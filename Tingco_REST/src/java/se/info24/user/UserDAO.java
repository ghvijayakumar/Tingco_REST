/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.user;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import se.info24.device.DeviceDAO;
import se.info24.group.GroupDAO;
import se.info24.ismOperationsPojo.Blacklist;
import se.info24.ismOperationsPojo.ObjectUsageRecords;
import se.info24.measurementjaxb.TingcoMeasurementTypes;
import se.info24.pojo.AddressType;
import se.info24.pojo.AddressTypeTranslations;
import se.info24.pojo.Addresses;
import se.info24.pojo.ContentCategories;
import se.info24.pojo.Country;
import se.info24.pojo.Currency;
import se.info24.pojo.DeviceUsers;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupTrusts;
import se.info24.pojo.Groups;
import se.info24.pojo.ObjectAddresses;
import se.info24.pojo.ObjectAddressesId;
import se.info24.pojo.ObjectGroupTranslations;
import se.info24.pojo.ObjectGroups;
import se.info24.pojo.ObjectTags;
import se.info24.pojo.OgmuserAlias;
import se.info24.pojo.SettingDataType;
import se.info24.pojo.SupportOrganizationUsers;
import se.info24.pojo.TimeFrameGroups;
import se.info24.pojo.TimeGroups;
import se.info24.pojo.TimeZones;
import se.info24.pojo.UserAddresses;
import se.info24.pojo.UserAlias;
import se.info24.pojo.UserAliasDetails;
import se.info24.pojo.UserAliasOrders;
import se.info24.pojo.UserApplicationSettings;
import se.info24.pojo.UserApplicationSettingsToActivate;
import se.info24.pojo.UserExtendedLicenses;
import se.info24.pojo.UserFavoriteContentCategories;
import se.info24.pojo.UserFavoriteDevices;
import se.info24.pojo.UserFavoriteGroups;
import se.info24.pojo.UserFavoriteServices;
import se.info24.pojo.UserGroupMemberships;
import se.info24.pojo.UserGroupMemberships2;
import se.info24.pojo.UserLog;
import se.info24.pojo.UserPasswords;
import se.info24.pojo.UserProviderTypeReferences;
import se.info24.pojo.UserProviderTypes;
import se.info24.pojo.UserRoleMemberships2;
import se.info24.pojo.UserRoleMembershipsToActivate;
import se.info24.pojo.UserRoleMembershipsToActivateId;
import se.info24.pojo.UserRoles2;
import se.info24.pojo.UserRolesInGroups2;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.UserSettings2;
import se.info24.pojo.UserTimeZones2;
import se.info24.pojo.UserTimeZonesToActivate;
import se.info24.pojo.UserTypeTranslations2;
import se.info24.pojo.UserTypes2;
import se.info24.pojo.Users2;
import se.info24.pojo.UsersToActivate;
import se.info24.pojo.UsersToActivateId;
import se.info24.pojo.WeekdayTranslations;
import se.info24.restUtil.RestUtilDAO;
import se.info24.tcp.TingcoCustomerDAO;
import se.info24.tingcoEV.UserAliasOrder;
import se.info24.usersjaxb.FraudLog;
import se.info24.usersjaxb.TingcoUsers;
import se.info24.usersjaxb.User;
import se.info24.usersjaxb.UserAliases;
import se.info24.usersjaxb.UserRoles;
import se.info24.usersjaxb.UserTimeZone;
import se.info24.util.RSAPassword;
import se.info24.util.TCMUtil;

/**
 *
 * @author Sridhar Dasari
 */
public class UserDAO {

    public List<ObjectGroups> getObjectGroupsByObjectGroupIdsList(Set<String> objectGroupIdsList, Session session) {
        return session.getNamedQuery("getObjectGroupsByGroupIdsList").setParameterList("objectGroupId", objectGroupIdsList).list();
    }

    public boolean getUserByAppIdEmail(String domainID, String email, Session session) {
        try {
            List<Users2> userList = session.getNamedQuery("getUserByLoginNameandDomainId").setString("loginName", email).setString("domainID", domainID).list();
            return !userList.isEmpty() ? true : false;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return false;
        }
    }

    public List<Users2> getUserByAppIdEmails(String domainID, String email, Session session) {
        return session.getNamedQuery("getUserByLoginNameandDomainId").setString("loginName", email).setString("domainID", domainID).list();
    }

    public List<UserFavoriteGroups> getUserFavoriteGroupsByGroupId(String groupId, Session session) {
        return session.getNamedQuery("getUserFavoriteGroupsByGroupId").setString("groupId", groupId).list();
    }

    public List<UserRolesInGroups2> getUserRolesInGroups2ByGroupId(String groupId, Session session) {
        return session.getNamedQuery("getUserRolesInGroups2ByGroupID").setString("groupID", groupId).list();
    }

    public void saveOrUpdateUserSettings2(UserApplicationSettings settings2, Session session) {
        Transaction tx = null;
        try {
            if (settings2 != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(settings2);
                tx.commit();
            }
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public UserApplicationSettings getUserSettings2BySettingName(String userId, String settingName, Session session) {
        return (UserApplicationSettings) session.getNamedQuery("getUserAppSettingBySettingName").setString("userId", userId).setString("settingName", settingName).uniqueResult();
    }

    public List<UserSettings2> getUserSettings2ByUserID(String userId, Session session) {
        List<UserSettings2> userSettingsList = new ArrayList<UserSettings2>();
        try {
            userSettingsList = session.getNamedQuery("getUserSettingByUserID").setString("userID", userId).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
        }
        return userSettingsList;
    }

    public boolean saveUserTimezones2(UserTimeZones2 userTimezones2, Session session) {
        Transaction tx = null;
        try {
            if (userTimezones2 != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(userTimezones2);
                tx.commit();
                return true;
            }
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
        return false;
    }

    public boolean saveUserTimezonesToActivate(UserTimeZonesToActivate userTimeZonesToActivate, Session session) {
        Transaction tx = null;
        try {
            if (userTimeZonesToActivate != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(userTimeZonesToActivate);
                tx.commit();
                return true;
            }
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
        return false;
    }

    // To save Login, Logout and ForgotPassword services info.
    public boolean saveUserLog(UserLog userLog, Session session) {
        Transaction tx = null;
        try {
            if (userLog != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(userLog);
                tx.commit();
                return true;
            }
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
        return false;
    }

    public Users2 getUserById(String userId, Session session) {
        List<Users2> users2List = session.getNamedQuery("getUsers2ById").setString("userID", userId).list();
        return !users2List.isEmpty() ? users2List.get(0) : null;
    }

    public List<UserAlias> getUserAliasByUserID(Session session, String userId) {
        List<UserAlias> list = session.getNamedQuery("getUserAliasByLoggedInUserID").setString("userID", userId).list();
        return list;
    }

    public List<UserAlias> getUserAliasByUserIDOrderByCreatedDate(Session session, String userId) {
        return session.getNamedQuery("getUserAliasByUserID").setString("userId", userId).list();
    }

    public boolean addNewUserToActivate(TingcoUsers tUser, String pwd, HttpServletRequest request, Session session) {
        Transaction tx = session.beginTransaction();
        UsersToActivate uta = new UsersToActivate();

        try {

            List<User> users = tUser.getUsers().getUser();
            for (User u : users) {
                UsersToActivateId id = new UsersToActivateId(u.getUserEmail(), u.getDomainID());
                uta.setId(id);
                uta.setUserId(u.getUserID());
                uta.setFirstName(u.getFirstName());
                uta.setLastName(u.getLastName());
                uta.setUserEmail(u.getUserEmail());
                uta.setGroupId(u.getGroupID().getValue());
                uta.setPassword(RSAPassword.encryptedPwd(pwd));
                uta.setIsLockedOut(u.getIsLockedOut());
                uta.setCountryId(Integer.valueOf(u.getCountryID().getValue()));
                //UserTypes2 type = new UserTypes2("4a24b403-67ee-4236-acf2-58ee94d3a4a3");//TODO specify based on whether he is normal or admin user
                uta.setUserTypes2(new UserTypes2("4a24b403-67ee-4236-acf2-58ee94d3a4a3"));
                uta.setChangePwdRequired(0);
                GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                uta.setLastLoginDate(gc.getTime());
                uta.setLockedOutDate(gc.getTime());
                uta.setLastPasswordChangedDate(gc.getTime());
                uta.setFailedPasswordAttemptCount(0);
                uta.setCreatedDate(gc.getTime());
                uta.setUpdatedDate(gc.getTime());
                uta.setActiveFromDate(gc.getTime());
                gc = new GregorianCalendar();
                gc.add(Calendar.YEAR, 5);
                uta.setActiveToDate(gc.getTime());
                uta.setUserSmallImageUrl("");
                //adding UserApplicationSettingsToActivate
                UserApplicationSettingsToActivate uasta = new UserApplicationSettingsToActivate();
                uasta.setUserApplicationSettingId(UUID.randomUUID().toString());
                uasta.setApplicationSettingName("WelcomeUponLogin");
                uasta.setApplicationId(u.getApplicationID());
                uasta.setApplicationSettingValue("1");
                uasta.setUserId(u.getUserID());
                uasta.setSettingDataTypeId("A67EF262-2CDB-4292-B8D7-6517EBF20053");
                gc = new GregorianCalendar();
                uasta.setLastUpdatedByUserId(u.getUserID());
                uasta.setCreatedDate(gc.getTime());
                uasta.setUpdatedDate(gc.getTime());

                //adding timezone
                UserTimeZonesToActivate utz2 = new UserTimeZonesToActivate();
                List<UserTimeZone> timeZones = u.getUserTimeZones().getUserTimeZone();
                for (UserTimeZone utz : timeZones) {
                    utz2.setTimeZoneId(utz.getTimeZoneID());
                    utz2.setUserId(u.getUserID());
                    utz2.setUseDaylightSaving(new Integer(utz.getUseDayLightSaving()));
                    gc = new GregorianCalendar();
                    utz2.setCreatedDate(gc.getTime());
                    utz2.setUpdatedDate(gc.getTime());
                }
                UserRoleMembershipsToActivate urma = new UserRoleMembershipsToActivate();
                UserRoles ur = u.getUserRoles();
                UserRoleMembershipsToActivateId uid = new UserRoleMembershipsToActivateId();
                uid.setUserId(u.getUserID());
                uid.setUserRoleId(ur.getUserRole().get(0).getUserRoleID());
                urma.setId(uid);
                UserLog log = new UserLog();
                log.setCreatedDate(gc.getTime());
                log.setAction("CREATE NEW USER");
                log.setTableName("Users2");
                log.setRequestIp(request.getRemoteAddr());
                log.setUpdatedDate(gc.getTime());
                log.setUserId(u.getUserID());
                session.saveOrUpdate(log);
                session.saveOrUpdate(utz2);
                session.saveOrUpdate(uasta);
                session.saveOrUpdate(uta);
                session.saveOrUpdate(urma);
                tx.commit();
            }
            return true;

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tx.rollback();
            return false;
        }
    }

    public boolean addUsers2(Users2 user2, Addresses address, ObjectAddresses oa, Session session) {
        Transaction tx = null;
        try {
            if (user2 != null) {
                tx = session.beginTransaction();
                if (address != null) {
                    session.saveOrUpdate(address);
                }
                session.saveOrUpdate(user2);
                if (oa != null) {
                    session.saveOrUpdate(oa);
                }

                tx.commit();
                return true;
            }
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }

        }
        session.close();
        return false;
    }

    public boolean addUserPasswords(Session session, UserPasswords userPasswords) {
        Transaction tx = null;
        try {
            if (userPasswords != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(userPasswords);
                tx.commit();
                return true;
            }
        } catch (Exception he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
        }
        session.close();
        return false;
    }

    public boolean addUserTimeZones2(UserTimeZones2 timeZones2, Session session) {
        Transaction tx = null;
        try {
            if (timeZones2 != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(timeZones2);
                tx.commit();
                return true;
            }
        } catch (Exception he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
        }
        session.close();
        return false;
    }

    public List<UserGroupMemberships> getUserGroupMembershipsbyGroupId(String groupId, Session session) {
        return session.getNamedQuery("getUserGroupMembershipsbyGroupId").setString("groupId", groupId).list();
    }

    public List<UserGroupMemberships2> getUserGroupMemberships2byGroupId(String groupId, Session session) {
        return session.getNamedQuery("getUserGroupMemberships2ByGroupId").setString("groupId", groupId).list();
    }

    public boolean deleteUserByUserId(Session session, String userId) {
        Transaction tx = session.beginTransaction();
        boolean result = false;
        TingcoCustomerDAO customerDAO = new TingcoCustomerDAO();
        try {
            List<Users2> usersList = getUsers2ById(userId, session);
            List<UserRoleMemberships2> userRoleMemberships2List = session.getNamedQuery("getUserRoleMemberships2ByUserId").setString("userID", userId).list();
            UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(userId, session);
            List<UserAliasOrders> userAliasOrdersList = session.getNamedQuery("GetOrderedKeysByUserID").setString("userID", userId).list();
            List<UserApplicationSettings> userApplicationSettingsList = session.getNamedQuery("getUserApp").setString("userId", userId).list();
            List<UserExtendedLicenses> userExtendedLicenses = session.getNamedQuery("getUserExtendedLicenses").setString("userId", userId).list();
            List<UserFavoriteDevices> userFavoriteDevices = session.getNamedQuery("getUserFavoriteDevicesById").setString("userId", userId).list();
            List<UserFavoriteGroups> userFavoriteGroups = session.getNamedQuery("getUserFavoriteGroupsById").setString("userId", userId).list();
            List<UserFavoriteServices> userFavoriteServices = session.getNamedQuery("getUserFavoriteServices").setString("userId", userId).list();
            List<UserSessions2> userSessions2List = session.getNamedQuery("getUserSessions2ByUserId").setString("userId", userId).list();
            List<UserSettings2> userSettings2List = session.getNamedQuery("getUserSettingByUserID").setString("userID", userId).list();
            List<UserGroupMemberships2> userGroupMemberships2List = session.getNamedQuery("getUserGroupMemberships2ById").setString("userID", userId).list();
            List<UserFavoriteContentCategories> userFCCList = session.getNamedQuery("getUserFavouriteContentCategories").setString("userID", userId).list();
            List<ContentCategories> contentCategoriesList = session.getNamedQuery("getContentCategoryByUserId").setString("userId", userId).list();
            List<Groups> groupsList = session.getNamedQuery("getGroupsByUserId").setString("userId", userId).list();
//            List<Services> servicesList = session.getNamedQuery("getServicesByUserId").setString("userId", userId).list();
//            List<Device> deviceList = session.getNamedQuery("getDeviceByUserId").setString("userId", userId).list();
            List<UserAlias> userAliasList = session.getNamedQuery("getUserAliasByLoggedInUserID").setString("userID", userId).list();
            List<TimeGroups> timeGroupsList = session.getNamedQuery("getTimeGroupsByUserId").setString("userId", userId).list();
            List<TimeFrameGroups> timeFrameGroupsList = session.getNamedQuery("getTimeFrameGroupsByUserId").setString("userId", userId).list();
//            List<Weekdays> weekdaysList = session.getNamedQuery("getWeekdaysByUserId").setString("userId", userId).list();
            List<WeekdayTranslations> weekdayTransList = session.getNamedQuery("getWeekDaysByUserId").setString("userId", userId).list();
            List<SettingDataType> settingDataTypeList = session.getNamedQuery("getSettingDataTypeByUserId").setString("userId", userId).list();
            UserProviderTypeReferences userProviderTypeReferences = (UserProviderTypeReferences) session.getNamedQuery("getUserProviderTypeReferencesByUserID").setString("userId", userId).uniqueResult();

            List<SupportOrganizationUsers> supportOrganizationUserses = session.getNamedQuery("getSupportOrganizationUsersByUserId").setString("userId", userId).list();

            UserProviderTypes upt = customerDAO.getUserProviderTypesByUserID(session, userId);
            List<ObjectAddresses> objectAddress = customerDAO.getObjectAddressesByobjectId(session, userId);


            if (!supportOrganizationUserses.isEmpty()) {
                for (SupportOrganizationUsers supportOrganizationUserse : supportOrganizationUserses) {
                    session.delete(supportOrganizationUserse);
                }
            }
            if (!userGroupMemberships2List.isEmpty()) {
                for (UserGroupMemberships2 ugm : userGroupMemberships2List) {
                    session.delete(ugm);
                }
            }
            if (!userFCCList.isEmpty()) {
                for (UserFavoriteContentCategories ufcc : userFCCList) {
                    session.delete(ufcc);
                }
            }
            if (!contentCategoriesList.isEmpty()) {
                for (ContentCategories cc : contentCategoriesList) {
                    session.delete(cc);
                }
            }
            session.flush();
            session.clear();
//            if (!groupsList.isEmpty()) {
//                for (Groups g : groupsList) {
//                    session.delete(g);
//                }
//            }
//            if (!servicesList.isEmpty()) {
//                for (Services s : servicesList) {
//                    session.delete(s);
//                }
//            }
//            if (!deviceList.isEmpty()) {
//                for (Device d : deviceList) {
//                    session.delete(d);
//                }
//            }
            if (!userAliasList.isEmpty()) {
                for (UserAlias ua : userAliasList) {
                    session.delete(ua);
                }
            }
            if (!timeGroupsList.isEmpty()) {
                for (TimeGroups tg : timeGroupsList) {
                    session.delete(tg);
                }
            }
            if (!timeFrameGroupsList.isEmpty()) {
                for (TimeFrameGroups tfg : timeFrameGroupsList) {
                    session.delete(tfg);
                }
            }
            if (!weekdayTransList.isEmpty()) {
                for (WeekdayTranslations wdt : weekdayTransList) {
                    session.delete(wdt);
                }
            }
            session.flush();
            session.clear();

            if (!settingDataTypeList.isEmpty()) {
                for (SettingDataType sdt : settingDataTypeList) {
                    session.delete(sdt);
                }
            }
            if (!userRoleMemberships2List.isEmpty()) {
                for (UserRoleMemberships2 urm : userRoleMemberships2List) {
                    session.delete(urm);
                }
            }

            if (userTimeZones2 != null) {
                session.delete(userTimeZones2);
            }

            if (!userAliasOrdersList.isEmpty()) {
                for (UserAliasOrders uao : userAliasOrdersList) {
                    session.delete(uao);
                }
            }
            if (!userApplicationSettingsList.isEmpty()) {
                for (UserApplicationSettings uas : userApplicationSettingsList) {
                    session.delete(uas);
                }
            }
            if (!userExtendedLicenses.isEmpty()) {
                for (UserExtendedLicenses uel : userExtendedLicenses) {
                    session.delete(uel);
                }
            }
            if (!userFavoriteDevices.isEmpty()) {
                for (UserFavoriteDevices ufd : userFavoriteDevices) {
                    session.delete(ufd);
                }
            }
            if (!userFavoriteGroups.isEmpty()) {
                for (UserFavoriteGroups ufg : userFavoriteGroups) {
                    session.delete(ufg);
                }
            }
            if (!userFavoriteServices.isEmpty()) {
                for (UserFavoriteServices ufs : userFavoriteServices) {
                    session.delete(ufs);
                }
            }
            if (!userSessions2List.isEmpty()) {
                for (UserSessions2 us : userSessions2List) {
                    session.delete(us);
                }
            }
            session.flush();
            session.clear();

            if (!userSettings2List.isEmpty()) {
                for (UserSettings2 us : userSettings2List) {
                    session.delete(us);
                }
            }

            if (userProviderTypeReferences != null) {
                session.delete(userProviderTypeReferences);
            }

            if (upt != null) {
                session.delete(upt);
            }

            if (!objectAddress.isEmpty()) {
                for (ObjectAddresses objectAddresses : objectAddress) {
                    Addresses address = (Addresses) getAddress(objectAddresses.getAddressId(), session);
                    if (address != null) {
                        session.delete(address);
                    }
                    session.delete(objectAddresses);
                }
            }

            for (Users2 user : usersList) {
                if (user != null) {
                    session.delete(user);
                }
            }

            tx.commit();
            result = true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tx.rollback();
            result = false;
        }
        return result;
    }

    public boolean deleteGroupUserByUserId(Session session, String userId) {
//        Transaction tx = session.beginTransaction();
        boolean result = false;
        TingcoCustomerDAO customerDAO = new TingcoCustomerDAO();
        try {
            List<Users2> usersList = getUsers2ById(userId, session);
            List<UserRoleMemberships2> userRoleMemberships2List = session.getNamedQuery("getUserRoleMemberships2ByUserId").setString("userID", userId).list();
            UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(userId, session);
            List<UserAliasOrders> userAliasOrdersList = session.getNamedQuery("GetOrderedKeysByUserID").setString("userID", userId).list();
            List<UserApplicationSettings> userApplicationSettingsList = session.getNamedQuery("getUserApp").setString("userId", userId).list();
            List<UserExtendedLicenses> userExtendedLicenses = session.getNamedQuery("getUserExtendedLicenses").setString("userId", userId).list();
            List<UserFavoriteDevices> userFavoriteDevices = session.getNamedQuery("getUserFavoriteDevicesById").setString("userId", userId).list();
            List<UserFavoriteGroups> userFavoriteGroups = session.getNamedQuery("getUserFavoriteGroupsById").setString("userId", userId).list();
            List<UserFavoriteServices> userFavoriteServices = session.getNamedQuery("getUserFavoriteServices").setString("userId", userId).list();
            List<UserSessions2> userSessions2List = session.getNamedQuery("getUserSessions2ByUserId").setString("userId", userId).list();
            List<UserSettings2> userSettings2List = session.getNamedQuery("getUserSettingByUserID").setString("userID", userId).list();
            List<UserGroupMemberships2> userGroupMemberships2List = session.getNamedQuery("getUserGroupMemberships2ById").setString("userID", userId).list();
            List<UserFavoriteContentCategories> userFCCList = session.getNamedQuery("getUserFavouriteContentCategories").setString("userID", userId).list();
            List<ContentCategories> contentCategoriesList = session.getNamedQuery("getContentCategoryByUserId").setString("userId", userId).list();
            List<Groups> groupsList = session.getNamedQuery("getGroupsByUserId").setString("userId", userId).list();
//            List<Services> servicesList = session.getNamedQuery("getServicesByUserId").setString("userId", userId).list();
//            List<Device> deviceList = session.getNamedQuery("getDeviceByUserId").setString("userId", userId).list();
            List<UserAlias> userAliasList = session.getNamedQuery("getUserAliasByLoggedInUserID").setString("userID", userId).list();
            List<TimeGroups> timeGroupsList = session.getNamedQuery("getTimeGroupsByUserId").setString("userId", userId).list();
            List<TimeFrameGroups> timeFrameGroupsList = session.getNamedQuery("getTimeFrameGroupsByUserId").setString("userId", userId).list();
//            List<Weekdays> weekdaysList = session.getNamedQuery("getWeekdaysByUserId").setString("userId", userId).list();
            List<WeekdayTranslations> weekdayTransList = session.getNamedQuery("getWeekDaysByUserId").setString("userId", userId).list();
            List<SettingDataType> settingDataTypeList = session.getNamedQuery("getSettingDataTypeByUserId").setString("userId", userId).list();
            UserProviderTypeReferences userProviderTypeReferences = (UserProviderTypeReferences) session.getNamedQuery("getUserProviderTypeReferencesByUserID").setString("userId", userId).uniqueResult();
            UserProviderTypes upt = customerDAO.getUserProviderTypesByUserID(session, userId);
            List<ObjectAddresses> objectAddress = customerDAO.getObjectAddressesByobjectId(session, userId);

            if (!userGroupMemberships2List.isEmpty()) {
                for (UserGroupMemberships2 ugm : userGroupMemberships2List) {
                    session.delete(ugm);
                }
            }
            if (!userFCCList.isEmpty()) {
                for (UserFavoriteContentCategories ufcc : userFCCList) {
                    session.delete(ufcc);
                }
            }
            if (!contentCategoriesList.isEmpty()) {
                for (ContentCategories cc : contentCategoriesList) {
                    session.delete(cc);
                }
            }
            session.flush();
            session.clear();
            if (!groupsList.isEmpty()) {
                for (Groups g : groupsList) {
                    session.delete(g);
                }
            }
//            if (!servicesList.isEmpty()) {
//                for (Services s : servicesList) {
//                    session.delete(s);
//                }
//            }
//            if (!deviceList.isEmpty()) {
//                for (Device d : deviceList) {
//                    session.delete(d);
//                }
//            }
            if (!userAliasList.isEmpty()) {
                for (UserAlias ua : userAliasList) {
                    session.delete(ua);
                }
            }
            if (!timeGroupsList.isEmpty()) {
                for (TimeGroups tg : timeGroupsList) {
                    session.delete(tg);
                }
            }
            if (!timeFrameGroupsList.isEmpty()) {
                for (TimeFrameGroups tfg : timeFrameGroupsList) {
                    session.delete(tfg);
                }
            }
            if (!weekdayTransList.isEmpty()) {
                for (WeekdayTranslations wdt : weekdayTransList) {
                    session.delete(wdt);
                }
            }
            session.flush();
            session.clear();

            if (!settingDataTypeList.isEmpty()) {
                for (SettingDataType sdt : settingDataTypeList) {
                    session.delete(sdt);
                }
            }
            if (!userRoleMemberships2List.isEmpty()) {
                for (UserRoleMemberships2 urm : userRoleMemberships2List) {
                    session.delete(urm);
                }
            }

            if (userTimeZones2 != null) {
                session.delete(userTimeZones2);
            }

            if (!userAliasOrdersList.isEmpty()) {
                for (UserAliasOrders uao : userAliasOrdersList) {
                    session.delete(uao);
                }
            }
            if (!userApplicationSettingsList.isEmpty()) {
                for (UserApplicationSettings uas : userApplicationSettingsList) {
                    session.delete(uas);
                }
            }
            if (!userExtendedLicenses.isEmpty()) {
                for (UserExtendedLicenses uel : userExtendedLicenses) {
                    session.delete(uel);
                }
            }
            if (!userFavoriteDevices.isEmpty()) {
                for (UserFavoriteDevices ufd : userFavoriteDevices) {
                    session.delete(ufd);
                }
            }
            if (!userFavoriteGroups.isEmpty()) {
                for (UserFavoriteGroups ufg : userFavoriteGroups) {
                    session.delete(ufg);
                }
            }
            if (!userFavoriteServices.isEmpty()) {
                for (UserFavoriteServices ufs : userFavoriteServices) {
                    session.delete(ufs);
                }
            }
            if (!userSessions2List.isEmpty()) {
                for (UserSessions2 us : userSessions2List) {
                    session.delete(us);
                }
            }
            session.flush();
            session.clear();

            if (!userSettings2List.isEmpty()) {
                for (UserSettings2 us : userSettings2List) {
                    session.delete(us);
                }
            }

            if (userProviderTypeReferences != null) {
                session.delete(userProviderTypeReferences);
            }

            if (upt != null) {
                session.delete(upt);
            }

            if (!objectAddress.isEmpty()) {
                Addresses address = (Addresses) getAddress(objectAddress.get(0).getAddressId(), session);
                session.delete(address);

            }

            for (Users2 user : usersList) {
                if (user != null) {
                    session.delete(user);
                }
            }

//            tx.commit();
            result = true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
//            tx.rollback();
            result = false;
        }
        return result;
    }

    List<Object> getBlackListBySearchCriteria(String groupId, String startValue, String userAliasType, int countryId, Session session) {
        StringBuffer queryString = new StringBuffer();
//        queryString.append("select bl.blackListId as blackListId, bl.startValue as keyNumber, (ua.firstName +' '+ ua.lastName) as keyName, uat.userAliasTypeName as type, " +
//                "gt.groupName as organization, (u.firstName+' '+ u.lastName) as assignedToUser, bl.description as reason, bl.createdDate as blockedDate " +
//                "from ismoperations.dbo.blackList as bl inner join userAlias as ua on ua.userAlias = bl.startValue and ua.groupId = '" + groupId + "' inner join blackListType as blt " +
//                "on bl.blackListTypeId = blt.blackListTypeId inner join userAliasTypes as uat on blt.userAliasTypeId = uat.userAliasTypeId inner join userAliasTypesInGroups as uatig " +
//                "on uat.userAliasTypeId = uatig.userAliasTypeId and uatig.groupId = '" + groupId + "' inner join groupTranslations as gt on ua.groupId = gt.groupId " +
//                "and gt.countryId = " + countryId + " inner join users2 as u on ua.userId = u.userId ");
        queryString.append("select bl.blackListId as blackListId, bl.startValue as keyNumber, (ua.firstName +' '+ ua.lastName) as keyName, uat.userAliasTypeName as type, " +
                "gt.groupName as organization, (u.firstName+' '+ u.lastName) as assignedToUser, bl.description as reason, bl.createdDate as blockedDate " +
                "from ismoperations.dbo.blackList as bl left join userAlias as ua on ua.userAlias = bl.startValue left join blackListType as blt " +
                "on bl.blackListTypeId = blt.blackListTypeId left join userAliasTypes as uat on blt.userAliasTypeId = uat.userAliasTypeId left join userAliasTypesInGroups as uatig " +
                "on uat.userAliasTypeId = uatig.userAliasTypeId left join groupTranslations as gt on ua.groupId = gt.groupId " +
                "and gt.countryId = " + countryId + " left join users2 as u on ua.userId = u.userId where uatig.groupid = '" + groupId + "' ");

        if (startValue != null) {
            queryString.append(" and bl.startValue like '%" + startValue + "%' ");
        }
        if (userAliasType != null) {
            if (TCMUtil.isValidUUID(userAliasType)) {
                queryString.append(" and uat.userAliasTypeId = '" + userAliasType + "' ");
            } else {
                queryString.append(" and uat.userAliasTypeName like '%" + userAliasType + "%' ");
            }
        }
        queryString.append(" order by bl.startValue asc");
        SQLQuery query = session.createSQLQuery(queryString.toString());
        query.addScalar("blackListId", Hibernate.STRING);
        query.addScalar("keyNumber", Hibernate.STRING);
        query.addScalar("keyName", Hibernate.STRING);
        query.addScalar("type", Hibernate.STRING);
        query.addScalar("organization", Hibernate.STRING);
        query.addScalar("assignedToUser", Hibernate.STRING);
        query.addScalar("reason", Hibernate.STRING);
        query.addScalar("blockedDate", Hibernate.TIMESTAMP);
        return query.setMaxResults(200).list();
    }

    List<Object> getDefaultUsersListForListUserPage(User user, Session session) {
        StringBuffer queryString = new StringBuffer();
        queryString.append("select u.firstname as firstName, u.lastname as lastName, u.useremail as userEmail, gts.groupname as groupName, ad.addressstreet as addressStreet, " +
                "ad.addresszip as addressZip, ad.addresscity as addressCity, c.countryName as countryName, u.createddate as createdDate from users2 as u " +
                "inner join groups as g on u.groupid = g.groupid and g.groupid in (" + TCMUtil.getGroupTrust(user.getGroupID().getValue()) + ") " +
                "inner join GroupTranslations as gts on g.groupId = gts.groupId and gts.countryid = " + user.getCountryID().getValue() + " " +
                "left join objectaddresses oa on u.userid = oa.objectid left join addresses as ad " +
                "on oa.addressid = ad.addressid and ad.addresstypeid = 3 inner join country as c on ad.countryid = c.countryid ");

        queryString.append(" order by u.firstName asc, u.lastName asc ");
        SQLQuery query = session.createSQLQuery(queryString.toString());
        query.addScalar("firstName", Hibernate.STRING);
        query.addScalar("lastName", Hibernate.STRING);
        query.addScalar("userEmail", Hibernate.STRING);
        query.addScalar("groupName", Hibernate.STRING);
        query.addScalar("addressStreet", Hibernate.STRING);
        query.addScalar("addressZip", Hibernate.STRING);
        query.addScalar("addressCity", Hibernate.STRING);
        query.addScalar("countryName", Hibernate.STRING);
        query.addScalar("createdDate", Hibernate.TIMESTAMP);
        return query.setMaxResults(2000).list();
    }

    List<Object> getDefaultUsersListForSearchUsersPage(User user, Session session) {
        StringBuffer queryString = new StringBuffer();
        queryString.append("select distinct u.userID as UserID,u.firstname as firstName, u.lastname as lastName, u.useremail as userEmail, gts.groupname as groupName, u.isLockedOut as isLockedOut, " +
                "u.lastLoginDate as lastLoginDate from users2 as u inner join groups as g on u.groupid = g.groupid and g.groupid = '" + user.getGroupID().getValue() + "' " +
                "inner join GroupTranslations as gts on g.groupId = gts.groupId and gts.countryid = " + user.getCountryID().getValue() + " ");

        queryString.append(" order by u.firstName asc, u.lastName asc ");
        SQLQuery query = session.createSQLQuery(queryString.toString());
        query.addScalar("UserID", Hibernate.STRING);
        query.addScalar("firstName", Hibernate.STRING);
        query.addScalar("lastName", Hibernate.STRING);
        query.addScalar("userEmail", Hibernate.STRING);
        query.addScalar("groupName", Hibernate.STRING);
        query.addScalar("isLockedOut", Hibernate.INTEGER);
        query.addScalar("lastLoginDate", Hibernate.TIMESTAMP);
        return query.setMaxResults(2000).list();
    }

    Object getDeviceUsersByDeviceID(Session session, String deviceId) {
        return session.getNamedQuery("getDeviceUsersByDeviceID").setString("deviceId", deviceId).uniqueResult();
    }

    Object getFraudLogById(String fraudLogId, Session ismOperationsSession) {
        return ismOperationsSession.getNamedQuery("getFraudLogById").setString("fraudLogId", fraudLogId).uniqueResult();
    }

    List<FraudLog> getFraudLogDetailsBySearchCriteria(TingcoUsers tingcoUsers, FraudLog fraudLogjaxb, Session session, String timeZoneGMToffset) throws ParseException {
        List<FraudLog> fraudLogList = new ArrayList<FraudLog>();
        String timePeriod = fraudLogjaxb.getEventDatas().getTimePeriod();
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        String gc_time = lFormat.format(gc.getTime());
        GregorianCalendar gc_diff = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        String gc_diff_time = null;
        if (timePeriod != null) {
            if (timePeriod.equalsIgnoreCase("hour")) {
                gc_diff.add(GregorianCalendar.HOUR, -1);
                gc_diff_time = lFormat.format(gc_diff.getTime());
            } else if (timePeriod.equalsIgnoreCase("day")) {
                gc_diff.add(GregorianCalendar.DATE, -1);
                gc_diff_time = lFormat.format(gc_diff.getTime());
            } else if (timePeriod.equalsIgnoreCase("week")) {
                gc_diff.add(GregorianCalendar.DATE, -7);
                gc_diff_time = lFormat.format(gc_diff.getTime());
            } else if (timePeriod.equalsIgnoreCase("month")) {
                gc_diff.add(GregorianCalendar.MONTH, -1);
                gc_diff_time = lFormat.format(gc_diff.getTime());
            }
        } else if (fraudLogjaxb.getEventDatas().getFromDate() != null && fraudLogjaxb.getEventDatas().getToDate() != null) {

            gc_diff.setTime(lFormat.parse(fraudLogjaxb.getEventDatas().getFromDate()));
            gc_diff.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc_diff.getTime()));
            gc_diff_time = lFormat.format(gc_diff.getTime());

            gc.setTime(lFormat.parse(fraudLogjaxb.getEventDatas().getToDate()));
            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
            gc_time = lFormat.format(gc.getTime());
        }

        StringBuffer queryString = new StringBuffer();

        queryString.append("select fl.fraudLogId as fraudLogID, ua.userAliasId  as userAliasID , ua.userAlias as userAlias, d.deviceId as deviceID, d.deviceName as deviceName, " +
                "substring(fl.fraudText,1,50) as fraudText, fl.isChecked as isChecked, fl.createdDate as createdDate from UserAlias as ua inner join ismoperations.dbo.fraudLog as fl " +
                "on ua.useraliasid = fl.useraliasid inner join device as d on d.deviceid = fl.deviceid " +
                "and ua.groupid in(" + TCMUtil.getGroupTrust(fraudLogjaxb.getGroupID().getValue()) + ") and d.groupid in(" + TCMUtil.getGroupTrust(fraudLogjaxb.getGroupID().getValue()) + ") ");

        if (fraudLogjaxb.getUserAlias() != null) {
            if (TCMUtil.isValidUUID(fraudLogjaxb.getUserAlias())) {
                queryString.append(" and fl.userAliasId = '" + fraudLogjaxb.getUserAlias() + "' ");
            } else {
                queryString.append(" and ua.userAlias like '%" + fraudLogjaxb.getUserAlias() + "%' ");
            }
        }

        if (fraudLogjaxb.getDeviceName() != null) {
            if (TCMUtil.isValidUUID(fraudLogjaxb.getDeviceName())) {
                queryString.append(" and fl.deviceId = '" + fraudLogjaxb.getDeviceName() + "' ");
            } else {
                queryString.append(" and d.deviceName like '%" + fraudLogjaxb.getDeviceName() + "%' ");
            }
        }

        if (fraudLogjaxb.getFraudText() != null) {
            queryString.append(" and fl.fraudText like '%" + fraudLogjaxb.getFraudText() + "%' ");
        }

        if (timePeriod != null) {
            if (timePeriod.equalsIgnoreCase("100")) {
                queryString.append(" order by fl.createdDate desc");
                System.out.println(queryString.toString());
                SQLQuery query = session.createSQLQuery(queryString.toString());
                query.addScalar("fraudLogID", Hibernate.STRING);
                query.addScalar("userAliasID", Hibernate.STRING);
                query.addScalar("userAlias", Hibernate.STRING);
                query.addScalar("deviceID", Hibernate.STRING);
                query.addScalar("deviceName", Hibernate.STRING);
                query.addScalar("fraudText", Hibernate.STRING);
                query.addScalar("isChecked", Hibernate.STRING);
                query.addScalar("createdDate", Hibernate.STRING);
                query.setResultTransformer(Transformers.aliasToBean(FraudLog.class));
                fraudLogList = query.setMaxResults(100).list();

            } else if (timePeriod.equalsIgnoreCase("5")) {
                queryString.append(" order by fl.createdDate desc");
                System.out.println(queryString.toString());
                SQLQuery query = session.createSQLQuery(queryString.toString());
                query.addScalar("fraudLogID", Hibernate.STRING);
                query.addScalar("userAliasID", Hibernate.STRING);
                query.addScalar("userAlias", Hibernate.STRING);
                query.addScalar("deviceID", Hibernate.STRING);
                query.addScalar("deviceName", Hibernate.STRING);
                query.addScalar("fraudText", Hibernate.STRING);
                query.addScalar("isChecked", Hibernate.STRING);
                query.addScalar("createdDate", Hibernate.STRING);
                query.setResultTransformer(Transformers.aliasToBean(FraudLog.class));
                fraudLogList = query.setMaxResults(5).list();

            } else {
                queryString.append(" and fl.createdDate between '" + gc_diff_time + "' and '" + gc_time + "' order by fl.createdDate desc");
                System.out.println(queryString.toString());
                SQLQuery query = session.createSQLQuery(queryString.toString());
                query.addScalar("fraudLogID", Hibernate.STRING);
                query.addScalar("userAliasID", Hibernate.STRING);
                query.addScalar("userAlias", Hibernate.STRING);
                query.addScalar("deviceID", Hibernate.STRING);
                query.addScalar("deviceName", Hibernate.STRING);
                query.addScalar("fraudText", Hibernate.STRING);
                query.addScalar("isChecked", Hibernate.STRING);
                query.addScalar("createdDate", Hibernate.STRING);
                query.setResultTransformer(Transformers.aliasToBean(FraudLog.class));
                fraudLogList = query.list();
            }
        } else {
            queryString.append(" and fl.createdDate between '" + gc_diff_time + "' and '" + gc_time + "' order by fl.createdDate desc");
            System.out.println(queryString.toString());
            SQLQuery query = session.createSQLQuery(queryString.toString());
            query.addScalar("fraudLogID", Hibernate.STRING);
            query.addScalar("userAliasID", Hibernate.STRING);
            query.addScalar("userAlias", Hibernate.STRING);
            query.addScalar("deviceID", Hibernate.STRING);
            query.addScalar("deviceName", Hibernate.STRING);
            query.addScalar("fraudText", Hibernate.STRING);
            query.addScalar("isChecked", Hibernate.STRING);
            query.addScalar("createdDate", Hibernate.STRING);
            query.setResultTransformer(Transformers.aliasToBean(FraudLog.class));
            fraudLogList = query.list();
        }

        return fraudLogList;

    }

    List<ObjectUsageRecords> getObjectUsageRecordsByUserAlias(Session session, String userAlias, String month, String year) {
        List<ObjectUsageRecords> list = session.getNamedQuery("getObjectUsageRecordsByUserAlias").setString("userAlias", userAlias).setString("month", month).setString("year", year).list();
        return list;
    }

    public boolean updateUserAliasDetailsByUserAliasId(Session session, String userAliasId, String firstName, String lastName, int isEnabled,
            String lastUpdatedByUserid) {
        Transaction tx = session.beginTransaction();
        try {

            UserAlias userAlias = (UserAlias) getUserAliasDetailsByUserAliasID(session, userAliasId);
            userAlias.setFirstName(!firstName.equals("") ? firstName : userAlias.getFirstName());
            userAlias.setLastName(!lastName.equals("") ? lastName : userAlias.getLastName());
            userAlias.setIsEnabled(isEnabled);
            userAlias.setLastUpdatedByUserId(!lastUpdatedByUserid.equals("") ? lastUpdatedByUserid : userAlias.getLastUpdatedByUserId());
            GregorianCalendar gc = new GregorianCalendar();
            userAlias.setUpdatedDate(gc.getTime());
            session.saveOrUpdate(userAlias);
            tx.commit();
            return true;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            return false;
        }
    }

    public boolean updateUserAddresses(Addresses addresses, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(addresses);
            tx.commit();
            return true;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }

    }

    @SuppressWarnings("unchecked")
    public List<UserAliasOrders> getOrderedKeysByUserID(Session session, String UserId) {
        try {
            return session.getNamedQuery("GetOrderedKeysByUserID").setString("userID", UserId).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public boolean insertOrderNewKey(Session session, List<UserAliasOrder> list, String userID) {
        try {
            Transaction tx = session.beginTransaction();
            GregorianCalendar gc = new GregorianCalendar();
            for (UserAliasOrder userAliasOrder : list) {
                se.info24.pojo.UserAliasOrders uao = new UserAliasOrders();
                uao.setUserAliasOrderId(UUID.randomUUID().toString());
                uao.setUserId(userID);
                uao.setGroupId(userAliasOrder.getGroupID());
                uao.setOrderDate(gc.getTime());
                uao.setOrderedUserAlias(userAliasOrder.getOrderedUserAlias());
                uao.setOrderComment(userAliasOrder.getOrderComment());
                uao.setIsDelivered(userAliasOrder.getIsDelivered());
                uao.setFirstName(userAliasOrder.getFirstName());
                uao.setLastName(userAliasOrder.getLastName());
                uao.setDeliveryAddressRow1(userAliasOrder.getDeliveryAddressRow1());
                uao.setDeliveryAddressRow2(userAliasOrder.getDeliveryAddressRow2());
                uao.setDeliveryZipCode(userAliasOrder.getDeliveryZipCode());
                uao.setDeliveryCity(userAliasOrder.getDeliveryCity());
                uao.setDeliveryCountry(userAliasOrder.getDeliveryCountry());
                uao.setLastUpdatedByUserId(userID);
                uao.setCreatedDate(gc.getTime());
                uao.setUpdatedDate(gc.getTime());
                session.saveOrUpdate(uao);
            }

            tx.commit();
            return true;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return false;
        }

    }

    public List<UserAddresses> getUserAddressDetails(String userId, Session session) {
        List<UserAddresses> add = session.getNamedQuery("getUserAddressByUserID").setString("userId", userId).list();
        return add;
    }

    public boolean updateUserAddresses(User u, String addressId, Session session) {
        Transaction tx = session.beginTransaction();
        DeviceDAO deviceDAO = new DeviceDAO();
        try {
            Addresses addresses = deviceDAO.getAddress(addressId, session);
            if (u.getAddress().getAddressName() != null) {
                addresses.setAddressName(u.getAddress().getAddressName());
            }
            if (u.getAddress().getAddressDesc() != null) {
                addresses.setAddressDescription(u.getAddress().getAddressDesc());
            }
            if (u.getAddress().getAddressRegion() != null) {
                addresses.setAddressRegion(u.getAddress().getAddressRegion());
            }
            if (u.getAddress().getAddressStreet() != null) {
                addresses.setAddressStreet(u.getAddress().getAddressStreet());
            }
            if (u.getAddress().getAddressStreet2() != null) {
                addresses.setAddressStreet2(u.getAddress().getAddressStreet2());
            }
            if (u.getAddress().getAddressSuite() != null) {
                addresses.setAddressSuite(u.getAddress().getAddressSuite());
            }
            if (u.getAddress().getAddressZip() != null) {
                addresses.setAddressZip(u.getAddress().getAddressZip());
            }
            if (u.getAddress().getAddressCity() != null) {
                addresses.setAddressCity(u.getAddress().getAddressCity());
            }

            if (u.getAddress().getCountryStateID() != null) {
                addresses.setCountryStateId(u.getAddress().getCountryStateID());
            }
            if (u.getAddress().getAddressPhone() != null) {
                addresses.setAddressPhone(u.getAddress().getAddressPhone());
            }
            if (u.getAddress().getAddressMobile() != null) {
                addresses.setAddressMobile(u.getAddress().getAddressMobile());
            }

            if (u.getAddress().getAddressFax() != null) {
                addresses.setAddressFax(String.valueOf(u.getAddress().getAddressFax()));
            }
            if (u.getAddress().getAddressEmail() != null) {
                addresses.setAddressEmail(u.getAddress().getAddressEmail());
            }
            if (u.getAddress().getAddressWeb() != null) {
                addresses.setAddressWeb(u.getAddress().getAddressWeb());
            }

            if (u.getAddress().getAddressIM() != null) {
                addresses.setAddressIm(u.getAddress().getAddressIM());
            }
            if (u.getAddress().getAddressLatitude() != 0.0) {
                addresses.setAddressLatitude((double) u.getAddress().getAddressLatitude());
            }
            if (u.getAddress().getAddressLongitude() != 0.0) {
                addresses.setAddressLongitude((double) u.getAddress().getAddressLongitude());
            }


            if (u.getAddress().getAddressDepth() != 0.0) {
                addresses.setAddressDepth((double) u.getAddress().getAddressDepth());
            }


            addresses.setUserId(u.getUserID());
            GregorianCalendar gc = new GregorianCalendar();
            addresses.setUpdatedDate(gc.getTime());
            session.saveOrUpdate(addresses);
            tx.commit();
            return true;
        } catch (Exception e) {

            TCMUtil.exceptionLog(getClass().getName(), e);
            tx.rollback();
            return false;
        }
    }

    public boolean updateUserAddressesForObject(User u, String addressId, Session session, String userID) {
        Transaction tx = session.beginTransaction();
        DeviceDAO deviceDAO = new DeviceDAO();
        CountryDAO countryDao = new CountryDAO();
        GregorianCalendar gc = new GregorianCalendar();
        try {
            Addresses addresses = deviceDAO.getAddress(addressId, session);
            if (addresses == null) {
                addresses = new Addresses();
                addresses.setAddressId(addressId);
                AddressType at = getAddresstypeById(session, u.getAddress().getAddressTypeID());
                addresses.setAddressType(at);
                addresses.setCreatedDate(gc.getTime());
                ObjectAddresses objAddress = new ObjectAddresses();
                objAddress.setId(new ObjectAddressesId(u.getAddress().getObjectID(), u.getAddress().getAddressTypeID()));
                objAddress.setAddressId(addressId);
                objAddress.setLastUpdatedUserId(userID);
                objAddress.setCreatedDate(gc.getTime());
                objAddress.setUpdatedDate(gc.getTime());
                session.saveOrUpdate(objAddress);
            }
            if (u.getAddress().getAddressName() != null) {
                addresses.setAddressName(u.getAddress().getAddressName());
            } else {
                addresses.setAddressName(null);
            }
            if (u.getAddress().getAddressDesc() != null) {
                addresses.setAddressDescription(u.getAddress().getAddressDesc());
            } else {
                addresses.setAddressDescription(null);
            }
            if (u.getAddress().getAddressRegion() != null) {
                addresses.setAddressRegion(u.getAddress().getAddressRegion());
            } else {
                addresses.setAddressRegion(null);
            }
            if (u.getAddress().getAddressStreet() != null) {
                addresses.setAddressStreet(u.getAddress().getAddressStreet());
            } else {
                addresses.setAddressStreet(null);
            }
            if (u.getAddress().getAddressStreet2() != null) {
                addresses.setAddressStreet2(u.getAddress().getAddressStreet2());
            } else {
                addresses.setAddressStreet2(null);
            }
            if (u.getAddress().getAddressSuite() != null) {
                addresses.setAddressSuite(u.getAddress().getAddressSuite());
            } else {
                addresses.setAddressSuite(null);
            }
            if (u.getAddress().getAddressZip() != null) {
                addresses.setAddressZip(u.getAddress().getAddressZip());
            } else {
                addresses.setAddressZip(null);
            }
            if (u.getAddress().getAddressCity() != null) {
                addresses.setAddressCity(u.getAddress().getAddressCity());
            } else {
                addresses.setAddressCity(null);
            }
            if (u.getAddress().getCountryStateID() != null) {
                addresses.setCountryStateId(u.getAddress().getCountryStateID());
            }
            if (u.getAddress().getAddressPhone() != null) {
                addresses.setAddressPhone(u.getAddress().getAddressPhone());
            } else {
                addresses.setAddressPhone(null);
            }
            if (u.getAddress().getAddressMobile() != null) {
                addresses.setAddressMobile(u.getAddress().getAddressMobile());
            } else {
                addresses.setAddressMobile(null);
            }
            if (u.getAddress().getAddressFax() != null) {
                addresses.setAddressFax(String.valueOf(u.getAddress().getAddressFax()));
            } else {
                addresses.setAddressFax(null);
            }
            if (u.getAddress().getAddressEmail() != null) {
                addresses.setAddressEmail(u.getAddress().getAddressEmail());
            } else {
                addresses.setAddressEmail(null);
            }
            if (u.getAddress().getAddressWeb() != null) {
                addresses.setAddressWeb(u.getAddress().getAddressWeb());
            } else {
                addresses.setAddressWeb(null);
            }
            if (u.getAddress().getAddressIM() != null) {
                addresses.setAddressIm(u.getAddress().getAddressIM());
            }
            if (u.getAddress().getAddressLatitude() != 0.0) {
                addresses.setAddressLatitude((double) u.getAddress().getAddressLatitude());
            } else {
                addresses.setAddressLatitude(null);
            }
            if (u.getAddress().getAddressLongitude() != 0.0) {
                addresses.setAddressLongitude((double) u.getAddress().getAddressLongitude());
            } else {
                addresses.setAddressLongitude(null);
            }

            if (u.getAddress().getAddressDepth() != 0.0) {
                addresses.setAddressDepth((double) u.getAddress().getAddressDepth());
            } else {
                addresses.setAddressDepth(null);
            }
            if (u.getAddress().getCountryID() != 0) {
                Country country = countryDao.getCountryById(u.getAddress().getCountryID(), session);
                addresses.setCountry(country);
            }

            addresses.setUserId(userID);
            addresses.setUpdatedDate(gc.getTime());
            session.saveOrUpdate(addresses);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tx.rollback();
            return false;
        }
    }

    public boolean insertUserAddress(User u, String addressId, String userID, Session session) {
        boolean result = false;
        Transaction tx = session.beginTransaction();
        GregorianCalendar gc = new GregorianCalendar();
        try {
            Addresses addresses = new Addresses();
            addresses.setAddressId(addressId);

            addresses.setAddressType(new AddressType(u.getAddress().getAddressTypeID(), gc.getTime()));
            addresses.getAddressType().setAddressTypeId(u.getAddress().getAddressTypeID());

            addresses.setCountry(new Country(u.getAddress().getCountryID()));
            addresses.getCountry().setCountryId(u.getAddress().getCountryID());

            if (u.getAddress().getAddressName() != null) {
                addresses.setAddressName(u.getAddress().getAddressName());
            }
            if (u.getAddress().getAddressDesc() != null) {
                addresses.setAddressDescription(u.getAddress().getAddressDesc());
            }
            if (u.getAddress().getAddressRegion() != null) {
                addresses.setAddressRegion(u.getAddress().getAddressRegion());
            }
            if (u.getAddress().getAddressStreet() != null) {
                addresses.setAddressStreet(u.getAddress().getAddressStreet());
            }
            if (u.getAddress().getAddressStreet2() != null) {
                addresses.setAddressStreet2(u.getAddress().getAddressStreet2());
            }
            if (u.getAddress().getAddressSuite() != null) {
                addresses.setAddressSuite(u.getAddress().getAddressSuite());
            }
            if (u.getAddress().getAddressZip() != null) {
                addresses.setAddressZip(u.getAddress().getAddressZip());
            }
            if (u.getAddress().getAddressCity() != null) {
                addresses.setAddressCity(u.getAddress().getAddressCity());
            }

            if (u.getAddress().getCountryStateID() != null) {
                addresses.setCountryStateId(u.getAddress().getCountryStateID());
            }
            if (u.getAddress().getAddressPhone() != null) {
                addresses.setAddressPhone(u.getAddress().getAddressPhone());
            }
            if (u.getAddress().getAddressMobile() != null) {
                addresses.setAddressMobile(u.getAddress().getAddressMobile());
            }


            if (u.getAddress().getAddressFax() != null) {
                addresses.setAddressFax(String.valueOf(u.getAddress().getAddressFax()));
            }
            if (u.getAddress().getAddressEmail() != null) {
                addresses.setAddressEmail(u.getAddress().getAddressEmail());
            }
            if (u.getAddress().getAddressWeb() != null) {
                addresses.setAddressWeb(u.getAddress().getAddressWeb());
            }


            if (u.getAddress().getAddressIM() != null) {
                addresses.setAddressIm(u.getAddress().getAddressIM());
            }
            if (u.getAddress().getAddressLatitude() != 0.0) {
                addresses.setAddressLatitude((double) u.getAddress().getAddressLatitude());
            }
            if (u.getAddress().getAddressLongitude() != 0.0) {
                addresses.setAddressLongitude((double) u.getAddress().getAddressLongitude());
            }

            if (u.getAddress().getAddressDepth() != 0.0) {
                addresses.setAddressDepth((double) u.getAddress().getAddressDepth());
            }
            addresses.setUserId(userID);
            addresses.setCreatedDate(gc.getTime());
            addresses.setUpdatedDate(gc.getTime());



            UserAddresses useraddress = new UserAddresses();
            useraddress.setUserId(userID);
            useraddress.setCreatedDate(gc.getTime());
            useraddress.setUpdatedDate(gc.getTime());
            useraddress.setAddresses(addresses);
            //  useraddress.getAddresses().setAddressId(addressId);

            addresses.getUserAddresseses().add(useraddress);

            session.saveOrUpdate(addresses);
            tx.commit();
            result = true;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            result = false;
        }

        return result;
    }

    public List<UserTimeZones2> getUserTimeZones(String userId, Session session) {
        return session.getNamedQuery("getTimeZoneByUserID").setString("userID", userId).list();
    }

    public se.info24.pojo.TimeZones getTimeZone(String timeZoneId, Session session) {
        return (TimeZones) session.getNamedQuery("getTimezoneById").setString("timezoneId", timeZoneId).list().get(0);
    }

    public List<UserTypeTranslations2> getUserTypes(Session session, String countryId) {
        List<UserTypes2> userTypesList = session.getNamedQuery("getUserTypes2").list();
        List<UserTypeTranslations2> userTypeTransList = new ArrayList<UserTypeTranslations2>();
        if (!userTypesList.isEmpty()) {
            List<String> userTypeIDList = new ArrayList<String>();
            for (UserTypes2 ut : userTypesList) {
                userTypeIDList.add(ut.getUserTypeId());
            }
            userTypeTransList = session.getNamedQuery("getUserTypeTranslations2").setParameterList("userTypeId", userTypeIDList).setString("countryId", countryId).list();
        }
        return userTypeTransList;
    }

    public List<UserAlias> getUserAlias(Session session, String userId) {
        try {
            List<UserAlias> list = session.createCriteria(UserAlias.class).add(Restrictions.eq("userId", userId)).setMaxResults(100).addOrder(Order.asc("userAlias")).list();
            return list;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public List<Users2> getUsers2ByGroupId(String groupId, Session session) {
        return session.getNamedQuery("getUsers2ByGroupId").setString("groupId", groupId).list();
    }

    public List<Users2> getUsers2ByUserIdShorted(List<String> userID, Session session) {
        return session.getNamedQuery("getUsers2ByUserIdShorted").setParameterList("userID", userID).list();
    }

    public List<SupportOrganizationUsers> getSupportOrganizationUsersByUserIdandId(String userId, String supportOrganizationId, Session session) {
        return session.getNamedQuery("getSupportOrganizationUsersByUserIdandId").setString("userId", userId).setString("supportOrganizationId", supportOrganizationId).list();
    }

    public List<SupportOrganizationUsers> getSupportOrganizationUsersById(String supportOrganizationId, Session session) {
        return session.getNamedQuery("getSupportOrganizationUsersById").setString("supportOrganizationId", supportOrganizationId).list();
    }

    public List<Users2> getUserByGroupId(Set<String> groupId, String searchFilter, UserSessions2 sessions2, Session session) {
        List<Users2> searchedusersList = new ArrayList<Users2>();
        if (groupId != null) {
            searchedusersList = session.getNamedQuery("getUsers2ByGroupIdList").setParameterList("groupId", groupId).setMaxResults(5000).list();
            if (!searchedusersList.isEmpty() && searchFilter != null) {
                searchedusersList = getSearchedUser(searchedusersList, searchFilter, session);
            }
            if (searchFilter == null) {
                if (!searchedusersList.isEmpty()) {
                    searchedusersList = getSearchUser(getUserIdsList(searchedusersList), session);
                } else {
                    searchedusersList.clear();
                }
            }
        } else {
            Users2 users2 = getUserById(sessions2.getUserId(), session);
            GroupDAO groupDAO = new GroupDAO();
            Set<String> groupidset = groupDAO.getGroupsAndGroupTrusts(users2.getGroupId(), session);
            List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupidset), 2000);
            for (List<String> list : listsplit) {
                List<Users2> searchedusersListTemp = session.getNamedQuery("getUsers2ByGroupIdList").setParameterList("groupId", list).setMaxResults(5000).list();
                searchedusersList.addAll(searchedusersListTemp);
            }


            if (!searchedusersList.isEmpty() && searchFilter != null) {
                searchedusersList = getSearchedUser(searchedusersList, searchFilter, session);
            }
            if (searchFilter == null) {
                searchedusersList = getSearchUser(getUserIdsList(searchedusersList), session);
            }
//            else {
//                searchedusersList.clear();
//            }
        }
        return searchedusersList;
    }

    public List<Users2> getUserBySearchFilter(String searchFilter, Session session) {
        List<String> userIdForSortingList = new ArrayList<String>();
        List<Users2> sortedUsersList = new ArrayList<Users2>();
        try {
            List<Users2> list = new ArrayList<Users2>();
            if (searchFilter != null) {
                list = session.getNamedQuery("getUsers2BySearchFilters").setString("firstName", "%" + searchFilter + "%").setString("lastName", "%" + searchFilter + "%").setString("userEmail", "%" + searchFilter + "%").setMaxResults(50).list();
            } else {
                list = getUsers2(session);
            }

            for (Users2 u : list) {
                userIdForSortingList.add(u.getUserId());
            }
            List<List<String>> splList = TCMUtil.splitStringList(userIdForSortingList, 2000);
            for (List<String> list1 : splList) {
                if (!userIdForSortingList.isEmpty()) {
                    sortedUsersList = getUser2ListByUserId(list1, session);
                }
            }

            return sortedUsersList;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return null;
        }
    }

    public List<Users2> getUsers2BySearchFiltersAndGroup(String searchFilter, List<String> groupId, Session session) {
        try {
            List<Users2> list = new ArrayList<Users2>();
            if (searchFilter != null) {
                list = session.getNamedQuery("getUsers2BySearchFiltersAndGroup").setParameterList("groupId", groupId).setString("firstName", "%" + searchFilter + "%").setString("lastName", "%" + searchFilter + "%").setString("userEmail", "%" + searchFilter + "%").list();
            } else {
                list = getUsers2ByGroupIdListorderByFirstNameLastName(session, groupId, 0);
            }

            return list;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return null;
        }
    }

    public List<Users2> getUsers2(Session session) {
        return session.getNamedQuery("getUsers2").setMaxResults(50).list();
    }

    public List<Users2> getUser2ListByUserId(List<String> userIdsList, Session session) {
        return session.getNamedQuery("getUsers2InSortedOrder").setParameterList("userID", userIdsList).list();
    }

//    List<UserAlias> getUserAliasBySearchCriteria(UserAliases userAliases, TingcoUsers tingcoUsers, Session session) {
//        GroupDAO groupDAO = new GroupDAO();
//        List<UserAlias> userAliasList = new ArrayList<UserAlias>();
//        List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(userAliases.getGroupID(), session);
//        Set<String> groupIdsList = groupDAO.getGroupIdsList(userAliases.getGroupID(), groupTrustsList);
//        StringBuffer queryString = new StringBuffer("from UserAlias where groupId in (:groupIdsList)");
//
//        List<String> searchGroupIdsList = new ArrayList<String>();
//        if (userAliases.getGroupName() != null) {
//            List<GroupTranslations> groupTranslationsList = groupDAO.getGroupTransByGroupIdandSearchString(groupIdsList, userAliases.getGroupName(), userAliases.getCountryID().getValue(), session);
//            if (!groupTranslationsList.isEmpty()) {
//                searchGroupIdsList = groupDAO.getGroupidsListFromGroupTranslations(groupTranslationsList);
//                queryString.append(" and groupId in (:searchGroupIdsList)");
//            } else {
//                return userAliasList;
//            }
//        }
//
//        if (userAliases.getUserAlias() != null) {
//            if (userAliases.getUserAlias().length() == 36) {
//                queryString.append(" and userAliasId = '" + userAliases.getUserAlias() + "'");
//            } else {
//                queryString.append(" and (userAlias like '%" + userAliases.getUserAlias() + "%' or firstName like '%" + userAliases.getUserAlias() + "%' or lastName like '%" + userAliases.getUserAlias() + "%')");
//            }
//        }
//
//        List<String> userAliasIdsList = new ArrayList<String>();
//        if (userAliases.getObjectGroup() != null) {
//            Criteria criteria = session.createCriteria(UserAlias.class, "ua");
//            criteria.createAlias("ua.ogmuserAliases", "ogm", CriteriaSpecification.INNER_JOIN);
//            criteria.createAlias("ogm.objectGroups", "og", CriteriaSpecification.INNER_JOIN);
//            criteria.add(Restrictions.in("og.groupId", groupIdsList));
//            criteria.createAlias("og.objectGroupTranslationses", "ogt", CriteriaSpecification.INNER_JOIN);
//            criteria.add(Restrictions.ilike("ogt.objectGroupName", userAliases.getObjectGroup(), MatchMode.ANYWHERE));
//            criteria.add(Restrictions.eq("ogt.id.countryId", userAliases.getCountryID().getValue()));
//            criteria.setProjection(Projections.distinct(Projections.property("ua.userAliasId")));
//            userAliasIdsList = criteria.list();
//            if (!userAliasIdsList.isEmpty()) {
//                queryString.append(" and userAliasId in (:userAliasIdsList)");
//            } else {
//                return userAliasList;
//            }
//        }
//
//        if (userAliases.getUserAliasTypeID() != null) {
//            queryString.append(" and userAliasTypes.userAliasTypeId = '" + userAliases.getUserAliasTypeID() + "' ");
//        }
//
//        queryString.append(" order by createdDate desc");
//
//        Query query = session.createQuery(queryString.toString());
//        query.setParameterList("groupIdsList", groupIdsList);
//        if (!searchGroupIdsList.isEmpty()) {
//            query.setParameterList("searchGroupIdsList", searchGroupIdsList);
//        }
//        if (!userAliasIdsList.isEmpty()) {
//            query.setParameterList("userAliasIdsList", userAliasIdsList);
//        }
//        userAliasList = query.setMaxResults(200).list();
//        return userAliasList;
//    }
    List<UserAlias> getUserAliasBySearchCriteria(UserAliases userAliases, TingcoUsers tingcoUsers, Session session, String objectTags) {
        List<UserAlias> userAliasList = new ArrayList<UserAlias>();
        StringBuffer queryString = new StringBuffer("select ua.userAliasId as userAliasId, ua.userAlias as userAlias, ua.firstName as firstName, ua.lastName as lastName, ua.groupId as groupId, " +
                " ua.activeToDate as activeToDate, ua.isEnabled as isEnabled, ua.creditAmount as creditAmount, ua.credits as credits, ua.userId as userId from UserAlias as ua " +
                "inner join Groups as g on ua.groupId = g.groupId and g.groupid in (" + TCMUtil.getGroupTrust(userAliases.getGroupID()) + ") ");

        if (userAliases.getUserID() != null) {
            queryString.append(" and ua.userId = '" + userAliases.getUserID() + "'");
        }
        if (userAliases.getGroupName() != null) {
            if (TCMUtil.isValidUUID(userAliases.getGroupName())) {
                queryString.append("inner join GroupTranslations as gts on g.groupId = gts.groupId and gts.groupId = '" + userAliases.getGroupName() + "' and gts.countryId = " + userAliases.getCountryID().getValue() + " ");
            } else {
                queryString.append("inner join GroupTranslations as gts on g.groupId = gts.groupId and gts.groupName like '%" + userAliases.getGroupName() + "%' and gts.countryId = " + userAliases.getCountryID().getValue() + " ");
            }
        }

        if (userAliases.getUserAlias() != null) {
            if (TCMUtil.isValidUUID(userAliases.getUserAlias())) {
                queryString.append(" and ua.userAliasId = '" + userAliases.getUserAlias() + "'");
            } else {
                queryString.append(" and (ua.userAlias like '%" + userAliases.getUserAlias() + "%' or ua.firstName like '%" + userAliases.getUserAlias() + "%' or ua.lastName like '%" + userAliases.getUserAlias() + "%') ");
            }
        }
        if (objectTags != null) {
            queryString.append(" and ua.userAliasId in (select ObjectID from ObjectTags where SearchTags like '%" + objectTags + "%')");
        }

        if (userAliases.getObjectGroup() != null) {
            queryString.append(" inner join OgmUserAlias as ogm on ua.userAliasId = ogm.userAliasId inner join ObjectGroups as og on ogm.objectGroupId = og.objectGroupId " +
                    " inner join ObjectGroupTranslations as ogt on og.objectGroupId = ogt.objectGroupId and ogt.countryId = " + userAliases.getCountryID().getValue() + " ");
            if (TCMUtil.isValidUUID(userAliases.getObjectGroup())) {
                queryString.append(" and ogt.objectGroupId = '" + userAliases.getObjectGroup() + "' ");
            } else {
                queryString.append(" and ogt.objectGroupName like '%" + userAliases.getObjectGroup() + "%' ");
            }
        }

        if (userAliases.getUserAliasTypeID() != null) {
            if (TCMUtil.isValidUUID(userAliases.getUserAliasTypeID())) {
                queryString.append(" and ua.userAliasTypeId = '" + userAliases.getUserAliasTypeID() + "' ");
            } else {
                queryString.append("inner join UserAliasTypes as uat on ua.userAliasTypeId = uat.userAliasTypeId and uat.UserAliasTypeName like '%" + userAliases.getUserAliasTypeID() + "%' ");
            }
        }

        queryString.append(" order by ua.createdDate desc");
        SQLQuery query = session.createSQLQuery(queryString.toString());
        System.out.println(queryString.toString());
        query.addScalar("userAliasId", Hibernate.STRING);
        query.addScalar("userAlias", Hibernate.STRING);
        query.addScalar("firstName", Hibernate.STRING);
        query.addScalar("lastName", Hibernate.STRING);
        query.addScalar("groupId", Hibernate.STRING);
        query.addScalar("activeToDate", Hibernate.TIMESTAMP);
        query.addScalar("isEnabled", Hibernate.INTEGER);
        query.addScalar("creditAmount", Hibernate.DOUBLE);
        query.addScalar("credits", Hibernate.INTEGER);
        query.addScalar("userId", Hibernate.STRING);
        query.setResultTransformer(Transformers.aliasToBean(UserAlias.class));
        userAliasList = query.setMaxResults(200).list();
        return userAliasList;
    }

    public Object getUserAliasDetailsByUserAliasID(Session session, String userAliasId) {
        return session.getNamedQuery("getUserAliasByUserAliasID").setString("userAliasId", userAliasId).uniqueResult();
    }

    public UserAliasDetails getUserAliasDetailsByID(Session session, String userAliasId) {
        return (UserAliasDetails) session.getNamedQuery("getUserAliasDetailsByID").setString("userAliasId", userAliasId).uniqueResult();
    }

    public Currency getCurrencyById(Session session, Integer currencyid) {
        return (Currency) session.getNamedQuery("getCurrencyById").setInteger("currencyId", currencyid).uniqueResult();
    }

    public List<OgmuserAlias> getOgmuserAliasByID(Session session, String userAliasId) {
        return session.getNamedQuery("getOgmuserAliasByID").setString("userAliasId", userAliasId).list();
    }

    public Set<String> getObjectGroupIdsSet(Session session, List<OgmuserAlias> ogmUserAliasList) {
        Set<String> ObjectGroupIDSet = new HashSet<String>();
        for (OgmuserAlias ogmuserAlias : ogmUserAliasList) {
            ObjectGroupIDSet.add(ogmuserAlias.getId().getObjectGroupId());
        }
        return ObjectGroupIDSet;
    }

    public boolean deleteUserKey(Session session, UserAlias useralias, UserAliasDetails uAD, List<OgmuserAlias> oUA) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            if (oUA.size() > 0) {
                for (OgmuserAlias ogmuserAlias : oUA) {
                    session.delete(ogmuserAlias);
                }
            }
            if (uAD != null) {
                session.delete(uAD);
            }
            session.delete(useralias);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    public List<ObjectGroups> getObjectGroupsByGroupId(Set<String> groupIdSet, Session session) {
        return session.getNamedQuery("getObjectGroupsByGroupId").setParameterList("groupId", groupIdSet).setMaxResults(200).list();
    }

    public List<ObjectGroupTranslations> getObjectGroupTranslationsById(Session session, Set<String> objectGroupId, int countryId) {
        return session.getNamedQuery("getObjectGroupTranslationsById").setParameterList("objectGroupId", objectGroupId).setInteger("countryId", countryId).list();
    }

    public List<ObjectGroupTranslations> getObjectGroupTranslationsByIds(Session session, String objectGroupId) {
        return session.getNamedQuery("getObjectGroupTranslationsByObjectGroupId").setString("objectGroupId", objectGroupId).list();
    }

    public boolean updatedUserAlias(Session session, UserAlias userAlias) {
        try {
            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(userAlias);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    public List<Users2> getUsers2BySearchstrings(Session session, Set<String> groupIdSet, String searchString, int maxrecord) {
        return session.getNamedQuery("getUsers2BySearchstrings").setParameterList("groupId", groupIdSet).setString("searchString", searchString).setMaxResults(maxrecord).list();
    }

    Object getUserAliasOrderById(String userAliasOrderId, Session session) {
        return session.getNamedQuery("GetUserAliasOrderById").setString("userAliasOrderId", userAliasOrderId).uniqueResult();
    }

    List<UserAliasOrders> getUserAliasOrdersByIsDelivered(Session session, String isDelivered, String orderComment, int maxResults) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserAliasOrders.class, "uao");
        if (!isDelivered.equalsIgnoreCase("all")) {
            detachedCriteria.add(Restrictions.eq("uao.isDelivered", Integer.valueOf(isDelivered)));
        }
        if (orderComment != null) {
            detachedCriteria.add(Restrictions.like("uao.orderComment", orderComment));
        }
        detachedCriteria.addOrder(Order.desc("uao.createdDate"));
        detachedCriteria.setProjection(Projections.property("uao.userAliasOrderId"));
        List<String> userAliasOrderId = detachedCriteria.getExecutableCriteria(session).setMaxResults(maxResults).list();
        if (!userAliasOrderId.isEmpty()) {
            return getUserAliasOrdersByUserAliasOrderId(session, userAliasOrderId, maxResults);
        } else {
            return null;
        }
    }

    List<UserAliasOrders> getUserAliasOrdersByGroups(Session session, String isDelivered, String groupId, String orderComment, int maxResults) {
        GroupDAO groupDAO = new GroupDAO();
        List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(groupId, session);
        Set<String> groupIdSet = groupDAO.getGroupIdsList(groupId, groupTrustsList);
        List<UserAliasOrders> userAliasOrdersList = new ArrayList<UserAliasOrders>();
        List<List<String>> spList = TCMUtil.splitStringList(new ArrayList<String>(groupIdSet), 2000);
        if (!isDelivered.equalsIgnoreCase("all")) {
            Integer isdelivered = Integer.valueOf(isDelivered);
            for (List<String> list : spList) {
                if (orderComment == null) {
                    List<UserAliasOrders> uaoListTemp = getUserAliasOrdersByUserAliasGroupIdsAndIsDelivered(session, list, isdelivered);
                    if (uaoListTemp != null) {
                        userAliasOrdersList.addAll(uaoListTemp);
                    }
                } else {
                    List<UserAliasOrders> uaoListTemp = getUserAliasOrderByGroupIdsAndIsDeliveredSearch(session, list, isdelivered, orderComment);
                    if (uaoListTemp != null) {
                        userAliasOrdersList.addAll(uaoListTemp);
                    }
                }
            }
        } else {
            for (List<String> list : spList) {
                if (orderComment == null) {
                    List<UserAliasOrders> uaoListTemp = getUserAliasOrdersByUserAliasGroupIds(session, list);
                    if (uaoListTemp != null) {
                        userAliasOrdersList.addAll(uaoListTemp);
                    }
                } else {
                    List<UserAliasOrders> uaoListTemp = getUserAliasOrderByGroupIdsSearch(session, list, orderComment);
                    if (uaoListTemp != null) {
                        userAliasOrdersList.addAll(uaoListTemp);
                    }
                }
            }
        }
        if (!userAliasOrdersList.isEmpty()) {
            if (userAliasOrdersList.size() > maxResults) {
                return userAliasOrdersList.subList(0, maxResults);
            }
        }
        return userAliasOrdersList;
    }

    List<UserAliasOrders> getUserAliasOrdersByUserAliasGroupIds(Session session, List<String> groupId) {
        return session.getNamedQuery("GetUserAliasOrderByGroupIds").setParameterList("groupId", groupId).list();
    }

    List<UserAliasOrders> getUserAliasOrderByGroupIdsSearch(Session session, List<String> groupId, String searchField) {
        return session.getNamedQuery("GetUserAliasOrderByGroupIdsSearch").setParameterList("groupId", groupId).setString("searchField", searchField).list();
    }

    List<UserAliasOrders> getUserAliasOrdersByUserAliasGroupIdsAndIsDelivered(Session session, List<String> groupId, int isDelivered) {
        return session.getNamedQuery("GetUserAliasOrderByGroupIdsAndIsDelivered").setParameterList("groupId", groupId).setInteger("isDelivered", isDelivered).list();
    }

    List<UserAliasOrders> getUserAliasOrderByGroupIdsAndIsDeliveredSearch(Session session, List<String> groupId, int isDelivered, String searchField) {
        return session.getNamedQuery("GetUserAliasOrderByGroupIdsAndIsDeliveredSearch").setParameterList("groupId", groupId).setInteger("isDelivered", isDelivered).setString("searchField", searchField).list();
    }

    List<UserAliasOrders> getUserAliasOrdersByUserAliasOrderId(Session session, List<String> userAliasOrderId, int maxResults) {
        return session.getNamedQuery("GetUserAliasOrderByIds").setParameterList("userAliasOrderId", userAliasOrderId).setMaxResults(maxResults).list();
    }

    List<String> getUsers2ByGroupIdUsingCriteria(String groupId, Session session) {
        Criteria users2Criteria = session.createCriteria(Users2.class, "u");
        users2Criteria.add(Restrictions.eq("u.groupId", groupId));
        users2Criteria.addOrder(Order.desc("u.createdDate"));
        users2Criteria.setProjection(Projections.property("u.userId"));
        return users2Criteria.setMaxResults(200).list();
    }

    public List<String> getUsers2ByGroupIdUsingCriteria(String groupId, String searchString, Session session) {
        List<Users2> users2s = session.getNamedQuery("getUsers2ByGroupIdUsingCriteria").setString("groupId", groupId).setString("searchString", searchString).list();
        List<String> userId = new ArrayList<String>();
        if (users2s.isEmpty()) {
            return userId;
        }

        for (Users2 users2 : users2s) {
            userId.add(users2.getUserId());

        }
        return userId;
    }

    public List<Users2> getUsers2BySearchString(String searchString, List<String> userIdsList, Session session) {
        return session.getNamedQuery("getUsers2BySearchstring").setParameterList("userId", userIdsList).setString("firstName", "%" + searchString + "%").setString("lastName", "%" + searchString + "%").list();
    }

    public List<Users2> getUsers2BySearchstringConcat(String searchString, List<String> userIdsList, Session session) {
        return session.getNamedQuery("getUsers2BySearchstringConcat").setParameterList("userId", userIdsList).setString("searchString", searchString).list();
    }

    List<Object> getUsersListForListUserPage(User user, Session session) {
        StringBuffer queryString = new StringBuffer();
        queryString.append("select u.firstname as firstName, u.lastname as lastName, u.useremail as userEmail, gts.groupname as groupName, ad.addressstreet as addressStreet, " +
                "ad.addresszip as addressZip, ad.addresscity as addressCity, c.countryName as countryName, u.createddate as createdDate from users2 as u " +
                "inner join groups as g on u.groupid = g.groupid and g.groupid in(" + TCMUtil.getGroupTrust(user.getGroupID().getValue()) + ")  " +
                "inner join GroupTranslations as gts on g.groupId = gts.groupId and gts.countryid = " + user.getCountryID().getValue() + " ");

//        user.getGroupID().getValue()
        if (user.getGroupID().getGroupName() != null) {
            if (TCMUtil.isValidUUID(user.getGroupID().getGroupName())) {
                queryString.append(" and gts.groupId = '" + user.getGroupID().getGroupName() + "' and gts.countryId = " + user.getCountryID().getValue() + " ");
            } else {
                queryString.append(" and gts.groupname like '%" + user.getGroupID().getGroupName() + "%' and gts.countryId = " + user.getCountryID().getValue() + " ");
            }
        }

        if (user.getFirstName() != null) {
            queryString.append(" and u.firstName like '%" + user.getFirstName() + "%' ");
        }

        if (user.getLastName() != null) {
            queryString.append(" and u.lastName like '%" + user.getLastName() + "%' ");
        }

        if (user.getUserEmail() != null) {
            queryString.append(" and u.userEmail like '%" + user.getUserEmail() + "%' ");
        }

        /*
         *Don't move the below left joins anywhere in the query. The optional field ad.addresscity and c.countryName will not bound.
         */

        queryString.append("left join objectaddresses oa on u.userid = oa.objectid left join addresses as ad " +
                "on oa.addressid = ad.addressid and ad.addresstypeid = 3 inner join country as c on ad.countryid = c.countryid ");

        if (user.getAddress() != null) {
            queryString.append(" and ad.addressCity like '%" + user.getAddress().getAddressCity() + "%' ");
        }

        if (user.getCountryID().getCountryName() != null) {
            if (user.getCountryID().getCountryName().matches("[0-9]+")) {
                queryString.append(" and c.countryId  = " + user.getCountryID().getCountryName() + " ");
            } else {
                queryString.append(" and c.countryName like '%" + user.getCountryID().getCountryName() + "%' ");
            }
        }
        queryString.append(" order by u.firstName asc, u.lastName asc ");
        System.out.println(queryString.toString());
        SQLQuery query = session.createSQLQuery(queryString.toString());
        TCMUtil.loger(getClass().getName(), query.toString(), "info");
        System.out.println(query.toString());
        query.addScalar("firstName", Hibernate.STRING);
        query.addScalar("lastName", Hibernate.STRING);
        query.addScalar("userEmail", Hibernate.STRING);
        query.addScalar("groupName", Hibernate.STRING);
        query.addScalar("addressStreet", Hibernate.STRING);
        query.addScalar("addressZip", Hibernate.STRING);
        query.addScalar("addressCity", Hibernate.STRING);
        query.addScalar("countryName", Hibernate.STRING);
        query.addScalar("createdDate", Hibernate.TIMESTAMP);
        return query.setMaxResults(2000).list();
    }

    List<Object> getUsersListForSearchUsersPage(User user, Session session) {
        StringBuffer queryString = new StringBuffer();
        queryString.append("select distinct u.userID as UserID, u.firstname as firstName, u.lastname as lastName, u.useremail as userEmail, gts.groupname as groupName, u.isLockedOut as isLockedOut, " +
                "u.lastLoginDate as lastLoginDate from users2 as u inner join groups as g on u.groupid = g.groupid and g.groupid " +
                "in(" + TCMUtil.getGroupTrust(user.getGroupID().getValue()) + ")  inner join GroupTranslations as gts on g.groupId = gts.groupId " +
                "and gts.countryid = " + user.getCountryID().getValue() + " ");

        if (user.getGroupID().getGroupName() != null) {
            if (TCMUtil.isValidUUID(user.getGroupID().getGroupName())) {
                queryString.append(" and gts.groupId = '" + user.getGroupID().getGroupName() + "' and gts.countryId = " + user.getCountryID().getValue() + " ");
            } else {
                queryString.append(" and gts.groupname like '%" + user.getGroupID().getGroupName() + "%' and gts.countryId = " + user.getCountryID().getValue() + " ");
            }
        }

        if (user.getFirstName() != null) {
            queryString.append(" and u.firstName like '%" + user.getFirstName() + "%' ");
        }

        if (user.getLastName() != null) {
            queryString.append(" and u.lastName like '%" + user.getLastName() + "%' ");
        }

        if (user.getUserEmail() != null) {
            queryString.append(" and u.userEmail like '%" + user.getUserEmail() + "%' ");
        }

        /*
         *Don't move the below left joins anywhere in the query. The optional field ad.addresscity and c.countryName will not bound.
         */

        if (user.getAddress() != null || user.getCountryID().getCountryName() != null) {

            queryString.append("left join objectaddresses oa on u.userid = oa.objectid left join addresses as ad " +
                    "on oa.addressid = ad.addressid inner join country as c on ad.countryid = c.countryid ");

        }
        if (user.getAddress() != null) {
            queryString.append(" and ad.addressCity like '%" + user.getAddress().getAddressCity() + "%' ");
        }

        if (user.getCountryID().getCountryName() != null) {
            if (user.getCountryID().getCountryName().matches("[0-9]+")) {
                queryString.append(" and c.countryId  = " + user.getCountryID().getCountryName() + " ");
            } else {
                queryString.append(" and c.countryName like '%" + user.getCountryID().getCountryName() + "%' ");
            }
        }
        queryString.append(" order by u.firstName asc, u.lastName asc ");
        System.out.println(queryString.toString());
        SQLQuery query = session.createSQLQuery(queryString.toString());
        TCMUtil.loger(getClass().getName(), query.toString(), "info");
        System.out.println(query.toString());
        query.addScalar("UserID", Hibernate.STRING);
        query.addScalar("firstName", Hibernate.STRING);
        query.addScalar("lastName", Hibernate.STRING);
        query.addScalar("userEmail", Hibernate.STRING);
        query.addScalar("groupName", Hibernate.STRING);
        query.addScalar("isLockedOut", Hibernate.INTEGER);
        query.addScalar("lastLoginDate", Hibernate.TIMESTAMP);
        return query.setMaxResults(2000).list();
    }

    boolean updateCountryId(String userId, String countryId, Session session) {
        List<Users2> users2 = session.getNamedQuery("getUsers2ById").setString("userID", userId).list();
        Transaction tx = null;
        boolean result = false;
        if (!users2.isEmpty()) {
            for (Users2 u : users2) {
                u.setCountryId(Integer.valueOf(countryId));
                try {
                    tx = session.beginTransaction();
                    session.saveOrUpdate(u);
                    tx.commit();
                    result = true;
                } catch (Exception e) {
                    tx.rollback();
                    TCMUtil.exceptionLog(getClass().getName(), e);
                    result = false;
                }
            }
        }
        return result;
    }

    boolean updateUserInformation(String userId, String firstName, String lastName, String groupId, String emailId, String countryId, String activeFromDate, String activeToDate, String lockedOut, String userTypeId, String userMobilePhone, Session session, String timeZoneGMToffset, String addressStreet, String addressStreet2, String addressSuite, String addressZip, String addressCity, String addressDesc, String addressRegion, String addressId, String addressCountryId) {
        Transaction tx = null;
        boolean result = true;
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            tx = session.beginTransaction();
            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
            GregorianCalendar gc1 = new GregorianCalendar();
            Query query = null;
            StringBuffer queryString = new StringBuffer("update Users2 set loginName = :loginName, firstName = :firstName, lastName = :lastName, groupId = :groupId, userEmail = :userEmail, countryId = :countryId, activeFromDate = :activeFromDate, activeToDate = :activeToDate");
            if (lockedOut.equalsIgnoreCase("0")) {
                queryString.append(", isLockedOut = :isLockedOut, failedPasswordAttemptCount =:failedPasswordAttemptCount ");
                queryString.append(", userTypeId = :userTypeId, userMobilePhone = :userMobilePhone, updatedDate = :updatedDate where userID = :userID");
                query = session.createQuery(queryString.toString());
                query.setParameter("isLockedOut", Integer.valueOf(lockedOut));
                query.setParameter("failedPasswordAttemptCount", Integer.valueOf(lockedOut));
            } else {
                queryString.append(", isLockedOut = :isLockedOut");
                queryString.append(", userTypeId = :userTypeId, userMobilePhone = :userMobilePhone, updatedDate = :updatedDate where userID = :userID");
                query = session.createQuery(queryString.toString());
                query.setParameter("isLockedOut", Integer.valueOf(lockedOut));
            }

//            Query query = session.createQuery("update Users2 set loginName = :loginName, firstName = :firstName, lastName = :lastName, groupId = :groupId, userEmail = :userEmail, countryId = :countryId, activeFromDate = :activeFromDate, activeToDate = :activeToDate, isLockedOut = :isLockedOut, userTypeId = :userTypeId, userMobilePhone = :userMobilePhone, updatedDate = :updatedDate where userID = :userID");
            query.setParameter("loginName", emailId);
            query.setParameter("firstName", firstName);
            query.setParameter("lastName", lastName);
            query.setParameter("groupId", groupId);
            query.setParameter("userEmail", emailId);
            query.setParameter("countryId", Integer.valueOf(countryId));
//            gc.setTimeInMillis(Long.valueOf(activeFromDate));
            gc.setTime(df.parse(activeFromDate));
            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
            query.setParameter("activeFromDate", gc.getTime());
//            gc.setTimeInMillis(Long.valueOf(activeToDate));
            gc.setTime(df.parse(activeToDate));
            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
            query.setParameter("activeToDate", gc.getTime());

            query.setParameter("userTypeId", userTypeId);
            query.setParameter("updatedDate", gc1.getTime());
            query.setParameter("userMobilePhone", userMobilePhone);
            query.setParameter("userID", userId);
            query.executeUpdate();

            ObjectAddresses oa = getObjectAddressesByID(session, userId, "3");
            if (oa == null) {
                if (addressId != null) {
                    TCMUtil.loger(getClass().getName(), "1", "Info");
                    Addresses address = (Addresses) session.get(Addresses.class, addressId);
                    AddressType at = (AddressType) session.get(AddressType.class, 3);
                    address.setAddressType(at);
                    address.setCountry((Country) session.get(Country.class, Integer.valueOf(addressCountryId)));
                    if (addressRegion != null) {
                        address.setAddressRegion(addressRegion);
                    } else {
                        address.setAddressRegion(null);
                    }
                    if (addressStreet != null) {
                        address.setAddressStreet(addressStreet);
                    } else {
                        address.setAddressStreet(null);
                    }
                    if (addressStreet2 != null) {
                        address.setAddressStreet2(addressStreet2);
                    } else {
                        address.setAddressStreet2(null);
                    }
                    if (addressSuite != null) {
                        address.setAddressSuite(addressSuite);
                    } else {
                        address.setAddressSuite(null);
                    }
                    if (addressZip != null) {
                        address.setAddressZip(addressZip);
                    } else {
                        address.setAddressZip(null);
                    }
                    if (addressCity != null) {
                        address.setAddressCity(addressCity);
                    } else {
                        address.setAddressCity(null);
                    }
                    if (addressDesc != null) {
                        address.setAddressDescription(addressDesc);
                    } else {
                        address.setAddressDescription(null);
                    }
                    address.setUpdatedDate(gc.getTime());
                    session.saveOrUpdate(address);

                    oa.setAddressId(addressId);
                    oa.setAddressType(at);
                    ObjectAddressesId ids = new ObjectAddressesId();
                    ids.setAddressTypeId(3);
                    ids.setObjectId(userId);
                    oa.setId(ids);
                    oa.setLastUpdatedUserId(userId);
                    oa.setCreatedDate(gc.getTime());
                    oa.setUpdatedDate(gc.getTime());
                    session.update(address);
                    session.saveOrUpdate(oa);
                } else {
                    TCMUtil.loger(getClass().getName(), "2", "Info");
                    Addresses address = null;
                    if (addressStreet != null || addressStreet2 != null || addressSuite != null || addressZip != null || addressCity != null || addressDesc != null || addressRegion != null) {
                        address = new Addresses();
                        oa = new ObjectAddresses();
                        String addressid = UUID.randomUUID().toString();
                        address.setAddressId(addressid);
                        AddressType at = (AddressType) session.get(AddressType.class, 3);
                        address.setAddressType(at);
                        address.setCountry((Country) session.get(Country.class, Integer.valueOf(addressCountryId)));
                        if (addressRegion != null) {
                            address.setAddressRegion(addressRegion);
                        }
                        if (addressStreet != null) {
                            address.setAddressStreet(addressStreet);
                        }
                        if (addressStreet2 != null) {
                            address.setAddressStreet2(addressStreet2);
                        }
                        if (addressSuite != null) {
                            address.setAddressSuite(addressSuite);
                        }
                        if (addressZip != null) {
                            address.setAddressZip(addressZip);
                        }
                        if (addressCity != null) {
                            address.setAddressCity(addressCity);
                        }
                        if (addressDesc != null) {
                            address.setAddressDescription(addressDesc);
                        }
                        address.setUpdatedDate(gc.getTime());
                        address.setCreatedDate(gc.getTime());

                        oa.setAddressId(addressid);
                        oa.setAddressType(at);
                        ObjectAddressesId ids = new ObjectAddressesId();
                        ids.setAddressTypeId(3);
                        ids.setObjectId(userId);
                        oa.setId(ids);
                        oa.setLastUpdatedUserId(userId);
                        oa.setCreatedDate(gc.getTime());
                        oa.setUpdatedDate(gc.getTime());
                        session.saveOrUpdate(address);
                        session.saveOrUpdate(oa);
                    }
                }
            } else {
                if (addressId == null) {
                    TCMUtil.loger(getClass().getName(), "3", "Info");
                    Addresses add = (Addresses) session.get(Addresses.class, oa.getAddressId());
                    session.delete(add);
                    session.delete(oa);
                } else {
                    TCMUtil.loger(getClass().getName(), "4", "Info");
                    Object object = (Addresses) getAddress(addressId, session);
                    Addresses address = (Addresses) object;
                    AddressType at = (AddressType) session.get(AddressType.class, 3);
                    address.setAddressType(at);
                    address.setCountry((Country) session.get(Country.class, Integer.valueOf(addressCountryId)));
                    if (addressRegion != null) {
                        address.setAddressRegion(addressRegion);
                    } else {
                        address.setAddressRegion(null);
                    }
                    if (addressStreet != null) {
                        address.setAddressStreet(addressStreet);
                    } else {
                        address.setAddressStreet(null);
                    }
                    if (addressStreet2 != null) {
                        address.setAddressStreet2(addressStreet2);
                    } else {
                        address.setAddressStreet2(null);
                    }
                    if (addressSuite != null) {
                        address.setAddressSuite(addressSuite);
                    } else {
                        address.setAddressSuite(null);
                    }
                    if (addressZip != null) {
                        address.setAddressZip(addressZip);
                    } else {
                        address.setAddressZip(null);
                    }
                    if (addressCity != null) {
                        address.setAddressCity(addressCity);
                    } else {
                        address.setAddressCity(null);
                    }
                    if (addressDesc != null) {
                        address.setAddressDescription(addressDesc);
                    } else {
                        address.setAddressDescription(null);
                    }
                    address.setUpdatedDate(gc.getTime());
                    session.update(address);
                }

            }

            tx.commit();
            result = true;
        } catch (Exception e) {
            tx.rollback();
            TCMUtil.exceptionLog(getClass().getName(), e);
            result = false;
        }

        return result;
    }

    boolean updateUserProfileDetails(String loginname, String domainID, String userID, String firstName, String lastName, String countryId, String emailId, String userMobilePhone, Session session) {
        Transaction tx = null;
        boolean result = true;
        try {
            GregorianCalendar gc = new GregorianCalendar();
            tx =
                    session.beginTransaction();
            Query query = session.createQuery("update Users2 set loginName = :loginName, firstName = :firstName, lastName = :lastName, userEmail = :userEmail, countryId = :countryId, updatedDate = :updatedDate where domainID = :domainID and userID = :userID");
            query.setParameter("loginName", emailId);
            query.setParameter("firstName", firstName);
            query.setParameter("lastName", lastName);
            query.setParameter("userEmail", emailId);
            query.setParameter("countryId", Integer.valueOf(countryId));
            query.setParameter("updatedDate", gc.getTime());
            query.setParameter("domainID", domainID);
            query.setParameter("userID", userID);
            query.executeUpdate();
            tx.commit();
            tx =
                    session.beginTransaction();
            if (!userMobilePhone.equals("")) {
                query = session.createQuery("update Users2 set userMobilePhone = :userMobilePhone where domainID = :domainID and userID = :userID");
                query.setParameter("userMobilePhone", userMobilePhone);
                query.setParameter("domainID", domainID);
                query.setParameter("userID", userID);
                query.executeUpdate();
                tx.commit();
            }

            result = true;
        } catch (Exception e) {
            tx.rollback();
            TCMUtil.exceptionLog(getClass().getName(), e);
            result =
                    false;
        }

        return result;
    }

    public List<Users2> getUsersByGroupIdsList(Set<String> groupIdList, Session session) {
        try {
            return session.getNamedQuery("getUsers2ByGroupIdList").setParameterList("groupId", groupIdList).setMaxResults(100).list();
        } catch (HibernateException e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return null;
        }

    }

    public List<Users2> getUsers2ById(String userID, Session session) {
        try {
            return session.getNamedQuery("getUsers2ByUserId").setString("userId", userID).list();
        } catch (HibernateException e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return null;
        }

    }

    public List<GroupTranslations> getGroupTranslationsById(String groupid, String countryid, Session session) {
        try {
            return session.getNamedQuery("getGroupTranslationsById").setString("groupID", groupid).setString("countryID", countryid).list();
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return null;
        }

    }

    public UserTypeTranslations2 getUserTypeTranslations2ById(
            String userTypeId, int countryid, Session session) {
        return (UserTypeTranslations2) session.getNamedQuery("getUserTypeTranslationsByIds").setString("userTypeId", userTypeId).setInteger("countryId", countryid).uniqueResult();
    }

    public List<Users2> getSearchedUser(List<Users2> users2List, String searchFilter, Session session) {
        List<String> userIdList = getUserIdsList(users2List);
        List<String> usersidslist = new ArrayList<String>();
        try {
            List<Users2> usersList = new ArrayList<Users2>();
            if (TCMUtil.isValidUUID(searchFilter)) {
                usersList = getUsers2ById(searchFilter, session);
            } else {
                List<List<String>> lists = split(userIdList, 2000);
                for (List<String> list : lists) {
//                    if (usersList.size() < 200) {
                    List<Users2> temp = new ArrayList<Users2>();
                    temp =
                            session.getNamedQuery("getUsers2BySearchFilter").setParameterList("userId", list).setString("firstName", "%" + searchFilter + "%").setString("lastName", "%" + searchFilter + "%").setString("loginName", "%" + searchFilter + "%").setString("userEmail", "%" + searchFilter + "%").setString("userMobilePhone", "%" + searchFilter + "%").list();
                    usersList.addAll(temp);
//                    }
                }

            }
            if (!usersList.isEmpty()) {
                Collections.sort(usersList, new Comparator<Users2>() {

                    public int compare(Users2 o1, Users2 o2) {
                        return o2.getCreatedDate().compareTo(o1.getCreatedDate());
                    }
                });
                usersidslist =
                        getUserIdsList(usersList);
            }

            userIdList = getUserAliasBySearch(searchFilter, userIdList, session);


            if (!userIdList.isEmpty()) {
                usersidslist.addAll(userIdList);
            }

            if (!usersidslist.isEmpty()) {
                List<Users2> users2s = getSearchUser(usersidslist, session);
                if (users2s.size() > 200) {
                    return users2s.subList(0, 200);
                } else {
                    return users2s;
                }
//                return getSearchUser(usersidslist, session);

            } else {
                return null;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return null;
        }

    }

    public List<Users2> getSearchUser(List<String> userIdForSortingList, Session session) {
        List<Users2> userIdForSortingLists = new ArrayList<Users2>();
//        for (Users2 u : users2List) {
//            userIdForSortingList.add(u.getUserId());
//        }
        List<List<String>> lists = split(userIdForSortingList, 2000);
        for (List<String> list : lists) {
            userIdForSortingLists.addAll(getUser2ListByUserId(list, session));
        }

        return userIdForSortingLists;
    }

    public List<String> getUserIdsList(List<Users2> users2List) {
        List<String> userIdList = new ArrayList<String>();
        for (Users2 u : users2List) {
            userIdList.add(u.getUserId());
        }

        return userIdList;
    }

    public List<String> getUserAliasBySearch(String searchString, List<String> userIdList, Session session) {
        List<List<String>> lists = split(userIdList, 2000);
        List<UserAlias> ualist = new ArrayList();
        for (List<String> list : lists) {
            List<UserAlias> temp = session.getNamedQuery("getUserAliasBySearchFilter").setString("searchString", searchString).setParameterList("userId", list).list();
            ualist.addAll(temp);
        }

        List<String> userid = new ArrayList<String>();
        if (!ualist.isEmpty()) {
            for (UserAlias ua : ualist) {
                userid.add(ua.getUserId());
            }

        }
        return userid;
    }

    public boolean UpdateUserKeyDetails(Session session, UserAlias userAlias, UserAliasDetails userAliasDetails) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(userAlias);
            session.saveOrUpdate(userAliasDetails);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }

            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }

    }

    public boolean AddUserAlias(Session session, UserAlias useraliases, UserAliasDetails userAliasDetail, List<OgmuserAlias> ogmuserAliasList, ObjectTags objectTagsPojo) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(useraliases);
            session.saveOrUpdate(userAliasDetail);
            for (OgmuserAlias ogmuserAlias : ogmuserAliasList) {
                session.merge(ogmuserAlias);
//                session.flush();
            }

            if (objectTagsPojo != null) {
                session.save(objectTagsPojo);
            }

            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }

            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }

    }

    public Object getUserAliasTypesByID(
            Session session, String userAliasTypeId) {
        return session.getNamedQuery("getUserAliasTypesByID").setString("userAliasTypeId", userAliasTypeId).uniqueResult();
    }

    public List<AddressTypeTranslations> getAddressTypeTranslationsByCountryId(Session session, String countryId) {
        return session.getNamedQuery("getAddressTypeTranslationsByCountryId").setParameter("countryId", Integer.parseInt(countryId)).list();
    }

    public ObjectAddresses getObjectAddressesByID(
            Session session, String objectId, String addressTypeId) {
        return (ObjectAddresses) session.getNamedQuery("getObjectAddressesByID").setString("objectId", objectId).setString("addressTypeId", addressTypeId).uniqueResult();
    }

    public Object getAddress(
            String addressId, Session session) {
        return session.getNamedQuery("getAddressById").setString("addressID", addressId).uniqueResult();
    }

    public List<UserAlias> getUserAliasByUserAliasTypeIdAndUserAlias(Session session, String userAlias, String userAliasTypeId) {
        return session.getNamedQuery("getUserAliasByUserAliasTypeIdAndUserAlias").setString("userAlias", userAlias).setString("userAliasTypeId", userAliasTypeId).list();
    }

    public UserTypes2 getUserTypes2ById(
            Session session, String userTypeId) {
        return (UserTypes2) session.getNamedQuery("getUserTypes2ById").setString("userTypeId", userTypeId).uniqueResult();
    }

    public AddressType getAddresstypeById(
            Session session, int addressTypeId) {
        return (AddressType) session.getNamedQuery("getAddresstypeById").setInteger("addressTypeId", addressTypeId).uniqueResult();
    }

    public List<UserAlias> getUserAliasBySearchString(Session session, String searchString, Set<String> groupIdSet) {
        return session.getNamedQuery("getUserAliasBySearchString").setString("searchString", searchString).setParameterList("groupId", groupIdSet).list();

    }

    public List<UserRoles2> getUserRoleByIDs(Session session, List<String> userRoleID, int maxResults) {
        return session.getNamedQuery("getUserRoleByIDs").setParameterList("userRoleID", userRoleID).setMaxResults(maxResults).list();
    }

    public UserRolesInGroups2 getUserRolesInGroups2ByRoleAndGroupID(
            Session session, String groupID, String userRoleID) {
        return (UserRolesInGroups2) session.getNamedQuery("getUserRolesInGroups2ByRoleAndGroupID").setParameter("userRoleID", userRoleID).setParameter("groupID", groupID).uniqueResult();
    }

    public List<Users2> getUsers2ByGroupIdListorderByFirstNameLastName(Session session, List<String> groupIdList, int maxresult) {
        if (maxresult == 0) {
            return session.getNamedQuery("getUsers2ByGroupIdListorderByFirstNameLastName").setParameterList("groupId", groupIdList).list();
        } else {
            return session.getNamedQuery("getUsers2ByGroupIdListorderByFirstNameLastName").setParameterList("groupId", groupIdList).setMaxResults(maxresult).list();
        }

    }

    public List<UserLog> getUserLogDetail(Session session, TingcoMeasurementTypes measurementTypes) {
        List<UserLog> mdlist = new ArrayList<UserLog>();

        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        GregorianCalendar gc_diff = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
        long lMillis = gc.getTimeInMillis();
        Date lDate = new Date(lMillis);
        String gc_time = lFormat.format(lDate);
        String userId = measurementTypes.getUserId();
        String timePeriod = measurementTypes.getMeasurementDatas().getTimePeriod();
        if (timePeriod != null) {
            if (timePeriod.equalsIgnoreCase("100")) {
                mdlist = session.getNamedQuery("getUserLogByUserIDTop100").setString("userId", userId).setMaxResults(100).list();
            } else if (timePeriod.equalsIgnoreCase("hour")) {
                gc_diff.add(GregorianCalendar.HOUR, -1);
                long gc_diff_Millis = gc_diff.getTimeInMillis();
                Date gc_diff_lDate = new Date(gc_diff_Millis);
                String gc_diff_time = lFormat.format(gc_diff_lDate);
                mdlist =
                        session.getNamedQuery("getUserLogByUserID").setString("userId", userId).setString("fromDate", gc_diff_time).setString("toDate", gc_time).setMaxResults(200).list();
            } else if (timePeriod.equalsIgnoreCase("day")) {
                gc_diff.add(GregorianCalendar.DATE, -1);
                long gc_diff_Millis = gc_diff.getTimeInMillis();
                Date gc_diff_lDate = new Date(gc_diff_Millis);
                String gc_diff_time = lFormat.format(gc_diff_lDate);
                mdlist =
                        session.getNamedQuery("getUserLogByUserID").setString("userId", userId).setString("fromDate", gc_diff_time).setString("toDate", gc_time).setMaxResults(200).list();
            } else if (timePeriod.equalsIgnoreCase("week")) {
                gc_diff.add(GregorianCalendar.DATE, -7);
                long gc_diff_Millis = gc_diff.getTimeInMillis();
                Date gc_diff_lDate = new Date(gc_diff_Millis);
                String gc_diff_time = lFormat.format(gc_diff_lDate);
                mdlist =
                        session.getNamedQuery("getUserLogByUserID").setString("userId", userId).setString("fromDate", gc_diff_time).setString("toDate", gc_time).setMaxResults(200).list();
            } else if (timePeriod.equalsIgnoreCase("month")) {
                gc_diff.add(GregorianCalendar.MONTH, -1);
                long gc_diff_Millis = gc_diff.getTimeInMillis();
                Date gc_diff_lDate = new Date(gc_diff_Millis);
                String gc_diff_time = lFormat.format(gc_diff_lDate);
                mdlist =
                        session.getNamedQuery("getUserLogByUserID").setString("userId", userId).setString("fromDate", gc_diff_time).setString("toDate", gc_time).setMaxResults(200).list();
            }

        } else if (measurementTypes.getMeasurementDatas().getFromDate() != null && measurementTypes.getMeasurementDatas().getToDate() != null) {
            String gc_diff_time = measurementTypes.getMeasurementDatas().getFromDate();
            gc_time =
                    measurementTypes.getMeasurementDatas().getToDate();
            mdlist =
                    session.getNamedQuery("getUserLogByUserID").setString("userId", userId).setString("fromDate", gc_diff_time).setString("toDate", gc_time).setMaxResults(200).list();
        }

        return mdlist;
    }

    public List<UserAlias> getUserAliasByTrustedGroupIDs(Set<String> groupTrustedIDSet, Session session, int maxResult) {
        return session.getNamedQuery("getUserAliasByTrustedGroupIDs").setParameterList("groupId", groupTrustedIDSet).setMaxResults(maxResult).list();
    }

    public List<UserAlias> getUserAliasBySearchStringAndGroupID(Set<String> groupTrustedIDSet, String searchString, Session session, int maxResult) {
        return session.getNamedQuery("getUserAliasBySearchStringAndGroupID").setString("searchString", searchString).setParameterList("groupId", groupTrustedIDSet).setMaxResults(maxResult).list();
    }

    public <T extends Object> List<List<T>> split(List<T> list, int targetSize) {
        List<List<T>> lists = new ArrayList<List<T>>();
        for (int i = 0; i <
                list.size(); i +=
                        targetSize) {
            lists.add(list.subList(i, Math.min(i + targetSize, list.size())));
        }

        return lists;
    }

    Object getBlacklistByBlacklistID(String blackListId, Session session) {
        return session.getNamedQuery("getBlacklistByBlacklistID").setString("blacklistId", blackListId).uniqueResult();
    }

    void deleteBlackList(Blacklist blacklist, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            tx.begin();
            session.delete(blacklist);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }

            TCMUtil.exceptionLog(getClass().getName(), e);
        }

    }

    boolean saveOrUpdateDeviceUsers(DeviceUsers deviceUsers, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            tx.begin();
            session.saveOrUpdate(deviceUsers);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            if (tx != null) {
                tx.rollback();
            }

            return false;
        }

    }

    boolean deleteDeviceUsers(DeviceUsers deviceUsers, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            tx.begin();
            session.delete(deviceUsers);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }

            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }

    }

    boolean saveOrUpdateUserAlias(UserAlias userAlias, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            tx.begin();
            session.update(userAlias);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }

            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }

    }

    public List<Object> getUserKeyByGroup(Session session, String userId, String groupid, String searchString) {
        String querystString = "select ua.UserAliasID as UserAliasID, ua.UserAlias as UserAlias, ua.FirstName as FirstName, ua.LastName as LastName from UserAlias as ua where (ua.UserID <> '" + userId + "' or ua.UserID is null)and ua.GroupID in(" + TCMUtil.getGroupTrust(groupid) + ") ";
        if (searchString != null) {
            querystString += " and (ua.UserAlias like '%" + searchString + "%' or ua.FirstName like '%" + searchString + "%' or ua.LastName like '%" + searchString + "%')";
        }

        querystString += "  order by ua.UserAlias, ua.FirstName, ua.LastName";

        SQLQuery query = session.createSQLQuery(querystString);
        query.addScalar("UserAliasID", Hibernate.STRING);
        query.addScalar("UserAlias", Hibernate.STRING);
        query.addScalar("FirstName", Hibernate.STRING);
        query.addScalar("LastName", Hibernate.STRING);
        List<Object> objectList = query.setMaxResults(200).list();
        return objectList;
    }

    public List<OgmuserAlias> getOgmuserAliasByIDandGroupID(Session session, String userAlias, String objectGroupID) {
        return session.getNamedQuery("getOgmuserAliasByIDandGroupID").setString("userAliasId", userAlias).setString("objectGroupId", objectGroupID).list();
    }

    Object getObjectTagsById(Session session, String userAliasId) {
        return session.getNamedQuery("getObjectTagsByObjectId").setString("userAliasID", userAliasId).uniqueResult();
    }

    public Object getUsers2ByUserId(
            Session session, String userId) {
        return session.getNamedQuery("getUsers2ByUserId").setString("userId", userId).uniqueResult();
    }

    List<Blacklist> getBlacklistByStartValue(String userAlias, Session session) {
        return session.getNamedQuery("getBlacklistByStartValue").setString("userAlias", userAlias).list();
    }
}

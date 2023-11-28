/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.group;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import se.info24.application.ApplicationDAO;
import se.info24.device.DeviceDAO;
import se.info24.groupsjaxb.Address;
import se.info24.groupsjaxb.Group;
import se.info24.groupsjaxb.GroupApplicationPackage;
import se.info24.groupsjaxb.GroupName;
import se.info24.groupsjaxb.GroupNames;
import se.info24.groupsjaxb.GroupVisibleApplicationPackages;
import se.info24.groupsjaxb.GroupVisibleApplicationSolutions;
import se.info24.groupsjaxb.ObjectFactory;
import se.info24.groupsjaxb.TingcoGroups;
import se.info24.ismOperationsPojo.AccessLog;
import se.info24.ismOperationsPojo.TransactionResult;
import se.info24.measurementTypes.MeasurementDAO;
import se.info24.network.NetworkDAO;
import se.info24.pojo.AddressType;
import se.info24.pojo.Addresses;
import se.info24.pojo.Agreements;
import se.info24.pojo.ApplicationGroupMemberships;
import se.info24.pojo.ApplicationGroupMembershipsId;
import se.info24.pojo.ApplicationPackageTranslations;
import se.info24.pojo.ApplicationPackages;
import se.info24.pojo.ApplicationSettings;
import se.info24.pojo.ApplicationSolutionTranslations;
import se.info24.pojo.ContactFieldTranslations;
import se.info24.pojo.ContactGroups;
import se.info24.pojo.ContactGroupsInContacts;
import se.info24.pojo.ContactTypeDefaultFields;
import se.info24.pojo.ContactTypeDetails;
import se.info24.pojo.ContactTypeTranslations;
import se.info24.pojo.ContactTypesInContacts;
import se.info24.pojo.ContactTypesInGroups;
import se.info24.pojo.Contacts;
import se.info24.pojo.CostCenters;
import se.info24.pojo.Country;
import se.info24.pojo.Device;
import se.info24.pojo.EventTypeTranslations;
import se.info24.pojo.Faq;
import se.info24.pojo.GroupAddresses;
import se.info24.pojo.GroupAlias;
import se.info24.pojo.GroupApplicationPackages;
import se.info24.pojo.GroupApplicationPackagesId;
import se.info24.pojo.GroupDefaultAgreement;
import se.info24.pojo.GroupDefaultUserAlias;
import se.info24.pojo.GroupLimitPackages;
import se.info24.pojo.GroupLimits;
import se.info24.pojo.GroupObjectSettingPackages;
import se.info24.pojo.GroupProductComponents;
import se.info24.pojo.GroupProductVariantSubscriptions;
import se.info24.pojo.GroupSettings;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupTranslationsId;
import se.info24.pojo.GroupTrusts;
import se.info24.pojo.GroupTrustsId;
import se.info24.pojo.GroupTypeTranslations;
import se.info24.pojo.GroupVisibleApplicationModules;
import se.info24.pojo.GroupVisibleApplicationPackagesId;
import se.info24.pojo.GroupVisibleApplicationSolutionsId;
import se.info24.pojo.GroupWeekdays;
import se.info24.pojo.Groups;
import se.info24.pojo.GroupsFirstDayOfWeek;
import se.info24.pojo.MeasurementTypesInGroups;
import se.info24.pojo.NetworkSubscriptions;
import se.info24.pojo.ObjectAddresses;
import se.info24.pojo.ObjectAddressesId;
import se.info24.pojo.ObjectGroupTranslations;
import se.info24.pojo.ObjectGroups;
import se.info24.pojo.Ogmcontainers;
import se.info24.pojo.Ogmdevice;
import se.info24.pojo.OgmuserAlias;
import se.info24.pojo.ProductsInGroups;
import se.info24.pojo.RoutingTable;
import se.info24.pojo.ServiceClientLogins;
import se.info24.pojo.ServiceTypesInGroups;
import se.info24.pojo.Services;
import se.info24.pojo.ServicesVisibleToGroup;
import se.info24.pojo.SupportCases;
import se.info24.pojo.SupportOrgVisibleToGroups;
import se.info24.pojo.UserAlias;
import se.info24.pojo.UserAliasTypesInGroups;
import se.info24.pojo.UserFavoriteGroups;
import se.info24.pojo.UserFavoriteGroupsId;
import se.info24.pojo.UserGroupMemberships2;
import se.info24.pojo.UserGroupMemberships2Id;
import se.info24.pojo.UserRoleMemberships2;
import se.info24.pojo.UserRolesInGroups2;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.Users2;
import se.info24.priceplans.PricePlanDAO;
import se.info24.products.ProductsDAO;
import se.info24.restUtil.RestUtilDAO;
import se.info24.service.ServiceDAO;
import se.info24.user.CountryDAO;
import se.info24.user.UserDAO;
import se.info24.usersjaxb.GroupID;
import se.info24.usersjaxb.TingcoUsers;
import se.info24.usersjaxb.User;
import se.info24.usersjaxb.UserGroupMemberships;
import se.info24.usersjaxb.Users;
import se.info24.util.DbManager;
import se.info24.util.TCMUtil;

/**
 *
 * @author Sridhar Dasari
 */
public class GroupDAO {

    public List<ContactTypeDefaultFields> getContactTypeDefaultFieldsByContactTypeId(String contactTypeId, Session session) {
        return session.getNamedQuery("getContactTypeDefaultFieldsByContactTypeID").setString("contactTypeId", contactTypeId).list();
    }

    public ContactTypesInContacts getContactTypesInContacts(String contactId, String contactTypeId, Session session) {
        return (ContactTypesInContacts) session.getNamedQuery("").setString("contactId", contactId).setString("contactTypeId", contactTypeId).uniqueResult();
    }

    public List<String> getGroupidsListFromGroupTranslations(List<GroupTranslations> groupTranslationsList) {
        List<String> groupIdsList = new ArrayList<String>();
        for (GroupTranslations gt : groupTranslationsList) {
            groupIdsList.add(gt.getGroups().getGroupId());
        }
        return groupIdsList;
    }

    public ContactFieldTranslations getcontactFieldTranslationsByIds(String contactTypeFieldId, Integer countryId, Session session) {
        return (ContactFieldTranslations) session.getNamedQuery("getContactFieldTranslationsByIds").setString("contactTypeFieldId", contactTypeFieldId).setInteger("countryId", countryId).uniqueResult();
    }

    public List<ContactTypeDetails> getcontactTypeDetailsByIds(String contactTypeInCoId, String contactTypeFieldId, Session session) {
        return session.getNamedQuery("getContactTypeDetailsByIds").setString("contactTypeInCoId", contactTypeInCoId).setString("contactTypeFieldId", contactTypeFieldId).list();
    }

    protected void addGroup(String groupName, String groupTypeID, String grpParID, String grp_desc, String appID, String userID, Session session) throws DatatypeConfigurationException {
        Transaction tx = session.beginTransaction();
        Groups group = new Groups();
        CountryDAO countryDAO = new CountryDAO();
        String groupID = "";
        if (grpParID.equals("")) {
            ApplicationGroupMemberships agm = getApplicationGroupMembershipsById(appID, session).get(0);
            grpParID = agm.getId().getGroupId();
            group.setGroupParentId(grpParID);
        } else {
            group.setGroupParentId(grpParID.split("/")[2]);
        }
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        group.setCreatedDate(gc.getTime());
        group.setDisplayOrder(0);
        group.setFunctionId(0);
        groupID = UUID.randomUUID().toString();
        group.setGroupId(groupID);

        group.setIsWebShopUser(0);
        group.setUpdatedDate(gc.getTime());
        group.setUserId(userID);

        List<Country> countries = countryDAO.getAllCountries(session);
        Set<GroupTranslations> trans = new HashSet<GroupTranslations>();
        for (Country c : countries) {
            GroupTranslations gt = new GroupTranslations();
            GroupTranslationsId gt_id = new GroupTranslationsId(groupID, c.getCountryId());
            gt.setId(gt_id);
            gt.setCreatedDate(gc.getTime());
            gt.setGroupName(groupName);
            gt.setGroupDescription(!grp_desc.equals("") ? grp_desc.split("/")[2] : "");
            gt.setGroups(group);
            gt.setUpdatedDate(gc.getTime());
            gt.setUserId(userID);
            trans.add(gt);
        }

        group.setGroupTranslationses(trans);

//        userDAO = new UserDAO();
        Set<UserGroupMemberships2> mem = new HashSet<UserGroupMemberships2>();
        UserGroupMemberships2 ugm = new UserGroupMemberships2();
        UserGroupMemberships2Id ugm_id = new UserGroupMemberships2Id(userID, groupID);
        ugm.setId(ugm_id);
        ugm.setCreatedDate(gc.getTime());
        ugm.setGroups(group);
        ugm.setUpdatedDate(gc.getTime());
        UserDAO userDAO = new UserDAO();
        Users2 user = userDAO.getUserById(userID, session);
        ugm.setUsers2(user);
        mem.add(ugm);

        group.setUserGroupMemberships2s(mem);

        session.saveOrUpdate(group);
        tx.commit();
        session.saveOrUpdate(ugm);

    }

    public UserSessions2 getUserSession(Session session, HttpServletRequest request) {
        UserSessions2 us2 = (UserSessions2) session.getNamedQuery("getUserSessionByToken").setString("authenticationToken", request.getHeader("AuthenticationToken")).list().get(0);
        return us2;
    }

    public GroupTranslations getGroupTranslationsByIds(String groupId, int countryId, Session session) {
        List<GroupTranslations> gtList = session.getNamedQuery("getGroupTranslationsById").setString("groupID", groupId).setInteger("countryID", countryId).list();
        if (gtList.isEmpty()) {
            return null;
        }
        return gtList.get(0);
    }

    protected TingcoGroups getGroups(String groupID, String userID, String groupTypeID, Session session) throws DatatypeConfigurationException {
        Groups user_group = getGroupByGroupId(groupID, session);
        Users2 user = (Users2) session.getNamedQuery("getUsers2ById").setString("userID", userID).list().get(0);
        Country c = (Country) session.getNamedQuery("getCountryById").setInteger("countryID", user.getCountryId()).list().get(0);
        //GroupTypeTranslations trans = (GroupTypeTranslations)session.createQuery("from GroupTypeTranslations where CountryID = '"+user.getCountryId()+"' and GroupTypeID = '"+groupTypeID+"'").list().get(0);
        GroupTranslations gt = getGroupTranslationsByIds(user_group.getGroupId(), user.getCountryId(), session);
        TingcoGroups tg = buildGroupTemplate();
        int seq = 1;
        GregorianCalendar gc = new GregorianCalendar();
        se.info24.groupsjaxb.Groups groups = new se.info24.groupsjaxb.Groups();
        se.info24.groupsjaxb.Group grp = new se.info24.groupsjaxb.Group();
        grp.setSeqNo(seq++);
        grp.setGroupID(user_group.getGroupId());
        grp.setGroupParentID(user_group.getGroupParentId());
        GroupNames names = new GroupNames();
        GroupName gn = new GroupName();
        gn.setLanguage(c.getLanguageCode());
        gn.setValue(gt.getGroupName());
        names.getGroupName().add(gn);
        gc.setTime(user_group.getCreatedDate());
        grp.setCreatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
        gc = new GregorianCalendar();
        gc.setTime(user_group.getUpdatedDate());
        grp.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
        groups.getGroup().add(grp);

        tg.setGroups(groups);
        return tg;

    }

    protected TingcoGroups buildGroupTemplate() throws DatatypeConfigurationException {
        ObjectFactory of = new ObjectFactory();
        TingcoGroups groups = of.createTingcoGroups();
        groups.setCreateRef("Info24");
        groups.setMsgID(null);
        groups.setMsgVer(new BigDecimal(1.0));
        groups.setRegionalUnits("Metric");
        groups.setTimeZone("UTC");
        groups.setMsgID(UUID.randomUUID().toString());
        groups.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));

        se.info24.groupsjaxb.MsgStatus status = new se.info24.groupsjaxb.MsgStatus();
        status.setResponseCode(0);
        status.setResponseText("OK");

        groups.setMsgStatus(status);
        return groups;
    }

    protected TingcoGroups updateGroup(String groupID, String grpParID, String groupName, String group_desc, String userID, Session session) throws DatatypeConfigurationException {
        Transaction tx = session.beginTransaction();
        TingcoGroups group = new TingcoGroups();

        Users2 user = (Users2) session.getNamedQuery("getUsers2ById").setString("userID", userID).list().get(0);
        GroupTranslations trans = getGroupTranslationsByIds(groupID, user.getCountryId(), session);
        trans.setGroupName(groupName);
        trans.setGroupDescription(group_desc);
        trans.setUpdatedDate(new GregorianCalendar(TimeZone.getTimeZone("GMT")).getTime());
        session.saveOrUpdate(trans);
        tx.commit();
        group = buildGroupTemplate();
        return group;
    }

    protected boolean deleteGroup(String groupID, Session session) {
        Transaction tx = session.beginTransaction();
        Groups del = getGroupByGroupId(groupID, session);
        session.delete(del);
        tx.commit();
        return true;
    }

    public List<ApplicationGroupMemberships> getApplicationGroupMembershipsById(String appID, Session session) {
        return session.getNamedQuery("getApplicationGroupMembershipsById").setString("appID", appID).list();
    }

    public TingcoGroups getAllGroups(String appID, int countryID, TingcoGroups tingcogroups, Session session) {
        List<ApplicationGroupMemberships> groupMemShips = getApplicationGroupMembershipsById(appID, session);
        List<Groups> grps = session.getNamedQuery("getAllGroupss").list();
        String groupTypeID = null;
        int seq = 1;
        GroupTypeTranslations gtt = null;
        Set<GroupTranslations> trans = null;
        se.info24.groupsjaxb.Groups groups = new se.info24.groupsjaxb.Groups();

        for (ApplicationGroupMemberships agm : groupMemShips) {
            se.info24.groupsjaxb.Group grp = new Group();
            grp.setSeqNo(seq++);
            GroupNames grpName = new GroupNames();
            for (Groups g : grps) {
                GroupName gn = new GroupName();
                if (agm.getId().getGroupId().equalsIgnoreCase(g.getGroupId())) {
                    groupTypeID = g.getGroupTypeId();
                    gtt = getGroupTypeTranslationsByGroupTypeId(groupTypeID, countryID, session);
                    trans = g.getGroupTranslationses();
                    for (GroupTranslations gt : trans) {
                        if (gt.getCountry().getCountryId() == countryID) {
                            gn.setLanguage(gt.getCountry().getCountryName());
                            gn.setValue(gt.getGroupName());
                            grpName.getGroupName().add(gn);
                            break;
                        }
                    }
                    if (gtt != null) {
                        grp.setGroupTypeID(gtt.getId().getGroupTypeId());
                        grp.setGroupTypeName(gtt.getGroupTypeName());
                        break;
                    }
                }
            }
            grp.setGroupNames(grpName);
            grp.setGroupID(agm.getId().getGroupId());
            groups.getGroup().add(grp);
            tingcogroups.setGroups(groups);
        }

        return tingcogroups;
    }

    protected boolean createGroupTrust(GroupTrusts groupTrusts, Session session) {
        Transaction tx = null;
        try {
            if (groupTrusts != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(groupTrusts);
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
        return false;  // Control won't come upto here.
    }

    protected List<GroupTypeTranslations> getGroupTypesbyCountryID(int countryId, Session session) {
        return session.getNamedQuery("getGroupTypeTranslationsById").setInteger("countryId", countryId).list();
    }

    public List<GroupTrusts> getGroupTrustByGroupID(String groupId, Session session) {
        List<GroupTrusts> groupTrustsList = new ArrayList<GroupTrusts>();
        try {
            groupTrustsList = session.getNamedQuery("getGroupTrustsById").setString("iTrustgroupID", groupId).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
        }
        return groupTrustsList;
    }

    protected List<GroupTrusts> getITrustOrganizations(String groupId, Session session) {
        return session.getNamedQuery("getGroupTrustsByGroupId").setString("groupId", groupId).list();
    }

    public List<Groups> loadAllGroups(Session session) {
        return session.getNamedQuery("getAllGroupss").list();
    }

    public GroupTypeTranslations getGroupTypeTranslationsByGroupTypeId(String groupTypeId, int countryId, Session session) {
        return (GroupTypeTranslations) session.getNamedQuery("getGroupTypeTranslationsByGroupTypeId").setString("groupTypeId", groupTypeId).setInteger("countryId", countryId).uniqueResult();
    }

    boolean deleteGroupDetails(String groupId, Groups groups, Session session) {
        Transaction tx = session.beginTransaction();
        MeasurementDAO measurementDAO = new MeasurementDAO();
        ApplicationDAO applicationDAO = new ApplicationDAO();
        ProductsDAO productsDAO = new ProductsDAO();
        PricePlanDAO pricePlanDAO = new PricePlanDAO();
        ServiceDAO serviceDAO = new ServiceDAO();
        UserDAO userDAO = new UserDAO();
        DeviceDAO deviceDAO = new DeviceDAO();
        boolean result = false;
        boolean deleteDone = false;
        try {
            List<MeasurementTypesInGroups> mtigList = measurementDAO.getMeasurementTypesInGroups(groupId, session);
            if (!mtigList.isEmpty()) {
                for (MeasurementTypesInGroups mtig : mtigList) {
                    session.delete(mtig);
                }
            }

            GroupLimits groupLimits = getGroupLimitsByGroupId(groupId, session);
            if (groupLimits != null) {
                session.delete(groupLimits);
            }

            List<GroupApplicationPackages> gapList = applicationDAO.getGroupApplicationPackagesbyGroupId(groupId, session);
            if (!gapList.isEmpty()) {
                for (GroupApplicationPackages gap : gapList) {
                    session.delete(gap);
                }
            }

            List<GroupVisibleApplicationModules> gvamList = applicationDAO.getGroupVisibleAppModules(session, groupId);
            if (!gvamList.isEmpty()) {
                for (GroupVisibleApplicationModules gvam : gvamList) {
                    session.delete(gvam);
                }
            }

            List<se.info24.pojo.GroupVisibleApplicationPackages> gvapList = applicationDAO.getGroupVisibleApplicationPackagesbyGroupid(groupId, session);
            if (!gvapList.isEmpty()) {
                for (se.info24.pojo.GroupVisibleApplicationPackages gvap : gvapList) {
                    session.delete(gvap);
                }
            }

            List<se.info24.pojo.GroupVisibleApplicationSolutions> gvasList = applicationDAO.getGroupVisibleApplicationSolutionsbyGroupid(groupId, session);
            if (!gvasList.isEmpty()) {
                for (se.info24.pojo.GroupVisibleApplicationSolutions gvas : gvasList) {
                    session.delete(gvas);
                }
            }

            List<ObjectAddresses> groupAddressesList = session.getNamedQuery("getObjectAddressesByobjectId").setString("objectId", groupId).list();
            if (!groupAddressesList.isEmpty()) {
                for (ObjectAddresses ga : groupAddressesList) {
                    session.delete(ga);
                }
            }

            GroupDefaultAgreement gda = productsDAO.getGroupDefaultAgreement(groupId, session);
            if (gda != null) {
                session.delete(gda);
            }

            List<GroupObjectSettingPackages> gospList = getGroupObjectSettingPackagesByGroupId(groupId, session);
            if (!gospList.isEmpty()) {
                for (GroupObjectSettingPackages gosp : gospList) {
                    session.delete(gosp);
                }
            }

            List<GroupProductComponents> gpcList = getGroupProductComponentsbyGroupId(groupId, session);
            if (!gpcList.isEmpty()) {
                for (GroupProductComponents gpc : gpcList) {
                    session.delete(gpc);
                }
            }

            List<GroupProductVariantSubscriptions> gpvsList = productsDAO.getAllGroupProductVariantSubscriptions(groupId, session);
            if (!gpvsList.isEmpty()) {
                for (GroupProductVariantSubscriptions gpvs : gpvsList) {
                    session.delete(gpvs);
                }
            }

            List<GroupSettings> gsList = getGroupSettingsbyGroupId(groupId, session);
            if (!gsList.isEmpty()) {
                for (GroupSettings gs : gsList) {
                    session.delete(gs);
                }
            }

            List<GroupTrusts> gtList = getITrustOrganizations(groupId, session);
            if (!gtList.isEmpty()) {
                for (GroupTrusts gt : gtList) {
                    session.delete(gt);
                }
            }

            List<GroupWeekdays> gwList = getGroupWeekDaysByGroupId(groupId, session);
            if (!gwList.isEmpty()) {
                for (GroupWeekdays gw : gwList) {
                    session.delete(gw);
                }
            }

            List<GroupsFirstDayOfWeek> gfdowList = getGroupsFirstDayOfWeekbyGroupId(groupId, session);
            if (!gfdowList.isEmpty()) {
                for (GroupsFirstDayOfWeek gfdow : gfdowList) {
                    session.delete(gfdow);
                }
            }

            List<ProductsInGroups> pigList = productsDAO.getProductsInGroupsByGroupId(session, groupId);
            if (!pigList.isEmpty()) {
                for (ProductsInGroups pig : pigList) {
                    session.delete(pig);
                }
            }



            List<UserAliasTypesInGroups> uatigList = pricePlanDAO.getUserAliasTypesInGroups(session, groupId);
            if (!uatigList.isEmpty()) {
                for (UserAliasTypesInGroups uatig : uatigList) {
                    session.delete(uatig);
                }
            }

            List<ContactTypesInGroups> ctigList = getContactTypesInGroupsbyGroupId(groupId, session);
            if (!ctigList.isEmpty()) {
                for (ContactTypesInGroups ctig : ctigList) {
                    session.delete(ctig);
                }
            }

            List<Users2> users2List = userDAO.getUsers2ByGroupId(groupId, session);
            if (!users2List.isEmpty()) {
                for (Users2 users2 : users2List) {
                    userDAO.deleteGroupUserByUserId(session, users2.getUserId());
//                    session.delete(users2);
                }
            }

            List<se.info24.pojo.UserGroupMemberships> ugmsList = userDAO.getUserGroupMembershipsbyGroupId(groupId, session);
            if (!ugmsList.isEmpty()) {
                for (se.info24.pojo.UserGroupMemberships ugms : ugmsList) {
                    session.delete(ugms);
                }
            }

            List<se.info24.pojo.UserGroupMemberships2> ugms2List = userDAO.getUserGroupMemberships2byGroupId(groupId, session);
            if (!ugms2List.isEmpty()) {
                for (se.info24.pojo.UserGroupMemberships2 ugms2 : ugms2List) {
                    session.delete(ugms2);
                }
            }

            List<ServiceClientLogins> sclList = productsDAO.getServiceClientLoginsByGroupId(groupId, session);
            if (!sclList.isEmpty()) {
                for (ServiceClientLogins scl : sclList) {
                    session.delete(scl);
                }
            }

            List<Services> servicesList = serviceDAO.getServicesByGroupId(session, groupId);
            if (!servicesList.isEmpty()) {
                for (Services ser : servicesList) {
                    session.delete(ser);
                }
            }

            List<ServicesVisibleToGroup> svtgList = serviceDAO.getServicesVisibleToGroups(session, groupId);
            if (!svtgList.isEmpty()) {
                for (ServicesVisibleToGroup svtg : svtgList) {
                    session.delete(svtg);
                }
            }

            List<UserFavoriteGroups> ufgList = userDAO.getUserFavoriteGroupsByGroupId(groupId, session);
            if (!ufgList.isEmpty()) {
                for (UserFavoriteGroups ufg : ufgList) {
                    session.delete(ufg);
                }
            }

            List<UserRolesInGroups2> urigList = userDAO.getUserRolesInGroups2ByGroupId(groupId, session);
            if (!urigList.isEmpty()) {
                for (UserRolesInGroups2 urig : urigList) {
                    session.delete(urig);
                }
            }

            List<ServiceTypesInGroups> stgList = serviceDAO.getServiceTypesByGroupId(groupId, session);
            if (!stgList.isEmpty()) {
                for (ServiceTypesInGroups stg : stgList) {
                    session.delete(stg);
                }
            }

            List<Device> deviceList = deviceDAO.getDeviceByGroup(session, groupId);

            Set<Device> deviceset = new HashSet<Device>(deviceList);
            groups.setDevices(deviceset);

            List<RoutingTable> rtList = session.getNamedQuery("getRoutingTableByGroupId").setString("groupId", groupId).list();
            Set<RoutingTable> routeSet = new HashSet<RoutingTable>(rtList);
            groups.setRoutingTables(routeSet);

            List<NetworkSubscriptions> nsList = session.getNamedQuery("getNetworkSubscriptionsByGroupId").setString("groupId", groupId).list();
            Set<NetworkSubscriptions> nsSet = new HashSet<NetworkSubscriptions>(nsList);
            groups.setNetworkSubscriptionses(nsSet);

            List<SupportOrgVisibleToGroups> sovgList = session.getNamedQuery("getSupportOrgVisibleToGroupsByGroupId").setString("groupId", groupId).list();
            Set<SupportOrgVisibleToGroups> sovgSet = new HashSet<SupportOrgVisibleToGroups>(sovgList);
            groups.setSupportOrgVisibleToGroupses(sovgSet);

            List<SupportCases> scList = session.getNamedQuery("getSupportCasesByGroupId").setString("groupId", groupId).list();
            Set<SupportCases> scSet = new HashSet<SupportCases>(scList);
            groups.setSupportCaseses(scSet);

            List<GroupTranslations> gTransList = getGroupTranslationbyGroupId(groupId, session);
//            if (!gTransList.isEmpty()) {
//                for (GroupTranslations gtrans : gTransList) {
//                    session.delete(gtrans);
//                }
//            }
            Set<GroupTranslations> gtset = new HashSet<GroupTranslations>(gTransList);
            groups.setGroupTranslationses(gtset);
            session.delete(groups);

            deleteDone = true;
            if (deleteDone) {
                tx.commit();
            }
            result = true;
        } catch (Exception ex) {
            result = false;
            tx.rollback();
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }

        return result;
    }

    public List<GroupTranslations> getGroupTranslationbyGroupId(String groupId, Session session) {
        return session.getNamedQuery("getGroupTranslationbyGroupId").setString("groupId", groupId).list();
    }

    public List<ContactTypesInGroups> getContactTypesInGroupsbyGroupId(String groupId, Session session) {
        return session.getNamedQuery("getContactTypesInGroupsbyGroupId").setString("groupId", groupId).list();
    }

    public List<GroupsFirstDayOfWeek> getGroupsFirstDayOfWeekbyGroupId(String groupId, Session session) {
        return session.getNamedQuery("getGroupsFirstDayOfWeekbyGroupId").setString("groupId", groupId).list();
    }

    public List<GroupWeekdays> getGroupWeekDaysByGroupId(String groupId, Session session) {
        return session.getNamedQuery("getGroupWeekDaysByGroupId").setString("groupId", groupId).list();
    }

    public List<GroupSettings> getGroupSettingsbyGroupId(String groupId, Session session) {
        return session.getNamedQuery("getGroupSettingsbyGroupId").setString("groupId", groupId).list();
    }

    public List<GroupProductComponents> getGroupProductComponentsbyGroupId(String groupId, Session session) {
        return session.getNamedQuery("getGroupProductComponentsbyGroupId").setString("groupId", groupId).list();
    }

    public List<GroupObjectSettingPackages> getGroupObjectSettingPackagesByGroupId(String groupId, Session session) {
        return session.getNamedQuery("getGroupObjectSettingPackagesByGroupId").setString("groupId", groupId).list();
    }

    public List<GroupAddresses> getGroupAddressesByGroupId(String groupId, Session session) {
        return session.getNamedQuery("getGroupAddressesByGroupId").setString("groupId", groupId).list();
    }

    boolean deleteObjectGroups(ObjectGroups objectGroups, List<ObjectGroupTranslations> objectGroupTranslationList, List<Ogmdevice> ogmDeviceList, List<OgmuserAlias> ogmUserAliasList, List<Ogmcontainers> ogmContainerList, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (Ogmdevice ogmDevice : ogmDeviceList) {
                session.delete(ogmDevice);
            }
            for (OgmuserAlias ogmAlias : ogmUserAliasList) {
                session.delete(ogmAlias);
            }
            session.flush();
            session.clear();
            for (Ogmcontainers ogmcontainers : ogmContainerList) {
                session.delete(ogmcontainers);
            }
            for (ObjectGroupTranslations objectGroupTranslation : objectGroupTranslationList) {
                session.delete(objectGroupTranslation);
            }
            if (objectGroups != null) {
                session.delete(objectGroups);
            }
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

//    List<AccessLog> getAccessLogDetails(se.info24.devicejaxbPost.Device deviceXML, TingcoGroups tingcoGroups, String loggedinUserId, Session ismOperationsSession, String timeZoneGMToffset, Session session) throws ParseException {
//        GroupDAO groupDAO = new GroupDAO();
//        UserDAO userDAO = new UserDAO();
//        List<AccessLog> accessLogList = new ArrayList<AccessLog>();
//        String timePeriod = deviceXML.getEventDatas().getTimePeriod();
//        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
//        String gc_time = lFormat.format(gc.getTime());
//        GregorianCalendar gc_diff = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
//        String gc_diff_time = null;
//        if (timePeriod != null) {
//            if (timePeriod.equalsIgnoreCase("hour")) {
//                gc_diff.add(GregorianCalendar.HOUR, -1);
//                gc_diff_time = lFormat.format(gc_diff.getTime());
//            } else if (timePeriod.equalsIgnoreCase("day")) {
//                gc_diff.add(GregorianCalendar.DATE, -1);
//                gc_diff_time = lFormat.format(gc_diff.getTime());
//            } else if (timePeriod.equalsIgnoreCase("week")) {
//                gc_diff.add(GregorianCalendar.DATE, -7);
//                gc_diff_time = lFormat.format(gc_diff.getTime());
//            } else if (timePeriod.equalsIgnoreCase("month")) {
//                gc_diff.add(GregorianCalendar.MONTH, -1);
//                gc_diff_time = lFormat.format(gc_diff.getTime());
//            }
//        } else if (deviceXML.getEventDatas().getFromDate() != null && deviceXML.getEventDatas().getToDate() != null) {
//
//            gc_diff.setTime(lFormat.parse(deviceXML.getEventDatas().getFromDate()));
//            gc_diff.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc_diff.getTime()));
//            gc_diff_time = lFormat.format(gc_diff.getTime());
//
//            gc.setTime(lFormat.parse(deviceXML.getEventDatas().getToDate()));
//            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
//            gc_time = lFormat.format(gc.getTime());
//        }
//        Users2 users2 = userDAO.getUserById(loggedinUserId, session);
//        List<GroupTrusts> groupTrustsList = getGroupTrustByGroupID(users2.getGroupId(), session);
//        Set<String> groupIdsList = getGroupIdsList(users2.getGroupId(), groupTrustsList);
//        StringBuffer queryString = new StringBuffer("from AccessLog where userGroupId in (:groupIdsList)");
//
//        List<String> searchGroupIdsList = new ArrayList<String>();
//        if (deviceXML.getGroupID() != null) {
//            List<GroupTranslations> groupTranslationsList = groupDAO.getGroupTransByGroupIdandSearchString(groupIdsList, deviceXML.getGroupID().getGroupName(), deviceXML.getCountryID().getValue(), session);
//            if (!groupTranslationsList.isEmpty()) {
//                searchGroupIdsList = groupDAO.getGroupidsListFromGroupTranslations(groupTranslationsList);
//                queryString.append(" and deviceGroupId in (:searchGroupIdsList)");
//            } else {
//                return accessLogList;
//            }
//        }
//
//        List<String> objectDeviceIdsList = new ArrayList<String>();
//        if (deviceXML.getObjectGroupName() != null) {
//            Criteria criteria = session.createCriteria(Ogmdevice.class, "ogm");
//            criteria.createAlias("ogm.objectGroups", "og", CriteriaSpecification.INNER_JOIN);
//            criteria.add(Restrictions.in("og.groupId", groupIdsList));
//            criteria.createAlias("og.objectGroupTranslationses", "ogt", CriteriaSpecification.INNER_JOIN);
//            criteria.add(Restrictions.ilike("ogt.objectGroupName", deviceXML.getObjectGroupName(), MatchMode.ANYWHERE));
//            criteria.add(Restrictions.eq("ogt.id.countryId", deviceXML.getCountryID().getValue()));
//            criteria.setProjection(Projections.distinct(Projections.property("ogm.id.deviceId")));
//            objectDeviceIdsList = criteria.list();
//            if (!objectDeviceIdsList.isEmpty()) {
//                queryString.append(" and deviceId in (:objectDeviceIdsList)");
//            } else {
//                return accessLogList;
//            }
//        }
//
//        if (deviceXML.getDeviceName() != null) {
//            if (deviceXML.getDeviceName().length() == 36) {
//                queryString.append(" and deviceId = '" + deviceXML.getDeviceName() + "' ");
//            } else {
//                queryString.append(" and deviceName like '%" + deviceXML.getDeviceName() + "%' ");
//            }
//        }
//        if (deviceXML.getLastUpdatedByUserID() != null) {
//            queryString.append(" and (firstName like '%" + deviceXML.getLastUpdatedByUserID().getUserFullName() + "%' or lastName like '%" + deviceXML.getLastUpdatedByUserID().getUserFullName() + "%' or userAlias like '%" + deviceXML.getLastUpdatedByUserID().getUserFullName() + "%') ");
//        }
//        if (deviceXML.getEventTypes() != null) {
//            queryString.append(" and eventTypeId = '" + deviceXML.getEventTypes().getEventTypeTranslation().get(0).getEventTypeID() + "' ");
//        }
//        if (timePeriod != null) {
//            if (timePeriod.equalsIgnoreCase("100")) {
//
//                queryString.append(" order by accessTime desc");
//                Query query = ismOperationsSession.createQuery(queryString.toString());
//                query.setParameterList("groupIdsList", groupIdsList);
//                if (!searchGroupIdsList.isEmpty()) {
//                    query.setParameterList("searchGroupIdsList", searchGroupIdsList);
//                } else {
//                    searchGroupIdsList = null;
//                }
//                if (!objectDeviceIdsList.isEmpty()) {
//                    query.setParameterList("objectDeviceIdsList", objectDeviceIdsList);
//                } else {
//                    objectDeviceIdsList = null;
//                }
//                accessLogList = query.setMaxResults(100).list();
//            } else {
//                queryString.append(" and accessTime between '" + gc_diff_time + "' and '" + gc_time + "' order by accessTime desc");
//                Query query = ismOperationsSession.createQuery(queryString.toString());
//                query.setParameterList("groupIdsList", groupIdsList);
//                if (!searchGroupIdsList.isEmpty()) {
//                    query.setParameterList("searchGroupIdsList", searchGroupIdsList);
//                } else {
//                    searchGroupIdsList = null;
//                }
//                if (!objectDeviceIdsList.isEmpty()) {
//                    query.setParameterList("objectDeviceIdsList", objectDeviceIdsList);
//                } else {
//                    objectDeviceIdsList = null;
//                }
//                accessLogList = query.setMaxResults(200).list();
//            }
//        } else {
//            queryString.append(" and accessTime between '" + gc_diff_time + "' and '" + gc_time + "' order by accessTime desc");
//            Query query = ismOperationsSession.createQuery(queryString.toString());
//            query.setParameterList("groupIdsList", groupIdsList);
//            if (!searchGroupIdsList.isEmpty()) {
//                query.setParameterList("searchGroupIdsList", searchGroupIdsList);
//            } else {
//                searchGroupIdsList = null;
//            }
//            if (!objectDeviceIdsList.isEmpty()) {
//                query.setParameterList("objectDeviceIdsList", objectDeviceIdsList);
//            } else {
//                objectDeviceIdsList = null;
//            }
//            accessLogList = query.setMaxResults(200).list();
//        }
//        return accessLogList;
//    }
    List<Object> getAccessLogDetails(se.info24.devicejaxbPost.Device deviceXML, TingcoGroups tingcoGroups, String loggedinUserId, String timeZoneGMToffset, Session session) throws ParseException {
        UserDAO userDAO = new UserDAO();
        List<Object> accessLogList = new ArrayList<Object>();
        String timePeriod = deviceXML.getEventDatas().getTimePeriod();
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
        } else if (deviceXML.getEventDatas().getFromDate() != null && deviceXML.getEventDatas().getToDate() != null) {

            gc_diff.setTime(lFormat.parse(deviceXML.getEventDatas().getFromDate()));
            gc_diff.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc_diff.getTime()));
            gc_diff_time = lFormat.format(gc_diff.getTime());

            gc.setTime(lFormat.parse(deviceXML.getEventDatas().getToDate()));
            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
            gc_time = lFormat.format(gc.getTime());
        }
        Users2 users2 = userDAO.getUserById(loggedinUserId, session);
        StringBuffer queryString = new StringBuffer();

        if (deviceXML.getGroupID() != null) {
            queryString.append("select al.accesslogrowid as accessLogRowId, al.accessTime as accessTime, al.deviceName as deviceName, al.firstName as firstName, al.lastName as lastName, al.userAlias as userAlias, ett.eventTypeName as eventTypeName from " +
                    " ISMOperations.dbo.accesslog as al inner join eventtypetranslations as ett on al.eventtypeId = ett.eventtypeid and ett.countryid=" + deviceXML.getCountryID().getValue() + " " +
                    " inner join Groups as g on al.deviceGroupId = g.groupId and g.groupId in (" + TCMUtil.getGroupTrust(users2.getGroupId()) + ")  ");

            if (TCMUtil.isValidUUID(deviceXML.getGroupID().getGroupName())) {
                queryString.append("inner join GroupTranslations as gts on g.groupId = gts.groupId and gts.groupId = '" + deviceXML.getGroupID().getGroupName() + "' ");
            } else {
                queryString.append("inner join GroupTranslations as gts on g.groupId = gts.groupId and gts.groupName like '%" + deviceXML.getGroupID().getGroupName() + "%' and gts.countryId = " + deviceXML.getCountryID().getValue() + " ");
            }
        } else {
            queryString.append("select al.accesslogrowid as accessLogRowId, al.accessTime as accessTime, al.deviceName as deviceName, al.firstName as firstName, al.lastName as lastName, al.userAlias as userAlias, ett.eventTypeName as eventTypeName from " +
                    " ISMOperations.dbo.accesslog as al inner join eventtypetranslations as ett on al.eventtypeId = ett.eventtypeid and ett.countryid=" + deviceXML.getCountryID().getValue() + " " +
                    " inner join Groups as g on al.userGroupId = g.groupId and g.groupId in (" + TCMUtil.getGroupTrust(users2.getGroupId()) + ")  ");
        }

        if (deviceXML.getObjectGroupName() != null) {
            queryString.append(" inner join Ogmdevice as ogm on al.deviceId = ogm.deviceId inner join ObjectGroups as og on ogm.objectGroupId = og.objectGroupId " +
                    " inner join ObjectGroupTranslations as ogt on og.objectGroupId = ogt.objectGroupId and ogt.countryId = " + deviceXML.getCountryID().getValue() + " ");
            if (TCMUtil.isValidUUID(deviceXML.getObjectGroupName())) {
                queryString.append("and ogt.objectGroupId = '" + deviceXML.getObjectGroupName() + "' ");
            } else {
                queryString.append("and ogt.objectGroupName like '%" + deviceXML.getObjectGroupName() + "%' ");
            }
        }

        if (deviceXML.getObjectGroupNameTCMV3() != null) {
            queryString.append(" inner join Ogmdevice as ogm on al.deviceId = ogm.deviceId inner join ObjectGroups as og on ogm.objectGroupId = og.objectGroupId " +
                    " inner join ObjectGroupTranslations as ogt on og.objectGroupId = ogt.objectGroupId and ogt.countryId = " + deviceXML.getCountryID().getValue() + " ");
            if (TCMUtil.isValidUUID(deviceXML.getObjectGroupNameTCMV3())) {
                queryString.append("and ogt.objectGroupId = '" + deviceXML.getObjectGroupNameTCMV3() + "' ");
            } else {
                queryString.append("and ogt.objectGroupName like '%" + TCMUtil.convertHexToString(deviceXML.getObjectGroupNameTCMV3()) + "%' ");
            }
        }

        if (deviceXML.getDeviceName() != null) {
            if (TCMUtil.isValidUUID(deviceXML.getDeviceName())) {
                queryString.append(" and al.deviceId = '" + deviceXML.getDeviceName() + "' ");
            } else {
                queryString.append(" and al.deviceName like '%" + deviceXML.getDeviceName() + "%' ");
            }
        }
        if (deviceXML.getLastUpdatedByUserID() != null) {
            queryString.append(" and (al.firstName like '%" + deviceXML.getLastUpdatedByUserID().getUserFullName() + "%' or al.lastName like '%" + deviceXML.getLastUpdatedByUserID().getUserFullName() + "%' or al.userAlias like '%" + deviceXML.getLastUpdatedByUserID().getUserFullName() + "%') ");
        }
        if (deviceXML.getEventTypes() != null) {
            queryString.append(" and al.eventTypeId = '" + deviceXML.getEventTypes().getEventTypeTranslation().get(0).getEventTypeID() + "' ");
        }
        if (timePeriod != null) {
            if (timePeriod.equalsIgnoreCase("100")) {
                queryString.append(" order by al.accessTime desc");
                SQLQuery query = session.createSQLQuery(queryString.toString());
                query.addScalar("accessLogRowId", Hibernate.STRING);
                query.addScalar("accessTime", Hibernate.STRING);
                query.addScalar("deviceName", Hibernate.STRING);
                query.addScalar("firstName", Hibernate.STRING);
                query.addScalar("lastName", Hibernate.STRING);
                query.addScalar("userAlias", Hibernate.STRING);
                query.addScalar("eventTypeName", Hibernate.STRING);
                accessLogList = query.setMaxResults(100).list();
            } else {
                queryString.append(" and al.accessTime between '" + gc_diff_time + "' and '" + gc_time + "' order by al.accessTime desc");
                SQLQuery query = session.createSQLQuery(queryString.toString());
                query.addScalar("accessLogRowId", Hibernate.STRING);
                query.addScalar("accessTime", Hibernate.STRING);
                query.addScalar("deviceName", Hibernate.STRING);
                query.addScalar("firstName", Hibernate.STRING);
                query.addScalar("lastName", Hibernate.STRING);
                query.addScalar("userAlias", Hibernate.STRING);
                query.addScalar("eventTypeName", Hibernate.STRING);
                accessLogList = query.list();
            }
        } else {
            queryString.append(" and al.accessTime between '" + gc_diff_time + "' and '" + gc_time + "' order by al.accessTime desc");
            SQLQuery query = session.createSQLQuery(queryString.toString());
            query.addScalar("accessLogRowId", Hibernate.STRING);
            query.addScalar("accessTime", Hibernate.STRING);
            query.addScalar("deviceName", Hibernate.STRING);
            query.addScalar("firstName", Hibernate.STRING);
            query.addScalar("lastName", Hibernate.STRING);
            query.addScalar("userAlias", Hibernate.STRING);
            query.addScalar("eventTypeName", Hibernate.STRING);
            accessLogList = query.list();
        }
        return accessLogList;
    }

    ApplicationSettings getAppSettingValueByIDandName(String appID, String appSettingName, Session session) {
        return (ApplicationSettings) session.getNamedQuery("getAppSettingValueByIDandName").setString("appID", appID).setString("appSetName", appSettingName).uniqueResult();
    }

    public TingcoGroups addNewGroup(String groupName, String grp_desc, String groupTypeID, int countryID, String domainID, String grpParID, String appID, String userID, TingcoGroups tGroup, Session session, String roleID) {
        Transaction tx = session.beginTransaction();
        CountryDAO countryDAO = new CountryDAO();
        try {
            if (!groupExists(groupName, countryID, session)) {
                String groupID = UUID.randomUUID().toString();
                Groups group = new Groups(groupID);
                se.info24.groupsjaxb.Groups grps = new se.info24.groupsjaxb.Groups();
                Group g = new Group();//corresponds to the Group in TingcoGroup.xml

                g.setGroupID(groupID);

//                ApplicationSettings as = (ApplicationSettings) session.getNamedQuery("getAppSettingValueByIDandName").setString("appID", appID).setString("appSetName", "GroupParentID").list().get(0);
                ApplicationSettings as = getAppSettingValueByIDandName(appID, "GroupParentID", session);

                group.setGroupParentId(as.getApplicationSettingValue());

                GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                group.setCreatedDate(gc.getTime());
                group.setDisplayOrder(0);
                group.setFunctionId(0);
                group.setIsWebShopUser(0);
                group.setUpdatedDate(gc.getTime());
                group.setUserId(userID);
                group.setDomainId(domainID);

                grps.getGroup().add(g);

                tGroup.setGroups(grps);

                List<Country> countries = countryDAO.getAllCountries(session);
                Set<GroupTranslations> trans = new HashSet<GroupTranslations>();
                for (Country c : countries) {
                    GroupTranslations gt = new GroupTranslations();
                    GroupTranslationsId gt_id = new GroupTranslationsId(groupID, c.getCountryId());
                    gt.setId(gt_id);
                    gt.setCreatedDate(gc.getTime());
                    gt.setGroupName(groupName);
                    gt.setGroupDescription(!grp_desc.equals("") ? grp_desc.split("/")[2] : "");
                    gt.setGroups(group);
                    gt.setUpdatedDate(gc.getTime());
                    gt.setUserId(userID);
                    trans.add(gt);
                }

                ApplicationGroupMemberships agm = new ApplicationGroupMemberships();
                ApplicationGroupMembershipsId id = new ApplicationGroupMembershipsId(appID, groupID);
                agm.setId(id);
                agm.setCreatedDate(gc.getTime());
                agm.setLastUpdatedByUserId(userID);
                agm.setUpdatedDate(gc.getTime());

                group.setGroupTranslationses(trans);

                if (!roleID.equals("")) {
                    roleID = roleID.split("/")[2];
                    GroupTrusts groupTrusts = new GroupTrusts();
                    GroupTrustsId groupTrustsId = new GroupTrustsId(groupID, as.getApplicationSettingValue(), roleID);
                    groupTrusts.setId(groupTrustsId);
                    groupTrusts.setActiveFromDate(gc.getTime());
                    groupTrusts.setLastUpdatedByUserId(userID);
                    groupTrusts.setCreatedDate(gc.getTime());
                    groupTrusts.setUpdatedDate(gc.getTime());
                    gc.add(Calendar.YEAR, 5);
                    groupTrusts.setActiveToDate(gc.getTime());
                    session.save(groupTrusts);
                }
                session.save(group);
                session.save(agm);
                tx.commit();
            } else {
                tGroup.getMsgStatus().setResponseCode(-1);
                tGroup.getMsgStatus().setResponseText("Error");
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tx.rollback();
            tGroup.getMsgStatus().setResponseCode(-1);
            tGroup.getMsgStatus().setResponseText("Error");
        }
        return tGroup;
    }

    public TingcoUsers isGroupTrustExists(String groupID, String iTrustgroupID, String roleID, int countryID, TingcoUsers tingcoUser, Session session) {
        try {
            List<GroupTrusts> list = session.getNamedQuery("getGroupTrustsByIds").setString("groupID", groupID).setString("iTrustgroupID", iTrustgroupID).setString("roleID", roleID).list();
            GroupDAO grpMan = new GroupDAO();
            UserGroupMemberships group_mem = new UserGroupMemberships();
            Users users = new Users();
            User user = new User();
            List<Groups> grps = grpMan.loadAllGroups(session);
            boolean present = false;

            for (Groups g : grps) {
                GroupID gid = new GroupID();
                if (g.getGroupId().equalsIgnoreCase(groupID)) {
                    present = true;
                    gid.setValue(g.getGroupId());
                    Set<GroupTranslations> trans = g.getGroupTranslationses();
                    for (GroupTranslations grouptrans : trans) {
                        if (grouptrans.getCountry().getCountryId() == countryID) {
                            gid.setGroupName(grouptrans.getGroupName());
                            group_mem.getGroupID().add(gid);
                            break;
                        }
                    }
                    break;
                }
            }
            user.setUserGroupMemberships(group_mem);
            users.getUser().add(user);
            tingcoUser.setUsers(users);
            if (list.size() > 0) {
                return tingcoUser;
            } else {
                if (present) {
                    user.setUserGroupMemberships(group_mem);
                    users.getUser().add(user);
                    tingcoUser.setUsers(users);
                }
                tingcoUser.getMsgStatus().setResponseCode(-2);
                tingcoUser.getMsgStatus().setResponseText("Error");
            }
            if (!present) {
                tingcoUser.getMsgStatus().setResponseCode(-1);
                tingcoUser.getMsgStatus().setResponseText("Error");
            }
            return tingcoUser;
        } catch (Exception e) {
            return null;
        }

    }

    public boolean deleteGroupTrust(String iTrustgroupID, String roleid, Session session) {
        try {
            Transaction tx = session.beginTransaction();
            Query q = session.getNamedQuery("deleteGroupTrustById").setString("iTrustgroupID", iTrustgroupID).setString("roleId", roleid);
            q.executeUpdate();
            q = session.getNamedQuery("deleteUserRoleObjectPermissionsById").setString("iTrustgroupID", iTrustgroupID).setString("roleid", roleid);
            q.executeUpdate();
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    public Groups getGroupByGroupId(String groupId, Session session) {
        try {
            return (Groups) session.getNamedQuery("getGroupsById").setString("groupID", groupId).uniqueResult();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public List<UserFavoriteGroups> getUserFavouriteGroups(String userId, Session session) {
        List<UserFavoriteGroups> favGroups = session.getNamedQuery("getUserFavoriteGroupsById").setString("userId", userId).list();
        return favGroups;
    }

    public boolean addfavoriteGroups(String userID, List<Group> list, Session session) {
        try {
            Transaction tx = session.beginTransaction();
            GregorianCalendar gc = new GregorianCalendar();
            for (Group g : list) {
                UserFavoriteGroups ufg = new UserFavoriteGroups();
                UserFavoriteGroupsId id = new UserFavoriteGroupsId(userID, g.getGroupID());
                ufg.setId(id);
                ufg.setCreatedDate(gc.getTime());
                ufg.setUpdatedDate(gc.getTime());
                tx = session.beginTransaction();
                session.save(ufg);
                tx.commit();
            }
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    public boolean deleteuserfavoriteGroups(String userId, Session session) {
        try {
            Transaction tx = session.beginTransaction();
            Query query = session.getNamedQuery("deleteUserFavoriteGroupsById").setString("userId", userId);
            query.executeUpdate();
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }

    }

    public Set<String> getGroupIdsList(String groupId, List<GroupTrusts> groupTrustList) {
        Set<String> groupidSet = new HashSet<String>();
        groupidSet.add(groupId);
        if (!groupTrustList.isEmpty()) {
            for (GroupTrusts gt : groupTrustList) {
                groupidSet.add(gt.getId().getGroupId());
            }
        }
        return groupidSet;
    }

    public Set<String> getGroupsAndGroupTrusts(String groupid, Session session) {
        Set<String> groupidset = new HashSet<String>();
        groupidset.add(groupid);
        List<GroupTrusts> groupTrustsList = getGroupTrustByGroupID(groupid, session);
        if (!groupTrustsList.isEmpty()) {
            for (GroupTrusts gtr : groupTrustsList) {
                groupidset.add(gtr.getId().getGroupId());
            }
        }
        return groupidset;
    }

    boolean addNewOrganization(Group groupXML, Users2 users2, ApplicationSettings as, Session session) {
        Transaction tx = null;
//        boolean result = false;
        try {
            tx = session.beginTransaction();
            ProductsDAO productDAO = new ProductsDAO();
            CountryDAO countryDAO = new CountryDAO();
            DbManager dbManager = new DbManager();
            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
            GregorianCalendar gc_20years = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
            gc_20years.add(GregorianCalendar.YEAR, 20);
            GregorianCalendar gc_10years = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
            gc_10years.add(GregorianCalendar.YEAR, 10);
            String newGroupName = groupXML.getGroupNames().getGroupName().get(0).getValue();
            String newGroupDesc = null;
            if (groupXML.getGroupDescriptions() != null) {
                newGroupDesc = groupXML.getGroupDescriptions().getGroupDescription().get(0).getValue();
            }
            Groups groups = new Groups();
            String groupId = groupXML.getGroupID();
            if (groupXML.getGroupID() != null) {
                groups.setGroupId(groupId);
            }
            if (groupXML.getGroupParentID() != null) {
                groups.setGroupParentId(groupXML.getGroupParentID());
            }
            if (groupXML.getGroupTypeID() != null) {
                groups.setGroupTypeId(groupXML.getGroupTypeID());
            }
            if (groupXML.getOrganizationNumber() != null) {
                groups.setOrganizationNumber(groupXML.getOrganizationNumber());
            }
            groups.setFunctionId(0);
            groups.setDisplayOrder(0);
            groups.setIsWebShopUser(0);
            groups.setUserId(users2.getUserId());
            groups.setCreatedDate(gc.getTime());
            groups.setUpdatedDate(gc.getTime());
            groups.setDomainId(users2.getDomains().getDomainId());

            List<Country> countries = countryDAO.getAllCountries(session);
            Set<GroupTranslations> transSet = new HashSet<GroupTranslations>();
            for (Country c : countries) {
                GroupTranslations gt = new GroupTranslations();
                GroupTranslationsId gt_id = new GroupTranslationsId(groupId, c.getCountryId());
                gt.setId(gt_id);
                gt.setGroupName(newGroupName);
                if (newGroupDesc != null) {
                    gt.setGroupDescription(newGroupDesc);
                } else {
                    gt.setGroupDescription(null);
                }
                gt.setUserId(users2.getUserId());
                gt.setCreatedDate(gc.getTime());
                gt.setUpdatedDate(gc.getTime());
                transSet.add(gt);
            }
            groups.setGroupTranslationses(transSet);

            Set<ServiceClientLogins> serviceClientLoginsSet = new HashSet<ServiceClientLogins>();
            ServiceClientLogins serviceClientLogins = new ServiceClientLogins();
            serviceClientLogins.setServiceClientLoginId(UUID.randomUUID().toString());
            serviceClientLogins.setServiceClientLoginName(newGroupName);
            if (newGroupDesc != null) {
                serviceClientLogins.setServiceClientLoginDescription(newGroupDesc);
            } else {
                serviceClientLogins.setServiceClientLoginDescription(null);
            }
            serviceClientLogins.setGroupId(groupId);
            serviceClientLogins.setClientLogin(UUID.randomUUID().toString());
            serviceClientLogins.setPassword("password");
            serviceClientLogins.setIsEnabled(1);
            serviceClientLogins.setProtocol("none");
            serviceClientLogins.setProtocolVersion(0);
            serviceClientLogins.setActiveFromDate(gc.getTime());
            serviceClientLogins.setActiveToDate(gc_20years.getTime());
            serviceClientLogins.setUserId(users2.getUserId());
            serviceClientLogins.setCreatedDate(gc.getTime());
            serviceClientLogins.setUpdatedDate(gc.getTime());

            serviceClientLoginsSet.add(serviceClientLogins);
            groups.setServiceClientLoginses(serviceClientLoginsSet);
            session.saveOrUpdate(groups);

            session.saveOrUpdate(serviceClientLogins);

            GroupDefaultAgreement gda = new GroupDefaultAgreement();
            gda.setGroupId(groupId);
            gda.setAgreements(new Agreements(groupXML.getAgreementID()));
            gda.setLastUpdatedByUserId(users2.getUserId());
            gda.setCreatedDate(gc.getTime());
            gda.setUpdatedDate(gc.getTime());
            session.saveOrUpdate(gda);

            GroupLimitPackages glp = productDAO.getGroupLimitPackagesById(groupXML.getGroupLimitPackageID(), session);
            if (glp != null) {
                GroupLimits groupLimits = new GroupLimits();
                groupLimits.setGroupId(groupId);
                groupLimits.setGroupLimitPackages(glp);
                if (glp.getMaxNumberOfDevices() != null) {
                    groupLimits.setMaxNumberOfDevices(glp.getMaxNumberOfDevices());
                }
                if (glp.getMaxNumberOfUsers() != null) {
                    groupLimits.setMaxNumberOfUsers(glp.getMaxNumberOfUsers());
                }
                if (glp.getMaxNumberOfSubscriptions() != null) {
                    groupLimits.setMaxNumberOfSubscriptions(glp.getMaxNumberOfSubscriptions());
                }
                if (glp.getMaxNumberOfChildGroups() != null) {
                    groupLimits.setMaxNumberOfChildGroups(glp.getMaxNumberOfChildGroups());
                }
                if (glp.getMaxNumberOfTrustingGroups() != null) {
                    groupLimits.setMaxNumberOfTrustingGroups(glp.getMaxNumberOfTrustingGroups());
                }
                if (glp.getMaxDataStorageSize() != null) {
                    groupLimits.setMaxDataStorageSize(glp.getMaxDataStorageSize());
                }
                groupLimits.setLastUpdatedByUserId(users2.getUserId());
                groupLimits.setCreatedDate(gc.getTime());
                groupLimits.setUpdatedDate(gc.getTime());
                session.saveOrUpdate(groupLimits);
            }

            if (groupXML.getGroupApplicationPackages() != null) {
                for (se.info24.groupsjaxb.GroupApplicationPackages gaps : groupXML.getGroupApplicationPackages()) {
                    List<GroupApplicationPackage> gapList = gaps.getGroupApplicationPackage();
                    for (GroupApplicationPackage gap : gapList) {
                        GroupApplicationPackages groupAppPackages = new GroupApplicationPackages();
                        groupAppPackages.setId(new GroupApplicationPackagesId(groupId, gap.getApplicationPackageID()));
                        groupAppPackages.setAgreements(new Agreements(gap.getAgreementID()));
                        groupAppPackages.setIsEnabled(1);
                        groupAppPackages.setIsTrial(0);
                        groupAppPackages.setIsPendingDelete(0);
                        groupAppPackages.setActiveFromDate(gc.getTime());
                        groupAppPackages.setActiveToDate(gc_20years.getTime());
                        groupAppPackages.setOrderedByUserId(users2.getUserId());
                        groupAppPackages.setLastUpdatedByUserId(users2.getUserId());
                        groupAppPackages.setCretedDate(gc.getTime());
                        groupAppPackages.setUpdatedDate(gc.getTime());
                        session.saveOrUpdate(groupAppPackages);
                    }
                }
            }

            if (groupXML.getGroupVisibleApplicationSolutions() != null) {
                for (GroupVisibleApplicationSolutions gvasList : groupXML.getGroupVisibleApplicationSolutions()) {
                    List<String> appSolutionIdList = gvasList.getApplicationSolutionID();
                    for (String str : appSolutionIdList) {
                        se.info24.pojo.GroupVisibleApplicationSolutions gvas = new se.info24.pojo.GroupVisibleApplicationSolutions();
                        gvas.setId(new GroupVisibleApplicationSolutionsId(groupId, str));
                        gvas.setLastUpdatedByUserId(users2.getUserId());
                        gvas.setCreatedDate(gc.getTime());
                        gvas.setUpdatedDate(gc.getTime());
                        session.saveOrUpdate(gvas);
                    }
                }
            }

            if (groupXML.getGroupVisibleApplicationPackages() != null) {
                for (GroupVisibleApplicationPackages gvapList : groupXML.getGroupVisibleApplicationPackages()) {
                    List<String> appPackagesIdList = gvapList.getApplicationPackageID();
                    for (String str : appPackagesIdList) {
                        se.info24.pojo.GroupVisibleApplicationPackages gvap = new se.info24.pojo.GroupVisibleApplicationPackages();
                        gvap.setId(new GroupVisibleApplicationPackagesId(groupId, str));
                        gvap.setLastUpdatedByUserId(users2.getUserId());
                        gvap.setCreatedDate(gc.getTime());
                        gvap.setUpdatedDate(gc.getTime());
                        session.saveOrUpdate(gvap);
                    }
                }
            }
            UserRoleMemberships2 urm = null;
            List<UserRoleMemberships2> urmList = dbManager.getUserRoleIdByUserId(session, users2.getUserId());
            if (!urmList.isEmpty()) {
                urm = urmList.get(0);
            }

            String iTrustGroupId = null;
            if (users2.getGroupId().equalsIgnoreCase(as.getApplicationSettingValue())) {
                GroupTrusts groupTrusts = new GroupTrusts();
                groupTrusts.setId(new GroupTrustsId(groupId, as.getApplicationSettingValue(), urm.getId().getUserRoleId()));
                groupTrusts.setActiveFromDate(gc.getTime());
                groupTrusts.setActiveToDate(gc_10years.getTime());
                groupTrusts.setLastUpdatedByUserId(users2.getUserId());
                groupTrusts.setCreatedDate(gc.getTime());
                groupTrusts.setUpdatedDate(gc.getTime());
                session.merge(groupTrusts);
            } else if (groupXML.getGroupParentID().equalsIgnoreCase(as.getApplicationSettingValue())) {
                iTrustGroupId = groupXML.getGroupParentID();
                for (int i = 0; i < 2; i++) {
                    GroupTrusts groupTrusts = new GroupTrusts();
                    groupTrusts.setId(new GroupTrustsId(groupId, iTrustGroupId, urm.getId().getUserRoleId()));
                    groupTrusts.setActiveFromDate(gc.getTime());
                    groupTrusts.setActiveToDate(gc_10years.getTime());
                    groupTrusts.setLastUpdatedByUserId(users2.getUserId());
                    groupTrusts.setCreatedDate(gc.getTime());
                    groupTrusts.setUpdatedDate(gc.getTime());
                    session.merge(groupTrusts);
                    iTrustGroupId = users2.getGroupId();
                }
            } else if (groupXML.getGroupParentID().equalsIgnoreCase(users2.getGroupId())) {
                iTrustGroupId = groupXML.getGroupParentID();
                for (int i = 0; i < 2; i++) {
                    GroupTrusts groupTrusts = new GroupTrusts();
                    groupTrusts.setId(new GroupTrustsId(groupId, iTrustGroupId, urm.getId().getUserRoleId()));
                    groupTrusts.setActiveFromDate(gc.getTime());
                    groupTrusts.setActiveToDate(gc_10years.getTime());
                    groupTrusts.setLastUpdatedByUserId(users2.getUserId());
                    groupTrusts.setCreatedDate(gc.getTime());
                    groupTrusts.setUpdatedDate(gc.getTime());
                    session.merge(groupTrusts);
                    iTrustGroupId = as.getApplicationSettingValue();
                }
            } else {
                iTrustGroupId = as.getApplicationSettingValue();
                for (int i = 0; i < 3; i++) {
                    if (i == 2) {
                        iTrustGroupId = groupXML.getGroupParentID();
                    }
                    GroupTrusts groupTrusts = new GroupTrusts();
                    groupTrusts.setId(new GroupTrustsId(groupId, iTrustGroupId, urm.getId().getUserRoleId()));
                    groupTrusts.setActiveFromDate(gc.getTime());
                    groupTrusts.setActiveToDate(gc_10years.getTime());
                    groupTrusts.setLastUpdatedByUserId(users2.getUserId());
                    groupTrusts.setCreatedDate(gc.getTime());
                    groupTrusts.setUpdatedDate(gc.getTime());
                    session.merge(groupTrusts);
                    iTrustGroupId = users2.getGroupId();
                }
            }

            if (!as.getApplicationSettingValue().equalsIgnoreCase(groupXML.getGroupParentID())) {
                GroupTrusts groupTrusts = new GroupTrusts();
                iTrustGroupId = groupXML.getGroupParentID();
                groupTrusts.setId(new GroupTrustsId(groupId, iTrustGroupId, urm.getId().getUserRoleId()));
                groupTrusts.setActiveFromDate(gc.getTime());
                groupTrusts.setActiveToDate(gc_10years.getTime());
                groupTrusts.setLastUpdatedByUserId(users2.getUserId());
                groupTrusts.setCreatedDate(gc.getTime());
                groupTrusts.setUpdatedDate(gc.getTime());
                session.merge(groupTrusts);
            }

            if (groupXML.getAddress() != null) {
                Address add = groupXML.getAddress();
                String addressId = UUID.randomUUID().toString();
                Addresses address = new Addresses();
                address.setAddressId(addressId);
                address.setAddressStreet(add.getAddressStreet());
                address.setAddressStreet2(add.getAddressStreet2());
                address.setAddressSuite(add.getAddressSuite());
                address.setAddressCity(add.getAddressCity());
                address.setAddressZip(add.getAddressZip());
                address.setAddressRegion(add.getAddressRegion());
                address.setAddressDescription(add.getAddressDesc());
                address.setAddressType(new AddressType(3));
                address.setCountry(new Country(add.getCountryID()));
                address.setUserId(users2.getUserId());
                address.setCreatedDate(gc.getTime());
                address.setUpdatedDate(gc.getTime());
                session.saveOrUpdate(address);
                session.flush();
                session.clear();

                ObjectAddresses objectAddresses = new ObjectAddresses();
                objectAddresses.setId(new ObjectAddressesId(groupId, 3));
                objectAddresses.setAddressId(addressId);
                objectAddresses.setLastUpdatedUserId(users2.getUserId());
                objectAddresses.setCreatedDate(gc.getTime());
                objectAddresses.setUpdatedDate(gc.getTime());
                session.saveOrUpdate(objectAddresses);

            }
            tx.commit();
            return true;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
            return false;
        }
    }

    public List<Groups> getDownstreamGroupIdsList(String parentGroupID, Session session) {
        return session.getNamedQuery("getDownstreamGroups").setString("groupParentId", parentGroupID).setMaxResults(200).list();
    }

    List<String> getContactGroupsInContactsByCriteria(String contactId, Session session) {
        DetachedCriteria contactGroupsInContactsCriteria = DetachedCriteria.forClass(ContactGroupsInContacts.class, "cgic");
        contactGroupsInContactsCriteria.add(Restrictions.eq("cgic.id.contactId", contactId));
        contactGroupsInContactsCriteria.setProjection(Projections.distinct(Projections.property("cgic.id.contactGroupId")));
        return contactGroupsInContactsCriteria.getExecutableCriteria(session).list();
    }

    List<ContactGroups> getContactGroupsByCriteria(List<String> contactGroupIdsList, Session session) {
        Criteria criteria = session.createCriteria(ContactGroups.class, "cg");
        criteria.add(Restrictions.in("cg.contactGroupId", contactGroupIdsList));
        criteria.addOrder(Order.asc("cg.contactGroupName"));
        return criteria.setMaxResults(200).list();
    }

    ContactGroupsInContacts getContactGroupsInContacts(String contactId, String contactGroupId, Session session) {
        return (ContactGroupsInContacts) session.getNamedQuery("getContactGroupsInContactsByIds").setString("contactId", contactId).setString("contactGroupId", contactGroupId).uniqueResult();
    }

    List<ContactGroupsInContacts> getContactGroupsInContactsByContactId(String contactId, Session session) {
        return session.getNamedQuery("getContactGroupsInContactsByContactId").setString("contactId", contactId).list();
    }

    List<ContactTypeTranslations> getAllContactTypeTranslations(int countryId, Session session) {
        return session.getNamedQuery("getAllContactTypeTranslations").setInteger("countryId", countryId).list();
    }

    List<ContactTypesInContacts> getContactTypesInContactsByContactId(String contactId, Session session) {
        return session.getNamedQuery("getContactTypesInContactsByContactId").setString("contactId", contactId).list();
    }

    public ContactTypesInContacts getContactTypesInContactsByIds(String contactID, String contactTypeID, Session session) {
        return (ContactTypesInContacts) session.getNamedQuery("getContactTypesInContactsByIds").setString("contactId", contactID).setString("contactTypeId", contactTypeID).uniqueResult();
    }

    Contacts getContacts(String contactId, Session session) {
        return (Contacts) session.getNamedQuery("getContactByContactId").setString("contactId", contactId).list().get(0);
    }

    public List<Contacts> getContactsByDefaultSearch(Set<String> groupIdSet, Session session) {
        return session.getNamedQuery("getContactByGroupIdsList").setParameterList("groupId", groupIdSet).setMaxResults(200).list();
    }

    public List<Contacts> getContactByGroupIdsListsearchString(Set<String> groupIdSet, String searchString, Session session) {
        return session.getNamedQuery("getContactByGroupIdsListsearchString").setParameterList("groupId", groupIdSet).setString("contactName", searchString).setMaxResults(200).list();
    }

    List<Contacts> getContactsBySearch(Set<String> groupIdsList, Integer countryId, String contactName, String organization, String contactGroup, Session session) {
        List<Contacts> contactsList = new ArrayList<Contacts>();
        StringBuffer queryString = new StringBuffer("from Contacts where groupId in (:groupIdsList)");

        if (contactName != null) {
            if (TCMUtil.isValidUUID(contactName)) {
                queryString.append(" and contactId = '" + contactName + "' ");
            } else {
                queryString.append(" and (contactName like '%" + contactName + "%' or contactDescription like '%" + contactName + "%') ");
            }
        }

        List<String> searchGroupIdsList = new ArrayList<String>();
        if (organization != null) {
            if (TCMUtil.isValidUUID(organization)) {
                for (String string : groupIdsList) {
                    if (organization.equalsIgnoreCase(string)) {
                        searchGroupIdsList.add(string);
                    }
                }
                if (searchGroupIdsList.isEmpty()) {
                    return contactsList;
                }
                queryString.append(" and groupId in (:searchGroupIdsList)");
            } else {
                List<GroupTranslations> groupTranslationsList = getGroupTransByGroupIdandSearchString(groupIdsList, organization, countryId, session);
                if (!groupTranslationsList.isEmpty()) {
                    searchGroupIdsList = getGroupidsListFromGroupTranslations(groupTranslationsList);
                    queryString.append(" and groupId in (:searchGroupIdsList)");
                } else {
                    return contactsList;
                }
            }

        }

        List<String> contactIdsList = new ArrayList<String>();
        if (contactGroup != null) {
            NetworkDAO networkDAO = new NetworkDAO();
            List<ContactGroups> contactGroupsList;
            if (TCMUtil.isValidUUID(contactGroup)) {
                contactGroupsList = getContactGroupByContactGroupId(groupIdsList, contactGroup, session);
            } else {
                contactGroupsList = networkDAO.getContactGroupBySearchCreterias(session, groupIdsList, contactGroup);
            }
            if (!contactGroupsList.isEmpty()) {
                List<String> contactGroupIds = getContactGroupIds(contactGroupsList);
                contactIdsList = getContactGroupsInContactsByContactGroupIds(contactGroupIds, session);
                if (!contactIdsList.isEmpty()) {
                    queryString.append(" and contactId in (:contactIdsList) ");
                } else {
                    return contactsList;
                }
            } else {
                return contactsList;
            }
        }

        queryString.append(" order by contactName asc ");

        Query query = session.createQuery(queryString.toString());
        query.setParameterList("groupIdsList", groupIdsList);
        if (!searchGroupIdsList.isEmpty()) {
            query.setParameterList("searchGroupIdsList", searchGroupIdsList);
        }
        if (!contactIdsList.isEmpty()) {
            query.setParameterList("contactIdsList", contactIdsList);
        }
        contactsList = query.setMaxResults(200).list();
        return contactsList;
    }

    CostCenters getCostCenterByCostCenterNameAndGroupId(String costCenterName, String groupId, Session session) {
        List<CostCenters> costCentersList = session.getNamedQuery("getCostCenterByCostCenterNameAndGroupId").setString("costCenterName", costCenterName).setString("groupId", groupId).list();
        return !costCentersList.isEmpty() ? costCentersList.get(0) : null;
    }

    CostCenters getCostCenterByCostCenterNumberAndGroupId(int costCenterNumber, String groupId, Session session) {
        List<CostCenters> costCentersList = session.getNamedQuery("getCostCenterByCostCenterNumberAndGroupId").setInteger("costCenterNumber", costCenterNumber).setString("groupId", groupId).list();
        return !costCentersList.isEmpty() ? costCentersList.get(0) : null;
    }

    public Set<String> getDownstreamGroupsForLoggedInGroup(String parentGroupID, List<Groups> groupsList, Session session) {
        Set<String> downstreamGroupIdsList = new HashSet<String>();
        for (Groups g : groupsList) {
            downstreamGroupIdsList.add(g.getGroupId());
            downstreamGroupIdsList.addAll(getDownStreamGroups(g.getGroupId(), downstreamGroupIdsList, session));
        }
        return downstreamGroupIdsList;
    }

    List<ServiceClientLogins> getServiceClientLoginsBySearchCriteria(String groupId, String serviceClientLoginName, String organization, int countryId, Session session) {
        StringBuffer queryString = new StringBuffer();
        queryString.append("select scl.serviceClientLoginId as serviceClientLoginId, scl.serviceClientLoginName as serviceClientLoginName, scl.clientLogin as clientLogin, scl.activeFromDate as activeFromDate, scl.activeToDate as activeToDate, scl.createdDate as createdDate, scl.groupId as groupId from ServiceClientLogins as scl " +
                " inner join groups as g on scl.groupId = g.groupId and g.groupId in(select groupId from groupTrusts  where iTrustGroupId = '" + groupId + "' or groupId = '" + groupId + "') ");

        if (organization != null) {
            if (TCMUtil.isValidUUID(organization)) {
                queryString.append(" and g.groupId = '" + organization + "' ");
            } else {
                queryString.append(" inner join groupTranslations as gts on g.groupId = gts.groupId and (gts.groupName like '%" + organization + "%' or g.organizationNumber = '" + organization + "') and gts.countryId = " + countryId + " ");
            }

        }

        if (serviceClientLoginName != null) {
            if (TCMUtil.isValidUUID(serviceClientLoginName)) {
                queryString.append(" and (scl.serviceClientLoginId = '" + serviceClientLoginName + "'  or scl.clientLogin ='" + serviceClientLoginName + "') ");
            } else {
                queryString.append(" and (scl.serviceClientLoginName like '%" + serviceClientLoginName + "%' or scl.serviceClientLoginDescription like '%" + serviceClientLoginName + "%') ");
            }

        }

        queryString.append(" order by scl.serviceClientLoginName asc");
        SQLQuery query = session.createSQLQuery(queryString.toString());
        query.addScalar("serviceClientLoginId", Hibernate.STRING);
        query.addScalar("serviceClientLoginName", Hibernate.STRING);
        query.addScalar("clientLogin", Hibernate.STRING);
        query.addScalar("activeFromDate", Hibernate.TIMESTAMP);
        query.addScalar("activeToDate", Hibernate.TIMESTAMP);
        query.addScalar("createdDate", Hibernate.TIMESTAMP);
        query.addScalar("groupId", Hibernate.STRING);
        query.setResultTransformer(Transformers.aliasToBean(ServiceClientLogins.class));
        return query.setMaxResults(200).list();
    }

    private Set<String> getDownStreamGroups(String parentGroupId, Set<String> downstreamGroupIdsList, Session session) {
        List<Groups> groupsList = getDownstreamGroupIdsList(parentGroupId, session);
//        int i = 0;
        for (Groups g : groupsList) {
            downstreamGroupIdsList.add(g.getGroupId());
//            if(i == 5) {
//                break;
//            }
//            i++;
            getDownStreamGroups(g.getGroupId(), downstreamGroupIdsList, session);
        }
        return downstreamGroupIdsList;
    }

    HashMap<String, GroupTrusts> getDownstreamTrustedGroups(List<GroupTrusts> groupTrustsList, Session session) {
        HashMap<String, GroupTrusts> hm = new HashMap<String, GroupTrusts>();
        for (GroupTrusts gt : groupTrustsList) {
            hm.put(gt.getId().getItrustGroupId(), gt);
//            hm.putAll(traverseDownStream(gt.getId().getItrustGroupId(), session));
        }
        return hm;
    }

    HashMap<String, GroupTrusts> getTrustedGroups(List<GroupTrusts> groupTrustsList, Session session) {
        HashMap<String, GroupTrusts> hm = new HashMap<String, GroupTrusts>();
        for (GroupTrusts gt : groupTrustsList) {
            hm.put(gt.getId().getGroupId(), gt);
        }
        return hm;
    }

    public List<GroupTranslations> getGroupTransBySearchStringCriteria(Set<String> groupIdsList, String searchString, int countryId, int maxResult, Session session) {
        Criteria criteria = session.createCriteria(GroupTranslations.class, "gt");
        criteria.createAlias("gt.groups", "g", CriteriaSpecification.INNER_JOIN);
        criteria.add(Restrictions.eq("gt.id.countryId", countryId));
        criteria.add(Restrictions.in("g.groupId", groupIdsList));
        if (searchString != null) {
            if (TCMUtil.isValidUUID(searchString)) {
                criteria.add(Restrictions.eq("g.groupId", searchString));
            } else {
                criteria.add(Restrictions.ilike("gt.groupName", searchString, MatchMode.ANYWHERE));
            }
        }
        criteria.addOrder(Order.asc("gt.groupName"));

        return criteria.setMaxResults(maxResult).list();
    }

//    private HashMap<String, GroupTrusts> traverseDownStream(String groupId, Session session) {
//        List<GroupTrusts> gtList = getITrustOrganizations(groupId, session);
//        HashMap<String, GroupTrusts> hm = new HashMap<String, GroupTrusts>();
//        int i = 0;
//        if (!gtList.isEmpty()) {
//            for (GroupTrusts gts : gtList) {
//                if (i == 5) {
//                    break;
//                }
//                hm.put(gts.getId().getItrustGroupId(), gts);
//                traverseDownStream(gts.getId().getItrustGroupId(), session);
//                i++;
//            }
//        }
//        return hm;
//    }
    public Set<String> getGroupTransIdsList(List<GroupTranslations> groupTranslationList) {
        Set<String> groupIdList = new HashSet();
        for (GroupTranslations gt : groupTranslationList) {
            groupIdList.add(gt.getId().getGroupId());
        }
        return groupIdList;
    }

    public List<GroupTranslations> getGroupTransByGroupIdandSearchString(Set<String> groupidSet, String groupName, int countryId, Session session) {
        return session.getNamedQuery("getGroupTransByIdandSearchString").setParameterList("groupID", groupidSet).setInteger("countryID", countryId).setString("groupName", groupName).setMaxResults(200).list();
    }

    List<Groups> getGroupsByIdandOrganizationNumber(Set<String> groupIdSet, String searchString, Session session) {
        return session.getNamedQuery("getGroupsbyIdandOrganizationNumber").setParameterList("groupId", groupIdSet).setString("organizationNumber", searchString).list();
    }

    List<ContactGroups> getContactGroupByContactGroupId(Set<String> groupIdSet, String searchString, Session session) {
        return session.getNamedQuery("getContactGroupByContactGroupId").setParameterList("groupId", groupIdSet).setString("searchString", searchString).list();
    }

    Set<String> getGroupIdsList(List<Groups> groupsList) {
        Set<String> groupIdList = new HashSet();
        for (Groups g : groupsList) {
            groupIdList.add(g.getGroupId());
        }
        return groupIdList;
    }

    public List<ObjectGroupTranslations> getObjectGroupTranslationsByIdandSearchString(Set<String> objectGroupIdList, int countryId, String searchString, Session session) {
        return session.getNamedQuery("getObjectGroupTranslationsByIdandObjectGroupName").setParameterList("objectGroupId", objectGroupIdList).setInteger("countryId", countryId).setString("searchString", searchString).list();
    }

    public List<ObjectGroupTranslations> getObjectGroupTranslationsByCountryIdandSearchString(int countryId, String searchString, Session session) {
        return session.getNamedQuery("getObjectGroupTranslationsByCountryIdandObjectGroupName").setInteger("countryId", countryId).setString("searchString", searchString).list();
    }

    public Object getObjectGroupTranslationsByIds(int countryId, String objectgroupId, Session session) {
        return session.getNamedQuery("getObjectGroupTranslationsByIds").setInteger("countryId", countryId).setString("objectGroupId", objectgroupId).uniqueResult();
    }

    public List<ObjectGroups> getObjectGroupsByGroupId(Set<String> groupIdSet, Session session) {
        return session.getNamedQuery("getObjectGroupsByGroupId").setParameterList("groupId", groupIdSet).setMaxResults(200).list();
    }

    public List<ObjectGroups> getObjectGroupsByGroupId(Set<String> groupIdSet, Session session, int maxresult) {
        return session.getNamedQuery("getObjectGroupsByGroupId").setParameterList("groupId", groupIdSet).setMaxResults(maxresult).list();
    }

    List<ContactGroups> getNonLinkedContactGroupsForContactWithOptional(Set<String> groupIdSet, List<String> contactGroupIdsList, String groupName, String contactGroupName, Integer countryId, Session session) {
        List<ContactGroups> contactGroupsList = new ArrayList<ContactGroups>();
        StringBuffer queryString = new StringBuffer("from ContactGroups where groupId in (:groupIdsList)");

        if (!contactGroupIdsList.isEmpty()) {
            queryString.append(" and contactGroupId NOT IN (:contactGroupIdsList)");
        }

        List<String> searchGroupIdsList = new ArrayList<String>();
        if (groupName != null) {
            List<GroupTranslations> groupTranslationsList = getGroupTransByGroupIdandSearchString(groupIdSet, groupName, countryId, session);
            if (!groupTranslationsList.isEmpty()) {
                searchGroupIdsList = getGroupidsListFromGroupTranslations(groupTranslationsList);
                queryString.append(" and groupId in (:searchGroupIdsList)");
            } else {
                return contactGroupsList;
            }
        }

        List<String> contactGroupIdList = new ArrayList<String>();
        if (contactGroupName != null) {
            NetworkDAO networkDAO = new NetworkDAO();
            List<ContactGroups> contactGroupList = new ArrayList<ContactGroups>();
            if (TCMUtil.isValidUUID(contactGroupName)) {
                contactGroupList = getContactGroupByContactGroupId(groupIdSet, contactGroupName, session);
            } else {
                contactGroupList = networkDAO.getContactGroupBySearchCreterias(session, groupIdSet, contactGroupName);
            }
            if (!contactGroupList.isEmpty()) {
                contactGroupIdList = getContactGroupIds(contactGroupList);
                queryString.append(" and contactGroupId in (:contactGroupIdList) ");
            } else {
                return contactGroupsList;
            }
        }

        queryString.append(" order by contactGroupName asc ");
        Query query = session.createQuery(queryString.toString());
        query.setParameterList("groupIdsList", groupIdSet);
        if (!contactGroupIdsList.isEmpty()) {
            query.setParameterList("contactGroupIdsList", contactGroupIdsList);
        }
        if (!searchGroupIdsList.isEmpty()) {
            query.setParameterList("searchGroupIdsList", searchGroupIdsList);
        }
        if (!contactGroupIdList.isEmpty()) {
            query.setParameterList("contactGroupIdList", contactGroupIdList);
        }
        contactGroupsList = query.setMaxResults(200).list();
        return contactGroupsList;
    }

    List<ContactGroups> getNonLinkedContactGroupsForContactWithoutOptional(Set<String> groupIdSet, List<String> contactGroupIdsList, Session session) {
        List<ContactGroups> contactGroupsList = new ArrayList<ContactGroups>();
        StringBuffer queryString = new StringBuffer("from ContactGroups where groupId in (:groupIdSet)");
        if (!contactGroupIdsList.isEmpty()) {
            queryString.append(" and contactGroupId NOT IN (:contactGroupIdsList)");
        }

        queryString.append(" order by contactGroupName asc ");

        Query query = session.createQuery(queryString.toString());
        query.setParameterList("groupIdSet", groupIdSet);
        if (!contactGroupIdsList.isEmpty()) {
            query.setParameterList("contactGroupIdsList", contactGroupIdsList);
        }
        contactGroupsList = query.setMaxResults(200).list();
        return contactGroupsList;
    }

    Set<String> getObjectGroupIdsList(List<ObjectGroups> objectGroupsList) {
        Set<String> objectGroupIdsList = new HashSet<String>();
        for (ObjectGroups og : objectGroupsList) {
            objectGroupIdsList.add(og.getObjectGroupId());
        }
        return objectGroupIdsList;
    }

    public List<String> getOGMDeviceObjectGroupId(String deviceId, Session session) {
        Criteria ogmDeviceCriteria = session.createCriteria(Ogmdevice.class, "ogmd");
        ogmDeviceCriteria.add(Restrictions.eq("ogmd.id.deviceId", deviceId));
        ogmDeviceCriteria.setProjection(Projections.property("ogmd.id.objectGroupId"));
        return ogmDeviceCriteria.list();
    }

    List<String> getOGMContainersObjectGroupId(String containerId, Session session) {
        Criteria ogmContainersCriteria = session.createCriteria(Ogmcontainers.class, "ogmc");
        ogmContainersCriteria.add(Restrictions.eq("ogmc.id.containerId", containerId));
        ogmContainersCriteria.setProjection(Projections.property("ogmc.id.objectGroupId"));
        return ogmContainersCriteria.list();
    }

    List<ObjectGroups> getObjectGroupsNotLinkedToOGMDevice(List<String> ogmDeviceObjectGroupIds, Set<String> groupIdsList, Session session) {
        List<ObjectGroups> objectGroupsList = new ArrayList<ObjectGroups>();
        DeviceDAO deviceDAO = new DeviceDAO();
        UserDAO userDAO = new UserDAO();
        if (!ogmDeviceObjectGroupIds.isEmpty()) {
            objectGroupsList = deviceDAO.getObjectGroupsByGroupIdsandNotObjectGroupIds(session, groupIdsList, ogmDeviceObjectGroupIds);
        } else {
            objectGroupsList = userDAO.getObjectGroupsByGroupId(groupIdsList, session);
        }
        return objectGroupsList;
    }

    List<Ogmdevice> getObjectGroupsLinkedToOGMDevice(String deviceId, String userId, Session session) {
        UserDAO userDAO = new UserDAO();
        Users2 users2 = userDAO.getUserById(userId, session);
        List<GroupTrusts> groupTrustsList = getGroupTrustByGroupID(users2.getGroupId(), session);
        Set<String> groupIdsList = getGroupIdsList(users2.getGroupId(), groupTrustsList);
        List<Ogmdevice> ogm = new ArrayList<Ogmdevice>();
        List<List<String>> spList = TCMUtil.splitStringList(new ArrayList<String>(groupIdsList), 2000);
        for (List<String> list : spList) {
            Criteria criteria = session.createCriteria(Ogmdevice.class, "ogmd");
            criteria.createAlias("ogmd.objectGroups", "og", CriteriaSpecification.INNER_JOIN);
            criteria.add(Restrictions.eq("ogmd.id.deviceId", deviceId));
            criteria.add(Restrictions.in("og.groupId", list));
            criteria.addOrder(Order.desc("og.createdDate"));
            ogm.addAll(criteria.setMaxResults(200).list());
        }

        return ogm;
    }

//    List<Ogmdevice> getObjectGroupsLinkedToOGMDevice(String deviceId, String userId, Session session) {
//        UserDAO userDAO = new UserDAO();
//        Users2 users2 = userDAO.getUserById(userId, session);
//
//        StringBuffer queryString = new StringBuffer();
//        queryString.append("select ogmd.objectGroupId as objectGroupId, ogmd.scheduleId as scheduleId from ogmDevice as ogmd inner join objectgroups as og on ogmd.objectGroupId = og.objectGroupId " +
//                " inner join groups as g on og.groupId = g.groupId and g.groupId in(select groupId from groupTrusts where iTrustGroupId = '"+users2.getGroupId()+"' or groupId = '"+users2.getGroupId()+"') " +
//                " and ogmd.deviceId = '"+deviceId+"' order by og.createdDate desc");
//        SQLQuery query = session.createSQLQuery(queryString.toString());
//        query.addScalar("objectGroupId", Hibernate.STRING);
//        query.addScalar("scheduleId", Hibernate.STRING);
//        query.setResultTransformer(Transformers.aliasToBean(Ogmdevice.class));
//        return query.setMaxResults(200).list();
//
//    }

//    List<Ogmcontainers> getObjectGroupsLinkedToOGMContainers(String containerId, String userId, Session session) {
//        UserDAO userDAO = new UserDAO();
//        Users2 users2 = userDAO.getUserById(userId, session);
//        List<GroupTrusts> groupTrustsList = getGroupTrustByGroupID(users2.getGroupId(), session);
//        Set<String> groupIdsList = getGroupIdsList(users2.getGroupId(), groupTrustsList);
//
//        Criteria criteria = session.createCriteria(Ogmcontainers.class, "ogmc");
//        criteria.createAlias("ogmc.objectGroups", "og", CriteriaSpecification.INNER_JOIN);
//        criteria.add(Restrictions.eq("ogmc.id.containerId", containerId));
//        criteria.add(Restrictions.in("og.groupId", groupIdsList));
//        criteria.addOrder(Order.desc("og.createdDate"));
//        List<Ogmcontainers> ogmContainersList = criteria.setMaxResults(200).list();
//
//        return ogmContainersList;
//    }
    List<Ogmcontainers> getObjectGroupsLinkedToOGMContainers(String containerId, String userId, Session session) {
        UserDAO userDAO = new UserDAO();
        Users2 users2 = userDAO.getUserById(userId, session);

        StringBuffer queryString = new StringBuffer();
        queryString.append("select ogmc.objectGroupId as objectGroupId, ogmc.scheduleId as scheduleId from ogmContainers as ogmc inner join objectgroups as og on ogmc.objectGroupId = og.objectGroupId " +
                " inner join groups as g on og.groupId = g.groupId and g.groupId in(" + TCMUtil.getGroupTrust(users2.getGroupId()) + ") " +
                " and ogmc.containerId = '" + containerId + "' order by og.createdDate desc");
        SQLQuery query = session.createSQLQuery(queryString.toString());
        query.addScalar("objectGroupId", Hibernate.STRING);
        query.addScalar("scheduleId", Hibernate.STRING);
        query.setResultTransformer(Transformers.aliasToBean(Ogmcontainers.class));
        return query.setMaxResults(200).list();

    }

    boolean updateOrganization(Group groupXML, Users2 users2, ApplicationSettings as, Session session) {
        Transaction tx = null;
        boolean result = false;
        ProductsDAO productDAO = new ProductsDAO();
        ApplicationDAO applicationDAO = new ApplicationDAO();
        CountryDAO countryDAO = new CountryDAO();
        DbManager dbManager = new DbManager();
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        GregorianCalendar gc_20years = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        gc_20years.add(GregorianCalendar.YEAR, 20);
        GregorianCalendar gc_10years = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        gc_10years.add(GregorianCalendar.YEAR, 10);
        tx = session.beginTransaction();
        try {
            String newGroupName = groupXML.getGroupNames().getGroupName().get(0).getValue();
            String newGroupDesc = null;
            if (groupXML.getGroupDescriptions() != null) {
                newGroupDesc = groupXML.getGroupDescriptions().getGroupDescription().get(0).getValue();
            }
            String groupId = groupXML.getGroupID();
            Groups groups = getGroupByGroupId(groupId, session);
            if (groups != null) {
                if (groupXML.getGroupParentID() != null) {
                    groups.setGroupParentId(groupXML.getGroupParentID());
                }
                if (groupXML.getGroupTypeID() != null) {
                    groups.setGroupTypeId(groupXML.getGroupTypeID());
                }
                if (groupXML.getOrganizationNumber() != null) {
                    groups.setOrganizationNumber(groupXML.getOrganizationNumber());
                }
                groups.setUserId(users2.getUserId());
                groups.setUpdatedDate(gc.getTime());


                session.saveOrUpdate(groups);
            } else {
                groups = new Groups();
                if (groupXML.getGroupID() != null) {
                    groups.setGroupId(groupId);
                }
                if (groupXML.getGroupParentID() != null) {
                    groups.setGroupParentId(groupXML.getGroupParentID());
                }
                if (groupXML.getGroupTypeID() != null) {
                    groups.setGroupTypeId(groupXML.getGroupTypeID());
                }
                if (groupXML.getOrganizationNumber() != null) {
                    groups.setOrganizationNumber(groupXML.getOrganizationNumber());
                }
                groups.setFunctionId(0);
                groups.setDisplayOrder(0);
                groups.setIsWebShopUser(0);
                groups.setUserId(users2.getUserId());
                groups.setCreatedDate(gc.getTime());
                groups.setUpdatedDate(gc.getTime());
                groups.setDomainId(users2.getDomains().getDomainId());
                session.saveOrUpdate(groups);
            }
            List<Country> countries = countryDAO.getAllCountries(session);
            for (Country c : countries) {
                GroupTranslations gt = getGroupTranslationsByIds(groupId, c.getCountryId(), session);
                if (gt != null) {
                    gt.setId(new GroupTranslationsId(groupId, c.getCountryId()));
                    gt.setGroupName(newGroupName);
                    if (newGroupDesc != null) {
                        gt.setGroupDescription(newGroupDesc);
                    } else {
                        gt.setGroupDescription(null);
                    }
                    gt.setUserId(users2.getUserId());
                    gt.setUpdatedDate(gc.getTime());
                    session.saveOrUpdate(gt);
                } else {
                    gt = new GroupTranslations();
                    GroupTranslationsId gt_id = new GroupTranslationsId(groupId, c.getCountryId());
                    gt.setId(gt_id);
                    gt.setGroupName(newGroupName);
                    if (newGroupDesc != null) {
                        gt.setGroupDescription(newGroupDesc);
                    } else {
                        gt.setGroupDescription(null);
                    }
                    gt.setUserId(users2.getUserId());
                    gt.setCreatedDate(gc.getTime());
                    gt.setUpdatedDate(gc.getTime());
                    session.saveOrUpdate(gt);
                }
            }

            List<ServiceClientLogins> serviceClientLoginsList = productDAO.getServiceClientLoginsByGroupId(groupId, session);
            if (!serviceClientLoginsList.isEmpty()) {
//                for (ServiceClientLogins serviceClientLogins : serviceClientLoginsList) {
//                    serviceClientLogins.setServiceClientLoginName(newGroupName);
//                    if (newGroupDesc != null) {
//                        serviceClientLogins.setServiceClientLoginDescription(newGroupDesc);
//                    } else {
//                        serviceClientLogins.setServiceClientLoginDescription(null);
//                    }
//                    serviceClientLogins.setUserId(users2.getUserId());
//                    serviceClientLogins.setUpdatedDate(gc.getTime());
//                    session.saveOrUpdate(serviceClientLogins);
//                }
            } else {
                ServiceClientLogins serviceClientLogins = new ServiceClientLogins();
                serviceClientLogins.setServiceClientLoginId(UUID.randomUUID().toString());
                serviceClientLogins.setServiceClientLoginName(newGroupName);
                if (newGroupDesc != null) {
                    serviceClientLogins.setServiceClientLoginDescription(newGroupDesc);
                } else {
                    serviceClientLogins.setServiceClientLoginDescription(null);
                }
                serviceClientLogins.setGroupId(groupId);
                serviceClientLogins.setIsEnabled(1);
                serviceClientLogins.setActiveFromDate(gc.getTime());
                serviceClientLogins.setActiveToDate(gc_20years.getTime());
                serviceClientLogins.setUserId(users2.getUserId());
                serviceClientLogins.setCreatedDate(gc.getTime());
                serviceClientLogins.setUpdatedDate(gc.getTime());
                session.saveOrUpdate(serviceClientLogins);
            }

            GroupDefaultAgreement gda = productDAO.getGroupDefaultAgreement(groupId, session);
            if (gda != null) {
                gda.setAgreements(new Agreements(groupXML.getAgreementID()));
                gda.setLastUpdatedByUserId(users2.getUserId());
                gda.setUpdatedDate(gc.getTime());
                session.saveOrUpdate(gda);
            } else {
                gda = new GroupDefaultAgreement();
                gda.setGroupId(groupId);
                gda.setAgreements(new Agreements(groupXML.getAgreementID()));
                gda.setLastUpdatedByUserId(users2.getUserId());
                gda.setCreatedDate(gc.getTime());
                gda.setUpdatedDate(gc.getTime());
                session.saveOrUpdate(gda);
            }

            if (groupXML.getGroupLimitPackageID() == null) {
                GroupLimits groupLimits = getGroupLimitsByGroupId(groupId, session);
                if (groupLimits != null) {
                    session.delete(groupLimits);
                }
                session.flush();
                session.clear();
            } else {
                GroupLimitPackages glp = productDAO.getGroupLimitPackagesById(groupXML.getGroupLimitPackageID(), session);
                GroupLimits groupLimits = null;
                if (glp != null) {
                    groupLimits = getGroupLimitsByGroupId(groupId, session);
                    if (groupLimits != null) {
                        groupLimits.setGroupLimitPackages(glp);
                        if (glp.getMaxNumberOfDevices() != null) {
                            groupLimits.setMaxNumberOfDevices(glp.getMaxNumberOfDevices());
                        }
                        if (glp.getMaxNumberOfUsers() != null) {
                            groupLimits.setMaxNumberOfUsers(glp.getMaxNumberOfUsers());
                        }
                        if (glp.getMaxNumberOfSubscriptions() != null) {
                            groupLimits.setMaxNumberOfSubscriptions(glp.getMaxNumberOfSubscriptions());
                        }
                        if (glp.getMaxNumberOfChildGroups() != null) {
                            groupLimits.setMaxNumberOfChildGroups(glp.getMaxNumberOfChildGroups());
                        }
                        if (glp.getMaxNumberOfTrustingGroups() != null) {
                            groupLimits.setMaxNumberOfTrustingGroups(glp.getMaxNumberOfTrustingGroups());
                        }
                        if (glp.getMaxDataStorageSize() != null) {
                            groupLimits.setMaxDataStorageSize(glp.getMaxDataStorageSize());
                        }
                        groupLimits.setLastUpdatedByUserId(users2.getUserId());
                        groupLimits.setUpdatedDate(gc.getTime());
                        session.saveOrUpdate(groupLimits);
                    } else {
                        groupLimits = new GroupLimits();
                        groupLimits.setGroupId(groupId);
                        groupLimits.setGroupLimitPackages(glp);
                        if (glp.getMaxNumberOfDevices() != null) {
                            groupLimits.setMaxNumberOfDevices(glp.getMaxNumberOfDevices());
                        }
                        if (glp.getMaxNumberOfUsers() != null) {
                            groupLimits.setMaxNumberOfUsers(glp.getMaxNumberOfUsers());
                        }
                        if (glp.getMaxNumberOfSubscriptions() != null) {
                            groupLimits.setMaxNumberOfSubscriptions(glp.getMaxNumberOfSubscriptions());
                        }
                        if (glp.getMaxNumberOfChildGroups() != null) {
                            groupLimits.setMaxNumberOfChildGroups(glp.getMaxNumberOfChildGroups());
                        }
                        if (glp.getMaxNumberOfTrustingGroups() != null) {
                            groupLimits.setMaxNumberOfTrustingGroups(glp.getMaxNumberOfTrustingGroups());
                        }
                        if (glp.getMaxDataStorageSize() != null) {
                            groupLimits.setMaxDataStorageSize(glp.getMaxDataStorageSize());
                        }
                        groupLimits.setLastUpdatedByUserId(users2.getUserId());
                        groupLimits.setCreatedDate(gc.getTime());
                        groupLimits.setUpdatedDate(gc.getTime());
                        session.saveOrUpdate(groupLimits);
                    }
                }
            }

            if (groupXML.getGroupApplicationPackages() != null) {
                List<GroupApplicationPackages> groupapList = applicationDAO.getGroupApplicationPackagesbyGroupId(groupId, session);
                if (!groupapList.isEmpty()) {
                    for (GroupApplicationPackages gaps : groupapList) {
                        session.delete(gaps);
                    }
                    session.flush();
                    session.clear();
                }
                for (se.info24.groupsjaxb.GroupApplicationPackages gaps : groupXML.getGroupApplicationPackages()) {
                    List<GroupApplicationPackage> gapList = gaps.getGroupApplicationPackage();
                    for (GroupApplicationPackage gap : gapList) {
                        GroupApplicationPackages groupAppPackages = applicationDAO.getGroupApplicationPackagesbyGroupIdAndPackageId(groupId, gap.getApplicationPackageID(), session);
                        if (groupAppPackages != null) {
                            groupAppPackages.setAgreements(new Agreements(gap.getAgreementID()));
                            groupAppPackages.setIsEnabled(1);
                            groupAppPackages.setIsTrial(0);
                            groupAppPackages.setIsPendingDelete(0);
                            groupAppPackages.setOrderedByUserId(users2.getUserId());
                            groupAppPackages.setLastUpdatedByUserId(users2.getUserId());
                            groupAppPackages.setUpdatedDate(gc.getTime());
                            session.saveOrUpdate(groupAppPackages);
                        } else {
                            groupAppPackages = new GroupApplicationPackages();
                            groupAppPackages.setId(new GroupApplicationPackagesId(groupId, gap.getApplicationPackageID()));
                            groupAppPackages.setAgreements(new Agreements(gap.getAgreementID()));
                            groupAppPackages.setIsEnabled(1);
                            groupAppPackages.setIsTrial(0);
                            groupAppPackages.setIsPendingDelete(0);
                            groupAppPackages.setActiveFromDate(gc.getTime());
                            groupAppPackages.setActiveToDate(gc_20years.getTime());
                            groupAppPackages.setOrderedByUserId(users2.getUserId());
                            groupAppPackages.setLastUpdatedByUserId(users2.getUserId());
                            groupAppPackages.setCretedDate(gc.getTime());
                            groupAppPackages.setUpdatedDate(gc.getTime());
                            session.saveOrUpdate(groupAppPackages);
                        }
                    }
                }
            }

            if (groupXML.getGroupVisibleApplicationSolutions() != null) {
                List<se.info24.pojo.GroupVisibleApplicationSolutions> groupvasList = applicationDAO.getGroupVisibleApplicationSolutionsbyGroupid(groupId, session);
                if (!groupvasList.isEmpty()) {
                    for (se.info24.pojo.GroupVisibleApplicationSolutions gvass : groupvasList) {
                        session.delete(gvass);
                    }
                    session.flush();
                    session.clear();
                }
                for (GroupVisibleApplicationSolutions gvasList : groupXML.getGroupVisibleApplicationSolutions()) {
                    List<String> appSolutionIdList = gvasList.getApplicationSolutionID();
                    for (String str : appSolutionIdList) {
                        se.info24.pojo.GroupVisibleApplicationSolutions gvas = applicationDAO.getGroupVisibleApplicationSolutionsByIds(groupId, str, session);
                        if (gvas != null) {
                            gvas.setId(new GroupVisibleApplicationSolutionsId(groupId, str));
                            gvas.setLastUpdatedByUserId(users2.getUserId());
                            gvas.setUpdatedDate(gc.getTime());
                            session.saveOrUpdate(gvas);
                        } else {
                            gvas = new se.info24.pojo.GroupVisibleApplicationSolutions();
                            gvas.setId(new GroupVisibleApplicationSolutionsId(groupId, str));
                            gvas.setLastUpdatedByUserId(users2.getUserId());
                            gvas.setCreatedDate(gc.getTime());
                            gvas.setUpdatedDate(gc.getTime());
                            session.saveOrUpdate(gvas);
                        }
                    }
                }
            }

            session.flush();
            session.clear();

            if (groupXML.getGroupVisibleApplicationPackages() != null) {
                List<se.info24.pojo.GroupVisibleApplicationPackages> groupvapList = applicationDAO.getGroupVisibleApplicationPackagesbyGroupid(groupId, session);
                if (!groupvapList.isEmpty()) {
                    for (se.info24.pojo.GroupVisibleApplicationPackages gvaps : groupvapList) {
                        session.delete(gvaps);
                    }
                    session.flush();
                    session.clear();
                }
                for (GroupVisibleApplicationPackages gvapList : groupXML.getGroupVisibleApplicationPackages()) {
                    List<String> appPackagesIdList = gvapList.getApplicationPackageID();
                    for (String str : appPackagesIdList) {
                        se.info24.pojo.GroupVisibleApplicationPackages gvap = applicationDAO.getGroupVisibleApplicationPackagesByIds(groupId, str, session);
                        if (gvap != null) {
                            gvap.setId(new GroupVisibleApplicationPackagesId(groupId, str));
                            gvap.setLastUpdatedByUserId(users2.getUserId());
                            gvap.setUpdatedDate(gc.getTime());
                            session.saveOrUpdate(gvap);
                        } else {
                            gvap = new se.info24.pojo.GroupVisibleApplicationPackages();
                            gvap.setId(new GroupVisibleApplicationPackagesId(groupId, str));
                            gvap.setLastUpdatedByUserId(users2.getUserId());
                            gvap.setCreatedDate(gc.getTime());
                            gvap.setUpdatedDate(gc.getTime());
                            session.saveOrUpdate(gvap);
                        }
                    }
                }
            }

            UserRoleMemberships2 urm = null;
            List<UserRoleMemberships2> urmList = dbManager.getUserRoleIdByUserId(session, users2.getUserId());
            if (!urmList.isEmpty()) {
                urm = urmList.get(0);
            }

            String iTrustGroupId = null;
            List<GroupTrusts> grouptrustsList = getGroupTrustByGroupID(groupId, session);
            if (grouptrustsList.isEmpty()) {
                if (users2.getGroupId().equalsIgnoreCase(as.getApplicationSettingValue())) {
                    GroupTrusts groupTrusts = new GroupTrusts();
                    groupTrusts.setId(new GroupTrustsId(groupId, as.getApplicationSettingValue(), urm.getId().getUserRoleId()));
                    groupTrusts.setActiveFromDate(gc.getTime());
                    groupTrusts.setActiveToDate(gc_10years.getTime());
                    groupTrusts.setLastUpdatedByUserId(users2.getUserId());
                    groupTrusts.setCreatedDate(gc.getTime());
                    groupTrusts.setUpdatedDate(gc.getTime());
                    session.saveOrUpdate(groupTrusts);
                } else if (groupXML.getGroupParentID().equalsIgnoreCase(as.getApplicationSettingValue())) {
                    iTrustGroupId = groupXML.getGroupParentID();
                    for (int i = 0; i < 2; i++) {
                        GroupTrusts groupTrusts = new GroupTrusts();
                        groupTrusts.setId(new GroupTrustsId(groupId, iTrustGroupId, urm.getId().getUserRoleId()));
                        groupTrusts.setActiveFromDate(gc.getTime());
                        groupTrusts.setActiveToDate(gc_10years.getTime());
                        groupTrusts.setLastUpdatedByUserId(users2.getUserId());
                        groupTrusts.setCreatedDate(gc.getTime());
                        groupTrusts.setUpdatedDate(gc.getTime());
                        session.saveOrUpdate(groupTrusts);
                        iTrustGroupId = users2.getGroupId();
                    }
                } else if (groupXML.getGroupParentID().equalsIgnoreCase(users2.getGroupId())) {
                    iTrustGroupId = groupXML.getGroupParentID();
                    for (int i = 0; i < 2; i++) {
                        GroupTrusts groupTrusts = new GroupTrusts();
                        groupTrusts.setId(new GroupTrustsId(groupId, iTrustGroupId, urm.getId().getUserRoleId()));
                        groupTrusts.setActiveFromDate(gc.getTime());
                        groupTrusts.setActiveToDate(gc_10years.getTime());
                        groupTrusts.setLastUpdatedByUserId(users2.getUserId());
                        groupTrusts.setCreatedDate(gc.getTime());
                        groupTrusts.setUpdatedDate(gc.getTime());
                        session.saveOrUpdate(groupTrusts);
                        iTrustGroupId = as.getApplicationSettingValue();
                    }
                } else {
                    iTrustGroupId = as.getApplicationSettingValue();
                    for (int i = 0; i < 3; i++) {
                        if (i == 2) {
                            iTrustGroupId = groupXML.getGroupParentID();
                        }
                        GroupTrusts groupTrusts = new GroupTrusts();
                        groupTrusts.setId(new GroupTrustsId(groupId, iTrustGroupId, urm.getId().getUserRoleId()));
                        groupTrusts.setActiveFromDate(gc.getTime());
                        groupTrusts.setActiveToDate(gc_10years.getTime());
                        groupTrusts.setLastUpdatedByUserId(users2.getUserId());
                        groupTrusts.setCreatedDate(gc.getTime());
                        groupTrusts.setUpdatedDate(gc.getTime());
                        session.saveOrUpdate(groupTrusts);
                        iTrustGroupId = users2.getGroupId();
                    }
                }
            }

            if (groupXML.getAddress() != null) {
                Address add = groupXML.getAddress();
                String addressId = UUID.randomUUID().toString();
                Addresses address = new Addresses();
                address.setAddressId(addressId);
                address.setAddressStreet(add.getAddressStreet());
                address.setAddressStreet2(add.getAddressStreet2());
                address.setAddressSuite(add.getAddressSuite());
                address.setAddressCity(add.getAddressCity());
                address.setAddressZip(add.getAddressZip());
                address.setAddressRegion(add.getAddressRegion());
                address.setAddressDescription(add.getAddressDesc());
                address.setAddressType(new AddressType(3));
                address.setCountry(new Country(add.getCountryID()));
                address.setUserId(users2.getUserId());
                address.setCreatedDate(gc.getTime());
                address.setUpdatedDate(gc.getTime());
                session.saveOrUpdate(address);
                session.flush();
                session.clear();

                ObjectAddresses objectAddresses = new ObjectAddresses();
                objectAddresses.setId(new ObjectAddressesId(groupId, 3));
                objectAddresses.setAddressId(addressId);
                objectAddresses.setLastUpdatedUserId(users2.getUserId());
                objectAddresses.setCreatedDate(gc.getTime());
                objectAddresses.setUpdatedDate(gc.getTime());
                session.saveOrUpdate(objectAddresses);

            }

            result = true;
            if (result) {
                tx.commit();
            }
            result = true;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            if (tx != null) {
                tx.rollback();
            }
            result = false;
        }
        return result;
    }

    public GroupLimits getGroupLimitsByGroupId(String groupId, Session session) {
        try {
            return (GroupLimits) session.getNamedQuery("getGroupLimitsById").setString("groupId", groupId).uniqueResult();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public List<GroupTranslations> getGroupTransSearchCountryid(Set<String> groupId, String searchString, int countryID, Session session) {
        return session.getNamedQuery("getGroupTransSearchCountryid").setParameterList("groupID", groupId).setString("groupName", searchString).setInteger("countryID", countryID).list();
    }

    private List<String> getContactGroupIds(List<ContactGroups> contactGroupsList) {
        List<String> contactGroupIdsList = new ArrayList<String>();
        for (ContactGroups cg : contactGroupsList) {
            contactGroupIdsList.add(cg.getContactGroupId());
        }
        return contactGroupIdsList;
    }

    private List<String> getContactGroupsInContactsByContactGroupIds(List<String> contactGroupIds, Session session) {
        Criteria criteria = session.createCriteria(ContactGroupsInContacts.class, "cgic");
        criteria.add(Restrictions.in("cgic.id.contactGroupId", contactGroupIds));
        criteria.setProjection(Projections.distinct(Projections.property("cgic.id.contactId")));
        return criteria.list();
    }

    private boolean groupExists(String groupName, int countryID, Session session) {
        try {
            session.getNamedQuery("getGroupTranslationsByGrpNameandCountryId").setString("groupName", groupName).setInteger("countryID", countryID).list().get(0);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<GroupTranslations> getGroupTranslationsByGrpNameandCountryId(String groupName, int countryID, Session session) {
        return session.getNamedQuery("getGroupTranslationsByGrpNameandCountryId").setString("groupName", groupName).setInteger("countryID", countryID).list();

    }

    public List<Users2> getUsersList(List<String> userId, Session session) {
        List<Users2> usersList = null;
        try {
            usersList = session.getNamedQuery("getUsers2ByUserIdList").setParameterList("userId", userId).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
        }
        return usersList;
    }

    List<GroupTypeTranslations> getGroupTypeTranslations(List<String> groupTypeIdList, int countryid, Session session) {
        List<GroupTypeTranslations> groupTypeTransList = null;
        try {
            groupTypeTransList = session.getNamedQuery("getGroupTypeTranslationsByGroupTypeIdList").setParameterList("groupTypeId", groupTypeIdList).setInteger("countryId", countryid).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
        }
        return groupTypeTransList;
    }

    List<Groups> getGroupsByIdList(Set<String> groupIdSet, Session session) {
        List<Groups> groupsList = null;
        try {
            groupsList = session.getNamedQuery("getGroupsByIdList").setParameterList("groupID", groupIdSet).setMaxResults(100).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
        }
        return groupsList;
    }

    public List<GroupTranslations> getGroupTranslationsByGroupId(Set<String> groupIdSet, int countryid, int maxResult, Session session) {
        if (maxResult == 0) {
            return session.getNamedQuery("getGroupTranslationByIds").setParameterList("groupID", groupIdSet).setInteger("countryID", countryid).list();
        } else {
            return session.getNamedQuery("getGroupTranslationByIds").setParameterList("groupID", groupIdSet).setInteger("countryID", countryid).setMaxResults(maxResult).list();

        }
    }

    public List<GroupTranslations> getGroupTranslationsById(List<String> groupIdSet, int countryid, Session session) {
        try {
            return session.getNamedQuery("getGroupTranslationByGrpIDandCountryId").setParameterList("groupID", groupIdSet).setInteger("countryID", countryid).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    List<ApplicationSolutionTranslations> getApplicationSolutionTranslations(List<String> applicationSolutionIdList, Session session) {
        return session.getNamedQuery("getApplicationSolutionTranslationsById").setParameterList("applicationSolutionId", applicationSolutionIdList).list();
    }

    List<ApplicationPackageTranslations> getApplicationPackageTranslations(List<String> applicationPackageIdList, Session session) {
        return session.getNamedQuery("getApplicationPackageTranslationsById").setParameterList("applicationPackageId", applicationPackageIdList).list();
    }

    public List<ApplicationPackages> getApplicationPackages(List<String> applicationPackageIdList, Session session) {
        return session.getNamedQuery("getApplicationPackagesByAppPacId").setParameterList("applicationPackageId", applicationPackageIdList).list();
    }

    public CostCenters getCostCenterById(String costCenterId, Session session) {
        List<CostCenters> list = session.getNamedQuery("getCostCenterById").setString("costCenterId", costCenterId).list();
        return list.isEmpty() ? null : list.get(0);
    }

    public List<CostCenters> getCostCenterByIds(List<String> costCenterId, Session session) {
        return session.getNamedQuery("getCostCenterByIds").setParameterList("costCenterId", costCenterId).list();
    }

    public List<CostCenters> getCostCenterByGroupIdForDropDown(Session session, String groupid, int number) {
        return session.getNamedQuery("getCostCenterByGroupIdForDropDown").setString("groupId", groupid).setMaxResults(number).list();
    }

    public List<CostCenters> getCostCenterByGroupIdForDropDownSearchString(Session session, String groupid, int number, String searchString) {
        return session.getNamedQuery("getCostCenterByGroupIdForDropDownSearchString").setString("groupId", groupid).setString("costCenterName", searchString).setMaxResults(number).list();
    }

    public GroupDefaultUserAlias getGroupDefaultUserAliasByGroupId(Session session, String groupId) {
        List<GroupDefaultUserAlias> groupDefaultUserAlias = session.getNamedQuery("getGroupDefaultUserAliasByGroupId").setString("groupId", groupId).list();
        return !groupDefaultUserAlias.isEmpty() ? groupDefaultUserAlias.get(0) : null;
    }

    public List<GroupAlias> getGroupAliasBYGroupId(Session session, String groupId) {
        return session.getNamedQuery("getGroupAliasBYGroupId").setString("groupId", groupId).setMaxResults(5).list();
    }

    CostCenters getCostCenterByCostCenterIdAndGroupId(String costCenterId, String groupId, Session session) {
        return (CostCenters) session.getNamedQuery("getCostCenterByCostCenterIdAndGroupId").setString("costCenterId", costCenterId).setString("groupId", groupId).uniqueResult();
    }

    GroupDefaultUserAlias getGroupDefaultUserAliasByCostCenterIdAndGroupId(String groupId, String costCenterId, Session session) {
        return (GroupDefaultUserAlias) session.getNamedQuery("getGroupDefaultUserAliasByIdandCostCenterId").setString("groupId", groupId).setString("costCenterId", costCenterId).uniqueResult();
    }

    public AccessLog getAccessLogById(Session session, String accessLogRowId) {
        return (AccessLog) session.getNamedQuery("getAccessLogById").setString("accessLogRowId", accessLogRowId).uniqueResult();
    }

    public List<EventTypeTranslations> getEventTypeTranslationsbyCountryId(Session session, String countryId) {
        return session.getNamedQuery("getEventTypeTranslationsbyCountryId").setString("countryId", countryId).list();
    }

    boolean addUpdateObjectGroup(ObjectGroups objectGroups, ObjectGroupTranslations objectGroupTranslation, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            if (objectGroups != null) {
                session.saveOrUpdate(objectGroups);

            }
            if (objectGroupTranslation != null) {
                session.saveOrUpdate(objectGroupTranslation);

            }
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    public List<OgmuserAlias> getOgmuserAliasByObjectGroupId(Session session, String objectGroupId) {
        return session.getNamedQuery("getOgmuserAliasByObjectGroupId").setString("objectGroupId", objectGroupId).list();
    }

    public List<Ogmcontainers> getOgmContainersByObjectGroupId(Session session, String objectGroupId) {
        return session.getNamedQuery("getOgmContainersByObjectGroupId").setString("objectGroupId", objectGroupId).list();
    }

    public List<ObjectGroupTranslations> getObjectGroupTranslationsBySearchString(Session session, int countryId, String searchString) {
        return session.getNamedQuery("getObjectGroupTranslationsBySearchString").setInteger("countryId", countryId).setString("searchString", searchString).list();
    }

    public List<Groups> getGroupsByIDs(Session session, List<String> groupID) {
        return session.getNamedQuery("getGroupsByIDs").setParameterList("groupID", groupID).list();
    }

    List<ServiceClientLogins> getServiceClientLoginsByGroupIdListAndSearchString(Set<String> groupIdSet, Session session, String searchString, int maxResults) {
        return session.getNamedQuery("getServiceClientLoginsByGroupIdListAndSearchString").setParameterList("groupId", groupIdSet).setParameter("searchString", searchString).setMaxResults(maxResults).list();
    }

    public List<ServiceClientLogins> getServiceClientLoginsByGroupIdList(Set<String> groupIdSet, Session session, int maxResults) {
        return session.getNamedQuery("getServiceClientLoginsByGroupIds").setParameterList("groupId", groupIdSet).setMaxResults(maxResults).list();
    }

    public List<ServiceClientLogins> getServiceClientLoginsByGroupId(String groupId, Session session, int maxResults) {
        return session.getNamedQuery("getServiceClientLoginsByGroupId").setString("groupId", groupId).setMaxResults(maxResults).list();
    }

    public List<GroupTrusts> getGroupTrustsByGroupIdAndITrustID(Session session, String groupId, String iTrustgroupID) {
        return session.getNamedQuery("getGroupTrustsByGroupIdAndITrustID").setParameter("groupId", groupId).setParameter("iTrustgroupID", iTrustgroupID).list();
    }

    public List<GroupTrusts> isGroupTrustExists(Session session, String groupID, String iTrustgroupID, String roleID) {
        return session.getNamedQuery("getGroupTrustsByIds").setString("groupID", groupID).setString("iTrustgroupID", iTrustgroupID).setString("roleID", roleID).list();
    }

    public List<GroupTranslations> getGroupTranslationsBySearchStringandCountryId(Session session, String groupName, int countryId) {
        return session.getNamedQuery("getGroupTranslationsBySearchStringandCountryId").setString("groupName", groupName).setInteger("countryID", countryId).list();
    }

    public List<TransactionResult> getTransactionResultBySearchStringGroupS(Session session, List<String> userGroupIdSet, String gc_start_time, String gc_stop_time) {
        return session.getNamedQuery("getTransactionResultBySearchStringGroupS").setString("fromDate", gc_start_time).setString("toDate", gc_stop_time).setParameterList("searchString", userGroupIdSet).list();
    }

    public List<Object> getTransactionProductsByTransactionIdsCreatedDate(Session ismOperationsSession, Set<String> transactionId) {

        List<Object> tp = new ArrayList<Object>();
        List<List<String>> splitList = TCMUtil.splitStringList(new ArrayList(transactionId), 2000);
        for (List<String> list : splitList) {
//            StringBuffer queryString = new StringBuffer("select tp.productVariantId as productVariantId,tp.productVariantSku as productVariantSku," +
//                    "tp.quantityUnit as quantityUnit,tp.ppu as ppu,SUM(tp.quantity) as quantity,tr.currencyId as currencyId from " +
//                    "TransactionProducts as tp INNER JOIN TransactionResult as tr ON tp.transactionId = tr.transactionId " +
//                    "where tp.transactionId in (:transactionId) group by tp.productVariantSku,tp.quantityUnit,tp.productVariantId,tr.currencyId," +
//                    "tp.productVariantName,tp.ppu order by tp.productVariantName");

            StringBuffer queryString = new StringBuffer("select distinct tps.productVariantId as productVariantId,x.ProductVariantName, x.productVariantSku as productVariantSku," +
                    "x.quantityUnit as quantityUnit,x.ppu as ppu,x.quantity as quantity,x.currencyId as currencyId, tps.productVariantName as ProductVariantName from " +
                    "TransactionProducts as tps inner join (select tp.productVariantName as ProductVariantName,tp.productVariantSku as productVariantSku," +
                    "tp.quantityUnit as quantityUnit,tp.ppu as ppu,SUM(tp.quantity) as quantity,tr.currencyId as currencyId from " +
                    "TransactionProducts as tp INNER JOIN TransactionResult as tr ON tp.transactionId = tr.transactionId " +
                    "where tp.transactionId in (:transactionId) group by tp.productVariantSku,tp.quantityUnit,tr.currencyId," +
                    "tp.productVariantName,tp.ppu  )x  on x.productVariantSku = tps.productVariantSku and " +
                    "x.ppu= tps.ppu and x.ProductVariantName = tps.ProductVariantName and ISNULL(x.quantityUnit ,0)= Isnull(tps.quantityUnit,0) order by tps.productVariantName");
//            inner join TransactionResult as trs ON tps.transactionId = trs.transactionId and x.currencyId = trs.CurrencyID

            SQLQuery query = ismOperationsSession.createSQLQuery(queryString.toString());
            query.addScalar("productVariantId", Hibernate.STRING);
            query.addScalar("productVariantSku", Hibernate.STRING);
            query.addScalar("quantityUnit", Hibernate.STRING);
            query.addScalar("ppu", Hibernate.STRING);
            query.addScalar("quantity", Hibernate.STRING);
            query.addScalar("currencyId", Hibernate.STRING);

            query.setParameterList("transactionId", list);
            tp.addAll(query.list());
        }
        if (tp.size() > 1000) {
            tp = tp.subList(0, 1000);
        }
        return tp;

//        return session.getNamedQuery("getTransactionProductsByTransactionIdsCreatedDate").setParameterList("transactionId", transactionId).setMaxResults(1000).list();
    }

    public List<GroupAddresses> getGroupAddressesByGroupIdAddressTypeId(Session session, String groupid) {
        return session.getNamedQuery("getGroupAddressesByGroupIdAddressTypeId").setString("groupId", groupid).setString("addressTypeId", "3").list();
    }

    public List<UserAlias> getUserAliasByLoggedInUserID(Session session, String userId,int limit) {
        if(limit == 0){
            return session.getNamedQuery("getUserAliasByLoggedInUserID").setString("userID", userId).list();
        }
        return session.getNamedQuery("getUserAliasByLoggedInUserID").setString("userID", userId).setMaxResults(limit).list();
    }

    Object getFAQById(Session session, String faqId) {
        return session.getNamedQuery("getFAQById").setString("faqid", faqId).uniqueResult();
    }

    boolean saveOrUpdateFAQ(Faq faq, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(faq);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    boolean deleteFAQ(Session session, Faq faq) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(faq);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    List<Object> searchFAQ(Session session, Users2 loggedInUser, int countryId, String question, String answer, String groupid) {
        List<Object> deviceList = new ArrayList<Object>();
        StringBuffer queryString = new StringBuffer();

        queryString.append("Select faq.faqid,faq.groupId,faq.question,faq.answer,gt.groupName from ISM.dbo.FAQ as faq inner join ISM.dbo.GroupTranslations as gt on faq.GroupID = gt.GroupID where gt.CountryID = '"+countryId+"'" +
                "and faq.groupid in (" + TCMUtil.getGroupTrust(loggedInUser.getGroupId()) + ")");

        if(groupid != null){
            if(TCMUtil.isValidUUID(groupid)){
                queryString.append("and faq.groupid = '"+groupid+"'");
            }else{
                queryString.append("and faq.GroupID in ( select GroupID from ISM.dbo.GroupTranslations where GroupName like '%"+groupid+"%')");
            }
        }

        if(question != null){
            queryString.append("and faq.Question like '%"+question+"%'");
        }
        if(answer != null){
            queryString.append("and faq.Answer like '%"+answer+"%'");
        }
        
        queryString.append(" order by question asc ");
        SQLQuery query = session.createSQLQuery(queryString.toString());
        TCMUtil.loger(getClass().getName(), queryString.toString(), "Info");
        query.addScalar("faqid", Hibernate.STRING);
        query.addScalar("groupId", Hibernate.STRING);
        query.addScalar("question", Hibernate.STRING);
        query.addScalar("answer", Hibernate.STRING);
        query.addScalar("groupName", Hibernate.STRING);
      //  query.setMaxResults(200);
        deviceList = query.list();

        return deviceList;
    }

}

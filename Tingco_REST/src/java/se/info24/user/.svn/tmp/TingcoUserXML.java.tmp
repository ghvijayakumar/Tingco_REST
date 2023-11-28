/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.user;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import org.hibernate.Session;
import se.info24.device.DeviceDAO;
import se.info24.ismOperationsPojo.FraudLog;
import se.info24.ismOperationsPojo.ObjectUsageRecords;
import se.info24.pojo.Addresses;
import se.info24.pojo.Currency;
import se.info24.pojo.Device;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupTranslationsId;
import se.info24.pojo.ObjectGroupTranslations;
import se.info24.pojo.ObjectTags;
import se.info24.pojo.OgmuserAlias;
import se.info24.pojo.UserAddresses;
import se.info24.pojo.UserAlias;
import se.info24.pojo.UserAliasDetails;
import se.info24.pojo.UserAliasOrders;
import se.info24.pojo.Users2;
import se.info24.restUtil.RestUtilDAO;
import se.info24.tingcoEV.MsgStatus;
import se.info24.tingcoEV.ObjectFactory;
import se.info24.tingcoEV.TingcoEV;
import se.info24.tingcoEV.UsageRecord;
import se.info24.tingcoEV.UsageRecords;
import se.info24.tingcoEV.UserAliasOrder;
import se.info24.tingcoEV.UserKey;
import se.info24.tingcoEV.Userkeys;
import se.info24.usersjaxb.Address;
import se.info24.usersjaxb.FraudLogs;
import se.info24.usersjaxb.GroupID;
import se.info24.usersjaxb.TingcoUsers;
import se.info24.usersjaxb.User;
import se.info24.usersjaxb.UserAliases;
import se.info24.usersjaxb.Users;
import se.info24.util.HibernateUtil;
import se.info24.util.TCMUtil;

/**
 *
 * @author Hitha
 */
public class TingcoUserXML {

    private TingcoEV userXML;
    private Userkeys userkeys = new Userkeys();
    private UserDAO userDao = new UserDAO();
    private DeviceDAO deviceDao = new DeviceDAO();
    private UsageRecords usageReocords = new UsageRecords();
    private TingcoUsers users;
    se.info24.usersjaxb.ObjectFactory objectFactory;

    public TingcoUserXML() {
        objectFactory = new se.info24.usersjaxb.ObjectFactory();
    }

    public TingcoEV buildTingcoObjectUsageRecords(TingcoEV usageRecords, String deviceId, List<ObjectUsageRecords> objectUsageRecords, Session session) {
        if (!objectUsageRecords.isEmpty()) {
            UsageRecord usageRecord = null;
            int seqNo = 0;
            ObjectFactory obj = new ObjectFactory();
            for (ObjectUsageRecords our : objectUsageRecords) {
                usageRecord = obj.createUsageRecord();
                usageRecord.setSeqNo(++seqNo);
                if (our.getUsageRecordId() != null) {
                    usageRecord.setUsageRecordID(our.getUsageRecordId());
                }
                GregorianCalendar gc = new GregorianCalendar();
                if (our.getUsageStartTime() != null) {
                    gc.setTime(our.getUsageStartTime());
                    try {
                        usageRecord.setUsageStartTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    } catch (DatatypeConfigurationException ex) {
                        TCMUtil.exceptionLog(getClass().getName(), ex);
                    }
                }

                if (our.getUsageStopTime() != null) {
                    gc.setTime(our.getUsageStopTime());
                    try {
                        usageRecord.setUsageStopTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    } catch (DatatypeConfigurationException ex) {
                        TCMUtil.exceptionLog(getClass().getName(), ex);
                    }
                }

                if (our.getObjectId() != null) {
                    usageRecord.setObjectID(our.getObjectId());
                    Device device = deviceDao.getDeviceById(our.getObjectId(), session);
                    if (device != null) {
                        usageRecord.setDeviceName(device.getDeviceName());
                    }
                }

                if (our.getDataItemName() != null) {
                    usageRecord.setDataItemName(our.getDataItemName());
                }

                if (our.getUsageVolume() != null) {
                    usageRecord.setUsageVolume(our.getUsageVolume());
                }

                if (our.getUsageUnitName() != null) {
                    usageRecord.setUsageUnitName(our.getUsageUnitName());
                }
                usageReocords.getUsageRecord().add(usageRecord);
                usageRecords.setUsageRecords(usageReocords);
            }
        }
        return usageRecords;
    }

    public TingcoEV buildTingcoUserAliasXML(String userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<UserAlias> list = userDao.getUserAliasByUserID(session, userId);
        if (list.size() == 0) {
            userXML.getMsgStatus().setResponseCode(-2);
            userXML.getMsgStatus().setResponseText("No UserAlias for entered UserID");
            return userXML;
        } else {
            ObjectFactory obj = new ObjectFactory();
            UserKey userKeys = null;
            int seqNo = 0;
            for (UserAlias us : list) {
                userKeys = obj.createUserKey();
                userKeys.setSeqNo(++seqNo);
                if (us.getUserAliasId() != null) {
                    userKeys.setUserAliasId(us.getUserAliasId());
                }

                if (us.getUserAlias() != null) {
                    userKeys.setUserAlias(us.getUserAlias());
                }

                if (us.getUserAliasTypes().getUserAliasTypeId() != null) {
                    userKeys.setUserAliasTypeID(us.getUserAliasTypes().getUserAliasTypeId());
                }

                if (us.getGroupId() != null) {
                    userKeys.setGroupID(us.getGroupId());
                }

                if (us.getCreditAmount() != null) {
                    userKeys.setCreditAmount(us.getCreditAmount());
                }

                if (us.getCreditAmountCurrencyId() != null) {
                    userKeys.setCreditAmountCurrencyID(us.getCreditAmountCurrencyId());
                }

                if (us.getFirstName() != null) {
                    userKeys.setFirstName(us.getFirstName());
                }

                if (us.getLastName() != null) {
                    userKeys.setLastName(us.getLastName());
                }

                GregorianCalendar gc = new GregorianCalendar();
                if (us.getActiveFromDate() != null) {
                    gc.setTime(us.getActiveFromDate());
                    try {
                        userKeys.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    } catch (DatatypeConfigurationException ex) {
                        TCMUtil.exceptionLog(getClass().getName(), ex);
                    }
                }

                if (us.getActiveToDate() != null) {
                    gc.setTime(us.getActiveToDate());
                    try {
                        userKeys.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    } catch (DatatypeConfigurationException ex) {
                        TCMUtil.exceptionLog(getClass().getName(), ex);
                    }
                }


                if (us.getCredits() != null) {
                    userKeys.setCredits(us.getCredits());
                }

                if (us.getIsEnabled() != null) {
                    userKeys.setIsEnabled(us.getIsEnabled());
                }
                userkeys.getUserKey().add(userKeys);
                userXML.setUserkeys(userkeys);
            }
            return userXML;
        }
    }

    public TingcoEV buildTingcoUserTemplate() throws DatatypeConfigurationException {
        ObjectFactory objectFactory = new ObjectFactory();
        userXML = objectFactory.createTingcoEV();
        userXML.setCreateRef("Info24");
        userXML.setMsgVer(new BigDecimal("1.0"));
        userXML.setRegionalUnits("Metric");
        userXML.setTimeZone("UTC");

        userXML.setMsgID(UUID.randomUUID().toString());
        userXML.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));

        MsgStatus msgStatus = new MsgStatus();
        msgStatus.setResponseCode(0);
        msgStatus.setResponseText("OK");

        userXML.setMsgStatus(msgStatus);

        return userXML;
    }

    public TingcoUsers buildUserTemplate() throws DatatypeConfigurationException {
        users = objectFactory.createTingcoUsers();
        users.setCreateRef("Info24");
        users.setMsgVer(new BigDecimal("1.0"));
        users.setRegionalUnits("Metric");
        users.setTimeZone("UTC");

        users.setMsgID(UUID.randomUUID().toString());
        users.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));

        se.info24.usersjaxb.MsgStatus msgStatus = new se.info24.usersjaxb.MsgStatus();
        msgStatus.setResponseCode(0);
        msgStatus.setResponseText("OK");

        users.setMsgStatus(msgStatus);

        return users;
    }

    public TingcoEV buildTingcoUsageRecordsXML(String userAlias, String month, String year) {
        Session session = HibernateUtil.getISMOperationsSessionFactory().openSession();
        List<ObjectUsageRecords> list = userDao.getObjectUsageRecordsByUserAlias(session, userAlias, month, year);
        if (list.size() == 0) {
            userXML.getMsgStatus().setResponseCode(-2);
            userXML.getMsgStatus().setResponseText("No UsageRecord for entered UserAlias");
            return userXML;
        } else {
            ObjectFactory obj = new ObjectFactory();
            UsageRecord usageRecord = null;
            int seqNo = 0;
            for (ObjectUsageRecords our : list) {
                usageRecord = obj.createUsageRecord();
                usageRecord.setSeqNo(++seqNo);
                if (our.getUsageRecordId() != null) {
                    usageRecord.setUsageRecordID(our.getUsageRecordId());
                }
                GregorianCalendar gc = new GregorianCalendar();
                if (our.getUsageStartTime() != null) {
                    gc.setTime(our.getUsageStartTime());
                    try {
                        usageRecord.setUsageStartTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    } catch (DatatypeConfigurationException ex) {
                        TCMUtil.exceptionLog(getClass().getName(), ex);
                    }
                }

                if (our.getUsageStopTime() != null) {
                    gc.setTime(our.getUsageStopTime());
                    try {
                        usageRecord.setUsageStopTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    } catch (DatatypeConfigurationException ex) {
                        TCMUtil.exceptionLog(getClass().getName(), ex);
                    }
                }

                if (our.getObjectName() != null) {
                    usageRecord.setDeviceName(our.getObjectName());
                }

                if (our.getDataItemName() != null) {
                    usageRecord.setDataItemName(our.getDataItemName());
                }

                if (our.getUsageVolume() != null) {
                    usageRecord.setUsageVolume(our.getUsageVolume());
                }

                if (our.getUsageUnitName() != null) {
                    usageRecord.setUsageUnitName(our.getUsageUnitName());
                }
                usageReocords.getUsageRecord().add(usageRecord);
                userXML.setUsageRecords(usageReocords);
            }
            return userXML;
        }
    }

    public TingcoEV buildUserAliasOrder(TingcoEV tingcoEV, List<UserAliasOrders> uaol, String timeZoneGMToffset) throws DatatypeConfigurationException {
        se.info24.tingcoEV.UserAliasOrders uaos = new se.info24.tingcoEV.UserAliasOrders();
        int i = 1;
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        GregorianCalendar gc = new GregorianCalendar();
        for (UserAliasOrders userAliasOrders : uaol) {
            UserAliasOrder uao = new UserAliasOrder();
            uao.setSeqNo(i++);
            uao.setUserAliasOrderID(userAliasOrders.getUserAliasOrderId());
            uao.setUserID(userAliasOrders.getUserId());
            uao.setGroupID(userAliasOrders.getGroupId());
//            gc.setTime(userAliasOrders.getOrderDate());
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, userAliasOrders.getOrderDate()));
            uao.setOrderDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            uao.setOrderDateTCMV3(lFormat.format(gc.getTime()));
            uao.setOrderedUserAlias(userAliasOrders.getOrderedUserAlias());
            uao.setOrderComment(userAliasOrders.getOrderComment());
            uao.setIsDelivered(userAliasOrders.getIsDelivered());
            uao.setFirstName(userAliasOrders.getFirstName());
            uao.setLastName(userAliasOrders.getLastName());
            uao.setDeliveryAddressRow1(userAliasOrders.getDeliveryAddressRow1());
            uao.setDeliveryAddressRow2(userAliasOrders.getDeliveryAddressRow2());
            uao.setDeliveryZipCode(userAliasOrders.getDeliveryZipCode());
            uao.setDeliveryCity(userAliasOrders.getDeliveryCity());
            uao.setDeliveryCountry(userAliasOrders.getDeliveryCountry());
            uao.setProduct(userAliasOrders.getProduct());
            uaos.getUserAliasOrder().add(uao);
        }
        tingcoEV.setUserAliasOrders(uaos);
        return tingcoEV;
    }

    public TingcoUsers buildUserAddressDetails(List<UserAddresses> addresses, TingcoUsers tUser) {

        Users users = new Users();

        for (UserAddresses userAddresses : addresses) {
            User u = new User();
            Addresses address = userAddresses.getAddresses();

            Address ad = new Address();
            ad.setAddressID(address.getAddressId());
            ad.setAddressCity(address.getAddressCity());
            if (address.getAddressDepth() != null) {
                ad.setAddressDepth(address.getAddressDepth().floatValue());
            }

            ad.setAddressDesc(address.getAddressDescription());
            ad.setAddressEmail(address.getAddressEmail());
            ad.setAddressFax(address.getAddressFax());
            ad.setAddressIM(address.getAddressIm());

            if (address.getAddressLatitude() != null) {
                ad.setAddressLatitude(address.getAddressLatitude().floatValue());
            }
            if (address.getAddressLongitude() != null) {
                ad.setAddressLongitude(address.getAddressLongitude().floatValue());
            }

            ad.setCountryID(address.getCountry().getCountryId());
            ad.setAddressMobile(address.getAddressMobile());
            ad.setAddressName(address.getAddressName());
            ad.setAddressPhone(address.getAddressPhone());
            ad.setAddressRegion(address.getAddressRegion());
            ad.setAddressStreet(address.getAddressStreet());
            ad.setAddressStreet2(address.getAddressStreet2());
            ad.setAddressSuite(address.getAddressSuite());
            ad.setAddressWeb(address.getAddressWeb());
            ad.setAddressZip(address.getAddressZip());

            u.setAddress(ad);
            users.getUser().add(u);
        }

        tUser.setUsers(users);
        return tUser;
    }

    public TingcoUsers buildGetUserForUserAlias(TingcoUsers tingcoUsers, List<Users2> users2List, List<GroupTranslations> groupTranslation) {
        Users users = new Users();
        se.info24.usersjaxb.User user = null;
        for (Users2 users2 : users2List) {
            user = new User();
            user.setUserID(users2.getUserId());
            user.setFirstName(users2.getFirstName());
            user.setLastName(users2.getLastName());
            se.info24.usersjaxb.GroupID gg = new se.info24.usersjaxb.GroupID();
            for (GroupTranslations groupTranslations : groupTranslation) {
                if (users2.getGroupId().equalsIgnoreCase(groupTranslations.getId().getGroupId())) {
                    gg.setGroupName(groupTranslations.getGroupName());
                }
            }
            gg.setValue(users2.getGroupId());
            user.setGroupID(gg);
            users.getUser().add(user);
        }
        tingcoUsers.setUsers(users);
        return tingcoUsers;
    }

    public TingcoUsers buildGetUserAliasForGroup(TingcoUsers tingcoUsers, List<UserAlias> userAliasList, List<GroupTranslations> groupTranslation) {
        Users users = new Users();
        se.info24.usersjaxb.User user = new User();
        se.info24.usersjaxb.UserAliases useralias = null;
        int seq = 0;
        for (UserAlias users2 : userAliasList) {
            String fullName = null;
            useralias = new UserAliases();
            useralias.setSeqNo(seq++);
            useralias.setUserAliasID(users2.getUserAliasId());
            useralias.setUserAlias(users2.getUserAlias());
            useralias.setFirstName(users2.getFirstName());
            useralias.setLastName(users2.getLastName());
            fullName = users2.getFirstName() + " " + users2.getLastName();
            for (GroupTranslations groupTranslations : groupTranslation) {
                if (users2.getGroupId().equalsIgnoreCase(groupTranslations.getId().getGroupId())) {
                    useralias.setGroupName(groupTranslations.getGroupName());
                    fullName = fullName + " (" + groupTranslations.getGroupName() + ")";
                }
            }
            useralias.setUserFullName(fullName);
            useralias.setGroupID(users2.getGroupId());
            useralias.setUserAlias(users2.getUserAlias());
            user.getUserAliases().add(useralias);
        }
        users.getUser().add(user);
        tingcoUsers.setUsers(users);
        return tingcoUsers;
    }

    public TingcoUsers buildGetObjectGroupsForUserAlias(TingcoUsers tingcoUsers, List<OgmuserAlias> oUAList, List<ObjectGroupTranslations> oGTList) {
        Users users = new Users();
        se.info24.usersjaxb.User user = new User();
        se.info24.usersjaxb.UserAliases userAlias = null;
        se.info24.usersjaxb.ObjectGroupTranslations oGT = null;
        for (ObjectGroupTranslations objectGroupTranslations : oGTList) {
            userAlias = new UserAliases();
            for (OgmuserAlias ogmuserAlias : oUAList) {
                if (objectGroupTranslations.getId().getObjectGroupId().equalsIgnoreCase(ogmuserAlias.getId().getObjectGroupId())) {
                    userAlias.setUserAliasID(ogmuserAlias.getId().getUserAliasId());
                }
            }
            oGT = new se.info24.usersjaxb.ObjectGroupTranslations();
            oGT.setCountryID(objectGroupTranslations.getId().getCountryId());
            oGT.setObjectGroupDescription(objectGroupTranslations.getObjectGroupDescription());
            oGT.setObjectGroupID(objectGroupTranslations.getId().getObjectGroupId());
            oGT.setObjectGroupName(objectGroupTranslations.getObjectGroupName());
            userAlias.getObjectGroupTranslations().add(oGT);
            user.getUserAliases().add(userAlias);
        }
        users.getUser().add(user);
        tingcoUsers.setUsers(users);
        return tingcoUsers;
    }

    public TingcoUsers buildUserkeyDetail(TingcoUsers tingcoUsers, UserAlias useralias, UserAliasDetails uAD, Currency currency, String timeZoneGMToffset) throws DatatypeConfigurationException {
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Users users = new Users();
        se.info24.usersjaxb.User user = new User();
        se.info24.usersjaxb.UserAliases userAlias = new UserAliases();
        userAlias.setUserAliasID(useralias.getUserAliasId());
        userAlias.setUserAlias(useralias.getUserAlias());
//        gc.setTime(useralias.getActiveFromDate());
        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, useralias.getActiveFromDate()));
        userAlias.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc) + "");
        userAlias.setActiveFromDateTCMV3(df.format(gc.getTime()));
//        gc.setTime(useralias.getActiveToDate());
        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, useralias.getActiveToDate()));
        userAlias.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc) + "");
        userAlias.setActiveToDateTCMV3(df.format(gc.getTime()));
        if (useralias.getCredits() != null) {
            userAlias.setCredits(new BigDecimal(useralias.getCredits()));
        }
        if (useralias.getCreditAmount() != null) {
            userAlias.setCreditAmount(new BigDecimal(useralias.getCreditAmount()));
        }
        if (useralias.getIsEnabled() != null) {
            userAlias.setIsEnabled(String.valueOf(useralias.getIsEnabled()));
        }
        if (useralias.getCreditAmountCurrencyId() != null) {
            userAlias.setCreditAmountCurrencyID(new BigDecimal(useralias.getCreditAmountCurrencyId()));
        }
        if (useralias.getFirstName() != null) {
            userAlias.setFirstName(useralias.getFirstName());
        }
        if (useralias.getLastName() != null) {
            userAlias.setLastName(useralias.getLastName());
        }
        if (uAD != null) {
            se.info24.usersjaxb.UserAliasDetails userAliasdetail = new se.info24.usersjaxb.UserAliasDetails();
            if (uAD.getIsCreditCard() != null) {
                userAliasdetail.setIsCreditCard(String.valueOf(uAD.getIsCreditCard()));
            }
            if (uAD.getIsServiceKey() != null) {
                userAliasdetail.setIsServiceKey(String.valueOf(uAD.getIsServiceKey()));
            }
            if (uAD.getCreditLimitPerPurchase() != null) {
                userAliasdetail.setCreditLimitPerPurchase(uAD.getCreditLimitPerPurchase().toString());
            }
            userAliasdetail.setBlockedReason(uAD.getBlockedReason());
            userAlias.getUserAliasDetails().add(userAliasdetail);
        }

        if (currency != null) {
            userAlias.setCurrencyISOCharCode(currency.getCurrencyIsocharCode());
        }
        user.getUserAliases().add(userAlias);
        users.getUser().add(user);
        tingcoUsers.setUsers(users);
        return tingcoUsers;
    }

    public TingcoUsers buildUserAliasBySearchCriteria(TingcoUsers tingcoUsers, List<UserAlias> userAliasList, String timeZoneGMToffset, List<GroupTranslations> groupTranslationsList, Session session) throws DatatypeConfigurationException {
        Users userss = new Users();
        User user = new User();
        int seqNo = 1;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        for (UserAlias ua : userAliasList) {
            UserAliases userAlias = new UserAliases();
            userAlias.setSeqNo(seqNo++);

            userAlias.setUserAliasID(ua.getUserAliasId());
            userAlias.setUserAlias(ua.getUserAlias());
            userAlias.setUserAliasFullName((ua.getFirstName() != null ? ua.getFirstName() : "") + " " + (ua.getLastName() != null ? ua.getLastName() : "")); //both firstname and lastname are combined
            for (GroupTranslations groupTranslations : groupTranslationsList) {
                if (groupTranslations.getId().getGroupId().equalsIgnoreCase(ua.getGroupId())) {
                    userAlias.setGroupID(groupTranslations.getId().getGroupId());
                    userAlias.setGroupName(groupTranslations.getGroupName());
                    break;
                }
            }
            if (ua.getActiveToDate() != null) {
                GregorianCalendar gc = new GregorianCalendar();
//                gc.setTime(df.parse(ua.getActiveToDate()));
                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, ua.getActiveToDate()));
                userAlias.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc) + "");
                userAlias.setActiveToDateTCMV3(df.format(gc.getTime()));
            }
            if (ua.getIsEnabled() != null) {
                userAlias.setIsEnabled(String.valueOf(ua.getIsEnabled()));
            }
            if (ua.getCreditAmount() != null) {
                userAlias.setCreditAmount(new BigDecimal(ua.getCreditAmount()));
            }
            if (ua.getCredits() != null) {
                userAlias.setCredits(new BigDecimal(ua.getCredits()));
            }

            if (ua.getUserId() != null) {
                userAlias.setUserID(ua.getUserId());
                Users2 users2 = userDao.getUserById(ua.getUserId(), session);
                if (users2 != null) {
                    userAlias.setUserFullName(users2.getFirstName() + " " + users2.getLastName());
                }
            }
            ObjectTags ot = (ObjectTags) session.get(ObjectTags.class, ua.getUserAliasId());
            if (ot != null) {
                if (ot.getSearchTags().length() < 15) {
                    userAlias.setTag(ot.getSearchTags());
                } else {
                    userAlias.setTag(ot.getSearchTags().substring(0, 15));
                }
            }
            user.getUserAliases().add(userAlias);
        }
        userss.getUser().add(user);
        tingcoUsers.setUsers(userss);
        return tingcoUsers;
    }

    public TingcoUsers buildGetUserAlias(List<UserAlias> userAliases, TingcoUsers tingcoUsers) {
        Users userss = new Users();
        User user = new User();
        int seqNo = 1;
        Collections.sort(userAliases, new Comparator<se.info24.pojo.UserAlias>() {

            public int compare(se.info24.pojo.UserAlias o1, se.info24.pojo.UserAlias o2) {
                return o1.getUserAlias().compareToIgnoreCase(o2.getUserAlias());
            }
        });
        for (UserAlias ua : userAliases) {
            UserAliases userAlias = new UserAliases();
            userAlias.setSeqNo(seqNo++);
            userAlias.setUserAliasID(ua.getUserAliasId());
            userAlias.setUserAlias(ua.getUserAlias());
            if (ua.getFirstName() != null) {
                userAlias.setFirstName(ua.getFirstName());
            }
            if (ua.getLastName() != null) {
                userAlias.setLastName(ua.getLastName());
            }
            userAlias.setUserAliasFullName((ua.getFirstName() != null ? ua.getFirstName() : "") + " " + (ua.getLastName() != null ? ua.getLastName() : ""));
            user.getUserAliases().add(userAlias);
        }
        userss.getUser().add(user);
        tingcoUsers.setUsers(userss);
        return tingcoUsers;
    }

    public TingcoUsers buildGetUserAliasByUserId(List<UserAlias> userAliases, TingcoUsers tingcoUsers) {
        Users userss = new Users();
        User user = new User();
        int seqNo = 1;
        for (UserAlias ua : userAliases) {
            UserAliases userAlias = new UserAliases();
            userAlias.setSeqNo(seqNo++);
            userAlias.setUserAliasID(ua.getUserAliasId());
            userAlias.setUserAlias(ua.getUserAlias());
            userAlias.setIsEnabled(String.valueOf(ua.getIsEnabled()));
            String fullName = "";
//            userAlias.setUserAliasFullName(value);
            if (ua.getFirstName() != null) {
                userAlias.setFirstName(ua.getFirstName());
                fullName = fullName + ua.getFirstName() + " ";
            }
            if (ua.getLastName() != null) {
                userAlias.setLastName(ua.getLastName());
                fullName = fullName + ua.getLastName() + " ";
            }
            if (ua.getSocialSecurityNumber() != null) {
                userAlias.setSocialSecurityNumber(ua.getSocialSecurityNumber());
            }
            fullName = fullName + "(" + ua.getUserAlias() + ")";
            userAlias.setUserAliasFullName(fullName);
            user.getUserAliases().add(userAlias);
        }
        userss.getUser().add(user);
        tingcoUsers.setUsers(userss);
        return tingcoUsers;
    }

    TingcoUsers buildFraudLogDetails(TingcoUsers tingcoUsers, FraudLog fl, String timeZoneGMToffset, int countryId, Session session) {
        GregorianCalendar gc = new GregorianCalendar();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Users userss = new Users();
        User user = new User();
        UserAliases userAlias = new UserAliases();
        FraudLogs fraudLogs = new FraudLogs();
        se.info24.usersjaxb.FraudLog fraudLog = new se.info24.usersjaxb.FraudLog();
        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, fl.getCreatedDate()));
        fraudLog.setCreatedDate(dateFormat.format(gc.getTime()));
        fraudLog.setFraudText(fl.getFraudText());
        UserAlias ua = (UserAlias) session.get(UserAlias.class, fl.getUserAliasId());
        GroupTranslations gt = (GroupTranslations) session.get(GroupTranslations.class, new GroupTranslationsId(ua.getGroupId(), countryId));
        GroupID groupID = new GroupID();
        groupID.setValue(ua.getGroupId());
        groupID.setGroupName(gt.getGroupName());
        fraudLog.setGroupID(groupID);
        fraudLogs.setFraudLog(fraudLog);
        userAlias.getFraudLogs().add(fraudLogs);
        userAlias.setUserAlias(ua.getUserAlias());
        userAlias.setFirstName(ua.getFirstName());
        userAlias.setLastName(ua.getLastName());
        user.getUserAliases().add(userAlias);
        userss.getUser().add(user);
        tingcoUsers.setUsers(userss);
        return tingcoUsers;
    }

    TingcoUsers buildFraudLogDetailsBySearchCriteria(TingcoUsers tingcoUsers, List<se.info24.usersjaxb.FraudLog> fraudLogList, String timeZoneGMToffset) throws ParseException {
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Users userss = new Users();
        User user = new User();
        UserAliases userAlias = new UserAliases();
        for (se.info24.usersjaxb.FraudLog fl : fraudLogList) {
            FraudLogs fraudLogs = new FraudLogs();
            se.info24.usersjaxb.FraudLog fraudLog = new se.info24.usersjaxb.FraudLog();
            fraudLog.setFraudLogID(fl.getFraudLogID());
            fraudLog.setDeviceID(fl.getDeviceID());
            fraudLog.setDeviceName(fl.getDeviceName());
            fraudLog.setUserAliasID(fl.getUserAliasID());
            fraudLog.setUserAlias(fl.getUserAlias());
            if (fl.getIsChecked() != null) {
                if (fl.getIsChecked().equalsIgnoreCase("1")) {
                    fraudLog.setIsChecked("Yes");
                } else {
                    fraudLog.setIsChecked("No");
                }
            } else {
                fraudLog.setIsChecked("No");
            }
            fraudLog.setFraudText(fl.getFraudText());
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, dateFormat.parse(fl.getCreatedDate())));
            fraudLog.setCreatedDate(dateFormat.format(gc.getTime()));
            fraudLogs.setFraudLog(fraudLog);
            userAlias.getFraudLogs().add(fraudLogs);
        }
        user.getUserAliases().add(userAlias);
        userss.getUser().add(user);
        tingcoUsers.setUsers(userss);
        return tingcoUsers;
    }

    public TingcoUsers buildGetUserKeyByGroup(TingcoUsers tingcoUsers, List<Object> objectlist) {
        Users userss = new Users();
        User user = new User();
        int sq = 0;
        for (Iterator itr = objectlist.iterator(); itr.hasNext();) {
            UserAliases userAlias = new UserAliases();
            userAlias.setSeqNo(sq++);
            String useraS = "";
            Object[] row = (Object[]) itr.next();
            for (int i = 0; i < row.length; i++) {
                if (i % 2 == 0) {
                    if (row[i] != null) {
                        userAlias.setUserAliasID(row[i].toString());
                    }
                    if (row[i + 1] != null) {
                        useraS += row[i + 1].toString();
                    }

                    if (row[i + 2] != null) {
                        useraS = useraS + "(" + row[i + 2].toString();
                    }
                    if (row[i + 3] != null) {
                        useraS = useraS + " " + row[i + 3].toString().trim() + ")";
                    }
                    i = i + 3;
                }
            }
            userAlias.setUserAlias(useraS);
            user.getUserAliases().add(userAlias);
        }
        userss.getUser().add(user);
        tingcoUsers.setUsers(userss);
        return tingcoUsers;
    }
}

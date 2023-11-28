/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.jaxbUtil;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import org.hibernate.Session;
import se.info24.group.GroupDAO;
import se.info24.ismOperationsPojo.Blacklist;
import se.info24.permission.PermissionDAO;
import se.info24.pojo.Country;
import se.info24.pojo.Currency;
import se.info24.pojo.FunctionAreas;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupTrusts;
import se.info24.pojo.Groups;
import se.info24.pojo.ObjectAddresses;
import se.info24.pojo.ObjectGroupTranslations;
import se.info24.pojo.ObjectTags;
import se.info24.pojo.OgmuserAlias;
import se.info24.pojo.PermissionOperations;
import se.info24.pojo.PermissionTranslations;
import se.info24.pojo.SupportOrganizationUsers;
import se.info24.pojo.UserAlias;
import se.info24.pojo.UserAliasDetails;
import se.info24.pojo.UserLog;
import se.info24.pojo.UserRoleObjectPermissions2;
import se.info24.pojo.UserRoles2;
import se.info24.pojo.UserTimeZones2;
import se.info24.pojo.UserTypeTranslations2;
import se.info24.pojo.Users2;
import se.info24.restUtil.RestUtilDAO;
import se.info24.user.UserDAO;
import se.info24.usersjaxb.Address;
import se.info24.usersjaxb.Addresses;
import se.info24.usersjaxb.BlackList;
import se.info24.usersjaxb.CountryID;
import se.info24.usersjaxb.Function;
import se.info24.usersjaxb.GroupID;
import se.info24.usersjaxb.MsgStatus;
import se.info24.usersjaxb.OGMUserAlias;
import se.info24.usersjaxb.ObjectFactory;
import se.info24.usersjaxb.Operation;
import se.info24.usersjaxb.Operations;
import se.info24.usersjaxb.Permission;
import se.info24.usersjaxb.Permissions;
import se.info24.usersjaxb.TingcoUsers;
import se.info24.usersjaxb.User;
import se.info24.usersjaxb.UserAliases;
import se.info24.usersjaxb.UserRole;
import se.info24.usersjaxb.UserRoles;
import se.info24.usersjaxb.UserTimeZones;
import se.info24.usersjaxb.UserTypeID;
import se.info24.usersjaxb.UserTypes;
import se.info24.usersjaxb.Users;

/**
 *
 * @author Sekhar, Vijayakumar
 */
public class TingcoUserXML {

    UserDAO userDAO = new UserDAO();
    GroupDAO groupDAO = new GroupDAO();

    public TingcoUsers buildBlackList(TingcoUsers tingcoUsers, List<Object> blackList, String timeZoneGMToffset) throws ParseException, DatatypeConfigurationException {
        Users users = new Users();
        GregorianCalendar gc = new GregorianCalendar();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Iterator itr = blackList.iterator(); itr.hasNext();) {
            Object[] row = (Object[]) itr.next();
            User user = new User();
            BlackList blackListjaxb = new BlackList();
            UserAliases ua = new UserAliases();
            for (int i = 0; i < row.length; i++) {
                if (i % 2 == 0) {
                    if (row[i] != null) {
                        blackListjaxb.setBlackListID(row[i].toString());
                    }
                    if (row[i + 1] != null) {
                        blackListjaxb.setStartValue(row[i + 1].toString());
                    }
                    if (row[i + 2] != null) {
                        ua.setUserAliasFullName(row[i + 2].toString());
                        user.getUserAliases().add(ua);
                    }
                    if (row[i + 3] != null) {
                        ua.setUserAliasTypeName(row[i + 3].toString());
                    }
                    if (row[i + 4] != null) {
                        GroupID groupId = new GroupID();
                        groupId.setGroupName(row[i + 4].toString());
                        user.setGroupID(groupId);
                    }
                    if (row[i + 5] != null) {
                        user.setFullName(row[i + 5].toString());
                    }
                    if (row[i + 6] != null) {
                        blackListjaxb.setDescription(row[i + 6].toString());
                    }
                    if (row[i + 7] != null) {
                        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, dateFormat.parse(row[i + 7].toString())));
                        user.setCreatedDateTCMV3(dateFormat.format(gc.getTime()));
                    }
                    i += 7;
                }
                user.getBlackList().add(blackListjaxb);
                users.getUser().add(user);
            }
        }
        tingcoUsers.setUsers(users);
        return tingcoUsers;
    }

    public TingcoUsers buildGetAssignedUsers(TingcoUsers userXml, List<Users2> usersList) {
        Users users = new Users();
        int seq = 1;
        for (Users2 users2 : usersList) {
            User user = new User();
            user.setSeqNo(seq++);
            user.setUserID(users2.getUserId());
            user.setFirstName(users2.getFirstName());
            user.setLastName(users2.getLastName());
            user.setFullName(users2.getFirstName() + " " + users2.getLastName());
            users.getUser().add(user);
        }
        userXml.setUsers(users);
        return userXml;
    }

    public TingcoUsers buildGetUserDetails(TingcoUsers tingcoUsers, List<Users2> users2List, List<GroupTranslations> groupTranslationsList, String timeZoneGMToffset) throws DatatypeConfigurationException {
        Users users = new Users();
        int seqNo = 1;
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Users2 users2 : users2List) {
            User user = new User();
            user.setSeqNo(seqNo++);
            user.setUserID(users2.getUserId());
            user.setFirstName(users2.getFirstName());
            user.setLastName(users2.getLastName());
            user.setUserEmail(users2.getUserEmail());
            user.setIsLockedOut(users2.getIsLockedOut());
            if (users2.getIsLockedOut() == 1) {
                user.setIsLockedOutTCMV3("No");
            } else {
                user.setIsLockedOutTCMV3("Yes");
            }

            GroupID groupID = new GroupID();
//            if (!groupTranslationsList.isEmpty()) {
//                if (groupTranslationsList.size() == 1) {
//                    groupID.setGroupName(groupTranslationsList.get(0).getGroupName());
//                    groupID.setValue(groupTranslationsList.get(0).getId().getGroupId());
//                    user.setGroupID(groupID);
//                } else {
//                    for (int i = 0; i < groupTranslationsList.size(); i++) {
//                        groupID.setGroupName(groupTranslationsList.get(i).getGroupName());
//                        groupID.setValue(groupTranslationsList.get(i).getId().getGroupId());
//                        user.setGroupID(groupID);
//                    }
//                }
//
//            } else {
//                groupID.setGroupName(users2.getGroupId());
//                groupID.setValue(users2.getGroupId());
//                user.setGroupID(groupID);
//            }

            if (!groupTranslationsList.isEmpty()) {
                for (GroupTranslations gt : groupTranslationsList) {
                    if (gt.getId().getGroupId().equalsIgnoreCase(users2.getGroupId())) {
                        groupID.setGroupName(gt.getGroupName());
                        groupID.setValue(gt.getId().getGroupId());
                        user.setGroupID(groupID);
                    }
                }
            } else {
                groupID.setValue(users2.getGroupId());
                user.setGroupID(groupID);
            }

            GregorianCalendar gc = new GregorianCalendar();
            if (users2.getLastLoginDate() != null) {
                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, users2.getLastLoginDate()));
                user.setLastLoginDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                user.setLastLoginDateTCMV3(df1.format(gc.getTime()));
            }
            gc.setTime(users2.getActiveFromDate());
            user.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            gc.setTime(users2.getActiveToDate());
            user.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            users.getUser().add(user);
        }
        tingcoUsers.setUsers(users);
        return tingcoUsers;
    }

    public TingcoUsers buildGetUserPermissions(TingcoUsers tingcoUsers, Hashtable<String, List<PermissionOperations>> ht, List<PermissionTranslations> permissionTranslationsList) {
        Users users = new Users();
        int seq = 1;
        int perSeq = 1;
        User user = new User();
        user.setSeqNo(seq++);
        Permissions permissions = new Permissions();

        for (String permissionName : ht.keySet()) {
            Permission permission = new Permission();
            permission.setSeqNo(perSeq++);
            permission.setPermissionName(permissionName);
            for (PermissionTranslations permissionTranslations : permissionTranslationsList) {
                if (permissionName.equals(permissionTranslations.getPermissionName())) {
                    permission.setPermissionID(permissionTranslations.getId().getPermissionId());
                }
            }
//            permission.setPermissionID(permissionidSet.);
            Operations operations = new Operations();
            for (se.info24.pojo.PermissionOperations operation : ht.get(permissionName)) {
                Operation op = new Operation();
                op.setOperationName(operation.getOperations().getOperationName());
                op.setOperationID(operation.getOperations().getOperationId());
                operations.getOperation().add(op);

            }
            permission.setOperations(operations);
            permissions.getPermission().add(permission);
        }

        user.setPermissions(permissions);
        users.getUser().add(user);
        tingcoUsers.setUsers(users);
        return tingcoUsers;

//        for (PermissionTranslations pt : permissionTransList) {
//            Permission permission = new Permission();
//            permission.setSeqNo(perSeq++);
////            permission.setPermissionID(pt.getId().getPermissionId());
//            permission.setPermissionName(pt.getPermissionName());
//            for (String operationsName : operationsList) {
//                Operation operation = new Operation();
//                operation.setOperationName(operationsName);
//                operations.getOperation().add(operation);
//                permission.setOperations(operations);
//            }
//            permissions.getPermission().add(permission);
//        }
//        user.setPermissions(permissions);
//        users.getUser().add(user);
//        tingcoUsers.setUsers(users);
//        return tingcoUsers;
    }

    public TingcoUsers buildGetUsersList(TingcoUsers tingcoUsers, int countryId, List<Users2> users2List, List<String> groupidset, Session session) throws DatatypeConfigurationException {
        Users users = new Users();
        int seqNo = 1;
        for (Users2 users2 : users2List) {
            User user = new User();
            user.setSeqNo(seqNo++);
            String fullName = null;
            fullName = users2.getFirstName() + " " + users2.getLastName();
            user.setUserID(users2.getUserId());
            user.setFirstName(users2.getFirstName());
            user.setLastName(users2.getLastName());
            GroupTranslations groupTrans = groupDAO.getGroupTranslationsByIds(users2.getGroupId(), countryId, session);
            if (groupTrans != null) {
                GroupID groupID = new GroupID();
                groupID.setGroupName(groupTrans.getGroupName());
                groupID.setValue(groupTrans.getId().getGroupId());
                user.setGroupID(groupID);
                fullName = fullName + "(" + groupTrans.getGroupName() + ")";
            }
            user.setFullName(fullName);
            GregorianCalendar gc = new GregorianCalendar();
            if (users2.getLastLoginDate() != null) {
                gc.setTime(users2.getLastLoginDate());
                user.setLastLoginDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            }
            gc.setTime(users2.getActiveFromDate());
            user.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            gc.setTime(users2.getActiveToDate());
            user.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            users.getUser().add(user);
        }
        tingcoUsers.setUsers(users);
        return tingcoUsers;
    }

    public TingcoUsers buildGetUsersList(TingcoUsers tingcoUsers, int countryId, List<Users2> users2List, Session session) throws DatatypeConfigurationException {
        Users users = new Users();
        int seqNo = 1;
        for (Users2 users2 : users2List) {
            User user = new User();
            user.setSeqNo(seqNo++);
            String fullName = null;
            fullName = users2.getFirstName() + " " + users2.getLastName();
            user.setUserID(users2.getUserId());
            user.setFirstName(users2.getFirstName());
            user.setLastName(users2.getLastName());
            GroupTranslations groupTrans = groupDAO.getGroupTranslationsByIds(users2.getGroupId(), countryId, session);
            if (groupTrans != null) {
                GroupID groupID = new GroupID();
                groupID.setGroupName(groupTrans.getGroupName());
                groupID.setValue(groupTrans.getId().getGroupId());
                user.setGroupID(groupID);
                fullName = fullName + "(" + groupTrans.getGroupName() + ")";
            }
            user.setFullName(fullName);
            GregorianCalendar gc = new GregorianCalendar();
            if (users2.getLastLoginDate() != null) {
                gc.setTime(users2.getLastLoginDate());
                user.setLastLoginDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            }
            gc.setTime(users2.getActiveFromDate());
            user.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            gc.setTime(users2.getActiveToDate());
            user.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            users.getUser().add(user);
        }
        tingcoUsers.setUsers(users);
        return tingcoUsers;
    }

    public TingcoUsers buildUserAlias(TingcoUsers tingcoUsers, List<UserAlias> userAliasList, String timeZoneGMToffset) throws DatatypeConfigurationException {
        Users users = new Users();
        User user = new User();
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        for (UserAlias ua : userAliasList) {
            se.info24.usersjaxb.UserAliases userAliases = new se.info24.usersjaxb.UserAliases();
            userAliases.setUserAlias(ua.getUserAlias());
            if (ua.getFirstName() != null) {
                userAliases.setFirstName(ua.getFirstName());
            }

            if (ua.getLastName() != null) {
                userAliases.setLastName(ua.getLastName());
            }

            if (ua.getCredits() != null) {
                userAliases.setCredits(new BigDecimal(ua.getCredits()));
            }

            userAliases.setIsEnabled(String.valueOf(ua.getIsEnabled()));
            GregorianCalendar gc = new GregorianCalendar();
            if (ua.getActiveFromDate() != null) {
//                gc.setTime(ua.getActiveFromDate());
                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, ua.getActiveFromDate()));
                userAliases.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc) + "");
                userAliases.setActiveFromDateTCMV3(df1.format(gc.getTime()));
            }

            if (ua.getActiveToDate() != null) {
//                gc.setTime(ua.getActiveToDate());
                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, ua.getActiveToDate()));
                userAliases.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc) + "");
                userAliases.setActiveToDateTCMV3(df1.format(gc.getTime()));
            }

            user.getUserAliases().add(userAliases);
        }

        users.getUser().add(user);
        tingcoUsers.setUsers(users);
        return tingcoUsers;
    }

    public TingcoUsers buildUserAliasBySearchCriteria(TingcoUsers tingcoUsers, List<UserAlias> userAliasList, String timeZoneGMToffset, Session session) throws DatatypeConfigurationException {
        Users users = new Users();
        User user = new User();
        for (UserAlias ua : userAliasList) {
            UserAliases userAlias = new UserAliases();
            userAlias.setUserAliasID(ua.getUserAliasId());
            userAlias.setUserAlias(ua.getUserAlias());
            userAlias.setFirstName(ua.getFirstName() != null ? ua.getFirstName() : "" + " " + ua.getLastName() != null ? ua.getLastName() : ""); //both firstname and lastname are combined
            if (ua.getActiveToDate() != null) {
                GregorianCalendar gc = new GregorianCalendar();
                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, ua.getActiveToDate()));
                userAlias.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc) + "");
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

            if (ua.getLastUpdatedByUserId() != null) {
                userAlias.setLastUpdatedByUserID(ua.getLastUpdatedByUserId());
            }

            if (ua.getLastUpdatedByUserId() != null) {
                Users2 users2 = userDAO.getUserById(ua.getLastUpdatedByUserId(), session);
                userAlias.setLastName(users2.getFirstName() + " " + users2.getLastName());
            }
            user.getUserAliases().add(userAlias);
        }
        users.getUser().add(user);
        tingcoUsers.setUsers(users);
        return tingcoUsers;
    }

    public TingcoUsers buildUserAliasDetailsByUserAliasId(TingcoUsers tingcoUsers, UserAlias ua) {
        Set<OgmuserAlias> ogmUseraliasSet = new HashSet<OgmuserAlias>();
        Set<UserAliasDetails> uadetails = new HashSet<UserAliasDetails>();
        Users users = new Users();
        User user = new User();
        UserAliases userAlias = new UserAliases();
        userAlias.setUserAliasTypeID(ua.getUserAliasTypes().getUserAliasTypeId());
        userAlias.setCreditAmount(new BigDecimal(ua.getCreditAmount()));
        userAlias.setCredits(new BigDecimal(ua.getCredits()));
        userAlias.setCreditAmountCurrencyID(new BigDecimal(ua.getCreditAmountCurrencyId()));

        OGMUserAlias ogmUserAlias = new OGMUserAlias();
        ogmUseraliasSet = ua.getOgmuserAliases();
        for (OgmuserAlias ogmua : ogmUseraliasSet) {
            ogmUserAlias.getObjectGroupID().add(ogmua.getId().getObjectGroupId());
        }
        userAlias.getOGMUserAlias().add(ogmUserAlias);

        se.info24.usersjaxb.UserAliasDetails userAliasDetails = new se.info24.usersjaxb.UserAliasDetails();
        uadetails = ua.getUserAliasDetailses();
        for (UserAliasDetails uad : uadetails) {
            userAliasDetails.setIsCreditCard(String.valueOf(uad.getIsCreditCard()));
        }
        userAlias.getUserAliasDetails().add(userAliasDetails);

        user.getUserAliases().add(userAlias);
        users.getUser().add(user);
        tingcoUsers.setUsers(users);
        return tingcoUsers;
    }

    public TingcoUsers buildUserAliasDetailsByUserAliasId(TingcoUsers tingcoUsers, UserAlias userAlias, GroupTranslations groupTranslations, Users2 users2, ObjectTags objectTags,
            List<Blacklist> blackList, String timeZoneGMToffset, List<OgmuserAlias> oUAList, int countryId, Session session) {
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd");
        se.info24.usersjaxb.User userJaxb = new User();
        se.info24.usersjaxb.Users usersJaxb = new Users();
        se.info24.usersjaxb.UserAliases userAliasesJaxb = new UserAliases();
        userAliasesJaxb.setUserAlias(userAlias.getUserAlias());//Setter for "UserAlias"
        if (userAlias.getUserAliasTypes() != null) {
            userAliasesJaxb.setUserAliasTypeID(userAlias.getUserAliasTypes().getUserAliasTypeId());//Setter for "UserAliasTypeID"
            userAliasesJaxb.setUserAliasTypeName(userAlias.getUserAliasTypes().getUserAliasTypeName());//Setter for "UserAliasTypeName"
        }
        if (userAlias.getActiveFromDate() != null) {
            userAliasesJaxb.setActiveFromDate(lFormat.format(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, userAlias.getActiveFromDate()))); //Setter for "ActiveFromDate"
        }
        if (userAlias.getActiveToDate() != null) {
            userAliasesJaxb.setActiveToDate(lFormat.format(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, userAlias.getActiveToDate())));// Setter for "ActiveToDate"
        }
        if (userAlias.getFirstName() != null) {
            userAliasesJaxb.setFirstName(userAlias.getFirstName());//Setter for "FirstName"
        }
        if (userAlias.getLastName() != null) {
            userAliasesJaxb.setLastName(userAlias.getLastName());//Setter for "LastName"
        }
        if (userAlias.getIsEnabled() != null) {
            userAliasesJaxb.setIsEnabled(String.valueOf(userAlias.getIsEnabled()));//Setter for "IsEnabled"
        }
        if (userAlias.getCredits() != null) {
            userAliasesJaxb.setCredits(BigDecimal.valueOf(userAlias.getCredits()));//Setter for "Credits"
        }
        if (userAlias.getCreditAmount() != null) {
            userAliasesJaxb.setCreditAmount(BigDecimal.valueOf(userAlias.getCreditAmount()));//Setter for "CreditAmount"
        }
        if (userAlias.getCreditAmountCurrencyId() != null) {
            userAliasesJaxb.setCreditAmountCurrencyID(BigDecimal.valueOf(userAlias.getCreditAmountCurrencyId()));//Setter for "CreditAmountCurrencyID"
            Currency currency = userDAO.getCurrencyById(session, userAlias.getCreditAmountCurrencyId());
            userAliasesJaxb.setCurrencyISOCharCode(currency.getCurrencyIsocharCode());
        }
        if (groupTranslations != null) {
            userAliasesJaxb.setGroupID(userAlias.getGroupId());//Setter for "GroupID"
            userAliasesJaxb.setGroupName(groupTranslations.getGroupName());//Setter for "GroupName"
        }
        if (users2 != null) {
            userAliasesJaxb.setUserID(users2.getUserId());//Setter for "UserID"
            userAliasesJaxb.setUserFullName(users2.getFirstName() + " " + users2.getLastName());//Setter for "UserName"
        }

        /*UserAliasDetails Jaxb Object Reference*/

        Set<UserAliasDetails> userAliasDetailsesPojo = userAlias.getUserAliasDetailses();
        if (!userAliasDetailsesPojo.isEmpty()) {
            for (UserAliasDetails usdls : userAliasDetailsesPojo) {
                se.info24.usersjaxb.UserAliasDetails userAliasDetailsJaxb = new se.info24.usersjaxb.UserAliasDetails();
                if (usdls.getIsCreditCard() != null) {
                    userAliasDetailsJaxb.setIsCreditCard(String.valueOf(usdls.getIsCreditCard()));//Setter for "IsCreditCard"
                }
                if (usdls.getIsServiceKey() != null) {
                    userAliasDetailsJaxb.setIsServiceKey(String.valueOf(usdls.getIsServiceKey()));//Setter for "IsServiceKey"
                }
                if (usdls.getCreditLimitPerPurchase() != null) {
                    userAliasDetailsJaxb.setCreditLimitPerPurchase(String.valueOf(usdls.getCreditLimitPerPurchase()));//Setter for "CreditLimitPerPurchase"
                }
                userAliasDetailsJaxb.setBlockedReason(usdls.getBlockedReason());//Setter for "BlockedReason"
                userAliasesJaxb.getUserAliasDetails().add(userAliasDetailsJaxb);//Binding to Parent
            }
        }
        if (objectTags != null) {
            se.info24.usersjaxb.ObjectTags objectTagsJaxb = new se.info24.usersjaxb.ObjectTags();
            objectTagsJaxb.setSearchTags(objectTags.getSearchTags());//Setter for "SearchTags"
            userAliasesJaxb.setObjectTags(objectTagsJaxb);//Binding
        }
        //BlackList
        se.info24.usersjaxb.BlackList blackListJaxb = new BlackList();
        if (blackList.isEmpty()) {
            blackListJaxb.setIsBlocked("0");//Setter for "IsBlocked 0"
        } else {
            blackListJaxb.setIsBlocked("1");//Setter for "IsBlocked 1"
            blackListJaxb.setBlackListID(blackList.get(0).getBlacklistId());//Setter for "BlackListID"
        }
        userAliasesJaxb.setBlackList(blackListJaxb);
        if (!oUAList.isEmpty()) {
            Set<String> ObjectGroupIDSet = userDAO.getObjectGroupIdsSet(session, oUAList);
            List<ObjectGroupTranslations> oGTList = userDAO.getObjectGroupTranslationsById(session, ObjectGroupIDSet, countryId);
            if (!oGTList.isEmpty()) {
                for (ObjectGroupTranslations ogt : oGTList) {
                    se.info24.usersjaxb.ObjectGroupTranslations objectGroupTranslationsJaxb = new se.info24.usersjaxb.ObjectGroupTranslations();
                    objectGroupTranslationsJaxb.setObjectGroupID(ogt.getId().getObjectGroupId());//Setter for "ObjectGroupID"
                    objectGroupTranslationsJaxb.setObjectGroupName(ogt.getObjectGroupName());//Setter for "ObjectGroupName"
                    userAliasesJaxb.getObjectGroupTranslations().add(objectGroupTranslationsJaxb);//Binding to Parent
                }
            }
        }

        userJaxb.getUserAliases().add(userAliasesJaxb);
        usersJaxb.getUser().add(userJaxb);
        tingcoUsers.setUsers(usersJaxb);
        return tingcoUsers;
    }

    public TingcoUsers buildUserTemplate() throws DatatypeConfigurationException {
        ObjectFactory of = new ObjectFactory();
        TingcoUsers tcm_user = of.createTingcoUsers();
        tcm_user.setCreateRef("Info24");
        tcm_user.setMsgVer(new BigDecimal(1.0));
        tcm_user.setRegionalUnits("Metric");
        tcm_user.setTimeZone("UTC");
        tcm_user.setMsgID(UUID.randomUUID().toString());
        tcm_user.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));
        MsgStatus status = new MsgStatus();
        status.setResponseCode(0);
        status.setResponseText("OK");
        tcm_user.setMsgStatus(status);
        return tcm_user;
    }

    public TingcoUsers buildALLAppRoles(TingcoUsers tingcoUsers, List<UserRoles2> userRoles2List) {
        Users users = new Users();
        int seq = 1;
        int roleseq = 1;
        User u = new User();
        u.setSeqNo(seq++);
        UserRoles user_roles = new UserRoles();
        for (UserRoles2 urg : userRoles2List) {
            UserRole ur = new UserRole();
            ur.setSeqNo(roleseq++);
            ur.setUserRoleID(urg.getUserRoleId());
            ur.setUserRoleParentID(urg.getUserRoleparentID() != null ? urg.getUserRoleparentID() : "");
            ur.setUserRoleName(urg.getUserRoleName());
            ur.setUserRoleDesc(urg.getUserRoleDescription());
            ur.setSystemRole(urg.getIsSystemRole() + "");
            user_roles.getUserRole().add(ur);
        }
        users.getUser().add(u);
        u.setUserRoles(user_roles);
        tingcoUsers.setUsers(users);
        return tingcoUsers;
    }

    public TingcoUsers buildPermissions(TingcoUsers tingcoUsers, List<PermissionTranslations> ptList) {
        Users users = new Users();
        int seq = 1;
        int perSeq = 1;
        User user = new User();
        user.setSeqNo(seq++);
        Permissions permissions = new Permissions();
        for (PermissionTranslations pt : ptList) {
            Permission permission = new Permission();
            permission.setSeqNo(perSeq++);
            permission.setPermissionID(pt.getId().getPermissionId());
            permission.setPermissionName(pt.getPermissionName());
            permission.setPermissionDesc(pt.getPermissionDescription());
            permissions.getPermission().add(permission);
        }
        users.getUser().add(user);
        user.setPermissions(permissions);
        tingcoUsers.setUsers(users);
        return tingcoUsers;
    }

    public TingcoUsers buildUserRolesObjectPermissions(TingcoUsers tingcoUsers, List<UserRoleObjectPermissions2> uropList, int countryId) {
        Users users = new Users();
        int seq = 1;
        int perSeq = 1;
        User user = new User();
        user.setSeqNo(1);
        Permissions permissions = new Permissions();
        UserRoles userroles = new UserRoles();
        user.setUserRoles(userroles);
        Set<PermissionTranslations> ptList = null;
        for (UserRoleObjectPermissions2 urop : uropList) {
            ptList = urop.getPermissions().getPermissionTranslationses();
            for (PermissionTranslations pt : ptList) {
                if (pt.getId().getCountryId() == countryId) {
                    Permission permission = new Permission();
                    permission.setSeqNo(perSeq++);
                    permission.setPermissionID(pt.getId().getPermissionId());
                    permission.setPermissionName(pt.getPermissionName());
                    permission.setPermissionDesc(pt.getPermissionDescription());
                    permissions.getPermission().add(permission);
                }
            }
            UserRoles2 roles = urop.getUserRoles2();
            UserRole ur = new UserRole();
            ur.setSeqNo(seq++);
            ur.setUserRoleID(roles.getUserRoleId());
            ur.setUserRoleDesc(roles.getUserRoleDescription());
            ur.setUserRoleName(roles.getUserRoleName());
            user.getUserRoles().getUserRole().add(ur);
        }
        user.setPermissions(permissions);
        users.getUser().add(user);
        tingcoUsers.setUsers(users);
        return tingcoUsers;
    }

    public TingcoUsers buildOrganizationPermissionsRoles(TingcoUsers tingcoUsers, List<UserRoleObjectPermissions2> uropList, int countryId) {
        Users users = new Users();
        int seq = 1;
        Set<PermissionTranslations> ptList = null;
        for (UserRoleObjectPermissions2 urop : uropList) {
            User user = new User();
            user.setSeqNo(seq++);
            int perSeq = 1;
            Permissions permissions = new Permissions();
            ptList = urop.getPermissions().getPermissionTranslationses();
            for (PermissionTranslations pt : ptList) {
                if (pt.getId().getCountryId() == countryId) {
                    Permission permission = new Permission();
                    permission.setSeqNo(perSeq++);
                    permission.setPermissionID(pt.getId().getPermissionId());
                    permission.setPermissionName(pt.getPermissionName());
                    permission.setPermissionDesc(pt.getPermissionDescription());
                    permissions.getPermission().add(permission);
                }
            }
            UserRoles userRoles = new UserRoles();
            UserRole userRole = new UserRole();
            userRole.setSeqNo(1);
            userRole.setUserRoleID(urop.getUserRoles2().getUserRoleId());
            userRole.setUserRoleName(urop.getUserRoles2().getUserRoleName());
            if (urop.getUserRoles2().getUserRoleDescription() != null) {
                userRole.setUserRoleDesc(urop.getUserRoles2().getUserRoleDescription());
            }
            userRoles.getUserRole().add(userRole);
            // To Get One Role in one user sequence.
//        HashMap<String, List<UserRoleObjectPermissions2>> permissionMap = new HashMap<String, List<UserRoleObjectPermissions2>>();
//        String userRoleId = null;
//        for (UserRoleObjectPermissions2 urop : uropList) {
//            userRoleId = urop.getUserRoles2().getUserRoleId();
//            if (permissionMap.containsKey(userRoleId)) {
//                permissionMap.get(userRoleId).add(urop);
//            } else {
//                ArrayList<UserRoleObjectPermissions2> al = new ArrayList<UserRoleObjectPermissions2>();
//                al.add(urop);
//                permissionMap.put(userRoleId, al);
//            }
//        }
//        for (String uRoleId : permissionMap.keySet()) {
//            User user = new User();
//            user.setSeqNo(seq++);
//            int perSeq = 1;
//
//            Permissions permissions = new Permissions();
//            UserRoles userRoles = new UserRoles();
//
//            for (UserRoleObjectPermissions2 urop : permissionMap.get(uRoleId)) {
//                if (perSeq == 1) {
//                    UserRole userRole = new UserRole();
//                    userRole.setSeqNo(1);
//                    userRole.setUserRoleID(urop.getUserRoles2().getUserRoleId());
//                    userRole.setUserRoleName(urop.getUserRoles2().getUserRoleName());
//                    if (urop.getUserRoles2().getUserRoleDescription() != null) {
//                        userRole.setUserRoleDesc(urop.getUserRoles2().getUserRoleDescription());
//                    }
//                    userRoles.getUserRole().add(userRole);
//                }
//
//                ptList = urop.getPermissions().getPermissionTranslationses();
//                for (PermissionTranslations pt : ptList) {
//                    if (pt.getId().getCountryId() == countryId) {
//                        Permission permission = new Permission();
//                        permission.setSeqNo(perSeq++);
//                        permission.setPermissionID(pt.getId().getPermissionId());
//                        permission.setPermissionName(pt.getPermissionName());
//                        permission.setPermissionDesc(pt.getPermissionDescription());
//                        permissions.getPermission().add(permission);
//                    }
//                }
//            }

            user.setUserRoles(userRoles);
            user.setPermissions(permissions);
            users.getUser().add(user);
        }

        tingcoUsers.setUsers(users);
        return tingcoUsers;
    }

    public TingcoUsers buildGroupTrustRolesPermissions(TingcoUsers tingcoUsers, List<GroupTrusts> groupTrustList, List<Groups> groups, int countryId, Session session) {
        PermissionDAO permissionDAO = new PermissionDAO();
        List<UserRoleObjectPermissions2> uropList = new ArrayList<UserRoleObjectPermissions2>();
        for (GroupTrusts gt : groupTrustList) {
            // uropList.addAll(permissionDAO.getUserRoleObjectPermissions(gt.getId().getUserRoleId(), gt.getId().getGroupId(), session));
            uropList.addAll(permissionDAO.getUserRoleObjectPermissions(gt.getId().getUserRoleId(), gt.getId().getItrustGroupId(), session)); // Passing the Trusted GroupID to get Permission and Roles list.
        }
        Users users = new Users();
        int seq = 1;
        Set<PermissionTranslations> ptList = null;
        for (UserRoleObjectPermissions2 urop : uropList) {
            User user = new User();
            user.setSeqNo(seq++);
            int perSeq = 1;
            for (Groups g : groups) {
                GroupID gid = new GroupID();
                if (g.getGroupId().equalsIgnoreCase(urop.getId().getObjectId())) {
                    gid.setValue(g.getGroupId());
                    Set<GroupTranslations> trans = g.getGroupTranslationses();
                    for (GroupTranslations grouptrans : trans) {
                        if (grouptrans.getCountry().getCountryId() == countryId) {
                            gid.setGroupName(grouptrans.getGroupName());
                            user.setGroupID(gid);
                            break;
                        }
                    }
                    break;
                }
            }
            Permissions permissions = new Permissions();
            ptList = urop.getPermissions().getPermissionTranslationses();
            for (PermissionTranslations pt : ptList) {
                if (pt.getId().getCountryId() == countryId) {
                    Permission permission = new Permission();
                    permission.setSeqNo(perSeq++);
                    permission.setPermissionID(pt.getId().getPermissionId());
                    permission.setPermissionName(pt.getPermissionName());
                    permission.setPermissionDesc(pt.getPermissionDescription());
                    permissions.getPermission().add(permission);
                }
            }
            UserRoles userRoles = new UserRoles();
            UserRole userRole = new UserRole();
            userRole.setSeqNo(1);
            userRole.setUserRoleID(urop.getUserRoles2().getUserRoleId());
            userRole.setUserRoleName(urop.getUserRoles2().getUserRoleName());
            if (urop.getUserRoles2().getUserRoleDescription() != null) {
                userRole.setUserRoleDesc(urop.getUserRoles2().getUserRoleDescription());
            }
            userRoles.getUserRole().add(userRole);
            user.setUserRoles(userRoles);
            user.setPermissions(permissions);
            users.getUser().add(user);
        }
        tingcoUsers.setUsers(users);
        return tingcoUsers;
    }

    public void buildLoggedinUserPermissions(TingcoUsers tingcoUsers, int countryID, List<UserRoleObjectPermissions2> uropList) {
        buildOrganizationPermissionsRoles(tingcoUsers, uropList, countryID);
    }

    public TingcoUsers buildUserTypes(TingcoUsers userXml, List<UserTypeTranslations2> userTypeList) {
        Users users = new Users();
        int seq = 1;
        for (UserTypeTranslations2 utt : userTypeList) {
            UserTypes userTypes = new UserTypes();
            userTypes.setSeqNo(seq++);
            UserTypeID userTypeId = new UserTypeID();
            userTypeId.setUserTypeName(utt.getUserTypeName());
            userTypeId.setValue(utt.getId().getUserTypeId());
            userTypes.setUserTypeID(userTypeId);
            users.getUserTypes().add(userTypes);
        }
        userXml.setUsers(users);
        return userXml;
    }

    public TingcoUsers buildUsersByGroupId(TingcoUsers userXml, List<Users2> usersList) {
        Users users = new Users();
        int seq = 1;
        for (Users2 users2 : usersList) {
            User user = new User();
            user.setSeqNo(seq++);
            user.setUserID(users2.getUserId());
            user.setFirstName(users2.getFirstName());
            user.setLastName(users2.getLastName());
            user.setFullName(users2.getFirstName() + " " + users2.getLastName());
            users.getUser().add(user);
        }
        userXml.setUsers(users);
        return userXml;
    }

    public TingcoUsers buildUsers2DetailsbyUserID(TingcoUsers userXml, Users2 users2, GroupTranslations groupTranslations, UserTimeZones2 utz, String timeZoneGMToffset, ObjectAddresses oa, Object addressesObject) throws DatatypeConfigurationException {
        Users users = new Users();
        int seq = 1;
        User user = new User();
        user.setSeqNo(seq++);
        user.setFirstName(users2.getFirstName());
        user.setLastName(users2.getLastName());
        user.setUserEmail(users2.getUserEmail());
        if (users2.getUserMobilePhone() != null) {
            user.setUserMobilePhone(users2.getUserMobilePhone());
        }

        if (groupTranslations.getGroups() != null) {
            GroupID gi = new GroupID();
            gi.setGroupName(groupTranslations.getGroupName());
            gi.setValue(groupTranslations.getGroups().getGroupId());
            user.setGroupID(gi);
        }

        user.setIsLockedOut(users2.getIsLockedOut());
        CountryID ci = new CountryID();
        ci.setValue(users2.getCountryId());
        user.setCountryID(ci);
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (users2.getActiveFromDate() != null) {
//            gc.setTime(users2.getActiveFromDate());
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, users2.getActiveFromDate()));
            user.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            user.setActiveFromDateTCMv3(df.format(gc.getTime()));

        }

        if (users2.getActiveToDate() != null) {
//            gc.setTime(users2.getActiveToDate());
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, users2.getActiveToDate()));
            user.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            user.setActiveToDateTCMV3(df.format(gc.getTime()));
        }

        UserTypeID uti = new UserTypeID();
        uti.setValue(users2.getUserTypes2().getUserTypeId());
        user.setUserTypeID(uti);
        if (utz.getTimeZoneId() != null) {
            se.info24.usersjaxb.UserTimeZone userTimeZone = new se.info24.usersjaxb.UserTimeZone();
            userTimeZone.setTimeZoneID(utz.getTimeZoneId());
            if (utz.getUseDaylightSaving() != null) {
                userTimeZone.setUseDayLightSaving(utz.getUseDaylightSaving().intValue());
            }

            UserTimeZones utxs = new UserTimeZones();
            utxs.getUserTimeZone().add(userTimeZone);
            user.setUserTimeZones(utxs);
        }
        if (oa != null) {
            user.setAddressID(oa.getAddressId());
        }
        users.getUser().add(user);
        if (addressesObject != null) {
            se.info24.pojo.Addresses addresspojo = (se.info24.pojo.Addresses) addressesObject;
            Addresses addresses = new Addresses();
            Address address = new Address();
            if (addresspojo.getAddressStreet() != null) {
                address.setAddressStreet(addresspojo.getAddressStreet());
            }
            if (addresspojo.getAddressStreet2() != null) {
                address.setAddressStreet2(addresspojo.getAddressStreet2());
            }
            if (addresspojo.getAddressSuite() != null) {
                address.setAddressSuite(addresspojo.getAddressSuite());
            }
            if (addresspojo.getAddressZip() != null) {
                address.setAddressZip(addresspojo.getAddressZip());
            }
            if (addresspojo.getAddressCity() != null) {
                address.setAddressCity(addresspojo.getAddressCity());
            }
            if (addresspojo.getAddressDescription() != null) {
                address.setAddressDesc(addresspojo.getAddressDescription());
            }
            if (addresspojo.getAddressRegion() != null) {
                address.setAddressRegion(addresspojo.getAddressRegion());
            }
            address.setCountryID(addresspojo.getCountry().getCountryId());
            addresses.getAddress().add(address);
            userXml.setAddresses(addresses);
        }
        userXml.setUsers(users);
        return userXml;
    }

    public TingcoUsers buildUserkeyDetail(TingcoUsers tingcoUsers, UserAlias useralias, UserAliasDetails uAD, Currency currency) throws DatatypeConfigurationException {
        GregorianCalendar gc = new GregorianCalendar();
        Users users = new Users();
        se.info24.usersjaxb.User user = new User();
        se.info24.usersjaxb.UserAliases userAlias = new UserAliases();
        userAlias.setUserAliasID(useralias.getUserAliasId());
        userAlias.setUserAlias(useralias.getUserAlias());
//        gc.setTime(useralias.getActiveFromDate());
        userAlias.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc) + "");
        gc.setTime(useralias.getActiveToDate());
        userAlias.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc) + "");
        userAlias.setCredits(useralias.getCredits() == null ? new BigDecimal(0) : new BigDecimal(useralias.getCredits()));
        if (useralias.getCreditAmount() != null) {
            userAlias.setCreditAmount(new BigDecimal(useralias.getCreditAmount()));
        }
        userAlias.setIsEnabled(useralias.getIsEnabled() == null ? "0" : String.valueOf(useralias.getIsEnabled()));

        if (uAD != null) {
            se.info24.usersjaxb.UserAliasDetails userAliasdetail = new se.info24.usersjaxb.UserAliasDetails();
            userAliasdetail.setIsCreditCard(uAD.getIsCreditCard() == null ? "0" : String.valueOf(uAD.getIsCreditCard()));
            userAliasdetail.setIsServiceKey(uAD.getIsServiceKey() == null ? "0" : String.valueOf(uAD.getIsServiceKey()));
            userAliasdetail.setCreditLimitPerPurchase(uAD.getCreditLimitPerPurchase() + "");
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

    public TingcoUsers buildGetAllUserRoles2(TingcoUsers tingcoUsers, List<UserRoles2> userRolesList) {
        Users users = new Users();
        User user = new User();
        UserRoles userRoles = new UserRoles();
        user.setSeqNo(1);
        int seqNo = 1;
        for (UserRoles2 ur2 : userRolesList) {
            if (ur2.getUserRoleName() != null) {
                UserRole role = new UserRole();
                role.setSeqNo(seqNo++);
                role.setUserRoleID(ur2.getUserRoleId());
                role.setUserRoleName(ur2.getUserRoleName());
                userRoles.getUserRole().add(role);
            }
        }
        user.setUserRoles(userRoles);
        users.getUser().add(user);
        tingcoUsers.setUsers(users);
        return tingcoUsers;
    }

    public TingcoUsers buildGetUserLogDetail(TingcoUsers tingcoUsers, List<UserLog> userLogList, String timeZoneGMToffset) throws DatatypeConfigurationException {
        Users users = new Users();
        User user = new User();
        GregorianCalendar gc = new GregorianCalendar();
        se.info24.usersjaxb.UserLog userLogJaxb = null;
        int seq = 0;
        for (UserLog userLog : userLogList) {
            userLogJaxb = new se.info24.usersjaxb.UserLog();
            userLogJaxb.setSeqNo(seq++);
            userLogJaxb.setLogUserActionID(userLog.getLogUserActionId() + "");
            userLogJaxb.setAction(userLog.getAction());
            if (userLog.getResults() != null) {
                userLogJaxb.setResults(userLog.getResults());
            }
            if (userLog.getRequestIp() != null) {
                userLogJaxb.setRequestIP(userLog.getRequestIp());
            }
            if (userLog.getActionValue1() != null) {
                userLogJaxb.setActionValue1(userLog.getActionValue1());
            }
            gc.setTimeInMillis(RestUtilDAO.convertGMTtoUserLocalTimeInLongFormat(timeZoneGMToffset, userLog.getCreatedDate()));
            userLogJaxb.setCreatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            userLogJaxb.setCreatedDateTCMV3(dateFormat.format(gc.getTime()));
            user.getUserLog().add(userLogJaxb);
        }

        users.getUser().add(user);
        tingcoUsers.setUsers(users);
        return tingcoUsers;
    }

    public TingcoUsers buildUsersListForListUsersPage(TingcoUsers tingcoUsers, List<Object> usersList, String timeZoneGMToffset) throws ParseException, DatatypeConfigurationException {
        Users users = new Users();
        GregorianCalendar gc = new GregorianCalendar();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Iterator itr = usersList.iterator(); itr.hasNext();) {
            Object[] row = (Object[]) itr.next();
            User user = new User();
            Address address = new Address();
            for (int i = 0; i < row.length; i++) {
                if (i % 2 == 0) {
                    if (row[i] != null) {
                        user.setFirstName(row[i].toString());
                    }
                    if (row[i + 1] != null) {
                        user.setLastName(row[i + 1].toString());
                    }
                    if (row[i + 2] != null) {
                        user.setUserEmail(row[i + 2].toString());
                    }
                    if (row[i + 3] != null) {
                        GroupID groupId = new GroupID();
                        groupId.setGroupName(row[i + 3].toString());
                        user.setGroupID(groupId);
                    }
                    if (row[i + 4] != null) {
                        address.setAddressStreet(row[i + 4].toString());
                    }
                    if (row[i + 5] != null) {
                        address.setAddressZip(row[i + 5].toString());
                    }
                    if (row[i + 6] != null) {
                        address.setAddressCity(row[i + 6].toString());
                    }
                    if (row[i + 7] != null) {
//                        CountryID countryId = new CountryID();
                        address.setCountryName(row[i + 7].toString());
//                        address.setCountryID(Integer.valueOf(row[i + 7].toString()));
                    }
                    if (row[i + 8] != null) {
                        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, dateFormat.parse(row[i + 8].toString())));
                        user.setCreatedDateTCMV3(dateFormat.format(gc.getTime()));
                    }
                    i += 8;
                }
                user.setAddress(address);
            }
            users.getUser().add(user);
        }

        tingcoUsers.setUsers(users);
        return tingcoUsers;
    }

    public TingcoUsers buildUsersListForSearchUsersPage(TingcoUsers tingcoUsers, List<Object> usersList, String timeZoneGMToffset) throws ParseException, DatatypeConfigurationException {
        Users users = new Users();
        GregorianCalendar gc = new GregorianCalendar();
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Iterator itr = usersList.iterator(); itr.hasNext();) {
            Object[] row = (Object[]) itr.next();
            User user = new User();
            for (int i = 0; i < row.length; i++) {
                if (i % 2 == 0) {
                    if (row[i] != null) {
                        user.setUserID(row[i].toString());
                    }
                    if (row[i + 1] != null) {
                        user.setFirstName(row[i + 1].toString());
                    }
                    if (row[i + 2] != null) {
                        user.setLastName(row[i + 2].toString());
                    }
                    if (row[i + 3] != null) {
                        user.setUserEmail(row[i + 3].toString());
                    }
                    if (row[i + 4] != null) {
                        GroupID groupId = new GroupID();
                        groupId.setGroupName(row[i + 4].toString());
                        user.setGroupID(groupId);
                    }
                    if (row[i + 5] != null) {
                        if (row[i + 5].toString().equalsIgnoreCase("1")) {
                            user.setIsLockedOutTCMV3("No");
                        } else {
                            user.setIsLockedOutTCMV3("Yes");
                        }
                    }
                    if (row[i + 6] != null) {
                        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, dateFormat1.parse(row[i + 6].toString())));
                        user.setLastLoginDateTCMV3(dateFormat1.format(gc.getTime()));
                    }
                    i += 6;
                }
                users.getUser().add(user);
            }
        }
        tingcoUsers.setUsers(users);
        return tingcoUsers;
    }

    public TingcoUsers buildGetDeviceDriversDetails(TingcoUsers tingcoUsers, Users2 users2, GroupTranslations groupTranslations) {
        Users users = new Users();
        User userjaxb = new User();
        GregorianCalendar gc = new GregorianCalendar();
        int seq = 0;
        userjaxb.setUserID(users2.getUserId());
        userjaxb.setFirstName(users2.getFirstName());
        userjaxb.setLastName(users2.getLastName());
        GroupID groupID = new GroupID();
        groupID.setGroupName(groupTranslations.getGroupName());
        groupID.setValue(groupTranslations.getId().getGroupId());
        userjaxb.setGroupID(groupID);
        users.getUser().add(userjaxb);
        tingcoUsers.setUsers(users);
        return tingcoUsers;
    }

    public TingcoUsers buildGetUserRoleFunctionAreasDetails(TingcoUsers tingcoUsers, List<UserRoleObjectPermissions2> userRoleObjectPermissions2, List<PermissionTranslations> permissionTranslationses, List<FunctionAreas> functionAreases) {
        Users users = new Users();
        User userjaxb = new User();
        se.info24.usersjaxb.FunctionAreas fa = new se.info24.usersjaxb.FunctionAreas();
        for (FunctionAreas functionAreas : functionAreases) {
            for (UserRoleObjectPermissions2 userRoleObjectPermissions : userRoleObjectPermissions2) {
                if (userRoleObjectPermissions.getId().getObjectId().equalsIgnoreCase(functionAreas.getFunctionAreaId())) {
                    Function function = new Function();
                    function.setFunctionAreaID(functionAreas.getFunctionAreaId());
                    function.setFunctionAreaTechName(functionAreas.getFunctionAreaTechName());
                    for (PermissionTranslations permissionTranslations : permissionTranslationses) {
                        if (userRoleObjectPermissions.getId().getPermissionId().equalsIgnoreCase(permissionTranslations.getId().getPermissionId())) {
                            Permissions permissions = new Permissions();
                            Permission permission = new Permission();
                            permission.setPermissionID(permissionTranslations.getId().getPermissionId());
                            permission.setPermissionName(permissionTranslations.getPermissionName());
                            permissions.getPermission().add(permission);
                            function.setPermissions(permissions);
                            break;
                        }
                    }
                    fa.getFunction().add(function);
                    break;
                }
            }
        }
        userjaxb.setFunctionAreas(fa);
        users.getUser().add(userjaxb);
        tingcoUsers.setUsers(users);
        return tingcoUsers;
    }

    public TingcoUsers buildGetAllFunctionArea(TingcoUsers tingcoUsers, List<FunctionAreas> functionAreases) {
        Users users = new Users();
        User userjaxb = new User();
        se.info24.usersjaxb.FunctionAreas fa = new se.info24.usersjaxb.FunctionAreas();
        for (FunctionAreas functionAreas : functionAreases) {
            Function function = new Function();
            function.setFunctionAreaID(functionAreas.getFunctionAreaId());
            function.setFunctionAreaTechName(functionAreas.getFunctionAreaTechName());
            fa.getFunction().add(function);
        }
        userjaxb.setFunctionAreas(fa);
        users.getUser().add(userjaxb);
        tingcoUsers.setUsers(users);
        return tingcoUsers;
    }

    public TingcoUsers buildGetSupportOrganizationUsersList(TingcoUsers tingcoUsers, List<Users2> users2s, List<SupportOrganizationUsers> supportOrganizationUserses, List<GroupTranslations> groupTranslationses) {
        Users users = new Users();
        int seq = 1;
        for (Users2 users2 : users2s) {
            User user = new User();
            user.setSeqNo(seq++);
            user.setUserID(users2.getUserId());
            user.setFirstName(users2.getFirstName());
            user.setLastName(users2.getLastName());
            for (SupportOrganizationUsers supportOrganizationUsers : supportOrganizationUserses) {
                if (supportOrganizationUsers.getId().getUserId().equalsIgnoreCase(users2.getUserId())) {
                    if (supportOrganizationUsers.getIsManager() == 1) {
                        user.setIsManager("Yes");
                    } else {
                        user.setIsManager("No");
                    }

                }
            }
            for (GroupTranslations groupTranslations : groupTranslationses) {
                if (users2.getGroupId().equalsIgnoreCase(groupTranslations.getId().getGroupId())) {
                    GroupID id = new GroupID();
                    id.setGroupName(groupTranslations.getGroupName());
                    id.setValue(groupTranslations.getId().getGroupId());
                    user.setGroupID(id);
                }
            }
            user.setFullName(users2.getFirstName() + " " + users2.getLastName());
            users.getUser().add(user);
        }
        tingcoUsers.setUsers(users);
        return tingcoUsers;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.tcp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.ismOperationsPojo.TransactionResult;
import se.info24.pojo.Addresses;
import se.info24.pojo.ApplicationProviderTypes;
import se.info24.pojo.ApplicationTypeTranslations;
import se.info24.pojo.Country;
import se.info24.pojo.GroupAlias;
import se.info24.pojo.ObjectAddresses;
import se.info24.pojo.ObjectGroups;
import se.info24.pojo.OgmuserAlias;
import se.info24.pojo.ProviderTypeTranslations;
import se.info24.pojo.ProviderTypes;
import se.info24.pojo.UserAlias;
import se.info24.pojo.UserAliasDetails;
import se.info24.pojo.UserProviderTypeReferences;
import se.info24.pojo.UserProviderTypes;
import se.info24.pojo.UserRoleMemberships2;
import se.info24.pojo.UserRoles2;
import se.info24.pojo.UserTimeZones2;
import se.info24.pojo.Users2;
import se.info24.util.TCMUtil;

/**
 *
 * @author Ravikant
 */
public class TingcoCustomerDAO {

    public List<ApplicationProviderTypes> GetApplicationProviderTypesByapplicationId(Session session, String applicationId){
        return session.getNamedQuery("GetApplicationProviderTypesByapplicationId").setString("applicationId", applicationId).list();
    }

    public List<ProviderTypes> getProviderTypesByIds(Session session, List<String> providerTypesId) {
        return session.getNamedQuery("getProviderTypesByIds").setParameterList("providerTypeId", providerTypesId).list();
    }

    public List<ProviderTypeTranslations> getProviderTypeTranslationsByCountryId(Session session, int countryId) {
        return session.getNamedQuery("getProviderTypeTranslationsByCountryId").setInteger("countryId", countryId).list();
    }

    public List<ProviderTypeTranslations> getProviderTypeTranslationsByProviderId(Session session, List<String> providerTypeId, int countryId) {
        return session.getNamedQuery("getProviderTypeTranslationsByProviderId").setInteger("countryId", countryId).setParameterList("providerTypeId", providerTypeId).list();
    }

    public UserProviderTypes getUserProviderTypesByUserID(Session session, String userId) {
        return (UserProviderTypes) session.getNamedQuery("getUserProviderTypesByUserID").setString("userId", userId).uniqueResult();
    }

    public boolean updateUserProviderTypes(Session session, UserProviderTypes uPTs) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(uPTs);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tx.rollback();
            return false;
        }
    }

    public List<TransactionResult> getTransactionResultByStartTime(Session session, String transactionStartTime, String transactionStopTime, String userId) {
        return session.getNamedQuery("getTransactionResultByStartTimeAndUserId").setString("fromDate", transactionStartTime).setString("toDate", transactionStopTime).setString("userId", userId).setMaxResults(200).list();
    }

    public List<TransactionResult> getTransactionResultByStartTimeAndUserIduserAliasId(Session session, String transactionStartTime, String transactionStopTime, String userId,String userAliasId) {
        return session.getNamedQuery("getTransactionResultByStartTimeAndUserIduserAliasId").setString("fromDate", transactionStartTime).setString("toDate", transactionStopTime).setString("userId", userId).setString("userAliasId", userAliasId).setMaxResults(200).list();
    }

    public List<ObjectAddresses> getObjectAddressesByobjectId(Session session, String objectId) {
        return session.getNamedQuery("getObjectAddressesByobjectId").setString("objectId", objectId).list();
    }

    public GroupAlias getGroupAliasByalias(Session session, String groupAlias) {
        List<GroupAlias> groupAliasList = session.getNamedQuery("getGroupAliasByalias").setString("groupAlias", groupAlias).list();
        return groupAliasList.isEmpty() ? null : groupAliasList.get(0);
    }

    public UserAlias getUserAliasByUserAlias(Session session, String userAlias) {
        List<UserAlias> userAliasList = session.getNamedQuery("getUserAliasByUserAlias").setString("userAlias", userAlias).list();
        return (userAliasList.isEmpty() ? null : userAliasList.get(0));
    }

    public boolean isValidUserAlias(UserAlias useralias) {
        if (useralias != null) {
            Date date = new Date();
            if (useralias.getIsEnabled() == 0 && useralias.getActiveFromDate().before(date) && useralias.getActiveToDate().after(date)) {
                if (!isDateAvailable(useralias.getFirstUseDate())) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        } else {
            return false;
        }

    }

    public boolean isDateAvailable(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            sdf.format(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateUserProfile(Session session, Users2 users, Addresses address, ObjectAddresses objectAddresses) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(users);

            if (address != null) {
                session.saveOrUpdate(address);

            }
            if (objectAddresses != null) {
                session.saveOrUpdate(objectAddresses);
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    public Country getCountryById(Session session, int countryId) {
        return (Country) session.getNamedQuery("getCountryById").setInteger("countryID", countryId).list().get(0);
    }

    public UserRoles2 getUserRoleByUserRoleID(Session session, String userRoleId) {
        return (UserRoles2) session.getNamedQuery("getUserRoleByUserRoleID").setString("roleID", userRoleId).list().get(0);
    }

    public ObjectGroups getObjectGroupsByGroupId(Session session, String objectGroupId) {
        return (ObjectGroups) session.getNamedQuery("getObjectGroupsByGroupIds").setString("objectGroupId", objectGroupId).uniqueResult();
    }

    public boolean addTCPNewUser(Session session, Users2 users, Addresses address, ObjectAddresses objectAddresses, UserRoleMemberships2 urm, UserProviderTypes uPT, UserAlias ua, UserAliasDetails uad, OgmuserAlias ogmuserAlias, UserTimeZones2 timezone) {

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(users);
            if (address != null) {
                session.saveOrUpdate(address);
                session.saveOrUpdate(objectAddresses);
            }
            session.saveOrUpdate(urm);
            session.saveOrUpdate(uPT);
            session.saveOrUpdate(ua);
            session.saveOrUpdate(uad);
            session.saveOrUpdate(ogmuserAlias);
            session.saveOrUpdate(timezone);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tx.rollback();
            return false;
        }
    }

    public ProviderTypes getProviderTypesById(Session session, String providerTypeId) {
        return (ProviderTypes) session.getNamedQuery("getProviderTypesById").setString("providerTypeId", providerTypeId).uniqueResult();
    }

    public UserProviderTypeReferences getUserProviderTypeReferencesByIdandUserID(Session session, String providerTypeId, String userId) {
        return (UserProviderTypeReferences) session.getNamedQuery("getUserProviderTypeReferencesByIdandUserID").setString("providerTypeId", providerTypeId).setString("userId", userId).uniqueResult();
    }

    boolean saveAddUserProviderTypeReference(Session session, UserProviderTypeReferences uptr) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(uptr);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tx.rollback();
            return false;
        }
    }

    boolean deleteUserProviderTypeReference(Session session, UserProviderTypeReferences uptr) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(uptr);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tx.rollback();
            return false;
        }
    }
}

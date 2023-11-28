/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.permission;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.pojo.FunctionAreas;
import se.info24.pojo.PermissionOperations;
import se.info24.pojo.PermissionTranslations;
import se.info24.pojo.UserRoleMemberships2;
import se.info24.pojo.UserRoleObjectPermissions2;
import se.info24.util.DbManager;
import se.info24.util.TCMUtil;

/**
 *
 * @author Sridhar | Sekhar
 */
public class PermissionDAO {

//    DbManager dbManager = new DbManager();

    public List<PermissionTranslations> getAllPermissions(int countryID, Session session) {
        List<PermissionTranslations> permissionTranslations = null;
        try {
            permissionTranslations = session.getNamedQuery("getPermissionTranslationsById").setInteger("countryID", countryID).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
        }
        return permissionTranslations;
    }

    public List<UserRoleObjectPermissions2> getUserRoleObjectPermissions(Session session) {
        return session.getNamedQuery("getAllUserRoleObjectPermissions2").list();
    }

    public List<UserRoleObjectPermissions2> getUserRoleObjectPermissions(String userRoleID, String objectID, Session session) {
        List<UserRoleObjectPermissions2> userRoleObjectPermissions2List = null;
        try {
            userRoleObjectPermissions2List = session.getNamedQuery("getUserRoleObjectPermissions2ByObjectIDandUserRoleID").setString("userroleID", userRoleID).setString("objectID", objectID).list();
        } catch (Exception he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
        }
        return userRoleObjectPermissions2List;
    }

    public List<UserRoleObjectPermissions2> getUserRoleObjectPermissions2ByObjectIDsandUserRoleIDs(List<String> userRoleID, List<String> objectID, Session session) {
        List<UserRoleObjectPermissions2> userRoleObjectPermissions2List = null;
        try {
            userRoleObjectPermissions2List = session.getNamedQuery("getUserRoleObjectPermissions2ByObjectIDsandUserRoleIDs").setParameterList("userroleID", userRoleID).setParameterList("objectID", objectID).list();
        } catch (Exception he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
        }
        return userRoleObjectPermissions2List;
    }

    public List<UserRoleObjectPermissions2> getUserRoleObjectPermissionsByGroupID(String groupId, Session session) {
        List<UserRoleObjectPermissions2> userRoleObjectPermissions2List = null;
        try {
            userRoleObjectPermissions2List = session.getNamedQuery("getUserRoleObjectPermissions2ByObjectId").setString("objectID", groupId).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
        }
        return userRoleObjectPermissions2List;
    }

    public UserRoleObjectPermissions2 getUserRoleObjectPermission(String userRoleID, String permissionID, String objectID, Session session) {
        try {
            return (UserRoleObjectPermissions2) session.getNamedQuery("getUserRoleObjectPermissions2").setString("userroleID", userRoleID).setString("permissionID", permissionID).setString("objectID", objectID).uniqueResult();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }

    }

    public boolean removeUserRoleObjectPermission(UserRoleObjectPermissions2 userRoleObjectPermissions2, Session session) {
        Transaction tx = null;
        try {
            if (userRoleObjectPermissions2 != null) {
                tx = session.beginTransaction();
                session.delete(userRoleObjectPermissions2);
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

    protected List<UserRoleObjectPermissions2> getLoggedinUserPermissions(String userID, Session session) {
        List<UserRoleObjectPermissions2> userPerms = null;
        try {
            userPerms = session.getNamedQuery("getUserRoleObjectPermissions2ByObjectId").setString("objectID", userID).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
        }
        return userPerms;
    }

    public List<UserRoleObjectPermissions2> getpermissionid(String userID, String functionAreaID, Session session) {
        DbManager dbManager = new DbManager();
        List<UserRoleMemberships2> userRoleMembershipsList = dbManager.getUserRoleIdByUserId(session, userID);
        List<UserRoleObjectPermissions2> userRoleObjectPermissionsList = dbManager.getUserRoleObjectPermissions2(session, functionAreaID, userRoleMembershipsList);
        return userRoleObjectPermissionsList;
    }

    public PermissionTranslations getPermissionTranslations(Session session, String permissionid, int countryId) {
        return (PermissionTranslations) session.getNamedQuery("getPermissionTranslations").setParameter("permissionId", permissionid).setInteger("countryID", countryId).uniqueResult();
    }

    public FunctionAreas getFunctionAreas(Session session, String functionAreas) {
        return (FunctionAreas) session.getNamedQuery("getFunctionAreasByFunctionAreaTechName").setString("functionAreaTechName", functionAreas.toUpperCase()).uniqueResult();
    }

    public List<PermissionOperations> getPermissionOperations(Session session, String permissionid) {
        List<PermissionOperations> permissionlist = session.getNamedQuery("getPermissionOperations").setString("permissionID", permissionid).list();
        return permissionlist;
    }
    public List<FunctionAreas> getAllFunctionAreas(Session session){
        return session.getNamedQuery("getAllFunctionAreas").list();
    }
}

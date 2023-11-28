/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.roles;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.pojo.UserRoles2;
import se.info24.pojo.UserRolesInGroups2;
import se.info24.util.TCMUtil;

/**
 *
 * @author Sekhar
 */
public class RoleDAO {

    /**
     *  Utility Method to save a Record in UserRolesInGroups2 Table.
     *  Role and Group (Organization) Mapping method.
     *  @param userRolesInGroups2
     *  @return
     */
    public boolean saveRoleOrganizationMapping(UserRolesInGroups2 userRolesInGroups2, Session session) {
        Transaction tx = null;
        try {
            if (userRolesInGroups2 != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(userRolesInGroups2);
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

    public List<UserRoles2> getAllUserRoles2(Session session) {
        List<UserRoles2> userRoles2List = null;
        try {
            userRoles2List = session.getNamedQuery("getAllUserRoles2").list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
        }
        return userRoles2List;
    }

    public boolean isOrganizationRoleExists(String userRoleID, String groupID, Session session) {
        UserRolesInGroups2 userRolesInGroups2 = null;
        try {
            userRolesInGroups2 = (UserRolesInGroups2) session.getNamedQuery("getUserRolesInGroups2ByRoleAndGroupID").setString("userRoleID",userRoleID).setString("groupID",groupID).list().get(0);

            if(userRolesInGroups2 != null)
                return true;
            else
                return false;
        }  catch (Exception he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return false;
        }
    }

   public void deleteUserRoles2(Session session, String userRoleId) {
        Transaction tx = null;
        try {
            UserRoles2 userRoles = (UserRoles2) session.getNamedQuery("getUserRoleByUserRoleID").setString("roleID", userRoleId).list().get(0);
            if (userRoles != null) {
                tx = session.beginTransaction();
                session.delete(userRoles);
                tx.commit();
            }
        } catch (Exception he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public boolean updateUserRoles2(Session session, String userRoleId, String userRoleParentId, String userRoleName, String userRoleDesc, String systemRole,String userId) {
        Transaction tx = null;
        try {
            UserRoles2 userRoles = (UserRoles2) session.getNamedQuery("getUserRoleByUserRoleID").setString("roleID", userRoleId).list().get(0);
            tx = session.beginTransaction();
            userRoles.setUserRoleName(userRoleName);
            if(userRoleDesc != null){
                    userRoles.setUserRoleDescription(userRoleDesc);
            }
            
            userRoles.setLastUpdatedByUserId(userRoleId);


            if(userRoleParentId != null){
                userRoles.setUserRoleparentID(userRoleParentId);
            }

            userRoles.setIsSystemRole(Integer.valueOf(systemRole));
            userRoles.setLastUpdatedByUserId(userId);
            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
            userRoles.setUpdatedDate(gc.getTime());
            session.saveOrUpdate(userRoles);
            tx.commit();
            return true;
        } catch (Exception he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    public boolean addUserRoles2(Session session, UserRoles2 userRoles) {
        Transaction tx = null;
        try {
            if (userRoles != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(userRoles);
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
}

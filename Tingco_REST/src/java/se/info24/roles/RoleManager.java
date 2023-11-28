/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.roles;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.pojo.UserRoleMemberships2;
import se.info24.pojo.UserRoleMemberships2Id;
import se.info24.pojo.UserRoleMembershipsToActivate;
import se.info24.pojo.UserRoleMembershipsToActivateId;
import se.info24.pojo.UserRoleObjectPermissions2;
import se.info24.pojo.UserRoleObjectPermissions2Id;
import se.info24.pojo.UserRolesInGroups2;
import se.info24.pojo.UserRolesInGroups2Id;
import se.info24.pojo.UserSessions2;
import se.info24.usersjaxb.MsgStatus;
import se.info24.usersjaxb.ObjectFactory;
import se.info24.usersjaxb.TingcoUsers;
import se.info24.usersjaxb.User;
import se.info24.usersjaxb.UserRole;
import se.info24.usersjaxb.UserRoles;
import se.info24.usersjaxb.Users;
import se.info24.util.TCMUtil;

/**
 *
 * @author Sridhar Dasari
 */
public class RoleManager {

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

    public TingcoUsers getAllRoles(String groupID, TingcoUsers user, Session session) {
        List<UserRolesInGroups2> userRolesInGrps = session.getNamedQuery("getUserRolesInGroups2ByGroupID").setString("groupID", groupID).list();
        Users users = new Users();
        int seq = 1;
        int roleseq = 1;
        User u = new User();
        u.setSeqNo(seq++);
        UserRoles user_roles = new UserRoles();
        for (UserRolesInGroups2 urg : userRolesInGrps) {
            UserRole ur = new UserRole();
            ur.setSeqNo(roleseq++);
            ur.setUserRoleID(urg.getUserRoles2().getUserRoleId());
            ur.setUserRoleName(urg.getUserRoles2().getUserRoleName());
            ur.setUserRoleDesc(urg.getUserRoles2().getUserRoleDescription());
            user_roles.getUserRole().add(ur);
        }
        users.getUser().add(u);
        u.setUserRoles(user_roles);
        user.setUsers(users);
        return user;
    }

    public TingcoUsers getUserRoleMemberships(String userID, TingcoUsers user, Session session) {
        List<UserRoleMemberships2> mem = session.getNamedQuery("getUserGroupMemberships2ById").setString("userID", userID).list();
        Users users = new Users();
        int seq = 1;
        for (UserRoleMemberships2 urm : mem) {
            UserRoles user_roles = new UserRoles();
            User u = new User();
            u.setSeqNo(seq++);
//            UserRoleID roleID = new UserRoleID();
//            roleID.setUserRoleName(urm.getUserRoles2().getUserRoleName());
//            roleID.setValue(urm.getUserRoles2().getUserRoleId());
//            user_roles.getUserRoleID().add(roleID);
//            u.setUserRoles(user_roles);
//            users.getUser().add(u);
        }
        user.setUsers(users);
        return user;
    }

    public void insertUserRoleMemShip(String userID, String roleID, UserSessions2 user_sess, Session session) {
        Transaction tx = session.beginTransaction();
        GregorianCalendar gc = new GregorianCalendar();
        UserRoleMembershipsToActivate urm2act = new UserRoleMembershipsToActivate();
        UserRoleMembershipsToActivateId id = new UserRoleMembershipsToActivateId(userID, roleID);
        urm2act.setId(id);
        urm2act.setCreatedDate(gc.getTime());
        urm2act.setUpdatedDate(gc.getTime());
        urm2act.setLastUpdatedByUserId(user_sess.getUserId());
        session.saveOrUpdate(urm2act);
        tx.commit();
    }

    public void deleteMemberShip(String userID, String roleID, Session session) {
        Transaction tx = session.beginTransaction();
        UserRoleMemberships2 del = (UserRoleMemberships2) session.createQuery(" from UserRoleMemberships2  where UserID = '" + userID + "' and UserRoleID = '" + roleID + "'").list().get(0);
        session.delete(del);
        tx.commit();
    }

    public boolean updateOrganizationRole(String oldroleID, String newroleID, String groupID, Session session) {
        Transaction tx = session.beginTransaction();
        try {
            UserRolesInGroups2 urig = (UserRolesInGroups2) session.createQuery("from UserRolesInGroups2 where UserRoleID = '" + oldroleID + "' and GroupID = '" + groupID + "'").list().get(0);
            session.delete(urig);
            UserRolesInGroups2 newRole = new UserRolesInGroups2();
            UserRolesInGroups2Id id = new UserRolesInGroups2Id(newroleID, groupID);
            newRole.setId(id);
            GregorianCalendar gc = new GregorianCalendar();
            newRole.setCreatedDate(gc.getTime());
            newRole.setUpdatedDate(gc.getTime());
            session.saveOrUpdate(newRole);
            tx.commit();
            return true;

        } catch (Exception e) {
            tx.rollback();
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }

    }

    protected boolean addNewRolePermission(String roleID, String permID, String groupID, UserSessions2 user_sess, Session session) {
        try {
            Transaction tx = session.beginTransaction();
            UserRoleObjectPermissions2 urop = new UserRoleObjectPermissions2();
            UserRoleObjectPermissions2Id id = new UserRoleObjectPermissions2Id(roleID, permID, groupID);
            urop.setId(id);
            GregorianCalendar gc = new GregorianCalendar();
            urop.setCreatedDate(gc.getTime());
            urop.setUpdatedDate(gc.getTime());
            urop.setLastUpdatedByUserId(user_sess.getUserId());
            session.saveOrUpdate(urop);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }

    }

    public boolean deleteRolePermission(String roleID, String permID, String groupID, Session session) {
        try {
            Transaction tx = session.beginTransaction();
            UserRoleObjectPermissions2 urp = null;
            if (permID != null) {
                urp = (UserRoleObjectPermissions2) session.createQuery("from UserRoleObjectPermissions2 where UserRoleID = '" + roleID + "' and PermissionID = '" + permID + "' and ObjectID = '" + groupID + "'").list().get(0);
                session.delete(urp);
            } else {
                List<UserRoleObjectPermissions2> uropList = session.createQuery("from UserRoleObjectPermissions2 where UserRoleID = '" + roleID + "' and ObjectID = '" + groupID + "'").list();

                for (UserRoleObjectPermissions2 urop : uropList) {
                    session.delete(urop);
                }
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void addUserRoleMemShip(String userID, String roleID, UserSessions2 user_sess, Session session) {
        Transaction tx = session.beginTransaction();
        GregorianCalendar gc = new GregorianCalendar();
        UserRoleMemberships2 urm2 = new UserRoleMemberships2();
        UserRoleMemberships2Id id = new UserRoleMemberships2Id(userID, roleID);
        urm2.setId(id);
        urm2.setCreatedDate(gc.getTime());
        urm2.setUpdatedDate(gc.getTime());
        urm2.setLastUpdatedByUserId(user_sess.getUserId());

        session.saveOrUpdate(urm2);
        tx.commit();
    }

    

    void addUserRoleToUserandGroup(String loginUserID, String userID, String roleID, UserSessions2 user_sess, String groupId, Session session) {
        Transaction tx = session.beginTransaction();
        GregorianCalendar gc = new GregorianCalendar();
        UserRoleMemberships2 urm2 = new UserRoleMemberships2();
        UserRoleMemberships2Id id = new UserRoleMemberships2Id(userID, roleID);
        urm2.setId(id);
        urm2.setCreatedDate(gc.getTime());
        urm2.setUpdatedDate(gc.getTime());
        urm2.setLastUpdatedByUserId(user_sess.getUserId());
        UserRolesInGroups2 urg2 = new UserRolesInGroups2();
        UserRolesInGroups2Id ids = new UserRolesInGroups2Id();
        ids.setGroupId(groupId);
        ids.setUserRoleId(roleID);
        urg2.setId(ids);
        urg2.setLastUpdatedByUserId(loginUserID);
        urg2.setUpdatedDate(gc.getTime());
        urg2.setCreatedDate(gc.getTime());
        session.saveOrUpdate(urm2);
        session.saveOrUpdate(urg2);
        tx.commit();
    }

   
}

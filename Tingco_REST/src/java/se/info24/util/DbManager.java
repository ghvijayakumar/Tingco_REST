/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import se.info24.pojo.FunctionAreas;
import se.info24.pojo.Operations;
import se.info24.pojo.PermissionOperations;
import se.info24.pojo.PermissionTranslations;
import se.info24.pojo.UserRoleMemberships2;
import se.info24.pojo.UserRoleObjectPermissions2;

/**
 *
 * @author Hitha
 */
public class DbManager {

    public ArrayList<Operations> getOperations(Session session, List<PermissionOperations> permissionOperationsList) {
        ArrayList<String> operationidList = new ArrayList<String>();
        for(PermissionOperations po: permissionOperationsList) {
            operationidList.add(po.getId().getOperationId());
        }
        return (ArrayList<Operations>) session.getNamedQuery("getOperationsByOperationId").setParameterList("operationId", operationidList).list();
    }

    public  List<PermissionOperations> getPermissionOperations(Session session, String permissionid) {
        List<PermissionOperations> permissionlist = session.getNamedQuery("getPermissionOperations").setString("permissionID", permissionid).list();
        return permissionlist;
    }

    public List<UserRoleMemberships2> getUserRoleIdByUserId(Session session, String userID) {
        List<UserRoleMemberships2> userRoleMemberships2 = session.getNamedQuery("getUserRoleMemberships2ByUserId").setString("userID", userID).list();
        return userRoleMemberships2;
    }

    public List<UserRoleObjectPermissions2> getUserRoleObjectPermissions2ByUserRoleId(Session session, String userRoleID) {
        List<UserRoleObjectPermissions2> list = session.getNamedQuery("getUserRoleObjectPermissions2ByUserId").setString("userRoleID", userRoleID).list();
        return list;
    }

    public List<PermissionOperations> getPermissionOperationsByPermissionID(Session session, String permissionID) {
        List<PermissionOperations> list = session.getNamedQuery("getPermissionOperationsByPermissionID").setString("permissionID", permissionID).list();
        return list;
    }

    public List<FunctionAreas> getFunctionAreasByObjectID(Session session, String objectID) {
        List<FunctionAreas> list = session.getNamedQuery("getFunctionAreasByObjectID").setString("objectID", objectID).list();
        return list;
    }

    public List<FunctionAreas> getFunctionAreasByObjectIDs(Session session, List<String> objectID) {
        return  session.getNamedQuery("getFunctionAreasByObjectIDs").setParameterList("objectID", objectID).list();
    }

    public List<UserRoleObjectPermissions2> getUserRoleObjectPermissions2(Session session, String functionAreaID, List<UserRoleMemberships2> userRoleMemberships2List) {
        Set<String> userRoleIdList = getUserRoleIdsList(userRoleMemberships2List);
        return session.getNamedQuery("getUserRoleObjectPermissions").setParameterList("userRoleID", userRoleIdList).setString("objectID", functionAreaID).list();
    }

    public Set<String> getUserRoleIdsList(List<UserRoleMemberships2> userRoleMemberships2List) {
        Set<String> userRoleIdList = new HashSet();
        for (UserRoleMemberships2 urm : userRoleMemberships2List) {
            userRoleIdList.add(urm.getId().getUserRoleId());
        }
        return userRoleIdList;
    }

    public PermissionTranslations getPermissionTranslations(Session session, String permissionid, int countryId) {
        PermissionTranslations permissionTranslations = (PermissionTranslations) session.getNamedQuery("getPermissionTranslations").setParameter("permissionId", permissionid).setInteger("countryID", countryId).list().get(0);
        return permissionTranslations;
    }
     public List<PermissionTranslations> getPermissionTranslationsByIdsAndCountry(Session session, List<String> permissionid, int countryId) {
        return session.getNamedQuery("getPermissionTranslationsByIdsAndCountry").setParameterList("permissionId", permissionid).setInteger("countryID", countryId).list();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import se.info24.pojo.FunctionAreas;
import se.info24.pojo.PermissionOperations;
import se.info24.pojo.UserRoleMemberships2;
import se.info24.pojo.UserRoleObjectPermissions2;

/**
 *
 * @author Hitha
 */
public class UserPermissions {

    public DbManager dbManager = new DbManager();

    public Hashtable<String, ArrayList> getUserRolePermissions2ByUserID(String userID) {
        Session session = null;
        Hashtable<String, ArrayList> userFunctionsAreas = new Hashtable<String, ArrayList>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            List<UserRoleMemberships2> userRoleMemberships2 = dbManager.getUserRoleIdByUserId(session, userID);
            List<UserRoleObjectPermissions2> userRoleObjectPermissions2 = null;
            String userRoleId = null;
            ArrayList<String> arr_oper = new ArrayList<String>();
            String functionAreaName = null;
            for (UserRoleMemberships2 urm : userRoleMemberships2) {
                userRoleId = urm.getId().getUserRoleId();
                userRoleObjectPermissions2 = dbManager.getUserRoleObjectPermissions2ByUserRoleId(session, userRoleId);
                if (userRoleId != null) {
                    for (UserRoleObjectPermissions2 urop : userRoleObjectPermissions2) {
//                        String permissionid = urop.getPermissions().getPermissionId();
//                        String objectid = urop.getId().getObjectId();
                        List<PermissionOperations> permissionOperations = dbManager.getPermissionOperationsByPermissionID(session, urop.getPermissions().getPermissionId());
                        List<FunctionAreas> functionAreaTranslations = dbManager.getFunctionAreasByObjectID(session, urop.getId().getObjectId());
                        for (FunctionAreas fa : functionAreaTranslations) {
                            if (functionAreaName == null) {
                                functionAreaName = fa.getFunctionAreaTechName();
                            } else if (!functionAreaName.equalsIgnoreCase(fa.getFunctionAreaTechName())) {
                                functionAreaName = fa.getFunctionAreaTechName();
                                arr_oper = new ArrayList<String>();
                            }
                            for (PermissionOperations po : permissionOperations) {
                                arr_oper.add(po.getOperations().getOperationName());
                            }
                            userFunctionsAreas.put(fa.getFunctionAreaTechName(), arr_oper);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            Logger logger = Logger.getLogger(UserSessions2Daemon.class);
            StringWriter stack = new StringWriter();
            ex.printStackTrace(new PrintWriter(stack));
            logger.error(stack.toString());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return userFunctionsAreas;
    }
}

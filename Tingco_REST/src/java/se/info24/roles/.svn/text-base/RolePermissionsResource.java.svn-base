/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.roles;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.xml.datatype.DatatypeConfigurationException;
import org.hibernate.Session;
import se.info24.pojo.UserSessions2;
import se.info24.user.UserManager;
import se.info24.usersjaxb.TingcoUsers;
import se.info24.util.HibernateUtil;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Sridhar
 */
@Path("/rolepermissions")
public class RolePermissionsResource {

    @Context
    private UriInfo context;
//    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /** Creates a new instance of RolePermissionsResource */
    public RolePermissionsResource() {
    }

    /**
     * RolePermissions_Add
     * @param request
     * @param roleID
     * @param permID
     * @param groupID
     * @return
     */
    @GET
    @Path("/add/rest/{rest}/userroleid/{userroleid}/permissionid/{permissionid}/groupid/{groupid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "RolePermissions_Add", desc = "Adds new Role Permissions", req_Params = {"UserRoleID;UUID", "PermissionID;UUID", "GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_Add(@Context HttpServletRequest request, @PathParam("userroleid") String roleID, @PathParam("permissionid") String permID,
            @PathParam("groupid") String groupID) {
        return isAddedNewRolePermission(roleID, permID, groupID, request);
    }

    /**
     * RolePermissions_Add
     * @param request
     * @param roleID
     * @param permID
     * @param groupID
     * @return
     */
    @GET
    @Path("/add/json/{json}/userroleid/{userroleid}/permissionid/{permissionid}/groupid/{groupid}")
    @Produces("application/json")
    public TingcoUsers getJson_Add(@Context HttpServletRequest request, @PathParam("userroleid") String roleID, @PathParam("permissionid") String permID,
            @PathParam("groupid") String groupID) {
        return isAddedNewRolePermission(roleID, permID, groupID, request);
    }

    /**
     * RolePermission_Delete
     * @param request
     * @param roleID
     * @param groupID
     * @param permID
     * @return
     */
    @GET
    @Path("/delete/rest/{rest}/userroleid/{userroleid}/groupid/{groupid}{permissionid:(/permissionid/[^/]+?)?}")
    @Produces("application/xml")
    @RESTDoc(methodName = "RolePermission_Delete", desc = "Delete's Permissions for a Particular User ", req_Params = {"RoleID;UUID", "PermissionID;UUID", "GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_Delete(@Context HttpServletRequest request, @PathParam("userroleid") String roleID, @PathParam("groupid") String groupID, @PathParam("permissionid") String permID) {

        if (!permID.equals("")) {
            permID = (permID.split("/")[2]);
        } else {
            permID = null;
        }

        return isRolePermissionDeleted(roleID, permID, groupID, request);
    }

    /**
     * RolePermission_Delete
     * @param request
     * @param roleID
     * @param groupID
     * @param permID
     * @return
     */
    @GET
    @Path("/delete/json/{json}/userroleid/{userroleid}/groupid/{groupid}{permissionid:(/permissionid/[^/]+?)?}")
    @Produces("application/json")
    public TingcoUsers getJson_Delete(@Context HttpServletRequest request, @PathParam("userroleid") String roleID, @PathParam("groupid") String groupID, @PathParam("permissionid") String permID) {

        if (!permID.equals("")) {
            permID = (permID.split("/")[2]);
        } else {
            permID = null;
        }

        return isRolePermissionDeleted(roleID, permID, groupID, request);
    }

    /**
     * IsRolePermissionExists
     * @param request
     * @param roleID
     * @param permID
     * @param groupID
     * @return
     */
    @GET
    @Path("/isexists/rest/{rest}/userroleid/{userroleid}/permissionid/{permissionid}/groupid/{groupid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "IsRolePermissionExists", desc = "Check's whether Role Permissions Exists or Not", req_Params = {"UserRoleID;UUID", "PermissionID;UUID", "GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_IsExists(@Context HttpServletRequest request, @PathParam("userroleid") String roleID, @PathParam("permissionid") String permID,
            @PathParam("GroupID") String groupID) {

        return isRolePermissionExists(roleID, permID, groupID, request);
    }

    /**
     * IsRolePermissionExists
     * @param request
     * @param roleID
     * @param permID
     * @param groupID
     * @return
     */
    @GET
    @Path("/isexists/json/{json}/userroleid/{userroleid}/permissionid/{permissionid}/groupid/{groupid}")
    @Produces("application/json")
    public TingcoUsers getJson_IsExists(@Context HttpServletRequest request, @PathParam("userroleid") String roleID, @PathParam("permissionid") String permID,
            @PathParam("GroupID") String groupID) {

        return isRolePermissionExists(roleID, permID, groupID, request);
    }

    /**
     * POST method for creating an instance of RolePermissionResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    public Response postXml(String content) {
        //TODO
        return Response.created(context.getAbsolutePath()).build();
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public RolePermissionResource getRolePermissions_AddResource(@PathParam("id") String id) {
        return RolePermissionResource.getInstance(id);
    }

    private TingcoUsers isAddedNewRolePermission(String roleID, String permID, String groupID, HttpServletRequest request) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        RoleManager manager = new RoleManager();
        TingcoUsers user = null;
        long requestedTime = System.currentTimeMillis();
        try {
            user = manager.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 user_sess = (UserSessions2) request.getAttribute("USERSESSION");
                boolean added = manager.addNewRolePermission(roleID, permID, groupID, user_sess, session);
                if (!added) {
                    user.getMsgStatus().setResponseCode(-1);
                    user.getMsgStatus().setResponseText("Error");
                }
            } else {
                TCMUtil tcm = new TCMUtil();
                user = tcm.sessionExpired();
            }
        } catch (DatatypeConfigurationException e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            session.close();
            delayLog(requestedTime);
        }
        return user;
    }

    private TingcoUsers isRolePermissionDeleted(String roleID, String permID, String groupID, HttpServletRequest request) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        RoleManager manager = new RoleManager();
        TingcoUsers user = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                user = manager.buildUserTemplate();
                boolean del = manager.deleteRolePermission(roleID, permID, groupID, session);
                if (del) {
                    return user;
                } else {
                    user.getMsgStatus().setResponseCode(-1);
                    user.getMsgStatus().setResponseText("Error");
                }
            } else {
                TCMUtil tcm = new TCMUtil();
                user = tcm.sessionExpired();
            }
        } catch (DatatypeConfigurationException e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            session.close();
            delayLog(requestedTime);
        }
        return user;
    }

     private TingcoUsers isRolePermissionExists(String roleID, String permID, String groupID, HttpServletRequest request) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserManager manager = new UserManager();
        TingcoUsers user = null;
        long requestedTime = System.currentTimeMillis();
        try {
            user = manager.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                boolean exists = manager.isRolePermissionExists(roleID, permID, groupID, session);
                if (!exists) {
                    user.getMsgStatus().setResponseCode(-1);
                    user.getMsgStatus().setResponseText("Error");
                }
            } else {
                TCMUtil tcm = new TCMUtil();
                user = tcm.sessionExpired();
            }
        } catch (DatatypeConfigurationException e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            session.close();
            delayLog(requestedTime);
        }
        return user;
    }
     public void delayLog(long requestedTime) {
         TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}

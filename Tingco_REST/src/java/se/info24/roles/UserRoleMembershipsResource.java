/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.roles;

import java.util.GregorianCalendar;
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
import se.info24.usersjaxb.TingcoUsers;
import se.info24.util.HibernateUtil;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Sridhar Dasari
 */
@Path("/userrolememberships")
public class UserRoleMembershipsResource {

    @Context
    private UriInfo context;
//    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of UserRoleMembershipsResource */
    public UserRoleMembershipsResource() {
    }

    @GET
    @Path("/add/toactivate/rest/{rest}/userid/{userid}/roleid/{roleid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "UserRoleMemberships_Add", desc = "Adds user roles to userrole memberships", req_Params = {"UserID;UUID", "RoleID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers urmtoActivate(@Context HttpServletRequest request, @PathParam("userid") String userID,
            @PathParam("roleid") String roleID) {

        return insertUserRoleMemberShip(userID, roleID, request);
    }

    /**
     * UserRoleMemberships_Add
     * @param request
     * @param userID
     * @param roleID
     * @return
     */
    @GET
    @Path("/add/rest/{rest}/userid/{userid}/roleid/{roleid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "UserRoleMemberships_Add", desc = "Adds user roles to userrole memberships", req_Params = {"UserID;UUID", "RoleID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_Add(@Context HttpServletRequest request, @PathParam("userid") String userID,
            @PathParam("roleid") String roleID) {

        return addUserRoleMemberShip(userID, roleID, request);
    }

    /**
     * UserRoleMemberships_Add
     * @param request
     * @param userID
     * @param roleID
     * @return
     */
    @GET
    @Path("/add/json/{json}/userid/{userid}/roleid/{roleid}")
    @Produces("application/json")
    public TingcoUsers getJson_Add(@Context HttpServletRequest request, @PathParam("userid") String userID,
            @PathParam("roleid") String roleID) {

        return insertUserRoleMemberShip(userID, roleID, request);
    }

    /**
     * UserRoleMemberships_Remove
     * @param request
     * @param userID
     * @param roleID
     * @return
     */
    @GET
    @Path("/delete/rest/{rest}/userid/{userid}/roleid/{roleid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "UserRoleMemberships_Remove", desc = "Delete user roles from userrole memberships", req_Params = {"UserID;UUID", "RoleID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml(@Context HttpServletRequest request, @PathParam("userid") String userID,
            @PathParam("roleid") String roleID) {
        return isDeletedMemShip(userID, roleID, request);
    }

    /**
     * UserRoleMemberships_Remove
     * @param request
     * @param userID
     * @param roleID
     * @return
     */
    @GET
    @Path("/delete/json/{json}/userid/{userid}/roleid/{roleid}")
    @Produces("application/json")
    public TingcoUsers getJson(@Context HttpServletRequest request, @PathParam("userid") String userID,
            @PathParam("roleid") String roleID) {
        return isDeletedMemShip(userID, roleID, request);
    }

    /**
     * GetUserRoleMemberships
     * @param request
     * @param userID
     * @return
     */
    @GET
    @Path("/get/rest/{rest}/userid/{userid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetUserRoleMemberships", desc = "Get the UserRoleMembership Data", req_Params = {"UserID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml(@Context HttpServletRequest request, @PathParam("userid") String userID) {
        return userRoleMemberships(userID, request);
    }

    /**
     * GetUserRoleMemberships
     * @param request
     * @param userID
     * @return
     */
    @GET
    @Path("/get/json/{json}/userid/{userid}")
    @Produces("application/json")
    public TingcoUsers getJson(@Context HttpServletRequest request, @PathParam("userid") String userID) {
        return userRoleMemberships(userID, request);
    }

    /**
     * POST method for creating an instance of UserRoleMembershipResource
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
    public UserRoleMembershipResource getUserRoleMemberships_AddResource(@PathParam("id") String id) {
        return UserRoleMembershipResource.getInstance(id);
    }

    @GET
    @Path("/adduserroletouserandgroup/rest/{rest}/userid/{userid}/roleid/{roleid}/groupid/{groupid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "UserRoleMemberships_Add", desc = "Adds user roles to userrole memberships", req_Params = {"UserID;UUID", "RoleID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_AddUserRoleToUserandGroup(@Context HttpServletRequest request, @PathParam("userid") String userID,
            @PathParam("roleid") String roleID, @PathParam("groupid") String groupId) {

        return addUserRoleToUserandGroup(userID, roleID, request,groupId);
    }

    @GET
    @Path("/adduserroletouserandgroup/json/{json}/userid/{userid}/roleid/{roleid}/groupid/{groupid}")
    @Produces("application/json")
    @RESTDoc(methodName = "UserRoleMemberships_Add", desc = "Adds user roles to userrole memberships", req_Params = {"UserID;UUID", "RoleID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getJson_AddUserRoleToUserandGroup(@Context HttpServletRequest request, @PathParam("userid") String userID,
            @PathParam("roleid") String roleID, @PathParam("groupid") String groupId) {

        return addUserRoleToUserandGroup(userID, roleID, request,groupId);
    }

    private TingcoUsers addUserRoleToUserandGroup(String userID, String roleID, HttpServletRequest request,String groupId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        RoleManager manager = new RoleManager();
        TingcoUsers user = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 user_sess = (UserSessions2) request.getAttribute("USERSESSION");
                user = manager.buildUserTemplate();
                manager.addUserRoleToUserandGroup(user_sess.getUserId(),userID, roleID, user_sess,groupId, session);
            } else {
                TCMUtil tcm = new TCMUtil();
                user = tcm.sessionExpired();
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            user.getMsgStatus().setResponseCode(-1);
            user.getMsgStatus().setResponseText("Error Occured");
        } finally {
            session.close();
            delayLog(requestedTime);
        }
        return user;
    }

    private TingcoUsers addUserRoleMemberShip(String userID, String roleID, HttpServletRequest request) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        RoleManager manager = new RoleManager();
        TingcoUsers user = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 user_sess = (UserSessions2) request.getAttribute("USERSESSION");
                user = manager.buildUserTemplate();
                manager.addUserRoleMemShip(userID, roleID, user_sess, session);
            } else {
                TCMUtil tcm = new TCMUtil();
                user = tcm.sessionExpired();
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            user.getMsgStatus().setResponseCode(-1);
            user.getMsgStatus().setResponseText("Error Occured");
        } finally {
            session.close();
            delayLog(requestedTime);
        }
        return user;
    }

    private TingcoUsers insertUserRoleMemberShip(String userID, String roleID, HttpServletRequest request) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        RoleManager manager = new RoleManager();
        TingcoUsers user = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 user_sess = (UserSessions2) request.getAttribute("USERSESSION");
                user = manager.buildUserTemplate();
                manager.insertUserRoleMemShip(userID, roleID, user_sess, session);
            } else {
                TCMUtil tcm = new TCMUtil();
                user = tcm.sessionExpired();
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            user.getMsgStatus().setResponseCode(-1);
            user.getMsgStatus().setResponseText("Error Occured");
        } finally {
            session.close();
            delayLog(requestedTime);
        }
        return user;
    }

    private TingcoUsers isDeletedMemShip(String userID, String roleID, HttpServletRequest request) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        RoleManager manager = new RoleManager();
        long requestedTime = System.currentTimeMillis();
        TingcoUsers user = null;
        GregorianCalendar gc = new GregorianCalendar();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                user = manager.buildUserTemplate();
                manager.deleteMemberShip(userID, roleID, session);
            } else {
                TCMUtil tcm = new TCMUtil();
                user = tcm.sessionExpired();
            }
        } catch (DatatypeConfigurationException e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            user.getMsgStatus().setResponseCode(-1);
            user.getMsgStatus().setResponseText("Error Occured");
        } finally {
            session.close();
            delayLog(requestedTime);
        }
        return user;
    }

    private TingcoUsers userRoleMemberships(String userID, HttpServletRequest request) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        RoleManager manager = new RoleManager();
        TingcoUsers user = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                user = manager.buildUserTemplate();
                user = manager.getUserRoleMemberships(userID, user, session);
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

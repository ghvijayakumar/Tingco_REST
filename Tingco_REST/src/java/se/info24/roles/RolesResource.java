/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.roles;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
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
import se.info24.jaxbUtil.TingcoUserXML;
import se.info24.pojo.UserRoles2;
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
@Path("/roles")
public class RolesResource {

    @Context
    private UriInfo context;
    @Context
    HttpServletRequest request;
    TingcoUserXML tingcoUserXML = new TingcoUserXML();
    RoleDAO roleDao = new RoleDAO();
//    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /** Creates a new instance of RolesResource */
    public RolesResource() {
    }

    /**
     * GetAllRoles
     * @param request
     * @param groupID
     * @return
     */
    @GET
    @Path("/rest/{rest}/groupid/{groupid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetAllRoles", desc = "Returns all Roles for a Particular User ", req_Params = {"GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_All(@Context HttpServletRequest request, @PathParam("groupid") String groupID) {
        return roles(groupID, request);
    }

    /**
     * GetAllRoles
     * @param request
     * @param groupID
     * @return
     */
    @GET
    @Path("/json/{json}/groupid/{groupid}")
    @Produces("application/json")
    public TingcoUsers getJson_All(@Context HttpServletRequest request, @PathParam("groupid") String groupID) {
        return roles(groupID, request);
    }

    /**
     * GetAllAppRoles
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getallapproles/rest/{rest}")
    @RESTDoc(methodName = "GetAllAppRoles", desc = "Get All Roles in TCM", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUsers getXml_AllAppRoles() {
        return getAllAppRoles();
    }

    /**
     * GetAllAppRoles
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getallapproles/json/{json}")
    public TingcoUsers getJson_AllAppRoles() {
        return getAllAppRoles();
    }

    /**
     * GetAllAppRoles
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/getallapproles/rest/{rest}")
    public TingcoUsers postXml_AllAppRoles() {
        return getAllAppRoles();
    }

    /**
     * GetAllAppRoles
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/getallapproles/json/{json}")
    public TingcoUsers postJson_AllAppRoles() {
        return getAllAppRoles();
    }

    /**
     * POST method for creating an instance of RoleResource
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
     * AddUserRole
     * @param userRoleId
     * @param userRoleParentId
     * @param userRoleName
     * @param userRoleDesc
     * @param isSystemRole
     * @param userId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/add/rest/{rest}/userroleid/{userroleid}{userroleparentid:(/userroleparentid/[^/]+?)?}/userrolename/{userrolename}{userroledescription:(/userroledescription/[^/]+?)?}/issystemrole/{issystemrole}")
    public TingcoUsers getXml_Add(@PathParam("userroleid") String userRoleId, @PathParam("userroleparentid") String userRoleParentId,
            @PathParam("userrolename") String userRoleName, @PathParam("userroledescription") String userRoleDesc, @PathParam("issystemrole") String isSystemRole) {
        if (userRoleParentId.equals("")) {
            userRoleParentId = null;
        } else {
            userRoleParentId = userRoleParentId.split("/")[2];
        }

        if (userRoleDesc.equals("")) {
            userRoleDesc = null;
        } else {
            userRoleDesc = userRoleDesc.split("/")[2];
        }


        return addRole(userRoleId, userRoleParentId, userRoleName, userRoleDesc, isSystemRole);
    }

    /**
     * AddUserRole
     * @param userRoleId
     * @param userRoleParentId
     * @param userRoleName
     * @param userRoleDesc
     * @param isSystemRole
     * @param userId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/add/json/{json}/userroleid/{userroleid}{userroleparentid:(/userroleparentid/[^/]+?)?}/userrolename/{userrolename}{userroledescription:(/userroledescription/[^/]+?)?}/issystemrole/{issystemrole}")
    public TingcoUsers getJson_Add(@PathParam("userroleid") String userRoleId, @PathParam("userroleparentid") String userRoleParentId,
            @PathParam("userrolename") String userRoleName, @PathParam("userroledescription") String userRoleDesc, @PathParam("issystemrole") String isSystemRole) {
        if (userRoleParentId.equals("")) {
            userRoleParentId = null;
        } else {
            userRoleParentId = userRoleParentId.split("/")[2];
        }

        if (userRoleDesc.equals("")) {
            userRoleDesc = null;
        } else {
            userRoleDesc = userRoleDesc.split("/")[2];
        }
        return addRole(userRoleId, userRoleParentId, userRoleName, userRoleDesc, isSystemRole);
    }

    /**
     * UpdateUserRole
     * @param userRoleId
     * @param userRoleParentId
     * @param userRoleName
     * @param userRoleDesc
     * @param isSystemRole
     * @param userId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/update/rest/{rest}/userroleid/{userroleid}{userroleparentid:(/userroleparentid/[^/]+?)?}/userrolename/{userrolename}{userroledescription:(/userroledescription/[^/]+?)?}/issystemrole/{issystemrole}")
    public TingcoUsers getXml_Update(@PathParam("userroleid") String userRoleId, @PathParam("userroleparentid") String userRoleParentId,
            @PathParam("userrolename") String userRoleName, @PathParam("userroledescription") String userRoleDesc, @PathParam("issystemrole") String isSystemRole) {

        if (userRoleParentId.equals("")) {
            userRoleParentId = null;
        } else {
            userRoleParentId = userRoleParentId.split("/")[2];
        }

        if (userRoleDesc.equals("")) {
            userRoleDesc = null;
        } else {
            userRoleDesc = userRoleDesc.split("/")[2];
        }
        return updateRole(userRoleId, userRoleParentId, userRoleName, userRoleDesc, isSystemRole);
    }

    /**
     * UpdateUserRole
     * @param userRoleId
     * @param userRoleParentId
     * @param userRoleName
     * @param userRoleDesc
     * @param isSystemRole
     * @param userId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/update/json/{json}/userroleid/{userroleid}{userroleparentid:(/userroleparentid/[^/]+?)?}/userrolename/{userrolename}{userroledescription:(/userroledescription/[^/]+?)?}/issystemrole/{issystemrole}")
    public TingcoUsers getJson_Update(@PathParam("userroleid") String userRoleId, @PathParam("userroleparentid") String userRoleParentId,
            @PathParam("userrolename") String userRoleName, @PathParam("userroledescription") String userRoleDesc, @PathParam("issystemrole") String isSystemRole) {
        return updateRole(userRoleId, userRoleParentId, userRoleName, userRoleDesc, isSystemRole);
    }

    /**
     * DeleteUserRole
     * @param userRoleId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/userroleid/{userroleid}")
    public TingcoUsers getXml_Delete(@PathParam("userroleid") String userRoleId) {
        return deleteRole(userRoleId);
    }

    /**
     * DeleteUserRole
     * @param userRoleId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/delete/json/{json}/userroleid/{userroleid}")
    public TingcoUsers getJson_Delete(@PathParam("userroleid") String userRoleId) {
        return deleteRole(userRoleId);
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public RoleResource getGetAllRolesResource(@PathParam("id") String id) {
        return RoleResource.getInstance(id);
    }

    private TingcoUsers addRole(String userRoleId, String userRoleParentId, String userRoleName, String userRoleDesc, String isSystemRole) {
        TingcoUsers tingcoUsers = null;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoUsers = tingcoUserXML.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 userSessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                session = HibernateUtil.getSessionFactory().openSession();

                if (userRoleId == null) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("UserRoleID should not be null");
                    return tingcoUsers;
                }

                if (userRoleParentId == null) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("UserRoleParentID should not be null");
                    return tingcoUsers;
                }

                UserRoles2 userRoles = new UserRoles2();
                userRoles.setUserRoleId(userRoleId);
                userRoles.setUserRoleName(userRoleName);
                userRoles.setUserRoleDescription(userRoleDesc);
                userRoles.setIsSystemRole(Integer.valueOf(isSystemRole));
                userRoles.setLastUpdatedByUserId(userRoleId);
                userRoles.setUserRoleparentID(userRoleParentId);

                userRoles.setLastUpdatedByUserId(userSessions2.getUserId());
                GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                userRoles.setCreatedDate(gc.getTime());
                userRoles.setUpdatedDate(gc.getTime());

                if (roleDao.addUserRoles2(session, userRoles)) {
                    return tingcoUsers;
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("Error Occured While Inserting the Role");
                }

            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUsers;
    }

    private TingcoUsers updateRole(String userRoleId, String userRoleParentId, String userRoleName, String userRoleDesc, String isSystemRole) {
        TingcoUsers tingcoUsers = null;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoUsers = tingcoUserXML.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (userRoleId == null) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("UserRoleID should not be null");
                    return tingcoUsers;
                }

                UserSessions2 userSessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                session = HibernateUtil.getSessionFactory().openSession();
                if (roleDao.updateUserRoles2(session, userRoleId, userRoleParentId, userRoleName, userRoleDesc, isSystemRole, userSessions2.getUserId())) {
                    return tingcoUsers;
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("Error Occured While Updating the Role");
                }

            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUsers;
    }

    private TingcoUsers deleteRole(String userRoleId) {
        TingcoUsers tingcoUsers = null;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoUsers = tingcoUserXML.buildUserTemplate();
            if (userRoleId == null) {
                tingcoUsers.getMsgStatus().setResponseCode(-1);
                tingcoUsers.getMsgStatus().setResponseText("UserRoleID should not be null");
                return tingcoUsers;
            }

            if (request.getAttribute("USERSESSION") != null) {
                session = HibernateUtil.getSessionFactory().openSession();
                roleDao.deleteUserRoles2(session, userRoleId);
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured While Deleting the Roles");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUsers;
    }

    private TingcoUsers roles(String groupID, HttpServletRequest request) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        RoleManager manager = new RoleManager();
        TingcoUsers user = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                user = manager.buildUserTemplate();
                user = manager.getAllRoles(groupID, user, session);
            } else {
                TCMUtil tcm = new TCMUtil();
                user = tcm.sessionExpired();
            }
        } catch (DatatypeConfigurationException e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            session.close();
            delayLog(requestedTime);
//            System.gc();
        }
        return user;
    }

    private TingcoUsers getAllAppRoles() {
        TingcoUsers tingcoUsers = null;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoUsers = tingcoUserXML.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                session = HibernateUtil.getSessionFactory().openSession();
                List<UserRoles2> userRoles2List = roleDao.getAllUserRoles2(session);
                if (userRoles2List != null) {
                    tingcoUserXML.buildALLAppRoles(tingcoUsers, userRoles2List);
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        }catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured While Getting the Roles");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUsers;
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}

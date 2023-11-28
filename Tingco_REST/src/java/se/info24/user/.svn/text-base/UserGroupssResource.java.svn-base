/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.user;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.xml.datatype.DatatypeConfigurationException;
import org.hibernate.Session;
import se.info24.pojo.UserRoles2;
import se.info24.pojo.UserRolesInGroups2;
import se.info24.pojo.UserRolesInGroups2Id;
import se.info24.pojo.UserSessions2;
import se.info24.usersjaxb.TingcoUsers;
import se.info24.util.HibernateUtil;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/usergroups")
public class UserGroupssResource {

    @Context
    private UriInfo context;
    @Context
    HttpServletRequest request;
    UserDAO userDao = new UserDAO();
//    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of UserGroupssResource */
    public UserGroupssResource() {
    }

    /**
     * AddToGroup
     * @param request
     * @param userID
     * @param groupID
     * @return
     */
    @GET
    @Path("/add/rest/{rest}/userid/{userid}/groupid/{groupid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "User_AddToGroup", desc = "Add's a user to a system group", req_Params = {"UserID;UUID", "GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUsers getXml_Add(@Context HttpServletRequest request, @PathParam("userid") String userID, @PathParam("groupid") String groupID) {
        return userAddedToGroup(userID, groupID, request);
    }

    /**
     * AddToGroup
     * @param request
     * @param userID
     * @param groupID
     * @return
     */
    @GET
    @Path("/add/json/{json}/userid/{userid}/groupid/{groupid}")
    @Produces("application/json")
    public TingcoUsers getJson_Add(@Context HttpServletRequest request, @PathParam("userid") String userID, @PathParam("groupid") String groupID) {
        return userAddedToGroup(userID, groupID, request);
    }

    /**
     * DeleteFromGroup
     * @param request
     * @param userID
     * @param groupID
     * @return
     */
    @GET
    @Path("/delete/rest/{rest}/userid/{userid}/groupid/{groupid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "User_DeleteFromGroup", desc = "Removes the specified user from a system group", req_Params = {"UserID;UUID", "GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUsers getXml_Delete(@Context HttpServletRequest request, @PathParam("userid") String userID, @PathParam("groupid") String groupID) {
        return deletedUSerFromGroup(userID, groupID, request);
    }

    /**
     * DeleteFromGroup
     * @param request
     * @param userID
     * @param groupID
     * @return
     */
    @GET
    @Path("/delete/json/{json}/userid/{userid}/groupid/{groupid}")
    @Produces("application/json")
    public TingcoUsers getJson_Delete(@Context HttpServletRequest request, @PathParam("userid") String userID, @PathParam("groupid") String groupID) {
        return deletedUSerFromGroup(userID, groupID, request);
    }

    /**
     * GetGroupMembershipDetails
     * @param request
     * @param userID
     * @param maxItems
     * @return
     */
    @GET
    @Path("/get/rest/{rest}/userid/{userid}{maxitems:(/maxitems/[^/]+?)?}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetGroupMemberships", desc = "Group Membership Details", req_Params = {"UserID;UUID"}, opt_Params = {"MaxItems;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUsers getXml(@Context HttpServletRequest request, @PathParam("userid") String userID, @PathParam("maxitems") String maxItems) {
        return groupMemberShips(userID, maxItems, request);
    }

    /**
     * GetGroupMembershipDetails
     * @param request
     * @param userID
     * @param maxItems
     * @return
     */
    @GET
    @Path("/get/json/{json}/userid/{userid}{maxitems:(/maxitems/[^/]+?)?}")
    @Produces("application/json")
    public TingcoUsers getJSON(@Context HttpServletRequest request, @PathParam("userid") String userID, @PathParam("maxitems") String maxItems) {
        return groupMemberShips(userID, maxItems, request);
    }

    /**
     * POST method for creating an instance of UserGroupssResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    public Response postXml(String content) {
        return Response.created(context.getAbsolutePath()).build();
    }
    /**
     * GetAllUserRoles2
     * @return
     */
    @GET
    @Path("/getalluserroles/rest/{rest}/groupid/{groupid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetAllUserRoles2", desc = "Get All UserRoles2", req_Params = {"GroupId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_GetAllUserRoles2(@PathParam("groupid") String groupId) {
        return getAllUserRoles2(groupId);
    }

    /**
     * GetAllUserRoles2
     * @return
     */
    @GET
    @Path("/getalluserroles/json/{json}/groupid/{groupid}")
    @Produces("application/json")
    public TingcoUsers getJson_GetAllUserRoles2(@PathParam("groupid") String groupId) {
        return getAllUserRoles2(groupId);
    }

    /**
     * InsertUserRoleForGroup
     * @param groupId
     * @param userRoleId
     * @return
     */
    @GET
    @Path("/insertuserroleforgroup/rest/{rest}/groupid/{groupid}/userroleid/{userroleid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "InsertUserRoleForGroup", desc = "Insert UserRole For Group", req_Params = {"GroupId;UUID", "UserRoleId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_InsertUserRoleForGroup(@PathParam("groupid") String groupId,@PathParam("userroleid") String userRoleId) {
        return insertUserRoleForGroup(groupId,userRoleId);
    }

    /**
     * InsertUserRoleForGroup
     * @param groupId
     * @param userRoleId
     * @return
     */
    @GET
    @Path("/insertuserroleforgroup/json/{json}/groupid/{groupid}/userroleid/{userroleid}")
    @Produces("application/json")
    public TingcoUsers getJson_InsertUserRoleForGroup(@PathParam("groupid") String groupId,@PathParam("userroleid") String userRoleId) {
        return insertUserRoleForGroup(groupId,userRoleId);
    }

    /**
     * DeleteUserRoleForGroup
     * @param groupId
     * @param userRoleId
     * @return
     */
    @GET
    @Path("/deleteuserroleforgroup/rest/{rest}/groupid/{groupid}/userroleid/{userroleid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "DeleteUserRoleForGroup", desc = "Delete UserRole For Group", req_Params = {"GroupId;UUID", "UserRoleId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_DeleteUserRoleForGroup(@PathParam("groupid") String groupId,@PathParam("userroleid") String userRoleId) {
        return deleteUserRoleForGroup(groupId,userRoleId);
    }

    /**
     * DeleteUserRoleForGroup
     * @param groupId
     * @param userRoleId
     * @return
     */
    @GET
    @Path("/deleteuserroleforgroup/json/{json}/groupid/{groupid}/userroleid/{userroleid}")
    @Produces("application/json")
    public TingcoUsers getJson_DeleteUserRoleForGroup(@PathParam("groupid") String groupId,@PathParam("userroleid") String userRoleId) {
        return deleteUserRoleForGroup(groupId,userRoleId);
    }
    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public UserGroupsResource getUserGroupsResource() {
        return new UserGroupsResource();
    }

    private TingcoUsers deleteUserRoleForGroup(String groupId, String userRoleId) {
        Session session = null;
        boolean hasPermission = false;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXML = new se.info24.jaxbUtil.TingcoUserXML();
        TingcoUsers tingcoUsers = null;
        UserRolesInGroups2 userRolesInGroups2 = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoUsers = tingcoUserXML.buildUserTemplate();
            if(groupId.equals("")){
                tingcoUsers.getMsgStatus().setResponseCode(-1);
                tingcoUsers.getMsgStatus().setResponseText("GroupID should not be empty");
                return tingcoUsers;
            }
            if(userRoleId.equals("")){
                tingcoUsers.getMsgStatus().setResponseCode(-1);
                tingcoUsers.getMsgStatus().setResponseText("UserRoleId should not be empty");
                return tingcoUsers;
            }
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    userRolesInGroups2 = userDao.getUserRolesInGroups2ByRoleAndGroupID(session, groupId, userRoleId);
                    if(userRolesInGroups2==null){
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoUsers;
                    }
                    try {
                        session.beginTransaction();
                        session.delete(userRolesInGroups2);
                        session.getTransaction().commit();
                    } catch (Exception e) {
                        if(session.getTransaction().wasCommitted()){
                            session.getTransaction().rollback();
                        }
                        TCMUtil.exceptionLog(getClass().getName(), e);
                        //e.printStackTrace();
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("Error occurred");
                        return tingcoUsers;
                    }
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-10);
                tingcoUsers.getMsgStatus().setResponseText("User Session is not Valid");
                return tingcoUsers;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error occurred");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUsers;
    }
    
    private TingcoUsers insertUserRoleForGroup(String groupId, String userRoleId) {
        Session session = null;
        boolean hasPermission = false;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXML = new se.info24.jaxbUtil.TingcoUserXML();
        TingcoUsers tingcoUsers = null;
        UserRolesInGroups2 userRolesInGroups2 = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoUsers = tingcoUserXML.buildUserTemplate();
            if(groupId.equals("")){
                tingcoUsers.getMsgStatus().setResponseCode(-1);
                tingcoUsers.getMsgStatus().setResponseText("GroupID should not be empty");
                return tingcoUsers;
            }
            if(userRoleId.equals("")){
                tingcoUsers.getMsgStatus().setResponseCode(-1);
                tingcoUsers.getMsgStatus().setResponseText("UserRoleId should not be empty");
                return tingcoUsers;
            }
            if (request.getAttribute("USERSESSION") != null) {                
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    userRolesInGroups2 = userDao.getUserRolesInGroups2ByRoleAndGroupID(session, groupId, userRoleId);
                    if(userRolesInGroups2!=null){
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("User Roles For Group Already Exists");
                        return tingcoUsers;
                    }
                    userRolesInGroups2 = new UserRolesInGroups2();
                    userRolesInGroups2.setId(new UserRolesInGroups2Id(userRoleId, groupId));
                    userRolesInGroups2.setCreatedDate(gc.getTime());
                    userRolesInGroups2.setUpdatedDate(gc.getTime());
                    userRolesInGroups2.setLastUpdatedByUserId(sessions2.getUserId());
                    try {
                        session.beginTransaction();
                        session.save(userRolesInGroups2);
                        session.getTransaction().commit();
                    } catch (Exception e) {
                        if(session.getTransaction().wasCommitted()){
                            session.getTransaction().rollback();
                        }
                        TCMUtil.exceptionLog(getClass().getName(), e);
                        //e.printStackTrace();
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("Error occurred");
                        return tingcoUsers;
                    }
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-10);
                tingcoUsers.getMsgStatus().setResponseText("User Session is not Valid");
                return tingcoUsers;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error occurred");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUsers;
    }

    private TingcoUsers getAllUserRoles2(String groupId) {
        Session session = null;
        boolean hasPermission = false;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXML = new se.info24.jaxbUtil.TingcoUserXML();
        TingcoUsers tingcoUsers = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoUsers = tingcoUserXML.buildUserTemplate();
            if(groupId.equals("")){
                tingcoUsers.getMsgStatus().setResponseCode(-1);
                tingcoUsers.getMsgStatus().setResponseText("GroupID should not be empty");
                return tingcoUsers;
            }
            if (request.getAttribute("USERSESSION") != null) {                
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();                    
                    List<UserRolesInGroups2> userRoleInGroupsList = userDao.getUserRolesInGroups2ByGroupId(groupId, session);
                    List<String> listIds = new ArrayList<String>();
                    for(UserRolesInGroups2 urg : userRoleInGroupsList){
                        listIds.add(urg.getId().getUserRoleId());
                    }
                    if(!listIds.isEmpty()){
                        List<UserRoles2> userRolesList = userDao.getUserRoleByIDs(session, listIds, 200);
                        tingcoUsers = tingcoUserXML.buildGetAllUserRoles2(tingcoUsers, userRolesList);
                    }else{
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoUsers;
                    }
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-10);
                tingcoUsers.getMsgStatus().setResponseText("User Session is not Valid");
                return tingcoUsers;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error occurred");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUsers;
    }

    private TingcoUsers userAddedToGroup(String userID, String groupID, HttpServletRequest request) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        UserManager manager = new UserManager();
        long requestedTime = System.currentTimeMillis();
        UserSessions2 user_sess = manager.getUserSession(session, request);
        TingcoUsers user = null;
        GregorianCalendar gc = new GregorianCalendar();
        try {
            user = manager.buildUserTemplate();
            if (user_sess.getActiveToDate().after(gc.getTime())) {
                manager.addUsertoGroup(userID, groupID, session);
            } else {
                TCMUtil tcm = new TCMUtil();
                user = tcm.sessionExpired();
            }
        }catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            user.getMsgStatus().setResponseCode(-1);
        } finally {
            session.close();
            delayLog(requestedTime);
        }
        return user;
    }

    private TingcoUsers deletedUSerFromGroup(String userID, String groupID, HttpServletRequest request) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserManager manager = new UserManager();
        long requestedTime = System.currentTimeMillis();
        UserSessions2 user_sess = (UserSessions2) request.getAttribute("USERSESSION");
        TingcoUsers user = null;
        GregorianCalendar gc = new GregorianCalendar();
        try {
            if (user_sess.getActiveToDate().after(gc.getTime())) {
                manager.deleteUserFromGroup(userID, groupID, session);
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

    private TingcoUsers groupMemberShips(String userID, String maxItems, HttpServletRequest request) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserManager manager = new UserManager();
        long requestedTime = System.currentTimeMillis();
        UserSessions2 user_sess = (UserSessions2) request.getAttribute("USERSESSION");
        TingcoUsers user = null;
        GregorianCalendar gc = new GregorianCalendar();
        try {
            if (user_sess.getActiveToDate().after(gc.getTime())) {
                user = manager.getUserGroupMemberShips(userID, maxItems, session);
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
        TCMUtil.loger(UserGroupssResource.class.getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}

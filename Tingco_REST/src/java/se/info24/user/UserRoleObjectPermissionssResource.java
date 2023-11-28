/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.user;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import org.hibernate.Session;
import se.info24.permission.PermissionDAO;
import se.info24.pojo.UserRoleObjectPermissions2;
import se.info24.usersjaxb.TingcoUsers;
import se.info24.util.HibernateUtil;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/userroleobjectpermissions")
public class UserRoleObjectPermissionssResource {

    @Context
    private UriInfo context;
    @Context
    HttpServletRequest request;
    se.info24.jaxbUtil.TingcoUserXML tingcoUserXML = new se.info24.jaxbUtil.TingcoUserXML();
    PermissionDAO permissinDAO = new PermissionDAO();
//    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of UserRoleObjectPermissionssResource */
    public UserRoleObjectPermissionssResource() {
    }

    /**
     * getAllUserRoleObjectPermissions
     * @param userRoleID
     * @param objectID
     * @param countryID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/all/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "all", desc = "Gets all userRoleObjectPermissions", req_Params = {"CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getAllUserRoleObjectPermissions_XML(@PathParam("countryid") int countryID) {
        return allUserRoleObjectPermissions(countryID);
    }

    /**
     * getAllUserRoleObjectPermissions
     * @param userRoleID
     * @param objectID
     * @param countryID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/all/json/{json}/countryid/{countryid}")
    @RESTDoc(methodName = "all", desc = "Gets all userRoleObjectPermissions", req_Params = {"CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getAllUserRoleObjectPermissions_JSON(@PathParam("countryid") int countryID) {
        return allUserRoleObjectPermissions(countryID);
    }

    /**
     * GetUserRoleObjectPermissions
     * @param userRoleID
     * @param objectID
     * @param countryID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/get/rest/{rest}/userroleid/{userroleid}/objectid/{objectid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetUserRoleObjectPermissions", desc = "Get the Permission List By UserRoleID and ObjectID", req_Params = {"UserRoleID;UUID", "ObjectID;UUID", "CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUsers getXml_getUserRoleObjectPermissions(@PathParam("userroleid") String userRoleID, @PathParam("objectid") String objectID, @PathParam("countryid") int countryID) {
        return getUserRoleObjectPermissions(userRoleID, objectID, countryID);
    }

    /**
     * GetUserRoleObjectPermissions
     * @param userRoleID
     * @param objectID
     * @param countryID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/get/json/{json}/userroleid/{userroleid}/objectid/{objectid}/countryid/{countryid}")
    public TingcoUsers getJson(@PathParam("userroleid") String userRoleID, @PathParam("objectid") String objectID, @PathParam("countryid") int countryID) {
        return getUserRoleObjectPermissions(userRoleID, objectID, countryID);
    }

    /**
     * GetUserRoleObjectPermissions
     * @param userRoleID
     * @param objectID
     * @param countryID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/get/rest/{rest}/userroleid/{userroleid}/objectid/{objectid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetUserRoleObjectPermissions", desc = "Get the Permission List By UserRoleID and ObjectID", req_Params = {"countryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUsers postXml(@PathParam("userroleid") String userRoleID, @PathParam("objectid") String objectID, @PathParam("countryid") int countryID) {
        return getUserRoleObjectPermissions(userRoleID, objectID, countryID);
    }

    /**
     * GetUserRoleObjectPermissions
     * @param userRoleID
     * @param objectID
     * @param countryID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/get/json/{json}/userroleid/{userroleid}/objectid/{objectid}/countryid/{countryid}")
    public TingcoUsers postJson(@PathParam("userroleid") String userRoleID, @PathParam("objectid") String objectID, @PathParam("countryid") int countryID) {
        return getUserRoleObjectPermissions(userRoleID, objectID, countryID);
    }

    /**
     * UserRoleObjectPermissions_Delete
     * @param userRoleID
     * @param permissionID
     * @param objectID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/userroleid/{userroleid}/permissionid/{permissionid}/objectid/{objectid}")
    @RESTDoc(methodName = "UserRoleObjectPermissions_Delete", desc = "Delete the UserRoleObjectPemissions", req_Params = {"UserRoleID;UUID", "PermissionID;UUID", "ObjectID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUsers getXml_Delete(@PathParam("userroleid") String userRoleID, @PathParam("permissionid") String permissionID, @PathParam("objectid") String objectID) {
        return delete_userRoleObjectPermissions(userRoleID, permissionID, objectID);
    }

    /**
     * UserRoleObjectPermissions_Delete
     * @param userRoleID
     * @param permissionID
     * @param objectID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/delete/json/{json}/userroleid/{userroleid}/permissionid/{permissionid}/objectid/{objectid}")
    public TingcoUsers getJson_Delete(@PathParam("userroleid") String userRoleID, @PathParam("permissionid") String permissionID, @PathParam("objectid") String objectID) {
        return delete_userRoleObjectPermissions(userRoleID, permissionID, objectID);
    }

    /**
     * UserRoleObjectPermissions_Delete
     * @param userRoleID
     * @param permissionID
     * @param objectID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/userroleid/{userroleid}/permissionid/{permissionid}/objectid/{objectid}")
    public TingcoUsers postXml_Delete(@PathParam("userroleid") String userRoleID, @PathParam("permissionid") String permissionID, @PathParam("objectid") String objectID) {
        return delete_userRoleObjectPermissions(userRoleID, permissionID, objectID);
    }

    /**
     * UserRoleObjectPermissions_Delete
     * @param userRoleID
     * @param permissionID
     * @param objectID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/delete/json/{json}/userroleid/{userroleid}/permissionid/{permissionid}/objectid/{objectid}")
    public TingcoUsers postJson_Delete(@PathParam("userroleid") String userRoleID, @PathParam("permissionid") String permissionID, @PathParam("objectid") String objectID) {
        return delete_userRoleObjectPermissions(userRoleID, permissionID, objectID);
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public UserRoleObjectPermissionsResource getUserRoleObjectPermissionsResource() {
        return new UserRoleObjectPermissionsResource();
    }

    private TingcoUsers allUserRoleObjectPermissions(int countryID) {
        long requestedTime = System.currentTimeMillis();
        TingcoUsers tingcoUsers = null;
        Session session = null;

        try {
            tingcoUsers = tingcoUserXML.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (countryID <= 0) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("CountryID is should not be empty");
                    return tingcoUsers;
                }


                session = HibernateUtil.getSessionFactory().openSession();
                List<UserRoleObjectPermissions2> uropList = permissinDAO.getUserRoleObjectPermissions(session);
                if (uropList != null) {
                    tingcoUserXML.buildUserRolesObjectPermissions(tingcoUsers, uropList, countryID);
                    return tingcoUsers;
                }

            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(UserRoleObjectPermissionssResource.class.getName(), e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured While retrieving the Roles");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUsers;
    }

    private TingcoUsers getUserRoleObjectPermissions(String userRoleID, String objectID, int countryID) {
        boolean hasPermission = false;
        TingcoUsers tingcoUsers = null;
        Session session = null;

        long requestedTime = System.currentTimeMillis();
        try {
            tingcoUsers = tingcoUserXML.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (userRoleID == null) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("UserRoleID  should not be empty");
                    return tingcoUsers;
                } else if (objectID == null) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("ObjectID  should not be empty");
                    return tingcoUsers;
                } else if (countryID <= 0) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("CountryID  should not be empty");
                    return tingcoUsers;
                }

//                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) request.getSession(true).getAttribute(request.getSession(true).getId());
//                if (ht.containsKey(TCMUtil.USERS)) {
//                    ArrayList<String> operations = ht.get(TCMUtil.USERS);
//                    for (int i = 0; i < operations.size(); i++) {
//                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                hasPermission = true;
//                        }
//                    }
//                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<UserRoleObjectPermissions2> uropList = permissinDAO.getUserRoleObjectPermissions(userRoleID, objectID, session);
                    if (uropList != null) {
                        tingcoUserXML.buildUserRolesObjectPermissions(tingcoUsers, uropList, countryID);
                        return tingcoUsers;
                    }
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(UserRoleObjectPermissionssResource.class.getName(), e);
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

    private TingcoUsers delete_userRoleObjectPermissions(String userRoleID, String permissionID, String objectID) {
        boolean hasPermission = false;
        TingcoUsers tingcoUsers = null;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoUsers = tingcoUserXML.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (userRoleID == null) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("UserRoleID is should not be empty");
                    return tingcoUsers;
                } else if (permissionID == null) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("PermissionID is should not be empty");
                    return tingcoUsers;
                } else if (objectID == null) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("ObjectID is should not be empty");
                    return tingcoUsers;
                }

//                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) request.getSession(true).getAttribute(request.getSession(true).getId());
//                if (ht.containsKey(TCMUtil.USERS)) {
//                    ArrayList<String> operations = ht.get(TCMUtil.USERS);
//                    for (int i = 0; i < operations.size(); i++) {
//                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                hasPermission = true;
//                        }
//                    }
//                }

                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserRoleObjectPermissions2 permissions2 = permissinDAO.getUserRoleObjectPermission(userRoleID, permissionID, objectID, session);
                    if (permissions2 != null) {
                        if (permissinDAO.removeUserRoleObjectPermission(permissions2, session)) {
                            return tingcoUsers;
                        } else {
                            tingcoUsers.getMsgStatus().setResponseCode(-1);
                            tingcoUsers.getMsgStatus().setResponseText("Error Occured While Deleting");
                            return tingcoUsers;
                        }
                    } else {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("Invalid Data Received");
                        return tingcoUsers;
                    }
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }

            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(UserRoleObjectPermissionssResource.class.getName(), e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(UserRoleObjectPermissionssResource.class.getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}

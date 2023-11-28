/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.permission;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.xml.datatype.DatatypeConfigurationException;
import org.hibernate.Session;
import se.info24.jaxbUtil.TingcoUserXML;
import se.info24.pojo.FunctionAreas;
import se.info24.pojo.PermissionOperations;
import se.info24.pojo.PermissionTranslations;
import se.info24.pojo.UserRoleObjectPermissions2;
import se.info24.pojo.UserSessions2;
import se.info24.user.User_LoginsResource;
import se.info24.usersjaxb.TingcoUsers;
import se.info24.util.HibernateUtil;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/permissions")
public class PermissionssResource {

    @Context
    private UriInfo context;
    @Context
    HttpServletRequest request;
    TingcoUsers tingcoUsers = new TingcoUsers();
    TingcoUserXML tingcoUserXML = new TingcoUserXML();
    PermissionDAO permissinDAO = new PermissionDAO();
    SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /** Creates a new instance of PermissionssResource */
    public PermissionssResource() {
    }

    /**
     * GetPermissions
     * @param countryID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/get/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetPermissions", desc = "Return's all Permissions", req_Params = {"CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUsers getXml(@PathParam("countryid") int countryID) {
        return getAllPermissions(countryID);
    }

    /**
     * GetPermissions
     * @param countryID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/get/rest/{rest}/countryid/{countryid}")
    public TingcoUsers postXml(@PathParam("countryid") int countryID) {
        return getAllPermissions(countryID);
    }

    /**
     * GetPermissions
     * @param countryID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/get/json/{json}/countryid/{countryid}")
    public TingcoUsers getJson(@PathParam("countryid") int countryID) {
        return getAllPermissions(countryID);
    }

    /**
     * GetPermissions
     * @param countryID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/get/json/{json}/countryid/{countryid}")
    public TingcoUsers postJson(@PathParam("countryid") int countryID) {
        return getAllPermissions(countryID);
    }

    /**
     * Get_LoggedInUsersPermissions
     * @param request
     * @param userID
     * @param countryID
     * @return
     */
    @GET
    @Path("/getloggedin/rest/{rest}/userid/{userid}/countryid/{countryid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "Get_LoggedInUsersPermissions", desc = "Return's all Permissions of the Logged in User", req_Params = {"UserID;UUID", "CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_LoggedIn(@Context HttpServletRequest request, @PathParam("userid") String userID, @PathParam("countryid") int countryID) {
        return getLoggedinUserPermissions(userID, countryID, request);
    }

    /**
     * Get_LoggedInUsersPermissions
     * @param request
     * @param userID
     * @param countryID
     * @return
     */
    @GET
    @Path("/getloggedin/json/{json}/userid/{userid}/countryid/{countryid}")
    @Produces("application/json")
    public TingcoUsers getJson_LoggedIn(@Context HttpServletRequest request, @PathParam("userid") String userID, @PathParam("countryid") int countryID) {
        return getLoggedinUserPermissions(userID, countryID, request);
    }

    /**
     * GetUserPermissions
     * @param userID
     * @param functionareas
     * @param countryID
     * @return
     */
    @GET
    @Path("/getuserpermissions/rest/{rest}/userid/{userid}/functionareas/{functionareas}/countryid/{countryid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetUsersPermissions", desc = "Return's all Permissions and operations of the Logged in User", req_Params = {"UserID;UUID", "FunctionAreas;string", "CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_GetUserPermissions(@PathParam("userid") String userID, @PathParam("functionareas") String functionAreas, @PathParam("countryid") int countryID) {
        return getUserPermissions(userID, functionAreas, countryID);
    }

    /**
     * GetUserPermissions
     * @param userID
     * @param functionareas
     * @param countryID
     * @return
     */
    @GET
    @Path("/getuserpermissions/json/{json}/userid/{userid}/functionareas/{functionareas}/countryid/{countryid}")
    @Produces("application/json")
    public TingcoUsers getJson_GetUserPermissions(@PathParam("userid") String userID, @PathParam("functionareas") String functionAreas, @PathParam("countryid") int countryID) {
        return getUserPermissions(userID, functionAreas, countryID);
    }

    private TingcoUsers getAllPermissions(int countryID) {
        TingcoUsers tingcoUsers = null;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoUsers = tingcoUserXML.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (countryID <= 0) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("CountryID  should not be empty");
                    return tingcoUsers;
                }
                session = HibernateUtil.getSessionFactory().openSession();
                List<PermissionTranslations> ptList = permissinDAO.getAllPermissions(countryID, session);
                if (ptList != null) {
                    tingcoUserXML.buildPermissions(tingcoUsers, ptList);
                }
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
            tingcoUsers.getMsgStatus().setResponseText("Error Occured While Getting the Roles");
            return tingcoUsers;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoUsers;
    }

    private TingcoUsers getLoggedinUserPermissions(String userID, int countryID, HttpServletRequest request) {
        TingcoUsers tingcoUsers = null;
        Session session = null;
        long requestedTime = System.currentTimeMillis();

        try {
            tingcoUsers = tingcoUserXML.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                session = HibernateUtil.getSessionFactory().openSession();
                List<UserRoleObjectPermissions2> uropList = permissinDAO.getLoggedinUserPermissions(userID, session);
                if (uropList != null) {
                    tingcoUserXML.buildLoggedinUserPermissions(tingcoUsers, countryID, uropList);
                }
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
            tingcoUsers.getMsgStatus().setResponseText("Error Occured While Getting the Roles");
            return tingcoUsers;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoUsers;
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public PermissionsResource getGetPermissionsResource(@PathParam("id") String id) {
        return PermissionsResource.getInstance(id);
    }

    private TingcoUsers getUserPermissions(String userID, String functionAreas, int countryID) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoUsers = tingcoUserXML.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (userID.equals("")) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("UserID should not be empty");
                    return tingcoUsers;
                }
                if (functionAreas.equals("")) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("FunctionAreas should not be empty");
                    return tingcoUsers;
                }
                if (countryID <= 0) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("CountryID should not be empty");
                    return tingcoUsers;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(functionAreas.toUpperCase())) {
                    ArrayList<String> operations = ht.get(functionAreas.toUpperCase());
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    FunctionAreas functionArea = permissinDAO.getFunctionAreas(session, functionAreas);
                    if (functionArea != null) {
                        List<UserRoleObjectPermissions2> userRoleObjectPermissionsList = permissinDAO.getpermissionid(userID, functionArea.getFunctionAreaId(), session);
                        if (!userRoleObjectPermissionsList.isEmpty()) {
                            Set<String> permissionidSet = new HashSet<String>();
                            for (UserRoleObjectPermissions2 urop : userRoleObjectPermissionsList) {
                                permissionidSet.add(urop.getId().getPermissionId());
                            }
                            List<PermissionTranslations> permissionTranslationsList = new ArrayList<PermissionTranslations>();
                            Hashtable<String, List<PermissionOperations>> hashTable = new Hashtable<String, List<PermissionOperations>>();
                            for (String permissionid : permissionidSet) {
                                PermissionTranslations permissionTranslations = permissinDAO.getPermissionTranslations(session, permissionid, countryID);
                                if (permissionTranslations != null) {
                                    if (permissionTranslations.getPermissionName() != null) {
                                        List<PermissionOperations> permissionOperationsList = permissinDAO.getPermissionOperations(session, permissionid);
                                        hashTable.put(permissionTranslations.getPermissionName(), permissionOperationsList);
                                    }
                                }
                            }

                            if (!hashTable.isEmpty()) {
                                tingcoUsers = tingcoUserXML.buildGetUserPermissions(tingcoUsers, hashTable, permissionTranslationsList);
                            } else {
                                tingcoUsers.getMsgStatus().setResponseCode(-1);
                                tingcoUsers.getMsgStatus().setResponseText("No Permission is given");
                                return tingcoUsers;
                            }
                        } else {
                            tingcoUsers.getMsgStatus().setResponseCode(-1);
                            tingcoUsers.getMsgStatus().setResponseText("No Permission is given");
                            return tingcoUsers;
                        }
                    } else {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("No FunctionArea found for the given input");
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
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoUsers;
    }
    public void delayLog(long requestedTime){
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took "+(System.currentTimeMillis()- requestedTime)+"ms]", "Info");
	}
}

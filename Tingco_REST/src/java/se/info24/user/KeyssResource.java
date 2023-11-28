/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.xml.datatype.DatatypeConfigurationException;
import org.hibernate.Session;
import se.info24.pojo.UserAliasOrders;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.UserTimeZones2;
import se.info24.restUtil.RestUtilDAO;
import se.info24.tingcoEV.TingcoEV;
import se.info24.tingcoEV.UserAliasOrder;
import se.info24.util.HibernateUtil;
import se.info24.util.JAXBManager;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/keys")
public class KeyssResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoUserXML userXml = new TingcoUserXML();
    UserDAO manager = new UserDAO();
//    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of KeyssResource */
    public KeyssResource() {
    }

    /**
     * GetOrderedKeysByUserID
     * @param userID
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/ordered/rest/{rest}/userid/{userid}")
    @RESTDoc(methodName = "GetOrderedKeysByUserID", desc = "Returns user orders", req_Params = {"UserID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoEV getXml_Ordered(@PathParam("userid") String userID) throws DatatypeConfigurationException {
        return getOrderedKeysByUserID(userID);
    }

    /**
     * GetOrderedKeysByUserID
     * @param userID
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/ordered/json/{json}/userid/{userid}")
    public TingcoEV getJson_Ordered(@PathParam("userid") String userID) throws DatatypeConfigurationException {
        return getOrderedKeysByUserID(userID);
    }

    /**
     * GetLoggedInUserKeys
     * @param userId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/loggedin/rest/{rest}/userid/{userid}")
    @RESTDoc(methodName = "GetUserLoggedInKeyss", desc = "Reteieves user alias", req_Params = {"UserID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoEV getXml_LoggedIn(@PathParam("userid") String userId) throws DatatypeConfigurationException {
        return getTingcoLoggedInUserIdXML(userId);
    }

    /**
     * GetLoggedInUserKeys
     * @param userId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/loggedin/json/{json}/userid/{userid}")
    public TingcoEV getJson_LoggedIn(@PathParam("userid") String userId) throws DatatypeConfigurationException {
        return getTingcoLoggedInUserIdXML(userId);
    }

    /**
     * InsertOrderNewKeys
     * @param content
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @POST
    @Path("/addorder/rest/{rest}")
    @Consumes("application/xml")
    @Produces("application/xml")
    @RESTDoc(methodName = "InsertOrderNewKeys", desc = "Insert Order Key", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoEV getXml_AddOrder(String content) throws DatatypeConfigurationException {
        TingcoEV tingcoEV = (TingcoEV) JAXBManager.getInstance().unMarshall(content, TingcoEV.class);
        return insertOrderData(tingcoEV);
    }

    /**
     * InsertOrderNewKeys
     * @param content
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @POST
    @Path("/addorder/json/{json}")
    @Consumes("application/xml")
    @Produces("application/json")
    public TingcoEV getJson_AddOrder(String content) throws DatatypeConfigurationException {
        TingcoEV tingcoEV = (TingcoEV) JAXBManager.getInstance().unMarshall(content, TingcoEV.class);
        return insertOrderData(tingcoEV);
    }

    /**
     * UpdateUserKeyDetails
     * @param userAliasId
     * @param firstName
     * @param lastName
     * @param isEnabled
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/update/rest/{rest}/useraliasid/{useraliasid}/firstname/{firstname}/lastname/{lastname}/isenabled/{isenabled}")
    @RESTDoc(methodName = "User_UpdateUserKeyDetails", desc = "Used to update the specified user’s information", req_Params = {"UserAliasID;UUID", "FirstName;String", "LastName;String", "IsEnabled;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoEV getXml_Update(@PathParam("useraliasid") String userAliasId, @PathParam("firstname") String firstName, @PathParam("lastname") String lastName, @PathParam("isenabled") int isEnabled)
            throws DatatypeConfigurationException {

        return updateUserAliasIdDetails(userAliasId, firstName, lastName, isEnabled);
    }

    /**
     * UpdateUserKeyDetails
     * @param userAliasId
     * @param firstName
     * @param lastName
     * @param isEnabled
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/update/json/{json}/useraliasid/{useraliasid}/firstname/{firstname}/lastname/{lastname}/isenabled/{isenabled}")
    public TingcoEV getJson_Update(@PathParam("useraliasid") String userAliasId, @PathParam("firstname") String firstName, @PathParam("lastname") String lastName, @PathParam("isenabled") int isEnabled)
            throws DatatypeConfigurationException {

        return updateUserAliasIdDetails(userAliasId, firstName, lastName, isEnabled);
    }

    /**
     * GetUserAliasOrdersList
     * @param IsDelivered
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getuseraliasorderslist/rest/{rest}/isdelivered/{isdelivered}{groupid:(/groupid/[^/]+?)?}{ordercomment:(/ordercomment/[^/]+?)?}")
    @RESTDoc(methodName = "GetUserAliasOrdersList", desc = "Returns user orders", req_Params = {"IsDelivered;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoEV getXml_getUserAliasOrdersList(@PathParam("isdelivered") String isDelivered, @PathParam("groupid") String groupId, @PathParam("ordercomment") String orderComment) throws DatatypeConfigurationException {
        return getUserAliasOrdersList(isDelivered, groupId, orderComment);
    }

    @GET
    @Produces("application/json")
    @Path("/getuseraliasorderslist/json/{json}/isdelivered/{isdelivered}{groupid:(/groupid/[^/]+?)?}{ordercomment:(/ordercomment/[^/]+?)?}")
    public TingcoEV getJson_getUserAliasOrdersList1(@PathParam("isdelivered") String isDelivered, @PathParam("groupid") String groupId, @PathParam("ordercomment") String orderComment) throws DatatypeConfigurationException {
        return getUserAliasOrdersList(isDelivered, groupId, orderComment);
    }

    /**
     * GetUserAliasOrdersList
     * @param IsDelivered
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/ordered/json/{json}/isdelivered/{isdelivered}{groupid:(/groupid/[^/]+?)?}{ordercomment:(/ordercomment/[^/]+?)?}")
    public TingcoEV getJson_getUserAliasOrdersList(@PathParam("isdelivered") String isDelivered, @PathParam("groupid") String groupId, @PathParam("ordercomment") String orderComment) throws DatatypeConfigurationException {
        return getUserAliasOrdersList(isDelivered, groupId, orderComment);
    }

    /**
     * DeleteUserAliasOrder
     * @param UserAliasOrderId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/deleteuseraliasorder/rest/{rest}/useraliasorderid/{useraliasorderid}")
    @RESTDoc(methodName = "DeleteUserAliasOrder", desc = "DeleteUserAliasOrder", req_Params = {"UserAliasOrderId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoEV getXml_deleteUserAliasOrder(@PathParam("useraliasorderid") String userAliasOrderId) throws DatatypeConfigurationException {
        return deleteUserAliasOrder(userAliasOrderId);
    }

    /**
     * DeleteUserAliasOrder
     * @param UserAliasOrderId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/deleteuseraliasorder/json/{json}/useraliasorderid/{useraliasorderid}")
    public TingcoEV getJson_deleteUserAliasOrder(@PathParam("useraliasorderid") String userAliasOrderId) throws DatatypeConfigurationException {
        return deleteUserAliasOrder(userAliasOrderId);
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public KeysResource getKeysResource() {
        return new KeysResource();
    }

    @SuppressWarnings("unchecked")
    public TingcoEV getOrderedKeysByUserID(String userID) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        TingcoEV tingcoEv = null;
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        try {
            tingcoEv = userXml.buildTingcoUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                // Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) request.getSession(true).getAttribute(request.getSession(true).getId());

//            if (ht.containsKey(TCMUtil.USERS)) {
//                ArrayList<String> operations = ht.get(TCMUtil.USERS);
//                for (int i = 0; i < operations.size(); i++) {
//                    if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                hasPermission = true;
//                    }
//                }
//            }

                if (userID == null || userID.equals("")) {
                    tingcoEv.getMsgStatus().setResponseCode(-1);
                    tingcoEv.getMsgStatus().setResponseText("UserID should not be empty");
                    return tingcoEv;
                } else {
                    if (hasPermission) {
                        session = HibernateUtil.getSessionFactory().openSession();
                        UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                        if (userTimeZones2 != null) {
                            String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                            List<UserAliasOrders> uao = manager.getOrderedKeysByUserID(session, userID);
                            if (uao.size() > 0) {
                                tingcoEv = userXml.buildUserAliasOrder(tingcoEv, uao, timeZoneGMToffset);
                            } else {
                                tingcoEv.getMsgStatus().setResponseCode(-1);
                                tingcoEv.getMsgStatus().setResponseText("UserId not found");
                                return tingcoEv;
                            }

                        } else {
                            tingcoEv.getMsgStatus().setResponseCode(-1);
                            tingcoEv.getMsgStatus().setResponseText("UserTimeZone not found");
                            return tingcoEv;
                        }
                    } else {
                        tingcoEv.getMsgStatus().setResponseCode(-10);
                        tingcoEv.getMsgStatus().setResponseText("User Permission not given");
                        return tingcoEv;
                    }
                }
            } else {
                tingcoEv.getMsgStatus().setResponseCode(-3);
                tingcoEv.getMsgStatus().setResponseText("User session not valid");
//            return tingcoEv;
            }
        } catch (Exception e) {
            tingcoEv.getMsgStatus().setResponseCode(-1);
            tingcoEv.getMsgStatus().setResponseText("Error occurred while retriving data");
//                        return tingcoEv;
        } finally {
            session.close();
            delayLog(requestedTime);
        }
        return tingcoEv;
    }

    private TingcoEV deleteUserAliasOrder(String userAliasOrderId) {
        boolean hasPermission = false;
        TingcoEV tingcoEv = null;
        long requestedTime = System.currentTimeMillis();
        Session session = null;
//        Transaction tx = null;
        try {
            tingcoEv = userXml.buildTingcoUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.USERALIAS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.USERALIAS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
//                    tx = session.beginTransaction();
                    Object object = manager.getUserAliasOrderById(userAliasOrderId, session);
                    if (object != null) {
                        session.delete(object);
                        session.beginTransaction().commit();
                    } else {
                        tingcoEv.getMsgStatus().setResponseCode(-1);
                        tingcoEv.getMsgStatus().setResponseText("UserKeyOrder not found");
                        return tingcoEv;
                    }

                } else {
                    tingcoEv.getMsgStatus().setResponseCode(-10);
                    tingcoEv.getMsgStatus().setResponseText("User Permission not given");
                    return tingcoEv;
                }
            } else {
                tingcoEv.getMsgStatus().setResponseCode(-3);
                tingcoEv.getMsgStatus().setResponseText("User session not valid");
                return tingcoEv;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoEv.getMsgStatus().setResponseCode(-1);
            tingcoEv.getMsgStatus().setResponseText("Error occurred");
            return tingcoEv;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoEv;
    }

    private TingcoEV getTingcoLoggedInUserIdXML(String userId) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        long requestedTime = System.currentTimeMillis();
        TingcoEV loggedInUserID = new TingcoEV();
        TingcoUserXML userXML = new TingcoUserXML();
        loggedInUserID = userXML.buildTingcoUserTemplate();
        if (request.getAttribute("USERSESSION") != null) {
            //Hashtable<String,ArrayList> ht = (Hashtable<String, ArrayList>) request.getSession(true).getAttribute(request.getSession(true).getId());
            //   Hashtable<String,ArrayList> ht = (Hashtable<String, ArrayList>) request.getSession(true).getAttribute(sess.getUserId());
//             ht =  TCMUtil.getFunctionAreas();
//            if (ht.containsKey(TCMUtil.USERALIAS)) {
//                ArrayList<String> operations = ht.get(TCMUtil.USERALIAS);
//                for(int i=0;i<operations.size();i++){
//                    if(operations.get(i).equalsIgnoreCase(TCMUtil.READ)){
            hasPermission = true;
//                    }
//                }
//            }
            if (userId == null) {
                loggedInUserID.getMsgStatus().setResponseCode(-1);
                loggedInUserID.getMsgStatus().setResponseText("User ID should not be empty");
                return loggedInUserID;
            } else {
                if (hasPermission == true) {
                    loggedInUserID = userXML.buildTingcoUserAliasXML(userId);
                    delayLog(requestedTime);
                    return loggedInUserID;
                } else {
                    loggedInUserID.getMsgStatus().setResponseCode(-10);
                    loggedInUserID.getMsgStatus().setResponseText("User Permission is not given");
                    delayLog(requestedTime);
                    return loggedInUserID;
                }
            }

        } else {
            loggedInUserID.getMsgStatus().setResponseCode(-3);
            loggedInUserID.getMsgStatus().setResponseText("User Session is Not Valid");
            delayLog(requestedTime);
            return loggedInUserID;
        }
    }

    @SuppressWarnings("unchecked")
    public TingcoEV insertOrderData(TingcoEV input) throws DatatypeConfigurationException {
        TingcoEV tingcoEv = null;
        long requestedTime = System.currentTimeMillis();
        tingcoEv = userXml.buildTingcoUserTemplate();
        Session session = null;
        try {
            if (request.getAttribute("USERSESSION") != null) {
                boolean hasPermission = false;
//                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) request.getSession(true).getAttribute(request.getSession(true).getId());
//            if (ht.containsKey(TCMUtil.USERS)) {
//                ArrayList<String> operations = ht.get(TCMUtil.USERS);
//                for (int i = 0; i < operations.size(); i++) {
//                    if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                hasPermission = true;
//                    }
//                }
//            }

                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserSessions2 sess = (UserSessions2) request.getAttribute("USERSESSION");
                    List<UserAliasOrder> list = input.getUserAliasOrders().getUserAliasOrder();
                    boolean inserted = manager.insertOrderNewKey(session, list, sess.getUserId());
                    if (inserted) {
                        tingcoEv.getMsgStatus().setResponseCode(0);
                        tingcoEv.getMsgStatus().setResponseText("OK");
                    } else {
                        tingcoEv.getMsgStatus().setResponseCode(-1);
                        tingcoEv.getMsgStatus().setResponseText("Error occurred while inserting data");
//                    return tingcoEv;
                    }

                    return tingcoEv;
                } else {
                    tingcoEv.getMsgStatus().setResponseCode(-10);
                    tingcoEv.getMsgStatus().setResponseText("User Permission not given");
                    return tingcoEv;

                }
            } else {
                tingcoEv.getMsgStatus().setResponseCode(-3);
                tingcoEv.getMsgStatus().setResponseText("User session not valid");
                return tingcoEv;

            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoEv.getMsgStatus().setResponseCode(-1);
            tingcoEv.getMsgStatus().setResponseText("Error occurred while inserting data");
            return tingcoEv;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoEV getUserAliasOrdersList(String isDelivered, String groupId, String orderComment) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoEV tingcoEv = null;
        Session session = null;
        try {
            tingcoEv = userXml.buildTingcoUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (!groupId.equalsIgnoreCase("")) {
                    groupId = groupId.split("/")[2];
                } else {
                    groupId = null;
                }
                if (!orderComment.equalsIgnoreCase("")) {
                    orderComment = orderComment.split("/")[2];
                } else {
                    orderComment = null;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.USERALIAS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.USERALIAS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        List<UserAliasOrders> uao = new ArrayList<UserAliasOrders>();

                            if (groupId == null) {
                                uao = manager.getUserAliasOrdersByIsDelivered(session, isDelivered,orderComment, 500);
                            } else {
                                uao = manager.getUserAliasOrdersByGroups(session, isDelivered, groupId, orderComment, 500);
                                Collections.sort(uao, new Comparator<UserAliasOrders>() {

                                    public int compare(UserAliasOrders o1, UserAliasOrders o2) {
                                        return o2.getOrderDate().compareTo(o1.getOrderDate());
                                    }
                                });
                            }


                        if (!uao.isEmpty()) {
                            tingcoEv = userXml.buildUserAliasOrder(tingcoEv, uao, timeZoneGMToffset);
                        } else {
                            tingcoEv.getMsgStatus().setResponseCode(-1);
                            tingcoEv.getMsgStatus().setResponseText("UserKeyOrder not found");
                            return tingcoEv;
                        }
                    } else {
                        tingcoEv.getMsgStatus().setResponseCode(-10);
                        tingcoEv.getMsgStatus().setResponseText("User Timezone not found");
                        return tingcoEv;
                    }
                } else {
                    tingcoEv.getMsgStatus().setResponseCode(-10);
                    tingcoEv.getMsgStatus().setResponseText("User Permission not given");
                    return tingcoEv;
                }
            } else {
                tingcoEv.getMsgStatus().setResponseCode(-3);
                tingcoEv.getMsgStatus().setResponseText("User session not valid");
                return tingcoEv;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoEv.getMsgStatus().setResponseCode(-1);
            tingcoEv.getMsgStatus().setResponseText("Error occurred");
            return tingcoEv;
        } finally {
            session.close();
            delayLog(requestedTime);
        }
        return tingcoEv;
    }

    private TingcoEV updateUserAliasIdDetails(String userAliasId, String firstName, String lastName, int enabled) {
        boolean hasPermission = false;
        long requestedTime = System.currentTimeMillis();
        TingcoEV loggedInUserID = new TingcoEV();
        TingcoUserXML userXML = new TingcoUserXML();
        Session session = null;
        try {
            loggedInUserID = userXML.buildTingcoUserTemplate();

            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sess = (UserSessions2) request.getAttribute("USERSESSION");
                //  Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) request.getSession(true).getAttribute(request.getSession(true).getId());
                // ht = TCMUtil.getFunctionAreas();
//            if (ht.containsKey(TCMUtil.USERALIAS)) {
//                ArrayList<String> operations = ht.get(TCMUtil.USERALIAS);
//                for (int i = 0; i < operations.size(); i++) {
//                    if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE) || operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                hasPermission = true;
//                    }
//                }
//            }
                if (userAliasId == null) {
                    loggedInUserID.getMsgStatus().setResponseCode(-1);
                    loggedInUserID.getMsgStatus().setResponseText("User Alias ID should not be empty");
                    return loggedInUserID;
                } else if (firstName == null) {
                    loggedInUserID.getMsgStatus().setResponseCode(-1);
                    loggedInUserID.getMsgStatus().setResponseText("First Name should not be empty");
                    return loggedInUserID;
                } else if (lastName == null) {
                    loggedInUserID.getMsgStatus().setResponseCode(-1);
                    loggedInUserID.getMsgStatus().setResponseText("Last Name should not be empty");
                    return loggedInUserID;
                } else {
                    if (hasPermission == true) {
                        UserDAO userDao = new UserDAO();
                        session = HibernateUtil.getSessionFactory().openSession();
                        boolean result = userDao.updateUserAliasDetailsByUserAliasId(session, userAliasId, firstName, lastName, enabled, sess.getUserId());
                        if (!result) {
                            loggedInUserID.getMsgStatus().setResponseCode(-1);
                            loggedInUserID.getMsgStatus().setResponseText("Data Not Updated");
                        }
                    } else {
                        loggedInUserID.getMsgStatus().setResponseCode(-10);
                        loggedInUserID.getMsgStatus().setResponseText("User Permission is not given");
//                    return loggedInUserID;
                    }
                }
                return loggedInUserID;
            } else {
                loggedInUserID.getMsgStatus().setResponseCode(-3);
                loggedInUserID.getMsgStatus().setResponseText("User Session is Not Valid");
                return loggedInUserID;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            loggedInUserID.getMsgStatus().setResponseCode(-3);
            loggedInUserID.getMsgStatus().setResponseText("Error Occured");
            return loggedInUserID;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }

    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}

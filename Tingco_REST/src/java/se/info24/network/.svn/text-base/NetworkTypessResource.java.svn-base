/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.network;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.hibernate.Session;
import se.info24.jaxbUtil.TingcoUtilsXML;
import se.info24.pojo.NetworkTypes;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.Users2;
import se.info24.user.UserDAO;
import se.info24.util.HibernateUtil;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;
import se.info24.utiljaxb.TingcoUtils;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/networktypes")
public class NetworkTypessResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoUtilsXML tuXML = new TingcoUtilsXML();
    NetworkDAO networkDAO = new NetworkDAO();
    UserDAO userDAO = new UserDAO();

    /** Creates a new instance of NetworkTypessResource */
    public NetworkTypessResource() {
    }

    /**
     * NetworkTypes_Add
     * @param networkTypeName
     * @param networkTypeDesc
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/add/rest/{rest}/networktypename/{networktypename}{networktypedesc:(/networktypedesc/[^/]+?)?}")
    @RESTDoc(methodName = "NetworkTypes_Add", desc = "Used to create a new NetworkType", req_Params = {"NetworkTypeName;String", "NetworkTypeDesc;String"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_Add(@PathParam("networktypename") String networkTypeName, @PathParam("networktypedesc") String networkTypeDesc) {
        return createNetworkType(networkTypeName, networkTypeDesc);
    }

    /**
     * NetworkTypes_Add
     * @param networkTypeName
     * @param networkTypeDesc
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/add/rest/{rest}/networktypename/{networktypename}{networktypedesc:(/networktypedesc/[^/]+?)?}")
    public TingcoUtils postXml(@PathParam("networktypename") String networkTypeName, @PathParam("networktypedesc") String networkTypeDesc) {
        return createNetworkType(networkTypeName, networkTypeDesc);
    }

    /**
     * NetworkTypes_Add
     * @param networkTypeName
     * @param networkTypeDesc
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/add/json/{json}/networktypename/{networktypename}{networktypedesc:(/networktypedesc/[^/]+?)?}")
    public TingcoUtils getJson(@PathParam("networktypename") String networkTypeName, @PathParam("networktypedesc") String networkTypeDesc) {
        return createNetworkType(networkTypeName, networkTypeDesc);
    }

    /**
     * NetworkTypes_Add
     * @param networkTypeName
     * @param networkTypeDesc
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/add/json/{json}/networktypename/{networktypename}{networktypedesc:(/networktypedesc/[^/]+?)?}")
    public TingcoUtils postJson(@PathParam("networktypename") String networkTypeName, @PathParam("networktypedesc") String networkTypeDesc) {
        return createNetworkType(networkTypeName, networkTypeDesc);
    }

    /**
     * NetworkTypes_Delete
     * @param networkTypeID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/networktypeid/{networktypeid}")
    @RESTDoc(methodName = "NetworkTypes_Delete", desc = "To Delete a NetworkType", req_Params = {"NetworkTypeID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_Delete(@PathParam("networktypeid") String networkTypeID) {
        return deleteNetworkTypes(networkTypeID);
    }

    /**
     * NetworkTypes_Delete
     * @param networkTypeID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/networktypeid/{networktypeid}")
    public TingcoUtils postXml_Delete(@PathParam("networktypeid") String networkTypeID) {
        return deleteNetworkTypes(networkTypeID);
    }

    /**
     * NetworkTypes_Delete
     * @param networkTypeID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/delete/json/{json}/networktypeid/{networktypeid}")
    public TingcoUtils getJson_Delete(@PathParam("networktypeid") String networkTypeID) {
        return deleteNetworkTypes(networkTypeID);
    }

    /**
     * NetworkTypes_Delete
     * @param networkTypeID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/delete/json/{json}/networktypeid/{networktypeid}")
    public TingcoUtils postJson_Delete(@PathParam("networktypeid") String networkTypeID) {
        return deleteNetworkTypes(networkTypeID);
    }

    /**
     * NetworkTypes_GetInfo
     * @param networkTypeID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/get/rest/{rest}/networktypeid/{networktypeid}")
    @RESTDoc(methodName = "NetworkTypes_GetInfo", desc = "To Get a NetworkTypes", req_Params = {"NetworkTypeID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml(@PathParam("networktypeid") String networkTypeID) {
        return getNetworkTypeInfo(networkTypeID);
    }

    /**
     * NetworkTypes_GetInfo
     * @param networkTypeID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/get/rest/{rest}/networktypeid/{networktypeid}")
    public TingcoUtils postXml(@PathParam("networktypeid") String networkTypeID) {
        return getNetworkTypeInfo(networkTypeID);
    }

    /**
     * NetworkTypes_GetInfo
     * @param networkTypeID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/get/json/{json}/networktypeid/{networktypeid}")
    public TingcoUtils getJSON(@PathParam("networktypeid") String networkTypeID) {
        return getNetworkTypeInfo(networkTypeID);
    }

    /**
     * NetworkTypes_GetInfo
     * @param networkTypeID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/get/json/{json}/networktypeid/{networktypeid}")
    public TingcoUtils postJSON(@PathParam("networktypeid") String networkTypeID) {
        return getNetworkTypeInfo(networkTypeID);
    }

    /**
     * NetworkTypes_Update
     * @param networkTypeID
     * @param networkTypeName
     * @param networkTypeDesc
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/update/rest/{rest}/networktypeid/{networktypeid}{networktypename:(/networktypename/[^/]+?)?}{networktypedesc:(/networktypedesc/[^/]+?)?}")
    @RESTDoc(methodName = "NetworkTypes_Update", desc = "To Update a NetworkTypes", req_Params = {"NetworkTypeID;UUID"}, opt_Params = {"NetworkTypeName;String", "NetworkTypeDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_Update(@PathParam("networktypeid") String networkTypeID, @PathParam("networktypename") String networkTypeName, @PathParam("networktypedesc") String networkTypeDesc) {
        return updateNetworkTypes(networkTypeID, networkTypeName, networkTypeDesc);
    }

    /**
     * NetworkTypes_Update
     * @param networkTypeID
     * @param networkTypeName
     * @param networkTypeDesc
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/update/rest/{rest}/networktypeid/{networktypeid}{networktypename:(/networktypename/[^/]+?)?}{networktypedesc:(/networktypedesc/[^/]+?)?}")
    public TingcoUtils postXml_Update(@PathParam("networktypeid") String networkTypeID, @PathParam("networktypename") String networkTypeName, @PathParam("networktypedesc") String networkTypeDesc) {
        return updateNetworkTypes(networkTypeID, networkTypeName, networkTypeDesc);
    }

    /**
     * NetworkTypes_Update
     * @param networkTypeID
     * @param networkTypeName
     * @param networkTypeDesc
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/update/json/{json}/networktypeid/{networktypeid}{networktypename:(/networktypename/[^/]+?)?}{networktypedesc:(/networktypedesc/[^/]+?)?}")
    public TingcoUtils getJson_Update(@PathParam("networktypeid") String networkTypeID, @PathParam("networktypename") String networkTypeName, @PathParam("networktypedesc") String networkTypeDesc) {
        return updateNetworkTypes(networkTypeID, networkTypeName, networkTypeDesc);
    }

    /**
     * NetworkTypes_Update
     * @param networkTypeID
     * @param networkTypeName
     * @param networkTypeDesc
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/update/json/{json}/networktypeid/{networktypeid}{networktypename:(/networktypename/[^/]+?)?}{networktypedesc:(/networktypedesc/[^/]+?)?}")
    public TingcoUtils postJson_Update(@PathParam("networktypeid") String networkTypeID, @PathParam("networktypename") String networkTypeName, @PathParam("networktypedesc") String networkTypeDesc) {
        return updateNetworkTypes(networkTypeID, networkTypeName, networkTypeDesc);
    }

    /**
     * GetAllNetworkTypes
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/rest/{rest}")
    @RESTDoc(methodName = "GetAllNetworkTypes", desc = "To Get All NetworkTypes", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_All() {
        return getAllNetworkTypes();
    }

    /**
     * GetAllNetworkTypes
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/rest/{rest}")
    public TingcoUtils postXml_All() {
        return getAllNetworkTypes();
    }

    /**
     * GetAllNetworkTypes
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/json/{json}")
    public TingcoUtils getJson_All() {
        return getAllNetworkTypes();
    }

    /**
     * GetAllNetworkTypes
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/json/{json}")
    public TingcoUtils postJson_All() {
        return getAllNetworkTypes();
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public NetworkTypesResource getNetworkTypesResource() {
        return new NetworkTypesResource();
    }

    private TingcoUtils createNetworkType(String networkTypeName, String networkTypeDesc) {
        long requestedTime = System.currentTimeMillis();
        TingcoUtils tingcoUtils = null;
        Session session = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (networkTypeName == null) {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("NetworkTypeName is should not be empty");
                    return tingcoUtils;
                }
                if (networkTypeDesc.equals("")) {
                    networkTypeDesc = null;
                } else {
                    networkTypeDesc = networkTypeDesc.split("/")[2];
                }
                UserSessions2 userSessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                NetworkTypes nwt = new NetworkTypes();
                nwt.setNetworkTypeId(UUID.randomUUID().toString());
                nwt.setNetworkTypeName(networkTypeName);
                if (networkTypeDesc != null) {
                    nwt.setNetworkTypeDescription(networkTypeDesc);
                }
                GregorianCalendar gc = new GregorianCalendar();
                nwt.setUpdatedDate(gc.getTime());
                nwt.setCreatedDate(gc.getTime());
                nwt.setUserId(userSessions2.getUserId());
                session = HibernateUtil.getSessionFactory().openSession();
                if (networkDAO.saveNetworkTypes(nwt, session)) {
                    return tingcoUtils;
                } else {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("Error Occured while Saving NetworkTypes");
                    return tingcoUtils;
                }
            } else {
                tingcoUtils.getMsgStatus().setResponseCode(-3);
                tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUtils;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUtils.getMsgStatus().setResponseCode(-1);
            tingcoUtils.getMsgStatus().setResponseText("Error Occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUtils;
    }

    private TingcoUtils deleteNetworkTypes(String networkTypeID) {
        long requestedTime = System.currentTimeMillis();
        TingcoUtils tingcoUtils = null;
        Session session = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (networkTypeID == null) {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("NetworkTypeID is should not be empty");
                    return tingcoUtils;
                }
                session = HibernateUtil.getSessionFactory().openSession();
                NetworkTypes nwt = networkDAO.getNetworkTypesById(networkTypeID, session);
                if (nwt != null) {
                    if (networkDAO.removeNetworkTypes(nwt, session)) {
                        return tingcoUtils;
                    } else {
                        tingcoUtils.getMsgStatus().setResponseCode(-1);
                        tingcoUtils.getMsgStatus().setResponseText("Error Occured while Deleting NetworkTypes");
                        return tingcoUtils;
                    }
                } else {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("NetworkTypeID Not Found");
                    return tingcoUtils;
                }

            } else {
                tingcoUtils.getMsgStatus().setResponseCode(-3);
                tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUtils;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUtils.getMsgStatus().setResponseCode(-1);
            tingcoUtils.getMsgStatus().setResponseText("Error Occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUtils;
    }

    private TingcoUtils getNetworkTypeInfo(String networkTypeID) {
        long requestedTime = System.currentTimeMillis();
        TingcoUtils tingcoUtils = null;
        Session session = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (networkTypeID == null) {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("NetworkTypeID is should not be empty");
                    return tingcoUtils;
                }
                session = HibernateUtil.getSessionFactory().openSession();
                NetworkTypes nwt = networkDAO.getNetworkTypesById(networkTypeID, session);
                if (nwt != null) {
                    ArrayList<NetworkTypes> typesList = new ArrayList<NetworkTypes>();
                    typesList.add(nwt);
                    Users2 user2 = userDAO.getUserById(nwt.getUserId(), session);
                    HashMap<String, Users2> userMap = new HashMap<String, Users2>();
                    userMap.put(nwt.getUserId(), user2);
                    tingcoUtils = tuXML.buildNetworkTypes(tingcoUtils, typesList, userMap);
                } else {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("No DeviceTypes Found with Given DeviceTypeID");
                }
            } else {
                tingcoUtils.getMsgStatus().setResponseCode(-3);
                tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUtils;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUtils.getMsgStatus().setResponseCode(-1);
            tingcoUtils.getMsgStatus().setResponseText("Error Occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUtils;
    }

    private TingcoUtils updateNetworkTypes(String networkTypeID, String networkTypeName, String networkTypeDesc) {
        long requestedTime = System.currentTimeMillis();
        TingcoUtils tingcoUtils = null;
        Session session = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (networkTypeID == null) {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("NetworkTypeID is should not be empty");
                    return tingcoUtils;
                }

                if (networkTypeName.equals("")) {
                    networkTypeName = null;
                } else {
                    networkTypeName = networkTypeName.split("/")[2];
                }

                if (networkTypeDesc.equals("")) {
                    networkTypeDesc = null;
                } else {
                    networkTypeDesc = networkTypeDesc.split("/")[2];
                }

                if (networkTypeName == null && networkTypeDesc == null) {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("Atleast One Value Should Be Present");
                    return tingcoUtils;
                }

                session = HibernateUtil.getSessionFactory().openSession();
                NetworkTypes nwt = networkDAO.getNetworkTypesById(networkTypeID, session);

                if (nwt != null) {
                    UserSessions2 userSessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                    nwt.setUserId(userSessions2.getUserId());
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    nwt.setUpdatedDate(gc.getTime());
                    if (networkTypeName != null) {
                        nwt.setNetworkTypeName(networkTypeName);
                    }
                    if (networkTypeDesc != null) {
                        nwt.setNetworkTypeDescription(networkTypeDesc);
                    }
                    if (networkDAO.saveNetworkTypes(nwt, session)) {
                        return tingcoUtils;
                    } else {
                        tingcoUtils.getMsgStatus().setResponseCode(-1);
                        tingcoUtils.getMsgStatus().setResponseText("Error Occured while Saving NetworkTypes");
                        return tingcoUtils;
                    }
                } else {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("NetworkTypeID Not Found");
                    return tingcoUtils;
                }
            } else {
                tingcoUtils.getMsgStatus().setResponseCode(-3);
                tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUtils;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUtils.getMsgStatus().setResponseCode(-1);
            tingcoUtils.getMsgStatus().setResponseText("Error Occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUtils;
    }

    private TingcoUtils getAllNetworkTypes() {
        long requestedTime = System.currentTimeMillis();
        TingcoUtils tingcoUtils = null;
        Session session = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                session = HibernateUtil.getSessionFactory().openSession();
                List<NetworkTypes> nwtList = networkDAO.getAllDeviceTypes(session);
                if (nwtList != null) {
                    HashMap<String, Users2> userMap = new HashMap<String, Users2>();
                    for (NetworkTypes nwt : nwtList) {
                        if (!userMap.containsKey(nwt.getUserId())) {
                            Users2 user2 = userDAO.getUserById(nwt.getUserId(), session);
                            userMap.put(nwt.getUserId(), user2);
                        }
                    }
                    tingcoUtils = tuXML.buildNetworkTypes(tingcoUtils, nwtList, userMap);
                    return tingcoUtils;
                } else {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("Error Occured while Loading the NetworkTypes");
                    return tingcoUtils;
                }
            } else {
                tingcoUtils.getMsgStatus().setResponseCode(-3);
                tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUtils;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUtils.getMsgStatus().setResponseCode(-1);
            tingcoUtils.getMsgStatus().setResponseText("Error Occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUtils;
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}

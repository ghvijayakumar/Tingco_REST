/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.service;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
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
import javax.xml.datatype.DatatypeConfigurationException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.device.DeviceDAO;
import se.info24.group.GroupDAO;
import se.info24.jaxbUtil.TingcoServiceXML;
import se.info24.pojo.Channels;
import se.info24.pojo.Groups;
import se.info24.pojo.ServicesChannels;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.Users2;
import se.info24.servicejaxb.TingcoService;
import se.info24.user.UserDAO;
import se.info24.user.User_LoginsResource;
import se.info24.util.HibernateUtil;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;
import sun.util.BuddhistCalendar;

/**
 * REST Web Service
 *
 * @author Sekhar
 */
@Path("/channels")
public class ChannelsResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    ServiceDAO serviceDAO = new ServiceDAO();
    DeviceDAO deviceDAO = new DeviceDAO();
    TingcoServiceXML tingcoServiceXML = new TingcoServiceXML();
    UserDAO userDAO = new UserDAO();
    GroupDAO groupDAO = new GroupDAO();
//    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of ChannelsResource */
    public ChannelsResource() {
    }

    /**
     * Channel_Add
     * @param channelName
     * @param channelData
     * @param groupId
     * @param channelDesc
     * @param channelEnabled
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/add/rest/{rest}/channelname/{channelname}/channeldata/{channeldata}/groupid/{groupid}{channeldesc:(/channeldesc/[^/]+?)?}{channelenabled:(/channelenabled/[^/]+?)?}")
    @RESTDoc(methodName = "Channel_Add", desc = "Used to create a new Channel", req_Params = {"ChannelName;String", "ChannelData;String", "GroupID;UUID"}, opt_Params = {"ChannelDesc;String", "ChannelEnabled;int"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoService getXml_Add(@PathParam("channelname") String channelName, @PathParam("channeldata") String channelData, @PathParam("groupid") String groupId, @PathParam("channeldesc") String channelDesc, @PathParam("channelenabled") String channelEnabled) {
        return createChannel(channelName, channelData, groupId, channelDesc, channelEnabled);
    }

    /**
     * Channel_Add
     * @param channelName
     * @param channelData
     * @param groupId
     * @param channelDesc
     * @param channelEnabled
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/add/json/{json}/channelname/{channelname}/channeldata/{channeldata}/groupid/{groupid}{channeldesc:(/channeldesc/[^/]+?)?}{channelenabled:(/channelenabled/[^/]+?)?}")
    public TingcoService getJson_Add(@PathParam("channelname") String channelName, @PathParam("channeldata") String channelData, @PathParam("groupid") String groupId, @PathParam("channeldesc") String channelDesc, @PathParam("channelenabled") String channelEnabled) {
        return createChannel(channelName, channelData, groupId, channelDesc, channelEnabled);
    }

    /**
     * Channel_Add
     * @param channelName
     * @param channelData
     * @param groupId
     * @param channelDesc
     * @param channelEnabled
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/add/rest/{rest}/channelname/{channelname}/channeldata/{channeldata}/groupid/{groupid}{channeldesc:(/channeldesc/[^/]+?)?}{channelenabled:(/channelenabled/[^/]+?)?}")
    public TingcoService postXml_Add(@PathParam("channelname") String channelName, @PathParam("channeldata") String channelData, @PathParam("groupid") String groupId, @PathParam("channeldesc") String channelDesc, @PathParam("channelenabled") String channelEnabled) {
        return createChannel(channelName, channelData, groupId, channelDesc, channelEnabled);
    }

    /**
     * Channel_Add
     * @param channelName
     * @param channelData
     * @param groupId
     * @param channelDesc
     * @param channelEnabled
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/add/json/{json}/channelname/{channelname}/channeldata/{channeldata}/groupid/{groupid}{channeldesc:(/channeldesc/[^/]+?)?}{channelenabled:(/channelenabled/[^/]+?)?}")
    public TingcoService postJson_Add(@PathParam("channelname") String channelName, @PathParam("channeldata") String channelData, @PathParam("groupid") String groupId, @PathParam("channeldesc") String channelDesc, @PathParam("channelenabled") String channelEnabled) {
        return createChannel(channelName, channelData, groupId, channelDesc, channelEnabled);
    }

    /**
     * Channel_Delete
     * @param channelID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/channelid/{channelid}")
    @RESTDoc(methodName = "Channel_Delete", desc = "To Delete a Channel", req_Params = {"ChannelID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoService getXml_Delete(@PathParam("channelid") String channelID) {
        return deleteChannel(channelID);
    }

    /**
     * Channel_Delete
     * @param channelID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/delete/json/{json}/channelid/{channelid}")
    public TingcoService getJson_Delete(@PathParam("channelid") String channelID) {
        return deleteChannel(channelID);
    }

    /**
     * Channel_Delete
     * @param channelID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/channelid/{channelid}")
    public TingcoService postXml_Delete(@PathParam("channelid") String channelID) {
        return deleteChannel(channelID);
    }

    /**
     * Channel_Delete
     * @param channelID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/delete/json/{json}/channelid/{channelid}")
    public TingcoService postJson_Delete(@PathParam("channelid") String channelID) {
        return deleteChannel(channelID);
    }

    /**
     * Channel_GetInfo
     * @param channelID
     * @param countryID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/get/rest/{rest}/channelid/{channelid}/countryid/{countryid}")
    @RESTDoc(methodName = "Channel_GetInfo", desc = "To Get a Channel", req_Params = {"ChannelID;UUID", "CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoService getXml(@PathParam("channelid") String channelID, @PathParam("countryid") int countryID) {
        return getChannelInfo(channelID, countryID);
    }

    /**
     * Channel_GetInfo
     * @param channelID
     * @param countryID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/get/json/{json}/channelid/{channelid}/countryid/{countryid}")
    public TingcoService getJson(@PathParam("channelid") String channelID, @PathParam("countryid") int countryID) {
        return getChannelInfo(channelID, countryID);
    }

    /**
     * Channel_GetInfo
     * @param channelID
     * @param countryID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/get/rest/{rest}/channelid/{channelid}/countryid/{countryid}")
    public TingcoService postXml(@PathParam("channelid") String channelID, @PathParam("countryid") int countryID) {
        return getChannelInfo(channelID, countryID);
    }

    /**
     * Channel_GetInfo
     * @param channelID
     * @param countryID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/get/json/{json}/channelid/{channelid}/countryid/{countryid}")
    public TingcoService postJson(@PathParam("channelid") String channelID, @PathParam("countryid") int countryID) {
        return getChannelInfo(channelID, countryID);
    }

    /**
     * Channel_Update
     * @param channelID
     * @param channelName
     * @param channelData
     * @param groupID
     * @param channelDesc
     * @param channelEnabled
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/update/rest/{rest}/channelid/{channelid}{channelname:(/channelname/[^/]+?)?}{channeldata:(/channeldata/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}{channeldesc:(/channeldesc/[^/]+?)?}{channelenabled:(/channelenabled/[^/]+?)?}")
    @RESTDoc(methodName = "Channel_Update", desc = "Used to update the channel", req_Params = {"ChannelID;UUID"}, opt_Params = {"ChannelName;String", "ChannelData;String", "GroupID;UUID", "ChannelDesc;String", "ChannelEnabled;int"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoService getXml_Update(@PathParam("channelid") String channelID, @PathParam("channelname") String channelName, @PathParam("channeldata") String channelData, @PathParam("groupid") String groupID, @PathParam("channeldesc") String channelDesc, @PathParam("channelenabled") String channelEnabled) {
        return updateChannel(channelID, channelName, channelData, groupID, channelDesc, channelEnabled);
    }

    /**
     * Channel_Update
     * @param channelID
     * @param channelName
     * @param channelData
     * @param groupID
     * @param channelDesc
     * @param channelEnabled
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/update/json/{json}/channelid/{channelid}{channelname:(/channelname/[^/]+?)?}{channeldata:(/channeldata/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}{channeldesc:(/channeldesc/[^/]+?)?}{channelenabled:(/channelenabled/[^/]+?)?}")
    public TingcoService getJson_Update(@PathParam("channelid") String channelID, @PathParam("channelname") String channelName, @PathParam("channeldata") String channelData, @PathParam("groupid") String groupID, @PathParam("channeldesc") String channelDesc, @PathParam("channelenabled") String channelEnabled) {
        return updateChannel(channelID, channelName, channelData, groupID, channelDesc, channelEnabled);
    }

    /**
     * Channel_Update
     * @param channelID
     * @param channelName
     * @param channelData
     * @param groupID
     * @param channelDesc
     * @param channelEnabled
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/update/rest/{rest}/channelid/{channelid}{channelname:(/channelname/[^/]+?)?}{channeldata:(/channeldata/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}{channeldesc:(/channeldesc/[^/]+?)?}{channelenabled:(/channelenabled/[^/]+?)?}")
    public TingcoService postXml_Update(@PathParam("channelid") String channelID, @PathParam("channelname") String channelName, @PathParam("channeldata") String channelData, @PathParam("groupid") String groupID, @PathParam("channeldesc") String channelDesc, @PathParam("channelenabled") String channelEnabled) {
        return updateChannel(channelID, channelName, channelData, groupID, channelDesc, channelEnabled);
    }

    /**
     * Channel_Update
     * @param channelID
     * @param channelName
     * @param channelData
     * @param groupID
     * @param channelDesc
     * @param channelEnabled
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/update/json/{json}/channelid/{channelid}{channelname:(/channelname/[^/]+?)?}{channeldata:(/channeldata/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}{channeldesc:(/channeldesc/[^/]+?)?}{channelenabled:(/channelenabled/[^/]+?)?}")
    public TingcoService postJson_Update(@PathParam("channelid") String channelID, @PathParam("channelname") String channelName, @PathParam("channeldata") String channelData, @PathParam("groupid") String groupID, @PathParam("channeldesc") String channelDesc, @PathParam("channelenabled") String channelEnabled) {
        return updateChannel(channelID, channelName, channelData, groupID, channelDesc, channelEnabled);
    }

    /**
     * GetAllChannels
     * @param countryID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetAllChannels", desc = "To Get All Channels", req_Params = {"CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoService getXml_All(@PathParam("countryid") int countryID) {
        return getAllChannels(countryID);
    }

    /**
     * GetAllChannels
     * @param countryID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/json/{json}/countryid/{countryid}")
    public TingcoService getJson_All(@PathParam("countryid") int countryID) {
        return getAllChannels(countryID);
    }

    /**
     * GetAllChannels
     * @param countryID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/rest/{rest}/countryid/{countryid}")
    public TingcoService postXml_All(@PathParam("countryid") int countryID) {
        return getAllChannels(countryID);
    }

    /**
     * AddNewChannelToService
     * @param serviceChannelId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getservicechanneldetailsbyid/rest/{rest}/servicechannelid/{servicechannelid}")
    @RESTDoc(methodName = "AddNewChannelToService", desc = "Used to Add New Channel To Service", req_Params = {"servicechannelid;UUID"}, opt_Params = {"searchstring;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoService getXml_getServiceChannelDetailsById(@PathParam("servicechannelid") String serviceChannelId) throws DatatypeConfigurationException {
        return getServiceChannelDetailsById(serviceChannelId);
    }

    /**
     * AddNewChannelToService
     * @param serviceChannelId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getservicechanneldetailsbyid/json/{json}/servicechannelid/{servicechannelid}")
    @RESTDoc(methodName = "AddNewChannelToService", desc = "Used to Add New Channel To Service", req_Params = {"servicechannelid;UUID"}, opt_Params = {"searchstring;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoService getJson_getServiceChannelDetailsById(@PathParam("servicechannelid") String serviceChannelId) throws DatatypeConfigurationException {
        return getServiceChannelDetailsById(serviceChannelId);
    }

    /**
     * UpdateServiceChannelDetailsById
     * @param serviceChannelId
     * @param channelDirection
     * @param channelTag
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/updateservicechanneldetailsbyid/rest/{rest}/servicechannelid/{servicechannelid}/channeldirection/{channeldirection}{channeltag:(/channeltag/[^/]+?)?}")
    @RESTDoc(methodName = "UpdateServiceChannelDetailsById", desc = "Update ServiceChannel Details By Id", req_Params = {"servicechannelid;UUID"}, opt_Params = {"searchstring;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoService getXml_UpdateServiceChannelDetailsById(@PathParam("servicechannelid") String serviceChannelId, @PathParam("channeldirection") String channelDirection, @PathParam("channeltag") String channelTag) throws DatatypeConfigurationException {
        return updateServiceChannelDetailsById(serviceChannelId, channelDirection, channelTag);
    }

    /**
     * UpdateServiceChannelDetailsById
     * @param serviceChannelId
     * @param channelDirection
     * @param channelTag
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/updateservicechanneldetailsbyid/json/{json}/servicechannelid/{servicechannelid}/channeldirection/{channeldirection}{channeltag:(/channeltag/[^/]+?)?}")
    @RESTDoc(methodName = "UpdateServiceChannelDetailsById", desc = "Update ServiceChannel Details By Id", req_Params = {"servicechannelid;UUID"}, opt_Params = {"searchstring;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoService getJson_UpdateServiceChannelDetailsById(@PathParam("servicechannelid") String serviceChannelId, @PathParam("channeldirection") String channelDirection, @PathParam("channeltag") String channelTag) throws DatatypeConfigurationException {
        return updateServiceChannelDetailsById(serviceChannelId, channelDirection, channelTag);
    }

    /**
     * DeleteServiceChannelDetailsById
     * @param serviceChannelId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/deleteservicechanneldetailsbyid/rest/{rest}/servicechannelid/{servicechannelid}")
    @RESTDoc(methodName = "DeleteServiceChannelDetailsById", desc = "DeleteServiceChannelDetailsById", req_Params = {"servicechannelid;UUID"}, opt_Params = {"searchstring;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoService getXml_DeleteServiceChannelDetailsById(@PathParam("servicechannelid") String serviceChannelId) throws DatatypeConfigurationException {
        return deleteServiceChannelDetailsById(serviceChannelId);
    }

    /**
     * DeleteServiceChannelDetailsById
     * @param serviceChannelId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/deleteservicechanneldetailsbyid/json/{json}/servicechannelid/{servicechannelid}")
    @RESTDoc(methodName = "DeleteServiceChannelDetailsById", desc = "DeleteServiceChannelDetailsById", req_Params = {"servicechannelid;UUID"}, opt_Params = {"searchstring;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoService getjson_DeleteServiceChannelDetailsById(@PathParam("servicechannelid") String serviceChannelId) throws DatatypeConfigurationException {
        return deleteServiceChannelDetailsById(serviceChannelId);
    }

    /**
     * GetAllChannels
     * @param countryID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/json/{json}/countryid/{countryid}")
    public TingcoService postJson_All(@PathParam("countryid") int countryID) {
        return getAllChannels(countryID);
    }

    private TingcoService deleteServiceChannelDetailsById(String serviceChannelId) {
        Session session = null;
        TingcoService tingcoService = new TingcoService();
        boolean hasPermission = false;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoService = tingcoServiceXML.buildTingcoServiceTemplate();
            if (request.getAttribute("USERSESSION") != null) {

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.SERVICES, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ServicesChannels sch = serviceDAO.getServicesChannelsById(session, Integer.valueOf(serviceChannelId));
                    if (sch == null) {
                        tingcoService.getMsgStatus().setResponseCode(-1);
                        tingcoService.getMsgStatus().setResponseText("Data Not found");
                        return tingcoService;
                    }

                    if (!serviceDAO.deleteServiceChannelDetailsById(sch, session)) {
                        tingcoService.getMsgStatus().setResponseCode(-1);
                        tingcoService.getMsgStatus().setResponseText("Error occured while adding");
                        return tingcoService;
                    }
                    return tingcoService;
                } else {
                    tingcoService.getMsgStatus().setResponseCode(-10);
                    tingcoService.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoService;
                }
            } else {
                tingcoService.getMsgStatus().setResponseCode(-3);
                tingcoService.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoService;
            }
        } catch (Exception ex) {

            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoService.getMsgStatus().setResponseCode(-1);
            tingcoService.getMsgStatus().setResponseText("Error occured");
            return tingcoService;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoService updateServiceChannelDetailsById(String serviceChannelId, String channelDirection, String channelTag) {
        Session session = null;
        TingcoService tingcoService = new TingcoService();
        boolean hasPermission = false;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoService = tingcoServiceXML.buildTingcoServiceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (!channelTag.equals("")) {
                    channelTag = channelTag.split("/")[2];
                } else {
                    channelTag = null;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.SERVICES, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ServicesChannels sch = serviceDAO.getServicesChannelsByIdNative(session, Integer.valueOf(serviceChannelId));
                    if (sch == null) {
                        tingcoService.getMsgStatus().setResponseCode(-1);
                        tingcoService.getMsgStatus().setResponseText("Data Not found");
                        return tingcoService;
                    }
                    sch.setChannelDirection(Integer.valueOf(channelDirection));
                    if (channelTag != null) {
                        sch.setChannelTag(channelTag);
                    }
                    sch.setUserId(sessions2.getUserId());
                    GregorianCalendar gc = new GregorianCalendar();
                    sch.setUpdatedDate(gc.getTime());
                    if (!serviceDAO.updateChannelToService(sch, session)) {
                        tingcoService.getMsgStatus().setResponseCode(-1);
                        tingcoService.getMsgStatus().setResponseText("Error occured while adding");
                        return tingcoService;
                    }
                    return tingcoService;
                } else {
                    tingcoService.getMsgStatus().setResponseCode(-10);
                    tingcoService.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoService;
                }
            } else {
                tingcoService.getMsgStatus().setResponseCode(-3);
                tingcoService.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoService;
            }
        } catch (Exception ex) {

            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoService.getMsgStatus().setResponseCode(-1);
            tingcoService.getMsgStatus().setResponseText("Error occured");
            return tingcoService;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoService getServiceChannelDetailsById(String serviceChannelId) {
        Session session = null;
        TingcoService tingcoService = new TingcoService();
        boolean hasPermission = false;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoService = tingcoServiceXML.buildTingcoServiceTemplate();
            if (request.getAttribute("USERSESSION") != null) {

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.SERVICES, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ServicesChannels sch = serviceDAO.getServicesChannelsById(session, Integer.valueOf(serviceChannelId));
                    if (sch == null) {
                        tingcoService.getMsgStatus().setResponseCode(-1);
                        tingcoService.getMsgStatus().setResponseText("Data Not found");
                        return tingcoService;
                    }
                    return tingcoServiceXML.buildGetServiceChannelDetailsById(tingcoService, sch);
                } else {
                    tingcoService.getMsgStatus().setResponseCode(-10);
                    tingcoService.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoService;
                }
            } else {
                tingcoService.getMsgStatus().setResponseCode(-3);
                tingcoService.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoService;
            }
        } catch (Exception ex) {

            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoService.getMsgStatus().setResponseCode(-1);
            tingcoService.getMsgStatus().setResponseText("Error occured");
            return tingcoService;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoService createChannel(String channelName, String channelData, String groupId, String channelDesc, String channelEnabled) {
        TingcoService tingcoService = null;
        try {
            tingcoService = tingcoServiceXML.buildTingcoServiceTemplate();
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        long requestedTime = System.currentTimeMillis();
        if (request.getAttribute("USERSESSION") != null) {
            if (channelName == null) {
                tingcoService.getMsgStatus().setResponseCode(-1);
                tingcoService.getMsgStatus().setResponseText("ChannelName is should not be empty");
                return tingcoService;
            } else if (channelData == null) {
                tingcoService.getMsgStatus().setResponseCode(-1);
                tingcoService.getMsgStatus().setResponseText("ChannelData is should not be empty");
                return tingcoService;
            } else if (groupId == null) {
                tingcoService.getMsgStatus().setResponseCode(-1);
                tingcoService.getMsgStatus().setResponseText("GroupID is should not be empty");
                return tingcoService;
            }

            if (channelDesc.equals("")) {
                channelDesc = null;
            } else {
                channelDesc = channelDesc.split("/")[2];
            }

            if (channelEnabled.equals("")) {
                channelEnabled = null;
            } else {
                channelEnabled = channelEnabled.split("/")[2];
            }

            UserSessions2 userSessions2 = (UserSessions2) request.getAttribute("USERSESSION");
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                GregorianCalendar gc = new GregorianCalendar();
                Channels channel = new Channels();
                channel.setChannelId(UUID.randomUUID().toString());
                channel.setChannelName(channelName);
                channel.setChannelData(channelData);
                channel.setGroups(new Groups(groupId));

                if (channelDesc != null) {
                    channel.setChannelDescription(channelDesc);
                }
                if (channelEnabled != null) {
                    channel.setChannelEnabled(Integer.valueOf(channelEnabled));
                } else {
                    channel.setChannelEnabled(0);
                }
                channel.setUserId(userSessions2.getUserId());
                channel.setCreatedDate(gc.getTime());
                channel.setUpdatedDate(gc.getTime());

                if (serviceDAO.saveChannels(channel, session)) {
                    return tingcoService;
                } else {
                    tingcoService.getMsgStatus().setResponseCode(-1);
                    tingcoService.getMsgStatus().setResponseText("Eroor Occured While Saving the Channel");
                    return tingcoService;
                }
            } catch (Exception e) {
                TCMUtil.exceptionLog(getClass().getName(), e);
                tingcoService.getMsgStatus().setResponseCode(-1);
                tingcoService.getMsgStatus().setResponseText("Error Occured While Saving Channels");
                return tingcoService;
            } finally {
                if (session != null) {
                    session.close();
                }
                delayLog(requestedTime);
            }

        } else {
            tingcoService.getMsgStatus().setResponseCode(-3);
            tingcoService.getMsgStatus().setResponseText("User Session is Not Valid");
            return tingcoService;
        }
    }

    private TingcoService deleteChannel(String channelId) {
        TingcoService tingcoService = null;
        try {
            tingcoService = tingcoServiceXML.buildTingcoServiceTemplate();
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        long requestedTime = System.currentTimeMillis();
        if (request.getAttribute("USERSESSION") != null) {
            if (channelId == null) {
                tingcoService.getMsgStatus().setResponseCode(-1);
                tingcoService.getMsgStatus().setResponseText("ChannelID is should not be empty");
                return tingcoService;
            }
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                Channels channels = deviceDAO.getChannels(channelId, session);
                if (channels != null) {
                    if (serviceDAO.deleteChannel(channels, session)) {
                        return tingcoService;
                    } else {
                        tingcoService.getMsgStatus().setResponseCode(-1);
                        tingcoService.getMsgStatus().setResponseText("Eroor Occured While Deleting the Channel");
                        return tingcoService;
                    }
                } else {
                    tingcoService.getMsgStatus().setResponseCode(-1);
                    tingcoService.getMsgStatus().setResponseText("ChannelID Not Found");
                    return tingcoService;
                }
            } catch (Exception e) {
                TCMUtil.exceptionLog(getClass().getName(), e);
                tingcoService.getMsgStatus().setResponseCode(-1);
                tingcoService.getMsgStatus().setResponseText("Eroor Occured While Deleting the Channel");
                return tingcoService;
            } finally {
                session.close();
                delayLog(requestedTime);
            }
        } else {
            tingcoService.getMsgStatus().setResponseCode(-3);
            tingcoService.getMsgStatus().setResponseText("User Session is Not Valid");
            return tingcoService;
        }
    }

    private TingcoService getAllChannels(int countryId) {
        TingcoService tingcoService = null;
        try {
            tingcoService = tingcoServiceXML.buildTingcoServiceTemplate();
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }

        long requestedTime = System.currentTimeMillis();
        if (request.getAttribute("USERSESSION") != null) {
            if (countryId <= 0) {
                tingcoService.getMsgStatus().setResponseCode(-1);
                tingcoService.getMsgStatus().setResponseText("CountryID is should not be empty");
                return tingcoService;
            }
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                List<Channels> channels = serviceDAO.getAllChannels(session);
                if (channels != null) {
                    HashMap<String, Users2> userMap = new HashMap<String, Users2>();
                    HashMap<String, Groups> groupMap = new HashMap<String, Groups>();

                    for (Channels channel : channels) {
                        if (!userMap.containsKey(channel.getUserId())) {
                            Users2 user2 = userDAO.getUserById(channel.getUserId(), session);
                            userMap.put(channel.getUserId(), user2);
                        }
                        if (!groupMap.containsKey(channel.getGroups().getGroupId())) {
                            Groups group = groupDAO.getGroupByGroupId(channel.getGroups().getGroupId(), session);
                            groupMap.put(group.getGroupId(), group);
                        }
                    }

                    tingcoService = tingcoServiceXML.buildChannelInfo(tingcoService, channels, userMap, groupMap, countryId);
                    return tingcoService;
                } else {
                    tingcoService.getMsgStatus().setResponseCode(-1);
                    tingcoService.getMsgStatus().setResponseText("ServiceTypeID Not Found");
                    return tingcoService;
                }
            } catch (Exception e) {
                TCMUtil.exceptionLog(getClass().getName(), e);
                tingcoService.getMsgStatus().setResponseCode(-1);
                tingcoService.getMsgStatus().setResponseText("Eroor Occured While Loading the ServiceType");
                return tingcoService;
            } finally {
                session.close();
                delayLog(requestedTime);
            }
        } else {
            tingcoService.getMsgStatus().setResponseCode(-3);
            tingcoService.getMsgStatus().setResponseText("User Session is Not Valid");
            return tingcoService;
        }
    }

    private TingcoService getChannelInfo(String channelID, int countryId) {
        TingcoService tingcoService = null;
        try {
            tingcoService = tingcoServiceXML.buildTingcoServiceTemplate();
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }

        long requestedTime = System.currentTimeMillis();
        if (request.getAttribute("USERSESSION") != null) {
            if (channelID == null) {
                tingcoService.getMsgStatus().setResponseCode(-1);
                tingcoService.getMsgStatus().setResponseText("ChannelID is should not be empty");
                return tingcoService;
            } else if (countryId <= 0) {
                tingcoService.getMsgStatus().setResponseCode(-1);
                tingcoService.getMsgStatus().setResponseText("CountryID is should not be empty");
                return tingcoService;
            }
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                Channels channels = deviceDAO.getChannels(channelID, session);
                if (channels != null) {
                    ArrayList<Channels> channelList = new ArrayList<Channels>();
                    channelList.add(channels);
                    Users2 user2 = userDAO.getUserById(channels.getUserId(), session);
                    HashMap<String, Users2> userMap = new HashMap<String, Users2>();
                    userMap.put(channels.getUserId(), user2);
                    HashMap<String, Groups> groupMap = new HashMap<String, Groups>();
                    Groups group = groupDAO.getGroupByGroupId(channels.getGroups().getGroupId(), session);
                    groupMap.put(group.getGroupId(), group);
                    tingcoService = tingcoServiceXML.buildChannelInfo(tingcoService, channelList, userMap, groupMap, countryId);
                    return tingcoService;
                } else {
                    tingcoService.getMsgStatus().setResponseCode(-1);
                    tingcoService.getMsgStatus().setResponseText("ServiceTypeID Not Found");
                    return tingcoService;
                }
            } catch (Exception e) {
                TCMUtil.exceptionLog(getClass().getName(), e);
                tingcoService.getMsgStatus().setResponseCode(-1);
                tingcoService.getMsgStatus().setResponseText("Eroor Occured While Loading the ServiceType");
                return tingcoService;
            } finally {
                session.close();
                delayLog(requestedTime);
            }
        } else {
            tingcoService.getMsgStatus().setResponseCode(-3);
            tingcoService.getMsgStatus().setResponseText("User Session is Not Valid");
            return tingcoService;
        }

    }

    private TingcoService updateChannel(String channelId, String channelName, String channelData, String groupId, String channelDesc, String channelEnabled) {
        TingcoService tingcoService = null;
        try {
            tingcoService = tingcoServiceXML.buildTingcoServiceTemplate();
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        long requestedTime = System.currentTimeMillis();
        if (request.getAttribute("USERSESSION") != null) {
            if (channelId == null) {
                tingcoService.getMsgStatus().setResponseCode(-1);
                tingcoService.getMsgStatus().setResponseText("ChannelID is should not be empty");
                return tingcoService;
            }
            if (channelName.equals("")) {
                channelName = null;
            } else {
                channelName = channelName.split("/")[2];
            }
            if (channelData.equals("")) {
                channelData = null;
            } else {
                channelData = channelData.split("/")[2];
            }
            if (groupId.equals("")) {
                groupId = null;
            } else {
                groupId = groupId.split("/")[2];
            }
            if (channelDesc.equals("")) {
                channelDesc = null;
            } else {
                channelDesc = channelDesc.split("/")[2];
            }
            if (channelEnabled.equals("")) {
                channelEnabled = null;
            } else {
                channelEnabled = channelEnabled.split("/")[2];
            }

            if (channelName == null && channelData == null && groupId == null && channelDesc == null && channelEnabled == null) {
                tingcoService.getMsgStatus().setResponseCode(-1);
                tingcoService.getMsgStatus().setResponseText("Alleast One Value Should Present");
                return tingcoService;
            }
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                Channels channels = deviceDAO.getChannels(channelId, session);
                if (channels != null) {
                    UserSessions2 userSessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));

                    if (channelName != null) {
                        channels.setChannelName(channelName);
                    }
                    if (channelData != null) {
                        channels.setChannelData(channelData);
                    }
                    if (groupId != null) {
                        channels.setGroups(new Groups(groupId));
                    }

                    if (channelDesc != null) {
                        channels.setChannelDescription(channelDesc);
                    }

                    if (channelEnabled != null) {
                        channels.setChannelEnabled(Integer.valueOf(channelEnabled));
                    }

                    channels.setUserId(userSessions2.getUserId());
                    channels.setUpdatedDate(gc.getTime());
                    if (serviceDAO.saveChannels(channels, session)) {
                        return tingcoService;
                    } else {
                        tingcoService.getMsgStatus().setResponseCode(-1);
                        tingcoService.getMsgStatus().setResponseText("Eroor Occured While Updating the Channel");
                        return tingcoService;
                    }

                } else {
                    tingcoService.getMsgStatus().setResponseCode(-1);
                    tingcoService.getMsgStatus().setResponseText("ChannelID Not Found");
                    return tingcoService;
                }
            } catch (Exception e) {
                TCMUtil.exceptionLog(getClass().getName(), e);
                tingcoService.getMsgStatus().setResponseCode(-1);
                tingcoService.getMsgStatus().setResponseText("Eroor Occured While Updating the Channel");
                return tingcoService;
            } finally {
                session.close();
                delayLog(requestedTime);
            }
        } else {
            tingcoService.getMsgStatus().setResponseCode(-3);
            tingcoService.getMsgStatus().setResponseText("User Session is Not Valid");
            return tingcoService;
        }
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public ChannelResource getChannel_AddResource() {
        return new ChannelResource();
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }

    private boolean getPermission(String userId, String functionarea, String operation) {
        boolean hasPermission = false;
        Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(userId);
        if (ht.containsKey(functionarea)) {
            ArrayList<String> operations = ht.get(functionarea);
            for (int i = 0; i < operations.size(); i++) {
                if (operations.get(i).equalsIgnoreCase(operation)) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }
}

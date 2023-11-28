/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.device;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.hibernate.Session;
import se.info24.devicejaxbPost.Device;
import se.info24.devicejaxbPost.DeviceMessage;
import se.info24.devicejaxbPost.TingcoDevice;
import se.info24.ismOperationsPojo.DeviceMessages;
import se.info24.jaxbUtil.TingcoDeviceXML;
import se.info24.pojo.UserSessions2;
import se.info24.util.HibernateUtil;
import se.info24.util.JAXBManager;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/devicemessage")
public class DeviceMessagesResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
    DeviceDAO deviceDAO = new DeviceDAO();
//    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of DeviceMessagesResource */
    public DeviceMessagesResource() {
    }

    /**
     * DeviceMessage_Delete
     * @param deviceMessageID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/devicemessageid/{devicemessageid}")
    @RESTDoc(methodName = "DeviceMessage_Delete", desc = "To Delete a DeviceMessage", req_Params = {"DeviceMessageID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public se.info24.devicejaxb.TingcoDevice getXml_Delete(@PathParam("devicemessageid") String deviceMessageID) {
        return deleteDeviceMessage(deviceMessageID);
    }

    /**
     * DeviceMessage_Delete
     * @param deviceMessageID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/devicemessageid/{devicemessageid}")
    public se.info24.devicejaxb.TingcoDevice postXml_Delete(@PathParam("devicemessageid") String deviceMessageID) {
        return deleteDeviceMessage(deviceMessageID);
    }

    /**
     * DeviceMessage_Delete
     * @param deviceMessageID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/delete/json/{json}/devicemessageid/{devicemessageid}")
    public se.info24.devicejaxb.TingcoDevice getJson_Delete(@PathParam("devicemessageid") String deviceMessageID) {
        return deleteDeviceMessage(deviceMessageID);
    }

    /**
     * DeviceMessage_Delete
     * @param deviceMessageID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/delete/json/{json}/devicemessageid/{devicemessageid}")
    public se.info24.devicejaxb.TingcoDevice postJson_Delete(@PathParam("devicemessageid") String deviceMessageID) {
        return deleteDeviceMessage(deviceMessageID);
    }

    /**
     * DeviceMessage_Send
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/send/rest/{rest}")
    @RESTDoc(methodName = "DeviceMessage_Send", desc = "To Add a DeviceMessage", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"POST"}, versions = {"1"})
    public se.info24.devicejaxb.TingcoDevice postXml_Send(String content) {
        return send(content);
    }

    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/send/json/{json}")
    public se.info24.devicejaxb.TingcoDevice postJson_Send(String content) {
        return send(content);
    }

    /**
     * DeviceMessages_Get
     * @param countryId
     * @param deviceId
     * @param groupId
     * @param deviceTypeID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/get/rest/{rest}/countryid/{countryid}{deviceid:(/deviceid/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}{devicetypeid:(/devicetypeid/[^/]+?)?}")
    @RESTDoc(methodName = "DeviceMessages_Get", desc = "To Get Device Messages By DeviceID or DeviceTypeID or GroupID", req_Params = {"CountryID;int"}, opt_Params = {"DeviceID;UUID", "GroupID;UUID", "DeviceTypeID;UUID"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public se.info24.devicejaxb.TingcoDevice getXml(@PathParam("countryid") int countryId, @PathParam("deviceid") String deviceId, @PathParam("groupid") String groupId, @PathParam("devicetypeid") String deviceTypeID) {
        return getDeviceMessages(countryId, deviceId, groupId, deviceTypeID);
    }

    /**
     * DeviceMessages_Get
     * @param countryId
     * @param deviceId
     * @param groupId
     * @param deviceTypeID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/get/rest/{rest}/countryid/{countryid}{deviceid:(/deviceid/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}{devicetypeid:(/devicetypeid/[^/]+?)?}")
    public se.info24.devicejaxb.TingcoDevice postXml(@PathParam("countryid") int countryId, @PathParam("deviceid") String deviceId, @PathParam("groupid") String groupId, @PathParam("devicetypeid") String deviceTypeID) {
        return getDeviceMessages(countryId, deviceId, groupId, deviceTypeID);
    }

    /**
     * DeviceMessages_Get
     * @param countryId
     * @param deviceId
     * @param groupId
     * @param deviceTypeID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/get/json/{json}/countryid/{countryid}{deviceid:(/deviceid/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}{devicetypeid:(/devicetypeid/[^/]+?)?}")
    public se.info24.devicejaxb.TingcoDevice getJson(@PathParam("countryid") int countryId, @PathParam("deviceid") String deviceId, @PathParam("groupid") String groupId, @PathParam("devicetypeid") String deviceTypeID) {
        return getDeviceMessages(countryId, deviceId, groupId, deviceTypeID);
    }

    /**
     * DeviceMessages_Get
     * @param countryId
     * @param deviceId
     * @param groupId
     * @param deviceTypeID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/get/json/{json}/countryid/{countryid}{deviceid:(/deviceid/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}{devicetypeid:(/devicetypeid/[^/]+?)?}")
    public se.info24.devicejaxb.TingcoDevice postJson(@PathParam("countryid") int countryId, @PathParam("deviceid") String deviceId, @PathParam("groupid") String groupId, @PathParam("devicetypeid") String deviceTypeID) {
        return getDeviceMessages(countryId, deviceId, groupId, deviceTypeID);
    }

    /**
     * GetAllDeviceMessages
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetAllDeviceMessages", desc = "To Get All Device Messages", req_Params = {"CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public se.info24.devicejaxb.TingcoDevice getXml_All(@PathParam("CountryID") int countryId) {
        return getAllDeviceMessages(countryId);
    }

    /**
     * GetAllDeviceMessages
     * @param countryId
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/rest/{rest}/countryid/{countryid}")
    public se.info24.devicejaxb.TingcoDevice postXml_All(@PathParam("CountryID") int countryId) {
        return getAllDeviceMessages(countryId);
    }

    /**
     * GetAllDeviceMessages
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/json/{json}/countryid/{countryid}")
    public se.info24.devicejaxb.TingcoDevice getJson_All(@PathParam("CountryID") int countryId) {
        return getAllDeviceMessages(countryId);
    }

    /**
     * GetAllDeviceMessages
     * @param countryId
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/json/{json}/countryid/{countryid}")
    public se.info24.devicejaxb.TingcoDevice postJson_All(@PathParam("CountryID") int countryId) {
        return getAllDeviceMessages(countryId);
    }

    private se.info24.devicejaxb.TingcoDevice getDeviceMessages(int countryId, String deviceId, String groupId, String deviceTypeId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        se.info24.devicejaxb.TingcoDevice tingcoDevice = null;
        Session ismOpr_session = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (countryId <= 0) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("CountryID Should Be not Null");
                    return tingcoDevice;
                }
                if (deviceId.equals("")) {
                    deviceId = null;
                } else {
                    deviceId = deviceId.split("/")[2];
                }

                if (groupId.equals("")) {
                    groupId = null;
                } else {
                    groupId = groupId.split("/")[2];
                }

                if (deviceTypeId.equals("")) {
                    deviceTypeId = null;
                } else {
                    deviceTypeId = deviceTypeId.split("/")[2];
                }

                if (deviceId == null && groupId == null && deviceTypeId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("Atleast One value has Present among DeviceID, GroupID and DeviceTypeID");
                    return tingcoDevice;
                }

//            Hashtable<String,ArrayList> ht = (Hashtable<String, ArrayList>) request.getSession(true).getAttribute(request.getSession(true).getId());
//            if (ht.containsKey(TCMUtil.DEVICE)) {
//                ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
//                for(int i=0;i<operations.size();i++){
//                    if(operations.get(i).equalsIgnoreCase(TCMUtil.READ)){
                hasPermission = true;
//                    }
//                }
//            }

                if (hasPermission) {
                    String qry = "from DeviceMessages dm where ";

                    if (deviceId != null && groupId != null && deviceTypeId != null) {
                        qry = qry + " dm.deviceId = '" + deviceId + "' and dm.groupId = '" + groupId + "' and dm.deviceTypeId = '" + deviceTypeId + "'";
                    } else if (deviceId != null && groupId != null && deviceTypeId == null) {
                        qry = qry + " dm.deviceId = '" + deviceId + "' and dm.groupId = '" + groupId + "'";
                    } else if (deviceId != null && groupId == null && deviceTypeId != null) {
                        qry = qry + " dm.deviceId = '" + deviceId + "' and dm.deviceTypeId = '" + deviceTypeId + "'";
                    } else if (deviceId == null && groupId != null && deviceTypeId != null) {
                        qry = qry + " dm.groupId = '" + groupId + "' and dm.deviceTypeId = '" + deviceTypeId + "'";
                    } else if (deviceId != null && groupId == null && deviceTypeId == null) {
                        qry = qry + " dm.deviceId = '" + deviceId + "'";
                    } else if (deviceId == null && groupId != null && deviceTypeId == null) {
                        qry = qry + " dm.groupId = '" + groupId + "'";
                    } else if (deviceId == null && groupId == null && deviceTypeId != null) {
                        qry = qry + " dm.deviceTypeId = '" + deviceTypeId + "'";
                    }
                    ismOpr_session = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<DeviceMessages> dMList = deviceDAO.getAllDeviceMessagesByQry(qry, ismOpr_session);
                    if (dMList != null) {
                        HashMap<String, se.info24.pojo.Device> deviceMap = new HashMap<String, se.info24.pojo.Device>();
                        HashMap<String, List<DeviceMessages>> sortMessages = new HashMap<String, List<DeviceMessages>>();
                        for (DeviceMessages dm : dMList) {
                            if (!deviceMap.containsKey(dm.getDeviceId())) {
                                deviceMap.put(dm.getDeviceId(), deviceDAO.getDeviceById(dm.getDeviceId(), session));
                            }
                            if (sortMessages.containsKey(dm.getDeviceId())) {
                                sortMessages.get(dm.getDeviceId()).add(dm);
                            } else {
                                ArrayList<DeviceMessages> list = new ArrayList<DeviceMessages>();
                                list.add(dm);
                                sortMessages.put(dm.getDeviceId(), list);
                            }
                        }
                        tingcoDevice = tingcoDeviceXML.buildDeviceMessages(tingcoDevice, sortMessages, deviceMap, countryId);
                        return tingcoDevice;
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No DeviceMessages Found");
                        return tingcoDevice;
                    }
                } else {
                    tingcoDevice.getMsgStatus().setResponseCode(-10);
                    tingcoDevice.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoDevice;
                }

            } else {
                tingcoDevice.getMsgStatus().setResponseCode(-3);
                tingcoDevice.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoDevice;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error Occured While Loading DeviceMessages");
            return tingcoDevice;
        } finally {
            if (ismOpr_session != null) {
                ismOpr_session.close();
            }
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private se.info24.devicejaxb.TingcoDevice deleteDeviceMessage(String deviceMessageId) {
         long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        se.info24.devicejaxb.TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceMessageId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceMessageID is should not be empty");
                    return tingcoDevice;
                }

//            Hashtable<String,ArrayList> ht = (Hashtable<String, ArrayList>) request.getSession(true).getAttribute(request.getSession(true).getId());
//            if (ht.containsKey(TCMUtil.DEVICE)) {
//                ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
//                for(int i=0;i<operations.size();i++){
//                    if(operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)){
                hasPermission = true;
//                    }
//                }
//            }

                if (hasPermission) {
                    session = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    DeviceMessages deviceMessages = deviceDAO.getDeviceMessagesById(deviceMessageId, session);

                    if (deviceMessages != null) {
                        if (deviceDAO.removeDeviceMessages(deviceMessages, session)) {
                            return tingcoDevice;
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error Occured while Deleting DeviceMessages");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("DeviceMessageID Not Found");
                        return tingcoDevice;
                    }
                } else {
                    tingcoDevice.getMsgStatus().setResponseCode(-10);
                    tingcoDevice.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoDevice;
                }
            } else {
                tingcoDevice.getMsgStatus().setResponseCode(-3);
                tingcoDevice.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoDevice;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private se.info24.devicejaxb.TingcoDevice getAllDeviceMessages(int countryId) {
         long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        se.info24.devicejaxb.TingcoDevice tingcoDevice = null;
        Session ismOpr_session = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {

//            Hashtable<String,ArrayList> ht = (Hashtable<String, ArrayList>) request.getSession(true).getAttribute(request.getSession(true).getId());
//            if (ht.containsKey(TCMUtil.DEVICE)) {
//                ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
//                for(int i=0;i<operations.size();i++){
//                    if(operations.get(i).equalsIgnoreCase(TCMUtil.READ)){
                hasPermission = true;
//                    }
//                }
//            }

                if (hasPermission) {
                    ismOpr_session = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<DeviceMessages> dMList = deviceDAO.getAllDeviceMessages(ismOpr_session);
                    if (dMList != null) {
                        HashMap<String, se.info24.pojo.Device> deviceMap = new HashMap<String, se.info24.pojo.Device>();
                        HashMap<String, List<DeviceMessages>> sortMessages = new HashMap<String, List<DeviceMessages>>();
                        for (DeviceMessages dm : dMList) {
                            if (!deviceMap.containsKey(dm.getDeviceId())) {
                                deviceMap.put(dm.getDeviceId(), deviceDAO.getDeviceById(dm.getDeviceId(), session));
                            }
                            if (sortMessages.containsKey(dm.getDeviceId())) {
                                sortMessages.get(dm.getDeviceId()).add(dm);
                            } else {
                                ArrayList<DeviceMessages> list = new ArrayList<DeviceMessages>();
                                list.add(dm);
                                sortMessages.put(dm.getDeviceId(), list);
                            }
                        }
                        tingcoDevice = tingcoDeviceXML.buildDeviceMessages(tingcoDevice, sortMessages, deviceMap, countryId);
                        return tingcoDevice;
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No DeviceMessages Found");
                        return tingcoDevice;
                    }
                } else {
                    tingcoDevice.getMsgStatus().setResponseCode(-10);
                    tingcoDevice.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoDevice;
                }
            } else {
                tingcoDevice.getMsgStatus().setResponseCode(-3);
                tingcoDevice.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoDevice;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public DeviceMessageResource getDeviceMessageResource() {
        return new DeviceMessageResource();
    }

    public se.info24.devicejaxb.TingcoDevice send(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        se.info24.devicejaxb.TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
//            Hashtable<String,ArrayList> ht = (Hashtable<String, ArrayList>) request.getSession(true).getAttribute(request.getSession(true).getId());
//            if (ht.containsKey(TCMUtil.DEVICE)) {
//                ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
//                for(int i=0;i<operations.size();i++){
//                    if(operations.get(i).equalsIgnoreCase(TCMUtil.SEND)){
                hasPermission = true;
//                    }
//                }
//            }

                if (hasPermission) {
                    TingcoDevice deviceXML = (TingcoDevice) JAXBManager.getInstance().unMarshall(content, TingcoDevice.class);
                    ArrayList<DeviceMessages> dmList = new ArrayList<DeviceMessages>();
                    UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                    session = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    DeviceMessages message = null;
                    for (Device dev : deviceXML.getDevices().getDevice()) {
                        for (DeviceMessage dm : dev.getDeviceMessages().getDeviceMessage()) {
                            message = new DeviceMessages();
                            message.setDeviceMessageId(UUID.randomUUID().toString());
                            if (dev.getDeviceID() != null) {
                                message.setDeviceId(dev.getDeviceID());
                            }
                            if (dev.getGroupID() != null) {
                                message.setGroupId(dev.getGroupID().getValue());
                            }
                            if (dev.getDeviceTypeID() != null) {
                                message.setDeviceTypeId(dev.getDeviceTypeID().getValue());
                            }
                            if (dm.getDataItemID() != null) {
                                message.setDataItemId(dm.getDataItemID());
                            }
                            if (dm.getSourceReferenceID() != null) {
                                message.setSourceReferenceId(dm.getSourceReferenceID());
                            }

                            if (dm.getMessageText() != null) {
                                message.setMessageText(dm.getMessageText());
                            }
                            message.setPriority(0);
                            message.setSendToDeviceNow(1);
                            message.setIsNew(1);
                            message.setIsSentToDevice(1);
                            message.setIsReceivedFromDevice(0);
                            message.setIsDeleted(0);
                            if (dm.getIsEmergency() != null) {
                                message.setIsEmergency(dm.getIsEmergency());
                            } else {
                                message.setIsEmergency(0);
                            }
                            if (dm.getIsEmergencyAck() != null) {
                                message.setIsEmergencyAck(dm.getIsEmergencyAck());
                            } else {
                                message.setIsEmergencyAck(0);
                            }

                            if (dm.getPosLatitude() != null) {
                                message.setPosLatitude(Double.valueOf(dm.getPosLatitude()));
                            }
                            if (dm.getPosLongitude() != null) {
                                message.setPosLongitude(Double.valueOf(dm.getPosLongitude()));
                            }
                            if (dm.getPosDepth() != null) {
                                message.setPosDepth(Double.valueOf(dm.getPosDepth()));
                            }
                            if (dm.getPosDirection() != null) {
                                message.setPosDirection(Integer.valueOf(dm.getPosDirection()));
                            }
                            if (dm.getDeviceStatusText() != null) {
                                message.setDeviceStatus(dm.getDeviceStatusText());
                            }
                            message.setCreatedByUserId(sessions2.getUserId());
                            message.setSentToDeviceDate(gc.getTime());
                            message.setCreatedDate(gc.getTime());
                            message.setUpdatedDate(gc.getTime());
                            if (message.getDeviceId() != null && message.getGroupId() != null && message.getMessageText() != null) {
                                dmList.add(message);
                            }
                        }
                    }
                    for (DeviceMessages dms : dmList) {
                        deviceDAO.saveDeviceMessages(dms, session);
                    }
                    return tingcoDevice;
                } else {
                    tingcoDevice.getMsgStatus().setResponseCode(-10);
                    tingcoDevice.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoDevice;
                }
            } else {
                tingcoDevice.getMsgStatus().setResponseCode(-3);
                tingcoDevice.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoDevice;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error Occured While Saving the DeviceMessage");
            return tingcoDevice;
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

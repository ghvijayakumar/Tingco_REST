/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.device;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.xml.datatype.DatatypeConfigurationException;
import org.hibernate.Session;
import se.info24.devicejaxb.TingcoDevice;
import se.info24.ismOperationsPojo.DeviceActivationSchedules;
import se.info24.jaxbUtil.TingcoDeviceXML;
import se.info24.pojo.Channels;
import se.info24.pojo.Device;
import se.info24.pojo.DeviceDataItems;
import se.info24.pojo.DeviceTypeChannels;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.Services;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.Users2;
import se.info24.protocols.em.EventMessageDocument;
import se.info24.service.ServiceDAO;
import se.info24.user.UserDAO;
import se.info24.user.User_LoginsResource;
import se.info24.util.HibernateUtil;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;
import se.info24.util.TingcoConstants;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/deviceactivationschedules")
public class DeviceActivationSchedulesResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    private TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
    private DeviceDAO deviceDAO = new DeviceDAO();
//    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of DeviceActivationSchedulesResource */
    public DeviceActivationSchedulesResource() {
    }

    /**
     * InsertDeviceActivationSchedules
     * @param deviceDataItemid
     * @param deviceId
     * @param userAlias
     * @param deviceTypeId
     * @param dataItem
     * @param dataItemValue
     * @param groupId
     * @param minutes
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/add/rest/{rest}/devicedataitemid/{devicedataitemid}/deviceid/{deviceid}/useralias/{useralias}/devicetypeid/{devicetypeid}/dataitem/{dataitem}/dataitemvalue/{dataitemvalue}/groupid/{groupid}/minutes/{minutes}/countryid/{countryid}")
    @RESTDoc(methodName = "InsertDeviceActivationSchedules", desc = "Insert data", req_Params = {"DeviceDataItemID;UUID", "DeviceID;UUID", "Useralias;String", "DeviceTypeID;String", "DataItem;String", "DataItemValue;String", "GroupID;UUID", "Minutes;String"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_Add(@PathParam("devicedataitemid") String deviceDataItemId, @PathParam("deviceid") String deviceId, @PathParam("useralias") String userAlias,
            @PathParam("devicetypeid") String deviceTypeId, @PathParam("dataitem") String dataItem, @PathParam("dataitemvalue") String dataItemValue, @PathParam("groupid") String groupId,
            @PathParam("minutes") int minutes, @PathParam("countryid") String countryid) {
        return addDeviceActivationSchedules(deviceDataItemId, deviceId, userAlias, deviceTypeId, dataItem, dataItemValue, groupId, minutes, countryid);
    }

    /**
     * InsertDeviceActivationSchedules_UI
     * @param deviceDataItemid
     * @param deviceId
     * @param userAlias
     * @param deviceTypeId
     * @param dataItem
     * @param dataItemValue
     * @param groupId
     * @param minutes
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/add/json/{json}/devicedataitemid/{devicedataitemid}/deviceid/{deviceid}/useralias/{useralias}/devicetypeid/{devicetypeid}/dataitem/{dataitem}/dataitemvalue/{dataitemvalue}/groupid/{groupid}/minutes/{minutes}/countryid/{countryid}")
    public TingcoDevice getJson_Add(@PathParam("devicedataitemid") String deviceDataItemId, @PathParam("deviceid") String deviceId, @PathParam("useralias") String userAlias,
            @PathParam("devicetypeid") String deviceTypeId, @PathParam("dataitem") String dataItem, @PathParam("dataitemvalue") String dataItemValue, @PathParam("groupid") String groupId,
            @PathParam("minutes") int minutes, @PathParam("countryid") String countryid) {
        return addDeviceActivationSchedules(deviceDataItemId, deviceId, userAlias, deviceTypeId, dataItem, dataItemValue, groupId, minutes, countryid);
    }

    /**
     * UpdateDeviceActivationSchedules
     * @param deviceDataItemId
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/update/rest/{rest}/devicedataitemid/{devicedataitemid}/deviceid/{deviceid}/countryid/{countryid}")
    @RESTDoc(methodName = "UpdateDeviceActivationSchedules", desc = "Update data", req_Params = {"DeviceDataItemID;UUID", "DeviceID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_Update(@PathParam("devicedataitemid") String deviceDataItemId, @PathParam("deviceid") String deviceId, @PathParam("countryid") String countryid) {
        return updateDeviceActivationSchedules(deviceDataItemId, deviceId, countryid);
    }

    /**
     * UpdateDeviceActivationSchedules
     * @param deviceDataItemId
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/update/json/{json}/devicedataitemid/{devicedataitemid}/deviceid/{deviceid}/countryid/{countryid}")
    public TingcoDevice getJson_Update(@PathParam("devicedataitemid") String deviceDataItemId, @PathParam("deviceid") String deviceId, @PathParam("countryid") String countryid) {
        return updateDeviceActivationSchedules(deviceDataItemId, deviceId, countryid);
    }

    /**
     * POST method for creating an instance of DeviceActivationSchedulesResource
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
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public DeviceActivationScheduleResource getDeviceActivationScheduleResource() {
        return new DeviceActivationScheduleResource();
    }

    private TingcoDevice addDeviceActivationSchedules(String deviceDataItemId, String deviceId, String userAlias, String deviceTypeId, String dataItem, String dataItemValue, String groupId, int minutes, String countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        Session ismSession = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {

                if (deviceDataItemId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DataItemID should not be null");
                    return tingcoDevice;
                }

                if (deviceId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceID should not be null");
                    return tingcoDevice;
                }

                if (userAlias == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("UserAlias should not be null");
                    return tingcoDevice;
                }

                if (deviceTypeId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DataTypeID should not be null");
                    return tingcoDevice;
                }

                if (dataItem == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DataItem should not be null");
                    return tingcoDevice;
                }

                if (dataItemValue == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DataItemValue should not be null");
                    return tingcoDevice;
                }

                if (groupId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("GroupID should not be null");
                    return tingcoDevice;
                }

                if (minutes == 0) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("Minutes should not be null");
                    return tingcoDevice;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.CONTROL)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    ismSession = HibernateUtil.getSessionFactory().openSession();
                    List<DeviceActivationSchedules> deviceActiveList = deviceDAO.getDeviceActivationID(session, deviceDataItemId, deviceId, ismSession);
                    DeviceActivationSchedules das = new DeviceActivationSchedules();
                    if (!deviceActiveList.isEmpty()) {
                        for (DeviceActivationSchedules dass : deviceActiveList) {
                            boolean result = deviceDAO.updateDeviceActivationSchedulesByActivationId(dass.getDeviceActivationId(), session, minutes, userAlias, deviceDataItemId, deviceId);
                            if (!result) {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("Error Occured while Inserting");
                            }
                            UserDAO userdao = new UserDAO();
                            List<Users2> users = userdao.getUsers2ById(sessions2.getUserId(), ismSession);
                            List<GroupTranslations> gtList = userdao.getGroupTranslationsById(users.get(0).getGroupId(), countryId, ismSession);
                            ServiceDAO servicedao = new ServiceDAO();
                            Services service = (Services) servicedao.getServicesbyServiceId(ismSession, TingcoConstants.getServiceID());
                            Device device = deviceDAO.getDeviceById(deviceId, ismSession);
                            List<DeviceTypeChannels> deviceTypeChannelsList = deviceDAO.getDeviceTypeChannels(device.getDeviceTypes().getDeviceTypeId(), ismSession);
                            EventMessageDocument emd = deviceDAO.createEM(device, service, "Charging Session Manual Command Events", "Start charging session", userAlias, minutes, dataItem, users.get(0).getFirstName() + " " + users.get(0).getLastName(), gtList.get(0).getGroupName(), null);
                            for (DeviceTypeChannels dtc : deviceTypeChannelsList) {
                                Channels c = deviceDAO.getChannels(dtc.getId().getChannelId(), ismSession);
                                deviceDAO.sendEventMessage(emd,TingcoConstants.getEventTopic());
                            }
                        }

                    } else {
                        das.setDeviceActivationId(UUID.randomUUID().toString());
                        das.setDeviceId(deviceId);
                        das.setUserAlias(userAlias);
                        das.setDeviceTypeId(deviceTypeId);
                        das.setDataItem(dataItem.toUpperCase());
                        das.setDataItemValue(dataItemValue);
                        das.setGroupId(groupId);
                        GregorianCalendar gc = new GregorianCalendar();
                        das.setStartTime(gc.getTime());
                        gc.add(Calendar.MINUTE, minutes);
//                    date.setMinutes(date.getMinutes()+minutes);
                        das.setStopTime(gc.getTime());

                        das.setCreatedDate(gc.getTime());
                        das.setUpdatedDate(gc.getTime());
                        boolean result = deviceDAO.addDeviceActivationSchedules(das, session, deviceDataItemId, deviceId);
                        if (!result) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error Occured while Inserting");
                            return tingcoDevice;
                        }
                        UserDAO userdao = new UserDAO();
                        List<Users2> users = userdao.getUsers2ById(sessions2.getUserId(), ismSession);
                        List<GroupTranslations> gtList = userdao.getGroupTranslationsById(users.get(0).getGroupId(), countryId, ismSession);
                        ServiceDAO servicedao = new ServiceDAO();
                        Services service = (Services) servicedao.getServicesbyServiceId(ismSession, TingcoConstants.getServiceID());
                        Device device = deviceDAO.getDeviceById(deviceId, ismSession);
                        List<DeviceTypeChannels> deviceTypeChannelsList = deviceDAO.getDeviceTypeChannels(device.getDeviceTypes().getDeviceTypeId(), ismSession);
                        EventMessageDocument emd = deviceDAO.createEM(device, service, "Charging Session Manual Command Events", "Start charging session", userAlias, minutes, dataItem, users.get(0).getFirstName() + " " + users.get(0).getLastName(), gtList.get(0).getGroupName(), null);
                        for (DeviceTypeChannels dtc : deviceTypeChannelsList) {
                            Channels c = deviceDAO.getChannels(dtc.getId().getChannelId(), ismSession);
                            deviceDAO.sendEventMessage(emd, TingcoConstants.getEventTopic());
                        }
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

            return tingcoDevice;
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error Occured");
            return tingcoDevice;
        } finally {
            if (ismSession != null) {
                ismSession.close();
            }
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoDevice updateDeviceActivationSchedules(String deviceDataItemId, String deviceId, String Countryid) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        Session ismsession = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {

                if (deviceDataItemId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DataItemID should not be null");
                    return tingcoDevice;
                }

                if (deviceId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceID should not be null");
                    return tingcoDevice;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.CONTROL)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }

                if (hasPermission) {
                    session = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    ismsession = HibernateUtil.getSessionFactory().openSession();
                    if (deviceDAO.getDeviceActivationID(session, deviceDataItemId, deviceId, ismsession).isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoDevice;
                    }
                    boolean result = deviceDAO.updateDeviceActivationSchedules(deviceDataItemId, deviceId, session, ismsession);
                    if (!result) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Error Occured while Updating");
                        return tingcoDevice;
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(0);
                        tingcoDevice.getMsgStatus().setResponseText("Updated successfully ");

                        String outlet = null;
                        List<DeviceDataItems> ddiList = ismsession.getNamedQuery("getDeviceDataItemsByItemId").setString("id", deviceDataItemId).list();
                        if (!ddiList.isEmpty()) {
                            for (DeviceDataItems ddi : ddiList) {
                                List<DeviceActivationSchedules> dasList = session.getNamedQuery("getDeviceActivationSchedules").setString("dataItem", ddi.getDeviceDataFieldName()).setString("deviceId", deviceId).list();
                                if (!dasList.isEmpty()) {
                                    for (DeviceActivationSchedules das : dasList) {
                                        outlet = das.getDataItem();
                                        break;
                                    }
                                }
                            }
                        }
                        UserDAO userdao = new UserDAO();
                        List<Users2> users = userdao.getUsers2ById(sessions2.getUserId(), ismsession);
                        List<GroupTranslations> gtList = userdao.getGroupTranslationsById(users.get(0).getGroupId(), Countryid, ismsession);
                        ServiceDAO servicedao = new ServiceDAO();
                        Services service = (Services) servicedao.getServicesbyServiceId(ismsession, TingcoConstants.getServiceID());
                        Device device = deviceDAO.getDeviceById(deviceId, ismsession);
                        List<DeviceTypeChannels> deviceTypeChannelsList = deviceDAO.getDeviceTypeChannels(device.getDeviceTypes().getDeviceTypeId(), ismsession);
                        EventMessageDocument emd = deviceDAO.createEM(device, service, "Charging Session Manual Command Events", "End charging session", null, 0, outlet, users.get(0).getFirstName() + " " + users.get(0).getLastName(), gtList.get(0).getGroupName(), null);
                        for (DeviceTypeChannels dtc : deviceTypeChannelsList) {
                            Channels c = deviceDAO.getChannels(dtc.getId().getChannelId(), ismsession);
                            deviceDAO.sendEventMessage(emd,TingcoConstants.getEventTopic());
                        }
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
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error Occured while Updating");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            if (ismsession != null) {
                ismsession.close();
            }
            delayLog(requestedTime);
        }
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}

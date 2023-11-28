/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.device;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.xml.datatype.DatatypeConfigurationException;
import org.hibernate.Session;
import se.info24.devicejaxb.TingcoDevice;
import se.info24.jaxbUtil.TingcoDeviceXML;
import se.info24.pojo.DeviceDataItems;
import se.info24.pojo.DeviceTypeDataitems;
import se.info24.pojo.DeviceTypeDataitemsId;
import se.info24.pojo.DeviceTypes;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.Users2;
import se.info24.user.UserDAO;
import se.info24.util.HibernateUtil;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/devicetypeattribute")
public class DeviceTypeAttributesResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
    DeviceDAO deviceDAO = new DeviceDAO();
    UserDAO userDAO = new UserDAO();
//    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of DeviceTypeAttributesResource */
    public DeviceTypeAttributesResource() {
    }

    /**
     * DeviceTypeAttribute_Add
     * @param deviceTypeID
     * @param deviceDataItemID
     * @param dataItemDefaultValue
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/add/rest/{rest}/devicetypeid/{devicetypeid}/devicedataitemid/{devicedataitemid}/dataitemdefaultvalue/{dataitemdefaultvalue}")
    @RESTDoc(methodName = "DeviceTypeAttribute_Add", desc = "Used to create DeviceType Attribute", req_Params = {"DeviceTypeID;UUID", "DeviceDataItemID;UUID", "DataItemDefaultValue;String"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_Add(@PathParam("devicetypeid") String deviceTypeID, @PathParam("devicedataitemid") String deviceDataItemID, @PathParam("dataitemdefaultvalue") String dataItemDefaultValue) throws DatatypeConfigurationException {
        return createDeviceTypeAttribute(deviceTypeID, deviceDataItemID, dataItemDefaultValue);
    }

    /**
     * DeviceTypeAttribute_Add
     * @param deviceTypeID
     * @param deviceDataItemID
     * @param dataItemDefaultValue
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/add/json/{json}/devicetypeid/{devicetypeid}/devicedataitemid/{devicedataitemid}/dataitemdefaultvalue/{dataitemdefaultvalue}")
    public TingcoDevice getJson_Add(@PathParam("devicetypeid") String deviceTypeID, @PathParam("devicedataitemid") String deviceDataItemID, @PathParam("dataitemdefaultvalue") String dataItemDefaultValue) throws DatatypeConfigurationException {
        return createDeviceTypeAttribute(deviceTypeID, deviceDataItemID, dataItemDefaultValue);
    }

    /**
     * DeviceTypeAttribute_Delete
     * @param deviceTypeID
     * @param deviceDataItemID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/devicetypeid/{devicetypeid}/devicedataitemid/{devicedataitemid}")
    @RESTDoc(methodName = "DeviceTypeAttribute_Delete", desc = "Used to delete DeviceType Attribute", req_Params = {"DeviceTypeID;UUID", "DeviceDataItemID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_Delete(@PathParam("devicetypeid") String deviceTypeID, @PathParam("devicedataitemid") String deviceDataItemID) {
        return deleteDeviceTypeAttribute(deviceTypeID, deviceDataItemID);
    }

    /**
     * DeviceTypeAttribute_Delete
     * @param deviceTypeID
     * @param deviceDataItemID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/delete/json/{json}/devicetypeid/{devicetypeid}/devicedataitemid/{devicedataitemid}")
    public TingcoDevice getJson_Delete(@PathParam("devicetypeid") String deviceTypeID, @PathParam("devicedataitemid") String deviceDataItemID) {
        return deleteDeviceTypeAttribute(deviceTypeID, deviceDataItemID);
    }

    /**
     * DeviceTypeAttribute_GetInfo
     * @param deviceTypeID
     * @param countryID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/get/rest/{rest}/devicetypeid/{devicetypeid}/countryid/{countryid}")
    @RESTDoc(methodName = "DeviceTypeAttribute_GetInfo", desc = "To Get a DeviceTypeAttributes by DeviceTypeID(DeviceTypeDataItems)", req_Params = {"DeviceTypeID;UUID", "CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoDevice getXml(@PathParam("devicetypeid") String deviceTypeID, @PathParam("countryid") int countryID) {
        return getDeviceTypeAttributeInfo(deviceTypeID, countryID);
    }

    /**
     * DeviceTypeAttribute_GetInfo
     * @param deviceTypeID
     * @param countryID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/get/rest/{rest}/devicetypeid/{devicetypeid}/countryid/{countryid}")
    public TingcoDevice postXml(@PathParam("devicetypeid") String deviceTypeID, @PathParam("countryid") int countryID) {
        return getDeviceTypeAttributeInfo(deviceTypeID, countryID);
    }

    /**
     * DeviceTypeAttribute_GetInfo
     * @param deviceTypeID
     * @param countryID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/get/json/{json}/devicetypeid/{devicetypeid}/countryid/{countryid}")
    public TingcoDevice getJson(@PathParam("devicetypeid") String deviceTypeID, @PathParam("countryid") int countryID) {
        return getDeviceTypeAttributeInfo(deviceTypeID, countryID);
    }

    /**
     * DeviceTypeAttribute_GetInfo
     * @param deviceTypeID
     * @param countryID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/get/json/{json}/devicetypeid/{devicetypeid}/countryid/{countryid}")
    public TingcoDevice postJson(@PathParam("devicetypeid") String deviceTypeID, @PathParam("countryid") int countryID) {
        return getDeviceTypeAttributeInfo(deviceTypeID, countryID);
    }

    private TingcoDevice createDeviceTypeAttribute(String deviceTypeId, String deviceDataItemId, String dataitemDefaultValue) throws DatatypeConfigurationException {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceTypeId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceTypeID is should not be empty");
                    return tingcoDevice;
                } else if (deviceDataItemId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceDataItemID is should not be empty");
                    return tingcoDevice;
                } else if (dataitemDefaultValue == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DataItemDefaultValue is should not be empty");
                    return tingcoDevice;
                }

//            Hashtable<String,ArrayList> ht = (Hashtable<String, ArrayList>) request.getSession(true).getAttribute(request.getSession(true).getId());
//            if (ht.containsKey(TCMUtil.DEVICE)) {
//                ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
//                for(int i=0;i<operations.size();i++){
//                    if(operations.get(i).equalsIgnoreCase(TCMUtil.ADD)){
                hasPermission = true;
//                    }
//                }
//            }

                if (hasPermission) {
                    UserSessions2 userSessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    DeviceTypeDataitems dtdi = new DeviceTypeDataitems();
                    dtdi.setId(new DeviceTypeDataitemsId(deviceTypeId, deviceDataItemId));
                    dtdi.setDeviceTypes(new DeviceTypes(deviceTypeId));
                    dtdi.setDeviceDataItems(new DeviceDataItems(deviceDataItemId));
                    dtdi.setDataItemDefaultValue(dataitemDefaultValue);
                    dtdi.setUserId(userSessions2.getUserId());
                    dtdi.setCreatedDate(gc.getTime());
                    dtdi.setUpdatedDate(gc.getTime());
                    session = HibernateUtil.getSessionFactory().openSession();
                    if (!deviceDAO.saveDeviceTypeDataItems(dtdi, session)) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Error Occured while Saving DeviceTypeDataitems");
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
            tingcoDevice.getMsgStatus().setResponseText("Error Occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice deleteDeviceTypeAttribute(String deviceTypeID, String deviceDataItemID) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceTypeID == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceTypeID is should not be empty");
                    return tingcoDevice;
                } else if (deviceDataItemID == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceDataItemID is should not be empty");
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
                    session = HibernateUtil.getSessionFactory().openSession();
                    DeviceTypeDataitems deviceTypeDataitems = deviceDAO.getDeviceTypeDataItems(deviceTypeID, deviceDataItemID, session);
                    if (deviceTypeDataitems != null) {
                        if (!deviceDAO.removeDeviceTypeDataitems(deviceTypeDataitems, session)) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error Occured while Deleting DeviceTypeDataitems");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("DeviceTypeID and DeviceDataItemID Not Found");
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
            tingcoDevice.getMsgStatus().setResponseText("Error Occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getDeviceTypeAttributeInfo(String deviceTypeId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceTypeId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceTypeID is should not be empty");
                    return tingcoDevice;
                } else if (countryId <= 0) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("CountryID is should not be empty");
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
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<DeviceTypeDataitems> dtdiList = deviceDAO.getDeviceTypeDataItemsByDeviceTypeID(deviceTypeId, session);
                    if (dtdiList != null) {
                        HashMap<String, Users2> userMap = new HashMap<String, Users2>();
                        for (DeviceTypeDataitems dtdi : dtdiList) {
                            if (!userMap.containsKey(dtdi.getUserId())) {
                                Users2 user2 = userDAO.getUserById(dtdi.getUserId(), session);
                                userMap.put(dtdi.getUserId(), user2);
                            }
                        }
//                        tingcoDevice = tingcoDeviceXML.buildDeviceTypeAttributes(tingcoDevice, dtdiList, userMap, countryId);
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("DeviceTypeID Not Found");
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
            tingcoDevice.getMsgStatus().setResponseText("Error Occured");
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
    public DeviceTypeAttributeResource getDeviceTypeAttributeResource() {
        return new DeviceTypeAttributeResource();
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}

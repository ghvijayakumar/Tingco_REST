/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.device;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
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
import se.info24.devicejaxb.TingcoDevice;
import se.info24.ismOperationsPojo.DeviceStatusDataItems;
import se.info24.jaxbUtil.TingcoDeviceXML;
import se.info24.pojo.DataItemTranslationsPerDevice;
import se.info24.pojo.Device;
import se.info24.pojo.DeviceDataItemScaling;
import se.info24.pojo.DeviceDataItems;
import se.info24.pojo.DeviceDataitemTranslations;
import se.info24.pojo.Ogmdevice;
import se.info24.pojo.ProductVariantTranslations;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.UserTimeZones2;
import se.info24.restUtil.RestUtilDAO;
import se.info24.user.User_LoginsResource;
import se.info24.util.HibernateUtil;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/deviceattributes")
public class DeviceAttributessResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
    DeviceDAO deviceDAO = new DeviceDAO();
//    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of DeviceAttributessResource */
    public DeviceAttributessResource() {
    }

    /**
     * DeviceAttributes_Add
     * @param dataFieldName
     * @param dataGroupName
     * @param unit
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/add/rest/{rest}/datafieldname/{datafieldname}/datagroupname/{datagroupname}{unit:(/unit/[^/]+?)?}")
    @RESTDoc(methodName = "DeviceAttributes_Add", desc = "Used to create a DeviceAttribute", req_Params = {"DataFieldName;String", "DataGroupName;String"}, opt_Params = {"Unit;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoDevice getXml_Add(@PathParam("datafieldname") String dataFieldName, @PathParam("datagroupname") String dataGroupName, @PathParam("unit") String unit) {
        return createDeviceAttribute(dataFieldName, dataGroupName, unit);
    }

    /**
     * DeviceAttributes_Add
     * @param dataFieldName
     * @param dataGroupName
     * @param unit
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/add/rest/{rest}/datafieldname/{datafieldname}/datagroupname/{datagroupname}{unit:(/unit/[^/]+?)?}")
    public TingcoDevice postXml_Add(@PathParam("datafieldname") String dataFieldName, @PathParam("datagroupname") String dataGroupName, @PathParam("unit") String unit) {
        return createDeviceAttribute(dataFieldName, dataGroupName, unit);
    }

    /**
     * DeviceAttributes_Add
     * @param dataFieldName
     * @param dataGroupName
     * @param unit
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/add/json/{json}/datafieldname/{datafieldname}/datagroupname/{datagroupname}{unit:(/unit/[^/]+?)?}")
    public TingcoDevice getJson_Add(@PathParam("datafieldname") String dataFieldName, @PathParam("datagroupname") String dataGroupName, @PathParam("unit") String unit) {
        return createDeviceAttribute(dataFieldName, dataGroupName, unit);
    }

    /**
     * DeviceAttributes_Add
     * @param dataFieldName
     * @param dataGroupName
     * @param unit
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/add/json/{json}/datafieldname/{datafieldname}/datagroupname/{datagroupname}{unit:(/unit/[^/]+?)?}")
    public TingcoDevice postJson_Add(@PathParam("datafieldname") String dataFieldName, @PathParam("datagroupname") String dataGroupName, @PathParam("unit") String unit) {
        return createDeviceAttribute(dataFieldName, dataGroupName, unit);
    }

    /**
     * DeviceAttributes_Delete
     * @param id
     * @return
     */
    @GET
    @Path("/delete/rest/{rest}/devicedataitemid/{devicedataitemid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "DeviceAttributes_Delete", desc = "Used to delete a DeviceAttribute", req_Params = {"DeviceDataItemID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoDevice getXml_Delete(@PathParam("devicedataitemid") String id) {
        return isDeletedDeviceAttribute(id);
    }

    /**
     * DeviceAttributes_Delete
     * @param id
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/delete/json/{json}/devicedataitemid/{devicedataitemid}")
    public TingcoDevice getJson_Delete(@PathParam("devicedataitemid") String id) {
        return isDeletedDeviceAttribute(id);
    }

    /**
     * DeviceAttributes_Delete
     * @param id
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/devicedataitemid/{devicedataitemid}")
    public TingcoDevice postXml_Delete(@PathParam("devicedataitemid") String id) {
        return isDeletedDeviceAttribute(id);
    }

    /**
     * DeviceAttributes_Delete
     * @param id
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/delete/json/{json}/devicedataitemid/{devicedataitemid}")
    public TingcoDevice postJson_Delete(@PathParam("devicedataitemid") String id) {
        return isDeletedDeviceAttribute(id);
    }

    /**
     * DeviceAttributes_Update
     * @param id
     * @param dataFieldName
     * @param dataGroupName
     * @param unit
     * @return
     */
    @GET
    @Path("/update/rest/{rest}/devicedataitemid/{devicedataitemid}{datafieldname:(/datafieldname/[^/]+?)?}{datagroupname:(/datagroupname/[^/]+?)?}{unit:(/unit/[^/]+?)?}")
    @RESTDoc(methodName = "DeviceAttributes_Update", desc = "Used to update a DeviceAttribute", req_Params = {"DeviceDataItemID;UUID"}, opt_Params = {"DataFieldName;String", "DataGroupName;String", "Unit;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    @Produces("application/xml")
    public TingcoDevice getXml_Update(@PathParam("devicedataitemid") String id, @PathParam("datafieldname") String dataFieldName, @PathParam("datagroupname") String dataGroupName, @PathParam("unit") String unit) {
        return isUpdatedDeviceAttribute(id, dataFieldName, dataGroupName, unit);
    }

    /**
     * DeviceAttributes_Update
     * @param id
     * @param dataFieldName
     * @param dataGroupName
     * @param unit
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/update/json/{json}/devicedataitemid/{devicedataitemid}{datafieldname:(/datafieldname/[^/]+?)?}{datagroupname:(/datagroupname/[^/]+?)?}{unit:(/unit/[^/]+?)?}")
    public TingcoDevice getJson_Update(@PathParam("devicedataitemid") String id, @PathParam("datafieldname") String dataFieldName, @PathParam("datagroupname") String dataGroupName, @PathParam("unit") String unit) {
        return isUpdatedDeviceAttribute(id, dataFieldName, dataGroupName, unit);
    }

    /**
     * GetDeviceLevelDetails
     * @param countryid
     * @param groupid
     * @param objectgroupid
     * @param deviceid
     * @param productvariantid
     * @param filllevel
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getdeviceleveldetails/rest/{rest}/countryid/{countryid}{groupid:(/groupid/[^/]+?)?}{objectgroupid:(/objectgroupid/[^/]+?)?}{deviceid:(/deviceid/[^/]+?)?}{productvariantid:(/productvariantid/[^/]+?)?}{filllevel:(/filllevel/[^/]+?)?}")
    @RESTDoc(methodName = "GetDeviceLevelDetails", desc = "Used to get Device Level Details", req_Params = {"CountryID;int"}, opt_Params = {"GroupID;UUID", "ObjectGroupID;UUID", "DeviceID;UUID", "ProductVariantID;UUID", "FiilLevel;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice GetXml_DeviceLevelDetails(@PathParam("countryid") int countryid, @PathParam("groupid") String groupid, @PathParam("objectgroupid") String objectgroupid, @PathParam("deviceid") String deviceid, @PathParam("productvariantid") String productvariantid, @PathParam("filllevel") String filllevel) {
        return getDeviceLevelDetails(countryid, groupid, objectgroupid, deviceid, productvariantid, filllevel);
    }

    /**
     * GetDeviceLevelDetails
     * @param countryid
     * @param groupid
     * @param objectgroupid
     * @param deviceid
     * @param productvariantid
     * @param filllevel
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getdeviceleveldetails/json/{json}/countryid/{countryid}{groupid:(/groupid/[^/]+?)?}{objectgroupid:(/objectgroupid/[^/]+?)?}{deviceid:(/deviceid/[^/]+?)?}{productvariantid:(/productvariantid/[^/]+?)?}{filllevel:(/filllevel/[^/]+?)?}")
    public TingcoDevice GetJson_DeviceLevelDetails(@PathParam("countryid") int countryid, @PathParam("groupid") String groupid, @PathParam("objectgroupid") String objectgroupid, @PathParam("deviceid") String deviceid, @PathParam("productvariantid") String productvariantid, @PathParam("filllevel") String filllevel) {
        return getDeviceLevelDetails(countryid, groupid, objectgroupid, deviceid, productvariantid, filllevel);
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public DeviceAttributesResource getDeviceAttributesResource() {
        return new DeviceAttributesResource();
    }

    private TingcoDevice createDeviceAttribute(String dataFieldName, String dataGroupName, String unit) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (dataFieldName == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DataFieldName is should not be empty");
                    return tingcoDevice;
                } else if (dataGroupName == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DataGroupName is should not be empty");
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
                if (unit.equals("")) {
                    unit = null;
                } else {
                    unit = unit.split("/")[2];
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    GregorianCalendar gc = new GregorianCalendar();
                    DeviceDataItems ddi = new DeviceDataItems();
                    ddi.setDeviceDataFieldName(dataFieldName);
                    ddi.setDeviceDataGroupName(dataGroupName);
                    ddi.setUnit(unit);
                    ddi.setDeviceDataItemId(UUID.randomUUID().toString());
                    ddi.setCreatedDate(gc.getTime());
                    ddi.setUpdatedDate(gc.getTime());
                    boolean added = deviceDAO.addNewDeviceAttribute(ddi, session);
                    if (!added) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Error");
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
            tingcoDevice.getMsgStatus().setResponseText("Error");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice isDeletedDeviceAttribute(String id) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (id == null || id.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceDataItemID should not be empty");
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
                    boolean deleted = deviceDAO.deleteDeviceAttribute(id, session);
                    if (!deleted) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Error");
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
            tingcoDevice.getMsgStatus().setResponseText("Error");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice isUpdatedDeviceAttribute(String id, String dataFieldName, String dataGroupName, String unit) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (id == null || id.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceDataItemID should not be empty");
                    return tingcoDevice;
                }

                if (dataFieldName.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DataFieldName should not be empty");
                    return tingcoDevice;
                }


                if (dataGroupName.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DataGroupName should not be empty");
                    return tingcoDevice;
                }

//            Hashtable<String,ArrayList> ht = (Hashtable<String, ArrayList>) request.getSession(true).getAttribute(request.getSession(true).getId());
//            if (ht.containsKey(TCMUtil.DEVICE)) {
//                ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
//                for(int i=0;i<operations.size();i++){
//                    if(operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)){
                hasPermission = true;
//                    }
//                }
//            }

                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    if (!dataFieldName.equals("")) {
                        dataFieldName = dataFieldName.split("/")[2];
                    }
                    if (!dataGroupName.equals("")) {
                        dataGroupName = dataGroupName.split("/")[2];
                    }

                    if (!unit.equals("")) {
                        unit = unit.split("/")[2];
                    }


                    boolean updated = deviceDAO.updateDeviceAttribute(id, dataFieldName, dataGroupName, unit, session);
                    if (!updated) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Error");
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
            tingcoDevice.getMsgStatus().setResponseText("Error");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getDeviceLevelDetails(int countryid, String groupid, String objectgroupid, String deviceid, String productvariantid, String filllevel) {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        Session session = null;
        Session sessionoperation = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                boolean checkDataScaling = false;
                if (countryid <= 0) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("CountryID should not be empty");
                    return tingcoDevice;
                }
                if (!groupid.equals("")) {
                    groupid = groupid.split("/")[2];
                }
                if (!objectgroupid.equals("")) {
                    objectgroupid = objectgroupid.split("/")[2];
                }

                if (!deviceid.equals("")) {
                    deviceid = deviceid.split("/")[2];
                }
                if (!productvariantid.equals("")) {
                    productvariantid = productvariantid.split("/")[2];
                }

                if (!filllevel.equals("")) {
                    filllevel = filllevel.split("/")[2];
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> hta = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (hta.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = hta.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    sessionoperation = HibernateUtil.getISMOperationsSessionFactory().openSession();


                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        Set<String> deviceIdSet = new HashSet<String>();
                        List<DeviceDataItemScaling> ddis = new ArrayList<DeviceDataItemScaling>();
                        if (!groupid.equals("") && objectgroupid.equals("") && deviceid.equals("") && productvariantid.equals("") && filllevel.equals("")) {
                            List<se.info24.pojo.Device> device = deviceDAO.getDeviceByGroup(session, groupid);
                            if (device != null) {
                                for (Device device1 : device) {
                                    deviceIdSet.add(device1.getDeviceId());
                                }
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("GroupId Not Found");
                                return tingcoDevice;
                            }
                        } else if (groupid.equals("") && !objectgroupid.equals("") && deviceid.equals("") && productvariantid.equals("") && filllevel.equals("")) {
                            List<Ogmdevice> od = deviceDAO.getOgmdeviceById(session, objectgroupid);
                            for (Ogmdevice ogmdevice : od) {
                                deviceIdSet.add(ogmdevice.getId().getDeviceId());
                            }
                        } else if (groupid.equals("") && objectgroupid.equals("") && !deviceid.equals("") && productvariantid.equals("") && filllevel.equals("")) {
                            deviceIdSet.add(deviceid);
                        } else if (groupid.equals("") && objectgroupid.equals("") && deviceid.equals("") && !productvariantid.equals("") && filllevel.equals("")) {
                            ddis = deviceDAO.getDeviceDataItemScalingByproductVariantId(session, productvariantid);
                        } else if (groupid.equals("") && objectgroupid.equals("") && deviceid.equals("") && productvariantid.equals("") && !filllevel.equals("")) {
                            checkDataScaling = true;
                            ddis = deviceDAO.getAllDeviceDataItemScaling(session);
                            Object dsdio = null;
                            List<DeviceDataItemScaling> added = new ArrayList<DeviceDataItemScaling>();
                            for (DeviceDataItemScaling deviceDataItemScaling : ddis) {
                                dsdio = deviceDAO.getDeviceStatusDataItemsBydeviceDataItemId(sessionoperation, deviceDataItemScaling.getId().getDeviceId(), deviceDataItemScaling.getId().getDeviceDataItemId());
                                if (dsdio != null) {
                                    DeviceStatusDataItems dsdi = (DeviceStatusDataItems) dsdio;
                                    String FillLevel = dsdi.getDeviceDataValue();
                                    double y = (Double.valueOf(FillLevel) - deviceDataItemScaling.getScaledMinValue().doubleValue()) / (deviceDataItemScaling.getScaledMaxValue().doubleValue() - deviceDataItemScaling.getScaledMinValue().doubleValue()) * 100;
                                    if (y <= Double.valueOf(filllevel)) {
                                        added.add(deviceDataItemScaling);
                                    }
                                }
                            }
                            ddis.clear();
                            ddis.addAll(added);
                        } else if (!groupid.equals("") && !objectgroupid.equals("") && deviceid.equals("") && productvariantid.equals("") && filllevel.equals("")) {
                            List<Ogmdevice> od = deviceDAO.getOgmdeviceById(session, objectgroupid);
                            List<se.info24.pojo.Device> device = deviceDAO.getDeviceByGroup(session, groupid);
                            for (Ogmdevice ogmdevice : od) {
                                for (Device device1 : device) {
                                    if (ogmdevice.getId().getDeviceId().equalsIgnoreCase(device1.getDeviceId())) {
                                        deviceIdSet.add(device1.getDeviceId());
                                    }
                                }
                            }


                        } else if (!groupid.equals("") && objectgroupid.equals("") && !deviceid.equals("") && productvariantid.equals("") && filllevel.equals("")) {
                            List<se.info24.pojo.Device> device = deviceDAO.getDeviceByGroup(session, groupid);
                            boolean flag = false;
                            for (Device device1 : device) {
                                if (device1.getDeviceId().equalsIgnoreCase(deviceid)) {
                                    deviceIdSet.add(deviceid);
                                    flag = true;
                                }
                            }
                            if (!flag) {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("DeviceID not belongs to given GroupID");
                                return tingcoDevice;
                            }
                        } else if (!groupid.equals("") && objectgroupid.equals("") && deviceid.equals("") && !productvariantid.equals("") && filllevel.equals("")) {
                            List<se.info24.pojo.Device> device = deviceDAO.getDeviceByGroup(session, groupid);
                            for (Device device1 : device) {
                                deviceIdSet.add(device1.getDeviceId());
                            }
                            if (!deviceIdSet.isEmpty()) {
                                checkDataScaling = true;
                                List<String> deviceLists = new ArrayList<String>(deviceIdSet);
                                ddis = deviceDAO.getDeviceDataItemScalingByDeviceIdAndVariantId(session, deviceLists, productvariantid);
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                                return tingcoDevice;
                            }
                        } else if (!groupid.equals("") && objectgroupid.equals("") && deviceid.equals("") && productvariantid.equals("") && !filllevel.equals("")) {
                            List<se.info24.pojo.Device> device = deviceDAO.getDeviceByGroup(session, groupid);
                            for (Device device1 : device) {
                                deviceIdSet.add(device1.getDeviceId());
                            }
                            if (!deviceIdSet.isEmpty()) {
                                checkDataScaling = true;
                                List<String> deviceLists = new ArrayList<String>(deviceIdSet);
                                ddis = deviceDAO.getDeviceDataItemScalingByDeviceId(session, deviceLists);
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                                return tingcoDevice;
                            }
                            Object dsdio = null;
//                        List<DeviceDataItemScaling> added = new AbstractList<DeviceDataItemScaling>();
                            List<DeviceDataItemScaling> added = new ArrayList<DeviceDataItemScaling>();
                            for (DeviceDataItemScaling deviceDataItemScaling : ddis) {
                                dsdio = deviceDAO.getDeviceStatusDataItemsBydeviceDataItemId(sessionoperation, deviceDataItemScaling.getId().getDeviceId(), deviceDataItemScaling.getId().getDeviceDataItemId());
                                if (dsdio != null) {
                                    DeviceStatusDataItems dsdi = (DeviceStatusDataItems) dsdio;
                                    String FillLevel = dsdi.getDeviceDataValue();
                                    double y = (Double.valueOf(FillLevel) - deviceDataItemScaling.getScaledMinValue().doubleValue()) / (deviceDataItemScaling.getScaledMaxValue().doubleValue() - deviceDataItemScaling.getScaledMinValue().doubleValue()) * 100;
                                    if (y <= Double.valueOf(filllevel)) {
                                        added.add(deviceDataItemScaling);
                                    }
                                }

                            }
                            ddis.clear();
                            ddis.addAll(added);
                        } else if (groupid.equals("") && !objectgroupid.equals("") && !deviceid.equals("") && productvariantid.equals("") && filllevel.equals("")) {
                            List<Ogmdevice> od = deviceDAO.getOgmdeviceById(session, objectgroupid);
                            for (Ogmdevice ogmdevice : od) {
                                if (ogmdevice.getId().getDeviceId().equalsIgnoreCase(deviceid)) {
                                    deviceIdSet.add(ogmdevice.getId().getDeviceId());
                                }
                            }
                        } else if (groupid.equals("") && !objectgroupid.equals("") && deviceid.equals("") && !productvariantid.equals("") && filllevel.equals("")) {
                            List<Ogmdevice> od = deviceDAO.getOgmdeviceById(session, objectgroupid);
                            for (Ogmdevice ogmdevice : od) {
                                deviceIdSet.add(ogmdevice.getId().getDeviceId());
                            }
                            if (!deviceIdSet.isEmpty()) {
                                checkDataScaling = true;
                                List<String> deviceLists = new ArrayList<String>(deviceIdSet);
                                ddis = deviceDAO.getDeviceDataItemScalingByDeviceIdAndVariantId(session, deviceLists, productvariantid);
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                                return tingcoDevice;
                            }

                        } else if (groupid.equals("") && !objectgroupid.equals("") && deviceid.equals("") && productvariantid.equals("") && !filllevel.equals("")) {
                            List<Ogmdevice> od = deviceDAO.getOgmdeviceById(session, objectgroupid);
                            for (Ogmdevice ogmdevice : od) {
                                deviceIdSet.add(ogmdevice.getId().getDeviceId());
                            }
                            if (!deviceIdSet.isEmpty()) {
                                checkDataScaling = true;
                                List<String> deviceLists = new ArrayList<String>(deviceIdSet);
                                ddis = deviceDAO.getDeviceDataItemScalingByDeviceId(session, deviceLists);
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                                return tingcoDevice;
                            }
                            Object dsdio = null;
                            List<DeviceDataItemScaling> added = new ArrayList<DeviceDataItemScaling>();
                            for (DeviceDataItemScaling deviceDataItemScaling : ddis) {
                                dsdio = deviceDAO.getDeviceStatusDataItemsBydeviceDataItemId(sessionoperation, deviceDataItemScaling.getId().getDeviceId(), deviceDataItemScaling.getId().getDeviceDataItemId());
                                if (dsdio != null) {
                                    DeviceStatusDataItems dsdi = (DeviceStatusDataItems) dsdio;
                                    String FillLevel = dsdi.getDeviceDataValue();
                                    double y = (Double.valueOf(FillLevel) - deviceDataItemScaling.getScaledMinValue().doubleValue()) / (deviceDataItemScaling.getScaledMaxValue().doubleValue() - deviceDataItemScaling.getScaledMinValue().doubleValue()) * 100;
                                    if (y <= Double.valueOf(filllevel)) {
                                        added.add(deviceDataItemScaling);
                                    }
                                }

                            }
                            ddis.clear();
                            ddis.addAll(added);
                        } else if (groupid.equals("") && objectgroupid.equals("") && !deviceid.equals("") && !productvariantid.equals("") && filllevel.equals("")) {
                            deviceIdSet.add(deviceid);
                            if (!deviceIdSet.isEmpty()) {
                                checkDataScaling = true;
                                List<String> deviceLists = new ArrayList<String>(deviceIdSet);
                                ddis = deviceDAO.getDeviceDataItemScalingByDeviceIdAndVariantId(session, deviceLists, productvariantid);
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                                return tingcoDevice;
                            }


                        } else if (groupid.equals("") && objectgroupid.equals("") && !deviceid.equals("") && productvariantid.equals("") && !filllevel.equals("")) {
                            deviceIdSet.add(deviceid);
                            if (!deviceIdSet.isEmpty()) {
                                checkDataScaling = true;
                                List<String> deviceLists = new ArrayList<String>(deviceIdSet);
                                ddis = deviceDAO.getDeviceDataItemScalingByDeviceId(session, deviceLists);
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                                return tingcoDevice;
                            }

                            Object dsdio = null;
                            List<DeviceDataItemScaling> added = new ArrayList<DeviceDataItemScaling>();
                            for (DeviceDataItemScaling deviceDataItemScaling : ddis) {
                                dsdio = deviceDAO.getDeviceStatusDataItemsBydeviceDataItemId(sessionoperation, deviceDataItemScaling.getId().getDeviceId(), deviceDataItemScaling.getId().getDeviceDataItemId());
                                if (dsdio != null) {
                                    DeviceStatusDataItems dsdi = (DeviceStatusDataItems) dsdio;
                                    String FillLevel = dsdi.getDeviceDataValue();
                                    double y = (Double.valueOf(FillLevel) - deviceDataItemScaling.getScaledMinValue().doubleValue()) / (deviceDataItemScaling.getScaledMaxValue().doubleValue() - deviceDataItemScaling.getScaledMinValue().doubleValue()) * 100;
                                    if (y <= Double.valueOf(filllevel)) {
                                        added.add(deviceDataItemScaling);
                                    }
                                }

                            }
                            ddis.clear();
                            ddis.addAll(added);
                        } else if (groupid.equals("") && objectgroupid.equals("") && deviceid.equals("") && !productvariantid.equals("") && !filllevel.equals("")) {
                            checkDataScaling = true;
                            ddis = deviceDAO.getDeviceDataItemScalingByproductVariantId(session, productvariantid);
                            Object dsdio = null;
                            List<DeviceDataItemScaling> added = new ArrayList<DeviceDataItemScaling>();
                            for (DeviceDataItemScaling deviceDataItemScaling : ddis) {
                                dsdio = deviceDAO.getDeviceStatusDataItemsBydeviceDataItemId(sessionoperation, deviceDataItemScaling.getId().getDeviceId(), deviceDataItemScaling.getId().getDeviceDataItemId());
                                if (dsdio != null) {
                                    DeviceStatusDataItems dsdi = (DeviceStatusDataItems) dsdio;
                                    String FillLevel = dsdi.getDeviceDataValue();
                                    double y = (Double.valueOf(FillLevel) - deviceDataItemScaling.getScaledMinValue().doubleValue()) / (deviceDataItemScaling.getScaledMaxValue().doubleValue() - deviceDataItemScaling.getScaledMinValue().doubleValue()) * 100;
                                    if (y <= Double.valueOf(filllevel)) {
                                        added.add(deviceDataItemScaling);
                                    }
                                }

                            }
                            ddis.clear();
                            ddis.addAll(added);
                        } else if (!groupid.equals("") && !objectgroupid.equals("") && !deviceid.equals("") && productvariantid.equals("") && filllevel.equals("")) {
                            List<Ogmdevice> od = deviceDAO.getOgmdeviceById(session, objectgroupid);
                            List<se.info24.pojo.Device> device = deviceDAO.getDeviceByGroup(session, groupid);
                            for (Ogmdevice ogmdevice : od) {
                                for (Device device1 : device) {
                                    if (ogmdevice.getId().getDeviceId().equalsIgnoreCase(device1.getDeviceId()) && device1.getDeviceId().equalsIgnoreCase(deviceid) && ogmdevice.getId().getDeviceId().equalsIgnoreCase(deviceid)) {
                                        deviceIdSet.add(device1.getDeviceId());
                                    }
                                }
                            }
                        } else if (!groupid.equals("") && objectgroupid.equals("") && !deviceid.equals("") && !productvariantid.equals("") && filllevel.equals("")) {
                            List<se.info24.pojo.Device> device = deviceDAO.getDeviceByGroup(session, groupid);
                            for (Device device1 : device) {
                                if (device1.getDeviceId().equalsIgnoreCase(deviceid)) {
                                    deviceIdSet.add(device1.getDeviceId());
                                }
                            }
                            if (!deviceIdSet.isEmpty()) {
                                checkDataScaling = true;
                                List<String> deviceLists = new ArrayList<String>(deviceIdSet);
                                ddis = deviceDAO.getDeviceDataItemScalingByDeviceIdAndVariantId(session, deviceLists, productvariantid);
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                                return tingcoDevice;
                            }

                        } else if (!groupid.equals("") && objectgroupid.equals("") && deviceid.equals("") && !productvariantid.equals("") && !filllevel.equals("")) {
                            List<se.info24.pojo.Device> device = deviceDAO.getDeviceByGroup(session, groupid);
                            for (Device device1 : device) {
                                deviceIdSet.add(device1.getDeviceId());
                            }
                            if (!deviceIdSet.isEmpty()) {
                                checkDataScaling = true;
                                List<String> deviceLists = new ArrayList<String>(deviceIdSet);
                                ddis = deviceDAO.getDeviceDataItemScalingByDeviceIdAndVariantId(session, deviceLists, productvariantid);
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                                return tingcoDevice;
                            }
                            Object dsdio = null;
                            List<DeviceDataItemScaling> added = new ArrayList<DeviceDataItemScaling>();
                            for (DeviceDataItemScaling deviceDataItemScaling : ddis) {
                                dsdio = deviceDAO.getDeviceStatusDataItemsBydeviceDataItemId(sessionoperation, deviceDataItemScaling.getId().getDeviceId(), deviceDataItemScaling.getId().getDeviceDataItemId());
                                if (dsdio != null) {
                                    DeviceStatusDataItems dsdi = (DeviceStatusDataItems) dsdio;
                                    String FillLevel = dsdi.getDeviceDataValue();
                                    double y = (Double.valueOf(FillLevel) - deviceDataItemScaling.getScaledMinValue().doubleValue()) / (deviceDataItemScaling.getScaledMaxValue().doubleValue() - deviceDataItemScaling.getScaledMinValue().doubleValue()) * 100;
                                    if (y <= Double.valueOf(filllevel)) {
                                        added.add(deviceDataItemScaling);
                                    }
                                }

                            }
                            ddis.clear();
                            ddis.addAll(added);
                        } else if (groupid.equals("") && !objectgroupid.equals("") && !deviceid.equals("") && !productvariantid.equals("") && filllevel.equals("")) {
                            List<Ogmdevice> od = deviceDAO.getOgmdeviceById(session, objectgroupid);
                            for (Ogmdevice ogmdevice : od) {
                                if (ogmdevice.getId().getDeviceId().equalsIgnoreCase(deviceid)) {
                                    deviceIdSet.add(ogmdevice.getId().getDeviceId());
                                }
                            }
                            if (!deviceIdSet.isEmpty()) {
                                checkDataScaling = true;
                                List<String> deviceLists = new ArrayList<String>(deviceIdSet);
                                ddis = deviceDAO.getDeviceDataItemScalingByDeviceIdAndVariantId(session, deviceLists, productvariantid);
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                                return tingcoDevice;
                            }


                        } else if (groupid.equals("") && !objectgroupid.equals("") && deviceid.equals("") && !productvariantid.equals("") && !filllevel.equals("")) {
                            List<Ogmdevice> od = deviceDAO.getOgmdeviceById(session, objectgroupid);
                            for (Ogmdevice ogmdevice : od) {
                                deviceIdSet.add(ogmdevice.getId().getDeviceId());
                            }
                            if (!deviceIdSet.isEmpty()) {
                                checkDataScaling = true;
                                List<String> deviceLists = new ArrayList<String>(deviceIdSet);
                                ddis = deviceDAO.getDeviceDataItemScalingByDeviceIdAndVariantId(session, deviceLists, productvariantid);
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                                return tingcoDevice;
                            }
                            Object dsdio = null;
                            List<DeviceDataItemScaling> added = new ArrayList<DeviceDataItemScaling>();
                            for (DeviceDataItemScaling deviceDataItemScaling : ddis) {
                                dsdio = deviceDAO.getDeviceStatusDataItemsBydeviceDataItemId(sessionoperation, deviceDataItemScaling.getId().getDeviceId(), deviceDataItemScaling.getId().getDeviceDataItemId());
                                if (dsdio != null) {
                                    DeviceStatusDataItems dsdi = (DeviceStatusDataItems) dsdio;
                                    String FillLevel = dsdi.getDeviceDataValue();
                                    double y = (Double.valueOf(FillLevel) - deviceDataItemScaling.getScaledMinValue().doubleValue()) / (deviceDataItemScaling.getScaledMaxValue().doubleValue() - deviceDataItemScaling.getScaledMinValue().doubleValue()) * 100;
                                    if (y <= Double.valueOf(filllevel)) {
                                        added.add(deviceDataItemScaling);
                                    }
                                }

                            }
                            ddis.clear();
                            ddis.addAll(added);
                        } else if (groupid.equals("") && objectgroupid.equals("") && !deviceid.equals("") && !productvariantid.equals("") && !filllevel.equals("")) {
                            deviceIdSet.add(deviceid);
                            if (!deviceIdSet.isEmpty()) {
                                checkDataScaling = true;
                                List<String> deviceLists = new ArrayList<String>(deviceIdSet);
                                ddis = deviceDAO.getDeviceDataItemScalingByDeviceIdAndVariantId(session, deviceLists, productvariantid);
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                                return tingcoDevice;
                            }
                            Object dsdio = null;
                            List<DeviceDataItemScaling> added = new ArrayList<DeviceDataItemScaling>();
                            for (DeviceDataItemScaling deviceDataItemScaling : ddis) {
                                dsdio = deviceDAO.getDeviceStatusDataItemsBydeviceDataItemId(sessionoperation, deviceDataItemScaling.getId().getDeviceId(), deviceDataItemScaling.getId().getDeviceDataItemId());
                                if (dsdio != null) {
                                    DeviceStatusDataItems dsdi = (DeviceStatusDataItems) dsdio;
                                    String FillLevel = dsdi.getDeviceDataValue();
                                    double y = (Double.valueOf(FillLevel) - deviceDataItemScaling.getScaledMinValue().doubleValue()) / (deviceDataItemScaling.getScaledMaxValue().doubleValue() - deviceDataItemScaling.getScaledMinValue().doubleValue()) * 100;
                                    if (y <= Double.valueOf(filllevel)) {
                                        added.add(deviceDataItemScaling);
                                    }
                                }

                            }
                            ddis.clear();
                            ddis.addAll(added);
                        } else if (!groupid.equals("") && !objectgroupid.equals("") && deviceid.equals("") && !productvariantid.equals("") && filllevel.equals("")) {
                            List<Ogmdevice> od = deviceDAO.getOgmdeviceById(session, objectgroupid);
                            List<se.info24.pojo.Device> device = deviceDAO.getDeviceByGroup(session, groupid);
                            for (Ogmdevice ogmdevice : od) {
                                for (Device device1 : device) {
                                    if (ogmdevice.getId().getDeviceId().equalsIgnoreCase(device1.getDeviceId())) {
                                        deviceIdSet.add(device1.getDeviceId());
                                    }
                                }
                            }
                            if (!deviceIdSet.isEmpty()) {
                                checkDataScaling = true;
                                List<String> deviceLists = new ArrayList<String>(deviceIdSet);
                                ddis = deviceDAO.getDeviceDataItemScalingByDeviceIdAndVariantId(session, deviceLists, productvariantid);
                                checkDataScaling = true;
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                                return tingcoDevice;
                            }

                        } else if (!groupid.equals("") && !objectgroupid.equals("") && deviceid.equals("") && productvariantid.equals("") && !filllevel.equals("")) {
                            List<Ogmdevice> od = deviceDAO.getOgmdeviceById(session, objectgroupid);
                            List<se.info24.pojo.Device> device = deviceDAO.getDeviceByGroup(session, groupid);
                            for (Ogmdevice ogmdevice : od) {
                                for (Device device1 : device) {
                                    if (ogmdevice.getId().getDeviceId().equalsIgnoreCase(device1.getDeviceId())) {
                                        deviceIdSet.add(device1.getDeviceId());
                                    }
                                }
                            }
                            if (!deviceIdSet.isEmpty()) {
                                checkDataScaling = true;
                                List<String> deviceLists = new ArrayList<String>(deviceIdSet);
                                ddis = deviceDAO.getDeviceDataItemScalingByDeviceId(session, deviceLists);
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                                return tingcoDevice;
                            }
                            Object dsdio = null;
                            List<DeviceDataItemScaling> added = new ArrayList<DeviceDataItemScaling>();
                            for (DeviceDataItemScaling deviceDataItemScaling : ddis) {
                                dsdio = deviceDAO.getDeviceStatusDataItemsBydeviceDataItemId(sessionoperation, deviceDataItemScaling.getId().getDeviceId(), deviceDataItemScaling.getId().getDeviceDataItemId());
                                if (dsdio != null) {
                                    DeviceStatusDataItems dsdi = (DeviceStatusDataItems) dsdio;
                                    String FillLevel = dsdi.getDeviceDataValue();
                                    double y = (Double.valueOf(FillLevel) - deviceDataItemScaling.getScaledMinValue().doubleValue()) / (deviceDataItemScaling.getScaledMaxValue().doubleValue() - deviceDataItemScaling.getScaledMinValue().doubleValue()) * 100;
                                    if (y <= Double.valueOf(filllevel)) {
                                        added.add(deviceDataItemScaling);
                                    }
                                }
                            }
                            ddis.clear();
                            ddis.addAll(added);
                        } else if (groupid.equals("") && !objectgroupid.equals("") && !deviceid.equals("") && productvariantid.equals("") && !filllevel.equals("")) {
                            List<Ogmdevice> od = deviceDAO.getOgmdeviceById(session, objectgroupid);
                            for (Ogmdevice ogmdevice : od) {
                                if (ogmdevice.getId().getDeviceId().equalsIgnoreCase(deviceid)) {
                                    deviceIdSet.add(ogmdevice.getId().getDeviceId());
                                }
                            }
                            if (!deviceIdSet.isEmpty()) {
                                checkDataScaling = true;
                                List<String> deviceLists = new ArrayList<String>(deviceIdSet);
                                ddis = deviceDAO.getDeviceDataItemScalingByDeviceId(session, deviceLists);
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                                return tingcoDevice;
                            }
                            Object dsdio = null;
                            List<DeviceDataItemScaling> added = new ArrayList<DeviceDataItemScaling>();
                            for (DeviceDataItemScaling deviceDataItemScaling : ddis) {
                                dsdio = deviceDAO.getDeviceStatusDataItemsBydeviceDataItemId(sessionoperation, deviceDataItemScaling.getId().getDeviceId(), deviceDataItemScaling.getId().getDeviceDataItemId());
                                if (dsdio != null) {
                                    DeviceStatusDataItems dsdi = (DeviceStatusDataItems) dsdio;
                                    String FillLevel = dsdi.getDeviceDataValue();
                                    double y = (Double.valueOf(FillLevel) - deviceDataItemScaling.getScaledMinValue().doubleValue()) / (deviceDataItemScaling.getScaledMaxValue().doubleValue() - deviceDataItemScaling.getScaledMinValue().doubleValue()) * 100;
                                    if (y <= Double.valueOf(filllevel)) {
                                        added.add(deviceDataItemScaling);
                                    }
                                }

                            }
                            ddis.clear();
                            ddis.addAll(added);
                        } else if (!groupid.equals("") && !objectgroupid.equals("") && !deviceid.equals("") && !productvariantid.equals("") && filllevel.equals("")) {
                            List<Ogmdevice> od = deviceDAO.getOgmdeviceById(session, objectgroupid);
                            List<se.info24.pojo.Device> device = deviceDAO.getDeviceByGroup(session, groupid);
                            for (Ogmdevice ogmdevice : od) {
                                for (Device device1 : device) {
                                    if (ogmdevice.getId().getDeviceId().equalsIgnoreCase(device1.getDeviceId()) && device1.getDeviceId().equalsIgnoreCase(deviceid) && ogmdevice.getId().getDeviceId().equalsIgnoreCase(deviceid)) {
                                        deviceIdSet.add(device1.getDeviceId());
                                    }
                                }
                            }
                            if (!deviceIdSet.isEmpty()) {
                                checkDataScaling = true;
                                List<String> deviceLists = new ArrayList<String>(deviceIdSet);
                                ddis = deviceDAO.getDeviceDataItemScalingByDeviceIdAndVariantId(session, deviceLists, productvariantid);
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                                return tingcoDevice;
                            }

                        } else if (!groupid.equals("") && !objectgroupid.equals("") && !deviceid.equals("") && productvariantid.equals("") && !filllevel.equals("")) {
                            List<Ogmdevice> od = deviceDAO.getOgmdeviceById(session, objectgroupid);
                            List<se.info24.pojo.Device> device = deviceDAO.getDeviceByGroup(session, groupid);
                            for (Ogmdevice ogmdevice : od) {
                                for (Device device1 : device) {
                                    if (ogmdevice.getId().getDeviceId().equalsIgnoreCase(device1.getDeviceId()) && device1.getDeviceId().equalsIgnoreCase(deviceid) && ogmdevice.getId().getDeviceId().equalsIgnoreCase(deviceid)) {
                                        deviceIdSet.add(device1.getDeviceId());
                                    }
                                }
                            }
                            if (!deviceIdSet.isEmpty()) {
                                checkDataScaling = true;
                                List<String> deviceLists = new ArrayList<String>(deviceIdSet);
                                ddis = deviceDAO.getDeviceDataItemScalingByDeviceId(session, deviceLists);
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                                return tingcoDevice;
                            }
                            Object dsdio = null;
                            List<DeviceDataItemScaling> added = new ArrayList<DeviceDataItemScaling>();
                            for (DeviceDataItemScaling deviceDataItemScaling : ddis) {
                                dsdio = deviceDAO.getDeviceStatusDataItemsBydeviceDataItemId(sessionoperation, deviceDataItemScaling.getId().getDeviceId(), deviceDataItemScaling.getId().getDeviceDataItemId());
                                if (dsdio != null) {
                                    DeviceStatusDataItems dsdi = (DeviceStatusDataItems) dsdio;
                                    String FillLevel = dsdi.getDeviceDataValue();
                                    double y = (Double.valueOf(FillLevel) - deviceDataItemScaling.getScaledMinValue().doubleValue()) / (deviceDataItemScaling.getScaledMaxValue().doubleValue() - deviceDataItemScaling.getScaledMinValue().doubleValue()) * 100;
                                    if (y <= Double.valueOf(filllevel)) {
                                        added.add(deviceDataItemScaling);
                                    }
                                }
                            }
                            ddis.clear();
                            ddis.addAll(added);
                        } else if (groupid.equals("") && !objectgroupid.equals("") && !deviceid.equals("") && !productvariantid.equals("") && !filllevel.equals("")) {
                            List<Ogmdevice> od = deviceDAO.getOgmdeviceById(session, objectgroupid);
                            for (Ogmdevice ogmdevice : od) {
                                if (ogmdevice.getId().getDeviceId().equalsIgnoreCase(deviceid)) {
                                    deviceIdSet.add(ogmdevice.getId().getDeviceId());
                                }
                            }
                            if (!deviceIdSet.isEmpty()) {
                                checkDataScaling = true;
                                List<String> deviceLists = new ArrayList<String>(deviceIdSet);
                                ddis = deviceDAO.getDeviceDataItemScalingByDeviceIdAndVariantId(session, deviceLists, productvariantid);
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                                return tingcoDevice;
                            }
                            Object dsdio = null;
                            List<DeviceDataItemScaling> added = new ArrayList<DeviceDataItemScaling>();
                            for (DeviceDataItemScaling deviceDataItemScaling : ddis) {
                                dsdio = deviceDAO.getDeviceStatusDataItemsBydeviceDataItemId(sessionoperation, deviceDataItemScaling.getId().getDeviceId(), deviceDataItemScaling.getId().getDeviceDataItemId());
                                if (dsdio != null) {
                                    DeviceStatusDataItems dsdi = (DeviceStatusDataItems) dsdio;
                                    String FillLevel = dsdi.getDeviceDataValue();
                                    double y = (Double.valueOf(FillLevel) - deviceDataItemScaling.getScaledMinValue().doubleValue()) / (deviceDataItemScaling.getScaledMaxValue().doubleValue() - deviceDataItemScaling.getScaledMinValue().doubleValue()) * 100;
                                    if (y <= Double.valueOf(filllevel)) {
                                        added.add(deviceDataItemScaling);
                                    }
                                }
                            }
                            ddis.clear();
                            ddis.addAll(added);
                        } else if (!groupid.equals("") && objectgroupid.equals("") && !deviceid.equals("") && !productvariantid.equals("") && !filllevel.equals("")) {
                            List<se.info24.pojo.Device> device = deviceDAO.getDeviceByGroup(session, groupid);
                            for (Device device1 : device) {
                                if (device1.getDeviceId().equalsIgnoreCase(deviceid)) {
                                    deviceIdSet.add(device1.getDeviceId());
                                }
                            }
                            if (!deviceIdSet.isEmpty()) {
                                checkDataScaling = true;
                                List<String> deviceLists = new ArrayList<String>(deviceIdSet);
                                ddis = deviceDAO.getDeviceDataItemScalingByDeviceIdAndVariantId(session, deviceLists, productvariantid);
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                                return tingcoDevice;
                            }
                            Object dsdio = null;
                            List<DeviceDataItemScaling> added = new ArrayList<DeviceDataItemScaling>();
                            for (DeviceDataItemScaling deviceDataItemScaling : ddis) {
                                dsdio = deviceDAO.getDeviceStatusDataItemsBydeviceDataItemId(sessionoperation, deviceDataItemScaling.getId().getDeviceId(), deviceDataItemScaling.getId().getDeviceDataItemId());
                                if (dsdio != null) {
                                    DeviceStatusDataItems dsdi = (DeviceStatusDataItems) dsdio;
                                    String FillLevel = dsdi.getDeviceDataValue();
                                    double y = (Double.valueOf(FillLevel) - deviceDataItemScaling.getScaledMinValue().doubleValue()) / (deviceDataItemScaling.getScaledMaxValue().doubleValue() - deviceDataItemScaling.getScaledMinValue().doubleValue()) * 100;
                                    if (y <= Double.valueOf(filllevel)) {
                                        added.add(deviceDataItemScaling);
                                    }
                                }

                            }
                            ddis.clear();
                            ddis.addAll(added);
                        } else if (!groupid.equals("") && !objectgroupid.equals("") && !deviceid.equals("") && !productvariantid.equals("") && !filllevel.equals("")) {
                            List<Ogmdevice> od = deviceDAO.getOgmdeviceById(session, objectgroupid);
                            List<se.info24.pojo.Device> device = deviceDAO.getDeviceByGroup(session, groupid);
                            for (Ogmdevice ogmdevice : od) {
                                for (Device device1 : device) {
                                    if (ogmdevice.getId().getDeviceId().equalsIgnoreCase(device1.getDeviceId()) && device1.getDeviceId().equalsIgnoreCase(deviceid) && ogmdevice.getId().getDeviceId().equalsIgnoreCase(deviceid)) {
                                        deviceIdSet.add(device1.getDeviceId());
                                    }
                                }
                            }
                            if (!deviceIdSet.isEmpty()) {
                                checkDataScaling = true;
                                List<String> deviceLists = new ArrayList<String>(deviceIdSet);
                                ddis = deviceDAO.getDeviceDataItemScalingByDeviceIdAndVariantId(session, deviceLists, productvariantid);
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                                return tingcoDevice;
                            }


                            Object dsdio = null;
                            List<DeviceDataItemScaling> added = new ArrayList<DeviceDataItemScaling>();
                            for (DeviceDataItemScaling deviceDataItemScaling : ddis) {
                                dsdio = deviceDAO.getDeviceStatusDataItemsBydeviceDataItemId(sessionoperation, deviceDataItemScaling.getId().getDeviceId(), deviceDataItemScaling.getId().getDeviceDataItemId());
                                if (dsdio != null) {
                                    DeviceStatusDataItems dsdi = (DeviceStatusDataItems) dsdio;
                                    String FillLevel = dsdi.getDeviceDataValue();
                                    double y = (Double.valueOf(FillLevel) - deviceDataItemScaling.getScaledMinValue().doubleValue()) / (deviceDataItemScaling.getScaledMaxValue().doubleValue() - deviceDataItemScaling.getScaledMinValue().doubleValue()) * 100;
                                    if (y <= Double.valueOf(filllevel)) {
                                        added.add(deviceDataItemScaling);
                                    }
                                }
                            }
                            ddis.clear();
                            ddis.addAll(added);
                        } else if (groupid.equals("") && objectgroupid.equals("") && deviceid.equals("") && productvariantid.equals("") && filllevel.equals("")) {
                            ddis = deviceDAO.getAllDeviceDataItemScaling(session);
                        }
                        Hashtable<String, String> ht = new Hashtable<String, String>();
                        if (ddis.isEmpty() && !checkDataScaling) {
                            if (!deviceIdSet.isEmpty()) {
                                List<String> deviceList = new ArrayList<String>(deviceIdSet);
                                ddis = deviceDAO.getDeviceDataItemScalingByDeviceId(session, deviceList);
                                if (ddis.isEmpty()) {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("ddis not Found");
                                    return tingcoDevice;
                                }
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("else DeviceID not Found");
                                return tingcoDevice;
                            }
                        }
//            List<ProductVariantTranslations> pvt  = new ArrayList<ProductVariantTranslations>();
                        List<String> ProductVariantId = new ArrayList<String>();
                        DeviceDataitemTranslations ddit = null;
                        List<DeviceStatusDataItems> deviceStatusDataItemsList = new ArrayList<DeviceStatusDataItems>();
                        if (!ddis.isEmpty()) {
                            Object dsdio = null;
                            for (DeviceDataItemScaling deviceDataItemScaling : ddis) {
                                if (deviceDataItemScaling.getProductVariantId() != null) {
                                    ProductVariantId.add(deviceDataItemScaling.getProductVariantId());
                                }
                                deviceIdSet.add(deviceDataItemScaling.getId().getDeviceId());
                                dsdio = deviceDAO.getDeviceStatusDataItemsBydeviceDataItemId(sessionoperation, deviceDataItemScaling.getId().getDeviceId(), deviceDataItemScaling.getId().getDeviceDataItemId());
                                if (dsdio != null) {
                                    DeviceStatusDataItems dsdi = (DeviceStatusDataItems) dsdio;
                                    deviceStatusDataItemsList.add(dsdi);
                                }
                                DataItemTranslationsPerDevice ditpd = deviceDAO.getDataItemTranslationsPerDevice(deviceDataItemScaling.getId().getDeviceId(), deviceDataItemScaling.getId().getDeviceDataItemId(), Integer.valueOf(countryid), session);
                                if (ditpd == null) {
                                    ddit = deviceDAO.getDeviceDataItemTranslationsById(session, deviceDataItemScaling.getId().getDeviceDataItemId(), countryid);
                                    if (ddit != null) {
                                        ht.put(deviceDataItemScaling.getId().getDeviceDataItemId(), ddit.getDataItemName());
                                    }/* else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("Data not Found");
                                return tingcoDevice;
                                }*/

                                } else {
                                    ht.put(deviceDataItemScaling.getId().getDeviceDataItemId(), ditpd.getDataItemName());
                                }
                            }
                            List<ProductVariantTranslations> pvt = new ArrayList<ProductVariantTranslations>();
                            if (!ProductVariantId.isEmpty()) {
                                pvt = deviceDAO.getProductVariantTranslations(session, ProductVariantId, countryid, 0);
                            }

                            List<String> deviceList = new ArrayList<String>(deviceIdSet);
                            List<Device> device = deviceDAO.getDeviceByDeviceIdsList(session, deviceList);

                            tingcoDevice = tingcoDeviceXML.buildDeviceLevelDetails(tingcoDevice, ddis, ht, pvt, deviceStatusDataItemsList, countryid, device, timeZoneGMToffset);
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("if ddis DeviceID not Found");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("UserTimezone not found");
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
        } finally {
            if (session != null) {
                session.close();
            }
            if (sessionoperation != null) {
                sessionoperation.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}

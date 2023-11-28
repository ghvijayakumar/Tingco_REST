/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.device;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.devicejaxb.TingcoDevice;
import se.info24.jaxbUtil.TingcoDeviceXML;
import se.info24.pojo.Device;
import se.info24.pojo.ProductVariantDevices;
import se.info24.pojo.ProductVariantDevicesId;
import se.info24.pojo.ProductVariants;
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
 * @author Vijayakumar
 */
@Path("/deviceproductvariants")
public class DeviceProductVariantssResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
    DeviceDAO deviceDAO = new DeviceDAO();
    SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of DeviceProductVariantssResource */
    public DeviceProductVariantssResource() {
    }

    /**
     * Retrieves representation of an instance of se.info24.device.DeviceProductVariantssResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * AddProductVariantForDevice
     * @param deviceId
     * @param productVariantId
     * @param isEnabled
     * @param activeFromDate
     * @param activeToDate
     * @param displayOrder
     * @param bay
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/addproductvariantfordevice/rest/{rest}/deviceid/{deviceid}/productvariantid/{productvariantid}/isenabled/{isenabled}/activefromdate/{activefromdate}/activetodate/{activetodate}/displayorder/{displayorder}/bay/{bay}{isdefault:(/isdefault/[^/]+?)?}")
    @RESTDoc(methodName = "AddProductVariantForDevice", desc = "Used to Add ProductVariantDevices", req_Params = {"DeviceID;UUID", "ProductVariantId;UUID", "IsEnabled;int", "ActiveFromDate;string", "ActiveToDate;string", "DisplayOrder;int", "Bay;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_addProductVariantForDevice(@PathParam("deviceid") String deviceId, @PathParam("productvariantid") String productVariantId, @PathParam("isenabled") int isEnabled, @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate, @PathParam("displayorder") int displayOrder, @PathParam("bay") int bay, @PathParam("isdefault") String isDefault) {
        return addProductVariantForDevice(deviceId, productVariantId, isEnabled, activeFromDate, activeToDate, displayOrder, bay, isDefault);
    }

    /**
     * AddProductVariantForDevice
     * @param deviceId
     * @param productVariantId
     * @param isEnabled
     * @param activeFromDate
     * @param activeToDate
     * @param displayOrder
     * @param bay
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/addproductvariantfordevice/rest/{rest}/deviceid/{deviceid}/productvariantid/{productvariantid}/isenabled/{isenabled}/activefromdate/{activefromdate}/activetodate/{activetodate}/displayorder/{displayorder}/bay/{bay}{isdefault:(/isdefault/[^/]+?)?}")
    public TingcoDevice getJson_addProductVariantForDevice(@PathParam("deviceid") String deviceId, @PathParam("productvariantid") String productVariantId, @PathParam("isenabled") int isEnabled, @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate, @PathParam("displayorder") int displayOrder, @PathParam("bay") int bay, @PathParam("isdefault") String isDefault) {
        return addProductVariantForDevice(deviceId, productVariantId, isEnabled, activeFromDate, activeToDate, displayOrder, bay, isDefault);
    }

    /**
     * UpdateProductVariantForDevice
     * @param deviceId
     * @param productVariantId
     * @param isEnabled
     * @param activeFromDate
     * @param activeToDate
     * @param displayOrder
     * @param bay
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/updateproductvariantfordevice/rest/{rest}/deviceid/{deviceid}/productvariantid/{productvariantid}/isenabled/{isenabled}/activefromdate/{activefromdate}/activetodate/{activetodate}/displayorder/{displayorder}/bay/{bay}{isdefault:(/isdefault/[^/]+?)?}")
    @RESTDoc(methodName = "UpdateProductVariantForDevice", desc = "Used to Update ProductVariantForDevice", req_Params = {"DeviceID;UUID", "ProductVariantId;UUID", "IsEnabled;int", "ActiveFromDate;string", "ActiveToDate;string", "DisplayOrder;int", "Bay;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_updateProductVariantForDevice(@PathParam("deviceid") String deviceId, @PathParam("productvariantid") String productVariantId, @PathParam("isenabled") int isEnabled, @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate, @PathParam("displayorder") int displayOrder, @PathParam("bay") int bay, @PathParam("isdefault") String isDefault) {
        return updateProductVariantForDevice(deviceId, productVariantId, isEnabled, activeFromDate, activeToDate, displayOrder, bay, isDefault);
    }

    /**
     * UpdateProductVariantForDevice
     * @param deviceId
     * @param productVariantId
     * @param isEnabled
     * @param activeFromDate
     * @param activeToDate
     * @param displayOrder
     * @param bay
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/updateproductvariantfordevice/rest/{rest}/deviceid/{deviceid}/productvariantid/{productvariantid}/isenabled/{isenabled}/activefromdate/{activefromdate}/activetodate/{activetodate}/displayorder/{displayorder}/bay/{bay}{isdefault:(/isdefault/[^/]+?)?}")
    public TingcoDevice getJson_updateProductVariantForDevice(@PathParam("deviceid") String deviceId, @PathParam("productvariantid") String productVariantId, @PathParam("isenabled") int isEnabled, @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate, @PathParam("displayorder") int displayOrder, @PathParam("bay") int bay, @PathParam("isdefault") String isDefault) {
        return updateProductVariantForDevice(deviceId, productVariantId, isEnabled, activeFromDate, activeToDate, displayOrder, bay, isDefault);
    }

    /**
     * DeleteProductVariantForDevice
     * @param deviceId
     * @param productVariantId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deleteproductvariantfordevice/rest/{rest}/deviceid/{deviceid}/productvariantid/{productvariantid}")
    @RESTDoc(methodName = "DeleteProductVariantForDevice", desc = "Used to Delete ProductVariantForDevice", req_Params = {"DeviceID;UUID", "ProductVariantId;UUID", "IsEnabled;int", "ActiveFromDate;string", "ActiveToDate;string", "DisplayOrder;int", "Bay;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_deleteProductVariantForDevice(@PathParam("deviceid") String deviceId, @PathParam("productvariantid") String productVariantId) {
        return deleteProductVariantForDevice(deviceId, productVariantId);
    }

    /**
     * DeleteProductVariantForDevice
     * @param deviceId
     * @param productVariantId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deleteproductvariantfordevice/rest/{rest}/deviceid/{deviceid}/productvariantid/{productvariantid}")
    public TingcoDevice getJson_deleteProductVariantForDevice(@PathParam("deviceid") String deviceId, @PathParam("productvariantid") String productVariantId) {
        return deleteProductVariantForDevice(deviceId, productVariantId);
    }

    /**
     * POST method for creating an instance of DeviceProductVariantssResource
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
    public DeviceProductVariantsResource getDeviceProductVariantsResource() {
        return new DeviceProductVariantsResource();
    }

    private TingcoDevice addProductVariantForDevice(String deviceId, String productVariantId, int isEnabled, String activeFromDate, String activeToDate, int displayOrder, int bay, String isDefault) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        TingcoDevice tingcoDevice = null;
        Transaction tx = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceId should not be empty");
                    return tingcoDevice;
                }
                if (productVariantId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("productVariantId should not be empty");
                    return tingcoDevice;
                }
                if (isEnabled < 0) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("isEnabled should not be empty");
                    return tingcoDevice;
                }
                if (activeFromDate.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("activeFromDate should not be empty");
                    return tingcoDevice;
                }
                if (activeToDate.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("activeToDate should not be empty");
                    return tingcoDevice;
                }
                if (displayOrder < 0) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("displayOrder should not be empty");
                    return tingcoDevice;
                }

                if (bay < 0) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("bay should not be empty");
                    return tingcoDevice;
                }
                if (!isDefault.equals("")) {
                    isDefault = isDefault.split("/")[2];
                } else {
                    isDefault = null;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        ProductVariantDevices productVariantDevices = new ProductVariantDevices();
                        productVariantDevices.setId(new ProductVariantDevicesId(productVariantId, deviceId));
                        ProductVariants productVariants = new ProductVariants();
                        productVariants.setProductVariantId(productVariantId);
                        productVariantDevices.setProductVariants(productVariants);
                        Device device = new Device();
                        device.setDeviceId(deviceId);
                        productVariantDevices.setDevice(device);
                        productVariantDevices.setIsEnabled(isEnabled);
                        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                        productVariantDevices.setDisplayOrder(displayOrder);
                        productVariantDevices.setBayNumber(bay);
                        productVariantDevices.setUserId(sessions2.getUserId());
                        productVariantDevices.setCreatedDate(gc.getTime());
                        productVariantDevices.setUpdatedDate(gc.getTime());
                        gc.setTime(lFormat.parse(activeFromDate));
                        gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
//                    gc.setTimeInMillis(Long.valueOf(activeFromDate));
                        productVariantDevices.setActiveFromDate(gc.getTime());
                        gc.setTime(lFormat.parse(activeToDate));
                        gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
//                    gc.setTimeInMillis(Long.valueOf(activeToDate));
                        productVariantDevices.setActiveToDate(gc.getTime());
                        if (isDefault != null) {
                            productVariantDevices.setIsDefault(Integer.parseInt(isDefault));
                        } else {
                            productVariantDevices.setIsDefault(0);
                        }
                        if (deviceDAO.saveProductVariantDevices(productVariantDevices, session)) {
                            tx.commit();
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error Occured while adding ProductVariantDevices");
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
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            if (tx.wasCommitted()) {
                tx.rollback();
            }
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice deleteProductVariantForDevice(String deviceId, String productVariantId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        TingcoDevice tingcoDevice = null;
        Transaction tx = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceId should not be empty");
                    return tingcoDevice;
                }
                if (productVariantId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("productVariantId should not be empty");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    ProductVariantDevices productVariantDevices = deviceDAO.getProductVariantDevices(deviceId, productVariantId, session);
                    if (productVariantDevices != null) {
                        if (deviceDAO.deleteProductVariantDevices(productVariantDevices, session)) {
                            tx.commit();
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error Occured while deleting ProductVariantDevices");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No ProductVariant found for this device");
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
            if (tx.wasCommitted()) {
                tx.rollback();
            }
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice updateProductVariantForDevice(String deviceId, String productVariantId, int isEnabled, String activeFromDate, String activeToDate, int displayOrder, int bay, String isDefault) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        TingcoDevice tingcoDevice = null;
        Transaction tx = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceId should not be empty");
                    return tingcoDevice;
                }
                if (productVariantId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("productVariantId should not be empty");
                    return tingcoDevice;
                }
                if (isEnabled < 0) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("isEnabled should not be empty");
                    return tingcoDevice;
                }
                if (activeFromDate.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("activeFromDate should not be empty");
                    return tingcoDevice;
                }
                if (activeToDate.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("activeToDate should not be empty");
                    return tingcoDevice;
                }
                if (displayOrder < 0) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("displayOrder should not be empty");
                    return tingcoDevice;
                }

                if (bay < 0) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("bay should not be empty");
                    return tingcoDevice;
                }
                if (!isDefault.equals("")) {
                    isDefault = isDefault.split("/")[2];
                } else {
                    isDefault = null;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        ProductVariantDevices productVariantDevices = deviceDAO.getProductVariantDevices(deviceId, productVariantId, session);
                        if (productVariantDevices != null) {
                            productVariantDevices.setIsEnabled(isEnabled);
                            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                            productVariantDevices.setDisplayOrder(displayOrder);
                            productVariantDevices.setBayNumber(bay);
                            productVariantDevices.setUserId(sessions2.getUserId());
                            productVariantDevices.setCreatedDate(gc.getTime());
                            productVariantDevices.setUpdatedDate(gc.getTime());
                            gc.setTime(lFormat.parse(activeFromDate));
                            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                            productVariantDevices.setActiveFromDate(gc.getTime());
                            
                            gc.setTime(lFormat.parse(activeToDate));
                            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                            productVariantDevices.setActiveToDate(gc.getTime());

                            if(isDefault != null){
                                productVariantDevices.setIsDefault(Integer.parseInt(isDefault));
                            }else{
                                productVariantDevices.setIsDefault(0);
                            }
                            if (deviceDAO.saveProductVariantDevices(productVariantDevices, session)) {
                                tx.commit();
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("Error Occured while updating ProductVariantDevices");
                                return tingcoDevice;
                            }
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("No ProductVariant found for this device");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
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
            if (tx.wasCommitted()) {
                tx.rollback();
            }
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}

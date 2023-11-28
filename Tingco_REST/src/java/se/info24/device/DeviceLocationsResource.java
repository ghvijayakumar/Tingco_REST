/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.device;

import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import org.hibernate.Session;
import se.info24.devicejaxb.TingcoDevice;
import se.info24.devicejaxbPost.Device;
import se.info24.devicejaxbPost.DeviceAddress;
import se.info24.jaxbUtil.TingcoDeviceXML;
import se.info24.pojo.AddressType;
import se.info24.pojo.Addresses;
import se.info24.pojo.Country;
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
@Path("/devicelocation")
public class DeviceLocationsResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
    DeviceDAO deviceDAO = new DeviceDAO();
//    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of DeviceLocationsResource */
    public DeviceLocationsResource() {
    }

    /**
     * Retrieves representation of an instance of se.info24.device.DeviceLocationsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * DeviceLocation_Add
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/add/rest/{rest}")
    @RESTDoc(methodName = "DeviceLocation_Add", desc = "Used to create a new Device Location", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_Add(String content) {
        return add(content);
    }

    /**
     * DeviceLocation_Add
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/add/json/{json}")
    public TingcoDevice postJson_Add(String content) {
        return add(content);
    }

    /**
     * DeviceLocation_Update
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/update/rest/{rest}")
    @RESTDoc(methodName = "DeviceLocation_Update", desc = "Used to update a Device Location", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_Update(String content) {
        return update(content);
    }

    /**
     * DeviceLocation_Update
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/update/json/{json}")
    public TingcoDevice postJson_Update(String content) {
        return update(content);
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public DeviceLocationResource getDeviceLocationResource() {
        return new DeviceLocationResource();
    }

    public TingcoDevice add(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {

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
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                    session = HibernateUtil.getSessionFactory().openSession();
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    if (devices.getDevices().getDevice().size() > 0) {
                        Device deviceXML = devices.getDevices().getDevice().get(0);
                        if (deviceXML != null) {
                            se.info24.pojo.Device dev = deviceDAO.getDeviceById(deviceXML.getDeviceID(), session);
                            if (dev != null) {
                                if (deviceXML.getDeviceAddress() != null) {
                                    DeviceAddress da = deviceXML.getDeviceAddress();
                                    Addresses add = new Addresses();
                                    add.setAddressId(UUID.randomUUID().toString());
                                    if (da.getAddressCity() != null) {
                                        add.setAddressCity(da.getAddressCity());
                                    }
                                    if (da.getAddressDepth() != null) {
                                        add.setAddressDepth(Double.valueOf(da.getAddressDepth()));
                                    }
                                    if (da.getAddressDescription() != null) {
                                        add.setAddressDescription(da.getAddressDescription());
                                    }
                                    if (da.getAddressEmail() != null) {
                                        add.setAddressEmail(da.getAddressEmail());
                                    }
                                    if (da.getAddressFax() != null) {
                                        add.setAddressFax(da.getAddressFax());
                                    }
                                    if (da.getAddressIM() != null) {
                                        add.setAddressIm(da.getAddressIM());
                                    }
                                    if (da.getAddressLatitude() != null) {
                                        add.setAddressLatitude(Double.valueOf(da.getAddressLatitude()));
                                    }
                                    if (da.getAddressLongitude() != null) {
                                        add.setAddressLongitude(Double.valueOf(da.getAddressLongitude()));
                                    }
                                    if (da.getAddressMobile() != null) {
                                        add.setAddressMobile(da.getAddressMobile());
                                    }
                                    if (da.getAddressName() != null) {
                                        add.setAddressName(da.getAddressName());
                                    }
                                    if (da.getAddressPhone() != null) {
                                        add.setAddressPhone(da.getAddressPhone());
                                    }
                                    if (da.getAddressRegion() != null) {
                                        add.setAddressRegion(da.getAddressRegion());
                                    }
                                    if (da.getAddressStreet() != null) {
                                        add.setAddressStreet(da.getAddressStreet());
                                    }
                                    if (da.getAddressStreet2() != null) {
                                        add.setAddressStreet2(da.getAddressStreet2());
                                    }
                                    if (da.getAddressSuite() != null) {
                                        add.setAddressSuite(da.getAddressSuite());
                                    }
                                    if (da.getAddressTypeID() != null) {
                                        AddressType at = new AddressType();
                                        at.setAddressTypeId(Integer.valueOf(da.getAddressTypeID().getValue()));
                                        add.setAddressType(at);
                                    }
                                    if (da.getAddressWeb() != null) {
                                        add.setAddressWeb(da.getAddressWeb());
                                    }
                                    if (da.getAddressZip() != null) {
                                        add.setAddressZip(da.getAddressZip());
                                    }
                                    if (da.getCountryID() != null) {
                                        add.setCountry(new Country(da.getCountryID().getValue()));
                                    }
                                    if (da.getCountryStateID() != null) {
                                        add.setCountryStateId(da.getCountryStateID());
                                    }
                                    add.setUserId(sessions2.getUserId());
                                    add.setCreatedDate(gc.getTime());
                                    add.setUpdatedDate(gc.getTime());

                                    dev.setAddresses(add);
                                    if (add.getCountry() == null || add.getAddressType() == null) {
                                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                                        tingcoDevice.getMsgStatus().setResponseText("Country and AddressType Should not be empty");
                                        return tingcoDevice;
                                    }
                                    if (!deviceDAO.saveDevice(dev, session)) {
                                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                                        tingcoDevice.getMsgStatus().setResponseText("Error Occured while Saving DeviceLocation");
                                        return tingcoDevice;
                                    }
                                } else {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("Invalid XML Found.");
                                    return tingcoDevice;
                                }
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("Device Not Found with Given DeviceID");
                                return tingcoDevice;
                            }
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Invalid Device XML Found");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Invalid Device XML Found");
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

    public TingcoDevice update(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {

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
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                    session = HibernateUtil.getSessionFactory().openSession();
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    if (devices.getDevices().getDevice().size() > 0) {
                        Device deviceXML = devices.getDevices().getDevice().get(0);
                        if (deviceXML != null) {
                            se.info24.pojo.Device dev = deviceDAO.getDeviceById(deviceXML.getDeviceID(), session);
                            if (dev != null) {
                                if (deviceXML.getDeviceAddress() != null) {
                                    DeviceAddress da = deviceXML.getDeviceAddress();

                                    Addresses add = dev.getAddresses();
                                    if (add == null) {
                                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                                        tingcoDevice.getMsgStatus().setResponseText("Address is Not Exists For The Device");
                                        return tingcoDevice;
                                    }
                                    if (da.getAddressCity() != null) {
                                        add.setAddressCity(da.getAddressCity());
                                    }
                                    if (da.getAddressDepth() != null) {
                                        add.setAddressDepth(Double.valueOf(da.getAddressDepth()));
                                    }
                                    if (da.getAddressDescription() != null) {
                                        add.setAddressDescription(da.getAddressDescription());
                                    }
                                    if (da.getAddressEmail() != null) {
                                        add.setAddressEmail(da.getAddressEmail());
                                    }
                                    if (da.getAddressFax() != null) {
                                        add.setAddressFax(da.getAddressFax());
                                    }
                                    if (da.getAddressIM() != null) {
                                        add.setAddressIm(da.getAddressIM());
                                    }
                                    if (da.getAddressLatitude() != null) {
                                        add.setAddressLatitude(Double.valueOf(da.getAddressLatitude()));
                                    }
                                    if (da.getAddressLongitude() != null) {
                                        add.setAddressLongitude(Double.valueOf(da.getAddressLongitude()));
                                    }
                                    if (da.getAddressMobile() != null) {
                                        add.setAddressMobile(da.getAddressMobile());
                                    }
                                    if (da.getAddressName() != null) {
                                        add.setAddressName(da.getAddressName());
                                    }
                                    if (da.getAddressPhone() != null) {
                                        add.setAddressPhone(da.getAddressPhone());
                                    }
                                    if (da.getAddressRegion() != null) {
                                        add.setAddressRegion(da.getAddressRegion());
                                    }
                                    if (da.getAddressStreet() != null) {
                                        add.setAddressStreet(da.getAddressStreet());
                                    }
                                    if (da.getAddressStreet2() != null) {
                                        add.setAddressStreet2(da.getAddressStreet2());
                                    }
                                    if (da.getAddressSuite() != null) {
                                        add.setAddressSuite(da.getAddressSuite());
                                    }
                                    if (da.getAddressTypeID() != null) {
                                        AddressType at = new AddressType();
                                        at.setAddressTypeId(Integer.valueOf(da.getAddressTypeID().getValue()));
                                        add.setAddressType(at);
                                    }
                                    if (da.getAddressWeb() != null) {
                                        add.setAddressWeb(da.getAddressWeb());
                                    }
                                    if (da.getAddressZip() != null) {
                                        add.setAddressZip(da.getAddressZip());
                                    }
                                    if (da.getCountryID() != null) {
                                        add.setCountry(new Country(da.getCountryID().getValue()));
                                    }
                                    if (da.getCountryStateID() != null) {
                                        add.setCountryStateId(da.getCountryStateID());
                                    }
                                    add.setUserId(sessions2.getUserId());
                                    add.setCreatedDate(gc.getTime());
                                    add.setUpdatedDate(gc.getTime());

                                    dev.setAddresses(add);
                                    if (add.getCountry() == null || add.getAddressType() == null) {
                                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                                        tingcoDevice.getMsgStatus().setResponseText("Country and AddressType Should not be empty");
                                        return tingcoDevice;
                                    }
                                    if (deviceDAO.saveDevice(dev, session)) {
                                        return tingcoDevice;
                                    } else {
                                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                                        tingcoDevice.getMsgStatus().setResponseText("Error Occured while Updating DeviceLocation");
                                        return tingcoDevice;
                                    }
                                } else {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("Invalid XML Found.");
                                    return tingcoDevice;
                                }
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("Device Not Found with Given DeviceID");
                                return tingcoDevice;
                            }
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Invalid Device XML Found");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Invalid Device XML Found");
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

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}

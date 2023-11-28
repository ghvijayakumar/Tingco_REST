/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.device;

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
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import org.hibernate.Session;
import se.info24.devicejaxb.TingcoDevice;
import se.info24.jaxbUtil.TingcoDeviceXML;
import se.info24.pojo.DeviceManufacturers;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.Users2;
import se.info24.user.UserDAO;
import se.info24.user.User_LoginsResource;
import se.info24.util.RESTDoc;
import se.info24.util.HibernateUtil;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/devicemanufacturers")
public class DeviceManufacturersResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
    DeviceDAO deviceDAO = new DeviceDAO();
    UserDAO userDAO = new UserDAO();
//    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of DeviceManufacturersResource */
    public DeviceManufacturersResource() {
    }

    /**
     * DeviceManfacturers_Add
     * @param manufacturerName
     * @param manufacturerDesc
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/add/rest/{rest}/manufacturername/{manufacturername}/manufacturerdesc/{manufacturerdesc}")
    @RESTDoc(methodName = "DeviceManfacturers_Add", desc = "Used to create a new DeviceManfacturer", req_Params = {"ManufacturerName;String", "ManufacturerDesc;String"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoDevice getXml_Add(@PathParam("manufacturername") String manufacturerName, @PathParam("manufacturerdesc") String manufacturerDesc) {
        return createDeviceManfcturer(manufacturerName, manufacturerDesc);
    }

    /**
     * DeviceManfacturers_Add
     * @param manufacturerName
     * @param manufacturerDesc
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/add/rest/{rest}/manufacturername/{manufacturername}/manufacturerdesc/{manufacturerdesc}")
    public TingcoDevice postXml_Add(@PathParam("manufacturername") String manufacturerName, @PathParam("manufacturerdesc") String manufacturerDesc) {
        return createDeviceManfcturer(manufacturerName, manufacturerDesc);
    }

    /**
     * DeviceManfacturers_Add
     * @param manufacturerName
     * @param manufacturerDesc
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/add/json/{json}/manufacturername/{manufacturername}/manufacturerdesc/{manufacturerdesc}")
    public TingcoDevice getJson_Add(@PathParam("manufacturername") String manufacturerName, @PathParam("manufacturerdesc") String manufacturerDesc) {
        return createDeviceManfcturer(manufacturerName, manufacturerDesc);
    }

    /**
     * DeviceManfacturers_Add
     * @param manufacturerName
     * @param manufacturerDesc
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/add/json/{json}/manufacturername/{manufacturername}/manufacturerdesc/{manufacturerdesc}")
    public TingcoDevice postJson_Add(@PathParam("manufacturername") String manufacturerName, @PathParam("manufacturerdesc") String manufacturerDesc) {
        return createDeviceManfcturer(manufacturerName, manufacturerDesc);
    }

    /**
     * DeviceManfacturers_Delete
     * @param deviceManufacturerID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/devicemanufacturerid/{devicemanufacturerid}")
    @RESTDoc(methodName = "DeviceManfacturers_Delete", desc = "To Delete a DeviceManfacturers", req_Params = {"DeviceManufacturerID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoDevice getXml_Delete(@PathParam("devicemanufacturerid") String deviceManufacturerID) {
        return deleteDeviceManfacturer(deviceManufacturerID);
    }

    /**
     * DeviceManfacturers_Delete
     * @param deviceManufacturerID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/devicemanufacturerid/{devicemanufacturerid}")
    public TingcoDevice postXml_Delete(@PathParam("devicemanufacturerid") String deviceManufacturerID) {
        return deleteDeviceManfacturer(deviceManufacturerID);
    }

    /**
     * DeviceManfacturers_Delete
     * @param deviceManufacturerID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/delete/json/{json}/devicemanufacturerid/{devicemanufacturerid}")
    public TingcoDevice getJSON_Delete(@PathParam("devicemanufacturerid") String deviceManufacturerID) {
        return deleteDeviceManfacturer(deviceManufacturerID);
    }

    /**
     * DeviceManfacturers_Delete
     * @param deviceManufacturerID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/delete/json/{json}/devicemanufacturerid/{devicemanufacturerid}")
    public TingcoDevice postJSON_Delete(@PathParam("devicemanufacturerid") String deviceManufacturerID) {
        return deleteDeviceManfacturer(deviceManufacturerID);
    }

    /**
     * DeviceManfacturers_GetInfo
     * @param deviceManufacturerID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/get/rest/{rest}/devicemanufacturerid/{devicemanufacturerid}")
    @RESTDoc(methodName = "DeviceManfacturers_GetInfo", desc = "To Get a DeviceManufacturer", req_Params = {"DeviceManufacturerID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoDevice getXml(@PathParam("devicemanufacturerid") String deviceManufacturerID) {
        return getDeviceManfacturer(deviceManufacturerID);
    }

    /**
     * DeviceManfacturers_GetInfo
     * @param deviceManufacturerID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/get/rest/{rest}/devicemanufacturerid/{devicemanufacturerid}")
    public TingcoDevice postXml(@PathParam("DeviceManufacturerID") String deviceManufacturerID) {
        return getDeviceManfacturer(deviceManufacturerID);
    }

    /**
     * DeviceManfacturers_GetInfo
     * @param deviceManufacturerID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/get/json/{json}/devicemanufacturerid/{devicemanufacturerid}")
    public TingcoDevice getJson(@PathParam("devicemanufacturerid") String deviceManufacturerID) {
        return getDeviceManfacturer(deviceManufacturerID);
    }

    /**
     * DeviceManfacturers_GetInfo
     * @param deviceManufacturerID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/get/json/{json}/devicemanufacturerid/{devicemanufacturerid}")
    public TingcoDevice postJson(@PathParam("devicemanufacturerid") String deviceManufacturerID) {
        return getDeviceManfacturer(deviceManufacturerID);
    }

    /**
     * DeviceManfacturers_Update
     * @param deviceManufacturerID
     * @param manufacturerName
     * @param manufacturerDesc
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/update/rest/{rest}/devicemanufacturerid/{devicemanufacturerid}{manufacturername:(/manufacturername/[^/]+?)?}{manufacturerdesc:(/manufacturerdesc/[^/]+?)?}")
    @RESTDoc(methodName = "DeviceManfacturers_Update", desc = "To Update a DeviceManfacturer", req_Params = {"DeviceManufacturerID;UUID"}, opt_Params = {"ManufacturerName;String", "ManufacturerDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoDevice getXml_Update(@PathParam("devicemanufacturerid") String deviceManufacturerID, @PathParam("manufacturername") String manufacturerName, @PathParam("manufacturerdesc") String manufacturerDesc) {
        return updateDeviceManfacturer(deviceManufacturerID, manufacturerName, manufacturerDesc);
    }

    /**
     * DeviceManfacturers_Update
     * @param deviceManufacturerID
     * @param manufacturerName
     * @param manufacturerDesc
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/update/rest/{rest}/devicemanufacturerid/{devicemanufacturerid}{manufacturername:(/manufacturername/[^/]+?)?}{manufacturerdesc:(/manufacturerdesc/[^/]+?)?}")
    public TingcoDevice postXml_Update(@PathParam("devicemanufacturerid") String deviceManufacturerID, @PathParam("manufacturername") String manufacturerName, @PathParam("manufacturerdesc") String manufacturerDesc) {
        return updateDeviceManfacturer(deviceManufacturerID, manufacturerName, manufacturerDesc);
    }

    /**
     * DeviceManfacturers_Update
     * @param deviceManufacturerID
     * @param manufacturerName
     * @param manufacturerDesc
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/update/json/{json}/devicemanufacturerid/{devicemanufacturerid}{manufacturername:(/manufacturername/[^/]+?)?}{manufacturerdesc:(/manufacturerdesc/[^/]+?)?}")
    public TingcoDevice getJson_Update(@PathParam("devicemanufacturerid") String deviceManufacturerID, @PathParam("manufacturername") String manufacturerName, @PathParam("manufacturerdesc") String manufacturerDesc) {
        return updateDeviceManfacturer(deviceManufacturerID, manufacturerName, manufacturerDesc);
    }

    /**
     * DeviceManfacturers_Update
     * @param deviceManufacturerID
     * @param manufacturerName
     * @param manufacturerDesc
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/update/json/{json}/devicemanufacturerid/{devicemanufacturerid}{manufacturername:(/manufacturername/[^/]+?)?}{manufacturerdesc:(/manufacturerdesc/[^/]+?)?}")
    public TingcoDevice postJson_Update(@PathParam("devicemanufacturerid") String deviceManufacturerID, @PathParam("manufacturername") String manufacturerName, @PathParam("manufacturerdesc") String manufacturerDesc) {
        return updateDeviceManfacturer(deviceManufacturerID, manufacturerName, manufacturerDesc);
    }

    /**
     * GetAllDeviceManfacturers
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/rest/{rest}")
    @RESTDoc(methodName = "GetAllDeviceManfacturers", desc = "To Get All DeviceManfacturers", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoDevice getXml_All() {
        return getAllDeviceManfactrers();
    }

    /**
     * GetAllDeviceManfacturers
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/rest/{rest}")
    public TingcoDevice postXml_All() {
        return getAllDeviceManfactrers();
    }

    /**
     * GetAllDeviceManfacturers
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/json/{json}")
    public TingcoDevice getJson_All() {
        return getAllDeviceManfactrers();
    }

    /**
     * GetAllDeviceManfacturers
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/json/{json}")
    public TingcoDevice postJson_All() {
        return getAllDeviceManfactrers();
    }

    /**
     *
     * @param searchString
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getdevicemanufactureslist/rest/{rest}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoDevice getXMl_GetDeviceManufacturesList(@PathParam("searchstring") String searchString) {
        return getDeviceManufacturesList(searchString);
    }

    /**
     *
     * @param searchString
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getdevicemanufactureslist/json/{json}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoDevice getJson_GetDeviceManufacturesList(@PathParam("searchstring") String searchString) {
        return getDeviceManufacturesList(searchString);
    }

    private TingcoDevice getDeviceManufacturesList(String searchString) {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                boolean hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    if (!searchString.equals("")) {
                        searchString = searchString.split("/")[2];
                    } else {
                        searchString = null;
                    }
                    List<DeviceManufacturers> dmList = new ArrayList<DeviceManufacturers>();
                    if (searchString != null) {
                        dmList = deviceDAO.getAllDeviceManufacturersBySearchString(session, searchString);
                    } else {
                        dmList = deviceDAO.getAllDeviceManufacturers(session, 200);
                        if (dmList.isEmpty()) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                            return tingcoDevice;
                        }
                        List<String> manufactureId = new ArrayList<String>();
                        for (DeviceManufacturers deviceManufacturers : dmList) {
                            manufactureId.add(deviceManufacturers.getDeviceManufacturerId());
                        }
                        dmList.clear();
                        dmList = deviceDAO.getDeviceManufacturersByIds(manufactureId, 200, session);
                    }
                    if (dmList.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoDevice;
                    }
                    return tingcoDeviceXML.buildGetDeviceManufacturesList(tingcoDevice, dmList);
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
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoDevice createDeviceManfcturer(String manfacturerName, String manfacturerDesc) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (manfacturerName == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("ManfacturerName is should not be empty");
                    return tingcoDevice;
                } else if (manfacturerDesc == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("ManfacturerDesc is should not be empty");
                    return tingcoDevice;
                }
//
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
                    DeviceManufacturers manfacturer = new DeviceManufacturers();
                    manfacturer.setDeviceManufacturerId(UUID.randomUUID().toString());
                    manfacturer.setDeviceManufacturerName(manfacturerName);
                    manfacturer.setDeviceManufacturerDescription(manfacturerDesc);
                    manfacturer.setUserId(userSessions2.getUserId());
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    manfacturer.setCreatedDate(gc.getTime());
                    manfacturer.setUpdatedDate(gc.getTime());
                    session = HibernateUtil.getSessionFactory().openSession();
                    if (!deviceDAO.saveDeviceManfacturer(manfacturer, session)) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Error Occured while Saving DeviceManfacturer");
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

    private TingcoDevice deleteDeviceManfacturer(String deviceManufacturerID) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceManufacturerID == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceManufacturerID is should not be empty");
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
                    DeviceManufacturers deviceManufacturers = deviceDAO.getDeviceManufacturersById(deviceManufacturerID, session);

                    if (deviceManufacturers != null) {
                        if (!deviceDAO.removeDeviceManufacturers(deviceManufacturers, session)) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error Occured while Deleting DeviceManufacturer");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("DeviceManufacturerID Not Found");
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

    private TingcoDevice getDeviceManfacturer(String deviceManfacturerID) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceManfacturerID == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceManfacturerID is should not be empty");
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
                    DeviceManufacturers deviceManufacturers = deviceDAO.getDeviceManufacturersById(deviceManfacturerID, session);
                    if (deviceManufacturers != null) {
                        ArrayList<DeviceManufacturers> deviceManufacturersList = new ArrayList<DeviceManufacturers>();
                        deviceManufacturersList.add(deviceManufacturers);
                        Users2 user2 = userDAO.getUserById(deviceManufacturers.getUserId(), session);
                        HashMap<String, Users2> userMap = new HashMap<String, Users2>();
                        userMap.put(deviceManufacturers.getUserId(), user2);
                        tingcoDevice = tingcoDeviceXML.buildDeviceManfacurers(tingcoDevice, deviceManufacturersList, userMap);
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No DeviceManufacturer Found with Given DeviceManufacturerID");
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

    private TingcoDevice updateDeviceManfacturer(String deviceManufacturerID, String manufacturerName, String manufacturerDesc) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceManufacturerID == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceManufacturerID is should not be empty");
                    return tingcoDevice;
                }

                if (manufacturerName.equals("")) {
                    manufacturerName = null;
                } else {
                    manufacturerName = manufacturerName.split("/")[2];
                }

                if (manufacturerDesc.equals("")) {
                    manufacturerDesc = null;
                } else {
                    manufacturerDesc = manufacturerDesc.split("/")[2];
                }

                if (manufacturerName == null && manufacturerDesc == null && deviceManufacturerID == null) {
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
                    DeviceManufacturers deviceManufacturers = deviceDAO.getDeviceManufacturersById(deviceManufacturerID, session);

                    if (deviceManufacturers != null) {
                        UserSessions2 userSessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                        deviceManufacturers.setUserId(userSessions2.getUserId());
                        if (manufacturerName != null) {
                            deviceManufacturers.setDeviceManufacturerName(manufacturerName);
                        }
                        if (manufacturerDesc != null) {
                            deviceManufacturers.setDeviceManufacturerDescription(manufacturerDesc);
                        }

                        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                        deviceManufacturers.setUpdatedDate(gc.getTime());
                        if (!deviceDAO.saveDeviceManfacturer(deviceManufacturers, session)) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error Occured while Updating DeviceManufacturer");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No DeviceManufacturer Found with Given DeviceManufacturerID");
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

    private TingcoDevice getAllDeviceManfactrers() {
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
//                    if(operations.get(i).equalsIgnoreCase(TCMUtil.READ)){
                hasPermission = true;
//                    }
//                }
//            }

                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<DeviceManufacturers> dmList = deviceDAO.getAllDeviceManufacturers(session, 0);
                    if (dmList != null) {
                        HashMap<String, Users2> userMap = new HashMap<String, Users2>();
                        for (DeviceManufacturers deviceTypes : dmList) {
                            if (!userMap.containsKey(deviceTypes.getUserId())) {
                                Users2 user2 = userDAO.getUserById(deviceTypes.getUserId(), session);
                                userMap.put(deviceTypes.getUserId(), user2);
                            }
                        }
                        tingcoDevice = tingcoDeviceXML.buildDeviceManfacurers(tingcoDevice, dmList, userMap);
                        return tingcoDevice;
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Error Occured while Loading the DeviceTypes");
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
    public DeviceManufacturerResource getDeviceManufacturerResource() {
        return new DeviceManufacturerResource();
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

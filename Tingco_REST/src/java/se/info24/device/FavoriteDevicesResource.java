/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.device;

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
import se.info24.devicejaxb.TingcoDevice;
import se.info24.devicejaxbPost.Device;
import se.info24.jaxbUtil.TingcoDeviceXML;
import se.info24.pojo.UserFavoriteDevices;
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
@Path("/favoritedevice")
public class FavoriteDevicesResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
    DeviceDAO deviceDAO = new DeviceDAO();
//    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of FavoriteDevicesResource */
    public FavoriteDevicesResource() {
    }

    /**
     * FavoriteDevices_Delete
     * @param userId
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/delete/rest/{rest}{userid:(/userid/[^/]+?)?}{deviceid:(/deviceid/[^/]+?)?}")
    @RESTDoc(methodName = "FavoriteDevices_Delete", desc = "Deletes UserFavoriteDevices ", req_Params = {"rest;string"}, opt_Params = {"UserId;UUID,DeviceId;UUID"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_Delete(@PathParam("userid") String userId, @PathParam("deviceid") String deviceId) {
        if (userId.equals("")) {
            userId = null;
        } else {
            userId = userId.split("/")[2];
            return deleteFavoriteDevicesByUserId(userId);
        }
        if (deviceId.equals("")) {
            deviceId = null;
        } else {
            deviceId = deviceId.split("/")[2];
            return deleteFavoriteDevicesByDeviceId(deviceId);
        }
        return null;
    }

    /**
     * FavoriteDevices_Delete
     * @param userId
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/delete/json/{json}{userid:(/userid/[^/]+?)?}{deviceid:(/deviceid/[^/]+?)?}")
    public TingcoDevice getJSON_Delete(@PathParam("userid") String userId, @PathParam("deviceid") String deviceId) {
        if (userId.equals("")) {
            userId = null;
        } else {
            userId = userId.split("/")[2];
        }
        if (deviceId.equals("")) {
            deviceId = null;
        } else {
            deviceId = deviceId.split("/")[2];
        }
        if (userId != null) {
            return deleteFavoriteDevicesByUserId(userId);
        } else if (deviceId != null) {
            return deleteFavoriteDevicesByDeviceId(deviceId);
        }
        return null;
    }

    /**
     * GetFavoriteDevices
     * @return
     */
    @GET
    @Path("/get/rest/{rest}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetFavoriteDevices", desc = "Return's user favorite devices", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml() {
        return userFavoriteDevices();
    }

    /**
     * GetFavoriteDevices
     * @return
     */
    @GET
    @Path("/get/json/{json}")
    @Produces("application/xml")
    public TingcoDevice getJSON() {
        return userFavoriteDevices();
    }

    /**
     * FavoriteDevices_Add
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/add/rest/{rest}")
    @RESTDoc(methodName = "FavoriteDevices_Add", desc = "Add's UserFavoriteDevices ", req_Params = {"DeviceId;UUID"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_Add(String content) {
        JAXBManager manage = JAXBManager.getInstance();
        se.info24.devicejaxbPost.TingcoDevice td = (se.info24.devicejaxbPost.TingcoDevice) manage.unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
        return addFavoriteDevices(td);
    }

    /**
     * FavoriteDevices_Add
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/add/json/{json}")
    public TingcoDevice postJson_Add(String content) {
        JAXBManager manage = JAXBManager.getInstance();
        se.info24.devicejaxbPost.TingcoDevice td = (se.info24.devicejaxbPost.TingcoDevice) manage.unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
        return addFavoriteDevices(td);
    }

    private TingcoDevice addFavoriteDevices(se.info24.devicejaxbPost.TingcoDevice td) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
//
//                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) request.getSession(true).getAttribute(request.getSession(true).getId());
//                if (ht.containsKey(TCMUtil.DEVICE)) {
//                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
//                    for (int i = 0; i < operations.size(); i++) {
//                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                hasPermission = true;
//                        }
//                    }
//                }

                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserSessions2 user_sess = (UserSessions2) request.getAttribute("USERSESSION");
                    List<Device> devices = td.getDevices().getDevice();
                    boolean added = deviceDAO.addFavoriteDevices(user_sess.getUserId(), devices, session);
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
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice deleteFavoriteDevicesByDeviceId(String deviceId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
//
//                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) request.getSession(true).getAttribute(request.getSession(true).getId());
//                if (ht.containsKey(TCMUtil.DEVICE)) {
//                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
//                    for (int i = 0; i < operations.size(); i++) {
//                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                hasPermission = true;
//                        }
//                    }
//                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserSessions2 user_sess = (UserSessions2) request.getAttribute("USERSESSION");
                    boolean deleted = deviceDAO.deleteFavoriteDevicesByDeviceId(user_sess.getUserId(), deviceId, session);
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
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice deleteFavoriteDevicesByUserId(String userId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {

//                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) request.getSession(true).getAttribute(request.getSession(true).getId());
//                if (ht.containsKey(TCMUtil.DEVICE)) {
//                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
//                    for (int i = 0; i < operations.size(); i++) {
//                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                hasPermission = true;
//                        }
//                    }
//                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    boolean deleted = deviceDAO.deleteFavoriteDevicesByUserId(userId, session);
                    if (deleted) {
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
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice userFavoriteDevices() {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {

//                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) request.getSession(true).getAttribute(request.getSession(true).getId());
//                if (ht.containsKey(TCMUtil.DEVICE)) {
//                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
//                    for (int i = 0; i < operations.size(); i++) {
//                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                hasPermission = true;
//                        }
//                    }
//                }

                if (hasPermission) {
                    UserSessions2 userSess = (UserSessions2) request.getAttribute("USERSESSION");
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<UserFavoriteDevices> ufd = deviceDAO.getUserFavoriteDevices(userSess.getUserId(), session);
                    tingcoDevice = tingcoDeviceXML.buildUserFavoriteDevices(tingcoDevice, ufd);
                } else {
                    tingcoDevice.getMsgStatus().setResponseCode(-3);
                    tingcoDevice.getMsgStatus().setResponseText("User Session is Expired");
                }
            } else {
                tingcoDevice.getMsgStatus().setResponseCode(-10);
                tingcoDevice.getMsgStatus().setResponseText("User Permission is not given");
                return tingcoDevice;
            }
        } catch (DatatypeConfigurationException ex) {
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
    public FavoriteDeviceResource getFavoriteDeviceResource() {
        return new FavoriteDeviceResource();
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}

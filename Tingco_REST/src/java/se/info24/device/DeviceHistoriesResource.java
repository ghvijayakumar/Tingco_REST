/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.hibernate.Session;
import se.info24.devicejaxb.TingcoDevice;
import se.info24.ismOperationsPojo.DeviceHistory;
import se.info24.ismOperationsPojo.DeviceHistoryDataItems;
import se.info24.jaxbUtil.TingcoDeviceXML;
import se.info24.pojo.Device;
import se.info24.pojo.DeviceDataitemTranslations;
import se.info24.util.HibernateUtil;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/devicehistory")
public class DeviceHistoriesResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
    DeviceDAO deviceDAO = new DeviceDAO();
    Hashtable<String, DeviceDataitemTranslations> dataitemTrans = null;
    HashMap<String, Device> deviceMap = null;
    HashMap<String, List<DeviceHistory>> sortDevHistory = null;
    HashMap<String, List<DeviceHistoryDataItems>> dhdiMap = null;
//    private DateFormat df = new S("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of DeviceHistoriesResource */
    public DeviceHistoriesResource() {
    }

    /**
     * DeviceHistory_Get
     * @param countryId
     * @param deviceId
     * @param groupId
     * @param deviceTypeID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/rest/{rest}/countryid/{countryid}{deviceid:(/deviceid/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}{devicetypeid:(/devicetypeid/[^/]+?)?}")
    @RESTDoc(methodName = "DeviceHistory_Get", desc = "To Get Device History By DeviceID or DeviceTypeID or GroupID", req_Params = {"CountryID;int"}, opt_Params = {"DeviceID;UUID", "GroupID;UUID", "DeviceTypeID;UUID"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoDevice getXml(@PathParam("countryid") int countryId, @PathParam("deviceid") String deviceId, @PathParam("groupid") String groupId, @PathParam("devicetypeid") String deviceTypeID) {
        return getDeviceHistory(countryId, deviceId, groupId, deviceTypeID);
    }

    /**
     * DeviceHistory_Get
     * @param countryId
     * @param deviceId
     * @param groupId
     * @param deviceTypeID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/rest/{rest}/countryid/{countryid}{deviceid:(/deviceid/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}{devicetypeid:(/devicetypeid/[^/]+?)?}")
    public TingcoDevice postXml(@PathParam("countryid") int countryId, @PathParam("deviceid") String deviceId, @PathParam("groupid") String groupId, @PathParam("devicetypeid") String deviceTypeID) {
        return getDeviceHistory(countryId, deviceId, groupId, deviceTypeID);
    }

    /**
     * DeviceHistory_Get
     * @param countryId
     * @param deviceId
     * @param groupId
     * @param deviceTypeID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/json/{json}/countryid/{countryid}{deviceid:(/deviceid/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}{devicetypeid:(/devicetypeid/[^/]+?)?}")
    public TingcoDevice getJson(@PathParam("countryid") int countryId, @PathParam("deviceid") String deviceId, @PathParam("groupid") String groupId, @PathParam("devicetypeid") String deviceTypeID) {
        return getDeviceHistory(countryId, deviceId, groupId, deviceTypeID);
    }

    /**
     * DeviceHistory_Get
     * @param countryId
     * @param deviceId
     * @param groupId
     * @param deviceTypeID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/json/{json}/countryid/{countryid}{deviceid:(/deviceid/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}{devicetypeid:(/devicetypeid/[^/]+?)?}")
    public TingcoDevice postJson(@PathParam("countryid") int countryId, @PathParam("deviceid") String deviceId, @PathParam("groupid") String groupId, @PathParam("devicetypeid") String deviceTypeID) {
        return getDeviceHistory(countryId, deviceId, groupId, deviceTypeID);
    }

    private TingcoDevice getDeviceHistory(int countryId, String deviceId, String groupId, String deviceTypeId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
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

//            Hashtable<String,ArrayList> ht = (Hashtable<String, ArrayList>) request.getSession(true).getAttribute(request.getSession(true).getId());
//            if (ht.containsKey(TCMUtil.DEVICE)) {
//                ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
//                for(int i=0;i<operations.size();i++){
//                    if(operations.get(i).equalsIgnoreCase(TCMUtil.READ)){
                hasPermission = true;
//                    }
//                }
//            }

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

                if (hasPermission) {
                    String qry = "from DeviceHistory dh where ";

                    if (deviceId != null && groupId != null && deviceTypeId != null) {
                        qry = qry + " dh.deviceId = '" + deviceId + "' and dh.groupId = '" + groupId + "' and dh.deviceTypeId = '" + deviceTypeId + "'";
                    } else if (deviceId != null && groupId != null && deviceTypeId == null) {
                        qry = qry + " dh.deviceId = '" + deviceId + "' and dh.groupId = '" + groupId + "'";
                    } else if (deviceId != null && groupId == null && deviceTypeId != null) {
                        qry = qry + " dh.deviceId = '" + deviceId + "' and dh.deviceTypeId = '" + deviceTypeId + "'";
                    } else if (deviceId == null && groupId != null && deviceTypeId != null) {
                        qry = qry + " dh.groupId = '" + groupId + "' and dh.deviceTypeId = '" + deviceTypeId + "'";
                    } else if (deviceId != null && groupId == null && deviceTypeId == null) {
                        qry = qry + " dh.deviceId = '" + deviceId + "'";
                    } else if (deviceId == null && groupId != null && deviceTypeId == null) {
                        qry = qry + " dh.groupId = '" + groupId + "'";
                    } else if (deviceId == null && groupId == null && deviceTypeId != null) {
                        qry = qry + " dh.deviceTypeId = '" + deviceTypeId + "'";
                    }
                    ismOpr_session = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<DeviceHistory> dhList = deviceDAO.getAllDeviceHistoryByQry(qry, ismOpr_session);
                    if (dhList != null) {
                        deviceMap = new HashMap<String, Device>();
                        sortDevHistory = new HashMap<String, List<DeviceHistory>>();
                        dhdiMap = new HashMap<String, List<DeviceHistoryDataItems>>();
                        dataitemTrans = new Hashtable<String, DeviceDataitemTranslations>();

                        for (DeviceHistory dh : dhList) {
                            if (!deviceMap.containsKey(dh.getDeviceId())) {
                                deviceMap.put(dh.getDeviceId(), deviceDAO.getDeviceById(dh.getDeviceId(), session));
                            }
                            if (sortDevHistory.containsKey(dh.getDeviceId())) {
                                sortDevHistory.get(dh.getDeviceId()).add(dh);
                            } else {
                                ArrayList<DeviceHistory> list = new ArrayList<DeviceHistory>();
                                list.add(dh);
                                sortDevHistory.put(dh.getDeviceId(), list);
                            }
                        }
                        // Loading the DeviceHistoryDataItems.
                        for (String dId : deviceMap.keySet()) {
                            dhdiMap.put(dId, deviceDAO.getDeviceHistoryDataItemsById(dId, ismOpr_session));
                        }

                        for (DeviceDataitemTranslations ddit : deviceDAO.getAllDeviceDataitemTranslations(countryId, session)) {
                            dataitemTrans.put(ddit.getId().getDeviceDataItemId(), ddit);
                        }

                        tingcoDevice = tingcoDeviceXML.buildDeviceHistory(tingcoDevice, dhList, deviceMap, dataitemTrans, dhdiMap, countryId);
                        return tingcoDevice;
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No DeviceHistory Found");
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
            tingcoDevice.getMsgStatus().setResponseText("Error Occured While Loading DeviceHistory");
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

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public DeviceHistoryResource getDeviceHistoryResource() {
        return new DeviceHistoryResource();
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}

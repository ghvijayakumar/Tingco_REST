/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.device;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import javax.ws.rs.core.Context;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import se.info24.util.TCMUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.containers.ContainerDAO;
import se.info24.devicejaxb.TingcoDevice;
import se.info24.devicejaxbPost.Connector;
import se.info24.jaxbUtil.TingcoDeviceXML;
import se.info24.pojo.ConnectorAcdc;
import se.info24.pojo.ConnectorCurrents;
import se.info24.pojo.ConnectorModes;
import se.info24.pojo.ConnectorTypes;
import se.info24.pojo.ConnectorVoltages;
import se.info24.pojo.Connectors;
import se.info24.pojo.Device;
import se.info24.pojo.DeviceDataItems;
import se.info24.pojo.DeviceDataitemTranslations;
import se.info24.pojo.DeviceTypeDataitems;
import se.info24.pojo.DeviceTypeDataitemsId;
import se.info24.pojo.ProductVariants;
import se.info24.pojo.UserSessions2;
import se.info24.user.User_LoginsResource;
import se.info24.util.HibernateUtil;
import se.info24.util.JAXBManager;
import se.info24.util.RESTDoc;

/**
 * REST Web Service
 *
 * @author Vijayakumar
 */
@Path("/deviceconnectors")
public class DeviceConnectorsResource {

    @Context
    private HttpServletRequest request;
    DeviceDAO deviceDAO = new DeviceDAO();
    TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));

    /** Creates a new instance of DeviceConnectorsResource */
    public DeviceConnectorsResource() {
    }

    /**
     * GetConnectorsList
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getconnectorslist/rest/{rest}/deviceid/{deviceid}")
    @RESTDoc(methodName = "GetConnectorsList", desc = "Used to Get Connectors List", req_Params = {"DeviceID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetConnectorsList(@PathParam("deviceid") String deviceId) {
        return getConnectorsList(deviceId);
    }

    /**
     * GetConnectorsList
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getconnectorslist/json/{json}/deviceid/{deviceid}")
    public TingcoDevice getJson_GetConnectorsList(@PathParam("deviceid") String deviceId) {
        return getConnectorsList(deviceId);
    }

    /**
     * GetDeviceDataItemsList
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getdevicedataitemslist/rest/{rest}/deviceid/{deviceid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetDeviceDataItemsList", desc = "Used to Get DeviceDataItems List", req_Params = {"DeviceID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetDeviceDataItemsList(@PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId) {
        return getDeviceDataItemsList(deviceId, countryId);
    }

    /**
     * GetDeviceDataItemsList
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getdevicedataitemslist/json/{json}/deviceid/{deviceid}/countryid/{countryid}")
    public TingcoDevice getJson_GetDeviceDataItemsList(@PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId) {
        return getDeviceDataItemsList(deviceId, countryId);
    }

    /**
     * GetConnectorTypes
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getconnectortypes/rest/{rest}")
    @RESTDoc(methodName = "GetConnectorTypes", desc = "Used to Get ConnectorTypes", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetConnectorTypes() {
        return getConnectorTypes();
    }

    /**
     * GetConnectorTypes
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getconnectortypes/json/{json}")
    public TingcoDevice getJson_GetConnectorTypes() {
        return getConnectorTypes();
    }

    /**
     * GetConnectorModes
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getconnectormodes/rest/{rest}")
    @RESTDoc(methodName = "GetConnectorModes", desc = "Used to Get ConnectorModes", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetConnectorModes() {
        return getConnectorModes();
    }

    /**
     * GetConnectorModes
     * @return
     */
    @GET
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getconnectormodes/json/{json}")
    public TingcoDevice getJson_GetConnectorModes() {
        return getConnectorModes();
    }

    /**
     * GetConnectorVoltages
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getconnectorvoltages/rest/{rest}")
    @RESTDoc(methodName = "GetConnectorVoltages", desc = "Used to Get ConnectorVoltages", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetConnectorVoltages() {
        return getConnectorVoltages();
    }

    /**
     * GetConnectorVoltages
     * @return
     */
    @GET
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getconnectorvoltages/json/{json}")
    public TingcoDevice getJson_GetConnectorVoltages() {
        return getConnectorVoltages();
    }

    /**
     * GetConnectorCurrents
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getconnectorcurrents/rest/{rest}")
    @RESTDoc(methodName = "GetConnectorCurrents", desc = "Used to Get ConnectorCurrents", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetConnectorCurrents() {
        return getConnectorCurrents();
    }

    /**
     * GetConnectorCurrents
     * @return
     */
    @GET
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getconnectorcurrents/json/{json}")
    public TingcoDevice getJson_GetConnectorCurrents() {
        return getConnectorCurrents();
    }

    /**
     * GetConnectorACDC
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getconnectoracdc/rest/{rest}")
    @RESTDoc(methodName = "GetConnectorACDC", desc = "Used to Get ConnectorACDC", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetConnectorACDC() {
        return getConnectorACDC();
    }

    /**
     * GetConnectorACDC
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getconnectoracdc/json/{json}")
    public TingcoDevice getJson_GetConnectorACDC() {
        return getConnectorACDC();
    }

    /**
     * GetLoadBalanceList
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getloadbalancelist/rest/{rest}")
    @RESTDoc(methodName = "GetLoadBalanceList", desc = "Used to Get LoadBalance List", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetLoadBalanceList() {
        return getLoadBalanceList();
    }

    /**
     * GetLoadBalanceList
     * @return
     */
    @GET
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getloadbalancelist/json/{json}")
    public TingcoDevice getJson_GetLoadBalanceList() {
        return getLoadBalanceList();
    }

    /**
     * GetConnectorDetailsById
     * @param connectorId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getconnectordetailsbyid/rest/{rest}/connectorid/{connectorid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetConnectorDetailsById", desc = "Used to Get ConnectorDetails By Id", req_Params = {"ConnectorId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetConnectorDetailsById(@PathParam("connectorid") String connectorId, @PathParam("countryid") int countryID) {
        return getConnectorDetailsById(connectorId, countryID);
    }

    /**
     * GetConnectorDetailsById
     * @param connectorId
     * @return
     */
    @GET
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getconnectordetailsbyid/json/{json}/connectorid/{connectorid}/countryid/{countryid}")
    public TingcoDevice getJson_GetConnectorDetailsById(@PathParam("connectorid") String connectorId, @PathParam("countryid") int countryID) {
        return getConnectorDetailsById(connectorId, countryID);
    }

    /**
     * DeleteConnectorByID
     * @param connectorId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deleteconnectorbyid/rest/{rest}/connectorid/{connectorid}")
    @RESTDoc(methodName = "DeleteConnectorByID", desc = "Used to Delete Connectors By ID", req_Params = {"ConnectorId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_DeleteConnectorByID(@PathParam("connectorid") String connectorId) {
        return deleteConnectorByID(connectorId);
    }

    /**
     * DeleteConnectorByID
     * @param connectorId
     * @return
     */
    @GET
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/deleteconnectorbyid/json/{json}/connectorid/{connectorid}")
    public TingcoDevice getJson_DeleteConnectorByID(@PathParam("connectorid") String connectorId) {
        return deleteConnectorByID(connectorId);
    }

    /**
     * Device_Add
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addconnectortodevice/rest/{rest}")
    @RESTDoc(methodName = "Add_Connector_To_Device", desc = "Used to add a new connector to device", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_Add(String content) {
        return addConnectorToDevice(content);
    }

    /**
     * Device_Add
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addconnectortodevice/json/{json}")
    public TingcoDevice postJson_Add(String content) {
        return addConnectorToDevice(content);
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public DeviceConnectorResource getDeviceConnectorResource() {
        return new DeviceConnectorResource();
    }

    /**
     * GetDeviceDataItemDeviceTypeID
     * @param deviceTypeId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getdevicedataitemdevicetypeid/rest/{rest}/devicetypeid/{devicetypeid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetDeviceDataItemDeviceTypeID", desc = "GetDeviceDataItemDeviceTypeID", req_Params = {"devicetypeid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetDeviceDataItemDeviceTypeID(@PathParam("devicetypeid") String deviceTypeId, @PathParam("countryid") int countryId) {
        return getDeviceDataItemDeviceTypeID(deviceTypeId, countryId);
    }

    /**
     * GetDeviceDataItemDeviceTypeID
     * @param deviceTypeId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getdevicedataitemdevicetypeid/json/{json}/devicetypeid/{devicetypeid}/countryid/{countryid}")
    public TingcoDevice getJson_GetDeviceDataItemDeviceTypeID(@PathParam("devicetypeid") String deviceTypeId, @PathParam("countryid") int countryId) {
        return getDeviceDataItemDeviceTypeID(deviceTypeId, countryId);
    }

    /**
     * InsertDeleteDeviceDataItemDeviceTypeID
     * @param deviceTypeId
     * @param devcieDataItemId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/insertdeletedevicedataitemdevicetypeid/rest/{rest}/flag/{flag}/devicetypeid/{devicetypeid}/devciedataitemid/{devciedataitemid}")
    @RESTDoc(methodName = "InsertDeleteDeviceDataItemDeviceTypeID", desc = "InsertDeleteDeviceDataItemDeviceTypeID", req_Params = {"devicetypeid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_InsertDeleteDeviceDataItemDeviceTypeID(@PathParam("flag") String flag, @PathParam("devicetypeid") String deviceTypeId, @PathParam("devciedataitemid") String devcieDataItemId) {
        return insertDeleteDeviceDataItemDeviceTypeID(flag, deviceTypeId, devcieDataItemId);
    }

    /**
     * InsertDeleteDeviceDataItemDeviceTypeID
     * @param deviceTypeId
     * @param devcieDataItemId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/insertdeletedevicedataitemdevicetypeid/json/{json}/flag/{flag}/devicetypeid/{devicetypeid}/devciedataitemid/{devciedataitemid}")
    public TingcoDevice getJson_InsertDeleteDeviceDataItemDeviceTypeID(@PathParam("flag") String flag, @PathParam("devicetypeid") String deviceTypeId, @PathParam("devciedataitemid") String devcieDataItemId) {
        return insertDeleteDeviceDataItemDeviceTypeID(flag, deviceTypeId, devcieDataItemId);
    }

    private TingcoDevice insertDeleteDeviceDataItemDeviceTypeID(String flag, String deviceTypeId, String devcieDataItemId) {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    DeviceTypeDataitems deviceTypeDataitems = deviceDAO.getDeviceTypeDataItems(deviceTypeId, devcieDataItemId, session);
                    if (flag.equalsIgnoreCase("INSERT")) {
                        if (deviceTypeDataitems == null) {
                            DeviceTypeDataitems dtd = new DeviceTypeDataitems();
                            dtd.setId(new DeviceTypeDataitemsId(deviceTypeId, devcieDataItemId));
                            dtd.setUserId(sessions2.getUserId());
                            dtd.setCreatedDate(gc.getTime());
                            dtd.setUpdatedDate(gc.getTime());
                            deviceDAO.saveDeviceTypeDataitems(dtd, session);
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Data Already Exists");
                            return tingcoDevice;
                        }
                    }
                    if (flag.equalsIgnoreCase("DELETE")) {
                        if (deviceTypeDataitems == null) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                            return tingcoDevice;
                        } else {
                            deviceDAO.deleteDeviceTypeDataitems(deviceTypeDataitems, session);
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

    private TingcoDevice getDeviceDataItemDeviceTypeID(String deviceTypeId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<DeviceTypeDataitems> deviceTypeDataitemses = deviceDAO.getDeviceTypeDataItemsByDeviceTypeID(deviceTypeId, session);
                    if (!deviceTypeDataitemses.isEmpty()) {
                        List<String> deviceDataItemIDs = new ArrayList<String>();
                        for (DeviceTypeDataitems dataitems : deviceTypeDataitemses) {
                            deviceDataItemIDs.add(dataitems.getId().getDeviceDataItemId());
                        }
                        ContainerDAO containerDAO = new ContainerDAO();
                        tingcoDevice = tingcoDeviceXML.buildGetDeviceDataItemsList(tingcoDevice, containerDAO.getDeviceDataItemTranslationsByIds(session, deviceDataItemIDs, countryId));
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Found With Given DeviceType");
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

    private TingcoDevice deleteConnectorByID(String connectorId) {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object connObject = deviceDAO.getConnectorsByConnectorID(connectorId, session);
                    if (connObject == null) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoDevice;
                    } else {
                        deviceDAO.deleteConnector((Connectors) connObject, session);
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

    private TingcoDevice getConnectorDetailsById(String connectorId, int CountryId) {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object connectorsByConnectorID = deviceDAO.getConnectorsByConnectorID(connectorId, session);
                    if (connectorsByConnectorID == null) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoDevice;
                    }
                    DeviceDataitemTranslations dataitemTranslations = null;
                    Object loadBalanceObject = null;
                    Connectors connector = (Connectors) connectorsByConnectorID;

                    if (connector.getDeviceDataItems() != null) {
                        dataitemTranslations = deviceDAO.getDeviceDataItemTranslationsById(session, connector.getDeviceDataItems().getDeviceDataItemId(), CountryId);
                    }
                    if (connector.getLoadBalanceId() != null) {
                        loadBalanceObject = deviceDAO.getAllLoadBalanceByLoadBalanceID(connector.getLoadBalanceId(), session);
                    }
                    tingcoDevice = tingcoDeviceXML.buildGetConnectorDetailsById(tingcoDevice, connector, CountryId, loadBalanceObject, dataitemTranslations);

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

    private TingcoDevice getConnectorACDC() {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tingcoDevice = tingcoDeviceXML.buildGetConnectorACDC(tingcoDevice, deviceDAO.getAllConnectorAcdcOrderByConnectorACDCName(session));
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

    private TingcoDevice getConnectorCurrents() {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tingcoDevice = tingcoDeviceXML.buildGetConnectorCurrents(tingcoDevice, deviceDAO.getAllConnectorCurrentsOrderByConnectorCurrentName(session));
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

    private TingcoDevice getConnectorModes() {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tingcoDevice = tingcoDeviceXML.buildGetConnectorModes(tingcoDevice, deviceDAO.getAllConnectorsModesOrderByConnectorModeName(session));
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

    private TingcoDevice getConnectorTypes() {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tingcoDevice = tingcoDeviceXML.buildGetConnectorTypes(tingcoDevice, deviceDAO.getAllConnectorsTypesOrderByConnectorTypeName(session));
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

    private TingcoDevice getConnectorVoltages() {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tingcoDevice = tingcoDeviceXML.buildGetConnectorVoltages(tingcoDevice, deviceDAO.getAllConnectorVoltagesOrderByConnectorVoltageName(session));
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

    private TingcoDevice getConnectorsList(String deviceId) {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tingcoDevice = tingcoDeviceXML.buildGetConnectorsList(tingcoDevice, deviceDAO.getConnectorsByDeviceIdSortedByConnectorName(deviceId, session));
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

    private TingcoDevice getDeviceDataItemsList(String deviceId, int countryID) {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Device deviceObj = deviceDAO.getDeviceById(deviceId, session);
                    if (deviceObj != null) {
                        List<DeviceTypeDataitems> deviceTypeDataitemses = deviceDAO.getDeviceTypeDataItemsByDeviceTypeID(deviceObj.getDeviceTypes().getDeviceTypeId(), session);
                        if (!deviceTypeDataitemses.isEmpty()) {
                            List<String> deviceDataItemIDs = new ArrayList<String>();
                            for (DeviceTypeDataitems dataitems : deviceTypeDataitemses) {
                                deviceDataItemIDs.add(dataitems.getId().getDeviceDataItemId());
                            }
                            ContainerDAO containerDAO = new ContainerDAO();
                            tingcoDevice = tingcoDeviceXML.buildGetDeviceDataItemsList(tingcoDevice, containerDAO.getDeviceDataItemTranslationsByIds(session, deviceDataItemIDs, countryID));
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("No Data Found With Given DeviceType");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Found With Given Device");
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

    private TingcoDevice getLoadBalanceList() {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tingcoDevice = tingcoDeviceXML.buildGetLoadBalanceList(tingcoDevice, deviceDAO.getAllLoadBalanceOrderByLoadBalanceName(session));
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

    private TingcoDevice addConnectorToDevice(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        Transaction tx = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    Connector connectorjaxb = devices.getDevices().getDevice().get(0).getConnectors().get(0).getConnector().get(0);
                    List<Connectors> connectorsList = deviceDAO.getConnectorsLinkedToDevice(connectorjaxb.getDeviceID(), session);
                    boolean isSortOrderAlreadyExists = false;
                    if (connectorjaxb.getConnectorID() == null) {
                        for (Connectors con : connectorsList) {
                            if (con.getSortOrder() == connectorjaxb.getSortOrder()) {
                                isSortOrderAlreadyExists = true;
                                break;
                            }
                        }
                    }
//                    if (!isSortOrderAlreadyExists) {
                    String connectorId = null;
                    Connectors connectors = null;
                    //if else condition used to reuse the code to add a new connector and update existing connector.
                    if (connectorjaxb.getConnectorID() == null) {
                        Object connectorHasSameDeviceDataItems = deviceDAO.getConnectorsByDeviceIdAndDeviceDataItemId(connectorjaxb.getDeviceID(), connectorjaxb.getDeviceDataItemID(), session);
                        if (connectorHasSameDeviceDataItems != null) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Device attribute already exists. Please choose a different Device attribute");
                            return tingcoDevice;
                        }

                        for (Connectors con : connectorsList) {
                            if (con.getSortOrder() == connectorjaxb.getSortOrder()) {
                                isSortOrderAlreadyExists = true;
                                break;
                            }
                        }
                        if (isSortOrderAlreadyExists) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("A Connector with same sort order already exists. Please choose a different sort order");
                            return tingcoDevice;
                        }
                        connectorId = UUID.randomUUID().toString();
                        connectors = new Connectors();
                        connectors.setConnectorId(connectorId);
                    } else {
                        connectorId = connectorjaxb.getConnectorID();
                        Object connectorObject = deviceDAO.getConnectorsByConnectorID(connectorId, session);
                        connectors = (Connectors) connectorObject;
                        if (!connectors.getDeviceId().equalsIgnoreCase(connectorjaxb.getDeviceID()) || !connectors.getDeviceDataItems().getDeviceDataItemId().equalsIgnoreCase(connectorjaxb.getDeviceDataItemID())) {
                            Object connectorHasSameDeviceDataItems = deviceDAO.getConnectorsByDeviceIdAndDeviceDataItemId(connectorjaxb.getDeviceID(), connectorjaxb.getDeviceDataItemID(), session);
                            if (connectorHasSameDeviceDataItems == null) {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("Device attribute already exists. Please choose a different Device attribute");
                                return tingcoDevice;
                            }
                        }
                    }
                    if (connectors.getSortOrder() != connectorjaxb.getSortOrder()) {
                        for (Connectors con : connectorsList) {
                            if (con.getSortOrder() == connectorjaxb.getSortOrder()) {
                                isSortOrderAlreadyExists = true;
                                break;
                            }
                        }
                        if (isSortOrderAlreadyExists) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("A Connector with same sort order already exists. Please choose a different sort order");
                            return tingcoDevice;
                        }
                    }
                    connectors.setDeviceId(connectorjaxb.getDeviceID());
                    DeviceDataItems deviceDataItems = (DeviceDataItems) session.get(DeviceDataItems.class, connectorjaxb.getDeviceDataItemID());
                    if (deviceDataItems == null) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("DeviceDataItemsID not Found");
                        return tingcoDevice;
                    }
                    connectors.setDeviceDataItems(deviceDataItems);

                    connectors.setConnectorName(connectorjaxb.getConnectorName());
                    connectors.setSortOrder(connectorjaxb.getSortOrder());

                    ConnectorTypes connectorTypes = (ConnectorTypes) session.get(ConnectorTypes.class, connectorjaxb.getConnectorTypeID());
                    if (connectorTypes == null) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("ConnectorTypesID not Found");
                        return tingcoDevice;
                    }
                    connectors.setConnectorTypes(connectorTypes);
                    connectors.setConnectorTypeCode(connectorTypes.getObjectCode());

                    ConnectorModes connectorModes = (ConnectorModes) session.get(ConnectorModes.class, connectorjaxb.getConnectorModeID());
                    if (connectorModes == null) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("ConnectorModesID not Found");
                        return tingcoDevice;
                    }

                    connectors.setConnectorModes(connectorModes);
                    connectors.setConnectorModeCode(connectorModes.getConnectorModeName());

                    ConnectorVoltages connectorVoltages = (ConnectorVoltages) session.get(ConnectorVoltages.class, connectorjaxb.getConnectorVoltageID());
                    if (connectorVoltages == null) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("ConnectorVoltagesID not Found");
                        return tingcoDevice;
                    }
                    connectors.setConnectorVoltages(connectorVoltages);
                    connectors.setConnectorVoltageCode(connectorVoltages.getConnectorVoltageName());

                    ConnectorCurrents connectorCurrents = (ConnectorCurrents) session.get(ConnectorCurrents.class, connectorjaxb.getConnectorCurrentID());
                    if (connectorCurrents == null) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("ConnectorCurrentsID not Found");
                        return tingcoDevice;
                    }
                    connectors.setConnectorCurrents(connectorCurrents);
                    connectors.setConnectorCurrentCode(connectorCurrents.getConnectorCurrentName());

                    ConnectorAcdc connectorAcdc = (ConnectorAcdc) session.get(ConnectorAcdc.class, connectorjaxb.getConnectorACDCID());
                    if (connectorAcdc == null) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("ConnectorAcdcID not Found");
                        return tingcoDevice;
                    }
                    connectors.setConnectorAcdc(connectorAcdc);
                    connectors.setConnectorAcdccode(connectorAcdc.getConnectorAcdcname());

                    ProductVariants productVariants = (ProductVariants) session.get(ProductVariants.class, connectorjaxb.getProductVariantID());
                    if (productVariants == null) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("ProductVariantsID not Found");
                        return tingcoDevice;
                    }
                    connectors.setProductVariants(productVariants);

                    if (connectorjaxb.getLoadBalanceID() != null) {
                        connectors.setLoadBalanceId(connectorjaxb.getLoadBalanceID());
                    }
                    connectors.setIsEnabled(connectorjaxb.getIsEnabled());
                    connectors.setIsVisibleInApi(connectorjaxb.getIsVisibleInAPI());
                    if (connectorjaxb.getPublicComment1() != null) {
                        connectors.setPublicComment1(connectorjaxb.getPublicComment1());
                    }
                    if (connectorjaxb.getPublicComment2() != null) {
                        connectors.setPublicComment2(connectorjaxb.getPublicComment2());
                    }
                    if (connectorjaxb.getInternalComment() != null) {
                        connectors.setInternalComment(connectorjaxb.getInternalComment());
                    }
                    if (connectorjaxb.getSearchTags() != null) {
                        connectors.setSearchTags(connectorjaxb.getSearchTags());
                    }
                    if (connectorjaxb.getConnectorImageURL() != null) {
                        connectors.setConnectorImageUrl(connectorjaxb.getConnectorImageURL());
                    }
                    connectors.setLastUpdatedByUserId(sessions2.getUserId());
                    connectors.setCreatedDate(gc.getTime());
                    connectors.setUpdatedDate(gc.getTime());
                    if (deviceDAO.addConnectorToDevice(session, connectors)) {
                        tx.commit();
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Error occured");
                        return tingcoDevice;
                    }

//                    } else {
//                        tingcoDevice.getMsgStatus().setResponseCode(-1);
//                        tingcoDevice.getMsgStatus().setResponseText("A Connector with same sort order already exists. Please choose a different sort order");
//                        return tingcoDevice;
//                    }
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
            tx.rollback();
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

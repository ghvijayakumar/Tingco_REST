/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.chargePoint;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import javax.jms.Connection;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import org.apache.xmlbeans.XmlOptions;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.device.DeviceDAO;
import se.info24.devicejaxb.Devices;
import se.info24.devicejaxb.TingcoDevice;
import se.info24.group.GroupDAO;
import se.info24.ismOperationsPojo.DeviceActivationSchedules;
import se.info24.ismOperationsPojo.DeviceStatus;
import se.info24.ismOperationsPojo.DeviceStatusDataItems;
import se.info24.ismOperationsPojo.ObjectUsageRecords;
import se.info24.jaxbUtil.TingcoDeviceXML;
import se.info24.pojo.AddressType;
import se.info24.pojo.Addresses;
import se.info24.pojo.Channels;
import se.info24.pojo.Connectors;
import se.info24.pojo.Country;
import se.info24.pojo.Device;
import se.info24.pojo.DeviceTypeChannels;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupTranslationsId;
import se.info24.pojo.GroupTrusts;
import se.info24.pojo.GroupTrustsId;
import se.info24.pojo.Groups;
import se.info24.pojo.ObjectAddresses;
import se.info24.pojo.ObjectAddressesId;
import se.info24.pojo.ObjectFieldData;
import se.info24.pojo.ObjectFieldDataId;
import se.info24.objectpojo.ObjectUsageSummaryReport;
import se.info24.pojo.OgmuserAlias;
import se.info24.pojo.OgmuserAliasId;
import se.info24.pojo.UserAlias;
import se.info24.pojo.UserAliasDetails;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.UserTimeZones2;
import se.info24.pojo.UserTypes2;
import se.info24.pojo.Users2;
import se.info24.pojo.Users2Id;
import se.info24.protocols.ddm.DataContainer;
import se.info24.protocols.ddm.DataField;
import se.info24.protocols.ddm.DataGroup;
import se.info24.protocols.ddm.DataSequence;
import se.info24.protocols.ddm.DeviceDataMessage;
import se.info24.protocols.ddm.DeviceDataMessageDocument;
import se.info24.protocols.ddm.LanguageString;
import se.info24.protocols.ddm.MsgReceivers;
import se.info24.protocols.ddm.MsgSender;
import se.info24.restUtil.RestUtilDAO;
import se.info24.tingcoEV.TingcoEV;
import se.info24.user.CountryDAO;
import se.info24.user.TingcoUserXML;
import se.info24.user.UserDAO;
import se.info24.user.User_LoginsResource;
import se.info24.util.HibernateUtil;
import se.info24.util.JAXBManager;
import se.info24.util.RESTDoc;
import se.info24.util.RSAPassword;
import se.info24.util.TCMUtil;
import se.info24.util.TingcoConstants;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/chargepoints")
public class ChargePointsResource {

    @Context
    private UriInfo context;
    private ChargePointDAO dao = new ChargePointDAO();
    @Context
    private HttpServletRequest request;
    private DeviceDAO deviceDAO = new DeviceDAO();
    GroupDAO groupdao = new GroupDAO();
    CountryDAO countryDao = new CountryDAO();
    UserDAO userDao = new UserDAO();
//    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of ChargePointsResource */
    public ChargePointsResource() {
    }

    /**
     * GetChargePointDetailsByGroupandDeviceID
     * @param groupId
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getdetailsbygroupanddeviceid/rest/{rest}{deviceid:(/deviceid/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}")
    @RESTDoc(methodName = "GetChargePointDetailsByGroupandDeviceID", desc = "Get the ChargePointDetails", req_Params = {"groupid;UUID"}, opt_Params = {"GroupID;UUID", "DeviceID;UUID"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_getDetailsByGroupandDeviceID(@PathParam("groupid") String groupId, @PathParam("deviceid") String deviceId) {
        return getDetailsByGroupAndDeviceID(groupId, deviceId);
    }

    /**
     * GetChargePointDetailsByGroupandDeviceID
     * @param groupId
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getdetailsbygroupanddeviceid/json/{json}{deviceid:(/deviceid/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}")
    public TingcoDevice getJson_getDetailsByGroupandDeviceID(@PathParam("groupid") String groupId, @PathParam("deviceid") String deviceId) {
        return getDetailsByGroupAndDeviceID(groupId, deviceId);
    }

    /**
     * GetChargePointStatusDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getchargepointstatusdetails/rest/{rest}{objecttags :(/objecttags/[^/]+?)?}{status :(/status/[^/]+?)?}{statecode :(/statecode/[^/]+?)?}")
    public TingcoDevice postXml_getChargePointStatusDetails(String content, @PathParam("objecttags") String objectTags, @PathParam("status") String status, @PathParam("statecode") String stateCode) {
        return getChargePointStatusDetails(content, objectTags, status, stateCode);
    }

    /**
     * GetChargePointStatusDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getchargepointstatusdetails/json/{json}{objecttags :(/objecttags/[^/]+?)?}{status :(/status/[^/]+?)?}{statecode :(/statecode/[^/]+?)?}")
    public TingcoDevice postJson_getChargePointStatusDetails(String content, @PathParam("objecttags") String objectTags, @PathParam("status") String status, @PathParam("statecode") String stateCode) {
        return getChargePointStatusDetails(content, objectTags, status, stateCode);
    }

    /**
     * GetObjectUsageSummaryReport
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getobjectusagesummaryreport/rest/{rest}")
    public TingcoDevice postXml_getObjectUsageSummaryReport(String content) {
        return getObjectUsageSummaryReport(content);
    }

    /**
     * GetObjectUsageSummaryReport
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getobjectusagesummaryreport/json/{json}")
    public TingcoDevice postJson_getObjectUsageSummaryReport(String content) {
        return getObjectUsageSummaryReport(content);
    }

    /**
     * GetObjectUsageErrorReport
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getobjectusageerrorreport/rest/{rest}")
    public TingcoDevice postXml_getObjectUsageErrorReport(String content) {
        return getObjectUsageErrorReport(content);
    }

    /**
     * GetObjectUsageErrorReport
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getobjectusageerrorreport/json/{json}")
    public TingcoDevice postJson_getObjectUsageErrorReport(String content) {
        return getObjectUsageErrorReport(content);
    }

    /**
     * GetChargePointDetailsByUserID
     * @param userId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getdetailsbyuserid/rest/{rest}/userid/{userid}")
    public TingcoDevice getXml_getDetailsByUserID(@PathParam("userid") String userId) {
        return getDetailsByUserID(userId);
    }

    /**
     * GetChargePointDetailsByUserID
     * @param userId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getdetailsbyuserid/json/{json}/userid/{userid}")
    public TingcoDevice getJson_getDetailsByUserID(@PathParam("userid") String userId) {
        return getDetailsByUserID(userId);
    }

    /**
     * GetChargePointStatusByDID
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getstatusbydid/rest/{rest}/deviceid/{deviceid}")
    public TingcoDevice getXml_getStatusByDeviceId(@PathParam("deviceid") String deviceId) {
        return getStatusByDeviceId(deviceId);
    }

    /**
     * GetChargePointStatusByDID
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getstatusbydid/json/{json}/deviceid/{deviceid}")
    public TingcoDevice getJson_getStatusByDeviceId(@PathParam("deviceid") String deviceId) {
        return getStatusByDeviceId(deviceId);
    }

    /**
     * GetChargePointStatusByDID
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getconnectordetailsbydeviceid/rest/{rest}/deviceid/{deviceid}/countryid/{countryid}")
    public TingcoDevice getXml_getConnectorDetailsByDeviceId(@PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId) {
        return getConnectorDetailsByDeviceId(deviceId, countryId);
    }

    /**
     * GetChargePointStatusByDID
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getconnectordetailsbydeviceid/json/{json}/deviceid/{deviceid}/countryid/{countryid}")
    public TingcoDevice getJson_getConnectorDetailsByDeviceId(@PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId) {
        return getConnectorDetailsByDeviceId(deviceId, countryId);
    }

    /**
     * GetDeviceStatusByDID
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getdevicestatusbydeviceid/rest/{rest}/deviceid/{deviceid}")
    public TingcoDevice getXml_getDeviceStatusByDeviceId(@PathParam("deviceid") String deviceId) {
        return getDeviceStatusByDeviceId(deviceId);
    }

    /**
     * GetDeviceStatusByDID
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getdevicestatusbydeviceid/json/{json}/deviceid/{deviceid}")
    public TingcoDevice getJson_getDeviceStatusByDeviceId(@PathParam("deviceid") String deviceId) {
        return getDeviceStatusByDeviceId(deviceId);
    }

    /**
     * GetChargePointStatusChartByGroupandDeviceID
     * @param groupId
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getstatuschartbygroupanddeviceid/rest/{rest}{deviceid:(/deviceid/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}")
    @RESTDoc(methodName = "GetChargePointStatusChartByGroupandDeviceID", desc = "Get the ChargePointStatusChart By Group and DeviceID", req_Params = {"Groupid;UUID"}, opt_Params = {"GroupID;UUID"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoEV getXml_getStatusChartByGroupandDeviceID(@PathParam("groupid") String groupId, @PathParam("deviceid") String deviceId) {
        return getStatusChartByGroupAndDeviceID(groupId, deviceId);
    }

    /**
     * GetChargePointStatusChartByGroupandDeviceID
     * @param groupId
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getstatuschartbygroupanddeviceid/json/{json}{deviceid:(/deviceid/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}")
    public TingcoEV getJson_getStatusChartByGroupandDeviceID(@PathParam("groupid") String groupId, @PathParam("deviceid") String deviceId) {
        return getStatusChartByGroupAndDeviceID(groupId, deviceId);
    }

    /**
     * GetChargePointStatusLocationByDeviceID
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getstatuslocationbydeviceid/rest/{rest}/deviceid/{deviceid}")
    public TingcoDevice getXml_getStatusLocationByDeviceID(@PathParam("deviceid") String deviceId) {
        return getStatusLocationByDeviceID(deviceId);
    }

    /**
     * GetChargePointStatusLocationByDeviceID
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getstatuslocationbydeviceid/json/{json}/deviceid/{deviceid}")
    public TingcoDevice getJson_getStatusLocationByDeviceID(@PathParam("deviceid") String deviceId) {
        return getStatusLocationByDeviceID(deviceId);
    }

    /**
     * GetChargePointStatusHistoryByDeviceID
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getstatushistorybydeviceid/rest/{rest}/deviceid/{deviceid}")
    public TingcoDevice getXml_getStatusHistoryByDeviceID(@PathParam("deviceid") String deviceId) {
        return getStatusHistoryByDeviceID(deviceId);
    }

    /**
     * GetChargePointStatusHistoryByDeviceID
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getstatushistorybydeviceid/json/{json}/deviceid/{deviceid}")
    public TingcoDevice getJson_getStatusHistoryByDeviceID(@PathParam("deviceid") String deviceId) {
        return getStatusHistoryByDeviceID(deviceId);
    }

    /**
     * DeviceControl
     * @param deviceId
     * @param fieldName
     * @param fieldValue
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/devicecontrol/rest/{rest}/deviceid/{deviceid}/fieldname/{fieldname}/fieldvalue/{fieldvalue}")
    public TingcoDevice getXML_DeviceControl(@PathParam("deviceid") String deviceId, @PathParam("fieldname") String fieldName, @PathParam("fieldvalue") String fieldValue) {
        return getDeviceControl(deviceId, fieldName, fieldValue);
    }

    /**
     * DeviceControl
     * @param deviceId
     * @param fieldName
     * @param fieldValue
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/devicecontrol/json/{json}/deviceid/{deviceid}/fieldname/{fieldname}/fieldvalue/{fieldvalue}")
    public TingcoDevice getJSON_DeviceControl(@PathParam("deviceid") String deviceId, @PathParam("fieldname") String fieldName, @PathParam("fieldvalue") String fieldValue) {
        return getDeviceControl(deviceId, fieldName, fieldValue);
    }

    /**
     *
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getcountriesforev/rest/{rest}")
    public TingcoEV getXML_getCountriesForEV() {
        return getCountriesForEV();
    }

    /**
     * 
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getcountriesforev/json/{json}")
    public TingcoEV getJSON_getCountriesForEV() {
        return getCountriesForEV();
    }

    /**
     * POST method for creating an instance of ChargePointsResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    /**
     *
     * @param content
     * @return TingcoEV
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addactivatekey/rest/{rest}")
    public TingcoEV postXml_AddActivateKey(String content) {
        return addActivateKey(content);
    }

    /**
     *
     * @param content
     * @return TingcoEV
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addactivatekey/json/{json}")
    public TingcoEV postJson_AddActivateKey(String content) {
        return addActivateKey(content);
    }

    /**
     *
     * @param content
     * @return TingcoEV
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addactivatekeybmw/rest/{rest}")
    public TingcoEV postXml_AddActivateKeyBMW(String content) {
        return addActivateKeyBMW(content);
    }

    /**
     *
     * @param content
     * @return TingcoEV
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addactivatekeybmw/json/{json}")
    public TingcoEV postJson_AddActivateKeyBMW(String content) {
        return addActivateKeyBMW(content);
    }

    /**
     *
     * @param content
     * @return TingcoEV
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/exportxsl/rest/{rest}/countryid/{countryid}")
    public TingcoDevice postXml_ExportXsl(String content, @PathParam("countryid") int countryId) {
        return exportXsl(content, countryId);
    }

    /**
     *
     * @param content
     * @return TingcoEV
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/exportxsl/json/{json}/countryid/{countryid}")
    public TingcoDevice postJson_ExportXsl(String content, @PathParam("countryid") int countryId) {
        return exportXsl(content, countryId);
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public ChargePointResource getChargePointResource() {
        return new ChargePointResource();
    }

    private TingcoDevice exportXsl(String content, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
        Session ismopersession = null;
        Session ismSession = null;
        Devices devices = new Devices();
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    ismopersession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    ismSession = HibernateUtil.getSessionFactory().openSession();

                    se.info24.devicejaxbPost.TingcoDevice devicesJaxb = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    List<se.info24.devicejaxbPost.Device> devicejaxb = devicesJaxb.getDevices().getDevice();
                    for (se.info24.devicejaxbPost.Device device : devicejaxb) {

                        List<Connectors> connectorses = deviceDAO.getConnectorsLinkedToDevice(device.getDeviceID(), ismSession);
                        if (connectorses.isEmpty()) {
                            continue;
                        }
                        List<String> deviceDataItemIDList = new ArrayList<String>();
                        List<String> currentId = new ArrayList<String>();
                        List<String> voltageId = new ArrayList<String>();
                        for (Connectors connectorse : connectorses) {
                            deviceDataItemIDList.add(connectorse.getDeviceDataItems().getDeviceDataItemId());
                        }
                        if (deviceDataItemIDList.isEmpty()) {
                            continue;
                        }
                        List<se.info24.pojo.DeviceDataItems> ddiList = deviceDAO.getDeviceDataItemsByIdList(ismSession, deviceDataItemIDList, 50);
                        if (ddiList.isEmpty()) {
                            continue;
                        }

                        List<DeviceStatusDataItems> deviceList = deviceDAO.getDeviceStatusDataItemsBydeviceDataItemIds(ismopersession, device.getDeviceID(), deviceDataItemIDList);

                        if (!ddiList.isEmpty()) {
                            se.info24.devicejaxb.Device dev = tingcoDeviceXML.buildExportxsl(connectorses, deviceList, ddiList, countryId, ismSession);
                            devices.getDevice().add(dev);
                        } else {
                            continue;
                        }

                    }
                    if (devices.getDevice().size() > 0) {
                        tingcoDevice.setDevices(devices);
                        return tingcoDevice;
                    } else {
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
            if (ismSession != null) {
                ismSession.close();
            }
            if (ismopersession != null) {
                ismopersession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    public TingcoEV addActivateKeyBMW(String content) {
        long requestedTime = System.currentTimeMillis();
        TingcoEV tingcoEv = null;
        Session session = null;
        TingcoUserXML userXML = new TingcoUserXML();
        Transaction tx = null;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            tingcoEv = userXML.buildTingcoUserTemplate();
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            se.info24.tingcoEV.TingcoEV tingcoEVJaxb = (se.info24.tingcoEV.TingcoEV) JAXBManager.getInstance().unMarshall(content, se.info24.tingcoEV.TingcoEV.class);
            se.info24.tingcoEV.ChargePoints chargePointJaxb = tingcoEVJaxb.getChargePoints();
            for (se.info24.tingcoEV.Activatekey activateKey : chargePointJaxb.getActivatekey()) {
                String userAlia = activateKey.getUserAlias().replaceFirst("^0*", "");
                List<UserAlias> uaList = userDao.getUserAliasByUserAliasTypeIdAndUserAlias(session, userAlia, activateKey.getUserAliasTypeID());
                if (uaList.isEmpty()) {
                    tingcoEv.getMsgStatus().setResponseCode(-1);
                    tingcoEv.getMsgStatus().setResponseText("Key Not Exists");
                    return tingcoEv;
                } else {
                    UserAlias userAlias = uaList.get(0);
                    if (isActivated(userAlias)) {
                        tingcoEv.getMsgStatus().setResponseCode(-1);
                        tingcoEv.getMsgStatus().setResponseText("Already Activated");
                        return tingcoEv;
                    } else {
                        /**
                         * Key Activation
                         */
                        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                        Groups groups = createNewGroup(session, activateKey);
                        List<Country> countries = countryDao.getAllCountries(session);
                        List<GroupTranslations> groupTransList = createNewGroupTranslations(countries, activateKey, groups);
                        List<Users2> users2List = userDao.getUserByAppIdEmails(groups.getDomainId(), activateKey.getUserEmail(), session);
                        Users2 user = null;
                        if (!users2List.isEmpty()) {
                            user = users2List.get(0);
//                            tingcoEv.getMsgStatus().setResponseCode(-1);
//                            tingcoEv.getMsgStatus().setResponseText("Email is Already existing for " + groups.getDomainId() + " domain ID");
//                            return tingcoEv;
                        } else {
                            user = createNewUser(activateKey, groups, session);
                        }

                        /**
                         * Update UserAlias
                         */
                        userAlias.setUserEmail(activateKey.getUserEmail());
                        userAlias.setUserId(user.getUserId());
                        userAlias.setGroupId(groups.getGroupId());
                        userAlias.setFirstName(activateKey.getFirstName());
                        userAlias.setLastName(activateKey.getLastName());

                        userAlias.setUserMobilePhone(activateKey.getUserMobilePhone());
                        userAlias.setIsEnabled(1);
                        userAlias.setActiveFromDate(gc.getTime());
                        userAlias.setUpdatedDate(gc.getTime());
//                        gc.setTime(new Date(Long.valueOf(activateKey.getActiveToDate())));
                        gc.add(Calendar.MONTH, 12);
                        userAlias.setActiveToDate(gc.getTime());
//                        userAlias.setCreditAmount(Double.valueOf(1));   Then please change the code so that you don’t do any update (no hardcoded values) on these two UserAlias table columns.
                        Country country = countryDao.getCountryById(activateKey.getCountryID(), session);
                        userAlias.setCreditAmountCurrencyId(country.getCurrency().getCurrencyId());
//                        userAlias.setCredits(100);  All the other columns should be same as it is now.
                        userAlias.setSocialSecurityNumber(activateKey.getSocialSecurityNumber());

                        /**
                         * Insert/Update UserAliasDetails
                         */
                        gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                        UserAliasDetails userAliasDetails = userDao.getUserAliasDetailsByID(session, userAlias.getUserAliasId());
                        if (userAliasDetails == null) {
                            userAliasDetails = new UserAliasDetails();
                            userAliasDetails.setUserAliasId(userAlias.getUserAliasId());
                            userAliasDetails.setIsCreditCard(0);
                            userAliasDetails.setIsServiceKey(0);
                            userAliasDetails.setIapproveSendingMarketingInfo(activateKey.getIApproveSendingMarketingInfo());
                            userAliasDetails.setIapproveTermsOfBusiness(activateKey.getIApproveTermsOfBusiness());
                            userAliasDetails.setCreatedDate(gc.getTime());
                            userAliasDetails.setUpdatedDate(gc.getTime());
                            userAliasDetails.setLastUpdatedByUserId(user.getUserId());
                        } else {
                            userAliasDetails.setUserAliasId(userAlias.getUserAliasId());
                            userAliasDetails.setIsCreditCard(0);
                            userAliasDetails.setIsServiceKey(0);
                            userAliasDetails.setIapproveSendingMarketingInfo(activateKey.getIApproveSendingMarketingInfo());
                            userAliasDetails.setIapproveTermsOfBusiness(activateKey.getIApproveTermsOfBusiness());
                            userAliasDetails.setUpdatedDate(gc.getTime());
                            userAliasDetails.setLastUpdatedByUserId(user.getUserId());
                        }
                        /**
                         * Link Key to ObjectGroups
                         */
                        OgmuserAlias ogmUserAlias = new OgmuserAlias();
                        ogmUserAlias.setId(new OgmuserAliasId(userAlias.getUserAliasId(), activateKey.getObjectGroupID()));
                        ogmUserAlias.setLastUpdatedByUserId(user.getUserId());
                        ogmUserAlias.setCreatedDate(gc.getTime());
                        ogmUserAlias.setUpdatedDate(gc.getTime());
                        /**
                         * Set GroupTrusts
                         */
                        GroupTrusts groupTrustsID = new GroupTrusts();
                        groupTrustsID.setId(new GroupTrustsId(groups.getGroupId(), activateKey.getTrustGroupID(), activateKey.getUserRoleID()));
                        groupTrustsID.setLastUpdatedByUserId(user.getUserId());
                        groupTrustsID.setActiveFromDate(gc.getTime());
                        groupTrustsID.setCreatedDate(gc.getTime());
                        groupTrustsID.setUpdatedDate(gc.getTime());
                        groupTrustsID.setActiveToDate(gc.getTime());

                        GroupTrusts groupParentId = null;
                        if (!activateKey.getTrustGroupID().equalsIgnoreCase(activateKey.getGroupParentID())) {
                            groupParentId = new GroupTrusts();
                            groupParentId.setId(new GroupTrustsId(groups.getGroupId(), activateKey.getGroupParentID(), activateKey.getUserRoleID()));
                            groupParentId.setLastUpdatedByUserId(user.getUserId());
                            groupParentId.setActiveFromDate(gc.getTime());
                            groupParentId.setCreatedDate(gc.getTime());
                            groupParentId.setUpdatedDate(gc.getTime());
                            groupParentId.setActiveToDate(gc.getTime());
                        }

                        GroupTrusts groupInfo = null;
                        if (!activateKey.getTrustGroupID().equalsIgnoreCase(activateKey.getInfo24GroupID()) && !activateKey.getGroupParentID().equalsIgnoreCase(activateKey.getInfo24GroupID())) {
                            groupInfo = new GroupTrusts();
                            groupInfo.setId(new GroupTrustsId(groups.getGroupId(), activateKey.getInfo24GroupID(), activateKey.getUserRoleID()));
                            groupInfo.setLastUpdatedByUserId(user.getUserId());
                            groupInfo.setActiveFromDate(gc.getTime());
                            groupInfo.setCreatedDate(gc.getTime());
                            groupInfo.setUpdatedDate(gc.getTime());
                            groupInfo.setActiveToDate(gc.getTime());
                        }
                        /**
                         * Insert Address
                         */
                        gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                        Addresses address = new Addresses();
                        address.setAddressId(UUID.randomUUID().toString().toUpperCase());
                        address.setAddressName("Invoice address");
                        address.setAddressDescription("Invoice address");
                        AddressType addressType = new AddressType();
                        addressType.setAddressTypeId(3);
                        address.setAddressType(addressType);
                        address.setAddressStreet(activateKey.getAddressStreet());
                        address.setAddressCity(activateKey.getAddressCity());
                        address.setAddressZip(activateKey.getAddressZip());
                        address.setCountry(country);
                        address.setAddressEmail(activateKey.getUserEmail());
                        address.setAddressMobile(activateKey.getUserMobilePhone());
                        address.setCreatedDate(gc.getTime());
                        address.setUserId(user.getUserId());
                        address.setUpdatedDate(gc.getTime());

                        /**
                         * Insert ObjectAddress For User and organization
                         */
                        ObjectAddresses objAddress = new ObjectAddresses();
                        objAddress.setAddressId(address.getAddressId());
                        objAddress.setId(new ObjectAddressesId(user.getUserId(), address.getAddressType().getAddressTypeId()));
                        objAddress.setCreatedDate(gc.getTime());
                        objAddress.setLastUpdatedUserId(user.getUserId());
                        objAddress.setUpdatedDate(gc.getTime());

                        ObjectAddresses objOrgAddress = new ObjectAddresses();
                        objOrgAddress.setAddressId(address.getAddressId());
                        objOrgAddress.setId(new ObjectAddressesId(groups.getGroupId(), address.getAddressType().getAddressTypeId()));
                        objOrgAddress.setCreatedDate(gc.getTime());
                        objOrgAddress.setLastUpdatedUserId(user.getUserId());
                        objOrgAddress.setUpdatedDate(gc.getTime());
                        /**
                         * ObjectFieldData
                         */
                        ObjectFieldData objFieldDataSSN = new ObjectFieldData();
                        ObjectFieldDataId id = new ObjectFieldDataId();
                        id.setObjectId(user.getUserId());
                        id.setFieldId("f4ef81e9-ce2e-4112-be71-c37b20d4578c".toUpperCase());
                        objFieldDataSSN.setId(id);
                        objFieldDataSSN.setFieldValue(activateKey.getSocialSecurityNumber());
                        objFieldDataSSN.setCreatedDate(gc.getTime());
                        objFieldDataSSN.setUpdatedDate(gc.getTime());
                        objFieldDataSSN.setLastUpdatedByUserId(user.getUserId());

                        ObjectFieldData objFieldDataLP = null;
                        if (activateKey.getLicensePlate() != null) {
                            objFieldDataLP = new ObjectFieldData();
                            id = new ObjectFieldDataId();
                            id.setObjectId(user.getUserId());
                            id.setFieldId("25488b29-cba6-4361-bb22-f3aff0bf6ba6".toUpperCase());
                            objFieldDataLP.setId(id);
                            objFieldDataLP.setFieldValue(activateKey.getLicensePlate());
                            objFieldDataLP.setCreatedDate(gc.getTime());
                            objFieldDataLP.setUpdatedDate(gc.getTime());
                            objFieldDataLP.setLastUpdatedByUserId(user.getUserId());
                        }


                        session.saveOrUpdate(groups);
                        session.flush();
                        session.clear();
                        session.saveOrUpdate(user);
                        if (activateKey.getPurchaseDate() != null) {// As per jeya skype chat we have done the optional PurchaseDate.12/12/14
                            ObjectFieldData objFieldDataPD = new ObjectFieldData();
                            id = new ObjectFieldDataId();
                            id.setObjectId(user.getUserId());
                            id.setFieldId("cbacb676-b8c5-44bb-aab6-2f07afa34e66".toUpperCase());
                            objFieldDataPD.setId(id);
                            Date purchaseDate = formatter.parse(activateKey.getPurchaseDate());
                            objFieldDataPD.setFieldValue(formatter.format(purchaseDate));
                            objFieldDataPD.setCreatedDate(gc.getTime());
                            objFieldDataPD.setUpdatedDate(gc.getTime());
                            objFieldDataPD.setLastUpdatedByUserId(user.getUserId());
                            session.saveOrUpdate(objFieldDataPD);
                        }

                        session.saveOrUpdate(userAliasDetails);
                        session.saveOrUpdate(userAlias);
                        session.saveOrUpdate(ogmUserAlias);
                        for (GroupTranslations gt : groupTransList) {
                            session.saveOrUpdate(gt);
                        }
                        session.saveOrUpdate(groupTrustsID);
                        if (groupInfo != null) {
                            session.saveOrUpdate(groupInfo);
                        }
                        if (groupParentId != null) {
                            session.saveOrUpdate(groupParentId);
                        }
                        session.saveOrUpdate(address);
                        session.saveOrUpdate(objAddress);
                        session.saveOrUpdate(objOrgAddress);
                        session.saveOrUpdate(objFieldDataSSN);
                        if (objFieldDataLP != null) {
                            session.saveOrUpdate(objFieldDataLP);
                        }

                    }
                }
            }
            tx.commit();
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            if (tx != null) {
                tx.rollback();
            }
            tingcoEv.getMsgStatus().setResponseCode(-1);
            tingcoEv.getMsgStatus().setResponseText("Error Occured while Adding :: " + e.getMessage());
            return tingcoEv;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoEv;
    }

    public TingcoEV addActivateKey(String content) {
        long requestedTime = System.currentTimeMillis();
        TingcoEV tingcoEv = null;
        Session session = null;
        TingcoUserXML userXML = new TingcoUserXML();
        Transaction tx = null;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            tingcoEv = userXML.buildTingcoUserTemplate();
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            se.info24.tingcoEV.TingcoEV tingcoEVJaxb = (se.info24.tingcoEV.TingcoEV) JAXBManager.getInstance().unMarshall(content, se.info24.tingcoEV.TingcoEV.class);
            se.info24.tingcoEV.ChargePoints chargePointJaxb = tingcoEVJaxb.getChargePoints();
            for (se.info24.tingcoEV.Activatekey activateKey : chargePointJaxb.getActivatekey()) {
                List<UserAlias> uaList = userDao.getUserAliasByUserAliasTypeIdAndUserAlias(session, activateKey.getUserAlias(), activateKey.getUserAliasTypeID());
                if (uaList.isEmpty()) {
                    tingcoEv.getMsgStatus().setResponseCode(-1);
                    tingcoEv.getMsgStatus().setResponseText("Key Not Exists");
                    return tingcoEv;
                } else {
                    UserAlias userAlias = uaList.get(0);
                    if (isActivated(userAlias)) {
                        tingcoEv.getMsgStatus().setResponseCode(-1);
                        tingcoEv.getMsgStatus().setResponseText("Already Activated");
                        return tingcoEv;
                    } else {
                        /**
                         * Key Activation
                         */
                        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                        Groups groups = createNewGroup(session, activateKey);
                        List<Country> countries = countryDao.getAllCountries(session);
                        List<GroupTranslations> groupTransList = createNewGroupTranslations(countries, activateKey, groups);
                        Users2 user = createNewUser(activateKey, groups, session);
                        /**
                         * Update UserAlias
                         */
                        userAlias.setUserId(user.getUserId());
                        userAlias.setGroupId(groups.getGroupId());
                        userAlias.setFirstName(activateKey.getFirstName());
                        userAlias.setLastName(activateKey.getLastName());
                        userAlias.setUserEmail(activateKey.getUserEmail());
                        userAlias.setUserMobilePhone(activateKey.getUserMobilePhone());
                        userAlias.setIsEnabled(1);
                        userAlias.setActiveFromDate(gc.getTime());
                        gc.setTime(new Date(Long.valueOf(activateKey.getActiveToDate())));
                        userAlias.setActiveToDate(gc.getTime());
                        userAlias.setCreditAmount(Double.valueOf(1));
                        Country country = countryDao.getCountryById(activateKey.getCountryID(), session);
                        userAlias.setCreditAmountCurrencyId(country.getCurrency().getCurrencyId());
                        userAlias.setCredits(100000);
                        userAlias.setSocialSecurityNumber(activateKey.getSocialSecurityNumber());
                        userAlias.setUpdatedDate(gc.getTime());
                        /**
                         * Insert/Update UserAliasDetails
                         */
                        gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                        UserAliasDetails userAliasDetails = userDao.getUserAliasDetailsByID(session, userAlias.getUserAliasId());
                        if (userAliasDetails == null) {
                            userAliasDetails = new UserAliasDetails();
                            userAliasDetails.setUserAliasId(userAlias.getUserAliasId());
                            userAliasDetails.setIsCreditCard(0);
                            userAliasDetails.setIsServiceKey(0);
                            userAliasDetails.setIapproveSendingMarketingInfo(activateKey.getIApproveSendingMarketingInfo());
                            userAliasDetails.setIapproveTermsOfBusiness(activateKey.getIApproveTermsOfBusiness());
                            userAliasDetails.setCreatedDate(gc.getTime());
                            userAliasDetails.setUpdatedDate(gc.getTime());
                            userAliasDetails.setLastUpdatedByUserId(user.getUserId());
                        } else {
                            userAliasDetails.setUserAliasId(userAlias.getUserAliasId());
                            userAliasDetails.setIsCreditCard(0);
                            userAliasDetails.setIsServiceKey(0);
                            userAliasDetails.setIapproveSendingMarketingInfo(activateKey.getIApproveSendingMarketingInfo());
                            userAliasDetails.setIapproveTermsOfBusiness(activateKey.getIApproveTermsOfBusiness());
                            userAliasDetails.setUpdatedDate(gc.getTime());
                            userAliasDetails.setLastUpdatedByUserId(user.getUserId());
                        }
                        /**
                         * Link Key to ObjectGroups
                         */
                        OgmuserAlias ogmUserAlias = new OgmuserAlias();
                        ogmUserAlias.setId(new OgmuserAliasId(userAlias.getUserAliasId(), activateKey.getObjectGroupID()));
                        ogmUserAlias.setLastUpdatedByUserId(user.getUserId());
                        ogmUserAlias.setCreatedDate(gc.getTime());
                        ogmUserAlias.setUpdatedDate(gc.getTime());
                        /**
                         * Set GroupTrusts
                         */
                        GroupTrusts groupTrustsID = new GroupTrusts();
                        groupTrustsID.setId(new GroupTrustsId(groups.getGroupId(), activateKey.getTrustGroupID(), activateKey.getUserRoleID()));
                        groupTrustsID.setLastUpdatedByUserId(user.getUserId());
                        groupTrustsID.setActiveFromDate(gc.getTime());
                        groupTrustsID.setCreatedDate(gc.getTime());
                        groupTrustsID.setUpdatedDate(gc.getTime());
                        groupTrustsID.setActiveToDate(gc.getTime());

                        GroupTrusts groupParentId = null;
                        if (!activateKey.getTrustGroupID().equalsIgnoreCase(activateKey.getGroupParentID())) {
                            groupParentId = new GroupTrusts();
                            groupParentId.setId(new GroupTrustsId(groups.getGroupId(), activateKey.getGroupParentID(), activateKey.getUserRoleID()));
                            groupParentId.setLastUpdatedByUserId(user.getUserId());
                            groupParentId.setActiveFromDate(gc.getTime());
                            groupParentId.setCreatedDate(gc.getTime());
                            groupParentId.setUpdatedDate(gc.getTime());
                            groupParentId.setActiveToDate(gc.getTime());
                        }

                        GroupTrusts groupInfo = null;
                        if (!activateKey.getTrustGroupID().equalsIgnoreCase(activateKey.getInfo24GroupID()) && !activateKey.getGroupParentID().equalsIgnoreCase(activateKey.getInfo24GroupID())) {
                            groupInfo = new GroupTrusts();
                            groupInfo.setId(new GroupTrustsId(groups.getGroupId(), activateKey.getInfo24GroupID(), activateKey.getUserRoleID()));
                            groupInfo.setLastUpdatedByUserId(user.getUserId());
                            groupInfo.setActiveFromDate(gc.getTime());
                            groupInfo.setCreatedDate(gc.getTime());
                            groupInfo.setUpdatedDate(gc.getTime());
                            groupInfo.setActiveToDate(gc.getTime());
                        }
                        /**
                         * Insert Address
                         */
                        gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                        Addresses address = new Addresses();
                        address.setAddressId(UUID.randomUUID().toString().toUpperCase());
                        address.setAddressName("Invoice address");
                        address.setAddressDescription("Invoice address");
                        AddressType addressType = new AddressType();
                        addressType.setAddressTypeId(3);
                        address.setAddressType(addressType);
                        address.setAddressStreet(activateKey.getAddressStreet());
                        address.setAddressCity(activateKey.getAddressCity());
                        address.setAddressZip(activateKey.getAddressZip());
                        address.setCountry(country);
                        address.setAddressEmail(activateKey.getUserEmail());
                        address.setAddressMobile(activateKey.getUserMobilePhone());
                        address.setCreatedDate(gc.getTime());
                        address.setUserId(user.getUserId());
                        address.setUpdatedDate(gc.getTime());

                        /**
                         * Insert ObjectAddress For User and organization
                         */
                        ObjectAddresses objAddress = new ObjectAddresses();
                        objAddress.setAddressId(address.getAddressId());
                        objAddress.setId(new ObjectAddressesId(user.getUserId(), address.getAddressType().getAddressTypeId()));
                        objAddress.setCreatedDate(gc.getTime());
                        objAddress.setLastUpdatedUserId(user.getUserId());
                        objAddress.setUpdatedDate(gc.getTime());

                        ObjectAddresses objOrgAddress = new ObjectAddresses();
                        objOrgAddress.setAddressId(address.getAddressId());
                        objOrgAddress.setId(new ObjectAddressesId(groups.getGroupId(), address.getAddressType().getAddressTypeId()));
                        objOrgAddress.setCreatedDate(gc.getTime());
                        objOrgAddress.setLastUpdatedUserId(user.getUserId());
                        objOrgAddress.setUpdatedDate(gc.getTime());
                        /**                  
                         * ObjectFieldData
                         */
                        ObjectFieldData objFieldDataSSN = new ObjectFieldData();
                        ObjectFieldDataId id = new ObjectFieldDataId();
                        id.setObjectId(user.getUserId());
                        id.setFieldId("f4ef81e9-ce2e-4112-be71-c37b20d4578c".toUpperCase());
                        objFieldDataSSN.setId(id);
                        objFieldDataSSN.setFieldValue(activateKey.getSocialSecurityNumber());
                        objFieldDataSSN.setCreatedDate(gc.getTime());
                        objFieldDataSSN.setUpdatedDate(gc.getTime());
                        objFieldDataSSN.setLastUpdatedByUserId(user.getUserId());

                        ObjectFieldData objFieldDataLP = new ObjectFieldData();
                        id = new ObjectFieldDataId();
                        id.setObjectId(user.getUserId());
                        id.setFieldId("25488b29-cba6-4361-bb22-f3aff0bf6ba6".toUpperCase());
                        objFieldDataLP.setId(id);
                        objFieldDataLP.setFieldValue(activateKey.getLicensePlate());
                        objFieldDataLP.setCreatedDate(gc.getTime());
                        objFieldDataLP.setUpdatedDate(gc.getTime());
                        objFieldDataLP.setLastUpdatedByUserId(user.getUserId());

                        ObjectFieldData objFieldDataPD = new ObjectFieldData();
                        id = new ObjectFieldDataId();
                        id.setObjectId(user.getUserId());
                        id.setFieldId("cbacb676-b8c5-44bb-aab6-2f07afa34e66".toUpperCase());
                        objFieldDataPD.setId(id);
                        Date purchaseDate = formatter.parse(activateKey.getPurchaseDate());
//                        Date purchaseDate = new Date(Long.valueOf(activateKey.getPurchaseDate()));
//                        objFieldDataPD.setFieldValue(formatter.format(purchaseDate));
                        objFieldDataPD.setFieldValue(formatter.format(purchaseDate));
                        objFieldDataPD.setCreatedDate(gc.getTime());
                        objFieldDataPD.setUpdatedDate(gc.getTime());
                        objFieldDataPD.setLastUpdatedByUserId(user.getUserId());

                        session.saveOrUpdate(groups);
                        session.saveOrUpdate(user);

                        session.saveOrUpdate(userAliasDetails);
                        session.saveOrUpdate(userAlias);
                        session.saveOrUpdate(ogmUserAlias);
                        for (GroupTranslations gt : groupTransList) {
                            session.saveOrUpdate(gt);
                        }
                        session.saveOrUpdate(groupTrustsID);
                        if (groupInfo != null) {
                            session.saveOrUpdate(groupInfo);
                        }
                        if (groupParentId != null) {
                            session.saveOrUpdate(groupParentId);
                        }
                        session.saveOrUpdate(address);
                        session.saveOrUpdate(objAddress);
                        session.saveOrUpdate(objOrgAddress);
                        session.saveOrUpdate(objFieldDataSSN);
                        session.saveOrUpdate(objFieldDataLP);
                        session.saveOrUpdate(objFieldDataPD);
                    }
                }
            }
            tx.commit();
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            if (tx != null) {
                tx.rollback();
            }
            tingcoEv.getMsgStatus().setResponseCode(-1);
            tingcoEv.getMsgStatus().setResponseText("Error Occured while Adding :: " + e.getMessage());
            return tingcoEv;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoEv;
    }

    public boolean isActivated(UserAlias ua) {
        long requestedTime = System.currentTimeMillis();
        boolean result = true;
//        if (ua.getIsEnabled() == 0 && ua.getUserId() == null && ua.getSocialSecurityNumber() == null && ua.getFirstUseDate() == null) {
//            result = false;
//        }
        if (ua.getUserId() == null) {
            result = false;
        }
        delayLog(requestedTime);
        return result;
    }

    public Groups createNewGroup(Session session, se.info24.tingcoEV.Activatekey activateKey) {
        long requestedTime = System.currentTimeMillis();
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        Groups groups = new Groups();
        groups.setGroupId(UUID.randomUUID().toString().toUpperCase());
        groups.setGroupParentId(activateKey.getGroupParentID());
        groups.setDisplayOrder(1);
        groups.setIsWebShopUser(0);
        groups.setGroupTypeId(activateKey.getGroupTypeID());
        Groups parentGroup = groupdao.getGroupByGroupId(activateKey.getGroupParentID(), session);
        if (parentGroup != null) {
            groups.setDomainId(parentGroup.getDomainId());
        }
        groups.setOrganizationNumber(activateKey.getSocialSecurityNumber());
        groups.setCreatedDate(gc.getTime());
        groups.setUpdatedDate(gc.getTime());
        delayLog(requestedTime);
        return groups;
    }

    public List<GroupTranslations> createNewGroupTranslations(List<Country> countries, se.info24.tingcoEV.Activatekey activateKey, Groups groups) {
        long requestedTime = System.currentTimeMillis();
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        List<GroupTranslations> list = new ArrayList<GroupTranslations>();
        for (Country country : countries) {
            GroupTranslations gt = new GroupTranslations();
            gt.setId(new GroupTranslationsId(groups.getGroupId(), country.getCountryId()));
            gt.setGroups(groups);
            gt.setCountry(country);
            gt.setGroupName(activateKey.getFirstName() + " " + activateKey.getLastName());
            gt.setGroupDescription("Key activated Organization");
            gt.setCreatedDate(gc.getTime());
            gt.setUpdatedDate(gc.getTime());
            list.add(gt);
        }
        delayLog(requestedTime);
        return list;
    }

    public Users2 createNewUser(se.info24.tingcoEV.Activatekey activateKey, Groups groups, Session session) {
        long requestedTime = System.currentTimeMillis();
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        Users2 user = new Users2();
        user.setUserId(UUID.randomUUID().toString().toUpperCase());
        user.setId(new Users2Id(activateKey.getUserEmail(), groups.getDomainId()));
        user.setGroupId(groups.getGroupId());
        user.setPassword(RSAPassword.encryptedPwd(activateKey.getPassword()));
        user.setFirstName(activateKey.getFirstName());
        user.setLastName(activateKey.getLastName());
        user.setUserEmail(activateKey.getUserEmail());
        user.setUserMobilePhone(activateKey.getUserMobilePhone());
        user.setCountryId(activateKey.getCountryID());
        if (activateKey.getIsLockedOut() != null) {
            user.setIsLockedOut(Integer.valueOf(activateKey.getIsLockedOut()));
        } else {
            user.setIsLockedOut(1);
        }
        user.setChangePwdRequired(1);
        user.setFailedPasswordAttemptCount(0);
        UserTypes2 userType = userDao.getUserTypes2ById(session, activateKey.getUserTypeID());
        if (userType == null) {
            userType = new UserTypes2();
            userType.setUserTypeId(activateKey.getUserTypeID());
            userType.setCreatedDate(gc.getTime());
            userType.setUpdatedDate(gc.getTime());
            userType.setLastUpdatedByUserId(user.getUserId());
        }
        user.setCreatedDate(gc.getTime());
        user.setUpdatedDate(gc.getTime());
        user.setUserTypes2(userType);
        user.setActiveFromDate(gc.getTime());
        gc.add(Calendar.YEAR, 10);
        user.setActiveToDate(gc.getTime());
        delayLog(requestedTime);
        return user;
    }

    public TingcoEV getCountriesForEV() {
        long requestedTime = System.currentTimeMillis();
        TingcoEV tingcoEv = null;
        Session session = null;
        TingcoUserXML userXML = new TingcoUserXML();
        try {
            tingcoEv = userXML.buildTingcoUserTemplate();
            session = HibernateUtil.getSessionFactory().openSession();
            List<Country> countries = countryDao.getAllCountries(session);
            se.info24.tingcoEV.ChargePoints chargePointsJaxb = new se.info24.tingcoEV.ChargePoints();
            for (Country country : countries) {
                se.info24.tingcoEV.Country countryJaxb = new se.info24.tingcoEV.Country();
                countryJaxb.setCountryID(country.getCountryId());
                countryJaxb.setCountryName(country.getCountryName());
                chargePointsJaxb.getCountry().add(countryJaxb);
            }
            tingcoEv.setChargePoints(chargePointsJaxb);
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoEv.getMsgStatus().setResponseCode(-1);
            tingcoEv.getMsgStatus().setResponseText("Error Occured while Adding");
            return tingcoEv;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoEv;
    }

    private TingcoDevice getChargePointStatusDetails(String content, String objectTags, String status, String stateCode) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session ismOperationsSession = null;
        Session session = null;
        TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                        }
                    }
                }
                if (hasPermission) {
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    ismOperationsSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    session = HibernateUtil.getSessionFactory().openSession();

                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 == null) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("User TimeZone is not found");
                        return tingcoDevice;
                    }
                    String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                    if (!objectTags.equals("")) {
                        objectTags = objectTags.split("/")[2];
                    } else {
                        objectTags = null;
                    }
                    if (!status.equals("")) {
                        status = status.split("/")[2];
                    } else {
                        status = null;
                    }
                    if (!stateCode.equals("")) {
                        stateCode = stateCode.split("/")[2];
                    } else {
                        stateCode = null;
                    }
                    if (devices.getDevices().getDevice().size() > 0) {
                        se.info24.devicejaxbPost.Device deviceXML = devices.getDevices().getDevice().get(0);
                        if (deviceXML != null) {
                            List<Object> deviceList = deviceDAO.getChargePointStatusDetails(deviceXML, session, objectTags);
                            if (!deviceList.isEmpty()) {
                                if (deviceList.size() > 200) {
                                    deviceList.subList(0, 200);
                                    tingcoDevice.setExceeds200(1);
                                } else {
                                    tingcoDevice.setExceeds200(0);
                                }
                                tingcoDevice = tingcoDeviceXML.buildDeviceConnectionStatusXML(tingcoDevice, deviceList, ismOperationsSession, session, sessions2.getUserId(), timeZoneGMToffset, status, stateCode);
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No status found for the given input");
                                return tingcoDevice;
                            }
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("No Device found in XML");
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
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            if (ismOperationsSession != null) {
                ismOperationsSession.close();
            }
//            System.gc();
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getConnectorDetailsByDeviceId(String deviceId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
        Session ismopersession = null;
        Session ismSession = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceId should not be null");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    ismopersession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    ismSession = HibernateUtil.getSessionFactory().openSession();
                    List<Connectors> connectorses = deviceDAO.getConnectorsLinkedToDevice(deviceId, ismSession);
                    if (connectorses.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoDevice;
                    }
                    List<String> deviceDataItemIDList = new ArrayList<String>();
                    List<String> currentId = new ArrayList<String>();
                    List<String> voltageId = new ArrayList<String>();
                    for (Connectors connectorse : connectorses) {
                        deviceDataItemIDList.add(connectorse.getDeviceDataItems().getDeviceDataItemId());
                    }
                    if (deviceDataItemIDList.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoDevice;
                    }
                    List<se.info24.pojo.DeviceDataItems> ddiList = deviceDAO.getDeviceDataItemsByIdList(ismSession, deviceDataItemIDList, 50);
                    if (ddiList.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoDevice;
                    }

                    List<DeviceStatusDataItems> deviceList = deviceDAO.getDeviceStatusDataItemsBydeviceDataItemIds(ismopersession, deviceId, deviceDataItemIDList);

                    if (!ddiList.isEmpty()) {
                        tingcoDevice = tingcoDeviceXML.buildDeviceStatusDataItemsByDeviceId(tingcoDevice, connectorses, deviceList, ddiList, countryId, ismSession);
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoDevice;
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
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (ismSession != null) {
                ismSession.close();
            }
            if (ismopersession != null) {
                ismopersession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getDetailsByGroupAndDeviceID(String groupId, String deviceId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        Session ismOperSession = null;
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
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

                if (groupId == null && deviceId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("GroupId and DeviceId both should not be null, any one should be present");
                    return tingcoDevice;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ismOperSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    List<se.info24.pojo.Device> deviceList = deviceDAO.getDeviceConnectionStatus(groupId, deviceId, session);
                    tingcoDevice = tingcoDeviceXML.buildDeviceConnectionStatussXML(tingcoDevice, deviceList, ismOperSession, session, sessions2.getUserId());
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
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            if (ismOperSession != null) {
                ismOperSession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getDetailsByUserID(String userId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        Session ismOperSession = null;
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (userId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("UserID should not be null");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ismOperSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    List<Device> deviceList = deviceDAO.getDeviceDetailsByUserId(userId, session);
                    tingcoDevice = tingcoDeviceXML.buildDeviceConnectionStatussXML(tingcoDevice, deviceList, ismOperSession, session, sessions2.getUserId());
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
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            if (ismOperSession != null) {
                ismOperSession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getDeviceControl(String deviceId, String fieldName, String fieldValue) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceID should not be null");
                    return tingcoDevice;
                }
                if (fieldName == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("FieldName should not be null");
                    return tingcoDevice;
                }
                if (fieldValue == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("FieldValue should not be null");
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
                    session = HibernateUtil.getSessionFactory().openSession();
                    Device device = deviceDAO.getDeviceById(deviceId, session);
                    DeviceDataMessageDocument ddmDoc = DeviceDataMessageDocument.Factory.newInstance();
                    DeviceDataMessage ddm = ddmDoc.addNewDeviceDataMessage();
                    ddm.setCreateRef("Info24");
                    ddm.setMsgVer(new BigDecimal("4.0"));
                    ddm.setRegionalUnits("Metric");
                    ddm.setTimeZone("UTC");
                    ddm.setMsgID(UUID.randomUUID().toString().toUpperCase());
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    ddm.setMsgTimeStamp(gc);
                    gc = new GregorianCalendar();
                    gc.add(Calendar.HOUR, 1);
                    ddm.setMsgExpiryTime(gc);
                    ddm.setMsgPriority(0);

                    MsgSender sender = ddm.addNewMsgSender();
                    LanguageString ls = sender.addNewSenderName();
                    ls.setLanguage("SE");
                    ls.setStringValue("Info24");
                    sender.setGroupID(device.getGroups().getGroupId());
                    sender.setServiceID(TingcoConstants.getServiceID());
                    MsgReceivers receivers = ddm.addNewMsgReceivers();
                    receivers.addServiceID("0");

                    DataContainer dc = ddm.addNewDeviceData();
                    DataSequence ds = dc.addNewDataSequence();
                    ds.setDeviceID(device.getDeviceId());
                    ds.setDeviceTypeID(device.getDeviceTypes().getDeviceTypeId());
                    ds.setDataItemID(UUID.randomUUID().toString());
                    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
                    ds.setDataItemTime(cal);
                    ds.setGroupID(device.getGroups().getGroupId());
                    DataGroup dg = ds.addNewDataGroup();
                    dg.setGroupName("IOData");
                    DataField df = dg.addNewDataField();
                    df.setFieldName(fieldName.toUpperCase());
                    df.setStringValue(fieldValue);

                    List<DeviceTypeChannels> deviceTypeChannelsList = deviceDAO.getDeviceTypeChannels(device.getDeviceTypes().getDeviceTypeId(), session);
                    for (DeviceTypeChannels dtc : deviceTypeChannelsList) {
                        Channels c = deviceDAO.getChannels(dtc.getId().getChannelId(), session);
                        sendDDM(ddmDoc, c.getChannelData());
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

    private TingcoDevice getDeviceStatusByDeviceId(String deviceId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
        Session ismopersession = null;
        Session ismSession = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceId should not be null");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    ismopersession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    ismSession = HibernateUtil.getSessionFactory().openSession();
                    Device device = deviceDAO.getDeviceById(deviceId, ismSession);
                    List<DeviceStatusDataItems> deviceList = deviceDAO.getDeviceStatusDataItemsByDeviceID(deviceId, ismopersession);
                    if (!deviceList.isEmpty()) {
                        List<String> deviceDataItemIDList = deviceDAO.getDeviceDataItemIdsList(deviceList);
                        List<se.info24.pojo.DeviceDataItems> ddiList = deviceDAO.getDeviceDataItemsList(ismSession, deviceDataItemIDList, 0);
                        List<DeviceActivationSchedules> das = dao.getDeviceActivationSchedulesByDeviceId(deviceId, 1000, ismopersession);
                        tingcoDevice = tingcoDeviceXML.buildDeviceStatusDataItemsByDeviceId1(tingcoDevice, deviceList, ddiList, das, device, ismSession);
                        return tingcoDevice;
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No data found");
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
            if (ismSession != null) {
                ismSession.close();
            }
            if (ismopersession != null) {
                ismopersession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getObjectUsageErrorReport(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    se.info24.devicejaxbPost.Device deviceXML = devices.getDevices().getDevice().get(0);
                    String groupingBy = deviceXML.getObjectUsageRecords().get(0).getUsageReportGroupBy();
                    List<ObjectUsageSummaryReport> usageErrorRecordsList = deviceDAO.getObjectUsageErrorReport(deviceXML, groupingBy, session);
                    if (!usageErrorRecordsList.isEmpty()) {
                        if (groupingBy.equalsIgnoreCase("UserKey")) {
                            Collections.sort(usageErrorRecordsList, new Comparator<ObjectUsageSummaryReport>() {

                                public int compare(ObjectUsageSummaryReport o1, ObjectUsageSummaryReport o2) {
                                    return (o1.getLastName()).compareToIgnoreCase(o2.getLastName());
                                }
                            });
                            Collections.sort(usageErrorRecordsList, new Comparator<ObjectUsageSummaryReport>() {

                                public int compare(ObjectUsageSummaryReport o1, ObjectUsageSummaryReport o2) {
                                    return (o1.getFirstName()).compareToIgnoreCase(o2.getFirstName());
                                }
                            });
                        } else {
                            Collections.sort(usageErrorRecordsList, new Comparator<ObjectUsageSummaryReport>() {

                                public int compare(ObjectUsageSummaryReport o1, ObjectUsageSummaryReport o2) {
                                    return (o1.getGroupedBy()).compareToIgnoreCase(o2.getGroupedBy());
                                }
                            });
                        }
                        tingcoDevice = tingcoDeviceXML.buildObjectUsageErrorReport(tingcoDevice, usageErrorRecordsList, groupingBy);
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Usage Report found for the given input");
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
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getObjectUsageSummaryReport(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    se.info24.devicejaxbPost.Device deviceXML = devices.getDevices().getDevice().get(0);
                    String groupingBy = deviceXML.getObjectUsageRecords().get(0).getUsageReportGroupBy();
                    List<ObjectUsageSummaryReport> usageRecordsList = deviceDAO.getObjectUsageSummaryReport(deviceXML, groupingBy, session);
                    if (!usageRecordsList.isEmpty()) {
                        if (groupingBy.equalsIgnoreCase("UserKey")) {
                            Collections.sort(usageRecordsList, new Comparator<ObjectUsageSummaryReport>() {

                                public int compare(ObjectUsageSummaryReport o1, ObjectUsageSummaryReport o2) {
                                    return (o1.getLastName()).compareToIgnoreCase(o2.getLastName());
                                }
                            });
                            Collections.sort(usageRecordsList, new Comparator<ObjectUsageSummaryReport>() {

                                public int compare(ObjectUsageSummaryReport o1, ObjectUsageSummaryReport o2) {
                                    return (o1.getFirstName()).compareToIgnoreCase(o2.getFirstName());
                                }
                            });
                        } else {
                            Collections.sort(usageRecordsList, new Comparator<ObjectUsageSummaryReport>() {

                                public int compare(ObjectUsageSummaryReport o1, ObjectUsageSummaryReport o2) {
                                    return (o1.getGroupedBy()).compareToIgnoreCase(o2.getGroupedBy());
                                }
                            });
                        }
                        tingcoDevice = tingcoDeviceXML.buildObjectUsageSummaryReport(tingcoDevice, usageRecordsList, groupingBy);
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Usage Report found for the given input");
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
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getStatusByDeviceId(String deviceId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
        Session ismopersession = null;
        Session ismSession = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceId should not be null");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    ismopersession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    ismSession = HibernateUtil.getSessionFactory().openSession();
                    List<DeviceStatusDataItems> deviceList = deviceDAO.getDeviceStatusDataItemsByDeviceID(deviceId, ismopersession);
                    if (deviceList.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoDevice;
                    }
                    List<String> deviceDataItemIDList = deviceDAO.getDeviceDataItemIdsList(deviceList);
                    if (!deviceDataItemIDList.isEmpty()) {
                        List<se.info24.pojo.DeviceDataItems> ddiList = deviceDAO.getDeviceDataItemsList(ismSession, deviceDataItemIDList, 0);
                        if (!ddiList.isEmpty()) {
                            tingcoDevice = tingcoDeviceXML.buildDeviceStatusDataItemsByDeviceId(tingcoDevice, deviceList, ddiList, ismSession);
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                            return tingcoDevice;
                        }
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
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (ismSession != null) {
                ismSession.close();
            }
            if (ismopersession != null) {
                ismopersession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoEV getStatusChartByGroupAndDeviceID(String groupId, String deviceId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = true;
        TingcoEV usageRecords = new TingcoEV();
        TingcoUserXML userXML = new TingcoUserXML();
        Session session = null;
        Session ismOperSession = null;
        try {
            usageRecords = userXML.buildTingcoUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
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

                if (groupId == null && deviceId == null) {
                    usageRecords.getMsgStatus().setResponseCode(-1);
                    usageRecords.getMsgStatus().setResponseText("GroupId and DeviceId both should not be null, any one should be present");
                    return usageRecords;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ismOperSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    List<Device> deviceList = deviceDAO.getDeviceConnectionStatus(groupId, deviceId, session);
                    List<String> dList = new ArrayList();
                    for (Device d : deviceList) {
                        dList.add(d.getDeviceId());
                    }
                    if (!dList.isEmpty()) {
                        if (deviceList.size() > 2000) {
                            dList = dList.subList(0, 2000);
                        }
                        List<ObjectUsageRecords> objectUsageRecords = dao.getStatusChart(groupId, dList, ismOperSession);
                        usageRecords = userXML.buildTingcoObjectUsageRecords(usageRecords, deviceId, objectUsageRecords, session);
                    } else {
                        usageRecords.getMsgStatus().setResponseCode(-1);
                        usageRecords.getMsgStatus().setResponseText("No Device Found");
                        return usageRecords;
                    }

                } else {
                    usageRecords.getMsgStatus().setResponseCode(-10);
                    usageRecords.getMsgStatus().setResponseText("User Permission is not given");
                    return usageRecords;
                }
            } else {
                usageRecords.getMsgStatus().setResponseCode(-3);
                usageRecords.getMsgStatus().setResponseText("User Session is Not Valid");
                return usageRecords;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            usageRecords.getMsgStatus().setResponseCode(-1);
            usageRecords.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            if (ismOperSession != null) {
                ismOperSession.close();
            }
            delayLog(requestedTime);
        }
        return usageRecords;
    }

    private TingcoDevice getStatusHistoryByDeviceID(String deviceId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
        Session ismopersession = null;
        Session ismSession = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceId should not be null");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }

                if (hasPermission) {
                    ismopersession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    ismSession = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), ismSession);
                    if (userTimeZones2 == null) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("User TimeZone is not found");
                        return tingcoDevice;
                    }
                    String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), ismSession).getTimeZoneGmtoffset();
                    List<DeviceActivationSchedules> das = dao.getDeviceActivationSchedulesByDeviceId(deviceId, 1000, ismopersession);
                    if (das.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoDevice;
                    }
                    Device device = deviceDAO.getDeviceById(deviceId, ismSession);
                    tingcoDevice = tingcoDeviceXML.buildDeviceActivationSchedules(tingcoDevice, das, device, timeZoneGMToffset);
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
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (ismSession != null) {
                ismSession.close();
            }
            if (ismopersession != null) {
                ismopersession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getStatusLocationByDeviceID(String deviceId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
        Session ismopersession = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceId should not be null");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }

                if (hasPermission) {
                    ismopersession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    List<DeviceStatus> deviceStatusList = dao.getStatusLocationByDeviceID(deviceId, ismopersession);
                    tingcoDevice = tingcoDeviceXML.buildChargePointStatusLocationXML(tingcoDevice, deviceStatusList);
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
            if (ismopersession != null) {
                ismopersession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    public void sendDDM(DeviceDataMessageDocument ddmDoc, String topicName) {
        long requestedTime = System.currentTimeMillis();
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            XmlOptions opt = new XmlOptions();
            opt.setSavePrettyPrint();
            ddmDoc.save(baos, opt);
            Connection connection = null;
            connection = (Connection) TingcoConstants.getGenericPool().borrowObject();
            javax.jms.Session pubSession = connection.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
            javax.jms.Topic topic = pubSession.createTopic(topicName);
            javax.jms.MessageProducer publisher = pubSession.createProducer(topic);
            connection.start();
            progress.message.jclient.XMLMessage xMsg = ((progress.message.jclient.Session) pubSession).createXMLMessage();
            xMsg.setText(baos.toString());
            publisher.send(xMsg);
            pubSession.close();
            TingcoConstants.getGenericPool().returnObject(connection);
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        } finally {
            delayLog(requestedTime);
        }
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}



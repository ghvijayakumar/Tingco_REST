/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.tingcovirtualgate;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
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
import javax.xml.datatype.DatatypeConfigurationException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.device.DeviceDAO;
import se.info24.group.GroupDAO;
import se.info24.ismOperationsPojo.AccessLog;
import se.info24.ismOperationsPojo.DeviceStatus;
import se.info24.ismOperationsPojo.MeasurementData;
import se.info24.ismOperationsPojo.MeasurementDataId;
import se.info24.ismOperationsPojo.ObjectConnectedState;
import se.info24.ismOperationsPojo.ObjectUsageRecords;
import se.info24.jaxbUtil.TingcoVirtualGateXML;
import se.info24.pojo.CardPrefixes;
import se.info24.pojo.ContainerDevices;
import se.info24.pojo.ContainerUserAlias;
import se.info24.pojo.ContainerUserAliasId;
import se.info24.pojo.Device;
import se.info24.pojo.ObjectGroupTranslations;
import se.info24.pojo.Ogmdevice;
import se.info24.pojo.OgmuserAlias;
import se.info24.pojo.OgmuserAliasId;
import se.info24.pojo.UserAlias;
import se.info24.pojo.UserAliasTypesInGroups;
import se.info24.pojo.UserLog;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.UserTimeZones2;
import se.info24.priceplans.PricePlanDAO;
import se.info24.products.ProductsDAO;
import se.info24.restUtil.RestUtilDAO;
import se.info24.tingcovirtualgatejaxb.ObjectGroups;
import se.info24.tingcovirtualgatejaxb.TingcoVirtualGate;
import se.info24.tingcovirtualgatejaxb.UserAliases;
import se.info24.user.UserDAO;
import se.info24.user.User_LoginsResource;
import se.info24.util.HibernateUtil;
import se.info24.util.JAXBManager;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;
import se.info24.util.TingcoConstants;

/**
 * REST Web Service
 *
 * @author Vijayakumar
 */
@Path("/tingcovirtualgates")
public class TingcoVirtualGatesResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    GregorianCalendar currentDateTime = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
    TingcoVirtualGateXML tingcoVirtualGateXML = new TingcoVirtualGateXML();

    /** Creates a new instance of TingcoVirtualGatesResource */
    public TingcoVirtualGatesResource() {
    }

    /**
     * CheckTingcoAPIConnected
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/checktingcoapiconnected/rest/{rest}/deviceid/{deviceid}")
    @RESTDoc(methodName = "CheckTingcoAPIConnected", desc = "Used to Check TingcoAPI is Connected or not in Tingco Virtual Gate", req_Params = {"DeviceID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoVirtualGate getXml_checkTingcoAPIConnected(@PathParam("deviceid") String deviceId) {
        return checkTingcoAPIConnected(deviceId);
    }

    /**
     * CheckTingcoAPIConnected
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/checktingcoapiconnected/json/{json}/deviceid/{deviceid}")
    public TingcoVirtualGate getJson_checkTingcoAPIConnected(@PathParam("deviceid") String deviceId) {
        return checkTingcoAPIConnected(deviceId);
    }

    /**
     *InsertCheckOutDetails
     * @param deviceId
     * @param userAliasID
     * @return TingcoVirtualGate
     */
    @GET
    @Produces("application/xml")
    @Path("/insertcheckoutdetails/rest/{rest}/deviceid/{deviceid}/useraliasid/{useraliasid}{istcm:(/istcm/[^/]+?)?}")
    @RESTDoc(methodName = "InsertCheckOutDetails", desc = "Used to Check TingcoAPI is Connected or not in Tingco Virtual Gate", req_Params = {"DeviceID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoVirtualGate getXml_InsertCheckOutDetails(@PathParam("deviceid") String deviceId, @PathParam("useraliasid") String userAliasID, @PathParam("istcm") String istcm) {
        return insertCheckOutDetails(deviceId, userAliasID, istcm);
    }

    /**
     *InsertCheckOutDetails
     * @param deviceId
     * @param userAliasID
     * @return TingcoVirtualGate
     */
    @GET
    @Produces("application/json")
    @Path("/insertcheckoutdetails/json/{json}/deviceid/{deviceid}/useraliasid/{useraliasid}{istcm:(/istcm/[^/]+?)?}")
    @RESTDoc(methodName = "InsertCheckOutDetails", desc = "Used to Check TingcoAPI is Connected or not in Tingco Virtual Gate", req_Params = {"DeviceID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoVirtualGate getJson_InsertCheckOutDetails(@PathParam("deviceid") String deviceId, @PathParam("useraliasid") String userAliasID, @PathParam("istcm") String istcm) {
        return insertCheckOutDetails(deviceId, userAliasID, istcm);
    }

    /**
     * InsertCheckInDetails
     * @param deviceId
     * @param userAliasId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/insertcheckindetails/rest/{rest}/deviceid/{deviceid}/useraliasid/{useraliasid}")
    @RESTDoc(methodName = "InsertCheckInDetails", desc = "Used to Insert CheckIn Details", req_Params = {"DeviceID;UUID", "UserAliasID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoVirtualGate getXml_insertcheckindetails(@PathParam("deviceid") String deviceId, @PathParam("useraliasid") String userAliasId) {
        return insertCheckInDetails(deviceId, userAliasId);
    }

    /**
     * InsertCheckInDetails
     * @param deviceId
     * @param userAliasId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/insertcheckindetails/json/{json}/deviceid/{deviceid}/useraliasid/{useraliasid}")
    public TingcoVirtualGate getJson_insertcheckindetails(@PathParam("deviceid") String deviceId, @PathParam("useraliasid") String userAliasId) {
        return insertCheckInDetails(deviceId, userAliasId);
    }

    /**
     * POST method for creating an instance of TingcoVirtualGatesResource
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
     * GetValidTicketsList
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getvalidticketslist/rest/{rest}/deviceid/{deviceid}{maxresult:(/maxresult/[^/]+?)?}")
    @RESTDoc(methodName = "GetValidTicketsList", desc = "Used to GetValidTicketsList", req_Params = {"DeviceID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoVirtualGate getXml_GetValidTicketsList(@PathParam("deviceid") String deviceId, @PathParam("maxresult") String maxResult) throws DatatypeConfigurationException {
        return getValidTicketsList(deviceId, maxResult);
    }

    /**
     * GetValidTicketsList
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getvalidticketslist/json/{json}/deviceid/{deviceid}{maxresult:(/maxresult/[^/]+?)?}")
    @RESTDoc(methodName = "GetValidTicketsList", desc = "Used to GetValidTicketsList", req_Params = {"DeviceID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoVirtualGate getJson_GetValidTicketsList(@PathParam("deviceid") String deviceId, @PathParam("maxresult") String maxResult) throws DatatypeConfigurationException {
        return getValidTicketsList(deviceId, maxResult);
    }

    /**
     * GetValidTicketsList
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getvalidticketscount/rest/{rest}/deviceid/{deviceid}")
    @RESTDoc(methodName = "GetValidTicketsCount", desc = "Get ValidTickets Count", req_Params = {"DeviceID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoVirtualGate getXml_GetValidTicketsCount(@PathParam("deviceid") String deviceId) throws DatatypeConfigurationException {
        return getValidTicketsCount(deviceId);
    }

    /**
     * GetValidTicketsList
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getvalidticketscount/json/{json}/deviceid/{deviceid}")
    @RESTDoc(methodName = "GetValidTicketsCount", desc = "Get ValidTickets Count", req_Params = {"DeviceID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoVirtualGate getJson_GetValidTicketsCount(@PathParam("deviceid") String deviceId) throws DatatypeConfigurationException {
        return getValidTicketsCount(deviceId);
    }

    /**
     * GetCardTypes
     * @param groupId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getcardtypes/rest/{rest}/groupid/{groupid}")
    @RESTDoc(methodName = "GetCardTypes", desc = "Used to GetCardTypes", req_Params = {"GroupId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoVirtualGate getXml_GetCardTypes(@PathParam("groupid") String groupId) throws DatatypeConfigurationException {
        return getCardTypes(groupId);
    }

    /**
     * GetCardTypes
     * @param groupId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getcardtypes/json/{json}/groupid/{groupid}")
    @RESTDoc(methodName = "GetCardTypes", desc = "Used to GetCardTypes", req_Params = {"GroupId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoVirtualGate getJson_GetCardTypes(@PathParam("groupid") String groupId) throws DatatypeConfigurationException {
        return getCardTypes(groupId);
    }

    /**
     * GetAllVenues
     * @param deviceId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getallvenues/rest/{rest}/deviceid/{deviceid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetAllVenues", desc = "Used to Get All Venues", req_Params = {"DeviceID;UUID", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoVirtualGate getXml_getAllVenues(@PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId) {
        return getAllVenues(deviceId, countryId);
    }

    /**
     * GetAllVenues
     * @param deviceId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getallvenues/json/{json}/deviceid/{deviceid}/countryid/{countryid}")
    public TingcoVirtualGate getJson_getAllVenues(@PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId) {
        return getAllVenues(deviceId, countryId);
    }

    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/createnewuseralias/rest/{rest}/deviceid/{deviceid}")
    @RESTDoc(methodName = "CreateNewUserAlias", desc = "Create New UserAlias", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoVirtualGate postXml_CreateNewUserAlias(String content, @PathParam("deviceid") String deviceId) {
        return createNewUserAlias(content, deviceId);
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public TingcoVirtualGateResource getTingcoVirtualGateResource() {
        return new TingcoVirtualGateResource();
    }

    private TingcoVirtualGate createNewUserAlias(String content, String deviceId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        Session ismOperationsession = null;
        Transaction tx = null;
        Transaction ismoperationTx = null;
        TingcoVirtualGate tingcoVirtualGate = null;
        boolean hasPermission = false;

        try {
            tingcoVirtualGate = tingcoVirtualGateXML.buildTingcoVirtualGateTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.ACCESS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ismOperationsession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    tx = session.beginTransaction();
                    ismoperationTx = ismOperationsession.beginTransaction();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        TingcoVirtualGate tingcoVirtualGateJaxb = (TingcoVirtualGate) JAXBManager.getInstance().unMarshall(content, TingcoVirtualGate.class);
                        UserAliases userAliases = tingcoVirtualGateJaxb.getVirtualGates().getVirtualGate().get(0).getUserAliases().get(0);
                        List<ObjectGroups> objectGroupsList = tingcoVirtualGateJaxb.getVirtualGates().getVirtualGate().get(0).getObjectGroups();
                        se.info24.pojo.UserAliasTypes userAliasTypes = (se.info24.pojo.UserAliasTypes) session.get(se.info24.pojo.UserAliasTypes.class, userAliases.getUserAliasTypeID());
                        if (userAliasTypes == null || userAliasTypes.getCardPrefixId() == null) {
                            tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
                            tingcoVirtualGate.getMsgStatus().setResponseText("CardType is not valid");
                            return tingcoVirtualGate;
                        }
                        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        GregorianCalendar gc = new GregorianCalendar();
                        gc.setTime(df1.parse(userAliases.getActiveToDate()));

                        CardPrefixes cardPrefixes = (CardPrefixes) session.get(CardPrefixes.class, userAliasTypes.getCardPrefixId());
                        long highestAccountNumber = cardPrefixes.getHighestAccountNumber();
                        String userAlias = null;
                        ProductsDAO productsDAO = new ProductsDAO();
                        while (true) {
                            userAlias = generateUserAlia(session, cardPrefixes, highestAccountNumber);
                            if (productsDAO.getUserAliasByUserAlias(session, userAlias) == null) {
                                break;
                            }
                            highestAccountNumber = highestAccountNumber + 1;
                        }
                        cardPrefixes.setHighestAccountNumber(highestAccountNumber);
                        session.saveOrUpdate(cardPrefixes);
                        UserAlias userAliaspojo = new UserAlias();
                        String userAliaId = UUID.randomUUID().toString();
                        userAliaspojo.setUserAliasId(userAliaId);
                        userAliaspojo.setUserAlias(userAlias);
                        userAliaspojo.setUserAliasTypes(userAliasTypes);
                        userAliaspojo.setGroupId(userAliases.getGroupID());
                        userAliaspojo.setFirstName(userAliases.getFirstName());
                        userAliaspojo.setLastName(userAliases.getLastName());
                        userAliaspojo.setIsEnabled(1);
                        gc.setTime(df1.parse(userAliases.getActiveFromDate()));
                        userAliaspojo.setActiveFromDate(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));

                        gc.setTime(df1.parse(userAliases.getActiveToDate()));
                        userAliaspojo.setActiveToDate(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));

                        userAliaspojo.setFirstUseDate(currentDateTime.getTime());
                        userAliaspojo.setCreditAmount(Double.parseDouble("0"));
                        userAliaspojo.setCreditAmountCurrencyId(4);
                        userAliaspojo.setCredits(1000);
                        userAliaspojo.setLastUpdatedByUserId(sessions2.getUserId());
                        userAliaspojo.setUpdatedDate(currentDateTime.getTime());
                        userAliaspojo.setCreatedDate(currentDateTime.getTime());
                        session.saveOrUpdate(userAliaspojo);

                        for (ObjectGroups objectGroups : objectGroupsList) {
                            OgmuserAlias ogmuserAlias = new OgmuserAlias();
                            OgmuserAliasId id = new OgmuserAliasId();
                            id.setObjectGroupId(objectGroups.getObjectGroupID());
                            id.setUserAliasId(userAliaId);
                            ogmuserAlias.setId(id);
                            ogmuserAlias.setUpdatedDate(currentDateTime.getTime());
                            ogmuserAlias.setCreatedDate(currentDateTime.getTime());
                            session.saveOrUpdate(ogmuserAlias);
                        }

                        Device device = (Device) session.get(Device.class, deviceId);
                        ObjectUsageRecords objectUsageRecords = new ObjectUsageRecords();
                        objectUsageRecords.setUsageRecordId(UUID.randomUUID().toString());
                        objectUsageRecords.setUsageTypeId("71C030BD-7626-410A-9594-531F91E51CDA");
                        objectUsageRecords.setServiceId(TingcoConstants.getServiceID());
                        objectUsageRecords.setObjectId(deviceId);
                        objectUsageRecords.setObjectTypeId(device.getDeviceTypes().getDeviceTypeId());
                        objectUsageRecords.setObjectName(device.getDeviceTypes().getDeviceTypeName());
                        objectUsageRecords.setUsageDescription("Ticket Sales");
                        objectUsageRecords.setCreatedDate(currentDateTime.getTime());
                        objectUsageRecords.setUpdatedDate(currentDateTime.getTime());
                        objectUsageRecords.setUsageStartTime(currentDateTime.getTime());
                        currentDateTime.add(Calendar.MINUTE, 5);
                        objectUsageRecords.setUsageStopTime(currentDateTime.getTime());
                        objectUsageRecords.setDataItemName("Ticket Sales");
                        objectUsageRecords.setGroupId(device.getGroups().getGroupId());
                        objectUsageRecords.setAgreementId(device.getAgreements().getAgreementId());
                        objectUsageRecords.setUsageVolume("1");
                        objectUsageRecords.setUsageUnitId("862C0047-17DF-4C5A-9A05-9610C700C489");
                        objectUsageRecords.setUsageUnitName("st");
                        objectUsageRecords.setUsedByUserAlias(userAlias);
                        objectUsageRecords.setLastUpdatedByUserId(sessions2.getUserId());
                        ismOperationsession.saveOrUpdate(objectUsageRecords);

                        tx.commit();
                        ismoperationTx.commit();
                        String isoChar = getISOChar(cardPrefixes, userAlias, df.format(gc.getTime()));
                        return tingcoVirtualGateXML.builtcreateNewUserAlias(tingcoVirtualGate, userAliases.getFirstName(), userAliases.getLastName(), userAlias, isoChar, userAliases.getActiveFromDate(), userAliases.getActiveToDate());


                    } else {
                        tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
                        tingcoVirtualGate.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                        return tingcoVirtualGate;
                    }
                } else {
                    tingcoVirtualGate.getMsgStatus().setResponseCode(-10);
                    tingcoVirtualGate.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoVirtualGate;
                }
            } else {
                tingcoVirtualGate.getMsgStatus().setResponseCode(-3);
                tingcoVirtualGate.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoVirtualGate;
            }
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            if (ismoperationTx != null) {
                ismoperationTx.rollback();
            }
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
            tingcoVirtualGate.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            if (ismOperationsession != null) {
                ismOperationsession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoVirtualGate;
    }
//    private TingcoVirtualGate createNewUserAlias(String content, String deviceId) {
//        long requestedTime = System.currentTimeMillis();
//        Session session = null;
//        Session ismOperationsession = null;
//        Transaction tx = null;
//        Transaction ismoperationTx = null;
//        TingcoVirtualGate tingcoVirtualGate = null;
//        boolean hasPermission = false;
//
//        try {
//            tingcoVirtualGate = tingcoVirtualGateXML.buildTingcoVirtualGateTemplate();
//            if (request.getAttribute("USERSESSION") != null) {
//                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
//                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.ACCESS, TCMUtil.READ);
//                if (hasPermission) {
//                    session = HibernateUtil.getSessionFactory().openSession();
//                    ismOperationsession = HibernateUtil.getISMOperationsSessionFactory().openSession();
//
//                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
//                    if (userTimeZones2 != null) {
//                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
//                        TingcoVirtualGate tingcoVirtualGateJaxb = (TingcoVirtualGate) JAXBManager.getInstance().unMarshall(content, TingcoVirtualGate.class);
//                        UserAliases userAliases = tingcoVirtualGateJaxb.getVirtualGates().getVirtualGate().get(0).getUserAliases().get(0);
//                        List<ObjectGroups> objectGroupsList = tingcoVirtualGateJaxb.getVirtualGates().getVirtualGate().get(0).getObjectGroups();
//                        se.info24.pojo.UserAliasTypes userAliasTypes = (se.info24.pojo.UserAliasTypes) session.get(se.info24.pojo.UserAliasTypes.class, userAliases.getUserAliasTypeID());
//                        if (userAliasTypes == null || userAliasTypes.getCardPrefixId() == null) {
//                            tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
//                            tingcoVirtualGate.getMsgStatus().setResponseText("CardType is not valid");
//                            return tingcoVirtualGate;
//                        }
//                        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//                        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//                        GregorianCalendar gc = new GregorianCalendar();
//                        gc.setTime(df1.parse(userAliases.getActiveToDate()));
//
//                        CardPrefixes cardPrefixes = (CardPrefixes) session.get(CardPrefixes.class, userAliasTypes.getCardPrefixId());
//                        long highestAccountNumber = cardPrefixes.getHighestAccountNumber();
//                        String userAlias = null;
//                        ProductsDAO productsDAO = new ProductsDAO();
//                        while (true) {
//                            userAlias = generateUserAlia(session, cardPrefixes, highestAccountNumber);
//                            if (productsDAO.getUserAliasByUserAlias(session, userAlias) == null) {
//                                break;
//                            }
//                            highestAccountNumber = highestAccountNumber + 1;
//                        }
//                        cardPrefixes.setHighestAccountNumber(highestAccountNumber);
//
//
//                        UserAlias userAliaspojo = new UserAlias();
//                        String userAliaId = UUID.randomUUID().toString();
//                        userAliaspojo.setUserAliasId(userAliaId);
//                        userAliaspojo.setUserAlias(userAlias);
//                        userAliaspojo.setUserAliasTypes(userAliasTypes);
//                        userAliaspojo.setGroupId(userAliases.getGroupID());
//                        userAliaspojo.setFirstName(userAliases.getFirstName());
//                        userAliaspojo.setLastName(userAliases.getLastName());
//                        userAliaspojo.setIsEnabled(1);
//                        gc.setTime(df1.parse(userAliases.getActiveFromDate()));
//                        userAliaspojo.setActiveFromDate(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
//
//                        gc.setTime(df1.parse(userAliases.getActiveToDate()));
//                        userAliaspojo.setActiveToDate(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
//
//                        userAliaspojo.setFirstUseDate(currentDateTime.getTime());
//                        userAliaspojo.setCreditAmount(Double.parseDouble("0"));
//                        userAliaspojo.setCreditAmountCurrencyId(4);
//                        userAliaspojo.setCredits(1000);
//                        userAliaspojo.setLastUpdatedByUserId(sessions2.getUserId());
//                        userAliaspojo.setUpdatedDate(currentDateTime.getTime());
//                        userAliaspojo.setCreatedDate(currentDateTime.getTime());
//
//
//
//                        Device device = (Device) session.get(Device.class, deviceId);
//
//                        ObjectUsageRecords objectUsageRecords = new ObjectUsageRecords();
//                        objectUsageRecords.setUsageRecordId(UUID.randomUUID().toString());
//                        objectUsageRecords.setUsageTypeId("71C030BD-7626-410A-9594-531F91E51CDA");
//                        objectUsageRecords.setServiceId(TingcoConstants.getServiceID());
//                        objectUsageRecords.setObjectId(deviceId);
//                        objectUsageRecords.setObjectTypeId(device.getDeviceTypes().getDeviceTypeId());
//                        objectUsageRecords.setObjectName(device.getDeviceTypes().getDeviceTypeName());
//                        objectUsageRecords.setUsageDescription("Ticket Sales");
//                        objectUsageRecords.setCreatedDate(currentDateTime.getTime());
//                        objectUsageRecords.setUpdatedDate(currentDateTime.getTime());
//                        objectUsageRecords.setUsageStartTime(currentDateTime.getTime());
//                        currentDateTime.add(Calendar.MINUTE, 5);
//                        objectUsageRecords.setUsageStopTime(currentDateTime.getTime());
//                        objectUsageRecords.setDataItemName("Ticket Sales");
//                        objectUsageRecords.setGroupId(device.getGroups().getGroupId());
//                        objectUsageRecords.setAgreementId(device.getAgreements().getAgreementId());
//                        objectUsageRecords.setUsageVolume("1");
//                        objectUsageRecords.setUsageUnitId("862C0047-17DF-4C5A-9A05-9610C700C489");
//                        objectUsageRecords.setUsageUnitName("st");
//                        objectUsageRecords.setUsedByUserAlias(userAlias);
//                        objectUsageRecords.setLastUpdatedByUserId(sessions2.getUserId());
//
//
//
//
////                        ismoperationTx.commit();
//
//
//                        //******************************Automatic Check-In START*******************************************
//
//
////                        ismOperationsession = HibernateUtil.getISMOperationsSessionFactory().openSession();
////                        ismoperationTx = ismOperationsession.beginTransaction();
//
//                        DeviceDAO deviceDAO = new DeviceDAO();
//
//                        DeviceTypes deviceTypes = deviceDAO.getDeviceTypesById(device.getDeviceTypes().getDeviceTypeId(), session);
//                        DeviceStatus deviceStatus = deviceDAO.getDeviceStatusByDeviceId(deviceId, ismOperationsession);
//
//                        List<ContainerDevices> containerDevicess = deviceDAO.getContainerDevicesByDeviceId(deviceId, session);
//                        if (containerDevicess.isEmpty()) {
//                            tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
//                            tingcoVirtualGate.getMsgStatus().setResponseText("Device not found in ContainerDevices ");
//                            return tingcoVirtualGate;
//                        }
//                        ContainerDevices containerDevices = containerDevicess.get(0);
//
//                        TingcoVirtualGateDAO virtualGateDAO = new TingcoVirtualGateDAO();
//                        Object containerUserAliasObject = virtualGateDAO.getContainerUserAliasByIds(containerDevices.getContainers().getContainerId(), userAliaId, session);
//                        ContainerUserAlias containerUserAlias = null;
//                        if (containerUserAliasObject != null) {
//                            containerUserAlias = (ContainerUserAlias) containerUserAliasObject;
//                            containerUserAlias.setUserAlias(userAlias);
//                            containerUserAlias.setCheckInDate(currentDateTime.getTime());
//                            containerUserAlias.setCheckInDeviceId(deviceId);
//                            containerUserAlias.setCheckinDeviceName(device.getDeviceName());
//                            containerUserAlias.setCheckinDeviceGroupId(device.getGroups().getGroupId());
//                            containerUserAlias.setCheckinDeviceTypeId(device.getDeviceTypes().getDeviceTypeId());
//                            containerUserAlias.setCheckinDeviceAgreementId(device.getAgreements().getAgreementId());
//                            containerUserAlias.setCheckInServiceId(TingcoConstants.getServiceID());
//                            containerUserAlias.setLastUpdatedByUserId(sessions2.getUserId());
//                            containerUserAlias.setUpdatedDate(currentDateTime.getTime());
//                        } else {
//                            containerUserAlias = new ContainerUserAlias();
//                            containerUserAlias.setId(new ContainerUserAliasId(containerDevices.getContainers().getContainerId(), userAliaId));
//                            containerUserAlias.setUserAlias(userAlias);
//                            containerUserAlias.setCheckInDate(currentDateTime.getTime());
//                            containerUserAlias.setCheckInDeviceId(deviceId);
//                            containerUserAlias.setCheckinDeviceName(device.getDeviceName());
//                            containerUserAlias.setCheckinDeviceGroupId(device.getGroups().getGroupId());
//                            containerUserAlias.setCheckinDeviceTypeId(device.getDeviceTypes().getDeviceTypeId());
//                            containerUserAlias.setCheckinDeviceAgreementId(device.getAgreements().getAgreementId());
//                            containerUserAlias.setCheckInServiceId(TingcoConstants.getServiceID());
//                            containerUserAlias.setLastUpdatedByUserId(sessions2.getUserId());
//                            containerUserAlias.setCreatedDate(currentDateTime.getTime());
//                            containerUserAlias.setUpdatedDate(currentDateTime.getTime());
//                        }
//
//
//                        AccessLog accessLog = new AccessLog();
//                        accessLog.setAccessLogRowId(UUID.randomUUID().toString());
//                        accessLog.setAccessTime(currentDateTime.getTime());
//                        accessLog.setDeviceId(deviceId);
//                        accessLog.setDeviceGroupId(device.getGroups().getGroupId());
//                        accessLog.setDeviceName(device.getDeviceName());
//                        accessLog.setResource("Virtual Gate");
//                        accessLog.setUserAliasId(userAliaId);
//                        accessLog.setUserAlias(userAlias);
//                        accessLog.setFirstName(userAliases.getFirstName());
//                        accessLog.setLastName(userAliases.getLastName());
////                        accessLog.setUserId(userAlias.getUserId() != null ? userAlias.getUserId() : null);
//                        accessLog.setUserGroupId(userAliases.getGroupID());
//                        accessLog.setUserAliasTypeId(userAliasTypes.getUserAliasTypeId());
//                        accessLog.setUserAliasTypeName(userAliasTypes.getUserAliasTypeName());
//                        accessLog.setEventTypeId("B8D3E7F2-3770-4AC7-A313-9E8B052782FA");
//                        accessLog.setEventSubject("Check-in");
//                        if (deviceStatus != null) {
////                        DecimalFormat dec = new DecimalFormat("#.##");
//                            if (deviceStatus.getPosLatitude() != null) {
//                                accessLog.setPosLatitude(deviceStatus.getPosLatitude().replace(",", "."));
//                            }
//                            if (deviceStatus.getPosLongitude() != null) {
//                                accessLog.setPosLongitude(deviceStatus.getPosLongitude().replace(",", "."));
//                            }
//                            if (deviceStatus.getPosDepth() != null) {
//                                accessLog.setPosAltitude(deviceStatus.getPosDepth().replace(",", "."));
//                            }
//                            if (deviceStatus.getPosDirection() != null) {
//                                accessLog.setPosHeading(deviceStatus.getPosDirection());
//                            }
//                        }
//                        accessLog.setCoordinateSystemId("WGS84");
//                        accessLog.setLastUpdatedByUserId(sessions2.getUserId());
//                        accessLog.setCreatedDate(currentDateTime.getTime());
//                        accessLog.setUpdatedDate(currentDateTime.getTime());
//
//
//                        MeasurementData measurementData = new MeasurementData();
//                        measurementData.setId(new MeasurementDataId(UUID.randomUUID().toString(), "03e271cc-6c3b-484d-b38e-5dedee1ff6a0"));
//                        measurementData.setDataItemTime(currentDateTime.getTime());
//                        measurementData.setObjectId(deviceId);
//                        measurementData.setGroupId(device.getGroups().getGroupId());
//                        measurementData.setMeasurementValue(new BigDecimal(1));
//                        java.text.DateFormat yyyy = new SimpleDateFormat("yyyy");
//                        java.text.DateFormat mm = new SimpleDateFormat("MM");
//                        java.text.DateFormat dd = new SimpleDateFormat("dd");
//                        java.text.DateFormat hh = new SimpleDateFormat("HH");
//                        measurementData.setYear(Short.parseShort(yyyy.format(currentDateTime.getTime())));
//                        measurementData.setMonth(Short.parseShort(mm.format(currentDateTime.getTime())));
//                        measurementData.setDay(Short.parseShort(dd.format(currentDateTime.getTime())));
//                        measurementData.setHour(Short.parseShort(hh.format(currentDateTime.getTime())));
//                        measurementData.setCreatedDate(currentDateTime.getTime());
//                        measurementData.setUpdatedDate(currentDateTime.getTime());
//
//
//                        if (!virtualGateDAO.updatecontainers(session, containerDevices.getContainers().getContainerId())) {
//                            tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
//                            tingcoVirtualGate.getMsgStatus().setResponseText("Error occured");
//                            return tingcoVirtualGate;
//                        }
//                        session = HibernateUtil.getSessionFactory().openSession();
//                        tx = session.beginTransaction();
//                        ismoperationTx = ismOperationsession.beginTransaction();
//                        session.saveOrUpdate(cardPrefixes);
//                        session.saveOrUpdate(userAliaspojo);
//                        ismOperationsession.saveOrUpdate(objectUsageRecords);
//                        session.saveOrUpdate(containerUserAlias);
//                        for (ObjectGroups objectGroups : objectGroupsList) {
//                            OgmuserAlias ogmuserAlias = new OgmuserAlias();
//                            OgmuserAliasId id = new OgmuserAliasId();
//                            id.setObjectGroupId(objectGroups.getObjectGroupID());
//                            id.setUserAliasId(userAliaId);
//                            ogmuserAlias.setId(id);
//                            ogmuserAlias.setUpdatedDate(currentDateTime.getTime());
//                            ogmuserAlias.setCreatedDate(currentDateTime.getTime());
//                            session.saveOrUpdate(ogmuserAlias);
//                        }
//                        ismOperationsession.saveOrUpdate(accessLog);
//                        ismOperationsession.saveOrUpdate(measurementData);
//
//                        //******************************Automatic Check-In END*******************************************
//                        tx.commit();
//
//                        ismoperationTx.commit();
//
//                        String isoChar = getISOChar(cardPrefixes, userAlias, df.format(gc.getTime()));
//                        return tingcoVirtualGateXML.builtcreateNewUserAlias(tingcoVirtualGate, userAliases.getFirstName(), userAliases.getLastName(), userAlias, isoChar, userAliases.getActiveFromDate(), userAliases.getActiveToDate());
//
//
//                    } else {
//                        tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
//                        tingcoVirtualGate.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
//                        return tingcoVirtualGate;
//                    }
//                } else {
//                    tingcoVirtualGate.getMsgStatus().setResponseCode(-10);
//                    tingcoVirtualGate.getMsgStatus().setResponseText("User Permission is not given");
//                    return tingcoVirtualGate;
//                }
//            } else {
//                tingcoVirtualGate.getMsgStatus().setResponseCode(-3);
//                tingcoVirtualGate.getMsgStatus().setResponseText("User Session is Not Valid");
//                return tingcoVirtualGate;
//            }
//        } catch (Exception ex) {
////            if (tx != null) {
////                tx.rollback();
////            }
//            if (ismoperationTx != null) {
//                ismoperationTx.rollback();
//            }
//            TCMUtil.exceptionLog(getClass().getName(), ex);
//            tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
//            tingcoVirtualGate.getMsgStatus().setResponseText("Error occured");
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//            if (ismOperationsession != null) {
//                ismOperationsession.close();
//            }
//            delayLog(requestedTime);
//        }
//        return tingcoVirtualGate;
//    }

    public String generateUserAlia(Session session, CardPrefixes cardPrefixes, long highestAccountNumber) {
        StringBuffer sb = new StringBuffer();
        sb.append(cardPrefixes.getStartPrefixBin());
        int numberofZero = cardPrefixes.getAccountNumberLength() - (getNumberCount(highestAccountNumber));
        for (int i = 0; i < numberofZero; i++) {
            sb.append("0");
        }
        sb.append(highestAccountNumber + 1);
        return sb.toString();
    }

    public int getNumberCount(long number) {
        String value = number + "";
        return value.length();
    }

    public String getISOChar(CardPrefixes cardPrefixes, String userAlias, String ActiveFromDate) {
        StringBuffer isoChar = new StringBuffer(cardPrefixes.getStartChar());
        isoChar.append(userAlias);
        isoChar.append(cardPrefixes.getFieldSeparatorChar());
        isoChar.append(ActiveFromDate);
        isoChar.append("00000000");
        isoChar.append(cardPrefixes.getEndChar());
        return isoChar.toString();
    }

    private TingcoVirtualGate getAllVenues(String deviceId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoVirtualGate tingcoVirtualGate = null;
        boolean hasPermission = false;
        GroupDAO groupDAO = new GroupDAO();
        UserDAO userDAO = new UserDAO();
        try {
            tingcoVirtualGate = tingcoVirtualGateXML.buildTingcoVirtualGateTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.ACCESS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<String> objectGroupIdList = groupDAO.getOGMDeviceObjectGroupId(deviceId, session);
                    if (!objectGroupIdList.isEmpty()) {
                        List<ObjectGroupTranslations> ogtransList = userDAO.getObjectGroupTranslationsById(session, new HashSet<String>(objectGroupIdList), countryId);
                        tingcoVirtualGate = tingcoVirtualGateXML.buildAllVenues(tingcoVirtualGate, ogtransList);
                    } else {
                        tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
                        tingcoVirtualGate.getMsgStatus().setResponseText("No Venues found");
                        return tingcoVirtualGate;
                    }
                } else {
                    tingcoVirtualGate.getMsgStatus().setResponseCode(-10);
                    tingcoVirtualGate.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoVirtualGate;
                }
            } else {
                tingcoVirtualGate.getMsgStatus().setResponseCode(-3);
                tingcoVirtualGate.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoVirtualGate;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
            tingcoVirtualGate.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoVirtualGate;
    }

    private TingcoVirtualGate getCardTypes(String groupId) throws DatatypeConfigurationException {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoVirtualGate tingcoVirtualGate = null;
        boolean hasPermission = false;
        TingcoVirtualGateDAO virtualGateDAO = new TingcoVirtualGateDAO();
        TingcoVirtualGateXML tingcoVirtualGateXML = new TingcoVirtualGateXML();
        tingcoVirtualGate = tingcoVirtualGateXML.buildTingcoVirtualGateTemplate();
        try {
            if (groupId.equals("")) {
                tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
                tingcoVirtualGate.getMsgStatus().setResponseText("GroupId should not empty");
                return tingcoVirtualGate;
            }
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.ACCESS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    PricePlanDAO pricePlanDAO = new PricePlanDAO();
                    List<UserAliasTypesInGroups> aliasTypesInGroupses = pricePlanDAO.getUserAliasTypesInGroups(session, groupId);
                    if (aliasTypesInGroupses.isEmpty()) {
                        tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
                        tingcoVirtualGate.getMsgStatus().setResponseText("No Data Found");
                        return tingcoVirtualGate;
                    } else {
                        Set<String> userAliasTypeIDs = new HashSet<String>();
                        for (UserAliasTypesInGroups uatig : aliasTypesInGroupses) {
                            userAliasTypeIDs.add(uatig.getId().getUserAliasTypeId());
                        }
                        List<se.info24.pojo.UserAliasTypes> userAliasTypeses = virtualGateDAO.getUserAliasTypesByIDList(session, userAliasTypeIDs);
                        tingcoVirtualGate = tingcoVirtualGateXML.buildGetCardTypes(tingcoVirtualGate, userAliasTypeses);
                    }
                } else {
                    tingcoVirtualGate.getMsgStatus().setResponseCode(-10);
                    tingcoVirtualGate.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoVirtualGate;
                }
            } else {
                tingcoVirtualGate.getMsgStatus().setResponseCode(-3);
                tingcoVirtualGate.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoVirtualGate;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
            tingcoVirtualGate.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoVirtualGate;
    }

    private TingcoVirtualGate getValidTicketsCount(String deviceId) throws DatatypeConfigurationException {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoVirtualGate tingcoVirtualGate = null;
        boolean hasPermission = false;
        TingcoVirtualGateDAO virtualGateDAO = new TingcoVirtualGateDAO();
        tingcoVirtualGate = tingcoVirtualGateXML.buildTingcoVirtualGateTemplate();
        DeviceDAO deviceDAO = new DeviceDAO();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (deviceId.equals("")) {
                tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
                tingcoVirtualGate.getMsgStatus().setResponseText("DeviceId should not empty");
                return tingcoVirtualGate;
            }
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.ACCESS, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<Ogmdevice> ogmdevices = virtualGateDAO.getOgmdeviceByDeviceId(session, deviceId);
                    List<UserAlias> userAliases = new ArrayList<UserAlias>();
                    if (ogmdevices.isEmpty()) {
                        tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
                        tingcoVirtualGate.getMsgStatus().setResponseText("No Data Found With Given Device");
                        return tingcoVirtualGate;
                    } else {
                        Set<String> objectGroupIDs = new HashSet<String>();
                        for (Ogmdevice ogm : ogmdevices) {
                            objectGroupIDs.add(ogm.getObjectGroupId());
                        }
                        List<OgmuserAlias> ogmuserAliases = virtualGateDAO.getOgmuserAliasByObjectGroupIdList(session, objectGroupIDs);
                        if (ogmuserAliases.isEmpty()) {
                            tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
                            tingcoVirtualGate.getMsgStatus().setResponseText("No UserAlias Found");
                            return tingcoVirtualGate;
                        } else {
                            List<String> userAliasIDs = new ArrayList<String>();
                            for (OgmuserAlias ogm : ogmuserAliases) {
                                userAliasIDs.add(ogm.getUserAlias().getUserAliasId());
                            }
                            List<List<String>> listsplit = TCMUtil.split(userAliasIDs, 2000);
                            for (List<String> list : listsplit) {
                                userAliases.addAll(virtualGateDAO.getUserAliasDetailsByUserAliasIDsAndValidDate(session, list, df.format(currentDateTime.getTime())));
                            }
                            tingcoVirtualGate = tingcoVirtualGateXML.buildgetValidTicketsCount(tingcoVirtualGate, userAliases.size());
                        }
                    }
                } else {
                    tingcoVirtualGate.getMsgStatus().setResponseCode(-10);
                    tingcoVirtualGate.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoVirtualGate;
                }
            } else {
                tingcoVirtualGate.getMsgStatus().setResponseCode(-3);
                tingcoVirtualGate.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoVirtualGate;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
            tingcoVirtualGate.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoVirtualGate;
    }

    private TingcoVirtualGate getValidTicketsList(String deviceId, String maxResult) throws DatatypeConfigurationException {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoVirtualGate tingcoVirtualGate = null;
        boolean hasPermission = false;
        TingcoVirtualGateDAO virtualGateDAO = new TingcoVirtualGateDAO();
        tingcoVirtualGate = tingcoVirtualGateXML.buildTingcoVirtualGateTemplate();
        DeviceDAO deviceDAO = new DeviceDAO();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (deviceId.equals("")) {
                tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
                tingcoVirtualGate.getMsgStatus().setResponseText("DeviceId should not empty");
                return tingcoVirtualGate;
            }
            if (!maxResult.equals("")) {
                maxResult = maxResult.split("/")[2];
            } else {
                maxResult = null;
            }
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.ACCESS, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<Ogmdevice> ogmdevices = virtualGateDAO.getOgmdeviceByDeviceId(session, deviceId);
                    List<UserAlias> userAliases = new ArrayList<UserAlias>();
                    if (ogmdevices.isEmpty()) {
                        tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
                        tingcoVirtualGate.getMsgStatus().setResponseText("No Data Found With Given Device");
                        return tingcoVirtualGate;
                    } else {
                        Set<String> objectGroupIDs = new HashSet<String>();
                        for (Ogmdevice ogm : ogmdevices) {
                            objectGroupIDs.add(ogm.getObjectGroupId());
                        }
                        List<OgmuserAlias> ogmuserAliases = virtualGateDAO.getOgmuserAliasByObjectGroupIdList(session, objectGroupIDs);
                        if (ogmuserAliases.isEmpty()) {
                            tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
                            tingcoVirtualGate.getMsgStatus().setResponseText("No UserAlias Found");
                            return tingcoVirtualGate;
                        } else {
                            List<String> userAliasIDs = new ArrayList<String>();
                            for (OgmuserAlias ogm : ogmuserAliases) {
                                userAliasIDs.add(ogm.getUserAlias().getUserAliasId());
                            }
                            List<List<String>> listsplit = TCMUtil.split(userAliasIDs, 2000);
                            for (List<String> list : listsplit) {
                                userAliases.addAll(virtualGateDAO.getUserAliasDetailsByUserAliasIDsAndValidDate(session, list, df.format(currentDateTime.getTime())));
                            }

                            Collections.sort(userAliases, new Comparator<UserAlias>() {

                                public int compare(UserAlias o1, UserAlias o2) {
                                    return o2.getCreatedDate().compareTo(o1.getCreatedDate());
                                }
                            });

                            TCMUtil.loger(getClass().getName(), userAliases.get(0).getUserAlias(), "Info");
                            if (maxResult == null) {
                                tingcoVirtualGate = tingcoVirtualGateXML.buildgetValidTicketsList(tingcoVirtualGate, userAliases);
                            } else {
                                tingcoVirtualGate = tingcoVirtualGateXML.buildgetValidTicketsList(tingcoVirtualGate, userAliases.subList(0, Integer.parseInt(maxResult)));
                            }
                        }
                    }
                } else {
                    tingcoVirtualGate.getMsgStatus().setResponseCode(-10);
                    tingcoVirtualGate.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoVirtualGate;
                }
            } else {
                tingcoVirtualGate.getMsgStatus().setResponseCode(-3);
                tingcoVirtualGate.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoVirtualGate;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
            tingcoVirtualGate.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoVirtualGate;
    }

    private TingcoVirtualGate insertCheckOutDetails(String deviceId, String userAliasID, String istcm) {
        long requestedTime = System.currentTimeMillis();
        Session ismOperationsession = null;
        Session session = null;
        TingcoVirtualGate tingcoVirtualGate = null;
        boolean hasPermission = false;
        TingcoVirtualGateDAO virtualGateDAO = new TingcoVirtualGateDAO();
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        Transaction tx = null;
        Transaction ismTx = null;
        try {
            tingcoVirtualGate = tingcoVirtualGateXML.buildTingcoVirtualGateTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                //Added due to TCM V3 support. As per .Net team requirement data is inserting into UserLog Table.
                if (!istcm.equals("")) {
                    istcm = istcm.split("/")[2];
                } else {
                    istcm = null;
                }


                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.ACCESS, TCMUtil.ADD);
                if (hasPermission) {
                    ismOperationsession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    session = HibernateUtil.getSessionFactory().openSession();
                    AccessLog accessLog = new AccessLog();
                    accessLog.setAccessLogRowId(UUID.randomUUID().toString());
                    accessLog.setAccessTime(currentDateTime.getTime());
                    Device device = (Device) session.get(Device.class, deviceId);
                    if (device == null) {
                        tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
                        tingcoVirtualGate.getMsgStatus().setResponseText("Device is not valid");
                        return tingcoVirtualGate;
                    }
                    accessLog.setDeviceId(deviceId);

                    accessLog.setDeviceGroupId(device.getGroups().getGroupId());

                    accessLog.setDeviceName(device.getDeviceName());
                    accessLog.setResource("Virtual Gate");
                    UserAlias userAlias = (UserAlias) session.get(UserAlias.class, userAliasID);
                    if (userAlias == null) {
                        tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
                        tingcoVirtualGate.getMsgStatus().setResponseText("userAlias is not valid");
                        return tingcoVirtualGate;
                    }
                    accessLog.setUserAlias(userAlias.getUserAlias());
                    accessLog.setUserAliasId(userAlias.getUserAliasId());
                    accessLog.setFirstName(userAlias.getFirstName());
                    accessLog.setLastName(userAlias.getLastName());
                    if (userAlias.getUserId() != null) {
                        accessLog.setUserId(userAlias.getUserId());
                    }
                    accessLog.setUserGroupId(userAlias.getGroupId());
                    accessLog.setUserAliasTypeId(userAlias.getUserAliasTypes().getUserAliasTypeId());
                    accessLog.setUserAliasTypeName(userAlias.getUserAliasTypes().getUserAliasTypeName());
                    accessLog.setEventTypeId("2C7E511E-F189-4CD0-9B6A-9E7CCD8D0A3D");
                    accessLog.setEventSubject("Check-out");



                    DeviceStatus deviceStatus = (DeviceStatus) ismOperationsession.get(DeviceStatus.class, deviceId);
                    if (deviceStatus != null) {
//                        DecimalFormat dec = new DecimalFormat("#.##");
                        if (deviceStatus.getPosLatitude() != null) {
                            accessLog.setPosLatitude(deviceStatus.getPosLatitude().replace(",", "."));
                        }
                        if (deviceStatus.getPosLongitude() != null) {
                            accessLog.setPosLongitude(deviceStatus.getPosLongitude().replace(",", "."));
                        }
                        if (deviceStatus.getPosDepth() != null) {
                            accessLog.setPosAltitude(deviceStatus.getPosDepth().replace(",", "."));
                        }
                        if (deviceStatus.getPosDirection() != null) {
                            accessLog.setPosHeading(deviceStatus.getPosDirection());
                        }
                    }
                    accessLog.setCoordinateSystemId("WGS84");
                    accessLog.setLastUpdatedByUserId(sessions2.getUserId());
                    accessLog.setUpdatedDate(gc.getTime());
                    accessLog.setCreatedDate(gc.getTime());

                    MeasurementData measurementData = new MeasurementData();
                    measurementData.setId(new MeasurementDataId(UUID.randomUUID().toString(), "4e37dd0e-295c-40fd-986f-b252edd1f828"));
                    measurementData.setDataItemTime(gc.getTime());
                    measurementData.setObjectId(deviceId);
                    measurementData.setGroupId(device.getGroups().getGroupId());
                    measurementData.setMeasurementValue(new BigDecimal(1));
                    java.text.DateFormat yyyy = new SimpleDateFormat("yyyy");
                    java.text.DateFormat mm = new SimpleDateFormat("MM");
                    java.text.DateFormat dd = new SimpleDateFormat("dd");
                    java.text.DateFormat hh = new SimpleDateFormat("HH");
                    measurementData.setYear(Short.parseShort(yyyy.format(gc.getTime())));
                    measurementData.setDay(Short.parseShort(dd.format(gc.getTime())));
                    measurementData.setMonth(Short.parseShort(mm.format(gc.getTime())));
                    measurementData.setHour(Short.parseShort(hh.format(gc.getTime())));
                    measurementData.setCreatedDate(gc.getTime());
                    measurementData.setUpdatedDate(gc.getTime());
                    DeviceDAO deviceDAO = new DeviceDAO();
                    List<ContainerDevices> containerDevicess = deviceDAO.getContainerDevicesByDeviceId(deviceId, session);
                    if (containerDevicess.isEmpty()) {
                        tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
                        tingcoVirtualGate.getMsgStatus().setResponseText("Device not found in ContainerDevices ");
                        return tingcoVirtualGate;
                    }
                    ContainerDevices containerDevices = containerDevicess.get(0);

                    if (userAlias == null) {
                        tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
                        tingcoVirtualGate.getMsgStatus().setResponseText("UserAlis is not Valid");
                        return tingcoVirtualGate;
                    }
                    UserLog userLog = null;
                    if (istcm != null) {
                        userLog = new UserLog();
                        userLog.setAction("Manual check-out");
                        userLog.setTableName("UserAlias");
                        userLog.setResults("Success");
                        userLog.setUserId(sessions2.getUserId());
                        userLog.setRequestIp(request.getRemoteAddr());
                        userLog.setCreatedDate(currentDateTime.getTime());
                        userLog.setUpdatedDate(currentDateTime.getTime());

                    }
                    Object containerUserAliasObject = virtualGateDAO.getContainerUserAliasByIds(containerDevices.getContainers().getContainerId(), userAliasID, session);
                    ismTx = session.beginTransaction();
                    if (containerUserAliasObject != null) {
                        ContainerUserAlias containerUserAlias = (ContainerUserAlias) containerUserAliasObject;
                        session.delete(containerUserAlias);
                    }
                    if (userLog != null) {
                        session.save(userLog);
                    }
                    ismTx.commit();

                    tx = ismOperationsession.beginTransaction();
                    ismOperationsession.saveOrUpdate(accessLog);
                    ismOperationsession.saveOrUpdate(measurementData);

                    tx.commit();

                    if (!virtualGateDAO.updatecontainers(session, containerDevices.getContainers().getContainerId())) {
                        tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
                        tingcoVirtualGate.getMsgStatus().setResponseText("Error occured");
                        return tingcoVirtualGate;
                    }
                } else {
                    tingcoVirtualGate.getMsgStatus().setResponseCode(-10);
                    tingcoVirtualGate.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoVirtualGate;
                }
            } else {
                tingcoVirtualGate.getMsgStatus().setResponseCode(-3);
                tingcoVirtualGate.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoVirtualGate;
            }
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            if (ismTx != null) {
                ismTx.rollback();
            }
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
            tingcoVirtualGate.getMsgStatus().setResponseText("Error occured");
            return tingcoVirtualGate;
        } finally {
            if (ismOperationsession != null) {
                ismOperationsession.close();
            }
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoVirtualGate;
    }

    private TingcoVirtualGate checkTingcoAPIConnected(String deviceId) {
        long requestedTime = System.currentTimeMillis();
        Session ismOperationsession = null;
        Transaction tx = null;
        TingcoVirtualGate tingcoVirtualGate = null;
        boolean hasPermission = false;
        TingcoVirtualGateDAO virtualGateDAO = new TingcoVirtualGateDAO();
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        try {
            tingcoVirtualGate = tingcoVirtualGateXML.buildTingcoVirtualGateTemplate();
            if (deviceId.equals("")) {
                tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
                tingcoVirtualGate.getMsgStatus().setResponseText("DeviceId should not empty");
                return tingcoVirtualGate;
            }
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.ACCESS, TCMUtil.ADD);
                if (hasPermission) {
                    ismOperationsession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    tx = ismOperationsession.beginTransaction();
                    ObjectConnectedState objectConnectedState = null;
                    Object objectConnectedStateObject = virtualGateDAO.getObjectConnectedStateByObjectIdAndServiceId(deviceId, TingcoConstants.getServiceID(), ismOperationsession);
                    if (objectConnectedStateObject != null) {
                        objectConnectedState = (ObjectConnectedState) objectConnectedStateObject;
                        objectConnectedState.setLastContactTime(currentDateTime.getTime());
                        objectConnectedState.setLastContactMessage("PING");
                        gc.add(Calendar.SECOND, 130);
                        objectConnectedState.setConnectedExpiryTime(gc.getTime());
                        objectConnectedState.setIsConnected(1);
                        objectConnectedState.setLastUpdatedByUserId(sessions2.getUserId());
                        objectConnectedState.setUpdatedDate(currentDateTime.getTime());
                    } else {
                        objectConnectedState = new ObjectConnectedState();
                        objectConnectedState.setObjectId(deviceId);
                        objectConnectedState.setLastContactServiceId(TingcoConstants.getServiceID());
                        objectConnectedState.setLastContactTime(currentDateTime.getTime());
                        objectConnectedState.setLastContactMessage("PING");
                        gc.add(Calendar.SECOND, 130);

                        objectConnectedState.setEventConnectedIsSent(0);
                        objectConnectedState.setEventDisconnectedIsSent(1);
                        objectConnectedState.setConnectedExpiryTime(gc.getTime());
                        objectConnectedState.setIsConnected(1);
                        objectConnectedState.setLastUpdatedByUserId(sessions2.getUserId());
                        objectConnectedState.setCreatedDate(currentDateTime.getTime());
                        objectConnectedState.setUpdatedDate(currentDateTime.getTime());
                    }
                    ismOperationsession.saveOrUpdate(objectConnectedState);
                    tx.commit();
                } else {
                    tingcoVirtualGate.getMsgStatus().setResponseCode(-10);
                    tingcoVirtualGate.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoVirtualGate;
                }
            } else {
                tingcoVirtualGate.getMsgStatus().setResponseCode(-3);
                tingcoVirtualGate.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoVirtualGate;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
            tingcoVirtualGate.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (ismOperationsession != null) {
                ismOperationsession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoVirtualGate;
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

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }

    private TingcoVirtualGate insertCheckInDetails(String deviceId, String userAliasId) {
        long requestedTime = System.currentTimeMillis();
        TingcoVirtualGateDAO virtualGateDAO = new TingcoVirtualGateDAO();
        DeviceDAO deviceDAO = new DeviceDAO();
        UserDAO userDAO = new UserDAO();
        Session session = null;
        Session ismOperationsession = null;
        Transaction tx = null;
        Transaction ismtx = null;
        TingcoVirtualGate tingcoVirtualGate = null;
        boolean hasPermission = false;
        try {
            tingcoVirtualGate = tingcoVirtualGateXML.buildTingcoVirtualGateTemplate();
            if (deviceId.equals("")) {
                tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
                tingcoVirtualGate.getMsgStatus().setResponseText("DeviceId should not empty");
                return tingcoVirtualGate;
            }
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.ACCESS, TCMUtil.ADD);
                if (hasPermission) {
                    ismOperationsession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = ismOperationsession.beginTransaction();
                    ismtx = session.beginTransaction();
                    Device device = deviceDAO.getDeviceById(deviceId, session);
                    if (device == null) {
                        tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
                        tingcoVirtualGate.getMsgStatus().setResponseText("DeviceID is not Valid");
                        return tingcoVirtualGate;
                    }
                    Object deviceTypes = deviceDAO.getDeviceTypesById(device.getDeviceTypes().getDeviceTypeId(), session);
                    DeviceStatus deviceStatus = deviceDAO.getDeviceStatusByDeviceId(deviceId, ismOperationsession);

                    List<ContainerDevices> containerDevicess = deviceDAO.getContainerDevicesByDeviceId(deviceId, session);
                    if (containerDevicess.isEmpty()) {
                        tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
                        tingcoVirtualGate.getMsgStatus().setResponseText("Device not found in ContainerDevices ");
                        return tingcoVirtualGate;
                    }
                    ContainerDevices containerDevices = containerDevicess.get(0);
                    Object object = userDAO.getUserAliasDetailsByUserAliasID(session, userAliasId);
                    if (object == null) {
                        tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
                        tingcoVirtualGate.getMsgStatus().setResponseText("UserAlis is not Valid");
                        return tingcoVirtualGate;
                    }
                    UserAlias userAlias = (UserAlias) object;
                    Object containerUserAliasObject = virtualGateDAO.getContainerUserAliasByIds(containerDevices.getContainers().getContainerId(), userAliasId, session);
                    ContainerUserAlias containerUserAlias = null;
                    if (containerUserAliasObject != null) {
                        containerUserAlias = (ContainerUserAlias) containerUserAliasObject;
                        containerUserAlias.setUserAlias(userAlias.getUserAlias());
                        containerUserAlias.setCheckInDate(currentDateTime.getTime());
                        containerUserAlias.setCheckInDeviceId(deviceId);
                        containerUserAlias.setCheckinDeviceName(device.getDeviceName());
                        containerUserAlias.setCheckinDeviceGroupId(device.getGroups().getGroupId());
                        containerUserAlias.setCheckinDeviceTypeId(device.getDeviceTypes().getDeviceTypeId());
                        containerUserAlias.setCheckinDeviceAgreementId(device.getAgreements().getAgreementId());
                        containerUserAlias.setCheckInServiceId(TingcoConstants.getServiceID());
                        containerUserAlias.setLastUpdatedByUserId(sessions2.getUserId());
                        containerUserAlias.setUpdatedDate(currentDateTime.getTime());
                    } else {
                        containerUserAlias = new ContainerUserAlias();
                        containerUserAlias.setId(new ContainerUserAliasId(containerDevices.getContainers().getContainerId(), userAliasId));
                        containerUserAlias.setUserAlias(userAlias.getUserAlias());
                        containerUserAlias.setCheckInDate(currentDateTime.getTime());
                        containerUserAlias.setCheckInDeviceId(deviceId);
                        containerUserAlias.setCheckinDeviceName(device.getDeviceName());
                        containerUserAlias.setCheckinDeviceGroupId(device.getGroups().getGroupId());
                        containerUserAlias.setCheckinDeviceTypeId(device.getDeviceTypes().getDeviceTypeId());
                        containerUserAlias.setCheckinDeviceAgreementId(device.getAgreements().getAgreementId());
                        containerUserAlias.setCheckInServiceId(TingcoConstants.getServiceID());
                        containerUserAlias.setLastUpdatedByUserId(sessions2.getUserId());
                        containerUserAlias.setCreatedDate(currentDateTime.getTime());
                        containerUserAlias.setUpdatedDate(currentDateTime.getTime());
                    }
                    session.saveOrUpdate(containerUserAlias);

                    AccessLog accessLog = new AccessLog();
                    accessLog.setAccessLogRowId(UUID.randomUUID().toString());
                    accessLog.setAccessTime(currentDateTime.getTime());
                    accessLog.setDeviceId(deviceId);
                    accessLog.setDeviceGroupId(device.getGroups().getGroupId());
                    accessLog.setDeviceName(device.getDeviceName());
                    accessLog.setResource("Virtual Gate");
                    accessLog.setUserAliasId(userAliasId);
                    accessLog.setUserAlias(userAlias.getUserAlias());
                    accessLog.setFirstName(userAlias.getFirstName());
                    accessLog.setLastName(userAlias.getLastName());
                    accessLog.setUserId(userAlias.getUserId() != null ? userAlias.getUserId() : null);
                    accessLog.setUserGroupId(userAlias.getGroupId());
                    accessLog.setUserAliasTypeId(userAlias.getUserAliasTypes().getUserAliasTypeId());
                    accessLog.setUserAliasTypeName(userAlias.getUserAliasTypes().getUserAliasTypeName());
                    accessLog.setEventTypeId("B8D3E7F2-3770-4AC7-A313-9E8B052782FA");
                    accessLog.setEventSubject("Check-in");
                    if (deviceStatus != null) {
//                        DecimalFormat dec = new DecimalFormat("#.##");
                        if (deviceStatus.getPosLatitude() != null) {
                            accessLog.setPosLatitude(deviceStatus.getPosLatitude().replace(",", "."));
                        }
                        if (deviceStatus.getPosLongitude() != null) {
                            accessLog.setPosLongitude(deviceStatus.getPosLongitude().replace(",", "."));
                        }
                        if (deviceStatus.getPosDepth() != null) {
                            accessLog.setPosAltitude(deviceStatus.getPosDepth().replace(",", "."));
                        }
                        if (deviceStatus.getPosDirection() != null) {
                            accessLog.setPosHeading(deviceStatus.getPosDirection());
                        }
                    }
                    accessLog.setCoordinateSystemId("WGS84");
                    accessLog.setLastUpdatedByUserId(sessions2.getUserId());
                    accessLog.setCreatedDate(currentDateTime.getTime());
                    accessLog.setUpdatedDate(currentDateTime.getTime());
                    ismOperationsession.saveOrUpdate(accessLog);

                    MeasurementData measurementData = new MeasurementData();
                    measurementData.setId(new MeasurementDataId(UUID.randomUUID().toString(), "03e271cc-6c3b-484d-b38e-5dedee1ff6a0"));
                    measurementData.setDataItemTime(currentDateTime.getTime());
                    measurementData.setObjectId(deviceId);
                    measurementData.setGroupId(device.getGroups().getGroupId());
                    measurementData.setMeasurementValue(new BigDecimal(1));
                    java.text.DateFormat yyyy = new SimpleDateFormat("yyyy");
                    java.text.DateFormat mm = new SimpleDateFormat("MM");
                    java.text.DateFormat dd = new SimpleDateFormat("dd");
                    java.text.DateFormat hh = new SimpleDateFormat("HH");
                    measurementData.setYear(Short.parseShort(yyyy.format(currentDateTime.getTime())));
                    measurementData.setMonth(Short.parseShort(mm.format(currentDateTime.getTime())));
                    measurementData.setDay(Short.parseShort(dd.format(currentDateTime.getTime())));
                    measurementData.setHour(Short.parseShort(hh.format(currentDateTime.getTime())));
                    measurementData.setCreatedDate(currentDateTime.getTime());
                    measurementData.setUpdatedDate(currentDateTime.getTime());
                    ismOperationsession.saveOrUpdate(measurementData);
                    ismtx.commit();
                    tx.commit();
                    if (!virtualGateDAO.updatecontainers(session, containerDevices.getContainers().getContainerId())) {
                        tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
                        tingcoVirtualGate.getMsgStatus().setResponseText("Error occured");
                        return tingcoVirtualGate;
                    }
                } else {
                    tingcoVirtualGate.getMsgStatus().setResponseCode(-10);
                    tingcoVirtualGate.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoVirtualGate;
                }
            } else {
                tingcoVirtualGate.getMsgStatus().setResponseCode(-3);
                tingcoVirtualGate.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoVirtualGate;
            }
        } catch (Exception ex) {
            if (ismtx != null) {
                ismtx.rollback();
            }
            if (tx != null) {
                tx.rollback();
            }
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoVirtualGate.getMsgStatus().setResponseCode(-1);
            tingcoVirtualGate.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (ismOperationsession != null) {
                ismOperationsession.close();
            }
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoVirtualGate;
    }
}

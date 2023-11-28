/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.reports;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.xml.datatype.DatatypeConfigurationException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import se.info24.devicejaxb.TingcoDevice;
import se.info24.group.GroupDAO;
import se.info24.ismOperationsPojo.ObjectUsageRecords;
import se.info24.ismOperationsPojo.TransactionResult;
import se.info24.jaxbUtil.TingcoDeviceXML;
import se.info24.jaxbUtil.TingcoReportsXML;
import se.info24.pojo.GroupWeekdays;
import se.info24.pojo.MeasurementTypes;
import se.info24.pojo.ObjectAddresses;
import se.info24.pojo.ObjectProductVariantCounters;
import se.info24.objectpojo.ObjectUsageSummaryReport;
import se.info24.pojo.ObjectUsageUnits;
import se.info24.pojo.ProductVariantTranslations;
import se.info24.pojo.ReportSendScheduleSettings;
import se.info24.pojo.ReportSendSchedules;
import se.info24.pojo.Reports;
import se.info24.pojo.UserLog;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.UserTimeZones2;
import se.info24.pojo.Users2;
import se.info24.products.ProductsDAO;
import se.info24.reportsjaxb.InstantMessageLog;
import se.info24.reportsjaxb.Report;
import se.info24.reportsjaxb.ReportSendSchedule;
import se.info24.reportsjaxb.ReportSendScheduleSetting;
import se.info24.reportsjaxb.TingcoReports;
import se.info24.restUtil.RestUtilDAO;
import se.info24.user.UserDAO;
import se.info24.user.User_LoginsResource;
import se.info24.util.HibernateUtil;
import se.info24.util.JAXBManager;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Vijayakumar
 */
@Path("/reports")
public class ReportssResource {

    @Context
    private UriInfo context;
    @Context
    HttpServletRequest request;
    ReportDAO dao = new ReportDAO();
    GroupDAO groupDAO = new GroupDAO();
    UserDAO userDAO = new UserDAO();
    ProductsDAO productsDAO = new ProductsDAO();
    TingcoReports tingcoReports = new TingcoReports();
    TingcoReportsXML tingcoReportsXml = new TingcoReportsXML();
    SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Create/getsmslogdetails/json/s a new instance of ReportssResource */
    public ReportssResource() {
    }

    /**
     * GetScheduledReports
     * @param reportCode
     * @param groupId
     * @param measurementTypeId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/get/rest/{rest}/reportcode/{reportcode}/groupid/{groupid}/measurementtypeid/{measurementtypeid}")
    @RESTDoc(methodName = "GetScheduledReports", desc = "Used to get the ReportSendSchedules", req_Params = {"ReportCode;string", "GroupId;UUID", "MeasurementTypeId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoReports getXml(@PathParam("reportcode") String reportCode, @PathParam("groupid") String groupId, @PathParam("measurementtypeid") String measurementTypeId) {
        return getReportSendSchedules(reportCode, groupId, measurementTypeId);
    }

    /**
     * GetScheduledReports
     * @param reportCode
     * @param groupId
     * @param measurementTypeId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/get/json/{json}/reportcode/{reportcode}/groupid/{groupid}/measurementtypeid/{measurementtypeid}")
    public TingcoReports getJson(@PathParam("reportcode") String reportCode, @PathParam("groupid") String groupId, @PathParam("measurementtypeid") String measurementTypeId) {
        return getReportSendSchedules(reportCode, groupId, measurementTypeId);
    }

    /**
     * GetAllReports
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/get/rest/{rest}")
    @RESTDoc(methodName = "GetAllReports", desc = "Used to get all the Report Details", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoReports getXml_All() {
        return getReportSendSchedules();
    }

    /**
     * GetAllReports
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/get/json/{json}")
    public TingcoReports getJson_All() {
        return getReportSendSchedules();
    }

    /**
     * getallusageunits
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getallobjectusageunits/rest/{rest}")
    @RESTDoc(methodName = "GetAllUsageUnits", desc = "Used to Get All ObjectUsingUnits", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoReports getXml_getAllObjectUsageUnits() throws DatatypeConfigurationException {
        return getAllObjectUsageUnits();
    }

    /**
     * getallusageunits
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getallusageunits/json/{json}")
    public TingcoReports getJson_getAllObjectUsageUnits() throws DatatypeConfigurationException {
        return getAllObjectUsageUnits();
    }

    /**
     * GetReportsandReportTranslations
     * @param groupId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getreports/rest/{rest}/groupid/{groupid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetReportsandReportTranslations", desc = "Used to get Reports and ReportTranslations", req_Params = {"GroupId;string", "countryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoReports getXml_Reports(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getReports(groupId, countryId);
    }

    /**
     * GetReportsandReportTranslations
     * @param groupId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getreports/json/{json}/groupid/{groupid}/countryid/{countryid}")
    public TingcoReports getJson_Reports(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getReports(groupId, countryId);
    }

    /**
     * DeleteReportSendSchedule
     * @param reportSendScheduleId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/reportsendscheduleid/{reportsendscheduleid}")
    @RESTDoc(methodName = "DeleteReportSendSchedule", desc = "Used to get all the Report Details", req_Params = {"reportsendscheduleid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoReports getXml_Delete(@PathParam("reportsendscheduleid") String reportSendScheduleId) {
        return deleteReportSendSchedules(reportSendScheduleId);
    }

    /**
     * DeleteReportSendSchedule
     * @param reportSendScheduleId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/delete/json/{json}/reportsendscheduleid/{reportsendscheduleid}")
    public TingcoReports getJson_Delete(@PathParam("reportsendscheduleid") String reportSendScheduleId) {
        return deleteReportSendSchedules(reportSendScheduleId);
    }

    /**
     * AddReportSendSchedules
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/add/rest/{rest}")
    @RESTDoc(methodName = "AddReportSendSchedules", desc = "Insert the Data into ReportSendSchedules", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoReports postXml_Add(String content) {
        return addReports(content);
    }

    /**
     * AddReportSendSchedules
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/add/json/{json}")
    public TingcoReports postJson_Add(String content) {
        return addReports(content);
    }

    /**
     * AddReportSettings
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addsettings/rest/{rest}")
    @RESTDoc(methodName = "AddReportSendScheduleSettings", desc = "Insert the Data into ReportSendSchedulesSettings", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoReports postXml_AddReportSettings(String content) {
        return addReportSettings(content);
    }

    /**
     * AddReportSettings
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addsettings/json/{json}")
    public TingcoReports postJson_AddReportSettings(String content) {
        return addReportSettings(content);
    }

    /**
     *
     * @param usageRecordId
     * @return TingcoReports
     */
    @GET
    @Produces("application/xml")
    @Path("/getusagelogdetailsbyid/rest/{rest}/usagerecordid/{usagerecordid}")
    @RESTDoc(methodName = "DeleteReportSendSchedule", desc = "Used to get all the Report Details", req_Params = {"usagerecordid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoReports getXml_UsageLogDetailsById(@PathParam("usagerecordid") String usageRecordId) {
        return getUsageLogDetailsById(usageRecordId);
    }

    /**
     *
     * @param usageRecordId
     * @return TingcoReports
     */
    @GET
    @Produces("application/json")
    @Path("/getusagelogdetailsbyid/json/{json}/usagerecordid/{usagerecordid}")
    @RESTDoc(methodName = "DeleteReportSendSchedule", desc = "Used to get all the Report Details", req_Params = {"usagerecordid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoReports getJson_UsageLogDetailsById(@PathParam("usagerecordid") String usageRecordId) {
        return getUsageLogDetailsById(usageRecordId);
    }

    /**
     *
     * @param usageRecordId
     * @param usageStartTime
     * @param usageStopTime
     * @param usageVolume
     * @param usageUnitId
     * @return TingcoReports
     */
    @GET
    @Produces("application/xml")
    @Path("/updateusagelogdetails/rest/{rest}/usagerecordid/{usagerecordid}/usagestarttime/{usagestarttime}/usagestoptime/{usagestoptime}{usagevolume:(/usagevolume/[^/]+?)?}{usageunitid:(/usageunitid/[^/]+?)?}")
    @RESTDoc(methodName = "UpdateUsageLogDetails", desc = "Used to get all the Report Details", req_Params = {"usagerecordid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoReports updateUsageLogDetail(@PathParam("usagerecordid") String usageRecordId, @PathParam("usagestarttime") String usageStartTime, @PathParam("usagestoptime") String usageStopTime, @PathParam("usagevolume") String usageVolume, @PathParam("usageunitid") String usageUnitId) {
        return updateUsageLogDetails(usageRecordId, usageStartTime, usageStopTime, usageVolume, usageUnitId);
    }

    /**
     *
     * @param usageRecordId
     * @param usageStartTime
     * @param usageStopTime
     * @param usageVolume
     * @param usageUnitId
     * @return TingcoReports
     */
    @GET
    @Produces("application/json")
    @Path("/updateusagelogdetails/json/{json}/usagerecordid/{usagerecordid}/usagestarttime/{usagestarttime}/usagestoptime/{usagestoptime}{usagevolume:(/usagevolume/[^/]+?)?}{usageunitid:(/usageunitid/[^/]+?)?}")
    @RESTDoc(methodName = "UpdateUsageLogDetails", desc = "Used to get all the Report Details", req_Params = {"usagerecordid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoReports updateUsageLogDetailJson(@PathParam("usagerecordid") String usageRecordId, @PathParam("usagestarttime") String usageStartTime, @PathParam("usagestoptime") String usageStopTime, @PathParam("usagevolume") String usageVolume, @PathParam("usageunitid") String usageUnitId) {
        return updateUsageLogDetails(usageRecordId, usageStartTime, usageStopTime, usageVolume, usageUnitId);
    }

    /**
     *
     * @param usageRecordId
     * @return TingcoReports
     */
    @GET
    @Produces("application/xml")
    @Path("/deleteusagelogdetails/rest/{rest}/usagerecordid/{usagerecordid}")
    @RESTDoc(methodName = "DeleteReportSendSchedule", desc = "Used to get all the Report Details", req_Params = {"usagerecordid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoReports Xml_DeleteUsageLogDetails(@PathParam("usagerecordid") String usageRecordId) {
        return deleteUsageLogDetails(usageRecordId);
    }

    /**
     *
     * @param usageRecordId
     * @return TingcoReports
     */
    @GET
    @Produces("application/json")
    @Path("/deleteusagelogdetails/json/{json}/usagerecordid/{usagerecordid}")
    @RESTDoc(methodName = "DeleteReportSendSchedule", desc = "Used to get all the Report Details", req_Params = {"usagerecordid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoReports json_DeleteUsageLogDetails(@PathParam("usagerecordid") String usageRecordId) {
        return deleteUsageLogDetails(usageRecordId);
    }

    /**
     * GetOrganizationProductSalesReportDetails
     * @param countryid
     * @param fromdate
     * @param todate
     * @param groupid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getorganizationproductsalesreportdetails/rest/{rest}/countryid/{countryid}/fromdate/{fromdate}/todate/{todate}{groupid:(/groupid/[^/]+?)?}")
    @RESTDoc(methodName = "GetOrganizationProductSalesReportDetails", desc = "Used to get all the Report Details", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoReports getXml_GetOrganizationProductSalesReportDetails(@PathParam("countryid") String countryid, @PathParam("fromdate") String fromdate,
            @PathParam("todate") String todate, @PathParam("groupid") String groupid) {
        return getOrganizationProductSalesReportDetails(countryid, fromdate, todate, groupid);
    }

    /**
     * GetOrganizationProductSalesReportDetails
     * @param countryid
     * @param fromdate
     * @param todate
     * @param groupid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getorganizationproductsalesreportdetails/json/{json}/countryid/{countryid}/fromdate/{fromdate}/todate/{todate}{groupid:(/groupid/[^/]+?)?}")
    public TingcoReports getJson_GetOrganizationProductSalesReportDetails(@PathParam("countryid") String countryid, @PathParam("fromdate") String fromdate,
            @PathParam("todate") String todate, @PathParam("groupid") String groupid) {
        return getOrganizationProductSalesReportDetails(countryid, fromdate, todate, groupid);
    }

    /**
     * GetDeviceProductCounterDetails
     * @param countryId
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getdeviceproductcounterdetails/rest/{rest}/countryid/{countryid}/deviceid/{deviceid}")
    @RESTDoc(methodName = "GetDeviceProductCounterDetails", desc = "Get Device Product Counter Details", req_Params = {"deviceid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoReports getXml_GetDeviceProductCounterDetails(@PathParam("countryid") String countryId, @PathParam("deviceid") String deviceId) {
        return getDeviceProductCounterDetails(countryId, deviceId);
    }

    /**
     * GetDeviceProductCounterDetails
     * @param countryId
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getdeviceproductcounterdetails/json/{json}/countryid/{countryid}/deviceid/{deviceid}")
    public TingcoReports getJson_GetDeviceProductCounterDetails(@PathParam("countryid") String countryId, @PathParam("deviceid") String deviceId) {
        return getDeviceProductCounterDetails(countryId, deviceId);
    }

    /**
     * GetDeviceProductCounterTotalCount
     * @param productVariantId
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getdeviceproductcountertotalcount/rest/{rest}/productvariantid/{productvariantid}/deviceid/{deviceid}")
    @RESTDoc(methodName = "GetDeviceProductCounterTotalCount", desc = "GetDeviceProductCounterTotalCount", req_Params = {"productvariantid;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoReports getXml_GetDeviceProductCounterTotalCount(@PathParam("productvariantid") String productVariantId, @PathParam("deviceid") String deviceId) {
        return getDeviceProductCounterTotalCount(productVariantId, deviceId);
    }

    /**
     * GetDeviceProductCounterTotalCount
     * @param productVariantId
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getdeviceproductcountertotalcount/json/{json}/productvariantid/{productvariantid}/deviceid/{deviceid}")
    public TingcoReports getJson_GetDeviceProductCounterTotalCount(@PathParam("productvariantid") String productVariantId, @PathParam("deviceid") String deviceId) {
        return getDeviceProductCounterTotalCount(productVariantId, deviceId);
    }

    /**
     * GetObjectUsageTypes
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getobjectusagetypes/rest/{rest}")
    @RESTDoc(methodName = "GetObjectUsageTypes", desc = "Used to Get ObjectUsageTypes", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoReports getXml_GetObjectUsageTypes() {
        return getObjectUsageTypes();
    }

    /**
     * GetObjectUsageTypes
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getobjectusagetypes/json/{json}")
    public TingcoReports getJson_GetObjectUsageTypes() {
        return getObjectUsageTypes();
    }

    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getsmslogdetails/rest/{rest}")
    @RESTDoc(methodName = "GetSMSLogDetails", desc = "Get SMS Log Details", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoReports postXml_GetSMSLogDetails(String content) throws ParseException {
        return getSMSLogDetails(content);
    }

    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getsmslogdetails/json/{json}")
    @RESTDoc(methodName = "GetSMSLogDetails", desc = "Get SMS Log Details", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoReports postJson_GetSMSLogDetails(String content) throws ParseException {
        return getSMSLogDetails(content);
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public ReportsResource getReportsResource() {
        return new ReportsResource();
    }

    @GET
    @Produces("application/xml")
    @Path("/getdevicecounthistorydetails/rest/{rest}/deviceid/{deviceid}/productvaraintid/{productvaraintid}")
    @RESTDoc(methodName = "Get DeviceCount History Details", desc = "Add New Product Counter For Device", req_Params = {"deviceid;UUID", "productvaraintid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoReports getXml_GetDeviceCountHistoryDetails(@PathParam("deviceid") String deviceId, @PathParam("productvaraintid") String productVaraintId) throws DatatypeConfigurationException {
        return getDeviceCountHistoryDetails(deviceId, productVaraintId);
    }

    @GET
    @Produces("application/json")
    @Path("/getdevicecounthistorydetails/json/{json}/deviceid/{deviceid}/productvaraintid/{productvaraintid}")
    @RESTDoc(methodName = "Get DeviceCount History Details", desc = "Add New Product Counter For Device", req_Params = {"deviceid;UUID", "productvaraintid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoReports getJson_GetDeviceCountHistoryDetails(@PathParam("deviceid") String deviceId, @PathParam("productvaraintid") String productVaraintId) throws DatatypeConfigurationException {
        return getDeviceCountHistoryDetails(deviceId, productVaraintId);
    }

    /**
     * GetUsageChartReport
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getusagechartreport/rest/{rest}")
    @RESTDoc(methodName = "GetUsageChartReport", desc = "Get UsageChart Report", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_getUsageChartReport(String content) throws ParseException {
        return getUsageChartReport(content);
    }

    /**
     * GetUsageChartReport
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getusagechartreport/json/{json}")
    public TingcoDevice postJson_getUsageChartReport(String content) throws ParseException {
        return getUsageChartReport(content);
    }

    /**
     * GetChargingStatisticsReport
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getchargingstatisticsreport/rest/{rest}")
    @RESTDoc(methodName = "GetChargingStatisticsReport", desc = "Get ChargingStatistics Report", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_getChargingStatisticsReport(String content) throws ParseException {
        return getChargingStatisticsReport(content);
    }

   /**
     * GetChargingStatisticsReport
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getchargingstatisticsreport/json/{json}")
    public TingcoDevice postJson_getChargingStatisticsReport(String content) throws ParseException {
        return getChargingStatisticsReport(content);
    }

    private TingcoDevice getChargingStatisticsReport(String content) {
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
                if (ht.containsKey(TCMUtil.REPORTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.REPORTS);
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
                    se.info24.devicejaxbPost.ObjectUsageRecords ourjaxb = deviceXML.getObjectUsageRecords().get(0);
                    String groupingBy = ourjaxb.getUsageReportGroupBy();
                    List<ObjectUsageSummaryReport> usageChartReportList = dao.getChargingStatisticsReport(deviceXML, groupingBy, session);
                    if (!usageChartReportList.isEmpty()) {
                        tingcoDevice = tingcoReportsXml.buildChargingStatisticsReport(tingcoDevice, usageChartReportList, groupingBy);
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Usage Chart Report found for the given input");
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

    private TingcoReports getDeviceCountHistoryDetails(String deviceId, String productVaraintId) {
        boolean hasPermission = false;
        Session session = null;
        Session sessionIsm = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoReports = tingcoReportsXml.buildTingcoReportsTemplate();
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
                    session = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    sessionIsm = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), sessionIsm);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), sessionIsm).getTimeZoneGmtoffset();
                        List<se.info24.ismOperationsPojo.ObjectProductVariantCounters> objectProductVariantCounters = productsDAO.getObjectProductVariantCountersByIDOperation(deviceId, productVaraintId, session);
                        if (objectProductVariantCounters.isEmpty()) {
                            tingcoReports.getMsgStatus().setResponseCode(-1);
                            tingcoReports.getMsgStatus().setResponseText("Data Not Found");
                            return tingcoReports;
                        }

                        return tingcoReportsXml.builtGetDeviceCountHistoryDetails(tingcoReports, objectProductVariantCounters, timeZoneGMToffset);
                    } else {
                        tingcoReports.getMsgStatus().setResponseCode(-1);
                        tingcoReports.getMsgStatus().setResponseText("User TimeZone is not found");
                        return tingcoReports;
                    }


                } else {
                    tingcoReports.getMsgStatus().setResponseCode(-10);
                    tingcoReports.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoReports;
                }
            } else {
                tingcoReports.getMsgStatus().setResponseCode(-3);
                tingcoReports.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoReports;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoReports.getMsgStatus().setResponseCode(-1);
            tingcoReports.getMsgStatus().setResponseText("Error occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoReports;
    }

    private TingcoReports getSMSLogDetails(String content) throws ParseException {
        boolean hasPermission = false;
        Session session = null;
        Session ismOperationSession = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoReports = tingcoReportsXml.buildTingcoReportsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.REPORTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.REPORTS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ismOperationSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    TingcoReports tingcoReport = (TingcoReports) JAXBManager.getInstance().unMarshall(content, TingcoReports.class);
                    if (tingcoReport.getInstantMessageLogs().getInstantMessageLog() != null || tingcoReport.getInstantMessageLogs().getInstantMessageLog().size() > 0) {
                        InstantMessageLog instantMessageLog = tingcoReport.getInstantMessageLogs().getInstantMessageLog().get(0);
                        UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                        if (userTimeZones2 != null) {
                            String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                            List<Object> objects = dao.getgetSMSLogDetails(instantMessageLog, timeZoneGMToffset, ismOperationSession);
                            if (objects.isEmpty()) {
                                tingcoReports.getMsgStatus().setResponseCode(-1);
                                tingcoReports.getMsgStatus().setResponseText("Data Not Found");
                                return tingcoReports;
                            }
                            List<String> imLogID = dao.converObjectToString(objects);
                            List<se.info24.ismOperationsPojo.InstantMessageLog> instantMessageLogs = dao.getInstantMessageLogByIds(ismOperationSession, imLogID);
                            return tingcoReportsXml.buildGetSMSLogDetails(tingcoReports, instantMessageLogs, timeZoneGMToffset);
                        } else {
                            tingcoReports.getMsgStatus().setResponseCode(-1);
                            tingcoReports.getMsgStatus().setResponseText("User TimeZone is not found");
                            return tingcoReports;
                        }
                    } else {
                        tingcoReports.getMsgStatus().setResponseCode(-1);
                        tingcoReports.getMsgStatus().setResponseText("XML is not valid");
                        return tingcoReports;
                    }
                } else {
                    tingcoReports.getMsgStatus().setResponseCode(-10);
                    tingcoReports.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoReports;
                }
            } else {
                tingcoReports.getMsgStatus().setResponseCode(-3);
                tingcoReports.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoReports;
            }
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoReports.getMsgStatus().setResponseCode(-1);
            tingcoReports.getMsgStatus().setResponseText("Error occur");
            return tingcoReports;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }

    }

    private TingcoReports getObjectUsageTypes() {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoReports = tingcoReportsXml.buildTingcoReportsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.REPORTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.REPORTS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tingcoReports = tingcoReportsXml.buildGetObjectUsageTypes(tingcoReports, dao.getAllObjectUsageTypesOrderByUsageTypeName(session));
                } else {
                    tingcoReports.getMsgStatus().setResponseCode(-10);
                    tingcoReports.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoReports;
                }
            } else {
                tingcoReports.getMsgStatus().setResponseCode(-3);
                tingcoReports.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoReports;
            }
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoReports.getMsgStatus().setResponseCode(-1);
            tingcoReports.getMsgStatus().setResponseText("Error occur");
            return tingcoReports;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoReports;
    }

    private TingcoReports getDeviceProductCounterTotalCount(String productVariantId, String deviceId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoReports = tingcoReportsXml.buildTingcoReportsTemplate();
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
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object object = productsDAO.getObjectProductVariantCountersByID(deviceId, productVariantId, session);
                    if (object != null) {
                        ObjectProductVariantCounters opvc = (ObjectProductVariantCounters) object;
                        tingcoReports = tingcoReportsXml.buildGetDeviceProductCounterTotalCount(opvc, tingcoReports);
                    } else {
                        tingcoReports.getMsgStatus().setResponseCode(-1);
                        tingcoReports.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoReports;
                    }
                } else {
                    tingcoReports.getMsgStatus().setResponseCode(-10);
                    tingcoReports.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoReports;
                }
            } else {
                tingcoReports.getMsgStatus().setResponseCode(-3);
                tingcoReports.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoReports;
            }
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoReports.getMsgStatus().setResponseCode(-1);
            tingcoReports.getMsgStatus().setResponseText("Error occur");
            return tingcoReports;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoReports;
    }

    private TingcoReports getDeviceProductCounterDetails(String countryId, String deviceId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoReports = tingcoReportsXml.buildTingcoReportsTemplate();
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
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ObjectProductVariantCounters> opvcList = dao.getObjectProductVariantCountersByObjectID(deviceId, session, 200);
                    if (opvcList.isEmpty()) {
                        tingcoReports.getMsgStatus().setResponseCode(-1);
                        tingcoReports.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoReports;
                    }
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    String timeZoneGMToffset = null;
                    if (userTimeZones2 != null) {
                        timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                    } else {
                        tingcoReports.getMsgStatus().setResponseCode(-10);
                        tingcoReports.getMsgStatus().setResponseText("User Permission is not given");
                        return tingcoReports;
                    }
                    List<String> productVariantIDs = new ArrayList<String>();
                    List<String> lastUpdatedByUserIDs = new ArrayList<String>();
                    for (ObjectProductVariantCounters opvc : opvcList) {
                        productVariantIDs.add(opvc.getId().getProductVariantId());
                        if (opvc.getLastUpdatedByUserId() != null) {
                            lastUpdatedByUserIDs.add(opvc.getLastUpdatedByUserId());
                        }
                    }
                    List<ProductVariantTranslations> pvtList = productsDAO.getProductVariantTranslations(session, productVariantIDs, Integer.parseInt(countryId));
                    List<Users2> users2List = new ArrayList<Users2>();
                    if (!lastUpdatedByUserIDs.isEmpty()) {
                        users2List = userDAO.getUser2ListByUserId(lastUpdatedByUserIDs, session);
                    }
                    tingcoReports = tingcoReportsXml.buildDeviceProductCounterDetails(opvcList, pvtList, users2List, timeZoneGMToffset, tingcoReports);
                } else {
                    tingcoReports.getMsgStatus().setResponseCode(-10);
                    tingcoReports.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoReports;
                }
            } else {
                tingcoReports.getMsgStatus().setResponseCode(-3);
                tingcoReports.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoReports;
            }
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoReports.getMsgStatus().setResponseCode(-1);
            tingcoReports.getMsgStatus().setResponseText("Error occur");
            return tingcoReports;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoReports;
    }

    private TingcoReports getOrganizationProductSalesReportDetails(String countryid, String fromdate, String todate, String groupid) {
        boolean hasPermission = false;
        Session sessionISMOperations = null;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoReports = tingcoReportsXml.buildTingcoReportsTemplate();
            if (groupid.equals("")) {
                groupid = null;
            } else {
                groupid = groupid.split("/")[2];
            }
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.REPORTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.REPORTS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    sessionISMOperations = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    session = HibernateUtil.getSessionFactory().openSession();

                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    String timeZoneGMToffset = null;
                    if (userTimeZones2 != null) {
                        timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                        gc.setTime(lFormat.parse(fromdate));
                        gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                        fromdate = lFormat.format(gc.getTime());
                        gc.setTime(lFormat.parse(todate));
                        gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                        todate = lFormat.format(gc.getTime());
                        se.info24.device.DeviceDAO deviceDAO = new se.info24.device.DeviceDAO();

//                        List<GroupAddresses> groupAddresses = groupDAO.getGroupAddressesByGroupIdAddressTypeId(session, groupid);
                        ObjectAddresses objectAddresseses = userDAO.getObjectAddressesByID(session, groupid, "3");

                        List<String> groupidList = new ArrayList<String>();
                        if (groupid != null) {
                            groupidList.add(groupid);
                        }
                        if (groupidList.isEmpty()) {
                            tingcoReports.getMsgStatus().setResponseCode(-1);
                            tingcoReports.getMsgStatus().setResponseText("Data Not Found");
                            return tingcoReports;
                        }
                        List<TransactionResult> transactionResultList = groupDAO.getTransactionResultBySearchStringGroupS(sessionISMOperations, groupidList, fromdate, todate);
                        if (transactionResultList.isEmpty()) {
                            tingcoReports.getMsgStatus().setResponseCode(-1);
                            tingcoReports.getMsgStatus().setResponseText("Data Not Found");
                            return tingcoReports;
                        }
                        Set<String> transactionId = new HashSet<String>();
                        for (TransactionResult transactionResult : transactionResultList) {
                            transactionId.add(transactionResult.getTransactionId());
                        }
//                        List<Object> transactionProductsLists = groupDAO.getTransactionProductsByTransactionIdsCreatedDates(sessionISMOperations, transactionId);
                        List<Object> transactionProductsList = groupDAO.getTransactionProductsByTransactionIdsCreatedDate(sessionISMOperations, transactionId);
                        if (transactionProductsList.isEmpty()) {
                            tingcoReports.getMsgStatus().setResponseCode(-1);
                            tingcoReports.getMsgStatus().setResponseText("Data Not Found");
                            return tingcoReports;
                        }
                        Set<String> productVariantIDSet = new HashSet<String>();

                        for (Iterator itr = transactionProductsList.iterator(); itr.hasNext();) {
                            Object[] row = (Object[]) itr.next();
                            for (int i = 0; i < row.length; i++) {
                                if (i % 2 == 0) {
                                    if (row[i] != null) {
                                        productVariantIDSet.add(row[i].toString());
                                    }
                                    i += 6;
                                }
                            }
                        }
                        List<String> productVariantID = new ArrayList<String>(productVariantIDSet);
                        List<ProductVariantTranslations> pvt = new ArrayList<ProductVariantTranslations>();
                        List<List<String>> splitList = TCMUtil.splitStringList(new ArrayList(productVariantID), 2000);
                        for (List<String> list : splitList) {
                            List<ProductVariantTranslations> pvttemp = deviceDAO.getProductVariantTranslations(session, list, Integer.parseInt(countryid), 0);
                            pvt.addAll(pvttemp);
                        }

                        tingcoReports = tingcoReportsXml.buildGetOrganizationProductSalesReportDetails(session, tingcoReports, transactionProductsList, objectAddresseses, pvt, transactionResultList);
                    } else {
                        tingcoReports.getMsgStatus().setResponseCode(-1);
                        tingcoReports.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                        return tingcoReports;
                    }
                } else {
                    tingcoReports.getMsgStatus().setResponseCode(-10);
                    tingcoReports.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoReports;
                }
            } else {
                tingcoReports.getMsgStatus().setResponseCode(-3);
                tingcoReports.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoReports;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoReports.getMsgStatus().setResponseCode(-1);
            tingcoReports.getMsgStatus().setResponseText("Error occured");
            return tingcoReports;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
            if (sessionISMOperations != null) {
                sessionISMOperations.close();
            }
        }
        return tingcoReports;
    }

    private TingcoReports deleteUsageLogDetails(String usageRecordId) {
        boolean hasPermission = false;
        Session sessionISMOperations = null;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoReports = tingcoReportsXml.buildTingcoReportsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.REPORTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.REPORTS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    sessionISMOperations = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    ObjectUsageRecords objectUsageRecords = dao.getUsageLogDetailsById(sessionISMOperations, usageRecordId);
                    if (objectUsageRecords != null) {
                        if (dao.deleteObjectUsageRecords(sessionISMOperations, objectUsageRecords)) {
                            session = HibernateUtil.getSessionFactory().openSession();
                            RestUtilDAO restUtilDAO = new RestUtilDAO();
                            UserLog userLog = new UserLog();
                            userLog.setUserId(sessions2.getUserId());
                            userLog.setAction("UpdateUsageRecords");
                            userLog.setTableName("ObjectUsageRecords");
                            userLog.setRequestIp(request.getRemoteHost());
                            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                            userLog.setCreatedDate(gc.getTime());
                            userLog.setUpdatedDate(gc.getTime());
                            if (!restUtilDAO.saveUserLog(userLog, session)) {
                                tingcoReports.getMsgStatus().setResponseCode(-1);
                                tingcoReports.getMsgStatus().setResponseText("Error occur in userLog");
                                return tingcoReports;
                            }
                            tingcoReports.getMsgStatus().setResponseCode(0);
                            tingcoReports.getMsgStatus().setResponseText("Deleted");
                            return tingcoReports;
                        } else {
                            tingcoReports.getMsgStatus().setResponseCode(-1);
                            tingcoReports.getMsgStatus().setResponseText("Error occur");
                            return tingcoReports;
                        }
                    } else {
                        tingcoReports.getMsgStatus().setResponseCode(-1);
                        tingcoReports.getMsgStatus().setResponseText("Data is not Found");
                        return tingcoReports;
                    }


                } else {
                    tingcoReports.getMsgStatus().setResponseCode(-10);
                    tingcoReports.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoReports;
                }
            } else {
                tingcoReports.getMsgStatus().setResponseCode(-3);
                tingcoReports.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoReports;
            }
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoReports.getMsgStatus().setResponseCode(-1);
            tingcoReports.getMsgStatus().setResponseText("Error occur");
            return tingcoReports;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
            if (sessionISMOperations != null) {
                sessionISMOperations.close();
            }
        }
    }

    private TingcoDevice getUsageChartReport(String content) {
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
                if (ht.containsKey(TCMUtil.REPORTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.REPORTS);
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
                    se.info24.devicejaxbPost.ObjectUsageRecords ourjaxb = deviceXML.getObjectUsageRecords().get(0);
                    String groupingBy = ourjaxb.getUsageReportGroupBy();
                    List<ObjectUsageSummaryReport> usageChartReportList = dao.getUsageChartReport(deviceXML, groupingBy, session);
                    if (!usageChartReportList.isEmpty()) {
                        Object object = dao.getMeasurementTypesById(session, ourjaxb.getObjectTypeID());
                        MeasurementTypes measurementTypes = (MeasurementTypes) object;
                        tingcoDevice = tingcoReportsXml.buildUsageChartReport(tingcoDevice, usageChartReportList, groupingBy, measurementTypes.getObjectUsageUnits().getUsageUnitName());
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Usage Chart Report found for the given input");
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

    private TingcoReports updateUsageLogDetails(String usageRecordId, String usageStartTime, String usageStopTime, String usageVolume, String usageUnitId) {
        boolean hasPermission = false;
        Session session = null;
        Session sessionISMOperations = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoReports = tingcoReportsXml.buildTingcoReportsTemplate();
            if (request.getAttribute("USERSESSION") != null) {

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.REPORTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.REPORTS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    if (usageVolume.equals("")) {
                        usageVolume = null;
                    } else {
                        usageVolume = usageVolume.split("/")[2];
                    }

                    if (usageUnitId.equals("")) {
                        usageUnitId = null;
                    } else {
                        usageUnitId = usageUnitId.split("/")[2];
                    }
                    sessionISMOperations = HibernateUtil.getISMOperationsSessionFactory().openSession();


                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    GregorianCalendar gc1 = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    ObjectUsageRecords objectUsageRecords = dao.getUsageLogDetailsById(sessionISMOperations, usageRecordId);
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        if (objectUsageRecords != null) {
                            gc.setTime(lFormat.parse(usageStartTime));
                            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                            objectUsageRecords.setUsageStartTime(gc.getTime());
                            gc.setTime(lFormat.parse(usageStopTime));
                            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                            objectUsageRecords.setUsageStopTime(gc.getTime());
                            if (usageVolume != null) {
                                objectUsageRecords.setUsageVolume(usageVolume);
                            } else {
                                objectUsageRecords.setUsageVolume(null);
                            }
                            if (usageUnitId != null) {
                                objectUsageRecords.setUsageUnitId(usageUnitId);
                            } else {
                                objectUsageRecords.setUsageUnitId(null);
                            }
                            objectUsageRecords.setUpdatedDate(gc1.getTime());
                            if (dao.saveObjectUsageRecords(sessionISMOperations, objectUsageRecords)) {
                                RestUtilDAO restUtilDAO = new RestUtilDAO();
                                UserLog userLog = new UserLog();
                                userLog.setUserId(sessions2.getUserId());
                                userLog.setAction("UpdateUsageRecords");
                                userLog.setTableName("ObjectUsageRecords");
                                userLog.setRequestIp(request.getRemoteHost());
                                userLog.setCreatedDate(gc1.getTime());
                                userLog.setUpdatedDate(gc1.getTime());
                                if (!restUtilDAO.saveUserLog(userLog, session)) {
                                    tingcoReports.getMsgStatus().setResponseCode(-1);
                                    tingcoReports.getMsgStatus().setResponseText("Error occur in userLog");
                                    return tingcoReports;
                                }
                                tingcoReports.getMsgStatus().setResponseCode(0);
                                tingcoReports.getMsgStatus().setResponseText("Updated");
                                return tingcoReports;



                            } else {
                                tingcoReports.getMsgStatus().setResponseCode(-1);
                                tingcoReports.getMsgStatus().setResponseText("Error occur");
                                return tingcoReports;
                            }
                        } else {
                            tingcoReports.getMsgStatus().setResponseCode(-1);
                            tingcoReports.getMsgStatus().setResponseText("Data is not Found");
                            return tingcoReports;
                        }
                    } else {
                        tingcoReports.getMsgStatus().setResponseCode(-1);
                        tingcoReports.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                        return tingcoReports;
                    }
                } else {
                    tingcoReports.getMsgStatus().setResponseCode(-10);
                    tingcoReports.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoReports;
                }
            } else {
                tingcoReports.getMsgStatus().setResponseCode(-3);
                tingcoReports.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoReports;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoReports.getMsgStatus().setResponseCode(-1);
            tingcoReports.getMsgStatus().setResponseText("Error occur");
            return tingcoReports;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
            if (sessionISMOperations != null) {
                sessionISMOperations.close();
            }
//            System.gc();
        }
    }

    private TingcoReports addReportSettings(String content) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoReports = tingcoReportsXml.buildTingcoReportsTemplate();
            if (request.getAttribute("USERSESSION") != null) {

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
                    TingcoReports tingcoReport = (TingcoReports) JAXBManager.getInstance().unMarshall(content, TingcoReports.class);
                    if (!tingcoReport.getReports().getReport().isEmpty()) {
                        for (Report r : tingcoReport.getReports().getReport()) {
                            for (ReportSendScheduleSetting rss : r.getReportSendScheduleSetting()) {
                                if (dao.getReportTemplateSettings(r.getReportID(), rss.getSettingName(), session) != 0) {
                                    ReportSendScheduleSettings rsss = new ReportSendScheduleSettings();
                                    rsss.setReportSendScheduleSettingId(UUID.randomUUID().toString());
                                    rsss.setReportSendScheduleId(rss.getReportSendScheduleID());
                                    rsss.setSettingName(rss.getSettingName());
                                    rsss.setSettingValue(rss.getSettingValue());
                                    rsss.setLastUpdatedByUserId(sessions2.getUserId());
                                    GregorianCalendar gc = new GregorianCalendar();
                                    rsss.setCreatedDate(gc.getTime());
                                    rsss.setUpdatedDate(gc.getTime());
                                    if (!dao.addReportsSendScheduleSettings(rsss, session)) {
                                        tingcoReports.getMsgStatus().setResponseCode(-1);
                                        tingcoReports.getMsgStatus().setResponseText("Error Occured while Inserting");
                                    }
                                }
                            }
                        }
                    }
                    return tingcoReports;
                } else {
                    tingcoReports.getMsgStatus().setResponseCode(-10);
                    tingcoReports.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoReports;
                }

            } else {
                tingcoReports.getMsgStatus().setResponseCode(-3);
                tingcoReports.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoReports;
            }
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoReports.getMsgStatus().setResponseCode(-1);
            tingcoReports.getMsgStatus().setResponseText("Error Occured");
            return tingcoReports;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }

    }

    private TingcoReports addReports(String content) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoReports = tingcoReportsXml.buildTingcoReportsTemplate();
            if (request.getAttribute("USERSESSION") != null) {

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
                    TingcoReports tingcoReport = (TingcoReports) JAXBManager.getInstance().unMarshall(content, TingcoReports.class);
                    if (!tingcoReport.getReports().getReport().isEmpty()) {
                        se.info24.reportsjaxb.ReportSendSchedules rssj = new se.info24.reportsjaxb.ReportSendSchedules();
                        for (Report r : tingcoReport.getReports().getReport()) {
                            for (ReportSendSchedule tr : r.getReportSendSchedule()) {
                                if (tr.getSendDaily() != 0) {
                                    ReportSendSchedules rss = new ReportSendSchedules();
                                    rss.setGroupId(tr.getGroupID());
                                    rss.setReportSendScheduleId(tr.getReportSendScheduleID());
                                    rss.setReportId(tr.getReportID());
                                    rss.setMeasurementTypeId(tr.getMeasurementTypeID());
                                    rss.setSendToEmail(tr.getSendToEmail());
                                    rss.setSendHourly(tr.getSendHourly());
                                    String uuid = UUID.randomUUID().toString();
                                    rss.setReportSendScheduleId(uuid);

                                    rss.setSendDaily(tr.getSendDaily());
                                    rss.setSendOnHour(tr.getSendOnHour());
                                    rss.setSendOnMinute(tr.getSendOnMinute());

                                    rss.setSendWeekly(0);
                                    rss.setSendOnWeekday(0);
                                    rss.setSendMonthly(0);
                                    rss.setSendOnDayOfMonth(0);

                                    rss.setSendQuarterly(tr.getSendQuarterly());
                                    rss.setSendYearly(tr.getSendYearly());
                                    rss.setSendOnMonth(tr.getSendOnMonth());
                                    rss.setSendAsPdf(tr.getSendAsPdf());
                                    rss.setSendAsCsv(tr.getSendAsCsv());
                                    rss.setSendAsExcel(tr.getSendAsExcel());
                                    rss.setLastUpdatedByUserId(sessions2.getUserId());
                                    GregorianCalendar gc = new GregorianCalendar();
                                    rss.setCreatedDate(gc.getTime());
                                    rss.setUpdatedDate(gc.getTime());

                                    ReportSendSchedule rs = new ReportSendSchedule();
                                    rs.setReportSendScheduleID(uuid);
                                    rssj.getReportSendSchedule().add(rs);

                                    if (!dao.addReports(rss, session)) {
                                        tingcoReports.getMsgStatus().setResponseCode(-1);
                                        tingcoReports.getMsgStatus().setResponseText("Error Occured while Inserting");
                                        return tingcoReports;
                                    }
                                }

                                if (tr.getSendWeekly() != 0) {
                                    ReportSendSchedules rss = new ReportSendSchedules();
                                    rss.setGroupId(tr.getGroupID());
                                    rss.setReportSendScheduleId(tr.getReportSendScheduleID());
                                    rss.setReportId(tr.getReportID());
                                    rss.setMeasurementTypeId(tr.getMeasurementTypeID());
                                    rss.setSendToEmail(tr.getSendToEmail());
                                    rss.setSendHourly(tr.getSendHourly());
                                    String uuid = UUID.randomUUID().toString();
                                    rss.setReportSendScheduleId(uuid);
                                    rss.setSendWeekly(tr.getSendWeekly());
                                    List<GroupWeekdays> weekDaysList = dao.getGroupWeekdays(tr.getWeekdayID(), session);
                                    if (!weekDaysList.isEmpty()) {
                                        rss.setSendOnWeekday(weekDaysList.get(0).getWeekdayValue());
                                    }

                                    rss.setSendDaily(0);
                                    rss.setSendOnHour(tr.getSendOnWeekdayHour());
                                    rss.setSendOnMinute(tr.getSendOnWeekdayMinute());
                                    rss.setSendMonthly(0);
                                    rss.setSendOnDayOfMonth(0);
                                    rss.setSendQuarterly(tr.getSendQuarterly());
                                    rss.setSendYearly(tr.getSendYearly());
                                    rss.setSendOnMonth(tr.getSendOnMonth());
                                    rss.setSendAsPdf(tr.getSendAsPdf());
                                    rss.setSendAsCsv(tr.getSendAsCsv());
                                    rss.setSendAsExcel(tr.getSendAsExcel());
                                    rss.setLastUpdatedByUserId(sessions2.getUserId());
                                    GregorianCalendar gc = new GregorianCalendar();
                                    rss.setCreatedDate(gc.getTime());
                                    rss.setUpdatedDate(gc.getTime());

                                    ReportSendSchedule rs = new ReportSendSchedule();
                                    rs.setReportSendScheduleID(uuid);
                                    rssj.getReportSendSchedule().add(rs);

                                    if (!dao.addReports(rss, session)) {
                                        tingcoReports.getMsgStatus().setResponseCode(-1);
                                        tingcoReports.getMsgStatus().setResponseText("Error Occured while Inserting");
                                        return tingcoReports;
                                    }
                                }

                                if (tr.getSendMonthly() != 0) {
                                    ReportSendSchedules rss = new ReportSendSchedules();
                                    rss.setGroupId(tr.getGroupID());
                                    rss.setReportSendScheduleId(tr.getReportSendScheduleID());
                                    rss.setReportId(tr.getReportID());
                                    rss.setMeasurementTypeId(tr.getMeasurementTypeID());
                                    rss.setSendToEmail(tr.getSendToEmail());
                                    rss.setSendHourly(tr.getSendHourly());
                                    String uuid = UUID.randomUUID().toString();
                                    rss.setReportSendScheduleId(uuid);
                                    rss.setSendMonthly(tr.getSendMonthly());
                                    rss.setSendOnDayOfMonth(tr.getSendOnDayOfMonth());
                                    rss.setLastUpdatedByUserId(sessions2.getUserId());

                                    rss.setSendDaily(0);
                                    rss.setSendOnHour(tr.getSendOnMonthHour());
                                    rss.setSendOnMinute(tr.getSendOnMonthMinute());
                                    rss.setSendWeekly(0);
                                    rss.setSendOnWeekday(0);
                                    rss.setSendQuarterly(tr.getSendQuarterly());
                                    rss.setSendYearly(tr.getSendYearly());
                                    rss.setSendOnMonth(tr.getSendOnMonth());
                                    rss.setSendAsPdf(tr.getSendAsPdf());
                                    rss.setSendAsCsv(tr.getSendAsCsv());
                                    rss.setSendAsExcel(tr.getSendAsExcel());
                                    GregorianCalendar gc = new GregorianCalendar();
                                    rss.setCreatedDate(gc.getTime());
                                    rss.setUpdatedDate(gc.getTime());

                                    ReportSendSchedule rs = new ReportSendSchedule();
                                    rs.setReportSendScheduleID(uuid);
                                    rssj.getReportSendSchedule().add(rs);

                                    if (!dao.addReports(rss, session)) {
                                        tingcoReports.getMsgStatus().setResponseCode(-1);
                                        tingcoReports.getMsgStatus().setResponseText("Error Occured while Inserting");
                                        return tingcoReports;
                                    }
                                }
                            }
                        }
                        tingcoReports.setReportSendSchedules(rssj);

                    }
                    return tingcoReports;
                } else {
                    tingcoReports.getMsgStatus().setResponseCode(-10);
                    tingcoReports.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoReports;
                }

            } else {
                tingcoReports.getMsgStatus().setResponseCode(-3);
                tingcoReports.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoReports;
            }
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoReports.getMsgStatus().setResponseCode(-1);
            tingcoReports.getMsgStatus().setResponseText("Error Occured");
            return tingcoReports;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }


    }

    private TingcoReports deleteReportSendSchedules(String reportSendScheduleId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoReports = tingcoReportsXml.buildTingcoReportsTemplate();
            if (request.getAttribute("USERSESSION") != null) {

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
                    if (!dao.deleteReportSendSchedules(session, reportSendScheduleId)) {
                        tingcoReports.getMsgStatus().setResponseCode(-1);
                        tingcoReports.getMsgStatus().setResponseText("Error Occured while Deleting");
                    }
                    return tingcoReports;
                } else {
                    tingcoReports.getMsgStatus().setResponseCode(-10);
                    tingcoReports.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoReports;
                }
            } else {
                tingcoReports.getMsgStatus().setResponseCode(-3);
                tingcoReports.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoReports;
            }
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoReports.getMsgStatus().setResponseCode(-1);
            tingcoReports.getMsgStatus().setResponseText("Error Occured");
            return tingcoReports;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }

    }

    private TingcoReports getAllObjectUsageUnits() {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoReports = tingcoReportsXml.buildTingcoReportsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.REPORTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.REPORTS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ObjectUsageUnits> objectUsageUnitsList = dao.getAllObjectUsageUnits(session);
                    tingcoReports = tingcoReportsXml.buildObjectUsageUnits(tingcoReports, objectUsageUnitsList);
                } else {
                    tingcoReports.getMsgStatus().setResponseCode(-10);
                    tingcoReports.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoReports;
                }
            } else {
                tingcoReports.getMsgStatus().setResponseCode(-3);
                tingcoReports.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoReports;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoReports;
    }

    private TingcoReports getReportSendSchedules() {
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            try {
                tingcoReports = tingcoReportsXml.buildTingcoReportsTemplate();
            } catch (DatatypeConfigurationException ex) {
                TCMUtil.exceptionLog(getClass().getName(), ex);
            }
            session = HibernateUtil.getSessionFactory().openSession();
            List<ReportSendSchedules> reportList = dao.getReportSendSchedules(session);
            if (!reportList.isEmpty()) {
                tingcoReports = tingcoReportsXml.buildTingcoReports(tingcoReports, reportList, session);
            } else {
                tingcoReports.getMsgStatus().setResponseCode(-1);
                tingcoReports.getMsgStatus().setResponseText("No Data found");
                return tingcoReports;
            }
            return tingcoReports;
        } catch (HibernateException ex) {
            tingcoReports.getMsgStatus().setResponseCode(-1);
            tingcoReports.getMsgStatus().setResponseText("Database Exception");
            return tingcoReports;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
    }

    private TingcoReports getReportSendSchedules(String reportCode, String groupId, String measurementTypeId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoReports = tingcoReportsXml.buildTingcoReportsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (reportCode.equals("")) {
                    tingcoReports.getMsgStatus().setResponseCode(-1);
                    tingcoReports.getMsgStatus().setResponseText("Report Code should not be empty");
                    return tingcoReports;
                }

                if (groupId.equals("")) {
                    tingcoReports.getMsgStatus().setResponseCode(-1);
                    tingcoReports.getMsgStatus().setResponseText("GroupID should not be empty");
                    return tingcoReports;
                }

                if (measurementTypeId.equals("")) {
                    tingcoReports.getMsgStatus().setResponseCode(-1);
                    tingcoReports.getMsgStatus().setResponseText("MeasurementTypeID should not be empty");
                    return tingcoReports;
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
                    List<ReportSendSchedules> reportList = dao.getReportSendSchedules(reportCode, groupId, measurementTypeId, session);
                    if (!reportList.isEmpty()) {
                        tingcoReports = tingcoReportsXml.buildTingcoReportSendSchedules(tingcoReports, reportList);
                    } else {
                        tingcoReports.getMsgStatus().setResponseCode(-1);
                        tingcoReports.getMsgStatus().setResponseText("No Data found");
                    }
                    return tingcoReports;
                } else {
                    tingcoReports.getMsgStatus().setResponseCode(-10);
                    tingcoReports.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoReports;
                }
            } else {
                tingcoReports.getMsgStatus().setResponseCode(-3);
                tingcoReports.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoReports;
            }
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoReports.getMsgStatus().setResponseCode(-1);
            tingcoReports.getMsgStatus().setResponseText("Error Occured");
            return tingcoReports;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }


    }

    private TingcoReports getReports(String groupId, int countryId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoReports = tingcoReportsXml.buildTingcoReportsTemplate();
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
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<Reports> reportList = dao.getReportsByGroupId(session, groupId);
                    if (!reportList.isEmpty()) {
                        tingcoReports = tingcoReportsXml.buildTingcoReportsandTranslations(tingcoReports, reportList, countryId, session);
                    } else {
                        tingcoReports.getMsgStatus().setResponseCode(-1);
                        tingcoReports.getMsgStatus().setResponseText("No Data found");
                    }
                    return tingcoReports;
                } else {
                    tingcoReports.getMsgStatus().setResponseCode(-10);
                    tingcoReports.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoReports;
                }
            } else {
                tingcoReports.getMsgStatus().setResponseCode(-3);
                tingcoReports.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoReports;
            }
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoReports.getMsgStatus().setResponseCode(-1);
            tingcoReports.getMsgStatus().setResponseText("Error Occured");
            return tingcoReports;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
    }

    private TingcoReports getUsageLogDetailsById(String usageRecordId) {
        boolean hasPermission = false;
        Session ismOperationssession = null;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoReports = tingcoReportsXml.buildTingcoReportsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.REPORTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.REPORTS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    ismOperationssession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        ObjectUsageRecords objectUsageRecords = dao.getUsageLogDetailsById(ismOperationssession, usageRecordId);
                        if (objectUsageRecords != null) {
                            tingcoReports = tingcoReportsXml.buildUsageLogDetailsById(tingcoReports, objectUsageRecords, timeZoneGMToffset);
                            return tingcoReports;
                        } else {
                            tingcoReports.getMsgStatus().setResponseCode(-1);
                            tingcoReports.getMsgStatus().setResponseText("Data is not Found");
                            return tingcoReports;
                        }
                    } else {
                        tingcoReports.getMsgStatus().setResponseCode(-1);
                        tingcoReports.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                        return tingcoReports;
                    }


                } else {
                    tingcoReports.getMsgStatus().setResponseCode(-10);
                    tingcoReports.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoReports;
                }
            } else {
                tingcoReports.getMsgStatus().setResponseCode(-3);
                tingcoReports.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoReports;
            }
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoReports.getMsgStatus().setResponseCode(-1);
            tingcoReports.getMsgStatus().setResponseText("Error occur");
            return tingcoReports;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}

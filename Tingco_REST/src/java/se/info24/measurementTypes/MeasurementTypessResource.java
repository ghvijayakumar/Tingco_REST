/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.measurementTypes;

import se.info24.jaxbUtil.TingcoMeasurementXML;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import org.hibernate.Session;
import se.info24.device.DeviceDAO;
import se.info24.ismOperationsPojo.MeasurementData;
import se.info24.measurementjaxb.TingcoMeasurementTypes;
import se.info24.pojo.Device;
import se.info24.pojo.DeviceTypeMeasurementTypes;
import se.info24.pojo.DeviceTypeMeasurementTypesId;
import se.info24.pojo.MeasurementTypeTranslations;
import se.info24.pojo.MeasurementTypesInGroups;
import se.info24.pojo.MeasurementTypesInGroupsId;
import se.info24.pojo.UserSessions2;
import se.info24.user.User_LoginsResource;
import se.info24.util.HibernateUtil;
import se.info24.util.JAXBManager;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/measurementtypes")
public class MeasurementTypessResource {

    @Context
    private UriInfo context;
    private MeasurementDAO dao = new MeasurementDAO();
    @Context
    private HttpServletRequest request;
//    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of MeasurementTypessResource */
    public MeasurementTypessResource() {
    }

    /**
     * GetMeasurementDataByGroupID
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getbygroupid/rest/{rest}/groupid/{groupid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetMeasurementDataByGroupId", desc = "Get MeasurementData Values", req_Params = {"GroupID;UUID", "CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoMeasurementTypes getXml_ByGroupId(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getMeasurementTypesByGroupId(groupId, countryId);
    }

    /**
     * GetMeasurementDataByGroupID
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getbygroupid/json/{json}/groupid/{groupid}/countryid/{countryid}")
    public TingcoMeasurementTypes getJson_ByGroupId(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getMeasurementTypesByGroupId(groupId, countryId);
    }

    /**
     * GetMeasurementDataByDeviceTypeID
     * @param deviceId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getbydevicetypeid/rest/{rest}/deviceid/{deviceid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetMeasurementDataByGroupId", desc = "Get MeasurementData Values", req_Params = {"DeviceID;UUID", "CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoMeasurementTypes getXml_getBydeviceTypeId(@PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId) {
        return getMeasurementTypesByDeviceTypeId(deviceId, countryId);
    }

    /**
     * GetMeasurementDataByDeviceTypeID
     * @param deviceId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getbydevicetypeid/json/{json}/deviceid/{deviceid}/countryid/{countryid}")
    public TingcoMeasurementTypes getJson_getBydeviceTypeId(@PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId) {
        return getMeasurementTypesByDeviceTypeId(deviceId, countryId);
    }

    /**
     * GetUsageReportsByGroupsWithoutGrouping
     * @param reportId
     * @param groupId
     * @param deviceId
     * @param fromDate
     * @param toDate
     * @param dataCal
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getusagereportswithoutgrouping/rest/{rest}/reportid/{reportid}/groupid/{groupid}{deviceid:(/deviceid/[^/]+?)?}/fromdate/{fromdate}/todate/{todate}/datacalculation/{datacalculation}")
    @RESTDoc(methodName = "GetUsageReportsByGroupsWithoutGrouping", desc = "Get the Usage Reports", req_Params = {"ReportID;UUID", "GroupID;UUID", "FromDate;String", "ToDate;String", "DataCalculation;String"}, opt_Params = {"DeviceID;UUID"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoMeasurementTypes getXml_UsageReportsWithoutGrouping(@PathParam("reportid") String reportId, @PathParam("groupid") String groupId, @PathParam("deviceid") String deviceId,
            @PathParam("fromdate") String fromDate, @PathParam("todate") String toDate, @PathParam("datacalculation") String dataCal) {
        if (deviceId.equals("")) {
            deviceId = null;
        } else {
            deviceId = deviceId.split("/")[2];
        }
        return getMeasurementDataWithoutGrouping(reportId, groupId, deviceId, fromDate, toDate, dataCal);
    }

    /**
     * GetUsageReportsByGroupsWithoutGrouping
     * @param reportId
     * @param groupId
     * @param deviceId
     * @param fromDate
     * @param toDate
     * @param dataCal
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getusagereportswithoutgrouping/json/{json}/reportid/{reportid}/groupid/{groupid}{deviceid:(/deviceid/[^/]+?)?}/fromdate/{fromdate}/todate/{todate}/datacalculation/{datacalculation}")
    public TingcoMeasurementTypes getJson_UsageReportsWithoutGrouping(@PathParam("reportid") String reportId, @PathParam("groupid") String groupId, @PathParam("deviceid") String deviceId,
            @PathParam("fromdate") String fromDate, @PathParam("todate") String toDate, @PathParam("datacalculation") String dataCal) {
        if (deviceId.equals("")) {
            deviceId = null;
        } else {
            deviceId = deviceId.split("/")[2];
        }
        return getMeasurementDataWithoutGrouping(reportId, groupId, deviceId, fromDate, toDate, dataCal);
    }

    /**
     * GetUsageReportsByGroupsWithGrouping
     * @param reportId
     * @param groupId
     * @param deviceId
     * @param fromDate
     * @param toDate
     * @param dataCal
     * @param groupBy
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getusagereportswithgrouping/rest/{rest}/reportid/{reportid}/groupid/{groupid}{deviceid:(/deviceid/[^/]+?)?}/fromdate/{fromdate}/todate/{todate}/datacalculation/{datacalculation}/groupby/{groupby}")
    @RESTDoc(methodName = "GetUsageReportsByGroupsWithGrouping", desc = "Get the Usage Reports", req_Params = {"ReportID;UUID", "GroupID;UUID", "FromDate;String", "ToDate;String", "DataCalculation;String", "GroupBy;String"}, opt_Params = {"DeviceID;UUID"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoMeasurementTypes getXml_UsageReportsWithGrouping(@PathParam("reportid") String reportId, @PathParam("groupid") String groupId, @PathParam("deviceid") String deviceId,
            @PathParam("fromdate") String fromDate, @PathParam("todate") String toDate, @PathParam("datacalculation") String dataCal, @PathParam("groupby") String groupBy) {
        if (deviceId.equals("")) {
            deviceId = null;
        } else {
            deviceId = deviceId.split("/")[2];
        }
        return getMeasurementDataWithGrouping(reportId, groupId, deviceId, fromDate, toDate, dataCal, groupBy);
    }

    /**
     * GetUsageReportsByGroupsWithGrouping
     * @param reportId
     * @param groupId
     * @param deviceId
     * @param fromDate
     * @param toDate
     * @param dataCal
     * @param groupBy
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getusagereportswithgrouping/json/{json}/reportid/{reportid}/groupid/{groupid}{deviceid:(/deviceid/[^/]+?)?}/fromdate/{fromdate}/todate/{todate}/datacalculation/{datacalculation}/groupby/{groupby}")
    public TingcoMeasurementTypes getJson_UsageReportsWithGrouping(@PathParam("reportid") String reportId, @PathParam("groupid") String groupId, @PathParam("deviceid") String deviceId,
            @PathParam("fromdate") String fromDate, @PathParam("todate") String toDate, @PathParam("datacalculation") String dataCal, @PathParam("groupby") String groupBy) {
        if (deviceId.equals("")) {
            deviceId = null;
        } else {
            deviceId = deviceId.split("/")[2];
        }
        return getMeasurementDataWithGrouping(reportId, groupId, deviceId, fromDate, toDate, dataCal, groupBy);
    }

    @GET
    @Produces("application/xml")
    @Path("/getusagereportswithgroupingdemo/rest/{rest}/reportid/{reportid}/groupid/{groupid}{deviceid:(/deviceid/[^/]+?)?}/fromdate/{fromdate}/todate/{todate}/datacalculation/{datacalculation}/groupby/{groupby}")
    @RESTDoc(methodName = "GetUsageReportsByGroupsWithGrouping", desc = "Get the Usage Reports", req_Params = {"ReportID;UUID", "GroupID;UUID", "FromDate;String", "ToDate;String", "DataCalculation;String", "GroupBy;String"}, opt_Params = {"DeviceID;UUID"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoMeasurementTypes getXml_UsageReportsWithGroupingdemo(@PathParam("reportid") String reportId, @PathParam("groupid") String groupId, @PathParam("deviceid") String deviceId,
            @PathParam("fromdate") String fromDate, @PathParam("todate") String toDate, @PathParam("datacalculation") String dataCal, @PathParam("groupby") String groupBy) {
        if (deviceId.equals("")) {
            deviceId = null;
        } else {
            deviceId = deviceId.split("/")[2];
        }
        return getMeasurementDataWithGrouping(reportId, groupId, deviceId, fromDate, toDate, dataCal, groupBy);
    }

    /**
     * GetWindowsUsageReportsByGroupsWithGrouping
     * @param reportId
     * @param groupId
     * @param deviceId
     * @param fromDate
     * @param toDate
     * @param dataCal
     * @param groupBy
     * @return
     */
//    @GET
//    @Produces("application/xml")
//    @Path("/getwindowsusagereportswithgrouping/rest/{rest}/reportid/{reportid}/groupid/{groupid}{deviceid:(/deviceid/[^/]+?)?}/fromdate/{fromdate}/todate/{todate}/datacalculation/{datacalculation}/groupby/{groupby}")
//    @RESTDoc(methodName = "GetWindowsUsageReportsByGroupsWithGrouping", desc = "Get the Usage Reports without Authentication Token", req_Params = {"ReportID;UUID", "GroupID;UUID", "FromDate;String", "ToDate;String", "DataCalculation;String", "GroupBy;String"}, opt_Params = {"DeviceID;UUID"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
//    public TingcoMeasurementTypes getXml_WindowsUsageReportsWithGrouping(@PathParam("reportid") String reportId, @PathParam("groupid") String groupId, @PathParam("deviceid") String deviceId,
//            @PathParam("fromdate") String fromDate, @PathParam("todate") String toDate, @PathParam("datacalculation") String dataCal, @PathParam("groupby") String groupBy) {
//        if (deviceId.equals("")) {
//            deviceId = null;
//        } else {
//            deviceId = deviceId.split("/")[2];
//        }
//        return getWindowsMeasurementDataWithGrouping(reportId, groupId, deviceId, fromDate, toDate, dataCal, groupBy);
//    }
//
//    /**
//     * GetWindowsUsageReportsByGroupsWithGrouping
//     * @param reportId
//     * @param groupId
//     * @param deviceId
//     * @param fromDate
//     * @param toDate
//     * @param dataCal
//     * @param groupBy
//     * @return
//     */
//    @GET
//    @Produces("application/json")
//    @Path("/getwindowsusagereportswithgrouping/json/{json}/reportid/{reportid}/groupid/{groupid}{deviceid:(/deviceid/[^/]+?)?}/fromdate/{fromdate}/todate/{todate}/datacalculation/{datacalculation}/groupby/{groupby}")
//    public TingcoMeasurementTypes getJson_WindowsUsageReportsWithGrouping(@PathParam("reportid") String reportId, @PathParam("groupid") String groupId, @PathParam("deviceid") String deviceId,
//            @PathParam("fromdate") String fromDate, @PathParam("todate") String toDate, @PathParam("datacalculation") String dataCal, @PathParam("groupby") String groupBy) {
//        if (deviceId.equals("")) {
//            deviceId = null;
//        } else {
//            deviceId = deviceId.split("/")[2];
//        }
//        return getWindowsMeasurementDataWithGrouping(reportId, groupId, deviceId, fromDate, toDate, dataCal, groupBy);
//    }
    /**
     * GetWindowsUsageReportsByGroupsWithoutGrouping
     * @param reportId
     * @param groupId
     * @param deviceId
     * @param fromDate
     * @param toDate
     * @param dataCal
     * @return
     */
//    @GET
//    @Produces("application/xml")
//    @Path("/getwindowsusagereportswithoutgrouping/rest/{rest}/reportid/{reportid}/groupid/{groupid}{deviceid:(/deviceid/[^/]+?)?}/fromdate/{fromdate}/todate/{todate}/datacalculation/{datacalculation}")
//    @RESTDoc(methodName = "GetWindowsUsageReportsByGroupsWithoutGrouping", desc = "Get the Usage Reports without Authentication Token", req_Params = {"ReportID;UUID", "GroupID;UUID", "FromDate;String", "ToDate;String", "DataCalculation;String"}, opt_Params = {"DeviceID;UUID"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
//    public TingcoMeasurementTypes getXml_WindowsUsageReportsWithoutGrouping(@PathParam("reportid") String reportId, @PathParam("groupid") String groupId, @PathParam("deviceid") String deviceId,
//            @PathParam("fromdate") String fromDate, @PathParam("todate") String toDate, @PathParam("datacalculation") String dataCal) {
//        if (deviceId.equals("")) {
//            deviceId = null;
//        } else {
//            deviceId = deviceId.split("/")[2];
//        }
//        return getWindowsMeasurementDataWithoutGrouping(reportId, groupId, deviceId, fromDate, toDate, dataCal);
//    }
//
//    /**
//     * GetWindowsUsageReportsByGroupsWithoutGrouping
//     * @param reportId
//     * @param groupId
//     * @param deviceId
//     * @param fromDate
//     * @param toDate
//     * @param dataCal
//     * @return
//     */
//    @GET
//    @Produces("application/json")
//    @Path("/getwindowsusagereportswithoutgrouping/json/{json}/reportid/{reportid}/groupid/{groupid}{deviceid:(/deviceid/[^/]+?)?}/fromdate/{fromdate}/todate/{todate}/datacalculation/{datacalculation}")
//    public TingcoMeasurementTypes getJson_WindowsUsageReportsWithoutGrouping(@PathParam("reportid") String reportId, @PathParam("groupid") String groupId, @PathParam("deviceid") String deviceId,
//            @PathParam("fromdate") String fromDate, @PathParam("todate") String toDate, @PathParam("datacalculation") String dataCal) {
//        if (deviceId.equals("")) {
//            deviceId = null;
//        } else {
//            deviceId = deviceId.split("/")[2];
//        }
//        return getWindowsMeasurementDataWithoutGrouping(reportId, groupId, deviceId, fromDate, toDate, dataCal);
//    }
    /**
     * GetMeasurementDataForDevice
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getmeasurementdatafordevice/rest/{rest}")
    @RESTDoc(methodName = "GetMeasurementDataForDevice", desc = "Used to Get MeasurementDataForDevice", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoMeasurementTypes postXml_getMeasurementDataForDevice(String content) {
        return getMeasurementDataForDevice(content);
    }

    /**
     * GetMeasurementDataForDevice
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getmeasurementdatafordevice/json/{json}")
    public TingcoMeasurementTypes postJson_getMeasurementDataForDevice(String content) {
        return getMeasurementDataForDevice(content);
    }

     /**
     * GetMeasurementDataForDevice
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getmeasurementdatadetailsfordevice/rest/{rest}")
    @RESTDoc(methodName = "GetMeasurementDataForDevice", desc = "Used to Get MeasurementDataForDevice", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoMeasurementTypes postXml_GetMeasurementDataDetailsForDevice(String content) {
        return getMeasurementDataDetailsForDevice(content);
    }

    /**
     * GetMeasurementDataForDevice
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getmeasurementdatadetailsfordevice/json/{json}")
    public TingcoMeasurementTypes postJson_GetMeasurementDataDetailsForDevice(String content) {
        return getMeasurementDataDetailsForDevice(content);
    }


    /**
     * POST method for creating an instance of MeasurementTypessResource
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
    public MeasurementTypesResource getMeasurementTypesResource() {
        return new MeasurementTypesResource();
    }

    /**
     * GetMeasurementTypesForGroup
     * @param groupId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getmeasurementtypesforgroup/rest/{rest}/groupid/{groupid}")
    @RESTDoc(methodName = "GetMeasurementTypesForGroup", desc = "Get MeasurementTypes For Group", req_Params = {"GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoMeasurementTypes getXml_GetMeasurementTypesForGroup(@PathParam("groupid") String groupId) {
        return getMeasurementTypesForGroup(groupId);
    }

    /**
     * GetMeasurementTypesForGroup
     * @param groupId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getmeasurementtypesforgroup/json/{json}/groupid/{groupid}")
    @RESTDoc(methodName = "GetMeasurementTypesForGroup", desc = "Get MeasurementTypes For Group", req_Params = {"GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoMeasurementTypes getJson_GetMeasurementTypesForGroup(@PathParam("groupid") String groupId) {
        return getMeasurementTypesForGroup(groupId);
    }

    /**
     * InsertMeasurementTypeForGroup
     * @param groupId
     * @param measurementTypeId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/insertmeasurementtypeforgroup/rest/{rest}/groupid/{groupid}/measurementtypeid/{measurementtypeid}")
    @RESTDoc(methodName = "InsertMeasurementTypeForGroup", desc = "Insert MeasurementType For Group", req_Params = {"GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoMeasurementTypes getXml_InsertMeasurementTypeForGroup(@PathParam("groupid") String groupId, @PathParam("measurementtypeid") String measurementTypeId) {
        return insertMeasurementTypeForGroup(groupId, measurementTypeId);
    }

    /**
     * InsertMeasurementTypeForGroup
     * @param groupId
     * @param measurementTypeId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/insertmeasurementtypeforgroup/json/{json}/groupid/{groupid}/measurementtypeid/{measurementtypeid}")
    @RESTDoc(methodName = "InsertMeasurementTypeForGroup", desc = "Insert MeasurementType For Group", req_Params = {"GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoMeasurementTypes getJson_InsertMeasurementTypeForGroup(@PathParam("groupid") String groupId, @PathParam("measurementtypeid") String measurementTypeId) {
        return insertMeasurementTypeForGroup(groupId, measurementTypeId);
    }

    @GET
    @Produces("application/xml")
    @Path("/deletemeasurementtypeforgroup/rest/{rest}/groupid/{groupid}/measurementtypeid/{measurementtypeid}")
    @RESTDoc(methodName = "DeleteMeasurementTypeForGroup", desc = "Delete MeasurementType For Group", req_Params = {"GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoMeasurementTypes getXml_DeleteMeasurementTypeForGroup(@PathParam("groupid") String groupId, @PathParam("measurementtypeid") String measurementTypeId) {
        return deleteMeasurementTypeForGroup(groupId, measurementTypeId);
    }

    @GET
    @Produces("application/json")
    @Path("/deletemeasurementtypeforgroup/json/{json}/groupid/{groupid}/measurementtypeid/{measurementtypeid}")
    @RESTDoc(methodName = "DeleteMeasurementTypeForGroup", desc = "Delete MeasurementType For Group", req_Params = {"GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoMeasurementTypes getJson_DeleteMeasurementTypeForGroup(@PathParam("groupid") String groupId, @PathParam("measurementtypeid") String measurementTypeId) {
        return deleteMeasurementTypeForGroup(groupId, measurementTypeId);
    }

    /**
     * GetMeasurementTypesByDevcieTypeID
     * @param deviceTypeId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getmeasurementtypesbydevcietypeid/rest/{rest}/devicetypeid/{devicetypeid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetMeasurementTypesByDevcieTypeID", desc = "GetMeasurementTypesByDevcieTypeID", req_Params = {"devicetypeid;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoMeasurementTypes getXml_GetMeasurementTypesByDevcieTypeID(@PathParam("devicetypeid") String deviceTypeId, @PathParam("countryid") int countryId) {
        return getMeasurementTypesByDevcieTypeID(deviceTypeId, countryId);
    }
    /**
     * GetMeasurementTypesByDevcieTypeID
     * @param deviceTypeId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getmeasurementtypesbydevcietypeid/json/{json}/devicetypeid/{devicetypeid}/countryid/{countryid}")
    public TingcoMeasurementTypes getJson_GetMeasurementTypesByDevcieTypeID(@PathParam("devicetypeid") String deviceTypeId, @PathParam("countryid") int countryId) {
        return getMeasurementTypesByDevcieTypeID(deviceTypeId, countryId);
    }
    /**
     * InsertUpdateMeasurementTypesByDevcieTypeID
     * @param flag
     * @param deviceTypeId
     * @param measurementTypeId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/insertdeletemeasurementtypesbydevcietypeid/rest/{rest}/flag/{flag}/devicetypeid/{devicetypeid}/measurementtypeid/{measurementtypeid}")
    @RESTDoc(methodName = "InsertDeleteMeasurementTypesByDevcieTypeID ", desc = "InsertDeleteMeasurementTypesByDevcieTypeID ", req_Params = {"devicetypeid;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoMeasurementTypes getXml_InsertUpdateMeasurementTypesByDevcieTypeID (@PathParam("flag") String flag, @PathParam("devicetypeid") String deviceTypeId, @PathParam("measurementtypeid") String measurementTypeId) {
        return insertDeleteMeasurementTypesByDevcieTypeID (flag, deviceTypeId, measurementTypeId);
    }
    /**
     * InsertUpdateMeasurementTypesByDevcieTypeID
     * @param flag
     * @param deviceTypeId
     * @param measurementTypeId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/insertdeletemeasurementtypesbydevcietypeid/json/{json}/flag/{flag}/devicetypeid/{devicetypeid}/measurementtypeid/{measurementtypeid}")
    public TingcoMeasurementTypes getJson_InsertUpdateMeasurementTypesByDevcieTypeID (@PathParam("flag") String flag, @PathParam("devicetypeid") String deviceTypeId, @PathParam("measurementtypeid") String measurementTypeId) {
        return insertDeleteMeasurementTypesByDevcieTypeID (flag, deviceTypeId, measurementTypeId);
    }

    private TingcoMeasurementTypes getMeasurementDataDetailsForDevice(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoMeasurementTypes tingcoMeasurement = null;
        Session session = null;
        Session ismSession = null;
        TingcoMeasurementXML tingcoMeasurementXml = new TingcoMeasurementXML();
        try {
            tingcoMeasurement = tingcoMeasurementXml.buildTingcoMeasurementTemplate();
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
                    TingcoMeasurementTypes measurementTypes = (TingcoMeasurementTypes) JAXBManager.getInstance().unMarshall(content, TingcoMeasurementTypes.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    ismSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    if (measurementTypes.getMeasurementTypes() != null) {
                        if (!measurementTypes.getMeasurementTypes().getMeasurementTypeTranslations().isEmpty()) {
                            tingcoMeasurement = dao.buildGetMeasurementDataDetailsForDevice(measurementTypes, tingcoMeasurement, session, ismSession);
                        } else {
                            tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                            tingcoMeasurement.getMsgStatus().setResponseText("No MeasurementTypeTranslations found");
                            return tingcoMeasurement;
                        }
                    } else {
                        tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                        tingcoMeasurement.getMsgStatus().setResponseText("No MeasurementTypes found");
                        return tingcoMeasurement;
                    }
                } else {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-10);
                    tingcoMeasurement.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoMeasurement;
                }
            } else {
                tingcoMeasurement.getMsgStatus().setResponseCode(-3);
                tingcoMeasurement.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoMeasurement;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoMeasurement.getMsgStatus().setResponseCode(-1);
            tingcoMeasurement.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            if (ismSession != null) {
                ismSession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoMeasurement;

    }
    
    private TingcoMeasurementTypes insertDeleteMeasurementTypesByDevcieTypeID(String flag, String deviceTypeId, String measurementTypeId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoMeasurementTypes tingcoMeasurement = null;
        TingcoMeasurementXML tingcoMeasurementXML = new TingcoMeasurementXML();
        Session session = null;
        try {
            tingcoMeasurement = tingcoMeasurementXML.buildTingcoMeasurementTemplate();
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
                    Object object = dao.getDeviceTypeMeasurementTypesByID(deviceTypeId, measurementTypeId, session);
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    if(flag.equalsIgnoreCase("INSERT")){
                        if(object == null){
                            DeviceTypeMeasurementTypes dtmt = new DeviceTypeMeasurementTypes(new DeviceTypeMeasurementTypesId(deviceTypeId, measurementTypeId));
                            dtmt.setLastUpdatedByUserId(sessions2.getUserId());
                            dtmt.setCreatedDate(gc.getTime());
                            dtmt.setUpdatedDate(gc.getTime());
                            dao.saveDeviceTypeMeasurementTypes(dtmt, session);
                        } else {
                            tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                            tingcoMeasurement.getMsgStatus().setResponseText("Data Already Exists");
                            return tingcoMeasurement;
                        }
                    }
                    if(flag.equalsIgnoreCase("DELETE")){
                        if(object == null){
                            tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                            tingcoMeasurement.getMsgStatus().setResponseText("No Data Found");
                            return tingcoMeasurement;
                        }else{
                            DeviceTypeMeasurementTypes dtmt = (DeviceTypeMeasurementTypes) object;
                            dao.deleteDeviceTypeMeasurementTypes(dtmt, session);
                        }
                    }
                } else {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-10);
                    tingcoMeasurement.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoMeasurement;
                }
            } else {
                tingcoMeasurement.getMsgStatus().setResponseCode(-3);
                tingcoMeasurement.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoMeasurement;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoMeasurement.getMsgStatus().setResponseCode(-1);
            tingcoMeasurement.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoMeasurement;
    }

    private TingcoMeasurementTypes getMeasurementTypesByDevcieTypeID(String deviceTypeId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoMeasurementTypes tingcoMeasurement = null;
        TingcoMeasurementXML tingcoMeasurementXML = new TingcoMeasurementXML();
        Session session = null;
        try {
            tingcoMeasurement = tingcoMeasurementXML.buildTingcoMeasurementTemplate();
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
                    List<DeviceTypeMeasurementTypes> deviceTypemeasurementTypesList = dao.getDeviceTypeMeasurementTypes(deviceTypeId, session);
                    if (!deviceTypemeasurementTypesList.isEmpty()) {
                        List<String> measurementTypeIdsList = dao.getMeasurementTypeIdsList(deviceTypemeasurementTypesList);
                        List<MeasurementTypeTranslations> meaurementTypeTranslationsList = dao.getMeasurementTypeTranslationsbyIds(measurementTypeIdsList, countryId, session);
                        if (!meaurementTypeTranslationsList.isEmpty()) {
                            tingcoMeasurement = tingcoMeasurementXML.buildTingcoTypeTranslations(tingcoMeasurement, meaurementTypeTranslationsList);
                        } else {
                            tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                            tingcoMeasurement.getMsgStatus().setResponseText("No MeasurementTypeTranslations found");
                            return tingcoMeasurement;
                        }
                    } else {
                        tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                        tingcoMeasurement.getMsgStatus().setResponseText("No Measurementtypes found");
                        return tingcoMeasurement;
                    }
                } else {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-10);
                    tingcoMeasurement.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoMeasurement;
                }
            } else {
                tingcoMeasurement.getMsgStatus().setResponseCode(-3);
                tingcoMeasurement.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoMeasurement;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoMeasurement.getMsgStatus().setResponseCode(-1);
            tingcoMeasurement.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoMeasurement;
    }

    private TingcoMeasurementTypes getMeasurementDataWithGroupingdemo(String reportId, String groupId, String deviceId, String fromDate, String toDate, String dataCal, String groupBy) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoMeasurementTypes tingcoMeasurement = null;
        TingcoMeasurementXML tingcoMeasurementXML = new TingcoMeasurementXML();
        Session session = null;
        Session ismOperSession = null;
        try {
            tingcoMeasurement = tingcoMeasurementXML.buildTingcoMeasurementTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (reportId == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("ReportID should not be null");
                    return tingcoMeasurement;
                }

                if (groupId == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("GroupID should not be null");
                    return tingcoMeasurement;
                }

                if (fromDate == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("FromDate should not be null");
                    return tingcoMeasurement;
                }

                if (toDate == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("ToDate should not be null");
                    return tingcoMeasurement;
                }

                if (dataCal == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("DataCalculation should not be null");
                    return tingcoMeasurement;
                }

                if (groupBy == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("GroupBy should not be null");
                    return tingcoMeasurement;
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
                } else {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-10);
                    tingcoMeasurement.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoMeasurement;
                }
            } else {
                tingcoMeasurement.getMsgStatus().setResponseCode(-3);
                tingcoMeasurement.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoMeasurement;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoMeasurement.getMsgStatus().setResponseCode(-1);
            tingcoMeasurement.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            if (ismOperSession != null) {
                ismOperSession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoMeasurement;
    }

    private TingcoMeasurementTypes getMeasurementDataWithGroupingdemo1(String reportId, String groupId, String deviceId, String fromDate, String toDate, String dataCal, String groupBy) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoMeasurementTypes tingcoMeasurement = null;
        TingcoMeasurementXML tingcoMeasurementXML = new TingcoMeasurementXML();
        Session session = null;
        Session ismOperSession = null;
        try {
            tingcoMeasurement = tingcoMeasurementXML.buildTingcoMeasurementTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (reportId == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("ReportID should not be null");
                    return tingcoMeasurement;
                }

                if (groupId == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("GroupID should not be null");
                    return tingcoMeasurement;
                }

                if (fromDate == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("FromDate should not be null");
                    return tingcoMeasurement;
                }

                if (toDate == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("ToDate should not be null");
                    return tingcoMeasurement;
                }

                if (dataCal == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("DataCalculation should not be null");
                    return tingcoMeasurement;
                }

                if (groupBy == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("GroupBy should not be null");
                    return tingcoMeasurement;
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
                    List<MeasurementData> measurementDataList = dao.getMeasurementDatademo(dataCal, reportId, groupId, fromDate, toDate, deviceId, groupBy, ismOperSession, session);
                    if (!measurementDataList.isEmpty()) {

                        for (MeasurementData m : measurementDataList) {
                            System.out.println(m.getMeasurementValue() + " " + m.getMonth());
                        }

                        String nullString = null + "";
                        if (dataCal.equalsIgnoreCase("sum")) {
                            Map<String, Double> data = new HashMap<String, Double>();

                            for (MeasurementData measurementData : measurementDataList) {
                                if (groupBy.equalsIgnoreCase("year")) {
                                    if (data.containsKey(measurementData.getYear() + "")) {
                                        if (measurementData.getYear() != null) {
                                            data.put(measurementData.getYear() + "", data.get(measurementData.getYear() + "") + measurementData.getMeasurementValue().doubleValue());
                                        } else {
                                            data.put(nullString, data.get(nullString) + measurementData.getMeasurementValue().doubleValue());
                                        }
                                    } else {
                                        if (measurementData.getYear() != null) {
                                            data.put(measurementData.getYear() + "", measurementData.getMeasurementValue().doubleValue());
                                        } else {
                                            data.put(nullString, measurementData.getMeasurementValue().doubleValue());
                                        }
                                    }
                                } else if (groupBy.equalsIgnoreCase("month")) {
                                    if (data.containsKey(measurementData.getMonth().toString())) {
                                        if (measurementData.getMonth() != null) {
                                            System.out.println("othertime " + measurementData.getMonth().toString() + " " + measurementData.getMeasurementValue().doubleValue());
                                            data.put(measurementData.getMonth().toString(), data.get(measurementData.getMonth().toString()) + measurementData.getMeasurementValue().doubleValue());
                                        } else {
                                            data.put(nullString, data.get(nullString) + measurementData.getMeasurementValue().doubleValue());
                                        }

                                    } else {
                                        if (measurementData.getMonth() != null) {
                                            System.out.println("firsttime " + measurementData.getMonth().toString() + " " + measurementData.getMeasurementValue().doubleValue());
                                            data.put(measurementData.getMonth().toString(), measurementData.getMeasurementValue().doubleValue());
                                        } else {
                                            data.put(nullString, measurementData.getMeasurementValue().doubleValue());
                                        }
                                    }
                                } else if (groupBy.equalsIgnoreCase("day")) {
                                    if (data.containsKey(measurementData.getYear() + "," + measurementData.getMonth() + "," + measurementData.getDay())) {
                                        data.put(measurementData.getYear() + "," + measurementData.getMonth() + "," + measurementData.getDay(), data.get(measurementData.getYear() + "," + measurementData.getMonth() + "," + measurementData.getDay()) + measurementData.getMeasurementValue().doubleValue());
                                    } else {
                                        data.put(measurementData.getYear() + "," + measurementData.getMonth() + "," + measurementData.getDay(), measurementData.getMeasurementValue().doubleValue());
                                    }
                                } else if (groupBy.equalsIgnoreCase("hour")) {
                                    if (data.containsKey(measurementData.getHour() + "")) {
                                        if (measurementData.getHour() != null) {
                                            data.put(measurementData.getHour() + "", data.get(measurementData.getHour() + "") + measurementData.getMeasurementValue().doubleValue());
                                        } else {
                                            data.put(nullString, data.get(nullString) + measurementData.getMeasurementValue().doubleValue());
                                        }

                                    } else {
                                        if (measurementData.getHour() != null) {
                                            data.put(measurementData.getHour() + "", measurementData.getMeasurementValue().doubleValue());
                                        } else {
                                            data.put(nullString, measurementData.getMeasurementValue().doubleValue());
                                        }
                                    }
                                }
                            }

                            tingcoMeasurement = tingcoMeasurementXML.buildTingcoMeasurementDataWithGroupBydemo(tingcoMeasurement, data, groupBy);
                        } else {
                            HashMap<String, Double> data = new HashMap<String, Double>();
                            HashMap<String, Integer> lengthCounter = new HashMap<String, Integer>();
                            for (MeasurementData measurementData : measurementDataList) {
                                if (groupBy.equalsIgnoreCase("year")) {
                                    if (data.containsKey(measurementData.getYear() + "")) {
                                        if (measurementData.getYear() != null) {
                                            data.put(measurementData.getYear() + "", data.get(measurementData.getYear() + "") + measurementData.getMeasurementValue().doubleValue());
                                            lengthCounter.put(measurementData.getYear() + "", lengthCounter.get(measurementData.getYear() + "") + 1);
                                        } else {
                                            data.put(nullString, data.get(nullString) + measurementData.getMeasurementValue().doubleValue());
                                            lengthCounter.put(nullString, lengthCounter.get(nullString) + 1);
                                        }

                                    } else {
                                        if (measurementData.getYear() != null) {
                                            data.put(measurementData.getYear() + "", measurementData.getMeasurementValue().doubleValue());
                                            lengthCounter.put(measurementData.getYear() + "", 1);
                                        } else {
                                            data.put(nullString, measurementData.getMeasurementValue().doubleValue());
                                            lengthCounter.put(nullString, 1);
                                        }

                                    }
                                } else if (groupBy.equalsIgnoreCase("month")) {
                                    if (data.containsKey(measurementData.getMonth() + "")) {
                                        if (measurementData.getMonth() != null) {
                                            data.put(measurementData.getMonth() + "", data.get(measurementData.getMonth() + "") + measurementData.getMeasurementValue().doubleValue());
                                            lengthCounter.put(measurementData.getMonth() + "", lengthCounter.get(measurementData.getMonth().toString()) + 1);
                                        } else {
                                            data.put(nullString, data.get(nullString) + measurementData.getMeasurementValue().doubleValue());
                                            lengthCounter.put(nullString, lengthCounter.get(nullString) + 1);
                                        }

                                    } else {
                                        if (measurementData.getMonth() != null) {
                                            data.put(measurementData.getMonth() + "", measurementData.getMeasurementValue().doubleValue());
                                            lengthCounter.put(measurementData.getMonth() + "", 1);
                                        } else {
                                            data.put(nullString, measurementData.getMeasurementValue().doubleValue());
                                            lengthCounter.put(nullString, 1);
                                        }
                                    }


                                } else if (groupBy.equalsIgnoreCase("day")) {
                                    if (data.containsKey(measurementData.getYear() + "," + measurementData.getMonth() + "," + measurementData.getDay())) {
                                        data.put(measurementData.getYear() + "," + measurementData.getMonth() + "," + measurementData.getDay(), data.get(measurementData.getYear() + "," + measurementData.getMonth() + "," + measurementData.getDay()) + measurementData.getMeasurementValue().doubleValue());
                                        lengthCounter.put(measurementData.getYear() + "," + measurementData.getMonth() + "," + measurementData.getDay(), lengthCounter.get(measurementData.getYear() + "," + measurementData.getMonth() + "," + measurementData.getDay()) + 1);
                                    } else {
                                        data.put(measurementData.getYear() + "," + measurementData.getMonth() + "," + measurementData.getDay(), measurementData.getMeasurementValue().doubleValue());
                                        lengthCounter.put(measurementData.getYear() + "," + measurementData.getMonth() + "," + measurementData.getDay(), 1);
                                    }
                                } else if (groupBy.equalsIgnoreCase("hour")) {
                                    if (data.containsKey(measurementData.getHour() + "")) {
                                        if (measurementData.getHour() != null) {
                                            data.put(measurementData.getHour() + "", data.get(measurementData.getHour() + "") + measurementData.getMeasurementValue().doubleValue());
                                            lengthCounter.put(measurementData.getHour() + "", lengthCounter.get(measurementData.getHour() + "") + 1);
                                        } else {
                                            data.put(nullString, data.get(nullString) + measurementData.getMeasurementValue().doubleValue());
                                            lengthCounter.put(nullString, lengthCounter.get(nullString) + 1);
                                        }
                                    } else {
                                        if (measurementData.getHour() != null) {
                                            data.put(measurementData.getHour() + "", measurementData.getMeasurementValue().doubleValue());
                                            lengthCounter.put(measurementData.getHour() + "", 1);
                                        } else {
                                            data.put(nullString, measurementData.getMeasurementValue().doubleValue());
                                            lengthCounter.put(nullString, 1);
                                        }
                                    }
                                }
                            }

                            for (Map.Entry<String, Double> entry : data.entrySet()) {
                                String key = entry.getKey();
                                for (Map.Entry<String, Integer> numberSet : lengthCounter.entrySet()) {
                                    if (entry.getKey() != null && numberSet.getKey() != null) {
                                        if (entry.getKey().equalsIgnoreCase(numberSet.getKey())) {
                                            data.put(entry.getKey(), entry.getValue() / numberSet.getValue());
                                        }
                                    } else if (entry.getKey() == null && numberSet.getKey() == null) {
                                        data.put(entry.getKey(), entry.getValue() / numberSet.getValue());
                                    }

                                }
                            }
                            tingcoMeasurement = tingcoMeasurementXML.buildTingcoMeasurementDataWithGroupBydemo(tingcoMeasurement, data, groupBy);
                        }
//                        tingcoMeasurement = tingcoMeasurementXML.buildTingcoMeasurementDataWithGroupBy(tingcoMeasurement, measurementDataList, groupId, session, groupBy);
                    } else {
                        tingcoMeasurement.getMsgStatus().setResponseCode(-10);
                        tingcoMeasurement.getMsgStatus().setResponseText("No Data Found");
                        return tingcoMeasurement;
                    }
                } else {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-10);
                    tingcoMeasurement.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoMeasurement;
                }
            } else {
                tingcoMeasurement.getMsgStatus().setResponseCode(-3);
                tingcoMeasurement.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoMeasurement;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoMeasurement.getMsgStatus().setResponseCode(-1);
            tingcoMeasurement.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            if (ismOperSession != null) {
                ismOperSession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoMeasurement;
    }

    private TingcoMeasurementTypes getMeasurementDataWithGrouping(String reportId, String groupId, String deviceId, String fromDate, String toDate, String dataCal, String groupBy) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoMeasurementTypes tingcoMeasurement = null;
        TingcoMeasurementXML tingcoMeasurementXML = new TingcoMeasurementXML();
        Session session = null;
        Session ismOperSession = null;
        try {
            tingcoMeasurement = tingcoMeasurementXML.buildTingcoMeasurementTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (reportId == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("ReportID should not be null");
                    return tingcoMeasurement;
                }

                if (groupId == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("GroupID should not be null");
                    return tingcoMeasurement;
                }

                if (fromDate == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("FromDate should not be null");
                    return tingcoMeasurement;
                }

                if (toDate == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("ToDate should not be null");
                    return tingcoMeasurement;
                }

                if (dataCal == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("DataCalculation should not be null");
                    return tingcoMeasurement;
                }

                if (groupBy == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("GroupBy should not be null");
                    return tingcoMeasurement;
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
                    List<Object> measurementDataList = dao.getMeasurementData(dataCal, reportId, groupId, fromDate, toDate, deviceId, groupBy, ismOperSession, session);
                    if (!measurementDataList.isEmpty()) {
                        tingcoMeasurement = tingcoMeasurementXML.buildTingcoMeasurementDataWithGroupBy(tingcoMeasurement, measurementDataList, groupId, session, groupBy);
                    } else {
                        tingcoMeasurement.getMsgStatus().setResponseCode(-10);
                        tingcoMeasurement.getMsgStatus().setResponseText("No Data Found");
                        return tingcoMeasurement;
                    }
                } else {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-10);
                    tingcoMeasurement.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoMeasurement;
                }
            } else {
                tingcoMeasurement.getMsgStatus().setResponseCode(-3);
                tingcoMeasurement.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoMeasurement;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoMeasurement.getMsgStatus().setResponseCode(-1);
            tingcoMeasurement.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            if (ismOperSession != null) {
                ismOperSession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoMeasurement;
    }

    private TingcoMeasurementTypes deleteMeasurementTypeForGroup(String groupId, String measurementTypeId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoMeasurementTypes tingcoMeasurement = null;
        TingcoMeasurementXML tingcoMeasurementXML = new TingcoMeasurementXML();
        Session session = null;
        MeasurementTypesInGroups measurementTypesInGroups = null;
        try {
            tingcoMeasurement = tingcoMeasurementXML.buildTingcoMeasurementTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("GroupId should not be null");
                    return tingcoMeasurement;
                }
                if (measurementTypeId == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("MeasurementTypeId should not be null");
                    return tingcoMeasurement;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    measurementTypesInGroups = dao.getMeasurementTypesInGroupsByID(session, groupId, measurementTypeId);
                    if (measurementTypesInGroups == null) {
                        tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                        tingcoMeasurement.getMsgStatus().setResponseText("No Data Found");
                        return tingcoMeasurement;
                    }
                    try {
                        session.beginTransaction();
                        session.delete(measurementTypesInGroups);
                        session.getTransaction().commit();
                    } catch (Exception e) {
                        if (session.getTransaction().wasCommitted()) {
                            session.getTransaction().rollback();
                        }
                        TCMUtil.exceptionLog(getClass().getName(), e);
                        tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                        tingcoMeasurement.getMsgStatus().setResponseText("Error occured");
                        return tingcoMeasurement;
                    }
                } else {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-10);
                    tingcoMeasurement.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoMeasurement;
                }
            } else {
                tingcoMeasurement.getMsgStatus().setResponseCode(-3);
                tingcoMeasurement.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoMeasurement;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoMeasurement.getMsgStatus().setResponseCode(-1);
            tingcoMeasurement.getMsgStatus().setResponseText("Error occured");
            return tingcoMeasurement;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoMeasurement;
    }

    private TingcoMeasurementTypes insertMeasurementTypeForGroup(String groupId, String measurementTypeId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoMeasurementTypes tingcoMeasurement = null;
        TingcoMeasurementXML tingcoMeasurementXML = new TingcoMeasurementXML();
        Session session = null;
        MeasurementTypesInGroups measurementTypesInGroups = null;
        try {
            tingcoMeasurement = tingcoMeasurementXML.buildTingcoMeasurementTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("GroupId should not be null");
                    return tingcoMeasurement;
                }
                if (measurementTypeId == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("MeasurementTypeId should not be null");
                    return tingcoMeasurement;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    measurementTypesInGroups = dao.getMeasurementTypesInGroupsByID(session, groupId, measurementTypeId);
                    if (measurementTypesInGroups != null) {
                        tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                        tingcoMeasurement.getMsgStatus().setResponseText("Data Already Exists");
                        return tingcoMeasurement;
                    }
                    GregorianCalendar gc = new GregorianCalendar();
                    measurementTypesInGroups = new MeasurementTypesInGroups();
                    measurementTypesInGroups.setId(new MeasurementTypesInGroupsId(measurementTypeId, groupId));
                    measurementTypesInGroups.setCreatedDate(gc.getTime());
                    measurementTypesInGroups.setUpdatedDate(gc.getTime());
                    measurementTypesInGroups.setLastUpdatedByUserId(sessions2.getUserId());
                    try {
                        session.beginTransaction();
                        session.save(measurementTypesInGroups);
                        session.getTransaction().commit();
                    } catch (Exception e) {
                        if (session.getTransaction().wasCommitted()) {
                            session.getTransaction().rollback();
                        }
                        TCMUtil.exceptionLog(getClass().getName(), e);
                        tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                        tingcoMeasurement.getMsgStatus().setResponseText("Error occured");
                        return tingcoMeasurement;
                    }
                } else {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-10);
                    tingcoMeasurement.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoMeasurement;
                }
            } else {
                tingcoMeasurement.getMsgStatus().setResponseCode(-3);
                tingcoMeasurement.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoMeasurement;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoMeasurement.getMsgStatus().setResponseCode(-1);
            tingcoMeasurement.getMsgStatus().setResponseText("Error occured");
            return tingcoMeasurement;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoMeasurement;
    }

    private TingcoMeasurementTypes getMeasurementTypesForGroup(String groupId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoMeasurementTypes tingcoMeasurement = null;
        TingcoMeasurementXML tingcoMeasurementXML = new TingcoMeasurementXML();
        Session session = null;
        try {
            tingcoMeasurement = tingcoMeasurementXML.buildTingcoMeasurementTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("GroupId should not be null");
                    return tingcoMeasurement;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<MeasurementTypesInGroups> list = dao.getMeasurementTypesInGroups(groupId, session);
                    if (!list.isEmpty()) {
                        tingcoMeasurement = tingcoMeasurementXML.buildMeasurementTypesForGroup(tingcoMeasurement, list);
                    } else {
                        tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                        tingcoMeasurement.getMsgStatus().setResponseText("Data not Found");
                        return tingcoMeasurement;
                    }
                } else {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-10);
                    tingcoMeasurement.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoMeasurement;
                }
            } else {
                tingcoMeasurement.getMsgStatus().setResponseCode(-3);
                tingcoMeasurement.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoMeasurement;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoMeasurement.getMsgStatus().setResponseCode(-1);
            tingcoMeasurement.getMsgStatus().setResponseText("Error occured");
            return tingcoMeasurement;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoMeasurement;
    }

    private TingcoMeasurementTypes getMeasurementDataForDevice(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoMeasurementTypes tingcoMeasurement = null;
        Session session = null;
        Session ismSession = null;
        TingcoMeasurementXML tingcoMeasurementXml = new TingcoMeasurementXML();
        try {
            tingcoMeasurement = tingcoMeasurementXml.buildTingcoMeasurementTemplate();
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
                    TingcoMeasurementTypes measurementTypes = (TingcoMeasurementTypes) JAXBManager.getInstance().unMarshall(content, TingcoMeasurementTypes.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    ismSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    if (measurementTypes.getMeasurementTypes() != null) {
                        if (!measurementTypes.getMeasurementTypes().getMeasurementTypeTranslations().isEmpty()) {
                            tingcoMeasurement = dao.buildMeasurementGraphforDevice(measurementTypes, tingcoMeasurement, session, ismSession);
                        } else {
                            tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                            tingcoMeasurement.getMsgStatus().setResponseText("No MeasurementTypeTranslations found");
                            return tingcoMeasurement;
                        }
                    } else {
                        tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                        tingcoMeasurement.getMsgStatus().setResponseText("No MeasurementTypes found");
                        return tingcoMeasurement;
                    }
                } else {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-10);
                    tingcoMeasurement.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoMeasurement;
                }
            } else {
                tingcoMeasurement.getMsgStatus().setResponseCode(-3);
                tingcoMeasurement.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoMeasurement;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoMeasurement.getMsgStatus().setResponseCode(-1);
            tingcoMeasurement.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            if (ismSession != null) {
                ismSession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoMeasurement;
    }

    private TingcoMeasurementTypes getMeasurementTypesByDeviceTypeId(String deviceId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoMeasurementTypes tingcoMeasurement = null;
        TingcoMeasurementXML tingcoMeasurementXML = new TingcoMeasurementXML();
        Session session = null;
        try {
            tingcoMeasurement = tingcoMeasurementXML.buildTingcoMeasurementTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceId == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("DeviceId should not be null");
                    return tingcoMeasurement;
                }
                if (countryId <= 0) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("CountryId should not be null");
                    return tingcoMeasurement;
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
                    DeviceDAO deviceDAO = new DeviceDAO();
                    Device device = deviceDAO.getDeviceById(deviceId, session);
                    if (device != null) {
                        List<DeviceTypeMeasurementTypes> deviceTypemeasurementTypesList = dao.getDeviceTypeMeasurementTypes(device.getDeviceTypes().getDeviceTypeId(), session);
                        if (!deviceTypemeasurementTypesList.isEmpty()) {
                            List<String> measurementTypeIdsList = dao.getMeasurementTypeIdsList(deviceTypemeasurementTypesList);
                            List<MeasurementTypeTranslations> meaurementTypeTranslationsList = dao.getMeasurementTypeTranslationsbyIds(measurementTypeIdsList, countryId, session);
                            if (!meaurementTypeTranslationsList.isEmpty()) {
                                tingcoMeasurement = tingcoMeasurementXML.buildTingcoTypeTranslations(tingcoMeasurement, meaurementTypeTranslationsList);
                            } else {
                                tingcoMeasurement.getMsgStatus().setResponseCode(-10);
                                tingcoMeasurement.getMsgStatus().setResponseText("No MeasurementTypeTranslations found");
                                return tingcoMeasurement;
                            }
                        } else {
                            tingcoMeasurement.getMsgStatus().setResponseCode(-10);
                            tingcoMeasurement.getMsgStatus().setResponseText("No Measurementtypes found");
                            return tingcoMeasurement;
                        }
                    } else {
                        tingcoMeasurement.getMsgStatus().setResponseCode(-10);
                        tingcoMeasurement.getMsgStatus().setResponseText("No Device found in the given deviceId");
                        return tingcoMeasurement;
                    }
                } else {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-10);
                    tingcoMeasurement.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoMeasurement;
                }
            } else {
                tingcoMeasurement.getMsgStatus().setResponseCode(-3);
                tingcoMeasurement.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoMeasurement;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoMeasurement.getMsgStatus().setResponseCode(-1);
            tingcoMeasurement.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoMeasurement;
    }

//    private TingcoMeasurementTypes getWindowsMeasurementDataWithGrouping(String reportId, String groupId, String deviceId, String fromDate, String toDate, String dataCal, String groupBy) {
//        long requestedTime = System.currentTimeMillis();
//        boolean hasPermission = true;
//        TingcoMeasurementTypes tingcoMeasurement = null;
//        TingcoMeasurementXML tingcoMeasurementXML = new TingcoMeasurementXML();
//        Session session = null;
//        Session ismOperSession = null;
//        try {
//            tingcoMeasurement = tingcoMeasurementXML.buildTingcoMeasurementTemplate();
//            if (reportId == null) {
//                tingcoMeasurement.getMsgStatus().setResponseCode(-1);
//                tingcoMeasurement.getMsgStatus().setResponseText("ReportID should not be null");
//                return tingcoMeasurement;
//            }
//
//            if (groupId == null) {
//                tingcoMeasurement.getMsgStatus().setResponseCode(-1);
//                tingcoMeasurement.getMsgStatus().setResponseText("GroupID should not be null");
//                return tingcoMeasurement;
//            }
//
//            if (fromDate == null) {
//                tingcoMeasurement.getMsgStatus().setResponseCode(-1);
//                tingcoMeasurement.getMsgStatus().setResponseText("FromDate should not be null");
//                return tingcoMeasurement;
//            }
//
//            if (toDate == null) {
//                tingcoMeasurement.getMsgStatus().setResponseCode(-1);
//                tingcoMeasurement.getMsgStatus().setResponseText("ToDate should not be null");
//                return tingcoMeasurement;
//            }
//
//            if (dataCal == null) {
//                tingcoMeasurement.getMsgStatus().setResponseCode(-1);
//                tingcoMeasurement.getMsgStatus().setResponseText("DataCalculation should not be null");
//                return tingcoMeasurement;
//            }
//
//            if (groupBy == null) {
//                tingcoMeasurement.getMsgStatus().setResponseCode(-1);
//                tingcoMeasurement.getMsgStatus().setResponseText("GroupBy should not be null");
//                return tingcoMeasurement;
//            }
//
//            //        UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
//            //        Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
//            //        if (ht.containsKey(TCMUtil.DEVICE)) {
//            //            ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
//            //            for (int i = 0; i < operations.size(); i++) {
//            //                if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
//            //                    hasPermission = true;
//            //                }
//            //            }
//            //        }
//
//            if (hasPermission) {
//                session = HibernateUtil.getSessionFactory().openSession();
//                ismOperSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
//                List<Object> measurementDataList = dao.getMeasurementData(dataCal, reportId, groupId, fromDate, toDate, deviceId, groupBy, ismOperSession, session);
//                tingcoMeasurement = tingcoMeasurementXML.buildTingcoMeasurementDataWithGroupBy(tingcoMeasurement, measurementDataList, groupId, session, groupBy);
//            } else {
//                tingcoMeasurement.getMsgStatus().setResponseCode(-10);
//                tingcoMeasurement.getMsgStatus().setResponseText("User Permission is not given");
//                return tingcoMeasurement;
//            }
//        } catch (Exception ex) {
//            TCMUtil.exceptionLog(getClass().getName(), ex);
//            tingcoMeasurement.getMsgStatus().setResponseCode(-1);
//            tingcoMeasurement.getMsgStatus().setResponseText("Error occured");
//            return tingcoMeasurement;
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//            if (ismOperSession != null) {
//                ismOperSession.close();
//            }
//            delayLog(requestedTime);
//        }
//        return tingcoMeasurement;
//    }
    private TingcoMeasurementTypes getMeasurementDataWithoutGrouping(String reportId, String groupId, String deviceId, String fromDate, String toDate, String dataCal) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoMeasurementTypes tingcoMeasurement = null;
        TingcoMeasurementXML tingcoMeasurementXML = new TingcoMeasurementXML();
        Session session = null;
        Session ismOperSession = null;
        try {
            tingcoMeasurement = tingcoMeasurementXML.buildTingcoMeasurementTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (reportId == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("ReportID should not be null");
                    return tingcoMeasurement;
                }

                if (groupId == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("GroupID should not be null");
                    return tingcoMeasurement;
                }

                if (fromDate == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("FromDate should not be null");
                    return tingcoMeasurement;
                }

                if (toDate == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("ToDate should not be null");
                    return tingcoMeasurement;
                }

                if (dataCal == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("DataCalculation should not be null");
                    return tingcoMeasurement;
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
                    List<Object> measurementDataList = dao.getMeasurementData(dataCal, reportId, groupId, fromDate, toDate, deviceId, ismOperSession);
                    tingcoMeasurement = tingcoMeasurementXML.buildTingcoMeasurementData(tingcoMeasurement, measurementDataList, groupId, session);
                } else {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-10);
                    tingcoMeasurement.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoMeasurement;
                }
            } else {
                tingcoMeasurement.getMsgStatus().setResponseCode(-3);
                tingcoMeasurement.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoMeasurement;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoMeasurement.getMsgStatus().setResponseCode(-1);
            tingcoMeasurement.getMsgStatus().setResponseText("Error occured");
            return tingcoMeasurement;
        } finally {
            if (session != null) {
                session.close();
            }
            if (ismOperSession != null) {
                ismOperSession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoMeasurement;
    }

//    private TingcoMeasurementTypes getWindowsMeasurementDataWithoutGrouping(String reportId, String groupId, String deviceId, String fromDate, String toDate, String dataCal) {
//        long requestedTime = System.currentTimeMillis();
//        boolean hasPermission = true;
//        TingcoMeasurementTypes tingcoMeasurement = null;
//        TingcoMeasurementXML tingcoMeasurementXML = new TingcoMeasurementXML();
//        Session session = null;
//        Session ismOperSession = null;
//        try {
//            tingcoMeasurement = tingcoMeasurementXML.buildTingcoMeasurementTemplate();
//            if (reportId == null) {
//                tingcoMeasurement.getMsgStatus().setResponseCode(-1);
//                tingcoMeasurement.getMsgStatus().setResponseText("ReportID should not be null");
//                return tingcoMeasurement;
//            }
//
//            if (groupId == null) {
//                tingcoMeasurement.getMsgStatus().setResponseCode(-1);
//                tingcoMeasurement.getMsgStatus().setResponseText("GroupID should not be null");
//                return tingcoMeasurement;
//            }
//
//            if (fromDate == null) {
//                tingcoMeasurement.getMsgStatus().setResponseCode(-1);
//                tingcoMeasurement.getMsgStatus().setResponseText("FromDate should not be null");
//                return tingcoMeasurement;
//            }
//
//            if (toDate == null) {
//                tingcoMeasurement.getMsgStatus().setResponseCode(-1);
//                tingcoMeasurement.getMsgStatus().setResponseText("ToDate should not be null");
//                return tingcoMeasurement;
//            }
//
//            if (dataCal == null) {
//                tingcoMeasurement.getMsgStatus().setResponseCode(-1);
//                tingcoMeasurement.getMsgStatus().setResponseText("DataCalculation should not be null");
//                return tingcoMeasurement;
//            }
//
//
//
//            if (hasPermission) {
//                session = HibernateUtil.getSessionFactory().openSession();
//                ismOperSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
//                List<Object> measurementDataList = dao.getMeasurementData(dataCal, reportId, groupId, fromDate, toDate, deviceId, ismOperSession);
//                tingcoMeasurement = tingcoMeasurementXML.buildTingcoMeasurementData(tingcoMeasurement, measurementDataList, groupId, session);
//            } else {
//                tingcoMeasurement.getMsgStatus().setResponseCode(-10);
//                tingcoMeasurement.getMsgStatus().setResponseText("User Permission is not given");
//                return tingcoMeasurement;
//            }
//        } catch (HibernateException he) {
//            tingcoMeasurement.getMsgStatus().setResponseCode(-1);
//            tingcoMeasurement.getMsgStatus().setResponseText("Database Exception");
//            return tingcoMeasurement;
//        } catch (Exception ex) {
//            TCMUtil.exceptionLog(getClass().getName(), ex);
//            tingcoMeasurement.getMsgStatus().setResponseCode(-1);
//            tingcoMeasurement.getMsgStatus().setResponseText("Error occured");
//            return tingcoMeasurement;
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//            if (ismOperSession != null) {
//                ismOperSession.close();
//            }
//            delayLog(requestedTime);
//        }
//        return tingcoMeasurement;
//    }
    private TingcoMeasurementTypes getMeasurementTypesByGroupId(String groupId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoMeasurementTypes tingcoMeasurement = null;
        TingcoMeasurementXML tingcoMeasurementXML = new TingcoMeasurementXML();
        Session session = null;
        try {
            tingcoMeasurement = tingcoMeasurementXML.buildTingcoMeasurementTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId == null) {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-1);
                    tingcoMeasurement.getMsgStatus().setResponseText("GroupId should not be null");
                    return tingcoMeasurement;
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
                    List<MeasurementTypeTranslations> meaurementTypeTranslationsList = dao.getMeasurementTypeTranslations(groupId, countryId, session);
                    tingcoMeasurement = tingcoMeasurementXML.buildTingcoTypeTranslations(tingcoMeasurement, meaurementTypeTranslationsList);
                } else {
                    tingcoMeasurement.getMsgStatus().setResponseCode(-10);
                    tingcoMeasurement.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoMeasurement;
                }
            } else {
                tingcoMeasurement.getMsgStatus().setResponseCode(-3);
                tingcoMeasurement.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoMeasurement;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoMeasurement.getMsgStatus().setResponseCode(-1);
            tingcoMeasurement.getMsgStatus().setResponseText("Error occured");
            return tingcoMeasurement;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoMeasurement;
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }

    private static Map<String, Double> sortByComparator(Map<String, Double> unsortMap) {

        List list = new LinkedList(unsortMap.entrySet());

        // sort list based on comparator
        Collections.sort(list, new Comparator() {

            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getKey()).compareTo(((Map.Entry) (o2)).getKey());
            }
        });

        // put sorted list into map again
        //LinkedHashMap make sure order in which keys were inserted
        Map sortedMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}

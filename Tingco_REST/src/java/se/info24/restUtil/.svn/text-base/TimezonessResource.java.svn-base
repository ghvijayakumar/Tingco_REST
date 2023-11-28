/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.restUtil;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.datatype.DatatypeConfigurationException;
import org.hibernate.Session;
import se.info24.jaxbUtil.TingcoUtilsXML;
import se.info24.pojo.TimeZones;
import se.info24.user.CountryDAO;
import se.info24.util.HibernateUtil;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;
import se.info24.utiljaxb.TingcoUtils;

/**
 * REST Web Service
 *
 * @author Saptashiri
 */
@Path("/timezones")
public class TimezonessResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoUtilsXML tuXML = new TingcoUtilsXML();
    RestUtilDAO restUtilDao = new RestUtilDAO();
    CountryDAO countryDAO = new CountryDAO();

    /** Creates a new instance of TimezonessResource */
    public TimezonessResource() {
    }

    /**
     * Timezone_Add
     * @param timezoneName
     * @param timeZoneGMTOffset
     * @param timeZoneDescription
     * @param daylightSavingStartTime
     * @param daylightSavingEndTime
     * @param daylightSavingOffset
     * @param daylightSavingStartRule
     * @param daylightSavingStopRule
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/add/rest/{rest}/timezonename/{timezonename}/timezonegmtoffset/{timezonegmtoffset}{timezonedescription:(/timezonedescription/[^/]+?)?}{daylightsavingstarttime:(/daylightsavingstarttime/[^/]+?)?}{daylightsavingendtime:(/daylightsavingendtime/[^/]+?)?}{daylightsavingoffset:(/daylightsavingoffset/[^/]+?)?}{daylightsavingstartrule:(/daylightsavingstartrule/[^/]+?)?}{daylightsavingstoprule:(/daylightsavingstoprule/[^/]+?)?}")
    @RESTDoc(methodName = "Timezone_Add", desc = "Used to create a new Timezone", req_Params = {"TimezoneName;String", "TimeZoneGMTOffset;String"}, opt_Params = {"TimeZoneDescription;String", "DaylightSavingStartTime;String", "DaylightSavingEndTime;String", "DaylightSavingOffset;String", "DaylightSavingStartRule;String", "DaylightSavingStopRule;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_Add(@PathParam("timezonename") String timezoneName, @PathParam("timezonegmtoffset") String timeZoneGMTOffset, @PathParam("timezonedescription") String timeZoneDescription, @PathParam("daylightsavingstarttime") String daylightSavingStartTime, @PathParam("daylightsavingendtime") String daylightSavingEndTime, @PathParam("daylightsavingoffset") String daylightSavingOffset, @PathParam("daylightsavingstartrule") String daylightSavingStartRule, @PathParam("daylightsavingstoprule") String daylightSavingStopRule) {
        return addTimezone(timezoneName, timeZoneGMTOffset, timeZoneDescription, daylightSavingStartTime, daylightSavingEndTime, daylightSavingOffset, daylightSavingStartRule, daylightSavingStopRule);
    }

    /**
     * Timezone_Add
     * @param timezoneName
     * @param timeZoneGMTOffset
     * @param timeZoneDescription
     * @param daylightSavingStartTime
     * @param daylightSavingEndTime
     * @param daylightSavingOffset
     * @param daylightSavingStartRule
     * @param daylightSavingStopRule
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/add/rest/{rest}/timezonename/{timezonename}/timezonegmtoffset/{timezonegmtoffset}{timezonedescription:(/timezonedescription/[^/]+?)?}{daylightsavingstarttime:(/daylightsavingstarttime/[^/]+?)?}{daylightsavingendtime:(/daylightsavingendtime/[^/]+?)?}{daylightsavingoffset:(/daylightsavingoffset/[^/]+?)?}{daylightsavingstartrule:(/daylightsavingstartrule/[^/]+?)?}{daylightsavingstoprule:(/daylightsavingstoprule/[^/]+?)?}")
    public TingcoUtils postXml_Add(@PathParam("timezonename") String timezoneName, @PathParam("timezonegmtoffset") String timeZoneGMTOffset, @PathParam("timezonedescription") String timeZoneDescription, @PathParam("daylightsavingstarttime") String daylightSavingStartTime, @PathParam("daylightsavingendtime") String daylightSavingEndTime, @PathParam("daylightsavingoffset") String daylightSavingOffset, @PathParam("daylightsavingstartrule") String daylightSavingStartRule, @PathParam("daylightsavingstoprule") String daylightSavingStopRule) {
        return addTimezone(timezoneName, timeZoneGMTOffset, timeZoneDescription, daylightSavingStartTime, daylightSavingEndTime, daylightSavingOffset, daylightSavingStartRule, daylightSavingStopRule);
    }

    /**
     * Timezone_Add
     * @param timezoneName
     * @param timeZoneGMTOffset
     * @param timeZoneDescription
     * @param daylightSavingStartTime
     * @param daylightSavingEndTime
     * @param daylightSavingOffset
     * @param daylightSavingStartRule
     * @param daylightSavingStopRule
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/add/json/{json}/timezonename/{timezonename}/timezonegmtoffset/{timezonegmtoffset}{timezonedescription:(/timezonedescription/[^/]+?)?}{daylightsavingstarttime:(/daylightsavingstarttime/[^/]+?)?}{daylightsavingendtime:(/daylightsavingendtime/[^/]+?)?}{daylightsavingoffset:(/daylightsavingoffset/[^/]+?)?}{daylightsavingstartrule:(/daylightsavingstartrule/[^/]+?)?}{daylightsavingstoprule:(/daylightsavingstoprule/[^/]+?)?}")
    public TingcoUtils getJson_Add(@PathParam("timezonename") String timezoneName, @PathParam("timezonegmtoffset") String timeZoneGMTOffset, @PathParam("timezonedescription") String timeZoneDescription, @PathParam("daylightsavingstarttime") String daylightSavingStartTime, @PathParam("daylightsavingendtime") String daylightSavingEndTime, @PathParam("daylightsavingoffset") String daylightSavingOffset, @PathParam("daylightsavingstartrule") String daylightSavingStartRule, @PathParam("daylightsavingstoprule") String daylightSavingStopRule) {
        return addTimezone(timezoneName, timeZoneGMTOffset, timeZoneDescription, daylightSavingStartTime, daylightSavingEndTime, daylightSavingOffset, daylightSavingStartRule, daylightSavingStopRule);
    }

    /**
     * Timezone_Add
     * @param timezoneName
     * @param timeZoneGMTOffset
     * @param timeZoneDescription
     * @param daylightSavingStartTime
     * @param daylightSavingEndTime
     * @param daylightSavingOffset
     * @param daylightSavingStartRule
     * @param daylightSavingStopRule
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/add/json/{json}/timezonename/{timezonename}/timezonegmtoffset/{timezonegmtoffset}{timezonedescription:(/timezonedescription/[^/]+?)?}{daylightsavingstarttime:(/daylightsavingstarttime/[^/]+?)?}{daylightsavingendtime:(/daylightsavingendtime/[^/]+?)?}{daylightsavingoffset:(/daylightsavingoffset/[^/]+?)?}{daylightsavingstartrule:(/daylightsavingstartrule/[^/]+?)?}{daylightsavingstoprule:(/daylightsavingstoprule/[^/]+?)?}")
    public TingcoUtils postJson_Add(@PathParam("timezonename") String timezoneName, @PathParam("timezonegmtoffset") String timeZoneGMTOffset, @PathParam("timezonedescription") String timeZoneDescription, @PathParam("daylightsavingstarttime") String daylightSavingStartTime, @PathParam("daylightsavingendtime") String daylightSavingEndTime, @PathParam("daylightsavingoffset") String daylightSavingOffset, @PathParam("daylightsavingstartrule") String daylightSavingStartRule, @PathParam("daylightsavingstoprule") String daylightSavingStopRule) {
        return addTimezone(timezoneName, timeZoneGMTOffset, timeZoneDescription, daylightSavingStartTime, daylightSavingEndTime, daylightSavingOffset, daylightSavingStartRule, daylightSavingStopRule);
    }

    /**
     * Timezone_Delete
     * @param timezoneID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/timezoneid/{timezoneid}")
    @RESTDoc(methodName = "Timezone_Delete", desc = "To Delete a Timezone", req_Params = {"TimezoneID;String"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_Delete(@PathParam("timezoneid") String timezoneID) {
        return deleteTimezone(timezoneID);
    }

    /**
     * Timezone_Delete
     * @param timezoneID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/timezoneid/{timezoneid}")
    public TingcoUtils postXml_Delete(@PathParam("timezoneid") String timezoneID) {
        return deleteTimezone(timezoneID);
    }

    /**
     * Timezone_Delete
     * @param timezoneID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/delete/json/{json}/timezoneid/{timezoneid}")
    public TingcoUtils getJSON_Delete(@PathParam("timezoneid") String timezoneID) {
        return deleteTimezone(timezoneID);
    }

    /**
     * Timezone_Delete
     * @param timezoneID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/delete/json/{json}/timezoneid/{timezoneid}")
    public TingcoUtils postJSON_Delete(@PathParam("timezoneid") String timezoneID) {
        return deleteTimezone(timezoneID);
    }

    /**
     * Timezone_GetInfo
     * @param timezoneID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/get/rest/{rest}/timezoneid/{timezoneid}")
    @RESTDoc(methodName = "Timezone_GetInfo", desc = "To Get a Timezone", req_Params = {"TimezoneID;String"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml(@PathParam("timezoneid") String timezoneID) {
        return getTimezone(timezoneID);
    }

    /**
     * Timezone_GetInfo
     * @param timezoneID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/get/rest/{rest}/timezoneid/{timezoneid}")
    public TingcoUtils postXml(@PathParam("timezoneid") String timezoneID) {
        return getTimezone(timezoneID);
    }

    /**
     * Timezone_GetInfo
     * @param timezoneID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/get/json/{json}/timezoneid/{timezoneid}")
    public TingcoUtils getJson(@PathParam("timezoneid") String timezoneID) {
        return getTimezone(timezoneID);
    }

    /**
     * Timezone_GetInfo
     * @param timezoneID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/get/json/{json}/timezoneid/{timezoneid}")
    public TingcoUtils postJson(@PathParam("timezoneid") String timezoneID) {
        return getTimezone(timezoneID);
    }

    /**
     * WindowsTimezone_GetInfo
     * @param timezoneID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getwindows/rest/{rest}/timezoneid/{timezoneid}")
    @RESTDoc(methodName = "WindowsTimezone_GetInfo", desc = "To Get a Timezone Without AuthenticationToken", req_Params = {"TimezoneID;String"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_Windows(@PathParam("timezoneid") String timezoneID) {
        return getWindowsTimezone(timezoneID);
    }

    /**
     * WindowsTimezone_GetInfo
     * @param timezoneID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getwindows/json/{json}/timezoneid/{timezoneid}")
    public TingcoUtils getJson_Windows(@PathParam("timezoneid") String timezoneID) {
        return getWindowsTimezone(timezoneID);
    }

    /**
     * Timezone_Update
     * @param timezoneID
     * @param timezoneName
     * @param timeZoneGMTOffset
     * @param timeZoneDescription
     * @param daylightSavingStartTime
     * @param daylightSavingEndTime
     * @param daylightSavingOffset
     * @param daylightSavingStartRule
     * @param daylightSavingStopRule
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/update/rest/{rest}/timezoneid/{timezoneid}{timezonename:(/timezonename/[^/]+?)?}{timezonegmtoffset:(/timezonegmtoffset/[^/]+?)?}{timezonedescription:(/timezonedescription/[^/]+?)?}{daylightsavingstarttime:(/daylightsavingstarttime/[^/]+?)?}{daylightsavingendtime:(/daylightsavingendtime/[^/]+?)?}{daylightsavingoffset:(/daylightsavingoffset/[^/]+?)?}{daylightsavingstartrule:(/daylightsavingstartrule/[^/]+?)?}{daylightsavingstoprule:(/daylightsavingstoprule/[^/]+?)?}")
    @RESTDoc(methodName = "Timezone_Update", desc = "Used to update Timezone", req_Params = {"TimezoneID;String"}, opt_Params = {"TimezoneName;String", "TimeZoneGMTOffset;String", "TimeZoneDescription;String", "DaylightSavingStartTime;String", "DaylightSavingEndTime;String", "DaylightSavingOffset;String", "DaylightSavingStartRule;String", "DaylightSavingStopRule;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_Update(@PathParam("timezoneid") String timezoneID, @PathParam("timezonename") String timezoneName, @PathParam("timezonegmtoffset") String timeZoneGMTOffset, @PathParam("timezonedescription") String timeZoneDescription, @PathParam("daylightsavingstarttime") String daylightSavingStartTime, @PathParam("daylightsavingendtime") String daylightSavingEndTime, @PathParam("daylightsavingoffset") String daylightSavingOffset, @PathParam("daylightsavingstartrule") String daylightSavingStartRule, @PathParam("daylightsavingstoprule") String daylightSavingStopRule) {
        return updateTimezone(timezoneID, timezoneName, timeZoneGMTOffset, timeZoneDescription, daylightSavingStartTime, daylightSavingEndTime, daylightSavingOffset, daylightSavingStartRule, daylightSavingStopRule);
    }

    /**
     * Timezone_Update
     * @param timezoneID
     * @param timezoneName
     * @param timeZoneGMTOffset
     * @param timeZoneDescription
     * @param daylightSavingStartTime
     * @param daylightSavingEndTime
     * @param daylightSavingOffset
     * @param daylightSavingStartRule
     * @param daylightSavingStopRule
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/update/rest/{rest}/timezoneid/{timezoneid}{timezonename:(/timezonename/[^/]+?)?}{timezonegmtoffset:(/timezonegmtoffset/[^/]+?)?}{timezonedescription:(/timezonedescription/[^/]+?)?}{daylightsavingstarttime:(/daylightsavingstarttime/[^/]+?)?}{daylightsavingendtime:(/daylightsavingendtime/[^/]+?)?}{daylightsavingoffset:(/daylightsavingoffset/[^/]+?)?}{daylightsavingstartrule:(/daylightsavingstartrule/[^/]+?)?}{daylightsavingstoprule:(/daylightsavingstoprule/[^/]+?)?}")
    public TingcoUtils postXml_Update(@PathParam("timezoneid") String timezoneID, @PathParam("timezonename") String timezoneName, @PathParam("timezonegmtoffset") String timeZoneGMTOffset, @PathParam("timezonedescription") String timeZoneDescription, @PathParam("daylightsavingstarttime") String daylightSavingStartTime, @PathParam("daylightsavingendtime") String daylightSavingEndTime, @PathParam("daylightsavingoffset") String daylightSavingOffset, @PathParam("daylightsavingstartrule") String daylightSavingStartRule, @PathParam("daylightsavingstoprule") String daylightSavingStopRule) {
        return updateTimezone(timezoneID, timezoneName, timeZoneGMTOffset, timeZoneDescription, daylightSavingStartTime, daylightSavingEndTime, daylightSavingOffset, daylightSavingStartRule, daylightSavingStopRule);
    }

    /**
     * Timezone_Update
     * @param timezoneID
     * @param timezoneName
     * @param timeZoneGMTOffset
     * @param timeZoneDescription
     * @param daylightSavingStartTime
     * @param daylightSavingEndTime
     * @param daylightSavingOffset
     * @param daylightSavingStartRule
     * @param daylightSavingStopRule
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/update/json/{json}/timezoneid/{timezoneid}{timezonename:(/timezonename/[^/]+?)?}{timezonegmtoffset:(/timezonegmtoffset/[^/]+?)?}{timezonedescription:(/timezonedescription/[^/]+?)?}{daylightsavingstarttime:(/daylightsavingstarttime/[^/]+?)?}{daylightsavingendtime:(/daylightsavingendtime/[^/]+?)?}{daylightsavingoffset:(/daylightsavingoffset/[^/]+?)?}{daylightsavingstartrule:(/daylightsavingstartrule/[^/]+?)?}{daylightsavingstoprule:(/daylightsavingstoprule/[^/]+?)?}")
    public TingcoUtils getJSON_Update(@PathParam("timezoneid") String timezoneID, @PathParam("timezonename") String timezoneName, @PathParam("timezonegmtoffset") String timeZoneGMTOffset, @PathParam("timezonedescription") String timeZoneDescription, @PathParam("daylightsavingstarttime") String daylightSavingStartTime, @PathParam("daylightsavingendtime") String daylightSavingEndTime, @PathParam("daylightsavingoffset") String daylightSavingOffset, @PathParam("daylightsavingstartrule") String daylightSavingStartRule, @PathParam("daylightsavingstoprule") String daylightSavingStopRule) {
        return updateTimezone(timezoneID, timezoneName, timeZoneGMTOffset, timeZoneDescription, daylightSavingStartTime, daylightSavingEndTime, daylightSavingOffset, daylightSavingStartRule, daylightSavingStopRule);
    }

    /**
     * Timezone_Update
     * @param timezoneID
     * @param timezoneName
     * @param timeZoneGMTOffset
     * @param timeZoneDescription
     * @param daylightSavingStartTime
     * @param daylightSavingEndTime
     * @param daylightSavingOffset
     * @param daylightSavingStartRule
     * @param daylightSavingStopRule
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/update/json/{json}/timezoneid/{timezoneid}{timezonename:(/timezonename/[^/]+?)?}{timezonegmtoffset:(/timezonegmtoffset/[^/]+?)?}{timezonedescription:(/timezonedescription/[^/]+?)?}{daylightsavingstarttime:(/daylightsavingstarttime/[^/]+?)?}{daylightsavingendtime:(/daylightsavingendtime/[^/]+?)?}{daylightsavingoffset:(/daylightsavingoffset/[^/]+?)?}{daylightsavingstartrule:(/daylightsavingstartrule/[^/]+?)?}{daylightsavingstoprule:(/daylightsavingstoprule/[^/]+?)?}")
    public TingcoUtils postJSON_Update(@PathParam("timezoneid") String timezoneID, @PathParam("timezonename") String timezoneName, @PathParam("timezonegmtoffset") String timeZoneGMTOffset, @PathParam("timezonedescription") String timeZoneDescription, @PathParam("daylightsavingstarttime") String daylightSavingStartTime, @PathParam("daylightsavingendtime") String daylightSavingEndTime, @PathParam("daylightsavingoffset") String daylightSavingOffset, @PathParam("daylightsavingstartrule") String daylightSavingStartRule, @PathParam("daylightsavingstoprule") String daylightSavingStopRule) {
        return updateTimezone(timezoneID, timezoneName, timeZoneGMTOffset, timeZoneDescription, daylightSavingStartTime, daylightSavingEndTime, daylightSavingOffset, daylightSavingStartRule, daylightSavingStopRule);
    }

    /**
     * GetAllTimezones
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/rest/{rest}")
    @RESTDoc(methodName = "GetAllTimezones", desc = "Get All Timezones", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_All() {
        return getAllTimezones();
    }

    /**
     * GetAllTimezones
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/json/{json}")
    public TingcoUtils getJson_All() {
        return getAllTimezones();
    }

    /**
     * UpdateUserTimeZoneByUserID
     * @param UserID
     * @param TimeZoneID
     * @param UseDaylightSaving
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/updateusertimezones/rest/{rest}/userid/{userid}/timezoneid/{timezoneid}/usedaylightsaving/{usedaylightsaving}")
    @RESTDoc(methodName = "UserTimezones_Update", desc = "Used to update UserTimezones", req_Params = {"UserID;string", "TimeZoneID;string", "UseDaylightSaving;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUtils getXml_UpdateUserTimeZones(@PathParam("userid") String userID, @PathParam("timezoneid") String timezoneID, @PathParam("usedaylightsaving") String useDayLightSaving) {
        return updateUserTimeZones(userID, timezoneID, useDayLightSaving);
    }

    /**
     * UpdateUserTimeZoneByUserID
     * @param UserID
     * @param TimeZoneID
     * @param UseDaylightSaving
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/updateusertimezones/json/{json}/userid/{userid}/timezoneid/{timezoneid}/usedaylightsaving/{usedaylightsaving}")
    public TingcoUtils getJson_UpdateUserTimeZones(@PathParam("userid") String userID, @PathParam("timezoneid") String timezoneID, @PathParam("usedaylightsaving") String useDayLightSaving) {
        return updateUserTimeZones(userID, timezoneID, useDayLightSaving);
    }

    /**
     * GetAllTimezones
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/rest/{rest}")
    public TingcoUtils postXml_All() {
        return getAllTimezones();
    }

    /**
     * GetAllTimezones
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/json/{json}")
    public TingcoUtils postJson_All() {
        return getAllTimezones();
    }

    private TingcoUtils getAllTimezones() {
        TingcoUtils tingcoUtils = null;
        Session session = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                session = HibernateUtil.getSessionFactory().openSession();
                tingcoUtils = tuXML.buildAllTimeZones(tingcoUtils, countryDAO.getAllTimezones(session));
            } else {
                tingcoUtils.getMsgStatus().setResponseCode(-3);
                tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
            }
            return tingcoUtils;
        } catch (DatatypeConfigurationException dce) {
            TCMUtil.exceptionLog(getClass().getName(), dce);
            tingcoUtils.getMsgStatus().setResponseCode(-1);
            tingcoUtils.getMsgStatus().setResponseText("Error Occured");
            return tingcoUtils;
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    private TingcoUtils getWindowsTimezone(String timezoneID) {
        TingcoUtils tingcoUtil = null;
        Session session = null;
        try {
            tingcoUtil = tuXML.buildTingcoUtilsTemplate();
            if (timezoneID == null) {
                tingcoUtil.getMsgStatus().setResponseCode(-1);
                tingcoUtil.getMsgStatus().setResponseText("TimezoneID should not be empty");
                return tingcoUtil;
            }
            session = HibernateUtil.getSessionFactory().openSession();
            TimeZones timezone = restUtilDao.getTimezoneById(timezoneID, session);
            if (timezone != null) {
                ArrayList<TimeZones> timezoneList = new ArrayList<TimeZones>();
                timezoneList.add(timezone);
                tingcoUtil = tuXML.buildAllTimeZones(tingcoUtil, timezoneList);
            } else {
                tingcoUtil.getMsgStatus().setResponseCode(-1);
                tingcoUtil.getMsgStatus().setResponseText("No Timezone Found with Given TimezoneID");
            }
            return tingcoUtil;
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUtil.getMsgStatus().setResponseCode(-1);
            tingcoUtil.getMsgStatus().setResponseText("Error Ocuured");
            return tingcoUtil;
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    private TingcoUtils updateTimezone(String timezoneID, String timezoneName, String timeZoneGMTOffset, String timeZoneDescription, String daylightSavingStartTime, String daylightSavingEndTime, String daylightSavingOffset, String daylightSavingStartRule, String daylightSavingStopRule) {
        TingcoUtils tingcoUtils = null;
        Session session = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (timezoneID == null) {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("TimezoneID is should not be empty");
                    return tingcoUtils;
                }
                if (timezoneName.equals("")) {
                    timezoneName = null;
                } else {
                    timezoneName = timezoneName.split("/")[2];
                }
                if (timeZoneGMTOffset.equals("")) {
                    timeZoneGMTOffset = null;
                } else {
                    timeZoneGMTOffset = timeZoneGMTOffset.split("/")[2];
                }

                if (timezoneName != null && timezoneName.equalsIgnoreCase("null")) {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("TimezoneName can not be null");
                    return tingcoUtils;
                }

                if (timeZoneGMTOffset != null && timeZoneGMTOffset.equalsIgnoreCase("null")) {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("TimeZoneGMTOffset  should not be null");
                    return tingcoUtils;
                }

                if (timeZoneDescription.equals("")) {
                    timeZoneDescription = null;
                } else {
                    timeZoneDescription = timeZoneDescription.split("/")[2];
                }

                if (daylightSavingStartTime.equals("")) {
                    daylightSavingStartTime = null;
                } else {
                    daylightSavingStartTime = daylightSavingStartTime.split("/")[2];
                }

                if (daylightSavingEndTime.equals("")) {
                    daylightSavingEndTime = null;
                } else {
                    daylightSavingEndTime = daylightSavingEndTime.split("/")[2];
                }

                if (daylightSavingOffset.equals("")) {
                    daylightSavingOffset = null;
                } else {
                    daylightSavingOffset = daylightSavingOffset.split("/")[2];
                }

                if (daylightSavingStartRule.equals("")) {
                    daylightSavingStartRule = null;
                } else {
                    daylightSavingStartRule = daylightSavingStartRule.split("/")[2];
                }

                if (daylightSavingStopRule.equals("")) {
                    daylightSavingStopRule = null;
                } else {
                    daylightSavingStopRule = daylightSavingStopRule.split("/")[2];
                }

                if (timezoneName == null && timeZoneGMTOffset == null && timeZoneDescription == null && daylightSavingStartTime == null && daylightSavingEndTime == null && daylightSavingOffset == null && daylightSavingStartRule == null && daylightSavingStopRule == null) {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("Atleast One Value Should Be Present");
                    return tingcoUtils;
                }

                session = HibernateUtil.getSessionFactory().openSession();
                TimeZones timeZone = restUtilDao.getTimezoneById(timezoneID, session);

                if (timeZone != null) {

                    if (timezoneName != null) {
                        timeZone.setTimeZoneName(timezoneName);
                    }
                    if (timeZoneGMTOffset != null) {
                        timeZone.setTimeZoneGmtoffset(timeZoneGMTOffset);
                    }

                    if (timeZoneDescription != null) {
                        timeZone.setTimeZoneDescription(timeZoneDescription);
                    }
                    if (daylightSavingStartTime != null) {
                        timeZone.setDaylightSavingStartTime(daylightSavingStartTime);
                    }
                    if (daylightSavingEndTime != null) {
                        timeZone.setDaylightSavingEndTime(daylightSavingEndTime);
                    }
                    if (daylightSavingOffset != null) {
                        timeZone.setDaylightSavingOffset(daylightSavingOffset);
                    }
                    if (daylightSavingStartRule != null) {
                        timeZone.setDaylightSavingStartRule(daylightSavingStartRule);
                    }
                    if (daylightSavingStopRule != null) {
                        timeZone.setDaylightSavingStopRule(daylightSavingStopRule);
                    }
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    timeZone.setUpdatedDate(gc.getTime());
                    try {
                        if (restUtilDao.saveTimezone(timeZone, session)) {
                            return tingcoUtils;
                        } else {
                            tingcoUtils.getMsgStatus().setResponseCode(-1);
                            tingcoUtils.getMsgStatus().setResponseText("Error Occured while Updating TimeZone");
                            return tingcoUtils;
                        }
                    } finally {
                        
                    }
                } else {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("TimezoneID Not Found");
                    return tingcoUtils;
                }

            } else {
                tingcoUtils.getMsgStatus().setResponseCode(-3);
                tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUtils;
            }
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUtils.getMsgStatus().setResponseCode(-1);
            tingcoUtils.getMsgStatus().setResponseText("Error Occured while Updating TimeZone");
            return tingcoUtils;
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    private TingcoUtils getTimezone(String timezoneID) {
        TingcoUtils tingcoUtil = null;
        Session session = null;
        try {
            tingcoUtil = tuXML.buildTingcoUtilsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (timezoneID == null) {
                    tingcoUtil.getMsgStatus().setResponseCode(-1);
                    tingcoUtil.getMsgStatus().setResponseText("TimezoneID should not be empty");
                    return tingcoUtil;
                }
                session = HibernateUtil.getSessionFactory().openSession();
                TimeZones timezone = restUtilDao.getTimezoneById(timezoneID, session);
                if (timezone != null) {
                    ArrayList<TimeZones> timezoneList = new ArrayList<TimeZones>();
                    timezoneList.add(timezone);
                    tingcoUtil = tuXML.buildAllTimeZones(tingcoUtil, timezoneList);
                } else {
                    tingcoUtil.getMsgStatus().setResponseCode(-1);
                    tingcoUtil.getMsgStatus().setResponseText("No Timezone Found with Given TimezoneID");
                }
                return tingcoUtil;
            } else {
                tingcoUtil.getMsgStatus().setResponseCode(-3);
                tingcoUtil.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUtil;
            }
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUtil.getMsgStatus().setResponseCode(-1);
            tingcoUtil.getMsgStatus().setResponseText("Error Occured ");
            return tingcoUtil;
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    private TingcoUtils deleteTimezone(String timezoneID) {
        TingcoUtils tingcoUtils = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        if (request.getAttribute("USERSESSION") != null) {
            if (timezoneID == null) {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("TimezoneID should not be empty");
                return tingcoUtils;
            }

            Session session = HibernateUtil.getSessionFactory().openSession();
            TimeZones timezone = restUtilDao.getTimezoneById(timezoneID, session);

            if (timezone != null) {
                try {
                    if (restUtilDao.removeTimeZone(timezone, session)) {
                        return tingcoUtils;
                    } else {
                        tingcoUtils.getMsgStatus().setResponseCode(-1);
                        tingcoUtils.getMsgStatus().setResponseText("Error Occured while Deleting TimeZone");
                        return tingcoUtils;
                    }
                } finally {
                    session.close();
                }
            } else {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("TimezoneID Not Found");
                return tingcoUtils;
            }
        } else {
            tingcoUtils.getMsgStatus().setResponseCode(-3);
            tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
            return tingcoUtils;
        }
    }

    private TingcoUtils addTimezone(String timezoneName, String timeZoneGMTOffset, String timeZoneDescription, String daylightSavingStartTime, String daylightSavingEndTime, String daylightSavingOffset, String daylightSavingStartRule, String daylightSavingStopRule) {
        TingcoUtils tingcoUtils = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }

        if (request.getAttribute("USERSESSION") != null) {
            if (timezoneName == null) {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("TimezoneName is should not be empty");
                return tingcoUtils;
            } else if (timeZoneGMTOffset == null) {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("TimeZoneGMTOffset is should not be empty");
                return tingcoUtils;
            }
            String timeZoneId = UUID.randomUUID().toString();
            TimeZones timeZone = new TimeZones();
            timeZone.setTimeZoneId(timeZoneId);
            timeZone.setTimeZoneName(timezoneName);
            timeZone.setTimeZoneGmtoffset(timeZoneGMTOffset);
            timeZone.setTimeZoneDescription(timeZoneDescription);
            timeZone.setDaylightSavingStartTime(daylightSavingStartTime);
            timeZone.setDaylightSavingEndTime(daylightSavingEndTime);
            timeZone.setDaylightSavingOffset(daylightSavingOffset);
            timeZone.setDaylightSavingStartRule(daylightSavingStartRule);
            timeZone.setDaylightSavingStopRule(daylightSavingStopRule);

            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
            timeZone.setCreatedDate(gc.getTime());
            timeZone.setUpdatedDate(gc.getTime());
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                if (restUtilDao.saveTimezone(timeZone, session)) {
                    return tingcoUtils;
                } else {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("Error Occured while Saving Timezone");
                    return tingcoUtils;
                }
            } finally {
                session.close();
            }
        } else {
            tingcoUtils.getMsgStatus().setResponseCode(-3);
            tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
            return tingcoUtils;
        }
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public TimezonesResource getTimezone_AddResource() {
        return new TimezonesResource();
    }

    private TingcoUtils updateUserTimeZones(String userID, String timezoneID, String useDayLightSaving) {
        TingcoUtils tingcoUtils = null;
        Session session = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (userID.equals("")) {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("UseID should not be empty");
                    return tingcoUtils;
                }
                if (timezoneID.equals("")) {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("TimeZoneID should not be empty");
                    return tingcoUtils;
                }
                if (useDayLightSaving.equals("")) {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("UseDayLightSaving should not be empty");
                    return tingcoUtils;
                }
                session = HibernateUtil.getSessionFactory().openSession();
                if (!restUtilDao.updateUserTimeZones(userID, timezoneID, useDayLightSaving, session)) {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("Error Occured while Updating");
                    return tingcoUtils;
                }
                return tingcoUtils;
            } else {
                tingcoUtils.getMsgStatus().setResponseCode(-3);
                tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUtils;
            }
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUtils.getMsgStatus().setResponseCode(-1);
            tingcoUtils.getMsgStatus().setResponseText("Error Occured while Updating");
            return tingcoUtils;
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }
}

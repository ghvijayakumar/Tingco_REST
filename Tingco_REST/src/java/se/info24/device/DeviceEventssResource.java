/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.device;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.datatype.DatatypeConfigurationException;
import org.hibernate.Session;
import se.info24.devicejaxb.TingcoDevice;
import se.info24.group.GroupDAO;
import se.info24.ismOperationsPojo.EventDetails;
import se.info24.ismOperationsPojo.EventLog;
import se.info24.ismOperationsPojo.ObjectUsageRecords;
import se.info24.jaxbUtil.TingcoDeviceXML;
import se.info24.pojo.Connectors;
import se.info24.pojo.ContactGroups;
import se.info24.pojo.Device;
import se.info24.pojo.EventActionSchedules;
import se.info24.pojo.EventItemActions;
import se.info24.pojo.EventItemActionsId;
import se.info24.pojo.EventItems;
import se.info24.pojo.EventTypeTranslations;
import se.info24.pojo.EventTypesInGroups;
import se.info24.pojo.EventTypesInGroupsId;
import se.info24.pojo.EventTypesInTypes;
import se.info24.pojo.EventTypesInTypesId;
import se.info24.pojo.GroupTrusts;
import se.info24.pojo.Schedules;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.UserTimeZones2;
import se.info24.pojo.Users2;
import se.info24.reports.ReportDAO;
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
@Path("/deviceevents")
public class DeviceEventssResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
    DeviceDAO deviceDAO = new DeviceDAO();
    GroupDAO groupDAO = new GroupDAO();
    ReportDAO reportDAO = new ReportDAO();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of DeviceEventssResource */
    public DeviceEventssResource() {
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public DeviceEventsResource getDeviceEventsResource() {
        return new DeviceEventsResource();
    }

    /**
     * Retrieves representation of an instance of se.info24.device.DeviceEventssResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * GetDeviceEventLog
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getdeviceeventlog/rest/{rest}")
    @RESTDoc(methodName = "GetDeviceEventLog", desc = "Used to Get Device EventLog", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_getDeviceEventLog(String content) throws DatatypeConfigurationException {
        return getDeviceEventLog(content);
    }

    /**
     * GetDeviceEventLog
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getdeviceeventlog/json/{json}")
    public TingcoDevice postJson_getDeviceEventLog(String content) throws DatatypeConfigurationException {
        return getDeviceEventLog(content);
    }

    /**
     * GetDeviceEventLogInformation
     * @param eventId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getdeviceeventloginformation/rest/{rest}/eventid/{eventid}")
    @RESTDoc(methodName = "GetDeviceEventLogInformation", desc = "Used to Get Device EventLogInformation", req_Params = {"EventID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_getDeviceEventLogInformation(@PathParam("eventid") String eventId) {
        return getDeviceEventLogInformation(eventId);
    }

    /**
     * GetDeviceEventLogInformation
     * @param eventId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getdeviceeventloginformation/json/{json}/eventid/{eventid}")
    public TingcoDevice getJson_getDeviceEventLogInformation(@PathParam("eventid") String eventId) {
        return getDeviceEventLogInformation(eventId);
    }

    /**
     * InsertEventItems
     * @param deviceID
     * @param eventTypeID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/xml")
    @Path("/add/rest/{rest}/deviceid/{deviceid}/eventtypeid/{eventtypeid}")
    @RESTDoc(methodName = "InsertEventItems", desc = "Used to InsertEventItems", req_Params = {"DeviceID;string,EventTypeID;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoDevice getXml_Add(@PathParam("deviceid") String deviceID, @PathParam("eventtypeid") String eventTypeID) {
        return addEventItems(deviceID, eventTypeID);
    }

    /**
     * InsertEventItems
     * @param deviceID
     * @param eventTypeID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/json")
    @Path("/add/json/{json}/deviceid/{deviceid}/eventtypeid/{eventtypeid}")
    public TingcoDevice getJson_Add(@PathParam("deviceid") String deviceID, @PathParam("eventtypeid") String eventTypeID) {
        return addEventItems(deviceID, eventTypeID);
    }

    /**
     * InsertEventItems
     * @param deviceID
     * @param eventTypeID
     * @return TingcoDevice
     */
    @POST
    @Produces("application/xml")
    @Path("/add/rest/{rest}/deviceid/{deviceid}/eventtypeid/{eventtypeid}")
    public TingcoDevice postXml_Add(@PathParam("deviceid") String deviceID, @PathParam("eventtypeid") String eventTypeID) {
        return addEventItems(deviceID, eventTypeID);
    }

    /**
     * InsertEventItems
     * @param deviceID
     * @param eventTypeID
     * @return TingcoDevice
     */
    @POST
    @Produces("application/json")
    @Path("/add/json/{json}/deviceid/{deviceid}/eventtypeid/{eventtypeid}")
    public TingcoDevice postJson_Add(@PathParam("deviceid") String deviceID, @PathParam("eventtypeid") String eventTypeID) {
        return addEventItems(deviceID, eventTypeID);
    }

    /**
     * GetNotification
     * @param countryID
     * @param groupID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/xml")
    @Path("/getnotification/rest/{rest}/countryid/{countryid}/groupid/{groupid}")
    @RESTDoc(methodName = "GetNotification", desc = "Used to Get Notification", req_Params = {"CountryID;int", "GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoDevice getXml_Notification(@PathParam("countryid") int countryID, @PathParam("groupid") String groupID) {
        return getNotification(countryID, groupID);
    }

    /**
     * GetNotification
     * @param countryID
     * @param groupID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/json")
    @Path("/getnotification/json/{json}/countryid/{countryid}/groupid/{groupid}")
    public TingcoDevice getJson_Notification(@PathParam("countryid") int countryID, @PathParam("groupid") String groupID) {
        return getNotification(countryID, groupID);
    }

    /**
     * GetNotification
     * @param countryID
     * @param groupID
     * @return TingcoDevice
     */
    @POST
    @Produces("application/xml")
    @Path("/getnotification/rest/{rest}/countryid/{countryid}/groupid/{groupid}")
    public TingcoDevice postXml_Notification(@PathParam("countryid") int countryID, @PathParam("groupid") String groupID) {
        return getNotification(countryID, groupID);
    }

    /**
     * GetNotification
     * @param countryID
     * @param groupID
     * @return TingcoDevice
     */
    @POST
    @Produces("application/json")
    @Path("/getnotification/json/{json}/countryid/{countryid}/groupid/{groupid}")
    public TingcoDevice postJson_Notification(@PathParam("countryid") int countryID, @PathParam("groupid") String groupID) {
        return getNotification(countryID, groupID);
    }

    /**
     *  GetSchedules
     * @param countryID
     * @param groupID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/xml")
    @Path("/getschedules/rest/{rest}/countryid/{countryid}/groupid/{groupid}")
    @RESTDoc(methodName = "GetSchedules", desc = "Used to Get Schedules", req_Params = {"CountryID;int", "GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoDevice getXml_Schedules(@PathParam("countryid") int countryID, @PathParam("groupid") String groupID) {
        return getSchedules(countryID, groupID);
    }

    /**
     *  GetSchedules
     * @param countryID
     * @param groupID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/json")
    @Path("/getschedules/json/{json}/countryid/{countryid}/groupid/{groupid}")
    public TingcoDevice getJson_Schedules(@PathParam("countryid") int countryID, @PathParam("groupid") String groupID) {
        return getSchedules(countryID, groupID);
    }

    /**
     *  GetSchedules
     * @param countryID
     * @param groupID
     * @return TingcoDevice
     */
    @POST
    @Produces("application/xml")
    @Path("/getschedules/rest/{rest}/countryid/{countryid}/groupid/{groupid}")
    public TingcoDevice postXml_Schedules(@PathParam("countryid") int countryID, @PathParam("groupid") String groupID) {
        return getSchedules(countryID, groupID);
    }

    /**
     *  GetSchedules
     * @param countryID
     * @param groupID
     * @return TingcoDevice
     */
    @POST
    @Produces("application/json")
    @Path("/getschedules/json/{json}/countryid/{countryid}/groupid/{groupid}")
    public TingcoDevice postJson_Schedules(@PathParam("countryid") int countryID, @PathParam("groupid") String groupID) {
        return getSchedules(countryID, groupID);
    }

    /**
     * GetDeviceEventTriggers
     * @param deviceID
     * @param countryID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/xml")
    @Path("/getdeviceeventtriggers/rest/{rest}/deviceid/{deviceid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetDeviceLevelDetails", desc = "Used to get Device Level Details", req_Params = {"CountryID;int"}, opt_Params = {"GroupID;UUID", "ObjectGroupID;UUID", "DeviceID;UUID", "ProductVariantID;UUID", "FiilLevel;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_DeviceEventTriggers(@PathParam("deviceid") String deviceID, @PathParam("countryid") int countryID) {
        return getDeviceEventTriggers(deviceID, countryID);
    }

    /**
     * GetDeviceEventTriggers
     * @param deviceID
     * @param countryID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/json")
    @Path("/getdeviceeventtriggers/json/{json}/deviceid/{deviceid}/countryid/{countryid}")
    public TingcoDevice getJson_DeviceEventTriggers(@PathParam("deviceid") String deviceID, @PathParam("countryid") int countryID) {
        return getDeviceEventTriggers(deviceID, countryID);
    }

    /**
     * GetDeviceEventTriggers
     * @param deviceID
     * @param countryID
     * @return TingcoDevice
     */
    @POST
    @Produces("application/xml")
    @Path("/getdeviceeventtriggers/rest/{rest}/deviceid/{deviceid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetDeviceLevelDetails", desc = "Used to get Device Level Details", req_Params = {"CountryID;int"}, opt_Params = {"GroupID;UUID", "ObjectGroupID;UUID", "DeviceID;UUID", "ProductVariantID;UUID", "FiilLevel;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice postXml_DeviceEventTriggers(@PathParam("deviceid") String deviceID, @PathParam("countryid") int countryID) {
        return getDeviceEventTriggers(deviceID, countryID);
    }

    /**
     * GetDeviceEventTriggers
     * @param deviceID
     * @param countryID
     * @return TingcoDevice
     */
    @POST
    @Produces("application/json")
    @Path("/getdeviceeventtriggers/json/{json}/deviceid/{deviceid}/countryid/{countryid}")
    public TingcoDevice postJson_DeviceEventTriggers(@PathParam("deviceid") String deviceID, @PathParam("countryid") int countryID) {
        return getDeviceEventTriggers(deviceID, countryID);
    }

    /**
     * GetEventTypesForEventLog
     * @param groupId
     * @param deviceId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/geteventtypesforeventlog/rest/{rest}/groupid/{groupid}/deviceid/{deviceid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetEventTypesForEventLog", desc = "Used To Get EventTypes For EventLog", req_Params = {"GroupID;UUID", "DeviceID;UUID", "CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_getEventTypesForEventLog(@PathParam("groupid") String groupId, @PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId) {
        return getEventTypesForEventLog(groupId, deviceId, countryId);
    }

    /**
     * GetEventTypesForEventLog
     * @param groupId
     * @param deviceId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/geteventtypesforeventlog/json/{json}/groupid/{groupid}/deviceid/{deviceid}/countryid/{countryid}")
    public TingcoDevice getJson_getEventTypesForEventLog(@PathParam("groupid") String groupId, @PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId) {
        return getEventTypesForEventLog(groupId, deviceId, countryId);
    }

    /**
     * GetGroupEventTypes
     * @param groupId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getgroupeventtypes/rest/{rest}/groupid/{groupid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetGroupEventTypes", desc = "Used To Get GroupEventTypes", req_Params = {"GroupID;UUID", "CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_getGroupEventTypes(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getGroupEventTypes(groupId, countryId);
    }

    /**
     * GetGroupEventTypes
     * @param groupId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getgroupeventtypes/json/{json}/groupid/{groupid}/countryid/{countryid}")
    public TingcoDevice getJson_getGroupEventTypes(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getGroupEventTypes(groupId, countryId);
    }

    /**
     *
     * @param isEnabled
     * @param eventItemId
     * @return xml
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/updateeventcondition/rest/{rest}/isenabled/{isenabled}/eventitemid/{eventitemid}")
    @RESTDoc(methodName = "GetDeviceLevelDetails", desc = "Used to get Device Level Details", req_Params = {"CountryID;int"}, opt_Params = {"GroupID;UUID", "ObjectGroupID;UUID", "DeviceID;UUID", "ProductVariantID;UUID", "FiilLevel;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_updateEventCondition(@PathParam("isenabled") String isEnabled, @PathParam("eventitemid") String eventItemId) throws DatatypeConfigurationException {
        return updateEventCondition(isEnabled, eventItemId);
    }

    /**
     *
     * @param isEnabled
     * @param eventItemId
     * @return json
     */
    @GET
    @Produces("application/json")
    @Path("/updateeventcondition/rest/{rest}/isenabled/{isenabled}/eventitemid/{eventitemid}")
    public TingcoDevice getJson_updateEventCondition(@PathParam("isenabled") String isEnabled, @PathParam("eventitemid") String eventItemId) {
        return updateEventCondition(isEnabled, eventItemId);
    }

    /**
     *
     * @param eventItemId
     * @return xml
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/deleteeventcondition/rest/{rest}/eventitemid/{eventitemid}")
    @RESTDoc(methodName = "GetDeviceLevelDetails", desc = "Used to get Device Level Details", req_Params = {"CountryID;int"}, opt_Params = {"GroupID;UUID", "ObjectGroupID;UUID", "DeviceID;UUID", "ProductVariantID;UUID", "FiilLevel;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_deleteEventCondition(@PathParam("eventitemid") String eventItemId) throws DatatypeConfigurationException {
        return deleteEventCondition(eventItemId);
    }

    /**
     *
     * @param eventItemId
     * @return json
     */
    @GET
    @Produces("application/json")
    @Path("/deleteeventcondition/rest/{rest}/eventitemid/{eventitemid}")
    public TingcoDevice getJson_deleteEventCondition(@PathParam("eventitemid") String eventItemId) {
        return deleteEventCondition(eventItemId);
    }

    /**
     *
     * @param eventItemId
     * @return xml
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/updateeventnotification/rest/{rest}/eventactionscheduleid/{eventactionscheduleid}/contactgroupid/{contactgroupid}/scheduleid/{scheduleid}")
    @RESTDoc(methodName = "GetDeviceLevelDetails", desc = "Used to get Device Level Details", req_Params = {"CountryID;int"}, opt_Params = {"GroupID;UUID", "ObjectGroupID;UUID", "DeviceID;UUID", "ProductVariantID;UUID", "FiilLevel;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_updateEventNotification(@PathParam("eventactionscheduleid") String eventActionScheduleId, @PathParam("contactgroupid") String contactGroupId, @PathParam("scheduleid") String scheduleId) throws DatatypeConfigurationException {
        return updateEventNotification(eventActionScheduleId, contactGroupId, scheduleId);
    }

    /**
     *
     * @param eventItemId
     * @return json
     */
    @GET
    @Produces("application/json")
    @Path("/updateeventnotification/rest/{rest}/eventactionscheduleid/{eventactionscheduleid}/contactgroupid/{contactgroupid}/scheduleid/{scheduleid}")
    public TingcoDevice getJson_updateEventNotification(@PathParam("eventactionscheduleid") String eventActionScheduleId, @PathParam("contactgroupid") String contactGroupId, @PathParam("scheduleid") String scheduleId) {
        return updateEventNotification(eventActionScheduleId, contactGroupId, scheduleId);
    }

    /**
     *
     * @param eventItemId
     * @return xml
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/deleteeventnotification/rest/{rest}/eventactionscheduleid/{eventactionscheduleid}")
    @RESTDoc(methodName = "GetDeviceLevelDetails", desc = "Used to get Device Level Details", req_Params = {"CountryID;int"}, opt_Params = {"GroupID;UUID", "ObjectGroupID;UUID", "DeviceID;UUID", "ProductVariantID;UUID", "FiilLevel;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_deleteEventNotification(@PathParam("eventactionscheduleid") String eventActionScheduleId) throws DatatypeConfigurationException {
        return deleteEventNotification(eventActionScheduleId);
    }

    /**
     *
     * @param eventItemId
     * @return json
     */
    @GET
    @Produces("application/json")
    @Path("/deleteeventnotification/rest/{rest}/eventactionscheduleid/{eventactionscheduleid}")
    public TingcoDevice getJson_deleteEventNotification(@PathParam("eventactionscheduleid") String eventActionScheduleId) {
        return deleteEventNotification(eventActionScheduleId);
    }

    /**
     *
     * @param eventItemId
     * @return xml
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/addeventnotification/rest/{rest}/groupid/{groupid}/contactgroupid/{contactgroupid}/contactgroupname/{contactgroupname}{contactgroupdescription:(/contactgroupdescription/[^/]+?)?}/scheduleid/{scheduleid}/eventitemid/{eventitemid}")
    @RESTDoc(methodName = "GetDeviceLevelDetails", desc = "Used to get Device Level Details", req_Params = {"CountryID;int"}, opt_Params = {"GroupID;UUID", "ObjectGroupID;UUID", "DeviceID;UUID", "ProductVariantID;UUID", "FiilLevel;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_addEventNotification(@PathParam("groupid") String groupId, @PathParam("contactgroupid") String contactGroupId, @PathParam("contactgroupname") String contactGroupname, @PathParam("contactgroupdescription") String contactGroupDescription, @PathParam("scheduleid") String scheduleId, @PathParam("eventitemid") String eventItemId) throws DatatypeConfigurationException {
        return addEventNotification(groupId, contactGroupId, contactGroupname, contactGroupDescription, scheduleId, eventItemId);
    }

    /**
     *
     * @param eventItemId
     * @return json
     */
    @GET
    @Produces("application/json")
    @Path("/addeventnotification/json/{json}/groupid/{groupid}/contactgroupid/{contactgroupid}/contactgroupname/{contactgroupname}/contactgroupdescription/{contactgroupdescription}/scheduleid/{scheduleid}/eventitemid/{eventitemid}")
    public TingcoDevice getJson_addEventNotification(@PathParam("groupid") String groupId, @PathParam("contactgroupid") String contactGroupId, @PathParam("contactgroupname") String contactGroupname, @PathParam("contactgroupdescription") String contactGroupDescription, @PathParam("scheduleid") String scheduleId, @PathParam("eventitemid") String eventItemId) {
        return addEventNotification(groupId, contactGroupId, contactGroupname, contactGroupDescription, scheduleId, eventItemId);
    }

    /**
     * GetEventDetailsForUsageLog
     * @param deviceId
     * @param usageRecordId
     * @param countryId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/geteventdetailsforusagelog/rest/{rest}/deviceid/{deviceid}/usagerecordid/{usagerecordid}/countryid/{countryid}")
    public TingcoDevice getXml_GetEventDetailsForUsageLog(@PathParam("deviceid") String deviceId, @PathParam("usagerecordid") String usageRecordId, @PathParam("countryid") String countryId) throws DatatypeConfigurationException {
        return getEventDetailsForUsageLog(deviceId, usageRecordId, countryId);
    }

    /**
     * GetEventDetailsForUsageLog
     * @param deviceId
     * @param usageRecordId
     * @param countryId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/geteventdetailsforusagelog/json/{json}/deviceid/{deviceid}/usagerecordid/{usagerecordid}/countryid/{countryid}")
    public TingcoDevice getJson_GetEventDetailsForUsageLog(@PathParam("deviceid") String deviceId, @PathParam("usagerecordid") String usageRecordId, @PathParam("countryid") String countryId) throws DatatypeConfigurationException {
        return getEventDetailsForUsageLog(deviceId, usageRecordId, countryId);
    }

    /**
     * InsertEventTypeForGroup
     * @param groupID
     * @param eventTypeID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/xml")
    @Path("/inserteventtypeforgroup/rest/{rest}/groupid/{groupid}/eventtypeid/{eventtypeid}")
    public TingcoDevice getXml_InsertEventTypeForGroup(@PathParam("groupid") String groupID, @PathParam("eventtypeid") String eventTypeID) {
        return insertEventTypeForGroup(groupID, eventTypeID);
    }

    /**
     * InsertEventTypeForGroup
     * @param groupID
     * @param eventTypeID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/json")
    @Path("/inserteventtypeforgroup/json/{json}/groupid/{groupid}/eventtypeid/{eventtypeid}")
    public TingcoDevice getJson_InsertEventTypeForGroup(@PathParam("groupid") String groupID, @PathParam("eventtypeid") String eventTypeID) {
        return insertEventTypeForGroup(groupID, eventTypeID);
    }

    /**
     * DeleteEventTypeForGroup
     * @param groupID
     * @param eventTypeID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/xml")
    @Path("/deleteeventtypeforgroup/rest/{rest}/groupid/{groupid}/eventtypeid/{eventtypeid}")
    public TingcoDevice getXml_DeleteEventTypeForGroup(@PathParam("groupid") String groupID, @PathParam("eventtypeid") String eventTypeID) {
        return deleteEventTypeForGroup(groupID, eventTypeID);
    }

    /**
     * DeleteEventTypeForGroup
     * @param groupID
     * @param eventTypeID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/json")
    @Path("/deleteeventtypeforgroup/json/{json}/groupid/{groupid}/eventtypeid/{eventtypeid}")
    public TingcoDevice getJson_DeleteEventTypeForGroup(@PathParam("groupid") String groupID, @PathParam("eventtypeid") String eventTypeID) {
        return deleteEventTypeForGroup(groupID, eventTypeID);
    }

    /**
     * GetEventTypes
     * @param countryID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/xml")
    @Path("/geteventtypes/rest/{rest}/groupid/{groupid}/countryid/{countryid}")
    public TingcoDevice getXml_GetEventTypes(@PathParam("countryid") String countryID, @PathParam("groupid") String groupId) {
        return getEventTypes(countryID, groupId);
    }

    /**
     * GetEventTypes
     * @param countryID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/json")
    @Path("/geteventtypes/json/{json}/groupid/{groupid}/countryid/{countryid}")
    public TingcoDevice getJson_GetEventTypes(@PathParam("countryid") String countryID, @PathParam("groupid") String groupId) {
        return getEventTypes(countryID, groupId);
    }

    /**
     * POST method for creating an instance of DeviceEventssResource
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
     * GetAllEventTypeDetailsByGroupId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getalleventtypedetailsbygroupid/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetAllEventTypeDetailsByGroupId", desc = "Get All EventType Details By GroupId", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetAllEventTypeDetailsByGroupId(@PathParam("countryid") int countryId) {
        return getAllEventTypeDetailsByGroupId(countryId);
    }

    /**
     * GetAllEventTypeDetailsByGroupId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getalleventtypedetailsbygroupid/json/{json}/countryid/{countryid}")
    public TingcoDevice getJson_GetAllEventTypeDetailsByGroupId(@PathParam("countryid") int countryId) {
        return getAllEventTypeDetailsByGroupId(countryId);
    }

    /**
     * GetAllEventTypesByDeviceType
     * @param countryId
     * @param deviceTypeId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getalleventtypesbydevicetype/rest/{rest}/countryid/{countryid}/devicetypeid/{devicetypeid}")
    @RESTDoc(methodName = "GetAllEventTypesByDeviceType", desc = "Get All EventTypes By DeviceType", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetAllEventTypesByDeviceType(@PathParam("countryid") int countryId, @PathParam("devicetypeid") String deviceTypeId) {
        return getAllEventTypesByDeviceType(countryId, deviceTypeId);
    }

    /**
     * GetAllEventTypesByDeviceType
     * @param countryId
     * @param deviceTypeId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getalleventtypesbydevicetype/json/{json}/countryid/{countryid}/devicetypeid/{devicetypeid}")
    public TingcoDevice getJson_GetAllEventTypesByDeviceType(@PathParam("countryid") int countryId, @PathParam("devicetypeid") String deviceTypeId) {
        return getAllEventTypesByDeviceType(countryId, deviceTypeId);
    }

    /**
     * InsertEventTypeByDeviceType
     * @param eventTypeId
     * @param deviceTypeId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/inserteventtypebydevicetype/rest/{rest}/eventtypeid/{eventtypeid}/devicetypeid/{devicetypeid}/targetid/{targetid}")
    @RESTDoc(methodName = "InsertEventTypeByDeviceType", desc = "InsertEventTypeByDeviceType", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_InsertEventTypeByDeviceType(@PathParam("eventtypeid") String eventTypeId, @PathParam("devicetypeid") String deviceTypeId, @PathParam("targetid") String targetId) {
        return insertEventTypeByDeviceType(eventTypeId, deviceTypeId, targetId);
    }

    /**
     * InsertEventTypeByDeviceType
     * @param eventTypeId
     * @param deviceTypeId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/inserteventtypebydevicetype/json/{json}/eventtypeid/{eventtypeid}/devicetypeid/{devicetypeid}/targetid/{targetid}")
    public TingcoDevice getJson_InsertEventTypeByDeviceType(@PathParam("eventtypeid") String eventTypeId, @PathParam("devicetypeid") String deviceTypeId, @PathParam("targetid") String targetId) {
        return insertEventTypeByDeviceType(eventTypeId, deviceTypeId, targetId);
    }

    /**
     * DeleteEventTypeByDeviceType
     * @param eventTypeId
     * @param deviceTypeId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deleteeventtypebydevicetype/rest/{rest}/eventtypeid/{eventtypeid}/devicetypeid/{devicetypeid}")
    @RESTDoc(methodName = "DeleteEventTypeByDeviceType", desc = "Delete EventType By DeviceType", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_DeleteEventTypeByDeviceType(@PathParam("eventtypeid") String eventTypeId, @PathParam("devicetypeid") String deviceTypeId) {
        return deleteEventTypeByDeviceType(eventTypeId, deviceTypeId);
    }

    /**
     * DeleteEventTypeByDeviceType
     * @param eventTypeId
     * @param deviceTypeId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deleteeventtypebydevicetype/json/{json}/eventtypeid/{eventtypeid}/devicetypeid/{devicetypeid}")
    public TingcoDevice getJson_DeleteEventTypeByDeviceType(@PathParam("eventtypeid") String eventTypeId, @PathParam("devicetypeid") String deviceTypeId) {
        return deleteEventTypeByDeviceType(eventTypeId, deviceTypeId);
    }

    private TingcoDevice deleteEventTypeByDeviceType(String eventTypeId, String deviceTypeId) {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                            hasPermission = true;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<EventTypesInTypes> eventTypesInTypeses = deviceDAO.getEventTypesInTypesByEventTypeIDAndItemID(eventTypeId, deviceTypeId, session);
                    if (eventTypesInTypeses.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                        return tingcoDevice;
                    } else {
                        if (!deviceDAO.deleteEventTypesInTypes(eventTypesInTypeses, session)) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error Occured While Delete");
                            return tingcoDevice;
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

    private TingcoDevice insertEventTypeByDeviceType(String eventTypeId, String deviceTypeId, String targetId) {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object object = deviceDAO.getEventTypesInTypesByEventTypeIDAndItemIDAndTargetID(eventTypeId, deviceTypeId, targetId, session);
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    if (object == null) {
                        EventTypesInTypes eventTypesInTypes = new EventTypesInTypes();
                        eventTypesInTypes.setId(new EventTypesInTypesId(eventTypeId, deviceTypeId, targetId));
                        eventTypesInTypes.setUserId(sessions2.getUserId());
                        eventTypesInTypes.setCreatedDate(gc.getTime());
                        eventTypesInTypes.setUpdatedDate(gc.getTime());
                        if (!deviceDAO.saveEventTypesInTypes(eventTypesInTypes, session)) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error Occurred While Insert");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Already Exists");
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

    private TingcoDevice getAllEventTypesByDeviceType(int countryId, String deviceTypeId) {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        Session session = null;
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
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<EventTypesInTypes> eventTypesInTypeses = deviceDAO.getEventTypesInTypesByDeviceTypeID(deviceTypeId, session);
                    if (eventTypesInTypeses.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                        return tingcoDevice;
                    } else {
                        Set<String> eventTypeIds = new HashSet<String>();
                        for (EventTypesInTypes etit : eventTypesInTypeses) {
                            eventTypeIds.add(etit.getId().getEventTypeId());
                        }

                        List<EventTypeTranslations> eventTypeTranslationses = deviceDAO.getEventTypeTranslations(new ArrayList<String>(eventTypeIds), countryId, session);

                        tingcoDevice = tingcoDeviceXML.buildGetAllEventTypeDetailsByGroupId(tingcoDevice, eventTypeTranslationses);
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

    private TingcoDevice getAllEventTypeDetailsByGroupId(int countryId) {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        Session session = null;
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
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserDAO userDAO = new UserDAO();
                    Users2 user = userDAO.getUserById(sessions2.getUserId(), session);
                    List<GroupTrusts> groupTrusts = groupDAO.getGroupTrustByGroupID(user.getGroupId(), session);
                    Set<String> groupIdsList = groupDAO.getGroupIdsList(user.getGroupId(), groupTrusts);
                    List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupIdsList), 2000);
                    List<EventTypesInGroups> eventTypesInGroupses = new ArrayList<EventTypesInGroups>();

                    for (List<String> list : listsplit) {
                        List<EventTypesInGroups> temp = deviceDAO.getEventTypesInGroupsbyGroupIDs(new HashSet<String>(list), session);
                        eventTypesInGroupses.addAll(temp);
                    }

                    if (eventTypesInGroupses.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                        return tingcoDevice;
                    } else {
                        Set<String> eventTypeIds = new HashSet<String>();
                        for (EventTypesInGroups eventTypesInGroups : eventTypesInGroupses) {
                            eventTypeIds.add(eventTypesInGroups.getEventTypes().getEventTypeId());
                        }

                        List<EventTypeTranslations> eventTypeTranslationses = deviceDAO.getEventTypeTranslations(new ArrayList<String>(eventTypeIds), countryId, session);

                        tingcoDevice = tingcoDeviceXML.buildGetAllEventTypeDetailsByGroupId(tingcoDevice, eventTypeTranslationses);
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

    private TingcoDevice getEventTypes(String countryID, String groupId) {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
//                    UserDAO userDAO = new UserDAO();
//                    Users2 users2 = userDAO.getUserById(sessions2.getUserId(), session);
                    List<EventTypesInGroups> eventTypesInGroupsesList = deviceDAO.getEventTypesInGroups(groupId, session);
                    if (eventTypesInGroupsesList.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoDevice;
                    }
                    List<String> eventTypeId = new ArrayList<String>();
                    for (EventTypesInGroups eventTypesInGroups : eventTypesInGroupsesList) {
                        eventTypeId.add(eventTypesInGroups.getEventTypes().getEventTypeId());
                    }
                    List<EventTypeTranslations> eventTypeTranslationses = deviceDAO.getEventTypeTranslations(eventTypeId, Integer.valueOf(countryID), session);
                    return tingcoDeviceXML.buildGetEventTypes(tingcoDevice, eventTypeTranslationses);
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

    private TingcoDevice deleteEventTypeForGroup(String groupID, String eventTypeID) {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                            hasPermission = true;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<EventTypesInGroups> eventTypesInGroupsesList = deviceDAO.getEventTypesInGroupsbyGroupIdandeventTypeId(session, eventTypeID, groupID);
                    if (eventTypesInGroupsesList.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data not Found");
                        return tingcoDevice;
                    }

                    if (!deviceDAO.deleteEventTypeForGroup(session, eventTypesInGroupsesList.get(0))) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Error Occured");
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

    private TingcoDevice insertEventTypeForGroup(String groupID, String eventTypeID) {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<EventTypesInGroups> eventTypesInGroupsesList = deviceDAO.getEventTypesInGroupsbyGroupIdandeventTypeId(session, eventTypeID, groupID);
                    if (!eventTypesInGroupsesList.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Already available");
                        return tingcoDevice;
                    }
                    EventTypesInGroups eventTypesInGroups = new EventTypesInGroups();
                    EventTypesInGroupsId id = new EventTypesInGroupsId();
                    id.setEventTypeId(eventTypeID);
                    id.setGroupId(groupID);
                    eventTypesInGroups.setId(id);
                    eventTypesInGroups.setUserId(sessions2.getUserId());
                    GregorianCalendar gc = new GregorianCalendar();
                    eventTypesInGroups.setUpdatedDate(gc.getTime());
                    eventTypesInGroups.setCreatedDate(gc.getTime());
                    if (!deviceDAO.insertEventTypeForGroup(session, eventTypesInGroups)) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Error Occured");
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

    private TingcoDevice getEventDetailsForUsageLog(String deviceId, String usageRecordId, String countryId) {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        Session session = null;
        Session ismOprSession = null;
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
                    session = HibernateUtil.getSessionFactory().openSession();
                    ismOprSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    Device device = deviceDAO.getDeviceById(deviceId, session);
                    if (device != null) {
                        ObjectUsageRecords objectUsageRecords = reportDAO.getUsageLogDetailsById(ismOprSession, usageRecordId);
                        if (objectUsageRecords != null) {
                            UserDAO userdao = new UserDAO();
                            List<Users2> users = userdao.getUsers2ById(sessions2.getUserId(), session);
                            UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                            if (userTimeZones2 != null) {
                                String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                                Set<String> groupIdsList = new HashSet<String>();
                                groupIdsList.add(users.get(0).getGroupId());
                                List<EventTypesInGroups> eventTypesInGroupses = deviceDAO.getEventTypesInGroupsbyGroupIDs(groupIdsList, session);
                                Set<String> eventTypeIds = new HashSet<String>();
                                if (eventTypesInGroupses.isEmpty()) {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("Data not Found");
                                    return tingcoDevice;
                                }

                                for (EventTypesInGroups etig : eventTypesInGroupses) {
                                    eventTypeIds.add(etig.getId().getEventTypeId());
                                }

                                List<EventTypeTranslations> eventTypeTranslationses = deviceDAO.getEventTypeTranslations(new ArrayList<String>(eventTypeIds), Integer.parseInt(countryId), session);
                                GregorianCalendar gc = new GregorianCalendar();
//                                gc.setTime(objectUsageRecords.getUsageStartTime());
                                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, objectUsageRecords.getUsageStartTime()));
                                gc.add(Calendar.MINUTE, -15);
                                String fromTime = dateFormat.format(gc.getTime());
//                                gc.setTime(objectUsageRecords.getUsageStopTime());
                                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, objectUsageRecords.getUsageStopTime()));
                                gc.add(Calendar.MINUTE, 15);
                                String toTime = dateFormat.format(gc.getTime());
//                                List<EventLog> eventLogs = deviceDAO.getEventLogbyEventTypeIdandEventTime(new ArrayList<String>(eventIds), fromTime, toTime, ismOprSession, 200, deviceId);

                                List<EventLog> eventLogs = deviceDAO.getEventLogbyIdsList(new ArrayList<String>(eventTypeIds), deviceId, null, fromTime, toTime, timeZoneGMToffset, ismOprSession);
                                Set<String> eventIds = new HashSet<String>();
                                for (EventLog eventLog : eventLogs) {
                                    eventIds.add(eventLog.getEventId());
                                }
                                List<EventDetails> eventDetailses = new ArrayList<EventDetails>();
                                if (eventIds.isEmpty()) {
                                    eventDetailses = ismOprSession.getNamedQuery("getDeviceEventLog").setParameterList("eventId", new ArrayList<String>(eventIds)).list();
                                }
                                return tingcoDeviceXML.buildEventDetailsForUsageLog(eventLogs, eventTypeTranslationses, tingcoDevice, timeZoneGMToffset, eventDetailses);
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("TimeZone is not Found");
                                return tingcoDevice;
                            }
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("No ObjectUsageRecords Found");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Device Found");
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
            return tingcoDevice;
        } finally {
            Runtime.getRuntime().gc();
            if (session != null) {
                session.close();
            }
            if (ismOprSession != null) {
                ismOprSession.close();
            }
            delayLog(requestedTime);
        }

    }

    public TingcoDevice addEventItems(String deviceID, String eventTypeID) {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceID == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceID should not be empty");
                    return tingcoDevice;
                }
                if (eventTypeID == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("EventTypeID should not be empty");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<EventItems> ei = deviceDAO.getEventItemsByID(deviceID, eventTypeID, session);
                    if (ei != null) {
                        if (ei.size() > 0) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Data Already Exist");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Error Occured");
                        return tingcoDevice;
                    }
                    UserSessions2 userSessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                    EventItems eventitems = new EventItems();
                    eventitems.setEventItemId(UUID.randomUUID().toString());
                    eventitems.setEventTypeId(eventTypeID);
                    eventitems.setItemId(deviceID);
                    eventitems.setUserId(userSessions2.getUserId());
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    eventitems.setCreatedDate(gc.getTime());
                    eventitems.setUpdatedDate(gc.getTime());
                    eventitems.setIsEnabled(1);
                    if (deviceDAO.saveEventItems(eventitems, session)) {
                        return tingcoDevice;
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Error Occured");
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

    public TingcoDevice getNotification(int countryID, String groupID) {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (countryID <= 0) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("CountryID should not be empty");
                    return tingcoDevice;
                }
                if (groupID == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("GroupID should not be empty");
                    return tingcoDevice;
                }
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
//                List<String> GroupList = new ArrayList<String>();
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
//                    GroupList.add(groupID);
                    List<GroupTrusts> groupTrustList = groupDAO.getGroupTrustByGroupID(groupID, session);
                    Set<String> groupList = groupDAO.getGroupIdsList(groupID, groupTrustList);

//                    for (GroupTrusts groupTrusts : groupTrustList) {
//                        GroupList.add(groupTrusts.getId().getGroupId());
//                    }
                    List<ContactGroups> cgList = new ArrayList<ContactGroups>();
                    List<List<String>> splitList = TCMUtil.splitStringList(new ArrayList<String>(groupList), 2000);
                    for (List<String> list : splitList) {
                        List<ContactGroups> cgListTemp = deviceDAO.getContactGroupByGroupid(new HashSet<String>(list), session);
                        cgList.addAll(cgListTemp);
                    }


                    if (cgList != null) {
                        if (!cgList.isEmpty()) {
                            return tingcoDeviceXML.buildContactGroupByGroup(tingcoDevice, cgList);
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Data is not Found");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Error Occured");
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

    public TingcoDevice getSchedules(int countryID, String groupID) {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (countryID <= 0) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("CountryID should not be empty");
                    return tingcoDevice;
                }
                if (groupID == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("GroupID should not be empty");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                        }
                    }
                }
                List<String> GroupList = new ArrayList<String>();
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    GroupList.add(groupID);
                    List<GroupTrusts> groupTrustList = groupDAO.getGroupTrustByGroupID(groupID, session);
                    for (GroupTrusts groupTrusts : groupTrustList) {
                        GroupList.add(groupTrusts.getId().getGroupId());
                    }
                    List<Schedules> schedules = new ArrayList<Schedules>();
                    List<List<String>> splitList = TCMUtil.splitStringList(GroupList, 2000);
                    for (List<String> list : splitList) {
                        List<Schedules> schedulestemp = deviceDAO.getSchedulesByGroupidList(list, session);
                        schedules.addAll(schedulestemp);
                    }

                    if (schedules != null) {
                        if (!schedules.isEmpty()) {
                            return tingcoDeviceXML.buildSchedulesByGroup(tingcoDevice, schedules);
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Data is not Found");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Error Occured");
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

    public TingcoDevice getDeviceEventTriggers(String deviceID, int countryID) {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (countryID <= 0) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("CountryID should not be empty");
                    return tingcoDevice;
                }
                if (deviceID == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceID should not be empty");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<EventItems> eventItemList = deviceDAO.getEventItemsByItemID(deviceID, session);
                    if (eventItemList != null) {
                        if (!eventItemList.isEmpty()) {
                            List<String> eventTypeID = new ArrayList<String>();
                            List<String> eventItemID = new ArrayList<String>();
                            for (EventItems eventItem : eventItemList) {
                                eventTypeID.add(eventItem.getEventTypeId());
                                eventItemID.add(eventItem.getEventItemId());
                            }
                            if (eventTypeID.size() > 0 && eventItemID.size() > 0) {
                                List<EventTypeTranslations> eTT = deviceDAO.getEventTypeTranslationsbyEventTypeId(eventTypeID, countryID, session);
                                List<EventItemActions> eIA = deviceDAO.getEventItemActionsbyEventItemID(eventItemID, session);
                                eventTypeID.clear();
                                eventItemID.clear();
                                if (eIA != null) {
                                    List<String> eventActionScheduleID = new ArrayList<String>();
                                    for (EventItemActions eventItemActions : eIA) {
                                        eventActionScheduleID.add(eventItemActions.getId().getEventActionScheduleId());
                                    }
                                    List<EventActionSchedules> eAS = new ArrayList<EventActionSchedules>();
                                    List<ContactGroups> cgList = new ArrayList<ContactGroups>();
                                    List<Schedules> schedulesList = new ArrayList<Schedules>();
                                    if (eventActionScheduleID.size() > 0) {
                                        eAS = deviceDAO.getEventActionSchedulesbyID(eventActionScheduleID, session);
                                        eventActionScheduleID.clear();
                                        List<String> contractGroupIdList = new ArrayList<String>();
                                        List<String> ScheduledIDList = new ArrayList<String>();
                                        for (EventActionSchedules eventActionSchedules : eAS) {
                                            contractGroupIdList.add(eventActionSchedules.getContactGroupId());
                                            ScheduledIDList.add(eventActionSchedules.getScheduleId());
                                        }
                                        cgList = deviceDAO.getContactGroupById(contractGroupIdList, session);
                                        schedulesList = deviceDAO.getSchedulesByID(ScheduledIDList, session);
                                    }
                                    return tingcoDeviceXML.buildDeviceEventTrigger(tingcoDevice, eventItemList, eTT, eIA, eAS, cgList, schedulesList);

                                } else {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("Error Occurred");
                                    return tingcoDevice;
                                }
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("Data not Found");
                                return tingcoDevice;
                            }

                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Data not Found");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Error Occurred");
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

    private TingcoDevice getDeviceEventLog(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        Session ismOperationsSession = null;
        TingcoDevice tingcoDevice = null;
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
                    session = HibernateUtil.getSessionFactory().openSession();
                    ismOperationsSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    se.info24.devicejaxbPost.TingcoDevice tingcoDevices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    if (tingcoDevices.getDevices() != null) {
                        if (!tingcoDevices.getDevices().getDevice().isEmpty()) {
                            se.info24.devicejaxbPost.Device device = tingcoDevices.getDevices().getDevice().get(0);
                            List<String> eventTypeIdsList = deviceDAO.getEventTypeIdList(device, session);
                            if (!eventTypeIdsList.isEmpty()) {
                                tingcoDevice = deviceDAO.buildGetDeviceEventLog(device, eventTypeIdsList, tingcoDevice, session, ismOperationsSession);
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Records found");
                                return tingcoDevice;
                            }
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Device not Found in XML");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Devices not Found in XML");
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
            if (ismOperationsSession != null) {
                ismOperationsSession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getDeviceEventLogInformation(String eventId) {
        long requestedTime = System.currentTimeMillis();
        Session operSession = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (eventId == null || eventId.length() == 0) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("EventId should not be empty");
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
                    operSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    List<EventDetails> eventDetailsList = deviceDAO.getEventDetailsById(eventId, operSession);
                    if (!eventDetailsList.isEmpty()) {
                        tingcoDevice = tingcoDeviceXML.buildEventDetails(tingcoDevice, eventDetailsList);
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data found in Event Details");
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
            if (operSession != null) {
                operSession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getEventTypesForEventLog(String groupId, String deviceId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (groupId == null || groupId.length() == 0) {
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("groupId should not empty");
                return tingcoDevice;
            }
            if (deviceId == null || deviceId.length() == 0) {
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("deviceId should not empty");
                return tingcoDevice;
            }
            if (countryId == 0 || countryId < 0) {
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("countryId should not empty");
                return tingcoDevice;
            }
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
                    se.info24.pojo.Device device = deviceDAO.getDeviceById(deviceId, session);
                    List<EventTypeTranslations> eventTrans = deviceDAO.getEventTypesbyCriteria(groupId, device.getDeviceTypes().getDeviceTypeId(), countryId, session);
                    if (!eventTrans.isEmpty()) {
                        tingcoDevice = tingcoDeviceXML.buildEventTypes(tingcoDevice, eventTrans);
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Found");
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

    private TingcoDevice getGroupEventTypes(String groupId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (groupId.equals("")) {
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("groupId should not empty");
                return tingcoDevice;
            }
            if (countryId == 0 || countryId < 0) {
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("countryId should not empty");
                return tingcoDevice;
            }
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
                    List<EventTypeTranslations> eventTrans = deviceDAO.getEventTypeTranslationsbyCriteria(groupId, countryId, session);
                    if (!eventTrans.isEmpty()) {
                        tingcoDevice = tingcoDeviceXML.buildEventTypes(tingcoDevice, eventTrans);
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Found");
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

    public TingcoDevice addEventNotification(String groupId, String contactGroupId, String contactGroupname, String contactGroupDescription, String scheduleId, String eventItemId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session ismSession = null;
        TingcoDevice tingcoDevice = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("groupId  should not be empty");
                    return tingcoDevice;
                }
                if (contactGroupId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("contactGroupId should not be empty");
                    return tingcoDevice;
                }
                if (contactGroupname.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("contactGroupname should not be empty");
                    return tingcoDevice;
                }
                if (!contactGroupDescription.equals("")) {
                    contactGroupDescription = contactGroupDescription.split("/")[2];
                } else {
                    contactGroupDescription = null;
                }
                if (scheduleId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("scheduleId should not be empty");
                    return tingcoDevice;
                }
                if (eventItemId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("eventItemId should not be empty");
                    return tingcoDevice;
                }
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
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    ismSession = HibernateUtil.getSessionFactory().openSession();
                    EventActionSchedules eventActionSchedules = new EventActionSchedules();
                    EventItemActions eventItemActions = new EventItemActions();

                    eventActionSchedules.setEventActionScheduleId(UUID.randomUUID().toString().toUpperCase());
                    eventActionSchedules.setContactGroupId(contactGroupId);
                    eventActionSchedules.setCreatedDate(gc.getTime());
                    eventActionSchedules.setUpdatedDate(gc.getTime());
                    eventActionSchedules.setUserId(sessions2.getUserId());
                    if (contactGroupDescription != null) {
                        eventActionSchedules.setEventActionDescription(contactGroupDescription);
                    }
                    eventActionSchedules.setEventActionName(contactGroupname);
                    eventActionSchedules.setScheduleId(scheduleId);
                    eventActionSchedules.setIsEnabled(1);
                    eventActionSchedules.setGroupId(groupId);

                    eventItemActions.setCreatedDate(gc.getTime());
                    eventItemActions.setId(new EventItemActionsId(eventItemId, eventActionSchedules.getEventActionScheduleId()));
                    eventItemActions.setUpdatedDate(gc.getTime());
                    eventItemActions.setUserId(sessions2.getUserId());

                    if (!deviceDAO.addEventNotification(eventActionSchedules, eventItemActions, ismSession)) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Error Occured While Adding EventNotification");
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
            tingcoDevice.getMsgStatus().setResponseText("Error Occured");
        } finally {
            if (ismSession != null) {
                ismSession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    public TingcoDevice updateEventNotification(String eventActionScheduleId, String contactGroupId, String scheduleId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session ismSession = null;
        TingcoDevice tingcoDevice = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (eventActionScheduleId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("eventActionScheduleId  should not be empty");
                    return tingcoDevice;
                }
                if (contactGroupId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("contactGroupId should not be empty");
                    return tingcoDevice;
                }
                if (scheduleId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("scheduleId should not be empty");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    ismSession = HibernateUtil.getSessionFactory().openSession();
                    EventActionSchedules eventActionSchedules = deviceDAO.getEventActionSchedulesById(ismSession, eventActionScheduleId);
                    if (eventActionSchedules != null) {
                        eventActionSchedules.setContactGroupId(contactGroupId);
                        eventActionSchedules.setScheduleId(scheduleId);
                        eventActionSchedules.setUpdatedDate(gc.getTime());
                        eventActionSchedules.setUserId(sessions2.getUserId());
                        if (!deviceDAO.saveEventActionSchedules(eventActionSchedules, ismSession)) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error occured while updating EventNotification");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Exists for given eventActionScheduleId");
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
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (ismSession != null) {
                ismSession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    public TingcoDevice deleteEventNotification(String eventActionScheduleId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session ismSession = null;
        TingcoDevice tingcoDevice = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (eventActionScheduleId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("eventActionScheduleId should not be empty");
                    return tingcoDevice;
                }
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
                    ismSession = HibernateUtil.getSessionFactory().openSession();
                    EventActionSchedules eventActionSchedules = deviceDAO.getEventActionSchedulesById(ismSession, eventActionScheduleId);
                    List<EventItemActions> itemActions = deviceDAO.getEventItemActionsByScheduleId(ismSession, eventActionScheduleId);
                    if (eventActionSchedules != null) {
                        eventActionSchedules.setEventActionScheduleId(eventActionScheduleId);
                        if (deviceDAO.deleteEventActionSchedules(eventActionSchedules, ismSession)) {
                            if (!deviceDAO.deleteEventItemActions(itemActions, ismSession)) {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("Error occured while deleting EventItemActions");
                                return tingcoDevice;
                            }

                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error occured while deleting EventActionSchedules");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Exists for given eventActionScheduleId");
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
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (ismSession != null) {
                ismSession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    public TingcoDevice updateEventCondition(String isEnabled, String eventItemId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session ismSession = null;
        TingcoDevice tingcoDevice = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (isEnabled.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("isEnabled  should not be empty");
                    return tingcoDevice;
                }
                if (eventItemId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("eventItemId should not be empty");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    ismSession = HibernateUtil.getSessionFactory().openSession();
                    EventItems eventItems = deviceDAO.getEventItemsById(ismSession, eventItemId);
                    if (eventItems != null) {
                        eventItems.setEventItemId(eventItemId);
                        eventItems.setIsEnabled(Integer.parseInt(isEnabled));
                        eventItems.setUpdatedDate(gc.getTime());
                        eventItems.setUserId(sessions2.getUserId());
                        if (!deviceDAO.saveEventCondition(eventItems, ismSession)) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error occured while updating EventCondition");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Exists for given eventitemid");
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
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (ismSession != null) {
                ismSession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    public TingcoDevice deleteEventCondition(String eventItemId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session ismSession = null;
        TingcoDevice tingcoDevice = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (eventItemId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("eventItemId should not be empty");
                    return tingcoDevice;
                }
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
                    ismSession = HibernateUtil.getSessionFactory().openSession();
                    EventItems eventItems = deviceDAO.getEventItemsById(ismSession, eventItemId);
                    List<EventItemActions> itemActions = deviceDAO.getEventItemActionsByEventItemId(ismSession, eventItemId);
                    List<EventActionSchedules> actionSchedules = new ArrayList<EventActionSchedules>();
                    if (!itemActions.isEmpty()) {
                        for (EventItemActions item : itemActions) {
                            EventActionSchedules eas = deviceDAO.getEventActionSchedulesById(ismSession, item.getId().getEventActionScheduleId());
                            actionSchedules.add(eas);
                        }
                    }
                    if (eventItems != null) {
                        if (!deviceDAO.deleteEventCondition(eventItems, actionSchedules, itemActions, ismSession)) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error occured while deleting EventCondition");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Exists for given eventitemid");
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
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (ismSession != null) {
                ismSession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}

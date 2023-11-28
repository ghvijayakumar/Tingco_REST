/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.home;

import java.util.ArrayList;
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
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.application.ApplicationDAO;
import se.info24.device.DeviceDAO;
import se.info24.devicejaxb.TingcoDevice;
import se.info24.group.GroupDAO;
import se.info24.homejaxb.DeviceID;
import se.info24.homejaxb.Room;
import se.info24.homejaxb.TingcoHome;
import se.info24.jaxbUtil.TingcoDeviceXML;
import se.info24.jaxbUtil.TingcoHomeXML;
import se.info24.objectpojo.ServiceStatusDetails;
import se.info24.pojo.Agreements;
import se.info24.pojo.BuildingDevices;
import se.info24.pojo.BuildingDevicesId;
import se.info24.pojo.BuildingTypeTranslations;
import se.info24.pojo.BuildingTypes;
import se.info24.pojo.Buildings;
import se.info24.pojo.Country;
import se.info24.pojo.Device;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupTrusts;
import se.info24.pojo.RoomTypeTranslations;
import se.info24.pojo.RoomTypes;
import se.info24.pojo.Rooms;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.ZoneRooms;
import se.info24.pojo.ZoneRoomsId;
import se.info24.pojo.Zones;
import se.info24.user.CountryDAO;
import se.info24.user.User_LoginsResource;
import se.info24.util.HibernateUtil;
import se.info24.util.JAXBManager;

/**
 * REST Web Service
 *
 * @author Vijayakumar
 */
@Path("/homes")
public class HomesResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoHomeXML tingcoHomeXML = new TingcoHomeXML();
    HomeDAO homeDAO = new HomeDAO();
    GregorianCalendar currentDateTime = new GregorianCalendar(TimeZone.getTimeZone("GMT"));

    /** Creates a new instance of HomesResource */
    public HomesResource() {
    }

    /**
     * GetBuildingDetailsById
     * @param buildingId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getbuildingdetailsbyid/rest/{rest}/buildingid/{buildingid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetBuildingDetailsById", desc = "Used to Get Building Details", req_Params = {""}, opt_Params = {""}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoHome getXml_GetBuildingDetailsById(@PathParam("buildingid") String buildingId, @PathParam("countryid") int countryId) {
        return getBuildingDetailsById(buildingId, countryId);
    }

    /**
     * GetBuildingDetailsById
     * @param buildingId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getbuildingdetailsbyid/json/{json}/buildingid/{buildingid}/countryid/{countryid}")
    public TingcoHome getJson_GetBuildingDetailsById(@PathParam("buildingid") String buildingId, @PathParam("countryid") int countryId) {
        return getBuildingDetailsById(buildingId, countryId);
    }

    /**
     * GetRoomsByBuildingId
     * @param buildingId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getroomsbybuildingid/rest/{rest}/buildingid/{buildingid}")
    @RESTDoc(methodName = "GetRoomsByBuildingId", desc = "Used to Get Rooms", req_Params = {""}, opt_Params = {""}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoHome getXml_getRoomsByBuildingId(@PathParam("buildingid") String buildingId) {
        return getRoomsByBuildingId(buildingId);
    }

    /**
     * GetRoomsByBuildingId
     * @param buildingId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getroomsbybuildingid/json/{json}/buildingid/{buildingid}")
    public TingcoHome getJson_getRoomsByBuildingId(@PathParam("buildingid") String buildingId) {
        return getRoomsByBuildingId(buildingId);
    }

    /**
     * DeleteZoneRooms
     * @param roomId
     * @param zoneId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deletezonerooms/rest/{rest}/roomid/{roomid}/zoneid/{zoneid}")
    public TingcoHome getXML_deleteZoneRooms(@PathParam("roomid") String roomId, @PathParam("zoneid") String zoneId) {
        return deleteZoneRooms(roomId, zoneId);
    }

    /**
     * DeleteZoneRooms
     * @param roomId
     * @param zoneId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deletezonerooms/json/{json}/roomid/{roomid}/zoneid/{zoneid}")
    public TingcoHome getJson_deleteZoneRooms(@PathParam("roomid") String roomId, @PathParam("zoneid") String zoneId) {
        return deleteZoneRooms(roomId, zoneId);
    }

    /**
     * GetBuildingDevicesDetails
     * @param buildingId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getbuildingdevicesdetails/rest/{rest}/buildingid/{buildingid}/groupid/{groupid}/countryid/{countryid}")
    public TingcoHome getXML_getBuildingDevicesDetails(@PathParam("buildingid") String buildingId, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getBuildingDevicesDetails(buildingId, groupId, countryId);
    }

    /**
     * GetBuildingDevicesDetails
     * @param buildingId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getbuildingdevicesdetails/json/{json}/buildingid/{buildingid}/groupid/{groupid}/countryid/{countryid}")
    public TingcoHome getJson_getBuildingDevicesDetails(@PathParam("buildingid") String buildingId, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getBuildingDevicesDetails(buildingId, groupId, countryId);
    }

    /**
     * DeleteBuildingDevices
     * @param buildingId
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deletebuildingdevices/rest/{rest}/buildingid/{buildingid}/deviceid/{deviceid}")
    public TingcoHome getXML_deleteBuildingDevices(@PathParam("buildingid") String buildingId, @PathParam("deviceid") String deviceid) {
        return deleteBuildingDevices(buildingId, deviceid);
    }

    /**
     * DeleteBuildingDevices
     * @param buildingId
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deletebuildingdevices/json/{json}/buildingid/{buildingid}/deviceid/{deviceid}")
    public TingcoHome getJson_deleteBuildingDevices(@PathParam("buildingid") String buildingId, @PathParam("deviceid") String deviceid) {
        return deleteBuildingDevices(buildingId, deviceid);
    }

    /**
     * AddBuildingDevices
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addbuildingdevices/rest/{rest}")
    public TingcoHome postXml_addBuildingDevices(String content) {
        return addBuildingDevices(content);
    }

    /**
     * AddBuildingDevices
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addbuildingdevices/json/{json}")
    public TingcoHome postJson_addBuildingDevices(String content) {
        return addBuildingDevices(content);
    }

    /**
     * AddBuildingDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addbuildingdetails/rest/{rest}")
    public TingcoHome postXml_AddBuildingDetails(String content) {
        return addBuildingDetails(content);
    }

    /**
     * AddBuildingDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addbuildingdetails/json/{json}")
    public TingcoHome postJson_AddBuildingDetails(String content) {
        return addBuildingDetails(content);
    }

    /**
     * UpdateBuildingDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/updatebuildingdetails/rest/{rest}")
    public TingcoHome postXml_UpdateBuildingDetails(String content) {
        return updateBuildingDetails(content);
    }

    /**
     * UpdateBuildingDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/updatebuildingdetails/json/{json}")
    public TingcoHome postJson_UpdateBuildingDetails(String content) {
        return updateBuildingDetails(content);
    }

    /**
     * GetAllRoomTypes
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getallroomtypes/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetAllRoomTypes", desc = "Used to Get All RoomTypes", req_Params = {""}, opt_Params = {""}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoHome getXml_GetAllRoomTypes(@PathParam("countryid") int countryId) {
        return getAllRoomTypes(countryId);
    }

    /**
     * GetAllRoomTypes
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getallroomtypes/json/{json}/countryid/{countryid}")
    public TingcoHome getJson_GetAllRoomTypes(@PathParam("countryid") int countryId) {
        return getAllRoomTypes(countryId);
    }

    /**
     * GetRoomDetailsByRoomId
     * @param roomId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getroomdetailsbyroomid/rest/{rest}/roomid/{roomid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetRoomDetailsByRoomId", desc = "Used to Get RoomDetails By RoomId", req_Params = {"roomId;string", "countryId;string"}, opt_Params = {""}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoHome getXml_getRoomDetailsByRoomId(@PathParam("roomid") String roomId, @PathParam("countryid") int countryId) {
        return getRoomDetailsByRoomId(roomId, countryId);
    }

    /**
     * GetRoomDetailsByRoomId
     * @param roomId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getroomdetailsbyroomid/json/{json}/roomid/{roomid}/countryid/{countryid}")
    public TingcoHome getJson_getRoomDetailsByRoomId(@PathParam("roomid") String roomId, @PathParam("countryid") int countryId) {
        return getRoomDetailsByRoomId(roomId, countryId);
    }

    /**
     * GetAllBuildingTypes
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getallbuildingtypes/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetAllBuildingTypes", desc = "Used to Get All BuildingTypes", req_Params = {"countryid;int"}, opt_Params = {""}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoHome getXML_getAllBuildingTypes(@PathParam("countryid") int countryId) {
        return getAllBuildingTypes(countryId);

    }

    /**
     * GetAllBuildingTypes
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getallbuildingtypes/json/{json}/countryid/{countryid}")
    public TingcoHome getJson_getAllBuildingTypes(@PathParam("countryid") int countryId) {
        return getAllBuildingTypes(countryId);
    }

    /**
     * GetBuildingDetailsByGroupId
     * @param groupId
     * @param countryId
     * @param searchString
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getbuildingdetailsbygroupid/rest/{rest}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoHome getXML_getBuildingDetailsByGroupId(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("searchstring") String searchString) {
        return getBuildingDetailsByGroupId(groupId, countryId, searchString);
    }

    /**
     * GetBuildingDetailsByGroupId
     * @param groupId
     * @param countryId
     * @param searchString
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getbuildingdetailsbygroupid/json/{json}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoHome getJson_getBuildingDetailsByGroupId(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("searchstring") String searchString) {
        return getBuildingDetailsByGroupId(groupId, countryId, searchString);
    }

    /**
     * GetDeviceDetailsForBuilding
     * @param groupId
     * @param countryId
     * @param groupName
     * @param deviceName
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getdevicedetailsforbuildings/rest/{rest}/groupid/{groupid}/countryid/{countryid}{groupname:(/groupname/[^/]+?)?}{devicename:(/devicename/[^/]+?)?}")
    @RESTDoc(methodName = "GetDeviceDetailsForBuilding", desc = "Get DeviceDetails For Building", req_Params = {"GroupID;UUID", "CountryID;int"}, opt_Params = {"groupName;string", "deviceName;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_getDeviceDetailsForBuilding(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("groupname") String groupName, @PathParam("devicename") String deviceName) {
        return getDeviceDetailsForBuilding(groupId, countryId, groupName, deviceName);
    }

    /**
     * GetDeviceDetailsForBuilding
     * @param groupId
     * @param countryId
     * @param groupName
     * @param deviceName
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getdevicedetailsforbuildings/json/{json}/groupid/{groupid}/countryid/{countryid}{groupname:(/groupname/[^/]+?)?}{devicename:(/devicename/[^/]+?)?}")
    public TingcoDevice getJson_getDeviceDetailsForBuilding(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("groupname") String groupName, @PathParam("devicename") String deviceName) {
        return getDeviceDetailsForBuilding(groupId, countryId, groupName, deviceName);
    }

    /**
     * GetZoneDetailsByGroupId
     * @param groupId
     * @param searchString
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getzonedetailsbygroupid/rest/{rest}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetZoneDetailsByGroupId", desc = "Used to Get ZoneDetails By GroupId", req_Params = {"groupid;string"}, opt_Params = {"searchString;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoHome getXML_getZoneDetailsByGroupId(@PathParam("groupid") String groupId, @PathParam("searchstring") String searchString) {
        return getZoneDetailsByGroupId(groupId, searchString);
    }

    /**
     * GetZoneDetailsByGroupId
     * @param groupId
     * @param searchString
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getzonedetailsbygroupid/json/{json}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoHome getJson_getZoneDetailsByGroupId(@PathParam("groupid") String groupId, @PathParam("searchstring") String searchString) {
        return getZoneDetailsByGroupId(groupId, searchString);
    }

    /**
     * GetRoomDetailsByGroupId
     * @param groupId
     * @param countryId
     * @param searchString
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getroomdetailsbygroupid/rest/{rest}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetRoomDetailsByGroupId", desc = "Used to Get RoomDetails By GroupId", req_Params = {"groupid;string", "countryId;int"}, opt_Params = {"searchString;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoHome getXML_getRoomDetailsByGroupId(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("searchstring") String searchString) {
        return getRoomDetailsByGroupId(groupId, countryId, searchString);
    }

    /**
     * GetRoomDetailsByGroupId
     * @param groupId
     * @param countryId
     * @param searchString
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getroomdetailsbygroupid/json/{json}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoHome getJson_getRoomDetailsByGroupId(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("searchstring") String searchString) {
        return getRoomDetailsByGroupId(groupId, countryId, searchString);
    }

    /**
     * AddZoneDetails
     * @param zoneName
     * @param groupId
     * @param zoneDescription
     * @param iconURL
     * @return TingcoHome
     */
    @GET
    @Produces("application/xml")
    @Path("/addzonedetails/rest/{rest}/zonename/{zonename}/groupid/{groupid}{zonedescription:(/zonedescription/[^/]+?)?}{iconurl:(/iconurl/[^/]+?)?}")
    @RESTDoc(methodName = "AddZoneDetails", desc = "Add Zone Details", req_Params = {"groupid;string", "countryId;int"}, opt_Params = {"searchString;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoHome getXML_AddZoneDetails(@PathParam("zonename") String zoneName, @PathParam("groupid") String groupId, @PathParam("zonedescription") String zoneDescription, @PathParam("iconurl") String iconURL) {
        return addZoneDetails(zoneName, groupId, zoneDescription, iconURL);
    }

    /**
     * AddZoneDetails
     * @param zoneName
     * @param groupId
     * @param zoneDescription
     * @param iconURL
     * @return TingcoHome
     */
    @GET
    @Produces("application/json")
    @Path("/addzonedetails/json/{json}/zonename/{zonename}/groupid/{groupid}{zonedescription:(/zonedescription/[^/]+?)?}{iconurl:(/iconurl/[^/]+?)?}")
    @RESTDoc(methodName = "AddZoneDetails", desc = "Add Zone Details", req_Params = {"groupid;string", "countryId;int"}, opt_Params = {"searchString;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoHome getJSON_AddZoneDetails(@PathParam("zonename") String zoneName, @PathParam("groupid") String groupId, @PathParam("zonedescription") String zoneDescription, @PathParam("iconurl") String iconURL) {
        return addZoneDetails(zoneName, groupId, zoneDescription, iconURL);
    }

    /**
     * UpdateZoneDetails
     * @param zoneName
     * @param groupId
     * @param zoneDescription
     * @param iconURL
     * @return TingcoHome
     */
    @GET
    @Produces("application/xml")
    @Path("/updatezonedetails/rest/{rest}/zoneid/{zoneid}/zonename/{zonename}/groupid/{groupid}{zonedescription:(/zonedescription/[^/]+?)?}{iconurl:(/iconurl/[^/]+?)?}")
    @RESTDoc(methodName = "UpdateZoneDetails", desc = "Update Zone Details", req_Params = {"groupid;string", "countryId;int"}, opt_Params = {"searchString;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoHome getXML_UpdateZoneDetails(@PathParam("zoneid") String zoneId, @PathParam("zonename") String zoneName, @PathParam("groupid") String groupId, @PathParam("zonedescription") String zoneDescription, @PathParam("iconurl") String iconURL) {
        return updateZoneDetails(zoneId, zoneName, groupId, zoneDescription, iconURL);
    }

    /**
     * UpdateZoneDetails
     * @param zoneName
     * @param groupId
     * @param zoneDescription
     * @param iconURL
     * @return TingcoHome
     */
    @GET
    @Produces("application/json")
    @Path("/updatezonedetails/json/{json}/zoneid/{zoneid}/zonename/{zonename}/groupid/{groupid}{zonedescription:(/zonedescription/[^/]+?)?}{iconurl:(/iconurl/[^/]+?)?}")
    @RESTDoc(methodName = "UpdateZoneDetails", desc = "Update Zone Details", req_Params = {"groupid;string", "countryId;int"}, opt_Params = {"searchString;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoHome getJson_UpdateZoneDetails(@PathParam("zoneid") String zoneId, @PathParam("zonename") String zoneName, @PathParam("groupid") String groupId, @PathParam("zonedescription") String zoneDescription, @PathParam("iconurl") String iconURL) {
        return updateZoneDetails(zoneId, zoneName, groupId, zoneDescription, iconURL);
    }

    /**
     * GetZoneDetailsByID
     * @param zoneId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/rest")
    @Path("/getzonedetailsbyid/rest/{rest}/zoneid/{zoneid}/countryid/{countryid}")
    public TingcoHome getxml_GetZoneDetailsByID(@PathParam("zoneid") String zoneId, @PathParam("countryid") int countryId) {
        return getZoneDetailsByID(zoneId, countryId);
    }

    /**
     * GetZoneDetailsByID
     * @param zoneId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getzonedetailsbyid/json/{json}/zoneid/{zoneid}/countryid/{countryid}")
    public TingcoHome getJson_GetZoneDetailsByID(@PathParam("zoneid") String zoneId, @PathParam("countryid") int countryId) {
        return getZoneDetailsByID(zoneId, countryId);
    }

    /**
     * GetRoomDetailsByRoomId
     * @param roomId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getzonedetailsbyroomid/rest/{rest}/roomid/{roomid}")
    @RESTDoc(methodName = "GetZoneDetailsByRoomId", desc = "Used to Get RoomDetails By RoomId", req_Params = {"roomId;string", "countryId;string"}, opt_Params = {""}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoHome getXml_getzoneDetailsByRoomId(@PathParam("roomid") String roomId) {
        return getZoneDetailsByRoomId(roomId);
    }

    /**
     * GetRoomDetailsByRoomId
     * @param roomId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getzonedetailsbyroomid/json/{json}/roomid/{roomid}")
    public TingcoHome getJson_getZoneDetailsByRoomId(@PathParam("roomid") String roomId) {
        return getZoneDetailsByRoomId(roomId);
    }

    /**
     * AddRoomDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addroomdetails/rest/{rest}")
    @RESTDoc(methodName = "AddRoomDetails", desc = "Used to add new Rooms", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoHome postXml_addRoomDetails(String content) {
        return addRoomDetails(content);
    }

    /**
     * AddRoomDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addroomdetails/json/{json}")
    public TingcoHome postJson_addRoomDetails(String content) {
        return addRoomDetails(content);
    }

    /**
     * UpdateRooms
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/updaterooms/rest/{rest}")
    @RESTDoc(methodName = "UpdateRooms", desc = "Used to Update Rooms", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoHome postXml_updateRooms(String content) {
        return updateRooms(content);
    }

    /**
     * UpdateRooms
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/updaterooms/json/{json}")
    public TingcoHome postJson_updateRooms(String content) {
        return updateRooms(content);
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public HomeResource getHomeResource() {
        return new HomeResource();
    }

    private TingcoHome addBuildingDevices(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoHome tingcoHome = null;
        Session session = null;
        Transaction tx = null;
        int i = 0;
        try {
            tingcoHome = tingcoHomeXML.buildTingcoHomeTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.ROOMS, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    TingcoHome tingcoHomeJaxb = (TingcoHome) JAXBManager.getInstance().unMarshall(content, TingcoHome.class);
                    se.info24.homejaxb.BuildingDevices buildingDevicesjaxb = tingcoHomeJaxb.getBuildings().getBuilding().get(0).getBuildingDevices();
                    for (DeviceID deviceId : buildingDevicesjaxb.getDeviceID()) {
                        BuildingDevices buildingDevices = new BuildingDevices();
                        buildingDevices.setId(new BuildingDevicesId(buildingDevicesjaxb.getBuildingID(), deviceId.getValue()));
                        buildingDevices.setRooms(new Rooms(buildingDevicesjaxb.getRoom().get(0).getRoomID()));
                        buildingDevices.setLastUpdatedByUserId(sessions2.getUserId());
                        buildingDevices.setCreatedDate(currentDateTime.getTime());
                        buildingDevices.setUpdatedDate(currentDateTime.getTime());
                        session.saveOrUpdate(buildingDevices);
                        i++;
                        if (i % 20 == 0) {
                            session.flush();
                            session.clear();
                        }
                    }
                    tx.commit();
                } else {
                    tingcoHome.getMsgStatus().setResponseCode(-10);
                    tingcoHome.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoHome;
                }
            } else {
                tingcoHome.getMsgStatus().setResponseCode(-3);
                tingcoHome.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoHome;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
            tingcoHome.getMsgStatus().setResponseCode(-1);
            tingcoHome.getMsgStatus().setResponseText("Error occured");
            tx.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoHome;
    }

    private TingcoHome deleteBuildingDevices(String buildingId, String deviceid) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoHome tingcoHome = null;
        Session session = null;
        Transaction tx = null;
        try {
            tingcoHome = tingcoHomeXML.buildTingcoHomeTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.BUILDINGS, TCMUtil.DELETE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    Object object = homeDAO.getBuildingDevicesById(session, buildingId, deviceid);
                    if (object != null) {
                        session.delete(object);
                        tx.commit();
                    } else {
                        tingcoHome.getMsgStatus().setResponseCode(-1);
                        tingcoHome.getMsgStatus().setResponseText("No Data found");
                        return tingcoHome;
                    }
                } else {
                    tingcoHome.getMsgStatus().setResponseCode(-10);
                    tingcoHome.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoHome;
                }
            } else {
                tingcoHome.getMsgStatus().setResponseCode(-3);
                tingcoHome.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoHome;
            }
        } catch (Exception ex) {
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoHome.getMsgStatus().setResponseCode(-1);
            tingcoHome.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoHome;
    }

    private TingcoHome getBuildingDevicesDetails(String buildingId, String groupId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoHome tingcoHome = null;
        Session session = null;
        try {
            tingcoHome = tingcoHomeXML.buildTingcoHomeTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.BUILDINGS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ServiceStatusDetails> buildingDevicesList = homeDAO.getBuildingDevicesByBuildingIdAndGroupId(session, buildingId, groupId, countryId);
                    if (!buildingDevicesList.isEmpty()) {
                        Collections.sort(buildingDevicesList, new Comparator<ServiceStatusDetails>() {

                            public int compare(ServiceStatusDetails o1, ServiceStatusDetails o2) {
                                return o1.getDeviceName().compareToIgnoreCase(o2.getDeviceName());
                            }
                        });
                        tingcoHome = tingcoHomeXML.buildBuildingDevicesDetails(tingcoHome, buildingDevicesList);
                        
                    } else {
                        tingcoHome.getMsgStatus().setResponseCode(-1);
                        tingcoHome.getMsgStatus().setResponseText("No data found");
                        return tingcoHome;
                    }
                } else {
                    tingcoHome.getMsgStatus().setResponseCode(-10);
                    tingcoHome.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoHome;
                }
            } else {
                tingcoHome.getMsgStatus().setResponseCode(-3);
                tingcoHome.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoHome;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoHome.getMsgStatus().setResponseCode(-1);
            tingcoHome.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoHome;
    }

    private TingcoDevice getDeviceDetailsForBuilding(String groupId, int countryId, String groupName, String deviceName) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (!groupName.equals("")) {
                    groupName = groupName.split("/")[2];
                } else {
                    groupName = null;
                }
                if (!deviceName.equals("")) {
                    deviceName = deviceName.split("/")[2];
                } else {
                    deviceName = null;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.BUILDINGS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<Device> devicesList = homeDAO.getDeviceDetailsForBuilding(groupId, countryId, groupName, deviceName, session);
                    if (!devicesList.isEmpty()) {
                        Collections.sort(devicesList);
                        tingcoDevice = tingcoDeviceXML.buildDevices(tingcoDevice, devicesList, countryId,session);
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
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoHome getRoomsByBuildingId(String buildingId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoHome tingcoHome = null;
        Session session = null;
        try {
            tingcoHome = tingcoHomeXML.buildTingcoHomeTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.BUILDINGS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<Rooms> roomsList = homeDAO.getRoomsByBuildingId(buildingId, session);
                    if (!roomsList.isEmpty()) {
                        Collections.sort(roomsList);
                        tingcoHome = tingcoHomeXML.buildRoomsByBuildingId(tingcoHome, roomsList);
                    } else {
                        tingcoHome.getMsgStatus().setResponseCode(-1);
                        tingcoHome.getMsgStatus().setResponseText("No data found");
                        return tingcoHome;
                    }
                } else {
                    tingcoHome.getMsgStatus().setResponseCode(-10);
                    tingcoHome.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoHome;
                }
            } else {
                tingcoHome.getMsgStatus().setResponseCode(-3);
                tingcoHome.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoHome;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);

            tingcoHome.getMsgStatus().setResponseCode(-1);
            tingcoHome.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoHome;
    }

    private TingcoHome getZoneDetailsByRoomId(String roomId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoHome tingcoHome = null;
        Session session = null;
        try {
            tingcoHome = tingcoHomeXML.buildTingcoHomeTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.ROOMS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ZoneRooms> zoneRoomses = homeDAO.getZoneRoomsByRoomId(session, roomId);
                    if (zoneRoomses.isEmpty()) {
                        tingcoHome.getMsgStatus().setResponseCode(-1);
                        tingcoHome.getMsgStatus().setResponseText("Data Not Found.");
                        return tingcoHome;
                    }
                    Set<String> zoneId = new HashSet<String>();
                    for (ZoneRooms zr : zoneRoomses) {
                        zoneId.add(zr.getZones().getZoneId());
                    }
                    tingcoHome = tingcoHomeXML.buildGetZoneDetailsByRoomId(tingcoHome, zoneRoomses, zoneId);
                } else {
                    tingcoHome.getMsgStatus().setResponseCode(-10);
                    tingcoHome.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoHome;
                }
            } else {
                tingcoHome.getMsgStatus().setResponseCode(-3);
                tingcoHome.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoHome;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);

            tingcoHome.getMsgStatus().setResponseCode(-1);
            tingcoHome.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoHome;
    }

    private TingcoHome getZoneDetailsByID(String zoneId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoHome tingcoHome = null;
        Session session = null;
        try {
            tingcoHome = tingcoHomeXML.buildTingcoHomeTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.ROOMS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Zones zone = (Zones) session.get(Zones.class, zoneId);
                    if (zone == null) {
                        tingcoHome.getMsgStatus().setResponseCode(-1);
                        tingcoHome.getMsgStatus().setResponseText("Data Not Found.");
                        return tingcoHome;
                    }
                    GroupDAO groupDAO = new GroupDAO();
                    GroupTranslations gt = groupDAO.getGroupTranslationsByIds(zone.getGroupId(), countryId, session);
                    tingcoHome = tingcoHomeXML.buildGetZoneDetailsByID(tingcoHome, zone, gt);


                } else {
                    tingcoHome.getMsgStatus().setResponseCode(-10);
                    tingcoHome.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoHome;
                }
            } else {
                tingcoHome.getMsgStatus().setResponseCode(-3);
                tingcoHome.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoHome;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);

            tingcoHome.getMsgStatus().setResponseCode(-1);
            tingcoHome.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoHome;
    }

    private TingcoHome updateZoneDetails(String zoneId, String zoneName, String groupId, String zoneDescription, String iconURL) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoHome tingcoHome = null;
        Session session = null;
        Transaction tx = null;
        try {
            tingcoHome = tingcoHomeXML.buildTingcoHomeTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.ROOMS, TCMUtil.UPDATE);
                if (hasPermission) {
                    if (!zoneDescription.equals("")) {
                        zoneDescription = zoneDescription.split("/")[2];
                    } else {
                        zoneDescription = null;
                    }
                    if (!iconURL.equals("")) {
                        iconURL = iconURL.split("/")[2];
                    } else {
                        iconURL = null;
                    }
                    GregorianCalendar gc = new GregorianCalendar();
                    session = HibernateUtil.getSessionFactory().openSession();
                    Zones zone = (Zones) session.get(Zones.class, zoneId);
                    if (zone == null) {
                        tingcoHome.getMsgStatus().setResponseCode(-1);
                        tingcoHome.getMsgStatus().setResponseText("Data Not Found.");
                        return tingcoHome;
                    }
                    tx = session.beginTransaction();
                    zone.setZoneName(zoneName);
                    zone.setGroupId(groupId);
                    if (zoneDescription != null) {
                        zone.setZoneDescription(zoneDescription);
                    } else {
                        zone.setZoneDescription(null);
                    }
                    if (iconURL != null) {
                        zone.setIconUrl(TCMUtil.convertHexToString(iconURL));
                    } else {
                        zone.setIconUrl(null);
                    }
                    zone.setLastUpdatedByUserId(sessions2.getUserId());
                    zone.setUpdatedDate(gc.getTime());
                    session.save(zone);
                    tx.commit();

                } else {
                    tingcoHome.getMsgStatus().setResponseCode(-10);
                    tingcoHome.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoHome;
                }
            } else {
                tingcoHome.getMsgStatus().setResponseCode(-3);
                tingcoHome.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoHome;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
            tingcoHome.getMsgStatus().setResponseCode(-1);
            tingcoHome.getMsgStatus().setResponseText("Error occured");
            tx.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoHome;
    }

    private TingcoHome addZoneDetails(String zoneName, String groupId, String zoneDescription, String iconURL) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoHome tingcoHome = null;
        Session session = null;
        Transaction tx = null;
        try {
            tingcoHome = tingcoHomeXML.buildTingcoHomeTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.ROOMS, TCMUtil.ADD);
                if (hasPermission) {
                    if (!zoneDescription.equals("")) {
                        zoneDescription = zoneDescription.split("/")[2];
                    } else {
                        zoneDescription = null;
                    }
                    if (!iconURL.equals("")) {
                        iconURL = iconURL.split("/")[2];
                    } else {
                        iconURL = null;
                    }
                    GregorianCalendar gc = new GregorianCalendar();
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    Zones zone = new Zones();
                    zone.setZoneId(UUID.randomUUID().toString());
                    zone.setZoneName(zoneName);
                    zone.setGroupId(groupId);
                    if (zoneDescription != null) {
                        zone.setZoneDescription(zoneDescription);
                    }
                    if (iconURL != null) {
                        zone.setIconUrl(TCMUtil.convertHexToString(iconURL));
                    }
                    zone.setLastUpdatedByUserId(sessions2.getUserId());
                    zone.setCreatedDate(gc.getTime());
                    zone.setUpdatedDate(gc.getTime());
                    session.save(zone);
                    tx.commit();

                } else {
                    tingcoHome.getMsgStatus().setResponseCode(-10);
                    tingcoHome.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoHome;
                }
            } else {
                tingcoHome.getMsgStatus().setResponseCode(-3);
                tingcoHome.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoHome;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
            tingcoHome.getMsgStatus().setResponseCode(-1);
            tingcoHome.getMsgStatus().setResponseText("Error occured");
            tx.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoHome;
    }

    private TingcoHome addRoomDetails(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoHome tingcoHome = null;
        Session session = null;
        Transaction tx = null;
        boolean recordsAdded = false;
        try {
            tingcoHome = tingcoHomeXML.buildTingcoHomeTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.ROOMS, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    TingcoHome tingcoHomeJaxb = (TingcoHome) JAXBManager.getInstance().unMarshall(content, TingcoHome.class);
                    Room roomJaxb = tingcoHomeJaxb.getZones().getZone().get(0).getRoom().get(0);
                    String roomId = UUID.randomUUID().toString();
                    Rooms rooms = new Rooms();
                    rooms.setRoomId(roomId);
                    rooms.setRoomName(roomJaxb.getRoomName());
                    if (roomJaxb.getRoomDescription() != null) {
                        rooms.setRoomDescription(roomJaxb.getRoomDescription());
                    }
                    if (roomJaxb.getRoomNumber() != null) {
                        rooms.setRoomNumber(roomJaxb.getRoomNumber());
                    }
                    rooms.setRoomTypes(new RoomTypes(roomJaxb.getRoomTypeID()));
                    rooms.setBuildings(new Buildings(roomJaxb.getBuilding().getBuildingID()));
                    if (roomJaxb.getContainerID() != null) {
                        rooms.setContainerId(roomJaxb.getContainerID().getValue());
                    }
                    rooms.setIsNonSmoking(roomJaxb.getIsNonSmoking());
                    rooms.setIsPublicAccess(roomJaxb.getIsPublicAccess());
                    rooms.setIsDoNotDisturb(roomJaxb.getIsDoNotDisturb());
                    rooms.setOwnerGroupId(roomJaxb.getOwnerGroupID().getValue());
                    if (roomJaxb.getUsedByGroupID() != null) {
                        rooms.setUsedByGroupId(roomJaxb.getUsedByGroupID().getValue());
                    }
                    rooms.setAgreements(new Agreements(roomJaxb.getAgreementID().getValue()));
                    if (roomJaxb.getFloorLevel() != null) {
                        rooms.setFloorLevel(roomJaxb.getFloorLevel());
                    }
                    if (roomJaxb.getArea() != null) {
                        rooms.setArea(roomJaxb.getArea());
                    }
                    if (roomJaxb.getAreaUnit() != null) {
                        rooms.setAreaUnit(roomJaxb.getAreaUnit());
                    }
                    if (roomJaxb.getVolume() != null) {
                        rooms.setVolume(roomJaxb.getVolume());
                    }
                    if (roomJaxb.getVolumeUnit() != null) {
                        rooms.setVolumeUnit(roomJaxb.getVolumeUnit());
                    }
                    if (roomJaxb.getLayoutImageURL() != null) {
                        rooms.setLayoutImageUrl(roomJaxb.getLayoutImageURL());
                    }
                    if (roomJaxb.getRating() != null) {
                        rooms.setRating(roomJaxb.getRating().intValue());
                    }
                    rooms.setLastUpdatedByUserId(sessions2.getUserId());
                    rooms.setCreatedDate(currentDateTime.getTime());
                    rooms.setUpdatedDate(currentDateTime.getTime());

                    if (tingcoHomeJaxb.getZones().getZone().get(0).getZoneID() != null) {
                        Set<ZoneRooms> zoneRoomsSet = new HashSet<ZoneRooms>();
                        ZoneRooms zoneRooms = new ZoneRooms();
                        zoneRooms.setId(new ZoneRoomsId(tingcoHomeJaxb.getZones().getZone().get(0).getZoneID(), roomId));
                        zoneRooms.setLastUpdatedByUserId(sessions2.getUserId());
                        zoneRooms.setCreatedDate(currentDateTime.getTime());
                        zoneRooms.setUpdatedDate(currentDateTime.getTime());
                        zoneRoomsSet.add(zoneRooms);
                        rooms.setZoneRoomses(zoneRoomsSet);
                    }
                    session.saveOrUpdate(rooms);
                    recordsAdded = true;
                    if (recordsAdded) {
                        tx.commit();
                    }

                } else {
                    tingcoHome.getMsgStatus().setResponseCode(-10);
                    tingcoHome.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoHome;
                }
            } else {
                tingcoHome.getMsgStatus().setResponseCode(-3);
                tingcoHome.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoHome;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
            tingcoHome.getMsgStatus().setResponseCode(-1);
            tingcoHome.getMsgStatus().setResponseText("Error occured");
            tx.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoHome;
    }

    private TingcoHome getAllBuildingTypes(int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoHome tingcoHome = null;
        Session session = null;
        try {
            tingcoHome = tingcoHomeXML.buildTingcoHomeTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.BUILDINGS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<BuildingTypeTranslations> btTransList = homeDAO.getAllBuildingTypeTranslationsByCountryId(countryId, session);
                    if (!btTransList.isEmpty()) {
                        tingcoHome = tingcoHomeXML.buildAllBuildingTypeTranslations(tingcoHome, btTransList);
                    } else {
                        tingcoHome.getMsgStatus().setResponseCode(-1);
                        tingcoHome.getMsgStatus().setResponseText("No data found");
                        return tingcoHome;
                    }
                } else {
                    tingcoHome.getMsgStatus().setResponseCode(-10);
                    tingcoHome.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoHome;
                }
            } else {
                tingcoHome.getMsgStatus().setResponseCode(-3);
                tingcoHome.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoHome;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoHome.getMsgStatus().setResponseCode(-1);
            tingcoHome.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoHome;
    }

    private TingcoHome getAllRoomTypes(int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoHome tingcoHome = null;
        Session session = null;
        try {
            tingcoHome = tingcoHomeXML.buildTingcoHomeTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.ROOMS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<RoomTypeTranslations> roomTypeTranslationses = homeDAO.getRoomTypeTranslationsByCountryIds(countryId, session);
                    if (!roomTypeTranslationses.isEmpty()) {
                        tingcoHome = tingcoHomeXML.buildGetAllRoomTypes(tingcoHome, roomTypeTranslationses);
                    } else {
                        tingcoHome.getMsgStatus().setResponseCode(-1);
                        tingcoHome.getMsgStatus().setResponseText("No Data Found");
                        return tingcoHome;
                    }
                } else {
                    tingcoHome.getMsgStatus().setResponseCode(-10);
                    tingcoHome.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoHome;
                }
            } else {
                tingcoHome.getMsgStatus().setResponseCode(-3);
                tingcoHome.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoHome;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoHome.getMsgStatus().setResponseCode(-1);
            tingcoHome.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoHome;
    }

    private TingcoHome getBuildingDetailsByGroupId(String groupId, int countryId, String searchString) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoHome tingcoHome = null;
        Session session = null;
        DeviceDAO deviceDAO = new DeviceDAO();
        GroupDAO groupDAO = new GroupDAO();
        try {
            tingcoHome = tingcoHomeXML.buildTingcoHomeTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (!searchString.equals("")) {
                    searchString = searchString.split("/")[2];
                } else {
                    searchString = null;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.BUILDINGS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<Buildings> buildingsList = new ArrayList<Buildings>();
                    List<GroupTranslations> groupTransList = new ArrayList<GroupTranslations>();
                    List<String> groupidset = deviceDAO.getTrustedGroup(session, groupId);
                    List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                    for (List<String> list : splitList) {
                        List<GroupTranslations> gtListTemp = groupDAO.getGroupTranslationsById(list, countryId, session);
                        if (gtListTemp != null) {
                            groupTransList.addAll(gtListTemp);
                        }
                        if (searchString == null) {
                            List<Buildings> buildingsListTemp = homeDAO.getBuildingsByGroupIdsList(list, session);
                            if (!buildingsListTemp.isEmpty()) {
                                buildingsList.addAll(buildingsListTemp);
                            }
                        } else {
                            if (TCMUtil.isValidUUID(searchString)) {
                                Object buildingsObject = homeDAO.getBuildingsByIdAndGroupIdsList(list, searchString, session);
                                if (buildingsObject != null) {
                                    buildingsList.add((Buildings) buildingsObject);
                                }
                            } else {
                                List<Buildings> buildingsListTemp = homeDAO.getBuildingsByNameAndOwnerGroupId(list, searchString, session);
                                if (!buildingsListTemp.isEmpty()) {
                                    buildingsList.addAll(buildingsListTemp);
                                }
                            }
                        }
                    }
                    if (!buildingsList.isEmpty()) {
                        if (buildingsList.size() > 200) {
                            buildingsList = buildingsList.subList(0, 200);
                        }
                        Collections.sort(buildingsList, new Comparator<Buildings>() {

                            public int compare(Buildings o1, Buildings o2) {
                                return o1.getBuildingName().compareToIgnoreCase(o2.getBuildingName());
                            }
                        });
                        tingcoHome = tingcoHomeXML.buildBuildingsDetails(tingcoHome, buildingsList, groupTransList);
                    } else {
                        tingcoHome.getMsgStatus().setResponseCode(-1);
                        tingcoHome.getMsgStatus().setResponseText("No data found");
                        return tingcoHome;
                    }
                } else {
                    tingcoHome.getMsgStatus().setResponseCode(-10);
                    tingcoHome.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoHome;
                }
            } else {
                tingcoHome.getMsgStatus().setResponseCode(-3);
                tingcoHome.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoHome;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoHome.getMsgStatus().setResponseCode(-1);
            tingcoHome.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoHome;
    }

    private TingcoHome getRoomDetailsByGroupId(String groupId, int countryId, String searchString) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoHome tingcoHome = null;
        Session session = null;
        DeviceDAO deviceDAO = new DeviceDAO();
        try {
            tingcoHome = tingcoHomeXML.buildTingcoHomeTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (!searchString.equals("")) {
                    searchString = searchString.split("/")[2];
                } else {
                    searchString = null;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.BUILDINGS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<Rooms> roomsList = new ArrayList<Rooms>();

                    List<String> groupidset = deviceDAO.getTrustedGroup(session, groupId);
                    List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                    for (List<String> list : splitList) {
                        if (searchString == null) {
                            List<Rooms> roomsListTemp = homeDAO.getRoomsByGroupIdsList(list, session);
                            if (!roomsListTemp.isEmpty()) {
                                roomsList.addAll(roomsListTemp);
                            }
                        } else {
                            if (TCMUtil.isValidUUID(searchString)) {
                                Object roomsObject = homeDAO.getRoomsByIdAndOwnerGroupIds(list, searchString, session);
                                if (roomsObject != null) {
                                    roomsList.add((Rooms) roomsObject);
                                }
                            } else {
                                List<Rooms> roomsListTemp = homeDAO.getRoomsByRoomNameAndOwnerGroupIds(list, searchString, session);
                                if (!roomsListTemp.isEmpty()) {
                                    roomsList.addAll(roomsListTemp);
                                }
                            }
                        }
                    }
                    if (!roomsList.isEmpty()) {
                        if (roomsList.size() > 200) {
                            roomsList = roomsList.subList(0, 200);
                        }
                        Collections.sort(roomsList, new Comparator<Rooms>() {

                            public int compare(Rooms o1, Rooms o2) {
                                return o1.getRoomName().compareToIgnoreCase(o2.getRoomName());
                            }
                        });
                        tingcoHome = tingcoHomeXML.buildRoomsDetails(tingcoHome, roomsList);
                    } else {
                        tingcoHome.getMsgStatus().setResponseCode(-1);
                        tingcoHome.getMsgStatus().setResponseText("No data found");
                        return tingcoHome;
                    }
                } else {
                    tingcoHome.getMsgStatus().setResponseCode(-10);
                    tingcoHome.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoHome;
                }
            } else {
                tingcoHome.getMsgStatus().setResponseCode(-3);
                tingcoHome.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoHome;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoHome.getMsgStatus().setResponseCode(-1);
            tingcoHome.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoHome;
    }

    private TingcoHome getRoomDetailsByRoomId(String roomId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        boolean hasPermission = false;
        TingcoHome tingcoHome = null;
        try {
            tingcoHome = tingcoHomeXML.buildTingcoHomeTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.ROOMS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object roomObject = session.get(Rooms.class, roomId);
                    if (roomObject != null) {
                        Rooms rooms = (Rooms) roomObject;
                        tingcoHome = tingcoHomeXML.buildRoomDetails(tingcoHome, rooms, countryId, session);
                    } else {
                        tingcoHome.getMsgStatus().setResponseCode(-1);
                        tingcoHome.getMsgStatus().setResponseText("No data found");
                        return tingcoHome;
                    }
                } else {
                    tingcoHome.getMsgStatus().setResponseCode(-10);
                    tingcoHome.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoHome;
                }
            } else {
                tingcoHome.getMsgStatus().setResponseCode(-3);
                tingcoHome.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoHome;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoHome.getMsgStatus().setResponseCode(-1);
            tingcoHome.getMsgStatus().setResponseText("Error occured");
            return tingcoHome;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoHome;
    }

    private TingcoHome getZoneDetailsByGroupId(String groupId, String searchString) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoHome tingcoHome = null;
        Session session = null;
        DeviceDAO deviceDAO = new DeviceDAO();
        try {
            tingcoHome = tingcoHomeXML.buildTingcoHomeTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (!searchString.equals("")) {
                    searchString = searchString.split("/")[2];
                } else {
                    searchString = null;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.ROOMS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<Zones> zonesList = new ArrayList<Zones>();
                    List<String> groupidset = deviceDAO.getTrustedGroup(session, groupId);
                    List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                    for (List<String> list : splitList) {
                        if (searchString == null) {
                            List<Zones> zonesListTemp = homeDAO.getZonesByGroupIdsList(list, session);
                            if (!zonesListTemp.isEmpty()) {
                                zonesList.addAll(zonesListTemp);
                            }
                        } else {
                            if (TCMUtil.isValidUUID(searchString)) {
                                Object zonesObject = homeDAO.getZonesByIdAndGroupIds(list, searchString, session);
                                if (zonesObject != null) {
                                    zonesList.add((Zones) zonesObject);
                                }
                            } else {
                                List<Zones> zonesListTemp = homeDAO.getZonesByZoneNameAndGroupIds(list, searchString, session);
                                if (!zonesListTemp.isEmpty()) {
                                    zonesList.addAll(zonesListTemp);
                                }
                            }
                        }
                    }

                    if (!zonesList.isEmpty()) {
                        if (zonesList.size() > 200) {
                            zonesList = zonesList.subList(0, 200);
                        }
                        Collections.sort(zonesList, new Comparator<Zones>() {

                            public int compare(Zones o1, Zones o2) {
                                return o1.getZoneName().compareToIgnoreCase(o2.getZoneName());
                            }
                        });
                        tingcoHome = tingcoHomeXML.buildZonesDetails(tingcoHome, zonesList);
                    } else {
                        tingcoHome.getMsgStatus().setResponseCode(-1);
                        tingcoHome.getMsgStatus().setResponseText("No data found");
                        return tingcoHome;
                    }
                } else {
                    tingcoHome.getMsgStatus().setResponseCode(-10);
                    tingcoHome.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoHome;
                }
            } else {
                tingcoHome.getMsgStatus().setResponseCode(-3);
                tingcoHome.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoHome;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoHome.getMsgStatus().setResponseCode(-1);
            tingcoHome.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoHome;
    }

    private TingcoHome updateBuildingDetails(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoHome tingcoHome = null;
        Session session = null;
        try {
            tingcoHome = tingcoHomeXML.buildTingcoHomeTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.BUILDINGS, TCMUtil.UPDATE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Buildings modifiedBuildings = null;
                    TingcoHome postTingcoHome = (TingcoHome) JAXBManager.getInstance().unMarshall(content, TingcoHome.class);
                    if (!postTingcoHome.getBuildings().getBuilding().isEmpty()) {
                        for (se.info24.homejaxb.Building buildingXml : postTingcoHome.getBuildings().getBuilding()) {
                            if (buildingXml.getBuildingID() == null || !TCMUtil.isValidUUID(buildingXml.getBuildingID())) {
                                tingcoHome.getMsgStatus().setResponseCode(-1);
                                tingcoHome.getMsgStatus().setResponseText("BuildingID Not Found");
                                return tingcoHome;
                            }
                            Object object = homeDAO.getBuildingsById(buildingXml.getBuildingID(), session);
                            if (object == null) {
                                tingcoHome.getMsgStatus().setResponseCode(-1);
                                tingcoHome.getMsgStatus().setResponseText("No Data Found");
                                return tingcoHome;
                            } else {
                                modifiedBuildings = (Buildings) object;
                            }

                            if (buildingXml.getIsPetsAllowed() != null) {
                                modifiedBuildings.setIsPetsAllowed(Integer.valueOf(buildingXml.getIsPetsAllowed()));
                            } else {
                                modifiedBuildings.setIsPetsAllowed(null);
                            }
                            if (buildingXml.getUserCanChangeClimatePricePref() != null) {
                                modifiedBuildings.setUserCanChangeClimatePricePref(Integer.valueOf(buildingXml.getUserCanChangeClimatePricePref()));
                            } else {
                                modifiedBuildings.setUserCanChangeClimatePricePref(null);
                            }

                            if (buildingXml.getBuildingParentID() != null) {
                                modifiedBuildings.setBuildingParentId(buildingXml.getBuildingParentID().getValue());
                            } else {
                                modifiedBuildings.setBuildingParentId(null);
                            }
                            modifiedBuildings.setBuildingName(buildingXml.getBuildingName());

                            if (buildingXml.getBuildingDescription() != null) {
                                modifiedBuildings.setBuildingDescription(buildingXml.getBuildingDescription());
                            } else {
                                modifiedBuildings.setBuildingDescription(null);
                            }

                            if (!buildingXml.getBuildingTypes().isEmpty()) {
                                BuildingTypes buildingTypes = homeDAO.getBuildingTypesById(buildingXml.getBuildingTypes().get(0).getBuildingTypeID(), session);
                                if (buildingTypes != null) {
                                    modifiedBuildings.setBuildingTypes(buildingTypes);
                                }
                            }
                            if (buildingXml.getOwnerGroupID() != null) {
                                modifiedBuildings.setOwnerGroupId(buildingXml.getOwnerGroupID().getValue());
                            }

                            if (buildingXml.getUsedByGroupID() != null) {
                                modifiedBuildings.setUsedByGroupId(buildingXml.getUsedByGroupID().getValue());
                            } else {
                                modifiedBuildings.setUsedByGroupId(null);
                            }

                            if (buildingXml.getAgreementID() != null) {
                                ApplicationDAO applicationDAO = new ApplicationDAO();
                                Agreements agreement = (Agreements) applicationDAO.getAgreementsById(session, buildingXml.getAgreementID().getValue());
                                modifiedBuildings.setAgreements(agreement);
                            }

                            if (buildingXml.getContainerID() != null) {
                                modifiedBuildings.setContainerId(buildingXml.getContainerID().getValue());
                            } else {
                                modifiedBuildings.setContainerId(null);
                            }

                            if (buildingXml.getPropertyCode() != null) {
                                modifiedBuildings.setPropertyCode(buildingXml.getPropertyCode());
                            } else {
                                modifiedBuildings.setPropertyCode(null);
                            }

                            if (buildingXml.getBuildingNumber() != null) {
                                modifiedBuildings.setBuildingNumber(buildingXml.getBuildingNumber());
                            } else {
                                modifiedBuildings.setBuildingNumber(null);
                            }

                            if (buildingXml.getBuildingCode() != null) {
                                modifiedBuildings.setBuildingCode(buildingXml.getBuildingCode());
                            } else {
                                modifiedBuildings.setBuildingCode(null);
                            }

                            if ((Integer) buildingXml.getShareOfParent() != null) {
                                modifiedBuildings.setShareOfParent(buildingXml.getShareOfParent());
                            } else {
                                modifiedBuildings.setShareOfParent(null);
                            }

                            if (buildingXml.getFloorLevel() != null) {
                                modifiedBuildings.setFloorLevel(buildingXml.getFloorLevel());
                            } else {
                                modifiedBuildings.setFloorLevel(null);
                            }

                            if (buildingXml.getArea() != null) {
                                modifiedBuildings.setArea(buildingXml.getArea());
                            } else {
                                modifiedBuildings.setArea(null);
                            }

                            if (buildingXml.getAreaUnit() != null) {
                                modifiedBuildings.setAreaUnit(buildingXml.getAreaUnit());
                            } else {
                                modifiedBuildings.setAreaUnit(null);
                            }

                            if (buildingXml.getVolume() != null) {
                                modifiedBuildings.setVolume(buildingXml.getVolume());
                            } else {
                                modifiedBuildings.setVolume(null);
                            }

                            if (buildingXml.getVolumeUnit() != null) {
                                modifiedBuildings.setVolumeUnit(buildingXml.getVolumeUnit());
                            } else {
                                modifiedBuildings.setVolumeUnit(null);
                            }

                            if ((Integer) buildingXml.getRating() != null) {
                                modifiedBuildings.setRating(buildingXml.getRating());
                            } else {
                                modifiedBuildings.setRating(null);
                            }

                            if ((Integer) buildingXml.getIsInEmergencyMode() != null) {
                                modifiedBuildings.setIsInEmergencyMode(buildingXml.getIsInEmergencyMode());
                            } else {
                                modifiedBuildings.setIsInEmergencyMode(null);
                            }

                            if ((Integer) buildingXml.getIsNonSmoking() != null) {
                                modifiedBuildings.setIsNonSmoking(buildingXml.getIsNonSmoking());
                            } else {
                                modifiedBuildings.setIsNonSmoking(null);
                            }

                            if ((Integer) buildingXml.getIsOpen() != null) {
                                modifiedBuildings.setIsOpen(buildingXml.getIsOpen());
                            } else {
                                modifiedBuildings.setIsOpen(null);
                            }

                            if ((Integer) buildingXml.getIsPublicAccess() != null) {
                                modifiedBuildings.setIsPublicAccess(buildingXml.getIsPublicAccess());
                            } else {
                                modifiedBuildings.setIsPublicAccess(null);
                            }

                            if (buildingXml.getStreet1() != null) {
                                modifiedBuildings.setStreet1(buildingXml.getStreet1());
                            } else {
                                modifiedBuildings.setStreet1(null);
                            }

                            if (buildingXml.getStreet2() != null) {
                                modifiedBuildings.setStreet2(buildingXml.getStreet2());
                            } else {
                                modifiedBuildings.setStreet2(null);
                            }

                            if (buildingXml.getZipCode() != null) {
                                modifiedBuildings.setZipCode(buildingXml.getZipCode());
                            } else {
                                modifiedBuildings.setZipCode(null);
                            }

                            if (buildingXml.getCity() != null) {
                                modifiedBuildings.setCity(buildingXml.getCity());
                            } else {
                                modifiedBuildings.setCity(null);
                            }

                            if (buildingXml.getState() != null) {
                                modifiedBuildings.setState(buildingXml.getState());
                            } else {
                                modifiedBuildings.setState(null);
                            }

                            if (buildingXml.getRegion() != null) {
                                modifiedBuildings.setRegion(buildingXml.getRegion());
                            } else {
                                modifiedBuildings.setRegion(null);
                            }

                            if (buildingXml.getCountryID() != null) {
                                CountryDAO countryDAO = new CountryDAO();
                                Country country = countryDAO.getCountryById(Integer.valueOf(buildingXml.getCountryID().getValue()), session);
                                modifiedBuildings.setCountry(country);
                            } else {
                                modifiedBuildings.setCountry(null);
                            }

                            if (buildingXml.getLatitude() != null) {
                                modifiedBuildings.setLatitude(buildingXml.getLatitude());
                            } else {
                                modifiedBuildings.setLatitude(null);
                            }

                            if (buildingXml.getLongitude() != null) {
                                modifiedBuildings.setLongitude(buildingXml.getLongitude());
                            } else {
                                modifiedBuildings.setLongitude(null);
                            }

                            if (buildingXml.getAltitude() != null) {
                                modifiedBuildings.setAltitude(buildingXml.getAltitude());
                            } else {
                                modifiedBuildings.setAltitude(null);
                            }

                            if (buildingXml.getLayoutImageURL() != null) {
                                modifiedBuildings.setLayoutImageUrl(buildingXml.getLayoutImageURL());
                            } else {
                                modifiedBuildings.setLayoutImageUrl(null);
                            }

                            if (buildingXml.getWebsiteURL() != null) {
                                modifiedBuildings.setWebsiteUrl(buildingXml.getWebsiteURL());
                            } else {
                                modifiedBuildings.setWebsiteUrl(null);
                            }

                            if ((Integer) buildingXml.getClimatePricePreference() != null) {
                                modifiedBuildings.setClimatePricePreference(buildingXml.getClimatePricePreference());
                            } else {
                                modifiedBuildings.setClimatePricePreference(null);
                            }

                            if (buildingXml.getEnergyMeterDeviceID() != null) {
                                modifiedBuildings.setEnergyMeterDeviceId(buildingXml.getEnergyMeterDeviceID().getValue());
                            } else {
                                modifiedBuildings.setEnergyMeterDeviceId(null);
                            }

                            if (buildingXml.getWaterMeterDeviceID() != null) {
                                modifiedBuildings.setWaterMeterDeviceId(buildingXml.getWaterMeterDeviceID().getValue());
                            } else {
                                modifiedBuildings.setWaterMeterDeviceId(null);
                            }

                            if (buildingXml.getHeatMeterDeviceID() != null) {
                                modifiedBuildings.setHeatMeterDeviceId(buildingXml.getHeatMeterDeviceID().getValue());
                            } else {
                                modifiedBuildings.setHeatMeterDeviceId(null);
                            }

                            if (buildingXml.getGasMeterDeviceID() != null) {
                                modifiedBuildings.setGasMeterDeviceId(buildingXml.getGasMeterDeviceID().getValue());
                            } else {
                                modifiedBuildings.setGasMeterDeviceId(null);
                            }

                            modifiedBuildings.setLastUpdatedByUserId(sessions2.getUserId());

                            modifiedBuildings.setUpdatedDate(currentDateTime.getTime());

                            if (modifiedBuildings != null) {
                                if (!homeDAO.saveOrUpdate(modifiedBuildings, session)) {
                                    tingcoHome.getMsgStatus().setResponseCode(-1);
                                    tingcoHome.getMsgStatus().setResponseText("Error occurred while updating Building");
                                    return tingcoHome;
                                }
                            }
                        }
                    } else {
                        tingcoHome.getMsgStatus().setResponseCode(-1);
                        tingcoHome.getMsgStatus().setResponseText("No Data In Post XMl");
                        return tingcoHome;
                    }
                } else {
                    tingcoHome.getMsgStatus().setResponseCode(-10);
                    tingcoHome.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoHome;
                }
            } else {
                tingcoHome.getMsgStatus().setResponseCode(-3);
                tingcoHome.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoHome;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoHome.getMsgStatus().setResponseCode(-1);
            tingcoHome.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoHome;
    }

    private TingcoHome addBuildingDetails(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoHome tingcoHome = null;
        Session session = null;
        try {
            tingcoHome = tingcoHomeXML.buildTingcoHomeTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.BUILDINGS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.BUILDINGS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Buildings newBuildings = null;
                    TingcoHome postTingcoHome = (TingcoHome) JAXBManager.getInstance().unMarshall(content, TingcoHome.class);
                    if (!postTingcoHome.getBuildings().getBuilding().isEmpty()) {
                        for (se.info24.homejaxb.Building buildingXml : postTingcoHome.getBuildings().getBuilding()) {
                            //
                            newBuildings = new Buildings();
                            newBuildings.setBuildingId(UUID.randomUUID().toString().toUpperCase());
                            if (buildingXml.getBuildingParentID() != null) {
                                newBuildings.setBuildingParentId(buildingXml.getBuildingParentID().getValue());
                            }
                            newBuildings.setBuildingName(buildingXml.getBuildingName());
                            if (buildingXml.getBuildingDescription() != null) {
                                newBuildings.setBuildingDescription(buildingXml.getBuildingDescription());
                            }
                            if (!buildingXml.getBuildingTypes().isEmpty()) {
                                BuildingTypes buildingTypes = homeDAO.getBuildingTypesById(buildingXml.getBuildingTypes().get(0).getBuildingTypeID(), session);
                                if (buildingTypes != null) {
                                    newBuildings.setBuildingTypes(buildingTypes);
                                }
                            }
                            if (buildingXml.getIsPetsAllowed() != null) {
                                newBuildings.setIsPetsAllowed(Integer.valueOf(buildingXml.getIsPetsAllowed()));
                            } else {
                                newBuildings.setIsPetsAllowed(null);
                            }
                            if (buildingXml.getUserCanChangeClimatePricePref() != null) {
                                newBuildings.setUserCanChangeClimatePricePref(Integer.valueOf(buildingXml.getUserCanChangeClimatePricePref()));
                            } else {
                                newBuildings.setUserCanChangeClimatePricePref(null);
                            }
                            if (buildingXml.getOwnerGroupID() != null) {
                                newBuildings.setOwnerGroupId(buildingXml.getOwnerGroupID().getValue());
                            }
                            if (buildingXml.getUsedByGroupID() != null) {
                                newBuildings.setUsedByGroupId(buildingXml.getUsedByGroupID().getValue());
                            }
                            if (buildingXml.getAgreementID() != null) {
                                ApplicationDAO applicationDAO = new ApplicationDAO();
                                Agreements agreement = (Agreements) applicationDAO.getAgreementsById(session, buildingXml.getAgreementID().getValue());
                                newBuildings.setAgreements(agreement);
                            }

                            if (buildingXml.getContainerID() != null) {
                                newBuildings.setContainerId(buildingXml.getContainerID().getValue());
                            }
                            if (buildingXml.getPropertyCode() != null) {
                                newBuildings.setPropertyCode(buildingXml.getPropertyCode());
                            }
                            if (buildingXml.getBuildingNumber() != null) {
                                newBuildings.setBuildingNumber(buildingXml.getBuildingNumber());
                            }
                            if (buildingXml.getBuildingCode() != null) {
                                newBuildings.setBuildingCode(buildingXml.getBuildingCode());
                            }
                            if ((Integer) buildingXml.getShareOfParent() != null) {
                                newBuildings.setShareOfParent(buildingXml.getShareOfParent());
                            }
                            if (buildingXml.getFloorLevel() != null) {
                                newBuildings.setFloorLevel(buildingXml.getFloorLevel());
                            }
                            if (buildingXml.getArea() != null) {
                                newBuildings.setArea(buildingXml.getArea());
                            }
                            if (buildingXml.getAreaUnit() != null) {
                                newBuildings.setAreaUnit(buildingXml.getAreaUnit());
                            }
                            if (buildingXml.getVolume() != null) {
                                newBuildings.setVolume(buildingXml.getVolume());
                            }
                            if (buildingXml.getVolumeUnit() != null) {
                                newBuildings.setVolumeUnit(buildingXml.getVolumeUnit());
                            }
                            if ((Integer) buildingXml.getRating() != null) {
                                newBuildings.setRating(buildingXml.getRating());
                            }
                            if ((Integer) buildingXml.getIsInEmergencyMode() != null) {
                                newBuildings.setIsInEmergencyMode(buildingXml.getIsInEmergencyMode());
                            }
                            if ((Integer) buildingXml.getIsNonSmoking() != null) {
                                newBuildings.setIsNonSmoking(buildingXml.getIsNonSmoking());
                            }
                            if ((Integer) buildingXml.getIsOpen() != null) {
                                newBuildings.setIsOpen(buildingXml.getIsOpen());
                            }
                            if ((Integer) buildingXml.getIsPublicAccess() != null) {
                                newBuildings.setIsPublicAccess(buildingXml.getIsPublicAccess());
                            }
                            if (buildingXml.getStreet1() != null) {
                                newBuildings.setStreet1(buildingXml.getStreet1());
                            }
                            if (buildingXml.getStreet2() != null) {
                                newBuildings.setStreet2(buildingXml.getStreet2());
                            }
                            if (buildingXml.getZipCode() != null) {
                                newBuildings.setZipCode(buildingXml.getZipCode());
                            }
                            if (buildingXml.getCity() != null) {
                                newBuildings.setCity(buildingXml.getCity());
                            }
                            if (buildingXml.getState() != null) {
                                newBuildings.setState(buildingXml.getState());
                            }
                            if (buildingXml.getRegion() != null) {
                                newBuildings.setRegion(buildingXml.getRegion());
                            }
                            if (buildingXml.getCountryID() != null) {
                                CountryDAO countryDAO = new CountryDAO();
                                Country country = countryDAO.getCountryById(Integer.valueOf(buildingXml.getCountryID().getValue()), session);
                                newBuildings.setCountry(country);
                            }
                            if (buildingXml.getLatitude() != null) {
                                newBuildings.setLatitude(buildingXml.getLatitude());
                            }
                            if (buildingXml.getLongitude() != null) {
                                newBuildings.setLongitude(buildingXml.getLongitude());
                            }
                            if (buildingXml.getAltitude() != null) {
                                newBuildings.setAltitude(buildingXml.getAltitude());
                            }
                            if (buildingXml.getLayoutImageURL() != null) {
                                newBuildings.setLayoutImageUrl(buildingXml.getLayoutImageURL());
                            }
                            if (buildingXml.getWebsiteURL() != null) {
                                newBuildings.setWebsiteUrl(buildingXml.getWebsiteURL());
                            }
                            if ((Integer) buildingXml.getClimatePricePreference() != null) {
                                newBuildings.setClimatePricePreference(buildingXml.getClimatePricePreference());
                            }

                            if (buildingXml.getEnergyMeterDeviceID() != null) {
                                newBuildings.setEnergyMeterDeviceId(buildingXml.getEnergyMeterDeviceID().getValue());
                            }
                            if (buildingXml.getWaterMeterDeviceID() != null) {
                                newBuildings.setWaterMeterDeviceId(buildingXml.getWaterMeterDeviceID().getValue());
                            }
                            if (buildingXml.getHeatMeterDeviceID() != null) {
                                newBuildings.setHeatMeterDeviceId(buildingXml.getHeatMeterDeviceID().getValue());
                            }
                            if (buildingXml.getGasMeterDeviceID() != null) {
                                newBuildings.setGasMeterDeviceId(buildingXml.getGasMeterDeviceID().getValue());
                            }
                            newBuildings.setLastUpdatedByUserId(sessions2.getUserId());
                            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                            newBuildings.setCreatedDate(gc.getTime());
                            newBuildings.setUpdatedDate(gc.getTime());
                            if (newBuildings != null) {
                                if (!homeDAO.saveOrUpdate(newBuildings, session)) {
                                    tingcoHome.getMsgStatus().setResponseCode(-1);
                                    tingcoHome.getMsgStatus().setResponseText("Error occurred while adding new Building");
                                    return tingcoHome;
                                }
                            }
                        }

                    } else {
                        tingcoHome.getMsgStatus().setResponseCode(-1);
                        tingcoHome.getMsgStatus().setResponseText("No Data In Post XMl");
                        return tingcoHome;
                    }
                } else {
                    tingcoHome.getMsgStatus().setResponseCode(-10);
                    tingcoHome.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoHome;
                }
            } else {
                tingcoHome.getMsgStatus().setResponseCode(-3);
                tingcoHome.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoHome;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoHome.getMsgStatus().setResponseCode(-1);
            tingcoHome.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoHome;
    }

    private TingcoHome getBuildingDetailsById(String buildingId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoHome tingcoHome = null;
        Session session = null;
        try {
            tingcoHome = tingcoHomeXML.buildTingcoHomeTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.BUILDINGS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object buildingObj = homeDAO.getBuildingsById(buildingId, session);
                    if (buildingObj == null) {
                        tingcoHome.getMsgStatus().setResponseCode(-1);
                        tingcoHome.getMsgStatus().setResponseText("No Data Found");
                        return tingcoHome;
                    } else {
                        Buildings buildings = (Buildings) buildingObj;
                        tingcoHome = tingcoHomeXML.buildGetBuildingByID(tingcoHome, buildings, countryId, session);
                    }
                } else {
                    tingcoHome.getMsgStatus().setResponseCode(-10);
                    tingcoHome.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoHome;
                }
            } else {
                tingcoHome.getMsgStatus().setResponseCode(-3);
                tingcoHome.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoHome;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoHome.getMsgStatus().setResponseCode(-1);
            tingcoHome.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoHome;
    }

    private TingcoHome deleteZoneRooms(String roomId, String zoneId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        Transaction tx = null;
        boolean hasPermission = false;
        TingcoHome tingcoHome = null;
        try {
            tingcoHome = tingcoHomeXML.buildTingcoHomeTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.ROOMS, TCMUtil.DELETE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    Object zoneRoomsObject = homeDAO.getZoneRoomsById(zoneId, roomId, session);
                    if (zoneRoomsObject != null) {
                        session.delete(zoneRoomsObject);
                        tx.commit();
                    } else {
                        tingcoHome.getMsgStatus().setResponseCode(-1);
                        tingcoHome.getMsgStatus().setResponseText("No data found");
                        return tingcoHome;
                    }
                } else {
                    tingcoHome.getMsgStatus().setResponseCode(-10);
                    tingcoHome.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoHome;
                }
            } else {
                tingcoHome.getMsgStatus().setResponseCode(-3);
                tingcoHome.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoHome;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoHome.getMsgStatus().setResponseCode(-1);
            tingcoHome.getMsgStatus().setResponseText("Error occured");
            return tingcoHome;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoHome;
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

    private TingcoHome updateRooms(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoHome tingcoHome = null;
        Session session = null;
        Transaction tx = null;
        try {
            tingcoHome = tingcoHomeXML.buildTingcoHomeTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.ROOMS, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    TingcoHome tingcoHomeJaxb = (TingcoHome) JAXBManager.getInstance().unMarshall(content, TingcoHome.class);
                    Room roomJaxb = tingcoHomeJaxb.getZones().getZone().get(0).getRoom().get(0);
                    Object roomsObject = session.get(Rooms.class, roomJaxb.getRoomID());
                    if (roomsObject != null) {
                        Rooms rooms = (Rooms) roomsObject;
                        rooms.setRoomName(roomJaxb.getRoomName());
                        if (roomJaxb.getRoomDescription() != null) {
                            rooms.setRoomDescription(roomJaxb.getRoomDescription());
                        } else {
                            rooms.setRoomDescription(null);
                        }

                        if (roomJaxb.getRoomNumber() != null) {
                            rooms.setRoomNumber(roomJaxb.getRoomNumber());
                        } else {
                            rooms.setRoomNumber(null);
                        }


                        rooms.setRoomTypes(new RoomTypes(roomJaxb.getRoomTypeID()));
                        rooms.setBuildings(new Buildings(roomJaxb.getBuilding().getBuildingID()));


                        if (roomJaxb.getContainerID() != null) {
                            rooms.setContainerId(roomJaxb.getContainerID().getValue());
                        } else {
                            rooms.setContainerId(null);
                        }

                        if ((Integer) roomJaxb.getIsNonSmoking() != null) {
                            rooms.setIsNonSmoking(roomJaxb.getIsNonSmoking());
                        } else {
                            rooms.setIsNonSmoking(null);
                        }

                        if ((Integer) roomJaxb.getIsPublicAccess() != null) {
                            rooms.setIsPublicAccess(roomJaxb.getIsPublicAccess());
                        } else {
                            rooms.setIsPublicAccess(null);
                        }

                        if ((Integer) roomJaxb.getIsDoNotDisturb() != null) {
                            rooms.setIsDoNotDisturb(roomJaxb.getIsDoNotDisturb());
                        } else {
                            rooms.setIsDoNotDisturb(null);
                        }

                        rooms.setOwnerGroupId(roomJaxb.getOwnerGroupID().getValue());

                        if (roomJaxb.getUsedByGroupID() != null) {
                            rooms.setUsedByGroupId(roomJaxb.getUsedByGroupID().getValue());
                        } else {
                            rooms.setUsedByGroupId(null);
                        }

                        rooms.setAgreements(new Agreements(roomJaxb.getAgreementID().getValue()));

                        if (roomJaxb.getFloorLevel() != null) {
                            rooms.setFloorLevel(roomJaxb.getFloorLevel());
                        } else {
                            rooms.setFloorLevel(null);
                        }
                        if (roomJaxb.getArea() != null) {
                            rooms.setArea(roomJaxb.getArea());
                        } else {
                            rooms.setArea(null);
                        }
                        if (roomJaxb.getAreaUnit() != null) {
                            rooms.setAreaUnit(roomJaxb.getAreaUnit());
                        } else {
                            rooms.setAreaUnit(null);
                        }
                        if (roomJaxb.getVolume() != null) {
                            rooms.setVolume(roomJaxb.getVolume());
                        } else {
                            rooms.setVolume(null);
                        }
                        if (roomJaxb.getVolumeUnit() != null) {
                            rooms.setVolumeUnit(roomJaxb.getVolumeUnit());
                        } else {
                            rooms.setVolumeUnit(null);
                        }
                        if (roomJaxb.getLayoutImageURL() != null) {
                            rooms.setLayoutImageUrl(roomJaxb.getLayoutImageURL());
                        } else {
                            rooms.setLayoutImageUrl(null);
                        }
                        if (roomJaxb.getRating() != null) {
                            rooms.setRating(roomJaxb.getRating().intValue());
                        } else {
                            rooms.setRating(null);
                        }
                        rooms.setLastUpdatedByUserId(sessions2.getUserId());
                        rooms.setUpdatedDate(currentDateTime.getTime());
                        session.saveOrUpdate(rooms);
                        tx.commit();
                    } else {
                        tingcoHome.getMsgStatus().setResponseCode(-1);
                        tingcoHome.getMsgStatus().setResponseText("Room not found");
                        return tingcoHome;
                    }
                } else {
                    tingcoHome.getMsgStatus().setResponseCode(-10);
                    tingcoHome.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoHome;
                }
            } else {
                tingcoHome.getMsgStatus().setResponseCode(-3);
                tingcoHome.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoHome;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
            tingcoHome.getMsgStatus().setResponseCode(-1);
            tingcoHome.getMsgStatus().setResponseText("Error occured");
            tx.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoHome;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.device;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.devicejaxb.TingcoDevice;
import se.info24.group.GroupDAO;
import se.info24.jaxbUtil.TingcoDeviceXML;
import se.info24.pojo.BillingCategories;
import se.info24.pojo.BillingCategoryTranslations;
import se.info24.pojo.Device;
import se.info24.pojo.DeviceManufacturers;
import se.info24.pojo.DeviceTypeBillingCategories;
import se.info24.pojo.DeviceTypeBillingCategoriesId;
import se.info24.pojo.DeviceTypeCommandTranslations;
import se.info24.pojo.DeviceTypeCommandTranslationsId;
import se.info24.pojo.DeviceTypeCommands;
import se.info24.pojo.DeviceTypes;
import se.info24.pojo.DeviceTypesInGroups;
import se.info24.pojo.DeviceTypesInGroupsId;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupTrusts;
import se.info24.pojo.PermissionTranslations;
import se.info24.pojo.UserRoleObjectPermissions2;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.Users2;
import se.info24.user.UserDAO;
import se.info24.user.User_LoginsResource;
import se.info24.util.RESTDoc;
import se.info24.util.HibernateUtil;
import se.info24.util.JAXBManager;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/devicetype")
public class DeviceTypesResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
    DeviceDAO deviceDAO = new DeviceDAO();
    UserDAO userDAO = new UserDAO();
    GroupDAO groupDAO = new GroupDAO();
//    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of DeviceTypesResource */
    public DeviceTypesResource() {
    }

    /**
     * DeviceType_Add
     * @param deviceTypeName
     * @param deviceTypeDesc
     * @param deviceManufacturerID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/add/rest/{rest}/devicetypename/{devicetypename}/devicetypedesc/{devicetypedesc}/devicemanufacturerid/{devicemanufacturerid}")
    @RESTDoc(methodName = "DeviceType_Add", desc = "Used to create a new DeviceType", req_Params = {"DeviceTypeName;String", "DeviceTypeDesc;String", "DeviceManufacturerID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoDevice getXml_Add(@PathParam("devicetypename") String deviceTypeName, @PathParam("devicetypedesc") String deviceTypeDesc, @PathParam("devicemanufacturerid") String deviceManufacturerID) {
        return createDeviceType(deviceTypeName, deviceTypeDesc, deviceManufacturerID);
    }

    /**
     * DeviceType_Add
     * @param deviceTypeName
     * @param deviceTypeDesc
     * @param deviceManufacturerID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/add/rest/{rest}/devicetypename/{devicetypename}/devicetypedesc/{devicetypedesc}/devicemanufacturerid/{devicemanufacturerid}")
    public TingcoDevice postXml_Add(@PathParam("devicetypename") String deviceTypeName, @PathParam("devicetypedesc") String deviceTypeDesc, @PathParam("devicemanufacturerid") String deviceManufacturerID) {
        return createDeviceType(deviceTypeName, deviceTypeDesc, deviceManufacturerID);
    }

    /**
     * DeviceType_Add
     * @param deviceTypeName
     * @param deviceTypeDesc
     * @param deviceManufacturerID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/add/json/{json}/devicetypename/{devicetypename}/devicetypedesc/{devicetypedesc}/devicemanufacturerid/{devicemanufacturerid}")
    public TingcoDevice getJson_Add(@PathParam("devicetypename") String deviceTypeName, @PathParam("devicetypedesc") String deviceTypeDesc, @PathParam("devicemanufacturerid") String deviceManufacturerID) {
        return createDeviceType(deviceTypeName, deviceTypeDesc, deviceManufacturerID);
    }

    /**
     * DeviceType_Add
     * @param deviceTypeName
     * @param deviceTypeDesc
     * @param deviceManufacturerID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/add/json/{json}/devicetypename/{devicetypename}/devicetypedesc/{devicetypedesc}/devicemanufacturerid/{devicemanufacturerid}")
    public TingcoDevice postJson_Add(@PathParam("devicetypename") String deviceTypeName, @PathParam("devicetypedesc") String deviceTypeDesc, @PathParam("devicemanufacturerid") String deviceManufacturerID) {
        return createDeviceType(deviceTypeName, deviceTypeDesc, deviceManufacturerID);
    }

    /**
     * DeviceType_Delete
     * @param deviceTypeID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/devicetypeid/{devicetypeid}")
    @RESTDoc(methodName = "DeviceType_Delete", desc = "To Delete a DeviceType", req_Params = {"DeviceTypeID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoDevice getXml_Delete(@PathParam("devicetypeid") String deviceTypeID) {
        return deleteDeviceTypes(deviceTypeID);
    }

    /**
     * DeviceType_Delete
     * @param deviceTypeID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/delete/json/{json}/devicetypeid/{devicetypeid}")
    public TingcoDevice getJson_Delete(@PathParam("devicetypeid") String deviceTypeID) {
        return deleteDeviceTypes(deviceTypeID);
    }

//    /**
//     * DeviceType_Delete
//     * @param deviceTypeID
//     * @return
//     */
//    @POST
//    @Produces("application/xml")
//    @Path("/delete/rest/{rest}/devicetypeid/{devicetypeid}")
//    public TingcoDevice postXml_Delete(@PathParam("devicetypeid") String deviceTypeID) {
//        return deleteDeviceTypes(deviceTypeID);
//    }

//    /**
//     * DeviceType_Delete
//     * @param deviceTypeID
//     * @return
//     */
//    @POST
//    @Produces("application/json")
//    @Path("/delete/json/{json}/devicetypeid/{devicetypeid}")
//    public TingcoDevice postJson_Delete(@PathParam("devicetypeid") String deviceTypeID) {
//        return deleteDeviceTypes(deviceTypeID);
//    }
    /**
     * DeviceType_GetInfo
     * @param deviceTypeID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/get/rest/{rest}/devicetypeid/{devicetypeid}")
    @RESTDoc(methodName = "DeviceType_GetInfo", desc = "To Get a DeviceType", req_Params = {"DeviceTypeID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoDevice getXml(@PathParam("devicetypeid") String deviceTypeID) {
        return getDeviceTypes(deviceTypeID);
    }

    /**
     * DeviceType_GetInfo
     * @param deviceTypeID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/get/rest/{rest}/devicetypeid/{devicetypeid}")
    public TingcoDevice postXml(@PathParam("devicetypeid") String deviceTypeID) {
        return getDeviceTypes(deviceTypeID);
    }

    /**
     * DeviceType_GetInfo
     * @param deviceTypeID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/get/json/{json}/devicetypeid/{devicetypeid}")
    public TingcoDevice getJson(@PathParam("devicetypeid") String deviceTypeID) {
        return getDeviceTypes(deviceTypeID);
    }

    /**
     * DeviceType_GetInfo
     * @param deviceTypeID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/get/json/{json}/devicetypeid/{devicetypeid}")
    public TingcoDevice postJson(@PathParam("devicetypeid") String deviceTypeID) {
        return getDeviceTypes(deviceTypeID);
    }

    /**
     * DeviceTypes_Update
     * @param deviceTypeID
     * @param deviceTypeName
     * @param deviceTypeDesc
     * @param deviceManufacturerID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/update/rest/{rest}/devicetypeid/{devicetypeid}{devicetypename:(/devicetypename/[^/]+?)?}{devicetypedesc:(/devicetypedesc/[^/]+?)?}{devicemanufacturerid:(/devicemanufacturerid/[^/]+?)?}")
    @RESTDoc(methodName = "DeviceTypes_Update", desc = "To Update a DeviceType", req_Params = {"DeviceTypeID;UUID"}, opt_Params = {"DeviceTypeName;String", "DeviceTypeDesc;String", "DeviceManufacturerID;UUID"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoDevice getXml_Update(@PathParam("devicetypeid") String deviceTypeID, @PathParam("devicetypename") String deviceTypeName, @PathParam("devicetypedesc") String deviceTypeDesc, @PathParam("devicemanufacturerid") String deviceManufacturerID) {
        return updateDeviceType(deviceTypeID, deviceTypeName, deviceTypeDesc, deviceManufacturerID);
    }

    /**
     * DeviceTypes_Update
     * @param deviceTypeID
     * @param deviceTypeName
     * @param deviceTypeDesc
     * @param deviceManufacturerID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/update/rest/{rest}/devicetypeid/{devicetypeid}{devicetypename:(/devicetypename/[^/]+?)?}{devicetypedesc:(/devicetypedesc/[^/]+?)?}{devicemanufacturerid:(/devicemanufacturerid/[^/]+?)?}")
    public TingcoDevice postXml_Update(@PathParam("devicetypeid") String deviceTypeID, @PathParam("devicetypename") String deviceTypeName, @PathParam("devicetypedesc") String deviceTypeDesc, @PathParam("devicemanufacturerid") String deviceManufacturerID) {
        return updateDeviceType(deviceTypeID, deviceTypeName, deviceTypeDesc, deviceManufacturerID);
    }

    /**
     * DeviceTypes_Update
     * @param deviceTypeID
     * @param deviceTypeName
     * @param deviceTypeDesc
     * @param deviceManufacturerID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/update/json/{json}/devicetypeid/{devicetypeid}{devicetypename:(/devicetypename/[^/]+?)?}{devicetypedesc:(/devicetypedesc/[^/]+?)?}{devicemanufacturerid:(/devicemanufacturerid/[^/]+?)?}")
    public TingcoDevice getJson_Update(@PathParam("devicetypeid") String deviceTypeID, @PathParam("devicetypename") String deviceTypeName, @PathParam("devicetypedesc") String deviceTypeDesc, @PathParam("devicemanufacturerid") String deviceManufacturerID) {
        return updateDeviceType(deviceTypeID, deviceTypeName, deviceTypeDesc, deviceManufacturerID);
    }

    /**
     * DeviceTypes_Update
     * @param deviceTypeID
     * @param deviceTypeName
     * @param deviceTypeDesc
     * @param deviceManufacturerID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/update/json/{json}/devicetypeid/{devicetypeid}{devicetypename:(/devicetypename/[^/]+?)?}{devicetypedesc:(/devicetypedesc/[^/]+?)?}{devicemanufacturerid:(/devicemanufacturerid/[^/]+?)?}")
    public TingcoDevice postJson_Update(@PathParam("devicetypeid") String deviceTypeID, @PathParam("devicetypename") String deviceTypeName, @PathParam("devicetypedesc") String deviceTypeDesc, @PathParam("devicemanufacturerid") String deviceManufacturerID) {
        return updateDeviceType(deviceTypeID, deviceTypeName, deviceTypeDesc, deviceManufacturerID);
    }

    /**
     * GetAllDeviceTypes
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/rest/{rest}{devicetypename:(/devicetypename/[^/]+?)?}{devicemanufacturername:(/devicemanufacturername/[^/]+?)?}{istcm:(/istcm/[^/]+?)?}")
    @RESTDoc(methodName = "GetAllDeviceTypes", desc = "To Get All DeviceTypes", req_Params = {"rest;string"}, opt_Params = {"devicetypename;string", "devicemanufacturername;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoDevice getXml(@PathParam("devicetypename") String deviceTypeName, @PathParam("devicemanufacturername") String deviceManufacturerName, @PathParam("istcm") String isTcm) {
        return getAllDeviceTypes(deviceTypeName, deviceManufacturerName, isTcm);
    }

//    /**
//     * GetAllDeviceTypes
//     * @return
//     */
//    @POST
//    @Produces("application/xml")
//    @Path("/rest/{rest}")
//    public TingcoDevice postXml() {
//        return getAllDeviceTypes();
//    }
    /**
     * GetAllDeviceTypes
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/json/{json}{devicetypename:(/devicetypename/[^/]+?)?}{devicemanufacturername:(/devicemanufacturername/[^/]+?)?}{istcm:(/istcm/[^/]+?)?}")
    public TingcoDevice getJson(@PathParam("devicetypename") String deviceTypeName, @PathParam("devicemanufacturername") String deviceManufacturerName, @PathParam("istcm") String isTcm) {
        return getAllDeviceTypes(deviceTypeName, deviceManufacturerName, isTcm);
    }

//    /**
//     * GetAllDeviceTypes
//     * @return
//     */
//    @POST
//    @Produces("application/json")
//    @Path("/json/{json}")
//    public TingcoDevice postJson() {
//        return getAllDeviceTypes();
//    }
    /**
     * GetDeviceTypesInGroups
     * @param groupID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getingroups/rest/{rest}/groupid/{groupid}")
    @RESTDoc(methodName = "GetDeviceTypesInGroups", desc = "To Get a DeviceTypesInGroups by GroupID", req_Params = {"GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoDevice getXml_GetInGroups(@PathParam("groupid") String groupID) {
        return getDeviceTypesInGroups(groupID);
    }

    /**
     * GetDeviceTypesInGroups
     * @param groupID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/getingroups/rest/{rest}/groupid/{groupid}")
    public TingcoDevice postXml_GetInGroups(@PathParam("groupid") String groupID) {
        return getDeviceTypesInGroups(groupID);
    }

    /**
     * GetDeviceTypesInGroups
     * @param groupID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getingroups/json/{json}/groupid/{groupid}")
    public TingcoDevice getJson_GetInGroups(@PathParam("groupid") String groupID) {
        return getDeviceTypesInGroups(groupID);
    }

    /**
     * GetDeviceTypesInGroups
     * @param groupID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/getingroups/json/{json}/groupid/{groupid}")
    public TingcoDevice postJson_GetInGroups(@PathParam("groupid") String groupID) {
        return getDeviceTypesInGroups(groupID);
    }

    /**
     * getdevicetype
     * @param groupID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/xml")
    @Path("/getdevicetype/rest/{rest}/groupid/{groupid}")
    @RESTDoc(methodName = "GetDeviceTypesInGroups", desc = "To Get a DeviceTypesInGroups by GroupID", req_Params = {"GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetDeviceType(@PathParam("groupid") String groupID) {
        return getDeviceType(groupID);
    }

    /**
     * getdevicetype
     * @param groupID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/json")
    @Path("/getdevicetype/json/{json}/groupid/{groupid}")
    public TingcoDevice getjson_GetDeviceType(@PathParam("groupid") String groupID) {
        return getDeviceType(groupID);
    }

    /**
     * getdevicetypesforTCMV3
     * @param groupID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/xml")
    @Path("/getdevicetypesforpopup/rest/{rest}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetDeviceTypesInGroups", desc = "To Get a DeviceTypesInGroups by GroupID", req_Params = {"GroupID;UUID"}, opt_Params = {"searchstring;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetDeviceTypesForPopup(@PathParam("groupid") String groupID, @PathParam("searchstring") String searchString) {
        return getDeviceTypesForPopup(groupID, searchString);
    }

    /**
     * getdevicetypesforTCMV3
     * @param groupID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/json")
    @Path("/getdevicetypesforpopup/json/{json}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoDevice getjson_GetDeviceTypesForPopup(@PathParam("groupid") String groupID, @PathParam("searchstring") String searchString) {
        return getDeviceTypesForPopup(groupID, searchString);
    }

    /**
     * GetDeviceTypesByGroupID
     * @param groupID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getdevicetypesbygroupid/rest/{rest}/groupid/{groupid}")
    @RESTDoc(methodName = "GetDeviceTypesInGroups", desc = "Get DeviceTypes by GroupID", req_Params = {"GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetDeviceTypesByGroupID(@PathParam("groupid") String groupID) {
        return getDeviceTypesByGroupID(groupID);
    }

    /**
     * GetDeviceTypesByGroupID
     * @param groupID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getdevicetypesbygroupid/json/{json}/groupid/{groupid}")
    @RESTDoc(methodName = "GetDeviceTypesInGroups", desc = "Get DeviceTypes by GroupID", req_Params = {"GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getJson_GetDeviceTypesByGroupID(@PathParam("groupid") String groupID) {
        return getDeviceTypesByGroupID(groupID);
    }

    /**
     * InsertDeviceTypeForGroup
     * @param groupID
     * @param deviceTypeId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/insertdevicetypeforgroup/rest/{rest}/groupid/{groupid}/devicetypeid/{devicetypeid}")
    @RESTDoc(methodName = "InsertDeviceTypeForGroup", desc = "Insert DeviceType For Group", req_Params = {"GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_InsertDeviceTypeForGroup(@PathParam("groupid") String groupID, @PathParam("devicetypeid") String deviceTypeId) {
        return insertDeviceTypeForGroup(groupID, deviceTypeId);
    }

    /**
     * InsertDeviceTypeForGroup
     * @param groupID
     * @param deviceTypeId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/insertdevicetypeforgroup/json/{json}/groupid/{groupid}/devicetypeid/{devicetypeid}")
    @RESTDoc(methodName = "InsertDeviceTypeForGroup", desc = "Insert DeviceType For Group", req_Params = {"GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getJson_InsertDeviceTypeForGroup(@PathParam("groupid") String groupID, @PathParam("devicetypeid") String deviceTypeId) {
        return insertDeviceTypeForGroup(groupID, deviceTypeId);
    }

    /**
     * DeleteDeviceTypeForGroup
     * @param groupID
     * @param deviceTypeId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deletedevicetypeforgroup/rest/{rest}/groupid/{groupid}/devicetypeid/{devicetypeid}")
    @RESTDoc(methodName = "DeleteDeviceTypeForGroup", desc = "Delete DeviceType For Group", req_Params = {"GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_DeleteDeviceTypeForGroup(@PathParam("groupid") String groupID, @PathParam("devicetypeid") String deviceTypeId) {
        return deleteDeviceTypeForGroup(groupID, deviceTypeId);
    }

    /**
     * DeleteDeviceTypeForGroup
     * @param groupID
     * @param deviceTypeId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deletedevicetypeforgroup/json/{json}/groupid/{groupid}/devicetypeid/{devicetypeid}")
    @RESTDoc(methodName = "DeleteDeviceTypeForGroup", desc = "Delete DeviceType For Group", req_Params = {"GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getJson_DeleteDeviceTypeForGroup(@PathParam("groupid") String groupID, @PathParam("devicetypeid") String deviceTypeId) {
        return deleteDeviceTypeForGroup(groupID, deviceTypeId);
    }

    @GET
    @Produces("application/xml")
    @Path("/adddevicetypes/rest/{rest}/devicetypename/{devicetypename}/groupid/{groupid}/devicemanufacturerid/{devicemanufacturerid}{devicetypedescription:(/devicetypedescription/[^/]+?)?}")
    @RESTDoc(methodName = "DeviceType_Add", desc = "Used to create a new DeviceType", req_Params = {"DeviceTypeName;String", "DeviceTypeDesc;String", "DeviceManufacturerID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoDevice getXml_AddDeviceTypes(@PathParam("devicetypename") String deviceTypeName, @PathParam("groupid") String groupId, @PathParam("devicemanufacturerid") String deviceManufacturerID, @PathParam("devicetypedescription") String deviceTypeDescription) {
        return addDeviceTypes(deviceTypeName, groupId, deviceManufacturerID, deviceTypeDescription);
    }

    @GET
    @Produces("application/json")
    @Path("/adddevicetypes/json/{json}/devicetypename/{devicetypename}/groupid/{groupid}/devicemanufacturerid/{devicemanufacturerid}{devicetypedescription:(/devicetypedescription/[^/]+?)?}")
    @RESTDoc(methodName = "DeviceType_Add", desc = "Used to create a new DeviceType", req_Params = {"DeviceTypeName;String", "DeviceTypeDesc;String", "DeviceManufacturerID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoDevice getJson_AddDeviceTypes(@PathParam("devicetypename") String deviceTypeName, @PathParam("groupid") String groupId, @PathParam("devicemanufacturerid") String deviceManufacturerID, @PathParam("devicetypedescription") String deviceTypeDescription) {
        return addDeviceTypes(deviceTypeName, groupId, deviceManufacturerID, deviceTypeDescription);
    }

    /**
     * GetAllDeviceTypeCommands
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getalldevicetypecommands/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetAllDeviceTypeCommands", desc = "GetAllDeviceTypeCommands", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetAllDeviceTypeCommands(@PathParam("countryid") int countryId) {
        return getAllDeviceTypeCommands(countryId);
    }

    /**
     * GetAllDeviceTypeCommands
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getalldevicetypecommands/json/{json}/countryid/{countryid}")
    public TingcoDevice getJson_GetAllDeviceTypeCommands(@PathParam("countryid") int countryId) {
        return getAllDeviceTypeCommands(countryId);
    }

    /**
     * GetUserRoleDeviceTypeCommandDetails
     * @param userRoleId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getuserroledevicetypecommanddetails/rest/{rest}/userroleid/{userroleid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetUserRoleDeviceTypeCommandDetails", desc = "GetUserRoleDeviceTypeCommandDetails", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetUserRoleDeviceTypeCommandDetails(@PathParam("userroleid") String userRoleId, @PathParam("countryid") int countryId) {
        return getUserRoleDeviceTypeCommandDetails(userRoleId, countryId);
    }

    /**
     * GetUserRoleDeviceTypeCommandDetails
     * @param userRoleId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getuserroledevicetypecommanddetails/json/{json}/userroleid/{userroleid}/countryid/{countryid}")
    public TingcoDevice getJson_GetUserRoleDeviceTypeCommandDetails(@PathParam("userroleid") String userRoleId, @PathParam("countryid") int countryId) {
        return getUserRoleDeviceTypeCommandDetails(userRoleId, countryId);
    }

    /**
     * GetBillingCategoriesByDeviceType
     * @param deviceTypeId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getbillingcategoriesbydevicetype/rest/{rest}/devicetypeid/{devicetypeid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetBillingCategoriesByDeviceType", desc = "Get BillingCategories By DeviceType", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetBillingCategoriesByDeviceType(@PathParam("devicetypeid") String deviceTypeId, @PathParam("countryid") int countryId) {
        return getBillingCategoriesByDeviceType(deviceTypeId, countryId);
    }

    /**
     * GetBillingCategoriesByDeviceType
     * @param deviceTypeId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getbillingcategoriesbydevicetype/json/{json}/devicetypeid/{devicetypeid}/countryid/{countryid}")
    public TingcoDevice getJson_GetBillingCategoriesByDeviceType(@PathParam("devicetypeid") String deviceTypeId, @PathParam("countryid") int countryId) {
        return getBillingCategoriesByDeviceType(deviceTypeId, countryId);
    }

    /**
     * GetBillingCategoriesListByDeviceType
     * @param deviceTypeId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getbillingcategorieslistbydevicetype/rest/{rest}/devicetypeid/{devicetypeid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetBillingCategoriesListByDeviceType", desc = "GetBillingCategoriesListByDeviceType", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetBillingCategoriesListByDeviceType(@PathParam("devicetypeid") String deviceTypeId, @PathParam("countryid") int countryId) {
        return getBillingCategoriesListByDeviceType(deviceTypeId, countryId);
    }

    /**
     * GetBillingCategoriesListByDeviceType
     * @param deviceTypeId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getbillingcategorieslistbydevicetype/json/{json}/devicetypeid/{devicetypeid}/countryid/{countryid}")
    public TingcoDevice getJson_GetBillingCategoriesListByDeviceType(@PathParam("devicetypeid") String deviceTypeId, @PathParam("countryid") int countryId) {
        return getBillingCategoriesListByDeviceType(deviceTypeId, countryId);
    }

    /**
     * GetAllBillingCategories
     * @param searchString
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getallbillingcategories/rest/{rest}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetAllBillingCategories", desc = "GetAllBillingCategories", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetAllBillingCategories(@PathParam("searchstring") String searchString, @PathParam("countryid") int countryId) {
        return getAllBillingCategories(searchString, countryId);
    }

    /**
     * GetAllBillingCategories
     * @param searchString
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getallbillingcategories/json/{json}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoDevice getJson_GetAllBillingCategories(@PathParam("searchstring") String searchString, @PathParam("countryid") int countryId) {
        return getAllBillingCategories(searchString, countryId);
    }

    /**
     * AddBillingCategoryToDeviceType
     * @param content
     * @return
     */
    @POST
    @Produces("application/xml")
    @Consumes("application/xml")
    @Path("/addbillingcategorytodevicetype/rest/{rest}")
    @RESTDoc(methodName = "AddBillingCategoryToDeviceType", desc = "AddBillingCategoryToDeviceType", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice getXml_AddBillingCategoryToDeviceType(String content) {
        return addBillingCategoryToDeviceType(content);
    }

    /**
     * AddBillingCategoryToDeviceType
     * @param content
     * @return
     */
    @POST
    @Produces("application/json")
    @Consumes("application/xml")
    @Path("/addbillingcategorytodevicetype/json/{json}")
    public TingcoDevice getJson_AddBillingCategoryToDeviceType(String content) {
        return addBillingCategoryToDeviceType(content);
    }

    /**
     * DeleteBillingCategory
     * @param deviceTypeId
     * @param billingCategoryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deletebillingcategory/rest/{rest}/devicetypeid/{devicetypeid}/billingcategoryid/{billingcategoryid}")
    @RESTDoc(methodName = "DeleteBillingCategory", desc = "DeleteBillingCategory", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_DeleteBillingCategory(@PathParam("devicetypeid") String deviceTypeId, @PathParam("billingcategoryid") String billingCategoryId) {
        return deleteBillingCategory(deviceTypeId, billingCategoryId);
    }

    /**
     * DeleteBillingCategory
     * @param deviceTypeId
     * @param billingCategoryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deletebillingcategory/json/{json}/devicetypeid/{devicetypeid}/billingcategoryid/{billingcategoryid}")
    public TingcoDevice getJson_DeleteBillingCategory(@PathParam("devicetypeid") String deviceTypeId, @PathParam("billingcategoryid") String billingCategoryId) {
        return deleteBillingCategory(deviceTypeId, billingCategoryId);
    }

    /**
     * AddDeviceTypeCommandsAndTranslations
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/adddevicetypecommandsandtranslations/rest/{rest}")
    @RESTDoc(methodName = "AddDeviceTypeCommandsAndTranslations", desc = "Used to Add DeviceTypeCommandsAndTranslations", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_addDeviceTypeCommandAndTranslations(String content) {
        return addDeviceTypeCommandAndTranslations(content);
    }

    /**
     * AddDeviceTypeCommandsAndTranslations
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/adddevicetypecommandsandtranslations/json/{json}")
    public TingcoDevice postJson_addDeviceTypeCommandAndTranslations(String content) {
        return addDeviceTypeCommandAndTranslations(content);
    }

    /**
     * UpdateDeviceTypeCommandsAndTranslations
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/updatedevicetypecommandsandtranslations/rest/{rest}")
    @RESTDoc(methodName = "UpdateDeviceTypeCommandsAndTranslations", desc = "Used to Update DeviceTypeCommandsAndTranslations", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_updateDeviceTypeCommandAndTranslations(String content) {
        return updateDeviceTypeCommandAndTranslations(content);
    }

    /**
     * UpdateDeviceTypeCommandsAndTranslations
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/updatedevicetypecommandsandtranslations/json/{json}")
    public TingcoDevice postJson_updateDeviceTypeCommandAndTranslations(String content) {
        return updateDeviceTypeCommandAndTranslations(content);
    }

    /**
     * UpdateDeviceTypeCommandTranslations
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/updatedevicetypecommandtranslations/rest/{rest}")
    @RESTDoc(methodName = "UpdateDeviceTypeCommandTranslations", desc = "Used to Update DeviceTypeCommandTranslations", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_updateDeviceTypeCommandTranslations(String content) {
        return updateDeviceTypeCommandTranslations(content);
    }

    /**
     * UpdateDeviceTypeCommandTranslations
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/updatedevicetypecommandtranslations/json/{json}")
    public TingcoDevice postJson_updateDeviceTypeCommandTranslations(String content) {
        return updateDeviceTypeCommandTranslations(content);
    }

    private TingcoDevice addDeviceTypeCommandAndTranslations(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        Transaction tx = null;
        TingcoDevice tingcoDevice = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    GregorianCalendar gc = new GregorianCalendar();
                    se.info24.devicejaxbPost.TingcoDevice tingcoDevices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    se.info24.devicejaxbPost.DeviceTypeCommands dtcjaxb = tingcoDevices.getDevices().getDevice().get(0).getDeviceTypeCommands().get(0);
                    se.info24.devicejaxbPost.DeviceTypeCommandTranslations dtctransjaxb = dtcjaxb.getDeviceTypeCommandTranslations().get(0);
                    DeviceTypeCommands deviceTypeCommands = new DeviceTypeCommands();
                    String deviceTypecommandId = UUID.randomUUID().toString();
                    deviceTypeCommands.setDeviceTypeCommandId(deviceTypecommandId);
                    if (dtcjaxb.getDeviceTypeCommandParentID() != null) {
                        deviceTypeCommands.setDeviceTypeCommandParentId(dtcjaxb.getDeviceTypeCommandParentID());
                    }
                    deviceTypeCommands.setDeviceTypeId(dtcjaxb.getDeviceTypeID());
                    deviceTypeCommands.setObjectCode(dtcjaxb.getObjectCode());
                    deviceTypeCommands.setDeviceDataItemId(dtcjaxb.getDeviceDataItemID());
                    if (dtcjaxb.getDataItemValue() != null) {
                        deviceTypeCommands.setDataItemValue(dtcjaxb.getDataItemValue());
                    }
                    if (dtcjaxb.getFlagUserCanSetValue() != null) {
                        deviceTypeCommands.setFlagUserCanSetValue(dtcjaxb.getFlagUserCanSetValue());
                    }
                    if (dtcjaxb.getFlagIsNumericValue() != null) {
                        deviceTypeCommands.setFlagIsNumericValue(dtcjaxb.getFlagIsNumericValue());
                    }
                    if (dtcjaxb.getFlagIsVisible() != null) {
                        deviceTypeCommands.setFlagIsVisible(dtcjaxb.getFlagIsVisible());
                    }
                    if (dtcjaxb.getFlagValueCanBeNull() != null) {
                        deviceTypeCommands.setFlagValueCanBeNull(dtcjaxb.getFlagValueCanBeNull());
                    }
                    if (dtcjaxb.getDisplayOrder() != null) {
                        deviceTypeCommands.setDisplayOrder(dtcjaxb.getDisplayOrder());
                    }
                    if (dtcjaxb.getMinNumericValue() != null) {
                        deviceTypeCommands.setMinNumericValue(new BigDecimal(dtcjaxb.getMinNumericValue()));
                    }
                    if (dtcjaxb.getMaxNumericValue() != null) {
                        deviceTypeCommands.setMaxNumericValue(new BigDecimal(dtcjaxb.getMaxNumericValue()));
                    }
                    if (dtcjaxb.getMaxCharsInValue() != null) {
                        deviceTypeCommands.setMaxCharsInValue(dtcjaxb.getMaxCharsInValue());
                    }
                    if (dtcjaxb.getValueRexExValidationString() != null) {
                        deviceTypeCommands.setValueRexExValidationString(dtcjaxb.getValueRexExValidationString());
                    }
                    deviceTypeCommands.setLastUpdatedByUserId(sessions2.getUserId());
                    deviceTypeCommands.setCreatedDate(gc.getTime());
                    deviceTypeCommands.setUpdatedDate(gc.getTime());
                    Set<DeviceTypeCommandTranslations> deviceTypeCommandTransset = new HashSet<DeviceTypeCommandTranslations>();
                    DeviceTypeCommandTranslations deviceTypeCommandTranslations = new DeviceTypeCommandTranslations();
                    deviceTypeCommandTranslations.setId(new DeviceTypeCommandTranslationsId(deviceTypecommandId, dtctransjaxb.getCountryID()));
                    deviceTypeCommandTranslations.setCommandName(dtctransjaxb.getDeviceTypeCommandName());
                    if (dtctransjaxb.getCommandDescription() != null) {
                        deviceTypeCommandTranslations.setCommandDescription(dtctransjaxb.getCommandDescription());
                    }
                    deviceTypeCommandTranslations.setCommandButtonText(dtctransjaxb.getCommandButtonText());
                    deviceTypeCommandTranslations.setLastUpdatedByUserId(sessions2.getUserId());
                    deviceTypeCommandTranslations.setCreatedDate(gc.getTime());
                    deviceTypeCommandTranslations.setUpdatedDate(gc.getTime());
                    deviceTypeCommandTransset.add(deviceTypeCommandTranslations);
                    deviceTypeCommands.setDeviceTypeCommandTranslationses(deviceTypeCommandTransset);
                    session.saveOrUpdate(deviceTypeCommands);
                    tx.commit();
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

    private TingcoDevice deleteBillingCategory(String deviceTypeId, String billingCategoryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
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
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object object = deviceDAO.getDeviceTypeBillingCategoriesByDeviceTypeIDAndBillingCategoryID(deviceTypeId, billingCategoryId, session);
                    if (object != null) {
                        DeviceTypeBillingCategories billingCategories = (DeviceTypeBillingCategories) object;
                        if (!deviceDAO.deleteDeviceTypeBillingCategories(billingCategories, session)) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error Occured while delete");
                            return tingcoDevice;
                        }
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
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error Occured");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice addBillingCategoryToDeviceType(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
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
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    se.info24.devicejaxbPost.TingcoDevice tingcoDeviceXml = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    for (se.info24.devicejaxbPost.Device deviceXml : tingcoDeviceXml.getDevices().getDevice()) {
                        if (deviceXml.getDeviceTypeID() != null && deviceXml.getDeviceTypeID().getValue() != null) {
                            String deviceTypeID = deviceXml.getDeviceTypeID().getValue();
                            Object dt = deviceDAO.getDeviceTypesById(deviceTypeID, session);
                            if (dt == null) {
                                DeviceTypes deviceTypes = (DeviceTypes) dt;
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No DeviceType Found For Given Input");
                                return tingcoDevice;
                            }
                            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                            List<DeviceTypeBillingCategories> newDeviceTypeBillingCategorieses = new ArrayList<DeviceTypeBillingCategories>();
                            for (se.info24.devicejaxbPost.BillingCategories bcXml : deviceXml.getBillingCategories()) {
                                if (bcXml.getBillingCategoryID() != null) {
                                    List<BillingCategories> billingCategories = deviceDAO.getBillingCategorieses(session, bcXml.getBillingCategoryID());
                                    if (!billingCategories.isEmpty()) {
                                        Object deviceTypeBillingCategoriesTemp = deviceDAO.getDeviceTypeBillingCategoriesByDeviceTypeIDAndBillingCategoryID(deviceTypeID, bcXml.getBillingCategoryID(), session);
                                        if (deviceTypeBillingCategoriesTemp != null) {
                                            continue;
                                        }
                                        DeviceTypeBillingCategories deviceTypeBillingCategories = new DeviceTypeBillingCategories();
                                        deviceTypeBillingCategories.setId(new DeviceTypeBillingCategoriesId(deviceTypeID, bcXml.getBillingCategoryID()));
                                        /*deviceTypeBillingCategories.setDeviceTypes(deviceTypes);
                                        deviceTypeBillingCategories.setBillingCategories(billingCategories.get(0));*/
                                        deviceTypeBillingCategories.setLastUpdatedByUserId(sessions2.getUserId());
                                        deviceTypeBillingCategories.setCreatedDate(gc.getTime());
                                        deviceTypeBillingCategories.setUpdatedDate(gc.getTime());
                                        newDeviceTypeBillingCategorieses.add(deviceTypeBillingCategories);
                                    }
                                }
                            }
                            if (!newDeviceTypeBillingCategorieses.isEmpty()) {
                                if (!deviceDAO.saveDeviceTypeBillingCategories(session, newDeviceTypeBillingCategorieses)) {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("Error Occured While Adding Data");
                                    return tingcoDevice;
                                }
                            }
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("No DeviceType Found in Input");
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
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error Occured");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getAllBillingCategories(String searchString, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
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
                            break;
                        }
                    }
                }
                if (searchString.equals("")) {
                    searchString = null;
                } else {
                    searchString = searchString.split("/")[2];
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Users2 user = userDAO.getUserById(sessions2.getUserId(), session);
                    List<GroupTrusts> groupTrusts = groupDAO.getGroupTrustByGroupID(user.getGroupId(), session);
                    Set<String> groupIdsList = groupDAO.getGroupIdsList(user.getGroupId(), groupTrusts);
                    List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupIdsList), 2000);
                    List<BillingCategories> billingCategorieses = new ArrayList<BillingCategories>();
                    for (List<String> list : listsplit) {
                        List<BillingCategories> temp = deviceDAO.getBillingCategoriesByGroupIDs(list, session);
                        billingCategorieses.addAll(temp);
                    }
                    if (billingCategorieses.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                        return tingcoDevice;
                    }
                    Set<String> categoryIDs = new HashSet<String>();
                    for (BillingCategories bc : billingCategorieses) {
                        categoryIDs.add(bc.getBillingCategoryId());
                    }
                    List<BillingCategoryTranslations> billingCategoryTranslationses = new ArrayList<BillingCategoryTranslations>();
                    if (searchString != null) {
                        if (TCMUtil.isValidUUID(searchString)) {
                            billingCategoryTranslationses.addAll(deviceDAO.getBillingCategoryTranslations(session, searchString, countryId));
                        } else {
                            billingCategoryTranslationses.addAll(deviceDAO.getBillingCategoryTranslationsBySearchString(categoryIDs, countryId, searchString, session));
                        }
                    } else {
                        billingCategoryTranslationses.addAll(deviceDAO.getBillingCategoryTranslationsByIDs(categoryIDs, countryId, session));
                    }
                    tingcoDevice = tingcoDeviceXML.buildGetBillingCategoriesByDeviceType(tingcoDevice, billingCategoryTranslationses);
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
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getBillingCategoriesListByDeviceType(String deviceTypeId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
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
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<DeviceTypeBillingCategories> deviceTypeBillingCategorieses = deviceDAO.getDeviceTypeBillingCategoriesByDeviceTypeID(deviceTypeId, session);
                    if (deviceTypeBillingCategorieses.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                        return tingcoDevice;
                    } else {
                        Set<String> billingCategoryIDs = new HashSet<String>();
                        for (DeviceTypeBillingCategories dtbc : deviceTypeBillingCategorieses) {
                            billingCategoryIDs.add(dtbc.getId().getBillingCategoryId());
                        }
                        //
                        Users2 user = userDAO.getUserById(sessions2.getUserId(), session);
                        List<GroupTrusts> groupTrusts = groupDAO.getGroupTrustByGroupID(user.getGroupId(), session);
                        Set<String> groupIdsList = groupDAO.getGroupIdsList(user.getGroupId(), groupTrusts);
                        List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupIdsList), 2000);
                        List<BillingCategories> billingCategorieses = new ArrayList<BillingCategories>();
                        for (List<String> list : listsplit) {
                            List<BillingCategories> temp = deviceDAO.getBillingCategoriesByBillingCategoryIDAndGroupID(billingCategoryIDs, list, session);
                            billingCategorieses.addAll(temp);
                        }
                        if (billingCategorieses.isEmpty()) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                            return tingcoDevice;
                        }
                        /**
                         * TOP 200
                         */
                        Set<String> resultIDs = new HashSet<String>();
                        for (BillingCategories bc : billingCategorieses) {
                            resultIDs.add(bc.getBillingCategoryId());
                        }
                        List<BillingCategories> billingCategoriesesFiltered = deviceDAO.getBillingCategoriesByBillingCategoryID(resultIDs, session, 200);
                        //
                        Set<String> billingCategoryID2s = new HashSet<String>();
                        List<String> groupIDs = new ArrayList<String>();
                        for (BillingCategories bc : billingCategoriesesFiltered) {
                            billingCategoryID2s.add(bc.getBillingCategoryId());
                            groupIDs.add(bc.getGroupId());
                        }
                        List<BillingCategoryTranslations> billingCategoryTranslationses = deviceDAO.getBillingCategoryTranslationsByIDs(billingCategoryID2s, countryId, session);
                        List<GroupTranslations> groupTranslationses = groupDAO.getGroupTranslationsById(groupIDs, countryId, session);
                        tingcoDevice = tingcoDeviceXML.buildGetBillingCategoriesByDeviceType(tingcoDevice, billingCategoryTranslationses, billingCategoriesesFiltered, groupTranslationses);

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
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getBillingCategoriesByDeviceType(String deviceTypeId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
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
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<DeviceTypeBillingCategories> deviceTypeBillingCategorieses = deviceDAO.getDeviceTypeBillingCategoriesByDeviceTypeID(deviceTypeId, session);
                    if (deviceTypeBillingCategorieses.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                        return tingcoDevice;
                    } else {
                        Set<String> billingCategoryIDs = new HashSet<String>();
                        for (DeviceTypeBillingCategories dtbc : deviceTypeBillingCategorieses) {
                            billingCategoryIDs.add(dtbc.getId().getBillingCategoryId());
                        }
                        List<BillingCategoryTranslations> billingCategoryTranslationses = deviceDAO.getBillingCategoryTranslationsByIDs(billingCategoryIDs, countryId, session);

                        tingcoDevice = tingcoDeviceXML.buildGetBillingCategoriesByDeviceType(tingcoDevice, billingCategoryTranslationses);
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
            tingcoDevice.getMsgStatus().setResponseText("Error Occured While Inserting the DeviceTypesInGroups");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getUserRoleDeviceTypeCommandDetails(String userRoleId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.ROLES)) {
                    ArrayList<String> operations = ht.get(TCMUtil.ROLES);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();

                    List<UserRoleObjectPermissions2> userRoleObjectPermissions2s = deviceDAO.getUserRoleObjectPermissions2ByUserRoleId(session, userRoleId);
                    if (userRoleObjectPermissions2s.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                        return tingcoDevice;
                    } else {
                        List<String> deviceTypeCommandIDs = new ArrayList<String>();
                        List<String> permissionIDs = new ArrayList<String>();
                        for (UserRoleObjectPermissions2 urop : userRoleObjectPermissions2s) {
                            deviceTypeCommandIDs.add(urop.getId().getObjectId());
                            permissionIDs.add(urop.getId().getPermissionId());
                        }
                        List<DeviceTypeCommandTranslations> commandTranslationses = deviceDAO.getDeviceTypeCommandTranslations(session, deviceTypeCommandIDs, countryId);
                        List<PermissionTranslations> permissionTranslationses = deviceDAO.getPermissionTranslationsByIdsAndCountry(session, permissionIDs, countryId);

                        List<DeviceTypeCommands> deviceTypeCommandses = deviceDAO.getDeviceTypeCommandByIDS(new HashSet<String>(deviceTypeCommandIDs), session);
                        Set<String> deviceTypeIDs = new HashSet<String>();
                        for (DeviceTypeCommands dtc : deviceTypeCommandses) {
                            deviceTypeIDs.add(dtc.getDeviceTypeId());
                        }
                        if (deviceTypeIDs.isEmpty()) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                            return tingcoDevice;
                        }
                        List<DeviceTypes> deviceTypeses = deviceDAO.getAllDeviceTypes(deviceTypeIDs, session);

                        tingcoDevice = tingcoDeviceXML.buildGetUserRoleDeviceTypeCommandDetails(tingcoDevice, commandTranslationses, permissionTranslationses, deviceTypeses, userRoleObjectPermissions2s, deviceTypeCommandses);
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
            tingcoDevice.getMsgStatus().setResponseText("Error Occured While Inserting the DeviceTypesInGroups");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getAllDeviceTypeCommands(int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.ROLES)) {
                    ArrayList<String> operations = ht.get(TCMUtil.ROLES);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<DeviceTypeCommands> deviceTypeCommandses = deviceDAO.getAllDeviceTypeCommands(session);
                    List<String> deviceTypeCommandIDs = new ArrayList<String>();
                    Set<String> deviceTypeIDs = new HashSet<String>();
                    for (DeviceTypeCommands dtc : deviceTypeCommandses) {
                        deviceTypeCommandIDs.add(dtc.getDeviceTypeCommandId());
                        deviceTypeIDs.add(dtc.getDeviceTypeId());
                    }
                    List<DeviceTypeCommandTranslations> dtcts = deviceDAO.getDeviceTypeCommandTranslations(session, deviceTypeCommandIDs, countryId);
                    List<DeviceTypes> dts = deviceDAO.getAllDeviceTypes(deviceTypeIDs, session);

                    tingcoDevice = tingcoDeviceXML.buildGetAllDeviceTypeCommands(tingcoDevice, deviceTypeCommandses, dtcts, dts);

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
            tingcoDevice.getMsgStatus().setResponseText("Error Occured While Inserting the DeviceTypesInGroups");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice addDeviceTypes(String deviceTypeName, String groupId, String deviceManufacturerID, String deviceTypeDescription) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
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
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    DeviceTypes deviceTypes = new DeviceTypes();
                    if (!deviceTypeDescription.equals("")) {
                        deviceTypeDescription = deviceTypeDescription.split("/")[2];
                        deviceTypes.setDeviceTypeDescription(deviceTypeDescription);
                    } else {
                        deviceTypeDescription = null;
                    }
                    String deviceTypeId = UUID.randomUUID().toString();
                    deviceTypes.setDeviceTypeId(deviceTypeId);
                    deviceTypes.setDeviceTypeName(deviceTypeName);
                    deviceTypes.setDeviceManufacturers(new DeviceManufacturers(deviceManufacturerID));
                    deviceTypes.setUserId(sessions2.getUserId());
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    deviceTypes.setCreatedDate(gc.getTime());
                    deviceTypes.setUpdatedDate(gc.getTime());

                    DeviceTypesInGroups deviceTypesInGroups = new DeviceTypesInGroups();
                    DeviceTypesInGroupsId id = new DeviceTypesInGroupsId();
                    id.setDeviceTypeId(deviceTypeId);
                    id.setGroupId(groupId);
                    deviceTypesInGroups.setId(id);
                    deviceTypesInGroups.setLastUpdatedByUserId(sessions2.getUserId());
                    deviceTypesInGroups.setUpdatedDate(gc.getTime());
                    deviceTypesInGroups.setCreatedDate(gc.getTime());

                    if (!deviceDAO.addDeviceTypes(deviceTypesInGroups, deviceTypes, session)) {
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
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error Occured While Inserting the DeviceTypesInGroups");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice deleteDeviceTypeForGroup(String groupID, String deviceTypeId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        DeviceTypesInGroups deviceTypesInGroups = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupID.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("GroupID is should not be empty");
                    return tingcoDevice;
                }
                if (deviceTypeId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceTypeId is should not be empty");
                    return tingcoDevice;
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
                    deviceTypesInGroups = deviceDAO.getDeviceTypesInGroupsById(session, groupID, deviceTypeId);
                    if (deviceTypesInGroups == null) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoDevice;
                    }
                    try {
                        if (deviceTypesInGroups != null) {
                            session.beginTransaction();
                            session.delete(deviceTypesInGroups);
                            session.getTransaction().commit();
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                            return tingcoDevice;
                        }
                    } catch (Exception e) {
                        if (session.getTransaction().wasCommitted()) {
                            session.getTransaction().rollback();
                        }
                        TCMUtil.exceptionLog(getClass().getName(), e);
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Error occurred");
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
            tingcoDevice.getMsgStatus().setResponseText("Error Occured While Inserting the DeviceTypesInGroups");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getDeviceTypesForPopup(String groupID, String searchString) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupID.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("GroupID is should not be empty");
                    return tingcoDevice;
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
//                    List<DeviceTypesInGroups> dtigList = session.getNamedQuery("getDeviceHistoryDataItemsById").setString("groupId", groupID).list();
//                    if (dtigList != null && dtigList.size() > 0) {
//                        Set<String> deviceTypeIds = new HashSet<String>();
//                        for (DeviceTypesInGroups dtig : dtigList) {
//                            deviceTypeIds.add(dtig.getId().getDeviceTypeId());
//                        }
//                        List<DeviceTypes> typesList = session.getNamedQuery("getDeviceTypesByIdsList").setParameterList("deviceTypeId", deviceTypeIds).setMaxResults(200).list();
//                        tingcoDevice = tingcoDeviceXML.buildDeviceTypesByGroupId(tingcoDevice, typesList, new HashMap<String, Users2>());
//                        return tingcoDevice;
//                    } else {
//                        tingcoDevice.getMsgStatus().setResponseCode(-1);
//                        tingcoDevice.getMsgStatus().setResponseText("No DeviceTypes Found with Given GroupID");
//                        return tingcoDevice;
//                    }
                    if (searchString.equals("")) {
                        searchString = null;
                    } else {
                        searchString = searchString.split("/")[2];
                    }
                    List<GroupTrusts> groupTrusts = groupDAO.getGroupTrustByGroupID(groupID, session);
                    Set<String> groupIdsList = groupDAO.getGroupIdsList(groupID, groupTrusts);
                    List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupIdsList), 2000);
                    List<DeviceTypesInGroups> deviceTypesInGroups = new ArrayList<DeviceTypesInGroups>();
                    for (List<String> list : listsplit) {
                        List<DeviceTypesInGroups> deviceTypesInGroupsTemp = deviceDAO.getDeviceTypesInGroupsByGroupIds(new HashSet(list), session);
                        deviceTypesInGroups.addAll(deviceTypesInGroupsTemp);
                    }

                    Set<String> deviceTypeIds = deviceDAO.getDeviceTypeIds(deviceTypesInGroups);
                    List<DeviceTypes> typesList = new ArrayList<DeviceTypes>();
                    if (searchString == null) {
                        typesList = deviceDAO.getDeviceTypes(deviceTypeIds, session);
                    } else {
                        typesList = deviceDAO.getDeviceTypesByIdsAndDeviceTypeName(deviceTypeIds, searchString, session);
                    }
                    if (!typesList.isEmpty()) {
                        tingcoDevice = tingcoDeviceXML.buildDeviceTypesByGroupId(tingcoDevice, typesList);
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
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error Occured While Loading the DeviceTypesInGroups");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoDevice insertDeviceTypeForGroup(String groupID, String deviceTypeId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        DeviceTypesInGroups deviceTypesInGroups = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupID.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("GroupID is should not be empty");
                    return tingcoDevice;
                }
                if (deviceTypeId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceTypeId is should not be empty");
                    return tingcoDevice;
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
                    GregorianCalendar gc = new GregorianCalendar();
                    deviceTypesInGroups = deviceDAO.getDeviceTypesInGroupsById(session, groupID, deviceTypeId);
                    if (deviceTypesInGroups != null) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Already Exists");
                        return tingcoDevice;
                    }
                    deviceTypesInGroups = new DeviceTypesInGroups();
                    deviceTypesInGroups.setId(new DeviceTypesInGroupsId(deviceTypeId, groupID));
                    deviceTypesInGroups.setCreatedDate(gc.getTime());
                    deviceTypesInGroups.setUpdatedDate(gc.getTime());
                    deviceTypesInGroups.setLastUpdatedByUserId(sessions2.getUserId());
                    try {
                        session.beginTransaction();
                        session.save(deviceTypesInGroups);
                        session.getTransaction().commit();
                    } catch (Exception e) {
                        if (session.getTransaction().wasCommitted()) {
                            session.getTransaction().rollback();
                        }
                        TCMUtil.exceptionLog(getClass().getName(), e);
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Error Occured While Inserting");
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
            tingcoDevice.getMsgStatus().setResponseText("Error Occured While Inserting the DeviceTypesInGroups");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getDeviceTypesByGroupID(String groupID) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupID.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("GroupID is should not be empty");
                    return tingcoDevice;
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
                    List<DeviceTypesInGroups> dtigList = session.getNamedQuery("getDeviceHistoryDataItemsById").setString("groupId", groupID).list();
                    if (dtigList != null && dtigList.size() > 0) {
//                        Set<String> deviceTypeIds = new HashSet<String>();
//                        for (DeviceTypesInGroups dtig : dtigList) {
//                            deviceTypeIds.add(dtig.getId().getDeviceTypeId());
//                        }
                        Set<String> deviceTypeIds = deviceDAO.getDeviceTypeIds(dtigList);
                        List<DeviceTypes> typesList = deviceDAO.getDeviceTypes(deviceTypeIds, session);
                        tingcoDevice = tingcoDeviceXML.buildDeviceTypesByGroupId(tingcoDevice, typesList);
                        return tingcoDevice;
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No DeviceTypes Found with Given GroupID");
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
            tingcoDevice.getMsgStatus().setResponseText("Error Occured While Loading the DeviceTypesInGroups");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoDevice createDeviceType(String deviceTypeName, String deviceTypeDesc, String deviceManufacturerID) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceTypeName == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceTypeName should not be empty");
                    return tingcoDevice;
                } else if (deviceTypeDesc == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceTypeDesc should not be empty");
                    return tingcoDevice;
                } else if (deviceManufacturerID == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceManufacturerID should not be empty");
                    return tingcoDevice;
                }

                //   Hashtable<String,ArrayList> ht = (Hashtable<String, ArrayList>) request.getSession(true).getAttribute(request.getSession(true).getId());
//            if (ht.containsKey(TCMUtil.DEVICE)) {
//                ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
//                for(int i=0;i<operations.size();i++){
//                    if(operations.get(i).equalsIgnoreCase(TCMUtil.ADD)){
                hasPermission = true;
//                    }
//                }
//            }

                if (hasPermission) {
                    UserSessions2 userSessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                    DeviceTypes deviceTypes = new DeviceTypes();
                    String deviceTypeId = UUID.randomUUID().toString();
                    deviceTypes.setDeviceTypeId(deviceTypeId);
                    deviceTypes.setDeviceTypeName(deviceTypeName);
                    deviceTypes.setDeviceTypeDescription(deviceTypeDesc);
                    deviceTypes.setDeviceManufacturers(new DeviceManufacturers(deviceManufacturerID));
                    deviceTypes.setUserId(userSessions2.getUserId());
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    deviceTypes.setCreatedDate(gc.getTime());
                    deviceTypes.setUpdatedDate(gc.getTime());
                    session = HibernateUtil.getSessionFactory().openSession();
                    if (deviceDAO.saveDeviceTypes(deviceTypes, session)) {
                        return tingcoDevice;
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Error Occured while Saving DeviceTypes");
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

    private TingcoDevice deleteDeviceTypes(String deviceTypeID) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        Transaction tx = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.DELETE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    List<Device> deviceList = deviceDAO.getDeviceByDeviceTypeId(deviceTypeID, session);
//                    DeviceTypes deviceTypes = deviceDAO.getDeviceTypesById(deviceTypeID, session);
//                    if (deviceTypes != null) {
//                        if (deviceDAO.removeDeviceType(deviceTypes, session)) {
//                            return tingcoDevice;
                    if (deviceList.isEmpty()) {
                        Object obj = session.get(DeviceTypes.class, deviceTypeID);
                        session.delete(obj);
                        tx.commit();
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Device type cannot be deleted since it is still used by some Devices");
                        return tingcoDevice;
                    }
//                         } else {
//                            tingcoDevice.getMsgStatus().setResponseCode(-1);
//                            tingcoDevice.getMsgStatus().setResponseText("Error Occured while Deleting DeviceTypes");
//                            return tingcoDevice;
//                        }
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

    private TingcoDevice getDeviceTypes(String deviceTypeID) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceTypeID == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceTypeID is should not be empty");
                    return tingcoDevice;
                }
                hasPermission = true;
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object dt = deviceDAO.getDeviceTypesById(deviceTypeID, session);
                    if (dt != null) {
                        DeviceTypes deviceTypes = (DeviceTypes) dt;
                        ArrayList<DeviceTypes> typesList = new ArrayList<DeviceTypes>();
                        typesList.add(deviceTypes);
                        Users2 user2 = userDAO.getUserById(deviceTypes.getUserId(), session);
                        HashMap<String, Users2> userMap = new HashMap<String, Users2>();
                        userMap.put(deviceTypes.getUserId(), user2);
                        tingcoDevice = tingcoDeviceXML.buildDeviceTypes(tingcoDevice, typesList, userMap, null);
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No DeviceTypes Found");
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

    private TingcoDevice updateDeviceType(String deviceTypeId, String deviceTypeName, String deviceTypeDesc, String deviceManufacturerId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceTypeId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceTypeID is should not be empty");
                    return tingcoDevice;
                }

                if (deviceTypeName.equals("")) {
                    deviceTypeName = null;
                } else {
                    deviceTypeName = deviceTypeName.split("/")[2];
                }

                if (deviceTypeDesc.equals("")) {
                    deviceTypeDesc = null;
                } else {
                    deviceTypeDesc = deviceTypeDesc.split("/")[2];
                }

                if (deviceManufacturerId.equals("")) {
                    deviceManufacturerId = null;
                } else {
                    deviceManufacturerId = deviceManufacturerId.split("/")[2];
                }

                if (deviceTypeName == null && deviceTypeDesc == null && deviceManufacturerId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("Alleast One Value Should Present");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.UPDATE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object dt = deviceDAO.getDeviceTypesById(deviceTypeId, session);

                    if (dt != null) {
                        DeviceTypes deviceTypes = (DeviceTypes) dt;
                        deviceTypes.setUserId(sessions2.getUserId());
                        if (deviceTypeName != null) {
                            deviceTypes.setDeviceTypeName(deviceTypeName);
                        }
                        if (deviceTypeDesc != null) {
                            deviceTypes.setDeviceTypeDescription(deviceTypeDesc);
                        } else {
                            deviceTypes.setDeviceTypeDescription(null);
                        }

                        if (deviceManufacturerId != null) {
                            deviceTypes.setDeviceManufacturers(new DeviceManufacturers(deviceManufacturerId));
                        }

                        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                        deviceTypes.setUpdatedDate(gc.getTime());
                        if (deviceDAO.saveDeviceTypes(deviceTypes, session)) {
                            return tingcoDevice;
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error Occured while Updating DeviceTypes");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("DeviceType Not Found");
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

    private TingcoDevice getAllDeviceTypes(String deviceTypeName, String deviceManufacturerName, String isTcm) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (!deviceTypeName.equalsIgnoreCase("")) {
                    deviceTypeName = deviceTypeName.split("/")[2];
                } else {
                    deviceTypeName = null;
                }
                if (!deviceManufacturerName.equalsIgnoreCase("")) {
                    deviceManufacturerName = deviceManufacturerName.split("/")[2];
                } else {
                    deviceManufacturerName = null;
                }
                if (!isTcm.equalsIgnoreCase("")) {
                    isTcm = isTcm.split("/")[2];
                } else {
                    isTcm = null;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<DeviceTypes> deviceTypeList = deviceDAO.getDeviceTypeDetails(deviceTypeName, deviceManufacturerName, session);
                    if (!deviceTypeList.isEmpty()) {
                        HashMap<String, Users2> userMap = new HashMap<String, Users2>();
                        for (DeviceTypes deviceTypes : deviceTypeList) {
                            if (!userMap.containsKey(deviceTypes.getUserId())) {
                                Users2 user2 = userDAO.getUserById(deviceTypes.getUserId(), session);
                                userMap.put(deviceTypes.getUserId(), user2);
                            }
                        }
                        tingcoDevice = tingcoDeviceXML.buildDeviceTypes(tingcoDevice, deviceTypeList, userMap, isTcm);
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

    private TingcoDevice getDeviceTypesInGroups(String groupId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("GroupID is should not be empty");
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

                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<DeviceTypesInGroups> dtigList = deviceDAO.getDeviceTypesInGroupsByGroupId(groupId, session);
                    if (dtigList != null && dtigList.size() > 0) {
                        Set<String> deviceTypeIds = new HashSet<String>();
                        ArrayList<DeviceTypes> typesList = new ArrayList<DeviceTypes>();
                        Object dts = null;
                        for (DeviceTypesInGroups dtig : dtigList) {
                            deviceTypeIds.add(dtig.getId().getDeviceTypeId());
                        }
                        for (String deviceTypeId : deviceTypeIds) {
                            dts = deviceDAO.getDeviceTypesById(deviceTypeId, session);
                            if (dts != null) {
                                DeviceTypes deviceTypes = (DeviceTypes) dts;
                                typesList.add(deviceTypes);
                            }
                        }
                        tingcoDevice = tingcoDeviceXML.buildDeviceTypes(tingcoDevice, typesList, new HashMap<String, Users2>(), null);
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No DeviceTypes Found with Given GroupID");
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
            tingcoDevice.getMsgStatus().setResponseText("Error Occured While Loading the DeviceTypesInGroups");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    public TingcoDevice getDeviceType(String groupId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = true;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("GroupID is should not be empty");
                    return tingcoDevice;
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<DeviceTypesInGroups> dtigList = session.getNamedQuery("getDeviceHistoryDataItemsById").setString("groupId", groupId).list();
                    if (dtigList != null && dtigList.size() > 0) {
                        Set<String> deviceTypeIds = new HashSet<String>();
//                        ArrayList<DeviceTypes> typesList = new ArrayList<DeviceTypes>();
//                        DeviceTypes dts = null;
                        for (DeviceTypesInGroups dtig : dtigList) {
                            deviceTypeIds.add(dtig.getId().getDeviceTypeId());
                        }
                        List<DeviceTypes> typesList = session.getNamedQuery("getDeviceTypesByIdsList").setParameterList("deviceTypeId", deviceTypeIds).list();
//                        for (String deviceTypeId : deviceTypeIds) {
//                            dts = (DeviceTypes) session.getNamedQuery("getDeviceTypesById").setString("deviceTypeId", deviceTypeId).list().get(0);
//                            if (dts != null) {
//                                typesList.add(dts);
//                            }
//                        }
                        tingcoDevice = tingcoDeviceXML.buildDeviceTypesByGroupId(tingcoDevice, typesList);
                        return tingcoDevice;
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No DeviceTypes Found with Given GroupID");
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
            tingcoDevice.getMsgStatus().setResponseText("Error Occured While Loading the DeviceTypesInGroups");
            return tingcoDevice;
        } finally {
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
    public DeviceTypeResource getDeviceTypeResource() {
        return new DeviceTypeResource();
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

    private TingcoDevice updateDeviceTypeCommandAndTranslations(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        Transaction tx = null;
        TingcoDevice tingcoDevice = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.UPDATE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    GregorianCalendar gc = new GregorianCalendar();
                    se.info24.devicejaxbPost.TingcoDevice tingcoDevices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    se.info24.devicejaxbPost.DeviceTypeCommands dtcjaxb = tingcoDevices.getDevices().getDevice().get(0).getDeviceTypeCommands().get(0);
                    se.info24.devicejaxbPost.DeviceTypeCommandTranslations dtctransjaxb = dtcjaxb.getDeviceTypeCommandTranslations().get(0);
                    Object dtcObject = deviceDAO.getDeviceTypeCommandsById(dtcjaxb.getDeviceTypeCommandID(), session);
                    if (dtcObject != null) {
                        DeviceTypeCommands deviceTypeCommands = (DeviceTypeCommands) dtcObject;
                        if (dtcjaxb.getDeviceTypeCommandParentID() != null) {
                            deviceTypeCommands.setDeviceTypeCommandParentId(dtcjaxb.getDeviceTypeCommandParentID());
                        } else {
                            deviceTypeCommands.setDeviceTypeCommandParentId(null);
                        }
                        deviceTypeCommands.setDeviceTypeId(dtcjaxb.getDeviceTypeID());
                        deviceTypeCommands.setObjectCode(dtcjaxb.getObjectCode());
                        deviceTypeCommands.setDeviceDataItemId(dtcjaxb.getDeviceDataItemID());
                        if (dtcjaxb.getDataItemValue() != null) {
                            deviceTypeCommands.setDataItemValue(dtcjaxb.getDataItemValue());
                        } else {
                            deviceTypeCommands.setDataItemValue(null);
                        }
                        if (dtcjaxb.getFlagUserCanSetValue() != null) {
                            deviceTypeCommands.setFlagUserCanSetValue(dtcjaxb.getFlagUserCanSetValue());
                        } else {
                            deviceTypeCommands.setFlagUserCanSetValue(null);
                        }
                        if (dtcjaxb.getFlagIsNumericValue() != null) {
                            deviceTypeCommands.setFlagIsNumericValue(dtcjaxb.getFlagIsNumericValue());
                        } else {
                            deviceTypeCommands.setFlagIsNumericValue(null);
                        }
                        if (dtcjaxb.getFlagIsVisible() != null) {
                            deviceTypeCommands.setFlagIsVisible(dtcjaxb.getFlagIsVisible());
                        } else {
                            deviceTypeCommands.setFlagIsVisible(null);
                        }
                        if (dtcjaxb.getFlagValueCanBeNull() != null) {
                            deviceTypeCommands.setFlagValueCanBeNull(dtcjaxb.getFlagValueCanBeNull());
                        } else {
                            deviceTypeCommands.setFlagValueCanBeNull(null);
                        }
                        if (dtcjaxb.getDisplayOrder() != null) {
                            deviceTypeCommands.setDisplayOrder(dtcjaxb.getDisplayOrder());
                        } else {
                            deviceTypeCommands.setDisplayOrder(null);
                        }
                        if (dtcjaxb.getMinNumericValue() != null) {
                            deviceTypeCommands.setMinNumericValue(new BigDecimal(dtcjaxb.getMinNumericValue()));
                        } else {
                            deviceTypeCommands.setMinNumericValue(null);
                        }
                        if (dtcjaxb.getMaxNumericValue() != null) {
                            deviceTypeCommands.setMaxNumericValue(new BigDecimal(dtcjaxb.getMaxNumericValue()));
                        } else {
                            deviceTypeCommands.setMaxNumericValue(null);
                        }
                        if (dtcjaxb.getMaxCharsInValue() != null) {
                            deviceTypeCommands.setMaxCharsInValue(dtcjaxb.getMaxCharsInValue());
                        } else {
                            deviceTypeCommands.setMaxCharsInValue(null);
                        }
                        if (dtcjaxb.getValueRexExValidationString() != null) {
                            deviceTypeCommands.setValueRexExValidationString(dtcjaxb.getValueRexExValidationString());
                        } else {
                            deviceTypeCommands.setValueRexExValidationString(null);
                        }
                        deviceTypeCommands.setLastUpdatedByUserId(sessions2.getUserId());
                        deviceTypeCommands.setUpdatedDate(gc.getTime());
                        Set<DeviceTypeCommandTranslations> deviceTypeCommandTransset = deviceTypeCommands.getDeviceTypeCommandTranslationses();
//                    for(DeviceTypeCommandTranslations dtctrans: deviceTypeCommandTransset) {
//                        if(dtctrans.getId().getCountryId() == dtctransjaxb.getCountryID()) {
//                            dtctrans
//                        }
//                    }
                        Object dtctransObject = deviceDAO.getDeviceTypeCommandTranslationsByDeviceTypeCommandIDAndCountryId(session, dtcjaxb.getDeviceTypeCommandID(), dtctransjaxb.getCountryID());
                        if (dtctransObject != null) {
                            DeviceTypeCommandTranslations deviceTypeCommandTranslations = (DeviceTypeCommandTranslations) dtctransObject;
                            deviceTypeCommandTranslations.setCommandName(dtctransjaxb.getDeviceTypeCommandName());
                            if (dtctransjaxb.getCommandDescription() != null) {
                                deviceTypeCommandTranslations.setCommandDescription(dtctransjaxb.getCommandDescription());
                            } else {
                                deviceTypeCommandTranslations.setCommandDescription(null);
                            }
                            deviceTypeCommandTranslations.setCommandButtonText(dtctransjaxb.getCommandButtonText());
                            deviceTypeCommandTranslations.setLastUpdatedByUserId(sessions2.getUserId());
                            deviceTypeCommandTranslations.setUpdatedDate(gc.getTime());
                            deviceTypeCommandTransset.add(deviceTypeCommandTranslations);
                            deviceTypeCommands.setDeviceTypeCommandTranslationses(deviceTypeCommandTransset);
                        }
                        session.saveOrUpdate(deviceTypeCommands);
                        tx.commit();
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
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }

        return tingcoDevice;
    }

    private TingcoDevice updateDeviceTypeCommandTranslations(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        Transaction tx = null;
        TingcoDevice tingcoDevice = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.UPDATE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    GregorianCalendar gc = new GregorianCalendar();
                    se.info24.devicejaxbPost.TingcoDevice tingcoDevices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    se.info24.devicejaxbPost.DeviceTypeCommands dtcjaxb = tingcoDevices.getDevices().getDevice().get(0).getDeviceTypeCommands().get(0);
                    Object dtcObject = deviceDAO.getDeviceTypeCommandsById(dtcjaxb.getDeviceTypeCommandID(), session);
                    if (dtcObject != null) {
                        DeviceTypeCommands deviceTypeCommands = (DeviceTypeCommands) dtcObject;
//                        Iterator itr = deviceTypeCommands.getDeviceTypeCommandTranslationses().iterator();
                        for (Iterator itr = deviceTypeCommands.getDeviceTypeCommandTranslationses().iterator(); itr.hasNext();) {
                            session.delete(itr.next());
                            itr.remove();
//                            dtctransset.remove(itr.next();
                        }
                        session.flush();
                        session.clear();
                        DeviceTypeCommandTranslations dtctrans = null;
                        for (se.info24.devicejaxbPost.DeviceTypeCommandTranslations dtctjaxb : dtcjaxb.getDeviceTypeCommandTranslations()) {
                            dtctrans = new DeviceTypeCommandTranslations();
                            dtctrans.setId(new DeviceTypeCommandTranslationsId(dtcjaxb.getDeviceTypeCommandID(), dtctjaxb.getCountryID()));
                            dtctrans.setCommandName(dtctjaxb.getDeviceTypeCommandName());
                            dtctrans.setCommandButtonText(dtctjaxb.getCommandButtonText());
                            dtctrans.setLastUpdatedByUserId(sessions2.getUserId());
                            dtctrans.setCreatedDate(gc.getTime());
                            dtctrans.setUpdatedDate(gc.getTime());
                            session.saveOrUpdate(dtctrans);
                        }
                        tx.commit();
//                        Object dtctobject = null;
//                        DeviceTypeCommandTranslations dtctrans = null;
//                        int savedCount = 0;
//                        for (se.info24.devicejaxbPost.DeviceTypeCommandTranslations dtctjaxb : dtcjaxb.getDeviceTypeCommandTranslations()) {
//                            dtctobject = deviceDAO.getDeviceTypeCommandTranslationsByDeviceTypeCommandIDAndCountryId(session, dtcjaxb.getDeviceTypeCommandID(), dtctjaxb.getCountryID());
//                            if(dtctobject == null) {
//                                System.out.println("null "+dtctjaxb.getCountryID());
//                                dtctrans = new DeviceTypeCommandTranslations();
//                                dtctrans.setId(new DeviceTypeCommandTranslationsId(dtcjaxb.getDeviceTypeCommandID(), dtctjaxb.getCountryID()));
//                                dtctrans.setCommandName(dtctjaxb.getDeviceTypeCommandName());
//                                dtctrans.setCommandButtonText(dtctjaxb.getCommandButtonText());
//                                dtctrans.setLastUpdatedByUserId(sessions2.getUserId());
//                                dtctrans.setCreatedDate(gc.getTime());
//                                dtctrans.setUpdatedDate(gc.getTime());
//                                session.saveOrUpdate(dtctrans);
//                                dtctransset.remove(dtctrans);
//                            } else {
//                                System.out.println("not null " + dtctjaxb.getCountryID());
//                                dtctrans = (DeviceTypeCommandTranslations) dtctobject;
//                                System.out.println(dtctjaxb.getDeviceTypeCommandName());
//                                dtctrans.setCommandName(dtctjaxb.getDeviceTypeCommandName());
//                                dtctrans.setCommandButtonText(dtctjaxb.getCommandButtonText());
//                                dtctrans.setLastUpdatedByUserId(sessions2.getUserId());
//                                dtctrans.setUpdatedDate(gc.getTime());
//                            }
//
//                            savedCount++;
//                            if(savedCount % 20 == 0) {
//                                session.flush();
//                                session.clear();
//                            }
//                        }
//                        for(DeviceTypeCommandTranslations dtct: dtctransset) {
//                            session.delete(dtct);
//                            dtctransset.remove(dtct);
//                        }
//
//                        tx.commit();

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
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
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
}

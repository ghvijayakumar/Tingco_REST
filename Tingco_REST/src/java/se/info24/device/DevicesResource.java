/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.device;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.commons.codec.binary.Base64;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.chargePoint.ChargePointsResource;
import se.info24.commandsjaxb.TingcoCommands;
import se.info24.containers.ContainerDAO;
import se.info24.content.ContentDAO;
import se.info24.devicejaxb.TingcoDevice;
import se.info24.devicejaxbPost.AlarmLevels;
import se.info24.devicejaxbPost.DataItemTranslation;
import se.info24.devicejaxbPost.Device;
import se.info24.devicejaxbPost.DeviceAddress;
import se.info24.devicejaxbPost.DeviceCountReport;
import se.info24.devicejaxbPost.DeviceSetting;
import se.info24.devicejaxbPost.ObjectMediaFile;
import se.info24.devicejaxbPost.ProductLink;
import se.info24.devicejaxbPost.RecurringPurchases;
import se.info24.devicejaxbPost.Scaling;
import se.info24.devicejaxbPost.WarningLevels;
import se.info24.group.GroupDAO;
import se.info24.ismOperationsPojo.DeviceActivationSchedules;
import se.info24.ismOperationsPojo.DeviceHistory;
import se.info24.ismOperationsPojo.DeviceStatus;
import se.info24.ismOperationsPojo.DeviceStatusDataItems;
import se.info24.ismOperationsPojo.ItemConnectionStatus;
import se.info24.ismOperationsPojo.MediaFiles;
import se.info24.ismOperationsPojo.TransactionCodes;
import se.info24.ismOperationsPojo.TransactionProducts;
import se.info24.ismOperationsPojo.TransactionResult;
import se.info24.ismOperationsPojo.Widgets2;
import se.info24.jaxbUtil.TingcoDeviceXML;
import se.info24.permission.PermissionDAO;
import se.info24.pojo.AddressType;
import se.info24.pojo.Addresses;
import se.info24.pojo.Agreements;
import se.info24.pojo.BillingCategories;
import se.info24.pojo.BillingCategoryTranslations;
import se.info24.pojo.Channels;
import se.info24.pojo.CommandParseTargets;
import se.info24.pojo.CommandParseTargetsId;
import se.info24.pojo.CommandTranslations;
import se.info24.pojo.Company;
import se.info24.pojo.Connectors;
import se.info24.pojo.ContainerDevices;
import se.info24.pojo.Country;
import se.info24.pojo.Currency;
import se.info24.pojo.DataItemTranslationsPerDevice;
import se.info24.pojo.DataItemTranslationsPerDeviceId;
import se.info24.pojo.DeviceBillingCategories;
import se.info24.pojo.DeviceCommandSchedules;
import se.info24.pojo.DeviceDataItemScaling;
import se.info24.pojo.DeviceDataItemScalingId;
import se.info24.pojo.DeviceDataItems;
import se.info24.pojo.DeviceDataitemTranslations;
import se.info24.pojo.DeviceDataitemTranslationsId;
import se.info24.pojo.DeviceLinks;
import se.info24.pojo.DeviceLinksId;
import se.info24.pojo.DeviceOperationsMember;
import se.info24.pojo.DeviceOperationsStatus;
import se.info24.pojo.DeviceOperationsStatusTranslation;
import se.info24.pojo.DevicePendingDelete;
import se.info24.pojo.DeviceSettings;
import se.info24.pojo.DeviceSettingsPackages;
import se.info24.pojo.DeviceTypeChannels;
import se.info24.pojo.DeviceTypeChannelsId;
import se.info24.pojo.DeviceTypeCommandTranslations;
import se.info24.pojo.DeviceTypeCommands;
import se.info24.pojo.DeviceTypes;
import se.info24.pojo.FieldTranslations;
import se.info24.pojo.GroupLimits;
import se.info24.pojo.GroupObjectSettingPackages;
import se.info24.pojo.GroupObjectSettingPackagesId;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupTrusts;
import se.info24.pojo.Groups;
import se.info24.pojo.MediaFileTypes;
import se.info24.pojo.ObjectContactMemberships;
import se.info24.pojo.ObjectContacts;
import se.info24.pojo.ObjectGroupTranslations;
import se.info24.pojo.ObjectGroups;
import se.info24.pojo.ObjectMediaFiles;
import se.info24.pojo.ObjectMediaFilesId;
import se.info24.pojo.ObjectSettingPackageTemplates;
import se.info24.pojo.ObjectSettingPackageTemplatesId;
import se.info24.pojo.ObjectSettingPackageTranslations;
import se.info24.pojo.ObjectSettingPackageTranslationsId;
import se.info24.pojo.ObjectSettingPackages;
import se.info24.pojo.ObjectSettingTemplates;
import se.info24.pojo.ObjectTags;
import se.info24.pojo.ObjectTypeFields;
import se.info24.pojo.ObjectTypeSettingTemplates;
import se.info24.pojo.ObjectTypeSettingTemplatesId;
import se.info24.pojo.Ogmdevice;
import se.info24.pojo.OgmdeviceId;
import se.info24.pojo.PermissionOperations;
import se.info24.pojo.ProductVariantTranslations;
import se.info24.pojo.ProductVariants;
import se.info24.pojo.Providers;
import se.info24.pojo.RecurrenceTypes;
import se.info24.pojo.Schedules;
import se.info24.pojo.ServiceClientLogins;
import se.info24.pojo.ServiceDeviceSettingTemplates;
import se.info24.pojo.ServiceDeviceSettings;
import se.info24.pojo.ServiceDeviceSubscriptions;
import se.info24.pojo.Services;
import se.info24.pojo.SettingDataType;
import se.info24.pojo.UserAddresses;
import se.info24.pojo.UserAlias;
import se.info24.pojo.UserFavoriteDataItems;
import se.info24.pojo.UserFavoriteDataItemsId;
import se.info24.pojo.UserLog;
import se.info24.pojo.UserRoleMemberships2;
import se.info24.pojo.UserRoleObjectPermissions2;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.UserTimeZones2;
import se.info24.pojo.Users2;
import se.info24.protocols.ddm.DataContainer;
import se.info24.protocols.ddm.DataField;
import se.info24.protocols.ddm.DataGroup;
import se.info24.protocols.ddm.DataSequence;
import se.info24.protocols.ddm.DeviceDataMessage;
import se.info24.protocols.ddm.DeviceDataMessageDocument;
import se.info24.protocols.ddm.LanguageString;
import se.info24.protocols.ddm.MsgReceivers;
import se.info24.protocols.ddm.MsgSender;
import se.info24.protocols.em.EventMessageDocument;
import se.info24.restUtil.RestUtilDAO;
import se.info24.service.ServiceDAO;
import se.info24.user.CountryDAO;
import se.info24.user.UserDAO;
import se.info24.user.User_LoginsResource;
import se.info24.util.DbManager;
import se.info24.util.HibernateUtil;
import se.info24.util.JAXBManager;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;
import se.info24.util.TingcoConstants;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/device")
public class DevicesResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
    DeviceDAO deviceDAO = new DeviceDAO();
    GroupDAO groupDAO = new GroupDAO();
    UserDAO userDAO = new UserDAO();
    ContainerDAO containerDAO = new ContainerDAO();
    ChargePointsResource chargePointResource = new ChargePointsResource();
    Hashtable<String, se.info24.pojo.Device> deviceMap = null;
    Hashtable<String, DeviceDataitemTranslations> dataitemTrans = null;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    GregorianCalendar currentDateTime = new GregorianCalendar(TimeZone.getTimeZone("GMT"));

    /** Creates a new instance of DevicesResource */
    public DevicesResource() {
    }

    /**
     * Device_Add
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/add/rest/{rest}")
    @RESTDoc(methodName = "Device_Add", desc = "Used to create a new Device", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_Add(String content) {
        return createDevice(content);
    }

    /**
     * Device_Add
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/add/json/{json}")
    public TingcoDevice postJson_Add(String content) {
        return createDevice(content);
    }

    /**
     * Device_Add
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/adddevicewizard/rest/{rest}")
    @RESTDoc(methodName = "Device_Add", desc = "Used to create a new Device", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_AddDeviceWizard(String content) {
        return createDeviceWizard(content);
    }

    /**
     * Device_Add
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/adddevicewizard/json/{json}")
    public TingcoDevice postJson_AddDeviceWizard(String content) {
        return createDeviceWizard(content);
    }

    /**
     * CopyDeviceSettings
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/copydevicesettings/rest/{rest}")
    @RESTDoc(methodName = "CopyDeviceSettings", desc = "Used to Copy DeviceSettings", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_copyDeviceSettings(String content) {
        return copyDeviceSettings(content);
    }

    /**
     * CopyDeviceSettings
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/copydevicesettings/json/{json}")
    public TingcoDevice postJson_copyDeviceSettings(String content) {
        return copyDeviceSettings(content);
    }

    /**
     * AddSettingsPackage
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addsettingspackage/rest/{rest}")
    @RESTDoc(methodName = "AddSettingsPackage", desc = "Used to Add Settings Package", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_addSettingsPackage(String content) {
        return addSettingsPackage(content);
    }

    /**
     * AddSettingsPackage
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addsettingspackage/json/{json}")
    public TingcoDevice postJson_addSettingsPackage(String content) {
        return addSettingsPackage(content);
    }

    /**
     * AddUpdateDeviceDataItem
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addupdatedevicedataitem/rest/{rest}")
    @RESTDoc(methodName = "AddUpdateDeviceDataItem", desc = "Used to Add or Update DeviceDataItem", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_addUpdateDeviceDataItem(String content) {
        return addUpdateDeviceDataItem(content);
    }

    /**
     * AddUpdateDeviceDataItem
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addupdatedevicedataitem/json/{json}")
    public TingcoDevice postJson_addUpdateDeviceDataItem(String content) {
        return addUpdateDeviceDataItem(content);
    }

    /**
     * AdddDeviceSetting
     * @param deviceId
     * @param objectSettingTemplateId
     * @param settingValue
     * @param deviceSettingParentId
     * @return
     */
    @GET
    @Produces("application/xml")
    /*********** SettingValue should be send as Hexadecimal encoded value ***********/
    @Path("/adddevicesetting/rest/{rest}/deviceid/{deviceid}/objectsettingtemplateid/{objectsettingtemplateid}/settingvalue/{settingvalue}{devicesettingparentid:(/devicesettingparentid/[^/]+?)?}")
    @RESTDoc(methodName = "AdddDeviceSetting", desc = "Add Device Setting", req_Params = {"DeviceId;UUID", "ObjectSettingTemplateId;UUID", "SettingValue;string"}, opt_Params = {"DeviceSettingParentId;UUID"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_addDeviceSetting(@PathParam("deviceid") String deviceId, @PathParam("objectsettingtemplateid") String objectSettingTemplateId, @PathParam("settingvalue") String settingValue, @PathParam("devicesettingparentid") String deviceSettingParentId) {
        return addDeviceSetting(deviceId, objectSettingTemplateId, settingValue, deviceSettingParentId);
    }

    /**
     * AdddDeviceSetting
     * @param deviceId
     * @param objectSettingTemplateId
     * @param settingValue
     * @param deviceSettingParentId
     * @return
     */
    @GET
    @Produces("application/json")
    /*********** SettingValue should be send as Hexadecimal encoded value ***********/
    @Path("/adddevicesetting/json/{json}/deviceid/{deviceid}/objectsettingtemplateid/{objectsettingtemplateid}/settingvalue/{settingvalue}{devicesettingparentid:(/devicesettingparentid/[^/]+?)?}")
    public TingcoDevice getJson_addDeviceSetting(@PathParam("deviceid") String deviceId, @PathParam("objectsettingtemplateid") String objectSettingTemplateId, @PathParam("settingvalue") String settingValue, @PathParam("devicesettingparentid") String deviceSettingParentId) {
        return addDeviceSetting(deviceId, objectSettingTemplateId, settingValue, deviceSettingParentId);
    }

    /**
     * AddAdvancedDeviceSetting
     * @param settingName
     * @param settingValue
     * @param settingDataTypeId
     * @param deviceId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/addadvanceddevicesetting/rest/{rest}/deviceid/{deviceid}/settingname/{settingname}/settingvalue/{settingvalue}/settingdatatypeid/{settingdatatypeid}")
    @RESTDoc(methodName = "AddAdvancedDeviceSetting", desc = "Add Advanced Device Setting", req_Params = {"SettingName;string", "SettingValue;string", "SettingDataTypeId;UUID", "deviceId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_addAdvancedDeviceSetting(@PathParam("settingname") String settingName, @PathParam("settingvalue") String settingValue, @PathParam("settingdatatypeid") String settingDataTypeId, @PathParam("deviceid") String deviceId) throws DatatypeConfigurationException {
        return addAdvancedDeviceSetting(settingName, settingValue, settingDataTypeId, deviceId);
    }

    /**
     * AddAdvancedDeviceSetting
     * @param settingName
     * @param settingValue
     * @param settingDataTypeId
     * @param deviceId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/addadvanceddevicesetting/rest/{rest}/deviceid/{deviceid}/settingname/{settingname}/settingvalue/{settingvalue}/settingdatatypeid/{settingdatatypeid}")
    public TingcoDevice getJson_addAdvancedDeviceSetting(@PathParam("settingname") String settingName, @PathParam("settingvalue") String settingValue, @PathParam("settingdatatypeid") String settingDataTypeId, @PathParam("deviceid") String deviceId) throws DatatypeConfigurationException {
        return addAdvancedDeviceSetting(settingName, settingValue, settingDataTypeId, deviceId);
    }

    /**
     * AddDeviceSettingbyPackage
     * @param objectSettingPackageId
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/adddevicesettingsbypackage/rest/{rest}/objectsettingpackageid/{objectsettingpackageid}/deviceid/{deviceid}")
    @RESTDoc(methodName = "Add Device Setting By Package", desc = "Used to add device setting by package", req_Params = {"ObjectSettingPackageId;UUID", "DeviceId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_addDeviceSettingbyPackage(@PathParam("objectsettingpackageid") String objectSettingPackageId, @PathParam("deviceid") String deviceId) {
        return addDeviceSettingbyPackage(objectSettingPackageId, deviceId);
    }

    /**
     * AddDeviceSettingbyPackage
     * @param objectSettingPackageId
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/adddevicesettingsbypackage/json/{json}/objectsettingpackageid/{objectsettingpackageid}/deviceid/{deviceid}")
    public TingcoDevice getJson_addDeviceSettingbyPackage(@PathParam("objectsettingpackageid") String objectSettingPackageId, @PathParam("deviceid") String deviceId) {
        return addDeviceSettingbyPackage(objectSettingPackageId, deviceId);
    }

    /**
     * AddServiceDeviceSetting
     * @param objectSettingTemplateID
     * @param serviceDeviceSubscriptionID
     * @param ServiceDeviceSettingValue
     * @return
     */
    @GET
    @Produces("application/xml")
    /*********** ServiceDeviceSettingValue should be send as Hexadecimal encoded value ***********/
    @Path("/addservicedevicesetting/rest/{rest}/objectsettingtemplateid/{objectsettingtemplateid}/servicedevicesubscriptionid/{servicedevicesubscriptionid}/servicedevicesettingvalue/{servicedevicesettingvalue}")
    @RESTDoc(methodName = "Add ServiceDeviceSetting", desc = "Used to Add ServiceDeviceSetting", req_Params = {"ObjectSettingTemplateID;UUID", "serviceDeviceSubscriptionID;UUID", "ServiceDeviceSettingValue;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_addServiceDeviceSetting(@PathParam("objectsettingtemplateid") String objectSettingTemplateId, @PathParam("servicedevicesubscriptionid") String serviceDeviceSubscriptionId, @PathParam("servicedevicesettingvalue") String serviceDeviceSettingValue) {
        return addServiceDeviceSetting(objectSettingTemplateId, serviceDeviceSubscriptionId, serviceDeviceSettingValue);
    }

    /**
     * AddServiceDeviceSetting
     * @param objectSettingTemplateID
     * @param serviceDeviceSubscriptionID
     * @param ServiceDeviceSettingValue
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/addservicedevicesetting/json/{json}/objectsettingtemplateid/{objectsettingtemplateid}/servicedevicesubscriptionid/{servicedevicesubscriptionid}/servicedevicesettingvalue/{servicedevicesettingvalue}")
    public TingcoDevice getJson_addServiceDeviceSetting(@PathParam("objectsettingtemplateid") String objectSettingTemplateId, @PathParam("servicedevicesubscriptionid") String serviceDeviceSubscriptionId, @PathParam("servicedevicesettingvalue") String serviceDeviceSettingValue) {
        return addServiceDeviceSetting(objectSettingTemplateId, serviceDeviceSubscriptionId, serviceDeviceSettingValue);
    }

    /**
     * Update Device Status
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addadvanceddevicesettings/rest/{rest}")
    @RESTDoc(methodName = "Update Device Status", desc = "Used to Update Device Status", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_addAdvancedDeviceSettings(String content) throws DatatypeConfigurationException {
        return addAdvancedDeviceSettings(content);
    }

    /**
     * Update Device Status
     * @param content
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addadvanceddevicesettings/json/{json}")
    public TingcoDevice postJson_addAdvancedDeviceSettings(String content) throws DatatypeConfigurationException {
        return addAdvancedDeviceSettings(content);
    }

    /**
     * Update Device Status
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/updatedevicestatus/rest/{rest}")
    @RESTDoc(methodName = "Update Device Status", desc = "Used to Update Device Status", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_updateDeviceStatus(String content) throws DatatypeConfigurationException {
        return updateDeviceStatus(content);
    }

    /**
     * Update Device Status
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/updatedevicestatus/json/{json}")
    public TingcoDevice postJson_updateDeviceStatus(String content) throws DatatypeConfigurationException {
        return updateDeviceStatus(content);
    }

    /**
     * UpdateUserFavoriteDataItems
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/updateuserfavoritedataitems/rest/{rest}")
    @RESTDoc(methodName = "UpdateUserFavoriteDataItems", desc = "Used to Update UserFavoriteDataItems", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_updateUserFavoriteDataItems(String content) {
        return updateUserFavoriteDataItems(content);
    }

    /**
     * UpdateUserFavoriteDataItems
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/updateuserfavoritedataitems/json/{json}")
    public TingcoDevice postJson_updateUserFavoriteDataItems(String content) {
        return updateUserFavoriteDataItems(content);
    }

    /**
     * UpdateDeviceDataItem
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/updatedevicedataitem/rest/{rest}")
    @RESTDoc(methodName = "Update Device Status", desc = "Used to Update DeviceDataItem", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_updateDeviceDataItem(String content) throws DatatypeConfigurationException {
        return updateDeviceDataItem(content);
    }

    /**
     * UpdateDeviceDataItem
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/updatedevicedataitem/json/{json}")
    public TingcoDevice postJson_updateDeviceDataItem(String content) throws DatatypeConfigurationException {
        return updateDeviceDataItem(content);
    }

    /**
     * UpdateServiceDeviceSetting
     * @param serviceDeviceSettingId
     * @param servicedevicesettingvalue
     * @return
     */
    @GET
    @Produces("application/xml")
    /*********** ServiceDeviceSettingValue should be send as Hexadecimal encoded value ***********/
    @Path("/updateservicedevicesetting/rest/{rest}/servicedevicesettingid/{servicedevicesettingid}/servicedevicesettingvalue/{servicedevicesettingvalue}")
    @RESTDoc(methodName = "UpdateServiceDeviceSetting", desc = "Used to Update ServiceDeviceSetting", req_Params = {"ServiceDeviceSettingId;UUID", "serviceDeviceSettingValue;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_updateServiceDeviceSetting(@PathParam("servicedevicesettingid") String serviceDeviceSettingId, @PathParam("servicedevicesettingvalue") String serviceDeviceSettingValue) {
        return updateServiceDeviceSetting(serviceDeviceSettingId, serviceDeviceSettingValue);
    }

    /**
     * UpdateServiceDeviceSetting
     * @param serviceDeviceSettingId
     * @param servicedevicesettingvalue
     * @return
     */
    @GET
    @Produces("application/json")
    /*********** ServiceDeviceSettingValue should be send as Hexadecimal encoded value ***********/
    @Path("/updateservicedevicesetting/json/{json}/servicedevicesettingid/{servicedevicesettingid}/servicedevicesettingvalue/{servicedevicesettingvalue}")
    public TingcoDevice getJson_updateServiceDeviceSetting(@PathParam("servicedevicesettingid") String serviceDeviceSettingId, @PathParam("servicedevicesettingvalue") String serviceDeviceSettingValue) {
        return updateServiceDeviceSetting(serviceDeviceSettingId, serviceDeviceSettingValue);
    }

    /**
     * GetItemConnectionStatus
     * @param itemId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getitemconnectionstatus/rest/{rest}/itemid/{itemid}")
    @RESTDoc(methodName = "GetItemConnectionStatus", desc = "Used to get ItemConnectionStatus", req_Params = {"itemId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_getItemConnectionStatus(@PathParam("itemid") String itemId) {
        return getItemConnectionStatus(itemId);
    }

    /**
     * GetDeviceDataItemAndWidgets
     * @param itemId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getitemconnectionstatus/json/{json}/itemid/{itemid}")
    public TingcoDevice getJson_getItemConnectionStatus(@PathParam("itemid") String itemId) {
        return getItemConnectionStatus(itemId);
    }

    /**
     * GetDeviceDataItemAndWidgets
     * @param deviceId
     * @param deviceDataItemId
     * @param widgetId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getdevicedataitemandwidgets/rest/{rest}/deviceid/{deviceid}/devicedataitemid/{devicedataitemid}/widgetid/{widgetid}")
    @RESTDoc(methodName = "GetDeviceDataItemAndWidgets", desc = "Used to Get DeviceDataItemAndWidgets", req_Params = {"deviceId;UUID", "deviceDataItemId;UUID", "widgetId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_getDeviceDataItemAndWidgets(@PathParam("deviceid") String deviceId, @PathParam("devicedataitemid") String deviceDataItemId, @PathParam("widgetid") String widgetId) {
        return getDeviceDataItemAndWidgets(deviceId, deviceDataItemId, widgetId);
    }

    /**
     * GetDeviceDataItemAndWidgets
     * @param deviceId
     * @param deviceDataItemId
     * @param widgetId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getdevicedataitemandwidgets/json/{json}/deviceid/{deviceid}/devicedataitemid/{devicedataitemid}/widgetid/{widgetid}")
    public TingcoDevice getJson_getDeviceDataItemAndWidgets(@PathParam("deviceid") String deviceId, @PathParam("devicedataitemid") String deviceDataItemId, @PathParam("widgetid") String widgetId) {
        return getDeviceDataItemAndWidgets(deviceId, deviceDataItemId, widgetId);
    }

    /**
     * GetObjectSettingTemplates
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getobjectsettingtemplates/rest/{rest}/deviceid/{deviceid}")
    @RESTDoc(methodName = "GetObjectSettingTemplates", desc = "Used to get the Objectsettingtemplates", req_Params = {"DeviceID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_getObjectSettingTemplates(@PathParam("deviceid") String deviceId) {
        return getObjectSettingTemplates(deviceId);
    }

    /**
     * GetObjectSettingTemplates
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getobjectsettingtemplates/json/{json}/deviceid/{deviceid}")
    public TingcoDevice getJson_getObjectSettingTemplates(@PathParam("deviceid") String deviceId) {
        return getObjectSettingTemplates(deviceId);
    }

    /**
     * GetObjectSettingTemplatesForDeviceServices
     * @param serviceId
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getobjectsettingtemplatesfordeviceservices/rest/{rest}/serviceid/{serviceid}/deviceid/{deviceid}")
    @RESTDoc(methodName = "GetObjectSettingTemplatesForDeviceServices", desc = "Used to get Objectsettingtemplates for DeviceServices", req_Params = {"ServiceID;UUID", "DeviceID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_getObjectSettingTemplatesForDeviceServices(@PathParam("serviceid") String serviceId, @PathParam("deviceid") String deviceId) {
        return getObjectSettingTemplatesForDeviceServices(serviceId, deviceId);
    }

    /**
     * GetObjectSettingTemplatesForDeviceServices
     * @param serviceId
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getobjectsettingtemplatesfordeviceservices/json/{json}/serviceid/{serviceid}/deviceid/{deviceid}")
    public TingcoDevice getJson_getObjectSettingTemplatesForDeviceServices(@PathParam("serviceid") String serviceId, @PathParam("deviceid") String deviceId) {
        return getObjectSettingTemplatesForDeviceServices(serviceId, deviceId);
    }

    /**
     * GetPredefinedSettingsPackages
     * @param deviceId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getpredefinedsettingspackages/rest/{rest}/deviceid/{deviceid}/groupid/{groupid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetPredefinedSettingsPackages", desc = "To Get PredefinedSettingsPackages", req_Params = {"DeviceID;UUID", "GroupID;UUID", "CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_getPredefinedSettingsPackages(@PathParam("deviceid") String deviceId, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getPredefinedSettingsPackages(deviceId, groupId, countryId);
    }

    /**
     * GetPredefinedSettingsPackages
     * @param deviceId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getpredefinedsettingspackages/json/{json}/deviceid/{deviceid}/groupid/{groupid}/countryid/{countryid}")
    public TingcoDevice getJson_getPredefinedSettingsPackages(@PathParam("deviceid") String deviceId, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getPredefinedSettingsPackages(deviceId, groupId, countryId);
    }

    /**
     * ServiceDeviceSettingInfo
     * @param serviceDeviceSettingId
     * @param objectSettingTemplateId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getservicedevicesettinginfo/rest/{rest}/servicedevicesettingid/{servicedevicesettingid}{objectsettingtemplateid:(/objectsettingtemplateid/[^/]+?)?}")
    @RESTDoc(methodName = "ServiceDeviceSettingInfo", desc = "Used to get ServiceDeviceSetting Information", req_Params = {"ServiceDeviceSettingID;UUID", "ObjectSettingTemplateID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoDevice getXml_getserviceDeviceSettingInfo(@PathParam("servicedevicesettingid") String serviceDeviceSettingId, @PathParam("objectsettingtemplateid") String objectSettingTemplateId) {
        return serviceDeviceSettingInfo(serviceDeviceSettingId, objectSettingTemplateId);
    }

    /**
     * ServiceDeviceSettingInfo
     * @param serviceDeviceSettingId
     * @param objectSettingTemplateId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getservicedevicesettinginfo/json/{json}/servicedevicesettingid/{servicedevicesettingid}{objectsettingtemplateid:(/objectsettingtemplateid/[^/]+?)?}")
    public TingcoDevice getJson_getserviceDeviceSettingInfo(@PathParam("servicedevicesettingid") String serviceDeviceSettingId, @PathParam("objectsettingtemplateid") String objectSettingTemplateId) {
        return serviceDeviceSettingInfo(serviceDeviceSettingId, objectSettingTemplateId);
    }

    /**
     * GetServiceDeviceSettings
     * @param serviceDeviceSubscriptionId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getservicedevicesettings/rest/{rest}/servicedevicesubscriptionid/{servicedevicesubscriptionid}")
    @RESTDoc(methodName = "GetServiceDeviceSettings", desc = "Used to Get ServiceDeviceSettings", req_Params = "ServiceDeviceSubscriptionID;UUID", opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_getServiceDeviceSettings(@PathParam("servicedevicesubscriptionid") String serviceDeviceSubscriptionId) {
        return getServiceDeviceSettings(serviceDeviceSubscriptionId);
    }

    /**
     * GetServiceDeviceSettings
     * @param serviceDeviceSubscriptionId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getservicedevicesettings/json/{json}/servicedevicesubscriptionid/{servicedevicesubscriptionid}")
    public TingcoDevice getJson_getServiceDeviceSettings(@PathParam("servicedevicesubscriptionid") String serviceDeviceSubscriptionId) {
        return getServiceDeviceSettings(serviceDeviceSubscriptionId);
    }

    /**
     * GetSettingsPackage
     * @param groupId
     * @param countryId
     * @param deviceTypeId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getsettingpackage/rest/{rest}/groupid/{groupid}/countryid/{countryid}/devicetypeid/{devicetypeid}")
    @RESTDoc(methodName = "GetSettingsPackage", desc = "Used to Get SettingsPackage", req_Params = {"GroupID;UUID", "CountryID;int", "DeviceTypeID;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_getSettingsPackage(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("devicetypeid") String deviceTypeId) {
        return getSettingsPackage(groupId, countryId, deviceTypeId);
    }

    /**
     * GetSettingsPackage
     * @param groupId
     * @param countryId
     * @param deviceTypeId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getsettingpackage/json/{json}/groupid/{groupid}/countryid/{countryid}/devicetypeid/{devicetypeid}")
    public TingcoDevice getJson_getSettingsPackage(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("devicetypeid") String deviceTypeId) {
        return getSettingsPackage(groupId, countryId, deviceTypeId);
    }

    /**
     * Device_GetInfo
     * @param deviceId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/get/rest/{rest}/deviceid/{deviceid}/countryid/{countryid}")
    @RESTDoc(methodName = "Device_GetInfo", desc = "To Get the Device Information", req_Params = {"DeviceID;UUID", "CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml(@PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId) {
        return getDeviceInfo(deviceId, countryId);
    }

    /**
     * Device_GetInfo
     * @param deviceId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/get/json/{json}/deviceid/{deviceid}/countryid/{countryid}")
    public TingcoDevice getJson(@PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId) {
        return getDeviceInfo(deviceId, countryId);
    }

    /**
     * DeleteServiceDeviceSubscription
     * @param serviceDeviceSubscriptionId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/deleteservicedevicesubscription/rest/{rest}/servicedevicesubscriptionid/{servicedevicesubscriptionid}")
    @RESTDoc(methodName = "Delete Service DeviceSubscriptions", desc = "Used to delete the ServiceDeviceSubscription", req_Params = {"ServiceDeviceSubscriptionID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_deleteServiceDeviceSubscription(@PathParam("servicedevicesubscriptionid") String serviceDeviceSubscriptionId) throws DatatypeConfigurationException {
        return deleteServiceDeviceSubscription(serviceDeviceSubscriptionId);
    }

    /**
     * DeleteServiceDeviceSubscription
     * @param serviceDeviceSubscriptionId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/deleteservicedevicesubscription/json/{json}/servicedevicesubscriptionid/{servicedevicesubscriptionid}")
    public TingcoDevice getJson_deleteServiceDeviceSubscription(@PathParam("servicedevicesubscriptionid") String serviceDeviceSubscriptionId) throws DatatypeConfigurationException {
        return deleteServiceDeviceSubscription(serviceDeviceSubscriptionId);
    }

    /**
     * DeleteDeviceActivationSchedules
     * @param deviceActivationId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/deletedeviceactivationschedules/rest/{rest}/deviceactivationid/{deviceactivationid}")
    @RESTDoc(methodName = "DeleteDeviceActivationSchedules", desc = "Used to delete the DeviceActivationSchedules", req_Params = {"deviceActivationId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_deleteDeviceActivationSchedules(@PathParam("deviceactivationid") String deviceActivationId) throws DatatypeConfigurationException {
        return deleteDeviceActivationSchedules(deviceActivationId);
    }

    /**
     * DeleteDeviceActivationSchedules
     * @param deviceActivationId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/deletedeviceactivationschedules/json/{json}/deviceactivationid/{deviceactivationid}")
    public TingcoDevice getJson_deleteDeviceActivationSchedules(@PathParam("deviceactivationid") String deviceActivationId) throws DatatypeConfigurationException {
        return deleteDeviceActivationSchedules(deviceActivationId);
    }

    /**
     * DeleteDeviceLinks
     * @param deviceId
     * @param iTrustDeviceId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/deletedevicelinks/rest/{rest}/deviceid/{deviceid}/itrustdeviceid/{itrustdeviceid}")
    @RESTDoc(methodName = "DeleteDeviceLinks", desc = "Used to Delete DeviceLinks", req_Params = {"deviceId;UUID", "iTrustDeviceId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_deleteDeviceLinks(@PathParam("deviceid") String deviceId, @PathParam("itrustdeviceid") String iTrustDeviceId) throws DatatypeConfigurationException {
        return deleteDeviceLinks(deviceId, iTrustDeviceId);
    }

    /**
     * DeleteDeviceLinks
     * @param deviceId
     * @param iTrustDeviceId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/deletedevicelinks/json/{json}/deviceid/{deviceid}/itrustdeviceid/{itrustdeviceid}")
    public TingcoDevice getJson_deleteDeviceLinks(@PathParam("deviceid") String deviceId, @PathParam("itrustdeviceid") String iTrustDeviceId) throws DatatypeConfigurationException {
        return deleteDeviceLinks(deviceId, iTrustDeviceId);
    }

    /**
     * UpdateObjectContactMembership
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/updateobjectcontactmemberships/rest/{rest}")
    @RESTDoc(methodName = "UpdateObjectContactMembership", desc = "Used to Update ObjectContactMembership", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_updateObjectContactMembership(String content) throws DatatypeConfigurationException {
        return updateObjectContactMembership(content);
    }

    /**
     * UpdateObjectContactMembership
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/updateobjectcontactmemberships/json/{json}")
    public TingcoDevice postJson_updateObjectContactMembership(String content) throws DatatypeConfigurationException {
        return updateObjectContactMembership(content);
    }

    /**
     * GetDeviceSalesDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getdevicesalesdetails/rest/{rest}")
    @RESTDoc(methodName = "GetDeviceSalesDetails", desc = "Used to Get DeviceSalesDetails", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_getDeviceSalesDetails(String content) {
        return getDeviceSalesDetails(content);
    }

    /**
     * GetDeviceSalesDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getdevicesalesdetails/json/{json}")
    public TingcoDevice postJson_getDeviceSalesDetails(String content) {
        return getDeviceSalesDetails(content);
    }

    /**
     * GetDeviceSalesDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getusersalesdetails/rest/{rest}")
    @RESTDoc(methodName = "GetDeviceSalesDetails", desc = "Used to Get DeviceSalesDetails", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_GetUserSalesDetails(String content) {
        return GetUserSalesDetails(content);
    }

    /**
     * GetDeviceSalesDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getusersalesdetails/json/{json}")
    @RESTDoc(methodName = "GetDeviceSalesDetails", desc = "Used to Get DeviceSalesDetails", req_Params = {"json;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postJson_GetUserSalesDetails(String content) {
        return GetUserSalesDetails(content);
    }

    /**
     * GetDeviceSalesDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getsalesbyproductvariant/rest/{rest}")
    @RESTDoc(methodName = "GetDeviceSalesDetails", desc = "Used to Get DeviceSalesDetails", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_GetSalesbyProductVariant(String content) {
        return getSalesbyProductVariant(content);
    }

    /**
     * GetDeviceSalesDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getsalesbyproductvariant/json/{json}")
    @RESTDoc(methodName = "GetDeviceSalesDetails", desc = "Used to Get DeviceSalesDetails", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postJson_GetSalesbyProductVariant(String content) {
        return getSalesbyProductVariant(content);
    }

    /**
     * GetDataItemsForDevice
     * @param deviceId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getdataitemsfordevice/rest/{rest}/deviceid/{deviceid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetDataItemsForDevice", desc = "Used to get data items for Device", req_Params = {"DeviceID;UUID", "CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_getDataItemsForDevice(@PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId) {
        return getDataItemsForDevice(deviceId, countryId);
    }

    /**
     * GetDataItemsForDevice
     * @param deviceId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getdataitemsfordevice/json/{json}/deviceid/{deviceid}/countryid/{countryid}")
    public TingcoDevice getJson_getDataItemsForDevice(@PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId) {
        return getDataItemsForDevice(deviceId, countryId);
    }

    /**
     * DeviceTypeCommands
     * @param countryId
     * @param deviceTypeId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getdevicetypecommands/rest/{rest}/devicetypeid/{devicetypeid}/countryid/{countryid}")
    @RESTDoc(methodName = "DeviceTypeCommands", desc = "To Get the DeviceTypeCommands Information", req_Params = {"DeviceTypeID;UUID", "CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_getDeviceTypeCommands(@PathParam("devicetypeid") String deviceTypeId, @PathParam("countryid") int countryId) {
        return getDeviceTypeCommands(deviceTypeId, countryId);
    }

    /**
     * DeviceTypeCommands
     * @param countryId
     * @param deviceTypeId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getdevicetypecommands/json/{json}/devicetypeid/{devicetypeid}/countryid/{countryid}")
    public TingcoDevice getJson_getDeviceTypeCommands(@PathParam("devicetypeid") String deviceTypeId, @PathParam("countryid") int countryId) {
        return getDeviceTypeCommands(deviceTypeId, countryId);
    }

    /**
     * ObjectContact
     * @param groupId
     * @param countryId
     * @param objectId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getobjectcontact/rest/{rest}/objectid/{objectid}/groupid/{groupid}/countryid/{countryid}")
    @RESTDoc(methodName = "ObjectContact", desc = "To Get the ObjectContact Information", req_Params = {"ObjectID;UUID", "GroupID;UUID", "CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_getObjectContact(@PathParam("objectid") String objectId, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getObjectContact(groupId, countryId, objectId);
    }

    /**
     * ObjectContact
     * @param groupId
     * @param countryId
     * @param objectId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getobjectcontact/json/{json}/objectid/{objectid}/groupid/{groupid}/countryid/{countryid}")
    public TingcoDevice getJson_getObjectContact(@PathParam("objectid") String objectId, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getObjectContact(groupId, countryId, objectId);
    }

    /**
     * InsertObjectContactMemberships
     * @param groupId
     * @param countryId
     * @param objectId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/insertobjectcontactmemberships/rest/{rest}/objectid/{objectid}/objectcontactid/{objectcontactid}")
    @RESTDoc(methodName = "ObjectContact", desc = "To Get the ObjectContact Information", req_Params = {"ObjectID;UUID", "GroupID;UUID", "CountryID;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoDevice getXml_insertObjectContactMemberships(@PathParam("objectid") String objectId, @PathParam("objectcontactid") String objectContactId) {
//        return getObjectContact(groupId, countryId, objectId);
        return null;
    }

    /**
     * InsertObjectContactMemberships
     * @param objectId
     * @param objectContactId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/insertobjectcontactmemberships/json/{json}/objectid/{objectid}/objectcontactid/{objectcontactid}")
    @RESTDoc(methodName = "ObjectContact", desc = "To Get the ObjectContact Information", req_Params = {"ObjectID;UUID", "GroupID;UUID", "CountryID;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoDevice getJson_insertObjectContactMemberships(@PathParam("objectid") String objectId, @PathParam("objectcontactid") String objectContactId) {
//        return getObjectContact(groupId, countryId, objectId);
        return null;
    }

    /**
     * InsertServiceDeviceSubscriptions
     * @param serviceId
     * @param deviceId
     * @param serviceClientLoginId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/insertservicedevicesubscriptions/rest/{rest}/serviceid/{serviceid}/deviceid/{deviceid}/serviceclientloginid/{serviceclientloginid}")
    @RESTDoc(methodName = "InsertServiceDeviceSubscriptions", desc = "Used to Insert ServiceDeviceSubscriptions", req_Params = {"serviceid;UUID", "deviceid;UUID", "serviceclientloginid;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_insertServiceDeviceSubscriptions(@PathParam("serviceid") String serviceId, @PathParam("deviceid") String deviceId, @PathParam("serviceclientloginid") String serviceClientLoginId) {
        return insertServiceDeviceSubscriptions(serviceId, deviceId, serviceClientLoginId);
    }

    /**
     * InsertServiceDeviceSubscriptions
     * @param serviceId
     * @param deviceId
     * @param serviceClientLoginId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/insertservicedevicesubscriptions/json/{json}/serviceid/{serviceid}/deviceid/{deviceid}/serviceclientloginid/{serviceclientloginid}")
    public TingcoDevice getJson_insertServiceDeviceSubscriptions(@PathParam("serviceid") String serviceId, @PathParam("deviceid") String deviceId, @PathParam("serviceclientloginid") String serviceClientLoginId) {
        return insertServiceDeviceSubscriptions(serviceId, deviceId, serviceClientLoginId);
    }

    /**
     * deletedevicesetting
     * @param deviceId
     * @param devicesettingid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deletedevicesetting/rest/{rest}/deviceid/{deviceid}/devicesettingid/{devicesettingid}")
    @RESTDoc(methodName = "DeleteDeviceSetting", desc = "Used to delete DeviceSettings", req_Params = {"DeviceID;UUID", "DeviceSettingID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_deleteDeviceSettings(@PathParam("deviceid") String deviceId, @PathParam("devicesettingid") String deviceSettingId) throws DatatypeConfigurationException {
        return deleteDeviceSettingsbyIds(deviceId, deviceSettingId);
    }

    /**
     * deletedevicesetting
     * @param deviceId
     * @param devicesettingid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deletedevicesetting/json/{json}/deviceid/{deviceid}/devicesettingid/{devicesettingid}")
    public TingcoDevice getJson_deleteDeviceSettings(@PathParam("deviceid") String deviceId, @PathParam("devicesettingid") String deviceSettingId) throws DatatypeConfigurationException {
        return deleteDeviceSettingsbyIds(deviceId, deviceSettingId);
    }

    /**
     * DeleteServiceDeviceSetting
     * @param servicedevicesettingid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deleteservicedevicesetting/rest/{rest}/servicedevicesettingid/{servicedevicesettingid}")
    @RESTDoc(methodName = "DeleteServiceDeviceSetting", desc = "Used to Delete ServiceDeviceSetting", req_Params = {"ServiceDeviceSettingID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_deleteServiceDeviceSetting(@PathParam("servicedevicesettingid") String serviceDeviceSettingId) {
        return deleteServiceDeviceSetting(serviceDeviceSettingId);
    }

    /**
     * DeleteServiceDeviceSetting
     * @param servicedevicesettingid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deleteservicedevicesetting/json/{json}/servicedevicesettingid/{servicedevicesettingid}")
    public TingcoDevice getJson_deleteServiceDeviceSetting(@PathParam("servicedevicesettingid") String serviceDeviceSettingId) {
        return deleteServiceDeviceSetting(serviceDeviceSettingId);
    }

    /**
     * EditDeviceSetting
     * @param deviceId
     * @param devicesettingId
     * @param settingValue
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/editdevicesetting/rest/{rest}/deviceid/{deviceid}/devicesettingid/{devicesettingid}/settingvalue/{settingvalue}")
    @RESTDoc(methodName = "EditDeviceSetting", desc = "Used to Edit DeviceSetting", req_Params = {"deviceId;UUID", "deviceSettingId;UUID", "settingValue;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_editDeviceSettings(@PathParam("deviceid") String deviceId, @PathParam("devicesettingid") String deviceSettingId, @PathParam("settingvalue") String settingValue) throws DatatypeConfigurationException {
        return editDeviceSettings(deviceId, deviceSettingId, settingValue);
    }

    /**
     * EDITdevicesetting
     * @param deviceId
     * @param devicesettingid
     * @param settingValue
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/editdevicesetting/json/{json}/deviceid/{deviceid}/devicesettingid/{devicesettingid}/settingvalue/{settingvalue}")
    public TingcoDevice getJson_editDeviceSettings(@PathParam("deviceid") String deviceId, @PathParam("devicesettingid") String deviceSettingId, @PathParam("settingvalue") String settingValue) throws DatatypeConfigurationException {
        return editDeviceSettings(deviceId, deviceSettingId, settingValue);
    }

    /**
     * getallsettingdatatypes
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getallsettingdatatypes/rest/{rest}")
    @RESTDoc(methodName = "GetAllSettingDataTypes", desc = "Used to Get All Setting Datatypes", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_getAllSettingDataType() throws DatatypeConfigurationException {
        return getSettingDataType();
    }

    /**
     * getsettingdatatypes
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getallsettingdatatypes/json/{json}")
    public TingcoDevice getJson_getAllSettingDataType() throws DatatypeConfigurationException {
        return getSettingDataType();
    }

    /**
     * Device_GetInfo
     * @param deviceId
     * @param countryId
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/get/rest/{rest}/deviceid/{deviceid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetDeviceInfo", desc = "Used to get DeviceInformation", req_Params = {"DeviceID;UUID", "CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml(@PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId) {
        return getDeviceInfo(deviceId, countryId);
    }

    /**
     * Device_GetInfo
     * @param deviceId
     * @param countryId
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/get/json/{json}/deviceid/{deviceid}/countryid/{countryid}")
    public TingcoDevice postJSON(@PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId) {
        return getDeviceInfo(deviceId, countryId);
    }

    /**
     * GetDeviceLocationHistoryDetails
     * @param deviceId
     * @param fromDate
     * @param toDate
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getdevicelocationhistorydetails/rest/{rest}/deviceid/{deviceid}/fromdate/{fromdate}/todate/{todate}")
    @RESTDoc(methodName = "GetDeviceLocationHistoryDetails", desc = "Used to Get Device LocationHistoryDetails", req_Params = {"deviceId;UUID", "fromDate;string", "toDate;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_getDeviceLocationHistoryDetails(@PathParam("deviceid") String deviceId, @PathParam("fromdate") String fromDate, @PathParam("todate") String toDate) throws DatatypeConfigurationException {
        return getDeviceLocationHistoryDetails(deviceId, fromDate, toDate);
    }

    /**
     * GetDeviceLocationHistoryDetails
     * @param deviceId
     * @param fromDate
     * @param toDate
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getdevicelocationhistorydetails/json/{json}/deviceid/{deviceid}/fromdate/{fromdate}/todate/{todate}")
    public TingcoDevice getJson_getDeviceLocationHistoryDetails(@PathParam("deviceid") String deviceId, @PathParam("fromdate") String fromDate, @PathParam("todate") String toDate) throws DatatypeConfigurationException {
        return getDeviceLocationHistoryDetails(deviceId, fromDate, toDate);
    }

    /**
     * GetDeviceDataItemInformation
     * @param deviceId
     * @param dataItemId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getdevicedataiteminformation/rest/{rest}/deviceid/{deviceid}/dataitemid/{dataitemid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetDeviceDataItemInformation", desc = "Used to Get DeviceDataItemInformation", req_Params = {"deviceId;UUID", "dataItemId;UUID", "countryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_getDeviceDataItemInformation(@PathParam("deviceid") String deviceId, @PathParam("dataitemid") String dataItemId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getDeviceDataItemInformation(deviceId, dataItemId, countryId);
    }

    /**
     * GetDeviceDataItemInformation
     * @param deviceId
     * @param dataItemId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getdevicedataiteminformation/json/{json}/deviceid/{deviceid}/dataitemid/{dataitemid}/countryid/{countryid}")
    public TingcoDevice getJson_getDeviceDataItemInformation(@PathParam("deviceid") String deviceId, @PathParam("dataitemid") String dataItemId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getDeviceDataItemInformation(deviceId, dataItemId, countryId);
    }

    /**
     * GetTrustedDevices
     * @param iTrustDeviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/gettrusteddevices/rest/{rest}/itrustdeviceid/{itrustdeviceid}/groupid/{groupid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetTrustedDevices", desc = "Used to Get TrustedDevices", req_Params = {"deviceId;UUID", "groupId;UUID", "countryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_getTrustedDevices(@PathParam("itrustdeviceid") String deviceId, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getTrustedDevices(deviceId, groupId, countryId);
    }

    /**
     * GetTrustedDevices
     * @param iTrustDeviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/gettrusteddevices/json/{json}/itrustdeviceid/{itrustdeviceid}/groupid/{groupid}/countryid/{countryid}")
    public TingcoDevice getJson_getTrustedDevices(@PathParam("itrustdeviceid") String deviceId, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getTrustedDevices(deviceId, groupId, countryId);
    }

    /**
     * GetDeviceDataItemTranslations
     * @param deviceId
     * @param dataItemid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getdevicedataitemtranslations/rest/{rest}/deviceid/{deviceid}/dataitemid/{dataitemid}")
    @RESTDoc(methodName = "GetDeviceDataItemtranslations", desc = "Used to get DeviceDataItemTranslations", req_Params = {"DeviceID;UUID", "DataItemID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_getDeviceDataItemTranslations(@PathParam("deviceid") String deviceId, @PathParam("dataitemid") String dataItemId) {
        return getDeviceDataItemTranslations(deviceId, dataItemId);
    }

    /**
     * GetDeviceDataItemTranslations
     * @param deviceId
     * @param dataItemid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getdevicedataitemtranslations/json/{json}/deviceid/{deviceid}/dataitemid/{dataitemid}")
    public TingcoDevice getJson_getDeviceDataItemTranslations(@PathParam("deviceid") String deviceId, @PathParam("dataitemid") String dataItemId) {
        return getDeviceDataItemTranslations(deviceId, dataItemId);
    }

    /**
     * GetUserFavoriteDataItems
     * @param countryId
     * @param userId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getuserfavoritedataitems/rest/{rest}/userid/{userid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetUserFavoriteDataItems", desc = "Used to get UserFavoriteDataItems", req_Params = {"UserId;UUID", "CountryID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_getUserFavoriteDataItems(@PathParam("userid") String userId, @PathParam("countryid") int countryId) {
        return getUserFavoriteDataItems(userId, countryId);
    }

    /**
     * GetUserFavoriteDataItems
     * @param countryId
     * @param userId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getuserfavoritedataitems/json/{json}/userid/{userid}/countryid/{countryid}")
    public TingcoDevice getJson_getUserFavoriteDataItems(@PathParam("userid") String userId, @PathParam("countryid") int countryId) {
        return getUserFavoriteDataItems(userId, countryId);
    }

    /**
     * GetDeviceSettings
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getdevicesettings/rest/{rest}/deviceid/{deviceid}")
    @RESTDoc(methodName = "GetDeviceSettings", desc = "Used to GetDeviceSettings", req_Params = {"DeviceID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_getDeviceSettings(@PathParam("deviceid") String deviceId) {
        return getDeviceSettings(deviceId);
    }

    /**
     * GetDeviceSettings
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getdevicesettings/json/{json}/deviceid/{deviceid}")
    public TingcoDevice getJson_getDeviceSettings(@PathParam("deviceid") String deviceId) {
        return getDeviceSettings(deviceId);
    }

    /**
     * DeleteDeviceSettings
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deletealldevicesettings/rest/{rest}/deviceid/{deviceid}")
    @RESTDoc(methodName = "DeleteDeviceSettings", desc = "Used to Delete DeviceSettings", req_Params = {"DeviceID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_deleteDeviceSettings(@PathParam("deviceid") String deviceId) {
        return deleteDeviceSettingsbyDeviceId(deviceId);
    }

    /**
     * DeleteDeviceSettings
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deletealldevicesettings/json/{json}/deviceid/{deviceid}")
    public TingcoDevice getJson_deleteDeviceSettings(@PathParam("deviceid") String deviceId) {
        return deleteDeviceSettingsbyDeviceId(deviceId);
    }

    /**
     * SendDeviceTypeCommands
     * @param deviceId
     * @param deviceTypeCommandId
     * @param DataItemValue
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/senddevicetypecommands/rest/{rest}/deviceid/{deviceid}/devicetypecommandid/{devicetypecommandid}/countryid/{countryid}{dataitemvalue:(/dataitemvalue/[^/]+?)?}")
    @RESTDoc(methodName = "SendDeviceTypeCommands", desc = "Send the XML to the sonicTopics", req_Params = {"DeviceID;UUID", "DeviceTypeCommandID;UUID"}, opt_Params = {"DataItemValue;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_sendDeviceTypeCommands(@PathParam("deviceid") String deviceId, @PathParam("devicetypecommandid") String deviceTypeCommandId, @PathParam("dataitemvalue") String dataItemValue, @PathParam("countryid") String countryid) {
        return sendDeviceTypeCommands(deviceId, deviceTypeCommandId, dataItemValue, countryid);
    }

    /**
     * SendDeviceTypeCommands
     * @param deviceId
     * @param deviceTypeCommandId
     * @param DataItemValue
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/senddevicetypecommands/json/{json}/deviceid/{deviceid}/devicetypecommandid/{devicetypecommandid}/countryid/{countryid}{dataitemvalue:(/dataitemvalue/[^/]+?)?}")
    public TingcoDevice getJson_sendDeviceTypeCommands(@PathParam("deviceid") String deviceId, @PathParam("devicetypecommandid") String deviceTypeCommandId, @PathParam("dataitemvalue") String dataItemValue, @PathParam("countryid") String countryid) {
        return sendDeviceTypeCommands(deviceId, deviceTypeCommandId, dataItemValue, countryid);
    }

    /**
     * Device_Update
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/update/rest/{rest}{objecttags :(/objecttags/[^/]+?)?}")
    @RESTDoc(methodName = "Device_Update", desc = "Used to update a new Device", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_Update(String content, @PathParam("objecttags") String objectTags) throws DatatypeConfigurationException {
        return deviceUpdate(content, objectTags);
    }

    /**
     * Device_Update
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/update/json/{json}{objecttags :(/objecttags/[^/]+?)?}")
    public TingcoDevice postJson_Update(String content, @PathParam("objecttags") String objectTags) throws DatatypeConfigurationException {
        return deviceUpdate(content, objectTags);
    }

    /**
     * IsExistsDeviceName2
     * @param deviceTypeID
     * @param deviceName2
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/isexistsdevicename2/rest/{rest}/devicetypeid/{devicetypeid}/devicename2/{devicename2}")
    @RESTDoc(methodName = "IsExistsDeviceName2", desc = "To Check that DeviceName2 exists for Given DeviceTypeID", req_Params = {"DeviceTypeID;UUID", "DeviceName2;String"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoDevice getXml_IsExistsDeviceName2(@PathParam("devicetypeid") String deviceTypeID, @PathParam("devicename2") String deviceName2) {
        return isDeviceName2Exists4DeviceTypeID(deviceTypeID, deviceName2);
    }

    /**
     * IsExistsDeviceName2
     * @param deviceTypeID
     * @param deviceName2
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/isexistsdevicename2/rest/{rest}/devicetypeid/{devicetypeid}/devicename2/{devicename2}")
    public TingcoDevice postXml_IsExistsDeviceName2(@PathParam("devicetypeid") String deviceTypeID, @PathParam("devicename2") String deviceName2) {
        return isDeviceName2Exists4DeviceTypeID(deviceTypeID, deviceName2);
    }

    /**
     * IsExistsDeviceName2
     * @param deviceTypeID
     * @param deviceName2
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/isexistsdevicename2/json/{json}/devicetypeid/{devicetypeid}/devicename2/{devicename2}")
    public TingcoDevice getJson_IsExistsDeviceName2(@PathParam("devicetypeid") String deviceTypeID, @PathParam("devicename2") String deviceName2) {
        return isDeviceName2Exists4DeviceTypeID(deviceTypeID, deviceName2);
    }

    /**
     * IsExistsDeviceName2
     * @param deviceTypeID
     * @param deviceName2
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/isexistsdevicename2/json/{json}/devicetypeid/{devicetypeid}/devicename2/{devicename2}")
    public TingcoDevice postJson_IsExistsDeviceName2(@PathParam("devicetypeid") String deviceTypeID, @PathParam("devicename2") String deviceName2) {
        return isDeviceName2Exists4DeviceTypeID(deviceTypeID, deviceName2);
    }

    /**
     * IsExistsDeviceName
     * @param deviceName
     * @param groupID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/isexistsdevicename/rest/{rest}/devicename/{devicename}/groupid/{groupid}")
    @RESTDoc(methodName = "IsExistsDeviceName", desc = "To Check that DeviceName exists for Given GroupID", req_Params = {"DeviceName;String", "GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoDevice getXml_IsExistsDeviceName(@PathParam("devicename") String deviceName, @PathParam("groupid") String groupID) {
        return isDeviceNameExists4GroupID(deviceName, groupID);
    }

    /**
     * IsExistsDeviceName
     * @param deviceName
     * @param groupID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/isexistsdevicename/rest/{rest}/devicename/{devicename}/groupid/{groupid}")
    public TingcoDevice postXml_IsExistsDeviceName(@PathParam("devicename") String deviceName, @PathParam("groupid") String groupID) {
        return isDeviceNameExists4GroupID(deviceName, groupID);
    }

    /**
     * IsExistsDeviceName
     * @param deviceName
     * @param groupID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/isexistsdevicename/json/{json}/devicename/{devicename}/groupid/{groupid}")
    public TingcoDevice getJson_IsExistsDeviceName(@PathParam("devicename") String deviceName, @PathParam("groupid") String groupID) {
        return isDeviceNameExists4GroupID(deviceName, groupID);
    }

    /**
     * IsExistsDeviceName
     * @param deviceName
     * @param groupID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/isexistsdevicename/json/{json}/devicename/{devicename}/groupid/{groupid}")
    public TingcoDevice postJson_IsExistsDeviceName(@PathParam("devicename") String deviceName, @PathParam("groupid") String groupID) {
        return isDeviceNameExists4GroupID(deviceName, groupID);
    }

    /**
     * GetDeviceStatus
     * @param countryId
     * @param deviceId
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getstatus/rest/{rest}/countryid/{countryid}{deviceid:(/deviceid/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}")
    @RESTDoc(methodName = "GetDeviceStatus", desc = "To Get DeviceStatus", req_Params = {"CountryID;int"}, opt_Params = {"DeviceID;UUID", "GroupID;UUID"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoDevice getXml_GetStatus(@PathParam("countryid") int countryId, @PathParam("deviceid") String deviceId, @PathParam("groupid") String groupId) {
        return getDeviceStatus(countryId, deviceId, groupId);
    }

    /**
     * GetDeviceStatus
     * @param countryId
     * @param deviceId
     * @param groupId
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/getstatus/rest/{rest}/countryid/{countryid}{deviceid:(/deviceid/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}")
    public TingcoDevice postXml_GetStatus(@PathParam("countryid") int countryId, @PathParam("deviceid") String deviceId, @PathParam("groupid") String groupId) {
        return getDeviceStatus(countryId, deviceId, groupId);
    }

    /**
     * GetDeviceStatus
     * @param countryId
     * @param deviceId
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getstatus/json/{json}/countryid/{countryid}{deviceid:(/deviceid/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}")
    public TingcoDevice getJson_GetStatus(@PathParam("countryid") int countryId, @PathParam("deviceid") String deviceId, @PathParam("groupid") String groupId) {
        return getDeviceStatus(countryId, deviceId, groupId);
    }

    /**
     * GetDeviceStatus
     * @param countryId
     * @param deviceId
     * @param groupId
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/getstatus/json/{json}/countryid/{countryid}{deviceid:(/deviceid/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}")
    public TingcoDevice postJson_GetStatus(@PathParam("countryid") int countryId, @PathParam("deviceid") String deviceId, @PathParam("groupid") String groupId) {
        return getDeviceStatus(countryId, deviceId, groupId);
    }

    /**
     * GetDeviceDetails
     * @param countryId
     * @param groupId
     * @param searchField
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getdetails/rest/{rest}/countryid/{countryid}/groupid/{groupid}/searchfield/{searchfield}")
    @RESTDoc(methodName = "GetDeviceDetails", desc = "To Get DeviceDetails", req_Params = {"CountryID;int", "SearchField;String", "GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoDevice getXml_GetDetails(@PathParam("countryid") int countryId, @PathParam("groupid") String groupId, @PathParam("searchfield") String searchField) {
        return getDeviceDetails(countryId, groupId, searchField);
    }

    /**
     * GetDeviceDetails
     * @param countryId
     * @param groupId
     * @param searchField
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getdetails/json/{json}/countryid/{countryid}/groupid/{groupid}/searchfield/{searchfield}")
    public TingcoDevice getJson_GetDetails(@PathParam("countryid") int countryId, @PathParam("groupid") String groupId, @PathParam("searchfield") String searchField) {
        return getDeviceDetails(countryId, groupId, searchField);
    }

    @GET
    @Produces("application/xml")
    @Path("/devicesettinginfo/rest/{rest}/deviceid/{deviceid}/devicesettingid/{devicesettingid}")
    @RESTDoc(methodName = "DeviceSEttingInfo", desc = "To Get Device  Information", req_Params = {"DeviceID;UUID", "DeviceSettingID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_deviceSettingInfo(@PathParam("deviceid") String deviceId, @PathParam("devicesettingid") String deviceSettingId) {
        return deviceSettingInfo(deviceId, deviceSettingId);
    }

    @GET
    @Produces("application/json")
    @Path("/devicesettinginfo/json/{json}/deviceid/{deviceid}/devicesettingid/{devicesettingid}")
    public TingcoDevice getJson_deviceSettingInfo(@PathParam("deviceid") String deviceId, @PathParam("devicesettingid") String deviceSettingId) {
        return deviceSettingInfo(deviceId, deviceSettingId);
    }

    /**
     * GetDeviceConnectionStatusByGroupIDAndDeviceID
     * @param countryId
     * @param groupId
     * @param searchField
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getconnectionstatusbyid/rest/{rest}{deviceid:(/deviceid/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}")
    @RESTDoc(methodName = "GetDeviceConnectionStatusByGroupIDAndDeviceID", desc = "To Get ConnectionStatus", req_Params = {"rest;string"}, opt_Params = {"DeviceID;String", "GroupID;UUID"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetConnectionStatus(@PathParam("groupid") String groupId, @PathParam("deviceid") String deviceId) {
        return getDeviceConnectionStatusById(groupId, deviceId);
    }

    /**
     * GetDeviceConnectionStatusByGroupIDAndDeviceID
     * @param countryId
     * @param groupId
     * @param searchField
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getconnectionstatusbyid/json/{json}{deviceid:(/deviceid/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}")
    public TingcoDevice getJson_GetConnectionStatus(@PathParam("groupid") String groupId, @PathParam("deviceid") String deviceId) {
        return getDeviceConnectionStatusById(groupId, deviceId);
    }

    /**
     * GetDeviceStatusDataItemsByDeviceID
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getdevicestatusdataitems/rest/{rest}/deviceid/{deviceid}")
    @RESTDoc(methodName = "GetDeviceStatusDataItemsByDeviceID", desc = "To Get DeviceStatusDataItems", req_Params = {"DeviceID;String"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetDeviceStatusDataItems(@PathParam("deviceid") String deviceId) {
        return getDeviceStatusDataItemsByDeviceId(deviceId);
    }

    /**
     * GetDeviceStatusDataItemsByDeviceID
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getdevicestatusdataitems/json/{json}/deviceid/{deviceid}")
    public TingcoDevice getJson_GetDeviceStatusDataItems(@PathParam("deviceid") String deviceId) {
        return getDeviceStatusDataItemsByDeviceId(deviceId);
    }

    /**
     * CheckDeviceConnectionAndDeviceTypeName
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/checkdeviceconnectionandtypename/rest/{rest}/deviceid/{deviceid}")
    @RESTDoc(methodName = "CheckDeviceConnectionAndDeviceTypeName", desc = "Get the Connection Status and DeviceTypeName", req_Params = {"DeviceID;String"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_CheckConnectionAndName(@PathParam("deviceid") String deviceId) {
        return checkDeviceConnectionAndDeviceTypeName(deviceId);
    }

    /**
     * CheckDeviceConnectionAndDeviceTypeName
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/checkdeviceconnectionandtypename/json/{json}/deviceid/{deviceid}")
    public TingcoDevice getJson_CheckConnectionAndName(@PathParam("deviceid") String deviceId) {
        return checkDeviceConnectionAndDeviceTypeName(deviceId);
    }

    /**
     * GetDevicesByGroupID
     * @param groupId
     * @param searchString
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getbygroupid/rest/{rest}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}{maxresult:(/maxresult/[^/]+?)?}")
    @RESTDoc(methodName = "GetDevicesByGroupID", desc = "Get Device List filetered by groupID", req_Params = {"GroupID;UUID", "CountryID;int"}, opt_Params = {"SearchString;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_ByGroupID(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("searchstring") String searchString, @PathParam("maxresult") String maxResult) {
        return getDevicesByGroupID(groupId, countryId, searchString, maxResult);
    }

    /**
     * GetDevicesByGroupID
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getbygroupid/json/{json}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}{maxresult:(/maxresult/[^/]+?)?}")
    public TingcoDevice getJson_ByGroupID(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("searchstring") String searchString, @PathParam("maxresult") String maxResult) {
        return getDevicesByGroupID(groupId, countryId, searchString, maxResult);
    }

    /**
     * DeleteDevicePending
     * @param deviceid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deletedevicepending/rest/{rest}/deviceid/{deviceid}")
    @RESTDoc(methodName = "DeleteDevicePending", desc = "Used to Delete the table devicependingdelete", req_Params = {"deviceid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_deleteDevicePending(@PathParam("deviceid") String deviceId) throws DatatypeConfigurationException {
        return deleteDevicePending(deviceId);
    }

    /**
     * DeleteDevicePending
     * @param deviceid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deletedevicepending/json/{json}/deviceid/{deviceid}")
    public TingcoDevice getJson_deleteDevicePending(@PathParam("deviceid") String deviceId) throws DatatypeConfigurationException {
        return deleteDevicePending(deviceId);
    }

    /**
     * GetDeviceOperationsStatus
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getdeviceoperationsstatus/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetDeviceOperationsStatus", desc = "Used to Get DeviceOperationsStatus", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_getDeviceOperationsStatus(@PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getDeviceOperationsStatus(countryId);
    }

    /**
     * GetDeviceOperationsStatus
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getdeviceoperationsstatus/json/{json}/countryid/{countryid}")
    public TingcoDevice getJson_getDeviceOperationsStatus(@PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getDeviceOperationsStatus(countryId);
    }

    /**
     * DeleteObjectGroup
     * @param deviceId
     * @param objectGroupId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deleteobjectgroup/rest/{rest}/deviceid/{deviceid}/objectgroupid/{objectgroupid}")
    @RESTDoc(methodName = "DeleteObjectGroup", desc = "Used to Delete ObjectGroup", req_Params = {"deviceId;UUID", "objectGroupId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_deleteObjectGroup(@PathParam("deviceid") String deviceId, @PathParam("objectgroupid") String objectGroupId) throws DatatypeConfigurationException {
        return deleteObjectGroup(deviceId, objectGroupId);
    }

    /**
     * DeleteObjectGroup
     * @param deviceId
     * @param objectGroupId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deleteobjectgroup/json/{json}/deviceid/{deviceid}/objectgroupid/{objectgroupid}")
    public TingcoDevice getJson_deleteObjectGroup(@PathParam("deviceid") String deviceId, @PathParam("objectgroupid") String objectGroupId) throws DatatypeConfigurationException {
        return deleteObjectGroup(deviceId, objectGroupId);
    }

    /**
     * GetDeviceForDeviceLocationHistory
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getdevicefordevicelocationhistory/rest/{rest}")
    @RESTDoc(methodName = "GetDeviceForDeviceLocationHistory", desc = "Used to Get Device For DeviceLocationHistory", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_getDeviceForDeviceLocationHistory(String content) {
        return getDeviceForDeviceLocationHistory(content);
    }

    /**
     * GetDeviceForDeviceLocationHistory
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getdevicefordevicelocationhistory/json/{json}")
    public TingcoDevice postJson_getDeviceForDeviceLocationHistory(String content) {
        return getDeviceForDeviceLocationHistory(content);
    }

    /**
     * GetObjectContacts
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getobjectcontactsforcontainers/rest/{rest}/groupid/{groupid}")
    @RESTDoc(methodName = "GetObjectContacts", desc = "Used to Get ObjectContacts", req_Params = {"GroupId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_getObjectContactsForContainers(@PathParam("groupid") String groupId) {
        return getObjectContactsForContainers(groupId);
    }

    /**
     * GetObjectContacts
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getobjectcontactsforcontainers/json/{json}/groupid/{groupid}")
    public TingcoDevice getJson_getObjectContactsForContainers(@PathParam("groupid") String groupId) {
        return getObjectContactsForContainers(groupId);
    }

    @GET
    @Produces("application/xml")
    @Path("/getpropertiesfordevicetype/rest/{rest}/devicetypeid/{devicetypeid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetDeviceOperationsStatus", desc = "Used to Get DeviceOperationsStatus", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_PropertiesForDeviceType(@PathParam("devicetypeid") String deviceTypeId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getPropertiesForDeviceType(deviceTypeId, countryId);
    }

    @GET
    @Produces("application/xml")
    @Path("/getsettingsfordevicetype/rest/{rest}/devicetypeid/{devicetypeid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetSettingsForDeviceType", desc = "Get Settings For DeviceType", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_SettingsForDeviceType(@PathParam("devicetypeid") String deviceTypeId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getSettingsForDeviceType(deviceTypeId, countryId);
    }

    /**
     * GetContainerDeviceDetails
     * @param containerId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getcontainerdevicedetails/rest/{rest}/containerid/{containerid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetContainerDeviceDetails", desc = "Used to Get ContainerDeviceDetails", req_Params = {"containerId;UUID", "countryId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_getContainerDeviceDetails(@PathParam("containerid") String containerId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getContainerDeviceDetails(containerId, countryId);
    }

    /**
     * GetContainerDeviceDetails
     * @param containerId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getcontainerdevicedetails/rest/{rest}/containerid/{containerid}/countryid/{countryid}")
    public TingcoDevice getJson_getContainerDeviceDetails(@PathParam("containerid") String containerId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getContainerDeviceDetails(containerId, countryId);
    }

    @GET
    @Produces("application/xml")
    @Path("/getdevicemediafiles/rest/{rest}/deviceid/{deviceid}{countryid:(/countryid/[^/]+?)?}")
    public TingcoDevice getXml_getDeviceMediaFiles(@PathParam("deviceid") String deviceId, @PathParam("countryid") String countryId) throws DatatypeConfigurationException {
        return getDeviceMediaFiles(deviceId, countryId);
    }

    @GET
    @Produces("application/json")
    @Path("/getdevicemediafiles/json/{json}/deviceid/{deviceid}{countryid:(/countryid/[^/]+?)?}")
    public TingcoDevice getJson_getDeviceMediaFiles(@PathParam("deviceid") String deviceId, @PathParam("countryid") String countryId) throws DatatypeConfigurationException {
        return getDeviceMediaFiles(deviceId, countryId);
    }

    @GET
    @Produces("application/xml")
    @Path("/getmediafiletypes/rest/{rest}")
    public TingcoDevice getXML_getMediaFileTypes() throws DatatypeConfigurationException {
        return getMediaFileTypes();
    }

    @GET
    @Produces("application/json")
    @Path("/getmediafiletypes/json/{json}")
    public TingcoDevice getJSON_getMediaFileTypes() throws DatatypeConfigurationException {
        return getMediaFileTypes();
    }

    @GET
    @Produces("application/xml")
    @Path("/getexistingfilesfordevice/rest/{rest}/deviceid/{deviceid}/groupid/{groupid}/countryid/{countryid}{filename:(/filename/[^/]+?)?}")
    public TingcoDevice getXML_getExistingFilesForDevice(@PathParam("deviceid") String deviceId, @PathParam("groupid") String groupId, @PathParam("countryid") String countryId, @PathParam("filename") String fileName) throws DatatypeConfigurationException {
        return getExistingFilesForDevice(deviceId, groupId, fileName, countryId);
    }

    @GET
    @Produces("application/json")
    @Path("/getexistingfilesfordevice/json/{json}/deviceid/{deviceid}/groupid/{groupid}/countryid/{countryid}{filename:(/filename/[^/]+?)?}")
    public TingcoDevice getJson_getExistingFilesForDevice(@PathParam("deviceid") String deviceId, @PathParam("groupid") String groupId, @PathParam("countryid") String countryId, @PathParam("filename") String fileName) throws DatatypeConfigurationException {
        return getExistingFilesForDevice(deviceId, groupId, fileName, countryId);
    }

    /**
     * GetAllDeviceDataItems
     * @param countryId
     * @param userId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getalldevicedataitems/rest/{rest}/countryid/{countryid}/userid/{userid}")
    public TingcoDevice getXML_getAllDeviceDataItems(@PathParam("userid") String userId, @PathParam("countryid") int countryId) {
        return getAllDeviceDataItems(userId, countryId);
    }

    /**
     * GetAllDeviceDataItems
     * @param countryId
     * @param userId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getalldevicedataitems/json/{json}/countryid/{countryid}/userid/{userid}")
    public TingcoDevice getJson_getAllDeviceDataItems(@PathParam("userid") String userId, @PathParam("countryid") int countryId) {
        return getAllDeviceDataItems(userId, countryId);
    }

    /**
     * GetUsageLogDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getusagelogdetails/rest/{rest}{functionarea:(/functionarea/[^/]+?)?}")
    @RESTDoc(methodName = "GetUsageLogDetails", desc = "Used to Get UsageLogDetails", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_getUsageLogDetails(String content, @PathParam("functionarea") String functionArea) {
        return getUsageLogDetails(content, functionArea);
    }

    /**
     * GetUsageLogDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getusagelogdetails/json/{json}{functionarea:(/functionarea/[^/]+?)?}")
    public TingcoDevice postJson_getUsageLogDetails(String content, @PathParam("functionarea") String functionArea) {
        return getUsageLogDetails(content, functionArea);
    }

    /**
     * GetDeviceDetailsBySearchCriteria
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getdevicedetailsbysearchcriteria/rest/{rest}{objecttags :(/objecttags/[^/]+?)?}")
    @RESTDoc(methodName = "GetDeviceDetailsBySearchCriteria", desc = "Used to Get DeviceDetails By SearchCriteria", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_getDeviceDetailsBySearchCriteria(String content, @PathParam("objecttags") String objectTags) {
        return getDeviceDetailsBySearchCriteria(content, objectTags);
    }

    /**
     * GetDeviceDetailsBySearchCriteria
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getdevicedetailsbysearchcriteria/json/{json}{objecttags :(/objecttags/[^/]+?)?}")
    public TingcoDevice postJson_getDeviceDetailsBySearchCriteria(String content, @PathParam("objecttags") String objectTags) {
        return getDeviceDetailsBySearchCriteria(content, objectTags);
    }

    @POST
    @Produces("application/xml")
    @Consumes("application/xml")
    @Path("/getdeviceconnectionstatusdetails/rest/{rest}{objecttags :(/objecttags/[^/]+?)?}")
    @RESTDoc(methodName = "Get Device Connection Status", desc = "Used to connection status of Device", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_GetDeviceConnectionStatusDetails(String content, @PathParam("objecttags") String objectTags) throws DatatypeConfigurationException {
        return getDeviceConnectionStatusDetails(content, objectTags);
    }

    @POST
    @Produces("application/json")
    @Consumes("application/xml")
    @Path("/getdeviceconnectionstatusdetails/json/{json}{objecttags :(/objecttags/[^/]+?)?}")
    @RESTDoc(methodName = "Get Device Connection Status", desc = "Used to connection status of Device", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postJson_GetDeviceConnectionStatusDetails(String content, @PathParam("objecttags") String objectTags) throws DatatypeConfigurationException {
        return getDeviceConnectionStatusDetails(content, objectTags);
    }

    @POST
    @Produces("application/xml")
    @Consumes("application/xml")
    @Path("/getdeviceconnectionstatusdetailstcmv3/rest/{rest}{objecttags :(/objecttags/[^/]+?)?}")
    @RESTDoc(methodName = "Get Device Connection Status", desc = "Used to connection status of Device", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXmlTCMV3_GetDeviceConnectionStatusDetails(String content, @PathParam("objecttags") String objectTags) throws DatatypeConfigurationException {
        return getDeviceConnectionStatusDetailsTCMV3(content, objectTags);
    }

    @POST
    @Produces("application/json")
    @Consumes("application/xml")
    @Path("/getdeviceconnectionstatusdetailstcmv3/json/{json}{objecttags :(/objecttags/[^/]+?)?}")
    @RESTDoc(methodName = "Get Device Connection Status", desc = "Used to connection status of Device", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postJsonTCMV3_GetDeviceConnectionStatusDetails(String content, @PathParam("objecttags") String objectTags) throws DatatypeConfigurationException {
        return getDeviceConnectionStatusDetailsTCMV3(content, objectTags);
    }

    /**
     * AddObjectGroup
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addobjectgroup/rest/{rest}")
    @RESTDoc(methodName = "AddObjectGroup", desc = "Used to Add ObjectGroup in OGMDevice Table", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXML_addObjectGroup(String content) {
        return addObjectGroup(content);
    }

    /**
     * AddObjectGroup
     * @param content
     * @return
     */
    @POST
    @Produces("application/json")
    @Consumes("application/xml")
    @Path("/addobjectgroup/json/{json}")
    public TingcoDevice postJson_addObjectGroup(String content) {
        return addObjectGroup(content);
    }

    /**
     * AddDeviceLinks
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/adddevicelinks/rest/{rest}")
    @RESTDoc(methodName = "AddDeviceLinks", desc = "Used to Add DeviceLinks", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_addDeviceLinks(String content) throws DatatypeConfigurationException {
        return addDeviceLinks(content);
    }

    /**
     * AddDeviceLinks
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/adddevicelinks/json/{json}")
    public TingcoDevice postJson_addDeviceLinks(String content) throws DatatypeConfigurationException {
        return addDeviceLinks(content);
    }

    /**
     * AddNewMediaFile
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addnewmediafile/rest/{rest}")
    @RESTDoc(methodName = "AddNewMediaFile", desc = "Used to Add NewMediaFile", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXML_addNewMediaFile(String content) {
        return addNewMediaFile(content);
    }

    /**
     * AddNewMediaFile
     * @param content
     * @return
     */
    @POST
    @Produces("application/json")
    @Consumes("application/xml")
    @Path("/addnewmediafile/json/{json}")
    public TingcoDevice postJson_addNewMediaFile(String content) {
        return addNewMediaFile(content);
    }

    /**
     * GetSensorMonitors
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getsensormonitors/rest/{rest}")
    @RESTDoc(methodName = "GetSensorMonitors", desc = "Used to Get SensorMonitors", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXML_getSensorMonitors(String content) {
        return getSensorMonitors(content);
    }

    /**
     * GetSensorMonitors
     * @param content
     * @return
     */
    @POST
    @Produces("application/json")
    @Consumes("application/xml")
    @Path("/getsensormonitors/json/{json}")
    public TingcoDevice postJson_getSensorMonitors(String content) {
        return getSensorMonitors(content);
    }

    /**
     * GetMediaFiles
     * @param mediaFileId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getmediafiles/rest/{rest}/mediafileid/{mediafileid}")
    @RESTDoc(methodName = "GetMediaFiles", desc = "Used to Get MediaFiles", req_Params = {"mediaFileId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice getXML_getMediaFiles(@PathParam("mediafileid") String mediaFileId) {
        return getMediaFiles(mediaFileId);
    }

    /**
     * GetMediaFiles
     * @param mediaFileId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getmediafiles/json/{json}/mediafileid/{mediafileid}")
    public TingcoDevice getJson_getMediaFiles(@PathParam("mediafileid") String mediaFileId) {
        return getMediaFiles(mediaFileId);
    }

    /**
     * DeleteMediaFiles
     * @param deviceId
     * @param mediaFileId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deletemediafiles/rest/{rest}/deviceid/{deviceid}/mediafileid/{mediafileid}")
    @RESTDoc(methodName = "DeleteMediaFiles", desc = "Used to Delete MediaFiles", req_Params = {"deviceId;string", "mediaFileId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice getXML_deleteMediaFiles(@PathParam("deviceid") String deviceId, @PathParam("mediafileid") String mediaFileId) {
        return deleteMediaFiles(deviceId, mediaFileId);
    }

    /**
     * DeleteMediaFiles
     * @param deviceId
     * @param mediaFileId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deletemediafiles/json/{json}/deviceid/{deviceid}/mediafileid/{mediafileid}")
    public TingcoDevice getJson_deleteMediaFiles(@PathParam("deviceid") String deviceId, @PathParam("mediafileid") String mediaFileId) {
        return deleteMediaFiles(deviceId, mediaFileId);
    }

    /**
     * AddExistingMediaFile
     * @param deviceId
     * @param mediaFileId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/addexistingmediafile/rest/{rest}/deviceid/{deviceid}/mediafileid/{mediafileid}")
    @RESTDoc(methodName = "AddExistingMediaFile", desc = "Used to Add ExistingMediaFile", req_Params = {"deviceId;string", "mediaFileId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice getXML_addExistingMediaFiles(@PathParam("deviceid") String deviceId, @PathParam("mediafileid") String mediaFileId) {
        return addExistingMediaFiles(deviceId, mediaFileId);
    }

    /**
     * AddExistingMediaFile
     * @param deviceId
     * @param mediaFileId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/addexistingmediafile/json/{json}/deviceid/{deviceid}/mediafileid/{mediafileid}")
    public TingcoDevice getJson_addExistingMediaFiles(@PathParam("deviceid") String deviceId, @PathParam("mediafileid") String mediaFileId) {
        return addExistingMediaFiles(deviceId, mediaFileId);
    }

    /**
     *  GetDeviceCommandScheduleDetails
     * @param deviceId
     * @param CountryID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getdevicecommandscheduledetails/rest/{rest}/deviceid/{deviceid}/countryid/{countryid}")
    public TingcoDevice getXML_DeviceCommandScheduleDetails(@PathParam("deviceid") String deviceId, @PathParam("countryid") String CountryID) {
        return getDeviceCommandScheduleDetails(deviceId, CountryID);
    }

    /**
     *  GetDeviceCommandScheduleDetails
     * @param deviceId
     * @param CountryID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/json")
    @Path("/getdevicecommandscheduledetails/json/{json}/deviceid/{deviceid}/countryid/{countryid}")
    public TingcoDevice getjson_DeviceCommandScheduleDetails(@PathParam("deviceid") String deviceId, @PathParam("countryid") String CountryID) {
        return getDeviceCommandScheduleDetails(deviceId, CountryID);
    }

    /**
     * GetDeviceTypeCommands
     * @param deviceId
     * @param CountryID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/xml")
    @Path("/getdevicetypecommands/rest/{rest}/deviceid/{deviceid}/countryid/{countryid}")
    public TingcoDevice getXML_GetDeviceTypeCommands(@PathParam("deviceid") String deviceId, @PathParam("countryid") String CountryID) {
        return getDeviceTypeCommands(deviceId, CountryID);
    }

    /**
     * GetDeviceTypeCommands
     * @param deviceId
     * @param CountryID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/json")
    @Path("/getdevicetypecommands/json/{json}/deviceid/{deviceid}/countryid/{countryid}")
    public TingcoDevice getJson_GetDeviceTypeCommands(@PathParam("deviceid") String deviceId, @PathParam("countryid") String CountryID) {
        return getDeviceTypeCommands(deviceId, CountryID);
    }

    /**
     *
     * AddDeviceCommandSchedule
     * @param content
     * @return TingcoDevice
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/adddevicecommandschedule/rest/{rest}")
    @RESTDoc(methodName = "AddDeviceCommandSchedule", desc = "Used to ADD DeviceCommandSchedules", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXML_AddDeviceCommandSchedule(String content) {
        return AddDeviceCommandSchedule(content);
    }

    /**
     *
     * AddDeviceCommandSchedule
     * @param content
     * @return TingcoDevice
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/adddevicecommandschedule/json/{json}")
    @RESTDoc(methodName = "AddDeviceCommandSchedule", desc = "Used to ADD DeviceCommandSchedules", req_Params = {"json;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postJson_AddDeviceCommandSchedule(String content) {
        return AddDeviceCommandSchedule(content);
    }

    /**
     * UpdateDeviceCommandSchedule
     * @param content
     * @return TingcoDevice
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/updatedevicecommandschedule/rest/{rest}")
    @RESTDoc(methodName = "UpdateDeviceCommandSchedule", desc = "Used to update DeviceCommandSchedules", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXML_UpdateDeviceCommandSchedule(String content) {
        return UpdateDeviceCommandSchedule(content);
    }

    /**
     * DeleteDeviceCommandSchedule
     * @param deviceCommandScheduleID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/xml")
    @Path("/deletedevicecommandschedule/rest/{rest}/devicecommandscheduleid/{devicecommandscheduleid}")
    public TingcoDevice getXML_DeleteDeviceCommandSchedule(@PathParam("devicecommandscheduleid") String deviceCommandScheduleID) {
        return DeleteDeviceCommandSchedule(deviceCommandScheduleID);
    }

    /**
     * DeleteDeviceCommandSchedule
     * @param deviceCommandScheduleID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/json")
    @Path("/deletedevicecommandschedule/json/{json}/devicecommandscheduleid/{devicecommandscheduleid}")
    public TingcoDevice getJson_DeleteDeviceCommandSchedule(@PathParam("devicecommandscheduleid") String deviceCommandScheduleID) {
        return DeleteDeviceCommandSchedule(deviceCommandScheduleID);
    }

    /**
     * GetDeviceCommandScheduleDetailsByDeviceCommandScheduleID
     * @param deviceCommandScheduleID
     * @param countryID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/xml")
    @Path("/getdevicecommandscheduledetailsbydevicecommandscheduleid/rest/{rest}/devicecommandscheduleid/{devicecommandscheduleid}/countryid/{countryid}")
    public TingcoDevice getXML_GetDeviceCommandScheduleDetailsByDeviceCommandScheduleID(@PathParam("devicecommandscheduleid") String deviceCommandScheduleID, @PathParam("countryid") String countryID) {
        return getDeviceCommandScheduleDetailsByDeviceCommandScheduleID(deviceCommandScheduleID, countryID);
    }

    /**
     * GetDeviceCommandScheduleDetailsByDeviceCommandScheduleID
     * @param deviceCommandScheduleID
     * @param countryID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/json")
    @Path("/getdevicecommandscheduledetailsbydevicecommandscheduleid/json/{json}/devicecommandscheduleid/{devicecommandscheduleid}/countryid/{countryid}")
    public TingcoDevice getJson_GetDeviceCommandScheduleDetailsByDeviceCommandScheduleID(@PathParam("devicecommandscheduleid") String deviceCommandScheduleID, @PathParam("countryid") String countryID) {
        return getDeviceCommandScheduleDetailsByDeviceCommandScheduleID(deviceCommandScheduleID, countryID);
    }

    /**
     * GetDeviceCountReportDetails
     * @param content
     * @return se.info24.devicejaxbPost.TingcoDevice
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getdevicecountreportdetails/rest/{rest}")
    @RESTDoc(methodName = "GetDeviceCountReportDetails", desc = "Used to GetDeviceCountReportDetails", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public se.info24.devicejaxbPost.TingcoDevice postXml_GetDeviceCountReportDetails(String content) throws DatatypeConfigurationException {
        return getDeviceCountReportDetails(content);
    }

    /**
     * GetDeviceCountReportDetails
     * @param content
     * @return se.info24.devicejaxbPost.TingcoDevice
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getdevicecountreportdetails/json/{json}")
    @RESTDoc(methodName = "GetDeviceCountReportDetails", desc = "Used to GetDeviceCountReportDetails", req_Params = {"json;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public se.info24.devicejaxbPost.TingcoDevice postJson_GetDeviceCountReportDetails(String content) throws DatatypeConfigurationException {
        return getDeviceCountReportDetails(content);
    }

    /**
     * GetSalesByProviderDetails
     * @param content
     * @return TingcoDevice
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getsalesbyproviderdetails/rest/{rest}")
    @RESTDoc(methodName = "GetSalesByProviderDetails", desc = "Used to SalesByProviderDetails", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_SalesByProviderDetails(String content) throws DatatypeConfigurationException {
        return getSalesByProviderDetails(content);
    }

    /**
     * GetSalesByProviderDetails
     * @param content
     * @return TingcoDevice
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getsalesbyproviderdetails/json/{json}")
    @RESTDoc(methodName = "GetSalesByProviderDetails", desc = "Used to SalesByProviderDetails", req_Params = {"json;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postJson_SalesByProviderDetails(String content) throws DatatypeConfigurationException {
        return getSalesByProviderDetails(content);
    }

    /**
     * GetSMSCommands
     * @param countryID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/xml")
    @Path("/getsmscommands/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetSMSCommands", desc = "Used to Get SMSCommands", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoCommands getXML_getSMSCommands(@PathParam("countryid") int countryID) {
        return getSMSCommands(countryID);
    }

    /**
     * GetSMSCommands
     * @param countryID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/json")
    @Path("/getsmscommands/json/{json}/countryid/{countryid}")
    public TingcoCommands getJson_getSMSCommands(@PathParam("countryid") int countryID) {
        return getSMSCommands(countryID);
    }

    /**
     * addSMSCommands
     * @param commandid
     * @param deviceid
     * @param targetstring
     * @param devicetypecommandid
     * @return TingcoDevice
     */
    @GET
    @Produces("application/xml")
    @Path("/addsmscommands/rest/{rest}/commandid/{commandid}/deviceid/{deviceid}/targetstring/{targetstring}/devicetypecommandid/{devicetypecommandid}")
    @RESTDoc(methodName = "AddSMSCommands", desc = "Used to Add SMSCommands", req_Params = {"commandid;string", "deviceid;string", "targetstring;string", "devicetypecommandid;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoCommands getXML_addSMSCommands(@PathParam("commandid") String commandId, @PathParam("deviceid") String deviceId, @PathParam("targetstring") String targetString, @PathParam("devicetypecommandid") String deviceTypeCommandId) {
        return addSMSCommands(commandId, deviceId, targetString, deviceTypeCommandId);
    }

    /**
     * addSMSCommands
     * @param commandid
     * @param deviceid
     * @param targetstring
     * @param devicetypecommandid
     * @return TingcoDevice
     */
    @GET
    @Produces("application/json")
    @Path("/addsmscommands/json/{json}/commandid/{commandid}/deviceid/{deviceid}/targetstring/{targetstring}/devicetypecommandid/{devicetypecommandid}")
    public TingcoCommands getJson_addSMSCommands(@PathParam("commandid") String commandId, @PathParam("deviceid") String deviceId, @PathParam("targetstring") String targetString, @PathParam("devicetypecommandid") String deviceTypeCommandId) {
        return addSMSCommands(commandId, deviceId, targetString, deviceTypeCommandId);
    }

    /**
     * DeleteSMSCommands
     * @param commandid
     * @param deviceid
     * @param targetstring
     * @return TingcoDevice
     */
    @GET
    @Produces("application/xml")
    @Path("/deletesmscommands/rest/{rest}/commandid/{commandid}/deviceid/{deviceid}/targetstring/{targetstring}")
    @RESTDoc(methodName = "DeleteSMSCommands", desc = "Used to Delete SMSCommands", req_Params = {"commandid;string", "deviceid;string", "targetstring;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoCommands getXML_deleteSMSCommands(@PathParam("commandid") String commandId, @PathParam("deviceid") String deviceId, @PathParam("targetstring") String targetString) {
        return deleteSMSCommands(commandId, deviceId, targetString);
    }

    /**
     * DeleteSMSCommands
     * @param commandid
     * @param deviceid
     * @param targetstring
     * @return TingcoDevice
     */
    @GET
    @Produces("application/json")
    @Path("/deletesmscommands/json/{json}/commandid/{commandid}/deviceid/{deviceid}/targetstring/{targetstring}")
    public TingcoCommands getJson_deleteSMSCommands(@PathParam("commandid") String commandId, @PathParam("deviceid") String deviceId, @PathParam("targetstring") String targetString) {
        return deleteSMSCommands(commandId, deviceId, targetString);
    }

    /**
     * GetCommandDetailsByDeviceId
     * @param countryid
     * @param deviceid
     * @return TingcoDevice
     */
    @GET
    @Produces("application/xml")
    @Path("/getcommanddetailsbydeviceid/rest/{rest}/deviceid/{deviceid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetCommandDetailsByDeviceId", desc = "Used to Get CommandDetailsByDeviceId", req_Params = {"deviceid;string", "countryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXML_getCommandDetailsByDeviceId(@PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId) {
        return getCommandDetailsByDeviceId(deviceId, countryId);
    }

    /**
     * GetCommandDetailsByDeviceId
     * @param countryid
     * @param deviceid
     * @return TingcoDevice
     */
    @GET
    @Produces("application/json")
    @Path("/getcommanddetailsbydeviceid/json/{json}/deviceid/{deviceid}/countryid/{countryid}")
    public TingcoDevice getJson_getCommandDetailsByDeviceId(@PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId) {
        return getCommandDetailsByDeviceId(deviceId, countryId);
    }

    /**
     * DeleteDeviceDataItemByDeviceDataItemId
     * @param deviceDataItemId
     * @return TingcoDevice
     */
    @GET
    @Produces("application/xml")
    @Path("/deletedevicedataitembydevicedataitemid/rest/{rest}/devicedataitemid/{devicedataitemid}")
    @RESTDoc(methodName = "DeleteDeviceDataItem", desc = "Used to Delet eDeviceDataItem", req_Params = {"devicedataitemid;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXML_deleteDeviceDataItemByDeviceDataItemId(@PathParam("devicedataitemid") String deviceDataItemId) {
        return deleteDeviceDataItemByDeviceDataItemId(deviceDataItemId);
    }

    /**
     * DeleteDeviceDataItemByDeviceDataItemId
     * @param deviceDataItemId
     * @return TingcoDevice
     */
    @GET
    @Produces("application/json")
    @Path("/deletedevicedataitembydevicedataitemid/json/{json}/devicedataitemid/{devicedataitemid}")
    public TingcoDevice getJson_deleteDeviceDataItemByDeviceDataItemId(@PathParam("devicedataitemid") String deviceDataItemId) {
        return deleteDeviceDataItemByDeviceDataItemId(deviceDataItemId);
    }

    /**
     * GetDeviceListByAssetId
     * @param countryid
     * @param assetid
     * @return TingcoDevice
     */
    @GET
    @Produces("application/xml")
    @Path("/getdevicebyassetid/rest/{rest}/assetid/{assetid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetDeviceListByAssetId", desc = "Used to Get DeviceList By AssetId", req_Params = {"assetid;string", "countryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXML_getDeviceListByAssetId(@PathParam("assetid") String assetId, @PathParam("countryid") int countryId) {
        return getDeviceListByAssetId(assetId, countryId);
    }

    /**
     * GetDeviceListByAssetId
     * @param countryid
     * @param assetid
     * @return TingcoDevice
     */
    @GET
    @Produces("application/json")
    @Path("/getdevicebyassetid/json/{json}/assetid/{assetid}/countryid/{countryid}")
    public TingcoDevice getJson_getDeviceListByAssetId(@PathParam("assetid") String assetId, @PathParam("countryid") int countryId) {
        return getDeviceListByAssetId(assetId, countryId);
    }

    /**
     * AddRecurringPurchases
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addrecurringpurchases/rest/{rest}")
    @RESTDoc(methodName = "AddRecurringPurchases", desc = "Used to add RecurringPurchases", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_addRecurringPurchases(String content) {
        return addRecurringPurchases(content);
    }

    /**
     * AddRecurringPurchases
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addrecurringpurchases/json/{json}")
    public TingcoDevice postJson_addRecurringPurchases(String content) {
        return addRecurringPurchases(content);
    }

    /**
     * AddDeliveryAccount
     * @param content
     * @return TingcoDevice
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/adddeliveryaccount/rest/{rest}")
    @RESTDoc(methodName = "AddDeliveryAccount", desc = "Add Delivery Account", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_addDeliveryAccount(String content) throws DatatypeConfigurationException {
        return addDeliveryAccount(content);
    }

    /**
     * AddDeliveryAccount
     * @param content
     * @return TingcoDevice
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/adddeliveryaccount/json/{json}")
    public TingcoDevice postJson_addDeliveryAccount(String content) throws DatatypeConfigurationException {
        return addDeliveryAccount(content);
    }

    /**
     * UpdateServiceClientLogins
     * @param content
     * @return TingcoDevice
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/updateserviceclientlogins/rest/{rest}")
    @RESTDoc(methodName = "UpdateServiceClientLogins", desc = "UpdateServiceClientLogins", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_updateServiceClientLogins(String content) throws DatatypeConfigurationException {
        return updateServiceClientLogins(content);
    }

    /**
     * UpdateServiceClientLogins
     * @param content
     * @return TingcoDevice
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/updateserviceclientlogins/json/{json}")
    public TingcoDevice postJson_updateServiceClientLogins(String content) throws DatatypeConfigurationException {
        return updateServiceClientLogins(content);
    }

    /**
     *
     * @param recurringPurchaseID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deleterecurringpurchasebyid/rest/{rest}/recurringpurchaseid/{recurringpurchaseid}")
    @RESTDoc(methodName = "DeleteRecurringPurchaseByID", desc = "Used to Delete RecurringPurchase By ID", req_Params = {"assetid;string", "countryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXML_DeleteRecurringPurchaseByID(@PathParam("recurringpurchaseid") String recurringPurchaseID) {
        return deleteRecurringPurchaseByID(recurringPurchaseID);
    }

    /**
     *
     * @param recurringPurchaseID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deleterecurringpurchasebyid/json/{json}/recurringpurchaseid/{recurringpurchaseid}")
    public TingcoDevice getJson_DeleteRecurringPurchaseByID(@PathParam("recurringpurchaseid") String recurringPurchaseID) {
        return deleteRecurringPurchaseByID(recurringPurchaseID);
    }

    /**
     *
     * @param deviceId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getrecurringpurchaselist/rest/{rest}/deviceid/{deviceid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetDeviceProductVariants", desc = "Used to Get DeviceProductVariants", req_Params = {"GroupId;string", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetRecurringPurchaseList(@PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId) {
        return getProductVariantsForDeviceReport(deviceId, countryId);
    }

    /**
     * 
     * @param deviceId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getrecurringpurchaselist/json/{json}/deviceid/{deviceid}/countryid/{countryid}")
    public TingcoDevice getJson_GetRecurringPurchaseList(@PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId) {
        return getProductVariantsForDeviceReport(deviceId, countryId);
    }

    /**
     * GetDeviceSalesDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getdevicesalesdetailsbyuseralias/rest/{rest}")
    @RESTDoc(methodName = "GetDeviceSalesDetails", desc = "Used to Get DeviceSalesDetails", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_getDeviceSalesDetailsByUserAlias(String content) {
        return getDeviceSalesDetailsByUserAlias(content);
    }

    /**
     * GetDeviceSalesDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getdevicesalesdetailsbyuseralias/json/{json}")
    @RESTDoc(methodName = "GetDeviceSalesDetails", desc = "Used to Get DeviceSalesDetails", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postJson_getDeviceSalesDetailsByUserAlias(String content) {
        return getDeviceSalesDetailsByUserAlias(content);
    }

    /**
     * GetDeviceTypeChannels
     * @param deviceTypeId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getdevicetypechannels/rest/{rest}/devicetypeid/{devicetypeid}")
    @RESTDoc(methodName = "GetDeviceTypeChannels", desc = "Used to Get DeviceTypeChannels", req_Params = {"DeviceTypeId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetDeviceTypeChannels(@PathParam("devicetypeid") String deviceTypeId) {
        return getDeviceTypeChannels(deviceTypeId);
    }

    /**
     * GetDeviceTypeChannels
     * @param deviceTypeId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getdevicetypechannels/json/{json}/devicetypeid/{devicetypeid}")
    public TingcoDevice getJson_GetDeviceTypeChannels(@PathParam("devicetypeid") String deviceTypeId) {
        return getDeviceTypeChannels(deviceTypeId);
    }

    /**
     * DeleteDeviceTypeChannel
     * @param deviceTypeId
     * @param channelid
     * @param channelDirection
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deletedevicetypechannel/rest/{rest}/devicetypeid/{devicetypeid}/channelid/{channelid}/channeldirection/{channeldirection}")
    @RESTDoc(methodName = "DeleteDeviceTypeChannel", desc = "Delete DeviceTypeChannel ", req_Params = {"channelid;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_DeleteDeviceTypeChannel(@PathParam("devicetypeid") String deviceTypeId, @PathParam("channelid") String channelid, @PathParam("channeldirection") int channelDirection) {
        return deleteDeviceTypeChannel(deviceTypeId, channelid, channelDirection);
    }

    /**
     * DeleteDeviceTypeChannel
     * @param deviceTypeId
     * @param channelid
     * @param channelDirection
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deletedevicetypechannel/json/{json}/devicetypeid/{devicetypeid}/channelid/{channelid}/channeldirection/{channeldirection}")
    public TingcoDevice getJson_DeleteDeviceTypeChannel(@PathParam("devicetypeid") String deviceTypeId, @PathParam("channelid") String channelId, @PathParam("channeldirection") int channelDirection) {
        return deleteDeviceTypeChannel(deviceTypeId, channelId, channelDirection);
    }

    /**
     * InsertUpdateDeviceTypeChannel
     * @param flag
     * @param deviceTypeId
     * @param channelid
     * @param channelDirection
     * @param channelTag
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/insertupdatedevicetypechannel/rest/{rest}/flag/{flag}/devicetypeid/{devicetypeid}/channelid/{channelid}/oldchanneldirection/{oldchanneldirection}{newchanneldirection:(/newchanneldirection/[^/]+?)?}{channeltag:(/channeltag/[^/]+?)?}")
    @RESTDoc(methodName = "InsertUpdateDeviceTypeChannel", desc = "InsertUpdateDeviceTypeChannel", req_Params = {"channelid;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_insertUpdateDeviceTypeChannel(@PathParam("flag") String flag, @PathParam("devicetypeid") String deviceTypeId, @PathParam("channelid") String channelid, @PathParam("oldchanneldirection") int oldChannelDirection, @PathParam("newchanneldirection") String newChannelDirection, @PathParam("channeltag") String channelTag) {
        return insertUpdateDeviceTypeChannel(flag, deviceTypeId, channelid, oldChannelDirection, newChannelDirection, channelTag);
    }

    /**
     * InsertUpdateDeviceTypeChannel
     * @param flag
     * @param deviceTypeId
     * @param channelid
     * @param channelDirection
     * @param channelTag
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/insertupdatedevicetypechannel/json/{json}/flag/{flag}/devicetypeid/{devicetypeid}/channelid/{channelid}/oldchanneldirection/{oldchanneldirection}{newchanneldirection:(/newchanneldirection/[^/]+?)?}{channeltag:(/channeltag/[^/]+?)?}")
    public TingcoDevice getJson_insertUpdateDeviceTypeChannel(@PathParam("flag") String flag, @PathParam("devicetypeid") String deviceTypeId, @PathParam("channelid") String channelid, @PathParam("oldchanneldirection") int oldChannelDirection, @PathParam("newchanneldirection") String newChannelDirection, @PathParam("channeltag") String channelTag) {
        return insertUpdateDeviceTypeChannel(flag, deviceTypeId, channelid, oldChannelDirection, newChannelDirection, channelTag);
    }

    /**
     * GetAllDeviceDataItem
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getalldevicedataitem/rest/{rest}")
    @RESTDoc(methodName = "GetAllDeviceDataItem", desc = "GetAllDeviceDataItem", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetAllDeviceDataItem() {
        return getAllDeviceDataItem();
    }

    /**
     * GetAllDeviceDataItem
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getalldevicedataitem/json/{json}")
    public TingcoDevice getJson_GetAllDeviceDataItem() {
        return getAllDeviceDataItem();
    }

    /**
     * GetDevcieDataItembyDeviceDataItemID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getdevciedataitembydevicedataitemid/rest/{rest}/devicedataitemid/{devicedataitemid}")
    @RESTDoc(methodName = "GetDevcieDataItembyDeviceDataItemID", desc = "GetDevcieDataItembyDeviceDataItemID", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetDevcieDataItembyDeviceDataItemID(@PathParam("devicedataitemid") String deviceDataItemID) {
        return getDevcieDataItembyDeviceDataItemID(deviceDataItemID);
    }

    /**
     * GetDevcieDataItembyDeviceDataItemID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getdevciedataitembydevicedataitemid/json/{json}/devicedataitemid/{devicedataitemid}")
    public TingcoDevice getJson_GetDevcieDataItembyDeviceDataItemID(@PathParam("devicedataitemid") String deviceDataItemID) {
        return getDevcieDataItembyDeviceDataItemID(deviceDataItemID);
    }

    /**
     * startservice
     * @param serviceId
     * @param deviceId
     * @param command
     * @return TingcoDevice
     */
    @GET
    @Produces("application/xml")
    @Path("/startservice/rest/{rest}/serviceid/{serviceid}/deviceid/{deviceid}/command/{Command}")
    @RESTDoc(methodName = "startservice", desc = "startservice", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_StartService(@PathParam("serviceid") String serviceId, @PathParam("deviceid") String deviceId, @PathParam("command") String command) {
        return startService(serviceId, deviceId, command);
    }

    /**
     * startservice
     * @param serviceId
     * @param deviceId
     * @param command
     * @return TingcoDevice
     */
    @GET
    @Produces("application/json")
    @Path("/startservice/json/{json}/serviceid/{serviceid}/deviceid/{deviceid}/command/{Command}")
    @RESTDoc(methodName = "startservice", desc = "startservice", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getJson_StartService(@PathParam("serviceid") String serviceId, @PathParam("deviceid") String deviceId, @PathParam("command") String command) {
        return startService(serviceId, deviceId, command);
    }

    /**
     * startservice
     * @param serviceId
     * @param deviceId
     * @param command
     * @return TingcoDevice
     */
    @GET
    @Produces("application/xml")
    @Path("/stopservice/rest/{rest}/serviceid/{serviceid}/deviceid/{deviceid}/command/{Command}")
    @RESTDoc(methodName = "StopService", desc = "StopService", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_StopService(@PathParam("serviceid") String serviceId, @PathParam("deviceid") String deviceId, @PathParam("command") String command) {
        return startService(serviceId, deviceId, command);
    }

    /**
     * startservice
     * @param serviceId
     * @param deviceId
     * @param command
     * @return TingcoDevice
     */
    @GET
    @Produces("application/json")
    @Path("/stopservice/json/{json}/serviceid/{serviceid}/deviceid/{deviceid}/command/{Command}")
    @RESTDoc(methodName = "StopService", desc = "Stop Service", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getJson_StopService(@PathParam("serviceid") String serviceId, @PathParam("deviceid") String deviceId, @PathParam("command") String command) {
        return startService(serviceId, deviceId, command);
    }

    /**
     * startservice
     * @param serviceId
     * @param deviceId
     * @param command
     * @return TingcoDevice
     */
    @GET
    @Produces("application/xml")
    @Path("/restartservice/rest/{rest}/serviceid/{serviceid}/deviceid/{deviceid}/command/{Command}")
    @RESTDoc(methodName = "RestartService", desc = "RestartService", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_RestartService(@PathParam("serviceid") String serviceId, @PathParam("deviceid") String deviceId, @PathParam("command") String command) {
        return startService(serviceId, deviceId, command);
    }

    /**
     * startservice
     * @param serviceId
     * @param deviceId
     * @param command
     * @return TingcoDevice
     */
    @GET
    @Produces("application/json")
    @Path("/restartservice/json/{json}/serviceid/{serviceid}/deviceid/{deviceid}/command/{Command}")
    @RESTDoc(methodName = "RestartService", desc = "RestartService", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getJson_RestartService(@PathParam("serviceid") String serviceId, @PathParam("deviceid") String deviceId, @PathParam("command") String command) {
        return startService(serviceId, deviceId, command);
    }

    /**
     * AddMediaFile
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addmediafile/rest/{rest}")
    @RESTDoc(methodName = "AddMediaFile", desc = "Used to Add MediaFile", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_AddMediaFile(String content) {
        return addMediaFile(content);
    }

    /**
     * AddMediaFile
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addmediafile/json/{json}")
    public TingcoDevice postJson_AddMediaFile(String content) {
        return addMediaFile(content);
    }

    /**
     * DeleteMediaFile
     * @param mediaFileId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deletemediafile/rest/{rest}/mediafileid/{mediafileid}")
    @RESTDoc(methodName = "DeleteMediaFile", desc = "Delete MediaFile", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_DeleteMediaFile(@PathParam("mediafileid") String mediaFileId) {
        return deleteMediaFile(mediaFileId);
    }

    /**
     * DeleteMediaFile
     * @param mediaFileId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deletemediafile/json/{json}/mediafileid/{mediafileid}")
    public TingcoDevice getJson_DeleteMediaFile(@PathParam("mediafileid") String mediaFileId) {
        return deleteMediaFile(mediaFileId);
    }

    /**
     * UpdateMediaFilesDetails
     * @param content
     * @return
     */
    @POST
    @Produces("application/xml")
    @Consumes("application/xml")
    @Path("/updatemediafilesdetails/rest/{rest}")
    @RESTDoc(methodName = "UpdateMediaFilesDetails", desc = "Update MediaFiles Details", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_UpdateMediaFilesDetails(String content) {
        return updateMediaFilesDetails(content);
    }

    /**
     * UpdateMediaFilesDetails
     * @param content
     * @return
     */
    @POST
    @Produces("application/json")
    @Consumes("application/xml")
    @Path("/updatemediafilesdetails/json/{json}")
    public TingcoDevice getJson_UpdateMediaFilesDetails(String content) {
        return updateMediaFilesDetails(content);
    }

    /**
     * GetMediaFilesDetails
     * @param content
     * @return
     */
    @POST
    @Produces("application/xml")
    @Consumes("application/xml")
    @Path("/getmediafilesdetails/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetMediaFilesDetails", desc = "Get MediaFiles Details", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetMediaFilesDetails(String content, @PathParam("countryid") int countryId) {
        return getMediaFilesDetails(content, countryId);
    }

    /**
     * GetMediaFilesDetails
     * @param content
     * @return
     */
    @POST
    @Produces("application/json")
    @Consumes("application/xml")
    @Path("/getmediafilesdetails/json/{json}/countryid/{countryid}")
    public TingcoDevice getJson_GetMediaFilesDetails(String content, @PathParam("countryid") int countryId) {
        return getMediaFilesDetails(content, countryId);
    }

    /**
     * GetTransactionReceiptDetails
     * @param transactionCode
     * @return TingcoDevice
     */
    @GET
    @Produces("application/xml")
    @Path("/gettransactionreceiptdetails/rest/{rest}/transactioncode/{transactioncode}")
    @RESTDoc(methodName = "Get TransactionReceipt Details", desc = "GetTransactionReceiptDetails", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetTransactionReceiptDetails(@PathParam("transactioncode") String transactionCode) {
        return getTransactionReceiptDetails(transactionCode);
    }

    /**
     * GetTransactionReceiptDetails
     * @param transactionCode
     * @return TingcoDevice
     */
    @GET
    @Produces("application/json")
    @Path("/gettransactionreceiptdetails/json/{json}/transactioncode/{transactioncode}")
    @RESTDoc(methodName = "Get TransactionReceipt Details", desc = "GetTransactionReceiptDetails", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getjson_GetTransactionReceiptDetails(@PathParam("transactioncode") String transactionCode) {
        return getTransactionReceiptDetails(transactionCode);
    }

    /**
     * GetDeviceStateWidgetDetails
     * @param objectGroupId
     * @return TingcoDevice
     */
    @GET
    @Produces("application/xml")
    @Path("/getdevicestatewidgetdetails/rest/{rest}/objectgroupid/{objectgroupid}")
    @RESTDoc(methodName = "GetDeviceStateWidgetDetails", desc = "Get DeviceState Widget Details", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetDeviceStateWidgetDetails(@PathParam("objectgroupid") String objectGroupId) {
        return getDeviceStateWidgetDetails(objectGroupId);
    }

    /**
     * GetDeviceStateWidgetDetails
     * @param objectGroupId
     * @return TingcoDevice
     */
    @GET
    @Produces("application/json")
    @Path("/getdevicestatewidgetdetails/json/{json}/objectgroupid/{objectgroupid}")
    @RESTDoc(methodName = "GetDeviceStateWidgetDetails", desc = "Get DeviceState Widget Details", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getJson_GetDeviceStateWidgetDetails(@PathParam("objectgroupid") String objectGroupId) {
        return getDeviceStateWidgetDetails(objectGroupId);
    }

    /**
     * GetChargePointStatusWidgetDetails
     * @param objectGroupId
     * @return TingcoDevice
     */
    @GET
    @Produces("application/xml")
    @Path("/getchargepointstatuswidgetdetails/rest/{rest}/objectgroupid/{objectgroupid}")
    @RESTDoc(methodName = "GetChargePointStatusWidgetDetails", desc = "Get ChargePoint Status Widget Details", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetChargePointStatusWidgetDetails(@PathParam("objectgroupid") String objectGroupId) {
        return getChargePointStatusWidgetDetails(objectGroupId);
    }

    /**
     * GetChargePointStatusWidgetDetails
     * @param objectGroupId
     * @return TingcoDevice
     */
    @GET
    @Produces("application/json")
    @Path("/getchargepointstatuswidgetdetails/json/{json}/objectgroupid/{objectgroupid}")
    @RESTDoc(methodName = "GetChargePointStatusWidgetDetails", desc = "Get ChargePoint Status Widget Details", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getJson_GetChargePointStatusWidgetDetails(@PathParam("objectgroupid") String objectGroupId) {
        return getChargePointStatusWidgetDetails(objectGroupId);
    }

    /**
     * DeleteDeviceTypeCommandsById
     * @param deviceTypeCommandId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deletedevicetypecommandsbyid/rest/{rest}/devicetypecommandid/{devicetypecommandid}")
    @RESTDoc(methodName = "DeleteDeviceTypeCommandsById", desc = "DeleteDeviceTypeCommandsById", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_DeleteDeviceTypeCommandsById(@PathParam("devicetypecommandid") String deviceTypeCommandId) {
        return deleteDeviceTypeCommandsById(deviceTypeCommandId);
    }

    /**
     * DeleteDeviceTypeCommandsById
     * @param deviceTypeCommandId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deletedevicetypecommandsbyid/json/{json}/devicetypecommandid/{devicetypecommandid}")
    public TingcoDevice getJson_DeleteDeviceTypeCommandsById(@PathParam("devicetypecommandid") String deviceTypeCommandId) {
        return deleteDeviceTypeCommandsById(deviceTypeCommandId);
    }

    /**
     * GetDeviceTypeCommandDetailsById
     * @param deviceTypeCommandId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getdevicetypecommanddetailsbyid/rest/{rest}/devicetypecommandid/{devicetypecommandid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetDeviceTypeCommandDetailsById", desc = "GetDeviceTypeCommandDetailsById", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetDeviceTypeCommandDetailsById(@PathParam("devicetypecommandid") String deviceTypeCommandId, @PathParam("countryid") int countryId) {
        return getDeviceTypeCommandDetailsById(deviceTypeCommandId, countryId);
    }

    /**
     * GetDeviceTypeCommandDetailsById
     * @param deviceTypeCommandId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getdevicetypecommanddetailsbyid/json/{json}/devicetypecommandid/{devicetypecommandid}/countryid/{countryid}")
    public TingcoDevice getJson_GetDeviceTypeCommandDetailsById(@PathParam("devicetypecommandid") String deviceTypeCommandId, @PathParam("countryid") int countryId) {
        return getDeviceTypeCommandDetailsById(deviceTypeCommandId, countryId);
    }

    /**
     * GetDeviceTypeCommands
     * @param deviceId
     * @param CountryID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/xml")
    @Path("/getdevicetypecommandsbydevicetype/rest/{rest}/devicetypeid/{devicetypeid}/countryid/{countryid}")
    public TingcoDevice getXML_GetDeviceTypeCommandsByDeviceType(@PathParam("devicetypeid") String deviceId, @PathParam("countryid") String CountryID) {
        return getDeviceTypeCommandsByDeviceType(deviceId, CountryID);
    }

    /**
     * GetDeviceTypeCommands
     * @param deviceId
     * @param CountryID
     * @return TingcoDevice
     */
    @GET
    @Produces("application/json")
    @Path("/getdevicetypecommandsbydevicetype/json/{json}/devicetypeid/{devicetypeid}/countryid/{countryid}")
    public TingcoDevice getJson_GetDeviceTypeCommandsByDeviceType(@PathParam("devicetypeid") String deviceId, @PathParam("countryid") String CountryID) {
        return getDeviceTypeCommandsByDeviceType(deviceId, CountryID);
    }

    /**
     * DeleteDevicePending
     * @param deviceid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deletedevicependingstate/rest/{rest}/deviceid/{deviceid}")
    @RESTDoc(methodName = "DeleteDevicePending", desc = "Used to Delete the table devicependingdelete", req_Params = {"deviceid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_deleteDevicePendingState(@PathParam("deviceid") String deviceId) throws DatatypeConfigurationException {
        return deleteDevicePendingState(deviceId);
    }

    /**
     * DeleteDevicePending
     * @param deviceid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deletedevicependingstate/json/{json}/deviceid/{deviceid}")
    public TingcoDevice getJson_deleteDevicePendingState(@PathParam("deviceid") String deviceId) throws DatatypeConfigurationException {
        return deleteDevicePendingState(deviceId);
    }

    private TingcoDevice deleteDevicePendingState(String deviceId) {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        Session session = null;
        Transaction tx = null;
        GregorianCalendar gc = new GregorianCalendar();
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                boolean hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    DevicePendingDelete dpd = (DevicePendingDelete) session.get(DevicePendingDelete.class, deviceId);
                    List<DeviceOperationsMember> dom = deviceDAO.getdeviceOperationsMember(session, deviceId);
                    tx = session.beginTransaction();
                    if (dpd != null) {
                        session.delete(dpd);
                    }
                    DeviceOperationsStatus dop = (DeviceOperationsStatus) session.get(DeviceOperationsStatus.class, "020BFEBB-7C82-4724-8FB4-C5982AA10334");
                    for (DeviceOperationsMember deviceOperationsMember : dom) {
                        deviceOperationsMember.setDeviceOperationsStatus(dop);
                        deviceOperationsMember.setUpdatedDate(gc.getTime());
                        session.saveOrUpdate(deviceOperationsMember);
                    }
                    tx.commit();
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
            if (tx != null) {
                tx.rollback();
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
    }

    private TingcoDevice getDeviceTypeCommandsByDeviceType(String deviceId, String CountryID) {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                boolean hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<DeviceTypeCommands> dtcList = deviceDAO.getDeviceTypeCommandsByType(session, deviceId);
                    if (dtcList.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoDevice;
                    }
                    List<String> deviceTypeCommandID = new ArrayList<String>();
                    Set<String> deviceDataitemId = new HashSet<String>();
                    for (DeviceTypeCommands deviceTypeCommands : dtcList) {
                        deviceTypeCommandID.add(deviceTypeCommands.getDeviceTypeCommandId());
                        deviceDataitemId.add(deviceTypeCommands.getDeviceDataItemId());
                    }
                    List<DeviceTypeCommandTranslations> dtct = deviceDAO.getDeviceTypeCommandTranslations(session, deviceTypeCommandID, Integer.parseInt(CountryID));
                    List<DeviceDataitemTranslations> deviceDataitemTranslationses = containerDAO.getDeviceDataItemTranslationsByIds(session, new ArrayList<String>(deviceDataitemId), Integer.parseInt(CountryID));

                    if (dtct.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoDevice;
                    }
                    return tingcoDeviceXML.buildGetDeviceTypeCommandsByDeviceType(tingcoDevice, dtcList, dtct, deviceDataitemTranslationses);
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
    }

    private TingcoDevice getDeviceTypeCommandDetailsById(String deviceTypeCommandId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object dtcObject = deviceDAO.getDeviceTypeCommandsById(deviceTypeCommandId, session);
                    if (dtcObject == null) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                        return tingcoDevice;
                    } else {
                        DeviceTypeCommands deviceTypeCommands = (DeviceTypeCommands) dtcObject;
                        Object dtctObjetc = deviceDAO.getDeviceTypeCommandTranslationsByDeviceTypeCommandIDAndCountryId(session, deviceTypeCommandId, countryId);
                        DeviceTypeCommandTranslations deviceTypeCommandTranslationses = (DeviceTypeCommandTranslations) dtctObjetc;

                        tingcoDevice = tingcoDeviceXML.buildGetDeviceTypeCommandDetailsById(tingcoDevice, deviceTypeCommands, deviceTypeCommandTranslationses);
                    }
                } else {
                    tingcoDevice.getMsgStatus().setResponseCode(-3);
                    tingcoDevice.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoDevice;
                }
            } else {
                tingcoDevice.getMsgStatus().setResponseCode(-3);
                tingcoDevice.getMsgStatus().setResponseText("User Session is not valid");
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

    private TingcoDevice deleteDeviceTypeCommandsById(String deviceTypeCommandId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.DELETE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object dtcObject = deviceDAO.getDeviceTypeCommandsById(deviceTypeCommandId, session);
                    if (dtcObject == null) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                        return tingcoDevice;
                    } else {
                        DeviceTypeCommands deviceTypeCommands = (DeviceTypeCommands) dtcObject;
                        ContentDAO contentDAO = new ContentDAO();
                        List<DeviceTypeCommandTranslations> deviceTypeCommandTranslationses = contentDAO.getDeviceTypeCommandTranslationsByDeviceTypeCommandID(deviceTypeCommandId, session);
                        List<DeviceCommandSchedules> deviceCommandScheduleses = contentDAO.getDeviceCommandSchedulesByTypeId(session, deviceTypeCommandId);
                        if (!deviceDAO.deleteDTC(deviceTypeCommands, deviceTypeCommandTranslationses, deviceCommandScheduleses, session)) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error Occured While Delete");
                            return tingcoDevice;
                        }
                    }
                } else {
                    tingcoDevice.getMsgStatus().setResponseCode(-3);
                    tingcoDevice.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoDevice;
                }
            } else {
                tingcoDevice.getMsgStatus().setResponseCode(-3);
                tingcoDevice.getMsgStatus().setResponseText("User Session is not valid");
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

    private TingcoDevice getChargePointStatusWidgetDetails(String objectGroupId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        Session ismOperation = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ismOperation = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        Set<String> objectGroupIdset = new HashSet<String>();
                        objectGroupIdset.add(objectGroupId);
                        List<Ogmdevice> devicesList = deviceDAO.getAllOgmdeviceById(session, objectGroupIdset);
                        if (!devicesList.isEmpty()) {
                            Set<String> deviceid = new HashSet<String>();
                            for (Ogmdevice ogmdevice : devicesList) {
                                deviceid.add(ogmdevice.getId().getDeviceId());
                            }
                            List<ItemConnectionStatus> ICS = new ArrayList<ItemConnectionStatus>();
                            ICS = deviceDAO.getItemConnectionStatusByItemIds(ismOperation, deviceid);
                            if (!ICS.isEmpty()) {
                                Set<String> itemId = new HashSet<String>();
                                for (ItemConnectionStatus itemConnectionStatus : ICS) {
                                    if (itemConnectionStatus.getConnected() != 1 || (!itemConnectionStatus.getObjectStateCode().equalsIgnoreCase("OK"))) {
                                        itemId.add(itemConnectionStatus.getItemId());
                                    }
                                }
                                List<DeviceStatusDataItems> deviceStatusDataItemses = deviceDAO.getDeviceStatusDataItemsByDeviceIDs(deviceid, ismOperation);
                                for (DeviceStatusDataItems deviceStatusDataItems : deviceStatusDataItemses) {
                                    if (deviceStatusDataItems.getDeviceDataValue().equalsIgnoreCase("1")) {
                                        Object obj = deviceDAO.getConnectorsByDeviceIdAndDeviceDataItemId(deviceStatusDataItems.getDeviceId(), deviceStatusDataItems.getDeviceDataItemId(), session);
                                        if (obj != null) {
                                            itemId.add(deviceStatusDataItems.getDeviceId());
                                        }
                                    }
                                }
                                if (itemId.isEmpty()) {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                                    return tingcoDevice;
                                }
                                List<Connectors> connectorses = deviceDAO.getConnectorsLinkedToDevices(itemId, session);
                                if (connectorses.isEmpty()) {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                                    return tingcoDevice;
                                }
                                List<String> dataItem = new ArrayList<String>();
                                List<String> dataItemGroup = new ArrayList<String>();
                                for (Connectors connectors : connectorses) {
                                    dataItemGroup.add(connectors.getDeviceDataItems().getDeviceDataGroupName());
                                    dataItem.add(connectors.getDeviceDataItems().getDeviceDataFieldName());
                                }
                                List<DeviceActivationSchedules> deviceActivationScheduleses = deviceDAO.getChargePointStatusWidgetDetails(ismOperation, deviceid, dataItem, dataItemGroup);
                                if (deviceActivationScheduleses.isEmpty()) {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                                    return tingcoDevice;
                                }
                                List<se.info24.pojo.Device> device = deviceDAO.getDeviceByDeviceIdsList(session, new ArrayList(deviceid));
                                return tingcoDeviceXML.buildGetChargePointStatusWidgetDetails(tingcoDevice, device, connectorses, deviceActivationScheduleses, timeZoneGMToffset, ICS, deviceStatusDataItemses);
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                                return tingcoDevice;
                            }
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("UserTimeZone is not found");
                        return tingcoDevice;
                    }

                } else {
                    tingcoDevice.getMsgStatus().setResponseCode(-3);
                    tingcoDevice.getMsgStatus().setResponseText("User Session is not valid");
                    return tingcoDevice;
                }
            } else {
                tingcoDevice.getMsgStatus().setResponseCode(-3);
                tingcoDevice.getMsgStatus().setResponseText("User Session is not valid");
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

    private TingcoDevice getDeviceDataItemAndWidgets(String deviceId, String deviceDataItemId, String widgetId) {
        long requestedTime = System.currentTimeMillis();
        Session ismOperation = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    ismOperation = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    Object deviceStatusDataItemObject = deviceDAO.getDeviceStatusDataItemsBydeviceDataItemId(ismOperation, deviceId, deviceDataItemId);
                    Object widgets2Object = deviceDAO.getWidgets2ByWidgetId(widgetId, ismOperation);
                    if (deviceStatusDataItemObject != null && widgets2Object != null) {
                        DeviceStatusDataItems dsdi = (DeviceStatusDataItems) deviceStatusDataItemObject;
                        Widgets2 widgets2 = (Widgets2) widgets2Object;
                        tingcoDevice = tingcoDeviceXML.buildDeviceStatusDataItemsAndWidget2(tingcoDevice, dsdi, widgets2);
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No data found");
                        return tingcoDevice;
                    }
                } else {
                    tingcoDevice.getMsgStatus().setResponseCode(-3);
                    tingcoDevice.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoDevice;
                }
            } else {
                tingcoDevice.getMsgStatus().setResponseCode(-3);
                tingcoDevice.getMsgStatus().setResponseText("User Session is not valid");
                return tingcoDevice;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);

            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (ismOperation != null) {
                ismOperation.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getDeviceStateWidgetDetails(String objectGroupId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        Session ismOperation = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ismOperation = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    Set<String> objectGroupIdset = new HashSet<String>();
                    objectGroupIdset.add(objectGroupId);
                    List<Ogmdevice> devicesList = deviceDAO.getAllOgmdeviceById(session, objectGroupIdset);
                    if (!devicesList.isEmpty()) {
                        Set<String> deviceid = new HashSet<String>();
                        for (Ogmdevice ogmdevice : devicesList) {
                            deviceid.add(ogmdevice.getId().getDeviceId());
                        }

                        List<ItemConnectionStatus> ICS = new ArrayList<ItemConnectionStatus>();
                        ICS = deviceDAO.getItemConnectionStatusByItemIds(ismOperation, deviceid);
                        if (!ICS.isEmpty()) {
                            Hashtable<String, Integer> ht = new Hashtable<String, Integer>();
                            for (ItemConnectionStatus itemConnectionStatus : ICS) {
                                if (itemConnectionStatus.getObjectStateCode() != null) {
                                    if (ht.containsKey(itemConnectionStatus.getObjectStateCode())) {
                                        ht.put(itemConnectionStatus.getObjectStateCode(), ht.get(itemConnectionStatus.getObjectStateCode()) + 1);
                                    } else {
                                        ht.put(itemConnectionStatus.getObjectStateCode(), 1);
                                    }
                                }
                            }
                            if (!ht.isEmpty()) {
                                return tingcoDeviceXML.buildGetDeviceStateWidgetDetails(tingcoDevice, ht, ICS.size());
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No data Found");
                                return tingcoDevice;
                            }
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Device Status Not Found");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoDevice;
                    }
                } else {
                    tingcoDevice.getMsgStatus().setResponseCode(-3);
                    tingcoDevice.getMsgStatus().setResponseText("User Session is not valid");
                    return tingcoDevice;
                }
            } else {
                tingcoDevice.getMsgStatus().setResponseCode(-3);
                tingcoDevice.getMsgStatus().setResponseText("User Session is not valid");
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

    private TingcoDevice addDeviceLinks(String content) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        Transaction tx = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    se.info24.devicejaxbPost.TingcoDevice devicesJaxb = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    Device devicejaxb = devicesJaxb.getDevices().getDevice().get(0);
                    int count = 0;
                    for (String iTrustDeviceId : devicejaxb.getITrustDeviceID()) {
                        DeviceLinks deviceLinks = new DeviceLinks();
                        deviceLinks.setId(new DeviceLinksId(devicejaxb.getDeviceID(), iTrustDeviceId));
                        deviceLinks.setActiveFromDate(currentDateTime.getTime());
                        gc.add(Calendar.YEAR, 30);
                        deviceLinks.setActiveToDate(gc.getTime());
                        deviceLinks.setLastUpdatedByUserId(sessions2.getUserId());
                        deviceLinks.setCreatedDate(currentDateTime.getTime());
                        deviceLinks.setUpdatedDate(currentDateTime.getTime());
                        session.saveOrUpdate(deviceLinks);
                        count++;
                        if (count % 20 == 0) {
                            session.flush();
                            session.clear();
                        }
                    }
                    tx.commit();
                } else {
                    tingcoDevice.getMsgStatus().setResponseCode(-3);
                    tingcoDevice.getMsgStatus().setResponseText("User Session is not valid");
                    return tingcoDevice;
                }
            } else {
                tingcoDevice.getMsgStatus().setResponseCode(-3);
                tingcoDevice.getMsgStatus().setResponseText("User Session is not valid");
                return tingcoDevice;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
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

    private TingcoDevice deleteDeviceLinks(String deviceId, String iTrustDeviceId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        Transaction tx = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.DELETE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    Object deviceLinks = session.get(DeviceLinks.class, new DeviceLinksId(deviceId, iTrustDeviceId));
                    if (deviceLinks != null) {
                        session.delete(deviceLinks);
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
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
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

    private TingcoDevice getItemConnectionStatus(String itemId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session operationsSession = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    operationsSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    Object itemConnectionStatusObject = deviceDAO.getitemconnectionStatus(itemId, operationsSession);
                    tingcoDevice = tingcoDeviceXML.buildItemConnectionStatus(tingcoDevice, itemConnectionStatusObject);
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
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error Occured while Loading Device");
            return tingcoDevice;
        } finally {
            if (operationsSession != null) {
                operationsSession.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoDevice getTransactionReceiptDetails(String transactionCode) {
        long requestedTime = System.currentTimeMillis();
        Session ismoperationSession = null;
        Session session = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
//            if (request.getAttribute("USERSESSION") != null) {
//                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
//                TCMUtil.loger(getClass().getName(), sessions2.getUserId(), "Info");
//                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
//                if (hasPermission) {
                    ismoperationSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    session = HibernateUtil.getSessionFactory().openSession();
//                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
//
//                    if (userTimeZones2 != null) {
//                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        TransactionCodes transactionCodes = (TransactionCodes) ismoperationSession.get(TransactionCodes.class, transactionCode);
                        if (transactionCodes != null) {

                            TransactionResult tr = (TransactionResult) ismoperationSession.get(TransactionResult.class, transactionCodes.getTransactionId());
                            if (tr != null) {
                                Set<String> transactionIdSet = new HashSet<String>();
                                transactionIdSet.add(transactionCodes.getTransactionId());
                                List<TransactionProducts> tp = deviceDAO.getTransactionProductsByTransactionIds(ismoperationSession, transactionIdSet);
                                if (tp.isEmpty()) {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("No product found");
                                    return tingcoDevice;
                                }
                                Currency currency = (Currency) session.get(Currency.class, tr.getCurrencyId());
                                se.info24.pojo.Device device = (se.info24.pojo.Device) session.get(se.info24.pojo.Device.class, tr.getDeviceId());

                                int countryId = 6;

                                if (device.getAddresses() != null) {
                                    Addresses add = deviceDAO.getAddress(device.getAddresses().getAddressId(), session);
                                    countryId = add.getCountry().getCountryId();
                                }
                                Country country = (Country) session.get(Country.class, countryId);
                                GroupTranslations gt = groupDAO.getGroupTranslationsByIds(tr.getDeviceGroupId(), countryId, session);
                                tingcoDevice = tingcoDeviceXML.buildGetTransactionReceiptDetails(tingcoDevice, tp, tr, currency, gt, country, device, session);

                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No data found");
                                return tingcoDevice;
                            }
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("No data found");
                            return tingcoDevice;
                        }
//                    } else {
//                        tingcoDevice.getMsgStatus().setResponseCode(-1);
//                        tingcoDevice.getMsgStatus().setResponseText("TimeZone is not Valid");
//                        return tingcoDevice;
//                    }
//
//
//                } else {
//                    tingcoDevice.getMsgStatus().setResponseCode(-10);
//                    tingcoDevice.getMsgStatus().setResponseText("User Permission is not given");
//                    return tingcoDevice;
//                }
//            } else {
//                tingcoDevice.getMsgStatus().setResponseCode(-3);
//                tingcoDevice.getMsgStatus().setResponseText("User Session is Not Valid");
//                return tingcoDevice;
//            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (ismoperationSession != null) {
                ismoperationSession.close();
            }
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice deleteDeviceActivationSchedules(String deviceActivationId) {
        long requestedTime = System.currentTimeMillis();
        Session ismoperationSession = null;
        Session session = null;
        Transaction ismOperationstx = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.DELETE);
                if (hasPermission) {
                    ismoperationSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    session = HibernateUtil.getSessionFactory().openSession();
                    ismOperationstx = ismoperationSession.beginTransaction();
                    Object deviceActivationSchedules = deviceDAO.getDeviceActivationSchedulesById(deviceActivationId, ismoperationSession);
                    if (deviceActivationSchedules != null) {
                        ismoperationSession.delete(deviceActivationSchedules);
                        UserLog userLog = new UserLog();
                        userLog.setAction("Delete");
                        userLog.setTableName("DeviceActivationSchedules");
                        userLog.setUserId(sessions2.getUserId());
                        userLog.setRequestIp(request.getRemoteAddr());
                        userLog.setCreatedDate(currentDateTime.getTime());
                        userLog.setUpdatedDate(currentDateTime.getTime());
                        userDAO.saveUserLog(userLog, session);
                        ismOperationstx.commit();
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
            if (ismOperationstx != null) {
                ismOperationstx.rollback();
            }
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (ismoperationSession != null) {
                ismoperationSession.close();
            }
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getMediaFilesDetails(String content, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session ismoperationsession = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.MEDIA, TCMUtil.UPDATE);
                if (hasPermission) {
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    ismoperationsession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    session = HibernateUtil.getSessionFactory().openSession();
                    if (devices != null) {
                        ObjectMediaFile objectMediaFile = devices.getObjectMediaFiles().getObjectMediaFile().get(0);
                        if (objectMediaFile != null) {
                            Users2 users2 = userDAO.getUserById(sessions2.getUserId(), session);
                            //
                            List<GroupTrusts> groupTrusts = groupDAO.getGroupTrustByGroupID(users2.getGroupId(), session);
                            Set<String> groupTrustedIDSet = groupDAO.getGroupIdsList(users2.getGroupId(), groupTrusts);
                            List<List<String>> splitList = TCMUtil.splitStringList(new ArrayList(groupTrustedIDSet), 2000);
                            //
                            UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                            //
                            if (userTimeZones2 != null) {
                                String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                                List<String> mediaFileIDs = new ArrayList<String>();
                                List<String> userIDs = new ArrayList<String>();
                                List<String> groupIDs = new ArrayList<String>();
                                List<String> mediaFilesTypeIDs = new ArrayList<String>();
                                List<MediaFiles> mediaFilesesSorted = new ArrayList<MediaFiles>();
                                //
                                if (objectMediaFile.getMediaFiles().isEmpty()) {
                                    List<Object> filteredMediaFiles = new ArrayList<Object>();
                                    for (List<String> list : splitList) {
                                        //List<MediaFiles> temp = deviceDAO.getMediaFilesByGroupIDs(ismoperationsession, list);
                                        List<Object> mfList = deviceDAO.getMediaFilesByGivenGroupIDs(ismoperationsession, list);
                                        filteredMediaFiles.addAll(mfList);
                                    }
                                    for (Iterator itr = filteredMediaFiles.iterator(); itr.hasNext();) {
                                        Object[] rows = (Object[]) itr.next();
                                        for (int i = 0; i < rows.length; i++) {
                                            if (rows[i] != null) {
                                                mediaFileIDs.add((String) rows[i]);
                                            }
                                            if (rows[i + 2] != null) {
                                                mediaFilesTypeIDs.add((String) rows[i + 2]);
                                            }
                                            if (rows[i + 8] != null) {
                                                userIDs.add((String) rows[i + 8]);
                                            }
                                            if (rows[i + 9] != null) {
                                                groupIDs.add((String) rows[i + 9]);
                                            }
                                            i += 9;
                                        }
                                    }
                                    if (filteredMediaFiles.isEmpty()) {
                                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                                        tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                                        return tingcoDevice;
                                    } else {
                                        List<Object> sortedMediaFiles = deviceDAO.getMediaFilesByMediaFileIDs(ismoperationsession, mediaFileIDs);
                                        mediaFilesesSorted.addAll(fetchMediaFiles(sortedMediaFiles));
                                    }
                                } else {
                                    //SEARCH
                                    List<Object> mediaFileses = deviceDAO.filterMediaFiles(objectMediaFile.getMediaFiles().get(0), countryId, groupTrustedIDSet, users2.getGroupId(), ismoperationsession);
                                    if (mediaFileses == null || mediaFileses.isEmpty()) {
                                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                                        tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                                        return tingcoDevice;

                                    } else {
                                        for (Object mf : mediaFileses) {
                                            mediaFileIDs.add((String) mf);
                                        }
                                        List<Object> sortedMediaFiles = deviceDAO.getMediaFilesByMediaFileIDs(ismoperationsession, mediaFileIDs);
                                        mediaFilesesSorted.addAll(fetchMediaFiles(sortedMediaFiles));
                                        for (MediaFiles mf : mediaFilesesSorted) {
                                            if (mf.getLastUpdatedByUserId() != null) {
                                                userIDs.add(mf.getLastUpdatedByUserId());
                                            }
                                            if (mf.getMediaFileTypeId() != null) {
                                                mediaFilesTypeIDs.add(mf.getMediaFileTypeId());
                                            }
                                            groupIDs.add(mf.getGroupId());
                                        }
                                    }
                                }
                                List<GroupTranslations> groupTranslationses = groupDAO.getGroupTranslationsById(groupIDs, countryId, session);
                                List<Users2> users2List = new ArrayList<Users2>();
                                List<MediaFileTypes> fileTypeses = new ArrayList<MediaFileTypes>();
                                if (!userIDs.isEmpty()) {
                                    users2List.addAll(userDAO.getUser2ListByUserId(userIDs, session));
                                }
                                if (!mediaFilesTypeIDs.isEmpty()) {
                                    fileTypeses.addAll(deviceDAO.getMediaFileTypesByIds(session, mediaFilesTypeIDs));
                                }
                                tingcoDevice = tingcoDeviceXML.buildGetMediaFilesDetails(tingcoDevice, timeZoneGMToffset, mediaFilesesSorted, groupTranslationses, users2List, fileTypeses);
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("UserTimeZone not found");
                                return tingcoDevice;
                            }

                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("No ObjectMediaFile found in XML");
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
            if (ismoperationsession != null) {
                ismoperationsession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private List<MediaFiles> fetchMediaFiles(List<Object> sortedMediaFiles) {
        //mediaFileId,mediaFileName,mediaFileTypeId,mediaFileLength,mediaFileExtension,createdDate,updatedDate,mediaFileDescription,lastUpdatedByUserId,groupId
        List<MediaFiles> mediaFileses = new ArrayList<MediaFiles>();
        for (Iterator itr = sortedMediaFiles.iterator(); itr.hasNext();) {
            Object[] rows = (Object[]) itr.next();
            MediaFiles mediaFile = new MediaFiles();
            for (int i = 0; i < rows.length; i++) {
                if (rows[i] != null) {
                    mediaFile.setMediaFileId((String) rows[i]);
                }
                if (rows[i + 1] != null) {
                    mediaFile.setMediaFileName((String) rows[i + 1]);
                }
                if (rows[i + 2] != null) {
                    mediaFile.setMediaFileTypeId((String) rows[i + 2]);
                }
                if (rows[i + 3] != null) {
                    mediaFile.setMediaFileLength(Long.parseLong((String) rows[i + 3]));
                }
                if (rows[i + 4] != null) {
                    mediaFile.setMediaFileExtension((String) rows[i + 4]);
                }
                if (rows[i + 5] != null) {
                    try {
                        mediaFile.setCreatedDate(df.parse((String) rows[i + 5]));
                    } catch (ParseException ex) {
                        TCMUtil.exceptionLog(this.getClass().getName(), ex);
                    }
                }
                if (rows[i + 6] != null) {
                    try {
                        mediaFile.setUpdatedDate(df.parse((String) rows[i + 6]));
                    } catch (ParseException ex) {
                        TCMUtil.exceptionLog(this.getClass().getName(), ex);
                    }
                }
                if (rows[i + 7] != null) {
                    mediaFile.setMediaFileDescription((String) rows[i + 7]);
                }
                if (rows[i + 8] != null) {
                    mediaFile.setLastUpdatedByUserId((String) rows[i + 8]);
                }
                if (rows[i + 9] != null) {
                    mediaFile.setGroupId((String) rows[i + 9]);
                }
                i += 9;
            }
            mediaFileses.add(mediaFile);
        }
        return mediaFileses;
    }

    private TingcoDevice getTrustedDevices(String deviceId, String groupId, int countryId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<DeviceLinks> deviceLinksList = deviceDAO.getDeviceLinksByITrustDeviceId(deviceId, session);
                    if (!deviceLinksList.isEmpty()) {
                        Set<String> deviceIdsList = deviceDAO.getDeviceLinkIdsList(deviceLinksList);
//                        deviceIdsList.add(deviceId);
                        Set<String> groupIdsList = groupDAO.getGroupsAndGroupTrusts(groupId, session);
                        List<se.info24.pojo.Device> deviceList = deviceDAO.getDeviceByGroupListandDeviceIdList(session, deviceIdsList, groupIdsList);
                        if (!deviceList.isEmpty()) {
                            tingcoDevice = tingcoDeviceXML.buildDevices(tingcoDevice, deviceList, countryId);
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("No data found");
                            return tingcoDevice;
                        }
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
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoDevice;
    }

    private TingcoDevice updateServiceClientLogins(String content) {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                boolean hasPermission = getPermission(sessions2.getUserId(), TCMUtil.GROUPS, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);

                    se.info24.devicejaxbPost.ServiceClientLogins serviceClientLoginsJaxb = devices.getDevices().getDevice().get(0).getServiceClientLogins();
                    Object sclObject = session.get(ServiceClientLogins.class, serviceClientLoginsJaxb.getServiceClientLoginID());
                    if (sclObject != null) {
                        ServiceClientLogins serviceClientLogin = (ServiceClientLogins) sclObject;

                        serviceClientLogin.setServiceClientLoginName(serviceClientLoginsJaxb.getServiceClientLoginName());
                        if (serviceClientLoginsJaxb.getServiceClientLoginDescription() != null) {
                            serviceClientLogin.setServiceClientLoginDescription(serviceClientLoginsJaxb.getServiceClientLoginDescription());
                        }
                        serviceClientLogin.setGroupId(serviceClientLoginsJaxb.getGroupID());
                        if (serviceClientLoginsJaxb.getPassword() != null) {
                            serviceClientLogin.setPassword(serviceClientLoginsJaxb.getPassword());
                        } else {
                            serviceClientLogin.setPassword(null);
                        }
                        if (serviceClientLoginsJaxb.getProtocol() != null) {
                            serviceClientLogin.setProtocol(serviceClientLoginsJaxb.getProtocol());
                        } else {
                            serviceClientLogin.setProtocol(null);
                        }
                        if (serviceClientLoginsJaxb.getProtocolVersion() != null) {
                            serviceClientLogin.setProtocolVersion(serviceClientLoginsJaxb.getProtocolVersion());
                        } else {
                            serviceClientLogin.setProtocolVersion(null);
                        }
                        serviceClientLogin.setIsEnabled(serviceClientLoginsJaxb.getIsEnabled());
                        serviceClientLogin.setActiveFromDate(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, df.parse(serviceClientLoginsJaxb.getActiveFromDate())));
                        serviceClientLogin.setActiveToDate(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, df.parse(serviceClientLoginsJaxb.getActiveToDate())));

                        serviceClientLogin.setUserId(sessions2.getUserId());
                        serviceClientLogin.setUpdatedDate(currentDateTime.getTime());
                        if (!deviceDAO.saveServiceClientLogins(session, serviceClientLogin)) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error occured");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data not found");
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

    private TingcoDevice updateMediaFilesDetails(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session ismoperationsession = null;
        Transaction ismoperationtx = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.MEDIA, TCMUtil.UPDATE);
                if (hasPermission) {
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    ismoperationsession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    ismoperationtx = ismoperationsession.beginTransaction();
                    if (devices != null) {
                        ObjectMediaFile objectMediaFile = devices.getObjectMediaFiles().getObjectMediaFile().get(0);
                        if (objectMediaFile != null) {
                            se.info24.devicejaxbPost.MediaFiles mediaFileXml = objectMediaFile.getMediaFiles().get(0);
                            if (mediaFileXml == null) {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No MediaFiles found in XML");
                                return tingcoDevice;
                            } else {
                                Object object = deviceDAO.getMediaFilesByMediaFileID(ismoperationsession, mediaFileXml.getMediaFileID());
                                if (object != null) {
                                    MediaFiles modifiedMediaFiles = (MediaFiles) object;
                                    if (mediaFileXml.getMediaFileName() != null) {
                                        modifiedMediaFiles.setMediaFileName(mediaFileXml.getMediaFileName());
                                    }
                                    if (mediaFileXml.getMediaFileDescription() != null) {
                                        modifiedMediaFiles.setMediaFileDescription(mediaFileXml.getMediaFileDescription());

                                    }
                                    if ((Integer) mediaFileXml.getMediaFileLength() != null) {
                                        modifiedMediaFiles.setMediaFileLength(mediaFileXml.getMediaFileLength());
                                    }
                                    if (mediaFileXml.getMediaFileExtension() != null) {
                                        modifiedMediaFiles.setMediaFileExtension(mediaFileXml.getMediaFileExtension());
                                    }
                                    if (mediaFileXml.getMediaFileBlob() != null) {
                                        TCMUtil tcmUtil = new TCMUtil();
                                        String sha1Checksum = tcmUtil.SHA1checksumCalculation(Base64.decodeBase64(mediaFileXml.getMediaFileBlob().getBytes()));
                                        String md5Checksum = tcmUtil.MD5checksumCalculation(Base64.decodeBase64(mediaFileXml.getMediaFileBlob().getBytes()));
                                        modifiedMediaFiles.setMediaFileBlob(Base64.decodeBase64(mediaFileXml.getMediaFileBlob().getBytes()));
                                        modifiedMediaFiles.setChecksumSha1(sha1Checksum);
                                        modifiedMediaFiles.setChecksumMd5(md5Checksum);
                                    }
                                    if (mediaFileXml.getMediaFileTypeID() != null) {
                                        modifiedMediaFiles.setMediaFileTypeId(mediaFileXml.getMediaFileTypeID());
                                    }
                                    modifiedMediaFiles.setLastUpdatedByUserId(sessions2.getUserId());
                                    modifiedMediaFiles.setUpdatedDate(gc.getTime());
                                    //
                                    ismoperationsession.update(modifiedMediaFiles);
                                    ismoperationtx.commit();

                                } else {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                                    return tingcoDevice;
                                }
                            }
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("No ObjectMediaFile found in XML");
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
            if (ismoperationtx != null) {
                ismoperationtx.rollback();
            }
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
            return tingcoDevice;
        } finally {
            if (ismoperationsession != null) {
                ismoperationsession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice deleteMediaFile(String mediaFileId) {
        boolean hasPermission = false;
        Session session = null;
        Session ismOprSession = null;
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        Transaction ismTx = null;
        Transaction ismOprTx = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.MEDIA, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ismOprSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    Object object = deviceDAO.getMediaFilesByMediaFileID(ismOprSession, mediaFileId);

                    if (object == null) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                        return tingcoDevice;
                    } else {
                        MediaFiles mediaFiles = (MediaFiles) object;
                        List<ObjectMediaFiles> objectMediaFileses = deviceDAO.getObjectMediaFilesByMediaFileID(session, mediaFileId);
                        ismTx = session.beginTransaction();
                        ismOprTx = ismOprSession.beginTransaction();
                        for (ObjectMediaFiles files : objectMediaFileses) {
                            session.delete(files);
                        }
                        ismOprSession.delete(mediaFiles);
                        ismTx.commit();
                        ismOprTx.commit();
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
            if (ismOprTx != null) {
                ismOprTx.rollback();
            }
            if (ismTx != null) {
                ismTx.rollback();
            }
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
            if (ismOprSession != null) {
                ismOprSession.close();
            }
        }
        return tingcoDevice;
    }

    private TingcoDevice addMediaFile(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        Session ismoperationsession = null;
        Transaction tx = null;
        Transaction ismoperationtx = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.MEDIA, TCMUtil.ADD);
                if (hasPermission) {
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    ismoperationsession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    tx = session.beginTransaction();
                    ismoperationtx = ismoperationsession.beginTransaction();
                    if (devices != null) {
                        ObjectMediaFile objectMediaFile = devices.getObjectMediaFiles().getObjectMediaFile().get(0);
                        if (objectMediaFile != null) {
                            se.info24.devicejaxbPost.MediaFiles mediaFileXml = objectMediaFile.getMediaFiles().get(0);
                            String mediaFileId = deviceDAO.saveMediaFiles(mediaFileXml, sessions2.getUserId(), gc.getTime(), ismoperationsession);
                            if (!mediaFileId.equalsIgnoreCase("Error occured")) {
                                if (mediaFileXml.getObjectTags() != null && mediaFileXml.getObjectTags().getSearchTags() != null) {
                                    ObjectTags objectTags = new ObjectTags();
                                    objectTags.setObjectId(mediaFileId);
                                    objectTags.setSearchTags(mediaFileXml.getObjectTags().getSearchTags());
                                    objectTags.setCreatedDate(gc.getTime());
                                    objectTags.setLastUpdatedByUserId(sessions2.getUserId());
                                    objectTags.setUpdatedDate(gc.getTime());
                                    session.save(objectTags);
                                }
                                ismoperationtx.commit();
                                tx.commit();
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("Error occured");
                                return tingcoDevice;
                            }
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("No ObjectMediaFile found in XML");
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
            if (tx != null) {
                tx.rollback();
            }
            if (ismoperationtx != null) {
                ismoperationtx.rollback();
            }
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            if (ismoperationsession != null) {
                ismoperationsession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice startService(String serviceId, String deviceId, String command) {
        boolean hasPermission = false;
        Session session = null;
        Transaction tx = null;
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
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
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoDevice;
    }

    private TingcoDevice addUpdateDeviceDataItem(String content) {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        Session session = null;
        Transaction tx = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                if (getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.ADD)) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    se.info24.devicejaxbPost.TingcoDevice deviceXML = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    se.info24.devicejaxbPost.DeviceDataItems deviceDataItemsjaxb = deviceXML.getDevices().getDevice().get(0).getDeviceDataItems().get(0);
                    DeviceDataItems deviceDataItems = null;
                    Set<DeviceDataitemTranslations> dditSet = new HashSet<DeviceDataitemTranslations>();
                    if (deviceDataItemsjaxb.getDeviceDataItemID().isEmpty()) {
                        String deviceDataItemId = UUID.randomUUID().toString();
                        deviceDataItems = new DeviceDataItems();
                        deviceDataItems.setDeviceDataItemId(deviceDataItemId);
                        deviceDataItems.setDeviceDataFieldName(deviceDataItemsjaxb.getDeviceDataFieldName());
                        deviceDataItems.setDeviceDataGroupName(deviceDataItemsjaxb.getDeviceDataGroupName());
                        deviceDataItems.setUnit(deviceDataItemsjaxb.getUnit());
                        deviceDataItems.setUserId(sessions2.getUserId());
                        deviceDataItems.setCreatedDate(currentDateTime.getTime());
                        deviceDataItems.setUpdatedDate(currentDateTime.getTime());

                        for (DataItemTranslation dit : deviceDataItemsjaxb.getDataItemTranslations().getDataItemTranslation()) {
                            DeviceDataitemTranslations deviceDataItemTranslations = new DeviceDataitemTranslations();
                            deviceDataItemTranslations.setId(new DeviceDataitemTranslationsId(deviceDataItemId, dit.getCountryID()));
                            deviceDataItemTranslations.setDataItemName(dit.getDataItemName());
                            deviceDataItemTranslations.setUserId(sessions2.getUserId());
                            deviceDataItemTranslations.setCreatedDate(currentDateTime.getTime());
                            deviceDataItemTranslations.setUpdatedDate(currentDateTime.getTime());
                            dditSet.add(deviceDataItemTranslations);
                        }
                        deviceDataItems.setDeviceDataitemTranslationses(dditSet);
                        session.saveOrUpdate(deviceDataItems);
                    } else {
                        List<DeviceDataitemTranslations> deviceDataItemTransList = deviceDAO.getDeviceDataItemTranslationsByDataItemId(deviceDataItemsjaxb.getDeviceDataItemID().get(0), session);
                        if (!deviceDataItemTransList.isEmpty()) {
                            for (DeviceDataitemTranslations ddit : deviceDataItemTransList) {
                                session.delete(ddit);
                            }
                        }
                        tx.commit();
                        tx = session.beginTransaction();

                        deviceDataItems = (DeviceDataItems) deviceDAO.getDeviceDataItemsByItemId(deviceDataItemsjaxb.getDeviceDataItemID().get(0), session);
                        deviceDataItems.setDeviceDataFieldName(deviceDataItemsjaxb.getDeviceDataFieldName());
                        deviceDataItems.setDeviceDataGroupName(deviceDataItemsjaxb.getDeviceDataGroupName());
                        deviceDataItems.setUnit(deviceDataItemsjaxb.getUnit());
                        deviceDataItems.setUserId(sessions2.getUserId());
                        deviceDataItems.setUpdatedDate(currentDateTime.getTime());

                        for (DataItemTranslation dit : deviceDataItemsjaxb.getDataItemTranslations().getDataItemTranslation()) {
                            DeviceDataitemTranslations deviceDataItemTranslations = new DeviceDataitemTranslations();
                            deviceDataItemTranslations.setId(new DeviceDataitemTranslationsId(deviceDataItemsjaxb.getDeviceDataItemID().get(0), dit.getCountryID()));
                            deviceDataItemTranslations.setDataItemName(dit.getDataItemName());
                            deviceDataItemTranslations.setUserId(sessions2.getUserId());
                            deviceDataItemTranslations.setCreatedDate(currentDateTime.getTime());
                            deviceDataItemTranslations.setUpdatedDate(currentDateTime.getTime());
                            dditSet.add(deviceDataItemTranslations);
                        }
                        deviceDataItems.setDeviceDataitemTranslationses(dditSet);
                        session.saveOrUpdate(deviceDataItems);
                    }
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

    private TingcoDevice deleteDeviceDataItemByDeviceDataItemId(String deviceDataItemId) {
        boolean hasPermission = false;
        Session session = null;
        Transaction tx = null;
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.DELETE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    Object deviceDataItemsObject = deviceDAO.getDeviceDataItemsByItemId(deviceDataItemId, session);

//                    List<DeviceTypeDataitems> deviceTypeDataitems = deviceDAO.getDeviceTypeDataItemsByDeviceTypeID(deviceDataItemId, session);

                    if (deviceDataItemsObject != null) {
//                        if(!deviceTypeDataitems.isEmpty()){
//                            for (DeviceTypeDataitems deviceTypeDataitems1 : deviceTypeDataitems) {
//                                session.delete(deviceTypeDataitems1);
//                            }
//                        }
                        session.delete(deviceDataItemsObject);
                        tx.commit();
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
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoDevice;
    }

    private TingcoDevice getDevcieDataItembyDeviceDataItemID(String deviceDataItemID) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object deviceDataItemsObject = deviceDAO.getDeviceDataItemsByItemId(deviceDataItemID, session);
                    if (deviceDataItemsObject == null) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                        return tingcoDevice;
                    } else {
                        DeviceDataItems dataItems = (DeviceDataItems) deviceDataItemsObject;
                        List<DeviceDataitemTranslations> dataitemTranslationses = deviceDAO.getDeviceDataItemTranslationsByDataItemId(deviceDataItemID, session);
                        CountryDAO countryDAO = new CountryDAO();
                        List<Country> countries = countryDAO.getAllCountries(session);
                        tingcoDevice = tingcoDeviceXML.buildGetDevcieDataItembyDeviceDataItemID(tingcoDevice, dataItems, countries, dataitemTranslationses);
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
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoDevice;
    }

    private TingcoDevice getAllDeviceDataItem() {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tingcoDevice = tingcoDeviceXML.buildGetAllDeviceDataItem(tingcoDevice, deviceDAO.getAllDeviceDataItems(session));
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
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoDevice;
    }

    private TingcoDevice insertUpdateDeviceTypeChannel(String flag, String deviceTypeId, String channelid, int oldChannelDirection, String newChannelDirection, String channelTag) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        Transaction tx = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (!newChannelDirection.equals("")) {
                    newChannelDirection = newChannelDirection.split("/")[2];
                } else {
                    newChannelDirection = null;
                }
                if (!channelTag.equals("")) {
                    channelTag = channelTag.split("/")[2];
                } else {
                    channelTag = null;
                }
                GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    Object object = deviceDAO.getDeviceTypeChannelsByID(session, deviceTypeId, channelid, oldChannelDirection);

                    DeviceTypeChannels deviceTypeChannels = null;
                    if (flag.equalsIgnoreCase("UPDATE")) {
                        if (object != null) {
                            Object newObject = deviceDAO.getDeviceTypeChannelsByID(session, deviceTypeId, channelid, Integer.valueOf(newChannelDirection));
                            deviceTypeChannels = (DeviceTypeChannels) object;

                            if (Integer.valueOf(newChannelDirection) == oldChannelDirection || newObject == null) {
                                StringBuffer queryString = new StringBuffer("update DeviceTypeChannels set updatedDate = getutcdate() ");
                                if (newChannelDirection != null) {
                                    queryString.append(", channelDirection = " + Integer.valueOf(newChannelDirection) + " ");
                                }
                                if (channelTag != null) {
                                    queryString.append(", channelTag = '" + channelTag + "' ");
                                }
                                queryString.append(" where deviceTypeId = '" + deviceTypeId + "' and channelId = '" + channelid + "' and channelDirection = " + oldChannelDirection + " ");
                                SQLQuery query = session.createSQLQuery(queryString.toString());
                                query.executeUpdate();
                                tx.commit();
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("Record already exists with new channel direction");
                                return tingcoDevice;
                            }
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                            return tingcoDevice;
                        }
                    } else if (flag.equalsIgnoreCase("INSERT")) {
                        if (object == null) {
                            deviceTypeChannels = new DeviceTypeChannels();
                            deviceTypeChannels.setId(new DeviceTypeChannelsId(deviceTypeId, channelid, oldChannelDirection));
                            if (channelTag != null) {
                                deviceTypeChannels.setChannelTag(channelTag);
                            }
                            deviceTypeChannels.setCreatedDate(gc.getTime());
                            deviceTypeChannels.setUpdatedDate(gc.getTime());
                            deviceTypeChannels.setLastUpdatedByUser(sessions2.getUserId());
                            session.saveOrUpdate(deviceTypeChannels);
                            tx.commit();
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Data Already Exists");
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
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoDevice;
    }

    private TingcoDevice deleteDeviceTypeChannel(String deviceTypeId, String channelid, int channelDirection) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.DELETE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object object = deviceDAO.getDeviceTypeChannelsByID(session, deviceTypeId, channelid, channelDirection);
                    if (object != null) {
                        DeviceTypeChannels deviceTypeChannels = (DeviceTypeChannels) object;
                        deviceDAO.deleteDeviceTypeChannels(session, deviceTypeChannels);
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
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoDevice;
    }

    private TingcoDevice getDeviceTypeChannels(String deviceTypeId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<DeviceTypeChannels> deviceTypeChannelsList = deviceDAO.getDeviceTypeChannelsBydeviceTypeId(deviceTypeId, session);
                    List<Channels> channelsList = new ArrayList<Channels>();
                    if (deviceTypeChannelsList.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                        return tingcoDevice;
                    } else {
                        List<String> channelIDs = new ArrayList<String>();
                        for (DeviceTypeChannels dtc : deviceTypeChannelsList) {
                            channelIDs.add(dtc.getId().getChannelId());
                        }
                        ServiceDAO serviceDAO = new ServiceDAO();
                        channelsList.addAll(serviceDAO.getChannelByIds(session, channelIDs));
                        tingcoDevice = tingcoDeviceXML.buildGetDeviceTypeChannels(tingcoDevice, deviceTypeChannelsList, channelsList);
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
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoDevice;
    }

    private TingcoDevice getDeviceSalesDetailsByUserAlias(String content) {//At .Net team request Refer E-mail of 26/04/2014 12:30

        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        Session ismOperationsSession = null;
        TingcoDevice tingcoDevice = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.REPORTS, TCMUtil.READ);
                if (hasPermission) {
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    ismOperationsSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        List<se.info24.devicejaxbPost.Device> deviceList = devices.getDevices().getDevice();
                        if (!deviceList.isEmpty()) {
                            se.info24.devicejaxbPost.Device device = deviceList.get(0);
                            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                            SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                            gc.setTime(lFormat.parse(device.getEventDatas().getFromDate()));
                            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                            String gc_start_time = lFormat.format(gc.getTime());

                            gc.setTime(lFormat.parse(device.getEventDatas().getToDate()));
                            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                            String gc_stop_time = lFormat.format(gc.getTime());

                            String groupname = null;
                            Users2 users = (Users2) session.getNamedQuery("getUsers2ById").setString("userID", sessions2.getUserId()).list().get(0);
                            List<String> groupidset = deviceDAO.getTrustedGroup(session, users.getGroupId());

                            List<GroupTranslations> gtList = new ArrayList<GroupTranslations>();
                            if (device.getGroupID() != null) {
                                groupname = device.getGroupID().getGroupName();
                                List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                for (List<String> list : splitList) {
                                    List<GroupTranslations> gtListTemp = deviceDAO.getGroupTransSearchByGroupName(session, list, groupname, device.getCountryID().getValue());
                                    gtList.addAll(gtListTemp);
                                }

                                groupidset.clear();
                                for (GroupTranslations groupTranslations : gtList) {
                                    groupidset.add(groupTranslations.getId().getGroupId());
                                }
                            }
                            List<se.info24.ismOperationsPojo.TransactionResult> transactionResultList = new ArrayList<se.info24.ismOperationsPojo.TransactionResult>();
                            if (device.getUserName() != null && groupname == null && device.getDeviceName() == null) {
                                List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                for (List<String> list : splitList) {
                                    if (TCMUtil.isValidUUID(device.getUserName())) {
                                        List<se.info24.ismOperationsPojo.TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringUserGroupNameUserDetailID(ismOperationsSession, device.getUserName(), list, gc_start_time, gc_stop_time);
                                        transactionResultList.addAll(transactionResultListTemp);
                                    } else {
                                        List<se.info24.ismOperationsPojo.TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringUserGroupNameUserDetail(ismOperationsSession, device.getUserName(), list, gc_start_time, gc_stop_time);
                                        transactionResultList.addAll(transactionResultListTemp);
                                    }

                                }
                            } else if (device.getUserName() == null && groupname != null && device.getDeviceName() == null) {
                                if (!groupidset.isEmpty()) {
                                    List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                    for (List<String> list : splitList) {
                                        List<se.info24.ismOperationsPojo.TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringGroupName(ismOperationsSession, list, gc_start_time, gc_stop_time);
                                        transactionResultList.addAll(transactionResultListTemp);
                                    }

                                } else {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("GroupName Not Found");
                                    return tingcoDevice;
                                }
                            } else if (device.getUserName() == null && groupname == null && device.getDeviceName() != null) {
                                if (TCMUtil.isValidUUID(device.getDeviceName())) {
                                    List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                    for (List<String> list : splitList) {
                                        List<se.info24.ismOperationsPojo.TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringDeviceID(ismOperationsSession, device.getDeviceName(), gc_start_time, gc_stop_time, list);
                                        transactionResultList.addAll(transactionResultListTemp);
                                    }
                                } else {
                                    List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                    for (List<String> list : splitList) {
                                        List<se.info24.ismOperationsPojo.TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringDeviceName(ismOperationsSession, device.getDeviceName(), gc_start_time, gc_stop_time, list);
                                        transactionResultList.addAll(transactionResultListTemp);
                                    }
                                }
                            } else if (device.getUserName() != null && groupname != null && device.getDeviceName() == null) {
                                if (!groupidset.isEmpty()) {
                                    List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                    for (List<String> list : splitList) {
                                        if (TCMUtil.isValidUUID(device.getUserName())) {
                                            List<se.info24.ismOperationsPojo.TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringUserGroupNameUserDetailID(ismOperationsSession, device.getUserName(), list, gc_start_time, gc_stop_time);
                                            transactionResultList.addAll(transactionResultListTemp);
                                        } else {
                                            List<se.info24.ismOperationsPojo.TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringUserGroupNameUserDetail(ismOperationsSession, device.getUserName(), list, gc_start_time, gc_stop_time);
                                            transactionResultList.addAll(transactionResultListTemp);
                                        }
                                    }
                                } else {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("GroupName Not Found");
                                    return tingcoDevice;
                                }
                            } else if (device.getUserName() == null && groupname != null && device.getDeviceName() != null) {
                                if (!groupidset.isEmpty()) {
                                    if (TCMUtil.isValidUUID(device.getDeviceName())) {
                                        List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                        for (List<String> list : splitList) {
                                            List<se.info24.ismOperationsPojo.TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringDeviceIDGroupName(ismOperationsSession, device.getDeviceName(), list, gc_start_time, gc_stop_time);
                                            transactionResultList.addAll(transactionResultListTemp);
                                        }
                                    } else {
                                        List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                        for (List<String> list : splitList) {
                                            List<se.info24.ismOperationsPojo.TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringDeviceGroupName(ismOperationsSession, device.getDeviceName(), list, gc_start_time, gc_stop_time);
                                            transactionResultList.addAll(transactionResultListTemp);
                                        }

                                    }
                                } else {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("GroupName Not Found");
                                    return tingcoDevice;
                                }
                            } else if (device.getUserName() != null && groupname == null && device.getDeviceName() != null) {
                                if (TCMUtil.isValidUUID(device.getDeviceName())) {
                                    List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                    for (List<String> list : splitList) {
                                        if (TCMUtil.isValidUUID(device.getUserName())) {
                                            List<se.info24.ismOperationsPojo.TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringUserDeviceIDNameUserDetailID(ismOperationsSession, device.getDeviceName(), device.getUserName(), gc_start_time, gc_stop_time, list);
                                            transactionResultList.addAll(transactionResultListTemp);
                                        } else {
                                            List<se.info24.ismOperationsPojo.TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringUserDeviceIDNameUserDetail(ismOperationsSession, device.getDeviceName(), device.getUserName(), gc_start_time, gc_stop_time, list);
                                            transactionResultList.addAll(transactionResultListTemp);
                                        }

                                    }
                                } else {
                                    List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                    for (List<String> list : splitList) {
                                        if (TCMUtil.isValidUUID(device.getUserName())) {
                                            List<se.info24.ismOperationsPojo.TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringUserDeviceNameUserDetailID(ismOperationsSession, device.getDeviceName(), device.getUserName(), gc_start_time, gc_stop_time, list);
                                            transactionResultList.addAll(transactionResultListTemp);
                                        } else {
                                            List<se.info24.ismOperationsPojo.TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringUserDeviceNameUserDetail(ismOperationsSession, device.getDeviceName(), device.getUserName(), gc_start_time, gc_stop_time, list);
                                            transactionResultList.addAll(transactionResultListTemp);
                                        }

                                    }
                                }

                            } else if (device.getUserName() != null && groupname != null && device.getDeviceName() != null) {
                                if (!groupidset.isEmpty()) {
                                    if (TCMUtil.isValidUUID(device.getDeviceName())) {
                                        List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                        for (List<String> list : splitList) {
                                            if (TCMUtil.isValidUUID(device.getUserName())) {
                                                List<se.info24.ismOperationsPojo.TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringUserDeviceIDGroupNameUserDetailID(ismOperationsSession, device.getDeviceName(), device.getUserName(), list, gc_start_time, gc_stop_time);
                                                transactionResultList.addAll(transactionResultListTemp);
                                            } else {
                                                List<se.info24.ismOperationsPojo.TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringUserDeviceIDGroupNameUserDetail(ismOperationsSession, device.getDeviceName(), device.getUserName(), list, gc_start_time, gc_stop_time);
                                                transactionResultList.addAll(transactionResultListTemp);
                                            }

                                        }
                                    } else {
                                        List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                        for (List<String> list : splitList) {
                                            if (TCMUtil.isValidUUID(device.getDeviceName())) {
                                                List<se.info24.ismOperationsPojo.TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringUserDeviceGroupNameUserDetailID(ismOperationsSession, device.getDeviceName(), device.getUserName(), list, gc_start_time, gc_stop_time);
                                                transactionResultList.addAll(transactionResultListTemp);
                                            } else {
                                                List<se.info24.ismOperationsPojo.TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringUserDeviceGroupNameUserDetail(ismOperationsSession, device.getDeviceName(), device.getUserName(), list, gc_start_time, gc_stop_time);
                                                transactionResultList.addAll(transactionResultListTemp);
                                            }
                                        }
                                    }
                                } else {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("GroupName Not Found");
                                    return tingcoDevice;
                                }
                            }
                            if (!transactionResultList.isEmpty()) {
                                Set<Integer> curencyId = new HashSet();
                                Set<String> usergroupID = new HashSet<String>();
                                Hashtable<String, Float> userAmount = new Hashtable<String, Float>();
                                Hashtable<String, Integer> userNumber = new Hashtable<String, Integer>();
                                Hashtable<String, List<se.info24.ismOperationsPojo.TransactionResult>> TR = new Hashtable<String, List<se.info24.ismOperationsPojo.TransactionResult>>();
                                for (se.info24.ismOperationsPojo.TransactionResult transactionResult : transactionResultList) {
                                    curencyId.add(transactionResult.getCurrencyId());
                                    if (transactionResult.getUserAliasId() != null) {
                                        if (!TR.containsKey(transactionResult.getUserAliasId())) {
                                            List<se.info24.ismOperationsPojo.TransactionResult> TRList = new ArrayList<se.info24.ismOperationsPojo.TransactionResult>();
                                            TRList.add(transactionResult);
                                            TR.put(transactionResult.getUserAliasId(), TRList);
                                        } else {
                                            List<se.info24.ismOperationsPojo.TransactionResult> TRList = TR.get(transactionResult.getUserAliasId());
                                            boolean flag = false;
                                            for (se.info24.ismOperationsPojo.TransactionResult transactionResult1 : TRList) {
                                                if (transactionResult1.getCurrencyId().intValue() == transactionResult.getCurrencyId().intValue()) {
                                                    flag = true;
                                                }
                                            }
                                            if (!flag) {
                                                TRList.add(transactionResult);
                                                TR.put(transactionResult.getUserAliasId(), TRList);
                                            }
                                        }

                                    }
                                    if (!userAmount.containsKey(transactionResult.getUserAliasId() + "," + transactionResult.getCurrencyId())) {
                                        userAmount.put(transactionResult.getUserAliasId() + "," + transactionResult.getCurrencyId(), transactionResult.getAmount().floatValue());
                                    } else {
                                        userAmount.put(transactionResult.getUserAliasId() + "," + transactionResult.getCurrencyId(), (userAmount.get(transactionResult.getUserAliasId() + "," + transactionResult.getCurrencyId()) + transactionResult.getAmount().floatValue()));
                                    }

                                    if (!userNumber.containsKey(transactionResult.getUserAliasId() + "," + transactionResult.getCurrencyId())) {
                                        userNumber.put(transactionResult.getUserAliasId() + "," + transactionResult.getCurrencyId(), 1);
                                    } else {
                                        userNumber.put(transactionResult.getUserAliasId() + "," + transactionResult.getCurrencyId(), (userNumber.get(transactionResult.getUserAliasId() + "," + transactionResult.getCurrencyId()) + 1));
                                    }
                                    if (transactionResult.getUserGroupId() != null) {
                                        usergroupID.add(transactionResult.getUserGroupId());
                                    }

                                }
                                List<GroupTranslations> gtLists = new ArrayList<GroupTranslations>();
                                if (!usergroupID.isEmpty()) {
                                    gtLists = deviceDAO.getGroupTranslationsById(usergroupID, device.getCountryID().getValue(), session);
                                }
                                List<Currency> currency = deviceDAO.getCurrencyByCurrencyId(session, curencyId);
                                return tingcoDeviceXML.buildGetUserSalesDetails(tingcoDevice, TR, currency, gtLists, userAmount, userNumber);
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                                return tingcoDevice;
                            }
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("XML Data Not Found");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
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
//            System.gc();
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getProductVariantsForDeviceReport(String deviceId, int countryId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        session = HibernateUtil.getSessionFactory().openSession();
                        List<se.info24.pojo.RecurringPurchases> recurringPurchaseses = deviceDAO.getRecurringPurchasesByDeviceId(session, deviceId);
                        if (recurringPurchaseses.isEmpty()) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Data Not found");
                            return tingcoDevice;
                        }
                        List<String> productVariantId = new ArrayList<String>();
                        List<String> userAliasId = new ArrayList<String>();

                        for (se.info24.pojo.RecurringPurchases recurringPurchases : recurringPurchaseses) {
                            productVariantId.add(recurringPurchases.getProductVariants().getProductVariantId());
                            userAliasId.add(recurringPurchases.getUserAliasId());
                        }
                        List<UserAlias> userAliases = deviceDAO.getUserAliasDetailsByUserAliasIDs(session, userAliasId);
                        List<ProductVariantTranslations> productVariantTranslationses = deviceDAO.getProductVariantTranslations(session, productVariantId, countryId, 200);
                        return tingcoDeviceXML.buildGetProductVariantsForDeviceReport(tingcoDevice, recurringPurchaseses, userAliases, productVariantTranslationses, timeZoneGMToffset);
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
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
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoDevice;
    }

    private TingcoDevice deleteRecurringPurchaseByID(String recurringPurchaseID) {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                boolean hasPermission = getPermission(sessions2.getUserId(), TCMUtil.ORDERS, TCMUtil.DELETE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<se.info24.pojo.RecurringPurchases> recurringPurchaseses = deviceDAO.getRecurringPurchasesByID(session, recurringPurchaseID);
                    if (recurringPurchaseses.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Not found");
                        return tingcoDevice;
                    }
                    if (!deviceDAO.deleteRecurringPurchaseByID(session, recurringPurchaseses.get(0))) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Error occured");
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

    private TingcoDevice addDeliveryAccount(String content) {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                boolean hasPermission = getPermission(sessions2.getUserId(), TCMUtil.GROUPS, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);

                    se.info24.devicejaxbPost.ServiceClientLogins serviceClientLoginsJaxb = devices.getDevices().getDevice().get(0).getServiceClientLogins();
                    se.info24.pojo.ServiceClientLogins serviceClientLogin = new ServiceClientLogins();
                    serviceClientLogin.setServiceClientLoginId(UUID.randomUUID().toString());
                    /**
                     * @Sumit 21 oct 2014
                     */
                    serviceClientLogin.setClientLogin(UUID.randomUUID().toString().toUpperCase());

                    serviceClientLogin.setServiceClientLoginName(serviceClientLoginsJaxb.getServiceClientLoginName());
                    if (serviceClientLoginsJaxb.getServiceClientLoginDescription() != null) {
                        serviceClientLogin.setServiceClientLoginDescription(serviceClientLoginsJaxb.getServiceClientLoginDescription());
                    }
                    serviceClientLogin.setGroupId(serviceClientLoginsJaxb.getGroupID());
                    if (serviceClientLoginsJaxb.getPassword() != null) {
                        serviceClientLogin.setPassword(serviceClientLoginsJaxb.getPassword());
                    } else {
                        serviceClientLogin.setPassword("password");
                    }
                    if (serviceClientLoginsJaxb.getProtocol() != null) {
                        serviceClientLogin.setProtocol(serviceClientLoginsJaxb.getProtocol());
                    } else {
                        serviceClientLogin.setProtocol("none");
                    }
                    serviceClientLogin.setProtocolVersion(serviceClientLoginsJaxb.getProtocolVersion());
                    serviceClientLogin.setIsEnabled(serviceClientLoginsJaxb.getIsEnabled());
                    serviceClientLogin.setActiveFromDate(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, df.parse(serviceClientLoginsJaxb.getActiveFromDate())));
                    serviceClientLogin.setActiveToDate(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, df.parse(serviceClientLoginsJaxb.getActiveToDate())));

                    serviceClientLogin.setUserId(sessions2.getUserId());
                    GregorianCalendar gc = new GregorianCalendar();
                    serviceClientLogin.setUpdatedDate(gc.getTime());
                    serviceClientLogin.setCreatedDate(gc.getTime());
                    if (!deviceDAO.saveServiceClientLogins(session, serviceClientLogin)) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Error occured");
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

    private TingcoDevice addRecurringPurchases(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.ORDERS, TCMUtil.ADD);
                if (hasPermission) {
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                    RecurringPurchases rps = devices.getDevices().getDevice().get(0).getRecurrenceTypes().get(0).getRecurringPurchases().get(0);
                    se.info24.pojo.RecurringPurchases recurringPurchases = new se.info24.pojo.RecurringPurchases();
                    Date activeFromDate = RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, df.parse(rps.getActiveFromDate()));
                    Date activeToDate = RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, df.parse(rps.getActiveToDate()));

                    recurringPurchases.setRecurringPurchaseId(UUID.randomUUID().toString());
                    recurringPurchases.setDeviceId(rps.getDeviceID());
                    recurringPurchases.setUserAliasId(rps.getUserAliasID());

                    Object pro = session.get(Providers.class, rps.getProviderID());
                    if (pro == null) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("ProviderId is not Valid");
                        return tingcoDevice;
                    }
                    recurringPurchases.setProviders((Providers) pro);
                    pro = session.get(ProductVariants.class, rps.getProductVariantID());
                    if (pro == null) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("ProductVariantID is not Valid");
                        return tingcoDevice;
                    }
                    recurringPurchases.setProductVariants((ProductVariants) pro);
                    recurringPurchases.setQuantity(rps.getQuantity());
                    recurringPurchases.setIsEnabled(1);
                    recurringPurchases.setActiveFromDate(activeFromDate);
                    recurringPurchases.setActiveToDate(activeToDate);
                    if (rps.getNumberOfOccurances() != 0) {
                        recurringPurchases.setNumberOfOccurances(String.valueOf(rps.getNumberOfOccurances()));
                    }
                    recurringPurchases.setLastRunDate(currentDateTime.getTime());
                    recurringPurchases.setNextRunDate(currentDateTime.getTime());
                    recurringPurchases.setRecurrenceTypes(new RecurrenceTypes(rps.getRecurrenceTypeID()));
                    recurringPurchases.setRecurrenceMinute(rps.getRecurrenceMinute());
                    recurringPurchases.setRecurrenceHour(rps.getRecurrenceHour());
                    if (rps.getRecurrenceDayOfMonth() != null) {
                        recurringPurchases.setRecurrenceDayOfMonth(Integer.valueOf(rps.getRecurrenceDayOfMonth()));
                    }
                    if (rps.getRecurrenceMonday() != null) {
                        recurringPurchases.setRecurrenceMonday(Integer.valueOf(rps.getRecurrenceMonday()));
                    }
                    if (rps.getRecurrenceTuesday() != null) {
                        recurringPurchases.setRecurrenceTuesday(Integer.valueOf(rps.getRecurrenceTuesday()));
                    }
                    if (rps.getRecurrenceWednesday() != null) {
                        recurringPurchases.setRecurrenceWednesday(Integer.valueOf(rps.getRecurrenceWednesday()));
                    }
                    if (rps.getRecurrenceThursday() != null) {
                        recurringPurchases.setRecurrenceThursday(Integer.valueOf(rps.getRecurrenceThursday()));
                    }
                    if (rps.getRecurrenceFriday() != null) {
                        recurringPurchases.setRecurrenceFriday(Integer.valueOf(rps.getRecurrenceFriday()));
                    }
                    if (rps.getRecurrenceSaturday() != null) {
                        recurringPurchases.setRecurrenceSaturday(Integer.valueOf(rps.getRecurrenceSaturday()));
                    }
                    if (rps.getRecurrenceSunday() != null) {
                        recurringPurchases.setRecurrenceSunday(Integer.valueOf(rps.getRecurrenceSunday()));
                    }
                    if (rps.getRecurrenceMonth() != null) {
                        recurringPurchases.setRecurrenceMonth(Integer.valueOf(rps.getRecurrenceMonth()));
                    }
                    recurringPurchases.setLastUpdatedByUserId(sessions2.getUserId());
                    recurringPurchases.setCreatedDate(currentDateTime.getTime());
                    recurringPurchases.setUpdatedDate(currentDateTime.getTime());

                    if (!deviceDAO.saveRecurringPurchases(recurringPurchases, session)) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Error occured");
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

    private TingcoCommands addSMSCommands(String commandId, String deviceId, String targetString, String deviceTypeCommandId) {
        long requestedTime = System.currentTimeMillis();
        TingcoCommands tingcoCommands = null;
        Session session = null;
        try {
            tingcoCommands = tingcoDeviceXML.buildTingcoCommandsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                boolean hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    CommandParseTargets commandParseTargets = new CommandParseTargets();
                    commandParseTargets.setId(new CommandParseTargetsId(commandId, targetString));
                    commandParseTargets.setDeviceTypeCommands(new DeviceTypeCommands(deviceTypeCommandId));
                    commandParseTargets.setDevice(new se.info24.pojo.Device(deviceId));
                    commandParseTargets.setLastUpdatedByUserId(sessions2.getUserId());
                    commandParseTargets.setCreatedDate(gc.getTime());
                    commandParseTargets.setUpdatedDate(gc.getTime());
                    session.saveOrUpdate(commandParseTargets);
                    tx.commit();
                } else {
                    tingcoCommands.getMsgStatus().setResponseCode(-10);
                    tingcoCommands.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoCommands;
                }
            } else {
                tingcoCommands.getMsgStatus().setResponseCode(-3);
                tingcoCommands.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoCommands;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoCommands.getMsgStatus().setResponseCode(-1);
            tingcoCommands.getMsgStatus().setResponseText("Error occured");
            return tingcoCommands;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoCommands;
    }

    private TingcoCommands deleteSMSCommands(String commandId, String deviceId, String targetString) {
        long requestedTime = System.currentTimeMillis();
        TingcoCommands tingcoCommands = null;
        Session session = null;
        try {
            tingcoCommands = tingcoDeviceXML.buildTingcoCommandsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                boolean hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.DELETE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();
                    Object commandParseTargets = deviceDAO.getCommandParseTargetsByIds(commandId, targetString, deviceId, session);
                    if (commandParseTargets == null) {
                        tingcoCommands.getMsgStatus().setResponseCode(-1);
                        tingcoCommands.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoCommands;
                    }
                    session.delete(commandParseTargets);
                    tx.commit();
                } else {
                    tingcoCommands.getMsgStatus().setResponseCode(-10);
                    tingcoCommands.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoCommands;
                }
            } else {
                tingcoCommands.getMsgStatus().setResponseCode(-3);
                tingcoCommands.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoCommands;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoCommands.getMsgStatus().setResponseCode(-1);
            tingcoCommands.getMsgStatus().setResponseText("Error occured");
            return tingcoCommands;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoCommands;
    }

    private TingcoDevice getCommandDetailsByDeviceId(String deviceId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                boolean hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<Object> CommandDetails = deviceDAO.getCommanddetailsByDeviceId(deviceId, countryId, session);
                    if (CommandDetails.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoDevice;
                    }
                    tingcoDevice = tingcoDeviceXML.buildGetCommandDetailsByDeviceId(tingcoDevice, CommandDetails);
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

    private TingcoDevice getDeviceCommandScheduleDetailsByDeviceCommandScheduleID(String deviceCommandScheduleID, String countryID) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        List<DeviceCommandSchedules> dcsList = deviceDAO.getDeviceCommandSchedulesById(session, deviceCommandScheduleID);
                        if (dcsList.isEmpty()) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                            return tingcoDevice;
                        }
                        List<String> deviceTypeCommandId = new ArrayList<String>();
                        deviceTypeCommandId.add(dcsList.get(0).getDeviceTypeCommands().getDeviceTypeCommandId());
                        List<DeviceTypeCommandTranslations> dtctList = deviceDAO.getDeviceTypeCommandTranslations(session, deviceTypeCommandId, Integer.parseInt(countryID));
                        return tingcoDeviceXML.buildGetDeviceCommandScheduleDetailsByDeviceCommandScheduleID(tingcoDevice, dcsList.get(0), dtctList, timeZoneGMToffset);
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("User have no TimeZone");
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
    }

    private TingcoDevice getDeviceListByAssetId(String assetId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<se.info24.pojo.Device> deviceList = deviceDAO.getDeviceByAssetId(assetId, session);
                    if (!deviceList.isEmpty()) {
                        return tingcoDeviceXML.buildDevices(tingcoDevice, deviceList, countryId);
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Record found");
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
    }

    private TingcoCommands getSMSCommands(int countryId) {
        long requestedTime = System.currentTimeMillis();
        TingcoCommands tingcoCommands = null;
        Session session = null;
        try {
            tingcoCommands = tingcoDeviceXML.buildTingcoCommandsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                boolean hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    String commandTypeId = "3d963865-311a-418e-bf2a-b3a352058c5c"; //Hardcoded as per Hans requirement for DeviceSMSCommands page in the TCM Sprint 7(2.8.0).
                    List<CommandTranslations> commandTransList = deviceDAO.getCommandTranslations(countryId, commandTypeId, session);
                    if (!commandTransList.isEmpty()) {
                        tingcoCommands = tingcoDeviceXML.buildGetSMSCommands(tingcoCommands, commandTransList);
                    } else {
                        tingcoCommands.getMsgStatus().setResponseCode(-1);
                        tingcoCommands.getMsgStatus().setResponseText("No data found");
                        return tingcoCommands;
                    }
                } else {
                    tingcoCommands.getMsgStatus().setResponseCode(-10);
                    tingcoCommands.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoCommands;
                }
            } else {
                tingcoCommands.getMsgStatus().setResponseCode(-3);
                tingcoCommands.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoCommands;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoCommands.getMsgStatus().setResponseCode(-1);
            tingcoCommands.getMsgStatus().setResponseText("Error occured");
            return tingcoCommands;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoCommands;
    }

    private TingcoDevice getSalesByProviderDetails(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session ismOperationsSession = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.REPORTS, TCMUtil.READ);
                if (hasPermission) {
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    ismOperationsSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    session = HibernateUtil.getSessionFactory().openSession();
                    if (devices.getDevices().getDevice().size() > 0) {
                        Device deviceXML = devices.getDevices().getDevice().get(0);
                        if (deviceXML != null) {
                            Users2 users2 = userDAO.getUserById(sessions2.getUserId(), session);
                            UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                            if (userTimeZones2 != null) {
                                String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                                List<Object> transactionResultList = deviceDAO.getTransactionResultForSalesByProvider(deviceXML, tingcoDevice, users2.getGroupId(), timeZoneGMToffset, ismOperationsSession, session);
                                if (!transactionResultList.isEmpty()) {
                                    tingcoDevice = tingcoDeviceXML.buildTransactionResultForSalesByProvider(tingcoDevice, transactionResultList, deviceXML.getCountryID().getValue(), session);
                                } else {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("No Sales details found for the given input");
                                    return tingcoDevice;
                                }
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("UserTimeZone not found");
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
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private se.info24.devicejaxbPost.TingcoDevice getDeviceCountReportDetails(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        se.info24.devicejaxbPost.TingcoDevice tingcoDevice = null;
        Session session = null;
        String agreementId = null;
        String groupName = null;
        String objectGroupName = null;
        String deviceName = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplatePost();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.REPORTS, TCMUtil.READ);
                if (hasPermission) {
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    if (devices.getDevices().getDevice().size() > 0) {
                        Device deviceXML = devices.getDevices().getDevice().get(0);
                        if (deviceXML != null) {
                            DeviceCountReport deviceCountReport = deviceXML.getDeviceCountReport().get(0);
                            if (deviceCountReport != null) {
                                List<GroupTrusts> groupTrusts = groupDAO.getGroupTrustByGroupID(deviceCountReport.getLoggedInUserGroupID(), session);
                                Set<String> groupTrustedIDSet = groupDAO.getGroupIdsList(deviceCountReport.getLoggedInUserGroupID(), groupTrusts);

                                groupName = deviceCountReport.getGroupName();
                                agreementId = deviceCountReport.getAgreementID();
                                objectGroupName = deviceCountReport.getObjectGroupName();
                                deviceName = deviceCountReport.getDeviceName();

                                List<se.info24.pojo.GroupTranslations> gtList = new ArrayList<GroupTranslations>();
                                List<se.info24.pojo.Device> deviceList = new ArrayList<se.info24.pojo.Device>();

                                List<List<String>> splitList = TCMUtil.splitStringList(new ArrayList(groupTrustedIDSet), 2000);
                                for (List<String> list : splitList) {
                                    List<se.info24.pojo.GroupTranslations> gtListtemp = groupDAO.getGroupTranslationsByGroupId(new HashSet<String>(list), deviceCountReport.getCountryID(), 0, session);
                                    gtList.addAll(gtListtemp);
                                    if (groupName == null && agreementId == null && objectGroupName == null && deviceName == null && deviceCountReport.getOptionalCountryID() == null && deviceCountReport.getDeviceType() == null) {
                                        List<se.info24.pojo.Device> deviceListtemp = deviceDAO.getDeviceDetailsByGroupId(session, list);
                                        deviceList.addAll(deviceListtemp);
                                    }
                                }

                                if (groupName == null && agreementId == null && objectGroupName == null && deviceName == null && deviceCountReport.getOptionalCountryID() == null && deviceCountReport.getDeviceType() == null) {
                                    if (!deviceList.isEmpty()) {
                                        tingcoDevice = tingcoDeviceXML.buildDeviceCountReport(deviceList, gtList, tingcoDevice, deviceCountReport.getGroupBy(), session);
                                    } else {
                                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                                        tingcoDevice.getMsgStatus().setResponseText("No data found");
                                        return tingcoDevice;
                                    }
                                } else {
//                                    deviceList = deviceDAO.getDevicesCountBySearch(session, agreementId, groupName, objectGroupName, deviceName, deviceCountReport.getCountryID(), deviceCountReport.getLoggedInUserGroupID());
                                    List<Object> deviceListObject = deviceDAO.getDevicesCountBySearch(session, agreementId, groupName, objectGroupName, deviceName, deviceCountReport);
                                    if (deviceListObject.isEmpty()) {
                                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                                        tingcoDevice.getMsgStatus().setResponseText("No data found");
                                        return tingcoDevice;
                                    }

                                    List<String> deviceIdsList = deviceDAO.getdeviceIdsListObject(deviceListObject);
                                    List<List<String>> spList = TCMUtil.splitStringList(deviceIdsList, 2000);
                                    for (List<String> list : spList) {
                                        List<se.info24.pojo.Device> deviceListTemp = deviceDAO.getDeviceByDeviceIdsList(session, list);
                                        if (deviceListTemp != null) {
                                            deviceList.addAll(deviceListTemp);
                                        }
                                    }
//                                    deviceList = deviceDAO.getDeviceByDeviceIdsList(session, deviceIdsList);
                                    if (!deviceList.isEmpty()) {
                                        tingcoDevice = tingcoDeviceXML.buildDeviceCountReport(deviceList, gtList, tingcoDevice, deviceCountReport.getGroupBy(), session);
                                    } else {
                                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                                        tingcoDevice.getMsgStatus().setResponseText("No data found");
                                        return tingcoDevice;
                                    }
                                }
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No DeviceCountReport found in XML");
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
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice DeleteDeviceCommandSchedule(String deviceCommandScheduleID) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.UPDATE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<DeviceCommandSchedules> dcsList = deviceDAO.getDeviceCommandSchedulesById(session, deviceCommandScheduleID);
                    if (dcsList.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoDevice;
                    }
                    if (!deviceDAO.deleteDeviceCommandSchedule(dcsList.get(0), session)) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Error occured");
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
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoDevice UpdateDeviceCommandSchedule(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        GregorianCalendar gc = new GregorianCalendar();
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.UPDATE);
                if (hasPermission) {
                    se.info24.devicejaxb.TingcoDevice devices = (se.info24.devicejaxb.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxb.TingcoDevice.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        if (devices.getDevices().getDevice().isEmpty()) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("XML is Wrong");
                            return tingcoDevice;
                        }
                        se.info24.devicejaxb.DeviceCommandSchedules dcsJaxb = (se.info24.devicejaxb.DeviceCommandSchedules) devices.getDevices().getDevice().get(0).getContent().get(0);
                        List<DeviceCommandSchedules> DCSList = deviceDAO.getDeviceCommandSchedulesById(session, dcsJaxb.getDeviceCommandScheduleID());
                        if (DCSList.isEmpty()) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                            return tingcoDevice;
                        }
                        DeviceCommandSchedules dcs = DCSList.get(0);
//                        dcs.setDeviceCommandScheduleId(UUID.randomUUID().toString());
                        if (dcsJaxb.getDeviceID() != null) {
                            se.info24.pojo.Device device = deviceDAO.getDeviceById(dcsJaxb.getDeviceID(), session);
                            if (device == null) {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                                return tingcoDevice;
                            }
                            dcs.setDevice(device);
                        }

                        if (dcsJaxb.getDeviceTypeCommandID() != null) {
                            Object dtcObject = deviceDAO.getDeviceTypeCommandsById(dcsJaxb.getDeviceTypeCommandID(), session);
                            if (dtcObject == null) {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                                return tingcoDevice;
                            }
                            DeviceTypeCommands dtc = (DeviceTypeCommands) dtcObject;
                            dcs.setDeviceTypeCommands(dtc);
                        }

                        dcs.setSendMonday(dcsJaxb.getSendMonday());
                        dcs.setSendTuesday(dcsJaxb.getSendTuesday());
                        dcs.setSendWednesday(dcsJaxb.getSendWednesday());
                        dcs.setSendThursday(dcsJaxb.getSendThursday());
                        dcs.setSendFriday(dcsJaxb.getSendFriday());
                        dcs.setSendSaturday(dcsJaxb.getSendSaturday());
                        dcs.setSendSunday(dcsJaxb.getSendSunday());
                        dcs.setIsEnabled(dcsJaxb.getIsEnabled());
                        if (dcsJaxb.getActiveFromDate() != null) {
                            dcs.setActiveFromDate(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, df.parse(dcsJaxb.getActiveFromDate())));
                        }
                        if (dcsJaxb.getActiveToDate() != null) {
                            dcs.setActiveToDate(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, df.parse(dcsJaxb.getActiveToDate())));
                        }
//                        dcs.setLastSent(gc.getTime());
                        dcs.setUpdatedDate(gc.getTime());

                        if (dcsJaxb.getSendTime() != null) {
                            DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
                            String xx = df1.format(gc.getTime());
                            gc.setTime(df.parse(xx + " " + dcsJaxb.getSendTime()));
                            dcs.setSendTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                        }
                        if (!deviceDAO.AddDeviceCommandSchedule(dcs, session)) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error occured");
                            return tingcoDevice;
                        }
                        return tingcoDevice;
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("TimeZone is not Found");
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
    }

    private TingcoDevice AddDeviceCommandSchedule(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        GregorianCalendar gc = new GregorianCalendar();
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.ADD);
                if (hasPermission) {
                    se.info24.devicejaxb.TingcoDevice devices = (se.info24.devicejaxb.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxb.TingcoDevice.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        if (devices.getDevices().getDevice().isEmpty()) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("XML is Wrong");
                            return tingcoDevice;
                        }
                        se.info24.devicejaxb.DeviceCommandSchedules dcsJaxb = (se.info24.devicejaxb.DeviceCommandSchedules) devices.getDevices().getDevice().get(0).getContent().get(0);
                        DeviceCommandSchedules dcs = new DeviceCommandSchedules();
                        dcs.setDeviceCommandScheduleId(UUID.randomUUID().toString());
                        if (dcsJaxb.getDeviceID() != null) {
                            se.info24.pojo.Device device = deviceDAO.getDeviceById(dcsJaxb.getDeviceID(), session);
                            if (device == null) {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                                return tingcoDevice;
                            }
                            dcs.setDevice(device);
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Devie should");
                            return tingcoDevice;
                        }

                        if (dcsJaxb.getDeviceTypeCommandID() != null) {
                            Object dtcObject = deviceDAO.getDeviceTypeCommandsById(dcsJaxb.getDeviceTypeCommandID(), session);
                            if (dtcObject == null) {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                                return tingcoDevice;
                            }
                            DeviceTypeCommands dtc = (DeviceTypeCommands) dtcObject;
                            dcs.setDeviceTypeCommands(dtc);
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("DeviceTypeCommandID not found");
                            return tingcoDevice;
                        }
                        dcs.setSendMonday(dcsJaxb.getSendMonday());
                        dcs.setSendTuesday(dcsJaxb.getSendTuesday());
                        dcs.setSendWednesday(dcsJaxb.getSendWednesday());
                        dcs.setSendThursday(dcsJaxb.getSendThursday());
                        dcs.setSendFriday(dcsJaxb.getSendFriday());
                        dcs.setSendSaturday(dcsJaxb.getSendSaturday());
                        dcs.setSendSunday(dcsJaxb.getSendSunday());
                        dcs.setIsEnabled(dcsJaxb.getIsEnabled());
                        if (dcsJaxb.getActiveFromDate() != null) {
                            dcs.setActiveFromDate(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, df.parse(dcsJaxb.getActiveFromDate())));

                        } else {
                            dcs.setActiveFromDate(gc.getTime());
                        }

                        if (dcsJaxb.getActiveToDate() != null) {
                            dcs.setActiveToDate(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, df.parse(dcsJaxb.getActiveToDate())));

                        } else {
                            dcs.setActiveToDate(gc.getTime());
                        }
                        dcs.setLastUpdatedByUserId(sessions2.getUserId());
//                        dcs.setLastSent(gc.getTime());
                        dcs.setUpdatedDate(gc.getTime());
                        dcs.setCreatedDate(gc.getTime());
                        if (dcsJaxb.getSendTime() != null) {
                            DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
                            String xx = df1.format(gc.getTime());
                            gc.setTime(df.parse(xx + " " + dcsJaxb.getSendTime()));
                            dcs.setSendTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("SendTime Should not be empty");
                            return tingcoDevice;
                        }



                        if (!deviceDAO.AddDeviceCommandSchedule(dcs, session)) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error occured");
                            return tingcoDevice;
                        }
                        return tingcoDevice;
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("TimeZone is not Found");
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
    }

    private TingcoDevice getDeviceTypeCommands(String deviceId, String CountryID) {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                boolean hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    se.info24.pojo.Device device = deviceDAO.getDeviceById(deviceId, session);
                    if (device == null) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoDevice;
                    }
                    List<DeviceTypeCommands> dtcList = deviceDAO.getDeviceTypeCommandsByType(session, device.getDeviceTypes().getDeviceTypeId());
                    if (dtcList.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoDevice;
                    }
                    List<String> deviceTypeCommandID = new ArrayList<String>();
                    for (DeviceTypeCommands deviceTypeCommands : dtcList) {
                        deviceTypeCommandID.add(deviceTypeCommands.getDeviceTypeCommandId());
                    }
                    List<DeviceTypeCommandTranslations> dtct = deviceDAO.getDeviceTypeCommandTranslations(session, deviceTypeCommandID, Integer.parseInt(CountryID));
                    if (dtct.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoDevice;
                    }
                    return tingcoDeviceXML.buildGetDeviceTypeCommands(tingcoDevice, dtcList, dtct);
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
    }

    private TingcoDevice getDeviceCommandScheduleDetails(String deviceId, String CountryID) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        List<DeviceCommandSchedules> dcsList = deviceDAO.getDeviceCommandSchedulesByDeviceId(session, deviceId, 200);
                        if (dcsList.isEmpty()) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                            return tingcoDevice;
                        }
                        List<String> deviceTypeCommandID = new ArrayList<String>();
                        for (DeviceCommandSchedules dcs : dcsList) {
                            deviceTypeCommandID.add(dcs.getDeviceTypeCommands().getDeviceTypeCommandId());
                        }
                        List<DeviceTypeCommandTranslations> dtct = deviceDAO.getDeviceTypeCommandTranslations(session, deviceTypeCommandID, Integer.parseInt(CountryID));
                        if (dtct.isEmpty()) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                            return tingcoDevice;
                        }
                        return tingcoDeviceXML.buildGetDeviceCommandScheduleDetails(tingcoDevice, dcsList, dtct, timeZoneGMToffset);
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("User have no TimeZone");
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

    }

    private TingcoDevice addExistingMediaFiles(String deviceId, String mediaFileId) {
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
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    ObjectMediaFiles objectMediaFiles = new ObjectMediaFiles();
                    objectMediaFiles.setId(new ObjectMediaFilesId(deviceId, mediaFileId));
                    objectMediaFiles.setLastUpdatedByUserId(sessions2.getUserId());
                    objectMediaFiles.setCreatedDate(gc.getTime());
                    objectMediaFiles.setUpdatedDate(gc.getTime());
                    session.saveOrUpdate(objectMediaFiles);
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

    private TingcoDevice addNewMediaFile(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        Session ismoperationsession = null;
        Transaction tx = null;
        Transaction ismoperationtx = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.ADD);
                if (hasPermission) {
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    ismoperationsession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    tx = session.beginTransaction();
                    ismoperationtx = ismoperationsession.beginTransaction();
                    if (devices != null) {
                        ObjectMediaFile objectMediaFile = devices.getObjectMediaFiles().getObjectMediaFile().get(0);
                        if (objectMediaFile != null) {
                            se.info24.devicejaxbPost.MediaFiles mediaFile = objectMediaFile.getMediaFiles().get(0);
                            String mediaFileId = deviceDAO.saveMediaFiles(mediaFile, sessions2.getUserId(), gc.getTime(), ismoperationsession);
                            if (!mediaFileId.equalsIgnoreCase("Error occured")) {
                                ObjectMediaFiles objectMediaFiles = new ObjectMediaFiles();
                                objectMediaFiles.setId(new ObjectMediaFilesId(objectMediaFile.getObjectID(), mediaFileId));
                                objectMediaFiles.setLastUpdatedByUserId(sessions2.getUserId());
                                objectMediaFiles.setCreatedDate(gc.getTime());
                                objectMediaFiles.setUpdatedDate(gc.getTime());
                                session.saveOrUpdate(objectMediaFiles);
                                ismoperationtx.commit();
                                tx.commit();
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("Error occured");
                                return tingcoDevice;
                            }
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("No ObjectMediaFile found in XML");
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
            if (tx != null) {
                tx.rollback();
            }
            if (ismoperationtx != null) {
                ismoperationtx.rollback();
            }
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            if (ismoperationsession != null) {
                ismoperationsession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice deleteMediaFiles(String deviceId, String mediaFileId) {
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
                    ObjectMediaFiles objectMediaFiles = deviceDAO.getObjectMediaFilesByIds(deviceId, mediaFileId, session);
                    if (objectMediaFiles != null) {
                        session.delete(objectMediaFiles);
                        tx.commit();
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No data found for the given input");
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

    private TingcoDevice getAllDeviceDataItems(String userId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<DeviceDataitemTranslations> deviceDataItemTrans = deviceDAO.getAllDeviceDataitemTranslations(countryId, session);
                    tingcoDevice = tingcoDeviceXML.buildGetAllDeviceDataItems(tingcoDevice, deviceDataItemTrans, userId);
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

    private TingcoDevice getExistingFilesForDevice(String deviceId, String groupId, String fileName, String countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        Session sessionopr = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    if (!fileName.equals("")) {
                        fileName = fileName.split("/")[2];
                    }
                    session = HibernateUtil.getSessionFactory().openSession();
                    sessionopr = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(groupId, session);
                    Set<String> groupIdsList = groupDAO.getGroupIdsList(groupId, groupTrustsList);
                    List<MediaFiles> mfList = new ArrayList<MediaFiles>();
                    List<List<String>> spList = TCMUtil.splitStringList(new ArrayList<String>(groupIdsList), 2000);
                    for (List<String> list : spList) {
                        List<MediaFiles> mfListTemp = new ArrayList<MediaFiles>();
                        if (fileName == null) {
                            mfListTemp = deviceDAO.getMediaFilesByGroupId(sessionopr, list);
                        } else {
                            if (TCMUtil.isValidUUID(fileName)) {
                                mfListTemp = deviceDAO.getMediaFilesByGroupIdandId(sessionopr, list, fileName);
                            } else {
                                mfListTemp = deviceDAO.getMediaFilesByGroupIdandFileName(sessionopr, list, fileName);
                            }
                        }
                        mfList.addAll(mfListTemp);
                    }

                    if (mfList.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoDevice;
                    }
                    List<ObjectMediaFiles> omfList = deviceDAO.getObjectMediaFilesByNotobjectId(session, deviceId);
                    groupIdsList.clear();
                    for (MediaFiles mediaFiles : mfList) {
                        groupIdsList.add(mediaFiles.getGroupId());
                    }
                    List<GroupTranslations> gtList = new ArrayList<GroupTranslations>();
                    spList = TCMUtil.splitStringList(new ArrayList<String>(groupIdsList), 2000);
                    for (List<String> list : spList) {
                        List<GroupTranslations> gtListtemp = deviceDAO.getGroupTranslationsById(new HashSet<String>(list), Integer.valueOf(countryId), session);
                        gtList.addAll(gtListtemp);
                    }

                    return tingcoDeviceXML.buildGetExistingFilesForDevice(tingcoDevice, gtList, mfList, omfList);
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
            if (sessionopr != null) {
                sessionopr.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoDevice getMediaFileTypes() {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
//       System.gc();
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<MediaFileTypes> mftList = deviceDAO.getMediaFileTypes(session);
                    if (!mftList.isEmpty()) {
                        return tingcoDeviceXML.buildGetMediaFileTypes(tingcoDevice, mftList);

                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
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
    }

    private TingcoDevice getDeviceMediaFiles(String deviceId, String countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        Session sessionopr = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    if (!countryId.equals("")) {
                        countryId = countryId.split("/")[2];
                    } else {
                        countryId = null;
                    }
                    session = HibernateUtil.getSessionFactory().openSession();
                    sessionopr = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        List<String> mediaFileId = deviceDAO.getMediaFiles(sessionopr);
                        if (mediaFileId.isEmpty()) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                            return tingcoDevice;
                        }
                        List<ObjectMediaFiles> omfList = deviceDAO.getObjectMediaFilesByobjectId(session, deviceId, mediaFileId);
                        if (omfList.isEmpty()) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                            return tingcoDevice;
                        }
                        mediaFileId.clear();
                        for (ObjectMediaFiles objectMediaFiles : omfList) {
                            mediaFileId.add(objectMediaFiles.getId().getMediaFileId());
                        }
                        List<String> mediaFileTypeId = new ArrayList<String>();
                        List<String> groupId = new ArrayList<String>();
                        List<String> userId = new ArrayList<String>();
                        List<Object> mfList = deviceDAO.getMediaFilesByIds(sessionopr, mediaFileId);
                        for (Iterator itr = mfList.iterator(); itr.hasNext();) {
                            Object[] rows = (Object[]) itr.next();
                            for (int i = 0; i < rows.length; i++) {
                                if (rows[i + 2] != null) {
                                    mediaFileTypeId.add(rows[i + 2].toString());
                                }
                                if (rows[i + 6] != null) {
                                    userId.add(rows[i + 6].toString());
                                }
                                if (rows[i + 7] != null) {
                                    groupId.add(rows[i + 7].toString());
                                }
                                i += 8;
                            }
                        }
                        List<GroupTranslations> groupTranslationses = new ArrayList<GroupTranslations>();
                        if (countryId != null) {
                            if (!groupId.isEmpty()) {
                                groupTranslationses = groupDAO.getGroupTranslationsById(groupId, Integer.parseInt(countryId), session);
                            }
                        }
                        List<Users2> users2s = new ArrayList<Users2>();
                        if (!userId.isEmpty()) {
                            users2s.addAll(userDAO.getUser2ListByUserId(userId, session));
                        }
                        List<MediaFileTypes> mftList = new ArrayList<MediaFileTypes>();
                        if (!mediaFileTypeId.isEmpty()) {
                            mftList = deviceDAO.getMediaFileTypesByIds(session, mediaFileTypeId);
                        }

                        return tingcoDeviceXML.buildGetDeviceMediaFiles(tingcoDevice, mfList, mftList, omfList, timeZoneGMToffset, groupTranslationses, users2s);
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("UserTimezone not found");
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
            Runtime.getRuntime().gc();
            if (sessionopr != null) {
                sessionopr.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoDevice addObjectGroup(String content) {
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
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    int i = 0;
                    if (devices.getDevices().getDevice().size() > 0) {
                        Device deviceXML = devices.getDevices().getDevice().get(0);
                        if (deviceXML != null) {
                            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                            String deviceId = deviceXML.getOGMDevice().get(0).getDeviceID();
                            List<String> objectGroupidList = deviceXML.getOGMDevice().get(0).getObjectGroupID();
                            String scheduleId = null;
                            if (deviceXML.getOGMDevice().get(0).getScheduleID() != null) {
                                scheduleId = deviceXML.getOGMDevice().get(0).getScheduleID();
                            }
                            Ogmdevice ogmDevice = null;
                            for (String objectGroupId : objectGroupidList) {
                                ogmDevice = new Ogmdevice();
                                ogmDevice.setId(new OgmdeviceId(deviceId, objectGroupId));
                                if (scheduleId != null) {
                                    ogmDevice.setSchedules(new Schedules(scheduleId));
                                }
                                ogmDevice.setLastUpdatedByUserId(sessions2.getUserId());
                                ogmDevice.setCreatedDate(gc.getTime());
                                ogmDevice.setUpdatedDate(gc.getTime());
                                session.saveOrUpdate(ogmDevice);
                                i++;
                                if (i % 20 == 0) {
                                    session.flush();
                                    session.clear();
                                }
                            }
                            tx.commit();
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

    private TingcoDevice deleteObjectGroup(String deviceId, String objectGroupId) {
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
                    Ogmdevice ogmDevice = deviceDAO.getOGMDeviceByDeviceIdAndObjectGroupId(deviceId, objectGroupId, session);
                    if (ogmDevice != null) {
                        session.delete(ogmDevice);
                        tx.commit();
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No data found for the given input");
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

    private TingcoDevice getContainerDeviceDetails(String containerId, Integer countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        Session ismOperationsSession = null;
        try {
            if (containerId.equals("")) {
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("ContainerId should not be empty");
                return tingcoDevice;
            }
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.CONTAINERS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ismOperationsSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    List<ContainerDevices> containerDevices = containerDAO.getContainerDevicesByContainerId(containerId, session);
                    if (!containerDevices.isEmpty()) {
                        List<String> deviceIdsList = new ArrayList<String>();
                        for (ContainerDevices cd : containerDevices) {
                            deviceIdsList.add(cd.getDevice().getDeviceId());
                        }
                        List<se.info24.pojo.Device> devicesList = deviceDAO.getDeviceByDeviceIdsList(session, deviceIdsList);
                        tingcoDevice = tingcoDeviceXML.buildContainerDevices(tingcoDevice, containerDevices, devicesList, session, ismOperationsSession, sessions2.getUserId(), countryId);
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
            if (ismOperationsSession != null) {
                ismOperationsSession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getDeviceDetailsBySearchCriteria(String content, String objectTags) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    if (devices.getDevices().getDevice().size() > 0) {
                        Device deviceXML = devices.getDevices().getDevice().get(0);
                        if (deviceXML != null) {
                            if (!objectTags.equals("")) {
                                objectTags = objectTags.split("/")[2];
                            } else {
                                objectTags = null;
                            }
                            List<Object> devicesListObject = deviceDAO.getDeviceDetailsBySearchCriteria(deviceXML, tingcoDevice, sessions2.getUserId(), session, objectTags);
                            if (!devicesListObject.isEmpty()) {
                                if (devicesListObject.size() > 200) {
                                    tingcoDevice.setExceeds200(1);
                                    devicesListObject = devicesListObject.subList(0, 200);
                                } else {
                                    tingcoDevice.setExceeds200(0);
                                }
                                List<String> deviceIdsList = deviceDAO.getdeviceIdsListObject(devicesListObject);
                                List<se.info24.pojo.Device> deviceList = deviceDAO.getDeviceByDeviceIdsList(session, deviceIdsList);
                                UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                                if (userTimeZones2 != null) {
                                    String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                                    tingcoDevice = tingcoDeviceXML.buildDeviceDetailsbySearchCriteria(tingcoDevice, deviceList, deviceXML, timeZoneGMToffset, session);
                                } else {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                                    return tingcoDevice;
                                }
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Devices found for the given input");
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
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getDeviceForDeviceLocationHistory(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    if (devices.getDevices().getDevice().size() > 0) {
                        Device deviceXML = devices.getDevices().getDevice().get(0);
                        if (deviceXML != null) {
                            List<String> deviceIdsList = deviceDAO.getDeviceForDeviceLocationHistory(deviceXML, tingcoDevice, sessions2.getUserId(), session);
                            if (!deviceIdsList.isEmpty()) {
                                List<se.info24.pojo.Device> deviceList = deviceDAO.getDeviceByDeviceIdsList(session, deviceIdsList);
                                tingcoDevice = tingcoDeviceXML.buildDevices(tingcoDevice, deviceList, deviceXML.getCountryID().getValue());
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Devices found for the given input");
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
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getDeviceLocationHistoryDetails(String deviceId, String fromDate, String toDate) {
        long requestedTime = System.currentTimeMillis();
        Session ismOperationsession = null;
        Session session = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (deviceId.equals("")) {
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("DeviceId should not empty");
                return tingcoDevice;
            }
            if (fromDate.equals("")) {
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("fromDate should not empty");
                return tingcoDevice;
            }
            if (toDate.equals("")) {
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("toDate should not empty");
                return tingcoDevice;
            }
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    ismOperationsession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        List<DeviceHistory> deviceHistory = deviceDAO.getDeviceHistory(deviceId, fromDate, toDate, ismOperationsession, timeZoneGMToffset);
                        if (!deviceHistory.isEmpty()) {
                            tingcoDevice = tingcoDeviceXML.buildDeviceHistory(tingcoDevice, deviceHistory, timeZoneGMToffset);
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("No Data found");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("TimeZone Not found");
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
            if (ismOperationsession != null) {
                ismOperationsession.close();
            }
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getMediaFiles(String mediaFileId) {
        long requestedTime = System.currentTimeMillis();
        Session ismOperationsession = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = true;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (mediaFileId.equals("")) {
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("MediaFileId should not be empty");
                return tingcoDevice;
            }
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    ismOperationsession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    Object mediafiles = deviceDAO.getMediaFilesByMediaFileId(ismOperationsession, mediaFileId);
                    if (mediafiles != null) {
                        tingcoDevice = tingcoDeviceXML.buildMediaFiles(tingcoDevice, mediafiles);
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
            if (ismOperationsession != null) {
                ismOperationsession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getObjectContactsForContainers(String groupId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = true;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (groupId.equals("")) {
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("GroupId should not be empty");
                return tingcoDevice;
            }
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.CONTAINERS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(groupId, session);
                    Set<String> groupIdsList = groupDAO.getGroupIdsList(groupId, groupTrustsList);
                    List<List<String>> spList = TCMUtil.splitStringList(new ArrayList<String>(groupIdsList), 2000);
                    List<ObjectContacts> objectContactsList = new ArrayList<ObjectContacts>();
                    for (List<String> list : spList) {
                        List<ObjectContacts> objectContactsListtemp = deviceDAO.getObjectContactsByGroupId(new HashSet<String>(list), session);
                        objectContactsList.addAll(objectContactsListtemp);
                        if (objectContactsList.size() > 200) {
                            objectContactsList = objectContactsList.subList(0, 200);
                            break;
                        }

                    }

                    if (!objectContactsList.isEmpty()) {
                        tingcoDevice = tingcoDeviceXML.buildObjectContacts(tingcoDevice, null, objectContactsList);
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

    private TingcoDevice getSensorMonitors(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        Session ismOperationSession = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    ismOperationSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    if (devices.getDevices().getDevice().size() > 0) {
                        Device deviceXML = devices.getDevices().getDevice().get(0);
                        if (deviceXML != null) {
                            UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                            if (userTimeZones2 != null) {
                                String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                                List<GroupTrusts> groupTrusts = groupDAO.getGroupTrustByGroupID(deviceXML.getGroupID().getValue(), session);
                                Set<String> groupIDSet = groupDAO.getGroupIdsList(deviceXML.getGroupID().getValue(), groupTrusts);

                                List<DeviceStatusDataItems> deviceStatusDataItems;
//                                if (deviceXML.getGroupID().getGroupName() == null && deviceXML.getObjectGroupName() == null && deviceXML.getDeviceName() == null && deviceXML.getAssetID() == null && deviceXML.getDeviceDataItems().isEmpty()) {
                                if (deviceXML.getGroupID().getGroupName() == null && deviceXML.getObjectGroupName() == null && deviceXML.getDeviceName() == null && deviceXML.getAssetID() == null) {
                                    if (!deviceXML.getDeviceDataItems().isEmpty()) {
                                        deviceStatusDataItems = deviceDAO.getDeviceStatusDataItemsByDeviceDataItemIds(deviceXML.getDeviceDataItems().get(0).getDeviceDataItemID(), ismOperationSession);
                                    } else {
                                        deviceStatusDataItems = deviceDAO.getDeviceStatusDataItems(groupIDSet, sessions2.getUserId(), session, ismOperationSession);
                                    }
                                } else {
                                    deviceStatusDataItems = deviceDAO.getDeviceStatusDataItemsBySearchCriteria(deviceXML, session);
                                }

                                TCMUtil.loger(getClass().getName(), deviceStatusDataItems.size() + "", "Info");
                                if (!deviceStatusDataItems.isEmpty()) {
                                    List<se.info24.pojo.Device> deviceList = deviceDAO.getDeviceByGroupListandDeviceIdList(session, deviceDAO.getDeviceDataItemDeviceIdsList(deviceStatusDataItems), groupIDSet);
                                    if (deviceList.isEmpty()) {
                                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                                        tingcoDevice.getMsgStatus().setResponseText("There are currently no sensor data to display.");
                                        return tingcoDevice;
                                    }
                                    tingcoDevice = tingcoDeviceXML.buildGetSensorMonitors(tingcoDevice, deviceList, deviceStatusDataItems, deviceXML.getCountryID().getValue(), timeZoneGMToffset, session);
                                } else {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("There are currently no sensor data to display.");
                                    return tingcoDevice;
                                }
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
            ex.printStackTrace();
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            if (ismOperationSession != null) {
                ismOperationSession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getSettingsForDeviceType(String deviceTypeId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (deviceTypeId.equals("")) {
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("DeviceId should not empty");
                return tingcoDevice;
            }
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ObjectTypeSettingTemplates> otst = deviceDAO.getObjectTypeSettingTemplatesByTypeId(deviceTypeId, session);
                    if (!otst.isEmpty()) {
                        List<String> objectSettingTemplateIdList = new ArrayList<String>();
                        for (ObjectTypeSettingTemplates otsts : otst) {
                            objectSettingTemplateIdList.add(otsts.getId().getObjectSettingTemplateId());
                        }
                        List<ObjectSettingTemplates> ost = deviceDAO.getObjectSettingTemplate(objectSettingTemplateIdList, session);
                        if (!ost.isEmpty()) {
                            tingcoDevice = tingcoDeviceXML.buildObjectSettingTemplates(tingcoDevice, ost);
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("No Data found in ObjectSettingTemplates");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data found ");
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

    private TingcoDevice getPropertiesForDeviceType(String deviceTypeId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (!TCMUtil.isValidUUID(deviceTypeId)) {
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("Data is not Valid");
                return tingcoDevice;
            }
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceTypeId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("deviceTypeId should not be null");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ObjectTypeFields> otfList = deviceDAO.getObjectTypeFieldsByObjectID(session, deviceTypeId);
                    if (!otfList.isEmpty()) {
                        List<String> fieldId = new ArrayList<String>();
                        for (ObjectTypeFields objectTypeFields : otfList) {
                            fieldId.add(objectTypeFields.getId().getFieldId());
                        }
                        List<FieldTranslations> ftList = deviceDAO.getFieldTranslationsByIdList(session, fieldId, String.valueOf(countryId));
                        return tingcoDeviceXML.buildPropertiesForDeviceType(tingcoDevice, ftList);
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
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

    private TingcoDevice getDeviceConnectionStatusDetails(String content, String objectTags) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        Session ismOperation = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ismOperation = HibernateUtil.getISMOperationsSessionFactory().openSession();

                    if (!objectTags.equals("")) {
                        objectTags = objectTags.split("/")[2];
                    } else {
                        objectTags = null;
                    }


                    se.info24.devicejaxbPost.TingcoDevice tingcoDevicePost = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    se.info24.devicejaxbPost.Device device = tingcoDevicePost.getDevices().getDevice().get(0);
                    String isDeviceStatus = null; //Checks whether this service is used in DeviceStatus page or in DeviceLocation page. If Devicestatus page means, all the DeviceStatus table records or else Top 200 DeviceStatus records
                    if (device != null) {
                        isDeviceStatus = device.getItemConnectionStatus().getIsDeviceStatus();
                        UserDAO userdao = new UserDAO();
                        Users2 user = userdao.getUsers2ById(sessions2.getUserId(), session).get(0);
                        UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                        if (userTimeZones2 != null) {
                            String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                            List<Object> devicesList = deviceDAO.getChargePointStatusDetails(device, session, objectTags);
                            Set<String> deviceid = deviceDAO.getDeviceIdsListObject(devicesList);
                            if (!deviceid.isEmpty()) {
                                List<ItemConnectionStatus> ICS = new ArrayList<ItemConnectionStatus>();
                                ICS = deviceDAO.getItemConnectionStatusByItemIds(ismOperation, deviceid);
                                if (!deviceid.isEmpty()) {
                                    List<DeviceStatus> deviceStatus = new ArrayList<DeviceStatus>();
                                    if (isDeviceStatus.equalsIgnoreCase("yes")) {
                                        deviceStatus = deviceDAO.getDeviceStatusByDeviceIds(ismOperation, deviceid);
                                    } else if (isDeviceStatus.equalsIgnoreCase("no")) {
                                        deviceStatus = deviceDAO.getDeviceStatusByDeviceIdsTop200(ismOperation, deviceid);
                                    }
                                    List<se.info24.pojo.Device> deviceList = new ArrayList<se.info24.pojo.Device>();
                                    deviceList = deviceDAO.getDeviceByDeviceIdsList(session, new ArrayList<String>(deviceid));
                                    tingcoDevice = tingcoDeviceXML.buildDeviceConnectionStatusDetails(tingcoDevice, deviceStatus, deviceList, ICS, timeZoneGMToffset, user.getCountryId(), session);
                                    return tingcoDevice;
                                } else {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("DeviceStatus Not Found");
                                    return tingcoDevice;
                                }
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                                return tingcoDevice;
                            }
//                            return tingcoDevice;
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("UserTimeZone is not found");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Invalid Device XML found");
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
            if (session != null) {
                session.close();
            }
            if (ismOperation != null) {
                ismOperation.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoDevice getDeviceConnectionStatusDetailsTCMV3(String content, String objectTags) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        Session ismOperation = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ismOperation = HibernateUtil.getISMOperationsSessionFactory().openSession();

                    if (!objectTags.equals("")) {
                        objectTags = objectTags.split("/")[2];
                    } else {
                        objectTags = null;
                    }

                    se.info24.devicejaxbPost.TingcoDevice tingcoDevicePost = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    se.info24.devicejaxbPost.Device device = tingcoDevicePost.getDevices().getDevice().get(0);
                    String isDeviceStatus = null; //Checks whether this service is used in DeviceStatus page or in DeviceLocation page. If Devicestatus page means, all the DeviceStatus table records or else Top 200 DeviceStatus records
                    if (device != null) {
                        isDeviceStatus = device.getItemConnectionStatus().getIsDeviceStatus();
                        UserDAO userdao = new UserDAO();
                        Users2 user = userdao.getUsers2ById(sessions2.getUserId(), session).get(0);
                        UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                        if (userTimeZones2 != null) {
                            String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                            List<Object> devicesList = deviceDAO.getChargePointStatusDetails(device, session, objectTags);
                            if (!devicesList.isEmpty()) {
                                if (devicesList.size() > 200) {
                                    devicesList = devicesList.subList(0, 200);
                                    tingcoDevice.setExceeds200(1);
                                } else {
                                    tingcoDevice.setExceeds200(0);
                                }
                                Set<String> deviceid = deviceDAO.getDeviceIdsListObject(devicesList);
                                List<ItemConnectionStatus> ICS = new ArrayList<ItemConnectionStatus>();
                                ICS = deviceDAO.getItemConnectionStatusByItemIds(ismOperation, deviceid);
                                if (!deviceid.isEmpty()) {
                                    List<DeviceStatus> deviceStatus = new ArrayList<DeviceStatus>();
                                    if (isDeviceStatus.equalsIgnoreCase("yes")) {
                                        deviceStatus = deviceDAO.getDeviceStatusByDeviceIds(ismOperation, deviceid);
                                    } else if (isDeviceStatus.equalsIgnoreCase("no")) {
                                        deviceStatus = deviceDAO.getDeviceStatusByDeviceIdsTop200(ismOperation, deviceid);
                                    }
                                    List<se.info24.pojo.Device> deviceList = new ArrayList<se.info24.pojo.Device>();
                                    deviceList = deviceDAO.getDeviceByDeviceIdsList(session, new ArrayList<String>(deviceid));
                                    if (!deviceList.isEmpty()) {
                                        tingcoDevice = tingcoDeviceXML.buildDeviceConnectionStatusDetailsTCMV3(tingcoDevice, deviceStatus, deviceList, ICS, timeZoneGMToffset, device.getItemConnectionStatus().getConnected(), user.getCountryId(), session);
                                        return tingcoDevice;
                                    } else {
                                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                                        tingcoDevice.getMsgStatus().setResponseText("Devices Not Found");
                                        return tingcoDevice;
                                    }
                                } else {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("DeviceStatus Not Found");
                                    return tingcoDevice;
                                }
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                                return tingcoDevice;
                            }
//                            return tingcoDevice;
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("UserTimeZone is not found");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Invalid Device XML found");
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
            if (session != null) {
                session.close();
            }
            if (ismOperation != null) {
                ismOperation.close();
            }
            delayLog(requestedTime);
        }
    }

    public TingcoDevice deviceUpdate(String content, String objectTags) throws DatatypeConfigurationException {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        Session operationsSession = null;
        tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
        if (request.getAttribute("USERSESSION") != null) {
            UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
            hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.UPDATE);
            if (hasPermission) {
                session = HibernateUtil.getSessionFactory().openSession();
                operationsSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                if (!objectTags.equals("")) {
                    objectTags = objectTags.split("/")[2];
                } else {
                    objectTags = null;
                }

                try {
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    GregorianCalendar gc1 = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    if (devices.getDevices().getDevice().size() > 0) {
                        Device deviceXML = devices.getDevices().getDevice().get(0);
                        if (deviceXML != null) {
                            if (deviceXML.getDeviceID() == null) {
                                TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Device", " ", "Device", "DeviceID Should Not be Empty");
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("DeviceID Should Not Be Empty");
                                return tingcoDevice;
                            }
                            UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                            if (userTimeZones2 != null) {
                                String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                                se.info24.pojo.Device dev = deviceDAO.getDeviceById(deviceXML.getDeviceID(), session);



                                if (deviceXML.getBillingCategories() != null && !deviceXML.getBillingCategories().isEmpty()) {
                                    if (deviceXML.getBillingCategories().get(0).getBillingCategoryID() != null) {

                                        List<DeviceBillingCategories> deviceBillingCategorieslist = deviceDAO.getDeviceBillingCategories(session, deviceXML.getDeviceID());
                                        if (deviceBillingCategorieslist.isEmpty()) {
                                            DeviceBillingCategories deviceBillingCategories = new DeviceBillingCategories();
                                            deviceBillingCategories.setDeviceId(deviceXML.getDeviceID());
                                            List<BillingCategories> billingCategorieses = deviceDAO.getBillingCategorieses(session, deviceXML.getBillingCategories().get(0).getBillingCategoryID());
                                            if (billingCategorieses.isEmpty()) {
                                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                                tingcoDevice.getMsgStatus().setResponseText("Not valid BillingCategories");
                                                return tingcoDevice;
                                            }
                                            deviceBillingCategories.setBillingCategories(billingCategorieses.get(0));
                                            deviceBillingCategories.setLastUpdatedByUserId(sessions2.getUserId());
                                            deviceBillingCategories.setUpdatedDate(gc.getTime());
                                            deviceBillingCategories.setCreatedDate(gc.getTime());

                                            deviceDAO.savedeviceBillingCategories(deviceBillingCategories, session);
//                                            session.saveOrUpdate(deviceBillingCategories);
                                            session.flush();
                                        } else {
                                            DeviceBillingCategories deviceBillingCategories = deviceBillingCategorieslist.get(0);
//                                            deviceBillingCategories.setDeviceId(deviceXML.getDeviceID());
                                            List<BillingCategories> billingCategorieses = deviceDAO.getBillingCategorieses(session, deviceXML.getBillingCategories().get(0).getBillingCategoryID());
                                            if (billingCategorieses.isEmpty()) {
                                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                                tingcoDevice.getMsgStatus().setResponseText("Not valid BillingCategories");
                                                return tingcoDevice;
                                            }
                                            deviceBillingCategories.setBillingCategories(billingCategorieses.get(0));
                                            deviceBillingCategories.setLastUpdatedByUserId(sessions2.getUserId());
                                            deviceBillingCategories.setUpdatedDate(gc.getTime());
                                            deviceDAO.savedeviceBillingCategories(deviceBillingCategories, session);
                                            session.flush();
                                        }
                                    }
                                }

                                if (dev != null) {
                                    if (!dev.getDeviceName2().equalsIgnoreCase(deviceXML.getDeviceName2()) && dev.getGroups().getGroupId().equalsIgnoreCase(deviceXML.getGroupID().getValue())) {
                                        List<se.info24.pojo.Device> list = deviceDAO.getDeviceByGroupIdNDeviceName2(deviceXML.getGroupID().getValue(), deviceXML.getDeviceName2(), session);
                                        if (!list.isEmpty()) {
                                            TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Device", dev.getDeviceName2(), "Device", "Failed");
                                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                                            tingcoDevice.getMsgStatus().setResponseText("You cannot have two Devices with the same Unit ID in the same Organization");
                                            return tingcoDevice;
                                        }
                                    } else if (!dev.getDeviceName2().equalsIgnoreCase(deviceXML.getDeviceName2()) && !dev.getGroups().getGroupId().equalsIgnoreCase(deviceXML.getGroupID().getValue())) {
                                        List<se.info24.pojo.Device> list = deviceDAO.getDeviceByGroupIdNDeviceName2(deviceXML.getGroupID().getValue(), deviceXML.getDeviceName2(), session);
                                        if (!list.isEmpty()) {
                                            TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Device", dev.getDeviceName2(), "Device", "Failed");
                                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                                            tingcoDevice.getMsgStatus().setResponseText("You cannot have two Devices with the same Unit ID in the same Organization");
                                            return tingcoDevice;
                                        }
                                    }
                                    if (deviceXML.getDeviceName() != null) {
                                        dev.setDeviceName(deviceXML.getDeviceName());
                                    }
                                    if (deviceXML.getDeviceName2() != null) {
                                        dev.setDeviceName2(deviceXML.getDeviceName2());
                                    }
                                    if (deviceXML.getDeviceDescription() != null) {
                                        dev.setDeviceDescription(deviceXML.getDeviceDescription());
                                    } else {
                                        dev.setDeviceDescription(null);
                                    }
                                    if (deviceXML.getGroupID() != null) {
                                        dev.setGroups(new Groups(deviceXML.getGroupID().getValue()));
                                    }

                                    if (deviceXML.getDeviceTypeID() != null) {
                                        dev.setDeviceTypes(new DeviceTypes(deviceXML.getDeviceTypeID().getValue()));
                                    }
                                    if (deviceXML.getDeviceEnabled() != null) {
                                        dev.setDeviceEnabled(deviceXML.getDeviceEnabled());
                                    } else {
                                        dev.setDeviceEnabled(0);
                                    }
                                    if (deviceXML.getInvoiceDevice() != null) {
                                        dev.setInvoiceDevice(deviceXML.getInvoiceDevice());
                                    } else {
                                        dev.setInvoiceDevice(0);
                                    }
                                    if (deviceXML.getActiveFromDate() != null) {
                                        gc1.setTime(df.parse(deviceXML.getActiveFromDate()));
                                        gc1.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc1.getTime()));
                                        dev.setActiveFromDate(gc1.getTime());
                                    }
                                    if (deviceXML.getInstalledDate() != null) {
                                        gc1.setTime(df.parse(deviceXML.getInstalledDate()));
                                        gc1.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc1.getTime()));
                                        dev.setInstalledDate(gc1.getTime());
                                    }
                                    dev.setUserId(sessions2.getUserId());
                                    dev.setUpdatedDate(gc.getTime());
                                    if (deviceXML.getSettingsPackageID() != null) {
                                        DeviceSettingsPackages dsp = (DeviceSettingsPackages) session.get(DeviceSettingsPackages.class, deviceXML.getSettingsPackageID());
                                        if (dsp != null) {
                                            dev.setDeviceSettingsPackages(dsp);
                                        }
                                    }
                                    if (deviceXML.getDeviceAgreementID() != null) {
                                        Agreements agreement = (Agreements) session.get(Agreements.class, deviceXML.getDeviceAgreementID());
                                        if (agreement != null) {
                                            dev.setAgreements(agreement);
                                        }
                                    }
                                    if (deviceXML.getAssetID() != null) {
                                        dev.setAssetId(deviceXML.getAssetID());
                                    } else {
                                        dev.setAssetId(null);
                                    }
                                    if (deviceXML.getShortdescription() != null) {
                                        dev.setShortDescription(deviceXML.getShortdescription());
                                    } else {
                                        dev.setShortDescription(null);
                                    }

                                    if (deviceXML.getDeviceAddress() != null) {
                                        DeviceAddress da = deviceXML.getDeviceAddress();
                                        Addresses add = deviceDAO.getAddress(da.getAddressID(), session);
                                        if (add != null) {
                                            if (da.getAddressCity() != null) {
                                                add.setAddressCity(da.getAddressCity());
                                            }
                                            if (da.getAddressStreet() != null) {
                                                add.setAddressStreet(da.getAddressStreet());
                                            }
                                            if (da.getAddressSuite() != null) {
                                                add.setAddressSuite(da.getAddressSuite());
                                            }
                                            if (da.getAddressZip() != null) {
                                                add.setAddressZip(da.getAddressZip());
                                            }
                                            if (da.getCountryID() != null) {
                                                add.setCountry(new Country(da.getCountryID().getValue()));
                                            }
                                            TCMUtil.loger(getClass().getName(), add.getAddressId(), "Info");
                                            add.setUserId(sessions2.getUserId());
                                            add.setUpdatedDate(gc.getTime());
//                                            session.flush();
                                            session.clear();
                                            if (!deviceDAO.saveAddresses(add, session)) {
                                                TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Device", dev.getDeviceName2(), "Addresses", "Failed");
                                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                                tingcoDevice.getMsgStatus().setResponseText("Error Occured while Updating Addresses");
                                                return tingcoDevice;
                                            }
                                            session.flush();
                                            dev.setAddresses(add);
                                        } else {
                                            Addresses addresses = new Addresses();
                                            String addressesId = UUID.randomUUID().toString();
                                            addresses.setAddressId(addressesId);
                                            if (da.getAddressTypeID() != null) {
                                                AddressType at = new AddressType();
                                                at.setAddressTypeId(Integer.valueOf(da.getAddressTypeID().getValue()));
                                                addresses.setAddressType(at);
                                            } else {
                                                AddressType at = new AddressType();
                                                at.setAddressTypeId(1);
                                                addresses.setAddressType(at);
                                            }

                                            if (da.getCountryID() != null) {
                                                addresses.setCountry(new Country(da.getCountryID().getValue()));
                                            }

                                            if (da.getAddressName() != null) {
                                                addresses.setAddressName(da.getAddressName());
                                            }
                                            if (da.getAddressDescription() != null) {
                                                addresses.setAddressDescription(da.getAddressDescription());
                                            }
                                            if (da.getAddressRegion() != null) {
                                                addresses.setAddressRegion(da.getAddressRegion());
                                            }
                                            if (da.getAddressStreet() != null) {
                                                addresses.setAddressStreet(da.getAddressStreet());
                                            }
                                            if (da.getAddressStreet2() != null) {
                                                addresses.setAddressStreet2(da.getAddressStreet2());
                                            }
                                            if (da.getAddressSuite() != null) {
                                                addresses.setAddressSuite(da.getAddressSuite());
                                            }
                                            if (da.getAddressZip() != null) {
                                                addresses.setAddressZip(da.getAddressZip());
                                            }
                                            if (da.getAddressCity() != null) {
                                                addresses.setAddressCity(da.getAddressCity());
                                            }

                                            if (da.getCountryStateID() != null) {
                                                addresses.setCountryStateId(da.getCountryStateID());
                                            }
                                            if (da.getAddressPhone() != null) {
                                                addresses.setAddressPhone(da.getAddressPhone());
                                            }
                                            if (da.getAddressMobile() != null) {
                                                addresses.setAddressMobile(da.getAddressMobile());
                                            }

                                            if (da.getAddressFax() != null) {
                                                addresses.setAddressFax(String.valueOf(da.getAddressFax()));
                                            }
                                            if (da.getAddressEmail() != null) {
                                                addresses.setAddressEmail(da.getAddressEmail());
                                            }
                                            if (da.getAddressWeb() != null) {
                                                addresses.setAddressWeb(da.getAddressWeb());
                                            }

                                            if (da.getAddressIM() != null) {
                                                addresses.setAddressIm(da.getAddressIM());
                                            }
                                            addresses.setUserId(sessions2.getUserId());
                                            addresses.setCreatedDate(gc.getTime());
                                            addresses.setUpdatedDate(gc.getTime());

                                            UserAddresses useraddress = new UserAddresses();
                                            useraddress.setUserId(sessions2.getUserId());
                                            useraddress.setCreatedDate(gc.getTime());
                                            useraddress.setUpdatedDate(gc.getTime());
                                            useraddress.setAddresses(addresses);
                                            //  useraddress.getAddresses().setAddressId(addressId);

//                                        addresses.getUserAddresseses().add(useraddress);
                                            session.clear();

                                            if (!deviceDAO.saveAddresses(addresses, session)) {
                                                TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Device", dev.getDeviceName2(), "Addresses", "Failed");
                                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                                tingcoDevice.getMsgStatus().setResponseText("Error Occured while adding Addresses");
                                                return tingcoDevice;
                                            }
                                            session.flush();
                                            session.clear();
                                            dev.setAddresses(addresses);
                                        }
                                    }

                                    if (deviceXML.getDeviceOperationsStatusID() != null) {
                                        List<DeviceOperationsMember> domList = new ArrayList<DeviceOperationsMember>();
                                        domList = deviceDAO.getdeviceOperationsMember(session, deviceXML.getDeviceID());
                                        if (!domList.isEmpty()) {
                                            if (!deviceDAO.saveDeviceOperationsMember(domList, deviceXML.getDeviceOperationsStatusID(), session)) {
                                                TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Device", dev.getDeviceName2(), "DeviceOperationsMember", "Failed");
                                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                                tingcoDevice.getMsgStatus().setResponseText("Error Occured while Updating DeviceOperationsMember");
                                                return tingcoDevice;
                                            }
                                        } else {
                                            DeviceOperationsMember dom = new DeviceOperationsMember();
                                            dom.setDeviceOperationsMemberId(UUID.randomUUID().toString());
                                            dom.setDevice(dev);
                                            dom.setDeviceOperationsStatus(new DeviceOperationsStatus(deviceXML.getDeviceOperationsStatusID()));
                                            dom.setActiveFromDate(gc.getTime());
                                            dom.setUserId(sessions2.getUserId());
                                            dom.setCreatedDate(gc.getTime());
                                            dom.setUpdatedDate(gc.getTime());
                                            if (!deviceDAO.insertDeviceOperationsMember(dom, session)) {
                                                TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Device", dev.getDeviceName2(), "DeviceOperationsMember", "Failed");
                                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                                tingcoDevice.getMsgStatus().setResponseText("Error Occured while creating DeviceOperationsMember");
                                                return tingcoDevice;
                                            }
                                        }
                                    }
                                    if (!deviceDAO.saveDevice(dev, session)) {
                                        TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Device", dev.getDeviceName2(), "Device", "Failed");
                                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                                        tingcoDevice.getMsgStatus().setResponseText("Error Occured while Updating Device");
                                        return tingcoDevice;
                                    } else {
                                        TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Device", dev.getDeviceName2(), "Device", "Success");

                                        ObjectTags objectTagsPojo = (ObjectTags) session.get(ObjectTags.class, dev.getDeviceId());
                                        Transaction tx = session.beginTransaction();
                                        if (objectTagsPojo != null) {
                                            if (objectTags != null) {
                                                objectTagsPojo.setSearchTags(objectTags);
                                                objectTagsPojo.setLastUpdatedByUserId(sessions2.getUserId());
                                                objectTagsPojo.setUpdatedDate(gc.getTime());
                                                session.saveOrUpdate(objectTagsPojo);
                                            } else {
                                                session.delete(objectTagsPojo);
                                            }
                                            tx.commit();
                                        } else {
                                            if (objectTags != null) {
                                                objectTagsPojo = new ObjectTags();
                                                objectTagsPojo.setObjectId(dev.getDeviceId());
                                                objectTagsPojo.setSearchTags(objectTags);
                                                objectTagsPojo.setLastUpdatedByUserId(sessions2.getUserId());
                                                objectTagsPojo.setUpdatedDate(gc.getTime());
                                                objectTagsPojo.setCreatedDate(gc.getTime());
                                                session.saveOrUpdate(objectTagsPojo);
                                                tx.commit();
                                            }
                                        }
                                    }
                                } else {
                                    List<se.info24.pojo.Device> list = deviceDAO.getDeviceByGroupIdNDeviceName2(deviceXML.getGroupID().getValue(), deviceXML.getDeviceName2(), session);
                                    if (!list.isEmpty()) {
                                        TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Device", deviceXML.getDeviceName2(), "Device", "Failed");
                                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                                        tingcoDevice.getMsgStatus().setResponseText("You cannot have two Devices with the same Unit ID in the same Organization");
                                        return tingcoDevice;
                                    }
                                    se.info24.pojo.Device device = new se.info24.pojo.Device();
                                    if (deviceXML.getDeviceID() != null) {
                                        device.setDeviceId(deviceXML.getDeviceID());
                                    } else {
                                        device.setDeviceId(UUID.randomUUID().toString());
                                    }

                                    if (deviceXML.getDeviceName() != null) {
                                        device.setDeviceName(deviceXML.getDeviceName());
                                    }
                                    if (deviceXML.getDeviceName2() != null) {
                                        device.setDeviceName2(deviceXML.getDeviceName2());
                                    }
                                    if (deviceXML.getDeviceDescription() != null) {
                                        device.setDeviceDescription(deviceXML.getDeviceDescription());
                                    }
                                    if (deviceXML.getGroupID() != null) {
                                        device.setGroups(new Groups(deviceXML.getGroupID().getValue()));
                                    }
                                    if (deviceXML.getDeviceTypeID() != null) {
                                        device.setDeviceTypes(new DeviceTypes(deviceXML.getDeviceTypeID().getValue()));
                                    }
                                    if (deviceXML.getDeviceEnabled() != null) {
                                        device.setDeviceEnabled(deviceXML.getDeviceEnabled());
                                    } else {
                                        device.setDeviceEnabled(0);
                                    }
                                    if (deviceXML.getInvoiceDevice() != null) {
                                        device.setInvoiceDevice(deviceXML.getInvoiceDevice());
                                    } else {
                                        device.setInvoiceDevice(0);
                                    }
                                    if (deviceXML.getActiveFromDate() != null) {
                                        device.setActiveFromDate(df.parse(deviceXML.getActiveFromDate()));
                                    } else {
                                        device.setActiveFromDate(gc.getTime());
                                    }

                                    if (deviceXML.getInstalledDate() != null) {
                                        device.setInstalledDate(df.parse(deviceXML.getInstalledDate()));
                                    } else {
                                        device.setInstalledDate(gc.getTime());
                                    }
                                    device.setUserId(sessions2.getUserId());
                                    device.setCreatedDate(gc.getTime());
                                    device.setUpdatedDate(gc.getTime());
                                    if (deviceXML.getSettingsPackageID() != null) {
                                        device.setDeviceSettingsPackages(new DeviceSettingsPackages(deviceXML.getSettingsPackageID()));
                                    }
                                    if (deviceXML.getDeviceAgreementID() != null) {
                                        device.setAgreements(new Agreements(deviceXML.getDeviceAgreementID()));
                                    }
                                    if (deviceXML.getAssetID() != null) {
                                        device.setAssetId(deviceXML.getAssetID());
                                    }
                                    if (deviceXML.getShortdescription() != null) {
                                        device.setShortDescription(deviceXML.getShortdescription());
                                    }

                                    if (deviceXML.getDeviceAddress() != null) {
                                        DeviceAddress da = deviceXML.getDeviceAddress();
                                        Addresses add = deviceDAO.getAddress(da.getAddressID(), session);
                                        if (add != null) {
                                            if (da.getAddressCity() != null) {
                                                add.setAddressCity(da.getAddressCity());
                                            }
                                            if (da.getAddressStreet() != null) {
                                                add.setAddressStreet(da.getAddressStreet());
                                            }
                                            if (da.getAddressSuite() != null) {
                                                add.setAddressSuite(da.getAddressSuite());
                                            }
                                            if (da.getAddressZip() != null) {
                                                add.setAddressZip(da.getAddressZip());
                                            }
                                            if (da.getCountryID() != null) {
                                                Country country = (Country) session.get(Country.class, da.getCountryID().getValue());
                                                add.setCountry(country);
                                            }
                                            add.setUserId(sessions2.getUserId());
                                            add.setUpdatedDate(gc.getTime());
                                            dev.setAddresses(add);
                                            if (!deviceDAO.saveAddresses(add, session)) {
                                                TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Device", dev.getDeviceName2(), "Addresses", "Failed");
                                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                                tingcoDevice.getMsgStatus().setResponseText("Error Occured while Updating Addresses");
                                                return tingcoDevice;
                                            }
                                        } else {
                                            Addresses addresses = new Addresses();
                                            String addressesId = UUID.randomUUID().toString();
                                            addresses.setAddressId(addressesId);
                                            if (da.getAddressTypeID() != null) {
                                                AddressType at = (AddressType) session.get(AddressType.class, da.getAddressTypeID().getValue());
//                                                at.setAddressTypeId(Integer.valueOf(da.getAddressTypeID().getValue()));
                                                if (at != null) {
                                                    addresses.setAddressType(at);
                                                }
                                            } else {
                                                AddressType at = new AddressType();
                                                at.setAddressTypeId(1);
                                                addresses.setAddressType(at);
                                            }

                                            if (da.getCountryID() != null) {
                                                Country country = (Country) session.get(Country.class, da.getCountryID().getValue());
                                                addresses.setCountry(country);
                                            }

                                            if (da.getAddressName() != null) {
                                                addresses.setAddressName(da.getAddressName());
                                            }
                                            if (da.getAddressDescription() != null) {
                                                addresses.setAddressDescription(da.getAddressDescription());
                                            }
                                            if (da.getAddressRegion() != null) {
                                                addresses.setAddressRegion(da.getAddressRegion());
                                            }
                                            if (da.getAddressStreet() != null) {
                                                addresses.setAddressStreet(da.getAddressStreet());
                                            }
                                            if (da.getAddressStreet2() != null) {
                                                addresses.setAddressStreet2(da.getAddressStreet2());
                                            }
                                            if (da.getAddressSuite() != null) {
                                                addresses.setAddressSuite(da.getAddressSuite());
                                            }
                                            if (da.getAddressZip() != null) {
                                                addresses.setAddressZip(da.getAddressZip());
                                            }
                                            if (da.getAddressCity() != null) {
                                                addresses.setAddressCity(da.getAddressCity());
                                            }

                                            if (da.getCountryStateID() != null) {
                                                addresses.setCountryStateId(da.getCountryStateID());
                                            }
                                            if (da.getAddressPhone() != null) {
                                                addresses.setAddressPhone(da.getAddressPhone());
                                            }
                                            if (da.getAddressMobile() != null) {
                                                addresses.setAddressMobile(da.getAddressMobile());
                                            }

                                            if (da.getAddressFax() != null) {
                                                addresses.setAddressFax(String.valueOf(da.getAddressFax()));
                                            }
                                            if (da.getAddressEmail() != null) {
                                                addresses.setAddressEmail(da.getAddressEmail());
                                            }
                                            if (da.getAddressWeb() != null) {
                                                addresses.setAddressWeb(da.getAddressWeb());
                                            }

                                            if (da.getAddressIM() != null) {
                                                addresses.setAddressIm(da.getAddressIM());
                                            }
                                            addresses.setUserId(sessions2.getUserId());
                                            addresses.setCreatedDate(gc.getTime());
                                            addresses.setUpdatedDate(gc.getTime());

                                            UserAddresses useraddress = new UserAddresses();
                                            useraddress.setUserId(sessions2.getUserId());
                                            useraddress.setCreatedDate(gc.getTime());
                                            useraddress.setUpdatedDate(gc.getTime());
                                            useraddress.setAddresses(addresses);
                                            //  useraddress.getAddresses().setAddressId(addressId);
                                            device.setAddresses(addresses);
//                                        addresses.getUserAddresseses().add(useraddress);
                                            if (!deviceDAO.saveAddresses(addresses, session)) {
                                                TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Device", dev.getDeviceName2(), "Addresses", "Failed");
                                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                                tingcoDevice.getMsgStatus().setResponseText("Error Occured while adding Addresses");
                                                return tingcoDevice;
                                            }
                                        }
                                    }

                                    if (!deviceDAO.saveDevice(device, session)) {
                                        TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Device", dev.getDeviceName2(), "Device", "Failed");
                                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                                        tingcoDevice.getMsgStatus().setResponseText("Error Occured while adding Device");
                                        return tingcoDevice;
                                    } else {
                                        TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Device", dev.getDeviceName2(), "Device", "Success");

                                        ObjectTags objectTagsPojo = (ObjectTags) session.get(ObjectTags.class, dev.getDeviceId());
                                        Transaction tx = session.beginTransaction();
                                        if (objectTagsPojo != null) {
                                            if (objectTags != null) {
                                                objectTagsPojo.setSearchTags(objectTags);
                                                objectTagsPojo.setLastUpdatedByUserId(sessions2.getUserId());
                                                objectTagsPojo.setUpdatedDate(gc.getTime());
                                                session.saveOrUpdate(objectTagsPojo);
                                            } else {
                                                session.delete(objectTagsPojo);
                                            }
                                            tx.commit();
                                        } else {
                                            if (objectTags != null) {
                                                objectTagsPojo = new ObjectTags();
                                                objectTagsPojo.setObjectId(dev.getDeviceId());
                                                objectTagsPojo.setSearchTags(objectTags);
                                                objectTagsPojo.setLastUpdatedByUserId(sessions2.getUserId());
                                                objectTagsPojo.setUpdatedDate(gc.getTime());
                                                objectTagsPojo.setCreatedDate(gc.getTime());
                                                session.saveOrUpdate(objectTagsPojo);
                                                tx.commit();
                                            }
                                        }
                                    }
//                                    }
                                } // new device insert done
                                if (deviceXML.getDeviceOperationsStatusID() != null) {
                                    List<DeviceOperationsMember> domList = new ArrayList<DeviceOperationsMember>();
                                    domList = deviceDAO.getdeviceOperationsMember(session, deviceXML.getDeviceID());
                                    if (!domList.isEmpty()) {
                                        if (!deviceDAO.saveDeviceOperationsMember(domList, deviceXML.getDeviceOperationsStatusID(), session)) {
                                            TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Device", dev.getDeviceName2(), "DeviceOperationsMember", "Failed");
                                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                                            tingcoDevice.getMsgStatus().setResponseText("Error Occured while Updating DeviceOperationsMember");
                                            return tingcoDevice;
                                        }
                                    } else {
                                        DeviceOperationsMember dom = new DeviceOperationsMember();
                                        dom.setDeviceOperationsMemberId(UUID.randomUUID().toString());
                                        se.info24.pojo.Device de = (se.info24.pojo.Device) session.get(se.info24.pojo.Device.class, deviceXML.getDeviceID());
//                                        de.setDeviceId(deviceXML.getDeviceID());
                                        if (de != null) {
                                            dom.setDevice(de);
                                        }
                                        dom.setDeviceOperationsStatus(new DeviceOperationsStatus(deviceXML.getDeviceOperationsStatusID()));
                                        dom.setActiveFromDate(gc.getTime());
                                        dom.setUserId(sessions2.getUserId());
                                        dom.setCreatedDate(gc.getTime());
                                        dom.setUpdatedDate(gc.getTime());
                                        if (!deviceDAO.insertDeviceOperationsMember(dom, session)) {
                                            TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Device", dev.getDeviceName2(), "DeviceOperationsMember", "Failed");
                                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                                            tingcoDevice.getMsgStatus().setResponseText("Error Occured while creating DeviceOperationsMember");
                                            return tingcoDevice;
                                        }

                                    }
                                }
                                if (deviceXML.getDeviceID() != null) {
                                    String objectId = deviceXML.getDeviceID();
                                    List<ObjectContactMemberships> objectContactMembershipsList = deviceDAO.getObjectContactMembershipsbyIdandIsDefault(objectId, session);
                                    if (!objectContactMembershipsList.isEmpty()) {
                                        if (deviceXML.getObjectContactID() != null) {
                                            String objectcontactId = deviceXML.getObjectContactID();
                                            if (deviceDAO.deleteObjectContactMemberships(objectContactMembershipsList, session)) {
                                                if (!deviceDAO.insertObjectContactMemberships(objectId, objectcontactId, session, sessions2.getUserId())) {
                                                    TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Device", dev.getDeviceName2(), "ObjectContactMemberships", "Failed");
                                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                                    tingcoDevice.getMsgStatus().setResponseText("Error occured while Inserting new ObjectContactMemberships");
                                                    return tingcoDevice;
                                                }
                                            } else {
                                                TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Device", dev.getDeviceName2(), "ObjectContactMemberships", "Failed");
                                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                                tingcoDevice.getMsgStatus().setResponseText("Error occured when Deleting ObjectContactMemberships");
                                                return tingcoDevice;
                                            }
                                        } else {
                                            if (!deviceDAO.deleteObjectContactMemberships(objectContactMembershipsList, session)) {
                                                TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Device", dev.getDeviceName2(), "ObjectContactMemberships", "Failed");
                                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                                tingcoDevice.getMsgStatus().setResponseText("Error occured while Deleting ObjectContactMemberships");
                                                return tingcoDevice;
                                            }
                                        }
                                    }
                                }
                                DeviceStatus de = deviceDAO.getDeviceStatusByDeviceId(deviceXML.getDeviceID(), operationsSession);
                                if (deviceXML.getDeviceStatus() != null) {
                                    if (de != null) {
                                        if (deviceXML.getDeviceStatus().getPosLatitude() != null) {
                                            de.setPosLatitude(deviceXML.getDeviceStatus().getPosLatitude());
                                        } else {
                                            de.setPosLatitude(null);
                                        }
                                        if (deviceXML.getDeviceStatus().getPosLongitude() != null) {
                                            de.setPosLongitude(deviceXML.getDeviceStatus().getPosLongitude());
                                        } else {
                                            de.setPosLongitude(null);
                                        }
                                        if (deviceXML.getDeviceStatus().getPosDepth() != null) {
                                            de.setPosDepth(deviceXML.getDeviceStatus().getPosDepth());
                                        } else {
                                            de.setPosDepth(null);
                                        }
                                        if (deviceXML.getDeviceStatus().getPosDirection() != null) {
                                            de.setPosDirection(Integer.valueOf(deviceXML.getDeviceStatus().getPosDirection()));
                                        }
                                        if (deviceXML.getDeviceStatus().getCoordinateSystemID() != null) {
                                            de.setCoordinateSystemId(deviceXML.getDeviceStatus().getCoordinateSystemID());
                                        }
                                        de.setUpdatedDate(gc.getTime());
                                        if (!deviceDAO.saveOrUpdateDeviceStatus(de, operationsSession)) {
                                            TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Device", dev.getDeviceName2(), "DeviceStatus", "Failed");
                                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                                            tingcoDevice.getMsgStatus().setResponseText("Error Occured while Updating");
                                            return tingcoDevice;
                                        }
                                    } else {
                                        DeviceStatus des = new DeviceStatus();
                                        des.setDeviceId(deviceXML.getDeviceID());
                                        des.setDataItemId(UUID.randomUUID().toString());
                                        des.setDataItemTime(gc.getTime());
                                        des.setGroupId(deviceXML.getGroupID().getValue());
                                        des.setIsEnabled(1);
                                        if (deviceXML.getDeviceStatus().getPosLatitude() != null) {
                                            des.setPosLatitude(deviceXML.getDeviceStatus().getPosLatitude());
                                        }
                                        if (deviceXML.getDeviceStatus().getPosLongitude() != null) {
                                            des.setPosLongitude(deviceXML.getDeviceStatus().getPosLongitude());
                                        }
                                        if (deviceXML.getDeviceStatus().getPosDepth() != null) {
                                            des.setPosDepth(deviceXML.getDeviceStatus().getPosDepth());
                                        }
                                        des.setPosDirection(0);
                                        des.setCoordinateSystemId("WGS84");
                                        des.setMsgId(UUID.randomUUID().toString());
                                        des.setMsgTimeStamp(gc.getTime());
                                        des.setCreatedDate(gc.getTime());
                                        des.setUpdatedDate(gc.getTime());
                                        if (!deviceDAO.saveOrUpdateDeviceStatus(des, operationsSession)) {
                                            TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Device", dev.getDeviceName2(), "DeviceStatus", "Failed");
                                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                                            tingcoDevice.getMsgStatus().setResponseText("Error Occured while adding new DeviceStatus");
                                            return tingcoDevice;
                                        }
                                    }
                                } else {
                                    if (de != null) {
                                        if (!deviceDAO.deleteDeviceStatus(de, operationsSession)) {
                                            TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Device", dev.getDeviceName2(), "DeviceStatus", "Failed");
                                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                                            tingcoDevice.getMsgStatus().setResponseText("Error Occured while deleting DeviceStatus");
                                            return tingcoDevice;
                                        }
                                    }
                                }
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("UserTimezone not found");
                                return tingcoDevice;
                            }
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Invalid Device XML Found");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Invalid Device XML Found");
                        return tingcoDevice;
                    }
                } catch (Exception e) {
                    TCMUtil.exceptionLog(getClass().getName(), e);
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("Error occured");
                } finally {
                    if (session != null) {
                        session.close();
                    }
                    if (operationsSession != null) {
                        operationsSession.close();
                    }
                    delayLog(requestedTime);
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
        return tingcoDevice;
    }

    private TingcoDevice addAdvancedDeviceSetting(String settingName, String settingValue, String settingDataTypeId, String deviceId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (settingName.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("settingName should not be empty");
                    return tingcoDevice;
                }
                if (settingValue.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("settingValue should not be empty");
                    return tingcoDevice;
                }
                if (settingDataTypeId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("settingDataTypeId should not be empty");
                    return tingcoDevice;
                }
                if (deviceId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceId should not be empty");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    DeviceSettings deviceSettings = deviceDAO.getDeviceSettingsByIds(deviceId, settingName, session);
                    if (deviceSettings != null) {
                        settingValue = TCMUtil.convertHexToString(settingValue);
                        deviceSettings.setDeviceSettingValue(settingValue);
                        deviceSettings.setActiveFromDate(gc.getTime());
                        deviceSettings.setUserId(sessions2.getUserId());
                        deviceSettings.setUpdatedDate(gc.getTime());
                        if (!deviceDAO.saveDeviceSettings(deviceSettings, session)) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error occured while updating DeviceSettings");
                            return tingcoDevice;
                        }
                    } else {
                        deviceSettings = new DeviceSettings();
                        deviceSettings.setDeviceSettingId(UUID.randomUUID().toString());
//                        deviceSettings.setDeviceSettingParentID(null);
                        se.info24.pojo.Device de = new se.info24.pojo.Device();
                        de.setDeviceId(deviceId);
                        deviceSettings.setDevice(de);
                        deviceSettings.setDeviceSettingName(settingName);
                        settingValue = TCMUtil.convertHexToString(settingValue);
                        deviceSettings.setDeviceSettingValue(settingValue);
                        SettingDataType sdt = new SettingDataType();
                        sdt.setSettingDataTypeId(settingDataTypeId);
                        deviceSettings.setSettingDataType(sdt);
                        deviceSettings.setActiveFromDate(gc.getTime());
                        deviceSettings.setUserId(sessions2.getUserId());
                        deviceSettings.setCreatedDate(gc.getTime());
                        deviceSettings.setUpdatedDate(gc.getTime());
                        if (!deviceDAO.saveDeviceSettings(deviceSettings, session)) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error occured while inserting DeviceSettings");
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
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice addAdvancedDeviceSettings(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        TingcoDevice tingcoDevice = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
//                if (ht.containsKey(TCMUtil.DEVICE)) {
//                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
//                    for (int i = 0; i < operations.size(); i++) {
//                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                hasPermission = true;
//                            break;
//                        }
//                    }
//                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    GregorianCalendar gc = new GregorianCalendar();
                    se.info24.devicejaxbPost.TingcoDevice tingcoDevices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    if (!tingcoDevices.getDevices().getDevice().isEmpty()) {
                        se.info24.devicejaxbPost.Device d = tingcoDevices.getDevices().getDevice().get(0);
                        if (d != null) {
                            if (d.getDeviceSettings() != null) {
                                if (!d.getDeviceSettings().getDeviceSetting().isEmpty()) {
                                    DeviceSetting dsg = d.getDeviceSettings().getDeviceSetting().get(0);
                                    DeviceSettings deviceSettings = deviceDAO.getDeviceSettingsByIds(d.getDeviceID(), dsg.getDeviceSettingName(), session);
                                    if (deviceSettings != null) {
                                        deviceSettings.setDeviceSettingValue(dsg.getDeviceSettingValue());
                                        deviceSettings.setActiveFromDate(gc.getTime());
                                        deviceSettings.setUserId(sessions2.getUserId());
                                        deviceSettings.setUpdatedDate(gc.getTime());
                                        if (!deviceDAO.saveDeviceSettings(deviceSettings, session)) {
                                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                                            tingcoDevice.getMsgStatus().setResponseText("Error occured while updating DeviceSettings");
                                            return tingcoDevice;
                                        }
                                    } else {
                                        deviceSettings = new DeviceSettings();
                                        deviceSettings.setDeviceSettingId(UUID.randomUUID().toString());
//                                      deviceSettings.setDeviceSettingParentID(null);
                                        se.info24.pojo.Device de = new se.info24.pojo.Device();
                                        de.setDeviceId(d.getDeviceID());
                                        deviceSettings.setDevice(de);
                                        deviceSettings.setDeviceSettingName(dsg.getDeviceSettingName());
                                        deviceSettings.setDeviceSettingValue(dsg.getDeviceSettingValue());
                                        SettingDataType sdt = new SettingDataType();
                                        sdt.setSettingDataTypeId(dsg.getObjectSettingTemplates().getSettingDataType().get(0).getSettingDataTypeID());
                                        deviceSettings.setSettingDataType(sdt);
                                        deviceSettings.setActiveFromDate(gc.getTime());
                                        deviceSettings.setUserId(sessions2.getUserId());
                                        deviceSettings.setCreatedDate(gc.getTime());
                                        deviceSettings.setUpdatedDate(gc.getTime());
                                        if (!deviceDAO.saveDeviceSettings(deviceSettings, session)) {
                                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                                            tingcoDevice.getMsgStatus().setResponseText("Error occured while inserting DeviceSettings");
                                            return tingcoDevice;
                                        }
                                    }
                                } else {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("Atleast one DeviceSetting should Found");
                                    return tingcoDevice;
                                }
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("DeviceSettings not Found");
                                return tingcoDevice;
                            }
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Device not Found in XML");
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
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }

        return tingcoDevice;
    }

    private TingcoDevice addDeviceSetting(String deviceId, String objectSettingTemplateId, String settingValue, String deviceSettingParentId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        TingcoDevice tingcoDevice = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceId should not be empty");
                    return tingcoDevice;
                }
                if (objectSettingTemplateId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("ObjectSettingTemplateId should not be empty");
                    return tingcoDevice;
                }
                if (settingValue.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("SettingValue should not be empty");
                    return tingcoDevice;
                }
                if (!deviceSettingParentId.equals("")) {
                    deviceSettingParentId = deviceSettingParentId.split("/")[2];
                } else {
                    deviceSettingParentId = null;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ObjectSettingTemplates ost = deviceDAO.getObjectSettingTemplates(objectSettingTemplateId, session);
                    if (ost != null) {
                        if (!deviceDAO.addDeviceSetting(ost, deviceId, settingValue, deviceSettingParentId, sessions2.getUserId(), session)) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error Occured while Adding DeviceSettings");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Found in ObjectSettingTemplates");
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

    private TingcoDevice addDeviceSettingbyPackage(String objectSettingPackageId, String deviceId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        TingcoDevice tingcoDevice = null;
        Transaction tx = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    boolean result = false;
                    List<ObjectSettingPackageTemplates> osptList = deviceDAO.getObjectSettingPackageTemplatesByPackageId(objectSettingPackageId, session);
                    if (!osptList.isEmpty()) {
                        for (ObjectSettingPackageTemplates ospt : osptList) {
                            DeviceSettings ds = deviceDAO.getDeviceSettingsByIds(deviceId, ospt.getObjectSettingTemplates().getObjectSettingName(), session);
                            if (ds != null) {
                                ds.setDeviceSettingValue(ospt.getObjectSettingDefaultValue());
                                ds.setActiveFromDate(gc.getTime());
                                ds.setUserId(sessions2.getUserId());
                                ds.setUpdatedDate(gc.getTime());
                                ds.setObjectSettingTemplateId(ospt.getObjectSettingTemplates().getObjectSettingTemplateId());
                                // deviceDAO.saveDeviceSettings(ds, session);
                                session.saveOrUpdate(ds);
                                if (deviceDAO.traverseDeviceSettings(deviceId, ospt.getObjectSettingTemplates(), sessions2.getUserId(), 1, session)) {
                                    result = true;
                                }
                            } else {
                                ds = new DeviceSettings();
                                ds.setDeviceSettingId(UUID.randomUUID().toString());
//                                ds.setDeviceSettingParentID(null);
                                se.info24.pojo.Device de = new se.info24.pojo.Device();
                                de.setDeviceId(deviceId);
                                ds.setDevice(de);
                                ds.setDeviceSettingName(ospt.getObjectSettingTemplates().getObjectSettingName());
                                ds.setDeviceSettingValue(ospt.getObjectSettingDefaultValue());
                                SettingDataType sd = new SettingDataType();
                                sd.setSettingDataTypeId(ospt.getObjectSettingTemplates().getSettingDataType().getSettingDataTypeId());
                                ds.setSettingDataType(sd);
                                ds.setActiveFromDate(gc.getTime());
                                ds.setUserId(sessions2.getUserId());
                                ds.setCreatedDate(gc.getTime());
                                ds.setUpdatedDate(gc.getTime());
                                ds.setObjectSettingTemplateId(ospt.getObjectSettingTemplates().getObjectSettingTemplateId());
                                //deviceDAO.saveDeviceSettings(ds, session);
                                session.saveOrUpdate(ds);
                                if (deviceDAO.traverseDeviceSettings(deviceId, ospt.getObjectSettingTemplates(), sessions2.getUserId(), 1, session)) {
                                    result = true;
                                }
                            }
                        }
                        if (result) {
                            // if(!tx.wasCommitted()){
                            tx.commit();
                        // }
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error occured while adding DeviceSettings");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Found in ObjectSettingPackageTemplates");
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
                tx.rollback();
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("Error occured");
            }
            TCMUtil.exceptionLog(getClass().getName(), ex);
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice addServiceDeviceSetting(String objectSettingTemplateId, String serviceDeviceSubscriptionId, String serviceDeviceSettingValue) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        TingcoDevice tingcoDevice = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (objectSettingTemplateId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("ObjectSettingTemplateId should not be empty");
                    return tingcoDevice;
                }
                if (serviceDeviceSubscriptionId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("ServiceDeviceSubscriptionId should not be empty");
                    return tingcoDevice;
                }
                if (serviceDeviceSettingValue.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("ServiceDeviceSettingValue should not be empty");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    GregorianCalendar gc = new GregorianCalendar();
                    ObjectSettingTemplates ost = deviceDAO.getObjectSettingTemplates(objectSettingTemplateId, session);
                    ServiceDeviceSettings sds = deviceDAO.getServiceDeviceSettingsBySubscriptionIdandName(ost.getObjectSettingName(), serviceDeviceSubscriptionId, session);
                    serviceDeviceSettingValue = TCMUtil.convertHexToString(serviceDeviceSettingValue);
                    if (sds != null) {
                        sds.setServiceDeviceSettingValue(serviceDeviceSettingValue);
                        sds.setActiveFromDate(gc.getTime());
                        sds.setUserId(sessions2.getUserId());
                        sds.setUpdatedDate(gc.getTime());
                        if (!deviceDAO.saveServiceDeviceSettings(sds, session)) {
                            tingcoDevice.getMsgStatus().setResponseCode(-10);
                            tingcoDevice.getMsgStatus().setResponseText("Error occured while updating ServiceDeviceSettings");
                            return tingcoDevice;
                        }
                    } else {
                        sds = new ServiceDeviceSettings();
                        sds.setServiceDeviceSettingId(UUID.randomUUID().toString());
                        sds.setServiceDeviceSettingParentID(null);
                        ServiceDeviceSubscriptions serviceDeviceSubscriptions = new ServiceDeviceSubscriptions();
                        serviceDeviceSubscriptions.setServiceDeviceSubscriptionId(serviceDeviceSubscriptionId);
                        sds.setServiceDeviceSubscriptions(serviceDeviceSubscriptions);
                        sds.setServiceDeviceSettingName(ost.getObjectSettingName());
                        sds.setServiceDeviceSettingValue(serviceDeviceSettingValue);
                        sds.setSettingDataTypeId(ost.getSettingDataType().getSettingDataTypeId());
                        sds.setActiveFromDate(gc.getTime());
                        sds.setUserId(sessions2.getUserId());
                        sds.setCreatedDate(gc.getTime());
                        sds.setUpdatedDate(gc.getTime());
                        sds.setObjectSettingTemplateId(objectSettingTemplateId);
                        if (!deviceDAO.saveServiceDeviceSettings(sds, session)) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error occured while inserting ServiceDeviceSettings");
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
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice addSettingsPackage(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        TingcoDevice tingcoDevice = null;
        Transaction tx = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    CountryDAO countryDAO = new CountryDAO();
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    se.info24.devicejaxbPost.TingcoDevice tingcoDevices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    if (!tingcoDevices.getDevices().getDevice().isEmpty()) {
                        se.info24.devicejaxbPost.Device d = tingcoDevices.getDevices().getDevice().get(0);
                        se.info24.pojo.Device device = deviceDAO.getDeviceById(d.getDeviceID(), session);
                        if (d != null) {
                            if (device != null) {
                                if (d.getDeviceSettings() != null) {
                                    List<se.info24.devicejaxbPost.DeviceSetting> dseList = d.getDeviceSettings().getDeviceSetting();
                                    if (!dseList.isEmpty()) {
                                        ObjectSettingTemplates osts = new ObjectSettingTemplates();
                                        String objectSettingTemplateId = null;
                                        String objectSettingPackageId = UUID.randomUUID().toString();
                                        ObjectSettingPackages osp = new ObjectSettingPackages();
                                        osp.setObjectSettingPackageId(objectSettingPackageId);
                                        osp.setObjectTypeId(device.getDeviceTypes().getDeviceTypeId());
                                        osp.setLastUpdatedByUser(sessions2.getUserId());
                                        osp.setCreatedDate(gc.getTime());
                                        osp.setUpdatedDate(gc.getTime());
                                        if (deviceDAO.saveObjectSettingPackages(osp, session)) {
                                            List<Country> countriesList = countryDAO.getAllCountries(session);
                                            for (Country c : countriesList) {
                                                ObjectSettingPackageTranslations osptrans = new ObjectSettingPackageTranslations();
                                                osptrans.setId(new ObjectSettingPackageTranslationsId(objectSettingPackageId, c.getCountryId()));
                                                osptrans.setPackageName(d.getObjectSettingPackages().getObjectSettingPackageTranslations().getPackageName());
                                                osptrans.setLastUpdatedByUserId(sessions2.getUserId());
                                                osptrans.setCreatedDate(gc.getTime());
                                                osptrans.setUpdatedDate(gc.getTime());
                                                deviceDAO.saveObjectSettingPackageTranslations(osptrans, session);
                                            }
                                        }
                                        for (se.info24.devicejaxbPost.DeviceSetting dse : dseList) {
                                            if (dse.getObjectSettingTemplates() != null) {
                                                if (dse.getObjectSettingTemplates().getSettingDataType() != null) {

                                                    if (dse.getObjectSettingTemplates().getObjectSettingTemplateID() != null) {
                                                        if (dse.getObjectSettingTemplates() != null) {

                                                            osts = deviceDAO.getObjectSettingTemplatesByTemplateId(dse.getObjectSettingTemplates().getObjectSettingTemplateID(), session);
                                                            if (osts != null) {
                                                                objectSettingTemplateId = dse.getObjectSettingTemplates().getObjectSettingTemplateID();
                                                                deviceDAO.insertObjectSettingTemplates(d.getDeviceID(), objectSettingTemplateId, dse.getDeviceSettingID(), session, sessions2);
                                                            }
                                                        } else {
                                                            osts = deviceDAO.getObjectSettingTemplatesByName(dse.getDeviceSettingName(), session);
                                                            if (osts != null) {
                                                                objectSettingTemplateId = osts.getObjectSettingTemplateId();
                                                                deviceDAO.insertObjectSettingTemplates(d.getDeviceID(), objectSettingTemplateId, dse.getDeviceSettingID(), session, sessions2);
                                                            } else {
                                                                ObjectSettingTemplates ost = new ObjectSettingTemplates();
                                                                objectSettingTemplateId = UUID.randomUUID().toString();
                                                                ost.setObjectSettingTemplateId(objectSettingTemplateId);
                                                                ost.setObjectSettingTemplateParentId(null);
                                                                ost.setObjectSettingName(dse.getDeviceSettingName());
                                                                ost.setObjectSettingDefaultValue(dse.getDeviceSettingValue());
                                                                ost.setObjectSettingDescription(dse.getDeviceSettingName());
                                                                ost.setSettingRequired(0);
                                                                SettingDataType sdt = new SettingDataType();
                                                                sdt.setSettingDataTypeId(dse.getObjectSettingTemplates().getSettingDataType().get(0).getSettingDataTypeID());
                                                                ost.setSettingDataType(sdt);
                                                                ost.setLastUpdatedByUserId(sessions2.getUserId());
                                                                ost.setCreatedDate(gc.getTime());
                                                                ost.setUpdatedDate(gc.getTime());
                                                                deviceDAO.saveObjectSettingTemplates(ost, session);
                                                            }
                                                        }
                                                    } else {
                                                        osts = deviceDAO.getObjectSettingTemplatesByName(dse.getDeviceSettingName(), session);
                                                        if (osts != null) {
                                                            objectSettingTemplateId = osts.getObjectSettingTemplateId();
                                                            deviceDAO.insertObjectSettingTemplates(d.getDeviceID(), objectSettingTemplateId, dse.getDeviceSettingID(), session, sessions2);
                                                        } else {
                                                            ObjectSettingTemplates ost = new ObjectSettingTemplates();
                                                            objectSettingTemplateId = UUID.randomUUID().toString();
                                                            ost.setObjectSettingTemplateId(objectSettingTemplateId);
                                                            ost.setObjectSettingTemplateParentId(null);
                                                            ost.setObjectSettingName(dse.getDeviceSettingName());
                                                            ost.setObjectSettingDefaultValue(dse.getDeviceSettingValue());
                                                            ost.setObjectSettingDescription(dse.getDeviceSettingName());
                                                            ost.setSettingRequired(0);
                                                            SettingDataType sdt = new SettingDataType();
                                                            sdt.setSettingDataTypeId(dse.getObjectSettingTemplates().getSettingDataType().get(0).getSettingDataTypeID());
                                                            ost.setSettingDataType(sdt);
                                                            ost.setLastUpdatedByUserId(sessions2.getUserId());
                                                            ost.setCreatedDate(gc.getTime());
                                                            ost.setUpdatedDate(gc.getTime());
                                                            deviceDAO.saveObjectSettingTemplates(ost, session);
                                                        }
                                                    }
                                                    ObjectTypeSettingTemplates otst = new ObjectTypeSettingTemplates();
                                                    otst.setId(new ObjectTypeSettingTemplatesId(objectSettingTemplateId, device.getDeviceTypes().getDeviceTypeId()));
                                                    otst.setLastUpdatedByUserId(sessions2.getUserId());
                                                    otst.setCreatedDate(gc.getTime());
                                                    otst.setUpdatedDate(gc.getTime());
                                                    if (deviceDAO.saveObjectTypeSettingTemplates(otst, session)) {
                                                        ObjectSettingPackageTemplates ospt = new ObjectSettingPackageTemplates();
                                                        ospt.setId(new ObjectSettingPackageTemplatesId(objectSettingPackageId, objectSettingTemplateId));
                                                        ospt.setObjectSettingDefaultValue(dse.getDeviceSettingValue());
                                                        ospt.setLastUpdatedByUserId(sessions2.getUserId());
                                                        ospt.setCreatedDate(gc.getTime());
                                                        ospt.setUpdatedDate(gc.getTime());
                                                        deviceDAO.saveObjectSettingPackageTemplates(ospt, session);
                                                    }

                                                } else {
                                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                                    tingcoDevice.getMsgStatus().setResponseText("SettingDataTypeId is required");
                                                    return tingcoDevice;
                                                }
                                            } else {
                                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                                tingcoDevice.getMsgStatus().setResponseText("SettingDataTypeId is required");
                                                return tingcoDevice;
                                            }
                                        }
                                        GroupObjectSettingPackages gosp = null;
                                        Users2 users2 = userDAO.getUserById(sessions2.getUserId(), session);
                                        String groupId = d.getGroupID().getValue();
                                        if (d.getGroupID().getValue().equalsIgnoreCase(users2.getGroupId())) {
                                            gosp = new GroupObjectSettingPackages();
                                            gosp.setId(new GroupObjectSettingPackagesId(groupId, objectSettingPackageId));
                                            gosp.setLastUpdatedByUserId(sessions2.getUserId());
                                            gosp.setCreatedDate(gc.getTime());
                                            gosp.setUpdatedDate(gc.getTime());
                                            deviceDAO.saveGroupObjectSettingPackages(gosp, session);
                                        } else {
                                            for (int i = 0; i < 2; i++) {
                                                gosp = new GroupObjectSettingPackages();
                                                gosp.setId(new GroupObjectSettingPackagesId(groupId, objectSettingPackageId));
                                                gosp.setLastUpdatedByUserId(sessions2.getUserId());
                                                gosp.setCreatedDate(gc.getTime());
                                                gosp.setUpdatedDate(gc.getTime());
                                                deviceDAO.saveGroupObjectSettingPackages(gosp, session);
                                                groupId = users2.getGroupId();
                                            }
                                        }
                                        tx.commit();
                                    } else {
                                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                                        tingcoDevice.getMsgStatus().setResponseText("No DeviceSetting found in XML");
                                        return tingcoDevice;
                                    }
                                } else {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("DeviceSettings not Found in XML");
                                    return tingcoDevice;
                                }
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Device found with the given DeviceId in Device Table");
                                return tingcoDevice;
                            }
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("No Device Found in XML");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Device tag is not found in XML");
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
            tx.rollback();
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

    private TingcoDevice copyDeviceSettings(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        TingcoDevice tingcoDevice = null;
        Transaction tx = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    boolean result = true;
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    se.info24.devicejaxbPost.TingcoDevice tingcoDevices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    if (!tingcoDevices.getDevices().getDevice().isEmpty()) {
                        se.info24.devicejaxbPost.Device d = tingcoDevices.getDevices().getDevice().get(0);
                        se.info24.pojo.Device device = deviceDAO.getDeviceById(d.getDeviceID(), session);
                        if (d != null) {
                            if (device != null) {
                                if (d.getDeviceSettings() != null) {
                                    List<se.info24.devicejaxbPost.DeviceSetting> dseList = d.getDeviceSettings().getDeviceSetting();
                                    if (!dseList.isEmpty()) {
                                        for (se.info24.devicejaxbPost.DeviceSetting dse : dseList) {
                                            DeviceSettings ds = deviceDAO.getDeviceSettingsBySettingId(dse.getDeviceSettingID(), session);
                                            DeviceSettings dss = deviceDAO.getDeviceSettingsByIds(d.getDeviceID(), dse.getDeviceSettingName(), session);
                                            if (dss != null) {
                                                dss.setDeviceSettingValue(dse.getDeviceSettingValue());
                                                dss.setActiveFromDate(gc.getTime());
                                                dss.setUserId(sessions2.getUserId());
                                                dss.setUpdatedDate(gc.getTime());
                                                if (dse.getObjectSettingTemplates() != null) {
                                                    dss.setObjectSettingTemplateId(dse.getObjectSettingTemplates().getObjectSettingTemplateID());
                                                }
                                                session.saveOrUpdate(dss);
                                            } else {
                                                dss = new DeviceSettings();
                                                dss.setDeviceSettingId(UUID.randomUUID().toString());
//                                                dss.setDeviceSettingParentID(null);
                                                se.info24.pojo.Device de = new se.info24.pojo.Device();
                                                de.setDeviceId(d.getDeviceID());
                                                dss.setDevice(de);
                                                dss.setDeviceSettingName(dse.getDeviceSettingName());
                                                dss.setDeviceSettingValue(dse.getDeviceSettingValue());
                                                if (dse.getObjectSettingTemplates() != null) {
                                                    if (!dse.getObjectSettingTemplates().getSettingDataType().isEmpty()) {
                                                        SettingDataType sdt = new SettingDataType();
                                                        sdt.setSettingDataTypeId(dse.getObjectSettingTemplates().getSettingDataType().get(0).getSettingDataTypeID());
                                                        dss.setSettingDataType(sdt);
                                                    }
                                                }
                                                dss.setActiveFromDate(gc.getTime());
                                                dss.setUserId(sessions2.getUserId());
                                                dss.setCreatedDate(gc.getTime());
                                                dss.setUpdatedDate(gc.getTime());
                                                if (dse.getObjectSettingTemplates() != null) {
                                                    dss.setObjectSettingTemplateId(dse.getObjectSettingTemplates().getObjectSettingTemplateID());
                                                }
                                                session.saveOrUpdate(dss);
                                                if (!deviceDAO.copyDeviceSettings(dss, ds, 1, session, sessions2)) {
                                                    result = false;
                                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                                    tingcoDevice.getMsgStatus().setResponseText("Error occured while inserting DeviceSettings");
                                                    return tingcoDevice;
                                                }
                                            }
                                        }
                                        if (result) {
                                            tx.commit();
                                        }
                                    } else {
                                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                                        tingcoDevice.getMsgStatus().setResponseText("No DeviceSetting found in XML");
                                        return tingcoDevice;
                                    }
                                } else {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("DeviceSettings not Found in XML");
                                    return tingcoDevice;
                                }
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Device found with the given DeviceId in Device Table");
                                return tingcoDevice;
                            }
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("No Device Found in XML");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Device tag is not found in XML");
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
            tx.rollback();
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

    private TingcoDevice createDeviceWizard(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        Transaction tx = null;
        boolean result = true;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.ADD);
                if (hasPermission) {
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    if (devices.getDevices().getDevice().size() > 0) {
                        Device deviceXML = devices.getDevices().getDevice().get(0);
                        if (deviceXML != null) {
                            se.info24.pojo.Device dev = new se.info24.pojo.Device();
                            String groupId = deviceXML.getGroupID().getValue();
                            GroupLimits groupLimits = groupDAO.getGroupLimitsByGroupId(groupId, session);

                            List<se.info24.pojo.Device> devicecheck = deviceDAO.getDeviceByGroupIdNDeviceName2(groupId, deviceXML.getDeviceName2(), session);

                            if (!devicecheck.isEmpty()) {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("UnitID is not Available.");
                                return tingcoDevice;
                            }

                            se.info24.pojo.Device device = deviceDAO.getDeviceById(deviceXML.getDeviceID(), session);
                            Addresses addtemp = device.getAddresses();
                            if (addtemp != null) {
                                Addresses add = new Addresses();
                                add.setAddressId(UUID.randomUUID().toString());
                                if (addtemp.getAddressName() != null) {
                                    add.setAddressName(addtemp.getAddressName());
                                }
                                if (addtemp.getAddressDescription() != null) {
                                    add.setAddressDescription(addtemp.getAddressDescription());
                                }
                                add.setAddressType(addtemp.getAddressType());
                                if (addtemp.getAddressRegion() != null) {
                                    add.setAddressRegion(addtemp.getAddressRegion());
                                }
                                if (addtemp.getAddressStreet() != null) {
                                    add.setAddressStreet(addtemp.getAddressStreet());
                                }
                                if (addtemp.getAddressStreet2() != null) {
                                    add.setAddressStreet2(addtemp.getAddressStreet2());
                                }
                                if (addtemp.getAddressSuite() != null) {
                                    add.setAddressSuite(addtemp.getAddressSuite());
                                }
                                if (addtemp.getAddressZip() != null) {
                                    add.setAddressZip(addtemp.getAddressZip());
                                }
                                if (addtemp.getAddressCity() != null) {
                                    add.setAddressCity(addtemp.getAddressCity());
                                }

                                if (addtemp.getCountryStateId() != null) {
                                    add.setCountryStateId(addtemp.getCountryStateId());
                                }
                                if (addtemp.getAddressPhone() != null) {
                                    add.setAddressPhone(addtemp.getAddressPhone());
                                }
                                if (addtemp.getAddressMobile() != null) {
                                    add.setAddressMobile(addtemp.getAddressMobile());
                                }

                                if (addtemp.getAddressFax() != null) {
                                    add.setAddressFax(String.valueOf(addtemp.getAddressFax()));
                                }
                                if (addtemp.getAddressEmail() != null) {
                                    add.setAddressEmail(addtemp.getAddressEmail());
                                }
                                if (addtemp.getAddressWeb() != null) {
                                    add.setAddressWeb(addtemp.getAddressWeb());
                                }

                                if (addtemp.getAddressIm() != null) {
                                    add.setAddressIm(addtemp.getAddressIm());
                                }
                                if (addtemp.getAddressLatitude() != null) {
                                    add.setAddressLatitude(addtemp.getAddressLatitude());
                                }

                                if (addtemp.getAddressLongitude() != null) {
                                    add.setAddressLongitude(addtemp.getAddressLongitude());
                                }

                                if (addtemp.getAddressDepth() != null) {
                                    add.setAddressDepth(addtemp.getAddressDepth());
                                }
                                add.setCountry(addtemp.getCountry());
                                add.setUserId(sessions2.getUserId());
                                add.setCreatedDate(gc.getTime());
                                add.setUpdatedDate(gc.getTime());

                                session.saveOrUpdate(add);
                                session.flush();
                                session.clear();

                                dev.setAddresses(add);
                            }

                            if (groupLimits != null) {
                                if (groupLimits.getCurrentNumberOfDevices() < groupLimits.getMaxNumberOfDevices()) {
                                    String deviceId = UUID.randomUUID().toString();
                                    dev.setDeviceId(deviceId);
                                    if (deviceXML.getDeviceName() != null) {
                                        dev.setDeviceName(deviceXML.getDeviceName());
                                    }
                                    if (deviceXML.getDeviceName2() != null) {
                                        dev.setDeviceName2(deviceXML.getDeviceName2());
                                    }
                                    if (deviceXML.getDeviceName() != null) {
                                        dev.setDeviceDescription(deviceXML.getDeviceName());
                                    }
                                    if (deviceXML.getGroupID() != null) {
                                        dev.setGroups(new Groups(deviceXML.getGroupID().getValue()));
                                    }
                                    //Check DeviceName2 and GroupId exists in DB.
                                    List<se.info24.pojo.Device> list = deviceDAO.getDeviceByGroupIdNDeviceName2(groupId, deviceXML.getDeviceName2(), session);
                                    if (!list.isEmpty()) {
                                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                                        tingcoDevice.getMsgStatus().setResponseText("You cannot have two Devices with the same Unit ID in the same Organization");
                                        return tingcoDevice;
                                    }
                                    dev.setDeviceTypes(new DeviceTypes(device.getDeviceTypes().getDeviceTypeId()));
                                    if (device.getAgreements() != null) {
                                        dev.setAgreements(new Agreements(device.getAgreements().getAgreementId()));
                                    }

                                    Company company = new Company();
                                    company.setCompanyId("830AA198-0BEA-4EB1-B758-45D69647E417"); //Hardcoded as per feature backlog id 389
                                    dev.setCompany(company);
                                    dev.setDeviceEnabled(1);
                                    dev.setInvoiceDevice(1);
                                    dev.setActiveFromDate(gc.getTime());
                                    dev.setInstalledDate(gc.getTime());
                                    dev.setUserId(sessions2.getUserId());
                                    dev.setCreatedDate(gc.getTime());
                                    dev.setUpdatedDate(gc.getTime());
                                    session.saveOrUpdate(dev);
                                    session.flush();
                                    session.clear();
//                                    if (deviceXML.getBillingCategories() != null && !deviceXML.getBillingCategories().isEmpty()) {
//                                        if (deviceXML.getBillingCategories().get(0).getBillingCategoryID() != null) {
                                    List<DeviceBillingCategories> deviceBillingCategoriesTemp = deviceDAO.getDeviceBillingCategories(session, device.getDeviceId());
                                    if (!deviceBillingCategoriesTemp.isEmpty()) {
                                        DeviceBillingCategories deviceBillingCategories = new DeviceBillingCategories();
                                        deviceBillingCategories.setDeviceId(deviceId);
//                                        List<BillingCategories> billingCategorieses = deviceDAO.getBillingCategorieses(session, deviceBillingCategoriesTemp.get(0).getBillingCategories());
                                        if (deviceBillingCategoriesTemp.get(0).getBillingCategories() == null) {
                                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                                            tingcoDevice.getMsgStatus().setResponseText("Not valid BillingCategories");
                                            return tingcoDevice;
                                        }
                                        deviceBillingCategories.setBillingCategories(deviceBillingCategoriesTemp.get(0).getBillingCategories());
                                        deviceBillingCategories.setLastUpdatedByUserId(sessions2.getUserId());
                                        deviceBillingCategories.setUpdatedDate(gc.getTime());
                                        deviceBillingCategories.setCreatedDate(gc.getTime());
                                        session.saveOrUpdate(deviceBillingCategories);
                                        session.flush();
                                        session.clear();
                                    }
//                                    }
                                    if (!deviceDAO.addDeviceWizard(deviceId, device, session, deviceXML, sessions2.getUserId(), gc)) {
                                        result = false;
                                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                                        tingcoDevice.getMsgStatus().setResponseText("Error Occured while Saving Device");
                                        return tingcoDevice;
                                    }
                                    if (result) {
                                        tx.commit();
                                    }
                                    tingcoDevice = tingcoDeviceXML.buildDeviceID(tingcoDevice, deviceId.toUpperCase());
                                    return tingcoDevice;
                                } else {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("Maximum number of Devices reached for this group. Adding new device is not permitted");
                                    return tingcoDevice;
                                }
                            } else {
                                String deviceId = UUID.randomUUID().toString();
                                dev.setDeviceId(deviceId);
                                if (deviceXML.getDeviceName() != null) {
                                    dev.setDeviceName(deviceXML.getDeviceName());
                                }
                                if (deviceXML.getDeviceName2() != null) {
                                    dev.setDeviceName2(deviceXML.getDeviceName2());
                                }
                                if (deviceXML.getDeviceName() != null) {
                                    dev.setDeviceDescription(deviceXML.getDeviceName());
                                }
                                if (deviceXML.getGroupID() != null) {
                                    dev.setGroups(new Groups(deviceXML.getGroupID().getValue()));
                                }
                                //Check DeviceName2 and GroupId exists in DB.
                                List<se.info24.pojo.Device> list = deviceDAO.getDeviceByGroupIdNDeviceName2(groupId, deviceXML.getDeviceName2(), session);
                                if (!list.isEmpty()) {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("You cannot have two Devices with the same Unit ID in the same Organization");
                                    return tingcoDevice;
                                }
                                dev.setDeviceTypes(new DeviceTypes(device.getDeviceTypes().getDeviceTypeId()));
                                if (device.getAgreements() != null) {
                                    dev.setAgreements(new Agreements(device.getAgreements().getAgreementId()));
                                }

                                Company company = new Company();
                                company.setCompanyId("830AA198-0BEA-4EB1-B758-45D69647E417"); //Hardcoded as per feature backlog id 389
                                dev.setCompany(company);
                                dev.setDeviceEnabled(1);
                                dev.setInvoiceDevice(1);
                                dev.setActiveFromDate(gc.getTime());
                                dev.setInstalledDate(gc.getTime());
                                dev.setUserId(sessions2.getUserId());
                                dev.setCreatedDate(gc.getTime());
                                dev.setUpdatedDate(gc.getTime());
                                session.saveOrUpdate(dev);
                                session.flush();
                                session.clear();
                                if (deviceXML.getBillingCategories() != null && !deviceXML.getBillingCategories().isEmpty()) {
                                    if (deviceXML.getBillingCategories().get(0).getBillingCategoryID() != null) {
                                        DeviceBillingCategories deviceBillingCategories = new DeviceBillingCategories();
                                        deviceBillingCategories.setDeviceId(deviceId);
                                        List<BillingCategories> billingCategorieses = deviceDAO.getBillingCategorieses(session, deviceXML.getBillingCategories().get(0).getBillingCategoryID());
                                        if (billingCategorieses.isEmpty()) {
                                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                                            tingcoDevice.getMsgStatus().setResponseText("Not valid BillingCategories");
                                            return tingcoDevice;
                                        }
                                        deviceBillingCategories.setBillingCategories(billingCategorieses.get(0));
                                        deviceBillingCategories.setLastUpdatedByUserId(sessions2.getUserId());
                                        deviceBillingCategories.setUpdatedDate(gc.getTime());
                                        deviceBillingCategories.setCreatedDate(gc.getTime());
                                        session.saveOrUpdate(deviceBillingCategories);
                                        session.flush();
                                        session.clear();
                                    }
                                }

                                if (!deviceDAO.addDeviceWizard(deviceId, device, session, deviceXML, sessions2.getUserId(), gc)) {
                                    result = false;
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("Error Occured while Saving Device");
                                    return tingcoDevice;
                                }
                                if (result) {
                                    tx.commit();
                                }
                                tingcoDevice = tingcoDeviceXML.buildDeviceID(tingcoDevice, deviceId.toUpperCase());
                                return tingcoDevice;
                            }
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Invalid Device XML Found");
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
            tx.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice deleteDevicePending(String deviceId) throws DatatypeConfigurationException {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
        if (request.getAttribute("USERSESSION") != null) {
            if (deviceId == null) {
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("DeviceId should not be empty");
                return tingcoDevice;
            }

            UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
            hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.DELETE);
            GregorianCalendar gc = new GregorianCalendar();
            if (hasPermission) {
                Session session = HibernateUtil.getSessionFactory().openSession();
                try {
                    se.info24.pojo.Device device = deviceDAO.getDeviceById(deviceId, session);
                    if (!deviceDAO.deleteDevicePending(session, deviceId, sessions2.getUserId())) {
                        TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Delete Device Pending", device.getDeviceName2(), "DevicePendingDelete", "Failed");
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Error Occured while Deleting DevicePendingDelete");
                    } else {
                        TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Delete Device Pending", device.getDeviceName2(), "DevicePendingDelete", "Success");
                        /**
                         * feature ID :- 773
                         *  then also set/update the Device operations status in the DeviceOperationsMember table.
                         * If there is already a row for this device then update the DeviceOperationsStatusID to "51DAA631-FD49-446E-A293-575365A68FE0" or
                         * if there is no row for the device then insert a new row for this device and
                         * set the DeviceOperationsStatusID to "51DAA631-FD49-446E-A293-575365A68FE0" for this device.
                         */
                        List<DeviceOperationsMember> domList = deviceDAO.getdeviceOperationsMember(session, deviceId);
                        if (!domList.isEmpty()) {
                            DeviceOperationsMember dom = domList.get(0);
                            dom.setDeviceOperationsStatus(new DeviceOperationsStatus("51DAA631-FD49-446E-A293-575365A68FE0"));
                            dom.setUpdatedDate(gc.getTime());
                            if (!deviceDAO.insertDeviceOperationsMember(dom, session)) {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("Error Occured while Updating DeviceOperationsMember");
                                return tingcoDevice;
                            }
                        } else {
                            DeviceOperationsMember dom = new DeviceOperationsMember();
                            dom.setDeviceOperationsMemberId(UUID.randomUUID().toString());
                            dom.setDevice(device);
                            dom.setDeviceOperationsStatus(new DeviceOperationsStatus("51DAA631-FD49-446E-A293-575365A68FE0"));
                            dom.setActiveFromDate(gc.getTime());
                            dom.setUserId(sessions2.getUserId());
                            dom.setCreatedDate(gc.getTime());
                            dom.setUpdatedDate(gc.getTime());
                            if (!deviceDAO.insertDeviceOperationsMember(dom, session)) {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("Error Occured while creating DeviceOperationsMember");
                                return tingcoDevice;
                            }
                        }
                    }
                } catch (Exception e) {
                    TCMUtil.exceptionLog(getClass().getName(), e);
                } finally {
                    if (session != null) {
                        session.close();
                    }
                    delayLog(requestedTime);
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
    }

    private TingcoDevice deleteDeviceSettingsbyDeviceId(String deviceId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("deviceId should not be empty");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.DELETE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<DeviceSettings> deviceSettingsList = deviceDAO.getDeviceSettingsbyDeviceId(deviceId, session);
                    if (!deviceSettingsList.isEmpty()) {
                        if (!deviceDAO.deleteDeviceSettingsList(deviceSettingsList, session)) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error Occured while deleting the DeviceSettings");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No DeviceSettings found for the given deviceId");
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
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice deleteDeviceSettingsbyIds(String deviceId, String deviceSettingId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Transaction tx = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("deviceId  should not be empty");
                    return tingcoDevice;
                }
                if (deviceSettingId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("deviceSettingId should not be empty");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.DELETE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    DeviceSettings devSettings = deviceDAO.getDeviceSettings(deviceId, deviceSettingId, session);
                    if (devSettings != null) {
                        if (deviceDAO.deleteDeviceSetting(devSettings, session)) {
                            tx.commit();
                        } else {
                            tx.rollback();
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error Occured while Deleting DeviceSettings");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Found in DeviceSettings");
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
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice deleteServiceDeviceSetting(String serviceDeviceSettingId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (serviceDeviceSettingId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("ServiceDeviceSettingId should not be empty");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.DELETE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ServiceDeviceSettings serviceDevicesettings = deviceDAO.getServiceDeviceSettings(serviceDeviceSettingId, session);
                    if (serviceDevicesettings != null) {
                        if (!deviceDAO.deleteServiceDeviceSetting(serviceDevicesettings, session)) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error Occured while Deleting ServiceDeviceSetting");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Found in ServiceDeviceSetting");
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
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice deleteServiceDeviceSubscription(String serviceDeviceSubscriptionId) throws DatatypeConfigurationException {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoDevice tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
        try {
            if (serviceDeviceSubscriptionId.equals("")) {
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("ServiceDeviceSubscriptionId  should not be empty");
                return tingcoDevice;
            }
            session = HibernateUtil.getSessionFactory().openSession();
            ServiceDeviceSubscriptions sds = deviceDAO.getServiceDeviceSubscriptions(serviceDeviceSubscriptionId, session);
            if (sds != null) {
                if (!deviceDAO.deleteServiceDeviceSubscription(sds, session)) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("Error Occured while Deleting ServiceDeviceSubscription");
                    return tingcoDevice;
                }
            } else {
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("NoData Found in ServiceDeviceSubscriptions");
                return tingcoDevice;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
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

    private TingcoDevice deviceSettingInfo(String deviceId, String deviceSettingId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        TingcoDevice tingcoDevice = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceId should not be empty");
                    return tingcoDevice;
                }
                if (deviceSettingId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceSettingId should not be empty");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    DeviceSettings ds = deviceDAO.getDeviceSettings(deviceId, deviceSettingId, session);
                    if (ds != null) {
                        ObjectSettingTemplates ost = new ObjectSettingTemplates();
                        if (ds.getObjectSettingTemplateId() != null) {
                            ost = deviceDAO.getObjectSettingTemplates(ds.getObjectSettingTemplateId(), session);
                        }
                        Users2 us = userDAO.getUsers2ById(sessions2.getUserId(), session).get(0);
                        tingcoDevice = tingcoDeviceXML.buildDeviceSettingInfo(tingcoDevice, ds, us, ost);
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data found in DeviceSettings");
                        return tingcoDevice;
                    }
                }
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

    private TingcoDevice editDeviceSettings(String deviceId, String deviceSettingId, String settingValue) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        TingcoDevice tingcoDevice = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceId should not be empty");
                    return tingcoDevice;
                }
                if (deviceSettingId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceSettingId should not be empty");
                    return tingcoDevice;
                }
                if (settingValue.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("SettingValue should not be empty");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.UPDATE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    DeviceSettings ds = deviceDAO.getDeviceSettings(deviceId, deviceSettingId, session);
                    if (ds != null) {
                        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                        settingValue = TCMUtil.convertHexToString(settingValue);
                        ds.setDeviceSettingValue(settingValue);
                        ds.setActiveFromDate(gc.getTime());
                        ds.setUpdatedDate(gc.getTime());
                        ds.setUserId(sessions2.getUserId());
                        if (!deviceDAO.saveDeviceSettings(ds, session)) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error occured while updating DeviceSettingvalue");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data found in DeviceSettings");
                        return tingcoDevice;
                    }
                }
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

    private TingcoDevice getDataItemsForDevice(String deviceId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceId should not be empty");
                    return tingcoDevice;
                }
                if (countryId == 0 || countryId < 0) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("CountryId should not be empty");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    se.info24.pojo.Device device = deviceDAO.getDeviceById(deviceId, session);
                    if (device != null) {
                        List<DeviceDataitemTranslations> ddiTransList = deviceDAO.getDeviceDataItemTranslations(device.getDeviceTypes().getDeviceTypeId(), countryId, session);
                        if (!ddiTransList.isEmpty()) {
                            tingcoDevice = tingcoDeviceXML.buildDeviceDataItemTranslations(tingcoDevice, ddiTransList);
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("No Data found in DeviceDataItemTranslations");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data found in Device");
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

    private TingcoDevice getDeviceDataItemInformation(String deviceId, String dataItemId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        TingcoDevice tingcoDevice = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceId should not be empty");
                    return tingcoDevice;
                }
                if (dataItemId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DataItemId should not be empty");
                    return tingcoDevice;
                }
                if (countryId == 0) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("CountryId should not be 0");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    DeviceDataItemScaling ddis = deviceDAO.getDeviceDataItemScaling(deviceId, dataItemId, session);
                    DataItemTranslationsPerDevice ditpd = deviceDAO.getDataItemTranslationsPerDevice(deviceId, dataItemId, countryId, session);
                    tingcoDevice = tingcoDeviceXML.buildGetDeviceDataItemInformation(ddis, ditpd, deviceId, dataItemId, countryId, session);
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

    private TingcoDevice getDeviceDataItemTranslations(String deviceId, String dataItemId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        CountryDAO countryDAO = new CountryDAO();
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceId should not be empty");
                    return tingcoDevice;
                }
                if (dataItemId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DataItemId should not be empty");
                    return tingcoDevice;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<DataItemTranslationsPerDevice> ditpList = deviceDAO.getDataItemTranslationsPerDeviceById(deviceId, dataItemId, session);
                    List<Country> countryList = countryDAO.getCountryByLanguage(session);
                    tingcoDevice = tingcoDeviceXML.buildDataItemTranslationsPerDevice(tingcoDevice, ditpList, countryList);
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

    private TingcoDevice getDeviceOperationsStatus(int countryId) throws DatatypeConfigurationException {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
        if (request.getAttribute("USERSESSION") != null) {
            if (countryId <= 0) {
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("countryId should not be empty");
                return tingcoDevice;
            }

            UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
            hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
            if (hasPermission) {
                Session session = HibernateUtil.getSessionFactory().openSession();
                try {
                    List<DeviceOperationsStatusTranslation> dostList = deviceDAO.getDeviceOperationsStatusTrans(session, countryId);
                    if (!dostList.isEmpty()) {
                        tingcoDevice = tingcoDeviceXML.buildDeviceOperationsStatusTrans(tingcoDevice, dostList);
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                    }
                } catch (Exception e) {
                    TCMUtil.exceptionLog(getClass().getName(), e);
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("Error occured");
                } finally {
                    if (session != null) {
                        session.close();
                    }
                    delayLog(requestedTime);
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
    }

    private TingcoDevice getDeviceSalesDetails(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session ismOperationsSession = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.REPORTS, TCMUtil.READ);
                if (hasPermission) {
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    ismOperationsSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    session = HibernateUtil.getSessionFactory().openSession();
                    if (devices.getDevices().getDevice().size() > 0) {
                        Device deviceXML = devices.getDevices().getDevice().get(0);
                        if (deviceXML != null) {
                            List<Object> transactionResultList = deviceDAO.getTransactionResultForDeviceSalesDetails(deviceXML, tingcoDevice, ismOperationsSession, session);
                            if (!transactionResultList.isEmpty()) {
                                tingcoDevice = tingcoDeviceXML.buildTransactionResultForDeviceSalesDetails(tingcoDevice, transactionResultList, deviceXML.getCountryID().getValue(), session);
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Sales details found for the given input");
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
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getSalesbyProductVariant(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        Session ismOperationsSession = null;
        TingcoDevice tingcoDevice = null;
//        System.gc();
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.REPORTS, TCMUtil.READ);
                if (hasPermission) {
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    ismOperationsSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        List<se.info24.devicejaxbPost.Device> deviceList = devices.getDevices().getDevice();
                        if (!deviceList.isEmpty()) {
                            se.info24.devicejaxbPost.Device device = deviceList.get(0);
                            SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                            gc.setTime(lFormat.parse(device.getEventDatas().getFromDate()));
                            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                            Date d = gc.getTime();
                            String gc_start_time = lFormat.format(gc.getTime());
                            gc.setTime(lFormat.parse(device.getEventDatas().getToDate()));
                            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                            String gc_stop_time = lFormat.format(gc.getTime());
                            String groupname = null;
                            Users2 users = userDAO.getUserById(sessions2.getUserId(), session);
                            List<String> groupidset = deviceDAO.getTrustedGroup(session, users.getGroupId());
                            List<GroupTranslations> gtList = new ArrayList<GroupTranslations>();
                            if (device.getGroupID() != null) {
                                groupname = device.getGroupID().getGroupName();
                                List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                for (List<String> list : splitList) {
                                    List<GroupTranslations> gtListTemp = deviceDAO.getGroupTransSearchByGroupName(session, list, groupname, device.getCountryID().getValue());
                                    if (gtListTemp != null) {
                                        gtList.addAll(gtListTemp);
                                    }
                                }
                                groupidset.clear();
                                for (GroupTranslations groupTranslations : gtList) {
                                    groupidset.add(groupTranslations.getId().getGroupId());
                                }
                            }
                            if (groupidset.isEmpty()) {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("GroupID Not Found");
                                return tingcoDevice;
                            }
                            Set<String> deviceid = new HashSet<String>();
                            if (device.getObjectGroupName() != null) {
                                List<ObjectGroupTranslations> objectGroupTranslationsList = deviceDAO.getObjectGroupTranslationsByObjectGroupNames(session, device.getObjectGroupName());
                                Set<String> objectGroupId = new HashSet<String>();
                                for (ObjectGroupTranslations objectGroupTranslations : objectGroupTranslationsList) {
                                    objectGroupId.add(objectGroupTranslations.getId().getObjectGroupId());
                                }
                                if (!objectGroupId.isEmpty()) {
                                    List<ObjectGroups> ObjectGroupsList = new ArrayList<ObjectGroups>();
                                    List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                    for (List<String> list : splitList) {
                                        List<ObjectGroups> ObjectGroupsListTemp = deviceDAO.getObjectGroupsByGroupIdsandObjectGroupIds(session, objectGroupId, list);
                                        if (ObjectGroupsListTemp != null) {
                                            ObjectGroupsList.addAll(ObjectGroupsListTemp);
                                        }
                                    }
                                    objectGroupId.clear();
                                    for (ObjectGroups objectGroup : ObjectGroupsList) {
                                        objectGroupId.add(objectGroup.getObjectGroupId());
                                    }
                                    List<Ogmdevice> ogmdeviceList = deviceDAO.getAllOgmdeviceById(session, objectGroupId);
                                    for (Ogmdevice ogmdevice : ogmdeviceList) {
                                        deviceid.add(ogmdevice.getId().getDeviceId());
                                    }
                                }
                                if (deviceid.isEmpty()) {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("DeviceID Not Found");
                                    return tingcoDevice;
                                }
                            }
                            List<se.info24.ismOperationsPojo.TransactionResult> transactionResultList = new ArrayList<se.info24.ismOperationsPojo.TransactionResult>();
                            if (device.getObjectGroupName() != null && groupname == null && device.getDeviceName() == null) {
                                transactionResultList = deviceDAO.getTransactionResultBySearchStringObjectGroupName(ismOperationsSession, deviceid, gc_start_time, gc_stop_time);
                            } else if (device.getObjectGroupName() == null && groupname != null && device.getDeviceName() == null) {
                                List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                for (List<String> list : splitList) {
                                    List<TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringGroup(ismOperationsSession, list, gc_start_time, gc_stop_time);
                                    if (transactionResultListTemp != null) {
                                        transactionResultList.addAll(transactionResultListTemp);
                                    }
                                }

                            } else if (device.getObjectGroupName() == null && groupname == null && device.getDeviceName() != null) {

                                List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                for (List<String> list : splitList) {
                                    List<TransactionResult> transactionResultListTemp = new ArrayList<TransactionResult>();
                                    if (TCMUtil.isValidUUID(device.getDeviceName())) {
                                        List<String> devicesID = new ArrayList<String>();
                                        devicesID.add(device.getDeviceName());
                                        transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringDevice(ismOperationsSession, devicesID, gc_start_time, gc_stop_time, list);
                                    } else {
                                        transactionResultListTemp = deviceDAO.getTransactionResultBySearchDeviceName(ismOperationsSession, device.getDeviceName(), gc_start_time, gc_stop_time, list);
                                    }
                                    if (transactionResultListTemp != null) {
                                        transactionResultList.addAll(transactionResultListTemp);
                                    }
                                }

                            } else if (device.getObjectGroupName() != null && groupname != null && device.getDeviceName() == null) {
                                List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                for (List<String> list : splitList) {
                                    List<TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringDevice(ismOperationsSession, new ArrayList<String>(deviceid), gc_start_time, gc_stop_time, list);
                                    if (transactionResultListTemp != null) {
                                        transactionResultList.addAll(transactionResultListTemp);
                                    }
                                }
                            } else if (device.getObjectGroupName() == null && groupname != null && device.getDeviceName() != null) {
                                List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                for (List<String> list : splitList) {
                                    List<TransactionResult> transactionResultListTemp = new ArrayList<TransactionResult>();
                                    if (TCMUtil.isValidUUID(device.getDeviceName())) {
                                        List<String> devicesID = new ArrayList<String>();
                                        devicesID.add(device.getDeviceName());
                                        transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringDeviceIDGroupName(ismOperationsSession, device.getDeviceName(), list, gc_start_time, gc_stop_time);
                                    } else {
                                        transactionResultListTemp = deviceDAO.getTransactionResultBySearchDeviceName(ismOperationsSession, device.getDeviceName(), gc_start_time, gc_stop_time, list);
                                    }
                                    if (transactionResultListTemp != null) {
                                        transactionResultList.addAll(transactionResultListTemp);
                                    }
                                }

                            } else if (device.getObjectGroupName() != null && groupname == null && device.getDeviceName() != null) {
                                List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                for (List<String> list : splitList) {
                                    List<TransactionResult> transactionResultListTemp = new ArrayList<TransactionResult>();
                                    if (TCMUtil.isValidUUID(device.getDeviceName())) {
                                        List<String> devicesID = new ArrayList<String>();
                                        for (String dID : deviceid) {
                                            if (dID.equalsIgnoreCase(device.getDeviceName())) {
                                                devicesID.add(dID);
                                            }
                                        }
                                        if (devicesID.isEmpty()) {
                                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                                            tingcoDevice.getMsgStatus().setResponseText("DevicesIDPOST Not Found");
                                            return tingcoDevice;
                                        }
                                        transactionResultList = deviceDAO.getTransactionResultBySearchStringDevice(ismOperationsSession, devicesID, gc_start_time, gc_stop_time, list);
                                    } else {
                                        transactionResultList = deviceDAO.getTransactionResultBySearchDeviceNameID(ismOperationsSession, gc_start_time, gc_stop_time, device.getDeviceName(), deviceid, list);
                                    }
                                    if (transactionResultListTemp != null) {
                                        transactionResultList.addAll(transactionResultListTemp);
                                    }
                                }
                            } else if (device.getObjectGroupName() != null && groupname != null && device.getDeviceName() != null) {
                                List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                for (List<String> list : splitList) {
                                    List<TransactionResult> transactionResultListTemp = new ArrayList<TransactionResult>();
                                    if (TCMUtil.isValidUUID(device.getDeviceName())) {
                                        List<String> devicesID = new ArrayList<String>();
                                        for (String dID : deviceid) {
                                            if (dID.equalsIgnoreCase(device.getDeviceName())) {
                                                devicesID.add(dID);
                                            }
                                        }
                                        if (devicesID.isEmpty()) {
                                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                                            tingcoDevice.getMsgStatus().setResponseText("DevicesIDPOST Not Found");
                                            return tingcoDevice;
                                        }
                                        transactionResultList = deviceDAO.getTransactionResultBySearchStringDevice(ismOperationsSession, devicesID, gc_start_time, gc_stop_time, list);
                                    } else {
                                        transactionResultList = deviceDAO.getTransactionResultBySearchDeviceNameID(ismOperationsSession, gc_start_time, gc_stop_time, device.getDeviceName(), deviceid, list);
                                    }
                                    if (transactionResultListTemp != null) {
                                        transactionResultList.addAll(transactionResultListTemp);
                                    }
                                }


                            }
                            if (!transactionResultList.isEmpty()) {
                                Set<String> transactionId = new HashSet<String>();
                                List<TransactionProducts> transactionProducts = new ArrayList<TransactionProducts>();
                                Hashtable<String, Integer> ht1 = new Hashtable<String, Integer>();
                                for (se.info24.ismOperationsPojo.TransactionResult transactionResult : transactionResultList) {
                                    transactionId.add(transactionResult.getTransactionId());
                                    if (transactionId.size() > 2000) {
                                        List<TransactionProducts> transactionProducts1 = deviceDAO.getTransactionProductsByTransactionIds(ismOperationsSession, transactionId);
                                        transactionProducts.addAll(transactionProducts1);
                                        transactionId.clear();
                                    }
                                }
                                if (transactionId.size() < 2000) {
                                    List<TransactionProducts> transactionProducts1 = deviceDAO.getTransactionProductsByTransactionIds(ismOperationsSession, transactionId);
                                    transactionProducts.addAll(transactionProducts1);
                                    transactionId.clear();
                                }
//                                List<TransactionProducts> transactionProducts = deviceDAO.getTransactionProductsByTransactionIds(ismOperationsSession, transactionId);
                                for (TransactionProducts transactionProducts1 : transactionProducts) {
                                    if (!ht1.containsKey(transactionProducts1.getId().getProductVariantId())) {
                                        ht1.put(transactionProducts1.getId().getProductVariantId(), transactionProducts1.getQuantity());
                                    } else {
                                        ht1.put(transactionProducts1.getId().getProductVariantId(), ht1.get(transactionProducts1.getId().getProductVariantId()) + transactionProducts1.getQuantity());
                                    }
                                }
                                return tingcoDeviceXML.buildgetSalesbyProductVariant(tingcoDevice, ht1, transactionProducts);
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("transactionResultList Not Found");
                                return tingcoDevice;
                            }

                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("XML Data Not Found");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
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
//            System.gc();
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice GetUserSalesDetails(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        Session ismOperationsSession = null;
        TingcoDevice tingcoDevice = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.REPORTS, TCMUtil.READ);
                if (hasPermission) {
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    ismOperationsSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        List<se.info24.devicejaxbPost.Device> deviceList = devices.getDevices().getDevice();
                        if (!deviceList.isEmpty()) {
                            se.info24.devicejaxbPost.Device device = deviceList.get(0);
                            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                            SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                            gc.setTime(lFormat.parse(device.getEventDatas().getFromDate()));
                            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
//                            Date d = gc.getTime(); //not formatted due to malfunctioning in the date and time ie, showing -12 hours difference if we format that
                            String gc_start_time = lFormat.format(gc.getTime());
//                            System.out.println("date now is "+gc_start_time);

                            gc.setTime(lFormat.parse(device.getEventDatas().getToDate()));
                            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                            String gc_stop_time = lFormat.format(gc.getTime());

                            String groupname = null;
                            Users2 users = (Users2) session.getNamedQuery("getUsers2ById").setString("userID", sessions2.getUserId()).list().get(0);
                            List<String> groupidset = deviceDAO.getTrustedGroup(session, users.getGroupId());

                            List<GroupTranslations> gtList = new ArrayList<GroupTranslations>();
                            if (device.getGroupID() != null) {
                                groupname = device.getGroupID().getGroupName();
                                List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                for (List<String> list : splitList) {
                                    List<GroupTranslations> gtListTemp = deviceDAO.getGroupTransSearchByGroupName(session, list, groupname, device.getCountryID().getValue());
                                    gtList.addAll(gtListTemp);
                                }

                                groupidset.clear();
                                for (GroupTranslations groupTranslations : gtList) {
                                    groupidset.add(groupTranslations.getId().getGroupId());
                                }
                            }
                            List<se.info24.ismOperationsPojo.TransactionResult> transactionResultList = new ArrayList<se.info24.ismOperationsPojo.TransactionResult>();
                            if (device.getUserName() != null && groupname == null && device.getDeviceName() == null) {
                                List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                for (List<String> list : splitList) {
                                    List<se.info24.ismOperationsPojo.TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringUserGroupName(ismOperationsSession, device.getUserName(), list, gc_start_time, gc_stop_time);
                                    transactionResultList.addAll(transactionResultListTemp);
                                }
//                                transactionResultList = deviceDAO.getTransactionResultBySearchStringUserGroupName(ismOperationsSession, device.getUserName(), groupidset, gc_start_time, gc_stop_time);
                            } else if (device.getUserName() == null && groupname != null && device.getDeviceName() == null) {
                                if (!groupidset.isEmpty()) {
                                    List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                    for (List<String> list : splitList) {
                                        List<se.info24.ismOperationsPojo.TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringGroupName(ismOperationsSession, list, gc_start_time, gc_stop_time);
                                        transactionResultList.addAll(transactionResultListTemp);
                                    }

                                } else {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("GroupName Not Found");
                                    return tingcoDevice;
                                }
                            } else if (device.getUserName() == null && groupname == null && device.getDeviceName() != null) {
                                if (TCMUtil.isValidUUID(device.getDeviceName())) {
                                    List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                    for (List<String> list : splitList) {
                                        List<se.info24.ismOperationsPojo.TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringDeviceID(ismOperationsSession, device.getDeviceName(), gc_start_time, gc_stop_time, list);
                                        transactionResultList.addAll(transactionResultListTemp);
                                    }
//                                    transactionResultList = deviceDAO.getTransactionResultBySearchStringDeviceID(ismOperationsSession, device.getDeviceName(), gc_start_time, gc_stop_time, groupidset);
                                } else {
                                    List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                    for (List<String> list : splitList) {
                                        List<se.info24.ismOperationsPojo.TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringDeviceName(ismOperationsSession, device.getDeviceName(), gc_start_time, gc_stop_time, list);
                                        transactionResultList.addAll(transactionResultListTemp);
                                    }
//                                    transactionResultList = deviceDAO.getTransactionResultBySearchStringDeviceName(ismOperationsSession, device.getDeviceName(), gc_start_time, gc_stop_time, groupidset);
                                }
                            } else if (device.getUserName() != null && groupname != null && device.getDeviceName() == null) {
                                if (!groupidset.isEmpty()) {
                                    List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                    for (List<String> list : splitList) {
                                        List<se.info24.ismOperationsPojo.TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringUserGroupName(ismOperationsSession, device.getUserName(), list, gc_start_time, gc_stop_time);
                                        transactionResultList.addAll(transactionResultListTemp);
                                    }
//                                    transactionResultList = deviceDAO.getTransactionResultBySearchStringUserGroupName(ismOperationsSession, device.getUserName(), groupidset, gc_start_time, gc_stop_time);
                                } else {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("GroupName Not Found");
                                    return tingcoDevice;
                                }
                            } else if (device.getUserName() == null && groupname != null && device.getDeviceName() != null) {
                                if (!groupidset.isEmpty()) {
                                    if (TCMUtil.isValidUUID(device.getDeviceName())) {
                                        List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                        for (List<String> list : splitList) {
                                            List<se.info24.ismOperationsPojo.TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringDeviceIDGroupName(ismOperationsSession, device.getDeviceName(), list, gc_start_time, gc_stop_time);
                                            transactionResultList.addAll(transactionResultListTemp);
                                        }
//                                        transactionResultList = deviceDAO.getTransactionResultBySearchStringDeviceIDGroupName(ismOperationsSession, device.getDeviceName(), groupidset, gc_start_time, gc_stop_time);
                                    } else {
                                        List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                        for (List<String> list : splitList) {
                                            List<se.info24.ismOperationsPojo.TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringDeviceGroupName(ismOperationsSession, device.getDeviceName(), list, gc_start_time, gc_stop_time);
                                            transactionResultList.addAll(transactionResultListTemp);
                                        }
//                                        transactionResultList = deviceDAO.getTransactionResultBySearchStringDeviceGroupName(ismOperationsSession, device.getDeviceName(), groupidset, gc_start_time, gc_stop_time);

                                    }
                                } else {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("GroupName Not Found");
                                    return tingcoDevice;
                                }
                            } else if (device.getUserName() != null && groupname == null && device.getDeviceName() != null) {
                                if (TCMUtil.isValidUUID(device.getDeviceName())) {
                                    List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                    for (List<String> list : splitList) {
                                        List<se.info24.ismOperationsPojo.TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringUserDeviceIDName(ismOperationsSession, device.getDeviceName(), device.getUserName(), gc_start_time, gc_stop_time, list);
                                        transactionResultList.addAll(transactionResultListTemp);
                                    }
//                                    transactionResultList = deviceDAO.getTransactionResultBySearchStringUserDeviceIDName(ismOperationsSession, device.getDeviceName(), device.getUserName(), gc_start_time, gc_stop_time, groupidset);
                                } else {
                                    List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                    for (List<String> list : splitList) {
                                        List<se.info24.ismOperationsPojo.TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringUserDeviceName(ismOperationsSession, device.getDeviceName(), device.getUserName(), gc_start_time, gc_stop_time, list);
                                        transactionResultList.addAll(transactionResultListTemp);
                                    }
//                                    transactionResultList = deviceDAO.getTransactionResultBySearchStringUserDeviceName(ismOperationsSession, device.getDeviceName(), device.getUserName(), gc_start_time, gc_stop_time, groupidset);
                                }

                            } else if (device.getUserName() != null && groupname != null && device.getDeviceName() != null) {
                                if (!groupidset.isEmpty()) {
                                    if (TCMUtil.isValidUUID(device.getDeviceName())) {
                                        List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                        for (List<String> list : splitList) {
                                            List<se.info24.ismOperationsPojo.TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringUserDeviceIDGroupName(ismOperationsSession, device.getDeviceName(), device.getUserName(), list, gc_start_time, gc_stop_time);
                                            transactionResultList.addAll(transactionResultListTemp);
                                        }
//                                        transactionResultList = deviceDAO.getTransactionResultBySearchStringUserDeviceIDGroupName(ismOperationsSession, device.getDeviceName(), device.getUserName(), groupidset, gc_start_time, gc_stop_time);
                                    } else {
                                        List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                                        for (List<String> list : splitList) {
                                            List<se.info24.ismOperationsPojo.TransactionResult> transactionResultListTemp = deviceDAO.getTransactionResultBySearchStringUserDeviceGroupName(ismOperationsSession, device.getDeviceName(), device.getUserName(), list, gc_start_time, gc_stop_time);
                                            transactionResultList.addAll(transactionResultListTemp);
                                        }
//                                        transactionResultList = deviceDAO.getTransactionResultBySearchStringUserDeviceGroupName(ismOperationsSession, device.getDeviceName(), device.getUserName(), groupidset, gc_start_time, gc_stop_time);

                                    }
                                } else {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("GroupName Not Found");
                                    return tingcoDevice;
                                }
                            }
                            if (!transactionResultList.isEmpty()) {
                                Set<Integer> curencyId = new HashSet();
                                Set<String> usergroupID = new HashSet<String>();
                                Hashtable<String, Float> userAmount = new Hashtable<String, Float>();
                                Hashtable<String, Integer> userNumber = new Hashtable<String, Integer>();
                                Hashtable<String, List<se.info24.ismOperationsPojo.TransactionResult>> TR = new Hashtable<String, List<se.info24.ismOperationsPojo.TransactionResult>>();
                                for (se.info24.ismOperationsPojo.TransactionResult transactionResult : transactionResultList) {
                                    curencyId.add(transactionResult.getCurrencyId());
                                    if (transactionResult.getUserAliasId() != null) {
                                        if (!TR.containsKey(transactionResult.getUserAliasId())) {
                                            List<se.info24.ismOperationsPojo.TransactionResult> TRList = new ArrayList<se.info24.ismOperationsPojo.TransactionResult>();
                                            TRList.add(transactionResult);
                                            TR.put(transactionResult.getUserAliasId(), TRList);
                                        } else {
                                            List<se.info24.ismOperationsPojo.TransactionResult> TRList = TR.get(transactionResult.getUserAliasId());
                                            boolean flag = false;
                                            for (se.info24.ismOperationsPojo.TransactionResult transactionResult1 : TRList) {
                                                if (transactionResult1.getCurrencyId().intValue() == transactionResult.getCurrencyId().intValue()) {
                                                    flag = true;
                                                }
                                            }
                                            if (!flag) {
                                                TRList.add(transactionResult);
                                                TR.put(transactionResult.getUserAliasId(), TRList);
                                            }
                                        }

                                    }
                                    if (!userAmount.containsKey(transactionResult.getUserAliasId() + "," + transactionResult.getCurrencyId())) {
                                        userAmount.put(transactionResult.getUserAliasId() + "," + transactionResult.getCurrencyId(), transactionResult.getAmount().floatValue());
                                    } else {
                                        userAmount.put(transactionResult.getUserAliasId() + "," + transactionResult.getCurrencyId(), (userAmount.get(transactionResult.getUserAliasId() + "," + transactionResult.getCurrencyId()) + transactionResult.getAmount().floatValue()));
                                    }

                                    if (!userNumber.containsKey(transactionResult.getUserAliasId() + "," + transactionResult.getCurrencyId())) {
                                        userNumber.put(transactionResult.getUserAliasId() + "," + transactionResult.getCurrencyId(), 1);
                                    } else {
                                        userNumber.put(transactionResult.getUserAliasId() + "," + transactionResult.getCurrencyId(), (userNumber.get(transactionResult.getUserAliasId() + "," + transactionResult.getCurrencyId()) + 1));
                                    }
                                    if (transactionResult.getUserGroupId() != null) {
                                        usergroupID.add(transactionResult.getUserGroupId());
                                    }

                                }
                                List<GroupTranslations> gtLists = new ArrayList<GroupTranslations>();
                                if (!usergroupID.isEmpty()) {
                                    gtLists = deviceDAO.getGroupTranslationsById(usergroupID, device.getCountryID().getValue(), session);
                                }
                                List<Currency> currency = deviceDAO.getCurrencyByCurrencyId(session, curencyId);
                                return tingcoDeviceXML.buildGetUserSalesDetails(tingcoDevice, TR, currency, gtLists, userAmount, userNumber);
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                                return tingcoDevice;
                            }
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("XML Data Not Found");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
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
//            System.gc();
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getDeviceSettings(String deviceId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("deviceId should not be empty");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<DeviceSettings> deviceSettingsList = deviceDAO.getDeviceSettingsbyDeviceId(deviceId, session);
                    if (!deviceSettingsList.isEmpty()) {
                        tingcoDevice = tingcoDeviceXML.buildDeviceSettingsList(tingcoDevice, deviceSettingsList);
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No DeviceSettings found for the given deviceId");
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
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getDevicesByGroupID(String groupId, int countryId, String searchString, String maxResult) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("GroupID should not be empty");
                    return tingcoDevice;
                }
                if (countryId <= 0) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("countryId should not be empty");
                    return tingcoDevice;
                }
                if (!searchString.equals("")) {
                    searchString = searchString.split("/")[2];
                } else {
                    searchString = null;
                }
                if (!maxResult.equals("")) {
                    maxResult = maxResult.split("/")[2];
                } else {
                    maxResult = 1000 + "";
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<se.info24.pojo.Device> deviceList = new ArrayList<se.info24.pojo.Device>();
                    List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(groupId, session);
                    Set<String> groupIdSet = groupDAO.getGroupIdsList(groupId, groupTrustsList);
                    if (searchString == null) {
                        List<List<String>> splList = TCMUtil.splitStringList(new ArrayList<String>(groupIdSet), 2000);
                        for (List<String> list : splList) {
                            List<se.info24.pojo.Device> deviceListtemp = deviceDAO.getDeviceByGroupId(new HashSet<String>(list), session, 0);
                            deviceList.addAll(deviceListtemp);
                        }
                    } else {
                        List<List<String>> splList = TCMUtil.split(new ArrayList<String>(groupIdSet), 2000);
                        for (List<String> list : splList) {
                            List<se.info24.pojo.Device> deviceListtemp = deviceDAO.getDeviceDetails(0, new HashSet<String>(list), searchString, Integer.valueOf(maxResult), session); //countryid not required, so it has set as 0
                            deviceList.addAll(deviceListtemp);
                        }
                    }

                    if (!deviceList.isEmpty()) {
                        Collections.sort(deviceList, new Comparator<se.info24.pojo.Device>() {

                            public int compare(se.info24.pojo.Device o1, se.info24.pojo.Device o2) {
                                return Long.valueOf(o2.getCreatedDate().getTime()).compareTo(Long.valueOf(o1.getCreatedDate().getTime()));
                            }
                        });

                        if (deviceList.size() > Integer.parseInt(maxResult)) {
                            deviceList = deviceList.subList(0, Integer.parseInt(maxResult));
                        }

                        List<String> deviceidList = new ArrayList<String>();
                        Set<String> groupidList = new HashSet<String>();

                        for (se.info24.pojo.Device string : deviceList) {
                            deviceidList.add(string.getDeviceId());
                            groupidList.add(string.getGroups().getGroupId());
                        }

                        deviceList = deviceDAO.getDeviceByDeviceIdsList(session, deviceidList);
                        List<GroupTranslations> groupTransList = groupDAO.getGroupTranslationsByGroupId(groupidList, countryId, Integer.valueOf(maxResult), session);
                        tingcoDevice = tingcoDeviceXML.buildDeviceList(tingcoDevice, deviceList, groupTransList);

                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Devices for the given GroupID");
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
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice checkDeviceConnectionAndDeviceTypeName(String deviceId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        Session ismOperSession = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {

                if (deviceId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceId should not be null");
                    return tingcoDevice;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ismOperSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    se.info24.pojo.Device device = deviceDAO.getDeviceById(deviceId, session);
                    List<se.info24.pojo.Device> deviceList = new ArrayList<se.info24.pojo.Device>();
                    deviceList.add(device);
                    tingcoDevice = tingcoDeviceXML.buildTingcoDeviceDetailsXML(tingcoDevice, deviceList, 0, ismOperSession);
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

    private TingcoDevice getDeviceStatusDataItemsByDeviceId(String deviceId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        Session ismOperSession = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceId should not be null");
                    return tingcoDevice;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ismOperSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    List<DeviceStatusDataItems> deviceStatusList = deviceDAO.getDeviceStatusDataItemsByDeviceID(deviceId, ismOperSession);
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                    if (!deviceStatusList.isEmpty()) {
                        List<String> deviceStatusDataItemsIdList = new ArrayList<String>();
                        for (DeviceStatusDataItems deviceStatusDataItems : deviceStatusList) {
                            deviceStatusDataItemsIdList.add(deviceStatusDataItems.getId().getDeviceDataItemId());
                        }
                        List<DeviceDataitemTranslations> deviceDataitemTranslationses = deviceDAO.getDeviceDataItemTranslationsByDataItemIdorderBydataItemName(deviceStatusDataItemsIdList, session);
//                        Hashtable<String, DeviceDataitemTranslations> hashtable = new Hashtable<String, DeviceDataitemTranslations>();

                        tingcoDevice = tingcoDeviceXML.buildDeviceStatusDataItems(tingcoDevice, deviceStatusList, timeZoneGMToffset, session, deviceDataitemTranslationses);
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

    private TingcoDevice getDeviceConnectionStatusById(String groupId, String deviceId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        Session ismOperSession = null;
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
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
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
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
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

    private TingcoDevice getDeviceDetails(int countryId, String groupId, String searchField) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        Session ismOperSession = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {

                if (groupId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("GroupID should not be empty");
                    return tingcoDevice;
                }
                if (searchField == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("SearchField should not be empty");
                    return tingcoDevice;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ismOperSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(groupId, session);
                    Set<String> groupIdSet = groupDAO.getGroupIdsList(groupId, groupTrustsList);

                    List<se.info24.pojo.Device> device = new ArrayList<se.info24.pojo.Device>();
                    List<List<String>> spList = TCMUtil.splitStringList(new ArrayList<String>(groupIdSet), 2000);
                    for (List<String> list : spList) {
                        List<se.info24.pojo.Device> devicetemp = deviceDAO.getDeviceDetails(countryId, new HashSet<String>(list), searchField, 0, session);
                        device.addAll(devicetemp);
//                        if (device.size() > 200) {
//                            device = device.subList(0, 200);
//                        }
                    }

                    Collections.sort(device, new Comparator<se.info24.pojo.Device>() {

                        public int compare(se.info24.pojo.Device o1, se.info24.pojo.Device o2) {
                            return o1.getDeviceName().compareToIgnoreCase(o2.getDeviceName());
                        }
                    });

                    if (device.size() > 200) {
                        tingcoDevice = tingcoDeviceXML.buildTingcoDeviceDetailsXML(tingcoDevice, device.subList(0, 200), countryId, ismOperSession);
                    } else {
                        tingcoDevice = tingcoDeviceXML.buildTingcoDeviceDetailsXML(tingcoDevice, device, countryId, ismOperSession);
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

    private TingcoDevice createDevice(String content) {
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
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    if (devices.getDevices().getDevice().size() > 0) {
                        Device deviceXML = devices.getDevices().getDevice().get(0);
                        String addressId = UUID.randomUUID().toString();
                        if (deviceXML != null) {
                            se.info24.pojo.Device dev = new se.info24.pojo.Device();
                            String deviceId = deviceXML.getDeviceID();
                            String groupId = deviceXML.getGroupID().getValue();
                            if (deviceXML.getDeviceID() != null) {
                                dev.setDeviceId(deviceId);
                            }
                            if (deviceXML.getDeviceName() != null) {
                                dev.setDeviceName(deviceXML.getDeviceName());
                            }
                            if (deviceXML.getDeviceName2() != null) {
                                dev.setDeviceName2(deviceXML.getDeviceName2());
                            }
                            if (deviceXML.getDeviceDescription() != null) {
                                dev.setDeviceDescription(deviceXML.getDeviceDescription());
                            }
                            if (deviceXML.getGroupID() != null) {
                                dev.setGroups(new Groups(deviceXML.getGroupID().getValue()));
                            }
                            //Check DeviceName2 and GroupId exists in DB.
                            List<se.info24.pojo.Device> list = deviceDAO.getDeviceByGroupIdNDeviceName2(groupId, deviceXML.getDeviceName2(), session);
                            if (!list.isEmpty()) {
                                TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Add Device", deviceXML.getDeviceName2(), "Device", "Failed");
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("You cannot have two Devices with the same Unit ID in the same Organization");
                                return tingcoDevice;
                            }

                            if (deviceXML.getDeviceTypeID() != null) {
                                dev.setDeviceTypes(new DeviceTypes(deviceXML.getDeviceTypeID().getValue()));
                            }
                            if (deviceXML.getDeviceAgreementID() != null) {
                                dev.setAgreements(new Agreements(deviceXML.getDeviceAgreementID()));
                            }
                            if (deviceXML.getAssetID() != null) {
                                dev.setAssetId(deviceXML.getAssetID());
                            }
                            if (deviceXML.getDeviceAddress() != null) {
                                DeviceAddress devAddress = deviceXML.getDeviceAddress();
                                Addresses address = new Addresses();
                                address.setAddressId(addressId);
                                AddressType addressType = new AddressType();
                                addressType.setAddressTypeId(1); //Hardcoded as per .NET team instruction
                                address.setAddressType(addressType);
                                address.setAddressStreet(devAddress.getAddressStreet());
                                address.setAddressSuite(devAddress.getAddressSuite());
                                address.setAddressZip(devAddress.getAddressZip());
                                address.setAddressCity(devAddress.getAddressCity());
                                address.setCountry(new Country(devAddress.getCountryID().getValue()));
                                address.setUserId(sessions2.getUserId());
                                address.setCreatedDate(gc.getTime());
                                address.setUpdatedDate(gc.getTime());
                                session.saveOrUpdate(address);
                                session.flush();
                                session.clear();
                                dev.setAddresses(address);
                            }
                            if (deviceXML.getShortdescription() != null) {
                                dev.setShortDescription(deviceXML.getShortdescription());
                            }

                            Company company = new Company();
                            company.setCompanyId("830AA198-0BEA-4EB1-B758-45D69647E417"); //Hardcoded as per feature backlog id 389
                            dev.setCompany(company);
                            dev.setDeviceEnabled(1);
                            dev.setInvoiceDevice(1);
                            dev.setActiveFromDate(gc.getTime());
                            dev.setInstalledDate(gc.getTime());
                            dev.setUserId(sessions2.getUserId());
                            dev.setCreatedDate(gc.getTime());
                            dev.setUpdatedDate(gc.getTime());

                            List<se.info24.pojo.Services> servicesList = new ArrayList<Services>();
                            if (deviceXML.getServices() != null && deviceXML.getServices().getService().size() > 0) {
                                List<se.info24.devicejaxbPost.Service> servicesXML = deviceXML.getServices().getService();
                                List<String> serviceIDList = new ArrayList<String>();
                                for (se.info24.devicejaxbPost.Service svtg : servicesXML) {
                                    serviceIDList.add(svtg.getServiceID());
                                }
                                ServiceDeviceSubscriptions sds = deviceDAO.getServiceDeviceSubscriptions(serviceIDList, deviceId, deviceXML.getServiceClientLogins().getServiceClientLoginID(), deviceXML.getDeviceAgreementID(), session);
                                if (sds != null) {
                                    TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Add Device", deviceXML.getDeviceName2(), "Device", "Failed");
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("ServiceDeviceSubscriptions already exists with the given serviceId's, deviceId, ServiceClientLoginId and AgreementId");
                                    return tingcoDevice;
                                }
                                servicesList = deviceDAO.getServices(session, serviceIDList);
                            }
                            if (dev != null) {
                                session.saveOrUpdate(dev);
                                session.flush();
                                session.clear();
                                if (deviceXML.getBillingCategories() != null && !deviceXML.getBillingCategories().isEmpty()) {
                                    if (deviceXML.getBillingCategories().get(0).getBillingCategoryID() != null) {
                                        DeviceBillingCategories deviceBillingCategories = new DeviceBillingCategories();
                                        deviceBillingCategories.setDeviceId(deviceId);
                                        List<BillingCategories> billingCategorieses = deviceDAO.getBillingCategorieses(session, deviceXML.getBillingCategories().get(0).getBillingCategoryID());
                                        if (billingCategorieses.isEmpty()) {
                                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                                            tingcoDevice.getMsgStatus().setResponseText("Not valid BillingCategories");
                                            return tingcoDevice;
                                        }
                                        deviceBillingCategories.setBillingCategories(billingCategorieses.get(0));
                                        deviceBillingCategories.setLastUpdatedByUserId(sessions2.getUserId());
                                        deviceBillingCategories.setUpdatedDate(gc.getTime());
                                        deviceBillingCategories.setCreatedDate(gc.getTime());
                                        session.saveOrUpdate(deviceBillingCategories);
                                        session.flush();
                                        session.clear();
                                    }
                                }
                            }
                            if (deviceDAO.addNewDevice(dev, servicesList, session, deviceXML, sessions2.getUserId())) {
                                TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Add Device", deviceXML.getDeviceName2(), "Device", "Success");
                                tingcoDevice = tingcoDeviceXML.buildDeviceID(tingcoDevice, deviceId);
                            } else {
                                TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Add Device", deviceXML.getDeviceName2(), "Device", "Failed");
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("Error Occured while Saving Device");
                                return tingcoDevice;
                            }
                        } else {
                            TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Add Device", deviceXML.getDeviceName2(), "Device", "Failed");
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Invalid Device XML Found");
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

    public TingcoDevice getDeviceInfo(String deviceId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session operationsSession = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeciveID should not be empty");
                    return tingcoDevice;
                } else if (countryId <= 0) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("CountryID should not be empty");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    operationsSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        se.info24.pojo.Device device = deviceDAO.getDeviceById(deviceId, session);
                        DevicePendingDelete dpd = deviceDAO.getDevicePendingDelete(deviceId, session);
                        List<ObjectContactMemberships> objectContactMem = deviceDAO.getObjectContactMemberships(deviceId, session);

                        List<String> objectContactIdList = new ArrayList<String>();
                        if (!objectContactMem.isEmpty()) {
                            for (ObjectContactMemberships ocm : objectContactMem) {
                                objectContactIdList.add(ocm.getId().getObjectContactId());
                            }
                        }
                        ItemConnectionStatus itemConnectionStatus = (ItemConnectionStatus) deviceDAO.getitemconnectionStatus(deviceId, operationsSession);
                        DeviceStatus deviceStatus = deviceDAO.getDeviceStatusByDeviceId(deviceId, operationsSession);
                        List<ObjectContacts> objectContactsList = deviceDAO.getObjectContacts(objectContactIdList, session);
                        List<DeviceOperationsMember> deviceOperationsMembersList = deviceDAO.getdeviceOperationsMember(session, deviceId);
                        List<DeviceOperationsStatusTranslation> dost = deviceDAO.getDeviceOperationsStatusTrans(session, countryId);

                        ObjectTags objectTags = (ObjectTags) session.get(ObjectTags.class, deviceId);

                        ArrayList<se.info24.pojo.Device> devices = new ArrayList<se.info24.pojo.Device>();
                        devices.add(device);
                        if (device != null) {
                            List<DeviceBillingCategories> deviceBillingCategorieses = deviceDAO.getDeviceBillingCategories(session, deviceId);
                            List<BillingCategoryTranslations> billingCategoryTranslationses = new ArrayList<BillingCategoryTranslations>();
                            if (!deviceBillingCategorieses.isEmpty()) {
                                billingCategoryTranslationses.addAll(deviceDAO.getBillingCategoryTranslations(session, deviceBillingCategorieses.get(0).getBillingCategories().getBillingCategoryId(), countryId));
                            }
                            tingcoDeviceXML.buildTincoDeviceXML(tingcoDevice, devices, dpd, dost, objectContactMem, objectContactsList, deviceOperationsMembersList, itemConnectionStatus, deviceStatus, countryId, timeZoneGMToffset, objectTags, billingCategoryTranslationses);
                            return tingcoDevice;
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Device Not Found With Given DeciveID");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
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
            tingcoDevice.getMsgStatus().setResponseText("Error Occured while Loading Device");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            if (operationsSession != null) {
                operationsSession.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoDevice getDeviceTypeCommands(String deviceTypeId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        DbManager dbManager = new DbManager();
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceTypeId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("deviceTypeId should not be empty");
                    return tingcoDevice;
                }
                if (countryId <= 0) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("countryId should not be empty");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                session = HibernateUtil.getSessionFactory().openSession();
                PermissionDAO dao = new PermissionDAO();
                List<String> devicetypeCommand = new ArrayList<String>();
                List<DeviceTypeCommands> deviceTypeCommandsList = deviceDAO.getDeviceTypeCommands(deviceTypeId, session);
                for (DeviceTypeCommands deviceTypeCommands : deviceTypeCommandsList) {
                    devicetypeCommand.add(deviceTypeCommands.getDeviceTypeCommandId());
                }
                if (devicetypeCommand.isEmpty()) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                    return tingcoDevice;
                }

                TCMUtil.loger(getClass().getName(), "deviceTypeCommandsList " + deviceTypeCommandsList.size() + "", "Info");

                List<UserRoleMemberships2> userRoleMemberships2 = dbManager.getUserRoleIdByUserId(session, sessions2.getUserId());
                List<String> userroleId = new ArrayList<String>();
                for (UserRoleMemberships2 userRoleMemberships21 : userRoleMemberships2) {
                    userroleId.add(userRoleMemberships21.getId().getUserRoleId());
                }
                Set<String> deviceTypeCommandId = new HashSet<String>();
                List<UserRoleObjectPermissions2> userRoleObjectPermissions2s = dao.getUserRoleObjectPermissions2ByObjectIDsandUserRoleIDs(userroleId, devicetypeCommand, session);
                if (userRoleObjectPermissions2s == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                    return tingcoDevice;
                }
                for (UserRoleObjectPermissions2 userRoleObjectPermissions2 : userRoleObjectPermissions2s) {
                    List<PermissionOperations> permissionOperations = dbManager.getPermissionOperationsByPermissionID(session, userRoleObjectPermissions2.getId().getPermissionId());
                    for (PermissionOperations po : permissionOperations) {
                        if (po.getOperations().getOperationName().equalsIgnoreCase(TCMUtil.CONTROL)) {
                            deviceTypeCommandId.add(userRoleObjectPermissions2.getId().getObjectId());
                        }
                    }
                }
                if (deviceTypeCommandId.isEmpty()) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                    return tingcoDevice;
                }
                deviceTypeCommandsList = deviceDAO.getDeviceTypeCommandByIDS(deviceTypeCommandId, session);
                List<DeviceTypeCommandTranslations> deviceTypeCommandTranslationses = deviceDAO.getDeviceTypeCommandTranslationsOrderBycommandButtonText(session, devicetypeCommand, countryId);
                if (deviceTypeCommandsList != null) {
                    tingcoDevice = tingcoDeviceXML.buildDeviceTypeCommands(tingcoDevice, deviceTypeCommandsList, deviceTypeCommandTranslationses);
                } else {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("No Data Found");
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
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getObjectContact(String groupId, int countryId, String objectId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            session = HibernateUtil.getSessionFactory().openSession();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("groupId should not be empty");
                    return tingcoDevice;
                }
                if (countryId <= 0) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("countryId should not be empty");
                    return tingcoDevice;
                }
                if (objectId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("objectId should not be empty");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    List<ObjectContacts> objectContactsList = deviceDAO.getObjectContact(groupId, countryId, objectId, session);
                    GroupTranslations groupTrans = groupDAO.getGroupTranslationsByIds(groupId, countryId, session);
                    if (!objectContactsList.isEmpty()) {
                        tingcoDevice = tingcoDeviceXML.buildObjectContacts(tingcoDevice, groupTrans, objectContactsList);
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
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getObjectSettingTemplates(String deviceId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (deviceId.equals("")) {
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("DeviceId should not empty");
                return tingcoDevice;
            }
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    se.info24.pojo.Device device = deviceDAO.getDeviceById(deviceId, session);
                    if (device != null) {
                        List<ObjectTypeSettingTemplates> otst = deviceDAO.getObjectTypeSettingTemplatesByTypeId(device.getDeviceTypes().getDeviceTypeId(), session);
                        if (!otst.isEmpty()) {
                            List<String> objectSettingTemplateIdList = new ArrayList<String>();
                            for (ObjectTypeSettingTemplates otsts : otst) {
                                objectSettingTemplateIdList.add(otsts.getId().getObjectSettingTemplateId());
                            }
                            List<ObjectSettingTemplates> ost = deviceDAO.getObjectSettingTemplate(objectSettingTemplateIdList, session);
                            if (!ost.isEmpty()) {
                                tingcoDevice = tingcoDeviceXML.buildObjectSettingTemplates(tingcoDevice, ost);
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Data found in ObjectSettingTemplates");
                                return tingcoDevice;
                            }
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("No Data found in given ObjectTypeSettingTemplates");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data found in given Device");
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

    private TingcoDevice getObjectSettingTemplatesForDeviceServices(String serviceId, String deviceId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (serviceId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("ServiceId should not be empty");
                    return tingcoDevice;
                }
                if (deviceId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceId should not be empty");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    se.info24.pojo.Device device = deviceDAO.getDeviceById(deviceId, session);
                    if (device != null) {
                        List<ServiceDeviceSettingTemplates> sdstList = deviceDAO.getServiceDeviceSettingTemplatesbyIds(serviceId, device.getDeviceTypes().getDeviceTypeId(), session);
                        List<String> objectSettingTemplateIdList = new ArrayList<String>();
                        if (!sdstList.isEmpty()) {
                            for (ServiceDeviceSettingTemplates sdst : sdstList) {
                                objectSettingTemplateIdList.add(sdst.getId().getObjectSettingTemplateId());
                            }
                            List<ObjectSettingTemplates> ostList = deviceDAO.getObjectSettingTemplate(objectSettingTemplateIdList, session);
                            tingcoDevice = tingcoDeviceXML.buildObjectSettingTemplatesForDeviceService(tingcoDevice, ostList);
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("No Data Found in ServiceDeviceSettingTemplates");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Found in Device");
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

    private TingcoDevice getPredefinedSettingsPackages(String deviceId, String groupId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (deviceId.equals("")) {
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("DeviceId should not empty");
                return tingcoDevice;
            }
            if (groupId.equals("")) {
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("groupId should not empty");
                return tingcoDevice;
            }
            if (countryId <= 0) {
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("countryId should not empty");
                return tingcoDevice;
            }
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    se.info24.pojo.Device device = deviceDAO.getDeviceById(deviceId, session);
                    List<ObjectSettingPackageTranslations> osptransList = deviceDAO.getObjectSettingPackageTranslations(device.getDeviceTypes().getDeviceTypeId(), groupId, countryId, session);
                    if (!osptransList.isEmpty()) {
                        tingcoDevice = tingcoDeviceXML.buildObjectSettingPackages(tingcoDevice, osptransList);
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

    private TingcoDevice getServiceDeviceSettings(String serviceDeviceSubscriptionId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (serviceDeviceSubscriptionId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("ServiceDeviceSubscriptionId should not be empty");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ServiceDeviceSettings> sdsList = deviceDAO.getServiceDeviceSettingsBySubscriptionId(session, serviceDeviceSubscriptionId);
                    if (!sdsList.isEmpty()) {
                        tingcoDevice = tingcoDeviceXML.buildServiceDeviceSettings(tingcoDevice, sdsList);
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Found in ServiceDeviceSettings");
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

    private TingcoDevice getSettingDataType() throws DatatypeConfigurationException {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<SettingDataType> settingList = deviceDAO.getSettingDataType(session);
                    if (!settingList.isEmpty()) {
                        tingcoDevice = tingcoDeviceXML.buildSettingDataType(tingcoDevice, settingList);
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

    private TingcoDevice getSettingsPackage(String groupId, int countryId, String deviceTypeId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoDevice tingcoDevice = null;
        try {
            if (groupId.equals("")) {
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("GroupId  should not be empty");
                return tingcoDevice;
            } else if (countryId <= 0) {
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("CountryId  should not be empty");
                return tingcoDevice;
            } else if (deviceTypeId.equals("")) {
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("DeviceTypeId  should not be empty");
                return tingcoDevice;
            }
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            session = HibernateUtil.getSessionFactory().openSession();
            List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(groupId, session);
            Set<String> groupIdSet = groupDAO.getGroupIdsList(groupId, groupTrustsList);
            if (!groupIdSet.isEmpty()) {
                List<GroupObjectSettingPackages> gospList = new ArrayList<GroupObjectSettingPackages>();
                List<List<String>> spList = TCMUtil.splitStringList(new ArrayList<String>(groupIdSet), 2000);
                for (List<String> list : spList) {
                    List<GroupObjectSettingPackages> gospListTemp = deviceDAO.getGroupObjectSettingPackages(new HashSet<String>(list), session);
                    gospList.addAll(gospListTemp);
                }

                if (!gospList.isEmpty()) {
                    List<String> objectSettingPackageIdList = deviceDAO.getObjectSettingPackageIdList(gospList);
                    List<ObjectSettingPackages> ospList = deviceDAO.getObjectSettingPackagesByIds(objectSettingPackageIdList, deviceTypeId, session);
                    if (!ospList.isEmpty()) {
                        List<String> objectSettingPackageIdsList = new ArrayList<String>();
                        for (ObjectSettingPackages osp : ospList) {
                            objectSettingPackageIdsList.add(osp.getObjectSettingPackageId());
                        }
                        List<ObjectSettingPackageTranslations> osptList = deviceDAO.getObjectSettingPackageTranslations(objectSettingPackageIdsList, countryId, session);
                        if (!osptList.isEmpty()) {
                            tingcoDevice = tingcoDeviceXML.buildObjectSettingPackages(tingcoDevice, osptList);
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("No Data found in ObjectSettingPackage Translations");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data found in ObjectSettingPackages");
                        return tingcoDevice;
                    }
                } else {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("No Data found in GroupObjectSettingPackages");
                    return tingcoDevice;
                }
            } else {
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("No Data found in Groups");
                return tingcoDevice;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
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

    private TingcoDevice getUsageLogDetails(String content, String functionarea) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session ismOperationsSession = null;
        Session session = null;
        if (!functionarea.equals("")) {
            functionarea = functionarea.split("/")[2];
        } else {
            functionarea = null;
        }
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                if (functionarea != null) {
                    hasPermission = getPermission(sessions2.getUserId(), functionarea.toUpperCase(), TCMUtil.READ);
                } else {
                    hasPermission = getPermission(sessions2.getUserId(), TCMUtil.REPORTS, TCMUtil.READ);
                }

                if (hasPermission) {
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    ismOperationsSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    session = HibernateUtil.getSessionFactory().openSession();
                    if (devices.getDevices().getDevice().size() > 0) {
                        Device deviceXML = devices.getDevices().getDevice().get(0);
                        if (deviceXML != null) {
                            UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                            if (userTimeZones2 != null) {
                                String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                                List<Object> objectUsageRecordsList = deviceDAO.getObjectUsageRecordsForUsageLog(deviceXML, tingcoDevice, ismOperationsSession, session, timeZoneGMToffset);

//                                List<ObjectUsageRecords> objectUsageRecordsList = deviceDAO.getObjectUsageRecordsForUsageLog1(deviceXML, tingcoDevice, ismOperationsSession, session, timeZoneGMToffset);
                                if (objectUsageRecordsList.isEmpty()) {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("No Usage Records found for the given input");
                                    return tingcoDevice;
                                }
                                if (objectUsageRecordsList.size() > 200) {
                                    objectUsageRecordsList = objectUsageRecordsList.subList(0, 200);
                                    tingcoDevice.setExceeds200(1);
                                } else {
                                    tingcoDevice.setExceeds200(0);
                                }
//                                System.out.println("size "+objectUsageRecordsList.size());
                                tingcoDevice = tingcoDeviceXML.buildObjectUsageRecordsForUsageLog(tingcoDevice, objectUsageRecordsList, timeZoneGMToffset, session);
//                                tingcoDevice = tingcoDeviceXML.buildObjectUsageRecordsForUsageLog1(tingcoDevice, objectUsageRecordsList, timeZoneGMToffset, session);

                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
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
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getUserFavoriteDataItems(String userId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<DeviceDataitemTranslations> deviceDataItemTranslations = deviceDAO.getDeviceDataItemTranslationsByCriteria(userId, countryId, session);
                    if (!deviceDataItemTranslations.isEmpty()) {
                        tingcoDevice = tingcoDeviceXML.buildUserFavoriteDataItems(tingcoDevice, deviceDataItemTranslations);
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

    private TingcoDevice insertServiceDeviceSubscriptions(String serviceId, String deviceId, String serviceClientLoginId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (serviceId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("serviceId should not be empty");
                    return tingcoDevice;
                }
                if (deviceId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("deviceId should not be empty");
                    return tingcoDevice;
                }
                if (serviceClientLoginId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("serviceClientLoginId should not be empty");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    se.info24.pojo.Device device = deviceDAO.getDeviceById(deviceId, session);
                    ServiceDeviceSubscriptions sds = new ServiceDeviceSubscriptions();
                    sds = deviceDAO.getServiceDeviceSubscriptions(serviceId, deviceId, serviceClientLoginId, session);
                    if (sds == null) {
                        if (!deviceDAO.insertServiceDeviceSubscriptions(serviceId, deviceId, serviceClientLoginId, device, sessions2.getUserId(), session)) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error occured while adding ServiceDeviceSubscriptions");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Record already exist with the given ServiceId, DeviceID, ServiceClientLoginID in ServiceDeviceSubscriptions");
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
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice isDeviceName2Exists4DeviceTypeID(String deviceTypeId, String deviceName2) {
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
                } else if (deviceName2 == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceName2 is should not be empty");
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
                    se.info24.pojo.Device device = deviceDAO.getDeviceByDeviceTypeIdNDeviceName2(deviceTypeId, deviceName2, session);
                    if (device != null) {
                        return tingcoDevice;
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("DeviceName2 Not Existed For Given DeviceTypeID");
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
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice isDeviceNameExists4GroupID(String deviceName, String groupId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceName == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceName is should not be empty");
                    return tingcoDevice;
                } else if (groupId == null) {
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
                    se.info24.pojo.Device device = deviceDAO.getDeviceByGroupIDNDeviceName(groupId, deviceName, session);
                    if (device != null) {
                        return tingcoDevice;
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("DeviceName Not Existed For Given GroupID");
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
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getDeviceStatus(int countryId, String deviceId, String groupId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session operationsSession = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (countryId <= 0) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("CountryID Should Be not Null");
                    return tingcoDevice;
                }

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

                if (deviceId == null && groupId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("Either DeviceID or GroupID Has to be Present");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    operationsSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    session = HibernateUtil.getSessionFactory().openSession();
                    deviceMap = new Hashtable<String, se.info24.pojo.Device>();
                    dataitemTrans = new Hashtable<String, DeviceDataitemTranslations>();

                    List<se.info24.ismOperationsPojo.DeviceStatus> deviceStatusList = new ArrayList<DeviceStatus>();
                    HashMap<String, List<se.info24.ismOperationsPojo.DeviceStatusDataItems>> deviceStatusMap = new HashMap<String, List<se.info24.ismOperationsPojo.DeviceStatusDataItems>>();

                    DeviceStatus deviceStatus = null;
                    if (deviceId != null && groupId != null) {
                        deviceStatus = deviceDAO.getDeviceStatus(deviceId, groupId, operationsSession);
                        deviceStatusList.add(deviceStatus);
                    } else if (deviceId != null && groupId == null) {
                        deviceStatus = deviceDAO.getDeviceStatusByDeviceId(deviceId, operationsSession);
                        deviceStatusList.add(deviceStatus);
                    } else if (deviceId == null && groupId != null) {
                        deviceStatusList = deviceDAO.getDeviceStatusByGroupId(groupId, operationsSession);
                    }

                    if (!deviceStatusList.isEmpty()) {
                        for (se.info24.pojo.Device d : deviceDAO.getAllDevices(session)) {
                            deviceMap.put(d.getDeviceId(), d);
                        }

                        for (DeviceDataitemTranslations ddit : deviceDAO.getAllDeviceDataitemTranslations(countryId, session)) {
                            dataitemTrans.put(ddit.getId().getDeviceDataItemId(), ddit);
                        }

                        for (se.info24.ismOperationsPojo.DeviceStatus ds : deviceStatusList) {
                            deviceStatusMap.put(ds.getDeviceId(), deviceDAO.getDeviceStatusDataItemsByDeviceID(deviceId, operationsSession));
                        }
                        tingcoDevice = tingcoDeviceXML.buildDeviceStatus(tingcoDevice, deviceStatusList, deviceMap, dataitemTrans, deviceStatusMap, countryId);
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
            if (session != null) {
                session.close();
            }
            if (operationsSession != null) {
                operationsSession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public DeviceResource getDeviceResource() {
        return new DeviceResource();
    }

    private TingcoDevice sendDeviceTypeCommands(String deviceId, String deviceTypeCommandId, String dataItemValue, String CountryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceTypeCommandId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("deviceTypeCommandId should not be empty");
                    return tingcoDevice;
                }
                if (deviceId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("deviceId should not be empty");
                    return tingcoDevice;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.SEND);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    se.info24.pojo.Device device = deviceDAO.getDeviceById(deviceId, session);
                    DeviceDataMessageDocument ddmDoc = DeviceDataMessageDocument.Factory.newInstance();
                    DeviceDataMessage ddm = ddmDoc.addNewDeviceDataMessage();
                    ddm.setCreateRef("Info24");
                    ddm.setMsgVer(new BigDecimal("4.1"));
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
                    sender.setGroupID(device.getGroups().getGroupId().toUpperCase());
                    sender.setServiceID(TingcoConstants.getServiceID().toUpperCase());

                    MsgReceivers receivers = ddm.addNewMsgReceivers();
                    receivers.addServiceID("0");
                    Object deviceTypeCommandsObject = null;
                    DeviceTypeCommands deviceTypeCommands = null;
                    DeviceDataItems deviceDataItems = new DeviceDataItems();
                    if (deviceTypeCommandId.equalsIgnoreCase("")) {
                        TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Send DeviceTypeCommands", " ", "DeviceTypeCommands", "Failed");
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("deviceTypeCommandId should not null ");
                        return tingcoDevice;
                    } else {
                        deviceTypeCommandsObject = deviceDAO.getDeviceTypeCommandsById(deviceTypeCommandId, session);
                    }
                    if (deviceTypeCommandsObject == null) {
                        TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Send DeviceTypeCommands", " ", "DeviceTypeCommands", "Failed");
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No deviceTypeCommandId found ");
                        return tingcoDevice;
                    }
                    List<String> deviceTypeCommandIdList = new ArrayList();
                    deviceTypeCommandIdList.add(deviceTypeCommandId);
                    List<DeviceTypeCommandTranslations> deviceTypeCommandTranslationses = deviceDAO.getDeviceTypeCommandTranslations(session, deviceTypeCommandIdList, 6);
                    if (!dataItemValue.equals("")) {
                        dataItemValue = dataItemValue.split("/")[2];
                    }
                    if (deviceTypeCommandsObject != null) {
                        deviceTypeCommands = (DeviceTypeCommands) deviceTypeCommandsObject;
                        deviceDataItems = (DeviceDataItems) deviceDAO.getDeviceDataItemsByItemId(deviceTypeCommands.getDeviceDataItemId(), session);
                        dataItemValue = deviceDataItems.getDeviceDataFieldName();
                    }

                    DataContainer dc = ddm.addNewDeviceData();
                    DataSequence ds = dc.addNewDataSequence();
                    ds.setDeviceID(device.getDeviceId());
                    ds.setDeviceTypeID(device.getDeviceTypes().getDeviceTypeId());
                    ds.setGroupID(device.getGroups().getGroupId());
                    ds.setDataItemID(deviceTypeCommands.getDeviceDataItemId().toUpperCase());
                    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
                    ds.setDataItemTime(cal);

                    DataGroup dg = ds.addNewDataGroup();
                    //dg.setGroupName("Commands");
                    dg.setGroupName(deviceDataItems.getDeviceDataGroupName().toUpperCase());
                    DataField dataField = dg.addNewDataField();

                    dataField.setFieldName(dataItemValue.toUpperCase());
                    dataField.setStringValue(deviceTypeCommands.getDataItemValue());



                    UserDAO userdao = new UserDAO();
                    List<Users2> users = userdao.getUsers2ById(sessions2.getUserId(), session);
                    List<GroupTranslations> gtList = userdao.getGroupTranslationsById(users.get(0).getGroupId(), CountryId, session);
                    ServiceDAO servicedao = new ServiceDAO();
                    Services service = (Services) servicedao.getServicesbyServiceId(session, TingcoConstants.getServiceID());
                    se.info24.pojo.Device devices = deviceDAO.getDeviceById(deviceId, session);
                    EventMessageDocument emd = deviceDAO.createEM(device, service, "Manual Command Events", deviceTypeCommandTranslationses.get(0).getCommandButtonText(), null, 0, null, users.get(0).getFirstName() + " " + users.get(0).getLastName(), gtList.get(0).getGroupName(), deviceTypeCommands.getDataItemValue());

                    List<DeviceTypeChannels> deviceTypeChannelsList = deviceDAO.getDeviceTypeChannels(device.getDeviceTypes().getDeviceTypeId(), session);
                    if (!deviceTypeChannelsList.isEmpty()) {
                        for (DeviceTypeChannels dtc : deviceTypeChannelsList) {
                            Channels c = deviceDAO.getChannels(dtc.getId().getChannelId(), session);
                            System.out.println("channel is " + c.getChannelData());
                            chargePointResource.sendDDM(ddmDoc, c.getChannelData());
                            deviceDAO.sendEventMessage(emd, TingcoConstants.getEventTopic());
                        }
                        TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Send DeviceTypeCommands", deviceTypeCommandTranslationses.get(0).getCommandName(), "DeviceTypeCommands", "Success");
                    } else {
                        TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Send DeviceTypeCommands", deviceTypeCommandTranslationses.get(0).getCommandName(), "DeviceTypeCommands", "Failed");
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Channel found to send DDM");
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
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice serviceDeviceSettingInfo(String serviceDeviceSettingId, String objectSettingTemplateId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (serviceDeviceSettingId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("ServiceDeviceSettingId should not be empty");
                    return tingcoDevice;
                }
                if (!objectSettingTemplateId.equals("")) {
                    objectSettingTemplateId = objectSettingTemplateId.split("/")[2];
                } else {
                    objectSettingTemplateId = null;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ServiceDeviceSettings sds = deviceDAO.getServiceDeviceSettings(serviceDeviceSettingId, session);
                    if (sds != null) {
                        Users2 us = userDAO.getUserById(sessions2.getUserId(), session);
                        ObjectSettingTemplates ost = null;
                        if (objectSettingTemplateId != null) {
                            ost = deviceDAO.getObjectSettingTemplates(objectSettingTemplateId, session);
                        }
                        SettingDataType sdt = new SettingDataType();
                        if (sds.getSettingDataTypeId() != null) {
                            sdt = deviceDAO.getSettingDataTypeById(sds.getSettingDataTypeId(), session);
                        }
                        tingcoDevice = tingcoDeviceXML.buildServiceDeviceSettings(tingcoDevice, sds, ost, sdt, us);
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Found in ServiceDeviceSettings");
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

    private TingcoDevice updateDeviceDataItem(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        TingcoDevice tingcoDevice = null;
        Transaction tx = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.UPDATE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    GregorianCalendar gc = new GregorianCalendar();
                    tx = session.beginTransaction();
                    se.info24.devicejaxbPost.TingcoDevice tingcoDevices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    if (!tingcoDevices.getDeviceDataItemScalings().getDeviceDataItemScaling().isEmpty()) {
                        se.info24.devicejaxbPost.DeviceDataItemScaling ddis = tingcoDevices.getDeviceDataItemScalings().getDeviceDataItemScaling().get(0);
                        DeviceDataItemScaling deviceDataItemScaling = deviceDAO.getDeviceDataItemScaling(ddis.getDeviceID(), ddis.getDeviceDataItemID(), session);
                        if (deviceDataItemScaling != null) {
                            if (ddis.getScaling() != null) {
                                Scaling scaling = ddis.getScaling();
                                if (scaling.getFlagScaleValue() != null) {
                                    deviceDataItemScaling.setFlagScaleValue(scaling.getFlagScaleValue());
                                } else {
                                    deviceDataItemScaling.setFlagScaleValue(0);
                                }
                                if (scaling.getRawMinValue() != null) {
                                    deviceDataItemScaling.setRawMinValue(scaling.getRawMinValue());
                                } else {
                                    deviceDataItemScaling.setRawMinValue(null);
                                }
                                if (scaling.getRawMaxValue() != null) {
                                    deviceDataItemScaling.setRawMaxValue(scaling.getRawMaxValue());
                                } else {
                                    deviceDataItemScaling.setRawMaxValue(null);
                                }
                                if (scaling.getScaledMinValue() != null) {
                                    deviceDataItemScaling.setScaledMinValue(scaling.getScaledMinValue());
                                } else {
                                    deviceDataItemScaling.setScaledMinValue(null);
                                }
                                if (scaling.getScaledMaxValue() != null) {
                                    deviceDataItemScaling.setScaledMaxValue(scaling.getScaledMaxValue());
                                } else {
                                    deviceDataItemScaling.setScaledMaxValue(null);
                                }
                                if (scaling.getFlagSetValueToMinIfBelowMin() != null) {
                                    deviceDataItemScaling.setFlagSetValueToMinIfBelowMin(scaling.getFlagSetValueToMinIfBelowMin());
                                } else {
                                    deviceDataItemScaling.setFlagSetValueToMinIfBelowMin(0);
                                }
                                if (scaling.getFlagSetValueToMaxIfAboveMax() != null) {
                                    deviceDataItemScaling.setFlagSetValueToMaxIfAboveMax(scaling.getFlagSetValueToMaxIfAboveMax());
                                } else {
                                    deviceDataItemScaling.setFlagSetValueToMaxIfAboveMax(0);
                                }
                                if (scaling.getFlagSaveAsFixedValue() != null) {
                                    deviceDataItemScaling.setFlagSaveAsFixedValue(scaling.getFlagSaveAsFixedValue());
                                } else {
                                    deviceDataItemScaling.setFlagSaveAsFixedValue(0);
                                }
                                if (scaling.getFixedValue() != null) {
                                    deviceDataItemScaling.setFixedValue(scaling.getFixedValue());
                                } else {
                                    deviceDataItemScaling.setFixedValue(null);
                                }
                            }
                            if (ddis.getWarningLevels() != null) {
                                WarningLevels warningLevels = ddis.getWarningLevels();
                                if (warningLevels.getWarningLowValue() != null) {
                                    deviceDataItemScaling.setWarningLowValue(warningLevels.getWarningLowValue());
                                } else {
                                    deviceDataItemScaling.setWarningLowValue(null);
                                }
                                if (warningLevels.getWarningLowEventTypeID() != null) {
                                    deviceDataItemScaling.setWarningLowEventTypeId(warningLevels.getWarningLowEventTypeID());
                                } else {
                                    deviceDataItemScaling.setWarningLowEventTypeId(null);
                                }
                                if (warningLevels.getWarningHighValue() != null) {
                                    deviceDataItemScaling.setWarningHighValue(warningLevels.getWarningHighValue());
                                } else {
                                    deviceDataItemScaling.setWarningHighValue(null);
                                }
                                if (warningLevels.getWarningHighEventTypeID() != null) {
                                    deviceDataItemScaling.setWarningHighEventTypeId(warningLevels.getWarningHighEventTypeID());
                                } else {
                                    deviceDataItemScaling.setWarningHighEventTypeId(null);
                                }
                            }
                            if (ddis.getAlarmLevels() != null) {
                                AlarmLevels alarmLevels = ddis.getAlarmLevels();
                                if (alarmLevels.getAlarmLowValue() != null) {
                                    deviceDataItemScaling.setAlarmLowValue(alarmLevels.getAlarmLowValue());
                                } else {
                                    deviceDataItemScaling.setAlarmLowValue(null);
                                }
                                if (alarmLevels.getAlarmLowEventTypeID() != null) {
                                    deviceDataItemScaling.setAlarmLowEventTypeId(alarmLevels.getAlarmLowEventTypeID());
                                } else {
                                    deviceDataItemScaling.setAlarmLowEventTypeId(null);
                                }
                                if (alarmLevels.getAlarmHighValue() != null) {
                                    deviceDataItemScaling.setAlarmHighValue(alarmLevels.getAlarmHighValue());
                                } else {
                                    deviceDataItemScaling.setAlarmHighValue(null);
                                }
                                if (alarmLevels.getAlarmHighEventTypeID() != null) {
                                    deviceDataItemScaling.setAlarmHighEventTypeId(alarmLevels.getAlarmHighEventTypeID());
                                } else {
                                    deviceDataItemScaling.setAlarmHighEventTypeId(null);
                                }
                            }
                            if (ddis.getProductLink() != null) {
                                ProductLink productLink = ddis.getProductLink();
                                if (productLink.getProductVariantID() != null) {
                                    deviceDataItemScaling.setProductVariantId(productLink.getProductVariantID());
                                } else {
                                    deviceDataItemScaling.setProductVariantId(null);
                                }
                                if (productLink.getOrderMoreBelowThisValue() != null) {
                                    deviceDataItemScaling.setOrderMoreBelowThisValue(productLink.getOrderMoreBelowThisValue());
                                } else {
                                    deviceDataItemScaling.setOrderMoreBelowThisValue(null);
                                }
                                if (productLink.getOrderMoreEventTypeID() != null) {
                                    deviceDataItemScaling.setOrderMoreEventTypeId(productLink.getOrderMoreEventTypeID());
                                } else {
                                    deviceDataItemScaling.setOrderMoreEventTypeId(null);
                                }
                            }
                            deviceDataItemScaling.setLastUpdatedByUserId(sessions2.getUserId());
                            deviceDataItemScaling.setUpdatedDate(gc.getTime());
                            deviceDAO.saveDeviceDataItemScaling(deviceDataItemScaling, session);
                            List<DataItemTranslationsPerDevice> dataItemTransPerDeviceList = deviceDAO.getDataItemTranslationsPerDevice(ddis.getDeviceID(), ddis.getDeviceDataItemID(), session);
                            if (!dataItemTransPerDeviceList.isEmpty()) {
                                for (DataItemTranslationsPerDevice dits : dataItemTransPerDeviceList) {
                                    deviceDAO.deleteDataItemTranslationsPerDevice(dits, session);
                                }
                            }
                            session.flush();
                            if (ddis.getDataItemTranslationsPerDevice() != null) {
                                List<se.info24.devicejaxbPost.DataItemTranslation> dataItemTransList = ddis.getDataItemTranslationsPerDevice().getDataItemTranslation();
                                for (se.info24.devicejaxbPost.DataItemTranslation ditpd : dataItemTransList) {
                                    DataItemTranslationsPerDevice ditranspd = new DataItemTranslationsPerDevice();
                                    ditranspd.setId(new DataItemTranslationsPerDeviceId(ddis.getDeviceID(), ddis.getDeviceDataItemID(), ditpd.getCountryID()));
                                    ditranspd.setDataItemName(ditpd.getDataItemName());
                                    if (ditpd.getUnit() != null) {
                                        ditranspd.setUnit(ditpd.getUnit());
                                    }
                                    ditranspd.setLastUpdatedByUserId(sessions2.getUserId());
                                    ditranspd.setCreatedDate(gc.getTime());
                                    ditranspd.setUpdatedDate(gc.getTime());
                                    deviceDAO.saveDataItemTranslationsPerDevice(ditranspd, session);
                                }
                            }
                        } else {
                            deviceDataItemScaling = new DeviceDataItemScaling();
                            deviceDataItemScaling.setId(new DeviceDataItemScalingId(ddis.getDeviceID(), ddis.getDeviceDataItemID()));
                            if (ddis.getScaling() != null) {
                                Scaling scaling = ddis.getScaling();
                                if (scaling.getFlagScaleValue() != null) {
                                    deviceDataItemScaling.setFlagScaleValue(scaling.getFlagScaleValue());
                                } else {
                                    deviceDataItemScaling.setFlagScaleValue(0);
                                }
                                if (scaling.getRawMinValue() != null) {
                                    deviceDataItemScaling.setRawMinValue(scaling.getRawMinValue());
                                } else {
                                    deviceDataItemScaling.setRawMinValue(null);
                                }
                                if (scaling.getRawMaxValue() != null) {
                                    deviceDataItemScaling.setRawMaxValue(scaling.getRawMaxValue());
                                } else {
                                    deviceDataItemScaling.setRawMaxValue(null);
                                }
                                if (scaling.getScaledMinValue() != null) {
                                    deviceDataItemScaling.setScaledMinValue(scaling.getScaledMinValue());
                                } else {
                                    deviceDataItemScaling.setScaledMinValue(null);
                                }
                                if (scaling.getScaledMaxValue() != null) {
                                    deviceDataItemScaling.setScaledMaxValue(scaling.getScaledMaxValue());
                                } else {
                                    deviceDataItemScaling.setScaledMaxValue(null);
                                }
                                if (scaling.getFlagSetValueToMinIfBelowMin() != null) {
                                    deviceDataItemScaling.setFlagSetValueToMinIfBelowMin(scaling.getFlagSetValueToMinIfBelowMin());
                                } else {
                                    deviceDataItemScaling.setFlagSetValueToMinIfBelowMin(0);
                                }
                                if (scaling.getFlagSetValueToMaxIfAboveMax() != null) {
                                    deviceDataItemScaling.setFlagSetValueToMaxIfAboveMax(scaling.getFlagSetValueToMaxIfAboveMax());
                                } else {
                                    deviceDataItemScaling.setFlagSetValueToMaxIfAboveMax(0);
                                }
                                if (scaling.getFlagSaveAsFixedValue() != null) {
                                    deviceDataItemScaling.setFlagSaveAsFixedValue(scaling.getFlagSaveAsFixedValue());
                                } else {
                                    deviceDataItemScaling.setFlagSaveAsFixedValue(0);
                                }
                                if (scaling.getFixedValue() != null) {
                                    deviceDataItemScaling.setFixedValue(scaling.getFixedValue());
                                } else {
                                    deviceDataItemScaling.setFixedValue(null);
                                }
                            }
                            if (ddis.getWarningLevels() != null) {
                                WarningLevels warningLevels = ddis.getWarningLevels();
                                if (warningLevels.getWarningLowValue() != null) {
                                    deviceDataItemScaling.setWarningLowValue(warningLevels.getWarningLowValue());
                                } else {
                                    deviceDataItemScaling.setWarningLowValue(null);
                                }
                                if (warningLevels.getWarningLowEventTypeID() != null) {
                                    deviceDataItemScaling.setWarningLowEventTypeId(warningLevels.getWarningLowEventTypeID());
                                } else {
                                    deviceDataItemScaling.setWarningLowEventTypeId(null);
                                }
                                if (warningLevels.getWarningHighValue() != null) {
                                    deviceDataItemScaling.setWarningHighValue(warningLevels.getWarningHighValue());
                                } else {
                                    deviceDataItemScaling.setWarningHighValue(null);
                                }
                                if (warningLevels.getWarningHighEventTypeID() != null) {
                                    deviceDataItemScaling.setWarningHighEventTypeId(warningLevels.getWarningHighEventTypeID());
                                } else {
                                    deviceDataItemScaling.setWarningHighEventTypeId(null);
                                }
                            }
                            if (ddis.getAlarmLevels() != null) {
                                AlarmLevels alarmLevels = ddis.getAlarmLevels();
                                if (alarmLevels.getAlarmLowValue() != null) {
                                    deviceDataItemScaling.setAlarmLowValue(alarmLevels.getAlarmLowValue());
                                } else {
                                    deviceDataItemScaling.setAlarmLowValue(null);
                                }
                                if (alarmLevels.getAlarmLowEventTypeID() != null) {
                                    deviceDataItemScaling.setAlarmLowEventTypeId(alarmLevels.getAlarmLowEventTypeID());
                                } else {
                                    deviceDataItemScaling.setAlarmLowEventTypeId(null);
                                }
                                if (alarmLevels.getAlarmHighValue() != null) {
                                    deviceDataItemScaling.setAlarmHighValue(alarmLevels.getAlarmHighValue());
                                } else {
                                    deviceDataItemScaling.setAlarmHighValue(null);
                                }
                                if (alarmLevels.getAlarmHighEventTypeID() != null) {
                                    deviceDataItemScaling.setAlarmHighEventTypeId(alarmLevels.getAlarmHighEventTypeID());
                                } else {
                                    deviceDataItemScaling.setAlarmHighEventTypeId(null);
                                }
                            }
                            if (ddis.getProductLink() != null) {
                                ProductLink productLink = ddis.getProductLink();
                                if (productLink.getProductVariantID() != null) {
                                    deviceDataItemScaling.setProductVariantId(productLink.getProductVariantID());
                                } else {
                                    deviceDataItemScaling.setProductVariantId(null);
                                }
                                if (productLink.getOrderMoreBelowThisValue() != null) {
                                    deviceDataItemScaling.setOrderMoreBelowThisValue(productLink.getOrderMoreBelowThisValue());
                                } else {
                                    deviceDataItemScaling.setOrderMoreBelowThisValue(null);
                                }
                                if (productLink.getOrderMoreEventTypeID() != null) {
                                    deviceDataItemScaling.setOrderMoreEventTypeId(productLink.getOrderMoreEventTypeID());
                                } else {
                                    deviceDataItemScaling.setOrderMoreEventTypeId(null);
                                }
                            }
                            deviceDataItemScaling.setLastUpdatedByUserId(sessions2.getUserId());
                            deviceDataItemScaling.setCreatedDate(gc.getTime());
                            deviceDataItemScaling.setUpdatedDate(gc.getTime());
                            deviceDAO.saveDeviceDataItemScaling(deviceDataItemScaling, session);
                            List<DataItemTranslationsPerDevice> dataItemTransPerDeviceList = deviceDAO.getDataItemTranslationsPerDevice(ddis.getDeviceID(), ddis.getDeviceDataItemID(), session);
                            if (!dataItemTransPerDeviceList.isEmpty()) {
                                for (DataItemTranslationsPerDevice dits : dataItemTransPerDeviceList) {
                                    deviceDAO.deleteDataItemTranslationsPerDevice(dits, session);
                                }
                            }
                            session.flush();
                            if (ddis.getDataItemTranslationsPerDevice() != null) {
                                List<se.info24.devicejaxbPost.DataItemTranslation> dataItemTransList = ddis.getDataItemTranslationsPerDevice().getDataItemTranslation();
                                for (se.info24.devicejaxbPost.DataItemTranslation ditpd : dataItemTransList) {
                                    DataItemTranslationsPerDevice ditranspd = new DataItemTranslationsPerDevice();
                                    ditranspd.setId(new DataItemTranslationsPerDeviceId(ddis.getDeviceID(), ddis.getDeviceDataItemID(), ditpd.getCountryID()));
                                    ditranspd.setDataItemName(ditpd.getDataItemName());
                                    if (ditpd.getUnit() != null) {
                                        ditranspd.setUnit(ditpd.getUnit());
                                    }
                                    ditranspd.setLastUpdatedByUserId(sessions2.getUserId());
                                    ditranspd.setCreatedDate(gc.getTime());
                                    ditranspd.setUpdatedDate(gc.getTime());
                                    deviceDAO.saveDataItemTranslationsPerDevice(ditranspd, session);
                                }
                            }
                        }
                        tx.commit();
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("DeviceDataItemScaling not found in XML");
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
            if (tx != null) {
                tx.rollback();
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("Error occured");
            }
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice updateDeviceStatus(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session operationsSession = null;
        TingcoDevice tingcoDevice = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.UPDATE);
                if (hasPermission) {
                    operationsSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    GregorianCalendar gc = new GregorianCalendar();
                    se.info24.devicejaxbPost.TingcoDevice tingcoDevices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    if (!tingcoDevices.getDevices().getDevice().isEmpty()) {
                        se.info24.devicejaxbPost.Device d = tingcoDevices.getDevices().getDevice().get(0);
                        if (d != null) {
                            DeviceStatus de = deviceDAO.getDeviceStatusByDeviceId(d.getDeviceID(), operationsSession);
                            if (d.getDeviceStatus() != null) {
                                if (de != null) {
                                    if (d.getDeviceStatus().getPosLatitude() != null) {
                                        de.setPosLatitude(d.getDeviceStatus().getPosLatitude());
                                    }
                                    if (d.getDeviceStatus().getPosLongitude() != null) {
                                        de.setPosLongitude(d.getDeviceStatus().getPosLongitude());
                                    }
                                    if (d.getDeviceStatus().getPosDepth() != null) {
                                        de.setPosDepth(d.getDeviceStatus().getPosDepth());
                                    }
                                    if (d.getDeviceStatus().getPosDirection() != null) {
                                        de.setPosDirection(Integer.valueOf(d.getDeviceStatus().getPosDirection()));
                                    }
                                    if (d.getDeviceStatus().getCoordinateSystemID() != null) {
                                        de.setCoordinateSystemId(d.getDeviceStatus().getCoordinateSystemID());
                                    }
                                    de.setUpdatedDate(gc.getTime());
                                    if (!deviceDAO.saveOrUpdateDeviceStatus(de, operationsSession)) {
                                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                                        tingcoDevice.getMsgStatus().setResponseText("Error Occured while Updating");
                                    }
                                } else {
                                    DeviceStatus des = new DeviceStatus();
                                    des.setDeviceId(d.getDeviceID());
                                    des.setDataItemId(UUID.randomUUID().toString());
                                    des.setDataItemTime(gc.getTime());
                                    des.setGroupId(d.getGroupID().getValue());
                                    des.setIsEnabled(1);
                                    if (d.getDeviceStatus().getPosLatitude() != null) {
                                        des.setPosLatitude(d.getDeviceStatus().getPosLatitude());
                                    }
                                    if (d.getDeviceStatus().getPosLongitude() != null) {
                                        des.setPosLongitude(d.getDeviceStatus().getPosLongitude());
                                    }
                                    if (d.getDeviceStatus().getPosDepth() != null) {
                                        des.setPosDepth(d.getDeviceStatus().getPosDepth());
                                    }
                                    des.setPosDirection(0);
                                    des.setCoordinateSystemId("WGS84");
                                    des.setMsgId(UUID.randomUUID().toString());
                                    des.setMsgTimeStamp(gc.getTime());
                                    des.setCreatedDate(gc.getTime());
                                    des.setUpdatedDate(gc.getTime());
                                    if (!deviceDAO.saveOrUpdateDeviceStatus(des, operationsSession)) {
                                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                                        tingcoDevice.getMsgStatus().setResponseText("Error Occured while adding new DeviceStatus");
                                    }
                                }
                            } else {
                                if (de != null) {
                                    if (!deviceDAO.deleteDeviceStatus(de, operationsSession)) {
                                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                                        tingcoDevice.getMsgStatus().setResponseText("Error Occured while deleting DeviceStatus");
                                    }
                                }
                            }
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Device not Found in XML");
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
        } finally {
            if (operationsSession != null) {
                operationsSession.close();
            }
            delayLog(requestedTime);
        }

        return tingcoDevice;
    }

    private TingcoDevice updateObjectContactMembership(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.UPDATE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    se.info24.devicejaxbPost.TingcoDevice tingcoDevices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    if (!tingcoDevices.getDevices().getDevice().isEmpty()) {
                        se.info24.devicejaxbPost.Device d = tingcoDevices.getDevices().getDevice().get(0);
                        if (d.getDeviceID() != null && d.getObjectContactID() != null) {
                            String objectId = d.getDeviceID();
                            String objectcontactId = d.getObjectContactID();
                            List<ObjectContactMemberships> objectContactMembershipsList = deviceDAO.getObjectContactMembershipsbyIdandIsDefault(objectId, session);
                            if (!objectContactMembershipsList.isEmpty()) {
                                if (deviceDAO.deleteObjectContactMemberships(objectContactMembershipsList, session)) {
                                    if (!deviceDAO.insertObjectContactMemberships(objectId, objectcontactId, session, sessions2.getUserId())) {
                                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                                        tingcoDevice.getMsgStatus().setResponseText("Error occured while Inserting new ObjectContactMemberships");
                                        return tingcoDevice;
                                    }
                                } else {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("Error occured while Deleting ObjectContactMemberships");
                                    return tingcoDevice;
                                }
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Data Found with the given objectId");
                                return tingcoDevice;
                            }
                        }
//                        else {
//                            tingcoDevice.getMsgStatus().setResponseCode(-1);
//                            tingcoDevice.getMsgStatus().setResponseText("Both objectId and objectContactId are required");
//                            return tingcoDevice;
//                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Invalid XML found");
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
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

//    private TingcoCommands updateSMSCommands(String commandId, String deviceId, String targetString, String deviceTypeCommandId) {
//        long requestedTime = System.currentTimeMillis();
//        TingcoCommands tingcoCommands = null;
//        Session session = null;
//        try {
//            tingcoCommands = tingcoDeviceXML.buildTingcoCommandsTemplate();
//            if (request.getAttribute("USERSESSION") != null) {
//                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
//                boolean hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.UPDATE);
//                if (hasPermission) {
//                    session = HibernateUtil.getSessionFactory().openSession();
//                    Transaction tx = session.beginTransaction();
//                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
//                    List<CommandParseTargets> commandParseTargetsList = deviceDAO.getCommandParseTargets(commandId, deviceId, session);
//                    CommandParseTargets commandParseTargets = commandParseTargetsList.get(0);
//                    CommandParseTargetsId commandParseTargetsId = commandParseTargets.getId();
//                    commandParseTargetsId.setTargetString(targetString);
////                    commandParseTargets.setId(new CommandParseTargetsId(commandId, targetString));
//                    commandParseTargets.setDeviceTypeCommands(new DeviceTypeCommands(deviceTypeCommandId));
//                    commandParseTargets.setDevice(new se.info24.pojo.Device(deviceId));
//                    commandParseTargets.setUpdatedDate(gc.getTime());
//                    session.saveOrUpdate(commandParseTargets);
//                    tx.commit();
//                } else {
//                    tingcoCommands.getMsgStatus().setResponseCode(-10);
//                    tingcoCommands.getMsgStatus().setResponseText("User Permission is not given");
//                    return tingcoCommands;
//                }
//            } else {
//                tingcoCommands.getMsgStatus().setResponseCode(-3);
//                tingcoCommands.getMsgStatus().setResponseText("User Session is Not Valid");
//                return tingcoCommands;
//            }
//        } catch (Exception ex) {
//            TCMUtil.exceptionLog(getClass().getName(), ex);
//            tingcoCommands.getMsgStatus().setResponseCode(-1);
//            tingcoCommands.getMsgStatus().setResponseText("Error occured");
//            return tingcoCommands;
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//            delayLog(requestedTime);
//        }
//        return tingcoCommands;
//    }
    private TingcoDevice updateServiceDeviceSetting(String serviceDeviceSettingId, String serviceDeviceSettingValue) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (serviceDeviceSettingId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("ServiceDeviceSettingId should not be null");
                    return tingcoDevice;
                }

                if (serviceDeviceSettingValue == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("ServiceDeviceSettingValue should not be null");
                    return tingcoDevice;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.UPDATE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    GregorianCalendar gc = new GregorianCalendar();
                    ServiceDeviceSettings serviceDeviceSettings = deviceDAO.getServiceDeviceSettings(serviceDeviceSettingId, session);
                    serviceDeviceSettingValue = TCMUtil.convertHexToString(serviceDeviceSettingValue);
                    if (serviceDeviceSettings != null) {
                        serviceDeviceSettings.setServiceDeviceSettingValue(serviceDeviceSettingValue);
                        serviceDeviceSettings.setActiveFromDate(gc.getTime());
                        serviceDeviceSettings.setUserId(sessions2.getUserId());
                        serviceDeviceSettings.setUpdatedDate(gc.getTime());
                        if (!deviceDAO.saveServiceDeviceSetting(serviceDeviceSettings, session)) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error Occured while Updating");
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Record Found");
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

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }

    private TingcoDevice updateUserFavoriteDataItems(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        Transaction tx = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.UPDATE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    se.info24.devicejaxbPost.TingcoDevice tingcoDevices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    if (!tingcoDevices.getDevices().getDevice().isEmpty()) {
                        se.info24.devicejaxbPost.DeviceDataItems deviceDataItems = tingcoDevices.getDevices().getDevice().get(0).getDeviceDataItems().get(0);
                        List<UserFavoriteDataItems> userFavoritedeviceDataItemsList = deviceDAO.getUserFavoriteDataItems(deviceDataItems.getUserID(), session);
                        for (UserFavoriteDataItems ufdi : userFavoritedeviceDataItemsList) {
                            session.delete(ufdi);
                            if (userFavoritedeviceDataItemsList.size() % 20 == 0) {
                                session.flush();
                                session.clear();
                            }
                        }
                        UserFavoriteDataItems ufdi;
                        GregorianCalendar gc = new GregorianCalendar();
                        for (String deviceDataItemId : deviceDataItems.getDeviceDataItemID()) {
                            ufdi = new UserFavoriteDataItems(new UserFavoriteDataItemsId(deviceDataItems.getUserID(), deviceDataItemId), null, gc.getTime(), gc.getTime());
                            session.save(ufdi);
                            if (deviceDataItemId.length() % 20 == 0) {
                                session.flush();
                                session.clear();
                            }
                        }

                        tx.commit();
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Invalid XML found");
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
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
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

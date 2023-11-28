/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.network;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Hashtable;
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
import se.info24.device.DeviceDAO;
import se.info24.group.GroupDAO;
import se.info24.jaxbUtil.TingcoNetworkSubscriptionsXML;
import se.info24.jaxbUtil.TingcoUtilsXML;
import se.info24.networkjaxb.TingcoNetworkSubscriptions;
import se.info24.pojo.Agreements;
import se.info24.pojo.ContactGroups;
import se.info24.pojo.ContactGroupsInContacts;
import se.info24.pojo.CostCenters;
import se.info24.pojo.Device;
import se.info24.pojo.EventActionSchedules;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupTrusts;
import se.info24.pojo.Groups;
import se.info24.pojo.NetworkSettingsTemplate;
import se.info24.pojo.NetworkSubscriptionSettings;
import se.info24.pojo.NetworkSubscriptionStatuses;
import se.info24.pojo.NetworkSubscriptions;
import se.info24.pojo.NetworkSubscriptionsPendingDelete;
import se.info24.pojo.NetworkTypes;
import se.info24.pojo.Networks;
import se.info24.pojo.ObjectCostCenters;
import se.info24.pojo.ObjectCostCentersId;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.UserTimeZones2;
import se.info24.pojo.Users2;
import se.info24.restUtil.RestUtilDAO;
import se.info24.user.UserDAO;
import se.info24.user.User_LoginsResource;
import se.info24.util.HibernateUtil;
import se.info24.util.JAXBManager;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;
import se.info24.utiljaxb.TingcoUtils;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/network")
public class NetworksResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoUtilsXML tuXML = new TingcoUtilsXML();
    NetworkDAO networkDAO = new NetworkDAO();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of NetworksResource */
    public NetworksResource() {
    }

    /**
     * Network_Add
     * @param networkName
     * @param networkDesc
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/add/rest/{rest}/networkname/{networkname}{networkdesc:(/networkdesc/[^/]+?)?}")
    @RESTDoc(methodName = "Network_Add", desc = "Used to create a new Network", req_Params = {"NetworkName;String"}, opt_Params = {"NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_Add(@PathParam("networkname") String networkName, @PathParam("networkdesc") String networkDesc) {
        return createNetwork(networkName, networkDesc);
    }

    /**
     * Network_Add
     * @param networkName
     * @param networkDesc
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/add/rest/{rest}/networkname/{networkname}{networkdesc:(/networkdesc/[^/]+?)?}")
    public TingcoUtils postXml_Add(@PathParam("networkname") String networkName, @PathParam("networkdesc") String networkDesc) {
        return createNetwork(networkName, networkDesc);
    }

    /**
     * Network_Add
     * @param networkName
     * @param networkDesc
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/add/json/{json}/networkname/{networkname}{networkdesc:(/networkdesc/[^/]+?)?}")
    public TingcoUtils getJson_Add(@PathParam("networkname") String networkName, @PathParam("networkdesc") String networkDesc) {
        return createNetwork(networkName, networkDesc);
    }

    /**
     * Network_Add
     * @param networkName
     * @param networkDesc
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/add/json/{json}/networkname/{networkname}{networkdesc:(/networkdesc/[^/]+?)?}")
    public TingcoUtils postJson_Add(@PathParam("networkname") String networkName, @PathParam("networkdesc") String networkDesc) {
        return createNetwork(networkName, networkDesc);
    }

    /**
     * Network_Delete
     * @param networkID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/networkid/{networkid}")
    @RESTDoc(methodName = "Network_Delete", desc = "To Delete a Network", req_Params = {"NetworkID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_Delete(@PathParam("networkid") String networkID) {
        return deleteNetwork(networkID);
    }

    /**
     * Network_Delete
     * @param networkID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/networkid/{networkid}")
    public TingcoUtils postXml_Delete(@PathParam("networkid") String networkID) {
        return deleteNetwork(networkID);
    }

    /**
     * Network_Delete
     * @param networkID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/delete/json/{json}/networkid/{networkid}")
    public TingcoUtils getJson_Delete(@PathParam("networkid") String networkID) {
        return deleteNetwork(networkID);
    }

    /**
     * Network_Delete
     * @param networkID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/delete/json/{json}/networkid/{networkid}")
    public TingcoUtils postJson_Delete(@PathParam("networkid") String networkID) {
        return deleteNetwork(networkID);
    }

    /**
     * Network_Update
     * @param networkID
     * @param networkName
     * @param networkDesc
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/update/rest/{rest}/networkid/{networkid}{networkname:(/networkname/[^/]+?)?}{networkdesc:(/networkdesc/[^/]+?)?}")
    @RESTDoc(methodName = "Network_Update", desc = "To Update a Network", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_Update(@PathParam("networkid") String networkID, @PathParam("networkname") String networkName, @PathParam("networkdesc") String networkDesc) {
        return updateNetwork(networkID, networkName, networkDesc);
    }

    /**
     * Network_Update
     * @param networkID
     * @param networkName
     * @param networkDesc
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/update/rest/{rest}/networkid/{networkid}{networkname:(/networkname/[^/]+?)?}{networkdesc:(/networkdesc/[^/]+?)?}")
    public TingcoUtils postXml_Update(@PathParam("networkid") String networkID, @PathParam("networkname") String networkName, @PathParam("networkdesc") String networkDesc) {
        return updateNetwork(networkID, networkName, networkDesc);
    }

    /**
     * Network_Update
     * @param networkID
     * @param networkName
     * @param networkDesc
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/update/json/{json}/networkid/{networkid}{networkname:(/networkname/[^/]+?)?}{networkdesc:(/networkdesc/[^/]+?)?}")
    public TingcoUtils getJson_Update(@PathParam("networkid") String networkID, @PathParam("networkname") String networkName, @PathParam("networkdesc") String networkDesc) {
        return updateNetwork(networkID, networkName, networkDesc);
    }

    /**
     * Network_Update
     * @param networkID
     * @param networkName
     * @param networkDesc
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/update/json/{json}/networkid/{networkid}{networkname:(/networkname/[^/]+?)?}{networkdesc:(/networkdesc/[^/]+?)?}")
    public TingcoUtils postJson_Update(@PathParam("networkid") String networkID, @PathParam("networkname") String networkName, @PathParam("networkdesc") String networkDesc) {
        return updateNetwork(networkID, networkName, networkDesc);
    }

    /**
     * GetNetworkSubscriptions
     * @param deviceId
     * @param countryId
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/xml")
    @Path("/getnetworksubscriptions/rest/{rest}/deviceid/{deviceid}/countryid/{countryid}{networksubscriptionname:(/networksubscriptionname/[^/]+?)?}{subscriptionnumber:(/subscriptionnumber/[^/]+?)?}{iccid:(/iccid/[^/]+?)?}")
    @RESTDoc(methodName = "GetNetworkSubscriptions ", desc = "To Get NetworkSubscriptions ", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getXml_NetworkSubscriptions(@PathParam("deviceid") String deviceId, @PathParam("countryid") String countryId, @PathParam("networksubscriptionname") String networkSubscriptionName, @PathParam("subscriptionnumber") String subscriptionNumber, @PathParam("iccid") String iccid) {
        return getNetworkSubscriptions(deviceId, countryId, networkSubscriptionName, subscriptionNumber, iccid);
    }

    /**
     * GetNetworkSubscriptions
     * @param deviceId
     * @param countryId
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/json")
    @Path("/getnetworksubscriptions/json/{json}/deviceid/{deviceid}/countryid/{countryid}{networksubscriptionname:(/networksubscriptionname/[^/]+?)?}{subscriptionnumber:(/subscriptionnumber/[^/]+?)?}{iccid:(/iccid/[^/]+?)?}")
    @RESTDoc(methodName = "GetNetworkSubscriptions ", desc = "To Get NetworkSubscriptions ", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getJson_NetworkSubscriptions(@PathParam("deviceid") String deviceId, @PathParam("countryid") String countryId, @PathParam("networksubscriptionname") String networkSubscriptionName, @PathParam("subscriptionnumber") String subscriptionNumber, @PathParam("iccid") String iccid) {
        return getNetworkSubscriptions(deviceId, countryId, networkSubscriptionName, subscriptionNumber, iccid);
    }

    /**
     * GetNetworkSubscriptions
     * @param deviceId
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/xml")
    @Path("/getdevicenetworksubscription/rest/{rest}/deviceid/{deviceid}")
    @RESTDoc(methodName = "GetNetworkSubscriptions ", desc = "To Get NetworkSubscriptions ", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getXml_GetDeviceNetworkSubscription(@PathParam("deviceid") String deviceId) {
        return getDeviceNetworkSubscription(deviceId);
    }

    /**
     * GetNetworkSubscriptions
     * @param deviceId
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/json")
    @Path("/getdevicenetworksubscription/json/{json}/deviceid/{deviceid}")
    @RESTDoc(methodName = "GetNetworkSubscriptions ", desc = "To Get NetworkSubscriptions ", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getJson_GetDeviceNetworkSubscription(@PathParam("deviceid") String deviceId) {
        return getDeviceNetworkSubscription(deviceId);
    }

    /**
     * GetNetworkSubscriptionSettings
     * @param networksubscriptionId
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/xml")
    @Path("/getnetworksubscriptionsettings/rest/{rest}/networksubscriptionid/{networksubscriptionid}")
    @RESTDoc(methodName = "GetNetworkSubscriptionSettings ", desc = "To Get NetworkSubscriptionSettings ", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getXml_GetNetworkSubscriptionSettings(@PathParam("networksubscriptionid") String networksubscriptionId) {
        return getNetworkSubscriptionSettings(networksubscriptionId);
    }

    /**
     * GetNetworkSubscriptionSettings
     * @param networksubscriptionId
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/json")
    @Path("/getnetworksubscriptionsettings/json/{json}/networksubscriptionid/{networksubscriptionid}")
    @RESTDoc(methodName = "GetNetworkSubscriptionSettings ", desc = "To Get NetworkSubscriptionSettings ", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getJson_GetNetworkSubscriptionSettings(@PathParam("networksubscriptionid") String networksubscriptionId) {
        return getNetworkSubscriptionSettings(networksubscriptionId);
    }

    /**
     * GetNonLinkedNetworkSubscriptionForDevice
     * @param countryId
     * @param groupId
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/xml")
    @Path("/getnonlinkednetworksubscriptionfordevice/rest/{rest}/countryid/{countryid}/groupid/{groupid}{networksubscriptionname:(/networksubscriptionname/[^/]+?)?}{subscriptionnumber:(/subscriptionnumber/[^/]+?)?}{iccid:(/iccid/[^/]+?)?}")
    @RESTDoc(methodName = "GetNonLinkedNetworkSubscriptionForDevice ", desc = "To Get NonLinkedNetworkSubscriptionForDevice ", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getXml_GetNonLinkedNetworkSubscriptionForDevice(@PathParam("countryid") int countryId, @PathParam("groupid") String groupId, @PathParam("networksubscriptionname") String networkSubscriptionName, @PathParam("subscriptionnumber") String subscriptionNumber, @PathParam("iccid") String iccId) {
        return getNonLinkedNetworkSubscriptionForDevice(countryId, groupId, networkSubscriptionName, subscriptionNumber, iccId);
    }

    /**
     * GetNonLinkedNetworkSubscriptionForDevice
     * @param countryId
     * @param groupId
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/json")
    @Path("/getnonlinkednetworksubscriptionfordevice/json/{json}/countryid/{countryid}/groupid/{groupid}{networksubscriptionname:(/networksubscriptionname/[^/]+?)?}{subscriptionnumber:(/subscriptionnumber/[^/]+?)?}{iccid:(/iccid/[^/]+?)?}")
    @RESTDoc(methodName = "GetNonLinkedNetworkSubscriptionForDevice ", desc = "To Get NonLinkedNetworkSubscriptionForDevice ", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getJson_GetNonLinkedNetworkSubscriptionForDevice(@PathParam("countryid") int countryId, @PathParam("groupid") String groupId, @PathParam("networksubscriptionname") String networkSubscriptionName, @PathParam("subscriptionnumber") String subscriptionNumber, @PathParam("iccid") String iccId) {
        return getNonLinkedNetworkSubscriptionForDevice(countryId, groupId, networkSubscriptionName, subscriptionNumber, iccId);
    }

    /**
     *  GetNetworkOperators
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/xml")
    @Path("/getnetworkoperators/rest/{rest}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetNetworkOperators", desc = "To Get NetworkOperators ", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getXml_GetNetworkOperators(@PathParam("searchstring") String searchString) {
        return getNetworkOperators(searchString);
    }

    /**
     *  GetNetworkOperators
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/json")
    @Path("/getnetworkoperators/json/{json}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetNetworkOperators", desc = "To Get NetworkOperators ", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getJson_GetNetworkOperators(@PathParam("searchstring") String searchString) {
        return getNetworkOperators(searchString);
    }

    /**
     *  GetNetworkStatuses
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/xml")
    @Path("/getnetworkstatuses/rest/{rest}")
    @RESTDoc(methodName = "GetNetworkStatuses", desc = "To Get NetworkStatuses", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getXml_GetNetworkStatuses() {
        return getNetworkStatuses();
    }

    /**
     *  GetNetworkStatuses
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/json")
    @Path("/getnetworkstatuses/json/{json}")
    @RESTDoc(methodName = "GetNetworkStatuses", desc = "To Get NetworkStatuses", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getJson_GetNetworkStatuses() {
        return getNetworkStatuses();
    }

    /**
     * RemoveNetworkSubscription
     * @param networkSubscriptionId
     * @param deviceId
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/xml")
    @Path("/removenetworksubscription/rest/{rest}/networksubscriptionid/{networksubscriptionid}{deviceid:(/deviceid/[^/]+?)?}")
    @RESTDoc(methodName = "RemoveNetworkSubscription", desc = "Remove NetworkSubscription", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getXml_RemoveNetworkSubscription(@PathParam("networksubscriptionid") String networkSubscriptionId, @PathParam("deviceid") String deviceId) {
        return removeNetworkSubscription(networkSubscriptionId, deviceId);
    }

    /**
     * RemoveNetworkSubscription
     * @param networkSubscriptionId
     * @param deviceId
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/json")
    @Path("/removenetworksubscription/json/{json}/networksubscriptionid/{networksubscriptionid}{deviceid:(/deviceid/[^/]+?)?}")
    @RESTDoc(methodName = "RemoveNetworkSubscription", desc = "Remove NetworkSubscription", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getJson_RemoveNetworkSubscription(@PathParam("networksubscriptionid") String networkSubscriptionId, @PathParam("deviceid") String deviceId) {
        return removeNetworkSubscription(networkSubscriptionId, deviceId);
    }

    /**
     * GetNetworkSettingTemplates
     * @param networkSubscriptionId
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/xml")
    @Path("/getnetworksettingtemplates/rest/{rest}/networksubscriptionid/{networksubscriptionid}")
    @RESTDoc(methodName = "GetNetworkSettingTemplates", desc = "Get NetworkSettingTemplates", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getXml_NetworkSettingTemplates(@PathParam("networksubscriptionid") String networkSubscriptionId) {
        return getNetworkSettingTemplates(networkSubscriptionId);
    }

    /**
     * GetNetworkSettingTemplates
     * @param networkSubscriptionId
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/json")
    @Path("/getnetworksettingtemplates/json/{json}/networksubscriptionid/{networksubscriptionid}")
    @RESTDoc(methodName = "GetNetworkSettingTemplates", desc = "Get NetworkSettingTemplates", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getJson_NetworkSettingTemplates(@PathParam("networksubscriptionid") String networkSubscriptionId) {
        return getNetworkSettingTemplates(networkSubscriptionId);
    }

    /**
     * AddNewNetworkSubscriptionSetting
     * @param networkSubscriptionID
     * @param networkSubscriptionSettingName
     * @param networkSubscriptionSettingValue
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/xml")
    @Path("/addnewnetworksubscriptionsetting/rest/{rest}/networksubscriptionid/{networksubscriptionid}/networksubscriptionsettingname/{networksubscriptionsettingname}/networksubscriptionsettingvalue/{networksubscriptionsettingvalue}")
    @RESTDoc(methodName = "AddNewNetworkSubscriptionSetting", desc = "Add New NetworkSubscriptionSetting", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getXml_AddNewNetworkSubscriptionSetting(@PathParam("networksubscriptionid") String networkSubscriptionID, @PathParam("networksubscriptionsettingname") String networkSubscriptionSettingName, @PathParam("networksubscriptionsettingvalue") String networkSubscriptionSettingValue) {
        return addNewNetworkSubscriptionSetting(networkSubscriptionID, networkSubscriptionSettingName, networkSubscriptionSettingValue);
    }

    /**
     * AddNewNetworkSubscriptionSetting
     * @param networkSubscriptionID
     * @param networkSubscriptionSettingName
     * @param networkSubscriptionSettingValue
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/json")
    @Path("/addnewnetworksubscriptionsetting/json/{json}/networksubscriptionid/{networksubscriptionid}/networksubscriptionsettingname/{networksubscriptionsettingname}/networksubscriptionsettingvalue/{networksubscriptionsettingvalue}")
    @RESTDoc(methodName = "AddNewNetworkSubscriptionSetting", desc = "Add New NetworkSubscriptionSetting", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getJson_AddNewNetworkSubscriptionSetting(@PathParam("networksubscriptionid") String networkSubscriptionID, @PathParam("networksubscriptionsettingname") String networkSubscriptionSettingName, @PathParam("networksubscriptionsettingvalue") String networkSubscriptionSettingValue) {
        return addNewNetworkSubscriptionSetting(networkSubscriptionID, networkSubscriptionSettingName, networkSubscriptionSettingValue);
    }

    /**
     *  UpdateNetworkSubscriptionSetting
     * @param networkSubscriptionSettingID
     * @param networkSubscriptionSettingName
     * @param networkSubscriptionSettingValue
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/xml")
    @Path("/updatenetworksubscriptionsetting/rest/{rest}/networksubscriptionsettingid/{networksubscriptionsettingid}/networksubscriptionsettingname/{networksubscriptionsettingname}/networksubscriptionsettingvalue/{networksubscriptionsettingvalue}")
    @RESTDoc(methodName = "UpdateNetworkSubscriptionSetting", desc = "Update NetworkSubscriptionSetting", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getXml_UpdateNetworkSubscriptionSetting(@PathParam("networksubscriptionsettingid") String networkSubscriptionSettingID, @PathParam("networksubscriptionsettingname") String networkSubscriptionSettingName, @PathParam("networksubscriptionsettingvalue") String networkSubscriptionSettingValue) {
        return updateNetworkSubscriptionSetting(networkSubscriptionSettingID, networkSubscriptionSettingName, networkSubscriptionSettingValue);
    }

    /**
     *  UpdateNetworkSubscriptionSetting
     * @param networkSubscriptionSettingID
     * @param networkSubscriptionSettingName
     * @param networkSubscriptionSettingValue
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/json")
    @Path("/updatenetworksubscriptionsetting/json/{json}/networksubscriptionsettingid/{networksubscriptionsettingid}/networksubscriptionsettingname/{networksubscriptionsettingname}/networksubscriptionsettingvalue/{networksubscriptionsettingvalue}")
    @RESTDoc(methodName = "UpdateNetworkSubscriptionSetting", desc = "Update NetworkSubscriptionSetting", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getJson_UpdateNetworkSubscriptionSetting(@PathParam("networksubscriptionsettingid") String networkSubscriptionSettingID, @PathParam("networksubscriptionsettingname") String networkSubscriptionSettingName, @PathParam("networksubscriptionsettingvalue") String networkSubscriptionSettingValue) {
        return updateNetworkSubscriptionSetting(networkSubscriptionSettingID, networkSubscriptionSettingName, networkSubscriptionSettingValue);
    }

    /**
     *  DeleteNetworkSubscriptionSetting
     * @param networkSubscriptionSettingID
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/xml")
    @Path("/deletenetworksubscriptionsetting/rest/{rest}/networksubscriptionsettingid/{networksubscriptionsettingid}")
    @RESTDoc(methodName = "DeleteNetworkSubscriptionSetting", desc = "Delete NetworkSubscriptionSetting", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getXml_DeleteNetworkSubscriptionSetting(@PathParam("networksubscriptionsettingid") String networkSubscriptionSettingID) {
        return deleteNetworkSubscriptionSetting(networkSubscriptionSettingID);
    }

    /**
     *  DeleteNetworkSubscriptionSetting
     * @param networkSubscriptionSettingID
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/json")
    @Path("/deletenetworksubscriptionsetting/json/{json}/networksubscriptionsettingid/{networksubscriptionsettingid}")
    @RESTDoc(methodName = "DeleteNetworkSubscriptionSetting", desc = "Delete NetworkSubscriptionSetting", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getjson_DeleteNetworkSubscriptionSetting(@PathParam("networksubscriptionsettingid") String networkSubscriptionSettingID) {
        return deleteNetworkSubscriptionSetting(networkSubscriptionSettingID);
    }

    /**
     *  AddNewContactGroup
     * @param contactGroupName
     * @param contactGroupDescription
     * @param groupID
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/xml")
    @Path("/addnewcontactgroup/rest/{rest}/contactgroupname/{contactgroupname}{contactgroupdescription:(/contactgroupdescription/[^/]+?)?}/groupid/{groupid}")
    @RESTDoc(methodName = "AddNewContactGroup", desc = "Add New ContactGroup", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getXml_AddNewContactGroup(@PathParam("contactgroupname") String contactGroupName, @PathParam("contactgroupdescription") String contactGroupDescription, @PathParam("groupid") String groupID) {
        return addNewContactGroup(contactGroupName, contactGroupDescription, groupID);
    }

    /**
     *  AddNewContactGroup
     * @param contactGroupName
     * @param contactGroupDescription
     * @param groupID
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/json")
    @Path("/addnewcontactgroup/json/{json}/contactgroupname/{contactgroupname}{contactgroupdescription:(/contactgroupdescription/[^/]+?)?}/groupid/{groupid}")
    @RESTDoc(methodName = "AddNewContactGroup", desc = "Add New ContactGroup", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getJson_AddNewContactGroup(@PathParam("contactgroupname") String contactGroupName, @PathParam("contactgroupdescription") String contactGroupDescription, @PathParam("groupid") String groupID) {
        return addNewContactGroup(contactGroupName, contactGroupDescription, groupID);
    }

    /**
     * EditContactGroup
     * @param contactGroupID
     * @param contactGroupName
     * @param contactGroupDescription
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/xml")
    @Path("/editcontactgroup/rest/{rest}/contactgroupid/{contactgroupid}/contactgroupname/{contactgroupname}{contactgroupdescription:(/contactgroupdescription/[^/]+?)?}")
    @RESTDoc(methodName = "EditContactGroup", desc = "Edit ContactGroup", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getXml_EditContactGroup(@PathParam("contactgroupid") String contactGroupID, @PathParam("contactgroupname") String contactGroupName, @PathParam("contactgroupdescription") String contactGroupDescription) {
        return editContactGroup(contactGroupID, contactGroupName, contactGroupDescription);
    }

    /**
     * EditContactGroup
     * @param contactGroupID
     * @param contactGroupName
     * @param contactGroupDescription
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/json")
    @Path("/editcontactgroup/json/{json}/contactgroupid/{contactgroupid}/contactgroupname/{contactgroupname}{contactgroupdescription:(/contactgroupdescription/[^/]+?)?}")
    @RESTDoc(methodName = "EditContactGroup", desc = "Edit ContactGroup", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getJson_EditContactGroup(@PathParam("contactgroupid") String contactGroupID, @PathParam("contactgroupname") String contactGroupName, @PathParam("contactgroupdescription") String contactGroupDescription) {
        return editContactGroup(contactGroupID, contactGroupName, contactGroupDescription);
    }

    /**
     * DeleteContactGroup
     * @param contactGroupID
     * @return  TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/xml")
    @Path("/deletecontactgroup/rest/{rest}/contactgroupid/{contactgroupid}")
    @RESTDoc(methodName = "DeleteContactGroup", desc = "Delete ContactGroup", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getXml_DeleteContactGroup(@PathParam("contactgroupid") String contactGroupID) {
        return deleteContactGroup(contactGroupID);
    }

    /**
     * DeleteContactGroup
     * @param contactGroupID
     * @return  TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/json")
    @Path("/deletecontactgroup/json/{json}/contactgroupid/{contactgroupid}")
    @RESTDoc(methodName = "DeleteContactGroup", desc = "Delete ContactGroup", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getJson_DeleteContactGroup(@PathParam("contactgroupid") String contactGroupID) {
        return deleteContactGroup(contactGroupID);
    }

    /**
     * AddExistingSubscription
     * @param networkSubscriptionID
     * @param deviceID
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/xml")
    @Path("/addexistingsubscription/rest/{rest}/networksubscriptionid/{networksubscriptionid}/deviceid/{deviceid}")
    @RESTDoc(methodName = "AddExistingSubscription", desc = "Add Existing Subscription", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getXml_AddExistingSubscription(@PathParam("networksubscriptionid") String networkSubscriptionID, @PathParam("deviceid") String deviceID) {
        return addExistingSubscription(networkSubscriptionID, deviceID);
    }

    /**
     * AddExistingSubscription
     * @param networkSubscriptionID
     * @param deviceID
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/json")
    @Path("/addexistingsubscription/json/{json}/networksubscriptionid/{networksubscriptionid}/deviceid/{deviceid}")
    @RESTDoc(methodName = "AddExistingSubscription", desc = "Add Existing Subscription", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getJson_AddExistingSubscription(@PathParam("networksubscriptionid") String networkSubscriptionID, @PathParam("deviceid") String deviceID) {
        return addExistingSubscription(networkSubscriptionID, deviceID);
    }

    /**
     * GetContactGroups
     * @param contactGroupName
     * @param groupName
     * @param countryID
     * @param groupID
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/xml")
    @Path("/getcontactgroups/rest/{rest}{contactgroupname:(/contactgroupname/[^/]+?)?}{groupname:(/groupname/[^/]+?)?}/countryid/{countryid}/groupid/{groupid}")
    @RESTDoc(methodName = "GetContactGroups", desc = "Get ContactGroups", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getXml_ContactGroup(@PathParam("contactgroupname") String contactGroupName, @PathParam("groupname") String groupName, @PathParam("countryid") int countryID, @PathParam("groupid") String groupID) {
        return getContactGroups(contactGroupName, groupName, countryID, groupID);
    }

    /**
     * GetContactGroups
     * @param contactGroupName
     * @param groupName
     * @param countryID
     * @param groupID
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/json")
    @Path("/getcontactgroups/json/{json}{contactgroupname:(/contactgroupname/[^/]+?)?}{groupname:(/groupname/[^/]+?)?}/countryid/{countryid}/groupid/{groupid}")
    @RESTDoc(methodName = "GetContactGroups", desc = "Get ContactGroups", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getJson_ContactGroup(@PathParam("contactgroupname") String contactGroupName, @PathParam("groupname") String groupName, @PathParam("countryid") int countryID, @PathParam("groupid") String groupID) {
        return getContactGroups(contactGroupName, groupName, countryID, groupID);
    }

    /**
     * GetNetworkSubscriptionDetails
     * @param deviceID
     * @param countryID
     * @param networkSubscriptionID
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/xml")
    @Path("/getnetworksubscriptiondetails/rest/{rest}{deviceid:(/deviceid/[^/]+?)?}/countryid/{countryid}/networksubscriptionid/{networksubscriptionid}")
    @RESTDoc(methodName = "GetNetworkSubscriptionDetails", desc = "Get NetworkSubscriptionDetails", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getXml_NetworkSubscriptionDetails(@PathParam("deviceid") String deviceID, @PathParam("countryid") int countryID, @PathParam("networksubscriptionid") String networkSubscriptionID) {
        return getNetworkSubscriptionDetails(deviceID, countryID, networkSubscriptionID);
    }

    /**
     * GetNetworkSubscriptionDetails
     * @param deviceID
     * @param countryID
     * @param networkSubscriptionID
     * @return TingcoNetworkSubscriptions
     */
    @GET
    @Produces("application/json")
    @Path("/getnetworksubscriptiondetails/json/{json}{deviceid:(/deviceid/[^/]+?)?}/countryid/{countryid}/networksubscriptionid/{networksubscriptionid}")
    @RESTDoc(methodName = "GetNetworkSubscriptionDetails", desc = "Get NetworkSubscriptionDetails", req_Params = {"NetworkID;UUID"}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getJson_NetworkSubscriptionDetails(@PathParam("deviceid") String deviceID, @PathParam("countryid") int countryID, @PathParam("networksubscriptionid") String networkSubscriptionID) {
        return getNetworkSubscriptionDetails(deviceID, countryID, networkSubscriptionID);
    }

    /**
     *  addnewsubscription
     * @param content
     * @return TingcoNetworkSubscriptions
     */
    @POST
    @Produces("application/xml")
    @Consumes("application/xml")
    @Path("/addnewsubscription/rest/{rest}{istcmv3:(/istcmv3/[^/]+?)?}")
    public TingcoNetworkSubscriptions postXml1_Add(String content, @PathParam("istcmv3") String isTcmv3) {
        return addNewSubscription(content, isTcmv3);
    }

    /**
     *  addnewsubscription
     * @param content
     * @return TingcoNetworkSubscriptions
     */
    @POST
    @Produces("application/json")
    @Consumes("application/xml")
    @Path("/addnewsubscription/json/{json}{istcmv3:(/istcmv3/[^/]+?)?}")
    public TingcoNetworkSubscriptions postJson1_Add(String content, @PathParam("istcmv3") String isTcmv3) {
        return addNewSubscription(content, isTcmv3);
    }

    /**
     * updatenetworksubscription
     * @param content
     * @return TingcoNetworkSubscriptions
     */
    @POST
    @Produces("application/xml")
    @Consumes("application/xml")
    @Path("/updatenetworksubscription/rest/{rest}")
    public TingcoNetworkSubscriptions postXml_Update(String content) {
        return updateNetworkSubscription(content);
    }

    /**
     * updatenetworksubscription
     * @param content
     * @return TingcoNetworkSubscriptions
     */
    @POST
    @Produces("application/json")
    @Consumes("application/xml")
    @Path("/updatenetworksubscription/json/{jsom}")
    public TingcoNetworkSubscriptions postJson_Update(String content) {
        return updateNetworkSubscription(content);
    }

    /*
     * GetNetWorkSubscriptionsBySearchCriteria
     * content
     */
    @POST
    @Produces("application/xml")
    @Consumes("application/xml")
    @Path("/getnetworksubscriptionsbysearchcriteria/rest/{rest}")
    public TingcoNetworkSubscriptions postXml_getNetWorkSubscriptionsBySearchCriteria(String content) {
        return getNetWorkSubscriptionsBySearchCriteria(content);
    }

    /*
     * GetNetWorkSubscriptionsBySearchCriteria
     * content
     */
    @POST
    @Produces("application/json")
    @Consumes("application/xml")
    @Path("/getnetworksubscriptionsbysearchcriteria/json/{json}")
    public TingcoNetworkSubscriptions postJson_getNetWorkSubscriptionsBySearchCriteria(String content) {
        return getNetWorkSubscriptionsBySearchCriteria(content);
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public NetworkResource getNetworkResource() {
        return new NetworkResource();
    }

    /**
     * GetAllNetworkSubscriptions
     * @param searchString
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getallnetworksubscriptions/rest/{rest}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetAllNetworkSubscriptions", desc = "Get All NetworkSubscriptions", req_Params = {""}, opt_Params = {"NetworkName;String", "NetworkDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoNetworkSubscriptions getXml_GetAllNetworkSubscriptions(@PathParam("searchstring") String searchString) {
        return getAllNetworkSubscriptions(searchString);
    }

    /**
     * GetAllNetworkSubscriptions
     * @param searchString
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getallnetworksubscriptions/json/{json}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoNetworkSubscriptions getJson_GetAllNetworkSubscriptions(@PathParam("searchstring") String searchString) {
        return getAllNetworkSubscriptions(searchString);
    }

    private TingcoNetworkSubscriptions getAllNetworkSubscriptions(String searchString) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoNetworkSubscriptions tingcoNetworkSubscriptions = null;
        TingcoNetworkSubscriptionsXML tingcoNetworkSubscriptionsXML = new TingcoNetworkSubscriptionsXML();
        Session session = null;
        try {
            tingcoNetworkSubscriptions = tingcoNetworkSubscriptionsXML.buildTingcoNetworkSubscriptionsTemplate();
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
                if (!searchString.equals("")) {
                    searchString = searchString.split("/")[2];
                } else {
                    searchString = null;
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserDAO userDAO = new UserDAO();
                    Users2 users2 = userDAO.getUserById(sessions2.getUserId(), session);
                    GroupDAO groupDAO = new GroupDAO();
                    List<GroupTrusts> groupTrusts = groupDAO.getGroupTrustByGroupID(users2.getGroupId(), session);
                    Set<String> groupTrustedIDSet = groupDAO.getGroupIdsList(users2.getGroupId(), groupTrusts);
                    List<List<String>> splitList = TCMUtil.splitStringList(new ArrayList(groupTrustedIDSet), 2000);
                    List<NetworkSubscriptions> networkSubscriptionses = new ArrayList<NetworkSubscriptions>();
                    if (searchString != null) {
                        for (List<String> list : splitList) {
                            List<NetworkSubscriptions> temp = networkDAO.getNetworkSubscriptionsBySearchString(searchString, list, session);
                            networkSubscriptionses.addAll(temp);
                        }
                        if (networkSubscriptionses.isEmpty()) {
                            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("No Data Found");
                            return tingcoNetworkSubscriptions;
                        }
                        Set<String> networkSubIDs = new HashSet<String>();
                        for (NetworkSubscriptions ns : networkSubscriptionses) {
                            networkSubIDs.add(ns.getNetworkSubscriptionId());
                        }
                        List<NetworkSubscriptions> nsList = networkDAO.getNetworkSubscriptionsOrderByNetworkSubscriptionName(networkSubIDs, session, 500);
                        tingcoNetworkSubscriptions = tingcoNetworkSubscriptionsXML.buildGetAllNetworkSubscriptions(tingcoNetworkSubscriptions, nsList);
                    } else {
                        for (List<String> list : splitList) {
                            List<NetworkSubscriptions> temp = networkDAO.getNetworkSubscriptionsByGroupIDs(list, session);
                            networkSubscriptionses.addAll(temp);
                        }
                        if (networkSubscriptionses.isEmpty()) {
                            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("No Data Found");
                            return tingcoNetworkSubscriptions;
                        }
                        Set<String> networkSubIDs = new HashSet<String>();
                        for (NetworkSubscriptions ns : networkSubscriptionses) {
                            networkSubIDs.add(ns.getNetworkSubscriptionId());
                        }
                        List<NetworkSubscriptions> nsList = networkDAO.getNetworkSubscriptionsOrderByNetworkSubscriptionName(networkSubIDs, session, 500);
                        tingcoNetworkSubscriptions = tingcoNetworkSubscriptionsXML.buildGetAllNetworkSubscriptions(tingcoNetworkSubscriptions, nsList);
                    }
                } else {
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-10);
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoNetworkSubscriptions;
                }
            } else {
                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-3);
                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoNetworkSubscriptions;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoNetworkSubscriptions;
    }

    private TingcoNetworkSubscriptions getNetWorkSubscriptionsBySearchCriteria(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoNetworkSubscriptions tingcoNetworkSubscriptions = null;
        TingcoNetworkSubscriptionsXML tingcoNetworkSubscriptionsXML = new TingcoNetworkSubscriptionsXML();
        Session session = null;
        try {
            tingcoNetworkSubscriptions = tingcoNetworkSubscriptionsXML.buildTingcoNetworkSubscriptionsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.NETWORKS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.NETWORKS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    se.info24.networkjaxb.TingcoNetworkSubscriptions networkSubscriptions = (se.info24.networkjaxb.TingcoNetworkSubscriptions) JAXBManager.getInstance().unMarshall(content, se.info24.networkjaxb.TingcoNetworkSubscriptions.class);
                    if (networkSubscriptions.getNetworkSubscriptions().getNetworkSubscription().size() > 0) {
                        se.info24.networkjaxb.NetworkSubscription networkSubscription = networkSubscriptions.getNetworkSubscriptions().getNetworkSubscription().get(0);
                        List<Object> networkSubscriptionIdsObjectList = networkDAO.getNetworkSubscriptionsBySearchCriteria(networkSubscription, session);
                        if (!networkSubscriptionIdsObjectList.isEmpty()) {
                            Set<String> networkSubscriptionIdsList = networkDAO.getDeviceIdsListObject(networkSubscriptionIdsObjectList);
                            List<NetworkSubscriptions> networkSubscriptionsList = networkDAO.getNetworkSubscriptionsByIds(session, new HashSet(networkSubscriptionIdsList));

                            tingcoNetworkSubscriptions = tingcoNetworkSubscriptionsXML.buildGetNetworkSubscriptionsBySearchCriteria(tingcoNetworkSubscriptions, networkSubscriptionsList, networkSubscription.getCountryID(), networkSubscription.getGroupID().getValue(), session);
                        } else {
                            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("No Data Found");
                            return tingcoNetworkSubscriptions;
                        }
                    } else {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Invalid NetworkSubscription XML Found");
                        return tingcoNetworkSubscriptions;
                    }
                } else {
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-10);
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoNetworkSubscriptions;
                }
            } else {
                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-3);
                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoNetworkSubscriptions;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoNetworkSubscriptions;
    }

    private TingcoNetworkSubscriptions getNetworkSubscriptionDetails(String deviceID, int countryID, String networkSubscriptionID) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoNetworkSubscriptions tingcoNetworkSubscriptions = null;
        TingcoNetworkSubscriptionsXML tingcoNetworkSubscriptionsXML = new TingcoNetworkSubscriptionsXML();
        Session session = null;
        try {
            tingcoNetworkSubscriptions = tingcoNetworkSubscriptionsXML.buildTingcoNetworkSubscriptionsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.NETWORKS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.NETWORKS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    if (!deviceID.equals("")) {
                        deviceID = deviceID.split("/")[2];
                    } else {
                        deviceID = null;
                    }
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {

                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        if (deviceID != null) {
                            Object networksubscription = networkDAO.getNetworkSubscriptionsByIdAnddDeviceIds(session, networkSubscriptionID, deviceID);
                            if (networksubscription == null) {
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Data Not Found");
                                return tingcoNetworkSubscriptions;
                            }
                            NetworkSubscriptions ns = (NetworkSubscriptions) networksubscription;
                            GroupDAO groupDAO = new GroupDAO();
                            GroupTranslations gt = groupDAO.getGroupTranslationsByIds(ns.getGroups().getGroupId(), countryID, session);
                            NetworkSubscriptionsPendingDelete nspd = networkDAO.getNetworkSubscriptionsPendingDeleteBySubscriptionId(session, networkSubscriptionID);
                            CostCenters cc = null;
                            List<ObjectCostCenters> objectCostCenterses = networkDAO.getObjectCostCentersByGroupIdandObjectId(session, ns.getNetworkSubscriptionId(), ns.getGroups().getGroupId());
                            if (!objectCostCenterses.isEmpty()) {
                                cc = groupDAO.getCostCenterById(objectCostCenterses.get(0).getCostCenterId(), session);
                            }

                            return tingcoNetworkSubscriptionsXML.buildGetNetworkSubscriptionDetails(tingcoNetworkSubscriptions, ns, gt, timeZoneGMToffset, nspd, cc);
                        } else {
                            Set<String> networksubscriptionId = new HashSet<String>();
                            networksubscriptionId.add(networkSubscriptionID);
                            List<NetworkSubscriptions> networkSubscriptions = networkDAO.getNetworkSubscriptionsByIds(session, networksubscriptionId);
                            if (networkSubscriptions.isEmpty()) {
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Data Not Found");
                                return tingcoNetworkSubscriptions;
                            }
                            List<String> costCenterId = new ArrayList<String>();
                            List<String> groupId = new ArrayList<String>();
                            for (NetworkSubscriptions ns : networkSubscriptions) {
                                groupId.add(ns.getGroups().getGroupId());
                                List<ObjectCostCenters> objectCostCenterses = networkDAO.getObjectCostCentersByGroupIdandObjectId(session, ns.getNetworkSubscriptionId(), ns.getGroups().getGroupId());
                                if (!objectCostCenterses.isEmpty()) {
                                    costCenterId.add(objectCostCenterses.get(0).getCostCenterId());
                                }
                            }
                            NetworkSubscriptionsPendingDelete nspd = networkDAO.getNetworkSubscriptionsPendingDeleteBySubscriptionId(session, networkSubscriptionID);
                            GroupDAO groupDAO = new GroupDAO();
                            List<GroupTranslations> gt = groupDAO.getGroupTranslationsById(groupId, countryID, session);
                            List<CostCenters> costCenterses = new ArrayList<CostCenters>();
                            if (!costCenterId.isEmpty()) {
                                costCenterses = groupDAO.getCostCenterByIds(costCenterId, session);
                            }
                            return tingcoNetworkSubscriptionsXML.buildGetNetworkSubscriptionDetailsoptional(tingcoNetworkSubscriptions, networkSubscriptions, gt, timeZoneGMToffset, nspd, costCenterses);
                        }
                    } else {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                        return tingcoNetworkSubscriptions;
                    }
                } else {
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-10);
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoNetworkSubscriptions;
                }
            } else {
                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-3);
                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoNetworkSubscriptions;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoNetworkSubscriptions;
    }

    private TingcoNetworkSubscriptions updateNetworkSubscription(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoNetworkSubscriptions tingcoNetworkSubscriptions = null;
        TingcoNetworkSubscriptionsXML tingcoNetworkSubscriptionsXML = new TingcoNetworkSubscriptionsXML();
        Session session = null;
        try {
            tingcoNetworkSubscriptions = tingcoNetworkSubscriptionsXML.buildTingcoNetworkSubscriptionsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.NETWORKS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.NETWORKS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    se.info24.networkjaxb.TingcoNetworkSubscriptions networkSubscription = (se.info24.networkjaxb.TingcoNetworkSubscriptions) JAXBManager.getInstance().unMarshall(content, se.info24.networkjaxb.TingcoNetworkSubscriptions.class);
                    if (networkSubscription.getNetworkSubscriptions().getNetworkSubscription().size() > 0) {
                        se.info24.networkjaxb.NetworkSubscription nsjax = networkSubscription.getNetworkSubscriptions().getNetworkSubscription().get(0);
                        if (nsjax != null) {
                            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                            Set<String> id = new HashSet<String>();
                            id.add(nsjax.getNetworkSubscriptionID());
                            List<NetworkSubscriptions> networkSubscriptionsList = networkDAO.getNetworkSubscriptionsByIds(session, id);
                            if (networkSubscriptionsList.isEmpty()) {
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Data Not Found");
                                return tingcoNetworkSubscriptions;
                            }
                            NetworkSubscriptions ns = networkSubscriptionsList.get(0);
                            GroupDAO groupDAO = new GroupDAO();
                            Groups groups = groupDAO.getGroupByGroupId(nsjax.getGroupID().getValue(), session);
                            if (groups == null) {
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("GroupID is not valid");
                                return tingcoNetworkSubscriptions;
                            }
                            ns.setGroups(groups);
                            Agreements agreement = networkDAO.getAgreementsById(session, nsjax.getAgreementID().getValue());
                            if (agreement == null) {
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("AgreementID is not valid");
                                return tingcoNetworkSubscriptions;
                            }
                            ns.setAgreements(agreement);
                            DeviceDAO deviceDAO = new DeviceDAO();
                            if (nsjax.getDeviceID() != null) {
                                Device device = deviceDAO.getDeviceById(nsjax.getDeviceID(), session);
                                if (device == null) {
                                    tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                                    tingcoNetworkSubscriptions.getMsgStatus().setResponseText("DeviceID is not valid");
                                    return tingcoNetworkSubscriptions;
                                }
                                ns.setDevice(device);
                            }

                            Networks networks = networkDAO.getNetworksById(nsjax.getNetworkID(), session);
                            if (networks == null) {
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("NetworkID is not valid");
                                return tingcoNetworkSubscriptions;
                            }
                            ns.setNetworks(networks);
                            NetworkTypes nt = networkDAO.getNetworkTypesById(nsjax.getNetworkType(), session);
                            if (nt == null) {
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("NetworkType is not valid");
                                return tingcoNetworkSubscriptions;
                            }
                            ns.setNetworkTypes(nt);
                            ns.setNetworkSubscriptionName(nsjax.getNetworkSubscriptionName());
                            ns.setSubscriptionEnabled(nsjax.getSubscriptionEnabled());
                            ns.setSubscriptionNumber(nsjax.getSubscriptionNumber());

                            if (nsjax.getNetworkSubscriptionDescription() != null) {
                                ns.setNetworkSubscriptionDescription(nsjax.getNetworkSubscriptionDescription());
                            } else {
                                ns.setNetworkSubscriptionDescription(null);
                            }
                            if (nsjax.getInvoiceSubscription() != null) {
                                ns.setInvoiceSubscription(nsjax.getInvoiceSubscription().intValue());
                            } else {
                                ns.setInvoiceSubscription(null);
                            }
                            if (nsjax.getSubscriptionPassword() != null) {
                                ns.setSubscriptionPassword(nsjax.getSubscriptionPassword());
                            } else {
                                ns.setSubscriptionPassword(null);
                            }
                            if (nsjax.getIPV4() != null) {
                                ns.setIpv4(nsjax.getIPV4());
                            } else {
                                ns.setIpv4(null);
                            }
                            if (nsjax.getIPV6() != null) {
                                ns.setIpv6(nsjax.getIPV6());
                            } else {
                                ns.setIpv6(null);
                            }
                            if (nsjax.getSubscriptionUser() != null) {
                                ns.setSubscriptionUser(nsjax.getSubscriptionUser());
                            } else {
                                ns.setSubscriptionUser(null);
                            }
                            ObjectCostCenters costCenters = null;
                            boolean isDelete = false;
                            List<ObjectCostCenters> objectCostCenterses = networkDAO.getObjectCostCentersByGroupIdandObjectId(session, nsjax.getNetworkSubscriptionID(), nsjax.getGroupID().getValue());
                            if (nsjax.getCostCenterID() != null) {
                                ns.setCostCenterId(nsjax.getCostCenterID());
                                if (!objectCostCenterses.isEmpty()) {
                                    costCenters = objectCostCenterses.get(0);
                                    costCenters.setCostCenterId(nsjax.getCostCenterID());
                                    costCenters.setLastUpdatedByUserId(sessions2.getUserId());
                                    costCenters.setUpdatedDate(gc.getTime());
                                } else {
                                    costCenters = new ObjectCostCenters();
                                    ObjectCostCentersId ids = new ObjectCostCentersId();
                                    ids.setObjectId(nsjax.getNetworkSubscriptionID());
                                    ids.setGroupId(groups.getGroupId());
                                    costCenters.setId(ids);
                                    costCenters.setCostCenterId(nsjax.getCostCenterID());
                                    costCenters.setLastUpdatedByUserId(sessions2.getUserId());
                                    costCenters.setUpdatedDate(gc.getTime());
                                    costCenters.setCreatedDate(gc.getTime());
                                }
                            } else {
                                ns.setCostCenterId(null);
                                if (!objectCostCenterses.isEmpty()) {
                                    costCenters = objectCostCenterses.get(0);
                                    isDelete = true;
                                }
                            }
                            if (nsjax.getNetworkSubscriptionsStatusID() != null) {
                                NetworkSubscriptionStatuses nsse = networkDAO.getNetworkSubscriptionStatusesById(session, nsjax.getNetworkSubscriptionsStatusID());
                                if (nsse == null) {
                                    tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                                    tingcoNetworkSubscriptions.getMsgStatus().setResponseText("NetworkSubscriptionsStatusID is not valid");
                                    return tingcoNetworkSubscriptions;
                                }
                                ns.setNetworkSubscriptionStatuses(nsse);
                            } else {
                                ns.setNetworkSubscriptionStatuses(null);
                            }

                            GregorianCalendar gc1 = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                            UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                            if (userTimeZones2 != null) {
                                String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                                if (nsjax.getActiveToDate() != null) {
                                    gc1.setTime(df.parse(nsjax.getActiveToDate()));
                                    gc1.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc1.getTime()));
                                    ns.setActiveToDate(gc1.getTime());
                                } else {
                                    ns.setActiveToDate(gc.getTime());
                                }
                                if (nsjax.getActiveFromDate() != null) {
                                    gc1.setTime(df.parse(nsjax.getActiveFromDate()));
                                    gc1.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc1.getTime()));
                                    ns.setActiveFromDate(gc1.getTime());
                                } else {
                                    ns.setActiveFromDate(gc.getTime());
                                }
                            } else {
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("UserTimezone not found");
                                return tingcoNetworkSubscriptions;
                            }
                            if (nsjax.getICCID() != null) {
                                ns.setIccid(nsjax.getICCID());
                            } else {
                                ns.setIccid(null);
                            }
                            if (nsjax.getAPN() != null) {
                                ns.setApn(nsjax.getAPN());
                            } else {
                                ns.setApn(null);
                            }
                            ns.setUpdatedDate(gc.getTime());
                            if (!networkDAO.addNewSubscription(session, ns, new ArrayList<NetworkSubscriptionSettings>(), costCenters, isDelete)) {
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
                            }
                            return tingcoNetworkSubscriptions;
                        } else {
                            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("No NetworkSubscription found in XML");
                            return tingcoNetworkSubscriptions;
                        }
                    } else {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Invalid NetworkSubscription XML Found");
                        return tingcoNetworkSubscriptions;
                    }
                } else {
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-10);
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoNetworkSubscriptions;
                }
            } else {
                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-3);
                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoNetworkSubscriptions;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoNetworkSubscriptions;
    }

    private TingcoNetworkSubscriptions addNewSubscription(String content, String isTcmv3) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoNetworkSubscriptions tingcoNetworkSubscriptions = null;
        TingcoNetworkSubscriptionsXML tingcoNetworkSubscriptionsXML = new TingcoNetworkSubscriptionsXML();
        Session session = null;
        try {
            tingcoNetworkSubscriptions = tingcoNetworkSubscriptionsXML.buildTingcoNetworkSubscriptionsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.NETWORKS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.NETWORKS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    if (!isTcmv3.equals("")) {
                        isTcmv3 = isTcmv3.split("/")[2];
                    } else {
                        isTcmv3 = null;
                    }
                    session = HibernateUtil.getSessionFactory().openSession();
                    se.info24.networkjaxb.TingcoNetworkSubscriptions networkSubscription = (se.info24.networkjaxb.TingcoNetworkSubscriptions) JAXBManager.getInstance().unMarshall(content, se.info24.networkjaxb.TingcoNetworkSubscriptions.class);
                    if (networkSubscription.getNetworkSubscriptions().getNetworkSubscription().size() > 0) {
                        se.info24.networkjaxb.NetworkSubscription nsjax = networkSubscription.getNetworkSubscriptions().getNetworkSubscription().get(0);
                        if (nsjax != null) {
                            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                            NetworkSubscriptions ns = new NetworkSubscriptions();
                            String networkSUbscriptionId = UUID.randomUUID().toString();
                            ns.setNetworkSubscriptionId(networkSUbscriptionId);
                            GroupDAO groupDAO = new GroupDAO();
                            Groups groups = groupDAO.getGroupByGroupId(nsjax.getGroupID().getValue(), session);
                            if (groups == null) {
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("GroupID is not valid");
                                return tingcoNetworkSubscriptions;
                            }
                            ns.setGroups(groups);
                            Agreements agreement = networkDAO.getAgreementsById(session, nsjax.getAgreementID().getValue());
                            if (agreement == null) {
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("AgreementID is not valid");
                                return tingcoNetworkSubscriptions;
                            }
                            ns.setAgreements(agreement);
                            if (nsjax.getDeviceID() != null) {
                                DeviceDAO deviceDAO = new DeviceDAO();
                                Device device = deviceDAO.getDeviceById(nsjax.getDeviceID(), session);
                                if (device == null) {
                                    tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                                    tingcoNetworkSubscriptions.getMsgStatus().setResponseText("DeviceID is not valid");
                                    return tingcoNetworkSubscriptions;
                                }
                                ns.setDevice(device);
                            }
                            Networks networks = networkDAO.getNetworksById(nsjax.getNetworkID(), session);
                            if (networks == null) {
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("NetworkID is not valid");
                                return tingcoNetworkSubscriptions;
                            }
                            ns.setNetworks(networks);
                            NetworkTypes nt = networkDAO.getNetworkTypesById(nsjax.getNetworkType(), session);
                            if (nt == null) {
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("NetworkType is not valid");
                                return tingcoNetworkSubscriptions;
                            }
                            ns.setNetworkTypes(nt);
                            ns.setNetworkSubscriptionName(nsjax.getNetworkSubscriptionName());
                            ns.setSubscriptionEnabled(nsjax.getSubscriptionEnabled());
                            ns.setSubscriptionNumber(nsjax.getSubscriptionNumber());

                            if (nsjax.getNetworkSubscriptionDescription() != null) {
                                ns.setNetworkSubscriptionDescription(nsjax.getNetworkSubscriptionDescription());
                            }
                            if (nsjax.getInvoiceSubscription() != null) {
                                ns.setInvoiceSubscription(nsjax.getInvoiceSubscription().intValue());
                            }
                            if (nsjax.getSubscriptionPassword() != null) {
                                ns.setSubscriptionPassword(nsjax.getSubscriptionPassword());
                            }
                            if (nsjax.getIPV4() != null) {
                                ns.setIpv4(nsjax.getIPV4());
                            }
                            if (nsjax.getIPV6() != null) {
                                ns.setIpv6(nsjax.getIPV6());
                            }
                            if (nsjax.getSubscriptionUser() != null) {
                                ns.setSubscriptionUser(nsjax.getSubscriptionUser());
                            }
                            ObjectCostCenters costCenters = null;
                            if (nsjax.getCostCenterID() != null) {
                                ns.setCostCenterId(nsjax.getCostCenterID());
                                costCenters = new ObjectCostCenters();
                                ObjectCostCentersId id = new ObjectCostCentersId();
                                id.setObjectId(networkSUbscriptionId);
                                id.setGroupId(groups.getGroupId());
                                costCenters.setId(id);
                                costCenters.setCostCenterId(nsjax.getCostCenterID());
                                costCenters.setLastUpdatedByUserId(sessions2.getUserId());
                                costCenters.setUpdatedDate(gc.getTime());
                                costCenters.setCreatedDate(gc.getTime());

                            }
                            if (nsjax.getNetworkSubscriptionsStatusID() != null) {
                                NetworkSubscriptionStatuses nsse = networkDAO.getNetworkSubscriptionStatusesById(session, nsjax.getNetworkSubscriptionsStatusID());
                                if (nsse == null) {
                                    tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                                    tingcoNetworkSubscriptions.getMsgStatus().setResponseText("NetworkSubscriptionsStatusID is not valid");
                                    return tingcoNetworkSubscriptions;
                                }
                                ns.setNetworkSubscriptionStatuses(nsse);
                            }

                            GregorianCalendar gc1 = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                            UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                            if (userTimeZones2 != null) {
                                String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                                if (nsjax.getActiveToDate() != null) {
                                    gc1.setTime(df.parse(nsjax.getActiveToDate()));
                                    gc1.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc1.getTime()));
                                    ns.setActiveToDate(gc1.getTime());
                                } else {
                                    ns.setActiveToDate(gc.getTime());
                                }
                                if (nsjax.getActiveFromDate() != null) {
                                    gc1.setTime(df.parse(nsjax.getActiveFromDate()));
                                    gc1.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc1.getTime()));
                                    ns.setActiveFromDate(gc1.getTime());
                                } else {
                                    ns.setActiveFromDate(gc.getTime());
                                }
                            } else {
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("UserTimezone not found");
                                return tingcoNetworkSubscriptions;
                            }
                            if (nsjax.getICCID() != null) {
                                ns.setIccid(nsjax.getICCID());
                            }
                            if (nsjax.getAPN() != null) {
                                ns.setApn(nsjax.getAPN());
                            }
                            ns.setCreatedDate(gc.getTime());
                            ns.setUpdatedDate(gc.getTime());

                            List<NetworkSettingsTemplate> nst = new ArrayList<NetworkSettingsTemplate>();
                            List<NetworkSubscriptionSettings> nsst = new ArrayList<NetworkSubscriptionSettings>();
                            if (isTcmv3 != null) {
                                List<NetworkSettingsTemplate> nstList = networkDAO.getNetworkSettingsTemplateByNetworkTypes(session, nsjax.getNetworkType());
                                nsst = networkDAO.getNetworkSubscriptionSettingsBySubscriptionID(session, networkSUbscriptionId);
                                boolean flag = false;
                                for (NetworkSettingsTemplate networkSettingsTemplate : nstList) {
                                    flag = false;
                                    for (NetworkSubscriptionSettings networkSubscriptionSettings : nsst) {
                                        if (networkSubscriptionSettings.getNetworkSubscriptionSettingName().equalsIgnoreCase(networkSettingsTemplate.getNetworkSettingName())) {
                                            flag = true;
                                        }
                                    }
                                    if (!flag) {
                                        nst.add(networkSettingsTemplate);
                                    }
                                }
                                nsst.clear();
                                NetworkSubscriptionSettings networkSubscriptionSettings = null;
                                if (!nst.isEmpty()) {
                                    for (NetworkSettingsTemplate networkSettingsTemplate : nst) {
                                        networkSubscriptionSettings = new NetworkSubscriptionSettings();
                                        networkSubscriptionSettings.setNetworkSubscriptionSettingId(UUID.randomUUID().toString());
                                        networkSubscriptionSettings.setNetworkSubscriptions(ns);
                                        networkSubscriptionSettings.setNetworkSubscriptionSettingName(networkSettingsTemplate.getNetworkSettingName());
                                        networkSubscriptionSettings.setNetworkSubscriptionSettingValue(networkSettingsTemplate.getNetworkSettingValue());
                                        networkSubscriptionSettings.setSettingDataType(networkSettingsTemplate.getSettingDataType());
                                        networkSubscriptionSettings.setActiveFromDate(gc1.getTime());
                                        networkSubscriptionSettings.setUserId(sessions2.getUserId());
                                        networkSubscriptionSettings.setCreatedDate(gc.getTime());
                                        networkSubscriptionSettings.setUpdatedDate(gc.getTime());
                                        nsst.add(networkSubscriptionSettings);
                                    }
                                }
                            }
                            if (!networkDAO.addNewSubscription(session, ns, nsst, costCenters, false)) {
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
                            }
                            return tingcoNetworkSubscriptions;
                        } else {
                            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("No NetworkSubscription found in XML");
                            return tingcoNetworkSubscriptions;
                        }
                    } else {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Invalid NetworkSubscription XML Found");
                        return tingcoNetworkSubscriptions;
                    }
                } else {
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-10);
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoNetworkSubscriptions;
                }
            } else {
                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-3);
                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoNetworkSubscriptions;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoNetworkSubscriptions;
    }

    private TingcoNetworkSubscriptions getContactGroups(String contactGroupName, String groupName, int countryID, String groupID) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoNetworkSubscriptions tingcoNetworkSubscriptions = null;
        TingcoNetworkSubscriptionsXML tingcoNetworkSubscriptionsXML = new TingcoNetworkSubscriptionsXML();
        Session session = null;
        try {
            tingcoNetworkSubscriptions = tingcoNetworkSubscriptionsXML.buildTingcoNetworkSubscriptionsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
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
                    if (contactGroupName.equals("")) {
                        contactGroupName = null;
                    } else {
                        contactGroupName = contactGroupName.split("/")[2];
                    }

                    if (groupName.equals("")) {
                        groupName = null;
                    } else {
                        groupName = groupName.split("/")[2];
                    }
                    session = HibernateUtil.getSessionFactory().openSession();
                    GroupDAO groupDAO = new GroupDAO();
                    List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(groupID, session);
                    Set<String> groupidset = groupDAO.getGroupIdsList(groupID, groupTrustsList);
                    List<GroupTranslations> gtList = new ArrayList<GroupTranslations>();
                    if (groupName != null) {
                        if (TCMUtil.isValidUUID(groupName)) {
                            /**
                             * SPLIT LIST SIZE 30 MAR 2014
                             */
                            List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupidset), 2000);
                            for (List<String> list : listsplit) {
                                List<GroupTranslations> gtListTemp = groupDAO.getGroupTransSearchCountryid(new HashSet<String>(list), groupName, countryID, session);
                                gtList.addAll(gtListTemp);
                            }
                        //gtList = groupDAO.getGroupTransSearchCountryid(groupidset, groupName, countryID, session);
                        } else {
                            DeviceDAO deviceDAO = new DeviceDAO();
                            /**
                             * SPLIT LIST SIZE 30 MAR 2014
                             */
                            List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupidset), 2000);
                            for (List<String> list : listsplit) {
                                List<GroupTranslations> gtListTemp = deviceDAO.getGroupTransSearchByGroupName(session, list, groupName, countryID);
                                gtList.addAll(gtListTemp);
                            }
                        //gtList = deviceDAO.getGroupTransSearchByGroupName(session, new ArrayList<String>(groupidset), groupName, countryID);
                        }
                        groupidset.clear();
                        for (GroupTranslations groupTranslations : gtList) {
                            groupidset.add(groupTranslations.getId().getGroupId());
                        }
                    }
                    if (groupidset.isEmpty()) {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoNetworkSubscriptions;
                    }
                    List<ContactGroups> cgList = new ArrayList<ContactGroups>();
                    DeviceDAO deviceDAO = new DeviceDAO();
                    if (contactGroupName != null) {
                        if (TCMUtil.isValidUUID(contactGroupName)) {
                            /**
                             * SPLIT LIST SIZE 30 MAR 2014
                             */
                            List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupidset), 2000);
                            for (List<String> list : listsplit) {
                                List<ContactGroups> cgListTemp = networkDAO.getContactGroupBySearchCreteria(session, new HashSet<String>(list), contactGroupName);
                                cgList.addAll(cgListTemp);
                            }
                        //cgList = networkDAO.getContactGroupBySearchCreteria(session, groupidset, contactGroupName);

                        } else {
                            /**
                             * SPLIT LIST SIZE 30 MAR 2014
                             */
                            List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupidset), 2000);
                            for (List<String> list : listsplit) {
                                List<ContactGroups> cgListTemp = networkDAO.getContactGroupBySearchCreterias(session, new HashSet(list), contactGroupName);
                                cgList.addAll(cgListTemp);
                            }
                        //cgList = networkDAO.getContactGroupBySearchCreterias(session, groupidset, contactGroupName);
                        }
                    } else {
                        /**
                         * SPLIT LIST SIZE 30 MAR 2014
                         */
                        List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupidset), 2000);
                        for (List<String> list : listsplit) {
                            List<ContactGroups> cgListTemp = deviceDAO.getContactGroupByGroupid(new HashSet<String>(list), session);
                            cgList.addAll(cgListTemp);
                            if (cgList.size() > 200) {
                                cgList = cgList.subList(0, 200);
                                break;
                            }
                        }
                    //cgList = deviceDAO.getContactGroupByGroupid(groupidset, session);
                    }
                    gtList.clear();
                    /**
                     * SPLIT LIST SIZE 30 MAR 2014
                     */
                    List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupidset), 2000);
                    for (List<String> list : listsplit) {
                        List<GroupTranslations> gtListTemp = deviceDAO.getGroupTranslationsById(new HashSet<String>(list), Integer.valueOf(countryID), session);
                        gtList.addAll(gtListTemp);
                    }
                    //gtList = deviceDAO.getGroupTranslationsById(groupidset, Integer.valueOf(countryID), session);

                    if (cgList.isEmpty()) {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoNetworkSubscriptions;
                    }
                    return tingcoNetworkSubscriptionsXML.buildGetContactGroups(tingcoNetworkSubscriptions, cgList, gtList);

                } else {
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-10);
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoNetworkSubscriptions;
                }
            } else {
                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-3);
                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoNetworkSubscriptions;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoNetworkSubscriptions;
    }

    private TingcoNetworkSubscriptions addExistingSubscription(String networkSubscriptionID, String deviceID) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoNetworkSubscriptions tingcoNetworkSubscriptions = null;
        TingcoNetworkSubscriptionsXML tingcoNetworkSubscriptionsXML = new TingcoNetworkSubscriptionsXML();
        Session session = null;
        try {
            tingcoNetworkSubscriptions = tingcoNetworkSubscriptionsXML.buildTingcoNetworkSubscriptionsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.NETWORKS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.NETWORKS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    session = HibernateUtil.getSessionFactory().openSession();
                    Set<String> id = new HashSet<String>();
                    id.add(networkSubscriptionID);
                    List<NetworkSubscriptions> networkSubscriptionsList = networkDAO.getNetworkSubscriptionsByIds(session, id);
                    if (networkSubscriptionsList.isEmpty()) {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoNetworkSubscriptions;
                    }
                    NetworkSubscriptions networkSubscriptions = networkSubscriptionsList.get(0);
                    DeviceDAO deviceDAO = new DeviceDAO();
                    Device device = deviceDAO.getDeviceById(deviceID, session);
                    if (device == null) {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("DeviceID is not Valid");
                        return tingcoNetworkSubscriptions;
                    }
                    networkSubscriptions.setDevice(device);
                    networkSubscriptions.setUpdatedDate(gc.getTime());
//                    device.getNetworkSubscriptionses().add(networkSubscriptions);
                    if (!networkDAO.addExistingSubscription(session, networkSubscriptions)) {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
                    }
                    return tingcoNetworkSubscriptions;
                } else {
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-10);
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoNetworkSubscriptions;
                }
            } else {
                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-3);
                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoNetworkSubscriptions;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoNetworkSubscriptions;
    }

    private TingcoNetworkSubscriptions deleteContactGroup(String contactGroupID) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoNetworkSubscriptions tingcoNetworkSubscriptions = null;
        TingcoNetworkSubscriptionsXML tingcoNetworkSubscriptionsXML = new TingcoNetworkSubscriptionsXML();
        Session session = null;
        try {
            tingcoNetworkSubscriptions = tingcoNetworkSubscriptionsXML.buildTingcoNetworkSubscriptionsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
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
                    DeviceDAO deviceDAO = new DeviceDAO();
                    List<String> contactId = new ArrayList<String>();
                    contactId.add(contactGroupID);
                    List<ContactGroups> contactgroupList = deviceDAO.getContactGroupById(contactId, session);
                    if (contactgroupList.isEmpty()) {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoNetworkSubscriptions;
                    }
                    List<EventActionSchedules> easList = networkDAO.getEventActionSchedulesBycontactGroupId(session, contactGroupID);
                    List<ContactGroupsInContacts> cgicList = networkDAO.getContactGroupsInContactsBycontactGroupId(session, contactGroupID);
                    if (!networkDAO.deleteContactGroup(session, contactgroupList.get(0), easList, cgicList)) {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
                    }
                    return tingcoNetworkSubscriptions;
                } else {
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-10);
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoNetworkSubscriptions;
                }
            } else {
                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-3);
                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoNetworkSubscriptions;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoNetworkSubscriptions;
    }

    private TingcoNetworkSubscriptions editContactGroup(String contactGroupID, String contactGroupName, String contactGroupDescription) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoNetworkSubscriptions tingcoNetworkSubscriptions = null;
        TingcoNetworkSubscriptionsXML tingcoNetworkSubscriptionsXML = new TingcoNetworkSubscriptionsXML();
        Session session = null;
        try {
            tingcoNetworkSubscriptions = tingcoNetworkSubscriptionsXML.buildTingcoNetworkSubscriptionsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    if (contactGroupDescription.equals("")) {
                        contactGroupDescription = null;
                    } else {
                        contactGroupDescription = contactGroupDescription.split("/")[2];
                    }
                    session = HibernateUtil.getSessionFactory().openSession();
                    DeviceDAO deviceDAO = new DeviceDAO();
                    List<String> contactId = new ArrayList<String>();
                    contactId.add(contactGroupID);
                    List<ContactGroups> contactgroupList = deviceDAO.getContactGroupById(contactId, session);
                    if (contactgroupList.isEmpty()) {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoNetworkSubscriptions;
                    }

                    ContactGroups cg = contactgroupList.get(0);
                    cg.setContactGroupName(contactGroupName);
//                    if (contactGroupDescription != null) {
                    cg.setContactGroupDescription(contactGroupDescription);
//                    }
                    cg.setUserId(sessions2.getUserId());
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    cg.setUpdatedDate(gc.getTime());
                    if (!networkDAO.addNewContactGroup(session, cg)) {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
                    }
                    return tingcoNetworkSubscriptions;
                } else {
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-10);
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoNetworkSubscriptions;
                }
            } else {
                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-3);
                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoNetworkSubscriptions;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoNetworkSubscriptions;
    }

    private TingcoNetworkSubscriptions addNewContactGroup(String contactGroupName, String contactGroupDescription, String groupID) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoNetworkSubscriptions tingcoNetworkSubscriptions = null;
        TingcoNetworkSubscriptionsXML tingcoNetworkSubscriptionsXML = new TingcoNetworkSubscriptionsXML();
        Session session = null;
        try {
            tingcoNetworkSubscriptions = tingcoNetworkSubscriptionsXML.buildTingcoNetworkSubscriptionsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
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
                    if (contactGroupDescription.equals("")) {
                        contactGroupDescription = null;
                    } else {
                        contactGroupDescription = contactGroupDescription.split("/")[2];
                    }
                    session = HibernateUtil.getSessionFactory().openSession();
                    ContactGroups cg = new ContactGroups();
                    cg.setContactGroupId(UUID.randomUUID().toString());
                    cg.setContactGroupName(contactGroupName);
                    if (contactGroupDescription != null) {
                        cg.setContactGroupDescription(contactGroupDescription);

                    }
                    cg.setGroupId(groupID);
                    cg.setUserId(sessions2.getUserId());
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    cg.setCreatedDate(gc.getTime());
                    cg.setUpdatedDate(gc.getTime());
                    if (!networkDAO.addNewContactGroup(session, cg)) {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
                    }
                    return tingcoNetworkSubscriptions;
                } else {
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-10);
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoNetworkSubscriptions;
                }
            } else {
                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-3);
                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoNetworkSubscriptions;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoNetworkSubscriptions;
    }

    private TingcoNetworkSubscriptions deleteNetworkSubscriptionSetting(String networkSubscriptionSettingID) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoNetworkSubscriptions tingcoNetworkSubscriptions = null;
        TingcoNetworkSubscriptionsXML tingcoNetworkSubscriptionsXML = new TingcoNetworkSubscriptionsXML();
        Session session = null;
        try {
            tingcoNetworkSubscriptions = tingcoNetworkSubscriptionsXML.buildTingcoNetworkSubscriptionsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.NETWORKS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.NETWORKS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    NetworkSubscriptionSettings nss = networkDAO.getNetworkSubscriptionSettingsByID(session, networkSubscriptionSettingID);
                    if (nss == null) {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoNetworkSubscriptions;
                    }

                    if (!networkDAO.deleteNetworkSubscriptionSetting(session, nss)) {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
                    }
                    return tingcoNetworkSubscriptions;
                } else {
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-10);
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoNetworkSubscriptions;
                }
            } else {
                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-3);
                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoNetworkSubscriptions;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoNetworkSubscriptions;
    }

    private TingcoNetworkSubscriptions updateNetworkSubscriptionSetting(String networkSubscriptionSettingID, String networkSubscriptionSettingName, String networkSubscriptionSettingValue) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoNetworkSubscriptions tingcoNetworkSubscriptions = null;
        TingcoNetworkSubscriptionsXML tingcoNetworkSubscriptionsXML = new TingcoNetworkSubscriptionsXML();
        Session session = null;
        try {
            tingcoNetworkSubscriptions = tingcoNetworkSubscriptionsXML.buildTingcoNetworkSubscriptionsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.NETWORKS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.NETWORKS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
//                    List<NetworkSubscriptionSettings> nssList = networkDAO.getNetworkSubscriptionSettingsBySubscriptionID(session, networkSubscriptionSettingID);
                    NetworkSubscriptionSettings nss = networkDAO.getNetworkSubscriptionSettingsByID(session, networkSubscriptionSettingID);
                    if (nss == null) {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoNetworkSubscriptions;
                    }
                    nss.setNetworkSubscriptionSettingName(networkSubscriptionSettingName);
                    nss.setNetworkSubscriptionSettingValue(TCMUtil.convertHexToString(networkSubscriptionSettingValue));
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    nss.setUpdatedDate(gc.getTime());
                    if (!networkDAO.addNewNetworkSubscriptionSetting(session, nss)) {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
                    }
                    return tingcoNetworkSubscriptions;
                } else {
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-10);
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoNetworkSubscriptions;
                }
            } else {
                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-3);
                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoNetworkSubscriptions;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoNetworkSubscriptions;
    }

    private TingcoNetworkSubscriptions addNewNetworkSubscriptionSetting(String networkSubscriptionID, String networkSubscriptionSettingName, String networkSubscriptionSettingValue) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoNetworkSubscriptions tingcoNetworkSubscriptions = null;
        TingcoNetworkSubscriptionsXML tingcoNetworkSubscriptionsXML = new TingcoNetworkSubscriptionsXML();
        Session session = null;
        try {
            tingcoNetworkSubscriptions = tingcoNetworkSubscriptionsXML.buildTingcoNetworkSubscriptionsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.NETWORKS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.NETWORKS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    NetworkSubscriptionSettings nss = new NetworkSubscriptionSettings();
                    nss.setNetworkSubscriptionSettingId(UUID.randomUUID().toString());
                    Set<String> id = new HashSet<String>();
                    id.add(networkSubscriptionID);
                    List<NetworkSubscriptions> networkSubscriptionsList = networkDAO.getNetworkSubscriptionsByIds(session, id);
                    if (networkSubscriptionsList.isEmpty()) {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("networkSubscriptionID is not valid");
                        return tingcoNetworkSubscriptions;
                    }
                    nss.setNetworkSubscriptions(networkSubscriptionsList.get(0));
                    nss.setNetworkSubscriptionSettingName(networkSubscriptionSettingName);
                    nss.setNetworkSubscriptionSettingValue(TCMUtil.convertHexToString(networkSubscriptionSettingValue));
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    nss.setActiveFromDate(gc.getTime());
                    nss.setUserId(sessions2.getUserId());
                    nss.setUpdatedDate(gc.getTime());
                    nss.setCreatedDate(gc.getTime());
                    if (!networkDAO.addNewNetworkSubscriptionSetting(session, nss)) {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
                    }
                    return tingcoNetworkSubscriptions;
                } else {
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-10);
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoNetworkSubscriptions;
                }
            } else {
                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-3);
                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoNetworkSubscriptions;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoNetworkSubscriptions;
    }

    private TingcoNetworkSubscriptions getNetworkSettingTemplates(String networkSubscriptionId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoNetworkSubscriptions tingcoNetworkSubscriptions = null;
        TingcoNetworkSubscriptionsXML tingcoNetworkSubscriptionsXML = new TingcoNetworkSubscriptionsXML();
        Session session = null;
        try {
            tingcoNetworkSubscriptions = tingcoNetworkSubscriptionsXML.buildTingcoNetworkSubscriptionsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.NETWORKS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.NETWORKS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Set<String> id = new HashSet<String>();
                    id.add(networkSubscriptionId);
                    List<NetworkSubscriptions> NetworkSubscriptionsList = networkDAO.getNetworkSubscriptionsByIds(session, id);
                    if (NetworkSubscriptionsList.isEmpty()) {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoNetworkSubscriptions;
                    }
                    List<NetworkSettingsTemplate> networkSettingsTemplateList = networkDAO.getNetworkSettingsTemplateByNetworkTypes(session, NetworkSubscriptionsList.get(0).getNetworkTypes().getNetworkTypeId());
                    if (networkSettingsTemplateList.isEmpty()) {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoNetworkSubscriptions;
                    }
                    List<NetworkSubscriptionSettings> NetworkSubscriptionSettingsList = networkDAO.getNetworkSubscriptionSettingsBySubscriptionID(session, networkSubscriptionId);
                    return tingcoNetworkSubscriptionsXML.buildGetNetworkSettingTemplates(tingcoNetworkSubscriptions, networkSettingsTemplateList, NetworkSubscriptionSettingsList);
                } else {
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-10);
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoNetworkSubscriptions;
                }
            } else {
                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-3);
                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoNetworkSubscriptions;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoNetworkSubscriptions;
    }

    private TingcoNetworkSubscriptions removeNetworkSubscription(String networkSubscriptionId, String deviceId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoNetworkSubscriptions tingcoNetworkSubscriptions = null;
        TingcoNetworkSubscriptionsXML tingcoNetworkSubscriptionsXML = new TingcoNetworkSubscriptionsXML();
        Session session = null;
        try {
            tingcoNetworkSubscriptions = tingcoNetworkSubscriptionsXML.buildTingcoNetworkSubscriptionsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.NETWORKS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.NETWORKS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    if (!deviceId.equals("")) {
                        deviceId = deviceId.split("/")[2];
                    } else {
                        deviceId = null;
                    }
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserDAO userDAO = new UserDAO();
                    Users2 users2 = userDAO.getUserById(sessions2.getUserId(), session);
                    List<ObjectCostCenters> costCenterses = networkDAO.getObjectCostCentersByGroupIdandObjectId(session, networkSubscriptionId, users2.getGroupId());
                    if (deviceId != null) {
                        Object networkSubscription = networkDAO.getNetworkSubscriptionsByIdAnddDeviceIds(session, networkSubscriptionId, deviceId);
                        if (networkSubscription == null) {
                            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Data Not Found");
                            return tingcoNetworkSubscriptions;
                        }
                        GregorianCalendar gc = new GregorianCalendar();
                        NetworkSubscriptions networkSubscriptions = (NetworkSubscriptions) networkSubscription;
                        networkSubscriptions.setDevice(null);
                        networkSubscriptions.setUpdatedDate(gc.getTime());
                        if (!networkDAO.removeNetworkSubscription(session, networkSubscriptions, costCenterses)) {
                            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
                        }
                    } else {
                        Set<String> networksubscriptionId = new HashSet<String>();
                        networksubscriptionId.add(networkSubscriptionId);
                        List<NetworkSubscriptions> networkSubscriptions = networkDAO.getNetworkSubscriptionsByIds(session, networksubscriptionId);
                        if (networkSubscriptions.isEmpty()) {
                            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Data Not Found");
                            return tingcoNetworkSubscriptions;
                        }
                        List<NetworkSubscriptionSettings> networkSubscriptionSettingses = networkDAO.getNetworkSubscriptionSettingsBySubscriptionID(session, networkSubscriptionId);

                        if (!networkDAO.removeNetworkSubscriptions(session, networkSubscriptions, networkSubscriptionSettingses, costCenterses)) {
                            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
                        }
                    }


                    return tingcoNetworkSubscriptions;
                } else {
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-10);
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoNetworkSubscriptions;
                }
            } else {
                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-3);
                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoNetworkSubscriptions;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoNetworkSubscriptions;
    }

    private TingcoNetworkSubscriptions getNetworkOperators(String searchString) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoNetworkSubscriptions tingcoNetworkSubscriptions = null;
        TingcoNetworkSubscriptionsXML tingcoNetworkSubscriptionsXML = new TingcoNetworkSubscriptionsXML();
        Session session = null;
        try {
            tingcoNetworkSubscriptions = tingcoNetworkSubscriptionsXML.buildTingcoNetworkSubscriptionsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.NETWORKS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.NETWORKS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    if (!searchString.equals("")) {
                        searchString = searchString.split("/")[2];
                    } else {
                        searchString = null;
                    }
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<Networks> networkList = new ArrayList<Networks>();
                    if (searchString == null) {
                        networkList = networkDAO.getNetworksAll(session);
                    } else {
                        if (TCMUtil.isValidUUID(searchString)) {
                            Networks networks = networkDAO.getNetworksById(searchString, session);
                            if (networks == null) {
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Data Not Found");
                                return tingcoNetworkSubscriptions;
                            } else {
                                networkList.add(networks);
                            }
                        } else {
                            networkList = networkDAO.getNetworksByName(session, searchString);
                        }
                    }
                    if (networkList.isEmpty()) {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoNetworkSubscriptions;
                    }
                    return tingcoNetworkSubscriptionsXML.buildGetNetworkOperators(tingcoNetworkSubscriptions, networkList);
                } else {
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-10);
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoNetworkSubscriptions;
                }
            } else {
                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-3);
                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoNetworkSubscriptions;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoNetworkSubscriptions;
    }

    private TingcoNetworkSubscriptions getNetworkStatuses() {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoNetworkSubscriptions tingcoNetworkSubscriptions = null;
        TingcoNetworkSubscriptionsXML tingcoNetworkSubscriptionsXML = new TingcoNetworkSubscriptionsXML();
        Session session = null;
        try {
            tingcoNetworkSubscriptions = tingcoNetworkSubscriptionsXML.buildTingcoNetworkSubscriptionsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.NETWORKS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.NETWORKS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<NetworkSubscriptionStatuses> networkSubscriptionStatusesList = networkDAO.getNetworkSubscriptionStatusesAll(session);
                    if (networkSubscriptionStatusesList.isEmpty()) {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoNetworkSubscriptions;
                    }
                    return tingcoNetworkSubscriptionsXML.buildGetNetworkStatuses(tingcoNetworkSubscriptions, networkSubscriptionStatusesList);
                } else {
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-10);
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoNetworkSubscriptions;
                }
            } else {
                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-3);
                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoNetworkSubscriptions;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoNetworkSubscriptions;
    }

//    private TingcoNetworkSubscriptions getNonLinkedNetworkSubscriptionForDevice(String countryId, String groupId) {
//        long requestedTime = System.currentTimeMillis();
//        boolean hasPermission = false;
//        TingcoNetworkSubscriptions tingcoNetworkSubscriptions = null;
//        TingcoNetworkSubscriptionsXML tingcoNetworkSubscriptionsXML = new TingcoNetworkSubscriptionsXML();
//        Session session = null;
//        try {
//            tingcoNetworkSubscriptions = tingcoNetworkSubscriptionsXML.buildTingcoNetworkSubscriptionsTemplate();
//            if (request.getAttribute("USERSESSION") != null) {
//                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
//                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
//                if (ht.containsKey(TCMUtil.NETWORKS)) {
//                    ArrayList<String> operations = ht.get(TCMUtil.NETWORKS);
//                    for (int i = 0; i < operations.size(); i++) {
//                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
//                            hasPermission = true;
//                            break;
//                        }
//                    }
//                }
//                if (hasPermission) {
//                    session = HibernateUtil.getSessionFactory().openSession();
//                    GroupDAO groupDAO = new GroupDAO();
//                    List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(groupId, session);
//                    Set<String> groupidset = groupDAO.getGroupIdsList(groupId, groupTrustsList);
//                    /**
//                     * SPLIT LIST SIZE 30 MAR 2014
//                     */
//                    List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupidset), 2000);
//                    List<NetworkSubscriptions> networkSubscriptionsList = new ArrayList<NetworkSubscriptions>();
//                    for (List<String> list : listsplit) {
//                        List<NetworkSubscriptions> networkSubscriptionsListTemp = networkDAO.getNetworkSubscriptionsByGroupIdAnddDeviceId(session, new HashSet<String>(list));
//                        networkSubscriptionsList.addAll(networkSubscriptionsListTemp);
//                    }
//                    //List<NetworkSubscriptions> networkSubscriptionsList = networkDAO.getNetworkSubscriptionsByGroupIdAnddDeviceId(session, groupidset);
//                    if (networkSubscriptionsList.isEmpty()) {
//                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
//                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Data Not Found");
//                        return tingcoNetworkSubscriptions;
//                    }
//                    DeviceDAO deviceDAO = new DeviceDAO();
//                    /**
//                     * SPLIT LIST SIZE 30 MAR 2014
//                     */
//                    List<GroupTranslations> gtList = new ArrayList<GroupTranslations>();
//                    for (List<String> list : listsplit) {
//                        List<GroupTranslations> gtListTemp = deviceDAO.getGroupTranslationsById(new HashSet<String>(list), Integer.parseInt(countryId), session);
//                        gtList.addAll(gtListTemp);
//                    }
//                    //List<GroupTranslations> gtList = deviceDAO.getGroupTranslationsById(groupidset, Integer.parseInt(countryId), session);
//
//                    return tingcoNetworkSubscriptionsXML.buildGetNetworkSubscriptions(tingcoNetworkSubscriptions, gtList, networkSubscriptionsList);
//                } else {
//                    tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-10);
//                    tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Permission is not given");
//                    return tingcoNetworkSubscriptions;
//                }
//            } else {
//                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-3);
//                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Session is Not Valid");
//                return tingcoNetworkSubscriptions;
//            }
//        } catch (Exception e) {
//            TCMUtil.exceptionLog(getClass().getName(), e);
//            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
//            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
//        } finally {
//            if (session != null) {
//                session.close();
//            }
////            System.gc();
//            delayLog(requestedTime);
//        }
//        return tingcoNetworkSubscriptions;
//    }
    private TingcoNetworkSubscriptions getNonLinkedNetworkSubscriptionForDevice(int countryId, String groupId, String networkSubscriptionName, String subscriptionNumber, String iccId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoNetworkSubscriptions tingcoNetworkSubscriptions = null;
        TingcoNetworkSubscriptionsXML tingcoNetworkSubscriptionsXML = new TingcoNetworkSubscriptionsXML();
        Session session = null;
        try {
            tingcoNetworkSubscriptions = tingcoNetworkSubscriptionsXML.buildTingcoNetworkSubscriptionsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (!networkSubscriptionName.equals("")) {
                    networkSubscriptionName = networkSubscriptionName.split("/")[2];
                } else {
                    networkSubscriptionName = null;
                }
                if (!subscriptionNumber.equals("")) {
                    subscriptionNumber = subscriptionNumber.split("/")[2];
                } else {
                    subscriptionNumber = null;
                }
                if (!iccId.equals("")) {
                    iccId = iccId.split("/")[2];
                } else {
                    iccId = null;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.NETWORKS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.NETWORKS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<NetworkSubscriptions> networkSubscriptionsList = networkDAO.getNetworkSubscriptionsByGroupIdAndDeviceId(session, groupId, networkSubscriptionName, subscriptionNumber, iccId);
                    if (networkSubscriptionsList.isEmpty()) {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoNetworkSubscriptions;
                    }
                    return tingcoNetworkSubscriptionsXML.buildNonLinkedNetworkSubscriptionForDevice(session, tingcoNetworkSubscriptions, networkSubscriptionsList, countryId);
                } else {
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-10);
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoNetworkSubscriptions;
                }
            } else {
                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-3);
                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoNetworkSubscriptions;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
//            System.gc();
            delayLog(requestedTime);
        }
        return tingcoNetworkSubscriptions;
    }

    private TingcoNetworkSubscriptions getDeviceNetworkSubscription(String deviceId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoNetworkSubscriptions tingcoNetworkSubscriptions = null;
        TingcoNetworkSubscriptionsXML tingcoNetworkSubscriptionsXML = new TingcoNetworkSubscriptionsXML();
        Session session = null;
        try {
            tingcoNetworkSubscriptions = tingcoNetworkSubscriptionsXML.buildTingcoNetworkSubscriptionsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.NETWORKS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.NETWORKS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<NetworkSubscriptions> networkSubsList = networkDAO.getNetworkSubscriptionsBydeviceId(session, deviceId, 1);
                    if (networkSubsList.isEmpty()) {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Data not Found");
                        return tingcoNetworkSubscriptions;
                    }
                    return tingcoNetworkSubscriptionsXML.buildGetNetworkSubscriptions(tingcoNetworkSubscriptions, new ArrayList<GroupTranslations>(), networkSubsList);
                } else {
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-10);
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoNetworkSubscriptions;
                }
            } else {
                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-3);
                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoNetworkSubscriptions;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoNetworkSubscriptions;
    }

    private TingcoNetworkSubscriptions getNetworkSubscriptionSettings(String networksubscriptionId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoNetworkSubscriptions tingcoNetworkSubscriptions = null;
        TingcoNetworkSubscriptionsXML tingcoNetworkSubscriptionsXML = new TingcoNetworkSubscriptionsXML();
        Session session = null;
        try {
            tingcoNetworkSubscriptions = tingcoNetworkSubscriptionsXML.buildTingcoNetworkSubscriptionsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.NETWORKS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.NETWORKS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<NetworkSubscriptionSettings> networkSubscriptionSettingsList = networkDAO.getNetworkSubscriptionSettingsBySubscriptionID(session, networksubscriptionId);
                    if (networkSubscriptionSettingsList.isEmpty()) {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Data not Found");
                        return tingcoNetworkSubscriptions;
                    }
                    return tingcoNetworkSubscriptionsXML.buildGetNetworkSubscriptionSettings(tingcoNetworkSubscriptions, networkSubscriptionSettingsList);
                } else {
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-10);
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoNetworkSubscriptions;
                }
            } else {
                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-3);
                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoNetworkSubscriptions;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoNetworkSubscriptions;
    }

    private TingcoNetworkSubscriptions getNetworkSubscriptions(String deviceId, String countryId, String networkSubscriptionName, String subscriptionNumber, String iccid) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoNetworkSubscriptions tingcoNetworkSubscriptions = null;
        TingcoNetworkSubscriptionsXML tingcoNetworkSubscriptionsXML = new TingcoNetworkSubscriptionsXML();
        Session session = null;
        try {
            tingcoNetworkSubscriptions = tingcoNetworkSubscriptionsXML.buildTingcoNetworkSubscriptionsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.NETWORKS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.NETWORKS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    if (networkSubscriptionName.equals("")) {
                        networkSubscriptionName = null;
                    } else {
                        networkSubscriptionName = networkSubscriptionName.split("/")[2];
                    }
                    if (subscriptionNumber.equals("")) {
                        subscriptionNumber = null;
                    } else {
                        subscriptionNumber = subscriptionNumber.split("/")[2];
                    }
                    if (iccid.equals("")) {
                        iccid = null;
                    } else {
                        iccid = iccid.split("/")[2];
                    }
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<NetworkSubscriptions> networkSubsList = networkDAO.getNetworkSubscriptionsBydeviceId(session, deviceId, 0);
                    if (networkSubsList.isEmpty()) {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Data not Found");
                        return tingcoNetworkSubscriptions;
                    }
                    Set<String> networkSubId = new HashSet<String>();
                    for (NetworkSubscriptions networkSubscriptions : networkSubsList) {
                        networkSubId.add(networkSubscriptions.getNetworkSubscriptionId());
                    }
                    if (networkSubscriptionName != null || subscriptionNumber != null || iccid != null) {
                        networkSubsList = networkDAO.getNetworkSubscription(session, networkSubId, networkSubscriptionName, subscriptionNumber, iccid);
                    } else {
                        if (networkSubsList.size() > 200) {
                            networkSubsList = networkSubsList.subList(0, 200);
                        }
                    }
                    if (networkSubsList.isEmpty()) {
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
                        tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Data not Found");
                        return tingcoNetworkSubscriptions;
                    }
                    Set<String> groupid = new HashSet<String>();
                    networkSubId.clear();
                    for (NetworkSubscriptions networkSubscriptions : networkSubsList) {
                        groupid.add(networkSubscriptions.getGroups().getGroupId());
                        networkSubId.add(networkSubscriptions.getNetworkSubscriptionId());
                    }
                    DeviceDAO deviceDAO = new DeviceDAO();
                    List<GroupTranslations> gtList = deviceDAO.getGroupTranslationsById(groupid, Integer.parseInt(countryId), session);
                    networkSubsList = networkDAO.getNetworkSubscriptionsByIds(session, networkSubId);
                    return tingcoNetworkSubscriptionsXML.buildGetNetworkSubscriptions(tingcoNetworkSubscriptions, gtList, networkSubsList);
                } else {
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-10);
                    tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoNetworkSubscriptions;
                }
            } else {
                tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-3);
                tingcoNetworkSubscriptions.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoNetworkSubscriptions;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseCode(-1);
            tingcoNetworkSubscriptions.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoNetworkSubscriptions;
    }

    private TingcoUtils createNetwork(String networkName, String networkDesc) {
        long requestedTime = System.currentTimeMillis();
        TingcoUtils tingcoUtils = null;
        Session session = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (networkName == null) {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("NetworkTypeName is should not be empty");
                    return tingcoUtils;
                }

                if (networkDesc.equals("")) {
                    networkDesc = null;
                } else {
                    networkDesc = networkDesc.split("/")[2];
                }
                UserSessions2 userSessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Networks network = new Networks();
                network.setNetworkId(UUID.randomUUID().toString());
                network.setNetworkName(networkName);
                if (networkDesc != null) {
                    network.setNetworkDescription(networkDesc);
                }
                network.setUserId(userSessions2.getUserId());
                GregorianCalendar gc = new GregorianCalendar();
                network.setUpdatedDate(gc.getTime());
                network.setCreatedDate(gc.getTime());
                session = HibernateUtil.getSessionFactory().openSession();
                try {
                    if (networkDAO.saveNetwork(network, session)) {
                        return tingcoUtils;
                    } else {
                        tingcoUtils.getMsgStatus().setResponseCode(-1);
                        tingcoUtils.getMsgStatus().setResponseText("Error Occured while Saving NetworkTypes");
                        return tingcoUtils;
                    }
                } catch (Exception e) {
                }
            } else {
                tingcoUtils.getMsgStatus().setResponseCode(-3);
                tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUtils;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUtils.getMsgStatus().setResponseCode(-1);
            tingcoUtils.getMsgStatus().setResponseText("Error Occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUtils;
    }

    private TingcoUtils deleteNetwork(String networkId) {
        long requestedTime = System.currentTimeMillis();
        TingcoUtils tingcoUtils = null;
        Session session = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (networkId == null) {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("NetworkID is should not be empty");
                    return tingcoUtils;
                }
                session = HibernateUtil.getSessionFactory().openSession();
                Networks network = networkDAO.getNetworksById(networkId, session);
                if (network != null) {
                    if (networkDAO.removeNetwork(network, session)) {
                        return tingcoUtils;
                    } else {
                        tingcoUtils.getMsgStatus().setResponseCode(-1);
                        tingcoUtils.getMsgStatus().setResponseText("Error Occured while Deleting Network");
                        return tingcoUtils;
                    }
                } else {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("NetworkID Not Found");
                    return tingcoUtils;
                }
            } else {
                tingcoUtils.getMsgStatus().setResponseCode(-3);
                tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUtils;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUtils.getMsgStatus().setResponseCode(-1);
            tingcoUtils.getMsgStatus().setResponseText("Error Occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUtils;

    }

    private TingcoUtils updateNetwork(String networkId, String networkName, String networkDesc) {
        long requestedTime = System.currentTimeMillis();
        TingcoUtils tingcoUtils = null;
        Session session = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (networkId == null) {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("NetworkID is should not be empty");
                    return tingcoUtils;
                }

                if (networkName.equals("")) {
                    networkName = null;
                } else {
                    networkName = networkName.split("/")[2];
                }

                if (networkDesc.equals("")) {
                    networkDesc = null;
                } else {
                    networkDesc = networkDesc.split("/")[2];
                }
                session = HibernateUtil.getSessionFactory().openSession();
                Networks network = networkDAO.getNetworksById(networkId, session);
                if (network != null) {
                    UserSessions2 userSessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                    network.setUserId(userSessions2.getUserId());
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    network.setUpdatedDate(gc.getTime());
                    if (networkName != null) {
                        network.setNetworkName(networkName);
                    }
                    if (networkDesc != null) {
                        network.setNetworkDescription(networkDesc);
                    }
                    if (networkDAO.saveNetwork(network, session)) {
                        return tingcoUtils;
                    } else {
                        tingcoUtils.getMsgStatus().setResponseCode(-1);
                        tingcoUtils.getMsgStatus().setResponseText("Error Occured while Saving Networks");
                        return tingcoUtils;
                    }
                } else {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("NetworkID Not Found");
                    return tingcoUtils;
                }
            } else {
                tingcoUtils.getMsgStatus().setResponseCode(-3);
                tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUtils;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUtils.getMsgStatus().setResponseCode(-1);
            tingcoUtils.getMsgStatus().setResponseText("Error Occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUtils;
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}

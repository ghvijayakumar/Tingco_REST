/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.containers;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.xml.datatype.DatatypeConfigurationException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.containersjaxb.Container;
import se.info24.containersjaxb.TingcoContainers;
import se.info24.device.DeviceDAO;
import se.info24.group.GroupDAO;
import se.info24.ismOperationsPojo.ContainerData;
import se.info24.ismOperationsPojo.ContainerFillings;
import se.info24.ismOperationsPojo.ContainerTransactions;
import se.info24.ismOperationsPojo.ContainerUsageData;
import se.info24.jaxbUtil.TingcoContainersXML;
import se.info24.network.NetworkDAO;
import se.info24.pojo.AddressType;
import se.info24.pojo.Addresses;
import se.info24.pojo.Agreements;
import se.info24.pojo.ContainerDevices;
import se.info24.pojo.ContainerDevicesId;
import se.info24.pojo.ContainerShapes;
import se.info24.pojo.ContainerTypes;
import se.info24.pojo.Containers;
import se.info24.pojo.CostCenters;
import se.info24.pojo.Country;
import se.info24.pojo.Device;
import se.info24.pojo.DeviceDataitemTranslations;
import se.info24.pojo.EventTypeTranslations;
import se.info24.pojo.FieldTranslations;
import se.info24.pojo.Fields;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupTrusts;
import se.info24.pojo.Groups;
import se.info24.pojo.ObjectContactMemberships;
import se.info24.pojo.ObjectContactMembershipsId;
import se.info24.pojo.ObjectContactTypeTranslations;
import se.info24.pojo.ObjectContactTypes;
import se.info24.pojo.ObjectContacts;
import se.info24.pojo.ObjectCostCenters;
import se.info24.pojo.ObjectCostCentersId;
import se.info24.pojo.ObjectStateCodeTranslations;
import se.info24.pojo.ObjectTypeFields;
import se.info24.pojo.Ogmcontainers;
import se.info24.pojo.OgmcontainersId;
import se.info24.pojo.ProductVariantTranslations;
import se.info24.pojo.ProductVariants;
import se.info24.pojo.Schedules;
import se.info24.pojo.SensorTypes;
import se.info24.pojo.ServiceDeviceSubscriptions;
import se.info24.pojo.Services;
import se.info24.pojo.UnitTranslations;
import se.info24.pojo.UserAlias;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.UserTimeZones2;
import se.info24.pojo.Users2;
import se.info24.products.ProductsDAO;
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
 * @author Sumit
 */
@Path("/containers")
public class ContainerssResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoContainersXML tingcoContainersXML = new TingcoContainersXML();
    ContainerDAO containerDAO = new ContainerDAO();
    DeviceDAO deviceDAO = new DeviceDAO();
    GroupDAO groupDAO = new GroupDAO();
    ProductsDAO productsDAO = new ProductsDAO();
    UserDAO userDAO = new UserDAO();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of ContainerssResource */
    public ContainerssResource() {
    }

    /**
     * Retrieves representation of an instance of se.info24.containers.ContainerssResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * GetContainerDashBoardDetails
     * @param ContainerId
     * @param CountryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getcontainerdashboarddetails/rest/{rest}/containerid/{containerid}/countryid/{countryid}{fromdate:(/fromdate/[^/]+?)?}{todate:(/todate/[^/]+?)?}")
    @RESTDoc(methodName = "GetContainerDashBoardDetails", desc = "Used to Get ContainerDashBoardDetails", req_Params = {"ContainerId;string", "countryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getXml_getContainerDashBoardDetails(@PathParam("containerid") String containerId, @PathParam("countryid") int countryId, @PathParam("fromdate") String fromDate, @PathParam("todate") String toDate) {
        return getContainerDashBoardDetails(containerId, countryId, fromDate, toDate);
    }

    /**
     * GetContainerDashBoardDetails
     * @param ContainerId
     * @param CountryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getcontainerdashboarddetails/json/{json}/containerid/{containerid}/countryid/{countryid}{fromdate:(/fromdate/[^/]+?)?}{todate:(/todate/[^/]+?)?}")
    public TingcoContainers getJson_getContainerDashBoardDetails(@PathParam("containerid") String containerId, @PathParam("countryid") int countryId, @PathParam("fromdate") String fromDate, @PathParam("todate") String toDate) {
        return getContainerDashBoardDetails(containerId, countryId, fromDate, toDate);
    }

    /**
     * GetContainerDataGraphDetails
     * @param ContainerId
     * @param fromDate
     * @param toDate
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getcontainerdatagraphdetails/rest/{rest}/containerid/{containerid}{fromdate:(/fromdate/[^/]+?)?}{todate:(/todate/[^/]+?)?}")
    @RESTDoc(methodName = "GetContainerDashBoardGraphDetails", desc = "Used to Get ContainerDashBoardGraphDetails", req_Params = {"ContainerId;string"}, opt_Params = {"FromDate;string", " ToDate;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getXml_getContainerDashBoardGraphDetails(@PathParam("containerid") String containerId, @PathParam("fromdate") String fromDate, @PathParam("todate") String toDate) {
        return getContainerDashBoardGraphDetails(containerId, fromDate, toDate);
    }

    /**
     * GetContainerDataGraphDetails
     * @param ContainerId
     * @param fromDate
     * @param toDate
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getcontainerdatagraphdetails/json/{json}/containerid/{containerid}{fromdate:(/fromdate/[^/]+?)?}{todate:(/todate/[^/]+?)?}")
    public TingcoContainers getJson_getContainerDashBoardGraphDetails(@PathParam("containerid") String containerId, @PathParam("fromdate") String fromDate, @PathParam("todate") String toDate) {
        return getContainerDashBoardGraphDetails(containerId, fromDate, toDate);
    }

    /**
     * GetContainerTypes
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getcontainertypes/rest/{rest}")
    @RESTDoc(methodName = "GetContainerTypes", desc = "Used to Get ContainerTypes", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getXml_getContainerTypes() {
        return getContainerTypes();
    }

    /**
     * GetContainerTypes
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getcontainertypes/json/{json}")
    public TingcoContainers getJson_getContainerTypes() {
        return getContainerTypes();
    }

    /**
     * GetContainerShapes
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getcontainershapes/rest/{rest}")
    @RESTDoc(methodName = "GetContainerShapes", desc = "Used to Get ContainerShapes", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getXml_getContainerShapes() {
        return getContainerShapes();
    }

    /**
     * GetContainerShapes
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getcontainershapes/json/{json}")
    public TingcoContainers getJson_getContainerShapes() {
        return getContainerShapes();
    }

    /**
     * GetObjectContacts
     * @param groupId
     * @return TingcoContainers
     */
    @GET
    @Produces("application/xml")
    @Path("/getobjectcontacts/rest/{rest}/groupid/{groupid}")
    @RESTDoc(methodName = "GetObjectContacts", desc = "Used to Get ContainerShapes", req_Params = {"Groupid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getXml_getObjectContacts(@PathParam("groupid") String groupId) {
        return getObjectContacts(groupId);
    }

    /**
     * GetObjectContacts
     * @param groupId
     * @return TingcoContainers
     */
    @GET
    @Produces("application/json")
    @Path("/getobjectcontacts/json/{json}/groupid/{groupid}")
    @RESTDoc(methodName = "GetObjectContacts", desc = "Used to Get ContainerShapes", req_Params = {"groupid:string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getJson_getObjectContacts(@PathParam("groupid") String groupId) {
        return getObjectContacts(groupId);
    }

    /**
     * getobjectcontacttypes
     * @param countryId
     * @return TingcoContainers
     */
    @GET
    @Produces("application/xml")
    @Path("/getobjectcontacttypes/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "getobjectcontacttypes", desc = "Used to Get ContainerShapes", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getXml_getObjectContactTypes(@PathParam("countryid") String countryId) {
        return getObjectContactTypes(countryId);
    }

    /**
     * getobjectcontacttypes
     * @param countryId
     * @return TingcoContainers
     */
    @GET
    @Produces("application/json")
    @Path("/getobjectcontacttypes/json/{json}/countryid/{countryid}")
    public TingcoContainers getJson_getObjectContactTypes(@PathParam("countryid") String countryId) {
        return getObjectContactTypes(countryId);
    }

    /**
     * GetAllUnits
     * @param countryId
     * @return TingcoContainers
     */
    @GET
    @Produces("application/xml")
    @Path("/getallunits/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetAllUnits", desc = "Used to Get AllUnits", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getXml_getAllUnits(@PathParam("countryid") int countryId) {
        return getAllUnits(countryId);
    }

    /**
     * GetAllUnits
     * @param countryId
     * @return TingcoContainers
     */
    @GET
    @Produces("application/json")
    @Path("/getallunits/json/{json}/countryid/{countryid}")
    public TingcoContainers getJson_getAllUnits(@PathParam("countryid") int countryId) {
        return getAllUnits(countryId);
    }

    /**
     * AddExistingContactToContainer
     * @param containerId
     * @param objectcontactid
     * @return TingcoContainers
     */
    @GET
    @Produces("application/xml")
    @Path("/addexistingcontacttocontainer/rest/{rest}/containerid/{containerid}/objectcontactid/{objectcontactid}")
    @RESTDoc(methodName = "AddExistingContactToContainer", desc = "Used to Get ContainerShapes", req_Params = {"containerid;UUID", "objectcontactid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getXml_addExistingContactToContainer(@PathParam("containerid") String containerId, @PathParam("objectcontactid") String objectcontactid) {
        return addExistingContactToContainer(containerId, objectcontactid);
    }

    /**
     * AddExistingContactToContainer
     * @param containerId
     * @param objectcontactid
     * @return TingcoContainers
     */
    @GET
    @Produces("application/json")
    @Path("/addexistingcontacttocontainer/json/{json}/containerid/{containerid}/objectcontactid/{objectcontactid}")
    @RESTDoc(methodName = "AddExistingContactToContainer", desc = "Used to Get ContainerShapes", req_Params = {"containerid;UUID", "objectcontactid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getJson_addExistingContactToContainer(@PathParam("containerid") String containerId, @PathParam("objectcontactid") String objectcontactid) {
        return addExistingContactToContainer(containerId, objectcontactid);
    }

    /**
     *  AddNewContactToContainer
     * @param containerId
     * @param contactFirstName
     * @param contactLastName
     * @param contactOrganizationName
     * @param contactMobilePhone
     * @param contactEmail
     * @param objectContactTypeId
     * @param groupId
     * @return TingcoContainers
     */
    @GET
    @Produces("application/xml")
    @Path("/addnewcontacttocontainer/rest/{rest}/containerid/{containerid}/contactfirstname/{contactfirstname}/contactlastname/{contactlastname}/contactorganizationname/{contactorganizationname}{contactmobilephone:(/contactmobilephone/[^/]+?)?}{contactemail:(/contactemail/[^/]+?)?}/objectcontacttypeid/{objectcontacttypeid}/groupid/{groupid}")
    @RESTDoc(methodName = "AddNewContactToContainer", desc = "Used to Get ContainerShapes", req_Params = {"containerid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getXml_addNewContactToContainer(@PathParam("containerid") String containerId, @PathParam("contactfirstname") String contactFirstName, @PathParam("contactlastname") String contactLastName, @PathParam("contactorganizationname") String contactOrganizationName, @PathParam("contactmobilephone") String contactMobilePhone, @PathParam("contactemail") String contactEmail, @PathParam("objectcontacttypeid") String objectContactTypeId, @PathParam("groupid") String groupId) {
        return addNewContactToContainer(containerId, contactFirstName, contactLastName, contactOrganizationName, contactMobilePhone, contactEmail, objectContactTypeId, groupId);
    }

    /**
     *  AddNewContactToContainer
     * @param containerId
     * @param contactFirstName
     * @param contactLastName
     * @param contactOrganizationName
     * @param contactMobilePhone
     * @param contactEmail
     * @param objectContactTypeId
     * @param groupId
     * @return TingcoContainers
     */
    @GET
    @Produces("application/json")
    @Path("/addnewcontacttocontainer/json/{json}/containerid/{containerid}/contactfirstname/{contactfirstname}/contactlastname/{contactlastname}/contactorganizationname/{contactorganizationname}/contactmobilephone/{contactmobilephone}/contactemail/{contactemail}/objectcontacttypeid/{objectcontacttypeid}/groupid/{groupid}")
    @RESTDoc(methodName = "AddNewContactToContainer", desc = "Used to Get ContainerShapes", req_Params = {"containerid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getJson_addNewContactToContainer(@PathParam("containerid") String containerId, @PathParam("contactfirstname") String contactFirstName, @PathParam("contactlastname") String contactLastName, @PathParam("contactorganizationname") String contactOrganizationName, @PathParam("contactmobilephone") String contactMobilePhone, @PathParam("contactemail") String contactEmail, @PathParam("objectcontacttypeid") String objectContactTypeId, @PathParam("groupid") String groupId) {
        return addNewContactToContainer(containerId, contactFirstName, contactLastName, contactOrganizationName, contactMobilePhone, contactEmail, objectContactTypeId, groupId);
    }

    /**
     *  GetObjectContactInfo
     * @param objectContactID
     * @param countryID
     * @return TingcoContainers
     */
    @GET
    @Produces("application/xml")
    @Path("/getobjectcontactinfo/rest/{rest}/objectcontactid/{objectcontactid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetObjectContactInfo", desc = "Used to Get ContainerShapes", req_Params = {"objectcontactid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getXml_getObjectContactInfo(@PathParam("objectcontactid") String objectContactID, @PathParam("countryid") String countryID) {
        return getObjectContactInfo(objectContactID, countryID);
    }

    /**
     *  GetObjectContactInfo
     * @param objectContactID
     * @param countryID
     * @return TingcoContainers
     */
    @GET
    @Produces("application/json")
    @Path("/getobjectcontactinfo/json/{json}/objectcontactid/{objectcontactid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetObjectContactInfo", desc = "Used to Get ContainerShapes", req_Params = {"objectcontactid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getJson_getObjectContactInfo(@PathParam("objectcontactid") String objectContactID, @PathParam("countryid") String countryID) {
        return getObjectContactInfo(objectContactID, countryID);
    }

    /**
     *  UpdateObjectContactInfo
     * @param objectContactID
     * @param contactFirstName
     * @param contactLastName
     * @param contactOrganizationName
     * @param contactMobilePhone
     * @param contactEmail
     * @param objectContactTypeID
     * @return TingcoContainers
     */
    @GET
    @Produces("application/xml")
    @Path("/updateobjectcontactinfo/rest/{rest}/objectcontactid/{objectcontactid}/contactfirstname/{contactfirstname}/contactlastname/{contactlastname}/contactorganizationname/{contactorganizationname}{contactmobilephone:(/contactmobilephone/[^/]+?)?}{contactemail:(/contactemail/[^/]+?)?}/objectcontacttypeid/{objectcontacttypeid}")
    @RESTDoc(methodName = "UpdateObjectContactInfo", desc = "Used to Get ContainerShapes", req_Params = {"objectcontactid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getXml_updateObjectContactInfo(@PathParam("objectcontactid") String objectContactID, @PathParam("contactfirstname") String contactFirstName, @PathParam("contactlastname") String contactLastName, @PathParam("contactorganizationname") String contactOrganizationName, @PathParam("contactmobilephone") String contactMobilePhone, @PathParam("contactemail") String contactEmail, @PathParam("objectcontacttypeid") String objectContactTypeID) {
        return updateObjectContactInfo(objectContactID, contactFirstName, contactLastName, contactOrganizationName, contactMobilePhone, contactEmail, objectContactTypeID);
    }

    /**
     *  UpdateObjectContactInfo
     * @param objectContactID
     * @param contactFirstName
     * @param contactLastName
     * @param contactOrganizationName
     * @param contactMobilePhone
     * @param contactEmail
     * @param objectContactTypeID
     * @return TingcoContainers
     */
    @GET
    @Produces("application/json")
    @Path("/updateobjectcontactinfo/json/{json}/objectcontactid/{objectcontactid}/contactfirstname/{contactfirstname}/contactlastname/{contactlastname}/contactorganizationname/{contactorganizationname}{contactmobilephone:(/contactmobilephone/[^/]+?)?}{contactemail:(/contactemail/[^/]+?)?}/objectcontacttypeid/{objectcontacttypeid}")
    public TingcoContainers getJson_updateObjectContactInfo(@PathParam("objectcontactid") String objectContactID, @PathParam("contactfirstname") String contactFirstName, @PathParam("contactlastname") String contactLastName, @PathParam("contactorganizationname") String contactOrganizationName, @PathParam("contactmobilephone") String contactMobilePhone, @PathParam("contactemail") String contactEmail, @PathParam("objectcontacttypeid") String objectContactTypeID) {
        return updateObjectContactInfo(objectContactID, contactFirstName, contactLastName, contactOrganizationName, contactMobilePhone, contactEmail, objectContactTypeID);
    }

    /**
     * GetVisitorsList
     * @param groupId
     * @param containerId
     * @param userAlias
     * @param searchGroupId
     * @param deviceId
     */
    @GET
    @Produces("application/xml")
    @Path("/getvisitorslist/rest/{rest}/groupid/{groupid}/countryid/{countryid}{containerid:(/containerid/[^/]+?)?}{useralias:(/useralias/[^/]+?)?}{searchgroupid:(/searchgroupid/[^/]+?)?}{deviceid:(/deviceid/[^/]+?)?}")
    @RESTDoc(methodName = "GetVisitorsList", desc = "Used to Get VisitorsList", req_Params = {"groupid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getXml_getVisitorsList(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("containerid") String containerId, @PathParam("useralias") String userAlias, @PathParam("searchgroupid") String searchGroupId, @PathParam("deviceid") String deviceId) {
        return getVisitorsList(groupId, containerId, userAlias, searchGroupId, deviceId, countryId);
    }

    /**
     * GetVisitorsList
     * @param groupId
     * @param containerId
     * @param userAlias
     * @param searchGroupId
     * @param deviceId
     */
    @GET
    @Produces("application/json")
    @Path("/getvisitorslist/json/{json}/groupid/{groupid}/countryid/{countryid}{containerid:(/containerid/[^/]+?)?}{useralias:(/useralias/[^/]+?)?}{searchgroupid:(/searchgroupid/[^/]+?)?}{deviceid:(/deviceid/[^/]+?)?}")
    public TingcoContainers getJson_getVisitorsList(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("containerid") String containerId, @PathParam("useralias") String userAlias, @PathParam("searchgroupid") String searchGroupId, @PathParam("deviceid") String deviceId) {
        return getVisitorsList(groupId, containerId, userAlias, searchGroupId, deviceId, countryId);
    }

    /**
     * DeleteContact
     * @param objectContactID
     * @param containerID
     * @return TingcoContainers
     */
    @GET
    @Produces("application/xml")
    @Path("/deletecontact/rest/{rest}/objectcontactid/{objectcontactid}/containerid/{containerid}")
    @RESTDoc(methodName = "DeleteContact", desc = "Used to Get ContainerShapes", req_Params = {"objectcontactid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getXml_DeleteContact(@PathParam("objectcontactid") String objectContactID, @PathParam("containerid") String containerID) {
        return deleteContact(objectContactID, containerID);
    }

    /**
     * DeleteContact
     * @param objectContactID
     * @param containerID
     * @return TingcoContainers
     */
    @GET
    @Produces("application/json")
    @Path("/deletecontact/json/{json}/objectcontactid/{objectcontactid}/containerid/{containerid}")
    @RESTDoc(methodName = "DeleteContact", desc = "Used to Get ContainerShapes", req_Params = {"objectcontactid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getJson_DeleteContact(@PathParam("objectcontactid") String objectContactID, @PathParam("containerid") String containerID) {
        return deleteContact(objectContactID, containerID);
    }

    /**
     *  DeleteContainer
     * @param containerID
     * @return TingcoContainers
     */
    @GET
    @Produces("application/xml")
    @Path("/deletecontainer/rest/{rest}/containerid/{containerid}")
    @RESTDoc(methodName = "DeleteContainer", desc = "Used to Get ContainerShapes", req_Params = {"containerid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getxml_DeleteContacts(@PathParam("containerid") String containerID) {
        return deleteContainer(containerID);
    }

    /**
     *  DeleteContainer
     * @param containerID
     * @return TingcoContainers
     */
    @GET
    @Produces("application/json")
    @Path("/deletecontainer/json/{json}/containerid/{containerid}")
    @RESTDoc(methodName = "DeleteContainer", desc = "Used to Get ContainerShapes", req_Params = {"containerid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getjson_DeleteContacts(@PathParam("containerid") String containerID) {
        return deleteContainer(containerID);
    }

    /**
     * GetContainerNotificationDetails
     * @param containerID
     * @param countryID
     * @return TingcoContainers
     */
    @GET
    @Produces("application/xml")
    @Path("/getcontainernotificationdetails/rest/{rest}/containerid/{containerid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetContainerNotificationDetails", desc = "Used to Get ContainerShapes", req_Params = {"containerid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getXml_getContainerNotificationDetails(@PathParam("containerid") String containerID, @PathParam("countryid") String countryID) {
        return getContainerNotificationDetails(containerID, countryID);
    }

    /**
     * GetContainerNotificationDetails
     * @param containerID
     * @param countryID
     * @return TingcoContainers
     */
    @GET
    @Produces("application/json")
    @Path("/getcontainernotificationdetails/json/{json}/containerid/{containerid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetContainerNotificationDetails", desc = "Used to Get ContainerShapes", req_Params = {"containerid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getJson_getContainerNotificationDetails(@PathParam("containerid") String containerID, @PathParam("countryid") String countryID) {
        return getContainerNotificationDetails(containerID, countryID);
    }

    /**
     * GetContainers
     * @param containerName
     * @param countryID
     * @param groupID
     * @return TingcoContainers
     */
    @GET
    @Produces("application/xml")
    @Path("/getcontainers/rest/{rest}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetContainers", desc = "Used to Get ContainerShapes", req_Params = {"groupid;string", "countryid;int"}, opt_Params = {"searchstring;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getXml_getContainers(@PathParam("searchstring") String searchstring, @PathParam("countryid") String countryID, @PathParam("groupid") String groupID) {
        return getContainers(searchstring, countryID, groupID);
    }

    /**
     * GetContainers
     * @param containerName
     * @param countryID
     * @param groupID
     * @return TingcoContainers
     */
    @GET
    @Produces("application/json")
    @Path("/getcontainers/json/{json}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetContainers", desc = "Used to Get ContainerShapes", req_Params = {"groupid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getJson_getContainers(@PathParam("searchstring") String searchstring, @PathParam("countryid") String countryID, @PathParam("groupid") String groupID) {
        return getContainers(searchstring, countryID, groupID);
    }

    /**
     * GetContainerInfoForTankContainer
     * @param assetId
     * @return TingcoContainers
     */
    @GET
    @Produces("application/xml")
    @Path("/getcontainerinfofortankcontainer/rest/{rest}/assetid/{assetid}")
    @RESTDoc(methodName = "GetContainerInfoForTankContainer", desc = "Used to Get ContainerInfoForTankContainer", req_Params = {"assetid;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getXml_getContainerInfoForTankContainer(@PathParam("assetid") String assetId) {
        return getContainerInfoForTankContainer(assetId);
    }

    /**
     * GetContainerInfoForTankContainer
     * @param assetId
     * @return TingcoContainers
     */
    @GET
    @Produces("application/json")
    @Path("/getcontainerinfofortankcontainer/json/{json}/assetid/{assetid}")
    public TingcoContainers getJson_getContainerInfoForTankContainer(@PathParam("assetid") String assetId) {
        return getContainerInfoForTankContainer(assetId);
    }

    /**
     * GetDevicesForContainer
     * @param cotainerID
     * @return TingcoContainers
     */
    @GET
    @Produces("application/xml")
    @Path("/getdevicesforcontainer/rest/{rest}/containerid/{containerid}")
    @RESTDoc(methodName = "GetDevicesForContainer", desc = "Used to Get ContainerShapes", req_Params = {"containerid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getXml_getDevicesForContainer(@PathParam("containerid") String cotainerID) {
        return getDevicesForContainer(cotainerID);
    }

    /**
     * GetDevicesForContainer
     * @param cotainerID
     * @return TingcoContainers
     */
    @GET
    @Produces("application/json")
    @Path("/getdevicesforcontainer/json/{json}/containerid/{containerid}")
    @RESTDoc(methodName = "GetDevicesForContainer", desc = "Used to Get ContainerShapes", req_Params = {"containerid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getJson_getDevicesForContainer(@PathParam("containerid") String cotainerID) {
        return getDevicesForContainer(cotainerID);
    }

    /**
     * GetContainerScalingDetails
     * @param containerID
     * @param deviceID
     * @param countryID
     * @return TingcoContainers
     */
    @GET
    @Produces("application/xml")
    @Path("/getcontainerscalingdetails/rest/{rest}/containerid/{containerid}/deviceid/{deviceid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetContainerScalingDetails", desc = "Used to Get ContainerShapes", req_Params = {"containerid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getXml_getContainerScalingDetails(@PathParam("containerid") String containerID, @PathParam("deviceid") String deviceID, @PathParam("countryid") String countryID) {
        return getContainerScalingDetails(containerID, deviceID, countryID);
    }

    /**
     * GetContainerScalingDetails
     * @param containerID
     * @param deviceID
     * @param countryID
     * @return TingcoContainers
     */
    @GET
    @Produces("application/json")
    @Path("/getcontainerscalingdetails/json/{json}/containerid/{containerid}/deviceid/{deviceid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetContainerScalingDetails", desc = "Used to Get ContainerShapes", req_Params = {"containerid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getJson_getContainerScalingDetails(@PathParam("containerid") String containerID, @PathParam("deviceid") String deviceID, @PathParam("countryid") String countryID) {
        return getContainerScalingDetails(containerID, deviceID, countryID);
    }

    /**
     * DeleteDeviceLinkedToContainer
     * @param containerID
     * @param deviceID
     * @return TingcoContainers
     */
    @GET
    @Produces("application/xml")
    @Path("/deletedevicelinkedtocontainer/rest/{rest}/containerid/{containerid}/deviceid/{deviceid}")
    @RESTDoc(methodName = "DeleteDeviceLinkedToContainer", desc = "Used to Get ContainerShapes", req_Params = {"containerid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getXml_DeleteDeviceLinkedToContainer(@PathParam("containerid") String containerID, @PathParam("deviceid") String deviceID) {
        return deleteDeviceLinkedToContainer(containerID, deviceID);
    }

    /**
     * DeleteDeviceLinkedToContainer
     * @param containerID
     * @param deviceID
     * @return TingcoContainers
     */
    @GET
    @Produces("application/json")
    @Path("/deletedevicelinkedtocontainer/json/{json}/containerid/{containerid}/deviceid/{deviceid}")
    @RESTDoc(methodName = "DeleteDeviceLinkedToContainer", desc = "Used to Get ContainerShapes", req_Params = {"containerid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getJson_DeleteDeviceLinkedToContainer(@PathParam("containerid") String containerID, @PathParam("deviceid") String deviceID) {
        return deleteDeviceLinkedToContainer(containerID, deviceID);
    }

    /**
     * GetContainerInfo
     * @param containerID
     * @param CountryID
     * @return TingcoContainers
     */
    @GET
    @Produces("application/xml")
    @Path("/getcontainerinfo/rest/{rest}/containerid/{containerid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetContainerInfo", desc = "Used to Get ContainerShapes", req_Params = {"containerid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getXml_getContainerInfo(@PathParam("containerid") String containerID, @PathParam("countryid") String CountryID) {
        return getContainerInfo(containerID, CountryID);
    }

    /**
     * GetContainerInfo
     * @param containerID
     * @param CountryID
     * @return TingcoContainers
     */
    @GET
    @Produces("application/json")
    @Path("/getcontainerinfo/json/{json}/containerid/{containerid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetContainerInfo", desc = "Used to Get ContainerShapes", req_Params = {"containerid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getJson_getContainerInfo(@PathParam("containerid") String containerID, @PathParam("countryid") String CountryID) {
        return getContainerInfo(containerID, CountryID);
    }

    /**
     * GetNonLinkedDevicesForContainer
     * @param containerID
     * @param deviceName
     * @param groupName
     * @param counrtyID
     * @param groupID
     * @return TingcoContainers
     */
    @GET
    @Produces("application/xml")
    @Path("/getnonlinkeddevicesforcontainer/rest/{rest}/containerid/{containerid}/countryid/{countryid}/groupid/{groupid}{devicename:(/devicename/[^/]+?)?}{groupname:(/groupname/[^/]+?)?}")
    @RESTDoc(methodName = "GetNonLinkedDevicesForContainer", desc = "Used to Get ContainerShapes", req_Params = {"containerid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getXml_getNonLinkedDevicesForContainer(@PathParam("containerid") String containerID, @PathParam("devicename") String deviceName, @PathParam("groupname") String groupName, @PathParam("countryid") String countryID, @PathParam("groupid") String groupID) {
        return getNonLinkedDevicesForContainer(containerID, countryID, groupID, deviceName, groupName);
    }

    /**
     * GetNonLinkedDevicesForContainer
     * @param containerID
     * @param deviceName
     * @param groupName
     * @param counrtyID
     * @param groupID
     * @return TingcoContainers
     */
    @GET
    @Produces("application/json")
    @Path("/getnonlinkeddevicesforcontainer/json/{json}/containerid/{containerid}/countryid/{countryid}/groupid/{groupid}{devicename:(/devicename/[^/]+?)?}{groupname:(/groupname/[^/]+?)?}")
    @RESTDoc(methodName = "GetNonLinkedDevicesForContainer", desc = "Used to Get ContainerShapes", req_Params = {"containerid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getJson_getNonLinkedDevicesForContainer(@PathParam("containerid") String containerID, @PathParam("devicename") String deviceName, @PathParam("groupname") String groupName, @PathParam("countryid") String countryID, @PathParam("groupid") String groupID) {
        return getNonLinkedDevicesForContainer(containerID, countryID, groupID, deviceName, groupName);
    }

    @GET
    @Produces("application/xml")
    @Path("/getcontainerobjectgroupdetails/rest/{rest}/containerid/{containerid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetContainerObjectGroupDetails", desc = "Used to Get ContainerShapes", req_Params = {"containerid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getXml_getContainerObjectGroupDetails(@PathParam("containerid") String containerId, @PathParam("countryid") String countryId) {
        return getContainerObjectGroupDetails(containerId, countryId);
    }

    /**
     * DeleteOGMContainers
     * @param containerId
     * @param objectGroupId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deleteogmcontainers/rest/{rest}/containerid/{containerid}/objectgroupid/{objectgroupid}")
    @RESTDoc(methodName = "DeleteOGMContainers", desc = "Used to Delete OGMContainers", req_Params = {"ContainerId;UUID", "objectGroupId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getXml_deleteOGMContainers(@PathParam("containerid") String containerId, @PathParam("objectgroupid") String objectGroupId) throws DatatypeConfigurationException {
        return deleteOGMContainers(containerId, objectGroupId);
    }

    /**
     * DeleteOGMContainers
     * @param containerId
     * @param objectGroupId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deleteogmcontainers/json/{json}/containerid/{containerid}/objectgroupid/{objectgroupid}")
    public TingcoContainers getJson_deleteOGMContainers(@PathParam("containerid") String containerId, @PathParam("objectgroupid") String objectGroupId) throws DatatypeConfigurationException {
        return deleteOGMContainers(containerId, objectGroupId);
    }

    /**
     * GetSensorTypes
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getallsensortypes/rest/{rest}")
    @RESTDoc(methodName = "GetSensorTypes", desc = "Used to Get SensorTypes", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getXml_getSensorTypes() {
        return getSensorTypes();
    }

    /**
     * GetSensorTypes
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getallsensortypes/json/{json}")
    public TingcoContainers getJson_getSensorTypes() {
        return getSensorTypes();
    }

    /**
     * AddContainerDetails
     * @param content
     * @return TingcoContainers
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addcontainerdetails/rest/{rest}")
    public TingcoContainers postXml_AddContainerDetails(String content) {
        return addContainerDetails(content);
    }

    /**
     * AddContainerDetails
     * @param content
     * @return TingcoContainers
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addcontainerdetails/json/{json}")
    public TingcoContainers postJson_AddContainerDetails(String content) {
        return addContainerDetails(content);
    }

    /**
     * GetContainerLevelMonitorDetails
     * @param content
     * @return TingcoContainers
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getcontainerlevelmonitordetails/rest/{rest}")
    @RESTDoc(methodName = "GetContainerLevelMonitorDetails", desc = "Used to Get Container Level Monitor Details", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoContainers postXml_GetContainerLevelMonitorDetails(String content) {
        return getContainerLevelMonitorDetails(content);
    }

    /**
     * GetContainerLevelMonitorDetails
     * @param content
     * @return TingcoContainers
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getcontainerlevelmonitordetails/json/{json}")
    @RESTDoc(methodName = "GetContainerLevelMonitorDetails", desc = "Used to Get Container Level Monitor Details", req_Params = {"json;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoContainers postJson_GetContainerLevelMonitorDetails(String content) {
        return getContainerLevelMonitorDetails(content);
    }

    /**
     *  UpdateContainerInfo
     * @param content
     * @return TingcoContainers
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/updatecontainerinfo/rest/{rest}")
    public TingcoContainers postXml_UpdateContainerInfo(String content) {
        return updateContainerInfo(content);
    }

    /**
     *  UpdateContainerInfo
     * @param content
     * @return TingcoContainers
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/updatecontainerinfo/json/{json}")
    public TingcoContainers postJson_UpdateContainerInfo(String content) {
        return updateContainerInfo(content);
    }

    /**
     * AddNewDeviceToContainer
     * @param content
     * @return TingcoContainers
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addnewdevicetocontainer/rest/{rest}")
    public TingcoContainers postXml_AddNewDeviceToContainer(String content) {
        return addNewDeviceToContainer(content);
    }

    /**
     * AddNewDeviceToContainer
     * @param content
     * @return TingcoContainers
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addnewdevicetocontainer/json/{json}")
    public TingcoContainers postJson_AddNewDeviceToContainer(String content) {
        return addNewDeviceToContainer(content);
    }

    /**
     * UpdateContainerScalingDetails
     * @param content
     * @return TingcoContainers
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/updatecontainerscalingdetails/rest/{rest}")
    public TingcoContainers postXml_UpdateContainerScalingDetails(String content) {
        return updateContainerScalingDetails(content);
    }

    /**
     * UpdateContainerScalingDetails
     * @param content
     * @return TingcoContainers
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/updatecontainerscalingdetails/json/{json}")
    public TingcoContainers postJson_UpdateContainerScalingDetails(String content) {
        return updateContainerScalingDetails(content);
    }

    /**
     * UpdateContainerNotificationDetails
     * @param content
     * @return TingcoContainers
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/updatecontainernotificationdetails/rest/{rest}")
    public TingcoContainers postXml_UpdateContainerNotificationDetails(String content) {
        return UpdateContainerNotificationDetails(content);
    }

    /**
     * UpdateContainerNotificationDetails
     * @param content
     * @return TingcoContainers
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/updatecontainernotificationdetails/json/{json}")
    public TingcoContainers postjson_UpdateContainerNotificationDetails(String content) {
        return UpdateContainerNotificationDetails(content);
    }

    /**
     * AddObjectGroupsToContainer
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addobjectgroupstocontainer/rest/{rest}")
    @RESTDoc(methodName = "AddObjectGroupsToContainer", desc = "Used to Add ObjectGroup in OGMContainers Table", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoContainers postXML_addObjectGroupsToContainer(String content) {
        return addObjectGroupsToContainer(content);
    }

    /**
     * AddObjectGroupsToContainer
     * @param content
     * @return
     */
    @POST
    @Produces("application/json")
    @Consumes("application/xml")
    @Path("/addobjectgroupstocontainer/json/{json}")
    public TingcoContainers postJson_addObjectGroupsToContainer(String content) {
        return addObjectGroupsToContainer(content);
    }

    /**
     * GetContainerListReport
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getcontainerlistreport/rest/{rest}")
    @RESTDoc(methodName = "GetContainerListReport", desc = "Used to Get ContainerListReport", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoContainers postXML_getContainerListReport(String content) {
        return getContainerListReport(content);
    }

    /**
     * GetContainerListReport
     * @param content
     * @return
     */
    @POST
    @Produces("application/json")
    @Consumes("application/xml")
    @Path("/getcontainerlistreport/json/{json}")
    public TingcoContainers postJson_getContainerListReport(String content) {
        return getContainerListReport(content);
    }

    /**
     * AddContainerWizard
     * @param content
     * @return TingcoContainers
     */
    @POST
    @Produces("application/xml")
    @Consumes("application/xml")
    @Path("/addcontainerwizard/rest/{rest}")
    public TingcoContainers postXml_AddContainerWizard(String content) {
        return addContainerWizard(content);
    }

    /**
     * AddContainerWizard
     * @param content
     * @return TingcoContainers
     */
    @POST
    @Produces("application/json")
    @Consumes("application/xml")
    @Path("/addcontainerwizard/json/{json}")
    public TingcoContainers postJson_AddContainerWizard(String content) {
        return addContainerWizard(content);
    }

    /**
     * UpdateContainerInformation
     * @param content
     * @return TingcoContainers
     */
    @POST
    @Produces("application/xml")
    @Consumes("application/xml")
    @Path("/updatecontainerinformation/rest/{rest}")
    public TingcoContainers postXml_UpdateContainerInformation(String content) {
        return updateContainerInformation(content);
    }

    /**
     * UpdateContainerInformation
     * @param content
     * @return TingcoContainers
     */
    @POST
    @Produces("application/json")
    @Consumes("application/xml")
    @Path("/updatecontainerinformation/json/{json}")
    public TingcoContainers postJson_UpdateContainerInformation(String content) {
        return updateContainerInformation(content);
    }

    /**
     * POST method for creating an instance of ContainerssResource
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
     * GetContainerDetails
     * @param containerId
     * @param countryId
     * @return TingcoContainers
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getcontainerdetails/rest/{rest}/containerid/{containerid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetContainerDetails", desc = "Get Container Details", req_Params = {"ContainerId;UUID", "objectGroupId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getXml_GetContainerDetails(@PathParam("containerid") String containerId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getContainerDetails(containerId, countryId);
    }

    /**
     * GetContainerDetails
     * @param containerId
     * @param countryId
     * @return TingcoContainers
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getcontainerdetails/json/{json}/containerid/{containerid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetContainerDetails", desc = "Get Container Details", req_Params = {"ContainerId;UUID", "objectGroupId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getJson_GetContainerDetails(@PathParam("containerid") String containerId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getContainerDetails(containerId, countryId);
    }

    /**
     * GetDatatItemsForContainer
     * @param containerId
     * @param countryId
     * @return TingcoContainers
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getdatatitemsforcontainer/rest/{rest}/containerid/{containerid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetDatatItemsForContainer", desc = "Get DatatItems For Container", req_Params = {"ContainerId;UUID", "objectGroupId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getXml_GetDatatItemsForContainer(@PathParam("containerid") String containerId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getDatatItemsForContainer(containerId, countryId);
    }

    /**
     * GetDatatItemsForContainer
     * @param containerId
     * @param countryId
     * @return TingcoContainers
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getdatatitemsforcontainer/json/{json}/containerid/{containerid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetDatatItemsForContainer", desc = "Get DatatItems For Container", req_Params = {"ContainerId;UUID", "objectGroupId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getJson_GetDatatItemsForContainer(@PathParam("containerid") String containerId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getDatatItemsForContainer(containerId, countryId);
    }

    /**
     * UpdateContainerInformation
     * @param content
     * @return TingcoContainers
     */
    @POST
    @Produces("application/xml")
    @Consumes("application/xml")
    @Path("/updatecurrentfilllevel/rest/{rest}")
    public TingcoContainers postXml_UpdateCurrentFillLevel(String content) {
        return updateCurrentFillLevel(content);
    }

    /**
     * UpdateContainerInformation
     * @param content
     * @return TingcoContainers
     */
    @POST
    @Produces("application/json")
    @Consumes("application/xml")
    @Path("/updatecurrentfilllevel/json/{json}")
    public TingcoContainers postJson_UpdateCurrentFillLevel(String content) {
        return updateCurrentFillLevel(content);
    }

    /**
     * GetContainersByProductVariantID
     * @param groupid
     * @param productvariantid
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getcontainersbyproductvariantid/rest/{rest}/groupid/{groupid}/productvariantid/{productvariantid}")
    @RESTDoc(methodName = "GetContainersByProductVariantID", desc = "Get Containers By ProductVariantID", req_Params = {"GroupID;UUID", "ProductVariantID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getXml_GetContainersByProductVariantID(@PathParam("groupid") String groupid, @PathParam("productvariantid") String productvariantid) throws DatatypeConfigurationException {
        return getContainersByProductVariantID(groupid, productvariantid);
    }

    /**
     * GetContainersByProductVariantID
     * @param groupid
     * @param productvariantid
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @RESTDoc(methodName = "GetContainersByProductVariantID", desc = "Get Containers By ProductVariantID", req_Params = {"GroupID;UUID", "ProductVariantID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getJson_GetContainersByProductVariantID(@PathParam("groupid") String groupid, @PathParam("productvariantid") String productvariantid) throws DatatypeConfigurationException {
        return getContainersByProductVariantID(groupid, productvariantid);
    }

    /**
     * GetContainerDetailsForTankMonitor
     * @param fieldId
     * @param fieldValue
     * @param countryId
     * @return TingcoContainers
     */
    @GET
    @Produces("application/xml")
    @Path("/getcontainerdetailsfortankmonitor/rest/{rest}/fieldid/{fieldid}/fieldvalue/{fieldvalue}/countryid/{countryid}")
    @RESTDoc(methodName = "GetContainerDetailsForTankMonitor", desc = "Get ContainerDetails For TankMonitor", req_Params = {"fieldId;UUID", "fieldValue;string", "countryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContainers getXml_getContainerDetailsForTankMonitor(@PathParam("fieldid") String fieldId, @PathParam("fieldvalue") String fieldValue, @PathParam("countryid") int countryId) {
        return getContainerDetailsForTankMonitor(fieldId, fieldValue, countryId);
    }

    /**
     * GetContainerDetailsForTankMonitor
     * @param fieldId
     * @param fieldValue
     * @return TingcoContainers
     */
    @GET
    @Produces("application/json")
    @Path("/getcontainerdetailsfortankmonitor/json/{json}/fieldid/{fieldid}/fieldvalue/{fieldvalue}/countryid/{countryid}")
    public TingcoContainers getJson_getContainerDetailsForTankMonitor(@PathParam("fieldid") String fieldId, @PathParam("fieldvalue") String fieldValue, @PathParam("countryid") int countryId) {
        return getContainerDetailsForTankMonitor(fieldId, fieldValue, countryId);
    }

    /**
     * Sub-resource locator method for  containers
     */
    @Path("containers")
    public ContainersResource getContainersResource() {
        return new ContainersResource();
    }

    private TingcoContainers getContainerDetailsForTankMonitor(String fieldId, String fieldValue, int countryId) {
        long requestedTime = System.currentTimeMillis();
        TingcoContainers tingcoContainers = null;
        Session session = null;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                session = HibernateUtil.getSessionFactory().openSession();
                UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                List<Containers> containersList = containerDAO.getContainersForTankMonitor(fieldId, fieldValue, countryId, session);
                if (!containersList.isEmpty()) {
                    tingcoContainers = tingcoContainersXML.buildContainerLevelMonitorDetails(containersList, countryId, session, tingcoContainers, timeZoneGMToffset);
                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-1);
                    tingcoContainers.getMsgStatus().setResponseText("No data found");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
            return tingcoContainers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;


    }

    private TingcoContainers getContainersByProductVariantID(String groupid, String productvariantid) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoContainers tingcoContainers = null;
        Session session = null;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tingcoContainers = tingcoContainersXML.buildGetContainersByProductVariantID(tingcoContainers, containerDAO.getContainersByGroupIDAndProductVariantID(groupid, productvariantid, session));
                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
            return tingcoContainers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers getVisitorsList(String groupId, String containerId, String userAlias, String searchGroupId, String deviceId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {
            if (!containerId.equals("")) {
                containerId = containerId.split("/")[2];
            } else {
                containerId = null;
            }
            if (!userAlias.equals("")) {
                userAlias = userAlias.split("/")[2];
            } else {
                userAlias = null;
            }
            if (!searchGroupId.equals("")) {
                searchGroupId = searchGroupId.split("/")[2];
            } else {
                searchGroupId = null;
            }
            if (!deviceId.equals("")) {
                deviceId = deviceId.split("/")[2];
            } else {
                deviceId = null;
            }
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.REPORTS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                    List<Object> visitorsList = containerDAO.getVisitorsList(groupId, containerId, userAlias, searchGroupId, deviceId, countryId, session);

                    if (!visitorsList.isEmpty()) {
                        Set<String> useraliasId = containerDAO.getDeviceIdsListObject(visitorsList);

                        List<UserAlias> userAliases = deviceDAO.getUserAliasDetailsByUserAliasIDs(session, new ArrayList<String>(useraliasId));

                        tingcoContainers = tingcoContainersXML.buildVisitorsList(tingcoContainers, visitorsList, timeZoneGMToffset, userAliases);
                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("No data found");
                        return tingcoContainers;
                    }
                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers updateCurrentFillLevel(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoContainers tingcoContainers = null;
        Session session = null;
        Session ismOperationSession = null;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD) || operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    TingcoContainers containers = (TingcoContainers) JAXBManager.getInstance().unMarshall(content, TingcoContainers.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    ismOperationSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    int i = 0;
                    if (containers.getContainers().getContainer().size() > 0) {
                        Container containerXML = containers.getContainers().getContainer().get(0);
                        if (containerXML != null) {

                            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                            Containers containersPojo = (Containers) session.get(Containers.class, containerXML.getContainerID());
                            if (containersPojo == null) {
                                tingcoContainers.getMsgStatus().setResponseCode(-1);
                                tingcoContainers.getMsgStatus().setResponseText("Data Not Found");
                                return tingcoContainers;
                            }

                            ContainerTransactions ct = new ContainerTransactions();
                            ct.setContainerTransactionId(UUID.randomUUID().toString());
                            ct.setTransactionStartTime(gc.getTime());
                            ct.setContainerId(containersPojo.getContainerId());
                            ct.setContainerName(containersPojo.getContainerName());
//                            ct.setTransactionCounter(1);
                            ct.setContainerNumber(containersPojo.getContainerNumber());
                            ct.setGroupId(containerXML.getGroupID().getValue());
                            ct.setGroupName(containerXML.getGroupID().getGroupName());
                            ct.setVolume(containerXML.getCurrentFillLevel());
                            ct.setOldFillLevel(containersPojo.getCurrentFillLevel());
                            ct.setProductVariantId(containerXML.getProductVariantID());
                            ct.setProductVariantName(containerXML.getProductVariantName());
                            ct.setProductVariantUnit(containersPojo.getProductVariantUnit());
                            if (containerXML.getFlag().equalsIgnoreCase("GoodsOut")) {
                                double updatedVolume = containersPojo.getCurrentFillLevel().doubleValue() - containerXML.getCurrentFillLevel().doubleValue();
                                ct.setNewFillLevel(new BigDecimal(updatedVolume));
                                containersPojo.setCurrentFillLevel(new BigDecimal(updatedVolume));
                                ct.setTransactionTypeId("116ACA64-FCC5-4368-86E1-ABA2FDCC0297");
                                ct.setTransactionPurpose("GoodsOut");
                            } else if (containerXML.getFlag().equalsIgnoreCase("StockTaking")) {
                                double updatedVolume = containersPojo.getCurrentFillLevel().doubleValue() + containerXML.getCurrentFillLevel().doubleValue();
                                ct.setNewFillLevel(new BigDecimal(updatedVolume));
                                containersPojo.setCurrentFillLevel(containerXML.getCurrentFillLevel());
                                ct.setTransactionTypeId("C05B853A-4032-43BC-8945-061047620818");
                                ct.setTransactionPurpose("StockTaking");

                            } else {
                                double updatedVolume = containersPojo.getCurrentFillLevel().doubleValue() + containerXML.getCurrentFillLevel().doubleValue();
                                ct.setNewFillLevel(new BigDecimal(updatedVolume));
                                containersPojo.setCurrentFillLevel(new BigDecimal(updatedVolume));
                                ct.setTransactionTypeId("116ACA64-FCC5-4368-86E1-ABA2FDCC0297");
                                ct.setTransactionPurpose("GoodsIn");
                            }
                            ct.setLastUpdatedByUserId(sessions2.getUserId());
                            ct.setUpdatedDate(gc.getTime());
                            ct.setCreatedDate(gc.getTime());

                            containersPojo.setLastUpdatedByUserId(sessions2.getUserId());
                            containersPojo.setUpdatedDate(gc.getTime());

                            if (!containerDAO.updateCurrentFillLevel(ct, containersPojo, ismOperationSession, session)) {
                                tingcoContainers.getMsgStatus().setResponseCode(-1);
                                tingcoContainers.getMsgStatus().setResponseText("Error occured");
                                return tingcoContainers;
                            }
                            return tingcoContainers;

                        } else {
                            tingcoContainers.getMsgStatus().setResponseCode(-1);
                            tingcoContainers.getMsgStatus().setResponseText("No Containers found in XML");
                            return tingcoContainers;
                        }
                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("Invalid Container XML Found");
                        return tingcoContainers;
                    }
                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {

            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
            return tingcoContainers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoContainers getDatatItemsForContainer(String containerId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoContainers tingcoContainers = null;
        Session session = null;
        Transaction tx = null;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.CONTAINERS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<DeviceDataitemTranslations> deviceDataitemTranslationses = containerDAO.getDeviceDataItemTranslationsByDataItemNameandCountryId(session, countryId);
                    if (deviceDataitemTranslationses.isEmpty()) {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("Data Item Not Found");
                        return tingcoContainers;
                    }
                    List<String> devicedataItemId = new ArrayList<String>();
                    for (DeviceDataitemTranslations deviceDataitemTranslations : deviceDataitemTranslationses) {
                        if (deviceDataitemTranslations.getDataItemName().equalsIgnoreCase("AI1") || deviceDataitemTranslations.getDataItemName().equalsIgnoreCase("AI2") || deviceDataitemTranslations.getDataItemName().equalsIgnoreCase("AI3") || deviceDataitemTranslations.getDataItemName().equalsIgnoreCase("AI4")) {
                            devicedataItemId.add(deviceDataitemTranslations.getId().getDeviceDataItemId());
                        }
                    }
                    List<ContainerDevices> containerDeviceses = containerDAO.getContainerDevicesByContainerIdandDeviceDataItemId(session, containerId, devicedataItemId);
                    if (containerDeviceses.isEmpty()) {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("Data Item Not Found");
                        return tingcoContainers;
                    }
//                    Containers containers = (Containers) session.get(Containers.class, containerId);

                    return tingcoContainersXML.builtGetDatatItemsForContainer(tingcoContainers, deviceDataitemTranslationses, containerDeviceses);
                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
            return tingcoContainers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoContainers getContainerDetails(String containerId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoContainers tingcoContainers = null;
        Session session = null;
        Transaction tx = null;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    se.info24.pojo.Containers containerPojo = containerDAO.getContainersByContainerId(containerId, session);
                    if (containerPojo == null) {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("ContainerID is not Valid");
                        return tingcoContainers;
                    }
                    List<ObjectContactMemberships> objectContactMembershipses = deviceDAO.getObjectContactMemberships(containerId, session);
                    List<String> objectContactId = new ArrayList<String>();
                    for (ObjectContactMemberships objectContactMemberships : objectContactMembershipses) {
                        objectContactId.add(objectContactMemberships.getId().getObjectContactId());
                    }
                    List<ObjectContacts> objectContactses = new ArrayList<ObjectContacts>();
                    if (!objectContactId.isEmpty()) {
                        objectContactses = deviceDAO.getObjectContacts(objectContactId, session);
                    }

                    List<ContainerDevices> cd = deviceDAO.getContainerDevicesByContainerIdOrderBy(containerId, session);
                    GroupTranslations gt = groupDAO.getGroupTranslationsByIds(containerPojo.getGroups().getGroupId(), countryId, session);
                    ProductVariantTranslations pvt = productsDAO.getProductVariantTranslationsByIds(containerPojo.getProductVariantId(), countryId, session);
                    ProductVariants pv = productsDAO.getProductVariants(containerPojo.getProductVariantId(), session);
                    List<ContainerDevices> containerDevicesList = containerDAO.getContainerDevicesByDeviceIdByDeviceDateOrder(containerId, session);
                    return tingcoContainersXML.buildGetContainerDetails(tingcoContainers, containerPojo, objectContactses, gt, pvt, pv, session, containerDevicesList, cd, countryId);
                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
            return tingcoContainers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
//        return tingcoContainers;
    }

    private TingcoContainers updateContainerInformation(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoContainers tingcoContainers = null;
        Session session = null;
        Transaction tx = null;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    TingcoContainers containers = (TingcoContainers) JAXBManager.getInstance().unMarshall(content, TingcoContainers.class);
                    session = HibernateUtil.getSessionFactory().openSession();

                    int i = 0;
                    if (containers.getContainers().getContainer().size() > 0) {
                        Container containerXML = containers.getContainers().getContainer().get(0);
                        if (containerXML != null) {
                            return containerDAO.updateContainerInformation(session, sessions2.getUserId(), containerXML, tingcoContainers);

                        } else {
                            tingcoContainers.getMsgStatus().setResponseCode(-1);
                            tingcoContainers.getMsgStatus().setResponseText("No Containers found in XML");
                            return tingcoContainers;
                        }
                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("Invalid Container XML Found");
                        return tingcoContainers;
                    }
                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
            return tingcoContainers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoContainers addContainerWizard(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoContainers tingcoContainers = null;
        Session session = null;
        Transaction tx = null;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    TingcoContainers containers = (TingcoContainers) JAXBManager.getInstance().unMarshall(content, TingcoContainers.class);
                    session = HibernateUtil.getSessionFactory().openSession();

                    int i = 0;
                    if (containers.getContainers().getContainer().size() > 0) {
                        Container containerXML = containers.getContainers().getContainer().get(0);
                        if (containerXML != null) {
                            return containerDAO.addContainerWizard(session, sessions2.getUserId(), containerXML, tingcoContainers);

                        } else {
                            tingcoContainers.getMsgStatus().setResponseCode(-1);
                            tingcoContainers.getMsgStatus().setResponseText("No Containers found in XML");
                            return tingcoContainers;
                        }
                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("Invalid Container XML Found");
                        return tingcoContainers;
                    }
                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
            return tingcoContainers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoContainers addObjectGroupsToContainer(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoContainers tingcoContainers = null;
        Session session = null;
        Transaction tx = null;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    TingcoContainers containers = (TingcoContainers) JAXBManager.getInstance().unMarshall(content, TingcoContainers.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    int i = 0;
                    if (containers.getContainers().getContainer().size() > 0) {
                        Container containerXML = containers.getContainers().getContainer().get(0);
                        if (containerXML != null) {
                            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                            String containerId = containerXML.getOGMContainers().get(0).getContainerID();
                            List<String> objectGroupidList = containerXML.getOGMContainers().get(0).getObjectGroupID();
                            String scheduleId = null;
                            if (containerXML.getOGMContainers().get(0).getScheduleID() != null) {
                                scheduleId = containerXML.getOGMContainers().get(0).getScheduleID();
                            }
                            Ogmcontainers ogmContainers = null;
                            for (String objectGroupId : objectGroupidList) {
                                ogmContainers = new Ogmcontainers();
                                ogmContainers.setId(new OgmcontainersId(containerId, objectGroupId));
                                if (scheduleId != null) {
                                    ogmContainers.setScheduleId(scheduleId);
                                }
                                ogmContainers.setLastUpdatedByUserId(sessions2.getUserId());
                                ogmContainers.setCreatedDate(gc.getTime());
                                ogmContainers.setUpdatedDate(gc.getTime());
                                session.saveOrUpdate(ogmContainers);
                                i++;
                                if (i % 20 == 0) {
                                    session.flush();
                                    session.clear();
                                }
                            }
                            tx.commit();
                        } else {
                            tingcoContainers.getMsgStatus().setResponseCode(-1);
                            tingcoContainers.getMsgStatus().setResponseText("No Containers found in XML");
                            return tingcoContainers;
                        }
                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("Invalid Container XML Found");
                        return tingcoContainers;
                    }
                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
            return tingcoContainers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers deleteOGMContainers(String containerId, String objectGroupId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoContainers tingcoContainers = null;
        Session session = null;
        Transaction tx = null;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    Ogmcontainers ogmContainers = containerDAO.getOGMContainersByContainerIdAndObjectGroupId(containerId, objectGroupId, session);
                    if (ogmContainers != null) {
                        session.delete(ogmContainers);
                        tx.commit();
                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("No data found for the given input");
                        return tingcoContainers;
                    }
                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
            return tingcoContainers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers getAllUnits(int countryId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<UnitTranslations> unitTransList = containerDAO.getUnitTranslationsByCountryId(session, countryId);
                    if (!unitTransList.isEmpty()) {
                        return tingcoContainersXML.buildGetAllUnits(tingcoContainers, unitTransList);
                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("Data not found");
                        return tingcoContainers;
                    }

                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers getContainerInfoForTankContainer(String assetId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Users2 users2 = userDAO.getUserById(sessions2.getUserId(), session);
                    List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(users2.getGroupId(), session);
                    Set<String> groupIdsList = groupDAO.getGroupIdsList(users2.getGroupId(), groupTrustsList);

                    /**
                     * SPLIT LIST SIZE 30 MAR 2014
                     */
                    List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupIdsList), 2000);
                    List<Containers> container = new ArrayList<Containers>();
                    for (List<String> list : listsplit) {
                        List<Containers> containerListTemp = containerDAO.getContainersByAssetId(new HashSet<String>(list), assetId, session);
                        container.addAll(containerListTemp);
                    }
                    //List<Containers> container = containerDAO.getContainersByAssetId(groupIdsList, assetId, session);
                    if (!container.isEmpty()) {
                        tingcoContainers = tingcoContainersXML.buildContainers(tingcoContainers, container.get(0));
                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoContainers;
                    }
                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers getContainerListReport(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoContainers tingcoContainers = null;
        Session session = null;
        Transaction tx = null;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
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
                    TingcoContainers containers = (TingcoContainers) JAXBManager.getInstance().unMarshall(content, TingcoContainers.class);
                    if (containers.getContainers().getContainer().size() > 0) {
                        Container containerXML = containers.getContainers().getContainer().get(0);
                        if (containerXML != null) {
                            session = HibernateUtil.getSessionFactory().openSession();

                            Set<String> set = groupDAO.getGroupsAndGroupTrusts(containerXML.getGroupID().getValue(), session);
                            List<Containers> containersList = new ArrayList<Containers>();
                            List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(set), 2000);
                            for (List<String> list : listsplit) {
                                List<Containers> containersListtemp = containerDAO.getContainersListReport(containerXML, new HashSet<String>(list), session);
                                containersList.addAll(containersListtemp);
                            }

                            set.clear();
                            if (!containersList.isEmpty()) {
                                set = containerDAO.getContainerIds(containersList);
                                containersList.clear();
                                containersList = containerDAO.getContainersOrderByName(set, session);
                                tingcoContainers = tingcoContainersXML.buildContainerListReport(tingcoContainers, containersList, containerXML.getCountry().getCountryID(), session);
                            } else {
                                tingcoContainers.getMsgStatus().setResponseCode(-1);
                                tingcoContainers.getMsgStatus().setResponseText("No Data found");
                                return tingcoContainers;
                            }
                        } else {
                            tingcoContainers.getMsgStatus().setResponseCode(-1);
                            tingcoContainers.getMsgStatus().setResponseText("No Containers found in XML");
                            return tingcoContainers;
                        }
                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("Invalid Container XML Found");
                        return tingcoContainers;
                    }
                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
            return tingcoContainers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers getContainerObjectGroupDetails(String containerId, String countryId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<Ogmcontainers> ogmContainerList = containerDAO.getOgmcontainersById(session, containerId);
                    if (ogmContainerList.isEmpty()) {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoContainers;
                    }
                    Set<String> objectGroupId = new HashSet<String>();
                    for (Ogmcontainers ogmcontainers : ogmContainerList) {
                        objectGroupId.add(ogmcontainers.getObjectGroups().getObjectGroupId());
                    }
                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers getNonLinkedDevicesForContainer(String containerID, String countryID, String groupID, String deviceName, String groupName) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {
            if (!deviceName.equals("")) {
                deviceName = deviceName.split("/")[2];
            } else {
                deviceName = null;
            }
            if (!groupName.equals("")) {
                groupName = groupName.split("/")[2];
            } else {
                groupName = null;
            }
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(groupID, session);
                    Set<String> groupidset = groupDAO.getGroupIdsList(groupID, groupTrustsList);

                    List<String> ContainerDeviceList = new ArrayList<String>();
                    List<ContainerDevices> containerDeviceList = containerDAO.getContainerDevicesByContainerId(containerID, session);
                    for (ContainerDevices containerDevices : containerDeviceList) {
                        ContainerDeviceList.add(containerDevices.getId().getDeviceId());
                    }
//                    if(ContainerDeviceList.isEmpty()){
//                        tingcoContainers.getMsgStatus().setResponseCode(-1);
//                        tingcoContainers.getMsgStatus().setResponseText("Data Not Found in ContainerDevices");
//                        return tingcoContainers;
//                    }
                    List<String> deviceId = new ArrayList<String>();
                    if (deviceName == null && groupName == null) {
                        /**
                         * SPLIT LIST SIZE 30 MAR 2014
                         */
                        List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupidset), 2000);
                        List<Device> deviceList = new ArrayList<Device>();
                        for (List<String> list : listsplit) {
                            List<Device> deviceListTemp = deviceDAO.getDeviceDetailsByGroupId(session, new ArrayList<String>(list));
                            deviceList.addAll(deviceListTemp);
                        }
                        //List<Device> deviceList = deviceDAO.getDeviceDetailsByGroupId(session, new ArrayList<String>(groupidset));
                        for (Device device : deviceList) {
                            deviceId.add(device.getDeviceId());
                        }
                    } else if (deviceName != null && groupName == null) {
                        /**
                         * SPLIT LIST SIZE 30 MAR 2014
                         */
                        List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupidset), 2000);
//                        List<Device> deviceList = deviceDAO.getDeviceBySearchdeviceName(session, deviceName, groupidset);
                        List<Device> deviceList = new ArrayList<Device>();
                        for (List<String> list : listsplit) {
                            List<Device> deviceListTemp = deviceDAO.getDeviceBySearchdeviceName(session, deviceName, new HashSet<String>(list));
                            deviceList.addAll(deviceListTemp);
                        }

                        //List<Device> deviceList = deviceDAO.getDeviceBySearchdeviceName(session, deviceName, groupidset);
                        for (Device device : deviceList) {
                            deviceId.add(device.getDeviceId());
                        }
                    } else if (deviceName == null && groupName != null) {
                        /**
                         * SPLIT LIST SIZE 30 MAR 2014
                         */
                        List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupidset), 2000);
                        List<GroupTranslations> gtList = new ArrayList<GroupTranslations>();
                        for (List<String> list : listsplit) {
                            List<GroupTranslations> gtListTemp = deviceDAO.getGroupTransSearchByGroupName(session, new ArrayList<String>(list), groupName, Integer.valueOf(countryID));
                            gtList.addAll(gtListTemp);
                        }

                        //List<GroupTranslations> gtList = deviceDAO.getGroupTransSearchByGroupName(session, new ArrayList<String>(groupidset), groupName, Integer.valueOf(countryID));
                        List<String> groupidList = new ArrayList<String>();
                        for (GroupTranslations groupTranslations : gtList) {
                            groupidList.add(groupTranslations.getId().getGroupId());
                        }
                        if (!groupidList.isEmpty()) {
                            List<Device> deviceList = deviceDAO.getDeviceDetailsByGroupId(session, groupidList);
                            for (Device device : deviceList) {
                                deviceId.add(device.getDeviceId());
                            }
                        }
                    } else if (deviceName != null && groupName != null) {
                        /**
                         * SPLIT LIST SIZE 30 MAR 2014
                         */
                        List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupidset), 2000);
                        List<GroupTranslations> gtList = new ArrayList<GroupTranslations>();
                        for (List<String> list : listsplit) {
                            List<GroupTranslations> gtListTemp = deviceDAO.getGroupTransSearchByGroupName(session, new ArrayList<String>(list), groupName, Integer.valueOf(countryID));
                            gtList.addAll(gtListTemp);
                        }

                        //List<GroupTranslations> gtList = deviceDAO.getGroupTransSearchByGroupName(session, new ArrayList<String>(groupidset), groupName, Integer.valueOf(countryID));
                        Set<String> groupidSet = new HashSet<String>();
                        for (GroupTranslations groupTranslations : gtList) {
                            groupidSet.add(groupTranslations.getId().getGroupId());
                        }
                        if (!groupidSet.isEmpty()) {
                            List<Device> deviceList = deviceDAO.getDeviceBySearchdeviceName(session, deviceName, groupidSet);
                            for (Device device : deviceList) {
                                deviceId.add(device.getDeviceId());
                            }
                        }
                    }
                    if (deviceId.isEmpty()) {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoContainers;
                    }

                    List<Device> devices = new ArrayList<Device>();

                    List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(deviceId), 2000);
                    for (List<String> list : listsplit) {
                        List<Device> devicesTemp = containerDAO.getNonLinkedDeviceForContainer(session, list, ContainerDeviceList);
                        if (devicesTemp != null) {
                            devices.addAll(devicesTemp);
                        }
                    }

                    Collections.sort(devices, new Comparator<se.info24.pojo.Device>() {

                        public int compare(se.info24.pojo.Device o1, se.info24.pojo.Device o2) {
                            return o1.getDeviceName().compareToIgnoreCase(o2.getDeviceName());
                        }
                    });

                    /**
                     * SPLIT LIST SIZE 30 MAR 2014
                     */
                    listsplit = TCMUtil.splitStringList(new ArrayList(groupidset), 2000);
                    List<GroupTranslations> groupTranslationList = new ArrayList<GroupTranslations>();
                    for (List<String> list : listsplit) {
                        List<GroupTranslations> groupTranslationListTemp = deviceDAO.getGroupTranslationsById(new HashSet<String>(list), Integer.valueOf(countryID), session);
                        groupTranslationList.addAll(groupTranslationListTemp);
                    }
                    //List<GroupTranslations> groupTranslationList = deviceDAO.getGroupTranslationsById(groupidset, Integer.valueOf(countryID), session);
                    if (devices.size() > 100) {
                        return tingcoContainersXML.buildGetNonLinkedDevicesForContainer(tingcoContainers, devices.subList(0, 100), groupTranslationList);
                    } else {
                        return tingcoContainersXML.buildGetNonLinkedDevicesForContainer(tingcoContainers, devices, groupTranslationList);
                    }

                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers getContainerInfo(String cotainerID, String CountryID) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    Containers container = containerDAO.getContainersByContainerId(cotainerID, session);
                    if (container == null) {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoContainers;
                    }
                    if (userTimeZones2 == null) {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("User TimeZone is not found");
                        return tingcoContainers;
                    }
                    String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();

                    GroupTranslations gt = groupDAO.getGroupTranslationsByIds(container.getGroups().getGroupId(), Integer.valueOf(CountryID), session);
                    ProductVariantTranslations pvt = productsDAO.getProductVariantTranslationsByIds(container.getProductVariantId(), Integer.valueOf(CountryID), session);
                    List<ObjectContactMemberships> ocm = deviceDAO.getObjectContactMemberships(cotainerID, session);
                    List<ObjectContacts> objectContactList = new ArrayList<ObjectContacts>();
                    if (!ocm.isEmpty()) {
                        List<String> objectcontatctId = new ArrayList<String>();
                        for (ObjectContactMemberships objectContactMemberships : ocm) {
                            objectcontatctId.add(objectContactMemberships.getId().getObjectContactId());
                        }
                        objectContactList = deviceDAO.getObjectContacts(objectcontatctId, session);
                    }
                    ObjectStateCodeTranslations osct = null;
                    List<ObjectContactTypeTranslations> octt = new ArrayList<ObjectContactTypeTranslations>();
                    if (container.getObjectStateCodeId() != null) {
                        if (!objectContactList.isEmpty()) {
                            List<String> objectContactTypeId = new ArrayList<String>();
                            for (ObjectContacts objectContacts : objectContactList) {
                                objectContactTypeId.add(objectContacts.getObjectContactTypes().getObjectContactTypeId());
                            }
                            octt = containerDAO.getObjectContactTypeTranslationsByIds(session, CountryID, objectContactTypeId);
                        }
                        osct = containerDAO.getObjectStateCodeTranslationsByCountryId(session, CountryID, container.getObjectStateCodeId());
                    }
                    UserDAO userDAO = new UserDAO();
                    Users2 users2 = userDAO.getUserById(sessions2.getUserId(), session);
                    NetworkDAO networkDAO = new NetworkDAO();

                    List<ObjectCostCenters> costCenterses = networkDAO.getObjectCostCentersByGroupIdandObjectId(session, container.getContainerId(), users2.getGroupId());
                    return tingcoContainersXML.buildGetContainerInfo(tingcoContainers, container, gt, pvt, objectContactList, osct, octt, timeZoneGMToffset, costCenterses);
                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers UpdateContainerNotificationDetails(String content) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    se.info24.containersjaxb.TingcoContainers containers = (se.info24.containersjaxb.TingcoContainers) JAXBManager.getInstance().unMarshall(content, se.info24.containersjaxb.TingcoContainers.class);

                    if (containers.getContainers().getContainer().size() > 0) {
                        se.info24.containersjaxb.Container con = containers.getContainers().getContainer().get(0);

                        Containers container = containerDAO.getContainersByContainerId(con.getContainerID(), session);
                        if (container == null) {
                            tingcoContainers.getMsgStatus().setResponseCode(-1);
                            tingcoContainers.getMsgStatus().setResponseText("Data Not Found");
                            return tingcoContainers;
                        }

                        container.setEventSendingDeviceId(con.getEventSendingDeviceID());

                        String strNullable = null;
                        if (con.getWarningLowValue() != null) {
                            container.setWarningLowValue(con.getWarningLowValue());
                        } else {
                            container.setWarningLowValue(null);
                        }
                        if (con.getWarningHighValue() != null) {
                            container.setWarningHighValue(con.getWarningHighValue());
                        } else {
                            container.setWarningHighValue(null);
                        }
                        if (con.getWarningLowEventTypeID() != null) {
                            container.setWarningLowEventTypeId(con.getWarningLowEventTypeID());
                        } else {
                            container.setWarningLowEventTypeId(strNullable);
                        }

                        if (con.getWarningHighEventTypeID() != null) {
                            container.setWarningHighEventTypeId(con.getWarningHighEventTypeID());
                        } else {
                            container.setWarningHighEventTypeId(strNullable);
                        }


                        if (con.getAlarmLowValue() != null) {
                            container.setAlarmLowValue(con.getAlarmLowValue());
                        } else {
                            container.setAlarmLowValue(null);
                        }
                        if (con.getAlarmHighValue() != null) {
                            container.setAlarmHighValue(con.getAlarmHighValue());
                        } else {
                            container.setAlarmHighValue(null);
                        }
                        if (con.getAlarmLowEventTypeID() != null) {
                            container.setAlarmLowEventTypeId(con.getAlarmLowEventTypeID());
                        } else {
                            container.setAlarmLowEventTypeId(strNullable);
                        }

                        if (con.getAlarmHighEventTypeID() != null) {
                            container.setAlarmHighEventTypeId(con.getAlarmHighEventTypeID());
                        } else {
                            container.setAlarmHighEventTypeId(strNullable);
                        }

                        if (con.getOrderMoreBelowThisValue() != null) {
                            container.setOrderMoreBelowThisValue(con.getOrderMoreBelowThisValue());
                        } else {
                            container.setOrderMoreBelowThisValue(null);
                        }
                        if (con.getOrderMoreEventTypeID() != null) {
                            container.setOrderMoreEventTypeId(con.getOrderMoreEventTypeID());
                        } else {
                            container.setOrderMoreEventTypeId(strNullable);
                        }
                        if (con.getFlagSetValueToMaxIfAboveMax() != null) {
                            container.setFlagSetValueToMaxIfAboveMax(con.getFlagSetValueToMaxIfAboveMax().intValue());
                        } else {
                            container.setFlagSetValueToMaxIfAboveMax(null);
                        }
                        if (con.getFlagSetValueToMinIfBelowMin() != null) {
                            container.setFlagSetValueToMinIfBelowMin(con.getFlagSetValueToMinIfBelowMin().intValue());
                        } else {
                            container.setFlagSetValueToMinIfBelowMin(null);
                        }
                        if (con.getEmptyPublicText() != null) {
                            container.setEmptyPublicText(con.getEmptyPublicText());
                        } else {
                            container.setEmptyPublicText(strNullable);
                        }
                        if (con.getFullPublicText() != null) {
                            container.setFullPublicText(con.getFullPublicText());
                        } else {
                            container.setFullPublicText(strNullable);
                        }
                        if (!containerDAO.UpdateContainerNotificationDetails(session, container)) {
                            tingcoContainers.getMsgStatus().setResponseCode(-1);
                            tingcoContainers.getMsgStatus().setResponseText("Error occured");
                        }
                        return tingcoContainers;
                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("XML does not contain Containers");
                        return tingcoContainers;
                    }

                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers deleteDeviceLinkedToContainer(String cotainerID, String deviceID) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ContainerDevices cd = containerDAO.getContainerDevicesByContainerIdandDeviceId(session, cotainerID, deviceID);
                    if (cd == null) {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoContainers;
                    }
                    if (!containerDAO.deleteDeviceLinkedToContainer(session, cd)) {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("Error occured");
                    }
                    return tingcoContainers;
                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers getSensorTypes() {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<se.info24.pojo.SensorTypes> sensorTypesList = containerDAO.getAllSensorTypes(session);
                    if (!sensorTypesList.isEmpty()) {
                        tingcoContainers = tingcoContainersXML.buildGetAllSensorTypes(tingcoContainers, sensorTypesList);
                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoContainers;
                    }
                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers updateContainerScalingDetails(String content) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    se.info24.containersjaxb.TingcoContainers containers = (se.info24.containersjaxb.TingcoContainers) JAXBManager.getInstance().unMarshall(content, se.info24.containersjaxb.TingcoContainers.class);
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));

                    if (containers.getContainers().getContainer().size() > 0) {
                        if (containers.getContainers().getContainer().get(0).getContainerDevices().size() > 0) {
                            se.info24.containersjaxb.ContainerDevices containerDevices = containers.getContainers().getContainer().get(0).getContainerDevices().get(0);
                            ContainerDevices cd = containerDAO.getContainerDevicesByContainerIdandDeviceId(session, containerDevices.getContainerID(), containerDevices.getDeviceID());
                            if (cd == null) {
                                cd = new ContainerDevices();
                                ContainerDevicesId id = new ContainerDevicesId();
                                id.setContainerId(containerDevices.getContainerID());
                                id.setDeviceId(containerDevices.getDeviceID());
                                cd.setId(id);
                                cd.setDeviceDataItemId(containerDevices.getDeviceDataItemID());
                                cd.setFillDirection(containerDevices.getFillDirection());
                                cd.setFlagScaleValue(containerDevices.getFlagScaleValue());
                                cd.setIsEnabled(containerDevices.getIsEnabled());
                                if (containerDevices.getRawMaxValue() != null) {
                                    cd.setRawMaxValue(containerDevices.getRawMaxValue());
                                } else {
                                    cd.setRawMaxValue(null);
                                }
                                if (containerDevices.getRawMinValue() != null) {
                                    cd.setRawMinValue(containerDevices.getRawMinValue());
                                } else {
                                    cd.setRawMinValue(null);
                                }
                                if (containerDevices.getScaledMaxValue() != null) {
                                    cd.setScaledMaxValue(containerDevices.getScaledMaxValue());
                                } else {
                                    cd.setScaledMaxValue(null);
                                }
                                if (containerDevices.getScaledMinValue() != null) {
                                    cd.setScaledMinValue(containerDevices.getScaledMinValue());
                                } else {
                                    cd.setScaledMinValue(null);
                                }
                                if (containerDevices.getScalingOperand() != null) {
                                    cd.setScalingOperand(containerDevices.getScalingOperand());
                                } else {
                                    cd.setScalingOperand(null);
                                }
                                if (containerDevices.getScalingFactor() != null) {
                                    cd.setScalingFactor(containerDevices.getScalingFactor());
                                } else {
                                    cd.setScalingFactor(null);
                                }
                                if (containerDevices.getSensorTypeID() != null) {
                                    cd.setSensorTypeId(containerDevices.getSensorTypeID());
                                } else {
                                    cd.setSensorTypeId(null);
                                }
                                cd.setLastUpdatedByUserId(sessions2.getUserId());
                                cd.setCreatedDate(gc.getTime());
                                cd.setUpdatedDate(gc.getTime());
                            } else {
//                                ContainerDevicesId id = new ContainerDevicesId();
//                                id.setContainerId(containerDevices.getContainerID());
//                                id.setDeviceId(containerDevices.getDeviceID());
//                                cd.setId(id);
                                cd.setDeviceDataItemId(containerDevices.getDeviceDataItemID());
                                cd.setFillDirection(containerDevices.getFillDirection());
                                cd.setFlagScaleValue(containerDevices.getFlagScaleValue());
                                cd.setIsEnabled(containerDevices.getIsEnabled());
                                if (containerDevices.getRawMaxValue() != null) {
                                    cd.setRawMaxValue(containerDevices.getRawMaxValue());
                                } else {
                                    cd.setRawMaxValue(null);
                                }
                                if (containerDevices.getRawMinValue() != null) {
                                    cd.setRawMinValue(containerDevices.getRawMinValue());
                                } else {
                                    cd.setRawMinValue(null);
                                }
                                if (containerDevices.getScaledMaxValue() != null) {
                                    cd.setScaledMaxValue(containerDevices.getScaledMaxValue());
                                } else {
                                    cd.setScaledMaxValue(null);
                                }
                                if (containerDevices.getScaledMinValue() != null) {
                                    cd.setScaledMinValue(containerDevices.getScaledMinValue());
                                } else {
                                    cd.setScaledMinValue(null);
                                }
                                if (containerDevices.getScalingOperand() != null) {
                                    cd.setScalingOperand(containerDevices.getScalingOperand());
                                } else {
                                    cd.setScalingOperand(null);
                                }
                                if (containerDevices.getScalingFactor() != null) {
                                    cd.setScalingFactor(containerDevices.getScalingFactor());
                                } else {
                                    cd.setScalingFactor(null);
                                }
                                if (containerDevices.getSensorTypeID() != null) {
                                    cd.setSensorTypeId(containerDevices.getSensorTypeID());
                                } else {
                                    cd.setSensorTypeId(null);
                                }
                                cd.setUpdatedDate(gc.getTime());
                            }
                            if (!containerDAO.addNewDeviceToContainer(session, cd)) {
                                tingcoContainers.getMsgStatus().setResponseCode(-1);
                                tingcoContainers.getMsgStatus().setResponseText("Error occured");
                            }
                            return tingcoContainers;
                        } else {
                            tingcoContainers.getMsgStatus().setResponseCode(-1);
                            tingcoContainers.getMsgStatus().setResponseText("XML does not contain ContainerDevices");
                            return tingcoContainers;
                        }
                    }
                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers addNewDeviceToContainer(String content) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    se.info24.containersjaxb.TingcoContainers containers = (se.info24.containersjaxb.TingcoContainers) JAXBManager.getInstance().unMarshall(content, se.info24.containersjaxb.TingcoContainers.class);
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));

                    if (containers.getContainers().getContainer().size() > 0) {
                        if (containers.getContainers().getContainer().get(0).getContainerDevices().size() > 0) {
                            se.info24.containersjaxb.ContainerDevices containerDevices = containers.getContainers().getContainer().get(0).getContainerDevices().get(0);
                            ContainerDevices cd = containerDAO.getContainerDevicesByContainerIdandDeviceId(session, containerDevices.getContainerID(), containerDevices.getDeviceID());
                            if (cd == null) {
                                cd = new ContainerDevices();
                                cd.setId(new ContainerDevicesId(containerDevices.getContainerID(), containerDevices.getDeviceID()));
                                cd.setDeviceDataItemId(containerDevices.getDeviceDataItemID());
                                cd.setFillDirection(containerDevices.getFillDirection());
                                cd.setFlagScaleValue(containerDevices.getFlagScaleValue());
                                cd.setIsEnabled(containerDevices.getIsEnabled());
                                if (containerDevices.getRawMaxValue() != null) {
                                    cd.setRawMaxValue(containerDevices.getRawMaxValue());
                                }
                                if (containerDevices.getRawMinValue() != null) {
                                    cd.setRawMinValue(containerDevices.getRawMinValue());
                                }
                                if (containerDevices.getScaledMaxValue() != null) {
                                    cd.setScaledMaxValue(containerDevices.getScaledMaxValue());
                                }
                                if (containerDevices.getScaledMinValue() != null) {
                                    cd.setScaledMinValue(containerDevices.getScaledMinValue());
                                }
                                if (containerDevices.getScalingOperand() != null) {
                                    cd.setScalingOperand(containerDevices.getScalingOperand());
                                }
                                if (containerDevices.getScalingFactor() != null) {
                                    cd.setScalingFactor(containerDevices.getScalingFactor());
                                }
                                if (containerDevices.getSensorTypeID() != null) {
                                    cd.setSensorTypeId(containerDevices.getSensorTypeID());
                                }
                                cd.setCreatedDate(gc.getTime());
                                cd.setUpdatedDate(gc.getTime());
                                if (!containerDAO.addNewDeviceToContainer(session, cd)) {
                                    tingcoContainers.getMsgStatus().setResponseCode(-1);
                                    tingcoContainers.getMsgStatus().setResponseText("Error occured");
                                }
                                return tingcoContainers;
                            } else {
                                tingcoContainers.getMsgStatus().setResponseCode(-1);
                                tingcoContainers.getMsgStatus().setResponseText("Data Already Available");
                                return tingcoContainers;
                            }
                        } else {
                            tingcoContainers.getMsgStatus().setResponseCode(-1);
                            tingcoContainers.getMsgStatus().setResponseText("XML does not contain ContainerDevices");
                            return tingcoContainers;
                        }
                    }
                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers getContainerScalingDetails(String cotainerID, String deviceID, String countryID) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ContainerDevices containerDevice = containerDAO.getContainerDevicesByContainerIdandDeviceId(session, cotainerID, deviceID);
                    if (containerDevice != null) {
                        SensorTypes sensorTypes = containerDAO.getSensorTypesBySensorTypeId(containerDevice.getSensorTypeId(), session);
                        DeviceDataitemTranslations deviceDataitemTranslations = deviceDAO.getDeviceDataItemTranslationsById(session, containerDevice.getDeviceDataItemId(), Integer.valueOf(countryID));
                        return tingcoContainersXML.buildGetContainerScalingDetails(tingcoContainers, containerDevice, deviceDataitemTranslations, sensorTypes);

                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoContainers;
                    }

                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers getDevicesForContainer(String cotainerID) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ContainerDevices> containerDeviceList = containerDAO.getContainerDevicesByContainerId(cotainerID, session);
                    if (!containerDeviceList.isEmpty()) {
                        List<String> deviceId = new ArrayList<String>();
                        for (ContainerDevices containerDevices : containerDeviceList) {
                            deviceId.add(containerDevices.getDevice().getDeviceId());
                        }
                        List<Device> deviceList = deviceDAO.getDeviceByDeviceIdsList(session, deviceId);
                        return tingcoContainersXML.buildGetDevicesForContainer(tingcoContainers, deviceList);
                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoContainers;
                    }

                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers updateContainerInfo(String content) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();

            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    se.info24.containersjaxb.TingcoContainers containers = (se.info24.containersjaxb.TingcoContainers) JAXBManager.getInstance().unMarshall(content, se.info24.containersjaxb.TingcoContainers.class);
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    GregorianCalendar gc1 = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    if (containers.getContainers().getContainer().size() > 0) {
                        se.info24.containersjaxb.Container con = containers.getContainers().getContainer().get(0);
                        session = HibernateUtil.getSessionFactory().openSession();
                        Containers c = containerDAO.getContainersByContainerId(con.getContainerID(), session);
                        String stringNull = null;
                        if (c != null) {
                            UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                            if (userTimeZones2 != null) {
                                String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                                c.setContainerName(con.getContainerName());
                                ContainerTypes containerType = containerDAO.getContainerTypesById(con.getContainerTypeID(), session);
                                if (containerType != null) {
                                    c.setContainerTypes(containerType);
                                } else {
                                    TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Container", con.getContainerName(), "Containers", "Failed");
                                    tingcoContainers.getMsgStatus().setResponseCode(-1);
                                    tingcoContainers.getMsgStatus().setResponseText("ContainerTypeId is not Valid");
                                    return tingcoContainers;
                                }
                                ContainerShapes cs = containerDAO.getContainerShapesById(con.getContainerShapeID(), session);
                                if (cs != null) {
                                    c.setContainerShapes(cs);
                                } else {
                                    TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Container", con.getContainerName(), "Containers", "Failed");
                                    tingcoContainers.getMsgStatus().setResponseCode(-1);
                                    tingcoContainers.getMsgStatus().setResponseText("ContainerShapeID is not Valid");
                                    return tingcoContainers;
                                }
                                c.setContainerNumber(con.getContainerNumber());
                                if (con.getAssetID() != null) {
                                    c.setAssetId(con.getAssetID());
                                } else {
                                    c.setAssetId(stringNull);
                                }
//                                c.setGroupId(con.getGroupID().getValue());
                                Groups groups = groupDAO.getGroupByGroupId(con.getGroupID().getValue(), session);
                                c.setGroups(groups);
                                List<String> aidList = new ArrayList<String>();
                                aidList.add(con.getAgreementID());
                                Agreements agreements = containerDAO.getAgreements(aidList, session);
                                if (agreements != null) {
                                    c.setAgreements(agreements);
                                } else {
                                    TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Container", con.getContainerName(), "Containers", "Failed");
                                    tingcoContainers.getMsgStatus().setResponseCode(-1);
                                    tingcoContainers.getMsgStatus().setResponseText("AgreementID is not Valid");
                                    return tingcoContainers;
                                }

                                ObjectCostCenters costCenters = null;
                                boolean isDelete = false;
                                NetworkDAO networkDAO = new NetworkDAO();
                                UserDAO userDAO = new UserDAO();
                                Users2 users2 = userDAO.getUserById(sessions2.getUserId(), session);
                                List<ObjectCostCenters> objectCostCenterses = networkDAO.getObjectCostCentersByGroupIdandObjectId(session, c.getContainerId(), users2.getGroupId());
                                if (con.getCostCenterID() != null) {
                                    CostCenters cc = groupDAO.getCostCenterById(con.getCostCenterID(), session);
                                    if (cc != null) {
                                        c.setCostCenters(cc);
                                        if (!objectCostCenterses.isEmpty()) {
                                            costCenters = objectCostCenterses.get(0);
                                            costCenters.setCostCenterId(con.getCostCenterID());
                                            costCenters.setLastUpdatedByUserId(sessions2.getUserId());
                                            costCenters.setUpdatedDate(gc.getTime());
                                        } else {
                                            costCenters = new ObjectCostCenters();
                                            ObjectCostCentersId ids = new ObjectCostCentersId();
                                            ids.setObjectId(c.getContainerId());
                                            ids.setGroupId(groups.getGroupId());
                                            costCenters.setId(ids);
                                            costCenters.setCostCenterId(con.getCostCenterID());
                                            costCenters.setLastUpdatedByUserId(sessions2.getUserId());
                                            costCenters.setUpdatedDate(gc.getTime());
                                            costCenters.setCreatedDate(gc.getTime());
                                        }
                                    } else {
                                        TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Container", con.getContainerName(), "Containers", "Failed");
                                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                                        tingcoContainers.getMsgStatus().setResponseText("CostCenterID is not Valid");
                                        return tingcoContainers;
                                    }
                                } else {
                                    c.setCostCenters(null);
                                    if (!objectCostCenterses.isEmpty()) {
                                        costCenters = objectCostCenterses.get(0);
                                        isDelete = true;
                                    }
                                }
                                if (con.getContainerDescription() != null) {
                                    c.setContainerDescription(con.getContainerDescription());
                                } else {
                                    c.setContainerDescription(stringNull);
                                }
                                if (con.getOpenScheduleID() != null) {
                                    Schedules schedule = containerDAO.getSchedulesByID(con.getOpenScheduleID(), session);
                                    if (schedule != null) {
                                        c.setSchedules(schedule);
                                    } else {
                                        TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Container", con.getContainerName(), "Containers", "Failed");
                                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                                        tingcoContainers.getMsgStatus().setResponseText("OpenScheduleID is not Valid");
                                        return tingcoContainers;
                                    }
                                }
                                c.setIsOpen(con.getIsOpen());
                                c.setObjectStateCodeId(con.getObjectStateCodeID());
                                c.setIsEnabled(con.getIsEnabled());
                                c.setIsMobileContainer(con.getIsMobileContainer());
                                if (con.getActiveFromDate() != null) {
                                    gc1.setTime(df.parse(con.getActiveFromDate()));
                                    gc1.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc1.getTime()));
                                    c.setActiveFromDate(gc1.getTime());
                                } else {
                                    c.setActiveFromDate(null);
                                }
                                if (con.getActiveToDate() != null) {
                                    gc1.setTime(df.parse(con.getActiveToDate()));
                                    gc1.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc1.getTime()));
                                    c.setActiveToDate(gc1.getTime());
                                } else {
                                    c.setActiveToDate(null);
                                }
                                if (con.getProductVariantID() != null) {
                                    c.setProductVariantId(con.getProductVariantID());
                                } else {
                                    c.setProductVariantId(null);
                                }
                                if (con.getProductVariantUnit() != null) {
                                    c.setProductVariantUnit(con.getProductVariantUnit());
                                } else {
                                    c.setProductVariantUnit(null);
                                }
                                if (con.getCurrentFillLevel() != null) {
                                    c.setCurrentFillLevel(con.getCurrentFillLevel());
                                } else {
                                    c.setCurrentFillLevel(null);
                                }

                                c.setContainerMinLevel(con.getContainerMinLevel());
                                c.setContainerMaxLevel(con.getContainerMaxLevel());
                                c.setContainerEmptyLevel(con.getContainerEmptyLevel());
                                c.setContainerFullLevel(con.getContainerFullLevel());
                                if (con.getContainerHeight() != null) {
                                    c.setContainerHeight(con.getContainerHeight());
                                } else {
                                    c.setContainerHeight(null);
                                }
                                if (con.getContainerLength() != null) {
                                    c.setContainerLength(con.getContainerLength());
                                } else {
                                    c.setContainerLength(null);
                                }
                                if (con.getContainerWidth() != null) {
                                    c.setContainerWidth(con.getContainerWidth());
                                } else {
                                    c.setContainerWidth(null);
                                }
                                if (con.getSalesNumber() != null) {
                                    c.setSalesNumber(con.getSalesNumber());
                                } else {
                                    c.setSalesNumber(stringNull);
                                }
                                if (con.getDepot() != null) {
                                    c.setDepot(con.getDepot());
                                } else {
                                    c.setDepot(stringNull);
                                }
                                c.setUpdatedDate(gc.getTime());
                                se.info24.pojo.Addresses address = null;
                                if (con.getAddress().size() > 0) {
                                    if (c.getAddresses() != null) {
                                        address = c.getAddresses();
                                    } else {
                                        address = new Addresses();
                                        address.setAddressId(UUID.randomUUID().toString());
                                        AddressType addressType = new AddressType();
                                        addressType.setAddressTypeId(1);
                                        address.setAddressType(addressType);
                                    }
                                    if (con.getAddress().get(0).getAddressStreet() != null) {
                                        address.setAddressStreet(con.getAddress().get(0).getAddressStreet().trim());
                                    } else {
                                        address.setAddressStreet(stringNull);
                                    }
                                    if (con.getAddress().get(0).getAddressSuite() != null) {
                                        address.setAddressSuite(con.getAddress().get(0).getAddressSuite());
                                    } else {
                                        address.setAddressSuite(stringNull);
                                    }
                                    if (con.getAddress().get(0).getAddressZip() != null) {
                                        address.setAddressZip(con.getAddress().get(0).getAddressZip());
                                    } else {
                                        address.setAddressZip(stringNull);
                                    }
                                    if (con.getAddress().get(0).getAddressCity() != null) {
                                        address.setAddressCity(con.getAddress().get(0).getAddressCity());
                                    } else {
                                        address.setAddressCity(stringNull);
                                    }
                                    Country country = containerDAO.getCountryById(con.getAddress().get(0).getCountryID(), session);
                                    if (country != null) {
                                        address.setCountry(country);
                                    }
                                    address.setUpdatedDate(gc.getTime());
                                    c.setAddresses(address);
                                } else {
                                    c.setAddresses(null);
                                }

                                if (!containerDAO.updateContainerDetails(address, c, null, session, costCenters, isDelete)) {
                                    TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Container", con.getContainerName(), "Containers", "Failed");
                                    tingcoContainers.getMsgStatus().setResponseCode(-1);
                                    tingcoContainers.getMsgStatus().setResponseText("Error occured");
                                    return tingcoContainers;
                                } else {
                                    TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Container", con.getContainerName(), "Containers", "Success");
                                }
                            } else {
                                TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Container", con.getContainerName(), "Containers", "Failed");
                                tingcoContainers.getMsgStatus().setResponseCode(-1);
                                tingcoContainers.getMsgStatus().setResponseText("UserTimezone not found");
                                return tingcoContainers;
                            }
                        } else {
                            TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Container", con.getContainerName(), "Containers", "Failed");
                            tingcoContainers.getMsgStatus().setResponseCode(-1);
                            tingcoContainers.getMsgStatus().setResponseText("Data Not Found");
                            return tingcoContainers;
                        }
                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("xml does not contain Data");
                        return tingcoContainers;
                    }
                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers getContainers(String searchString, String countryID, String groupID) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (!searchString.equals("")) {
                    searchString = searchString.split("/")[2];
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(groupID, session);
                    Set<String> groupidset = groupDAO.getGroupIdsList(groupID, groupTrustsList);
                    List<Containers> containerList = new ArrayList<Containers>();
                    if (searchString != null) {
                        /**
                         * SPLIT LIST SIZE 30 MAR 2014
                         */
                        List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupidset), 2000);
                        for (List<String> list : listsplit) {
                            List<Containers> containerListTemp = containerDAO.searchContainersByNameOrNumbers(session, searchString, new HashSet<String>(list));
                            containerList.addAll(containerListTemp);
                            if (containerList.size() > 100) {
                                containerList = containerList.subList(0, 100);
                                break;
                            }
                        }
                    //containerList = containerDAO.searchContainersByNameOrNumbers(session, searchString, groupidset);
                    } else {
                        /**
                         * SPLIT LIST SIZE 30 MAR 2014
                         */
                        List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupidset), 2000);
                        for (List<String> list : listsplit) {
                            List<Containers> containerListTemp = containerDAO.getContainersByGroupIDList(new HashSet<String>(list), 100, session);
                            containerList.addAll(containerListTemp);
                            if (containerList.size() > 100) {
                                containerList = containerList.subList(0, 100);
                                break;
                            }
                        }
                    //containerList = containerDAO.getContainersByGroupIDList(groupidset, 100, session);
                    }
                    if (!containerList.isEmpty()) {
                        groupidset.clear();
                        for (Containers containers : containerList) {
                            groupidset.add(containers.getGroups().getGroupId());
                        }
                        List<GroupTranslations> grouptransList = new ArrayList<GroupTranslations>();
                        if (!groupidset.isEmpty()) {
                            /**
                             * SPLIT LIST SIZE 30 MAR 2014
                             */
                            List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupidset), 2000);
                            for (List<String> list : listsplit) {
                                List<GroupTranslations> grouptransListTemp = deviceDAO.getGroupTranslationsById(new HashSet<String>(list), Integer.valueOf(countryID), session);
                                grouptransList.addAll(grouptransListTemp);
                            }
                        //grouptransList = deviceDAO.getGroupTranslationsById(groupidset, Integer.valueOf(countryID), session);
                        }
                        return tingcoContainersXML.buildGetContainers(tingcoContainers, grouptransList, containerList);
                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoContainers;
                    }
//                    } else {
//                        tingcoContainers.getMsgStatus().setResponseCode(-1);
//                        tingcoContainers.getMsgStatus().setResponseText("Data Not Found");
//                        return tingcoContainers;
//                    }

                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers getContainerNotificationDetails(String containerID, String countryID) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Containers container = containerDAO.getContainersByContainerId(containerID, session);
                    if (container != null) {
                        List<String> eventTypeId = new ArrayList<String>();
                        if (container.getWarningLowEventTypeId() != null) {
                            eventTypeId.add(container.getWarningLowEventTypeId());
                        }
                        if (container.getWarningHighEventTypeId() != null) {
                            eventTypeId.add(container.getWarningHighEventTypeId());
                        }
                        if (container.getAlarmLowEventTypeId() != null) {
                            eventTypeId.add(container.getAlarmLowEventTypeId());
                        }
                        if (container.getAlarmHighEventTypeId() != null) {
                            eventTypeId.add(container.getAlarmHighEventTypeId());
                        }
                        if (container.getOrderMoreEventTypeId() != null) {
                            eventTypeId.add(container.getOrderMoreEventTypeId());
                        }
                        List<EventTypeTranslations> eventTypeTranslationses = new ArrayList();
                        if (!eventTypeId.isEmpty()) {
                            eventTypeTranslationses = deviceDAO.getEventTypeTranslations(eventTypeId, Integer.valueOf(countryID), session);
                        }
                        return tingcoContainersXML.buildGetContainerNotificationDetails(tingcoContainers, container, eventTypeTranslationses, session);

                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoContainers;
                    }
                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers deleteContainer(String containerID) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        Session sessionOper = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    sessionOper = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    Containers container = containerDAO.getContainersByContainerId(containerID, session);
                    if (container != null) {
                        List<ObjectContactMemberships> ocmList = deviceDAO.getObjectContactMemberships(containerID, session);
                        List<ContainerData> ContainerdataList = containerDAO.getContainerDataByContainerId(sessionOper, containerID);
                        List<ContainerDevices> containerdeviceList = containerDAO.getContainerDevicesByContainerId(containerID, session);
                        Users2 users2 = userDAO.getUserById(sessions2.getUserId(), session);
                        NetworkDAO networkDAO = new NetworkDAO();
                        List<ObjectCostCenters> costCenterses = networkDAO.getObjectCostCentersByGroupIdandObjectId(session, container.getContainerId(), users2.getGroupId());

                        if (!containerDAO.deleteContainer(session, sessionOper, container, ocmList, ContainerdataList, containerdeviceList, costCenterses)) {
                            TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Delete Container", container.getContainerName(), "Containers", "Failed");
                            tingcoContainers.getMsgStatus().setResponseCode(-1);
                            tingcoContainers.getMsgStatus().setResponseText("Error occured");
                        } else {
                            TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Delete Container", container.getContainerName(), "Containers", "Success");
                        }
                        return tingcoContainers;
                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoContainers;
                    }
                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            if (sessionOper != null) {
                sessionOper.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers deleteContact(String objectContactID, String containerID) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                } else if (ht.containsKey(TCMUtil.DEVICE)) {
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
                    ObjectContactMemberships ocm = containerDAO.getObjectContactMembershipsByIdandContactId(session, containerID, objectContactID);
                    if (ocm != null) {
                        if (!containerDAO.deleteContact(session, ocm)) {
                            tingcoContainers.getMsgStatus().setResponseCode(-1);
                            tingcoContainers.getMsgStatus().setResponseText("Error occured");
                        }
                        return tingcoContainers;
                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoContainers;
                    }
                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers updateObjectContactInfo(String objectContactID, String contactFirstName, String contactLastName, String contactOrganizationName, String contactMobilePhone, String contactEmail, String objectContactTypeID) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {

            if (!contactEmail.equals("")) {
                contactEmail = contactEmail.split("/")[2];
            } else {
                contactEmail = null;
            }
            if (!contactMobilePhone.equals("")) {
                contactMobilePhone = contactMobilePhone.split("/")[2];
            } else {
                contactMobilePhone = null;
            }
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                } else if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<String> objectContactIdSet = new ArrayList<String>();
                    objectContactIdSet.add(objectContactID);
                    List<ObjectContacts> ocList = deviceDAO.getObjectContacts(objectContactIdSet, session);
//                    List<ObjectContacts> ocList = containerDAO.getObjectContactsByGroupId(session, objectContactIdSet);
                    if (!ocList.isEmpty()) {
                        ObjectContactTypes oct = containerDAO.getObjectContactTypesById(session, objectContactTypeID);
                        if (oct != null) {
                            ObjectContacts oc = ocList.get(0);
                            oc.setContactFirstName(contactFirstName);
                            oc.setContactLastName(contactLastName);
                            oc.setContactOrganizationName(contactOrganizationName);
                            oc.setContactMobilePhone(contactMobilePhone);
                            oc.setContactEmail(contactEmail);
                            oc.setObjectContactTypes(oct);
                            if (!containerDAO.updateObjectContactInfo(session, oc)) {
                                tingcoContainers.getMsgStatus().setResponseCode(-1);
                                tingcoContainers.getMsgStatus().setResponseText("Error occured");
                            }
                            return tingcoContainers;
                        } else {
                            tingcoContainers.getMsgStatus().setResponseCode(-1);
                            tingcoContainers.getMsgStatus().setResponseText("objectContactTypeID Not Found");
                            return tingcoContainers;
                        }

                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoContainers;
                    }

                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers getObjectContactInfo(String objectContactID, String countryID) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (objectContactID.equals("")) {
                tingcoContainers.getMsgStatus().setResponseCode(-1);
                tingcoContainers.getMsgStatus().setResponseText("containerId should not be empty");
                return tingcoContainers;
            }
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;
                        }
                    }
                } else if (ht.containsKey(TCMUtil.DEVICE)) {
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
                    List<String> objectContactIdSet = new ArrayList<String>();
                    objectContactIdSet.add(objectContactID);
                    List<ObjectContacts> oc = deviceDAO.getObjectContacts(objectContactIdSet, session);
//                    List<ObjectContacts> oc = containerDAO.getObjectContactsByGroupId(session, objectContactIdSet);

                    if (!oc.isEmpty()) {
                        List<String> objectContactTypeId = new ArrayList<String>();
                        objectContactTypeId.add(oc.get(0).getObjectContactTypes().getObjectContactTypeId());
                        List<ObjectContactTypeTranslations> octt = containerDAO.getObjectContactTypeTranslationsByIds(session, countryID, objectContactTypeId);
                        return tingcoContainersXML.buildGetObjectContactInfo(tingcoContainers, oc.get(0), octt);
                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoContainers;
                    }

                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers addNewContactToContainer(String containerId, String contactFirstName, String contactLastName, String contactOrganizationName, String contactMobilePhone, String contactEmail, String objectContactTypeId, String groupId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (containerId.equals("")) {
                tingcoContainers.getMsgStatus().setResponseCode(-1);
                tingcoContainers.getMsgStatus().setResponseText("containerId should not be empty");
                return tingcoContainers;
            }
            if (contactFirstName.equals("")) {
                tingcoContainers.getMsgStatus().setResponseCode(-1);
                tingcoContainers.getMsgStatus().setResponseText("contactFirstName should not be empty");
                return tingcoContainers;
            }
            if (contactLastName.equals("")) {
                tingcoContainers.getMsgStatus().setResponseCode(-1);
                tingcoContainers.getMsgStatus().setResponseText("contactLastName should not be empty");
                return tingcoContainers;
            }
            if (contactOrganizationName.equals("")) {
                tingcoContainers.getMsgStatus().setResponseCode(-1);
                tingcoContainers.getMsgStatus().setResponseText("contactOrganizationName should not be empty");
                return tingcoContainers;
            }
            if (!contactEmail.equals("")) {
                contactEmail = contactEmail.split("/")[2];
            } else {
                contactEmail = null;
            }
            if (!contactMobilePhone.equals("")) {
                contactMobilePhone = contactMobilePhone.split("/")[2];
            } else {
                contactMobilePhone = null;
            }
            if (objectContactTypeId.equals("")) {
                tingcoContainers.getMsgStatus().setResponseCode(-1);
                tingcoContainers.getMsgStatus().setResponseText("objectContactTypeId should not be empty");
                return tingcoContainers;
            }
            if (groupId.equals("")) {
                tingcoContainers.getMsgStatus().setResponseCode(-1);
                tingcoContainers.getMsgStatus().setResponseText("groupId should not be empty");
                return tingcoContainers;
            }
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;
                        }
                    }
                } else if (ht.containsKey(TCMUtil.DEVICE)) {
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
                    ObjectContactTypes oct = containerDAO.getObjectContactTypesById(session, objectContactTypeId);
                    if (oct != null) {
                        String objectContactId = UUID.randomUUID().toString();
                        ObjectContacts oc = new ObjectContacts();
                        oc.setObjectContactId(objectContactId);
                        oc.setGroupId(groupId);
                        oc.setContactFirstName(contactFirstName);
                        oc.setContactLastName(contactLastName);
                        oc.setContactOrganizationName(contactOrganizationName);
                        oc.setContactMobilePhone(contactMobilePhone);
                        oc.setContactEmail(contactEmail);
                        oc.setObjectContactTypes(oct);
                        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                        oc.setCreatedDate(gc.getTime());
                        oc.setUpdatedDate(gc.getTime());

                        ObjectContactMemberships ocm = new ObjectContactMemberships();
                        ObjectContactMembershipsId idm = new ObjectContactMembershipsId();
                        idm.setObjectId(containerId);
                        idm.setObjectContactId(objectContactId);
                        ocm.setId(idm);
                        ocm.setCreatedDate(gc.getTime());
                        ocm.setUpdatedDate(gc.getTime());

                        if (!containerDAO.addNewContactToContainer(session, oc, ocm)) {
                            tingcoContainers.getMsgStatus().setResponseCode(-1);
                            tingcoContainers.getMsgStatus().setResponseText("Error occured");
                            return tingcoContainers;
                        }
                        return tingcoContainers;

                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("objectContactTypeId is not Valid");
                        return tingcoContainers;
                    }

                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers addExistingContactToContainer(String containerId, String objectcontactid) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (containerId.equals("")) {
                tingcoContainers.getMsgStatus().setResponseCode(-1);
                tingcoContainers.getMsgStatus().setResponseText("containerId should not be empty");
                return tingcoContainers;
            }
            if (objectcontactid.equals("")) {
                tingcoContainers.getMsgStatus().setResponseCode(-1);
                tingcoContainers.getMsgStatus().setResponseText("objectcontactid should not be empty");
                return tingcoContainers;
            }

            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ObjectContactMemberships ocm = containerDAO.getObjectContactMembershipsByIdandContactId(session, containerId, objectcontactid);

                    if (ocm == null) {
                        ObjectContactMemberships ocms = new ObjectContactMemberships();
                        ObjectContactMembershipsId id = new ObjectContactMembershipsId();
                        id.setObjectId(containerId);
                        id.setObjectContactId(objectcontactid);
                        ocms.setId(id);
                        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                        ocms.setCreatedDate(gc.getTime());
                        ocms.setUpdatedDate(gc.getTime());
                        if (!containerDAO.addExistingContactToContainer(session, ocms)) {
                            tingcoContainers.getMsgStatus().setResponseCode(-1);
                            tingcoContainers.getMsgStatus().setResponseText("Error occured");
                            return tingcoContainers;
                        }
                        return tingcoContainers;
                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("Data ALready Available");
                        return tingcoContainers;
                    }

                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers getObjectContactTypes(String countryId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (countryId.equals("")) {
                tingcoContainers.getMsgStatus().setResponseCode(-1);
                tingcoContainers.getMsgStatus().setResponseText("countryId should not be empty");
                return tingcoContainers;
            }

            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                } else if (ht.containsKey(TCMUtil.DEVICE)) {
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
                    List<ObjectContactTypeTranslations> octtList = containerDAO.getObjectContactTypeTranslationsByCountryId(session, countryId);
                    if (!octtList.isEmpty()) {
                        return tingcoContainersXML.buildGetObjectContactTypes(tingcoContainers, octtList);
                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("Data not found");
                        return tingcoContainers;
                    }

                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers getObjectContacts(String groupId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (groupId.equals("")) {
                tingcoContainers.getMsgStatus().setResponseCode(-1);
                tingcoContainers.getMsgStatus().setResponseText("groupId should not be empty");
                return tingcoContainers;
            }

            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                } else if (ht.containsKey(TCMUtil.DEVICE)) {
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
                    List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(groupId, session);
                    Set<String> groupidset = groupDAO.getGroupIdsList(groupId, groupTrustsList);
                    /**
                     * SPLIT LIST SIZE 30 MAR 2014
                     */
                    List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupidset), 2000);
                    List<ObjectContacts> ObjectContactList = new ArrayList<ObjectContacts>();
                    for (List<String> list : listsplit) {
                        List<ObjectContacts> ObjectContactListTemp = containerDAO.getObjectContactsByGroupId(session, new HashSet<String>(list));
                        ObjectContactList.addAll(ObjectContactListTemp);
                        if (ObjectContactList.size() > 100) {
                            ObjectContactList = ObjectContactList.subList(0, 100);
                            break;
                        }
                    }
                    //List<ObjectContacts> ObjectContactList = containerDAO.getObjectContactsByGroupId(session, groupidset);
                    if (ObjectContactList.isEmpty()) {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoContainers;
                    }
                    return tingcoContainersXML.buildGetObjectContacts(tingcoContainers, ObjectContactList);

                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers getContainerLevelMonitorDetails(String content) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        String containerName = null;
        String organizationName = null;
        String productVariantID = null;
        Integer currentFillLevelPercent = null;
        int countryID = 0;
        String objectGroupName = null;
        String addressCity = null;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.CONTAINERS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    Users2 users = userDAO.getUserById(sessions2.getUserId(), session);
                    List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(users.getGroupId(), session);
                    Set<String> groupidset = groupDAO.getGroupIdsList(users.getGroupId(), groupTrustsList);
                    se.info24.containersjaxb.TingcoContainers tingcoContainerJaxb = (se.info24.containersjaxb.TingcoContainers) JAXBManager.getInstance().unMarshall(content, se.info24.containersjaxb.TingcoContainers.class);

                    se.info24.containersjaxb.Containers containersJaxb = tingcoContainerJaxb.getContainers();
                    List<se.info24.containersjaxb.Container> containerJaxbList = containersJaxb.getContainer();
                    for (se.info24.containersjaxb.Container containerJaxb : containerJaxbList) {
                        containerName = containerJaxb.getContainerName() == null ? null : containerJaxb.getContainerName();
                        if (containerJaxb.getGroupID() != null) {
                            organizationName = containerJaxb.getGroupID().getGroupName() == null ? null : containerJaxb.getGroupID().getGroupName();
                        }
                        currentFillLevelPercent = containerJaxb.getCurrentFillLevelPercent() == null ? null : containerJaxb.getCurrentFillLevelPercent().intValue();
                        productVariantID = containerJaxb.getProductVariantID() == null ? null : containerJaxb.getProductVariantID();
                        if (containerJaxb.getObjectGroupTranslations().size() > 0) {
                            se.info24.containersjaxb.ObjectGroupTranslations objectGroupTranslationsJaxb = containerJaxb.getObjectGroupTranslations().get(0);
                            countryID = objectGroupTranslationsJaxb.getCountryID();
                            objectGroupName = objectGroupTranslationsJaxb.getObjectGroupName() == null ? null : objectGroupTranslationsJaxb.getObjectGroupName();
                        }
                        if (containerJaxb.getAddress() != null && containerJaxb.getAddress().size() > 0) {
                            se.info24.containersjaxb.Address addressJaxb = containerJaxb.getAddress().get(0);
                            addressCity = addressJaxb.getAddressCity();
                        }
                    }

                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();

//                       new ArrayList<Containers>();
                        Set<String> containerIDs = new HashSet<String>();
                        if (containerName == null && organizationName == null && currentFillLevelPercent == null && productVariantID == null && objectGroupName == null && addressCity == null) {
                            /**
                             * SPLIT LIST SIZE 30 MAR 2014
                             */
                            List<String> groupIdList = new ArrayList(groupidset);
                            List<List<String>> listSplit = TCMUtil.splitStringList(groupIdList, 2000);
                            List<Containers> listContainers = new ArrayList<Containers>();
                            for (List<String> list : listSplit) {
                                List<Containers> listContainersTemp = containerDAO.getContainersByGroupIDList(new HashSet<String>(list), 500, session);
                                listContainers.addAll(listContainersTemp);
                                if (listContainers.size() > 500) {
                                    listContainers = listContainers.subList(0, 500);
                                    break;
                                }
                            }
                            for (Containers c : listContainers) {
                                containerIDs.add(c.getContainerId());
                            }

                        } else {
                            //listContainers = containerDAO.searchContainers(containerName, organizationName, productVariantID, currentFillLevelPercent, countryID, objectGroupName, addressCity, groupidset, session);
                            List<Object> listContainersObject = containerDAO.searchContainers(containerName, organizationName, productVariantID, currentFillLevelPercent, countryID, objectGroupName, addressCity, users.getGroupId(), session);
                            containerIDs = containerDAO.getsearchContainersListObject(listContainersObject);
                        }
                        if (!containerIDs.isEmpty()) {

                            List<Containers> containerOrderedByNumber = containerDAO.getContainersOrderByNumber(new ArrayList<String>(containerIDs), session);
                            if (!containerOrderedByNumber.isEmpty()) {
                                tingcoContainersXML.buildContainerLevelMonitorDetails(containerOrderedByNumber, countryID, session, tingcoContainers, timeZoneGMToffset);
                            } else {
                                tingcoContainers.getMsgStatus().setResponseCode(-1);
                                tingcoContainers.getMsgStatus().setResponseText("Data not found");
                                return tingcoContainers;
                            }
                        } else {
                            tingcoContainers.getMsgStatus().setResponseCode(-1);
                            tingcoContainers.getMsgStatus().setResponseText("Data not found");
                            return tingcoContainers;
                        }
                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("User TimeZone is not found");
                        return tingcoContainers;
                    }
                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers addContainerDetails(String content) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    se.info24.containersjaxb.TingcoContainers containers = (se.info24.containersjaxb.TingcoContainers) JAXBManager.getInstance().unMarshall(content, se.info24.containersjaxb.TingcoContainers.class);
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    GregorianCalendar gc1 = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    if (containers.getContainers().getContainer().size() > 0) {
                        se.info24.containersjaxb.Container con = containers.getContainers().getContainer().get(0);
                        session = HibernateUtil.getSessionFactory().openSession();
                        Containers c = new Containers();
                        c.setCreatedDate(gc.getTime());
                        c.setUpdatedDate(gc.getTime());
                        String containerid = UUID.randomUUID().toString();
                        c.setContainerId(containerid);
                        c.setContainerName(con.getContainerName());
                        ContainerTypes containerType = containerDAO.getContainerTypesById(con.getContainerTypeID(), session);
                        if (containerType != null) {
                            c.setContainerTypes(containerType);
                        } else {
                            TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Add Container", con.getContainerName(), "Containers", "Failed");
                            tingcoContainers.getMsgStatus().setResponseCode(-1);
                            tingcoContainers.getMsgStatus().setResponseText("ContainerTypeId is not Valid");
                            return tingcoContainers;
                        }
                        ContainerShapes cs = containerDAO.getContainerShapesById(con.getContainerShapeID(), session);
                        if (cs != null) {
                            c.setContainerShapes(cs);
                        } else {
                            TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Add Container", con.getContainerName(), "Containers", "Failed");
                            tingcoContainers.getMsgStatus().setResponseCode(-1);
                            tingcoContainers.getMsgStatus().setResponseText("ContainerShapeID is not Valid");
                            return tingcoContainers;
                        }
                        c.setContainerNumber(con.getContainerNumber());
                        if (con.getAssetID() != null) {
                            c.setAssetId(con.getAssetID());
                        }
//                        c.setGroupId(con.getGroupID().getValue());
                        Groups groups = groupDAO.getGroupByGroupId(con.getGroupID().getValue(), session);
                        c.setGroups(groups);
                        List<String> aidList = new ArrayList<String>();
                        aidList.add(con.getAgreementID());
                        Agreements agreements = containerDAO.getAgreements(aidList, session);
                        if (agreements != null) {
                            c.setAgreements(agreements);
                        } else {
                            TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Add Container", con.getContainerName(), "Containers", "Failed");
                            tingcoContainers.getMsgStatus().setResponseCode(-1);
                            tingcoContainers.getMsgStatus().setResponseText("AgreementID is not Valid");
                            return tingcoContainers;
                        }
                        ObjectCostCenters costCenters = null;
                        if (con.getCostCenterID() != null) {
                            CostCenters cc = groupDAO.getCostCenterById(con.getCostCenterID(), session);
                            if (cc != null) {
                                c.setCostCenters(cc);
                                UserDAO userDAO = new UserDAO();
                                Users2 users2 = userDAO.getUserById(sessions2.getUserId(), session);
                                costCenters = new ObjectCostCenters();
                                ObjectCostCentersId id = new ObjectCostCentersId();
                                id.setObjectId(containerid);
                                id.setGroupId(groups.getGroupId());
                                costCenters.setId(id);
                                costCenters.setCostCenterId(con.getCostCenterID());
                                costCenters.setLastUpdatedByUserId(sessions2.getUserId());
                                costCenters.setUpdatedDate(gc.getTime());
                                costCenters.setCreatedDate(gc.getTime());
                            } else {
                                TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Add Container", con.getContainerName(), "Containers", "Failed");
                                tingcoContainers.getMsgStatus().setResponseCode(-1);
                                tingcoContainers.getMsgStatus().setResponseText("CostCenterID is not Valid");
                                return tingcoContainers;
                            }
                        }
                        if (con.getContainerDescription() != null) {
                            c.setContainerDescription(con.getContainerDescription());
                        }
                        if (con.getOpenScheduleID() != null) {
                            Schedules schedule = containerDAO.getSchedulesByID(con.getOpenScheduleID(), session);
                            if (schedule != null) {
                                c.setSchedules(schedule);
                            } else {
                                TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Add Container", con.getContainerName(), "Containers", "Failed");
                                tingcoContainers.getMsgStatus().setResponseCode(-1);
                                tingcoContainers.getMsgStatus().setResponseText("OpenScheduleID is not Valid");
                                return tingcoContainers;
                            }
                        }
                        c.setIsOpen(con.getIsOpen());
                        c.setObjectStateCodeId(con.getObjectStateCodeID());
                        c.setIsEnabled(con.getIsEnabled());
                        c.setIsMobileContainer(con.getIsMobileContainer());

                        UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                        if (userTimeZones2 != null) {
                            String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();

                            if (con.getActiveFromDate() != null) {

                                gc1.setTime(df.parse(con.getActiveFromDate()));
                                gc1.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc1.getTime()));
                                c.setActiveFromDate(gc1.getTime());
                            }
                            if (con.getActiveToDate() != null) {

                                gc1.setTime(df.parse(con.getActiveToDate()));
                                gc1.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc1.getTime()));
                                c.setActiveToDate(gc1.getTime());
                            }
                            if (con.getProductVariantID() != null) {
                                c.setProductVariantId(con.getProductVariantID());
                            }
                            if (con.getProductVariantUnit() != null) {
                                c.setProductVariantUnit(con.getProductVariantUnit());
                            }
                            if (con.getCurrentFillLevel() != null) {
                                c.setCurrentFillLevel(con.getCurrentFillLevel());
                            }

                            c.setContainerMinLevel(con.getContainerMinLevel());
                            c.setContainerMaxLevel(con.getContainerMaxLevel());
                            c.setContainerEmptyLevel(con.getContainerEmptyLevel());
                            c.setContainerFullLevel(con.getContainerFullLevel());
                            if (con.getContainerHeight() != null) {
                                c.setContainerHeight(con.getContainerHeight());
                            }
                            if (con.getContainerLength() != null) {
                                c.setContainerLength(con.getContainerLength());
                            }
                            if (con.getContainerWidth() != null) {
                                c.setContainerWidth(con.getContainerWidth());
                            }
                            if (con.getSalesNumber() != null) {
                                c.setSalesNumber(con.getSalesNumber());
                            }
                            if (con.getDepot() != null) {
                                c.setDepot(con.getDepot());
                            }

                            se.info24.pojo.Addresses address = null;
                            if (con.getAddress().size() > 0) {
                                address = new Addresses();
                                String addressid = UUID.randomUUID().toString();
                                address.setAddressId(addressid);
                                AddressType addressType = new AddressType();
                                addressType.setAddressTypeId(1);
                                address.setAddressType(addressType);
                                if (con.getAddress().get(0).getAddressStreet() != null) {
                                    address.setAddressStreet(con.getAddress().get(0).getAddressStreet());
                                }
                                if (con.getAddress().get(0).getAddressSuite() != null) {
                                    address.setAddressSuite(con.getAddress().get(0).getAddressSuite());
                                }
                                if (con.getAddress().get(0).getAddressZip() != null) {
                                    address.setAddressZip(con.getAddress().get(0).getAddressZip());
                                }
                                if (con.getAddress().get(0).getAddressCity() != null) {
                                    address.setAddressCity(con.getAddress().get(0).getAddressCity());
                                }
                                Country country = containerDAO.getCountryById(con.getAddress().get(0).getCountryID(), session);
                                if (country != null) {
                                    address.setCountry(country);
                                }
                                address.setCreatedDate(gc.getTime());
                                address.setUpdatedDate(gc.getTime());

                                c.setAddresses(address);
                            }
                            ObjectContactMemberships ocm = null;
                            if (con.getObjectContactMemberships().size() > 0) {
                                ocm = new ObjectContactMemberships();
                                ObjectContactMembershipsId id = new ObjectContactMembershipsId();
                                if (con.getObjectContactMemberships().get(0).getObjectContactID() != null) {
                                    id.setObjectContactId(con.getObjectContactMemberships().get(0).getObjectContactID());
                                }
                                id.setObjectId(containerid);
                                ocm.setId(id);
                                ocm.setUpdatedDate(gc.getTime());
                                ocm.setCreatedDate(gc.getTime());
                            }
                            if (!containerDAO.addContainerDetails(address, c, ocm, session, costCenters)) {
                                TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Add Container", con.getContainerName(), "Containers", "Failed");
                                tingcoContainers.getMsgStatus().setResponseCode(-1);
                                tingcoContainers.getMsgStatus().setResponseText("Error occured");
                                return tingcoContainers;
                            } else {
                                TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Add Container", con.getContainerName(), "Containers", "Success");
                            }
                        } else {
                            tingcoContainers.getMsgStatus().setResponseCode(-1);
                            tingcoContainers.getMsgStatus().setResponseText("UserTimezone not found");
                            return tingcoContainers;
                        }
                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("xml does not contain Data");
                        return tingcoContainers;
                    }
                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers getContainerDashBoardDetails(String containerId, Integer countryId, String fromDate, String toDate) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        Session ismOperationSession = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (containerId.equals("")) {
                tingcoContainers.getMsgStatus().setResponseCode(-1);
                tingcoContainers.getMsgStatus().setResponseText("ContainerId should not be empty");
                return tingcoContainers;
            }
            if (countryId == null) {
                tingcoContainers.getMsgStatus().setResponseCode(-1);
                tingcoContainers.getMsgStatus().setResponseText("CountryId should not be empty");
                return tingcoContainers;
            }
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.CONTAINERS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ismOperationSession = HibernateUtil.getISMOperationsSessionFactory().openSession();

                    Containers containers = containerDAO.getContainersByContainerId(containerId, session);
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                        GregorianCalendar gc_diff = new GregorianCalendar(TimeZone.getTimeZone("GMT"));

                        long toDateMilisec = 0;
                        long fromDateMilisec = 0;

                        if (toDate.equals("")) {
                            toDate = lFormat.format(gc.getTime());
                            toDateMilisec = gc.getTimeInMillis();
                        } else {
                            toDate = toDate.split("/")[2];
                            gc_diff.setTime(lFormat.parse(toDate));
                            gc_diff.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc_diff.getTime()));
                            toDate = lFormat.format(gc_diff.getTime());
                            toDateMilisec = gc_diff.getTimeInMillis();
                        }

                        if (fromDate.equals("")) {
                            gc_diff.add(GregorianCalendar.DATE, -7);
                            fromDate = lFormat.format(gc_diff.getTime());
                            fromDateMilisec = gc_diff.getTimeInMillis();
                        } else {
                            fromDate = fromDate.split("/")[2];
                            gc_diff.setTime(lFormat.parse(fromDate));
                            gc_diff.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc_diff.getTime()));
                            fromDate = lFormat.format(gc_diff.getTime());
                            fromDateMilisec = gc_diff.getTimeInMillis();
                        }
                        int totalfilling = 0;
                        int averagefilling = 0;
                        int numberoffillings = 0;
                        int totalconsumption = 0;
                        int averageconsumption = 0;
//                        int numberofconsumption = 0;
                        if (containers != null) {
                            List<ContainerFillings> containerFillingsList = containerDAO.getContainerFillingsBydataItemTime(ismOperationSession, containerId, fromDate, toDate);
                            for (ContainerFillings containerFillings : containerFillingsList) {
                                totalfilling = totalfilling + containerFillings.getFillValue().intValue();
                                numberoffillings = numberoffillings + 1;
                            }
                            if (totalfilling > 0) {
                                averagefilling = totalfilling / numberoffillings;
                            }

                            List<ContainerUsageData> containerDataList = containerDAO.getContainerUsageDataBydataItemTime(ismOperationSession, containerId, fromDate, toDate);
                            for (ContainerUsageData containerData : containerDataList) {
                                totalconsumption = totalconsumption + containerData.getUsageValue().intValue();
                            }
                            if (totalconsumption > 0) {
                                long diff = toDateMilisec - fromDateMilisec;
                                int days = (int) (diff / (24 * 60 * 60 * 1000)) + 1;
                                averageconsumption = totalconsumption / days;
                            }
                            List<ContainerDevices> containerDeviceses = containerDAO.getContainerDevicesByContainerId(containerId, session);
                            List<String> deviceid = new ArrayList<String>();
                            for (ContainerDevices containerDevices : containerDeviceses) {
                                deviceid.add(containerDevices.getId().getDeviceId());
                            }
                            List<ServiceDeviceSubscriptions> serviceDeviceSubscriptionses = new ArrayList<ServiceDeviceSubscriptions>();
                            if (!deviceid.isEmpty()) {
                                serviceDeviceSubscriptionses = containerDAO.getServiceDeviceSubscriptionsByDeviceIds(session, deviceid);
                            }

                            List<String> serviceId = new ArrayList<String>();
                            for (ServiceDeviceSubscriptions serviceDeviceSubscriptions : serviceDeviceSubscriptionses) {
                                serviceId.add(serviceDeviceSubscriptions.getServices().getServiceId());
                            }
                            List<Services> service = new ArrayList<Services>();
                            if (!serviceId.isEmpty()) {
                                service = deviceDAO.getServices(session, serviceId);
                            }

                            tingcoContainers = tingcoContainersXML.buildContainers(tingcoContainers, containers, countryId, session, timeZoneGMToffset, totalfilling, averagefilling, numberoffillings, totalconsumption, averageconsumption, service);
                        } else {
                            tingcoContainers.getMsgStatus().setResponseCode(-1);
                            tingcoContainers.getMsgStatus().setResponseText("No Data Found");
                            return tingcoContainers;
                        }
                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("User TimeZone is not found");
                        return tingcoContainers;
                    }
                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers getContainerDashBoardGraphDetails(String containerId, String fromDate, String toDate) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        Session ismOperationsSession = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (containerId.equals("")) {
                tingcoContainers.getMsgStatus().setResponseCode(-1);
                tingcoContainers.getMsgStatus().setResponseText("ContainerId should not be empty");
                return tingcoContainers;
            }
            if (!fromDate.equals("")) {
                fromDate = fromDate.split("/")[2];
            } else {
                fromDate = null;
            }
            if (!toDate.equals("")) {
                toDate = toDate.split("/")[2];
            } else {
                toDate = null;
            }
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
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
                    List<ContainerData> containerDataList = containerDAO.getcontainerData(containerId, fromDate, toDate, ismOperationsSession);
                    Containers containers = containerDAO.getContainersByContainerId(containerId, session);
                    if (containers != null) {
                        UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                        if (userTimeZones2 != null) {
                            String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                            if (containerDataList != null) {
                                tingcoContainers = tingcoContainersXML.buildContainerData(tingcoContainers, containers, containerDataList, session, timeZoneGMToffset);
                            } else {
                                tingcoContainers.getMsgStatus().setResponseCode(-1);
                                tingcoContainers.getMsgStatus().setResponseText("No ContainerData Found");
                                return tingcoContainers;
                            }
                        } else {
                            tingcoContainers.getMsgStatus().setResponseCode(-1);
                            tingcoContainers.getMsgStatus().setResponseText("User TimeZone is not found");
                            return tingcoContainers;
                        }
                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("Containers not found");
                        return tingcoContainers;
                    }
                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            if (ismOperationsSession != null) {
                ismOperationsSession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers getContainerShapes() {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ContainerShapes> containerShapesList = containerDAO.getContainerShapes(session);
                    if (!containerShapesList.isEmpty()) {
                        tingcoContainers = tingcoContainersXML.buildContainerShapes(tingcoContainers, containerShapesList);
                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("No Data Found");
                        return tingcoContainers;
                    }
                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
    }

    private TingcoContainers getContainerTypes() {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoContainers tingcoContainers = null;
        boolean hasPermission = false;
        try {
            tingcoContainers = tingcoContainersXML.buildTingcoContainersTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ContainerTypes> containerTypesList = containerDAO.getContainerTypes(session);
                    if (!containerTypesList.isEmpty()) {
                        tingcoContainers = tingcoContainersXML.buildContainerTypes(tingcoContainers, containerTypesList);
                    } else {
                        tingcoContainers.getMsgStatus().setResponseCode(-1);
                        tingcoContainers.getMsgStatus().setResponseText("No Data Found");
                        return tingcoContainers;
                    }
                } else {
                    tingcoContainers.getMsgStatus().setResponseCode(-10);
                    tingcoContainers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContainers;
                }
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-3);
                tingcoContainers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContainers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContainers;
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
}

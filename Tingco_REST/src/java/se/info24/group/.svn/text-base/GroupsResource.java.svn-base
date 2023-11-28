/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.group;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.datatype.DatatypeConfigurationException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.application.ApplicationDAO;
import se.info24.device.DeviceDAO;
import se.info24.devicejaxbPost.Device;
import se.info24.groupsjaxb.ContactTypes;
import se.info24.groupsjaxb.Group;
import se.info24.groupsjaxb.TingcoGroups;
import se.info24.ismOperationsPojo.AccessLog;
import se.info24.jaxbUtil.TingcoGroupXML;
import se.info24.pojo.Addresses;
import se.info24.pojo.ApplicationPackageTranslations;
import se.info24.pojo.ApplicationPackages;
import se.info24.pojo.ApplicationSettings;
import se.info24.pojo.ApplicationSolutionTranslations;
import se.info24.pojo.ContactGroups;
import se.info24.pojo.ContactGroupsInContacts;
import se.info24.pojo.ContactGroupsInContactsId;
import se.info24.pojo.ContactTypeDefaultFields;
import se.info24.pojo.ContactTypeDetails;
import se.info24.pojo.ContactTypeTranslations;
import se.info24.pojo.ContactTypesInContacts;
import se.info24.pojo.Contacts;
import se.info24.pojo.CostCenters;
import se.info24.pojo.Country;
import se.info24.pojo.EventTypeTranslations;
import se.info24.pojo.Faq;
import se.info24.pojo.GroupAlias;
import se.info24.pojo.GroupApplicationPackages;
import se.info24.pojo.GroupDefaultAgreement;
import se.info24.pojo.GroupDefaultUserAlias;
import se.info24.pojo.GroupLimitPackages;
import se.info24.pojo.GroupLimits;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupTrusts;
import se.info24.pojo.GroupTypeTranslations;
import se.info24.pojo.GroupVisibleApplicationSolutions;
import se.info24.pojo.Groups;
import se.info24.pojo.ObjectAddresses;
import se.info24.pojo.ObjectAddressesId;
import se.info24.pojo.ObjectGroupTranslations;
import se.info24.pojo.ObjectGroupTranslationsId;
import se.info24.pojo.ObjectGroups;
import se.info24.pojo.Ogmcontainers;
import se.info24.pojo.Ogmdevice;
import se.info24.pojo.OgmuserAlias;
import se.info24.pojo.ServiceClientLogins;
import se.info24.pojo.UserAlias;
import se.info24.pojo.UserLog;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.UserTimeZones2;
import se.info24.pojo.Users2;
import se.info24.products.ProductsDAO;
import se.info24.restUtil.RestUtilDAO;
import se.info24.tcp.TingcoCustomerDAO;
import se.info24.user.CountryDAO;
import se.info24.user.UserDAO;
import se.info24.user.User_LoginsResource;
import se.info24.util.HibernateUtil;
import se.info24.util.JAXBManager;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Ravikant
 */
@Path("/groups")
public class GroupsResource {

    @Context
    private UriInfo context;
    @Context
    HttpServletRequest request;
    @Context
    HttpServletResponse response;
    GroupDAO groupDAO = new GroupDAO();
    UserDAO userDAO = new UserDAO();
    DeviceDAO deviceDao = new DeviceDAO();
    ProductsDAO productsDAO = new ProductsDAO();
    TingcoCustomerDAO customerDAO = new TingcoCustomerDAO();
    TingcoGroupXML tingcoGroupXml = new TingcoGroupXML();
    TingcoGroups tingcoGroups = new TingcoGroups();
    SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of GroupsResource */
    public GroupsResource() {
    }

    /**
     * Group_Add
     * @param request
     * @param groupName
     * @param groupTypeID
     * @param grpParID
     * @param grp_desc
     * @return
     */
    @GET
    @Path("/add/rest/{rest}/groupname/{groupname}{grouptypeid:(/grouptypeid/[^/]+?)?}{groupparentid:(/groupparentid/[^/]+?)?}{groupdescription:(/groupdescription/[^/]+?)?}")
    @Produces("application/xml")
    @RESTDoc(methodName = "Group_Add", desc = "Add's a new system Group", req_Params = {"GroupName;String"}, opt_Params = {"GroupParentID;UUID", "GroupDescription;String", "GroupTypeID;UUID"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoGroups getXml_Add(@Context HttpServletRequest request, @PathParam("groupname") String groupName, @PathParam("grouptypeid") String groupTypeID, @PathParam("groupparentid") String grpParID, @PathParam("groupdescription") String grp_desc) {
        return groupAdded(groupName, groupTypeID, grpParID, grp_desc, request);
    }

    /**
     * Group_Add
     * @param request
     * @param groupName
     * @param groupTypeID
     * @param grpParID
     * @param grp_desc
     * @return
     */
    @GET
    @Path("/add/json/{json}/groupname/{groupname}{grouptypeid:(/grouptypeid/[^/]+?)?}{groupparentid:(/groupparentid/[^/]+?)?}{groupdescription:(/groupdescription/[^/]+?)?}")
    @Produces("application/json")
    public TingcoGroups getJson_Add(@Context HttpServletRequest request, @PathParam("groupname") String groupName, @PathParam("grouptypeid") String groupTypeID, @PathParam("groupparentid") String grpParID, @PathParam("groupdescription") String grp_desc) {
        return groupAdded(groupName, groupTypeID, grpParID, grp_desc, request);
    }

    /**
     * Group_Add
     * @param request
     * @param groupName
     * @param groupTypeID
     * @param countryID
     * @param grpParID
     * @param grp_desc
     * @param roleId
     * @return
     */
    @GET
    @Path("/add/rest/{rest}/groupname/{groupname}/countryid/{countryid}{grouptypeid:(/grouptypeid/[^/]+?)?}{groupparentid:(/groupparentid/[^/]+?)?}{groupdescription:(/groupdescription/[^/]+?)?}{roleid:(/roleid/[^/]+?)?}")
    @Produces("application/xml")
    public TingcoGroups getXml_Add(@Context HttpServletRequest request, @PathParam("groupname") String groupName, @PathParam("grouptypeid") String groupTypeID,
            @PathParam("countryid") int countryID, @PathParam("groupparentid") String grpParID, @PathParam("groupdescription") String grp_desc, @PathParam("roleid") String roleId) {

        if (!grpParID.equals("")) {
            grpParID = grpParID.split("/")[2];
        }

        if (!groupTypeID.equals("")) {
            groupTypeID = groupTypeID.split("/")[2];
        }
        return newGroupAdded(groupName, grp_desc, groupTypeID, countryID, grpParID, request, roleId);
    }

    /**
     * Group_Add
     * @param request
     * @param groupName
     * @param groupTypeID
     * @param countryID
     * @param grpParID
     * @param grp_desc
     * @param roleId
     * @return
     */
    @GET
    @Path("/add/json/{json}/groupname/{groupname}/countryid/{countryid}{grouptypeid:(/grouptypeid/[^/]+?)?}{groupparentid:(/groupparentid/[^/]+?)?}{groupdescription:(/groupdescription/[^/]+?)?}{roleid:(/roleid/[^/]+?)?}")
    @Produces("application/json")
    public TingcoGroups getJson_Add(@Context HttpServletRequest request, @PathParam("groupname") String groupName, @PathParam("grouptypeid") String groupTypeID,
            @PathParam("countryid") int countryID, @PathParam("groupparentid") String grpParID, @PathParam("groupdescription") String grp_desc, @PathParam("roleid") String roleId) {

        if (!grpParID.equals("")) {
            grpParID = grpParID.split("/")[2];
        }

        if (!groupTypeID.equals("")) {
            groupTypeID = groupTypeID.split("/")[2];
        }
        return newGroupAdded(groupName, grp_desc, groupTypeID, countryID, grpParID, request, roleId);
    }

    /**
     * Group_Add
     * @param request
     * @param groupName
     * @param groupTypeID
     * @param countryID
     * @param domainID
     * @param appID
     * @param userID
     * @param grpParID
     * @param grp_desc
     * @param roleId
     * @return
     */
    @GET
    @Path("/add/rest/{rest}/groupname/{groupname}/countryid/{countryid}/domainid/{domainid}/applicationid/{appid}/userid/{userid}{grouptypeid:(/grouptypeid/[^/]+?)?}{groupparentid:(/groupparentid/[^/]+?)?}{groupdescription:(/groupdescription/[^/]+?)?}{roleid:(/roleid/[^/]+?)?}")
    @Produces("application/xml")
    public TingcoGroups getXml_addEVGroup(@Context HttpServletRequest request, @PathParam("groupname") String groupName, @PathParam("grouptypeid") String groupTypeID,
            @PathParam("countryid") int countryID, @PathParam("domainid") String domainID, @PathParam("appid") String appID, @PathParam("userid") String userID, @PathParam("groupparentid") String grpParID, @PathParam("groupdescription") String grp_desc, @PathParam("roleid") String roleId) {

        if (!grpParID.equals("")) {
            grpParID = grpParID.split("/")[2];
        }

        if (!groupTypeID.equals("")) {
            groupTypeID = groupTypeID.split("/")[2];
        }

        return addGroup_EV(groupName, grp_desc, groupTypeID, countryID, domainID, grpParID, appID, userID, request, roleId);
    }

    /**
     * Group_Add
     * @param request
     * @param groupName
     * @param groupTypeID
     * @param countryID
     * @param domainID
     * @param appID
     * @param userID
     * @param grpParID
     * @param grp_desc
     * @param roleId
     * @return
     */
    @GET
    @Path("/add/json/{json}/groupname/{groupname}/countryid/{countryid}/domainid/{domainid}/applicationid/{appid}/userid/{userid}{grouptypeid:(/grouptypeid/[^/]+?)?}{groupparentid:(/groupparentid/[^/]+?)?}{groupdescription:(/groupdescription/[^/]+?)?}{roleid:(/roleid/[^/]+?)?}")
    @Produces("application/json")
    public TingcoGroups getJson_addEVGroup(@Context HttpServletRequest request, @PathParam("groupname") String groupName, @PathParam("grouptypeid") String groupTypeID,
            @PathParam("countryid") int countryID, @PathParam("domainid") String domainID, @PathParam("appid") String appID, @PathParam("userid") String userID, @PathParam("groupparentid") String grpParID, @PathParam("groupdescription") String grp_desc, @PathParam("roleid") String roleId) {

        if (!grpParID.equals("")) {
            grpParID = grpParID.split("/")[2];
        }

        if (!groupTypeID.equals("")) {
            groupTypeID = groupTypeID.split("/")[2];
        }

        return addGroup_EV(groupName, grp_desc, groupTypeID, countryID, domainID, grpParID, appID, userID, request, roleId);
    }

    /**
     * Group_Delete
     * @param request
     * @param groupID
     * @return
     */
    @GET
    @Path("/delete/rest/{rest}/groupid/{groupid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "Group_Delete", desc = "Used to delete the specified system Group", req_Params = {"GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoGroups getXml_Delete(@Context HttpServletRequest request, @PathParam("groupid") String groupID) {
        return deletedGroup(groupID, request);
    }

    /**
     * Group_Delete
     * @param request
     * @param groupID
     * @return
     */
    @GET
    @Path("/delete/json/{json}/groupid/{groupid}")
    @Produces("application/json")
    public TingcoGroups getJson_Delete(@Context HttpServletRequest request, @PathParam("groupid") String groupID) {
        return deletedGroup(groupID, request);
    }

    /**
     * Group_GetInfo
     * @param request
     * @param groupID
     * @param groupTypeID
     * @return
     */
    @GET
    @Path("/get/rest/{rest}/groupid/{groupid}/grouptypeid/{grouptypeid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "Group_GetInfo", desc = "Return's the general information about the specified Group", req_Params = {"GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoGroups getXml(@Context HttpServletRequest request, @PathParam("groupid") String groupID, @PathParam("grouptypeid") String groupTypeID) {
        return groupsInfo(groupID, groupTypeID, request);
    }

    /**
     * Group_GetInfo
     * @param request
     * @param groupID
     * @param groupTypeID
     * @return
     */
    @GET
    @Path("/get/json/{json}/groupid/{groupid}/grouptypeid/{grouptypeid}")
    @Produces("application/json")
    public TingcoGroups getJson(@Context HttpServletRequest request, @PathParam("groupid") String groupID, @PathParam("grouptypeid") String groupTypeID) {
        return groupsInfo(groupID, groupTypeID, request);
    }

    /**
     * GetParentChildGroups
     * @param groupID
     * @param searchString
     * @param countryID
     * @return
     */
    @GET//{searchstring:(/searchstring/[^/]+?)?}
    @Path("/getparentchildgroups/rest/{rest}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}/countryid/{countryid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetParentChildGroups", desc = "Returns the Parent Child Organisations", req_Params = {"GroupID;UUID", "SearchString;String", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoGroups getXml_getParentChildGroups(@PathParam("groupid") String groupID, @PathParam("searchstring") String searchString, @PathParam("countryid") int countryId) {
        return getDownstreamGroupsForLoggedinUserGroup(groupID, searchString, countryId);
    }

    /**
     * GetParentChildGroups
     * @param groupID
     * @param searchString
     * @param countryID
     * @return
     */
    @GET
    @Path("/getparentchildgroups/json/{json}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}/countryid/{countryid}")
    @Produces("application/json")
    public TingcoGroups getJson_getParentChildGroups(@PathParam("groupid") String groupID, @PathParam("searchstring") String searchString, @PathParam("countryid") int countryId) {
        return getDownstreamGroupsForLoggedinUserGroup(groupID, searchString, countryId);
    }

    /**
     * GetGroupsbyNumber
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getgroupsbynumber/rest/{rest}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetGroupsbyNumber", desc = "Used to Get GroupsbyNumber", req_Params = {"GroupId;string", "CountryId;int"}, opt_Params = {"SearchString;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_getGroupsbyNumber(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("searchstring") String searchString) {
        return getGroupsByNumber(groupId, countryId, searchString);
    }

    /**
     * GetGroupsbyNumber
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getgroupsbynumber/json/{json}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetGroupsbyNumber", desc = "Used to Get GroupsbyNumber", req_Params = {"GroupId;string", "CountryId;string"}, opt_Params = {"SearchString;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getJson_getGroupsbyNumber(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("searchstring") String searchString) {
        return getGroupsByNumber(groupId, countryId, searchString);
    }

//    @GET
//    @Path("/getgroupsbynumber/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?)")
//    @Produces("application/xml")
//    public TingcoGroups getXml_getGroupsbyNumber(@PathParam("groupid") String groupID, @PathParam("countryid") String countryID, @PathParam("searchstring") String searchString) {
//        return getGroupsByNumber(groupID, countryID, searchString);
//    }
//
//    @GET
//    @Path("/getgroupsbynumber/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?)")
//    @Produces("application/json")
//    public TingcoGroups getJson_getGroupsbyNumber(@PathParam("groupid") String groupID, @PathParam("countryid") String countryID, @PathParam("searchstring") String searchString) {
//        return getGroupsByNumber(groupID, countryID, searchString);
//    }
    /**
     * GetGroupDetails
     * @param countryid
     * @param groupid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getgroupdetails/rest/{rest}/groupid/{groupid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetGroupDetails", desc = "Used to Get GroupDetails", req_Params = {"GroupId;string", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_GetGroupDetails(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getGroupDetails(groupId, countryId);
    }

    /**
     * GetGroupDetails
     * @param countryid
     * @param groupid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getgroupdetails/json/{json}/groupid/{groupid}/countryid/{countryid}")
    public TingcoGroups getJson_GetGroupDetails(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getGroupDetails(groupId, countryId);
    }

    /**
     * GetTrustedGroupsList
     * @param countryid
     * @param groupid
     * @param selectedgroupid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/gettrustedgroupslist/rest/{rest}/selectedgroupid/{selectedgroupid}/groupid/{groupid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetTrustedGroupsList", desc = "Used to Get TrustedOrganizationsList", req_Params = {"SelectedGroupId;string", "GroupId;string", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_GetTrustedGroupsList(@PathParam("selectedgroupid") String selectedGroupId, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getTrustedGroupsList(selectedGroupId, groupId, countryId);
    }

    /**
     * GetTrustedGroupsList
     * @param countryid
     * @param groupid
     * @param selectedgroupid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/gettrustedgroupslist/json/{json}/selectedgroupid/{selectedgroupid}/groupid/{groupid}/countryid/{countryid}")
    public TingcoGroups getJson_GetTrustedGroupsList(@PathParam("selectedgroupid") String selectedGroupId, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getTrustedGroupsList(selectedGroupId, groupId, countryId);
    }

//    /**
//     * Group_GetInfo
//     * @param groupID
//     * @param countryId
//     * @param searchString
//     * @return
//     */
//    @GET
//    @Produces("application/xml")
//    @Path("/getobjectgroups/rest/{rest}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
//    public TingcoGroups getXml_getObjectGroups(@PathParam("groupid") String groupID, @PathParam("countryid") int countryID, @PathParam("searchstring") String searchString) {
//        return getObjectGroups(groupID, countryID, searchString);
//    }
//
    /**
     * Group_GetInfo
     * @param groupID
     * @param countryId
     * @param searchString
     * @return
     */
    @GET
    @Path("/getobjectgroupsforpopup/rest/{rest}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    @Produces("application/xml")
    @RESTDoc(methodName = "getobjectgroups", desc = "getobjectgroups", req_Params = {"groupid;UUID", "countryid;int"}, opt_Params = {"searchstring;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXmlUserListForUserLog(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("searchstring") String searchString) {
        return getObjectGroupsForPopup(groupId, countryId, searchString);
    }

    /**
     * Group_GetInfo
     * @param groupID
     * @param countryId
     * @param searchString
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getobjectgroupsforpopup/json/{json}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoGroups getJson_getObjectGroups(@PathParam("groupid") String groupID, @PathParam("countryid") int countryID, @PathParam("searchstring") String searchString) {
        return getObjectGroupsForPopup(groupID, countryID, searchString);
    }

    /**
     * Group_Update
     * @param request
     * @param groupID
     * @param groupName
     * @param grpParID
     * @param group_desc
     * @return
     */
    @GET
    @Path("/update/rest/{rest}/groupid/{groupid}{groupname:(/groupname/[^/]+?)?}{groupparentid:(/groupparentid/[^/]?)?}{groupdescription:(/groupdescription/[^/]?)?}")
    @Produces("application/xml")
    @RESTDoc(methodName = "Group_Update", desc = "Update  the general information about a system Group", req_Params = {"GroupID;UUID"}, opt_Params = {"GroupName;String", "GroupParentID;UUID", "GroupDescription;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoGroups getXml_Update(@Context HttpServletRequest request, @PathParam("groupid") String groupID, @PathParam("groupname") String groupName, @PathParam("groupparentid") String grpParID, @PathParam("groupdescription") String group_desc) {
        groupName = groupName.substring(groupName.lastIndexOf('/') + 1);
        return updatedGroup(groupID, grpParID, groupName, group_desc, request);
    }

    /**
     * Group_Update
     * @param request
     * @param groupID
     * @param groupName
     * @param grpParID
     * @param group_desc
     * @return
     */
    @GET
    @Path("/update/json/{json}/groupid/{groupid}{groupname:(/groupname/[^/]+?)?}{groupparentid:(/groupparentid/[^/]?)?}{groupdescription:(/groupdescription/[^/]?)?}")
    @Produces("application/json")
    public TingcoGroups getJson_Update(@Context HttpServletRequest request, @PathParam("groupid") String groupID, @PathParam("groupname") String groupName, @PathParam("groupparentid") String grpParID, @PathParam("groupdescription") String group_desc) {
        return updatedGroup(groupID, grpParID, groupName, group_desc, request);
    }

    /**
     * IsGroupExists
     * @param groupId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/isgroupexists/rest/{rest}/groupid/{groupid}/countryid/{countryid}")
    @RESTDoc(methodName = "IsGroupExists", desc = "To Check the Given GroupID is Exists or Not in ISM Groups", req_Params = {"GroupID;UUID", "CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoGroups getXml_IsExists(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return isGroupExists(groupId, countryId);
    }

    /**
     * IsGroupExists
     * @param groupId
     * @param countryId
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/isgroupexists/rest/{rest}/groupid/{groupid}/countryid/{countryid}")
    public TingcoGroups postXml_IsExists(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return isGroupExists(groupId, countryId);
    }

    /**
     * IsGroupExists
     * @param groupId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/isgroupexists/json/{json}/groupid/{groupid}/countryid/{countryid}")
    public TingcoGroups getJson_IsExists(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return isGroupExists(groupId, countryId);
    }

    /**
     * IsGroupExists
     * @param groupId
     * @param countryId
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/isgroupexists/json/{json}/groupid/{groupid}/countryid/{countryid}")
    public TingcoGroups postJson_IsExists(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return isGroupExists(groupId, countryId);
    }

    /**
     * GetAllGroups
     * @param request
     * @param appID
     * @param countryID
     * @return
     */
    @GET
    @Path("/rest/{rest}/applicationid/{applicationid}/countryid/{countryid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetAllGroups", desc = "Returns all Groups for a Particular User", req_Params = {"ApplicationID;UUID", "CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_All(@PathParam("applicationid") String appID, @PathParam("countryid") int countryID) {
        return allGroups(appID, countryID);
    }

    /**
     * GetAllGroups
     * @param request
     * @param appID
     * @param countryID
     * @return
     */
    @GET
    @Path("/json/{json}/applicationid/{applicationid}/countryid/{countryid}")
    @Produces("application/json")
    public TingcoGroups getJson_All(@PathParam("applicationid") String appID, @PathParam("countryid") int countryID) {
        return allGroups(appID, countryID);
    }

    /**
     * GetAllGroupTypes
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getallgrouptypes/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetAllGroupTypes", desc = "To Get All GroupTypes based on CountryID", req_Params = {"CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoGroups getXml_AllTypes(@PathParam("countryid") int countryId) {
        return getGroupTypes(countryId);
    }

    /**
     * GetAllGroupTypes
     * @param countryId
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/getallgrouptypes/rest/{rest}/countryid/{countryid}")
    public TingcoGroups postXml_AllTypes(@PathParam("countryid") int countryId) {
        return getGroupTypes(countryId);
    }

    /**
     * GetAllGroupTypes
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getallgrouptypes/json/{json}/countryid/{countryid}")
    public TingcoGroups getJson_AllTypes(@PathParam("countryid") int countryId) {
        return getGroupTypes(countryId);
    }

    /**
     * GetAllGroupTypes
     * @param countryId
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/getallgrouptypes/json/{json}/countryid/{countryid}")
    public TingcoGroups postJson_AllTypes(@PathParam("countryid") int countryId) {
        return getGroupTypes(countryId);
    }

    /**
     * DeleteGroupDetails
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deletegroupdetails/rest/{rest}/groupid/{groupid}")
    @RESTDoc(methodName = "DeleteGroupDetails", desc = "Used to Delete GroupDetails", req_Params = {"GroupId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoGroups getXml_deleteGroupDetails(@PathParam("groupid") String groupId) {
        return deleteGroupDetails(groupId);
    }

    /**
     * DeleteGroupDetails
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deletegroupdetails/json/{json}/groupid/{groupid}")
    public TingcoGroups getJson_deleteGroupDetails(@PathParam("groupid") String groupId) {
        return deleteGroupDetails(groupId);
    }

    /**
     * getgroupslist
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getgroupslist/rest/{rest}/groupid/{groupid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetGroupsList", desc = "Used to get GroupsList", req_Params = {"GroupID;string, CountryID; int"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoGroups getXml_GetGroupsList(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getGroupsList(groupId, countryId);
    }

    /**
     * getgroupslist
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getgroupslist/json/{json}/groupid/{groupid}/countryid/{countryid}")
    public TingcoGroups getJson_GetGroupsList(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getGroupsList(groupId, countryId);
    }

    /**
     * Add Organization
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addorganization/rest/{rest}")
    @RESTDoc(methodName = "Add Organization", desc = "Used to add new Organization", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoGroups postXml_Add(String content) {
        return addOrganization(content);
    }

    /**
     * Add Organization
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addorganization/json/{json}")
    public TingcoGroups postJson_Add(String content) {
        return addOrganization(content);
    }

    /**
     * Add Organization
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/updateorganization/rest/{rest}")
    @RESTDoc(methodName = "Add Organization", desc = "Used to add new Organization", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoGroups postXml_update(String content) {
        return updateOrganization(content);
    }

    /**
     * Add Organization
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/updateorganization/json/{json}")
    public TingcoGroups postJson_update(String content) {
        return updateOrganization(content);
    }

    /**
     * GetCostCenterById
     * @param costCenterId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getcostcenterbyid/rest/{rest}/costcenterid/{costcenterid}")
    @RESTDoc(methodName = "GetCostCenterById", desc = "Used to get CostCenter By Id", req_Params = {"CostCenterId;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_GetCostCenterById(@PathParam("costcenterid") String costCenterId) {
        return getCostCentreByID(costCenterId);
    }

    /**
     * GetCostCenterById
     * @param costCenterId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getcostcenterbyid/json/{json}/costcenterid/{costcenterid}")
    public TingcoGroups getJson_GetCostCenterById(@PathParam("costcenterid") String costCenterId) {
        return getCostCentreByID(costCenterId);
    }

    /**
     *
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getcostcenters/rest/{rest}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetCostCenterById", desc = "Used to get CostCenter By Id", req_Params = {"CostCenterId;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_GetCostCenters(@PathParam("groupid") String groupId, @PathParam("searchstring") String searchString) {
        return getCostCenters(groupId, searchString);
    }

    /**
     *
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getcostcenters/json/{json}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetCostCenterById", desc = "Used to get CostCenter By Id", req_Params = {"CostCenterId;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getjson_GetCostCenters(@PathParam("groupid") String groupId, @PathParam("searchstring") String searchString) {
        return getCostCenters(groupId, searchString);
    }

    /**
     *
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getcostcentersfororganization/rest/{rest}/groupid/{groupid}")
    @RESTDoc(methodName = "GetCostCenterById", desc = "Used to get CostCenter By Id", req_Params = {"CostCenterId;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_GetCostCentersForOrganization(@PathParam("groupid") String groupId) {
        return getCostCentersForOrganization(groupId);
    }

    /**
     *
     * @param groupId
     * @return TingcoGroups
     */
    @GET
    @Produces("application/json")
    @Path("/getcostcentersfororganization/json/{json}/groupid/{groupid}")
    @RESTDoc(methodName = "GetCostCenterById", desc = "Used to get CostCenter By Id", req_Params = {"CostCenterId;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getJson_GetCostCentersForOrganization(@PathParam("groupid") String groupId) {
        return getCostCentersForOrganization(groupId);
    }

    /**
     *
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getpaymentsettingfororganization/rest/{rest}/groupid/{groupid}")
    @RESTDoc(methodName = "GetCostCenterById", desc = "Used to get CostCenter By Id", req_Params = {"CostCenterId;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_GetPaymentSettingForOrganization(@PathParam("groupid") String groupId) {
        return getPaymentSettingForOrganization(groupId);
    }

    /**
     *
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getpaymentsettingfororganization/json/{json}/groupid/{groupid}")
    @RESTDoc(methodName = "GetCostCenterById", desc = "Used to get CostCenter By Id", req_Params = {"CostCenterId;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getJson_GetPaymentSettingForOrganization(@PathParam("groupid") String groupId) {
        return getPaymentSettingForOrganization(groupId);
    }

    /**
     *
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getgroupaliasfororganization/rest/{rest}/groupid/{groupid}")
    @RESTDoc(methodName = "GetCostCenterById", desc = "Used to get CostCenter By Id", req_Params = {"CostCenterId;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_GetGroupAliasForOrganization(@PathParam("groupid") String groupId) {
        return getGroupAliasForOrganization(groupId);
    }

    /**
     *
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getgroupaliasfororganization/json/{json}/groupid/{groupid}")
    @RESTDoc(methodName = "GetCostCenterById", desc = "Used to get CostCenter By Id", req_Params = {"CostCenterId;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getJson_GetGroupAliasForOrganization(@PathParam("groupid") String groupId) {
        return getGroupAliasForOrganization(groupId);
    }

    /**
     * AddGroupAlias
     * @param groupAlias
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/addgroupalias/rest/{rest}/groupalias/{groupalias}/groupid/{groupid}")
    @RESTDoc(methodName = "AddGroupAlias", desc = "Used to add GroupAlias By GroupAlias and GroupId", req_Params = {"GroupAlias;string", "GroupId;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_addGroupAlias(@PathParam("groupalias") String groupAlias, @PathParam("groupid") String groupId) {
        return addGroupAlias(groupAlias, groupId);
    }

    /**
     * AddGroupAlias
     * @param groupAlias
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/addgroupalias/json/{json}/groupalias/{groupalias}/groupid/{groupid}")
    public TingcoGroups getJson_addGroupAlias(@PathParam("groupalias") String groupAlias, @PathParam("groupid") String groupId) {
        return addGroupAlias(groupAlias, groupId);
    }

    /**
     * UpdatePaymentSettingForOrganization
     * @param costCenterId
     * @param groupId
     * @param companyPaymentEnabled
     * @param UserAliasId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/updatepaymentsettingfororganization/rest/{rest}/groupid/{groupid}/useraliasid/{useraliasid}/costcenterid/{costcenterid}/companypaymentenabled/{companypaymentenabled}")
    @RESTDoc(methodName = "AddGroupAlias", desc = "Used to add GroupAlias By GroupAlias and GroupId", req_Params = {"GroupAlias;string", "GroupId;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_updatePaymentSettingForOrganization(@PathParam("groupid") String groupId, @PathParam("useraliasid") String userAliasId, @PathParam("costcenterid") String costCenterId, @PathParam("companypaymentenabled") int companyPaymentEnabled) {
        return updatePaymentSettingForOrganization(groupId, userAliasId, costCenterId, companyPaymentEnabled);
    }

    /**
     * UpdatePaymentSettingForOrganization
     * @param costCenterId
     * @param groupId
     * @param companyPaymentEnabled
     * @param UserAliasId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/updatepaymentsettingfororganization/rest/{rest}/groupid/{groupid}/useraliasid/{useraliasid}/costcenterid/{costcenterid}/companypaymentenabled/{companypaymentenabled}")
    public TingcoGroups getJson_updatePaymentSettingForOrganization(@PathParam("groupid") String groupId, @PathParam("useraliasid") String userAliasId, @PathParam("costcenterid") String costCenterId, @PathParam("companypaymentenabled") int companyPaymentEnabled) {
        return updatePaymentSettingForOrganization(groupId, userAliasId, costCenterId, companyPaymentEnabled);
    }

    /**
     * DeleteGroupAlias
     * @param groupAlias
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deletegroupalias/rest/{rest}/groupalias/{groupalias}")
    @RESTDoc(methodName = "DeleteGroupAlias", desc = "Used to delete GroupAlias By GroupAlias", req_Params = {"GroupAlias;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_deleteGroupAlias(@PathParam("groupalias") String groupAlias) {
        return deleteGroupAlias(groupAlias);
    }

    /**
     * DeleteGroupAlias
     * @param groupAlias
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deletegroupalias/json/{json}/groupalias/{groupalias}")
    public TingcoGroups getJson_deleteGroupAlias(@PathParam("groupalias") String groupAlias) {
        return deleteGroupAlias(groupAlias);
    }

    /**
     * DeleteCostCenter
     * @param costCenterId
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deletecostcenter/rest/{rest}/costcenterid/{costcenterid}/groupid/{groupid}")
    @RESTDoc(methodName = "DeleteCostCenter", desc = "Used to delete CostCenter By CostCenterId and GroupId", req_Params = {"CostCenterId;string", "groupId;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_deleteCostCenter(@PathParam("costcenterid") String costCenterId, @PathParam("groupid") String groupId) {
        return deleteCostCenter(costCenterId, groupId);
    }

    /**
     * DeleteCostCenter
     * @param costCenterId
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deletecostcenter/json/{json}/costcenterid/{costcenterid}/groupid/{groupid}")
    public TingcoGroups getJson_deleteCostCenter(@PathParam("costcenterid") String costCenterId, @PathParam("groupid") String groupId) {
        return deleteCostCenter(costCenterId, groupId);
    }

    /**
     * UpdateCostCenter
     * @param costCenterId
     * @param costCenterName
     * @param costCenterNumber
     * @param groupId
     * @param displayinwebshop
     * @param displayorder
     * @param activefromdate
     * @param activetodate
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/updatecostcenter/rest/{rest}/costcenterid/{costcenterid}/costcentername/{costcentername}/costcenternumber/{costcenternumber}/groupid/{groupid}/displayinwebshop/{displayinwebshop}/displayorder/{displayorder}/activefromdate/{activefromdate}/activetodate/{activetodate}")
    @RESTDoc(methodName = "UpdateCostCenter", desc = "Used to update CostCenter", req_Params = {"CostCenterId;string", "CostCenterName;string", "CostCenterNumber;int", "groupId;string", "DisplayInWebShop;int", "DisplayOrder;int", "ActiveFromDate;string", "ActiveToDate;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_updateCostCenter(@PathParam("costcenterid") String costCenterId, @PathParam("costcentername") String costCenterName, @PathParam("costcenternumber") int costCenterNumber, @PathParam("groupid") String groupId, @PathParam("displayinwebshop") int displayInWebShop, @PathParam("displayorder") int displayOrder, @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate) {
        return updateCostCenter(costCenterId, costCenterName, costCenterNumber, groupId, displayInWebShop, displayOrder, activeFromDate, activeToDate);
    }

    /**
     * UpdateCostCenter
     * @param costCenterId
     * @param costCenterName
     * @param costCenterNumber
     * @param groupId
     * @param displayinwebshop
     * @param displayorder
     * @param activefromdate
     * @param activetodate
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/updatecostcenter/json/{json}/costcenterid/{costcenterid}/costcentername/{costcentername}/costcenternumber/{costcenternumber}/groupid/{groupid}/displayinwebshop/{displayinwebshop}/displayorder/{displayorder}/activefromdate/{activefromdate}/activetodate/{activetodate}")
    public TingcoGroups getJson_updateCostCenter(@PathParam("costcenterid") String costCenterId, @PathParam("costcentername") String costCenterName, @PathParam("costcenternumber") int costCenterNumber, @PathParam("groupid") String groupId, @PathParam("displayinwebshop") int displayInWebShop, @PathParam("displayorder") int displayOrder, @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate) {
        return updateCostCenter(costCenterId, costCenterName, costCenterNumber, groupId, displayInWebShop, displayOrder, activeFromDate, activeToDate);
    }

    /**
     * AddCostCenter
     * @param costCenterName
     * @param costCenterNumber
     * @param groupId
     * @param displayinwebshop
     * @param displayorder
     * @param activefromdate
     * @param activetodate
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/addcostcenter/rest/{rest}/costcentername/{costcentername}/costcenternumber/{costcenternumber}/groupid/{groupid}/displayinwebshop/{displayinwebshop}/displayorder/{displayorder}/activefromdate/{activefromdate}/activetodate/{activetodate}")
    @RESTDoc(methodName = "AddCostCenter", desc = "Used to add CostCenter", req_Params = {"CostCenterName;string", "CostCenterNumber;int", "groupId;string", "DisplayInWebShop;int", "DisplayOrder;int", "ActiveFromDate;string", "ActiveToDate;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_addCostCenter(@PathParam("costcentername") String costCenterName, @PathParam("costcenternumber") int costCenterNumber, @PathParam("groupid") String groupId, @PathParam("displayinwebshop") int displayInWebShop, @PathParam("displayorder") int displayOrder, @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate) {
        return addCostCenter(costCenterName, costCenterNumber, groupId, displayInWebShop, displayOrder, activeFromDate, activeToDate);
    }

    /**
     * AddCostCenter
     * @param costCenterName
     * @param costCenterNumber
     * @param groupId
     * @param displayinwebshop
     * @param displayorder
     * @param activefromdate
     * @param activetodate
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/addcostcenter/json/{json}/costcentername/{costcentername}/costcenternumber/{costcenternumber}/groupid/{groupid}/displayinwebshop/{displayinwebshop}/displayorder/{displayorder}/activefromdate/{activefromdate}/activetodate/{activetodate}")
    public TingcoGroups getJson_addCostCenter(@PathParam("costcentername") String costCenterName, @PathParam("costcenternumber") int costCenterNumber, @PathParam("groupid") String groupId, @PathParam("displayinwebshop") int displayInWebShop, @PathParam("displayorder") int displayOrder, @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate) {
        return addCostCenter(costCenterName, costCenterNumber, groupId, displayInWebShop, displayOrder, activeFromDate, activeToDate);
    }

    @GET
    @Produces("application/xml")
    @Path("/getaccesslogmoreinformation/rest/{rest}/accesslogrowid/{accesslogrowid}/countryid/{countryid}")
    @RESTDoc(methodName = "getAccessLogMoreInformation", desc = "Get AccessLog", req_Params = {"AccessLogRowId;string", "countryId;int"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_AccessLogMoreInformation(@PathParam("accesslogrowid") String accessLogRowId, @PathParam("countryid") int countryId) {
        return getAccessLogMoreInformation(accessLogRowId, countryId);
    }

    @GET
    @Produces("application/json")
    @Path("/getaccesslogmoreinformation/json/{json}/accesslogrowid/{accesslogrowid}/countryid/{countryid}")
    @RESTDoc(methodName = "getAccessLogMoreInformation", desc = "Get AccessLog", req_Params = {"AccessLogRowId;string", "countryId;int"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getJson_AccessLogMoreInformation(@PathParam("accesslogrowid") String accessLogRowId, @PathParam("countryid") int countryId) {
        return getAccessLogMoreInformation(accessLogRowId, countryId);
    }

    @GET
    @Produces("application/xml")
    @Path("/geteventtypes/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "getEventTypes", desc = "Get EventTypes", req_Params = {"countryId;int"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_EventTypes(@PathParam("countryid") int countryId) {
        return getEventTypes(countryId);
    }

    /**
     * GetAccessLogDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getaccesslogdetails/rest/{rest}")
    @RESTDoc(methodName = "GetAccessLogDetails", desc = "Used to Get AccessLog Details", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoGroups postXml_getAccessLogDetails(String content) {
        return getAccessLogDetails(content);
    }

    /**
     * GetAccessLogDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getaccesslogdetails/json/{json}")
    public TingcoGroups postJson_getAccessLogDetails(String content) {
        return getAccessLogDetails(content);
    }

    /**
     * GetObjectGroups
     * @param countryId
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getobjectgroupsnotlinkedtoogmdevice/rest/{rest}/deviceid/{deviceid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetObjectGroups", desc = "Used to Get ObjectGroups not linked to OGMDevice", req_Params = {"DeviceId;string", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_getObjectGroupsNotLinkedToOGMDevice(@PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId) {
        return getObjectGroupsNotLinkedToOGMDevice(deviceId, countryId);
    }

    /**
     * GetObjectGroups
     * @param countryId
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getobjectgroupsnotlinkedtoogmdevice/json/{json}/deviceid/{deviceid}/countryid/{countryid}")
    public TingcoGroups getJson_getObjectGroupsNotLinkedToOGMDevice(@PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId) {
        return getObjectGroupsNotLinkedToOGMDevice(deviceId, countryId);
    }

    /**
     * GetObjectGroupsNotLinkedToOGMContainers
     * @param countryId
     * @param containerId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getobjectgroupsnotlinkedtoogmcontainers/rest/{rest}/containerid/{containerid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetObjectGroupsNotLinkedToOGMContainers", desc = "Used to Get ObjectGroups not linked to OGMContainers", req_Params = {"ContainerId;string", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_getObjectGroupsNotLinkedToOGMContainers(@PathParam("containerid") String containerId, @PathParam("countryid") int countryId) {
        return getObjectGroupsNotLinkedToOGMContainers(containerId, countryId);
    }

    /**
     * GetObjectGroupsNotLinkedToOGMContainers
     * @param countryId
     * @param containerId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getobjectgroupsnotlinkedtoogmcontainers/json/{json}/containerid/{containerid}/countryid/{countryid}")
    public TingcoGroups getJson_getObjectGroupsNotLinkedToOGMContainers(@PathParam("containerid") String containerId, @PathParam("countryid") int countryId) {
        return getObjectGroupsNotLinkedToOGMContainers(containerId, countryId);
    }

    /**
     * GetDeviceObjectGroupsDetails
     * @param countryId
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getobjectgroupslinkedtoogmdevice/rest/{rest}/deviceid/{deviceid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetDeviceObjectGroupsDetails", desc = "Used to Get ObjectGroups linked to OGMDevice", req_Params = {"DeviceId;string", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_getObjectGroupsLinkedToOGMDevice(@PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId) {
        return getObjectGroupsLinkedToOGMDevice(deviceId, countryId);
    }

    /**
     * GetDeviceObjectGroupsDetails
     * @param countryId
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getobjectgroupslinkedtoogmdevice/json/{json}/deviceid/{deviceid}/countryid/{countryid}")
    public TingcoGroups getJson_getObjectGroupsLinkedToOGMDevice(@PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId) {
        return getObjectGroupsLinkedToOGMDevice(deviceId, countryId);
    }

    /**
     * GetContainerObjectGroupsDetails
     * @param countryId
     * @param containerId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getobjectgroupslinkedtoogmcontainers/rest/{rest}/containerid/{containerid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetContainerObjectGroupsDetails", desc = "Used to Get ObjectGroups linked to OGMContainers", req_Params = {"ContainerId;string", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_getObjectGroupsLinkedToOGMContainers(@PathParam("containerid") String containerId, @PathParam("countryid") int countryId) {
        return getObjectGroupsLinkedToOGMContainers(containerId, countryId);
    }

    /**
     * GetDeviceObjectGroupsDetails
     * @param countryId
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getobjectgroupslinkedtoogmcontainers/json/{json}/containerid/{containerid}/countryid/{countryid}")
    public TingcoGroups getJson_getObjectGroupsLinkedToOGMContainers(@PathParam("containerid") String containerId, @PathParam("countryid") int countryId) {
        return getObjectGroupsLinkedToOGMContainers(containerId, countryId);
    }

    /**
     * AddNewObjectGroup
     * @param groupId
     * @param countryId
     * @param objectGroupName
     * @param objectGroupDescription
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/addnewobjectgroup/rest/{rest}/groupid/{groupid}/countryid/{countryid}/objectgroupname/{objectgroupname}{objectgroupdescription:(/objectgroupdescription/[^/]+?)?}")
    @RESTDoc(methodName = "AddNewObjectGroup", desc = "Used to Add ObjectGroups ", req_Params = {"GroupId;string", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_AddNewObjectGroup(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId,
            @PathParam("objectgroupname") String objectGroupName, @PathParam("objectgroupdescription") String objectGroupDescription) {
        return addNewObjectGroup(groupId, countryId, objectGroupName, objectGroupDescription);
    }

    /**
     * AddNewObjectGroup
     * @param groupId
     * @param countryId
     * @param objectGroupName
     * @param objectGroupDescription
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/addnewobjectgroup/json/{json}/groupid/{groupid}/countryid/{countryid}/objectgroupname/{objectgroupname}{objectgroupdescription:(/objectgroupdescription/[^/]+?)?}")
    public TingcoGroups getJson_AddNewObjectGroup(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId,
            @PathParam("objectgroupname") String objectGroupName, @PathParam("objectgroupdescription") String objectGroupDescription) {
        return addNewObjectGroup(groupId, countryId, objectGroupName, objectGroupDescription);
    }

    @GET
    @Produces("application/xml")
    @Path("/deleteobjectgroup/rest/{rest}/objectgroupid/{objectgroupid}")
    @RESTDoc(methodName = "deleteobjectgroup", desc = "Used to deleteobjectgroup", req_Params = {"objectgroupid;string", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_DeleteObjectGroup(@PathParam("objectgroupid") String objectGroupId) {
        return deleteObjectGroup(objectGroupId);
    }

    @GET
    @Produces("application/json")
    @Path("/deleteobjectgroup/rest/{rest}/objectgroupid/{objectgroupid}")
    public TingcoGroups getJson_DeleteObjectGroup(@PathParam("objectgroupid") String objectGroupId) {
        return deleteObjectGroup(objectGroupId);
    }

    @GET
    @Produces("application/xml")
    @Path("/updateobjectgroup/rest/{rest}/objectgroupid/{objectgroupid}/countryid/{countryid}/objectgroupname/{objectgroupname}{objectgroupdescription:(/objectgroupdescription/[^/]+?)?}")
    @RESTDoc(methodName = "GetContainerObjectGroupsDetails", desc = "Used to Get ObjectGroups linked to OGMContainers", req_Params = {"ContainerId;string", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_UpdateObjectGroup(@PathParam("objectgroupid") String objectGroupId, @PathParam("countryid") int countryId,
            @PathParam("objectgroupname") String objectGroupName, @PathParam("objectgroupdescription") String objectGroupDescription) {
        return updateObjectGroups(objectGroupId, countryId, objectGroupName, objectGroupDescription);
    }

    @GET
    @Produces("application/json")
    @Path("/updateobjectgroup/json/{json}/objectgroupid/{objectgroupid}/countryid/{countryid}/objectgroupname/{objectgroupname}{objectgroupdescription:(/objectgroupdescription/[^/]+?)?}")
    public TingcoGroups getJson_UpdateObjectGroup(@PathParam("objectgroupid") String objectGroupId, @PathParam("countryid") int countryId,
            @PathParam("objectgroupname") String objectGroupName, @PathParam("objectgroupdescription") String objectGroupDescription) {
        return updateObjectGroups(objectGroupId, countryId, objectGroupName, objectGroupDescription);
    }

    @GET
    @Produces("application/xml")
    @Path("/getobjectgroups/rest/{rest}/groupid/{groupid}/countryid/{countryid}{objectgroupname:(/objectgroupname/[^/]+?)?}{organization:(/organization/[^/]+?)?}")
    @RESTDoc(methodName = "getobjectgroups", desc = "Used to Get ObjectGroups", req_Params = {"groupid;string", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_GetObjectGroups(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId,
            @PathParam("objectgroupname") String objectGroupName, @PathParam("organization") String organization) {
        return getObjectGroups(groupId, countryId, objectGroupName, organization);
    }

    @GET
    @Produces("application/json")
    @Path("/getobjectgroups/json/{json}/groupid/{groupid}/countryid/{countryid}{objectgroupname:(/objectgroupname/[^/]+?)?}{organization:(/organization/[^/]+?)?}")
    public TingcoGroups getJson_GetObjectGroups(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId,
            @PathParam("objectgroupname") String objectGroupName, @PathParam("organization") String organization) {
        return getObjectGroups(groupId, countryId, objectGroupName, organization);
    }

    /**
     * DeleteContactGroup
     * @param contactId
     * @param contactGroupId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deletecontactgroup/rest/{rest}/contactid/{contactid}/contactgroupid/{contactgroupid}")
    @RESTDoc(methodName = "DeleteContactGroup", desc = "Used to Delete ContactGroup", req_Params = {"contactId;string", "contactGroupId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_deleteContactGroup(@PathParam("contactid") String contactId, @PathParam("contactgroupid") String contactGroupId) {
        return deleteContactGroup(contactId, contactGroupId);
    }

    /**
     * DeleteContactGroup
     * @param contactId
     * @param contactGroupId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deletecontactgroup/json/{json}/contactid/{contactid}/contactgroupid/{contactgroupid}")
    public TingcoGroups getJson_deleteContactGroup(@PathParam("contactid") String contactId, @PathParam("contactgroupid") String contactGroupId) {
        return deleteContactGroup(contactId, contactGroupId);
    }

    /**
     * GetContactGroupsForContact
     * @param contactId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getcontactgroupsforcontact/rest/{rest}/contactid/{contactid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetContactGroupsForContact", desc = "Used to Get ContactGroupsForContact", req_Params = {"contactId;string", "countryId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_getContactGroupsForContact(@PathParam("contactid") String contactId, @PathParam("countryid") int countryId) {
        return getContactGroupsForContact(contactId, countryId);
    }

    /**
     * GetContactGroupsForContact
     * @param contactId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getcontactgroupsforcontact/json/{json}/contactid/{contactid}/countryid/{countryid}")
    public TingcoGroups getJson_getContactGroupsForContact(@PathParam("contactid") String contactId, @PathParam("countryid") int countryId) {
        return getContactGroupsForContact(contactId, countryId);
    }

    /**
     * GetNonLinkedContactGroupsForContact
     * @param contactId
     * @param groupId
     * @param countryId
     * @param groupName
     * @param contactGroupName
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getnonlinkedcontactgroupsforcontact/rest/{rest}/contactid/{contactid}/groupid/{groupid}/countryid/{countryid}{groupname:(/groupname/[^/]+?)?}{contactgroupname:(/contactgroupname/[^/]+?)?}")
    @RESTDoc(methodName = "GetNonLinkedContactGroupsForContact", desc = "Used to Get NonLinked ContactGroups For Contact", req_Params = {"contactId;string", "groupId;string", "countryId;string"}, opt_Params = {"groupname;string", "contactgroupname;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_getNonLinkedContactGroupsForContact(@PathParam("contactid") String contactId, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("groupname") String groupName, @PathParam("contactgroupname") String contactGroupName) {
        return getNonLinkedContactGroupsForContact(contactId, groupId, countryId, groupName, contactGroupName);
    }

    /**
     * GetNonLinkedContactGroupsForContact
     * @param contactId
     * @param groupId
     * @param countryId
     * @param groupName
     * @param contactGroupName
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getnonlinkedcontactgroupsforcontact/rest/{rest}/contactid/{contactid}/groupid/{groupid}/countryid/{countryid}{groupname:(/groupname/[^/]+?)?}{contactgroupname:(/contactgroupname/[^/]+?)?}")
    public TingcoGroups getJson_getNonLinkedContactGroupsForContact(@PathParam("contactid") String contactId, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("groupname") String groupName, @PathParam("contactgroupname") String contactGroupName) {
        return getNonLinkedContactGroupsForContact(contactId, groupId, countryId, groupName, contactGroupName);
    }

    /**
     * GetContactTypeDetails
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getcontacttypedetails/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetContactTypeDetails", desc = "Used to Get ContactTypeDetails", req_Params = {"countryId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_getContactTypeDetails(@PathParam("countryid") int countryId) {
        return getContactTypeDetails(countryId);
    }

    /**
     * GetContactTypeDetails
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getcontacttypedetails/json/{json}/countryid/{countryid}")
    public TingcoGroups getJson_getContactTypeDetails(@PathParam("countryid") int countryId) {
        return getContactTypeDetails(countryId);
    }

    /**
     * DeleteContact
     * @param contactId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deletecontact/rest/{rest}/contactid/{contactid}")
    @RESTDoc(methodName = "DeleteContact", desc = "Used to Delete Contact", req_Params = {"contactId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_deleteContact(@PathParam("contactid") String contactId) {
        return deleteContact(contactId);
    }

    /**
     * DeleteContact
     * @param contactId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deletecontact/rest/{rest}/contactid/{contactid}")
    public TingcoGroups getJson_deleteContact(@PathParam("contactid") String contactId) {
        return deleteContact(contactId);
    }

    /**
     * AddContactGroupsForContacts
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addcontactgroupsforcontacts/rest/{rest}")
    @RESTDoc(methodName = "AddContactGroupsForContacts", desc = "Used to Add ContactGroupsForContacts", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoGroups postXml_addContactGroupsForContacts(String content) {
        return addContactGroupsForContacts(content);
    }

    /**
     * AddContactGroupsForContacts
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addcontactgroupsforcontacts/json/{json}")
    public TingcoGroups postJson_addContactGroupsForContacts(String content) {
        return addContactGroupsForContacts(content);
    }

    /**
     * GetContacts
     * @param groupId
     * @param countryId
     * @param contactName
     * @param organization
     * @param contactGroup
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getcontacts/rest/{rest}/groupid/{groupid}/countryid/{countryid}{contactname:(/contactname/[^/]+?)?}{organization:(/organization/[^/]+?)?}{contactgroup:(/contactgroup/[^/]+?)?}")
    @RESTDoc(methodName = "getcontacts", desc = "Used to Get Contacts", req_Params = {"Groupid;string", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_GetContacts(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId,
            @PathParam("contactname") String contactName, @PathParam("organization") String organization, @PathParam("contactgroup") String contactGroup) {
        return getContacts(groupId, countryId, contactName, organization, contactGroup);
    }

    /**
     * GetContacts
     * @param groupId
     * @param countryId
     * @param contactName
     * @param organization
     * @param contactGroup
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getcontacts/rest/{rest}/groupid/{groupid}/countryid/{countryid}{contactname:(/contactname/[^/]+?)?}{organization:(/organization/[^/]+?)?}{contactgroup:(/contactgroup/[^/]+?)?}")
    public TingcoGroups getJson_GetContacts(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId,
            @PathParam("contactname") String contactName, @PathParam("organization") String organization, @PathParam("contactgroup") String contactGroup) {
        return getContacts(groupId, countryId, contactName, organization, contactGroup);
    }

    /**
     * AddContact
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addcontact/rest/{rest}")
    @RESTDoc(methodName = "AddContact", desc = "Used to Add Contact", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoGroups postXml_addContact(String content) {
        return addContact(content);
    }

    /**
     * AddContact
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addcontact/json/{json}")
    public TingcoGroups postJson_addContact(String content) {
        return addContact(content);
    }

    /**
     * UpdateContact
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/updatecontact/rest/{rest}")
    @RESTDoc(methodName = "UpdateContact", desc = "Used to Update Contact", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoGroups postXml_updateContact(String content) {
        return updateContact(content);
    }

    /**
     * UpdateContact
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/updatecontact/json/{json}")
    public TingcoGroups postJson_updateContact(String content) {
        return updateContact(content);
    }

    /**
     * GetContactDetails
     * @param contactId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getcontactdetails/rest/{rest}/contactid/{contactid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetContactDetails", desc = "Used to Get ContactDetails", req_Params = {"contactId;string", "countryId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoGroups getXml_getContactDetails(@PathParam("contactid") String contactId, @PathParam("countryid") int countryId) {
        return getContactDetails(contactId, countryId);
    }

    /**
     * GetContactDetails
     * @param contactId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getcontactdetails/rest/{rest}/contactid/{contactid}/countryid/{countryid}")
    public TingcoGroups getJson_getContactDetails(@PathParam("contactid") String contactId, @PathParam("countryid") int countryId) {
        return getContactDetails(contactId, countryId);
    }

    /**
     * GetDeliveryAccountsList
     * @param groupId
     * @param countryId
     * @param serviceClientLoginName
     * @param organization
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getdeliveryaccountslist/rest/{rest}/groupid/{groupid}/countryid/{countryid}{serviceclientloginname:(/serviceclientloginname/[^/]+?)?}{organization:(/organization/[^/]+?)?}")
    @RESTDoc(methodName = "GetDeliveryAccountsList", desc = "GetDeliveryAccountsList", req_Params = {"groupid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoGroups getXml_GetDeliveryAccountsList(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("serviceclientloginname") String serviceClientLoginName, @PathParam("organization") String organization) {
        return getDeliveryAccountsList(groupId, countryId, serviceClientLoginName, organization);
    }

    /**
     * GetDeliveryAccountsList
     * @param groupId
     * @param countryId
     * @param serviceClientLoginName
     * @param organization
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getdeliveryaccountslist/json/{json}/groupid/{groupid}/countryid/{countryid}{serviceclientloginname:(/serviceclientloginname/[^/]+?)?}{organization:(/organization/[^/]+?)?}")
    @RESTDoc(methodName = "GetDeliveryAccountsList", desc = "GetDeliveryAccountsList", req_Params = {"groupid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoGroups getJson_GetDeliveryAccountsList(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("serviceclientloginname") String serviceClientLoginName, @PathParam("organization") String organization) {
        return getDeliveryAccountsList(groupId, countryId, serviceClientLoginName, organization);
    }

    /**
     * Sub-resource locator method for  id
     * @return
     */
    @Path("id")
    public GroupResource getGroupResource() {
        return new GroupResource();
    }

    /**
     *
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getcontactdetailsforpopup/rest/{rest}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetContactDetailsForPopup", desc = "Used to get CostCenter By Id", req_Params = {"CostCenterId;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_GetContactDetailsForPopup(@PathParam("groupid") String groupId, @PathParam("searchstring") String searchString) {
        return getContactDetailsForPopup(groupId, searchString);
    }

    /**
     *
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getcontactdetailsforpopup/json/{json}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetContactDetailsForPopup", desc = "Used to get CostCenter By Id", req_Params = {"CostCenterId;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getjson_GetContactDetailsForPopup(@PathParam("groupid") String groupId, @PathParam("searchstring") String searchString) {
        return getContactDetailsForPopup(groupId, searchString);
    }

    /**
     * GetServiceClientLogins
     * @param serviceClientLoginId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getserviceclientlogin/rest/{rest}/serviceclientloginid/{serviceclientloginid}/countryid/{countryid}")
    public TingcoGroups getXML_getServiceClientLogins(@PathParam("countryid") int countryId, @PathParam("serviceclientloginid") String serviceClientLoginId) throws DatatypeConfigurationException {
        return getServiceClientLogins(countryId, serviceClientLoginId);
    }

    /**
     * GetServiceClientLogins
     * @param serviceClientLoginId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getserviceclientlogin/json/{json}/serviceclientloginid/{serviceclientloginid}/countryid/{countryid}")
    public TingcoGroups getJson_getServiceClientLogins(@PathParam("countryid") int countryId, @PathParam("serviceclientloginid") String serviceClientLoginId) throws DatatypeConfigurationException {
        return getServiceClientLogins(countryId, serviceClientLoginId);
    }

    /**
     * GetCostCentersByGroup
     * @param loggedInGroupid
     * @param functionArea
     * @param searchString
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getcostcentersbygroup/rest/{rest}/loggedingroupid/{loggedingroupid}/functionarea/{functionarea}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoGroups getXML_GetCostCentersByGroup(@PathParam("loggedingroupid") String loggedInGroupid, @PathParam("functionarea") String functionArea, @PathParam("searchstring") String searchString) throws DatatypeConfigurationException {
        return getCostCentersByGroup(loggedInGroupid, functionArea, searchString);
    }

    /**
     * GetCostCentersByGroup
     * @param loggedInGroupid
     * @param functionArea
     * @param searchString
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getcostcentersbygroup/json/{json}/loggedingroupid/{loggedingroupid}/functionarea/{functionarea}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoGroups getJson_GetCostCentersByGroup(@PathParam("loggedingroupid") String loggedInGroupid, @PathParam("functionarea") String functionArea, @PathParam("searchstring") String searchString) throws DatatypeConfigurationException {
        return getCostCentersByGroup(loggedInGroupid, functionArea, searchString);
    }

    /**
     * GetFAQDetailsByFAQID
     * @param faqId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getfaqdetailsbyfaqid/rest/{rest}/faqid/{faqid}")
    @RESTDoc(methodName = "GetFAQDetailsByFAQID", desc = "Get FAQ Details By FAQID", req_Params = {""}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXML_GetFAQDetailsByFAQID(@PathParam("faqid") String faqId) throws DatatypeConfigurationException {
        return getFAQDetailsByFAQID(faqId);
    }

    /**
     * GetFAQDetailsByFAQID
     * @param faqId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getfaqdetailsbyfaqid/json/{json}/faqid/{faqid}")
    public TingcoGroups getJson_GetFAQDetailsByFAQID(@PathParam("faqid") String faqId) throws DatatypeConfigurationException {
        return getFAQDetailsByFAQID(faqId);
    }

    /**
     * AddFAQDetails
     * @param content
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addfaqdetails/rest/{rest}")
    @RESTDoc(methodName = "AddFAQDetails", desc = "Add FAQ Details", req_Params = {""}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXML_AddFAQDetails(String content) throws DatatypeConfigurationException {
        return addFAQDetails(content);
    }

    /**
     * AddFAQDetails
     * @param content
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addfaqdetails/json/{json}")
    public TingcoGroups getJson_AddFAQDetails(String content) throws DatatypeConfigurationException {
        return addFAQDetails(content);
    }

    /**
     * UpdateFAQDetails
     * @param content
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/updatefaqdetails/rest/{rest}")
    @RESTDoc(methodName = "UpdateFAQDetails", desc = "Update FAQ Details", req_Params = {""}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXML_UpdateFAQDetails(String content) throws DatatypeConfigurationException {
        return updateFAQDetails(content);
    }

    /**
     * UpdateFAQDetails
     * @param content
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/updatefaqdetails/json/{json}")
    @RESTDoc(methodName = "UpdateFAQDetails", desc = "Update FAQ Details", req_Params = {""}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getJson_UpdateFAQDetails(String content) throws DatatypeConfigurationException {
        return updateFAQDetails(content);
    }

    /**
     * DeleteFAQDetail
     * @param faqId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/deletefaqdetail/rest/{rest}/faqid/{faqid}")
    @RESTDoc(methodName = "DeleteFAQDetail", desc = "Delete FAQ Detail", req_Params = {""}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXML_DeleteFAQDetail(@PathParam("faqid") String faqId) throws DatatypeConfigurationException {
        return deleteFAQDetail(faqId);
    }

    /**
     * DeleteFAQDetail
     * @param faqId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/deletefaqdetail/json/{json}/faqid/{faqid}")
    public TingcoGroups getJson_DeleteFAQDetail(@PathParam("faqid") String faqId) throws DatatypeConfigurationException {
        return deleteFAQDetail(faqId);
    }

    /**
     * GetFAQDetails
     * @param countryId
     * @param question
     * @param answer
     * @param groupid
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getfaqdetails/rest/{rest}/countryid/{countryid}{question:(/question/[^/]+?)?}{answer:(/answer/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}")
    @RESTDoc(methodName = "GetFAQDetails", desc = "GetFAQDetails", req_Params = {""}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXML_GetFAQDetails(@PathParam("countryid") int countryId, @PathParam("question") String question, @PathParam("answer") String answer, @PathParam("groupid") String groupid) throws DatatypeConfigurationException {
        return getFAQDetails(countryId, question, answer, groupid);
    }

    /**
     * GetFAQDetails
     * @param countryId
     * @param question
     * @param answer
     * @param groupid
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getfaqdetails/json/{json}/countryid/{countryid}{question:(/question/[^/]+?)?}{answer:(/answer/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}")
    public TingcoGroups getJson_GetFAQDetails(@PathParam("countryid") int countryId, @PathParam("question") String question, @PathParam("answer") String answer, @PathParam("groupid") String groupid) throws DatatypeConfigurationException {
        return getFAQDetails(countryId, question, answer, groupid);
    }

    private TingcoGroups getFAQDetails(int countryId, String question, String answer, String groupid) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.MEDIA)) {
                    ArrayList<String> operations = ht.get(TCMUtil.MEDIA);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }

                if (!question.equals("")) {
                    question = question.split("/")[2];
                } else {
                    question = null;
                }
                if (!answer.equals("")) {
                    answer = answer.split("/")[2];
                } else {
                    answer = null;
                }
                if (!groupid.equals("")) {
                    groupid = groupid.split("/")[2];
                } else {
                    groupid = null;
                }

                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Users2 loggedInUser = userDAO.getUserById(sessions2.getUserId(), session);
                    List<Object> result = groupDAO.searchFAQ(session, loggedInUser, countryId, question, answer, groupid);

                    if (result.isEmpty()) {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("No Data Found");
                        return tingcoGroups;
                    } else {
                        if (result.size() > 200) {
                            result = result.subList(0, 200);
                            tingcoGroups.setExceeds200(1);
                        } else {
                            tingcoGroups.setExceeds200(0);
                        }
                        tingcoGroups = tingcoGroupXml.buildGetFAQDetails(tingcoGroups, result);
                    }
                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }
            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups deleteFAQDetail(String faqId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.MEDIA)) {
                    ArrayList<String> operations = ht.get(TCMUtil.MEDIA);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object faqObject = groupDAO.getFAQById(session, faqId);
                    if (faqObject != null) {
                        Faq faq = (Faq) faqObject;
                        if (!groupDAO.deleteFAQ(session, faq)) {
                            tingcoGroups.getMsgStatus().setResponseCode(-1);
                            tingcoGroups.getMsgStatus().setResponseText("Error Occurred While Delete");
                            return tingcoGroups;
                        }
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("No Data Found");
                        return tingcoGroups;
                    }
                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }
            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups addFAQDetails(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.MEDIA)) {
                    ArrayList<String> operations = ht.get(TCMUtil.MEDIA);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    TingcoGroups tingcoGroupsPost = (TingcoGroups) JAXBManager.getInstance().unMarshall(content, TingcoGroups.class);

                    if (tingcoGroupsPost.getFAQs() != null) {
                        for (se.info24.groupsjaxb.FAQ faqJaxb : tingcoGroupsPost.getFAQs().getFAQ()) {
                            Users2 loggedInUser = userDAO.getUserById(sessions2.getUserId(), session);
                            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                            Faq newFaq = new Faq();
                            newFaq.setFaqid(UUID.randomUUID().toString().toUpperCase());
                            newFaq.setGroupId(loggedInUser.getGroupId());
                            newFaq.setQuestion(faqJaxb.getQuestion());
                            newFaq.setAnswer(faqJaxb.getAnswer());
                            newFaq.setCountryId(faqJaxb.getCountryID().getValue());
                            if (faqJaxb.getCategory() != null) {
                                newFaq.setCategory(faqJaxb.getCategory());
                            }
                            if (faqJaxb.getTags() != null) {
                                newFaq.setTags(faqJaxb.getTags());
                            }
                            newFaq.setLastUpdatedByUserId(loggedInUser.getUserId());
                            newFaq.setCreatedDate(gc.getTime());
                            newFaq.setUpdatedDate(gc.getTime());

                            if (!groupDAO.saveOrUpdateFAQ(newFaq, session)) {
                                tingcoGroups.getMsgStatus().setResponseCode(-1);
                                tingcoGroups.getMsgStatus().setResponseText("Error Occurred While Adding New FAQ");
                                return tingcoGroups;
                            }
                        }
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("No Data In Inputxml");
                        return tingcoGroups;
                    }
                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }
            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups updateFAQDetails(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.MEDIA)) {
                    ArrayList<String> operations = ht.get(TCMUtil.MEDIA);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    TingcoGroups tingcoGroupsPost = (TingcoGroups) JAXBManager.getInstance().unMarshall(content, TingcoGroups.class);

                    if (tingcoGroupsPost.getFAQs() != null) {
                        for (se.info24.groupsjaxb.FAQ faqJaxb : tingcoGroupsPost.getFAQs().getFAQ()) {
                            if (faqJaxb.getFAQID() != null) {
                                Object faqObject = groupDAO.getFAQById(session, faqJaxb.getFAQID());
                                if (faqObject != null) {
                                    Faq modifiedFaq = (Faq) faqObject;
                                    Users2 loggedInUser = userDAO.getUserById(sessions2.getUserId(), session);
                                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                                    modifiedFaq.setGroupId(loggedInUser.getGroupId());
                                    modifiedFaq.setQuestion(faqJaxb.getQuestion());
                                    modifiedFaq.setAnswer(faqJaxb.getAnswer());
                                    modifiedFaq.setCountryId(faqJaxb.getCountryID().getValue());
                                    if (faqJaxb.getCategory() != null) {
                                        modifiedFaq.setCategory(faqJaxb.getCategory());
                                    } else {
                                        modifiedFaq.setCategory(null);
                                    }
                                    if (faqJaxb.getTags() != null) {
                                        modifiedFaq.setTags(faqJaxb.getTags());
                                    } else {
                                        modifiedFaq.setTags(null);
                                    }
                                    modifiedFaq.setLastUpdatedByUserId(loggedInUser.getUserId());
                                    modifiedFaq.setUpdatedDate(gc.getTime());

                                    if (!groupDAO.saveOrUpdateFAQ(modifiedFaq, session)) {
                                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                                        tingcoGroups.getMsgStatus().setResponseText("Error Occurred While Updating FAQ");
                                        return tingcoGroups;
                                    }
                                } else {
                                    tingcoGroups.getMsgStatus().setResponseCode(-1);
                                    tingcoGroups.getMsgStatus().setResponseText("No Data Found");
                                    return tingcoGroups;
                                }
                            } else {
                                tingcoGroups.getMsgStatus().setResponseCode(-1);
                                tingcoGroups.getMsgStatus().setResponseText("No Data Found");
                                return tingcoGroups;
                            }
                        }
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("No Data In Inputxml");
                        return tingcoGroups;
                    }
                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }
            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups getFAQDetailsByFAQID(String faqId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.MEDIA)) {
                    ArrayList<String> operations = ht.get(TCMUtil.MEDIA);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object faqObject = groupDAO.getFAQById(session, faqId);
                    if (faqObject != null) {
                        Faq faq = (Faq) faqObject;
                        CountryDAO countryDAO = new CountryDAO();
                        Country country = countryDAO.getCountryById(faq.getCountryId(), session);
                        tingcoGroups = tingcoGroupXml.buildGetFAQDetailsByFAQID(tingcoGroups, faq, country);
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("No Data Found");
                        return tingcoGroups;
                    }
                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }
            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups getCostCentersByGroup(String loggedInGroupid, String functionArea, String searchString) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();

            if (!searchString.equals("")) {
                searchString = searchString.split("/")[2];
            } else {
                searchString = null;
            }

            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(functionArea.toUpperCase())) {
                    ArrayList<String> operations = ht.get(functionArea.toUpperCase());
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<CostCenters> costCentersesList = new ArrayList<CostCenters>();
                    if (searchString == null) {
                        costCentersesList = groupDAO.getCostCenterByGroupIdForDropDown(session, loggedInGroupid, 200);
                    } else {
                        costCentersesList = groupDAO.getCostCenterByGroupIdForDropDownSearchString(session, loggedInGroupid, 200, searchString);
                    }

                    if (!costCentersesList.isEmpty()) {
                        return tingcoGroupXml.buildgetCostCenters(tingcoGroups, costCentersesList, null);
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoGroups;
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups getContactDetailsForPopup(String groupId, String searchString) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();

            if (groupId.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("GroupId should not be empty");
                return tingcoGroups;
            }

            if (!searchString.equals("")) {
                searchString = searchString.split("/")[2];
            } else {
                searchString = null;
            }

            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;

                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<Contacts> contactses = new ArrayList<Contacts>();
                    DeviceDAO deviceDAO = new DeviceDAO();
                    List<String> groupidset = deviceDAO.getTrustedGroup(session, groupId);
                    if (searchString == null) {
                        List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                        for (List<String> list : splitList) {
                            List<Contacts> contactsesTemp = groupDAO.getContactsByDefaultSearch(new HashSet<String>(list), session);
                            if (contactses != null) {
                                contactses.addAll(contactsesTemp);
                            }
                        }
                    } else {
                        List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                        for (List<String> list : splitList) {
                            List<Contacts> contactsesTemp = groupDAO.getContactByGroupIdsListsearchString(new HashSet<String>(list), searchString, session);
                            if (contactses != null) {
                                contactses.addAll(contactsesTemp);
                            }
                        }
                    }

                    if (!contactses.isEmpty()) {
                        Collections.sort(contactses, new Comparator<Contacts>() {

                            public int compare(Contacts o1, Contacts o2) {
                                return o1.getContactName().compareToIgnoreCase(o2.getContactName());
                            }
                        });

                        if (contactses.size() > 200) {
                            return tingcoGroupXml.buildgetContactDetailsForPopup(tingcoGroups, contactses.subList(0, 200));
                        } else {
                            return tingcoGroupXml.buildgetContactDetailsForPopup(tingcoGroups, contactses);
                        }
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoGroups;
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups getDeliveryAccountsList1(String groupId, int countryId, String serviceClientLoginName, String organization) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (groupId.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("groupId should not be empty");
                return tingcoGroups;
            }
            if (!serviceClientLoginName.equals("")) {
                serviceClientLoginName = serviceClientLoginName.split("/")[2];
            } else {
                serviceClientLoginName = null;
            }
            if (!organization.equals("")) {
                organization = organization.split("/")[2];
            } else {
                organization = null;
            }

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
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(groupId, session);
                    Set<String> groupIdsTrustedList = groupDAO.getGroupIdsList(groupId, groupTrustsList);
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 == null) {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("User TimeZone is not found");
                        return tingcoGroups;
                    }
                    String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                    if (serviceClientLoginName == null && organization == null) {
                        List<ServiceClientLogins> serviceClientLoginsList = groupDAO.getServiceClientLoginsByGroupId(groupId, session, 200);
                        List<String> groupIds = new ArrayList<String>();
                        for (ServiceClientLogins scl : serviceClientLoginsList) {
                            groupIds.add(scl.getGroupId());
                        }
                        if (!groupIds.isEmpty()) {
                            List<GroupTranslations> groupTranslationsList = groupDAO.getGroupTranslationsById(groupIds, countryId, session);
                            tingcoGroups = tingcoGroupXml.buildDeliveryAccountsList(tingcoGroups, serviceClientLoginsList, groupTranslationsList, timeZoneGMToffset);
                        } else {
                            tingcoGroups.getMsgStatus().setResponseCode(-1);
                            tingcoGroups.getMsgStatus().setResponseText("No Data Found");
                            return tingcoGroups;
                        }
                    } else if (serviceClientLoginName != null && organization == null) {
                        List<ServiceClientLogins> serviceClientLoginsList = groupDAO.getServiceClientLoginsByGroupIdListAndSearchString(groupIdsTrustedList, session, serviceClientLoginName, 200);
                        List<String> groupIds = new ArrayList<String>();
                        for (ServiceClientLogins scl : serviceClientLoginsList) {
                            groupIds.add(scl.getGroupId());
                        }
                        if (!groupIds.isEmpty()) {
                            List<GroupTranslations> groupTranslationsList = groupDAO.getGroupTranslationsById(groupIds, countryId, session);
                            tingcoGroups = tingcoGroupXml.buildDeliveryAccountsList(tingcoGroups, serviceClientLoginsList, groupTranslationsList, timeZoneGMToffset);
                        } else {
                            tingcoGroups.getMsgStatus().setResponseCode(-1);
                            tingcoGroups.getMsgStatus().setResponseText("No Data Found");
                            return tingcoGroups;
                        }
                    } else if (serviceClientLoginName == null && organization != null) {
                        Groups groups = null;
                        if (TCMUtil.isValidUUID(organization)) {
                            groups = groupDAO.getGroupByGroupId(organization, session);
                        }
                        if (groups == null) {
                            List<Groups> groupsList = groupDAO.getGroupsByIdandOrganizationNumber(groupIdsTrustedList, organization, session);
                            if (groupsList.isEmpty()) {
                                List<GroupTranslations> groupTranslationseList = groupDAO.getGroupTransByGroupIdandSearchString(groupIdsTrustedList, organization, countryId, session);
                                if (!groupTranslationseList.isEmpty()) {
                                    Set<String> groupIds = new HashSet<String>();
                                    for (GroupTranslations gt : groupTranslationseList) {
                                        groupIds.add(gt.getId().getGroupId());
                                    }
                                    if (!groupIds.isEmpty()) {
                                        List<ServiceClientLogins> serviceClientLoginsList = groupDAO.getServiceClientLoginsByGroupIdList(groupIds, session, 200);
                                        tingcoGroups = tingcoGroupXml.buildDeliveryAccountsList(tingcoGroups, serviceClientLoginsList, groupTranslationseList, timeZoneGMToffset);
                                    } else {
                                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                                        tingcoGroups.getMsgStatus().setResponseText("No Data Found");
                                        return tingcoGroups;
                                    }
                                } else {
                                    tingcoGroups.getMsgStatus().setResponseCode(-1);
                                    tingcoGroups.getMsgStatus().setResponseText("No Data Found");
                                    return tingcoGroups;
                                }
                            } else {
                                Set<String> groupIds = new HashSet<String>();
                                for (Groups g : groupsList) {
                                    groupIds.add(g.getGroupId());
                                }
                                List<GroupTranslations> groupTranslationseList = groupDAO.getGroupTranslationsByGroupId(groupIds, countryId, 1000, session);
                                if (!groupIds.isEmpty()) {
                                    List<ServiceClientLogins> serviceClientLoginsList = productsDAO.getServiceClientLoginsByGroupIdList(groupIds, session);
                                    tingcoGroups = tingcoGroupXml.buildDeliveryAccountsList(tingcoGroups, serviceClientLoginsList, groupTranslationseList, timeZoneGMToffset);
                                } else {
                                    tingcoGroups.getMsgStatus().setResponseCode(-1);
                                    tingcoGroups.getMsgStatus().setResponseText("No Data Found");
                                    return tingcoGroups;
                                }
                            }
                        } else {
                            GroupTranslations groupTranslationse = groupDAO.getGroupTranslationsByIds(groups.getGroupId(), countryId, session);
                            List<ServiceClientLogins> serviceClientLoginsList = productsDAO.getServiceClientLoginsByGroupId(groupTranslationse.getId().getGroupId(), session);
                            if (!serviceClientLoginsList.isEmpty()) {
                                tingcoGroups = tingcoGroupXml.buildDeliveryAccountList(tingcoGroups, serviceClientLoginsList, groupTranslationse, timeZoneGMToffset);
                            } else {
                                tingcoGroups.getMsgStatus().setResponseCode(-1);
                                tingcoGroups.getMsgStatus().setResponseText("No Data Found");
                                return tingcoGroups;
                            }
                        }
                    } else if (serviceClientLoginName != null && organization != null) {
                        List<ServiceClientLogins> serviceClientLoginsList = groupDAO.getServiceClientLoginsByGroupIdListAndSearchString(groupIdsTrustedList, session, serviceClientLoginName, 200);
                        Groups groups = null;
                        if (TCMUtil.isValidUUID(organization)) {
                            groups = groupDAO.getGroupByGroupId(organization, session);
                        }
                        if (groups == null) {
                            List<Groups> groupsList = groupDAO.getGroupsByIdandOrganizationNumber(groupIdsTrustedList, organization, session);
                            if (groupsList.isEmpty()) {
                                List<GroupTranslations> groupTranslationseList = groupDAO.getGroupTransByGroupIdandSearchString(groupIdsTrustedList, organization, countryId, session);
                                if (!groupTranslationseList.isEmpty()) {
                                    Set<String> groupIds = new HashSet<String>();
                                    for (GroupTranslations gt : groupTranslationseList) {
                                        groupIds.add(gt.getId().getGroupId());
                                    }
                                    if (!groupIds.isEmpty()) {
                                        tingcoGroups = tingcoGroupXml.buildDeliveryAccountsList(tingcoGroups, serviceClientLoginsList, groupTranslationseList, timeZoneGMToffset);
                                    } else {
                                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                                        tingcoGroups.getMsgStatus().setResponseText("No Data Found");
                                        return tingcoGroups;
                                    }
                                } else {
                                    tingcoGroups.getMsgStatus().setResponseCode(-1);
                                    tingcoGroups.getMsgStatus().setResponseText("No Data Found");
                                    return tingcoGroups;
                                }
                            } else {
                                Set<String> groupIds = new HashSet<String>();
                                for (Groups g : groupsList) {
                                    groupIds.add(g.getGroupId());
                                }
                                if (!groupIds.isEmpty()) {
                                    List<GroupTranslations> groupTranslationseList = groupDAO.getGroupTranslationsByGroupId(groupIds, countryId, 1000, session);
                                    tingcoGroups = tingcoGroupXml.buildDeliveryAccountsList(tingcoGroups, serviceClientLoginsList, groupTranslationseList, timeZoneGMToffset);
                                } else {
                                    tingcoGroups.getMsgStatus().setResponseCode(-1);
                                    tingcoGroups.getMsgStatus().setResponseText("No Data Found");
                                    return tingcoGroups;
                                }
                            }
                        } else {
                            GroupTranslations groupTranslationse = groupDAO.getGroupTranslationsByIds(groups.getGroupId(), countryId, session);
                            if (!serviceClientLoginsList.isEmpty()) {
                                tingcoGroups = tingcoGroupXml.buildDeliveryAccountList(tingcoGroups, serviceClientLoginsList, groupTranslationse, timeZoneGMToffset);
                            } else {
                                tingcoGroups.getMsgStatus().setResponseCode(-1);
                                tingcoGroups.getMsgStatus().setResponseText("No Data Found");
                                return tingcoGroups;
                            }
                        }
                    }
                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }
            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoGroups;
    }

    private TingcoGroups getDeliveryAccountsList(String groupId, int countryId, String serviceClientLoginName, String organization) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (groupId.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("groupId should not be empty");
                return tingcoGroups;
            }
            if (!serviceClientLoginName.equals("")) {
                serviceClientLoginName = serviceClientLoginName.split("/")[2];
            } else {
                serviceClientLoginName = null;
            }
            if (!organization.equals("")) {
                organization = organization.split("/")[2];
            } else {
                organization = null;
            }

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
                    session = HibernateUtil.getSessionFactory().openSession();
//                    List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(groupId, session);
//                    Set<String> groupIdsTrustedList = groupDAO.getGroupIdsList(groupId, groupTrustsList);
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                    List<String> groupIds = new ArrayList<String>();
                    List<ServiceClientLogins> serviceClientLoginsList;
                    if (serviceClientLoginName == null && organization == null) {
                        serviceClientLoginsList = groupDAO.getServiceClientLoginsByGroupId(groupId, session, 200);
                    } else {
                        serviceClientLoginsList = groupDAO.getServiceClientLoginsBySearchCriteria(groupId, serviceClientLoginName, organization, countryId, session);
                    }
                    if (!serviceClientLoginsList.isEmpty()) {
                        for (ServiceClientLogins scl : serviceClientLoginsList) {
                            groupIds.add(scl.getGroupId());
                        }
                        List<GroupTranslations> groupTranslationsList = groupDAO.getGroupTranslationsById(groupIds, countryId, session);
                        tingcoGroups = tingcoGroupXml.buildDeliveryAccountsList(tingcoGroups, serviceClientLoginsList, groupTranslationsList, timeZoneGMToffset);
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("No Data Found");
                        return tingcoGroups;
                    }
                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }
            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoGroups;
    }

    private TingcoGroups addContact(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        Transaction tx = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
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
                    se.info24.groupsjaxb.TingcoGroups groups = (se.info24.groupsjaxb.TingcoGroups) JAXBManager.getInstance().unMarshall(content, se.info24.groupsjaxb.TingcoGroups.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    if (groups.getGroups().getGroup().size() > 0) {
                        Group groupXML = groups.getGroups().getGroup().get(0);
                        if (groupXML != null) {
                            se.info24.groupsjaxb.Contacts contact = groupXML.getContacts().get(0);
                            if (contact != null) {
                                String contactId = UUID.randomUUID().toString();
                                Contacts contacts = new Contacts();
                                contacts.setContactId(contactId);
                                contacts.setContactName(contact.getContactName());
                                contacts.setContactDescription(contact.getContactDescription());
                                contacts.setGroupId(contact.getGroupID());
                                contacts.setCreatedDate(gc.getTime());
                                contacts.setUpdatedDate(gc.getTime());
                                session.saveOrUpdate(contacts);
                                session.flush();
                                session.clear();
                                int i = 1;
                                List<ContactTypes> contactTypes = contact.getContactTypes();
                                ContactTypesInContacts ctic = null;
                                Query query = null;
                                String contactTypeInCoId = null;
                                /* Insert query written instead of using hql due to the exception:
                                com.microsoft.sqlserver.jdbc.SQLServerException: Violation of UNIQUE KEY constraint 'IX_ContactTypesInContacts' and 'IX_ContactTypeDetails' */
                                for (ContactTypes ct : contactTypes) {
                                    contactTypeInCoId = UUID.randomUUID().toString();
                                    ctic = new ContactTypesInContacts(contactTypeInCoId, contactId, ct.getContactTypeID(), sessions2.getUserId(), gc.getTime(), gc.getTime(), null);
                                    session.saveOrUpdate(ctic);
                                    List<se.info24.groupsjaxb.ContactTypeDetails> ctdsList = ct.getContactTypeDetails();
                                    for (se.info24.groupsjaxb.ContactTypeDetails ctds : ctdsList) {
                                        query = session.createSQLQuery("INSERT INTO ContactTypeDetails values(:contactDetailId, :contactDetailParentId, :contactTypeInCoId, :contactTypeFieldId, :contactFieldValue, :userId, :createdDate, :updatedDate)");
                                        query.setParameter("contactDetailId", UUID.randomUUID().toString());
                                        query.setParameter("contactDetailParentId", null);
                                        query.setParameter("contactTypeInCoId", contactTypeInCoId);
                                        query.setParameter("contactTypeFieldId", ctds.getContactTypeFieldID());
                                        query.setParameter("contactFieldValue", ctds.getContactFieldValue());
                                        query.setParameter("userId", sessions2.getUserId());
                                        query.setParameter("createdDate", gc.getTime());
                                        query.setParameter("updatedDate", gc.getTime());
                                        query.executeUpdate();
//                                        query = session.createSQLQuery("INSERT INTO ContactTypesInContacts values(:contactTypeInCoId, :contactId, :contactTypeId, :userId, :createdDate, :updatedDate)");
//                                        query.setParameter("contactTypeInCoId", contactTypeInCoId);
//                                        query.setParameter("contactId", contactId);
//                                        query.setParameter("contactTypeId",  ct.getContactTypeID());
//                                        query.setParameter("userId", sessions2.getUserId());
//                                        query.setParameter("createdDate", gc.getTime());
//                                        query.setParameter("updatedDate", gc.getTime());
//                                        query.executeUpdate();
                                        session.flush();
                                        session.clear();
                                        i++;
                                        if (i % 20 == 0) {
                                            session.flush();
                                            session.clear();
                                        }
                                    }
                                }
                                tx.commit();
                                tingcoGroups.getMsgStatus().setResponseCode(0);
                                tingcoGroups.getMsgStatus().setResponseText(contactId.toUpperCase());
                                return tingcoGroups;
                            } else {
                                tingcoGroups.getMsgStatus().setResponseCode(-1);
                                tingcoGroups.getMsgStatus().setResponseText("No Contacts found in XML");
                                return tingcoGroups;
                            }
                        } else {
                            tingcoGroups.getMsgStatus().setResponseCode(-1);
                            tingcoGroups.getMsgStatus().setResponseText("No Group found in XML");
                            return tingcoGroups;
                        }
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("Invalid Group XML Found");
                        return tingcoGroups;
                    }
                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }
            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }

            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
            return tingcoGroups;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

    }

    private TingcoGroups addContactGroupsForContacts(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        Transaction tx = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;

                        }
                    }
                }
                if (hasPermission) {
                    se.info24.groupsjaxb.TingcoGroups groups = (se.info24.groupsjaxb.TingcoGroups) JAXBManager.getInstance().unMarshall(content, se.info24.groupsjaxb.TingcoGroups.class);
                    session = HibernateUtil.getSessionFactory().openSession();

                    tx = session.beginTransaction();
                    if (groups.getGroups().getGroup().size() > 0) {
                        Group groupXML = groups.getGroups().getGroup().get(0);
                        if (groupXML != null) {
                            se.info24.groupsjaxb.ContactGroupsInContacts contactGroupsInContacts = groupXML.getContacts().get(0).getContactGroupsInContacts().get(0);
                            if (contactGroupsInContacts != null) {
                                int i = 1;
                                for (String cgid : contactGroupsInContacts.getContactGroupID()) {
                                    ContactGroupsInContacts cgic = new ContactGroupsInContacts();
                                    cgic.setId(new ContactGroupsInContactsId(contactGroupsInContacts.getContactID(), cgid));
                                    session.saveOrUpdate(cgic);
                                    i++;
                                    if (i % 20 == 0) {
                                        session.flush();
                                        session.clear();
                                    }
                                }
                                tx.commit();
                            } else {
                                tingcoGroups.getMsgStatus().setResponseCode(-1);
                                tingcoGroups.getMsgStatus().setResponseText("No ContactGroupsInContacts found in XML");
                                return tingcoGroups;
                            }
                        } else {
                            tingcoGroups.getMsgStatus().setResponseCode(-1);
                            tingcoGroups.getMsgStatus().setResponseText("No Group found in XML");
                            return tingcoGroups;
                        }
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("Invalid Group XML Found");
                        return tingcoGroups;
                    }
                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
            return tingcoGroups;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups addNewObjectGroup(String groupId, Integer countryId, String objectGroupName, String objectGroupDescription) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (groupId.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("groupId should not be empty");
                return tingcoGroups;
            }

            if (countryId == null) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("countryId should not be empty");
                return tingcoGroups;
            }

            if (objectGroupName == null) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("objectGroupName should not be empty");
                return tingcoGroups;
            }

            if (!objectGroupDescription.equals("")) {
                objectGroupDescription = objectGroupDescription.split("/")[2];
            } else {
                objectGroupDescription = null;
            }

            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;

                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();

                    GregorianCalendar gc = new GregorianCalendar();

                    ObjectGroups objectGroups = new ObjectGroups();
                    String objectGroupId = UUID.randomUUID().toString().toUpperCase();
                    objectGroups.setObjectGroupId(objectGroupId);
                    objectGroups.setGroupId(groupId);
                    objectGroups.setObjectGroupParentId(null);
                    objectGroups.setLastUpdatedByUserId(sessions2.getUserId());
                    objectGroups.setCreatedDate(gc.getTime());
                    objectGroups.setUpdatedDate(gc.getTime());
                    ObjectGroupTranslations objectGroupTranslation = new ObjectGroupTranslations();
                    objectGroupTranslation.setObjectGroupName(objectGroupName);
                    objectGroupTranslation.setObjectGroupDescription(objectGroupDescription);
                    objectGroupTranslation.setCreatedDate(gc.getTime());
                    objectGroupTranslation.setUpdatedDate(gc.getTime());
                    objectGroupTranslation.setLastUpdatedByUserId(sessions2.getUserId());
                    objectGroupTranslation.setId(new ObjectGroupTranslationsId(objectGroupId, countryId));

                    if (!groupDAO.addUpdateObjectGroup(objectGroups, objectGroupTranslation, session)) {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("Error Occured While Adding");
                        return tingcoGroups;
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups getContactDetails(String contactId, Integer countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;

                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Contacts contacts = groupDAO.getContacts(contactId, session);
                    List<ContactTypeTranslations> contactTypeTrans = groupDAO.getAllContactTypeTranslations(countryId, session);
                    GroupTranslations groupTrans = groupDAO.getGroupTranslationsByIds(contacts.getGroupId(), countryId, session);
                    tingcoGroups =
                            tingcoGroupXml.buildGetContactDetails(tingcoGroups, contacts, groupTrans, contactTypeTrans, countryId, session);
                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
            return tingcoGroups;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups getContactTypeDetails(Integer countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (countryId == null) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("countryId should not be empty");
                return tingcoGroups;
            }

            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;

                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ContactTypeTranslations> contactTypeTrans = groupDAO.getAllContactTypeTranslations(countryId, session);
                    if (!contactTypeTrans.isEmpty()) {
                        tingcoGroups = tingcoGroupXml.buildGetContactTypeDetails(tingcoGroups, contactTypeTrans, countryId, session);
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("No ContactTypes found");
                        return tingcoGroups;
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups getContactGroupsForContact(String contactId, Integer countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (contactId.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("contactId should not be empty");
                return tingcoGroups;
            }

            if (countryId == null) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("countryId should not be empty");
                return tingcoGroups;
            }

            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;

                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<String> contactGroupIdsList = groupDAO.getContactGroupsInContactsByCriteria(contactId, session);
                    if (!contactGroupIdsList.isEmpty()) {
                        List<ContactGroups> contactGroups = groupDAO.getContactGroupsByCriteria(contactGroupIdsList, session);
                        tingcoGroups =
                                tingcoGroupXml.buildGetContactGroupsForContact(tingcoGroups, contactGroups, countryId, session);
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups getContacts(String groupId, Integer countryId, String contactName, String organization, String contactGroup) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (groupId.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("groupId should not be empty");
                return tingcoGroups;
            }

            if (countryId == null) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("countryId should not be empty");
                return tingcoGroups;
            }

            if (!contactName.equals("")) {
                contactName = contactName.split("/")[2];
            } else {
                contactName = null;
            }

            if (!organization.equals("")) {
                organization = organization.split("/")[2];
            } else {
                organization = null;
            }

            if (!contactGroup.equals("")) {
                contactGroup = contactGroup.split("/")[2];
            } else {
                contactGroup = null;
            }

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
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<Contacts> contactsList = new ArrayList<Contacts>();
                    List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(groupId, session);
                    Set<String> groupIdSet = groupDAO.getGroupIdsList(groupId, groupTrustsList);
                    if (contactName == null && organization == null && contactGroup == null) {
                        List<List<String>> splitList = TCMUtil.splitStringList(new ArrayList<String>(groupIdSet), 2000);
                        for (List<String> list : splitList) {
                            List<Contacts> contactsListTemp = groupDAO.getContactsByDefaultSearch(new HashSet<String>(list), session);
                            contactsList.addAll(contactsListTemp);
                        }

                    } else {
                        List<List<String>> splitList = TCMUtil.splitStringList(new ArrayList<String>(groupIdSet), 2000);
                        for (List<String> list : splitList) {
                            List<Contacts> contactsListTemp = groupDAO.getContactsBySearch(new HashSet<String>(list), countryId, contactName, organization, contactGroup, session);
                            contactsList.addAll(contactsListTemp);
                        }

//                        contactsList = groupDAO.getContactsBySearch(groupIdSet, countryId, contactName, organization, contactGroup, session);
                    }

                    if (!contactsList.isEmpty()) {
                        tingcoGroups = tingcoGroupXml.buildGetContacts(tingcoGroups, contactsList, countryId, session);
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("No Contacts found");
                        return tingcoGroups;
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups deleteContact(String contactId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        Transaction tx = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (contactId.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("contactId should not be empty");
                return tingcoGroups;
            }

            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                            hasPermission = true;
                            break;

                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx =
                            session.beginTransaction();
                    Contacts contacts = groupDAO.getContacts(contactId, session);
                    List<ContactGroupsInContacts> contactGroupsInContacts = groupDAO.getContactGroupsInContactsByContactId(contactId, session);
                    for (ContactGroupsInContacts cgic : contactGroupsInContacts) {
                        session.delete(cgic);
                    }

                    List<ContactTypesInContacts> contactTypesInContacts = groupDAO.getContactTypesInContactsByContactId(contactId, session);
                    for (ContactTypesInContacts ctic : contactTypesInContacts) {
                        session.delete(ctic);
                    }

                    session.delete(contacts);
                    tx.commit();
                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups getNonLinkedContactGroupsForContact(String contactId, String groupId, Integer countryId, String groupName, String contactGroupName) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (contactId.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("contactId should not be empty");
                return tingcoGroups;
            }

            if (groupId.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("groupId should not be empty");
                return tingcoGroups;
            }

            if (countryId == null) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("countryId should not be empty");
                return tingcoGroups;
            }

            if (!groupName.equals("")) {
                groupName = groupName.split("/")[2];
            } else {
                groupName = null;
            }

            if (!contactGroupName.equals("")) {
                contactGroupName = contactGroupName.split("/")[2];
            } else {
                contactGroupName = null;
            }

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
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(groupId, session);
                    Set<String> groupIdSet = groupDAO.getGroupIdsList(groupId, groupTrustsList);
                    List<ContactGroupsInContacts> cgicList = groupDAO.getContactGroupsInContactsByContactId(contactId, session);
//                    if (!cgicList.isEmpty()) {
                    List<String> contactGroupIdsList = new ArrayList<String>();
                    List<ContactGroups> contactGroupsList = new ArrayList<ContactGroups>();
                    for (ContactGroupsInContacts cgic : cgicList) {
                        contactGroupIdsList.add(cgic.getId().getContactGroupId());
                    }

                    List<List<String>> spList = TCMUtil.splitStringList(new ArrayList<String>(groupIdSet), 1500);
                    for (List<String> list : spList) {
                        List<ContactGroups> contactGroupsListtemp = new ArrayList<ContactGroups>();
                        if (contactGroupName == null && groupName == null) {
                            contactGroupsListtemp = groupDAO.getNonLinkedContactGroupsForContactWithoutOptional(new HashSet<String>(list), contactGroupIdsList, session);
                        } else {
                            contactGroupsListtemp = groupDAO.getNonLinkedContactGroupsForContactWithOptional(new HashSet<String>(list), contactGroupIdsList, groupName, contactGroupName, countryId, session);
                        }
                        contactGroupsList.addAll(contactGroupsListtemp);
                        if (contactGroupsList.size() > 200) {
                            contactGroupsList = contactGroupsList.subList(0, 200);
                            break;
                        }

                    }
                    return tingcoGroupXml.buildGetContactGroupsForContact(tingcoGroups, contactGroupsList, countryId, session);
//                    } else {
//                        tingcoGroups.getMsgStatus().setResponseCode(-10);
//                        tingcoGroups.getMsgStatus().setResponseText("No data found");
//                        return tingcoGroups;
//                    }
                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups getObjectGroups(String groupId, Integer countryId, String objectGroupName, String organization) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (groupId.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("groupId should not be empty");
                return tingcoGroups;
            }

            if (countryId == null) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("countryId should not be empty");
                return tingcoGroups;
            }

            if (!objectGroupName.equals("")) {
                objectGroupName = objectGroupName.split("/")[2];
            } else {
                objectGroupName = null;
            }

            if (!organization.equals("")) {
                organization = organization.split("/")[2];
            } else {
                organization = null;
            }

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
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(groupId, session);
                        Set<String> groupIdSet = groupDAO.getGroupIdsList(groupId, groupTrustsList);

                        if (!groupIdSet.isEmpty()) {

                            if (objectGroupName != null && organization != null) {
                                List<Groups> groupsList = new ArrayList<Groups>();
                                //Search for GroupName
                                List<GroupTranslations> groupTransList = new ArrayList<GroupTranslations>();
                                List<List<String>> spList = TCMUtil.splitStringList(new ArrayList<String>(groupIdSet), 2000);
                                boolean flag = false;
                                for (List<String> list : spList) {

                                    if (!flag) {
                                        List<GroupTranslations> groupTransListTemp = groupDAO.getGroupTransByGroupIdandSearchString(new HashSet<String>(list), organization, countryId, session);
                                        groupTransList.addAll(groupTransListTemp);
                                        if (groupTransList.size() > 200) {
                                            groupTransList = groupTransList.subList(0, 200);
//                                        break;
                                        }
                                        flag = true;
                                    }
                                    List<Groups> groupsListTemp = groupDAO.getGroupsByIdandOrganizationNumber(new HashSet<String>(list), organization, session);
                                    groupsList.addAll(groupsListTemp);
                                }

                                if (groupTransList.isEmpty()) {
                                    //Search for Organization Number

                                    Set<String> groupsIdFromGroups = new HashSet<String>();
                                    for (Groups grp : groupsList) {
                                        groupsIdFromGroups.add(grp.getGroupId());
                                    }

                                    if (groupsList.isEmpty() && TCMUtil.isValidUUID(organization)) {
                                        groupsIdFromGroups.add(organization);
                                        groupTransList = groupDAO.getGroupTranslationsByGroupId(groupsIdFromGroups, countryId, 1000, session);
                                    }
                                }

                                if (!groupTransList.isEmpty()) {
                                    Set<String> groupsIds = new HashSet<String>();
                                    for (GroupTranslations gt : groupTransList) {
                                        groupsIds.add(gt.getId().getGroupId());
                                    }

                                    List<ObjectGroups> objectGroupsList = groupDAO.getObjectGroupsByGroupId(groupsIds, session);

                                    List<ObjectGroupTranslations> ogtList = groupDAO.getObjectGroupTranslationsBySearchString(session, countryId, objectGroupName);
                                    if (TCMUtil.isValidUUID(objectGroupName)) {
                                        Set<String> checkObjectGroupID = new HashSet<String>();
                                        checkObjectGroupID.add(objectGroupName);
                                        ogtList =
                                                userDAO.getObjectGroupTranslationsById(session, checkObjectGroupID, countryId);
                                    }

                                    if (!ogtList.isEmpty()) {
                                        tingcoGroups = tingcoGroupXml.buildXmlObjectGroups(tingcoGroups, ogtList, groupTransList, objectGroupsList);
                                    } else {
                                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                                        tingcoGroups.getMsgStatus().setResponseText("Data Not Found");
                                        return tingcoGroups;
                                    }

                                } else {
                                    tingcoGroups.getMsgStatus().setResponseCode(-1);
                                    tingcoGroups.getMsgStatus().setResponseText("Data Not Found");
                                    return tingcoGroups;
                                }

                            } else if (organization != null) {
                                //Search for GroupName

                                List<GroupTranslations> groupTransList = new ArrayList<GroupTranslations>();
                                List<List<String>> spList = TCMUtil.splitStringList(new ArrayList<String>(groupIdSet), 2000);
                                for (List<String> list : spList) {
                                    List<GroupTranslations> groupTransListTemp = groupDAO.getGroupTransByGroupIdandSearchString(new HashSet(list), organization, countryId, session);
                                    groupTransList.addAll(groupTransListTemp);
                                }


                                if (groupTransList.isEmpty()) {
                                    //Search for Organization Number
                                    List<Groups> groupsList = new ArrayList<Groups>();
//                                    List<List<String>> spLists = TCMUtil.splitStringList(new ArrayList<String>(groupIdSet), 2000);
                                    for (List<String> list : spList) {
                                        List<Groups> groupsListTemp = groupDAO.getGroupsByIdandOrganizationNumber(new HashSet<String>(list), organization, session);
                                        groupsList.addAll(groupsListTemp);
                                    }

                                    Set<String> groupsIdFromGroups = new HashSet<String>();
                                    for (Groups grp : groupsList) {
                                        groupsIdFromGroups.add(grp.getGroupId());
                                    }

                                    if (groupsList.isEmpty() && TCMUtil.isValidUUID(organization)) {
                                        groupsIdFromGroups.add(organization);
                                        groupTransList =
                                                groupDAO.getGroupTranslationsByGroupId(groupsIdFromGroups, countryId, 1000, session);
                                    }

                                }
                                if (groupTransList.isEmpty()) {
                                    tingcoGroups.getMsgStatus().setResponseCode(-1);
                                    tingcoGroups.getMsgStatus().setResponseText("Data Not Found");
                                    return tingcoGroups;
                                }

                                Set<String> groupsIds = new HashSet<String>();
                                for (GroupTranslations gt : groupTransList) {
                                    groupsIds.add(gt.getId().getGroupId());
                                }

                                List<ObjectGroups> objectGroupsList = groupDAO.getObjectGroupsByGroupId(groupsIds, session);
                                if (!objectGroupsList.isEmpty()) {
                                    Set<String> objectGroupIds = new HashSet<String>();
                                    for (ObjectGroups og : objectGroupsList) {
                                        objectGroupIds.add(og.getObjectGroupId());
                                    }

                                    List<ObjectGroupTranslations> ogtList = userDAO.getObjectGroupTranslationsById(session, objectGroupIds, countryId);
                                    tingcoGroups =
                                            tingcoGroupXml.buildXmlObjectGroups(tingcoGroups, ogtList, groupTransList, objectGroupsList);
                                } else {
                                    tingcoGroups.getMsgStatus().setResponseCode(-1);
                                    tingcoGroups.getMsgStatus().setResponseText("Data Not Found");
                                    return tingcoGroups;
                                }

                            } else if (objectGroupName != null) {
                                List<ObjectGroupTranslations> ogtList = groupDAO.getObjectGroupTranslationsBySearchString(session, countryId, objectGroupName);
                                if (TCMUtil.isValidUUID(objectGroupName)) {
                                    Set<String> checkObjectGroupID = new HashSet<String>();
                                    checkObjectGroupID.add(objectGroupName);
                                    ogtList =
                                            userDAO.getObjectGroupTranslationsById(session, checkObjectGroupID, countryId);
                                }

                                if (ogtList.isEmpty()) {
                                    tingcoGroups.getMsgStatus().setResponseCode(-1);
                                    tingcoGroups.getMsgStatus().setResponseText("Data Not Found");
                                    return tingcoGroups;
                                } else {
                                    List<ObjectGroups> objectGroupsList = new ArrayList<ObjectGroups>();
                                    List<GroupTranslations> groupTransList = new ArrayList<GroupTranslations>();
                                    List<List<String>> spList = TCMUtil.splitStringList(new ArrayList<String>(groupIdSet), 2000);
                                    for (List<String> list : spList) {
                                        List<ObjectGroups> objectGroupsListTemp = groupDAO.getObjectGroupsByGroupId(new HashSet<String>(list), session);
                                        objectGroupsList.addAll(objectGroupsListTemp);
                                        List<GroupTranslations> groupTransListTemp = groupDAO.getGroupTranslationsByGroupId(new HashSet<String>(list), countryId, 1000, session);
                                        groupTransList.addAll(groupTransListTemp);
                                    }

                                    List<String> objectGroupIds = new ArrayList<String>();
                                    for (ObjectGroups og : objectGroupsList) {
                                        objectGroupIds.add(og.getObjectGroupId());
                                    }


                                    tingcoGroups = tingcoGroupXml.buildXmlObjectGroups(tingcoGroups, ogtList, groupTransList, objectGroupsList);
                                }

                            } else {
                                List<ObjectGroups> objectGroupsList = new ArrayList<ObjectGroups>();
                                List<GroupTranslations> groupTransList = new ArrayList<GroupTranslations>();
                                List<List<String>> spList = TCMUtil.splitStringList(new ArrayList<String>(groupIdSet), 2000);
                                for (List<String> list : spList) {
                                    List<ObjectGroups> objectGroupsListTemp = groupDAO.getObjectGroupsByGroupId(new HashSet<String>(list), session, 200);
                                    objectGroupsList.addAll(objectGroupsListTemp);
                                    List<GroupTranslations> groupTransListTemp = groupDAO.getGroupTranslationsByGroupId(new HashSet<String>(list), countryId, 1000, session);
                                    groupTransList.addAll(groupTransListTemp);
                                }
//                                List<ObjectGroups> objectGroupsList = groupDAO.getObjectGroupsByGroupId(groupIdSet, session, 200);
                                Set<String> objectGroupIds = new HashSet<String>();
                                for (ObjectGroups og : objectGroupsList) {
                                    objectGroupIds.add(og.getObjectGroupId());
                                }

                                List<ObjectGroupTranslations> ogtList = userDAO.getObjectGroupTranslationsById(session, objectGroupIds, countryId);

                                tingcoGroups = tingcoGroupXml.buildXmlObjectGroups(tingcoGroups, ogtList, groupTransList, objectGroupsList);
                            }

                        } else {
                            tingcoGroups.getMsgStatus().setResponseCode(-1);
                            tingcoGroups.getMsgStatus().setResponseText("Data Not Found");
                            return tingcoGroups;
                        }

                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                        return tingcoGroups;
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups deleteContactGroup(String contactId, String contactGroupId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        Transaction tx = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (contactId.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("contactId should not be empty");
                return tingcoGroups;
            }
            if (contactGroupId.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("contactGroupId should not be empty");
                return tingcoGroups;
            }
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
                    tx =
                            session.beginTransaction();
                    ContactGroupsInContacts contactGroupsInContacts = groupDAO.getContactGroupsInContacts(contactId, contactGroupId, session);
                    if (contactGroupsInContacts != null) {
                        session.delete(contactGroupsInContacts);
                        tx.commit();
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("No data found");
                        return tingcoGroups;
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups getDownstreamGroupsForLoggedinUserGroup(String parentGroupID, String searchString, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();

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
                    if (!searchString.equals("")) {
                        searchString = searchString.split("/")[2];
                    } else {
                        searchString = null;
                    }

                    session = HibernateUtil.getSessionFactory().openSession();
                    List<Groups> groupsList = groupDAO.getDownstreamGroupIdsList(parentGroupID, session);
                    if (!groupsList.isEmpty()) {
                        Set<String> downstreamGroupIdsList = groupDAO.getDownstreamGroupsForLoggedInGroup(parentGroupID, groupsList, session);
                        List<GroupTranslations> groupTransList = groupDAO.getGroupTransBySearchStringCriteria(downstreamGroupIdsList, searchString, countryId, 1000, session);

                        if (!groupTransList.isEmpty()) {
                            tingcoGroups = tingcoGroupXml.buildGroupTrusts(tingcoGroups, groupTransList);
                        } else {
                            tingcoGroups.getMsgStatus().setResponseCode(-1);
                            tingcoGroups.getMsgStatus().setResponseText("No data found");
                            return tingcoGroups;
                        }
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("No data found");
                        return tingcoGroups;
                    }
                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }
            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            ex.printStackTrace();
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoGroups;
    }

    private TingcoGroups getTrustedGroupsList(String selectedGroupId, String groupId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.GROUPS, TCMUtil.READ);
                if (hasPermission) {
                    if (!selectedGroupId.equalsIgnoreCase(groupId)) {
                        session = HibernateUtil.getSessionFactory().openSession();
                        List<GroupTrusts> groupTrustsList = groupDAO.getITrustOrganizations(selectedGroupId, session);
                        if (!groupTrustsList.isEmpty()) {
                            HashMap<String, GroupTrusts> hm = groupDAO.getDownstreamTrustedGroups(groupTrustsList, session);
                            Set<String> groupID = hm.keySet();
                            List<GroupTranslations> gt = new ArrayList<GroupTranslations>();
                            if (!groupID.isEmpty()) {
                                List<List<String>> spList = TCMUtil.splitStringList(new ArrayList<String>(groupID), 2000);
                                for (List<String> list : spList) {
                                    List<GroupTranslations> gtTemp = groupDAO.getGroupTranslationsByGroupId(new HashSet<String>(list), countryId, 1000, session);
                                    gt.addAll(gtTemp);
                                    if (gt.size() > 1000) {
                                        gt = gt.subList(0, 1000);
                                        break;
                                    }
                                }
                            }
                            tingcoGroups = tingcoGroupXml.buildGetTrustedGroupsList(tingcoGroups, gt, hm, countryId, session);
                        } else {
                            tingcoGroups.getMsgStatus().setResponseCode(-1);
                            tingcoGroups.getMsgStatus().setResponseText("No data found");
                            return tingcoGroups;
                        }
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("You cannot manage your own organisation");
                        return tingcoGroups;
                    }
                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }
            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            ex.printStackTrace();
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoGroups;
    }

    private TingcoGroups updateContact(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        Transaction tx = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
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
                    se.info24.groupsjaxb.TingcoGroups groups = (se.info24.groupsjaxb.TingcoGroups) JAXBManager.getInstance().unMarshall(content, se.info24.groupsjaxb.TingcoGroups.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    if (groups.getGroups().getGroup().size() > 0) {
                        Group groupXML = groups.getGroups().getGroup().get(0);
                        if (groupXML != null) {
                            se.info24.groupsjaxb.Contacts contact = groupXML.getContacts().get(0);
                            if (contact != null) {
                                Contacts contacts = groupDAO.getContacts(contact.getContactID(), session);
                                contacts.setContactName(contact.getContactName());
                                contacts.setContactDescription(contact.getContactDescription());
                                contacts.setGroupId(contact.getGroupID());
                                contacts.setUpdatedDate(gc.getTime());
                                session.saveOrUpdate(contacts);
                                session.flush();
                                session.clear();
                                int i = 1;
                                List<ContactTypes> contactTypes = contact.getContactTypes();
                                List<se.info24.groupsjaxb.ContactTypeDetails> ctdsList = new ArrayList<se.info24.groupsjaxb.ContactTypeDetails>();
                                List<ContactTypesInContacts> cticList = new ArrayList<ContactTypesInContacts>();
                                ContactTypesInContacts ctic = null;
                                String contactTypeInCoId = null;
                                for (ContactTypes ct : contactTypes) {
                                    cticList = groupDAO.getContactTypesInContactsByContactId(contact.getContactID(), session);
                                    if (!cticList.isEmpty()) {
                                        for (ContactTypesInContacts ctics : cticList) {
                                            List<ContactTypeDefaultFields> ctdfList = groupDAO.getContactTypeDefaultFieldsByContactTypeId(ct.getContactTypeID(), session);
                                            for (ContactTypeDefaultFields ctdf : ctdfList) {
                                                List<ContactTypeDetails> ctdList = groupDAO.getcontactTypeDetailsByIds(ctics.getContactTypeInCoId(), ctdf.getId().getContactTypeFieldId(), session);
                                                for (ContactTypeDetails ctd : ctdList) {
                                                    session.delete(ctd);
                                                }
                                            }
                                            session.delete(ctics);
                                        }
                                        session.flush();
                                        session.clear();
                                    }
                                }
                                for (ContactTypes ct : contactTypes) {
                                    contactTypeInCoId = UUID.randomUUID().toString();
                                    ctic = new ContactTypesInContacts(contactTypeInCoId, contact.getContactID(), ct.getContactTypeID(), sessions2.getUserId(), gc.getTime(), gc.getTime(), null);
                                    session.saveOrUpdate(ctic);
                                    ctdsList = ct.getContactTypeDetails();
                                    for (se.info24.groupsjaxb.ContactTypeDetails ctd : ctdsList) {
                                        Query query = session.createSQLQuery("INSERT INTO ContactTypeDetails values(:contactDetailId, :contactDetailParentId, :contactTypeInCoId, :contactTypeFieldId, :contactFieldValue, :userId, :createdDate, :updatedDate)");
                                        query.setParameter("contactDetailId", UUID.randomUUID().toString());
                                        query.setParameter("contactDetailParentId", null);
                                        query.setParameter("contactTypeInCoId", contactTypeInCoId);
                                        query.setParameter("contactTypeFieldId", ctd.getContactTypeFieldID());
                                        query.setParameter("contactFieldValue", ctd.getContactFieldValue());
                                        query.setParameter("userId", sessions2.getUserId());
                                        query.setParameter("createdDate", gc.getTime());
                                        query.setParameter("updatedDate", gc.getTime());
                                        query.executeUpdate();
                                        i++;
                                        if (i % 20 == 0) {
                                            session.flush();
                                            session.clear();
                                        }
                                    }
                                }
                                tx.commit();
                            } else {
                                tingcoGroups.getMsgStatus().setResponseCode(-1);
                                tingcoGroups.getMsgStatus().setResponseText("No Contacts found in XML");
                                return tingcoGroups;
                            }
                        } else {
                            tingcoGroups.getMsgStatus().setResponseCode(-1);
                            tingcoGroups.getMsgStatus().setResponseText("No Group found in XML");
                            return tingcoGroups;
                        }
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("Invalid Group XML Found");
                        return tingcoGroups;
                    }
                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }

            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
            return tingcoGroups;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups updateObjectGroups(String objectGroupId, Integer countryId, String objectGroupName, String objectGroupDescription) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (objectGroupId.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("objectGroupId should not be empty");
                return tingcoGroups;
            }

            if (countryId == null) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("countryId should not be empty");
                return tingcoGroups;
            }

            if (objectGroupName == null) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("objectGroupName should not be empty");
                return tingcoGroups;
            }

            if (!objectGroupDescription.equals("")) {
                objectGroupDescription = objectGroupDescription.split("/")[2];
            } else {
                objectGroupDescription = null;
            }

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
                    session = HibernateUtil.getSessionFactory().openSession();
                    GregorianCalendar gc = new GregorianCalendar();
                    Set<String> objectGroupIdSet = new HashSet<String>();
                    objectGroupIdSet.add(objectGroupId);
                    List<ObjectGroups> objectGroupsList = groupDAO.getObjectGroupsByGroupId(objectGroupIdSet, session);
                    ObjectGroups objectGroups = null;
                    if (!objectGroupsList.isEmpty()) {
                        objectGroups = objectGroupsList.get(0);
                        objectGroups.setObjectGroupParentId(null);
                        objectGroups.setLastUpdatedByUserId(sessions2.getUserId());
                        objectGroups.setUpdatedDate(gc.getTime());
                    }

                    List<ObjectGroupTranslations> objectGroupTranslationList = userDAO.getObjectGroupTranslationsById(session, objectGroupIdSet, countryId);
                    ObjectGroupTranslations objectGroupTranslation = null;
                    if (!objectGroupTranslationList.isEmpty()) {
                        objectGroupTranslation = objectGroupTranslationList.get(0);
                        objectGroupTranslation.setObjectGroupName(objectGroupName);
                        objectGroupTranslation.setObjectGroupDescription(objectGroupDescription);
                        objectGroupTranslation.setUpdatedDate(gc.getTime());
                        objectGroupTranslation.setLastUpdatedByUserId(sessions2.getUserId());
                    }

                    if (!groupDAO.addUpdateObjectGroup(objectGroups, objectGroupTranslation, session)) {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("Error Occured While Updating");
                        return tingcoGroups;
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoGroups;
    }

    private TingcoGroups deleteObjectGroup(String objectGroupId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (objectGroupId.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("objectGroupId should not be empty");
                return tingcoGroups;
            }
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
                    ObjectGroups objectGroups = customerDAO.getObjectGroupsByGroupId(session, objectGroupId);
                    List<ObjectGroupTranslations> objectGroupTranslationList = userDAO.getObjectGroupTranslationsByIds(session, objectGroupId);
                    List<Ogmdevice> ogmDeviceList = deviceDao.getOgmdeviceById(session, objectGroupId);
                    List<OgmuserAlias> ogmUserAliasList = groupDAO.getOgmuserAliasByObjectGroupId(session, objectGroupId);
                    List<Ogmcontainers> ogmContainerList = groupDAO.getOgmContainersByObjectGroupId(session, objectGroupId);

                    if (!groupDAO.deleteObjectGroups(objectGroups, objectGroupTranslationList, ogmDeviceList, ogmUserAliasList, ogmContainerList, session)) {
//                    if (!groupDAO.deleteObjectGroups(objectGroups, session))  {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("Error Occured While Deleting");
                        return tingcoGroups;
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoGroups;
    }

    private TingcoGroups getAccessLogDetails(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.REPORTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.REPORTS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;

                        }
                    }
                }
                if (hasPermission) {
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    if (devices.getDevices().getDevice().size() > 0) {
                        Device deviceXML = devices.getDevices().getDevice().get(0);
                        if (deviceXML != null) {
                            UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                            if (userTimeZones2 != null) {
                                String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                                List<Object> accessLogList = groupDAO.getAccessLogDetails(deviceXML, tingcoGroups, sessions2.getUserId(), timeZoneGMToffset, session);
                                if (!accessLogList.isEmpty()) {
                                    if (accessLogList.size() > 200) {
                                        accessLogList = accessLogList.subList(0, 200);
                                        tingcoGroups.setExceeds200(1);
                                    } else {
                                        tingcoGroups.setExceeds200(0);
                                    }
                                    tingcoGroups = tingcoGroupXml.buildAccessLogDetails(tingcoGroups, accessLogList, timeZoneGMToffset, deviceXML.getCountryID().getValue(), session);

                                } else {
                                    tingcoGroups.getMsgStatus().setResponseCode(-1);
                                    tingcoGroups.getMsgStatus().setResponseText("No AccessLogs found for the given input");
                                    return tingcoGroups;
                                }
                            } else {
                                tingcoGroups.getMsgStatus().setResponseCode(-1);
                                tingcoGroups.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                                return tingcoGroups;
                            }
                        } else {
                            tingcoGroups.getMsgStatus().setResponseCode(-1);
                            tingcoGroups.getMsgStatus().setResponseText("No Device found in XML");
                            return tingcoGroups;
                        }
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("Invalid Device XML Found");
                        return tingcoGroups;
                    }
                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
            return tingcoGroups;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoGroups;
    }

    private TingcoGroups getEventTypes(int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.REPORTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.REPORTS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;

                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<EventTypeTranslations> ettList = groupDAO.getEventTypeTranslationsbyCountryId(session, String.valueOf(countryId));
                    if (!ettList.isEmpty()) {
                        return tingcoGroupXml.buildEventTypes(tingcoGroups, ettList);
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoGroups;
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups getAccessLogMoreInformation(String accessLogRowId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        Session operSession = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
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
                    operSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    AccessLog accesslog = groupDAO.getAccessLogById(operSession, accessLogRowId);
                    if (accesslog != null) {
                        String userName = null;
                        String DeviceName = null;
                        String eventTypeName = null;
                        GroupTranslations groupTranslations = groupDAO.getGroupTranslationsByIds(accesslog.getUserGroupId(), countryId, session);
                        if (groupTranslations != null) {
                            userName = groupTranslations.getGroupName();
                        }

                        groupTranslations = groupDAO.getGroupTranslationsByIds(accesslog.getDeviceGroupId(), countryId, session);
                        if (groupTranslations != null) {
                            DeviceName = groupTranslations.getGroupName();
                        }

                        EventTypeTranslations ETT = deviceDao.getEventTypeTranslations(accesslog.getEventTypeId(), countryId, session);
                        if (ETT != null) {
                            eventTypeName = ETT.getEventTypeName();
                        }

                        UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                        if (userTimeZones2 != null) {
                            String timezoneOffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                            return tingcoGroupXml.buildAccessLogMoreInformation(tingcoGroups, accesslog, userName, DeviceName, eventTypeName, timezoneOffset);
                        } else {
                            tingcoGroups.getMsgStatus().setResponseCode(-1);
                            tingcoGroups.getMsgStatus().setResponseText("TimeZone Not found");
                            return tingcoGroups;
                        }

                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("Data Not found");
                        return tingcoGroups;
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            if (operSession != null) {
                operSession.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups addCostCenter(String costCenterName, int costCenterNumber, String groupId, int displayInWebShop, int displayOrder, String activeFromDate, String activeToDate) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;

                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        Transaction tx = session.beginTransaction();
                        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));

                        CostCenters costCenter = groupDAO.getCostCenterByCostCenterNameAndGroupId(costCenterName, groupId, session);
                        if (costCenter != null) {
                            tingcoGroups.getMsgStatus().setResponseCode(-1);
                            tingcoGroups.getMsgStatus().setResponseText("Costcenter already exist with the given CostCenterName");
                            return tingcoGroups;
                        }

                        costCenter = groupDAO.getCostCenterByCostCenterNumberAndGroupId(costCenterNumber, groupId, session);
                        if (costCenter != null) {
                            tingcoGroups.getMsgStatus().setResponseCode(-1);
                            tingcoGroups.getMsgStatus().setResponseText("Costcenter already exist with the given CostCenterNumber");
                            return tingcoGroups;
                        }

                        costCenter = new CostCenters();
                        costCenter.setCostCenterId(UUID.randomUUID().toString());
                        costCenter.setCostCenterName(costCenterName);
                        costCenter.setCostCenterDescription(costCenterName);
                        costCenter.setCostCenterNumber(costCenterNumber);
                        costCenter.setGroupId(groupId);
                        costCenter.setDisplayInWebShop(displayInWebShop);
                        costCenter.setDisplayOrder(displayOrder);
                        costCenter.setCreatedDate(gc.getTime());
                        costCenter.setUpdatedDate(gc.getTime());
                        gc.setTime(lFormat.parse(activeFromDate));
                        gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                        costCenter.setActiveFromDate(gc.getTime());
                        gc.setTime(lFormat.parse(activeToDate));
                        gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                        costCenter.setActiveToDate(gc.getTime());
                        costCenter.setLastUpdatedByUserId(sessions2.getUserId());
                        session.saveOrUpdate(costCenter);
                        tx.commit();
                        UserLog userLog = new UserLog();
                        userLog.setAction("Add CostCenters");
                        userLog.setTableName("CostCenters");
                        userLog.setUserId(sessions2.getUserId());
                        userLog.setRequestIp(request.getRemoteAddr());
                        gc = new GregorianCalendar();
                        userLog.setCreatedDate(gc.getTime());
                        userLog.setUpdatedDate(gc.getTime());
                        userDAO.saveUserLog(userLog, session);
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                        return tingcoGroups;
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups addGroupAlias(String groupalias, String groupId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;

                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();
                    GroupAlias groupAlias = customerDAO.getGroupAliasByalias(session, groupalias);
                    if (groupAlias == null) {
                        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                        groupAlias =
                                new GroupAlias();
                        groupAlias.setGroupAlias(groupalias);
                        groupAlias.setGroups(new Groups(groupId));
                        groupAlias.setLastUpdatedByUserId(sessions2.getUserId());
                        groupAlias.setCreatedDate(gc.getTime());
                        groupAlias.setUpdatedDate(gc.getTime());
                        session.saveOrUpdate(groupAlias);
                        tx.commit();
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("Data already exist with the given groupAlias");
                        return tingcoGroups;
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups deleteGroupAlias(String groupAlias) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (groupAlias.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("GroupAlias should not be empty");
                return tingcoGroups;
            }

            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                            hasPermission = true;
                            break;

                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();
                    GroupAlias groupalias = customerDAO.getGroupAliasByalias(session, groupAlias);
                    if (groupalias != null) {
                        session.delete(groupalias);
                        tx.commit();
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("No GroupAlias found with the given input");
                        return tingcoGroups;
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups getObjectGroupsLinkedToOGMContainers(String containerId, Integer countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (containerId.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("containerId should not be empty");
                return tingcoGroups;
            }

            if (countryId == null) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("countryId should not be empty");
                return tingcoGroups;
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
                    List<Ogmcontainers> ogmContainersList = groupDAO.getObjectGroupsLinkedToOGMContainers(containerId, sessions2.getUserId(), session);
                    System.out.println("size is " + ogmContainersList.size());
                    if (!ogmContainersList.isEmpty()) {
                        Set<String> objectGroupIdsList = new HashSet<String>();
                        for (Ogmcontainers ogm : ogmContainersList) {
//                            objectGroupIdsList.add(ogm.getId().getObjectGroupId());
                            objectGroupIdsList.add(ogm.getObjectGroupId());
                        }

                        List<ObjectGroups> ogList = userDAO.getObjectGroupsByObjectGroupIdsList(objectGroupIdsList, session);
                        List<ObjectGroupTranslations> ogtransList = userDAO.getObjectGroupTranslationsById(session, objectGroupIdsList, countryId);
                        tingcoGroups = tingcoGroupXml.buildObjectGroupsLinkedToOGMContainers(tingcoGroups, ogmContainersList, ogtransList, ogList, countryId, session);
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("No ObjectGroups found");
                        return tingcoGroups;
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups getObjectGroupsLinkedToOGMDevice(String deviceId, Integer countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (deviceId.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("deviceId should not be empty");
                return tingcoGroups;
            }

            if (countryId == null) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("countryId should not be empty");
                return tingcoGroups;
            }

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
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<Ogmdevice> ogmdeviceList = groupDAO.getObjectGroupsLinkedToOGMDevice(deviceId, sessions2.getUserId(), session);
                    if (!ogmdeviceList.isEmpty()) {
                        boolean ogmDeviceLink = true;
                        Set<String> objectGroupIdsList = new HashSet<String>();
                        for (Ogmdevice ogm : ogmdeviceList) {
                            objectGroupIdsList.add(ogm.getObjectGroupId());
                        }

                        List<ObjectGroups> ogList = userDAO.getObjectGroupsByObjectGroupIdsList(objectGroupIdsList, session);
                        List<ObjectGroupTranslations> ogtransList = userDAO.getObjectGroupTranslationsById(session, objectGroupIdsList, countryId);
//                        tingcoGroups = tingcoGroupXml.buildObjectGroups(tingcoGroups, objectGroupsList, ogtransList, countryId, ogmDeviceLink, session);
                        tingcoGroups = tingcoGroupXml.buildObjectGroups(tingcoGroups, ogmdeviceList, ogtransList, ogList, countryId, ogmDeviceLink, session);
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("No ObjectGroups found");
                        return tingcoGroups;
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

//    private TingcoGroups getObjectGroupsNotLinkedToOGMContainers(String containerId, Integer countryId) {
//        long requestedTime = System.currentTimeMillis();
//        boolean hasPermission = false;
//        Session session = null;
//        try {
//            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
//            if (containerId.equals("")) {
//                tingcoGroups.getMsgStatus().setResponseCode(-1);
//                tingcoGroups.getMsgStatus().setResponseText("containerId should not be empty");
//                return tingcoGroups;
//            }
//
//            if (countryId == null) {
//                tingcoGroups.getMsgStatus().setResponseCode(-1);
//                tingcoGroups.getMsgStatus().setResponseText("countryId should not be empty");
//                return tingcoGroups;
//            }
//
//            if (request.getAttribute("USERSESSION") != null) {
//                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
//                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
//                if (ht.containsKey(TCMUtil.CONTAINERS)) {
//                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
//                    for (int i = 0; i <
//                            operations.size(); i++) {
//                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
//                            hasPermission = true;
//                            break;
//
//                        }
//                    }
//                }
//                if (hasPermission) {
//                    session = HibernateUtil.getSessionFactory().openSession();
//                    Users2 users2 = userDAO.getUserById(sessions2.getUserId(), session);
//                    List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(users2.getGroupId(), session);
//                    Set<String> groupIdsList = groupDAO.getGroupIdsList(users2.getGroupId(), groupTrustsList);
//                    List<String> objectGroupIdList = groupDAO.getOGMContainersObjectGroupId(containerId, session);
//                    List<ObjectGroups> objectGroupsList = groupDAO.getObjectGroupsNotLinkedToOGMDevice(objectGroupIdList, groupIdsList, session);
//                    if (!objectGroupsList.isEmpty()) {
//                        boolean ogmDeviceLink = false;
//                        Set<String> objectGroupIdsList = groupDAO.getObjectGroupIdsList(objectGroupsList);
//                        List<ObjectGroupTranslations> ogtransList = userDAO.getObjectGroupTranslationsById(session, objectGroupIdsList, countryId);
//                        tingcoGroups =
//                                tingcoGroupXml.buildObjectGroups(tingcoGroups, objectGroupsList, ogtransList, countryId, ogmDeviceLink, session);
//                    } else {
//                        tingcoGroups.getMsgStatus().setResponseCode(-1);
//                        tingcoGroups.getMsgStatus().setResponseText("No ObjectGroups found");
//                        return tingcoGroups;
//                    }
//
//                } else {
//                    tingcoGroups.getMsgStatus().setResponseCode(-10);
//                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
//                    return tingcoGroups;
//                }
//
//            } else {
//                tingcoGroups.getMsgStatus().setResponseCode(-3);
//                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
//                return tingcoGroups;
//            }
//
//        } catch (Exception ex) {
//            TCMUtil.exceptionLog(getClass().getName(), ex);
//            tingcoGroups.getMsgStatus().setResponseCode(-1);
//            tingcoGroups.getMsgStatus().setResponseText("Error occured");
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//
//            delayLog(requestedTime);
//        }
//
//        return tingcoGroups;
//    }
    private TingcoGroups getObjectGroupsNotLinkedToOGMContainers(String containerId, Integer countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (containerId.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("containerId should not be empty");
                return tingcoGroups;
            }

            if (countryId == null) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("countryId should not be empty");
                return tingcoGroups;
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
                    Users2 users2 = userDAO.getUserById(sessions2.getUserId(), session);
                    List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(users2.getGroupId(), session);
                    Set<String> groupIdsList = groupDAO.getGroupIdsList(users2.getGroupId(), groupTrustsList);
                    List<String> objectGroupIdList = groupDAO.getOGMContainersObjectGroupId(containerId, session);
                    List<ObjectGroups> objectGroupsList = new ArrayList<ObjectGroups>();
                    List<List<String>> spList = TCMUtil.splitStringList(new ArrayList<String>(groupIdsList), 1000);
                    for (List<String> list : spList) {
                        List<ObjectGroups> objectGroupsListTemp = groupDAO.getObjectGroupsNotLinkedToOGMDevice(objectGroupIdList, new HashSet<String>(list), session);
                        objectGroupsList.addAll(objectGroupsListTemp);
                    }

                    if (!objectGroupsList.isEmpty()) {
                        boolean ogmDeviceLink = false;
                        Set<String> objectGroupIdsList = groupDAO.getObjectGroupIdsList(objectGroupsList);
                        List<ObjectGroupTranslations> ogtransList = userDAO.getObjectGroupTranslationsById(session, objectGroupIdsList, countryId);
                        tingcoGroups = tingcoGroupXml.buildObjectGroups(tingcoGroups, new HashSet<ObjectGroups>(objectGroupsList), ogtransList, countryId, ogmDeviceLink, session);
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("No ObjectGroups found");
                        return tingcoGroups;
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups getObjectGroupsNotLinkedToOGMDevice(String deviceId, Integer countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (deviceId.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("deviceId should not be empty");
                return tingcoGroups;
            }

            if (countryId == null) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("countryId should not be empty");
                return tingcoGroups;
            }

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
                    session = HibernateUtil.getSessionFactory().openSession();
                    Users2 users2 = userDAO.getUserById(sessions2.getUserId(), session);
                    List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(users2.getGroupId(), session);
                    Set<String> groupIdsList = groupDAO.getGroupIdsList(users2.getGroupId(), groupTrustsList);
                    List<String> objectGroupIdList = groupDAO.getOGMDeviceObjectGroupId(deviceId, session);

//                    List<ObjectGroups> objectGroupsList = groupDAO.getObjectGroupsNotLinkedToOGMDevice(objectGroupIdList, groupIdsList, session);
                    List<ObjectGroups> objectGroupsList = new ArrayList<ObjectGroups>();
                    List<List<String>> spList = TCMUtil.splitStringList(new ArrayList<String>(groupIdsList), 1000);
                    for (List<String> list : spList) {
                        List<ObjectGroups> objectGroupsListTemp = groupDAO.getObjectGroupsNotLinkedToOGMDevice(objectGroupIdList, new HashSet<String>(list), session);
                        objectGroupsList.addAll(objectGroupsListTemp);
                    }
                    if (!objectGroupsList.isEmpty()) {
                        boolean ogmDeviceLink = false;
                        Set<String> objectGroupIdsList = groupDAO.getObjectGroupIdsList(objectGroupsList);
                        List<ObjectGroupTranslations> ogtransList = userDAO.getObjectGroupTranslationsById(session, objectGroupIdsList, countryId);
                        tingcoGroups = tingcoGroupXml.buildObjectGroups(tingcoGroups, new HashSet<ObjectGroups>(objectGroupsList), ogtransList, countryId, ogmDeviceLink, session);
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("No ObjectGroups found");
                        return tingcoGroups;
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups updateCostCenter(String costCenterId, String costCenterName, int costCenterNumber, String groupId, int displayInWebShop, int displayOrder, String activeFromDate, String activeToDate) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;

                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        Transaction tx = session.beginTransaction();
                        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                        CostCenters costCenter = groupDAO.getCostCenterById(costCenterId, session);
                        if (costCenter != null) {
                            costCenter.setCostCenterName(costCenterName);
                            costCenter.setCostCenterDescription(costCenterName);
                            costCenter.setCostCenterNumber(costCenterNumber);
                            costCenter.setGroupId(groupId);
                            costCenter.setDisplayInWebShop(displayInWebShop);
                            costCenter.setDisplayOrder(displayOrder);
                            costCenter.setUpdatedDate(gc.getTime());
                            gc.setTime(lFormat.parse(activeFromDate));
                            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                            costCenter.setActiveFromDate(gc.getTime());

                            gc.setTime(lFormat.parse(activeToDate));
                            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                            costCenter.setActiveToDate(gc.getTime());
                            costCenter.setLastUpdatedByUserId(sessions2.getUserId());
                            session.saveOrUpdate(costCenter);
                            tx.commit();
                            UserLog userLog = new UserLog();
                            userLog.setAction("Update CostCenters");
                            userLog.setTableName("CostCenters");
                            userLog.setUserId(sessions2.getUserId());
                            userLog.setRequestIp(request.getRemoteAddr());
                            gc =
                                    new GregorianCalendar();
                            userLog.setCreatedDate(gc.getTime());
                            userLog.setUpdatedDate(gc.getTime());
                            userDAO.saveUserLog(userLog, session);
                        } else {
                            tingcoGroups.getMsgStatus().setResponseCode(-10);
                            tingcoGroups.getMsgStatus().setResponseText("No CostCenter found with the given input");
                            return tingcoGroups;
                        }

                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                        return tingcoGroups;
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups deleteCostCenter(String costCenterId, String groupId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (costCenterId.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("CostCenterID should not be empty");
                return tingcoGroups;
            }

            if (groupId.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("GroupID should not be empty");
                return tingcoGroups;
            }

            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                            hasPermission = true;
                            break;

                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    CostCenters costCenter = groupDAO.getCostCenterByCostCenterIdAndGroupId(costCenterId, groupId, session);

                    GroupDefaultUserAlias groupDefaultUserAlias = groupDAO.getGroupDefaultUserAliasByCostCenterIdAndGroupId(groupId, costCenterId, session);

                    if (costCenter == null & groupDefaultUserAlias == null) {   //If both are null, return no data found. If anyone is not null, go to else condition
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("No data found");
                        return tingcoGroups;
                    } else {
                        if (costCenter != null) {
                            session.delete(costCenter);
                        }

                        if (groupDefaultUserAlias != null) {
                            session.delete(groupDefaultUserAlias);
                        }

                        tx.commit();
                        UserLog userLog = new UserLog();
                        userLog.setAction("Delete");
                        userLog.setTableName("CostCenters;GroupDefaultUserAlias");
                        userLog.setUserId(sessions2.getUserId());
                        userLog.setRequestIp(request.getRemoteAddr());
                        userLog.setCreatedDate(gc.getTime());
                        userLog.setUpdatedDate(gc.getTime());
                        userDAO.saveUserLog(userLog, session);
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups getGroupAliasForOrganization(String groupId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (groupId.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("GroupId should not be empty");
                return tingcoGroups;
            }

            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;

                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<GroupAlias> groupAliasList = groupDAO.getGroupAliasBYGroupId(session, groupId);
                    Collections.sort(groupAliasList, new Comparator<GroupAlias>() {

                        public int compare(GroupAlias ga1, GroupAlias ga2) {
                            return ga1.getGroupAlias().compareToIgnoreCase(ga2.getGroupAlias());
                        }
                    });
                    if (!groupAliasList.isEmpty()) {
                        return tingcoGroupXml.buildGetGroupAliasForOrganization(tingcoGroups, groupAliasList);
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoGroups;
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups getPaymentSettingForOrganization(String groupId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();

            if (groupId.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("GroupId should not be empty");
                return tingcoGroups;
            }

            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;

                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    GroupDefaultUserAlias groupDefaultUserAliasList = groupDAO.getGroupDefaultUserAliasByGroupId(session, groupId);
                    if (groupDefaultUserAliasList != null) {
                        Object object = userDAO.getUserAliasDetailsByUserAliasID(session, groupDefaultUserAliasList.getUserAliasId());
                        UserAlias ua = (UserAlias) object;
                        CostCenters costCenters = groupDAO.getCostCenterById(groupDefaultUserAliasList.getCostCenterId(), session);
                        return tingcoGroupXml.buildGetPaymentSettingForOrganization(tingcoGroups, groupDefaultUserAliasList, ua, costCenters);
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoGroups;
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups getCostCentersForOrganization(String groupId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();

            if (groupId.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("GroupId should not be empty");
                return tingcoGroups;
            }

            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;

                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<CostCenters> costCentersesList = groupDAO.getCostCenterByGroupIdForDropDown(session, groupId, 500);
                    if (!costCentersesList.isEmpty()) {
                        UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                        if (userTimeZones2 != null) {
                            String timezoneOffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                            return tingcoGroupXml.buildgetCostCenters(tingcoGroups, costCentersesList, timezoneOffset);

                        } else {
                            tingcoGroups.getMsgStatus().setResponseCode(-1);
                            tingcoGroups.getMsgStatus().setResponseText("UserTimeZone Not Found. Unable to display data in UserTimezone");
                            return tingcoGroups;
                        }

                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoGroups;
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups getCostCenters(String groupId, String searchString) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();

            if (groupId.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("GroupId should not be empty");
                return tingcoGroups;
            }

            if (!searchString.equals("")) {
                searchString = searchString.split("/")[2];
            } else {
                searchString = null;
            }

            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;

                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<CostCenters> costCentersesList = new ArrayList<CostCenters>();
                    if (searchString == null) {
                        costCentersesList = groupDAO.getCostCenterByGroupIdForDropDown(session, groupId, 100);
                    } else {
                        costCentersesList = groupDAO.getCostCenterByGroupIdForDropDownSearchString(session, groupId, 100, searchString);
                    }

                    if (!costCentersesList.isEmpty()) {
                        return tingcoGroupXml.buildgetCostCenters(tingcoGroups, costCentersesList, null);
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoGroups;
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    public TingcoGroups getCostCentreByID(
            String costCenterId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();

            if (costCenterId.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("CostCenterID should not be empty");
                return tingcoGroups;
            }

            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;

                        }




                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    CostCenters costCenter = groupDAO.getCostCenterById(costCenterId, session);
                    if (costCenter != null) {
                        UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                        if (userTimeZones2 != null) {
                            String timezoneOffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                            return tingcoGroupXml.buildCostCentreByID(tingcoGroups, costCenter, timezoneOffset);
                        } else {
                            tingcoGroups.getMsgStatus().setResponseCode(-1);
                            tingcoGroups.getMsgStatus().setResponseText("UserTimeZone Not Found. Unable to display data in UserTimezone");
                            return tingcoGroups;
                        }

                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoGroups;
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups addGroup_EV(String groupName, String grp_desc, String groupTypeID, int countryID, String domainID, String grpParID, String appID, String userID, HttpServletRequest request, String roleID) {
        long requestedTime = System.currentTimeMillis();
        Session session = HibernateUtil.getSessionFactory().openSession();
        TingcoGroups groups = null;
        try {
            groups = groupDAO.buildGroupTemplate();
            groups =
                    groupDAO.addNewGroup(groupName, grp_desc, groupTypeID, countryID, domainID, grpParID, appID, userID, groups, session, roleID);
            return groups;
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        } finally {
            session.close();
            delayLog(requestedTime);
        }

        return groups;
    }

    private TingcoGroups addOrganization(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
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
                    TingcoGroups groups = (TingcoGroups) JAXBManager.getInstance().unMarshall(content, TingcoGroups.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    if (groups.getGroups().getGroup().size() > 0) {
                        Group groupXML = groups.getGroups().getGroup().get(0);
                        if (groupXML != null) {
                            ApplicationSettings as = groupDAO.getAppSettingValueByIDandName(groupXML.getApplicationID(), "Default Group", session);
                            if (as != null) {
                                Users2 users2 = userDAO.getUserById(sessions2.getUserId(), session);
                                if (!groupDAO.addNewOrganization(groupXML, users2, as, session)) {

                                    TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Add Organization", groupXML.getGroupNames().getGroupName().get(0).getValue(), "Groups;GroupTrust;", "Failed");
                                    tingcoGroups.getMsgStatus().setResponseCode(-1);
                                    tingcoGroups.getMsgStatus().setResponseText("Error Occured while Adding New Organization");
                                    return tingcoGroups;
                                } else {
                                    TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Add Organization", groupXML.getGroupNames().getGroupName().get(0).getValue(), "Groups;GroupTrust;", "Success");
                                }
                            } else {
                                TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Add Organization", groupXML.getGroupNames().getGroupName().get(0).getValue(), "Groups;GroupTrust;", "Failed");
                                tingcoGroups.getMsgStatus().setResponseCode(-1);
                                tingcoGroups.getMsgStatus().setResponseText("No Record found in ApplicationSettings for Info24 Group");
                                return tingcoGroups;
                            }
                        } else {
                            tingcoGroups.getMsgStatus().setResponseCode(-1);
                            tingcoGroups.getMsgStatus().setResponseText("Invalid Device XML Found");
                            return tingcoGroups;
                        }
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("Invalid Device XML Found");
                        return tingcoGroups;
                    }
                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups deleteGroupDetails(String groupId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (groupId.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("GroupID  should not be empty");
                return tingcoGroups;
            }

            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                            hasPermission = true;
                            break;

                        }

                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Groups groups = groupDAO.getGroupByGroupId(groupId, session);
                    if (groups != null) {
                        GroupTranslations gt = groupDAO.getGroupTranslationsByIds(groupId, 6, session);
                        if (!groupDAO.deleteGroupDetails(groupId, groups, session)) {
                            TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Delete Organization", gt.getGroupName(), "Groups;GroupTrust;", "Failed");
                            tingcoGroups.getMsgStatus().setResponseCode(-1);
                            tingcoGroups.getMsgStatus().setResponseText("Error occured while deleting Group Details");
                            return tingcoGroups;
                        } else {
                            TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Delete Organization", gt.getGroupName(), "Groups;GroupTrust;", "Success");
                        }

                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("No Data found in Groups");
                        return tingcoGroups;
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups getGroupDetails(String groupId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        ApplicationDAO applicationDAO = new ApplicationDAO();
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (groupId.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("GroupID  should not be empty");
                return tingcoGroups;
            } else if (countryId <= 0) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("CountryId should not be empty");
                return tingcoGroups;
            }

            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.GROUPS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Groups groups = groupDAO.getGroupByGroupId(groupId, session);
                    if (groups != null) {
                        GroupTranslations groupTrans = groupDAO.getGroupTranslationsByIds(groups.getGroupParentId(), countryId, session);
                        GroupTypeTranslations groupTypeTrans = groupDAO.getGroupTypeTranslationsByGroupTypeId(groups.getGroupTypeId(), countryId, session);
                        GroupDefaultAgreement groupDefaultAgreementList = productsDAO.getGroupDefaultAgreement(groupId, session);
                        List<se.info24.pojo.GroupVisibleApplicationPackages> gvapList = applicationDAO.getGroupVisibleApplicationPackagesbyGroupid(groupId, session);

                        List<GroupVisibleApplicationSolutions> gvasList = applicationDAO.getGroupVisibleApplicationSolutionsbyGroupid(groupId, session);
                        List<String> applicationSolutionIdList = new ArrayList<String>();
                        List<ApplicationSolutionTranslations> astList = new ArrayList<ApplicationSolutionTranslations>();
                        if (!gvasList.isEmpty()) {
                            for (GroupVisibleApplicationSolutions gvas : gvasList) {
                                applicationSolutionIdList.add(gvas.getId().getApplicationSolutionId());
                            }

                            astList = groupDAO.getApplicationSolutionTranslations(applicationSolutionIdList, session);
                        }

                        List<GroupApplicationPackages> gapList = applicationDAO.getGroupApplicationPackagesbyGroupId(groupId, session);
                        List<String> applicationPackageIdList = new ArrayList<String>();
                        List<String> applicationSolutionIdLists = new ArrayList<String>();
                        List<ApplicationPackageTranslations> aptList = new ArrayList<ApplicationPackageTranslations>();
                        List<ApplicationPackages> apsList = new ArrayList<ApplicationPackages>();
                        List<ApplicationSolutionTranslations> astsList = new ArrayList<ApplicationSolutionTranslations>();
                        if (!gapList.isEmpty()) {
                            for (GroupApplicationPackages gap : gapList) {
                                applicationPackageIdList.add(gap.getId().getApplicationPackageId());
                            }

                            aptList = groupDAO.getApplicationPackageTranslations(applicationPackageIdList, session);
                            apsList =
                                    groupDAO.getApplicationPackages(applicationPackageIdList, session);
                            if (!apsList.isEmpty()) {
                                for (ApplicationPackages ap : apsList) {
                                    applicationSolutionIdLists.add(ap.getApplicationSolutions().getApplicationSolutionId());
                                }

                            }
                            astsList = groupDAO.getApplicationSolutionTranslations(applicationSolutionIdLists, session);
                        }

                        List<String> visibleapplicationPackageIdList = new ArrayList<String>();
                        List<ApplicationPackageTranslations> aptsList = new ArrayList<ApplicationPackageTranslations>();
                        if (!gvapList.isEmpty()) {
                            for (se.info24.pojo.GroupVisibleApplicationPackages gap : gvapList) {
                                visibleapplicationPackageIdList.add(gap.getId().getApplicationPackageId());
                            }

                            aptsList = groupDAO.getApplicationPackageTranslations(visibleapplicationPackageIdList, session);
                        }

                        GroupLimits groupLimitsList = groupDAO.getGroupLimitsByGroupId(groupId, session);
                        GroupLimitPackages grouplimitpackageslist = new GroupLimitPackages();
                        if (groupLimitsList != null) {
                            if (groupLimitsList.getGroupLimitPackages() != null) {
                                if (null != groupLimitsList.getGroupLimitPackages().getGroupLimitPackageId()) {
                                    grouplimitpackageslist = productsDAO.getGroupLimitPackagesById(groupLimitsList.getGroupLimitPackages().getGroupLimitPackageId(), session);
                                }

                            }

                        }

                        Object objectAddressesObject = session.get(ObjectAddresses.class, new ObjectAddressesId(groupId, 3));
                        Addresses addresses = null;
                        if (objectAddressesObject != null) {
                            ObjectAddresses objectAddresses = (ObjectAddresses) objectAddressesObject;
                            String addressId = objectAddresses.getAddressId();
                            Object addressesObject = session.get(Addresses.class, addressId);
                            if (addressesObject != null) {
                                addresses = (Addresses) addressesObject;
                            }
                        }
                        tingcoGroups = tingcoGroupXml.buildTingcoGroupDetails(tingcoGroups, groups, groupTrans, groupTypeTrans, groupDefaultAgreementList, addresses, astList, grouplimitpackageslist, aptsList, aptList, countryId, astsList);
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("No Data found in Groups");
                        return tingcoGroups;
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;

    }

    private TingcoGroups getGroupsByNumber(String groupID, int countryID, String searchString) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        boolean hasPermission = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tingcoGroups =
                    tingcoGroupXml.buildTingcoGroupTemplate();
            if (groupID.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("GroupID  should not be empty");
                return tingcoGroups;
            } else if (countryID <= 0) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("CountryId should not be empty");
                return tingcoGroups;
            }

            if (!searchString.equals("")) {
                searchString = searchString.split("/")[2];
            }

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
                    List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(groupID, session);
                    Set<String> groupIdSet = groupDAO.getGroupIdsList(groupID, groupTrustsList);
                    if (!groupIdSet.isEmpty()) {
                        List<Groups> groupsList = new ArrayList<Groups>();
                        List<GroupTranslations> groupTranslationList = new ArrayList<GroupTranslations>();
                        List<List<String>> splitList = TCMUtil.splitStringList(new ArrayList<String>(groupIdSet), 2000);
                        for (List<String> list : splitList) {
                            List<Groups> groupsListTemp = groupDAO.getGroupsByIdandOrganizationNumber(new HashSet<String>(list), searchString, session);
                            List<GroupTranslations> groupTranslationListTemp = groupDAO.getGroupTransByGroupIdandSearchString(new HashSet<String>(list), searchString, Integer.valueOf(countryID), session);
                            groupsList.addAll(groupsListTemp);
                            groupTranslationList.addAll(groupTranslationListTemp);
                        }

                        Set<String> groupsIdsList = new HashSet<String>();
                        if (!groupsList.isEmpty()) {
                            groupsIdsList = groupDAO.getGroupIdsList(groupsList);
                        }


                        Set<String> groupTransIdsList = new HashSet();
                        if (!groupTranslationList.isEmpty()) {
                            groupTransIdsList = groupDAO.getGroupTransIdsList(groupTranslationList);
                        }

                        groupsIdsList.addAll(groupTransIdsList);
                        if (!groupsIdsList.isEmpty()) {
                            List<GroupTranslations> groupTransList = groupDAO.getGroupTranslationsByGroupId(groupsIdsList, countryID, 1000, session);
                            if (!groupTransList.isEmpty()) {
                                tingcoGroups = tingcoGroupXml.buildGroupTrusts(tingcoGroups, groupTransList);
                            }

                        } else {
                            tingcoGroups.getMsgStatus().setResponseCode(-1);
                            tingcoGroups.getMsgStatus().setResponseText("No Groups found with the search string");
                            return tingcoGroups;
                        }

                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("No Data found in Groups");
                        return tingcoGroups;
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups getGroupsList(String groupId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (groupId.equals("")) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("GroupID  should not be empty");
                return tingcoGroups;
            } else if (countryId <= 0) {
                tingcoGroups.getMsgStatus().setResponseCode(-1);
                tingcoGroups.getMsgStatus().setResponseText("CountryId should not be empty");
                return tingcoGroups;
            }
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
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(groupId, session);
                        Set<String> groupIdSet = groupDAO.getGroupIdsList(groupId, groupTrustsList);

                        List<Groups> groupsList = new ArrayList<Groups>();
                        List<GroupTranslations> groupTranslationsList1 = new ArrayList<GroupTranslations>();
                        List<List<String>> splitList = TCMUtil.splitStringList(new ArrayList<String>(groupIdSet), 2000);
                        for (List<String> list : splitList) {
                            List<Groups> groupsListTemp = groupDAO.getGroupsByIdList(new HashSet<String>(list), session);
                            groupsList.addAll(groupsListTemp);
                            List<GroupTranslations> groupTranslationsList1Temp = groupDAO.getGroupTranslationsByGroupId(new HashSet<String>(list), countryId, 1000, session);
                            groupTranslationsList1.addAll(groupTranslationsList1Temp);
                        }


                        if (!groupsList.isEmpty()) {
                            List<String> groupParentIdSet = new ArrayList<String>();
                            List<String> groupTypeIdList = new ArrayList<String>();
                            List<String> userIdList = new ArrayList<String>();
                            List<Users2> usersList = new ArrayList<Users2>();

                            List<GroupTranslations> groupTranslationsList = new ArrayList<GroupTranslations>();

                            for (Groups gs : groupsList) {
                                if (gs.getGroupTypeId() != null) {
                                    groupTypeIdList.add(gs.getGroupTypeId());
                                }

                                if (gs.getUserId() != null) {
                                    userIdList.add(gs.getUserId());
                                }

                                if (gs.getGroupParentId() != null) {
                                    groupParentIdSet.add(gs.getGroupParentId());
                                }
                            }
//                            if (!groupIdSet.isEmpty()) {
//                                groupTranslationsList1 = groupDAO.getGroupTranslationsByGroupId(groupIdSet, countryId, 1000, session);
//                            }

                            if (!groupParentIdSet.isEmpty()) {
                                groupTranslationsList = groupDAO.getGroupTranslationsById(groupParentIdSet, countryId, session);
                            }

                            if (!userIdList.isEmpty()) {
                                usersList = groupDAO.getUsersList(userIdList, session);
                            }

                            List<GroupTypeTranslations> groupTypeTransList = new ArrayList<GroupTypeTranslations>();
                            if (!groupTypeIdList.isEmpty()) {
                                groupTypeTransList = groupDAO.getGroupTypeTranslations(groupTypeIdList, countryId, session);
                            }

                            tingcoGroups = tingcoGroupXml.buildTingcoGroupList(tingcoGroups, groupsList, groupTypeTransList, usersList, groupTranslationsList, groupTranslationsList1, timeZoneGMToffset);
                        } else {
                            tingcoGroups.getMsgStatus().setResponseCode(-1);
                            tingcoGroups.getMsgStatus().setResponseText("No Data found in Groups");
                            return tingcoGroups;
                        }

                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                        return tingcoGroups;
                    }
                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups getObjectGroupsForPopup(String groupID, int countryID, String searchString) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        boolean hasPermission = false;
        try {
            if (request.getAttribute("USERSESSION") != null) {
                tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
                if (groupID == null) {
                    tingcoGroups.getMsgStatus().setResponseCode(-1);
                    tingcoGroups.getMsgStatus().setResponseText("GroupID  should not be empty");
                    return tingcoGroups;
                } else if (countryID <= 0) {
                    tingcoGroups.getMsgStatus().setResponseCode(-1);
                    tingcoGroups.getMsgStatus().setResponseText("CountryID  should not be empty");
                    return tingcoGroups;
                }

                if (!searchString.equals("")) {
                    searchString = searchString.split("/")[2];
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
                    List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(groupID, session);
                    Set<String> groupIdSet = groupDAO.getGroupIdsList(groupID, groupTrustsList);

                    List<ObjectGroups> objectGroupsList = new ArrayList<ObjectGroups>();

                    List<List<String>> splitList = TCMUtil.splitStringList(new ArrayList<String>(groupIdSet), 2000);
                    for (List<String> list : splitList) {
                        List<ObjectGroups> objectGroupsListTemp = groupDAO.getObjectGroupsByGroupId(new HashSet<String>(list), session);
                        objectGroupsList.addAll(objectGroupsListTemp);
                    }

                    if (!objectGroupsList.isEmpty()) {
                        Set<String> objectGroupIdSet = groupDAO.getObjectGroupIdsList(objectGroupsList);
                        if (objectGroupIdSet.isEmpty()) {
                            tingcoGroups.getMsgStatus().setResponseCode(-1);
                            tingcoGroups.getMsgStatus().setResponseText("No record found ");
                            return tingcoGroups;
                        }
                        List<ObjectGroupTranslations> objectGroupTranslations = new ArrayList<ObjectGroupTranslations>();
                        if (searchString != null) {
                            objectGroupTranslations = groupDAO.getObjectGroupTranslationsByIdandSearchString(objectGroupIdSet, countryID, searchString, session);
                        } else {
                            objectGroupTranslations = userDAO.getObjectGroupTranslationsById(session, objectGroupIdSet, countryID);
                        }

                        if (!objectGroupTranslations.isEmpty()) {
                            tingcoGroups = tingcoGroupXml.buileObjectGroups(tingcoGroups, objectGroupTranslations);
                        } else {
                            tingcoGroups.getMsgStatus().setResponseCode(-1);
                            tingcoGroups.getMsgStatus().setResponseText("No record found with the given Details");
                            return tingcoGroups;
                        }

                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("No record found with the given GroupId in ObjectGroups");
                        return tingcoGroups;
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups groupAdded(String groupName, String groupTypeID, String grpParID, String grp_desc, HttpServletRequest request) {
        long requestedTime = System.currentTimeMillis();
        Session session = HibernateUtil.getSessionFactory().openSession();
        TingcoGroups groups = null;
        GroupDAO manager = new GroupDAO();
        UserSessions2 user_sess = (UserSessions2) request.getAttribute("USERSESSION");
        GregorianCalendar gc = new GregorianCalendar();
        try {
            if (user_sess.getActiveToDate().after(gc.getTime())) {
                manager.addGroup(groupName, groupTypeID, grpParID, grp_desc, user_sess.getApplicationId(), user_sess.getUserId(), session);
            } else {
                TCMUtil tcm = new TCMUtil();
                groups =
                        tcm.group_SessionExpired();
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            session.close();
            delayLog(requestedTime);
        }

        return groups;
    }

    private TingcoGroups newGroupAdded(String groupName, String grp_desc, String groupTypeID, int countryID, String grpParID, HttpServletRequest request, String roleID) {
        long requestedTime = System.currentTimeMillis();
        Session session = HibernateUtil.getSessionFactory().openSession();
        TingcoGroups groups = null;
        GroupDAO manager = new GroupDAO();
        UserSessions2 user_sess = (UserSessions2) request.getAttribute("USERSESSION");
        GregorianCalendar gc = new GregorianCalendar();
        try {
            if (user_sess.getActiveToDate().after(gc.getTime())) {
                groups = manager.buildGroupTemplate();
                groups =
                        manager.addNewGroup(groupName, grp_desc, groupTypeID, countryID, user_sess.getDomainId(), grpParID, user_sess.getApplicationId(), user_sess.getUserId(), groups, session, roleID);

            } else {
                TCMUtil tcm = new TCMUtil();
                groups =
                        tcm.group_SessionExpired();
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            session.close();
            delayLog(requestedTime);
        }

        return groups;
    }

    private TingcoGroups deletedGroup(String groupID, HttpServletRequest request) {
        long requestedTime = System.currentTimeMillis();
        Session session = HibernateUtil.getSessionFactory().openSession();
        TingcoGroups groups = null;
        UserSessions2 user_sess = (UserSessions2) request.getAttribute("USERSESSION");
        GregorianCalendar gc = new GregorianCalendar();
        GroupDAO manager = new GroupDAO();
        boolean del = false;
        try {
            if (user_sess.getActiveToDate().after(gc.getTime())) {
                del = manager.deleteGroup(groupID, session);
                groups =
                        manager.buildGroupTemplate();
                if (del) {
                    groups.getMsgStatus().setResponseText("Group Deleted");
                } else {
                    groups.getMsgStatus().setResponseText("Error Occured in  Deleting the Group");
                }

            } else {
                TCMUtil tcm = new TCMUtil();
                groups =
                        tcm.group_SessionExpired();
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            session.close();
            delayLog(requestedTime);
        }

        return groups;
    }

    private TingcoGroups groupsInfo(String groupID, String grouptypeID, HttpServletRequest request) {
        long requestedTime = System.currentTimeMillis();
        Session session = HibernateUtil.getSessionFactory().openSession();
        TingcoGroups groups = null;
        GroupDAO manager = new GroupDAO();
        UserSessions2 user_sess = (UserSessions2) request.getAttribute("USERSESSION");
        GregorianCalendar gc = new GregorianCalendar();
        try {
            if (user_sess.getActiveToDate().after(gc.getTime())) {
                groups = manager.getGroups(groupID, user_sess.getUserId(), grouptypeID, session);
            } else {
                TCMUtil tcm = new TCMUtil();
                groups =
                        tcm.group_SessionExpired();
            }

            groups = manager.getGroups(groupID, user_sess.getUserId(), grouptypeID, session);
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            session.close();
            delayLog(requestedTime);
        }

        return groups;
    }

    private TingcoGroups updateOrganization(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
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
                    TingcoGroups groups = (TingcoGroups) JAXBManager.getInstance().unMarshall(content, TingcoGroups.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    if (groups.getGroups().getGroup().size() > 0) {
                        Group groupXML = groups.getGroups().getGroup().get(0);
                        if (groupXML != null) {
                            ApplicationSettings as = groupDAO.getAppSettingValueByIDandName(groupXML.getApplicationID(), "Default Group", session);
                            if (as != null) {
                                Users2 users2 = userDAO.getUserById(sessions2.getUserId(), session);
                                if (!groupDAO.updateOrganization(groupXML, users2, as, session)) {
                                    System.out.println("error");
//                                    TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Organization", groupXML.getGroupNames().getGroupName().get(0).getValue(), "Groups;GroupTrust;", "Failed");
//                                    tingcoGroups.getMsgStatus().setResponseCode(-1);
//                                    tingcoGroups.getMsgStatus().setResponseText("Error Occured while updating Organization");
//                                    return tingcoGroups;
                                } else {
                                    TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Organization", groupXML.getGroupNames().getGroupName().get(0).getValue(), "Groups;GroupTrust;", "Success");
                                }
                            } else {
                                TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update Organization", groupXML.getGroupNames().getGroupName().get(0).getValue(), "Groups;GroupTrust;", "Success");
                                tingcoGroups.getMsgStatus().setResponseCode(-1);
                                tingcoGroups.getMsgStatus().setResponseText("No Record found in ApplicationSettings for Info24 Group");
                                return tingcoGroups;
                            }
                        } else {
                            tingcoGroups.getMsgStatus().setResponseCode(-1);
                            tingcoGroups.getMsgStatus().setResponseText("Invalid Device XML Found");
                            return tingcoGroups;
                        }
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("Invalid Device XML Found");
                        return tingcoGroups;
                    }
                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups updatePaymentSettingForOrganization(String groupId, String userAliasId, String costCenterId, int companyPaymentEnabled) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.GROUPS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;

                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    GroupDefaultUserAlias groupDefaultUserAlias = null;
                    if (companyPaymentEnabled == 1) {
                        groupDefaultUserAlias = new GroupDefaultUserAlias();
                        groupDefaultUserAlias.setGroupId(groupId);
                        groupDefaultUserAlias.setUserAliasId(userAliasId);
                        groupDefaultUserAlias.setCostCenterId(costCenterId);
                        groupDefaultUserAlias.setCompanyPaymentEnabled(companyPaymentEnabled);
                        groupDefaultUserAlias.setUserId(sessions2.getUserId());
                        groupDefaultUserAlias.setLastUpdatedByUserId(sessions2.getUserId());
                        groupDefaultUserAlias.setCreatedDate(gc.getTime());
                        groupDefaultUserAlias.setUpdatedDate(gc.getTime());
                        session.saveOrUpdate(groupDefaultUserAlias);
                    } else {
                        groupDefaultUserAlias = groupDAO.getGroupDefaultUserAliasByGroupId(session, groupId);
                        if (groupDefaultUserAlias != null) {
                            session.delete(groupDefaultUserAlias);
                        }

                    }
                    tx.commit();
                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups updatedGroup(String groupID, String grpParID, String groupName, String group_desc, HttpServletRequest request) {
        long requestedTime = System.currentTimeMillis();
        Session session = HibernateUtil.getSessionFactory().openSession();
        TingcoGroups groups = null;
        UserSessions2 user_sess = (UserSessions2) request.getAttribute("USERSESSION");
        GregorianCalendar gc = new GregorianCalendar();
        try {
            if (user_sess.getActiveToDate().after(gc.getTime())) {
                groups = groupDAO.updateGroup(groupID, grpParID, groupName, group_desc, user_sess.getUserId(), session);
                groups.getMsgStatus().setResponseText("Group Updated Successfully");
            } else {
                TCMUtil tcm = new TCMUtil();
                groups =
                        tcm.group_SessionExpired();
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            session.close();
            delayLog(requestedTime);
        }

        return groups;
    }

    @SuppressWarnings("unchecked")
    private TingcoGroups isGroupExists(
            String groupId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId == null) {
                    tingcoGroups.getMsgStatus().setResponseCode(-1);
                    tingcoGroups.getMsgStatus().setResponseText("GroupID is should not be empty");
                    return tingcoGroups;
                } else if (countryId <= 0) {
                    tingcoGroups.getMsgStatus().setResponseCode(-1);
                    tingcoGroups.getMsgStatus().setResponseText("CountryID is should not be empty");
                    return tingcoGroups;
                }

                session = HibernateUtil.getSessionFactory().openSession();
                Groups group = groupDAO.getGroupByGroupId(groupId, session);
                if (group != null) {
                    if (group.getGroupTranslationses() != null) {
                        tingcoGroupXml.buildGroupInfo(tingcoGroups, group.getGroupTranslationses(), countryId);
                    }

                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-2);
                    tingcoGroups.getMsgStatus().setResponseText("No Group Found with This GroupID");
                    return tingcoGroups;
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error Occured while Getting Data");
            return tingcoGroups;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups allGroups(String appID, int countryID) {
        long requestedTime = System.currentTimeMillis();
        Session session = HibernateUtil.getSessionFactory().openSession();
        TingcoGroups groups = null;
        GroupDAO manager = new GroupDAO();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                groups = manager.buildGroupTemplate();
                groups =
                        manager.getAllGroups(appID, countryID, groups, session);
            } else {
                TCMUtil tcm = new TCMUtil();
                groups =
                        tcm.group_SessionExpired();
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            session.close();
            delayLog(requestedTime);
        }

        return groups;
    }

    /**
     * Convert a String to a Hashtable, with type checking
     * @param data the thing to convert
     * @return the converted data
     */
    public static Hashtable string2Hashtable(
            String data, Class superclass) {
        long requestedTime = System.currentTimeMillis();
        Hashtable commands = new Hashtable();
        StringTokenizer st = new StringTokenizer(data, " ");
        String[] data_arr = null;
        int j = 0;
        while (st.hasMoreTokens()) {
            data_arr[j] = st.nextToken();
            j++;

        }




        try {
            for (int i = 0; i <
                    data_arr.length; i++) {

                int equ_pos = data_arr[i].indexOf('=');
                String key = data_arr[i].substring(0, equ_pos);
                String value = data_arr[i].substring(equ_pos + 1);
                Class clazz = Class.forName(value);

                if (clazz.isAssignableFrom(superclass)) {
                    throw new ClassCastException("Type Error");
                }

                commands.put(key, value);

            }

        } catch (Exception ex) {
        }

        return commands;
    }

    private TingcoGroups getGroupTypes(int countryId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (countryId <= 0) {
                    tingcoGroups.getMsgStatus().setResponseCode(-1);
                    tingcoGroups.getMsgStatus().setResponseText("CountryID is should not be empty");
                    return tingcoGroups;
                }

                session = HibernateUtil.getSessionFactory().openSession();
                tingcoGroups =
                        tingcoGroupXml.buildTingcoGroupTypes(tingcoGroups, groupDAO.getGroupTypesbyCountryID(countryId, session));
                return tingcoGroups;
            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoGroups;
    }

    private TingcoGroups getServiceClientLogins(int countryId, String serviceClientLoginId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        boolean hasPermission = false;
        try {
            tingcoGroups = tingcoGroupXml.buildTingcoGroupTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.GROUPS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object sclObject = deviceDao.getServiceClientLoginsById(serviceClientLoginId, session);
                    if (sclObject != null) {
                        ServiceClientLogins serviceClientLogins = (ServiceClientLogins) sclObject;
                        UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        tingcoGroups = tingcoGroupXml.buildServiceClientLogins(tingcoGroups, serviceClientLogins, countryId, timeZoneGMToffset);
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("No data found");
                        return tingcoGroups;
                    }
                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-10);
                    tingcoGroups.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoGroups;
                }
            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error occured");
            return tingcoGroups;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoGroups;
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

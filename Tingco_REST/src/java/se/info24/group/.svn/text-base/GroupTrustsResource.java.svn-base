/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.group;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
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
import se.info24.apijaxb.TingcoAPI;
import se.info24.groupsjaxb.TingcoGroups;
import se.info24.jaxbUtil.TingcoAPIXML;
import se.info24.jaxbUtil.TingcoGroupXML;
import se.info24.jaxbUtil.TingcoUserXML;
import se.info24.permission.PermissionDAO;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupTrusts;
import se.info24.pojo.GroupTrustsId;
import se.info24.pojo.UserRoleObjectPermissions2;
import se.info24.pojo.UserSessions2;
import se.info24.user.UserManager;
import se.info24.user.User_LoginsResource;
import se.info24.usersjaxb.TingcoUsers;
import se.info24.util.HibernateUtil;
import se.info24.util.JAXBManager;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * rest Web Service
 *
 * @author Ravikant
 */
@Path("/grouptrusts")
public class GroupTrustsResource {

    @Context
    private UriInfo context;
    @Context
    HttpServletRequest request;
    GroupDAO groupDAO = new GroupDAO();
    TingcoGroupXML tingcoGroupXML = new TingcoGroupXML();
//    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of GroupTrustsResource */
    public GroupTrustsResource() {
    }

    /**
     * GetAllMyTrustedOrganizations
     * @param request
     * @param groupID
     * @param countryID
     * @return
     */
    @GET
    @Path("/getallmytrustedorganizations/rest/{rest}/groupid/{groupid}/countryid/{countryid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetAllMyTrustedOrganizations", desc = "Returns all the Trusted Organisations for the Given Organisation", req_Params = {"groupid;UUID", "countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_MyTrustOrganizations(@Context HttpServletRequest request, @PathParam("groupid") String groupID, @PathParam("countryid") int countryID) {
        return myTrustedOrganisations(groupID, countryID, request);
    }

    /**
     * GetAllMyTrustedOrganizations
     * @param request
     * @param groupID
     * @param countryID
     * @return
     */
    @GET
    @Path("/getallmytrustedorganizations/json/{json}/groupid/{groupid}/countryid/{countryid}")
    @Produces("application/json")
    public TingcoUsers getJson_MyTrustOrganizations(@Context HttpServletRequest request, @PathParam("groupid") String groupID, @PathParam("countryid") int countryID) {
        return myTrustedOrganisations(groupID, countryID, request);
    }

    /**
     * GetAllTrustingOrganizations
     * @param request
     * @param groupID
     * @param countryID
     * @return
     */
    @GET
    @Path("/getalltrustingorganizations/rest/{rest}/groupid/{groupid}/countryid/{countryid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetAllTrustingOrganizations", desc = "Returns all Organisations that trust the given organisation", req_Params = {"GroupID;UUID", "CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_All(@Context HttpServletRequest request, @PathParam("groupid") String groupID, @PathParam("countryid") int countryID) {
        return myTrustingOrganistaions(groupID, countryID, request);
    }

    /**
     * GetAllTrustingOrganizations
     * @param request
     * @param groupID
     * @param countryID
     * @return
     */
    @GET
    @Path("/getalltrustingorganizations/json/{json}/groupid/{groupid}/countryid/{countryid}")
    @Produces("application/json")
    public TingcoUsers getJson_All(@Context HttpServletRequest request, @PathParam("groupid") String groupID, @PathParam("countryid") int countryID) {
        return myTrustingOrganistaions(groupID, countryID, request);
    }

    /**
     * GetGroupTrusts
     * @param groupId
     * @param countryId
     * @param domainID
     * @param groupName
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/get/rest/{rest}/groupid/{groupid}/countryid/{countryid}/domainid/{domainid}{groupname:(/groupname/[^/]+?)?}")
    @RESTDoc(methodName = "GetGroupTrusts", desc = "Get All the Trusting Group Information", req_Params = {"GroupID;UUID", "CountryID;int", "DomainID;UUID"}, opt_Params = {"GroupName;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoGroups getXml(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("domainid") String domainID, @PathParam("groupname") String groupName) {
        return getGroupTrustInfo(groupId, countryId, domainID, groupName);
    }

    /**
     * GetGroupTrusts
     * @param groupId
     * @param countryId
     * @param domainID
     * @param groupName
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/get/rest/{rest}/groupid/{groupid}/countryid/{countryid}/domainid/{domainid}{groupname:(/groupname/[^/]+?)?}")
    public TingcoGroups postXml(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("domainid") String domainID, @PathParam("groupname") String groupName) {
        return getGroupTrustInfo(groupId, countryId, domainID, groupName);
    }

    /**
     * GetGroupTrusts
     * @param groupId
     * @param countryId
     * @param domainID
     * @param groupName
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/get/json/{json}/groupid/{groupid}/countryid/{countryid}/domainid/{domainid}{groupname:(/groupname/[^/]+?)?}")
    public TingcoGroups getJson(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("domainid") String domainID, @PathParam("groupname") String groupName) {
        return getGroupTrustInfo(groupId, countryId, domainID, groupName);
    }

    /**
     * GetGroupTrusts
     * @param groupId
     * @param countryId
     * @param domainID
     * @param groupName
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/get/json/{json}/groupid/{groupid}/countryid/{countryid}/domainid/{domainid}{groupname:(/groupname/[^/]+?)?}")
    public TingcoGroups postJson(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("domainid") String domainID, @PathParam("groupname") String groupName) {
        return getGroupTrustInfo(groupId, countryId, domainID, groupName);
    }

    /**
     * GetITrustOrganizations
     * @param groupId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getitrustorganizations/rest/{rest}/groupid/{groupid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetITrustOrganizations", desc = "Get All the Own Trusting Group Information", req_Params = {"GroupID;UUID", "CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUsers getXml_ITrustOrganizations(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getITrustGroupTruts(groupId, countryId);
    }

    /**
     * GetITrustOrganizations
     * @param groupId
     * @param countryId
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/getitrustorganizations/rest/{rest}/groupid/{groupid}/countryid/{countryid}")
    public TingcoUsers postXml_ITrustOrganizations(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getITrustGroupTruts(groupId, countryId);
    }

    /**
     * GetITrustOrganizations
     * @param groupId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getitrustorganizations/json/{json}/groupid/{groupid}/countryid/{countryid}")
    public TingcoUsers getJson_ITrustOrganizations(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getITrustGroupTruts(groupId, countryId);
    }

    /**
     * GetITrustOrganizations
     * @param groupId
     * @param countryId
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/getitrustorganizations/json/{json}/groupid/{groupid}/countryid/{countryid}")
    public TingcoUsers postJson_ITrustOrganizations(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getITrustGroupTruts(groupId, countryId);
    }

    /**
     * GetOrganizationsRnPByGroupID
     * @param groupId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getorganizationrnpbygroupid/rest/{rest}/groupid/{groupid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetOrganizationsRnPByGroupID", desc = "Get All the Organizations Roles and Permission Names by GroupID", req_Params = {"GroupID;UUID", "CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUsers getXml_OrganizationsRnPByGroupID(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getOrganizationsRnPNamesByGroupID(groupId, countryId);
    }

    /**
     * GetOrganizationsRnPByGroupID
     * @param groupId
     * @param countryId
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/getorganizationrnpbygroupid/rest/{rest}/groupid/{groupid}/countryid/{countryid}")
    public TingcoUsers postXml_OrganizationsRnPByGroupID(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getOrganizationsRnPNamesByGroupID(groupId, countryId);
    }

    /**
     * GetOrganizationsRnPByGroupID
     * @param groupId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getorganizationrnpbygroupid/json/{json}/groupid/{groupid}/countryid/{countryid}")
    public TingcoUsers getJson_OrganizationsRnPByGroupID(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getOrganizationsRnPNamesByGroupID(groupId, countryId);
    }

    /**
     * GetOrganizationsRnPByGroupID
     * @param groupId
     * @param countryId
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/getorganizationrnpbygroupid/json/{json}/groupid/{groupid}/countryid/{countryid}")
    public TingcoUsers postJson_OrganizationsRnPByGroupID(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getOrganizationsRnPNamesByGroupID(groupId, countryId);
    }

    /**
     * GroupTrust_Add
     * @param groupID
     * @param itrustGroupID
     * @param userRoleID
     * @param lastUpdatedByUserID
     * @param isInternal
     * @param isCustomer
     * @param isSupplier
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/add/rest/{rest}/groupid/{groupid}/itrustgroupid/{itrustgroupid}/userroleid/{userroleid}{lastupdatedbyuserid:(/lastupdatedbyuserid/[^/]+?)?}{isinternal:(/isinternal/[^/]+?)?}{iscustomer:(/iscustomer/[^/]+?)?}{issupplier:(/issupplier/[^/]+?)?}")
    @RESTDoc(methodName = "GroupTrust_Add", desc = "Used to create a GroupTrust", req_Params = {"GroupID;UUID", "ITrustGroupID;UUID", "UserRoleID;UUID"}, opt_Params = {"LastUpdatedByUserID;UUID", "IsInternal;int", "IsCustomer;int", "IsSupplier;int"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoAPI getXml_Add(@PathParam("groupid") String groupID, @PathParam("itrustgroupid") String itrustGroupID, @PathParam("userroleid") String userRoleID, @PathParam("lastupdatedbyuserid") String lastUpdatedByUserID, @PathParam("isinternal") String isInternal, @PathParam("iscustomer") String isCustomer, @PathParam("issupplier") String isSupplier) {
        return createGroupTrust(groupID, itrustGroupID, userRoleID, lastUpdatedByUserID, isInternal, isCustomer, isSupplier);
    }

    /**
     * GroupTrust_Add
     * @param groupID
     * @param itrustGroupID
     * @param userRoleID
     * @param lastUpdatedByUserID
     * @param isInternal
     * @param isCustomer
     * @param isSupplier
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/add/rest/{rest}/groupid/{groupid}/itrustgroupid/{itrustgroupid}/userroleid/{userroleid}{lastupdatedbyuserid:(/lastupdatedbyuserid/[^/]+?)?}{isinternal:(/isinternal/[^/]+?)?}{iscustomer:(/iscustomer/[^/]+?)?}{issupplier:(/issupplier/[^/]+?)?}")
    public TingcoAPI postXml_Add(@PathParam("groupid") String groupID, @PathParam("itrustgroupid") String itrustGroupID, @PathParam("UserRoleID") String userRoleID, @PathParam("lastupdatedbyuserid") String lastUpdatedByUserID, @PathParam("isinternal") String isInternal, @PathParam("iscustomer") String isCustomer, @PathParam("issupplier") String isSupplier) {
        return createGroupTrust(groupID, itrustGroupID, userRoleID, lastUpdatedByUserID, isInternal, isCustomer, isSupplier);
    }

    /**
     * GroupTrust_Add
     * @param groupID
     * @param itrustGroupID
     * @param userRoleID
     * @param lastUpdatedByUserID
     * @param isInternal
     * @param isCustomer
     * @param isSupplier
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/add/json/{json}/groupid/{groupid}/itrustgroupid/{itrustgroupid}/userroleid/{userroleid}{lastupdatedbyuserid:(/lastupdatedbyuserid/[^/]+?)?}{isinternal:(/isinternal/[^/]+?)?}{iscustomer:(/iscustomer/[^/]+?)?}{issupplier:(/issupplier/[^/]+?)?}")
    public TingcoAPI getJson_Add(@PathParam("groupid") String groupID, @PathParam("ITrustGroupID") String itrustGroupID, @PathParam("UserRoleID") String userRoleID, @PathParam("lastupdatedbyuserid") String lastUpdatedByUserID, @PathParam("IsInternal") String isInternal, @PathParam("IsCustomer") String isCustomer, @PathParam("issupplier") String isSupplier) {
        return createGroupTrust(groupID, itrustGroupID, userRoleID, lastUpdatedByUserID, isInternal, isCustomer, isSupplier);
    }

    /**
     * GroupTrust_Add
     * @param groupID
     * @param itrustGroupID
     * @param userRoleID
     * @param lastUpdatedByUserID
     * @param isInternal
     * @param isCustomer
     * @param isSupplier
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/add/json/{json}/groupid/{groupid}/itrustgroupid/{itrustgroupid}/userroleid/{userroleid}{lastupdatedbyuserid:(/lastupdatedbyuserid/[^/]+?)?}{isinternal:(/isinternal/[^/]+?)?}{iscustomer:(/iscustomer/[^/]+?)?}{issupplier:(/issupplier/[^/]+?)?}")
    public TingcoAPI postJson_Add(@PathParam("groupid") String groupID, @PathParam("itrustgroupid") String itrustGroupID, @PathParam("UserRoleID") String userRoleID, @PathParam("lastupdatedbyuserid") String lastUpdatedByUserID, @PathParam("isinternal") String isInternal, @PathParam("iscustomer") String isCustomer, @PathParam("issupplier") String isSupplier) {
        return createGroupTrust(groupID, itrustGroupID, userRoleID, lastUpdatedByUserID, isInternal, isCustomer, isSupplier);
    }

    /**
     * GroupTrust_Delete
     * @param request
     * @param groupID
     * @param roleID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/groupid/{groupid}/userroleid/{userroleid}")
    @RESTDoc(methodName = "GroupTrust_Delete", desc = "Delete's a GroupTrust", req_Params = {"GroupID;UUID", "UserRoleID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_Delete(@Context HttpServletRequest request, @PathParam("groupid") String groupID, @PathParam("userroleid") String roleID) {
        return groupTrustDeleted(groupID, roleID, request);
    }

    /**
     * GroupTrust_Delete
     * @param request
     * @param groupID
     * @param roleID
     * @return
     */
    @GET
    @Path("/delete/json/{json}/groupid/{groupid}/userroleid/{userroleid}")
    @Produces("application/json")
    public TingcoGroups getJson_Delete(@Context HttpServletRequest request, @PathParam("groupid") String groupID, @PathParam("userroleid") String roleID) {
        return groupTrustDeleted(groupID, roleID, request);
    }

    /**
     * IsGroupTrustExists
     * @param request
     * @param groupID
     * @param iTrustgroupID
     * @param roleID
     * @param countryID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/isexist/rest/{rest}/groupid/{groupid}/itrustgroupid/{itrustgroupid}/userroleid/{userroleid}/countryid/{countryid}")
    @RESTDoc(methodName = "IsGroupTrustExists", desc = "Check's Whether a GroupTrust Exist's or Not", req_Params = {"GroupID;UUID", "ITrustGroupID;UUID", "UserRoleID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_IsExist(@Context HttpServletRequest request, @PathParam("groupid") String groupID, @PathParam("itrustgroupid") String iTrustgroupID, @PathParam("userroleid") String roleID, @PathParam("countryid") int countryID) {
        return isGroupTrustExists(groupID, iTrustgroupID, roleID, countryID, request);
    }

    /**
     * IsGroupTrustExists
     * @param request
     * @param groupID
     * @param iTrustgroupID
     * @param roleID
     * @param countryID
     * @return
     */
    @GET
    @Path("/isexist/json/{json}/groupid/{groupid}/itrustgroupid/{itrustgroupid}/userroleid/{userroleid}/countryid/{countryid}")
    @Produces("application/json")
    public TingcoUsers getJson_IsExist(@Context HttpServletRequest request, @PathParam("groupid") String groupID, @PathParam("itrustgroupid") String iTrustgroupID, @PathParam("userroleid") String roleID, @PathParam("countryid") int countryID) {
        return isGroupTrustExists(groupID, iTrustgroupID, roleID, countryID, request);
    }

    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addgrouptrust/rest/{rest}")
    public TingcoGroups postXml_AddGroupTrust(String content) {
        return addGroupTrust(content);
    }

    @GET
    @Produces("application/xml")
    @Path("/deletegrouptrust/rest/{rest}/groupid/{groupid}/itrustgroupid/{itrustgroupid}/userroleid/{userroleid}")
    @RESTDoc(methodName = "DeleteGroupTrust", desc = "Delete GroupTrust", req_Params = {"GroupID;UUID", "ITrustGroupID;UUID", "UserRoleID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_DeleteGroupTrust(@Context HttpServletRequest request, @PathParam("groupid") String groupID, @PathParam("itrustgroupid") String iTrustgroupID, @PathParam("userroleid") String roleID, @PathParam("countryid") int countryID) {
        return deleteGroupTrust(groupID, iTrustgroupID, roleID);
    }

    /**
     * GetTrustedGroupsDetails
     * @param countryid
     * @param groupid
     * @param selectedgroupid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/gettrustedgroupsdetails/rest/{rest}/selectedgroupid/{selectedgroupid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetTrustedGroupsDetails", desc = "Used to Get TrustedOrganizations List", req_Params = {"SelectedGroupId;string", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_getTrustedGroupsDetails(@PathParam("selectedgroupid") String selectedGroupId, @PathParam("countryid") int countryId) {
        return getTrustedGroupsDetails(selectedGroupId, countryId);
    }

    /**
     * GetTrustedGroupsDetails
     * @param countryid
     * @param groupid
     * @param selectedgroupid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/gettrustedgroupsdetails/json/{json}/selectedgroupid/{selectedgroupid}/countryid/{countryid}")
    public TingcoGroups getJson_getTrustedGroupsDetails(@PathParam("selectedgroupid") String selectedGroupId, @PathParam("countryid") int countryId) {
        return getTrustedGroupsDetails(selectedGroupId, countryId);
    }

    /**
     * Sub-resource locator method for  id
     * @return
     */
    @Path("id")
    public GroupTrustResource getGroupTrustResource() {
        return new GroupTrustResource();
    }

    private TingcoGroups deleteGroupTrust(String groupID, String iTrustgroupID, String roleID) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoGroups tingcoGroups = null;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXML.buildTingcoGroupTemplate();
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
                    List<GroupTrusts> groupTrustsList = groupDAO.isGroupTrustExists(session, groupID, iTrustgroupID, roleID);
                    if (!groupTrustsList.isEmpty()) {
                        try {
                            session.beginTransaction();
                            session.delete(groupTrustsList.get(0));
                            session.getTransaction().commit();
                        } catch (Exception e) {
                            if (session.getTransaction().wasCommitted()) {
                                session.getTransaction().rollback();
                            }
                            TCMUtil.exceptionLog(getClass().getName(), e);
                            tingcoGroups.getMsgStatus().setResponseCode(-1);
                            tingcoGroups.getMsgStatus().setResponseText("Error Occured While Deleting GroupTrusts");
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
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error Occured While Adding GroupTrusts");
            return tingcoGroups;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoGroups;
    }

    private TingcoGroups addGroupTrust(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoGroups tingcoGroups = null;
        Session session = null;
        try {
            tingcoGroups = tingcoGroupXML.buildTingcoGroupTemplate();
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
                    session = HibernateUtil.getSessionFactory().openSession();
                    se.info24.groupsjaxb.TingcoGroups tingcoGroupsJaxb = (se.info24.groupsjaxb.TingcoGroups) JAXBManager.getInstance().unMarshall(content, se.info24.groupsjaxb.TingcoGroups.class);
                    List<se.info24.groupsjaxb.Group> groupList = tingcoGroupsJaxb.getGroups().getGroup();
                    for (se.info24.groupsjaxb.Group grpJaxb : groupList) {
                        if (grpJaxb.getGroupID() != null && grpJaxb.getUserRoleID() != null) {
                            List<se.info24.groupsjaxb.ITrustGroups> itrustList = grpJaxb.getITrustGroups();
                            if (!itrustList.isEmpty()) {
                                for (se.info24.groupsjaxb.ITrustGroups itrust : itrustList) {
                                    for (String iTrustID : itrust.getITrustGroupID()) {
                                        if (groupDAO.isGroupTrustExists(session, grpJaxb.getGroupID(), grpJaxb.getUserRoleID(), iTrustID).isEmpty()) {
                                            List<GroupTrusts> gtList = groupDAO.getGroupTrustsByGroupIdAndITrustID(session, grpJaxb.getGroupID(), iTrustID);
                                            if (gtList.isEmpty()) {
                                                GregorianCalendar gc = new GregorianCalendar();
                                                GroupTrusts gt = new GroupTrusts();
                                                gt.setId(new GroupTrustsId(grpJaxb.getGroupID(), iTrustID, grpJaxb.getUserRoleID()));
                                                gt.setIsCustomer(0);
                                                gt.setIsInternal(1);
                                                gt.setIsSupplier(0);
                                                gt.setLastUpdatedByUserId(sessions2.getUserId());
                                                gt.setActiveFromDate(gc.getTime());
                                                gt.setCreatedDate(gc.getTime());
                                                gt.setUpdatedDate(gc.getTime());
                                                gc.add(Calendar.YEAR, 15);
                                                gt.setActiveToDate(gc.getTime());
                                                groupDAO.createGroupTrust(gt, session);
                                            } else {
                                                List<Query> queryList = new ArrayList<Query>();
                                                for (GroupTrusts gt : gtList) {
                                                    Query query = session.createQuery("Update GroupTrusts set id.userRoleId='" + grpJaxb.getUserRoleID() + "', updatedDate =:updatedDate where " +
                                                            "id.groupId='" + gt.getId().getGroupId() + "' and id.itrustGroupId='" + gt.getId().getItrustGroupId() + "'");
                                                    query.setDate("updatedDate", new Date());
                                                    queryList.add(query);
                                                }
                                                try {
                                                    session.beginTransaction();
                                                    for (Query query : queryList) {
                                                        query.executeUpdate();
                                                    }
                                                    session.getTransaction().commit();
                                                } catch (Exception e) {
                                                    if (session.getTransaction().wasCommitted()) {
                                                        session.getTransaction().rollback();
                                                    }
                                                    TCMUtil.exceptionLog(getClass().getName(), e);
                                                    tingcoGroups.getMsgStatus().setResponseCode(-1);
                                                    tingcoGroups.getMsgStatus().setResponseText("Error Occured While Updating Userroleid");
                                                    return tingcoGroups;
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                tingcoGroups.getMsgStatus().setResponseCode(-1);
                                tingcoGroups.getMsgStatus().setResponseText("ItrustID cannot be empty");
                                return tingcoGroups;
                            }
                        } else {
                            tingcoGroups.getMsgStatus().setResponseCode(-1);
                            tingcoGroups.getMsgStatus().setResponseText("GroupID or UserRoleID cannot be empty");
                            return tingcoGroups;
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
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error Occured While Adding GroupTrusts");
            return tingcoGroups;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoGroups;
    }

    private TingcoGroups getTrustedGroupsDetails(String selectedGroupId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        TingcoGroups tingcoGroups = null;
        try {
            tingcoGroups = tingcoGroupXML.buildTingcoGroupTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.GROUPS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(selectedGroupId, session);
                    if (!groupTrustsList.isEmpty()) {
                        HashMap<String, GroupTrusts> hm = groupDAO.getTrustedGroups(groupTrustsList, session);
                        Set<String> groupID = hm.keySet();
                        List<GroupTranslations> gt = new ArrayList<GroupTranslations>();
                        if (!groupID.isEmpty()) {
                            List<List<String>> spList = TCMUtil.splitStringList(new ArrayList<String>(groupID), 2000);
                            for (List<String> list : spList) {
                                List<GroupTranslations> gtTemp = groupDAO.getGroupTranslationsByGroupId(new HashSet<String>(list), countryId, 200, session);
                                gt.addAll(gtTemp);
                                if (gt.size() > 200) {
                                    gt = gt.subList(0, 200);
                                    break;
                                }
                            }
                        }
                        tingcoGroups = tingcoGroupXML.buildGetTrustedGroupsList(tingcoGroups, gt, hm, countryId, session);
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

    private TingcoUsers myTrustedOrganisations(String groupID, int countryID, HttpServletRequest request) {
        long requestedTime = System.currentTimeMillis();
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserManager manager = new UserManager();
        TingcoUsers user = null;
        try {
            user = manager.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                user = manager.getTrustedOrganisations(groupID, countryID, user, session);
            } else {
                TCMUtil tcm = new TCMUtil();
                user = tcm.sessionExpired();
            }
        } catch (DatatypeConfigurationException e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            session.close();
            delayLog(requestedTime);
        }
        return user;
    }

    private TingcoUsers myTrustingOrganistaions(String groupID, int countryID, HttpServletRequest request) {
        long requestedTime = System.currentTimeMillis();
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserManager manager = new UserManager();
        TingcoUsers user = null;
        try {
            user = manager.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                user = manager.getTrustingOrganisations(groupID, countryID, user, session);
            } else {
                TCMUtil tcm = new TCMUtil();
                user = tcm.sessionExpired();
            }
        } catch (DatatypeConfigurationException e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            session.close();
            delayLog(requestedTime);
        }
        return user;
    }

    private TingcoGroups getGroupTrustInfo(String groupId, int countryId, String domainId, String groupName) {
        long requestedTime = System.currentTimeMillis();
        TingcoGroups tingcoGroups = null;
        Session session = null;
        tingcoGroupXML = new TingcoGroupXML();
        try {
            tingcoGroups = tingcoGroupXML.buildTingcoGroupTemplate();
            if (request.getAttribute("USERSESSION") != null) {

                // if (request.getAttribute("USERSESSION") != null) {
                if (groupId == null) {
                    tingcoGroups.getMsgStatus().setResponseCode(-1);
                    tingcoGroups.getMsgStatus().setResponseText("GroupID  should not be empty");
                    return tingcoGroups;
                }
                if (countryId <= 0) {
                    tingcoGroups.getMsgStatus().setResponseCode(-1);
                    tingcoGroups.getMsgStatus().setResponseText("CountryID  should not be empty");
                    return tingcoGroups;
                }
                if (domainId == null) {
                    tingcoGroups.getMsgStatus().setResponseCode(-1);
                    tingcoGroups.getMsgStatus().setResponseText("DomainID  should not be empty");
                    return tingcoGroups;
                }
                if (groupName.equals("")) {
                    groupName = null;
                } else {
                    groupName = groupName.split("/")[2];
                }
                session = HibernateUtil.getSessionFactory().openSession();
                // The groupId parameter is here ITrustGroupID value, need to check concern column.
                List<GroupTrusts> groupTrustList = groupDAO.getGroupTrustByGroupID(groupId, session);
                if (!groupTrustList.isEmpty()) {
                    Set<String> groupidSet = groupDAO.getGroupIdsList(groupId, groupTrustList);
                    List<GroupTranslations> groupTransList = new ArrayList<GroupTranslations>();
                    if (groupName != null) {
                        List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupidSet), 2000);
                        for (List<String> list : listsplit) {
                            List<GroupTranslations> groupTransListtemp = groupDAO.getGroupTransByGroupIdandSearchString(new HashSet<String>(list), groupName, countryId, session);
                            groupTransList.addAll(groupTransListtemp);
                            if (groupTransList.size() > 200) {
                                groupTransList = groupTransList.subList(0, 200);
                                break;
                            }
                        }


                    } else {

                        List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupidSet), 2000);
                        for (List<String> list : listsplit) {
                            List<GroupTranslations> groupTransListtemp = groupDAO.getGroupTranslationsByGroupId(new HashSet<String>(list), countryId, 1000, session);
                            groupTransList.addAll(groupTransListtemp);
                            if (groupTransList.size() > 1000) {
                                groupTransList = groupTransList.subList(0, 1000);
                                break;
                            }

                        }

//                         groupTransList = groupDAO.getGroupTranslationsByGroupId(groupidSet,countryId, 1000, session);
                    }
                    if (!groupTransList.isEmpty()) {
//                tingcoGroups = tingcoGroupXML.buildGroupTrust(tingcoGroups, groupDAO, groupTrustList, groupDAO.loadAllGroups(session), session, groupId, countryId, domainId, groupName);
                        tingcoGroups = tingcoGroupXML.buildGroupTrusts(tingcoGroups, groupTransList);
                    } else {
                        tingcoGroups.getMsgStatus().setResponseCode(-1);
                        tingcoGroups.getMsgStatus().setResponseText("No data found");
                        return tingcoGroups;
                    }
                } else {
                    tingcoGroups.getMsgStatus().setResponseCode(-1);
                    tingcoGroups.getMsgStatus().setResponseText("No Group found with the given GroupID");
                    return tingcoGroups;
                }
            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoGroups;
    }

    private TingcoUsers getITrustGroupTruts(String groupId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        TingcoUsers tingcoUsers = null;
        Session session = null;
        TingcoUserXML tingcoUserXML = new TingcoUserXML();
        try {
            tingcoUsers = tingcoUserXML.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId == null) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("GroupID should not be empty");
                    return tingcoUsers;
                } else if (countryId <= 0) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("CountryID should not be empty");
                    return tingcoUsers;
                }

                session = HibernateUtil.getSessionFactory().openSession();
                // List<GroupTrusts> groupTrustList = groupDAO.getGroupTrustByGroupID(groupId, session);
                List<GroupTrusts> groupTrustList = groupDAO.getITrustOrganizations(groupId, session);  // Check in GroupID column.
                if (groupTrustList != null) {
                    tingcoUsers = tingcoUserXML.buildGroupTrustRolesPermissions(tingcoUsers, groupTrustList, groupDAO.loadAllGroups(session), countryId, session);
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-2);
                    tingcoUsers.getMsgStatus().setResponseText("Invalid GroupID");
                    return tingcoUsers;
                }

            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured while Getting Data");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUsers;
    }

    private TingcoUsers getOrganizationsRnPNamesByGroupID(String groupId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        TingcoUsers tingcoUsers = null;
        Session session = null;
        TingcoUserXML tingcoUserXML = new TingcoUserXML();
        PermissionDAO permissinDAO = new PermissionDAO();
        try {
            tingcoUsers = tingcoUserXML.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId == null) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("GroupID should not be empty");
                    return tingcoUsers;
                } else if (countryId <= 0) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("CountryID  should not be empty");
                    return tingcoUsers;
                }

                session = HibernateUtil.getSessionFactory().openSession();
                List<UserRoleObjectPermissions2> uropList = permissinDAO.getUserRoleObjectPermissionsByGroupID(groupId, session);
                if (uropList != null) {
                    tingcoUserXML.buildOrganizationPermissionsRoles(tingcoUsers, uropList, countryId);
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUsers;
    }

    private TingcoAPI createGroupTrust(String groupID, String itrustGroupID, String userRoleID, String lastUpdatedByUserID, String isInternal, String isCustomer, String isSupplier) {
        long requestedTime = System.currentTimeMillis();
        TingcoAPI tingcoAPI = null;
        TingcoAPIXML tingcoAPIXML = new TingcoAPIXML();
        try {
            tingcoAPI = tingcoAPIXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 userSessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                if (groupID == null) {
                    tingcoAPI.getMsgStatus().setResponseCode(-1);
                    tingcoAPI.getMsgStatus().setResponseText("GroupID Should not be empty");
                    return tingcoAPI;
                } else if (itrustGroupID == null) {
                    tingcoAPI.getMsgStatus().setResponseCode(-1);
                    tingcoAPI.getMsgStatus().setResponseText("ITrustGroupID Should not be empty");
                    return tingcoAPI;
                } else if (userRoleID == null) {
                    tingcoAPI.getMsgStatus().setResponseCode(-1);
                    tingcoAPI.getMsgStatus().setResponseText("UserRoleID Should not be empty");
                    return tingcoAPI;
                }

                if (lastUpdatedByUserID.equals("")) {
                    lastUpdatedByUserID = userSessions2.getUserId();
                } else {
                    lastUpdatedByUserID = lastUpdatedByUserID.split("/")[2];
                }

                if (isInternal.equals("")) {
                    isInternal = null;
                } else {
                    isInternal = isInternal.split("/")[2];
                }

                if (isCustomer.equals("")) {
                    isCustomer = null;
                } else {
                    isCustomer = isCustomer.split("/")[2];
                }
                if (isSupplier.equals("")) {
                    isSupplier = null;
                } else {
                    isSupplier = isSupplier.split("/")[2];
                }


                Session session = HibernateUtil.getSessionFactory().openSession();
                GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                GroupTrusts groupTrusts = new GroupTrusts();
                groupTrusts.setId(new GroupTrustsId(groupID, itrustGroupID, userRoleID));
                groupTrusts.setLastUpdatedByUserId(lastUpdatedByUserID);
                if (isInternal != null) {
                    groupTrusts.setIsInternal(new Integer(isInternal));
                }
                if (isCustomer != null) {
                    groupTrusts.setIsCustomer(new Integer(isCustomer));
                }
                if (isSupplier != null) {
                    groupTrusts.setIsSupplier(new Integer(isSupplier));
                }
                groupTrusts.setActiveFromDate(gc.getTime());
                groupTrusts.setCreatedDate(gc.getTime());
                groupTrusts.setUpdatedDate(gc.getTime());
                gc.add(Calendar.YEAR, 5);
                groupTrusts.setActiveToDate(gc.getTime());
                boolean flag = groupDAO.createGroupTrust(groupTrusts, session);
                session.close();
                if (flag) {
                    return tingcoAPI;
                } else {
                    tingcoAPI.getMsgStatus().setResponseCode(-1);
                    tingcoAPI.getMsgStatus().setResponseText("Error Occured While Saving into GroupTrust");
                }
            } else {
                tingcoAPI.getMsgStatus().setResponseCode(-3);
                tingcoAPI.getMsgStatus().setResponseText("User Session is Not Valid");
            }
        } catch (NumberFormatException nfe) {
            tingcoAPI.getMsgStatus().setResponseCode(-1);
            tingcoAPI.getMsgStatus().setResponseText("Invalid Data Found");
        } catch (Exception e) {
            tingcoAPI.getMsgStatus().setResponseCode(-1);
            tingcoAPI.getMsgStatus().setResponseText("Error occured");
        } finally {
            delayLog(requestedTime);
        }
        return tingcoAPI;
    }

    private TingcoGroups groupTrustDeleted(String iTrustGroupID, String roleID, HttpServletRequest request) {
        long requestedTime = System.currentTimeMillis();
        Session session = HibernateUtil.getSessionFactory().openSession();
        TingcoGroups group = null;
        boolean del = false;
        try {
            if (request.getAttribute("USERSESSION") != null) {
                del = groupDAO.deleteGroupTrust(iTrustGroupID, roleID, session);
                group = groupDAO.buildGroupTemplate();
                if (!del) {
                    group.getMsgStatus().setResponseCode(-1);
                    group.getMsgStatus().setResponseText("Error");
                }
            } else {
                TCMUtil tcm = new TCMUtil();
                group = tcm.group_SessionExpired();
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            group.getMsgStatus().setResponseCode(-1);
            group.getMsgStatus().setResponseText("Error occured");
        } finally {
            session.close();
            delayLog(requestedTime);
        }
        return group;
    }

    private TingcoUsers isGroupTrustExists(String groupID, String iTrustgroupID, String roleID, int countryID, HttpServletRequest request) {
        long requestedTime = System.currentTimeMillis();
        Session session = HibernateUtil.getSessionFactory().openSession();
        TCMUtil tcm = new TCMUtil();
        TingcoUsers user = null;
        try {
            if (request.getAttribute("USERSESSION") != null) {
                user = tcm.buildUserTemplate();
                user = groupDAO.isGroupTrustExists(groupID, iTrustgroupID, roleID, countryID, user, session);
                if (user == null) {
                    user = tcm.buildUserTemplate();
                    user.getMsgStatus().setResponseCode(-1);
                    user.getMsgStatus().setResponseText("Error");
                }
            } else {
                user = tcm.sessionExpired();
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            user.getMsgStatus().setResponseCode(-1);
            user.getMsgStatus().setResponseText("Error occured");
        } finally {
            session.close();
            delayLog(requestedTime);
        }
        return user;
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

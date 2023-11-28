/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.supportcase;

import javax.ws.rs.core.Response;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.device.DeviceDAO;
import se.info24.group.GroupDAO;
import se.info24.jaxbUtil.TingcoSupportCaseXML;
import se.info24.network.NetworkDAO;
import se.info24.pojo.CostCenters;
import se.info24.pojo.Device;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupTrusts;
import se.info24.pojo.Groups;
import se.info24.pojo.ObjectCostCenters;
import se.info24.pojo.ObjectCostCentersId;
import se.info24.pojo.SupportCaseDeviceLinks;
import se.info24.pojo.SupportCaseDeviceLinksId;
import se.info24.pojo.SupportCaseImpacts;
import se.info24.pojo.SupportCasePriorities;
import se.info24.pojo.SupportCaseResolutionTemplates;
import se.info24.pojo.SupportCaseStatuses;
import se.info24.pojo.SupportCaseSubjectTemplates;
import se.info24.pojo.SupportCaseTypes;
import se.info24.pojo.SupportCases;
import se.info24.pojo.SupportImpactTranslations;
import se.info24.pojo.SupportOrgVisibleToGroups;
import se.info24.pojo.SupportOrganizationUsers;
import se.info24.pojo.SupportOrgVisibleToGroupsId;
import se.info24.pojo.SupportOrganizationUsersId;
import se.info24.pojo.SupportOrganizations;
import se.info24.pojo.SupportPriorityTranslations;
import se.info24.pojo.SupportStatusTranslations;
import se.info24.pojo.SupportTypeTranslations;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.UserTimeZones2;
import se.info24.pojo.Users2;
import se.info24.restUtil.RestUtilDAO;
import se.info24.supportcasejaxb.GroupID;
import se.info24.supportcasejaxb.SupportCase;
import se.info24.supportcasejaxb.SupportOrganizationsVisibleToGroups;
import se.info24.supportcasejaxb.TingcoSupportCase;
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
@Path("/supportcases")
public class SupportCasesResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoSupportCaseXML tingcoSupportXML = new TingcoSupportCaseXML();
    SupportCasesDAO supportCasesDAO = new SupportCasesDAO();
    DeviceDAO deviceDAO = new DeviceDAO();
    GroupDAO groupDAO = new GroupDAO();
    UserDAO userDAO = new UserDAO();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    GregorianCalendar currentDateTime = new GregorianCalendar(TimeZone.getTimeZone("GMT"));

    /** Creates a new instance of SupportCasesResource */
    public SupportCasesResource() {
    }

    /**
     * Retrieves representation of an instance of se.info24.supportcase.SupportCasesResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * POST method for creating an instance of SupportCasesResource
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

    @GET
    @Produces("application/xml")
    @Path("/getsupportcasesubjecttemplatedetails/rest/{rest}/supportcasesubjectid/{supportcasesubjectid}")
    @RESTDoc(methodName = "GetSupportCaseSubjectTemplateDetails", desc = "Used to Get SupportCaseSubjectTemplateDetails Details", req_Params = {"groupId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getXml_getSupportCaseSubjectTemplateDetails(@PathParam("supportcasesubjectid") String supportCaseSubjectId) {
        return getSupportCaseSubjectTemplateDetails(supportCaseSubjectId);
    }

    @GET
    @Produces("application/json")
    @Path("/getsupportcasesubjecttemplatedetails/json/{json}/supportcasesubjectid/{supportcasesubjectid}")
    public TingcoSupportCase getJson_getSupportCaseSubjectTemplateDetails(@PathParam("supportcasesubjectid") String supportCaseSubjectId) {
        return getSupportCaseSubjectTemplateDetails(supportCaseSubjectId);
    }

    @GET
    @Produces("application/xml")
    @Path("/getsupportorganizations/rest/{rest}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetSupportOrganizations", desc = "Used to Get SupportOrganizations Details", req_Params = {"groupId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getXml_getSupportOrganizations(@PathParam("groupid") String groupId, @PathParam("searchstring") String searchstring) {
        return getSupportOrganizations(groupId, searchstring);
    }

    @GET
    @Produces("application/json")
    @Path("/getsupportorganizations/json/{json}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoSupportCase getJson_getSupportOrganizations(@PathParam("groupid") String groupId, @PathParam("searchstring") String searchstring) {
        return getSupportOrganizations(groupId, searchstring);
    }

    @GET
    @Produces("application/xml")
    @Path("/getsupportorganizationsbygroupid/rest/{rest}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetSupportOrganizations", desc = "Used to Get SupportOrganizations Details", req_Params = {"groupId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getXml_getSupportOrganizationsByGroupId(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("searchstring") String searchString) {
        return getSupportOrganizationsByGroupId(groupId, countryId, searchString);
    }

    @GET
    @Produces("application/json")
    @Path("/getsupportorganizationsbygroupid/json/{json}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoSupportCase getJson_getSupportOrganizationsByGroupId(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("searchstring") String searchString) {
        return getSupportOrganizationsByGroupId(groupId, countryId, searchString);
    }

    @GET
    @Produces("application/xml")
    @Path("/getsubjecttemplates/rest/{rest}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetSubjectTemplates", desc = "Used to Get SubjectTemplates Details", req_Params = {"groupId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getXml_getContainerDashBoardDetails(@PathParam("groupid") String groupId, @PathParam("countryid") String countryId, @PathParam("searchstring") String searchString) {
        return getSubjectTemplates(groupId, countryId, searchString);
    }

    @GET
    @Produces("application/json")
    @Path("/getsubjecttemplates/json/{json}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoSupportCase getJson_getContainerDashBoardDetails(@PathParam("groupid") String groupId, @PathParam("countryid") String countryId, @PathParam("searchstring") String searchString) {
        return getSubjectTemplates(groupId, countryId, searchString);
    }

    @GET
    @Produces("application/xml")
    @Path("/getsupportcasetypes/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetSupportCaseTypes", desc = "Used to Get SupportCaseTypes Details", req_Params = {"groupId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getXml_getSupportCaseTypes(@PathParam("countryid") String countryId) {
        return getSupportCaseTypes(countryId);
    }

    @GET
    @Produces("application/json")
    @Path("/getsupportcasetypes/json/{json}/countryid/{countryid}")
    public TingcoSupportCase getJson_getSupportCaseTypes(@PathParam("countryid") String countryId) {
        return getSupportCaseTypes(countryId);
    }

    @GET
    @Produces("application/xml")
    @Path("/getsupportcaseimpacts/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetSupportCaseImpacts", desc = "Used to Get SupportCaseImpacts Details", req_Params = {"groupId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getXml_getSupportCaseImpacts(@PathParam("countryid") String countryId) {
        return getSupportCaseImpacts(countryId);
    }

    @GET
    @Produces("application/json")
    @Path("/getsupportcaseimpacts/json/{json}/countryid/{countryid}")
    public TingcoSupportCase getJson_getSupportCaseImpacts(@PathParam("countryid") String countryId) {
        return getSupportCaseImpacts(countryId);
    }

    @GET
    @Produces("application/xml")
    @Path("/getsupportcasepriorities/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetSupportCasePriorities", desc = "Used to Get SupportCasePriorities Details", req_Params = {"groupId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getXml_getSupportCasePriorities(@PathParam("countryid") String countryId) {
        return getSupportCasePriorities(countryId);
    }

    @GET
    @Produces("application/json")
    @Path("/getsupportcasepriorities/json/{json}/countryid/{countryid}")
    public TingcoSupportCase getJson_getSupportCasePriorities(@PathParam("countryid") String countryId) {
        return getSupportCasePriorities(countryId);
    }

    @GET
    @Produces("application/xml")
    @Path("/getsupportcaseresolutiontemplates/rest/{rest}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetSupportCaseResolutionTemplates", desc = "Used to Get SupportCaseResolutionTemplates Details", req_Params = {"groupId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getXml_getSupportCaseResolutionTemplates(@PathParam("countryid") String countryId, @PathParam("groupid") String groupId, @PathParam("searchstring") String searchstring) {
        return getSupportCaseResolutionTemplates(groupId, countryId, searchstring);
    }

    @GET
    @Produces("application/json")
    @Path("/getsupportcaseresolutiontemplates/json/{json}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoSupportCase getJson_getSupportCaseResolutionTemplates(@PathParam("countryid") String countryId, @PathParam("groupid") String groupId, @PathParam("searchstring") String searchstring) {
        return getSupportCaseResolutionTemplates(groupId, countryId, searchstring);
    }

    /*
     * GetSupportCaseDetails
     * supportCaseId
     * countryId
     */
    @GET
    @Produces("application/xml")
    @Path("/getsupportcasedetails/rest/{rest}/supportcaseid/{supportcaseid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetSupportCaseDetails", desc = "Used to Get SupportCase Details", req_Params = {"supportCaseId;string", "countryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getXml_getSupportCaseDetails(@PathParam("supportcaseid") String supportCaseId, @PathParam("countryid") int countryId) {
        return getSupportCaseDetails(supportCaseId, countryId);
    }

    /*
     * GetSupportCaseDetails
     * supportCaseId
     * countryId
     */
    @GET
    @Produces("application/json")
    @Path("/getsupportcasedetails/json/{json}/supportcaseid/{supportcaseid}/countryid/{countryid}")
    public TingcoSupportCase getJson_getSupportCaseDetails(@PathParam("supportcaseid") String supportCaseId, @PathParam("countryid") int countryId) {
        return getSupportCaseDetails(supportCaseId, countryId);
    }

    /*
     * UpdateSupportCase
     * supportCaseId
     */
    @GET
    @Produces("application/xml")
    @Path("/closesupportcase/rest/{rest}/supportcaseid/{supportcaseid}")
    @RESTDoc(methodName = "UpdateSupportCase", desc = "Used to Update SupportCases", req_Params = {"supportCaseId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getXml_updateSupportCase(@PathParam("supportcaseid") String supportCaseId) {
        return updateSupportCase(supportCaseId);
    }

    /*
     * UpdateSupportCase
     * supportCaseId
     */
    @GET
    @Produces("application/json")
    @Path("/closesupportcase/json/{json}/supportcaseid/{supportcaseid}")
    public TingcoSupportCase getJson_updateSupportCase(@PathParam("supportcaseid") String supportCaseId) {
        return updateSupportCase(supportCaseId);
    }

    /**
     * GetSupportCaseStatuses
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getsupportcasestatuses/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetSupportCaseStatuses", desc = "Used to Get SupportCaseStatuses Details", req_Params = {"groupId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getXml_getSupportCaseStatuses(@PathParam("countryid") String countryId) {
        return getSupportCaseStatuses(countryId);
    }

    /**
     * GetSupportCaseStatuses
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getsupportcasestatuses/json/{json}/countryid/{countryid}")
    @RESTDoc(methodName = "GetSupportCaseStatuses", desc = "Used to Get SupportCaseStatuses Details", req_Params = {"groupId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getJson_getSupportCaseStatuses(@PathParam("countryid") String countryId) {
        return getSupportCaseStatuses(countryId);
    }

    /**
     * GetSupportCaseStatusesTim
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getsupportcasestatusestim/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetSupportCaseStatusesTim", desc = "Used to Get SupportCaseStatuses Details", req_Params = {"groupId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getXml_getSupportCaseStatusesTim(@PathParam("countryid") String countryId) {
        return getSupportCaseStatusesTim(countryId);
    }

    /**
     * GetSupportCaseStatusesTim
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getsupportcasestatusestim/json/{json}/countryid/{countryid}")
    @RESTDoc(methodName = "GetSupportCaseStatusesTim", desc = "Used to Get SupportCaseStatuses Details", req_Params = {"groupId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getJson_getSupportCaseStatusesTim(@PathParam("countryid") String countryId) {
        return getSupportCaseStatusesTim(countryId);
    }

    /*
     * DeleteSupportCase
     * SupportCaseId
     */
    @GET
    @Produces("application/xml")
    @Path("/deletesupportcase/rest/{rest}/supportcaseid/{supportcaseid}")
    @RESTDoc(methodName = "DeleteSupportCase", desc = "Used to Delete SupportCase", req_Params = {"SupportId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getXml_deleteSupportCases(@PathParam("supportcaseid") String supportCaseId) {
        return deleteSupportCases(supportCaseId);
    }

    /*
     * DeleteSupportCase
     * SupportCaseId
     */
    @GET
    @Produces("application/json")
    @Path("/deletesupportcase/json/{json}/supportcaseid/{supportcaseid}")
    public TingcoSupportCase getJson_deleteSupportCases(@PathParam("supportcaseid") String supportCaseId) {
        return deleteSupportCases(supportCaseId);
    }

    /*
     * DeleteSupportOrganizationVisibleToGroups
     * SupportOrganizationId
     * LoggedInGroupId
     * SelectedGroupId
     */
    @GET
    @Produces("application/xml")
    @Path("/deletesupportorganizationsvisibletogroups/rest/{rest}/supportorganizationid/{supportorganizationid}/groupid/{groupid}/selectedgroupid/{selectedgroupid}")
    @RESTDoc(methodName = "DeleteSupportOrganizationVisibleToGroups", desc = "Used to Delete SupportOrganizationVisibleToGroups", req_Params = {"SupportOrganizationId;string", "LoggedInGroupId;string", "SelectedGroupId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getXml_deleteSupportOrganizationVisibleToGroups(@PathParam("supportorganizationid") String supportOrganizationId, @PathParam("groupid") String groupId, @PathParam("selectedgroupid") String selectedGroupId) {
        return deleteSupportOrganizationVisibleToGroups(supportOrganizationId, groupId, selectedGroupId);
    }

    /*
     * DeleteSupportOrganizationVisibleToGroups
     * SupportOrganizationId
     * LoggedInGroupId
     * SelectedGroupId
     */
    @GET
    @Produces("application/json")
    @Path("/deletesupportorganizationsvisibletogroups/json/{json}/supportorganizationid/{supportorganizationid}/groupid/{groupid}/selectedgroupid/{selectedgroupid}")
    public TingcoSupportCase getJson_deleteSupportOrganizationVisibleToGroups(@PathParam("supportorganizationid") String supportOrganizationId, @PathParam("groupid") String groupId, @PathParam("selectedgroupid") String selectedGroupId) {
        return deleteSupportOrganizationVisibleToGroups(supportOrganizationId, groupId, selectedGroupId);
    }

    /*
     * DeleteSupportOrganizationUsers
     * SupportOrganizationId
     * LoggedInGroupId
     * UserId
     */
    @GET
    @Produces("application/xml")
    @Path("/deletesupportorganizationusers/rest/{rest}/supportorganizationid/{supportorganizationid}/groupid/{groupid}/userid/{userid}")
    @RESTDoc(methodName = "DeleteSupportOrganizationUsers", desc = "Used to Delete SupportOrganizationUsers", req_Params = {"SupportOrganizationId;string", "LoggedInGroupId;string", "UserId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getXml_deleteSupportOrganizationUsers(@PathParam("supportorganizationid") String supportOrganizationId, @PathParam("groupid") String groupId, @PathParam("userid") String userId) {
        return deleteSupportOrganizationUsers(supportOrganizationId, groupId, userId);
    }

    /*
     * DeleteSupportOrganizationUsers
     * SupportOrganizationId
     * LoggedInGroupId
     * UserId
     */
    @GET
    @Produces("application/json")
    @Path("/deletesupportorganizationusers/json/{json}/supportorganizationid/{supportorganizationid}/groupid/{groupid}/userid/{userid}")
    public TingcoSupportCase getJson_deleteSupportOrganizationUsers(@PathParam("supportorganizationid") String supportOrganizationId, @PathParam("groupid") String groupId, @PathParam("userid") String userId) {
        return deleteSupportOrganizationUsers(supportOrganizationId, groupId, userId);
    }

    /*
     * GetGroupsForSupportOrgVisibleGroups
     * SupportOrganizationId
     * LoggedInGroupId
     * countryId
     */
    @GET
    @Produces("application/xml")
    @Path("/getgroupsforsupportorgvisiblegroups/rest/{rest}/supportorganizationid/{supportorganizationid}/groupid/{groupid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetGroupsForSupportOrgVisibleGroups", desc = "Used to Get Groups For SupportOrgVisibleGroups", req_Params = {"SupportOrganizationId;string", "LoggedInGroupId;string", "countryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getXml_getGroupsForSupportOrgVisibleGroups(@PathParam("supportorganizationid") String supportOrganizationId, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getGroupsForSupportOrgVisibleGroups(supportOrganizationId, groupId, countryId);
    }

    /*
     * GetGroupsForSupportOrgVisibleGroups
     * SupportOrganizationId
     * LoggedInGroupId
     * countryId
     */
    @GET
    @Produces("application/json")
    @Path("/getgroupsforsupportorgvisiblegroups/json/{json}/supportorganizationid/{supportorganizationid}/groupid/{groupid}/countryid/{countryid}")
    public TingcoSupportCase getJson_getGroupsForSupportOrgVisibleGroups(@PathParam("supportorganizationid") String supportOrganizationId, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getGroupsForSupportOrgVisibleGroups(supportOrganizationId, groupId, countryId);
    }

    /**
     * UpdateSupportCaseDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/updatesupportcasedetails/rest/{rest}")
    @RESTDoc(methodName = "UpdateSupportCaseDetails", desc = "Used to Update SupportCaseDetails", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoSupportCase postXml_updateSupportCaseDetails(String content) {
        return updateSupportCaseDetails(content);
    }

    /**
     * UpdateSupportCaseDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/updatesupportcasedetails/json/{json}")
    public TingcoSupportCase postJson_updateSupportCaseDetails(String content) {
        return updateSupportCaseDetails(content);
    }

    /**
     * AddSupportCase
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addsupportcase/rest/{rest}")
    @RESTDoc(methodName = "AddSupportCase", desc = "Used to Add SupportCase", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoSupportCase postXml_AddSupportCase(String content) {
        return addSupportCase(content);
    }

    /**
     * AddSupportCase
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addsupportcase/json/{json}")
    public TingcoSupportCase postJson_AddSupportCase(String content) {
        return addSupportCase(content);
    }

    /**
     * AddSupportOrgVisibleToGroups
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addsupportorgvisibletogroups/rest/{rest}")
    @RESTDoc(methodName = "AddSupportOrgVisibleToGroups", desc = "Used to Add SupportOrgVisibleToGroups", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoSupportCase postXml_addSupportOrgVisibleToGroups(String content) {
        return addSupportOrgVisibleToGroups(content);
    }

    /**
     * AddSupportOrgVisibleToGroups
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addsupportorgvisibletogroups/json/{json}")
    public TingcoSupportCase postJson_addSupportOrgVisibleToGroups(String content) {
        return addSupportOrgVisibleToGroups(content);
    }

    /**
     * GetSupportCasesBySearchCriteria
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getsupportcasesbysearchcriteria/rest/{rest}")
    @RESTDoc(methodName = "GetSupportCasesBySearchCriteria", desc = "Used to Get SupportCasesBySearchCriteria", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoSupportCase postXml_getSupportCasesBySearchCriteria(String content) {
        return getSupportCasesBySearchCriteria(content);
    }

    /**
     * TIMSaveSupportCaseByID
     * @param supportCaseId
     * @param supportCaseStatusId
     * @param resolutionText
     * @param durationActual
     * @return TingcoSupportCase
     */
    @GET
    @Produces("application/xml")
    @Path("/timsavesupportcasebyid/rest/{rest}/supportcaseid/{supportcaseid}{supportcasestatusid:(/supportcasestatusid/[^/]+?)?}{resolutiontext:(/resolutiontext/[^/]+?)?}{durationactual:(/durationactual/[^/]+?)?}")
    @RESTDoc(methodName = "TIMSaveSupportCaseByID", desc = "Used to Delete SupportCase", req_Params = {"SupportId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getXml_TIMSaveSupportCaseByID(@PathParam("supportcaseid") String supportCaseId, @PathParam("supportcasestatusid") String supportCaseStatusId, @PathParam("resolutiontext") String resolutionText, @PathParam("durationactual") String durationActual) {
        return SaveSupportCaseByID(supportCaseId, supportCaseStatusId, resolutionText, durationActual);
    }

    /**
     * TIMSaveSupportCaseByID
     * @param supportCaseId
     * @param supportCaseStatusId
     * @param resolutionText
     * @param durationActual
     * @return TingcoSupportCase
     */
    @GET
    @Produces("application/json")
    @Path("/timsavesupportcasebyid/json/{json}/supportcaseid/{supportcaseid}{supportcasestatusid:(/supportcasestatusid/[^/]+?)?}{resolutiontext:(/resolutiontext/[^/]+?)?}{durationactual:(/durationactual/[^/]+?)?}")
    @RESTDoc(methodName = "TIMSaveSupportCaseByID", desc = "Used to Delete SupportCase", req_Params = {"SupportId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getJson_TIMSaveSupportCaseByID(@PathParam("supportcaseid") String supportCaseId, @PathParam("supportcasestatusid") String supportCaseStatusId, @PathParam("resolutiontext") String resolutionText, @PathParam("durationactual") String durationActual) {
        return SaveSupportCaseByID(supportCaseId, supportCaseStatusId, resolutionText, durationActual);
    }

    /**
     * GetSupportCasesBySearchCriteria
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getsupportcasesbysearchcriteria/json/{json}")
    public TingcoSupportCase postJson_getSupportCasesBySearchCriteria(String content) {
        return getSupportCasesBySearchCriteria(content);
    }

    /**
     * GetSupportOrganizationList
     * @param groupId
     * @param countryId
     * @param OrganizationName
     * @return TingcoSupportCase
     */
    @GET
    @Produces("application/xml")
    @Path("/getsupportorganizationlist/rest/{rest}/groupid/{groupid}/countryid/{countryid}{organizationname :(/organizationname/[^/]+?)?}")
    @RESTDoc(methodName = "GetSupportOrganizationList", desc = "Used to Get SupportOrganization List", req_Params = {"SupportId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getXml_GetSupportOrganizationList(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("organizationname") String OrganizationName) {
        return getSupportOrganizationList(groupId, countryId, OrganizationName);
    }

    /**
     * GetSupportOrganizationList
     * @param groupId
     * @param countryId
     * @param OrganizationName
     * @return TingcoSupportCase
     */
    @GET
    @Produces("application/json")
    @Path("/getsupportorganizationlist/json/{json}/groupid/{groupid}/countryid/{countryid}{organizationname :(/organizationname/[^/]+?)?}")
    @RESTDoc(methodName = "GetSupportOrganizationList", desc = "Used to Get SupportOrganization List", req_Params = {"SupportId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getJson_GetSupportOrganizationList(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("organizationname") String OrganizationName) {
        return getSupportOrganizationList(groupId, countryId, OrganizationName);
    }

    /**
     * AddSupportOrganization
     * @param groupId
     * @param organizationDescription
     * @param OrganizationName
     * @return TingcoSupportCase
     */
    @GET
    @Produces("application/xml")
    @Path("/addsupportorganization/rest/{rest}/groupid/{groupid}/organizationname/{organizationname}{organizationdescription :(/organizationdescription/[^/]+?)?}")
    @RESTDoc(methodName = "AddSupportOrganization", desc = "Used to Add SupportOrganization", req_Params = {"SupportId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getXml_AddSupportOrganization(@PathParam("groupid") String groupId, @PathParam("organizationdescription") String organizationDescription, @PathParam("organizationname") String OrganizationName) {
        return addSupportOrganization(groupId, organizationDescription, OrganizationName);
    }

    /**
     * AddSupportOrganization
     * @param groupId
     * @param organizationDescription
     * @param OrganizationName
     * @return TingcoSupportCase
     */
    @GET
    @Produces("application/json")
    @Path("/addsupportorganization/json/{json}/groupid/{groupid}/organizationname/{organizationname}{organizationdescription :(/organizationdescription/[^/]+?)?}")
    @RESTDoc(methodName = "AddSupportOrganization", desc = "Used to Add SupportOrganization", req_Params = {"SupportId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getJson_AddSupportOrganization(@PathParam("groupid") String groupId, @PathParam("organizationdescription") String organizationDescription, @PathParam("organizationname") String OrganizationName) {
        return addSupportOrganization(groupId, organizationDescription, OrganizationName);
    }

    /**
     *  DeleteSupportOrganization
     * @param groupId
     * @param supportOrganizationID
     * @return TingcoSupportCase
     */
    @GET
    @Produces("application/xml")
    @Path("/deletesupportorganization/rest/{rest}/groupid/{groupid}/supportorganizationid/{supportorganizationid}")
    @RESTDoc(methodName = "DeleteSupportOrganization", desc = "Used to Delete SupportOrganization", req_Params = {"SupportId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getXml_DeleteSupportOrganization(@PathParam("groupid") String groupId, @PathParam("supportorganizationid") String supportOrganizationID) {
        return deleteSupportOrganization(groupId, supportOrganizationID);
    }

    /**
     *  DeleteSupportOrganization
     * @param groupId
     * @param supportOrganizationID
     * @return TingcoSupportCase
     */
    @GET
    @Produces("application/json")
    @Path("/deletesupportorganization/json/{json}/groupid/{groupid}/supportorganizationid/{supportorganizationid}")
    @RESTDoc(methodName = "DeleteSupportOrganization", desc = "Used to Delete SupportOrganization", req_Params = {"SupportId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getjson_DeleteSupportOrganization(@PathParam("groupid") String groupId, @PathParam("supportorganizationid") String supportOrganizationID) {
        return deleteSupportOrganization(groupId, supportOrganizationID);
    }

    /**
     * GetAllSupportOrganizations
     * @param searchString
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getallsupportorganizations/rest/{rest}{searchstring :(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetAllSupportOrganizations", desc = "Used to Get All SupportOrganizations", req_Params = {"SupportId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getXml_GetAllSupportOrganizations(@PathParam("searchstring") String searchString) {
        return getAllSupportOrganizations(searchString);
    }

    /**
     * GetAllSupportOrganizations
     * @param searchString
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getallsupportorganizations/json/{json}{searchstring :(/searchstring/[^/]+?)?}")
    public TingcoSupportCase getJson_GetAllSupportOrganizations(@PathParam("searchstring") String searchString) {
        return getAllSupportOrganizations(searchString);
    }

    /**
     * AddSupportOrganizationUsers
     * @param userId
     * @param isManager
     * @param supportOrganizationId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/addsupportorganizationusers/rest/{rest}/userid/{userid}/ismanager/{ismanager}/supportorganizationid/{supportorganizationid}")
    @RESTDoc(methodName = "AddSupportOrganizationUsers", desc = "Used to Add SupportOrganizationUsers", req_Params = {"SupportId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getXml_AddSupportOrganizationUsers(@PathParam("userid") String userId, @PathParam("ismanager") int isManager, @PathParam("supportorganizationid") String supportOrganizationId) {
        return addSupportOrganizationUsers(userId, isManager, supportOrganizationId);
    }

    /**
     * AddSupportOrganizationUsers
     * @param userId
     * @param isManager
     * @param supportOrganizationId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/addsupportorganizationusers/json/{json}/userid/{userid}/ismanager/{ismanager}/supportorganizationid/{supportorganizationid}")
    public TingcoSupportCase getJson_AddSupportOrganizationUsers(@PathParam("userid") String userId, @PathParam("ismanager") int isManager, @PathParam("supportorganizationid") String supportOrganizationId) {
        return addSupportOrganizationUsers(userId, isManager, supportOrganizationId);
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public SupportCaseResource getSupportCaseResource() {
        return new SupportCaseResource();
    }

    private TingcoSupportCase addSupportOrganizationUsers(String userId, int isManager, String supportOrganizationId) {
        Session session = null;
        TingcoSupportCase tingcoSupport = null;
        boolean hasPermission = false;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoSupport = tingcoSupportXML.buildTingcoSupportCasesTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.SUPPORT, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();

                    Object object = supportCasesDAO.getSupportOrganizationUsersByUserAndSupportOrganization(userId, supportOrganizationId, session);

                    if (object != null) {
                        tingcoSupport.getMsgStatus().setResponseCode(-1);
                        tingcoSupport.getMsgStatus().setResponseText("Data Already Exists");
                        return tingcoSupport;
                    }
                    Object objectSOU = supportCasesDAO.getSupportOrganizationsById(supportOrganizationId, session);
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    if (objectSOU != null) {
                        SupportOrganizationUsers sou = new SupportOrganizationUsers();
                        sou.setId(new SupportOrganizationUsersId(supportOrganizationId, userId));
                        sou.setIsManager(isManager);
                        sou.setCreatedDate(gc.getTime());
                        sou.setUpdatedDate(gc.getTime());
                        sou.setLastUpdatedByUserId(sessions2.getUserId());

                        if (!supportCasesDAO.saveSupportOrganizationUsers(sou, session)) {
                            tingcoSupport.getMsgStatus().setResponseCode(-1);
                            tingcoSupport.getMsgStatus().setResponseText("Error Occurred While Adding");
                            return tingcoSupport;
                        }
                    } else {
                        tingcoSupport.getMsgStatus().setResponseCode(-1);
                        tingcoSupport.getMsgStatus().setResponseText("SupportOrganization Not Found");
                        return tingcoSupport;
                    }
                } else {
                    tingcoSupport.getMsgStatus().setResponseCode(-10);
                    tingcoSupport.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoSupport;
                }
            } else {
                tingcoSupport.getMsgStatus().setResponseCode(-3);
                tingcoSupport.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoSupport;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoSupport.getMsgStatus().setResponseCode(-1);
            tingcoSupport.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoSupport;
    }

    private TingcoSupportCase getAllSupportOrganizations(String searchString) {
        Session session = null;
        TingcoSupportCase tingcoSupport = null;
        boolean hasPermission = false;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoSupport = tingcoSupportXML.buildTingcoSupportCasesTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.SUPPORT, TCMUtil.READ);
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
                    List<SupportOrganizations> supportOrganizationses = new ArrayList<SupportOrganizations>();
                    if (searchString != null) {
                        if (TCMUtil.isValidUUID(searchString)) {
                            for (List<String> list : listsplit) {
                                List<SupportOrganizations> temp = supportCasesDAO.getSupportOrganizationsByIdAndTrustedGroupIDs(searchString, list, session);
                                supportOrganizationses.addAll(temp);
                            }
                        } else {
                            for (List<String> list : listsplit) {
                                List<SupportOrganizations> temp = supportCasesDAO.getSupportOrganizationsByOrgNamemanagedByGroupId(session, list, searchString);
                                supportOrganizationses.addAll(temp);
                            }
                        }
                    } else {
                        for (List<String> list : listsplit) {
                            List<SupportOrganizations> temp = supportCasesDAO.getSupportOrganizationsBymanagedByGroupId(session, list);
                            supportOrganizationses.addAll(temp);
                        }
                    }

                    /**
                     * Top 200
                     */
                    Set<String> supportIDs = new HashSet<String>();
                    for (SupportOrganizations so : supportOrganizationses) {
                        supportIDs.add(so.getSupportOrganizationId());
                    }
                    if (supportIDs.isEmpty()) {
                        tingcoSupport.getMsgStatus().setResponseCode(-1);
                        tingcoSupport.getMsgStatus().setResponseText("No Data Found");
                        return tingcoSupport;
                    }
                    List<SupportOrganizations> filteredSO = supportCasesDAO.getSupportOrganizationsByIds(session, new ArrayList<String>(supportIDs), 200);

                    tingcoSupport = tingcoSupportXML.buildGetAllSupportOrganizations(tingcoSupport, filteredSO);

                } else {
                    tingcoSupport.getMsgStatus().setResponseCode(-10);
                    tingcoSupport.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoSupport;
                }
            } else {
                tingcoSupport.getMsgStatus().setResponseCode(-3);
                tingcoSupport.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoSupport;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoSupport.getMsgStatus().setResponseCode(-1);
            tingcoSupport.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoSupport;
    }

    private TingcoSupportCase deleteSupportOrganization(String groupId, String supportOrganizationID) {
        Session session = null;
        TingcoSupportCase tingcoSupport = null;
        boolean hasPermission = false;
        long requestedTime = System.currentTimeMillis();
        Transaction tx = null;
        try {
            tingcoSupport = tingcoSupportXML.buildTingcoSupportCasesTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.SUPPORT, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    GregorianCalendar gc = new GregorianCalendar();
                    Object object = session.get(SupportOrganizations.class, supportOrganizationID);
                    if (object == null) {
                        tingcoSupport.getMsgStatus().setResponseCode(-1);
                        tingcoSupport.getMsgStatus().setResponseText("Data Not Found.");
                        return tingcoSupport;
                    }
                    SupportOrganizations supportOrganizations = (SupportOrganizations) object;
                    if (!supportOrganizations.getManagedByGroupId().equalsIgnoreCase(groupId)) {
                        tingcoSupport.getMsgStatus().setResponseCode(-1);
                        tingcoSupport.getMsgStatus().setResponseText("You do not belong to the Manager organization and are not allowed to delete this Support organization.");
                        return tingcoSupport;
                    }
                    List<SupportOrganizationUsers> supportOrganizationUserses = userDAO.getSupportOrganizationUsersById(supportOrganizationID, session);
                    List<SupportOrgVisibleToGroups> supportOrgVisibleToGroupses = supportCasesDAO.getSupportOrgVisibleToGroupsById(session, supportOrganizationID);
                    tx = session.beginTransaction();
                    for (SupportOrganizationUsers supportOrganizationUsers : supportOrganizationUserses) {
                        session.delete(supportOrganizationUsers);
                    }
                    for (SupportOrgVisibleToGroups supportOrgVisibleToGroups : supportOrgVisibleToGroupses) {
                        session.delete(supportOrgVisibleToGroups);
                    }
                    session.delete(supportOrganizations);
                    tx.commit();
                } else {
                    tingcoSupport.getMsgStatus().setResponseCode(-10);
                    tingcoSupport.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoSupport;
                }
            } else {
                tingcoSupport.getMsgStatus().setResponseCode(-3);
                tingcoSupport.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoSupport;
            }
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoSupport.getMsgStatus().setResponseCode(-1);
            tingcoSupport.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoSupport;
    }

    private TingcoSupportCase addSupportOrganization(String groupId, String organizationDescription, String OrganizationName) {
        Session session = null;
        TingcoSupportCase tingcoSupport = null;
        boolean hasPermission = false;
        long requestedTime = System.currentTimeMillis();
        Transaction tx = null;
        try {
            if (!organizationDescription.equals("")) {
                organizationDescription = organizationDescription.split("/")[2];
            } else {
                organizationDescription = null;
            }

            tingcoSupport = tingcoSupportXML.buildTingcoSupportCasesTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.SUPPORT, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    GregorianCalendar gc = new GregorianCalendar();
                    SupportOrganizations supportOrganizations = new SupportOrganizations();
                    supportOrganizations.setSupportOrganizationId(UUID.randomUUID().toString());
                    supportOrganizations.setManagedByGroupId(groupId);
                    supportOrganizations.setOrganizationName(OrganizationName);
                    if (organizationDescription != null) {
                        supportOrganizations.setOrganizationDescription(organizationDescription);
                    }
                    supportOrganizations.setLastUpdatedByUserId(sessions2.getUserId());
                    supportOrganizations.setUpdatedDate(gc.getTime());
                    supportOrganizations.setCreatedDate(gc.getTime());
                    tx = session.beginTransaction();
                    session.saveOrUpdate(supportOrganizations);
                    tx.commit();
                } else {
                    tingcoSupport.getMsgStatus().setResponseCode(-10);
                    tingcoSupport.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoSupport;
                }
            } else {
                tingcoSupport.getMsgStatus().setResponseCode(-3);
                tingcoSupport.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoSupport;
            }
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoSupport.getMsgStatus().setResponseCode(-1);
            tingcoSupport.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoSupport;
    }

    private TingcoSupportCase deleteSupportOrganizationUsers(String supportOrganizationId, String groupId, String userId) {
        Session session = null;
        Transaction tx = null;
        TingcoSupportCase tingcoSupport = null;
        boolean hasPermission = true;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoSupport = tingcoSupportXML.buildTingcoSupportCasesTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.SUPPORT, TCMUtil.DELETE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object object = session.get(SupportOrganizations.class, supportOrganizationId);
                    if (object == null) {
                        tingcoSupport.getMsgStatus().setResponseCode(-1);
                        tingcoSupport.getMsgStatus().setResponseText("Data Not Found.");
                        return tingcoSupport;
                    }
                    SupportOrganizations supportOrganizations = (SupportOrganizations) object;
                    if (!supportOrganizations.getManagedByGroupId().equalsIgnoreCase(groupId)) {
                        tingcoSupport.getMsgStatus().setResponseCode(-1);
                        tingcoSupport.getMsgStatus().setResponseText("You do not belong to the Manager organization and are not allowed to delete this Support organization.");
                        return tingcoSupport;
                    }
                    tx = session.beginTransaction();
                    Object supportOrganizationUsers = supportCasesDAO.getSupportOrganizationUserByIds(supportOrganizationId, userId, session);
                    if (supportOrganizationUsers != null) {
                        session.delete(supportOrganizationUsers);
                        tx.commit();
                    } else {
                        tingcoSupport.getMsgStatus().setResponseCode(-1);
                        tingcoSupport.getMsgStatus().setResponseText("Data not found");
                        return tingcoSupport;
                    }

                } else {
                    tingcoSupport.getMsgStatus().setResponseCode(-10);
                    tingcoSupport.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoSupport;
                }
            } else {
                tingcoSupport.getMsgStatus().setResponseCode(-3);
                tingcoSupport.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoSupport;
            }
        } catch (Exception ex) {
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoSupport.getMsgStatus().setResponseCode(-1);
            tingcoSupport.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoSupport;
    }

    private TingcoSupportCase getGroupsForSupportOrgVisibleGroups(String supportOrganizationId, String groupId, int countryId) {
        Session session = null;
        TingcoSupportCase tingcoSupport = null;
        boolean hasPermission = true;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoSupport = tingcoSupportXML.buildTingcoSupportCasesTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.SUPPORT, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<SupportOrgVisibleToGroups> sovtgList = new ArrayList<SupportOrgVisibleToGroups>();
                    Set<String> groupIdsList = groupDAO.getGroupsAndGroupTrusts(groupId, session);
                    List<List<String>> splitList = TCMUtil.splitStringList(new ArrayList(groupIdsList), 2000);

                    for (List<String> list : splitList) {
                        List<SupportOrgVisibleToGroups> sovtgsList = supportCasesDAO.getSupportOrgVisibleToGroupsByGroupIds(session, supportOrganizationId, list, 0);
                        sovtgList.addAll(sovtgsList);
                    }

                    if (!sovtgList.isEmpty()) {
                        if (sovtgList.size() > 200) {
                            sovtgList = sovtgList.subList(0, 200);
                        }
                        Set<String> supportOrgGroupIdsList = supportCasesDAO.getsupportOrgGroupIdsList(sovtgList);
                        List<GroupTranslations> groupTransList = groupDAO.getGroupTranslationsByGroupId(supportOrgGroupIdsList, countryId, 0, session);
                        tingcoSupport = tingcoSupportXML.buildGroupsForSupportOrgVisibleGroups(tingcoSupport, groupTransList);
                    } else {
                        tingcoSupport.getMsgStatus().setResponseCode(-1);
                        tingcoSupport.getMsgStatus().setResponseText("No data found");
                        return tingcoSupport;
                    }
                } else {
                    tingcoSupport.getMsgStatus().setResponseCode(-10);
                    tingcoSupport.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoSupport;
                }
            } else {
                tingcoSupport.getMsgStatus().setResponseCode(-3);
                tingcoSupport.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoSupport;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoSupport.getMsgStatus().setResponseCode(-1);
            tingcoSupport.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoSupport;
    }

    private TingcoSupportCase getSupportOrganizationList(String groupId, int countryId, String OrganizationName) {
        Session session = null;
        TingcoSupportCase tingcoSupport = null;
        boolean hasPermission = false;
        long requestedTime = System.currentTimeMillis();
        try {
            if (!OrganizationName.equals("")) {
                OrganizationName = OrganizationName.split("/")[2];
            } else {
                OrganizationName = null;
            }

            tingcoSupport = tingcoSupportXML.buildTingcoSupportCasesTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.SUPPORT, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Users2 users2 = userDAO.getUserById(sessions2.getUserId(), session);
                    List<GroupTrusts> groupTrusts = groupDAO.getGroupTrustByGroupID(users2.getGroupId(), session);
                    Set<String> groupTrustedIDSet = groupDAO.getGroupIdsList(users2.getGroupId(), groupTrusts);
                    List<List<String>> splitList = TCMUtil.splitStringList(new ArrayList(groupTrustedIDSet), 2000);
                    List<SupportOrganizations> supportOrganizationses = new ArrayList<SupportOrganizations>();
                    if (TCMUtil.isValidUUID(groupId)) {
                        boolean flag = false;
                        for (String grouptrustID : groupTrustedIDSet) {
                            if (grouptrustID.equalsIgnoreCase(groupId)) {
                                flag = true;
                            }
                        }
                        if (!flag) {
                            tingcoSupport.getMsgStatus().setResponseCode(-1);
                            tingcoSupport.getMsgStatus().setResponseText("Data not Found");
                            return tingcoSupport;
                        }
                        if (OrganizationName != null) {
                            List<String> managedByGroupId = new ArrayList<String>();
                            managedByGroupId.add(groupId);
                            if (TCMUtil.isValidUUID(OrganizationName)) {
                                supportOrganizationses = supportCasesDAO.getSupportOrganizationsByIdAndTrustedGroupIDs(OrganizationName, managedByGroupId, session);
                            } else {
                                supportOrganizationses = supportCasesDAO.getSupportOrganizationsByOrgNamemanagedByGroupId(session, managedByGroupId, OrganizationName);
                            }

                        } else {
                            List<String> managedByGroupId = new ArrayList<String>();
                            managedByGroupId.add(groupId);
                            supportOrganizationses = supportCasesDAO.getSupportOrganizationsBymanagedByGroupId(session, managedByGroupId);
                        }
                    } else {
                        List<GroupTranslations> gtList = new ArrayList<GroupTranslations>();
                        for (List<String> list : splitList) {
                            List<GroupTranslations> gtListTemp = deviceDAO.getGroupTransSearchByGroupName(session, list, groupId, countryId);
                            gtList.addAll(gtListTemp);
                        }
                        if (gtList.isEmpty()) {
                            tingcoSupport.getMsgStatus().setResponseCode(-1);
                            tingcoSupport.getMsgStatus().setResponseText("Data not Found");
                            return tingcoSupport;
                        }
                        List<String> managedByGroupId = new ArrayList<String>();
                        for (GroupTranslations groupTranslations : gtList) {
                            managedByGroupId.add(groupTranslations.getId().getGroupId());
                        }
                        if (OrganizationName != null) {
                            if (TCMUtil.isValidUUID(OrganizationName)) {
                                supportOrganizationses = supportCasesDAO.getSupportOrganizationsByIdAndTrustedGroupIDs(OrganizationName, managedByGroupId, session);
                            } else {
                                supportOrganizationses = supportCasesDAO.getSupportOrganizationsByOrgNamemanagedByGroupId(session, managedByGroupId, OrganizationName);
                            }
                        } else {
                            supportOrganizationses = supportCasesDAO.getSupportOrganizationsBymanagedByGroupId(session, managedByGroupId);
                        }
                    }
                    if (supportOrganizationses.isEmpty()) {
                        tingcoSupport.getMsgStatus().setResponseCode(-1);
                        tingcoSupport.getMsgStatus().setResponseText("Data not Found");
                        return tingcoSupport;
                    }
                    List<String> managedByGroupId = new ArrayList<String>();
                    for (SupportOrganizations supportOrganizations : supportOrganizationses) {
                        managedByGroupId.add(supportOrganizations.getManagedByGroupId());
                    }
                    List<GroupTranslations> gtList = groupDAO.getGroupTranslationsById(managedByGroupId, countryId, session);
                    return tingcoSupportXML.buildGetSupportOrganizationList(tingcoSupport, supportOrganizationses, gtList);
                } else {
                    tingcoSupport.getMsgStatus().setResponseCode(-10);
                    tingcoSupport.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoSupport;
                }
            } else {
                tingcoSupport.getMsgStatus().setResponseCode(-3);
                tingcoSupport.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoSupport;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoSupport.getMsgStatus().setResponseCode(-1);
            tingcoSupport.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoSupport;
    }

    private TingcoSupportCase SaveSupportCaseByID(String supportCaseId, String supportCaseStatusId, String resolutionText, String durationActual) {
        Session session = null;
        Transaction tx = null;
        TingcoSupportCase tingcoSupport = null;
        boolean hasPermission = false;
        long requestedTime = System.currentTimeMillis();
        try {
            if (!supportCaseStatusId.equals("")) {
                supportCaseStatusId = supportCaseStatusId.split("/")[2];
            } else {
                supportCaseStatusId = null;
            }
            if (!resolutionText.equals("")) {
                resolutionText = resolutionText.split("/")[2];
            } else {
                resolutionText = null;
            }
            if (!durationActual.equals("")) {
                durationActual = durationActual.split("/")[2];
            } else {
                durationActual = null;
            }
            tingcoSupport = tingcoSupportXML.buildTingcoSupportCasesTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.SUPPORT, TCMUtil.DELETE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object obj = session.get(SupportCases.class, supportCaseId);
                    if (obj == null) {
                        tingcoSupport.getMsgStatus().setResponseCode(-1);
                        tingcoSupport.getMsgStatus().setResponseText("Data Not found");
                        return tingcoSupport;
                    }
                    String strNull = null;
                    SupportCases supportCases = (SupportCases) obj;
                    if (supportCaseStatusId != null) {
                        Object scs = session.get(SupportCaseStatuses.class, supportCaseStatusId);
                        if (scs == null) {
                            tingcoSupport.getMsgStatus().setResponseCode(-1);
                            tingcoSupport.getMsgStatus().setResponseText("supportCaseStatusId is not Valid");
                            return tingcoSupport;
                        }
                        supportCases.setSupportCaseStatuses((SupportCaseStatuses) scs);
                    } else {
                        tingcoSupport.getMsgStatus().setResponseCode(-1);
                        tingcoSupport.getMsgStatus().setResponseText("SupportCaseStatus should not be empty.");
                        return tingcoSupport;
                    }

                    if (resolutionText != null) {
                        supportCases.setResolutionText(resolutionText);
                    } else {
                        supportCases.setResolutionText(strNull);
                    }
                    if (durationActual != null) {
                        supportCases.setDurationActual(Integer.valueOf(durationActual));
                    } else {
                        supportCases.setDurationActual(null);
                    }
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    supportCases.setUpdatedDate(gc.getTime());
                    tx = session.beginTransaction();
                    session.update(supportCases);
                    tx.commit();
                } else {
                    tingcoSupport.getMsgStatus().setResponseCode(-10);
                    tingcoSupport.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoSupport;
                }
            } else {
                tingcoSupport.getMsgStatus().setResponseCode(-3);
                tingcoSupport.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoSupport;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoSupport.getMsgStatus().setResponseCode(-1);
            tingcoSupport.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoSupport;
    }

    private TingcoSupportCase addSupportOrgVisibleToGroups(String content) {
        boolean hasPermission = false;
        Session session = null;
        TingcoSupportCase tingcoSupportCase = null;
        Transaction tx = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoSupportCase = tingcoSupportXML.buildTingcoSupportCasesTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.SUPPORT, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    se.info24.supportcasejaxb.TingcoSupportCase tingcoSupportCases = (se.info24.supportcasejaxb.TingcoSupportCase) JAXBManager.getInstance().unMarshall(content, se.info24.supportcasejaxb.TingcoSupportCase.class);
                    SupportOrganizationsVisibleToGroups sovtgjaxb = tingcoSupportCases.getSupportCases().getSupportCase().get(0).getSupportOrganizations().get(0).getSupportOrganizationsVisibleToGroups();
                    for (GroupID selectedGroupId : sovtgjaxb.getGroupID()) {
                        SupportOrgVisibleToGroups supportOrgVisibleToGroups = new SupportOrgVisibleToGroups();
                        supportOrgVisibleToGroups.setId(new SupportOrgVisibleToGroupsId(sovtgjaxb.getSupportOrganizationID().get(0), selectedGroupId.getValue()));
                        supportOrgVisibleToGroups.setLastUpdatedByUserId(sessions2.getUserId());
                        supportOrgVisibleToGroups.setCreatedDate(currentDateTime.getTime());
                        supportOrgVisibleToGroups.setUpdatedDate(currentDateTime.getTime());
                        session.saveOrUpdate(supportOrgVisibleToGroups);
                    }
                    tx.commit();
                } else {
                    tingcoSupportCase.getMsgStatus().setResponseCode(-10);
                    tingcoSupportCase.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoSupportCase;
                }
            } else {
                tingcoSupportCase.getMsgStatus().setResponseCode(-3);
                tingcoSupportCase.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoSupportCase;
            }
        } catch (Exception ex) {
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoSupportCase.getMsgStatus().setResponseCode(-1);
            tingcoSupportCase.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoSupportCase;
    }

    private TingcoSupportCase deleteSupportCases(String supportCaseId) {
        Session session = null;
        Transaction tx = null;
        TingcoSupportCase tingcoSupport = null;
        boolean hasPermission = false;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoSupport = tingcoSupportXML.buildTingcoSupportCasesTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.SUPPORT, TCMUtil.DELETE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    Object obj = supportCasesDAO.getSupportCasesBySupportCaseId(session, supportCaseId);
                    UserDAO userDAO = new UserDAO();
                    Users2 users2 = userDAO.getUserById(sessions2.getUserId(), session);
                    NetworkDAO networkDAO = new NetworkDAO();
                    List<ObjectCostCenters> costCenterses = networkDAO.getObjectCostCentersByGroupIdandObjectId(session, supportCaseId, users2.getGroupId());
                    if (obj != null) {
                        if (!costCenterses.isEmpty()) {
                            session.delete(costCenterses.get(0));
                        }
                        session.delete(obj);

                        tx.commit();
                    }
                } else {
                    tingcoSupport.getMsgStatus().setResponseCode(-10);
                    tingcoSupport.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoSupport;
                }
            } else {
                tingcoSupport.getMsgStatus().setResponseCode(-3);
                tingcoSupport.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoSupport;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoSupport.getMsgStatus().setResponseCode(-1);
            tingcoSupport.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoSupport;
    }

    private TingcoSupportCase deleteSupportOrganizationVisibleToGroups(String supportOrganizationId, String groupId, String selectedGroupId) {
        Session session = null;
        Transaction tx = null;
        TingcoSupportCase tingcoSupport = null;
        boolean hasPermission = true;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoSupport = tingcoSupportXML.buildTingcoSupportCasesTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.SUPPORT, TCMUtil.DELETE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();

                    Object object = session.get(SupportOrganizations.class, supportOrganizationId);
                    if (object == null) {
                        tingcoSupport.getMsgStatus().setResponseCode(-1);
                        tingcoSupport.getMsgStatus().setResponseText("Data Not Found.");
                        return tingcoSupport;
                    }
                    SupportOrganizations supportOrganizations = (SupportOrganizations) object;
                    if (!supportOrganizations.getManagedByGroupId().equalsIgnoreCase(groupId)) {
                        tingcoSupport.getMsgStatus().setResponseCode(-1);
                        tingcoSupport.getMsgStatus().setResponseText("You do not belong to the Manager organization and are not allowed to delete this Support organization.");
                        return tingcoSupport;
                    }
                    tx = session.beginTransaction();
                    Object supportOrgVisibleToGroups = supportCasesDAO.getSupportOrgVisibleToGroupsByIds(supportOrganizationId, selectedGroupId, session);
                    if (supportOrgVisibleToGroups != null) {
                        session.delete(supportOrgVisibleToGroups);
                        tx.commit();
                    } else {
                        tingcoSupport.getMsgStatus().setResponseCode(-1);
                        tingcoSupport.getMsgStatus().setResponseText("Data not found");
                        return tingcoSupport;
                    }

                } else {
                    tingcoSupport.getMsgStatus().setResponseCode(-10);
                    tingcoSupport.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoSupport;
                }
            } else {
                tingcoSupport.getMsgStatus().setResponseCode(-3);
                tingcoSupport.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoSupport;
            }
        } catch (Exception ex) {
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoSupport.getMsgStatus().setResponseCode(-1);
            tingcoSupport.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoSupport;
    }

    private TingcoSupportCase getSupportCaseSubjectTemplateDetails(String supportCaseSubjectId) {
        Session session = null;
        TingcoSupportCase tingcoSupport = null;
        boolean hasPermission = true;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoSupport = tingcoSupportXML.buildTingcoSupportCasesTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.SUPPORT)) {
                    ArrayList<String> operations = ht.get(TCMUtil.SUPPORT);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    SupportCaseSubjectTemplates scst = supportCasesDAO.getSupportCaseSubjectTemplatesById(session, supportCaseSubjectId);
                    if (scst == null) {
                        tingcoSupport.getMsgStatus().setResponseCode(-1);
                        tingcoSupport.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoSupport;
                    }
                    return tingcoSupportXML.buildGetSupportCaseSubjectTemplateDetails(tingcoSupport, scst, session);
                } else {
                    tingcoSupport.getMsgStatus().setResponseCode(-10);
                    tingcoSupport.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoSupport;
                }
            } else {
                tingcoSupport.getMsgStatus().setResponseCode(-3);
                tingcoSupport.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoSupport;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoSupport.getMsgStatus().setResponseCode(-1);
            tingcoSupport.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoSupport;
    }

    private TingcoSupportCase getSupportOrganizations(String groupId, String searchString) {
        Session session = null;
        TingcoSupportCase tingcoSupport = null;
        boolean hasPermission = true;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoSupport = tingcoSupportXML.buildTingcoSupportCasesTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.SUPPORT)) {
                    ArrayList<String> operations = ht.get(TCMUtil.SUPPORT);
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
                    List<SupportOrgVisibleToGroups> sovtgList = supportCasesDAO.getSupportOrgVisibleToGroupsByGroupId(session, groupId);
                    if (sovtgList.isEmpty()) {
                        tingcoSupport.getMsgStatus().setResponseCode(-1);
                        tingcoSupport.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoSupport;
                    }
                    List<String> supportOrganizationId = new ArrayList<String>();
                    for (SupportOrgVisibleToGroups supportOrgVisibleToGroups : sovtgList) {
                        supportOrganizationId.add(supportOrgVisibleToGroups.getId().getSupportOrganizationId());
                    }
                    List<SupportOrganizations> soList = new ArrayList<SupportOrganizations>();
                    if (searchString != null) {
                        soList = supportCasesDAO.getSupportOrganizationsByIdsAndSearchString(session, supportOrganizationId, searchString, 100);
                    } else {
                        soList = supportCasesDAO.getSupportOrganizationsByIds(session, supportOrganizationId, 100);
                    }
                    return tingcoSupportXML.buildGetSupportOrganizations(tingcoSupport, soList, session);
                } else {
                    tingcoSupport.getMsgStatus().setResponseCode(-10);
                    tingcoSupport.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoSupport;
                }
            } else {
                tingcoSupport.getMsgStatus().setResponseCode(-3);
                tingcoSupport.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoSupport;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoSupport.getMsgStatus().setResponseCode(-1);
            tingcoSupport.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoSupport;
    }

    private TingcoSupportCase getSupportCaseResolutionTemplates(String groupId, String countryId, String searchString) {
        Session session = null;
        TingcoSupportCase tingcoSupport = null;
        boolean hasPermission = true;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoSupport = tingcoSupportXML.buildTingcoSupportCasesTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.SUPPORT)) {
                    ArrayList<String> operations = ht.get(TCMUtil.SUPPORT);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    if (!searchString.equals("")) {
                        searchString = searchString.split("/")[2];
                    } else {
                        searchString = null;
                    }
                    List<SupportCaseResolutionTemplates> scrtList = supportCasesDAO.getSupportCaseResolutionTemplatesByGroupId(session, groupId, countryId);
                    if (scrtList.isEmpty()) {
                        tingcoSupport.getMsgStatus().setResponseCode(-1);
                        tingcoSupport.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoSupport;
                    }
                    List<String> resolutionTemplateId = new ArrayList<String>();
                    for (SupportCaseResolutionTemplates string : scrtList) {
                        resolutionTemplateId.add(string.getResolutionTemplateId());
                    }
                    if (searchString != null) {
                        scrtList = supportCasesDAO.getSupportCaseResolutionTemplatesByIdsAnsSearch(session, resolutionTemplateId, searchString);
                    } else {
                        scrtList = supportCasesDAO.getSupportCaseResolutionTemplatesByIds(session, resolutionTemplateId);
                    }

                    return tingcoSupportXML.buildGetSupportCaseResolutionTemplates(tingcoSupport, scrtList);
                } else {
                    tingcoSupport.getMsgStatus().setResponseCode(-10);
                    tingcoSupport.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoSupport;
                }
            } else {
                tingcoSupport.getMsgStatus().setResponseCode(-3);
                tingcoSupport.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoSupport;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoSupport.getMsgStatus().setResponseCode(-1);
            tingcoSupport.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoSupport;
    }

    private TingcoSupportCase getSupportCaseDetails(String supportCaseId, Integer countryId) {
        Session session = null;
        TingcoSupportCase tingcoSupport = null;
        boolean hasPermission = true;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoSupport = tingcoSupportXML.buildTingcoSupportCasesTemplate();
            if (supportCaseId.equalsIgnoreCase("")) {
                tingcoSupport.getMsgStatus().setResponseCode(-1);
                tingcoSupport.getMsgStatus().setResponseText("SupportCaseId should not be empty");
                return tingcoSupport;
            }
            if (countryId == null) {
                tingcoSupport.getMsgStatus().setResponseCode(-1);
                tingcoSupport.getMsgStatus().setResponseText("CountryId should not be empty");
                return tingcoSupport;
            }
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.SUPPORT)) {
                    ArrayList<String> operations = ht.get(TCMUtil.SUPPORT);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object obj = supportCasesDAO.getSupportCasesBySupportCaseId(session, supportCaseId);
                    if (obj != null) {
                        SupportCases supportcases = (SupportCases) obj;
                        UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                        if (userTimeZones2 != null) {
                            String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                            SupportImpactTranslations supportImpactTrans = null;
                            SupportPriorityTranslations supportPriorityTrans = null;
                            SupportStatusTranslations supportStatusTrans = null;
                            Users2 users2Reported = null;
                            Users2 users2Assigned = null;
                            CostCenters costCenters = null;
                            SupportTypeTranslations supportTypeTrans = supportCasesDAO.getSupportTypeTranslationsByIds(supportcases.getSupportCaseTypes().getSupportCaseTypeId(), countryId, session);
                            if (supportcases.getSupportCaseImpacts() != null) {
                                supportImpactTrans = supportCasesDAO.getSupportImpactTranslationsByIds(supportcases.getSupportCaseImpacts().getSupportCaseImpactId(), countryId, session);
                            }
                            if (supportcases.getSupportCasePriorities() != null) {
                                supportPriorityTrans = supportCasesDAO.getSupportPriorityTranslationsByIds(supportcases.getSupportCasePriorities().getSupportCasePriorityId(), countryId, session);
                            }
                            if (supportcases.getSupportCaseStatuses() != null) {
                                supportStatusTrans = supportCasesDAO.getSupportStatusTranslationsByIds(supportcases.getSupportCaseStatuses().getSupportCaseStatusId(), countryId, session);
                            }
                            GroupTranslations groupTrans = groupDAO.getGroupTranslationsByIds(supportcases.getGroups().getGroupId(), countryId, session);
                            if (supportcases.getReportedByUserId() != null) {
                                users2Reported = userDAO.getUserById(supportcases.getReportedByUserId(), session);
                            }
                            List<SupportCaseDeviceLinks> supportCaseDeviceLinks = supportCasesDAO.getSupportCaseDeviceLinks(supportCaseId, session);
                            Device device = null;
                            if (!supportCaseDeviceLinks.isEmpty()) {
                                device = deviceDAO.getDeviceById(supportCaseDeviceLinks.get(0).getId().getDeviceId(), session);
                            }
                            SupportOrganizations supportOrganizations = supportCasesDAO.getSupportOrganizationsById(supportcases.getSupportOrganizations().getSupportOrganizationId(), session);
                            if (supportcases.getAssignedToUserId() != null) {
                                users2Assigned = userDAO.getUserById(supportcases.getAssignedToUserId(), session);
                            }
                            if (supportcases.getCostCenterId() != null) {
                                NetworkDAO networkDAO = new NetworkDAO();
                                Users2 users2 = userDAO.getUserById(sessions2.getUserId(), session);
                                List<ObjectCostCenters> objectCostCenterses = networkDAO.getObjectCostCentersByGroupIdandObjectId(session, supportcases.getSupportCaseId(), users2.getGroupId());
                                if (!objectCostCenterses.isEmpty()) {
                                    costCenters = groupDAO.getCostCenterById(objectCostCenterses.get(0).getCostCenterId(), session);
                                }
                            }
                            tingcoSupport = tingcoSupportXML.buildGetSupportCasesDetails(tingcoSupport, supportcases, supportTypeTrans, supportImpactTrans, supportPriorityTrans, supportStatusTrans, groupTrans, users2Reported, device, supportOrganizations, users2Assigned, costCenters, timeZoneGMToffset);
                        } else {
                            tingcoSupport.getMsgStatus().setResponseCode(-1);
                            tingcoSupport.getMsgStatus().setResponseText("UserTimeZone not found");
                            return tingcoSupport;
                        }
                    } else {
                        tingcoSupport.getMsgStatus().setResponseCode(-1);
                        tingcoSupport.getMsgStatus().setResponseText("No SupportCases found");
                        return tingcoSupport;
                    }
                } else {
                    tingcoSupport.getMsgStatus().setResponseCode(-10);
                    tingcoSupport.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoSupport;
                }
            } else {
                tingcoSupport.getMsgStatus().setResponseCode(-3);
                tingcoSupport.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoSupport;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoSupport.getMsgStatus().setResponseCode(-1);
            tingcoSupport.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoSupport;
    }

    private TingcoSupportCase getSupportCaseStatusesTim(String countryId) {
        Session session = null;
        TingcoSupportCase tingcoSupport = null;
        boolean hasPermission = true;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoSupport = tingcoSupportXML.buildTingcoSupportCasesTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.SUPPORT)) {
                    ArrayList<String> operations = ht.get(TCMUtil.SUPPORT);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<SupportStatusTranslations> sstList = supportCasesDAO.getSupportStatusTranslationsByCountryIdOrderBy(session, countryId, 0);
                    if (sstList.isEmpty()) {
                        tingcoSupport.getMsgStatus().setResponseCode(-1);
                        tingcoSupport.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoSupport;
                    }
                    List<String> supportCaseStatusId = new ArrayList<String>();
                    for (SupportStatusTranslations supportStatusTranslations : sstList) {
                        supportCaseStatusId.add(supportStatusTranslations.getId().getSupportCaseStatusId());
                    }
                    List<SupportCaseStatuses> scsList = supportCasesDAO.getSupportCaseStatusesByIds(session, supportCaseStatusId);
                    return tingcoSupportXML.buildGetSupportCaseStatusesTim(tingcoSupport, sstList, scsList);
                } else {
                    tingcoSupport.getMsgStatus().setResponseCode(-10);
                    tingcoSupport.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoSupport;
                }
            } else {
                tingcoSupport.getMsgStatus().setResponseCode(-3);
                tingcoSupport.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoSupport;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoSupport.getMsgStatus().setResponseCode(-1);
            tingcoSupport.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoSupport;
    }

    private TingcoSupportCase getSupportCaseStatuses(String countryId) {
        Session session = null;
        TingcoSupportCase tingcoSupport = null;
        boolean hasPermission = true;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoSupport = tingcoSupportXML.buildTingcoSupportCasesTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.SUPPORT)) {
                    ArrayList<String> operations = ht.get(TCMUtil.SUPPORT);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<SupportStatusTranslations> sstList = supportCasesDAO.getSupportStatusTranslationsByCountryId(session, countryId, 100);
                    if (sstList.isEmpty()) {
                        tingcoSupport.getMsgStatus().setResponseCode(-1);
                        tingcoSupport.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoSupport;
                    }
                    List<String> supportCaseStatusId = new ArrayList<String>();
                    for (SupportStatusTranslations supportStatusTranslations : sstList) {
                        supportCaseStatusId.add(supportStatusTranslations.getId().getSupportCaseStatusId());
                    }
                    List<SupportCaseStatuses> scsList = supportCasesDAO.getSupportCaseStatusesByIds(session, supportCaseStatusId);
                    return tingcoSupportXML.buildGetSupportCaseStatuses(tingcoSupport, sstList, scsList);
                } else {
                    tingcoSupport.getMsgStatus().setResponseCode(-10);
                    tingcoSupport.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoSupport;
                }
            } else {
                tingcoSupport.getMsgStatus().setResponseCode(-3);
                tingcoSupport.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoSupport;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoSupport.getMsgStatus().setResponseCode(-1);
            tingcoSupport.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoSupport;
    }

    private TingcoSupportCase getSupportCasePriorities(String countryId) {
        Session session = null;
        TingcoSupportCase tingcoSupport = null;
        boolean hasPermission = true;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoSupport = tingcoSupportXML.buildTingcoSupportCasesTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.SUPPORT)) {
                    ArrayList<String> operations = ht.get(TCMUtil.SUPPORT);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<SupportPriorityTranslations> sptList = supportCasesDAO.getSupportPriorityTranslationsByCountryId(session, countryId);
                    if (sptList.isEmpty()) {
                        tingcoSupport.getMsgStatus().setResponseCode(-1);
                        tingcoSupport.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoSupport;
                    }
                    List<String> supportCasePriorityId = new ArrayList<String>();
                    for (SupportPriorityTranslations supportPriorityTranslations : sptList) {
                        supportCasePriorityId.add(supportPriorityTranslations.getId().getSupportCasePriorityId());
                    }
                    List<SupportCasePriorities> scpList = supportCasesDAO.getSupportCasePrioritiesByIds(session, supportCasePriorityId);
                    return tingcoSupportXML.buildGetSupportCasePriorities(tingcoSupport, sptList, scpList);
                } else {
                    tingcoSupport.getMsgStatus().setResponseCode(-10);
                    tingcoSupport.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoSupport;
                }
            } else {
                tingcoSupport.getMsgStatus().setResponseCode(-3);
                tingcoSupport.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoSupport;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoSupport.getMsgStatus().setResponseCode(-1);
            tingcoSupport.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoSupport;
    }

    private TingcoSupportCase getSupportCaseImpacts(String countryId) {
        Session session = null;
        TingcoSupportCase tingcoSupport = null;
        boolean hasPermission = true;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoSupport = tingcoSupportXML.buildTingcoSupportCasesTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.SUPPORT)) {
                    ArrayList<String> operations = ht.get(TCMUtil.SUPPORT);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<SupportImpactTranslations> sitList = supportCasesDAO.getSupportImpactTranslationsByCountryId(session, countryId);
                    if (sitList.isEmpty()) {
                        tingcoSupport.getMsgStatus().setResponseCode(-1);
                        tingcoSupport.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoSupport;
                    }
                    List<String> supportCaseImpactId = new ArrayList<String>();
                    for (SupportImpactTranslations supportTypeTranslations : sitList) {
                        supportCaseImpactId.add(supportTypeTranslations.getId().getSupportCaseImpactId());
                    }
                    List<SupportCaseImpacts> sctList = supportCasesDAO.getSupportCaseImpactsByIDs(session, supportCaseImpactId);
                    return tingcoSupportXML.buildGetSupportCaseImpacts(tingcoSupport, sitList, sctList);
                } else {
                    tingcoSupport.getMsgStatus().setResponseCode(-10);
                    tingcoSupport.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoSupport;
                }
            } else {
                tingcoSupport.getMsgStatus().setResponseCode(-3);
                tingcoSupport.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoSupport;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoSupport.getMsgStatus().setResponseCode(-1);
            tingcoSupport.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoSupport;
    }

    private TingcoSupportCase getSupportCaseTypes(String countryId) {
        Session session = null;
        TingcoSupportCase tingcoSupport = null;
        boolean hasPermission = true;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoSupport = tingcoSupportXML.buildTingcoSupportCasesTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.SUPPORT)) {
                    ArrayList<String> operations = ht.get(TCMUtil.SUPPORT);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<SupportTypeTranslations> sttList = supportCasesDAO.getSupportTypeTranslationsByCountryId(session, countryId);
                    if (sttList.isEmpty()) {
                        tingcoSupport.getMsgStatus().setResponseCode(-1);
                        tingcoSupport.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoSupport;
                    }
                    List<String> supportCaseTypeId = new ArrayList<String>();
                    for (SupportTypeTranslations supportTypeTranslations : sttList) {
                        supportCaseTypeId.add(supportTypeTranslations.getId().getSupportCaseTypeId());
                    }
                    List<SupportCaseTypes> sctList = supportCasesDAO.getSupportCaseTypesByIds(session, supportCaseTypeId);
                    return tingcoSupportXML.buildGetSupportCaseTypes(tingcoSupport, sttList, sctList);
                } else {
                    tingcoSupport.getMsgStatus().setResponseCode(-10);
                    tingcoSupport.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoSupport;
                }
            } else {
                tingcoSupport.getMsgStatus().setResponseCode(-3);
                tingcoSupport.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoSupport;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoSupport.getMsgStatus().setResponseCode(-1);
            tingcoSupport.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoSupport;
    }

    private TingcoSupportCase getSubjectTemplates(String groupId, String countryId, String searchString) {
        Session session = null;
        TingcoSupportCase tingcoSupport = null;
        boolean hasPermission = true;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoSupport = tingcoSupportXML.buildTingcoSupportCasesTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.SUPPORT)) {
                    ArrayList<String> operations = ht.get(TCMUtil.SUPPORT);
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
                    List<SupportCaseSubjectTemplates> scstList = new ArrayList<SupportCaseSubjectTemplates>();
                    if (searchString != null) {
                        scstList = supportCasesDAO.getSupportCaseSubjectTemplatesByGroupIdSearch(session, groupId, countryId, searchString);
                    } else {
                        scstList = supportCasesDAO.getSupportCaseSubjectTemplatesByGroupId(session, groupId, countryId);
                    }

                    if (scstList.isEmpty()) {
                        tingcoSupport.getMsgStatus().setResponseCode(-1);
                        tingcoSupport.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoSupport;
                    }
                    return tingcoSupportXML.buildGetSubjectTemplates(tingcoSupport, scstList);
                } else {
                    tingcoSupport.getMsgStatus().setResponseCode(-10);
                    tingcoSupport.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoSupport;
                }
            } else {
                tingcoSupport.getMsgStatus().setResponseCode(-3);
                tingcoSupport.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoSupport;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoSupport.getMsgStatus().setResponseCode(-1);
            tingcoSupport.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoSupport;
    }

    private TingcoSupportCase getSupportCasesBySearchCriteria(String content) {
        boolean hasPermission = false;
        Session session = null;
        TingcoSupportCase tingcoSupport = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoSupport = tingcoSupportXML.buildTingcoSupportCasesTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.SUPPORT)) {
                    ArrayList<String> operations = ht.get(TCMUtil.SUPPORT);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    TingcoSupportCase tingcoSupportCase = (TingcoSupportCase) JAXBManager.getInstance().unMarshall(content, TingcoSupportCase.class);
                    if (!tingcoSupportCase.getSupportCases().getSupportCase().isEmpty()) {
                        SupportCase supportCase = tingcoSupportCase.getSupportCases().getSupportCase().get(0);
                        if (supportCase != null) {
                            UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                            if (userTimeZones2 != null) {
                                String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                                List<SupportCases> supportcases = new ArrayList<SupportCases>();
                                if (supportCase.getSupportCaseID() == null && supportCase.getAssignedToUserID() == null && supportCase.getDeviceID() == null && supportCase.getGroupID().getGroupName() == null &&
                                        supportCase.getDeviceTypeID() == null && supportCase.getObjectGroupName() == null && supportCase.getAssetID() == null && supportCase.getOptionalCountryID() == null &&
                                        supportCase.getSupportCaseStatuses().isEmpty() && supportCase.getSupportCaseTypes().isEmpty() && supportCase.getSupportCasePriorities().isEmpty()) {
                                    supportcases = supportCasesDAO.getSupportCasesByGroupId(supportCase.getGroupID().getValue(), session);
                                } else {
                                    List<String> supportCaseIdsList = supportCasesDAO.getSupportCasesBySearchCriteria(supportcases, supportCase, sessions2.getUserId(), session);
                                    if (!supportCaseIdsList.isEmpty()) {
                                        supportcases = supportCasesDAO.getSupportCasesBySupportCaseIds(session, supportCaseIdsList);
                                    }
                                }
                                if (!supportcases.isEmpty()) {
                                    System.out.println("supportcaes is " + supportcases.size());
                                    boolean isSupportCaseTimeReport = supportCase.isIsSupportCaseTimeReportPage();
                                    tingcoSupport = tingcoSupportXML.buildGetSupportCasesBySearchCriteria(tingcoSupport, supportcases, supportCase.getCountryID().intValue(), timeZoneGMToffset, isSupportCaseTimeReport, session);
                                } else {
                                    tingcoSupport.getMsgStatus().setResponseCode(-1);
                                    tingcoSupport.getMsgStatus().setResponseText("No Records found");
                                    return tingcoSupport;
                                }
                            } else {
                                tingcoSupport.getMsgStatus().setResponseCode(-1);
                                tingcoSupport.getMsgStatus().setResponseText("UserTimeZone not found");
                                return tingcoSupport;
                            }
                        } else {
                            tingcoSupport.getMsgStatus().setResponseCode(-1);
                            tingcoSupport.getMsgStatus().setResponseText("No SupportCase Found in XML");
                            return tingcoSupport;
                        }
                    } else {
                        tingcoSupport.getMsgStatus().setResponseCode(-1);
                        tingcoSupport.getMsgStatus().setResponseText("SupportCase tag is not found in XML");
                        return tingcoSupport;
                    }
                } else {
                    tingcoSupport.getMsgStatus().setResponseCode(-10);
                    tingcoSupport.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoSupport;
                }
            } else {
                tingcoSupport.getMsgStatus().setResponseCode(-3);
                tingcoSupport.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoSupport;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoSupport.getMsgStatus().setResponseCode(-1);
            tingcoSupport.getMsgStatus().setResponseText("Error occured");
            return tingcoSupport;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoSupport;
    }

    private TingcoSupportCase getSupportOrganizationsByGroupId(String groupId, int countryId, String searchString) {
        Session session = null;
        TingcoSupportCase tingcoSupport = null;
        boolean hasPermission = true;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoSupport = tingcoSupportXML.buildTingcoSupportCasesTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.SUPPORT, TCMUtil.READ);
                if (hasPermission) {
                    if (!searchString.equals("")) {
                        searchString = searchString.split("/")[2];
                    } else {
                        searchString = null;
                    }
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<SupportOrgVisibleToGroups> sovtgList = supportCasesDAO.getSupportOrgVisibleToGroupsByGroupId(session, groupId);
                    if (sovtgList.isEmpty()) {
                        tingcoSupport.getMsgStatus().setResponseCode(-1);
                        tingcoSupport.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoSupport;
                    }
                    List<String> supportOrganizationId = new ArrayList<String>();
                    for (SupportOrgVisibleToGroups supportOrgVisibleToGroups : sovtgList) {
                        supportOrganizationId.add(supportOrgVisibleToGroups.getId().getSupportOrganizationId());
                    }
                    List<SupportOrganizations> soList = new ArrayList<SupportOrganizations>();
                    if (searchString != null) {
                        soList = supportCasesDAO.getSupportOrganizationsByIdsAndSearchString(session, supportOrganizationId, searchString, 200);
                    } else {
                        soList = supportCasesDAO.getSupportOrganizationsByIds(session, supportOrganizationId, 200);
                    }
                    return tingcoSupportXML.buildGetSupportOrganizationsByGroupId(tingcoSupport, soList, countryId, session);
                } else {
                    tingcoSupport.getMsgStatus().setResponseCode(-10);
                    tingcoSupport.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoSupport;
                }
            } else {
                tingcoSupport.getMsgStatus().setResponseCode(-3);
                tingcoSupport.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoSupport;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoSupport.getMsgStatus().setResponseCode(-1);
            tingcoSupport.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoSupport;
    }

    private TingcoSupportCase updateSupportCase(String supportCaseId) {
        Session session = null;
        TingcoSupportCase tingcoSupport = null;
        boolean hasPermission = true;
        Transaction tx = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoSupport = tingcoSupportXML.buildTingcoSupportCasesTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.SUPPORT)) {
                    ArrayList<String> operations = ht.get(TCMUtil.SUPPORT);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    SupportCaseStatuses supportCaseStatuses = supportCasesDAO.getSupportCaseStatusesByIsClosed(session, 1).get(0);
                    Object obj = supportCasesDAO.getSupportCasesBySupportCaseId(session, supportCaseId);
                    SupportCases supportCases = (SupportCases) obj;
                    supportCases.setSupportCaseStatuses(supportCaseStatuses);
                    supportCases.setCompletionDate(gc.getTime());
                    supportCases.setIsUpdated(1);
                    supportCases.setLastUpdatedByUserId(sessions2.getUserId());
                    if (!supportCasesDAO.saveSupportCases(supportCases, tx, session)) {
                        tingcoSupport.getMsgStatus().setResponseCode(-1);
                        tingcoSupport.getMsgStatus().setResponseText("Error occured");
                        return tingcoSupport;
                    }
                } else {
                    tingcoSupport.getMsgStatus().setResponseCode(-10);
                    tingcoSupport.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoSupport;
                }
            } else {
                tingcoSupport.getMsgStatus().setResponseCode(-3);
                tingcoSupport.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoSupport;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoSupport.getMsgStatus().setResponseCode(-1);
            tingcoSupport.getMsgStatus().setResponseText("Error occured");
            return tingcoSupport;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoSupport;
    }

    private TingcoSupportCase updateSupportCaseDetails(String content) {
        boolean hasPermission = false;
        Session session = null;
        TingcoSupportCase tingcoSupportCase = null;
        Transaction tx = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoSupportCase = tingcoSupportXML.buildTingcoSupportCasesTemplate();
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
//                    tx = session.beginTransaction();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        GregorianCalendar gc = new GregorianCalendar();
                        se.info24.supportcasejaxb.TingcoSupportCase tingcoSupportCases = (se.info24.supportcasejaxb.TingcoSupportCase) JAXBManager.getInstance().unMarshall(content, se.info24.supportcasejaxb.TingcoSupportCase.class);
                        if (!tingcoSupportCases.getSupportCases().getSupportCase().isEmpty()) {
                            se.info24.supportcasejaxb.SupportCase supportCase = tingcoSupportCases.getSupportCases().getSupportCase().get(0);
                            Object obj = supportCasesDAO.getSupportCasesBySupportCaseId(session, supportCase.getSupportCaseID());
                            if (obj != null) {
                                SupportCases sc = (SupportCases) obj;
                                sc.setSupportCaseSubject(supportCase.getSupportCaseSubject());
                                List<String> sctId = new ArrayList<String>();
                                sctId.add(supportCase.getSupportCaseTypeID());
                                List<SupportCaseTypes> sctList = supportCasesDAO.getSupportCaseTypesByIds(session, sctId);
                                if (sctList.isEmpty()) {
                                    tingcoSupportCase.getMsgStatus().setResponseCode(-1);
                                    tingcoSupportCase.getMsgStatus().setResponseText("SupportCaseTypeID is not Valid");
                                    return tingcoSupportCase;
                                }
                                sc.setSupportCaseTypes(sctList.get(0));
                                sctId.clear();
                                sctId.add(supportCase.getSupportCaseImpactID());
                                List<SupportCaseImpacts> sciList = supportCasesDAO.getSupportCaseImpactsByIDs(session, sctId);
                                if (sciList.isEmpty()) {
                                    tingcoSupportCase.getMsgStatus().setResponseCode(-1);
                                    tingcoSupportCase.getMsgStatus().setResponseText("SupportCaseImpactID is not Valid");
                                    return tingcoSupportCase;
                                }
                                sc.setSupportCaseImpacts(sciList.get(0));

                                sctId.clear();
                                sctId.add(supportCase.getSupportCasePriorityID());
                                List<SupportCasePriorities> scpList = supportCasesDAO.getSupportCasePrioritiesByIds(session, sctId);
                                if (scpList.isEmpty()) {
                                    tingcoSupportCase.getMsgStatus().setResponseCode(-1);
                                    tingcoSupportCase.getMsgStatus().setResponseText("SupportCasePriorityID is not Valid");
                                    return tingcoSupportCase;
                                }
                                sc.setSupportCasePriorities(scpList.get(0));
                                sctId.clear();
                                sctId.add(supportCase.getSupportCaseStatusID());
                                List<SupportCaseStatuses> scsList = supportCasesDAO.getSupportCaseStatusesByIds(session, sctId);
                                if (scsList.isEmpty()) {
                                    tingcoSupportCase.getMsgStatus().setResponseCode(-1);
                                    tingcoSupportCase.getMsgStatus().setResponseText("SupportCaseStatusID is not Valid");
                                    return tingcoSupportCase;
                                }
                                sc.setSupportCaseStatuses(scsList.get(0));
                                Groups groups = groupDAO.getGroupByGroupId(supportCase.getImpactsGroupID(), session);
                                if (groups == null) {
                                    tingcoSupportCase.getMsgStatus().setResponseCode(-1);
                                    tingcoSupportCase.getMsgStatus().setResponseText("ImpactsGroupID is not Valid");
                                    return tingcoSupportCase;
                                }
                                sc.setGroups(groups);

                                sc.setReportedByUserId(supportCase.getReportedByUserID());

                                SupportOrganizations supportOrganizations = supportCasesDAO.getSupportOrganizationsById(supportCase.getAssignedToSupportOrganizationID(), session);
                                if (supportOrganizations == null) {
                                    tingcoSupportCase.getMsgStatus().setResponseCode(-1);
                                    tingcoSupportCase.getMsgStatus().setResponseText("AssignedToSupportOrganizationID is not Valid");
                                    return tingcoSupportCase;
                                }

                                sc.setSupportOrganizations(supportOrganizations);
                                String strnull = null;
                                if (supportCase.getSupportCaseDescription() != null) {
                                    sc.setSupportCaseDescription(supportCase.getSupportCaseDescription());
                                } else {
                                    sc.setSupportCaseDescription(strnull);
                                }
                                if (supportCase.getReproduceSteps() != null) {
                                    sc.setReproduceSteps(supportCase.getReproduceSteps());
                                } else {
                                    sc.setReproduceSteps(strnull);
                                }

                                if (supportCase.getResolutionText() != null) {
                                    sc.setResolutionText(supportCase.getResolutionText());
                                } else {
                                    sc.setResolutionText(null);
                                }
                                GregorianCalendar gc1 = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                                if (supportCase.getReportedDate() != null) {
                                    gc1.setTime(df.parse(supportCase.getReportedDate()));
//                                    df.format(gc1.getTime());
                                    gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                                    sc.setReportedDate(gc1.getTime());
                                }

                                if (supportCase.getDueDate() != null) {
                                    gc1.setTime(df.parse(supportCase.getDueDate()));
//                                    df.format(gc1.getTime());
                                    gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                                    sc.setDueDate(gc1.getTime());
                                }
                                if (supportCase.getAssignedToUserID() != null) {
                                    sc.setAssignedToUserId(supportCase.getAssignedToUserID());
                                } else {
                                    sc.setAssignedToUserId(strnull);
                                }
                                if (supportCase.getStartDate() != null) {
                                    gc1.setTime(df.parse(supportCase.getStartDate()));
//                                    df.format(gc1.getTime());
                                    gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                                    sc.setStartDate(gc1.getTime());
                                }

                                if (supportCase.getCompletionDate() != null) {
                                    gc1.setTime(df.parse(supportCase.getCompletionDate()));
//                                    df.format(gc1.getTime());
                                    gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                                    sc.setCompletionDate(gc1.getTime());
                                }
                                if (supportCase.getDurationEstimated() != null) {
                                    sc.setDurationEstimated(supportCase.getDurationEstimated().intValue());
                                } else {
                                    sc.setDurationEstimated(null);
                                }
                                if (supportCase.getDurationActual() != null) {
                                    sc.setDurationActual(supportCase.getDurationActual());
                                } else {
                                    sc.setDurationActual(null);
                                }
                                ObjectCostCenters costCenters = null;
                                boolean isDelete = false;
                                NetworkDAO networkDAO = new NetworkDAO();
                                UserDAO userDAO = new UserDAO();
                                Users2 users2 = userDAO.getUserById(sessions2.getUserId(), session);
                                List<ObjectCostCenters> objectCostCenterses = networkDAO.getObjectCostCentersByGroupIdandObjectId(session, sc.getSupportCaseId(), users2.getGroupId());
                                if (!supportCase.getCostCenters().isEmpty()) {
                                    sc.setCostCenterId(supportCase.getCostCenters().get(0).getCostCenterID());
                                    if (!objectCostCenterses.isEmpty()) {
                                        costCenters = objectCostCenterses.get(0);
                                        costCenters.setCostCenterId(supportCase.getCostCenters().get(0).getCostCenterID());
                                        costCenters.setLastUpdatedByUserId(sessions2.getUserId());
                                        costCenters.setUpdatedDate(gc.getTime());
                                    } else {
                                        costCenters = new ObjectCostCenters();
                                        ObjectCostCentersId ids = new ObjectCostCentersId();
                                        ids.setObjectId(sc.getSupportCaseId());
                                        ids.setGroupId(groups.getGroupId());
                                        costCenters.setId(ids);
                                        costCenters.setCostCenterId(supportCase.getCostCenters().get(0).getCostCenterID());
                                        costCenters.setLastUpdatedByUserId(sessions2.getUserId());
                                        costCenters.setUpdatedDate(gc.getTime());
                                        costCenters.setCreatedDate(gc.getTime());
                                    }
                                } else {
                                    sc.setCostCenterId(null);
                                    if (!objectCostCenterses.isEmpty()) {
                                        costCenters = objectCostCenterses.get(0);
                                        isDelete = true;
                                    }
                                }
                                sc.setLastUpdatedByUserId(sessions2.getUserId());
                                sc.setUpdatedDate(gc.getTime());
                                SupportCaseDeviceLinks scdl = null;
                                if (supportCase.getDeviceID() != null) {
                                    List<SupportCaseDeviceLinks> scdlList = supportCasesDAO.getSupportCaseDeviceLinksBySupportCaseIdDeviceId(session, supportCase.getDeviceID(), supportCase.getSupportCaseID());
                                    if (scdlList.isEmpty()) {
                                        scdl = new SupportCaseDeviceLinks();
                                        SupportCaseDeviceLinksId id = new SupportCaseDeviceLinksId();
                                        id.setDeviceId(supportCase.getDeviceID());
                                        id.setSupportCaseId(supportCase.getSupportCaseID());
                                        scdl.setId(id);
                                        scdl.setLastUpdatedByUserId(sessions2.getUserId());
                                        scdl.setCreatedDate(gc.getTime());
                                        scdl.setUpdatedDate(gc.getTime());
                                    } else {
                                        scdl = scdlList.get(0);
                                        scdl.setUpdatedDate(gc.getTime());
                                    }
                                }
                                if (!supportCasesDAO.updateSupportCaseDetails(session, sc, scdl, costCenters, isDelete)) {
                                    tingcoSupportCase.getMsgStatus().setResponseCode(-1);
                                    tingcoSupportCase.getMsgStatus().setResponseText("Error occured");
                                }
                                return tingcoSupportCase;
                            } else {
                                tingcoSupportCase.getMsgStatus().setResponseCode(-1);
                                tingcoSupportCase.getMsgStatus().setResponseText("No SupportCase Found in XML");
                                return tingcoSupportCase;
                            }
                        } else {
                            tingcoSupportCase.getMsgStatus().setResponseCode(-1);
                            tingcoSupportCase.getMsgStatus().setResponseText("SupportCase tag is not found in XML");
                            return tingcoSupportCase;
                        }
                    } else {
                        tingcoSupportCase.getMsgStatus().setResponseCode(-1);
                        tingcoSupportCase.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                        return tingcoSupportCase;
                    }

                } else {
                    tingcoSupportCase.getMsgStatus().setResponseCode(-10);
                    tingcoSupportCase.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoSupportCase;
                }
            } else {
                tingcoSupportCase.getMsgStatus().setResponseCode(-3);
                tingcoSupportCase.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoSupportCase;
            }
        } catch (Exception ex) {
            tx.rollback();
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoSupportCase.getMsgStatus().setResponseCode(-1);
            tingcoSupportCase.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoSupportCase;
    }

    private TingcoSupportCase addSupportCase(String content) {
        boolean hasPermission = false;
        Session session = null;
        TingcoSupportCase tingcoSupportCase = null;
        Transaction tx = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoSupportCase = tingcoSupportXML.buildTingcoSupportCasesTemplate();
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
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                        se.info24.supportcasejaxb.TingcoSupportCase tingcoSupportCases = (se.info24.supportcasejaxb.TingcoSupportCase) JAXBManager.getInstance().unMarshall(content, se.info24.supportcasejaxb.TingcoSupportCase.class);
                        if (!tingcoSupportCases.getSupportCases().getSupportCase().isEmpty()) {
                            se.info24.supportcasejaxb.SupportCase supportCase = tingcoSupportCases.getSupportCases().getSupportCase().get(0);
                            SupportCases sc = new SupportCases();
                            String supportCaseid = UUID.randomUUID().toString();
                            sc.setSupportCaseId(supportCaseid);
                            sc.setSupportCaseSubject(supportCase.getSupportCaseSubject());
                            List<String> sctId = new ArrayList<String>();
                            sctId.add(supportCase.getSupportCaseTypeID());
                            List<SupportCaseTypes> sctList = supportCasesDAO.getSupportCaseTypesByIds(session, sctId);
                            if (sctList.isEmpty()) {
                                tingcoSupportCase.getMsgStatus().setResponseCode(-1);
                                tingcoSupportCase.getMsgStatus().setResponseText("SupportCaseTypeID is not Valid");
                                return tingcoSupportCase;
                            }
                            sc.setSupportCaseTypes(sctList.get(0));
                            sctId.clear();
                            sctId.add(supportCase.getSupportCaseImpactID());
                            List<SupportCaseImpacts> sciList = supportCasesDAO.getSupportCaseImpactsByIDs(session, sctId);
                            if (sciList.isEmpty()) {
                                tingcoSupportCase.getMsgStatus().setResponseCode(-1);
                                tingcoSupportCase.getMsgStatus().setResponseText("SupportCaseImpactID is not Valid");
                                return tingcoSupportCase;
                            }
                            sc.setSupportCaseImpacts(sciList.get(0));

                            sctId.clear();
                            sctId.add(supportCase.getSupportCasePriorityID());
                            List<SupportCasePriorities> scpList = supportCasesDAO.getSupportCasePrioritiesByIds(session, sctId);
                            if (scpList.isEmpty()) {
                                tingcoSupportCase.getMsgStatus().setResponseCode(-1);
                                tingcoSupportCase.getMsgStatus().setResponseText("SupportCasePriorityID is not Valid");
                                return tingcoSupportCase;
                            }
                            sc.setSupportCasePriorities(scpList.get(0));
                            sctId.clear();
                            sctId.add(supportCase.getSupportCaseStatusID());
                            List<SupportCaseStatuses> scsList = supportCasesDAO.getSupportCaseStatusesByIds(session, sctId);
                            if (scsList.isEmpty()) {
                                tingcoSupportCase.getMsgStatus().setResponseCode(-1);
                                tingcoSupportCase.getMsgStatus().setResponseText("SupportCaseStatusID is not Valid");
                                return tingcoSupportCase;
                            }
                            sc.setSupportCaseStatuses(scsList.get(0));
                            Groups groups = groupDAO.getGroupByGroupId(supportCase.getImpactsGroupID(), session);
                            if (groups == null) {
                                tingcoSupportCase.getMsgStatus().setResponseCode(-1);
                                tingcoSupportCase.getMsgStatus().setResponseText("ImpactsGroupID is not Valid");
                                return tingcoSupportCase;
                            }
                            sc.setGroups(groups);
                            sctId.clear();
                            sctId.add(supportCase.getReportedByUserID());
                            List<Users2> users2List = groupDAO.getUsersList(sctId, session);
                            sc.setReportedByUserId(supportCase.getReportedByUserID());
                            if (!users2List.isEmpty()) {
                                if (users2List.get(0).getFirstName() != null && users2List.get(0).getLastName() != null) {
                                    sc.setReportedByUserName(users2List.get(0).getFirstName() + " " + users2List.get(0).getLastName());
                                }
                                if (users2List.get(0).getUserEmail() != null) {
                                    sc.setReportedByEmail(users2List.get(0).getUserEmail());
                                }
                                if (users2List.get(0).getUserMobilePhone() != null) {
                                    sc.setReportedByPhone(users2List.get(0).getUserMobilePhone());
                                }
                            }

                            SupportOrganizations supportOrganizations = supportCasesDAO.getSupportOrganizationsById(supportCase.getAssignedToSupportOrganizationID(), session);
                            if (supportOrganizations == null) {
                                tingcoSupportCase.getMsgStatus().setResponseCode(-1);
                                tingcoSupportCase.getMsgStatus().setResponseText("AssignedToSupportOrganizationID is not Valid");
                                return tingcoSupportCase;
                            }

                            sc.setSupportOrganizations(supportOrganizations);
                            String strnull = null;
                            if (supportCase.getSupportCaseDescription() != null) {
                                sc.setSupportCaseDescription(supportCase.getSupportCaseDescription());
                            } else {
                                sc.setSupportCaseDescription(strnull);
                            }
                            if (supportCase.getReproduceSteps() != null) {
                                sc.setReproduceSteps(supportCase.getReproduceSteps());
                            } else {
                                sc.setReproduceSteps(strnull);
                            }

                            if (supportCase.getResolutionText() != null) {
                                sc.setResolutionText(supportCase.getResolutionText());
                            } else {
                                sc.setResolutionText(null);
                            }
                            GregorianCalendar gc1 = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                            if (supportCase.getReportedDate() != null) {
                                gc1.setTime(df.parse(supportCase.getReportedDate()));
//                                df.format(gc1.getTime());
                                gc1.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc1.getTime()));
                                sc.setReportedDate(gc1.getTime());
                            } else {
                                sc.setReportedDate(gc.getTime());
                            }

                            if (supportCase.getDueDate() != null) {
                                gc1.setTime(df.parse(supportCase.getDueDate()));
                                gc1.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc1.getTime()));
//                                df.format(gc1.getTime());
                                sc.setDueDate(gc1.getTime());
                            }
                            if (supportCase.getAssignedToUserID() != null) {
                                sc.setAssignedToUserId(supportCase.getAssignedToUserID());
                            } else {
                                sc.setAssignedToUserId(strnull);
                            }
                            if (supportCase.getStartDate() != null) {
                                gc1.setTime(df.parse(supportCase.getStartDate()));
//                                df.format(gc1.getTime());
                                gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                                sc.setStartDate(gc1.getTime());
                            }

                            if (supportCase.getCompletionDate() != null) {
                                gc1.setTime(df.parse(supportCase.getCompletionDate()));
//                                df.format(gc1.getTime());
                                gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                                sc.setCompletionDate(gc1.getTime());
                            }
                            if (supportCase.getDurationEstimated() != null) {
                                sc.setDurationEstimated(supportCase.getDurationEstimated().intValue());
                            } else {
                                sc.setDurationEstimated(null);
                            }
                            if (supportCase.getDurationActual() != null) {
                                sc.setDurationActual(supportCase.getDurationActual());
                            } else {
                                sc.setDurationActual(null);
                            }
                            ObjectCostCenters costCenters = null;
                            if (!supportCase.getCostCenters().isEmpty()) {
                                sc.setCostCenterId(supportCase.getCostCenters().get(0).getCostCenterID());
                                costCenters = new ObjectCostCenters();
                                ObjectCostCentersId id = new ObjectCostCentersId();
                                id.setObjectId(supportCaseid);
                                id.setGroupId(groups.getGroupId());
                                costCenters.setId(id);
                                costCenters.setCostCenterId(supportCase.getCostCenters().get(0).getCostCenterID());
                                costCenters.setLastUpdatedByUserId(sessions2.getUserId());
                                costCenters.setUpdatedDate(gc.getTime());
                                costCenters.setCreatedDate(gc.getTime());
                            } else {
                                sc.setCostCenterId(null);
                            }
                            sc.setLastUpdatedByUserId(sessions2.getUserId());
                            sc.setUpdatedDate(gc.getTime());
                            sc.setCreatedDate(gc.getTime());

                            SupportCaseDeviceLinks scdl = null;
                            if (supportCase.getDeviceID() != null) {
                                List<SupportCaseDeviceLinks> scdlList = supportCasesDAO.getSupportCaseDeviceLinksBySupportCaseIdDeviceId(session, supportCase.getDeviceID(), supportCase.getSupportCaseID());
                                if (scdlList.isEmpty()) {
                                    scdl = new SupportCaseDeviceLinks();
                                    SupportCaseDeviceLinksId id = new SupportCaseDeviceLinksId(supportCaseid, supportCase.getDeviceID());
                                    scdl.setId(id);
                                    scdl.setLastUpdatedByUserId(sessions2.getUserId());
                                    scdl.setCreatedDate(gc.getTime());
                                    scdl.setUpdatedDate(gc.getTime());
                                } else {
                                    scdl = scdlList.get(0);
                                    scdl.setUpdatedDate(gc.getTime());
                                }
                            }
                            SupportCaseSubjectTemplates scst = null;
                            if (supportCase.getIsSupportCaseTemplate().intValue() == 1) {
                                scst = new SupportCaseSubjectTemplates();
                                scst.setSupportCaseSubjectId(UUID.randomUUID().toString());
                                scst.setSupportCaseSubject(supportCase.getSupportCaseSubject());
                                Users2 user = (Users2) session.getNamedQuery("getUsers2ById").setString("userID", sessions2.getUserId()).list().get(0);
                                scst.setGroupId(user.getGroupId());
                                if (!supportCase.getCostCenters().isEmpty()) {
                                    scst.setCostCenterId(supportCase.getCostCenters().get(0).getCostCenterID());
                                }
                                scst.setCountryId(supportCase.getCountryID().intValue());
                                if (supportCase.getSupportCaseDescription() != null) {
                                    scst.setSupportCaseDescription(supportCase.getSupportCaseDescription());
                                }

                                scst.setSupportCaseImpactId(supportCase.getSupportCaseImpactID());
                                scst.setSupportCasePriorityId(supportCase.getSupportCasePriorityID());
                                scst.setSupportCaseTypeId(supportCase.getSupportCaseTypeID());
                                scst.setSupportCaseStatusId(supportCase.getSupportCaseStatusID());

                                if (supportCase.getReproduceSteps() != null) {
                                    scst.setReproduceSteps(supportCase.getReproduceSteps());
                                }
                                if (supportCase.getResolutionText() != null) {
                                    scst.setResolutionText(supportCase.getResolutionText());
                                }
                                scst.setCreatedDate(gc.getTime());
                                scst.setUpdatedDate(gc.getTime());
                            }
                            if (!supportCasesDAO.addSupportCase(session, sc, scdl, scst, costCenters)) {
                                tingcoSupportCase.getMsgStatus().setResponseCode(-1);
                                tingcoSupportCase.getMsgStatus().setResponseText("Error occured");
                            }
                            return tingcoSupportCase;

                        } else {
                            tingcoSupportCase.getMsgStatus().setResponseCode(-1);
                            tingcoSupportCase.getMsgStatus().setResponseText("SupportCase tag is not found in XML");
                            return tingcoSupportCase;
                        }
                    } else {
                        tingcoSupportCase.getMsgStatus().setResponseCode(-1);
                        tingcoSupportCase.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                        return tingcoSupportCase;
                    }

                } else {
                    tingcoSupportCase.getMsgStatus().setResponseCode(-10);
                    tingcoSupportCase.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoSupportCase;
                }
            } else {
                tingcoSupportCase.getMsgStatus().setResponseCode(-3);
                tingcoSupportCase.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoSupportCase;
            }
        } catch (Exception ex) {
            tx.rollback();
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoSupportCase.getMsgStatus().setResponseCode(-1);
            tingcoSupportCase.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
            System.gc();
        }
        return tingcoSupportCase;
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.pojo.Applications;
import se.info24.appjaxb.TingcoApplications;
import se.info24.group.GroupDAO;
import se.info24.ismOperationsPojo.AgreementBillingData;
import se.info24.jaxbUtil.TingcoApplicationXML;
import se.info24.pojo.AgreementItemTypeTranslations;
import se.info24.pojo.AgreementItemTypes;
import se.info24.pojo.AgreementItems;
import se.info24.pojo.AgreementRoleTypeTranslations;
import se.info24.pojo.AgreementRoles;
import se.info24.pojo.AgreementRolesId;
import se.info24.pojo.AgreementRolesTypes;
import se.info24.pojo.AgreementStatusTranslations;
import se.info24.pojo.AgreementStatuses;
import se.info24.pojo.AgreementTypeTranslations;
import se.info24.pojo.Agreements;
import se.info24.pojo.ApplicationEmailTemplates;
import se.info24.pojo.ApplicationGroupMemberships;
import se.info24.pojo.ApplicationGroupMembershipsId;
import se.info24.pojo.ApplicationModuleTranslations;
import se.info24.pojo.ApplicationModules;
import se.info24.pojo.ApplicationPackageModules;
import se.info24.pojo.ApplicationPackageTranslations;
import se.info24.pojo.ApplicationPackages;
import se.info24.pojo.ApplicationSolutionTranslations;
import se.info24.pojo.ApplicationSolutions;
import se.info24.pojo.ApplicationTranslations;
import se.info24.pojo.ApplicationTranslationsId;
import se.info24.pojo.ApplicationTypes;
import se.info24.pojo.Country;
import se.info24.pojo.GroupApplicationPackages;
import se.info24.pojo.GroupDefaultAgreement;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupTrusts;
import se.info24.pojo.GroupVisibleApplicationModules;
import se.info24.pojo.GroupVisibleApplicationPackages;
import se.info24.pojo.GroupVisibleApplicationSolutions;
import se.info24.pojo.Groups;
import se.info24.pojo.PricePlans;
import se.info24.pojo.ProductVariantTranslations;
import se.info24.pojo.SystemMessageTranslations;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.UserTimeZones2;
import se.info24.pojo.Users2;
import se.info24.priceplans.PricePlanDAO;
import se.info24.products.ProductsDAO;
import se.info24.restUtil.RestUtilDAO;
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
 * @author Hitha
 */
@Path("/application")
public class ApplicationsResource {

    @Context
    private HttpServletRequest request;
    TingcoApplicationXML tingcoAppXML = new TingcoApplicationXML();
    TingcoApplications tingcoApps = new TingcoApplications();
    ApplicationDAO applicationDAO = new ApplicationDAO();
    CountryDAO countryDAO = new CountryDAO();
    UserDAO userDAO = new UserDAO();
    GroupDAO groupDAO = new GroupDAO();
    PricePlanDAO pricePlanDAO = new PricePlanDAO();
    ProductsDAO productsDAO = new ProductsDAO();
    GregorianCalendar currentDateTime = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of ApplicationsResource */
    public ApplicationsResource() {
    }

    /**
     * Application_Add
     * @param applicationName
     * @param appDesc
     * @param groupID
     * @param isEnabled
     * @param activeFromDate
     * @param activeToDate
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/add/rest/{rest}/applicationname/{applicationname}{applicationdescription:(/applicationdescription/[^/]+?)?}/groupid/{groupid}{isenabled:(/isenabled/[^/]+?)?}{activefromdate:(/activefromdate/[^/]+?)?}{activetodate:(/activetodate/[^/]+?)?}")
    @RESTDoc(methodName = "Application_Add", desc = "Used to create a new Application", req_Params = {"ApplicationName;String", "GroupID;UUID"}, opt_Params = {"ApplicationDescription;String", "IsEnabled;int", "ActiveFromDate;datetime", "ActiveToDate;datetime"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoApplications getXml_Add(@PathParam("applicationname") String applicationName, @PathParam("applicationdescription") String appDesc, @PathParam("groupid") String groupID, @PathParam("isenabled") String isEnabled, @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate) throws DatatypeConfigurationException {
        return createApplication(applicationName, appDesc, groupID, isEnabled, activeFromDate, activeToDate);
    }

    /**
     * Application_Add
     * @param applicationName
     * @param appDesc
     * @param groupID
     * @param isEnabled
     * @param activeFromDate
     * @param activeToDate
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/add/json/{json}/applicationname/{applicationname}{applicationdescription:(/applicationdescription/[^/]+?)?}/groupid/{groupid}{isenabled:(/isenabled/[^/]+?)?}{activefromdate:(/activefromdate/[^/]+?)?}{activetodate:(/activetodate/[^/]+?)?}")
    public TingcoApplications getJson_Add(@PathParam("applicationname") String applicationName, @PathParam("applicationdescription") String appDesc, @PathParam("groupid") String groupID, @PathParam("isenabled") String isEnabled, @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate) throws DatatypeConfigurationException {
        return createApplication(applicationName, appDesc, groupID, isEnabled, activeFromDate, activeToDate);
    }

    /**
     * Application_Add
     * @param applicationName
     * @param appDesc
     * @param groupID
     * @param isEnabled
     * @param activeFromDate
     * @param activeToDate
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @POST
    @Produces("application/xml")
    @Path("/add/rest/{rest}/applicationname/{applicationname}{applicationdescription:(/applicationdescription/[^/]+?)?}/groupid/{groupid}{isenabled:(/isenabled/[^/]+?)?}{activefromdate:(/activefromdate/[^/]+?)?}{activetodate:(/activetodate/[^/]+?)?}")
    public TingcoApplications postXml_add(@PathParam("applicationname") String applicationName, @PathParam("applicationdescription") String appDesc, @PathParam("groupid") String groupID, @PathParam("isenabled") String isEnabled, @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate) throws DatatypeConfigurationException {
        return createApplication(applicationName, appDesc, groupID, isEnabled, activeFromDate, activeToDate);
    }

    /**
     * Application_Add
     * @param applicationName
     * @param appDesc
     * @param groupID
     * @param isEnabled
     * @param activeFromDate
     * @param activeToDate
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @POST
    @Produces("application/json")
    @Path("/add/json/{json}/applicationname/{applicationname}{applicationdescription:(/applicationdescription/[^/]+?)?}/groupid/{groupid}{isenabled:(/isenabled/[^/]+?)?}{activefromdate:(/activefromdate/[^/]+?)?}{activetodate:(/activetodate/[^/]+?)?}")
    public TingcoApplications postJson_add(@PathParam("applicationname") String applicationName, @PathParam("applicationdescription") String appDesc, @PathParam("groupid") String groupID, @PathParam("isenabled") String isEnabled, @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate) throws DatatypeConfigurationException {
        return createApplication(applicationName, appDesc, groupID, isEnabled, activeFromDate, activeToDate);
    }

    /**
     * Application_Delete
     * @param applicationId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/applicationid/{applicationid}")
    @RESTDoc(methodName = "Application_Delete", desc = "Delete's the specified Application", req_Params = {"ApplicationID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoApplications getXml_delete(@PathParam("applicationid") String applicationId) {
        return deleteApplication(applicationId);
    }

    /**
     * Application_Delete
     * @param applicationId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/delete/json/{json}/applicationid/{applicationid}")
    public TingcoApplications getJson_delete(@PathParam("applicationid") String applicationId) {
        return deleteApplication(applicationId);
    }

    /**
     * GetApplicationModules
     * @param groupid
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getapplicationmodules/rest/{rest}/groupid/{groupid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetApplicationModules", desc = "Used to Get ApplicationModules", req_Params = {"GroupID;string", "CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoApplications getXml_getApplicationModules(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getApplicationModules(groupId, countryId);
    }

    /**
     * GetApplicationModules
     * @param groupid
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getapplicationmodules/json/{json}/groupid/{groupid}/countryid/{countryid}")
    public TingcoApplications getJson_getApplicationModules(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getApplicationModules(groupId, countryId);
    }

    /**
     * GetApplicationPackages
     * @param groupid
     * @param countryid
     * @param applicationsolutionid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getapplicationpackages/rest/{rest}/groupid/{groupid}/countryid/{countryid}{applicationsolutionid:(/applicationsolutionid/[^/]+?)?}")
    @RESTDoc(methodName = "GetSolutionPackages", desc = "Used to Get SolutionPackages", req_Params = {"GroupID;string", "CountryID;int", "ApplicationSolutionID;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoApplications getXml_getApplicationPackages(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("applicationsolutionid") String applicationSolutionId) {
        return getApplicationPackages(groupId, countryId, applicationSolutionId);
    }

    /**
     * GetApplicationPackages
     * @param groupid
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getapplicationpackages/json/{json}/groupid/{groupid}/countryid/{countryid}{applicationsolutionid:(/applicationsolutionid/[^/]+?)?}")
    public TingcoApplications getJson_getApplicationPackages(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("applicationsolutionid") String applicationSolutionId) {
        return getApplicationPackages(groupId, countryId, applicationSolutionId);
    }

    /**
     * GetApplicationSolutions
     * @param groupid
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getapplicationsolutions/rest/{rest}/groupid/{groupid}/countryid/{countryid}/applicationid/{applicationid}")
    @RESTDoc(methodName = "GetApplicationSolutions", desc = "Used to Get ApplicationSolutions", req_Params = {"GroupID;string", "CountryID;int", "ApplicationID;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoApplications getXml_getApplicationSolutions(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("applicationid") String applicationId) throws DatatypeConfigurationException {
        return getApplicationSolutions(groupId, countryId, applicationId);
    }

    /**
     * GetApplicationSolutions
     * @param groupid
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getapplicationsolutions/json/{json}/groupid/{groupid}/countryid/{countryid}/applicationid/{applicationid}")
    public TingcoApplications getJson_getApplicationSolutions(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("applicationid") String applicationId) throws DatatypeConfigurationException {
        return getApplicationSolutions(groupId, countryId, applicationId);
    }

    /**
     * GetAgreementsForGroup
     * @param GroupId
     * @param countryId
     * @param agreementNumber
     * @return
     *
     */
    @GET
    @Produces("application/xml")
    @Path("/getagreementsforgroup/rest/{rest}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}{agreementnumber:(/agreementnumber/[^/]+?)?}")
    @RESTDoc(methodName = "GetAgreementsForGroup", desc = "Used to Get AgreementsForGroup", req_Params = {"GroupID;UUID", "searchstring;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoApplications getXml_getAgreementsForGroup(@PathParam("groupid") String groupId, @PathParam("searchstring") String searchString, @PathParam("agreementnumber") String agreementnumber) throws DatatypeConfigurationException {
        return getAgreementsForGroup(groupId, searchString, agreementnumber);
    }

    /**
     * GetAgreementsForGroup
     * @param GroupId
     * @param countryId
     * @param agreementNumber 
     * @return
     *
     */
    @GET
    @Produces("application/json")
    @Path("/getagreementsforgroup/json/{json}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}{agreementnumber:(/agreementnumber/[^/]+?)?}")
    @RESTDoc(methodName = "GetAgreementsForGroup", desc = "Used to Get AgreementsForGroup", req_Params = {"GroupID;UUID", "searchstring;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoApplications getJson_getAgreementsForGroup(@PathParam("groupid") String groupId, @PathParam("searchstring") String searchString, @PathParam("agreementnumber") String agreementnumber) throws DatatypeConfigurationException {
        return getAgreementsForGroup(groupId, searchString, agreementnumber);
    }

    /**
     * AddTrialforSolutionPackage
     * @param groupid
     * @param applicationpackageid
     * @param agreementid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/addtrialforsolutionpackage/rest/{rest}/groupid/{groupid}/applicationpackageid/{applicationpackageid}/agreementid/{agreementid}")
    @RESTDoc(methodName = "AddTrialforSolutionPackage", desc = "Used to Add Trial SolutionPackage", req_Params = {"ApplicationPackageId;string", "AgreementId;string", "GroupId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoApplications getXml_addTrialforSolutionPackage(@PathParam("groupid") String groupId, @PathParam("applicationpackageid") String applicationPackageId, @PathParam("agreementid") String agreementId) {
        return addTrialforSolutionPackage(groupId, applicationPackageId, agreementId);
    }

    /**
     * AddTrialforSolutionPackage
     * @param groupid
     * @param applicationpackageid
     * @param agreementid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/addtrialforsolutionpackage/json/{json}/groupid/{groupid}/applicationpackageid/{applicationpackageid}/agreementid/{agreementid}")
    public TingcoApplications getJson_addTrialforSolutionPackage(@PathParam("groupid") String groupId, @PathParam("applicationpackageid") String applicationPackageId, @PathParam("agreementid") String agreementId) {
        return addTrialforSolutionPackage(groupId, applicationPackageId, agreementId);
    }

    /**
     * AddOrderforSolutionPackage
     * @param groupid
     * @param applicationpackageid
     * @param agreementid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/addorderforsolutionpackage/rest/{rest}/groupid/{groupid}/applicationpackageid/{applicationpackageid}/agreementid/{agreementid}")
    @RESTDoc(methodName = "AddOrderforSolutionPackage", desc = "Used to Add Order SolutionPackage", req_Params = {"ApplicationPackageId;string", "AgreementId;string", "GroupId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoApplications getXml_addOrderforSolutionPackage(@PathParam("groupid") String groupId, @PathParam("applicationpackageid") String applicationPackageId, @PathParam("agreementid") String agreementId) {
        return addOrderforSolutionPackage(groupId, applicationPackageId, agreementId);
    }

    /**
     * AddOrderforSolutionPackage
     * @param groupid
     * @param applicationpackageid
     * @param agreementid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/addorderforsolutionpackage/json/{json}/groupid/{groupid}/applicationpackageid/{applicationpackageid}/agreementid/{agreementid}")
    public TingcoApplications getJson_addOrderforSolutionPackage(@PathParam("groupid") String groupId, @PathParam("applicationpackageid") String applicationPackageId, @PathParam("agreementid") String agreementId) {
        return addOrderforSolutionPackage(groupId, applicationPackageId, agreementId);
    }

    /**
     * CancelOrderforSolutionPackage
     * @param groupid
     * @param applicationpackageid
     * @param agreementid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/cancelorderforsolutionpackage/rest/{rest}/groupid/{groupid}/applicationpackageid/{applicationpackageid}/agreementid/{agreementid}")
    @RESTDoc(methodName = "CancelOrderforSolutionPackage", desc = "Used to Cancel Order SolutionPackage", req_Params = {"ApplicationPackageId;string", "AgreementId;string", "GroupId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoApplications getXml_cancelOrderforSolutionPackage(@PathParam("groupid") String groupId, @PathParam("applicationpackageid") String applicationPackageId, @PathParam("agreementid") String agreementId) {
        return cancelOrderforSolutionPackage(groupId, applicationPackageId, agreementId);
    }

    /**
     * CancelOrderforSolutionPackage
     * @param groupid
     * @param applicationpackageid
     * @param agreementid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/cancelorderforsolutionpackage/json/{json}/groupid/{groupid}/applicationpackageid/{applicationpackageid}/agreementid/{agreementid}")
    public TingcoApplications getJson_cancelOrderforSolutionPackage(@PathParam("groupid") String groupId, @PathParam("applicationpackageid") String applicationPackageId, @PathParam("agreementid") String agreementId) {
        return cancelOrderforSolutionPackage(groupId, applicationPackageId, agreementId);
    }

    /**
     * GetAgreements
     * @param groupid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getagreements/rest/{rest}/groupid/{groupid}")
    @RESTDoc(methodName = "GetAgreements", desc = "Used to Get Agreements", req_Params = {"GroupId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoApplications getXml_getAgreements(@PathParam("groupid") String groupId) {
        return getAgreements(groupId);
    }

    /**
     * GetAgreements
     * @param groupid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getagreements/json/{json}/groupid/{groupid}")
    @RESTDoc(methodName = "GetAgreements", desc = "Used to Get Agreements", req_Params = {"GroupId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoApplications getJson_getAgreements(@PathParam("groupid") String groupId) {
        return getAgreements(groupId);
    }

    /**
     * Application_Delete
     * @param applicationId
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/applicationid/{applicationid}")
    public TingcoApplications postXml_delete(@PathParam("applicationid") String applicationId) {
        return deleteApplication(applicationId);
    }

    /**
     * Application_Delete
     * @param applicationId
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/delete/json/{json}/applicationid/{applicationid}")
    public TingcoApplications postJson_delete(@PathParam("applicationid") String applicationId) {
        return deleteApplication(applicationId);
    }

    /**
     * Application_GetInfo
     * @param applicationId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/get/rest/{rest}/applicationid/{applicationid}")
    @RESTDoc(methodName = "Application_GetInfo", desc = "Return's the application information", req_Params = {"ApplicationID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoApplications getXml(@PathParam("applicationid") String applicationId) {
        try {
            return getApplicationInfo(applicationId);
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        return null;
    }

    /**
     * Application_GetInfo
     * @param applicationId
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/get/rest/{rest}/applicationid/{applicationid}")
    public TingcoApplications postXml(@PathParam("applicationid") String applicationId) {
        try {
            return getApplicationInfo(applicationId);
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        return null;
    }

    /**
     * Application_GetInfo
     * @param applicationId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/get/json/{json}/applicationid/{applicationid}")
    public TingcoApplications getJSON(@PathParam("applicationid") String applicationId) {
        try {
            return getApplicationInfo(applicationId);
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        return null;
    }

    /**
     * Application_GetInfo
     * @param applicationId
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/get/json/{json}/applicationid/{applicationid}")
    public TingcoApplications postJSON(@PathParam("applicationid") String applicationId) {
        try {
            return getApplicationInfo(applicationId);
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        return null;
    }

    /**
     * Application_Update
     * @param applicationId
     * @param applicationName
     * @param appDesc
     * @param groupID
     * @param isEnabled
     * @param activeFromDate
     * @param activeToDate
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/update/rest/{rest}/applicationid/{applicationid}{applicationname:(/applicationname/[^/]+?)?}{applicationdescription:(/applicationdescription/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}{isenabled:(/isenabled/[^/]+?)?}{activefromdate:(/activefromdate/[^/]+?)?}{activetodate:(/activetodate/[^/]+?)?}")
    @RESTDoc(methodName = "Application_Update", desc = "Used to update teh specified Application", req_Params = {"ApplicationID;UUID"}, opt_Params = {"ApplicationName;String", "ApplicationDescription;String", "IsEnabled;int", "ActiveFromDate;datetime", "ActiveToDate;datetime"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoApplications getXml_Update(@PathParam("applicationid") String applicationId, @PathParam("applicationname") String applicationName, @PathParam("applicationdescription") String appDesc, @PathParam("groupid") String groupID, @PathParam("isenabled") String isEnabled, @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate) {
        return updateApplication(applicationId, applicationName, appDesc, groupID, isEnabled, activeFromDate, activeToDate);
    }

    /**
     * Application_Update
     * @param applicationId
     * @param applicationName
     * @param appDesc
     * @param groupID
     * @param isEnabled
     * @param activeFromDate
     * @param activeToDate
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/update/json/{json}/applicationid/{applicationid}{applicationname:(/applicationname/[^/]+?)?}{applicationdescription:(/applicationdescription/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}{isenabled:(/isenabled/[^/]+?)?}{activefromdate:(/activefromdate/[^/]+?)?}{activetodate:(/activetodate/[^/]+?)?}")
    public TingcoApplications getJson_Update(@PathParam("applicationid") String applicationId, @PathParam("applicationname") String applicationName, @PathParam("applicationdescription") String appDesc, @PathParam("groupid") String groupID, @PathParam("isenabled") String isEnabled, @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate) {
        return updateApplication(applicationId, applicationName, appDesc, groupID, isEnabled, activeFromDate, activeToDate);
    }

    /**
     * Application_Update
     * @param applicationId
     * @param applicationName
     * @param appDesc
     * @param groupID
     * @param isEnabled
     * @param activeFromDate
     * @param activeToDate
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/update/rest/{rest}/applicationid/{applicationid}{applicationname:(/applicationname/[^/]+?)?}{applicationdescription:(/applicationdescription/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}{isenabled:(/isenabled/[^/]+?)?}{activefromdate:(/activefromdate/[^/]+?)?}{activetodate:(/activetodate/[^/]+?)?}")
    public TingcoApplications postXml_Update(@PathParam("applicationid") String applicationId, @PathParam("applicationname") String applicationName, @PathParam("applicationdescription") String appDesc, @PathParam("groupid") String groupID, @PathParam("isenabled") String isEnabled, @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate) {
        return updateApplication(applicationId, applicationName, appDesc, groupID, isEnabled, activeFromDate, activeToDate);
    }

    /**
     * Application_Update
     * @param applicationId
     * @param applicationName
     * @param appDesc
     * @param groupID
     * @param isEnabled
     * @param activeFromDate
     * @param activeToDate
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/update/json/{json}/applicationid/{applicationid}{applicationname:(/applicationname/[^/]+?)?}{applicationdescription:(/applicationdescription/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}{isenabled:(/isenabled/[^/]+?)?}{activefromdate:(/activefromdate/[^/]+?)?}{activetodate:(/activetodate/[^/]+?)?}")
    public TingcoApplications postJson_Update(@PathParam("applicationid") String applicationId, @PathParam("applicationname") String applicationName, @PathParam("applicationdescription") String appDesc, @PathParam("groupid") String groupID, @PathParam("isenabled") String isEnabled, @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate) {
        return updateApplication(applicationId, applicationName, appDesc, groupID, isEnabled, activeFromDate, activeToDate);
    }

    /**
     * GetAllApplications
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/rest/{rest}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetAllApplications", desc = "Get All the Applications by CountryID", req_Params = {"CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoApplications getXml_All(@PathParam("searchstring") String searchString, @PathParam("countryid") int countryId) {
        return getAllApplicationsByCountryId(searchString, countryId);
    }

    /**
     * GetAllApplications
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/json/{json}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoApplications getJson_All(@PathParam("searchstring") String searchString, @PathParam("countryid") int countryId) {
        return getAllApplicationsByCountryId(searchString, countryId);
    }

    /**
     * GetAllApplications
     * @param countryId
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/rest/{rest}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoApplications postXml_All(@PathParam("searchstring") String searchString, @PathParam("countryid") int countryId) {
        return getAllApplicationsByCountryId(searchString, countryId);
    }

    /**
     * GetAllApplications
     * @param countryId
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/json/{json}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoApplications postJson_All(@PathParam("searchstring") String searchString, @PathParam("countryid") int countryId) {
        return getAllApplicationsByCountryId(searchString, countryId);
    }

    /**
     * GetSystemMessagesByApplicationId
     * @param applicationId
     * @param countryId
     * @param tokenId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getsystemmessages/rest/{rest}/applicationid/{applicationid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetSystemMessagesByApplicationId", desc = "Get Top Most SystemMessage within the ActiveDate", req_Params = {"ApplicationID;UUID,CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoApplications getXml_SystemMessages(@PathParam("applicationid") String applicationId, @PathParam("countryid") int countryId) {
        return getSystemMessagesByApplicationId(applicationId, countryId);
    }

    /**
     * GetSystemMessagesByApplicationIds
     * @param applicationId
     * @param countryId
     * @param tokenId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getsystemmessages/json/{json}/applicationid/{applicationid}/countryid/{countryid}")
    public TingcoApplications getJson_SystemMessages(@PathParam("applicationid") String applicationId, @PathParam("countryid") int countryId) {
        return getSystemMessagesByApplicationId(applicationId, countryId);
    }

    /**
     * GetAllApplicationSolution
     * @param applicationId
     * @param countryId
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getallapplicationsolution/rest/{rest}/groupid/{groupid}/countryid/{countryid}/applicationid/{applicationid}")
    @RESTDoc(methodName = "GetAllApplicationSolution", desc = "Get AllApplicationSolution", req_Params = {"GroupId;string,ApplicationID;UUID,CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoApplications getXml_AllApplicationSolution(@PathParam("groupid") String groupid, @PathParam("countryid") int countryId, @PathParam("applicationid") String applicationid) {
        return getAllApplicationSolution(groupid, countryId, applicationid);
    }

    /**
     * GetAllApplicationSolution
     * @param applicationId
     * @param countryId
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getallapplicationsolution/json/{json}/groupid/{groupid}/countryid/{countryid}/applicationid/{applicationid}")
    public TingcoApplications getJson_AllApplicationSolution(@PathParam("groupid") String groupid, @PathParam("countryid") int countryId, @PathParam("applicationid") String applicationid) {
        return getAllApplicationSolution(groupid, countryId, applicationid);
    }

    /**
     * GetApplicationModules
     * @param applicationId
     * @param countryId
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getapplicationmodules/rest/{rest}/applicationsolutionid/{applicationsolutionid}/countryid/{countryid}/groupid/{groupid}")
    @RESTDoc(methodName = "GetApplicationModules", desc = "Get ApplicationModules", req_Params = {"GroupId;string,ApplicationSolutionID;UUID,CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoApplications getXml_ApplicationModules(@PathParam("applicationsolutionid") String applicationsolutionid, @PathParam("countryid") int countryId, @PathParam("groupid") String groupid, @PathParam("applicationid") String applicationId) {
        return getApplicationModulesBySolutionId(applicationsolutionid, countryId, groupid);
    }

    /**
     * GetApplicationModules
     * @param applicationId
     * @param countryId
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getapplicationmodules/json/{json}/applicationsolutionid/{applicationsolutionid}/countryid/{countryid}/groupid/{groupid}")
    public TingcoApplications getJson_ApplicationModules(@PathParam("applicationsolutionid") String applicationsolutionid, @PathParam("countryid") int countryId, @PathParam("groupid") String groupid, @PathParam("applicationid") String applicationId) {
        return getApplicationModulesBySolutionId(applicationsolutionid, countryId, groupid);
    }

    /**
     * GetAllApplicationModules
     * @param applicationId
     * @param countryId
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getallapplicationmodules/rest/{rest}/applicationid/{applicationid}/countryid/{countryid}/groupid/{groupid}")
    @RESTDoc(methodName = "GetAllApplicationModules", desc = "Get AllApplicationModules", req_Params = {"GroupId;string,ApplicationID;UUID,CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoApplications getXml_getAllApplicationModules(@PathParam("countryid") int countryId, @PathParam("groupid") String groupid, @PathParam("applicationid") String applicationId) {
        return getAllApplicationModules(applicationId, countryId, groupid);
    }

    /**
     * GetAllApplicationModules
     * @param applicationId
     * @param countryId
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getallapplicationmodules/json/{json}/applicationid/{applicationid}/countryid/{countryid}/groupid/{groupid}")
    public TingcoApplications getJson_getAllApplicationModules(@PathParam("countryid") int countryId, @PathParam("groupid") String groupid, @PathParam("applicationid") String applicationId) {
        return getAllApplicationModules(applicationId, countryId, groupid);
    }

    /**
     * GetAgreementDetails
     * @param countryid
     * @param agreementSearchString
     * @param organizationSearchString
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getagreementdetails/rest/{rest}/countryid/{countryid}{agreementsearchstring:(/agreementsearchstring/[^/]+?)?}{organizationsearchstring:(/organizationsearchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetAgreementDetails", desc = "Used to Get Agreement Details", req_Params = {"CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoApplications getXml_GetAgreementDetails(@PathParam("countryid") Integer countryid, @PathParam("agreementsearchstring") String agreementSearchString, @PathParam("organizationsearchstring") String organizationSearchString) {
        return getAgreementDetails(countryid, agreementSearchString, organizationSearchString);
    }

    /**
     * GetAgreementDetails
     * @param countryid
     * @param agreementSearchString
     * @param organizationSearchString
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getagreementdetails/json/{json}/countryid/{countryid}{agreementsearchstring:(/agreementsearchstring/[^/]+?)?}{organizationsearchstring:(/organizationsearchstring/[^/]+?)?}")
    public TingcoApplications getJson_GetAgreementDetails(@PathParam("countryid") Integer countryid, @PathParam("agreementsearchstring") String agreementSearchString, @PathParam("organizationsearchstring") String organizationSearchString) {
        return getAgreementDetails(countryid, agreementSearchString, organizationSearchString);
    }

    /**
     * GetAgreementDetailsForPopup
     * @param countryid
     * @param searchString
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getagreementdetailsforpopup/rest/{rest}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetAgreementDetailsForPopup", desc = "Used to Get Agreement Details For Popup", req_Params = {"CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoApplications getXml_GetAgreementDetailsForPopup(@PathParam("countryid") Integer countryid, @PathParam("searchstring") String searchString) {
        return getAgreementDetailsForPopup(countryid, searchString);
    }

    /**
     * GetAgreementDetailsForPopup
     * @param countryid
     * @param searchString
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getagreementdetailsforpopup/json/{json}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoApplications getJson_GetAgreementDetailsForPopup(@PathParam("countryid") Integer countryid, @PathParam("searchstring") String searchString) {
        return getAgreementDetailsForPopup(countryid, searchString);
    }

    /**
     * GetAgreementInfoDetails
     * @param countryid
     * @param agreementId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getagreementinfodetails/rest/{rest}/countryid/{countryid}/agreementid/{agreementid}")
    @RESTDoc(methodName = "GetAgreementInfoDetails", desc = "Used to Get Agreement Info Details", req_Params = {"CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoApplications getXml_GetAgreementInfoDetails(@PathParam("countryid") Integer countryid, @PathParam("agreementid") String agreementId) {
        return getAgreementInfoDetails(countryid, agreementId);
    }

    /**
     * GetAgreementInfoDetails
     * @param countryid
     * @param agreementId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getagreementinfodetails/json/{json}/countryid/{countryid}/agreementid/{agreementid}")
    public TingcoApplications getJson_GetAgreementInfoDetails(@PathParam("countryid") Integer countryid, @PathParam("agreementid") String agreementId) {
        return getAgreementInfoDetails(countryid, agreementId);
    }

    /**
     * GetAgreementTypes
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getagreementtypes/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetAgreementTypes", desc = "Used to Get AgreementTypes", req_Params = {"CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoApplications getXml_GetAgreementTypes(@PathParam("countryid") Integer countryid) {
        return getAgreementTypes(countryid);
    }

    /**
     * GetAgreementTypes
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getagreementtypes/json/{json}/countryid/{countryid}")
    public TingcoApplications getJson_GetAgreementTypes(@PathParam("countryid") Integer countryid) {
        return getAgreementTypes(countryid);
    }

    /**
     * GetAgreementStatus
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getagreementstatus/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetAgreementStatus", desc = "Used to Get AgreementStatus", req_Params = {"CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoApplications getXml_GetAgreementStatus(@PathParam("countryid") Integer countryid) {
        return getAgreementStatus(countryid);
    }

    /**
     * GetAgreementStatus
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getagreementstatus/json/{json}/countryid/{countryid}")
    public TingcoApplications getJson_GetAgreementStatus(@PathParam("countryid") Integer countryid) {
        return getAgreementStatus(countryid);
    }

    /**
     * UpdateAgreementInfoDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/updateagreementinfodetails/rest/{rest}")
    public TingcoApplications postXml_UpdateAgreementInfoDetails(String content) {
        return updateAgreementInfoDetails(content);
    }

    /**
     * UpdateAgreementInfoDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/updateagreementinfodetails/json/{json}")
    public TingcoApplications postJsonUpdateAgreementInfoDetails(String content) {
        return updateAgreementInfoDetails(content);
    }

    /**
     * AddAgreementInfoDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addagreementinfodetails/rest/{rest}")
    public TingcoApplications postXml_addAgreementInfoDetails(String content) {
        return addAgreementInfoDetails(content);
    }

    /**
     * AddAgreementInfoDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addagreementinfodetails/json/{json}")
    public TingcoApplications postJson_addAgreementInfoDetails(String content) {
        return addAgreementInfoDetails(content);
    }

    /**
     * GetAgreementroleDetails
     * @param countryId
     * @param AgreementId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getagreementroledetails/rest/{rest}/agreementid/{agreementid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetAgreementroleDetails", desc = "Get Agreementrole Details", req_Params = {"AgreementId;UUID,CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoApplications getXml_getAgreementRoleDetails(@PathParam("agreementid") String agreementId, @PathParam("countryid") int countryId) {
        return getAgreementRoleDetails(agreementId, countryId);
    }

    /**
     * GetAgreementroleDetails
     * @param countryId
     * @param AgreementId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getagreementroledetails/json/{json}/agreementid/{agreementid}/countryid/{countryid}")
    public TingcoApplications getJson_getAgreementRoleDetails(@PathParam("agreementid") String agreementId, @PathParam("countryid") int countryId) {
        return getAgreementRoleDetails(agreementId, countryId);
    }

    /**
     * DeleteAgreementRoles
     * @param objectId
     * @param AgreementId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deleteagreementrole/rest/{rest}/agreementid/{agreementid}/objectid/{objectid}")
    @RESTDoc(methodName = "DeleteAgreementRoles", desc = "Delete AgreementRoles", req_Params = {"AgreementId;UUID,ObjectID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoApplications getXml_deleteAgreementRoles(@PathParam("agreementid") String agreementId, @PathParam("objectid") String objectId) {
        return deleteAgreementRoles(agreementId, objectId);
    }

    /**
     * DeleteAgreementRoles
     * @param objectId
     * @param AgreementId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deleteagreementrole/json/{json}/agreementid/{agreementid}/objectid/{objectid}")
    public TingcoApplications getJson_deleteAgreementRoles(@PathParam("agreementid") String agreementId, @PathParam("objectid") String objectId) {
        return deleteAgreementRoles(agreementId, objectId);
    }

    /**
     * AddAgreementroles
     * @param countryId
     * @param AgreementId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/addagreementroles/rest/{rest}/agreementid/{agreementid}/objectid/{objectid}/objectname/{objectname}/agreementroletypeid/{agreementroletypeid}/activefromdate/{activefromdate}/activetodate/{activetodate}/lastupdatedbyuserid/{lastupdatedbyuserid}")
    @RESTDoc(methodName = "GetAgreementroleDetails", desc = "Get Agreementrole Details", req_Params = {"AgreementId;UUID,CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoApplications getXml_addAgreementRoles(@PathParam("agreementid") String agreementId, @PathParam("objectid") String objectId, @PathParam("objectname") String objectName, @PathParam("agreementroletypeid") String agreementRoleTypeId, @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate, @PathParam("lastupdatedbyuserid") String lastUpdatedByUserId) {
        return addagreementroles(agreementId, objectId, objectName, agreementRoleTypeId, activeFromDate, activeToDate, lastUpdatedByUserId);
    }

    /**
     * AddAgreementroles
     * @param countryId
     * @param AgreementId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/addagreementroles/json/{json}/agreementid/{agreementid}/objectid/{objectid}/objectname/{objectname}/agreementroletypeid/{agreementroletypeid}/activefromdate/{activefromdate}/activetodate/{activetodate}/lastupdatedbyuserid/{lastupdatedbyuserid}")
    public TingcoApplications getJson_addAgreementRoles(@PathParam("agreementid") String agreementId, @PathParam("objectid") String objectId, @PathParam("objectname") String objectName, @PathParam("agreementroletypeid") String agreementRoleTypeId, @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate, @PathParam("lastupdatedbyuserid") String lastUpdatedByUserId) {
        return addagreementroles(agreementId, objectId, objectName, agreementRoleTypeId, activeFromDate, activeToDate, lastUpdatedByUserId);
    }

    /**
     * GetAgreementrolesTypes
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getagreementrolestypes/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetAgreementrolesTypes", desc = "Get AgreementrolesTypes", req_Params = {"CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoApplications getXml_getAgreementRoleTypes(@PathParam("countryid") int countryId) {
        return getAgreementRolesTypes(countryId);
    }

    /**
     * GetAgreementrolesTypes
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getagreementrolestypes/json/{json}/countryid/{countryid}")
    public TingcoApplications getJson_getAgreementRoleTypes(@PathParam("countryid") int countryId) {
        return getAgreementRolesTypes(countryId);
    }

    /**
     * GetAgreementItemTypes
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getagreementitemtypes/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetAgreementItemTypes", desc = "GetAgreementItemTypes", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoApplications getXml_GetAgreementItemTypes(@PathParam("countryid") int countryId) {
        return getAgreementItemTypes(countryId);
    }

    /**
     * GetAgreementItemTypes
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getagreementitemtypes/json/{json}/countryid/{countryid}")
    public TingcoApplications getJson_GetAgreementItemTypes(@PathParam("countryid") int countryId) {
        return getAgreementItemTypes(countryId);
    }

    @GET
    @Produces("application/xml")
    @Path("/getmaxsectionnumber/rest/{rest}/agreementid/{agreementid}")
    @RESTDoc(methodName = "GetMaxSectionNumber", desc = "Get MaxSection Number", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoApplications getXml_GetMaxSectionNuember(@PathParam("agreementid") String agreementId) {
        return getMaxSectionNumber(agreementId);
    }

    @GET
    @Produces("application/json")
    @Path("/getmaxsectionnumber/json/{json}/agreementid/{agreementid}")
    @RESTDoc(methodName = "GetMaxSectionNumber", desc = "Get MaxSection Number", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoApplications getJson_GetMaxSectionNuember(@PathParam("agreementid") String agreementId) {
        return getMaxSectionNumber(agreementId);
    }

    @GET
    @Produces("application/xml")
    @Path("/getagreementitembyid/rest/{rest}/agreementitemid/{agreementitemid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetAgreementItemById", desc = "GetAgreementItemById", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoApplications getXml_GetAgreementItemById(@PathParam("agreementitemid") String agreementItemId, @PathParam("countryid") int countryId) {
        return getAgreementItemById(agreementItemId, countryId);
    }

    @GET
    @Produces("application/json")
    @Path("/getagreementitembyid/json/{json}/agreementitemid/{agreementitemid}/countryid/{countryid}")
    public TingcoApplications getJson_GetAgreementItemById(@PathParam("agreementitemid") String agreementItemId, @PathParam("countryid") int countryId) {
        return getAgreementItemById(agreementItemId, countryId);
    }

    @GET
    @Produces("application/xml")
    @Path("/getagreementitemsdetailsbyagreementid/rest/{rest}/agreementid/{agreementid}/countryid/{countryid}{agreementitemid:(/agreementitemid/[^/]+?)?}")
    @RESTDoc(methodName = "GetAgreementItemsDetailsByAgreementID", desc = "GetAgreementItemsDetails By AgreementID", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoApplications getXml_GetAgreementItemsDetailsByAgreementID(@PathParam("agreementid") String agreementId, @PathParam("countryid") int countryId, @PathParam("agreementitemid") String agreementitemId) {
        return getAgreementItemsDetailsByAgreementID(agreementId, countryId, agreementitemId);
    }

    @GET
    @Produces("application/json")
    @Path("/getagreementitemsdetailsbyagreementid/json/{json}/agreementid/{agreementid}/countryid/{countryid}{agreementitemid:(/agreementitemid/[^/]+?)?}")
    public TingcoApplications getJson_GetAgreementItemsDetailsByAgreementID(@PathParam("agreementid") String agreementId, @PathParam("countryid") int countryId, @PathParam("agreementitemid") String agreementitemId) {
        return getAgreementItemsDetailsByAgreementID(agreementId, countryId, agreementitemId);
    }

    /**
     * DeleteAgreementItemById
     * @param agreementItemId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deleteagreementitembyid/rest/{rest}/agreementitemid/{agreementitemid}")
    @RESTDoc(methodName = "DeleteAgreementItemById", desc = "Delete AgreementItem By Id", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoApplications getXml_DeleteAgreementItemById(@PathParam("agreementitemid") String agreementItemId) {
        return deleteAgreementItemById(agreementItemId);
    }

    /**
     * DeleteAgreementItemById
     * @param agreementItemId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deleteagreementitembyid/json/{json}/agreementitemid/{agreementitemid}")
    @RESTDoc(methodName = "GetAgreementItemTypes", desc = "Delete AgreementItem By Id", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoApplications getJson_DeleteAgreementItemById(@PathParam("agreementitemid") String agreementItemId) {
        return deleteAgreementItemById(agreementItemId);
    }

    /**
     * AddAgreementItem
     * @param content
     * @return
     */
    @POST
    @Produces("application/xml")
    @Consumes("application/xml")
    @Path("/addagreementitem/rest/{rest}")
    public TingcoApplications postXml_AddAgreementItem(String content) {
        return addAgreementItem(content);
    }

    /**
     * AddAgreementItem
     * @param content
     * @return
     */
    @POST
    @Produces("application/json")
    @Consumes("application/xml")
    @Path("/addagreementitem/json/{json}")
    public TingcoApplications postJson_AddAgreementItem(String content) {
        return addAgreementItem(content);
    }

    /**
     * UpdateAgreementItemById
     * @param content
     * @return
     */
    @POST
    @Produces("application/xml")
    @Consumes("application/xml")
    @Path("/updateagreementitembyid/rest/{rest}")
    public TingcoApplications postXml_UpdateAgreementItemById(String content) {
        return updateAgreementItemById(content);
    }

    /**
     * UpdateAgreementItemById
     * @param content
     * @return
     */
    @POST
    @Produces("application/json")
    @Consumes("application/xml")
    @Path("/updateagreementitembyid/json/{json}")
    public TingcoApplications postJson_UpdateAgreementItemById(String content) {
        return updateAgreementItemById(content);
    }

    /**
     *
     * @param countryId
     * @param agreementitemId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getagreementitemsbyagreementitemid/rest/{rest}/countryid/{countryid}/agreementitemid/{agreementitemid}")
    @RESTDoc(methodName = "GetAgreementItemsDetailsByAgreementID", desc = "GetAgreementItemsDetails By AgreementID", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoApplications getXml_GetAgreementItemsDetailsByAgreementItemID(@PathParam("countryid") int countryId, @PathParam("agreementitemid") String agreementitemId) {
        return getAgreementItemsDetailsByAgreementItemID(countryId, agreementitemId);
    }

    /**
     *
     * @param countryId
     * @param agreementitemId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getagreementitemsbyagreementitemid/json/{json}/countryid/{countryid}/agreementitemid/{agreementitemid}")
    public TingcoApplications getJson_GetAgreementItemsDetailsByAgreementItemID(@PathParam("countryid") int countryId, @PathParam("agreementitemid") String agreementitemId) {
        return getAgreementItemsDetailsByAgreementItemID(countryId, agreementitemId);
    }

    /**
     * GetAgreementBillingDetails
     * @param agreementitemId
     * @param year
     * @param month
     * @return TingcoApplications
     */
    @GET
    @Produces("application/xml")
    @Path("/getagreementbillingdetails/rest/{rest}/agreementitemid/{agreementitemid}/year/{year}/month/{month}")
    @RESTDoc(methodName = "GetAgreementBillingDetails", desc = "Get AgreementBilling Details", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoApplications getXml_GetAgreementItemsDetailsByAgreementItemID(@PathParam("agreementitemid") String agreementitemId, @PathParam("year") String year, @PathParam("month") String month) {
        return getAgreementBillingDetails(agreementitemId, year, month);
    }

    /**
     * GetAgreementBillingDetails
     * @param agreementitemId
     * @param year
     * @param month
     * @return TingcoApplications
     */
    @GET
    @Produces("application/json")
    @Path("/getagreementbillingdetails/json/{json}/agreementitemid/{agreementitemid}/year/{year}/month/{month}")
    @RESTDoc(methodName = "GetAgreementBillingDetails", desc = "Get AgreementBilling Details", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoApplications getJson_GetAgreementItemsDetailsByAgreementItemID(@PathParam("agreementitemid") String agreementitemId, @PathParam("year") String year, @PathParam("month") String month) {
        return getAgreementBillingDetails(agreementitemId, year, month);
    }

    /**
     * DeleteApplicationEmailTemplates
     * @param applicationEmailTemplateID
     * @return TingcoApplications
     */
    @GET
    @Produces("application/xml")
    @Path("/deleteapplicationemailtemplates/rest/{rest}/applicationemailtemplateid/{applicationemailtemplateid}")
    @RESTDoc(methodName = "DeleteApplicationEmailTemplates", desc = "Delete ApplicationEmailTemplates", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoApplications getXml_DeleteApplicationEmailTemplates(@PathParam("applicationemailtemplateid") String applicationEmailTemplateID) {
        return deleteApplicationEmailTemplates(applicationEmailTemplateID);
    }

    /**
     * DeleteApplicationEmailTemplates
     * @param applicationEmailTemplateID
     * @return TingcoApplications
     */
    @GET
    @Produces("application/json")
    @Path("/deleteapplicationemailtemplates/json/{json}/applicationemailtemplateid /{applicationemailtemplateid}")
    @RESTDoc(methodName = "DeleteApplicationEmailTemplates", desc = "Delete ApplicationEmailTemplates", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoApplications getJson_DeleteApplicationEmailTemplates(@PathParam("applicationemailtemplateid") String applicationEmailTemplateID) {
        return deleteApplicationEmailTemplates(applicationEmailTemplateID);
    }

    /**
     * GetApplicationEmailTemplatesByID
     * @param applicationEmailTemplateID
     * @return TingcoApplications
     */
    @GET
    @Produces("application/xml")
    @Path("/getapplicationemailtemplatesByID/rest/{rest}/applicationemailtemplateid/{applicationemailtemplateid}")
    @RESTDoc(methodName = "GetApplicationEmailTemplatesByID", desc = "Get ApplicationEmailTemplates By ID", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoApplications getXml_getApplicationEmailTemplatesByID(@PathParam("applicationemailtemplateid") String applicationEmailTemplateID) {
        return getApplicationEmailTemplatesByID(applicationEmailTemplateID);
    }

    /**
     * GetApplicationEmailTemplatesByID
     * @param applicationEmailTemplateID
     * @return TingcoApplications
     */
    @GET
    @Produces("application/json")
    @Path("/getapplicationemailtemplatesByID/json/{json}/applicationemailtemplateid /{applicationemailtemplateid}")
    @RESTDoc(methodName = "GetApplicationEmailTemplatesByID", desc = "Get ApplicationEmailTemplates By ID", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoApplications getJson_getApplicationEmailTemplatesByID(@PathParam("applicationemailtemplateid") String applicationEmailTemplateID) {
        return getApplicationEmailTemplatesByID(applicationEmailTemplateID);
    }

    /**
     * GetApplicationEmailTemplatesDetails
     * @param loggededCountryId
     * @param applicationId
     * @param objectCode
     * @param countryId
     * @return TingcoApplications
     */
    @GET
    @Produces("application/xml")
    @Path("/getapplicationemailtemplatesdetails/rest/{rest}/loggedinusercountryid/{loggedinusercountryid}{applicationid :(/applicationid/[^/]+?)?}{objectcode:(/objectcode/[^/]+?)?}{countryid:(/countryid/[^/]+?)?}")
    @RESTDoc(methodName = "GetApplicationEmailTemplatesDetails", desc = "Get ApplicationEmailTemplatesDetails", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoApplications getXML_GetApplicationEmailTemplatesDetails(@PathParam("loggedinusercountryid") String loggededCountryId, @PathParam("applicationid") String applicationId, @PathParam("objectcode") String objectCode, @PathParam("countryid") String countryId) {
        return getApplicationEmailTemplatesDetails(loggededCountryId, applicationId, objectCode, countryId);
    }

    /**
     * GetApplicationEmailTemplatesDetails
     * @param loggededCountryId
     * @param applicationId
     * @param objectCode
     * @param countryId
     * @return TingcoApplications
     */
    @GET
    @Produces("application/json")
    @Path("/getapplicationemailtemplatesdetails/json/{json}/loggedinusercountryid/{loggedinusercountryid}{applicationid :(/applicationid/[^/]+?)?}{objectcode:(/objectcode/[^/]+?)?}{countryid:(/countryid/[^/]+?)?}")
    @RESTDoc(methodName = "GetApplicationEmailTemplatesDetails", desc = "Get ApplicationEmailTemplatesDetails", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoApplications getJson_GetApplicationEmailTemplatesDetails(@PathParam("loggedinusercountryid") String loggededCountryId, @PathParam("applicationid") String applicationId, @PathParam("objectcode") String objectCode, @PathParam("countryid") String countryId) {
        return getApplicationEmailTemplatesDetails(loggededCountryId, applicationId, objectCode, countryId);
    }

    /**
     * AddUpdateApplicationEmailTemplates
     * @param content
     * @return TingcoApplications
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addupdateapplicationemailtemplates/rest/{rest}")
    @RESTDoc(methodName = "AddUpdateApplicationEmailTemplates", desc = "Add Update ApplicationEmailTemplates", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoApplications postXml_Add(String content) {
        return addUpdateApplicationEmailTemplates(content);
    }

    /**
     * AddUpdateApplicationEmailTemplates
     * @param content
     * @return TingcoApplications
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addupdateapplicationemailtemplates/json/{json}")
    @RESTDoc(methodName = "AddUpdateApplicationEmailTemplates", desc = "Add Update ApplicationEmailTemplates", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoApplications postJson_Add(String content) {
        return addUpdateApplicationEmailTemplates(content);
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public ApplicationResource getApplicationResource() {
        return new ApplicationResource();
    }

    private TingcoApplications getApplicationEmailTemplatesDetails(String loggededCountryId, String applicationId, String objectCode, String countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        Transaction tx = null;
        try {

            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.SYSTEM, TCMUtil.DELETE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    boolean falg = false;
                    if (!applicationId.equals("")) {
                        applicationId = applicationId.split("/")[2];
                        falg = true;
                    } else {
                        applicationId = null;
                    }
                    if (!objectCode.equals("")) {
                        objectCode = objectCode.split("/")[2];
                        falg = true;
                    } else {
                        objectCode = null;
                    }
                    if (!countryId.equals("")) {
                        countryId = countryId.split("/")[2];
                        falg = true;
                    } else {
                        countryId = null;
                    }
                    List<ApplicationEmailTemplates> applicationEmailTemplates = applicationDAO.getApplicationEmailTemplatesDetails(session, falg, applicationId, objectCode, countryId, Integer.parseInt(loggededCountryId));
                    if (applicationEmailTemplates.isEmpty()) {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoApps;
                    }
                    Set<String> applicationIds = new HashSet<String>();
                    Set<Integer> countryIds = new HashSet<Integer>();
                    for (ApplicationEmailTemplates applicationEmailTemplates1 : applicationEmailTemplates) {
                        applicationIds.add(applicationEmailTemplates1.getApplications().getApplicationId());
                        countryIds.add(applicationEmailTemplates1.getCountryId());
                    }

                    CountryDAO countryDAO = new CountryDAO();
                    List<Country> countrys = countryDAO.getCountryByIdList(session, new ArrayList<Integer>(countryIds));
                    List<ApplicationTranslations> applicationTranslationses = applicationDAO.getApplicationTranslationsByIds(session, new ArrayList<String>(applicationIds), loggededCountryId);
                    tingcoApps = tingcoAppXML.buildGetApplicationEmailTemplatesDetails(tingcoApps, applicationEmailTemplates, countrys, applicationTranslationses);
                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }
            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            if (tx != null) {
                tx.rollback();
            }
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoApps;
    }

    private TingcoApplications getApplicationEmailTemplatesByID(String applicationEmailTemplateID) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        Transaction tx = null;
        try {

            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.SYSTEM, TCMUtil.DELETE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ApplicationEmailTemplates applicationEmailTemplates = (ApplicationEmailTemplates) session.get(ApplicationEmailTemplates.class, applicationEmailTemplateID);
                    if (applicationEmailTemplates == null) {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoApps;
                    }
                    tingcoApps = tingcoAppXML.buildGetApplicationEmailTemplatesByID(tingcoApps, applicationEmailTemplates);
                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }
            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            if (tx != null) {
                tx.rollback();
            }
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoApps;
    }

    private TingcoApplications addUpdateApplicationEmailTemplates(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        Transaction tx = null;
        se.info24.appjaxb.ApplicationEmailTemplates applicationEmailTemplatesJaxb = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                se.info24.appjaxb.TingcoApplications application = (se.info24.appjaxb.TingcoApplications) JAXBManager.getInstance().unMarshall(content, se.info24.appjaxb.TingcoApplications.class);
                if (application.getApplications().getApplication().isEmpty()) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("XML is not Valid");
                    return tingcoApps;
                }
                if (application.getApplications().getApplication().get(0).getApplicationEmailTemplates() != null) {
                    applicationEmailTemplatesJaxb = application.getApplications().getApplication().get(0).getApplicationEmailTemplates();
                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("XML is not Valid");
                    return tingcoApps;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                if (applicationEmailTemplatesJaxb.getApplicationEmailTemplateID() != null) {
                    hasPermission = getPermission(sessions2.getUserId(), TCMUtil.SYSTEM, TCMUtil.UPDATE);
                } else {
                    hasPermission = getPermission(sessions2.getUserId(), TCMUtil.SYSTEM, TCMUtil.ADD);
                }

                if (hasPermission) {
                    GregorianCalendar gc = new GregorianCalendar();
                    session = HibernateUtil.getSessionFactory().openSession();
                    ApplicationEmailTemplates applicationEmailTemplates = null;

                    List<ApplicationEmailTemplates> emailTemplateses = applicationDAO.getAppEmailTemplatesCheck(session, applicationEmailTemplatesJaxb.getCountryID(), applicationEmailTemplatesJaxb.getApplicationID(), applicationEmailTemplatesJaxb.getObjectCode());
                    if (applicationEmailTemplatesJaxb.getApplicationEmailTemplateID() != null) {
                        if (!emailTemplateses.isEmpty()) {
                            if (!emailTemplateses.get(0).getApplicationEmailTemplateId().equalsIgnoreCase(applicationEmailTemplatesJaxb.getApplicationEmailTemplateID())) {
                                tingcoApps.getMsgStatus().setResponseCode(-1);
                                tingcoApps.getMsgStatus().setResponseText("Data Already exist");
                                return tingcoApps;
                            }
                        }
                        applicationEmailTemplates = (ApplicationEmailTemplates) session.get(ApplicationEmailTemplates.class, applicationEmailTemplatesJaxb.getApplicationEmailTemplateID());
                        if (applicationEmailTemplates == null) {
                            tingcoApps.getMsgStatus().setResponseCode(-1);
                            tingcoApps.getMsgStatus().setResponseText("Data Not Found");
                            return tingcoApps;
                        }
                        Applications applications = (Applications) session.get(Applications.class, applicationEmailTemplatesJaxb.getApplicationID());
                        if (applications != null) {
                            applicationEmailTemplates.setApplications(applications);
                        } else {
                            tingcoApps.getMsgStatus().setResponseCode(-1);
                            tingcoApps.getMsgStatus().setResponseText("Data Not Found");
                            return tingcoApps;
                        }
                        applicationEmailTemplates.setCountryId(Integer.valueOf(applicationEmailTemplatesJaxb.getCountryID()));
                        applicationEmailTemplates.setObjectCode(applicationEmailTemplatesJaxb.getObjectCode());
                        applicationEmailTemplates.setEmailSubject(applicationEmailTemplatesJaxb.getEmailSubject());
                        applicationEmailTemplates.setEmailBody(applicationEmailTemplatesJaxb.getEmailBody());
                        applicationEmailTemplates.setEmailSignature(applicationEmailTemplatesJaxb.getEmailSignature());
                        applicationEmailTemplates.setLastUpdatedByUserId(sessions2.getUserId());
                        applicationEmailTemplates.setUpdatedDate(gc.getTime());

                    } else {
                        applicationEmailTemplates = new ApplicationEmailTemplates();
                        applicationEmailTemplates.setApplicationEmailTemplateId(UUID.randomUUID().toString());
                        Applications applications = (Applications) session.get(Applications.class, applicationEmailTemplatesJaxb.getApplicationID());

                        if (applications != null) {
                            applicationEmailTemplates.setApplications(applications);
                        } else {
                            tingcoApps.getMsgStatus().setResponseCode(-1);
                            tingcoApps.getMsgStatus().setResponseText("Data Not Found");
                            return tingcoApps;
                        }
                        applicationEmailTemplates.setCountryId(Integer.valueOf(applicationEmailTemplatesJaxb.getCountryID()));
                        applicationEmailTemplates.setObjectCode(applicationEmailTemplatesJaxb.getObjectCode());
                        applicationEmailTemplates.setEmailSubject(applicationEmailTemplatesJaxb.getEmailSubject());
                        applicationEmailTemplates.setEmailBody(applicationEmailTemplatesJaxb.getEmailBody());
                        applicationEmailTemplates.setEmailSignature(applicationEmailTemplatesJaxb.getEmailSignature());
                        applicationEmailTemplates.setLastUpdatedByUserId(sessions2.getUserId());
                        applicationEmailTemplates.setUpdatedDate(gc.getTime());
                        applicationEmailTemplates.setCreatedDate(gc.getTime());
                    }

                    tx = session.beginTransaction();
                    session.saveOrUpdate(applicationEmailTemplates);
                    tx.commit();
                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }
            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            if (tx != null) {
                tx.rollback();
            }
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoApps;
    }

    private TingcoApplications deleteApplicationEmailTemplates(String applicationEmailTemplateID) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        Transaction tx = null;
        try {

            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.SYSTEM, TCMUtil.DELETE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ApplicationEmailTemplates applicationEmailTemplates = (ApplicationEmailTemplates) session.get(ApplicationEmailTemplates.class, applicationEmailTemplateID);
                    if (applicationEmailTemplates == null) {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoApps;
                    }
                    tx = session.beginTransaction();
                    session.delete(applicationEmailTemplates);
                    tx.commit();
                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }
            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            if (tx != null) {
                tx.rollback();
            }
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoApps;
    }

    private TingcoApplications getAgreementBillingDetails(String agreementitemId, String year, String month) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        Session ismOperationSession = null;
        try {

            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.AGREEMENTS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ismOperationSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    Agreements agg = (Agreements) session.get(Agreements.class, agreementitemId);
                    if (agg == null) {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoApps;
                    }
                    List<AgreementBillingData> agreementBillingDatas = applicationDAO.getAgreementBillingDetails(ismOperationSession, agreementitemId, month, year);
                    if (agreementBillingDatas.isEmpty()) {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoApps;
                    }

                    tingcoApps = tingcoAppXML.buildGetAgreementBillingDetails(tingcoApps, agreementBillingDatas, agg);

                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }
            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }
        return tingcoApps;
    }

    private TingcoApplications getAgreementItemsDetailsByAgreementItemID(int countryId, String agreementitemId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {

            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.AGREEMENTS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 == null) {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("User TimeZone is not found");
                        return tingcoApps;
                    }
                    String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                    AgreementItems agreementItemses = (AgreementItems) session.get(AgreementItems.class, agreementitemId);
                    if (agreementItemses != null) {
//                        Set<String> agreementItemsIDs = new HashSet<String>();
                        List<String> productVariantIDs = new ArrayList<String>();
                        Set<String> aittIDs = new HashSet<String>();
                        Set<String> astIDs = new HashSet<String>();
                        List<String> userIDs = new ArrayList<String>();
//                        for (AgreementItems ai : agreementItemses) {
//                            agreementItemsIDs.add(agreementItemses.getAgreementItemId());
                        if (agreementItemses.getObjectId() != null) {
                            productVariantIDs.add(agreementItemses.getObjectId());
                        }
                        if (agreementItemses.getAgreementItemTypes() != null) {
                            aittIDs.add(agreementItemses.getAgreementItemTypes().getAgreementItemTypeId());
                        }
                        if (agreementItemses.getAgreementStatuses() != null) {
                            astIDs.add(agreementItemses.getAgreementStatuses().getAgreementStatusId());
                        }
                        if (agreementItemses.getLastUpdatedByUserId() != null) {
                            userIDs.add(agreementItemses.getLastUpdatedByUserId());
                        }
//                        }
                        List<AgreementItems> agreementItemsesOrderByISN = new ArrayList<AgreementItems>();
                        agreementItemsesOrderByISN.add(agreementItemses);
                        List<AgreementItemTypeTranslations> agreementItemTypeTranslationsList = new ArrayList<AgreementItemTypeTranslations>();
                        List<ProductVariantTranslations> productVariantTranslationsList = new ArrayList<ProductVariantTranslations>();
                        List<AgreementStatusTranslations> agreementStatusTranslationsList = new ArrayList<AgreementStatusTranslations>();
                        List<Users2> users2List = new ArrayList<Users2>();
                        if (!userIDs.isEmpty()) {
                            users2List = userDAO.getUser2ListByUserId(userIDs, session);
                        }
                        if (!productVariantIDs.isEmpty()) {
                            productVariantTranslationsList = productsDAO.getProductVariantTranslations(session, productVariantIDs, countryId);
                        }
                        if (!aittIDs.isEmpty()) {
                            agreementItemTypeTranslationsList = applicationDAO.getAgreementItemTypeTranslationsByIDs(session, aittIDs, countryId);
                        }
                        if (!astIDs.isEmpty()) {
                            agreementStatusTranslationsList = applicationDAO.getAgreementStatusTranslationsByStatusIds(session, astIDs, countryId);
                        }
                        tingcoApps = tingcoAppXML.buildGetAgreementItemsDetailsByAgreementID(tingcoApps, agreementItemsesOrderByISN, productVariantTranslationsList, users2List, agreementItemTypeTranslationsList, agreementStatusTranslationsList, timeZoneGMToffset);
                    } else {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("No Data Found");
                        return tingcoApps;
                    }
                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }
            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }
        return tingcoApps;
    }

    private TingcoApplications addAgreementItem(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.AGREEMENTS, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        se.info24.appjaxb.TingcoApplications application = (se.info24.appjaxb.TingcoApplications) JAXBManager.getInstance().unMarshall(content, se.info24.appjaxb.TingcoApplications.class);
                        List<se.info24.appjaxb.AgreementItems> agreementItemseList = application.getAgreementDetails().getAgreement().get(0).getAgreementItems();
                        if (agreementItemseList.isEmpty()) {
                            tingcoApps.getMsgStatus().setResponseCode(-1);
                            tingcoApps.getMsgStatus().setResponseText("Xml have not Data");
                            return tingcoApps;
                        }
                        se.info24.appjaxb.AgreementItems agreementItemseJaxb = agreementItemseList.get(0);
                        List<AgreementItems> agreements = applicationDAO.getAgreementItemsByAgreementIDAndItemSectionNumber(session, agreementItemseJaxb.getAgreementID(), agreementItemseJaxb.getItemSectionNumber());
                        if (!agreements.isEmpty()) {
                            tingcoApps.getMsgStatus().setResponseCode(-1);
                            tingcoApps.getMsgStatus().setResponseText("This section number is already used");
                            return tingcoApps;
                        }
                        AgreementItems ai = new AgreementItems();
                        ai.setAgreementItemId(UUID.randomUUID().toString());
                        if (agreementItemseJaxb.getAgreementItemParentID() != null) {
                            ai.setAgreementItemParentId(agreementItemseJaxb.getAgreementItemParentID());
                        }
                        Object object = session.get(Agreements.class, agreementItemseJaxb.getAgreementID());
                        if (object == null) {
                            tingcoApps.getMsgStatus().setResponseCode(-1);
                            tingcoApps.getMsgStatus().setResponseText("AgreementID is not valid");
                            return tingcoApps;
                        }
                        ai.setAgreements((Agreements) object);
                        object = session.get(AgreementItemTypes.class, agreementItemseJaxb.getAgreementItemTypeID());
                        if (object == null) {
                            tingcoApps.getMsgStatus().setResponseCode(-1);
                            tingcoApps.getMsgStatus().setResponseText("AgreementItemTypeID is not valid");
                            return tingcoApps;
                        }
                        ai.setAgreementItemTypes((AgreementItemTypes) object);

                        object = session.get(AgreementStatuses.class, agreementItemseJaxb.getAgreementStatusID());
                        if (object == null) {
                            tingcoApps.getMsgStatus().setResponseCode(-1);
                            tingcoApps.getMsgStatus().setResponseText("AgreementStatusID is not valid");
                            return tingcoApps;
                        }
                        ai.setAgreementStatuses((AgreementStatuses) object);

                        object = session.get(PricePlans.class, agreementItemseJaxb.getPricePlanID());
                        if (object == null) {
                            tingcoApps.getMsgStatus().setResponseCode(-1);
                            tingcoApps.getMsgStatus().setResponseText("PricePlanID is not valid");
                            return tingcoApps;
                        }
                        ai.setPricePlans((PricePlans) object);

                        ai.setItemSectionNumber(agreementItemseJaxb.getItemSectionNumber());
                        ai.setItemName(agreementItemseJaxb.getItemName());
                        ai.setItemText(agreementItemseJaxb.getItemText());
                        ai.setObjectId(agreementItemseJaxb.getObjectID());

                        ai.setActiveFromDate(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, df.parse(agreementItemseJaxb.getActiveFromDate())));
                        ai.setActiveToDate(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, df.parse(agreementItemseJaxb.getActiveToDate())));
                        ai.setLastUpdatedByUserId(sessions2.getUserId());
                        GregorianCalendar gc = new GregorianCalendar();
                        ai.setUpdatedDate(gc.getTime());
                        ai.setCreatedDate(gc.getTime());

                        if (!applicationDAO.addAgreementItem(session, ai)) {
                            tingcoApps.getMsgStatus().setResponseCode(-1);
                            tingcoApps.getMsgStatus().setResponseText("Error occured");
                            return tingcoApps;
                        }

                    } else {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("User have no TimeZone");
                        return tingcoApps;
                    }
                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }
            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }
        return tingcoApps;
    }

    private TingcoApplications updateAgreementItemById(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.AGREEMENTS, TCMUtil.UPDATE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        se.info24.appjaxb.TingcoApplications application = (se.info24.appjaxb.TingcoApplications) JAXBManager.getInstance().unMarshall(content, se.info24.appjaxb.TingcoApplications.class);
                        List<se.info24.appjaxb.AgreementItems> agreementItemseList = application.getAgreementDetails().getAgreement().get(0).getAgreementItems();
                        if (agreementItemseList.isEmpty()) {
                            tingcoApps.getMsgStatus().setResponseCode(-1);
                            tingcoApps.getMsgStatus().setResponseText("Xml have not Data");
                            return tingcoApps;
                        }
                        se.info24.appjaxb.AgreementItems agreementItemseJaxb = agreementItemseList.get(0);

                        Object objectagreement = applicationDAO.getAgreementItemsByAgreementItemId(agreementItemseJaxb.getAgreementItemID(), session);
                        if (objectagreement == null) {
                            tingcoApps.getMsgStatus().setResponseCode(-1);
                            tingcoApps.getMsgStatus().setResponseText("Data not found");
                            return tingcoApps;
                        }
                        AgreementItems ai = (AgreementItems) objectagreement;
                        Object object = session.get(AgreementItemTypes.class, agreementItemseJaxb.getAgreementItemTypeID());
                        if (object == null) {
                            tingcoApps.getMsgStatus().setResponseCode(-1);
                            tingcoApps.getMsgStatus().setResponseText("AgreementItemTypeID is not valid");
                            return tingcoApps;
                        }
                        ai.setAgreementItemTypes((AgreementItemTypes) object);

                        object = session.get(AgreementStatuses.class, agreementItemseJaxb.getAgreementStatusID());
                        if (object == null) {
                            tingcoApps.getMsgStatus().setResponseCode(-1);
                            tingcoApps.getMsgStatus().setResponseText("AgreementStatusID is not valid");
                            return tingcoApps;
                        }
                        ai.setAgreementStatuses((AgreementStatuses) object);

                        object = session.get(PricePlans.class, agreementItemseJaxb.getPricePlanID());
                        if (object == null) {
                            tingcoApps.getMsgStatus().setResponseCode(-1);
                            tingcoApps.getMsgStatus().setResponseText("PricePlanID is not valid");
                            return tingcoApps;
                        }
                        ai.setPricePlans((PricePlans) object);

                        ai.setItemName(agreementItemseJaxb.getItemName());
                        ai.setItemText(agreementItemseJaxb.getItemText());
                        ai.setObjectId(agreementItemseJaxb.getObjectID());

                        ai.setActiveFromDate(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, df.parse(agreementItemseJaxb.getActiveFromDate())));
                        ai.setActiveToDate(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, df.parse(agreementItemseJaxb.getActiveToDate())));
                        ai.setLastUpdatedByUserId(sessions2.getUserId());
                        GregorianCalendar gc = new GregorianCalendar();
                        ai.setUpdatedDate(gc.getTime());

                        if (!applicationDAO.updateAgreementItemById(session, ai)) {
                            tingcoApps.getMsgStatus().setResponseCode(-1);
                            tingcoApps.getMsgStatus().setResponseText("Error occured");
                            return tingcoApps;
                        }

                    } else {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("User have no TimeZone");
                        return tingcoApps;
                    }
                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }
            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }
        return tingcoApps;
    }

    private TingcoApplications deleteAgreementItemById(String agreementItemId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.AGREEMENTS, TCMUtil.DELETE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object object = session.get(AgreementItems.class, agreementItemId);
                    if (object == null) {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoApps;
                    }
                    Set<String> agreementItemIdSet = new HashSet();
                    agreementItemIdSet.add(agreementItemId);
                    agreementItemIdSet = applicationDAO.traverseAgreementsDownwards(agreementItemId, agreementItemIdSet, session);
                    List<AgreementItems> aggAgreementItemses = applicationDAO.getAgreementItemsOrderByItemSectionNumber(agreementItemIdSet, session);
                    if (!applicationDAO.deleteAgreementItemById(session, aggAgreementItemses)) {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("Error occured");
                        return tingcoApps;
                    }
                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }
            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }
        return tingcoApps;
    }

    private TingcoApplications getMaxSectionNumber(String agreementId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.AGREEMENTS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<AgreementItems> aggAgreementItemses = applicationDAO.getAgreementItemsByAgreementID(session, agreementId);
                    if (aggAgreementItemses.isEmpty()) {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoApps;
                    }
                    int itemSectionNumber = 0;
                    for (AgreementItems agreementItems : aggAgreementItemses) {
                        if (itemSectionNumber < agreementItems.getItemSectionNumber()) {
                            itemSectionNumber = agreementItems.getItemSectionNumber();
//                            break;
                        }
                    }
                    return tingcoAppXML.buildGetMaxSectionNuember(tingcoApps, itemSectionNumber, agreementId);
                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }
            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }
    }

    private TingcoApplications getAgreementItemsDetailsByAgreementID(String agreementId, int countryId, String agreementitemId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            if (!agreementitemId.equals("")) {
                agreementitemId = agreementitemId.split("/")[2];
            } else {
                agreementitemId = null;
            }
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.AGREEMENTS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 == null) {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("User TimeZone is not found");
                        return tingcoApps;
                    }
                    String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                    List<AgreementItems> agreementItemses = new ArrayList<AgreementItems>();
                    agreementItemses = applicationDAO.getAgreementItemsByAgreementId(agreementId, session, 200);
                    if (!agreementItemses.isEmpty()) {

                        if (agreementitemId != null) {
                            boolean flag = false;
                            for (AgreementItems agreementItems : agreementItemses) {
                                if (agreementItems.getAgreementItemId().equalsIgnoreCase(agreementitemId)) {
                                    agreementItemses.clear();
                                    agreementItemses.add(agreementItems);
                                    flag = true;
                                }
                            }
                            if (!flag) {
                                tingcoApps.getMsgStatus().setResponseCode(-1);
                                tingcoApps.getMsgStatus().setResponseText("No Data Found");
                                return tingcoApps;
                            }
                        }

                        Set<String> agreementItemsIDs = new HashSet<String>();
                        List<String> productVariantIDs = new ArrayList<String>();
                        Set<String> aittIDs = new HashSet<String>();
                        Set<String> astIDs = new HashSet<String>();
                        List<String> userIDs = new ArrayList<String>();
                        for (AgreementItems ai : agreementItemses) {
                            agreementItemsIDs.add(ai.getAgreementItemId());
                            if (ai.getObjectId() != null) {
                                productVariantIDs.add(ai.getObjectId());
                            }
                            if (ai.getAgreementItemTypes() != null) {
                                aittIDs.add(ai.getAgreementItemTypes().getAgreementItemTypeId());
                            }
                            if (ai.getAgreementStatuses() != null) {
                                astIDs.add(ai.getAgreementStatuses().getAgreementStatusId());
                            }
                            if (ai.getLastUpdatedByUserId() != null) {
                                userIDs.add(ai.getLastUpdatedByUserId());
                            }
                        }
                        List<AgreementItems> agreementItemsesOrderByISN = applicationDAO.getAgreementItemsOrderByItemSectionNumber(agreementItemsIDs, session);
                        List<AgreementItemTypeTranslations> agreementItemTypeTranslationsList = new ArrayList<AgreementItemTypeTranslations>();
                        List<ProductVariantTranslations> productVariantTranslationsList = new ArrayList<ProductVariantTranslations>();
                        List<AgreementStatusTranslations> agreementStatusTranslationsList = new ArrayList<AgreementStatusTranslations>();
                        List<Users2> users2List = new ArrayList<Users2>();
                        if (!userIDs.isEmpty()) {
                            users2List = userDAO.getUser2ListByUserId(userIDs, session);
                        }
                        if (!productVariantIDs.isEmpty()) {
                            productVariantTranslationsList = productsDAO.getProductVariantTranslations(session, productVariantIDs, countryId);
                        }
                        if (!aittIDs.isEmpty()) {
                            agreementItemTypeTranslationsList = applicationDAO.getAgreementItemTypeTranslationsByIDs(session, aittIDs, countryId);
                        }
                        if (!astIDs.isEmpty()) {
                            agreementStatusTranslationsList = applicationDAO.getAgreementStatusTranslationsByStatusIds(session, astIDs, countryId);
                        }
                        tingcoApps = tingcoAppXML.buildGetAgreementItemsDetailsByAgreementID(tingcoApps, agreementItemsesOrderByISN, productVariantTranslationsList, users2List, agreementItemTypeTranslationsList, agreementStatusTranslationsList, timeZoneGMToffset);
                    } else {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("No Data Found");
                        return tingcoApps;
                    }
                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }
            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }
        return tingcoApps;
    }

    private TingcoApplications getAgreementItemById(String agreementItemId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.AGREEMENTS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 == null) {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("User TimeZone is not found");
                        return tingcoApps;
                    }
                    String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                    Object object = applicationDAO.getAgreementItemsByAgreementItemId(agreementItemId, session);
                    if (object != null) {
                        AgreementItems agreementItems = (AgreementItems) object;
                        AgreementItemTypeTranslations agreementItemTypeTranslations = null;
                        ProductVariantTranslations productVariantTranslations = null;
                        AgreementStatusTranslations agreementStatusTranslations = null;
                        Users2 users2 = null;
                        if (agreementItems.getLastUpdatedByUserId() != null) {
                            users2 = userDAO.getUserById(agreementItems.getLastUpdatedByUserId(), session);
                        }
                        if (agreementItems.getObjectId() != null) {
                            productVariantTranslations = productsDAO.getProductVariantTranslationsByIds(agreementItems.getObjectId(), countryId, session);
                        }
                        if (agreementItems.getAgreementItemTypes() != null) {
                            Object obj = applicationDAO.getAgreementItemTypeTranslationsByID(agreementItems.getAgreementItemTypes().getAgreementItemTypeId(), countryId, session);
                            if (obj != null) {
                                agreementItemTypeTranslations = (AgreementItemTypeTranslations) obj;
                            }
                        }
                        if (agreementItems.getAgreementStatuses() != null) {
                            Object obj = applicationDAO.getAgreementStatusTranslationsById(agreementItems.getAgreementStatuses().getAgreementStatusId(), countryId, session);
                            if (obj != null) {
                                agreementStatusTranslations = (AgreementStatusTranslations) obj;
                            }
                        }
                        tingcoApps = tingcoAppXML.buildGetAgreementItemById(tingcoApps, agreementItems, productVariantTranslations, users2, agreementItemTypeTranslations, agreementStatusTranslations, timeZoneGMToffset);
                    } else {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("No Data Found");
                        return tingcoApps;
                    }
                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }
            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }
        return tingcoApps;
    }

    private TingcoApplications getAgreementItemTypes(int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.AGREEMENTS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<AgreementItemTypeTranslations> aitts = applicationDAO.getAgreementItemTypeTranslationsByCountryId(countryId, session);
                    tingcoApps = tingcoAppXML.buildGetAgreementItemTypes(tingcoApps, aitts);
                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }
            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }
        return tingcoApps;
    }

    private TingcoApplications addAgreementInfoDetails(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        Transaction tx = null;
        ProductsDAO productsDAO = new ProductsDAO();
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();

            if (request.getAttribute("USERSESSION") != null) {

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.AGREEMENTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.AGREEMENTS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    GregorianCalendar gc = new GregorianCalendar();
                    GregorianCalendar gc1 = new GregorianCalendar();
                    GregorianCalendar gc2 = new GregorianCalendar();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 == null) {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("User TimeZone is not found");
                        return tingcoApps;
                    }
                    String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                    se.info24.appjaxb.TingcoApplications tingcoApplicationsPOST = (se.info24.appjaxb.TingcoApplications) JAXBManager.getInstance().unMarshall(content, se.info24.appjaxb.TingcoApplications.class);
                    se.info24.appjaxb.Agreement agreementJaxb = tingcoApplicationsPOST.getAgreementDetails().getAgreement().get(0);

                    String agreementID = UUID.randomUUID().toString();
                    Agreements agreements = new Agreements();
                    agreements.setAgreementId(agreementID);

                    Users2 users2 = userDAO.getUserById(sessions2.getUserId(), session);

                    GroupDefaultAgreement gda = productsDAO.getGroupDefaultAgreement(users2.getGroupId(), session);
                    if (gda != null) {
                        agreements.setAgreements(gda.getAgreements());
                    }

                    agreements.setAgreementName(agreementJaxb.getAgreementName());
                    if (agreementJaxb.getAgreementNumber() != null) {
                        agreements.setAgreementNumber(agreementJaxb.getAgreementNumber());
                    }
                    if (agreementJaxb.getAgreementDesc() != null) {
                        agreements.setAgreementDescription(agreementJaxb.getAgreementDesc());
                    } else {
                        agreements.setAgreementDescription(null);
                    }

                    agreements.setIsEnabled(1);

                    if (agreementJaxb.getStartDate() != null) {
                        gc1.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, dateFormat.parse(agreementJaxb.getStartDate())));
                        agreements.setStartDate(gc1.getTime());
                    } else {
                        agreements.setStartDate(null);
                    }
                    if (agreementJaxb.getEndDate() != null) {
                        gc2.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, dateFormat.parse(agreementJaxb.getEndDate())));
                        agreements.setEndDate(gc2.getTime());
                    } else {
                        agreements.setEndDate(null);
                    }

                    agreements.setUserId(sessions2.getUserId());

                    if (!agreementJaxb.getAgreementTypeTranslations().isEmpty()) {
                        se.info24.appjaxb.AgreementTypeTranslations att = agreementJaxb.getAgreementTypeTranslations().get(0);
                        agreements.setAgreementTypeId(att.getAgreementTypeID());
                    }

                    agreements.setIsTemplate(0);

                    agreements.setAgreementOwnerGroupId(users2.getGroupId());

                    if (!agreementJaxb.getAgreementStatusTranslations().isEmpty()) {
                        se.info24.appjaxb.AgreementStatusTranslations ast = agreementJaxb.getAgreementStatusTranslations().get(0);
                        AgreementStatuses agreementStatuses = null;
                        Object aObject = applicationDAO.getAgreementStatusesById(ast.getAgreementStatusID(), session);
                        if (aObject != null) {
                            agreementStatuses = (AgreementStatuses) aObject;
                            agreements.setAgreementStatuses(agreementStatuses);
                        }
                    }

                    agreements.setAgreementVersion(1);

                    agreements.setCreatedDate(gc.getTime());
                    agreements.setUpdatedDate(gc.getTime());

                    tx = session.beginTransaction();
                    applicationDAO.saveUpdateAgreements(agreements, session);
                    session.flush();
                    session.clear();

                    GroupTranslations groupTrans = groupDAO.getGroupTranslationsByIds(users2.getGroupId(), users2.getCountryId(), session);

                    AgreementRoles agreementRoles = new AgreementRoles(new AgreementRolesId(agreementID, groupTrans.getId().getGroupId()), agreements, new AgreementRolesTypes("59155789-3B77-4C49-9A54-FC5169E74735"), groupTrans.getGroupName(), gc1.getTime(), gc2.getTime(), 1, 0, sessions2.getUserId(), gc.getTime(), gc.getTime());
                    session.saveOrUpdate(agreementRoles);
                    if (!agreementJaxb.getGroupID().getValue().equalsIgnoreCase(groupTrans.getId().getGroupId())) { //checking the given groupid is not info24 groupid
                        agreementRoles = new AgreementRoles(new AgreementRolesId(agreementID, agreementJaxb.getGroupID().getValue()), agreements, new AgreementRolesTypes("F56F4AF0-1B74-41A3-9232-F5DE75304D04"), agreementJaxb.getGroupID().getGroupName(), gc1.getTime(), gc2.getTime(), 0, 1, sessions2.getUserId(), gc.getTime(), gc.getTime());
                        session.saveOrUpdate(agreementRoles);
                    }
                    tx.commit();

                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }
            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }
        } catch (Exception ex) {
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoApps;
    }

    private TingcoApplications addagreementroles(String agreementId, String objectId, String objectName, String agreementRoleTypeId, String activeFromDate, String activeToDate, String lastUpdatedByUserId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        Transaction tx = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.AGREEMENTS, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();

                        tx = session.beginTransaction();
                        Object object = applicationDAO.getAgreementRolesByIds(agreementId, objectId, session);

                        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                        gc.setTime(df.parse(activeFromDate));
                        gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                        activeFromDate = df.format(gc.getTime());

                        gc.setTime(df.parse(activeToDate));
                        gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                        activeToDate = df.format(gc.getTime());
                        if (object == null) {
                            if (applicationDAO.saveAgreementRoles(agreementId, objectId, objectName, agreementRoleTypeId, df.parse(activeFromDate), df.parse(activeToDate), sessions2.getUserId(), session)) {
                                tx.commit();
                            } else {
                                tingcoApps.getMsgStatus().setResponseCode(-1);
                                tingcoApps.getMsgStatus().setResponseText("Error occured");
                                return tingcoApps;
                            }
                        } else {
                            tingcoApps.getMsgStatus().setResponseCode(-1);
                            tingcoApps.getMsgStatus().setResponseText("This Organization is already linked to this Agreement. An Organization can only have one role in an Agreement.");
                            return tingcoApps;
                        }
                    } else {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                        return tingcoApps;
                    }


                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }
            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoApps;
    }

    private TingcoApplications deleteAgreementRoles(String agreementId, String objectId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        Transaction tx = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.AGREEMENTS, TCMUtil.DELETE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    Object agreementRoles = applicationDAO.getAgreementRolesByIds(agreementId, objectId, session);
                    session.delete(agreementRoles);
                    tx.commit();
                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }
            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }

        } catch (Exception ex) {
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }
        return tingcoApps;
    }

    private TingcoApplications getAgreementRoleDetails(String agreementId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.AGREEMENTS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                    List<AgreementRoles> agreementRolesList = applicationDAO.getAgreementRolesByAgreementId(agreementId, 200, session);
                    if (!agreementRolesList.isEmpty()) {
                        Set<String> agreementIdsSet = applicationDAO.getAgreementIds(agreementRolesList);
                        List<AgreementRoles> agreementRoles = applicationDAO.getAgreementRoles(session, agreementIdsSet);
                        tingcoApps = tingcoAppXML.buildTingcoAgreementRolesDetails(tingcoApps, agreementRoles, countryId, sessions2.getUserId(), timeZoneGMToffset, session);
                    } else {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("No data found");
                        return tingcoApps;
                    }
                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }
            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }
        return tingcoApps;
    }

    private TingcoApplications getAgreementRolesTypes(int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.AGREEMENTS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<AgreementRoleTypeTranslations> agreementRoleTypeTrans = applicationDAO.getAgreementRoleTypeTranslations(session, countryId);
                    if (!agreementRoleTypeTrans.isEmpty()) {
                        tingcoApps = tingcoAppXML.buildTingcoAgreementRoleTypes(tingcoApps, agreementRoleTypeTrans);
                    } else {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("No data found");
                        return tingcoApps;
                    }
                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }
            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }
        return tingcoApps;
    }

    private TingcoApplications updateAgreementInfoDetails(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        Transaction tx = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.AGREEMENTS, TCMUtil.UPDATE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 == null) {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("User TimeZone is not found");
                        return tingcoApps;
                    }
                    String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                    se.info24.appjaxb.TingcoApplications tingcoApplicationsPOST = (se.info24.appjaxb.TingcoApplications) JAXBManager.getInstance().unMarshall(content, se.info24.appjaxb.TingcoApplications.class);
                    Agreements agreements = null;
                    for (se.info24.appjaxb.Agreement agreementJaxb : tingcoApplicationsPOST.getAgreementDetails().getAgreement()) {
                        Object obj = applicationDAO.getAgreementsById(session, agreementJaxb.getAgreementID());
                        if (obj != null) {
                            agreements = (Agreements) obj;
                            agreements.setAgreementName(agreementJaxb.getAgreementName());

                            if (agreementJaxb.getAgreementParentID() != null) {
                                agreements.setAgreements(new Agreements(agreementJaxb.getAgreementParentID().getValue()));
                            }
                            if (agreementJaxb.getGroupID() != null) {
                                agreements.setAgreementOwnerGroupId(agreementJaxb.getGroupID().getValue());
                            }
                            if (agreementJaxb.getAgreementNumber() != null) {
                                agreements.setAgreementNumber(agreementJaxb.getAgreementNumber());
                            }
                            if (agreementJaxb.getAgreementDesc() != null) {
                                agreements.setAgreementDescription(agreementJaxb.getAgreementDesc());
                            } else {
                                agreements.setAgreementDescription(null);
                            }

                            if (agreementJaxb.getAgreementStatusTranslations().size() == 0 || agreementJaxb.getAgreementStatusTranslations() == null) {
                                agreements.setAgreementStatuses(null);
                            } else {
                                se.info24.appjaxb.AgreementStatusTranslations ast = agreementJaxb.getAgreementStatusTranslations().get(0);
                                AgreementStatuses agreementStatuses = null;
                                Object aObject = applicationDAO.getAgreementStatusesById(ast.getAgreementStatusID(), session);
                                if (aObject != null) {
                                    agreementStatuses = (AgreementStatuses) aObject;
                                    agreements.setAgreementStatuses(agreementStatuses);
                                }
                            }
                            if (agreementJaxb.getAgreementTypeTranslations().size() == 0 || agreementJaxb.getAgreementTypeTranslations() == null) {
                                agreements.setAgreementTypeId(null);
                            } else {
                                se.info24.appjaxb.AgreementTypeTranslations att = agreementJaxb.getAgreementTypeTranslations().get(0);
                                if (att.getAgreementTypeID() != null) {
                                    agreements.setAgreementTypeId(att.getAgreementTypeID());
                                }
                            }

                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            GregorianCalendar gc = new GregorianCalendar();
                            agreements.setUpdatedDate(gc.getTime());

                            if (agreementJaxb.getStartDate() != null) {
                                gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, dateFormat.parse(agreementJaxb.getStartDate())));
                                agreements.setStartDate(gc.getTime());
                            } else {
                                agreements.setStartDate(null);
                            }
                            if (agreementJaxb.getEndDate() != null) {
                                gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, dateFormat.parse(agreementJaxb.getEndDate())));
                                agreements.setEndDate(gc.getTime());
                            } else {
                                agreements.setEndDate(null);
                            }

                            applicationDAO.saveUpdateAgreements(agreements, session);
                            tx.commit();
                        } else {
                            tingcoApps.getMsgStatus().setResponseCode(-1);
                            tingcoApps.getMsgStatus().setResponseText("Data Not Found");
                            return tingcoApps;
                        }
                    }
                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }
            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }

        } catch (Exception ex) {
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }

            }
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoApps;
    }

    private TingcoApplications getAgreementStatus(Integer countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.AGREEMENTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.AGREEMENTS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<AgreementStatusTranslations> agreementStatusTranslationses = applicationDAO.getAgreementStatusTranslationsByCountryId(session, countryId);
                    tingcoApps = tingcoAppXML.buildAgreementStatus(tingcoApps, agreementStatusTranslationses);
                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }
            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoApps;
    }

    private TingcoApplications getAgreementTypes(Integer countryid) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();

            if (request.getAttribute("USERSESSION") != null) {

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.AGREEMENTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.AGREEMENTS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<AgreementTypeTranslations> agreementTypeTranslationses = applicationDAO.getAgreementTypeTranslationsByCountryId(session, countryid);
                    tingcoApps = tingcoAppXML.buildAgreementTypes(tingcoApps, agreementTypeTranslationses);
                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }
            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoApps;
    }

    private TingcoApplications getAgreementInfoDetails(Integer countryid, String agreementId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();

            if (request.getAttribute("USERSESSION") != null) {

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.AGREEMENTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.AGREEMENTS);
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
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 == null) {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("User TimeZone is not found");
                        return tingcoApps;
                    }

                    String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                    Object obj = applicationDAO.getAgreementsById(session, agreementId);
                    if (obj != null) {
                        Agreements agreements = (Agreements) obj;
                        GroupTranslations groupTranslations = groupDAO.getGroupTranslationsByIds(agreements.getAgreementOwnerGroupId(), countryid, session);
                        AgreementStatusTranslations agreementStatusTranslations = null;
                        if (agreements.getAgreementStatuses() != null) {
                            Object ast = applicationDAO.getAgreementStatusTranslationsById(agreements.getAgreementStatuses().getAgreementStatusId(), countryid, session);
                            if (ast != null) {
                                agreementStatusTranslations = (AgreementStatusTranslations) ast;
                            }

                        }
                        AgreementTypeTranslations agreementTypeTranslations = null;
                        if (agreements.getAgreementTypeId() != null) {
                            agreementTypeTranslations = applicationDAO.getAgreementTypeTranslationsById(session, agreements.getAgreementTypeId(), countryid);
                        }

                        tingcoApps = tingcoAppXML.buildAgreementInfoDetails(tingcoApps, agreements, groupTranslations, agreementTypeTranslations, agreementStatusTranslations, timeZoneGMToffset);
                    } else {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoApps;
                    }

                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }

            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoApps;
    }

    private TingcoApplications getAgreementDetailsForPopup(Integer countryid, String searchString) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();

            if (request.getAttribute("USERSESSION") != null) {
                if (countryid == null) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("groupId should not be empty");
                    return tingcoApps;
                }

                if (!searchString.equals("")) {
                    searchString = searchString.split("/")[2];
                } else {
                    searchString = null;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.AGREEMENTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.AGREEMENTS);
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
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 == null) {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("User TimeZone is not found");
                        return tingcoApps;
                    }

                    String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                    List<GroupTranslations> groupTranslationses = new ArrayList<GroupTranslations>();
                    if (searchString == null) {
                        List<Agreements> agreementses = applicationDAO.getAgreementsByAgreementOwnerGroupID(session, users2.getGroupId(), 200);
                        GroupTranslations groupTranslations = groupDAO.getGroupTranslationsByIds(users2.getGroupId(), countryid, session);
                        groupTranslationses.add(groupTranslations);
                        tingcoApps =
                                tingcoAppXML.buildAgreementDetailsForPopup(tingcoApps, agreementses, groupTranslationses, timeZoneGMToffset);
                    } else {
                        String parentGroupID = users2.getGroupId();
                        List<Groups> groupsList = groupDAO.getDownstreamGroupIdsList(parentGroupID, session);
                        Set<String> downstreamGroupIdsList = new HashSet<String>();
                        if (!groupsList.isEmpty()) {
                            downstreamGroupIdsList = groupDAO.getDownstreamGroupsForLoggedInGroup(parentGroupID, groupsList, session);
                            downstreamGroupIdsList.add(parentGroupID);
                            groupTranslationses =
                                    groupDAO.getGroupTranslationsByGroupId(downstreamGroupIdsList, countryid, 1000, session);
                        }

                        List<Agreements> agreementses = applicationDAO.getAllAgreementsBySearchString(downstreamGroupIdsList, searchString, session);

                        if (agreementses.isEmpty()) {
                            tingcoApps.getMsgStatus().setResponseCode(-1);
                            tingcoApps.getMsgStatus().setResponseText("No Data Found");
                            return tingcoApps;
                        }

                        Set<String> agreementsIds = new HashSet<String>();
                        for (Agreements a : agreementses) {
                            agreementsIds.add(a.getAgreementId());
                        }

                        List<Agreements> sortedAgreements = applicationDAO.getAgreements(session, agreementsIds);
                        tingcoApps =
                                tingcoAppXML.buildAgreementDetailsForPopup(tingcoApps, sortedAgreements, groupTranslationses, timeZoneGMToffset);
                    }

                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }

            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoApps;
    }

    private TingcoApplications getAgreementDetails(Integer countryid, String agreementSearchString, String organizationSearchString) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();

            if (request.getAttribute("USERSESSION") != null) {
                if (countryid == null) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("groupId should not be empty");
                    return tingcoApps;
                }

                if (!agreementSearchString.equals("")) {
                    agreementSearchString = agreementSearchString.split("/")[2];
                } else {
                    agreementSearchString = null;
                }

                if (!organizationSearchString.equals("")) {
                    organizationSearchString = organizationSearchString.split("/")[2];
                } else {
                    organizationSearchString = null;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.AGREEMENTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.AGREEMENTS);
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
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 == null) {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("User TimeZone is not found");
                        return tingcoApps;
                    }

                    String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                    List<Agreements> agreementses = new ArrayList<Agreements>();
//                    List<GroupTranslations> groupTranslationses = new ArrayList<GroupTranslations>();
                    if (organizationSearchString == null && agreementSearchString == null) {
//                        groupTranslationses = new ArrayList<GroupTranslations>();
                        agreementses = applicationDAO.getAgreementsByAgreementOwnerGroupID(session, users2.getGroupId(), 200);
//                        GroupTranslations groupTranslations = groupDAO.getGroupTranslationsByIds(users2.getGroupId(), countryid, session);
//                        groupTranslationses.add(groupTranslations);
//                        Set<String> ids = new HashSet<String>();
//                        for (Agreements a : agreementses) {
//                            if (a.getAgreementStatuses() != null) {
//                                ids.add(a.getAgreementStatuses().getAgreementStatusId());
//                            }
//                        }
//                        List<AgreementStatusTranslations> agreementStatusTranslationses = new ArrayList<AgreementStatusTranslations>();
//                        if (!ids.isEmpty()) {
//                            agreementStatusTranslationses = applicationDAO.getAgreementStatusTranslationsByStatusIds(session, ids, countryid);
//                        }
//                        tingcoApps = tingcoAppXML.buildGetAgreementDetails(tingcoApps, agreementses, groupTranslationses, agreementStatusTranslationses, timeZoneGMToffset);
                    } else {
                        String parentGroupID = users2.getGroupId();
                        List<Groups> groupsList = groupDAO.getDownstreamGroupIdsList(parentGroupID, session);
                        Set<String> downstreamGroupIdsList = new HashSet<String>();
                        if (!groupsList.isEmpty()) {
                            downstreamGroupIdsList = groupDAO.getDownstreamGroupsForLoggedInGroup(parentGroupID, groupsList, session);
//                            groupTranslationses = groupDAO.getGroupTranslationsByGroupId(downstreamGroupIdsList, countryid, session);
                        }
                        downstreamGroupIdsList.add(parentGroupID);
                        agreementses = applicationDAO.getAllAgreementsBySearch(agreementSearchString, organizationSearchString, downstreamGroupIdsList, session, countryid);
                    }
                    if (!agreementses.isEmpty()) {
                        Set<String> ids = new HashSet<String>();
                        for (Agreements a : agreementses) {
                            if (a.getAgreementStatuses() != null) {
                                ids.add(a.getAgreementStatuses().getAgreementStatusId());
                            }
                        }
                        List<AgreementStatusTranslations> agreementStatusTranslationses = new ArrayList<AgreementStatusTranslations>();
                        if (!ids.isEmpty()) {
                            agreementStatusTranslationses = applicationDAO.getAgreementStatusTranslationsByStatusIds(session, ids, countryid);
                        }

                        tingcoApps = tingcoAppXML.buildGetAgreementDetails(tingcoApps, agreementses, countryid, agreementStatusTranslationses, timeZoneGMToffset, session);
                    } else {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("No data found");
                        return tingcoApps;
                    }
                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }
            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoApps;
    }

    private TingcoApplications addOrderforSolutionPackage(String groupId, String applicationPackageId, String agreementId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();

            if (request.getAttribute("USERSESSION") != null) {
                if (groupId.equals("")) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("groupId should not be empty");
                    return tingcoApps;
                }

                if (applicationPackageId.equals("")) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("applicationPackageId should not be empty");
                    return tingcoApps;
                }

                if (agreementId.equals("")) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("agreementId should not be empty");
                    return tingcoApps;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.APPLICATIONSOLUTIONS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.APPLICATIONSOLUTIONS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD) || operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;

                        }


                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    if (!applicationDAO.addOrderforSolutionPackage(groupId, applicationPackageId, agreementId, session, sessions2)) {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("Error while creating Order");
//                    return tingcoApps;
                    }

                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }

            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoApps;
    }

    private TingcoApplications addTrialforSolutionPackage(String groupId, String applicationPackageId, String agreementId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId.equals("")) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("groupId should not be empty");
                    return tingcoApps;
                }

                if (applicationPackageId.equals("")) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("applicationPackageId should not be empty");
                    return tingcoApps;
                }

                if (agreementId.equals("")) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("agreementId should not be empty");
                    return tingcoApps;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.APPLICATIONSOLUTIONS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.APPLICATIONSOLUTIONS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD) || operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;

                        }


                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    if (!applicationDAO.addTrialforSolutionPackage(groupId, applicationPackageId, agreementId, session, sessions2)) {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("You are already in trail for this package");
//                    return tingcoApps;
                    }

                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }

            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoApps;
    }

    private TingcoApplications cancelOrderforSolutionPackage(String groupId, String applicationPackageId, String agreementId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();

            if (request.getAttribute("USERSESSION") != null) {
                if (groupId.equals("")) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("groupId should not be empty");
                    return tingcoApps;
                }

                if (applicationPackageId.equals("")) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("applicationPackageId should not be empty");
                    return tingcoApps;
                }

                if (agreementId.equals("")) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("agreementId should not be empty");
                    return tingcoApps;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.APPLICATIONSOLUTIONS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.APPLICATIONSOLUTIONS);
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
                    if (!applicationDAO.cancelOrderforSolutionPackage(groupId, applicationPackageId, agreementId, session, sessions2)) {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("You are already in trail for this package");
                    }

                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }

            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoApps;
    }

    private TingcoApplications createApplication(String applicationName, String appDesc, String groupID, String isEnabled, String activeFromDate, String activeToDate) throws DatatypeConfigurationException {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        try {

            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (applicationName == null) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("Application Name is should not be empty");
                    return tingcoApps;
                } else if (groupID == null) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("GroupID is should not be empty");
                    return tingcoApps;
                }

                if (appDesc.equals("")) {
                    appDesc = null;
                } else {
                    appDesc = appDesc.split("/")[2];
                }

                if (isEnabled.equals("")) {
                    isEnabled = null;
                } else {
                    isEnabled = isEnabled.split("/")[2];
                }

                if (activeFromDate.equals("")) {
                    activeFromDate = null;
                } else {
                    activeFromDate = activeFromDate.split("/")[2];
                }

                if (activeToDate.equals("")) {
                    activeToDate = null;
                } else {
                    activeToDate = activeToDate.split("/")[2];
                }

                session = HibernateUtil.getSessionFactory().openSession();
                UserSessions2 userSessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Applications application = new Applications();
                String applicationId = UUID.randomUUID().toString();
                application.setApplicationId(applicationId);
                if (isEnabled != null) {
                    application.setIsEnabled(Integer.parseInt(isEnabled));
                } else {
                    application.setIsEnabled(1);
                }

                GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                application.setCreatedDate(gc.getTime());
                application.setUpdatedDate(gc.getTime());
                if (activeFromDate != null) {
                    application.setActiveFromDate(gc.getTime());
                } else {
                    application.setActiveFromDate(gc.getTime());
                }

                application.setLastUpdatedByUserId(userSessions2.getUserId());
                application.setApplicationTypes(new ApplicationTypes("27ba2ad4-1e78-41e4-a2f9-f673911e6e84")); // Need to change to dynamic

                for (Country country : countryDAO.getAllCountries(session)) {
                    ApplicationTranslations appTrans = new ApplicationTranslations();
                    appTrans.setId(new ApplicationTranslationsId(applicationId, country.getCountryId()));
                    appTrans.setApplicationName(applicationName);
                    if (appDesc != null) {
                        appTrans.setApplicationDescription(appDesc);
                    }

                    appTrans.setApplications(application);
                    appTrans.setCountry(country);
                    appTrans.setLastUpdatedByUserId(userSessions2.getUserId());
                    appTrans.setCreatedDate(gc.getTime());
                    appTrans.setUpdatedDate(gc.getTime());
                    application.getApplicationTranslationses().add(appTrans);
                }
// Setting ApplicationGroup Membership

                ApplicationGroupMemberships appGroupMem = new ApplicationGroupMemberships(new ApplicationGroupMembershipsId(applicationId, groupID), application);
                appGroupMem.setLastUpdatedByUserId(userSessions2.getUserId());
                appGroupMem.setCreatedDate(gc.getTime());
                appGroupMem.setUpdatedDate(gc.getTime());
                application.getApplicationGroupMembershipses().add(appGroupMem);
                gc.add(Calendar.YEAR, 10);
                if (activeToDate != null) {
                    application.setActiveToDate(gc.getTime());
                } else {
                    application.setActiveToDate(gc.getTime());
                }

                applicationDAO.saveApplication(application, session);
            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoApps;
    }

    public TingcoApplications deleteApplication(
            String applicationId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (applicationId == null) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("Application ID is should not be empty");
                }

                session = HibernateUtil.getSessionFactory().openSession();
                applicationDAO.removeApplicationByID(applicationId, session);

            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoApps;
    }

    private TingcoApplications getAgreements(String groupId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId.equals("")) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("Group Id should not be empty");
                    return tingcoApps;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.APPLICATIONSOLUTIONS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.APPLICATIONSOLUTIONS);
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
                    Set<String> groupIdList = groupDAO.getGroupIdsList(groupId, groupTrustsList);

                    if (!groupIdList.isEmpty()) {
                        /**
                         * SPLIT LIST SIZE 30 MAR 2014
                         */
                        List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupIdList), 2000);
                        List<String> agreementIdSet = new ArrayList<String>();
                        for (List<String> list : listsplit) {
                            Set<String> agreementIdSettemp = applicationDAO.getAgreementRolesByGroupIdsList(new HashSet<String>(list), session);
                            agreementIdSet.addAll(agreementIdSettemp);
                            if (agreementIdSet.size() > 50) {
                                agreementIdSet = agreementIdSet.subList(0, 50);
                                break;
                            }
                        }

                        if (!agreementIdSet.isEmpty()) {
                            List<Agreements> agreementsList = applicationDAO.getAgreements(session, new HashSet<String>(agreementIdSet));
                            if (!agreementsList.isEmpty()) {
                                tingcoApps = tingcoAppXML.buildTingcoAgreements(tingcoApps, agreementsList);
                            } else {
                                tingcoApps.getMsgStatus().setResponseCode(-1);
                                tingcoApps.getMsgStatus().setResponseText("No Data found");
                                return tingcoApps;
                            }
                        }
                    }
                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }
            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoApps;
    }

    private TingcoApplications getAgreementsForGroup(String groupId, String searchString, String agreementNumber) throws DatatypeConfigurationException {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        tingcoApps =
                tingcoAppXML.buildTingcoAPITemplate();
        Session session = null;
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId.equals("")) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("groupId should not be empty");
                    return tingcoApps;
                }

                if (!searchString.equals("")) {
                    searchString = searchString.split("/")[2];
                } else {
                    searchString = null;
                }

                if (!agreementNumber.equals("")) {
                    agreementNumber = agreementNumber.split("/")[2];
                } else {
                    agreementNumber = null;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.USERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.USERS);
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
                    Set<String> groupidSet = groupDAO.getGroupIdsList(groupId, groupTrustsList);
                    if (!groupidSet.isEmpty()) {

                        /**
                         * SPLIT LIST SIZE 30 MAR 2014
                         */
                        List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupidSet), 2000);
                        List<String> agreementIdSet = new ArrayList<String>();
                        for (List<String> list : listsplit) {
                            Set<String> agreementIdSettemp = applicationDAO.getAgreementRolesByGroupIdsList(new HashSet<String>(list), session);
                            agreementIdSet.addAll(agreementIdSettemp);
                            if (agreementIdSet.size() > 50) {
                                agreementIdSet = agreementIdSet.subList(0, 50);
                                break;
                            }
                        }

                        //Set<String> agreementIdsList = applicationDAO.getAgreementRolesByGroupIdsList(groupidSet, session);
                        if (agreementIdSet.isEmpty()) {
                            tingcoApps.getMsgStatus().setResponseCode(-1);
                            tingcoApps.getMsgStatus().setResponseText("No Record found in Agreements");
                            return tingcoApps;
                        }
                        List<Agreements> agreementsList = new ArrayList<Agreements>();
                        if (searchString != null) {
                            agreementsList = applicationDAO.getAgreements(new HashSet<String>(agreementIdSet), searchString, session);
                        } else if (agreementNumber != null) {
                            agreementsList = applicationDAO.getAgreementsByIdsAndSearchNameAndNumber(new HashSet<String>(agreementIdSet), agreementNumber, session);
                        } else {
                            agreementsList = applicationDAO.getAgreements(session, new HashSet<String>(agreementIdSet));
                        }

                        if (!agreementsList.isEmpty()) {
                            tingcoApps = tingcoAppXML.buildTingcoAgreements(tingcoApps, agreementsList);
                            return tingcoApps;
                        } else {
                            tingcoApps.getMsgStatus().setResponseCode(-1);
                            tingcoApps.getMsgStatus().setResponseText("No Record found in Agreements");
                            return tingcoApps;
                        }

                    } else {
                        tingcoApps.getMsgStatus().setResponseCode(-10);
                        tingcoApps.getMsgStatus().setResponseText("No Groups Found");
                        return tingcoApps;
                    }

                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }

            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoApps;
    }

    private TingcoApplications getAllApplicationModules(String applicationId, int countryId, String groupId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId.equals("")) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("Group Id should not be empty");
                    return tingcoApps;
                }

                if (countryId <= 0) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("countryId should not be empty");
                    return tingcoApps;
                }

                if (applicationId.equals("")) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("applicationId should not be empty");
                    return tingcoApps;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.APPLICATIONMODULES)) {
                    ArrayList<String> operations = ht.get(TCMUtil.APPLICATIONMODULES);
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
                    List<ApplicationModules> appModulesList = applicationDAO.getapplicationModules(session, applicationId);
                    List<GroupVisibleApplicationModules> gvamList = new ArrayList<GroupVisibleApplicationModules>();
                    if (!appModulesList.isEmpty()) {
                        gvamList = applicationDAO.getgroupVisibleApplicationModules(session, appModulesList, groupId);
                    }

                    List<ApplicationModuleTranslations> amtList = applicationDAO.getApplicationModuleTranslations(session);
                    if (!gvamList.isEmpty() && !amtList.isEmpty()) {
                        tingcoApps = tingcoAppXML.buildTingcoAllApplicationModules(tingcoApps, gvamList, amtList, countryId, appModulesList);
                    } else {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("No Data found");
                    }

                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }

            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoApps;
    }

    private TingcoApplications getApplicationInfo(String applicationId) throws DatatypeConfigurationException {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (applicationId == null) {
                tingcoApps.getMsgStatus().setResponseCode(-1);
                tingcoApps.getMsgStatus().setResponseText("Application ID is should not be empty");
                return tingcoApps;
            }

            session = HibernateUtil.getSessionFactory().openSession();
            Applications application = applicationDAO.getApplication(applicationId, session);
            // Need to get User object by UserId, pass the Country id from user object.
            if (application != null) {
                tingcoApps = tingcoAppXML.buildTingcoApplicationXML(tingcoApps, application, 6);
            } else {
                tingcoApps.getMsgStatus().setResponseCode(-1);
                tingcoApps.getMsgStatus().setResponseText("No Application  exists with supplied Application ID");
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoApps;
    }

    private TingcoApplications getApplicationPackages(String groupId, int countryId, String applicationSolutionId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId.equals("")) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("Group Id should not be empty");
                    return tingcoApps;
                }

                if (countryId <= 0) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("countryId should not be empty");
                    return tingcoApps;
                }

                if (!applicationSolutionId.equals("")) {
                    applicationSolutionId = applicationSolutionId.split("/")[2];
                } else {
                    applicationSolutionId = null;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.APPLICATIONPACKAGES)) {
                    ArrayList<String> operations = ht.get(TCMUtil.APPLICATIONPACKAGES);
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


                    List<GroupVisibleApplicationPackages> groupVisibleAppPackagesList = new ArrayList<GroupVisibleApplicationPackages>();
                    List<ApplicationPackages> applicationPackagesList = new ArrayList<ApplicationPackages>();
                    if (applicationSolutionId != null) {
                        applicationPackagesList = applicationDAO.getApplicationPackagesByAppSolId(applicationSolutionId, session);
                        if (!applicationPackagesList.isEmpty()) {
                            groupVisibleAppPackagesList = applicationDAO.getGroupVisibleAppPackages(session, groupId, applicationPackagesList);
                        }
                    } else {
//                       applicationPackagesList   getApplicationPackages
                        groupVisibleAppPackagesList = applicationDAO.getGroupVisibleApplicationPackagesbyGroupid(groupId, session);
                        List<String> applicationPackageIDList = new ArrayList<String>();
                        for (GroupVisibleApplicationPackages groupVisibleAppPackages : groupVisibleAppPackagesList) {
                            applicationPackageIDList.add(groupVisibleAppPackages.getId().getApplicationPackageId());
                        }
                        if (applicationPackageIDList.isEmpty()) {
                            tingcoApps.getMsgStatus().setResponseCode(-1);
                            tingcoApps.getMsgStatus().setResponseText("No Data found");
                            return tingcoApps;
                        }

                        applicationPackagesList = groupDAO.getApplicationPackages(applicationPackageIDList, session);
                    }
                    if (groupVisibleAppPackagesList != null) {
                        List<ApplicationPackageTranslations> appPackageTransList = applicationDAO.getApplicationPackageTranslations(session);
                        if (!groupVisibleAppPackagesList.isEmpty() && !appPackageTransList.isEmpty()) {
                            tingcoApps = tingcoAppXML.buildTingcoApplicationPackages(tingcoApps, groupVisibleAppPackagesList, appPackageTransList, countryId, applicationPackagesList);
                        } else {
                            tingcoApps.getMsgStatus().setResponseCode(-1);
                            tingcoApps.getMsgStatus().setResponseText("No Data found");
                            return tingcoApps;
                        }
                    } else {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("No Data found");
                        return tingcoApps;
                    }

                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }

            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoApps;
    }

    private TingcoApplications getApplicationSolutions(String groupId, int countryId, String applicationId) throws DatatypeConfigurationException {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();

            if (request.getAttribute("USERSESSION") != null) {
                if (groupId.equals("")) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("Group Id should not be empty");
                    return tingcoApps;
                }

                if (countryId <= 0) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("countryId should not be empty");
                    return tingcoApps;
                }

                if (applicationId.equals("")) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("applicationId should not be empty");
                    return tingcoApps;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.APPLICATIONSOLUTIONS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.APPLICATIONSOLUTIONS);
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
                    List<String> appSolutionIdsList = new ArrayList<String>();
                    List<ApplicationSolutions> applicationSolutionsList = applicationDAO.getApplicationSolutions(session, applicationId);
                    List<String> appSolutionIdList = new ArrayList<String>();
                    for (ApplicationSolutions as : applicationSolutionsList) {
                        appSolutionIdList.add(as.getApplicationSolutionId());
                    }
                    if (appSolutionIdList.isEmpty()) {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("ApplicationId is not available");
                        return tingcoApps;
                    }

                    List<GroupVisibleApplicationSolutions> groupVisibleappSolutionsList = applicationDAO.getGroupVisibleApplicationSolutionsbyGroupid(groupId, session);

                    for (GroupVisibleApplicationSolutions gvs : groupVisibleappSolutionsList) {
                        appSolutionIdsList.add(gvs.getApplicationSolutions().getApplicationSolutionId());
                    }
                    if (appSolutionIdsList.isEmpty()) {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("Data not Found");
                        return tingcoApps;
                    }

                    List<ApplicationSolutionTranslations> appSolutionTransList = new ArrayList<ApplicationSolutionTranslations>();
                    appSolutionTransList =
                            applicationDAO.getApplicationSolutionTranslationsByidandCountryid(appSolutionIdsList, countryId, session);
                    List<ApplicationPackages> appPackagesList = applicationDAO.getApplicationPackages(appSolutionIdList, session);
                    List<String> appPackageIdList = new ArrayList<String>();
                    for (ApplicationPackages ap : appPackagesList) {
                        appPackageIdList.add(ap.getApplicationPackageId());
                    }

                    List<GroupApplicationPackages> groupAppPackagesList = new ArrayList<GroupApplicationPackages>();
                    if (!appPackageIdList.isEmpty()) {
                        groupAppPackagesList = applicationDAO.getgroupApplicationPackages(appPackageIdList, groupId, session);
                    } else {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("ApplicationPackageId is not available");
                        return tingcoApps;
                    }

                    List<ApplicationPackageTranslations> appPackageTransList = applicationDAO.getApplicationPackageTranslations(session);
                    Set<String> agreementIdSet = new HashSet<String>();
                    for (GroupApplicationPackages gap : groupAppPackagesList) {
                        agreementIdSet.add(gap.getAgreements().getAgreementId());
                    }

                    List<Agreements> agreementsList = new ArrayList<Agreements>();
                    if (!agreementIdSet.isEmpty()) {
                        agreementsList = applicationDAO.getAgreements(session, agreementIdSet);
                    }

                    tingcoApps = tingcoAppXML.buildTingcoApplicationSolutions(tingcoApps, applicationSolutionsList, groupVisibleappSolutionsList, appSolutionTransList, appPackagesList, groupAppPackagesList, appPackageTransList, agreementsList, countryId);
                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }

            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoApps;
    }

    private TingcoApplications getSystemMessagesByApplicationId(String applicationId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (applicationId.equals("")) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("Application ID should not be empty");
                    return tingcoApps;
                }

                if (countryId <= 0) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("Country ID should not be empty");
                    return tingcoApps;
                }

                session = HibernateUtil.getSessionFactory().openSession();
                SystemMessageTranslations sysMsgTrans = applicationDAO.getSystemMessagesByApplicationId(session, applicationId, countryId);
                if (sysMsgTrans != null) {
                    se.info24.appjaxb.SystemMessages sysMsgs = new se.info24.appjaxb.SystemMessages();
                    se.info24.appjaxb.SystemMessage sysMsg = new se.info24.appjaxb.SystemMessage();

                    sysMsg.getSystemMessageID().add(sysMsgTrans.getId().getSystemMessageId());
                    sysMsg.getSystemMessageText().add(sysMsgTrans.getSystemMessageText());
                    sysMsgs.getSystemMessage().add(sysMsg);
                    tingcoApps.setSystemMessages(sysMsgs);
                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("No System Messages for this ApplicationID");
                    return tingcoApps;
                }

            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoApps;
    }

    private TingcoApplications getApplicationModules(String groupId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId.equals("")) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("Group Id should not be empty");
                    return tingcoApps;
                }

                if (countryId <= 0) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("countryId should not be empty");
                    return tingcoApps;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.APPLICATIONMODULES)) {
                    ArrayList<String> operations = ht.get(TCMUtil.APPLICATIONMODULES);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<GroupApplicationPackages> gAPList = applicationDAO.getGroupAppPackagesForApplicationModule(session, groupId);
                    Set<String> applicationPackageId = applicationDAO.getApplicationPackageId(gAPList);
                    if (applicationPackageId.isEmpty()) {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("No Data found");
                        return tingcoApps;
                    }
                    List<ApplicationPackageModules> aPMList = applicationDAO.getApplicationPackageModulesByModuleIdList(session, applicationPackageId);
                    Set<String> applicationModuleId = applicationDAO.getApplicationModuleId(aPMList);

                    if (applicationModuleId.isEmpty()) {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("No Data found");
                        return tingcoApps;
                    }

                    List<ApplicationModuleTranslations> transList = applicationDAO.getAppModuleTransByAppModuleIdListAndCountryId(session, applicationModuleId, countryId);
                    if (!transList.isEmpty()) {
                        tingcoApps = tingcoAppXML.buildTingcoApplicationModules(tingcoApps, transList, countryId);
                    } else {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("No Data found");
                        return tingcoApps;
                    }

                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }

            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoApps;
    }

    private TingcoApplications updateApplication(String applicationId, String applicationName, String appDesc, String groupID, String isEnabled, String activeFromDate, String activeToDate) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (applicationName.equals("")) {
                    applicationName = null;
                } else {
                    applicationName = applicationName.split("/")[2];
                }

                if (groupID.equals("")) {
                    groupID = null;
                } else {
                    groupID = groupID.split("/")[2];
                }

                if (appDesc.equals("")) {
                    appDesc = null;
                } else {
                    appDesc = appDesc.split("/")[2];
                }

                if (isEnabled.equals("")) {
                    isEnabled = null;
                } else {
                    isEnabled = isEnabled.split("/")[2];
                }

                if (activeFromDate.equals("")) {
                    activeFromDate = null;
                } else {
                    activeFromDate = activeFromDate.split("/")[2];
                }

                if (activeToDate.equals("")) {
                    activeToDate = null;
                } else {
                    activeToDate = activeToDate.split("/")[2];
                }

                if (applicationName == null && appDesc == null && groupID == null && isEnabled == null && activeFromDate == null && activeToDate == null) {
                    return tingcoApps;
                }

                session = HibernateUtil.getSessionFactory().openSession();
                UserSessions2 userSessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Applications application = applicationDAO.getApplication(applicationId, session);
                application.setIsEnabled((isEnabled != null) ? Integer.parseInt(isEnabled) : 1);
                GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                application.setUpdatedDate(gc.getTime());
                application.setLastUpdatedByUserId(userSessions2.getUserId());
                try {
                    if (activeFromDate != null) {
                        application.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(activeFromDate).toGregorianCalendar().getTime());
                    }

                    if (activeToDate != null) {
                        application.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(activeToDate).toGregorianCalendar().getTime());
                    }

                } catch (DatatypeConfigurationException ex) {
                    TCMUtil.exceptionLog(getClass().getName(), ex);
                }

                Iterator it;
                if (applicationName != null || appDesc != null) {
                    for (it = application.getApplicationTranslationses().iterator(); it.hasNext();) {
                        ApplicationTranslations appTrans = (ApplicationTranslations) it.next();
                        if (applicationName != null) {
                            appTrans.setApplicationName(applicationName);
                        }

                        if (appDesc != null) {
                            appTrans.setApplicationDescription(appDesc);
                        }

                        appTrans.setLastUpdatedByUserId(userSessions2.getUserId());
                        appTrans.setUpdatedDate(gc.getTime());
                    }

                }
                if (groupID != null) {
                    boolean flag = true;
                    for (it = application.getApplicationGroupMembershipses().iterator(); it.hasNext();) {
                        ApplicationGroupMemberships appGrpMem = (ApplicationGroupMemberships) it.next();
                        if (appGrpMem.getId().getGroupId().equalsIgnoreCase(groupID)) {
                            appGrpMem.setLastUpdatedByUserId(userSessions2.getUserId());
                            appGrpMem.setUpdatedDate(gc.getTime());
                            flag =
                                    false;
                            break;

                        }


                    }
                    if (flag) {
                        ApplicationGroupMemberships appGroupMem = new ApplicationGroupMemberships(new ApplicationGroupMembershipsId(applicationId, groupID), application);
                        appGroupMem.setLastUpdatedByUserId(userSessions2.getUserId());
                        appGroupMem.setCreatedDate(gc.getTime());
                        appGroupMem.setUpdatedDate(gc.getTime());
                        application.getApplicationGroupMembershipses().add(appGroupMem);
                    }

                }
                applicationDAO.saveApplication(application, session);
            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoApps;
    }

    private TingcoApplications getAllApplicationsByCountryId(String searchString, int countryId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (countryId <= 0) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("CoutnryID is should not be empty");
                    return tingcoApps;
                }
                if (!searchString.equals("")) {
                    searchString = searchString.split("/")[2];
                } else {
                    searchString = null;
                }
                session = HibernateUtil.getSessionFactory().openSession();
                if (searchString == null) {
                    tingcoApps = tingcoAppXML.buildAllApplicationsXML(tingcoApps, applicationDAO.getApplicationsByCountryId(countryId, session));
                } else {
                    tingcoApps = tingcoAppXML.buildAllApplicationsXML(tingcoApps, applicationDAO.getApplicationTranslationsByIdSearch(countryId, searchString, session));
                }
            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoApps;
    }

    private TingcoApplications getAllApplicationSolution(String groupid, int countryId, String applicationid) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            boolean hasPermission = true;
            if (request.getAttribute("USERSESSION") != null) {
                if (countryId <= 0) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("CounytryID is should not be empty");
                    return tingcoApps;
                }

                if (groupid == null) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("GroupID is should not be empty");
                    return tingcoApps;
                }

                if (applicationid == null) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("ApplicationID is should not be empty");
                    return tingcoApps;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.APPLICATIONMODULES)) {
                    ArrayList<String> operations = ht.get(TCMUtil.APPLICATIONMODULES);
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
                    List<String> appSolutionIdsList = new ArrayList<String>();
                    List<ApplicationSolutionTranslations> ast = new ArrayList<ApplicationSolutionTranslations>();
                    List<GroupVisibleApplicationSolutions> gvasl = applicationDAO.getGroupVisibleApplicationSolutionsbyGroupid(groupid, session);
                    List<ApplicationSolutions> as = applicationDAO.getApplicationSolutions(session, applicationid);
                    for (GroupVisibleApplicationSolutions gvas : gvasl) {
                        for (ApplicationSolutions applicationSolutions1 : as) {
                            if (gvas.getApplicationSolutions().getApplicationSolutionId().equalsIgnoreCase(applicationSolutions1.getApplicationSolutionId())) {
                                appSolutionIdsList.add(gvas.getApplicationSolutions().getApplicationSolutionId());
                            }

                        }
                    }
                    if (appSolutionIdsList.isEmpty()) {
                        tingcoApps.getMsgStatus().setResponseCode(-1);
                        tingcoApps.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoApps;
                    }

                    ast = applicationDAO.getApplicationSolutionTranslationsByidandCountryid(appSolutionIdsList, countryId, session);
                    tingcoApps =
                            tingcoAppXML.buildAllApplicationsSolutionXML(tingcoApps, ast);
                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }

            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoApps;
    }

    private TingcoApplications getApplicationModulesBySolutionId(String applicationsolutionid, int countryId, String groupid) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        try {
            tingcoApps = tingcoAppXML.buildTingcoAPITemplate();
            boolean hasPermission = false;
            if (request.getAttribute("USERSESSION") != null) {
                if (countryId <= 0) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("CoutnryID is should not be empty");
                    return tingcoApps;
                }

                if (applicationsolutionid == null) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("ApplicationSolutionID is should not be empty");
                    return tingcoApps;
                }

                if (groupid == null) {
                    tingcoApps.getMsgStatus().setResponseCode(-1);
                    tingcoApps.getMsgStatus().setResponseText("GroupId is should not be empty");
                    return tingcoApps;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.APPLICATIONMODULES)) {
                    ArrayList<String> operations = ht.get(TCMUtil.APPLICATIONMODULES);
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
                    ApplicationPackageTranslations trans = new ApplicationPackageTranslations();
                    ApplicationModuleTranslations amt = new ApplicationModuleTranslations();
                    List<ApplicationPackages> applicationPackagesList = applicationDAO.getApplicationPackagesByAppSolId(applicationsolutionid, session);
                    ArrayList<ApplicationPackages> validPackages = new ArrayList<ApplicationPackages>();
                    Hashtable<String, ApplicationPackageTranslations> pkgTrans = new Hashtable<String, ApplicationPackageTranslations>();
                    Hashtable<String, ArrayList<ApplicationModuleTranslations>> htable = new Hashtable<String, ArrayList<ApplicationModuleTranslations>>();//holds packages to moduletranslations
                    Hashtable<String, ApplicationPackages> packages = new Hashtable<String, ApplicationPackages>();
                    for (ApplicationPackages applicationPackages : applicationPackagesList) {
                        if (applicationDAO.isValidApplicationPackageId(applicationPackages.getApplicationPackageId(), groupid, session)) {
                            validPackages.add(applicationPackages);
                            trans =
                                    applicationDAO.getApplicationPackageTranslationsByAppPackID(applicationPackages.getApplicationPackageId(), countryId, session).get(0);
                            pkgTrans.put(applicationPackages.getApplicationPackageId(), trans);
                        }

                    }
                    for (ApplicationPackages ap : validPackages) {
                        List<ApplicationPackageModules> apm = applicationDAO.getApplicationPackageModules(ap.getApplicationPackageId(), session);
                        for (ApplicationPackageModules pkgModules : apm) {
//                            Change Required Business Solution Packages
//                            if (applicationDAO.isValidApplicationModuleId(pkgModules.getId().getApplicationModuleId(), groupid, session)) {
                            if (applicationDAO.isValidApplicationPackageId(pkgModules.getId().getApplicationPackageId(), groupid, session)) {
                                amt = applicationDAO.getAppModuleTransByAppModuleIdAndCountryId(pkgModules.getId().getApplicationModuleId(), countryId, session);
                                packages.put(ap.getApplicationPackageId(), ap);
                                if (htable.get(ap.getApplicationPackageId()) == null) {
                                    ArrayList<ApplicationModuleTranslations> list = new ArrayList<ApplicationModuleTranslations>();
                                    list.add(amt);
                                    htable.put(ap.getApplicationPackageId(), list);
                                } else {
                                    htable.get(ap.getApplicationPackageId()).add(amt);
                                }

                            }
                        }
                    }
                    tingcoAppXML.buildAllApplicationModules(tingcoApps, htable, packages, pkgTrans, applicationsolutionid);
                } else {
                    tingcoApps.getMsgStatus().setResponseCode(-10);
                    tingcoApps.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoApps;
                }

            } else {
                tingcoApps.getMsgStatus().setResponseCode(-3);
                tingcoApps.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoApps;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Error occured");
            return tingcoApps;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoApps;
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

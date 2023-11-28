/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.priceplans;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.device.DeviceDAO;
import se.info24.group.GroupDAO;
import se.info24.jaxbUtil.TingcoPricePlansXML;
import se.info24.pojo.ChargeTypeTranslations;
import se.info24.pojo.Currency;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupTrusts;
import se.info24.pojo.PricePlanItemTypeTranslations;
import se.info24.pojo.PricePlanItemTypes;
import se.info24.pojo.PricePlanTypeTranslations;
import se.info24.pojo.PricePlanTypes;
import se.info24.pojo.PricePlanVersions;
import se.info24.pojo.PricePlans;
import se.info24.pojo.ProductCategoriesInGroups;
import se.info24.pojo.ProductCategoryTranslations;
import se.info24.pojo.ProductTranslations;
import se.info24.pojo.ProductVariantTranslations;
import se.info24.pojo.ProductVariants;
import se.info24.pojo.UserAliasTypes;
import se.info24.pojo.UserAliasTypesInGroups;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.UserTimeZones2;
import se.info24.pojo.Users2;
import se.info24.pojo.ValidationDataType;
import se.info24.pojo.ValidationExpressions;
import se.info24.pojo.ValidationMemberships;
import se.info24.priceplansjaxb.PricePlan;
import se.info24.priceplansjaxb.PricePlanItems;
import se.info24.priceplansjaxb.TingcoPricePlans;
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
 * @author Vijayakumar
 */
@Path("/priceplans")
public class PricePlanssResource {

    @Context
    private UriInfo context;
    @Context
    HttpServletRequest request;
    TingcoPricePlans tingcoPricePlans = new TingcoPricePlans();
    PricePlanDAO dao = new PricePlanDAO();
    GroupDAO groupDAO = new GroupDAO();
    TingcoPricePlansXML tingcoPricePlansXML = new TingcoPricePlansXML();
    SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of PricePlanssResource */
    public PricePlanssResource() {
    }

    /**
     * GetPricePlans
     * @param groupid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/get/rest/{rest}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetPricePlans", desc = "Used to get the PricePlans", req_Params = {"GroupId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getXml_PricePlans(@PathParam("groupid") String groupId, @PathParam("searchstring") String searchString) {
        return getPricePlans(groupId, searchString);
    }

    /**
     * GetPricePlans
     * @param groupid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/get/json/{json}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoPricePlans getJson_PricePlans(@PathParam("groupid") String groupId, @PathParam("searchstring") String searchString) {
        return getPricePlans(groupId, searchString);
    }

    /**
     * GetPricePlanTypes
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getpriceplantypes/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetPricePlanTypeTranslations", desc = "Used to get the PricePlanTypeTranslations", req_Params = {"CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getXml_PricePlanTypes(@PathParam("countryid") int countryId) {
        return getPricePlanTypes(countryId);
    }

    /**
     * GetPricePlanTypes
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getpriceplantypes/json/{json}/countryid/{countryid}")
    public TingcoPricePlans getJson_PricePlanTypes(@PathParam("countryid") int countryId) {
        return getPricePlanTypes(countryId);
    }

    /**
     * GetPricePlansByPricePlanId
     * @param priceplanid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getpriceplandetails/rest/{rest}/priceplanid/{priceplanid}")
    @RESTDoc(methodName = "GetPricePlans", desc = "Used to get the PricePlans", req_Params = {"PricePlanId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getXml_PricePlanDetails(@PathParam("priceplanid") String pricePlanId) throws Exception {
        return getPricePlanDetails(pricePlanId);
    }

    /**
     * GetPricePlansByPricePlanId
     * @param priceplanid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getpriceplandetails/json/{json}/priceplanid/{priceplanid}")
    public TingcoPricePlans getJson_PricePlanDetails(@PathParam("priceplanid") String pricePlanId) throws Exception {
        return getPricePlanDetails(pricePlanId);
    }

    /**
     * GetPricePlansVersionDetails
     * @param priceplanid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getpriceplanversiondetails/rest/{rest}/priceplanid/{priceplanid}")
    @RESTDoc(methodName = "GetPricePlanVersions", desc = "Used to get the PricePlanVersions", req_Params = {"PricePlanId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getXml_PricePlanVersionDetails(@PathParam("priceplanid") String pricePlanId) throws Exception {
        return getPricePlanVersionDetails(pricePlanId);
    }

    /**
     * GetPricePlansVersionDetails
     * @param priceplanid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getpriceplanversiondetails/json/{json}/priceplanid/{priceplanid}")
    public TingcoPricePlans getJson_PricePlanVersionDetails(@PathParam("priceplanid") String pricePlanId) throws Exception {
        return getPricePlanVersionDetails(pricePlanId);
    }

    /**
     * DeletePricePlan
     * @param priceplanid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deletepriceplan/rest/{rest}/priceplanid/{priceplanid}")
    @RESTDoc(methodName = "DeletePricePlan", desc = "Used to delete PricePlan", req_Params = {"PricePlanId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getXml_DeletePricePlan(@PathParam("priceplanid") String pricePlanId) throws Exception {
        return deletePricePlan(pricePlanId);
    }

    /**
     * DeletePricePlan
     * @param priceplanid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deletepriceplan/json/{json}/priceplanid/{priceplanid}")
    public TingcoPricePlans getJson_DeletePricePlan(@PathParam("priceplanid") String pricePlanId) throws Exception {
        return deletePricePlan(pricePlanId);
    }

    /**
     * AddPricePlan
     * @param priceplanname,priceplandescription,activefromdate,activetodate,priceplantypeid,groupid,isenabled
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/addpriceplan/rest/{rest}/priceplanname/{priceplanname}{priceplandescription:(/priceplandescription/[^/]+?)?}/activefromdate/{activefromdate}/activetodate/{activetodate}/priceplantypeid/{priceplantypeid}/groupid/{groupid}/isenabled/{isenabled}")
    @RESTDoc(methodName = "AddPricePlan", desc = "Used to add in PricePlan", req_Params = {"PricePlanName;string", "ActiveFromdate;string", "ActiveToDate;string", "PricePlanTypeId;UUID", "GroupId;UUID", "IsEnabled;string"}, opt_Params = {"PricePlanDescription;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getXml_AddPricePlan(@PathParam("priceplanname") String pricePlanName, @PathParam("priceplandescription") String pricePlanDescription, @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate, @PathParam("priceplantypeid") String pricePlanTypeId, @PathParam("groupid") String groupId, @PathParam("isenabled") String isEnabled) throws Exception {
        return addPricePlan(pricePlanName, pricePlanDescription, activeFromDate, activeToDate, pricePlanTypeId, groupId, isEnabled);
    }

    /**
     * AddPricePlan
     * @param priceplanname,priceplandescription,activefromdate,activetodate,priceplantypeid,groupid,isenabled
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/addpriceplan/json/{json}/priceplanname/{priceplanname}{priceplandescription:(/priceplandescription/[^/]+?)?}/activefromdate/{activefromdate}/activetodate/{activetodate}/priceplantypeid/{priceplantypeid}/groupid/{groupid}/isenabled/{isenabled}")
    public TingcoPricePlans getJson_AddPricePlan(@PathParam("priceplanname") String pricePlanName, @PathParam("priceplandescription") String pricePlanDescription, @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate, @PathParam("priceplantypeid") String pricePlanTypeId, @PathParam("groupid") String groupId, @PathParam("isenabled") String isEnabled) throws Exception {
        return addPricePlan(pricePlanName, pricePlanDescription, activeFromDate, activeToDate, pricePlanTypeId, groupId, isEnabled);
    }

    /**
     * UpdatePricePlan
     * @param priceplanname,priceplandescription,activefromdate,activetodate,priceplantypeid,groupid,isenabled
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/updatepriceplan/rest/{rest}/priceplanid/{priceplanid}/priceplanname/{priceplanname}{priceplandescription:(/priceplandescription/[^/]+?)?}/activefromdate/{activefromdate}/activetodate/{activetodate}/priceplantypeid/{priceplantypeid}{groupid:(/groupid/[^/]+?)?}{isenabled:(/isenabled/[^/]+?)?}")
    @RESTDoc(methodName = "UpdatePricePlan", desc = "Used to Update PricePlan", req_Params = {"PricePlanName;string", "PricePlanDescription;string", "ActiveFromdate;string", "ActiveToDate;string", "PricePlanTypeId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getXml_UpdatePricePlan(@PathParam("priceplanid") String pricePlanId, @PathParam("priceplanname") String pricePlanName, @PathParam("priceplandescription") String pricePlanDescription,
            @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate,
            @PathParam("priceplantypeid") String pricePlanTypeId, @PathParam("groupid") String groupId, @PathParam("isenabled") String isEnabled) throws Exception {
        return updatePricePlan(pricePlanId, pricePlanName, pricePlanDescription, activeFromDate, activeToDate, pricePlanTypeId, groupId, isEnabled);
    }

    /**
     * UpdatePricePlan
     * @param priceplanname,priceplandescription,activefromdate,activetodate,priceplantypeid,groupid,isenabled
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/updatepriceplan/json/{json}/priceplanid/{priceplanid}/priceplanname/{priceplanname}{priceplandescription:(/priceplandescription/[^/]+?)?}/activefromdate/{activefromdate}/activetodate/{activetodate}/priceplantypeid/{priceplantypeid}{groupid:(/groupid/[^/]+?)?}{isenabled:(/isenabled/[^/]+?)?}")
    public TingcoPricePlans getJson_UpdatePricePlan(@PathParam("priceplanid") String pricePlanId, @PathParam("priceplanname") String pricePlanName, @PathParam("priceplandescription") String pricePlanDescription,
            @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate,
            @PathParam("priceplantypeid") String pricePlanTypeId, @PathParam("groupid") String groupId, @PathParam("isenabled") String isEnabled) throws Exception {
        return updatePricePlan(pricePlanId, pricePlanName, pricePlanDescription, activeFromDate, activeToDate, pricePlanTypeId, groupId, isEnabled);
    }
//    @GET
//    @Produces("application/json")
//    @Path("/updatepriceplan/json/{json}/priceplanid/{priceplanid}/priceplanname/{priceplanname}/priceplandescription/{priceplandescription}/activefromdate/{activefromdate}/activetodate/{activetodate}/priceplantypeid/{priceplantypeid}")
//    public TingcoPricePlans getJson_UpdatePricePlan(@PathParam("priceplanid") String pricePlanId, @PathParam("priceplanname") String pricePlanName, @PathParam("priceplandescription") String pricePlanDescription, @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate, @PathParam("priceplantypeid") String pricePlanTypeId) throws Exception {
//        return updatePricePlan(pricePlanId, pricePlanName, pricePlanDescription, activeFromDate, activeToDate, pricePlanTypeId);
//    }

    /**
     * GetPricePlanItemDetails
     * @param priceplanid, countryid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getpriceplanitemdetails/rest/{rest}/priceplanversionid/{priceplanversionid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetPricePlanItemTables", desc = "Used to get the PricePlanItemdetails", req_Params = {"PricePlanVersionId;string", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getXml_GetPricePlanItemDetails(@PathParam("priceplanversionid") String pricePlanVersionId, @PathParam("countryid") int countryId) throws Exception {
        return getPricePlanItemDetails(pricePlanVersionId, countryId);
    }

    /**
     * GetPricePlanItemDetails
     * @param priceplanid, countryid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getpriceplanitemdetails/json/{json}/priceplanversionid/{priceplanversionid}/countryid/{countryid}")
    public TingcoPricePlans getJson_GetPricePlanItemDetails(@PathParam("priceplanversionid") String pricePlanVersionId, @PathParam("countryid") int countryId) throws Exception {
        return getPricePlanItemDetails(pricePlanVersionId, countryId);
    }

    /**
     * CopyPricePlanTables
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/copypriceplan/rest/{rest}")
    @RESTDoc(methodName = "CopyPricePlan", desc = "Insert the Data into PricePlans, PricePlanVersions, PricePlanItems", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoPricePlans postXml_CopyPricePlan(String content) {
        return copyPricePlan(content);
    }

    /**
     * CopyPricePlanTables
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/copypriceplan/json/{json}")
    public TingcoPricePlans postJson_CopyPricePlan(String content) {
        return copyPricePlan(content);
    }

    /**
     * CopyPricePlan
     * @param priceplanid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/copypriceplan/rest/{rest}/priceplanid/{priceplanid}")
    @RESTDoc(methodName = "CopyPricePlan", desc = "Used to copy the PricePlan", req_Params = {"PricePlanId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getXml_CopyPricePlan(@PathParam("priceplanid") String pricePlanId) throws Exception {
        return copyPricePlanRecursion(pricePlanId);
    }

    /**
     * CopyPricePlan
     * @param priceplanid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/copypriceplan/json/{json}/priceplanid/{priceplanid}")
    public TingcoPricePlans getJson_CopyPricePlan(@PathParam("priceplanid") String pricePlanId) throws Exception {
        return copyPricePlanRecursion(pricePlanId);
    }

    /**
     * GetPricePlanItemTypes
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getpriceplanitemtypes/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetPricePlanItemTypes", desc = "Used to get the PricePlanItemTypes", req_Params = {"CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getXml_GetPricePlanItemTypes(@PathParam("countryid") int countryId) throws Exception {
        return getPricePlanItemTypes(countryId);
    }

    /**
     * GetPricePlanItemTypes
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getpriceplanitemtypes/json/{json}/countryid/{countryid}")
    public TingcoPricePlans getJson_GetPricePlanItemTypes(@PathParam("countryid") int countryId) throws Exception {
        return getPricePlanItemTypes(countryId);
    }

    /**
     * GetValidationDataTypes
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getvalidationdatatypes/rest/{rest}")
    @RESTDoc(methodName = "GetValidationDataTypes", desc = "Used to get the ValidationDataTypes", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getXml_GetValidationTypes() throws Exception {
        return getValidationDataTypes();
    }

    /**
     * GetValidationDataTypes
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getvalidationdatatypes/json/{json}")
    public TingcoPricePlans getJson_GetValidationTypes() throws Exception {
        return getValidationDataTypes();
    }

    /**
     * GetValidationExpressions
     * @param validationDataTypeId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getvalidationexpressions/rest/{rest}/validationdatatypeid/{validationdatatypeid}")
    @RESTDoc(methodName = "GetValidationExpressions", desc = "Used to get the ValidationExpressions", req_Params = {"validationdatatypeid;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getXml_GetValidationExpressions(@PathParam("validationdatatypeid") String validationDataTypeId) throws Exception {
        return getValidationExpressions(validationDataTypeId);
    }

    /**
     * GetValidationExpressions
     * @param validationDataTypeId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getvalidationexpressions/json/{json}/validationdatatypeid/{validationdatatypeid}")
    public TingcoPricePlans getJson_GetValidationExpressions(@PathParam("validationdatatypeid") String validationDataTypeId) throws Exception {
        return getValidationExpressions(validationDataTypeId);
    }

    /**
     * ChargeTypes
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getchargetypes/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetChargeTypes", desc = "Used to get the ChargeTypes", req_Params = {"CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getXml_GetChargeTypes(@PathParam("countryid") int countryId) throws Exception {
        return getChargeTypes(countryId);
    }

    /**
     * ChargeTypes
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getchargetypes/json/{json}/countryid/{countryid}")
    public TingcoPricePlans getJson_GetChargeTypes(@PathParam("countryid") int countryId) throws Exception {
        return getChargeTypes(countryId);
    }

    /**
     * GetUserAliasTypes
     * @param groupid
     * @param searchString
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getuseraliastypes/rest/{rest}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetUserAliasTypes", desc = "Used to get the UserAliasTypes", req_Params = {"GroupId;string"}, opt_Params = {"SearchString;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getXml_GetUserAliasTypes(@PathParam("groupid") String groupId, @PathParam("searchstring") String searchString) {
        return getUserAliasTypes(groupId, searchString);
    }

    /**
     * GetUserAliasTypes
     * @param groupid
     * @param searchString
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getuseraliastypes/json/{json}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoPricePlans getJson_GetUserAliasTypes(@PathParam("groupid") String groupId, @PathParam("searchstring") String searchString) {
        return getUserAliasTypes(groupId, searchString);
    }

    /**
     * GetProducts
     * @param groupid
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getproducts/rest/{rest}/groupid/{groupid}{countryid:(/countryid/[^/]+?)?}")
    @RESTDoc(methodName = "GetProducts", desc = "Used to get the Products", req_Params = {"GroupId;string"}, opt_Params = {"CountryId;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getXml_GetProducts(@PathParam("groupid") String groupId, @PathParam("countryid") String countryId) throws Exception {
        return getProducts(groupId, countryId);
    }

    /**
     * GetProducts
     * @param groupid
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getproducts/json/{json}/groupid/{groupid}{countryid:(/countryid/[^/]+?)?}")
    public TingcoPricePlans getJson_GetProducts(@PathParam("groupid") String groupId, @PathParam("countryid") String countryId) throws Exception {
        return getProducts(groupId, countryId);
    }

    /**
     * GetProductCategories
     * @param groupid
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getproductcategories/rest/{rest}/groupid/{groupid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetProductCategories", desc = "Used to get the ProductCategories", req_Params = {"GroupId;string", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getXml_GetProductCategories(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) throws Exception {
        return getProductCategories(groupId, countryId);
    }

    /**
     * GetProductCategories
     * @param groupid
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getproductcategories/json/{json}/groupid/{groupid}/countryid/{countryid}")
    public TingcoPricePlans getJson_GetProductCategories(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) throws Exception {
        return getProductCategories(groupId, countryId);
    }

    /**
     * GetProductVariants
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getproductvariants/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetProductVariants", desc = "Used to get the ProductVariants", req_Params = {"CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getXml_GetProductVariants(@PathParam("countryid") int countryId) throws Exception {
        return getProductVariants(countryId);
    }

    /**
     * GetProductVariants
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getproductvariants/json/{json}/countryid/{countryid}")
    public TingcoPricePlans getJson_GetProductVariants(@PathParam("countryid") int countryId) throws Exception {
        return getProductVariants(countryId);
    }

    /**
     * addpriceplanversion
     * @param priceplanid
     * @param versionname
     * @param versiondescription
     * @param activeFromDate
     * @param activeToDate
     * @param priority
     * @param currencyid
     * @param isenabled
     * @return TingcoPricePlans
     * @throws javax.xml.datatype.Exception
     */
    @GET
    @Produces("application/xml")
    @Path("/addpriceplanversion/rest/{rest}/priceplanid/{priceplanid}/versionname/{versionname}{versiondescription:(/versiondescription/[^/]+?)?}/activefromdate/{activefromdate}/activetodate/{activetodate}/priority/{priority}/currencyid/{currencyid}/isenabled/{isenabled}")
    public TingcoPricePlans getXml_AddPricePlanVersion(@PathParam("priceplanid") String priceplanid, @PathParam("versionname") String versionname, @PathParam("versiondescription") String versiondescription, @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate, @PathParam("priority") String priority, @PathParam("currencyid") String currencyid, @PathParam("isenabled") String isenabled) throws Exception {
        return addPricePlanVersion(priceplanid, versionname, versiondescription, activeFromDate, activeToDate, priority, currencyid, isenabled);
    }

    /**
     * addpriceplanversion
     * @param priceplanid
     * @param versionname
     * @param versiondescription
     * @param activeFromDate
     * @param activeToDate
     * @param priority
     * @param currencyid
     * @param isenabled
     * @return TingcoPricePlans
     * @throws javax.xml.datatype.Exception
     */
    @GET
    @Produces("application/json")
    @Path("/addpriceplanversion/json/{json}/priceplanid/{priceplanid}/versionname/{versionname}{versiondescription:(/versiondescription/[^/]+?)?}/activefromdate/{activefromdate}/activetodate/{activetodate}/priority/{priority}/currencyid/{currencyid}/isenabled/{isenabled}")
    public TingcoPricePlans getJson_AddPricePlanVersion(@PathParam("priceplanid") String priceplanid, @PathParam("versionname") String versionname, @PathParam("versiondescription") String versiondescription, @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate, @PathParam("priority") String priority, @PathParam("currencyid") String currencyid, @PathParam("isenabled") String isenabled) throws Exception {
        return addPricePlanVersion(priceplanid, versionname, versiondescription, activeFromDate, activeToDate, priority, currencyid, isenabled);
    }

    /**
     * additemforversion
     * @param priceplanversionid
     * @param priceplanitemparentid
     * @param itemamount
     * @param itempercent
     * @param itemname
     * @param priceplanitemtypeid
     * @param chargetypeid
     * @param validationdatatypeid
     * @param validationexpressionid
     * @param validationvalue
     * @param validationdatatype
     * @param validationexpression
     * @param isenabled
     * @return TingcoPricePlans
     * @throws javax.xml.datatype.Exception
     */
    @GET
    @Produces("application/xml")
    @Path("/additemforversion/rest/{rest}/priceplanversionid/{priceplanversionid}{priceplanitemparentid:(/priceplanitemparentid/[^/]+?)?}{itemamount:(/itemamount/[^/]+?)?}{itempercent:(/itempercent/[^/]+?)?}/itemname/{itemname}/priceplanitemtypeid/{priceplanitemtypeid}/chargetypeid/{chargetypeid}/validationdatatypeid/{validationdatatypeid}/validationexpressionid/{validationexpressionid}/validationvalue/{validationvalue}/validationdatatype/{validationdatatype}/validationexpression/{validationexpression}/isenabled/{isenabled}")
    @RESTDoc(methodName = "addItemForVersion", desc = "Used to add in PricePlanItems", req_Params = {"priceplanversionid;string", "itemname;string", "priceplanitemtypeid;string", "chargetypeid;string", "validationdatatypeid;UUID", "validationexpressionid;UUID", "validationvalue;string", "validationdatatype;string", "validationexpression;string", "IsEnabled;string"}, opt_Params = {"itemamount;string", "itempercent;string", "priceplanitemparentid;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getXml_AddItemForVersion(@PathParam("priceplanversionid") String priceplanversionid, @PathParam("priceplanitemparentid") String priceplanitemparentid, @PathParam("itemamount") String itemamount, @PathParam("itempercent") String itempercent, @PathParam("itemname") String itemname, @PathParam("priceplanitemtypeid") String priceplanitemtypeid, @PathParam("chargetypeid") String chargetypeid, @PathParam("validationdatatypeid") String validationdatatypeid, @PathParam("validationexpressionid") String validationexpressid, @PathParam("validationvalue") String validationvalue, @PathParam("validationdatatype") String validationdatatype, @PathParam("validationexpression") String validationexpression, @PathParam("isenabled") String isenabled) throws Exception {
        return addItemForVersion(priceplanversionid, priceplanitemparentid, itemamount, itempercent, itemname, priceplanitemtypeid, chargetypeid, validationdatatypeid, validationexpressid, validationvalue, validationdatatype, validationexpression, isenabled);
    }

    /**
     * additemforversion
     * @param priceplanversionid
     * @param priceplanitemparentid
     * @param itemamount
     * @param itempercent
     * @param itemname
     * @param priceplanitemtypeid
     * @param chargetypeid
     * @param validationdatatypeid
     * @param validationexpressid
     * @param validationvalue
     * @param validationdatatype
     * @param validationexpression
     * @param isenabled
     * @return TingcoPricePlans
     * @throws javax.xml.datatype.Exception
     */
    @GET
    @Produces("application/json")
    @Path("/additemforversion/json/{json}/priceplanversionid/{priceplanversionid}{priceplanitemparentid:(/priceplanitemparentid/[^/]+?)?}{itemamount:(/itemamount/[^/]+?)?}{itempercent:(/itempercent/[^/]+?)?}/itemname/{itemname}/priceplanitemtypeid/{priceplanitemtypeid}/chargetypeid/{chargetypeid}/validationdatatypeid/{validationdatatypeid}/validationexpressid/{validationexpressid}/validationvalue/{validationvalue}/validationdatatype/{validationdatatype}/validationexpression/{validationexpression}/isenabled/{isenabled}")
    public TingcoPricePlans getJson_AddItemForVersion(@PathParam("priceplanversionid") String priceplanversionid, @PathParam("priceplanitemparentid") String priceplanitemparentid, @PathParam("itemamount") String itemamount, @PathParam("itempercent") String itempercent, @PathParam("itemname") String itemname, @PathParam("priceplanitemtypeid") String priceplanitemtypeid, @PathParam("chargetypeid") String chargetypeid, @PathParam("validationdatatypeid") String validationdatatypeid, @PathParam("validationexpressid") String validationexpressid, @PathParam("validationvalue") String validationvalue, @PathParam("validationdatatype") String validationdatatype, @PathParam("validationexpression") String validationexpression, @PathParam("isenabled") String isenabled) throws Exception {
        return addItemForVersion(priceplanversionid, priceplanitemparentid, itemamount, itempercent, itemname, priceplanitemtypeid, chargetypeid, validationdatatypeid, validationexpressid, validationvalue, validationdatatype, validationexpression, isenabled);
    }

    /**
     * updateversionforpriceplan
     * @param priceplanversionid
     * @param versionname
     * @param versiondescription
     * @param activeFromDate
     * @param activeToDate
     * @param priority
     * @param currencyid
     * @param isenabled
     * @return TingcoPricePlans
     * @throws javax.xml.datatype.Exception
     */
    @GET
    @Produces("application/xml")
    @Path("/updateversionforpriceplan/rest/{rest}/priceplanversionid/{priceplanversionid}/versionname/{versionname}{versiondescription:(/versiondescription/[^/]+?)?}/activefromdate/{activefromdate}/activetodate/{activetodate}/priority/{priority}/currencyid/{currencyid}/isenabled/{isenabled}")
    @RESTDoc(methodName = "UpdateVersionForPriceplan", desc = "Used to add in PricePlan", req_Params = {"PricePlanName;string", "PricePlanDescription;string", "ActiveFromdate;string", "ActiveToDate;string", "PricePlanTypeId;UUID", "GroupId;UUID", "IsEnabled;string"}, opt_Params = {"VersionDescription;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getXml_UpdateVersionForPriceplan(@PathParam("priceplanversionid") String priceplanversionid, @PathParam("versionname") String versionname, @PathParam("versiondescription") String versionDescription, @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate, @PathParam("priority") String priority, @PathParam("currencyid") String currencyid, @PathParam("isenabled") String isenabled) throws Exception {
        return updateVersionForPriceplan(priceplanversionid, versionname, versionDescription, activeFromDate, activeToDate, priority, currencyid, isenabled);
    }

    /**
     * updateversionforpriceplan
     * @param priceplanversionid
     * @param versionname
     * @param activeFromDate
     * @param activeToDate
     * @param priority
     * @param currencyid
     * @param isenabled
     * @return TingcoPricePlans
     * @throws javax.xml.datatype.Exception
     */
    @GET
    @Produces("application/json")
    @Path("/updateversionforpriceplan/json/{json}/priceplanversionid/{priceplanversionid}/versionname/{versionname}{versiondescription:(/versiondescription/[^/]+?)?}/activefromdate/{activefromdate}/activetodate/{activetodate}/priority/{priority}/currencyid/{currencyid}/isenabled/{isenabled}")
    public TingcoPricePlans getJson_UpdateVersionForPriceplan(@PathParam("priceplanversionid") String priceplanversionid, @PathParam("versionname") String versionname, @PathParam("versiondescription") String versionDescription, @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate, @PathParam("priority") String priority, @PathParam("currencyid") String currencyid, @PathParam("isenabled") String isenabled) throws Exception {
        return updateVersionForPriceplan(priceplanversionid, versionname, versionDescription, activeFromDate, activeToDate, priority, currencyid, isenabled);
    }

    /**
     * updateitemforversion
     * @param priceplanitemid
     * @param priceplanversionid
     * @param itemamount
     * @param itempercent
     * @param itemname
     * @param priceplanitemtypeid
     * @param chargetypeid
     * @param validationdatatypeid
     * @param validationexpressionid
     * @param validationvalue
     * @param validationdatatype
     * @param validationexpression
     * @param isenabled
     * @return TingcoPricePlans
     * @throws javax.xml.datatype.Exception
     */
    @GET
    @Produces("application/xml")
    @Path("/updateitemforversion/rest/{rest}/priceplanitemid/{priceplanitemid}/priceplanversionid/{priceplanversionid}{itemamount:(/itemamount/[^/]+?)?}{itempercent:(/itempercent/[^/]+?)?}/itemname/{itemname}/priceplanitemtypeid/{priceplanitemtypeid}/chargetypeid/{chargetypeid}/validationdatatypeid/{validationdatatypeid}/validationexpressionid/{validationexpressionid}/validationvalue/{validationvalue}/validationdatatype/{validationdatatype}/validationexpression/{validationexpression}/isenabled/{isenabled}")
//    @RESTDoc(methodName = "addItemForVersion", desc = "Used to add in PricePlan", req_Params = {"PricePlanName;string", "PricePlanDescription;string", "ActiveFromdate;string", "ActiveToDate;string", "PricePlanTypeId;UUID", "GroupId;UUID", "IsEnabled;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getXml_UpdateItemsForVersionId(@PathParam("priceplanitemid") String priceplanitemid, @PathParam("priceplanversionid") String priceplanversionid, @PathParam("itemamount") String itemamount, @PathParam("itempercent") String itempercent, @PathParam("itemname") String itemname, @PathParam("priceplanitemtypeid") String priceplanitemtypeid, @PathParam("chargetypeid") String chargetypeid, @PathParam("validationdatatypeid") String validationdatatypeid, @PathParam("validationexpressionid") String validationexpressionid, @PathParam("validationvalue") String validationvalue, @PathParam("validationdatatype") String validationdatatype, @PathParam("validationexpression") String validationexpression, @PathParam("isenabled") String isenabled) throws Exception {
        return updateItemsForVersionId(priceplanitemid, priceplanversionid, itemamount, itempercent, itemname, priceplanitemtypeid, chargetypeid, validationdatatypeid, validationexpressionid, validationvalue, validationdatatype, validationexpression, isenabled);
    }

    /**
     * updateitemforversion
     * @param priceplanitemid
     * @param priceplanversionid
     * @param itemamount
     * @param itempercent
     * @param itemname
     * @param priceplanitemtypeid
     * @param chargetypeid
     * @param validationdatatypeid
     * @param validationexpressionid
     * @param validationvalue
     * @param validationdatatype
     * @param validationexpression
     * @param isenabled
     * @return TingcoPricePlans
     * @throws javax.xml.datatype.Exception
     */
    @GET
    @Produces("application/json")
    @Path("/updateitemforversion/json/{json}/priceplanitemid/{priceplanitemid}/priceplanversionid/{priceplanversionid}{itemamount:(/itemamount/[^/]+?)?}{itempercent:(/itempercent/[^/]+?)?}/itemname/{itemname}/priceplanitemtypeid/{priceplanitemtypeid}/chargetypeid/{chargetypeid}/validationdatatypeid/{validationdatatypeid}/validationexpressionid/{validationexpressionid}/validationvalue/{validationvalue}/validationdatatype/{validationdatatype}/validationexpression/{validationexpression}/isenabled/{isenabled}")
    public TingcoPricePlans getJson_UpdateItemsForVersionId(@PathParam("priceplanitemid") String priceplanitemid, @PathParam("priceplanversionid") String priceplanversionid, @PathParam("itemamount") String itemamount, @PathParam("itempercent") String itempercent, @PathParam("itemname") String itemname, @PathParam("priceplanitemtypeid") String priceplanitemtypeid, @PathParam("chargetypeid") String chargetypeid, @PathParam("validationdatatypeid") String validationdatatypeid, @PathParam("validationexpressionid") String validationexpressionid, @PathParam("validationvalue") String validationvalue, @PathParam("validationdatatype") String validationdatatype, @PathParam("validationexpression") String validationexpression, @PathParam("isenabled") String isenabled) throws Exception {
        return updateItemsForVersionId(priceplanitemid, priceplanversionid, itemamount, itempercent, itemname, priceplanitemtypeid, chargetypeid, validationdatatypeid, validationexpressionid, validationvalue, validationdatatype, validationexpression, isenabled);
    }

    /**
     * deleteversionforpriceplan
     * @param priceplanversionsid
     * @return TingcoPricePlans
     * @throws javax.xml.datatype.Exception
     */
    @GET
    @Produces("application/xml")
    @Path("/deleteversionforpriceplan/rest/{rest}/priceplanversionsid/{priceplanversionsid}")
    @RESTDoc(methodName = "addItemForVersion", desc = "Used to add in PricePlan", req_Params = {"PricePlanName;string", "PricePlanDescription;string", "ActiveFromdate;string", "ActiveToDate;string", "PricePlanTypeId;UUID", "GroupId;UUID", "IsEnabled;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getXml_DeletePricePlanVersionsById(@PathParam("priceplanversionsid") String priceplanversionsid) throws Exception {
        return DeletePricePlanVersionsById(priceplanversionsid);
    }

    /**
     * deletevesionforpriceplan
     * @param priceplanversionsid
     * @return TingcoPricePlans
     * @throws javax.xml.datatype.Exception
     */
    @GET
    @Produces("application/json")
    @Path("/deleteversionforpriceplan/json/{json}/priceplanversionsid/{priceplanversionsid}")
    @RESTDoc(methodName = "addItemForVersion", desc = "Used to add in PricePlan", req_Params = {"PricePlanName;string", "PricePlanDescription;string", "ActiveFromdate;string", "ActiveToDate;string", "PricePlanTypeId;UUID", "GroupId;UUID", "IsEnabled;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getJson_DeletePricePlanVersionsById(@PathParam("priceplanversionsid") String priceplanversionsid) throws Exception {
        return DeletePricePlanVersionsById(priceplanversionsid);
    }

    /**
     * DeleteItemsForVersion
     * @param priceplanid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deleteitemsforversion/rest/{rest}/priceplanitemid/{priceplanitemid}/priceplanversionid/{priceplanversionid}")
    @RESTDoc(methodName = "DeleteItemsForVersion", desc = "Used to delete PricePlanItems", req_Params = {"PricePlanItemId;string", "PricePlanVersionId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getXml_DeletePricePlanItems(@PathParam("priceplanitemid") String pricePlanItemId, @PathParam("priceplanversionid") String pricePlanVersionId) throws Exception {
        return deletePricePlanItemsForVersion(pricePlanItemId, pricePlanVersionId);
    }

    /**
     * DeleteItemsForVersion
     * @param priceplanid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deleteitemsforversion/json/{json}/priceplanitemid/{priceplanitemid}/priceplanversionid/{priceplanversionid}")
    public TingcoPricePlans getJson_DeletePricePlanItems(@PathParam("priceplanitemid") String pricePlanItemId, @PathParam("priceplanversionid") String pricePlanVersionId) throws Exception {
        return deletePricePlanItemsForVersion(pricePlanItemId, pricePlanVersionId);
    }

    /**
     *
     * @param countryID
     * @return
     * @throws java.lang.Exception
     */
    @GET
    @Produces("application/xml")
    @Path("/getpriceplans/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "getpriceplans", desc = "Used to get the ValidationDataTypes", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getXml_GetPricePlans(@PathParam("countryid") String countryID) throws Exception {
        return getPricePlans(countryID);
    }

    /**
     *
     * @param countryID
     * @return
     * @throws java.lang.Exception
     */
    @GET
    @Produces("application/json")
    @Path("/getpriceplans/json/{json}/countryid/{countryid}")
    @RESTDoc(methodName = "GetValidationDataTypes", desc = "Used to get the ValidationDataTypes", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getJson_GetPricePlans(@PathParam("countryid") String countryID) throws Exception {
        return getPricePlans(countryID);
    }

    /**
     * GetPricePlansBySearchString
     * @param countryID
     * @param groupId
     * @param searchString
     * @return
     * @throws java.lang.Exception
     */
    @GET
    @Produces("application/xml")
    @Path("/getpriceplansbysearchstring/rest/{rest}/countryid/{countryid}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetPricePlansBySearchString", desc = "Used to Get PricePlans By Search String", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getXml_GetPricePlansBySearchString(@PathParam("countryid") String countryID, @PathParam("groupid") String groupId, @PathParam("searchstring") String searchString) throws Exception {
        return getPricePlansBySearchString(countryID, groupId, searchString);
    }

    /**
     * GetPricePlansBySearchString
     * @param countryID
     * @param groupId
     * @param searchString
     * @return
     * @throws java.lang.Exception
     */
    @GET
    @Produces("application/json")
    @Path("/getpriceplansbysearchstring/json/{json}/countryid/{countryid}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoPricePlans getJson_GetPricePlansBySearchString(@PathParam("countryid") String countryID, @PathParam("groupid") String groupId, @PathParam("searchstring") String searchString) throws Exception {
        return getPricePlansBySearchString(countryID, groupId, searchString);
    }

    /**
     * GetPricePlanList
     * @param countryID
     * @param groupId
     * @param pricePlanName
     * @return
     * @throws java.lang.Exception
     */
    @GET
    @Produces("application/xml")
    @Path("/getpriceplanlist/rest/{rest}/countryid/{countryid}{organization:(/organization/[^/]+?)?}{priceplanname:(/priceplanname/[^/]+?)?}")
    @RESTDoc(methodName = "GetPricePlanList", desc = "Used to Get PricePlan List", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getXml_GetPricePlanList(@PathParam("countryid") String countryID, @PathParam("organization") String organization, @PathParam("priceplanname") String pricePlanName) throws Exception {
        return getPricePlanList(countryID, organization, pricePlanName);
    }

    /**
     * GetPricePlanList
     * @param countryID
     * @param groupId
     * @param pricePlanName
     * @return
     * @throws java.lang.Exception
     */
    @GET
    @Produces("application/json")
    @Path("/getpriceplanlist/json/{json}/countryid/{countryid}{organization:(/organization/[^/]+?)?}{priceplanname:(/priceplanname/[^/]+?)?}")
    public TingcoPricePlans getJson_GetPricePlanList(@PathParam("countryid") String countryID, @PathParam("organization") String organization, @PathParam("priceplanname") String pricePlanName) throws Exception {
        return getPricePlanList(countryID, organization, pricePlanName);
    }

    /**
     * GetPricePlanDetailsById
     * @param countryID
     * @param pricePlanId
     * @return
     * @throws java.lang.Exception
     */
    @GET
    @Produces("application/xml")
    @Path("/getpriceplandetailsbyid/rest/{rest}/countryid/{countryid}/priceplanid/{priceplanid}")
    @RESTDoc(methodName = "GetPricePlanDetailsById", desc = "Used to Get PricePlan Details By Id", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getXml_GetPricePlanDetailsById(@PathParam("countryid") String countryID, @PathParam("priceplanid") String pricePlanId) throws Exception {
        return getPricePlanDetailsById(countryID, pricePlanId);
    }

    /**
     * GetPricePlanDetailsById
     * @param countryID
     * @param pricePlanId
     * @return
     * @throws java.lang.Exception
     */
    @GET
    @Produces("application/json")
    @Path("/getpriceplandetailsbyid/json/{json}/countryid/{countryid}/priceplanid/{priceplanid}")
    public TingcoPricePlans getJson_GetPricePlanDetailsById(@PathParam("countryid") String countryID, @PathParam("priceplanid") String pricePlanId) throws Exception {
        return getPricePlanDetailsById(countryID, pricePlanId);
    }

    @GET
    @Produces("application/xml")
    @Path("/getversionsbypriceid/rest/{rest}/priceplanid/{priceplanid}")
    @RESTDoc(methodName = "GetVersionsByPriceId", desc = "Used to Get Versions By PriceId", req_Params = {"PricePlanId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getXml_GetVersionsByPriceId(@PathParam("priceplanid") String pricePlanId) throws Exception {
        return getVersionsByPriceId(pricePlanId);
    }

    @GET
    @Produces("application/json")
    @Path("/getversionsbypriceid/json/{json}/priceplanid/{priceplanid}")
    public TingcoPricePlans getJson_GetVersionsByPriceId(@PathParam("priceplanid") String pricePlanId) throws Exception {
        return getVersionsByPriceId(pricePlanId);
    }

      /**
     * GetPricePlanItemDetails
     * @param priceplanid, countryid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getpriceplanitembyitemid/rest/{rest}/priceplanitemid/{priceplanitemid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetPricePlanItemTables", desc = "Used to get the PricePlanItemdetails", req_Params = {"PricePlanVersionId;string", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoPricePlans getXml_GetPricePlanItemByItemId(@PathParam("priceplanitemid") String pricePlanItemId, @PathParam("countryid") int countryId) throws Exception {
        return getPricePlanItemByItemId(pricePlanItemId, countryId);
    }

    /**
     * GetPricePlanItemDetails
     * @param priceplanid, countryid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getpriceplanitembyitemid/json/{json}/priceplanitemid/{priceplanitemid}/countryid/{countryid}")
    public TingcoPricePlans getJson_GetPricePlanItemByItemId(@PathParam("priceplanitemid") String pricePlanItemId, @PathParam("countryid") int countryId) throws Exception {
        return getPricePlanItemByItemId(pricePlanItemId, countryId);
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public PricePlansResource getPricePlansResource() {
        return new PricePlansResource();
    }

    private TingcoPricePlans getPricePlanItemByItemId(String pricePlanItemId, int countryId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
            if (request.getAttribute("USERSESSION") != null) {                
                if (countryId <= 0) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("CountryId should not be empty");
                    return tingcoPricePlans;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRICEPLAN)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRICEPLAN);
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
//                        List<se.info24.pojo.PricePlanItems> pricePlanItemsList = dao.getPricePlanItemDetails(session, pricePlanVersionId);
                        se.info24.pojo.PricePlanItems pricePlanItems = (se.info24.pojo.PricePlanItems) session.get(se.info24.pojo.PricePlanItems.class, pricePlanItemId);
                        if (pricePlanItems != null) {
                             List<se.info24.pojo.PricePlanItems> pricePlanItemsList = new ArrayList<se.info24.pojo.PricePlanItems>();
                             pricePlanItemsList.add(pricePlanItems);
                            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlanItems(tingcoPricePlans, pricePlanItemsList, countryId, session, timeZoneGMToffset);
                        } else {
                            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                            tingcoPricePlans.getMsgStatus().setResponseText("No Data found");
                            return tingcoPricePlans;
                        }
                        return tingcoPricePlans;
                    } else {
                        tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                        tingcoPricePlans.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                        return tingcoPricePlans;
                    }

                } else {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                    tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoPricePlans;
                }
            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-3);
                tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoPricePlans;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
            return tingcoPricePlans;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }

    }

    private TingcoPricePlans getVersionsByPriceId(String pricePlanId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRICEPLAN)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRICEPLAN);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<PricePlanVersions> pricePlanVersionsList = dao.getPricePlanVersionsByPricePlanId(session, pricePlanId);

                    if (!pricePlanVersionsList.isEmpty()) {
                        Set<Integer> currencyIds = new HashSet<Integer>();
                        Set<String> ppVersionIds = new HashSet<String>();
                        for (PricePlanVersions ppv : pricePlanVersionsList) {
                            currencyIds.add(ppv.getCurrencyId());
                            ppVersionIds.add(ppv.getPricePlanVersionId());
                        }
                        DeviceDAO deviceDAO = new DeviceDAO();
                        List<Currency> currencys = deviceDAO.getCurrencyByCurrencyId(session, currencyIds);
                        pricePlanVersionsList = dao.getPricePlanVersionsByIdsOrderByVersionName(session, ppVersionIds);
                        tingcoPricePlans = tingcoPricePlansXML.buildVersionsByPriceId(tingcoPricePlans, pricePlanVersionsList, currencys);
                    } else {
                        tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                        tingcoPricePlans.getMsgStatus().setResponseText("No Data found");
                        return tingcoPricePlans;
                    }
                } else {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                    tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoPricePlans;
                }
            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-3);
                tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoPricePlans;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
            return tingcoPricePlans;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoPricePlans;
    }

    private TingcoPricePlans getPricePlanDetailsById(String countryID, String pricePlanId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (pricePlanId.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("PricePlanId should not be empty");
                    return tingcoPricePlans;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRICEPLAN)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRICEPLAN);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    PricePlans pricePlans = dao.getPricePlansByPricePlanId(session, pricePlanId);
                    if (pricePlans != null) {
                        UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                        if (userTimeZones2 != null) {
                            Object pptt = dao.getPricePlanTypeTranslationsByCountryAndPricePlanTypeID(pricePlans.getPricePlanTypes().getPricePlanTypeId(), Integer.parseInt(countryID), session);
                            GroupTranslations groupTranslations = groupDAO.getGroupTranslationsByIds(pricePlans.getGroupId(), Integer.parseInt(countryID), session);
                            String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                            tingcoPricePlans = tingcoPricePlansXML.buildPricePlanDetailsById(tingcoPricePlans, timeZoneGMToffset, pricePlans, groupTranslations, pptt);
                        } else {
                            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                            tingcoPricePlans.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                            return tingcoPricePlans;
                        }
                    } else {
                        tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                        tingcoPricePlans.getMsgStatus().setResponseText("No Data found");
                        return tingcoPricePlans;
                    }
                    return tingcoPricePlans;
                } else {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                    tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoPricePlans;
                }
            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-3);
                tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoPricePlans;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
            return tingcoPricePlans;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
    }

    private TingcoPricePlans getPricePlanList(String countryID, String organization, String pricePlanName) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (!pricePlanName.equals("")) {
                    pricePlanName = pricePlanName.split("/")[2];
                } else {
                    pricePlanName = null;
                }
                if (!organization.equals("")) {
                    organization = organization.split("/")[2];
                } else {
                    organization = null;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRICEPLAN)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRICEPLAN);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();

                    UserDAO userDAO = new UserDAO();
                    Users2 user = userDAO.getUserById(sessions2.getUserId(), session);
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        List<PricePlans> pricePlanList = new ArrayList<PricePlans>();
                        List<GroupTranslations> groupTranslationses = new ArrayList<GroupTranslations>();
                        List<PricePlanTypeTranslations> pricePlanTypeTranslationses = new ArrayList<PricePlanTypeTranslations>();

                        List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(user.getGroupId(), session);
                        Set<String> groupIdsList = groupDAO.getGroupIdsList(user.getGroupId(), groupTrustsList);

                        /**
                         * SPLIT LIST SIZE 30 MAR 2014
                         */
                        List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupIdsList), 2000);
                        if (pricePlanName == null && organization == null) {
                            for (List<String> list : listsplit) {
                                List<GroupTranslations> groupTranslationsesTemp = groupDAO.getGroupTranslationsById(list, Integer.parseInt(countryID), session);
                                groupTranslationses.addAll(groupTranslationsesTemp);
                                List<PricePlans> pricePlanListTemp = dao.getPricePlansByGivenGroupId(session, new HashSet<String>(list), 200);
                                pricePlanList.addAll(pricePlanListTemp);
                                if (pricePlanList.size() > 200) {
                                    pricePlanList = pricePlanList.subList(0, 200);
                                    break;
                                }
                            }
                            //groupTranslationses = groupDAO.getGroupTranslationsById(groupIds, Integer.parseInt(countryID), session);
                            //pricePlanList = dao.getPricePlansByGivenGroupId(session, groupIdsList, 200);
                            Set<String> pptypeIds = new HashSet<String>();
                            for (PricePlans pp : pricePlanList) {
                                if (pp.getPricePlanTypes() != null) {
                                    pptypeIds.add(pp.getPricePlanTypes().getPricePlanTypeId());
                                }
                            }
                            if (!pptypeIds.isEmpty()) {
                                pricePlanTypeTranslationses = dao.getPricePlanTypeTranslationsByID(pptypeIds, Integer.parseInt(countryID), session);
                            }
                        } else {
                            for (List<String> list : listsplit) {
                                List<PricePlans> pricePlanListTemp = dao.getPricePlansBySearch(session, pricePlanName, organization, new HashSet<String>(list), Integer.parseInt(countryID));
                                pricePlanList.addAll(pricePlanListTemp);
                                if (pricePlanList.size() > 200) {
                                    pricePlanList = pricePlanList.subList(0, 200);
                                    break;
                                }
                            }
                            //pricePlanList = dao.getPricePlansBySearch(session, pricePlanName,organization,groupIdsList,Integer.parseInt(countryID));
                            List<String> searchedGroupIds = new ArrayList<String>();
                            Set<String> pptypeIds = new HashSet<String>();
                            for (PricePlans pp : pricePlanList) {
                                searchedGroupIds.add(pp.getGroupId());
                                if (pp.getPricePlanTypes() != null) {
                                    pptypeIds.add(pp.getPricePlanTypes().getPricePlanTypeId());
                                }
                            }
                            if (!pptypeIds.isEmpty()) {
                                pricePlanTypeTranslationses = dao.getPricePlanTypeTranslationsByID(pptypeIds, Integer.parseInt(countryID), session);
                            }
                            if (!searchedGroupIds.isEmpty()) {
                                groupTranslationses = groupDAO.getGroupTranslationsById(searchedGroupIds, Integer.parseInt(countryID), session);
                            }
                        }
                        if (pricePlanList.isEmpty()) {
                            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                            tingcoPricePlans.getMsgStatus().setResponseText("No Data found");
                            return tingcoPricePlans;
                        }
                        List<String> ids = new ArrayList<String>();
                        for (PricePlans pp : pricePlanList) {
                            ids.add(pp.getPricePlanId());
                        }
                        pricePlanList.clear();
                        pricePlanList.addAll(dao.getPricePlans(session, ids));
                        tingcoPricePlans = tingcoPricePlansXML.buildGetPricePlansList(tingcoPricePlans, pricePlanList, groupTranslationses, pricePlanTypeTranslationses, timeZoneGMToffset);
                    }
                } else {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                    tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoPricePlans;
                }
            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-3);
                tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoPricePlans;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
            return tingcoPricePlans;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoPricePlans;
    }

    private TingcoPricePlans getPricePlansBySearchString(String countryID, String groupId, String searchString) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (!searchString.equals("")) {
                    searchString = searchString.split("/")[2];
                } else {
                    searchString = null;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRICEPLAN)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRICEPLAN);
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
                    Set<String> groupIdsList = groupDAO.getGroupIdsList(groupId, groupTrustsList);
                    List<PricePlans> pricePlanList = new ArrayList<PricePlans>();
                    //List<String> groupIds = new ArrayList<String>(groupIdsList);
                    /**
                     * SPLIT LIST SIZE 30 MAR 2014
                     */
                    List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupIdsList), 2000);
                    List<GroupTranslations> groupTranslationses = new ArrayList<GroupTranslations>();
                    for (List<String> list : listsplit) {
                        List<GroupTranslations> groupTranslationsesTemp = groupDAO.getGroupTranslationsById(list, Integer.parseInt(countryID), session);
                        groupTranslationses.addAll(groupTranslationsesTemp);
                    }
                    //List<GroupTranslations> groupTranslationses = groupDAO.getGroupTranslationsById(groupIds, Integer.parseInt(countryID), session);
                    if (searchString != null) {
                        if (TCMUtil.isValidUUID(searchString)) {
                            pricePlanList.add(dao.getPricePlansByPricePlanId(session, searchString));
                        } else {
                            for (List<String> list : listsplit) {
                                List<PricePlans> pricePlanListTemp = dao.getPricePlansByGroupIdAndSearchString(session, new HashSet<String>(list), searchString, 200);
                                pricePlanList.addAll(pricePlanListTemp);
                                if (pricePlanList.size() > 200) {
                                    pricePlanList = pricePlanList.subList(0, 200);
                                    break;
                                }
                            }
                        //pricePlanList = dao.getPricePlansByGroupIdAndSearchString(session, groupIdsList, searchString, 200);
                        }
                    } else {
                        for (List<String> list : listsplit) {
                            List<PricePlans> pricePlanListTemp = dao.getPricePlansByGivenGroupId(session, new HashSet<String>(list), 200);
                            pricePlanList.addAll(pricePlanListTemp);
                            if (pricePlanList.size() > 200) {
                                pricePlanList = pricePlanList.subList(0, 200);
                                break;
                            }
                        }
                    //pricePlanList = dao.getPricePlansByGivenGroupId(session, groupIdsList,200);
                    }
                    if (pricePlanList.isEmpty()) {
                        tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                        tingcoPricePlans.getMsgStatus().setResponseText("No Data found");
                        return tingcoPricePlans;
                    }
                    List<String> ids = new ArrayList<String>();
                    for (PricePlans pp : pricePlanList) {
                        ids.add(pp.getPricePlanId());
                    }
                    pricePlanList.clear();
                    pricePlanList.addAll(dao.getPricePlans(session, ids));
                    tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansBySearchString(tingcoPricePlans, pricePlanList, groupTranslationses);
                } else {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                    tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoPricePlans;
                }
            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-3);
                tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoPricePlans;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
            return tingcoPricePlans;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoPricePlans;
    }

    private TingcoPricePlans getPricePlans(String countryID) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
            if (request.getAttribute("USERSESSION") != null) {

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.AGREEMENTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.AGREEMENTS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserDAO userDAO = new UserDAO();
                    Users2 users2 = userDAO.getUserById(sessions2.getUserId(), session);
                    Set<String> groupIdsList = groupDAO.getGroupsAndGroupTrusts(users2.getGroupId(), session);
                    List<PricePlans> pricePlanList = new ArrayList<PricePlans>();
                    List<List<String>> spList = TCMUtil.splitStringList(new ArrayList<String>(groupIdsList), 2000);
                    for (List<String> list : spList) {
                        List<PricePlans> pricePlanListTemp = dao.getPricePlansById(session, new HashSet<String>(list));
                        if(pricePlanListTemp != null){
                            pricePlanList.addAll(pricePlanListTemp);
                        }
                    }

                    
                    if (pricePlanList.isEmpty()) {
                        tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                        tingcoPricePlans.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoPricePlans;
                    }
                    List<String> pricePlanceIdList = new ArrayList<String>();
                    for (PricePlans pricePlans : pricePlanList) {
                        pricePlanceIdList.add(pricePlans.getPricePlanId());
                    }
                    pricePlanList.clear();
                    pricePlanList = dao.getPricePlans(session, pricePlanceIdList);
                    List<GroupTranslations> groupTranslationsList = new ArrayList<GroupTranslations>();
                    spList = TCMUtil.splitStringList(new ArrayList<String>(groupIdsList), 2000);
                    for (List<String> list : spList) {
                        List<GroupTranslations> groupTranslationsListTest = groupDAO.getGroupTranslationsById(list, Integer.valueOf(countryID), session);
                        if(groupTranslationsListTest != null){
                            groupTranslationsList.addAll(groupTranslationsListTest);
                        }
                    }
                    
                    return tingcoPricePlansXML.buildgetPricePlans(tingcoPricePlans, pricePlanList, groupTranslationsList);
                } else {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                    tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoPricePlans;
                }
            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-3);
                tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoPricePlans;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
            return tingcoPricePlans;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
    }

    private TingcoPricePlans deletePricePlanItemsForVersion(String pricePlanItemId, String pricePlanVersionId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (pricePlanItemId.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("pricePlanItemId should not be empty");
                    return tingcoPricePlans;
                }
                if (pricePlanVersionId.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("pricePlanVersionId should not be empty");
                    return tingcoPricePlans;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRICEPLAN)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRICEPLAN);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                            hasPermission = true;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object object = dao.getPricePlanItems(session, pricePlanItemId, pricePlanVersionId);
                    if(object == null){
                        tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                        tingcoPricePlans.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoPricePlans;
                    }
                    if (!dao.deletePricePlanItems(pricePlanItemId, pricePlanVersionId, session)) {
                        tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                        tingcoPricePlans.getMsgStatus().setResponseText("Error Occured while Deleting");
                        return tingcoPricePlans;
                    }
                    return tingcoPricePlans;
                } else {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                    tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoPricePlans;
                }
            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-3);
                tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoPricePlans;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
            return tingcoPricePlans;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }

    }

    private TingcoPricePlans getChargeTypes(int countryId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (countryId <= 0) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("CountryId should not be empty");
                    return tingcoPricePlans;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRICEPLAN)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRICEPLAN);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ChargeTypeTranslations> chargeTypeTranslationsList = dao.getChargeTypeTranslations(session, countryId);
                    if (!chargeTypeTranslationsList.isEmpty()) {
                        tingcoPricePlans = tingcoPricePlansXML.buildTingcoChargeTypeTranslations(tingcoPricePlans, chargeTypeTranslationsList);
                    } else {
                        tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                        tingcoPricePlans.getMsgStatus().setResponseText("No Data found");
                        return tingcoPricePlans;
                    }
                    return tingcoPricePlans;
                } else {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                    tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoPricePlans;
                }

            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-3);
                tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoPricePlans;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
            return tingcoPricePlans;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }

    }

    private TingcoPricePlans getPricePlanDetails(String pricePlanId) throws Exception {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (pricePlanId.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("PricePlanId should not be empty");
                    return tingcoPricePlans;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRICEPLAN)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRICEPLAN);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    PricePlans pricePlans = dao.getPricePlansByPricePlanId(session, pricePlanId);
                    if (pricePlans != null) {
                        UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                        if (userTimeZones2 != null) {
                            String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansDetails(tingcoPricePlans, timeZoneGMToffset, pricePlans);
                        } else {
                            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                            tingcoPricePlans.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                            return tingcoPricePlans;
                        }
                    } else {
                        tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                        tingcoPricePlans.getMsgStatus().setResponseText("No Data found");
                        return tingcoPricePlans;
                    }
                    return tingcoPricePlans;
                } else {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                    tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoPricePlans;
                }
            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-3);
                tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoPricePlans;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
            return tingcoPricePlans;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }

    }

    private TingcoPricePlans getPricePlanTypes(int countryId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (countryId <= 0) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("Country Id should not be empty");
                    return tingcoPricePlans;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRICEPLAN)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRICEPLAN);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<PricePlanTypeTranslations> pricePlanTypeTranslationsList = dao.getPricePlanTypeTranslationsByCountryId(session, countryId);
                    if (!pricePlanTypeTranslationsList.isEmpty()) {
                        tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlanTypeTranslations(tingcoPricePlans, pricePlanTypeTranslationsList);
                    } else {
                        tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                        tingcoPricePlans.getMsgStatus().setResponseText("No Data found");
                        return tingcoPricePlans;
                    }
                    return tingcoPricePlans;
                } else {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                    tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoPricePlans;
                }
            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-3);
                tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoPricePlans;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
            return tingcoPricePlans;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }

    }

    private TingcoPricePlans getPricePlanVersionDetails(String pricePlanId) throws Exception {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (pricePlanId.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("PricePlanId should not be empty");
                    return tingcoPricePlans;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRICEPLAN)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRICEPLAN);
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
                        List<PricePlanVersions> pricePlanVersionsList = dao.getPricePlanVersions(session, pricePlanId);
                        if (!pricePlanVersionsList.isEmpty()) {
                            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlanVersions(tingcoPricePlans, pricePlanVersionsList, session, timeZoneGMToffset);
                        } else {
                            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                            tingcoPricePlans.getMsgStatus().setResponseText("No Data found");
                            return tingcoPricePlans;
                        }
                        return tingcoPricePlans;
                    } else {
                        tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                        tingcoPricePlans.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                        return tingcoPricePlans;
                    }

                } else {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                    tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoPricePlans;
                }
            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-3);
                tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoPricePlans;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
            return tingcoPricePlans;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
    }

    private TingcoPricePlans getPricePlans(String groupId, String searchString) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("Group Id should not be empty");
                    return tingcoPricePlans;
                }
                if (!searchString.equals("")) {
                    searchString = searchString.split("/")[2];
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRICEPLAN)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRICEPLAN);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<PricePlans> pricePlanList = dao.getPricePlansByGroupId(session, groupId, searchString);
                    if (!pricePlanList.isEmpty()) {
                        tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlans(tingcoPricePlans, pricePlanList);
                    } else {
                        tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                        tingcoPricePlans.getMsgStatus().setResponseText("No Data found");
                        return tingcoPricePlans;
                    }
                    return tingcoPricePlans;
                } else {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                    tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoPricePlans;
                }
            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-3);
                tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoPricePlans;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
            return tingcoPricePlans;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }

    }

    private TingcoPricePlans deletePricePlan(String pricePlanId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
            if (request.getAttribute("USERSESSION") != null) {

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRICEPLAN)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRICEPLAN);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                            hasPermission = true;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    if (!dao.deletePricePlan(session, pricePlanId)) {
                        tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                        tingcoPricePlans.getMsgStatus().setResponseText("Error Occured while Deleting");
                        return tingcoPricePlans;
                    }
                    return tingcoPricePlans;
                } else {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                    tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoPricePlans;
                }
            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-3);
                tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoPricePlans;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
            return tingcoPricePlans;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }

    }

    private TingcoPricePlans addPricePlan(String pricePlanName, String pricePlanDescription, String activeFromDate, String activeToDate, String pricePlanTypeId, String groupId, String isEnabled) throws Exception {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (pricePlanName.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("PricePlanName should not be empty");
                    return tingcoPricePlans;
                }
                if (activeFromDate.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("activeFromDate should not be empty");
                    return tingcoPricePlans;
                }
                if (activeToDate.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("activeToDate should not be empty");
                    return tingcoPricePlans;
                }
                if (pricePlanTypeId.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("pricePlanTypeId should not be empty");
                    return tingcoPricePlans;
                }
                if (groupId.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("groupId should not be empty");
                    return tingcoPricePlans;
                }
                if (isEnabled.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("isEnabled should not be empty");
                    return tingcoPricePlans;
                }
                if (!pricePlanDescription.equals("")) {
                    pricePlanDescription = pricePlanDescription.split("/")[2];
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRICEPLAN)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRICEPLAN);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        PricePlans pricePlans = new PricePlans();
                        String pricePlanID = UUID.randomUUID().toString();
                        pricePlans.setPricePlanId(pricePlanID);
                        pricePlans.setPricePlanName(pricePlanName);
                        pricePlans.setPricePlanDescription(pricePlanDescription);
                        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                        gc.setTime(lFormat.parse(activeFromDate));
                        gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                        pricePlans.setActiveFromDate(gc.getTime());
                        gc.setTime(lFormat.parse(activeToDate));
                        gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                        pricePlans.setActiveToDate(gc.getTime());
                        PricePlanTypes pricePlanTypes = new PricePlanTypes();
                        pricePlanTypes.setPricePlanTypeId(pricePlanTypeId);
                        pricePlans.setPricePlanTypes(pricePlanTypes);
                        pricePlans.setGroupId(groupId);
                        pricePlans.setIsEnabled(Integer.parseInt(isEnabled));
                        pricePlans.setLastUpdatedByUserId(sessions2.getUserId());
                        GregorianCalendar gc1 = new GregorianCalendar();
                        pricePlans.setCreatedDate(gc1.getTime());
                        pricePlans.setUpdatedDate(gc1.getTime());
                        if (!dao.addPricePlan(pricePlans, session)) {
                            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured while Inserting");
                            return tingcoPricePlans;
                        }
                        return tingcoPricePlans;
                    } else {
                        tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                        tingcoPricePlans.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                        return tingcoPricePlans;
                    }

                } else {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                    tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoPricePlans;
                }
            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-3);
                tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoPricePlans;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
            return tingcoPricePlans;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }

    }

    private TingcoPricePlans getProductCategories(String groupId, int countryId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("GroupId should not be empty");
                    return tingcoPricePlans;
                }
                if (countryId <= 0) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("CountryId should not be empty");
                    return tingcoPricePlans;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRICEPLAN)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRICEPLAN);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ProductCategoriesInGroups> productsCategoriesInGroupsList = dao.getProductCategoriesInGroups(session, groupId);
                    if (!productsCategoriesInGroupsList.isEmpty()) {
                        List<ProductCategoryTranslations> productCategoryTranslationsList = dao.getProductCategoryTranslations(session, productsCategoriesInGroupsList, countryId);
                        if (!productCategoryTranslationsList.isEmpty()) {
                            tingcoPricePlans = tingcoPricePlansXML.buildTingcoProductCategoryTranslations(tingcoPricePlans, productCategoryTranslationsList);
                        } else {
                            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                            tingcoPricePlans.getMsgStatus().setResponseText("No Record found for ProductsCategoryTranslations");
                            return tingcoPricePlans;
                        }
                    } else {
                        tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                        tingcoPricePlans.getMsgStatus().setResponseText("No Record found for ProductCategoriesInGroups");
                        return tingcoPricePlans;
                    }
                    return tingcoPricePlans;
                } else {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                    tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoPricePlans;
                }

            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-3);
                tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoPricePlans;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
            return tingcoPricePlans;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }

    }

    private TingcoPricePlans getProductVariants(int countryId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (countryId <= 0) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("CountryId should not be empty");
                    return tingcoPricePlans;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRICEPLAN)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRICEPLAN);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ProductVariants> productVariantsList = dao.getProductVariants(session);
                    if (!productVariantsList.isEmpty()) {
                        List<ProductVariantTranslations> productVariantTranslationsList = dao.getProductVariantTranslations(session, productVariantsList, countryId);
                        if (!productVariantTranslationsList.isEmpty()) {
                            tingcoPricePlans = tingcoPricePlansXML.buildTingcoProductVariantTranslations(tingcoPricePlans, productVariantTranslationsList);
                        } else {
                            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                            tingcoPricePlans.getMsgStatus().setResponseText("No Record found for ProductVariantTranslations");
                            return tingcoPricePlans;
                        }
                    } else {
                        tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                        tingcoPricePlans.getMsgStatus().setResponseText("No Record found for ProductVariants");
                        return tingcoPricePlans;
                    }
                    return tingcoPricePlans;
                } else {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                    tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoPricePlans;
                }

            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-3);
                tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoPricePlans;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
            return tingcoPricePlans;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }

    }

//    private TingcoPricePlans getProducts(String groupId, String countryId) {
//        boolean hasPermission = false;
//        Session session = null;
//        long requestedTime = System.currentTimeMillis();
//        try {
//            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
//            if (request.getAttribute("USERSESSION") != null) {
//                if (groupId.equals("")) {
//                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
//                    tingcoPricePlans.getMsgStatus().setResponseText("GroupId should not be empty");
//                    return tingcoPricePlans;
//                }
//                if (!countryId.equals("")) {
//                    countryId = countryId.split("/")[2];
//                }
//                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
//                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
//                if (ht.containsKey(TCMUtil.GROUPS)) {
//                    ArrayList<String> operations = ht.get(TCMUtil.GROUPS);
//                    for (int i = 0; i < operations.size(); i++) {
//                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
//                            hasPermission = true;
//                            break;
//                        }
//                    }
//                }
//                if (hasPermission) {
//                    session = HibernateUtil.getSessionFactory().openSession();
//                    ProductsDAO productsDAO = new ProductsDAO();
//                    Set<String> groupIdsList = groupDAO.getGroupsAndGroupTrusts(groupId, session);
//                    List<ProductsInGroups> productsInGroupsList = productsDAO.getProductsInGroupsByGroupIds(session, groupIdsList);
//                    Set<String> productIdsList = productsDAO.getproductsByProductsInGroups(productsInGroupsList, session, 200);
//
//                    if (countryId != null) {
//                        if (!countryId.equals("")) {
//                            List<ProductTranslations> productTranslationsList = productsDAO.getProductTranslations(session, productIdsList, Integer.valueOf(countryId));
//                            if (!productTranslationsList.isEmpty()) {
//                                tingcoPricePlans = tingcoPricePlansXML.buildTingcoProductTranslations(tingcoPricePlans, productTranslationsList, countryId);
//                            } else {
//                                tingcoPricePlans.getMsgStatus().setResponseCode(-1);
//                                tingcoPricePlans.getMsgStatus().setResponseText("No Record found");
//                                return tingcoPricePlans;
//                            }
//                        } else {
//                            tingcoPricePlans = tingcoPricePlansXML.buildTingcoProducts(tingcoPricePlans, productIdsList);
//                        }
//                    }
//                    return tingcoPricePlans;
//                } else {
//                    tingcoPricePlans.getMsgStatus().setResponseCode(-10);
//                    tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
//                    return tingcoPricePlans;
//                }
//
//            } else {
//                tingcoPricePlans.getMsgStatus().setResponseCode(-3);
//                tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
//                return tingcoPricePlans;
//            }
//        } catch (Exception ex) {
//            TCMUtil.exceptionLog(getClass().getName(), ex);
//            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
//            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
//            return tingcoPricePlans;
//        } finally {
//            delayLog(requestedTime);
//            if (session != null) {
//                session.close();
//            }
//        }
//
//    }
    private TingcoPricePlans getProducts(String groupId, String countryId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("GroupId should not be empty");
                    return tingcoPricePlans;
                }
                if (!countryId.equals("")) {
                    countryId = countryId.split("/")[2];
                } else {
                    countryId = null;
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
                    ProductsDAO productsDAO = new ProductsDAO();
                    List<String> productIdsList = productsDAO.getproductsByProductsInGroups(groupId, session, 200);
                    if (!productIdsList.isEmpty()) {
                        if (countryId != null) {
                            if (!countryId.equals("")) {
                                List<ProductTranslations> productTranslationsList = productsDAO.getProductTranslations(session, new HashSet<String>(productIdsList), Integer.valueOf(countryId));
                                if (!productTranslationsList.isEmpty()) {
                                    tingcoPricePlans = tingcoPricePlansXML.buildTingcoProductTranslations(tingcoPricePlans, productTranslationsList, countryId);
                                } else {
                                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                                    tingcoPricePlans.getMsgStatus().setResponseText("No Record found");
                                    return tingcoPricePlans;
                                }
                            } else {
                                tingcoPricePlans = tingcoPricePlansXML.buildTingcoProducts(tingcoPricePlans, new HashSet<String>(productIdsList));
                            }
                        }
                        return tingcoPricePlans;
                    } else {
                        tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                        tingcoPricePlans.getMsgStatus().setResponseText("No data found");
                        return tingcoPricePlans;
                    }
                } else {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                    tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoPricePlans;
                }

            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-3);
                tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoPricePlans;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
            return tingcoPricePlans;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
    }

    private TingcoPricePlans getUserAliasTypes(String groupId, String searchString) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("GroupId should not be empty");
                    return tingcoPricePlans;
                }
                if (!searchString.equals("")) {
                    searchString = searchString.split("/")[2];
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRICEPLAN)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRICEPLAN);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<UserAliasTypes> userAliasTypesList = dao.getUserAliasTypes(session, groupId, searchString);
                    if (!userAliasTypesList.isEmpty()) {
                        tingcoPricePlans = tingcoPricePlansXML.buildTingcoUserAliasTypes(tingcoPricePlans, userAliasTypesList);
                    } else {
                        tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                        tingcoPricePlans.getMsgStatus().setResponseText("No Key type found for the given input");
                        return tingcoPricePlans;
                    }
                } else {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                    tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoPricePlans;
                }
            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-3);
                tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoPricePlans;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
            return tingcoPricePlans;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoPricePlans;
    }

    private TingcoPricePlans updatePricePlan(String pricePlanId, String pricePlanName, String pricePlanDescription, String activeFromDate, String activeToDate, String pricePlanTypeId, String groupId, String enabled) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (pricePlanName.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("PricePlanName should not be empty");
                    return tingcoPricePlans;
                }
                if (!pricePlanDescription.equals("")) {
                    pricePlanDescription = pricePlanDescription.split("/")[2];
                } else {
                    pricePlanDescription = null;
                }
                if (!groupId.equals("")) {
                    groupId = groupId.split("/")[2];
                } else {
                    groupId = null;
                }
                if (!enabled.equals("")) {
                    enabled = enabled.split("/")[2];
                } else {
                    enabled = null;
                }
                if (activeFromDate.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("activeFromDate should not be empty");
                    return tingcoPricePlans;
                }
                if (activeToDate.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("activeToDate should not be empty");
                    return tingcoPricePlans;
                }
                if (pricePlanTypeId.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("pricePlanTypeId should not be empty");
                    return tingcoPricePlans;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRICEPLAN)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRICEPLAN);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        if (!dao.updatePricePlan(pricePlanId, pricePlanName, pricePlanDescription, pricePlanTypeId, activeFromDate, activeToDate, groupId, enabled, session, timeZoneGMToffset)) {
                            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured while Updating");
                            return tingcoPricePlans;
                        }
                        return tingcoPricePlans;
                    } else {
                        tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                        tingcoPricePlans.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                        return tingcoPricePlans;
                    }


                } else {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                    tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoPricePlans;
                }
            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-3);
                tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoPricePlans;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
            return tingcoPricePlans;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }


    }

    private TingcoPricePlans copyPricePlan(String content) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRICEPLAN)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRICEPLAN);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    TingcoPricePlans tingcoPricePlan = (TingcoPricePlans) JAXBManager.getInstance().unMarshall(content, TingcoPricePlans.class);
                    if (!tingcoPricePlan.getPricePlans().getPricePlan().isEmpty()) {
                        for (PricePlan pp : tingcoPricePlan.getPricePlans().getPricePlan()) {
                            PricePlans pps = new PricePlans();
                            pps.setPricePlanId(pp.getPricePlanID());
                            pps.setPricePlanName(pp.getPricePlanName());
                            pps.setPricePlanDescription(pp.getPricePlanDescription());
                            pps.setGroupId(pp.getGroupID());
                            PricePlanTypes ppt = new PricePlanTypes();
                            ppt.setPricePlanTypeId(pp.getPricePlanTypeID());
                            pps.setPricePlanTypes(ppt);
                            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                            gc.setTimeInMillis(Long.valueOf(pp.getActiveFromDate().toString()));
                            pps.setActiveFromDate(gc.getTime());
                            gc.setTimeInMillis(Long.valueOf(pp.getActiveToDate().toString()));
                            pps.setActiveToDate(gc.getTime());
                            pps.setIsEnabled(pp.getIsEnabled());
                            pps.setLastUpdatedByUserId(sessions2.getUserId());
                            gc = new GregorianCalendar();
                            pps.setCreatedDate(gc.getTime());
                            pps.setUpdatedDate(gc.getTime());
                            for (se.info24.priceplansjaxb.PricePlanVersions ppv : pp.getPricePlanVersions()) {
                                PricePlanVersions ppvs = new PricePlanVersions();
                                String versionId = UUID.randomUUID().toString();
                                ppvs.setPricePlanVersionId(versionId);
                                PricePlans pricePlans = new PricePlans();
                                pricePlans.setPricePlanId(pp.getPricePlanID());
                                ppvs.setPricePlans(pricePlans);
                                ppvs.setVersionName(ppv.getVersionName());
                                ppvs.setVersionDescription(ppv.getVersionDescription());
                                gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                                gc.setTimeInMillis(Long.valueOf(ppv.getActiveFromDate().toString()));
                                ppvs.setActiveFromDate(gc.getTime());
                                gc.setTimeInMillis(Long.valueOf(ppv.getActiveToDate().toString()));
                                ppvs.setActiveToDate(gc.getTime());
                                ppvs.setPriority(ppv.getPriority());
                                ppvs.setCurrencyId(ppv.getCurrencyID());
                                ppvs.setIsEnabled(ppv.getIsEnabled());
                                ppvs.setLastUpdatedByUserId(sessions2.getUserId());
                                gc = new GregorianCalendar();
                                ppvs.setCreatedDate(gc.getTime());
                                ppvs.setUpdatedDate(gc.getTime());
                                for (PricePlanItems ppi : ppv.getPricePlanItems()) {
                                    se.info24.pojo.PricePlanItems ppits = new se.info24.pojo.PricePlanItems();
                                    ppits.setPricePlanItemId(UUID.randomUUID().toString());
                                    ppits.setPricePlanVersionId(versionId);
                                    ppits.setItemName(ppi.getItemName());
                                    ppits.setItemDescription(ppi.getItemDescription());
                                    PricePlanItemTypes ppit = new PricePlanItemTypes();
                                    ppit.setPricePlanItemTypeId(ppi.getPricePlanItemTypeID());
                                    ppits.setPricePlanItemTypes(ppit);
                                    ppits.setChargeTypeId(ppi.getChargeTypeID());
                                    ValidationDataType vdt = dao.getValidationDataType(session, ppi.getValidationDataTypeID());
                                    vdt.setValidationDataTypeId(ppi.getValidationDataTypeID());
                                    ppits.setValidationDataType(vdt);
                                    ppits.setValidationDataType_1(vdt.getValidationDataType());
                                    ValidationExpressions ve1 = dao.getValidationExpression(session, ppi.getValidationExpressionID());
                                    ve1.setValidationExpressionId(ppi.getValidationExpressionID());
                                    ppits.setValidationExpressions(ve1);
                                    ppits.setValidationExpression(ve1.getValidationExpression());
                                    ppits.setValidationValue(ppi.getValidationValue().getValue());
                                    ppits.setItemAmount(BigDecimal.valueOf(ppi.getItemAmount()));
                                    ppits.setItemPercent(BigDecimal.valueOf(ppi.getItemPercent()));
                                    ppits.setIsEnabled(ppi.getIsEnabled());
                                    ppits.setLastUpdatedByUserId(sessions2.getUserId());
                                    gc = new GregorianCalendar();
                                    ppits.setCreatedDate(gc.getTime());
                                    ppits.setUpdatedDate(gc.getTime());
                                }
                            }
                        }
                    }
                    return tingcoPricePlans;
                } else {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                    tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoPricePlans;
                }
            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-3);
                tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoPricePlans;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
            return tingcoPricePlans;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
    }

    private TingcoPricePlans getPricePlanItemDetails(String pricePlanVersionId, int countryId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (pricePlanVersionId.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("PricePlanVersionId should not be empty");
                    return tingcoPricePlans;
                }
                if (countryId <= 0) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("CountryId should not be empty");
                    return tingcoPricePlans;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRICEPLAN)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRICEPLAN);
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
                        List<se.info24.pojo.PricePlanItems> pricePlanItemsList = dao.getPricePlanItemDetails(session, pricePlanVersionId);
                        if (!pricePlanItemsList.isEmpty()) {
                            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlanItems(tingcoPricePlans, pricePlanItemsList, countryId, session, timeZoneGMToffset);
                        } else {
                            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                            tingcoPricePlans.getMsgStatus().setResponseText("No Data found");
                            return tingcoPricePlans;
                        }
                        return tingcoPricePlans;
                    } else {
                        tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                        tingcoPricePlans.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                        return tingcoPricePlans;
                    }

                } else {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                    tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoPricePlans;
                }
            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-3);
                tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoPricePlans;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
            return tingcoPricePlans;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }

    }

    private TingcoPricePlans copyPricePlanRecursion(String pricePlanId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (pricePlanId.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("PricePlanId should not be empty");
                    return tingcoPricePlans;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRICEPLAN)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRICEPLAN);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    PricePlans pricePlans = dao.getPricePlansByPricePlanId(session, pricePlanId);
                    if (pricePlans != null) {
                        PricePlans pps = new PricePlans();
                        String newPricePlanId = UUID.randomUUID().toString();
                        pps.setPricePlanId(newPricePlanId);
                        if (!pricePlans.getPricePlanName().contains("Copy")) {
                            pps.setPricePlanName(pricePlans.getPricePlanName() + " Copy");
                        } else {
                            pps.setPricePlanName(pricePlans.getPricePlanName());
                        }
                        pps.setPricePlanDescription(pricePlans.getPricePlanDescription());
                        pps.setGroupId(pricePlans.getGroupId());
                        PricePlanTypes ppt = new PricePlanTypes();
                        ppt.setPricePlanTypeId(pricePlans.getPricePlanTypes().getPricePlanTypeId());
                        pps.setPricePlanTypes(ppt);
                        pps.setActiveFromDate(pricePlans.getActiveFromDate());
                        pps.setActiveToDate(pricePlans.getActiveToDate());
                        pps.setIsEnabled(pricePlans.getIsEnabled());
                        pps.setLastUpdatedByUserId(sessions2.getUserId());
                        GregorianCalendar gc = new GregorianCalendar();
                        pps.setCreatedDate(gc.getTime());
                        pps.setUpdatedDate(gc.getTime());
                        if (!dao.copyPricePlanRecursion(pps, session)) {
                            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured while copying PricePlans");
                            return tingcoPricePlans;
                        }

                        List<PricePlanVersions> pricePlanVersionList = dao.getPricePlanVersions(session, pricePlanId);
                        if (!pricePlanVersionList.isEmpty()) {
                            for (PricePlanVersions ppv : pricePlanVersionList) {
                                PricePlanVersions ppvs = new PricePlanVersions();
                                String newPricePlanVersionId = UUID.randomUUID().toString();
                                ppvs.setPricePlanVersionId(newPricePlanVersionId);
                                ppvs.setPricePlans(pps);
                                ppvs.setVersionName(ppv.getVersionName());
                                ppvs.setVersionDescription(ppv.getVersionDescription());
                                ppvs.setActiveFromDate(ppv.getActiveFromDate());
                                ppvs.setActiveToDate(ppv.getActiveToDate());
                                ppvs.setPriority(ppv.getPriority());
                                ppvs.setCurrencyId(ppv.getCurrencyId());
                                ppvs.setIsEnabled(ppv.getIsEnabled());
                                ppvs.setLastUpdatedByUserId(sessions2.getUserId());
                                ppvs.setCreatedDate(gc.getTime());
                                ppvs.setUpdatedDate(gc.getTime());
                                if (!dao.copyPricePlanVersionRecursion(ppvs, session)) {
                                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                                    tingcoPricePlans.getMsgStatus().setResponseText("Error Occured while copying PricePlanVersions");
                                    return tingcoPricePlans;
                                }
                                List<se.info24.pojo.PricePlanItems> pricePlanItemsList = dao.getPricePlanItemDetails(session, ppv.getPricePlanVersionId());
                                HashMap<String, String> hm = new HashMap<String, String>();
                                if (!pricePlanItemsList.isEmpty()) {
                                    for (se.info24.pojo.PricePlanItems ppi : pricePlanItemsList) {
                                        hm.put(ppi.getPricePlanItemId(), ppi.getPricePlanItemParentId());
                                    }
                                    ArrayList<String> ids = new ArrayList<String>();
                                    String childid = pricePlanItemsList.get(0).getPricePlanItemId();
                                    String parentid = pricePlanItemsList.get(0).getPricePlanItemParentId();
                                    String newChild = UUID.randomUUID().toString();
                                    String newparentId = UUID.randomUUID().toString();
                                    ids.add(childid);
                                    int i = 0;
                                    if (parentid == null) {
                                        addPricePlanItem(pricePlanItemsList.get(i), newChild, parentid, newPricePlanVersionId, session, sessions2.getUserId());
                                        i++;
                                        if (pricePlanItemsList.size() > i) {
                                            addUpStreamItems(pricePlanItemsList.get(i), childid, hm, newChild, newPricePlanVersionId, session, sessions2.getUserId());
                                        }

                                    } else {
                                        addPricePlanItem(pricePlanItemsList.get(i), newChild, newparentId, newPricePlanVersionId, session, sessions2.getUserId());
                                        i++;
                                        if (pricePlanItemsList.size() > i) {
                                            addDownStreamItems(pricePlanItemsList.get(i), parentid, hm, newparentId, newPricePlanVersionId, session, sessions2.getUserId());
                                        }
                                    }
                                }
                            }
                        }
                        tingcoPricePlans.getMsgStatus().setResponseText("New PricePlanId is " + newPricePlanId.toUpperCase());
                    } else {
                        tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                        tingcoPricePlans.getMsgStatus().setResponseText("No Records found for PricePlans");
                        return tingcoPricePlans;
                    }
                } else {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                    tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoPricePlans;
                }
            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-3);
                tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoPricePlans;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
            return tingcoPricePlans;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoPricePlans;
    }

    private void addUpStreamItems(se.info24.pojo.PricePlanItems ppitems, String oldchildid, HashMap<String, String> hm, String parentID, String newPricePlanVersionId, Session session, String userId) {
        Set<Map.Entry<String, String>> entryset = hm.entrySet();
        try {
            for (Map.Entry<String, String> set : entryset) {
                if (set.getValue() != null) {
                    if (set.getValue().equals(oldchildid)) {
                        String newchildId = UUID.randomUUID().toString();
                        addPricePlanItem(ppitems, newchildId, parentID, newPricePlanVersionId, session, userId);
                        oldchildid = set.getKey();
                        parentID = newchildId;
                    }
                }
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        }

    }

    private void addDownStreamItems(se.info24.pojo.PricePlanItems ppitems, String oldparentid, HashMap<String, String> hm, String childID, String newPricePlanVersionId, Session session, String userId) {
        String childid = oldparentid;
        oldparentid = hm.get(childid);
        while (oldparentid != null) {
            String newparentId = UUID.randomUUID().toString();
            addPricePlanItem(ppitems, childID, newparentId, newPricePlanVersionId, session, userId);
            childid = oldparentid;
            oldparentid = hm.get(childid);
            childID = newparentId;
        }
        addPricePlanItem(ppitems, childID, oldparentid, newPricePlanVersionId, session, userId);
    }

    private TingcoPricePlans addPricePlanItem(se.info24.pojo.PricePlanItems ppi, String childid, String parentid, String versionid, Session session, String userId) {
        try {
            se.info24.pojo.PricePlanItems ppItems = new se.info24.pojo.PricePlanItems();
            ppItems.setPricePlanItemId(childid);
            ppItems.setPricePlanItemParentId(parentid);
            ppItems.setPricePlanVersionId(versionid);
            ppItems.setItemName(ppi.getItemName());
            ppItems.setItemDescription(ppi.getItemDescription());
            PricePlanItemTypes ppit = new PricePlanItemTypes();
            ppit.setPricePlanItemTypeId(ppi.getPricePlanItemTypes().getPricePlanItemTypeId());
            ppItems.setPricePlanItemTypes(ppit);
            ppItems.setChargeTypeId(ppi.getChargeTypeId());
            ValidationDataType vdt = new ValidationDataType();
            vdt.setValidationDataTypeId(ppi.getValidationDataType().getValidationDataTypeId());
            ppItems.setValidationDataType(vdt);
            ppItems.setValidationDataType_1(ppi.getValidationDataType_1());
            ValidationExpressions ve = new ValidationExpressions();
            ve.setValidationExpressionId(ppi.getValidationExpressions().getValidationExpressionId());
            ppItems.setValidationExpressions(ve);
            ppItems.setValidationExpression(ppi.getValidationExpression());
            ppItems.setValidationValue(ppi.getValidationValue());
            ppItems.setItemAmount(ppi.getItemAmount());
            ppItems.setItemPercent(ppi.getItemPercent());
            ppItems.setIsEnabled(ppi.getIsEnabled());
            ppItems.setLastUpdatedByUserId(userId);
            GregorianCalendar gc = new GregorianCalendar();
            ppItems.setCreatedDate(gc.getTime());
            ppItems.setUpdatedDate(gc.getTime());
            ppItems.setPriority(ppi.getPriority());
            if (!dao.copyPricePlanItemsRecursion(ppItems, session)) {
                tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                tingcoPricePlans.getMsgStatus().setResponseText("Error Occured while Updating PricePlanItems");
                return tingcoPricePlans;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        }
        return tingcoPricePlans;
    }

    private TingcoPricePlans getPricePlanItemTypes(int countryId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (countryId <= 0) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("CountryId should not be empty");
                    return tingcoPricePlans;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRICEPLAN)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRICEPLAN);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<PricePlanItemTypeTranslations> pricePlanItemTypeTranslationsList = dao.getPricePlanItemTypeTranslations(session, countryId);
                    if (!pricePlanItemTypeTranslationsList.isEmpty()) {
                        tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlanItemTypes(tingcoPricePlans, pricePlanItemTypeTranslationsList);
                    } else {
                        tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                        tingcoPricePlans.getMsgStatus().setResponseText("No Data found");
                        return tingcoPricePlans;
                    }
                    return tingcoPricePlans;
                } else {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                    tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoPricePlans;
                }

            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-3);
                tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoPricePlans;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
            return tingcoPricePlans;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }

    }

    private TingcoPricePlans getValidationDataTypes() {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRICEPLAN)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRICEPLAN);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ValidationDataType> validationDataTypeList = dao.getValidationDataType(session);
                    if (!validationDataTypeList.isEmpty()) {
                        tingcoPricePlans = tingcoPricePlansXML.buildTingcoValidationDataType(tingcoPricePlans, validationDataTypeList);
                    } else {
                        tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                        tingcoPricePlans.getMsgStatus().setResponseText("No Data found");
                    }
                    return tingcoPricePlans;
                } else {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                    tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoPricePlans;
                }

            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-3);
                tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoPricePlans;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
            return tingcoPricePlans;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }

    }

    private TingcoPricePlans getValidationExpressions(String validationdatatypeid) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRICEPLAN)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRICEPLAN);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ValidationMemberships> validationMembershipsList = dao.getValidationMemberships(validationdatatypeid, session);
                    if (!validationMembershipsList.isEmpty()) {
                        List<ValidationExpressions> validationExpressionsList = dao.getValidationExpressions(validationMembershipsList, session);
                        if (!validationExpressionsList.isEmpty()) {
                            tingcoPricePlans = tingcoPricePlansXML.buildTingcoValidationExpressions(tingcoPricePlans, validationExpressionsList);
                        } else {
                            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                            tingcoPricePlans.getMsgStatus().setResponseText("No Data found for ValidationExpressions");

                        }
                    } else {
                        tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                        tingcoPricePlans.getMsgStatus().setResponseText("No Data found for ValidationMemberships");

                    }
                    return tingcoPricePlans;
                } else {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                    tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoPricePlans;
                }

            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-3);
                tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoPricePlans;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
            return tingcoPricePlans;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }

    }

    public TingcoPricePlans addPricePlanVersion(String priceplanid, String versionname, String versiondescription, String activeFromDate, String activeToDate, String priority, String currencyid, String isenabled) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
            UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
            if (request.getAttribute("USERSESSION") != null) {
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRICEPLAN)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRICEPLAN);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (!versiondescription.equals("")) {
                    versiondescription = versiondescription.split("/")[2];
                }
                if (hasPermission) {
                    try {
                        session = HibernateUtil.getSessionFactory().openSession();
                        UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                        if (userTimeZones2 != null) {
                            String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                            PricePlans pp = (PricePlans) session.getNamedQuery("getPricePlans").setString("pricePlanId", priceplanid).list().get(0);
                            if (pp != null) {
                                Transaction tx = session.beginTransaction();
                                PricePlanVersions ppv = new PricePlanVersions();
                                ppv.setPricePlanVersionId(UUID.randomUUID().toString());
                                PricePlans pricePlan = new PricePlans();
                                pricePlan.setPricePlanId(priceplanid);
                                ppv.setPricePlans(pricePlan);
                                ppv.setVersionName(versionname);
                                ppv.setVersionDescription(versiondescription);
                                GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                                ppv.setCreatedDate(gc.getTime());
                                ppv.setUpdatedDate(gc.getTime());
                                gc.setTime(lFormat.parse(activeFromDate));
                                gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                                ppv.setActiveFromDate(gc.getTime());
                                gc.setTime(lFormat.parse(activeToDate));
                                gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                                ppv.setActiveToDate(gc.getTime());
                                ppv.setPriority(Integer.parseInt(priority));
                                ppv.setCurrencyId(Integer.parseInt(currencyid));
                                ppv.setIsEnabled(Integer.parseInt(isenabled));
                                ppv.setLastUpdatedByUserId(sessions2.getUserId());
                                session.saveOrUpdate(ppv);
                                tx.commit();
                            }
                            return tingcoPricePlans;
                        } else {
                            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                            tingcoPricePlans.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                            return tingcoPricePlans;
                        }

                    } catch (Exception e) {
                        TCMUtil.exceptionLog(getClass().getName(), e);
                        tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                        tingcoPricePlans.getMsgStatus().setResponseText("Error Occured while Adding PricePlanItems");
                        return tingcoPricePlans;
                    }
                } else {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                    tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoPricePlans;
                }

            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-3);
                tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoPricePlans;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
            return tingcoPricePlans;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }

    }

    public TingcoPricePlans addItemForVersion(String priceplanversionid, String priceplanitemparentid, String itemamount, String itempercent, String itemname, String priceplanitemtypeid, String chargetypeid, String validationdatatypeid, String validationexpressid, String validationvalue, String validationdatatype, String validationexpression, String isenabled) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
            UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
            if (request.getAttribute("USERSESSION") != null) {
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRICEPLAN)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRICEPLAN);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                        }
                    }
                }
                if (priceplanversionid.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("PricePlanVersionId should not be empty");
                    return tingcoPricePlans;
                }
                if (!priceplanitemparentid.equals("")) {
                    priceplanitemparentid = priceplanitemparentid.split("/")[2];
                }
                if (!itemamount.equals("")) {
                    itemamount = itemamount.split("/")[2];
                }
                if (!itempercent.equals("")) {
                    itempercent = itempercent.split("/")[2];
                }
                if (itemname.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("Itemname should not be empty");
                    return tingcoPricePlans;
                }
                if (priceplanitemtypeid.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("priceplanitemtypeid should not be empty");
                    return tingcoPricePlans;
                }
                if (chargetypeid.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("chargetypeid should not be empty");
                    return tingcoPricePlans;
                }
                if (validationdatatypeid.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("validationdatatypeid should not be empty");
                    return tingcoPricePlans;
                }
                if (validationexpressid.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("validationexpressionid should not be empty");
                    return tingcoPricePlans;
                }
                if (validationvalue.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("validationvalue should not be empty");
                    return tingcoPricePlans;
                }
                if (validationdatatype.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("validationdatatype should not be empty");
                    return tingcoPricePlans;
                }
                if (validationexpression.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("validationexpression should not be empty");
                    return tingcoPricePlans;
                }
                if (isenabled.equals("")) {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("isenabled should not be empty");
                    return tingcoPricePlans;
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();
                    se.info24.pojo.PricePlanItems ppi = new se.info24.pojo.PricePlanItems();
                    ppi.setPricePlanItemId(UUID.randomUUID().toString());
                    if (!priceplanitemparentid.equals("")) {
                        ppi.setPricePlanItemParentId(priceplanitemparentid);
                    }
                    ppi.setPricePlanVersionId(priceplanversionid);
                    if (!itemamount.equals("")) {
                        ppi.setItemAmount(new BigDecimal(itemamount));
                    }
                    if (!itempercent.equals("")) {
                        ppi.setItemPercent(new BigDecimal(itempercent));
                    }

                    List<se.info24.pojo.PricePlanItems> ppitems = session.getNamedQuery("GetMaxValueOfPriority").setMaxResults(1).list();
                    int priority = 0;
                    if (!ppitems.isEmpty()) {
                        priority = ppitems.get(0).getPriority();
                    }
                    ppi.setItemName(itemname);
                    se.info24.pojo.PricePlanItemTypes ppit = dao.getPricePlanItemTypesById(priceplanitemtypeid, session);
                    ppi.setPricePlanItemTypes(ppit);
                    ppi.setChargeTypeId(chargetypeid);
                    se.info24.pojo.ValidationDataType vdt = dao.getValidationDataTypeById(validationdatatypeid, session);
                    ppi.setValidationDataType(vdt);
                    se.info24.pojo.ValidationExpressions ve = dao.getValidationExpressionsById(validationexpressid, session);
                    ppi.setValidationExpressions(ve);
                    ppi.setValidationDataType_1(vdt.getValidationDataType());
                    GregorianCalendar gc = new GregorianCalendar();
                    ppi.setCreatedDate(gc.getTime());
                    ppi.setUpdatedDate(gc.getTime());
                    if (vdt.getValidationDataType().equalsIgnoreCase("DATE")) {
                        UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                        if (userTimeZones2 != null) {
                            String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                            gc.setTime(lFormat.parse(validationvalue));
                            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                            ppi.setValidationValue(lFormat.format(gc.getTime()));
                        } else {
                            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                            tingcoPricePlans.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                            return tingcoPricePlans;
                        }

                    } else {
                        ppi.setValidationValue(validationvalue);
                    }
                    ppi.setValidationExpression(validationexpression);
                    ppi.setIsEnabled(Integer.parseInt(isenabled));
                    ppi.setLastUpdatedByUserId(sessions2.getUserId());
                    ppi.setPriority(priority + 1);
                    session.saveOrUpdate(ppi);
                    tx.commit();
                } else {
                    tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                    tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoPricePlans;
                }
            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-3);
                tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoPricePlans;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
            tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
            return tingcoPricePlans;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoPricePlans;
    }

    public TingcoPricePlans updateVersionForPriceplan(String priceplanversionid, String versionname, String versionDescription, String activeFromDate, String activeToDate, String priority, String currencyid, String isenabled) {
        boolean hasPermission = false;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
        if (request.getAttribute("USERSESSION") != null) {
            Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
            if (ht.containsKey(TCMUtil.PRICEPLAN)) {
                ArrayList<String> operations = ht.get(TCMUtil.PRICEPLAN);
                for (int i = 0; i < operations.size(); i++) {
                    if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                        hasPermission = true;
                    }
                }
            }
            if (hasPermission) {
                Session session = null;
                try {
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        Transaction tx = session.beginTransaction();
                        se.info24.pojo.PricePlanVersions ppv = dao.getPricePlanVersionsById(priceplanversionid, session);
                        if (ppv != null) {
                            ppv.setVersionName(versionname);
                            if (!versionDescription.equals("")) {
                                versionDescription = versionDescription.split("/")[2];
                                ppv.setVersionDescription(versionDescription);
                            } else {
                                ppv.setVersionDescription("NULL");
                            }
                            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                            ppv.setUpdatedDate(gc.getTime());
                            gc.setTime(lFormat.parse(activeFromDate));
                            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                            ppv.setActiveFromDate(gc.getTime());
                            gc.setTime(lFormat.parse(activeToDate));
                            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                            ppv.setActiveToDate(gc.getTime());
                            ppv.setPriority(Integer.parseInt(priority));
                            ppv.setCurrencyId(Integer.parseInt(currencyid));
                            ppv.setIsEnabled(Integer.parseInt(isenabled));
                            ppv.setLastUpdatedByUserId(sessions2.getUserId());
                            session.saveOrUpdate(ppv);
                            tx.commit();
                        } else {
                            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                            tingcoPricePlans.getMsgStatus().setResponseText("No Record found for PricePlanVersions");

                        }
                    } else {
                        tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                        tingcoPricePlans.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                        return tingcoPricePlans;
                    }


                    return tingcoPricePlans;
                } catch (Exception ex) {
                    TCMUtil.exceptionLog(getClass().getName(), ex);
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("Error Occured");
                    return tingcoPricePlans;
                } finally {
                    delayLog(requestedTime);
                    if (session != null) {
                        session.close();
                    }
                }
            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                return tingcoPricePlans;
            }

        } else {
            tingcoPricePlans.getMsgStatus().setResponseCode(-3);
            tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
            return tingcoPricePlans;
        }
    }

    public TingcoPricePlans updateItemsForVersionId(String priceplanitemid, String priceplanversionid, String itemamount, String itempercent, String itemname, String priceplanitemtypeid, String chargetypeid, String validationdatatypeid, String validationexpressionid, String validationvalue, String validationdatatype, String validationexpression, String isenabled) {
        boolean hasPermission = false;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
        if (request.getAttribute("USERSESSION") != null) {
            Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
            if (ht.containsKey(TCMUtil.PRICEPLAN)) {
                ArrayList<String> operations = ht.get(TCMUtil.PRICEPLAN);
                for (int i = 0; i < operations.size(); i++) {
                    if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                        hasPermission = true;
                    }
                }
            }
            if (priceplanitemid.equals("")) {
                tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                tingcoPricePlans.getMsgStatus().setResponseText("priceplanitemid should not be empty");
                return tingcoPricePlans;
            }
            if (priceplanversionid.equals("")) {
                tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                tingcoPricePlans.getMsgStatus().setResponseText("PricePlanVersionId should not be empty");
                return tingcoPricePlans;
            }
            if (!itemamount.equals("")) {
                itemamount = itemamount.split("/")[2];
            }
            if (!itempercent.equals("")) {
                itempercent = itempercent.split("/")[2];
            }
            if (itemname.equals("")) {
                tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                tingcoPricePlans.getMsgStatus().setResponseText("Itemname should not be empty");
                return tingcoPricePlans;
            }
            if (priceplanitemtypeid.equals("")) {
                tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                tingcoPricePlans.getMsgStatus().setResponseText("priceplanitemtypeid should not be empty");
                return tingcoPricePlans;
            }
            if (chargetypeid.equals("")) {
                tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                tingcoPricePlans.getMsgStatus().setResponseText("chargetypeid should not be empty");
                return tingcoPricePlans;
            }
            if (validationdatatypeid.equals("")) {
                tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                tingcoPricePlans.getMsgStatus().setResponseText("validationdatatypeid should not be empty");
                return tingcoPricePlans;
            }
            if (validationexpressionid.equals("")) {
                tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                tingcoPricePlans.getMsgStatus().setResponseText("validationexpressionid should not be empty");
                return tingcoPricePlans;
            }
            if (validationvalue.equals("")) {
                tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                tingcoPricePlans.getMsgStatus().setResponseText("validationvalue should not be empty");
                return tingcoPricePlans;
            }
            if (validationdatatype.equals("")) {
                tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                tingcoPricePlans.getMsgStatus().setResponseText("validationdatatype should not be empty");
                return tingcoPricePlans;
            }
            if (validationexpression.equals("")) {
                tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                tingcoPricePlans.getMsgStatus().setResponseText("validationexpression should not be empty");
                return tingcoPricePlans;
            }
            if (isenabled.equals("")) {
                tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                tingcoPricePlans.getMsgStatus().setResponseText("isenabled should not be empty");
                return tingcoPricePlans;
            }
            if (hasPermission) {
                Session session = null;
                try {
                    GregorianCalendar gc = new GregorianCalendar();
                    session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();
                    se.info24.pojo.PricePlanItems ppi = dao.getPricePlanItemById(priceplanitemid, session);
                    if (ppi != null) {
                        ppi.setPricePlanVersionId(priceplanversionid);
                        if (!itemamount.equals("")) {
                            ppi.setItemAmount(new BigDecimal(itemamount));
                        }
                        if (!itempercent.equals("")) {
                            ppi.setItemPercent(new BigDecimal(itempercent));
                        }
                        ppi.setItemName(itemname);
                        ppi.setPricePlanItemTypes(dao.getPricePlanItemTypesById(priceplanitemtypeid, session));
                        ppi.setChargeTypeId(chargetypeid);

                        se.info24.pojo.ValidationDataType vdt = dao.getValidationDataTypeById(validationdatatypeid, session);
                        ppi.setValidationDataType(vdt);
                        ppi.setValidationExpressions(dao.getValidationExpressionsById(validationexpressionid, session));
                        ppi.setUpdatedDate(gc.getTime());
                        if (vdt.getValidationDataType().equalsIgnoreCase("DATE")) {
                            UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                            if (userTimeZones2 != null) {
                                String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                                gc.setTime(lFormat.parse(validationvalue));
                                gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                                ppi.setValidationValue(lFormat.format(gc.getTime()));
                            } else {
                                tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                                tingcoPricePlans.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                                return tingcoPricePlans;
                            }

                        } else {
                            ppi.setValidationValue(validationvalue);
                        }
                        ppi.setValidationDataType_1(validationdatatype);
                        ppi.setValidationExpression(validationexpression);
                        ppi.setIsEnabled(Integer.parseInt(isenabled));
                        ppi.setLastUpdatedByUserId(sessions2.getUserId());
                        session.saveOrUpdate(ppi);
                        tx.commit();
                    } else {
                        tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                        tingcoPricePlans.getMsgStatus().setResponseText("No Data Found to update");
                    }
                    return tingcoPricePlans;
                } catch (Exception e) {
                    TCMUtil.exceptionLog(getClass().getName(), e);
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("Error Occured while Updating PricePlanItems");
                    return tingcoPricePlans;
                } finally {
                    delayLog(requestedTime);
                    if (session != null) {
                        session.close();
                    }
                }
            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                return tingcoPricePlans;
            }


        } else {
            tingcoPricePlans.getMsgStatus().setResponseCode(-3);
            tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
            return tingcoPricePlans;
        }
    }

    public TingcoPricePlans DeletePricePlanVersionsById(String priceplanversionsid) {
        boolean hasPermission = false;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoPricePlans = tingcoPricePlansXML.buildTingcoPricePlansTemplate();
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
        if (request.getAttribute("USERSESSION") != null) {
            Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
            if (ht.containsKey(TCMUtil.PRICEPLAN)) {
                ArrayList<String> operations = ht.get(TCMUtil.PRICEPLAN);
                for (int i = 0; i < operations.size(); i++) {
                    if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                        hasPermission = true;
                        break;
                    }
                }
            }
            if (hasPermission) {
                Session session = null;
                try {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();
                    List<se.info24.pojo.PricePlanItems> ppi = session.getNamedQuery("getPricePlanItemsByPricePlanVersionId").setString("pricePlanVersionId", priceplanversionsid).list();
                    for (se.info24.pojo.PricePlanItems pricePlanItems : ppi) {
                        session.delete(pricePlanItems);
                    }
                    se.info24.pojo.PricePlanVersions ppv = dao.getPricePlanVersionsById(priceplanversionsid, session);
                    if (ppv != null) {
                        session.delete(ppv);
                    }
                    tx.commit();

                    return tingcoPricePlans;
                } catch (Exception e) {

                    TCMUtil.exceptionLog(getClass().getName(), e);
                    tingcoPricePlans.getMsgStatus().setResponseCode(-1);
                    tingcoPricePlans.getMsgStatus().setResponseText("Error Occured while deleting PricePlanItems");
                    return tingcoPricePlans;
                } finally {
                    delayLog(requestedTime);
                    if (session != null) {
                        session.close();
                    }
                }
            } else {
                tingcoPricePlans.getMsgStatus().setResponseCode(-10);
                tingcoPricePlans.getMsgStatus().setResponseText("User Permission is not given");
                return tingcoPricePlans;
            }

        } else {
            tingcoPricePlans.getMsgStatus().setResponseCode(-3);
            tingcoPricePlans.getMsgStatus().setResponseText("User Session is Not Valid");
            return tingcoPricePlans;
        }
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}

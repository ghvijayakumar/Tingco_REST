/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.products;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.datatype.DatatypeConfigurationException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.content.ContentDAO;
import se.info24.device.DeviceDAO;
import se.info24.group.GroupDAO;
import se.info24.ismOperationsPojo.MeasurementData;
import se.info24.ismOperationsPojo.MeasurementDataId;
import se.info24.ismOperationsPojo.ObjectUsageRecords;
import se.info24.ismOperationsPojo.TransactionProducts;
import se.info24.ismOperationsPojo.TransactionProductsId;
import se.info24.ismOperationsPojo.TransactionResult;
import se.info24.jaxbUtil.TingcoProductsXML;
import se.info24.pojo.Addresses;
import se.info24.pojo.ContentCategories;
import se.info24.pojo.ContentCategoryTranslations;
import se.info24.pojo.ContentCategoryTranslationsId;
import se.info24.pojo.Country;
import se.info24.pojo.Device;
import se.info24.pojo.GroupDefaultAgreement;
import se.info24.pojo.GroupDefaultUserAlias;
import se.info24.pojo.GroupLimitPackages;
import se.info24.pojo.GroupProductVariantSubscriptions;
import se.info24.pojo.GroupProductVariantSubscriptionsId;
import se.info24.pojo.GroupTrusts;
import se.info24.pojo.ObjectImages;
import se.info24.pojo.ObjectProductVariantCounters;
import se.info24.pojo.ObjectProductVariantCountersId;
import se.info24.pojo.ObjectProductVariantPrices;
import se.info24.pojo.ObjectProductVariantPricesId;
import se.info24.pojo.ProductAttributes;
import se.info24.pojo.ProductCategories;
import se.info24.pojo.ProductCategoriesInGroups;
import se.info24.pojo.ProductCategoryTranslations;
import se.info24.pojo.ProductTranslations;
import se.info24.pojo.ProductTranslationsId;
import se.info24.pojo.ProductTypes;
import se.info24.pojo.ProductVariantAttributes;
import se.info24.pojo.ProductVariantComponents;
import se.info24.pojo.ProductVariantContentCategories;
import se.info24.pojo.ProductVariantDevices;
import se.info24.pojo.ProductVariantDownloadUrls;
import se.info24.pojo.ProductVariantMeasurementTypes;
import se.info24.pojo.ProductVariantPrices;
import se.info24.pojo.ProductVariantPricesId;
import se.info24.pojo.ProductVariantServices;
import se.info24.pojo.ProductVariantTranslations;
import se.info24.pojo.ProductVariantTranslationsId;
import se.info24.pojo.ProductVariants;
import se.info24.pojo.Products;
import se.info24.pojo.ProductsInGroups;
import se.info24.pojo.ProductsInGroupsId;
import se.info24.pojo.Providers;
import se.info24.pojo.ProvidersInGroups;
import se.info24.pojo.ServiceClientLogins;
import se.info24.pojo.ServiceContentSubscriptions;
import se.info24.pojo.ServiceSubscriptionAcl;
import se.info24.pojo.UserAlias;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.UserTimeZones2;
import se.info24.pojo.Users2;
import se.info24.productsjaxb.Product;
import se.info24.productsjaxb.TingcoProducts;
import se.info24.reports.ReportDAO;
import se.info24.restUtil.RestUtilDAO;
import se.info24.user.CountryDAO;
import se.info24.user.UserDAO;
import se.info24.user.User_LoginsResource;
import se.info24.util.HibernateUtil;
import se.info24.util.JAXBManager;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;
import se.info24.util.TingcoConstants;

/**
 * REST Web Service
 *
 * @author Vijayakumar
 */
@Path("/products")
public class ProductssResource {

    @Context
    private UriInfo context;
    @Context
    HttpServletRequest request;
    TingcoProducts tingcoProducts = new TingcoProducts();
    ProductsDAO productsDAO = new ProductsDAO();
    RestUtilDAO utilDAO = new RestUtilDAO();
    GroupDAO groupDAO = new GroupDAO();
    DeviceDAO deviceDAO = new DeviceDAO();
    UserDAO userDAO = new UserDAO();
    TingcoProductsXML tingcoProductsXML = new TingcoProductsXML();
    SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    GregorianCalendar currentDateTime = new GregorianCalendar(TimeZone.getTimeZone("GMT"));

    /** Creates a new instance of ProductssResource */
    public ProductssResource() {
    }

    /**
     * GetGroupProductSubscription
     * @param GroupId
     * @param countryId
     * @return 
     */
    @GET
    @Produces("application/xml")
    @Path("/getgroupproductsubscription/rest/{rest}/groupid/{groupid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetGroupProductSubscription", desc = "Used to GetGroupProductSubscription", req_Params = {"GroupID;UUID", "CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_getGroupProductSubscription(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getGroupProductSubscription(groupId, countryId);
    }

    /**
     * GetGroupProductSubscription
     * @param GroupId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getgroupproductsubscription/json/{json}/groupid/{groupid}/countryid/{countryid}")
    public TingcoProducts getJson_getGroupProductSubscription(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getGroupProductSubscription(groupId, countryId);
    }

    /**
     * CancelProductSubscription
     * @param serviceclientloginid
     * @param servicesubscriptionaclid
     * @return 
     */
    @GET
    @Produces("application/xml")
    @Path("/cancelsubscription/rest/{rest}/serviceclientloginid/{serviceclientloginid}/servicesubscriptionaclid/{servicesubscriptionaclid}")
    @RESTDoc(methodName = "CancelProductSubscription", desc = "Used to Cancel the ProductSubscription", req_Params = {"ServiceClientLoginID;UUID", "ServiceSubscriptionACLID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_cancelProductSubscription(@PathParam("serviceclientloginid") String serviceClientLoginId, @PathParam("servicesubscriptionaclid") String serviceSubscriptionACLID) throws DatatypeConfigurationException {
        return cancelProductSubscription(serviceClientLoginId, serviceSubscriptionACLID);
    }

    /**
     * CancelProductSubscription
     * @param serviceclientloginid
     * @param servicesubscriptionaclid
     * @return 
     */
    @GET
    @Produces("application/json")
    @Path("/cancelsubscription/json/{json}/serviceclientloginid/{serviceclientloginid}/servicesubscriptionaclid/{servicesubscriptionaclid}")
    public TingcoProducts getJson_cancelProductSubscription(@PathParam("serviceclientloginid") String serviceClientLoginId, @PathParam("servicesubscriptionaclid") String serviceSubscriptionACLID) throws DatatypeConfigurationException {
        return cancelProductSubscription(serviceClientLoginId, serviceSubscriptionACLID);
    }

    /**
     * RenewProductSubscription
     * @param months
     * @param servicesubscriptionaclid
     * @param searchhitslefttouse
     * @param groupid
     * @param productvariantid
     * @param productvariantname
     * @param userid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/renewsubscription/rest/{rest}/servicesubscriptionaclid/{servicesubscriptionaclid}/months/{months}/searchhitslefttouse/{searchhitslefttouse}/groupid/{groupid}/productvariantid/{productvariantid}/productvariantname/{productvariantname}/userid/{userid}")
    @RESTDoc(methodName = "RenewProductSubscription", desc = "Used to Renew the ProductSubscription", req_Params = {"ServiceSubscriptionACLID;UUID", "months;string", "searchhitslefttouse;string", "groupid;string", "productvariantid;string", "productvariantname;string", "userid;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_renewProductSubscription(@PathParam("servicesubscriptionaclid") String serviceSubscriptionACLID, @PathParam("months") String months, @PathParam("searchhitslefttouse") String searchHitsLeftToUse, @PathParam("groupid") String groupId, @PathParam("productvariantid") String productVariantId, @PathParam("productvariantname") String productVariantName, @PathParam("userid") String userId) throws DatatypeConfigurationException {
        return renewProductSubscription(serviceSubscriptionACLID, months, searchHitsLeftToUse, groupId, productVariantId, productVariantName, userId);
    }

    /**
     * RenewProductSubscription
     * @param months
     * @param servicesubscriptionaclid
     * @param searchhitslefttouse
     * @param groupid
     * @param productvariantid
     * @param productvariantname
     * @param userid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/renewsubscription/json/{json}/servicesubscriptionaclid/{servicesubscriptionaclid}/months/{months}/searchhitslefttouse/{searchhitslefttouse}/groupid/{groupid}/productvariantid/{productvariantid}/productvariantname/{productvariantname}/userid/{userid}")
    public TingcoProducts getJson_renewProductSubscription(@PathParam("servicesubscriptionaclid") String serviceSubscriptionACLID, @PathParam("months") String months, @PathParam("searchhitslefttouse") String searchHitsLeftToUse, @PathParam("groupid") String groupId, @PathParam("productvariantid") String productVariantId, @PathParam("productvariantname") String productVariantName, @PathParam("userid") String userId) throws DatatypeConfigurationException {
        return renewProductSubscription(serviceSubscriptionACLID, months, searchHitsLeftToUse, groupId, productVariantId, productVariantName, userId);
    }

    /**
     * GetGroupLimitPackages
     * @param
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getgrouplimitpackages/rest/{rest}")
    @RESTDoc(methodName = "GetGroupLimitPackages", desc = "Used to Get GroupLimitPackages", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_getGroupLimitPackages() throws DatatypeConfigurationException {
        return getGroupLimitPackages();
    }

    /**
     * GetGroupLimitPackages
     * @param
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getgrouplimitpackages/json/{json}")
    public TingcoProducts getJson_getGroupLimitPackages() throws DatatypeConfigurationException {
        return getGroupLimitPackages();
    }

    /**
     * GetContentCategoryDetailsByCategoryId
     * @param contentCategoryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getcontentcategorydetailsbycategoryid/rest/{rest}/contentcategoryid/{contentcategoryid}")
    @RESTDoc(methodName = "GetContentCategoryDetailsByCategoryId", desc = "Used to Get ContentCategory Details By CategoryId", req_Params = {"ContentCategoryId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_getContentCategoryDetailsByCategoryId(@PathParam("contentcategoryid") String contentCategoryId) {
        return getContentCategoryDetailsByCategoryId(contentCategoryId);
    }

    /**
     * GetContentCategoryDetailsByCategoryId
     * @param contentCategoryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getcontentcategorydetailsbycategoryid/json/{json}/contentcategoryid/{contentcategoryid}")
    public TingcoProducts getJson_getContentCategoryDetailsByCategoryId(@PathParam("contentcategoryid") String contentCategoryId) {
        return getContentCategoryDetailsByCategoryId(contentCategoryId);
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
    public TingcoProducts getXml_GetProductCategories(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
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
    public TingcoProducts getJson_GetProductCategories(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getProductCategories(groupId, countryId);
    }

    /**
     * GetProductDetails
     * @param productcategoryid
     * @param groupid
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getproductdetails/rest/{rest}/productcategoryid/{productcategoryid}/groupid/{groupid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetProductDetails", desc = "Used to get the GetProductDetails", req_Params = {"ProductCategoryId;string", "GroupId;string", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_GetProductDetails(@PathParam("productcategoryid") String productCategoryId, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getProductDetails(productCategoryId, groupId, countryId);
    }

    /**
     * GetProductDetails
     * @param productcategoryid
     * @param groupid
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getproductdetails/json/{json}/productcategoryid/{productcategoryid}/groupid/{groupid}/countryid/{countryid}")
    public TingcoProducts getJson_GetProductDetails(@PathParam("productcategoryid") String productCategoryId, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getProductDetails(productCategoryId, groupId, countryId);
    }

    /**
     * GetPurchaseTransactionDetails
     * @param transactionId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getpurchasetransactiondetails/rest/{rest}/transactionid/{transactionid}/countryid/{countryid}")
    public TingcoProducts getXml_GetPurchaseTransactionDetails(@PathParam("transactionid") String transactionId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getPurchaseTransactionDetails(transactionId, countryId);
    }

    /**
     * GetPurchaseTransactionDetails
     * @param transactionId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getpurchasetransactiondetails/json/{json}/transactionid/{transactionid}/countryid/{countryid}")
    public TingcoProducts getJson_GetPurchaseTransactionDetails(@PathParam("transactionid") String transactionId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getPurchaseTransactionDetails(transactionId, countryId);
    }

    /**
     * getdeviceproductvariantspurchased
     * @param deviceId
     * @param groupId
     * @param countryId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getdeviceproductvariantspurchased/rest/{rest}/deviceid/{deviceid}/groupid/{groupid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetDeviceProductVariants", desc = "Used to Get DeviceProductVariants", req_Params = {"deviceid;UUID", "GroupId;string", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_getDeviceProductVariantsPurchased(@PathParam("deviceid") String deviceId, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getDeviceProductVariantsPurchased(deviceId, groupId, countryId);
    }

    /**
     * getdeviceproductvariantspurchased
     * @param deviceId
     * @param groupId
     * @param countryId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getdeviceproductvariantspurchased/json/{json}/deviceid/{deviceid}/groupid/{groupid}/countryid/{countryid}")
    public TingcoProducts getjson_getDeviceProductVariantsPurchased(@PathParam("deviceid") String deviceId, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getDeviceProductVariantsPurchased(deviceId, groupId, countryId);
    }

    /**
     * getdeviceproductvariants
     * @param deviceId
     * @param groupId
     * @param countryId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getdeviceproductvariants/rest/{rest}/deviceid/{deviceid}/groupid/{groupid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetDeviceProductVariants", desc = "Used to Get DeviceProductVariants", req_Params = {"deviceid;UUID", "GroupId;string", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_getDeviceProductVariants(@PathParam("deviceid") String deviceId, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getDeviceProductVariants(deviceId, groupId, countryId);
    }

    /**
     * getdeviceproductvariants
     * @param deviceId
     * @param groupId
     * @param countryId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getdeviceproductvariants/json/{json}/deviceid/{deviceid}/groupid/{groupid}/countryid/{countryid}")
    public TingcoProducts getjson_getDeviceProductVariants(@PathParam("deviceid") String deviceId, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getDeviceProductVariants(deviceId, groupId, countryId);
    }

    /**
     * GetServiceClientLoginsbyGroupID
     * @param groupid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getserviceclientloginsbygroupid/rest/{rest}/groupid/{groupid}")
    @RESTDoc(methodName = "GetServiceClientLoginsByGroupID", desc = "Used to Get Service Client Logins", req_Params = {"groupid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_getServiceClientLoginsByGroupID(@PathParam("groupid") String groupid) throws DatatypeConfigurationException {
        return getServiceClientLogins(groupid);
    }

    /**
     * GetServiceClientLoginsbyGroupID
     * @param groupid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getserviceclientloginsbygroupid/json/{json}/groupid/{groupid}")
    public TingcoProducts getJson_getServiceClientLoginsByGroupID(@PathParam("groupid") String groupid) throws DatatypeConfigurationException {
        return getServiceClientLogins(groupid);
    }

    /**
     * GetServiceClientLogins
     * @param groupid
     * @param searchstring
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getserviceclientlogins/rest/{rest}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoProducts getXml_getServiceClientLogins(@PathParam("groupid") String groupId, @PathParam("searchstring") String searchString) throws DatatypeConfigurationException {
        return getServiceClientLogins(groupId, searchString);
    }

    /**
     * GetServiceClientLogins
     * @param groupid
     * @param searchstring
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getserviceclientlogins/json/{json}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoProducts getJson_getServiceClientLogins(@PathParam("groupid") String groupId, @PathParam("searchstring") String searchString) throws DatatypeConfigurationException {
        return getServiceClientLogins(groupId, searchString);
    }

    /**
     * GetProductDetailsbyProductID
     * @param productid
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getproductdetailsbyproductid/rest/{rest}/productid/{productid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetProductDetailsbyProductID", desc = "Used to Get ProductDetailsbyProductID", req_Params = {"productid;string", "countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_ProductDetailsByProductID(@PathParam("productid") String productID, @PathParam("countryid") int countryID) throws DatatypeConfigurationException {
        return getProductDetailsByProductID(productID, countryID);
    }

    /**
     * GetProductDetailsbyProductID
     * @param productid
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getproductdetailsbyproductid/json/{json}/productid/{productid}/countryid/{countryid}")
    public TingcoProducts getJson_ProductDetailsByProductID(@PathParam("productid") String productID, @PathParam("countryid") int countryID) throws DatatypeConfigurationException {
        return getProductDetailsByProductID(productID, countryID);
    }

    /**
     * GetProductVariantsByGroupId
     * @param groupid
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getproductvariantsbygroupid/rest/{rest}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetProductVariantsByGroupId", desc = "Used to Get ProductVariants By GroupId", req_Params = {"groupId;UUID", "countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_productVariantsByGroupID(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("searchstring") String searchString) throws DatatypeConfigurationException {
        return getProductVariantsByGroupId(groupId, countryId, searchString);
    }

    /**
     * GetProductVariantsByGroupId
     * @param groupid
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getproductvariantsbygroupid/json/{json}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoProducts getJson_productVariantsByGroupID(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("searchstring") String searchString) throws DatatypeConfigurationException {
        return getProductVariantsByGroupId(groupId, countryId, searchString);
    }

    /**
     * GetProductVariantsByProductID
     * @param productid
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getproductvariantsbyproductid/rest/{rest}/productid/{productid}/countryid/{countryid}")
    @RESTDoc(methodName = "getproductvariantsbyproductid", desc = "Used to getproductvariantsbyproductid", req_Params = {"productid;UUID", "countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_productVariantsByProductID(@PathParam("productid") String productID, @PathParam("countryid") int countryID) throws DatatypeConfigurationException {
        return getProductVariantsByProductID(productID, countryID);
    }

    /**
     * GetProductVariantsByProductID
     * @param productid
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getproductvariantsbyproductid/json/{json}/productid/{productid}/countryid/{countryid}")
    public TingcoProducts getJson_productVariantsByProductID(@PathParam("productid") String productID, @PathParam("countryid") int countryID) throws DatatypeConfigurationException {
        return getProductVariantsByProductID(productID, countryID);
    }

    /**
     * GetProductsForGroups
     * @param groupid
     * @param countryid 
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getproductsforgroups/groupid/{groupid}/countryid/{countryid}/searchstring/{searchstring}")
    @RESTDoc(methodName = "GetProductsForGroups", desc = "Used to get the GetProductsForGroups", req_Params = {"groupid;string", "CountryId;int", "SearchString;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml(@PathParam("groupid") String groupid, @PathParam("searchstring") String searchString, @PathParam("countryid") int countryid) throws DatatypeConfigurationException {
        return getProductsForGroups(groupid, searchString, countryid);
    }

    /**
     * GetProductsForGroups
     * @param groupid
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getproductsforgroups/groupid/{groupid}/countryid/{countryid}/searchstring/{searchstring}")
    public TingcoProducts getJson(@PathParam("groupid") String groupid, @PathParam("searchstring") String searchString, @PathParam("countryid") int countryid) throws DatatypeConfigurationException {
        return getProductsForGroups(groupid, searchString, countryid);
    }

    /**
     * OrderContentSubscription
     * @param groupid
     * @param serviceclientloginid
     * @param productvariantid
     * @param subscriptionlength
     * @param countryid
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/ordercontentsubscription/rest/{rest}/groupid/{groupid}/serviceclientloginid/{serviceclientloginid}/productvariantid/{productvariantid}/subscriptionlength/{subscriptionlength}/countryid/{countryid}")
    @RESTDoc(methodName = "OrderContentSubscription", desc = "OrderContentSubscription", req_Params = {"groupid;UUID", "ServiceClientLoginId;UUID", "ProductVariantId;UUID", "SubscriptionLength;UUID", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_OrderContentSubscription(@PathParam("groupid") String groupid, @PathParam("serviceclientloginid") String serviceclientloginid, @PathParam("productvariantid") String productvariantid, @PathParam("subscriptionlength") String subscriptionlength, @PathParam("countryid") int countryid) throws DatatypeConfigurationException {
        return orderContentSubscription(groupid, serviceclientloginid, productvariantid, subscriptionlength, countryid);
    }

    /**
     * OrderContentSubscription
     * @param groupid
     * @param serviceclientloginid
     * @param productvariantid
     * @param subscriptionlength
     * @param countryid
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/ordercontentsubscription/json/{json}/groupid/{groupid}/serviceclientloginid/{serviceclientloginid}/productvariantid/{productvariantid}/subscriptionlength/{subscriptionlength}/countryid/{countryid}")
    public TingcoProducts getJson_OrderContentSubscription(@PathParam("groupid") String groupid, @PathParam("serviceclientloginid") String serviceclientloginid, @PathParam("productvariantid") String productvariantid, @PathParam("subscriptionlength") String subscriptionlength, @PathParam("countryid") int countryid) throws DatatypeConfigurationException {
        return orderContentSubscription(groupid, serviceclientloginid, productvariantid, subscriptionlength, countryid);
    }

    /**
     * 
     * @param groupid
     * @param searchString
     * @param countryid
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getproductsforproductvariants/rest/{rest}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "getProductsForProductVariants", desc = "Used to getProductsForProductVariants", req_Params = {"groupid;UUID", "countryId;int", "searchstring;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_GetProductsForProductVariants(@PathParam("groupid") String groupid, @PathParam("searchstring") String searchString, @PathParam("countryid") int countryid) throws DatatypeConfigurationException {
        return getProductsForProductVariants(groupid, searchString, countryid);
    }

    /**
     *
     * @param groupid
     * @param searchString
     * @param countryid
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getproductsforproductvariants/json/{json}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoProducts getJson_GetProductsForProductVariants(@PathParam("groupid") String groupid, @PathParam("searchstring") String searchString, @PathParam("countryid") int countryid) throws DatatypeConfigurationException {
        return getProductsForProductVariants(groupid, searchString, countryid);
    }

    /**
     * 
     * @param productVariantId
     * @param countryid
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getproductvariantinformation/rest/{rest}/productvariantid/{productvariantid}/countryid/{countryid}")
    @RESTDoc(methodName = "getproductvariantinformation", desc = "Used to getproductvariantinformation", req_Params = {"productvariantid;UUID", "countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_GetProductVariantInformation(@PathParam("productvariantid") String productVariantId, @PathParam("countryid") int countryid) throws DatatypeConfigurationException {
        return getProductVariantInformation(productVariantId, countryid);
    }

    /**
     * 
     * @param productVariantId
     * @param countryid
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getproductvariantinformation/json/{json}/productvariantid/{productvariantid}/countryid/{countryid}")
    public TingcoProducts getJson_GetProductVariantInformation(@PathParam("productvariantid") String productVariantId, @PathParam("countryid") int countryid) throws DatatypeConfigurationException {
        return getProductVariantInformation(productVariantId, countryid);
    }

    /**
     * UpdateProductVariantDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/updateproductvariantdetails/rest/{rest}")
    @RESTDoc(methodName = "update product variant details", desc = "Used to update product variant details", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoProducts postXml_UpdateProductVariantDetails(String content) throws DatatypeConfigurationException {
        return updateProductVariantDetails(content);
    }

    /**
     * UpdateProductVariantDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/updateproductvariantdetails/json/{json}")
    public TingcoProducts postJson_UpdateProductVariantDetails(String content) throws DatatypeConfigurationException {
        return updateProductVariantDetails(content);
    }

    /**
     * AddContentCategories
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addcontentcategories/rest/{rest}")
    @RESTDoc(methodName = "AddContentCategories", desc = "Used to Add ContentCategories", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoProducts postXml_addContentCategories(String content) {
        return addContentCategories(content);
    }

    /**
     * AddContentCategories
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addcontentcategories/json/{json}")
    public TingcoProducts postJson_addContentCategories(String content) {
        return addContentCategories(content);
    }

    /**
     * updateContentCategories
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/updatecontentcategories/rest/{rest}")
    @RESTDoc(methodName = "UpdateContentCategories", desc = "Used to Update ContentCategories", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoProducts postXml_updateContentCategories(String content) {
        return updateContentCategories(content);
    }

    /**
     * updateContentCategories
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/updatecontentcategories/json/{json}")
    public TingcoProducts postJson_updateContentCategories(String content) {
        return updateContentCategories(content);
    }

    /**
     * AddProductDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addproductdetails/rest/{rest}")
    @RESTDoc(methodName = "Add Product details", desc = "Used to add new Products, ProductTranslations and ProductsInGroups", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoProducts postXml_addProductDetails(String content) throws DatatypeConfigurationException {
        return addProductDetails(content);
    }

    /**
     * AddProductDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addproductdetails/json/{json}")
    public TingcoProducts postJson_addProductDetails(String content) throws DatatypeConfigurationException {
        return addProductDetails(content);
    }

    /**
     * AddProductVariantAttribute
     * @param productvariantid
     * @param attributename
     * @param attributevalue
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/addproductvariantattribute/rest/{rest}/productvariantid/{productvariantid}/attributename/{attributename}/attributevalue/{attributevalue}")
    @RESTDoc(methodName = "addproductvariantattribute", desc = "Used to addproductvariantattribute", req_Params = {"productvariantid;UUID", "attributename;string", "attributevalue;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_AddProductVariantAttribute(@PathParam("productvariantid") String productVariantId, @PathParam("attributename") String attributeName, @PathParam("attributevalue") String attributeValue) throws DatatypeConfigurationException {
        return addProductVariantAttribute(productVariantId, attributeName, attributeValue);
    }

    /**
     * AddProductVariantAttribute
     * @param productvariantid
     * @param attributename
     * @param attributevalue
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/addproductvariantattribute/json/{json}/productvariantid/{productvariantid}/attributename/{attributename}/attributevalue/{attributevalue}")
    public TingcoProducts getJson_AddProductVariantAttribute(@PathParam("productvariantid") String productVariantId, @PathParam("attributename") String attributeName, @PathParam("attributevalue") String attributeValue) throws DatatypeConfigurationException {
        return addProductVariantAttribute(productVariantId, attributeName, attributeValue);
    }

    /**
     * UpdateProductVariantAttribute
     * @param productVariantAttributeId
     * @param productvariantid
     * @param attributename
     * @param attributevalue
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/updateproductvariantattribute/rest/{rest}/productvariantattributeid/{productvariantattributeid}/productvariantid/{productvariantid}/attributename/{attributename}/attributevalue/{attributevalue}")
    @RESTDoc(methodName = "updateproductvariantattribute", desc = "Used to updateproductvariantattribute", req_Params = {"productvariantattributeid;UUID", "productvariantid;UUID", "attributename;string", "attributevalue;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_UpdateProductVariantAttribute(@PathParam("productvariantattributeid") String productVariantAttributeId, @PathParam("productvariantid") String productVariantId, @PathParam("attributename") String attributeName, @PathParam("attributevalue") String attributeValue) throws DatatypeConfigurationException {
        return updateProductVariantAttribute(productVariantAttributeId, productVariantId, attributeName, attributeValue);
    }

    /**
     * UpdateProductVariantAttribute
     * @param productVariantAttributeId
     * @param productvariantid
     * @param attributename
     * @param attributevalue
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/updateproductvariantattribute/json/{json}/productvariantattributeid/{productvariantattributeid}/productvariantid/{productvariantid}/attributename/{attributename}/attributevalue/{attributevalue}")
    public TingcoProducts getJson_UpdateProductVariantAttribute(@PathParam("productvariantattributeid") String productVariantAttributeId, @PathParam("productvariantid") String productVariantId, @PathParam("attributename") String attributeName, @PathParam("attributevalue") String attributeValue) throws DatatypeConfigurationException {
        return updateProductVariantAttribute(productVariantAttributeId, productVariantId, attributeName, attributeValue);
    }

    /**
     * DeleteProductVariantAttribute
     * @param productVariantAttributeId
     * @param productvariantid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deleteproductvariantattribute/rest/{rest}/productvariantattributeid/{productvariantattributeid}/productvariantid/{productvariantid}")
    @RESTDoc(methodName = "deleteproductvariantattribute", desc = "Used to deleteproductvariantattribute", req_Params = {"productvariantattributeid;UUID", "productvariantid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_DeleteProductVariantAttribute(@PathParam("productvariantattributeid") String productVariantAttributeId, @PathParam("productvariantid") String productVariantId) throws DatatypeConfigurationException {
        return deleteProductVariantAttribute(productVariantAttributeId, productVariantId);
    }

    /**
     * DeleteProductVariantAttribute
     * @param productVariantAttributeId
     * @param productvariantid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deleteproductvariantattribute/json/{json}/productvariantattributeid/{productvariantattributeid}/productvariantid/{productvariantid}")
    public TingcoProducts getJson_DeleteProductVariantAttribute(@PathParam("productvariantattributeid") String productVariantAttributeId, @PathParam("productvariantid") String productVariantId) throws DatatypeConfigurationException {
        return deleteProductVariantAttribute(productVariantAttributeId, productVariantId);
    }

    /**
     * AddProductVariantPrice
     * @param productVariantId
     * @param countryId
     * @param pricePerUnitExclvat
     * @param vatpercent
     * @param quantityunit
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/addproductvariantprice/rest/{rest}/productvariantid/{productvariantid}/countryid/{countryid}/priceperunitexclvat/{priceperunitexclvat}/vatpercent/{vatpercent}/quantityunit/{quantityunit}")
    @RESTDoc(methodName = "addproductvariantprice", desc = "Used to addproductvariantprice", req_Params = {"productvariantid;UUID", "countryid;int", "priceperunitexclvat;string", "vatpercent;string", "quantityunit;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_AddProductVariantPrice(@PathParam("productvariantid") String productVariantId, @PathParam("countryid") int countryId, @PathParam("priceperunitexclvat") String pricePerUnitExclVat, @PathParam("vatpercent") String vatPercent, @PathParam("quantityunit") String quantityUnit) throws DatatypeConfigurationException {
        return addProductVariantPrice(productVariantId, countryId, pricePerUnitExclVat, vatPercent, quantityUnit);
    }

    /**
     * AddProductVariantPrice
     * @param productVariantId
     * @param countryId
     * @param pricePerUnitExclvat
     * @param vatpercent
     * @param quantityunit
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/addproductvariantprice/json/json/productvariantid/{productvariantid}/countryid/{countryid}/priceperunitexclvat/{priceperunitexclvat}/vatpercent/{vatpercent}/quantityunit/{quantityunit}")
    public TingcoProducts getJson_AddProductVariantPrice(@PathParam("productvariantid") String productVariantId, @PathParam("countryid") int countryId, @PathParam("priceperunitexclvat") String pricePerUnitExclVat, @PathParam("vatpercent") String vatPercent, @PathParam("quantityunit") String quantityUnit) throws DatatypeConfigurationException {
        return addProductVariantPrice(productVariantId, countryId, pricePerUnitExclVat, vatPercent, quantityUnit);
    }

    /**
     * UpdateProductVariantPrice
     * @param productVariantId
     * @param countryId
     * @param pricePerUnitExclvat
     * @param vatpercent
     * @param quantityunit
     * @param newcountryid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/updateproductvariantprice/rest/{rest}/productvariantid/{productvariantid}/countryid/{countryid}/priceperunitexclvat/{priceperunitexclvat}/vatpercent/{vatpercent}/quantityunit/{quantityunit}{newcountryid:(/newcountryid/[^/]+?)?}")
    @RESTDoc(methodName = "updateproductvariantprice", desc = "Used to updateproductvariantprice", req_Params = {"productvariantid;UUID", "countryid;int", "priceperunitexclvat;string", "vatpercent;string", "quantityunit;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_UpdateProductVariantPrice(@PathParam("productvariantid") String productVariantId, @PathParam("countryid") int countryId, @PathParam("priceperunitexclvat") String pricePerUnitExclVat, @PathParam("vatpercent") String vatPercent, @PathParam("quantityunit") String quantityUnit, @PathParam("newcountryid") String newCountryId) throws DatatypeConfigurationException {
        return updateProductVariantPrice(productVariantId, countryId, pricePerUnitExclVat, vatPercent, quantityUnit, newCountryId);
    }

    /**
     * UpdateProductVariantPrice
     * @param productVariantId
     * @param countryId
     * @param pricePerUnitExclvat
     * @param vatpercent
     * @param quantityunit
     * @param newcountryid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/updateproductvariantprice/json/{json}/productvariantid/{productvariantid}/countryid/{countryid}/priceperunitexclvat/{priceperunitexclvat}/vatpercent/{vatpercent}/quantityunit/{quantityunit}{newcountryid:(/newcountryid/[^/]+?)?}")
    public TingcoProducts getJson_UpdateProductVariantPrice(@PathParam("productvariantid") String productVariantId, @PathParam("countryid") int countryId, @PathParam("priceperunitexclvat") String pricePerUnitExclVat, @PathParam("vatpercent") String vatPercent, @PathParam("quantityunit") String quantityUnit, @PathParam("newcountryid") String newCountryId) throws DatatypeConfigurationException {
        return updateProductVariantPrice(productVariantId, countryId, pricePerUnitExclVat, vatPercent, quantityUnit, newCountryId);
    }

    /**
     * DeleteProductVariantPrice
     * @param productVariantId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deleteproductvariantprice/rest/{rest}/productvariantid/{productvariantid}/countryid/{countryid}")
    @RESTDoc(methodName = "deleteproductvariantprice", desc = "Used to deleteproductvariantprice", req_Params = {"productvariantid;UUID", "countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_DeleteProductVariantPrice(@PathParam("productvariantid") String productVariantId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return deleteProductVariantPrice(productVariantId, countryId);
    }

    /**
     * DeleteProductVariantPrice
     * @param productVariantId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deleteproductvariantprice/json/{json}/productvariantid/{productvariantid}/countryid/{countryid}")
    public TingcoProducts getJson_DeleteProductVariantPrice(@PathParam("productvariantid") String productVariantId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return deleteProductVariantPrice(productVariantId, countryId);
    }

    /**
     * GetProductVariantTranslations
     * @param productVariantId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getproductvarianttranslations/rest/{rest}/productvariantid/{productvariantid}")
    @RESTDoc(methodName = "getproductvarianttranslations", desc = "Used to getproductvarianttranslations", req_Params = {"productvariantid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_GetProductVariantTranslations(@PathParam("productvariantid") String productVariantId) throws DatatypeConfigurationException {
        return getProductVariantTranslations(productVariantId);
    }

    /**
     * GetProductVariantTranslations
     * @param productVariantId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getproductvarianttranslations/json/{json}/productvariantid/{productvariantid}")
    public TingcoProducts getJson_GetProductVariantTranslations(@PathParam("productvariantid") String productVariantId) throws DatatypeConfigurationException {
        return getProductVariantTranslations(productVariantId);
    }

    /**
     * AddProductVariant
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addproductvariant/rest/{rest}")
    @RESTDoc(methodName = "add product variant", desc = "Used to add product variant", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoProducts postXml_AddProductVariant(String content) throws DatatypeConfigurationException {
        return addProductVariant(content);
    }

    /**
     * AddProductVariant
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addproductvariant/json/{json}")
    @RESTDoc(methodName = "addproductvariant", desc = "Used to addproductvariant", req_Params = {"json;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoProducts postJson_AddProductVariant(String content) throws DatatypeConfigurationException {
        return addProductVariant(content);
    }

    /**
     * DeleteProductVariant
     * @param productVariantId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deleteproductvariant/rest/{rest}/productvariantid/{productvariantid}")
    @RESTDoc(methodName = "deleteproductvariant", desc = "Used to deleteproductvariant", req_Params = {"productvariantid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_deleteProductVariant(@PathParam("productvariantid") String productVariantId) throws DatatypeConfigurationException {
        return deleteProductVariant(productVariantId);
    }

    /**
     * DeleteProductVariant
     * @param productVariantId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deleteproductvariant/json/{json}/productvariantid/{productvariantid}")
    public TingcoProducts getJson_deleteProductVariant(@PathParam("productvariantid") String productVariantId) throws DatatypeConfigurationException {
        return deleteProductVariant(productVariantId);
    }

    /**
     * AddProductsInGroups
     * @param groupid
     * @param productid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/addproductsingroups/rest/{rest}/groupid/{groupid}/productid/{productid}")
    @RESTDoc(methodName = "AddProductsInGroups", desc = "AddProductsInGroups", req_Params = {"groupid;UUID", "productid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_addProductsInGroups(@PathParam("groupid") String groupId, @PathParam("productid") String productId) throws DatatypeConfigurationException {
        return addProductsInGroups(groupId, productId);
    }

    /**
     * AddProductsInGroups
     * @param groupid
     * @param productid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/addproductsingroups/json/{json}/groupid/{groupid}/productid/{productid}")
    public TingcoProducts getJson_addProductsInGroups(@PathParam("groupid") String groupId, @PathParam("productid") String productId) throws DatatypeConfigurationException {
        return addProductsInGroups(groupId, productId);
    }

    /**
     * DeleteProductsInGroups
     * @param groupid
     * @param productid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deleteproductsingroups/rest/{rest}/groupid/{groupid}/productid/{productid}")
    @RESTDoc(methodName = "DeleteProductsInGroups", desc = "DeleteProductsInGroups", req_Params = {"groupid;UUID", "productid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_deleteProductsInGroups(@PathParam("groupid") String groupId, @PathParam("productid") String productId) throws DatatypeConfigurationException {
        return deleteProductsInGroups(groupId, productId);
    }

    /**
     * DeleteProductsInGroups
     * @param groupid
     * @param productid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deleteproductsingroups/json/{json}/groupid/{groupid}/productid/{productid}")
    public TingcoProducts getJson_deleteProductsInGroups(@PathParam("groupid") String groupId, @PathParam("productid") String productId) throws DatatypeConfigurationException {
        return deleteProductsInGroups(groupId, productId);
    }

    /**
     * AddNewProductCounterForDevice
     * @param deviceId
     * @param productVaraintId
     * @param counterValue
     * @param isRegistered
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/addnewproductcounterfordevice/rest/{rest}/deviceid/{deviceid}/productvaraintid/{productvaraintid}/countervalue/{countervalue}/isregistered/{isregistered}")
    @RESTDoc(methodName = "AddNewProductCounterForDevice", desc = "Add New Product Counter For Device", req_Params = {"deviceid;UUID", "productvaraintid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_AddNewProductCounterForDevice(@PathParam("deviceid") String deviceId,
            @PathParam("productvaraintid") String productVaraintId, @PathParam("countervalue") Integer counterValue, @PathParam("isregistered") Integer isRegistered) throws DatatypeConfigurationException {
        return addNewProductCounterForDevice(deviceId, productVaraintId, counterValue, isRegistered);
    }

    /**
     * AddNewProductCounterForDevice
     * @param deviceId
     * @param productVaraintId
     * @param counterValue
     * @param isRegistered
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/addnewproductcounterfordevice/json/{json}/deviceid/{deviceid}/productvaraintid/{productvaraintid}/countervalue/{countervalue}/isregistered/{isregistered}")
    public TingcoProducts getJson_AddNewProductCounterForDevice(@PathParam("deviceid") String deviceId,
            @PathParam("productvaraintid") String productVaraintId, @PathParam("countervalue") Integer counterValue, @PathParam("isregistered") Integer isRegistered) throws DatatypeConfigurationException {
        return addNewProductCounterForDevice(deviceId, productVaraintId, counterValue, isRegistered);
    }

    /**
     * POST method for creating an instance of ProductssResource
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
     * GetObjectProductPriceDetails
     * @param objectId
     * @param countryId
     * @param objecttype
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getobjectproductpricedetails/rest/{rest}/objectid/{objectid}/countryid/{countryid}/objecttype/{objecttype}")
    @RESTDoc(methodName = "GetObjectProductPriceDetails", desc = "Get Object Product Price Details", req_Params = {"objectid;UUID", "countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_GetObjectProductPriceDetails(@PathParam("objectid") String objectId,
            @PathParam("countryid") Integer countryId, @PathParam("objecttype") String objecttype) throws DatatypeConfigurationException {
        return getObjectProductPriceDetails(objectId, countryId, objecttype.toUpperCase());
    }

    /**
     * GetObjectProductPriceDetails
     * @param objectId
     * @param countryId
     * @param objecttype
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getobjectproductpricedetails/json/{json}/objectid/{objectid}/countryid/{countryid}/objecttype/{objecttype}")
    public TingcoProducts getJson_GetObjectProductPriceDetails(@PathParam("objectid") String objectId,
            @PathParam("countryid") Integer countryId, @PathParam("objecttype") String objecttype) throws DatatypeConfigurationException {
        return getObjectProductPriceDetails(objectId, countryId, objecttype.toUpperCase());
    }

    /**
     * GetObjectProductPriceByProductVariantID
     * @param objectId
     * @param productvariantId
     * @param objecttype
     * @param countryId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getobjectproductpricebyproductvariantid/rest/{rest}/objectid/{objectid}/productvariantid/{productvariantid}/objecttype/{objecttype}/countryid/{countryid}")
    @RESTDoc(methodName = "GetObjectProductPriceByProductVariantID", desc = "Get Object Product Price By ProductVariantID", req_Params = {"objectid;UUID", "productvariantid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_GetObjectProductPriceByProductVariantID(@PathParam("objectid") String objectId,
            @PathParam("productvariantid") String productvariantId, @PathParam("objecttype") String objecttype, @PathParam("countryid") Integer countryId) throws DatatypeConfigurationException {
        return getObjectProductPriceByProductVariantID(objectId, productvariantId, objecttype.toUpperCase(), countryId);
    }

    /**
     * GetObjectProductPriceByProductVariantID
     * @param objectId
     * @param productvariantId
     * @param objecttype
     * @param countryId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getobjectproductpricebyproductvariantid/josn/{json}/objectid/{objectid}/productvariantid/{productvariantid}/objecttype/{objecttype}/countryid/{countryid}")
    public TingcoProducts getJson_GetObjectProductPriceByProductVariantID(@PathParam("objectid") String objectId,
            @PathParam("productvariantid") String productvariantId, @PathParam("objecttype") String objecttype, @PathParam("countryid") Integer countryId) throws DatatypeConfigurationException {
        return getObjectProductPriceByProductVariantID(objectId, productvariantId, objecttype.toUpperCase(), countryId);
    }

    /**
     * AddObjectProductPrice
     * @param objectId
     * @param productvariantId
     * @param objecttype
     * @param countryId
     * @param currencyid
     * @param pricePerUnitExclvat
     * @param vatPercent
     * @param quantityUnit
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/addobjectoroductprice/rest/{rest}/objectid/{objectid}/productvariantid/{productvariantid}/objecttype/{objecttype}/countryid/{countryid}/currencyid/{currencyid}/priceperunitexclvat/{priceperunitexclvat}/vatpercent/{vatpercent}/quantityunit/{quantityunit}")
    @RESTDoc(methodName = "AddObjectProductPrice", desc = "Add Object Product Price", req_Params = {"objectid;UUID", "productvariantid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_AddObjectProductPrice(@PathParam("objectid") String objectId, @PathParam("productvariantid") String productvariantId,
            @PathParam("objecttype") String objecttype, @PathParam("countryid") Integer countryId, @PathParam("currencyid") Integer currencyid,
            @PathParam("priceperunitexclvat") String pricePerUnitExclvat, @PathParam("vatpercent") String vatPercent, @PathParam("quantityunit") String quantityUnit) throws DatatypeConfigurationException {
        return addObjectProductPrice(objectId, productvariantId, objecttype.toUpperCase(), countryId, currencyid, pricePerUnitExclvat, vatPercent, quantityUnit);
    }

    /**
     * AddObjectProductPrice
     * @param objectId
     * @param productvariantId
     * @param objecttype
     * @param countryId
     * @param currencyid
     * @param pricePerUnitExclvat
     * @param vatPercent
     * @param quantityUnit
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/addobjectoroductprice/json/{json}/objectid/{objectid}/productvariantid/{productvariantid}/objecttype/{objecttype}/countryid/{countryid}/currencyid/{currencyid}/priceperunitexclvat/{priceperunitexclvat}/vatpercent/{vatpercent}/quantityunit/{quantityunit}")
    public TingcoProducts getJson_AddObjectProductPrice(@PathParam("objectid") String objectId, @PathParam("productvariantid") String productvariantId,
            @PathParam("objecttype") String objecttype, @PathParam("countryid") Integer countryId, @PathParam("currencyid") Integer currencyid,
            @PathParam("priceperunitexclvat") String pricePerUnitExclvat, @PathParam("vatpercent") String vatPercent, @PathParam("quantityunit") String quantityUnit) throws DatatypeConfigurationException {
        return addObjectProductPrice(objectId, productvariantId, objecttype.toUpperCase(), countryId, currencyid, pricePerUnitExclvat, vatPercent, quantityUnit);
    }

    /**
     * UpdateObjectProductPrice
     * @param objectId
     * @param productvariantId
     * @param objecttype
     * @param currencyid
     * @param pricePerUnitExclvat
     * @param vatPercent
     * @param quantityUnit
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/updateobjectproductprice/rest/{rest}/objectid/{objectid}/productvariantid/{productvariantid}/objecttype/{objecttype}/currencyid/{currencyid}/priceperunitexclvat/{priceperunitexclvat}/vatpercent/{vatpercent}/quantityunit/{quantityunit}")
    @RESTDoc(methodName = "UpdateObjectProductPrice", desc = "Update Object Product Price", req_Params = {"objectid;UUID", "productvariantid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_UpdateObjectProductPrice(@PathParam("objectid") String objectId, @PathParam("productvariantid") String productvariantId,
            @PathParam("objecttype") String objecttype, @PathParam("currencyid") Integer currencyid,
            @PathParam("priceperunitexclvat") String pricePerUnitExclvat, @PathParam("vatpercent") String vatPercent, @PathParam("quantityunit") String quantityUnit) throws DatatypeConfigurationException {
        return updateObjectProductPrice(objectId, productvariantId, objecttype.toUpperCase(), currencyid, pricePerUnitExclvat, vatPercent, quantityUnit);
    }

    /**
     * UpdateObjectProductPrice
     * @param objectId
     * @param productvariantId
     * @param objecttype
     * @param currencyid
     * @param pricePerUnitExclvat
     * @param vatPercent
     * @param quantityUnit
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/updateobjectproductprice/json/{json}/objectid/{objectid}/productvariantid/{productvariantid}/objecttype/{objecttype}/currencyid/{currencyid}/priceperunitexclvat/{priceperunitexclvat}/vatpercent/{vatpercent}/quantityunit/{quantityunit}")
    public TingcoProducts getJson_UpdateObjectProductPrice(@PathParam("objectid") String objectId, @PathParam("productvariantid") String productvariantId,
            @PathParam("objecttype") String objecttype, @PathParam("currencyid") Integer currencyid,
            @PathParam("priceperunitexclvat") String pricePerUnitExclvat, @PathParam("vatpercent") String vatPercent, @PathParam("quantityunit") String quantityUnit) throws DatatypeConfigurationException {
        return updateObjectProductPrice(objectId, productvariantId, objecttype.toUpperCase(), currencyid, pricePerUnitExclvat, vatPercent, quantityUnit);
    }

    /**
     * DeleteObjectProductPrice
     * @param objectId
     * @param productvariantId
     * @param objecttype
     * @param currencyid
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/deleteobjectproductprice/rest/{rest}/objectid/{objectid}/productvariantid/{productvariantid}/objecttype/{objecttype}/currencyid/{currencyid}")
    @RESTDoc(methodName = "DeleteObjectProductPrice", desc = "Delete Object Product Price", req_Params = {"objectid;UUID", "productvariantid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_DeleteObjectProductPrice(@PathParam("objectid") String objectId, @PathParam("productvariantid") String productvariantId,
            @PathParam("objecttype") String objecttype, @PathParam("currencyid") Integer currencyid) throws DatatypeConfigurationException {
        return deleteObjectProductPrice(objectId, productvariantId, objecttype.toUpperCase(), currencyid);
    }

    /**
     * DeleteObjectProductPrice
     * @param objectId
     * @param productvariantId
     * @param objecttype
     * @param currencyid
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/deleteobjectproductprice/json/{json}/objectid/{objectid}/productvariantid/{productvariantid}/objecttype/{objecttype}/currencyid/{currencyid}")
    public TingcoProducts getJson_DeleteObjectProductPrice(@PathParam("objectid") String objectId, @PathParam("productvariantid") String productvariantId,
            @PathParam("objecttype") String objecttype, @PathParam("currencyid") Integer currencyid) throws DatatypeConfigurationException {
        return deleteObjectProductPrice(objectId, productvariantId, objecttype.toUpperCase(), currencyid);
    }

    /**
     * GetProductVariantPrice
     * @param deviceId
     * @param productvariantId
     * @param currencyid
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getproductvariantprice/rest/{rest}/deviceid/{deviceid}/productvariantid/{productvariantid}/currencyid/{currencyid}")
    @RESTDoc(methodName = "GetProductVariantPrice", desc = "Get ProductVariantPrice", req_Params = {"objectid;UUID", "productvariantid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_GetProductVariantPrice(@PathParam("deviceid") String deviceId, @PathParam("productvariantid") String productvariantId, @PathParam("currencyid") Integer currencyid) throws DatatypeConfigurationException {
        return getProductVariantPrice(deviceId, productvariantId, currencyid);
    }

    /**
     * GetProductVariantPrice
     * @param deviceId
     * @param productvariantId
     * @param currencyid
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getproductvariantprice/json/{json}/deviceid/{deviceid}/productvariantid/{productvariantid}/currencyid/{currencyid}")
    @RESTDoc(methodName = "GetProductVariantPrice", desc = "Get ProductVariantPrice", req_Params = {"objectid;UUID", "productvariantid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getJson_GetProductVariantPrice(@PathParam("deviceid") String deviceId, @PathParam("productvariantid") String productvariantId, @PathParam("currencyid") Integer currencyid) throws DatatypeConfigurationException {
        return getProductVariantPrice(deviceId, productvariantId, currencyid);
    }

    /**
     * GetCurrencyForDeviceID
     * @param deviceId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getcurrencyfordeviceid/rest/{rest}/deviceid/{deviceid}")
    @RESTDoc(methodName = "GetCurrencyForDeviceID", desc = "Get Currency For DeviceID", req_Params = {"deviceid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_GetCurrencyForDeviceID(@PathParam("deviceid") String deviceId) throws DatatypeConfigurationException {
        return getCurrencyForDeviceID(deviceId);
    }

    /**
     * GetCurrencyForDeviceID
     * @param deviceId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getcurrencyfordeviceid/json/{json}/deviceid/{deviceid}")
    public TingcoProducts getJson_GetCurrencyForDeviceID(@PathParam("deviceid") String deviceId) throws DatatypeConfigurationException {
        return getCurrencyForDeviceID(deviceId);
    }

    /**
     * GetPaymentProviders
     * @param groupId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getpaymentproviders/rest/{rest}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetPaymentProviders", desc = "GetPaymentProviders", req_Params = {"groupId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_GetPaymentProviders(@PathParam("groupid") String groupId, @PathParam("searchstring") String searchString) throws DatatypeConfigurationException {
        return getPaymentProviders(groupId, searchString);
    }

    /**
     * GetPaymentProviders
     * @param groupId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getpaymentproviders/json/{json}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoProducts getJson_GetPaymentProviders(@PathParam("groupid") String groupId, @PathParam("searchstring") String searchString) throws DatatypeConfigurationException {
        return getPaymentProviders(groupId, searchString);
    }

    /**
     * GetProductVariantsByGroupId
     * @param groupid
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getproductvariantsbygroupidwithlimit/rest/{rest}/groupid/{groupid}/countryid/{countryid}{maxresult:(/maxresult/[^/]+?)?}")
    @RESTDoc(methodName = "GetProductVariantsByGroupId", desc = "Used to Get ProductVariants By GroupId", req_Params = {"groupId;UUID", "countryid;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_productVariantsByGroupIDWithLimit(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("maxresult") String limit) throws DatatypeConfigurationException {
        return getProductVariantsByGroupIdWithLimit(groupId, countryId, limit);
    }

    /**
     * GetProductVariantsByGroupId
     * @param groupid
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getproductvariantsbygroupidwithlimit/json/{json}/groupid/{groupid}/countryid/{countryid}{maxresult:(/maxresult/[^/]+?)?}")
    public TingcoProducts getJson_productVariantsByGroupIDWithLimit(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("maxresult") String limit) throws DatatypeConfigurationException {
        return getProductVariantsByGroupIdWithLimit(groupId, countryId, limit);
    }

    /**
     * getdeviceproductvariants
     * @param deviceId
     * @param groupId
     * @param countryId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getdeviceproductvariantswithlimit/rest/{rest}/deviceid/{deviceid}/countryid/{countryid}{maxresult:(/maxresult/[^/]+?)?}")
    @RESTDoc(methodName = "GetDeviceProductVariants", desc = "Used to Get DeviceProductVariants", req_Params = {"deviceid;UUID", "GroupId;string", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_getDeviceProductVariantsWithLimit(@PathParam("deviceid") String deviceId, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("maxresult") String limit) throws DatatypeConfigurationException {
        return getDeviceProductVariantsWithLimit(deviceId, countryId, limit);
    }

    /**
     * getdeviceproductvariants
     * @param deviceId
     * @param groupId
     * @param countryId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getdeviceproductvariantswithlimit/json/{json}/deviceid/{deviceid}/countryid/{countryid}{maxresult:(/maxresult/[^/]+?)?}")
    @RESTDoc(methodName = "GetDeviceProductVariants", desc = "Used to Get DeviceProductVariants", req_Params = {"deviceid;UUID", "GroupId;string", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getJson_getDeviceProductVariantsWithLimit(@PathParam("deviceid") String deviceId, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("maxresult") String limit) throws DatatypeConfigurationException {
        return getDeviceProductVariantsWithLimit(deviceId, countryId, limit);
    }

    /**
     * getdeviceproductvariants
     * @param deviceId
     * @param groupId
     * @param countryId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getdeviceproductvariants/json/{json}/deviceid/{deviceid}/countryid/{countryid}{maxresult:(/maxresult/[^/]+?)?}")
    public TingcoProducts getjson_getDeviceProductVariantsWithLimit(@PathParam("deviceid") String deviceId, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("maxresult") String limit) throws DatatypeConfigurationException {
        return getDeviceProductVariantsWithLimit(deviceId, countryId, limit);
    }

    /**
     *
     * @param content
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addmanualpurchase/rest/{rest}")
    @RESTDoc(methodName = "Add Manual Purchase", desc = "Used to ADD product Purchase", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoProducts postXml_addManualPurchase(String content) throws DatatypeConfigurationException {
        return addManualPurchase(content);
    }

    /**
     *
     * @param content
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addmanualpurchase/json/{json}")
    @RESTDoc(methodName = "update product variant details", desc = "Used to update product variant details", req_Params = {"json;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoProducts postJson_addManualPurchase(String content) throws DatatypeConfigurationException {
        return addManualPurchase(content);
    }

    /**
     *
     * @param content
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/addmanualpurchasetim/rest/{rest}/deviceid/{deviceid}/productvaraintid/{productvaraintid}/Quantity/{Quantity}/countryid/{countryid}")
    @RESTDoc(methodName = "update product variant details", desc = "Used to update product variant details", req_Params = {"productvaraintid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoProducts postXml_addManualPurchasegTim(@PathParam("deviceid") String deviceId, @PathParam("productvaraintid") String productVaraintId, @PathParam("Quantity") String quantity, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return addManualPurchaseTim(deviceId, productVaraintId, quantity, countryId);
    }

    /**
     *
     * @param content
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/addmanualpurchasetim/json/{json}/deviceid/{deviceid}/productvaraintid/{productvaraintid}/Quantity/{Quantity}/countryid/{countryid}")
    @RESTDoc(methodName = "update product variant details", desc = "Used to update product variant details", req_Params = {"productvaraintid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoProducts postJson_addManualPurchasegTim(@PathParam("deviceid") String deviceId, @PathParam("productvaraintid") String productVaraintId, @PathParam("Quantity") String quantity, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return addManualPurchaseTim(deviceId, productVaraintId, quantity, countryId);
    }

    /**
     *
     * @param groupId
     * @param countryId
     * @param searchString
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getproductvariantsfordevicereport/rest/{rest}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetDeviceProductVariants", desc = "Used to Get DeviceProductVariants", req_Params = {"GroupId;string", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_GetProductVariantsForDeviceReport(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("searchstring") String searchString) {
        return getProductVariantsForDeviceReport(groupId, countryId, searchString);
    }

    /**
     *
     * @param groupId
     * @param countryId
     * @param searchString
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getproductvariantsfordevicereport/json/{json}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetDeviceProductVariants", desc = "Used to Get DeviceProductVariants", req_Params = {"GroupId;string", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getJson_GetProductVariantsForDeviceReport(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("searchstring") String searchString) {
        return getProductVariantsForDeviceReport(groupId, countryId, searchString);
    }

    @GET
    @Produces("application/xml")
    @Path("/checkuserkeyexistfordevice/rest/{rest}/deviceid/{deviceid}")
    @RESTDoc(methodName = "GetDeviceProductVariants", desc = "Used to Get DeviceProductVariants", req_Params = {"GroupId;string", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_CheckUserKeyExistForDevice(@PathParam("deviceid") String deviceId) {
        return CheckUserKeyExistForDevice(deviceId);
    }

    @GET
    @Produces("application/json")
    @Path("/checkuserkeyexistfordevice/json/{json}/deviceid/{deviceid}")
    public TingcoProducts getJson_CheckUserKeyExistForDevice(@PathParam("deviceid") String deviceId) {
        return CheckUserKeyExistForDevice(deviceId);
    }

    /**
     * GetProductVariantsForProductCounter
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getproductvariantsforproductcounter/rest/{rest}/deviceid/{deviceid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetProductVariantsForProductCounter", desc = "Get Get ProductVariants For ProductCounter", req_Params = {"deviceid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_GetProductVariantsForProductCounter(@PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId) {
        return getProductVariantsForProductCounter(deviceId, countryId);
    }

    /**
     * GetProductVariantsForProductCounter
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getproductvariantsforproductcounter/json/{json}/deviceid/{deviceid}/countryid/{countryid}")
    public TingcoProducts getJson_GetProductVariantsForProductCounter(@PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId) {
        return getProductVariantsForProductCounter(deviceId, countryId);
    }

    /**
     * GetProductCategoryDetails
     * @param countryId
     * @param searchString
     * @return {searchstring:(/searchstring/[^/]+?)?}
     */
    @GET
    @Produces("application/xml")
    @Path("/getproductcategorydetails/rest/{rest}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetProductCategoryDetails", desc = "Used to Get ProductCategoryDetails", req_Params = {"CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_GetProductCategoryDetails(@PathParam("countryid") int countryId, @PathParam("searchstring") String searchString) {
        return getProductCategoryDetails(countryId, searchString);
    }

    /**
     * GetProductCategoryDetails
     * @param countryId
     * @param searchString
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getproductcategorydetails/json/{json}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetProductCategoryDetails", desc = "Used to Get ProductCategoryDetails", req_Params = {"CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getJson_GetProductCategoryDetails(@PathParam("countryid") int countryId, @PathParam("searchstring") String searchString) {
        return getProductCategoryDetails(countryId, searchString);
    }

    /**
     * GetProductTypeDetails
     * @param searchString
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getgroducttypedetails/rest/{rest}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetProductTypeDetails", desc = "Used to Get ProductType Details", req_Params = {"CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_GetProductTypeDetails(@PathParam("searchstring") String searchString) {
        return getProductTypeDetails(searchString);
    }

    /**
     * GetProductTypeDetails
     * @param searchString
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getgroducttypedetails/json/{json}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetProductTypeDetails", desc = "Used to Get ProductType Details", req_Params = {"CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getJson_GetProductTypeDetails(@PathParam("searchstring") String searchString) {
        return getProductTypeDetails(searchString);
    }

    @GET
    @Produces("application/xml")
    @Path("/getproductdetails/rest/{rest}/countryid/{countryid}{productid:(/productid/[^/]+?)?}{categoryid:(/categoryid/[^/]+?)?}{producttypeid:(/producttypeid/[^/]+?)?}{searchgroupid:(/searchgroupid/[^/]+?)?}")
    @RESTDoc(methodName = "GetProductTypeDetails", desc = "Used to Get ProductType Details", req_Params = {"CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_GetProductDetails(@PathParam("countryid") int countryId, @PathParam("productid") String productId, @PathParam("categoryid") String categoryId, @PathParam("producttypeid") String productTypeId, @PathParam("searchgroupid") String searchGroupId) {
        return getProductDetails(countryId, productId, categoryId, productTypeId, searchGroupId);
    }

    @GET
    @Produces("application/json")
    @Path("/getproductdetails/json/{Json}/countryid/{countryid}{productid:(/productid/[^/]+?)?}{categoryid:(/categoryid/[^/]+?)?}{producttypeid:(/producttypeid/[^/]+?)?}{searchgroupid:(/searchgroupid/[^/]+?)?}")
    @RESTDoc(methodName = "GetProductTypeDetails", desc = "Used to Get ProductType Details", req_Params = {"CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getJson_GetProductDetails(@PathParam("countryid") int countryId, @PathParam("productid") String productId, @PathParam("categoryid") String categoryId, @PathParam("producttypeid") String productTypeId, @PathParam("searchgroupid") String searchGroupId) {
        return getProductDetails(countryId, productId, categoryId, productTypeId, searchGroupId);
    }

    @POST
    @Produces("application/xml")
    @Consumes("application/xml")
    @Path("/updateproducts/rest/{rest}")
    @RESTDoc(methodName = "UpdateProducts", desc = "UpdateProducts", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoProducts getXml_UpdateProducts(String content) {
        return updateProducts(content);
    }

    @POST
    @Produces("application/json")
    @Consumes("application/xml")
    @Path("/updateproducts/json/{json}")
    @RESTDoc(methodName = "UpdateProducts", desc = "UpdateProducts", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoProducts getJson_UpdateProducts(String content) {
        return updateProducts(content);
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public ProductsResource getProductsResource() {
        return new ProductsResource();
    }

    /**
     * GetProductDetailsbyProductID
     * @param countryId
     * @param productId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getproductdetailsbyproductid/rest/{rest}/countryid/{countryid}/productid/{productid}")
    @RESTDoc(methodName = "GetProductDetailsbyProductID", desc = "GetProductDetailsbyProductID", req_Params = {"CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoProducts getXml_GetProductDetailsbyProductID(@PathParam("countryid") int countryId, @PathParam("productid") String productId) {
        return getProductDetailsbyProductID(countryId, productId);
    }

    /**
     * GetProductDetailsbyProductID
     * @param countryId
     * @param productId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getproductdetailsbyproductid/json/{json}/countryid/{countryid}/productid/{productid}")
    public TingcoProducts getJson_GetProductDetailsbyProductID(@PathParam("countryid") int countryId, @PathParam("productid") String productId) {
        return getProductDetailsbyProductID(countryId, productId);
    }

    private TingcoProducts addContentCategories(String content) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.CONTENT, TCMUtil.ADD);
                if (hasPermission) {
                    se.info24.productsjaxb.TingcoProducts tingcoProductsPost = (se.info24.productsjaxb.TingcoProducts) JAXBManager.getInstance().unMarshall(content, se.info24.productsjaxb.TingcoProducts.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    se.info24.productsjaxb.ContentCategory contentCategoryjaxb = tingcoProductsPost.getProducts().getProduct().get(0).getContentCategories().get(0).getContentCategory().get(0);
                    ContentDAO contentDAO = new ContentDAO();
                    Set<ContentCategoryTranslations> contentCategoryTransSet = new HashSet<ContentCategoryTranslations>();
                    ContentCategories contentCategories = new ContentCategories();
                    String contentCategoryId = UUID.randomUUID().toString();
                    contentCategories.setContentCategoryId(contentCategoryId);
                    if (contentCategoryjaxb.getContentCategoryParentID() != null) {
                        contentCategories.setContentCategoryParentId(contentCategoryjaxb.getContentCategoryParentID());
                    }
                    contentCategories.setUserId(sessions2.getUserId());
                    contentCategories.setCreatedDate(currentDateTime.getTime());
                    contentCategories.setUpdatedDate(currentDateTime.getTime());
                    for (se.info24.productsjaxb.ContentCategoryTranslations cct : contentCategoryjaxb.getContentCategoryTranslations()) {
                        ContentCategoryTranslations contentCategoryTrans = new ContentCategoryTranslations();
                        contentCategoryTrans.setId(new ContentCategoryTranslationsId(contentCategoryId, cct.getCountryID()));
                        contentCategoryTrans.setContentCategoryName(cct.getContentCategoryName());
                        contentCategoryTrans.setUserId(sessions2.getUserId());
                        contentCategoryTrans.setCreatedDate(currentDateTime.getTime());
                        contentCategoryTrans.setUpdatedDate(currentDateTime.getTime());
                        contentCategoryTransSet.add(contentCategoryTrans);
                    }
                    contentCategories.setContentCategoryTranslationses(contentCategoryTransSet);
                    if (!contentDAO.saveContentCategory(contentCategories, session)) {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Error occured while Adding");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured while Adding");
            return tingcoProducts;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts updateContentCategories(String content) {
        boolean hasPermission = false;
        Session session = null;
        Transaction tx = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.CONTENT, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    Set<ContentCategoryTranslations> contentCategoryTransSet = new HashSet<ContentCategoryTranslations>();
                    ContentDAO contentDAO = new ContentDAO();
                    se.info24.productsjaxb.TingcoProducts tingcoProductsPost = (se.info24.productsjaxb.TingcoProducts) JAXBManager.getInstance().unMarshall(content, se.info24.productsjaxb.TingcoProducts.class);
                    se.info24.productsjaxb.ContentCategory contentCategoryjaxb = tingcoProductsPost.getProducts().getProduct().get(0).getContentCategories().get(0).getContentCategory().get(0);
                    ContentCategories contentCategories = (ContentCategories) contentDAO.getContentCategoryById(contentCategoryjaxb.getContentCategoryID(), session);

                    Iterator iterator = contentCategories.getContentCategoryTranslationses().iterator();
                    while (iterator.hasNext()) {
                        ContentCategoryTranslations cct = (ContentCategoryTranslations) iterator.next();
                        iterator.remove();
                        session.delete(cct);
                    }

                    session.flush();
                    session.clear();

                    if (contentCategoryjaxb.getContentCategoryParentID() != null) {
                        contentCategories.setContentCategoryParentId(contentCategoryjaxb.getContentCategoryParentID());
                    }

                    contentCategories.setUserId(sessions2.getUserId());
                    contentCategories.setCreatedDate(currentDateTime.getTime());
                    contentCategories.setUpdatedDate(currentDateTime.getTime());

//                    Set<ContentCategoryTranslations> contentCategoryTransSet = contentCategories.getContentCategoryTranslationses();
//                    tx.commit();
//                    contentCategoryTransSet.clear(); //Clearing to reuse the same Set for adding new contentCategoryTranslations

                    for (se.info24.productsjaxb.ContentCategoryTranslations cct : contentCategoryjaxb.getContentCategoryTranslations()) {
                        ContentCategoryTranslations contentCategoryTrans = new ContentCategoryTranslations();
                        contentCategoryTrans.setId(new ContentCategoryTranslationsId(contentCategories.getContentCategoryId(), cct.getCountryID()));
                        contentCategoryTrans.setContentCategoryName(cct.getContentCategoryName());
                        contentCategoryTrans.setUserId(sessions2.getUserId());
                        contentCategoryTrans.setCreatedDate(currentDateTime.getTime());
                        contentCategoryTrans.setUpdatedDate(currentDateTime.getTime());
                        contentCategoryTransSet.add(contentCategoryTrans);
                    }
                    contentCategories.setContentCategoryTranslationses(contentCategoryTransSet);
                    contentDAO.saveContentCategory(contentCategories, session);
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured while Updating");
            return tingcoProducts;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts getProductDetailsbyProductID(int countryId, String productId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRODUCTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRODUCTS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object productsObject = productsDAO.getProductsByProductID(productId, session);
                    if (productsObject == null) {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("No Data Found");
                        return tingcoProducts;
                    } else {
                        Products products = (Products) productsObject;
                        ProductCategoryTranslations productCategoryTranslations = null;
                        ProductTypes productTypes = null;
                        ProductTranslations productTranslations = null;
                        Object productTranslationsObjetct = productsDAO.getProductTranslationsById(products.getProductId(), countryId, session);
                        if (productTranslationsObjetct != null) {
                            productTranslations = (ProductTranslations) productTranslationsObjetct;
                        }
                        if (products.getProductCategories() != null) {
                            Object productCategoriesObject = productsDAO.getProductCategoryTranslationsByID(products.getProductCategories().getProductCategoryId(), countryId, session);
                            productCategoryTranslations = (ProductCategoryTranslations) productCategoriesObject;
                        }
                        if (products.getProductTypes() != null) {
                            Object productTypeObject = productsDAO.getProductTypesByProductTypeID(session, products.getProductTypes().getProductTypeId());
                            productTypes = (ProductTypes) productTypeObject;
                        }
                        tingcoProducts = tingcoProductsXML.buildGetProductDetailsbyProductID(tingcoProducts, products, productTranslations, productCategoryTranslations, productTypes);
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts updateProducts(String content) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRODUCTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRODUCTS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    se.info24.productsjaxb.TingcoProducts tingcoProductsPost = (se.info24.productsjaxb.TingcoProducts) JAXBManager.getInstance().unMarshall(content, se.info24.productsjaxb.TingcoProducts.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    se.info24.productsjaxb.Products productsPost = tingcoProductsPost.getProducts();
                    if (productsPost.getProduct().isEmpty()) {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("No Product Information");
                        return tingcoProducts;
                    } else {
                        for (se.info24.productsjaxb.Product productPost : productsPost.getProduct()) {
                            Object object = productsDAO.getProductsByProductID(productPost.getProductID(), session);
                            if (object == null) {
                                tingcoProducts.getMsgStatus().setResponseCode(-1);
                                tingcoProducts.getMsgStatus().setResponseText("No Data Found With Given ProductID");
                                return tingcoProducts;
                            } else {
                                Products modifiedProducts = (Products) object;
                                if (productPost.getProductCategoryID() != null) {
                                    Object productCategoriesObject = productsDAO.getProductCategoriesByProductCategoryID(productPost.getProductCategoryID(), session);
                                    if (productCategoriesObject != null) {
                                        modifiedProducts.setProductCategories((ProductCategories) productCategoriesObject);
                                    }
                                }
                                if (productPost.getProductTypeID() != null) {
                                    Object productTypeObject = productsDAO.getProductTypesByProductTypeID(session, productPost.getProductTypeID());
                                    if (productTypeObject != null) {
                                        modifiedProducts.setProductTypes((ProductTypes) productTypeObject);
                                    }
                                }
                                modifiedProducts.setIsDownloadProduct(productPost.getIsDownloadProduct());
                                modifiedProducts.setIsEnabled(productPost.getIsEnabled());
                                modifiedProducts.setDisplayInWebShop(productPost.getDisplayInWebShop());
                                modifiedProducts.setExtendedMembershipRequired(productPost.getExtendedMembershipRequired());
                                modifiedProducts.setUpdatedDate(currentDateTime.getTime());

                                ProductTranslations productTranslations = null;

                                for (se.info24.productsjaxb.ProductTranslations ptPost : productPost.getProductTranslations()) {
                                    if (ptPost.getProductName() != null) {
                                        Object productTranslationsObjetct = productsDAO.getProductTranslationsById(modifiedProducts.getProductId(), ptPost.getCountryID(), session);
                                        if (productTranslationsObjetct != null) {
                                            TCMUtil.loger(this.getClass().getName(), ptPost.getProductName(), "info");
                                            productTranslations = (ProductTranslations) productTranslationsObjetct;
                                            productTranslations.setProductName(ptPost.getProductName());
                                            productTranslations.setUpdatedDate(currentDateTime.getTime());
                                        }
                                    }
                                }
                                productsDAO.updateProducts(session, modifiedProducts, productTranslations);
                            }
                        }
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts getProductDetails(int countryId, String productId, String categoryId, String productTypeId, String searchGroupId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRODUCTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRODUCTS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    if (!productId.equals("")) {
                        productId = productId.split("/")[2];
                    } else {
                        productId = null;
                    }
                    if (!categoryId.equals("")) {
                        categoryId = categoryId.split("/")[2];
                    } else {
                        categoryId = null;
                    }
                    if (!productTypeId.equals("")) {
                        productTypeId = productTypeId.split("/")[2];
                    } else {
                        productTypeId = null;
                    }
                    if (!searchGroupId.equals("")) {
                        searchGroupId = searchGroupId.split("/")[2];
                    } else {
                        searchGroupId = null;
                    }
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<Products> products = new ArrayList<Products>();

                    if (productId == null && categoryId == null && productTypeId == null && searchGroupId == null) {
                        products = productsDAO.getAllProducts(session);
                    } else {
                        List<Object> productsObject = productsDAO.getSearchProductDetail(countryId, productId, categoryId, productTypeId, searchGroupId, session);
                        if (productsObject.isEmpty()) {
                            tingcoProducts.getMsgStatus().setResponseCode(-1);
                            tingcoProducts.getMsgStatus().setResponseText("Data Not Found");
                            return tingcoProducts;
                        }

                        List<String> productIdList = new ArrayList<String>();
                        for (Iterator itr = productsObject.iterator(); itr.hasNext();) {
                            productIdList.add(itr.next().toString());
                        }
                        products = productsDAO.getProducts(productIdList, session);
                    }
                    if (products.isEmpty()) {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoProducts;
                    }
                    Set<String> userid = new HashSet();
                    Set<String> productIdList = new HashSet();
                    for (Products products1 : products) {
                        userid.add(products1.getUserId());
                        productIdList.add(products1.getProductId());
                    }
                    List<ProductTranslations> productTranslationses = productsDAO.getProductTranslations(session, productIdList, countryId);


                    List<Users2> users = groupDAO.getUsersList(new ArrayList<String>(userid), session);
                    return tingcoProductsXML.buildGetProductDetails(tingcoProducts, products, users, countryId, session, productTranslationses);

                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts getProductTypeDetails(String searchString) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRODUCTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRODUCTS);
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
                    List<ProductTypes> productTypeses = new ArrayList<ProductTypes>();
                    if (searchString != null) {
                        if (TCMUtil.isValidUUID(searchString)) {
                            Object pt = productsDAO.getProductTypesByProductTypeID(session, searchString);
                            if (pt == null) {
                                tingcoProducts.getMsgStatus().setResponseCode(-1);
                                tingcoProducts.getMsgStatus().setResponseText("No Data Found");
                                return tingcoProducts;
                            } else {
                                productTypeses.add((ProductTypes) pt);
                            }
                        } else {
                            productTypeses.addAll(productsDAO.getProductTypesByProductTypeNameSearchString(session, searchString));
                        }
                    } else {
                        productTypeses = productsDAO.getProductTypes(session);
                    }

                    tingcoProducts = tingcoProductsXML.buildGetProductTypeDetails(tingcoProducts, productTypeses);
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts getProductCategoryDetails(int countryId, String searchString) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRODUCTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRODUCTS);
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
                    Users2 loggedInUser = userDAO.getUserById(sessions2.getUserId(), session);
                    List<ProductCategoryTranslations> categoryTranslationses = new ArrayList<ProductCategoryTranslations>();
                    if (searchString != null) {
                        if (TCMUtil.isValidUUID(searchString)) {
                            Object pcig = productsDAO.getProductCategoriesInGroupsByGroupIdAndProductCategoryID(loggedInUser.getGroupId(), searchString, session);
                            if (pcig == null) {
                                tingcoProducts.getMsgStatus().setResponseCode(-1);
                                tingcoProducts.getMsgStatus().setResponseText("No Data Found");
                                return tingcoProducts;
                            } else {
                                ProductCategoriesInGroups pct = (ProductCategoriesInGroups) pcig;
                                categoryTranslationses.add(productsDAO.getProductCategoryTranslationsByID(pct.getId().getProductCategoryId(), countryId, session));
                            }
                        } else {
                            List<ProductCategoriesInGroups> categoriesInGroupses = productsDAO.getProductCategoriesInGroups(session, loggedInUser.getGroupId());
                            if (categoriesInGroupses.isEmpty()) {
                                tingcoProducts.getMsgStatus().setResponseCode(-1);
                                tingcoProducts.getMsgStatus().setResponseText("No Data Found");
                                return tingcoProducts;
                            } else {
                                Set<String> pcIds = new HashSet<String>();
                                for (ProductCategoriesInGroups pcig : categoriesInGroupses) {
                                    pcIds.add(pcig.getId().getProductCategoryId());
                                }
                                categoryTranslationses.addAll(productsDAO.getProductCategoryTranslationsByIDAndSearchString(session, pcIds, searchString, countryId));
                            }
                        }
                    } else {
                        List<ProductCategoriesInGroups> categoriesInGroupses = productsDAO.getProductCategoriesInGroups(session, loggedInUser.getGroupId());
                        if (categoriesInGroupses.isEmpty()) {
                            tingcoProducts.getMsgStatus().setResponseCode(-1);
                            tingcoProducts.getMsgStatus().setResponseText("No Data Found");
                            return tingcoProducts;
                        }
                        categoryTranslationses = productsDAO.getProductCategoryTranslations(session, categoriesInGroupses, countryId);
//                        Set<String> pcIds = new HashSet<String>();
//                                for (ProductCategoriesInGroups pcig : categoriesInGroupses) {
//                                    pcIds.add(pcig.getId().getProductCategoryId());
//                                }
//                        categoryTranslationses = productsDAO.getProductCategoryTranslationsByCountryID(session, countryId);
                    }
                    tingcoProducts = tingcoProductsXML.buildGetProductCategoryDetails(tingcoProducts, categoryTranslationses);
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts addProductDetails(String content) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        Transaction tx = null;
        try {
            tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.PRODUCTS, TCMUtil.ADD);
                if (hasPermission) {
                    se.info24.productsjaxb.TingcoProducts tingcoProductsPost = (se.info24.productsjaxb.TingcoProducts) JAXBManager.getInstance().unMarshall(content, se.info24.productsjaxb.TingcoProducts.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    Product productJaxb = tingcoProductsPost.getProducts().getProduct().get(0);
                    String productId = UUID.randomUUID().toString();
//                    Set<ProductsInGroups> productsInGroupsSet = new HashSet<ProductsInGroups>();
//                    Set<ProductTranslations> productTransSet = new HashSet<ProductTranslations>();

                    Products products = new Products();
                    products.setProductId(productId);
                    products.setIsEnabled(productJaxb.getIsEnabled());
                    products.setDisplayInWebShop(productJaxb.getDisplayInWebShop());
                    products.setProductTypes(new ProductTypes(productJaxb.getProductTypeID()));
                    products.setProductCategories(new ProductCategories(productJaxb.getProductCategoryID()));
                    products.setIsDownloadProduct(productJaxb.getIsDownloadProduct());
                    products.setExtendedMembershipRequired(productJaxb.getExtendedMembershipRequired());
                    products.setUserId(sessions2.getUserId());
                    products.setCreatedDate(currentDateTime.getTime());
                    products.setUpdatedDate(currentDateTime.getTime());

                    ProductsInGroups productsInGroups = new ProductsInGroups();
                    productsInGroups.setId(new ProductsInGroupsId(productId, productJaxb.getGroupID()));
                    productsInGroups.setLastUpdatedByUserId(sessions2.getUserId());
                    productsInGroups.setCreatedDate(currentDateTime.getTime());
                    productsInGroups.setUpdatedDate(currentDateTime.getTime());
//                    productsInGroupsSet.add(productsInGroups);
                    products.getProductsInGroupses().add(productsInGroups);

                    CountryDAO countryDAO = new CountryDAO();
                    List<Country> countries = countryDAO.getAllCountries(session);
                    for (Country country : countries) {
                        ProductTranslations productTrans = new ProductTranslations();
                        productTrans.setId(new ProductTranslationsId(productId, country.getCountryId()));
                        productTrans.setProductName(productJaxb.getProductTranslations().get(0).getProductName());
                        productTrans.setUserId(sessions2.getUserId());
                        productTrans.setCreatedDate(currentDateTime.getTime());
                        productTrans.setUpdatedDate(currentDateTime.getTime());
                        products.getProductTranslationses().add(productTrans);
                    }
//                    products.setProductTranslationses(productTransSet);
                    session.saveOrUpdate(products);
                    tx.commit();
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured while Adding");
            return tingcoProducts;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts getProductVariantsForProductCounter(String deviceId, int countryId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRODUCTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRODUCTS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ReportDAO reportDAO = new ReportDAO();
                    List<ObjectProductVariantCounters> opvcList = reportDAO.getObjectProductVariantCountersByObjectID(deviceId, session);
                    if (opvcList.isEmpty()) {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("No Data Found");
                        return tingcoProducts;
                    } else {
                        List<String> productVariantIDs = new ArrayList<String>();
                        for (ObjectProductVariantCounters opvc : opvcList) {
                            productVariantIDs.add(opvc.getId().getProductVariantId());
                        }
                        tingcoProducts = tingcoProductsXML.buildGetProductVariantsForDeviceReport(tingcoProducts, productsDAO.getProductVariantTranslations(session, productVariantIDs, countryId));
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts CheckUserKeyExistForDevice(String deviceId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.ORDERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.ORDERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Device device = deviceDAO.getDeviceById(deviceId, session);
                    if (device == null) {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("DeviceID is not valid");
                        return tingcoProducts;
                    }
                    GroupDefaultUserAlias groupDefaultUserAlias = groupDAO.getGroupDefaultUserAliasByGroupId(session, device.getGroups().getGroupId());
                    if (groupDefaultUserAlias == null) {
                        tingcoProducts.getMsgStatus().setResponseCode(-2);
                        tingcoProducts.getMsgStatus().setResponseText("The Organization for this Device is missing default User key information. Please contact your supervisor");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts addManualPurchaseTim(String deviceId, String productVaraintId, String quantity, int countryId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.ORDERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.ORDERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    GregorianCalendar gc = new GregorianCalendar();
                    session = HibernateUtil.getSessionFactory().openSession();

//                    se.info24.productsjaxb.TingcoProducts tingcoProductsPost = (se.info24.productsjaxb.TingcoProducts) JAXBManager.getInstance().unMarshall(content, se.info24.productsjaxb.TingcoProducts.class);
//                    se.info24.productsjaxb.TransactionProducts tpPost = tingcoProductsPost.getTransactions().getTransactionProducts().get(0);
//                    se.info24.productsjaxb.TransactionResult trPost = tingcoProductsPost.getTransactions().getTransactionResult().get(0);
                    ProductVariantTranslations pvt = productsDAO.getProductVariantTranslationsByIds(productVaraintId, countryId, session);
                    Device device = deviceDAO.getDeviceById(deviceId, session);
                    if (device == null) {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Device Not Found");
                        return tingcoProducts;
                    }
                    Country c = (Country) session.getNamedQuery("getCountryById").setInteger("countryID", countryId).list().get(0);
                    Object object = productsDAO.getObjectProductVariantPricesByID(deviceId, productVaraintId, c.getCurrency().getCurrencyId(), session);

                    ObjectProductVariantPrices obvp = null;
                    if (object != null) {
                        obvp = (ObjectProductVariantPrices) object;
                    }

                    GroupDefaultUserAlias groupDefaultUserAlias = groupDAO.getGroupDefaultUserAliasByGroupId(session, device.getGroups().getGroupId());
                    if (groupDefaultUserAlias == null) {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("The Organization for this Device is missing default User key information. Please contact your supervisor");
                        return tingcoProducts;
                    }

                    UserAlias userAlias = (UserAlias) userDAO.getUserAliasDetailsByUserAliasID(session, groupDefaultUserAlias.getUserAliasId());

                    BigDecimal pricePerUnit = null;
                    ObjectProductVariantPrices objectProductVariantPrices = null;

                    object = productsDAO.getObjectProductVariantPricesByID(device.getDeviceId(), productVaraintId, countryId, session);
                    if (object != null) {
                        objectProductVariantPrices = (ObjectProductVariantPrices) object;
                    } else {
                        Country country = (Country) session.getNamedQuery("getCountryById").setInteger("countryID", countryId).list().get(0);
                        object = productsDAO.getObjectProductVariantPricesByID(device.getGroups().getGroupId(), productVaraintId, country.getCurrency().getCurrencyId(), session);
                        if (object != null) {
                            objectProductVariantPrices = (ObjectProductVariantPrices) object;
                        }
                    }
                    if (objectProductVariantPrices != null) {
                        pricePerUnit = objectProductVariantPrices.getPricePerUnitExclVat();
                    } else {
                        if (device.getAddresses() != null) {
                            ProductVariantPrices productVariantPrices = productsDAO.getProductVariantPricesByProductVariantIdAndCountryId(session, productVaraintId, device.getAddresses().getCountry().getCountryId());
                            if (productVariantPrices != null) {
                                pricePerUnit = productVariantPrices.getPricePerUnitExclVat();
                            }
                        }
                    }

                    if (pricePerUnit == null) {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("PPU is not found");
                        return tingcoProducts;
                    }
                    Set<String> providerSet = new HashSet<String>();

                    providerSet.add("8AD63812-E1AD-4327-A3C5-BB47E4EE302E");
                    List<Providers> providerses = productsDAO.getProvidersByProviderIDs(providerSet, session, 1);
                    if (providerses.isEmpty()) {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("ProviderID is not found in Providers");
                        return tingcoProducts;
                    }
                    ProductVariantDevices productVariantDevices = deviceDAO.getProductVariantDevices(deviceId, productVaraintId, session);
                    ProductVariants productVariants = productsDAO.getProductVariants(productVaraintId, session);

                    ObjectUsageRecords objectUsageRecords = new ObjectUsageRecords();
                    objectUsageRecords.setUsageRecordId(UUID.randomUUID().toString());
                    objectUsageRecords.setUsageTypeId("8c123cc7-57a8-4a2a-946c-ba9c2ad5bc37");
                    objectUsageRecords.setServiceId(TingcoConstants.getServiceID());
                    objectUsageRecords.setObjectId(deviceId);
                    objectUsageRecords.setObjectTypeId(device.getDeviceTypes().getDeviceTypeId());
                    objectUsageRecords.setObjectName(device.getDeviceName());
                    objectUsageRecords.setObjectDescription(device.getDeviceDescription());
                    objectUsageRecords.setUsageDescription("Manual Purchase");
                    objectUsageRecords.setUsageStartTime(gc.getTime());
                    objectUsageRecords.setUsageStopTime(gc.getTime());

                    objectUsageRecords.setDataItemName(pvt.getProductVariantName());
                    objectUsageRecords.setGroupId(device.getGroups().getGroupId());
                    if (device.getAgreements() != null) {
                        objectUsageRecords.setAgreementId(device.getAgreements().getAgreementId());
                    }
                    objectUsageRecords.setUsageVolume(quantity);
                    objectUsageRecords.setUsageUnitId("862c0047-17df-4c5a-9a05-9610c700c489");
                    if (obvp != null) {
                        objectUsageRecords.setUsageUnitName(obvp.getQuantityUnit());

                    }

                    if (userAlias.getUserId() != null) {
                        objectUsageRecords.setUsedByUserId(userAlias.getUserId());
                    }
                    objectUsageRecords.setUsedByUserName(userAlias.getFirstName() + " " + userAlias.getLastName());
                    objectUsageRecords.setUsedByUserAlias(userAlias.getUserAlias());
                    objectUsageRecords.setLastUpdatedByUserId(sessions2.getUserId());
                    objectUsageRecords.setCreatedDate(gc.getTime());
                    objectUsageRecords.setUpdatedDate(gc.getTime());

                    TransactionResult transactionResult = new TransactionResult();
                    TransactionProducts transactionProducts = new TransactionProducts();
                    String transactionID = UUID.randomUUID().toString();
                    transactionResult.setTransactionId(transactionID);
                    transactionResult.setTransactionParentId(null);
                    transactionResult.setTransactionStartTime(gc.getTime());
                    transactionResult.setDataItemId(UUID.randomUUID().toString());
                    transactionResult.setExternalTransactionId(null);

                    Double amount = pricePerUnit.doubleValue() * Double.parseDouble(quantity);
                    transactionResult.setAmount(new BigDecimal(String.valueOf(amount)));
                    if (device.getAddresses() != null) {
                        transactionResult.setCurrencyId(device.getAddresses().getCountry().getCurrency().getCurrencyId());

                    }
                    transactionResult.setUserAliasId(userAlias.getUserAliasId());
                    transactionResult.setUserAlias(userAlias.getUserAlias());

                    if (userAlias.getUserId() != null) {
                        transactionResult.setUserId(userAlias.getUserId());
                    }
                    transactionResult.setFirstName(userAlias.getFirstName());
                    transactionResult.setLastName(userAlias.getLastName());

                    if (userAlias.getGroupId() != null) {
                        transactionResult.setUserGroupId(userAlias.getGroupId());
                    }
                    transactionResult.setUserAliasTypeId(userAlias != null ? userAlias.getUserAliasTypes().getUserAliasTypeId() : null);
                    transactionResult.setUserAliasTypeName(userAlias != null ? userAlias.getUserAliasTypes().getUserAliasTypeName() : null);
                    transactionResult.setProviderId("8AD63812-E1AD-4327-A3C5-BB47E4EE302E");

                    transactionResult.setProviderName(providerses.get(0).getProviderName());
                    transactionResult.setDeviceId(deviceId);
                    transactionResult.setDeviceGroupId(device.getGroups().getGroupId());
                    transactionResult.setDeviceName(device.getDeviceName());
                    transactionResult.setTransactionPurpose("Manual Purchase");
                    transactionResult.setIsPurchase(1);
                    transactionResult.setIsPayment(0);
                    transactionResult.setIsInvoiced(0);
                    transactionResult.setIsPaid(0);
                    transactionResult.setLastUpdatedByUserId(sessions2.getUserId());
                    transactionResult.setCreatedDate(gc.getTime());
                    transactionResult.setUpdatedDate(gc.getTime());

                    transactionProducts.setId(new TransactionProductsId(transactionID, productVaraintId));
                    transactionProducts.setProductVariantSku(productVariants.getProductVariantSKU());

                    transactionProducts.setProductVariantName(pvt.getProductVariantName());
                    transactionProducts.setType(null);
                    transactionProducts.setChargeType("955A50C2-8A6E-440A-B446-8823933FB7AD");
                    transactionProducts.setTransactionType(null);
                    transactionProducts.setPpu((int) (pricePerUnit.floatValue() * 100));

                    transactionProducts.setQuantity(Double.valueOf(quantity).intValue());
                    if (obvp != null) {
                        transactionProducts.setQuantityUnit(obvp.getQuantityUnit());
                        transactionProducts.setVat(obvp.getVatpercent());
                    }
                    transactionProducts.setBayNumber(productVariantDevices != null ? productVariantDevices.getBayNumber() : 1);
                    transactionProducts.setLastUpdatedByUserId(sessions2.getUserId());
                    transactionProducts.setCreatedDate(gc.getTime());
                    transactionProducts.setUpdateDate(gc.getTime());
                    List<MeasurementData> mdList = new ArrayList<MeasurementData>();
                    MeasurementData measurementData = new MeasurementData();
                    measurementData.setId(new MeasurementDataId(UUID.randomUUID().toString(), "7408B1EA-051F-466A-A03C-39971A0532A5"));
                    measurementData.setDataItemTime(gc.getTime());
                    measurementData.setObjectId(deviceId);
                    measurementData.setGroupId(device.getGroups().getGroupId());
                    measurementData.setMeasurementValue(new BigDecimal(quantity));
                    java.text.DateFormat yyyy = new SimpleDateFormat("yyyy");
                    java.text.DateFormat mm = new SimpleDateFormat("MM");
                    java.text.DateFormat dd = new SimpleDateFormat("dd");
                    java.text.DateFormat hh = new SimpleDateFormat("HH");
                    measurementData.setYear(Short.parseShort(yyyy.format(gc.getTime())));
                    measurementData.setDay(Short.parseShort(dd.format(gc.getTime())));
                    measurementData.setMonth(Short.parseShort(mm.format(gc.getTime())));
                    measurementData.setHour(Short.parseShort(hh.format(gc.getTime())));
                    measurementData.setCreatedDate(gc.getTime());
                    measurementData.setUpdatedDate(gc.getTime());
                    mdList.add(measurementData);
                    //
                    measurementData = new MeasurementData();
                    measurementData.setId(new MeasurementDataId(UUID.randomUUID().toString(), "D269448A-0593-45DF-8B51-8C4249402365"));
                    measurementData.setDataItemTime(gc.getTime());
                    measurementData.setObjectId(deviceId);
                    measurementData.setGroupId(device.getGroups().getGroupId());

                    measurementData.setMeasurementValue(new BigDecimal(String.valueOf(amount)));
                    measurementData.setYear(Short.parseShort(yyyy.format(gc.getTime())));
                    measurementData.setDay(Short.parseShort(dd.format(gc.getTime())));
                    measurementData.setMonth(Short.parseShort(mm.format(gc.getTime())));
                    measurementData.setHour(Short.parseShort(hh.format(gc.getTime())));
                    measurementData.setCreatedDate(gc.getTime());
                    measurementData.setUpdatedDate(gc.getTime());
                    mdList.add(measurementData);
                    if (productsDAO.addIsmOprationsData(objectUsageRecords, transactionProducts, transactionResult, mdList)) {
                        tingcoProducts.getMsgStatus().setResponseCode(0);
                        tingcoProducts.getMsgStatus().setResponseText("Added");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }

            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts getProductVariantsForDeviceReport(String groupId, int countryId, String searchString) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
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
                    if (searchString.equals("")) {
                        searchString = null;
                    } else {
                        searchString = searchString.split("/")[2];
                    }
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ProductsInGroups> productsInGroupses = productsDAO.getProductsInGroupsByGroupId(session, groupId);
                    if (productsInGroupses.isEmpty()) {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoProducts;
                    }
                    Set<String> productidList = new HashSet<String>();
                    for (ProductsInGroups productsInGroups : productsInGroupses) {
                        productidList.add(productsInGroups.getId().getProductId());
                    }
                    List<ProductVariants> productVariantses = productsDAO.getProductVariantsByProductIdsList(productidList, null, session);
                    if (productVariantses.isEmpty()) {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoProducts;
                    }
                    List<String> productVariantIdList = new ArrayList<String>();

                    for (ProductVariants productVariants : productVariantses) {
                        productVariantIdList.add(productVariants.getProductVariantId());
                    }
                    List<ProductVariantTranslations> productVariantTranslationses = new ArrayList<ProductVariantTranslations>();
                    if (searchString != null) {
                        productVariantTranslationses = productsDAO.getProductVariantTranslationsBySearchString(session, productVariantIdList, countryId, searchString, 200);
                    } else {
                        productVariantTranslationses = productsDAO.getProductVariantTranslationsorderByCreatedDate(session, productVariantIdList, countryId);
                        if (productVariantTranslationses.isEmpty()) {
                            tingcoProducts.getMsgStatus().setResponseCode(-1);
                            tingcoProducts.getMsgStatus().setResponseText("Data Not Found");
                            return tingcoProducts;
                        }
                        productVariantIdList.clear();
                        for (ProductVariantTranslations productVariantTranslations : productVariantTranslationses) {
                            productVariantIdList.add(productVariantTranslations.getId().getProductVariantId());
                        }
                        productVariantTranslationses = productsDAO.getProductVariantTranslations(session, productVariantIdList, countryId, "200");
                    }
                    if (productVariantTranslationses.isEmpty()) {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoProducts;
                    }
                    return tingcoProductsXML.buildGetProductVariantsForDeviceReport(tingcoProducts, productVariantTranslationses);
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts addManualPurchase(String content) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.ORDERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.ORDERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    GregorianCalendar gc = new GregorianCalendar();
                    session = HibernateUtil.getSessionFactory().openSession();

                    se.info24.productsjaxb.TingcoProducts tingcoProductsPost = (se.info24.productsjaxb.TingcoProducts) JAXBManager.getInstance().unMarshall(content, se.info24.productsjaxb.TingcoProducts.class);
                    se.info24.productsjaxb.TransactionProducts tpPost = tingcoProductsPost.getTransactions().getTransactionProducts().get(0);
                    se.info24.productsjaxb.TransactionResult trPost = tingcoProductsPost.getTransactions().getTransactionResult().get(0);

                    ProductVariantTranslations pvt = productsDAO.getProductVariantTranslationsByIds(tpPost.getProductVariantID(), Integer.parseInt(tpPost.getCountryID()), session);
                    Device device = deviceDAO.getDeviceById(trPost.getDeviceID(), session);
                    if (device == null) {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Device Not Found");
                        return tingcoProducts;
                    }
                    Object object = productsDAO.getObjectProductVariantPricesByID(trPost.getDeviceID(), tpPost.getProductVariantID(), trPost.getCurrencyID(), session);

                    ObjectProductVariantPrices obvp = null;
                    if (object != null) {
                        obvp = (ObjectProductVariantPrices) object;
                    }
                    UserAlias userAlias = productsDAO.getUserAliasByUserAlias(session, trPost.getUserAlias());
                    Set<String> providerSet = new HashSet<String>();
                    providerSet.add(trPost.getProviderID());
                    List<Providers> providerses = productsDAO.getProvidersByProviderIDs(providerSet, session, 1);
                    ProductVariantDevices productVariantDevices = deviceDAO.getProductVariantDevices(trPost.getDeviceID(), tpPost.getProductVariantID(), session);
                    ProductVariants productVariants = productsDAO.getProductVariants(tpPost.getProductVariantID(), session);

                    ObjectUsageRecords objectUsageRecords = new ObjectUsageRecords();
                    objectUsageRecords.setUsageRecordId(UUID.randomUUID().toString());
                    objectUsageRecords.setUsageTypeId("8c123cc7-57a8-4a2a-946c-ba9c2ad5bc37");
                    objectUsageRecords.setServiceId(TingcoConstants.getServiceID());
                    objectUsageRecords.setObjectId(trPost.getDeviceID());
                    objectUsageRecords.setObjectTypeId(device.getDeviceTypes().getDeviceTypeId());
                    objectUsageRecords.setObjectName(device.getDeviceName());
                    objectUsageRecords.setObjectDescription(device.getDeviceDescription());
                    objectUsageRecords.setUsageDescription("Manual Purchase");
                    objectUsageRecords.setUsageStartTime(gc.getTime());
                    objectUsageRecords.setUsageStopTime(gc.getTime());

                    objectUsageRecords.setDataItemName(pvt.getProductVariantName());
                    objectUsageRecords.setGroupId(device.getGroups().getGroupId());
                    if (device.getAgreements() != null) {
                        objectUsageRecords.setAgreementId(device.getAgreements().getAgreementId());
                    }
                    objectUsageRecords.setUsageVolume(tpPost.getQuantity() + "");
                    objectUsageRecords.setUsageUnitId("862c0047-17df-4c5a-9a05-9610c700c489");
                    if (obvp != null) {
                        objectUsageRecords.setUsageUnitName(obvp.getQuantityUnit());

                    }

                    if (userAlias.getUserId() != null) {
                        objectUsageRecords.setUsedByUserId(userAlias.getUserId());
                    }
                    objectUsageRecords.setUsedByUserName(userAlias.getFirstName() + " " + userAlias.getLastName());
                    objectUsageRecords.setUsedByUserAlias(trPost.getUserAlias());
                    objectUsageRecords.setLastUpdatedByUserId(sessions2.getUserId());
                    objectUsageRecords.setCreatedDate(gc.getTime());
                    objectUsageRecords.setUpdatedDate(gc.getTime());

                    TransactionResult transactionResult = new TransactionResult();
                    TransactionProducts transactionProducts = new TransactionProducts();
                    String transactionID = UUID.randomUUID().toString();
                    transactionResult.setTransactionId(transactionID);
                    transactionResult.setTransactionParentId(null);
                    transactionResult.setTransactionStartTime(gc.getTime());
                    transactionResult.setDataItemId(UUID.randomUUID().toString());
                    transactionResult.setExternalTransactionId(null);
                    transactionResult.setAmount(trPost.getAmount());
                    if (device.getAddresses() != null) {
                        transactionResult.setCurrencyId(device.getAddresses().getCountry().getCurrency().getCurrencyId());

                    }
                    transactionResult.setUserAliasId(userAlias.getUserAliasId());
                    transactionResult.setUserAlias(trPost.getUserAlias());

                    if (userAlias.getUserId() != null) {
                        transactionResult.setUserId(userAlias.getUserId());
                    }
                    transactionResult.setFirstName(userAlias.getFirstName());
                    transactionResult.setLastName(userAlias.getLastName());

                    if (userAlias.getGroupId() != null) {
                        transactionResult.setUserGroupId(userAlias.getGroupId());
                    }
                    transactionResult.setUserAliasTypeId(userAlias != null ? userAlias.getUserAliasTypes().getUserAliasTypeId() : null);
                    transactionResult.setUserAliasTypeName(userAlias != null ? userAlias.getUserAliasTypes().getUserAliasTypeName() : null);
                    transactionResult.setProviderId(trPost.getProviderID());

                    transactionResult.setProviderName(providerses.get(0).getProviderName());
                    transactionResult.setDeviceId(trPost.getDeviceID());
                    transactionResult.setDeviceGroupId(device.getGroups().getGroupId());
                    transactionResult.setDeviceName(device.getDeviceName());
                    transactionResult.setTransactionPurpose("Manual Purchase");
                    transactionResult.setIsPurchase(1);
                    transactionResult.setIsPayment(0);
                    transactionResult.setIsInvoiced(0);
                    transactionResult.setIsPaid(0);
                    transactionResult.setLastUpdatedByUserId(sessions2.getUserId());
                    transactionResult.setCreatedDate(gc.getTime());
                    transactionResult.setUpdatedDate(gc.getTime());

                    transactionProducts.setId(new TransactionProductsId(transactionID, tpPost.getProductVariantID()));
                    transactionProducts.setProductVariantSku(productVariants.getProductVariantSKU());

                    transactionProducts.setProductVariantName(pvt.getProductVariantName());
                    transactionProducts.setType(null);
                    transactionProducts.setChargeType("955A50C2-8A6E-440A-B446-8823933FB7AD");
                    transactionProducts.setTransactionType(null);
                    if (tpPost.getPPU().contains(".")) {
                        Float f = (Float.valueOf(tpPost.getPPU()) * 100);
                        transactionProducts.setPpu(f.intValue());
                    } else {
                        transactionProducts.setPpu(Integer.valueOf(tpPost.getPPU()) * 100);
                    }

                    transactionProducts.setQuantity(tpPost.getQuantity());
                    if (obvp != null) {
                        transactionProducts.setQuantityUnit(obvp.getQuantityUnit());
                        transactionProducts.setVat(obvp.getVatpercent());
                    }
                    transactionProducts.setBayNumber(productVariantDevices != null ? productVariantDevices.getBayNumber() : 1);
                    transactionProducts.setLastUpdatedByUserId(sessions2.getUserId());
                    transactionProducts.setCreatedDate(gc.getTime());
                    transactionProducts.setUpdateDate(gc.getTime());
                    List<MeasurementData> mdList = new ArrayList<MeasurementData>();
                    MeasurementData measurementData = new MeasurementData();
                    measurementData.setId(new MeasurementDataId(UUID.randomUUID().toString(), "7408B1EA-051F-466A-A03C-39971A0532A5"));
                    measurementData.setDataItemTime(gc.getTime());
                    measurementData.setObjectId(trPost.getDeviceID());
                    measurementData.setGroupId(device.getGroups().getGroupId());
                    measurementData.setMeasurementValue(new BigDecimal(tpPost.getQuantity()));
                    java.text.DateFormat yyyy = new SimpleDateFormat("yyyy");
                    java.text.DateFormat mm = new SimpleDateFormat("MM");
                    java.text.DateFormat dd = new SimpleDateFormat("dd");
                    java.text.DateFormat hh = new SimpleDateFormat("HH");
                    measurementData.setYear(Short.parseShort(yyyy.format(gc.getTime())));
                    measurementData.setDay(Short.parseShort(dd.format(gc.getTime())));
                    measurementData.setMonth(Short.parseShort(mm.format(gc.getTime())));
                    measurementData.setHour(Short.parseShort(hh.format(gc.getTime())));
                    measurementData.setCreatedDate(gc.getTime());
                    measurementData.setUpdatedDate(gc.getTime());
                    mdList.add(measurementData);
                    //
                    measurementData = new MeasurementData();
                    measurementData.setId(new MeasurementDataId(UUID.randomUUID().toString(), "D269448A-0593-45DF-8B51-8C4249402365"));
                    measurementData.setDataItemTime(gc.getTime());
                    measurementData.setObjectId(trPost.getDeviceID());
                    measurementData.setGroupId(device.getGroups().getGroupId());
                    measurementData.setMeasurementValue(trPost.getAmount());
                    measurementData.setYear(Short.parseShort(yyyy.format(gc.getTime())));
                    measurementData.setDay(Short.parseShort(dd.format(gc.getTime())));
                    measurementData.setMonth(Short.parseShort(mm.format(gc.getTime())));
                    measurementData.setHour(Short.parseShort(hh.format(gc.getTime())));
                    measurementData.setCreatedDate(gc.getTime());
                    measurementData.setUpdatedDate(gc.getTime());
                    mdList.add(measurementData);
                    if (productsDAO.addIsmOprationsData(objectUsageRecords, transactionProducts, transactionResult, mdList)) {
                        tingcoProducts.getMsgStatus().setResponseCode(0);
                        tingcoProducts.getMsgStatus().setResponseText("Added");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts getDeviceProductVariantsWithLimit(String deviceId, int countryId, String limit) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
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
                    if (limit.equals("")) {
                        limit = null;
                    } else {
                        limit = limit.split("/")[2];
                    }
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<se.info24.pojo.ProductVariantDevices> productVariantDevicese = productsDAO.getProductVariantDevicesByDeviceId(session, deviceId);
                    Set<String> productVariantidSet = new HashSet<String>();
                    if (!productVariantDevicese.isEmpty()) {
                        for (se.info24.pojo.ProductVariantDevices productVariantDevices : productVariantDevicese) {
                            productVariantidSet.add(productVariantDevices.getId().getProductVariantId());
                        }
                    }
                    if (productVariantidSet.isEmpty()) {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Data not Found");
                        return tingcoProducts;
                    }
                    List<String> productVariantidList = new ArrayList<String>(productVariantidSet);
                    List<ProductVariantTranslations> productVariantTransList = productsDAO.getProductVariantTranslations(session, productVariantidList, countryId, limit);
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        return tingcoProductsXML.buildDeviceEventTrigger(tingcoProducts, productVariantidList, productVariantDevicese, productVariantTransList, timeZoneGMToffset);
                    } else {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts getProductVariantsByGroupIdWithLimit(String groupId, int countryId, String limit) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("groupId should not be empty");
                    return tingcoProducts;
                }
                if (countryId <= 0) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("countryId should not be empty");
                    return tingcoProducts;
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
                    if (limit.equals("")) {
                        limit = null;
                    } else {
                        limit = limit.split("/")[2];
                    }

                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ProductsInGroups> productsInGroupsList = productsDAO.getProductsInGroupsByGroupId(session, groupId);
                    if (!productsInGroupsList.isEmpty()) {
                        Set<String> productIdList = productsDAO.getProductIdList(productsInGroupsList);
                        List<ProductVariants> productVariantsList = productsDAO.getProductVariantsByProductIdsList(productIdList, limit, session);
                        if (!productVariantsList.isEmpty()) {
                            List<String> productVariantIdList = productsDAO.getProductVariantIdList(productVariantsList);
                            List<ProductVariantTranslations> pvtList = productsDAO.getProductVariantTranslations(session, productVariantIdList, countryId);
                            if (!pvtList.isEmpty()) {
                                tingcoProducts = tingcoProductsXML.buildTingcoProductVariants(tingcoProducts, productVariantsList, pvtList);
                                return tingcoProducts;
                            } else {
                                tingcoProducts.getMsgStatus().setResponseCode(-1);
                                tingcoProducts.getMsgStatus().setResponseText("Data is not Found");
                                return tingcoProducts;
                            }
                        } else {
                            tingcoProducts.getMsgStatus().setResponseCode(-1);
                            tingcoProducts.getMsgStatus().setResponseText("No Record Found in ProductVariants");
                            return tingcoProducts;
                        }
                    } else {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("No Record Found for the given GroupId");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }

            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts getPaymentProviders(String groupId, String searchString) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.ORDERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.ORDERS);
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
                    List<ProvidersInGroups> providersInGroupses = productsDAO.getProvidersInGroupsByGroupID(groupId, session);
                    if (!providersInGroupses.isEmpty()) {
                        Set<String> providerIDs = new HashSet<String>();
                        for (ProvidersInGroups pig : providersInGroupses) {
                            providerIDs.add(pig.getId().getProviderId());
                        }
                        List<Providers> providerses = new ArrayList<Providers>();
                        if (searchString != null) {
                            if (TCMUtil.isValidUUID(searchString)) {
                                boolean flag = false;
                                for (String providerID : providerIDs) {
                                    if (providerID.equalsIgnoreCase(searchString)) {
                                        flag = true;
                                        Set<String> providerIDFilter = new HashSet<String>();
                                        providerIDFilter.add(providerID);
                                        providerses = productsDAO.getProvidersByProviderIDs(providerIDFilter, session, 200);
                                    }
                                }
                                if (!flag) {
                                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                                    tingcoProducts.getMsgStatus().setResponseText("Data Not Found");
                                    return tingcoProducts;
                                }
                            } else {
                                providerses = productsDAO.getProvidersByProviderIDsAndName(providerIDs, searchString, session, 200);
                            }
                        } else {
                            providerses = productsDAO.getProvidersByProviderIDs(providerIDs, session, 200);
                        }
                        tingcoProducts = tingcoProductsXML.buildGetPaymentProviders(providerses, tingcoProducts);
                    } else {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured while deleting Data");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts getCurrencyForDeviceID(String deviceId) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.ORDERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.ORDERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    se.info24.pojo.Device device = deviceDAO.getDeviceById(deviceId, session);
                    if (device != null) {
                        if (device.getAddresses() != null) {
                            tingcoProducts = tingcoProductsXML.buildGetCurrencyForDeviceID(device, tingcoProducts);
                        } else {
                            tingcoProducts.getMsgStatus().setResponseCode(-1);
                            tingcoProducts.getMsgStatus().setResponseText("Currency Not Found");
                            return tingcoProducts;
                        }
                    } else {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured while deleting Data");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts getProductVariantPrice(String deviceId, String productvariantId, Integer currencyid) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.ORDERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.ORDERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    se.info24.pojo.Device device = deviceDAO.getDeviceById(deviceId, session);
                    BigDecimal pricePerUnit = null;
                    ObjectProductVariantPrices objectProductVariantPrices = null;
                    Object object = productsDAO.getObjectProductVariantPricesByID(deviceId, productvariantId, currencyid, session);
                    if (object != null) {
                        objectProductVariantPrices = (ObjectProductVariantPrices) object;
                    } else {
                        object = productsDAO.getObjectProductVariantPricesByID(device.getGroups().getGroupId(), productvariantId, currencyid, session);
                        if (object != null) {
                            objectProductVariantPrices = (ObjectProductVariantPrices) object;
                        }
                    }
                    if (objectProductVariantPrices != null) {
                        pricePerUnit = objectProductVariantPrices.getPricePerUnitExclVat();
                    } else {
                        if (device.getAddresses() != null) {
                            ProductVariantPrices productVariantPrices = productsDAO.getProductVariantPricesByProductVariantIdAndCountryId(session, productvariantId, device.getAddresses().getCountry().getCountryId());
                            if (productVariantPrices == null) {
                                tingcoProducts.getMsgStatus().setResponseCode(-1);
                                tingcoProducts.getMsgStatus().setResponseText("Data Not Found");
                                return tingcoProducts;
                            }
                            pricePerUnit = productVariantPrices.getPricePerUnitExclVat();
                        }
                    }
                    tingcoProducts = tingcoProductsXML.buildGetProductVariantPrice(pricePerUnit, tingcoProducts);
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts deleteObjectProductPrice(String objectId, String productvariantId, String objectType, Integer currencyid) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(objectType)) {
                    ArrayList<String> operations = ht.get(objectType);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object object = productsDAO.getObjectProductVariantPricesByID(objectId, productvariantId, currencyid, session);
                    if (object != null) {
                        ObjectProductVariantPrices objectProductVariantPrices = (ObjectProductVariantPrices) object;
                        productsDAO.deleteObjectProductVariantPrices(objectProductVariantPrices, session);
                    } else {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured while deleting Data");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts updateObjectProductPrice(String objectId, String productvariantId, String objectType, Integer currencyid, String pricePerUnitExclvat, String vatPercent, String quantityUnit) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(objectType)) {
                    ArrayList<String> operations = ht.get(objectType);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object object = productsDAO.getObjectProductVariantPricesByID(objectId, productvariantId, currencyid, session);
                    if (object != null) {
                        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                        ObjectProductVariantPrices objectProductVariantPrices = (ObjectProductVariantPrices) object;
                        objectProductVariantPrices.setLastUpdatedByUserId(sessions2.getUserId());
                        objectProductVariantPrices.setUpdatedDate(gc.getTime());
                        objectProductVariantPrices.setPricePerUnitExclVat(new BigDecimal(pricePerUnitExclvat));
                        objectProductVariantPrices.setVatpercent(new BigDecimal(vatPercent));
                        objectProductVariantPrices.setQuantityUnit(quantityUnit);
                        productsDAO.saveUpdateObjectProductVariantPrices(objectProductVariantPrices, session);
                    } else {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured while deleting Data");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts addObjectProductPrice(String objectId, String productvariantId, String objectType, Integer countryId, Integer currencyid, String pricePerUnitExclvat, String vatPercent, String quantityUnit) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(objectType)) {
                    ArrayList<String> operations = ht.get(objectType);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object object = productsDAO.getObjectProductVariantPricesByID(objectId, productvariantId, currencyid, session);
                    if (object == null) {
                        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                        ObjectProductVariantPrices objectProductVariantPrices = new ObjectProductVariantPrices();
                        objectProductVariantPrices.setId(new ObjectProductVariantPricesId(objectId, productvariantId, currencyid));
                        objectProductVariantPrices.setLastUpdatedByUserId(sessions2.getUserId());
                        objectProductVariantPrices.setCreatedDate(gc.getTime());
                        objectProductVariantPrices.setUpdatedDate(gc.getTime());
                        objectProductVariantPrices.setPricePerUnitExclVat(new BigDecimal(pricePerUnitExclvat));
                        objectProductVariantPrices.setVatpercent(new BigDecimal(vatPercent));
                        objectProductVariantPrices.setQuantityUnit(quantityUnit);
                        productsDAO.saveUpdateObjectProductVariantPrices(objectProductVariantPrices, session);
                    } else {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Data Exists");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured while deleting Data");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts getObjectProductPriceByProductVariantID(String objectId, String productvariantId, String objecttype, Integer countryId) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(objecttype)) {
                    ArrayList<String> operations = ht.get(objecttype);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ObjectProductVariantPrices> objectProductVariantPriceses = productsDAO.getObjectProductVariantPricesByObjectIDAndProductVariantID(objectId, productvariantId, session, 200);
                    if (!objectProductVariantPriceses.isEmpty()) {
                        List<String> productVariantIDs = new ArrayList<String>();
                        for (ObjectProductVariantPrices opvp : objectProductVariantPriceses) {
                            productVariantIDs.add(opvp.getId().getProductVariantId());
                        }
                        List<ProductVariantTranslations> pvtList = productsDAO.getProductVariantTranslations(session, productVariantIDs, countryId);
                        tingcoProducts = tingcoProductsXML.buildObjectProductPriceDetails(objectProductVariantPriceses, pvtList, tingcoProducts);
                    } else {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured while deleting Data");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts getObjectProductPriceDetails(String objectId, Integer countryId, String objecttype) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(objecttype)) {
                    ArrayList<String> operations = ht.get(objecttype);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ObjectProductVariantPrices> objectProductVariantPriceses = productsDAO.getObjectProductVariantPricesByObjectID(objectId, session, 200);
                    if (!objectProductVariantPriceses.isEmpty()) {
                        List<String> productVariantIDs = new ArrayList<String>();
                        for (ObjectProductVariantPrices opvp : objectProductVariantPriceses) {
                            productVariantIDs.add(opvp.getId().getProductVariantId());
                        }
                        List<ProductVariantTranslations> pvtList = productsDAO.getProductVariantTranslations(session, productVariantIDs, countryId);
                        tingcoProducts = tingcoProductsXML.buildObjectProductPriceDetails(objectProductVariantPriceses, pvtList, tingcoProducts);
                    } else {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured while deleting Data");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts addNewProductCounterForDevice(String deviceId, String productVaraintId, Integer counterValue, Integer registered) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        Session ismOprSession = null;
        long requestedTime = System.currentTimeMillis();
        Transaction tx = null;
        try {
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object object = productsDAO.getObjectProductVariantCountersByID(deviceId, productVaraintId, session);
                    ObjectProductVariantCounters objectProductVariantCounters = null;
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                    Integer countSinceLastUpdate = null;
                    if (object != null) {
                        objectProductVariantCounters = (ObjectProductVariantCounters) object;
                        Integer lastCount = objectProductVariantCounters.getCountTotal() - counterValue;
                        if (lastCount < 0) {
                            lastCount = lastCount * -1;
                        }
                        countSinceLastUpdate = lastCount;
                        objectProductVariantCounters.setCountSinceLastUpdate(lastCount);
                        objectProductVariantCounters.setCountTotal(counterValue);
                        objectProductVariantCounters.setLastUpdatedByUserId(sessions2.getUserId());
                        objectProductVariantCounters.setUpdatedDate(gc.getTime());
                    } else {
                        countSinceLastUpdate = counterValue;
                        objectProductVariantCounters = new ObjectProductVariantCounters();
                        objectProductVariantCounters.setId(new ObjectProductVariantCountersId(deviceId, productVaraintId));
                        objectProductVariantCounters.setCountSinceLastUpdate(counterValue);
                        objectProductVariantCounters.setCountTotal(counterValue);
                        objectProductVariantCounters.setLastUpdatedByUserId(sessions2.getUserId());
                        objectProductVariantCounters.setCreatedDate(gc.getTime());
                        objectProductVariantCounters.setUpdatedDate(gc.getTime());
                    }
                    se.info24.pojo.Device device = deviceDAO.getDeviceById(deviceId, session);
                    ProductVariants productVariants = productsDAO.getProductVariants(productVaraintId, session);
                    Users2 loggedInUser = userDAO.getUserById(sessions2.getUserId(), session);
                    ProductVariantTranslations productVariantTranslations = productsDAO.getProductVariantTranslationsByIds(productVaraintId, loggedInUser.getCountryId(), session);

                    GroupDefaultUserAlias groupDefaultUserAlias = groupDAO.getGroupDefaultUserAliasByGroupId(session, device.getGroups().getGroupId());
                    String userIdFromGroupDefaultUserAlias = null;
                    String nameFromGroupDefaultUserAlias = null;
                    UserAlias userAlias = null;
                    if (groupDefaultUserAlias != null && groupDefaultUserAlias.getUserAliasId() != null) {
                        userAlias = (UserAlias) userDAO.getUserAliasDetailsByUserAliasID(session, groupDefaultUserAlias.getUserAliasId());
                        if (userAlias != null && userAlias.getUserId() != null) {
                            userIdFromGroupDefaultUserAlias = userAlias.getUserId();
                            if (userAlias.getFirstName() != null) {
                                nameFromGroupDefaultUserAlias = userAlias.getFirstName();
                            }
                            if (userAlias.getLastName() != null) {
                                nameFromGroupDefaultUserAlias.concat(" " + userAlias.getLastName());
                            }
                        }
                    }
                    //
                    ObjectUsageRecords objectUsageRecords = new ObjectUsageRecords();
                    objectUsageRecords.setUsageRecordId(UUID.randomUUID().toString());
                    objectUsageRecords.setUsageTypeId("8c123cc7-57a8-4a2a-946c-ba9c2ad5bc37");
                    objectUsageRecords.setServiceId(TingcoConstants.getServiceID());
                    objectUsageRecords.setObjectId(deviceId);
                    objectUsageRecords.setObjectTypeId(device.getDeviceTypes().getDeviceTypeId());
                    objectUsageRecords.setObjectName(device.getDeviceName());
                    objectUsageRecords.setObjectDescription(device.getDeviceDescription());
                    objectUsageRecords.setUsageDescription("Device product counter update");
                    objectUsageRecords.setUsageStartTime(gc.getTime());
                    objectUsageRecords.setUsageStopTime(gc.getTime());
                    objectUsageRecords.setDataItemName(productVariantTranslations.getProductVariantName());
                    objectUsageRecords.setGroupId(device.getGroups().getGroupId());
                    if (device.getAgreements() != null) {
                        objectUsageRecords.setAgreementId(device.getAgreements().getAgreementId());
                    }
                    objectUsageRecords.setUsageVolume(countSinceLastUpdate + "");
                    objectUsageRecords.setUsageUnitId("862c0047-17df-4c5a-9a05-9610c700c489");
                    objectUsageRecords.setUsageUnitName("count");
                    objectUsageRecords.setUsedByUserId(userIdFromGroupDefaultUserAlias);
                    objectUsageRecords.setUsedByUserName(nameFromGroupDefaultUserAlias);
                    objectUsageRecords.setUsedByUserAlias(userAlias != null ? userAlias.getUserAlias() : null);
                    objectUsageRecords.setLastUpdatedByUserId(sessions2.getUserId());
                    objectUsageRecords.setCreatedDate(gc.getTime());
                    objectUsageRecords.setUpdatedDate(gc.getTime());
                    //
                    TransactionResult transactionResult = null;
                    TransactionProducts transactionProducts = null;
                    BigDecimal vatPercent = null;
                    BigDecimal pricePerUnit = null;
                    String quantity = null;
                    boolean saveTransResult = false;
                    Addresses addresses = null;
                    if (registered == 1) {
                        if (device.getAddresses() != null) {
                            ObjectProductVariantPrices objectProductVariantPrices = null;
                            Object opvpObject = null;
                            addresses = (Addresses) userDAO.getAddress(device.getAddresses().getAddressId(), session);
                            if (addresses != null && addresses.getCountry() != null) {
                                opvpObject = productsDAO.getObjectProductVariantPricesByID(deviceId, productVaraintId, addresses.getCountry().getCurrency().getCurrencyId(), session);
                                if (opvpObject != null) {
                                    objectProductVariantPrices = (ObjectProductVariantPrices) opvpObject;
                                    saveTransResult = true;
                                } else {
                                    opvpObject = productsDAO.getObjectProductVariantPricesByID(device.getGroups().getGroupId(), productVaraintId, addresses.getCountry().getCurrency().getCurrencyId(), session);
                                    if (opvpObject != null) {
                                        objectProductVariantPrices = (ObjectProductVariantPrices) opvpObject;
                                        saveTransResult = true;
                                    }
                                }
                                if (objectProductVariantPrices == null) {
                                    Object pvpObject = productsDAO.getProductVariantPricesByID(productVaraintId, addresses.getCountry().getCountryId(), session);
                                    if (pvpObject != null) {
                                        ProductVariantPrices productVariantPrices = (ProductVariantPrices) pvpObject;
                                        vatPercent = productVariantPrices.getVatpercent();
                                        pricePerUnit = productVariantPrices.getPricePerUnitExclVat();
                                        quantity = productVariantPrices.getQuantityUnit();
                                        saveTransResult = true;
                                    }
                                } else {
                                    vatPercent = objectProductVariantPrices.getVatpercent();
                                    pricePerUnit = objectProductVariantPrices.getPricePerUnitExclVat();
                                    quantity = objectProductVariantPrices.getQuantityUnit();
                                }
                            }
                        }
                    }
                    if (saveTransResult) {
                        transactionResult = new TransactionResult();
                        transactionProducts = new TransactionProducts();
                        String transactionID = UUID.randomUUID().toString();
                        transactionResult.setTransactionId(transactionID);
                        transactionResult.setTransactionParentId(null);
                        transactionResult.setTransactionStartTime(gc.getTime());
                        transactionResult.setDataItemId(UUID.randomUUID().toString());
                        transactionResult.setExternalTransactionId(null);
                        float amount = pricePerUnit.floatValue() * countSinceLastUpdate;
                        transactionResult.setAmount(new BigDecimal(amount));
                        transactionResult.setCurrencyId(addresses.getCountry().getCurrency().getCurrencyId());
                        transactionResult.setUserAliasId(userAlias != null ? userAlias.getUserAliasId() : null);
                        transactionResult.setUserAlias(userAlias != null ? userAlias.getUserAlias() : null);
                        transactionResult.setUserId(userIdFromGroupDefaultUserAlias);
                        transactionResult.setFirstName(userAlias != null ? userAlias.getFirstName() : null);
                        transactionResult.setLastName(userAlias != null ? userAlias.getLastName() : null);
                        transactionResult.setUserGroupId(userAlias != null ? userAlias.getGroupId() : null);
                        transactionResult.setUserAliasTypeId(userAlias != null ? userAlias.getUserAliasTypes().getUserAliasTypeId() : null);
                        transactionResult.setUserAliasTypeName(userAlias != null ? userAlias.getUserAliasTypes().getUserAliasTypeName() : null);
                        transactionResult.setProviderId("8AD63812-E1AD-4327-A3C5-BB47E4EE302E");
                        transactionResult.setProviderName("Generic invoice");
                        transactionResult.setDeviceId(deviceId);
                        transactionResult.setDeviceGroupId(device.getGroups().getGroupId());
                        transactionResult.setDeviceName(device.getDeviceName());
                        transactionResult.setTransactionPurpose("Product counter update");
                        transactionResult.setIsPurchase(1);
                        transactionResult.setIsPayment(0);
                        transactionResult.setIsInvoiced(0);
                        transactionResult.setIsPaid(0);
                        transactionResult.setLastUpdatedByUserId(sessions2.getUserId());
                        transactionResult.setCreatedDate(gc.getTime());
                        transactionResult.setUpdatedDate(gc.getTime());


                        transactionProducts.setId(new TransactionProductsId(transactionID, productVaraintId));
                        transactionProducts.setProductVariantSku(productVariants.getProductVariantSKU());
                        transactionProducts.setProductVariantName(productVariantTranslations.getProductVariantName());
                        transactionProducts.setType(null);
                        transactionProducts.setChargeType("955A50C2-8A6E-440A-B446-8823933FB7AD");
                        transactionProducts.setTransactionType(null);
                        int ppu = (int) (pricePerUnit.floatValue() * 100);
                        transactionProducts.setPpu(ppu);
                        transactionProducts.setQuantity(countSinceLastUpdate);
                        transactionProducts.setQuantityUnit(quantity);
                        transactionProducts.setVat(vatPercent);
                        ProductVariantDevices productVariantDevices = deviceDAO.getProductVariantDevices(deviceId, productVaraintId, session);
                        transactionProducts.setBayNumber(productVariantDevices != null ? productVariantDevices.getBayNumber() : null);
                        transactionProducts.setLastUpdatedByUserId(sessions2.getUserId());
                        transactionProducts.setCreatedDate(gc.getTime());
                        transactionProducts.setUpdateDate(gc.getTime());
                    }
                    List<MeasurementData> mdList = new ArrayList<MeasurementData>();
                    MeasurementData measurementData = new MeasurementData();
                    measurementData.setId(new MeasurementDataId(UUID.randomUUID().toString(), "7408B1EA-051F-466A-A03C-39971A0532A5"));
                    measurementData.setDataItemTime(gc.getTime());
                    measurementData.setObjectId(deviceId);
                    measurementData.setGroupId(device.getGroups().getGroupId());
                    measurementData.setMeasurementValue(new BigDecimal(countSinceLastUpdate));
                    java.text.DateFormat yyyy = new SimpleDateFormat("yyyy");
                    java.text.DateFormat mm = new SimpleDateFormat("MM");
                    java.text.DateFormat dd = new SimpleDateFormat("dd");
                    java.text.DateFormat hh = new SimpleDateFormat("HH");
                    measurementData.setYear(Short.parseShort(yyyy.format(gc.getTime())));
                    measurementData.setDay(Short.parseShort(dd.format(gc.getTime())));
                    measurementData.setMonth(Short.parseShort(mm.format(gc.getTime())));
                    measurementData.setHour(Short.parseShort(hh.format(gc.getTime())));
                    measurementData.setCreatedDate(gc.getTime());
                    measurementData.setUpdatedDate(gc.getTime());
                    mdList.add(measurementData);
                    //
                    measurementData = new MeasurementData();
                    measurementData.setId(new MeasurementDataId(UUID.randomUUID().toString(), "D269448A-0593-45DF-8B51-8C4249402365"));
                    measurementData.setDataItemTime(gc.getTime());
                    measurementData.setObjectId(deviceId);
                    measurementData.setGroupId(device.getGroups().getGroupId());
                    if (pricePerUnit != null) {
                        float amount = pricePerUnit.floatValue() * countSinceLastUpdate;
                        measurementData.setMeasurementValue(new BigDecimal(amount));
                    } else {
                        measurementData.setMeasurementValue(new BigDecimal(0));
                    }
                    measurementData.setYear(Short.parseShort(yyyy.format(gc.getTime())));
                    measurementData.setDay(Short.parseShort(dd.format(gc.getTime())));
                    measurementData.setMonth(Short.parseShort(mm.format(gc.getTime())));
                    measurementData.setHour(Short.parseShort(hh.format(gc.getTime())));
                    measurementData.setCreatedDate(gc.getTime());
                    measurementData.setUpdatedDate(gc.getTime());
                    mdList.add(measurementData);
                    if (productsDAO.addIsmOprationsData(objectUsageRecords, transactionProducts, transactionResult, mdList)) {
                        try {
                            tx = session.beginTransaction();
                            tx.begin();
                            session.saveOrUpdate(objectProductVariantCounters);
                            tx.commit();
                            session.close();
                        } catch (Exception e) {
                            if (tx != null) {
                                tx.rollback();
                            }
                        }
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured while Adding Data");
        } finally {
            delayLog(requestedTime);
            if (ismOprSession != null && ismOprSession.isOpen()) {
                ismOprSession.close();
            }
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    public TingcoProducts deleteProductVariant(String productVariantId) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (productVariantId.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("productVariantId should not be empty");
                    return tingcoProducts;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRODUCTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRODUCTS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<String> pvIds = new ArrayList<String>();
                    pvIds.add(productVariantId);
                    List<ProductVariants> list = productsDAO.getProductVariants(pvIds, session);
                    if (list.isEmpty()) {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Data Not Found with ProductVariantID");
                        return tingcoProducts;
                    } else {
                        ProductVariants productVariant = list.get(0);
                        List<ProductVariantTranslations> pvtList = productsDAO.getProductVariantTranslationsByProductVariantID(session, productVariantId);
                        List<ProductVariantPrices> pvpList = productsDAO.getProductVariantPricesByProductVariantId(session, productVariantId);
                        List<ProductVariantAttributes> pvaList = productsDAO.getProductVariantAttributesByProductVariantId(session, pvIds);
                        List<ProductVariantDevices> pvdList = productsDAO.getProductVariantDevicesByProductVariantId(session, productVariantId);
                        List<ProductVariantServices> pvsList = productsDAO.getProductVariantServices(productVariantId, session);
                        List<ProductVariantContentCategories> pvccList = productsDAO.getProductVariantContentCategories(productVariantId, session);
                        List<ProductVariantComponents> pvcList = productsDAO.getProductVariantComponentsByProductvariantId(session, productVariantId);
                        List<ProductVariantMeasurementTypes> pvmList = productsDAO.getProductVariantMeasurementTypesByProductvariantId(session, productVariantId);
                        ProductVariantDownloadUrls pvdurl = productsDAO.getProductVariantDownloadUrlsByProductvariantId(session, productVariantId);
                        if (!productsDAO.deleteProductVariant(session, productVariant, pvtList, pvpList, pvaList, pvdList, pvsList, pvccList, pvcList, pvmList, pvdurl)) {
                            tingcoProducts.getMsgStatus().setResponseCode(-1);
                            tingcoProducts.getMsgStatus().setResponseText("Error occurred while delete");
                            return tingcoProducts;
                        }
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured while deleting Data");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    public TingcoProducts addProductVariant(String content) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRODUCTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRODUCTS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    se.info24.productsjaxb.TingcoProducts tingcoProductsPost = (se.info24.productsjaxb.TingcoProducts) JAXBManager.getInstance().unMarshall(content, se.info24.productsjaxb.TingcoProducts.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    se.info24.productsjaxb.Products products = tingcoProductsPost.getProducts();
                    List<se.info24.productsjaxb.Product> productList = products.getProduct();
                    for (se.info24.productsjaxb.Product productJaxb : productList) {
                        for (se.info24.productsjaxb.ProductVariants pv : productJaxb.getProductVariants()) {
                            Set<String> productIds = new HashSet<String>();
                            productIds.add(pv.getProductID());
                            List<Products> productDataList = productsDAO.getProductsByProductId(productIds, session, 1);
                            if (productDataList.isEmpty()) {
                                tingcoProducts.getMsgStatus().setResponseCode(-1);
                                tingcoProducts.getMsgStatus().setResponseText("Data Not Found with ProductID");
                                return tingcoProducts;
                            } else {
                                String productVariantID = UUID.randomUUID().toString().toUpperCase();
                                GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                                Products productData = productDataList.get(0);
                                ProductVariants productVariant = new ProductVariants();
                                productVariant.setProductVariantId(productVariantID);
                                productVariant.setProducts(productData);
                                productVariant.setDeleted(0);//DefectID 380.
                                productVariant.setIsDefault(pv.getIsDefault());
                                productVariant.setIsEnabled(pv.getIsEnabled());
                                productVariant.setDisplayInWebShop(pv.getDisplayInWebShop());
                                productVariant.setProductVariantSKU(pv.getProductVariantSKU());
                                productVariant.setCreatedDate(gc.getTime());
                                productVariant.setUpdatedDate(gc.getTime());
                                productVariant.setUserId(sessions2.getUserId());

                                List<ProductVariantTranslations> pvtList = new ArrayList<ProductVariantTranslations>();
                                for (se.info24.productsjaxb.ProductVariantTranslations pvt : pv.getProductVariantTranslations()) {
                                    ProductVariantTranslations variantTranslations = new ProductVariantTranslations();
                                    variantTranslations.setCreatedDate(gc.getTime());
                                    variantTranslations.setUpdatedDate(gc.getTime());
                                    variantTranslations.setUserId(sessions2.getUserId());
                                    variantTranslations.setProductVariantName(pvt.getProductVariantName());
                                    variantTranslations.setProductVariantDescription(pvt.getProductVariantDescription() == null ? null : pvt.getProductVariantDescription());
                                    variantTranslations.setId(new ProductVariantTranslationsId(productVariantID, pvt.getCountryID()));
                                    pvtList.add(variantTranslations);
                                }
                                List<ProductVariantAttributes> pvaList = new ArrayList<ProductVariantAttributes>();
                                String[] defaultData = {"ProductImageURL", "ProductIconURL", "Color", "Weight", "Length", "Calories", "Fat", "Sugar", "Volume"};
                                for (int i = 0; i < defaultData.length; i++) {
                                    ProductVariantAttributes pva = new ProductVariantAttributes();
                                    pva.setAttributeName(defaultData[i]);
                                    pva.setCreatedDate(gc.getTime());
                                    pva.setUpdatedDate(gc.getTime());
                                    pva.setProductVariantAttributeId(UUID.randomUUID().toString().toUpperCase());
                                    pva.setUserId(sessions2.getUserId());
                                    pva.setProductVariantId(productVariantID);
                                    pvaList.add(pva);
                                }
                                if (productsDAO.addProductVariant(session, productVariant, pvtList, pvaList)) {
                                    se.info24.productsjaxb.Products productss = new se.info24.productsjaxb.Products();
                                    se.info24.productsjaxb.Product product = new se.info24.productsjaxb.Product();
                                    se.info24.productsjaxb.ProductVariants productVariants = new se.info24.productsjaxb.ProductVariants();
                                    productVariants.setProductVariantID(productVariantID);
                                    product.getProductVariants().add(productVariants);
                                    productss.getProduct().add(product);
                                    tingcoProducts.setProducts(productss);
                                    return tingcoProducts;
                                } else {
                                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                                    tingcoProducts.getMsgStatus().setResponseText("Error occured while save");
                                    return tingcoProducts;
                                }
                            }
                        }
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured while Adding data");
            return tingcoProducts;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    public TingcoProducts getProductVariantTranslations(String productVariantId) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (productVariantId.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("productVariantId should not be empty");
                    return tingcoProducts;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRODUCTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRODUCTS);
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
                    List<ProductVariantTranslations> pvtlist = productsDAO.getProductVariantTranslationsByProductVariantID(session, productVariantId);
                    if (pvtlist.isEmpty()) {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Data Not Exists with given productVariantId");
                        return tingcoProducts;
                    } else {
                        CountryDAO countryDao = new CountryDAO();
                        List<Country> countryList = countryDao.getAllCountries(session);
                        tingcoProducts = tingcoProductsXML.buildGetProductVariantTranslations(tingcoProducts, countryList, pvtlist);
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured while Reading Data");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    public TingcoProducts deleteProductVariantPrice(String productVariantId, int countryId) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (productVariantId.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("productVariantId should not be empty");
                    return tingcoProducts;
                }
                if (countryId <= 0) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("countryId should not be empty");
                    return tingcoProducts;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRODUCTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRODUCTS);
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
                    ProductVariantPrices productVariantPrices = productsDAO.getProductVariantPricesByProductVariantIdAndCountryId(session, productVariantId, countryId);
                    if (productVariantPrices != null) {
                        if (!productsDAO.deleteProductVariantPrices(session, productVariantPrices)) {
                            tingcoProducts.getMsgStatus().setResponseCode(-1);
                            tingcoProducts.getMsgStatus().setResponseText("Error occured while delete");
                            return tingcoProducts;
                        }
                    } else {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Data Not Exists with productVariantId, countryId");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured while deleting Data");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    public TingcoProducts updateProductVariantPrice(String productVariantId, int countryId, String pricePerUnitExclVat, String vatPercent, String quantityUnit, String newCountryId) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (productVariantId.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("productVariantId should not be empty");
                    return tingcoProducts;
                }
                if (countryId <= 0) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("countryId should not be empty");
                    return tingcoProducts;
                }
                if (pricePerUnitExclVat.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("pricePerUnitExclVat should not be empty");
                    return tingcoProducts;
                }
                if (vatPercent.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("vatPercent should not be empty");
                    return tingcoProducts;
                }
                if (quantityUnit.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("quantityUnit should not be empty");
                    return tingcoProducts;
                }
                if (newCountryId.equals("")) {
                    newCountryId = null;
                } else {
                    newCountryId = newCountryId.split("/")[2];
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRODUCTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRODUCTS);
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
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                    ProductVariantPrices listOldCountryId = productsDAO.getProductVariantPricesByProductVariantIdAndCountryId(session, productVariantId, countryId);
                    if (listOldCountryId == null) {
                        CountryDAO countryDao = new CountryDAO();
                        Country country = countryDao.getCountryById(countryId, session);
                        ProductVariantPrices pvp = new ProductVariantPrices();
                        ProductVariantPricesId id = new ProductVariantPricesId(productVariantId, countryId);
                        ProductVariants pv = productsDAO.getProductVariants(productVariantId, session);
                        pvp.setId(id);
                        pvp.setCountry(country);
                        pvp.setProductVariants(pv);
                        pvp.setCreatedDate(gc.getTime());
                        pvp.setUpdatedDate(gc.getTime());
                        pvp.setLastUpdatedByUserId(sessions2.getUserId());
                        pvp.setPricePerUnitExclVat(new BigDecimal(pricePerUnitExclVat));
                        pvp.setVatpercent(new BigDecimal(vatPercent));
                        pvp.setQuantityUnit(quantityUnit);
                        if (!productsDAO.saveUpdateProductVariantPrices(session, pvp)) {
                            tingcoProducts.getMsgStatus().setResponseCode(-1);
                            tingcoProducts.getMsgStatus().setResponseText("Error occured while save");
                            return tingcoProducts;
                        }
                    } else {
                        if (newCountryId != null) {
                            ProductVariantPrices listNewCountryId = productsDAO.getProductVariantPricesByProductVariantIdAndCountryId(session, productVariantId, Integer.valueOf(newCountryId));
                            if (listNewCountryId == null) {
                                if (!productsDAO.saveUpdateProductVariantPricesKey(session, productVariantId, countryId, pricePerUnitExclVat, vatPercent, quantityUnit, Integer.valueOf(newCountryId))) {
                                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                                    tingcoProducts.getMsgStatus().setResponseText("Error occured while update");
                                    return tingcoProducts;
                                }
                            } else {
                                tingcoProducts.getMsgStatus().setResponseCode(-1);
                                tingcoProducts.getMsgStatus().setResponseText("Data Exists in ProductVariantPrices For New Country");
                                return tingcoProducts;
                            }
                        } else {
                            listOldCountryId.setUpdatedDate(gc.getTime());
                            listOldCountryId.setLastUpdatedByUserId(sessions2.getUserId());
                            listOldCountryId.setPricePerUnitExclVat(new BigDecimal(pricePerUnitExclVat));
                            listOldCountryId.setVatpercent(new BigDecimal(vatPercent));
                            listOldCountryId.setQuantityUnit(quantityUnit);
                            if (!productsDAO.saveUpdateProductVariantPrices(session, listOldCountryId)) {
                                tingcoProducts.getMsgStatus().setResponseCode(-1);
                                tingcoProducts.getMsgStatus().setResponseText("Error occured while update");
                                return tingcoProducts;
                            }
                        }
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    public TingcoProducts addProductVariantPrice(String productVariantId, int countryId, String pricePerUnitExclVat, String vatPercent, String quantityUnit) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (productVariantId.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("productVariantId should not be empty");
                    return tingcoProducts;
                }
                if (countryId <= 0) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("countryId should not be empty");
                    return tingcoProducts;
                }
                if (pricePerUnitExclVat.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("pricePerUnitExclVat should not be empty");
                    return tingcoProducts;
                }
                if (vatPercent.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("vatPercent should not be empty");
                    return tingcoProducts;
                }
                if (quantityUnit.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("quantityUnit should not be empty");
                    return tingcoProducts;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRODUCTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRODUCTS);
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
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                    CountryDAO countryDao = new CountryDAO();
                    Country country = countryDao.getCountryById(countryId, session);
                    ProductVariants pv = productsDAO.getProductVariants(productVariantId, session);
                    if (productsDAO.getProductVariantPricesByProductVariantIdAndCountryId(session, productVariantId, countryId) != null) {
                        ProductVariantPrices pvp = new ProductVariantPrices();
                        ProductVariantPricesId id = new ProductVariantPricesId(productVariantId, countryId);
                        pvp.setId(id);
                        pvp.setCountry(country);
                        pvp.setProductVariants(pv);
                        pvp.setCreatedDate(gc.getTime());
                        pvp.setUpdatedDate(gc.getTime());
                        pvp.setLastUpdatedByUserId(sessions2.getUserId());
                        pvp.setPricePerUnitExclVat(new BigDecimal(pricePerUnitExclVat));
                        pvp.setVatpercent(new BigDecimal(vatPercent));
                        pvp.setQuantityUnit(quantityUnit);
                        if (!productsDAO.saveUpdateProductVariantPrices(session, pvp)) {
                            tingcoProducts.getMsgStatus().setResponseCode(-1);
                            tingcoProducts.getMsgStatus().setResponseText("Error occured while save");
                            return tingcoProducts;
                        }
                    } else {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Data Exists in ProductVariantPrices");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured while saving Data");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    public TingcoProducts deleteProductVariantAttribute(String productVariantAttributeId, String productVariantId) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (productVariantAttributeId.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("productVariantAttributeId should not be empty");
                    return tingcoProducts;
                }
                if (productVariantId.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("productVariantId should not be empty");
                    return tingcoProducts;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRODUCTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRODUCTS);
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
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                    ProductVariantAttributes pva = productsDAO.getProductVariantAttributesByID(session, productVariantAttributeId, productVariantId);
                    if (pva == null) {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Data not Exists with given productVariantAttributeId, productVariantId");
                        return tingcoProducts;
                    } else {
                        if (!productsDAO.deleteProductVariantAttributes(session, pva)) {
                            tingcoProducts.getMsgStatus().setResponseCode(-1);
                            tingcoProducts.getMsgStatus().setResponseText("Error occured while Delete");
                            return tingcoProducts;
                        }
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured while deleting Data");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    public TingcoProducts updateProductVariantAttribute(String productVariantAttributeId, String productVariantId, String attributeName, String attributeValue) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (productVariantAttributeId.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("productVariantAttributeId should not be empty");
                    return tingcoProducts;
                }
                if (productVariantId.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("productVariantId should not be empty");
                    return tingcoProducts;
                }
                if (attributeName.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("attributeName should not be empty");
                    return tingcoProducts;
                }
                if (attributeValue.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("attributeValue should not be empty");
                    return tingcoProducts;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRODUCTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRODUCTS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    attributeValue = TCMUtil.convertHexToString(attributeValue);
                    session = HibernateUtil.getSessionFactory().openSession();
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                    ProductVariantAttributes pva = productsDAO.getProductVariantAttributesByID(session, productVariantAttributeId, productVariantId);
                    if (pva == null) {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Data not Exists with given productVariantAttributeId, productVariantId");
                        return tingcoProducts;
                    } else {
                        pva.setProductVariantAttributeId(productVariantAttributeId);
                        pva.setProductVariantId(productVariantId);
                        pva.setAttributeName(attributeName);
                        pva.setAttributeValue(attributeValue);
                        pva.setUserId(sessions2.getUserId());
                        pva.setUpdatedDate(gc.getTime());
                        if (!productsDAO.saveUpdateProductVariantAttributes(session, pva)) {
                            tingcoProducts.getMsgStatus().setResponseCode(-1);
                            tingcoProducts.getMsgStatus().setResponseText("Error occured while Update");
                            return tingcoProducts;
                        }
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured while updating Data");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    public TingcoProducts addProductVariantAttribute(String productVariantId, String attributeName, String attributeValue) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (productVariantId.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("productVariantId should not be empty");
                    return tingcoProducts;
                }
                if (attributeName.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("attributeName should not be empty");
                    return tingcoProducts;
                }
                if (attributeValue.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("attributeValue should not be empty");
                    return tingcoProducts;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRODUCTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRODUCTS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    attributeValue = TCMUtil.convertHexToString(attributeValue);
                    session = HibernateUtil.getSessionFactory().openSession();
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                    ProductVariantAttributes pva = new ProductVariantAttributes();
                    pva.setProductVariantAttributeId(UUID.randomUUID().toString().toUpperCase());
                    pva.setProductVariantId(productVariantId);
                    pva.setAttributeName(attributeName);
                    pva.setAttributeValue(attributeValue);
                    pva.setUserId(sessions2.getUserId());
                    pva.setCreatedDate(gc.getTime());
                    pva.setUpdatedDate(gc.getTime());
                    if (!productsDAO.saveUpdateProductVariantAttributes(session, pva)) {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Error occured while save");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured while saving Data");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    public TingcoProducts updateProductVariantDetails(String content) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        GregorianCalendar gc = new GregorianCalendar();
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRODUCTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRODUCTS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    se.info24.productsjaxb.TingcoProducts tingcoProductsPost = (se.info24.productsjaxb.TingcoProducts) JAXBManager.getInstance().unMarshall(content, se.info24.productsjaxb.TingcoProducts.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    se.info24.productsjaxb.Products products = tingcoProductsPost.getProducts();

                    for (se.info24.productsjaxb.Product productJaxb : products.getProduct()) {
                        for (se.info24.productsjaxb.ProductVariants pv : productJaxb.getProductVariants()) {
                            ProductVariants productVariants = productsDAO.getProductVariants(pv.getProductVariantID(), session);
                            if (productVariants == null) {
                                tingcoProducts.getMsgStatus().setResponseCode(-1);
                                tingcoProducts.getMsgStatus().setResponseText("Data Not Found");
                                return tingcoProducts;
                            } else {
                                productVariants.setProductVariantSKU(pv.getProductVariantSKU());
                                productVariants.setIsDefault(pv.getIsDefault());
                                productVariants.setIsEnabled(pv.getIsEnabled());
                                productVariants.setDisplayInWebShop(pv.getDisplayInWebShop());
                                productVariants.setUpdatedDate(gc.getTime());

                                List<ProductVariantTranslations> pvtList = new ArrayList<ProductVariantTranslations>();
                                for (se.info24.productsjaxb.ProductVariantTranslations pvt : pv.getProductVariantTranslations()) {
                                    ProductVariantTranslations variantTranslations = productsDAO.getProductVariantTranslationsByIds(pv.getProductVariantID(), pvt.getCountryID(), session);
                                    if (variantTranslations != null) {
                                        if (pvt.getProductVariantDescription() != null) {
                                            variantTranslations.setProductVariantDescription(pvt.getProductVariantDescription());
                                        }
                                        variantTranslations.setProductVariantName(pvt.getProductVariantName());
                                        variantTranslations.setUpdatedDate(gc.getTime());
                                        pvtList.add(variantTranslations);
                                    } else {
                                        ProductVariantTranslations pvtrans = new ProductVariantTranslations();
                                        pvtrans.setId(new ProductVariantTranslationsId(pv.getProductVariantID(), pvt.getCountryID()));
                                        pvtrans.setProductVariantName(pvt.getProductVariantName());
                                        if (pvt.getProductVariantDescription() != null) {
                                            pvtrans.setProductVariantDescription(pvt.getProductVariantDescription());
                                        }
                                        pvtrans.setUserId(sessions2.getUserId());
                                        pvtrans.setCreatedDate(gc.getTime());
                                        pvtrans.setUpdatedDate(gc.getTime());
                                        pvtList.add(pvtrans);
                                    }
                                }

                                if (!productsDAO.updateProductVariantDetails(session, productVariants, pvtList)) {
                                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                                    tingcoProducts.getMsgStatus().setResponseText("Error occured while update");
                                    return tingcoProducts;
                                }
                            }
                        }
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured while update");
            return tingcoProducts;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    public TingcoProducts getProductVariantInformation(String productVariantId, int countryId) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (productVariantId.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("productVariantId should not be empty");
                    return tingcoProducts;
                }
                if (countryId <= 0) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("countryId should not be empty");
                    return tingcoProducts;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRODUCTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRODUCTS);
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
                    List<String> productVariantIdList = new ArrayList<String>();
                    productVariantIdList.add(productVariantId);
                    List<ProductVariants> productVariantList = productsDAO.getProductVariants(productVariantIdList, session);
                    if (!productVariantList.isEmpty()) {
                        List<ProductVariantTranslations> pvtList = productsDAO.getProductVariantTranslations(session, productVariantIdList, countryId);
                        List<ProductVariantAttributes> pvaList = productsDAO.getProductVariantAttributesByProductVariantId(session, productVariantIdList);
                        List<ProductVariantPrices> pvpList = productsDAO.getProductVariantPricesByProductVariantId(session, productVariantId);
                        tingcoProducts = tingcoProductsXML.buildProductVariantInformation(tingcoProducts, productVariantList.get(0), pvtList, pvaList, pvpList, session);
                    } else {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Data Not Fount For ProductVariantId");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured while retreiving data");
            return tingcoProducts;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts addProductsInGroups(String groupId, String productId) {
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                boolean hasPermission = getPermission(sessions2.getUserId(), TCMUtil.GROUPS, TCMUtil.ADD);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    ProductsInGroups productsInGroups = new ProductsInGroups(new ProductsInGroupsId(productId, groupId), sessions2.getUserId(), gc.getTime(), gc.getTime());
                    session.saveOrUpdate(productsInGroups);
                    tx.commit();
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }

            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts deleteProductsInGroups(String groupId, String productId) {
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                boolean hasPermission = getPermission(sessions2.getUserId(), TCMUtil.GROUPS, TCMUtil.DELETE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();
                    Object productsInGroups = productsDAO.getProductsInGroupsByIds(session, groupId, productId);
                    session.delete(productsInGroups);
                    tx.commit();
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }

            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
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

    private TingcoProducts getProductVariantsByGroupId(String groupId, int countryId, String searchString) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("groupId should not be empty");
                    return tingcoProducts;
                }
                if (countryId <= 0) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("countryId should not be empty");
                    return tingcoProducts;
                }
                if (!searchString.equals("")) {
                    searchString = searchString.split("/")[2];
                } else {
                    searchString = null;
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
                    List<ProductsInGroups> productsInGroupsList = productsDAO.getProductsInGroupsByGroupId(session, groupId);
                    if (!productsInGroupsList.isEmpty()) {
                        Set<String> productIdList = productsDAO.getProductIdList(productsInGroupsList);
                        List<ProductVariants> productVariantsList = productsDAO.getProductVariantsByProductIdsList(productIdList, null, session);
                        if (!productVariantsList.isEmpty()) {
                            List<String> productVariantIdList = productsDAO.getProductVariantIdList(productVariantsList);
                            List<ProductVariantTranslations> pvtList = new ArrayList<ProductVariantTranslations>();

                            if (searchString != null) {
                                if (TCMUtil.isValidUUID(searchString)) {
                                    boolean flag = false;
                                    for (String productVariantId : productVariantIdList) {
                                        if (searchString.equalsIgnoreCase(productVariantId)) {
                                            flag = true;
                                        }
                                    }
                                    if (flag) {
                                        productVariantIdList.clear();
                                        productVariantIdList.add(searchString);
                                    } else {
                                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                                        tingcoProducts.getMsgStatus().setResponseText("Data is not Found");
                                        return tingcoProducts;
                                    }
                                    pvtList = productsDAO.getProductVariantTranslations(session, productVariantIdList, countryId);
                                } else {
                                    pvtList = productsDAO.getProductVariantTranslationsBySearchString(session, productVariantIdList, countryId, searchString, 0);
                                }

                            } else {
                                pvtList = productsDAO.getProductVariantTranslations(session, productVariantIdList, countryId);
                            }

                            if (!pvtList.isEmpty()) {
                                if (pvtList.size() > 100) {
                                    tingcoProducts = tingcoProductsXML.buildTingcoProductVariants(tingcoProducts, productVariantsList, pvtList.subList(0, 100));
                                } else {
                                    tingcoProducts = tingcoProductsXML.buildTingcoProductVariants(tingcoProducts, productVariantsList, pvtList);
                                }

                            } else {
                                tingcoProducts.getMsgStatus().setResponseCode(-1);
                                tingcoProducts.getMsgStatus().setResponseText("Data is not Found");
                                return tingcoProducts;
                            }
                        } else {
                            tingcoProducts.getMsgStatus().setResponseCode(-1);
                            tingcoProducts.getMsgStatus().setResponseText("No Record Found in ProductVariants");
                            return tingcoProducts;
                        }
                    } else {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("No Record Found for the given GroupId");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }

            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts getProductsForProductVariants(String groupId, String searchString, int countryId) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("groupId should not be empty");
                    return tingcoProducts;
                }

                if (!searchString.equals("")) {
                    searchString = searchString.split("/")[2];
                } else {
                    searchString = null;
                }
                if (countryId <= 0) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("countryId should not be empty");
                    return tingcoProducts;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.PRODUCTS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.PRODUCTS);
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
                    List<ProductsInGroups> productsInGroupsList = productsDAO.getProductsInGroupsByGroupId(session, groupId);
                    if (!productsInGroupsList.isEmpty()) {
                        Set<String> productIdList = productsDAO.getProductIdList(productsInGroupsList);
                        List<Products> productsList = productsDAO.getProductsByProductId(productIdList, session, 100);
                        if (!(productsList.isEmpty())) {
                            productIdList.clear();
                            for (Products p : productsList) {
                                productIdList.add(p.getProductId());
                            }
                            List<ProductTranslations> productTransList = new ArrayList<ProductTranslations>();
                            if (searchString != null) {
                                productTransList = productsDAO.getProductTranslationsByIdandSearchString(productIdList, searchString, countryId, session);
                            } else {
                                productTransList = productsDAO.getProductTranslations(session, productIdList, countryId);
                            }


                            if (!productTransList.isEmpty()) {
                                tingcoProducts = tingcoProductsXML.buildTingcoProducts(tingcoProducts, productTransList);
                            } else {
                                tingcoProducts.getMsgStatus().setResponseCode(-1);
                                tingcoProducts.getMsgStatus().setResponseText("No Record Found in ProductTranslations");
                                return tingcoProducts;
                            }
                        } else {
                            tingcoProducts.getMsgStatus().setResponseCode(-1);
                            tingcoProducts.getMsgStatus().setResponseText("No Record Found in Products");
                            return tingcoProducts;
                        }
                    } else {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("No Record Found in ProductsInGroups");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }

            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured while retreiving data");
            return tingcoProducts;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts cancelProductSubscription(String serviceClientLoginId, String serviceSubscriptionACLID) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (serviceClientLoginId.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("serviceClientLoginId should not be empty");
                    return tingcoProducts;
                }
                if (serviceSubscriptionACLID.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("serviceSubscriptionACLID should not be empty");
                    return tingcoProducts;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.ORDERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.ORDERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ServiceContentSubscriptions> serviceContentSubList = productsDAO.getServiceContentSubscriptions(serviceClientLoginId, serviceSubscriptionACLID, session);
                    GroupProductVariantSubscriptions groupProductVariantSub = productsDAO.getGroupProductVariantSubscriptions(serviceClientLoginId, serviceSubscriptionACLID, session);
                    if (!productsDAO.cancelProductSubscription(serviceContentSubList, groupProductVariantSub, session)) {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Error Occured while cancelling Product Subscription");
                        return tingcoProducts;
                    }

                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts getGroupLimitPackages() throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
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
                    List<GroupLimitPackages> groupLimitPackagesList = productsDAO.getGroupLimitPackages(session);
                    if (!groupLimitPackagesList.isEmpty()) {
                        tingcoProducts = tingcoProductsXML.buildTingcoGroupLimitPackages(tingcoProducts, groupLimitPackagesList);
                        return tingcoProducts;
                    } else {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("No Record found in GroupLimitPackages");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }

            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts getGroupProductSubscription(String groupId, int countryId) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
//        System.gc();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("groupId should not be empty");
                    return tingcoProducts;
                }
                if (countryId <= 0) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("countryId should not be empty");
                    return tingcoProducts;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.ORDERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.ORDERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<GroupProductVariantSubscriptions> groupProductVariantSubList = productsDAO.getGroupProductVariantSubscriptions(groupId, session);
                    if (!groupProductVariantSubList.isEmpty()) {
                        List<String> productVariantIdList = new ArrayList<String>();
                        for (GroupProductVariantSubscriptions gpvs : groupProductVariantSubList) {
                            productVariantIdList.add(gpvs.getId().getProductVariantId());
                        }
                        List<ProductVariants> productVariantsList = productsDAO.getProductVariants(productVariantIdList, session);
                        List<String> productsIdList = new ArrayList<String>();
                        for (ProductVariants pv : productVariantsList) {
                            productsIdList.add(pv.getProducts().getProductId());
                        }
                        List<Products> productsList = productsDAO.getProducts(productsIdList, session);

                        List<ProductAttributes> productAttributesList = productsDAO.getProductAttributes(productsIdList, session);

                        List<ObjectImages> objectImagesList = productsDAO.getObjectImages(productsIdList, session);

                        List<String> serviceSubscriptionaclIdList = new ArrayList<String>();
                        for (GroupProductVariantSubscriptions gpvs : groupProductVariantSubList) {
                            serviceSubscriptionaclIdList.add(gpvs.getId().getServiceSubscriptionAclid());
                        }
                        List<ServiceSubscriptionAcl> serviceSubACLList = productsDAO.getServiceSubscriptionACLList(serviceSubscriptionaclIdList, session);

                        List<String> serviceClientLoginIdList = new ArrayList<String>();
                        for (GroupProductVariantSubscriptions gpvs : groupProductVariantSubList) {
                            serviceClientLoginIdList.add(gpvs.getId().getServiceClientLoginId());
                        }

                        List<ServiceClientLogins> serviceClientLoginsList = productsDAO.getServiceClientLogins(serviceClientLoginIdList, session);
                        List<ServiceContentSubscriptions> serviceContentSubList = productsDAO.getServiceContentSubscriptions(serviceSubscriptionaclIdList, serviceClientLoginIdList, session);
                        Hashtable<String, ServiceContentSubscriptions> serviceContentSubHash = new Hashtable<String, ServiceContentSubscriptions>();
                        for (ServiceContentSubscriptions scs : serviceContentSubList) {
                            serviceContentSubHash.put(scs.getContentCategoryId(), scs);
                        }

                        List<ServiceContentSubscriptions> serviceContentSub = new ArrayList<ServiceContentSubscriptions>();
                        Set<String> contentCategoryIdSet = serviceContentSubHash.keySet();
                        for (String string : contentCategoryIdSet) {
                            serviceContentSub.add(serviceContentSubHash.get(string));
                        }
//                        System.gc();
                        List<ContentCategoryTranslations> contentCategoryTransList = productsDAO.getContentCategoryTranslations(contentCategoryIdSet, countryId, session);
                        tingcoProducts = tingcoProductsXML.buildTingcoProducts(tingcoProducts, groupProductVariantSubList, productVariantsList, productsList, serviceSubACLList, serviceClientLoginsList, productAttributesList, objectImagesList, serviceContentSub, contentCategoryTransList, countryId);
                    } else {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("No Data found in GroupProductVariantSubscriptions");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
//            System.gc();
        }
        return tingcoProducts;
    }

    private TingcoProducts getProductCategories(String groupId, int countryId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("GroupId should not be empty");
                    return tingcoProducts;
                }
                if (countryId <= 0) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("CountryId should not be empty");
                    return tingcoProducts;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.ORDERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.ORDERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ProductCategoriesInGroups> productsCategoriesInGroupsList = productsDAO.getProductCategoriesInGroups(session, groupId);
                    if (!productsCategoriesInGroupsList.isEmpty()) {
                        List<ProductCategoryTranslations> productCategoryTranslationsList = productsDAO.getProductCategoryTranslations(session, productsCategoriesInGroupsList, countryId);
                        if (!productCategoryTranslationsList.isEmpty()) {
                            tingcoProducts = tingcoProductsXML.buildTingcoProductCategoryTranslations(tingcoProducts, productCategoryTranslationsList);
                        } else {
                            tingcoProducts.getMsgStatus().setResponseCode(-1);
                            tingcoProducts.getMsgStatus().setResponseText("No Record found for ProductsCategoryTranslations");
                            return tingcoProducts;
                        }
                    } else {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("No Record found for ProductCategoriesInGroups");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts getProductDetails(String productCategoryId, String groupId, int countryId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (productCategoryId.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("ProductCategoryId should not be empty");
                    return tingcoProducts;
                }
                if (groupId.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("GroupId should not be empty");
                    return tingcoProducts;
                }
                if (countryId <= 0) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("CountryId should not be empty");
                    return tingcoProducts;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.ORDERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.ORDERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<Products> productsList = productsDAO.getProductsByProductCategoryId(session, productCategoryId);
                    if (!productsList.isEmpty()) {
                        List<String> productsIdList = new ArrayList<String>();
                        for (Products p : productsList) {
                            productsIdList.add(p.getProductId());
                        }
                        List<ProductsInGroups> productsInGroupsList = productsDAO.getProductsInGroupsByGroupId(session, groupId);
                        List<ProductAttributes> productAttributesList = productsDAO.getProductAttributesByProductId(productsIdList, session);
                        List<ObjectImages> objectImagesList = productsDAO.getObjectImages(productsIdList, session);
                        List<ProductTranslations> productTranslationses = productsDAO.getProductTranslations(session, new HashSet<String>(productsIdList), countryId);

                        tingcoProducts = tingcoProductsXML.buildTingcoProductDetails(tingcoProducts, productsList, productsInGroupsList, productAttributesList, objectImagesList, productTranslationses);
                    } else {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("No Products found for given ProductCategoryId");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    public TingcoProducts getProductsForGroups(String groupId, String searchString, int countryId) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("groupId should not be empty");
                    return tingcoProducts;
                }
                if (countryId <= 0) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("countryId should not be empty");
                    return tingcoProducts;
                }
                if (searchString.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("searchString should not be empty");
                    return tingcoProducts;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.ORDERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.ORDERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ProductsInGroups> productsInGroupsList = productsDAO.getProductsInGroupsByGroupId(session, groupId);
                    if (!productsInGroupsList.isEmpty()) {
                        Set<String> productIdsList = productsDAO.getproductsByProductsInGroups(productsInGroupsList, session, 50);
                        if (!productIdsList.isEmpty()) {
                            List<ProductTranslations> productTransList = productsDAO.getProductTranslationsByIdandSearchString(productIdsList, searchString, countryId, session);
                            if (!productTransList.isEmpty()) {
                                tingcoProducts = tingcoProductsXML.buildTingcoProducts(tingcoProducts, productTransList);
                            } else {
                                tingcoProducts.getMsgStatus().setResponseCode(-1);
                                tingcoProducts.getMsgStatus().setResponseText("No Record Found in ProductTranslations");
                                return tingcoProducts;
                            }
                        } else {
                            tingcoProducts.getMsgStatus().setResponseCode(-1);
                            tingcoProducts.getMsgStatus().setResponseText("No Record Found in Products");
                            return tingcoProducts;
                        }
                    } else {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("No Record Found in ProductsInGroups");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts getPurchaseTransactionDetails(String transactionId, int countryId) {
        boolean hasPermission = false;
        tingcoProducts = null;
        Session ismSession = null;
        Session oprSession = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
                if (transactionId.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("transactionId should not be empty");
                    return tingcoProducts;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.TRANSACTIONS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.TRANSACTIONS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    ismSession = HibernateUtil.getSessionFactory().openSession();
                    oprSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    List<TransactionProducts> transProductsList = productsDAO.getTransactionProductsById(oprSession, transactionId);
                    List<TransactionResult> transResultList = new ArrayList<TransactionResult>();
                    TransactionResult results = productsDAO.getTransactionResultsById(oprSession, transactionId);
                    if (results != null) {
                        transResultList.add(results);
                    }
                    if (!transProductsList.isEmpty() || !transResultList.isEmpty()) {
                        UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), ismSession);
                        if (userTimeZones2 != null) {
                            String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), ismSession).getTimeZoneGmtoffset();
                            return tingcoProductsXML.buildPurchaseTransactionDetails(tingcoProducts, transProductsList, transResultList, timeZoneGMToffset, ismSession, countryId);
                        } else {
                            tingcoProducts.getMsgStatus().setResponseCode(-1);
                            tingcoProducts.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                            return tingcoProducts;
                        }

                    } else {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Data not found");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured");
        } finally {
            delayLog(requestedTime);
            if (ismSession != null) {
                ismSession.close();
            }
            if (oprSession != null) {
                oprSession.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts getServiceClientLogins(String groupId) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts =
                tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("groupId should not be empty");
                    return tingcoProducts;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.ORDERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.ORDERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();

                    List<ServiceClientLogins> list = productsDAO.getServiceClientLoginsByGroupId(groupId, session);
                    if (list.isEmpty()) {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("No Data found in ServiceClientLogins");
                        return tingcoProducts;
                    } else {
                        se.info24.productsjaxb.Products products = new se.info24.productsjaxb.Products();
                        se.info24.productsjaxb.Product product = new Product();
                        int seqNo = 1;
                        product.setSeqNo(seqNo);
                        for (ServiceClientLogins scl : list) {
                            se.info24.productsjaxb.ServiceClientLogins serviceClientLogins = new se.info24.productsjaxb.ServiceClientLogins();
                            serviceClientLogins.setServiceClientLoginID(scl.getServiceClientLoginId());
                            serviceClientLogins.setServiceClientLoginName(scl.getServiceClientLoginName());
                            product.getServiceClientLogins().add(serviceClientLogins);
                        }

                        products.getProduct().add(product);
                        tingcoProducts.setProducts(products);
                    }

                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }

            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error Occured");
            return tingcoProducts;
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }

        }
        return tingcoProducts;
    }

    private TingcoProducts getServiceClientLogins(String groupId, String searchString) {
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (groupId.equals("")) {
                tingcoProducts.getMsgStatus().setResponseCode(-1);
                tingcoProducts.getMsgStatus().setResponseText("groupId should not be empty");
                return tingcoProducts;
            }

            if (searchString.equals("")) {
                searchString = null;
            } else {
                searchString = searchString.split("/")[2];
            }
            tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
            session = HibernateUtil.getSessionFactory().openSession();
            List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(groupId, session);
            Set<String> groupIdSet = groupDAO.getGroupIdsList(groupId, groupTrustsList);
            if (!groupIdSet.isEmpty()) {
                List<ServiceClientLogins> sclList = new ArrayList<ServiceClientLogins>();
                /**
                 * SPLIT LIST SIZE 30 MAR 2014
                 */
                List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupIdSet), 2000);

                if (searchString == null) {
                    for (List<String> list : listsplit) {
                        List<ServiceClientLogins> sclListTemp = productsDAO.getServiceClientLoginsByGroupIdList(new HashSet<String>(list), session);
                        sclList.addAll(sclListTemp);
                        if (sclList.size() > 50) {
                            sclList = sclList.subList(0, 50);
                            break;
                        }
                    }
                //sclList = productsDAO.getServiceClientLoginsByGroupIdList(groupIdSet, session);
                } else {
                    for (List<String> list : listsplit) {
                        List<ServiceClientLogins> sclListTemp = productsDAO.getServiceClientLoginsByGroupIdandSearchString(new HashSet<String>(list), searchString, session);
                        sclList.addAll(sclListTemp);
                        if (sclList.size() > 50) {
                            sclList = sclList.subList(0, 50);
                            break;
                        }
                    }
                //sclList = productsDAO.getServiceClientLoginsByGroupIdandSearchString(groupIdSet, searchString, session);
                }

                if (!sclList.isEmpty()) {
                    tingcoProducts = tingcoProductsXML.buildTingcoServiceClientLogins(tingcoProducts, sclList);
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("No Data found in ServiceClientLogins");
                    return tingcoProducts;
                }

            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-1);
                tingcoProducts.getMsgStatus().setResponseText("No Groups found for the given input");
                return tingcoProducts;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }

        }
        return tingcoProducts;
    }

    private TingcoProducts orderContentSubscription(String groupid, String serviceclientloginid, String productvariantid, String subscriptionlength, int countryId) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        se.info24.pojo.ServiceSubscriptionAcl ssACL = null;
        se.info24.pojo.GroupProductVariantSubscriptions gpvs = null;
        List<ServiceContentSubscriptions> scsList = new ArrayList<ServiceContentSubscriptions>();
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (groupid.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("groupId should not be empty");
                    return tingcoProducts;
                }

                if (serviceclientloginid.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("serviceclientloginid should not be empty");
                    return tingcoProducts;
                }

                if (productvariantid.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("productvariantid should not be empty");
                    return tingcoProducts;
                }

                if (subscriptionlength.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("subscriptionlength should not be empty");
                    return tingcoProducts;
                }

                if (countryId <= 0) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("countryId should not be empty");
                    return tingcoProducts;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.ORDERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.ORDERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Calendar cal = Calendar.getInstance();
                    ssACL = new se.info24.pojo.ServiceSubscriptionAcl();
                    ssACL.setActiveFromDate(new Date());
                    ssACL.setUpdatedDate(new Date());
                    ssACL.setCreatedDate(new Date());
                    ssACL.setLimitByDate(1);
                    ssACL.setLimitByHits(0);
                    ssACL.setSearchHitsLeftToUse(100000);
                    ssACL.setSearchHitsStartValue(100000);
                    ssACL.setServiceSubscriptionAclid(UUID.randomUUID().toString());
                    cal.setTime(ssACL.getActiveFromDate());
                    cal.add(Calendar.MONTH, Integer.parseInt(subscriptionlength));
                    ssACL.setActiveToDate(cal.getTime());
                    if (productsDAO.saveServiceSubscriptionAcl(ssACL, session)) {
                        String serviceSubscriptionAclid = ssACL.getServiceSubscriptionAclid();
                        gpvs = new GroupProductVariantSubscriptions();
                        se.info24.pojo.GroupProductVariantSubscriptionsId gpvsid = new GroupProductVariantSubscriptionsId(groupid, productvariantid, serviceclientloginid, serviceSubscriptionAclid);
                        gpvs.setCreatedDate(new Date());
                        gpvs.setIsEnabled(1);
                        gpvs.setUpdatedDate(new Date());
                        gpvs.setLastUpdatedByUserId(sessions2.getUserId());
                        gpvs.setId(gpvsid);
                        if (productsDAO.saveGroupProductVariantSubscriptions(gpvs, session)) {
                            List<ProductVariantContentCategories> pvccList = productsDAO.getProductVariantContentCategories(productvariantid, session);
                            List<ProductVariantServices> pvsList = productsDAO.getProductVariantServices(productvariantid, session);
                            GroupDefaultAgreement gda = productsDAO.getGroupDefaultAgreement(groupid, session);
                            ProductVariantTranslations pvt = productsDAO.getProductVariantTranslationsByIds(productvariantid, countryId, session);
                            if (pvccList.isEmpty()) {
                                tingcoProducts.getMsgStatus().setResponseCode(-1);
                                tingcoProducts.getMsgStatus().setResponseText("No Data found in ProductVariantContentCategories");
                                return tingcoProducts;
                            }

                            if (pvsList.isEmpty()) {
                                tingcoProducts.getMsgStatus().setResponseCode(-1);
                                tingcoProducts.getMsgStatus().setResponseText("No Data found in ProductVariantServices");
                                return tingcoProducts;
                            }

                            for (ProductVariantContentCategories pvcc : pvccList) {
                                for (ProductVariantServices pvs : pvsList) {
                                    se.info24.pojo.ServiceContentSubscriptions scs = new ServiceContentSubscriptions();
                                    scs.setServiceContentSubscriptionId(UUID.randomUUID().toString());
                                    scs.setContentCategoryId(pvcc.getId().getContentCategoryId());
                                    scs.setCreatedDate(new Date());
                                    if (gda != null) {
                                        scs.setAgreementId(gda.getAgreements().getAgreementId());
                                    }

                                    scs.setServiceClientLoginId(serviceclientloginid);
                                    scs.setServiceId(pvs.getId().getServiceId());
                                    scs.setServiceSubscriptionAclid(serviceSubscriptionAclid);
                                    scs.setSubscriptionEnabled(1);
                                    scs.setUpdatedDate(new Date());
                                    scs.setUserId(sessions2.getUserId());
                                    if (!productsDAO.saveServiceContentSubscriptions(scs, session)) {
                                        productsDAO.deleteServiceSubscriptionAcl(ssACL, session);
                                        productsDAO.deleteGroupProductVariantSubscriptions(gpvs, session);
                                        for (ServiceContentSubscriptions servicecontent : scsList) {
                                            productsDAO.deleteServiceContentSubscriptions(servicecontent, session);
                                        }
                                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                                        tingcoProducts.getMsgStatus().setResponseText("Error Occured while saving ServiceContentSubscriptions");
                                        return tingcoProducts;
                                    }
                                    scsList.add(scs);
                                }
                            }
                            ServiceClientLogins scl = productsDAO.getServiceClientLoginsById(serviceclientloginid, session);
                            if (!productsDAO.placeOrder(groupid, productvariantid, pvt.getProductVariantName(), sessions2.getUserId(), subscriptionlength, scl.getServiceClientLoginName(), session)) {
                                productsDAO.deleteServiceSubscriptionAcl(ssACL, session);
                                productsDAO.deleteGroupProductVariantSubscriptions(gpvs, session);
                                for (ServiceContentSubscriptions servicecontent : scsList) {
                                    productsDAO.deleteServiceContentSubscriptions(servicecontent, session);
                                }
                                tingcoProducts.getMsgStatus().setResponseCode(-1);
                                tingcoProducts.getMsgStatus().setResponseText("Error Occured While saving Order");
                                return tingcoProducts;
                            }
                            tingcoProducts.getMsgStatus().setResponseCode(0);
                            tingcoProducts.getMsgStatus().setResponseText("OK");
                            return tingcoProducts;
                        } else {
                            productsDAO.deleteServiceSubscriptionAcl(ssACL, session);
                            tingcoProducts.getMsgStatus().setResponseCode(-1);
                            tingcoProducts.getMsgStatus().setResponseText("Error Occured while saving GroupProductVariantSubscriptions");
                            return tingcoProducts;
                        }
                    } else {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Error Occured while saving ServiceSubscriptionAcl");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }

            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }

        } catch (Exception e) {
            if (ssACL != null) {
                productsDAO.deleteServiceSubscriptionAcl(ssACL, session);
            }

            if (gpvs != null) {
                productsDAO.deleteGroupProductVariantSubscriptions(gpvs, session);
            }

            for (ServiceContentSubscriptions servicecontent : scsList) {
                productsDAO.deleteServiceContentSubscriptions(servicecontent, session);
            }

            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error Occured while saving Data");
            return tingcoProducts;

        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }

        }
//        return tingcoProducts;
    }

    private TingcoProducts renewProductSubscription(String serviceSubscriptionACLID, String months, String searchHitsLeftToUse, String groupId, String productVariantId, String productVariantName, String userId) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts =
                tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (serviceSubscriptionACLID.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("serviceSubscriptionACLID should not be empty");
                    return tingcoProducts;
                }

                if (months.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("months should not be empty");
                    return tingcoProducts;
                }

                if (searchHitsLeftToUse.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("searchHitsLeftToUse should not be empty");
                    return tingcoProducts;
                }

                if (groupId.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("groupId should not be empty");
                    return tingcoProducts;
                }

                if (productVariantId.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("productVariantId should not be empty");
                    return tingcoProducts;
                }

                if (productVariantName.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("productVariantName should not be empty");
                    return tingcoProducts;
                }

                if (userId.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("userId should not be empty");
                    return tingcoProducts;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.ORDERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.ORDERS);
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
                    ServiceSubscriptionAcl serviceSubscriptionAcl = productsDAO.getServiceSubscriptionAcl(serviceSubscriptionACLID, session);
                    Date oldActiveToDate = serviceSubscriptionAcl.getActiveToDate();
                    int oldSearchHitsLeftTouse = serviceSubscriptionAcl.getSearchHitsLeftToUse();
                    String serviceSubscriptionAclId = serviceSubscriptionAcl.getServiceSubscriptionAclid();
                    if (productsDAO.extendSubscriptions(serviceSubscriptionAcl, Integer.valueOf(months), Integer.valueOf(searchHitsLeftToUse), session)) {
                        if (!productsDAO.placeOrder(groupId, productVariantId, productVariantName, userId, months, null, session)) {
                            if (!productsDAO.rollbackExtendedSubscription(oldActiveToDate, oldSearchHitsLeftTouse, serviceSubscriptionAclId, session)) {
                                tingcoProducts.getMsgStatus().setResponseCode(-1);
                                tingcoProducts.getMsgStatus().setResponseText("Error Occured While Rollback extended Subscription");
                                return tingcoProducts;
                            }
                        }
                    } else {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Error Occured While Updating ServiceSubscriptionACL");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts getProductDetailsByProductID(String productID, int countryID) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (productID.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("productID should not be empty");
                    return tingcoProducts;
                }
                if (countryID <= 0) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("countryID should not be empty");
                    return tingcoProducts;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.ORDERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.ORDERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<String> productidList = new ArrayList<String>();
                    productidList.add(productID);
                    List<Products> productList = productsDAO.getProducts(productidList, session);
                    if (!productList.isEmpty()) {
                        ProductTranslations productTrans = productsDAO.getProductTranslationsById(productList.get(0).getProductId(), countryID, session);
                        List<ProductAttributes> productAttributesList = productsDAO.getProductAttributesByProductID(productList.get(0).getProductId(), session);
                        ProductCategoryTranslations productCategoryTrans = productsDAO.getProductCategoryTranslationsByID(productList.get(0).getProductCategories().getProductCategoryId(), countryID, session);
                        if (productCategoryTrans != null) {
                            tingcoProducts = tingcoProductsXML.buildTingcoProductDetailByProductId(tingcoProducts, productCategoryTrans, productTrans, productAttributesList);
                            return tingcoProducts;
                        } else {
                            tingcoProducts.getMsgStatus().setResponseCode(-1);
                            tingcoProducts.getMsgStatus().setResponseText("No Record Found in ProductCategory");
                            return tingcoProducts;
                        }
                    } else {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("No Record Found for the given ProductId");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }
            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts getProductVariantsByProductID(String productID, int countryID) throws DatatypeConfigurationException {
        boolean hasPermission = false;
        tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (productID.equals("")) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("productID should not be empty");
                    return tingcoProducts;
                }

                if (countryID <= 0) {
                    tingcoProducts.getMsgStatus().setResponseCode(-1);
                    tingcoProducts.getMsgStatus().setResponseText("countryID should not be empty");
                    return tingcoProducts;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.ORDERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.ORDERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ProductVariants> productVariantsList = productsDAO.getProductVariantsByProductId(productID, session);
                    List<String> productVariantsid = productsDAO.getProductVariantIdList(productVariantsList);
                    if (!productVariantsid.isEmpty()) {
                        List<ProductVariantTranslations> pvtList = productsDAO.getProductVariantTranslations(session, productVariantsid, countryID);
                        if (pvtList.size() == 0) {
                            tingcoProducts.getMsgStatus().setResponseCode(-1);
                            tingcoProducts.getMsgStatus().setResponseText("Data is not Found");
                            return tingcoProducts;
                        }

                        tingcoProducts = tingcoProductsXML.buildTingcoProductVariants(tingcoProducts, productVariantsList, pvtList);
                        return tingcoProducts;
                    } else {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Data is not Found");
                        return tingcoProducts;
                    }

                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }

            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }

        }
        return tingcoProducts;
    }

    private TingcoProducts getDeviceProductVariantsPurchased(String deviceId, String groupId, int countryId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
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
                    List<ProductsInGroups> productInGroup = productsDAO.getProductsInGroupsByGroupId(session, groupId);
                    List<String> productid = new ArrayList<String>();
                    for (ProductsInGroups productsInGroups : productInGroup) {
                        productid.add(productsInGroups.getId().getProductId());
                    }
                    if (!productid.isEmpty()) {
                        List<ProductVariants> productvariantList = productsDAO.getProductVariantsByProductidList(session, productid);
                        List<se.info24.pojo.ProductVariantDevices> productVariantDevicese = productsDAO.getProductVariantDevicesByDeviceId(session, deviceId);
                        List<String> productVariantidList = new ArrayList<String>();
                        if (!productVariantDevicese.isEmpty()) {
                            for (se.info24.pojo.ProductVariantDevices productVariantDevices : productVariantDevicese) {
                                for (ProductVariants productVariants : productvariantList) {
                                    if (productVariants.getProductVariantId().equalsIgnoreCase(productVariantDevices.getId().getProductVariantId())) {
                                        productVariantidList.add(productVariantDevices.getId().getProductVariantId());
                                    }

                                }
                            }
                        } else {
                            tingcoProducts.getMsgStatus().setResponseCode(-1);
                            tingcoProducts.getMsgStatus().setResponseText("Data is not Found");
                            return tingcoProducts;
                        }

                        List<ProductVariantTranslations> productVariantTransList = productsDAO.getProductVariantTranslations(session, productVariantidList, countryId);
                        UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                        if (userTimeZones2 != null) {
                            String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                            return tingcoProductsXML.buildDeviceEventTrigger(tingcoProducts, productVariantidList, productVariantDevicese, productVariantTransList, timeZoneGMToffset);
                        } else {
                            tingcoProducts.getMsgStatus().setResponseCode(-1);
                            tingcoProducts.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                            return tingcoProducts;
                        }

                    } else {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Product is not Found");
                        return tingcoProducts;
                    }

                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }

            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts getDeviceProductVariants(String deviceId, String groupId, int countryId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
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
                    List<ProductsInGroups> productInGroup = productsDAO.getProductsInGroupsByGroupId(session, groupId);
                    List<String> productid = new ArrayList<String>();
                    for (ProductsInGroups productsInGroups : productInGroup) {
                        productid.add(productsInGroups.getId().getProductId());
                    }

                    if (!productid.isEmpty()) {
                        List<ProductVariants> productvariantList = productsDAO.getProductVariantsByProductidList(session, productid);
                        List<se.info24.pojo.ProductVariantDevices> productVariantDevicese = productsDAO.getProductVariantDevicesByDeviceId(session, deviceId);
                        Set<String> productVariantidSet = new HashSet<String>();
                        if (!productVariantDevicese.isEmpty()) {

                            for (ProductVariants productVariants : productvariantList) {
                                boolean flag = false;
                                for (se.info24.pojo.ProductVariantDevices productVariantDevices : productVariantDevicese) {
                                    if (productVariants.getProductVariantId().equalsIgnoreCase(productVariantDevices.getId().getProductVariantId())) {
                                        flag = true;
                                    }
                                }
                                if (!flag) {
                                    productVariantidSet.add(productVariants.getProductVariantId());
                                }
                            }
                        } else {
                            for (ProductVariants productVariants : productvariantList) {
                                productVariantidSet.add(productVariants.getProductVariantId());
                            }
                        }
                        if (productVariantidSet.isEmpty()) {
                            tingcoProducts.getMsgStatus().setResponseCode(-1);
                            tingcoProducts.getMsgStatus().setResponseText("Data not Found");
                            return tingcoProducts;
                        }

                        List<String> productVariantidList = new ArrayList<String>(productVariantidSet);
                        List<ProductVariantTranslations> productVariantTransList = productsDAO.getProductVariantTranslations(session, productVariantidList, countryId);
                        UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                        if (userTimeZones2 != null) {
                            String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                            return tingcoProductsXML.buildDeviceEventTrigger(tingcoProducts, productVariantidList, productVariantDevicese, productVariantTransList, timeZoneGMToffset);
                        } else {
                            tingcoProducts.getMsgStatus().setResponseCode(-1);
                            tingcoProducts.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                            return tingcoProducts;
                        }

                    } else {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("Product is not Found");
                        return tingcoProducts;
                    }

                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }

            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoProducts;
    }

    private TingcoProducts getContentCategoryDetailsByCategoryId(String contentCategoryId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        try {
            tingcoProducts = tingcoProductsXML.buildTingcoProductsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                if (getPermission(sessions2.getUserId(), TCMUtil.CONTENT, TCMUtil.READ)) {
                    ContentDAO contentDAO = new ContentDAO();
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object ccObject = contentDAO.getContentCategoryById(contentCategoryId, session);
                    if (ccObject != null) {
                        ContentCategories contentCategories = (ContentCategories) ccObject;
                        CountryDAO countryDAO = new CountryDAO();
                        List<Country> countries = countryDAO.getAllCountries(session);
                        tingcoProducts = tingcoProductsXML.buildContentCategories(tingcoProducts, contentCategories, countries);
                    } else {
                        tingcoProducts.getMsgStatus().setResponseCode(-1);
                        tingcoProducts.getMsgStatus().setResponseText("ContentCategory Not Found");
                        return tingcoProducts;
                    }
                } else {
                    tingcoProducts.getMsgStatus().setResponseCode(-10);
                    tingcoProducts.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoProducts;
                }

            } else {
                tingcoProducts.getMsgStatus().setResponseCode(-3);
                tingcoProducts.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoProducts;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoProducts;
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}

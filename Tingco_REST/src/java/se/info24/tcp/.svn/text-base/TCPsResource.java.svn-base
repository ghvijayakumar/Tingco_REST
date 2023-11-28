/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.tcp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
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
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.group.GroupDAO;
import se.info24.ismOperationsPojo.TransactionResult;
import se.info24.pojo.AddressType;
import se.info24.pojo.Addresses;
import se.info24.pojo.ApplicationProviderTypes;
import se.info24.pojo.Country;
import se.info24.pojo.GroupAlias;
import se.info24.pojo.GroupDefaultAgreement;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupTranslationsId;
import se.info24.pojo.GroupTrusts;
import se.info24.pojo.GroupTrustsId;
import se.info24.pojo.Groups;
import se.info24.pojo.ObjectAddresses;
import se.info24.pojo.ObjectAddressesId;
import se.info24.pojo.ObjectFieldData;
import se.info24.pojo.ObjectFieldDataId;
import se.info24.pojo.ObjectGroups;
import se.info24.pojo.OgmuserAlias;
import se.info24.pojo.OgmuserAliasId;
import se.info24.pojo.ProviderTypeTranslations;
import se.info24.pojo.ProviderTypes;
import se.info24.pojo.UserAlias;
import se.info24.pojo.UserAliasDetails;
import se.info24.pojo.UserAliasOrders;
import se.info24.pojo.UserProviderTypeReferences;
import se.info24.pojo.UserProviderTypeReferencesId;
import se.info24.pojo.UserProviderTypes;
import se.info24.pojo.UserRoleMemberships2;
import se.info24.pojo.UserRoleMemberships2Id;
import se.info24.pojo.UserRoles2;
import se.info24.pojo.UserTimeZones2;
import se.info24.pojo.UserTypes2;
import se.info24.pojo.Users2;
import se.info24.pojo.Users2Id;
import se.info24.products.ProductsDAO;
import se.info24.restUtil.RestUtilDAO;
import se.info24.tcpjaxb.TingcoCustomer;
import se.info24.tcpjaxb.Users;
import se.info24.user.CountryDAO;
import se.info24.user.UserDAO;
import se.info24.user.UserManager;
import se.info24.util.HibernateUtil;
import se.info24.util.JAXBManager;
import se.info24.util.RESTDoc;
import se.info24.util.RSAPassword;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Ravikant
 */
@Path("/tcp")
public class TCPsResource {

    @Context
    private UriInfo context;
    @Context
    HttpServletRequest request;
    TingcoCustomerXML tingcoCustomerxml = new TingcoCustomerXML();
    TingcoCustomerDAO customerDAO = new TingcoCustomerDAO();
    UserDAO userDAO = new UserDAO();
    CountryDAO countryDAO = new CountryDAO();
    UserManager manager = new UserManager();
    GroupDAO groupDAO = new GroupDAO();
    TingcoCustomer customer = new TingcoCustomer();
//    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of TCPsResource */
    public TCPsResource() {
    }

    /**
     * Retrieves representation of an instance of se.info24.tcp.TCPsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param countryid
     * @return TingcoCustomer
     */
    @GET
    @Path("/getprovidertypes/rest/{rest}/countryid/{countryid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetProviderTypes", desc = "Get ProviderTypes", req_Params = {"UserAlias;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoCustomer getxml_ProviderTypes(@PathParam("countryid") String countryid) {
        return getProviderTypes(countryid);
    }

    /**
     *
     * @param countryid
     * @return TingcoCustomer
     */
    @GET
    @Path("/getprovidertypes/json/{json}/countryid/{countryid}")
    @Produces("application/json")
    @RESTDoc(methodName = "GetProviderTypes", desc = "Get ProviderTypes", req_Params = {"UserAlias;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoCustomer getJson_ProviderTypes(@PathParam("countryid") String countryid) {
        return getProviderTypes(countryid);
    }

    /**
     *UpdateProviderMethod
     * @param providertypeid
     * @param userId
     * @return TingcoCustomer
     */
    @GET
    @Path("/updatepaymentmethod/rest/{rest}/providertypeid/{providertypeid}/userid/{userid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "UpdateProviderMethod", desc = "Update ProviderMethod", req_Params = {"providertypeid;UUID", "UserID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoCustomer getXml_UpdatePaymentMethod(@PathParam("providertypeid") String providertypeid, @PathParam("userid") String userId) {
        return updatePaymentMethod(providertypeid, userId);
    }

    /**
     *UpdateProviderMethod
     * @param providertypeid
     * @param userId
     * @return TingcoCustomer
     */
    @GET
    @Path("/updatepaymentmethod/json/{json}/providertypeid/{providertypeid}/userid/{userid}")
    @Produces("application/json")
    public TingcoCustomer getJson_UpdatePaymentMethod(@PathParam("providertypeid") String providertypeid, @PathParam("userid") String userId) {
        return updatePaymentMethod(providertypeid, userId);
    }

    /**
     *UpdateProviderMethodForFortum
     * @param userId
     * @return TingcoCustomer
     */
    @GET
    @Path("/updatepaymentmethodforfortum/rest/{rest}/userid/{userid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "UpdateProviderMethodForFortum", desc = "Update ProviderMethod For Fortum", req_Params = {"UserId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoCustomer getXml_updatePaymentMethodForFortum(@PathParam("userid") String userId) {
        return updatePaymentMethodForFortum(userId);
    }

    /**
     *UpdateProviderMethodForFortum
     * @param userId
     * @return TingcoCustomer
     */
    @GET
    @Path("/updatepaymentmethodforfortum/json/{json}/userid/{userid}")
    @Produces("application/json")
    public TingcoCustomer getJson_updatePaymentMethodForFortum(@PathParam("userid") String userId) {
        return updatePaymentMethodForFortum(userId);
    }

    /**
     *
     * @param userId
     * @param addresstypeId
     * @return TingcoCustomer
     */
    @GET
    @Path("/getuserprofileforuserid/rest/{rest}/userid/{userid}/addresstypeid/{addresstypeid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetProviderTypes", desc = "Get ProviderTypes", req_Params = {"UserAlias;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoCustomer getXml_UserProfileForUserId(@PathParam("userid") String userId, @PathParam("addresstypeid") String addresstypeId) {
        return getUserProfileForUserId(addresstypeId, userId);
    }

    /**
     *
     * @param userId
     * @param addresstypeId
     * @return TingcoCustomer
     */
    @GET
    @Path("/getuserprofileforuserid/json/{json}/userid/{userid}/addresstypeid/{addresstypeid}")
    @Produces("application/json")
    @RESTDoc(methodName = "GetProviderTypes", desc = "Get ProviderTypes", req_Params = {"UserAlias;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoCustomer getjson_UserProfileForUserId(@PathParam("userid") String userId, @PathParam("addresstypeid") String addresstypeId) {
        return getUserProfileForUserId(addresstypeId, userId);
    }

    /**
     *
     * @param userId
     * @param transactionStartTime
     * @param transactionStopTime
     * @return TingcoCustomer
     */
    @GET
    @Path("/getpurchasetransactionhistory/rest/{rest}/userid/{userid}/transactionstarttime/{transactionstarttime}/transactionstoptime/{transactionstoptime}{useraliasid:(/useraliasid/[^/]+?)?}")
    @Produces("application/xml")
    @RESTDoc(methodName = "getPurchaseTransactionHistory", desc = "get PurchaseTransactionHistory", req_Params = {"UserAlias;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoCustomer getXml_PurchaseTransactionHistory(@PathParam("userid") String userId, @PathParam("transactionstarttime") String transactionStartTime, @PathParam("transactionstoptime") String transactionStopTime, @PathParam("useraliasid") String userAliasId) {
        return getPurchaseTransactionHistory(userId, transactionStartTime, transactionStopTime, userAliasId);
    }

    /**
     *
     * @param userId
     * @param transactionStartTime
     * @param transactionStopTime
     * @return TingcoCustomer
     */
    @GET
    @Path("/getpurchasetransactionhistory/json/{json}/userid/{userid}/transactionstarttime/{transactionstarttime}/transactionstoptime/{transactionstoptime}{useraliasid:(/useraliasid/[^/]+?)?}")
    @Produces("application/json")
    @RESTDoc(methodName = "getPurchaseTransactionHistory", desc = "get PurchaseTransactionHistory", req_Params = {"UserAlias;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoCustomer getJson_PurchaseTransactionHistory(@PathParam("userid") String userId, @PathParam("transactionstarttime") String transactionStartTime, @PathParam("transactionstoptime") String transactionStopTime, @PathParam("useraliasid") String userAliasId) {
        return getPurchaseTransactionHistory(userId, transactionStartTime, transactionStopTime, userAliasId);
    }

    /**
     *
     * @param userId
     * @param providerTypeId
     * @return TingcoCustomer
     */
    @GET
    @Path("/getobjectcodeforprovidertype/rest/{rest}/providertypeid/{providertypeid}/userid/{userid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetObjectCodeForProviderType", desc = "Get ObjectCode For ProviderType", req_Params = {"userid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoCustomer getXml_ObjectCodeForProviderType(@PathParam("userid") String userId, @PathParam("providertypeid") String providerTypeId) {
        return getObjectCodeForProviderType(userId, providerTypeId);
    }

    /**
     *
     * @param userId
     * @param providerTypeId
     * @return TingcoCustomer
     */
    @GET
    @Path("/getobjectcodeforprovidertype/json/{json}/providertypeid/{providertypeid}/userid/{userid}")
    @Produces("application/json")
    @RESTDoc(methodName = "GetObjectCodeForProviderType", desc = "Get ObjectCode For ProviderType", req_Params = {"userid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoCustomer getjson_ObjectCodeForProviderType(@PathParam("userid") String userId, @PathParam("providertypeid") String providerTypeId) {
        return getObjectCodeForProviderType(userId, providerTypeId);
    }

    /**
     *
     * @param userId
     * @param providerTypeId
     * @param userProviderReference1
     * @return TingcoCustomer
     */
    @GET
    @Path("/adduserprovidertypereference/rest/{rest}/providertypeid/{providertypeid}/userid/{userid}/userproviderreference1/{userproviderreference1}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetObjectCodeForProviderType", desc = "Add UserProviderTypeReference", req_Params = {"userid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoCustomer getXml_AddUserProviderTypeReference(@PathParam("userid") String userId, @PathParam("providertypeid") String providerTypeId, @PathParam("userproviderreference1") String userProviderReference1) {
        return addUserProviderTypeReference(userId, providerTypeId, userProviderReference1);
    }

    /**
     *
     * @param userId
     * @param providerTypeId
     * @param userProviderReference1
     * @return TingcoCustomer
     */
    @GET
    @Path("/adduserprovidertypereference/json/{json}/providertypeid/{providertypeid}/userid/{userid}/userproviderreference1/{userproviderreference1}")
    @Produces("application/json")
    @RESTDoc(methodName = "GetObjectCodeForProviderType", desc = "Add UserProviderTypeReference", req_Params = {"userid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoCustomer getJson_AddUserProviderTypeReference(@PathParam("userid") String userId, @PathParam("providertypeid") String providerTypeId, @PathParam("userproviderreference1") String userProviderReference1) {
        return addUserProviderTypeReference(userId, providerTypeId, userProviderReference1);
    }

    /**
     *
     * @param userId
     * @param providerTypeId
     * @return TingcoCustomer
     */
    @GET
    @Path("/deleteuserprovidertypereference/rest/{rest}/providertypeid/{providertypeid}/userid/{userid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "DeleteUserProviderTypeReference", desc = "Delete UserProviderTypeReference", req_Params = {"userid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoCustomer getXml_DeleteUserProviderTypeReference(@PathParam("userid") String userId, @PathParam("providertypeid") String providerTypeId) {
        return deleteUserProviderTypeReference(userId, providerTypeId);
    }

    /**
     *
     * @param userId
     * @param providerTypeId
     * @return TingcoCustomer
     */
    @GET
    @Path("/deleteuserprovidertypereference/json/{json}/providertypeid/{providertypeid}/userid/{userid}")
    @Produces("application/json")
    @RESTDoc(methodName = "DeleteUserProviderTypeReference", desc = "Delete UserProviderTypeReference", req_Params = {"userid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoCustomer getJson_DeleteUserProviderTypeReference(@PathParam("userid") String userId, @PathParam("providertypeid") String providerTypeId) {
        return deleteUserProviderTypeReference(userId, providerTypeId);
    }

    /**
     *
     * @param userId
     * @param countryId
     * @return TingcoCustomer
     */
    @GET
    @Path("/getprovidertypeidforuser/rest/{rest}/userid/{userid}/countryid/{countryid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetProviderTypes", desc = "Get ProviderTypes", req_Params = {"UserAlias;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoCustomer getxml_ProviderTypesByUserId(@PathParam("userid") String userId, @PathParam("countryid") String countryId) {
        return getProviderTypesByUserId(userId, countryId);
    }

    /**
     *
     * @param userId
     * @param countryId
     * @return TingcoCustomer
     */
    @GET
    @Path("/getprovidertypeidforuser/json/{json}/userid/{userid}/countryid/{countryid}")
    @Produces("application/json")
    @RESTDoc(methodName = "GetProviderTypes", desc = "Get ProviderTypes", req_Params = {"UserAlias;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoCustomer getJson_ProviderTypesByUserId(@PathParam("userid") String userId, @PathParam("countryid") String countryId) {
        return getProviderTypesByUserId(userId, countryId);
    }

    /**
     *
     * @param content
     * @return TingcoCustomer
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/updateuserprofile/rest/{rest}")
    @RESTDoc(methodName = "update user profile", desc = "Used to update user profile details", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoCustomer postXml_UpdateUserProfile(String content) {
        return updateUserProfile(content);
    }

    /**
     *
     * @param content
     * @return TingcoCustomer
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/updateuserprofile/json/{json}")
    @RESTDoc(methodName = "update product variant details", desc = "Used to update product variant details", req_Params = {"json;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoCustomer postjson_UpdateUserProfile(String content) {
        return updateUserProfile(content);
    }

    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addtcpnewuser/rest/{rest}")
    @RESTDoc(methodName = "Add TCP New User details", desc = "Used to Add TCP New User", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoCustomer postXml_AddTCPNewUser(String content) {
        return addTCPNewUser(content);
    }

    /*
     * AddTCPUserForFortum
     * @param content
     * @return TingcoCustomer
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addtcpnewuserforfortum/rest/{rest}{ordercomment:(/ordercomment/[^/]+?)?}{vehicleregistrationnumber:(/vehicleregistrationnumber/[^/]+?)?}{product:(/product/[^/]+?)?}")
    @RESTDoc(methodName = "Add TCP New User details For Fortum", desc = "Used to Add TCP New User For Fortum", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoCustomer postXml_addTCPNewUserForFortum(String content, @PathParam("ordercomment") String orderComment, @PathParam("vehicleregistrationnumber") String vehicleRegistrationNumber,@PathParam("product") String product) {
        return addTCPNewUserForFortum(content, orderComment, vehicleRegistrationNumber,product);
    }

    /*
     * AddTCPUserForFortum
     * @param content
     * @return TingcoCustomer
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addtcpnewuserforfortum/json/{json}{ordercomment:(/ordercomment/[^/]+?)?}{vehicleregistrationnumber:(/vehicleregistrationnumber/[^/]+?)?}{product:(/product/[^/]+?)?}")
    @RESTDoc(methodName = "Add TCP New User details For Fortum", desc = "Used to Add TCP New User For Fortum", req_Params = {"json;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoCustomer postJson_addTCPNewUserForFortum(String content, @PathParam("ordercomment") String orderComment, @PathParam("vehicleregistrationnumber") String vehicleRegistrationNumber,@PathParam("product") String product) {
        return addTCPNewUserForFortum(content, orderComment, vehicleRegistrationNumber,product);
    }

    /**
     * POST method for creating an instance of TCPsResource
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
     * ProviderTypesByUserIdfortum
     * @param ApplicationId
     * @param countryId
     * @return TingcoCustomer
     */
    @GET
    @Path("/getprovidertypeidforfortum/rest/{rest}/applicationid/{applicationid}/countryid/{countryid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetProviderTypes", desc = "Get ProviderTypes", req_Params = {"UserAlias;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoCustomer getXml_ProviderTypesByUserIdfortum(@PathParam("applicationid") String ApplicationId, @PathParam("countryid") String countryId) {
        return getProviderTypesByUserIdfortum(ApplicationId, countryId);
    }

    /**
     * ProviderTypesByUserIdfortum
     * @param ApplicationId
     * @param countryId
     * @return TingcoCustomer
     */
    @GET
    @Path("/getprovidertypeidforfortum/json/{json}/applicationid/{applicationid}/countryid/{countryid}")
    @Produces("application/json")
    @RESTDoc(methodName = "GetProviderTypes", desc = "Get ProviderTypes", req_Params = {"UserAlias;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoCustomer getJson_ProviderTypesByUserIdfortum(@PathParam("applicationid") String ApplicationId, @PathParam("countryid") String countryId) {
        return getProviderTypesByUserIdfortum(ApplicationId, countryId);
    }

    /**
     * Sub-resource locator method for  tcp
     */
    @Path("tcp")
    public TCPResource getTCPResource() {
        return new TCPResource();
    }

    private TingcoCustomer getProviderTypesByUserIdfortum(String applicationId, String countryId) {
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            customer = tingcoCustomerxml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                session = HibernateUtil.getSessionFactory().openSession();
                List<ApplicationProviderTypes> applicationProviderTypeses = customerDAO.GetApplicationProviderTypesByapplicationId(session, applicationId);
                if (applicationProviderTypeses.isEmpty()) {
                    customer.getMsgStatus().setResponseCode(-1);
                    customer.getMsgStatus().setResponseText("Data Not Found");
                    return customer;
                }
                List<String> providertypeid = new ArrayList<String>();
                for (ApplicationProviderTypes ApplicationProviderType : applicationProviderTypeses) {
                    providertypeid.add(ApplicationProviderType.getId().getProviderTypeId());
                }
                List<ProviderTypes> PT = customerDAO.getProviderTypesByIds(session, providertypeid);
                if (PT.isEmpty()) {
                    customer.getMsgStatus().setResponseCode(-1);
                    customer.getMsgStatus().setResponseText("Data Not Found");
                    return customer;
                }
                providertypeid.clear();
                for (ProviderTypes providerTypes : PT) {
                    providertypeid.add(providerTypes.getProviderTypeId());
                }
                if (!providertypeid.isEmpty()) {
                    List<ProviderTypeTranslations> PTT = customerDAO.getProviderTypeTranslationsByProviderId(session, providertypeid, Integer.valueOf(countryId));
                    customer = tingcoCustomerxml.buildgetProviderTypes(customer, PTT, PT);
                    return customer;
                } else {
                    customer.getMsgStatus().setResponseCode(-1);
                    customer.getMsgStatus().setResponseText("Data Not Found");
                    return customer;
                }

            } else {
                customer.getMsgStatus().setResponseCode(-10);
                customer.getMsgStatus().setResponseText("User Session is not Valid");
                return customer;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            customer.getMsgStatus().setResponseCode(-1);
            customer.getMsgStatus().setResponseText("Error occurred");
            return customer;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoCustomer addTCPNewUserForFortum(String content, String orderComment, String vehicleRegistrationNumber,String product) {
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        GregorianCalendar gc_10years = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        gc_10years.add(GregorianCalendar.YEAR, 10);
        try {
            customer = tingcoCustomerxml.buildUserTemplate();
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            se.info24.tcpjaxb.TingcoCustomer tingcoCustomerPost = (se.info24.tcpjaxb.TingcoCustomer) JAXBManager.getInstance().unMarshall(content, se.info24.tcpjaxb.TingcoCustomer.class);
            Users usersJaxb = tingcoCustomerPost.getUsers();
            if (usersJaxb != null) {
                if (orderComment.equals("")) {
                    orderComment = null;
                } else {
                    orderComment = orderComment.split("/")[2];
                }
                if (vehicleRegistrationNumber.equals("")) {
                    vehicleRegistrationNumber = null;
                } else {
                    vehicleRegistrationNumber = vehicleRegistrationNumber.split("/")[2];
                }
                if (product.equals("")) {
                    product = null;
                } else {
                    product = product.split("/")[2];
                }
                se.info24.tcpjaxb.User userJaxb = usersJaxb.getUser().get(0);

                boolean emailIdExist = manager.isValidEmail(userJaxb.getUserEmail(), session);
                if (!emailIdExist) {
                    String groupID = UUID.randomUUID().toString();
                    String userID = UUID.randomUUID().toString();
                    Groups group = new Groups();
                    group.setGroupId(groupID);
                    group.setGroupParentId(userJaxb.getGroups().getGroupParentID());
                    group.setGroupTypeId(userJaxb.getGroups().getGroupTypeID());
                    group.setDisplayOrder(1);
                    group.setIsWebShopUser(0);
                    group.setUserId(userID);
                    group.setOrganizationNumber(userJaxb.getSocialSecurityNumber());
                    group.setCreatedDate(gc.getTime());
                    group.setUpdatedDate(gc.getTime());
                    Groups grps = groupDAO.getGroupByGroupId(userJaxb.getGroups().getGroupParentID(), session);
                    group.setDomainId(grps.getDomainId());

                    //Adding GroupDefaultAgreement Table.
                    ProductsDAO productsDAO = new ProductsDAO();
                    GroupDefaultAgreement gda = productsDAO.getGroupDefaultAgreement(userJaxb.getGroups().getGroupParentID(), session);
                    if (gda != null) {
                        GroupDefaultAgreement groupDefaultAgreement = new GroupDefaultAgreement();
                        groupDefaultAgreement.setGroupId(groupID);
                        groupDefaultAgreement.setAgreements(gda.getAgreements());
                        groupDefaultAgreement.setLastUpdatedByUserId(userID);
                        groupDefaultAgreement.setUpdatedDate(gc.getTime());
                        groupDefaultAgreement.setCreatedDate(gc.getTime());
                        session.saveOrUpdate(groupDefaultAgreement);
                    }


                    List<Country> countries = countryDAO.getAllCountries(session);
                    Set<GroupTranslations> trans = new HashSet<GroupTranslations>();
                    for (Country c : countries) {
                        GroupTranslations gt = new GroupTranslations();
                        GroupTranslationsId gt_id = new GroupTranslationsId(groupID, c.getCountryId());
                        gt.setId(gt_id);
                        gt.setGroupName(userJaxb.getFirstName() + " " + userJaxb.getLastName());
                        gt.setGroupDescription("CD Mypages registered customer");
                        gt.setGroups(group);
                        gt.setUserId(userID);
                        gt.setCreatedDate(gc.getTime());
                        gt.setUpdatedDate(gc.getTime());
                        trans.add(gt);
                    }
                    group.setGroupTranslationses(trans);
                    session.saveOrUpdate(group);
                    session.flush();
                    session.clear();

                    //New group trusts its groupparent
                    GroupTrusts groupTrusts = new GroupTrusts();
                    groupTrusts.setId(new GroupTrustsId(groupID, userJaxb.getGroups().getGroupParentID(), "1EE69077-F01F-4296-B439-0F3E535C3ACA"));
                    groupTrusts.setActiveFromDate(gc.getTime());
                    groupTrusts.setActiveToDate(gc_10years.getTime());
                    groupTrusts.setLastUpdatedByUserId(userID);
                    groupTrusts.setCreatedDate(gc.getTime());
                    groupTrusts.setUpdatedDate(gc.getTime());
                    session.saveOrUpdate(groupTrusts);

                    //New group trusts Info24 group
                    //Checking both parent group and info24 groups are same to avoid duplicate primary key
                    if (!userJaxb.getGroups().getGroupParentID().equalsIgnoreCase("B4C99EB0-18E6-439F-882A-9E4A11E1FF75")) {
                        groupTrusts = new GroupTrusts();
                        groupTrusts.setId(new GroupTrustsId(groupID, "B4C99EB0-18E6-439F-882A-9E4A11E1FF75", "1EE69077-F01F-4296-B439-0F3E535C3ACA"));
                        groupTrusts.setActiveFromDate(gc.getTime());
                        groupTrusts.setActiveToDate(gc_10years.getTime());
                        groupTrusts.setLastUpdatedByUserId(userID);
                        groupTrusts.setCreatedDate(gc.getTime());
                        groupTrusts.setUpdatedDate(gc.getTime());
                        session.saveOrUpdate(groupTrusts);
                    }

                    //New group trusts trusted groups configured in web.config of .NET team
                    if (userJaxb.getGroups().getITrustGroups() != null) {
                        for (String itrustgroupid : userJaxb.getGroups().getITrustGroups().getITrustGroupID()) {
                            groupTrusts = new GroupTrusts();
                            groupTrusts.setId(new GroupTrustsId(groupID, itrustgroupid, "1EE69077-F01F-4296-B439-0F3E535C3ACA"));
                            groupTrusts.setActiveFromDate(gc.getTime());
                            groupTrusts.setActiveToDate(gc_10years.getTime());
                            groupTrusts.setLastUpdatedByUserId(userID);
                            groupTrusts.setCreatedDate(gc.getTime());
                            groupTrusts.setUpdatedDate(gc.getTime());
                            session.merge(groupTrusts);
                        }
                    }

                    Users2 users = new Users2();
                    users.setUserId(userID);
                    Users2Id id = new Users2Id();
                    id.setDomainId(userJaxb.getDomainID());
                    id.setLoginName(userJaxb.getUserEmail());
                    users.setId(id);
                    users.setUserEmail(userJaxb.getUserEmail());
                    users.setGroupId(groupID);
                    users.setPassword(RSAPassword.encryptedPwd(userJaxb.getPassword()));
                    users.setFirstName(userJaxb.getFirstName());
                    users.setLastName(userJaxb.getLastName());
                    users.setUserMobilePhone(userJaxb.getUserMobilePhone());
                    users.setCountryId(userJaxb.getCountryID());
                    users.setUserTypes2(new UserTypes2(userJaxb.getUserTypeID()));
                    users.setIsLockedOut(0);
                    users.setChangePwdRequired(0);
                    users.setActiveFromDate(gc.getTime());
                    users.setActiveToDate(gc_10years.getTime());
                    users.setFailedPasswordAttemptCount(0);
                    users.setUpdatedDate(gc.getTime());
                    users.setCreatedDate(gc.getTime());
                    session.saveOrUpdate(users);

//                            Adding Address
                    Addresses address = null;
                    String addressid = UUID.randomUUID().toString();
                    address = new Addresses();
                    address.setAddressId(addressid);
                    address.setAddressName("Invoice address");
                    address.setAddressDescription("Invoice address");
                    address.setAddressType(new AddressType(3));

                    address.setAddressStreet(userJaxb.getAddress().getAddressStreet());
                    address.setAddressZip(userJaxb.getAddress().getAddressZip());
                    address.setAddressCity(userJaxb.getAddress().getAddressCity());
                    address.setCountry(new Country(userJaxb.getCountryID()));
                    address.setAddressMobile(userJaxb.getUserMobilePhone());
                    address.setAddressEmail(userJaxb.getUserEmail());
                    address.setUserId(userID);
                    address.setUpdatedDate(gc.getTime());
                    address.setCreatedDate(gc.getTime());
                    session.saveOrUpdate(address);
//                            Adding Data ObjectAddresses
                    ObjectAddresses objectAddresses = new ObjectAddresses();
                    ObjectAddressesId oID = new ObjectAddressesId();
                    oID.setObjectId(userID);
                    oID.setAddressTypeId(3);
                    objectAddresses.setId(oID);
                    objectAddresses.setAddressId(addressid);
                    objectAddresses.setLastUpdatedUserId(userID);
                    objectAddresses.setCreatedDate(gc.getTime());
                    objectAddresses.setUpdatedDate(gc.getTime());
                    session.saveOrUpdate(objectAddresses);

//                  Adding  UserRoleMemberships2
                    UserRoleMemberships2 urm = new UserRoleMemberships2();
                    UserRoleMemberships2Id urmid = new UserRoleMemberships2Id();
                    urmid.setUserId(userID);
                    urmid.setUserRoleId("1EE69077-F01F-4296-B439-0F3E535C3ACA");
                    urm.setId(urmid);
                    urm.setUserRoles2(new UserRoles2(userID, null));
                    urm.setLastUpdatedByUserId(userID);
                    urm.setCreatedDate(gc.getTime());
                    urm.setUpdatedDate(gc.getTime());
                    session.saveOrUpdate(urm);

//                  UserProviderTypes
                    UserProviderTypes uPT = new UserProviderTypes();
                    uPT.setUserId(userID);
                    uPT.setProviderTypes(new ProviderTypes("C67EAA14-3883-45F8-9399-599EF4BC84B9"));
                    uPT.setLastUpdatedByUserId(userID);
                    uPT.setCreatedDate(gc.getTime());
                    uPT.setUpdatedDate(gc.getTime());
                    session.saveOrUpdate(uPT);

//                  UserAlias
                    UserAliasOrders uao = new UserAliasOrders();
                    uao.setUserAliasOrderId(UUID.randomUUID().toString());
                    uao.setUserId(userID);
                    uao.setGroupId(groupID);
                    uao.setOrderDate(gc.getTime());
                    uao.setOrderedUserAlias(1);
                    if(product != null){
                        uao.setProduct(product);
                    }else{
                        uao.setProduct("RFID Card");
                    }
                    
                    if (orderComment != null) {
                        uao.setOrderComment(orderComment);
                    } else {
//                        uao.setOrderComment("New customer registration");
                        uao.setOrderComment("Fortum");// Updated on sprint 5(3.1.0)
                    }
                    uao.setIsDelivered(0);
                    uao.setFirstName(userJaxb.getFirstName());
                    uao.setLastName(userJaxb.getLastName());
                    uao.setDeliveryAddressRow1(userJaxb.getAddress().getAddressStreet());
                    uao.setDeliveryZipCode(userJaxb.getAddress().getAddressZip());
                    uao.setDeliveryCity(userJaxb.getAddress().getAddressCity());
                    Country country = countryDAO.getCountryById(userJaxb.getCountryID(), session);
                    uao.setDeliveryCountry(country.getCountryName());
                    uao.setLastUpdatedByUserId(userID);
                    uao.setCreatedDate(gc.getTime());
                    uao.setUpdatedDate(gc.getTime());
                    uao.setSocialSecurityNumber(userJaxb.getSocialSecurityNumber());

                    if (vehicleRegistrationNumber != null) {
                        ObjectFieldData objFieldDataLP = new ObjectFieldData();
                        ObjectFieldDataId ids = new ObjectFieldDataId();
                        ids.setObjectId(userID);
                        ids.setFieldId("25488b29-cba6-4361-bb22-f3aff0bf6ba6".toUpperCase());
                        objFieldDataLP.setId(ids);
                        objFieldDataLP.setFieldValue(vehicleRegistrationNumber);
                        objFieldDataLP.setCreatedDate(gc.getTime());
                        objFieldDataLP.setUpdatedDate(gc.getTime());
                        objFieldDataLP.setLastUpdatedByUserId(userID);
                        session.saveOrUpdate(objFieldDataLP);
                    }


                    session.saveOrUpdate(uao);
                    session.getTransaction().commit();
                } else {
                    customer.getMsgStatus().setResponseCode(-1);
                    customer.getMsgStatus().setResponseText("Email id already exists");
                    return customer;
                }
            } else {
                customer.getMsgStatus().setResponseCode(-1);
                customer.getMsgStatus().setResponseText("Invalid XML found");
                return customer;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            customer.getMsgStatus().setResponseCode(-1);
            customer.getMsgStatus().setResponseText("Error occurred");
            return customer;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return customer;
    }

    private TingcoCustomer getProviderTypesByUserId(String userId, String countryId) {
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            customer = tingcoCustomerxml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                session = HibernateUtil.getSessionFactory().openSession();
                List<String> providertypeid = new ArrayList<String>();
                UserProviderTypes UPT = customerDAO.getUserProviderTypesByUserID(session, userId);
                if (UPT == null) {
                    customer.getMsgStatus().setResponseCode(-1);
                    customer.getMsgStatus().setResponseText("Data Not Found");
                    return customer;
                }
                providertypeid.add(UPT.getProviderTypes().getProviderTypeId());
                if (!providertypeid.isEmpty()) {
                    List<ProviderTypeTranslations> PTT = customerDAO.getProviderTypeTranslationsByProviderId(session, providertypeid, Integer.valueOf(countryId));
                    List<ProviderTypes> PT = customerDAO.getProviderTypesByIds(session, providertypeid);
                    customer = tingcoCustomerxml.buildgetProviderTypes(customer, PTT, PT);
                    return customer;
                } else {
                    customer.getMsgStatus().setResponseCode(-1);
                    customer.getMsgStatus().setResponseText("Data Not Found");
                    return customer;
                }

            } else {
                customer.getMsgStatus().setResponseCode(-10);
                customer.getMsgStatus().setResponseText("User Session is not Valid");
                return customer;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            customer.getMsgStatus().setResponseCode(-1);
            customer.getMsgStatus().setResponseText("Error occurred");
            return customer;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoCustomer addUserProviderTypeReference(String userId, String providerTypeId, String userProviderReference1) {
        Session session = null;
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        long requestedTime = System.currentTimeMillis();
        try {
            customer = tingcoCustomerxml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                session = HibernateUtil.getSessionFactory().openSession();
                UserProviderTypeReferences uptr = new UserProviderTypeReferences();
                UserProviderTypeReferencesId uptrId = new UserProviderTypeReferencesId();
                uptrId.setProviderTypeId(providerTypeId);
                uptrId.setUserId(userId);
                uptr.setId(uptrId);
                uptr.setUserProviderReference1(userProviderReference1);
                uptr.setCreatedDate(gc.getTime());
                uptr.setUpdatedDate(gc.getTime());
                if (!customerDAO.saveAddUserProviderTypeReference(session, uptr)) {
                    customer.getMsgStatus().setResponseCode(-1);
                    customer.getMsgStatus().setResponseText("Error occurred");
                    return customer;
                } else {
                    customer.getMsgStatus().setResponseCode(0);
                    customer.getMsgStatus().setResponseText("Added");
                    return customer;
                }

            } else {
                customer.getMsgStatus().setResponseCode(-10);
                customer.getMsgStatus().setResponseText("User Session is not Valid");
                return customer;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            customer.getMsgStatus().setResponseCode(-1);
            customer.getMsgStatus().setResponseText("Error occurred");
            return customer;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoCustomer deleteUserProviderTypeReference(String userId, String providerTypeId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        try {
            customer = tingcoCustomerxml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                session = HibernateUtil.getSessionFactory().openSession();
                UserProviderTypeReferences uptr = customerDAO.getUserProviderTypeReferencesByIdandUserID(session, providerTypeId, userId);
                if (uptr != null) {
                    if (!customerDAO.deleteUserProviderTypeReference(session, uptr)) {
                        customer.getMsgStatus().setResponseCode(-1);
                        customer.getMsgStatus().setResponseText("Error occurred");
                        return customer;
                    } else {
                        customer.getMsgStatus().setResponseCode(0);
                        customer.getMsgStatus().setResponseText("Deleted");
                        return customer;
                    }
                } else {
                    customer.getMsgStatus().setResponseCode(-1);
                    customer.getMsgStatus().setResponseText("Data Not Found");
                    return customer;
                }

            } else {
                customer.getMsgStatus().setResponseCode(-10);
                customer.getMsgStatus().setResponseText("User Session is not Valid");
                return customer;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            customer.getMsgStatus().setResponseCode(-1);
            customer.getMsgStatus().setResponseText("Error occurred");
            return customer;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoCustomer getObjectCodeForProviderType(String userId, String providerTypeId) {
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            customer = tingcoCustomerxml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                session = HibernateUtil.getSessionFactory().openSession();
                ProviderTypes pt = customerDAO.getProviderTypesById(session, providerTypeId);
                if (pt != null) {
                    UserProviderTypeReferences uptr = customerDAO.getUserProviderTypeReferencesByIdandUserID(session, providerTypeId, userId);
                    return tingcoCustomerxml.buildGetObjectCodeForProviderType(customer, pt, uptr);
                } else {
                    customer.getMsgStatus().setResponseCode(-1);
                    customer.getMsgStatus().setResponseText("Data Not Found");
                    return customer;
                }

            } else {
                customer.getMsgStatus().setResponseCode(-10);
                customer.getMsgStatus().setResponseText("User Session is not Valid");
                return customer;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            customer.getMsgStatus().setResponseCode(-1);
            customer.getMsgStatus().setResponseText("Error occurred");
            return customer;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoCustomer addTCPNewUser(String content) {
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        try {
            customer = tingcoCustomerxml.buildUserTemplate();
//            if (request.getAttribute("USERSESSION") != null) {
            session = HibernateUtil.getSessionFactory().openSession();
            se.info24.tcpjaxb.TingcoCustomer tingcoCustomerPost = (se.info24.tcpjaxb.TingcoCustomer) JAXBManager.getInstance().unMarshall(content, se.info24.tcpjaxb.TingcoCustomer.class);
            List<se.info24.tcpjaxb.User> userJaxbList = tingcoCustomerPost.getUsers().getUser();
            if (!userJaxbList.isEmpty()) {
                se.info24.tcpjaxb.User userJaxb = userJaxbList.get(0);
                GroupAlias ga = customerDAO.getGroupAliasByalias(session, userJaxb.getOrganizationAlias());
                String rfid = null;
                if (ga != null) {

//                    UserAlias ua = customerDAO.getUserAliasByUserAlias(session, String.valueOf(new BigInteger(userJaxb.getUserAlias(), 16).toString()));
                    String idTag = userJaxb.getUserAlias();
                    int i = idTag.length();
                    if (i % 2 == 0) {
                        StringBuffer sb = new StringBuffer();
                        while (i > 0) {
                            sb.append(idTag, i - 2, i);
                            i = i - 2;
                        }
                        rfid = String.valueOf(Long.parseLong(sb.toString(), 16));
                    } else {
                        customer.getMsgStatus().setResponseCode(-4);
                        customer.getMsgStatus().setResponseText("Useralias is not valid ");
                        return customer;
                    }

                    List<UserAlias> ualist = userDAO.getUserAliasByUserAliasTypeIdAndUserAlias(session, rfid, userJaxb.getUserAliasTypeID());
                    if (!ualist.isEmpty()) {
                        UserAlias ua = ualist.get(0);
                        if (customerDAO.isValidUserAlias(ua)) {
                            GroupDAO groupdao = new GroupDAO();
                            UserRoles2 roles = customerDAO.getUserRoleByUserRoleID(session, "1EE69077-F01F-4296-B439-0F3E535C3ACA");
                            ObjectGroups objectGroup = customerDAO.getObjectGroupsByGroupId(session, userJaxb.getObjectGroupID());
                            List<String> providerTypesId = new ArrayList<String>();
                            providerTypesId.add("C67EAA14-3883-45F8-9399-599EF4BC84B9");
                            ProviderTypes providerTypes = customerDAO.getProviderTypesByIds(session, providerTypesId).get(0);
                            Groups group = groupdao.getGroupByGroupId(ua.getGroupId(), session);
                            UserAliasDetails uad = userDAO.getUserAliasDetailsByID(session, ua.getUserAliasId());

                            AddressType addressType = userDAO.getAddresstypeById(session, 3);
                            Country country = customerDAO.getCountryById(session, userJaxb.getCountryID());

                            UserTypes2 userType = userDAO.getUserTypes2ById(session, userJaxb.getUserTypeID());
//                            Adding Users2
                            String userid = UUID.randomUUID().toString();
                            Users2 users = new Users2();
                            users.setUserId(userid);
                            Users2Id id = new Users2Id();
                            id.setDomainId(group.getDomainId() == null ? "B4C99EB0-18E6-439F-882A-9E4A11E1FF75" : group.getDomainId());
                            id.setLoginName(userJaxb.getUserEmail());
                            users.setId(id);
                            users.setUserEmail(userJaxb.getUserEmail());
                            users.setGroupId(group.getGroupId());
                            users.setPassword(RSAPassword.encryptedPwd(userJaxb.getPassword()));
                            users.setFirstName(userJaxb.getFirstName());
                            users.setLastName(userJaxb.getLastName());
                            users.setUserMobilePhone(userJaxb.getUserMobilePhone());
                            users.setCountryId(userJaxb.getCountryID());
                            users.setUserTypes2(userType);
                            users.setIsLockedOut(0);
                            users.setChangePwdRequired(0);
                            users.setActiveFromDate(gc.getTime());
                            Calendar cal = GregorianCalendar.getInstance();
                            cal.setTime(gc.getTime());
                            cal.add(Calendar.YEAR, 10);
                            users.setActiveToDate(cal.getTime());
                            users.setUpdatedDate(gc.getTime());
                            users.setCreatedDate(gc.getTime());

//                            Adding Address
                            Addresses address = null;
                            ObjectAddresses objectAddresses = null;
                            if (userJaxb.getAddress() != null) {
                                String addressid = UUID.randomUUID().toString();
                                address = new Addresses();
                                address.setAddressId(addressid);
                                address.setAddressName("Invoice address");
                                address.setAddressDescription("Invoice address");
                                address.setAddressType(addressType);

                                address.setAddressStreet(userJaxb.getAddress().getAddressStreet());
                                address.setAddressZip(userJaxb.getAddress().getAddressZip());
                                address.setAddressCity(userJaxb.getAddress().getAddressCity());
                                address.setCountry(country);
                                address.setAddressMobile(userJaxb.getUserMobilePhone());
                                address.setAddressEmail(userJaxb.getUserEmail());
                                address.setUpdatedDate(gc.getTime());
                                address.setCreatedDate(gc.getTime());
//                            Adding Data ObjectAddresses
                                objectAddresses = new ObjectAddresses();
                                ObjectAddressesId oID = new ObjectAddressesId();
                                oID.setObjectId(userid);
                                oID.setAddressTypeId(3);
                                objectAddresses.setId(oID);
                                objectAddresses.setAddressId(addressid);
                                objectAddresses.setCreatedDate(gc.getTime());
                                objectAddresses.setUpdatedDate(gc.getTime());

                            }

//                          Adding  UserRoleMemberships2

                            UserRoleMemberships2 urm = new UserRoleMemberships2();
                            UserRoleMemberships2Id urmid = new UserRoleMemberships2Id();
                            urmid.setUserId(userid);
                            urmid.setUserRoleId("1EE69077-F01F-4296-B439-0F3E535C3ACA");
                            urm.setId(urmid);
                            urm.setUserRoles2(roles);
                            urm.setCreatedDate(gc.getTime());
                            urm.setUpdatedDate(gc.getTime());
//                            UserProviderTypes
                            UserProviderTypes uPT = new UserProviderTypes();
                            uPT.setUserId(userid);
                            uPT.setProviderTypes(providerTypes);
                            uPT.setCreatedDate(gc.getTime());
                            uPT.setUpdatedDate(gc.getTime());
//                            UserAlias
                            ua.setUserId(userid);
                            ua.setFirstName(userJaxb.getFirstName());
                            ua.setLastName(userJaxb.getLastName());
                            ua.setUserEmail(userJaxb.getUserEmail());
                            ua.setUserMobilePhone(userJaxb.getUserMobilePhone());
                            ua.setIsEnabled(1);
                            ua.setCreditAmountCurrencyId(country.getCurrency().getCurrencyId());
                            ua.setCredits(100000);
                            ua.setFirstUseDate(gc.getTime());
//                          UserAliasDetails

                            if (uad != null) {
                                uad.setUserAliasId(ua.getUserAliasId());
                                uad.setIsCreditCard(1);
                                uad.setIsServiceKey(0);
                                uad.setIapproveTermsOfBusiness(1);
                                uad.setLastUpdatedByUserId(userid);
                                uad.setUpdatedDate(gc.getTime());
                            } else {
                                uad = new UserAliasDetails();
                                uad.setUserAliasId(ua.getUserAliasId());
                                uad.setIsCreditCard(1);
                                uad.setIsServiceKey(0);
                                uad.setIapproveTermsOfBusiness(1);
                                uad.setLastUpdatedByUserId(userid);
                                uad.setUpdatedDate(gc.getTime());
                            }
//                          OGMUserAlias
                            OgmuserAlias ogmuserAlias = new OgmuserAlias();
                            OgmuserAliasId ogmid = new OgmuserAliasId();
                            ogmid.setObjectGroupId(objectGroup.getObjectGroupId());
                            ogmid.setUserAliasId(ua.getUserAliasId());
                            ogmuserAlias.setId(ogmid);
                            ogmuserAlias.setObjectGroups(objectGroup);
                            ogmuserAlias.setCreatedDate(gc.getTime());
                            ogmuserAlias.setUpdatedDate(gc.getTime());
//                          UserTimeZones2
                            UserTimeZones2 timezone = new UserTimeZones2();
                            timezone.setUserId(userid);
                            timezone.setTimeZoneId(userJaxb.getTimeZone());
                            timezone.setUseDaylightSaving(0);
                            timezone.setCreatedDate(gc.getTime());
                            timezone.setUpdatedDate(gc.getTime());
                            customerDAO.addTCPNewUser(session, users, address, objectAddresses, urm, uPT, ua, uad, ogmuserAlias, timezone);

                        } else {
                            customer.getMsgStatus().setResponseCode(-4);
                            customer.getMsgStatus().setResponseText("Useralias is not valid ");
                            return customer;
                        }
                    } else {
                        customer.getMsgStatus().setResponseCode(-4);
                        customer.getMsgStatus().setResponseText("Useralias is not valid ");
                        return customer;
                    }

                } else {
                    customer.getMsgStatus().setResponseCode(-5);
                    customer.getMsgStatus().setResponseText("Organization alias is not valid ");
                    return customer;
                }

            } else {
                customer.getMsgStatus().setResponseCode(-1);
                customer.getMsgStatus().setResponseText("Users Tag should Not Empty");
                return customer;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            customer.getMsgStatus().setResponseCode(-1);
            customer.getMsgStatus().setResponseText("Error occurred");
            return customer;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return customer;
    }

    private TingcoCustomer updatePaymentMethodForFortum(String userId) {
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        Transaction tx = null;
        try {
            customer = tingcoCustomerxml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                session = HibernateUtil.getSessionFactory().openSession();
                tx = session.beginTransaction();
                GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                List<UserAlias> uaList = userDAO.getUserAliasByUserIDOrderByCreatedDate(session, userId);
                int count = 0;
                for (UserAlias ua : uaList) {
                    if (count == 50) {
                        break;
                    }
                    if (ua.getIsEnabled() != 1) {
                        ua.setIsEnabled(1);
                        ua.setLastUpdatedByUserId(userId);
                        ua.setUpdatedDate(gc.getTime());
                        session.saveOrUpdate(ua);
                        count++;
                    }
                }
                tx.commit();
            } else {
                customer.getMsgStatus().setResponseCode(-10);
                customer.getMsgStatus().setResponseText("User Session is not Valid");
                return customer;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            customer.getMsgStatus().setResponseCode(-1);
            customer.getMsgStatus().setResponseText("Error occurred");
            return customer;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
            return customer;
        }
    }

    private TingcoCustomer updateUserProfile(String content) {
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            customer = tingcoCustomerxml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                session = HibernateUtil.getSessionFactory().openSession();
                se.info24.tcpjaxb.TingcoCustomer tingcoCustomerPost = (se.info24.tcpjaxb.TingcoCustomer) JAXBManager.getInstance().unMarshall(content, se.info24.tcpjaxb.TingcoCustomer.class);
                List<se.info24.tcpjaxb.User> userJaxbList = tingcoCustomerPost.getUsers().getUser();
                if (!userJaxbList.isEmpty()) {
                    se.info24.tcpjaxb.User userJaxb = userJaxbList.get(0);
                    if (userJaxb.getUserID() != null) {
                        List<Users2> userList = userDAO.getUsers2ById(userJaxb.getUserID(), session);

                        if (!userList.isEmpty()) {//on Request of .NET team on Dated 06 Dec 2013
                            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                            Users2 user = userList.get(0);
                            user.setFirstName(userJaxb.getFirstName());
                            user.setLastName(userJaxb.getLastName());
                            user.setUserMobilePhone(userJaxb.getUserMobilePhone());
                            user.setUserEmail(userJaxb.getUserEmail());
                            user.setUpdatedDate(gc.getTime());
//                            if (!oAList.isEmpty()) {
//
//                                if (address != null) {
                            if (userJaxb.getAddress() != null) {
                                List<ObjectAddresses> oAList = customerDAO.getObjectAddressesByobjectId(session, userJaxb.getUserID());
                                Addresses address = null;
                                if (!oAList.isEmpty()) {
                                    address = (Addresses) userDAO.getAddress(oAList.get(0).getAddressId(), session);
                                    address.setAddressStreet(userJaxb.getAddress().getAddressStreet() == null ? "" : userJaxb.getAddress().getAddressStreet());
                                    address.setAddressZip(userJaxb.getAddress().getAddressZip() == null ? "" : userJaxb.getAddress().getAddressZip());
                                    address.setAddressCity(userJaxb.getAddress().getAddressCity() == null ? "" : userJaxb.getAddress().getAddressCity());
                                    address.setUpdatedDate(gc.getTime());
                                    if (customerDAO.updateUserProfile(session, user, address, null)) {
                                        return customer;
                                    } else {
                                        customer.getMsgStatus().setResponseCode(-1);
                                        customer.getMsgStatus().setResponseText("Error Occured");
                                        return customer;
                                    }
                                } else {
                                    address = new Addresses();
                                    String addressid = UUID.randomUUID().toString();
                                    AddressType addressType = userDAO.getAddresstypeById(session, 3);
                                    address = new Addresses();
                                    address.setAddressId(addressid);
                                    address.setAddressName("Invoice address");
                                    address.setAddressDescription("Invoice address");
                                    address.setAddressType(addressType);
                                    Country country = customerDAO.getCountryById(session, userJaxb.getCountryID());
                                    address.setCountry(country);
                                    address.setAddressMobile(userJaxb.getUserMobilePhone());
                                    address.setAddressEmail(userJaxb.getUserEmail());
                                    address.setUpdatedDate(gc.getTime());
                                    address.setCreatedDate(gc.getTime());
                                    address.setAddressStreet(userJaxb.getAddress().getAddressStreet() == null ? "" : userJaxb.getAddress().getAddressStreet());
                                    address.setAddressZip(userJaxb.getAddress().getAddressZip() == null ? "" : userJaxb.getAddress().getAddressZip());
                                    address.setAddressCity(userJaxb.getAddress().getAddressCity() == null ? "" : userJaxb.getAddress().getAddressCity());
//                            Adding Data ObjectAddresses
                                    ObjectAddresses objectAddresses = new ObjectAddresses();
                                    ObjectAddressesId oID = new ObjectAddressesId();
                                    oID.setObjectId(userJaxb.getUserID());
                                    oID.setAddressTypeId(3);
                                    objectAddresses.setId(oID);
                                    objectAddresses.setAddressId(addressid);
                                    objectAddresses.setCreatedDate(gc.getTime());
                                    objectAddresses.setUpdatedDate(gc.getTime());

                                    if (customerDAO.updateUserProfile(session, user, address, objectAddresses)) {
                                        return customer;
                                    } else {
                                        customer.getMsgStatus().setResponseCode(-1);
                                        customer.getMsgStatus().setResponseText("Error Occured");
                                        return customer;
                                    }

                                }

//                                    uPTList.isEmpty() ? null : uPTList.get(0);

                            } else {
                                if (customerDAO.updateUserProfile(session, user, null, null)) {
                                    return customer;
                                } else {
                                    customer.getMsgStatus().setResponseCode(-1);
                                    customer.getMsgStatus().setResponseText("Error Occured");
                                    return customer;
                                }

                            }

//                                } else {
//                                    if (customerDAO.updateUserProfile(session, user, null)) {
//                                        return customer;
//                                    } else {
//                                        customer.getMsgStatus().setResponseCode(-1);
//                                        customer.getMsgStatus().setResponseText("Error Occured");
//                                        return customer;
//                                    }
//
//                                }
//                            } else {
//                                if (customerDAO.updateUserProfile(session, user, null)) {
//                                    return customer;
//                                } else {
//                                    customer.getMsgStatus().setResponseCode(-1);
//                                    customer.getMsgStatus().setResponseText("Error Occured");
//                                    return customer;
//                                }
//                            }


                        } else {
                            customer.getMsgStatus().setResponseCode(-1);
                            customer.getMsgStatus().setResponseText("Data Not Found");
                            return customer;
                        }
                    } else {
                        customer.getMsgStatus().setResponseCode(-1);
                        customer.getMsgStatus().setResponseText("UserID Tag should Not Empty");
                        return customer;
                    }

                } else {
                    customer.getMsgStatus().setResponseCode(-1);
                    customer.getMsgStatus().setResponseText("Users Tag should Not Empty");
                    return customer;
                }
            } else {
                customer.getMsgStatus().setResponseCode(-10);
                customer.getMsgStatus().setResponseText("User Session is not Valid");
                return customer;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            customer.getMsgStatus().setResponseCode(-1);
            customer.getMsgStatus().setResponseText("Error occurred");
            return customer;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoCustomer getPurchaseTransactionHistory(String userId, String transactionStartTime, String transactionStopTime, String userAliasId) {
        Session ismOperationsession = null;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            customer = tingcoCustomerxml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                session = HibernateUtil.getSessionFactory().openSession();
                ismOperationsession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(userId, session);
                if (userTimeZones2 != null) {
                    if (!userAliasId.equals("")) {
                        userAliasId = userAliasId.split("/")[2];
                    } else {
                        userAliasId = null;
                    }
                    String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                    DateFormat lFormatinput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS ");
                    String gc_start_time = lFormat.format(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, lFormatinput.parse(transactionStartTime)));
                    String gc_stop_time = lFormat.format(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, lFormatinput.parse(transactionStopTime)));
                    List<TransactionResult> transactionResultList = new ArrayList<TransactionResult>();
                    if (userAliasId != null) {
                        transactionResultList = customerDAO.getTransactionResultByStartTimeAndUserIduserAliasId(ismOperationsession, gc_start_time, gc_stop_time, userId, userAliasId);
                    } else {
                        transactionResultList = customerDAO.getTransactionResultByStartTime(ismOperationsession, gc_start_time, gc_stop_time, userId);
                    }

                    if (!transactionResultList.isEmpty()) {
                        customer = tingcoCustomerxml.buildTransactionResults(customer, transactionResultList, timeZoneGMToffset, ismOperationsession, session);
                    } else {
                        customer.getMsgStatus().setResponseCode(-1);
                        customer.getMsgStatus().setResponseText("No TransactionResult found for the given input");
                        return customer;
                    }
                } else {
                    customer.getMsgStatus().setResponseCode(-1);
                    customer.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                    return customer;
                }
            } else {
                customer.getMsgStatus().setResponseCode(-10);
                customer.getMsgStatus().setResponseText("User Session is not Valid");
                return customer;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            customer.getMsgStatus().setResponseCode(-1);
            customer.getMsgStatus().setResponseText("Error occurred");
            return customer;
        } finally {
            if (ismOperationsession != null) {
                ismOperationsession.close();
            }
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return customer;
    }

    private TingcoCustomer getUserProfileForUserId(String addresstypeId, String userId) {
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            customer = tingcoCustomerxml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                session = HibernateUtil.getSessionFactory().openSession();
                UserDAO userDao = new UserDAO();
                ObjectAddresses objectAddress = userDao.getObjectAddressesByID(session, userId, addresstypeId);
                List<Users2> userList = userDao.getUsers2ById(userId, session);
                if (userList.isEmpty()) {
                    customer.getMsgStatus().setResponseCode(-1);
                    customer.getMsgStatus().setResponseText("No Data Found");
                    return customer;
                } else {
                    if (objectAddress == null) {
                        Addresses address = null;
                        customer = tingcoCustomerxml.buildGetUserProfileForUserId(customer, address, userList.get(0));
                        return customer;
                    } else {
                        Addresses address = (Addresses) userDao.getAddress(objectAddress.getAddressId(), session);
                        if (address == null) {
                            customer = tingcoCustomerxml.buildGetUserProfileForUserId(customer, address, userList.get(0));
                            return customer;
                        } else {
                            customer = tingcoCustomerxml.buildGetUserProfileForUserId(customer, address, userList.get(0));
                            return customer;
                        }
                    }
                }

            } else {
                customer.getMsgStatus().setResponseCode(-10);
                customer.getMsgStatus().setResponseText("User Session is not Valid");
                return customer;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            customer.getMsgStatus().setResponseCode(-1);
            customer.getMsgStatus().setResponseText("Error occurred");
            return customer;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoCustomer updatePaymentMethod(String providertypeid, String userId) {
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
            customer = tingcoCustomerxml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                session = HibernateUtil.getSessionFactory().openSession();
                UserProviderTypes UPT = customerDAO.getUserProviderTypesByUserID(session, userId);
                if (UPT != null) {
                    ProviderTypes pts = new ProviderTypes();
                    pts.setProviderTypeId(providertypeid);
                    UPT.setProviderTypes(pts);
                    UPT.setUpdatedDate(gc.getTime());
                    if (!customerDAO.updateUserProviderTypes(session, UPT)) {
                        customer.getMsgStatus().setResponseCode(-1);
                        customer.getMsgStatus().setResponseText("Error occured");
                        return customer;
                    } else {
                        return customer;
                    }
                } else {
                    UPT = new UserProviderTypes();
                    ProviderTypes pts = new ProviderTypes();
                    pts.setProviderTypeId(providertypeid);
                    UPT.setProviderTypes(pts);
                    UPT.setUserId(userId);
                    UPT.setCreatedDate(gc.getTime());
                    UPT.setUpdatedDate(gc.getTime());
                    if (!customerDAO.updateUserProviderTypes(session, UPT)) {
                        customer.getMsgStatus().setResponseCode(-1);
                        customer.getMsgStatus().setResponseText("Error occured");
                        return customer;
                    } else {
                        return customer;
                    }
                }

            } else {
                customer.getMsgStatus().setResponseCode(-10);
                customer.getMsgStatus().setResponseText("User Session is not Valid");
                return customer;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            customer.getMsgStatus().setResponseCode(-1);
            customer.getMsgStatus().setResponseText("Error occurred");
            return customer;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoCustomer getProviderTypes(String countryid) {
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            customer = tingcoCustomerxml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                session = HibernateUtil.getSessionFactory().openSession();
                List<ProviderTypeTranslations> PTT = customerDAO.getProviderTypeTranslationsByCountryId(session, Integer.valueOf(countryid));
                List<String> providerTypeIdList = new ArrayList<String>();
                for (ProviderTypeTranslations providerTypeTranslations : PTT) {
                    providerTypeIdList.add(providerTypeTranslations.getId().getProviderTypeId());
                }

                if (!providerTypeIdList.isEmpty()) {
                    List<ProviderTypes> PT = customerDAO.getProviderTypesByIds(session, providerTypeIdList);
                    customer = tingcoCustomerxml.buildgetProviderTypes(customer, PTT, PT);
                    return customer;
                } else {
                    customer.getMsgStatus().setResponseCode(-1);
                    customer.getMsgStatus().setResponseText("Data Not Found");
                    return customer;
                }

            } else {
                customer.getMsgStatus().setResponseCode(-10);
                customer.getMsgStatus().setResponseText("User Session is not Valid");
                return customer;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            customer.getMsgStatus().setResponseCode(-1);
            customer.getMsgStatus().setResponseText("Error occurred");
            return customer;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}

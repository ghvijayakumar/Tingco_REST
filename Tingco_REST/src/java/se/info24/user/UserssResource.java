/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.user;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
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
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.commandsjaxb.Command;
import se.info24.commandsjaxb.CommandParameter;
import se.info24.commandsjaxb.Commands;
import se.info24.commandsjaxb.TingcoCommands;
import se.info24.device.DeviceDAO;
import se.info24.group.GroupDAO;
import se.info24.jaxbUtil.TingcoAPIXML;
import se.info24.jaxbUtil.TingcoUtilsXML;
import se.info24.measurementjaxb.TingcoMeasurementTypes;
import se.info24.permission.PermissionDAO;
import se.info24.pojo.AddressType;
import se.info24.pojo.Addresses;
import se.info24.pojo.Country;
import se.info24.pojo.DeviceUsers;
import se.info24.pojo.FunctionAreas;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupTrusts;
import se.info24.pojo.ObjectAddresses;
import se.info24.pojo.ObjectAddressesId;
import se.info24.pojo.PermissionTranslations;
import se.info24.pojo.SupportOrganizationUsers;
import se.info24.pojo.SupportOrganizations;
import se.info24.pojo.TimeZones;
import se.info24.pojo.UserAlias;
import se.info24.pojo.UserLog;
import se.info24.pojo.UserPasswords;
import se.info24.pojo.UserRoleObjectPermissions2;
import se.info24.pojo.UserRoleObjectPermissions2Id;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.UserTimeZones2;
import se.info24.pojo.UserTimeZonesToActivate;
import se.info24.pojo.UserTypeTranslations2;
import se.info24.pojo.UserTypes2;
import se.info24.pojo.Users2;
import se.info24.pojo.Users2Id;
import se.info24.restUtil.RestUtilDAO;
import se.info24.tingcoEV.TingcoEV;
import se.info24.usersjaxb.TingcoUsers;
import se.info24.usersjaxb.User;
import se.info24.usersjaxb.UserRoles;
import se.info24.usersjaxb.Users;
import se.info24.util.DbManager;
import se.info24.util.HibernateUtil;
import se.info24.util.JAXBManager;
import se.info24.util.RESTDoc;
import se.info24.util.RSAPassword;
import se.info24.util.TCMUtil;
import se.info24.utiljaxb.Timezones;
import se.info24.utiljaxb.TingcoUtils;
import se.info24.utiljaxb.UserTimeZone;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/users")
public class UserssResource {

    /**
     * Creates a new instance of UserssResource
     */
    public UserssResource() {
    }
    @Context
    private UriInfo context;
    private String loginName;
    private String pwd;
    private String firstName;
    private String lastName;
    private String email;
    private String groupID;
    private String roleID;
    private String countryID;
    private String phone;
    @Context
    private HttpServletRequest request;
    UserSession2DAO userSession2DAO = new UserSession2DAO();
    UserDAO userDAO = new UserDAO();
    GroupDAO groupDAO = new GroupDAO();
    TingcoAPIXML tingcoAPIXML = new TingcoAPIXML();
    TingcoUtils tingcoUtils = new TingcoUtils();
    TingcoUtilsXML tingcoUtilXml = new TingcoUtilsXML();
    UserManager usermanager = new UserManager();
//    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Logger logger = Logger.getLogger(UserssResource.class);

    public UriInfo getContext() {
        return context;
    }

    public void setContext(UriInfo context) {
        this.context = context;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    /**
     * User_Add
     *
     * @param request
     * @param rest
     * @param userID
     * @param domainID
     * @param appID
     * @param emailID
     * @param groupID
     * @param firstName
     * @param lastName
     * @param nickName
     * @param countryID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/add/rest/{rest}/userid/{userid}/domainid/{domainid}/applicationid/{applicationid}/emailid/{emailid}/groupid/{groupid}/firstname/{firstname}/lastname/{lastname}/nickname/{nickname}/countryid/{countryid}")
    public TingcoUsers getXml_Add(@Context HttpServletRequest request, @PathParam("rest") String rest, @PathParam("userid") String userID, @PathParam("domainid") String domainID, @PathParam("applicationid") String appID, @PathParam("emailid") String emailID, @PathParam("groupid") String groupID,
            @PathParam("firstname") String firstName, @PathParam("lastname") String lastName, @PathParam("nickname") String nickName, @PathParam("countryid") String countryID) {

        return newUserToActivate(userID, domainID, appID, emailID, groupID, firstName, lastName, nickName, countryID, request);

    }

    /**
     * User_Add
     *
     * @param request
     * @param rest
     * @param userID
     * @param domainID
     * @param appID
     * @param emailID
     * @param groupID
     * @param firstName
     * @param lastName
     * @param nickName
     * @param countryID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/add/json/{json}/userid/{userid}/domainid/{domainid}/applicationid/{applicationid}/emailid/{emailid}/groupid/{groupid}/firstname/{firstname}/lastname/{lastname}/nickname/{nickname}/countryid/{countryid}")
    public TingcoUsers getJson_Add(@Context HttpServletRequest request, @PathParam("rest") String rest, @PathParam("userid") String userID, @PathParam("domainid") String domainID, @PathParam("applicationid") String appID, @PathParam("emailid") String emailID, @PathParam("groupid") String groupID,
            @PathParam("firstname") String firstName, @PathParam("lastname") String lastName, @PathParam("nickname") String nickName, @PathParam("countryid") String countryID) {

        return newUserToActivate(userID, domainID, appID, emailID, groupID, firstName, lastName, nickName, countryID, request);

    }

    /**
     * User_Delete
     *
     * @param request
     * @param userID
     * @return
     */
    @GET
    @Path("/delete/rest/{rest}/userid/{userid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "User_Delete", desc = "Used to delete a user", req_Params = {"UserID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUsers getXml_Delete(@Context HttpServletRequest request, @PathParam("userid") String userID) {
        return deletedUser(userID, request);
    }

    /**
     * User_Delete
     *
     * @param request
     * @param userID
     * @return
     */
    @GET
    @Path("/delete/json/{json}/userid/{userid}")
    @Produces("application/json")
    public TingcoUsers getJson_Delete(@Context HttpServletRequest request, @PathParam("userid") String userID) {
        return deletedUser(userID, request);
    }

    /**
     * DeleteUserByUserID
     *
     * @param userID
     * @return
     */
    @GET
    @Path("/deletebyuserid/rest/{rest}/userid/{userid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "User_Delete", desc = "Used to delete from user tables", req_Params = {"UserID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUsers getXml_DeleteUserTables(@PathParam("userid") String userId) {
        return deletedUserByUserID(userId);
    }

    /**
     * DeleteUserByUserID
     *
     * @param userID
     * @return
     */
    @GET
    @Path("/deletebyuserid/json/{json}/userid/{userid}")
    @Produces("application/json")
    public TingcoUsers getJson_DeleteUserTables(@PathParam("userid") String userId) {
        return deletedUserByUserID(userId);
    }

    /**
     * UpdateCountryByUserID
     *
     * @param userID
     * @param countryID
     * @return
     */
    @GET
    @Path("/updatecountrybyuserid/rest/{rest}/userid/{userid}/countryid/{countryid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "UpdateCountryByUserId", desc = "Used to update country id in users2", req_Params = {"UserID;UUID", "CountryID;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_UpdateCountryByUserID(@PathParam("userid") String userId, @PathParam("countryid") String countryId) {
        return UpdateCountryByUserID(userId, countryId);
    }

    /**
     * UpdateCountryByUserID
     *
     * @param userID
     * @param countryID
     * @return
     */
    @GET
    @Path("/updatecountrybyuserid/json/{json}/userid/{userid}/countryid/{countryid}")
    @Produces("application/json")
    public TingcoUsers getJson_UpdateCountryByUserID(@PathParam("userid") String userId, @PathParam("countryid") String countryId) {
        return UpdateCountryByUserID(userId, countryId);
    }

    /**
     * User_Update
     *
     * @param request
     * @param userID
     * @param firstName
     * @param lastName
     * @param email
     * @param phone
     * @param groupID
     * @return
     */
    @GET
    @Path("/update/rest/{rest}/userid/{userid}{firstname:(/firstname/[^/]+?)?}{lastname:(/lastname/[^/]+?)?}{email:(/email/[^/]+?)?}{mobilephone:(/mobilephone/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}")
    @Produces("application/xml")
    @RESTDoc(methodName = "User_Update", desc = "Used to update  the specified user’s information", req_Params = {"UserID;UUID"}, opt_Params = {"FirstName;String", "LastName;String", "Email;String", "MobilePhone;int", "GroupID;UUID"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUsers getXml_Update(@Context HttpServletRequest request, @PathParam("userid") String userID, @PathParam("firstname") String firstName, @PathParam("lastname") String lastName,
            @PathParam("email") String email, @PathParam("mobilephone") String phone, @PathParam("groupid") String groupID) {

        return updatedUser(userID, firstName, lastName, email, phone, groupID, request);
    }

    /**
     * User_Update
     *
     * @param request
     * @param userID
     * @param firstName
     * @param lastName
     * @param email
     * @param phone
     * @param groupID
     * @return
     */
    @GET
    @Path("/update/json/{json}/userid/{userid}{firstname:(/firstname/[^/]+?)?}{lastname:(/lastname/[^/]+?)?}{email:(/email/[^/]+?)?}{mobilephone:(/mobilephone/[^/]+?)?}{groupid:(/groupid/[^/]+?)?}")
    @Produces("application/json")
    public TingcoUsers getJson_Update(@Context HttpServletRequest request, @PathParam("userid") String userID, @PathParam("firstname") String firstName, @PathParam("lastname") String lastName,
            @PathParam("email") String email, @PathParam("mobilephone") String phone, @PathParam("groupid") String groupID) {
        return updatedUser(userID, firstName, lastName, email, phone, groupID, request);
    }

    /**
     * User_GetInfo
     *
     * @param request
     * @param userID
     * @param appID
     * @return
     */
    @GET
    @Path("/get/rest/{rest}/userid/{userid}{applicationid:(/applicationid/[^/]+?)?}")
    @Produces("application/xml")
    @RESTDoc(methodName = "User_GetInfo", desc = "Returns all the information of the user", req_Params = {"UserID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUsers getXml(@Context HttpServletRequest request, @PathParam("userid") String userID,
            @PathParam("applicationid") String appID) {
        if (!appID.equals("")) {
            appID = appID.split("/")[2];
        }
        return getUser(userID, appID, request);
    }

    /**
     * User_GetInfo
     *
     * @param request
     * @param userID
     * @param appID
     * @return
     */
    @GET
    @Path("/get/json/{json}/userid/{userid}{applicationid:(/applicationid/[^/]+?)?}")
    @Produces("application/json")
    public TingcoUsers getJson(@Context HttpServletRequest request, @PathParam("userid") String userID,
            @PathParam("applicationid") String appID) {
        if (!appID.equals("")) {
            appID = appID.split("/")[2];
        }
        return getUser(userID, appID, request);
    }

    /**
     * UpdateUserProfileDetails
     *
     * @param userId
     * @param firstName
     * @param lastName
     * @param countryId
     * @param usereMail
     * @param userMobilePhone
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/updateuserprofiledetails/rest/{rest}/loginname/{loginname}/domainid/{domainid}/userid/{userid}/firstname/{firstname}/lastname/{lastname}/countryid/{countryid}/emailid/{emailid}{usermobilephone:(/usermobilephone/[^/]+?)?}")
    @RESTDoc(methodName = "UpdateUserProfileDetails", desc = "Used to update userprofile", req_Params = {"LoginName;string", "DomainID;string", "UserID;string", "FirstName;string", "LastName;string", "CountryID;int", "UserEmail;string"}, opt_Params = {"UserMobilePhone;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_UpdateUserProfileDetails(@PathParam("loginname") String loginname, @PathParam("domainid") String domainid, @PathParam("userid") String userId, @PathParam("firstname") String firstName, @PathParam("lastname") String lastName, @PathParam("countryid") String countryId, @PathParam("emailid") String emailId, @PathParam("usermobilephone") String userMobilePhone) throws DatatypeConfigurationException {
        return updateUserProfileDetails(loginname, domainid, userId, firstName, lastName, countryId, emailId, userMobilePhone);
    }

    /**
     * UpdateUserProfileDetails
     *
     * @param userId
     * @param firstName
     * @param lastName
     * @param countryId
     * @param usereMail
     * @param userMobilePhone
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/updateuserprofiledetails/json/{json}/loginname/{loginname}/domainid/{domainid}/userid/{userid}/firstname/{firstname}/lastname/{lastname}/countryid/{countryid}/emailid/{emailid}{usermobilephone:(/usermobilephone/[^/]+?)?}")
    public TingcoUsers getJson_UpdateUserProfileDetails(@PathParam("loginname") String loginname, @PathParam("domainid") String domainid, @PathParam("userid") String userId, @PathParam("firstname") String firstName, @PathParam("lastname") String lastName, @PathParam("countryid") String countryId, @PathParam("emailid") String emailId, @PathParam("usermobilephone") String userMobilePhone) throws DatatypeConfigurationException {
        return updateUserProfileDetails(loginname, domainid, userId, firstName, lastName, countryId, emailId, userMobilePhone);
    }

    /**
     * UpdateUserInformation
     *
     * @param userId
     * @param firstName
     * @param lastName
     * @param countryId
     * @param usereMail
     * @param userMobilePhone
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/updateuserinformation/rest/{rest}/userid/{userid}/firstname/{firstname}/lastname/{lastname}/groupid/{groupid}/emailid/{emailid}/domainid/{domainid}/countryid/{countryid}/activefromdate/{activefromdate}/activetodate/{activetodate}/islockedout/{islockedout}/usertypeid/{usertypeid}{usermobilephone:(/usermobilephone/[^/]+?)?}{addressstreet:(/addressstreet/[^/]+?)?}{addressstreet2:(/addressstreet2/[^/]+?)?}{addresssuite:(/addresssuite/[^/]+?)?}{addresszip:(/addresszip/[^/]+?)?}{addresscity:(/addresscity/[^/]+?)?}{addressdesc:(/addressdesc/[^/]+?)?}{addressregion:(/addressregion/[^/]+?)?}{addressid:(/addressid/[^/]+?)?}{addresscountryid:(/addresscountryid/[^/]+?)?}")
    public TingcoUsers getXml_UpdateUserInformation(@PathParam("userid") String userId, @PathParam("firstname") String firstName, @PathParam("lastname") String lastName, @PathParam("groupid") String groupId, @PathParam("emailid") String emailId, @PathParam("domainid") String domainId, @PathParam("countryid") String countryId, @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate, @PathParam("islockedout") String isLockedOut, @PathParam("usertypeid") String userTypeId, @PathParam("usermobilephone") String userMobilePhone, @PathParam("addressstreet") String addressStreet, @PathParam("addressstreet2") String addressStreet2, @PathParam("addresssuite") String addressSuite, @PathParam("addresszip") String addressZip, @PathParam("addresscity") String addressCity, @PathParam("addressdesc") String addressDesc, @PathParam("addressregion") String addressRegion, @PathParam("addressid") String addressId, @PathParam("addresscountryid") String addressCountryId) throws DatatypeConfigurationException {
        return updateUserInformation(userId, firstName, lastName, groupId, emailId, domainId, countryId, activeFromDate, activeToDate, isLockedOut, userTypeId, userMobilePhone, addressStreet, addressStreet2, addressSuite, addressZip, addressCity, addressDesc, addressRegion, addressId, addressCountryId);
    }

    /**
     * UpdateUserInformation
     *
     * @param userId
     * @param firstName
     * @param lastName
     * @param countryId
     * @param usereMail
     * @param userMobilePhone
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/updateuserinformation/json/{json}/userid/{userid}/firstname/{firstname}/lastname/{lastname}/groupid/{groupid}/emailid/{emailid}/domainid/{domainid}/countryid/{countryid}/activefromdate/{activefromdate}/activetodate/{activetodate}/islockedout/{islockedout}/usertypeid/{usertypeid}{usermobilephone:(/usermobilephone/[^/]+?)?}{addressstreet:(/addressstreet/[^/]+?)?}{addressstreet2:(/addressstreet2/[^/]+?)?}{addresssuite:(/addresssuite/[^/]+?)?}{addresszip:(/addresszip/[^/]+?)?}{addresscity:(/addresscity/[^/]+?)?}{addressdesc:(/addressdesc/[^/]+?)?}{addressregion:(/addressregion/[^/]+?)?}{addressid:(/addressid/[^/]+?)?}{addresscountryid:(/addresscountryid/[^/]+?)?}")
    public TingcoUsers getJson_UpdateUserInformation(@PathParam("userid") String userId, @PathParam("firstname") String firstName, @PathParam("lastname") String lastName, @PathParam("groupid") String groupId, @PathParam("emailid") String emailId, @PathParam("domainid") String domainId, @PathParam("countryid") String countryId, @PathParam("activefromdate") String activeFromDate, @PathParam("activetodate") String activeToDate, @PathParam("islockedout") String isLockedOut, @PathParam("usertypeid") String userTypeId, @PathParam("usermobilephone") String userMobilePhone, @PathParam("addressstreet") String addressStreet, @PathParam("addressstreet2") String addressStreet2, @PathParam("addresssuite") String addressSuite, @PathParam("addresszip") String addressZip, @PathParam("addresscity") String addressCity, @PathParam("addressdesc") String addressDesc, @PathParam("addressregion") String addressRegion, @PathParam("addressid") String addressId, @PathParam("addresscountryid") String addressCountryId) throws DatatypeConfigurationException {
        return updateUserInformation(userId, firstName, lastName, groupId, emailId, domainId, countryId, activeFromDate, activeToDate, isLockedOut, userTypeId, userMobilePhone, addressStreet, addressStreet2, addressSuite, addressZip, addressCity, addressDesc, addressRegion, addressId, addressCountryId);

    }

    /**
     * User_GetInfo
     *
     * @param request
     * @param content
     * @return
     */
    @POST
    @Path("/get/rest/{rest}")
    @Consumes("application/xml")
    @Produces("application/xml")
    public TingcoUsers postXml(@Context HttpServletRequest request, String content) {
        TingcoCommands comm = (TingcoCommands) JAXBManager.getInstance().unMarshall(content, TingcoCommands.class);
        String appID = "";//TODO change accordingly...for time being added
        Commands cmds = comm.getCommands();
        List<Command> cmd = cmds.getCommand();
        String userID = null;
        for (Command c : cmd) {
            if (c.getCommandName().equalsIgnoreCase("User_GetInfo")) {
                List<CommandParameter> parameters = c.getCommandParameter();
                for (CommandParameter param : parameters) {
                    if (param.getParameterName().equalsIgnoreCase("UserID")) {
                        userID = param.getValue();
                        break;
                    }
                }
            }
        }
        return getUser(userID, appID, request);
    }

    /**
     * User_GetRoles
     *
     * @param request
     * @param userID
     * @return
     */
    @GET
    @Path("/getroles/rest/{rest}/userid/{userid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "User_GetRoles", desc = "Return's a list of all Roles that the user has", req_Params = {"UserID;UUID"}, opt_Params = {"MaxItems;int"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUsers getXml_Roles(@Context HttpServletRequest request, @PathParam("userid") String userID) {
        return userRoles(userID, request);
    }

    /**
     * User_GetRoles
     *
     * @param request
     * @param userID
     * @return
     */
    @GET
    @Path("/getroles/json/{json}/userid/{userid}")
    @Produces("application/json")
    public TingcoUsers getJson_Roles(@Context HttpServletRequest request, @PathParam("userid") String userID) {
        return userRoles(userID, request);
    }

    /**
     * GetAllUsers
     *
     * @param request
     * @param domainId
     * @return
     */
    @GET
    @Path("/getallusers/rest/{rest}/domainid/{domainid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetAllUsers", desc = "Returns all user's", req_Params = {"DomainID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_All(@Context HttpServletRequest request, @PathParam("domainid") String domainId) {
        return allUSers(domainId, request);
    }

    /**
     * GetAllUsers
     *
     * @param request
     * @param domainId
     * @return
     */
    @GET
    @Path("/getallusers/json/{json}/domainid/{domainid}")
    @Produces("application/json")
    public TingcoUsers getJson_All(@Context HttpServletRequest request, @PathParam("domainid") String domainId) {
        return allUSers(domainId, request);
    }

    /**
     * User_Activate
     *
     * @param request
     * @param userID
     * @param domainID
     * @param appID
     * @return
     */
    @GET
    @Path("/activate/rest/{rest}/userid/{userid}/domainid/{domainid}/applicationid/{applicationid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "User_Activate", desc = "Used to Activate a new user", req_Params = {"UserID;UUID", "ApplicationID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_Activate(@Context HttpServletRequest request, @PathParam("userid") String userID, @PathParam("domainid") String domainID, @PathParam("applicationid") String appID) {
        return userActivate(userID, domainID, appID, request);
    }

    /**
     * User_Activate
     *
     * @param request
     * @param userID
     * @param domainID
     * @param appID
     * @return
     */
    @GET
    @Path("/activate/json/{json}/userid/{userid}/domainid/{domainid}")
    @Produces("application/json")
    public TingcoUsers getJson_Activate(@Context HttpServletRequest request, @PathParam("userid") String userID, @PathParam("domainid") String domainID, @PathParam("applicationid") String appID) {
        return userActivate(userID, domainID, appID, request);
    }

    /**
     * User_ExpireSession
     *
     * @param request
     * @return
     */
    @GET
    @Path("/expire/rest/{rest}")
    @Produces("application/xml")
    @RESTDoc(methodName = "User_ExpireSession", desc = "Expires the User Session", req_Params = {"rest;v1"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_Expire(@Context HttpServletRequest request) {
        return expireSession(request);
    }

    /**
     * User_ExpireSession
     *
     * @param request
     * @return
     */
    @GET
    @Path("/expire/json/{json}")
    @Produces("application/json")
    public TingcoUsers getJson_Expire(@Context HttpServletRequest request) {
        return expireSession(request);
    }

    /**
     * User_Invite
     *
     * @param request
     * @param invitedBy
     * @param invitedTo
     * @param domainID
     * @param appID
     * @return
     */
    @GET
    @Path("/invite/rest/{rest}/invitedbyuserid/{invitedbyuserid}/invitedtouserid/{invitedtouserid}/domainid/{domainid}/applicationid/{applicationid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "UserInvite", desc = "Used for inviting new user", req_Params = {"InvitedByUserID;UUID", "InvitedToUserID;UUID", "DomainID;DomainID", "ApplicationID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUtils getXml_Invite(@Context HttpServletRequest request, @PathParam("invitedbyuserid") String invitedBy, @PathParam("invitedtouserid") String invitedTo, @PathParam("domainid") String domainID, @PathParam("applicationid") String appID) {
        return userInvitation(invitedBy, invitedTo, domainID, appID, request);
    }

    /**
     * User_Invite
     *
     * @param request
     * @param invitedBy
     * @param invitedTo
     * @param domainID
     * @param appID
     * @return
     */
    @GET
    @Path("/invite/json/{json}/invitedbyuserid/{invitedbyuserid}/invitedtouserid/{invitedtouserid}/domainid/{domainid}/applicationid/{applicationid}")
    @Produces("application/json")
    public TingcoUtils getJson_Invite(@Context HttpServletRequest request, @PathParam("invitedbyuserid") String invitedBy, @PathParam("invitedtouserid") String invitedTo, @PathParam("domainid") String domainID, @PathParam("applicationid") String appID) {
        return userInvitation(invitedBy, invitedTo, domainID, appID, request);
    }

    /**
     * User_Lock
     *
     * @param request
     * @param userID
     * @param isLocked
     * @return
     */
    @GET
    @Path("/lock/rest/{rest}/userid/{userid}/islocked/{islocked}")
    @Produces("application/xml")
    @RESTDoc(methodName = "Lock_User", desc = "Used to lock a user", req_Params = {"UserID;UUID", "IsLocked;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_Lock(@Context HttpServletRequest request, @PathParam("userid") String userID,
            @PathParam("islocked") String isLocked) {
        return lockedUser(userID, isLocked, request);
    }

    /**
     * User_Lock
     *
     * @param request
     * @param userID
     * @param isLocked
     * @return
     */
    @GET
    @Path("/lock/json/{json}/userid/{userid}/islocked/{islocked}")
    @Produces("application/json")
    public TingcoUsers getJson_Lock(@Context HttpServletRequest request, @PathParam("userid") String userID,
            @PathParam("islocked") String isLocked) {
        return lockedUser(userID, isLocked, request);
    }

    /**
     * GetUsageRecordsByUserAlias
     *
     * @param userAlias
     * @param month
     * @param year
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/usagerecords/rest/{rest}/useralias/{useralias}/month/{month}/year/{year}")
    @RESTDoc(methodName = "GetUsageRecordsByUserAlias", desc = "Reteieves usage records", req_Params = {"UserAlias;UUID"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoEV getXml(@PathParam("useralias") String userAlias, @PathParam("month") String month, @PathParam("year") String year) throws DatatypeConfigurationException {
        return usagerecords(userAlias, month, year);
    }

    /**
     * GetUsageRecordsByUserAlias
     *
     * @param userAlias
     * @param month
     * @param year
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/usagerecords/json/{json}/useralias/{useralias}/month/{month}/year/{year}")
    @RESTDoc(methodName = "GetUsageRecordsByUserAlias", desc = "Reteieves usage records", req_Params = {"UserAlias;UUID"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoEV getJson(@PathParam("useralias") String userAlias, @PathParam("month") String month, @PathParam("year") String year) throws DatatypeConfigurationException {
        return usagerecords(userAlias, month, year);
    }

    /**
     * AddUserTimeZones
     *
     * @param userID
     * @param timezoneID
     * @param useDayLightSaving
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/addtimezones/rest/{rest}/userid/{userid}/timezoneid/{timezoneid}/usedaylightsaving/{usedaylightsaving}")
    @RESTDoc(methodName = "User_AddTimeZone", desc = "To Add UserTimezone", req_Params = {"UserID;UUID", "TimezoneID;UUID", "UseDayLightSaving;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUsers getXml_AddTimeZones(@PathParam("userid") String userID, @PathParam("timezoneid") String timezoneID, @PathParam("usedaylightsaving") String useDayLightSaving) {
        return addUserTimeZone(userID, timezoneID, useDayLightSaving);
    }

    /**
     * AddUserTimeZones
     *
     * @param userID
     * @param timezoneID
     * @param useDayLightSaving
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/addtimezones/json/{json}/userid/{userid}/timezoneid/{timezoneid}/usedaylightsaving/{usedaylightsaving}")
    public TingcoUsers getJson_AddTimeZones(@PathParam("userid") String userID, @PathParam("timezoneid") String timezoneID, @PathParam("usedaylightsaving") String useDayLightSaving) {
        return addUserTimeZone(userID, timezoneID, useDayLightSaving);
    }

    /**
     * AddUserTimeZones
     *
     * @param userID
     * @param timezoneID
     * @param useDayLightSaving
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/addtimezones/rest/{rest}/userid/{userid}/timezoneid/{timezoneid}/usedaylightsaving/{usedaylightsaving}")
    public TingcoUsers postXml_AddTimeZones(@PathParam("userid") String userID, @PathParam("timezoneid") String timezoneID, @PathParam("usedaylightsaving") String useDayLightSaving) {
        return addUserTimeZone(userID, timezoneID, useDayLightSaving);
    }

    /**
     * AddUserTimeZones
     *
     * @param userID
     * @param timezoneID
     * @param useDayLightSaving
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/addtimezones/json/{json}/userid/{userid}/timezoneid/{timezoneid}/usedaylightsaving/{usedaylightsaving}")
    public TingcoUsers postJson_AddTimeZones(@PathParam("userid") String userID, @PathParam("timezoneid") String timezoneID, @PathParam("usedaylightsaving") String useDayLightSaving) {
        return addUserTimeZone(userID, timezoneID, useDayLightSaving);
    }

    /**
     * GetUserTimeZones
     *
     * @param userId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/gettimezones/rest/{rest}/userid/{userid}")
    @RESTDoc(methodName = "GetUserTimeZOnes", desc = "To Get UserTimeZones", req_Params = {"UserID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_GetUserTimeZones(@PathParam("userid") String userId) {
        return getUserTimeZones(userId);
    }

    /**
     * GetUserTimeZones
     *
     * @param userId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/gettimezones/json/{json}/userid/{userid}")
    public TingcoUtils getJson_GetUserTimeZones(@PathParam("userid") String userId) {
        return getUserTimeZones(userId);
    }

    /**
     * GetUserTypes
     *
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getusertypes/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetUserTypes", desc = "Get the UserTypes", req_Params = {"CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_GetUserTypes(@PathParam("countryid") String countryId) {
        return getUserTypes(countryId);
    }

    /**
     * GetUserTypes
     *
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getusertypes/json/{json}/countryid/{countryid}")
    public TingcoUsers getJSON_GetUserTypes(@PathParam("countryid") String countryId) {
        return getUserTypes(countryId);
    }

    /**
     * Add_NewUser in Users2 table
     *
     * @param firstName
     * @param lastName
     * @param loginName
     * @param emailId
     * @param phone
     * @param groupId
     * @param domainId
     * @param pwd
     * @param isLockedOut
     * @param changePwd
     * @param countryId
     * @param activeFromDate
     * @param activeToDate
     * @param userTypeId
     * @return
     * @throws java.text.ParseException
     */
    @GET
    @Produces("application/xml")
    @Path("/adduser/rest/{rest}/firstname/{firstname}/lastname/{lastname}/loginname/{loginname}/emailid/{emailid}/usermobilephone/{usermobilephone}/groupid/{groupid}/domainid/{domainid}/password/{password}/islockedout/{islockedout}/changepasswordrequired/{changepasswordrequired}/countryid/{countryid}/activefromdate/{activefromdate}/activetodate/{activetodate}/usertypeid/{usertypeid}{addressstreet:(/addressstreet/[^/]+?)?}{addressstreet2:(/addressstreet2/[^/]+?)?}{addresssuite:(/addresssuite/[^/]+?)?}{addresszip:(/addresszip/[^/]+?)?}{addresscity:(/addresscity/[^/]+?)?}{addressdesc:(/addressdesc/[^/]+?)?}{addressregion:(/addressregion/[^/]+?)?}{addresscountryid:(/addresscountryid/[^/]+?)?}")
    @RESTDoc(methodName = "AddUser", desc = "Add New User in Users2 ", req_Params = {"FirstName;String", "LastName;String", "LoginName;String", "EmailID;String", "UserMobilePhone;String", "GroupID;UUID", "DomainID;UUID", "Password;String", "IsLockedOut;int", "ChnagePasswordRequired;int", "countryID;int", "ActiveFromDate;String", "ActiveToDate;String", "UserTypeID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_addUsers2(@PathParam("firstname") String firstName, @PathParam("lastname") String lastName, @PathParam("loginname") String loginName, @PathParam("emailid") String emailId,
            @PathParam("usermobilephone") String phone, @PathParam("groupid") String groupId, @PathParam("domainid") String domainId, @PathParam("password") String pwd,
            @PathParam("islockedout") String isLockedOut, @PathParam("changepasswordrequired") String changePwd, @PathParam("countryid") String countryId, @PathParam("activefromdate") String activeFromDate,
            @PathParam("activetodate") String activeToDate, @PathParam("usertypeid") String userTypeId, @PathParam("addressstreet") String addressStreet, @PathParam("addressstreet2") String addressStreet2, @PathParam("addresssuite") String addressSuite, @PathParam("addresszip") String addressZip, @PathParam("addresscity") String addressCity, @PathParam("addressdesc") String addressDesc, @PathParam("addressregion") String addressRegion, @PathParam("addresscountryid") String addressCountryId) throws ParseException {
        return addUsers2(firstName, lastName, loginName, emailId, phone, groupId, domainId, pwd, isLockedOut, changePwd, countryId, activeFromDate, activeToDate, userTypeId, addressStreet, addressStreet2, addressSuite, addressZip, addressCity, addressDesc, addressRegion, addressCountryId);
    }

    /**
     * Add_NewUser in Users2 table
     *
     * @param firstName
     * @param lastName
     * @param loginName
     * @param emailId
     * @param phone
     * @param groupId
     * @param domainId
     * @param pwd
     * @param isLockedOut
     * @param changePwd
     * @param countryId
     * @param activeFromDate
     * @param activeToDate
     * @param userTypeId
     * @return
     * @throws java.text.ParseException
     */
    @GET
    @Produces("application/json")
    @Path("/adduser/json/firstname/{firstname}/lastname/{lastname}/loginname/{loginname}/emailid/{emailid}/usermobilephone/{usermobilephone}/groupid/{groupid}/domainid/{domainid}/password/{password}/islockedout/{islockedout}/changepasswordrequired/{changepasswordrequired}/countryid/{countryid}/activefromdate/{activefromdate}/activetodate/{activetodate}/usertypeid/{usertypeid}{addressstreet:(/addressstreet/[^/]+?)?}{addressstreet2:(/addressstreet2/[^/]+?)?}{addresssuite:(/addresssuite/[^/]+?)?}{addresszip:(/addresszip/[^/]+?)?}{addresscity:(/addresscity/[^/]+?)?}{addressdesc:(/addressdesc/[^/]+?)?}{addressregion:(/addressregion/[^/]+?)?}{addresscountryid:(/addresscountryid/[^/]+?)?}")
    @RESTDoc(methodName = "AddUser", desc = "Add New User in Users2 ", req_Params = {"FirstName;String", "LastName;String", "LoginName;String", "EmailID;String", "UserMobilePhone;String", "GroupID;UUID", "DomainID;UUID", "Password;String", "IsLockedOut;int", "ChnagePasswordRequired;int", "countryID;int", "ActiveFromDate;String", "ActiveToDate;String", "UserTypeID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getJson_addUsers2(@PathParam("firstname") String firstName, @PathParam("lastname") String lastName, @PathParam("loginname") String loginName, @PathParam("emailid") String emailId,
            @PathParam("usermobilephone") String phone, @PathParam("groupid") String groupId, @PathParam("domainid") String domainId, @PathParam("password") String pwd,
            @PathParam("islockedout") String isLockedOut, @PathParam("changepasswordrequired") String changePwd, @PathParam("countryid") String countryId, @PathParam("activefromdate") String activeFromDate,
            @PathParam("activetodate") String activeToDate, @PathParam("usertypeid") String userTypeId, @PathParam("addressstreet") String addressStreet, @PathParam("addressstreet2") String addressStreet2, @PathParam("addresssuite") String addressSuite, @PathParam("addresszip") String addressZip, @PathParam("addresscity") String addressCity, @PathParam("addressdesc") String addressDesc, @PathParam("addressregion") String addressRegion, @PathParam("addresscountryid") String addressCountryId) throws ParseException {
        return addUsers2(firstName, lastName, loginName, emailId, phone, groupId, domainId, pwd, isLockedOut, changePwd, countryId, activeFromDate, activeToDate, userTypeId, addressStreet, addressStreet2, addressSuite, addressZip, addressCity, addressDesc, addressRegion, addressCountryId);
    }

    /**
     * Add_NewUserPassword
     *
     * @param userId
     * @param domainId
     * @param pwd
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/savepassword/rest/{rest}/userid/{userid}/domainid/{domainid}/password/{password}")
    @RESTDoc(methodName = "New_SavePassword", desc = "Save the Password to UserPasswords", req_Params = {"UserID;UUID", "DomainID;UUID", "Password;String"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_AddUsers2(@PathParam("userid") String userId, @PathParam("domainid") String domainId, @PathParam("password") String pwd) {
        return addUserPassword(userId, domainId, pwd);
    }

    /**
     * Add_NewUserPassword
     *
     * @param userId
     * @param domainId
     * @param pwd
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/savepassword/json/{json}/userid/{userid}/domainid/{domainid}/password/{password}")
    public TingcoUsers getJSON_AddUsers2(@PathParam("userid") String userId, @PathParam("domainid") String domainId, @PathParam("password") String pwd) {
        return addUserPassword(userId, domainId, pwd);
    }

    /**
     * AddUserTimeZones2
     *
     * @param userID
     * @param timezoneID
     * @param useDayLightSaving
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/addtimezones2/rest/{rest}/userid/{userid}/timezoneid/{timezoneid}/usedaylightsaving/{usedaylightsaving}")
    @RESTDoc(methodName = "AddUserTimeZones2", desc = "Add Data into UserTimeZones2", req_Params = {"UserID;UUID", "TimeZoneID;String", "UseDayLightSaving;String"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUtils getXml_AddTimeZones2(@PathParam("userid") String userID, @PathParam("timezoneid") String timezoneID, @PathParam("usedaylightsaving") String useDayLightSaving) {
        return addUserTimeZones2(userID, timezoneID, useDayLightSaving);
    }

    /**
     * AddUserTimeZones2
     *
     * @param userID
     * @param timezoneID
     * @param useDayLightSaving
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/addtimezones2/json/{json}/userid/{userid}/timezoneid/{timezoneid}/usedaylightsaving/{usedaylightsaving}")
    public TingcoUtils getJson_AddTimeZones2(@PathParam("userid") String userID, @PathParam("timezoneid") String timezoneID, @PathParam("usedaylightsaving") String useDayLightSaving) {
        return addUserTimeZones2(userID, timezoneID, useDayLightSaving);
    }

    /**
     * UsersByGroupId
     *
     * @param groupid
     * @param searchString
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/get/rest/{rest}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "UsersByGroupId", desc = "Used to get Users ByGroupId", req_Params = {"GroupID;UUID", "SearchString;String"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXmlUser(@PathParam("groupid") String groupid, @PathParam("searchstring") String searchString) {
        return getUsersByGroupId(groupid, searchString);
    }

    /**
     * UsersByGroupId
     *
     * @param groupid
     * @param searchString
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/get/json/{json}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoUsers getJsonUser(@PathParam("groupid") String groupid, @PathParam("searchstring") String searchString) {
        return getUsersByGroupId(groupid, searchString);
    }

    /**
     * GetUserAliasByUserID
     *
     * @param userid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getuseralias/rest/{rest}/userid/{userid}")
    @RESTDoc(methodName = "GetUserAlias", desc = "Get the UserAlias", req_Params = {"UserID;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_UserAlias(@PathParam("userid") String userId) throws DatatypeConfigurationException {
        return getUserAliasByUserId(userId);
    }

    /**
     * GetUserAliasByUserID
     *
     * @param userid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getuseralias/json/{json}/userid/{userid}")
    public TingcoUsers getJson_UserAlias(@PathParam("userid") String userId) throws DatatypeConfigurationException {
        return getUserAliasByUserId(userId);
    }

    /**
     * GetUsers2DetailsbyUserID
     *
     * @param groupid
     * @param countryid
     * @param searchfilter
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getusers2detailsbyuserid/rest/{rest}/userid/{userid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetUsers2DetailsbyUserID", desc = "Used to Get Users2Details by UserID", req_Params = {"UserID;string", "CountryID;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_Users2DetailsbyUserID(@Context HttpServletRequest request, @PathParam("userid") String userID, @PathParam("countryid") String countryid) {
        return getUsers2DetailsbyUserID(userID, countryid);
    }

    /**
     * GetUsers2DetailsbyUserID
     *
     * @param groupid
     * @param countryid
     * @param searchfilter
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getusers2detailsbyuserid/json/{json}/userid/{userid}/countryid/{countryid}")
    public TingcoUsers getJson_Users2DetailsbyUserID(@Context HttpServletRequest request, @PathParam("userid") String userID, @PathParam("countryid") String countryid) {
        return getUsers2DetailsbyUserID(userID, countryid);
    }

    /**
     * GetUsersDetails
     *
     * @param groupid
     * @param countryid
     * @param searchfilter
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getusersdetails/rest/{rest}/countryid/{countryid}{searchfilter:(/searchfilter/[^/]+?)?}{groupname:(/groupname/[^/]+?)?}")
    @RESTDoc(methodName = "GetUsersDetails", desc = "Get the UsersDetails", req_Params = {"CountryID;int", "SearchFilter;string"}, opt_Params = {"GroupID;string"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_GetUsersDetails(@PathParam("countryid") int countryId, @PathParam("searchfilter") String searchFilter, @PathParam("groupname") String groupName) throws DatatypeConfigurationException {
        return getUsersDetails(countryId, searchFilter, groupName);
    }

    /**
     * GetUsersDetails
     *
     * @param groupid
     * @param countryid
     * @param searchfilter
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getusersdetails/json/{json}/countryid/{countryid}{searchfilter:(/searchfilter/[^/]+?)?}{groupname:(/groupname/[^/]+?)?}")
    public TingcoUsers getJson_GetUsersDetails(@PathParam("countryid") int countryId, @PathParam("searchfilter") String searchFilter, @PathParam("groupname") String groupName) throws DatatypeConfigurationException {
        return getUsersDetails(countryId, searchFilter, groupName);
    }

    /**
     * GetUsers2byGroupId
     *
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getusers2listbygroupid/rest/{rest}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetUsersList", desc = "Get the UsersList", req_Params = {"GroupID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_getUsersListbyGroupId(@PathParam("groupid") String groupId, @PathParam("searchstring") String searchstring) throws DatatypeConfigurationException {
        return getUsers2ListbyGroupId(groupId, searchstring);
    }

    /**
     * GetUsers2byGroupId
     *
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getusers2listbygroupid/json/{json}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoUsers getJson_getUsersListbyGroupId(@PathParam("groupid") String groupId, @PathParam("searchstring") String searchstring) throws DatatypeConfigurationException {
        return getUsers2ListbyGroupId(groupId, searchstring);
    }

    @GET
    @Produces("application/xml")
    @Path("/getassignedusers/rest/{rest}/supportorganizationid/{supportorganizationid}/userid/{userid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetAssignedUsers", desc = "Get the assignedUsers", req_Params = {"GroupID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_getAssignedUsers(@PathParam("supportorganizationid") String supportOrganizationId, @PathParam("userid") String userId, @PathParam("searchstring") String searchstring) throws DatatypeConfigurationException {
        return getAssignedUsers(supportOrganizationId, userId, searchstring);
    }

    @GET
    @Produces("application/json")
    @Path("/getassignedusers/json/{json}/supportorganizationid/{supportorganizationid}/userid/{userid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetAssignedUsers", desc = "Get the assignedUsers", req_Params = {"GroupID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getJson_getAssignedUsers(@PathParam("supportorganizationid") String supportOrganizationId, @PathParam("userid") String userId, @PathParam("searchstring") String searchstring) throws DatatypeConfigurationException {
        return getAssignedUsers(supportOrganizationId, userId, searchstring);
    }

    /**
     *
     * @param authenticationTokenId
     * @return
     */
    @GET
    @Path("/loggedinusersessionisvalid/rest/{rest}")
    @Produces("application/xml")
    @RESTDoc(methodName = "UserSession_GetInfo", desc = "Returns user session is valid orr not", req_Params = {"authenticationtokenid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUsers getXml() {
        return getUserSession();
    }

    @POST
    @Path("/loggedinusersessionisvalid/rest/{rest}")
    @Produces("application/xml")
    @Consumes("application/xml")
    @RESTDoc(methodName = "UserSession_GetInfo", desc = "Returns user session is valid orr not", req_Params = {"authenticationtokenid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUsers postXml() {
        return getUserSession();
    }

    @POST
    @Path("/loggedinusersessionisvalid/json/{json}")
    @Produces("application/json")
    @Consumes("application/xml")
    @RESTDoc(methodName = "UserSession_GetInfo", desc = "Returns user session is valid orr not", req_Params = {"authenticationtokenid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUsers postjson() {
        return getUserSession();
    }

    /**
     *
     * @param authenticationTokenId
     * @return
     */
    @GET
    @Path("/loggedinusersessionisvalid/json/{json}")
    @Produces("application/json")
    @RESTDoc(methodName = "UserSession_GetInfo", desc = "Returns user session is valid orr not", req_Params = {"authenticationtokenid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUsers getJson() {
        return getUserSession();
    }

    @GET
    @Path("/getuserlistforuserlog/rest/{rest}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    @Produces("application/xml")
    @RESTDoc(methodName = "UserSession_GetInfo", desc = "Returns user session is valid orr not", req_Params = {"authenticationtokenid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUsers getXml_UserListForUserLog(@PathParam("groupid") String groupId, @PathParam("countryid") String countryId, @PathParam("searchstring") String searchString) {
        return getUserListForUserLog(groupId, countryId, searchString);
    }

    @GET
    @Path("/getuserlistforuserlog/json/{json}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    @Produces("application/json")
    @RESTDoc(methodName = "UserSession_GetInfo", desc = "Returns user session is valid orr not", req_Params = {"authenticationtokenid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUsers getJson_UserListForUserLog(@PathParam("groupid") String groupId, @PathParam("countryid") String countryId, @PathParam("searchstring") String searchString) {
        return getUserListForUserLog(groupId, countryId, searchString);
    }

    /**
     * GetUsersList
     *
     * @param countryid
     * @param searchfilter
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getuserslist/rest/{rest}/countryid/{countryid}{searchfilter:(/searchfilter/[^/]+?)?}")
    @RESTDoc(methodName = "GetUsersList", desc = "Get the UsersList", req_Params = {"CountryID;int", "SearchFilter;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_GetUsersList(@PathParam("countryid") int countryId, @PathParam("searchfilter") String searchFilter) throws DatatypeConfigurationException {
        return getUsersList(countryId, searchFilter);
    }

    /**
     * GetUsersList
     *
     * @param countryid
     * @param searchfilter
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getuserslist/json/{json}/countryid/{countryid}{searchfilter:(/searchfilter/[^/]+?)?}")
    public TingcoUsers getJson_GetUsersList(@PathParam("countryid") int countryId, @PathParam("searchfilter") String searchFilter) throws DatatypeConfigurationException {
        return getUsersList(countryId, searchFilter);
    }

    /**
     * GetBlackList
     *
     * @param userKey
     * @param keyType
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getblacklist/rest/{rest}/groupid/{groupid}/countryid/{countryid}{startvalue:(/startvalue/[^/]+?)?}{useraliastype:(/useraliastype/[^/]+?)?}")
    @RESTDoc(methodName = "GetBlackList", desc = "Get BlackList", req_Params = {"userKey;string", "keyType;string", "groupid;string", "countryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_getBlackList(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("startvalue") String startValue, @PathParam("useraliastype") String userAliasType) throws DatatypeConfigurationException {
        return getBlackList(groupId, startValue, userAliasType, countryId);
    }

    /**
     * GetBlackList
     *
     * @param userKey
     * @param keyType
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getblacklist/json/{json}/groupid/{groupid}/countryid/{countryid}{startvalue:(/startvalue/[^/]+?)?}{useraliastype:(/useraliastype/[^/]+?)?}")
    public TingcoUsers getJson_getBlackList(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId, @PathParam("startvalue") String startValue, @PathParam("useraliastype") String userAliasType) throws DatatypeConfigurationException {
        return getBlackList(groupId, startValue, userAliasType, countryId);
    }

    @POST
    @Path("/getuserlogdetails/rest/{rest}")
    @Produces("application/xml")
    @Consumes("application/xml")
    @RESTDoc(methodName = "GetUserLogDetails", desc = "Returns user session is valid orr not", req_Params = {"authenticationtokenid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUsers postXmlUserLogDetails(String content) {
        return getUserLogDetails(content);
    }

    @POST
    @Path("/getuserlogdetails/json/{json}")
    @Produces("application/json")
    @Consumes("application/xml")
    public TingcoUsers postJsonUserLogDetails(String content) {
        return getUserLogDetails(content);
    }

    /*
     * GetUsersList for ListUsers page @param xml
     */
    @POST
    @Path("/getuserslistforlistuserspage/rest/{rest}")
    @Produces("application/xml")
    @Consumes("application/xml")
    @RESTDoc(methodName = "GetUsersListForListUserspage", desc = "GetUsersList for ListUsers page", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoUsers postXml_getUsersListForListUserspage(String content) {
        return getUsersListForListUserspage(content);
    }

    /*
     * GetUsersList for ListUsers page @param xml
     */
    @POST
    @Path("/getuserslistforlistuserspage/json/{json}")
    @Consumes("application/xml")
    @Produces("application/json")
    public TingcoUsers postJson_getUsersListForListUserspage(String content) {
        return getUsersListForListUserspage(content);
    }

    /*
     * GetUsersList for SearchUsers page @param xml
     */
    @POST
    @Path("/getuserslistforsearchuserspage/rest/{rest}")
    @Produces("application/xml")
    @Consumes("application/xml")
    @RESTDoc(methodName = "GetUsersListForSearchUserspage", desc = "GetUsersList for SearchUsers page", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoUsers postXml_getUsersListForSearchUserspage(String content) {
        return getUsersListForSearchUserspage(content);
    }

    /*
     * GetUsersList for SearchUsers page @param xml
     */
    @POST
    @Path("/getuserslistforsearchuserspage/json/{json}")
    @Consumes("application/xml")
    @Produces("application/json")
    public TingcoUsers postJson_getUsersListForSearchUserspage(String content) {
        return getUsersListForSearchUserspage(content);
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public UsersResource getUsersResource() {
        return new UsersResource();
    }

    /**
     * GetDeviceDriversDetails
     *
     * @param deviceId
     * @param countryId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getdevicedriversdetails/rest/{rest}/deviceid/{deviceid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetDeviceDriversDetails", desc = "Get DeviceDrivers Details", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_GetDeviceDriversDetails(@PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getDeviceDriversDetails(deviceId, countryId);
    }

    /**
     * GetDeviceDriversDetails
     *
     * @param deviceId
     * @param countryId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getdevicedriversdetails/json/{json}/deviceid/{deviceid}/countryid/{countryid}")
    public TingcoUsers getJson_GetDeviceDriversDetails(@PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getDeviceDriversDetails(deviceId, countryId);
    }

    /**
     * UpdateDeviceUsers
     *
     * @param deviceId
     * @param userId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/updatedeviceusers/rest/{rest}/deviceid/{deviceid}/userid/{userid}")
    @RESTDoc(methodName = "UpdateDeviceUsers", desc = "Update DeviceUsers", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_UpdateDeviceUsers(@PathParam("deviceid") String deviceId, @PathParam("userid") String userId) throws DatatypeConfigurationException {
        return updateDeviceUsers(deviceId, userId);
    }

    /**
     * UpdateDeviceUsers
     *
     * @param deviceId
     * @param userId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/updatedeviceusers/json/{json}/deviceid/{deviceid}/userid/{userid}")
    public TingcoUsers getJson_UpdateDeviceUsers(@PathParam("deviceid") String deviceId, @PathParam("userid") String userId) throws DatatypeConfigurationException {
        return updateDeviceUsers(deviceId, userId);
    }

    /**
     * DeleteDeviceUsers
     *
     * @param deviceId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/deletedeviceusers/rest/{rest}/deviceid/{deviceid}")
    @RESTDoc(methodName = "DeleteDeviceUsers", desc = "Delete DeviceUsers", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_DeleteDeviceUsers(@PathParam("deviceid") String deviceId) throws DatatypeConfigurationException {
        return deleteDeviceUsers(deviceId);
    }

    /**
     * DeleteDeviceUsers
     *
     * @param deviceId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/deletedeviceusers/json/{json}/deviceid/{deviceid}")
    public TingcoUsers getJson_DeleteDeviceUsers(@PathParam("deviceid") String deviceId) throws DatatypeConfigurationException {
        return deleteDeviceUsers(deviceId);
    }

    /**
     * GetUserRoleFunctionAreasDetails
     *
     * @param userRoleId
     * @param countryId
     * @return TingcoUsers
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getuserrolefunctionareasdetails/rest/{rest}/userroleid/{userroleid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetUserRoleFunctionAreasDetails", desc = "GetUserRoleFunctionAreasDetails", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_GetUserRoleFunctionAreasDetails(@PathParam("userroleid") String userRoleId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getUserRoleFunctionAreasDetails(userRoleId, countryId);
    }

    /**
     * GetUserRoleFunctionAreasDetails
     *
     * @param userRoleId
     * @param countryId
     * @return TingcoUsers
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getuserrolefunctionareasdetails/json/{json}/userroleid/{userroleid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetUserRoleFunctionAreasDetails", desc = "GetUserRoleFunctionAreasDetails", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getJson_GetUserRoleFunctionAreasDetails(@PathParam("userroleid") String userRoleId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getUserRoleFunctionAreasDetails(userRoleId, countryId);
    }

    /**
     * DeleteUserRoleFunctionAreas
     *
     * @param userRoleId
     * @param functionAreaId
     * @param permissionId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/deleteuserrolefunctionareas/rest/{rest}/userroleid/{userroleid}/functionareaid/{functionareaid}/permissionid/{permissionid}")
    @RESTDoc(methodName = "Delete UserRole FunctionAreas", desc = "Delete UserRole FunctionAreas", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_DeleteUserRoleFunctionAreas(@PathParam("userroleid") String userRoleId, @PathParam("functionareaid") String functionAreaId, @PathParam("permissionid") String permissionId) throws DatatypeConfigurationException {
        return deleteUserRoleFunctionAreas(userRoleId, functionAreaId, permissionId);
    }

    /**
     * DeleteUserRoleFunctionAreas
     *
     * @param userRoleId
     * @param functionAreaId
     * @param permissionId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/deleteuserrolefunctionareas/json/{json}/userroleid/{userroleid}/functionareaid/{functionareaid}/permissionid/{permissionid}")
    @RESTDoc(methodName = "Delete UserRole FunctionAreas", desc = "Delete UserRole FunctionAreas", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getJson_DeleteUserRoleFunctionAreas(@PathParam("userroleid") String userRoleId, @PathParam("functionareaid") String functionAreaId, @PathParam("permissionid") String permissionId) throws DatatypeConfigurationException {
        return deleteUserRoleFunctionAreas(userRoleId, functionAreaId, permissionId);
    }

    /**
     * GetAllFunctionAreas
     *
     * @return TingcoUsers
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getallfunctionareas/rest/{rest}")
    @RESTDoc(methodName = "Get All FunctionAreas", desc = "Get All FunctionAreas", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_GetAllFunctionAreas() throws DatatypeConfigurationException {
        return getAllFunctionAreas();
    }

    /**
     * GetAllFunctionAreas
     *
     * @return TingcoUsers
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getallfunctionareas/json/{json}")
    @RESTDoc(methodName = "Get All FunctionAreas", desc = "Get All FunctionAreas", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getJson_GetAllFunctionAreas() throws DatatypeConfigurationException {
        return getAllFunctionAreas();
    }

    /**
     * AddUserRoleFunctionAreas
     *
     * @param userRoleId
     * @param functionAreaId
     * @param permissionId
     * @return TingcoUsers
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/adduserrolefunctionareas/rest/{rest}/userroleid/{userroleid}/functionareaid/{functionareaid}/permissionid/{permissionid}")
    @RESTDoc(methodName = "AddUserRoleFunctionAreas", desc = "AddUserRoleFunctionAreas", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_AddUserRoleFunctionAreas(@PathParam("userroleid") String userRoleId, @PathParam("functionareaid") String functionAreaId, @PathParam("permissionid") String permissionId) throws DatatypeConfigurationException {
        return addUserRoleFunctionAreas(userRoleId, functionAreaId, permissionId);
    }

    /**
     * AddUserRoleFunctionAreas
     *
     * @param userRoleId
     * @param functionAreaId
     * @param permissionId
     * @return TingcoUsers
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/adduserrolefunctionareas/json/{json}/userroleid/{userroleid}/functionareaid/{functionareaid}/permissionid/{permissionid}")
    @RESTDoc(methodName = "AddUserRoleFunctionAreas", desc = "AddUserRoleFunctionAreas", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getJson_AddUserRoleFunctionAreas(@PathParam("userroleid") String userRoleId, @PathParam("functionareaid") String functionAreaId, @PathParam("permissionid") String permissionId) throws DatatypeConfigurationException {
        return addUserRoleFunctionAreas(userRoleId, functionAreaId, permissionId);
    }

    /**
     * UpdateUserRoleFunctionAreas
     *
     * @param userRoleId
     * @param functionAreaId
     * @param oldPermissionId
     * @param newPermissionId
     * @return TingcoUsers
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/updateuserrolefunctionareas/rest/{rest}/userroleid/{userroleid}/functionareaid/{functionareaid}/oldpermissionid/{oldpermissionid}/newpermissionid/{newpermissionid}")
    @RESTDoc(methodName = "UpdateUserRoleFunctionAreas", desc = "UpdateUserRoleFunctionAreas", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_UpdateUserRoleFunctionAreas(@PathParam("userroleid") String userRoleId, @PathParam("functionareaid") String functionAreaId, @PathParam("oldpermissionid") String oldPermissionId, @PathParam("newpermissionid") String newPermissionId) throws DatatypeConfigurationException {
        return updateUserRoleFunctionAreas(userRoleId, functionAreaId, oldPermissionId, newPermissionId);
    }

    /**
     * UpdateUserRoleFunctionAreas
     *
     * @param userRoleId
     * @param functionAreaId
     * @param oldPermissionId
     * @param newPermissionId
     * @return TingcoUsers
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/updateuserrolefunctionareas/json/{json}/userroleid/{userroleid}/functionareaid/{functionareaid}/oldpermissionid/{oldpermissionid}/newpermissionid/{newpermissionid}")
    @RESTDoc(methodName = "UpdateUserRoleFunctionAreas", desc = "UpdateUserRoleFunctionAreas", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getJson_UpdateUserRoleFunctionAreas(@PathParam("userroleid") String userRoleId, @PathParam("functionareaid") String functionAreaId, @PathParam("oldpermissionid") String oldPermissionId, @PathParam("newpermissionid") String newPermissionId) throws DatatypeConfigurationException {
        return updateUserRoleFunctionAreas(userRoleId, functionAreaId, oldPermissionId, newPermissionId);
    }

    /**
     * GetSupportOrganizationUsersList
     *
     * @param supportOrganizationId
     * @param groupId
     * @param countryId
     * @return TingcoUsers
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getsupportorganizationuserslist/rest/{rest}/supportorganizationid/{supportorganizationid}/groupid/{groupid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetSupportOrganizationUsersList", desc = "GetSupportOrganizationUsersList", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_GetSupportOrganizationUsersList(@PathParam("supportorganizationid") String supportOrganizationId, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getSupportOrganizationUsersList(supportOrganizationId, groupId, countryId);
    }

    /**
     * GetSupportOrganizationUsersList
     *
     * @param supportOrganizationId
     * @param groupId
     * @param countryId
     * @return TingcoUsers
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getsupportorganizationuserslist/json/{json}/supportorganizationid/{supportorganizationid}/groupid/{groupid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetSupportOrganizationUsersList", desc = "GetSupportOrganizationUsersList", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getJson_GetSupportOrganizationUsersList(@PathParam("supportorganizationid") String supportOrganizationId, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getSupportOrganizationUsersList(supportOrganizationId, groupId, countryId);
    }

    /**
     * GetUsersList
     *
     * @param countryid
     * @param searchfilter
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getuserslist/rest/{rest}/countryid/{countryid}/groupid/{groupid}{searchfilter:(/searchfilter/[^/]+?)?}")
    @RESTDoc(methodName = "GetUsersList", desc = "Get the UsersList", req_Params = {"CountryID;int", "SearchFilter;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_GetUsersList(@PathParam("countryid") int countryId, @PathParam("groupid") String groupId, @PathParam("searchfilter") String searchFilter) throws DatatypeConfigurationException {
        return getUsersList(countryId, groupId, searchFilter);
    }

    /**
     * GetUsersList
     *
     * @param countryid
     * @param searchfilter
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getuserslist/json/{json}/countryid/{countryid}/groupid/{groupid}{searchfilter:(/searchfilter/[^/]+?)?}")
    @RESTDoc(methodName = "GetUsersList", desc = "Get the UsersList", req_Params = {"CountryID;int", "SearchFilter;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getJson_GetUsersList(@PathParam("countryid") int countryId, @PathParam("groupid") String groupId, @PathParam("searchfilter") String searchFilter) throws DatatypeConfigurationException {
        return getUsersList(countryId, groupId, searchFilter);
    }

    private TingcoUsers getUsersList(int countryId, String groupId, String searchFilter) throws DatatypeConfigurationException {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoUsers tingcoUsers = null;
        Session session = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        tingcoUsers = tingcoUserXml.buildUserTemplate();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (countryId <= 0) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("CountryId should not be empty");
                    return tingcoUsers;
                }
                if (!searchFilter.equals("")) {
                    searchFilter = searchFilter.split("/")[2];
                } else {
                    searchFilter = null;
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
                    List<Users2> users2List = new ArrayList<Users2>();
                    DeviceDAO deviceDAO = new DeviceDAO();
                    List<String> groupidset = deviceDAO.getTrustedGroup(session, groupId);
                    List<List<String>> splitList = TCMUtil.splitStringList(groupidset, 2000);
                    for (List<String> list : splitList) {
                        List<Users2> users2ListTemp = userDAO.getUsers2BySearchFiltersAndGroup(searchFilter, list, session);
                        if (users2ListTemp != null) {
                            users2List.addAll(users2ListTemp);
                        }
                    }
                    Collections.sort(users2List, new Comparator<Users2>() {

                        public int compare(Users2 o1, Users2 o2) {
                            return o2.getCreatedDate().compareTo(o1.getCreatedDate());
                        }
                    });

                    if (users2List.size() > 200) {
                        users2List = users2List.subList(0, 200);
                    }

                    Collections.sort(users2List, new Comparator<Users2>() {

                        public int compare(Users2 o1, Users2 o2) {
                            return (o1.getLastName()).compareToIgnoreCase(o2.getLastName());
                        }
                    });
                    Collections.sort(users2List, new Comparator<Users2>() {

                        public int compare(Users2 o1, Users2 o2) {
                            return (o1.getFirstName()).compareToIgnoreCase(o2.getFirstName());
                        }
                    });


                    if (!users2List.isEmpty()) {
                        tingcoUsers = tingcoUserXml.buildGetUsersList(tingcoUsers, countryId, users2List, groupidset, session);
                    } else {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("No Users found");
//                        return tingcoUsers;
                    }
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception e) {
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured ");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUsers;
    }

    private TingcoUsers getSupportOrganizationUsersList(String supportOrganizationId, String groupId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoUsers tingcoUsers = null;
        Session session = null;
        Transaction tx = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        try {
            tingcoUsers = tingcoUserXml.buildUserTemplate();
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
                    List<GroupTrusts> groupTrusts = groupDAO.getGroupTrustByGroupID(groupId, session);
                    Set<String> groupIdsList = groupDAO.getGroupIdsList(groupId, groupTrusts);
                    Object object = session.get(SupportOrganizations.class, supportOrganizationId);
                    if (object == null) {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("Data Not Found.");
                        return tingcoUsers;
                    }
                    SupportOrganizations supportOrganizations = (SupportOrganizations) object;
                    boolean flag = false;
                    for (String string : groupIdsList) {
                        if (supportOrganizations.getManagedByGroupId().equalsIgnoreCase(string)) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("You are not authorized to manage this Support Organization.");
                        return tingcoUsers;
                    }
                    List<SupportOrganizationUsers> supportOrganizationUserses = userDAO.getSupportOrganizationUsersById(supportOrganizationId, session);
                    if (supportOrganizationUserses.isEmpty()) {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("Data Not Found.");
                        return tingcoUsers;
                    }
                    List<String> userIdList = new ArrayList<String>();
                    for (SupportOrganizationUsers supportOrganizationUsers : supportOrganizationUserses) {
                        userIdList.add(supportOrganizationUsers.getId().getUserId());
                    }
                    List<Users2> users2s = userDAO.getUsers2ByUserIdShorted(userIdList, session);
                    if (users2s.isEmpty()) {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("User Data Not Found.");
                        return tingcoUsers;
                    }
                    Set<String> groupIdList = new HashSet<String>();
                    for (Users2 users2 : users2s) {
                        groupIdList.add(users2.getGroupId());
                    }
                    if (groupIdList.isEmpty()) {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("Data Not Found.");
                        return tingcoUsers;
                    }
                    List<GroupTranslations> groupTranslationses = groupDAO.getGroupTranslationsByGroupId(groupIdList, countryId, 0, session);
                    return tingcoUserXml.buildGetSupportOrganizationUsersList(tingcoUsers, users2s, supportOrganizationUserses, groupTranslationses);
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception ex) {
            exceptionLog(ex);
            if (tx != null) {
                tx.rollback();
            }
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoUsers updateUserRoleFunctionAreas(String userRoleId, String functionAreaId, String oldPermissionId, String newPermissionId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoUsers tingcoUsers = null;
        Session session = null;
        Transaction tx = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        try {
            tingcoUsers = tingcoUserXml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.ROLES)) {
                    ArrayList<String> operations = ht.get(TCMUtil.ROLES);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    if (oldPermissionId.equalsIgnoreCase(newPermissionId)) {
                        return tingcoUsers;
                    }
                    PermissionDAO permissionDAO = new PermissionDAO();
                    UserRoleObjectPermissions2 userRoleObjectPermissions2 = permissionDAO.getUserRoleObjectPermission(userRoleId, oldPermissionId, functionAreaId, session);
                    if (userRoleObjectPermissions2 == null) {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoUsers;
                    }
                    UserRoleObjectPermissions2 userRoleObjectPermissions2New = permissionDAO.getUserRoleObjectPermission(userRoleId, newPermissionId, functionAreaId, session);
                    if (userRoleObjectPermissions2New != null) {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("Data Already Exists");
                        return tingcoUsers;
                    }
                    tx = session.beginTransaction();
                    GregorianCalendar gc = new GregorianCalendar();
                    StringBuffer queryString = new StringBuffer("update UserRoleObjectPermissions2 set updatedDate = getutcdate() ");

                    queryString.append(", PermissionID = '" + newPermissionId + "' ");

                    queryString.append(" where UserRoleID = '" + userRoleId + "' and ObjectID = '" + functionAreaId + "' and PermissionID = '" + oldPermissionId + "' ");
                    SQLQuery query = session.createSQLQuery(queryString.toString());
                    query.executeUpdate();
                    tx.commit();
                    return tingcoUsers;
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception ex) {
            exceptionLog(ex);
            if (tx != null) {
                tx.rollback();
            }
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoUsers addUserRoleFunctionAreas(String userRoleId, String functionAreaId, String permissionId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoUsers tingcoUsers = null;
        Session session = null;
        Transaction tx = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        try {
            tingcoUsers = tingcoUserXml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.ROLES)) {
                    ArrayList<String> operations = ht.get(TCMUtil.ROLES);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    PermissionDAO permissionDAO = new PermissionDAO();
                    UserRoleObjectPermissions2 userRoleObjectPermissions2 = permissionDAO.getUserRoleObjectPermission(userRoleId, permissionId, functionAreaId, session);
                    if (userRoleObjectPermissions2 != null) {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("Data Already Exists");
                        return tingcoUsers;
                    }
                    tx = session.beginTransaction();
                    GregorianCalendar gc = new GregorianCalendar();
                    userRoleObjectPermissions2 = new UserRoleObjectPermissions2();
                    UserRoleObjectPermissions2Id id = new UserRoleObjectPermissions2Id();
                    id.setObjectId(functionAreaId);
                    id.setPermissionId(permissionId);
                    id.setUserRoleId(userRoleId);
                    userRoleObjectPermissions2.setId(id);
                    userRoleObjectPermissions2.setLastUpdatedByUserId(sessions2.getUserId());
                    userRoleObjectPermissions2.setCreatedDate(gc.getTime());
                    userRoleObjectPermissions2.setUpdatedDate(gc.getTime());
                    session.saveOrUpdate(userRoleObjectPermissions2);
                    tx.commit();
                    return tingcoUsers;
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception ex) {
            exceptionLog(ex);
            if (tx != null) {
                tx.rollback();
            }
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoUsers getAllFunctionAreas() {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoUsers tingcoUsers = null;
        Session session = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        try {
            tingcoUsers = tingcoUserXml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.ROLES)) {
                    ArrayList<String> operations = ht.get(TCMUtil.ROLES);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    PermissionDAO permissionDAO = new PermissionDAO();
                    List<FunctionAreas> functionAreases = permissionDAO.getAllFunctionAreas(session);
                    if (functionAreases.isEmpty()) {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("Data Not found");
                        return tingcoUsers;
                    }
                    return tingcoUserXml.buildGetAllFunctionArea(tingcoUsers, functionAreases);
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception ex) {
            exceptionLog(ex);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoUsers deleteUserRoleFunctionAreas(String userRoleId, String functionAreaId, String permissionId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoUsers tingcoUsers = null;
        Session session = null;
        Transaction tx = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        try {
            tingcoUsers = tingcoUserXml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.ROLES)) {
                    ArrayList<String> operations = ht.get(TCMUtil.ROLES);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    PermissionDAO permissionDAO = new PermissionDAO();
                    UserRoleObjectPermissions2 userRoleObjectPermissions2 = permissionDAO.getUserRoleObjectPermission(userRoleId, permissionId, functionAreaId, session);
                    if (userRoleObjectPermissions2 == null) {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoUsers;
                    }
                    tx = session.beginTransaction();
                    session.delete(userRoleObjectPermissions2);
                    tx.commit();
                    return tingcoUsers;
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception ex) {
            exceptionLog(ex);
            if (tx != null) {
                tx.rollback();
            }
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoUsers getUserRoleFunctionAreasDetails(String userRoleId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoUsers tingcoUsers = null;
        Session session = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        try {
            tingcoUsers = tingcoUserXml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
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
                    DbManager dbManager = new DbManager();
                    List<UserRoleObjectPermissions2> userRoleObjectPermissions2 = dbManager.getUserRoleObjectPermissions2ByUserRoleId(session, userRoleId);

                    if (userRoleObjectPermissions2.isEmpty()) {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoUsers;
                    } else {
                        List<String> functionAreaId = new ArrayList<String>();
                        List<String> permissionId = new ArrayList<String>();
                        for (UserRoleObjectPermissions2 userRoleObjectPermissions21 : userRoleObjectPermissions2) {
                            functionAreaId.add(userRoleObjectPermissions21.getId().getObjectId());
                            permissionId.add(userRoleObjectPermissions21.getId().getPermissionId());
                        }
                        List<FunctionAreas> functionAreases = dbManager.getFunctionAreasByObjectIDs(session, functionAreaId);
                        if (functionAreases.isEmpty()) {
                            tingcoUsers.getMsgStatus().setResponseCode(-1);
                            tingcoUsers.getMsgStatus().setResponseText("Data Not Found");
                            return tingcoUsers;
                        }
                        List<PermissionTranslations> permissionTranslationses = dbManager.getPermissionTranslationsByIdsAndCountry(session, permissionId, countryId);
                        return tingcoUserXml.buildGetUserRoleFunctionAreasDetails(tingcoUsers, userRoleObjectPermissions2, permissionTranslationses, functionAreases);
                    }
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception ex) {
            exceptionLog(ex);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }

    }

    private TingcoUsers deleteDeviceUsers(String deviceId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoUsers tingcoUsers = null;
        Session session = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        try {
            tingcoUsers = tingcoUserXml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
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
                    Object object = userDAO.getDeviceUsersByDeviceID(session, deviceId);
                    if (object == null) {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoUsers;
                    } else {
                        DeviceUsers deviceUsers = (DeviceUsers) object;
                        if (!userDAO.deleteDeviceUsers(deviceUsers, session)) {
                            tingcoUsers.getMsgStatus().setResponseCode(-1);
                            tingcoUsers.getMsgStatus().setResponseText("Error Occured While Delete");
                            return tingcoUsers;
                        }
                    }
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception ex) {
            exceptionLog(ex);
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

    private TingcoUsers updateDeviceUsers(String deviceId, String userId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoUsers tingcoUsers = null;
        Session session = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        try {
            tingcoUsers = tingcoUserXml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
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
                    Object object = userDAO.getDeviceUsersByDeviceID(session, deviceId);
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    DeviceUsers deviceUsers = null;
                    if (object == null) {
                        //Insert a New DeviceUsers
                        deviceUsers = new DeviceUsers();
                        deviceUsers.setDeviceId(deviceId);
                        deviceUsers.setUserId(userId);
                        deviceUsers.setCreatedDate(gc.getTime());
                        deviceUsers.setActiveFromDate(gc.getTime());
                        deviceUsers.setUpdatedDate(gc.getTime());
                    } else {
                        deviceUsers = (DeviceUsers) object;
                        deviceUsers.setUserId(userId);
                        deviceUsers.setUpdatedDate(gc.getTime());
                    }
                    if (!userDAO.saveOrUpdateDeviceUsers(deviceUsers, session)) {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("Error Occured While Update");
                        return tingcoUsers;
                    }
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception ex) {
            exceptionLog(ex);
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

    private TingcoUsers getDeviceDriversDetails(String deviceId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoUsers tingcoUsers = null;
        Session session = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        try {
            tingcoUsers = tingcoUserXml.buildUserTemplate();
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
                    Object object = userDAO.getDeviceUsersByDeviceID(session, deviceId);
                    if (object != null) {
                        DeviceUsers deviceUsers = (DeviceUsers) object;
                        Users2 users2 = userDAO.getUserById(deviceUsers.getUserId(), session);
                        if (users2 == null) {
                            tingcoUsers.getMsgStatus().setResponseCode(-1);
                            tingcoUsers.getMsgStatus().setResponseText("No Data Found");
                            return tingcoUsers;
                        }
                        GroupTranslations groupTranslations = groupDAO.getGroupTranslationsByIds(users2.getGroupId(), countryId, session);
                        tingcoUsers = tingcoUserXml.buildGetDeviceDriversDetails(tingcoUsers, users2, groupTranslations);
                    } else {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("No Data Found");
                        return tingcoUsers;
                    }
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception ex) {
            exceptionLog(ex);
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

    private TingcoUsers getBlackList(String groupId, String startValue, String userAliasType, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoUsers tingcoUsers = null;
        Session session = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        try {
            tingcoUsers = tingcoUserXml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (!startValue.equals("")) {
                    startValue = startValue.split("/")[2];
                }
                if (!userAliasType.equals("")) {
                    userAliasType = userAliasType.split("/")[2];
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
                    List<Object> blackList = userDAO.getBlackListBySearchCriteria(groupId, startValue, userAliasType, countryId, session);
                    if (!blackList.isEmpty()) {
                        UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        tingcoUsers = tingcoUserXml.buildBlackList(tingcoUsers, blackList, timeZoneGMToffset);
                    } else {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("No Blacklist found for the given input");
                        return tingcoUsers;
                    }
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (DatatypeConfigurationException ex) {
            exceptionLog(ex);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured");
            return tingcoUsers;
        } catch (ParseException pe) {
            exceptionLog(pe);
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

    private TingcoUsers getUserLogDetails(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoUsers tingcoUsers = null;
        Session session = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        try {
            tingcoUsers = tingcoUserXml.buildUserTemplate();
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
                    TingcoMeasurementTypes measurementTypes = (TingcoMeasurementTypes) JAXBManager.getInstance().unMarshall(content, TingcoMeasurementTypes.class);
                    if (measurementTypes.getMeasurementDatas() != null) {
                        String timeZoneId = RestUtilDAO.getUserTimeZones2byUserId(measurementTypes.getUserId(), session).getTimeZoneId();
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(timeZoneId, session).getTimeZoneGmtoffset();
                        List<UserLog> userLogList = userDAO.getUserLogDetail(session, measurementTypes);
                        if (userLogList.isEmpty()) {
                            tingcoUsers.getMsgStatus().setResponseCode(-1);
                            tingcoUsers.getMsgStatus().setResponseText("No Data Found");
                            return tingcoUsers;
                        }
                        return tingcoUserXml.buildGetUserLogDetail(tingcoUsers, userLogList, timeZoneGMToffset);
                    } else {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("POST XML have not proper Tag");
                        return tingcoUsers;
                    }

                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception e) {
            exceptionLog(e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured ");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoUsers getUserListForUserLog(String groupId, String countryId, String searchString) {
        long requestedTime = System.currentTimeMillis();

        boolean hasPermission = false;
        TingcoUsers tingcoUsers = null;
        Session session = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        try {
            tingcoUsers = tingcoUserXml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (searchString.equals("")) {
                    searchString = null;
                } else {
                    searchString = searchString.split("/")[2];
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
                    Set<String> groupIdSet = groupDAO.getGroupsAndGroupTrusts(groupId, session);
                    List<Users2> users2List = new ArrayList<Users2>();
                    List<String> groupIdList = new ArrayList<String>(groupIdSet);
                    Set<String> groupIdTempSet = new HashSet<String>();
                    if (searchString != null) {
                        List<List<String>> splitList = TCMUtil.splitStringList(groupIdList, 2000);
                        for (List<String> list : splitList) {
                            groupIdTempSet.addAll(list);
                            List<Users2> users2Listtemp = userDAO.getUsers2BySearchstrings(session, groupIdTempSet, searchString, 200);
                            users2List.addAll(users2Listtemp);
                            groupIdTempSet.clear();
                        }
                    } else {
                        List<String> group = new ArrayList<String>();
                        group.add(groupId);
                        users2List = userDAO.getUsers2ByGroupIdListorderByFirstNameLastName(session, group, 200);
                    }
                    List<GroupTranslations> groupTranslation = new ArrayList<GroupTranslations>();
                    List<List<String>> splitList = TCMUtil.splitStringList(groupIdList, 2000);
                    for (List<String> list : splitList) {
                        List<GroupTranslations> groupTranslationTemp = groupDAO.getGroupTranslationsById(list, Integer.parseInt(countryId), session);
                        groupTranslation.addAll(groupTranslationTemp);
                    }

                    return tingcoUserXml.buildGetUserForUserAlias(tingcoUsers, users2List, groupTranslation);

                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception e) {
            exceptionLog(e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured ");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoUsers getUserSession() {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoUsers tingcoUsers = null;
        Session session = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        try {
            tingcoUsers = tingcoUserXml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {

//                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
//                if (sessions2.getAuthenticationToken().equalsIgnoreCase(authenticationTokenId)) {
                return tingcoUsers;
//                } else {
//                    tingcoUsers.getMsgStatus().setResponseCode(-3);
//                    tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
//                    return tingcoUsers;
//                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception e) {
            exceptionLog(e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured ");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoUsers getAssignedUsers(String supportOrganizationId, String userId, String searchString) {
        long requestedTime = System.currentTimeMillis();

        boolean hasPermission = false;
        TingcoUsers tingcoUsers = null;
        Session session = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        try {
            tingcoUsers = tingcoUserXml.buildUserTemplate();
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
                    List<SupportOrganizationUsers> souList = userDAO.getSupportOrganizationUsersByUserIdandId(userId, supportOrganizationId, session);
                    if (souList.isEmpty()) {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("No Users found");
                        return tingcoUsers;
                    }
                    List<SupportOrganizationUsers> soutList = userDAO.getSupportOrganizationUsersById(supportOrganizationId, session);
                    List<String> userid = new ArrayList<String>();
                    for (SupportOrganizationUsers supportOrganizationUsers : soutList) {
                        userid.add(supportOrganizationUsers.getId().getUserId());
                    }
                    List<Users2> usersList = new ArrayList<Users2>();
                    if (searchString != null) {
                        usersList = userDAO.getUsers2BySearchstringConcat(searchString, userid, session);
                    } else {
                        usersList = userDAO.getUsers2ByUserIdShorted(userid, session);
                    }
                    if (usersList.isEmpty()) {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("No User found");
                        return tingcoUsers;
                    }
                    return tingcoUserXml.buildGetAssignedUsers(tingcoUsers, usersList);
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception e) {
            exceptionLog(e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured ");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }

    }

    private TingcoUsers UpdateCountryByUserID(String userId, String countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoUsers tingcoUsers = null;
        Session session = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        try {
            tingcoUsers = tingcoUserXml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (userId.equals("")) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("UserId should not be empty");
                    return tingcoUsers;
                }
                if (countryId.equals("")) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("CountryId should not be empty");
                    return tingcoUsers;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.USERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.USERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    if (!userDAO.updateCountryId(userId, countryId, session)) {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("Error Occured while Updating");
                    }
                    return tingcoUsers;
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (DatatypeConfigurationException ex) {
            exceptionLog(ex);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured while Updating");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoUsers addUserPassword(String userId, String domainId, String pwd) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoUsers userXml = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        Session session = null;
        try {
            userXml = tingcoUserXml.buildUserTemplate();
            session = HibernateUtil.getSessionFactory().openSession();
            if (request.getAttribute("USERSESSION") != null) {

                if (userId == null) {
                    userXml.getMsgStatus().setResponseCode(-1);
                    userXml.getMsgStatus().setResponseText("UserID should not be null");
                    return userXml;
                }

                if (domainId == null) {
                    userXml.getMsgStatus().setResponseCode(-1);
                    userXml.getMsgStatus().setResponseText("DomainID should not be null");
                    return userXml;
                }

                if (pwd == null) {
                    userXml.getMsgStatus().setResponseCode(-1);
                    userXml.getMsgStatus().setResponseText("Password should not be null");
                    return userXml;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.USERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.USERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }

                if (hasPermission) {
                    UserPasswords userPasswords = new UserPasswords();
                    userPasswords.setId(UUID.randomUUID().toString());
                    userPasswords.setUserId(userId);
                    userPasswords.setDomainId(domainId);
                    userPasswords.setPassword(RSAPassword.encryptedPwd(pwd));
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    userPasswords.setCreatedDate(gc.getTime());
                    List<Users2> usersList = session.getNamedQuery("getUserByUserIDandDomainId").setString("userID", userId).setString("domainID", domainId).list();
                    if (!usersList.isEmpty()) {
                        Users2 user = usersList.get(0);
                        user.setPassword(RSAPassword.encryptedPwd(pwd));
                        user.setLastPasswordChangedDate(gc.getTime());
                        usermanager.updateUser(user, session);
                        boolean result = userDAO.addUserPasswords(session, userPasswords);
                        if (!result) {
                            userXml.getMsgStatus().setResponseCode(-1);
                            userXml.getMsgStatus().setResponseText("Error Occured while Inserting");
                            return userXml;
                        }
                    } else {
                        userXml.getMsgStatus().setResponseCode(-1);
                        userXml.getMsgStatus().setResponseText("No Record found in Users2");
                        return userXml;
                    }
                    return userXml;
                } else {
                    userXml.getMsgStatus().setResponseCode(-10);
                    userXml.getMsgStatus().setResponseText("User Permission is not given");
                    return userXml;
                }
            } else {
                userXml.getMsgStatus().setResponseCode(-3);
                userXml.getMsgStatus().setResponseText("User Session is Not Valid");
                return userXml;
            }
        } catch (DatatypeConfigurationException ex) {
            exceptionLog(ex);
            userXml.getMsgStatus().setResponseCode(-1);
            userXml.getMsgStatus().setResponseText("Error Occured while Inserting");
            return userXml;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }

    }

    private TingcoUtils addUserTimeZones2(String userID, String timezoneID, String useDayLightSaving) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoUtils util = null;
        Session session = null;
        try {
            util = tingcoUtilXml.buildTingcoUtilsTemplate();
            session = HibernateUtil.getSessionFactory().openSession();
            if (request.getAttribute("USERSESSION") != null) {

                if (userID == null) {
                    util.getMsgStatus().setResponseCode(-1);
                    util.getMsgStatus().setResponseText("UserID should not be null");
                    return util;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.USERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.USERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }

                if (hasPermission) {
                    UserTimeZones2 timeZones2 = new UserTimeZones2();
                    timeZones2.setUserId(userID);
                    timeZones2.setTimeZoneId(timezoneID);
                    timeZones2.setUseDaylightSaving(Integer.parseInt(useDayLightSaving));
                    timeZones2.setCreatedDate(new GregorianCalendar().getTime());
                    timeZones2.setUpdatedDate(new GregorianCalendar().getTime());
                    boolean res = userDAO.addUserTimeZones2(timeZones2, session);
                    if (!res) {
                        util.getMsgStatus().setResponseCode(-1);
                        util.getMsgStatus().setResponseText("Error Occured While Inserting");
                        return util;
                    }
                    return util;
                } else {
                    util.getMsgStatus().setResponseCode(-10);
                    util.getMsgStatus().setResponseText("User Permission is not given");
                    return util;
                }
            } else {
                util.getMsgStatus().setResponseCode(-3);
                util.getMsgStatus().setResponseText("User Session is Not Valid");
                return util;
            }
        } catch (DatatypeConfigurationException ex) {
            exceptionLog(ex);
            util.getMsgStatus().setResponseCode(-1);
            util.getMsgStatus().setResponseText("Error Occured While Inserting");
            return util;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoUsers addUsers2(String firstName, String lastName, String loginName, String emailId, String phone, String groupId, String domainId, String pwd, String lockedOut, String changePwd, String countryId, String activeFromDate, String activeToDate, String userTypeId, String addressStreet, String addressStreet2, String addressSuite, String addressZip, String addressCity, String addressDesc, String addressRegion, String addressCountryId) throws ParseException {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoUsers userXml = null;
        Session session = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        try {
            userXml = tingcoUserXml.buildUserTemplate();
            session = HibernateUtil.getSessionFactory().openSession();
            if (request.getAttribute("USERSESSION") != null) {

                if (firstName == null) {
                    userXml.getMsgStatus().setResponseCode(-1);
                    userXml.getMsgStatus().setResponseText("FirstName should not be null");
                    return userXml;
                }

                if (lastName == null) {
                    userXml.getMsgStatus().setResponseCode(-1);
                    userXml.getMsgStatus().setResponseText("LastName should not be null");
                    return userXml;
                }

                if (loginName == null) {
                    userXml.getMsgStatus().setResponseCode(-1);
                    userXml.getMsgStatus().setResponseText("LoginName should not be null");
                    return userXml;
                }

                if (emailId == null) {
                    userXml.getMsgStatus().setResponseCode(-1);
                    userXml.getMsgStatus().setResponseText("UserEmailId should not be null");
                    return userXml;
                }

                if (phone == null) {
                    userXml.getMsgStatus().setResponseCode(-1);
                    userXml.getMsgStatus().setResponseText("MobilePhoneNumber should not be null");
                    return userXml;
                }

                if (groupId == null) {
                    userXml.getMsgStatus().setResponseCode(-1);
                    userXml.getMsgStatus().setResponseText("GroupID should not be null");
                    return userXml;
                }

                if (domainId == null) {
                    userXml.getMsgStatus().setResponseCode(-1);
                    userXml.getMsgStatus().setResponseText("DomainID should not be null");
                    return userXml;
                }

                if (pwd == null) {
                    userXml.getMsgStatus().setResponseCode(-1);
                    userXml.getMsgStatus().setResponseText("Password should not be null");
                    return userXml;
                }

                if (lockedOut == null) {
                    userXml.getMsgStatus().setResponseCode(-1);
                    userXml.getMsgStatus().setResponseText("IsLockedOut should not be null");
                    return userXml;
                }

                if (changePwd == null) {
                    userXml.getMsgStatus().setResponseCode(-1);
                    userXml.getMsgStatus().setResponseText("ChangePassword should not be null");
                    return userXml;
                }

                if (countryId == null) {
                    userXml.getMsgStatus().setResponseCode(-1);
                    userXml.getMsgStatus().setResponseText("CountryID should not be null");
                    return userXml;
                }

                if (activeFromDate == null) {
                    userXml.getMsgStatus().setResponseCode(-1);
                    userXml.getMsgStatus().setResponseText("ActiveFromDate should not be null");
                    return userXml;
                }

                if (activeToDate == null) {
                    userXml.getMsgStatus().setResponseCode(-1);
                    userXml.getMsgStatus().setResponseText("ActivetToDate should not be null");
                    return userXml;
                }

                if (userTypeId == null) {
                    userXml.getMsgStatus().setResponseCode(-1);
                    userXml.getMsgStatus().setResponseText("UserTypeID should not be null");
                    return userXml;
                }

                if (!addressStreet.equals("")) {
                    addressStreet = addressStreet.split("/")[2];
                } else {
                    addressStreet = null;
                }
                if (!addressStreet2.equals("")) {
                    addressStreet2 = addressStreet2.split("/")[2];
                } else {
                    addressStreet2 = null;
                }
                if (!addressSuite.equals("")) {
                    addressSuite = addressSuite.split("/")[2];
                } else {
                    addressSuite = null;
                }
                if (!addressZip.equals("")) {
                    addressZip = addressZip.split("/")[2];
                } else {
                    addressZip = null;
                }
                if (!addressCity.equals("")) {
                    addressCity = addressCity.split("/")[2];
                } else {
                    addressCity = null;
                }
                if (!addressDesc.equals("")) {
                    addressDesc = addressDesc.split("/")[2];
                } else {
                    addressDesc = null;
                }
                if (!addressRegion.equals("")) {
                    addressRegion = addressRegion.split("/")[2];
                } else {
                    addressRegion = null;
                }

                if (!addressCountryId.equals("")) {
                    addressCountryId = addressCountryId.split("/")[2];
                } else {
                    addressCountryId = null;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.USERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.USERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    if (!userDAO.getUserByAppIdEmail(domainId, loginName, session)) {
                        UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                        if (userTimeZones2 != null) {
                            String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                            SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                            String userID = UUID.randomUUID().toString();
                            Users2 user = new Users2();
                            user.setUserId(userID);
                            user.setFirstName(firstName);
                            user.setLastName(lastName);
                            Users2Id id = new Users2Id();
                            id.setDomainId(domainId);
                            id.setLoginName(loginName);
                            user.setId(id);
                            user.setUserEmail(emailId);
                            user.setUserMobilePhone(phone);
                            user.setGroupId(groupId);
                            user.setPassword(RSAPassword.encryptedPwd(pwd));
                            user.setIsLockedOut(Integer.parseInt(lockedOut));
                            user.setChangePwdRequired(Integer.parseInt(changePwd));
                            user.setFailedPasswordAttemptCount(0);
                            user.setCountryId(Integer.parseInt(countryId));
                            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
//                        gc.setTimeInMillis(Long.valueOf(activeFromDate));
                            gc.setTime(lFormat.parse(activeFromDate));
                            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                            user.setActiveFromDate(gc.getTime());
//                        gc.setTimeInMillis(Long.valueOf(activeToDate));
                            gc.setTime(lFormat.parse(activeToDate));
                            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                            user.setActiveToDate(gc.getTime());
                            gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                            user.setCreatedDate(gc.getTime());
                            user.setUpdatedDate(gc.getTime());
                            UserTypes2 userTypes = new UserTypes2();
                            userTypes.setUserTypeId(userTypeId);
                            user.setUserTypes2(userTypes);
                            Addresses address = null;
                            ObjectAddresses oa = null;
                            if (addressStreet != null || addressStreet2 != null || addressSuite != null || addressZip != null || addressCity != null || addressDesc != null || addressRegion != null) {
                                address = new Addresses();
                                oa = new ObjectAddresses();
                                String addressid = UUID.randomUUID().toString();
                                address.setAddressId(addressid);
                                AddressType at = (AddressType) session.get(AddressType.class, 3);
                                address.setAddressType(at);
                                address.setCountry((Country) session.get(Country.class, Integer.valueOf(addressCountryId)));
                                if (addressRegion != null) {
                                    address.setAddressRegion(addressRegion);
                                }
                                if (addressStreet != null) {
                                    address.setAddressStreet(addressStreet);
                                }
                                if (addressStreet2 != null) {
                                    address.setAddressStreet2(addressStreet2);
                                }
                                if (addressSuite != null) {
                                    address.setAddressSuite(addressSuite);
                                }
                                if (addressZip != null) {
                                    address.setAddressZip(addressZip);
                                }
                                if (addressCity != null) {
                                    address.setAddressCity(addressCity);
                                }
                                if (addressDesc != null) {
                                    address.setAddressDescription(addressDesc);
                                }
                                address.setUpdatedDate(gc.getTime());
                                address.setCreatedDate(gc.getTime());

                                oa.setAddressId(addressid);
                                oa.setAddressType(at);
                                ObjectAddressesId ids = new ObjectAddressesId();
                                ids.setAddressTypeId(3);
                                ids.setObjectId(userID);
                                oa.setId(ids);
                                oa.setLastUpdatedUserId(sessions2.getUserId());
                                oa.setCreatedDate(gc.getTime());
                                oa.setUpdatedDate(gc.getTime());
                            }

                            boolean result = userDAO.addUsers2(user, address, oa, session);
                            Users users = new Users();
                            User u = new User();
                            u.setUserID(userID);
                            users.getUser().add(u);
                            userXml.setUsers(users);
                            if (!result) {
                                TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Add User", firstName + " " + lastName, "Users2", "Failed");
                                userXml.getMsgStatus().setResponseCode(-1);
                                userXml.getMsgStatus().setResponseText("Error Occured While Inserting");
                                return userXml;
                            } else {
                                TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Add User", firstName + " " + lastName, "Users2", "Success");
                            }
                            return userXml;
                        } else {
                            TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Add User", firstName + " " + lastName, "Users2", "Failed");
                            userXml.getMsgStatus().setResponseCode(-1);
                            userXml.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                            return userXml;
                        }
                    } else {
                        TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Add User", firstName + " " + lastName, "Users2", "Failed");
                        userXml.getMsgStatus().setResponseCode(-1);
                        userXml.getMsgStatus().setResponseText("The new user cannot be created because a user with this e-mail already exists in the system.");
                        return userXml;
                    }
                } else {
                    userXml.getMsgStatus().setResponseCode(-10);
                    userXml.getMsgStatus().setResponseText("User Permission is not given");
                    return userXml;
                }
            } else {
                userXml.getMsgStatus().setResponseCode(-3);
                userXml.getMsgStatus().setResponseText("User Session is Not Valid");
                return userXml;
            }
        } catch (DatatypeConfigurationException ex) {
            exceptionLog(ex);
            userXml.getMsgStatus().setResponseCode(-1);
            userXml.getMsgStatus().setResponseText("Error Occured While Inserting");
            return userXml;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoUsers deletedUserByUserID(String userId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoUsers tingcoUsers = null;
        Session session = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        try {
            tingcoUsers = tingcoUserXml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (userId.equals("")) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("UserId should not be empty");
                    return tingcoUsers;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.USERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.USERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<Users2> usersList = userDAO.getUsers2ById(userId, session);
                    if (!usersList.isEmpty()) {
                        if (!userDAO.deleteUserByUserId(session, userId)) {
                            TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Delete User", usersList.get(0).getFirstName() + " " + usersList.get(0).getLastName(), "Users2", "Failed");
                            tingcoUsers.getMsgStatus().setResponseCode(-1);
                            tingcoUsers.getMsgStatus().setResponseText("Error Occured while deleting");
                            return tingcoUsers;
                        } else {
                            TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Delete User", usersList.get(0).getFirstName() + " " + usersList.get(0).getLastName(), "Users2", "Success");
                        }
                    } else {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoUsers;
                    }

                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (DatatypeConfigurationException ex) {
            exceptionLog(ex);
            tingcoUsers.getMsgStatus().setResponseCode(-10);
            tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUsers;
    }

    private TingcoUsers getUserAliasByUserId(String userId) throws DatatypeConfigurationException {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoUsers tingcoUsers = null;
        Session session = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        try {
            tingcoUsers = tingcoUserXml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (userId.equals("")) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("UserId should not be empty");
                    return tingcoUsers;
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
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        List<UserAlias> userAliasList = userDAO.getUserAlias(session, userId);
                        if (!userAliasList.isEmpty()) {
                            tingcoUsers = tingcoUserXml.buildUserAlias(tingcoUsers, userAliasList, timeZoneGMToffset);
                        } else {
                            tingcoUsers.getMsgStatus().setResponseCode(-1);
                            tingcoUsers.getMsgStatus().setResponseText("No Data found");
//                        return tingcoUsers;
                        }
                        return tingcoUsers;
                    } else {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                        return tingcoUsers;
                    }

                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (DatatypeConfigurationException ex) {
            exceptionLog(ex);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoUtils getUserTimeZones(String userId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = true;
        TingcoUtils util = null;
        Session session = null;
        try {
            util = tingcoUtilXml.buildTingcoUtilsTemplate();
            session = HibernateUtil.getSessionFactory().openSession();
            if (request.getAttribute("USERSESSION") != null) {
                if (userId == null) {
                    util.getMsgStatus().setResponseCode(-1);
                    util.getMsgStatus().setResponseText("UserID should not be null");
                    return util;
                }
                if (hasPermission) {
                    Timezones timeZones = new Timezones();
                    List<UserTimeZones2> userTimeZones = userDAO.getUserTimeZones(userId, session);
                    for (UserTimeZones2 utz : userTimeZones) {
                        TimeZones tz = new TimeZones();
                        tz = userDAO.getTimeZone(utz.getTimeZoneId(), session);
                        timeZones.setTimezoneID(tz.getTimeZoneId());
                        timeZones.setTimezoneName(tz.getTimeZoneName());
                        timeZones.setTimeZoneDescription(tz.getTimeZoneName());
                        timeZones.setTimeZoneGMTOffset(tz.getTimeZoneGmtoffset());
                        timeZones.setDaylightSavingEndTime(tz.getDaylightSavingEndTime());
                        timeZones.setDaylightSavingOffset(tz.getDaylightSavingOffset());
                        timeZones.setDaylightSavingStartRule(tz.getDaylightSavingStartRule());
                        timeZones.setDaylightSavingStartTime(tz.getDaylightSavingStartTime());
                        timeZones.setDaylightSavingStopRule(tz.getDaylightSavingStopRule());
                        GregorianCalendar gc = new GregorianCalendar();
                        gc.setTime(tz.getCreatedDate());
                        timeZones.setCreatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                        gc.setTime(tz.getUpdatedDate());
                        timeZones.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                        se.info24.utiljaxb.UserTimeZone usertz = new UserTimeZone();
                        usertz.setUseDayLightSaving(utz.getUseDaylightSaving());
                        timeZones.getUserTimeZone().add(usertz);
                        util.getTimezones().add(timeZones);
                    }
                } else {
                    util.getMsgStatus().setResponseCode(-10);
                    util.getMsgStatus().setResponseText("User Permission is not given");
                    return util;
                }
            } else {
                util.getMsgStatus().setResponseCode(-3);
                util.getMsgStatus().setResponseText("User Session is Not Valid");
                return util;
            }
        } catch (DatatypeConfigurationException ex) {
            exceptionLog(ex);
            util.getMsgStatus().setResponseCode(-1);
            util.getMsgStatus().setResponseText("Error Occured");
            return util;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return util;
    }

    private TingcoUsers getUserTypes(String countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoUsers userXml = null;
        Session session = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        try {
            userXml = tingcoUserXml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {

                if (countryId == null) {
                    userXml.getMsgStatus().setResponseCode(-1);
                    userXml.getMsgStatus().setResponseText("CountryID should not be null");
                    return userXml;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
//                if (ht.containsKey(TCMUtil.USERS)) {
//                    ArrayList<String> operations = ht.get(TCMUtil.USERS);
//                    for (int i = 0; i < operations.size(); i++) {
//                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                hasPermission = true;
//                        }
//                    }
//                }

                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<UserTypeTranslations2> userTypeList = userDAO.getUserTypes(session, countryId);
                    userXml = tingcoUserXml.buildUserTypes(userXml, userTypeList);
                } else {
                    userXml.getMsgStatus().setResponseCode(-10);
                    userXml.getMsgStatus().setResponseText("User Permission is not given");
                    return userXml;
                }
            } else {
                userXml.getMsgStatus().setResponseCode(-3);
                userXml.getMsgStatus().setResponseText("User Session is Not Valid");
                return userXml;
            }
        } catch (DatatypeConfigurationException ex) {
            exceptionLog(ex);
            userXml.getMsgStatus().setResponseCode(-1);
            userXml.getMsgStatus().setResponseText("Error Occured");
            return userXml;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return userXml;
    }

    private TingcoUsers getUsersDetails(int countryId, String searchFilter, String groupName) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoUsers tingcoUsers = null;
        Session session = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        try {
            tingcoUsers = tingcoUserXml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (countryId <= 0) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("CountryId should not be empty");
                    return tingcoUsers;
                }
                if (!searchFilter.equals("")) {
                    searchFilter = searchFilter.split("/")[2];
                } else {
                    searchFilter = null;
                }
                if (!groupName.equals("")) {
                    groupName = groupName.split("/")[2];
                } else {
                    groupName = null;
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
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        Users2 users2 = userDAO.getUserById(sessions2.getUserId(), session);
                        List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(users2.getGroupId(), session);
                        Set<String> groupIdsList = groupDAO.getGroupIdsList(users2.getGroupId(), groupTrustsList);

                        List<Users2> users2List = new ArrayList<Users2>();
                        List<GroupTranslations> groupTranslationsList = new ArrayList<GroupTranslations>();
                        Set<String> groupid = new HashSet<String>();

                        /**
                         * SPLIT LIST SIZE 30 MAR 2014
                         */
                        List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupIdsList), 2000);

                        if (groupName != null && searchFilter != null) {
                            if (TCMUtil.isValidUUID(groupName)) {
                                groupid.add(groupName);
                                groupTranslationsList = groupDAO.getGroupTranslationsById(new ArrayList<String>(groupid), countryId, session);
                            } else {
                                for (List<String> list : listsplit) {
                                    List<GroupTranslations> groupTranslationsListTemp = groupDAO.getGroupTransByGroupIdandSearchString(new HashSet<String>(list), groupName, countryId, session);
                                    groupTranslationsList.addAll(groupTranslationsListTemp);
                                    if (groupTranslationsList.size() > 200) {
                                        groupTranslationsList = groupTranslationsList.subList(0, 200);
                                        break;
                                    }
                                }
                                //groupTranslationsList = groupDAO.getGroupTransByGroupIdandSearchString(groupIdsList, groupName, countryId, session);
                                for (GroupTranslations groupTranslations : groupTranslationsList) {
                                    groupid.add(groupTranslations.getId().getGroupId());
                                }
                            }
                            if (!groupid.isEmpty()) {
                                users2List = userDAO.getUserByGroupId(groupid, searchFilter, sessions2, session);
                            }
                        } else if (groupName == null && searchFilter != null) {
                            users2List = userDAO.getUserByGroupId(null, searchFilter, sessions2, session);
                            if (users2List != null) {
                                if (!users2List.isEmpty()) {
                                    for (Users2 user : users2List) {
                                        GroupTranslations gt = groupDAO.getGroupTranslationsByIds(user.getGroupId(), countryId, session);
                                        groupTranslationsList.add(gt);
                                    }
                                }
                            }
                        } else if (groupName != null && searchFilter == null) {
                            if (TCMUtil.isValidUUID(groupName)) {
                                groupid.add(groupName);
                                groupTranslationsList = groupDAO.getGroupTranslationsById(new ArrayList<String>(groupid), countryId, session);
                            } else {
                                for (List<String> list : listsplit) {
                                    List<GroupTranslations> groupTranslationsListTemp = groupDAO.getGroupTransByGroupIdandSearchString(new HashSet<String>(list), groupName, countryId, session);
                                    groupTranslationsList.addAll(groupTranslationsListTemp);
                                    if (groupTranslationsList.size() > 200) {
                                        groupTranslationsList = groupTranslationsList.subList(0, 200);
                                        break;
                                    }
                                }
                                //groupTranslationsList = groupDAO.getGroupTransByGroupIdandSearchString(groupIdsList, groupName, countryId, session);
                                for (GroupTranslations groupTranslations : groupTranslationsList) {
                                    groupid.add(groupTranslations.getId().getGroupId());
                                }
                            }
                            if (!groupid.isEmpty()) {
                                users2List = userDAO.getUserByGroupId(groupid, null, sessions2, session);
                            }

                        } else {
//                            users2List = userDAO.getUserByGroupId(groupIdsList, null, sessions2, session); //both usergroup and trusted group. not used as per .NET team requirement
                            users2List = userDAO.getUsers2ByGroupId(users2.getGroupId(), session); //get only logged in group users list. No trusted group.
                            if (users2List != null) {
                                if (!users2List.isEmpty()) {
                                    users2List = userDAO.getSearchUser(userDAO.getUserIdsList(users2List), session);
                                    groupTranslationsList.add(groupDAO.getGroupTranslationsByIds(users2.getGroupId(), countryId, session));
                                }
                            }
                        }
                        if (users2List != null) {
                            if (!users2List.isEmpty()) {
                                tingcoUsers = tingcoUserXml.buildGetUserDetails(tingcoUsers, users2List, groupTranslationsList, timeZoneGMToffset);
                            } else {
                                tingcoUsers.getMsgStatus().setResponseCode(-1);
                                tingcoUsers.getMsgStatus().setResponseText("No Data found");
                                return tingcoUsers;
                            }
                        } else {
                            tingcoUsers.getMsgStatus().setResponseCode(-1);
                            tingcoUsers.getMsgStatus().setResponseText("No Data found");
                            return tingcoUsers;
                        }
                    } else {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                        return tingcoUsers;
                    }
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (DatatypeConfigurationException ex) {
            exceptionLog(ex);
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

    private TingcoUsers getUsersList(int countryId, String searchFilter) throws DatatypeConfigurationException {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoUsers tingcoUsers = null;
        Session session = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        tingcoUsers = tingcoUserXml.buildUserTemplate();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (countryId <= 0) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("CountryId should not be empty");
                    return tingcoUsers;
                }
                if (!searchFilter.equals("")) {
                    searchFilter = searchFilter.split("/")[2];
                } else {
                    searchFilter = null;
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
                    List<Users2> users2List = new ArrayList<Users2>();
                    users2List = userDAO.getUserBySearchFilter(searchFilter, session);
                    if (!users2List.isEmpty()) {
                        tingcoUsers = tingcoUserXml.buildGetUsersList(tingcoUsers, countryId, users2List, session);
                    } else {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("No Users found");
//                        return tingcoUsers;
                    }
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception e) {
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured ");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUsers;

    }

    private TingcoUsers getUsers2ListbyGroupId(String groupId, String searchString) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoUsers tingcoUsers = null;
        Session session = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        try {
            tingcoUsers = tingcoUserXml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId.equals("")) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("GroupId should not be empty");
                    return tingcoUsers;
                }
                if (!searchString.equals("")) {
                    searchString = searchString.split("/")[2];
                } else {
                    searchString = null;
                }
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
                    List<String> usersIdsList = new ArrayList<String>();
                    if (searchString != null) {
                        usersIdsList = userDAO.getUsers2ByGroupIdUsingCriteria(groupId, searchString, session);
                    } else {
                        usersIdsList = userDAO.getUsers2ByGroupIdUsingCriteria(groupId, session);
                    }

                    if (!usersIdsList.isEmpty()) {
                        List<Users2> users2List = userDAO.getUser2ListByUserId(usersIdsList, session);
                        tingcoUsers = tingcoUserXml.buildUsersByGroupId(tingcoUsers, users2List);
                    } else {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("No Users found");
                        return tingcoUsers;
                    }
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception e) {
            exceptionLog(e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured ");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUsers;
    }

    private TingcoUsers getUsersListForListUserspage(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoUsers tingcoUsers = null;
        Session session = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        try {
            tingcoUsers = tingcoUserXml.buildUserTemplate();
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
                    TingcoUsers users = (TingcoUsers) JAXBManager.getInstance().unMarshall(content, TingcoUsers.class);
                    User user = users.getUsers().getUser().get(0);
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                    List<Object> usersList = new ArrayList<Object>();
                    if (user.getGroupID().getGroupName() == null && user.getFirstName() == null && user.getLastName() == null && user.getUserEmail() == null && user.getAddress() == null && user.getCountryID().getCountryName() == null) {
                        usersList = userDAO.getDefaultUsersListForListUserPage(user, session);
                    } else {
                        usersList = userDAO.getUsersListForListUserPage(user, session);
                    }
                    if (!usersList.isEmpty()) {
                        tingcoUsers = tingcoUserXml.buildUsersListForListUsersPage(tingcoUsers, usersList, timeZoneGMToffset);
                    } else {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("No data found");
                        return tingcoUsers;
                    }
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception e) {
            exceptionLog(e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured ");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUsers;
    }

    private TingcoUsers getUsersListForSearchUserspage(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoUsers tingcoUsers = null;
        Session session = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        try {
            tingcoUsers = tingcoUserXml.buildUserTemplate();
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
                    TingcoUsers users = (TingcoUsers) JAXBManager.getInstance().unMarshall(content, TingcoUsers.class);
                    User user = users.getUsers().getUser().get(0);
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                    List<Object> usersList = new ArrayList<Object>();
                    if (user.getGroupID().getGroupName() == null && user.getFirstName() == null && user.getLastName() == null && user.getUserEmail() == null && user.getAddress() == null && user.getCountryID().getCountryName() == null) {
                        usersList = userDAO.getDefaultUsersListForSearchUsersPage(user, session);
                    } else {
                        usersList = userDAO.getUsersListForSearchUsersPage(user, session);
                    }
                    if (!usersList.isEmpty()) {
                        tingcoUsers = tingcoUserXml.buildUsersListForSearchUsersPage(tingcoUsers, usersList, timeZoneGMToffset);
                    } else {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("No data found");
                        return tingcoUsers;
                    }
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception e) {
            exceptionLog(e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured ");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUsers;
    }

    private TingcoUsers newUser(String loginName, String pwd, String firstName, String lastName, String email, String groupID, String roleID, String countryID, String phone, HttpServletRequest request) throws DatatypeConfigurationException {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoUsers user = null;
        GregorianCalendar gc = new GregorianCalendar();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            UserManager manager = new UserManager();
            UserSessions2 user_sess = manager.getUserSession(session, request);
            if (user_sess.getActiveToDate().after(gc.getTime())) {
                String userID = manager.addNewUser(loginName, pwd, firstName, lastName, email, groupID, roleID, countryID, phone, user_sess.getApplicationId(), session);
                user = manager.buildUserTemplate();
                Users users = new Users();
                se.info24.usersjaxb.UserSession sess = new se.info24.usersjaxb.UserSession();
                sess.setApplicationID(user_sess.getApplicationId());
                sess.setUserID(userID);
                users.setUserSession(sess);
                user.setUsers(users);
            } else {
                TCMUtil tcm = new TCMUtil();
                user = tcm.sessionExpired();
            }
            return user;
        } catch (DatatypeConfigurationException e) {
            exceptionLog(e);
            user.getMsgStatus().setResponseCode(-1);
            user.getMsgStatus().setResponseText("Error Occured ");
            return user;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }

    }

    private TingcoUsers newUserToActivate(String userID, String domainID, String appID, String emailID, String groupID, String firstName, String lastName, String nickName, String countryID, HttpServletRequest request) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoUsers user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            UserManager manager = new UserManager();
            user = manager.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                manager.addNewUserToActivate(userID, domainID, appID, emailID, groupID, firstName, lastName, nickName, countryID, session);
            } else {
                TCMUtil tcm = new TCMUtil();
                user = tcm.sessionExpired();
            }
        } catch (DatatypeConfigurationException e) {
            exceptionLog(e);
            user.getMsgStatus().setResponseCode(-1);
        } catch (Exception e) {
            exceptionLog(e);
            user.getMsgStatus().setResponseCode(-1);
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return user;
    }

    private TingcoUsers deletedUser(String userID, HttpServletRequest request) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoUsers user = null;
        boolean del = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            UserManager manager = new UserManager();
            if (request.getAttribute("USERSESSION") != null) {
                del = manager.deleteUser(userID, session);
                user = manager.buildUserTemplate();
                if (del) {
                    user.getMsgStatus().setResponseText("User Deleted");
                } else {
                    user.getMsgStatus().setResponseText("Error");
                }
            } else {
                TCMUtil tcm = new TCMUtil();
                user = tcm.sessionExpired();
            }
        } catch (DatatypeConfigurationException e) {
            exceptionLog(e);
            user.getMsgStatus().setResponseCode(-1);
            user.getMsgStatus().setResponseText("Error Occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return user;
    }

    private TingcoUsers updateUserInformation(String userId, String firstName, String lastName, String groupId, String emailId, String domainId, String countryId, String activeFromDate, String activeToDate, String islockedOut, String userTypeId, String userMobilePhone, String addressStreet, String addressStreet2, String addressSuite, String addressZip, String addressCity, String addressDesc, String addressRegion, String addressId, String addressCountryId) {
        long requestedTime = System.currentTimeMillis();
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXML = new se.info24.jaxbUtil.TingcoUserXML();
        TingcoUsers tingcoUsers = null;
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoUsers = tingcoUserXML.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (userId.equals("")) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("UserID should not be empty");
                    return tingcoUsers;
                }
                if (firstName.equals("")) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("First Name should not be empty");
                    return tingcoUsers;
                }
                if (lastName.equals("")) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("Last Name should not be empty");
                    return tingcoUsers;
                }
                if (groupId.equals("")) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("GroupId should not be empty");
                    return tingcoUsers;
                }
                if (emailId.equals("")) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("EmailId should not be empty");
                    return tingcoUsers;
                }
                if (countryId.equals("")) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("CountryId should not be empty");
                    return tingcoUsers;
                }

                if (activeFromDate.equals("")) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("ActiveFromDate should not be empty");
                    return tingcoUsers;
                }
                if (activeToDate.equals("")) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("ActiveToDate should not be empty");
                    return tingcoUsers;
                }
                if (islockedOut.equals("")) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("islockedOut should not be empty");
                    return tingcoUsers;
                }
                if (userTypeId.equals("")) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("UserTypeId should not be empty");
                    return tingcoUsers;
                }
                if (!userMobilePhone.equals("")) {
                    userMobilePhone = userMobilePhone.split("/")[2];
                } else {
                    userMobilePhone = null;// this code added on the requirement of .net team
                }

                if (!addressStreet.equals("")) {
                    addressStreet = addressStreet.split("/")[2];
                } else {
                    addressStreet = null;
                }
                if (!addressStreet2.equals("")) {
                    addressStreet2 = addressStreet2.split("/")[2];
                } else {
                    addressStreet2 = null;
                }
                if (!addressSuite.equals("")) {
                    addressSuite = addressSuite.split("/")[2];
                } else {
                    addressSuite = null;
                }
                if (!addressZip.equals("")) {
                    addressZip = addressZip.split("/")[2];
                } else {
                    addressZip = null;
                }
                if (!addressCity.equals("")) {
                    addressCity = addressCity.split("/")[2];
                } else {
                    addressCity = null;
                }
                if (!addressDesc.equals("")) {
                    addressDesc = addressDesc.split("/")[2];
                } else {
                    addressDesc = null;
                }
                if (!addressRegion.equals("")) {
                    addressRegion = addressRegion.split("/")[2];
                } else {
                    addressRegion = null;
                }

                if (!addressId.equals("")) {
                    addressId = addressId.split("/")[2];
                } else {
                    addressId = null;
                }
                if (!addressCountryId.equals("")) {
                    addressCountryId = addressCountryId.split("/")[2];
                } else {
                    addressCountryId = null;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.USERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.USERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    if (userDAO.getUserByAppIdEmail(domainId, emailId, session)) {
                        UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                        if (userTimeZones2 != null) {
                            String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                            List<Users2> users2List = userDAO.getUsers2ById(userId, session);
                            if (!users2List.isEmpty()) {
                                if (!emailId.equalsIgnoreCase(users2List.get(0).getId().getLoginName())) {
                                    if (userDAO.getUserByAppIdEmail(domainId, emailId, session)) {
                                        TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update User", firstName + " " + lastName, "Users2", "Failed");
                                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                                        tingcoUsers.getMsgStatus().setResponseText("Data Already Exist.");
                                        return tingcoUsers;
                                    }
                                }
                            }
                            if (!userDAO.updateUserInformation(userId, firstName, lastName, groupId, emailId, countryId, activeFromDate, activeToDate, islockedOut, userTypeId, userMobilePhone, session, timeZoneGMToffset, addressStreet, addressStreet2, addressSuite, addressZip, addressCity, addressDesc, addressRegion, addressId, addressCountryId)) {
                                TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update User", firstName + " " + lastName, "Users2", "Failed");
                                tingcoUsers.getMsgStatus().setResponseCode(-1);
                                tingcoUsers.getMsgStatus().setResponseText("Error Occured while Updating");
                                return tingcoUsers;
                            } else {
                                TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update User", firstName + " " + lastName, "Users2", "Success");
                            }
                        } else {
                            tingcoUsers.getMsgStatus().setResponseCode(-1);
                            tingcoUsers.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                            return tingcoUsers;
                        }
                    } else {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("No user exists with this e-mail in the system.");
                        return tingcoUsers;
                    }
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (DatatypeConfigurationException ex) {
            exceptionLog(ex);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured while Updating");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUsers;
    }

    private TingcoUsers updatedUser(String userID, String firstName, String lastName, String email, String phone, String groupID, HttpServletRequest request) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        long requestedTime = System.currentTimeMillis();
        UserManager manager = new UserManager();
        // UserSessions2 user_sess = manager.getUserSession(session,request);
        UserSessions2 user_sess = (UserSessions2) request.getAttribute("USERSESSION");
        TingcoUsers user = null;
        if (!firstName.equals("")) {
            firstName = firstName.split("/")[2];
        }
        if (!lastName.equals("")) {
            lastName = lastName.split("/")[2];
        }
        if (!email.equals("")) {
            email = email.split("/")[2];
        }
        if (!phone.equals("")) {
            phone = phone.split("/")[2];
        }
        if (!groupID.equals("")) {
            groupID = groupID.split("/")[2];
        }

        try {
            if (request.getAttribute("USERSESSION") != null) {
                manager.updateUser(userID, firstName, lastName, email, phone, groupID, session);
                user = manager.getUser(userID, session, user_sess);
            } else {
                TCMUtil tcm = new TCMUtil();
                user = tcm.sessionExpired();
            }
        } catch (DatatypeConfigurationException e) {
            exceptionLog(e);
        } finally {
            session.close();
            delayLog(requestedTime);
        }
        return user;
    }

    private TingcoUsers getUser(String userID, String appID, HttpServletRequest request) {
        long requestedTime = System.currentTimeMillis();
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserManager manager = new UserManager();
        TCMUtil tcm = new TCMUtil();
        TingcoUsers user = null;
        if (request.getAttribute("USERSESSION") != null) {
            UserSessions2 user_sess = (UserSessions2) request.getAttribute("USERSESSION");
            try {
                user = manager.getUser(userID, session, user_sess);
            } catch (DatatypeConfigurationException e) {
                exceptionLog(e);
            } catch (Exception e) {
                exceptionLog(e);
                try {
                    user = manager.buildUserTemplate();
                    user.getMsgStatus().setResponseCode(-1);
                    user.getMsgStatus().setResponseText("Invalid UserID");
                } catch (Exception ex) {
                    exceptionLog(ex);
                }
            } finally {
                session.close();
                delayLog(requestedTime);
//                System.gc();
            }
        } else {
            try {
                user = tcm.sessionExpired();
            } catch (DatatypeConfigurationException ex) {
                exceptionLog(ex);
            }
        }
        return user;
    }

    private TingcoEV usagerecords(String userAlias, String month, String year) throws DatatypeConfigurationException {
        // boolean hasPermission = false;
        long requestedTime = System.currentTimeMillis();
        TingcoEV usageRecords = new TingcoEV();
        TingcoUserXML userXML = new TingcoUserXML();
        usageRecords = userXML.buildTingcoUserTemplate();
        if (request.getAttribute("USERSESSION") != null) {
//            Hashtable<String,ArrayList> ht = (Hashtable<String, ArrayList>) request.getSession(true).getAttribute(request.getSession(true).getId());
//            if (ht.containsKey(TCMUtil.USERALIAS)) {
//                ArrayList<String> operations = ht.get(TCMUtil.USERALIAS);
//                for(int i=0;i<operations.size();i++){
//                    if(operations.get(i).equalsIgnoreCase(TCMUtil.READ)){
//                        hasPermission = true;
//                    }
//                }
//            }
            if (userAlias == null) {
                delayLog(requestedTime);
                usageRecords.getMsgStatus().setResponseCode(-1);
                usageRecords.getMsgStatus().setResponseText("User Alias should not be empty");
                return usageRecords;
            } else {
                // if (hasPermission == true) {
                usageRecords = userXML.buildTingcoUsageRecordsXML(userAlias, month, year);
                delayLog(requestedTime);
                return usageRecords;
//                } else {
//                    usageRecords.getMsgStatus().setResponseCode(-10);
//                    usageRecords.getMsgStatus().setResponseText("User Permission is not given");
//                    return usageRecords;
//                }
            }

        } else {
            delayLog(requestedTime);
            usageRecords.getMsgStatus().setResponseCode(-3);
            usageRecords.getMsgStatus().setResponseText("User Session is Not Valid");
            return usageRecords;

        }

    }

    private TingcoUsers userRoles(String userID, HttpServletRequest request) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserManager manager = new UserManager();
        TingcoUsers user = null;
        long requestedTime = System.currentTimeMillis();
        try {
            user = manager.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserRoles roles = manager.getUserRoles(userID, session);
                Users us = new Users();
                User u = new User();
                u.setUserRoles(roles);
                us.getUser().add(u);
                user.setUsers(us);
            } else {
                TCMUtil tcm = new TCMUtil();
                user = tcm.sessionExpired();
            }
        } catch (DatatypeConfigurationException e) {
            exceptionLog(e);
        } finally {
            session.close();
            delayLog(requestedTime);
        }
        return user;
    }

    private TingcoUsers allUSers(String domainId, HttpServletRequest request) {
        boolean hasPermission = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserManager manager = new UserManager();
        TingcoUsers user = null;
        long requestedTime = System.currentTimeMillis();
        try {
//            Hashtable<String,ArrayList> ht = (Hashtable<String, ArrayList>) request.getSession(true).getAttribute(request.getSession(true).getId());
//            if (ht.containsKey(TCMUtil.USERS)) {
//                ArrayList<String> operations = ht.get(TCMUtil.USERS);
//                for(int i=0;i<operations.size();i++){
//                    if(operations.get(i).equalsIgnoreCase(TCMUtil.READ)){
            hasPermission = true;
//                    }
//                }
//            }
            if (request.getAttribute("USERSESSION") != null) {
                user = manager.buildUserTemplate();
                if (hasPermission) {
                    user = manager.getAllUsers(domainId, user, session);
                } else {
                    user.getMsgStatus().setResponseCode(-10);
                    user.getMsgStatus().setResponseText("User Permission is not given");
                    return user;
                }
            } else {
                TCMUtil tcm = new TCMUtil();
                user = tcm.sessionExpired();
            }
        } catch (DatatypeConfigurationException e) {
            exceptionLog(e);
        } finally {
            session.close();
            delayLog(requestedTime);
        }
        return user;
    }

    private TingcoUsers userActivate(String userID, String domainID, String appID, HttpServletRequest request) {
        long requestedTime = System.currentTimeMillis();
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserManager manager = new UserManager();
        TingcoUsers user = null;
        try {
            user = manager.buildUserTemplate();
            user = manager.activateUser(userID, domainID, appID, user, session);

        } catch (Exception e) {
            exceptionLog(e);
        } finally {
            session.close();
            delayLog(requestedTime);
        }
        return user;
    }

    private TingcoUsers expireSession(HttpServletRequest request) {
        long requestedTime = System.currentTimeMillis();
//        TingcoAPI tingcoAPI = null;
        TingcoUsers tingcoUsers = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        try {
            tingcoUsers = tingcoUserXml.buildUserTemplate();
//            tingcoAPI = tingcoAPIXML.buildTingcoAPITemplate();
            if (request.getHeader("AuthenticationToken") != null) {
                session.beginTransaction();
                UserSessions2 userSession2 = (UserSessions2) request.getAttribute("USERSESSION");
                GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                if (userSession2 != null) {
                    userSession2DAO.removeUserSession2(userSession2, session);
                    UserLog userLog = new UserLog();
                    userLog.setAction("Logout");
                    userLog.setTableName("Users2;UserSessions2");
                    userLog.setUserId(userSession2.getUserId());
                    userLog.setRequestIp(request.getRemoteAddr());
                    userLog.setCreatedDate(gc.getTime());
                    userLog.setUpdatedDate(gc.getTime());
                    userDAO.saveUserLog(userLog, session);
                } else { // If no usersession2 obj from DB no record with this Auth.
                    tingcoUsers.getMsgStatus().setResponseCode(-3);
                    tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                }
            } else { // If no Auth Parameter in HttpHeader
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
            }

        } catch (DatatypeConfigurationException dce) {
            exceptionLog(dce);
        } catch (Exception e) {
            exceptionLog(e);
        } finally {
            session.close();
            delayLog(requestedTime);
        }
        return tingcoUsers;
    }

    private TingcoUtils userInvitation(String invitedBy, String invitedTo, String domainID, String appID, HttpServletRequest request) {
        long requestedTime = System.currentTimeMillis();
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserManager manager = new UserManager();
        TingcoUtilsXML tuXML = new TingcoUtilsXML();
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();

            if (request.getHeader("AuthenticationToken") == null) {
                tingcoUtils = manager.inviteNewUser(invitedBy, invitedTo, domainID, appID, tingcoUtils, session);
                return tingcoUtils;
            }

            if (request.getAttribute("USERSESSION") != null) {
                tingcoUtils = manager.inviteNewUser(invitedBy, invitedTo, domainID, appID, tingcoUtils, session);
            } else {
                tingcoUtils.getMsgStatus().setResponseCode(-3);
                tingcoUtils.getMsgStatus().setResponseText("Session Expired");
            }
        } catch (DatatypeConfigurationException ex) {
            exceptionLog(ex);
        } finally {
            session.close();
            delayLog(requestedTime);
        }
        return tingcoUtils;
    }

    private TingcoUsers lockedUser(String userID, String locked, HttpServletRequest request) {
        long requestedTime = System.currentTimeMillis();
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserManager manager = new UserManager();
        TCMUtil tcm = new TCMUtil();
        TingcoUsers user = null;
        if (request.getAttribute("USERSESSION") != null) {
            try {
                user = manager.buildUserTemplate();
                manager.lockUser(userID, locked, session);
            } catch (Exception e) {
                exceptionLog(e);
                try {
                    user = manager.buildUserTemplate();
                    user.getMsgStatus().setResponseCode(-1);
                    user.getMsgStatus().setResponseText("Invalid UserID");
                } catch (Exception ex) {
                    exceptionLog(ex);
                }

            } finally {
                session.close();
                delayLog(requestedTime);
            }
        } else {
            try {
                user = tcm.sessionExpired();
            } catch (DatatypeConfigurationException ex) {
                exceptionLog(ex);
            }
        }
        return user;
    }

    private TingcoUsers addUserTimeZone(String userID, String timezoneID, String useDayLightSaving) {
        long requestedTime = System.currentTimeMillis();
        TingcoUsers tingcoUsers = null;
        Session session = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXML = new se.info24.jaxbUtil.TingcoUserXML();
        try {
            tingcoUsers = tingcoUserXML.buildUserTemplate();

            if (request.getAttribute("USERSESSION") != null) {
                if (userID == null) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("UserID is should not be empty");
                    return tingcoUsers;
                } else if (timezoneID == null) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("TimezoneID is should not be empty");
                    return tingcoUsers;
                } else if (useDayLightSaving == null) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("UseDayLightSaving is should not be empty");
                    return tingcoUsers;
                }
                session = HibernateUtil.getSessionFactory().openSession();
                UserTimeZonesToActivate utz2 = new UserTimeZonesToActivate();
                utz2.setTimeZoneId(timezoneID);
                utz2.setUserId(userID);
                utz2.setUseDaylightSaving(new Integer(useDayLightSaving));
                GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                utz2.setCreatedDate(gc.getTime());
                utz2.setUpdatedDate(gc.getTime());
                if (userDAO.saveUserTimezonesToActivate(utz2, session)) {
                    return tingcoUsers;
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("Error Occured while saving!");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
            }
        } catch (DatatypeConfigurationException ex) {
            exceptionLog(ex);
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUsers;
    }

    private TingcoUsers updateUserProfileDetails(String loginname, String domainid, String userId, String firstName, String lastName, String countryId, String emailId, String userMobilePhone) {
        long requestedTime = System.currentTimeMillis();
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXML = new se.info24.jaxbUtil.TingcoUserXML();
        TingcoUsers tingcoUsers = null;
        Session session = null;
        try {
            tingcoUsers = tingcoUserXML.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (loginname.equals("")) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("Login Name should not be empty");
                    return tingcoUsers;
                }
                if (domainid.equals("")) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("Domain ID should not be empty");
                    return tingcoUsers;
                }
                if (userId.equals("")) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("UserID should not be empty");
                    return tingcoUsers;
                }
                if (firstName.equals("")) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("First Name should not be empty");
                    return tingcoUsers;
                }
                if (lastName.equals("")) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("Last Name should not be empty");
                    return tingcoUsers;
                }
                if (countryId.equals("")) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("CountryId should not be empty");
                    return tingcoUsers;
                }
                if (emailId.equals("")) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("EmailId should not be empty");
                    return tingcoUsers;
                }
                if (!userMobilePhone.equals("")) {
                    userMobilePhone = userMobilePhone.split("/")[2];
                } else {
                    userMobilePhone = "NULL";// this code added on the requirement of .net team
                }
                session = HibernateUtil.getSessionFactory().openSession();
                if (!userDAO.updateUserProfileDetails(loginname, domainid, userId, firstName, lastName, countryId, emailId, userMobilePhone, session)) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("Error Occured while Updating");
                    return tingcoUsers;
                }
                return tingcoUsers;
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (DatatypeConfigurationException ex) {
            exceptionLog(ex);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured while Updating");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    public TingcoUsers getUsersByGroupId(String groupid, String searchString) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXML = new se.info24.jaxbUtil.TingcoUserXML();
        TingcoUsers tingcoUsers = null;
        Session session = null;
        try {
            tingcoUsers = tingcoUserXML.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupid == null) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("GroupID should not be empty");
                    return tingcoUsers;
                }
                if (!searchString.equals("")) {
                    searchString = searchString.split("/")[2];
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
                    List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(groupid, session);
                    Set<String> groupIdsList = groupDAO.getGroupIdsList(groupid, groupTrustsList);
                    /**
                     * SPLIT LIST SIZE 30 MAR 2014
                     */
                    List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupIdsList), 2000);
                    List<Users2> usersList = new ArrayList<Users2>();
                    for (List<String> list : listsplit) {
                        List<Users2> usersListTemp = userDAO.getUsersByGroupIdsList(new HashSet<String>(list), session);
                        usersList.addAll(usersListTemp);
                        if (usersList.size() > 100) {
                            usersList = usersList.subList(0, 100);
                            break;
                        }
                    }
                    //List<Users2> usersList = userDAO.getUsersByGroupIdsList(groupIdsList, session);
                    List<String> userIdsList = userDAO.getUserIdsList(usersList);
                    List<Users2> users2List = userDAO.getUsers2BySearchString(searchString, userIdsList, session);
                    if (!usersList.isEmpty()) {
                        tingcoUsers = tingcoUserXML.buildUsersByGroupId(tingcoUsers, users2List);
                    } else {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("No Record found for the given GroupId");
                        return tingcoUsers;
                    }
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }

            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception e) {
            exceptionLog(e);
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUsers;
    }

    public TingcoUsers getUsers2DetailsbyUserID(String userID, String countryid) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXML = new se.info24.jaxbUtil.TingcoUserXML();
        TingcoUsers tingcoUsers = null;
        Session session = null;
        try {
            tingcoUsers = tingcoUserXML.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
//            Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) request.getSession(true).getAttribute(request.getSession(true).getId());
//            if (ht.containsKey(TCMUtil.USERS)) {
//                ArrayList<String> operations = ht.get(TCMUtil.USERS);
//                for (int i = 0; i < operations.size(); i++) {
//                    if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                hasPermission = true;
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
//                    }
//                }
//            }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();

                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        Users2 user = new Users2();
                        UserTimeZones2 utz = new UserTimeZones2();
                        GroupTranslations gt = new GroupTranslations();
                        List<Users2> userList = userDAO.getUsers2ById(userID, session);
                        if (!userList.isEmpty()) {
                            user = userList.get(0);
                            List<GroupTranslations> gtl = userDAO.getGroupTranslationsById(user.getGroupId(), countryid, session);
                            if (!gtl.isEmpty()) {
                                gt = gtl.get(0);
                            }
                            List<UserTimeZones2> utzl = userDAO.getUserTimeZones(userID, session);
                            if (!utzl.isEmpty()) {
                                utz = utzl.get(0);
                            }
                            ObjectAddresses oa = userDAO.getObjectAddressesByID(session, userID, "3");
                            Object addressesObject = null;
                            if (oa != null) {
                                addressesObject = userDAO.getAddress(oa.getAddressId(), session);
                            }
                            tingcoUsers = tingcoUserXML.buildUsers2DetailsbyUserID(tingcoUsers, user, gt, utz, timeZoneGMToffset, oa, addressesObject);

                        } else {
                            tingcoUsers.getMsgStatus().setResponseCode(-1);
                            tingcoUsers.getMsgStatus().setResponseText("No Record found");
                            return tingcoUsers;
                        }
                    } else {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                        return tingcoUsers;
                    }
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception ex) {
            exceptionLog(ex);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured while Retrieving User Details");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUsers;
    }

    public void delayLog(long requestedTime) {
        logger.info(" [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]");
    }

    private void exceptionLog(Exception e) {
        StringWriter stack = new StringWriter();
        e.printStackTrace(new PrintWriter(stack));
        logger.error(stack.toString());
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

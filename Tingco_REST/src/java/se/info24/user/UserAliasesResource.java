/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.user;

import java.math.BigDecimal;
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
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.xml.datatype.DatatypeConfigurationException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.group.GroupDAO;
import se.info24.ismOperationsPojo.Blacklist;
import se.info24.ismOperationsPojo.FraudLog;
import se.info24.pojo.Currency;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupTrusts;
import se.info24.pojo.ObjectGroupTranslations;
import se.info24.pojo.ObjectGroups;
import se.info24.pojo.ObjectTags;
import se.info24.pojo.OgmuserAlias;
import se.info24.pojo.OgmuserAliasId;
import se.info24.pojo.UserAlias;
import se.info24.pojo.UserAliasDetails;
import se.info24.pojo.UserAliasOrders;
import se.info24.pojo.UserAliasTypes;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.UserTimeZones2;
import se.info24.pojo.Users2;
import se.info24.restUtil.RestUtilDAO;
import se.info24.usersjaxb.OGMUserAlias;
import se.info24.usersjaxb.TingcoUsers;
import se.info24.usersjaxb.UserAliasOrder;
import se.info24.usersjaxb.UserAliases;
import se.info24.util.HibernateUtil;
import se.info24.util.JAXBManager;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Sumit
 */
@Path("/useralias")
public class UserAliasesResource {

    @Context
    private UriInfo context;
    @Context
    HttpServletRequest request;
    UserManager manager = new UserManager();
    TingcoUsers tingcoUsers = new TingcoUsers();
    TingcoUserXML tingcoUserXML = new TingcoUserXML();
    UserDAO userDAO = new UserDAO();
    RestUtilDAO utilDAO = new RestUtilDAO();
    GroupDAO groupDAO = new GroupDAO();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of UserAliasesResource */
    public UserAliasesResource() {
    }

    /**
     * GetUserKeyDetails
     * @param useraliasid
     * @return TingcoUsers
     */
    @GET
    @Path("/getuserkeydetails/rest/{rest}/useraliasid/{useraliasid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetUserKeyDetails", desc = "Get UserKeyDetails", req_Params = {"UserAlias;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_UserKeyDetails(@PathParam("useraliasid") String useraliasid) {
        return getUserKeyDetails(useraliasid);
    }

    /**
     * GetUserKeyDetails
     * @param useraliasid
     * @return TingcoUsers
     */
    @GET
    @Path("/getuserkeydetails/json/{json}/useraliasid/{useraliasid}")
    @Produces("application/json")
    public TingcoUsers getJson_UserKeyDetails(@PathParam("useraliasid") String useraliasid) {
        return getUserKeyDetails(useraliasid);
    }

    /**
     * UpdateUserKeyOrganization
     * @param useraliasid
     * @param groupid
     * @return TingcoUsers
     */
    @GET
    @Path("/updateuserkeyorganization/rest/{rest}/useraliasid/{useraliasid}/groupid/{groupid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "UpdateUserKeyOrganization", desc = "Update UserKeyOrganization", req_Params = {"UserAlias;UUID", "groupId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_updateUserKeyOrganization(@PathParam("useraliasid") String useraliasid, @PathParam("groupid") String groupId) {
        return updateUserKeyOrganization(useraliasid, groupId);
    }

    /**
     * UpdateUserKeyOrganization
     * @param useraliasid
     * @param groupid
     * @return TingcoUsers
     */
    @GET
    @Path("/updateuserkeyorganization/json/{json}/useraliasid/{useraliasid}/groupid/{groupid}")
    @Produces("application/json")
    public TingcoUsers getJson_updateUserKeyOrganization(@PathParam("useraliasid") String useraliasid, @PathParam("groupid") String groupId) {
        return updateUserKeyOrganization(useraliasid, groupId);
    }

    /**
     * DeleteUserKey
     * @param useraliasid
     * @return TingcoUsers
     */
    @GET
    @Path("/deleteuserkey/rest/{rest}/useraliasid/{useraliasid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "DeleteUserKey", desc = "Delete UserKeyDetails", req_Params = {"UserAlias;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_DeleteUserKey(@PathParam("useraliasid") String useraliasid) {
        return deleteUserKey(useraliasid);
    }

    /**
     * DeleteUserKey
     * @param useraliasid
     * @return TingcoUsers
     */
    @GET
    @Path("/deleteuserkey/json/{json}/useraliasid/{useraliasid}")
    @Produces("application/json")
    @RESTDoc(methodName = "DeleteUserKey", desc = "Delete UserKeyDetails", req_Params = {"UserAlias;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getJson_DeleteUserKey(@PathParam("useraliasid") String useraliasid) {
        return deleteUserKey(useraliasid);
    }

    /**
     * GetObjectGroupsForUserAlias
     * @param useraliasid
     * @param groupId
     * @param countryId
     * @return TingcoUsers
     */
    @GET
    @Path("/getobjectgroupsforuseralias/rest/{rest}/useraliasid/{useraliasid}/groupid/{groupid}/countryid/{countryid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetObjectGroupsForUserAlias", desc = "Get objectgroups for useralias", req_Params = {"UserAlias;UUID", "GroupId;UUID", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_ObjectGroupsForUserAlias(@PathParam("useraliasid") String useraliasid, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getObjectGroupsForUserAlias(useraliasid, groupId, countryId);
    }

    /**
     * GetObjectGroupsForUserAlias
     * @param useraliasid
     * @param groupId
     * @param countryId
     * @return TingcoUsers
     */
    @GET
    @Path("/getobjectgroupsforuseralias/json/{json}/useraliasid/{useraliasid}/groupid/{groupid}/countryid/{countryid}")
    @Produces("application/json")
    public TingcoUsers getjson_ObjectGroupsForUserAlias(@PathParam("useraliasid") String useraliasid, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getObjectGroupsForUserAlias(useraliasid, groupId, countryId);
    }

    /**
     * UpdateUserForUserAlias
     * @param userid
     * @param useraliasid
     * @return TingcoUsers
     */
    @GET
    @Path("/updateuserforuseralias/rest/{rest}/userid/{userid}/useraliasid/{useraliasid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "UpdateUserForUserAlias", desc = "Update useralias", req_Params = {"UserAlias;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_UpdateUserForUserAlias(@PathParam("userid") String userid, @PathParam("useraliasid") String useraliasid) {
        return getObjectGroupsForUserAlias(userid, useraliasid);
    }

    /**
     * UpdateUserForUserAlias
     * @param userid
     * @param useraliasid
     * @return TingcoUsers
     */
    @GET
    @Path("/updateuserforuseralias/json/{json}/userid/{userid}/useraliasid/{useraliasid}")
    @Produces("application/json")
    @RESTDoc(methodName = "UpdateUserForUserAlias", desc = "Update useralias", req_Params = {"UserAlias;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getJson_UpdateUserForUserAlias(@PathParam("userid") String userid, @PathParam("useraliasid") String useraliasid) {
        return getObjectGroupsForUserAlias(userid, useraliasid);
    }

    /**
     * GetUserForUserAlias
     * @param groupid
     * @param countryid
     * @param searchstring
     * @return TingcoUsers
     */
    @GET
    @Path("/getuserforuseralias/rest/{rest}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetUserForUserAlias", desc = "Update useralias", req_Params = {"GroupId;UUID", "CountryId;int", "SearchString;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_UserForUserAlias(@PathParam("groupid") String groupid, @PathParam("countryid") int countryid, @PathParam("searchstring") String searchstring) {
        return getUserForUserAlias(groupid, countryid, searchstring);
    }

    /**
     * GetUserForUserAlias
     * @param groupid
     * @param countryid
     * @param searchstring
     * @return TingcoUsers
     */
    @GET
    @Path("/getuserforuseralias/json/{json}/groupid/{groupid}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    @Produces("application/json")
    @RESTDoc(methodName = "GetUserForUserAlias", desc = "Update useralias", req_Params = {"UserAlias;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getJson_UserForUserAlias(@PathParam("groupid") String groupid, @PathParam("countryid") int countryid, @PathParam("searchstring") String searchstring) {
        return getUserForUserAlias(groupid, countryid, searchstring);
    }

    /**
     * GetUserForUserAlias
     * @param groupid
     * @param countryid
     * @param searchstring
     * @return TingcoUsers
     */
    @GET
    @Path("/getuseraliasforgroup/rest/{rest}/groupid/{groupid}/countryid/{countryid}/searchstring/{searchstring}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetUserForUserAlias", desc = "Update useralias", req_Params = {"GroupId;UUID", "CountryId;int", "SearchString;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_UserAliasForGroup(@PathParam("groupid") String groupid, @PathParam("countryid") int countryid, @PathParam("searchstring") String searchstring) {
        return getUserAliasForGroup(groupid, countryid, searchstring);
    }

    /**
     * GetUserForUserAlias
     * @param groupid
     * @param countryid
     * @param searchstring
     * @return TingcoUsers
     */
    @GET
    @Path("/getuseraliasforgroup/json/{json}/groupid/{groupid}/countryid/{countryid}/searchstring/{searchstring}")
    @Produces("application/json")
    @RESTDoc(methodName = "GetUserForUserAlias", desc = "Update useralias", req_Params = {"UserAlias;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getJson_UserAliasForGroup(@PathParam("groupid") String groupid, @PathParam("countryid") int countryid, @PathParam("searchstring") String searchstring) {
        return getUserAliasForGroup(groupid, countryid, searchstring);
    }

    /**
     * updateuserkeydetails
     * @param content
     * @return TingcoUsers
     */
    @POST
    @Path("/updateuserkeydetails/rest/{rest}")
    @Consumes("application/xml")
    @Produces("application/xml")
    public TingcoUsers postXmlUpdateUserKeyDetails(String content) {
        return UpdateUserKeyDetails(content);
    }

    /**
     * updateuserkeydetails
     * @param content
     * @return TingcoUsers
     */
    @POST
    @Path("/updateuserkeydetails/json/{json}")
    @Consumes("application/xml")
    @Produces("application/json")
    public TingcoUsers postJsonUpdateUserKeyDetails(String content) {
        return UpdateUserKeyDetails(content);
    }

    /**
     * adduseralias
     * @param content
     * @return TingcoUsers
     */
    @POST
    @Path("/adduseralias/rest/{rest}{objecttags :(/objecttags/[^/]+?)?}")
    @Consumes("application/xml")
    @Produces("application/xml")
    public TingcoUsers postXmlAddUserAlias(String content, @PathParam("objecttags") String objectTags) throws DatatypeConfigurationException {
        return addUserAlias(content, objectTags);
    }

    /**
     * adduseralias
     * @param content
     * @return TingcoUsers
     */
    @POST
    @Path("/adduseralias/json/{json}{objecttags :(/objecttags/[^/]+?)?}")
    @Consumes("application/xml")
    @Produces("application/json")
    public TingcoUsers postJsonAddUserAlias(String content, @PathParam("objecttags") String objectTags) throws DatatypeConfigurationException {
        return addUserAlias(content, objectTags);
    }

    /**
     * updateUserAlias
     * @param content
     * @return TingcoUsers
     */
    @POST
    @Path("/updateuseralias/rest/{rest}")
    @Consumes("application/xml")
    @Produces("application/xml")
    public TingcoUsers postXml_updateUserAlias(String content) throws DatatypeConfigurationException {
        return updateUserAlias(content);
    }

    /**
     * updateUserAlias
     * @param content
     * @return TingcoUsers
     */
    @POST
    @Path("/updateuseralias/json/{json}")
    @Consumes("application/xml")
    @Produces("application/json")
    public TingcoUsers postJson_updateUserAlias(String content) throws DatatypeConfigurationException {
        return updateUserAlias(content);
    }

    /**
     * GetUserAliasBySearchCriteria
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getuseraliasbysearchcriteria/rest/{rest}{objecttags :(/objecttags/[^/]+?)?}")
    @RESTDoc(methodName = "GetUserAliasBySearchCriteria", desc = "Used to get UserAlias BySearchCriteria", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoUsers postXml_getUserAliasBySearchCriteria(String content, @PathParam("objecttags") String objectTags) {
        return getUserAliasBySearchCriteria(content, objectTags);
    }

    /**
     * GetUserAliasBySearchCriteria
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getuseraliasbysearchcriteria/json/{json}{objecttags :(/objecttags/[^/]+?)?}")
    public TingcoUsers postJson_getUserAliasBySearchCriteria(String content, @PathParam("objecttags") String objectTags) {
        return getUserAliasBySearchCriteria(content, objectTags);
    }

    /**
     * UpdateObjectGroupsForUserAlias
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/updateobjectgroupsforuseralias/rest/{rest}")
    @RESTDoc(methodName = "UpdateUserAliasBySearchCriteria", desc = "Used to get UserAlias BySearchCriteria", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoUsers postXml_updateObjectGroupsForUserAlias(String content) {
        return updateObjectGroupsForUserAlias(content);
    }

    /**
     * UpdateObjectGroupsForUserAlias
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/updateobjectgroupsforuseralias/json/{json}")
    public TingcoUsers postJson_updateObjectGroupsForUserAlias(String content) {
        return updateObjectGroupsForUserAlias(content);
    }

    /**
     * AddUserAliasAndUserAliasDetails
     * @param content
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @POST
    @Path("/adduseraliasanduseraliasdetails/rest/{rest}")
    @Consumes("application/xml")
    @Produces("application/xml")
    @RESTDoc(methodName = "AddUserAliasAndUserAliasDetails", desc = "Add UserAlias And UserAliasDetails", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoUsers getXml_addUserAliasAndUserAliasDetails(String content) throws DatatypeConfigurationException {
//        TingcoEV tingcoEV = (TingcoEV) JAXBManager.getInstance().unMarshall(content, TingcoEV.class);
        return adduseraliasanduseraliasdetails(content);
    }

    /**
     * AddUserAliasAndUserAliasDetails
     * @param content
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @POST
    @Path("/adduseraliasanduseraliasdetails/json/{json}")
    @Consumes("application/xml")
    @Produces("application/json")
    public TingcoUsers getJson_addUserAliasAndUserAliasDetails(String content) throws DatatypeConfigurationException {
//        TingcoEV tingcoEV = (TingcoEV) JAXBManager.getInstance().unMarshall(content, TingcoEV.class);
        return adduseraliasanduseraliasdetails(content);
    }

    /**
     * GetUserAlias
     * @param groupid
     * @param searchstring
     * @return
     */
    @GET
    @Path("/getuseralias/rest/{rest}{searchstring:(/searchstring/[^/]+?)?}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetUserAlias", desc = "GetUserAlias", req_Params = {"SearchString;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_GetUserAlias(@PathParam("searchstring") String searchstring) {
        return getUserAlias(searchstring);
    }

    /**
     * GetUserAlias
     * @param groupid
     * @param searchstring
     * @return
     */
    @GET
    @Path("/getuseralias/json/{json}{searchstring:(/searchstring/[^/]+?)?}")
    @Produces("application/json")
    @RESTDoc(methodName = "GetUserAlias", desc = "GetUserAlias", req_Params = {"SearchString;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getJson_GetUserAlias(@PathParam("searchstring") String searchstring) {
        return getUserAlias(searchstring);
    }

    /**
     * getuseraliasbyuserid
     * @param userId
     * @return TingcoUsers
     */
    @GET
    @Path("/getuseraliasbyuserid/rest/{rest}/userid/{userid}{limit:(/limit/[^/]+?)?}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetUserAlias", desc = "GetUserAlias", req_Params = {"SearchString;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_getUserAliasByUserId(@PathParam("userid") String userId, @PathParam("limit") String limit) {
        return getUserAliasByUserId(userId, limit);
    }

    /**
     * getuseraliasbyuserid
     * @param userId
     * @return TingcoUsers
     */
    @GET
    @Path("/getuseraliasbyuserid/json/{json}/userid/{userid}{limit:(/limit/[^/]+?)?}")
    @Produces("application/json")
    @RESTDoc(methodName = "GetUserAlias", desc = "GetUserAlias", req_Params = {"SearchString;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getJson_getUserAliasByUserId(@PathParam("userid") String userId, @PathParam("limit") String limit) {
        return getUserAliasByUserId(userId, limit);
    }

    /**
     * DeleteBackListByID
     * @param blackListId
     * @return
     */
    @GET
    @Path("/deletebacklistbyid/rest/{rest}/blacklistid/{blacklistid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "DeleteBackListByID", desc = "Delete BackList By ID", req_Params = {"blacklistid;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_DeleteBackListByID(@PathParam("blacklistid") String blackListId) {
        return deleteBackListByID(blackListId);
    }

    @GET
    @Path("/deletebacklistbyid/json/{json}/blacklistid/{blacklistid}")
    @Produces("application/json")
    public TingcoUsers getJson_DeleteBackListByID(@PathParam("blacklistid") String blackListId) {
        return deleteBackListByID(blackListId);
    }

    /**
     * GetFraudLogDetails
     * @param fraudLogId
     * @param countryId
     * @return
     */
    @GET
    @Path("/getfraudlogdetails/rest/{rest}/fraudlogid/{fraudlogid}/countryid/{countryid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetFraudLogDetails", desc = "Get FraudLogDetails", req_Params = {"fraudLogId;string", "countryId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_getFraudLogDetails(@PathParam("fraudlogid") String fraudLogId, @PathParam("countryid") int countryId) {
        return getFraudLogDetails(fraudLogId, countryId);
    }

    /**
     * GetFraudLogDetails
     * @param fraudLogId
     * @param countryId
     * @return
     */
    @GET
    @Path("/getfraudlogdetails/json/{json}/fraudlogid/{fraudlogid}/countryid/{countryid}")
    @Produces("application/json")
    public TingcoUsers getJson_getFraudLogDetails(@PathParam("fraudlogid") String fraudLogId, @PathParam("countryid") int countryId) {
        return getFraudLogDetails(fraudLogId, countryId);
    }

    /**
     * GetFraudLogDetailsBySearchCriteria
     * @param content
     * @return
     */
    @POST
    @Path("/getfraudlogdetailsbysearchcriteria/rest/{rest}")
    @Consumes("application/xml")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetFraudLogDetailsBySearchCriteria", desc = "Get FraudLog Details By Search Criteria", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoUsers getXml_getFraudLogDetailsBySearchCriteria(String content) throws DatatypeConfigurationException {
        return getFraudLogDetailsBySearchCriteria(content);
    }

    /**
     * GetFraudLogDetailsBySearchCriteria
     * @param content
     * @return
     */
    @POST
    @Path("/getfraudlogdetailsbysearchcriteria/json/{json}")
    @Consumes("application/xml")
    @Produces("application/json")
    public TingcoUsers getJson_getFraudLogDetailsBySearchCriteria(String content) throws DatatypeConfigurationException {
        return getFraudLogDetailsBySearchCriteria(content);
    }

    /**
     * Sub-resource locator method for  useralias
     */
    @Path("useralias")
    public UserAliasResource getUserAliasResource() {
        return new UserAliasResource();
    }

    /**
     * UpdateUserAliasForCardActivation
     * @param userAliasId
     * @return
     */
    @GET
    @Path("/updateuseraliasforcardactivation/rest/{rest}/useraliasid/{useraliasid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "UpdateUserAliasForCardActivation", desc = "UpdateUserAliasForCardActivation", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_UpdateUserAliasForCardActivation(@PathParam("useraliasid") String userAliasId) {
        return updateUserAliasForCardActivation(userAliasId);
    }

    /**
     * UpdateUserAliasForCardActivation
     * @param userAliasId
     * @return
     */
    @GET
    @Path("/updateuseraliasforcardactivation/json/{json}/useraliasid/{useraliasid}")
    @Produces("application/json")
    public TingcoUsers getJson_UpdateUserAliasForCardActivation(@PathParam("useraliasid") String userAliasId) {
        return updateUserAliasForCardActivation(userAliasId);
    }

    /**
     * AssignExistingUserKeyToUser
     * @param userId
     * @param userAliasOrderId
     * @param userAliasId
     * @return TingcoUsers
     */
    @GET
    @Path("/assignexistinguserkeytouser/rest/{rest}/userid/{userid}/useraliasOrderid/{useraliasOrderid}/useraliasid/{useraliasid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "AssignExistingUserKeyToUser", desc = "UpdateUserAliasForCardActivation", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_AssignExistingUserKeyToUser(@PathParam("userid") String userId, @PathParam("useraliasOrderid") String userAliasOrderId, @PathParam("useraliasid") String userAliasId) {
        return assignExistingUserKeyToUser(userId, userAliasOrderId, userAliasId);
    }

    /**
     * AssignExistingUserKeyToUser
     * @param userId
     * @param userAliasOrderId
     * @param userAliasId
     * @return TingcoUsers
     */
    @GET
    @Path("/assignexistinguserkeytouser/json/{json}/userid/{userid}/useraliasOrderid/{useraliasOrderid}/useraliasid/{useraliasid}")
    @Produces("application/json")
    @RESTDoc(methodName = "AssignExistingUserKeyToUser", desc = "UpdateUserAliasForCardActivation", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getJson_AssignExistingUserKeyToUser(@PathParam("userid") String userId, @PathParam("useraliasOrderid") String userAliasOrderId, @PathParam("useraliasid") String userAliasId) {
        return assignExistingUserKeyToUser(userId, userAliasOrderId, userAliasId);
    }

    /**
     *
     * @param userId
     * @param groupid
     * @param searchString
     * @return
     */
    @GET
    @Path("/getuserkeybygroup/rest/{rest}/userid/{userid}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetUserKeyByGroup", desc = "UpdateUserAliasForCardActivation", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_GetUserKeyByGroup(@PathParam("userid") String userId, @PathParam("groupid") String groupid, @PathParam("searchstring") String searchString) {
        return getUserKeyByGroup(userId, groupid, searchString);
    }

    /**
     *
     * @param userId
     * @param groupid
     * @param searchString
     * @return
     */
    @GET
    @Path("/getuserkeybygroup/json/{json}/userid/{userid}/groupid/{groupid}{searchstring:(/searchstring/[^/]+?)?}")
    @Produces("application/json")
    @RESTDoc(methodName = "GetUserKeyByGroup", desc = "UpdateUserAliasForCardActivation", req_Params = {""}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getJSON_GetUserKeyByGroup(@PathParam("userid") String userId, @PathParam("groupid") String groupid, @PathParam("searchstring") String searchString) {
        return getUserKeyByGroup(userId, groupid, searchString);
    }

    /**
     * GetUserAliasDetailsByUserAliasId
     * @param userAliasId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getuseraliasdetailsbyuseraliasid/rest/{rest}/useraliasid/{useraliasid}")
    @RESTDoc(methodName = "GetUserAliasDetailsByUserAliasId", desc = "Get UserAliasDetails By UserAliasId", req_Params = {"userAliasId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_getUserAliasDetailsByUserAliasId(@PathParam("useraliasid") String userAliasId) {
        return getUserAliasDetailsByUserAliasId(userAliasId);
    }

    /**
     * GetUserAliasDetailsByUserAliasId
     * @param userAliasId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getuseraliasdetailsbyuseraliasid/json/{json}/useraliasid/{useraliasid}")
    public TingcoUsers getJson_getUserAliasDetailsByUserAliasId(@PathParam("useraliasid") String userAliasId) {
        return getUserAliasDetailsByUserAliasId(userAliasId);
    }

    /**
     * GetUserAliasDetailsById
     * @param userAliasId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getuseraliasdetailsbyid/rest/{rest}/useraliasid/{useraliasid}/loggedinusercountryid/{loggedinusercountryid}")
    @RESTDoc(methodName = "GetUserAliasDetailsById", desc = "Get UserAliasDetails By Id", req_Params = {"userAliasId;string", "loggedInCountryId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_getUserAliasDetailsById(@PathParam("useraliasid") String userAliasId, @PathParam("loggedinusercountryid") int loggedInCountryId) {
        return getUserAliasDetailsById(userAliasId, loggedInCountryId);
    }

    /**
     * GetUserAliasDetailsById
     * @param userAliasId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getuseraliasdetailsbyid/json/{json}/useraliasid/{useraliasid}/loggedinusercountryid/{loggedinusercountryid}")
    public TingcoUsers getJson_getUserAliasDetailsById(@PathParam("useraliasid") String userAliasId, @PathParam("loggedinusercountryid") int loggedInCountryId) {
        return getUserAliasDetailsById(userAliasId, loggedInCountryId);
    }

    private TingcoUsers getUserAliasDetailsById(String userAliasId, int loggedInCountryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        Session ismOperationsSession = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        try {
            tingcoUsers = tingcoUserXml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.USERALIAS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ismOperationsSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    Object object = userDAO.getUserAliasDetailsByUserAliasID(session, userAliasId);
                    if (object != null) {
                        UserAlias userAlias = (UserAlias) object;
                        Users2 users2 = (Users2) userDAO.getUsers2ByUserId(session, userAlias.getUserId());
                        GroupTranslations groupTranslations = groupDAO.getGroupTranslationsByIds(userAlias.getGroupId(), loggedInCountryId, session);
                        ObjectTags objectTags = (ObjectTags) userDAO.getObjectTagsById(session, userAliasId);
                        List<se.info24.ismOperationsPojo.Blacklist> blackList = userDAO.getBlacklistByStartValue(userAlias.getUserAlias(), ismOperationsSession);
                        UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        List<OgmuserAlias> oUAList = userDAO.getOgmuserAliasByID(session, userAliasId);
                        tingcoUsers = tingcoUserXml.buildUserAliasDetailsByUserAliasId(tingcoUsers, userAlias, groupTranslations, users2, objectTags, blackList, timeZoneGMToffset, oUAList, loggedInCountryId, session);
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
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            if (ismOperationsSession != null) {
                ismOperationsSession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUsers;
    }

    private TingcoUsers getUserKeyByGroup(String userId, String groupid, String searchString) {
        Session session = null;
        Transaction tx = null;
        boolean hasPermission = true;
        tingcoUsers = manager.buildUserTemplate();
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.USERALIAS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    if (!searchString.equals("")) {
                        searchString = searchString.split("/")[2];
                    } else {
                        searchString = null;
                    }
                    List<Object> objects = userDAO.getUserKeyByGroup(session, userId, groupid, searchString);
                    if (objects.isEmpty()) {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("Data Not found.");
                        return tingcoUsers;
                    }
                    return tingcoUserXML.buildGetUserKeyByGroup(tingcoUsers, objects);
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-10);
                tingcoUsers.getMsgStatus().setResponseText("User Session is not Valid");
                return tingcoUsers;
            }
        } catch (Exception e) {
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error occurred");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoUsers assignExistingUserKeyToUser(String userId, String userAliasOrderId, String userAliasId) {
        Session session = null;
        Session ismOperationsSession = null;
        Transaction tx = null;
        boolean hasPermission = true;
        tingcoUsers = manager.buildUserTemplate();
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.USERALIAS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    UserAlias userAlias = (UserAlias) session.get(UserAlias.class, userAliasId);
                    UserAliasOrders userAliasOrders = (UserAliasOrders) session.get(UserAliasOrders.class, userAliasOrderId);
                    if (userAlias != null && userAliasOrders != null) {
                        GregorianCalendar gc = new GregorianCalendar();
                        userAlias.setUserId(userId);
                        userAlias.setUpdatedDate(gc.getTime());
                        userAlias.setLastUpdatedByUserId(sessions2.getUserId());

                        userAliasOrders.setIsDelivered(1);
                        userAliasOrders.setLastUpdatedByUserId(sessions2.getUserId());
                        userAliasOrders.setUpdatedDate(gc.getTime());

                        session.saveOrUpdate(userAlias);
                        session.saveOrUpdate(userAliasOrders);
                        tx.commit();
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
                tingcoUsers.getMsgStatus().setResponseCode(-10);
                tingcoUsers.getMsgStatus().setResponseText("User Session is not Valid");
                return tingcoUsers;
            }
        } catch (Exception e) {
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error occurred");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUsers;
    }

    private TingcoUsers getFraudLogDetails(String fraudLogId, int countryId) {
        Session session = null;
        Session ismOperationsSession = null;
        Transaction tx = null;
        boolean hasPermission = true;
        tingcoUsers = manager.buildUserTemplate();
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.USERALIAS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ismOperationsSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    tx = ismOperationsSession.beginTransaction();
                    Object object = userDAO.getFraudLogById(fraudLogId, ismOperationsSession);
                    if (object != null) {
                        FraudLog fraudLog = (FraudLog) object;
                        fraudLog.setIsChecked(1);
                        fraudLog.setLastUpdatedByUserId(sessions2.getUserId());
                        ismOperationsSession.saveOrUpdate(fraudLog);
                        tx.commit();
                        UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        tingcoUsers = tingcoUserXML.buildFraudLogDetails(tingcoUsers, fraudLog, timeZoneGMToffset, countryId, session);
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
                tingcoUsers.getMsgStatus().setResponseCode(-10);
                tingcoUsers.getMsgStatus().setResponseText("User Session is not Valid");
                return tingcoUsers;
            }
        } catch (Exception e) {
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error occurred");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUsers;
    }

    private TingcoUsers getFraudLogDetailsBySearchCriteria(String content) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoUsers = tingcoUserXML.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.USERALIAS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    TingcoUsers users = (TingcoUsers) JAXBManager.getInstance().unMarshall(content, TingcoUsers.class);
                    se.info24.usersjaxb.FraudLog fraudLogjaxb = users.getUsers().getUser().get(0).getUserAliases().get(0).getFraudLogs().get(0).getFraudLog();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                    List<se.info24.usersjaxb.FraudLog> fraudLogList = userDAO.getFraudLogDetailsBySearchCriteria(tingcoUsers, fraudLogjaxb, session, timeZoneGMToffset);
                    if (!fraudLogList.isEmpty()) {
                        if (fraudLogList.size() > 200) {
                            tingcoUsers.setExceeds200(1);
                            fraudLogList = fraudLogList.subList(0, 200);
                        } else {
                            tingcoUsers.setExceeds200(0);
                        }
                        tingcoUsers = tingcoUserXML.buildFraudLogDetailsBySearchCriteria(tingcoUsers, fraudLogList, timeZoneGMToffset);
                    } else {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("No fraudLog found");
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
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error occured");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUsers;
    }

    private TingcoUsers updateUserAlias(String content) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        Transaction tx = null;
        try {
            tingcoUsers = tingcoUserXML.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.USERALIAS, TCMUtil.UPDATE);
                if (hasPermission) {
                    TingcoUsers users = (TingcoUsers) JAXBManager.getInstance().unMarshall(content, TingcoUsers.class);
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    UserAliases ua = users.getUsers().getUser().get(0).getUserAliases().get(0);
                    Object object = userDAO.getUserAliasDetailsByUserAliasID(session, ua.getUserAliasID());
                    if (object != null) {
                        Set<UserAliasDetails> userAliasDetailsSet = new HashSet<UserAliasDetails>();
                        Set<OgmuserAlias> ogmUserAliasSet = new HashSet<OgmuserAlias>();
                        UserAlias userAlias = (UserAlias) object;
                        userAlias.setUserAlias(ua.getUserAlias());
                        userAlias.setUserAliasTypes(new UserAliasTypes(ua.getUserAliasTypeID()));
                        if (ua.getIsEnabled() != null) {
                            userAlias.setIsEnabled(Integer.valueOf(ua.getIsEnabled()));
                        } else {
                            userAlias.setIsEnabled(0);
                        }
                        if (ua.getFirstName() != null) {
                            userAlias.setFirstName(ua.getFirstName());
                        } else {
                            userAlias.setFirstName(null);
                        }
                        if (ua.getLastName() != null) {
                            userAlias.setLastName(ua.getLastName());
                        } else {
                            userAlias.setLastName(null);
                        }
                        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        if (ua.getActiveFromDate() != null) {
                            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, lFormat.parse(ua.getActiveFromDate())));
                            userAlias.setActiveFromDate(gc.getTime());
                        }

                        if (ua.getActiveToDate() != null) {
                            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, lFormat.parse(ua.getActiveToDate())));
                            userAlias.setActiveToDate(gc.getTime());
                        }

                        userAlias.setGroupId(ua.getUserAliasOrders().getUserAliasOrder().get(0).getGroupID());
                        if (ua.getUserAliasOrders().getUserAliasOrder().get(0).getUserID() != null) {
                            userAlias.setUserId(ua.getUserAliasOrders().getUserAliasOrder().get(0).getUserID());
                        } else {
                            userAlias.setUserId(null);
                        }
                        if (ua.getCredits() != null) {
                            userAlias.setCredits(ua.getCredits().intValue());
                        } else {
                            userAlias.setCredits(null);
                        }
                        if (ua.getCreditAmount() != null) {
                            userAlias.setCreditAmount(ua.getCreditAmount().doubleValue());
                        } else {
                            userAlias.setCreditAmount(null);
                        }
                        userAlias.setCreditAmountCurrencyId(ua.getCreditAmountCurrencyID().intValue());
                        userAlias.setLastUpdatedByUserId(sessions2.getUserId());
                        userAlias.setUpdatedDate(gc.getTime());
                        UserAliasDetails userAliasDetails = userDAO.getUserAliasDetailsByID(session, ua.getUserAliasID());
                        se.info24.usersjaxb.UserAliasDetails userAliasDetailsJaxb = null;
                        if (userAliasDetails != null) {
                            if (!ua.getUserAliasDetails().isEmpty()) {
                                userAliasDetailsJaxb = ua.getUserAliasDetails().get(0);
                                userAliasDetails.setIsCreditCard(Integer.valueOf(userAliasDetailsJaxb.getIsCreditCard()));
                                if (userAliasDetailsJaxb.getIsServiceKey() != null) {
                                    userAliasDetails.setIsServiceKey(Integer.valueOf(userAliasDetailsJaxb.getIsServiceKey()));
                                }

                                if (userAliasDetailsJaxb.getCreditLimitPerPurchase() != null) {
                                    userAliasDetails.setCreditLimitPerPurchase(new BigDecimal(userAliasDetailsJaxb.getCreditLimitPerPurchase()));
                                } else {
                                    userAliasDetails.setCreditLimitPerPurchase(null);
                                }
                                if (userAliasDetailsJaxb.getBlockedReason() != null) {
                                    userAliasDetails.setBlockedReason(userAliasDetailsJaxb.getBlockedReason());
                                } else {
                                    userAliasDetails.setBlockedReason(null);
                                }
                                userAliasDetailsSet.add(userAliasDetails);
                                userAlias.setUserAliasDetailses(userAliasDetailsSet);
                            }
                        } else {
                            userAliasDetailsJaxb = ua.getUserAliasDetails().get(0);
                            userAliasDetails = new UserAliasDetails();
                            userAliasDetails.setUserAliasId(ua.getUserAliasID());
                            userAliasDetails.setIsCreditCard(Integer.valueOf(userAliasDetailsJaxb.getIsCreditCard()));
                            if (userAliasDetailsJaxb.getIsServiceKey() != null) {
                                userAliasDetails.setIsServiceKey(Integer.valueOf(userAliasDetailsJaxb.getIsServiceKey()));
                            }
                            if (userAliasDetailsJaxb.getCreditLimitPerPurchase() != null) {
                                userAliasDetails.setCreditLimitPerPurchase(new BigDecimal(userAliasDetailsJaxb.getCreditLimitPerPurchase()));
                            } else {
                                userAliasDetails.setCreditLimitPerPurchase(null);
                            }
                            if (userAliasDetailsJaxb.getBlockedReason() != null) {
                                userAliasDetails.setBlockedReason(userAliasDetailsJaxb.getBlockedReason());
                            }
                            userAliasDetails.setLastUpdatedByUserId(sessions2.getUserId());
                            userAliasDetails.setCreatedDate(gc.getTime());
                            userAliasDetails.setUpdatedDate(gc.getTime());
                            userAliasDetailsSet.add(userAliasDetails);
                            userAlias.setUserAliasDetailses(userAliasDetailsSet);
                        }

                        if (ua.getUserAliasOrders() != null) {
                            UserAliasOrder uao = ua.getUserAliasOrders().getUserAliasOrder().get(0);
                            if (uao.getUserAliasOrderID() != null) {
                                object = userDAO.getUserAliasOrderById(uao.getUserAliasOrderID(), session);
                                UserAliasOrders userAliasOrders = (UserAliasOrders) object;
                                userAliasOrders.setUserId(uao.getUserID());
                                userAliasOrders.setGroupId(uao.getGroupID());
                                userAliasOrders.setIsDelivered(uao.getIsDelivered());
                                session.saveOrUpdate(userAliasOrders);
                            }
                        }

                        if (!ua.getOGMUserAlias().isEmpty()) {
                            List<OgmuserAlias> ogmUserAliasList = userDAO.getOgmuserAliasByID(session, ua.getUserAliasID());
                            if (!ogmUserAliasList.isEmpty()) {
                                for (OgmuserAlias ogmua : ogmUserAliasList) {
                                    session.delete(ogmua);
                                }
                            }
                            for (String objectGroupId : ua.getOGMUserAlias().get(0).getObjectGroupID()) {
                                List<OgmuserAlias> ulist = userDAO.getOgmuserAliasByIDandGroupID(session, ua.getUserAliasID(), objectGroupId);
                                if (ulist.isEmpty()) {
                                    OgmuserAlias ogmUserAlias = new OgmuserAlias(new OgmuserAliasId(ua.getUserAliasID(), objectGroupId), new ObjectGroups(objectGroupId), userAlias, sessions2.getUserId(), gc.getTime(), gc.getTime());
//                                session.saveOrUpdate(ogmUserAlias);
                                    ogmUserAliasSet.add(ogmUserAlias);
                                }
                            }
                            userAlias.setOgmuserAliases(ogmUserAliasSet);
                        }

                        if (ua.getObjectTags() != null) {
                            object = userDAO.getObjectTagsById(session, ua.getUserAliasID());
                            ObjectTags objectTags = null;
                            if (object != null) {
                                objectTags = (ObjectTags) object;
                                objectTags.setSearchTags(ua.getObjectTags().getSearchTags());
                                objectTags.setLastUpdatedByUserId(sessions2.getUserId());
                                objectTags.setUpdatedDate(gc.getTime());
                            } else {
                                objectTags = new ObjectTags();
                                objectTags.setObjectId(ua.getUserAliasID());
                                objectTags.setSearchTags(ua.getObjectTags().getSearchTags());
                                objectTags.setLastUpdatedByUserId(sessions2.getUserId());
                                objectTags.setCreatedDate(gc.getTime());
                                objectTags.setUpdatedDate(gc.getTime());
                            }
                            session.saveOrUpdate(objectTags);
                        }
                        session.saveOrUpdate(userAlias);
                        tx.commit();

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
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }

            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error occured");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoUsers;
    }

    private TingcoUsers updateUserAliasForCardActivation(String userAliasId) {
        Session session = null;
        boolean hasPermission = true;
        tingcoUsers =
                manager.buildUserTemplate();
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.USERALIAS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.USERALIAS);
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
                    Object object = userDAO.getUserAliasDetailsByUserAliasID(session, userAliasId);
                    if (object != null) {
                        UserAlias userAlias = (UserAlias) object;
                        userAlias.setIsEnabled(1);
                        userAlias.setLastUpdatedByUserId(sessions2.getUserId());
                        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                        userAlias.setUpdatedDate(gc.getTime());
                        if (!userDAO.saveOrUpdateUserAlias(userAlias, session)) {
                            tingcoUsers.getMsgStatus().setResponseCode(-1);
                            tingcoUsers.getMsgStatus().setResponseText("Error Occured While Update");
                            return tingcoUsers;
                        }

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
                tingcoUsers.getMsgStatus().setResponseCode(-10);
                tingcoUsers.getMsgStatus().setResponseText("User Session is not Valid");
                return tingcoUsers;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error occurred");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoUsers;
    }

    private TingcoUsers deleteBackListByID(String blackListId) {
        Session ismOprSession = null;
        boolean hasPermission = true;
        tingcoUsers =
                manager.buildUserTemplate();
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.USERALIAS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.USERALIAS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                            hasPermission = true;
                            break;

                        }


                    }
                }
                if (hasPermission) {
                    ismOprSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    Object blackListObject = userDAO.getBlacklistByBlacklistID(blackListId, ismOprSession);
                    if (blackListObject == null) {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoUsers;
                    } else {
                        userDAO.deleteBlackList((Blacklist) blackListObject, ismOprSession);
                    }

                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }

            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-10);
                tingcoUsers.getMsgStatus().setResponseText("User Session is not Valid");
                return tingcoUsers;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error occurred");
            return tingcoUsers;
        } finally {
            if (ismOprSession != null) {
                ismOprSession.close();
            }

            delayLog(requestedTime);
        }

        return tingcoUsers;
    }

    private TingcoUsers getUserAliasByUserId(String userId, String limit) {
        Session session = null;
        boolean hasPermission = true;
        tingcoUsers =
                manager.buildUserTemplate();
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {

//                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
//                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
//                if (ht.containsKey(TCMUtil.USERALIAS)) {
//                    ArrayList<String> operations = ht.get(TCMUtil.READ);
//                    for (int i = 0; i < operations.size(); i++) {
//                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
//                            hasPermission = true;
//                            break;
//                        }
//                    }
//                }
                if (hasPermission) {
                    if (!limit.equals("")) {
                        limit = limit.split("/")[2];
                    } else {
                        limit = null;
                    }

                    session = HibernateUtil.getSessionFactory().openSession();
                    List<UserAlias> userAliases = new ArrayList<UserAlias>();
                    if (limit != null) {
                        userAliases = groupDAO.getUserAliasByLoggedInUserID(session, userId, Integer.parseInt(limit));
                    } else {
                        userAliases = groupDAO.getUserAliasByLoggedInUserID(session, userId, 0);
                    }

                    if (userAliases.isEmpty()) {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("Data not Found");
                        return tingcoUsers;
                    }

                    tingcoUsers = tingcoUserXML.buildGetUserAliasByUserId(userAliases, tingcoUsers);
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }

            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-10);
                tingcoUsers.getMsgStatus().setResponseText("User Session is not Valid");
                return tingcoUsers;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error occurred");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoUsers;
    }

    private TingcoUsers getUserAlias(String searchstring) {
        Session session = null;
        boolean hasPermission = false;
        tingcoUsers = manager.buildUserTemplate();
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (searchstring.equals("")) {
                    searchstring = null;
                } else {
                    searchstring = searchstring.split("/")[2];
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.ORDERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.ORDERS);
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
                    Users2 users2 = userDAO.getUserById(sessions2.getUserId(), session);
                    List<GroupTrusts> groupTrusts = groupDAO.getGroupTrustByGroupID(users2.getGroupId(), session);
                    Set<String> groupTrustedIDSet = groupDAO.getGroupIdsList(users2.getGroupId(), groupTrusts);

                    List<UserAlias> userAliases = new ArrayList<UserAlias>();
                    List<List<String>> splitList = TCMUtil.splitStringList(new ArrayList<String>(groupTrustedIDSet), 2000);
                    for (List<String> list : splitList) {
                        List<UserAlias> temp = new ArrayList<UserAlias>();
                        if (searchstring != null) {
                            temp = userDAO.getUserAliasBySearchStringAndGroupID(new HashSet<String>(list), searchstring, session, 200);
                        } else {

                            temp = userDAO.getUserAliasByTrustedGroupIDs(new HashSet<String>(list), session, 200);
                        }

                        userAliases.addAll(temp);
                        if (userAliases.size() > 200) {
                            userAliases = userAliases.subList(0, 200);
                        }

                    }

                    tingcoUsers = tingcoUserXML.buildGetUserAlias(userAliases, tingcoUsers);
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }

            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-10);
                tingcoUsers.getMsgStatus().setResponseText("User Session is not Valid");
                return tingcoUsers;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error occurred");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoUsers;
    }

    private TingcoUsers adduseraliasanduseraliasdetails(String content) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        Transaction tx = null;
        try {
            tingcoUsers = tingcoUserXML.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.USERALIAS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.USERALIAS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
                            break;

                        }
                    }
                }
                if (hasPermission) {
                    TingcoUsers users = (TingcoUsers) JAXBManager.getInstance().unMarshall(content, TingcoUsers.class);
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    if (!users.getUsers().getUser().isEmpty()) {
                        UserAliases ua = users.getUsers().getUser().get(0).getUserAliases().get(0);
                        String userAlia = ua.getUserAlias().replaceFirst("^0*", "");
                        if (ua != null) {
                            List<UserAlias> uaList = userDAO.getUserAliasByUserAliasTypeIdAndUserAlias(session, userAlia, ua.getUserAliasTypeID());
                            if (!uaList.isEmpty()) {
                                TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Add UserAlias", ua.getUserAlias(), "UserAlias", "Failed");
                                tingcoUsers.getMsgStatus().setResponseCode(-1);
                                tingcoUsers.getMsgStatus().setResponseText("You cannot have two User keys with the same Key Number in the same Key type");
                                return tingcoUsers;
                            }

                            UserAliasOrders uao = (UserAliasOrders) userDAO.getUserAliasOrderById(ua.getUserAliasOrders().getUserAliasOrder().get(0).getUserAliasOrderID(), session);
                            if (uao.getIsDelivered() != 1) {
                                uao.setIsDelivered(1);
                                uao.setLastUpdatedByUserId(sessions2.getUserId());
                                uao.setUpdatedDate(gc.getTime());
                                session.saveOrUpdate(uao);
                            }

                            String newUserAliasId = UUID.randomUUID().toString();
                            UserAlias userAlias = new UserAlias();
                            userAlias.setUserAliasId(newUserAliasId);
                            userAlias.setUserAlias(userAlia);
                            userAlias.setUserAliasTypes(new UserAliasTypes(ua.getUserAliasTypeID()));
                            userAlias.setIsEnabled(0);
                            userAlias.setActiveFromDate(gc.getTime());
                            gc.add(GregorianCalendar.YEAR, 10);
                            userAlias.setActiveToDate(gc.getTime());
                            userAlias.setFirstName(ua.getUserAliasOrders().getUserAliasOrder().get(0).getFirstName());
                            userAlias.setLastName(ua.getUserAliasOrders().getUserAliasOrder().get(0).getLastName());
                            userAlias.setGroupId(ua.getUserAliasOrders().getUserAliasOrder().get(0).getGroupID());
                            userAlias.setUserId(ua.getUserAliasOrders().getUserAliasOrder().get(0).getUserID());
                            if (ua.getCredits() != null) {
                                userAlias.setCredits(ua.getCredits().intValue());
                            }
                            if (uao.getSocialSecurityNumber() != null) {
                                userAlias.setSocialSecurityNumber(uao.getSocialSecurityNumber());
                            }
                            if (ua.getCreditAmount() != null) {
                                userAlias.setCreditAmount(ua.getCreditAmount().doubleValue());
                            }
                            userAlias.setCreditAmountCurrencyId(ua.getCreditAmountCurrencyID().intValue());
                            userAlias.setLastUpdatedByUserId(sessions2.getUserId());
                            gc.add(GregorianCalendar.YEAR, -10);
                            userAlias.setCreatedDate(gc.getTime());
                            userAlias.setUpdatedDate(gc.getTime());
                            session.saveOrUpdate(userAlias);

                            UserAliasDetails userAliasDetails = new UserAliasDetails();
                            userAliasDetails.setUserAliasId(newUserAliasId);
                            userAliasDetails.setIsCreditCard(Integer.valueOf(ua.getUserAliasDetails().get(0).getIsCreditCard()));
                            if (ua.getCreditAmount() != null) {
                                userAliasDetails.setCreditLimitPerPurchase(ua.getCreditAmount());
                            }
                            userAliasDetails.setLastUpdatedByUserId(sessions2.getUserId());
                            userAliasDetails.setCreatedDate(gc.getTime());
                            userAliasDetails.setUpdatedDate(gc.getTime());
                            session.saveOrUpdate(userAliasDetails);

                            if (!ua.getOGMUserAlias().isEmpty()) {
                                for (String s : ua.getOGMUserAlias().get(0).getObjectGroupID()) {
                                    OgmuserAlias ogmUserAlias = new OgmuserAlias(new OgmuserAliasId(newUserAliasId, s), new ObjectGroups(s), userAlias, sessions2.getUserId(), gc.getTime(), gc.getTime());
                                    session.saveOrUpdate(ogmUserAlias);
                                }
                            }
                            tx.commit();
                        }
                    } else {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("Invalid input XML found");
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
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error occured");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoUsers;
    }

    private TingcoUsers getUserAliasForGroup(String groupid, int countryid, String searchstring) {
        Session session = null;
        boolean hasPermission = false;
        tingcoUsers =
                manager.buildUserTemplate();
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (groupid == null) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("GroupID should not be empty");
                    return tingcoUsers;
                } else if (countryid <= 0) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("CountryID should not be empty");
                    return tingcoUsers;
                } else if (searchstring == null) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("SearchString should not be empty");
                    return tingcoUsers;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.USERALIAS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.USERALIAS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;

                        }


                    }
                }
                if (hasPermission) {
                    GroupDAO groupdao = new GroupDAO();
                    session =
                            HibernateUtil.getSessionFactory().openSession();
                    Set<String> groupIdSet = groupDAO.getGroupsAndGroupTrusts(groupid, session);

                    List<UserAlias> userAliasList = new ArrayList<UserAlias>();
                    List<GroupTranslations> groupTranslation = new ArrayList<GroupTranslations>();

                    List<List<String>> splitList = TCMUtil.splitStringList(new ArrayList<String>(groupIdSet), 2000);
                    for (List<String> list : splitList) {
                        List<UserAlias> userAliasListTemp = userDAO.getUserAliasBySearchString(session, searchstring, new HashSet<String>(list));
                        if (userAliasListTemp != null) {
                            userAliasList.addAll(userAliasListTemp);
                        }

                        List<GroupTranslations> groupTranslationTemp = groupdao.getGroupTranslationsById(list, countryid, session);
                        if (groupTranslationTemp != null) {
                            groupTranslation.addAll(groupTranslationTemp);
                        }

                    }
                    if (userAliasList.isEmpty()) {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoUsers;
                    }

                    return tingcoUserXML.buildGetUserAliasForGroup(tingcoUsers, userAliasList, groupTranslation);
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }

            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-10);
                tingcoUsers.getMsgStatus().setResponseText("User Session is not Valid");
                return tingcoUsers;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error occurred");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

    }

    private TingcoUsers getUserAliasBySearchCriteria(String content, String objectTags) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoUsers = tingcoUserXML.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                TingcoUsers users = (TingcoUsers) JAXBManager.getInstance().unMarshall(content, TingcoUsers.class);
                session = HibernateUtil.getSessionFactory().openSession();
                if (!users.getUsers().getUser().isEmpty()) {
                    UserAliases userAliases = users.getUsers().getUser().get(0).getUserAliases().get(0);
                    if (userAliases != null) {
                        hasPermission = getPermission(sessions2.getUserId(), userAliases.getFunctionAreas(), TCMUtil.READ);
                        if (hasPermission) {
                            if (!objectTags.equals("")) {
                                objectTags = objectTags.split("/")[2];
                            } else {
                                objectTags = null;
                            }
                            List<UserAlias> userAliasList = userDAO.getUserAliasBySearchCriteria(userAliases, tingcoUsers, session, objectTags);
                            if (!userAliasList.isEmpty()) {
                                List<String> groupidList = new ArrayList<String>();
                                for (UserAlias userAlias : userAliasList) {
                                    groupidList.add(userAlias.getGroupId());
                                }
                                GroupDAO groupdao = new GroupDAO();
                                List<GroupTranslations> groupTranslationsList = groupdao.getGroupTranslationsById(groupidList, users.getUsers().getUser().get(0).getUserAliases().get(0).getCountryID().getValue(), session);
                                UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(userAliases.getLastUpdatedByUserID(), session);
                                if (userTimeZones2 != null) {
                                    String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                                    tingcoUsers = tingcoUserXML.buildUserAliasBySearchCriteria(tingcoUsers, userAliasList, timeZoneGMToffset, groupTranslationsList, session);
                                } else {
                                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                                    tingcoUsers.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                                    return tingcoUsers;
                                }
                            } else {
                                tingcoUsers.getMsgStatus().setResponseCode(-1);
                                tingcoUsers.getMsgStatus().setResponseText("No UserAlias found for the given input");
                                return tingcoUsers;
                            }
                        } else {
                            tingcoUsers.getMsgStatus().setResponseCode(-10);
                            tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                            return tingcoUsers;
                        }
                    }

                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("Invalid input XML found");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error occured");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoUsers;
    }

    public TingcoUsers getUserKeyDetails(
            String useraliasid) {
        Session session = null;
        boolean hasPermission = false;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoUsers = tingcoUserXML.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (useraliasid == null) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("UserAliasId should not be empty");
                    return tingcoUsers;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.USERALIAS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.USERALIAS);
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
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        Object object = userDAO.getUserAliasDetailsByUserAliasID(session, useraliasid);
                        if (object != null) {
                            UserAlias useralias = (UserAlias) object;
                            Currency currency = null;
                            UserAliasDetails uAD = userDAO.getUserAliasDetailsByID(session, useraliasid);

                            if (useralias.getCreditAmountCurrencyId() != null) {
                                currency = userDAO.getCurrencyById(session, useralias.getCreditAmountCurrencyId());
                            }

                            return tingcoUserXML.buildUserkeyDetail(tingcoUsers, useralias, uAD, currency, timeZoneGMToffset);
                        } else {
                            tingcoUsers.getMsgStatus().setResponseCode(-1);
                            tingcoUsers.getMsgStatus().setResponseText("UserAliasId is not Valid");
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
                tingcoUsers.getMsgStatus().setResponseCode(-10);
                tingcoUsers.getMsgStatus().setResponseText("User Session is not Valid");
                return tingcoUsers;
            }

        } catch (Exception e) {
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error occurred");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

    }

    private TingcoUsers deleteUserKey(String useraliasid) {
        Session session = null;
        boolean hasPermission = false;
        tingcoUsers =
                manager.buildUserTemplate();
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (useraliasid == null) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("UserAliasId should not be empty");
                    return tingcoUsers;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.USERALIAS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.USERALIAS);
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
                    Object object = userDAO.getUserAliasDetailsByUserAliasID(session, useraliasid);
                    if (object != null) {
                        UserAlias useralias = (UserAlias) object;
                        UserAliasDetails uAD = userDAO.getUserAliasDetailsByID(session, useraliasid);
                        List<OgmuserAlias> oUA = userDAO.getOgmuserAliasByID(session, useraliasid);
                        if (userDAO.deleteUserKey(session, useralias, uAD, oUA)) {
                            TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Delete UserAlias", useralias.getUserAlias(), "UserAlias", "Success");
                            tingcoUsers.getMsgStatus().setResponseCode(0);
                            tingcoUsers.getMsgStatus().setResponseText("Deleted");
                            return tingcoUsers;
                        } else {
                            TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Delete UserAlias", useralias.getUserAlias(), "UserAlias", "Failed");
                            tingcoUsers.getMsgStatus().setResponseCode(-1);
                            tingcoUsers.getMsgStatus().setResponseText("Error occurred");
                            return tingcoUsers;
                        }

                    } else {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("UserAliasId is not Found");
                        return tingcoUsers;
                    }

                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }

            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-10);
                tingcoUsers.getMsgStatus().setResponseText("User Session is not Valid");
                return tingcoUsers;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error occurred");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

    }

    private TingcoUsers getObjectGroupsForUserAlias(String useraliasid, String groupId, int countryId) {
        Session session = null;
        boolean hasPermission = false;
        tingcoUsers =
                manager.buildUserTemplate();
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (useraliasid == null) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("UserAliasId should not be empty");
                    return tingcoUsers;
                } else if (groupId == null) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("GroupID should not be empty");
                    return tingcoUsers;
                } else if (countryId <= 0) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("CountryID should not be empty");
                    return tingcoUsers;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.USERALIAS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.USERALIAS);
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
                    Set<String> groupIdSet = new HashSet<String>();
                    groupIdSet =
                            groupDAO.getGroupsAndGroupTrusts(groupId, session);
                    List<ObjectGroups> oGList = new ArrayList<ObjectGroups>();
                    List<List<String>> splitList = TCMUtil.splitStringList(new ArrayList<String>(groupIdSet), 2000);
                    for (List<String> list : splitList) {
                        List<ObjectGroups> oGListTemp = userDAO.getObjectGroupsByGroupId(new HashSet<String>(list), session);
                        oGList.addAll(oGListTemp);
                    }
//                    List<ObjectGroups> oGList = userDAO.getObjectGroupsByGroupId(groupIdSet, session);

                    if (!oGList.isEmpty()) {
                        Set<String> ObjectGroupIDSet = new HashSet<String>();
                        for (ObjectGroups objectGroups : oGList) {
                            ObjectGroupIDSet.add(objectGroups.getObjectGroupId());
                        }

                        List<OgmuserAlias> oUAList = userDAO.getOgmuserAliasByID(session, useraliasid);
                        ObjectGroupIDSet.addAll(userDAO.getObjectGroupIdsSet(session, oUAList));
                        List<ObjectGroupTranslations> oGTList = userDAO.getObjectGroupTranslationsById(session, ObjectGroupIDSet, countryId);
                        return tingcoUserXML.buildGetObjectGroupsForUserAlias(tingcoUsers, oUAList, oGTList);
                    } else {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("Groupid not Found");
                        return tingcoUsers;
                    }

                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }

            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-10);
                tingcoUsers.getMsgStatus().setResponseText("User Session is not Valid");
                return tingcoUsers;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error occurred");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

    }

    public TingcoUsers getObjectGroupsForUserAlias(
            String userid, String useraliasid) {
        Session session = null;
        boolean hasPermission = false;
        tingcoUsers =
                manager.buildUserTemplate();
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (useraliasid == null) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("UserAliasId should not be empty");
                    return tingcoUsers;
                } else if (userid == null) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("UserID should not be empty");
                    return tingcoUsers;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.USERALIAS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.USERALIAS);
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
                    Users2 user = userDAO.getUserById(userid, session);
                    Object object = userDAO.getUserAliasDetailsByUserAliasID(session, useraliasid);
                    if (object != null) {
                        UserAlias userAlias = (UserAlias) object;
                        userAlias.setUserId(userid);
                        userAlias.setFirstName(user.getFirstName());
                        userAlias.setLastName(user.getLastName());
                        userAlias.setUserEmail(user.getUserEmail());
                        userAlias.setUserMobilePhone(user.getUserMobilePhone());
                        if (userDAO.updatedUserAlias(session, userAlias)) {
                            return tingcoUsers;
                        } else {
                            tingcoUsers.getMsgStatus().setResponseCode(-1);
                            tingcoUsers.getMsgStatus().setResponseText("Error occurred");
                            return tingcoUsers;
                        }

                    } else {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("UserAliasId is not Found");
                        return tingcoUsers;
                    }

                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }

            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-10);
                tingcoUsers.getMsgStatus().setResponseText("User Session is not Valid");
                return tingcoUsers;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error occurred");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

    }

    private TingcoUsers getUserForUserAlias(String groupid, int countryid, String searchstring) {
        Session session = null;
        boolean hasPermission = false;
        tingcoUsers =
                manager.buildUserTemplate();
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                if (groupid == null) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("GroupID should not be empty");
                    return tingcoUsers;
                } else if (countryid <= 0) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("CountryID should not be empty");
                    return tingcoUsers;
                }

                if (!searchstring.equals("")) {
                    searchstring = searchstring.split("/")[2];
                } else {
                    searchstring = null;
                }

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.USERALIAS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.USERALIAS);
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
                    Set<String> groupIdSet = groupDAO.getGroupsAndGroupTrusts(groupid, session);
                    List<Users2> users2List = new ArrayList<Users2>();
                    List<GroupTranslations> groupTranslation = new ArrayList<GroupTranslations>();
                    List<List<String>> splitList = TCMUtil.splitStringList(new ArrayList(groupIdSet), 2000);
                    for (List<String> list : splitList) {
                        List<Users2> users2ListTemp = new ArrayList<Users2>();
                        if (searchstring != null) {
                            users2ListTemp = userDAO.getUsers2BySearchstrings(session, new HashSet<String>(list), searchstring, 200);
                        } else {
                            users2ListTemp = userDAO.getUsers2ByGroupIdListorderByFirstNameLastName(session, list, 200);
                        }

                        List<GroupTranslations> groupTranslationTemp = groupDAO.getGroupTranslationsById(list, countryid, session);
                        groupTranslation.addAll(groupTranslationTemp);
                        users2List.addAll(users2ListTemp);
                    }

//                    List<String> groupIdList = new ArrayList<String>(groupIdSet);
                    return tingcoUserXML.buildGetUserForUserAlias(tingcoUsers, users2List, groupTranslation);
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }

            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-10);
                tingcoUsers.getMsgStatus().setResponseText("User Session is not Valid");
                return tingcoUsers;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error occurred");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

    }

    private TingcoUsers updateObjectGroupsForUserAlias(String content) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoUsers = tingcoUserXML.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.USERALIAS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.USERALIAS);
                    for (int i = 0; i <
                            operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;

                        }






                    }
                }
                if (hasPermission) {
                    TingcoUsers users = (TingcoUsers) JAXBManager.getInstance().unMarshall(content, TingcoUsers.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    Transaction tx = session.beginTransaction();
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    if (!users.getUsers().getUser().isEmpty()) {
                        OGMUserAlias ogmUsrAlias = users.getUsers().getUser().get(0).getUserAliases().get(0).getOGMUserAlias().get(0);
                        if (ogmUsrAlias != null) {
                            session.createQuery("DELETE FROM OgmuserAlias where id.userAliasId = :userAliasId").setString("userAliasId", ogmUsrAlias.getUserAliasID()).executeUpdate();
                            session.flush();
                            session.clear();
                            if (ogmUsrAlias.getObjectGroupID() != null) {
                                int i = 1;
                                for (String objectGroupId : ogmUsrAlias.getObjectGroupID()) {
                                    OgmuserAlias ogmua = new OgmuserAlias();
                                    ogmua.setId(new OgmuserAliasId(ogmUsrAlias.getUserAliasID(), objectGroupId));
                                    ogmua.setLastUpdatedByUserId(sessions2.getUserId());
                                    ogmua.setCreatedDate(gc.getTime());
                                    ogmua.setUpdatedDate(gc.getTime());
                                    session.saveOrUpdate(ogmua);
                                    i++;
                                    if (i % 50 == 0) {
                                        session.flush();
                                        session.clear();
                                    }
                                }
                                tx.commit();
                            }
                        }
                    } else {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("Invalid input XML found");
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
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error occured");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoUsers;
    }

    private TingcoUsers UpdateUserKeyDetails(String content) {
        Session session = null;
        boolean hasPermission = false;
        tingcoUsers =
                manager.buildUserTemplate();
        GregorianCalendar gc = new GregorianCalendar();
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.USERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.USERS);
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
                        JAXBManager manage = JAXBManager.getInstance();
                        se.info24.usersjaxb.TingcoUsers td = (se.info24.usersjaxb.TingcoUsers) manage.unMarshall(content, se.info24.usersjaxb.TingcoUsers.class);
                        List<se.info24.usersjaxb.User> userList = td.getUsers().getUser();
                        se.info24.usersjaxb.UserAliases ua = null;
                        se.info24.usersjaxb.UserAliasDetails uad = null;
                        for (se.info24.usersjaxb.User users : userList) {
                            ua = users.getUserAliases().get(0);
                            uad = ua.getUserAliasDetails().get(0);
                        }
                        if (ua != null) {

                            Object object = userDAO.getUserAliasDetailsByUserAliasID(session, ua.getUserAliasID());
                            UserAlias userAlias = null;
                            if (object != null) {
                                userAlias = (UserAlias) object;

                                gc.setTime(df.parse(ua.getActiveFromDate().toString()));
                                gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                                userAlias.setActiveFromDate(gc.getTime());
                                gc.setTime(df.parse(ua.getActiveToDate().toString()));
                                gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                                userAlias.setActiveToDate(gc.getTime());
                                if (ua.getCreditAmountCurrencyID() != null) {
                                    userAlias.setCreditAmountCurrencyId(ua.getCreditAmountCurrencyID().intValue());
                                } else {
                                    userAlias.setCreditAmountCurrencyId(0);
                                }
                                userAlias.setIsEnabled(Integer.valueOf(ua.getIsEnabled()));
                                if (ua.getFirstName() != null) {
                                    userAlias.setFirstName(ua.getFirstName());
                                } else {
                                    userAlias.setFirstName(null);
                                }
                                if (ua.getLastName() != null) {
                                    userAlias.setLastName(ua.getLastName());
                                } else {
                                    userAlias.setLastName(null);
                                }
                                if (ua.getCredits() != null) {
                                    userAlias.setCredits(ua.getCredits().intValue());
                                } else {
                                    userAlias.setCredits(null);
                                }

                                if (ua.getCreditAmount() != null) {
                                    userAlias.setCreditAmount(ua.getCreditAmount().doubleValue());
                                } else {
                                    userAlias.setCreditAmount(null);
                                }
                                userAlias.setUpdatedDate(gc.getTime());
                                userAlias.setLastUpdatedByUserId(sessions2.getUserId());

                            } else {
                                tingcoUsers.getMsgStatus().setResponseCode(-1);
                                tingcoUsers.getMsgStatus().setResponseText("UserAliasId is not Valid");
                                return tingcoUsers;
                            }
                            UserAliasDetails userAliasDetails = null;
                            if (uad != null) {
                                userAliasDetails = userDAO.getUserAliasDetailsByID(session, ua.getUserAliasID());
                                if (userAliasDetails != null) {
                                    userAliasDetails.setIsCreditCard(Integer.valueOf(uad.getIsCreditCard()));
                                    userAliasDetails.setIsServiceKey(Integer.valueOf(uad.getIsServiceKey()));
                                    if (uad.getBlockedReason() != null) {
                                        userAliasDetails.setBlockedReason(uad.getBlockedReason());
                                    } else {
                                        userAliasDetails.setBlockedReason(null);
                                    }
                                    if (uad.getCreditLimitPerPurchase() != null) {
                                        userAliasDetails.setCreditLimitPerPurchase(new BigDecimal(uad.getCreditLimitPerPurchase()));
                                    } else {
                                        userAliasDetails.setCreditLimitPerPurchase(null);
                                    }
                                    userAliasDetails.setLastUpdatedByUserId(sessions2.getUserId());
                                    userAliasDetails.setUpdatedDate(gc.getTime());
                                } else {
                                    TCMUtil.loger(getClass().getName(), "New ", "Info");
                                    userAliasDetails = new UserAliasDetails();
                                    userAliasDetails.setUserAliasId(ua.getUserAliasID());
                                    userAliasDetails.setIsCreditCard(Integer.valueOf(uad.getIsCreditCard()));
                                    userAliasDetails.setIsServiceKey(Integer.valueOf(uad.getIsServiceKey()));
                                    if (uad.getBlockedReason() != null) {
                                        userAliasDetails.setBlockedReason(uad.getBlockedReason());
                                    }
                                    if (uad.getCreditLimitPerPurchase() != null) {
                                        userAliasDetails.setCreditLimitPerPurchase(new BigDecimal(uad.getCreditLimitPerPurchase()));
                                    }
                                    userAliasDetails.setLastUpdatedByUserId(sessions2.getUserId());
                                    userAliasDetails.setCreatedDate(gc.getTime());
                                    userAliasDetails.setUpdatedDate(gc.getTime());
                                }
                            }
                            if (userDAO.UpdateUserKeyDetails(session, userAlias, userAliasDetails)) {
                                TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update UserAlias", userAlias.getUserAlias(), "UserAlias", "Failed");
                                tingcoUsers.getMsgStatus().setResponseCode(0);
                                tingcoUsers.getMsgStatus().setResponseText("Updated");
                                return tingcoUsers;
                            } else {
                                TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Update UserAlias", userAlias.getUserAlias(), "UserAlias", "Success");
                                tingcoUsers.getMsgStatus().setResponseCode(-1);
                                tingcoUsers.getMsgStatus().setResponseText("Error occurred");
                                return tingcoUsers;
                            }
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
                tingcoUsers.getMsgStatus().setResponseCode(-10);
                tingcoUsers.getMsgStatus().setResponseText("User Session is not Valid");
                return tingcoUsers;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error occurred");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

        return tingcoUsers;
    }

    private TingcoUsers addUserAlias(String content, String objectTags) throws DatatypeConfigurationException {
        Session session = null;
        boolean hasPermission = false;
        tingcoUsers =
                manager.buildUserTemplate();
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission =
                        getPermission(sessions2.getUserId(), TCMUtil.USERS, TCMUtil.ADD);
                if (hasPermission) {
                    if (!objectTags.equals("")) {
                        objectTags = objectTags.split("/")[2];
                    } else {
                        objectTags = null;
                    }

                    GregorianCalendar gc = new GregorianCalendar();
                    session =
                            HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        JAXBManager manage = JAXBManager.getInstance();
                        se.info24.usersjaxb.TingcoUsers td = (se.info24.usersjaxb.TingcoUsers) manage.unMarshall(content, se.info24.usersjaxb.TingcoUsers.class);
                        se.info24.usersjaxb.User users = td.getUsers().getUser().get(0);
                        se.info24.usersjaxb.UserAliases ua = users.getUserAliases().get(0);
                        se.info24.usersjaxb.UserAliasDetails uad = ua.getUserAliasDetails().get(0);
                        List<se.info24.usersjaxb.OGMUserAlias> oUA = ua.getOGMUserAlias();
                        List<String> ObjectGroupIDList = new ArrayList<String>();
                        for (se.info24.usersjaxb.OGMUserAlias oGMUserAlias : oUA) {
                            ObjectGroupIDList = oGMUserAlias.getObjectGroupID();
                        }
                        UserAlias useraliases = new UserAlias();
                        String useraliasId = UUID.randomUUID().toString();
                        useraliases.setUserAliasId(useraliasId);
                        Object uat = userDAO.getUserAliasTypesByID(session, ua.getUserAliasTypeID());

                        if (uat != null) {
                            UserAliasTypes userAliasTypes = (UserAliasTypes) uat;
                            useraliases.setUserAliasTypes(userAliasTypes);
                        } else {
                            TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Add UserAlias", ua.getUserAlias(), "UserAlias", "Failed");
                            tingcoUsers.getMsgStatus().setResponseCode(-1);
                            tingcoUsers.getMsgStatus().setResponseText("InValid UserAliasTypeID");
                            return tingcoUsers;
                        }
                        List<UserAlias> uaList = userDAO.getUserAliasByUserAliasTypeIdAndUserAlias(session, ua.getUserAlias(), ua.getUserAliasTypeID());
                        if (!uaList.isEmpty()) {
                            TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Add UserAlias", ua.getUserAlias(), "UserAlias", "Failed");
                            tingcoUsers.getMsgStatus().setResponseCode(-1);
                            tingcoUsers.getMsgStatus().setResponseText("UserAlias and UserAliasTypeID already Available");
                            return tingcoUsers;
                        }

                        useraliases.setUserAlias(ua.getUserAlias().replaceFirst("^0*", ""));

                        if (ua.getActiveFromDate() != null) {
                            gc.setTime(df.parse(ua.getActiveFromDate().toString()));
                            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                            useraliases.setActiveFromDate(gc.getTime());
                        }

                        if (ua.getActiveToDate() != null) {
                            gc.setTime(df.parse(ua.getActiveToDate().toString()));
                            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                            useraliases.setActiveToDate(gc.getTime());
                        }

                        if (ua.getCreditAmountCurrencyID() != null) {
                            useraliases.setCreditAmountCurrencyId(ua.getCreditAmountCurrencyID().intValue());
                        } else {
                            useraliases.setCreditAmountCurrencyId(0);
                        }

                        useraliases.setIsEnabled(Integer.valueOf(ua.getIsEnabled()));
                        useraliases.setFirstName(ua.getFirstName());
                        useraliases.setLastName(ua.getLastName());
                        useraliases.setGroupId(ua.getGroupID());

                        if (ua.getCredits() != null) {
                            useraliases.setCredits(ua.getCredits().intValue());
                        }

                        if (ua.getUserID() != null) {
                            useraliases.setUserId(ua.getUserID());
                        }

                        if (ua.getCreditAmount() != null) {
                            useraliases.setCreditAmount(ua.getCreditAmount().doubleValue());
                        } else {
                            useraliases.setCreditAmount(0.0);
                        }

                        useraliases.setLastUpdatedByUserId(sessions2.getUserId());
                        gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));

                        useraliases.setCreatedDate(gc.getTime());

                        useraliases.setUpdatedDate(gc.getTime());
                        UserAliasDetails userAliasDetail = new UserAliasDetails();
                        userAliasDetail.setUserAliasId(useraliasId);
                        userAliasDetail.setIsCreditCard(Integer.valueOf(uad.getIsCreditCard()));
                        userAliasDetail.setIsServiceKey(Integer.valueOf(uad.getIsServiceKey()));

                        if (uad.getCreditLimitPerPurchase() != null) {
                            userAliasDetail.setCreditLimitPerPurchase(new BigDecimal(uad.getCreditLimitPerPurchase()));
                        }
                        userAliasDetail.setLastUpdatedByUserId(sessions2.getUserId());
                        userAliasDetail.setCreatedDate(gc.getTime());
                        userAliasDetail.setUpdatedDate(gc.getTime());
                        List<OgmuserAlias> ogmuserAliasList = new ArrayList<OgmuserAlias>();
                        OgmuserAlias ogmuserAlias = null;
                        OgmuserAliasId id = null;
                        for (String ObjectGroupID : ObjectGroupIDList) {
                            if (ObjectGroupID != null && !ObjectGroupID.equalsIgnoreCase("")) {
                                ogmuserAlias = new OgmuserAlias();
                                id = new OgmuserAliasId();
                                id.setUserAliasId(useraliasId);
                                id.setObjectGroupId(ObjectGroupID);
                                ogmuserAlias.setId(id);
                                if (ua.getUserID() != null) {
                                    ogmuserAlias.setLastUpdatedByUserId(ua.getUserID());
                                }
                                ogmuserAlias.setUpdatedDate(gc.getTime());
                                ogmuserAlias.setCreatedDate(gc.getTime());
                                ogmuserAliasList.add(ogmuserAlias);
                            }

                        }
                        ObjectTags objectTagsPojo = null;
                        if (objectTags != null) {
                            objectTagsPojo = new ObjectTags();
                            objectTagsPojo.setObjectId(useraliasId);
                            objectTagsPojo.setSearchTags(objectTags);
                            objectTagsPojo.setLastUpdatedByUserId(sessions2.getUserId());
                            objectTagsPojo.setCreatedDate(gc.getTime());
                            objectTagsPojo.setUpdatedDate(gc.getTime());
                        }




                        if (userDAO.AddUserAlias(session, useraliases, userAliasDetail, ogmuserAliasList, objectTagsPojo)) {
                            TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Add UserAlias", ua.getUserAlias(), "UserAlias", "Success");
                            tingcoUsers.getMsgStatus().setResponseCode(0);
                            tingcoUsers.getMsgStatus().setResponseText("Added");
                            return tingcoUsers;
                        } else {
                            TCMUtil.saveLog(session, request.getRemoteHost(), sessions2.getUserId(), "Add UserAlias", ua.getUserAlias(), "UserAlias", "Failed");
                            tingcoUsers.getMsgStatus().setResponseCode(-1);
                            tingcoUsers.getMsgStatus().setResponseText("Error occurred");
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
                tingcoUsers.getMsgStatus().setResponseCode(-10);
                tingcoUsers.getMsgStatus().setResponseText("User Session is not Valid");
                return tingcoUsers;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error occurred");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
            System.gc();
        }

    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }

    private TingcoUsers updateUserKeyOrganization(String useraliasid, String groupId) {
        boolean hasPermission = false;
        Session session = null;
        Transaction tx = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoUsers = tingcoUserXML.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.USERALIAS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.USERALIAS);
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
                    tx =
                            session.beginTransaction();
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
//                    UserAlias userAlias = (UserAlias) session.load(UserAlias.class, useraliasid);
                    Object object = userDAO.getUserAliasDetailsByUserAliasID(session, useraliasid);
                    if (object != null) {
                        UserAlias userAlias = (UserAlias) object;
                        userAlias.setGroupId(groupId);
                        userAlias.setLastUpdatedByUserId(sessions2.getUserId());
                        userAlias.setUpdatedDate(gc.getTime());
                        session.saveOrUpdate(userAlias);
                        tx.commit();
                        return tingcoUsers;
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
                tingcoUsers.getMsgStatus().setResponseCode(-10);
                tingcoUsers.getMsgStatus().setResponseText("User Session is not Valid");
                return tingcoUsers;
            }

        } catch (ObjectNotFoundException obfj) {
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("No data found");
            return tingcoUsers;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }

            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error occurred");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }

            delayLog(requestedTime);
        }

    }

    private TingcoUsers getUserAliasDetailsByUserAliasId(String userAliasId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        se.info24.jaxbUtil.TingcoUserXML tingcoUserXml = new se.info24.jaxbUtil.TingcoUserXML();
        try {
            tingcoUsers = tingcoUserXml.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission =
                        getPermission(sessions2.getUserId(), TCMUtil.USERALIAS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object object = userDAO.getUserAliasDetailsByUserAliasID(session, userAliasId);
                    if (object != null) {
                        UserAlias userAlias = (UserAlias) object;
                        tingcoUsers = tingcoUserXml.buildUserAliasDetailsByUserAliasId(tingcoUsers, userAlias);
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

        } catch (Exception ex) {
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

    private boolean getPermission(String userId, String functionarea, String operation) {
        boolean hasPermission = false;
        Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(userId);
        if (ht.containsKey(functionarea)) {
            ArrayList<String> operations = ht.get(functionarea);
            for (int i = 0; i <
                    operations.size(); i++) {
                if (operations.get(i).equalsIgnoreCase(operation)) {
                    hasPermission = true;
                    break;

                }


            }
        }
        return hasPermission;
    }
}

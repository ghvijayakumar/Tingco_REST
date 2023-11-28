/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.tim;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
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
import org.hibernate.Session;
import se.info24.application.ApplicationDAO;
import se.info24.containers.ContainerDAO;
import se.info24.device.DeviceDAO;
import se.info24.group.GroupDAO;
import se.info24.jaxbUtil.TingcoSupportCaseXML;
import se.info24.pojo.ApplicationPackageModules;
import se.info24.pojo.GroupApplicationPackages;
import se.info24.pojo.SupportCaseDeviceLinks;
import se.info24.pojo.SupportCaseStatuses;
import se.info24.pojo.SupportCases;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.UserTimeZones2;
import se.info24.restUtil.RestUtilDAO;
import se.info24.supportcase.SupportCasesDAO;
import se.info24.supportcasejaxb.TingcoSupportCase;
import se.info24.timjaxb.TingcoMobile;
import se.info24.user.UserDAO;
import se.info24.user.User_LoginsResource;
import se.info24.util.HibernateUtil;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Vijayakumar
 */
@Path("/tim")
public class TIMsResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoSupportCaseXML tingcoSupportXML = new TingcoSupportCaseXML();
    TingcoMobileXML tingcoMobileXML = new TingcoMobileXML();
    TingcoMobileDAO tingcoMobileDAO = new TingcoMobileDAO();
    SupportCasesDAO supportCasesDAO = new SupportCasesDAO();
    DeviceDAO deviceDAO = new DeviceDAO();
    GroupDAO groupDAO = new GroupDAO();
    UserDAO userDAO = new UserDAO();
    ApplicationDAO applicationDAO = new ApplicationDAO();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of TIMsResource */
    public TIMsResource() {
    }

    /*
     * GetMySupportCasesForTIM
     * LoggedInUserId
     */
    @GET
    @Produces("application/json")
    @Path("/getmysupportcasesfortim/json/{json}/loggedinuserid/{loggedinuserid}/countryid/{countryid}{deviceid:(/deviceid/[^/]+?)?}")
    @RESTDoc(methodName = "GetMySupportCasesForTIM", desc = "Used to Get SupportCases For TIM Mobile", req_Params = {"LoggedInUserId;string", "countryid;int"}, opt_Params = {}, method_formats = {"JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoSupportCase getJson_getMySupportCasesForTIM(@PathParam("loggedinuserid") String loggedInUserId, @PathParam("countryid") int countryId, @PathParam("deviceid") String deviceId) {
        return getMySupportCasesForTIM(loggedInUserId, countryId, deviceId);
    }

    /*
     * GetTIMSiteMenuPageDetailsForUserID
     * LoggedInUserId
     * GroupId
     * CountryId
     */
    @GET
    @Produces("application/json")
    @Path("/gettimsitemenupagedetailsforuserid/json/{json}/loggedinuserid/{loggedinuserid}/groupid/{groupid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetTIMSiteMenuPageDetailsForUserID", desc = "Used to Get TIM SiteMenu PageDetails For UserID", req_Params = {"LoggedInUserId;string", "groupId;string", "countryid;int"}, opt_Params = {}, method_formats = {"JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoMobile getJson_getTIMSiteMenuPageDetailsForUserID(@PathParam("loggedinuserid") String loggedInUserId, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getTIMSiteMenuPageDetailsForUserID(loggedInUserId, groupId, countryId);
    }

    /*
     * GetTIMSiteMenuPageDetailsForUserID
     * LoggedInUserId
     * GroupId
     * CountryId
     */
    @GET
    @Produces("application/xml")
    @Path("/gettimsitemenupagedetailsforuserid/rest/{rest}/loggedinuserid/{loggedinuserid}/groupid/{groupid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetTIMSiteMenuPageDetailsForUserID", desc = "Used to Get TIM SiteMenu PageDetails For UserID", req_Params = {"LoggedInUserId;string", "groupId;string", "countryid;int"}, opt_Params = {}, method_formats = {"JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoMobile getXML_getTIMSiteMenuPageDetailsForUserID(@PathParam("loggedinuserid") String loggedInUserId, @PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getTIMSiteMenuPageDetailsForUserID(loggedInUserId, groupId, countryId);
    }

    @GET
    @Produces("application/json")
    @Path("/getdummydata/json/{json}")
    @RESTDoc(methodName = "GetTIMSiteMenuPageDetailsForUserID", desc = "Used to Get TIM SiteMenu PageDetails For UserID", req_Params = {"LoggedInUserId;string", "groupId;string", "countryid;int"}, opt_Params = {}, method_formats = {"JSON"}, method_requests = {"GET"}, versions = {"1"})
    public String getJson_getDummyData() {
        return getDummyData();
    }

    @GET
    @Produces("application/json")
    @Path("/getdummydatawithparentchildname/json/{json}")
    @RESTDoc(methodName = "GetTIMSiteMenuPageDetailsForUserID", desc = "Used to Get TIM SiteMenu PageDetails For UserID", req_Params = {"LoggedInUserId;string", "groupId;string", "countryid;int"}, opt_Params = {}, method_formats = {"JSON"}, method_requests = {"GET"}, versions = {"1"})
    public String getJson_getDummyDataWithParentChildname() {
        return getDummyDataWithParentChildname();
    }

    @GET
    @Produces("application/json")
    @Path("/getdummydatawithparentchildnamedb/json/{json}")
    @RESTDoc(methodName = "GetTIMSiteMenuPageDetailsForUserID", desc = "Used to Get TIM SiteMenu PageDetails For UserID", req_Params = {"LoggedInUserId;string", "groupId;string", "countryid;int"}, opt_Params = {}, method_formats = {"JSON"}, method_requests = {"GET"}, versions = {"1"})
    public String getJson_getDummyDataWithParentChildnamedb() {
        return getDummyDataWithParentChildnamedb();
    }

    /**
     * POST method for creating an instance of TIMsResource
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
    @Produces("application/json")
    @Path("/getdummydatatcm/json/{json}")
    @RESTDoc(methodName = "GetTIMSiteMenuPageDetailsForUserID", desc = "Used to Get TIM SiteMenu PageDetails For UserID", req_Params = {"LoggedInUserId;string", "groupId;string", "countryid;int"}, opt_Params = {}, method_formats = {"JSON"}, method_requests = {"GET"}, versions = {"1"})
    public String getJson_getDummyDataTcm() {
        return getDummyDataTcm();
    }


    @GET
    @Produces("application/json")
    @Path("/getdummydatatcmresponse/json/{json}")
    @RESTDoc(methodName = "GetTIMSiteMenuPageDetailsForUserID", desc = "Used to Get TIM SiteMenu PageDetails For UserID", req_Params = {"LoggedInUserId;string", "groupId;string", "countryid;int"}, opt_Params = {}, method_formats = {"JSON"}, method_requests = {"GET"}, versions = {"1"})
    public String getJson_getDummyDataTcmResponse() {
        return getDummyDataTcmResponse();
    }


    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public TIMResource getTIMResource() {
        return new TIMResource();
    }

     private String getDummyDataTcmResponse() {
        return "{\"CreateRef\":\"Info24\",\"MsgVer\":\"1\",\"RegionalUnits\":\"Metric\",\"TimeZone\":\"UTC\",\"MsgID\":\"3cbe3589-0aa0-46f4-bde7-2ea4540d2599\",\"MsgTimeStamp\":\"2014-02-05T10:38:55.921Z\",\"MsgStatus\":[{\"ResponseCode\":\"0\",\"ResponseText\":\"Login Accepted\"}]}";
    }


    private String getDummyDataTcm() {
        return "{"+
    "\"latestVersion\": null,"+
    "\"allVersions\": ["+

    "],"+
    "\"downloadableVersions\": ["+
       " {"+
            "\"major\": 4,"+
            "\"minor\": 0,"+
            "\"profile\":\"client\","+
             "\"servicePack\": null,"+
            "\"url\":\"http://www.microsoft.com/downloads/details.aspx?displaylang=en&FamilyID=5765d7a8-7722-4888-a970-ac39b33fd8ab\""+
        "},"+
        "{"+
            "\"major\": 4,"+
            "\"minor\": 0,"+
            "\"profile\": \"full\","+
            "\"servicePack\": null,"+
            "\"url\": \"http://www.microsoft.com/downloads/details.aspx?FamilyID=9cfb2d51-5ff4-4491-b0e5-b386f32c0992&displaylang=en\""+
        "},"+
        "{"+
            "\"major\": 3,"+
            "\"minor\": 5,"+
           "\"profile\": \"Client\","+
            "\"servicePack\": 1,"+
            "\"url\": \"http://www.microsoft.com/downloads/details.aspx?FamilyId=8CEA6CD1-15BC-4664-B27D-8CEBA808B28B&displaylang=en\""+
        "}"+
    "]"+
"}";
    }

    private String getDummyData() {
        return "{\"@CreateRef\":\"test\",\"@MsgVer\":\"1.0\",\"@RegionalUnits\":\"Metric\",\"@TimeZone\":\"UTC\",\"MsgID\":\"b53beeba-b494-4a98-a4b8-69708607863c\",\"MsgTimeStamp\":\"2013-12-06T05:36:12.200Z\",\"MsgStatus\":{\"ResponseCode\":\"0\",\"ResponseText\":\"OK\"}," +
                "\"Items\":{\"Item\":[{\"ItemID\":\"6CB85786-9265-4A77-B367-0E9F57659423\",\"ItemName\":\"Vegetables\",\"ItemParentID\":\"Null\"}," +
                "{\"ItemID\":\"BCB1B119-03DD-4229-B33A-B7C15BF56423\",\"ItemName\":\"Veg1\",\"ItemParentID\":\"6CB85786-9265-4A77-B367-0E9F57659423\"}," +
                "{\"ItemID\":\"5D46FECC-9BF1-437C-AC3F-9F70B1830E53\",\"ItemName\":\"Veg2\",\"ItemParentID\":\"6CB85786-9265-4A77-B367-0E9F57659423\"}," +
                "{\"ItemID\":\"382E8D71-6A89-40FF-8F51-7516CDBD88C3\",\"ItemName\":\"Fruits\",\"ItemParentID\": \"Null\"}," +
                "{\"ItemID\":\"1E992DC7-AF45-447C-A61A-1CA3E52B756B\",\"ItemName\":\"Fruit1\",\"ItemParentID\":\"382E8D71-6A89-40FF-8F51-7516CDBD88C3\"}," +
                "{\"ItemID\":\"B335CCBC-A2EA-4E9C-BD15-59E449AB34FA\",\"ItemName\":\"Fruit2\",\"ItemParentID\":\"382E8D71-6A89-40FF-8F51-7516CDBD88C3\"}]}}";
    }




    private String getDummyDataWithParentChildname() {
        return "{\"@CreateRef\":\"test\",\"@MsgVer\":\"1.0\",\"@RegionalUnits\":\"Metric\",\"@TimeZone\":\"UTC\",\"MsgID\":\"b53beeba-b494-4a98-a4b8-69708607863c\",\"MsgTimeStamp\":\"2013-12-06T05:36:12.200Z\",\"MsgStatus\":{\"ResponseCode\":\"0\",\"ResponseText\":\"OK\"}," +
                "\"Items\":{\"Item\":[{\"ItemID\":\"6CB85786-9265-4A77-B367-0E9F57659423\",\"ItemName\":\"Vegetables\",\"ItemParentID\":\"Null\",\"ParentName\":\"Null\"}," +
                "{\"ItemID\":\"BCB1B119-03DD-4229-B33A-B7C15BF56423\",\"ItemName\":\"Veg1\",\"ItemParentID\":\"6CB85786-9265-4A77-B367-0E9F57659423\",\"ParentName\":\"Vegetables\"}," +
                "{\"ItemID\":\"5D46FECC-9BF1-437C-AC3F-9F70B1830E53\",\"ItemName\":\"Veg2\",\"ItemParentID\":\"6CB85786-9265-4A77-B367-0E9F57659423\",\"ParentName\":\"Vegetables\"}," +
                "{\"ItemID\":\"382E8D71-6A89-40FF-8F51-7516CDBD88C3\",\"ItemName\":\"Fruits\",\"ItemParentID\": \"Null\",\"ParentName\":\"Null\"}," +
                "{\"ItemID\":\"1E992DC7-AF45-447C-A61A-1CA3E52B756B\",\"ItemName\":\"Fruit1\",\"ItemParentID\":\"382E8D71-6A89-40FF-8F51-7516CDBD88C3\",\"ParentName\":\"Fruits\"}," +
                "{\"ItemID\":\"B335CCBC-A2EA-4E9C-BD15-59E449AB34FA\",\"ItemName\":\"Fruit2\",\"ItemParentID\":\"382E8D71-6A89-40FF-8F51-7516CDBD88C3\",\"ParentName\":\"Fruits\"}]}}";
    }

    private String getDummyDataWithParentChildnamedb() {
        return "[{\n \"id\": \"1\",\n \"name\": \"Hot Chocolate\",\n \"type\": \"Chocolate Beverage\",\n \"calories\": \"370\",\n \"totalfat\": \"16g\",\n \"protein\": \"14g\"\n }, {\n \"id\": 2,\n \"name\": \"Peppermint Hot Chocolate\",\n \"type\": \"Chocolate Beverage\",\n \"calories\": \"440\",\n \"totalfat\": \"16g\",\n \"protein\": \"13g\"\n}, {\n \"id\": \"3\",\n \"name\": \"Salted Caramel Hot Chocolate\",\n \"type\": \"Chocolate Beverage\",\n \"calories\": \"450\",\n \"totalfat\": \"16g\",\n \"protein\": \"13g\"\n}, {\n \"id\": \"4\",\n \"name\": \"White Hot Chocolate\",\n \"type\": \"Chocolate Beverage\",\n \"calories\": \"420\",\n \"totalfat\": \"16g\",\n \"protein\": \"12g\"\n}, {\n \"id\": \"5\",\n \"name\": \"Caffe Americano\",\n \"type\": \"Espresso Beverage\",\n \"calories\": \"15\",\n \"totalfat\": \"0g\",\n \"protein\": \"1g\"\n}, {\n \"id\": \"6\",\n \"name\": \"Caffe Latte\",\n \"type\": \"Espresso Beverage\",\n \"calories\": \"190\",\n \"totalfat\": \"7g\",\n \"protein\": \"12g\"\n}, {\n \"id\": \"7\",\n \"name\": \"Caffe Mocha\",\n \"type\": \"Espresso Beverage\",\n \"calories\": \"330\",\n \"totalfat\": \"15g\",\n \"protein\": \"13g\"\n}, {\n \"id\": \"8\",\n \"name\": \"Cappuccino\",\n \"type\": \"Espresso Beverage\",\n \"calories\": \"120\",\n \"totalfat\": \"4g\",\n \"protein\": \"8g\"\n}, {\n \"id\": \"9\",\n \"name\": \"Caramel Brulee Latte\",\n \"type\": \"Espresso Beverage\",\n \"calories\": \"420\",\n \"totalfat\": \"9g\",\n \"protein\": \"8g\"\n}, {\n \"id\": \"10\",\n \"name\": \"Caramel Macchiato\",\n \"type\": \"Espresso Beverage\",\n \"calories\": \"240\",\n \"totalfat\": \"11g\",\n \"protein\": \"10g\"\n}, {\n \"id\": \"11\",\n \"name\": \"Peppermint Hot Chocolate\",\n \"type\": \"Espresso Beverage\",\n \"calories\": \"440\",\n \"totalfat\": \"10g\",\n \"protein\": \"13g\"\n}, {\n \"id\": \"12\",\n \"name\": \"Cinnamon Dolce Latte\",\n \"type\": \"Espresso Beverage\",\n \"calories\": \"260\",\n \"totalfat\": \"6g\",\n \"protein\": \"10g\"\n}, {\n \"id\": \"13\",\n \"name\": \"Eggnog Latte\",\n \"type\": \"Espresso Beverage\",\n \"calories\": \"460\",\n \"totalfat\": \"16g\",\n \"protein\": \"13g\"\n}]";
    }

    private TingcoSupportCase getMySupportCasesForTIM(String loggedInUserId, int countryId, String deviceId) {
        Session session = null;
        TingcoSupportCase tingcoSupport = null;
        boolean hasPermission = false;
        long requestedTime = System.currentTimeMillis();
        try {
            if (!deviceId.equals("")) {
                deviceId = deviceId.split("/")[2];
            } else {
                deviceId = null;
            }

            tingcoSupport = tingcoSupportXML.buildTingcoSupportCasesTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.SUPPORT, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<SupportCases> supportCasesList = supportCasesDAO.getSupportCasesByUserId(loggedInUserId, session);
                    if (!supportCasesList.isEmpty()) {
                        UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        List<String> supportCaseStatusIDList = new ArrayList<String>();
                        if (deviceId != null) {
                            ContainerDAO containerDAO = new ContainerDAO();
                            List<String> deviceidList = new ArrayList<String>();
                            deviceidList.add(deviceId);

                            List<SupportCaseDeviceLinks> supportCaseDeviceLinkses = containerDAO.getSupportCaseDeviceLinksByDeviceID(deviceidList, session);
                            deviceidList.clear();
                            for (SupportCases supportCases : supportCasesList) {
                                for (SupportCaseDeviceLinks supportCaseDeviceLinks : supportCaseDeviceLinkses) {
                                    if (supportCases.getSupportCaseId().equalsIgnoreCase(supportCaseDeviceLinks.getId().getSupportCaseId())) {
                                        deviceidList.add(supportCaseDeviceLinks.getId().getSupportCaseId());
                                        supportCaseStatusIDList.add(supportCases.getSupportCaseStatuses().getSupportCaseStatusId());
                                    }
                                }
                            }
                            if (deviceidList.isEmpty()) {
                                tingcoSupport.getMsgStatus().setResponseCode(-1);
                                tingcoSupport.getMsgStatus().setResponseText("Data not found");
                                return tingcoSupport;
                            }
                            supportCasesList = supportCasesDAO.getSupportCasesBySupportCaseIds(session, deviceidList);
                        } else {
                            for (SupportCases supportCases : supportCasesList) {
                                supportCaseStatusIDList.add(supportCases.getSupportCaseStatuses().getSupportCaseStatusId());
                            }
                        }

                        if (supportCaseStatusIDList.isEmpty()) {
                            tingcoSupport.getMsgStatus().setResponseCode(-1);
                            tingcoSupport.getMsgStatus().setResponseText("Data not found");
                            return tingcoSupport;
                        }
                        List<SupportCaseStatuses> supportCaseStatusesesList = tingcoMobileDAO.getSupportCaseStatusesByIdsAndIsClosed(session, supportCaseStatusIDList);
                        tingcoSupport = tingcoSupportXML.buildGetSupportCasesBySearchCriteriaTim(tingcoSupport, supportCasesList, countryId, timeZoneGMToffset, false, session, supportCaseStatusesesList);
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

    private TingcoMobile getTIMSiteMenuPageDetailsForUserID(String loggedInUserId, String groupId, int countryId) {
        Session session = null;
        Session timSession = null;
        TingcoMobile tingcoMobile = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoMobile = tingcoMobileXML.buildTingcoMobileTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                session = HibernateUtil.getSessionFactory().openSession();
                timSession = HibernateUtil.getTimSessionFactory().openSession();
                Set<String> userRoleIdsList = tingcoMobileDAO.getUserRoleIds(loggedInUserId, countryId, session);
                List<GroupApplicationPackages> gAPList = applicationDAO.getGroupAppPackagesForApplicationModule(session, groupId);
                Set<String> applicationPackageId = applicationDAO.getApplicationPackageId(gAPList);
                List<ApplicationPackageModules> aPMList = applicationDAO.getApplicationPackageModulesByModuleIdList(session, applicationPackageId);
                Set<String> applicationModuleId = applicationDAO.getApplicationModuleId(aPMList);
                List<Object> getTIMSiteMenuPageDetails = tingcoMobileDAO.getTIMSiteMenuPageDetailsForUserID(userRoleIdsList, applicationModuleId, countryId, session);
                if (getTIMSiteMenuPageDetails.isEmpty()) {
                    tingcoMobile.getMsgStatus().setResponseCode(-1);
                    tingcoMobile.getMsgStatus().setResponseText("Data Not found");
                    return tingcoMobile;
                }
                tingcoMobile = tingcoMobileXML.buildGetCommandDetailsByDeviceId(tingcoMobile, getTIMSiteMenuPageDetails, timSession, countryId);
            } else {
                tingcoMobile.getMsgStatus().setResponseCode(-3);
                tingcoMobile.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoMobile;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            ex.printStackTrace();
            tingcoMobile.getMsgStatus().setResponseCode(-1);
            tingcoMobile.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            if (timSession != null) {
                timSession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoMobile;
    }
}

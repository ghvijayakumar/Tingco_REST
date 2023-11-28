/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.objectcomments;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
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
import se.info24.device.DeviceDAO;
import se.info24.devicejaxb.TingcoDevice;
import se.info24.ismOperationsPojo.ObjectComments;
import se.info24.jaxbUtil.TingcoDeviceXML;
import se.info24.pojo.Device;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.SupportOrganizationUsers;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.Users2;
import se.info24.restUtil.RestUtilDAO;
import se.info24.user.UserDAO;
import se.info24.user.User_LoginsResource;
import se.info24.util.HibernateUtil;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;
import se.info24.util.TingcoConstants;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/objectcomments")
public class ObjectCommentssResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    private TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
    private ObjectCommentsDAO dao = new ObjectCommentsDAO();
    SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of ObjectCommentssResource */
    public ObjectCommentssResource() {
    }

    /**
     * InsertObjectComments
     * @param objectCommentParentId
     * @param objectId
     * @param subject
     * @param body
     * @param countryId
     * @param ratingAvg
     * @param ratingNo
     * @param del
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/add/rest/{rest}/{objectcommentparentid:(/objectcommentparentid/[^/]+?)?}objectid/{objectid}/subject/{subject}/body/{body}/countryid/{countryid}/{userratingaverage:(/userratingaverage/[^/]+?)?}{userratingnumberof:(/userratingnumberof/[^/]+?)?}isdeleted/{isdeleted}{supportorganizationid:(/supportorganizationid/[^/]+?)?}")
    @RESTDoc(methodName = "InsertObjectComments", desc = "Insert data into ObjectComments", req_Params = {"ObjectID;UUID", "Subject;String", "Body;String", "CountryID;int", "IsDeleted;String"}, opt_Params = {"ObjectParentID;UUID", "UserRatingAverage;String", "UserRatingNumber;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_Add(@PathParam("objectcommentparentid") String objectCommentParentId,
            @PathParam("objectid") String objectId,
            @PathParam("subject") String subject,
            @PathParam("body") String body,
            @PathParam("countryid") int countryId,
            @PathParam("userratingaverage") String ratingAvg,
            @PathParam("userratingnumberof") String ratingNo,
            @PathParam("isdeleted") String del,
            @PathParam("supportorganizationid") String supportOrganizationID) {
        return insertObjectCommentsRest(objectCommentParentId, objectId, subject, body, countryId, ratingAvg, ratingNo, del, supportOrganizationID);
    }

    /**
     * InsertObjectComments
     * @param objectCommentParentId
     * @param objectId
     * @param subject
     * @param body
     * @param countryId
     * @param ratingAvg
     * @param ratingNo
     * @param del
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/add/json/{json}/{objectcommentparentid:(/objectcommentparentid/[^/]+?)?}objectid/{objectid}/subject/{subject}/body/{body}/countryid/{countryid}/{userratingaverage:(/userratingaverage/[^/]+?)?}{userratingnumberof:(/userratingnumberof/[^/]+?)?}isdeleted/{isdeleted}{supportorganizationid:(/supportorganizationid/[^/]+?)?}")
    public TingcoDevice getJson_Add(@PathParam("objectcommentparentid") String objectCommentParentId,
            @PathParam("objectid") String objectId,
            @PathParam("subject") String subject,
            @PathParam("body") String body,
            @PathParam("countryid") int countryId,
            @PathParam("userratingaverage") String ratingAvg,
            @PathParam("userratingnumberof") String ratingNo,
            @PathParam("isdeleted") String del,
            @PathParam("supportorganizationid") String supportOrganizationID) {
        return insertObjectComments(objectCommentParentId, objectId, subject, body, countryId, ratingAvg, ratingNo, del, supportOrganizationID);
    }

    /**
     * UpdateWorkLogs
     * @param objectCommentId
     * @param subject
     * @param body
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/update/rest/{rest}/objectcommentid/{objectcommentid}/subject/{subject}/body/{body}/isdeleted/{isdeleted}")
    @RESTDoc(methodName = "UpdateWorkLogs", desc = "Update ObjectComments Data", req_Params = {"ObjectCommentID;UUID", "Subject;String", "Body;String"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_Update(@PathParam("objectcommentid") String objectCommentId, @PathParam("subject") String subject, @PathParam("body") String body, @PathParam("isdeleted") String del) {
        return updateObjectComment(objectCommentId, subject, body, del);
    }

    /**
     * UpdateWorkLogs
     * @param objectCommentId
     * @param subject
     * @param body
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/update/json/{json}/objectcommentid/{objectcommentid}/subject/{subject}/body/{body}/isdeleted/{isdeleted}")
    public TingcoDevice getJson_Update(@PathParam("objectcommentid") String objectCommentId, @PathParam("subject") String subject, @PathParam("body") String body, @PathParam("isdeleted") String del) {
        return updateObjectComment(objectCommentId, subject, body, del);
    }

    /**
     * DeleteWorkLogs
     * @param objectCommentId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/objectcommentid/{objectcommentid}")
    @RESTDoc(methodName = "DeleteWorkLogs", desc = "Delete ObjectComments Data", req_Params = {"ObjectCommentID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_Delete(@PathParam("objectcommentid") String objectCommentId) {
        return deleteObjectComment(objectCommentId);
    }

    /**
     * DeleteWorkLogs
     * @param objectCommentId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/delete/json/{json}/objectcommentid/{objectcommentid}")
    public TingcoDevice getJson_Delete(@PathParam("objectcommentid") String objectCommentId) {
        return deleteObjectComment(objectCommentId);
    }

    /**
     * GetWorkLogs
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/get/rest/{rest}/deviceid/{deviceid}")
    @RESTDoc(methodName = "GetWorkLogs", desc = "Read ObjectComments Data", req_Params = {"DeviceID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_Get(@PathParam("deviceid") String deviceId) {
        return getObjectComments(deviceId);
    }

    /**
     * GetWorkLogs
     * @param deviceId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/get/json/{json}/deviceid/{deviceid}")
    public TingcoDevice getJson_Get(@PathParam("deviceid") String deviceId) {
        return getObjectComments(deviceId);
    }

    /**
     * POST method for creating an instance of ObjectCommentssResource
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
     * ContactTingcoSupport
     * @param message
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/contacttingcosupport/rest/{rest}/message/{message}")
    @RESTDoc(methodName = "ContactTingcoSupport ", desc = "Contact Tingco Support ", req_Params = {"Message;String"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_ContactTingcoSupport(@PathParam("message") String message) {
        return contactTingcoSupport(message);
    }

    /**
     * ContactTingcoSupport
     * @param message
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/contacttingcosupport/json/{json}/message/{message}")
    public TingcoDevice getJson_ContactTingcoSupport(@PathParam("message") String message) {
        return contactTingcoSupport(message);
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public ObjectCommentsResource getObjectCommentsResource() {
        return new ObjectCommentsResource();
    }

    private TingcoDevice contactTingcoSupport(String message) {
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.USERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.USERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.SEND)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserDAO userDAO = new UserDAO();
                    Users2 users2 = userDAO.getUserById(sessions2.getUserId(), session);
                    List<GroupTranslations> groupTranslationses = userDAO.getGroupTranslationsById(users2.getGroupId(), users2.getCountryId()+"", session);
                    StringBuffer emailBuffer = new StringBuffer();
                    emailBuffer.append("Sent by User:").append(users2.getFirstName() + " " + users2.getLastName());
                    if(!groupTranslationses.isEmpty()){
                        emailBuffer.append("\nOrganization: ").append(groupTranslationses.get(0).getGroupName());
                    }
                    emailBuffer.append("\nE-mail: ").append(users2.getUserEmail());
                    emailBuffer.append("\nMobile: ").append(users2.getUserMobilePhone() == null ? "" : users2.getUserMobilePhone());
                    emailBuffer.append("\n\nMessage to the tingco team: ").append("\n\n\t");
                    String bodyMessage = TCMUtil.convertHexToString(message);
                    if (bodyMessage.length() > 1000) {
                        bodyMessage = bodyMessage.substring(0, 1000);
                        emailBuffer.append(bodyMessage);
                    } else {
                        emailBuffer.append(bodyMessage);
                    }
                    sendTingcoSupportEmail("TCM Support request", emailBuffer.toString(), "support@info24.se");
                } else {
                    tingcoDevice.getMsgStatus().setResponseCode(-10);
                    tingcoDevice.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoDevice;
                }
            } else {
                tingcoDevice.getMsgStatus().setResponseCode(-3);
                tingcoDevice.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoDevice;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error Occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoDevice;
    }

    private TingcoDevice deleteObjectComment(String objectCommentId) {
        TingcoDevice tingcoDevice = null;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                session = HibernateUtil.getISMOperationsSessionFactory().openSession();
                boolean result = dao.deleteObjectComment(objectCommentId, session);
                if (!result) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("Error Occured while Deleting");
                    return tingcoDevice;
                }
            } else {
                tingcoDevice.getMsgStatus().setResponseCode(-3);
                tingcoDevice.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoDevice;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error Occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoDevice;
    }

    private TingcoDevice getObjectComments(String deviceId) {
        TingcoDevice tingcoDevice = null;
        DeviceDAO deviceDao = new DeviceDAO();
        Session session = null;
        Session ismsession = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                session = HibernateUtil.getISMOperationsSessionFactory().openSession();
                ismsession = HibernateUtil.getSessionFactory().openSession();
                List<ObjectComments> objectCommentsList = dao.getObjectComments(deviceId, session);
                Device device = deviceDao.getDeviceById(deviceId, ismsession);
                tingcoDevice = tingcoDeviceXML.buildObjectComments(tingcoDevice, objectCommentsList, device, ismsession);
            } else {
                tingcoDevice.getMsgStatus().setResponseCode(-3);
                tingcoDevice.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoDevice;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error Occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
            if (ismsession != null) {
                ismsession.close();
            }
        }
        return tingcoDevice;

    }

    private TingcoDevice insertObjectCommentsRest(String objectCommentParentId, String objectId, String subject, String body, int countryId, String ratingAvg, String ratingNo, String del, String supportOrganizationID) {
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        Session sessionISM = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (objectCommentParentId.equals("")) {
                    objectCommentParentId = null;
                }

                if (objectId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("ObjectID should not be null");
                    return tingcoDevice;
                }

                if (subject.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("Subject should not be null");
                    return tingcoDevice;
                }

                if (body.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("Body should not be null");
                    return tingcoDevice;
                }

                if (countryId <= 0) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("CountryID should not be null");
                    return tingcoDevice;
                }

                if (ratingAvg.equals("")) {
                    ratingAvg = null;
                }

                if (ratingNo.equals("")) {
                    ratingNo = null;
                }

                if (del.equals("")) {
                    del = null;
                }

                if (supportOrganizationID.equals("")) {
                    supportOrganizationID = null;
                } else {
                    supportOrganizationID = supportOrganizationID.split("/")[2];
                }
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
                    session = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    sessionISM = HibernateUtil.getSessionFactory().openSession();
                    ObjectComments oc = new ObjectComments();
                    oc.setObjectCommentId(UUID.randomUUID().toString());
                    if (objectCommentParentId != null) {
                        oc.setObjectCommentParentId(objectCommentParentId);
                    }

                    oc.setObjectId(objectId);
                    oc.setSubject(subject);
                    oc.setBody(body);
                    oc.setCountryId(countryId);
                    if (ratingAvg != null) {
                        oc.setUserRatingAverage(Integer.parseInt(ratingAvg));
                    }
                    if (ratingNo != null) {
                        oc.setUserRatingNumberOf(Integer.parseInt(ratingNo));
                    }
                    oc.setIsDeleted(Integer.parseInt(del));
                    oc.setCreatedByUserId(sessions2.getUserId());
                    GregorianCalendar gc = new GregorianCalendar();
                    oc.setUpdatedDate(gc.getTime());
                    oc.setCreatedDate(gc.getTime());
                    boolean result = dao.saveObjectComments(oc, session);
                    if (!result) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Error Occured while Inserting");
                        return tingcoDevice;
                    }
                    if (supportOrganizationID != null) {
                        Device device = (Device) sessionISM.get(Device.class, objectId);

                        UserDAO userDAO = new UserDAO();
                        List<String> userId = new ArrayList<String>();
                        List<SupportOrganizationUsers> supportOrganizationUserses = userDAO.getSupportOrganizationUsersById(supportOrganizationID, sessionISM);
                        for (SupportOrganizationUsers supportOrganizationUsers : supportOrganizationUserses) {
                            userId.add(supportOrganizationUsers.getId().getUserId());
                        }
                        if (!userId.isEmpty()) {
                            List<Users2> users2s = userDAO.getUsers2ByUserIdShorted(userId, sessionISM);
                            for (Users2 users2 : users2s) {
                                StringBuffer sb = new StringBuffer();
                                sb.append("This is an automatic e-mail from the tingco Worklog. \n\n");
                                if (device != null) {
                                    sb.append("Device: " + device.getDeviceName() + "\n");
                                    if (device.getAssetId() != null) {
                                        sb.append("Asset ID: " + device.getAssetId() + "\n");
                                    }
                                }
                                sb.append("Created by: " + users2.getFirstName() + " " + users2.getLastName() + "\n\n");
                                sb.append(body);
                                sendEmail(subject, sb.toString(), users2.getUserEmail());
                            }
                        }
                    }
                } else {
                    tingcoDevice.getMsgStatus().setResponseCode(-10);
                    tingcoDevice.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoDevice;
                }
            } else {
                tingcoDevice.getMsgStatus().setResponseCode(-3);
                tingcoDevice.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoDevice;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error Occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoDevice;
    }

    private TingcoDevice insertObjectComments(String objectCommentParentId, String objectId, String subject, String body, int countryId, String ratingAvg, String ratingNo, String del, String supportOrganizationID) {
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        Session sessionISM = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (objectCommentParentId.equals("")) {
                    objectCommentParentId = null;
                }

                if (objectId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("ObjectID should not be null");
                    return tingcoDevice;
                }

                if (subject.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("Subject should not be null");
                    return tingcoDevice;
                }

                if (body.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("Body should not be null");
                    return tingcoDevice;
                }

                if (countryId <= 0) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("CountryID should not be null");
                    return tingcoDevice;
                }

                if (ratingAvg.equals("")) {
                    ratingAvg = null;
                }

                if (ratingNo.equals("")) {
                    ratingNo = null;
                }

                if (del.equals("")) {
                    del = null;
                }

                if (supportOrganizationID.equals("")) {
                    supportOrganizationID = null;
                } else {
                    supportOrganizationID = supportOrganizationID.split("/")[2];
                }
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
                    session = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    sessionISM = HibernateUtil.getSessionFactory().openSession();
                    ObjectComments oc = new ObjectComments();
                    oc.setObjectCommentId(UUID.randomUUID().toString());
                    if (objectCommentParentId != null) {
                        oc.setObjectCommentParentId(objectCommentParentId);
                    }

                    oc.setObjectId(objectId);
                    oc.setSubject(TCMUtil.convertHexToString(subject));
                    oc.setBody(TCMUtil.convertHexToString(body));
                    oc.setCountryId(countryId);
                    if (ratingAvg != null) {
                        oc.setUserRatingAverage(Integer.parseInt(ratingAvg));
                    }
                    if (ratingNo != null) {
                        oc.setUserRatingNumberOf(Integer.parseInt(ratingNo));
                    }
                    oc.setIsDeleted(Integer.parseInt(del));
                    oc.setCreatedByUserId(sessions2.getUserId());
                    GregorianCalendar gc = new GregorianCalendar();
                    oc.setUpdatedDate(gc.getTime());
                    oc.setCreatedDate(gc.getTime());

                    if (supportOrganizationID != null) {
                        Device device = (Device) sessionISM.get(Device.class, objectId);
                        UserDAO userDAO = new UserDAO();
                        Users2 createdUsers2 = userDAO.getUsers2ById(sessions2.getUserId(), sessionISM).get(0);

                        List<String> userId = new ArrayList<String>();
                        List<SupportOrganizationUsers> supportOrganizationUserses = userDAO.getSupportOrganizationUsersById(supportOrganizationID, sessionISM);
                        for (SupportOrganizationUsers supportOrganizationUsers : supportOrganizationUserses) {
                            userId.add(supportOrganizationUsers.getId().getUserId());
                        }
                        if (!userId.isEmpty()) {
                            List<Users2> users2s = userDAO.getUsers2ByUserIdShorted(userId, sessionISM);
                            for (Users2 users2 : users2s) {
                                StringBuffer sb = new StringBuffer();
                                sb.append("This is an automatic e-mail from the tingco Worklog. \n\n");
                                if (device != null) {
                                    sb.append("Device: " + device.getDeviceName() + "\n");
                                    if (device.getAssetId() != null) {
                                        sb.append("Asset ID: " + device.getAssetId() + "\n");
                                    }
                                }
                                sb.append("Created by: " + createdUsers2.getFirstName() + " " + createdUsers2.getLastName() + "\n\n");
                                sb.append(TCMUtil.convertHexToString(body));
                                sendEmail(TCMUtil.convertHexToString("576f726b6c6f673a20" + subject), sb.toString(), users2.getUserEmail());
                            }
                        }
                    }
                    boolean result = dao.saveObjectComments(oc, session);
                    if (!result) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Error Occured while Inserting");
                        return tingcoDevice;
                    }
                } else {
                    tingcoDevice.getMsgStatus().setResponseCode(-10);
                    tingcoDevice.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoDevice;
                }
            } else {
                tingcoDevice.getMsgStatus().setResponseCode(-3);
                tingcoDevice.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoDevice;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error Occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoDevice;
    }

    public void sendEmail(String subject, String body, String useremail) throws MessagingException {
        MimeMessage message = new MimeMessage(TingcoConstants.getMailSession());
        message.setSubject(subject);
        MimeBodyPart textPart = new MimeBodyPart();
//            textPart.setContent(body, "text/html");
        textPart.setText(body);
        Multipart mp = new MimeMultipart();
        mp.addBodyPart(textPart);

        message.setContent(mp);
        message.setFrom(new InternetAddress("Info24"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(useremail));

        Transport transport = TingcoConstants.getMailSession().getTransport();
        transport.connect(TingcoConstants.getEmailIp(), TingcoConstants.getEmailFrom(), TingcoConstants.getEmailPwd());

        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        transport.close();
    }

    private void sendTingcoSupportEmail(String subject, String body, String toEmail) throws MessagingException {
        MimeMessage message = new MimeMessage(TingcoConstants.getMailSession());
        message.setSubject(subject);
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText(body);
        Multipart mp = new MimeMultipart();
        mp.addBodyPart(textPart);

        message.setContent(mp);
        message.setFrom(new InternetAddress("Info24"));
//        message.addRecipient(Message.RecipientType.TO, new InternetAddress(TingcoConstants.getEmailFrom()));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

        Transport transport = TingcoConstants.getMailSession().getTransport();
        transport.connect(TingcoConstants.getEmailIp(), TingcoConstants.getEmailFrom(), TingcoConstants.getEmailPwd());

        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        transport.close();
    }

    private TingcoDevice updateObjectComment(String objectCommentId, String subject, String body, String del) {
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
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
                    session = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    boolean result = dao.updateObjectComment(objectCommentId, subject, body, del, session);
                    if (!result) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Error Occured while Updating");
                        return tingcoDevice;
                    }
                } else {
                    tingcoDevice.getMsgStatus().setResponseCode(-10);
                    tingcoDevice.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoDevice;
                }
            } else {
                tingcoDevice.getMsgStatus().setResponseCode(-3);
                tingcoDevice.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoDevice;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error Occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return tingcoDevice;
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}

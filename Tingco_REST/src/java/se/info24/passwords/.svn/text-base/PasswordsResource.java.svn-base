/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.passwords;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
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
import se.info24.pojo.ApplicationEmailTemplates;
import se.info24.pojo.ApplicationSettings;
import se.info24.pojo.UserLoginResetRequests;
import se.info24.pojo.Users2;
import se.info24.restUtil.UtilitiesResource;
import se.info24.user.UserManager;
import se.info24.user.UserSession2DAO;
import se.info24.usersjaxb.UserSession;
import se.info24.usersjaxb.TingcoUsers;
import se.info24.usersjaxb.Users;
import se.info24.util.HibernateUtil;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/password")
public class PasswordsResource {

    @Context
    private UriInfo context;
    UtilitiesResource utilsResource = new UtilitiesResource();
    UserSession2DAO userSessions2DAO = new UserSession2DAO();
    SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of PasswordsResource */
    public PasswordsResource() {
    }

    /**
     * Password_Save
     * @param request
     * @param userID
     * @param domainID
     * @param pwd
     * @return
     */
    @GET
    @Path("/save/rest/{rest}/userid/{userid}/domainid/{domainid}/password/{password}")
    @Produces("application/xml")
    @RESTDoc(methodName = "Password_Save", desc = "Save's the Password", req_Params = {"UserID;UUID", "DomainID;UUID", "Password;String"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_Save(@Context HttpServletRequest request, @PathParam("userid") String userID, @PathParam("domainid") String domainID,
            @PathParam("password") String pwd) {
        return savePassword(userID, domainID, pwd);
    }

    /**
     * Password_Save
     * @param request
     * @param userID
     * @param domainID
     * @param pwd
     * @return
     */
    @GET
    @Path("/save/json/{json}/userid/{userid}/domainid/{domainid}/password/{password}")
    @Produces("application/json")
    public TingcoUsers getJson_Save(@Context HttpServletRequest request, @PathParam("userid") String userID, @PathParam("domainid") String domainID,
            @PathParam("password") String pwd) {
        return savePassword(userID, domainID, pwd);
    }

    /**
     * IsCorrectPassword
     * @param userID
     * @param domainId
     * @param pwd
     * @return
     */
    @GET
    @Path("/iscorrect/rest/{rest}/userid/{userid}/domainid/{domainid}/password/{password}")
    @Produces("application/xml")
    @RESTDoc(methodName = "IsCorrectPassword", desc = "Checks whether the old password entered is correct or not.", req_Params = {"UserID;UUID", "DomainID;UUID", "Password;String"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_IsCorrect(@PathParam("userid") String userID, @PathParam("domainid") String domainId,
            @PathParam("password") String pwd) {
        return isCorrectPassword(userID, domainId, pwd);
    }

    /**
     * IsCorrectPassword
     * @param userID
     * @param domainId
     * @param pwd
     * @return
     */
    @GET
    @Path("/iscorrect/json/{json}/userid/{userid}/domainid/{domainid}/password/{password}")
    @Produces("application/json")
    public TingcoUsers getJson_IsCorrect(@PathParam("userid") String userID, @PathParam("domainid") String domainId,
            @PathParam("password") String pwd) {
        return isCorrectPassword(userID, domainId, pwd);
    }

    /**
     * IsAmongLast4Passwords
     * @param request
     * @param userID
     * @param domainID
     * @param pwd
     * @return
     */
    @GET
    @Path("/isamonglast4/rest/{rest}/userid/{userid}/domainid/{domainid}/password/{password}")
    @Produces("application/xml")
    @RESTDoc(methodName = "IsAmongLast4Passwords", desc = "Tells whether the password is one among the previous 4 passwords", req_Params = {"UserID;UUID", "ApplicationID;UUID", "Password;String"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_IsAmongLast4Passwords(@Context HttpServletRequest request, @PathParam("userid") String userID, @PathParam("domainid") String domainID,
            @PathParam("password") String pwd) {
        return isPreviousPassword(userID, domainID, pwd, request);
    }

    /**
     * IsAmongLast4Passwords
     * @param request
     * @param userID
     * @param domainID
     * @param pwd
     * @return
     */
    @GET
    @Path("/isamonglast4/json/{json}/userid/{userid}/domainid/{domainid}/password/{password}")
    @Produces("application/json")
    public TingcoUsers getJson_IsAmongLast4Passwords(@Context HttpServletRequest request, @PathParam("userid") String userID, @PathParam("domainid") String domainID,
            @PathParam("password") String pwd) {
        return isPreviousPassword(userID, domainID, pwd, request);
    }

    /**
     * insertpasswordrequest
     * @param email
     * @param applicationid
     * @param domainID
     * @param requestcode
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/insertpasswordrequest/rest/{rest}/email/{email}/applicationid/{applicationid}/domainid/{domainid}/requestcode/{requestcode}{applicationname:(/applicationname/[^/]+?)?}")
    @RESTDoc(methodName = "insertpasswordrequest", desc = "Used to insert password request", req_Params = {"ApplicationID;UUID", "domainid;UUID", "requestcode;String"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_InsertPasswordRequest(@PathParam("email") String email, @PathParam("applicationid") String applicationid,
            @PathParam("domainid") String domainid, @PathParam("requestcode") String requestcode, @PathParam("applicationname") String applicationName) {
        return insertPasswordRequest(email, applicationid, domainid, requestcode, applicationName);
    }

    /**
     * insertpasswordrequest
     * @param email
     * @param applicationid
     * @param domainID
     * @param requestcode
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/insertpasswordrequest/json/{json}/email/{email}/applicationid/{applicationid}/domainid/{domainid}/requestcode/{requestcode}{applicationname:(/applicationname/[^/]+?)?}")
    public TingcoUsers getJson_InsertPasswordRequest(@PathParam("email") String email, @PathParam("applicationid") String applicationid,
            @PathParam("domainid") String domainid, @PathParam("requestcode") String requestcode, @PathParam("applicationname") String applicationName) {
        return insertPasswordRequest(email, applicationid, domainid, requestcode, applicationName);
    }

    /**
     * resetUserPassword
     * @param loginresetrequestid
     * @param loginresetrequestcode
     * @param domainid
     * @param password
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/resetuserpassword/rest/{rest}/loginresetrequestid/{loginresetrequestid}/loginresetrequestcode/{loginresetrequestcode}/domainid/{domainid}{password:(/password/[^/]+?)?}")
    @RESTDoc(methodName = "resetuserpassword", desc = "Used to check the password reset request", req_Params = {"loginresetrequestid;string", "loginresetrequestcode;string", "domainid;string"}, opt_Params = {"password;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_resetUserPassword(@PathParam("loginresetrequestid") String loginResetRequestId, @PathParam("loginresetrequestcode") String loginResetRequestCode, @PathParam("domainid") String domainId, @PathParam("password") String password) {
        return resetUserPassword(loginResetRequestId, loginResetRequestCode, domainId, password);
    }

    /**
     * resetUserPassword
     * @param loginresetrequestid
     * @param loginresetrequestcode
     * @param domainid
     * @param password
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/resetuserpassword/json/{json}/loginresetrequestid/{loginresetrequestid}/loginresetrequestcode/{loginresetrequestcode}/domainid/{domainid}{password:(/password/[^/]+?)?}")
    public TingcoUsers getJson_resetUserPassword(@PathParam("loginresetrequestid") String loginResetRequestId, @PathParam("loginresetrequestcode") String loginResetRequestCode, @PathParam("domainid") String domainId, @PathParam("password") String password) {
        return resetUserPassword(loginResetRequestId, loginResetRequestCode, domainId, password);
    }

    /**
     * POST method for creating an instance of PasswordsResource
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

    private TingcoUsers resetUserPassword(String loginResetRequestId, String loginResetRequestCode, String domainId, String password) {
        UserManager manager = new UserManager();
        TingcoUsers user = manager.buildUserTemplate();
        Calendar cal = Calendar.getInstance();
        if (loginResetRequestId.equals("")) {
            user.getMsgStatus().setResponseCode(-1);
            user.getMsgStatus().setResponseText("LoginResetRequestId should not be empty");
            return user;
        }
        if (loginResetRequestCode.equals("")) {
            user.getMsgStatus().setResponseCode(-1);
            user.getMsgStatus().setResponseText("LoginResetRequestCode should not be empty");
            return user;
        }
        if (domainId.equals("")) {
            user.getMsgStatus().setResponseCode(-1);
            user.getMsgStatus().setResponseText("domainId should not be empty");
            return user;
        }
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            List<UserLoginResetRequests> userLoginResetRequestList = manager.checkValidPasswordResetRequest(loginResetRequestId, loginResetRequestCode, session);
            if (!userLoginResetRequestList.isEmpty()) {
                UserLoginResetRequests userLoginResetRequest = userLoginResetRequestList.get(0);
                boolean valid = userLoginResetRequest.getRequestExpiryDate().compareTo(cal.getTime()) >= 0;
                if (!valid) {
                    user.getMsgStatus().setResponseCode(-1);
                    user.getMsgStatus().setResponseText("Request Expired");
                    return user;
                } else if (valid && password.equals("")) {
                    user.getMsgStatus().setResponseCode(0);
                    user.getMsgStatus().setResponseText("Valid Request");
                    return user;
                } else if (valid && (!password.equals(""))) {
                    password = password.split("/")[2];
                    if (manager.savePassword(userLoginResetRequest.getUserId(), domainId, password, session)) {
                        if (!manager.deleteLoginResetRequests(loginResetRequestId, loginResetRequestCode, session)) {
                            user.getMsgStatus().setResponseCode(-1);
                            user.getMsgStatus().setResponseText("Error occured while deleting LoginResetRequest Record");
                            return user;
                        }
                    } else {
                        user.getMsgStatus().setResponseCode(-1);
                        user.getMsgStatus().setResponseText("Error occured while saving new Password");
                        return user;
                    }
                }
            } else {
                user.getMsgStatus().setResponseCode(-1);
                user.getMsgStatus().setResponseText("No Record Found");
                return user;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

    private TingcoUsers savePassword(String userID, String domainID, String pwd) {
        Session session = null;
        UserManager manager = new UserManager();
        TingcoUsers user = null;
        long requestedTime = System.currentTimeMillis();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            boolean success = manager.savePassword(userID, domainID, pwd, session);
            user = manager.buildUserTemplate();
            if (success) {
                user.getMsgStatus().setResponseCode(0);
                user.getMsgStatus().setResponseText("OK");
                Users users = new Users();
                UserSession sess = new UserSession();
                sess.setDomainID(domainID);
                sess.setUserID(userID);
                sess.setAuthenticationToken(UUID.randomUUID().toString());

                users.setUserSession(sess);
                user.setUsers(users);
            } else {
                user.getMsgStatus().setResponseCode(-1);
                user.getMsgStatus().setResponseText("Error");
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }

        }
        return user;
    }

    private TingcoUsers isCorrectPassword(String userID, String domainId, String pwd) {
        Session session = null;
        UserManager manager = new UserManager();
        TingcoUsers user = null;
        long requestedTime = System.currentTimeMillis();
        try {
            user = manager.buildUserTemplate();
            session = HibernateUtil.getSessionFactory().openSession();
            boolean correct = manager.isCorrectPassword(userID, domainId, pwd, session);
            if (correct) {
                user.getMsgStatus().setResponseCode(0);
                user.getMsgStatus().setResponseText("Ok");
            } else {
                user.getMsgStatus().setResponseCode(-1);
                user.getMsgStatus().setResponseText("Error");
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            user.getMsgStatus().setResponseCode(-1);
            user.getMsgStatus().setResponseText("Error");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

    private TingcoUsers isPreviousPassword(String userID, String domainID, String pwd, HttpServletRequest request) {
        long requestedTime = System.currentTimeMillis();
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserManager manager = new UserManager();
        TingcoUsers user = null;
        try {
            user = manager.buildUserTemplate();
            boolean present = manager.isPrevious(userID, domainID, pwd, session);
            if (!present) {
                user.getMsgStatus().setResponseCode(0);
                user.getMsgStatus().setResponseText("Ok");
            } else {
                user.getMsgStatus().setResponseCode(-1);
                user.getMsgStatus().setResponseText("Error");
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            delayLog(requestedTime);
            session.close();
        }
        return user;
    }

    public TingcoUsers insertPasswordRequest(String email, String applicationid, String domainid, String requestcode, String applicationName) {

        UserManager manager = new UserManager();
        TingcoUsers user = manager.buildUserTemplate();
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            if (manager.isValidEmail(email, session)) {
                if (manager.isValidApplication(applicationid, domainid, session)) {
                    Transaction tx = session.beginTransaction();
                    try {
                        Users2 users2 = (Users2) session.getNamedQuery("getUserByLoginNameandDomainId").setString("loginName", email).setString("domainID", domainid).list().get(0);
                        UserLoginResetRequests ulrr = new UserLoginResetRequests();
                        ulrr.setLoginResetRequestId(UUID.randomUUID().toString());
                        ulrr.setUserId(users2.getUserId());
                        ulrr.setLoginResetRequestCode(requestcode);
                        ulrr.setApplicationID(applicationid);
                        GregorianCalendar gc = new GregorianCalendar();
                        gc.add(GregorianCalendar.HOUR, 24);
                        ulrr.setRequestExpiryDate(gc.getTime());
                        GregorianCalendar gc1 = new GregorianCalendar();
                        ulrr.setCreatedDate(gc1.getTime());
                        ulrr.setUpdatedDate(gc1.getTime());
                        ulrr.setIsMailSent(0);
                        session.saveOrUpdate(ulrr);
                        tx.commit();
                        session.flush();
                        List<ApplicationSettings> appSettingsList = userSessions2DAO.getApplicationSettings(applicationid, session);
                        String ApplicationBaseURL = null;
                        String ResetPasswordURL = null;
                        if (!appSettingsList.isEmpty()) {
                            if (!applicationName.equals("")) {
                                applicationName = applicationName.split("/")[2];
                            } else {
                                applicationName = null;
                            }

                            for (ApplicationSettings as : appSettingsList) {
                                if (applicationName == null) {
                                    if (as.getApplicationSettingName().equalsIgnoreCase("ApplicationBaseURL")) {
                                        ApplicationBaseURL = as.getApplicationSettingValue();
                                    }
                                    if (as.getApplicationSettingName().equalsIgnoreCase("ResetPasswordURL")) {
                                        ResetPasswordURL = as.getApplicationSettingValue();
                                    }
                                } else if (applicationName.equalsIgnoreCase("TCM")) {
                                    if (as.getApplicationSettingName().equalsIgnoreCase("ApplicationBaseURL")) {
                                        ApplicationBaseURL = as.getApplicationSettingValue();
                                    }
                                    if (as.getApplicationSettingName().equalsIgnoreCase("TCMResetPasswordURL")) {
                                        ResetPasswordURL = as.getApplicationSettingValue();
                                    }
                                }
                            }
                        }
                        List<ApplicationEmailTemplates> appEmailTemplates = userSessions2DAO.getApplicationEmailTemplates(users2.getCountryId(), session);
                        for (ApplicationEmailTemplates apet : appEmailTemplates) {
                            if (users2.getCountryId() == apet.getCountryId() && apet.getObjectCode().equalsIgnoreCase("FORGOTTENPASSWORD") && apet.getApplications().getApplicationId().equalsIgnoreCase(applicationid)) {
                                String emailBody = "Hi, " + users2.getFirstName() + " " + users2.getLastName() + ",\n\n " + apet.getEmailBody() + " \n" + ApplicationBaseURL + "" + ResetPasswordURL + "?" + "LoginResetRequestId=" + ulrr.getLoginResetRequestId() + "&LoginResetRequestCode=" + ulrr.getLoginResetRequestCode() + "\n\n\n " + apet.getEmailSignature();
                                if (utilsResource.sendMail(email, apet.getEmailSubject(), emailBody)) {
                                    userSessions2DAO.setIsEmailSent(ulrr, session);
                                }
                            }
                        }

                        user.getMsgStatus().setResponseCode(0);
                        user.getMsgStatus().setResponseText("User ID is " + users2.getUserId());
                    } catch (Exception e) {
                        user.getMsgStatus().setResponseCode(-1);
                        user.getMsgStatus().setResponseText("Error");
                        TCMUtil.exceptionLog(getClass().getName(), e);
                    }

                } else {
                    user.getMsgStatus().setResponseCode(-1);
                    user.getMsgStatus().setResponseText("applicationid and domainid are not valid");
                }
            } else {
                user.getMsgStatus().setResponseCode(-1);
                user.getMsgStatus().setResponseText("EmailID not valid");
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            user.getMsgStatus().setResponseCode(-1);
            user.getMsgStatus().setResponseText("Error occured");
        } finally {
            delayLog(requestedTime);
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public PasswordResource getPasswordResource() {
        return new PasswordResource();
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}

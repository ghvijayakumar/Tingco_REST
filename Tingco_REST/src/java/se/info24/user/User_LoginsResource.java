/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.user;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.xml.datatype.DatatypeConfigurationException;
import org.hibernate.Session;
import se.info24.application.ApplicationDAO;
import se.info24.pojo.ApplicationModuleTranslations;
import se.info24.pojo.ApplicationPackageModules;
import se.info24.pojo.GroupApplicationPackages;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.Users2;
import se.info24.usersjaxb.TingcoUsers;
import se.info24.usersjaxb.User;
import se.info24.usersjaxb.Users;
import se.info24.util.HibernateUtil;
import se.info24.util.RESTDoc;
import se.info24.util.RSAPassword;
import se.info24.util.TCMUtil;
import se.info24.util.UserPermissions;

/**
 * REST Web Service
 *
 * @author Sridhar Dasari
 */
@Path("/login")
public class User_LoginsResource {

    @Context
    private UriInfo context;
    UserDAO userDAO = new UserDAO();
    @Context
    HttpServletRequest request;
    @Context
    HttpServletResponse response;
    DateFormat logs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public final static Hashtable<String, Hashtable<String, ArrayList>> permissions = new Hashtable<String, Hashtable<String, ArrayList>>();

    /** Creates a new instance of User_LoginsResource */
    public User_LoginsResource() {
    }

    /**
     * Retrieves representation of an instance of se.info24.user.User_LoginsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/rest/{rest}/loginname/{loginname}/password/{password}/applicationid/{applicationid}/domainid/{domainid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "User_Login", desc = "Used to login a user", req_Params = {"LoginName;String", "Password;String", "ApplicationID;UUID", "DomainID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUsers getXml(@PathParam("rest") String version, @PathParam("loginname") String loginName, @PathParam("password") String pwd, @PathParam("applicationid") String appID, @PathParam("domainid") String domainID) {
        return performLogin(loginName, pwd, appID, domainID);
    }

    @GET
    @Path("/json/{json}/loginname/{loginname}/password/{password}/applicationid/{applicationid}/domainid/{domainid}")
    @Produces("application/json")
    public TingcoUsers getJSON(@Context HttpServletRequest request, @Context HttpServletResponse response, @PathParam("json") String version, @PathParam("loginname") String loginName, @PathParam("password") String pwd, @PathParam("applicationid") String appID, @PathParam("domainid") String domainID) {
        return performLogin(loginName, pwd, appID, domainID);
    }

    @GET
    @Path("tcm/rest/{rest}/loginname/{loginname}/password/{password}/applicationid/{applicationid}/domainid/{domainid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "User_Login", desc = "Used to login a user", req_Params = {"LoginName;String", "Password;String", "ApplicationID;UUID", "DomainID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUsers getXmlTCM(@PathParam("rest") String version, @PathParam("loginname") String loginName, @PathParam("password") String pwd, @PathParam("applicationid") String appID, @PathParam("domainid") String domainID) {
        return performLoginTCM(loginName, pwd, appID, domainID);
    }

    @GET
    @Path("tcm/json/{json}/loginname/{loginname}/password/{password}/applicationid/{applicationid}/domainid/{domainid}")
    @Produces("application/json")
    @RESTDoc(methodName = "User_Login", desc = "Used to login a user", req_Params = {"LoginName;String", "Password;String", "ApplicationID;UUID", "DomainID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUsers getJsonTCM(@PathParam("rest") String version, @PathParam("loginname") String loginName, @PathParam("password") String pwd, @PathParam("applicationid") String appID, @PathParam("domainid") String domainID) {
        return performLoginTCM(loginName, pwd, appID, domainID);
    }

    /**
     * POST method for creating an instance of User_LoginResource
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
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public User_LoginResource getUser_LoginResource(@PathParam("id") String id) {
        return User_LoginResource.getInstance(id);
    }

    private TingcoUsers performLoginTCM(String loginName, String pwd, String appID, String domainID) {
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        Hashtable<String, ArrayList> functionAreas = new Hashtable<String, ArrayList>();
        UserManager manager = new UserManager();
        TingcoUsers user = null;
        GregorianCalendar gc = new GregorianCalendar();
        String token = "";
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            user = manager.buildUserTemplate();
            if (manager.isValidApplication(appID, domainID, session)) {
                if (manager.validUser(loginName, domainID, gc.getTime(), session)) {
                    Users2 u = manager.getUser();
                    if (u.getActiveToDate().before(gc.getTime())) {
                        user.getMsgStatus().setResponseCode(-1);
                        user.getMsgStatus().setResponseText("No Longer Active");
                        return user;
                    }
                    String enc_Pwd = RSAPassword.encryptedPwd(pwd);
                    u.setLastPasswordChangedDate(gc.getTime());
                    long lastchnagd = u.getLastPasswordChangedDate().getTime();
                    long curr = gc.getTime().getTime();
                    long diff = (curr - lastchnagd) / (1000 * 60 * 60 * 24);
                    if (u.getIsLockedOut() == 0 && enc_Pwd.equalsIgnoreCase(u.getPassword())) {
                        if (diff >= 90) {
                            user.getMsgStatus().setResponseCode(-5);
                            user.getMsgStatus().setResponseText("ChangePasswordRequired");
                            Users users = new Users();
                            User us = new User();
                            us.setSeqNo(1);
                            us.setUserID(u.getUserId());
                            us.setDomainID(u.getId().getDomainId());
                            us.setApplicationID(appID);
                            u.setChangePwdRequired(1);
                            manager.updateUser(u, session);
                            users.getUser().add(us);
                            user.setUsers(users);
                            return user;
                        }
                        u.setFailedPasswordAttemptCount(0);
                        u.setLastLoginDate(gc.getTime());
                        manager.updateUser(u, session);

                        UserSessions2 sess = new UserSessions2();
                        sess.setUserSessionId(UUID.randomUUID().toString());
                        sess.setCreatedDate(gc.getTime());
                        sess.setUpdatedDate(gc.getTime());
                        gc.add(GregorianCalendar.MINUTE, 15);
                        sess.setActiveToDate(gc.getTime());
                        sess.setApplicationId(appID);
                        sess.setDomainId(domainID);
                        token = UUID.randomUUID().toString().toUpperCase();
                        sess.setAuthenticationToken(token);
                        sess.setUserId(u.getUserId());
                        manager.saveUserSession(sess, session);
                        UserPermissions userPermissions = new UserPermissions();
                        functionAreas = userPermissions.getUserRolePermissions2ByUserID(u.getUserId());
                        TCMUtil.setFunctionAreas(functionAreas);
                        TCMUtil.loger(User_LoginsResource.class.getName(), "[function " + functionAreas + "]", "Info");
                        permissions.put(u.getUserId(), functionAreas);

                        ApplicationDAO applicationDAO = new ApplicationDAO();
                        List<GroupApplicationPackages> gAPList = applicationDAO.getGroupAppPackagesForApplicationModule(session, u.getGroupId());
                        Set<String> applicationPackageId = applicationDAO.getApplicationPackageId(gAPList);
                        Set<String> applicationModuleId = new HashSet<String>();
                        if (!applicationPackageId.isEmpty()) {
                            List<ApplicationPackageModules> aPMList = applicationDAO.getApplicationPackageModulesByModuleIdList(session, applicationPackageId);
                            applicationModuleId = applicationDAO.getApplicationModuleId(aPMList);
                        }
                        List<ApplicationModuleTranslations> transList = new ArrayList<ApplicationModuleTranslations>();
                        if (!applicationModuleId.isEmpty()) {
                            transList = applicationDAO.getAppModuleTransByAppModuleIdListAndCountryId(session, applicationModuleId, u.getCountryId());
                        }
                        user = manager.getLoggedinUserTCM(0, loginName, appID, domainID, token, request.getRemoteHost(), u.getUserId(), session, transList);
                    } else {
                        TCMUtil.loger(User_LoginsResource.class.getName(), "Password match fails", "Info");
                        if (u.getFailedPasswordAttemptCount() == 3) {
                            user.getMsgStatus().setResponseCode(-4);
                            manager.lockUser(u, gc, session);
                            user = manager.getLoggedinUser(-4, loginName, appID, domainID, "", request.getRemoteHost(), u.getUserId(), session);
                        } else {
                            user.getMsgStatus().setResponseCode(-2);
                            u.setFailedPasswordAttemptCount(u.getFailedPasswordAttemptCount() + 1);
                            manager.updateUser(u, session);
                            user = manager.getLoggedinUser(-2, loginName, appID, domainID, "", request.getRemoteHost(), u.getUserId(), session);
                        }
                    }

//                    }
                } else {
                    user = manager.getLoggedinUser(-2, loginName, appID, domainID, "", request.getRemoteHost(), null, session);
                }

            } else {
                user = manager.getLoggedinUser(-1, loginName, appID, domainID, "", request.getRemoteHost(), null, session);
            }
//            }
        } catch (DatatypeConfigurationException e) {
            TCMUtil.exceptionLog(User_LoginsResource.class.getName(), e);
        } finally {
            if (session != null) {
                session.close();
            }
            TCMUtil.loger(User_LoginsResource.class.getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
            System.gc();
        }
        return user;
    }

    private TingcoUsers performLogin(String loginName, String pwd, String appID, String domainID) {
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        Hashtable<String, ArrayList> functionAreas = new Hashtable<String, ArrayList>();
        UserManager manager = new UserManager();
        TingcoUsers user = null;
        GregorianCalendar gc = new GregorianCalendar();
        String token = "";
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            user = manager.buildUserTemplate();

//            List<Users2> users2list = userDAO.getUserByAppIdEmails(domainID, loginName, session);
//            if (!users2list.isEmpty()) {
            if (manager.isValidApplication(appID, domainID, session)) {
                if (manager.validUser(loginName, domainID, gc.getTime(), session)) {
                    Users2 u = manager.getUser();
//                    UserLog log = new UserLog();
//                    log.setCreatedDate(gc.getTime());
//                    log.setAction("Login");
//                    log.setTableName("Users2;UsersSession2");
//                    log.setRequestIp(request.getRemoteAddr());
////                    System.out.println("******************"+getClientIpAddr(request));
////                    log.setRequestIp(getClientIpAddr(request));
//                    log.setUpdatedDate(gc.getTime());
//                    log.setUserId(u.getUserId());
//                    dao.saveUserLog(log, session);
                    if (u.getActiveToDate().before(gc.getTime())) {
                        user.getMsgStatus().setResponseCode(-1);
                        user.getMsgStatus().setResponseText("No Longer Active");
                        return user;
                    }
                    String enc_Pwd = RSAPassword.encryptedPwd(pwd);
                    u.setLastPasswordChangedDate(gc.getTime());
                    long lastchnagd = u.getLastPasswordChangedDate().getTime();
                    long curr = gc.getTime().getTime();
                    long diff = (curr - lastchnagd) / (1000 * 60 * 60 * 24);
                    if (u.getIsLockedOut() == 0 && enc_Pwd.equalsIgnoreCase(u.getPassword())) {
                        if (diff >= 90) {
                            user.getMsgStatus().setResponseCode(-5);
                            user.getMsgStatus().setResponseText("ChangePasswordRequired");
                            Users users = new Users();
                            User us = new User();
                            us.setSeqNo(1);
                            us.setUserID(u.getUserId());
                            us.setDomainID(u.getId().getDomainId());
                            us.setApplicationID(appID);
                            u.setChangePwdRequired(1);
                            manager.updateUser(u, session);
                            users.getUser().add(us);
                            user.setUsers(users);
                            return user;
                        }
                        u.setFailedPasswordAttemptCount(0);
                        u.setLastLoginDate(gc.getTime());
                        manager.updateUser(u, session);

                        UserSessions2 sess = new UserSessions2();
                        sess.setUserSessionId(UUID.randomUUID().toString());
                        sess.setCreatedDate(gc.getTime());
                        sess.setUpdatedDate(gc.getTime());
                        gc.add(GregorianCalendar.MINUTE, 15);
                        sess.setActiveToDate(gc.getTime());
                        sess.setApplicationId(appID);
                        sess.setDomainId(domainID);
                        token = UUID.randomUUID().toString().toUpperCase();
                        sess.setAuthenticationToken(token);
                        sess.setUserId(u.getUserId());
                        manager.saveUserSession(sess, session);
                        user = manager.getLoggedinUser(0, loginName, appID, domainID, token, request.getRemoteHost(), u.getUserId(), session);
                    } else {
                        TCMUtil.loger(User_LoginsResource.class.getName(), "Password match fails", "Info");
                        if (u.getFailedPasswordAttemptCount() != null) {
                            if (u.getFailedPasswordAttemptCount() == 3) {
                                user.getMsgStatus().setResponseCode(-4);
                                manager.lockUser(u, gc, session);
                                user = manager.getLoggedinUser(-4, loginName, appID, domainID, "", request.getRemoteHost(), u.getUserId(), session);
                            } else {
                                user.getMsgStatus().setResponseCode(-2);
                                u.setFailedPasswordAttemptCount(u.getFailedPasswordAttemptCount() + 1);
                                manager.updateUser(u, session);
                                user = manager.getLoggedinUser(-2, loginName, appID, domainID, "", request.getRemoteHost(), u.getUserId(), session);
                            }
                        } else {
                            user.getMsgStatus().setResponseCode(-2);
                            u.setFailedPasswordAttemptCount(1);
                            manager.updateUser(u, session);
                            user = manager.getLoggedinUser(-2, loginName, appID, domainID, "", request.getRemoteHost(), u.getUserId(), session);
                        }
                    }
                    UserPermissions userPermissions = new UserPermissions();
                    functionAreas = userPermissions.getUserRolePermissions2ByUserID(u.getUserId());
                    TCMUtil.setFunctionAreas(functionAreas);
                    TCMUtil.loger(User_LoginsResource.class.getName(), "[function " + functionAreas + "]", "Info");
                    permissions.put(u.getUserId(), functionAreas);
//                    }
                } else {
                    user = manager.getLoggedinUser(-2, loginName, appID, domainID, "", request.getRemoteHost(), null, session);
                }

            } else {
                user = manager.getLoggedinUser(-1, loginName, appID, domainID, "", request.getRemoteHost(), null, session);
            }
//            }
        } catch (DatatypeConfigurationException e) {
            TCMUtil.exceptionLog(User_LoginsResource.class.getName(), e);
        } finally {
            if (session != null) {
                session.close();
            }
            TCMUtil.loger(User_LoginsResource.class.getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
            System.gc();
        }
        return user;
    }

    public String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}

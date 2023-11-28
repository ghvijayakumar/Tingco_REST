/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Properties;
import java.util.TimeZone;
import java.util.UUID;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import se.info24.ismOperations.APILogDAO;
import se.info24.ismOperationsPojo.Apilog;
import se.info24.pojo.UserSessions2;
import se.info24.user.UserSession2DAO;
import se.info24.user.User_LoginsResource;
import javax.ws.rs.core.Context;
import org.hibernate.Transaction;

/**
 *
 * @author Sridhar Dasari; Sekhar Babu
 */
public class TCMGateway extends com.sun.jersey.spi.container.servlet.ServletContainer implements Filter {

    @Context
    private HttpServletRequest req;
    APILogDAO apiLogDAO = new APILogDAO();
    UserSession2DAO userSession2DAO = new UserSession2DAO();
    DbManager dbManager = new DbManager();
    Hashtable<String, ArrayList> functionAreas = new Hashtable<String, ArrayList>();
    private FilterConfig filterConfig;
    Calendar calendar1 = Calendar.getInstance();
    boolean firsttime = true;
//    DateFormat log = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void init() throws ServletException {
        super.init();
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");

        props.put("mail.smtp.host", TingcoConstants.getEmailIp());
        props.put("mail.smtp.port", TingcoConstants.getEmailPort());
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.fallback", "false");
        TingcoConstants.mailSession = javax.mail.Session.getDefaultInstance(props, null);
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TCMUtil.loger(getClass().getName(), "****************", "Info");
        Apilog apiLog = new Apilog();
        apiLog.setApilogId(UUID.randomUUID().toString());
        String requestPath = request.getPathInfo();
        String methodName = requestPath.substring(1, requestPath.indexOf("/", 1));
        apiLog.setApimethodName(methodName);
        apiLog.setApiparameters(requestPath.substring(methodName.length() + 1));
        GregorianCalendar gc = new GregorianCalendar();
        apiLog.setCreatedDate(gc.getTime());
        apiLog.setUpdatedDate(gc.getTime());
        if (request.getHeader("referrer") != null) {
            if (request.getHeader("referrer").length() > 200) {
                apiLog.setUrlReferrer(request.getHeader("referrer").substring(0, 200));
            } else {
                apiLog.setUrlReferrer(request.getHeader("referrer"));
            }
        } else {
            apiLog.setUrlReferrer(request.getHeader("referrer"));
        }
//        apiLog.setUrlReferrer(request.getHeader("referrer"));

        if (request.getHeader("user-agent") != null) {
            if (request.getHeader("user-agent").length() > 200) {
                apiLog.setUserAgent(request.getHeader("user-agent").substring(0, 200));
            } else {
                apiLog.setUserAgent(request.getHeader("user-agent"));
            }
        } else {
            apiLog.setUserAgent(request.getHeader("user-agent"));
        }

//        apiLog.setUserAgent(request.getHeader("user-agent"));
        apiLog.setUserIp(request.getRemoteAddr());

        if (request.getHeader("accept-language") != null) {
            if (request.getHeader("accept-language").length() > 50) {
                apiLog.setUserLanguages(request.getHeader("accept-language").substring(0, 50));
            } else {
                apiLog.setUserLanguages(request.getHeader("accept-language"));
            }
        } else {
            apiLog.setUserLanguages(request.getHeader("accept-language"));
        }


        if (request.getHeader("AuthenticationToken") != null) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            apiLog.setUserLoggedIn(1);  // Logged User
            UserSessions2 userSession2 = userSession2DAO.getUserSession2ByAuthToken(request.getHeader("AuthenticationToken"), session);
            if (userSession2 != null) {
                apiLog.setUserId(userSession2.getUserId());
                if (userSession2.getActiveToDate().after(gc.getTime())) {
                    request.setAttribute("USERSESSION", userSession2);
                    userSession2.setUpdatedDate(gc.getTime());
                    gc.add(GregorianCalendar.MINUTE, 15);
                    userSession2.setActiveToDate(gc.getTime());
                    userSession2DAO.saveUserSession2(userSession2, session);
                }
            }

            session.close();
        } else {
            apiLog.setUserLoggedIn(0); // Not Logged User.
        }
        apiLogDAO.saveAPILog(apiLog);

        response.setHeader("Access-Control-Request-Headers", "authenticationtoken,AuthenticationToken");
        response.setHeader("CUSTOM-HEADER-KEY", "authenticationtoken,AuthenticationToken");
        
        response.setHeader("Access-Control-Allow-Origin", TingcoConstants.getcorsenabledURL());
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "authenticationtoken,AuthenticationToken, X-Requested-With, Content-Type, Origin, Accept");
        response.setHeader("Access-Control-Expose-Headers", "authenticationtoken,AuthenticationToken");
        response.setHeader("Access-Control-Allow-Credentials", "true");
//        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
//            response.sendError(HttpServletResponse.SC_NO_CONTENT);
//        }

        super.service(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
//        StringBuffer jb = new StringBuffer();
//        String line = null;
//        try {
//            BufferedReader reader = req.getReader();
//            while ((line = reader.readLine()) != null) {
//                jb.append(line);
//            }
//            TCMUtil.loger(getClass().getName(), jb.toString(), "Info");
//            if (!jb.toString().contains("'") && !jb.toString().contains("%27") && !jb.toString().contains("*")) {
//
//            }
//        } catch (Exception e) {
//        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String incoming_Url = httpRequest.getRequestURI();
        String new_Url = incoming_Url.replace(httpRequest.getContextPath(), "");
        System.gc();

        if (!new_Url.contains("*") && !new_Url.contains("%27")) {
            if (new_Url.indexOf("/password/insertpasswordrequest/rest/v1/email/") != -1) {
                Calendar calendar2 = Calendar.getInstance();
                long milliseconds1 = calendar1.getTimeInMillis();
                long milliseconds2 = calendar2.getTimeInMillis();
                long diff = milliseconds2 - milliseconds1;
                long diffSeconds = diff / 1000;

                if (diffSeconds >= 30 || firsttime) {
                    calendar1.setTime(calendar2.getTime());
                    firsttime = false;
                    TCMUtil.loger(getClass().getName(), "[ New URl is :" + new_Url + "]", "Info");
                    RequestDispatcher request_Dispatcher = request.getRequestDispatcher(new_Url);

                    request_Dispatcher.forward(request, response);

                }
            } else if (new_Url.equalsIgnoreCase("/chargepoints/addactivatekeybmw/rest/v1") || new_Url.equalsIgnoreCase("/chargepoints/addactivatekey/rest/v1") || new_Url.equalsIgnoreCase("/chargepoints/getcountriesforev/rest/v1") || new_Url.contains("/tcp/") || new_Url.contains("/userlog/add/rest/") || new_Url.contains("/utility/") || new_Url.equalsIgnoreCase("/getmethods/rest/v1") ||
                    new_Url.contains("/tim/getdummydatatcm") || new_Url.contains("/senderrormail/") || new_Url.contains("/device/gettransactionreceiptdetails/rest/") || new_Url.contains("/contentitems/updatelookupactions/")) {
                TCMUtil.loger(getClass().getName(), "[ New URl is :" + new_Url + "]", "Info");
                RequestDispatcher request_Dispatcher = request.getRequestDispatcher(new_Url);
                request_Dispatcher.forward(request, response);
            } else {
                if ((new_Url.indexOf("login") != -1) || (new_Url.indexOf("password") != -1)) {
//                    TCMUtil.loger(getClass().getName(), "[ New URl is :" + new_Url + "]", "Info");
                    RequestDispatcher request_Dispatcher = request.getRequestDispatcher(new_Url);
                    request_Dispatcher.forward(request, response);

                } else {
                    TCMUtil.loger(getClass().getName(), "[ New URl is :" + new_Url + "]", "Info");
                    String userid = getUserId(httpRequest);
                    if (userid == null) {
                        emptyHashMap(userid, request, response, new_Url);
                    } else {
                        if (User_LoginsResource.permissions.containsKey(userid)) {
                            RequestDispatcher request_Dispatcher = request.getRequestDispatcher(new_Url);
                            request_Dispatcher.forward(request, response);
                        } else {
                            emptyHashMap(userid, request, response, new_Url);
                        }
                    }
                }
            }
        }

    }

    public String getUserId(HttpServletRequest httpRequest) {
        Session session = null;
        String authenticationToken = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Enumeration headerNames = httpRequest.getHeaders("authenticationtoken");
            while (headerNames.hasMoreElements()) {
                authenticationToken = (String) headerNames.nextElement();
            }
            UserSession2DAO dao = new UserSession2DAO();
            UserSessions2 userSessions2 = dao.getUserSession2ByAuthToken(authenticationToken, session);
            if (userSessions2 == null) {
                return null;
            }
            return userSessions2.getUserId();
        } catch (Exception e) {

            TCMUtil.exceptionLog(getClass().getName(), e);
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void emptyHashMap(String userid, ServletRequest request, ServletResponse response, String new_Url) throws ServletException, IOException {
        Session session = null;
        Transaction tx = null;
        String authenticationToken = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            if (userid == null) {
                if (new_Url.contains("rest")) {
                    new_Url = "/users/loggedinusersessionisvalid/rest/v1";
                } else {
                    new_Url = "/users/loggedinusersessionisvalid/json/v1";
                }

                RequestDispatcher request_Dispatcher = request.getRequestDispatcher(new_Url);
                request_Dispatcher.forward(request, response);
            } else {
                Hashtable<String, ArrayList> functionAreas = new Hashtable<String, ArrayList>();
                UserPermissions userPermissions = new UserPermissions();
                functionAreas = userPermissions.getUserRolePermissions2ByUserID(userid);
                TCMUtil.setFunctionAreas(functionAreas);
                TCMUtil.loger(User_LoginsResource.class.getName(), "[function " + functionAreas + "]", "Info");
                User_LoginsResource.permissions.put(userid, functionAreas);
                RequestDispatcher request_Dispatcher = request.getRequestDispatcher(new_Url);
                request_Dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            if (session != null) {
                session.close();
            }

        }

    }
}

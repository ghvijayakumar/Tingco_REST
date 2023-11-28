/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.lang.annotation.Annotation;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.apijaxb.TingcoAPI;
import se.info24.groupsjaxb.TingcoGroups;
import se.info24.usersjaxb.MsgStatus;
import se.info24.usersjaxb.ObjectFactory;
import se.info24.usersjaxb.TingcoUsers;
import se.info24.apidocJAXB.APIDescription;
import se.info24.apidocJAXB.APIDocMessage;
import se.info24.apidocJAXB.APIErrorCodes;
import se.info24.apidocJAXB.ErrorCode;
import se.info24.apidocJAXB.Method;
import se.info24.apidocJAXB.MethodFormats;
import se.info24.apidocJAXB.MethodHTTPRequests;
import se.info24.apidocJAXB.MethodParameters;
import se.info24.apidocJAXB.MethodSupportedInAPIVersions;
import se.info24.apidocJAXB.Methods;
import se.info24.apidocJAXB.Parameter;
import se.info24.pojo.UserLog;
import se.info24.user.UserDAO;

/**
 *
 * @author Sridhar Dasari
 */
public class TCMUtil {

    public static final String ADD = "CREATE";
    public static final String READ = "READ";
    public static final String DELETE = "DELETE";
    public static final String UPDATE = "UPDATE";
    public static final String SEND = "SEND";
    public static final String ACCESS = "ACCESS";
    public static final String DEVICE = "DEVICE";
    public static final String USERS = "USERS";
    public static final String SUPPORT = "SUPPORT";
    public static final String NETWORKS = "NETWORKS";
    public static final String MEDIA = "MEDIA";
    public static final String NEWS = "NEWS";
    public static final String ROLES = "ROLES";
    public static final String GROUPS = "GROUPS";
    public static final String CONTENT = "CONTENT";
    public static final String SERVICES = "SERVICES";
    public static final String USERALIAS = "USERALIAS";
    public static final String CONTROL = "CONTROL";
    public static final String CONTAINERS = "CONTAINERS";
    public static final String PRICEPLAN = "PRICEPLAN";
    public static final String ORDERS = "ORDERS";
    public static final String TRANSACTIONS = "TRANSACTIONS";
    public static final String PRODUCTS = "PRODUCTS";
    public static final String REPORTS = "REPORTS";
    public static final String APPLICATIONSOLUTIONS = "APPLICATIONSOLUTIONS";
    public static final String APPLICATIONPACKAGES = "APPLICATIONPACKAGES";
    public static final String APPLICATIONMODULES = "APPLICATIONMODULES";
    public static final String AGREEMENTS = "AGREEMENTS";
    public static final String BUILDINGS = "BUILDINGS";
    public static final String ROOMS = "ROOMS";
    public static final String SYSTEM = "SYSTEM";
    private static Hashtable<String, ArrayList> functionAreas = new Hashtable<String, ArrayList>();
    private static Logger logger = Logger.getLogger(TCMUtil.class);

    public TingcoUsers buildUserTemplate() throws DatatypeConfigurationException {
        ObjectFactory of = new ObjectFactory();
        TingcoUsers tcm_user = of.createTingcoUsers();
        tcm_user.setCreateRef("Info24");
        tcm_user.setMsgVer(new BigDecimal(1.0));
        tcm_user.setRegionalUnits("Metric");
        tcm_user.setTimeZone("UTC");
        tcm_user.setMsgID(UUID.randomUUID().toString());
        tcm_user.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));

        MsgStatus status = new MsgStatus();
        status.setResponseCode(0);
        status.setResponseText("OK");
        tcm_user.setMsgStatus(status);
        return tcm_user;
    }

    public TingcoUsers sessionExpired() throws DatatypeConfigurationException {
        ObjectFactory of = new ObjectFactory();
        TingcoUsers user = of.createTingcoUsers();
        user.setCreateRef("Info24");
        user.setMsgVer(new BigDecimal(1.0));
        user.setRegionalUnits("Metric");
        user.setTimeZone("UTC");
        user.setMsgID(UUID.randomUUID().toString());
        user.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));

        MsgStatus status = new MsgStatus();
        status.setResponseCode(-3);
        status.setResponseText("Session Expired...Please Login Again");

        user.setMsgStatus(status);

        return user;
    }

    public TingcoGroups group_SessionExpired() throws DatatypeConfigurationException {
        se.info24.groupsjaxb.ObjectFactory of = new se.info24.groupsjaxb.ObjectFactory();
        TingcoGroups groups = of.createTingcoGroups();
        groups.setCreateRef("");
        groups.setMsgID(null);
        groups.setMsgVer(new BigDecimal(1.0));
        groups.setRegionalUnits("Metric");
        groups.setTimeZone("UTC");
        groups.setMsgID(UUID.randomUUID().toString());
        groups.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));

        se.info24.groupsjaxb.MsgStatus status = new se.info24.groupsjaxb.MsgStatus();
        status.setResponseCode(-3);
        status.setResponseText("Session Expired...Please Login Again");
        groups.setMsgStatus(status);
        return groups;
    }

    public static TingcoAPI ping() throws DatatypeConfigurationException {
        long requestedTime = System.currentTimeMillis();
        se.info24.apijaxb.ObjectFactory of = new se.info24.apijaxb.ObjectFactory();
        TingcoAPI api = of.createTingcoAPI();
        api.setCreateRef("Info24");
        api.setMsgVer(new BigDecimal(1.0));
        api.setRegionalUnits("Metric");
        api.setTimeZone("UTC");
        api.setMsgID(UUID.randomUUID().toString());
        api.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));

        se.info24.apijaxb.MsgStatus status = new se.info24.apijaxb.MsgStatus();
        status.setResponseCode(0);
        status.setResponseText("OK");

        api.setMsgStatus(status);
        logger.info(" [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]");
        return api;
    }

    public String getMethods() throws ClassNotFoundException, IOException {

        long requestedTime = System.currentTimeMillis();
        Class aClass;
        String[] temp = null;
        String str = null;
//        String prefix = "se.info24.";
//        String[] classes = {"user.User_AddsResource", "user.User_AddToGroupsResource", "user.User_DeleteFromGroupsResource", "user.User_DeletesResource", "user.User_ExpireSessionsResource", "user.User_GetGroupMembershipssResource", "user.User_GetInfoesResource", "user.User_GetRolessResource", "user.User_LoginsResource", "user.User_SendMailToesResource", "user.User_UpdatesResource",
//            "util.API_PingsResource", "group.Group_AddsResource", "restUtil.GetAllLanguagessResource", "restUtil.GetAllTimezonessResource",
//            "application.GetAllApplicationssResource", "restUtil.IsEmailExistssResource",
//            "application.Application_AddsResource", "application.Application_DeletesResource", "application.Application_GetInfosResource", "application.Application_UpdatesResource",
//            "device.Device_GetInfosResource", "group.Group_DeletesResource", "group.Group_GetInfoesResource", "group.Group_UpdatesResource", "group.Group_AddsResource", "group.GroupTrust_AddsResource", "group.GetGroupTrustssResource", "user.User_AddTimeZonesResource",
//            "user.IsValidUserInviteLinksResource", "passwords.IsValidForgotPasswordLinksResource", "roles.GetAllRolessResource", "roles.UserRoleMemberships_AddsResource",
//            "roles.UserRoleMemberships_RemovesResource", "group.GetAllGroupssResource", "user.UserInvitesResource", "user.Lock_UsersResource",
//            "user.User_ActivatesResource", "passwords.IsAmongLast4PasswordssResource", "passwords.Password_SavesResource", "passwords.ForgotPassword_AddsResource",
//            "user.GetAllUserssResource", "passwords.IsCorrectPasswordsResource", "roles.AddRolesToOrganizationsResource", "device.DeviceManfacturers_AddsResource", "device.DeviceManfacturers_DeletesResource", "device.DeviceManfacturers_GetInfoesResource", "device.DeviceManfacturers_UpdatesResource", "device.GetAllDeviceManfacturerssResource",
//            "device.DeviceType_AddsResource", "device.DeviceType_DeletesResource", "device.DeviceType_GetInfoesResource", "device.DeviceTypes_UpdatesResource", "device.GetAllDeviceTypessResource",
//            "roles.GetAllAppRolessResource", "group.GetAllGroupTypessResource", "group.GetAllMyTrustedOrganizationssResource", "group.GetAllTrustingOrganizationssResource", "group.GetITrustOrganizationssResource", "group.GetOrganizationsRnPByGroupIDsResource", "roles.GetAllRolesandOrgByOrgIDsResource",
//            "permission.GetPermissionssResource", "user.GetUserRoleObjectPermissionssResource", "group.GroupTrust_DeletesResource", "group.IsGroupTrustExistssResource", "roles.IsOrganizationRoleExistssResource", "roles.IsRolePermissionExistssResource", "roles.OrganizationRole_UpdatesResource", "passwords.Password_SavesResource",
//            "device.GetDeviceStatusesResource", "roles.RolePermission_DeletesResource", "roles.RolePermissions_AddsResource", "user.UserRoleObjectPermissions_DeletesResource", "restUtil.Currency_AddsResource", "restUtil.Currency_DeletesResource", "restUtil.Currency_GetInfoesResource", "restUtil.Currency_UpdatesResource", "restUtil.GetAllCurrenciessResource",
//            "restUtil.Timezone_AddsResource", "restUtil.Timezone_UpdatesResource", "restUtil.Timezone_GetInfoesResource", "restUtil.Timezone_DeletesResource",
//            "content.ContentType_AddsResource", "content.ContentType_DeletesResource", "content.ContentType_UpdatesResource", "content.ContentTypes_GetInfoesResource", "content.GetAllContentTypessResource", "device.IsExistsDeviceName2sResource", "device.IsExistsDeviceNamesResource",
//            "network.GetAllNetworkTypessResource", "network.NetworkTypes_AddsResource", "network.NetworkTypes_DeletesResource", "network.NetworkTypes_GetInfoesResource", "network.NetworkTypes_UpdatesResource",
//            "service.GetAllServiceTypessResource", "service.ServiceTypes_AddsResource", "service.ServiceTypes_DeletesResource", "service.ServiceTypes_GetInfoesResource", "service.ServiceTypes_UpdatesResource",
//            "restUtil.Timezone_AddsResource", "restUtil.Timezone_DeletesResource", "restUtil.Timezone_GetInfoesResource", "restUtil.Timezone_UpdatesResource", "device.GetDeviceSettingsPackagesByDeviceTypeIDsResource",
//            "content.ContentCategory_UpdatesResource", "content.ContentCategory_DeletesResource", "service.Channel_AddsResource", "service.Channel_DeletesResource", "service.Channel_UpdatesResource", "service.Channel_GetInfoesResource", "service.GetAllChannelssResource", "device.DeviceTypeAttribute_AddsResource", "device.DeviceTypeAttribute_DeletesResource", "device.DeviceTypeAttribute_GetInfoesResource", "content.ContentCategory_AddsResource",
//            "restUtil.News_DeletesResource", "restUtil.GetAllNewssResource", "restUtil.News_AddsResource", "restUtil.News_UpdatesResource", "restUtil.Country_AddsResource", "restUtil.Country_UpdatesResource", "restUtil.Country_DeletesResource", "restUtil.City_AddsResource", "restUtil.City_UpdatesResource", "restUtil.City_DeletesResource",
//            "network.Network_AddsResource", "network.Network_UpdatesResource", "network.Network_DeletesResource", "content.ContentCategoryAttribute_AddsResource", "content.ContentCategoryAttribute_DeletesResource", "device.DeviceMessage_DeletesResource", "device.GetAllDeviceMessagessResource", "device.DeviceMessages_GetsResource", "device.DeviceMessage_SendsResource", "device.DeviceHistory_GetsResource",
//            "device.GetDeviceTypesInGroupssResource", "restUtil.GetAllStatessResource", "device.Device_AddsResource", "content.ContentAttribute_UpdatesResource", "content.ContentAttribute_AddsResource", "content.ContentAttribute_DeletesResource", "device.DeviceLocation_AddsResource", "device.Device_UpdatesResource", "device.DeviceLocation_UpdatesResource",
//            "content.FilteredContentCategoriessResource","group.FilteredGroupssResource","group.AddFavoriteGroupssResource","content.GetContentItemByItemIDsResource","content.GetContentItemByCateagoryIDsResource","group.FavoriteGroups_DeletesResource",
//            "device.GetDeviceTypesInGroupssResource", "restUtil.GetAllStatessResource", "device.Device_AddsResource", "content.ContentAttribute_UpdatesResource", "content.ContentAttribute_AddsResource", "content.ContentAttribute_DeletesResource", "device.DeviceLocation_AddsResource", "device.Device_UpdatesResource", "device.DeviceLocation_UpdatesResource","content.ContentCategories_GetsResource",
//
//            "content.FavouriteContents_AddsResource", "device.FavoriteDevices_AddsResource","device.FavoriteDevices_DeletesResource","device.GetFavoriteDevicessResource","user.GetUserLoggedInKeyssResource","user.GetUsageRecordsByUserAliasesResource","user.User_RegistersResource","user.User_UpdateUserKeyDetailssResource","user.InsertUserAddressesResource"};
//        System.gc();
        String[] pkgs = {"se.info24.util",
            "se.info24.application",
            "se.info24.device",
            "se.info24.user",
            "se.info24.content",
            "se.info24.restUtil",
            "se.info24.service",
            "se.info24.group",
            "se.info24.network",
            "se.info24.passwords",
            "se.info24.permission",
            "se.info24.priceplans",
            "se.info24.products",
            "se.info24.roles",
            "se.info24.reports",
            "se.info24.chargePoint",
            "se.info24.objectcomments",
            "se.info24.measurementTypes"};

        Class[] classes = getClasses(pkgs);

        try {
            APIDocMessage message = new APIDocMessage();
            message.setSourceName("Info24");
            message.setSourceURL("http://www.info24.se");

            APIDescription desc = new APIDescription();
            desc.getAPIInfo().add("REST API");

            APIErrorCodes err_codes = new APIErrorCodes();
            ErrorCode ec = new ErrorCode();
            ec.setDescription("This means something is wrong");
            ec.setValue(-1);
            err_codes.getErrorCode().add(ec);
            ec = new ErrorCode();
            ec.setDescription("All is OK");
            ec.setValue(0);
            err_codes.getErrorCode().add(ec);
            message.setAPIErrorCodes(err_codes);

            Methods mthds = new Methods();
            java.lang.reflect.Method[] me = null;
            Method m = null;
            MethodParameters mp = null;
            Parameter param = null;
            MethodFormats formats = null;
            MethodHTTPRequests req = null;
            MethodSupportedInAPIVersions ver = null;

            for (Class className : classes) {
                aClass = Class.forName(className.getName());
                me = aClass.getMethods();

                for (java.lang.reflect.Method mthd : me) {
                    for (Annotation annotation : mthd.getAnnotations()) {
                        if (annotation instanceof RESTDoc) {
                            RESTDoc doc = (RESTDoc) annotation;
                            m = new Method();
                            m.setMethodName(doc.methodName());
                            m.setMethodDescription(doc.desc());

                            mp = new MethodParameters();

                            temp = doc.req_Params();
                            for (String p : temp) {
                                param = new Parameter();
                                param.setValue(p.substring(0, p.indexOf(";")));
                                param.setDataType(p.substring(p.indexOf(";") + 1));
                                param.setRequired(1);
                                mp.getParameter().add(param);
                            }

                            temp = doc.opt_Params();
                            for (String p : temp) {
                                param = new Parameter();
                                param.setValue(p.substring(0, p.indexOf(";")));
                                param.setDataType(p.substring(p.indexOf(";") + 1));
                                param.setRequired(0);
                                mp.getParameter().add(param);
                            }
                            m.setMethodParameters(mp);

                            formats = new MethodFormats();
                            temp = doc.method_formats();
                            for (String format : temp) {
                                formats.getFormat().add(format);
                            }

                            m.setMethodFormats(formats);

                            req = new MethodHTTPRequests();
                            temp = doc.method_requests();

                            for (String request : temp) {
                                req.getHTTPRequest().add(request);
                            }

                            m.setMethodHTTPRequests(req);

                            ver = new MethodSupportedInAPIVersions();
                            temp = doc.versions();

                            for (String version : temp) {
                                ver.getAPIVersion().add(Integer.valueOf(version));
                            }

                            m.setMethodSupportedInAPIVersions(ver);

                            mthds.getMethod().add(m);

                        }
                    }
                }


            }
            message.setMethods(mthds);
            str = JAXBManager.getInstance().marshall(message);
        } catch (ClassNotFoundException ex) {
            exceptionLog("TCMUtil", ex);
        } catch (JAXBException ex) {
            exceptionLog("TCMUtil", ex);
        } finally {
            logger.info(" [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]");
            System.gc();
        }
        return str;
    }

    public static Hashtable<String, ArrayList> getFunctionAreas() {
        return functionAreas;
    }

    public static void setFunctionAreas(Hashtable<String, ArrayList> functionAreas) {
        TCMUtil.functionAreas = functionAreas;
    }

    private static Class[] getClasses(String[] packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        List<File> dirs = new ArrayList<File>();
        for (String pkg : packageName) {
            String path = pkg.replace('.', '/');
            Enumeration<URL> resources = classLoader.getResources(path);

            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                dirs.add(new File(resource.getFile().replace("%20", " ")));
            }
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        int i = 0;
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName[i]));
            i++;
        }
        return classes.toArray(new Class[classes.size()]);
    }

    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith("Resource.class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

    public static String convertHexToString(String hex) {
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for (int i = 0; i < hex.length() - 1; i += 2) {
            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            if (output.equalsIgnoreCase("00")) {
                continue;
            }
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char) decimal);
            temp.append(decimal);
        }
        return sb.toString();
    }

    public static String convertStringToHex(String str) {
        char[] chars = str.toCharArray();
        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            hex.append(Integer.toHexString(0xFF & chars[i]));
        }
        return hex.toString();
    }

    public String SHA1checksumCalculation(byte[] byt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.update(byt, 0, byt.length);
        byte[] mdbytes = md.digest();
        return new BigInteger(1, mdbytes).toString(16);
    }

    public String MD5checksumCalculation(byte[] byt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(byt, 0, byt.length);
        byte[] mdbytes = md.digest();
        return new BigInteger(1, mdbytes).toString(16);
    }

    public static <T> void exceptionLog(String className, Exception e) {
        Logger logger = Logger.getLogger(className);
        StringWriter stack = new StringWriter();
        e.printStackTrace(new PrintWriter(stack));
        logger.error(stack.toString());
//        logger.shutdown();
    }

    public static <T> void loger(String className, String Message, String type) {
        Logger logger = Logger.getLogger(className);
        if (type.equalsIgnoreCase("Error")) {
            logger.error(Message);
        } else if (type.equalsIgnoreCase("Info")) {
            logger.info(Message);
        }
//        logger.shutdown();
    }

    public static void saveLog(Session session, String requestIP, String userID, String action, String actionValue1, String tableName, String result) {
        GregorianCalendar gc = new GregorianCalendar();
        UserDAO dao = new UserDAO();
        UserLog log = new UserLog();
        log.setCreatedDate(gc.getTime());
        log.setAction(action);
        log.setResults(result);
        log.setActionValue1(actionValue1);
        log.setTableName(tableName);
        log.setRequestIp(requestIP);
        log.setUpdatedDate(gc.getTime());
        log.setUserId(userID);
        dao.saveUserLog(log, session);
    }

    public static boolean saveUserLog(String userID, String action, String tableName, String result, String actionvalue1, String actionvalue2, String host) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        UserLog userLog = new UserLog();
        userLog.setUserId(userID);
        userLog.setAction(action);
        userLog.setTableName(tableName);
        userLog.setResults(result);
        userLog.setActionValue1(actionvalue1);
        userLog.setActionValue2(actionvalue2);
        userLog.setRequestIp(host);
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        userLog.setCreatedDate(gc.getTime());
        userLog.setUpdatedDate(gc.getTime());
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(userLog);
            tx.commit();
            return true;
        } catch (Exception he) {
//            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    public static String getGroupTrust(String groupID) {
        return " select groupId from ISM.dbo.GroupTrusts where iTrustGroupId = '" + groupID + "' union select GroupID from ISM.dbo.Groups where GroupID= '" + groupID + "' ";
    }

    public static <T extends Object> List<List<T>> split(List<T> list, int targetSize) {
        List<List<T>> lists = new ArrayList<List<T>>();
        for (int i = 0; i < list.size(); i += targetSize) {
            lists.add(list.subList(i, Math.min(i + targetSize, list.size())));
        }
        return lists;
    }

    public static <T extends String> List<List<T>> splitStringList(List<T> arrayList, int targetSize) {
        List<List<T>> lists = new ArrayList<List<T>>();
        for (int start = 0; start < arrayList.size(); start += targetSize) {
            int end = Math.min(start + 2000, arrayList.size());
            lists.add(arrayList.subList(start, end));
        }
        return lists;
    }

    public static boolean isValidUUID(String uuid) {
        if (uuid == null) {
            return false;
        }
        try {
            UUID fromStringUUID = UUID.fromString(uuid);
            String toStringUUID = fromStringUUID.toString();
            return toStringUUID.equalsIgnoreCase(uuid);
        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
            return false;
        }
    }
}

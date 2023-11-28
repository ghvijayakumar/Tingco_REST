/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.device;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.TimeZone;
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
import org.hibernate.Transaction;
import se.info24.devicejaxb.TingcoDevice;
import se.info24.devicejaxbPost.Device;
import se.info24.ismOperationsPojo.TransactionProducts;
import se.info24.ismOperationsPojo.TransactionResult;
import se.info24.jaxbUtil.TingcoDeviceXML;
import se.info24.pojo.RecurrenceTypes;
import se.info24.pojo.UserLog;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.UserTimeZones2;
import se.info24.restUtil.RestUtilDAO;
import se.info24.user.User_LoginsResource;
import se.info24.util.HibernateUtil;
import se.info24.util.JAXBManager;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Vijayakumar
 */
@Path("/devicepurchasetransactions")
public class DevicePurchaseTransactionsResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
    DeviceDAO deviceDAO = new DeviceDAO();
    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
//    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of DevicePurchaseTransactionsResource */
    public DevicePurchaseTransactionsResource() {
    }

    /**
     * Retrieves representation of an instance of se.info24.device.DevicePurchaseTransactionsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * DeleteTransactionForDevice
     * @param transactionId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deletetransactionfordevice/rest/{rest}/transactionid/{transactionid}")
    @RESTDoc(methodName = "DeleteTransactionForDevice", desc = "Used to Delete TransactionForDevice", req_Params = {"TransactionID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_deleteTransactionForDevice(@PathParam("transactionid") String transactionId) {
        return deleteTransactionForDevice(transactionId);
    }

    /**
     * DeleteTransactionForDevice
     * @param transactionId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deletetransactionfordevice/json/{json}/transactionid/{transactionid}")
    public TingcoDevice getJson_deleteTransactionForDevice(@PathParam("transactionid") String transactionId) {
        return deleteTransactionForDevice(transactionId);
    }

    /**
     * DeleteTransactionProductForDevice
     * @param transactionId
     * @param productVariantId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deletetransactionproductfordevice/rest/{rest}/transactionid/{transactionid}/productvariantid/{productvariantid}")
    @RESTDoc(methodName = "DeleteTransactionProductForDevice", desc = "Used to Delete TransactionProductForDevice", req_Params = {"TransactionID;UUID", "ProductVariantID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_deleteTransactionProductForDevice(@PathParam("transactionid") String transactionId, @PathParam("productvariantid") String productVariantId) {
        return deleteTransactionProductForDevice(transactionId, productVariantId);
    }

    /**
     * DeleteTransactionProductForDevice
     * @param transactionId
     * @param productVariantId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deletetransactionproductfordevice/json/{json}/transactionid/{transactionid}/productvariantid/{productvariantid}")
    public TingcoDevice getJson_deleteTransactionProductForDevice(@PathParam("transactionid") String transactionId, @PathParam("productvariantid") String productVariantId) {
        return deleteTransactionProductForDevice(transactionId, productVariantId);
    }

    /**
     * GetRecurrenceTypes
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getrecurrencetypes/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetRecurrenceTypes", desc = "Used to Get RecurrenceTypes", req_Params = {"CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_getRecurrenceTypes(@PathParam("countryid") int countryId) {
        return getRecurrenceTypes(countryId);
    }

    /**
     * GetRecurrenceTypes
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getrecurrencetypes/json/{json}/countryid/{countryid}")
    public TingcoDevice getJson_getRecurrenceTypes(@PathParam("countryid") int countryId) {
        return getRecurrenceTypes(countryId);
    }

    /**
     * GetPurchaseTransactions
     * @param content
     * @return
     *
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getpurchasetransactions/rest/{rest}{maxresult:(/maxresult/[^/]+?)?}")
    @RESTDoc(methodName = "GetPurchaseTransactions", desc = "Used to Get PurchaseTransactions", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_getPurchaseTransactions(String content, @PathParam("maxresult") String maxresult) {
        return getPurchaseTransactions(content, maxresult);
    }

    /**
     * GetPurchaseTransactions
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getpurchasetransactions/json/{json}{maxresult:(/maxresult/[^/]+?)?}")
    public TingcoDevice postJson_getPurchaseTransactions(String content, @PathParam("maxresult") String maxresult) {
        return getPurchaseTransactions(content, maxresult);
    }

    /**
     * GetSalesTransationReport
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getsalestransactionreport/rest/{rest}")
    @RESTDoc(methodName = "GetSalesTransationReport", desc = "Used to Get SalesTransation Report", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_getSalesTransationReport(String content) {
        return getSalesTransactionReport(content);
    }

    /**
     * GetSalesTransationReport
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getsalestransactionreport/json/{json}")
    public TingcoDevice postJson_getSalesTransationReport(String content) {
        return getSalesTransactionReport(content);
    }

    /**
     * POST method for creating an instance of DevicePurchaseTransactionsResource
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
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public DevicePurchaseTransactionResource getDevicePurchaseTransactionResource() {
        return new DevicePurchaseTransactionResource();
    }

    private TingcoDevice deleteTransactionForDevice(String transactionId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session ismOperationsSession = null;
        Session session = null;
        TingcoDevice tingcoDevice = null;
        Transaction tx = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (transactionId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("transactionId should not be empty");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.TRANSACTIONS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.TRANSACTIONS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    ismOperationsSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = ismOperationsSession.beginTransaction();
                    TransactionResult transactionResult = deviceDAO.getTransactionResult(transactionId, ismOperationsSession);
//                    List<TransactionResult> transactionResultList = deviceDAO.getTransactionResultbyParentId(transactionId, ismOperationsSession);
                    List<TransactionProducts> transactionProductsList = deviceDAO.getTransactionProducts(transactionId, ismOperationsSession);
                    if (transactionResult != null) {
                        if (!deviceDAO.deleteTransactionResultAndTransactionProducts(transactionResult, transactionProductsList, ismOperationsSession)) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error Occured while deleting TransactionResult");
                            return tingcoDevice;
                        }
                    }
                    UserLog userLog = new UserLog();
                    userLog.setUserId(sessions2.getUserId());
                    userLog.setAction("Delete TransactionResult, TransactionProducts");
                    userLog.setTableName("TransactionResult;TransactionProducts");
                    userLog.setRequestIp(request.getLocalAddr());
                    userLog.setCreatedDate(gc.getTime());
                    userLog.setUpdatedDate(gc.getTime());
                    session.saveOrUpdate(userLog);
                    session.flush();
                    session.clear();

                    tx.commit();
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
            if (tx.wasCommitted()) {
                tx.rollback();
            }
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            if (ismOperationsSession != null) {
                ismOperationsSession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice deleteTransactionProductForDevice(String transactionId, String productVariantId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        Session ismOperationsSession = null;
        TingcoDevice tingcoDevice = null;
        Transaction tx = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (transactionId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("transactionId should not be empty");
                    return tingcoDevice;
                }
                if (productVariantId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("productVariantId should not be empty");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.TRANSACTIONS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.TRANSACTIONS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ismOperationsSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    tx = ismOperationsSession.beginTransaction();
                    TransactionProducts transactionProducts = deviceDAO.getTransactionProductsByIds(transactionId, productVariantId, ismOperationsSession);
                    if (transactionProducts != null) {
                        if (!deviceDAO.deleteTransactionProducts(transactionProducts, ismOperationsSession)) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error Occured while deleting TransactionProducts");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No TransactionProducts found to delete");
                        return tingcoDevice;
                    }
                    UserLog userLog = new UserLog();
                    userLog.setUserId(sessions2.getUserId());
                    userLog.setAction("Delete TransactionProducts");
                    userLog.setTableName("TransactionProducts");
                    userLog.setRequestIp(request.getLocalAddr());
                    userLog.setCreatedDate(gc.getTime());
                    userLog.setUpdatedDate(gc.getTime());
                    session.saveOrUpdate(userLog);
                    session.flush();
                    session.clear();

                    tx.commit();
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
            if (tx.wasCommitted()) {
                tx.rollback();
            }
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            if (ismOperationsSession != null) {
                ismOperationsSession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    private TingcoDevice getPurchaseTransactions(String content, String maxresult) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.TRANSACTIONS, TCMUtil.READ);
                if (hasPermission) {
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    if (devices.getDevices().getDevice().size() > 0) {
                        Device deviceXML = devices.getDevices().getDevice().get(0);
                        if (deviceXML != null) {
                            if (!maxresult.equals("")) {
                                maxresult = maxresult.split("/")[2];
                            } else {
                                maxresult = "200";
                            }
                            
                            List<Object> transactionResultList = deviceDAO.getTransactionResultForPurchaseTransaction(deviceXML, tingcoDevice, session, maxresult);
                            if(transactionResultList.size() > Integer.parseInt(maxresult)){
                                    transactionResultList = transactionResultList.subList(0, Integer.parseInt(maxresult));
                                    tingcoDevice.setExceeds200(1);
                            }else{
                                tingcoDevice.setExceeds200(0);
                            }

                            if (!transactionResultList.isEmpty()) {
                                UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                                if (userTimeZones2 != null) {
                                    String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                                    tingcoDevice = tingcoDeviceXML.buildTransactionResult(tingcoDevice, transactionResultList, timeZoneGMToffset, session);
                                } else {
                                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                                    tingcoDevice.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                                    return tingcoDevice;
                                }
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Transaction found for the given input");
                                return tingcoDevice;
                            }
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("No Device found in XML");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Invalid Device XML Found");
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
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }

    private TingcoDevice getRecurrenceTypes(int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        TingcoDevice tingcoDevice = null;
        Transaction tx = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.ORDERS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<String> objectCodeList = new ArrayList<String>();
                    objectCodeList.add("DAILY");
                    objectCodeList.add("WEEKLY");
                    objectCodeList.add("MONTHLY");
                    objectCodeList.add("YEARLY");
                    List<RecurrenceTypes> recurrenceTypesList = deviceDAO.getRecurrenceTypesByObjectCodes(objectCodeList, session);
                    if (!recurrenceTypesList.isEmpty()) {
                        tingcoDevice = tingcoDeviceXML.buildRecurrenceTypes(tingcoDevice, recurrenceTypesList, countryId);
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No data found");
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
            if (tx.wasCommitted()) {
                tx.rollback();
            }
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
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

    private TingcoDevice getSalesTransactionReport(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.TRANSACTIONS, TCMUtil.READ);
                if (hasPermission) {
                    se.info24.devicejaxbPost.TingcoDevice devices = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    if (devices.getDevices().getDevice().size() > 0) {
                        Device deviceXML = devices.getDevices().getDevice().get(0);
                        if (deviceXML != null) {
                            List<Object> transactionResultList = deviceDAO.getSalesTransactionReport(deviceXML, tingcoDevice, session);
                            if (!transactionResultList.isEmpty()) {
                                if (transactionResultList.size() <= 1000) {
                                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(deviceXML.getLastUpdatedByUserID().getValue(), session);
                                    if (userTimeZones2 != null) {
                                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                                        tingcoDevice = tingcoDeviceXML.buildSalesTransationReport(tingcoDevice, transactionResultList, timeZoneGMToffset);
                                    } else {
                                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                                        tingcoDevice.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                                        return tingcoDevice;
                                    }
                                } else {
                                    tingcoDevice.getMsgStatus().setResponseCode(-5);
                                    tingcoDevice.getMsgStatus().setResponseText("This report will result in too much data being sent to your browser, please adjust your filter to reduce the amount of data. Then run the report again.");
                                    return tingcoDevice;
                                }
                            } else {
                                tingcoDevice.getMsgStatus().setResponseCode(-1);
                                tingcoDevice.getMsgStatus().setResponseText("No Transaction found for the given input");
                                return tingcoDevice;
                            }
                        } else {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("No Device found in XML");
                            return tingcoDevice;
                        }
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Invalid Device XML Found");
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
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }
}

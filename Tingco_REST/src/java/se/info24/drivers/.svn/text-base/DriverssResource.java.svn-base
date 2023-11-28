/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.drivers;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
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
import javax.xml.datatype.DatatypeFactory;
import org.hibernate.Session;
import se.info24.devicejaxb.TingcoDevice;
import se.info24.driversjaxb.DriverLog;
import se.info24.driversjaxb.DriverLogs;
import se.info24.driversjaxb.JourneyTypeTranslation;
import se.info24.driversjaxb.TingcoDrivers;
import se.info24.ismOperationsPojo.DriversLogItemHistory;
import se.info24.ismOperationsPojo.DriversLogItems;
import se.info24.jaxbUtil.TingcoDeviceXML;
import se.info24.jaxbUtil.TingcoDriversXML;
import se.info24.pojo.DeviceUsers;
import se.info24.pojo.JourneyTypeTranslations;
import se.info24.util.HibernateUtil;
import se.info24.util.JAXBManager;

import se.info24.pojo.Device;
import se.info24.pojo.UserSessions2;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/drivers")
public class DriverssResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    private DriversDAO driverManager = new DriversDAO();
    TingcoDriversXML driverXml = new TingcoDriversXML();
//    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of DriverssResource */
    public DriverssResource() {
    }

    /**
     * GetDriversLogByUserId
     * @param userId
     * @param deviceId
     * @param countryId
     * @return
     */
    @GET
    @Path("/getdriverslog/rest/{rest}/userid/{userid}/deviceid/{deviceid}/countryid/{countryid}{driverslogitemid:(/driverslogitemid/[^/]+?)?}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetDriversLogByUserId", desc = "Fetch the DriverLog Details fileterd by userid", req_Params = {"UserID;UUID", "DeviceID;UUID", "CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDrivers getXml_DriverLogByUserId(@PathParam("userid") String userId, @PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId, @PathParam("driverslogitemid") String driversLogItemId) throws DatatypeConfigurationException {
        return getDriverLog(userId, deviceId, countryId,driversLogItemId);
    }

    /**
     * GetDriversLogByUserId
     * @param userId
     * @param deviceId
     * @param countryId
     * @return
     */
    @GET
    @Path("/getdriverslog/json/{json}/userid/{userid}/deviceid/{deviceid}/countryid/{countryid}{driverslogitemid:(/driverslogitemid/[^/]+?)?}")
    @Produces("application/json")
    public TingcoDrivers getJson_DriverLogByUserId(@PathParam("userid") String userId, @PathParam("deviceid") String deviceId, @PathParam("countryid") int countryId, @PathParam("driverslogitemid") String driversLogItemId) throws DatatypeConfigurationException {
        return getDriverLog(userId, deviceId, countryId,driversLogItemId);
    }

    /**
     * GetVehicleList
     * @param userId
     * @return
     */
    @GET
    @Path("/getvehiclelist/rest/{rest}/userid/{userid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetVehicleList", desc = "Fetch the Device Details fileterd by userid", req_Params = {"UserID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_VehicleList(@PathParam("userid") String userId) throws DatatypeConfigurationException {
        return getVehicleList(userId);
    }

    /**
     * GetVehicleList
     * @param userId
     * @return
     */
    @GET
    @Path("/getvehiclelist/json/{json}/userid/{userid}")
    @Produces("application/json")
    public TingcoDevice getJson_VehicleList(@PathParam("userid") String userId) throws DatatypeConfigurationException {
        return getVehicleList(userId);
    }

    /**
     * GetJourneyTypes
     * @param groupId
     * @param countryId
     * @return
     */
    @GET
    @Path("/getjourneytypes/rest/{rest}/groupid/{groupid}/countryid/{countryid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetJourneyTypes", desc = "Get all JourneyTypes filetered by GroupID and CountryID", req_Params = {"GroupID;UUID", "CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDrivers getXml_JourneyTypes(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getJourneyTypes(groupId, countryId);
    }

    /**
     * GetJourneyTypes
     * @param groupId
     * @param countryId
     * @return
     */
    @GET
    @Path("/getjourneytypes/json/{json}/groupid/{groupid}/countryid/{countryid}")
    @Produces("application/json")
    public TingcoDrivers getJson_JourneyTypes(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getJourneyTypes(groupId, countryId);
    }

    /**
     * GetJourneyHistoryDetails
     * @param deviceId
     * @param fromDate
     * @param toDate
     * @param countryId
     * @param userId
     * @param filterFlag
     * @param journeyTypeId
     * @param journeyPurpose
     * @return
     */
    @GET
    @Path("/getjourneyhistory/rest/{rest}/deviceid/{deviceid}/fromdate/{fromdate}/todate/{todate}/filterflag/{filterflag}/countryid/{countryid}/userid/{userid}{journeytypeid:(/journeytypeid/[^/]+?)?}{journeypurpose:(/journeypurpose/[^/]+?)?}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetJourneyHistoryDetails", desc = "Get all JourneyHistoryDetails ", req_Params = {"DeviceID;UUID", "FromDate;String", "ToDate;String", "FilterFlag;String", "CountryID;int", "UserID;UUID"}, opt_Params = {"JourneyTypeID;UUID", "JourneyTypePurpose;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDrivers getXml_JourneyHistoryDetails(@PathParam("deviceid") String deviceId, @PathParam("fromdate") String fromDate, @PathParam("todate") String toDate, @PathParam("countryid") int countryId,
            @PathParam("userid") String userId, @PathParam("filterflag") String filterFlag, @PathParam("journeytypeid") String journeyTypeId, @PathParam("journeypurpose") String journeyPurpose) throws DatatypeConfigurationException {
        return getJourneyHistoryDetails(deviceId, fromDate, toDate, countryId, userId, filterFlag, journeyTypeId, journeyPurpose);
    }

    /**
     * GetJourneyHistoryDetails
     * @param deviceId
     * @param fromDate
     * @param toDate
     * @param countryId
     * @param userId
     * @param filterFlag
     * @param journeyTypeId
     * @param journeyPurpose
     * @return
     */
    @GET
    @Path("/getjourneyhistory/json/{json}/deviceid/{deviceid}/fromdate/{fromdate}/todate/{todate}/filterflag/{filterflag}/countryid/{countryid}/userid/{userid}{journeytypeid:(/journeytypeid/[^/]+?)?}{journeypurpose:(/journeypurpose/[^/]+?)?}")
    @Produces("application/json")
    public TingcoDrivers getJson_JourneyHistoryDetails(@PathParam("deviceid") String deviceId, @PathParam("fromdate") String fromDate, @PathParam("todate") String toDate, @PathParam("countryid") int countryId,
            @PathParam("userid") String userId, @PathParam("filterflag") String filterFlag, @PathParam("journeytypeid") String journeyTypeId, @PathParam("journeypurpose") String journeyPurpose) throws DatatypeConfigurationException {
        return getJourneyHistoryDetails(deviceId, fromDate, toDate, countryId, userId, filterFlag, journeyTypeId, journeyPurpose);
    }

    /**
     * UpdateDriversLog
     * @param content
     * @return
     */
    @POST
    @Path("/updatedriverslog/rest/{rest}")
    @Consumes("application/xml")
    @Produces("application/xml")
    @RESTDoc(methodName = "UpdateDriversLog", desc = "Update Changes in DriversLogItems", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDrivers getXml_Update(String content) throws DatatypeConfigurationException {
        TingcoDrivers tinngcoDrivers = (TingcoDrivers) JAXBManager.getInstance().unMarshall(content, TingcoDrivers.class);
        return updateDriverLogs(tinngcoDrivers);
    }

    /**
     * UpdateDriversLog
     * @param content
     * @return
     */
    @POST
    @Path("/updatedriverslog/json/{json}")
    @Consumes("application/xml")
    @Produces("application/json")
    public TingcoDrivers getJson_Update(String content) throws DatatypeConfigurationException {
        TingcoDrivers tinngcoDrivers = (TingcoDrivers) JAXBManager.getInstance().unMarshall(content, TingcoDrivers.class);
        return updateDriverLogs(tinngcoDrivers);
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public DriversResource getDriversResource() {
        return new DriversResource();
    }

    private TingcoDrivers getJourneyHistoryDetails(String deviceId, String fromDate, String toDate, int countryId, String userId, String filterFlag, String journeyTypeId, String journeyPurpose) throws DatatypeConfigurationException {
        long requestedTime = System.currentTimeMillis();
        TingcoDrivers tingcoDrivers = new TingcoDrivers();
        Session session = HibernateUtil.getISMOperationsSessionFactory().openSession();
        Session ismSession = HibernateUtil.getSessionFactory().openSession();
        try {
            tingcoDrivers = driverXml.buildTingcoDriverTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceId == null) {
                    tingcoDrivers.getMsgStatus().setResponseCode(-1);
                    tingcoDrivers.getMsgStatus().setResponseText("Device ID should not be empty");
                    return tingcoDrivers;
                }
                if (fromDate == null) {
                    tingcoDrivers.getMsgStatus().setResponseCode(-1);
                    tingcoDrivers.getMsgStatus().setResponseText("FromDate should not be empty");
                    return tingcoDrivers;
                }
                if (toDate == null) {
                    tingcoDrivers.getMsgStatus().setResponseCode(-1);
                    tingcoDrivers.getMsgStatus().setResponseText("ToDate should not be empty");
                    return tingcoDrivers;
                }
                if (countryId <= 0) {
                    tingcoDrivers.getMsgStatus().setResponseCode(-1);
                    tingcoDrivers.getMsgStatus().setResponseText("Country ID should not be empty");
                    return tingcoDrivers;
                }
                if (userId == null) {
                    tingcoDrivers.getMsgStatus().setResponseCode(-1);
                    tingcoDrivers.getMsgStatus().setResponseText("User ID should not be empty");
                    return tingcoDrivers;
                }
                if (filterFlag == null) {
                    tingcoDrivers.getMsgStatus().setResponseCode(-1);
                    tingcoDrivers.getMsgStatus().setResponseText("Filter Flag should not be empty");
                    return tingcoDrivers;
                }

                if (journeyPurpose.equals("")) {
                    journeyPurpose = null;
                } else {
                    journeyPurpose = journeyPurpose.split("/")[2];
                }

                if (journeyTypeId.equals("")) {
                    journeyTypeId = null;
                } else {
                    journeyTypeId = journeyTypeId.split("/")[2];
                }

                List<DriversLogItemHistory> driverLogHis = driverManager.getJourneyHistoryDetails(session, deviceId, fromDate, toDate, countryId, userId, filterFlag, journeyTypeId, journeyPurpose);
                if (driverLogHis.isEmpty()) {
                    tingcoDrivers.getMsgStatus().setResponseCode(-1);
                    tingcoDrivers.getMsgStatus().setResponseText("Data Not Found");
                }
                DriverLogs driverLogs = new DriverLogs();

                int i = 0;
                GregorianCalendar gc = new GregorianCalendar();
                SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                for (DriversLogItemHistory dlh : driverLogHis) {
                    DriverLog driverLog = new DriverLog();
                    driverLog.setSeqNo(++i);
                    driverLog.setDriversLogItemID(dlh.getDriversLogItemId());
                    gc.setTime(dlh.getCreatedDate());
                    driverLog.setCreatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    driverLog.setCreatedDateTCMV3(lFormat.format(gc.getTime()));
                    driverLog.setStartOdometer(dlh.getStartOdometer());
                    driverLog.setStopOdometer(dlh.getStopOdometer());
                    driverLog.setTotalDistance(dlh.getTotalDistance().floatValue());
                    driverLog.setJourneyTypeID(dlh.getJourneyTypeId());
                    driverLog.setJourneyAccount(dlh.getJourneyAccount());
                    driverLog.setJourneyPurpose(dlh.getJourneyPurpose());
                    driverLog.setStartAddress(dlh.getStartAddress());
                    driverLog.setStopAddress(dlh.getStopAddress());
                    gc.setTime(dlh.getExportedDate());
                    driverLog.setExportedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    driverLog.setExportedDateTCMV3(lFormat.format(gc.getTime()));
                    List<JourneyTypeTranslations> jtt = ismSession.getNamedQuery("getJourneyTypeTranslationsByJourneyTypeID").setString("journeyTypeId", dlh.getJourneyTypeId()).setInteger("countryId", countryId).list();
                    if (!jtt.isEmpty()) {
                        driverLog.setJourneyTypeName(jtt.get(0).getJourneyTypeName());
                    }
                    driverLogs.getDriverLog().add(driverLog);
                }
                tingcoDrivers.setDriverLogs(driverLogs);
            } else {
                tingcoDrivers.getMsgStatus().setResponseCode(-3);
                tingcoDrivers.getMsgStatus().setResponseText("User Session is Not Valid");
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDrivers.getMsgStatus().setResponseCode(-1);
            tingcoDrivers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDrivers;
    }

    private TingcoDrivers getJourneyTypes(String groupId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        TingcoDrivers tingcoDrivers = new TingcoDrivers();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tingcoDrivers = driverXml.buildTingcoDriverTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (groupId == null) {
                    tingcoDrivers.getMsgStatus().setResponseCode(-1);
                    tingcoDrivers.getMsgStatus().setResponseText("Group ID should not be empty");
                    return tingcoDrivers;
                }
                if (countryId <= 0) {
                    tingcoDrivers.getMsgStatus().setResponseCode(-1);
                    tingcoDrivers.getMsgStatus().setResponseText("Country ID should not be empty");
                    return tingcoDrivers;
                }
                List<JourneyTypeTranslations> jouTypeTrans = driverManager.getJourneyTypeTranslations(session, groupId, countryId);
                int i = 0;
                for (JourneyTypeTranslations jtt : jouTypeTrans) {
                    se.info24.driversjaxb.JourneyTypeTranslations jourTypTrns = new se.info24.driversjaxb.JourneyTypeTranslations();
                    JourneyTypeTranslation jourTypTrn = new JourneyTypeTranslation();
                    jourTypTrn.setSeqNo(++i);
                    jourTypTrn.setJourneyTypeID(jtt.getId().getJourneyTypeId());
                    jourTypTrn.setJourneyTypeName(jtt.getJourneyTypeName());
                    jourTypTrns.getJourneyTypeTranslation().add(jourTypTrn);
                    tingcoDrivers.setJourneyTypeTranslations(jourTypTrns);
                }

            } else {
                tingcoDrivers.getMsgStatus().setResponseCode(-3);
                tingcoDrivers.getMsgStatus().setResponseText("User Session is Not Valid");
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDrivers.getMsgStatus().setResponseCode(-1);
            tingcoDrivers.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDrivers;
    }

    public TingcoDrivers updateDriverLogs(TingcoDrivers input) throws DatatypeConfigurationException {
        long requestedTime = System.currentTimeMillis();
        TingcoDrivers tingcoDrivers = null;
        TingcoDriversXML driversXml = new TingcoDriversXML();
        tingcoDrivers = driversXml.buildTingcoDriverTemplate();
        Session session = null;
        try {
            if (request.getAttribute("USERSESSION") != null) {
                session = HibernateUtil.getISMOperationsSessionFactory().openSession();
                DriversDAO manager = new DriversDAO();
                UserSessions2 sess = (UserSessions2) request.getAttribute("USERSESSION");
                List<DriverLog> driverLogList = input.getDriverLogs().getDriverLog();
                boolean updated = false;
                boolean updateExported = false;
                GregorianCalendar gc = new GregorianCalendar();
                for (DriverLog log : driverLogList) {
                    //load DriverLogItems
                    DriversLogItems logItem = manager.gerLogItemsByDriversLogItemId(session, log.getDriversLogItemID());
                    if (logItem != null) {
                        if (log.getStartAddress() != null) {
                            logItem.setStartAddress(log.getStartAddress());
                            updateExported = true;
                        }
                        if (log.getStopAddress() != null) {
                            logItem.setStopAddress(log.getStopAddress());
                            updateExported = true;
                        }
                        if (log.getTotalDistance() != 0.0) {
                            logItem.setTotalDistance(new BigDecimal(log.getTotalDistance()));
                            updateExported = true;
                        }
                        if (log.getJourneyTypeID() != null) {
                            logItem.setJourneyTypeId(log.getJourneyTypeID());
                            updateExported = true;
                        }
                        if (log.getJourneyAccount() != null) {
                            logItem.setJourneyAccount(log.getJourneyAccount());
                            updateExported = true;
                        }
                        if (log.getJourneyPurpose() != null) {
                            logItem.setJourneyPurpose(log.getJourneyPurpose());
                            updateExported = true;
                        }

                        if (!updateExported) {
                            logItem.setIsExported(1);
                        }
                        logItem.setExportedDate(gc.getTime());
                        logItem.setLastUpdatedByUserId(sess.getUserId());
                        logItem.setUpdatedDate(gc.getTime());

                        updated = manager.updateDriverslog(session, logItem);
                    }
                }
                if (updated) {
                    tingcoDrivers.getMsgStatus().setResponseCode(0);
                    tingcoDrivers.getMsgStatus().setResponseText("OK");

                } else {
                    tingcoDrivers.getMsgStatus().setResponseCode(-1);
                    tingcoDrivers.getMsgStatus().setResponseText("Error occurred while updating data");
                    return tingcoDrivers;

                }
            } else {
                tingcoDrivers.getMsgStatus().setResponseCode(-3);
                tingcoDrivers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoDrivers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDrivers.getMsgStatus().setResponseCode(-1);
            tingcoDrivers.getMsgStatus().setResponseText("Error occurred while updating data");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDrivers;
    }

    public TingcoDrivers getDriverLog(String UserID, String deviceId, int countryId,String driversLogItemId) throws DatatypeConfigurationException {
        long requestedTime = System.currentTimeMillis();
        TingcoDrivers tingcodriver = new TingcoDrivers();
        TingcoDriversXML driversXml = new TingcoDriversXML();
        tingcodriver = driversXml.buildTingcoDriverTemplate();
        Session sessionTest = null;
        Session sessionIsmOperation = null;
        try {
            if (request.getAttribute("USERSESSION") != null) {
                DriversDAO manager = new DriversDAO();
                sessionTest = HibernateUtil.getSessionFactory().openSession();
                sessionIsmOperation = HibernateUtil.getISMOperationsSessionFactory().openSession();
                if (!driversLogItemId.equals("")) {
                    driversLogItemId = driversLogItemId.split("/")[2];
                } else {
                    driversLogItemId = null;
                }

                List<DriversLogItems> driversLogItemsList = new ArrayList<DriversLogItems>();
                if(driversLogItemId != null){
                    driversLogItemsList = manager.getDriverLogByUserIDandId(sessionIsmOperation, UserID, deviceId, driversLogItemId);
                }else{
                     driversLogItemsList = manager.getDriverLog(sessionIsmOperation, UserID, deviceId);
                }

               
                List<String> JourneyTypeId = new ArrayList<String>();
                if (driversLogItemsList.size() > 0) {

                    for (DriversLogItems driversLogItems : driversLogItemsList) {
                        JourneyTypeId.add(driversLogItems.getJourneyTypeId());
                    }
                    List<JourneyTypeTranslations> journeyTypeTranslationses = manager.getJourneyTypeTranslationsByJourneyTypeIDs(sessionTest, countryId, JourneyTypeId);
                    tingcodriver = driversXml.buildDriverLogByUserId(tingcodriver, journeyTypeTranslationses,driversLogItemsList);
                } else {
                    tingcodriver.getMsgStatus().setResponseCode(-1);
                    tingcodriver.getMsgStatus().setResponseText("Data Not Available");
                    return tingcodriver;
                }

            } else {
                tingcodriver.getMsgStatus().setResponseCode(-1);
                tingcodriver.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcodriver;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcodriver.getMsgStatus().setResponseCode(-1);
            tingcodriver.getMsgStatus().setResponseText("Error occurred while updating data");
        } finally {
            if (sessionTest != null) {
                sessionTest.close();
            }
            if (sessionIsmOperation != null) {
                sessionIsmOperation.close();
            }
            delayLog(requestedTime);
        }
        return tingcodriver;
    }

    public TingcoDevice getVehicleList(String userId) throws DatatypeConfigurationException {
        long requestedTime = System.currentTimeMillis();
        TingcoDevice tingcodriver = new TingcoDevice();
        TingcoDeviceXML deviceXml = new TingcoDeviceXML();
        tingcodriver = deviceXml.buildTingcoDeviceTemplate();
        Session sessionTest = null;
        try {
            if (request.getAttribute("USERSESSION") != null) {
                DriversDAO manager = new DriversDAO();
                sessionTest = HibernateUtil.getSessionFactory().openSession();
                List<DeviceUsers> deviceUsersList = manager.getDeviceidByUserId(sessionTest, userId);
                List<Device> devilist = new ArrayList<Device>();
                if (deviceUsersList.size() > 0) {
                    for (DeviceUsers deviceUsers : deviceUsersList) {
//                        Device device = manager.getdeviceNameByDeviceId(sessionTest, deviceUsers.getDeviceId(), userId);
                        Device device = (Device) sessionTest.get(Device.class, deviceUsers.getDeviceId());
                        if (device != null) {
                            devilist.add(device);

                        }
                    }
                    if (devilist.isEmpty()) {
                        tingcodriver.getMsgStatus().setResponseCode(-1);
                        tingcodriver.getMsgStatus().setResponseText("Data Not Found");
                        return tingcodriver;
                    }
                    tingcodriver = deviceXml.buildVehicleList(tingcodriver, devilist);
                } else {
                    tingcodriver.getMsgStatus().setResponseCode(-1);
                    tingcodriver.getMsgStatus().setResponseText("User Not Exist");
                    return tingcodriver;
                }
            } else {
                tingcodriver.getMsgStatus().setResponseCode(-1);
                tingcodriver.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcodriver;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcodriver.getMsgStatus().setResponseCode(-1);
            tingcodriver.getMsgStatus().setResponseText("Error occurred");
        } finally {
            if (sessionTest != null) {
                sessionTest.close();
            }
            delayLog(requestedTime);
        }
        return tingcodriver;
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}

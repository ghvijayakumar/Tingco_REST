/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.jaxbUtil;

import com.sun.org.apache.xerces.internal.parsers.IntegratedParserConfiguration;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import org.hibernate.Session;
import se.info24.devicejaxb.Devices;
import se.info24.devicejaxb.TingcoDevice;
import se.info24.devicejaxb.TotalMeasurementDataUsage;
import se.info24.ismOperationsPojo.ObjectUsageRecords;
import se.info24.ismOperationsPojo.TransactionResult;
import se.info24.measurementTypes.MeasurementDAO;
import se.info24.pojo.Addresses;
import se.info24.pojo.Currency;
import se.info24.pojo.GroupWeekdays;
import se.info24.pojo.MeasurementTypeTranslations;
import se.info24.pojo.ObjectAddresses;
import se.info24.objectpojo.ObjectUsageSummaryReport;
import se.info24.pojo.ObjectUsageTypes;
import se.info24.pojo.ObjectUsageUnits;
import se.info24.pojo.ProductVariantTranslations;
import se.info24.pojo.ReportSendScheduleSettings;
import se.info24.pojo.ReportSendSchedules;
import se.info24.pojo.Reports;
import se.info24.pojo.Users2;
import se.info24.pojo.WeekdayTranslations;
import se.info24.reports.ReportDAO;
import se.info24.reportsjaxb.DeviceProductCounterDetails;
import se.info24.reportsjaxb.InstantMessageLog;
import se.info24.reportsjaxb.InstantMessageLogs;
import se.info24.reportsjaxb.MsgStatus;
import se.info24.reportsjaxb.ObjectFactory;
import se.info24.reportsjaxb.ObjectProductVariantCounters;
import se.info24.reportsjaxb.ObjectUsageRecord;
import se.info24.reportsjaxb.ProductVariantID;
import se.info24.reportsjaxb.Report;
import se.info24.reportsjaxb.ReportSendSchedule;
import se.info24.reportsjaxb.ReportSendScheduleSetting;
import se.info24.reportsjaxb.ReportTranslations;
import se.info24.reportsjaxb.TingcoReports;
import se.info24.reportsjaxb.TransactionProduct;
import se.info24.restUtil.RestUtilDAO;
import se.info24.user.UserDAO;
import se.info24.util.TCMUtil;

/**
 *
 * @author Hitha
 */
public class TingcoReportsXML {

    private TingcoReports tingcoReport;
    ObjectFactory objectFactory;
    private ReportDAO dao = new ReportDAO();

    public TingcoReportsXML() {
        objectFactory = new ObjectFactory();
    }

    public TingcoReports buildGetOrganizationProductSalesReportDetails(Session session, TingcoReports tingcoReports, List<Object> TransactionProductsList, ObjectAddresses objectAddresseses, List<ProductVariantTranslations> pvt, List<TransactionResult> transactionResultList) {
        se.info24.reportsjaxb.TransactionProducts tps = new se.info24.reportsjaxb.TransactionProducts();
        DecimalFormat df = new DecimalFormat("#.##");
        RestUtilDAO utilDAO = new RestUtilDAO();
        if (objectAddresseses != null) {
            UserDAO userDAO = new UserDAO();

            Addresses address = (Addresses) userDAO.getAddress(objectAddresseses.getAddressId(), session);
            se.info24.reportsjaxb.Addresses add = new se.info24.reportsjaxb.Addresses();
            if (address.getAddressCity() != null) {
                add.setAddressCity(address.getAddressCity());
            }
            if (address.getAddressDescription() != null) {
                add.setAddressDescription(address.getAddressDescription());
            }
            if (address.getAddressStreet() != null) {
                add.setAddressStreet(address.getAddressStreet());
            }
            if (address.getAddressZip() != null) {
                add.setAddressZip(address.getAddressZip());
            }
            if (address.getCountry() != null) {
                add.setCountryName(address.getCountry().getCountryName());
            }
            tingcoReports.setAddresses(add);
        }



//        for (Iterator itr = TransactionProductsList.iterator(); itr.hasNext();) {
//            Object[] row = (Object[]) itr.next();
//            TransactionProduct tp = new TransactionProduct();
//            String ppu = null;
//            String quantity = null;
//            for (int i = 0; i < row.length; i++) {
//                boolean flag = false;
//                if (i % 2 == 0) {
//                    if (row[i] != null) {
//                        for (ProductVariantTranslations productVariantTranslations : pvt) {
//                            if (productVariantTranslations.getId().getProductVariantId().equalsIgnoreCase(row[i].toString())) {
//                                flag = true;
//                            }
//                        }
//                    }
//                    if (!flag) {
//                        if (row[i + 1] != null) {
//                            tp.setProductVariantSKU(row[i + 1].toString());
//                        }
//                        if (row[i + 2] != null) {
//                            tp.setQuantityUnit(row[i + 2].toString());
//                        }
//                        if (row[i + 3] != null) {
//                            ppu = row[i + 3].toString();
//                            tp.setPPU(df.format(Double.parseDouble(ppu) / 100));
//                        }
//                        if (row[i + 4] != null) {
//                            quantity = row[i + 4].toString();
//                            tp.setQuantity(quantity);
//                        }
//                        if (row[i + 5] != null) {
//                            Currency currency = utilDAO.getCurrencyById(row[i + 5].toString(), session);
//                            if (currency != null) {
//                                tp.setCurrencyISOCharCode(currency.getCurrencyIsocharCode());
//                            }
//                        }
//                        if (ppu != null && quantity != null) {
//                            tp.setAmount(df.format(Double.parseDouble(ppu) * Double.parseDouble(quantity) / 100));
//                        }
//                        tps.getTransactionProduct().add(tp);
//                    }
//
//                    i += 6;
//                }
//            }
//
//        }


        for (ProductVariantTranslations productVariantTranslations : pvt) {
            TransactionProduct tp = new TransactionProduct();
            int quantitysum = 0;
            double amount = 0;
            for (Iterator itr = TransactionProductsList.iterator(); itr.hasNext();) {
                Object[] row = (Object[]) itr.next();
//                TransactionProduct tp = new TransactionProduct();
                String ppu = null;
                String quantity = null;
                for (int i = 0; i < row.length; i++) {
                    boolean flag = false;
                    if (i % 2 == 0) {
                        if (row[i] != null) {
                            if (productVariantTranslations.getId().getProductVariantId().equalsIgnoreCase(row[i].toString())) {
                                flag = true;
                                tp.setProductVariantName(productVariantTranslations.getProductVariantName());
                            }
                        }
                        if (flag) {
                            if (row[i + 1] != null) {
                                tp.setProductVariantSKU(row[i + 1].toString());
                            }
                            if (row[i + 2] != null) {
                                tp.setQuantityUnit(row[i + 2].toString());
                            }
                            if (row[i + 3] != null) {
                                ppu = row[i + 3].toString();
                                tp.setPPU(df.format(Double.parseDouble(ppu) / 100));
                            }
                            if (row[i + 4] != null) {
                                quantity = row[i + 4].toString();
                                quantitysum = quantitysum + Integer.valueOf(quantity);
//                                tp.setQuantity(quantity);
                            }
                            if (row[i + 5] != null) {
                                Currency currency = utilDAO.getCurrencyById(row[i + 5].toString(), session);
                                if (currency != null) {
                                    tp.setCurrencyISOCharCode(currency.getCurrencyIsocharCode());
                                }
                            }
                            if (ppu != null && quantity != null) {
//                                tp.setAmount(df.format(Double.parseDouble(ppu) * Double.parseDouble(quantity) / 100));
                                amount = amount + (Double.parseDouble(ppu) * Double.parseDouble(quantity) / 100);
                            }
//                             tps.getTransactionProduct().add(tp);
                        }

                        i += 6;
                    }
                }
                tp.setAmount(df.format(amount));
                tp.setQuantity(quantitysum + "");
            }
            tps.getTransactionProduct().add(tp);
        }

        tingcoReports.setTransactionProducts(tps);
        return tingcoReports;
    }

    public TingcoReports buildObjectUsageUnits(TingcoReports tingcoReports, List<ObjectUsageUnits> objectUsageUnitsList) {
        se.info24.reportsjaxb.Reports reports = new se.info24.reportsjaxb.Reports();
        Report report = new Report();
        for (ObjectUsageUnits ouu : objectUsageUnitsList) {
            se.info24.reportsjaxb.ObjectUsageUnits objectUsageUnits = new se.info24.reportsjaxb.ObjectUsageUnits();
            objectUsageUnits.setUsageUnitID(ouu.getUsageUnitId());
            objectUsageUnits.setUsageUnitName(ouu.getUsageUnitName());
            if (ouu.getUsageUnitDescription() != null) {
                objectUsageUnits.setUsageUnitDescription(ouu.getUsageUnitDescription());
            }
            report.getObjectUsageUnits().add(objectUsageUnits);
        }
        reports.getReport().add(report);
        tingcoReports.setReports(reports);
        return tingcoReports;
    }

    public TingcoReports buildTingcoReportSendSchedules(TingcoReports tingcoReports, List<ReportSendSchedules> reportList) {
        se.info24.reportsjaxb.ReportSendSchedules rss = new se.info24.reportsjaxb.ReportSendSchedules();
        for (ReportSendSchedules r : reportList) {
            ReportSendSchedule rs = new ReportSendSchedule();
            rs.setReportID(r.getReportId());
            rs.setGroupID(r.getGroupId());
            rs.setReportSendScheduleID(r.getReportSendScheduleId());
            rs.setMeasurementTypeID(r.getMeasurementTypeId());
            rs.setSendToEmail(r.getSendToEmail());
            rs.setSendAsCsv(r.getSendAsCsv());
            rs.setSendAsExcel(r.getSendAsExcel());
            rs.setSendAsPdf(r.getSendAsPdf());
            rs.setSendDaily(r.getSendDaily());
            rs.setSendHourly(r.getSendHourly());
            rs.setSendMonthly(r.getSendMonthly());
            rs.setSendOnDayOfMonth(r.getSendOnDayOfMonth());
            rs.setSendOnHour(r.getSendOnHour());
            rs.setSendOnMinute(r.getSendOnMinute());
            rs.setSendOnMonth(r.getSendOnMonth());
            rs.setSendOnWeekday(r.getSendOnWeekday().toString());
            rs.setSendQuarterly(r.getSendQuarterly());
            rs.setSendWeekly(r.getSendWeekly());
            rs.setSendYearly(r.getSendYearly());
            rs.setLastUpdatedByUserID(r.getLastUpdatedByUserId());
            rss.getReportSendSchedule().add(rs);
        }
        tingcoReports.setReportSendSchedules(rss);
        return tingcoReports;
    }

    public TingcoReports buildTingcoReportsandTranslations(TingcoReports tingcoReports, List<Reports> reportList, int countryId, Session session) {
        if (!reportList.isEmpty()) {
            se.info24.reportsjaxb.Reports reports = new se.info24.reportsjaxb.Reports();
            for (Reports r : reportList) {
                Report report = new Report();
                report.setReportID(r.getReportId());
                report.setGroupID(r.getGroupId());
                report.setReportCode(r.getReportCode());
                se.info24.pojo.ReportTranslations reportTrans = dao.getReportTranslations(session, r.getReportId(), countryId);
                if (reportTrans != null) {
                    ReportTranslations rt = new ReportTranslations();
                    rt.setCountryID(countryId);
                    rt.setReportDescription(reportTrans.getReportDescription());
                    rt.setReportName(reportTrans.getReportName());
                    report.getReportTranslations().add(rt);
                }
                reports.getReport().add(report);
            }
            tingcoReports.setReports(reports);
        }
        return tingcoReports;
    }

    public TingcoReports buildTingcoReports(TingcoReports tingcoReports, List<ReportSendSchedules> reportList, Session session) {
        se.info24.reportsjaxb.Reports reports = new se.info24.reportsjaxb.Reports();
        MeasurementDAO measureDao = new MeasurementDAO();
        UserDAO userDao = new UserDAO();
        for (ReportSendSchedules r : reportList) {
            Report report = new Report();
            Reports rep = dao.getReports(session, r.getReportId());
            if (rep != null) {
                report.setReportID(rep.getReportId());
                report.setReportCode(rep.getReportCode());
            }
            ReportSendSchedule rs = new ReportSendSchedule();
            rs.setReportID(r.getReportId());
            rs.setGroupID(r.getGroupId());
            rs.setReportSendScheduleID(r.getReportSendScheduleId());
            rs.setMeasurementTypeID(r.getMeasurementTypeId());
            rs.setSendToEmail(r.getSendToEmail());
            rs.setSendAsCsv(r.getSendAsCsv());
            rs.setSendAsExcel(r.getSendAsExcel());
            rs.setSendAsPdf(r.getSendAsPdf());
            rs.setSendDaily(r.getSendDaily());
            rs.setSendHourly(r.getSendHourly());
            rs.setSendMonthly(r.getSendMonthly());
            rs.setSendOnDayOfMonth(r.getSendOnDayOfMonth());
            rs.setSendOnHour(r.getSendOnHour());
            rs.setSendOnMinute(r.getSendOnMinute());
            rs.setSendOnMonth(r.getSendOnMonth());
            rs.setSendOnWeekday(r.getSendOnWeekday().toString());
            rs.setSendQuarterly(r.getSendQuarterly());
            rs.setSendWeekly(r.getSendWeekly());
            rs.setSendYearly(r.getSendYearly());
            rs.setLastUpdatedByUserID(r.getLastUpdatedByUserId());
            Users2 user = userDao.getUserById(r.getLastUpdatedByUserId(), session);
            if (user != null) {
                String userName = null;
                if (!user.getFirstName().equals("")) {
                    userName = user.getFirstName();
                }
                if (!user.getLastName().equals("")) {
                    userName = userName + " " + user.getLastName();
                }
                rs.setUserName(userName);
                MeasurementTypeTranslations mtt = measureDao.getMeasurementTypeTranslationsByCountryId(session, r.getMeasurementTypeId(), user.getCountryId());
                if (mtt != null) {
                    rs.setMeasurementTypeName(mtt.getMeasurementTypeName());
                }
                GroupWeekdays gw = dao.getGroupWeekDaysByValue(session, r.getSendOnWeekday(), r.getGroupId());
                if (gw != null) {
                    WeekdayTranslations weekdayTrans = dao.getWeekDaysById(user.getCountryId(), gw.getId().getWeekdayId(), session);
                    if (weekdayTrans != null) {
                        rs.setWeekdayName(weekdayTrans.getWeekdayName());
                    }
                }

            }

            report.getReportSendSchedule().add(rs);
            List<ReportSendScheduleSettings> rssList = dao.getReportSendSchedulesSettings(session, r.getReportSendScheduleId());
            if (!rssList.isEmpty()) {
                for (ReportSendScheduleSettings resss : rssList) {
                    ReportSendScheduleSetting rsss = new ReportSendScheduleSetting();
                    rsss.setSettingName(resss.getSettingName());
                    rsss.setSettingValue(resss.getSettingValue());
                    report.getReportSendScheduleSetting().add(rsss);
                }
            }

            List<se.info24.pojo.ReportTranslations> rtList = dao.getReportTranslations(session, r.getReportId());
            if (!rtList.isEmpty()) {
                for (se.info24.pojo.ReportTranslations ret : rtList) {
                    ReportTranslations rt = new ReportTranslations();
                    rt.setReportName(ret.getReportName());
                    report.getReportTranslations().add(rt);
                }
            }
            reports.getReport().add(report);
        }
        tingcoReports.setReports(reports);
        return tingcoReports;
    }

    public TingcoReports buildTingcoReportsTemplate() throws DatatypeConfigurationException {
        tingcoReport = objectFactory.createTingcoReports();
        tingcoReport.setCreateRef("Info24");
        tingcoReport.setMsgVer(new BigDecimal("1.0"));
        tingcoReport.setRegionalUnits("Metric");
        tingcoReport.setTimeZone("UTC");

        tingcoReport.setMsgID(UUID.randomUUID().toString());
        tingcoReport.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));

        MsgStatus msgStatus = new MsgStatus();
        msgStatus.setResponseCode(0);
        msgStatus.setResponseText("OK");

        tingcoReport.setMsgStatus(msgStatus);
        return tingcoReport;
    }

    public TingcoDevice buildUsageChartReport(TingcoDevice tingcoDevice, List<ObjectUsageSummaryReport> usageErrorRecordsList, String groupingBy, String usageUnit) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        float totalMeasurementDataUsage = 0;
        for (ObjectUsageSummaryReport ouer : usageErrorRecordsList) {
            se.info24.devicejaxb.ObjectUsageRecords objectUsageRecords = new se.info24.devicejaxb.ObjectUsageRecords();
            objectUsageRecords.setGroupedBy(ouer.getGroupedBy());
            objectUsageRecords.setMeasurementValue(ouer.getMeasurementValue());
            totalMeasurementDataUsage = totalMeasurementDataUsage + Float.valueOf(ouer.getMeasurementValue());
            device.getContent().add(objectUsageRecords);
        }
        devices.getDevice().add(device);
        TotalMeasurementDataUsage tmdu = new TotalMeasurementDataUsage();
        tmdu.setValue(totalMeasurementDataUsage);
        tmdu.setUsageUnit(usageUnit);
        devices.setTotalMeasurementDataUsage(tmdu);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

     public TingcoDevice buildChargingStatisticsReport(TingcoDevice tingcoDevice, List<ObjectUsageSummaryReport> usageErrorRecordsList, String groupingBy) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        float totalMeasurementDataUsage = 0;
        for (ObjectUsageSummaryReport ouer : usageErrorRecordsList) {
            se.info24.devicejaxb.ObjectUsageRecords objectUsageRecords = new se.info24.devicejaxb.ObjectUsageRecords();
            objectUsageRecords.setGroupedBy(ouer.getGroupedBy());
            objectUsageRecords.setMeasurementValue(ouer.getMeasurementValue());
            objectUsageRecords.setUsageUnitName(ouer.getUsageUnitName());
            totalMeasurementDataUsage = totalMeasurementDataUsage + Float.valueOf(ouer.getMeasurementValue());
            device.getContent().add(objectUsageRecords);
        }
        devices.getDevice().add(device);
        TotalMeasurementDataUsage tmdu = new TotalMeasurementDataUsage();
        tmdu.setValue(totalMeasurementDataUsage);
        devices.setTotalMeasurementDataUsage(tmdu);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoReports buildUsageLogDetailsById(TingcoReports tingcoReports, ObjectUsageRecords objectUsageRecords, String timeZoneGMToffset) throws DatatypeConfigurationException {
        se.info24.reportsjaxb.ObjectUsageRecords ours = new se.info24.reportsjaxb.ObjectUsageRecords();
        se.info24.reportsjaxb.ObjectUsageRecord our = new ObjectUsageRecord();
        our.setUsageRecordID(objectUsageRecords.getUsageRecordId());
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, objectUsageRecords.getUsageStartTime()));
//        c.setTime(objectUsageRecords.getUsageStartTime());
        our.setUsageStartTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
//        c.setTime(objectUsageRecords.getUsageStopTime());
        c.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, objectUsageRecords.getUsageStopTime()));
        our.setUsageStopTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
        if (objectUsageRecords != null) {
            our.setUsageVolume(objectUsageRecords.getUsageVolume());
        }
        if (objectUsageRecords.getUsageUnitId() != null) {
            our.setUsageUnitID(objectUsageRecords.getUsageUnitId());
        }
        our.setUsageUnitName(objectUsageRecords.getUsageUnitName());
        ours.getObjectUsageRecord().add(our);
        tingcoReports.setObjectUsageRecords(ours);
        return tingcoReports;
    }

    public TingcoReports buildDeviceProductCounterDetails(List<se.info24.pojo.ObjectProductVariantCounters> opvcList, List<se.info24.pojo.ProductVariantTranslations> pvtList, List<Users2> users2List, String timeZoneGMToffset, TingcoReports tingcoReports) throws DatatypeConfigurationException {

        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        se.info24.reportsjaxb.Reports reports = new se.info24.reportsjaxb.Reports();
        Report reportJaxb = new Report();
        for (se.info24.pojo.ProductVariantTranslations pvt : pvtList) {
            DeviceProductCounterDetails counterDetails = new DeviceProductCounterDetails();
            ProductVariantID variantIDJaxb = new ProductVariantID();
            variantIDJaxb.setProductVariantName(pvt.getProductVariantName());
            variantIDJaxb.setValue(pvt.getId().getProductVariantId());
            counterDetails.setProductVariantID(variantIDJaxb);
            for (se.info24.pojo.ObjectProductVariantCounters opvc : opvcList) {
                if (opvc.getId().getProductVariantId().equalsIgnoreCase(pvt.getId().getProductVariantId())) {

                    ObjectProductVariantCounters countersJaxb = new ObjectProductVariantCounters();
                    countersJaxb.setCountSinceLastUpdate(opvc.getCountSinceLastUpdate());
                    countersJaxb.setCountTotal(opvc.getCountTotal());
                    GregorianCalendar gc = new GregorianCalendar();
                    gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, opvc.getUpdatedDate()));
                    countersJaxb.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc) + "");
                    countersJaxb.setUpdatedDateTCMV3(lFormat.format(gc.getTime()));
                    for (Users2 users2 : users2List) {
                        if (users2.getUserId().equalsIgnoreCase(opvc.getLastUpdatedByUserId())) {
                            counterDetails.setUpdatedBy(users2.getFirstName() + " " + users2.getLastName());
                            break;
                        }
                    }
                    counterDetails.setObjectProductVariantCounters(countersJaxb);
                    break;
                }
            }
            reportJaxb.getDeviceProductCounterDetails().add(counterDetails);
        }
        reports.getReport().add(reportJaxb);
        tingcoReports.setReports(reports);
        return tingcoReports;
    }

    public TingcoReports buildGetDeviceProductCounterTotalCount(se.info24.pojo.ObjectProductVariantCounters opvc, TingcoReports tingcoReports) {
        se.info24.reportsjaxb.Reports reports = new se.info24.reportsjaxb.Reports();
        Report reportJaxb = new Report();
        DeviceProductCounterDetails dpcd = new DeviceProductCounterDetails();
        ObjectProductVariantCounters opvcJaxb = new ObjectProductVariantCounters();
        opvcJaxb.setCountTotal(opvc.getCountTotal());
        dpcd.setObjectProductVariantCounters(opvcJaxb);
        reportJaxb.getDeviceProductCounterDetails().add(dpcd);
        reports.getReport().add(reportJaxb);
        tingcoReports.setReports(reports);
        return tingcoReports;
    }

    public TingcoReports buildGetObjectUsageTypes(TingcoReports tingcoReports, List<ObjectUsageTypes> objectUsageTypesList) {
        se.info24.reportsjaxb.Reports reports = new se.info24.reportsjaxb.Reports();
        Report reportJaxb = new Report();

        if (objectUsageTypesList.isEmpty()) {
            tingcoReports.getMsgStatus().setResponseCode(-1);
            tingcoReports.getMsgStatus().setResponseText("No Data Found");
            return tingcoReports;
        }
        int seqNo = 1;
        for (ObjectUsageTypes objectUsageType : objectUsageTypesList) {
            se.info24.reportsjaxb.ObjectUsageTypes objectUsageTypesJaxb = new se.info24.reportsjaxb.ObjectUsageTypes();
            objectUsageTypesJaxb.setUsageTypeID(objectUsageType.getUsageTypeId());
            objectUsageTypesJaxb.setUsageTypeName(objectUsageType.getUsageTypeName());
            objectUsageTypesJaxb.setSeqNo(seqNo++);
            reportJaxb.getObjectUsageTypes().add(objectUsageTypesJaxb);
        }
        reports.getReport().add(reportJaxb);
        tingcoReports.setReports(reports);
        return tingcoReports;
    }

    public TingcoReports buildGetSMSLogDetails(TingcoReports tingcoReports, List<se.info24.ismOperationsPojo.InstantMessageLog> instantMessageLog, String timeZoneGMToffset) {
        se.info24.reportsjaxb.Reports reports = new se.info24.reportsjaxb.Reports();
        InstantMessageLogs instantMessageLogs = new InstantMessageLogs();
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        GregorianCalendar gc_start = new GregorianCalendar();
        for (se.info24.ismOperationsPojo.InstantMessageLog instantMessageLog1 : instantMessageLog) {
            InstantMessageLog instantMessageLogJaxb = new InstantMessageLog();
            instantMessageLogJaxb.setIMText(instantMessageLog1.getImtext());
            instantMessageLogJaxb.setDeviceName(instantMessageLog1.getDeviceName());
            instantMessageLogJaxb.setIMSender(instantMessageLog1.getImsender());
            instantMessageLogJaxb.setIMReceiver(instantMessageLog1.getImreceiver());
            instantMessageLogJaxb.setCommandName(instantMessageLog1.getCommandName());
            gc_start.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, instantMessageLog1.getCreatedDate()));
            instantMessageLogJaxb.setCreatedDate(lFormat.format(gc_start.getTime()));
            instantMessageLogs.getInstantMessageLog().add(instantMessageLogJaxb);
        }
        tingcoReports.setInstantMessageLogs(instantMessageLogs);
        return tingcoReports;
    }

    public TingcoReports builtGetDeviceCountHistoryDetails(TingcoReports tingcoReports, List<se.info24.ismOperationsPojo.ObjectProductVariantCounters> objectProductVariantCountersList, String timeZoneGMToffset) {
        se.info24.reportsjaxb.Reports reports = new se.info24.reportsjaxb.Reports();
        Report reportJaxb = new Report();

        for (se.info24.ismOperationsPojo.ObjectProductVariantCounters objectProductVariantCounters : objectProductVariantCountersList) {
            SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DeviceProductCounterDetails dpcd = new DeviceProductCounterDetails();
            GregorianCalendar gc = new GregorianCalendar();
            ObjectProductVariantCounters opvcJaxb = new ObjectProductVariantCounters();
            opvcJaxb.setCountTotal(objectProductVariantCounters.getCountTotal());
            opvcJaxb.setCountSinceLastUpdate(objectProductVariantCounters.getCountSinceLastUpdate());
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, objectProductVariantCounters.getUpdatedDate()));
            opvcJaxb.setUpdatedDate(lFormat.format(gc.getTime()));
            dpcd.setObjectProductVariantCounters(opvcJaxb);
            reportJaxb.getDeviceProductCounterDetails().add(dpcd);
        }

        reports.getReport().add(reportJaxb);
        tingcoReports.setReports(reports);
        return tingcoReports;
    }
}

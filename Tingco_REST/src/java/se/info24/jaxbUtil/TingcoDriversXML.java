/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.jaxbUtil;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import se.info24.driversjaxb.DriverLog;
import se.info24.driversjaxb.DriverLogs;

import se.info24.driversjaxb.MsgStatus;
import se.info24.driversjaxb.ObjectFactory;
import se.info24.driversjaxb.TingcoDrivers;
import se.info24.ismOperationsPojo.DriversLogItems;
import se.info24.pojo.JourneyTypeTranslations;

/**
 *
 * @author Sumit anand 
 */
public class TingcoDriversXML {

    TingcoDrivers driverXML;

    public TingcoDrivers buildTingcoDriverTemplate() throws DatatypeConfigurationException {
        ObjectFactory objectFactory = new ObjectFactory();
        driverXML = objectFactory.createTingcoDrivers();
        driverXML.setCreateRef("Info24");
        driverXML.setMsgVer(new BigDecimal("1.0"));
        driverXML.setRegionalUnits("Metric");
        driverXML.setTimeZone("UTC");

        driverXML.setMsgID(UUID.randomUUID().toString());
        driverXML.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));

        MsgStatus msgStatus = new MsgStatus();
        msgStatus.setResponseCode(0);
        msgStatus.setResponseText("OK");

        driverXML.setMsgStatus(msgStatus);

        return driverXML;
    }

    public TingcoDrivers buildDriverLogByUserId(TingcoDrivers tingcodriver,  List<JourneyTypeTranslations> journeyTypeTranslationses , List<DriversLogItems> driversLogItemsList) throws DatatypeConfigurationException, ParseException {
        DriverLogs driverLogs = new DriverLogs();
        int seq = 0;
         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (DriversLogItems dli : driversLogItemsList) {
//            for (DriversLogItems dli : ht.keySet()) {
            DriverLog driverLog = new DriverLog();
            driverLog.setSeqNo(++seq);
            driverLog.setDriversLogItemID(dli.getDriversLogItemId());
            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
            gc.setTime(dli.getCreatedDate());
            driverLog.setCreatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            driverLog.setCreatedDateTCMV3(dateFormat.format(gc.getTime()));
            driverLog.setStartOdometer(dli.getStartOdometer());
            driverLog.setStopOdometer(dli.getStopOdometer());
            driverLog.setStartAddress(dli.getStartAddress());
            driverLog.setTotalDistance(dli.getStopOdometer() - dli.getStartOdometer());
            for (JourneyTypeTranslations journeyTypeTranslations : journeyTypeTranslationses) {
                if(journeyTypeTranslations.getId().getJourneyTypeId().equalsIgnoreCase(dli.getJourneyTypeId())){
                    driverLog.setJourneyTypeName(journeyTypeTranslations.getJourneyTypeName());
                }
            }
            driverLog.setJourneyAccount(dli.getJourneyAccount());
            driverLog.setJourneyPurpose(dli.getJourneyPurpose());
            driverLog.setStopAddress(dli.getStopAddress());
            driverLog.setJourneyTypeID(dli.getJourneyTypeId());
            driverLogs.getDriverLog().add(driverLog);
//            }
        }


        tingcodriver.setDriverLogs(driverLogs);
        return tingcodriver;
    }
}

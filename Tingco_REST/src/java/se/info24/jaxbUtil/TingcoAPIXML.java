/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.info24.jaxbUtil;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.UUID;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import se.info24.apijaxb.MsgStatus;
import se.info24.apijaxb.ObjectFactory;
import se.info24.apijaxb.TingcoAPI;

/**
 *
 * @author Sekhar
 */
public class TingcoAPIXML {

    private TingcoAPI tingcoAPI;

    /**
     * An Utility Method to give Basic TingcoAPI XMl file with MessageStatus value as OK
     * and ResponseCode as 0
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */

    public TingcoAPI buildTingcoAPITemplate() throws DatatypeConfigurationException {
        ObjectFactory objectFactory = new ObjectFactory();
        tingcoAPI = objectFactory.createTingcoAPI();
        tingcoAPI.setCreateRef("Info24");
        tingcoAPI.setMsgVer(new BigDecimal("1.0"));
        tingcoAPI.setRegionalUnits("Metric");
        tingcoAPI.setTimeZone("UTC");

        tingcoAPI.setMsgID(UUID.randomUUID().toString());
        tingcoAPI.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));

        MsgStatus msgStatus = new MsgStatus();
        msgStatus.setResponseCode(0);
        msgStatus.setResponseText("OK");

        tingcoAPI.setMsgStatus(msgStatus);
               
        return tingcoAPI;
    }

}

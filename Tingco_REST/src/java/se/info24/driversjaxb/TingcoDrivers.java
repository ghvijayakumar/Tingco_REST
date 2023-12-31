//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.02.08 at 03:34:43 PM IST 
//


package se.info24.driversjaxb;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}MsgID"/>
 *         &lt;element ref="{}MsgTimeStamp"/>
 *         &lt;element ref="{}MsgStatus" minOccurs="0"/>
 *         &lt;element ref="{}DriverLogs"/>
 *         &lt;element ref="{}JourneyTypeTranslations"/>
 *       &lt;/sequence>
 *       &lt;attribute name="TimeZone" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="UTC"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="RegionalUnits" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Metric"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="MsgVer" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="CreateRef" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Info24"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "msgID",
    "msgTimeStamp",
    "msgStatus",
    "driverLogs",
    "journeyTypeTranslations"
})
@XmlRootElement(name = "tingcoDrivers")
public class TingcoDrivers {

    @XmlElement(name = "MsgID", required = true)
    protected String msgID;
    @XmlElement(name = "MsgTimeStamp", required = true)
    protected XMLGregorianCalendar msgTimeStamp;
    @XmlElement(name = "MsgStatus")
    protected MsgStatus msgStatus;
    @XmlElement(name = "DriverLogs", required = true)
    protected DriverLogs driverLogs;
    @XmlElement(name = "JourneyTypeTranslations", required = true)
    protected JourneyTypeTranslations journeyTypeTranslations;
    @XmlAttribute(name = "TimeZone", required = true)
    protected String timeZone;
    @XmlAttribute(name = "RegionalUnits", required = true)
    protected String regionalUnits;
    @XmlAttribute(name = "MsgVer", required = true)
    protected BigDecimal msgVer;
    @XmlAttribute(name = "CreateRef", required = true)
    protected String createRef;

    /**
     * Gets the value of the msgID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgID() {
        return msgID;
    }

    /**
     * Sets the value of the msgID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgID(String value) {
        this.msgID = value;
    }

    /**
     * Gets the value of the msgTimeStamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMsgTimeStamp() {
        return msgTimeStamp;
    }

    /**
     * Sets the value of the msgTimeStamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMsgTimeStamp(XMLGregorianCalendar value) {
        this.msgTimeStamp = value;
    }

    /**
     * Gets the value of the msgStatus property.
     * 
     * @return
     *     possible object is
     *     {@link MsgStatus }
     *     
     */
    public MsgStatus getMsgStatus() {
        return msgStatus;
    }

    /**
     * Sets the value of the msgStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link MsgStatus }
     *     
     */
    public void setMsgStatus(MsgStatus value) {
        this.msgStatus = value;
    }

    /**
     * Gets the value of the driverLogs property.
     * 
     * @return
     *     possible object is
     *     {@link DriverLogs }
     *     
     */
    public DriverLogs getDriverLogs() {
        return driverLogs;
    }

    /**
     * Sets the value of the driverLogs property.
     * 
     * @param value
     *     allowed object is
     *     {@link DriverLogs }
     *     
     */
    public void setDriverLogs(DriverLogs value) {
        this.driverLogs = value;
    }

    /**
     * Gets the value of the journeyTypeTranslations property.
     * 
     * @return
     *     possible object is
     *     {@link JourneyTypeTranslations }
     *     
     */
    public JourneyTypeTranslations getJourneyTypeTranslations() {
        return journeyTypeTranslations;
    }

    /**
     * Sets the value of the journeyTypeTranslations property.
     * 
     * @param value
     *     allowed object is
     *     {@link JourneyTypeTranslations }
     *     
     */
    public void setJourneyTypeTranslations(JourneyTypeTranslations value) {
        this.journeyTypeTranslations = value;
    }

    /**
     * Gets the value of the timeZone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * Sets the value of the timeZone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeZone(String value) {
        this.timeZone = value;
    }

    /**
     * Gets the value of the regionalUnits property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegionalUnits() {
        return regionalUnits;
    }

    /**
     * Sets the value of the regionalUnits property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegionalUnits(String value) {
        this.regionalUnits = value;
    }

    /**
     * Gets the value of the msgVer property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMsgVer() {
        return msgVer;
    }

    /**
     * Sets the value of the msgVer property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMsgVer(BigDecimal value) {
        this.msgVer = value;
    }

    /**
     * Gets the value of the createRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateRef() {
        return createRef;
    }

    /**
     * Sets the value of the createRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateRef(String value) {
        this.createRef = value;
    }

}

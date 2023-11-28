//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.11 at 11:29:16 AM IST 
//


package se.info24.utiljaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element ref="{}TimezoneID"/>
 *         &lt;element ref="{}TimezoneName"/>
 *         &lt;element ref="{}TimeZoneDescription"/>
 *         &lt;element ref="{}TimeZoneGMTOffset"/>
 *         &lt;element ref="{}DaylightSavingStartTime"/>
 *         &lt;element ref="{}DaylightSavingEndTime"/>
 *         &lt;element ref="{}DaylightSavingOffset"/>
 *         &lt;element ref="{}DaylightSavingStartRule"/>
 *         &lt;element ref="{}DaylightSavingStopRule"/>
 *         &lt;element ref="{}CreatedDate"/>
 *         &lt;element ref="{}UpdatedDate"/>
 *         &lt;element ref="{}UserTimeZone" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "timezoneID",
    "timezoneName",
    "timeZoneDescription",
    "timeZoneGMTOffset",
    "daylightSavingStartTime",
    "daylightSavingEndTime",
    "daylightSavingOffset",
    "daylightSavingStartRule",
    "daylightSavingStopRule",
    "createdDate",
    "updatedDate",
    "userTimeZone"
})
@XmlRootElement(name = "Timezones")
public class Timezones {

    @XmlElement(name = "TimezoneID", required = true)
    protected String timezoneID;
    @XmlElement(name = "TimezoneName", required = true)
    protected String timezoneName;
    @XmlElement(name = "TimeZoneDescription", required = true)
    protected String timeZoneDescription;
    @XmlElement(name = "TimeZoneGMTOffset", required = true)
    protected String timeZoneGMTOffset;
    @XmlElement(name = "DaylightSavingStartTime", required = true)
    protected String daylightSavingStartTime;
    @XmlElement(name = "DaylightSavingEndTime", required = true)
    protected String daylightSavingEndTime;
    @XmlElement(name = "DaylightSavingOffset", required = true)
    protected String daylightSavingOffset;
    @XmlElement(name = "DaylightSavingStartRule", required = true)
    protected String daylightSavingStartRule;
    @XmlElement(name = "DaylightSavingStopRule", required = true)
    protected String daylightSavingStopRule;
    @XmlElement(name = "CreatedDate", required = true)
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "UpdatedDate", required = true)
    protected XMLGregorianCalendar updatedDate;
    @XmlElement(name = "UserTimeZone", required = true)
    protected List<UserTimeZone> userTimeZone;

    /**
     * Gets the value of the timezoneID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimezoneID() {
        return timezoneID;
    }

    /**
     * Sets the value of the timezoneID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimezoneID(String value) {
        this.timezoneID = value;
    }

    /**
     * Gets the value of the timezoneName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimezoneName() {
        return timezoneName;
    }

    /**
     * Sets the value of the timezoneName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimezoneName(String value) {
        this.timezoneName = value;
    }

    /**
     * Gets the value of the timeZoneDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeZoneDescription() {
        return timeZoneDescription;
    }

    /**
     * Sets the value of the timeZoneDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeZoneDescription(String value) {
        this.timeZoneDescription = value;
    }

    /**
     * Gets the value of the timeZoneGMTOffset property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeZoneGMTOffset() {
        return timeZoneGMTOffset;
    }

    /**
     * Sets the value of the timeZoneGMTOffset property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeZoneGMTOffset(String value) {
        this.timeZoneGMTOffset = value;
    }

    /**
     * Gets the value of the daylightSavingStartTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDaylightSavingStartTime() {
        return daylightSavingStartTime;
    }

    /**
     * Sets the value of the daylightSavingStartTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDaylightSavingStartTime(String value) {
        this.daylightSavingStartTime = value;
    }

    /**
     * Gets the value of the daylightSavingEndTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDaylightSavingEndTime() {
        return daylightSavingEndTime;
    }

    /**
     * Sets the value of the daylightSavingEndTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDaylightSavingEndTime(String value) {
        this.daylightSavingEndTime = value;
    }

    /**
     * Gets the value of the daylightSavingOffset property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDaylightSavingOffset() {
        return daylightSavingOffset;
    }

    /**
     * Sets the value of the daylightSavingOffset property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDaylightSavingOffset(String value) {
        this.daylightSavingOffset = value;
    }

    /**
     * Gets the value of the daylightSavingStartRule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDaylightSavingStartRule() {
        return daylightSavingStartRule;
    }

    /**
     * Sets the value of the daylightSavingStartRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDaylightSavingStartRule(String value) {
        this.daylightSavingStartRule = value;
    }

    /**
     * Gets the value of the daylightSavingStopRule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDaylightSavingStopRule() {
        return daylightSavingStopRule;
    }

    /**
     * Sets the value of the daylightSavingStopRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDaylightSavingStopRule(String value) {
        this.daylightSavingStopRule = value;
    }

    /**
     * Gets the value of the createdDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the value of the createdDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedDate(XMLGregorianCalendar value) {
        this.createdDate = value;
    }

    /**
     * Gets the value of the updatedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdatedDate() {
        return updatedDate;
    }

    /**
     * Sets the value of the updatedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdatedDate(XMLGregorianCalendar value) {
        this.updatedDate = value;
    }

    /**
     * Gets the value of the userTimeZone property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userTimeZone property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserTimeZone().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserTimeZone }
     * 
     * 
     */
    public List<UserTimeZone> getUserTimeZone() {
        if (userTimeZone == null) {
            userTimeZone = new ArrayList<UserTimeZone>();
        }
        return this.userTimeZone;
    }

}
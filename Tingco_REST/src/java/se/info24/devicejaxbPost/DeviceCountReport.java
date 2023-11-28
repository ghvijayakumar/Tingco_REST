//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.05.29 at 05:35:30 PM IST 
//


package se.info24.devicejaxbPost;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="CountryID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OptionalCountryID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="LoggedInUserGroupID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AgreementID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GroupName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ObjectGroupName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DeviceName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DeviceType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GroupBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "countryID",
    "optionalCountryID",
    "loggedInUserGroupID",
    "agreementID",
    "groupName",
    "objectGroupName",
    "deviceName",
    "deviceType",
    "groupBy"
})
@XmlRootElement(name = "DeviceCountReport")
public class DeviceCountReport {

    @XmlElement(name = "CountryID")
    protected int countryID;
    @XmlElement(name = "OptionalCountryID")
    protected String optionalCountryID;
    @XmlElement(name = "LoggedInUserGroupID", required = true)
    protected String loggedInUserGroupID;
    @XmlElement(name = "AgreementID", required = true)
    protected String agreementID;
    @XmlElement(name = "GroupName", required = true)
    protected String groupName;
    @XmlElement(name = "ObjectGroupName", required = true)
    protected String objectGroupName;
    @XmlElement(name = "DeviceName", required = true)
    protected String deviceName;
    @XmlElement(name = "DeviceType", required = true)
    protected String deviceType;
    @XmlElement(name = "GroupBy", required = true)
    protected String groupBy;

    /**
     * Gets the value of the countryID property.
     * 
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * Sets the value of the countryID property.
     * 
     */
    public void setCountryID(int value) {
        this.countryID = value;
    }

    /**
     * Gets the value of the optionalCountryID property.
     * 
     */
    public String getOptionalCountryID() {
        return optionalCountryID;
    }

    /**
     * Sets the value of the optionalCountryID property.
     * 
     */
    public void setOptionalCountryID(String  value) {
        this.optionalCountryID = value;
    }

    /**
     * Gets the value of the loggedInUserGroupID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoggedInUserGroupID() {
        return loggedInUserGroupID;
    }

    /**
     * Sets the value of the loggedInUserGroupID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoggedInUserGroupID(String value) {
        this.loggedInUserGroupID = value;
    }

    /**
     * Gets the value of the agreementID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgreementID() {
        return agreementID;
    }

    /**
     * Sets the value of the agreementID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgreementID(String value) {
        this.agreementID = value;
    }

    /**
     * Gets the value of the groupName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Sets the value of the groupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupName(String value) {
        this.groupName = value;
    }

    /**
     * Gets the value of the objectGroupName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectGroupName() {
        return objectGroupName;
    }

    /**
     * Sets the value of the objectGroupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectGroupName(String value) {
        this.objectGroupName = value;
    }

    /**
     * Gets the value of the deviceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * Sets the value of the deviceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceName(String value) {
        this.deviceName = value;
    }

    /**
     * Gets the value of the deviceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * Sets the value of the deviceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceType(String value) {
        this.deviceType = value;
    }

    /**
     * Gets the value of the groupBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupBy() {
        return groupBy;
    }

    /**
     * Sets the value of the groupBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupBy(String value) {
        this.groupBy = value;
    }

}

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.12 at 01:01:41 PM IST 
//


package se.info24.containersjaxb;

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
 *         &lt;element name="ContainerID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContainerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserAliasID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserAlias" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserAliasFirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserAliasLastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserAliasUserMobilePhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CheckInDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CheckIninDateDevice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CheckInDeviceID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CheckInDeviceName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CheckInDeviceGroupID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CheckInDeviceTypeID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CheckInDeviceAgreementID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CheckInServiceID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VisitingHost" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VisitingHostUserID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LastUpdatedByUserID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CreatedDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UpdatedDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "containerID",
    "containerName",
    "userAliasID",
    "userAlias",
    "userAliasFirstName",
    "userAliasLastName",
    "userAliasUserMobilePhone",
    "checkInDate",
    "checkIninDateDevice",
    "checkInDeviceID",
    "checkInDeviceName",
    "checkInDeviceGroupID",
    "checkInDeviceTypeID",
    "checkInDeviceAgreementID",
    "checkInServiceID",
    "visitingHost",
    "visitingHostUserID",
    "lastUpdatedByUserID",
    "createdDate",
    "updatedDate"
})
@XmlRootElement(name = "ContainerUserAlias")
public class ContainerUserAlias {

    @XmlElement(name = "ContainerID", required = true)
    protected String containerID;
    @XmlElement(name = "ContainerName", required = true)
    protected String containerName;
    @XmlElement(name = "UserAliasID", required = true)
    protected String userAliasID;
    @XmlElement(name = "UserAlias", required = true)
    protected String userAlias;
    @XmlElement(name = "UserAliasFirstName", required = true)
    protected String userAliasFirstName;
    @XmlElement(name = "UserAliasLastName", required = true)
    protected String userAliasLastName;
    @XmlElement(name = "UserAliasUserMobilePhone", required = true)
    protected String userAliasUserMobilePhone;
    @XmlElement(name = "CheckInDate", required = true)
    protected String checkInDate;
    @XmlElement(name = "CheckIninDateDevice", required = true)
    protected String checkIninDateDevice;
    @XmlElement(name = "CheckInDeviceID", required = true)
    protected String checkInDeviceID;
    @XmlElement(name = "CheckInDeviceName", required = true)
    protected String checkInDeviceName;
    @XmlElement(name = "CheckInDeviceGroupID", required = true)
    protected String checkInDeviceGroupID;
    @XmlElement(name = "CheckInDeviceTypeID", required = true)
    protected String checkInDeviceTypeID;
    @XmlElement(name = "CheckInDeviceAgreementID", required = true)
    protected String checkInDeviceAgreementID;
    @XmlElement(name = "CheckInServiceID", required = true)
    protected String checkInServiceID;
    @XmlElement(name = "VisitingHost", required = true)
    protected String visitingHost;
    @XmlElement(name = "VisitingHostUserID", required = true)
    protected String visitingHostUserID;
    @XmlElement(name = "LastUpdatedByUserID", required = true)
    protected String lastUpdatedByUserID;
    @XmlElement(name = "CreatedDate", required = true)
    protected String createdDate;
    @XmlElement(name = "UpdatedDate", required = true)
    protected String updatedDate;

    /**
     * Gets the value of the containerID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContainerID() {
        return containerID;
    }

    /**
     * Sets the value of the containerID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContainerID(String value) {
        this.containerID = value;
    }

    /**
     * Gets the value of the containerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContainerName() {
        return containerName;
    }

    /**
     * Sets the value of the containerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContainerName(String value) {
        this.containerName = value;
    }

    /**
     * Gets the value of the userAliasID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserAliasID() {
        return userAliasID;
    }

    /**
     * Sets the value of the userAliasID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserAliasID(String value) {
        this.userAliasID = value;
    }

    /**
     * Gets the value of the userAlias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserAlias() {
        return userAlias;
    }

    /**
     * Sets the value of the userAlias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserAlias(String value) {
        this.userAlias = value;
    }

    /**
     * Gets the value of the userAliasFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserAliasFirstName() {
        return userAliasFirstName;
    }

    /**
     * Sets the value of the userAliasFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserAliasFirstName(String value) {
        this.userAliasFirstName = value;
    }

    /**
     * Gets the value of the userAliasLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserAliasLastName() {
        return userAliasLastName;
    }

    /**
     * Sets the value of the userAliasLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserAliasLastName(String value) {
        this.userAliasLastName = value;
    }

    /**
     * Gets the value of the userAliasUserMobilePhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserAliasUserMobilePhone() {
        return userAliasUserMobilePhone;
    }

    /**
     * Sets the value of the userAliasUserMobilePhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserAliasUserMobilePhone(String value) {
        this.userAliasUserMobilePhone = value;
    }

    /**
     * Gets the value of the checkInDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckInDate() {
        return checkInDate;
    }

    /**
     * Sets the value of the checkInDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckInDate(String value) {
        this.checkInDate = value;
    }

    /**
     * Gets the value of the checkIninDateDevice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckIninDateDevice() {
        return checkIninDateDevice;
    }

    /**
     * Sets the value of the checkIninDateDevice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckIninDateDevice(String value) {
        this.checkIninDateDevice = value;
    }

    /**
     * Gets the value of the checkInDeviceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckInDeviceID() {
        return checkInDeviceID;
    }

    /**
     * Sets the value of the checkInDeviceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckInDeviceID(String value) {
        this.checkInDeviceID = value;
    }

    /**
     * Gets the value of the checkInDeviceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckInDeviceName() {
        return checkInDeviceName;
    }

    /**
     * Sets the value of the checkInDeviceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckInDeviceName(String value) {
        this.checkInDeviceName = value;
    }

    /**
     * Gets the value of the checkInDeviceGroupID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckInDeviceGroupID() {
        return checkInDeviceGroupID;
    }

    /**
     * Sets the value of the checkInDeviceGroupID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckInDeviceGroupID(String value) {
        this.checkInDeviceGroupID = value;
    }

    /**
     * Gets the value of the checkInDeviceTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckInDeviceTypeID() {
        return checkInDeviceTypeID;
    }

    /**
     * Sets the value of the checkInDeviceTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckInDeviceTypeID(String value) {
        this.checkInDeviceTypeID = value;
    }

    /**
     * Gets the value of the checkInDeviceAgreementID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckInDeviceAgreementID() {
        return checkInDeviceAgreementID;
    }

    /**
     * Sets the value of the checkInDeviceAgreementID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckInDeviceAgreementID(String value) {
        this.checkInDeviceAgreementID = value;
    }

    /**
     * Gets the value of the checkInServiceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckInServiceID() {
        return checkInServiceID;
    }

    /**
     * Sets the value of the checkInServiceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckInServiceID(String value) {
        this.checkInServiceID = value;
    }

    /**
     * Gets the value of the visitingHost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVisitingHost() {
        return visitingHost;
    }

    /**
     * Sets the value of the visitingHost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVisitingHost(String value) {
        this.visitingHost = value;
    }

    /**
     * Gets the value of the visitingHostUserID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVisitingHostUserID() {
        return visitingHostUserID;
    }

    /**
     * Sets the value of the visitingHostUserID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVisitingHostUserID(String value) {
        this.visitingHostUserID = value;
    }

    /**
     * Gets the value of the lastUpdatedByUserID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastUpdatedByUserID() {
        return lastUpdatedByUserID;
    }

    /**
     * Sets the value of the lastUpdatedByUserID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastUpdatedByUserID(String value) {
        this.lastUpdatedByUserID = value;
    }

    /**
     * Gets the value of the createdDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the value of the createdDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedDate(String value) {
        this.createdDate = value;
    }

    /**
     * Gets the value of the updatedDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdatedDate() {
        return updatedDate;
    }

    /**
     * Sets the value of the updatedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdatedDate(String value) {
        this.updatedDate = value;
    }

}
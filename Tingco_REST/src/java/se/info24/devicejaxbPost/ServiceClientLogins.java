//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.09.13 at 11:25:29 AM IST 
//


package se.info24.devicejaxbPost;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="ServiceClientLoginID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ServiceClientLoginName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ServiceClientLoginDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GroupID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ClientLogin" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsEnabled" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Protocol" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ProtocolVersion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ActiveFromDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ActiveToDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element ref="{}UserID"/>
 *         &lt;element ref="{}CreatedDate"/>
 *         &lt;element ref="{}UpdatedDate"/>
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
    "serviceClientLoginID",
    "serviceClientLoginName",
    "serviceClientLoginDescription",
    "groupID",
    "clientLogin",
    "password",
    "isEnabled",
    "protocol",
    "protocolVersion",
    "activeFromDate",
    "activeToDate",
    "userID",
    "createdDate",
    "updatedDate"
})
@XmlRootElement(name = "ServiceClientLogins")
public class ServiceClientLogins {

    @XmlElement(name = "ServiceClientLoginID", required = true)
    protected String serviceClientLoginID;
    @XmlElement(name = "ServiceClientLoginName", required = true)
    protected String serviceClientLoginName;
    @XmlElement(name = "ServiceClientLoginDescription", required = true)
    protected String serviceClientLoginDescription;
    @XmlElement(name = "GroupID", required = true)
    protected String groupID;
    @XmlElement(name = "ClientLogin", required = true)
    protected String clientLogin;
    @XmlElement(name = "Password", required = true)
    protected String password;
    @XmlElement(name = "IsEnabled")
    protected int isEnabled;
    @XmlElement(name = "Protocol", required = true)
    protected String protocol;
    @XmlElement(name = "ProtocolVersion")
    protected Integer protocolVersion;
    @XmlElement(name = "ActiveFromDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected String activeFromDate;
    @XmlElement(name = "ActiveToDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected String activeToDate;
    @XmlElement(name = "UserID", required = true)
    protected String userID;
    @XmlElement(name = "CreatedDate", required = true)
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "UpdatedDate", required = true)
    protected XMLGregorianCalendar updatedDate;

    /**
     * Gets the value of the serviceClientLoginID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceClientLoginID() {
        return serviceClientLoginID;
    }

    /**
     * Sets the value of the serviceClientLoginID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceClientLoginID(String value) {
        this.serviceClientLoginID = value;
    }

    /**
     * Gets the value of the serviceClientLoginName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceClientLoginName() {
        return serviceClientLoginName;
    }

    /**
     * Sets the value of the serviceClientLoginName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceClientLoginName(String value) {
        this.serviceClientLoginName = value;
    }

    /**
     * Gets the value of the serviceClientLoginDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceClientLoginDescription() {
        return serviceClientLoginDescription;
    }

    /**
     * Sets the value of the serviceClientLoginDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceClientLoginDescription(String value) {
        this.serviceClientLoginDescription = value;
    }

    /**
     * Gets the value of the groupID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupID() {
        return groupID;
    }

    /**
     * Sets the value of the groupID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupID(String value) {
        this.groupID = value;
    }

    /**
     * Gets the value of the clientLogin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientLogin() {
        return clientLogin;
    }

    /**
     * Sets the value of the clientLogin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientLogin(String value) {
        this.clientLogin = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the isEnabled property.
     * 
     */
    public int getIsEnabled() {
        return isEnabled;
    }

    /**
     * Sets the value of the isEnabled property.
     * 
     */
    public void setIsEnabled(int value) {
        this.isEnabled = value;
    }

    /**
     * Gets the value of the protocol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Sets the value of the protocol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProtocol(String value) {
        this.protocol = value;
    }

    /**
     * Gets the value of the protocolVersion property.
     * 
     */
    public Integer getProtocolVersion() {
        return protocolVersion;
    }

    /**
     * Sets the value of the protocolVersion property.
     * 
     */
    public void setProtocolVersion(Integer value) {
        this.protocolVersion = value;
    }

    /**
     * Gets the value of the activeFromDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public String getActiveFromDate() {
        return activeFromDate;
    }

    /**
     * Sets the value of the activeFromDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActiveFromDate(String value) {
        this.activeFromDate = value;
    }

    /**
     * Gets the value of the activeToDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public String getActiveToDate() {
        return activeToDate;
    }

    /**
     * Sets the value of the activeToDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActiveToDate(String value) {
        this.activeToDate = value;
    }

    /**
     * Gets the value of the userID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the value of the userID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserID(String value) {
        this.userID = value;
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

}

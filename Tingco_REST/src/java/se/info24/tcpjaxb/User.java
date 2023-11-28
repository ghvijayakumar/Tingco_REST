//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.04.11 at 01:04:34 PM IST 
//


package se.info24.tcpjaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element name="UserID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ApplicationID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DomainID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GroupID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LoginName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NickName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserMobilePhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}Address"/>
 *         &lt;element name="CountryID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ActiveFromDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ActiveToDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="IsLockedOut" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ChangePwdRequired" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="UserSmallImageURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LastLoginDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="LockedOutDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element ref="{}CreatedDate"/>
 *         &lt;element ref="{}UpdatedDate"/>
 *         &lt;element name="SocialSecurityNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserAlias" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserTypeID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserAliasTypeID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ObjectGroupID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OrganizationAlias" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TimeZone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}Groups"/>
 *       &lt;/sequence>
 *       &lt;attribute name="SeqNo" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
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
    "userID",
    "applicationID",
    "domainID",
    "groupID",
    "loginName",
    "nickName",
    "firstName",
    "lastName",
    "userEmail",
    "userMobilePhone",
    "address",
    "countryID",
    "activeFromDate",
    "activeToDate",
    "isLockedOut",
    "changePwdRequired",
    "userSmallImageURL",
    "lastLoginDate",
    "lockedOutDate",
    "createdDate",
    "updatedDate",
    "socialSecurityNumber",
    "userAlias",
    "userTypeID",
    "userAliasTypeID",
    "objectGroupID",
    "organizationAlias",
    "password",
    "timeZone",
    "groups"
})
@XmlRootElement(name = "User")
public class User {

    @XmlElement(name = "UserID", required = true)
    protected String userID;
    @XmlElement(name = "ApplicationID", required = true)
    protected String applicationID;
    @XmlElement(name = "DomainID", required = true)
    protected String domainID;
    @XmlElement(name = "GroupID", required = true)
    protected String groupID;
    @XmlElement(name = "LoginName", required = true)
    protected String loginName;
    @XmlElement(name = "NickName", required = true)
    protected String nickName;
    @XmlElement(name = "FirstName", required = true)
    protected String firstName;
    @XmlElement(name = "LastName", required = true)
    protected String lastName;
    @XmlElement(name = "UserEmail", required = true)
    protected String userEmail;
    @XmlElement(name = "UserMobilePhone", required = true)
    protected String userMobilePhone;
    @XmlElement(name = "Address", required = true)
    protected Address address;
    @XmlElement(name = "CountryID")
    protected int countryID;
    @XmlElement(name = "ActiveFromDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar activeFromDate;
    @XmlElement(name = "ActiveToDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar activeToDate;
    @XmlElement(name = "IsLockedOut")
    protected int isLockedOut;
    @XmlElement(name = "ChangePwdRequired")
    protected int changePwdRequired;
    @XmlElement(name = "UserSmallImageURL", required = true)
    protected String userSmallImageURL;
    @XmlElement(name = "LastLoginDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastLoginDate;
    @XmlElement(name = "LockedOutDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lockedOutDate;
    @XmlElement(name = "CreatedDate", required = true)
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "UpdatedDate", required = true)
    protected XMLGregorianCalendar updatedDate;
    @XmlElement(name = "SocialSecurityNumber", required = true)
    protected String socialSecurityNumber;
    @XmlElement(name = "UserAlias", required = true)
    protected String userAlias;
    @XmlElement(name = "UserTypeID", required = true)
    protected String userTypeID;
    @XmlElement(name = "UserAliasTypeID", required = true)
    protected String userAliasTypeID;
    @XmlElement(name = "ObjectGroupID", required = true)
    protected String objectGroupID;
    @XmlElement(name = "OrganizationAlias", required = true)
    protected String organizationAlias;
    @XmlElement(name = "Password", required = true)
    protected String password;
    @XmlElement(name = "TimeZone", required = true)
    protected String timeZone;
    @XmlElement(name = "Groups", required = true)
    protected Groups groups;
    @XmlAttribute(name = "SeqNo", required = true)
    protected int seqNo;

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
     * Gets the value of the applicationID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationID() {
        return applicationID;
    }

    /**
     * Sets the value of the applicationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationID(String value) {
        this.applicationID = value;
    }

    /**
     * Gets the value of the domainID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomainID() {
        return domainID;
    }

    /**
     * Sets the value of the domainID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomainID(String value) {
        this.domainID = value;
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
     * Gets the value of the loginName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * Sets the value of the loginName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginName(String value) {
        this.loginName = value;
    }

    /**
     * Gets the value of the nickName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Sets the value of the nickName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNickName(String value) {
        this.nickName = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the userEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Sets the value of the userEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserEmail(String value) {
        this.userEmail = value;
    }

    /**
     * Gets the value of the userMobilePhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserMobilePhone() {
        return userMobilePhone;
    }

    /**
     * Sets the value of the userMobilePhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserMobilePhone(String value) {
        this.userMobilePhone = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setAddress(Address value) {
        this.address = value;
    }

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
     * Gets the value of the activeFromDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getActiveFromDate() {
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
    public void setActiveFromDate(XMLGregorianCalendar value) {
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
    public XMLGregorianCalendar getActiveToDate() {
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
    public void setActiveToDate(XMLGregorianCalendar value) {
        this.activeToDate = value;
    }

    /**
     * Gets the value of the isLockedOut property.
     * 
     */
    public int getIsLockedOut() {
        return isLockedOut;
    }

    /**
     * Sets the value of the isLockedOut property.
     * 
     */
    public void setIsLockedOut(int value) {
        this.isLockedOut = value;
    }

    /**
     * Gets the value of the changePwdRequired property.
     * 
     */
    public int getChangePwdRequired() {
        return changePwdRequired;
    }

    /**
     * Sets the value of the changePwdRequired property.
     * 
     */
    public void setChangePwdRequired(int value) {
        this.changePwdRequired = value;
    }

    /**
     * Gets the value of the userSmallImageURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserSmallImageURL() {
        return userSmallImageURL;
    }

    /**
     * Sets the value of the userSmallImageURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserSmallImageURL(String value) {
        this.userSmallImageURL = value;
    }

    /**
     * Gets the value of the lastLoginDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastLoginDate() {
        return lastLoginDate;
    }

    /**
     * Sets the value of the lastLoginDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastLoginDate(XMLGregorianCalendar value) {
        this.lastLoginDate = value;
    }

    /**
     * Gets the value of the lockedOutDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLockedOutDate() {
        return lockedOutDate;
    }

    /**
     * Sets the value of the lockedOutDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLockedOutDate(XMLGregorianCalendar value) {
        this.lockedOutDate = value;
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
     * Gets the value of the socialSecurityNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    /**
     * Sets the value of the socialSecurityNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSocialSecurityNumber(String value) {
        this.socialSecurityNumber = value;
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
     * Gets the value of the userTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserTypeID() {
        return userTypeID;
    }

    /**
     * Sets the value of the userTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserTypeID(String value) {
        this.userTypeID = value;
    }

    /**
     * Gets the value of the userAliasTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserAliasTypeID() {
        return userAliasTypeID;
    }

    /**
     * Sets the value of the userAliasTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserAliasTypeID(String value) {
        this.userAliasTypeID = value;
    }

    /**
     * Gets the value of the objectGroupID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectGroupID() {
        return objectGroupID;
    }

    /**
     * Sets the value of the objectGroupID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectGroupID(String value) {
        this.objectGroupID = value;
    }

    /**
     * Gets the value of the organizationAlias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganizationAlias() {
        return organizationAlias;
    }

    /**
     * Sets the value of the organizationAlias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganizationAlias(String value) {
        this.organizationAlias = value;
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
     * Gets the value of the groups property.
     * 
     * @return
     *     possible object is
     *     {@link Groups }
     *     
     */
    public Groups getGroups() {
        return groups;
    }

    /**
     * Sets the value of the groups property.
     * 
     * @param value
     *     allowed object is
     *     {@link Groups }
     *     
     */
    public void setGroups(Groups value) {
        this.groups = value;
    }

    /**
     * Gets the value of the seqNo property.
     * 
     */
    public int getSeqNo() {
        return seqNo;
    }

    /**
     * Sets the value of the seqNo property.
     * 
     */
    public void setSeqNo(int value) {
        this.seqNo = value;
    }

}
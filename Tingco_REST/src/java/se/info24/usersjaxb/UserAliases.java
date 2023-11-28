//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.15 at 05:34:39 PM IST 
//


package se.info24.usersjaxb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="UserAliasID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Tag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserAlias" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserAliasTypeID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserAliasTypeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}UserID"/>
 *         &lt;element ref="{}CountryID"/>
 *         &lt;element name="GroupID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GroupName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ObjectGroup" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}FirstName"/>
 *         &lt;element ref="{}LastName"/>
 *         &lt;element name="UserAliasFullName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserFullName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}UserEmail"/>
 *         &lt;element ref="{}UserMobilePhone"/>
 *         &lt;element name="SocialSecurityNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsEnabled" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ActiveFromDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ActiveToDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ActiveFromDateTCMV3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ActiveToDateTCMV3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FirstUseDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="LastUseDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="CreditAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="CreditAmountCurrencyID" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Credits" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="ExtVerify" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="LastUpdatedByUserID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}CreatedDate"/>
 *         &lt;element ref="{}UpdatedDate"/>
 *         &lt;element name="IsBlocked" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FunctionAreas" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CurrencyISOCharCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}UserAliasDetails" maxOccurs="unbounded"/>
 *         &lt;element ref="{}ObjectGroupTranslations" maxOccurs="unbounded"/>
 *         &lt;element ref="{}OGMUserAlias" maxOccurs="unbounded"/>
 *         &lt;element ref="{}FraudLogs" maxOccurs="unbounded"/>
 *         &lt;element ref="{}ObjectTags"/>
 *         &lt;element ref="{}UserAliasOrders"/>
 *         &lt;element ref="{}BlackList"/>
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
    "userAliasID",
    "tag",
    "userAlias",
    "userAliasTypeID",
    "userAliasTypeName",
    "userID",
    "countryID",
    "groupID",
    "groupName",
    "objectGroup",
    "firstName",
    "lastName",
    "userAliasFullName",
    "userFullName",
    "userEmail",
    "userMobilePhone",
    "socialSecurityNumber",
    "isEnabled",
    "activeFromDate",
    "activeToDate",
    "activeFromDateTCMV3",
    "activeToDateTCMV3",
    "firstUseDate",
    "lastUseDate",
    "creditAmount",
    "creditAmountCurrencyID",
    "credits",
    "extVerify",
    "lastUpdatedByUserID",
    "createdDate",
    "updatedDate",
    "isBlocked",
    "functionAreas",
    "currencyISOCharCode",
    "userAliasDetails",
    "objectGroupTranslations",
    "ogmUserAlias",
    "fraudLogs",
    "objectTags",
    "userAliasOrders",
    "blackList"
})
@XmlRootElement(name = "UserAliases")
public class UserAliases {

    @XmlElement(name = "UserAliasID", required = true)
    protected String userAliasID;
    @XmlElement(name = "Tag", required = true)
    protected String tag;
    @XmlElement(name = "UserAlias", required = true)
    protected String userAlias;
    @XmlElement(name = "UserAliasTypeID", required = true)
    protected String userAliasTypeID;
    @XmlElement(name = "UserAliasTypeName", required = true)
    protected String userAliasTypeName;
    @XmlElement(name = "UserID", required = true)
    protected String userID;
    @XmlElement(name = "CountryID", required = true)
    protected CountryID countryID;
    @XmlElement(name = "GroupID", required = true)
    protected String groupID;
    @XmlElement(name = "GroupName", required = true)
    protected String groupName;
    @XmlElement(name = "ObjectGroup", required = true)
    protected String objectGroup;
    @XmlElement(name = "FirstName", required = true)
    protected String firstName;
    @XmlElement(name = "LastName", required = true)
    protected String lastName;
    @XmlElement(name = "UserAliasFullName", required = true)
    protected String userAliasFullName;
    @XmlElement(name = "UserFullName", required = true)
    protected String userFullName;
    @XmlElement(name = "UserEmail", required = true)
    protected String userEmail;
    @XmlElement(name = "UserMobilePhone", required = true)
    protected String userMobilePhone;
    @XmlElement(name = "SocialSecurityNumber", required = true)
    protected String socialSecurityNumber;
    @XmlElement(name = "IsEnabled", required = true)
    protected String isEnabled;
    @XmlElement(name = "ActiveFromDate", required = true)
    protected String activeFromDate;
    @XmlElement(name = "ActiveToDate", required = true)
    protected String activeToDate;
    @XmlElement(name = "ActiveFromDateTCMV3", required = true)
    protected String activeFromDateTCMV3;
    @XmlElement(name = "ActiveToDateTCMV3", required = true)
    protected String activeToDateTCMV3;
    @XmlElement(name = "FirstUseDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar firstUseDate;
    @XmlElement(name = "LastUseDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastUseDate;
    @XmlElement(name = "CreditAmount", required = true)
    protected BigDecimal creditAmount;
    @XmlElement(name = "CreditAmountCurrencyID", required = true)
    protected BigDecimal creditAmountCurrencyID;
    @XmlElement(name = "Credits", required = true)
    protected BigDecimal credits;
    @XmlElement(name = "ExtVerify", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar extVerify;
    @XmlElement(name = "LastUpdatedByUserID", required = true)
    protected String lastUpdatedByUserID;
    @XmlElement(name = "CreatedDate", required = true)
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "UpdatedDate", required = true)
    protected XMLGregorianCalendar updatedDate;
    @XmlElement(name = "IsBlocked", required = true)
    protected String isBlocked;
    @XmlElement(name = "FunctionAreas", required = true)
    protected String functionAreas;
    @XmlElement(name = "CurrencyISOCharCode", required = true)
    protected String currencyISOCharCode;
    @XmlElement(name = "UserAliasDetails", required = true)
    protected List<UserAliasDetails> userAliasDetails;
    @XmlElement(name = "ObjectGroupTranslations", required = true)
    protected List<ObjectGroupTranslations> objectGroupTranslations;
    @XmlElement(name = "OGMUserAlias", required = true)
    protected List<OGMUserAlias> ogmUserAlias;
    @XmlElement(name = "FraudLogs", required = true)
    protected List<FraudLogs> fraudLogs;
    @XmlElement(name = "ObjectTags", required = true)
    protected ObjectTags objectTags;
    @XmlElement(name = "UserAliasOrders", required = true)
    protected UserAliasOrders userAliasOrders;
    @XmlElement(name = "BlackList", required = true)
    protected BlackList blackList;
    @XmlAttribute(name = "SeqNo", required = true)
    protected int seqNo;

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
     * Gets the value of the tag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTag() {
        return tag;
    }

    /**
     * Sets the value of the tag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTag(String value) {
        this.tag = value;
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
     * Gets the value of the userAliasTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserAliasTypeName() {
        return userAliasTypeName;
    }

    /**
     * Sets the value of the userAliasTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserAliasTypeName(String value) {
        this.userAliasTypeName = value;
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
     * Gets the value of the countryID property.
     * 
     * @return
     *     possible object is
     *     {@link CountryID }
     *     
     */
    public CountryID getCountryID() {
        return countryID;
    }

    /**
     * Sets the value of the countryID property.
     * 
     * @param value
     *     allowed object is
     *     {@link CountryID }
     *     
     */
    public void setCountryID(CountryID value) {
        this.countryID = value;
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
     * Gets the value of the objectGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectGroup() {
        return objectGroup;
    }

    /**
     * Sets the value of the objectGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectGroup(String value) {
        this.objectGroup = value;
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
     * Gets the value of the userAliasFullName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserAliasFullName() {
        return userAliasFullName;
    }

    /**
     * Sets the value of the userAliasFullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserAliasFullName(String value) {
        this.userAliasFullName = value;
    }

    /**
     * Gets the value of the userFullName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserFullName() {
        return userFullName;
    }

    /**
     * Sets the value of the userFullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserFullName(String value) {
        this.userFullName = value;
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
     * Gets the value of the isEnabled property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsEnabled() {
        return isEnabled;
    }

    /**
     * Sets the value of the isEnabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsEnabled(String value) {
        this.isEnabled = value;
    }

    /**
     * Gets the value of the activeFromDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
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
     *     {@link String }
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
     *     {@link String }
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
     *     {@link String }
     *     
     */
    public void setActiveToDate(String value) {
        this.activeToDate = value;
    }

    /**
     * Gets the value of the activeFromDateTCMV3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActiveFromDateTCMV3() {
        return activeFromDateTCMV3;
    }

    /**
     * Sets the value of the activeFromDateTCMV3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActiveFromDateTCMV3(String value) {
        this.activeFromDateTCMV3 = value;
    }

    /**
     * Gets the value of the activeToDateTCMV3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActiveToDateTCMV3() {
        return activeToDateTCMV3;
    }

    /**
     * Sets the value of the activeToDateTCMV3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActiveToDateTCMV3(String value) {
        this.activeToDateTCMV3 = value;
    }

    /**
     * Gets the value of the firstUseDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFirstUseDate() {
        return firstUseDate;
    }

    /**
     * Sets the value of the firstUseDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFirstUseDate(XMLGregorianCalendar value) {
        this.firstUseDate = value;
    }

    /**
     * Gets the value of the lastUseDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastUseDate() {
        return lastUseDate;
    }

    /**
     * Sets the value of the lastUseDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastUseDate(XMLGregorianCalendar value) {
        this.lastUseDate = value;
    }

    /**
     * Gets the value of the creditAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    /**
     * Sets the value of the creditAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCreditAmount(BigDecimal value) {
        this.creditAmount = value;
    }

    /**
     * Gets the value of the creditAmountCurrencyID property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCreditAmountCurrencyID() {
        return creditAmountCurrencyID;
    }

    /**
     * Sets the value of the creditAmountCurrencyID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCreditAmountCurrencyID(BigDecimal value) {
        this.creditAmountCurrencyID = value;
    }

    /**
     * Gets the value of the credits property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCredits() {
        return credits;
    }

    /**
     * Sets the value of the credits property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCredits(BigDecimal value) {
        this.credits = value;
    }

    /**
     * Gets the value of the extVerify property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExtVerify() {
        return extVerify;
    }

    /**
     * Sets the value of the extVerify property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExtVerify(XMLGregorianCalendar value) {
        this.extVerify = value;
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
     * Gets the value of the isBlocked property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsBlocked() {
        return isBlocked;
    }

    /**
     * Sets the value of the isBlocked property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsBlocked(String value) {
        this.isBlocked = value;
    }

    /**
     * Gets the value of the functionAreas property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFunctionAreas() {
        return functionAreas;
    }

    /**
     * Sets the value of the functionAreas property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFunctionAreas(String value) {
        this.functionAreas = value;
    }

    /**
     * Gets the value of the currencyISOCharCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyISOCharCode() {
        return currencyISOCharCode;
    }

    /**
     * Sets the value of the currencyISOCharCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyISOCharCode(String value) {
        this.currencyISOCharCode = value;
    }

    /**
     * Gets the value of the userAliasDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userAliasDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserAliasDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserAliasDetails }
     * 
     * 
     */
    public List<UserAliasDetails> getUserAliasDetails() {
        if (userAliasDetails == null) {
            userAliasDetails = new ArrayList<UserAliasDetails>();
        }
        return this.userAliasDetails;
    }

    /**
     * Gets the value of the objectGroupTranslations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the objectGroupTranslations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObjectGroupTranslations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectGroupTranslations }
     * 
     * 
     */
    public List<ObjectGroupTranslations> getObjectGroupTranslations() {
        if (objectGroupTranslations == null) {
            objectGroupTranslations = new ArrayList<ObjectGroupTranslations>();
        }
        return this.objectGroupTranslations;
    }

    /**
     * Gets the value of the ogmUserAlias property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ogmUserAlias property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOGMUserAlias().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OGMUserAlias }
     * 
     * 
     */
    public List<OGMUserAlias> getOGMUserAlias() {
        if (ogmUserAlias == null) {
            ogmUserAlias = new ArrayList<OGMUserAlias>();
        }
        return this.ogmUserAlias;
    }

    /**
     * Gets the value of the fraudLogs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fraudLogs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFraudLogs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FraudLogs }
     * 
     * 
     */
    public List<FraudLogs> getFraudLogs() {
        if (fraudLogs == null) {
            fraudLogs = new ArrayList<FraudLogs>();
        }
        return this.fraudLogs;
    }

    /**
     * Gets the value of the objectTags property.
     * 
     * @return
     *     possible object is
     *     {@link ObjectTags }
     *     
     */
    public ObjectTags getObjectTags() {
        return objectTags;
    }

    /**
     * Sets the value of the objectTags property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectTags }
     *     
     */
    public void setObjectTags(ObjectTags value) {
        this.objectTags = value;
    }

    /**
     * Gets the value of the userAliasOrders property.
     * 
     * @return
     *     possible object is
     *     {@link UserAliasOrders }
     *     
     */
    public UserAliasOrders getUserAliasOrders() {
        return userAliasOrders;
    }

    /**
     * Sets the value of the userAliasOrders property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserAliasOrders }
     *     
     */
    public void setUserAliasOrders(UserAliasOrders value) {
        this.userAliasOrders = value;
    }

    /**
     * Gets the value of the blackList property.
     * 
     * @return
     *     possible object is
     *     {@link BlackList }
     *     
     */
    public BlackList getBlackList() {
        return blackList;
    }

    /**
     * Sets the value of the blackList property.
     * 
     * @param value
     *     allowed object is
     *     {@link BlackList }
     *     
     */
    public void setBlackList(BlackList value) {
        this.blackList = value;
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

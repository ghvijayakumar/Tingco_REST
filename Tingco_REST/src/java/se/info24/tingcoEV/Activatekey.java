//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.17 at 03:09:01 PM IST 
//


package se.info24.tingcoEV;

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
 *         &lt;element name="UserAlias" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserAliasTypeID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Info24GroupID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GroupParentID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GroupTypeID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TrustGroupID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserMobilePhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SocialSecurityNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PurchaseDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LicensePlate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserTypeID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserRoleID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IApproveTermsOfBusiness" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IApproveSendingMarketingInfo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ObjectGroupID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ActiveToDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AddressStreet" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AddressZip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AddressCity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CountryID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IsLockedOut" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "userAlias",
    "userAliasTypeID",
    "info24GroupID",
    "groupParentID",
    "groupTypeID",
    "trustGroupID",
    "firstName",
    "lastName",
    "userEmail",
    "userMobilePhone",
    "socialSecurityNumber",
    "purchaseDate",
    "licensePlate",
    "userTypeID",
    "userRoleID",
    "password",
    "iApproveTermsOfBusiness",
    "iApproveSendingMarketingInfo",
    "objectGroupID",
    "activeToDate",
    "addressStreet",
    "addressZip",
    "addressCity",
    "countryID",
    "isLockedOut"
})
@XmlRootElement(name = "Activatekey")
public class Activatekey {

    @XmlElement(name = "UserAlias", required = true)
    protected String userAlias;
    @XmlElement(name = "UserAliasTypeID", required = true)
    protected String userAliasTypeID;
    @XmlElement(name = "Info24GroupID", required = true)
    protected String info24GroupID;
    @XmlElement(name = "GroupParentID", required = true)
    protected String groupParentID;
    @XmlElement(name = "GroupTypeID", required = true)
    protected String groupTypeID;
    @XmlElement(name = "TrustGroupID", required = true)
    protected String trustGroupID;
    @XmlElement(name = "FirstName", required = true)
    protected String firstName;
    @XmlElement(name = "LastName", required = true)
    protected String lastName;
    @XmlElement(name = "UserEmail", required = true)
    protected String userEmail;
    @XmlElement(name = "UserMobilePhone", required = true)
    protected String userMobilePhone;
    @XmlElement(name = "SocialSecurityNumber", required = true)
    protected String socialSecurityNumber;
    @XmlElement(name = "PurchaseDate", required = true)
    protected String purchaseDate;
    @XmlElement(name = "LicensePlate", required = true)
    protected String licensePlate;
    @XmlElement(name = "UserTypeID", required = true)
    protected String userTypeID;
    @XmlElement(name = "UserRoleID", required = true)
    protected String userRoleID;
    @XmlElement(name = "Password", required = true)
    protected String password;
    @XmlElement(name = "IApproveTermsOfBusiness")
    protected int iApproveTermsOfBusiness;
    @XmlElement(name = "IApproveSendingMarketingInfo")
    protected int iApproveSendingMarketingInfo;
    @XmlElement(name = "ObjectGroupID", required = true)
    protected String objectGroupID;
    @XmlElement(name = "ActiveToDate", required = true)
    protected String activeToDate;
    @XmlElement(name = "AddressStreet", required = true)
    protected String addressStreet;
    @XmlElement(name = "AddressZip", required = true)
    protected String addressZip;
    @XmlElement(name = "AddressCity", required = true)
    protected String addressCity;
    @XmlElement(name = "CountryID")
    protected int countryID;
    @XmlElement(name = "IsLockedOut", required = true)
    protected String isLockedOut;

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
     * Gets the value of the info24GroupID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInfo24GroupID() {
        return info24GroupID;
    }

    /**
     * Sets the value of the info24GroupID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInfo24GroupID(String value) {
        this.info24GroupID = value;
    }

    /**
     * Gets the value of the groupParentID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupParentID() {
        return groupParentID;
    }

    /**
     * Sets the value of the groupParentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupParentID(String value) {
        this.groupParentID = value;
    }

    /**
     * Gets the value of the groupTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupTypeID() {
        return groupTypeID;
    }

    /**
     * Sets the value of the groupTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupTypeID(String value) {
        this.groupTypeID = value;
    }

    /**
     * Gets the value of the trustGroupID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrustGroupID() {
        return trustGroupID;
    }

    /**
     * Sets the value of the trustGroupID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrustGroupID(String value) {
        this.trustGroupID = value;
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
     * Gets the value of the purchaseDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Sets the value of the purchaseDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurchaseDate(String value) {
        this.purchaseDate = value;
    }

    /**
     * Gets the value of the licensePlate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * Sets the value of the licensePlate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLicensePlate(String value) {
        this.licensePlate = value;
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
     * Gets the value of the userRoleID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserRoleID() {
        return userRoleID;
    }

    /**
     * Sets the value of the userRoleID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserRoleID(String value) {
        this.userRoleID = value;
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
     * Gets the value of the iApproveTermsOfBusiness property.
     * 
     */
    public int getIApproveTermsOfBusiness() {
        return iApproveTermsOfBusiness;
    }

    /**
     * Sets the value of the iApproveTermsOfBusiness property.
     * 
     */
    public void setIApproveTermsOfBusiness(int value) {
        this.iApproveTermsOfBusiness = value;
    }

    /**
     * Gets the value of the iApproveSendingMarketingInfo property.
     * 
     */
    public int getIApproveSendingMarketingInfo() {
        return iApproveSendingMarketingInfo;
    }

    /**
     * Sets the value of the iApproveSendingMarketingInfo property.
     * 
     */
    public void setIApproveSendingMarketingInfo(int value) {
        this.iApproveSendingMarketingInfo = value;
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
     * Gets the value of the addressStreet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressStreet() {
        return addressStreet;
    }

    /**
     * Sets the value of the addressStreet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressStreet(String value) {
        this.addressStreet = value;
    }

    /**
     * Gets the value of the addressZip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressZip() {
        return addressZip;
    }

    /**
     * Sets the value of the addressZip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressZip(String value) {
        this.addressZip = value;
    }

    /**
     * Gets the value of the addressCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressCity() {
        return addressCity;
    }

    /**
     * Sets the value of the addressCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressCity(String value) {
        this.addressCity = value;
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
     * Gets the value of the isLockedOut property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsLockedOut() {
        return isLockedOut;
    }

    /**
     * Sets the value of the isLockedOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsLockedOut(String value) {
        this.isLockedOut = value;
    }

}

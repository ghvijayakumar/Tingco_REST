//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.05.10 at 03:31:14 PM IST 
//


package se.info24.devicejaxb;

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
 *         &lt;element name="ObjectContactID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GroupID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ObjectContactTypeID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContactFirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContactLastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContactFullName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContactOrganizationName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContactTitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContactEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContactPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContactMobilePhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContactFax" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContactDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContactShortDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContactStreetAddress1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContactStreetAddress2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContactSuite" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContactZipCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContactCity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CountryID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CountryStateID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContactWeb" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContactIM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LinkedToUserID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SortOrder" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="LastUpdatedByUserID" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "objectContactID",
    "groupID",
    "objectContactTypeID",
    "contactFirstName",
    "contactLastName",
    "contactFullName",
    "contactOrganizationName",
    "contactTitle",
    "contactEmail",
    "contactPhone",
    "contactMobilePhone",
    "contactFax",
    "contactDescription",
    "contactShortDescription",
    "contactStreetAddress1",
    "contactStreetAddress2",
    "contactSuite",
    "contactZipCode",
    "contactCity",
    "countryID",
    "countryStateID",
    "contactWeb",
    "contactIM",
    "linkedToUserID",
    "sortOrder",
    "lastUpdatedByUserID",
    "createdDate",
    "updatedDate"
})
@XmlRootElement(name = "ObjectContact")
public class ObjectContact {

    @XmlElement(name = "ObjectContactID", required = true)
    protected String objectContactID;
    @XmlElement(name = "GroupID", required = true)
    protected String groupID;
    @XmlElement(name = "ObjectContactTypeID", required = true)
    protected String objectContactTypeID;
    @XmlElement(name = "ContactFirstName", required = true)
    protected String contactFirstName;
    @XmlElement(name = "ContactLastName", required = true)
    protected String contactLastName;
    @XmlElement(name = "ContactFullName", required = true)
    protected String contactFullName;
    @XmlElement(name = "ContactOrganizationName", required = true)
    protected String contactOrganizationName;
    @XmlElement(name = "ContactTitle", required = true)
    protected String contactTitle;
    @XmlElement(name = "ContactEmail", required = true)
    protected String contactEmail;
    @XmlElement(name = "ContactPhone", required = true)
    protected String contactPhone;
    @XmlElement(name = "ContactMobilePhone", required = true)
    protected String contactMobilePhone;
    @XmlElement(name = "ContactFax", required = true)
    protected String contactFax;
    @XmlElement(name = "ContactDescription", required = true)
    protected String contactDescription;
    @XmlElement(name = "ContactShortDescription", required = true)
    protected String contactShortDescription;
    @XmlElement(name = "ContactStreetAddress1", required = true)
    protected String contactStreetAddress1;
    @XmlElement(name = "ContactStreetAddress2", required = true)
    protected String contactStreetAddress2;
    @XmlElement(name = "ContactSuite", required = true)
    protected String contactSuite;
    @XmlElement(name = "ContactZipCode", required = true)
    protected String contactZipCode;
    @XmlElement(name = "ContactCity", required = true)
    protected String contactCity;
    @XmlElement(name = "CountryID")
    protected int countryID;
    @XmlElement(name = "CountryStateID", required = true)
    protected String countryStateID;
    @XmlElement(name = "ContactWeb", required = true)
    protected String contactWeb;
    @XmlElement(name = "ContactIM", required = true)
    protected String contactIM;
    @XmlElement(name = "LinkedToUserID", required = true)
    protected String linkedToUserID;
    @XmlElement(name = "SortOrder")
    protected int sortOrder;
    @XmlElement(name = "LastUpdatedByUserID", required = true)
    protected String lastUpdatedByUserID;
    @XmlElement(name = "CreatedDate", required = true)
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "UpdatedDate", required = true)
    protected XMLGregorianCalendar updatedDate;

    /**
     * Gets the value of the objectContactID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectContactID() {
        return objectContactID;
    }

    /**
     * Sets the value of the objectContactID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectContactID(String value) {
        this.objectContactID = value;
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
     * Gets the value of the objectContactTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectContactTypeID() {
        return objectContactTypeID;
    }

    /**
     * Sets the value of the objectContactTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectContactTypeID(String value) {
        this.objectContactTypeID = value;
    }

    /**
     * Gets the value of the contactFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactFirstName() {
        return contactFirstName;
    }

    /**
     * Sets the value of the contactFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactFirstName(String value) {
        this.contactFirstName = value;
    }

    /**
     * Gets the value of the contactLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactLastName() {
        return contactLastName;
    }

    /**
     * Sets the value of the contactLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactLastName(String value) {
        this.contactLastName = value;
    }

    /**
     * Gets the value of the contactFullName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactFullName() {
        return contactFullName;
    }

    /**
     * Sets the value of the contactFullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactFullName(String value) {
        this.contactFullName = value;
    }

    /**
     * Gets the value of the contactOrganizationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactOrganizationName() {
        return contactOrganizationName;
    }

    /**
     * Sets the value of the contactOrganizationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactOrganizationName(String value) {
        this.contactOrganizationName = value;
    }

    /**
     * Gets the value of the contactTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactTitle() {
        return contactTitle;
    }

    /**
     * Sets the value of the contactTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactTitle(String value) {
        this.contactTitle = value;
    }

    /**
     * Gets the value of the contactEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * Sets the value of the contactEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactEmail(String value) {
        this.contactEmail = value;
    }

    /**
     * Gets the value of the contactPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * Sets the value of the contactPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactPhone(String value) {
        this.contactPhone = value;
    }

    /**
     * Gets the value of the contactMobilePhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactMobilePhone() {
        return contactMobilePhone;
    }

    /**
     * Sets the value of the contactMobilePhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactMobilePhone(String value) {
        this.contactMobilePhone = value;
    }

    /**
     * Gets the value of the contactFax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactFax() {
        return contactFax;
    }

    /**
     * Sets the value of the contactFax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactFax(String value) {
        this.contactFax = value;
    }

    /**
     * Gets the value of the contactDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactDescription() {
        return contactDescription;
    }

    /**
     * Sets the value of the contactDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactDescription(String value) {
        this.contactDescription = value;
    }

    /**
     * Gets the value of the contactShortDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactShortDescription() {
        return contactShortDescription;
    }

    /**
     * Sets the value of the contactShortDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactShortDescription(String value) {
        this.contactShortDescription = value;
    }

    /**
     * Gets the value of the contactStreetAddress1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactStreetAddress1() {
        return contactStreetAddress1;
    }

    /**
     * Sets the value of the contactStreetAddress1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactStreetAddress1(String value) {
        this.contactStreetAddress1 = value;
    }

    /**
     * Gets the value of the contactStreetAddress2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactStreetAddress2() {
        return contactStreetAddress2;
    }

    /**
     * Sets the value of the contactStreetAddress2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactStreetAddress2(String value) {
        this.contactStreetAddress2 = value;
    }

    /**
     * Gets the value of the contactSuite property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactSuite() {
        return contactSuite;
    }

    /**
     * Sets the value of the contactSuite property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactSuite(String value) {
        this.contactSuite = value;
    }

    /**
     * Gets the value of the contactZipCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactZipCode() {
        return contactZipCode;
    }

    /**
     * Sets the value of the contactZipCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactZipCode(String value) {
        this.contactZipCode = value;
    }

    /**
     * Gets the value of the contactCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactCity() {
        return contactCity;
    }

    /**
     * Sets the value of the contactCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactCity(String value) {
        this.contactCity = value;
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
     * Gets the value of the countryStateID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryStateID() {
        return countryStateID;
    }

    /**
     * Sets the value of the countryStateID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryStateID(String value) {
        this.countryStateID = value;
    }

    /**
     * Gets the value of the contactWeb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactWeb() {
        return contactWeb;
    }

    /**
     * Sets the value of the contactWeb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactWeb(String value) {
        this.contactWeb = value;
    }

    /**
     * Gets the value of the contactIM property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactIM() {
        return contactIM;
    }

    /**
     * Sets the value of the contactIM property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactIM(String value) {
        this.contactIM = value;
    }

    /**
     * Gets the value of the linkedToUserID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkedToUserID() {
        return linkedToUserID;
    }

    /**
     * Sets the value of the linkedToUserID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinkedToUserID(String value) {
        this.linkedToUserID = value;
    }

    /**
     * Gets the value of the sortOrder property.
     * 
     */
    public int getSortOrder() {
        return sortOrder;
    }

    /**
     * Sets the value of the sortOrder property.
     * 
     */
    public void setSortOrder(int value) {
        this.sortOrder = value;
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

}

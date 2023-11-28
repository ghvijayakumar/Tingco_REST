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
 *         &lt;element name="AddressID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AddressName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AddressDesc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AddressTypeID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="AddressRegion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AddressStreet" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AddressStreet2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AddressSuite" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AddressZip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AddressCity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CountryID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CountryName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CountryStateID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AddressPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AddressMobile" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AddressFax" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AddressEmail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AddressWeb" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AddressIM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AddressLatitude" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="AddressLongitude" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="AddressDepth" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="ObjectID" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "addressID",
    "addressName",
    "addressDesc",
    "addressTypeID",
    "addressRegion",
    "addressStreet",
    "addressStreet2",
    "addressSuite",
    "addressZip",
    "addressCity",
    "countryID",
    "countryName",
    "countryStateID",
    "addressPhone",
    "addressMobile",
    "addressFax",
    "addressEmail",
    "addressWeb",
    "addressIM",
    "addressLatitude",
    "addressLongitude",
    "addressDepth",
    "objectID"
})
@XmlRootElement(name = "Address")
public class Address {

    @XmlElement(name = "AddressID", required = true)
    protected String addressID;
    @XmlElement(name = "AddressName", required = true)
    protected String addressName;
    @XmlElement(name = "AddressDesc", required = true)
    protected String addressDesc;
    @XmlElement(name = "AddressTypeID")
    protected int addressTypeID;
    @XmlElement(name = "AddressRegion", required = true)
    protected String addressRegion;
    @XmlElement(name = "AddressStreet", required = true)
    protected String addressStreet;
    @XmlElement(name = "AddressStreet2", required = true)
    protected String addressStreet2;
    @XmlElement(name = "AddressSuite", required = true)
    protected String addressSuite;
    @XmlElement(name = "AddressZip", required = true)
    protected String addressZip;
    @XmlElement(name = "AddressCity", required = true)
    protected String addressCity;
    @XmlElement(name = "CountryID")
    protected int countryID;
    @XmlElement(name = "CountryName", required = true)
    protected String countryName;
    @XmlElement(name = "CountryStateID", required = true)
    protected String countryStateID;
    @XmlElement(name = "AddressPhone", required = true)
    protected String addressPhone;
    @XmlElement(name = "AddressMobile", required = true)
    protected String addressMobile;
    @XmlElement(name = "AddressFax", required = true)
    protected String addressFax;
    @XmlElement(name = "AddressEmail", required = true)
    protected String addressEmail;
    @XmlElement(name = "AddressWeb", required = true)
    protected String addressWeb;
    @XmlElement(name = "AddressIM", required = true)
    protected String addressIM;
    @XmlElement(name = "AddressLatitude")
    protected float addressLatitude;
    @XmlElement(name = "AddressLongitude")
    protected float addressLongitude;
    @XmlElement(name = "AddressDepth")
    protected float addressDepth;
    @XmlElement(name = "ObjectID", required = true)
    protected String objectID;

    /**
     * Gets the value of the addressID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressID() {
        return addressID;
    }

    /**
     * Sets the value of the addressID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressID(String value) {
        this.addressID = value;
    }

    /**
     * Gets the value of the addressName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressName() {
        return addressName;
    }

    /**
     * Sets the value of the addressName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressName(String value) {
        this.addressName = value;
    }

    /**
     * Gets the value of the addressDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressDesc() {
        return addressDesc;
    }

    /**
     * Sets the value of the addressDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressDesc(String value) {
        this.addressDesc = value;
    }

    /**
     * Gets the value of the addressTypeID property.
     * 
     */
    public int getAddressTypeID() {
        return addressTypeID;
    }

    /**
     * Sets the value of the addressTypeID property.
     * 
     */
    public void setAddressTypeID(int value) {
        this.addressTypeID = value;
    }

    /**
     * Gets the value of the addressRegion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressRegion() {
        return addressRegion;
    }

    /**
     * Sets the value of the addressRegion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressRegion(String value) {
        this.addressRegion = value;
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
     * Gets the value of the addressStreet2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressStreet2() {
        return addressStreet2;
    }

    /**
     * Sets the value of the addressStreet2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressStreet2(String value) {
        this.addressStreet2 = value;
    }

    /**
     * Gets the value of the addressSuite property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressSuite() {
        return addressSuite;
    }

    /**
     * Sets the value of the addressSuite property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressSuite(String value) {
        this.addressSuite = value;
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
     * Gets the value of the countryName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Sets the value of the countryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryName(String value) {
        this.countryName = value;
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
     * Gets the value of the addressPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressPhone() {
        return addressPhone;
    }

    /**
     * Sets the value of the addressPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressPhone(String value) {
        this.addressPhone = value;
    }

    /**
     * Gets the value of the addressMobile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressMobile() {
        return addressMobile;
    }

    /**
     * Sets the value of the addressMobile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressMobile(String value) {
        this.addressMobile = value;
    }

    /**
     * Gets the value of the addressFax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressFax() {
        return addressFax;
    }

    /**
     * Sets the value of the addressFax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressFax(String value) {
        this.addressFax = value;
    }

    /**
     * Gets the value of the addressEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressEmail() {
        return addressEmail;
    }

    /**
     * Sets the value of the addressEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressEmail(String value) {
        this.addressEmail = value;
    }

    /**
     * Gets the value of the addressWeb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressWeb() {
        return addressWeb;
    }

    /**
     * Sets the value of the addressWeb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressWeb(String value) {
        this.addressWeb = value;
    }

    /**
     * Gets the value of the addressIM property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressIM() {
        return addressIM;
    }

    /**
     * Sets the value of the addressIM property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressIM(String value) {
        this.addressIM = value;
    }

    /**
     * Gets the value of the addressLatitude property.
     * 
     */
    public float getAddressLatitude() {
        return addressLatitude;
    }

    /**
     * Sets the value of the addressLatitude property.
     * 
     */
    public void setAddressLatitude(float value) {
        this.addressLatitude = value;
    }

    /**
     * Gets the value of the addressLongitude property.
     * 
     */
    public float getAddressLongitude() {
        return addressLongitude;
    }

    /**
     * Sets the value of the addressLongitude property.
     * 
     */
    public void setAddressLongitude(float value) {
        this.addressLongitude = value;
    }

    /**
     * Gets the value of the addressDepth property.
     * 
     */
    public float getAddressDepth() {
        return addressDepth;
    }

    /**
     * Sets the value of the addressDepth property.
     * 
     */
    public void setAddressDepth(float value) {
        this.addressDepth = value;
    }

    /**
     * Gets the value of the objectID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectID() {
        return objectID;
    }

    /**
     * Sets the value of the objectID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectID(String value) {
        this.objectID = value;
    }

}

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.11.22 at 03:06:49 PM IST 
//


package se.info24.productsjaxb;

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
 *         &lt;element name="ChargeTypeID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CountryID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ChargeTypeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChargeTypeDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LastUpdatedByUSerID" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "chargeTypeID",
    "countryID",
    "chargeTypeName",
    "chargeTypeDescription",
    "lastUpdatedByUSerID",
    "createdDate",
    "updatedDate"
})
@XmlRootElement(name = "ChargeTypeTranslations")
public class ChargeTypeTranslations {

    @XmlElement(name = "ChargeTypeID", required = true)
    protected String chargeTypeID;
    @XmlElement(name = "CountryID")
    protected int countryID;
    @XmlElement(name = "ChargeTypeName", required = true)
    protected String chargeTypeName;
    @XmlElement(name = "ChargeTypeDescription", required = true)
    protected String chargeTypeDescription;
    @XmlElement(name = "LastUpdatedByUSerID", required = true)
    protected String lastUpdatedByUSerID;
    @XmlElement(name = "CreatedDate", required = true)
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "UpdatedDate", required = true)
    protected XMLGregorianCalendar updatedDate;

    /**
     * Gets the value of the chargeTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeTypeID() {
        return chargeTypeID;
    }

    /**
     * Sets the value of the chargeTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeTypeID(String value) {
        this.chargeTypeID = value;
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
     * Gets the value of the chargeTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeTypeName() {
        return chargeTypeName;
    }

    /**
     * Sets the value of the chargeTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeTypeName(String value) {
        this.chargeTypeName = value;
    }

    /**
     * Gets the value of the chargeTypeDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeTypeDescription() {
        return chargeTypeDescription;
    }

    /**
     * Sets the value of the chargeTypeDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeTypeDescription(String value) {
        this.chargeTypeDescription = value;
    }

    /**
     * Gets the value of the lastUpdatedByUSerID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastUpdatedByUSerID() {
        return lastUpdatedByUSerID;
    }

    /**
     * Sets the value of the lastUpdatedByUSerID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastUpdatedByUSerID(String value) {
        this.lastUpdatedByUSerID = value;
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
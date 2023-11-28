//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.05.15 at 10:36:30 AM IST 
//


package se.info24.priceplansjaxb;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="PricePlanVersionID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PricePlanID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VersionName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VersionNameTCMV3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VersionDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ActiveFromDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ActiveToDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ActiveFromDateTCMV3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ActiveToDateTCMV3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Priority" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CurrencyID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IsEnabled" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="LastUpdatedByUserID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CreatedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="UpdatedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="CurrencyISOCharCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}PricePlanItems" maxOccurs="unbounded" minOccurs="0"/>
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
    "pricePlanVersionID",
    "pricePlanID",
    "versionName",
    "versionNameTCMV3",
    "versionDescription",
    "activeFromDate",
    "activeToDate",
    "activeFromDateTCMV3",
    "activeToDateTCMV3",
    "priority",
    "currencyID",
    "isEnabled",
    "lastUpdatedByUserID",
    "createdDate",
    "updatedDate",
    "currencyISOCharCode",
    "pricePlanItems"
})
@XmlRootElement(name = "PricePlanVersions")
public class PricePlanVersions {

    @XmlElement(name = "PricePlanVersionID", required = true)
    protected String pricePlanVersionID;
    @XmlElement(name = "PricePlanID", required = true)
    protected String pricePlanID;
    @XmlElement(name = "VersionName", required = true)
    protected String versionName;
    @XmlElement(name = "VersionNameTCMV3", required = true)
    protected String versionNameTCMV3;
    @XmlElement(name = "VersionDescription", required = true)
    protected String versionDescription;
    @XmlElement(name = "ActiveFromDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar activeFromDate;
    @XmlElement(name = "ActiveToDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar activeToDate;
    @XmlElement(name = "ActiveFromDateTCMV3", required = true)
    protected String activeFromDateTCMV3;
    @XmlElement(name = "ActiveToDateTCMV3", required = true)
    protected String activeToDateTCMV3;
    @XmlElement(name = "Priority")
    protected int priority;
    @XmlElement(name = "CurrencyID")
    protected int currencyID;
    @XmlElement(name = "IsEnabled")
    protected int isEnabled;
    @XmlElement(name = "LastUpdatedByUserID", required = true)
    protected String lastUpdatedByUserID;
    @XmlElement(name = "CreatedDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "UpdatedDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updatedDate;
    @XmlElement(name = "CurrencyISOCharCode", required = true)
    protected String currencyISOCharCode;
    @XmlElement(name = "PricePlanItems")
    protected List<PricePlanItems> pricePlanItems;

    /**
     * Gets the value of the pricePlanVersionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricePlanVersionID() {
        return pricePlanVersionID;
    }

    /**
     * Sets the value of the pricePlanVersionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricePlanVersionID(String value) {
        this.pricePlanVersionID = value;
    }

    /**
     * Gets the value of the pricePlanID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricePlanID() {
        return pricePlanID;
    }

    /**
     * Sets the value of the pricePlanID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricePlanID(String value) {
        this.pricePlanID = value;
    }

    /**
     * Gets the value of the versionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersionName() {
        return versionName;
    }

    /**
     * Sets the value of the versionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersionName(String value) {
        this.versionName = value;
    }

    /**
     * Gets the value of the versionNameTCMV3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersionNameTCMV3() {
        return versionNameTCMV3;
    }

    /**
     * Sets the value of the versionNameTCMV3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersionNameTCMV3(String value) {
        this.versionNameTCMV3 = value;
    }

    /**
     * Gets the value of the versionDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersionDescription() {
        return versionDescription;
    }

    /**
     * Sets the value of the versionDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersionDescription(String value) {
        this.versionDescription = value;
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
     * Gets the value of the priority property.
     * 
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     */
    public void setPriority(int value) {
        this.priority = value;
    }

    /**
     * Gets the value of the currencyID property.
     * 
     */
    public int getCurrencyID() {
        return currencyID;
    }

    /**
     * Sets the value of the currencyID property.
     * 
     */
    public void setCurrencyID(int value) {
        this.currencyID = value;
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
     * Gets the value of the pricePlanItems property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pricePlanItems property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPricePlanItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PricePlanItems }
     * 
     * 
     */
    public List<PricePlanItems> getPricePlanItems() {
        if (pricePlanItems == null) {
            pricePlanItems = new ArrayList<PricePlanItems>();
        }
        return this.pricePlanItems;
    }

}
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.11.22 at 04:50:28 PM IST 
//


package se.info24.reportsjaxb;

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
 *         &lt;element name="UsageUnitID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UsageUnitName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UsageUnitDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}LastUpdatedByUserID"/>
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
    "usageUnitID",
    "usageUnitName",
    "usageUnitDescription",
    "lastUpdatedByUserID",
    "createdDate",
    "updatedDate"
})
@XmlRootElement(name = "ObjectUsageUnits")
public class ObjectUsageUnits {

    @XmlElement(name = "UsageUnitID", required = true)
    protected String usageUnitID;
    @XmlElement(name = "UsageUnitName", required = true)
    protected String usageUnitName;
    @XmlElement(name = "UsageUnitDescription", required = true)
    protected String usageUnitDescription;
    @XmlElement(name = "LastUpdatedByUserID", required = true)
    protected String lastUpdatedByUserID;
    @XmlElement(name = "CreatedDate", required = true)
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "UpdatedDate", required = true)
    protected XMLGregorianCalendar updatedDate;

    /**
     * Gets the value of the usageUnitID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsageUnitID() {
        return usageUnitID;
    }

    /**
     * Sets the value of the usageUnitID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsageUnitID(String value) {
        this.usageUnitID = value;
    }

    /**
     * Gets the value of the usageUnitName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsageUnitName() {
        return usageUnitName;
    }

    /**
     * Sets the value of the usageUnitName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsageUnitName(String value) {
        this.usageUnitName = value;
    }

    /**
     * Gets the value of the usageUnitDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsageUnitDescription() {
        return usageUnitDescription;
    }

    /**
     * Sets the value of the usageUnitDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsageUnitDescription(String value) {
        this.usageUnitDescription = value;
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

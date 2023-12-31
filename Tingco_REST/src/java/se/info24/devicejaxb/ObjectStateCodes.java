//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.09.12 at 01:04:12 PM IST 
//


package se.info24.devicejaxb;

import java.util.ArrayList;
import java.util.List;
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
 *       &lt;sequence minOccurs="0">
 *         &lt;element name="ObjectStateCodeID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ObjectStateCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LastUpdatedByUserID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}CreatedDate"/>
 *         &lt;element ref="{}UpdatedDate"/>
 *         &lt;element ref="{}ObjectStateCodeTranslations" maxOccurs="unbounded" minOccurs="0"/>
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
    "objectStateCodeID",
    "objectStateCode",
    "lastUpdatedByUserID",
    "createdDate",
    "updatedDate",
    "objectStateCodeTranslations"
})
@XmlRootElement(name = "ObjectStateCodes")
public class ObjectStateCodes {

    @XmlElement(name = "ObjectStateCodeID")
    protected String objectStateCodeID;
    @XmlElement(name = "ObjectStateCode")
    protected String objectStateCode;
    @XmlElement(name = "LastUpdatedByUserID")
    protected String lastUpdatedByUserID;
    @XmlElement(name = "CreatedDate")
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "UpdatedDate")
    protected XMLGregorianCalendar updatedDate;
    @XmlElement(name = "ObjectStateCodeTranslations")
    protected List<ObjectStateCodeTranslations> objectStateCodeTranslations;

    /**
     * Gets the value of the objectStateCodeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectStateCodeID() {
        return objectStateCodeID;
    }

    /**
     * Sets the value of the objectStateCodeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectStateCodeID(String value) {
        this.objectStateCodeID = value;
    }

    /**
     * Gets the value of the objectStateCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectStateCode() {
        return objectStateCode;
    }

    /**
     * Sets the value of the objectStateCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectStateCode(String value) {
        this.objectStateCode = value;
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
     * Gets the value of the objectStateCodeTranslations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the objectStateCodeTranslations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObjectStateCodeTranslations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectStateCodeTranslations }
     * 
     * 
     */
    public List<ObjectStateCodeTranslations> getObjectStateCodeTranslations() {
        if (objectStateCodeTranslations == null) {
            objectStateCodeTranslations = new ArrayList<ObjectStateCodeTranslations>();
        }
        return this.objectStateCodeTranslations;
    }

}

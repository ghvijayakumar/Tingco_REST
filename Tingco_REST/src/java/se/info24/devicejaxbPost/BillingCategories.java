//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.09 at 11:50:38 AM IST 
//


package se.info24.devicejaxbPost;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element name="BillingCategoryID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ObjectCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SKU" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}GroupID"/>
 *         &lt;element ref="{}LastUpdatedByUserID"/>
 *         &lt;element ref="{}CreatedDate"/>
 *         &lt;element ref="{}UpdatedDate"/>
 *         &lt;element ref="{}BillingCategoryTranslations" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="SeqNo">
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
    "billingCategoryID",
    "objectCode",
    "sku",
    "groupID",
    "lastUpdatedByUserID",
    "createdDate",
    "updatedDate",
    "billingCategoryTranslations"
})
@XmlRootElement(name = "BillingCategories")
public class BillingCategories {

    @XmlElement(name = "BillingCategoryID", required = true)
    protected String billingCategoryID;
    @XmlElement(name = "ObjectCode", required = true)
    protected String objectCode;
    @XmlElement(name = "SKU", required = true)
    protected String sku;
    @XmlElement(name = "GroupID", required = true)
    protected GroupID groupID;
    @XmlElement(name = "LastUpdatedByUserID", required = true)
    protected LastUpdatedByUserID lastUpdatedByUserID;
    @XmlElement(name = "CreatedDate", required = true)
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "UpdatedDate", required = true)
    protected XMLGregorianCalendar updatedDate;
    @XmlElement(name = "BillingCategoryTranslations")
    protected List<BillingCategoryTranslations> billingCategoryTranslations;
    @XmlAttribute(name = "SeqNo")
    protected Integer seqNo;

    /**
     * Gets the value of the billingCategoryID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingCategoryID() {
        return billingCategoryID;
    }

    /**
     * Sets the value of the billingCategoryID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingCategoryID(String value) {
        this.billingCategoryID = value;
    }

    /**
     * Gets the value of the objectCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectCode() {
        return objectCode;
    }

    /**
     * Sets the value of the objectCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectCode(String value) {
        this.objectCode = value;
    }

    /**
     * Gets the value of the sku property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSKU() {
        return sku;
    }

    /**
     * Sets the value of the sku property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSKU(String value) {
        this.sku = value;
    }

    /**
     * Gets the value of the groupID property.
     * 
     * @return
     *     possible object is
     *     {@link GroupID }
     *     
     */
    public GroupID getGroupID() {
        return groupID;
    }

    /**
     * Sets the value of the groupID property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupID }
     *     
     */
    public void setGroupID(GroupID value) {
        this.groupID = value;
    }

    /**
     * Gets the value of the lastUpdatedByUserID property.
     * 
     * @return
     *     possible object is
     *     {@link LastUpdatedByUserID }
     *     
     */
    public LastUpdatedByUserID getLastUpdatedByUserID() {
        return lastUpdatedByUserID;
    }

    /**
     * Sets the value of the lastUpdatedByUserID property.
     * 
     * @param value
     *     allowed object is
     *     {@link LastUpdatedByUserID }
     *     
     */
    public void setLastUpdatedByUserID(LastUpdatedByUserID value) {
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
     * Gets the value of the billingCategoryTranslations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the billingCategoryTranslations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBillingCategoryTranslations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BillingCategoryTranslations }
     * 
     * 
     */
    public List<BillingCategoryTranslations> getBillingCategoryTranslations() {
        if (billingCategoryTranslations == null) {
            billingCategoryTranslations = new ArrayList<BillingCategoryTranslations>();
        }
        return this.billingCategoryTranslations;
    }

    /**
     * Gets the value of the seqNo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSeqNo() {
        return seqNo;
    }

    /**
     * Sets the value of the seqNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSeqNo(Integer value) {
        this.seqNo = value;
    }

}
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.05.15 at 10:31:15 AM IST 
//


package se.info24.appjaxb;

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
 *         &lt;element name="AgreementItemID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AgreementItemParentID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AgreementID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AgreementItemTypeID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AgreementStatusID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ItemSectionNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ItemName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ItemText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ActiveFromDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ActiveToDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ActiveFromDateTCMV3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ActiveToDateTCMV3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CompletedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ObjectID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FunctionAreaID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PricePlanID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LastUpdatedByUserID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CreatedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="UpdatedDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ProductVariantID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ProductVariantName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PricePlanName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UpdatedBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}AgreementItemTypeTranslations" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}AgreementStatusTranslations" maxOccurs="unbounded" minOccurs="0"/>
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
    "agreementItemID",
    "agreementItemParentID",
    "agreementID",
    "agreementItemTypeID",
    "agreementStatusID",
    "itemSectionNumber",
    "itemName",
    "itemText",
    "activeFromDate",
    "activeToDate",
    "activeFromDateTCMV3",
    "activeToDateTCMV3",
    "completedDate",
    "objectID",
    "functionAreaID",
    "pricePlanID",
    "lastUpdatedByUserID",
    "createdDate",
    "updatedDate",
    "productVariantID",
    "productVariantName",
    "pricePlanName",
    "updatedBy",
    "agreementItemTypeTranslations",
    "agreementStatusTranslations"
})
@XmlRootElement(name = "AgreementItems")
public class AgreementItems {

    @XmlElement(name = "AgreementItemID", required = true)
    protected String agreementItemID;
    @XmlElement(name = "AgreementItemParentID", required = true)
    protected String agreementItemParentID;
    @XmlElement(name = "AgreementID", required = true)
    protected String agreementID;
    @XmlElement(name = "AgreementItemTypeID", required = true)
    protected String agreementItemTypeID;
    @XmlElement(name = "AgreementStatusID", required = true)
    protected String agreementStatusID;
    @XmlElement(name = "ItemSectionNumber")
    protected int itemSectionNumber;
    @XmlElement(name = "ItemName", required = true)
    protected String itemName;
    @XmlElement(name = "ItemText", required = true)
    protected String itemText;
    @XmlElement(name = "ActiveFromDate", required = true)
    protected String activeFromDate;
    @XmlElement(name = "ActiveToDate", required = true)
    protected String activeToDate;
    @XmlElement(name = "ActiveFromDateTCMV3", required = true)
    protected String activeFromDateTCMV3;
    @XmlElement(name = "ActiveToDateTCMV3", required = true)
    protected String activeToDateTCMV3;
    @XmlElement(name = "CompletedDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar completedDate;
    @XmlElement(name = "ObjectID", required = true)
    protected String objectID;
    @XmlElement(name = "FunctionAreaID", required = true)
    protected String functionAreaID;
    @XmlElement(name = "PricePlanID", required = true)
    protected String pricePlanID;
    @XmlElement(name = "LastUpdatedByUserID", required = true)
    protected String lastUpdatedByUserID;
    @XmlElement(name = "CreatedDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "UpdatedDate", required = true)
    protected String updatedDate;
    @XmlElement(name = "ProductVariantID", required = true)
    protected String productVariantID;
    @XmlElement(name = "ProductVariantName", required = true)
    protected String productVariantName;
    @XmlElement(name = "PricePlanName", required = true)
    protected String pricePlanName;
    @XmlElement(name = "UpdatedBy", required = true)
    protected String updatedBy;
    @XmlElement(name = "AgreementItemTypeTranslations")
    protected List<AgreementItemTypeTranslations> agreementItemTypeTranslations;
    @XmlElement(name = "AgreementStatusTranslations")
    protected List<AgreementStatusTranslations> agreementStatusTranslations;
    @XmlAttribute(name = "SeqNo", required = true)
    protected int seqNo;

    /**
     * Gets the value of the agreementItemID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgreementItemID() {
        return agreementItemID;
    }

    /**
     * Sets the value of the agreementItemID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgreementItemID(String value) {
        this.agreementItemID = value;
    }

    /**
     * Gets the value of the agreementItemParentID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgreementItemParentID() {
        return agreementItemParentID;
    }

    /**
     * Sets the value of the agreementItemParentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgreementItemParentID(String value) {
        this.agreementItemParentID = value;
    }

    /**
     * Gets the value of the agreementID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgreementID() {
        return agreementID;
    }

    /**
     * Sets the value of the agreementID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgreementID(String value) {
        this.agreementID = value;
    }

    /**
     * Gets the value of the agreementItemTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgreementItemTypeID() {
        return agreementItemTypeID;
    }

    /**
     * Sets the value of the agreementItemTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgreementItemTypeID(String value) {
        this.agreementItemTypeID = value;
    }

    /**
     * Gets the value of the agreementStatusID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgreementStatusID() {
        return agreementStatusID;
    }

    /**
     * Sets the value of the agreementStatusID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgreementStatusID(String value) {
        this.agreementStatusID = value;
    }

    /**
     * Gets the value of the itemSectionNumber property.
     * 
     */
    public int getItemSectionNumber() {
        return itemSectionNumber;
    }

    /**
     * Sets the value of the itemSectionNumber property.
     * 
     */
    public void setItemSectionNumber(int value) {
        this.itemSectionNumber = value;
    }

    /**
     * Gets the value of the itemName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the value of the itemName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemName(String value) {
        this.itemName = value;
    }

    /**
     * Gets the value of the itemText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemText() {
        return itemText;
    }

    /**
     * Sets the value of the itemText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemText(String value) {
        this.itemText = value;
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
     * Gets the value of the completedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCompletedDate() {
        return completedDate;
    }

    /**
     * Sets the value of the completedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCompletedDate(XMLGregorianCalendar value) {
        this.completedDate = value;
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

    /**
     * Gets the value of the functionAreaID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFunctionAreaID() {
        return functionAreaID;
    }

    /**
     * Sets the value of the functionAreaID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFunctionAreaID(String value) {
        this.functionAreaID = value;
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
     *     {@link String }
     *     
     */
    public String getUpdatedDate() {
        return updatedDate;
    }

    /**
     * Sets the value of the updatedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdatedDate(String value) {
        this.updatedDate = value;
    }

    /**
     * Gets the value of the productVariantID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductVariantID() {
        return productVariantID;
    }

    /**
     * Sets the value of the productVariantID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductVariantID(String value) {
        this.productVariantID = value;
    }

    /**
     * Gets the value of the productVariantName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductVariantName() {
        return productVariantName;
    }

    /**
     * Sets the value of the productVariantName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductVariantName(String value) {
        this.productVariantName = value;
    }

    /**
     * Gets the value of the pricePlanName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricePlanName() {
        return pricePlanName;
    }

    /**
     * Sets the value of the pricePlanName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricePlanName(String value) {
        this.pricePlanName = value;
    }

    /**
     * Gets the value of the updatedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * Sets the value of the updatedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdatedBy(String value) {
        this.updatedBy = value;
    }

    /**
     * Gets the value of the agreementItemTypeTranslations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the agreementItemTypeTranslations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAgreementItemTypeTranslations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AgreementItemTypeTranslations }
     * 
     * 
     */
    public List<AgreementItemTypeTranslations> getAgreementItemTypeTranslations() {
        if (agreementItemTypeTranslations == null) {
            agreementItemTypeTranslations = new ArrayList<AgreementItemTypeTranslations>();
        }
        return this.agreementItemTypeTranslations;
    }

    /**
     * Gets the value of the agreementStatusTranslations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the agreementStatusTranslations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAgreementStatusTranslations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AgreementStatusTranslations }
     * 
     * 
     */
    public List<AgreementStatusTranslations> getAgreementStatusTranslations() {
        if (agreementStatusTranslations == null) {
            agreementStatusTranslations = new ArrayList<AgreementStatusTranslations>();
        }
        return this.agreementStatusTranslations;
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

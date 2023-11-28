//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.01.31 at 04:39:46 PM IST 
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
 *         &lt;element name="PricePlanItemID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PricePlanItemParentID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PricePlanVersionID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ItemName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ItemDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PricePlanItemTypeID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChargeTypeID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ValidationDataTypeID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ValidationDataType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ValidationExpressionID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ValidationExpression" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}ValidationValue"/>
 *         &lt;element name="ItemAmount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="ItemPercent" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="IsEnabled" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="LastUpdatedByUserID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CreatedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="UpdatedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ChargeTypeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}ValidationExpressions" maxOccurs="unbounded"/>
 *         &lt;element ref="{}PricePlanItemTypes" maxOccurs="unbounded"/>
 *         &lt;element ref="{}ValidationDataTypes" maxOccurs="unbounded"/>
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
    "pricePlanItemID",
    "pricePlanItemParentID",
    "pricePlanVersionID",
    "itemName",
    "itemDescription",
    "pricePlanItemTypeID",
    "chargeTypeID",
    "validationDataTypeID",
    "validationDataType",
    "validationExpressionID",
    "validationExpression",
    "validationValue",
    "itemAmount",
    "itemPercent",
    "isEnabled",
    "lastUpdatedByUserID",
    "createdDate",
    "updatedDate",
    "chargeTypeName",
    "validationExpressions",
    "pricePlanItemTypes",
    "validationDataTypes"
})
@XmlRootElement(name = "PricePlanItems")
public class PricePlanItems {

    @XmlElement(name = "PricePlanItemID", required = true)
    protected String pricePlanItemID;
    @XmlElement(name = "PricePlanItemParentID", required = true)
    protected String pricePlanItemParentID;
    @XmlElement(name = "PricePlanVersionID", required = true)
    protected String pricePlanVersionID;
    @XmlElement(name = "ItemName", required = true)
    protected String itemName;
    @XmlElement(name = "ItemDescription", required = true)
    protected String itemDescription;
    @XmlElement(name = "PricePlanItemTypeID", required = true)
    protected String pricePlanItemTypeID;
    @XmlElement(name = "ChargeTypeID", required = true)
    protected String chargeTypeID;
    @XmlElement(name = "ValidationDataTypeID")
    protected int validationDataTypeID;
    @XmlElement(name = "ValidationDataType", required = true)
    protected String validationDataType;
    @XmlElement(name = "ValidationExpressionID")
    protected int validationExpressionID;
    @XmlElement(name = "ValidationExpression", required = true)
    protected String validationExpression;
    @XmlElement(name = "ValidationValue", required = true)
    protected ValidationValue validationValue;
    @XmlElement(name = "ItemAmount")
    protected float itemAmount;
    @XmlElement(name = "ItemPercent")
    protected float itemPercent;
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
    @XmlElement(name = "ChargeTypeName", required = true)
    protected String chargeTypeName;
    @XmlElement(name = "ValidationExpressions", required = true)
    protected List<ValidationExpressions> validationExpressions;
    @XmlElement(name = "PricePlanItemTypes", required = true)
    protected List<PricePlanItemTypes> pricePlanItemTypes;
    @XmlElement(name = "ValidationDataTypes", required = true)
    protected List<ValidationDataTypes> validationDataTypes;

    /**
     * Gets the value of the pricePlanItemID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricePlanItemID() {
        return pricePlanItemID;
    }

    /**
     * Sets the value of the pricePlanItemID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricePlanItemID(String value) {
        this.pricePlanItemID = value;
    }

    /**
     * Gets the value of the pricePlanItemParentID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricePlanItemParentID() {
        return pricePlanItemParentID;
    }

    /**
     * Sets the value of the pricePlanItemParentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricePlanItemParentID(String value) {
        this.pricePlanItemParentID = value;
    }

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
     * Gets the value of the itemDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * Sets the value of the itemDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemDescription(String value) {
        this.itemDescription = value;
    }

    /**
     * Gets the value of the pricePlanItemTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricePlanItemTypeID() {
        return pricePlanItemTypeID;
    }

    /**
     * Sets the value of the pricePlanItemTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricePlanItemTypeID(String value) {
        this.pricePlanItemTypeID = value;
    }

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
     * Gets the value of the validationDataTypeID property.
     * 
     */
    public int getValidationDataTypeID() {
        return validationDataTypeID;
    }

    /**
     * Sets the value of the validationDataTypeID property.
     * 
     */
    public void setValidationDataTypeID(int value) {
        this.validationDataTypeID = value;
    }

    /**
     * Gets the value of the validationDataType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidationDataType() {
        return validationDataType;
    }

    /**
     * Sets the value of the validationDataType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidationDataType(String value) {
        this.validationDataType = value;
    }

    /**
     * Gets the value of the validationExpressionID property.
     * 
     */
    public int getValidationExpressionID() {
        return validationExpressionID;
    }

    /**
     * Sets the value of the validationExpressionID property.
     * 
     */
    public void setValidationExpressionID(int value) {
        this.validationExpressionID = value;
    }

    /**
     * Gets the value of the validationExpression property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidationExpression() {
        return validationExpression;
    }

    /**
     * Sets the value of the validationExpression property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidationExpression(String value) {
        this.validationExpression = value;
    }

    /**
     * Gets the value of the validationValue property.
     * 
     * @return
     *     possible object is
     *     {@link ValidationValue }
     *     
     */
    public ValidationValue getValidationValue() {
        return validationValue;
    }

    /**
     * Sets the value of the validationValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidationValue }
     *     
     */
    public void setValidationValue(ValidationValue value) {
        this.validationValue = value;
    }

    /**
     * Gets the value of the itemAmount property.
     * 
     */
    public float getItemAmount() {
        return itemAmount;
    }

    /**
     * Sets the value of the itemAmount property.
     * 
     */
    public void setItemAmount(float value) {
        this.itemAmount = value;
    }

    /**
     * Gets the value of the itemPercent property.
     * 
     */
    public float getItemPercent() {
        return itemPercent;
    }

    /**
     * Sets the value of the itemPercent property.
     * 
     */
    public void setItemPercent(float value) {
        this.itemPercent = value;
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
     * Gets the value of the validationExpressions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the validationExpressions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValidationExpressions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValidationExpressions }
     * 
     * 
     */
    public List<ValidationExpressions> getValidationExpressions() {
        if (validationExpressions == null) {
            validationExpressions = new ArrayList<ValidationExpressions>();
        }
        return this.validationExpressions;
    }

    /**
     * Gets the value of the pricePlanItemTypes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pricePlanItemTypes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPricePlanItemTypes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PricePlanItemTypes }
     * 
     * 
     */
    public List<PricePlanItemTypes> getPricePlanItemTypes() {
        if (pricePlanItemTypes == null) {
            pricePlanItemTypes = new ArrayList<PricePlanItemTypes>();
        }
        return this.pricePlanItemTypes;
    }

    /**
     * Gets the value of the validationDataTypes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the validationDataTypes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValidationDataTypes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValidationDataTypes }
     * 
     * 
     */
    public List<ValidationDataTypes> getValidationDataTypes() {
        if (validationDataTypes == null) {
            validationDataTypes = new ArrayList<ValidationDataTypes>();
        }
        return this.validationDataTypes;
    }

}
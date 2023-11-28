//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.05.30 at 04:04:23 PM IST 
//


package se.info24.priceplansjaxb;

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
 *         &lt;element name="PricePlanID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PricePlanName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PricePlanNameTCMV3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PricePlanDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GroupID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GroupName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PricePlanTypeID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ActiveFromDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ActiveToDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ActiveFromDateTCMV3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ActiveToDateTCMV3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsEnabled" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="LastUpdatedByUserID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CreatedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="UpdatedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element ref="{}PricePlanTypes" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}PricePlanVersions" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}ChargeTypes" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}UserAliasTypes" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Products" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}ProductCategories" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}ProductVariants" maxOccurs="unbounded" minOccurs="0"/>
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
    "pricePlanID",
    "pricePlanName",
    "pricePlanNameTCMV3",
    "pricePlanDescription",
    "groupID",
    "groupName",
    "pricePlanTypeID",
    "activeFromDate",
    "activeToDate",
    "activeFromDateTCMV3",
    "activeToDateTCMV3",
    "isEnabled",
    "lastUpdatedByUserID",
    "createdDate",
    "updatedDate",
    "pricePlanTypes",
    "pricePlanVersions",
    "chargeTypes",
    "userAliasTypes",
    "products",
    "productCategories",
    "productVariants"
})
@XmlRootElement(name = "PricePlan")
public class PricePlan {

    @XmlElement(name = "PricePlanID", required = true)
    protected String pricePlanID;
    @XmlElement(name = "PricePlanName", required = true)
    protected String pricePlanName;
    @XmlElement(name = "PricePlanNameTCMV3", required = true)
    protected String pricePlanNameTCMV3;
    @XmlElement(name = "PricePlanDescription", required = true)
    protected String pricePlanDescription;
    @XmlElement(name = "GroupID", required = true)
    protected String groupID;
    @XmlElement(name = "GroupName", required = true)
    protected String groupName;
    @XmlElement(name = "PricePlanTypeID", required = true)
    protected String pricePlanTypeID;
    @XmlElement(name = "ActiveFromDate", required = true)
    protected String activeFromDate;
    @XmlElement(name = "ActiveToDate", required = true)
    protected String activeToDate;
    @XmlElement(name = "ActiveFromDateTCMV3", required = true)
    protected String activeFromDateTCMV3;
    @XmlElement(name = "ActiveToDateTCMV3", required = true)
    protected String activeToDateTCMV3;
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
    @XmlElement(name = "PricePlanTypes")
    protected List<PricePlanTypes> pricePlanTypes;
    @XmlElement(name = "PricePlanVersions")
    protected List<PricePlanVersions> pricePlanVersions;
    @XmlElement(name = "ChargeTypes")
    protected List<ChargeTypes> chargeTypes;
    @XmlElement(name = "UserAliasTypes")
    protected List<UserAliasTypes> userAliasTypes;
    @XmlElement(name = "Products")
    protected List<Products> products;
    @XmlElement(name = "ProductCategories")
    protected List<ProductCategories> productCategories;
    @XmlElement(name = "ProductVariants")
    protected List<ProductVariants> productVariants;
    @XmlAttribute(name = "SeqNo")
    protected Integer seqNo;

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
     * Gets the value of the pricePlanNameTCMV3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricePlanNameTCMV3() {
        return pricePlanNameTCMV3;
    }

    /**
     * Sets the value of the pricePlanNameTCMV3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricePlanNameTCMV3(String value) {
        this.pricePlanNameTCMV3 = value;
    }

    /**
     * Gets the value of the pricePlanDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricePlanDescription() {
        return pricePlanDescription;
    }

    /**
     * Sets the value of the pricePlanDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricePlanDescription(String value) {
        this.pricePlanDescription = value;
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
     * Gets the value of the groupName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Sets the value of the groupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupName(String value) {
        this.groupName = value;
    }

    /**
     * Gets the value of the pricePlanTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricePlanTypeID() {
        return pricePlanTypeID;
    }

    /**
     * Sets the value of the pricePlanTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricePlanTypeID(String value) {
        this.pricePlanTypeID = value;
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
     * Gets the value of the pricePlanTypes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pricePlanTypes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPricePlanTypes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PricePlanTypes }
     * 
     * 
     */
    public List<PricePlanTypes> getPricePlanTypes() {
        if (pricePlanTypes == null) {
            pricePlanTypes = new ArrayList<PricePlanTypes>();
        }
        return this.pricePlanTypes;
    }

    /**
     * Gets the value of the pricePlanVersions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pricePlanVersions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPricePlanVersions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PricePlanVersions }
     * 
     * 
     */
    public List<PricePlanVersions> getPricePlanVersions() {
        if (pricePlanVersions == null) {
            pricePlanVersions = new ArrayList<PricePlanVersions>();
        }
        return this.pricePlanVersions;
    }

    /**
     * Gets the value of the chargeTypes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the chargeTypes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChargeTypes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChargeTypes }
     * 
     * 
     */
    public List<ChargeTypes> getChargeTypes() {
        if (chargeTypes == null) {
            chargeTypes = new ArrayList<ChargeTypes>();
        }
        return this.chargeTypes;
    }

    /**
     * Gets the value of the userAliasTypes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userAliasTypes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserAliasTypes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserAliasTypes }
     * 
     * 
     */
    public List<UserAliasTypes> getUserAliasTypes() {
        if (userAliasTypes == null) {
            userAliasTypes = new ArrayList<UserAliasTypes>();
        }
        return this.userAliasTypes;
    }

    /**
     * Gets the value of the products property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the products property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProducts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Products }
     * 
     * 
     */
    public List<Products> getProducts() {
        if (products == null) {
            products = new ArrayList<Products>();
        }
        return this.products;
    }

    /**
     * Gets the value of the productCategories property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productCategories property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductCategories().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductCategories }
     * 
     * 
     */
    public List<ProductCategories> getProductCategories() {
        if (productCategories == null) {
            productCategories = new ArrayList<ProductCategories>();
        }
        return this.productCategories;
    }

    /**
     * Gets the value of the productVariants property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productVariants property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductVariants().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductVariants }
     * 
     * 
     */
    public List<ProductVariants> getProductVariants() {
        if (productVariants == null) {
            productVariants = new ArrayList<ProductVariants>();
        }
        return this.productVariants;
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
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.12 at 01:01:41 PM IST 
//


package se.info24.containersjaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element name="ProductVariantID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ProductID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsDefault" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IsEnabled" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DisplayInWebShop" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Deleted" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ServiceID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SearchHitsIncluded" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="NumberOfAccessDaysIncluded" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ProductVariantSKU" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "productVariantID",
    "productID",
    "isDefault",
    "isEnabled",
    "displayInWebShop",
    "deleted",
    "serviceID",
    "searchHitsIncluded",
    "numberOfAccessDaysIncluded",
    "productVariantSKU"
})
@XmlRootElement(name = "ProductVariants")
public class ProductVariants {

    @XmlElement(name = "ProductVariantID", required = true)
    protected String productVariantID;
    @XmlElement(name = "ProductID", required = true)
    protected String productID;
    @XmlElement(name = "IsDefault")
    protected int isDefault;
    @XmlElement(name = "IsEnabled")
    protected int isEnabled;
    @XmlElement(name = "DisplayInWebShop")
    protected int displayInWebShop;
    @XmlElement(name = "Deleted")
    protected int deleted;
    @XmlElement(name = "ServiceID", required = true)
    protected String serviceID;
    @XmlElement(name = "SearchHitsIncluded")
    protected int searchHitsIncluded;
    @XmlElement(name = "NumberOfAccessDaysIncluded")
    protected int numberOfAccessDaysIncluded;
    @XmlElement(name = "ProductVariantSKU", required = true)
    protected String productVariantSKU;
    @XmlAttribute(name = "SeqNo")
    protected Integer seqNo;

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
     * Gets the value of the productID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductID() {
        return productID;
    }

    /**
     * Sets the value of the productID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductID(String value) {
        this.productID = value;
    }

    /**
     * Gets the value of the isDefault property.
     * 
     */
    public int getIsDefault() {
        return isDefault;
    }

    /**
     * Sets the value of the isDefault property.
     * 
     */
    public void setIsDefault(int value) {
        this.isDefault = value;
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
     * Gets the value of the displayInWebShop property.
     * 
     */
    public int getDisplayInWebShop() {
        return displayInWebShop;
    }

    /**
     * Sets the value of the displayInWebShop property.
     * 
     */
    public void setDisplayInWebShop(int value) {
        this.displayInWebShop = value;
    }

    /**
     * Gets the value of the deleted property.
     * 
     */
    public int getDeleted() {
        return deleted;
    }

    /**
     * Sets the value of the deleted property.
     * 
     */
    public void setDeleted(int value) {
        this.deleted = value;
    }

    /**
     * Gets the value of the serviceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceID() {
        return serviceID;
    }

    /**
     * Sets the value of the serviceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceID(String value) {
        this.serviceID = value;
    }

    /**
     * Gets the value of the searchHitsIncluded property.
     * 
     */
    public int getSearchHitsIncluded() {
        return searchHitsIncluded;
    }

    /**
     * Sets the value of the searchHitsIncluded property.
     * 
     */
    public void setSearchHitsIncluded(int value) {
        this.searchHitsIncluded = value;
    }

    /**
     * Gets the value of the numberOfAccessDaysIncluded property.
     * 
     */
    public int getNumberOfAccessDaysIncluded() {
        return numberOfAccessDaysIncluded;
    }

    /**
     * Sets the value of the numberOfAccessDaysIncluded property.
     * 
     */
    public void setNumberOfAccessDaysIncluded(int value) {
        this.numberOfAccessDaysIncluded = value;
    }

    /**
     * Gets the value of the productVariantSKU property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductVariantSKU() {
        return productVariantSKU;
    }

    /**
     * Sets the value of the productVariantSKU property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductVariantSKU(String value) {
        this.productVariantSKU = value;
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
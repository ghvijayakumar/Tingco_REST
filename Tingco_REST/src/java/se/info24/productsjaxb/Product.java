//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.06 at 06:26:11 PM IST 
//


package se.info24.productsjaxb;

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
 *         &lt;element name="ProductID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsEnabled" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DisplayInWebShop" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IsDownloadProduct" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IsEnabledTCMV3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DisplayInWebShopTCMV3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsDownloadProductTCMV3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ProductTypeID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ProductTypeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ProductCategoryID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsDeleted" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ExtendedMembershipRequired" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="GroupID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CreatedDateTCMV3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FullName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}UserID"/>
 *         &lt;element ref="{}CreatedDate"/>
 *         &lt;element ref="{}UpdatedDate"/>
 *         &lt;element ref="{}ProductTranslations" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}ProductAttributes" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}ProductVariants" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}ServiceSubscriptionACL" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}ServiceClientLogins" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}ObjectImages" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}ContentCategories" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}ProductCategories" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}GroupLimitPackages" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}ProductVariantDevices" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}ObjectProductVariantPrices" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Providers" maxOccurs="unbounded" minOccurs="0"/>
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
    "productID",
    "isEnabled",
    "displayInWebShop",
    "isDownloadProduct",
    "isEnabledTCMV3",
    "displayInWebShopTCMV3",
    "isDownloadProductTCMV3",
    "productTypeID",
    "productTypeName",
    "productCategoryID",
    "isDeleted",
    "extendedMembershipRequired",
    "groupID",
    "createdDateTCMV3",
    "fullName",
    "userID",
    "createdDate",
    "updatedDate",
    "productTranslations",
    "productAttributes",
    "productVariants",
    "serviceSubscriptionACL",
    "serviceClientLogins",
    "objectImages",
    "contentCategories",
    "productCategories",
    "groupLimitPackages",
    "productVariantDevices",
    "objectProductVariantPrices",
    "providers"
})
@XmlRootElement(name = "Product")
public class Product {

    @XmlElement(name = "ProductID", required = true)
    protected String productID;
    @XmlElement(name = "IsEnabled")
    protected int isEnabled;
    @XmlElement(name = "DisplayInWebShop")
    protected int displayInWebShop;
    @XmlElement(name = "IsDownloadProduct")
    protected int isDownloadProduct;
    @XmlElement(name = "IsEnabledTCMV3", required = true)
    protected String isEnabledTCMV3;
    @XmlElement(name = "DisplayInWebShopTCMV3", required = true)
    protected String displayInWebShopTCMV3;
    @XmlElement(name = "IsDownloadProductTCMV3", required = true)
    protected String isDownloadProductTCMV3;
    @XmlElement(name = "ProductTypeID", required = true)
    protected String productTypeID;
    @XmlElement(name = "ProductTypeName", required = true)
    protected String productTypeName;
    @XmlElement(name = "ProductCategoryID", required = true)
    protected String productCategoryID;
    @XmlElement(name = "IsDeleted")
    protected int isDeleted;
    @XmlElement(name = "ExtendedMembershipRequired")
    protected int extendedMembershipRequired;
    @XmlElement(name = "GroupID", required = true)
    protected String groupID;
    @XmlElement(name = "CreatedDateTCMV3", required = true)
    protected String createdDateTCMV3;
    @XmlElement(name = "FullName", required = true)
    protected String fullName;
    @XmlElement(name = "UserID", required = true)
    protected String userID;
    @XmlElement(name = "CreatedDate", required = true)
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "UpdatedDate", required = true)
    protected XMLGregorianCalendar updatedDate;
    @XmlElement(name = "ProductTranslations")
    protected List<ProductTranslations> productTranslations;
    @XmlElement(name = "ProductAttributes")
    protected List<ProductAttributes> productAttributes;
    @XmlElement(name = "ProductVariants")
    protected List<ProductVariants> productVariants;
    @XmlElement(name = "ServiceSubscriptionACL")
    protected List<ServiceSubscriptionACL> serviceSubscriptionACL;
    @XmlElement(name = "ServiceClientLogins")
    protected List<ServiceClientLogins> serviceClientLogins;
    @XmlElement(name = "ObjectImages")
    protected List<ObjectImages> objectImages;
    @XmlElement(name = "ContentCategories")
    protected List<ContentCategories> contentCategories;
    @XmlElement(name = "ProductCategories")
    protected List<ProductCategories> productCategories;
    @XmlElement(name = "GroupLimitPackages")
    protected List<GroupLimitPackages> groupLimitPackages;
    @XmlElement(name = "ProductVariantDevices")
    protected List<ProductVariantDevices> productVariantDevices;
    @XmlElement(name = "ObjectProductVariantPrices")
    protected List<ObjectProductVariantPrices> objectProductVariantPrices;
    @XmlElement(name = "Providers")
    protected List<Providers> providers;
    @XmlAttribute(name = "SeqNo")
    protected Integer seqNo;

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
     * Gets the value of the isDownloadProduct property.
     * 
     */
    public int getIsDownloadProduct() {
        return isDownloadProduct;
    }

    /**
     * Sets the value of the isDownloadProduct property.
     * 
     */
    public void setIsDownloadProduct(int value) {
        this.isDownloadProduct = value;
    }

    /**
     * Gets the value of the isEnabledTCMV3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsEnabledTCMV3() {
        return isEnabledTCMV3;
    }

    /**
     * Sets the value of the isEnabledTCMV3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsEnabledTCMV3(String value) {
        this.isEnabledTCMV3 = value;
    }

    /**
     * Gets the value of the displayInWebShopTCMV3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayInWebShopTCMV3() {
        return displayInWebShopTCMV3;
    }

    /**
     * Sets the value of the displayInWebShopTCMV3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayInWebShopTCMV3(String value) {
        this.displayInWebShopTCMV3 = value;
    }

    /**
     * Gets the value of the isDownloadProductTCMV3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsDownloadProductTCMV3() {
        return isDownloadProductTCMV3;
    }

    /**
     * Sets the value of the isDownloadProductTCMV3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsDownloadProductTCMV3(String value) {
        this.isDownloadProductTCMV3 = value;
    }

    /**
     * Gets the value of the productTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductTypeID() {
        return productTypeID;
    }

    /**
     * Sets the value of the productTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductTypeID(String value) {
        this.productTypeID = value;
    }

    /**
     * Gets the value of the productTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductTypeName() {
        return productTypeName;
    }

    /**
     * Sets the value of the productTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductTypeName(String value) {
        this.productTypeName = value;
    }

    /**
     * Gets the value of the productCategoryID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCategoryID() {
        return productCategoryID;
    }

    /**
     * Sets the value of the productCategoryID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCategoryID(String value) {
        this.productCategoryID = value;
    }

    /**
     * Gets the value of the isDeleted property.
     * 
     */
    public int getIsDeleted() {
        return isDeleted;
    }

    /**
     * Sets the value of the isDeleted property.
     * 
     */
    public void setIsDeleted(int value) {
        this.isDeleted = value;
    }

    /**
     * Gets the value of the extendedMembershipRequired property.
     * 
     */
    public int getExtendedMembershipRequired() {
        return extendedMembershipRequired;
    }

    /**
     * Sets the value of the extendedMembershipRequired property.
     * 
     */
    public void setExtendedMembershipRequired(int value) {
        this.extendedMembershipRequired = value;
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
     * Gets the value of the createdDateTCMV3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedDateTCMV3() {
        return createdDateTCMV3;
    }

    /**
     * Sets the value of the createdDateTCMV3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedDateTCMV3(String value) {
        this.createdDateTCMV3 = value;
    }

    /**
     * Gets the value of the fullName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the value of the fullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFullName(String value) {
        this.fullName = value;
    }

    /**
     * Gets the value of the userID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the value of the userID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserID(String value) {
        this.userID = value;
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
     * Gets the value of the productTranslations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productTranslations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductTranslations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductTranslations }
     * 
     * 
     */
    public List<ProductTranslations> getProductTranslations() {
        if (productTranslations == null) {
            productTranslations = new ArrayList<ProductTranslations>();
        }
        return this.productTranslations;
    }

    /**
     * Gets the value of the productAttributes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productAttributes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductAttributes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductAttributes }
     * 
     * 
     */
    public List<ProductAttributes> getProductAttributes() {
        if (productAttributes == null) {
            productAttributes = new ArrayList<ProductAttributes>();
        }
        return this.productAttributes;
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
     * Gets the value of the serviceSubscriptionACL property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceSubscriptionACL property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceSubscriptionACL().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceSubscriptionACL }
     * 
     * 
     */
    public List<ServiceSubscriptionACL> getServiceSubscriptionACL() {
        if (serviceSubscriptionACL == null) {
            serviceSubscriptionACL = new ArrayList<ServiceSubscriptionACL>();
        }
        return this.serviceSubscriptionACL;
    }

    /**
     * Gets the value of the serviceClientLogins property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceClientLogins property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceClientLogins().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceClientLogins }
     * 
     * 
     */
    public List<ServiceClientLogins> getServiceClientLogins() {
        if (serviceClientLogins == null) {
            serviceClientLogins = new ArrayList<ServiceClientLogins>();
        }
        return this.serviceClientLogins;
    }

    /**
     * Gets the value of the objectImages property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the objectImages property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObjectImages().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectImages }
     * 
     * 
     */
    public List<ObjectImages> getObjectImages() {
        if (objectImages == null) {
            objectImages = new ArrayList<ObjectImages>();
        }
        return this.objectImages;
    }

    /**
     * Gets the value of the contentCategories property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contentCategories property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContentCategories().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContentCategories }
     * 
     * 
     */
    public List<ContentCategories> getContentCategories() {
        if (contentCategories == null) {
            contentCategories = new ArrayList<ContentCategories>();
        }
        return this.contentCategories;
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
     * Gets the value of the groupLimitPackages property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the groupLimitPackages property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGroupLimitPackages().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GroupLimitPackages }
     * 
     * 
     */
    public List<GroupLimitPackages> getGroupLimitPackages() {
        if (groupLimitPackages == null) {
            groupLimitPackages = new ArrayList<GroupLimitPackages>();
        }
        return this.groupLimitPackages;
    }

    /**
     * Gets the value of the productVariantDevices property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productVariantDevices property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductVariantDevices().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductVariantDevices }
     * 
     * 
     */
    public List<ProductVariantDevices> getProductVariantDevices() {
        if (productVariantDevices == null) {
            productVariantDevices = new ArrayList<ProductVariantDevices>();
        }
        return this.productVariantDevices;
    }

    /**
     * Gets the value of the objectProductVariantPrices property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the objectProductVariantPrices property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObjectProductVariantPrices().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectProductVariantPrices }
     * 
     * 
     */
    public List<ObjectProductVariantPrices> getObjectProductVariantPrices() {
        if (objectProductVariantPrices == null) {
            objectProductVariantPrices = new ArrayList<ObjectProductVariantPrices>();
        }
        return this.objectProductVariantPrices;
    }

    /**
     * Gets the value of the providers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the providers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProviders().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Providers }
     * 
     * 
     */
    public List<Providers> getProviders() {
        if (providers == null) {
            providers = new ArrayList<Providers>();
        }
        return this.providers;
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

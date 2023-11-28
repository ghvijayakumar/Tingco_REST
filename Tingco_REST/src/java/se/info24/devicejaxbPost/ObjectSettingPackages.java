//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.09.13 at 11:25:29 AM IST 
//


package se.info24.devicejaxbPost;

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
 *         &lt;element name="ObjectSettingPackageID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ObjectTypeID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LastUpdatedByUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}CreatedDate"/>
 *         &lt;element ref="{}UpdatedDate"/>
 *         &lt;element ref="{}ObjectSettingPackageTranslations"/>
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
    "objectSettingPackageID",
    "objectTypeID",
    "lastUpdatedByUser",
    "createdDate",
    "updatedDate",
    "objectSettingPackageTranslations"
})
@XmlRootElement(name = "ObjectSettingPackages")
public class ObjectSettingPackages {

    @XmlElement(name = "ObjectSettingPackageID", required = true)
    protected String objectSettingPackageID;
    @XmlElement(name = "ObjectTypeID", required = true)
    protected String objectTypeID;
    @XmlElement(name = "LastUpdatedByUser", required = true)
    protected String lastUpdatedByUser;
    @XmlElement(name = "CreatedDate", required = true)
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "UpdatedDate", required = true)
    protected XMLGregorianCalendar updatedDate;
    @XmlElement(name = "ObjectSettingPackageTranslations", required = true)
    protected ObjectSettingPackageTranslations objectSettingPackageTranslations;
    @XmlAttribute(name = "SeqNo", required = true)
    protected int seqNo;

    /**
     * Gets the value of the objectSettingPackageID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectSettingPackageID() {
        return objectSettingPackageID;
    }

    /**
     * Sets the value of the objectSettingPackageID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectSettingPackageID(String value) {
        this.objectSettingPackageID = value;
    }

    /**
     * Gets the value of the objectTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectTypeID() {
        return objectTypeID;
    }

    /**
     * Sets the value of the objectTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectTypeID(String value) {
        this.objectTypeID = value;
    }

    /**
     * Gets the value of the lastUpdatedByUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastUpdatedByUser() {
        return lastUpdatedByUser;
    }

    /**
     * Sets the value of the lastUpdatedByUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastUpdatedByUser(String value) {
        this.lastUpdatedByUser = value;
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
     * Gets the value of the objectSettingPackageTranslations property.
     * 
     * @return
     *     possible object is
     *     {@link ObjectSettingPackageTranslations }
     *     
     */
    public ObjectSettingPackageTranslations getObjectSettingPackageTranslations() {
        return objectSettingPackageTranslations;
    }

    /**
     * Sets the value of the objectSettingPackageTranslations property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectSettingPackageTranslations }
     *     
     */
    public void setObjectSettingPackageTranslations(ObjectSettingPackageTranslations value) {
        this.objectSettingPackageTranslations = value;
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

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.10.20 at 12:56:42 PM IST 
//


package se.info24.contentjaxb;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="ContentAttributeId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CDMFieldName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CDMDataGroupName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Unit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}ContentAttribute" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Language" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="AttributeGroup" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
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
    "contentAttributeId",
    "cdmFieldName",
    "cdmDataGroupName",
    "unit",
    "contentAttribute"
})
@XmlRootElement(name = "Attribute")
public class Attribute {

    @XmlElement(name = "ContentAttributeId", required = true)
    protected String contentAttributeId;
    @XmlElement(name = "CDMFieldName", required = true)
    protected String cdmFieldName;
    @XmlElement(name = "CDMDataGroupName", required = true)
    protected String cdmDataGroupName;
    @XmlElement(name = "Unit", required = true)
    protected String unit;
    @XmlElement(name = "ContentAttribute")
    protected List<ContentAttribute> contentAttribute;
    @XmlAttribute(name = "Language", required = true)
    protected String language;
    @XmlAttribute(name = "AttributeGroup", required = true)
    protected String attributeGroup;

    /**
     * Gets the value of the contentAttributeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentAttributeId() {
        return contentAttributeId;
    }

    /**
     * Sets the value of the contentAttributeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentAttributeId(String value) {
        this.contentAttributeId = value;
    }

    /**
     * Gets the value of the cdmFieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCDMFieldName() {
        return cdmFieldName;
    }

    /**
     * Sets the value of the cdmFieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCDMFieldName(String value) {
        this.cdmFieldName = value;
    }

    /**
     * Gets the value of the cdmDataGroupName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCDMDataGroupName() {
        return cdmDataGroupName;
    }

    /**
     * Sets the value of the cdmDataGroupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCDMDataGroupName(String value) {
        this.cdmDataGroupName = value;
    }

    /**
     * Gets the value of the unit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets the value of the unit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnit(String value) {
        this.unit = value;
    }

    /**
     * Gets the value of the contentAttribute property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contentAttribute property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContentAttribute().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContentAttribute }
     * 
     * 
     */
    public List<ContentAttribute> getContentAttribute() {
        if (contentAttribute == null) {
            contentAttribute = new ArrayList<ContentAttribute>();
        }
        return this.contentAttribute;
    }

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * Gets the value of the attributeGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttributeGroup() {
        return attributeGroup;
    }

    /**
     * Sets the value of the attributeGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttributeGroup(String value) {
        this.attributeGroup = value;
    }

}
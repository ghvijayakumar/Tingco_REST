//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.10.20 at 12:56:42 PM IST 
//


package se.info24.contentjaxb;

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
 *         &lt;element name="ContentGroupID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContentGroupName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContentGroupDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "contentGroupID",
    "contentGroupName",
    "contentGroupDescription",
    "createdDate",
    "updatedDate"
})
@XmlRootElement(name = "ContentGroups")
public class ContentGroups {

    @XmlElement(name = "ContentGroupID", required = true)
    protected String contentGroupID;
    @XmlElement(name = "ContentGroupName", required = true)
    protected String contentGroupName;
    @XmlElement(name = "ContentGroupDescription", required = true)
    protected String contentGroupDescription;
    @XmlElement(name = "CreatedDate", required = true)
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "UpdatedDate", required = true)
    protected XMLGregorianCalendar updatedDate;

    /**
     * Gets the value of the contentGroupID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentGroupID() {
        return contentGroupID;
    }

    /**
     * Sets the value of the contentGroupID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentGroupID(String value) {
        this.contentGroupID = value;
    }

    /**
     * Gets the value of the contentGroupName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentGroupName() {
        return contentGroupName;
    }

    /**
     * Sets the value of the contentGroupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentGroupName(String value) {
        this.contentGroupName = value;
    }

    /**
     * Gets the value of the contentGroupDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentGroupDescription() {
        return contentGroupDescription;
    }

    /**
     * Sets the value of the contentGroupDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentGroupDescription(String value) {
        this.contentGroupDescription = value;
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

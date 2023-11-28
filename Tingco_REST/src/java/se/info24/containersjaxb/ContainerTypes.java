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
 *         &lt;element name="ContainerTypeID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContainerTypeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContainerTypeDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ObjectCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}CreatedDate"/>
 *         &lt;element ref="{}UpdatedDate"/>
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
    "containerTypeID",
    "containerTypeName",
    "containerTypeDescription",
    "objectCode",
    "userID",
    "createdDate",
    "updatedDate"
})
@XmlRootElement(name = "ContainerTypes")
public class ContainerTypes {

    @XmlElement(name = "ContainerTypeID", required = true)
    protected String containerTypeID;
    @XmlElement(name = "ContainerTypeName", required = true)
    protected String containerTypeName;
    @XmlElement(name = "ContainerTypeDescription", required = true)
    protected String containerTypeDescription;
    @XmlElement(name = "ObjectCode", required = true)
    protected String objectCode;
    @XmlElement(name = "UserID", required = true)
    protected String userID;
    @XmlElement(name = "CreatedDate", required = true)
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "UpdatedDate", required = true)
    protected XMLGregorianCalendar updatedDate;
    @XmlAttribute(name = "SeqNo", required = true)
    protected int seqNo;

    /**
     * Gets the value of the containerTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContainerTypeID() {
        return containerTypeID;
    }

    /**
     * Sets the value of the containerTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContainerTypeID(String value) {
        this.containerTypeID = value;
    }

    /**
     * Gets the value of the containerTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContainerTypeName() {
        return containerTypeName;
    }

    /**
     * Sets the value of the containerTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContainerTypeName(String value) {
        this.containerTypeName = value;
    }

    /**
     * Gets the value of the containerTypeDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContainerTypeDescription() {
        return containerTypeDescription;
    }

    /**
     * Sets the value of the containerTypeDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContainerTypeDescription(String value) {
        this.containerTypeDescription = value;
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
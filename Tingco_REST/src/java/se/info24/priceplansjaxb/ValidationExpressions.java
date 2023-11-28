//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.01.31 at 04:39:46 PM IST 
//


package se.info24.priceplansjaxb;

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
 *         &lt;element name="ValidationExpressionID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ValidationExpression" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ValidationExpressionDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CreatedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="UpdatedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
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
    "validationExpressionID",
    "validationExpression",
    "validationExpressionDescription",
    "userID",
    "createdDate",
    "updatedDate"
})
@XmlRootElement(name = "ValidationExpressions")
public class ValidationExpressions {

    @XmlElement(name = "ValidationExpressionID")
    protected int validationExpressionID;
    @XmlElement(name = "ValidationExpression", required = true)
    protected String validationExpression;
    @XmlElement(name = "ValidationExpressionDescription", required = true)
    protected String validationExpressionDescription;
    @XmlElement(name = "UserID", required = true)
    protected String userID;
    @XmlElement(name = "CreatedDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "UpdatedDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updatedDate;

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
     * Gets the value of the validationExpressionDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidationExpressionDescription() {
        return validationExpressionDescription;
    }

    /**
     * Sets the value of the validationExpressionDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidationExpressionDescription(String value) {
        this.validationExpressionDescription = value;
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

}

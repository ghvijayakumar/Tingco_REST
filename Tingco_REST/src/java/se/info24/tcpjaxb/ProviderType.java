//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.03.12 at 12:01:32 PM IST 
//


package se.info24.tcpjaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="ProviderTypeID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ObjectCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}UserProviderTypeReferences"/>
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
    "providerTypeID",
    "objectCode",
    "userProviderTypeReferences"
})
@XmlRootElement(name = "ProviderType")
public class ProviderType {

    @XmlElement(name = "ProviderTypeID", required = true)
    protected String providerTypeID;
    @XmlElement(name = "ObjectCode", required = true)
    protected String objectCode;
    @XmlElement(name = "UserProviderTypeReferences", required = true)
    protected UserProviderTypeReferences userProviderTypeReferences;

    /**
     * Gets the value of the providerTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProviderTypeID() {
        return providerTypeID;
    }

    /**
     * Sets the value of the providerTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProviderTypeID(String value) {
        this.providerTypeID = value;
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
     * Gets the value of the userProviderTypeReferences property.
     * 
     * @return
     *     possible object is
     *     {@link UserProviderTypeReferences }
     *     
     */
    public UserProviderTypeReferences getUserProviderTypeReferences() {
        return userProviderTypeReferences;
    }

    /**
     * Sets the value of the userProviderTypeReferences property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserProviderTypeReferences }
     *     
     */
    public void setUserProviderTypeReferences(UserProviderTypeReferences value) {
        this.userProviderTypeReferences = value;
    }

}

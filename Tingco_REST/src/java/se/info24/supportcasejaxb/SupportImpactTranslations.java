//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.06.07 at 11:33:12 AM IST 
//


package se.info24.supportcasejaxb;

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
 *         &lt;element name="SupportCaseImpactID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CountryID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SupportCaseImpactName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SupportCaseImpactDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "supportCaseImpactID",
    "countryID",
    "supportCaseImpactName",
    "supportCaseImpactDescription"
})
@XmlRootElement(name = "SupportImpactTranslations")
public class SupportImpactTranslations {

    @XmlElement(name = "SupportCaseImpactID", required = true)
    protected String supportCaseImpactID;
    @XmlElement(name = "CountryID")
    protected int countryID;
    @XmlElement(name = "SupportCaseImpactName", required = true)
    protected String supportCaseImpactName;
    @XmlElement(name = "SupportCaseImpactDescription", required = true)
    protected String supportCaseImpactDescription;
    @XmlAttribute(name = "SeqNo")
    protected Integer seqNo;

    /**
     * Gets the value of the supportCaseImpactID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupportCaseImpactID() {
        return supportCaseImpactID;
    }

    /**
     * Sets the value of the supportCaseImpactID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupportCaseImpactID(String value) {
        this.supportCaseImpactID = value;
    }

    /**
     * Gets the value of the countryID property.
     * 
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * Sets the value of the countryID property.
     * 
     */
    public void setCountryID(int value) {
        this.countryID = value;
    }

    /**
     * Gets the value of the supportCaseImpactName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupportCaseImpactName() {
        return supportCaseImpactName;
    }

    /**
     * Sets the value of the supportCaseImpactName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupportCaseImpactName(String value) {
        this.supportCaseImpactName = value;
    }

    /**
     * Gets the value of the supportCaseImpactDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupportCaseImpactDescription() {
        return supportCaseImpactDescription;
    }

    /**
     * Sets the value of the supportCaseImpactDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupportCaseImpactDescription(String value) {
        this.supportCaseImpactDescription = value;
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
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.10.24 at 03:29:47 PM IST 
//


package se.info24.devicejaxb;

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
 *         &lt;element name="CommandID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CountryID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CommandName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CommandDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CommandButtonText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TargetString" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "commandID",
    "countryID",
    "commandName",
    "commandDescription",
    "commandButtonText",
    "targetString"
})
@XmlRootElement(name = "CommandTranslations")
public class CommandTranslations {

    @XmlElement(name = "CommandID", required = true)
    protected String commandID;
    @XmlElement(name = "CountryID")
    protected int countryID;
    @XmlElement(name = "CommandName", required = true)
    protected String commandName;
    @XmlElement(name = "CommandDescription", required = true)
    protected String commandDescription;
    @XmlElement(name = "CommandButtonText", required = true)
    protected String commandButtonText;
    @XmlElement(name = "TargetString", required = true)
    protected String targetString;

    /**
     * Gets the value of the commandID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommandID() {
        return commandID;
    }

    /**
     * Sets the value of the commandID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommandID(String value) {
        this.commandID = value;
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
     * Gets the value of the commandName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommandName() {
        return commandName;
    }

    /**
     * Sets the value of the commandName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommandName(String value) {
        this.commandName = value;
    }

    /**
     * Gets the value of the commandDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommandDescription() {
        return commandDescription;
    }

    /**
     * Sets the value of the commandDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommandDescription(String value) {
        this.commandDescription = value;
    }

    /**
     * Gets the value of the commandButtonText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommandButtonText() {
        return commandButtonText;
    }

    /**
     * Sets the value of the commandButtonText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommandButtonText(String value) {
        this.commandButtonText = value;
    }

    /**
     * Gets the value of the targetString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetString() {
        return targetString;
    }

    /**
     * Sets the value of the targetString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetString(String value) {
        this.targetString = value;
    }

}

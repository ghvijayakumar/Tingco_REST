//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.09.13 at 11:25:29 AM IST 
//


package se.info24.devicejaxbPost;

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
 *       &lt;sequence minOccurs="0">
 *         &lt;element name="AlarmLowValue" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="AlarmLowEventTypeID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AlarmLowEventTypeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AlarmHighValue" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="AlarmHighEventTypeID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AlarmHighEventTypeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "alarmLowValue",
    "alarmLowEventTypeID",
    "alarmLowEventTypeName",
    "alarmHighValue",
    "alarmHighEventTypeID",
    "alarmHighEventTypeName"
})
@XmlRootElement(name = "AlarmLevels")
public class AlarmLevels {

    @XmlElement(name = "AlarmLowValue")
    protected Double alarmLowValue;
    @XmlElement(name = "AlarmLowEventTypeID")
    protected String alarmLowEventTypeID;
    @XmlElement(name = "AlarmLowEventTypeName")
    protected String alarmLowEventTypeName;
    @XmlElement(name = "AlarmHighValue")
    protected Double alarmHighValue;
    @XmlElement(name = "AlarmHighEventTypeID")
    protected String alarmHighEventTypeID;
    @XmlElement(name = "AlarmHighEventTypeName")
    protected String alarmHighEventTypeName;

    /**
     * Gets the value of the alarmLowValue property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAlarmLowValue() {
        return alarmLowValue;
    }

    /**
     * Sets the value of the alarmLowValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAlarmLowValue(Double value) {
        this.alarmLowValue = value;
    }

    /**
     * Gets the value of the alarmLowEventTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlarmLowEventTypeID() {
        return alarmLowEventTypeID;
    }

    /**
     * Sets the value of the alarmLowEventTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlarmLowEventTypeID(String value) {
        this.alarmLowEventTypeID = value;
    }

    /**
     * Gets the value of the alarmLowEventTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlarmLowEventTypeName() {
        return alarmLowEventTypeName;
    }

    /**
     * Sets the value of the alarmLowEventTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlarmLowEventTypeName(String value) {
        this.alarmLowEventTypeName = value;
    }

    /**
     * Gets the value of the alarmHighValue property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAlarmHighValue() {
        return alarmHighValue;
    }

    /**
     * Sets the value of the alarmHighValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAlarmHighValue(Double value) {
        this.alarmHighValue = value;
    }

    /**
     * Gets the value of the alarmHighEventTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlarmHighEventTypeID() {
        return alarmHighEventTypeID;
    }

    /**
     * Sets the value of the alarmHighEventTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlarmHighEventTypeID(String value) {
        this.alarmHighEventTypeID = value;
    }

    /**
     * Gets the value of the alarmHighEventTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlarmHighEventTypeName() {
        return alarmHighEventTypeName;
    }

    /**
     * Sets the value of the alarmHighEventTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlarmHighEventTypeName(String value) {
        this.alarmHighEventTypeName = value;
    }

}

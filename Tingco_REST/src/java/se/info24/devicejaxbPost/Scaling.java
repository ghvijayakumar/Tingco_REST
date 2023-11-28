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
 *         &lt;element name="FlagScaleValue" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="RawMinValue" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="RawMaxValue" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ScaledMinValue" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ScaledMaxValue" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="FlagSetValueToMaxIfAboveMax" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="FlagSetValueToMinIfBelowMin" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="FlagSaveAsFixedValue" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="FixedValue" type="{http://www.w3.org/2001/XMLSchema}double"/>
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
    "flagScaleValue",
    "rawMinValue",
    "rawMaxValue",
    "scaledMinValue",
    "scaledMaxValue",
    "flagSetValueToMaxIfAboveMax",
    "flagSetValueToMinIfBelowMin",
    "flagSaveAsFixedValue",
    "fixedValue"
})
@XmlRootElement(name = "Scaling")
public class Scaling {

    @XmlElement(name = "FlagScaleValue")
    protected Integer flagScaleValue;
    @XmlElement(name = "RawMinValue")
    protected Double rawMinValue;
    @XmlElement(name = "RawMaxValue")
    protected Double rawMaxValue;
    @XmlElement(name = "ScaledMinValue")
    protected Double scaledMinValue;
    @XmlElement(name = "ScaledMaxValue")
    protected Double scaledMaxValue;
    @XmlElement(name = "FlagSetValueToMaxIfAboveMax")
    protected Integer flagSetValueToMaxIfAboveMax;
    @XmlElement(name = "FlagSetValueToMinIfBelowMin")
    protected Integer flagSetValueToMinIfBelowMin;
    @XmlElement(name = "FlagSaveAsFixedValue")
    protected Integer flagSaveAsFixedValue;
    @XmlElement(name = "FixedValue")
    protected Double fixedValue;

    /**
     * Gets the value of the flagScaleValue property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFlagScaleValue() {
        return flagScaleValue;
    }

    /**
     * Sets the value of the flagScaleValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFlagScaleValue(Integer value) {
        this.flagScaleValue = value;
    }

    /**
     * Gets the value of the rawMinValue property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getRawMinValue() {
        return rawMinValue;
    }

    /**
     * Sets the value of the rawMinValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRawMinValue(Double value) {
        this.rawMinValue = value;
    }

    /**
     * Gets the value of the rawMaxValue property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getRawMaxValue() {
        return rawMaxValue;
    }

    /**
     * Sets the value of the rawMaxValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRawMaxValue(Double value) {
        this.rawMaxValue = value;
    }

    /**
     * Gets the value of the scaledMinValue property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getScaledMinValue() {
        return scaledMinValue;
    }

    /**
     * Sets the value of the scaledMinValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setScaledMinValue(Double value) {
        this.scaledMinValue = value;
    }

    /**
     * Gets the value of the scaledMaxValue property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getScaledMaxValue() {
        return scaledMaxValue;
    }

    /**
     * Sets the value of the scaledMaxValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setScaledMaxValue(Double value) {
        this.scaledMaxValue = value;
    }

    /**
     * Gets the value of the flagSetValueToMaxIfAboveMax property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFlagSetValueToMaxIfAboveMax() {
        return flagSetValueToMaxIfAboveMax;
    }

    /**
     * Sets the value of the flagSetValueToMaxIfAboveMax property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFlagSetValueToMaxIfAboveMax(Integer value) {
        this.flagSetValueToMaxIfAboveMax = value;
    }

    /**
     * Gets the value of the flagSetValueToMinIfBelowMin property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFlagSetValueToMinIfBelowMin() {
        return flagSetValueToMinIfBelowMin;
    }

    /**
     * Sets the value of the flagSetValueToMinIfBelowMin property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFlagSetValueToMinIfBelowMin(Integer value) {
        this.flagSetValueToMinIfBelowMin = value;
    }

    /**
     * Gets the value of the flagSaveAsFixedValue property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFlagSaveAsFixedValue() {
        return flagSaveAsFixedValue;
    }

    /**
     * Sets the value of the flagSaveAsFixedValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFlagSaveAsFixedValue(Integer value) {
        this.flagSaveAsFixedValue = value;
    }

    /**
     * Gets the value of the fixedValue property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getFixedValue() {
        return fixedValue;
    }

    /**
     * Sets the value of the fixedValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setFixedValue(Double value) {
        this.fixedValue = value;
    }

}
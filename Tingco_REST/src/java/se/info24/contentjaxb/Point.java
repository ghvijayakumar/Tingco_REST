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
 *         &lt;element name="CoordinateSystem" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PosLatitude" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PosLongitude" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PosHeading" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PosDepth" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "coordinateSystem",
    "posLatitude",
    "posLongitude",
    "posHeading",
    "posDepth"
})
@XmlRootElement(name = "Point")
public class Point {

    @XmlElement(name = "CoordinateSystem", required = true)
    protected String coordinateSystem;
    @XmlElement(name = "PosLatitude", required = true)
    protected String posLatitude;
    @XmlElement(name = "PosLongitude", required = true)
    protected String posLongitude;
    @XmlElement(name = "PosHeading", required = true)
    protected String posHeading;
    @XmlElement(name = "PosDepth", required = true)
    protected String posDepth;

    /**
     * Gets the value of the coordinateSystem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoordinateSystem() {
        return coordinateSystem;
    }

    /**
     * Sets the value of the coordinateSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoordinateSystem(String value) {
        this.coordinateSystem = value;
    }

    /**
     * Gets the value of the posLatitude property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPosLatitude() {
        return posLatitude;
    }

    /**
     * Sets the value of the posLatitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosLatitude(String value) {
        this.posLatitude = value;
    }

    /**
     * Gets the value of the posLongitude property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPosLongitude() {
        return posLongitude;
    }

    /**
     * Sets the value of the posLongitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosLongitude(String value) {
        this.posLongitude = value;
    }

    /**
     * Gets the value of the posHeading property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPosHeading() {
        return posHeading;
    }

    /**
     * Sets the value of the posHeading property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosHeading(String value) {
        this.posHeading = value;
    }

    /**
     * Gets the value of the posDepth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPosDepth() {
        return posDepth;
    }

    /**
     * Sets the value of the posDepth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosDepth(String value) {
        this.posDepth = value;
    }

}
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.10.27 at 10:37:55 AM IST 
//


package se.info24.apidocJAXB;

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
 *         &lt;element ref="{}MethodName"/>
 *         &lt;element ref="{}MethodDescription"/>
 *         &lt;element ref="{}MethodParameters"/>
 *         &lt;element ref="{}MethodFormats"/>
 *         &lt;element ref="{}MethodHTTPRequests"/>
 *         &lt;element ref="{}MethodSupportedInAPIVersions"/>
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
    "methodName",
    "methodDescription",
    "methodParameters",
    "methodFormats",
    "methodHTTPRequests",
    "methodSupportedInAPIVersions"
})
@XmlRootElement(name = "Method")
public class Method {

    @XmlElement(name = "MethodName", required = true)
    protected String methodName;
    @XmlElement(name = "MethodDescription", required = true)
    protected String methodDescription;
    @XmlElement(name = "MethodParameters", required = true)
    protected MethodParameters methodParameters;
    @XmlElement(name = "MethodFormats", required = true)
    protected MethodFormats methodFormats;
    @XmlElement(name = "MethodHTTPRequests", required = true)
    protected MethodHTTPRequests methodHTTPRequests;
    @XmlElement(name = "MethodSupportedInAPIVersions", required = true)
    protected MethodSupportedInAPIVersions methodSupportedInAPIVersions;

    /**
     * Gets the value of the methodName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Sets the value of the methodName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMethodName(String value) {
        this.methodName = value;
    }

    /**
     * Gets the value of the methodDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMethodDescription() {
        return methodDescription;
    }

    /**
     * Sets the value of the methodDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMethodDescription(String value) {
        this.methodDescription = value;
    }

    /**
     * Gets the value of the methodParameters property.
     * 
     * @return
     *     possible object is
     *     {@link MethodParameters }
     *     
     */
    public MethodParameters getMethodParameters() {
        return methodParameters;
    }

    /**
     * Sets the value of the methodParameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link MethodParameters }
     *     
     */
    public void setMethodParameters(MethodParameters value) {
        this.methodParameters = value;
    }

    /**
     * Gets the value of the methodFormats property.
     * 
     * @return
     *     possible object is
     *     {@link MethodFormats }
     *     
     */
    public MethodFormats getMethodFormats() {
        return methodFormats;
    }

    /**
     * Sets the value of the methodFormats property.
     * 
     * @param value
     *     allowed object is
     *     {@link MethodFormats }
     *     
     */
    public void setMethodFormats(MethodFormats value) {
        this.methodFormats = value;
    }

    /**
     * Gets the value of the methodHTTPRequests property.
     * 
     * @return
     *     possible object is
     *     {@link MethodHTTPRequests }
     *     
     */
    public MethodHTTPRequests getMethodHTTPRequests() {
        return methodHTTPRequests;
    }

    /**
     * Sets the value of the methodHTTPRequests property.
     * 
     * @param value
     *     allowed object is
     *     {@link MethodHTTPRequests }
     *     
     */
    public void setMethodHTTPRequests(MethodHTTPRequests value) {
        this.methodHTTPRequests = value;
    }

    /**
     * Gets the value of the methodSupportedInAPIVersions property.
     * 
     * @return
     *     possible object is
     *     {@link MethodSupportedInAPIVersions }
     *     
     */
    public MethodSupportedInAPIVersions getMethodSupportedInAPIVersions() {
        return methodSupportedInAPIVersions;
    }

    /**
     * Sets the value of the methodSupportedInAPIVersions property.
     * 
     * @param value
     *     allowed object is
     *     {@link MethodSupportedInAPIVersions }
     *     
     */
    public void setMethodSupportedInAPIVersions(MethodSupportedInAPIVersions value) {
        this.methodSupportedInAPIVersions = value;
    }

}

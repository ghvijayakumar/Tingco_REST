//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.04.29 at 06:55:56 PM IST 
//


package se.info24.devicejaxb;

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
 *         &lt;element name="LoadBalanceID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LoadBalanceName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LoadBalanceDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GroupID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MaxCurrentTotal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MaxCurrentL1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MaxCurrentL2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MaxCurrentL3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsPhaseL1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsPhaseL2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsPhaseL3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CurrentTotal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CurrentL1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CurrentL2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CurrentL3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}LastUpdatedByUserID"/>
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
    "loadBalanceID",
    "loadBalanceName",
    "loadBalanceDescription",
    "groupID",
    "maxCurrentTotal",
    "maxCurrentL1",
    "maxCurrentL2",
    "maxCurrentL3",
    "isPhaseL1",
    "isPhaseL2",
    "isPhaseL3",
    "currentTotal",
    "currentL1",
    "currentL2",
    "currentL3",
    "lastUpdatedByUserID",
    "createdDate",
    "updatedDate"
})
@XmlRootElement(name = "LoadBalance")
public class LoadBalance {

    @XmlElement(name = "LoadBalanceID", required = true)
    protected String loadBalanceID;
    @XmlElement(name = "LoadBalanceName", required = true)
    protected String loadBalanceName;
    @XmlElement(name = "LoadBalanceDescription", required = true)
    protected String loadBalanceDescription;
    @XmlElement(name = "GroupID", required = true)
    protected String groupID;
    @XmlElement(name = "MaxCurrentTotal", required = true)
    protected String maxCurrentTotal;
    @XmlElement(name = "MaxCurrentL1", required = true)
    protected String maxCurrentL1;
    @XmlElement(name = "MaxCurrentL2", required = true)
    protected String maxCurrentL2;
    @XmlElement(name = "MaxCurrentL3", required = true)
    protected String maxCurrentL3;
    @XmlElement(name = "IsPhaseL1", required = true)
    protected String isPhaseL1;
    @XmlElement(name = "IsPhaseL2", required = true)
    protected String isPhaseL2;
    @XmlElement(name = "IsPhaseL3", required = true)
    protected String isPhaseL3;
    @XmlElement(name = "CurrentTotal", required = true)
    protected String currentTotal;
    @XmlElement(name = "CurrentL1", required = true)
    protected String currentL1;
    @XmlElement(name = "CurrentL2", required = true)
    protected String currentL2;
    @XmlElement(name = "CurrentL3", required = true)
    protected String currentL3;
    @XmlElement(name = "LastUpdatedByUserID", required = true)
    protected LastUpdatedByUserID lastUpdatedByUserID;
    @XmlElement(name = "CreatedDate", required = true)
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "UpdatedDate", required = true)
    protected XMLGregorianCalendar updatedDate;

    /**
     * Gets the value of the loadBalanceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoadBalanceID() {
        return loadBalanceID;
    }

    /**
     * Sets the value of the loadBalanceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoadBalanceID(String value) {
        this.loadBalanceID = value;
    }

    /**
     * Gets the value of the loadBalanceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoadBalanceName() {
        return loadBalanceName;
    }

    /**
     * Sets the value of the loadBalanceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoadBalanceName(String value) {
        this.loadBalanceName = value;
    }

    /**
     * Gets the value of the loadBalanceDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoadBalanceDescription() {
        return loadBalanceDescription;
    }

    /**
     * Sets the value of the loadBalanceDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoadBalanceDescription(String value) {
        this.loadBalanceDescription = value;
    }

    /**
     * Gets the value of the groupID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupID() {
        return groupID;
    }

    /**
     * Sets the value of the groupID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupID(String value) {
        this.groupID = value;
    }

    /**
     * Gets the value of the maxCurrentTotal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxCurrentTotal() {
        return maxCurrentTotal;
    }

    /**
     * Sets the value of the maxCurrentTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxCurrentTotal(String value) {
        this.maxCurrentTotal = value;
    }

    /**
     * Gets the value of the maxCurrentL1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxCurrentL1() {
        return maxCurrentL1;
    }

    /**
     * Sets the value of the maxCurrentL1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxCurrentL1(String value) {
        this.maxCurrentL1 = value;
    }

    /**
     * Gets the value of the maxCurrentL2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxCurrentL2() {
        return maxCurrentL2;
    }

    /**
     * Sets the value of the maxCurrentL2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxCurrentL2(String value) {
        this.maxCurrentL2 = value;
    }

    /**
     * Gets the value of the maxCurrentL3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxCurrentL3() {
        return maxCurrentL3;
    }

    /**
     * Sets the value of the maxCurrentL3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxCurrentL3(String value) {
        this.maxCurrentL3 = value;
    }

    /**
     * Gets the value of the isPhaseL1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsPhaseL1() {
        return isPhaseL1;
    }

    /**
     * Sets the value of the isPhaseL1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsPhaseL1(String value) {
        this.isPhaseL1 = value;
    }

    /**
     * Gets the value of the isPhaseL2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsPhaseL2() {
        return isPhaseL2;
    }

    /**
     * Sets the value of the isPhaseL2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsPhaseL2(String value) {
        this.isPhaseL2 = value;
    }

    /**
     * Gets the value of the isPhaseL3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsPhaseL3() {
        return isPhaseL3;
    }

    /**
     * Sets the value of the isPhaseL3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsPhaseL3(String value) {
        this.isPhaseL3 = value;
    }

    /**
     * Gets the value of the currentTotal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentTotal() {
        return currentTotal;
    }

    /**
     * Sets the value of the currentTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentTotal(String value) {
        this.currentTotal = value;
    }

    /**
     * Gets the value of the currentL1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentL1() {
        return currentL1;
    }

    /**
     * Sets the value of the currentL1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentL1(String value) {
        this.currentL1 = value;
    }

    /**
     * Gets the value of the currentL2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentL2() {
        return currentL2;
    }

    /**
     * Sets the value of the currentL2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentL2(String value) {
        this.currentL2 = value;
    }

    /**
     * Gets the value of the currentL3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentL3() {
        return currentL3;
    }

    /**
     * Sets the value of the currentL3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentL3(String value) {
        this.currentL3 = value;
    }

    /**
     * Gets the value of the lastUpdatedByUserID property.
     * 
     * @return
     *     possible object is
     *     {@link LastUpdatedByUserID }
     *     
     */
    public LastUpdatedByUserID getLastUpdatedByUserID() {
        return lastUpdatedByUserID;
    }

    /**
     * Sets the value of the lastUpdatedByUserID property.
     * 
     * @param value
     *     allowed object is
     *     {@link LastUpdatedByUserID }
     *     
     */
    public void setLastUpdatedByUserID(LastUpdatedByUserID value) {
        this.lastUpdatedByUserID = value;
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

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.12 at 01:01:41 PM IST 
//


package se.info24.containersjaxb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="DataItemID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContainerID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GroupID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FillLevelPercent" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="FreeLevel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FreeLevelPercent" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ContainerMinLevel" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="ContainerEmptyLevel" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="ContainerFullLevel" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="ContainerMaxLevel" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Year" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Month" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Day" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Hour" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element ref="{}CreatedDate" minOccurs="0"/>
 *         &lt;element ref="{}UpdatedDate" minOccurs="0"/>
 *         &lt;element ref="{}FillLevel" maxOccurs="unbounded"/>
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
    "dataItemID",
    "containerID",
    "groupID",
    "fillLevelPercent",
    "freeLevel",
    "freeLevelPercent",
    "containerMinLevel",
    "containerEmptyLevel",
    "containerFullLevel",
    "containerMaxLevel",
    "year",
    "month",
    "day",
    "hour",
    "createdDate",
    "updatedDate",
    "fillLevel"
})
@XmlRootElement(name = "ContainerData")
public class ContainerData {

    @XmlElement(name = "DataItemID", required = true)
    protected String dataItemID;
    @XmlElement(name = "ContainerID", required = true)
    protected String containerID;
    @XmlElement(name = "GroupID", required = true)
    protected String groupID;
    @XmlElement(name = "FillLevelPercent")
    protected int fillLevelPercent;
    @XmlElement(name = "FreeLevel", required = true)
    protected String freeLevel;
    @XmlElement(name = "FreeLevelPercent")
    protected int freeLevelPercent;
    @XmlElement(name = "ContainerMinLevel", required = true)
    protected BigDecimal containerMinLevel;
    @XmlElement(name = "ContainerEmptyLevel", required = true)
    protected BigDecimal containerEmptyLevel;
    @XmlElement(name = "ContainerFullLevel", required = true)
    protected BigDecimal containerFullLevel;
    @XmlElement(name = "ContainerMaxLevel", required = true)
    protected BigDecimal containerMaxLevel;
    @XmlElement(name = "Year")
    protected int year;
    @XmlElement(name = "Month")
    protected int month;
    @XmlElement(name = "Day")
    protected int day;
    @XmlElement(name = "Hour")
    protected int hour;
    @XmlElement(name = "CreatedDate")
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "UpdatedDate")
    protected XMLGregorianCalendar updatedDate;
    @XmlElement(name = "FillLevel", required = true)
    protected List<FillLevel> fillLevel;

    /**
     * Gets the value of the dataItemID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataItemID() {
        return dataItemID;
    }

    /**
     * Sets the value of the dataItemID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataItemID(String value) {
        this.dataItemID = value;
    }

    /**
     * Gets the value of the containerID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContainerID() {
        return containerID;
    }

    /**
     * Sets the value of the containerID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContainerID(String value) {
        this.containerID = value;
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
     * Gets the value of the fillLevelPercent property.
     * 
     */
    public int getFillLevelPercent() {
        return fillLevelPercent;
    }

    /**
     * Sets the value of the fillLevelPercent property.
     * 
     */
    public void setFillLevelPercent(int value) {
        this.fillLevelPercent = value;
    }

    /**
     * Gets the value of the freeLevel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFreeLevel() {
        return freeLevel;
    }

    /**
     * Sets the value of the freeLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFreeLevel(String value) {
        this.freeLevel = value;
    }

    /**
     * Gets the value of the freeLevelPercent property.
     * 
     */
    public int getFreeLevelPercent() {
        return freeLevelPercent;
    }

    /**
     * Sets the value of the freeLevelPercent property.
     * 
     */
    public void setFreeLevelPercent(int value) {
        this.freeLevelPercent = value;
    }

    /**
     * Gets the value of the containerMinLevel property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getContainerMinLevel() {
        return containerMinLevel;
    }

    /**
     * Sets the value of the containerMinLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setContainerMinLevel(BigDecimal value) {
        this.containerMinLevel = value;
    }

    /**
     * Gets the value of the containerEmptyLevel property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getContainerEmptyLevel() {
        return containerEmptyLevel;
    }

    /**
     * Sets the value of the containerEmptyLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setContainerEmptyLevel(BigDecimal value) {
        this.containerEmptyLevel = value;
    }

    /**
     * Gets the value of the containerFullLevel property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getContainerFullLevel() {
        return containerFullLevel;
    }

    /**
     * Sets the value of the containerFullLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setContainerFullLevel(BigDecimal value) {
        this.containerFullLevel = value;
    }

    /**
     * Gets the value of the containerMaxLevel property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getContainerMaxLevel() {
        return containerMaxLevel;
    }

    /**
     * Sets the value of the containerMaxLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setContainerMaxLevel(BigDecimal value) {
        this.containerMaxLevel = value;
    }

    /**
     * Gets the value of the year property.
     * 
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the value of the year property.
     * 
     */
    public void setYear(int value) {
        this.year = value;
    }

    /**
     * Gets the value of the month property.
     * 
     */
    public int getMonth() {
        return month;
    }

    /**
     * Sets the value of the month property.
     * 
     */
    public void setMonth(int value) {
        this.month = value;
    }

    /**
     * Gets the value of the day property.
     * 
     */
    public int getDay() {
        return day;
    }

    /**
     * Sets the value of the day property.
     * 
     */
    public void setDay(int value) {
        this.day = value;
    }

    /**
     * Gets the value of the hour property.
     * 
     */
    public int getHour() {
        return hour;
    }

    /**
     * Sets the value of the hour property.
     * 
     */
    public void setHour(int value) {
        this.hour = value;
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
     * Gets the value of the fillLevel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fillLevel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFillLevel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FillLevel }
     * 
     * 
     */
    public List<FillLevel> getFillLevel() {
        if (fillLevel == null) {
            fillLevel = new ArrayList<FillLevel>();
        }
        return this.fillLevel;
    }

}

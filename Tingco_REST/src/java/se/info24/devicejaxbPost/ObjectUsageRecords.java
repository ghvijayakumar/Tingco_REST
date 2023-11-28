//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.04.29 at 06:36:33 PM IST 
//


package se.info24.devicejaxbPost;

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
 *       &lt;sequence minOccurs="0">
 *         &lt;element name="UsageRecordID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UsageTypeID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ServiceID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ObjectID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ObjectTypeID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ObjectName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ObjectDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UsageDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UsageStartTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="UsageStartTimeTCMV3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UsageStopTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="UsageStopTimeTCMV3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Time" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="DataItemName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GroupID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AgreementID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UsageVolume" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UsageUnitID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UsageUnitName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UsedByUserID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UsedByUserName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UsedByUserAlias" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LastUpdatedByUserID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UsageReportGroupBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "usageRecordID",
    "usageTypeID",
    "serviceID",
    "objectID",
    "objectTypeID",
    "objectName",
    "objectDescription",
    "usageDescription",
    "usageStartTime",
    "usageStartTimeTCMV3",
    "usageStopTime",
    "usageStopTimeTCMV3",
    "time",
    "dataItemName",
    "groupID",
    "agreementID",
    "usageVolume",
    "usageUnitID",
    "usageUnitName",
    "usedByUserID",
    "usedByUserName",
    "usedByUserAlias",
    "lastUpdatedByUserID",
    "usageReportGroupBy",
    "createdDate",
    "updatedDate"
})
@XmlRootElement(name = "ObjectUsageRecords")
public class ObjectUsageRecords {

    @XmlElement(name = "UsageRecordID")
    protected String usageRecordID;
    @XmlElement(name = "UsageTypeID")
    protected String usageTypeID;
    @XmlElement(name = "ServiceID")
    protected String serviceID;
    @XmlElement(name = "ObjectID")
    protected String objectID;
    @XmlElement(name = "ObjectTypeID")
    protected String objectTypeID;
    @XmlElement(name = "ObjectName")
    protected String objectName;
    @XmlElement(name = "ObjectDescription")
    protected String objectDescription;
    @XmlElement(name = "UsageDescription")
    protected String usageDescription;
    @XmlElement(name = "UsageStartTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar usageStartTime;
    @XmlElement(name = "UsageStartTimeTCMV3")
    protected String usageStartTimeTCMV3;
    @XmlElement(name = "UsageStopTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar usageStopTime;
    @XmlElement(name = "UsageStopTimeTCMV3")
    protected String usageStopTimeTCMV3;
    @XmlElement(name = "Time")
    protected Long time;
    @XmlElement(name = "DataItemName")
    protected String dataItemName;
    @XmlElement(name = "GroupID")
    protected String groupID;
    @XmlElement(name = "AgreementID")
    protected String agreementID;
    @XmlElement(name = "UsageVolume")
    protected String usageVolume;
    @XmlElement(name = "UsageUnitID")
    protected String usageUnitID;
    @XmlElement(name = "UsageUnitName")
    protected String usageUnitName;
    @XmlElement(name = "UsedByUserID")
    protected String usedByUserID;
    @XmlElement(name = "UsedByUserName")
    protected String usedByUserName;
    @XmlElement(name = "UsedByUserAlias")
    protected String usedByUserAlias;
    @XmlElement(name = "LastUpdatedByUserID")
    protected String lastUpdatedByUserID;
    @XmlElement(name = "UsageReportGroupBy")
    protected String usageReportGroupBy;
    @XmlElement(name = "CreatedDate")
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "UpdatedDate")
    protected XMLGregorianCalendar updatedDate;

    /**
     * Gets the value of the usageRecordID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsageRecordID() {
        return usageRecordID;
    }

    /**
     * Sets the value of the usageRecordID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsageRecordID(String value) {
        this.usageRecordID = value;
    }

    /**
     * Gets the value of the usageTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsageTypeID() {
        return usageTypeID;
    }

    /**
     * Sets the value of the usageTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsageTypeID(String value) {
        this.usageTypeID = value;
    }

    /**
     * Gets the value of the serviceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceID() {
        return serviceID;
    }

    /**
     * Sets the value of the serviceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceID(String value) {
        this.serviceID = value;
    }

    /**
     * Gets the value of the objectID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectID() {
        return objectID;
    }

    /**
     * Sets the value of the objectID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectID(String value) {
        this.objectID = value;
    }

    /**
     * Gets the value of the objectTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectTypeID() {
        return objectTypeID;
    }

    /**
     * Sets the value of the objectTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectTypeID(String value) {
        this.objectTypeID = value;
    }

    /**
     * Gets the value of the objectName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectName() {
        return objectName;
    }

    /**
     * Sets the value of the objectName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectName(String value) {
        this.objectName = value;
    }

    /**
     * Gets the value of the objectDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectDescription() {
        return objectDescription;
    }

    /**
     * Sets the value of the objectDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectDescription(String value) {
        this.objectDescription = value;
    }

    /**
     * Gets the value of the usageDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsageDescription() {
        return usageDescription;
    }

    /**
     * Sets the value of the usageDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsageDescription(String value) {
        this.usageDescription = value;
    }

    /**
     * Gets the value of the usageStartTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUsageStartTime() {
        return usageStartTime;
    }

    /**
     * Sets the value of the usageStartTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUsageStartTime(XMLGregorianCalendar value) {
        this.usageStartTime = value;
    }

    /**
     * Gets the value of the usageStartTimeTCMV3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsageStartTimeTCMV3() {
        return usageStartTimeTCMV3;
    }

    /**
     * Sets the value of the usageStartTimeTCMV3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsageStartTimeTCMV3(String value) {
        this.usageStartTimeTCMV3 = value;
    }

    /**
     * Gets the value of the usageStopTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUsageStopTime() {
        return usageStopTime;
    }

    /**
     * Sets the value of the usageStopTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUsageStopTime(XMLGregorianCalendar value) {
        this.usageStopTime = value;
    }

    /**
     * Gets the value of the usageStopTimeTCMV3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsageStopTimeTCMV3() {
        return usageStopTimeTCMV3;
    }

    /**
     * Sets the value of the usageStopTimeTCMV3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsageStopTimeTCMV3(String value) {
        this.usageStopTimeTCMV3 = value;
    }

    /**
     * Gets the value of the time property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTime(Long value) {
        this.time = value;
    }

    /**
     * Gets the value of the dataItemName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataItemName() {
        return dataItemName;
    }

    /**
     * Sets the value of the dataItemName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataItemName(String value) {
        this.dataItemName = value;
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
     * Gets the value of the agreementID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgreementID() {
        return agreementID;
    }

    /**
     * Sets the value of the agreementID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgreementID(String value) {
        this.agreementID = value;
    }

    /**
     * Gets the value of the usageVolume property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsageVolume() {
        return usageVolume;
    }

    /**
     * Sets the value of the usageVolume property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsageVolume(String value) {
        this.usageVolume = value;
    }

    /**
     * Gets the value of the usageUnitID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsageUnitID() {
        return usageUnitID;
    }

    /**
     * Sets the value of the usageUnitID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsageUnitID(String value) {
        this.usageUnitID = value;
    }

    /**
     * Gets the value of the usageUnitName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsageUnitName() {
        return usageUnitName;
    }

    /**
     * Sets the value of the usageUnitName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsageUnitName(String value) {
        this.usageUnitName = value;
    }

    /**
     * Gets the value of the usedByUserID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsedByUserID() {
        return usedByUserID;
    }

    /**
     * Sets the value of the usedByUserID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsedByUserID(String value) {
        this.usedByUserID = value;
    }

    /**
     * Gets the value of the usedByUserName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsedByUserName() {
        return usedByUserName;
    }

    /**
     * Sets the value of the usedByUserName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsedByUserName(String value) {
        this.usedByUserName = value;
    }

    /**
     * Gets the value of the usedByUserAlias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsedByUserAlias() {
        return usedByUserAlias;
    }

    /**
     * Sets the value of the usedByUserAlias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsedByUserAlias(String value) {
        this.usedByUserAlias = value;
    }

    /**
     * Gets the value of the lastUpdatedByUserID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastUpdatedByUserID() {
        return lastUpdatedByUserID;
    }

    /**
     * Sets the value of the lastUpdatedByUserID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastUpdatedByUserID(String value) {
        this.lastUpdatedByUserID = value;
    }

    /**
     * Gets the value of the usageReportGroupBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsageReportGroupBy() {
        return usageReportGroupBy;
    }

    /**
     * Sets the value of the usageReportGroupBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsageReportGroupBy(String value) {
        this.usageReportGroupBy = value;
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

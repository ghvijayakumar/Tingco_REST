//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.09.12 at 01:04:12 PM IST 
//


package se.info24.devicejaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element ref="{}ServiceDeviceSettingID"/>
 *         &lt;element ref="{}ServiceDeviceSettingParentID"/>
 *         &lt;element ref="{}ServiceDeviceSubscriptionID"/>
 *         &lt;element ref="{}ServiceDeviceSettingName"/>
 *         &lt;element ref="{}ServiceDeviceSettingValue"/>
 *         &lt;element ref="{}ActiveFromDate"/>
 *         &lt;element ref="{}LastUpdatedByUserID" minOccurs="0"/>
 *         &lt;element ref="{}CreatedDate"/>
 *         &lt;element ref="{}UpdatedDate"/>
 *         &lt;element ref="{}ObjectSettingTemplates" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}EventTypes" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="SeqNo" use="required">
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
    "serviceDeviceSettingID",
    "serviceDeviceSettingParentID",
    "serviceDeviceSubscriptionID",
    "serviceDeviceSettingName",
    "serviceDeviceSettingValue",
    "activeFromDate",
    "lastUpdatedByUserID",
    "createdDate",
    "updatedDate",
    "objectSettingTemplates",
    "eventTypes"
})
@XmlRootElement(name = "ServiceDeviceSetting")
public class ServiceDeviceSetting {

    @XmlElement(name = "ServiceDeviceSettingID", required = true)
    protected String serviceDeviceSettingID;
    @XmlElement(name = "ServiceDeviceSettingParentID", required = true)
    protected String serviceDeviceSettingParentID;
    @XmlElement(name = "ServiceDeviceSubscriptionID", required = true)
    protected String serviceDeviceSubscriptionID;
    @XmlElement(name = "ServiceDeviceSettingName", required = true)
    protected String serviceDeviceSettingName;
    @XmlElement(name = "ServiceDeviceSettingValue", required = true)
    protected String serviceDeviceSettingValue;
    @XmlElement(name = "ActiveFromDate", required = true)
    protected XMLGregorianCalendar activeFromDate;
    @XmlElement(name = "LastUpdatedByUserID")
    protected LastUpdatedByUserID lastUpdatedByUserID;
    @XmlElement(name = "CreatedDate", required = true)
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "UpdatedDate", required = true)
    protected XMLGregorianCalendar updatedDate;
    @XmlElement(name = "ObjectSettingTemplates")
    protected List<ObjectSettingTemplates> objectSettingTemplates;
    @XmlElement(name = "EventTypes")
    protected List<EventTypes> eventTypes;
    @XmlAttribute(name = "SeqNo", required = true)
    protected int seqNo;

    /**
     * Gets the value of the serviceDeviceSettingID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceDeviceSettingID() {
        return serviceDeviceSettingID;
    }

    /**
     * Sets the value of the serviceDeviceSettingID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceDeviceSettingID(String value) {
        this.serviceDeviceSettingID = value;
    }

    /**
     * Gets the value of the serviceDeviceSettingParentID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceDeviceSettingParentID() {
        return serviceDeviceSettingParentID;
    }

    /**
     * Sets the value of the serviceDeviceSettingParentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceDeviceSettingParentID(String value) {
        this.serviceDeviceSettingParentID = value;
    }

    /**
     * Gets the value of the serviceDeviceSubscriptionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceDeviceSubscriptionID() {
        return serviceDeviceSubscriptionID;
    }

    /**
     * Sets the value of the serviceDeviceSubscriptionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceDeviceSubscriptionID(String value) {
        this.serviceDeviceSubscriptionID = value;
    }

    /**
     * Gets the value of the serviceDeviceSettingName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceDeviceSettingName() {
        return serviceDeviceSettingName;
    }

    /**
     * Sets the value of the serviceDeviceSettingName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceDeviceSettingName(String value) {
        this.serviceDeviceSettingName = value;
    }

    /**
     * Gets the value of the serviceDeviceSettingValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceDeviceSettingValue() {
        return serviceDeviceSettingValue;
    }

    /**
     * Sets the value of the serviceDeviceSettingValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceDeviceSettingValue(String value) {
        this.serviceDeviceSettingValue = value;
    }

    /**
     * Gets the value of the activeFromDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getActiveFromDate() {
        return activeFromDate;
    }

    /**
     * Sets the value of the activeFromDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActiveFromDate(XMLGregorianCalendar value) {
        this.activeFromDate = value;
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

    /**
     * Gets the value of the objectSettingTemplates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the objectSettingTemplates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObjectSettingTemplates().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectSettingTemplates }
     * 
     * 
     */
    public List<ObjectSettingTemplates> getObjectSettingTemplates() {
        if (objectSettingTemplates == null) {
            objectSettingTemplates = new ArrayList<ObjectSettingTemplates>();
        }
        return this.objectSettingTemplates;
    }

    /**
     * Gets the value of the eventTypes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eventTypes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEventTypes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EventTypes }
     * 
     * 
     */
    public List<EventTypes> getEventTypes() {
        if (eventTypes == null) {
            eventTypes = new ArrayList<EventTypes>();
        }
        return this.eventTypes;
    }

    /**
     * Gets the value of the seqNo property.
     * 
     */
    public int getSeqNo() {
        return seqNo;
    }

    /**
     * Sets the value of the seqNo property.
     * 
     */
    public void setSeqNo(int value) {
        this.seqNo = value;
    }

}

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.11.13 at 04:06:00 PM IST 
//


package se.info24.devicejaxb;

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
 *         &lt;element ref="{}Connected"/>
 *         &lt;element name="ConnectedTCMV3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}QueueSize"/>
 *         &lt;element ref="{}HostID"/>
 *         &lt;element ref="{}ConnectChange"/>
 *         &lt;element ref="{}ObjectStateCode"/>
 *         &lt;element ref="{}ObjectStateCodePercentage" maxOccurs="unbounded"/>
 *         &lt;element ref="{}StateChangedDate"/>
 *         &lt;element ref="{}UpdatedDate"/>
 *         &lt;element name="UpdatedDateTCMV3" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "connected",
    "connectedTCMV3",
    "queueSize",
    "hostID",
    "connectChange",
    "objectStateCode",
    "objectStateCodePercentage",
    "stateChangedDate",
    "updatedDate",
    "updatedDateTCMV3"
})
@XmlRootElement(name = "ItemConnectionStatus")
public class ItemConnectionStatus {

    @XmlElement(name = "Connected", required = true)
    protected String connected;
    @XmlElement(name = "ConnectedTCMV3", required = true)
    protected String connectedTCMV3;
    @XmlElement(name = "QueueSize")
    protected int queueSize;
    @XmlElement(name = "HostID", required = true)
    protected String hostID;
    @XmlElement(name = "ConnectChange", required = true)
    protected XMLGregorianCalendar connectChange;
    @XmlElement(name = "ObjectStateCode", required = true)
    protected String objectStateCode;
    @XmlElement(name = "ObjectStateCodePercentage", required = true)
    protected List<ObjectStateCodePercentage> objectStateCodePercentage;
    @XmlElement(name = "StateChangedDate", required = true)
    protected XMLGregorianCalendar stateChangedDate;
    @XmlElement(name = "UpdatedDate", required = true)
    protected XMLGregorianCalendar updatedDate;
    @XmlElement(name = "UpdatedDateTCMV3", required = true)
    protected String updatedDateTCMV3;

    /**
     * Gets the value of the connected property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConnected() {
        return connected;
    }

    /**
     * Sets the value of the connected property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConnected(String value) {
        this.connected = value;
    }

    /**
     * Gets the value of the connectedTCMV3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConnectedTCMV3() {
        return connectedTCMV3;
    }

    /**
     * Sets the value of the connectedTCMV3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConnectedTCMV3(String value) {
        this.connectedTCMV3 = value;
    }

    /**
     * Gets the value of the queueSize property.
     * 
     */
    public int getQueueSize() {
        return queueSize;
    }

    /**
     * Sets the value of the queueSize property.
     * 
     */
    public void setQueueSize(int value) {
        this.queueSize = value;
    }

    /**
     * Gets the value of the hostID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHostID() {
        return hostID;
    }

    /**
     * Sets the value of the hostID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHostID(String value) {
        this.hostID = value;
    }

    /**
     * Gets the value of the connectChange property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getConnectChange() {
        return connectChange;
    }

    /**
     * Sets the value of the connectChange property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setConnectChange(XMLGregorianCalendar value) {
        this.connectChange = value;
    }

    /**
     * Gets the value of the objectStateCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectStateCode() {
        return objectStateCode;
    }

    /**
     * Sets the value of the objectStateCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectStateCode(String value) {
        this.objectStateCode = value;
    }

    /**
     * Gets the value of the objectStateCodePercentage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the objectStateCodePercentage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObjectStateCodePercentage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectStateCodePercentage }
     * 
     * 
     */
    public List<ObjectStateCodePercentage> getObjectStateCodePercentage() {
        if (objectStateCodePercentage == null) {
            objectStateCodePercentage = new ArrayList<ObjectStateCodePercentage>();
        }
        return this.objectStateCodePercentage;
    }

    /**
     * Gets the value of the stateChangedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStateChangedDate() {
        return stateChangedDate;
    }

    /**
     * Sets the value of the stateChangedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStateChangedDate(XMLGregorianCalendar value) {
        this.stateChangedDate = value;
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
     * Gets the value of the updatedDateTCMV3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdatedDateTCMV3() {
        return updatedDateTCMV3;
    }

    /**
     * Sets the value of the updatedDateTCMV3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdatedDateTCMV3(String value) {
        this.updatedDateTCMV3 = value;
    }

}

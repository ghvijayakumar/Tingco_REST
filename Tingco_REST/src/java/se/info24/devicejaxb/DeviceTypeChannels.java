//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.11 at 05:07:17 PM IST 
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
 *         &lt;element name="DeviceTypeID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChannelID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChannelDirection" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ChannelTag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}LastUpdatedByUserID"/>
 *         &lt;element ref="{}CreatedDate"/>
 *         &lt;element ref="{}UpdatedDate"/>
 *         &lt;element ref="{}Channels" maxOccurs="unbounded" minOccurs="0"/>
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
    "deviceTypeID",
    "channelID",
    "channelDirection",
    "channelTag",
    "lastUpdatedByUserID",
    "createdDate",
    "updatedDate",
    "channels"
})
@XmlRootElement(name = "DeviceTypeChannels")
public class DeviceTypeChannels {

    @XmlElement(name = "DeviceTypeID", required = true)
    protected String deviceTypeID;
    @XmlElement(name = "ChannelID", required = true)
    protected String channelID;
    @XmlElement(name = "ChannelDirection")
    protected int channelDirection;
    @XmlElement(name = "ChannelTag", required = true)
    protected String channelTag;
    @XmlElement(name = "LastUpdatedByUserID", required = true)
    protected LastUpdatedByUserID lastUpdatedByUserID;
    @XmlElement(name = "CreatedDate", required = true)
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "UpdatedDate", required = true)
    protected XMLGregorianCalendar updatedDate;
    @XmlElement(name = "Channels")
    protected List<Channels> channels;

    /**
     * Gets the value of the deviceTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceTypeID() {
        return deviceTypeID;
    }

    /**
     * Sets the value of the deviceTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceTypeID(String value) {
        this.deviceTypeID = value;
    }

    /**
     * Gets the value of the channelID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelID() {
        return channelID;
    }

    /**
     * Sets the value of the channelID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelID(String value) {
        this.channelID = value;
    }

    /**
     * Gets the value of the channelDirection property.
     * 
     */
    public int getChannelDirection() {
        return channelDirection;
    }

    /**
     * Sets the value of the channelDirection property.
     * 
     */
    public void setChannelDirection(int value) {
        this.channelDirection = value;
    }

    /**
     * Gets the value of the channelTag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelTag() {
        return channelTag;
    }

    /**
     * Sets the value of the channelTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelTag(String value) {
        this.channelTag = value;
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
     * Gets the value of the channels property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the channels property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChannels().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Channels }
     * 
     * 
     */
    public List<Channels> getChannels() {
        if (channels == null) {
            channels = new ArrayList<Channels>();
        }
        return this.channels;
    }

}
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
 *         &lt;element name="ChannelID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChannelName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChannelDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChannelData" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChannelEnabled" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element ref="{}GroupID" maxOccurs="unbounded" minOccurs="0"/>
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
    "channelID",
    "channelName",
    "channelDescription",
    "channelData",
    "channelEnabled",
    "groupID",
    "lastUpdatedByUserID",
    "createdDate",
    "updatedDate"
})
@XmlRootElement(name = "Channels")
public class Channels {

    @XmlElement(name = "ChannelID", required = true)
    protected String channelID;
    @XmlElement(name = "ChannelName", required = true)
    protected String channelName;
    @XmlElement(name = "ChannelDescription", required = true)
    protected String channelDescription;
    @XmlElement(name = "ChannelData", required = true)
    protected String channelData;
    @XmlElement(name = "ChannelEnabled")
    protected int channelEnabled;
    @XmlElement(name = "GroupID")
    protected List<GroupID> groupID;
    @XmlElement(name = "LastUpdatedByUserID", required = true)
    protected LastUpdatedByUserID lastUpdatedByUserID;
    @XmlElement(name = "CreatedDate", required = true)
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "UpdatedDate", required = true)
    protected XMLGregorianCalendar updatedDate;

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
     * Gets the value of the channelName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * Sets the value of the channelName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelName(String value) {
        this.channelName = value;
    }

    /**
     * Gets the value of the channelDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelDescription() {
        return channelDescription;
    }

    /**
     * Sets the value of the channelDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelDescription(String value) {
        this.channelDescription = value;
    }

    /**
     * Gets the value of the channelData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelData() {
        return channelData;
    }

    /**
     * Sets the value of the channelData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelData(String value) {
        this.channelData = value;
    }

    /**
     * Gets the value of the channelEnabled property.
     * 
     */
    public int getChannelEnabled() {
        return channelEnabled;
    }

    /**
     * Sets the value of the channelEnabled property.
     * 
     */
    public void setChannelEnabled(int value) {
        this.channelEnabled = value;
    }

    /**
     * Gets the value of the groupID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the groupID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGroupID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GroupID }
     * 
     * 
     */
    public List<GroupID> getGroupID() {
        if (groupID == null) {
            groupID = new ArrayList<GroupID>();
        }
        return this.groupID;
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

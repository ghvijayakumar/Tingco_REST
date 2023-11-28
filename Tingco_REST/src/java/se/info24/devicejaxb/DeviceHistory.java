//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.09.12 at 01:04:12 PM IST 
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
 *         &lt;element ref="{}DeviceHistoryID"/>
 *         &lt;element ref="{}DataItemID"/>
 *         &lt;element ref="{}DataItemTime"/>
 *         &lt;element ref="{}IsEnabled"/>
 *         &lt;element ref="{}PosLatitude"/>
 *         &lt;element ref="{}PosLongitude"/>
 *         &lt;element ref="{}PosDepth"/>
 *         &lt;element ref="{}PosDirection"/>
 *         &lt;element ref="{}CoordinateSystemID"/>
 *         &lt;element ref="{}MsgID"/>
 *         &lt;element ref="{}MsgTimeStamp"/>
 *         &lt;element ref="{}MsgSenderDeviceID"/>
 *         &lt;element ref="{}MsgSenderServiceID"/>
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
    "deviceHistoryID",
    "dataItemID",
    "dataItemTime",
    "isEnabled",
    "posLatitude",
    "posLongitude",
    "posDepth",
    "posDirection",
    "coordinateSystemID",
    "msgID",
    "msgTimeStamp",
    "msgSenderDeviceID",
    "msgSenderServiceID",
    "createdDate",
    "updatedDate"
})
@XmlRootElement(name = "DeviceHistory")
public class DeviceHistory {

    @XmlElement(name = "DeviceHistoryID", required = true)
    protected String deviceHistoryID;
    @XmlElement(name = "DataItemID", required = true)
    protected String dataItemID;
    @XmlElement(name = "DataItemTime", required = true)
    protected XMLGregorianCalendar dataItemTime;
    @XmlElement(name = "IsEnabled", required = true)
    protected String isEnabled;
    @XmlElement(name = "PosLatitude", required = true)
    protected String posLatitude;
    @XmlElement(name = "PosLongitude", required = true)
    protected String posLongitude;
    @XmlElement(name = "PosDepth", required = true)
    protected String posDepth;
    @XmlElement(name = "PosDirection", required = true)
    protected String posDirection;
    @XmlElement(name = "CoordinateSystemID", required = true)
    protected String coordinateSystemID;
    @XmlElement(name = "MsgID", required = true)
    protected String msgID;
    @XmlElement(name = "MsgTimeStamp", required = true)
    protected XMLGregorianCalendar msgTimeStamp;
    @XmlElement(name = "MsgSenderDeviceID", required = true)
    protected String msgSenderDeviceID;
    @XmlElement(name = "MsgSenderServiceID", required = true)
    protected String msgSenderServiceID;
    @XmlElement(name = "CreatedDate", required = true)
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "UpdatedDate", required = true)
    protected XMLGregorianCalendar updatedDate;

    /**
     * Gets the value of the deviceHistoryID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceHistoryID() {
        return deviceHistoryID;
    }

    /**
     * Sets the value of the deviceHistoryID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceHistoryID(String value) {
        this.deviceHistoryID = value;
    }

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
     * Gets the value of the dataItemTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataItemTime() {
        return dataItemTime;
    }

    /**
     * Sets the value of the dataItemTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataItemTime(XMLGregorianCalendar value) {
        this.dataItemTime = value;
    }

    /**
     * Gets the value of the isEnabled property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsEnabled() {
        return isEnabled;
    }

    /**
     * Sets the value of the isEnabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsEnabled(String value) {
        this.isEnabled = value;
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

    /**
     * Gets the value of the posDirection property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPosDirection() {
        return posDirection;
    }

    /**
     * Sets the value of the posDirection property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosDirection(String value) {
        this.posDirection = value;
    }

    /**
     * Gets the value of the coordinateSystemID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoordinateSystemID() {
        return coordinateSystemID;
    }

    /**
     * Sets the value of the coordinateSystemID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoordinateSystemID(String value) {
        this.coordinateSystemID = value;
    }

    /**
     * Gets the value of the msgID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgID() {
        return msgID;
    }

    /**
     * Sets the value of the msgID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgID(String value) {
        this.msgID = value;
    }

    /**
     * Gets the value of the msgTimeStamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMsgTimeStamp() {
        return msgTimeStamp;
    }

    /**
     * Sets the value of the msgTimeStamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMsgTimeStamp(XMLGregorianCalendar value) {
        this.msgTimeStamp = value;
    }

    /**
     * Gets the value of the msgSenderDeviceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgSenderDeviceID() {
        return msgSenderDeviceID;
    }

    /**
     * Sets the value of the msgSenderDeviceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgSenderDeviceID(String value) {
        this.msgSenderDeviceID = value;
    }

    /**
     * Gets the value of the msgSenderServiceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgSenderServiceID() {
        return msgSenderServiceID;
    }

    /**
     * Sets the value of the msgSenderServiceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgSenderServiceID(String value) {
        this.msgSenderServiceID = value;
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

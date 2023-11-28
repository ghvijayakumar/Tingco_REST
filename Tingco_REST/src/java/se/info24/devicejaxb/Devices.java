//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.11.17 at 03:37:47 PM IST 
//


package se.info24.devicejaxb;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="ConnectedDevicesAverageTCMV3" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DisConnectedDevicesAverageTCMV3" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ChargingDevicesAverageTCMV3" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element ref="{}TotalMeasurementDataUsage"/>
 *         &lt;element ref="{}ItemConnectionStatus"/>
 *         &lt;element ref="{}Device" maxOccurs="unbounded" minOccurs="0"/>
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
    "connectedDevicesAverageTCMV3",
    "disConnectedDevicesAverageTCMV3",
    "chargingDevicesAverageTCMV3",
    "totalMeasurementDataUsage",
    "itemConnectionStatus",
    "device"
})
@XmlRootElement(name = "Devices")
public class Devices {

    @XmlElement(name = "ConnectedDevicesAverageTCMV3")
    protected int connectedDevicesAverageTCMV3;
    @XmlElement(name = "DisConnectedDevicesAverageTCMV3")
    protected int disConnectedDevicesAverageTCMV3;
    @XmlElement(name = "ChargingDevicesAverageTCMV3")
    protected int chargingDevicesAverageTCMV3;
    @XmlElement(name = "TotalMeasurementDataUsage", required = true)
    protected TotalMeasurementDataUsage totalMeasurementDataUsage;
    @XmlElement(name = "ItemConnectionStatus", required = true)
    protected ItemConnectionStatus itemConnectionStatus;
    @XmlElement(name = "Device")
    protected List<Device> device;

    /**
     * Gets the value of the connectedDevicesAverageTCMV3 property.
     * 
     */
    public int getConnectedDevicesAverageTCMV3() {
        return connectedDevicesAverageTCMV3;
    }

    /**
     * Sets the value of the connectedDevicesAverageTCMV3 property.
     * 
     */
    public void setConnectedDevicesAverageTCMV3(int value) {
        this.connectedDevicesAverageTCMV3 = value;
    }

    /**
     * Gets the value of the disConnectedDevicesAverageTCMV3 property.
     * 
     */
    public int getDisConnectedDevicesAverageTCMV3() {
        return disConnectedDevicesAverageTCMV3;
    }

    /**
     * Sets the value of the disConnectedDevicesAverageTCMV3 property.
     * 
     */
    public void setDisConnectedDevicesAverageTCMV3(int value) {
        this.disConnectedDevicesAverageTCMV3 = value;
    }

    /**
     * Gets the value of the chargingDevicesAverageTCMV3 property.
     * 
     */
    public int getChargingDevicesAverageTCMV3() {
        return chargingDevicesAverageTCMV3;
    }

    /**
     * Sets the value of the chargingDevicesAverageTCMV3 property.
     * 
     */
    public void setChargingDevicesAverageTCMV3(int value) {
        this.chargingDevicesAverageTCMV3 = value;
    }

    /**
     * Gets the value of the totalMeasurementDataUsage property.
     * 
     * @return
     *     possible object is
     *     {@link TotalMeasurementDataUsage }
     *     
     */
    public TotalMeasurementDataUsage getTotalMeasurementDataUsage() {
        return totalMeasurementDataUsage;
    }

    /**
     * Sets the value of the totalMeasurementDataUsage property.
     * 
     * @param value
     *     allowed object is
     *     {@link TotalMeasurementDataUsage }
     *     
     */
    public void setTotalMeasurementDataUsage(TotalMeasurementDataUsage value) {
        this.totalMeasurementDataUsage = value;
    }

    /**
     * Gets the value of the itemConnectionStatus property.
     * 
     * @return
     *     possible object is
     *     {@link ItemConnectionStatus }
     *     
     */
    public ItemConnectionStatus getItemConnectionStatus() {
        return itemConnectionStatus;
    }

    /**
     * Sets the value of the itemConnectionStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemConnectionStatus }
     *     
     */
    public void setItemConnectionStatus(ItemConnectionStatus value) {
        this.itemConnectionStatus = value;
    }

    /**
     * Gets the value of the device property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the device property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDevice().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Device }
     * 
     * 
     */
    public List<Device> getDevice() {
        if (device == null) {
            device = new ArrayList<Device>();
        }
        return this.device;
    }

}
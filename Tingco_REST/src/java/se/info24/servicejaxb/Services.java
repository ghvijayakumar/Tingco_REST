//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.10 at 02:35:02 PM IST 
//


package se.info24.servicejaxb;

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
 *         &lt;element name="ServiceStatusOnlineCount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ServiceStatusOfflineCount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}DeviceTypeID" minOccurs="0"/>
 *         &lt;element ref="{}Service" maxOccurs="unbounded" minOccurs="0"/>
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
    "serviceStatusOnlineCount",
    "serviceStatusOfflineCount",
    "deviceTypeID",
    "service"
})
@XmlRootElement(name = "Services")
public class Services {

    @XmlElement(name = "ServiceStatusOnlineCount", required = true)
    protected String serviceStatusOnlineCount;
    @XmlElement(name = "ServiceStatusOfflineCount", required = true)
    protected String serviceStatusOfflineCount;
    @XmlElement(name = "DeviceTypeID")
    protected DeviceTypeID deviceTypeID;
    @XmlElement(name = "Service")
    protected List<Service> service;

    /**
     * Gets the value of the serviceStatusOnlineCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceStatusOnlineCount() {
        return serviceStatusOnlineCount;
    }

    /**
     * Sets the value of the serviceStatusOnlineCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceStatusOnlineCount(String value) {
        this.serviceStatusOnlineCount = value;
    }

    /**
     * Gets the value of the serviceStatusOfflineCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceStatusOfflineCount() {
        return serviceStatusOfflineCount;
    }

    /**
     * Sets the value of the serviceStatusOfflineCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceStatusOfflineCount(String value) {
        this.serviceStatusOfflineCount = value;
    }

    /**
     * Gets the value of the deviceTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link DeviceTypeID }
     *     
     */
    public DeviceTypeID getDeviceTypeID() {
        return deviceTypeID;
    }

    /**
     * Sets the value of the deviceTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceTypeID }
     *     
     */
    public void setDeviceTypeID(DeviceTypeID value) {
        this.deviceTypeID = value;
    }

    /**
     * Gets the value of the service property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the service property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getService().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Service }
     * 
     * 
     */
    public List<Service> getService() {
        if (service == null) {
            service = new ArrayList<Service>();
        }
        return this.service;
    }

}
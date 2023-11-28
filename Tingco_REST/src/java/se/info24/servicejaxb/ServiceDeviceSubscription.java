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
 *         &lt;element name="ServiceDeviceSubscriptionID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}ServiceDeviceSettings" maxOccurs="unbounded" minOccurs="0"/>
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
    "serviceDeviceSubscriptionID",
    "serviceDeviceSettings"
})
@XmlRootElement(name = "ServiceDeviceSubscription")
public class ServiceDeviceSubscription {

    @XmlElement(name = "ServiceDeviceSubscriptionID", required = true)
    protected String serviceDeviceSubscriptionID;
    @XmlElement(name = "ServiceDeviceSettings")
    protected List<ServiceDeviceSettings> serviceDeviceSettings;

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
     * Gets the value of the serviceDeviceSettings property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceDeviceSettings property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceDeviceSettings().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceDeviceSettings }
     * 
     * 
     */
    public List<ServiceDeviceSettings> getServiceDeviceSettings() {
        if (serviceDeviceSettings == null) {
            serviceDeviceSettings = new ArrayList<ServiceDeviceSettings>();
        }
        return this.serviceDeviceSettings;
    }

}

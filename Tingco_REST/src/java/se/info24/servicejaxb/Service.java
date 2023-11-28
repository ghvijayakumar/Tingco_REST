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
 *         &lt;element ref="{}ServiceType"/>
 *         &lt;element ref="{}ChannelType"/>
 *         &lt;element ref="{}ServiceID"/>
 *         &lt;element ref="{}ServiceName"/>
 *         &lt;element ref="{}ServiceDescription"/>
 *         &lt;element ref="{}GroupID"/>
 *         &lt;element ref="{}ServiceEnabled"/>
 *         &lt;element name="ServiceEnabledTCMV3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}ActiveFromDate"/>
 *         &lt;element ref="{}Replicates"/>
 *         &lt;element name="ReplicatesName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}UserID"/>
 *         &lt;element name="CountryID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OptionalCountryID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ObjectGroupName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}CreatedDate"/>
 *         &lt;element ref="{}UpdatedDate"/>
 *         &lt;element ref="{}Device" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}ServiceStatus" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}DeviceTypeServices"/>
 *         &lt;element ref="{}ServiceDeviceSubscription" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}ServiceContentSubscription" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}ServiceClientLogins" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}ServiceSettings"/>
 *         &lt;element ref="{}ServicesChannels"/>
 *         &lt;element ref="{}Bundles"/>
 *       &lt;/sequence>
 *       &lt;attribute name="SeqNo">
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
    "serviceType",
    "channelType",
    "serviceID",
    "serviceName",
    "serviceDescription",
    "groupID",
    "serviceEnabled",
    "serviceEnabledTCMV3",
    "activeFromDate",
    "replicates",
    "replicatesName",
    "userID",
    "countryID",
    "optionalCountryID",
    "objectGroupName",
    "createdDate",
    "updatedDate",
    "device",
    "serviceStatus",
    "deviceTypeServices",
    "serviceDeviceSubscription",
    "serviceContentSubscription",
    "serviceClientLogins",
    "serviceSettings",
    "servicesChannels",
    "bundles"
})
@XmlRootElement(name = "Service")
public class Service {

    @XmlElement(name = "ServiceType", required = true)
    protected ServiceType serviceType;
    @XmlElement(name = "ChannelType", required = true)
    protected ChannelType channelType;
    @XmlElement(name = "ServiceID", required = true)
    protected String serviceID;
    @XmlElement(name = "ServiceName", required = true)
    protected String serviceName;
    @XmlElement(name = "ServiceDescription", required = true)
    protected String serviceDescription;
    @XmlElement(name = "GroupID", required = true)
    protected GroupID groupID;
    @XmlElement(name = "ServiceEnabled")
    protected int serviceEnabled;
    @XmlElement(name = "ServiceEnabledTCMV3", required = true)
    protected String serviceEnabledTCMV3;
    @XmlElement(name = "ActiveFromDate", required = true)
    protected XMLGregorianCalendar activeFromDate;
    @XmlElement(name = "Replicates", required = true)
    protected String replicates;
    @XmlElement(name = "ReplicatesName", required = true)
    protected String replicatesName;
    @XmlElement(name = "UserID", required = true)
    protected String userID;
    @XmlElement(name = "CountryID")
    protected int countryID;
    @XmlElement(name = "OptionalCountryID", required = true)
    protected String optionalCountryID;
    @XmlElement(name = "ObjectGroupName", required = true)
    protected String objectGroupName;
    @XmlElement(name = "CreatedDate", required = true)
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "UpdatedDate", required = true)
    protected XMLGregorianCalendar updatedDate;
    @XmlElement(name = "Device")
    protected List<Device> device;
    @XmlElement(name = "ServiceStatus")
    protected List<ServiceStatus> serviceStatus;
    @XmlElement(name = "DeviceTypeServices", required = true)
    protected DeviceTypeServices deviceTypeServices;
    @XmlElement(name = "ServiceDeviceSubscription")
    protected List<ServiceDeviceSubscription> serviceDeviceSubscription;
    @XmlElement(name = "ServiceContentSubscription")
    protected List<ServiceContentSubscription> serviceContentSubscription;
    @XmlElement(name = "ServiceClientLogins")
    protected List<ServiceClientLogins> serviceClientLogins;
    @XmlElement(name = "ServiceSettings", required = true)
    protected ServiceSettings serviceSettings;
    @XmlElement(name = "ServicesChannels", required = true)
    protected ServicesChannels servicesChannels;
    @XmlElement(name = "Bundles", required = true)
    protected Bundles bundles;
    @XmlAttribute(name = "SeqNo")
    protected Integer seqNo;

    /**
     * Gets the value of the serviceType property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceType }
     *     
     */
    public ServiceType getServiceType() {
        return serviceType;
    }

    /**
     * Sets the value of the serviceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceType }
     *     
     */
    public void setServiceType(ServiceType value) {
        this.serviceType = value;
    }

    /**
     * Gets the value of the channelType property.
     * 
     * @return
     *     possible object is
     *     {@link ChannelType }
     *     
     */
    public ChannelType getChannelType() {
        return channelType;
    }

    /**
     * Sets the value of the channelType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChannelType }
     *     
     */
    public void setChannelType(ChannelType value) {
        this.channelType = value;
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
     * Gets the value of the serviceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Sets the value of the serviceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceName(String value) {
        this.serviceName = value;
    }

    /**
     * Gets the value of the serviceDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceDescription() {
        return serviceDescription;
    }

    /**
     * Sets the value of the serviceDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceDescription(String value) {
        this.serviceDescription = value;
    }

    /**
     * Gets the value of the groupID property.
     * 
     * @return
     *     possible object is
     *     {@link GroupID }
     *     
     */
    public GroupID getGroupID() {
        return groupID;
    }

    /**
     * Sets the value of the groupID property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupID }
     *     
     */
    public void setGroupID(GroupID value) {
        this.groupID = value;
    }

    /**
     * Gets the value of the serviceEnabled property.
     * 
     */
    public int getServiceEnabled() {
        return serviceEnabled;
    }

    /**
     * Sets the value of the serviceEnabled property.
     * 
     */
    public void setServiceEnabled(int value) {
        this.serviceEnabled = value;
    }

    /**
     * Gets the value of the serviceEnabledTCMV3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceEnabledTCMV3() {
        return serviceEnabledTCMV3;
    }

    /**
     * Sets the value of the serviceEnabledTCMV3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceEnabledTCMV3(String value) {
        this.serviceEnabledTCMV3 = value;
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
     * Gets the value of the replicates property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReplicates() {
        return replicates;
    }

    /**
     * Sets the value of the replicates property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReplicates(String value) {
        this.replicates = value;
    }

    /**
     * Gets the value of the replicatesName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReplicatesName() {
        return replicatesName;
    }

    /**
     * Sets the value of the replicatesName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReplicatesName(String value) {
        this.replicatesName = value;
    }

    /**
     * Gets the value of the userID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the value of the userID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserID(String value) {
        this.userID = value;
    }

    /**
     * Gets the value of the countryID property.
     * 
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * Sets the value of the countryID property.
     * 
     */
    public void setCountryID(int value) {
        this.countryID = value;
    }

    /**
     * Gets the value of the optionalCountryID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOptionalCountryID() {
        return optionalCountryID;
    }

    /**
     * Sets the value of the optionalCountryID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOptionalCountryID(String value) {
        this.optionalCountryID = value;
    }

    /**
     * Gets the value of the objectGroupName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectGroupName() {
        return objectGroupName;
    }

    /**
     * Sets the value of the objectGroupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectGroupName(String value) {
        this.objectGroupName = value;
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

    /**
     * Gets the value of the serviceStatus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceStatus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceStatus }
     * 
     * 
     */
    public List<ServiceStatus> getServiceStatus() {
        if (serviceStatus == null) {
            serviceStatus = new ArrayList<ServiceStatus>();
        }
        return this.serviceStatus;
    }

    /**
     * Gets the value of the deviceTypeServices property.
     * 
     * @return
     *     possible object is
     *     {@link DeviceTypeServices }
     *     
     */
    public DeviceTypeServices getDeviceTypeServices() {
        return deviceTypeServices;
    }

    /**
     * Sets the value of the deviceTypeServices property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceTypeServices }
     *     
     */
    public void setDeviceTypeServices(DeviceTypeServices value) {
        this.deviceTypeServices = value;
    }

    /**
     * Gets the value of the serviceDeviceSubscription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceDeviceSubscription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceDeviceSubscription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceDeviceSubscription }
     * 
     * 
     */
    public List<ServiceDeviceSubscription> getServiceDeviceSubscription() {
        if (serviceDeviceSubscription == null) {
            serviceDeviceSubscription = new ArrayList<ServiceDeviceSubscription>();
        }
        return this.serviceDeviceSubscription;
    }

    /**
     * Gets the value of the serviceContentSubscription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceContentSubscription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceContentSubscription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceContentSubscription }
     * 
     * 
     */
    public List<ServiceContentSubscription> getServiceContentSubscription() {
        if (serviceContentSubscription == null) {
            serviceContentSubscription = new ArrayList<ServiceContentSubscription>();
        }
        return this.serviceContentSubscription;
    }

    /**
     * Gets the value of the serviceClientLogins property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceClientLogins property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceClientLogins().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceClientLogins }
     * 
     * 
     */
    public List<ServiceClientLogins> getServiceClientLogins() {
        if (serviceClientLogins == null) {
            serviceClientLogins = new ArrayList<ServiceClientLogins>();
        }
        return this.serviceClientLogins;
    }

    /**
     * Gets the value of the serviceSettings property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceSettings }
     *     
     */
    public ServiceSettings getServiceSettings() {
        return serviceSettings;
    }

    /**
     * Sets the value of the serviceSettings property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceSettings }
     *     
     */
    public void setServiceSettings(ServiceSettings value) {
        this.serviceSettings = value;
    }

    /**
     * Gets the value of the servicesChannels property.
     * 
     * @return
     *     possible object is
     *     {@link ServicesChannels }
     *     
     */
    public ServicesChannels getServicesChannels() {
        return servicesChannels;
    }

    /**
     * Sets the value of the servicesChannels property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServicesChannels }
     *     
     */
    public void setServicesChannels(ServicesChannels value) {
        this.servicesChannels = value;
    }

    /**
     * Gets the value of the bundles property.
     * 
     * @return
     *     possible object is
     *     {@link Bundles }
     *     
     */
    public Bundles getBundles() {
        return bundles;
    }

    /**
     * Sets the value of the bundles property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bundles }
     *     
     */
    public void setBundles(Bundles value) {
        this.bundles = value;
    }

    /**
     * Gets the value of the seqNo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSeqNo() {
        return seqNo;
    }

    /**
     * Sets the value of the seqNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSeqNo(Integer value) {
        this.seqNo = value;
    }

}

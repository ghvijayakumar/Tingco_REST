//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.05.09 at 02:02:29 PM IST 
//


package se.info24.groupsjaxb;

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
 *       &lt;sequence>
 *         &lt;element name="AccessLogRowID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AccessTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="AccessTimeTCMV3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DeviceID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}DeviceGroupID"/>
 *         &lt;element name="DeviceName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Resource" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserAliasID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserAlias" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FullName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}UserGroupID"/>
 *         &lt;element name="UserAliasTypeID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserAliasTypeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}EventTypeID"/>
 *         &lt;element name="EventSubject" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PosLatitude" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PosLongitude" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PosAltitude" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="PosHeading" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CoordinateSystemID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Location" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "accessLogRowID",
    "accessTime",
    "accessTimeTCMV3",
    "deviceID",
    "deviceGroupID",
    "deviceName",
    "resource",
    "userAliasID",
    "userAlias",
    "firstName",
    "lastName",
    "fullName",
    "userID",
    "userGroupID",
    "userAliasTypeID",
    "userAliasTypeName",
    "eventTypeID",
    "eventSubject",
    "posLatitude",
    "posLongitude",
    "posAltitude",
    "posHeading",
    "coordinateSystemID",
    "location"
})
@XmlRootElement(name = "AccessLog")
public class AccessLog {

    @XmlElement(name = "AccessLogRowID", required = true)
    protected String accessLogRowID;
    @XmlElement(name = "AccessTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar accessTime;
    @XmlElement(name = "AccessTimeTCMV3", required = true)
    protected String accessTimeTCMV3;
    @XmlElement(name = "DeviceID", required = true)
    protected String deviceID;
    @XmlElement(name = "DeviceGroupID", required = true)
    protected DeviceGroupID deviceGroupID;
    @XmlElement(name = "DeviceName", required = true)
    protected String deviceName;
    @XmlElement(name = "Resource", required = true)
    protected String resource;
    @XmlElement(name = "UserAliasID", required = true)
    protected String userAliasID;
    @XmlElement(name = "UserAlias", required = true)
    protected String userAlias;
    @XmlElement(name = "FirstName", required = true)
    protected String firstName;
    @XmlElement(name = "LastName", required = true)
    protected String lastName;
    @XmlElement(name = "FullName", required = true)
    protected String fullName;
    @XmlElement(name = "UserID", required = true)
    protected String userID;
    @XmlElement(name = "UserGroupID", required = true)
    protected UserGroupID userGroupID;
    @XmlElement(name = "UserAliasTypeID", required = true)
    protected String userAliasTypeID;
    @XmlElement(name = "UserAliasTypeName", required = true)
    protected String userAliasTypeName;
    @XmlElement(name = "EventTypeID", required = true)
    protected EventTypeID eventTypeID;
    @XmlElement(name = "EventSubject", required = true)
    protected String eventSubject;
    @XmlElement(name = "PosLatitude", required = true)
    protected String posLatitude;
    @XmlElement(name = "PosLongitude", required = true)
    protected String posLongitude;
    @XmlElement(name = "PosAltitude")
    protected int posAltitude;
    @XmlElement(name = "PosHeading")
    protected int posHeading;
    @XmlElement(name = "CoordinateSystemID", required = true)
    protected String coordinateSystemID;
    @XmlElement(name = "Location", required = true)
    protected String location;

    /**
     * Gets the value of the accessLogRowID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessLogRowID() {
        return accessLogRowID;
    }

    /**
     * Sets the value of the accessLogRowID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessLogRowID(String value) {
        this.accessLogRowID = value;
    }

    /**
     * Gets the value of the accessTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAccessTime() {
        return accessTime;
    }

    /**
     * Sets the value of the accessTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAccessTime(XMLGregorianCalendar value) {
        this.accessTime = value;
    }

    /**
     * Gets the value of the accessTimeTCMV3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessTimeTCMV3() {
        return accessTimeTCMV3;
    }

    /**
     * Sets the value of the accessTimeTCMV3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessTimeTCMV3(String value) {
        this.accessTimeTCMV3 = value;
    }

    /**
     * Gets the value of the deviceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceID() {
        return deviceID;
    }

    /**
     * Sets the value of the deviceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceID(String value) {
        this.deviceID = value;
    }

    /**
     * Gets the value of the deviceGroupID property.
     * 
     * @return
     *     possible object is
     *     {@link DeviceGroupID }
     *     
     */
    public DeviceGroupID getDeviceGroupID() {
        return deviceGroupID;
    }

    /**
     * Sets the value of the deviceGroupID property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceGroupID }
     *     
     */
    public void setDeviceGroupID(DeviceGroupID value) {
        this.deviceGroupID = value;
    }

    /**
     * Gets the value of the deviceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * Sets the value of the deviceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceName(String value) {
        this.deviceName = value;
    }

    /**
     * Gets the value of the resource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResource() {
        return resource;
    }

    /**
     * Sets the value of the resource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResource(String value) {
        this.resource = value;
    }

    /**
     * Gets the value of the userAliasID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserAliasID() {
        return userAliasID;
    }

    /**
     * Sets the value of the userAliasID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserAliasID(String value) {
        this.userAliasID = value;
    }

    /**
     * Gets the value of the userAlias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserAlias() {
        return userAlias;
    }

    /**
     * Sets the value of the userAlias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserAlias(String value) {
        this.userAlias = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the fullName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the value of the fullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFullName(String value) {
        this.fullName = value;
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
     * Gets the value of the userGroupID property.
     * 
     * @return
     *     possible object is
     *     {@link UserGroupID }
     *     
     */
    public UserGroupID getUserGroupID() {
        return userGroupID;
    }

    /**
     * Sets the value of the userGroupID property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserGroupID }
     *     
     */
    public void setUserGroupID(UserGroupID value) {
        this.userGroupID = value;
    }

    /**
     * Gets the value of the userAliasTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserAliasTypeID() {
        return userAliasTypeID;
    }

    /**
     * Sets the value of the userAliasTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserAliasTypeID(String value) {
        this.userAliasTypeID = value;
    }

    /**
     * Gets the value of the userAliasTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserAliasTypeName() {
        return userAliasTypeName;
    }

    /**
     * Sets the value of the userAliasTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserAliasTypeName(String value) {
        this.userAliasTypeName = value;
    }

    /**
     * Gets the value of the eventTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link EventTypeID }
     *     
     */
    public EventTypeID getEventTypeID() {
        return eventTypeID;
    }

    /**
     * Sets the value of the eventTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventTypeID }
     *     
     */
    public void setEventTypeID(EventTypeID value) {
        this.eventTypeID = value;
    }

    /**
     * Gets the value of the eventSubject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventSubject() {
        return eventSubject;
    }

    /**
     * Sets the value of the eventSubject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventSubject(String value) {
        this.eventSubject = value;
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
     * Gets the value of the posAltitude property.
     * 
     */
    public int getPosAltitude() {
        return posAltitude;
    }

    /**
     * Sets the value of the posAltitude property.
     * 
     */
    public void setPosAltitude(int value) {
        this.posAltitude = value;
    }

    /**
     * Gets the value of the posHeading property.
     * 
     */
    public int getPosHeading() {
        return posHeading;
    }

    /**
     * Sets the value of the posHeading property.
     * 
     */
    public void setPosHeading(int value) {
        this.posHeading = value;
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
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocation(String value) {
        this.location = value;
    }

}

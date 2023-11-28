//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.22 at 12:24:24 PM IST 
//


package se.info24.homejaxb;

import java.math.BigDecimal;
import java.math.BigInteger;
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
 *         &lt;element name="RoomID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RoomName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RoomDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RoomNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}OwnerGroupID"/>
 *         &lt;element ref="{}UsedByGroupID"/>
 *         &lt;element name="RoomTypeID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}Building"/>
 *         &lt;element ref="{}ContainerID"/>
 *         &lt;element name="Area" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="AreaUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Volume" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="VolumeUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Rating" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="IsNonSmoking" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IsPublicAccess" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IsDoNotDisturb" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element ref="{}AgreementID"/>
 *         &lt;element name="FloorLevel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LayoutImageURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LastUpdatedByUserID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}CreatedDate"/>
 *         &lt;element ref="{}UpdatedDate"/>
 *         &lt;element ref="{}RoomTypes" maxOccurs="unbounded"/>
 *         &lt;element ref="{}Zone" maxOccurs="unbounded"/>
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
    "roomID",
    "roomName",
    "roomDescription",
    "roomNumber",
    "ownerGroupID",
    "usedByGroupID",
    "roomTypeID",
    "building",
    "containerID",
    "area",
    "areaUnit",
    "volume",
    "volumeUnit",
    "rating",
    "isNonSmoking",
    "isPublicAccess",
    "isDoNotDisturb",
    "agreementID",
    "floorLevel",
    "layoutImageURL",
    "lastUpdatedByUserID",
    "createdDate",
    "updatedDate",
    "roomTypes",
    "zone"
})
@XmlRootElement(name = "Room")
public class Room {

    @XmlElement(name = "RoomID", required = true)
    protected String roomID;
    @XmlElement(name = "RoomName", required = true)
    protected String roomName;
    @XmlElement(name = "RoomDescription", required = true)
    protected String roomDescription;
    @XmlElement(name = "RoomNumber", required = true)
    protected String roomNumber;
    @XmlElement(name = "OwnerGroupID", required = true)
    protected OwnerGroupID ownerGroupID;
    @XmlElement(name = "UsedByGroupID", required = true)
    protected UsedByGroupID usedByGroupID;
    @XmlElement(name = "RoomTypeID", required = true)
    protected String roomTypeID;
    @XmlElement(name = "Building", required = true)
    protected Building building;
    @XmlElement(name = "ContainerID", required = true)
    protected ContainerID containerID;
    @XmlElement(name = "Area", required = true)
    protected BigDecimal area;
    @XmlElement(name = "AreaUnit", required = true)
    protected String areaUnit;
    @XmlElement(name = "Volume", required = true)
    protected BigDecimal volume;
    @XmlElement(name = "VolumeUnit", required = true)
    protected String volumeUnit;
    @XmlElement(name = "Rating", required = true)
    protected BigInteger rating;
    @XmlElement(name = "IsNonSmoking")
    protected int isNonSmoking;
    @XmlElement(name = "IsPublicAccess")
    protected int isPublicAccess;
    @XmlElement(name = "IsDoNotDisturb")
    protected int isDoNotDisturb;
    @XmlElement(name = "AgreementID", required = true)
    protected AgreementID agreementID;
    @XmlElement(name = "FloorLevel", required = true)
    protected String floorLevel;
    @XmlElement(name = "LayoutImageURL", required = true)
    protected String layoutImageURL;
    @XmlElement(name = "LastUpdatedByUserID", required = true)
    protected String lastUpdatedByUserID;
    @XmlElement(name = "CreatedDate", required = true)
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "UpdatedDate", required = true)
    protected XMLGregorianCalendar updatedDate;
    @XmlElement(name = "RoomTypes", required = true)
    protected List<RoomTypes> roomTypes;
    @XmlElement(name = "Zone", required = true)
    protected List<Zone> zone;
    @XmlAttribute(name = "SeqNo")
    protected Integer seqNo;

    /**
     * Gets the value of the roomID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomID() {
        return roomID;
    }

    /**
     * Sets the value of the roomID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomID(String value) {
        this.roomID = value;
    }

    /**
     * Gets the value of the roomName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * Sets the value of the roomName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomName(String value) {
        this.roomName = value;
    }

    /**
     * Gets the value of the roomDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomDescription() {
        return roomDescription;
    }

    /**
     * Sets the value of the roomDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomDescription(String value) {
        this.roomDescription = value;
    }

    /**
     * Gets the value of the roomNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * Sets the value of the roomNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomNumber(String value) {
        this.roomNumber = value;
    }

    /**
     * Gets the value of the ownerGroupID property.
     * 
     * @return
     *     possible object is
     *     {@link OwnerGroupID }
     *     
     */
    public OwnerGroupID getOwnerGroupID() {
        return ownerGroupID;
    }

    /**
     * Sets the value of the ownerGroupID property.
     * 
     * @param value
     *     allowed object is
     *     {@link OwnerGroupID }
     *     
     */
    public void setOwnerGroupID(OwnerGroupID value) {
        this.ownerGroupID = value;
    }

    /**
     * Gets the value of the usedByGroupID property.
     * 
     * @return
     *     possible object is
     *     {@link UsedByGroupID }
     *     
     */
    public UsedByGroupID getUsedByGroupID() {
        return usedByGroupID;
    }

    /**
     * Sets the value of the usedByGroupID property.
     * 
     * @param value
     *     allowed object is
     *     {@link UsedByGroupID }
     *     
     */
    public void setUsedByGroupID(UsedByGroupID value) {
        this.usedByGroupID = value;
    }

    /**
     * Gets the value of the roomTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomTypeID() {
        return roomTypeID;
    }

    /**
     * Sets the value of the roomTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomTypeID(String value) {
        this.roomTypeID = value;
    }

    /**
     * Gets the value of the building property.
     * 
     * @return
     *     possible object is
     *     {@link Building }
     *     
     */
    public Building getBuilding() {
        return building;
    }

    /**
     * Sets the value of the building property.
     * 
     * @param value
     *     allowed object is
     *     {@link Building }
     *     
     */
    public void setBuilding(Building value) {
        this.building = value;
    }

    /**
     * Gets the value of the containerID property.
     * 
     * @return
     *     possible object is
     *     {@link ContainerID }
     *     
     */
    public ContainerID getContainerID() {
        return containerID;
    }

    /**
     * Sets the value of the containerID property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContainerID }
     *     
     */
    public void setContainerID(ContainerID value) {
        this.containerID = value;
    }

    /**
     * Gets the value of the area property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getArea() {
        return area;
    }

    /**
     * Sets the value of the area property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setArea(BigDecimal value) {
        this.area = value;
    }

    /**
     * Gets the value of the areaUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaUnit() {
        return areaUnit;
    }

    /**
     * Sets the value of the areaUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaUnit(String value) {
        this.areaUnit = value;
    }

    /**
     * Gets the value of the volume property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVolume() {
        return volume;
    }

    /**
     * Sets the value of the volume property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVolume(BigDecimal value) {
        this.volume = value;
    }

    /**
     * Gets the value of the volumeUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVolumeUnit() {
        return volumeUnit;
    }

    /**
     * Sets the value of the volumeUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVolumeUnit(String value) {
        this.volumeUnit = value;
    }

    /**
     * Gets the value of the rating property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRating() {
        return rating;
    }

    /**
     * Sets the value of the rating property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRating(BigInteger value) {
        this.rating = value;
    }

    /**
     * Gets the value of the isNonSmoking property.
     * 
     */
    public int getIsNonSmoking() {
        return isNonSmoking;
    }

    /**
     * Sets the value of the isNonSmoking property.
     * 
     */
    public void setIsNonSmoking(int value) {
        this.isNonSmoking = value;
    }

    /**
     * Gets the value of the isPublicAccess property.
     * 
     */
    public int getIsPublicAccess() {
        return isPublicAccess;
    }

    /**
     * Sets the value of the isPublicAccess property.
     * 
     */
    public void setIsPublicAccess(int value) {
        this.isPublicAccess = value;
    }

    /**
     * Gets the value of the isDoNotDisturb property.
     * 
     */
    public int getIsDoNotDisturb() {
        return isDoNotDisturb;
    }

    /**
     * Sets the value of the isDoNotDisturb property.
     * 
     */
    public void setIsDoNotDisturb(int value) {
        this.isDoNotDisturb = value;
    }

    /**
     * Gets the value of the agreementID property.
     * 
     * @return
     *     possible object is
     *     {@link AgreementID }
     *     
     */
    public AgreementID getAgreementID() {
        return agreementID;
    }

    /**
     * Sets the value of the agreementID property.
     * 
     * @param value
     *     allowed object is
     *     {@link AgreementID }
     *     
     */
    public void setAgreementID(AgreementID value) {
        this.agreementID = value;
    }

    /**
     * Gets the value of the floorLevel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFloorLevel() {
        return floorLevel;
    }

    /**
     * Sets the value of the floorLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFloorLevel(String value) {
        this.floorLevel = value;
    }

    /**
     * Gets the value of the layoutImageURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLayoutImageURL() {
        return layoutImageURL;
    }

    /**
     * Sets the value of the layoutImageURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLayoutImageURL(String value) {
        this.layoutImageURL = value;
    }

    /**
     * Gets the value of the lastUpdatedByUserID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastUpdatedByUserID() {
        return lastUpdatedByUserID;
    }

    /**
     * Sets the value of the lastUpdatedByUserID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastUpdatedByUserID(String value) {
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
     * Gets the value of the roomTypes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roomTypes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoomTypes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RoomTypes }
     * 
     * 
     */
    public List<RoomTypes> getRoomTypes() {
        if (roomTypes == null) {
            roomTypes = new ArrayList<RoomTypes>();
        }
        return this.roomTypes;
    }

    /**
     * Gets the value of the zone property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the zone property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getZone().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Zone }
     * 
     * 
     */
    public List<Zone> getZone() {
        if (zone == null) {
            zone = new ArrayList<Zone>();
        }
        return this.zone;
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

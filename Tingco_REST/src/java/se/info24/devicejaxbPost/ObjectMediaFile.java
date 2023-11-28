//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.09.13 at 11:25:29 AM IST 
//


package se.info24.devicejaxbPost;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;sequence minOccurs="0">
 *         &lt;element name="ObjectID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MediaFileID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DisplayScheduleID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DisplayOrder" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element ref="{}MediaFiles" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="SeqNo" use="required">
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
    "objectID",
    "mediaFileID",
    "displayScheduleID",
    "displayOrder",
    "mediaFiles"
})
@XmlRootElement(name = "ObjectMediaFile")
public class ObjectMediaFile {

    @XmlElement(name = "ObjectID")
    protected String objectID;
    @XmlElement(name = "MediaFileID")
    protected String mediaFileID;
    @XmlElement(name = "DisplayScheduleID")
    protected String displayScheduleID;
    @XmlElement(name = "DisplayOrder")
    protected Integer displayOrder;
    @XmlElement(name = "MediaFiles")
    protected List<MediaFiles> mediaFiles;
    @XmlAttribute(name = "SeqNo", required = true)
    protected int seqNo;

    /**
     * Gets the value of the objectID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectID() {
        return objectID;
    }

    /**
     * Sets the value of the objectID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectID(String value) {
        this.objectID = value;
    }

    /**
     * Gets the value of the mediaFileID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMediaFileID() {
        return mediaFileID;
    }

    /**
     * Sets the value of the mediaFileID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMediaFileID(String value) {
        this.mediaFileID = value;
    }

    /**
     * Gets the value of the displayScheduleID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayScheduleID() {
        return displayScheduleID;
    }

    /**
     * Sets the value of the displayScheduleID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayScheduleID(String value) {
        this.displayScheduleID = value;
    }

    /**
     * Gets the value of the displayOrder property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    /**
     * Sets the value of the displayOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDisplayOrder(Integer value) {
        this.displayOrder = value;
    }

    /**
     * Gets the value of the mediaFiles property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mediaFiles property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMediaFiles().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MediaFiles }
     * 
     * 
     */
    public List<MediaFiles> getMediaFiles() {
        if (mediaFiles == null) {
            mediaFiles = new ArrayList<MediaFiles>();
        }
        return this.mediaFiles;
    }

    /**
     * Gets the value of the seqNo property.
     * 
     */
    public int getSeqNo() {
        return seqNo;
    }

    /**
     * Sets the value of the seqNo property.
     * 
     */
    public void setSeqNo(int value) {
        this.seqNo = value;
    }

}

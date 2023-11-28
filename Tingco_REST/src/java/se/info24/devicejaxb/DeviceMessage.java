//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.09.12 at 01:04:12 PM IST 
//


package se.info24.devicejaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
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
 *       &lt;sequence minOccurs="0">
 *         &lt;element ref="{}DeviceMessageID"/>
 *         &lt;element ref="{}DataItemID" minOccurs="0"/>
 *         &lt;element ref="{}SourceReferenceID" minOccurs="0"/>
 *         &lt;element ref="{}MessageText" minOccurs="0"/>
 *         &lt;element ref="{}Priority" minOccurs="0"/>
 *         &lt;element ref="{}SendToDeviceNow" minOccurs="0"/>
 *         &lt;element ref="{}IsNew" minOccurs="0"/>
 *         &lt;element ref="{}IsSentToDevice" minOccurs="0"/>
 *         &lt;element ref="{}IsReceivedFromDevice" minOccurs="0"/>
 *         &lt;element ref="{}IsDeleted" minOccurs="0"/>
 *         &lt;element ref="{}IsEmergency" minOccurs="0"/>
 *         &lt;element ref="{}IsEmergencyAck" minOccurs="0"/>
 *         &lt;element ref="{}PosLatitude" minOccurs="0"/>
 *         &lt;element ref="{}PosLongitude" minOccurs="0"/>
 *         &lt;element ref="{}PosDepth" minOccurs="0"/>
 *         &lt;element ref="{}PosDirection" minOccurs="0"/>
 *         &lt;element ref="{}DeviceStatusText" minOccurs="0"/>
 *         &lt;element ref="{}LastUpdatedByUserID" minOccurs="0"/>
 *         &lt;element ref="{}SentToDeviceDate" minOccurs="0"/>
 *         &lt;element ref="{}ReceivedFromDeviceDate" minOccurs="0"/>
 *         &lt;element ref="{}CreatedDate" minOccurs="0"/>
 *         &lt;element ref="{}UpdatedDate" minOccurs="0"/>
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
    "content"
})
@XmlRootElement(name = "DeviceMessage")
public class DeviceMessage {

    @XmlElementRefs({
        @XmlElementRef(name = "DeviceStatusText", type = JAXBElement.class),
        @XmlElementRef(name = "PosLatitude", type = JAXBElement.class),
        @XmlElementRef(name = "PosLongitude", type = JAXBElement.class),
        @XmlElementRef(name = "SourceReferenceID", type = JAXBElement.class),
        @XmlElementRef(name = "ReceivedFromDeviceDate", type = JAXBElement.class),
        @XmlElementRef(name = "UpdatedDate", type = JAXBElement.class),
        @XmlElementRef(name = "IsSentToDevice", type = JAXBElement.class),
        @XmlElementRef(name = "CreatedDate", type = JAXBElement.class),
        @XmlElementRef(name = "PosDirection", type = JAXBElement.class),
        @XmlElementRef(name = "IsEmergency", type = JAXBElement.class),
        @XmlElementRef(name = "LastUpdatedByUserID", type = LastUpdatedByUserID.class),
        @XmlElementRef(name = "IsEmergencyAck", type = JAXBElement.class),
        @XmlElementRef(name = "DeviceMessageID", type = JAXBElement.class),
        @XmlElementRef(name = "IsDeleted", type = JAXBElement.class),
        @XmlElementRef(name = "IsNew", type = JAXBElement.class),
        @XmlElementRef(name = "SentToDeviceDate", type = JAXBElement.class),
        @XmlElementRef(name = "Priority", type = JAXBElement.class),
        @XmlElementRef(name = "PosDepth", type = JAXBElement.class),
        @XmlElementRef(name = "IsReceivedFromDevice", type = JAXBElement.class),
        @XmlElementRef(name = "MessageText", type = JAXBElement.class),
        @XmlElementRef(name = "SendToDeviceNow", type = JAXBElement.class),
        @XmlElementRef(name = "DataItemID", type = JAXBElement.class)
    })
    @XmlMixed
    protected List<Object> content;
    @XmlAttribute(name = "SeqNo")
    protected Integer seqNo;

    /**
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * {@link LastUpdatedByUserID }
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * {@link String }
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * 
     */
    public List<Object> getContent() {
        if (content == null) {
            content = new ArrayList<Object>();
        }
        return this.content;
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
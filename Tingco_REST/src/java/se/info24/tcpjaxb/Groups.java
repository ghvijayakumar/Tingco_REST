//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.11.11 at 01:17:58 PM IST 
//


package se.info24.tcpjaxb;

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
 *       &lt;sequence>
 *         &lt;element name="GroupID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GroupParentID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GroupTypeID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{}ITrustGroups"/>
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
    "groupID",
    "groupParentID",
    "groupTypeID",
    "iTrustGroups"
})
@XmlRootElement(name = "Groups")
public class Groups {

    @XmlElement(name = "GroupID", required = true)
    protected String groupID;
    @XmlElement(name = "GroupParentID", required = true)
    protected String groupParentID;
    @XmlElement(name = "GroupTypeID", required = true)
    protected String groupTypeID;
    @XmlElement(name = "ITrustGroups", required = true)
    protected ITrustGroups iTrustGroups;
    @XmlAttribute(name = "SeqNo", required = true)
    protected int seqNo;

    /**
     * Gets the value of the groupID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupID() {
        return groupID;
    }

    /**
     * Sets the value of the groupID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupID(String value) {
        this.groupID = value;
    }

    /**
     * Gets the value of the groupParentID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupParentID() {
        return groupParentID;
    }

    /**
     * Sets the value of the groupParentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupParentID(String value) {
        this.groupParentID = value;
    }

    /**
     * Gets the value of the groupTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupTypeID() {
        return groupTypeID;
    }

    /**
     * Sets the value of the groupTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupTypeID(String value) {
        this.groupTypeID = value;
    }

    /**
     * Gets the value of the iTrustGroups property.
     * 
     * @return
     *     possible object is
     *     {@link ITrustGroups }
     *     
     */
    public ITrustGroups getITrustGroups() {
        return iTrustGroups;
    }

    /**
     * Sets the value of the iTrustGroups property.
     * 
     * @param value
     *     allowed object is
     *     {@link ITrustGroups }
     *     
     */
    public void setITrustGroups(ITrustGroups value) {
        this.iTrustGroups = value;
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
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.09 at 11:37:09 AM IST 
//


package se.info24.tingcovirtualgatejaxb;

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
 *       &lt;sequence>
 *         &lt;element ref="{}ObjectGroups" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}UserAliases" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}ProductVariantDevices" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="CountofValidTickets" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "objectGroups",
    "userAliases",
    "productVariantDevices",
    "countofValidTickets"
})
@XmlRootElement(name = "VirtualGate")
public class VirtualGate {

    @XmlElement(name = "ObjectGroups")
    protected List<ObjectGroups> objectGroups;
    @XmlElement(name = "UserAliases")
    protected List<UserAliases> userAliases;
    @XmlElement(name = "ProductVariantDevices")
    protected List<ProductVariantDevices> productVariantDevices;
    @XmlElement(name = "CountofValidTickets", required = true)
    protected String countofValidTickets;
    @XmlAttribute(name = "SeqNo")
    protected Integer seqNo;

    /**
     * Gets the value of the objectGroups property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the objectGroups property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObjectGroups().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectGroups }
     * 
     * 
     */
    public List<ObjectGroups> getObjectGroups() {
        if (objectGroups == null) {
            objectGroups = new ArrayList<ObjectGroups>();
        }
        return this.objectGroups;
    }

    /**
     * Gets the value of the userAliases property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userAliases property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserAliases().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserAliases }
     * 
     * 
     */
    public List<UserAliases> getUserAliases() {
        if (userAliases == null) {
            userAliases = new ArrayList<UserAliases>();
        }
        return this.userAliases;
    }

    /**
     * Gets the value of the productVariantDevices property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productVariantDevices property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductVariantDevices().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductVariantDevices }
     * 
     * 
     */
    public List<ProductVariantDevices> getProductVariantDevices() {
        if (productVariantDevices == null) {
            productVariantDevices = new ArrayList<ProductVariantDevices>();
        }
        return this.productVariantDevices;
    }

    /**
     * Gets the value of the countofValidTickets property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountofValidTickets() {
        return countofValidTickets;
    }

    /**
     * Sets the value of the countofValidTickets property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountofValidTickets(String value) {
        this.countofValidTickets = value;
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

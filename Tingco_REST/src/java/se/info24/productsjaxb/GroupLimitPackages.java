//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.11.22 at 03:06:49 PM IST 
//


package se.info24.productsjaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="GroupLimitPackageID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PackageName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PackageDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DisplayOrder" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MaxNumberOfDevices" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MaxNumberOfUsers" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MaxNumberOfSubscriptions" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MaxNumberOfChildGroups" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MaxNumberOfTrustingGroups" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MaxDataStorageSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element ref="{}UserID"/>
 *         &lt;element ref="{}CreatedDate"/>
 *         &lt;element ref="{}UpdatedDate"/>
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
    "groupLimitPackageID",
    "packageName",
    "packageDescription",
    "displayOrder",
    "maxNumberOfDevices",
    "maxNumberOfUsers",
    "maxNumberOfSubscriptions",
    "maxNumberOfChildGroups",
    "maxNumberOfTrustingGroups",
    "maxDataStorageSize",
    "userID",
    "createdDate",
    "updatedDate"
})
@XmlRootElement(name = "GroupLimitPackages")
public class GroupLimitPackages {

    @XmlElement(name = "GroupLimitPackageID", required = true)
    protected String groupLimitPackageID;
    @XmlElement(name = "PackageName", required = true)
    protected String packageName;
    @XmlElement(name = "PackageDescription", required = true)
    protected String packageDescription;
    @XmlElement(name = "DisplayOrder")
    protected int displayOrder;
    @XmlElement(name = "MaxNumberOfDevices")
    protected int maxNumberOfDevices;
    @XmlElement(name = "MaxNumberOfUsers")
    protected int maxNumberOfUsers;
    @XmlElement(name = "MaxNumberOfSubscriptions")
    protected int maxNumberOfSubscriptions;
    @XmlElement(name = "MaxNumberOfChildGroups")
    protected int maxNumberOfChildGroups;
    @XmlElement(name = "MaxNumberOfTrustingGroups")
    protected int maxNumberOfTrustingGroups;
    @XmlElement(name = "MaxDataStorageSize")
    protected int maxDataStorageSize;
    @XmlElement(name = "UserID", required = true)
    protected String userID;
    @XmlElement(name = "CreatedDate", required = true)
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "UpdatedDate", required = true)
    protected XMLGregorianCalendar updatedDate;

    /**
     * Gets the value of the groupLimitPackageID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupLimitPackageID() {
        return groupLimitPackageID;
    }

    /**
     * Sets the value of the groupLimitPackageID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupLimitPackageID(String value) {
        this.groupLimitPackageID = value;
    }

    /**
     * Gets the value of the packageName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Sets the value of the packageName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageName(String value) {
        this.packageName = value;
    }

    /**
     * Gets the value of the packageDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageDescription() {
        return packageDescription;
    }

    /**
     * Sets the value of the packageDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageDescription(String value) {
        this.packageDescription = value;
    }

    /**
     * Gets the value of the displayOrder property.
     * 
     */
    public int getDisplayOrder() {
        return displayOrder;
    }

    /**
     * Sets the value of the displayOrder property.
     * 
     */
    public void setDisplayOrder(int value) {
        this.displayOrder = value;
    }

    /**
     * Gets the value of the maxNumberOfDevices property.
     * 
     */
    public int getMaxNumberOfDevices() {
        return maxNumberOfDevices;
    }

    /**
     * Sets the value of the maxNumberOfDevices property.
     * 
     */
    public void setMaxNumberOfDevices(int value) {
        this.maxNumberOfDevices = value;
    }

    /**
     * Gets the value of the maxNumberOfUsers property.
     * 
     */
    public int getMaxNumberOfUsers() {
        return maxNumberOfUsers;
    }

    /**
     * Sets the value of the maxNumberOfUsers property.
     * 
     */
    public void setMaxNumberOfUsers(int value) {
        this.maxNumberOfUsers = value;
    }

    /**
     * Gets the value of the maxNumberOfSubscriptions property.
     * 
     */
    public int getMaxNumberOfSubscriptions() {
        return maxNumberOfSubscriptions;
    }

    /**
     * Sets the value of the maxNumberOfSubscriptions property.
     * 
     */
    public void setMaxNumberOfSubscriptions(int value) {
        this.maxNumberOfSubscriptions = value;
    }

    /**
     * Gets the value of the maxNumberOfChildGroups property.
     * 
     */
    public int getMaxNumberOfChildGroups() {
        return maxNumberOfChildGroups;
    }

    /**
     * Sets the value of the maxNumberOfChildGroups property.
     * 
     */
    public void setMaxNumberOfChildGroups(int value) {
        this.maxNumberOfChildGroups = value;
    }

    /**
     * Gets the value of the maxNumberOfTrustingGroups property.
     * 
     */
    public int getMaxNumberOfTrustingGroups() {
        return maxNumberOfTrustingGroups;
    }

    /**
     * Sets the value of the maxNumberOfTrustingGroups property.
     * 
     */
    public void setMaxNumberOfTrustingGroups(int value) {
        this.maxNumberOfTrustingGroups = value;
    }

    /**
     * Gets the value of the maxDataStorageSize property.
     * 
     */
    public int getMaxDataStorageSize() {
        return maxDataStorageSize;
    }

    /**
     * Sets the value of the maxDataStorageSize property.
     * 
     */
    public void setMaxDataStorageSize(int value) {
        this.maxDataStorageSize = value;
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

}

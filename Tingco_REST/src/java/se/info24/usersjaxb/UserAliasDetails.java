//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.02.07 at 02:35:39 PM IST 
//


package se.info24.usersjaxb;

import java.math.BigInteger;
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
 *         &lt;element name="UserAliasID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsCreditCard" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IsServiceKey" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CreditLimitTotal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CreditLimitPerPurchase" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CreditLimitPerDay" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BlockedReason" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IApproveTermsOfBusiness" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IApproveSendingMarketingInfo" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "userAliasID",
    "isCreditCard",
    "isServiceKey",
    "creditLimitTotal",
    "creditLimitPerPurchase",
    "creditLimitPerDay",
    "blockedReason",
    "iApproveTermsOfBusiness",
    "iApproveSendingMarketingInfo"
})
@XmlRootElement(name = "UserAliasDetails")
public class UserAliasDetails {

    @XmlElement(name = "UserAliasID", required = true)
    protected String userAliasID;
    @XmlElement(name = "IsCreditCard")
    protected String isCreditCard;
    @XmlElement(name = "IsServiceKey")
    protected String isServiceKey;
    @XmlElement(name = "CreditLimitTotal", required = true)
    protected String creditLimitTotal;
    @XmlElement(name = "CreditLimitPerPurchase", required = true)
    protected String creditLimitPerPurchase;
    @XmlElement(name = "CreditLimitPerDay", required = true)
    protected String creditLimitPerDay;
    @XmlElement(name = "BlockedReason", required = true)
    protected String blockedReason;
    @XmlElement(name = "IApproveTermsOfBusiness")
    protected int iApproveTermsOfBusiness;
    @XmlElement(name = "IApproveSendingMarketingInfo")
    protected int iApproveSendingMarketingInfo;

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
     * Gets the value of the isCreditCard property.
     * 
     */
    public String getIsCreditCard() {
        return isCreditCard;
    }

    /**
     * Sets the value of the isCreditCard property.
     * 
     */
    public void setIsCreditCard(String value) {
        this.isCreditCard = value;
    }

    /**
     * Gets the value of the isServiceKey property.
     * 
     */
    public String getIsServiceKey() {
        return isServiceKey;
    }

    /**
     * Sets the value of the isServiceKey property.
     * 
     */
    public void setIsServiceKey(String value) {
        this.isServiceKey = value;
    }

    /**
     * Gets the value of the creditLimitTotal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditLimitTotal() {
        return creditLimitTotal;
    }

    /**
     * Sets the value of the creditLimitTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditLimitTotal(String value) {
        this.creditLimitTotal = value;
    }

    /**
     * Gets the value of the creditLimitPerPurchase property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditLimitPerPurchase() {
        return creditLimitPerPurchase;
    }

    /**
     * Sets the value of the creditLimitPerPurchase property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditLimitPerPurchase(String value) {
        this.creditLimitPerPurchase = value;
    }

    /**
     * Gets the value of the creditLimitPerDay property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditLimitPerDay() {
        return creditLimitPerDay;
    }

    /**
     * Sets the value of the creditLimitPerDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditLimitPerDay(String value) {
        this.creditLimitPerDay = value;
    }

    /**
     * Gets the value of the blockedReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBlockedReason() {
        return blockedReason;
    }

    /**
     * Sets the value of the blockedReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBlockedReason(String value) {
        this.blockedReason = value;
    }

    /**
     * Gets the value of the iApproveTermsOfBusiness property.
     * 
     */
    public int getIApproveTermsOfBusiness() {
        return iApproveTermsOfBusiness;
    }

    /**
     * Sets the value of the iApproveTermsOfBusiness property.
     * 
     */
    public void setIApproveTermsOfBusiness(int value) {
        this.iApproveTermsOfBusiness = value;
    }

    /**
     * Gets the value of the iApproveSendingMarketingInfo property.
     * 
     */
    public int getIApproveSendingMarketingInfo() {
        return iApproveSendingMarketingInfo;
    }

    /**
     * Sets the value of the iApproveSendingMarketingInfo property.
     * 
     */
    public void setIApproveSendingMarketingInfo(int value) {
        this.iApproveSendingMarketingInfo = value;
    }

}

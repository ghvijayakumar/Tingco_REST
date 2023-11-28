package se.info24.pojo;
// Generated Jan 8, 2013 11:28:40 AM by Hibernate Tools 3.2.1.GA


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * UserAliasDetails generated by hbm2java
 */
public class UserAliasDetails  implements java.io.Serializable {


     private String userAliasId;
     private UserAlias userAlias;
     private Integer isCreditCard;
     private Integer isServiceKey;
     private BigDecimal creditLimitTotal;
     private BigDecimal creditLimitPerPurchase;
     private BigDecimal creditLimitPerDay;
     private String blockedReason;
     private Integer iapproveTermsOfBusiness;
     private Integer iapproveSendingMarketingInfo;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public UserAliasDetails() {
    }

	
    public UserAliasDetails(String userAliasId, UserAlias userAlias) {
        this.userAliasId = userAliasId;
        this.userAlias = userAlias;
    }
    public UserAliasDetails(String userAliasId, UserAlias userAlias, Integer isCreditCard, Integer isServiceKey, BigDecimal creditLimitTotal, BigDecimal creditLimitPerPurchase, BigDecimal creditLimitPerDay, String blockedReason, Integer iapproveTermsOfBusiness, Integer iapproveSendingMarketingInfo, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.userAliasId = userAliasId;
       this.userAlias = userAlias;
       this.isCreditCard = isCreditCard;
       this.isServiceKey = isServiceKey;
       this.creditLimitTotal = creditLimitTotal;
       this.creditLimitPerPurchase = creditLimitPerPurchase;
       this.creditLimitPerDay = creditLimitPerDay;
       this.blockedReason = blockedReason;
       this.iapproveTermsOfBusiness = iapproveTermsOfBusiness;
       this.iapproveSendingMarketingInfo = iapproveSendingMarketingInfo;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public String getUserAliasId() {
        return this.userAliasId;
    }
    
    public void setUserAliasId(String userAliasId) {
        this.userAliasId = userAliasId;
    }
    public UserAlias getUserAlias() {
        return this.userAlias;
    }
    
    public void setUserAlias(UserAlias userAlias) {
        this.userAlias = userAlias;
    }
    public Integer getIsCreditCard() {
        return this.isCreditCard;
    }
    
    public void setIsCreditCard(Integer isCreditCard) {
        this.isCreditCard = isCreditCard;
    }
    public Integer getIsServiceKey() {
        return this.isServiceKey;
    }
    
    public void setIsServiceKey(Integer isServiceKey) {
        this.isServiceKey = isServiceKey;
    }
    public BigDecimal getCreditLimitTotal() {
        return this.creditLimitTotal;
    }
    
    public void setCreditLimitTotal(BigDecimal creditLimitTotal) {
        this.creditLimitTotal = creditLimitTotal;
    }
    public BigDecimal getCreditLimitPerPurchase() {
        return this.creditLimitPerPurchase;
    }
    
    public void setCreditLimitPerPurchase(BigDecimal creditLimitPerPurchase) {
        this.creditLimitPerPurchase = creditLimitPerPurchase;
    }
    public BigDecimal getCreditLimitPerDay() {
        return this.creditLimitPerDay;
    }
    
    public void setCreditLimitPerDay(BigDecimal creditLimitPerDay) {
        this.creditLimitPerDay = creditLimitPerDay;
    }
    public String getBlockedReason() {
        return this.blockedReason;
    }
    
    public void setBlockedReason(String blockedReason) {
        this.blockedReason = blockedReason;
    }
    public Integer getIapproveTermsOfBusiness() {
        return this.iapproveTermsOfBusiness;
    }
    
    public void setIapproveTermsOfBusiness(Integer iapproveTermsOfBusiness) {
        this.iapproveTermsOfBusiness = iapproveTermsOfBusiness;
    }
    public Integer getIapproveSendingMarketingInfo() {
        return this.iapproveSendingMarketingInfo;
    }
    
    public void setIapproveSendingMarketingInfo(Integer iapproveSendingMarketingInfo) {
        this.iapproveSendingMarketingInfo = iapproveSendingMarketingInfo;
    }
    public String getLastUpdatedByUserId() {
        return this.lastUpdatedByUserId;
    }
    
    public void setLastUpdatedByUserId(String lastUpdatedByUserId) {
        this.lastUpdatedByUserId = lastUpdatedByUserId;
    }
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public Date getUpdatedDate() {
        return this.updatedDate;
    }
    
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }




}



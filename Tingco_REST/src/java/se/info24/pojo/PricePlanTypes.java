package se.info24.pojo;
// Generated Jun 26, 2012 3:24:36 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * PricePlanTypes generated by hbm2java
 */
public class PricePlanTypes  implements java.io.Serializable {


     private String pricePlanTypeId;
     private String pricePlanTypeCode;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;
     private Set pricePlanTypeTranslationses = new HashSet(0);
     private Set pricePlanses = new HashSet(0);

    public PricePlanTypes() {
    }

	
    public PricePlanTypes(String pricePlanTypeId, String pricePlanTypeCode) {
        this.pricePlanTypeId = pricePlanTypeId;
        this.pricePlanTypeCode = pricePlanTypeCode;
    }
    public PricePlanTypes(String pricePlanTypeId, String pricePlanTypeCode, String lastUpdatedByUserId, Date createdDate, Date updatedDate, Set pricePlanTypeTranslationses, Set pricePlanses) {
       this.pricePlanTypeId = pricePlanTypeId;
       this.pricePlanTypeCode = pricePlanTypeCode;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
       this.pricePlanTypeTranslationses = pricePlanTypeTranslationses;
       this.pricePlanses = pricePlanses;
    }
   
    public String getPricePlanTypeId() {
        return this.pricePlanTypeId;
    }
    
    public void setPricePlanTypeId(String pricePlanTypeId) {
        this.pricePlanTypeId = pricePlanTypeId;
    }
    public String getPricePlanTypeCode() {
        return this.pricePlanTypeCode;
    }
    
    public void setPricePlanTypeCode(String pricePlanTypeCode) {
        this.pricePlanTypeCode = pricePlanTypeCode;
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
    public Set getPricePlanTypeTranslationses() {
        return this.pricePlanTypeTranslationses;
    }
    
    public void setPricePlanTypeTranslationses(Set pricePlanTypeTranslationses) {
        this.pricePlanTypeTranslationses = pricePlanTypeTranslationses;
    }
    public Set getPricePlanses() {
        return this.pricePlanses;
    }
    
    public void setPricePlanses(Set pricePlanses) {
        this.pricePlanses = pricePlanses;
    }




}



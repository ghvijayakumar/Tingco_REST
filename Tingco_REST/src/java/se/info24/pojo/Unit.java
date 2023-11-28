package se.info24.pojo;
// Generated Sep 24, 2013 1:11:02 PM by Hibernate Tools 3.2.1.GA


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Unit generated by hbm2java
 */
public class Unit  implements java.io.Serializable {


     private String unitId;
     private String objectCode;
     private String unitCategoryId;
     private String unitSystemId;
     private String unitIconUrl;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;
     private Set unitTranslationses = new HashSet(0);

    public Unit() {
    }

	
    public Unit(String unitId, String objectCode) {
        this.unitId = unitId;
        this.objectCode = objectCode;
    }
    public Unit(String unitId, String objectCode, String unitCategoryId, String unitSystemId, String unitIconUrl, String lastUpdatedByUserId, Date createdDate, Date updatedDate, Set unitTranslationses) {
       this.unitId = unitId;
       this.objectCode = objectCode;
       this.unitCategoryId = unitCategoryId;
       this.unitSystemId = unitSystemId;
       this.unitIconUrl = unitIconUrl;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
       this.unitTranslationses = unitTranslationses;
    }
   
    public String getUnitId() {
        return this.unitId;
    }
    
    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }
    public String getObjectCode() {
        return this.objectCode;
    }
    
    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }
    public String getUnitCategoryId() {
        return this.unitCategoryId;
    }
    
    public void setUnitCategoryId(String unitCategoryId) {
        this.unitCategoryId = unitCategoryId;
    }
    public String getUnitSystemId() {
        return this.unitSystemId;
    }
    
    public void setUnitSystemId(String unitSystemId) {
        this.unitSystemId = unitSystemId;
    }
    public String getUnitIconUrl() {
        return this.unitIconUrl;
    }
    
    public void setUnitIconUrl(String unitIconUrl) {
        this.unitIconUrl = unitIconUrl;
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
    public Set getUnitTranslationses() {
        return this.unitTranslationses;
    }
    
    public void setUnitTranslationses(Set unitTranslationses) {
        this.unitTranslationses = unitTranslationses;
    }




}


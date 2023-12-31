package se.info24.pojo;
// Generated Jan 23, 2013 10:58:16 AM by Hibernate Tools 3.2.1.GA


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * FieldTypes generated by hbm2java
 */
public class FieldTypes  implements java.io.Serializable {


     private String fieldTypeId;
     private String fieldTypeCode;
     private String fieldTypeName;
     private String fieldTypeDescription;
     private String fieldTypeIconUrl;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;
     private Set fieldses = new HashSet(0);

    public FieldTypes() {
    }

	
    public FieldTypes(String fieldTypeId, String fieldTypeCode, String fieldTypeName) {
        this.fieldTypeId = fieldTypeId;
        this.fieldTypeCode = fieldTypeCode;
        this.fieldTypeName = fieldTypeName;
    }
    public FieldTypes(String fieldTypeId, String fieldTypeCode, String fieldTypeName, String fieldTypeDescription, String fieldTypeIconUrl, String lastUpdatedByUserId, Date createdDate, Date updatedDate, Set fieldses) {
       this.fieldTypeId = fieldTypeId;
       this.fieldTypeCode = fieldTypeCode;
       this.fieldTypeName = fieldTypeName;
       this.fieldTypeDescription = fieldTypeDescription;
       this.fieldTypeIconUrl = fieldTypeIconUrl;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
       this.fieldses = fieldses;
    }
   
    public String getFieldTypeId() {
        return this.fieldTypeId;
    }
    
    public void setFieldTypeId(String fieldTypeId) {
        this.fieldTypeId = fieldTypeId;
    }
    public String getFieldTypeCode() {
        return this.fieldTypeCode;
    }
    
    public void setFieldTypeCode(String fieldTypeCode) {
        this.fieldTypeCode = fieldTypeCode;
    }
    public String getFieldTypeName() {
        return this.fieldTypeName;
    }
    
    public void setFieldTypeName(String fieldTypeName) {
        this.fieldTypeName = fieldTypeName;
    }
    public String getFieldTypeDescription() {
        return this.fieldTypeDescription;
    }
    
    public void setFieldTypeDescription(String fieldTypeDescription) {
        this.fieldTypeDescription = fieldTypeDescription;
    }
    public String getFieldTypeIconUrl() {
        return this.fieldTypeIconUrl;
    }
    
    public void setFieldTypeIconUrl(String fieldTypeIconUrl) {
        this.fieldTypeIconUrl = fieldTypeIconUrl;
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
    public Set getFieldses() {
        return this.fieldses;
    }
    
    public void setFieldses(Set fieldses) {
        this.fieldses = fieldses;
    }




}



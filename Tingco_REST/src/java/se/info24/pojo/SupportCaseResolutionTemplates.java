package se.info24.pojo;
// Generated Jun 5, 2013 11:23:30 AM by Hibernate Tools 3.2.1.GA


import java.io.Serializable;
import java.util.Date;

/**
 * SupportCaseResolutionTemplates generated by hbm2java
 */
public class SupportCaseResolutionTemplates  implements java.io.Serializable {


     private String resolutionTemplateId;
     private String resolutionSubject;
     private String resolutionText;
     private String groupId;
     private int countryId;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public SupportCaseResolutionTemplates() {
    }

	
    public SupportCaseResolutionTemplates(String resolutionTemplateId, String resolutionSubject, String resolutionText, String groupId, int countryId, String lastUpdatedByUserId) {
        this.resolutionTemplateId = resolutionTemplateId;
        this.resolutionSubject = resolutionSubject;
        this.resolutionText = resolutionText;
        this.groupId = groupId;
        this.countryId = countryId;
        this.lastUpdatedByUserId = lastUpdatedByUserId;
    }
    public SupportCaseResolutionTemplates(String resolutionTemplateId, String resolutionSubject, String resolutionText, String groupId, int countryId, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.resolutionTemplateId = resolutionTemplateId;
       this.resolutionSubject = resolutionSubject;
       this.resolutionText = resolutionText;
       this.groupId = groupId;
       this.countryId = countryId;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public String getResolutionTemplateId() {
        return this.resolutionTemplateId;
    }
    
    public void setResolutionTemplateId(String resolutionTemplateId) {
        this.resolutionTemplateId = resolutionTemplateId;
    }
    public String getResolutionSubject() {
        return this.resolutionSubject;
    }
    
    public void setResolutionSubject(String resolutionSubject) {
        this.resolutionSubject = resolutionSubject;
    }
    public String getResolutionText() {
        return this.resolutionText;
    }
    
    public void setResolutionText(String resolutionText) {
        this.resolutionText = resolutionText;
    }
    public String getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public int getCountryId() {
        return this.countryId;
    }
    
    public void setCountryId(int countryId) {
        this.countryId = countryId;
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


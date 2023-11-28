package se.info24.pojo;
// Generated Aug 23, 2012 11:52:15 AM by Hibernate Tools 3.2.1.GA


import java.io.Serializable;
import java.util.Date;

/**
 * ApplicationEmailTemplates generated by hbm2java
 */
public class ApplicationEmailTemplates  implements java.io.Serializable {


     private String applicationEmailTemplateId;
     private Applications applications;
     private String objectCode;
     private int countryId;
     private String emailSubject;
     private String emailBody;
     private String emailSignature;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public ApplicationEmailTemplates() {
    }

	
    public ApplicationEmailTemplates(String applicationEmailTemplateId, Applications applications, String objectCode, int countryId, String emailSubject, String emailBody, String emailSignature) {
        this.applicationEmailTemplateId = applicationEmailTemplateId;
        this.applications = applications;
        this.objectCode = objectCode;
        this.countryId = countryId;
        this.emailSubject = emailSubject;
        this.emailBody = emailBody;
        this.emailSignature = emailSignature;
    }
    public ApplicationEmailTemplates(String applicationEmailTemplateId, Applications applications, String objectCode, int countryId, String emailSubject, String emailBody, String emailSignature, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.applicationEmailTemplateId = applicationEmailTemplateId;
       this.applications = applications;
       this.objectCode = objectCode;
       this.countryId = countryId;
       this.emailSubject = emailSubject;
       this.emailBody = emailBody;
       this.emailSignature = emailSignature;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public String getApplicationEmailTemplateId() {
        return this.applicationEmailTemplateId;
    }
    
    public void setApplicationEmailTemplateId(String applicationEmailTemplateId) {
        this.applicationEmailTemplateId = applicationEmailTemplateId;
    }
    public Applications getApplications() {
        return this.applications;
    }
    
    public void setApplications(Applications applications) {
        this.applications = applications;
    }
    public String getObjectCode() {
        return this.objectCode;
    }
    
    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }
    public int getCountryId() {
        return this.countryId;
    }
    
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    public String getEmailSubject() {
        return this.emailSubject;
    }
    
    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }
    public String getEmailBody() {
        return this.emailBody;
    }
    
    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }
    public String getEmailSignature() {
        return this.emailSignature;
    }
    
    public void setEmailSignature(String emailSignature) {
        this.emailSignature = emailSignature;
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



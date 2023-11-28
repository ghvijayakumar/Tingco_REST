package se.info24.pojo;
// Generated Jun 14, 2012 10:11:42 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * ReportTemplateSettings generated by hbm2java
 */
public class ReportTemplateSettings  implements java.io.Serializable {


     private String reportTemplateSettingId;
     private String reportId;
     private String settingName;
     private String settingDescription;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public ReportTemplateSettings() {
    }

	
    public ReportTemplateSettings(String reportTemplateSettingId, String reportId, String settingName) {
        this.reportTemplateSettingId = reportTemplateSettingId;
        this.reportId = reportId;
        this.settingName = settingName;
    }
    public ReportTemplateSettings(String reportTemplateSettingId, String reportId, String settingName, String settingDescription, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.reportTemplateSettingId = reportTemplateSettingId;
       this.reportId = reportId;
       this.settingName = settingName;
       this.settingDescription = settingDescription;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public String getReportTemplateSettingId() {
        return this.reportTemplateSettingId;
    }
    
    public void setReportTemplateSettingId(String reportTemplateSettingId) {
        this.reportTemplateSettingId = reportTemplateSettingId;
    }
    public String getReportId() {
        return this.reportId;
    }
    
    public void setReportId(String reportId) {
        this.reportId = reportId;
    }
    public String getSettingName() {
        return this.settingName;
    }
    
    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }
    public String getSettingDescription() {
        return this.settingDescription;
    }
    
    public void setSettingDescription(String settingDescription) {
        this.settingDescription = settingDescription;
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


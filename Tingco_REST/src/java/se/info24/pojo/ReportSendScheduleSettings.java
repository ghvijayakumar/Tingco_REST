package se.info24.pojo;
// Generated Jun 12, 2012 3:45:13 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * ReportSendScheduleSettings generated by hbm2java
 */
public class ReportSendScheduleSettings  implements java.io.Serializable {


     private String reportSendScheduleSettingId;
     private String reportSendScheduleId;
     private String settingName;
     private String settingValue;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public ReportSendScheduleSettings() {
    }

	
    public ReportSendScheduleSettings(String reportSendScheduleSettingId, String reportSendScheduleId, String settingName) {
        this.reportSendScheduleSettingId = reportSendScheduleSettingId;
        this.reportSendScheduleId = reportSendScheduleId;
        this.settingName = settingName;
    }
    public ReportSendScheduleSettings(String reportSendScheduleSettingId, String reportSendScheduleId, String settingName, String settingValue, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.reportSendScheduleSettingId = reportSendScheduleSettingId;
       this.reportSendScheduleId = reportSendScheduleId;
       this.settingName = settingName;
       this.settingValue = settingValue;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public String getReportSendScheduleSettingId() {
        return this.reportSendScheduleSettingId;
    }
    
    public void setReportSendScheduleSettingId(String reportSendScheduleSettingId) {
        this.reportSendScheduleSettingId = reportSendScheduleSettingId;
    }
    public String getReportSendScheduleId() {
        return this.reportSendScheduleId;
    }
    
    public void setReportSendScheduleId(String reportSendScheduleId) {
        this.reportSendScheduleId = reportSendScheduleId;
    }
    public String getSettingName() {
        return this.settingName;
    }
    
    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }
    public String getSettingValue() {
        return this.settingValue;
    }
    
    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
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



package se.info24.pojo;
// Generated Jun 12, 2012 3:45:13 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * ReportSendSchedules generated by hbm2java
 */
public class ReportSendSchedules  implements java.io.Serializable {


     private String reportSendScheduleId;
     private String groupId;
     private String reportId;
     private String measurementTypeId;
     private String sendToEmail;
     private Integer sendHourly;
     private Integer sendDaily;
     private Integer sendWeekly;
     private Integer sendMonthly;
     private Integer sendQuarterly;
     private Integer sendYearly;
     private Integer sendOnMinute;
     private Integer sendOnHour;
     private Integer sendOnWeekday;
     private Integer sendOnDayOfMonth;
     private Integer sendOnMonth;
     private Integer sendAsPdf;
     private Integer sendAsCsv;
     private Integer sendAsExcel;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public ReportSendSchedules() {
    }

	
    public ReportSendSchedules(String reportSendScheduleId, String groupId, String reportId, String measurementTypeId, String sendToEmail) {
        this.reportSendScheduleId = reportSendScheduleId;
        this.groupId = groupId;
        this.reportId = reportId;
        this.measurementTypeId = measurementTypeId;
        this.sendToEmail = sendToEmail;
    }
    public ReportSendSchedules(String reportSendScheduleId, String groupId, String reportId, String measurementTypeId, String sendToEmail, Integer sendHourly, Integer sendDaily, Integer sendWeekly, Integer sendMonthly, Integer sendQuarterly, Integer sendYearly, Integer sendOnMinute, Integer sendOnHour, Integer sendOnWeekday, Integer sendOnDayOfMonth, Integer sendOnMonth, Integer sendAsPdf, Integer sendAsCsv, Integer sendAsExcel, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.reportSendScheduleId = reportSendScheduleId;
       this.groupId = groupId;
       this.reportId = reportId;
       this.measurementTypeId = measurementTypeId;
       this.sendToEmail = sendToEmail;
       this.sendHourly = sendHourly;
       this.sendDaily = sendDaily;
       this.sendWeekly = sendWeekly;
       this.sendMonthly = sendMonthly;
       this.sendQuarterly = sendQuarterly;
       this.sendYearly = sendYearly;
       this.sendOnMinute = sendOnMinute;
       this.sendOnHour = sendOnHour;
       this.sendOnWeekday = sendOnWeekday;
       this.sendOnDayOfMonth = sendOnDayOfMonth;
       this.sendOnMonth = sendOnMonth;
       this.sendAsPdf = sendAsPdf;
       this.sendAsCsv = sendAsCsv;
       this.sendAsExcel = sendAsExcel;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public String getReportSendScheduleId() {
        return this.reportSendScheduleId;
    }
    
    public void setReportSendScheduleId(String reportSendScheduleId) {
        this.reportSendScheduleId = reportSendScheduleId;
    }
    public String getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public String getReportId() {
        return this.reportId;
    }
    
    public void setReportId(String reportId) {
        this.reportId = reportId;
    }
    public String getMeasurementTypeId() {
        return this.measurementTypeId;
    }
    
    public void setMeasurementTypeId(String measurementTypeId) {
        this.measurementTypeId = measurementTypeId;
    }
    public String getSendToEmail() {
        return this.sendToEmail;
    }
    
    public void setSendToEmail(String sendToEmail) {
        this.sendToEmail = sendToEmail;
    }
    public Integer getSendHourly() {
        return this.sendHourly;
    }
    
    public void setSendHourly(Integer sendHourly) {
        this.sendHourly = sendHourly;
    }
    public Integer getSendDaily() {
        return this.sendDaily;
    }
    
    public void setSendDaily(Integer sendDaily) {
        this.sendDaily = sendDaily;
    }
    public Integer getSendWeekly() {
        return this.sendWeekly;
    }
    
    public void setSendWeekly(Integer sendWeekly) {
        this.sendWeekly = sendWeekly;
    }
    public Integer getSendMonthly() {
        return this.sendMonthly;
    }
    
    public void setSendMonthly(Integer sendMonthly) {
        this.sendMonthly = sendMonthly;
    }
    public Integer getSendQuarterly() {
        return this.sendQuarterly;
    }
    
    public void setSendQuarterly(Integer sendQuarterly) {
        this.sendQuarterly = sendQuarterly;
    }
    public Integer getSendYearly() {
        return this.sendYearly;
    }
    
    public void setSendYearly(Integer sendYearly) {
        this.sendYearly = sendYearly;
    }
    public Integer getSendOnMinute() {
        return this.sendOnMinute;
    }
    
    public void setSendOnMinute(Integer sendOnMinute) {
        this.sendOnMinute = sendOnMinute;
    }
    public Integer getSendOnHour() {
        return this.sendOnHour;
    }
    
    public void setSendOnHour(Integer sendOnHour) {
        this.sendOnHour = sendOnHour;
    }
    public Integer getSendOnWeekday() {
        return this.sendOnWeekday;
    }
    
    public void setSendOnWeekday(Integer sendOnWeekday) {
        this.sendOnWeekday = sendOnWeekday;
    }
    public Integer getSendOnDayOfMonth() {
        return this.sendOnDayOfMonth;
    }
    
    public void setSendOnDayOfMonth(Integer sendOnDayOfMonth) {
        this.sendOnDayOfMonth = sendOnDayOfMonth;
    }
    public Integer getSendOnMonth() {
        return this.sendOnMonth;
    }
    
    public void setSendOnMonth(Integer sendOnMonth) {
        this.sendOnMonth = sendOnMonth;
    }
    public Integer getSendAsPdf() {
        return this.sendAsPdf;
    }
    
    public void setSendAsPdf(Integer sendAsPdf) {
        this.sendAsPdf = sendAsPdf;
    }
    public Integer getSendAsCsv() {
        return this.sendAsCsv;
    }
    
    public void setSendAsCsv(Integer sendAsCsv) {
        this.sendAsCsv = sendAsCsv;
    }
    public Integer getSendAsExcel() {
        return this.sendAsExcel;
    }
    
    public void setSendAsExcel(Integer sendAsExcel) {
        this.sendAsExcel = sendAsExcel;
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


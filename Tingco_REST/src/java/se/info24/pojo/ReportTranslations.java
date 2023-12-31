package se.info24.pojo;
// Generated Jun 14, 2012 10:11:42 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * ReportTranslations generated by hbm2java
 */
public class ReportTranslations  implements java.io.Serializable {


     private ReportTranslationsId id;
     private String reportName;
     private String reportDescription;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;
     private Reports reports;

    public ReportTranslations() {
    }

	
    public ReportTranslations(ReportTranslationsId id, String reportName) {
        this.id = id;
        this.reportName = reportName;
    }
    public ReportTranslations(ReportTranslationsId id, String reportName, String reportDescription, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.reportName = reportName;
       this.reportDescription = reportDescription;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public ReportTranslationsId getId() {
        return this.id;
    }
    
    public void setId(ReportTranslationsId id) {
        this.id = id;
    }
    public String getReportName() {
        return this.reportName;
    }
    
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }
    public String getReportDescription() {
        return this.reportDescription;
    }
    
    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
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

    public Reports getReports() {
        return reports;
    }

    public void setReports(Reports reports) {
        this.reports = reports;
    }

}



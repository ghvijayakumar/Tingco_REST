package se.info24.pojo;
// Generated Jun 14, 2012 10:11:42 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Reports generated by hbm2java
 */
public class Reports  implements java.io.Serializable {


     private String reportId;
     private String groupId;
     private String reportCode;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;
     private Set reportTranslationses = new HashSet(0);

    public Reports() {
    }

	
    public Reports(String reportId, String groupId, String reportCode) {
        this.reportId = reportId;
        this.groupId = groupId;
        this.reportCode = reportCode;
    }
    public Reports(String reportId, String groupId, String reportCode, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.reportId = reportId;
       this.groupId = groupId;
       this.reportCode = reportCode;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public String getReportId() {
        return this.reportId;
    }
    
    public void setReportId(String reportId) {
        this.reportId = reportId;
    }
    public String getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public String getReportCode() {
        return this.reportCode;
    }
    
    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
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

    public Set getReportTranslationses() {
        return reportTranslationses;
    }

    public void setReportTranslationses(Set reportTranslationses) {
        this.reportTranslationses = reportTranslationses;
    }
}


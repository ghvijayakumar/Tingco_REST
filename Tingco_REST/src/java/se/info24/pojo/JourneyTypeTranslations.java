package se.info24.pojo;
// Generated Feb 8, 2012 2:19:05 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * JourneyTypeTranslations generated by hbm2java
 */
public class JourneyTypeTranslations  implements java.io.Serializable {


     private JourneyTypeTranslationsId id;
     private String journeyTypeName;
     private String journeyTypeDescription;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public JourneyTypeTranslations() {
    }

	
    public JourneyTypeTranslations(JourneyTypeTranslationsId id, String journeyTypeName) {
        this.id = id;
        this.journeyTypeName = journeyTypeName;
    }
    public JourneyTypeTranslations(JourneyTypeTranslationsId id, String journeyTypeName, String journeyTypeDescription, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.journeyTypeName = journeyTypeName;
       this.journeyTypeDescription = journeyTypeDescription;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public JourneyTypeTranslationsId getId() {
        return this.id;
    }
    
    public void setId(JourneyTypeTranslationsId id) {
        this.id = id;
    }
    public String getJourneyTypeName() {
        return this.journeyTypeName;
    }
    
    public void setJourneyTypeName(String journeyTypeName) {
        this.journeyTypeName = journeyTypeName;
    }
    public String getJourneyTypeDescription() {
        return this.journeyTypeDescription;
    }
    
    public void setJourneyTypeDescription(String journeyTypeDescription) {
        this.journeyTypeDescription = journeyTypeDescription;
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



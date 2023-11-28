package se.info24.pojo;
// Generated Dec 2, 2010 3:59:27 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * ApplicationTranslations generated by hbm2java
 */
public class ApplicationTranslations  implements java.io.Serializable {


     private ApplicationTranslationsId id;
     private Country country;
     private Applications applications;
     private String applicationName;
     private String applicationDescription;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public ApplicationTranslations() {
    }

	
    public ApplicationTranslations(ApplicationTranslationsId id, Country country, Applications applications, String applicationName) {
        this.id = id;
        this.country = country;
        this.applications = applications;
        this.applicationName = applicationName;
    }
    public ApplicationTranslations(ApplicationTranslationsId id, Country country, Applications applications, String applicationName, String applicationDescription, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.country = country;
       this.applications = applications;
       this.applicationName = applicationName;
       this.applicationDescription = applicationDescription;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public ApplicationTranslationsId getId() {
        return this.id;
    }
    
    public void setId(ApplicationTranslationsId id) {
        this.id = id;
    }
    public Country getCountry() {
        return this.country;
    }
    
    public void setCountry(Country country) {
        this.country = country;
    }
    public Applications getApplications() {
        return this.applications;
    }
    
    public void setApplications(Applications applications) {
        this.applications = applications;
    }
    public String getApplicationName() {
        return this.applicationName;
    }
    
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
    public String getApplicationDescription() {
        return this.applicationDescription;
    }
    
    public void setApplicationDescription(String applicationDescription) {
        this.applicationDescription = applicationDescription;
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


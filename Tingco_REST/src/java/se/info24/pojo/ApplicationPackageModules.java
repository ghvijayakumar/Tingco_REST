package se.info24.pojo;
// Generated Aug 13, 2012 2:28:58 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * ApplicationPackageModules generated by hbm2java
 */
public class ApplicationPackageModules  implements java.io.Serializable {


     private ApplicationPackageModulesId id;
     private ApplicationPackages applicationPackages;
     private ApplicationModules applicationModules;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public ApplicationPackageModules() {
    }

	
    public ApplicationPackageModules(ApplicationPackageModulesId id, ApplicationPackages applicationPackages, ApplicationModules applicationModules) {
        this.id = id;
        this.applicationPackages = applicationPackages;
        this.applicationModules = applicationModules;
    }
    public ApplicationPackageModules(ApplicationPackageModulesId id, ApplicationPackages applicationPackages, ApplicationModules applicationModules, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.applicationPackages = applicationPackages;
       this.applicationModules = applicationModules;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public ApplicationPackageModulesId getId() {
        return this.id;
    }
    
    public void setId(ApplicationPackageModulesId id) {
        this.id = id;
    }
    public ApplicationPackages getApplicationPackages() {
        return this.applicationPackages;
    }
    
    public void setApplicationPackages(ApplicationPackages applicationPackages) {
        this.applicationPackages = applicationPackages;
    }
    public ApplicationModules getApplicationModules() {
        return this.applicationModules;
    }
    
    public void setApplicationModules(ApplicationModules applicationModules) {
        this.applicationModules = applicationModules;
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



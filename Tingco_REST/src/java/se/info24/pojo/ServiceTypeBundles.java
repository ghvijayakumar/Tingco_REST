package se.info24.pojo;
// Generated Jun 11, 2014 10:44:07 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * ServiceTypeBundles generated by hbm2java
 */
public class ServiceTypeBundles  implements java.io.Serializable {


     private ServiceTypeBundlesId id;
     private Bundle bundle;
     private ServiceTypes serviceTypes;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public ServiceTypeBundles() {
    }

	
    public ServiceTypeBundles(ServiceTypeBundlesId id, Bundle bundle, ServiceTypes serviceTypes, String lastUpdatedByUserId) {
        this.id = id;
        this.bundle = bundle;
        this.serviceTypes = serviceTypes;
        this.lastUpdatedByUserId = lastUpdatedByUserId;
    }
    public ServiceTypeBundles(ServiceTypeBundlesId id, Bundle bundle, ServiceTypes serviceTypes, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.bundle = bundle;
       this.serviceTypes = serviceTypes;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public ServiceTypeBundlesId getId() {
        return this.id;
    }
    
    public void setId(ServiceTypeBundlesId id) {
        this.id = id;
    }
    public Bundle getBundle() {
        return this.bundle;
    }
    
    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }
    public ServiceTypes getServiceTypes() {
        return this.serviceTypes;
    }
    
    public void setServiceTypes(ServiceTypes serviceTypes) {
        this.serviceTypes = serviceTypes;
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



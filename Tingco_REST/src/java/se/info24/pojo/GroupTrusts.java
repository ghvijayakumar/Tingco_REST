package se.info24.pojo;
// Generated Aug 3, 2011 5:08:12 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * GroupTrusts generated by hbm2java
 */
public class GroupTrusts  implements java.io.Serializable {


     private GroupTrustsId id;
     private Date activeFromDate;
     private Date activeToDate;
     private Integer isInternal;
     private Integer isCustomer;
     private Integer isSupplier;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public GroupTrusts() {
    }

	
    public GroupTrusts(GroupTrustsId id, Date activeFromDate, Date activeToDate) {
        this.id = id;
        this.activeFromDate = activeFromDate;
        this.activeToDate = activeToDate;
    }
    public GroupTrusts(GroupTrustsId id, Date activeFromDate, Date activeToDate, Integer isInternal, Integer isCustomer, Integer isSupplier, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.activeFromDate = activeFromDate;
       this.activeToDate = activeToDate;
       this.isInternal = isInternal;
       this.isCustomer = isCustomer;
       this.isSupplier = isSupplier;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public GroupTrustsId getId() {
        return this.id;
    }
    
    public void setId(GroupTrustsId id) {
        this.id = id;
    }
    public Date getActiveFromDate() {
        return this.activeFromDate;
    }
    
    public void setActiveFromDate(Date activeFromDate) {
        this.activeFromDate = activeFromDate;
    }
    public Date getActiveToDate() {
        return this.activeToDate;
    }
    
    public void setActiveToDate(Date activeToDate) {
        this.activeToDate = activeToDate;
    }
    public Integer getIsInternal() {
        return this.isInternal;
    }
    
    public void setIsInternal(Integer isInternal) {
        this.isInternal = isInternal;
    }
    public Integer getIsCustomer() {
        return this.isCustomer;
    }
    
    public void setIsCustomer(Integer isCustomer) {
        this.isCustomer = isCustomer;
    }
    public Integer getIsSupplier() {
        return this.isSupplier;
    }
    
    public void setIsSupplier(Integer isSupplier) {
        this.isSupplier = isSupplier;
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



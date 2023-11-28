package se.info24.pojo;
// Generated Aug 13, 2012 2:28:58 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * GroupApplicationPackages generated by hbm2java
 */
public class GroupApplicationPackages  implements java.io.Serializable {


     private GroupApplicationPackagesId id;
     private ApplicationPackages applicationPackages;
//     private Users2 users2;
     private String orderedByUserId;
     private Agreements agreements;
     private String comment;
     private Date activeFromDate;
     private Date activeToDate;
     private int isEnabled;
     private int isTrial;
     private int isPendingDelete;
     private Date deleteAfterThisDate;
     private String lastUpdatedByUserId;
     private Date cretedDate;
     private Date updatedDate;

    public GroupApplicationPackages() {
    }

	
    public GroupApplicationPackages(GroupApplicationPackagesId id, ApplicationPackages applicationPackages, String orderedByUserId, Agreements agreements, Date activeFromDate, Date activeToDate, int isEnabled, int isTrial, int isPendingDelete) {
        this.id = id;
        this.applicationPackages = applicationPackages;
        this.orderedByUserId = orderedByUserId;
        this.agreements = agreements;
        this.activeFromDate = activeFromDate;
        this.activeToDate = activeToDate;
        this.isEnabled = isEnabled;
        this.isTrial = isTrial;
        this.isPendingDelete = isPendingDelete;
    }
    public GroupApplicationPackages(GroupApplicationPackagesId id, ApplicationPackages applicationPackages, String orderedByUserId, Agreements agreements, String comment, Date activeFromDate, Date activeToDate, int isEnabled, int isTrial, int isPendingDelete, Date deleteAfterThisDate, String lastUpdatedByUserId, Date cretedDate, Date updatedDate) {
       this.id = id;
       this.applicationPackages = applicationPackages;
       this.orderedByUserId = orderedByUserId;
       this.agreements = agreements;
       this.comment = comment;
       this.activeFromDate = activeFromDate;
       this.activeToDate = activeToDate;
       this.isEnabled = isEnabled;
       this.isTrial = isTrial;
       this.isPendingDelete = isPendingDelete;
       this.deleteAfterThisDate = deleteAfterThisDate;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.cretedDate = cretedDate;
       this.updatedDate = updatedDate;
    }
   
    public GroupApplicationPackagesId getId() {
        return this.id;
    }
    
    public void setId(GroupApplicationPackagesId id) {
        this.id = id;
    }
    public ApplicationPackages getApplicationPackages() {
        return this.applicationPackages;
    }
    
    public void setApplicationPackages(ApplicationPackages applicationPackages) {
        this.applicationPackages = applicationPackages;
    }

    public String getOrderedByUserId() {
        return orderedByUserId;
    }

    public void setOrderedByUserId(String orderedByUserId) {
        this.orderedByUserId = orderedByUserId;
    }



//    public Users2 getUsers2() {
//        return this.users2;
//    }
//
//    public void setUsers2(Users2 users2) {
//        this.users2 = users2;
//    }
    
    public Agreements getAgreements() {
        return this.agreements;
    }
    
    public void setAgreements(Agreements agreements) {
        this.agreements = agreements;
    }
    public String getComment() {
        return this.comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
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
    public int getIsEnabled() {
        return this.isEnabled;
    }
    
    public void setIsEnabled(int isEnabled) {
        this.isEnabled = isEnabled;
    }
    public int getIsTrial() {
        return this.isTrial;
    }
    
    public void setIsTrial(int isTrial) {
        this.isTrial = isTrial;
    }
    public int getIsPendingDelete() {
        return this.isPendingDelete;
    }
    
    public void setIsPendingDelete(int isPendingDelete) {
        this.isPendingDelete = isPendingDelete;
    }
    public Date getDeleteAfterThisDate() {
        return this.deleteAfterThisDate;
    }
    
    public void setDeleteAfterThisDate(Date deleteAfterThisDate) {
        this.deleteAfterThisDate = deleteAfterThisDate;
    }
    public String getLastUpdatedByUserId() {
        return this.lastUpdatedByUserId;
    }
    
    public void setLastUpdatedByUserId(String lastUpdatedByUserId) {
        this.lastUpdatedByUserId = lastUpdatedByUserId;
    }
    public Date getCretedDate() {
        return this.cretedDate;
    }
    
    public void setCretedDate(Date cretedDate) {
        this.cretedDate = cretedDate;
    }
    public Date getUpdatedDate() {
        return this.updatedDate;
    }
    
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }


}


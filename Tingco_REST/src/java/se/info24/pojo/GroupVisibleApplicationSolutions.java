package se.info24.pojo;
// Generated Aug 13, 2012 2:28:58 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * GroupVisibleApplicationSolutions generated by hbm2java
 */
public class GroupVisibleApplicationSolutions  implements java.io.Serializable {


     private GroupVisibleApplicationSolutionsId id;
     private ApplicationSolutions applicationSolutions;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public GroupVisibleApplicationSolutions() {
    }

	
    public GroupVisibleApplicationSolutions(GroupVisibleApplicationSolutionsId id, ApplicationSolutions applicationSolutions) {
        this.id = id;
        this.applicationSolutions = applicationSolutions;
    }
    public GroupVisibleApplicationSolutions(GroupVisibleApplicationSolutionsId id, ApplicationSolutions applicationSolutions, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.applicationSolutions = applicationSolutions;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public GroupVisibleApplicationSolutionsId getId() {
        return this.id;
    }
    
    public void setId(GroupVisibleApplicationSolutionsId id) {
        this.id = id;
    }
    public ApplicationSolutions getApplicationSolutions() {
        return this.applicationSolutions;
    }
    
    public void setApplicationSolutions(ApplicationSolutions applicationSolutions) {
        this.applicationSolutions = applicationSolutions;
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



package se.info24.ismOperationsPojo;
// Generated Aug 25, 2011 3:25:26 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * ContentGroupSubscriptions generated by hbm2java
 */
public class ContentGroupSubscriptions  implements java.io.Serializable {


     private ContentGroupSubscriptionsId id;
     private String serviceSubscriptionAclid;
     private Integer isEnabled;
     private Date createdDate;
     private Date updatedDate;

    public ContentGroupSubscriptions() {
    }

	
    public ContentGroupSubscriptions(ContentGroupSubscriptionsId id) {
        this.id = id;
    }
    public ContentGroupSubscriptions(ContentGroupSubscriptionsId id, String serviceSubscriptionAclid, Integer isEnabled, Date createdDate, Date updatedDate) {
       this.id = id;
       this.serviceSubscriptionAclid = serviceSubscriptionAclid;
       this.isEnabled = isEnabled;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public ContentGroupSubscriptionsId getId() {
        return this.id;
    }
    
    public void setId(ContentGroupSubscriptionsId id) {
        this.id = id;
    }
    public String getServiceSubscriptionAclid() {
        return this.serviceSubscriptionAclid;
    }
    
    public void setServiceSubscriptionAclid(String serviceSubscriptionAclid) {
        this.serviceSubscriptionAclid = serviceSubscriptionAclid;
    }
    public Integer getIsEnabled() {
        return this.isEnabled;
    }
    
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
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


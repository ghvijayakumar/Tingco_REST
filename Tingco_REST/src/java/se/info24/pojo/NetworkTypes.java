package se.info24.pojo;
// Generated Feb 23, 2011 12:47:44 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * NetworkTypes generated by hbm2java
 */
public class NetworkTypes  implements java.io.Serializable {


     private String networkTypeId;
     private String networkTypeName;
     private String networkTypeDescription;
     private String userId;
     private Date createdDate;
     private Date updatedDate;
//     private Set networkTypeMembershipses = new HashSet(0);
     private Set networkSettingsTemplates = new HashSet(0);
     private Set networkSubscriptionses = new HashSet(0);

    public NetworkTypes() {
    }

	
    public NetworkTypes(String networkTypeId) {
        this.networkTypeId = networkTypeId;
    }
    public NetworkTypes(String networkTypeId, String networkTypeName, String networkTypeDescription, String userId, Date createdDate, Date updatedDate, Set networkTypeMembershipses, Set networkSettingsTemplates, Set networkSubscriptionses) {
       this.networkTypeId = networkTypeId;
       this.networkTypeName = networkTypeName;
       this.networkTypeDescription = networkTypeDescription;
       this.userId = userId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
//       this.networkTypeMembershipses = networkTypeMembershipses;
       this.networkSettingsTemplates = networkSettingsTemplates;
       this.networkSubscriptionses = networkSubscriptionses;
    }
   
    public String getNetworkTypeId() {
        return this.networkTypeId;
    }
    
    public void setNetworkTypeId(String networkTypeId) {
        this.networkTypeId = networkTypeId;
    }
    public String getNetworkTypeName() {
        return this.networkTypeName;
    }
    
    public void setNetworkTypeName(String networkTypeName) {
        this.networkTypeName = networkTypeName;
    }
    public String getNetworkTypeDescription() {
        return this.networkTypeDescription;
    }
    
    public void setNetworkTypeDescription(String networkTypeDescription) {
        this.networkTypeDescription = networkTypeDescription;
    }
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
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
//    public Set getNetworkTypeMembershipses() {
//        return this.networkTypeMembershipses;
//    }
//
//    public void setNetworkTypeMembershipses(Set networkTypeMembershipses) {
//        this.networkTypeMembershipses = networkTypeMembershipses;
//    }
    public Set getNetworkSettingsTemplates() {
        return this.networkSettingsTemplates;
    }

    public void setNetworkSettingsTemplates(Set networkSettingsTemplates) {
        this.networkSettingsTemplates = networkSettingsTemplates;
    }
    public Set getNetworkSubscriptionses() {
        return this.networkSubscriptionses;
    }

    public void setNetworkSubscriptionses(Set networkSubscriptionses) {
        this.networkSubscriptionses = networkSubscriptionses;
    }




}



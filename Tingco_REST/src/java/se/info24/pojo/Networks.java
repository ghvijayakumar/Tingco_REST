package se.info24.pojo;
// Generated May 2, 2011 11:42:57 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Networks generated by hbm2java
 */
public class Networks  implements java.io.Serializable {


     private String networkId;
     private String networkName;
     private String networkDescription;
     private String userId;
     private Date createdDate;
     private Date updatedDate;
//     private Set networkTypeMembershipses = new HashSet(0);
//     private Set networkDetailses = new HashSet(0);
//     private Set networkAddresseses = new HashSet(0);
//     private Set networkSubscriptionses = new HashSet(0);

    public Networks() {
    }

	
    public Networks(String networkId, String networkName) {
        this.networkId = networkId;
        this.networkName = networkName;
    }
//    public Networks(String networkId, String networkName, String networkDescription, String userId, Date createdDate, Date updatedDate, Set networkTypeMembershipses, Set networkDetailses, Set networkAddresseses, Set networkSubscriptionses) {
//       this.networkId = networkId;
//       this.networkName = networkName;
//       this.networkDescription = networkDescription;
//       this.userId = userId;
//       this.createdDate = createdDate;
//       this.updatedDate = updatedDate;
//       this.networkTypeMembershipses = networkTypeMembershipses;
//       this.networkDetailses = networkDetailses;
//       this.networkAddresseses = networkAddresseses;
//       this.networkSubscriptionses = networkSubscriptionses;
//    }
   
    public String getNetworkId() {
        return this.networkId;
    }
    
    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }
    public String getNetworkName() {
        return this.networkName;
    }
    
    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }
    public String getNetworkDescription() {
        return this.networkDescription;
    }
    
    public void setNetworkDescription(String networkDescription) {
        this.networkDescription = networkDescription;
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
//    public Set getNetworkDetailses() {
//        return this.networkDetailses;
//    }
//
//    public void setNetworkDetailses(Set networkDetailses) {
//        this.networkDetailses = networkDetailses;
//    }
//    public Set getNetworkAddresseses() {
//        return this.networkAddresseses;
//    }
//
//    public void setNetworkAddresseses(Set networkAddresseses) {
//        this.networkAddresseses = networkAddresseses;
//    }
//    public Set getNetworkSubscriptionses() {
//        return this.networkSubscriptionses;
//    }
//
//    public void setNetworkSubscriptionses(Set networkSubscriptionses) {
//        this.networkSubscriptionses = networkSubscriptionses;
//    }




}



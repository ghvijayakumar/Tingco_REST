package se.info24.pojo;
// Generated Jul 13, 2012 11:55:13 AM by Hibernate Tools 3.2.1.GA



/**
 * ServiceTypesInGroupsId generated by hbm2java
 */
public class ServiceTypesInGroupsId  implements java.io.Serializable {


     private String serviceTypeId;
     private String groupId;

    public ServiceTypesInGroupsId() {
    }

    public ServiceTypesInGroupsId(String serviceTypeId, String groupId) {
       this.serviceTypeId = serviceTypeId;
       this.groupId = groupId;
    }
   
    public String getServiceTypeId() {
        return this.serviceTypeId;
    }
    
    public void setServiceTypeId(String serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }
    public String getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ServiceTypesInGroupsId) ) return false;
		 ServiceTypesInGroupsId castOther = ( ServiceTypesInGroupsId ) other; 
         
		 return ( (this.getServiceTypeId()==castOther.getServiceTypeId()) || ( this.getServiceTypeId()!=null && castOther.getServiceTypeId()!=null && this.getServiceTypeId().equals(castOther.getServiceTypeId()) ) )
 && ( (this.getGroupId()==castOther.getGroupId()) || ( this.getGroupId()!=null && castOther.getGroupId()!=null && this.getGroupId().equals(castOther.getGroupId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getServiceTypeId() == null ? 0 : this.getServiceTypeId().hashCode() );
         result = 37 * result + ( getGroupId() == null ? 0 : this.getGroupId().hashCode() );
         return result;
   }   


}



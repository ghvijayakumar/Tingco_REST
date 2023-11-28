package se.info24.pojo;
// Generated Dec 2, 2010 3:59:27 PM by Hibernate Tools 3.2.1.GA



/**
 * ApplicationGroupMembershipsId generated by hbm2java
 */
public class ApplicationGroupMembershipsId  implements java.io.Serializable {


     private String applicationId;
     private String groupId;

    public ApplicationGroupMembershipsId() {
    }

    public ApplicationGroupMembershipsId(String applicationId, String groupId) {
       this.applicationId = applicationId;
       this.groupId = groupId;
    }
   
    public String getApplicationId() {
        return this.applicationId;
    }
    
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
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
		 if ( !(other instanceof ApplicationGroupMembershipsId) ) return false;
		 ApplicationGroupMembershipsId castOther = ( ApplicationGroupMembershipsId ) other; 
         
		 return ( (this.getApplicationId()==castOther.getApplicationId()) || ( this.getApplicationId()!=null && castOther.getApplicationId()!=null && this.getApplicationId().equals(castOther.getApplicationId()) ) )
 && ( (this.getGroupId()==castOther.getGroupId()) || ( this.getGroupId()!=null && castOther.getGroupId()!=null && this.getGroupId().equals(castOther.getGroupId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getApplicationId() == null ? 0 : this.getApplicationId().hashCode() );
         result = 37 * result + ( getGroupId() == null ? 0 : this.getGroupId().hashCode() );
         return result;
   }   


}


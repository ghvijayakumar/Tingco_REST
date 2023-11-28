package se.info24.pojo;
// Generated Jan 11, 2011 1:59:16 PM by Hibernate Tools 3.2.1.GA



/**
 * UserRoleMembershipsToActivateId generated by hbm2java
 */
public class UserRoleMembershipsToActivateId  implements java.io.Serializable {


     private String userId;
     private String userRoleId;

    public UserRoleMembershipsToActivateId() {
    }

    public UserRoleMembershipsToActivateId(String userId, String userRoleId) {
       this.userId = userId;
       this.userRoleId = userRoleId;
    }
   
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserRoleId() {
        return this.userRoleId;
    }
    
    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof UserRoleMembershipsToActivateId) ) return false;
		 UserRoleMembershipsToActivateId castOther = ( UserRoleMembershipsToActivateId ) other; 
         
		 return ( (this.getUserId()==castOther.getUserId()) || ( this.getUserId()!=null && castOther.getUserId()!=null && this.getUserId().equals(castOther.getUserId()) ) )
 && ( (this.getUserRoleId()==castOther.getUserRoleId()) || ( this.getUserRoleId()!=null && castOther.getUserRoleId()!=null && this.getUserRoleId().equals(castOther.getUserRoleId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUserId() == null ? 0 : this.getUserId().hashCode() );
         result = 37 * result + ( getUserRoleId() == null ? 0 : this.getUserRoleId().hashCode() );
         return result;
   }   


}


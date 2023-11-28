package se.info24.pojo;
// Generated Jan 3, 2011 5:17:04 PM by Hibernate Tools 3.2.1.GA



/**
 * GroupTrustsId generated by hbm2java
 */
public class GroupTrustsId  implements java.io.Serializable {


     private String groupId;
     private String itrustGroupId;
     private String userRoleId;

    public GroupTrustsId() {
    }

    public GroupTrustsId(String groupId, String itrustGroupId, String userRoleId) {
       this.groupId = groupId;
       this.itrustGroupId = itrustGroupId;
       this.userRoleId = userRoleId;
    }
   
    public String getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public String getItrustGroupId() {
        return this.itrustGroupId;
    }
    
    public void setItrustGroupId(String itrustGroupId) {
        this.itrustGroupId = itrustGroupId;
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
		 if ( !(other instanceof GroupTrustsId) ) return false;
		 GroupTrustsId castOther = ( GroupTrustsId ) other; 
         
		 return ( (this.getGroupId()==castOther.getGroupId()) || ( this.getGroupId()!=null && castOther.getGroupId()!=null && this.getGroupId().equals(castOther.getGroupId()) ) )
 && ( (this.getItrustGroupId()==castOther.getItrustGroupId()) || ( this.getItrustGroupId()!=null && castOther.getItrustGroupId()!=null && this.getItrustGroupId().equals(castOther.getItrustGroupId()) ) )
 && ( (this.getUserRoleId()==castOther.getUserRoleId()) || ( this.getUserRoleId()!=null && castOther.getUserRoleId()!=null && this.getUserRoleId().equals(castOther.getUserRoleId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getGroupId() == null ? 0 : this.getGroupId().hashCode() );
         result = 37 * result + ( getItrustGroupId() == null ? 0 : this.getItrustGroupId().hashCode() );
         result = 37 * result + ( getUserRoleId() == null ? 0 : this.getUserRoleId().hashCode() );
         return result;
   }   


}



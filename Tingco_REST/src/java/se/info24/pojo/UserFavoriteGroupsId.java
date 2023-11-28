package se.info24.pojo;
// Generated Aug 24, 2011 3:27:23 PM by Hibernate Tools 3.2.1.GA



/**
 * UserFavoriteGroupsId generated by hbm2java
 */
public class UserFavoriteGroupsId  implements java.io.Serializable {


     private String userId;
     private String groupId;

    public UserFavoriteGroupsId() {
    }

    public UserFavoriteGroupsId(String userId, String groupId) {
       this.userId = userId;
       this.groupId = groupId;
    }
   
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
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
		 if ( !(other instanceof UserFavoriteGroupsId) ) return false;
		 UserFavoriteGroupsId castOther = ( UserFavoriteGroupsId ) other; 
         
		 return ( (this.getUserId()==castOther.getUserId()) || ( this.getUserId()!=null && castOther.getUserId()!=null && this.getUserId().equals(castOther.getUserId()) ) )
 && ( (this.getGroupId()==castOther.getGroupId()) || ( this.getGroupId()!=null && castOther.getGroupId()!=null && this.getGroupId().equals(castOther.getGroupId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUserId() == null ? 0 : this.getUserId().hashCode() );
         result = 37 * result + ( getGroupId() == null ? 0 : this.getGroupId().hashCode() );
         return result;
   }   


}



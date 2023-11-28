package se.info24.pojo;
// Generated Jul 18, 2013 3:21:10 PM by Hibernate Tools 3.2.1.GA



/**
 * UserFavoriteDataItemsId generated by hbm2java
 */
public class UserFavoriteDataItemsId  implements java.io.Serializable {


     private String userId;
     private String deviceDataItemId;

    public UserFavoriteDataItemsId() {
    }

    public UserFavoriteDataItemsId(String userId, String deviceDataItemId) {
       this.userId = userId;
       this.deviceDataItemId = deviceDataItemId;
    }
   
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getDeviceDataItemId() {
        return this.deviceDataItemId;
    }
    
    public void setDeviceDataItemId(String deviceDataItemId) {
        this.deviceDataItemId = deviceDataItemId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof UserFavoriteDataItemsId) ) return false;
		 UserFavoriteDataItemsId castOther = ( UserFavoriteDataItemsId ) other; 
         
		 return ( (this.getUserId()==castOther.getUserId()) || ( this.getUserId()!=null && castOther.getUserId()!=null && this.getUserId().equals(castOther.getUserId()) ) )
 && ( (this.getDeviceDataItemId()==castOther.getDeviceDataItemId()) || ( this.getDeviceDataItemId()!=null && castOther.getDeviceDataItemId()!=null && this.getDeviceDataItemId().equals(castOther.getDeviceDataItemId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUserId() == null ? 0 : this.getUserId().hashCode() );
         result = 37 * result + ( getDeviceDataItemId() == null ? 0 : this.getDeviceDataItemId().hashCode() );
         return result;
   }   


}



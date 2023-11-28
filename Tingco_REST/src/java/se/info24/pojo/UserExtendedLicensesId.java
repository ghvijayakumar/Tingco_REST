package se.info24.pojo;
// Generated Jul 24, 2012 7:35:11 PM by Hibernate Tools 3.2.1.GA



/**
 * UserExtendedLicensesId generated by hbm2java
 */
public class UserExtendedLicensesId  implements java.io.Serializable {


     private String userId;
     private String applicationId;

    public UserExtendedLicensesId() {
    }

    public UserExtendedLicensesId(String userId, String applicationId) {
       this.userId = userId;
       this.applicationId = applicationId;
    }
   
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getApplicationId() {
        return this.applicationId;
    }
    
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof UserExtendedLicensesId) ) return false;
		 UserExtendedLicensesId castOther = ( UserExtendedLicensesId ) other; 
         
		 return ( (this.getUserId()==castOther.getUserId()) || ( this.getUserId()!=null && castOther.getUserId()!=null && this.getUserId().equals(castOther.getUserId()) ) )
 && ( (this.getApplicationId()==castOther.getApplicationId()) || ( this.getApplicationId()!=null && castOther.getApplicationId()!=null && this.getApplicationId().equals(castOther.getApplicationId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUserId() == null ? 0 : this.getUserId().hashCode() );
         result = 37 * result + ( getApplicationId() == null ? 0 : this.getApplicationId().hashCode() );
         return result;
   }   


}



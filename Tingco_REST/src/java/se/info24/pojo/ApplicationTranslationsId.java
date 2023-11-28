package se.info24.pojo;
// Generated Dec 2, 2010 3:59:27 PM by Hibernate Tools 3.2.1.GA



/**
 * ApplicationTranslationsId generated by hbm2java
 */
public class ApplicationTranslationsId  implements java.io.Serializable {


     private String applicationId;
     private int countryId;

    public ApplicationTranslationsId() {
    }

    public ApplicationTranslationsId(String applicationId, int countryId) {
       this.applicationId = applicationId;
       this.countryId = countryId;
    }
   
    public String getApplicationId() {
        return this.applicationId;
    }
    
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
    public int getCountryId() {
        return this.countryId;
    }
    
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ApplicationTranslationsId) ) return false;
		 ApplicationTranslationsId castOther = ( ApplicationTranslationsId ) other; 
         
		 return ( (this.getApplicationId()==castOther.getApplicationId()) || ( this.getApplicationId()!=null && castOther.getApplicationId()!=null && this.getApplicationId().equals(castOther.getApplicationId()) ) )
 && (this.getCountryId()==castOther.getCountryId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getApplicationId() == null ? 0 : this.getApplicationId().hashCode() );
         result = 37 * result + this.getCountryId();
         return result;
   }   


}



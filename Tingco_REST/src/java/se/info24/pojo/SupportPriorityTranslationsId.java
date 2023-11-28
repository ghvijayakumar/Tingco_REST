package se.info24.pojo;
// Generated Jun 5, 2013 11:23:30 AM by Hibernate Tools 3.2.1.GA



/**
 * SupportPriorityTranslationsId generated by hbm2java
 */
public class SupportPriorityTranslationsId  implements java.io.Serializable {


     private String supportCasePriorityId;
     private int countryId;

    public SupportPriorityTranslationsId() {
    }

    public SupportPriorityTranslationsId(String supportCasePriorityId, int countryId) {
       this.supportCasePriorityId = supportCasePriorityId;
       this.countryId = countryId;
    }
   
    public String getSupportCasePriorityId() {
        return this.supportCasePriorityId;
    }
    
    public void setSupportCasePriorityId(String supportCasePriorityId) {
        this.supportCasePriorityId = supportCasePriorityId;
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
		 if ( !(other instanceof SupportPriorityTranslationsId) ) return false;
		 SupportPriorityTranslationsId castOther = ( SupportPriorityTranslationsId ) other; 
         
		 return ( (this.getSupportCasePriorityId()==castOther.getSupportCasePriorityId()) || ( this.getSupportCasePriorityId()!=null && castOther.getSupportCasePriorityId()!=null && this.getSupportCasePriorityId().equals(castOther.getSupportCasePriorityId()) ) )
 && (this.getCountryId()==castOther.getCountryId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getSupportCasePriorityId() == null ? 0 : this.getSupportCasePriorityId().hashCode() );
         result = 37 * result + this.getCountryId();
         return result;
   }   


}


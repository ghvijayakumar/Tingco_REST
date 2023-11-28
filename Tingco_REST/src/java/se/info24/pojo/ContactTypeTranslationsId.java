package se.info24.pojo;
// Generated Jul 5, 2013 12:26:18 PM by Hibernate Tools 3.2.1.GA



/**
 * ContactTypeTranslationsId generated by hbm2java
 */
public class ContactTypeTranslationsId  implements java.io.Serializable {


     private String contactTypeId;
     private int countryId;

    public ContactTypeTranslationsId() {
    }

    public ContactTypeTranslationsId(String contactTypeId, int countryId) {
       this.contactTypeId = contactTypeId;
       this.countryId = countryId;
    }
   
    public String getContactTypeId() {
        return this.contactTypeId;
    }
    
    public void setContactTypeId(String contactTypeId) {
        this.contactTypeId = contactTypeId;
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
		 if ( !(other instanceof ContactTypeTranslationsId) ) return false;
		 ContactTypeTranslationsId castOther = ( ContactTypeTranslationsId ) other; 
         
		 return ( (this.getContactTypeId()==castOther.getContactTypeId()) || ( this.getContactTypeId()!=null && castOther.getContactTypeId()!=null && this.getContactTypeId().equals(castOther.getContactTypeId()) ) )
 && (this.getCountryId()==castOther.getCountryId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getContactTypeId() == null ? 0 : this.getContactTypeId().hashCode() );
         result = 37 * result + this.getCountryId();
         return result;
   }   


}



package se.info24.pojo;
// Generated Jul 5, 2013 12:26:18 PM by Hibernate Tools 3.2.1.GA



/**
 * ContactFieldTranslationsId generated by hbm2java
 */
public class ContactFieldTranslationsId  implements java.io.Serializable {


     private String contactTypeFieldId;
     private int countryId;

    public ContactFieldTranslationsId() {
    }

    public ContactFieldTranslationsId(String contactTypeFieldId, int countryId) {
       this.contactTypeFieldId = contactTypeFieldId;
       this.countryId = countryId;
    }
   
    public String getContactTypeFieldId() {
        return this.contactTypeFieldId;
    }
    
    public void setContactTypeFieldId(String contactTypeFieldId) {
        this.contactTypeFieldId = contactTypeFieldId;
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
		 if ( !(other instanceof ContactFieldTranslationsId) ) return false;
		 ContactFieldTranslationsId castOther = ( ContactFieldTranslationsId ) other; 
         
		 return ( (this.getContactTypeFieldId()==castOther.getContactTypeFieldId()) || ( this.getContactTypeFieldId()!=null && castOther.getContactTypeFieldId()!=null && this.getContactTypeFieldId().equals(castOther.getContactTypeFieldId()) ) )
 && (this.getCountryId()==castOther.getCountryId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getContactTypeFieldId() == null ? 0 : this.getContactTypeFieldId().hashCode() );
         result = 37 * result + this.getCountryId();
         return result;
   }   


}



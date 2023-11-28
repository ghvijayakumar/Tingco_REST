package se.info24.pojo;
// Generated Oct 4, 2012 10:52:52 AM by Hibernate Tools 3.2.1.GA



/**
 * ObjectGroupTranslationsId generated by hbm2java
 */
public class ObjectGroupTranslationsId  implements java.io.Serializable {


     private String objectGroupId;
     private int countryId;

    public ObjectGroupTranslationsId() {
    }

    public ObjectGroupTranslationsId(String objectGroupId, int countryId) {
       this.objectGroupId = objectGroupId;
       this.countryId = countryId;
    }
   
    public String getObjectGroupId() {
        return this.objectGroupId;
    }
    
    public void setObjectGroupId(String objectGroupId) {
        this.objectGroupId = objectGroupId;
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
		 if ( !(other instanceof ObjectGroupTranslationsId) ) return false;
		 ObjectGroupTranslationsId castOther = ( ObjectGroupTranslationsId ) other; 
         
		 return ( (this.getObjectGroupId()==castOther.getObjectGroupId()) || ( this.getObjectGroupId()!=null && castOther.getObjectGroupId()!=null && this.getObjectGroupId().equals(castOther.getObjectGroupId()) ) )
 && (this.getCountryId()==castOther.getCountryId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getObjectGroupId() == null ? 0 : this.getObjectGroupId().hashCode() );
         result = 37 * result + this.getCountryId();
         return result;
   }   


}


package se.info24.pojo;
// Generated Feb 23, 2011 12:47:44 PM by Hibernate Tools 3.2.1.GA



/**
 * ContentTypeTranslationsId generated by hbm2java
 */
public class ContentTypeTranslationsId  implements java.io.Serializable {


     private String contentTypeId;
     private int countryId;

    public ContentTypeTranslationsId() {
    }

    public ContentTypeTranslationsId(String contentTypeId, int countryId) {
       this.contentTypeId = contentTypeId;
       this.countryId = countryId;
    }
   
    public String getContentTypeId() {
        return this.contentTypeId;
    }
    
    public void setContentTypeId(String contentTypeId) {
        this.contentTypeId = contentTypeId;
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
		 if ( !(other instanceof ContentTypeTranslationsId) ) return false;
		 ContentTypeTranslationsId castOther = ( ContentTypeTranslationsId ) other; 
         
		 return ( (this.getContentTypeId()==castOther.getContentTypeId()) || ( this.getContentTypeId()!=null && castOther.getContentTypeId()!=null && this.getContentTypeId().equals(castOther.getContentTypeId()) ) )
 && (this.getCountryId()==castOther.getCountryId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getContentTypeId() == null ? 0 : this.getContentTypeId().hashCode() );
         result = 37 * result + this.getCountryId();
         return result;
   }   


}



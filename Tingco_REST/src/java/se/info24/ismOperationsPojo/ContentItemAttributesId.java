package se.info24.ismOperationsPojo;
// Generated Aug 25, 2011 3:25:26 PM by Hibernate Tools 3.2.1.GA



/**
 * ContentItemAttributesId generated by hbm2java
 */
public class ContentItemAttributesId  implements java.io.Serializable {


     private String contentItemId;
     private String contentAttributeId;

    public ContentItemAttributesId() {
    }

    public ContentItemAttributesId(String contentItemId, String contentAttributeId) {
       this.contentItemId = contentItemId;
       this.contentAttributeId = contentAttributeId;
    }
   
    public String getContentItemId() {
        return this.contentItemId;
    }
    
    public void setContentItemId(String contentItemId) {
        this.contentItemId = contentItemId;
    }
    public String getContentAttributeId() {
        return this.contentAttributeId;
    }
    
    public void setContentAttributeId(String contentAttributeId) {
        this.contentAttributeId = contentAttributeId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ContentItemAttributesId) ) return false;
		 ContentItemAttributesId castOther = ( ContentItemAttributesId ) other; 
         
		 return ( (this.getContentItemId()==castOther.getContentItemId()) || ( this.getContentItemId()!=null && castOther.getContentItemId()!=null && this.getContentItemId().equals(castOther.getContentItemId()) ) )
 && ( (this.getContentAttributeId()==castOther.getContentAttributeId()) || ( this.getContentAttributeId()!=null && castOther.getContentAttributeId()!=null && this.getContentAttributeId().equals(castOther.getContentAttributeId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getContentItemId() == null ? 0 : this.getContentItemId().hashCode() );
         result = 37 * result + ( getContentAttributeId() == null ? 0 : this.getContentAttributeId().hashCode() );
         return result;
   }   


}



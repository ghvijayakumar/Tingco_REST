package se.info24.pojo;
// Generated Jan 23, 2013 10:58:16 AM by Hibernate Tools 3.2.1.GA



/**
 * ObjectFieldDataId generated by hbm2java
 */
public class ObjectFieldDataId  implements java.io.Serializable {


     private String objectId;
     private String fieldId;

    public ObjectFieldDataId() {
    }

    public ObjectFieldDataId(String objectId, String fieldId) {
       this.objectId = objectId;
       this.fieldId = fieldId;
    }
   
    public String getObjectId() {
        return this.objectId;
    }
    
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
    public String getFieldId() {
        return this.fieldId;
    }
    
    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ObjectFieldDataId) ) return false;
		 ObjectFieldDataId castOther = ( ObjectFieldDataId ) other; 
         
		 return ( (this.getObjectId()==castOther.getObjectId()) || ( this.getObjectId()!=null && castOther.getObjectId()!=null && this.getObjectId().equals(castOther.getObjectId()) ) )
 && ( (this.getFieldId()==castOther.getFieldId()) || ( this.getFieldId()!=null && castOther.getFieldId()!=null && this.getFieldId().equals(castOther.getFieldId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getObjectId() == null ? 0 : this.getObjectId().hashCode() );
         result = 37 * result + ( getFieldId() == null ? 0 : this.getFieldId().hashCode() );
         return result;
   }   


}


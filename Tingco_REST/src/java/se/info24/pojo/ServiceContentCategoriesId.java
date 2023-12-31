package se.info24.pojo;
// Generated Jun 11, 2014 11:28:49 AM by Hibernate Tools 3.2.1.GA



/**
 * ServiceContentCategoriesId generated by hbm2java
 */
public class ServiceContentCategoriesId  implements java.io.Serializable {


     private String serviceId;
     private String contentCategoryId;

    public ServiceContentCategoriesId() {
    }

    public ServiceContentCategoriesId(String serviceId, String contentCategoryId) {
       this.serviceId = serviceId;
       this.contentCategoryId = contentCategoryId;
    }
   
    public String getServiceId() {
        return this.serviceId;
    }
    
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
    public String getContentCategoryId() {
        return this.contentCategoryId;
    }
    
    public void setContentCategoryId(String contentCategoryId) {
        this.contentCategoryId = contentCategoryId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ServiceContentCategoriesId) ) return false;
		 ServiceContentCategoriesId castOther = ( ServiceContentCategoriesId ) other; 
         
		 return ( (this.getServiceId()==castOther.getServiceId()) || ( this.getServiceId()!=null && castOther.getServiceId()!=null && this.getServiceId().equals(castOther.getServiceId()) ) )
 && ( (this.getContentCategoryId()==castOther.getContentCategoryId()) || ( this.getContentCategoryId()!=null && castOther.getContentCategoryId()!=null && this.getContentCategoryId().equals(castOther.getContentCategoryId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getServiceId() == null ? 0 : this.getServiceId().hashCode() );
         result = 37 * result + ( getContentCategoryId() == null ? 0 : this.getContentCategoryId().hashCode() );
         return result;
   }   


}



package se.info24.pojo;
// Generated Aug 13, 2012 2:28:58 PM by Hibernate Tools 3.2.1.GA



/**
 * ApplicationPackageModulesId generated by hbm2java
 */
public class ApplicationPackageModulesId  implements java.io.Serializable {


     private String applicationPackageId;
     private String applicationModuleId;

    public ApplicationPackageModulesId() {
    }

    public ApplicationPackageModulesId(String applicationPackageId, String applicationModuleId) {
       this.applicationPackageId = applicationPackageId;
       this.applicationModuleId = applicationModuleId;
    }
   
    public String getApplicationPackageId() {
        return this.applicationPackageId;
    }
    
    public void setApplicationPackageId(String applicationPackageId) {
        this.applicationPackageId = applicationPackageId;
    }
    public String getApplicationModuleId() {
        return this.applicationModuleId;
    }
    
    public void setApplicationModuleId(String applicationModuleId) {
        this.applicationModuleId = applicationModuleId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ApplicationPackageModulesId) ) return false;
		 ApplicationPackageModulesId castOther = ( ApplicationPackageModulesId ) other; 
         
		 return ( (this.getApplicationPackageId()==castOther.getApplicationPackageId()) || ( this.getApplicationPackageId()!=null && castOther.getApplicationPackageId()!=null && this.getApplicationPackageId().equals(castOther.getApplicationPackageId()) ) )
 && ( (this.getApplicationModuleId()==castOther.getApplicationModuleId()) || ( this.getApplicationModuleId()!=null && castOther.getApplicationModuleId()!=null && this.getApplicationModuleId().equals(castOther.getApplicationModuleId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getApplicationPackageId() == null ? 0 : this.getApplicationPackageId().hashCode() );
         result = 37 * result + ( getApplicationModuleId() == null ? 0 : this.getApplicationModuleId().hashCode() );
         return result;
   }   


}


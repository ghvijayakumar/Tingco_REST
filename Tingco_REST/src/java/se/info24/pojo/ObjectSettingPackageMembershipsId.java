package se.info24.pojo;
// Generated Oct 30, 2012 12:10:18 PM by Hibernate Tools 3.2.1.GA



/**
 * ObjectSettingPackageMembershipsId generated by hbm2java
 */
public class ObjectSettingPackageMembershipsId  implements java.io.Serializable {


     private String objectSettingPackageId;
     private String objectId;

    public ObjectSettingPackageMembershipsId() {
    }

    public ObjectSettingPackageMembershipsId(String objectSettingPackageId, String objectId) {
       this.objectSettingPackageId = objectSettingPackageId;
       this.objectId = objectId;
    }
   
    public String getObjectSettingPackageId() {
        return this.objectSettingPackageId;
    }
    
    public void setObjectSettingPackageId(String objectSettingPackageId) {
        this.objectSettingPackageId = objectSettingPackageId;
    }
    public String getObjectId() {
        return this.objectId;
    }
    
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ObjectSettingPackageMembershipsId) ) return false;
		 ObjectSettingPackageMembershipsId castOther = ( ObjectSettingPackageMembershipsId ) other; 
         
		 return ( (this.getObjectSettingPackageId()==castOther.getObjectSettingPackageId()) || ( this.getObjectSettingPackageId()!=null && castOther.getObjectSettingPackageId()!=null && this.getObjectSettingPackageId().equals(castOther.getObjectSettingPackageId()) ) )
 && ( (this.getObjectId()==castOther.getObjectId()) || ( this.getObjectId()!=null && castOther.getObjectId()!=null && this.getObjectId().equals(castOther.getObjectId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getObjectSettingPackageId() == null ? 0 : this.getObjectSettingPackageId().hashCode() );
         result = 37 * result + ( getObjectId() == null ? 0 : this.getObjectId().hashCode() );
         return result;
   }   


}


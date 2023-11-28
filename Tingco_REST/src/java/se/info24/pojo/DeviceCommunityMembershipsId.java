package se.info24.pojo;
// Generated Dec 6, 2010 12:53:09 PM by Hibernate Tools 3.2.1.GA



/**
 * DeviceCommunityMembershipsId generated by hbm2java
 */
public class DeviceCommunityMembershipsId  implements java.io.Serializable {


     private String deviceId;
     private String communityId;

    public DeviceCommunityMembershipsId() {
    }

    public DeviceCommunityMembershipsId(String deviceId, String communityId) {
       this.deviceId = deviceId;
       this.communityId = communityId;
    }
   
    public String getDeviceId() {
        return this.deviceId;
    }
    
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    public String getCommunityId() {
        return this.communityId;
    }
    
    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DeviceCommunityMembershipsId) ) return false;
		 DeviceCommunityMembershipsId castOther = ( DeviceCommunityMembershipsId ) other; 
         
		 return ( (this.getDeviceId()==castOther.getDeviceId()) || ( this.getDeviceId()!=null && castOther.getDeviceId()!=null && this.getDeviceId().equals(castOther.getDeviceId()) ) )
 && ( (this.getCommunityId()==castOther.getCommunityId()) || ( this.getCommunityId()!=null && castOther.getCommunityId()!=null && this.getCommunityId().equals(castOther.getCommunityId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getDeviceId() == null ? 0 : this.getDeviceId().hashCode() );
         result = 37 * result + ( getCommunityId() == null ? 0 : this.getCommunityId().hashCode() );
         return result;
   }   


}



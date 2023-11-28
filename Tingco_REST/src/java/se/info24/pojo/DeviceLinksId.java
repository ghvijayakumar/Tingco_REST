package se.info24.pojo;
// Generated Jul 24, 2014 10:43:38 AM by Hibernate Tools 3.2.1.GA



/**
 * DeviceLinksId generated by hbm2java
 */
public class DeviceLinksId  implements java.io.Serializable {


     private String deviceId;
     private String itrustDeviceId;

    public DeviceLinksId() {
    }

    public DeviceLinksId(String deviceId, String itrustDeviceId) {
       this.deviceId = deviceId;
       this.itrustDeviceId = itrustDeviceId;
    }
   
    public String getDeviceId() {
        return this.deviceId;
    }
    
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    public String getItrustDeviceId() {
        return this.itrustDeviceId;
    }
    
    public void setItrustDeviceId(String itrustDeviceId) {
        this.itrustDeviceId = itrustDeviceId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DeviceLinksId) ) return false;
		 DeviceLinksId castOther = ( DeviceLinksId ) other; 
         
		 return ( (this.getDeviceId()==castOther.getDeviceId()) || ( this.getDeviceId()!=null && castOther.getDeviceId()!=null && this.getDeviceId().equals(castOther.getDeviceId()) ) )
 && ( (this.getItrustDeviceId()==castOther.getItrustDeviceId()) || ( this.getItrustDeviceId()!=null && castOther.getItrustDeviceId()!=null && this.getItrustDeviceId().equals(castOther.getItrustDeviceId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getDeviceId() == null ? 0 : this.getDeviceId().hashCode() );
         result = 37 * result + ( getItrustDeviceId() == null ? 0 : this.getItrustDeviceId().hashCode() );
         return result;
   }   


}


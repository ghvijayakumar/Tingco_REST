package se.info24.ismOperationsPojo;
// Generated Jun 26, 2014 1:32:26 PM by Hibernate Tools 3.2.1.GA



/**
 * ServiceStatusId generated by hbm2java
 */
public class ServiceStatusId  implements java.io.Serializable {


     private String serviceId;
     private String deviceId;

    public ServiceStatusId() {
    }

    public ServiceStatusId(String serviceId, String deviceId) {
       this.serviceId = serviceId;
       this.deviceId = deviceId;
    }
   
    public String getServiceId() {
        return this.serviceId;
    }
    
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
    public String getDeviceId() {
        return this.deviceId;
    }
    
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ServiceStatusId) ) return false;
		 ServiceStatusId castOther = ( ServiceStatusId ) other; 
         
		 return ( (this.getServiceId()==castOther.getServiceId()) || ( this.getServiceId()!=null && castOther.getServiceId()!=null && this.getServiceId().equals(castOther.getServiceId()) ) )
 && ( (this.getDeviceId()==castOther.getDeviceId()) || ( this.getDeviceId()!=null && castOther.getDeviceId()!=null && this.getDeviceId().equals(castOther.getDeviceId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getServiceId() == null ? 0 : this.getServiceId().hashCode() );
         result = 37 * result + ( getDeviceId() == null ? 0 : this.getDeviceId().hashCode() );
         return result;
   }   


}



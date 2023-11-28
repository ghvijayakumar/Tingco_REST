package se.info24.pojo;
// Generated Oct 5, 2012 12:34:20 PM by Hibernate Tools 3.2.1.GA



/**
 * DataItemTranslationsPerDeviceId generated by hbm2java
 */
public class DataItemTranslationsPerDeviceId  implements java.io.Serializable {


     private String deviceId;
     private String deviceDataItemId;
     private int countryId;

    public DataItemTranslationsPerDeviceId() {
    }

    public DataItemTranslationsPerDeviceId(String deviceId, String deviceDataItemId, int countryId) {
       this.deviceId = deviceId;
       this.deviceDataItemId = deviceDataItemId;
       this.countryId = countryId;
    }
   
    public String getDeviceId() {
        return this.deviceId;
    }
    
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    public String getDeviceDataItemId() {
        return this.deviceDataItemId;
    }
    
    public void setDeviceDataItemId(String deviceDataItemId) {
        this.deviceDataItemId = deviceDataItemId;
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
		 if ( !(other instanceof DataItemTranslationsPerDeviceId) ) return false;
		 DataItemTranslationsPerDeviceId castOther = ( DataItemTranslationsPerDeviceId ) other; 
         
		 return ( (this.getDeviceId()==castOther.getDeviceId()) || ( this.getDeviceId()!=null && castOther.getDeviceId()!=null && this.getDeviceId().equals(castOther.getDeviceId()) ) )
 && ( (this.getDeviceDataItemId()==castOther.getDeviceDataItemId()) || ( this.getDeviceDataItemId()!=null && castOther.getDeviceDataItemId()!=null && this.getDeviceDataItemId().equals(castOther.getDeviceDataItemId()) ) )
 && (this.getCountryId()==castOther.getCountryId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getDeviceId() == null ? 0 : this.getDeviceId().hashCode() );
         result = 37 * result + ( getDeviceDataItemId() == null ? 0 : this.getDeviceDataItemId().hashCode() );
         result = 37 * result + this.getCountryId();
         return result;
   }   


}



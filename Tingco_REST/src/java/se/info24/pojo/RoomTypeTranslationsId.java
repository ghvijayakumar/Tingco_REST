package se.info24.pojo;
// Generated Aug 21, 2014 12:56:43 PM by Hibernate Tools 3.2.1.GA



/**
 * RoomTypeTranslationsId generated by hbm2java
 */
public class RoomTypeTranslationsId  implements java.io.Serializable {


     private String roomTypeId;
     private int countryId;

    public RoomTypeTranslationsId() {
    }

    public RoomTypeTranslationsId(String roomTypeId, int countryId) {
       this.roomTypeId = roomTypeId;
       this.countryId = countryId;
    }
   
    public String getRoomTypeId() {
        return this.roomTypeId;
    }
    
    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
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
		 if ( !(other instanceof RoomTypeTranslationsId) ) return false;
		 RoomTypeTranslationsId castOther = ( RoomTypeTranslationsId ) other; 
         
		 return ( (this.getRoomTypeId()==castOther.getRoomTypeId()) || ( this.getRoomTypeId()!=null && castOther.getRoomTypeId()!=null && this.getRoomTypeId().equals(castOther.getRoomTypeId()) ) )
 && (this.getCountryId()==castOther.getCountryId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getRoomTypeId() == null ? 0 : this.getRoomTypeId().hashCode() );
         result = 37 * result + this.getCountryId();
         return result;
   }   


}


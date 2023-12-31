package se.info24.pojo;
// Generated Aug 21, 2014 12:56:43 PM by Hibernate Tools 3.2.1.GA



/**
 * ZoneRoomsId generated by hbm2java
 */
public class ZoneRoomsId  implements java.io.Serializable {


     private String zoneId;
     private String roomId;

    public ZoneRoomsId() {
    }

    public ZoneRoomsId(String zoneId, String roomId) {
       this.zoneId = zoneId;
       this.roomId = roomId;
    }
   
    public String getZoneId() {
        return this.zoneId;
    }
    
    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }
    public String getRoomId() {
        return this.roomId;
    }
    
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ZoneRoomsId) ) return false;
		 ZoneRoomsId castOther = ( ZoneRoomsId ) other; 
         
		 return ( (this.getZoneId()==castOther.getZoneId()) || ( this.getZoneId()!=null && castOther.getZoneId()!=null && this.getZoneId().equals(castOther.getZoneId()) ) )
 && ( (this.getRoomId()==castOther.getRoomId()) || ( this.getRoomId()!=null && castOther.getRoomId()!=null && this.getRoomId().equals(castOther.getRoomId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getZoneId() == null ? 0 : this.getZoneId().hashCode() );
         result = 37 * result + ( getRoomId() == null ? 0 : this.getRoomId().hashCode() );
         return result;
   }   


}



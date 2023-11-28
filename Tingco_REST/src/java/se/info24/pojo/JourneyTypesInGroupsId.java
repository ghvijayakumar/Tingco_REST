package se.info24.pojo;
// Generated Feb 8, 2012 2:19:05 PM by Hibernate Tools 3.2.1.GA



/**
 * JourneyTypesInGroupsId generated by hbm2java
 */
public class JourneyTypesInGroupsId  implements java.io.Serializable {


     private String journeyTypeId;
     private String groupId;

    public JourneyTypesInGroupsId() {
    }

    public JourneyTypesInGroupsId(String journeyTypeId, String groupId) {
       this.journeyTypeId = journeyTypeId;
       this.groupId = groupId;
    }
   
    public String getJourneyTypeId() {
        return this.journeyTypeId;
    }
    
    public void setJourneyTypeId(String journeyTypeId) {
        this.journeyTypeId = journeyTypeId;
    }
    public String getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JourneyTypesInGroupsId) ) return false;
		 JourneyTypesInGroupsId castOther = ( JourneyTypesInGroupsId ) other; 
         
		 return ( (this.getJourneyTypeId()==castOther.getJourneyTypeId()) || ( this.getJourneyTypeId()!=null && castOther.getJourneyTypeId()!=null && this.getJourneyTypeId().equals(castOther.getJourneyTypeId()) ) )
 && ( (this.getGroupId()==castOther.getGroupId()) || ( this.getGroupId()!=null && castOther.getGroupId()!=null && this.getGroupId().equals(castOther.getGroupId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getJourneyTypeId() == null ? 0 : this.getJourneyTypeId().hashCode() );
         result = 37 * result + ( getGroupId() == null ? 0 : this.getGroupId().hashCode() );
         return result;
   }   


}



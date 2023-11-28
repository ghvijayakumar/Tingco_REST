package se.info24.pojo;
// Generated Dec 2, 2010 3:59:27 PM by Hibernate Tools 3.2.1.GA



/**
 * GroupTranslationsId generated by hbm2java
 */
public class GroupTranslationsId  implements java.io.Serializable {


     private String groupId;
     private int countryId;

    public GroupTranslationsId() {
    }

    public GroupTranslationsId(String groupId, int countryId) {
       this.groupId = groupId;
       this.countryId = countryId;
    }
   
    public String getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(String groupId) {
        this.groupId = groupId;
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
		 if ( !(other instanceof GroupTranslationsId) ) return false;
		 GroupTranslationsId castOther = ( GroupTranslationsId ) other; 
         
		 return ( (this.getGroupId()==castOther.getGroupId()) || ( this.getGroupId()!=null && castOther.getGroupId()!=null && this.getGroupId().equals(castOther.getGroupId()) ) )
 && (this.getCountryId()==castOther.getCountryId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getGroupId() == null ? 0 : this.getGroupId().hashCode() );
         result = 37 * result + this.getCountryId();
         return result;
   }   


}



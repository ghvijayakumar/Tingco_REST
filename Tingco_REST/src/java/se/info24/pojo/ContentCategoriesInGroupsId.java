package se.info24.pojo;
// Generated Jun 11, 2014 11:24:04 AM by Hibernate Tools 3.2.1.GA



/**
 * ContentCategoriesInGroupsId generated by hbm2java
 */
public class ContentCategoriesInGroupsId  implements java.io.Serializable {


     private String contentCategoryId;
     private String groupId;

    public ContentCategoriesInGroupsId() {
    }

    public ContentCategoriesInGroupsId(String contentCategoryId, String groupId) {
       this.contentCategoryId = contentCategoryId;
       this.groupId = groupId;
    }
   
    public String getContentCategoryId() {
        return this.contentCategoryId;
    }
    
    public void setContentCategoryId(String contentCategoryId) {
        this.contentCategoryId = contentCategoryId;
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
		 if ( !(other instanceof ContentCategoriesInGroupsId) ) return false;
		 ContentCategoriesInGroupsId castOther = ( ContentCategoriesInGroupsId ) other; 
         
		 return ( (this.getContentCategoryId()==castOther.getContentCategoryId()) || ( this.getContentCategoryId()!=null && castOther.getContentCategoryId()!=null && this.getContentCategoryId().equals(castOther.getContentCategoryId()) ) )
 && ( (this.getGroupId()==castOther.getGroupId()) || ( this.getGroupId()!=null && castOther.getGroupId()!=null && this.getGroupId().equals(castOther.getGroupId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getContentCategoryId() == null ? 0 : this.getContentCategoryId().hashCode() );
         result = 37 * result + ( getGroupId() == null ? 0 : this.getGroupId().hashCode() );
         return result;
   }   


}



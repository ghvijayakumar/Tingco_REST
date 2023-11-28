package se.info24.pojo;
// Generated Oct 25, 2013 7:57:56 PM by Hibernate Tools 3.2.1.GA



/**
 * ProductsInGroupsId generated by hbm2java
 */
public class ProductsInGroupsId  implements java.io.Serializable {


     private String productId;
     private String groupId;

    public ProductsInGroupsId() {
    }

    public ProductsInGroupsId(String productId, String groupId) {
       this.productId = productId;
       this.groupId = groupId;
    }
   
    public String getProductId() {
        return this.productId;
    }
    
    public void setProductId(String productId) {
        this.productId = productId;
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
		 if ( !(other instanceof ProductsInGroupsId) ) return false;
		 ProductsInGroupsId castOther = ( ProductsInGroupsId ) other; 
         
		 return ( (this.getProductId()==castOther.getProductId()) || ( this.getProductId()!=null && castOther.getProductId()!=null && this.getProductId().equals(castOther.getProductId()) ) )
 && ( (this.getGroupId()==castOther.getGroupId()) || ( this.getGroupId()!=null && castOther.getGroupId()!=null && this.getGroupId().equals(castOther.getGroupId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getProductId() == null ? 0 : this.getProductId().hashCode() );
         result = 37 * result + ( getGroupId() == null ? 0 : this.getGroupId().hashCode() );
         return result;
   }   


}


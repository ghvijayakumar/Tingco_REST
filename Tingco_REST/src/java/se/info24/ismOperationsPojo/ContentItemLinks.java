package se.info24.ismOperationsPojo;
// Generated Sep 26, 2013 12:17:17 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * ContentItemLinks generated by hbm2java
 */
public class ContentItemLinks  implements java.io.Serializable {


     private ContentItemLinksId id;
     private Date createdDate;
     private Date updatedDate;

    public ContentItemLinks() {
    }

	
    public ContentItemLinks(ContentItemLinksId id) {
        this.id = id;
    }
    public ContentItemLinks(ContentItemLinksId id, Date createdDate, Date updatedDate) {
       this.id = id;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public ContentItemLinksId getId() {
        return this.id;
    }
    
    public void setId(ContentItemLinksId id) {
        this.id = id;
    }
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public Date getUpdatedDate() {
        return this.updatedDate;
    }
    
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }




}



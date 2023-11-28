/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.info24.pojo;

import java.io.Serializable;

/**
 *
 * @author Ravikant
 */
public class ObjectCostCentersId implements Serializable{
    private String objectId;
    private String groupId;

    public ObjectCostCentersId(){}
    public ObjectCostCentersId(String objectId, String groupId) {
        this.objectId = objectId;
        this.groupId = groupId;
    }


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    
}

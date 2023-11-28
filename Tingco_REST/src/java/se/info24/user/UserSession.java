/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.info24.user;

import java.util.Date;

/**
 *
 * @author Sridhar Dasari
 */
public class UserSession {

    private String sessionid;
    private String userID;
    private String appID;
    private String authToken;
    private Date active2Date;
    private Date createdDate;
    private Date updatedDate;

    public Date getActive2Date() {
        return active2Date;
    }

    public void setActive2Date(Date active2Date) {
        this.active2Date = active2Date;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}

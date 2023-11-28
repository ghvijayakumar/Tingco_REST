/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.user;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.pojo.Country;
import se.info24.pojo.Groups;
import se.info24.pojo.UserGroupMemberships2;
import se.info24.pojo.UserGroupMemberships2Id;
import se.info24.pojo.UserRoleMemberships2;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.UserTypes2;
import se.info24.pojo.Users2;
import se.info24.pojo.Users2Id;
import se.info24.usersjaxb.CountryID;
import se.info24.usersjaxb.GroupID;
import se.info24.usersjaxb.MsgStatus;
import se.info24.usersjaxb.ObjectFactory;
import se.info24.usersjaxb.TingcoUsers;
import se.info24.usersjaxb.User;
import se.info24.usersjaxb.UserGroupMemberships;
import se.info24.usersjaxb.UserRoles;
import se.info24.usersjaxb.Users;
import se.info24.usersjaxb.UserSession;
import se.info24.usersjaxb.UserSetting;
import se.info24.usersjaxb.UserSettings;
import se.info24.usersjaxb.UserTypeID;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Message;
import javax.mail.Multipart;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import se.info24.application.ApplicationDAO;
import se.info24.group.GroupDAO;
import se.info24.pojo.ApplicationModuleTranslations;
import se.info24.pojo.Applications;
import se.info24.pojo.ApplicationSettings;
import se.info24.pojo.ForgetPasswords;
import se.info24.pojo.ForgetPasswordsId;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupTrusts;
import se.info24.pojo.PermissionTranslations;
import se.info24.pojo.TimeZones;
import se.info24.pojo.UserApplicationSettings;
import se.info24.pojo.UserApplicationSettingsToActivate;
import se.info24.pojo.UserInvitations;
import se.info24.pojo.UserInvitationsId;
import se.info24.pojo.UserLog;
import se.info24.pojo.UserLoginResetRequests;
import se.info24.pojo.UserPasswords;
import se.info24.pojo.UserRoleMemberships2Id;
import se.info24.pojo.UserRoleMembershipsToActivate;
import se.info24.pojo.UserRoleObjectPermissions2;
import se.info24.pojo.UserRoles2;
import se.info24.pojo.UserRolesInGroups2;
import se.info24.pojo.UserSettings2;
import se.info24.pojo.UserTimeZones2;
import se.info24.pojo.UserTimeZonesToActivate;
import se.info24.pojo.UsersToActivate;
import se.info24.pojo.UsersToActivateId;
import se.info24.usersjaxb.ApplicationModule;
import se.info24.usersjaxb.ApplicationModules;
import se.info24.usersjaxb.LastUpdatedByUserID;
import se.info24.usersjaxb.Permission;
import se.info24.usersjaxb.Permissions;
import se.info24.usersjaxb.UserRole;
import se.info24.usersjaxb.UserTimeZone;
import se.info24.usersjaxb.UserTimeZones;
import se.info24.util.RSAPassword;
import se.info24.util.TCMUtil;
import se.info24.util.TingcoConstants;
import se.info24.utiljaxb.TingcoUtils;
import se.info24.utiljaxb.UserInvitation;

/**
 *
 * @author Sridhar Dasari
 */
public class UserManager {

    private Users2 user_;
//    private UserDAO userDAO = new UserDAO();

    public List<UserLoginResetRequests> checkValidPasswordResetRequest(String loginResetRequestId, String loginResetRequestCode, Session session) {
        List<UserLoginResetRequests> list = session.getNamedQuery("getuserpasswordresetReq").setString("loginResetRequestId", loginResetRequestId).setString("loginResetRequestCode", loginResetRequestCode).list();
        return list;
    }

    public boolean deleteLoginResetRequests(String loginResetRequestId, String loginResetRequestCode, Session session) {
        boolean result = false;
        try {
            List<UserLoginResetRequests> userLoginResetRequest = session.getNamedQuery("getuserpasswordresetReq").setString("loginResetRequestId", loginResetRequestId).setString("loginResetRequestCode", loginResetRequestCode).list();
            if (!userLoginResetRequest.isEmpty()) {
                UserLoginResetRequests ulrs = userLoginResetRequest.get(0);
                Transaction tx = session.beginTransaction();
                session.delete(ulrs);
                tx.commit();
                result = true;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            TCMUtil.exceptionLog(UserManager.class.getName(), ex);
            result = false;
        }
        return result;
    }

    protected TingcoUsers getUser(String userID, Session session, UserSessions2 us2) throws DatatypeConfigurationException {
        Users2 user = (Users2) session.getNamedQuery("getUsers2ById").setString("userID", userID).list().get(0);
        TingcoUsers tcm_user = buildUserTemplate();
        Users us = new Users();
        UserSession sess = new UserSession();
        sess.setUserID(userID);
        sess.setApplicationID(us2.getApplicationId());
        sess.setAuthenticationToken(us2.getAuthenticationToken());
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(us2.getActiveToDate());
        sess.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
        us.setUserSession(sess);
        User u = new User();
        u.setSeqNo(1);
        u.setUserID(userID);
        u.setDomainID(user.getId().getDomainId());
        GroupID groupID = new GroupID();
        try {
            GroupTranslations trans = (GroupTranslations) session.getNamedQuery("getGroupTranslationsById").setString("groupID", user.getGroupId()).setInteger("countryID", user.getCountryId()).list().get(0);

            groupID.setGroupName(trans.getGroupName());
            groupID.setValue(user.getGroupId());
            u.setGroupID(groupID);
        } catch (Exception e) {
        }
        u.setLoginName(user.getId().getLoginName());
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setUserEmail(user.getUserEmail());
        u.setUserMobilePhone(user.getUserMobilePhone());
        u.setNickName(user.getNickName());
        CountryID cid = new CountryID();
        Country c = (Country) session.getNamedQuery("getCountryById").setInteger("countryID", user.getCountryId()).list().get(0);
        cid.setCountryName(c.getCountryName());
        cid.setValue(user.getCountryId());
        u.setCountryID(cid);
        se.info24.usersjaxb.Country country = new se.info24.usersjaxb.Country();
        country.setLanguage(c.getLanguage());
        u.setCountry(country);
        UserTypeID typeID = new UserTypeID();
        typeID.setUserTypeName("");
        typeID.setValue("");
        u.setUserTypeID(typeID);

        gc = new GregorianCalendar();
        gc.setTime(user.getActiveFromDate());
        u.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));

        gc = new GregorianCalendar();
        gc.setTime(user.getActiveToDate());
        u.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
        u.setIsLockedOut(user.getIsLockedOut());
        u.setChangePwdRequired(user.getChangePwdRequired());
        if (user.getUserSmallImageUrl() != null) {
            u.setUserSmallImageURL(user.getUserSmallImageUrl());
        }
        gc = new GregorianCalendar();
        if (user.getLastLoginDate() != null) {
            gc.setTime(user.getLastLoginDate());
        }
        u.setLastLoginDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));

        gc = new GregorianCalendar();
        if (user.getLockedOutDate() != null) {
            gc.setTime(user.getLockedOutDate());
            u.setLockedOutDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
        }
        UserSettings settings = new UserSettings();
        int setSeq = 1;
        LastUpdatedByUserID lastUserId;
        UserDAO userDAO = new UserDAO();
        for (UserSettings2 settings2 : userDAO.getUserSettings2ByUserID(userID, session)) {
            UserSetting set = new UserSetting();
            set.setSeqNo(setSeq++);
            set.setUserSettingID(settings2.getUserSettingsId());
            set.setUserSettingName(settings2.getSettingsName());
            set.setUserSettingValue(settings2.getSettingsValue());
            if (settings2.getLastUpdatedByUserId() != null) {
                lastUserId = new LastUpdatedByUserID();
                lastUserId.setUserFullName(settings2.getLastUpdatedByUserId()); // Change to User Name.
                lastUserId.setValue(settings2.getLastUpdatedByUserId());
            }
            settings.getUserSetting().add(set);
        }
        u.setUserSettings(settings);
        UserTimeZones zones = new UserTimeZones();
        UserTimeZone timeZone = new UserTimeZone();
        List<UserTimeZones2> utz = session.getNamedQuery("getTimeZoneByUserID").setString("userID", userID).list();
        if (!utz.isEmpty()) {
            TimeZones trans = (TimeZones) session.getNamedQuery("getTimeZoneByTimeZoneID").setString("timeZoneID", utz.get(0).getTimeZoneId()).list().get(0);

            timeZone.setTimeZoneID(utz.get(0).getTimeZoneId());
            timeZone.setUseDayLightSaving(utz.get(0).getUseDaylightSaving());
            if (trans != null) {
                timeZone.setTimeZoneName(trans.getTimeZoneName());
            }
        }
        zones.getUserTimeZone().add(timeZone);
        u.setUserTimeZones(zones);

        gc = new GregorianCalendar();
        gc.setTime(user.getCreatedDate());
        u.setCreatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));

        gc = new GregorianCalendar();
        gc.setTime(user.getUpdatedDate());
        u.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));

        us.getUser().add(u);
        tcm_user.setUsers(us);
        return tcm_user;
    }

    public UserSessions2 getUserSession(Session session, HttpServletRequest request) {
        UserSessions2 us2 = (UserSessions2) session.getNamedQuery("getUserSessionByToken").setString("authenticationToken", request.getHeader("AuthenticationToken")).list().get(0);
        return us2;
    }

    protected void updateUser(String userID, String firstName, String lastName, String email, String phone, String groupID, Session session) {

        try {
            Transaction tx = session.beginTransaction();
            Users2 u = (Users2) session.getNamedQuery("getUsers2ById").setString("userID", userID).list().get(0);

            Query q = session.createSQLQuery("update users2 set loginname = '" + email + "',useremail = '" + email + "',firstName ='" + firstName + "',lastName= '" + lastName + "'  where userid ='" + userID + "'");
            q.executeUpdate();
            session.saveOrUpdate(u);
            tx.commit();
        } catch (Exception e) {
            TCMUtil.exceptionLog(UserManager.class.getName(), e);
        }

    }

    protected UserRoles getUserRoles(String userID, Session session) {

        List<UserRoleMemberships2> urmlist = getUserRoleMemberships2(userID, session);
        UserRoles user_roles = new UserRoles();
        int seqNo = 1;
        for (UserRoleMemberships2 urm : urmlist) {
            UserRole ur = new UserRole();
            ur.setSeqNo(seqNo++);
            ur.setUserRoleID(urm.getUserRoles2().getUserRoleId());
            ur.setUserRoleName(urm.getUserRoles2().getUserRoleName());

            user_roles.getUserRole().add(ur);
        }
        return user_roles;

    }

    protected UserRoles getUserRolesTCM(String userID, Session session) {

        List<UserRoleMemberships2> urmlist = getUserRoleMemberships2(userID, session);
        UserRoles user_roles = new UserRoles();
        int seqNo = 1;
//        StringBuffer roleName = new StringBuffer();
        for (UserRoleMemberships2 urm : urmlist) {
            UserRole ur = new UserRole();
            ur.setSeqNo(seqNo++);
            ur.setUserRoleID(urm.getUserRoles2().getUserRoleId());
            ur.setUserRoleName(urm.getUserRoles2().getUserRoleName());
            user_roles.getUserRole().add(ur);
        }
        return user_roles;

    }

    public List<UserRoleMemberships2> getUserRoleMemberships2(String userID, Session session) {
        return session.getNamedQuery("getUserRoleMemberships2ByUserId").setString("userID", userID).list();
    }

    protected boolean deleteUser(String userID, Session session) {
        Transaction tx = session.beginTransaction();
        Users2 del = (Users2) session.getNamedQuery("getUsers2ById").setString("userID", userID).list().get(0);
        session.delete(del);
        tx.commit();
        return true;
    }

    public TingcoUsers buildUserTemplate() {
        try {
            ObjectFactory of = new ObjectFactory();
            TingcoUsers tcm_user = of.createTingcoUsers();
            tcm_user.setCreateRef("Info24");
            tcm_user.setMsgVer(new BigDecimal(1.0));
            tcm_user.setRegionalUnits("Metric");
            tcm_user.setTimeZone("UTC");
            tcm_user.setMsgID(UUID.randomUUID().toString());
            tcm_user.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));

            MsgStatus status = new MsgStatus();
            status.setResponseCode(0);
            status.setResponseText("OK");
            tcm_user.setMsgStatus(status);
            return tcm_user;
        } catch (Exception e) {
            TCMUtil.exceptionLog(UserManager.class.getName(), e);
            return null;
        }
    }

    protected void addUsertoGroup(String userID, String groupID, Session session) {
        Transaction tx = session.beginTransaction();
        GregorianCalendar gc = new GregorianCalendar();
        Groups group = new Groups();
        group.setGroupId(groupID);
        group.setUserId(userID);
        group.setCreatedDate(gc.getTime());
        group.setUpdatedDate(gc.getTime());
        session.saveOrUpdate(group);
        tx.commit();
    }

    protected String addNewUser(String loginName, String pwd, String firstName, String lastName, String email, String groupID, String roleID, String countryID, String phone, String appID, Session session) {

        Transaction tx = session.beginTransaction();
        UsersToActivate uta = new UsersToActivate();
        UsersToActivateId id = new UsersToActivateId(loginName, appID);
        uta.setId(id);
        String userID = UUID.randomUUID().toString();
        uta.setUserId(userID);
        uta.setGroupId(groupID);
        uta.setPassword(RSAPassword.encryptedPwd(pwd));
        uta.setFirstName(firstName);
        uta.setLastName(lastName);
        uta.setUserEmail(email);
        if (phone != null) {
            uta.setUserMobilePhone(phone);
        }

        uta.setCountryId(Integer.valueOf(countryID));
        //UserTypes2 type = new UserTypes2("4a24b403-67ee-4236-acf2-58ee94d3a4a3");//TODO specify based on whether he is normal or admin user
        uta.setUserTypes2(new UserTypes2("4a24b403-67ee-4236-acf2-58ee94d3a4a3"));
        uta.setIsLockedOut(0);
        uta.setChangePwdRequired(0);
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        uta.setActiveFromDate(gc.getTime());
        uta.setLastLoginDate(gc.getTime());
        uta.setLockedOutDate(gc.getTime());
        uta.setLastPasswordChangedDate(gc.getTime());
        uta.setCreatedDate(gc.getTime());
        uta.setUpdatedDate(gc.getTime());
        gc = new GregorianCalendar();
        gc.add(Calendar.YEAR, 5);//TODO how long the user should be active...as of now saving for 5 years
        uta.setActiveToDate(gc.getTime());
        uta.setUserSmallImageUrl("");
        uta.setFailedPasswordAttemptCount(0);

        // Saving the WelcomeUponLogin = 1 for all New Users in UserSettings2 Table.
        UserSettings2 userSettings2 = new UserSettings2();
        userSettings2.setUserSettingsId(UUID.randomUUID().toString());
        userSettings2.setSettingsName("WelcomeUponLogin");
        userSettings2.setSettingsValue("1");
        userSettings2.setUserId(userID);
        gc = new GregorianCalendar();
        userSettings2.setLastUpdatedByUserId(userID);
        userSettings2.setCreatedDate(gc.getTime());
        userSettings2.setUpdatedDate(gc.getTime());
        session.saveOrUpdate(userSettings2);

        session.saveOrUpdate(uta);
        tx.commit();
        return userID;
    }

    protected TingcoUsers getUserGroupMemberShips(String userID, String maxItems, Session session) throws DatatypeConfigurationException {
        GroupDAO groupDAO = new GroupDAO();
        //Users2 user = (Users2) session.createQuery("from Users2 where UserID = '" + userID + "'").list().get(0);
        Users2 user = (Users2) session.getNamedQuery("getUsers2ById").setString("userID", userID).list().get(0);
        List<UserGroupMemberships2> memShips = (List<UserGroupMemberships2>) session.getNamedQuery("getUserGroupMemberships2ById").setString("userID", userID);
        TingcoUsers groups = buildUserTemplate();
        UserGroupMemberships ugm = new UserGroupMemberships();
        for (UserGroupMemberships2 ugm2 : memShips) {
            GroupID gid = new GroupID();
            // Groups g = (Groups) session.createQuery("from Groups  where GroupID = '" + ugm2.getGroups().getGroupId() + "'").list().get(0);
            // GroupTranslations gt = (GroupTranslations) session.createQuery("from GroupTranslations  where GroupID = '" + g.getGroupId() + "' and CountryID = '" + user.getCountryId()).list().get(0);
            Groups g = groupDAO.getGroupByGroupId(ugm2.getGroups().getGroupId(), session);
            GroupTranslations gt = (GroupTranslations) session.getNamedQuery("getGroupTranslationsById").setString("groupID", g.getGroupId()).setInteger("countryID", user.getCountryId()).list().get(0);
            gid.setGroupName(gt.getGroupName());
            gid.setValue(gt.getGroups().getGroupId());
            ugm.getGroupID().add(gid);
        }
        Users users = new Users();
        User u = new User();
        u.setUserGroupMemberships(ugm);
        users.getUser().add(u);
        groups.setUsers(users);
        return groups;
    }

    public boolean sendMail(String to, String subject, String body, String userID, String action, Session session, javax.mail.Session mailSession) {
        try {
            Transaction tx = session.beginTransaction();
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject(subject);

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(body, "text/html");

            Multipart mp = new MimeMultipart();
            mp.addBodyPart(textPart);

            message.setContent(mp);
            message.setFrom(new InternetAddress("Info24"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            Transport transport = mailSession.getTransport();
            transport.connect(TingcoConstants.getEmailIp(), TingcoConstants.getEmailFrom(), TingcoConstants.getEmailPwd());

            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            transport.close();

            if (!action.equals("")) {
                action = action.substring(action.lastIndexOf('/') + 1);
                if (action.equalsIgnoreCase("Passwords")) {
                    ForgetPasswords fp = (ForgetPasswords) session.getNamedQuery("getFPByUserID").setString("userID", userID).list().get(0);
                    fp.setIsMailSent(Boolean.TRUE);
                    session.saveOrUpdate(fp);
                } else if (action.equalsIgnoreCase("Invitations")) {
                    UserInvitations ui = (UserInvitations) session.getNamedQuery("getUserInviteByInvitedTo").setString("invitedTo", userID).list().get(0);
                    ui.setIsMailSent(Boolean.TRUE);
                    session.saveOrUpdate(ui);
                }
                tx.commit();
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(UserManager.class.getName(), e);
            return false;
        }
        return true;
    }

    public boolean sendMail(String to, String subject, String body, javax.mail.Session mailSession) {
        try {
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject(subject);

            MimeBodyPart textPart = new MimeBodyPart();
//            textPart.setContent(body, "text/html");
            textPart.setText(body);
            Multipart mp = new MimeMultipart();
            mp.addBodyPart(textPart);

            message.setContent(mp);
            message.setFrom(new InternetAddress("Info24"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            Transport transport = mailSession.getTransport();
            transport.connect(TingcoConstants.getEmailIp(), TingcoConstants.getEmailFrom(), TingcoConstants.getEmailPwd());

            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            transport.close();
        } catch (Exception e) {
            TCMUtil.exceptionLog(UserManager.class.getName(), e);
            return false;
        }
        return true;
    }

    protected void deleteUserFromGroup(String userID, String groupID, Session session) {
        Transaction tx = session.beginTransaction();
        UserGroupMemberships2 ugm = new UserGroupMemberships2();
        UserGroupMemberships2Id id = new UserGroupMemberships2Id(userID, groupID);
        ugm.setId(id);
        session.delete(ugm);
        tx.commit();
    }

    public boolean isValidApplication(String appID, String domainID, Session session) {
        try {
            Object app = session.getNamedQuery("getApplicationByAppIDandDomainID").setString("appID", appID).setString("domainID", domainID).uniqueResult();
            if (app != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(UserManager.class.getName(), e);
            return false;
        }
    }

    protected boolean validUser(String loginName, String domainID, Date now, Session session) {
        try {
            Object object = session.getNamedQuery("getUserByLoginNameandDomainId").setString("loginName", loginName).setString("domainID", domainID).uniqueResult();
            setUser((Users2) object);
            if (object != null) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(UserManager.class.getName(), e);
            return false;
        }
    }

    
    private void setUser(Users2 user) {
        this.user_ = user;
    }

    public Users2 getUser() {
        return user_;
    }

    protected void saveUserSession(UserSessions2 sess, Session session) {
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(sess);
        tx.commit();
    }

    protected void lockUser(Users2 u, GregorianCalendar gc, Session session) {
        Transaction tx = session.beginTransaction();
        u.setIsLockedOut(1);
        u.setLockedOutDate(gc.getTime());
        session.saveOrUpdate(u);
        tx.commit();
    }

    protected void updateUser(Users2 u, Session session) {
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(u);
        tx.commit();
    }

    protected TingcoUsers getLoggedinUser(int status, String loginName, String appID, String domainID, String token, String remoteHost, String userId, Session session) throws DatatypeConfigurationException {

        TingcoUsers tcm_user = buildUserTemplate();
        try {
            //Users2 user = (Users2) session.createQuery("from Users2 where LoginName = '" + loginName + "' and DomainID = '" + domainID + "'").list().get(0);
            Object obj = session.getNamedQuery("getUserByLoginNameandDomainId").setString("loginName", loginName).setString("domainID", domainID).uniqueResult();
            if (obj == null) {
                tcm_user.getMsgStatus().setResponseCode(-1);
                tcm_user.getMsgStatus().setResponseText("Not a valid user");
//                TCMUtil.saveLog(session, remoteHost, userId, "Login", userId, "Users2;UsersSession2", "Not a valid user");
                return tcm_user;
            }
            if (status == -2) {
                tcm_user.getMsgStatus().setResponseCode(status);
                tcm_user.getMsgStatus().setResponseText("Incorrect Password / LoginName ");
                String dummyUserID = UUID.randomUUID().toString();
                TCMUtil.saveLog(session, remoteHost, dummyUserID, "Login", dummyUserID, "Users2;UsersSession2", "Incorrect Password / LoginName ");
            } else if (status == -4) {
                tcm_user.getMsgStatus().setResponseCode(status);
                tcm_user.getMsgStatus().setResponseText("The User is Locked");
                String dummyUserID = UUID.randomUUID().toString();
                TCMUtil.saveLog(session, remoteHost, dummyUserID, "Login", dummyUserID, "Users2;UsersSession2", "The User is Locked");
            } else if (status == 0) {
                tcm_user.getMsgStatus().setResponseCode(status);
                tcm_user.getMsgStatus().setResponseText("Login Accepted");
                TCMUtil.saveLog(session, remoteHost, userId, "Login", userId, "Users2;UsersSession2", "Success");
            } else if (status == -1) {
                tcm_user.getMsgStatus().setResponseCode(status);
                tcm_user.getMsgStatus().setResponseText("Please check the LoginName/DomainID");
                String dummyUserID = UUID.randomUUID().toString();
                TCMUtil.saveLog(session, remoteHost, dummyUserID, "Login", dummyUserID, "Users2;UsersSession2", "Incorrect LoginName/DomainID");
            }
            Users us = new Users();
            Users2 user = (Users2) obj;
            if (!token.equalsIgnoreCase("")) {
                UserSession user_sess = new UserSession();
                user_sess.setUserID(user.getUserId());
                user_sess.setApplicationID(appID.toUpperCase());
                user_sess.setAuthenticationToken(token);
                us.setUserSession(user_sess);
            }
            User u = new User();
            u.setSeqNo(1);
            u.setUserID(user.getUserId());
            u.setDomainID(user.getId().getDomainId());

            GroupID groupID = new GroupID();
            //List<GroupTranslations> gtList = session.createQuery("from GroupTranslations as gt where gt.id.groupId = '" + user.getGroupId() + "' and gt.id.countryId = '" + user.getCountryId() + "'").list();
            List<GroupTranslations> gtList = session.getNamedQuery("getGroupTranslationsById").setString("groupID", user.getGroupId()).setInteger("countryID", user.getCountryId()).list();
            if (!gtList.isEmpty()) {
                groupID.setGroupName(gtList.get(0).getGroupName());
            } else {
                groupID.setGroupName(user.getGroupId());
            }
            groupID.setValue(user.getGroupId());
            u.setGroupID(groupID);
            u.setLoginName(user.getId().getLoginName());
            u.setFirstName(user.getFirstName());
            u.setLastName(user.getLastName());
            u.setUserEmail(user.getUserEmail());
            u.setUserMobilePhone(user.getUserMobilePhone());

            CountryID cid = new CountryID();

            //Country c = (Country) session.createQuery("from Country where countryID = '" + user.getCountryId() + "'").list().get(0);
            Country c = (Country) session.getNamedQuery("getCountryById").setInteger("countryID", user.getCountryId()).list().get(0);
            cid.setCountryName(c.getCountryName());
            cid.setValue(user.getCountryId());
            u.setCountryID(cid);
            u.setCurrencyID(c.getCurrency().getCurrencyId());
            UserTypeID typeID = new UserTypeID();
            typeID.setUserTypeName("");
            typeID.setValue("");
            u.setUserTypeID(typeID);

            GregorianCalendar gc = new GregorianCalendar();
            gc = new GregorianCalendar();
            gc.setTime(user.getActiveFromDate());
            u.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));

            gc = new GregorianCalendar();
            gc.setTime(user.getActiveToDate());
            u.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            u.setIsLockedOut(user.getIsLockedOut());
            u.setChangePwdRequired(user.getChangePwdRequired());
            u.setUserSmallImageURL(user.getUserSmallImageUrl());

            gc = new GregorianCalendar();
            if (user.getLastLoginDate() != null) {
                gc.setTime(user.getLastLoginDate());
            }
            u.setLastLoginDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));

            gc = new GregorianCalendar();
            if (user.getLockedOutDate() != null) {
                gc.setTime(user.getLockedOutDate());
                u.setLockedOutDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            }
            UserSettings settings = new UserSettings();
            UserSetting set = null;
            int seq = 0;
            UserDAO userDAO = new UserDAO();
            UserApplicationSettings settings2 = userDAO.getUserSettings2BySettingName(user.getUserId(), "WelcomeUponLogin", session);
            if (settings2 != null && settings2.getApplicationSettingValue().equalsIgnoreCase("1")) {
                set = new UserSetting();
                set.setSeqNo(seq++);
                set.setUserSettingID(settings2.getUserApplicationSettingId());
                set.setUserSettingName(settings2.getApplicationSettingName());
                set.setUserSettingValue(settings2.getApplicationSettingValue());
                settings2.setApplicationSettingValue("0");
                userDAO.saveOrUpdateUserSettings2(settings2, session);
                settings.getUserSetting().add(set);
            } else {
                List<UserSettings2> sett2 = session.getNamedQuery("getUserSettingByUserID").setString("userID", user.getUserId()).list();
                for (UserSettings2 us2 : sett2) {
                    set = new UserSetting();
                    set.setSeqNo(seq++);
                    set.setUserSettingID(us2.getUserSettingsId());
                    set.setUserSettingName(us2.getSettingsName());
                    set.setUserSettingValue(us2.getSettingsValue());
                    //set.setLastUpdatedByUserID(null);//TODO add lastupdated by
                    settings.getUserSetting().add(set);
                }
            }
            u.setUserSettings(settings);
            gc = new GregorianCalendar();
            gc.setTime(user.getCreatedDate());
            u.setCreatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));

            gc = new GregorianCalendar();
            gc.setTime(user.getUpdatedDate());
            u.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));

            us.getUser().add(u);
            tcm_user.setUsers(us);
            return tcm_user;
        } catch (Exception e) {
            TCMUtil.exceptionLog(UserManager.class.getName(), e);
            tcm_user.getMsgStatus().setResponseCode(-1);
            tcm_user.getMsgStatus().setResponseText("Account is Invalid ");
            return tcm_user;
        }

    }

    protected TingcoUsers getLoggedinUserTCM(int status, String loginName, String appID, String domainID, String token, String remoteHost, String userId, Session session, List<ApplicationModuleTranslations> translations) throws DatatypeConfigurationException {

        TingcoUsers tcm_user = buildUserTemplate();
        try {
            Object obj = session.getNamedQuery("getUserByLoginNameandDomainId").setString("loginName", loginName).setString("domainID", domainID).uniqueResult();
            if (obj == null) {
                tcm_user.getMsgStatus().setResponseCode(-1);
                tcm_user.getMsgStatus().setResponseText("Not a valid user");
                return tcm_user;
            }

            Users us = new Users();
            Users2 user = (Users2) obj;
            if (!token.equalsIgnoreCase("")) {
                UserSession user_sess = new UserSession();
                user_sess.setUserID(user.getUserId());
                user_sess.setApplicationID(appID.toUpperCase());
                user_sess.setAuthenticationToken(token);
                us.setUserSession(user_sess);
            }
            User u = new User();

            if (!translations.isEmpty()) {
                ApplicationModules ams = new ApplicationModules();
                int seq = 0;
                for (ApplicationModuleTranslations amt : translations) {
                    ApplicationModule am = new ApplicationModule();
                    am.setSeqNo(seq++);
                    am.setApplicationModuleID(amt.getId().getApplicationModuleId());
                    am.setApplicationModuleName(amt.getApplicationModuleName());
                    ams.getApplicationModule().add(am);
                }
                u.setApplicationModules(ams);
            }

            u.setSeqNo(1);
            u.setUserID(user.getUserId());
            u.setDomainID(user.getId().getDomainId());
            UserManager manager = new UserManager();
            UserRoles roles = manager.getUserRolesTCM(user.getUserId(), session);
            u.setUserRoles(roles);

            GroupID groupID = new GroupID();
            List<GroupTranslations> gtList = session.getNamedQuery("getGroupTranslationsById").setString("groupID", user.getGroupId()).setInteger("countryID", user.getCountryId()).list();
            if (!gtList.isEmpty()) {
                groupID.setGroupName(gtList.get(0).getGroupName());
            } else {
                groupID.setGroupName(user.getGroupId());
            }
            groupID.setValue(user.getGroupId());
            u.setGroupID(groupID);
            u.setLoginName(user.getId().getLoginName());
            u.setFirstName(user.getFirstName());
            u.setLastName(user.getLastName());
            u.setUserEmail(user.getUserEmail());
            u.setUserMobilePhone(user.getUserMobilePhone());

            CountryID cid = new CountryID();

            Country c = (Country) session.getNamedQuery("getCountryById").setInteger("countryID", user.getCountryId()).list().get(0);
            cid.setCountryName(c.getCountryName());
            cid.setValue(user.getCountryId());
            u.setCountryID(cid);
            u.setCurrencyID(c.getCurrency().getCurrencyId());
            UserTypeID typeID = new UserTypeID();
            typeID.setUserTypeName("");
            typeID.setValue("");
            u.setUserTypeID(typeID);

            GregorianCalendar gc = new GregorianCalendar();
            gc = new GregorianCalendar();
            gc.setTime(user.getActiveFromDate());
            u.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));

            gc = new GregorianCalendar();
            gc.setTime(user.getActiveToDate());
            u.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            u.setIsLockedOut(user.getIsLockedOut());
            u.setChangePwdRequired(user.getChangePwdRequired());
            u.setUserSmallImageURL(user.getUserSmallImageUrl());

            gc = new GregorianCalendar();
            if (user.getLastLoginDate() != null) {
                gc.setTime(user.getLastLoginDate());
            }
            u.setLastLoginDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));

            gc = new GregorianCalendar();
            if (user.getLockedOutDate() != null) {
                gc.setTime(user.getLockedOutDate());
                u.setLockedOutDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            }

            UserSetting set = null;
            int seq = 0;
            UserDAO userDAO = new UserDAO();
            UserApplicationSettings settings2 = userDAO.getUserSettings2BySettingName(user.getUserId(), "WelcomeUponLogin", session);
            if (settings2 != null && settings2.getApplicationSettingValue().equalsIgnoreCase("1")) {
                UserSettings settings = new UserSettings();
                set = new UserSetting();
                set.setSeqNo(seq++);
                set.setUserSettingID(settings2.getUserApplicationSettingId());
                set.setUserSettingName(settings2.getApplicationSettingName());
                set.setUserSettingValue(settings2.getApplicationSettingValue());
                settings2.setApplicationSettingValue("0");
                userDAO.saveOrUpdateUserSettings2(settings2, session);
                settings.getUserSetting().add(set);
                u.setUserSettings(settings);
            } else {

                List<UserSettings2> sett2 = session.getNamedQuery("getUserSettingByUserID").setString("userID", user.getUserId()).list();
                if (!sett2.isEmpty()) {
                    UserSettings settings = new UserSettings();
                    for (UserSettings2 us2 : sett2) {
                        set = new UserSetting();
                        set.setSeqNo(seq++);
                        set.setUserSettingID(us2.getUserSettingsId());
                        set.setUserSettingName(us2.getSettingsName());
                        set.setUserSettingValue(us2.getSettingsValue());
                        settings.getUserSetting().add(set);
                    }
                    u.setUserSettings(settings);
                }

            }

            gc = new GregorianCalendar();
            gc.setTime(user.getCreatedDate());
            u.setCreatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));

            gc = new GregorianCalendar();
            gc.setTime(user.getUpdatedDate());
            u.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));

            us.getUser().add(u);
            tcm_user.setUsers(us);
            return tcm_user;
        } catch (Exception e) {
            TCMUtil.exceptionLog(UserManager.class.getName(), e);
            tcm_user.getMsgStatus().setResponseCode(-1);
            tcm_user.getMsgStatus().setResponseText("Account is Invalid ");
            return tcm_user;
        }

    }

    public boolean insertForgotPassword(String appID, String domainID, String emailID, TingcoUsers t_User, Session session, HttpServletRequest request) {
        if (isValidApplication(appID, domainID, session)) {
            Transaction tx = session.beginTransaction();
            UserDAO dao = new UserDAO();
            ForgetPasswords forgot = new ForgetPasswords();
            ForgetPasswordsId id = new ForgetPasswordsId();
            String link = null;
            String randomID = null;
            ApplicationDAO app = new ApplicationDAO();
            GregorianCalendar gc = new GregorianCalendar();
            Users2 user = null;
            try {
                user = (Users2) session.getNamedQuery("getUserByLoginNameandDomainId").setString("loginName", emailID).setString("domainID", domainID).list().get(0);
            } catch (Exception e) {
                TCMUtil.exceptionLog(UserManager.class.getName(), e);
                return false;
            }
            id.setDomainId(domainID);
            id.setUserId(user.getUserId());

            forgot.setId(id);
            forgot.setCreatedDate(gc.getTime());
            forgot.setUpdatedDate(gc.getTime());
            forgot.setIsActive(1);
            forgot.setIsMailSent(Boolean.FALSE);
            Applications apps = app.getApplication(appID, session);
            if (apps != null) {
                for (Iterator it = apps.getApplicationSettingses().iterator(); it.hasNext();) {
                    ApplicationSettings as = (ApplicationSettings) it.next();
                    if (as.getApplicationSettingName().equalsIgnoreCase("ApplicationCreateNewPasswordURL")) {
                        link = as.getApplicationSettingValue();
                        break;
                    }
                }
            }
            StringBuffer sb = new StringBuffer(link);
            sb.append("?id=");

            randomID = UUID.randomUUID().toString();
            sb.append(randomID);
            forgot.setForgetPasswordLink(sb.toString());
            session.saveOrUpdate(forgot);
            tx.commit();

            UserLog log = new UserLog();
            log.setAction("ForgotPassword");
            log.setActionValue1(emailID);
            log.setActionValue2(sb.toString());
            log.setCreatedDate(gc.getTime());
            log.setTableName("ForgetPasswords");
            log.setUserId(user.getUserId());
            log.setRequestIp(request.getRemoteAddr());
            dao.saveUserLog(log, session);

            Users users = new Users();
            User u = new User();
            u.setSeqNo(1);
            u.setFirstName(user.getFirstName());
            u.setLastName(user.getLastName());
            u.setUserID(user.getUserId());
            users.getUser().add(u);
            t_User.getMsgStatus().setResponseText(randomID);
            t_User.setUsers(users);
            return true;
        } else {
            return false;
        }

    }

    public ForgetPasswords isValidForgotPassword(String id, String token, Session session) {
        List<ForgetPasswords> links = null;
        Transaction tx = session.beginTransaction();
        try {
            links = session.getNamedQuery("getAllForgotPasswords").list();
        } catch (Exception e) {
            return null;
        }

        for (ForgetPasswords fp : links) {
            if (fp.getForgetPasswordLink().indexOf(id) != -1) {
                if (fp.getIsActive() != 0) {
                    fp.setIsActive(0);
                    session.saveOrUpdate(fp);
                    tx.commit();
                    return fp;
                } else {
                    return null;
                }
            }
        }
        return null;

    }

    public boolean savePassword(String userID, String domainID, String pwd, Session session) {
        boolean result = false;
        try {
            List<Users2> usersList = session.getNamedQuery("getUserByUserIDandDomainId").setString("userID", userID).setString("domainID", domainID).list();
            if (!usersList.isEmpty()) {
                Users2 user = usersList.get(0);
                Transaction tx = session.beginTransaction();
                UserPasswords up = new UserPasswords();
                up.setDomainId(domainID);
                GregorianCalendar gc = new GregorianCalendar();
                up.setCreatedDate(gc.getTime());
                up.setId(UUID.randomUUID().toString());
                up.setPassword(RSAPassword.encryptedPwd(pwd));
                up.setUserId(userID);
                session.saveOrUpdate(up);
                tx.commit();
                user.setPassword(RSAPassword.encryptedPwd(pwd));
                user.setLastPasswordChangedDate(gc.getTime());
                updateUser(user, session);
                result = true;
            } else {
                result = false;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(UserManager.class.getName(), e);
            result = false;
        }
        return result;
    }

    public boolean isPrevious(String userID, String domainID, String pwd, Session session) {
        int count = 0;

        ArrayList<String> passwords = new ArrayList<String>();
        List<UserPasswords> up = session.getNamedQuery("getuserPreviousPasswords").setString("userID", userID).setString("domainID", domainID).list();
        for (UserPasswords userPwd : up) {
            if (count == 4) {
                break;
            }
            passwords.add(userPwd.getPassword());
            count++;
        }
        if (passwords.contains(pwd)) {
            return true;          //here true implies password is one among the last 4....
        }
        return false;
    }

    public TingcoUtils inviteNewUser(String invitedBy, String invitedTo, String domainID, String appID, TingcoUtils tingcoUtils, Session session) {
        String link = null;
        String randomID = null;
        Transaction tx = session.beginTransaction();
        GregorianCalendar gc = new GregorianCalendar();
        ApplicationDAO app = new ApplicationDAO();
        UserInvitations invitation = new UserInvitations();
        UserInvitationsId id = new UserInvitationsId(invitedBy, invitedTo, appID, domainID);
        invitation.setId(id);

        invitation.setCreatedDate(gc.getTime());
        invitation.setIsActive(Boolean.TRUE);
        invitation.setIsMailSent(Boolean.FALSE);

        Applications apps = app.getApplication(appID, session);
        if (apps != null) {
            for (Iterator it = apps.getApplicationSettingses().iterator(); it.hasNext();) {
                ApplicationSettings as = (ApplicationSettings) it.next();
                if (as.getApplicationSettingName().equalsIgnoreCase("ApplicationUserActivationURL")) {
                    link = as.getApplicationSettingValue();
                } else if (as.getApplicationSettingName().equalsIgnoreCase("UserInviteExpiryTime")) {
                    gc.add(Calendar.DATE, Integer.valueOf(as.getApplicationSettingValue()));
                    invitation.setActiveToDate(gc.getTime());
                }
            }
        }

        StringBuffer sb = new StringBuffer(link);
        sb.append("?id=");

        randomID = UUID.randomUUID().toString();
        sb.append(randomID);
        invitation.setInvitationLink(sb.toString());
        session.saveOrUpdate(invitation);
        tx.commit();

        UserInvitation ui = new UserInvitation();
        ui.setInvitationLink(sb.toString());

        Users2 from = getUserByUserID(invitedBy, session);
        ui.setInvitedBy(from.getFirstName() + " " + from.getLastName());

        UsersToActivate to = getUserActivateByUserID(invitedTo, session);
        if (to != null) {
            ui.setInvitedTo(to.getFirstName() + " " + to.getLastName());
        }
        ui.setIsActive(1);
        tingcoUtils.setUserInvitation(ui);
        return tingcoUtils;
    }

    private Users2 getUserByUserID(String userID, Session session) {

        Users2 user = null;
        try {
            // user = (Users2) session.createQuery("from Users2 where UserID = '" + userID + "'").list().get(0);
            user = (Users2) session.getNamedQuery("getUsers2ById").setString("userID", userID).list().get(0);
        } catch (Exception e) {
            return null;
        }
        return user;
    }

    private UsersToActivate getUserActivateByUserID(String userID, Session session) {

        UsersToActivate user = null;
        try {
            //user = (UsersToActivate) session.createQuery("from UsersToActivate where UserID = '" + userID + "'").list().get(0);
            user = (UsersToActivate) session.getNamedQuery("getUserToActivateByUserID").setString("userID", userID).list().get(0);
        } catch (Exception e) {
            return null;
        }
        return user;
    }

    public TingcoUsers isValidInvitation(String id, TingcoUsers user, Session session) {
        List<UserInvitations> invitations = null;
        Transaction tx = session.beginTransaction();
        ApplicationDAO app = new ApplicationDAO();
        String expiryTime = null;
        GregorianCalendar gc = new GregorianCalendar();
        long diff = 0;
        String appID = null;
        String invited2userid = null;
        boolean exists = false;
        try {
            //  invitations = session.createQuery("from UserInvitations").list();'
            invitations = session.getNamedQuery("getAllUserInvitations").list();

            for (UserInvitations ui : invitations) {
                diff = (ui.getActiveToDate().getTime() - gc.getTimeInMillis()) / (1000 * 60 * 60 * 24);
                if (ui.getInvitationLink().indexOf(id) != -1) {
                    exists = true;
                    appID = ui.getId().getApplicationId();
                    invited2userid = ui.getId().getInvitedToUserId();
                    Applications apps = app.getApplication(appID, session);
                    if (apps != null) {
                        for (Iterator it = apps.getApplicationSettingses().iterator(); it.hasNext();) {
                            ApplicationSettings as = (ApplicationSettings) it.next();
                            if (as.getApplicationSettingName().equalsIgnoreCase("UserInviteExpiryTime")) {
                                expiryTime = as.getApplicationSettingValue();
                                break;
                            }
                        }
                    }
                    UsersToActivate act = (UsersToActivate) session.getNamedQuery("getUserToActivateByUserID").setString("userID", invited2userid).list().get(0);
                    Users users = new Users();
                    User u = new User();
                    u.setSeqNo(1);
                    u.setUserEmail(act.getUserEmail());
                    u.setUserID(invited2userid);
                    u.setApplicationID(appID);
                    u.setFirstName(act.getFirstName());
                    u.setLastName(act.getLastName());
                    users.getUser().add(u);
                    user.setUsers(users);
                    if (diff <= Integer.valueOf(expiryTime) && diff >= 0) {
                        if (ui.getIsActive()) {
                            ui.setIsActive(Boolean.FALSE);
                            session.saveOrUpdate(ui);
                            tx.commit();
                        } else {
                            user.getMsgStatus().setResponseCode(-3);
                            user.getMsgStatus().setResponseText("Error");
                        }
                    } else {
                        user.getMsgStatus().setResponseCode(-2);
                        user.getMsgStatus().setResponseText("Error");
                        ui.setIsActive(Boolean.FALSE);
                        session.saveOrUpdate(ui);
                        tx.commit();
                    }
                }
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(UserManager.class.getName(), e);
        }
        if (!exists) {
            user.getMsgStatus().setResponseCode(-1);
            user.getMsgStatus().setResponseText("Error");
        }
        return user;
    }

    public TingcoUsers activateUser(String userID, String domainID, String appID, TingcoUsers tingcoUser, Session session) {
        Transaction tx = session.beginTransaction();
        try {

            //UsersToActivate uta = (UsersToActivate) session.createQuery("from UsersToActivate where UserID = '" + userID + "' and DomainID = '" + domainID + "'").list().get(0);
            UsersToActivate uta = (UsersToActivate) session.getNamedQuery("getUserToActivateByUserIDandDomainID").setString("userID", userID).setString("domainID", domainID).list().get(0);
            Users2 newuser = new Users2();
            Users2Id id = new Users2Id();
            id.setLoginName(uta.getId().getLoginName());
            id.setDomainId(uta.getId().getDomainId());
            newuser.setId(id);
            UserTypes2 type = new UserTypes2(uta.getUserTypes2().getUserTypeId());
            newuser.setUserId(userID);
            newuser.setUserTypes2(type);

            newuser.setActiveFromDate(uta.getActiveFromDate());
            newuser.setActiveToDate(uta.getActiveToDate());
            newuser.setChangePwdRequired(uta.getChangePwdRequired());
            newuser.setCountryId(uta.getCountryId());
            newuser.setCreatedDate(uta.getCreatedDate());
            newuser.setFailedPasswordAttemptCount(uta.getFailedPasswordAttemptCount());
            newuser.setFirstName(uta.getFirstName());
            newuser.setGroupId(uta.getGroupId());
            newuser.setIsLockedOut(uta.getIsLockedOut());
            newuser.setLastLoginDate(uta.getLastLoginDate());
            newuser.setLastName(uta.getLastName());
            newuser.setLastPasswordChangedDate(uta.getLastPasswordChangedDate());
            newuser.setLockedOutDate(uta.getLockedOutDate());
            newuser.setPassword(uta.getPassword());
            newuser.setUpdatedDate(uta.getUpdatedDate());
            newuser.setUserEmail(uta.getUserEmail());
            newuser.setUserMobilePhone(uta.getUserMobilePhone());
            newuser.setUserSmallImageUrl(uta.getUserSmallImageUrl());
            session.saveOrUpdate(newuser);
            session.delete(uta);
            //Moving data from UserSettingsToActivate table to UserSettings2 table.
            GregorianCalendar gc = new GregorianCalendar();
            //UserApplicationSettingsToActivate usta = (UserApplicationSettingsToActivate) session.createQuery("from UserApplicationSettingsToActivate where UserID = '" + userID + "'").list().get(0);
            List<UserApplicationSettingsToActivate> ustaList = session.getNamedQuery("getUserApplicationSettingsToActivateByUserID").setString("userID", userID).list();

            if (ustaList != null) {
                UserApplicationSettingsToActivate usta = ustaList.get(0);

                UserApplicationSettings uas = new UserApplicationSettings();
                uas.setUserApplicationSettingId(usta.getUserApplicationSettingId());
                uas.setUserId(userID);
                uas.setApplicationId(usta.getApplicationId());
                uas.setApplicationSettingName(usta.getApplicationSettingName());
                uas.setApplicationSettingValue(usta.getApplicationSettingValue());
                uas.setSettingDataTypeId("A67EF262-2CDB-4292-B8D7-6517EBF20053");
                uas.setActiveFromDate(gc.getTime());
                uas.setCreatedDate(gc.getTime());
                uas.setUpdatedDate(gc.getTime());

                session.saveOrUpdate(uas);
                session.delete(usta);
            }

            List<UserRoleMembershipsToActivate> urm2act = session.getNamedQuery("getUserRoleMembershipsToActivateByUserID").setString("userID", userID).list();

            for (UserRoleMembershipsToActivate urm : urm2act) {
                UserRoleMemberships2 urm2 = new UserRoleMemberships2();
                UserRoleMemberships2Id urmid = new UserRoleMemberships2Id(urm.getId().getUserId(), urm.getId().getUserRoleId());
                urm2.setId(urmid);
                urm2.setCreatedDate(gc.getTime());
                urm2.setUpdatedDate(gc.getTime());
                urm2.setLastUpdatedByUserId(userID);
                session.saveOrUpdate(urm2);
                session.delete(urm);
            }

            UserTimeZonesToActivate utzact = (UserTimeZonesToActivate) session.getNamedQuery("getUserTimeZonesToActivateByUserID").setString("userID", userID).list().get(0);
            UserTimeZones2 utz2 = new UserTimeZones2();
            utz2.setCreatedDate(gc.getTime());
            utz2.setTimeZoneId(utzact.getTimeZoneId());
            utz2.setUpdatedDate(gc.getTime());
            utz2.setUseDaylightSaving(utzact.getUseDaylightSaving());
            utz2.setUserId(userID);
            session.saveOrUpdate(utz2);
            session.delete(utzact);
            Users us = new Users();
            UserSession user_sess = new UserSession();
            user_sess.setUserID(uta.getUserId());
            String token = UUID.randomUUID().toString();
            user_sess.setAuthenticationToken(token);
            us.setUserSession(user_sess);
            User u = new User();
            u.setSeqNo(1);
            u.setUserID(uta.getUserId());
            u.setDomainID(uta.getId().getDomainId());
            UserDAO userDAO = new UserDAO();
            UserApplicationSettings settings2 = userDAO.getUserSettings2BySettingName(uta.getUserId(), "WelcomeUponLogin", session);
            UserSettings settings = new UserSettings();

            UserSetting set = null;
            int seq = 0;

            if (settings2 != null && settings2.getApplicationSettingValue().equalsIgnoreCase("1")) {
                set = new UserSetting();
                set.setSeqNo(seq++);
                set.setUserSettingID(settings2.getUserApplicationSettingId());
                set.setUserSettingName(settings2.getApplicationSettingName());
                set.setUserSettingValue(settings2.getApplicationSettingValue());
                settings2.setApplicationSettingValue("0");
                session.saveOrUpdate(settings2);
                settings.getUserSetting().add(set);
            }
            u.setUserSettings(settings);
            GroupID groupID = new GroupID();
            List<GroupTranslations> gtList = session.getNamedQuery("getGroupTranslationsById").setString("groupID", uta.getGroupId()).setInteger("countryID", uta.getCountryId()).list();
            if (gtList != null && gtList.size() > 0) {
                groupID.setGroupName(gtList.get(0).getGroupName());
            } else {
                groupID.setGroupName(uta.getGroupId());
            }
            groupID.setValue(uta.getGroupId());
            u.setGroupID(groupID);
            u.setLoginName(uta.getId().getLoginName());
            u.setFirstName(uta.getFirstName());
            u.setLastName(uta.getLastName());
            u.setUserEmail(uta.getUserEmail());
            u.setUserMobilePhone(uta.getUserMobilePhone());
            u.setUserSmallImageURL(uta.getUserSmallImageUrl());
            CountryID cid = new CountryID();
            Country c = (Country) session.getNamedQuery("getCountryById").setInteger("countryID", uta.getCountryId()).list().get(0);
            cid.setCountryName(c.getCountryName());
            cid.setValue(uta.getCountryId());
            u.setCountryID(cid);
            gc = new GregorianCalendar();
            gc.setTime(uta.getActiveFromDate());
            u.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            gc = new GregorianCalendar();
            gc.setTime(uta.getActiveToDate());
            u.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            us.getUser().add(u);
            tingcoUser.setUsers(us);

            //save the session in usersessions2

            UserSessions2 sess = new UserSessions2();
            sess.setUserSessionId(UUID.randomUUID().toString());
            sess.setCreatedDate(gc.getTime());
            sess.setUpdatedDate(gc.getTime());
            gc.add(GregorianCalendar.MINUTE, 15);
            sess.setActiveToDate(gc.getTime());
            sess.setApplicationId(appID);
            sess.setDomainId(domainID);
            sess.setAuthenticationToken(token);
            sess.setUserId(uta.getUserId());

            session.saveOrUpdate(sess);
            tx.commit();

        } catch (Exception e) {
            tingcoUser.getMsgStatus().setResponseCode(-1);
            tingcoUser.getMsgStatus().setResponseText("Error");
            TCMUtil.exceptionLog(UserManager.class.getName(), e);
            tx.rollback();

        }
        return tingcoUser;
    }

    public TingcoUsers getAllUsers(String domainId, TingcoUsers tingcoUser, Session session) {
        List<Users2> users = session.getNamedQuery("getAllUsersByDomainID").setString("domainID", domainId).list();
        List<Groups> grps = session.getNamedQuery("getAllGroupss").list();
        Users t_Users = new Users();
        String g_Name = null;
        int seq = 1;
        for (Users2 u : users) {
            User us = new User();
            us.setSeqNo(seq++);
            us.setUserID(u.getUserId());
            us.setFirstName(u.getFirstName());
            us.setLastName(u.getLastName());
            us.setUserEmail(u.getUserEmail());
            GroupID gid = new GroupID();
            gid.setValue(u.getGroupId());
            g_Name = getGroupName(u.getGroupId(), grps);
            gid.setGroupName(g_Name);
            t_Users.getUser().add(us);
        }
        tingcoUser.setUsers(t_Users);
        return tingcoUser;
    }

    private String getGroupName(String grpID, List<Groups> grps) {
        String name = null;
        for (Groups g : grps) {
            if (g.getGroupId().equalsIgnoreCase(grpID)) {
                Set<GroupTranslations> trans = g.getGroupTranslationses();
                for (GroupTranslations gt : trans) {
                    if (gt.getGroups().getGroupId().equalsIgnoreCase(grpID)) {
                        name = gt.getGroupName();
                        break;
                    }
                }
                break;
            }
        }
        return name;
    }

    public void addNewUserToActivate(String userID, String domainID, String appID, String emailID, String groupID, String firstName, String lastName, String nickName, String countryID, Session session) {
        Transaction tx = session.beginTransaction();
        UsersToActivate uta = new UsersToActivate();
        UsersToActivateId id = new UsersToActivateId(emailID, domainID);
        uta.setId(id);
        uta.setUserId(userID);
        uta.setFirstName(firstName);
        uta.setLastName(lastName);
        uta.setUserEmail(emailID);
        uta.setGroupId(groupID);
        uta.setPassword("");

        uta.setCountryId(Integer.valueOf(countryID));
        //UserTypes2 type = new UserTypes2("4a24b403-67ee-4236-acf2-58ee94d3a4a3");//TODO specify based on whether he is normal or admin user
        uta.setUserTypes2(new UserTypes2("4a24b403-67ee-4236-acf2-58ee94d3a4a3"));
        uta.setChangePwdRequired(0);
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        uta.setLastLoginDate(gc.getTime());
        uta.setLockedOutDate(gc.getTime());
        uta.setLastPasswordChangedDate(gc.getTime());
        uta.setFailedPasswordAttemptCount(0);
        uta.setCreatedDate(gc.getTime());
        uta.setUpdatedDate(gc.getTime());
        uta.setActiveFromDate(gc.getTime());
        gc = new GregorianCalendar();

        gc.add(Calendar.YEAR, 5);
        uta.setActiveToDate(gc.getTime());
        uta.setUserSmallImageUrl("");
        session.saveOrUpdate(uta);
        session.flush();
        // Saving the WelcomeUponLogin = 1 for all New Users in UserApplicationSettingsToActivate Table.

        UserApplicationSettingsToActivate uasta = new UserApplicationSettingsToActivate();
        uasta.setUserApplicationSettingId(UUID.randomUUID().toString());
        uasta.setApplicationSettingName("WelcomeUponLogin");
        uasta.setApplicationId(appID);
        uasta.setApplicationSettingValue("1");
        uasta.setUserId(userID);
        uasta.setSettingDataTypeId("A67EF262-2CDB-4292-B8D7-6517EBF20053");
        gc = new GregorianCalendar();
        uasta.setLastUpdatedByUserId(userID);
        uasta.setCreatedDate(gc.getTime());
        uasta.setUpdatedDate(gc.getTime());
        session.saveOrUpdate(uasta);
        tx.commit();
        session.flush();
    }

    public void lockUser(String userID, String locked, Session session) {
        Transaction tx = session.beginTransaction();
        // UsersToActivate uta = (UsersToActivate) session.createQuery("from UsersToActivate where UserID = '" + userID + "'").list().get(0);
        UsersToActivate uta = (UsersToActivate) session.getNamedQuery("getUserToActivateByUserID").setString("userID", userID).list().get(0);
        uta.setIsLockedOut(Integer.valueOf(locked));
        session.saveOrUpdate(uta);
        tx.commit();
    }

    public boolean isCorrectPassword(String userID, String domainId, String pwd, Session session) {
        Users2 user = null;
        try {
            //user = (Users2) session.createQuery("from Users2 where UserID = '" + userID + "' and domains.domainId = '" + domainId + "'").list().get(0);
            user = (Users2) session.getNamedQuery("getUserByUserIDandDomainId").setString("userID", userID).setString("domainID", domainId).list().get(0);
        } catch (IndexOutOfBoundsException ie) {
            return false;
        } catch (HibernateException e) {
            return false;
        }

        if (user != null) {
            String cmp = RSAPassword.encryptedPwd(pwd);
            if (user.getPassword().equalsIgnoreCase(cmp)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public TingcoUsers getTrustedOrganisations(String groupID, int countryID, TingcoUsers tingcoUser, Session session) {
        GroupDAO grpMan = new GroupDAO();
        List<GroupTrusts> list = grpMan.getGroupTrustByGroupID(groupID, session);
        List<Groups> grps = grpMan.loadAllGroups(session);
        String grpID = null;
        String roleID = null;
        Users users = new Users();
        User user = new User();
        int seq = 1;
        user.setSeqNo(1);
        UserGroupMemberships group_mem = new UserGroupMemberships();
        UserRoles userRoles = new UserRoles();
        for (GroupTrusts gt : list) {
            grpID = gt.getId().getGroupId();
            for (Groups g : grps) {
                GroupID gid = new GroupID();
                if (g.getGroupId().equalsIgnoreCase(grpID)) {
                    gid.setValue(g.getGroupId());
                    Set<GroupTranslations> trans = g.getGroupTranslationses();
                    for (GroupTranslations grouptrans : trans) {
                        if (grouptrans.getCountry().getCountryId() == countryID) {
                            gid.setGroupName(grouptrans.getGroupName());
                            group_mem.getGroupID().add(gid);
                            break;
                        }
                    }
                    break;
                }
            }
        }
        user.setUserGroupMemberships(group_mem);
        for (GroupTrusts gt : list) {
            roleID = gt.getId().getUserRoleId();
            UserRole ur = new UserRole();
            ur.setSeqNo(seq++);
            ur.setUserRoleID(roleID);
            //UserRoles2 roles = (UserRoles2) session.createQuery("from UserRoles2 where UserRoleID = '" + roleID + "'").list().get(0);
            UserRoles2 roles = (UserRoles2) session.getNamedQuery("getUserRoleByUserRoleID").setString("roleID", roleID).list().get(0);
            ur.setUserRoleName(roles.getUserRoleName());
            userRoles.getUserRole().add(ur);
        }
        user.setUserRoles(userRoles);
        users.getUser().add(user);
        tingcoUser.setUsers(users);
        return tingcoUser;
    }

    public boolean isRolePermissionExists(String roleID, String permID, String groupID, Session session) {
        //List<UserRoleObjectPermissions2> list = session.createQuery("from UserRoleObjectPermissions2 where UserRoleID = '" + roleID + "' and PermissionID = '" + permID + "' and ObjectID = '" + groupID + "'").list();
        List<UserRoleObjectPermissions2> list = session.getNamedQuery("getUserRoleObjectPermissions2").setString("userroleID", roleID).setString("permissionID", permID).setString("objectID", groupID).list();
        if (list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public TingcoUsers getAllRolesandOrgByGroupID(String groupID, TingcoUsers tingcoUser, int countryID, Session session) {
        GroupDAO grpMan = new GroupDAO();
        List<Groups> grps = grpMan.loadAllGroups(session);
        UserGroupMemberships group_mem = new UserGroupMemberships();
        Users users = new Users();

        int userSeq = 1;

        for (Groups g : grps) {
            GroupID gid = new GroupID();
            if (g.getGroupId().equalsIgnoreCase(groupID)) {
                gid.setValue(g.getGroupId());
                Set<GroupTranslations> trans = g.getGroupTranslationses();
                for (GroupTranslations grouptrans : trans) {
                    if (grouptrans.getCountry().getCountryId() == countryID) {
                        gid.setGroupName(grouptrans.getGroupName());
                        group_mem.getGroupID().add(gid);
                        break;
                    }
                }
                break;
            }
        }
        List<UserRolesInGroups2> list_urig = session.getNamedQuery("getUserRolesInGroups2ByGroupID").setString("groupID", groupID).list();

        for (UserRolesInGroups2 urig : list_urig) {
            List<UserRoleObjectPermissions2> list_urop = session.getNamedQuery("getUserRoleObjectPermissions2ByObjectIDandUserRoleID").setString("objectID", groupID).setString("userroleID", urig.getUserRoles2().getUserRoleId()).list();

            for (UserRoleObjectPermissions2 urop : list_urop) {
                Permissions perms = new Permissions();
                int roleseq = 1;
                int permSeq = 1;
                User u = new User();
                u.setSeqNo(userSeq++);
                UserRoles user_roles = new UserRoles();
                UserRole ur = new UserRole();
                ur.setSeqNo(roleseq++);
                ur.setUserRoleID(urig.getUserRoles2().getUserRoleId());

                ur.setUserRoleName(urig.getUserRoles2().getUserRoleName());
                user_roles.getUserRole().add(ur);

                Permission p = new Permission();
                p.setSeqNo(permSeq++);
                p.setPermissionID(urop.getPermissions().getPermissionId());
                Set<PermissionTranslations> pt = urop.getPermissions().getPermissionTranslationses();
                String name_desc = getPermissionName(p.getPermissionID(), pt, countryID);
                p.setPermissionName(name_desc.substring(0, name_desc.indexOf("#")));
                p.setPermissionDesc(name_desc.substring(name_desc.indexOf("#") + 1));

                perms.getPermission().add(p);
                u.setUserGroupMemberships(group_mem);
                u.setPermissions(perms);
                u.setUserRoles(user_roles);
                users.getUser().add(u);
            }

        }

        tingcoUser.setUsers(users);
        return tingcoUser;
    }

    private String getPermissionName(String permissionID, Set<PermissionTranslations> pt, int countryID) {
        for (PermissionTranslations trans : pt) {
            if (trans.getId().getPermissionId().equalsIgnoreCase(permissionID) && trans.getId().getCountryId() == countryID) {
                return trans.getPermissionName() + "#" + trans.getPermissionDescription();
            }
        }
        return null;
    }

    public TingcoUsers getTrustingOrganisations(String groupID, int countryID, TingcoUsers tingcoUser, Session session) {
        List<GroupTrusts> list = session.getNamedQuery("getGroupTrustsByGroupId").setString("groupId", groupID).list();
        GroupDAO grpMan = new GroupDAO();
        List<Groups> grps = grpMan.loadAllGroups(session);
        String grpID = null;
        String roleID = null;
        Users users = new Users();
        User user = new User();
        int seq = 1;
        user.setSeqNo(1);
        UserGroupMemberships group_mem = new UserGroupMemberships();
        UserRoles userRoles = new UserRoles();
        for (GroupTrusts gt : list) {
            grpID = gt.getId().getItrustGroupId();
            for (Groups g : grps) {
                GroupID gid = new GroupID();

                if (g.getGroupId().equalsIgnoreCase(grpID)) {
                    gid.setValue(g.getGroupId());
                    Set<GroupTranslations> trans = g.getGroupTranslationses();
                    for (GroupTranslations grouptrans : trans) {
                        if (grouptrans.getCountry().getCountryId() == countryID) {
                            gid.setGroupName(grouptrans.getGroupName());
                            group_mem.getGroupID().add(gid);
                            break;
                        }
                    }
                    break;
                }
            }
        }
        user.setUserGroupMemberships(group_mem);
        for (GroupTrusts gt : list) {
            roleID = gt.getId().getUserRoleId();
            UserRole ur = new UserRole();
            ur.setSeqNo(seq++);
            ur.setUserRoleID(roleID);
            UserRoles2 roles = (UserRoles2) session.getNamedQuery("getUserRoleByUserRoleID").setString("roleID", roleID).list().get(0);
            ur.setUserRoleName(roles.getUserRoleName());
            userRoles.getUserRole().add(ur);
        }
        user.setUserRoles(userRoles);
        users.getUser().add(user);
        tingcoUser.setUsers(users);
        return tingcoUser;
    }

    protected TingcoUsers getUserAppSettings(String userID, String appID, TingcoUsers tingcoUser, Session session) {
        List<UserApplicationSettings> userSettings = session.getNamedQuery("getUserAppSettingByAppID").setString("userId", userID).setString("applicationID", appID).list();
        Users users = new Users();
        User u = new User();
        UserSettings settings = new UserSettings();
        int seqID = 1;
        for (UserApplicationSettings uas : userSettings) {
            UserSetting us = new UserSetting();
            us.setSeqNo(seqID++);
            us.setUserSettingID(uas.getUserApplicationSettingId());
            us.setUserSettingName(uas.getApplicationSettingName());
            us.setUserSettingValue(uas.getApplicationSettingValue());

            settings.getUserSetting().add(us);
        }
        u.setUserSettings(settings);
        users.getUser().add(u);
        tingcoUser.setUsers(users);
        return tingcoUser;

    }

    protected void updateUserAppSetting(String userID, String appID, String key, String value, Session session) {
        Transaction tx = session.beginTransaction();
        List<UserApplicationSettings> settings = session.getNamedQuery("getUserAppSettingByAppID").setString("userId", userID).setString("applicationID", appID).list();
        for (UserApplicationSettings uas : settings) {
            if (uas.getApplicationSettingName().equalsIgnoreCase(key)) {
                uas.setApplicationSettingValue(value);
                session.saveOrUpdate(uas);
                break;
            }
        }
        tx.commit();
    }

    protected void addUserAppSetting(String userID, String appID, String key, String value, Session session) {
        Transaction tx = session.beginTransaction();
        GregorianCalendar gc = new GregorianCalendar();
        UserApplicationSettings uas = new UserApplicationSettings();
        uas.setUserApplicationSettingId(UUID.randomUUID().toString());
        uas.setUserId(userID);
        uas.setApplicationId(appID);
        uas.setApplicationSettingName(key);
        uas.setApplicationSettingValue(value);
        uas.setSettingDataTypeId("A67EF262-2CDB-4292-B8D7-6517EBF20053");
        uas.setActiveFromDate(gc.getTime());
        uas.setCreatedDate(gc.getTime());
        uas.setUpdatedDate(gc.getTime());
        session.saveOrUpdate(uas);
        tx.commit();
    }

    public boolean isValidEmail(String email, Session session) {
        try {
            List<Users2> us = session.getNamedQuery("getUsersByEmailID").setString("email", email).list();
            if (!us.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(UserManager.class.getName(), e);
            return false;
        }

    }

    public Users2 getusers2Byuserid(String userID, Session session) {
        try {
            List<Users2> user = session.getNamedQuery("getUsers2ById").setString("userID", userID).list();
            if (!user.isEmpty()) {
                return user.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(UserManager.class.getName(), e);
            return null;
        }

    }
}

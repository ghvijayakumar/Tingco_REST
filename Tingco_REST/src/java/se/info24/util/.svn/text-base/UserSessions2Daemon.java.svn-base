/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.mail.MessagingException;
import org.hibernate.Session;
import se.info24.pojo.ForgetPasswords;
import se.info24.pojo.UserInvitations;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.Users2;
import se.info24.user.UserSession2DAO;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Message;
import javax.mail.Multipart;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import se.info24.pojo.ApplicationEmailTemplates;
import se.info24.pojo.ApplicationSettings;
import se.info24.pojo.UserLoginResetRequests;
import se.info24.restUtil.UtilitiesResource;
import se.info24.user.UserDAO;

/**
 *
 * @author Sekhar
 */
public class UserSessions2Daemon {

    private Timer timer = null;
    private Timer mailTimer = null;
    private Timer deletePassResetReqTimer = null;
    private Timer checkLoginResetRequestsTimer = null;
    UserDAO userDAO = new UserDAO();
    UserSession2DAO userSessions2DAO = new UserSession2DAO();
    UtilitiesResource utilsResource = new UtilitiesResource();
    List<ApplicationSettings> appSettings = null;
    private Logger logger = Logger.getLogger(UserSessions2Daemon.class);
    String invitebody;
    String invitesubject;
    String fpbody;
    String fpsubject;
    String baseUrl;
    String supportEmail;
    String ApplicationBaseURL = null;
    String ResetPasswordURL = null;

    public UserSessions2Daemon() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            List<ApplicationSettings> appSettingsList = userSessions2DAO.getAllApplicationSettings(session);
            if (!appSettingsList.isEmpty()) {
                for (ApplicationSettings as : appSettingsList) {
                    if (as.getApplicationSettingName().equalsIgnoreCase("ApplicationBaseURL")) {
                        ApplicationBaseURL = as.getApplicationSettingValue();
                    }
                    if (as.getApplicationSettingName().equalsIgnoreCase("ResetPasswordURL")) {
                        ResetPasswordURL = as.getApplicationSettingValue();
                    }
                }
            }
        } catch (Exception e) {
            exceptionLog(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    public void initiateDaemonTimer(int timerValue) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        appSettings = session.getNamedQuery("getAppSettingByAppID").setString("appID", "BAB6ABE3-8BBE-4BC9-A8B9-B9C2534C6E16").list();//TODO read from web.xml
        if (!appSettings.isEmpty()) {
            for (ApplicationSettings as : appSettings) {
                if (as.getApplicationSettingName().equalsIgnoreCase("AccountActivationEmailBody")) {
                    invitebody = as.getApplicationSettingValue();
                } else if (as.getApplicationSettingName().equalsIgnoreCase("AccountActivationEmailSubject")) {
                    invitesubject = as.getApplicationSettingValue();
                } else if (as.getApplicationSettingName().equalsIgnoreCase("ApplicationBaseURL")) {
                    baseUrl = as.getApplicationSettingValue();
                } else if (as.getApplicationSettingName().equalsIgnoreCase("ForgottenEmailBody")) {
                    fpbody = as.getApplicationSettingValue();
                } else if (as.getApplicationSettingName().equalsIgnoreCase("ForgottenEmailSubject")) {
                    fpsubject = as.getApplicationSettingValue();
                } else if (as.getApplicationSettingName().equalsIgnoreCase("SupportEmail")) {
                    supportEmail = as.getApplicationSettingValue();
                }
            }
        }

        if (timer == null) {
            timer = new Timer();
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    removeAbondedAuthTokens();
                }
            }, 1000 * 60 * timerValue, 1000 * 60 * timerValue); // timerValue is in minutes.
        }

        if (mailTimer == null) {
            mailTimer = new Timer();
            mailTimer.schedule(new TimerTask() {

                @Override
                public void run() {
                    try {
                        sendUnsentMails();
                    } catch (Exception e) {
                        logger.info("Error occured in Sending Mail");
                    }
                }
            }, 1000 * 60 * timerValue, 1000 * 60 * timerValue); // timerValue is in minutes.
        }


    }

    public void initiateDeletePassResetReqTimer(int timerValue) {
        if (deletePassResetReqTimer == null) {
            deletePassResetReqTimer = new Timer();
            deletePassResetReqTimer.schedule(new TimerTask() {

                @Override
                public void run() {
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    deletePassResetReq(session);
                    session.close();
                }
            }, 1000 * 60 * timerValue, 1000 * 60 * timerValue); // timerValue is in minutes.
        }
    }

    public void sendUnsentMails() throws Exception {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            List<ForgetPasswords> list = session.getNamedQuery("getAllUnsentMails_FP").list();
            List<UserInvitations> userlist = session.getNamedQuery("getAllUnsentMails_UI").list();

            for (ForgetPasswords fp : list) {
                List<Users2> usersList = session.getNamedQuery("getUsers2ById").setString("userID", fp.getId().getUserId()).list();
                if (!usersList.isEmpty()) {
                    sendMail(usersList.get(0), "FP", fp.getForgetPasswordLink());
                    fp.setIsMailSent(Boolean.TRUE);
                    session.saveOrUpdate(fp);
                }
            }

            for (UserInvitations ui : userlist) {
                List<Users2> usersList = session.getNamedQuery("getUsers2ById").setString("userID", ui.getId().getInvitedToUserId()).list();
                if (!usersList.isEmpty()) {
                    sendMail(usersList.get(0), "UI", ui.getInvitationLink());
                    ui.setIsMailSent(Boolean.TRUE);
                    session.saveOrUpdate(ui);
                }

            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            exceptionLog(e);
        } finally {
            if (session != null) {
                session.close();
            }
//            System.gc();
        }

    }

    public void shutdownDaemonTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        if (mailTimer != null) {
            mailTimer.cancel();
            mailTimer = null;
        }


        if (deletePassResetReqTimer != null) {
            deletePassResetReqTimer.cancel();
            deletePassResetReqTimer = null;
        }

        if (checkLoginResetRequestsTimer != null) {
            checkLoginResetRequestsTimer.cancel();
            checkLoginResetRequestsTimer = null;
        }
    }

    void initiateCheckLoginResetRequestsTimer(int timerValue) {
        if (checkLoginResetRequestsTimer == null) {
            checkLoginResetRequestsTimer = new Timer();
            checkLoginResetRequestsTimer.schedule(new TimerTask() {

                @Override
                public void run() {
                    try {
                        checkLoginResetRequests();
                    } catch (Exception ex) {
                        logger.info("Error occured");
                        exceptionLog(ex);
                    }
                }
            }, 1000 * 60 * timerValue, 1000 * 60 * timerValue); // timerValue is in minutes.
        }
    }

    private void checkLoginResetRequests() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            List<UserLoginResetRequests> userLoginResetRequestsList = userSessions2DAO.getAllUserLoginPassResetReq(session);
            if (!userLoginResetRequestsList.isEmpty()) {
                for (UserLoginResetRequests ulrr : userLoginResetRequestsList) {
                    String userid = ulrr.getUserId();
                    List<Users2> users2List = userDAO.getUsers2ById(userid, session);
                    if (!users2List.isEmpty()) {
                        for (Users2 users2 : users2List) {
                            if (users2.getUserId().equalsIgnoreCase(ulrr.getUserId())) {
                                List<ApplicationEmailTemplates> appEmailTemplates = userSessions2DAO.getApplicationEmailTemplates(users2.getCountryId(), session);
                                for (ApplicationEmailTemplates apet : appEmailTemplates) {
                                    if (users2.getCountryId() == apet.getCountryId()) {
                                        String emailBody = "Hi " + users2.getFirstName() + " " + users2.getLastName() + ", " + apet.getEmailBody() + " " + ApplicationBaseURL + "" + ResetPasswordURL + "?" + "LoginResetRequestId=" + ulrr.getLoginResetRequestId() + "&LoginResetRequestCode=" + ulrr.getLoginResetRequestCode() + " " + apet.getEmailSignature();
                                        if (utilsResource.sendMail(users2.getId().getLoginName(), apet.getEmailSubject(), emailBody)) {
                                            userSessions2DAO.setIsEmailSent(ulrr, session);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            exceptionLog(e);
        } finally {
            if (session != null) {
                session.close();
            }
//            System.gc();
        }
    }

    private void removeAbondedAuthTokens() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            List<UserSessions2> userSessList = userSessions2DAO.getAllUserSessions2(session);
            if (userSessList != null) {
                GregorianCalendar gc = new GregorianCalendar();
                for (UserSessions2 session2 : userSessList) {
                    if (!session2.getActiveToDate().after(gc.getTime())) {
                        logger.info("Auth record deleted " + session2.getUserSessionId());
                        userSessions2DAO.removeUserSession2(session2, session);
                    }
                }
            }
        } catch (Exception e) {
            exceptionLog(e);
        } finally {
            if (session != null) {
                session.close();
            }
//            System.gc();
        }
    }

    private void sendMail(Users2 user, String mailType, String url) throws Exception {
        String body = "";

        MimeMessage message = new MimeMessage(TingcoConstants.getMailSession());

        if (mailType.equalsIgnoreCase("FP")) {
            message.setSubject(fpsubject);
            body = fpbody.replaceAll("[USERS FULL NAME]", user.getFirstName() + "" + user.getLastName()).replaceAll("[ActivationLinkID]", baseUrl + url).replaceAll("[APPLICATION SUPPORT EMAIL ADDRESS]", supportEmail);
        } else {
            message.setSubject(invitesubject + "");
            body = fpbody.replaceAll("[USERS FULL NAME]", user.getFirstName() + "" + user.getLastName()).replaceAll("[ActivationLinkID]", baseUrl + url).replaceAll("[APPLICATION SUPPORT EMAIL ADDRESS]", supportEmail);
        }


        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setContent(body, "text/html");//TODO need to set the body take care of appending the url...

        Multipart mp = new MimeMultipart();
        mp.addBodyPart(textPart);

        message.setContent(mp);
        message.setFrom(new InternetAddress("Info24"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getUserEmail()));

        Transport transport = TingcoConstants.getMailSession().getTransport();
        transport.connect(TingcoConstants.getEmailIp(), TingcoConstants.getEmailFrom(), TingcoConstants.getEmailPwd());

        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        transport.close();

    }

    

    private void deletePassResetReq(Session session) {

        Transaction tx = session.beginTransaction();
        try {
            List<UserLoginResetRequests> ulrr = userSessions2DAO.getAllUserLoginPassResetReqByDate(session);
            for (UserLoginResetRequests userLoginResetRequests : ulrr) {
                session.delete(userLoginResetRequests);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            exceptionLog(e);
        }
    }

    void exceptionLog(Exception e) {
        StringWriter stack = new StringWriter();
        e.printStackTrace(new PrintWriter(stack));
        logger.error(stack.toString());
    }
}

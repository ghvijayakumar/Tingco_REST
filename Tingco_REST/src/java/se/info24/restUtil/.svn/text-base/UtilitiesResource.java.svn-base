/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.restUtil;

import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.xml.datatype.DatatypeConfigurationException;
import org.hibernate.Session;
import progress.message.jimpl.TextMessage;
import se.info24.email.EMail;
import se.info24.jaxbUtil.TingcoUtilsXML;
import se.info24.user.UserDAO;
import se.info24.user.UserManager;
import se.info24.usersjaxb.TingcoUsers;
import se.info24.util.HibernateUtil;
import se.info24.util.JAXBManager;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;
import se.info24.util.TingcoConstants;
import se.info24.utiljaxb.TingcoUtils;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/utility")
public class UtilitiesResource {

    @Context
    private HttpServletRequest request;
    static Properties props = new Properties();
    static javax.mail.Session mailSession;
    TingcoUtilsXML tuXML = new TingcoUtilsXML();
    UserDAO userDAo = new UserDAO();

    /** Creates a new instance of UtilitiesResource */
    public UtilitiesResource() {
    }


    static {
        java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", TingcoConstants.getEmailIp());
        props.put("mail.smtp.port", TingcoConstants.getEmailPort());
        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.ssl.enable", "true");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.socketFactory.fallback", "false");
        mailSession = javax.mail.Session.getDefaultInstance(props, null);
    }

    /**
     * IsValidUserInviteLink
     * @param request
     * @param id
     * @return
     */
    @GET
    @Path("/isvalidinvitelink/rest/{rest}/id/{id}")
    @Produces("application/xml")
    @RESTDoc(methodName = "IsValidUserInviteLink", desc = "Check's Whether the User Invitation link is Valid or Not", req_Params = {"ID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_IsValidInviteLink(@Context HttpServletRequest request, @PathParam("id") String id) {
        return isValidInviteLink(id, request);
    }

    /**
     * IsValidUserInviteLink
     * @param request
     * @param id
     * @return
     */
    @GET
    @Path("/isvalidinvitelink/json/{json}/id/{id}")
    @Produces("application/json")
    public TingcoUsers getJson_IsValidInviteLink(@Context HttpServletRequest request, @PathParam("id") String id) {
        return isValidInviteLink(id, request);
    }

    /**
     * User_SendMail
     * @param request
     * @param userID
     * @param subject
     * @param body
     * @return
     */
    @GET
    @Path("/sendmail/rest/{rest}/userid/{userid}/subject/{subject}/body/{body}")
    @Produces("application/xml")
    public Response getXml(@Context HttpServletRequest request, @PathParam("userid") String userID, @PathParam("subject") String subject, @PathParam("body") String body) {
        throw new UnsupportedOperationException();
    }

    /**
     * User_SendMail
     * @param request
     * @param userID
     * @param subject
     * @param body
     * @return
     */
    @GET
    @Path("/sendmail/json/{json}/userid/{userid}/subject/{subject}/body/{body}")
    @Produces("application/json")
    public Response getJson(@Context HttpServletRequest request, @PathParam("userid") String userID, @PathParam("subject") String subject, @PathParam("body") String body) {
        throw new UnsupportedOperationException();
    }

    /**
     * User_SendMailTo
     * @param content
     * @param userID
     * @param action
     * @return
     */
    @POST
    @Path("/sendmail/rest/{rest}/userid/{userid}{action:(/action/[^/]+?)?}")
    @Consumes("application/xml")
    @Produces("application/xml")
    @RESTDoc(methodName = "User_SendMailTo", desc = "Used to send an e-mail to the specified user", req_Params = {"UserID;UUID"}, opt_Params = {"Action;String"}, method_formats = {"REST"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoUsers postXml(String content, @PathParam("userid") String userID, @PathParam("action") String action) {
        EMail mail = (EMail) JAXBManager.getInstance().unMarshall(content, EMail.class);
        return sendMail(mail.getTo(), mail.getSubject(), mail.getBody(), userID, action, request);
    }

    /**
     * User_SendMailTo
     * @param content
     * @param userID
     * @param action
     * @return
     */
    @POST
    @Path("/sendmail/json/{json}/userid/{userid}{action:(/action/[^/]+?)?}")
    @Consumes("application/xml")
    @Produces("application/json")
    public TingcoUsers postJson(String content, @PathParam("userid") String userID, @PathParam("action") String action) {
        EMail mail = (EMail) JAXBManager.getInstance().unMarshall(content, EMail.class);
        return sendMail(mail.getTo(), mail.getSubject(), mail.getBody(), userID, action, request);
    }

    /**
     * IsMailExists
     * @param domainID
     * @param email
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/ismailexists/rest/{rest}/domainid/{domainid}/email/{email}")
    @RESTDoc(methodName = "IsEmailExists", desc = "Check whether the email exists for the Concerned DomainID", req_Params = {"DomainID;UUID", "EMail;String"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml(@PathParam("domainid") String domainID, @PathParam("email") String email) {
        return checkEmailExists(domainID, email);
    }

    /**
     * IsMailExists
     * @param domainID
     * @param email
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/ismailexists/json/{json}/domainid/{domainid}/email/{email}")
    public TingcoUtils getJson(@PathParam("domainid") String domainID, @PathParam("email") String email) {
        return checkEmailExists(domainID, email);
    }

    /**
     * IsMailExists
     * @param domainID
     * @param email
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/ismailexists/rest/{rest}/domainid/{domainid}/email/{email}")
    public TingcoUtils postXml(@PathParam("domainid") String domainID, @PathParam("email") String email) {
        return checkEmailExists(domainID, email);
    }

    /**
     * IsMailExists
     * @param domainID
     * @param email
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/ismailexists/json/{json}/domainid/{domainid}/email/{email}")
    public TingcoUtils postJson(@PathParam("domainid") String domainID, @PathParam("email") String email) {
        return checkEmailExists(domainID, email);
    }

    /**
     * User_SendMailTo
     * @param content
     * @param userID
     * @param action
     * @return
     */
    @POST
    @Path("/senderrormail/rest/{rest}")
    @Consumes("application/xml")
    @Produces("application/xml")
    @RESTDoc(methodName = "User_SendMailTo", desc = "Used to send an e-mail to the specified user", req_Params = {"UserID;UUID"}, opt_Params = {"Action;String"}, method_formats = {"REST"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoUsers postXml(String content) throws MessagingException {
        EMail mail = (EMail) JAXBManager.getInstance().unMarshall(content, EMail.class);
        return sendErrorMail(mail.getTo(), mail.getCc(), mail.getSubject(), mail.getBody());
    }

    /**
     * User_SendMailTo
     * @param content
     * @param userID
     * @param action
     * @return
     */
    @POST
    @Path("/senderrormail/json/{json}")
    @Consumes("application/xml")
    @Produces("application/json")
    public TingcoUsers postJson(String content) throws MessagingException {
        EMail mail = (EMail) JAXBManager.getInstance().unMarshall(content, EMail.class);
        return sendErrorMail(mail.getTo(), mail.getCc(), mail.getSubject(), mail.getBody());
    }

    /**
     * User_SendMailTo
     * @param content
     * @param userID
     * @param action
     * @return
     */
    @GET
    @Path("/senderrorsmail/rest/{rest}/to/{to}/subject/{subject}/body/{body}{cc:(/cc/[^/]+?)?}")
    @Produces("application/xml")
    @RESTDoc(methodName = "User_SendMailTo", desc = "Used to send an e-mail to the specified user", req_Params = {"UserID;UUID"}, opt_Params = {"Action;String"}, method_formats = {"REST"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoUsers getXmlsTingcoUsers(@PathParam("to") String to, @PathParam("cc") String cc, @PathParam("subject") String subject, @PathParam("body") String body) throws MessagingException {

        return sendErrorsMail(to, cc, subject, body);
    }

    /**
     * User_SendMailTo
     * @param content
     * @param userID
     * @param action
     * @return
     */
    @GET
    @Path("/senderrorsmail/json/{json}/to/{to}/subject/{subject}/body/{body}{cc:(/cc/[^/]+?)?}")
    @Produces("application/json")
    @RESTDoc(methodName = "User_SendMailTo", desc = "Used to send an e-mail to the specified user", req_Params = {"UserID;UUID"}, opt_Params = {"Action;String"}, method_formats = {"REST"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoUsers getJsonTingcoUsers(@PathParam("to") String to, @PathParam("cc") String cc, @PathParam("subject") String subject, @PathParam("body") String body) throws MessagingException {

        return sendErrorsMail(to, cc, subject, body);
    }

    private TingcoUsers sendErrorsMail(String to, String cc, String subject, String body) {
        TingcoUsers user = null;
        UserManager manager = new UserManager();
        try {
            if (!cc.equals("")) {
                cc = cc.split("/")[2];
            } else {
                cc = null;
            }
            user = manager.buildUserTemplate();
            MimeMessage message = new MimeMessage(TingcoConstants.getMailSession());
            message.setSubject(TCMUtil.convertHexToString(subject));
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setHeader("Content-Type", "text/plain; charset=\"utf-8\"");
            textPart.setContent(TCMUtil.convertHexToString(body), "text/plain; charset=\"utf-8\"");
            textPart.setText("\n" + TCMUtil.convertHexToString(body));
            Multipart mp = new MimeMultipart();
            mp.addBodyPart(textPart);
            message.setContent(mp, "text/html");
            message.setFrom(new InternetAddress("Info24"));
            if (to.contains(",")) {
                String[] toArray = to.split(",");
                for (int i = 0; i < toArray.length; i++) {
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(toArray[i]));
                }
            } else {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            }
            if (cc != null) {
                if (cc.contains(",")) {
                    String[] toArray = cc.split(",");
                    for (int i = 0; i < toArray.length; i++) {
                        message.addRecipient(Message.RecipientType.CC, new InternetAddress(toArray[i]));
                    }
                } else {
                    message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
                }
            }
            Transport transport = TingcoConstants.getMailSession().getTransport();
            transport.connect(TingcoConstants.getEmailIp(), TingcoConstants.getEmailFrom(), TingcoConstants.getEmailPwd());
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            if (cc != null) {
                transport.sendMessage(message, message.getRecipients(Message.RecipientType.CC));
            }
            transport.close();
            return user;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            user.getMsgStatus().setResponseCode(-1);
            user.getMsgStatus().setResponseText("Error occured");
            return user;
        }
    }

    private TingcoUsers sendErrorMail(String to, String cc, String subject, String body) throws MessagingException {
        TingcoUsers user = null;
        UserManager manager = new UserManager();
        try {
            user = manager.buildUserTemplate();
            MimeMessage message = new MimeMessage(TingcoConstants.getMailSession());
            message.setSubject(subject);
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("\n" + body);
            Multipart mp = new MimeMultipart();
            mp.addBodyPart(textPart);

            message.setContent(mp);
            message.setFrom(new InternetAddress("Info24"));
            if (to.contains(",")) {
                String[] toArray = to.split(",");
                for (int i = 0; i < toArray.length; i++) {
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(toArray[i]));
                }
            } else {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            }
            if (cc != null) {
                if (cc.contains(",")) {
                    String[] toArray = cc.split(",");
                    for (int i = 0; i < toArray.length; i++) {
                        message.addRecipient(Message.RecipientType.CC, new InternetAddress(toArray[i]));
                    }
                } else {
                    message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
                }
            }


            Transport transport = TingcoConstants.getMailSession().getTransport();
            transport.connect(TingcoConstants.getEmailIp(), TingcoConstants.getEmailFrom(), TingcoConstants.getEmailPwd());

            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            if (cc != null) {
                transport.sendMessage(message, message.getRecipients(Message.RecipientType.CC));
            }
            transport.close();
            return user;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            user.getMsgStatus().setResponseCode(-1);
            user.getMsgStatus().setResponseText("Error occured");
            return user;
        }

    }

    private TingcoUtils checkEmailExists(String domainID, String email) {
        TingcoUtils tingcoUtils = null;
        Session session = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
            if (domainID == null) {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("DomainID should not be empty");
                return tingcoUtils;
            } else if (email == null) {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("Email should not be empty");
                return tingcoUtils;
            }

            session = HibernateUtil.getSessionFactory().openSession();
            tingcoUtils.setMailExists(userDAo.getUserByAppIdEmail(domainID, email, session));
            return tingcoUtils;
        } catch (DatatypeConfigurationException dce) {
            TCMUtil.exceptionLog(getClass().getName(), dce);
            tingcoUtils.getMsgStatus().setResponseCode(-1);
            tingcoUtils.getMsgStatus().setResponseText("Error occured");
            return tingcoUtils;
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public UtilityResource getUtilityResource() {
        return new UtilityResource();
    }

    private TingcoUsers isValidInviteLink(String id, HttpServletRequest request) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserManager manager = new UserManager();
        TingcoUsers user = null;
        try {
            user = manager.buildUserTemplate();
            user = manager.isValidInvitation(id, user, session);
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            session.close();
        }
        return user;
    }

    public TingcoUsers sendMail(String to, String subject, String body, String userID, String action, HttpServletRequest request) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserManager manager = new UserManager();
        TingcoUsers user = null;
        boolean sent = false;
        try {
            user = manager.buildUserTemplate();
            sent = manager.sendMail(to, subject, body, userID, action, session, mailSession);
            if (sent) {
                user.getMsgStatus().setResponseText("Mail Sent Successfully");
            } else {
                user.getMsgStatus().setResponseText("Error Occured in Sending Mail");
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            session.close();
        }
        return user;
    }

    public boolean sendMail(String to, String subject, String body) {
        UserManager manager = new UserManager();
        boolean mailSent = false;
        try {
            if (manager.sendMail(to, subject, body, mailSession)) {
                mailSent = true;
            } else {
                mailSent = false;
            }
        } catch (Exception e) {
            mailSent = false;
            TCMUtil.exceptionLog(getClass().getName(), e);
        }
        return mailSent;
    }
}

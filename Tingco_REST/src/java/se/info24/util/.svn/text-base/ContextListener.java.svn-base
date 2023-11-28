/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Sekhar
 */
public final class ContextListener implements ServletContextListener {

    UserSessions2Daemon userSessionDaemon = null;
    private ServletContext context = null;

    public void contextInitialized(ServletContextEvent event) {
        this.context = event.getServletContext();

        TingcoConstants.setEmailIp(context.getInitParameter("EmailServerIP"));
        TingcoConstants.setEmailFrom(context.getInitParameter("EmailFrom"));
        TingcoConstants.setEmailPwd(context.getInitParameter("EmailServerPassword"));
        TingcoConstants.setEmailPort(context.getInitParameter("EmailServerPort"));
        TingcoConstants.brokerURL = context.getInitParameter("Broker_URL");
        TingcoConstants.initialConnections = Integer.parseInt(context.getInitParameter("InitialConnections"));
        TingcoConstants.password = context.getInitParameter("Password");
        TingcoConstants.topicName = context.getInitParameter("TopicName");
        TingcoConstants.userName = context.getInitParameter("UserName");
        TingcoConstants.setServiceID(context.getInitParameter("ServiceID").toUpperCase());
        TingcoConstants.setcorsenabledURL(context.getInitParameter("CORSEnabledURL"));
        TingcoConstants.setEventTopic(context.getInitParameter("EventTopic"));
        TingcoConstants.setUserSessions2DaemonTimer(Integer.parseInt(context.getInitParameter("UserSessions2DaemonTimer")));
        TopicConnectionPool tcp = new TopicConnectionPool();
        TingcoConstants.setGenericpool(tcp.makePool());
        userSessionDaemon = new UserSessions2Daemon();
        userSessionDaemon.initiateDaemonTimer(Integer.parseInt(context.getInitParameter("UserSessions2DaemonTimer")));
        userSessionDaemon.initiateDeletePassResetReqTimer(Integer.parseInt(context.getInitParameter("DeletePassResetReqTimer")));
        userSessionDaemon.initiateCheckLoginResetRequestsTimer(Integer.parseInt(context.getInitParameter("CheckLoginResetRequestsTimer")));
//        userSessionDaemon.gcTimer(1);
    }

    public void contextDestroyed(ServletContextEvent event) {
        userSessionDaemon.shutdownDaemonTimer();
        this.context = null;
    }
}

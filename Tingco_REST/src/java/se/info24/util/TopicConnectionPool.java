/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;

/**
 *
 * @author Sekhar
 */
public class TopicConnectionPool {

    public GenericObjectPool makePool() {
        GenericObjectPool pool = new GenericObjectPool();
        pool.setFactory(new SampleTopicConnectionFactory());

        for (int i = 0; i < TingcoConstants.initialConnections; ++i) {
            try {
                pool.addObject();
            } catch (Exception ex) {
                Logger logger = Logger.getLogger(TopicConnectionPool.class);
                StringWriter stack = new StringWriter();
                ex.printStackTrace(new PrintWriter(stack));
                logger.error(stack.toString());
            }
        }
        pool.setMinEvictableIdleTimeMillis(1000 * 60 * 30);
        pool.setTimeBetweenEvictionRunsMillis(1000 * 60 * 60);

        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            Logger logger = Logger.getLogger(UserSessions2Daemon.class);
            StringWriter stack = new StringWriter();
            ex.printStackTrace(new PrintWriter(stack));
            logger.error(stack.toString());
        }
        return pool;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.log4j.Logger;

/**
 *
 * @author Sridhar
 */
public class SampleTopicConnectionFactory extends BasePoolableObjectFactory {

    ConnectionFactory factory;
    Connection connection = null;  
    private Logger logger = Logger.getLogger(SampleTopicConnectionFactory.class);
    public Object makeObject() {        
        try {
            factory = (new progress.message.jclient.ConnectionFactory(TingcoConstants.brokerURL));
            connection = factory.createConnection(TingcoConstants.userName, TingcoConstants.password);
            return connection;
        } catch (JMSException ex) {
            exceptionLog(ex);
            return null;
        }
    }

    @Override
    public boolean validateObject(Object obj) {
       
        if (obj instanceof Connection) {
            try {
                if (((Connection) obj).getClientID() == null) {
                    return true;
                }
            } catch (JMSException ex) {
                exceptionLog(ex);
                return false;
            }
        }
        return true;
    }

    @Override
    public void passivateObject(Object obj) throws Exception {
        
        if (obj instanceof Connection) {
            ((Connection)obj).setClientID(null);
        } else {
            throw new Exception("Unknown object");
        }
    }

    @Override
    public void destroyObject(Object obj) throws Exception {
        super.destroyObject(obj);
         if (obj instanceof Connection) {
            ((Connection)obj).close();
        }
    }
    void exceptionLog(Exception e) {
        StringWriter stack = new StringWriter();
        e.printStackTrace(new PrintWriter(stack));
        logger.error(stack.toString());
    }
}

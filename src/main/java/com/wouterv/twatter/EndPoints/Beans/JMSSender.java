package com.wouterv.twatter.EndPoints.Beans;

import com.google.gson.Gson;
import com.wouterv.twatter.Models.Tweet;
import org.glassfish.jersey.internal.util.Producer;
import org.json.simple.JSONObject;
import sun.security.krb5.internal.crypto.Des;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Wouter Vanmulken on 8-5-2017.
 */
public class JMSSender {
//    private static final Logger LOG = Logger.getLogger(JMSSender.class.getName());
//
//    /**
//     * the preconfigured GlassFish-default connection factory
//     */
//    private static final String JNDI_CONNECTION_FACTORY = "jms/connectionfactory";
//
//    private static final String JNDI_QUEUE = "jms/kwetter";
//
//    private static <T> T lookup(Class<T> retvalClass, String jndi) {
//        try {
//            return retvalClass.cast(InitialContext.doLookup(jndi));
//        } catch (NamingException ex) {
//            throw new IllegalArgumentException("failed to lookup instance of " + retvalClass + " at " + jndi, ex);
//        }
//    }
//
//    public void TestJMS(Tweet tweet) {
////        final CountDownLatch countDownLatch = new CountDownLatch(1);
//        final ConnectionFactory connectionFactory = lookup(QueueConnectionFactory.class, JNDI_CONNECTION_FACTORY);
//        final Queue queue = lookup(Queue.class, JNDI_QUEUE);
//        Gson gson = new Gson();
//        //JMSContext implements AutoClosable: let us try 'try-with-resources'
//        //see http://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
//        try (JMSContext jmsContext = connectionFactory.createContext()) {
//            final JMSProducer producer = jmsContext.createProducer();
//            final String text = gson.toJson(tweet);
////            TextMessage message = new
//
//            producer.send(queue, text);
//            LOG.log(Level.INFO, "sent {0} to {1}", new Object[]{text, JNDI_QUEUE});
//        }//jmsContext is autoclosed
//    }

    public void send(String messageString){
        try {
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
            props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
            //props.put("queue", "queue.kwetter");

            Context jndiContext = new InitialContext(props);
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("jms/__defaultConnectionFactory");
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = (Destination) jndiContext.lookup("queue.kwetter");
            MessageProducer producer = session.createProducer(destination);
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText(messageString);

            producer.send(textMessage);
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
    }
}

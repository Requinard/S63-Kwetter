package com.wouterv.twatter.Utils;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.Properties;

/**
 * Created by Wouter Vanmulken on 3-5-2017.
 */
public class JMSMessenger {
    Connection connection;
    Session session;
    Destination destination;
    MessageProducer producer;
    String queueName;

    public JMSMessenger(String queueName) {
        this.queueName = queueName;
        try {
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");

            // connect to the Destination called “myFirstChannel”
            // queue or topic: “queue.myFirstDestination” or “topic.myFirstDestination”
            props.put("queue." + queueName, queueName);

            Context jndiContext = new InitialContext(props);
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext
                    .lookup("ConnectionFactory");
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // connect to the sender destination
            destination = (Destination) jndiContext.lookup(queueName);
            producer = session.createProducer(destination);
        } catch (JMSException exception) {
            exception.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

    public void Send(Serializable message, String correlationId) {
        ObjectMessage msg = null;
        try {
            msg = session.createObjectMessage(message);
            msg.setJMSCorrelationID(correlationId);
            // send the message
            producer.send(msg);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}

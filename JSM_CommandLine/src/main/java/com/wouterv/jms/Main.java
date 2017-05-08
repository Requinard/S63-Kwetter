package com.wouterv.jms;

import javax.annotation.Resource;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
import javax.naming.*;
import java.io.*;

public class Main {



    @Resource(mappedName = "jms/kwetter")
    private static ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/kwetterQueue")
    private static Queue queue;

    public void produceMessages()
    {
        MessageProducer messageProducer;
        TextMessage textMessage;
        try
        {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(queue);
            textMessage = session.createTextMessage();

            textMessage.setText("1,Hello world");
            System.out.println("Sending the following message: "
                    + textMessage.getText());
            messageProducer.send(textMessage);

            messageProducer.close();
            session.close();
            connection.close();
        }
        catch (JMSException e)
        {
            e.printStackTrace();
        }
    }










    public static void main(String[] args) {
//        new Main().produceMessages();
        try {

            // Start connection
            ConnectionFactory cf = new com.sun.messaging.ConnectionFactory();

            Connection connection = cf.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            session.createQueue("jms/kwetter");

            Destination destination = session.createQueue("kwetterQueue");
            MessageProducer producer = session.createProducer(destination);
            connection.start();

            // create message to send
            TextMessage message = session.createTextMessage();
            message.setText("1,Hello world from client app");

            System.out.println("Send from HelloProducer.java");
            producer.send(message);

            // close everything
            producer.close();
            session.close();
            connection.close();

        } catch (JMSException ex) {
            System.out.println("Error = " + ex.getMessage());
        }












//
//
//
//
//
//
//
//
//
//
//
//        try {
//            String addressList = "http://127.0.0.1:8080/imqhttp/tunnel";
//            TopicConnectionFactory topicConnectionFactory = new com.sun.messaging.TopicConnectionFactory();
//            topicConnectionFactory.setProperty(com.sun.messaging.ConnectionConfiguration.imqAddressList, addressList);
//            javax.jms.Topic top;

//            javax.jms.Connection con = topicConnectionFactory.createTopicConnection("admin", "admin");
//
//            javax.jms.Session session = con.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
//            top = session.createTopic("DIRECT_TOPIC");
//            MessageProducer prod = session.createProducer(top);
//            Message textMessage = session.createTextMessage(messageContent);
//            prod.send(textMessage);
//
//            prod.close();
//            session.close();
//            con.close();
//
//        } catch (JMSException ex) {
//            ex.printStackTrace();
//        }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////        com.sun.messaging.ConnectionFactory connFactory = new com.sun.messaging.ConnectionFactory();
////        connFactory.setProperty(ConnectionConfiguration.imqAddressList, "10.241.5.51:7676");
////        com.sun.messaging.Queue queue = new com.sun.messaging.Queue("jms/kwetter");//(com.sun.messaging.Queue)client.lookup("jms/tQueue");
////        try (Connection connection = connFactory.createConnection();
////             Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
////             MessageProducer producer = session.createProducer(queue)) {
////            for (int i = 0; i < 5; i++) {
////                TextMessage message = session.createTextMessage("It is a message from main class " + ": " + i);
////                System.out.println("It come from main class:" + message.getText());
////                producer.send(message);
////            }
////        }
//
//
//        Connection connection; // to connect to the JMS
//        Session session; // session for creating consumers
//
//        Destination receiveDestination; //reference to a queue destination
//        MessageConsumer consumer = null; // for receiving messages
//
//        try {
//            Properties props = new Properties();
////            props.setProperty(Context.INITIAL_CONTEXT_FACTORY,
////                    "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
//            props.setProperty(Context.PROVIDER_URL, "tcp://localhost:7676");
//
//            // connect to the Destination called “loanReplyQueue”
//            // queue or topic: “queue.loanReplyQueue”
//            props.put(("queue.kwetter"), " kwetter");
//
//            Context jndiContext = new InitialContext(props);
//            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext
//                    .lookup("ConnectionFactory");
//            connection = connectionFactory.createConnection();
//            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//
//            // connect to the receiver destination
//            receiveDestination = (Destination) jndiContext.lookup("jms/kwetter");
//            consumer = session.createConsumer(receiveDestination);
//
//            connection.start(); // this is needed to start receiving messages
//
//        } catch (NamingException | JMSException e) {
//            e.printStackTrace();
//        }
    }
}


//class Publisher {
//    private TopicConnectionFactory tCF;
//    private TopicConnection tCon;
//    private TopicSession pubSession;
//    private String topicName = "jms/kwetter";
//    private Topic top;
//    private TopicPublisher tPub;
//
//    // Constructor to initialise the required entities
//    public Publisher(String uname, String pwd)
//            throws NamingException, JMSException {
//
//        InitialContext ctx = new InitialContext();
//
//        // Step1: Lookup the Connection Factory and the Topic
//        tCF = (TopicConnectionFactory)
////                ctx.lookup("jms/MyConnectionFactory");
//                ctx.lookup("jms/kwetter");
//        top = (Topic) ctx.lookup(topicName);
//
//        // Step2: Create a connection using the Factory
//        tCon = tCF.createTopicConnection(uname, pwd);
//
//        // Step3: Create Topic Sessions using the connection
//        pubSession = tCon.createTopicSession
//                (false, Session.AUTO_ACKNOWLEDGE);
//
//        // Step4: Create TopicPublisher
//        tPub = pubSession.createPublisher(top);
//
//        tCon.start();
//    }   //  End of Constructor
//
//    // Method to close the connection to Topic
//    public void close() throws JMSException {
//        tCon.stop();
//    }
//
//    // Method to publish the message to the Topic
//    public void writeMsg(String msg) throws JMSException {
//        //  Creating a Text Message with the String object
//        TextMessage txtMsg = pubSession.createTextMessage(msg);
//
//        //  Publishing the message object to the Topic
//        tPub.publish(txtMsg);
//    }
//
//    public static void main(String[] args)
//            throws NamingException, IOException, JMSException {
//        String msg, uname = null, pwd = null;
//        Publisher pub;
//
//      /*
//       *   Extracting the credentials for establishing a
//       *   connection to the topic through command line arguments
//       */
//        if (args.length == 2) {
//            uname = args[0];
//            pwd = args[1];
//        } else {
//            System.out.println("Not Enough parameters");
//            System.exit(0);
//        }
//
//        //  Calling the constructor with the credentials
//        pub = new Publisher(uname, pwd);
//
//        //  Declaring a Reader for reading the message from user
//        BufferedReader br = new BufferedReader
//                (new InputStreamReader(System.in));
//
//        //  Read input to be published till the users enters 'exit'
//        while (true) {
//            msg = br.readLine();
//            if (msg.equalsIgnoreCase("exit")) {
//                pub.writeMsg(msg);
//                pub.close();
//                System.exit(0);
//            } else {
//                pub.writeMsg(msg);
//            }
//        }  //  End of while loop
//
//    }  //  End of main()
//
//}
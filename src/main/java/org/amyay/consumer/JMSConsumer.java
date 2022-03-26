package org.amyay.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSConsumer implements MessageListener {

    public void setUpConsumer() throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL("tcp://localhost:61617");
        factory.setUserName("admin");
        factory.setPassword("admin");

        try {
            Connection connection = factory.createQueueConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("MyQueue");
            MessageConsumer consumer = session.createConsumer(queue);
            consumer.setMessageListener(this);
            /*Message message = consumer.receive(1000);
            if(message instanceof TextMessage) {
                try {
                    System.out.println("Message Received: "+((TextMessage) message).getText());
                } catch (JMSException e) {
                    System.out.println("Error While Consuming Message");
                }
            }*/
        } catch (JMSException ex) {
            System.out.println("Error While Publishing Message: "+ ex.getMessage());
            throw ex;
        }
    }

    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage) {
            try {
                System.out.println("Message Received: "+((TextMessage) message).getText());
            } catch (JMSException e) {
                System.out.println("Error While Consuming Message");
            }
        }
    }
}
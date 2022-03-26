package org.amyay.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSProducer {

    public void producer() throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL("tcp://localhost:61617");
        factory.setUserName("admin");
        factory.setPassword("admin");

        try {
            QueueConnection connection = factory.createQueueConnection();
            connection.start();
            QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("MyQueue");
            MessageProducer producer = session.createProducer(queue);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            Message message = session.createTextMessage("Hello World");
            producer.send(message);
            System.out.println("Message Published");
        } catch (JMSException ex) {
            System.out.println("Error While Publishing Message: "+ ex.getMessage());
            throw ex;
        }
    }
}
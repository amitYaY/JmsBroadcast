package org.amyay;

import org.amyay.consumer.JMSConsumer;
import org.amyay.producer.JMSProducer;

import javax.jms.JMSException;

public class TestClass {

    public static void main(String[] args) throws JMSException {
        JMSProducer producer = new JMSProducer();
        producer.producer();

        JMSConsumer consumer = new JMSConsumer();
        consumer.setUpConsumer();
    }

}

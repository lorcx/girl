package com.girl.activeMQ;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @JmsListener(destination = "queue-test")
    public void receiveQueue(String text) {
        System.out.println("这是消费者：");
        System.out.println(text);
    }

}
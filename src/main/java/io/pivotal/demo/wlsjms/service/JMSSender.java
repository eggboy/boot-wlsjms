package io.pivotal.demo.wlsjms.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.jms.*;

@Service
public class JMSSender {

    QueueSession queueSession;
    QueueSender queueSender;

    public JMSSender(QueueSession queueSession, QueueSender queueSender) {
        this.queueSession = queueSession;
        this.queueSender = queueSender;
    }

    public void sendMessage(String message) {
        try {
            TextMessage textMessage = queueSession.createTextMessage();
            textMessage.setText(message);

            queueSender.send(textMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

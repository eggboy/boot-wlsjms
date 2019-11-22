package io.pivotal.demo.wlsjms.controller;

import io.pivotal.demo.wlsjms.service.JMSSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    JMSSender jmsSender;

    public MessageController(JMSSender jmsSender) {
        this.jmsSender = jmsSender;
    }

    @PostMapping("/message")
    public void sendMessageToJMS(@RequestBody String message) {
        jmsSender.sendMessage(message);
    }
}

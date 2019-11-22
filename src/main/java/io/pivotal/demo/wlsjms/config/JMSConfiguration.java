package io.pivotal.demo.wlsjms.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

@Configuration
@ConfigurationProperties(prefix = "wls")
@Getter
@Setter
public class JMSConfiguration {
    private String INITIAL_CONTEXT_FACTORY;
    private String PROVIDER_URL;
    private String SECURITY_PRINCIPAL;
    private String SECURITY_CREDENTIALS;
    private String JMS_FACTORY;
    private String QUEUE;

    @Bean
    public InitialContext getInitialContext() {
        Hashtable<String, String> h = new Hashtable<String, String>();
        h.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
        h.put(Context.PROVIDER_URL, PROVIDER_URL);
        h.put(Context.SECURITY_PRINCIPAL, SECURITY_PRINCIPAL);
        h.put(Context.SECURITY_CREDENTIALS, SECURITY_CREDENTIALS);

        InitialContext ctx = null;

        try {
            ctx = new InitialContext(h);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return ctx;
    }

    @Bean
    public QueueSession getQueueSession() throws NamingException, JMSException {
        InitialContext ctx = getInitialContext();
        QueueConnectionFactory qconFactory = (QueueConnectionFactory) ctx.lookup(JMS_FACTORY);
        QueueConnection qcon = qconFactory.createQueueConnection();
        qcon.start();

        return qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    @Bean
    public QueueSender getQueueSender() throws JMSException, NamingException {
        InitialContext ctx = getInitialContext();
        Queue queue = (Queue) ctx.lookup(QUEUE);

        return getQueueSession().createSender(queue);
    }

}

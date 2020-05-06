package com.tsystems.javaschool.onlinestore.domain.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.javaschool.onlinestore.domain.product.Product;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import javax.jms.*;
import java.util.List;

/**
 * Class represents logic to send messages to JMS broker
 */
@Component
public class MessageSender {

    private static final Logger logger= Logger.getLogger(MessageSender.class);

    /**
     * Injected JMS template
     */
    private JmsTemplate jmsTemplate;

    @Autowired
    public MessageSender(JmsTemplate jmsTemplate){
        this.jmsTemplate=jmsTemplate;
    }

    /**
     * Method creates JSON-message from product list and sends message to broker
     * @param productList
     */
    public void sendMessage(final List<Product> productList) {

        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMapper mapper = new ObjectMapper();
                String jsonList=null;
                try {
                    jsonList=mapper.writeValueAsString(productList);
                } catch (JsonProcessingException e) {
                    logger.debug("Error while parsing product list to JSON: "+ e.getStackTrace());
                }
                return session.createTextMessage(jsonList);
            }
        });
    }
}

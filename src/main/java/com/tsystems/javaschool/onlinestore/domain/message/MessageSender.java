package com.tsystems.javaschool.onlinestore.domain.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.internal.logging.Logger;
import com.tsystems.javaschool.onlinestore.domain.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import javax.jms.*;
import java.util.List;

@Component
public class MessageSender {

    private static final Logger logger= Logger.getLogger(MessageSender.class);

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(final List<Product> productList) {

        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMapper mapper = new ObjectMapper();
                String jsonList=null;
                try {
                    jsonList=mapper.writeValueAsString(productList);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                TextMessage message=session.createTextMessage(jsonList);
                return message;
            }
        });
    }
}

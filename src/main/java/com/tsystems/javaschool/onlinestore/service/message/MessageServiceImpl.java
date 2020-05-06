package com.tsystems.javaschool.onlinestore.service.message;

import com.tsystems.javaschool.onlinestore.domain.message.MessageSender;
import com.tsystems.javaschool.onlinestore.service.sales.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements  MessageService{

    /**
     * Injected message sender and sales service
     */
    private MessageSender messageSender;

    private SalesService salesService;

    @Autowired
    public MessageServiceImpl(MessageSender messageSender, SalesService salesService){
        this.messageSender=messageSender;
        this.salesService=salesService;
    }


    /**
     * Method sends message to JMS broker
     */
    @Override
    public void sendMessage() {
        messageSender.sendMessage(salesService.getTopProductsList());
    }
}

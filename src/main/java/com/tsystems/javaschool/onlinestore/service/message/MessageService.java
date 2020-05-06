package com.tsystems.javaschool.onlinestore.service.message;

/**
 * Interface provides methods to work with messages
 */
public interface MessageService {

    /**
     * Method sends message to JMS broker
     */
    void sendMessage();
}

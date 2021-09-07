package com.jss.camel.camelrestdslsaggu.service;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class Player {
    Logger logger = Logger.getLogger(this.getClass().getName());
    public void play(Exchange e) {
        Message message = e.getMessage();
        message.setBody(message.getBody() + " Play_");
        logger.info("\n---------------------------------------------------------------------------------------------------------------------------------------\n" +
                "Play service\n" +
                "Exchange route id :"+e.getFromRouteId());
                e.setMessage(message);


    }

    public String ret() {
         return "!!!!!!!!!!!!";
    }

    public String ret2() {
        return "???";
    }
}

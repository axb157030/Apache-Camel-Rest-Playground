package com.jss.camel.camelrestdslsaggu.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.support.DefaultMessage;
import org.springframework.stereotype.Component;

@Component
public class PingProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Message message = exchange.getMessage();
        message.setBody(exchange.getMessage().getBody() + " " + exchange.getProperty("k1") +  " Ping");
        exchange.setMessage(message);
    }
}

package com.jss.camel.camelrestdslsaggu.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.support.DefaultMessage;
import org.springframework.stereotype.Component;

@Component
public class HelloProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Message message = new DefaultMessage(exchange.getContext());
        message.setBody("Hello");
        exchange.setProperty("k1", "Key");
        exchange.setMessage(message);
    }
}

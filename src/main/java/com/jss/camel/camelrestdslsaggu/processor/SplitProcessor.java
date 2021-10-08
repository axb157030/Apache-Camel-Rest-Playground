package com.jss.camel.camelrestdslsaggu.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class SplitProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        final Integer ONE = 1;
        if((exchange.getMessage().getBody() != null) || exchange.getMessage().getBody().toString().isEmpty()) {
            exchange.getMessage().setBody(0);
        }
        exchange.getMessage().setBody(Integer.parseInt(exchange.getMessage().getBody().toString()) + ONE);
        System.out.println("Split Processor message: " + exchange.getMessage().getBody());
    }
}

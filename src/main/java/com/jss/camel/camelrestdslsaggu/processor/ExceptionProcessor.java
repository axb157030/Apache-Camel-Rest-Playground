package com.jss.camel.camelrestdslsaggu.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class ExceptionProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        throw new Exception("Some exception test msg");
    }
}
package com.jss.camel.camelrestdslsaggu.strategy;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class ExampleAggregationStrategy implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if(oldExchange == null) {
            return newExchange;
        }

        Integer old = oldExchange.getIn().getBody(Integer.class);
        Integer new_ = newExchange.getIn().getBody(Integer.class);
        newExchange.getIn().setBody(old + new_);
        return newExchange;
    }
}

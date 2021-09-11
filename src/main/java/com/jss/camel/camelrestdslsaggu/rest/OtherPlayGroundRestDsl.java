package com.jss.camel.camelrestdslsaggu.rest;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class OtherPlayGroundRestDsl extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        // Rest Clauses
        from("rest:get:helloworld?produces=application/json")
                .to("direct:helloworld");

        from("rest:get:nestCondition?produces=application/json")
                .to("direct:nestCondition");
    }
}

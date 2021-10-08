package com.jss.camel.camelrestdslsaggu.rest;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class OtherPlayGroundRestDsl extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        final String TESTVAL = "P";

        // Rest Clauses
        from("rest:get:otherExample?produces=application/json")
                .setProperty("test", constant(TESTVAL))
                .choice()
                        .when(simple("${exchangeProperty.test} == 'A'"))
                                .to("direct:helloworld")
                        .when(simple("${exchangeProperty.test} == 'B'"))
                        .to("direct:helloExample")
                .otherwise()
                .to("direct:ping")
        .choice()
                .when(simple("${exchangeProperty.test} == 'P'"))
                .to("direct:ping");



        from("rest:get:helloworld?produces=application/json")
                .to("direct:helloworld");
        from("rest:get:helloEx?produces=application/json")
                .to("direct:helloExample");

        from("rest:get:nestCondition?produces=application/json")
                .to("direct:nestCondition");


    }
}

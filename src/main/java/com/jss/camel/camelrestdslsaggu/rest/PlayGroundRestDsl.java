package com.jss.camel.camelrestdslsaggu.rest;

import com.jss.camel.camelrestdslsaggu.processor.ExceptionProcessor;
import com.jss.camel.camelrestdslsaggu.processor.HelloProcessor;
import com.jss.camel.camelrestdslsaggu.processor.PingProcessor;
import com.jss.camel.camelrestdslsaggu.processor.RandomNumProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.support.DefaultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayGroundRestDsl extends RouteBuilder {

    @Autowired
    private HelloProcessor helloProcessor;

    @Autowired
    private PingProcessor pingProcessor;

    @Autowired
    private RandomNumProcessor randomNumProcessor;

    @Autowired
    private ExceptionProcessor exceptionProcessor;

    @Override
    public void configure() throws Exception {

        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.auto);

        onException(Exception.class).to("bean:exceptionHandler?method=handleCustomException"); // Must be above all from() can be after rest()

        // Rest Clauses
        from("rest:get:helloworld?produces=application/json")
                .to("direct:helloworld");


        rest().get("/hello")
                .to("direct:hello");


        rest().get("/hello2ToTest") // if rest() calls several to routes after another result will be exchange body from last to route
                .to("direct:hello")
                .to("direct:ping");


       rest().get("fileTest").to("direct:fileStart"); // Rest has a route that has several to routes. The exchanges carry on between the to routes with the result being rhe message from the last to route. When referring to to route, I mean to("direct:<routeName>"). Seems to make sense with having 1 from containing path and up to several to() containing that path

        rest().get("/helloCondition")
                .to("direct:seRandNum");

        rest().get("/testRetFunction")
                .to("direct:testReturnFunction");


        // Routes
        from("direct:helloworld")
                .log(LoggingLevel.INFO, "Hello World")
                .transform().simple("Hello World");

        from("direct:hello")
                .process(helloProcessor)
                .log(LoggingLevel.INFO, "Hello");

        from("direct:ping").process(pingProcessor);


        from("direct:fileStart") // The exchanges carry on between the to routes. /fileStart does not exist. If url path used in to clause, it seems to not be able to be used in a from() apache camel function again
                .to("direct:hello")
                .to("direct:ping")
                .to("file:output?fileName=apacheCamelSample")
                        .process(pingProcessor)//.process(exceptionProcessor); //// Processor modify message body to display to user
        .to("bean:player?method=play")
        .to("bean:player?method=play");
                //.to("bean:exceptionHandler?method=handleCustomException");
                // process and on exception.

        from("direct:testReturnFunction")
                .to("direct:hello")
                .to("direct:ping")
                .to("file:output?fileName=apacheCamelSample")
                .process(pingProcessor)
                .to("bean:player?method=play")
                .to("bean:player?method=ret")
                .to("bean:player?method=ret2"); // String returned by last method of route is returned

        from("direct:seRandNum")
                .process(randomNumProcessor)
                        .choice().when(exchange -> {return exchange.getProperty("randNum").toString().length() > 1;})
                        .to("direct:addRandNum").otherwise().to("direct:subtractRandNum");
        from("direct:addRandNum").process(this::addRandNum);
        from("direct:subtractRandNum").process(this::subtractRandNum);


        // .responseMessage().code(200).message("Ping").endResponseMessage();


    }

    // Changes to exchange in this (Exchange exchange) method will not be saved
    private void ping(Exchange exchange) {

        Message message = new DefaultMessage(exchange.getContext());
        message.setBody(exchange.getMessage().getBody() + " " + exchange.getProperty("k1") +  " Ping");
        exchange.setMessage(message);
    }

    // Changes to exchange in this hello(Exchange exchange) method will not be saved
    private void hello(Exchange exchange) {

        Message message = new DefaultMessage(exchange.getContext());
        message.setBody("Hello there");
        exchange.setProperty("k1", "Key");
        exchange.setMessage(message);
    }

    private void addRandNum(Exchange exchange) {
        Double randNum = Double.parseDouble(exchange.getProperty("randNum").toString()) + 21;
        exchange.getIn().setBody(exchange.getIn().getBody() + " Add random number " + randNum + "");
    }

    private void subtractRandNum(Exchange exchange) {
        Double randNum = Double.parseDouble(exchange.getProperty("randNum").toString()) - 2;
        exchange.getIn().setBody(exchange.getIn().getBody() + " Subtract random number " + randNum + "");
    }

}

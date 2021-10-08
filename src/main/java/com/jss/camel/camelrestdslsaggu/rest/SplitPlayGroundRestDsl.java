package com.jss.camel.camelrestdslsaggu.rest;

import com.jss.camel.camelrestdslsaggu.processor.SplitProcessor;
import com.jss.camel.camelrestdslsaggu.strategy.ExampleAggregationStrategy;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class SplitPlayGroundRestDsl extends RouteBuilder {

    @Autowired
    SplitProcessor splitProcessor;

    @Autowired
    ExampleAggregationStrategy exampleAggregationStrategy;

    @Override
    public void configure() throws Exception {
        List<Integer> numList = IntStream.range(0, 5).boxed().collect(Collectors.toList());
        rest().get("splitRoute").to("direct:splitTest");


        from("direct:splitTest").split(constant(numList)).parallelProcessing().process(splitProcessor);//.aggregate(constant(true), exampleAggregationStrategy).completionSize(numList.size());
    }
}

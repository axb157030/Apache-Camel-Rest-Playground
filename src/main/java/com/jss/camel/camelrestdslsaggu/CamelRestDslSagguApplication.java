package com.jss.camel.camelrestdslsaggu;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jss"})
public class CamelRestDslSagguApplication {

	@Autowired
	ProducerTemplate template;

	public static void main(String[] args) {
		SpringApplication.run(CamelRestDslSagguApplication.class, args);
	}

}

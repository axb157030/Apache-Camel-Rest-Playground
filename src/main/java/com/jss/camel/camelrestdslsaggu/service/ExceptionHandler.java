package com.jss.camel.camelrestdslsaggu.service;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangeException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ExceptionHandler { // This class form https://github.com/techguy-bhushan/Springboot-ApacheCamel-Rest/blob/master/src/main/java/com/src/service/CamelRetryExceptionHandlerServiceImpl.java
    Logger logger = Logger.getLogger(this.getClass().getName());
    public void handleCustomException(Exchange e, @ExchangeException Exception causedBy) {
        logger.info("\n---------------------------------------------------------------------------------------------------------------------------------------\n" +
                "threshold has been reached\n" +
                "Exchange route id :"+e.getFromRouteId() +
                "\ncaused:"+causedBy.getMessage()+
                "\n---------------------------------------------------------------------------------------------------------------------------------------\n"

        );
    }
}

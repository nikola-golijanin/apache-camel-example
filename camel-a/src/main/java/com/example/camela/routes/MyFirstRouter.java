package com.example.camela.routes;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyFirstRouter extends RouteBuilder {

    private final GetCurrentTimeBean getCurrentTimeBean;
    private final SimpleLoggingProcessingComponent simpleLoggingProcessingComponent;

    public MyFirstRouter(GetCurrentTimeBean getCurrentTimeBean,
                         SimpleLoggingProcessingComponent simpleLoggingProcessingComponent) {
        this.getCurrentTimeBean = getCurrentTimeBean;
        this.simpleLoggingProcessingComponent = simpleLoggingProcessingComponent;
    }

    @Override
    public void configure() throws Exception {
        //timer
        //transformation
        //log
        //Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
        from("timer:first-timer") // it will print null
                //.transform().constant("My constant message")   it will print My Constant Message
                //.transform().constant("My local date time: " + LocalDateTime.now())   it will print My local date time is some date

                //Processing does something with data but does not change message body
                //Transformation changes body of the message
                .bean(getCurrentTimeBean, "getCurrentTime")
                .bean(simpleLoggingProcessingComponent)
                .to("log:first-timer");
    }
}


@Component
class GetCurrentTimeBean {
    public String getCurrentTime() {
        return "Time is: " + LocalDateTime.now();
    }

    public String getCurrentTime2() {
        return "Time is: " + LocalDateTime.now();
    }
}

@Component
class SimpleLoggingProcessingComponent {
    private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);

    public void process(String message) {
        logger.info("Simple message {}",message);
    }

}

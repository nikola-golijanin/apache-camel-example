package com.example.camela.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyFirstRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        //timer
        //transformation
        //log
        //Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
        from("timer:first-timer") // null
                //.transform().constant("My constant message")
                .transform().constant("My local date time: " + LocalDateTime.now())
                .to("log:first-timer");
    }
}

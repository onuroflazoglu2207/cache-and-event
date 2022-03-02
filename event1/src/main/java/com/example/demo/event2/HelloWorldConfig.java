package com.example.demo.event2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfig {

    @Bean
    public HelloWorld helloWorld() {
        return new HelloWorld("Hello Beauty World!");
    }

    @Bean
    public StartEventHandler startEventHandler() {
        return new StartEventHandler();
    }

    @Bean
    public StopEventHandler stopEventHandler() {
        return new StopEventHandler();
    }

}
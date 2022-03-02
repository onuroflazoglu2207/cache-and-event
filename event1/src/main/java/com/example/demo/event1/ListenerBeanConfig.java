package com.example.demo.event1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenerBeanConfig {

    @Bean
    public ListenerBean listenerBean() {
        return new ListenerBean();
    }
}

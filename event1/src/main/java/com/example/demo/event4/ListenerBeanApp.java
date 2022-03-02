package com.example.demo.event4;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ListenerBeanApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(ListenerBeanConfig.class);

        ctx.publishEvent(HelloWorld.builder().text("hello world1!").build());

        // this will not appear in console
        // cause u r set condition in ListenerBean class
        ctx.publishEvent(HelloWorld.builder().text("hello world2!").build());

    }
}
package com.example.demo.event3;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ListenerBeanApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(ListenerBeanConfig.class);
        ctx.start();
        ListenerBean bean = ctx.getBean(ListenerBean.class);
        ctx.stop();
        ctx.close();
    }
}
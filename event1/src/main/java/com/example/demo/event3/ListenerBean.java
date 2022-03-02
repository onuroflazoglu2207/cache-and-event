package com.example.demo.event3;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class ListenerBean implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("context event received: " + event);
    }

}

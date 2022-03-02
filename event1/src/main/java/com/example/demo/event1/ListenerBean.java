package com.example.demo.event1;

import org.springframework.context.event.*;

public class ListenerBean {

    @EventListener
    public void handleContextRefreshed(ContextRefreshedEvent event) {
        System.out.println("context refreshed event received: " + event);
    }

    @EventListener
    public void handleContextStopped(ContextStoppedEvent event) {
        System.out.println("context stopped event received: " + event);
    }

    @EventListener
    public void handleContextStarted(ContextStartedEvent event) {
        System.out.println("context started event received: " + event);
    }

    @EventListener
    public void handleContextClosed(ContextClosedEvent event) {
        System.out.println("context closed event received: " + event);
    }

    //@EventListener(classes = {ContextStoppedEvent.class, ContextClosedEvent.class})
    //public void handleContextStoppedClosed() {
    //    ...
    //}
}

package com.example.demo;

import org.springframework.context.event.EventListener;

public class ListenerBean {

    private static HelloWorld world = null;

    @EventListener
    public void handleContext(HelloWorld event) {
        world = event;
        System.out.println("context event received: " + event);
    }

    public static HelloWorld getWorld() {
        return world;
    }
}

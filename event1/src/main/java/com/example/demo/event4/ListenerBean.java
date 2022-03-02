package com.example.demo.event4;

import org.springframework.context.event.*;

public class ListenerBean {

    @EventListener(condition = "#event.text.equals('hello world1!')")
    public void handleContextStarted(HelloWorld event) {
        System.out.println("context started event received: " + event);
    }

}

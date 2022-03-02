package com.example.demo;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListenerController implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    @ResponseBody
    @GetMapping("/pub/{str}")
    public HelloWorld pub(@PathVariable String str) {
        HelloWorld helloWorld = HelloWorld.builder().text(str).build();
        publisher.publishEvent(helloWorld);
        return helloWorld;
    }

    @ResponseBody
    @GetMapping("/get")
    public ResponseEntity<?> get() {
        if (ListenerBean.getWorld() != null) return new ResponseEntity<>(ListenerBean.getWorld(), HttpStatus.OK);
        else return new ResponseEntity<>("Not HelloWorld Object created!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
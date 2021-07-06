package com.joon.springframeworkcleanup.ioc.envetpublisher;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

//@Component
public class EventHandler {
    @EventListener
    // @Async 비동기 처리 비동기 처리 시 @EnableAsync를 추가해주어야한다
    public void getEvent(MyEvent myEvent){
        System.out.println(myEvent.getMessage());
    }
}

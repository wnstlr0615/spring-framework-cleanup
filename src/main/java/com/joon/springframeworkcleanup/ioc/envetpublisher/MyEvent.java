package com.joon.springframeworkcleanup.ioc.envetpublisher;

import org.springframework.context.ApplicationEvent;

public class MyEvent {
    String message;
    int data;
    public MyEvent(String msg, int data) {
        message=msg;
        this.data=data;
    }

    public String getMessage() {
        return message;
    }

    public int getData() {
        return data;
    }
}

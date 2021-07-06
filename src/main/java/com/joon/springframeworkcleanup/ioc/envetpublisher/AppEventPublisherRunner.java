package com.joon.springframeworkcleanup.ioc.envetpublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

//@Component
public class AppEventPublisherRunner implements ApplicationRunner {
    @Autowired
    ApplicationContext context;
    /**
     * EventPublisher 는 이벤트를 처리하는 클래스 publishEvent()를 통하여 이벤트를 발생할 수 있다.
     * 이벤트를 발생해수고 해당 타입에 Event 를 받는 Handler 를 구현해준다
     * */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //이벤트 발생
        context.publishEvent(new MyEvent("메시지 전송", 200));
    }
}

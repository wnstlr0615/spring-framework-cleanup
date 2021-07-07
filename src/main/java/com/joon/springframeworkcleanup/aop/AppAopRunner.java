package com.joon.springframeworkcleanup.aop;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
public class AppAopRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
    @RestController
    class HelloController{
        @GetMapping("/hello")
        @CheckTime
        public String hello() throws InterruptedException {
            Thread.sleep(1000);
            return "hello";
        }

        @GetMapping("/bye")
        public String bye(){
            return "bye";
        }

    }
}

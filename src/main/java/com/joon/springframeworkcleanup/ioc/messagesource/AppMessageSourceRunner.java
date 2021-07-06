package com.joon.springframeworkcleanup.ioc.messagesource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

//@Component
public class AppMessageSourceRunner implements ApplicationRunner {
    @Autowired
    MessageSource messageSource;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(messageSource.getMessage("say", new String[]{"회원"}, Locale.KOREAN));
    }
}

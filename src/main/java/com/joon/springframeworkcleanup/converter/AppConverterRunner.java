package com.joon.springframeworkcleanup.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Component
public class AppConverterRunner implements ApplicationRunner {
    @Autowired
    ConversionService conversionService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(conversionService);
    }
    @RestController
    static
    class EventController{
        @GetMapping("/event/{event}")
        public Event getEvent(@PathVariable Event event){
            return event;
        }
    }
}

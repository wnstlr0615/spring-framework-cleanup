package com.joon.springframeworkcleanup.converter;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) { // addFormatters를 통해 converter 와 formatter 를 등록 할 수 있다 하지만 자동으로 등록됨
        registry.addConverter(new EventConverter());
        WebMvcConfigurer.super.addFormatters(registry);
    }
}

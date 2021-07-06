package com.joon.springframeworkcleanup.ioc.beanfactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.joon.springframeworkcleanup.ioc") //basePackages 등록 하여 하위 패키지 빈 인식
public class Appconfig {
}

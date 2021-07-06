package com.joon.springframeworkcleanup.ioc.beanfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

    /**
     * ApplicationContext 는 BeanFactory 를 확장한 것으로 사용하는 Bean을 등록해주어야 한다.
     * 등록 방법
     * 1. AnnotationConfigApplicationContext(java) 를 사용하여 어노테이션을 사용하여 등록
     * 2. ClassPathXmlApplicationContext(xml) 을 사용 하는 방법
     *
     * 정리 : Spring Boot 사용 시
     *  //@SpringBootApplication 어노테이션 사용 시 Application클래스 가 포함된 패키지 전체를 컴포넌트 스캔을 하므로 빈이 자동으로 등록됨
     *  스프링 부트를 사용 하지 않는 다면 다음 과 같은 과정을 통해 ioc 컨테이너에 빈을 등록해주는어야 한다.
     * */

    @Autowired
    ApplicationContext context;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        ApplicationContext ctx=new AnnotationConfigApplicationContext(Appconfig.class);//Appconfig.class 를 제거 하고 실행 하면 빈등록 여부를 테스트 할 수 있다;
//      ApplicationContext ctx=new ClassPathXmlApplicationContext(); //xml 등록 메서드
        BookService bookService = ctx.getBean(BookService.class);
        System.out.println(bookService.getBook());
    }

}

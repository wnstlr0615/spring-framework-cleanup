package com.joon.springframeworkcleanup.ioc.scope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

//@Component
public class AppScopeRunner implements ApplicationRunner {
    @Autowired
    ApplicationContext context;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Book - 동일한 빈 반환");
        System.out.println(context.getBean(Book.class));
        System.out.println(context.getBean(Book.class));
        System.out.println(context.getBean(Book.class));

        System.out.println("Bag - 매번 새로운 빈 반환");
        System.out.println(context.getBean(Bag.class));
        System.out.println(context.getBean(Bag.class));
        System.out.println(context.getBean(Bag.class));

        System.out.println("Bag 안에 싱글톤인 Book이 있는 경우 ");
        System.out.println(context.getBean(Bag.class).getBook());
        System.out.println(context.getBean(Bag.class).getBook());
        System.out.println(context.getBean(Bag.class).getBook());

        /**
         * 싱글톤 안에 있는 프로토 타입의 객체는 같은 객체를 반환
         * 싱글톤 안에 있는 객체들도 다 다르게 생성하려면
         * Bag 클래스에 @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS) 설정
         * */
        System.out.println("싱글 톤인 Book 안에 MCmBag 프로토타입에 프록시 모드 설정하지 않은 경우");
        System.out.println(context.getBean(Book.class).getMcmBag());
        System.out.println(context.getBean(Book.class).getMcmBag());
        System.out.println(context.getBean(Book.class).getMcmBag());

        System.out.println("싱글 톤인 Book 안에 Bag 프로토타입에 프록시 모드 설정한 경우");
        System.out.println(context.getBean(Book.class).getBag());
        System.out.println(context.getBean(Book.class).getBag());
        System.out.println(context.getBean(Book.class).getBag());


    }
}

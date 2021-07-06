package com.joon.springframeworkcleanup.ioc.environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppEnvironmentRunner implements ApplicationRunner {
    @Autowired
    Environment environment;
    @Autowired
    ApplicationContext context;

    /**
     * Environment 객체는 프로파일과 프로퍼티를 관리함
     *  프로퍼티에 등록된 값을 key 값으 통해 가져올 수 있다
     *  프로파일 설정 시 특정 프로파일 실행 시에만 빈으로 등록
     *  VM 옵션에 특정 프로파일로 실행 하도록 추가하거나
     *  환경변수에 설정
     *
     *  프로파일을 사용하면 테스트 환경에 등록해야 하는 빈과 일반적으로 필요한 빈들을
     *  구분하여 필요한 빈들만 실행 할 수 있다.
     *
     *  기본적으로 application.properties 로 관리하지만
     *  /@PropertySource("classpath:/app.properties) 다음 과 같은 방법으로 사용 가능
     * */

    @Autowired
    Book book;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //Environment environment = context.getEnvironment();
        System.out.println(this.environment.getProperty("app.name"));//프로 퍼티 읽기
        System.out.println(book);// test 로 프로파일을 활성화 시켜야 Bean이 등록됨

        String[] activeProfiles = environment.getActiveProfiles();
        String[] defaultProfiles = environment.getDefaultProfiles();
        //등록된 프로파일 확인
        for (String activeProfile : activeProfiles) {
            System.out.println(activeProfile);
        }
        //디폴트 프로파일 확인
        for (String defaultProfile : defaultProfiles) {
            System.out.println(defaultProfile);
        }

        System.out.println("app.properties 에서 불러오기");
        System.out.println(environment.getProperty("my.name"));
    }
}

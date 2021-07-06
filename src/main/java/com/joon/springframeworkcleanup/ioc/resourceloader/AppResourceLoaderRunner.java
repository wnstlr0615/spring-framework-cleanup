package com.joon.springframeworkcleanup.ioc.resourceloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
@Component
public class AppResourceLoaderRunner implements ApplicationRunner {

    @Autowired
    ApplicationContext context;
    /**
     * Resource를 classpath 타입이나 file 타입을 이용 하여 읽어 올 수 있다.
     * */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Resource resource = context.getResource("classpath:test.txt");
        Resource resource1 = context.getResource("file:C:\\Users\\wnstl\\Desktop\\spring-framework-cleanup\\src\\main\\resources\\test.txt");
        System.out.println(resource1.exists()); // 파일 존재 유무
        System.out.println(resource.getFilename()); // 파일 이름
        System.out.println(resource.getDescription()); // 파일 설명
        System.out.println(resource.getURI()); // 파일 경로
        System.out.println(Files.readString(Path.of(resource.getURI()))); // 파일 정보 가져오기
    }
}

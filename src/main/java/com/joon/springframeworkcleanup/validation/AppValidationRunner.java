package com.joon.springframeworkcleanup.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import java.util.List;

//@Component
public class AppValidationRunner implements ApplicationRunner {
    @Qualifier("bookValidator")// 여러 Validator이 등록되어 있으므로 한가지를 지정해준다.
    @Autowired
    Validator validator;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Book book = new Book();
        Errors errors=new BeanPropertyBindingResult(book, "book"); // Errors에 검증할 객체 추가
        validator.validate(book, errors); // 검사  검사 결과는 errors에 담겨진다.
        errors.getAllErrors().forEach(e->{
            System.out.println(e.getCode());
            System.out.println(e.getObjectName());
            System.out.println(e.getDefaultMessage());
        });
    }
}

package com.joon.springframeworkcleanup.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) { // 어떤 한 타입의 객체를 검증할 것인지 설정
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) { // 검증 로직 구현
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "title empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "content empty");
    }
}

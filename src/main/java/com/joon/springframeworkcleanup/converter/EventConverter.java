package com.joon.springframeworkcleanup.converter;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
/**
 *  A를 B로 변경해주는 데이터 바인딩 추상화 클래스이다.
 *  String 으로 입력받아 Event 객체로 변환 해준다.
 * */
//@Component
public class EventConverter implements Converter<String, Event> {
    @Override
    public Event convert(String eventId) {
        Long id = Long.valueOf(eventId);
        return new Event(id, "joon", "hello");
    }

}

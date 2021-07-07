package com.joon.springframeworkcleanup.converter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

/**
 * 문자열 다국화 기능 도 제공 하고 웹에 특화된 변환 기이다.
 * */
//@Component
public class EventFormatter implements Formatter<Event> {
    @Override
    public Event parse(String text, Locale locale) throws ParseException {
        return new Event(Long.parseLong(text), "joon", "hello");
    }
    @Override
    public String print(Event object, Locale locale) {
        return object.getId().toString();
    }
}

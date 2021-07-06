package com.joon.springframeworkcleanup.ioc.autowired;

import org.springframework.stereotype.Component;

@Component
public class BookA implements Book{
    @Override
    public String getBookName() {
        return "bookA";
    }
}


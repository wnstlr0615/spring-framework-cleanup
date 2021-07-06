package com.joon.springframeworkcleanup.ioc.autowired;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Component
//@Primary
public class BookB implements Book{
    @Override
    public String getBookName() {
        return "bookB";
    }
}

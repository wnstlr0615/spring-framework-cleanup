package com.joon.springframeworkcleanup.ioc.scope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

//@Component
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Bag {
    @Autowired
    Book book;
    public Book getBook(){
        return book;
    }
}

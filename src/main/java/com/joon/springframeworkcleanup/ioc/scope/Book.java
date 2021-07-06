package com.joon.springframeworkcleanup.ioc.scope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Book {
    @Autowired
    Bag bag;
    @Autowired
    McmBag mcmBag;
    public Bag getBag(){
        return bag;
    }

    public McmBag getMcmBag() {
        return mcmBag;
    }
}

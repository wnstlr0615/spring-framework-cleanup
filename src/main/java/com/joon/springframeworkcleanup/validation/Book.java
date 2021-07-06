package com.joon.springframeworkcleanup.validation;

import org.springframework.stereotype.Component;

public class Book {
    Long id;
    String title;
    String content;
    int price;

    public Book() {
    }

    public Book(Long id, String title, String content, int price) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getPrice() {
        return price;
    }
}

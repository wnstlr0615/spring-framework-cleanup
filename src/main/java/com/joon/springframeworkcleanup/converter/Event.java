package com.joon.springframeworkcleanup.converter;

public class Event {
    private Long id;
    private String name;
    private String message;

    public Event(Long id, String name, String message) {
        this.id = id;
        this.name = name;
        this.message = message;
    }

    public Event(Long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}

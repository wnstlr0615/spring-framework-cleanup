package com.joon.springframeworkcleanup.ioc.environment;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

//@Repository
@Profile("test")
public class Book {
}

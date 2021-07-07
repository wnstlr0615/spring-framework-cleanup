package com.joon.springframeworkcleanup.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

//@Component
public class AppSpringExpressLanguageRunner implements ApplicationRunner {
    @Value("#{1+1}")
    int value;
    @Value("#{'hello'+ 'world'}")
    String greeting;
    @Value("#{1 eq 1}")
    boolean trueOrFalse;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(value);
        System.out.println(greeting);
        System.out.println(trueOrFalse);
        ExpressionParser parser=new SpelExpressionParser();
        Expression expression = parser.parseExpression("2+100");
        System.out.println(expression.getValue());
    }
}

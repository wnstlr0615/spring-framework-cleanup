package com.joon.springframeworkcleanup.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class CheckTimeAop {
    @Around(value = "@annotation(CheckTime)") //어노테이션에 적용
    public Object checkTimeAnotation(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        Object proceed = joinPoint.proceed();
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        return proceed;
    }
    /**aop 패키지  AppAopRunner클래스 안에 모든 클래스 b로 시작하는 메소드에 적용 */
    @Around(value = "execution(* com.joon.springframeworkcleanup.aop.AppAopRunner.*.b*(..))")
    public Object checkTimeExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        Object proceed = joinPoint.proceed();
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        return proceed;
    }
}

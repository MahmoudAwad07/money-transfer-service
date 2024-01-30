package com.awadinhoo.code.moneytransferservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.util.Arrays;

public class LoggingAspect {

    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.awadinhoo.code.moneytransferservice.controllers.*.*(..))")
    public void logControllerEntry(JoinPoint joinPoint) {
        logger.info("Entering controller : {}", joinPoint.getSignature().toShortString());
    }

    @AfterReturning(pointcut = "execution(* com.awadinhoo.code.moneytransferservice.drones.controllers.*.*(..))", returning = "result")
    public void logControllerExit(JoinPoint joinPoint, Object result) {
        logger.info("Exiting controller : {}. Response: {}", joinPoint.getSignature().toShortString(), result);
    }

    @Around("execution(* com.awadinhoo.code.moneytransferservice.services..*(..)))")
    public Object logAllServices(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        Object[] parameters = proceedingJoinPoint.getArgs();

        logger.info("Start execution of {}.{} with parameters :: {}" , className , methodName, Arrays.toString(parameters));
        final StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        logger.info("End execution of {}.{} in time :: {} ms", className , methodName, stopWatch.getTotalTimeMillis());
        return result;
    }
}

package com.project.japanwishlist.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Aspect
@Component
public class ExecutionLoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(ExecutionLoggingAspect.class);

    @Around("execution(public * *(..)) && "
            + "(@within(org.springframework.web.bind.annotation.RestController) "
            + "|| @within(org.springframework.stereotype.Service))")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        Class<?> targetClass = AopUtils.getTargetClass(joinPoint.getTarget());
        String layer = isController(targetClass) ? "CONTROLLER" : "SERVICE";
        String className = targetClass.getSimpleName();
        String methodName = ((MethodSignature) joinPoint.getSignature()).getName();
        String invocation = className + "." + methodName + "()";
        long startTime = System.nanoTime();

        log.info("[{}] START {}", layer, invocation);
        try {
            Object result = joinPoint.proceed();
            log.info("[{}] END {} - {}ms", layer, invocation, elapsedMillis(startTime));
            return result;
        } catch (Throwable exception) {
            log.error("[{}] ERROR {} - {}: {} - {}ms", layer, invocation,
                    exception.getClass().getSimpleName(), exception.getMessage(), elapsedMillis(startTime));
            throw exception;
        }
    }

    private boolean isController(Class<?> targetClass) {
        return AnnotatedElementUtils.hasAnnotation(targetClass, RestController.class);
    }

    private long elapsedMillis(long startTime) {
        return (System.nanoTime() - startTime) / 1_000_000;
    }
}

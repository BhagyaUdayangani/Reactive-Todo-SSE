package com.example.to_do_api.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Aspect for logging execution time of methods annotated with {@link TrackExecutionTime}.
 * Supports both synchronous and reactive (Mono/Flux) return types.
 * Enabled via the 'aspect.enabled' property.
 */
@Aspect
@Component
@Slf4j
@ConditionalOnExpression("${aspect.enabled:true}")
public class ExecutionTimeAdvice {

    private void logExecutionTime(ProceedingJoinPoint joinPoint, long start) {
        long end = System.currentTimeMillis();
        log.info("LOG:: Class Name: {}. Method Name: {}. Time taken for Execution is : {}ms",
                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), (end - start));
    }

    @Around("@annotation(com.example.to_do_api.annotation.TrackExecutionTime)")
    @SuppressWarnings("ReactiveStreamsUnusedPublisher")
    public Object executionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();

        if (result instanceof Mono<?> mono) {
            return mono.doOnTerminate(() -> logExecutionTime(joinPoint, start));
        } else if (result instanceof Flux<?> flux) {
            return flux.doOnTerminate(() -> logExecutionTime(joinPoint, start));
        }

        // For synchronous methods, log immediately
        logExecutionTime(joinPoint, start);
        return result;
    }
}
package org.example.bai03.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {

    @Around("execution(* org.example.bai03.service.InventoryService.*(..))")
    public Object measurePerformance(ProceedingJoinPoint joinPoint)
            throws Throwable {

        long start = System.currentTimeMillis();

        try {

            Object result = joinPoint.proceed();

            long end = System.currentTimeMillis();

            long time = end - start;

            String methodName = joinPoint.getSignature().getName();

            log.info("Hàm {} chạy mất {} ms",
                    methodName,
                    time);

            if (time > 500) {

                log.warn(
                        "[Performance Alert] Hàm {} quá chậm ({} ms)",
                        methodName,
                        time
                );
            }

            return result;

        } catch (Exception e) {

            log.warn("Exception tại method {} : {}",
                    joinPoint.getSignature().getName(),
                    e.getMessage());

            throw e;
        }
    }
}
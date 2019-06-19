package by.epam.kvirykashvili.javalabtask.domain.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class LoggerAspect {

    @Pointcut("execution(* *(..)) && within(by.epam.kvirykashvili.javalabtask..*)")
    private void crudOperations() {
        //Do nothing because it's a pointcut.
    }

    @Around("@annotation(by.epam.kvirykashvili.javalabtask.domain.aspect.annotations.ShowTime)")
    public Object showTimeAnnotation(ProceedingJoinPoint joinPoint) {
        Object output = null;
        long startTime = System.currentTimeMillis();

        try {
            output = joinPoint.proceed();
        } catch (Throwable throwable) {
            log.error(throwable.getMessage(), throwable);
        }

        long time = System.currentTimeMillis() - startTime;
        log.info("Method: " + joinPoint.getSignature().toShortString() + ", time = " + time + " ms");

        return output;
    }

    @AfterReturning(pointcut = "@annotation(by.epam.kvirykashvili.javalabtask.domain.aspect.annotations.ShowResult)", returning = "object")
    public void showResultAnnotation(JoinPoint joinPoint, Object object) {
        log.info("Method: " + joinPoint.getSignature().toShortString() + " returned object: " + object);
    }


    @Before("crudOperations()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Start running method: " + joinPoint.getSignature().toShortString());
        for (Object o : joinPoint.getArgs()) {
            log.info("Param: " + o);
        }
    }

    @After("crudOperations()")
    public void logAfter(JoinPoint joinPoint) {
        log.info("Finish running method: " + joinPoint.getSignature().toShortString());
    }

}

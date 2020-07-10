package wr1ttenyu.f1nal.study.project.archetype.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodMonitor {

    private Logger logger = LoggerFactory.getLogger(MethodMonitor.class);

    @Pointcut("@annotation(wr1ttenyu.f1nal.study.project.archetype.util.LogMonitor) && within(wr1ttenyu.f1nal.study.project.archetype..*)")
    public void methodAspect() {}

    @Around("methodAspect()")
    public void around(JoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object rtn = null;
        Long runtime = null;
        try {
            rtn = ((ProceedingJoinPoint) joinPoint).proceed();
            long end = System.currentTimeMillis();
            runtime = end - start;
            if (runtime > 1000)
                logger.warn("方法 {} 执行时间超过阈值,请检查,执行时长:{}ms 入参:{} 出惨:{}", joinPoint.getSignature(), runtime, joinPoint.getArgs(), rtn);
            else
                logger.info("方法 {} 执行正常,执行时长:{}ms 入参:{} 出惨:{}", joinPoint.getSignature(), runtime, joinPoint.getArgs(), rtn);
        } catch (Throwable e) {
            // FIXME 这里如果异常被吞了  AfterThrowing  还会被执行吗
            long end = System.currentTimeMillis();
            runtime = end - start;
            logger.error("方法 {} 执行异常,执行时长:{}ms 入参:{} errMsg:", joinPoint.getSignature(), runtime, joinPoint.getArgs(), e);
            throw e;
        }
    }
}

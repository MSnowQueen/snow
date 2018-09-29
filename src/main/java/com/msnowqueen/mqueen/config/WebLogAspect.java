package com.msnowqueen.mqueen.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by zn* on 2018/9/29
 * 在客户端进行请求调用接口时, 根据aop(面向切面编程)进行打印日志的操作
 */
@Aspect
@Component
@Order(5) // 切面优先级, 值越小优先级越高
@Slf4j
public class WebLogAspect {

    // 用于保存某个线程共享变量
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.msnowqueen.mqueen.controller..*.*(..))")
    public void webLog() {

    }

    /**
     * 请求method前打印内容
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 开始记录运行时间
        startTime.set(System.currentTimeMillis());

        // 接收到请求, 记录请求内容
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        log.info("≮===============[请求内容-开始]===============≯");
        try {
            log.info("URL : " + request.getRequestURL().toString());
            log.info("HTTP_METHOD : " + request.getMethod());
            log.info("IP : " + request.getRemoteAddr());
            log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName()
                    + "." + joinPoint.getSignature().getName());
            log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        } catch (Exception e) {
            log.error("### WebLogAspect.class doBefore() ### ERROR : ", e);
        }
        log.info("≮===============[请求内容-结束]===============≯");
    }

    /**
     * 在方法执行完结后打印返回内容
     *
     * @param result
     * @throws Throwable
     */
    @AfterReturning(returning = "result", pointcut = "webLog()")
    public void doAfterReturning(Object result) throws Throwable {
        // 处理完请求，返回内容
        log.info("≮===============[返回内容-开始]===============≯");
        try {
            log.info("RESPONSE : " + result);
            log.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
        } catch (Exception e) {
            log.error("### WebLogAspect.class doAfterReturning() ### ERROR : ", e);
        }
        log.info("≮===============[返回内容-结束]===============≯");
    }

}

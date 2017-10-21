package com.girl.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * http 请求处理切面类
 */
@Aspect
@Component
public class HttpAspect {
    private static final Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    /**
     * 定义切点
     */
    @Pointcut("execution(public * com.girl.controller.GirlController.*(..))")
    public void log() {

    }

    /**
     * 方法执行之前
     */
    @Before("log()")
    public void doBefore(JoinPoint point) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        // url
        logger.info("url={}", request.getRequestURL());
        // method
        logger.info("method={}", request.getMethod());
        // ip
        logger.info("ip={}", request.getRemoteAddr());
        // 类方法
        logger.info("class_method={}", point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
        // 参数
        logger.info("args={}", point.getArgs());
    }

    /**
     * 方法执行之后
     */
    @After("log()")
    public void doAfter() {
        logger.info("httpAspect after");
    }

    /**
     * 方法返回
     * @param obj
     */
    @AfterReturning(returning = "obj", pointcut = "log()")
    public void doAfterReturning(Object obj) {
        logger.info("response={}", obj);
    }
}

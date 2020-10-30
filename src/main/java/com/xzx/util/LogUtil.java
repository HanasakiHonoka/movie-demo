package com.xzx.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname LogUtil
 * @Description
 * @Date 2020/10/27 19:05
 * @Author XZX
 * @Version 1.0
 */
@Aspect
@Slf4j
@Component
public class LogUtil {
    @Pointcut("execution(* com.xzx.controller..*.*(..))")
    public void log () {
    }

    @Before("log()")
    public void beforeLog(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        StringBuffer url=request.getRequestURL();
        String method=request.getMethod();
        String ip=request.getRemoteAddr();
        String class_method=joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args=joinPoint.getArgs();
        String[] argNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();//参数名
        log.info("------------------------------------");
        log.info("url: " + url.toString() + " method: " + method + " from: " + ip);
        log.info("class_method: " + class_method);
        JSONObject param = new JSONObject();
        int len;
        if (method.equals("POST")) {
            len = Math.min(1, argNames.length);
        } else {
            len = argNames.length;
        }
        for (int i = 0; i < len; i++) {
            param.put(argNames[i], args[i]);
        }
        log.info(param.toString());
        log.info("------------------------------------");
    }
}

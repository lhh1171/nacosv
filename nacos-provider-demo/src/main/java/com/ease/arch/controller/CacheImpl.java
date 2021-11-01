package com.ease.arch.controller;



import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class CacheImpl {
    private final CacheClient cacheClient;

    public CacheImpl(CacheClient cacheClient) {
        this.cacheClient=cacheClient;
    }



    @Pointcut("@annotation(CacheDelete)")
    public void cacheDeleteMethod() {
    }
    @Pointcut("@annotation(CacheInsert)")
    public void cacheInsertMethod() {
    }


    @Around("cacheDeleteMethod()")
    public void aroundCacheDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        Object o= cacheClient.getValue(getKey(joinPoint));

        Object proceed = joinPoint.proceed();
        cacheClient.remove(getKey(joinPoint));
    }
    @Around("cacheInsertMethod()")
    public void aroundCacheInsert(ProceedingJoinPoint joinPoint) throws Throwable {
        while (cacheClient.getValue(getKey(joinPoint))==null){
            cacheClient.putValue(getKey(joinPoint),new Date(),1);
            joinPoint.proceed();
        }
        aroundCacheInsert(joinPoint);
    }

    /**
     * 根据参数生成cachekey
     *
     * @param joinPoint
     * @return
     */
    private String getKey(ProceedingJoinPoint joinPoint) {
        Object key= joinPoint.getArgs();
        return key.toString();
    }

}

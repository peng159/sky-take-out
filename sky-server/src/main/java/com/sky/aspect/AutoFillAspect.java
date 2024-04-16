package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component //这个注解表示这个类将会被Spring自动扫描并注册为一个Bean。
@Slf4j //这个注解是Lombok库提供的，用于自动生成日志记录器（Logger）。
public class AutoFillAspect {
    //这个注解定义了一个切入点（Pointcut），表示匹配com.sky.mapper包下的所有方法，并且这些方法上标记了com.sky.annotation.AutoFill注解。
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointCut(){

    }
    @Before("autoFillPointCut()")  //这个注解表示在切入点方法执行前执行autoFill()方法。
    public void autoFill(JoinPoint joinPoint){
        log.info("开始进行公共字段的自动填充...");
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        MethodSignature signature=(MethodSignature) joinPoint.getSignature();
        AutoFill autoFill=signature.getMethod().getAnnotation(AutoFill.class);
        OperationType operationType=autoFill.value();

        Object[] args=joinPoint.getArgs();
        if(args==null || args.length==0){
            return;
        }
        Object entity=args[0];
        LocalDateTime now=LocalDateTime.now();
        Long currentId=BaseContext.getCurrentId();

        if(operationType==OperationType.INSERT){

            try {
                Method setCreateTime =entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME,LocalDateTime.class);
                Method setCreateUser=entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER,Long.class);
                Method setUpdateTime=entity.getClass().getDeclaredMethod("setUpdateTime",LocalDateTime.class);
                Method setUpdateUser=entity.getClass().getDeclaredMethod("setUpdateUser",Long.class);
                setCreateTime.invoke(entity,now);
                setCreateUser.invoke(entity,currentId);
                setUpdateTime.invoke(entity,now);
                setUpdateUser.invoke(entity,currentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(operationType==OperationType.UPDATE){
            try {
                Method setUpdateTime = entity.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
                Method setUpdateUser=entity.getClass().getDeclaredMethod("setUpdateUser",Long.class);
                setUpdateTime.invoke(entity,now);
                setUpdateUser.invoke(entity,currentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

package org.example.dao.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.dao.annotation.RoutingDataSource;
import org.example.dao.config.DataSourceContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by hyq on 2020/12/10 20:22.
 */
@Aspect
@Component
public class DynamicDataSourceAspect {
    @Before("@annotation(org.example.dao.annotation.RoutingDataSource)")
    public void beforeSwitchDS(JoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String dataSource = DataSourceContextHolder.DEFAULT_DATASOURCE;
        if (method.isAnnotationPresent(RoutingDataSource.class)) {
            RoutingDataSource routingDataSource = method.getDeclaredAnnotation(RoutingDataSource.class);
            dataSource = routingDataSource.value();
        }
        DataSourceContextHolder.setDB(dataSource);
    }

    @After("@annotation(org.example.dao.annotation.RoutingDataSource)")
    public void afterSwitchDS(JoinPoint point) {
        DataSourceContextHolder.clearDB();
    }
}

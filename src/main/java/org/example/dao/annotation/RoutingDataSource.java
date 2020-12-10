package org.example.dao.annotation;

import org.example.dao.entity.common.Datasources;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hyq on 2020/12/10 20:27.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface RoutingDataSource {
    String value() default Datasources.MASTER_DB;
}

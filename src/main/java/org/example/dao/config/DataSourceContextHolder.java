package org.example.dao.config;

import org.example.dao.entity.common.Datasources;
import org.springframework.stereotype.Component;

/**
 * Created by hyq on 2020/12/10 20:21.
 */
@Component
public class DataSourceContextHolder {
    /**
     * 默认数据源
     */
    public static final String DEFAULT_DATASOURCE = Datasources.MASTER_DB;

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    // 设置数据源名
    public static void setDB(String dbType) {
        System.out.println("切换到{}数据源:" +  dbType);
        contextHolder.set(dbType);
    }

    // 获取数据源名
    public static String getDB() {
        return (contextHolder.get());
    }

    // 清除数据源名
    public static void clearDB() {
        contextHolder.remove();
    }
}

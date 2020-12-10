package org.example.dao.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by hyq on 2020/12/10 20:18.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        System.out.println("数据源为{}:" + DataSourceContextHolder.getDB());
        return DataSourceContextHolder.getDB();
    }
}

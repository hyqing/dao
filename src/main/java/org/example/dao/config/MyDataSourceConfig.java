package org.example.dao.config;

import org.example.dao.entity.common.Datasources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hyq on 2020/12/10 20:15.
 */
@Configuration
public class MyDataSourceConfig {

    @Autowired
    @Qualifier(Datasources.MASTER_DB)
    private DataSource masterDB;

    @Autowired
    @Qualifier(Datasources.SLAVE_DB)
    private DataSource slaveDB;

    /**
     * 动态数据源
     */
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(masterDB);

        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap<>();
        dsMap.put(Datasources.MASTER_DB, masterDB);
        dsMap.put(Datasources.SLAVE_DB, slaveDB);
        dynamicDataSource.setTargetDataSources(dsMap);

        return dynamicDataSource;
    }

}

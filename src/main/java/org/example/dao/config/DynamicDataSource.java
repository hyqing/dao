package org.example.dao.config;

import org.example.dao.entity.common.Datasources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hyq on 2020/12/10 20:18.
 */
@Component
@Primary
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Autowired
    @Qualifier(Datasources.MASTER_DB)
    private DataSource masterDB;

    @Autowired
    @Qualifier(Datasources.SLAVE_DB)
    private DataSource slaveDB;

    @Override
    protected Object determineCurrentLookupKey() {
        System.out.println("数据源为{}:" + DataSourceContextHolder.getDB());
        return DataSourceContextHolder.getDB();
    }

    /**
     * 配置数据源信息
     */
    @Override
    public void afterPropertiesSet() {
        Map<Object, Object> map = new HashMap<>();
        map.put(Datasources.MASTER_DB, masterDB);
        map.put(Datasources.SLAVE_DB, slaveDB);
        setTargetDataSources(map);
        setDefaultTargetDataSource(masterDB);
//        super.afterPropertiesSet();
    }

}

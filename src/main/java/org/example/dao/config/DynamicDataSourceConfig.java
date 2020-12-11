package org.example.dao.config;

import com.zaxxer.hikari.HikariDataSource;
import org.example.dao.entity.common.Datasources;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hyq on 2020/12/11 17:36.
 */
@Configuration
public class DynamicDataSourceConfig {

    //destroy-method="close"的作用是当数据库连接不使用的时候,就把该连接重新放到数据池中,方便下次使用调用.
    @Bean(destroyMethod = "close", name = "dataSourceMaster")
    public DataSource dataSourceMaster() {
        System.out.println("master初始化----------");
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/master");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }

    @Bean(destroyMethod = "close", name = "dataSourceSlave")
    public DataSource dataSourceSlave() {
        System.out.println("slave初始化----------");
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/slave");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource dataSourceMaster, DataSource dataSourceSlave) {
        Map<Object, Object> targetDataSources = new HashMap<>(5);
        targetDataSources.put(Datasources.MASTER_DB, dataSourceMaster);
        targetDataSources.put(Datasources.SLAVE_DB, dataSourceSlave);
        return new DynamicDataSource(dataSourceMaster, targetDataSources);
    }

}

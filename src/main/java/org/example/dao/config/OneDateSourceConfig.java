package org.example.dao.config;

import org.example.dao.repository.common.CustomRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by hyq on 2020/12/9 22:12.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class,
        entityManagerFactoryRef = "entityManagerFactoryDataBase1", // 配置连接工厂
        transactionManagerRef = "transactionManagerDatabase1", // 配置事物管理器
        basePackages = {"org.example.dao.repository.one"} // 设置dao所在位置
)
public class OneDateSourceConfig {
    // 配置数据源
    @Autowired
    @Qualifier("oneDataSource")
    private DataSource oneDataSource;

    @Bean(name = "entityManagerFactoryDataBase1")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean1(EntityManagerFactoryBuilder builder) {
        return builder
                // 设置数据源
                .dataSource(oneDataSource)
                //设置实体类所在位置.扫描所有带有 @Entity 注解的类
                .packages("org.example.dao.entity.one")
                // Spring会将EntityManagerFactory注入到Repository之中.有了 EntityManagerFactory之后,
                // Repository就能用它来创建 EntityManager 了,然后 EntityManager 就可以针对数据库执行操作
                .persistenceUnit("database1PersistenceUnit")
                .build();
    }

    /**
     * 配置事物管理器
     */
    @Bean(name = "transactionManagerDatabase1")
    PlatformTransactionManager transactionManagerDatabase1(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryBean1(builder).getObject());
    }

}

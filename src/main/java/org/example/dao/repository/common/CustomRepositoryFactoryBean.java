package org.example.dao.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;

/**
 * Created by hyq on 2020/12/9 22:50.
 */
public class CustomRepositoryFactoryBean <T extends JpaRepository<S, ID>, S, ID >
        extends JpaRepositoryFactoryBean<T, S, ID> {
    public CustomRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    //重写createRepositoryFactory方法，用当前的CustomRepositoryFactory创建实例
    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new CustomRepositoryFactory (entityManager);
    }

    private static class CustomRepositoryFactory extends JpaRepositoryFactory {

        //参见问题1
        //注意：这里一定要写CustomRepositoryFactoryBean的构造函CustomRepositoryFactory数，否则会报错。
        public CustomRepositoryFactory (EntityManager entityManager){
            super(entityManager);
        }

        //重写getTargetRepository方法并且获得当前自定义的Repository实现
        @Override
        protected JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
            return new CustomRepositoryImp<>(information.getDomainType(),entityManager);
        }

        //重写getRepositoryBaseClas方法并且获得当前自定义的Repository的实现类型
        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata){
            return CustomRepositoryImp.class;
        }
    }
}

package org.example.dao.repository.common;

import org.example.dao.entity.common.CommonParam;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by hyq on 2020/12/9 22:49.
 */
@NoRepositoryBean
public class CustomRepositoryImp <T, ID> extends SimpleJpaRepository<T, ID> implements CustomRepository<T, ID> {
    private final EntityManager em;

    public CustomRepositoryImp(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
    }


    @Override
    public <S> List<S> myQuery(CommonParam param, Class<S> tClass) {
        String sql = "select u.* from user u where u.name='u1'";
        Query query = em.createNativeQuery(sql);
        List list = query.getResultList();
        return list;
    }
}

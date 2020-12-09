package org.example.dao.repository.common;

import org.example.dao.entity.common.CommonParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created by hyq on 2020/12/9 22:47.
 */
@NoRepositoryBean
public interface CustomRepository<T, ID> extends JpaRepository<T, ID> {

    /**
     * 自定义查询方法
     */
    <S> List<S> myQuery(CommonParam param, Class<S> tClass);
}

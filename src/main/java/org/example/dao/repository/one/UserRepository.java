package org.example.dao.repository.one;

import org.example.dao.entity.one.User;
import org.example.dao.repository.common.CustomRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hyq on 2020/12/9 22:52.
 */
@Repository
public interface UserRepository extends CustomRepository<User, Integer> {

    @Query(value = "select u.* from user u where u.name like  %?1%", nativeQuery = true)
    public List<User> queryByName(String name);
}


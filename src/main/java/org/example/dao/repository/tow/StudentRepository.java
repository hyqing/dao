package org.example.dao.repository.tow;

import org.example.dao.entity.tow.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by hyq on 2020/12/9 22:53.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>
        , JpaSpecificationExecutor<Student> {
}

package org.example.dao.repository.ms;

import org.example.dao.entity.ms.App;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hyq on 2020/12/10 20:44.
 */
public interface AppRepository extends JpaRepository<App, Integer> {
}

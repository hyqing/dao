package org.example.dao.service;

import org.example.dao.annotation.RoutingDataSource;
import org.example.dao.entity.common.Datasources;
import org.example.dao.entity.ms.App;
import org.example.dao.repository.ms.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hyq on 2020/12/10 20:45.
 */
@Service
public class AppService {

    @Autowired
    private AppRepository appRepository;

    @RoutingDataSource(value = Datasources.SLAVE_DB)
    public List<App> getApp() {
        return appRepository.findAll();
    }

    @RoutingDataSource()
    public App addApp() {
        App app = new App();
        app.setName("dy");
        app.setRemark("抖音");
        return appRepository.save(app);
    }
}

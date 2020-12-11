package org.example.dao.controller;

import org.example.dao.entity.common.CommonParam;
import org.example.dao.entity.ms.App;
import org.example.dao.entity.one.User;
import org.example.dao.entity.tow.Student;
import org.example.dao.repository.one.UserRepository;
import org.example.dao.repository.tow.StudentRepository;
import org.example.dao.service.AppService;
import org.example.dao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hyq on 2020/12/9 22:55.
 */
@RestController
public class TestController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AppService appService;

    @RequestMapping("/user")
    private List<User> getUser() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @RequestMapping("/student")
    private List<Student> getStudent() {
        List<Student> students = studentRepository.findAll();
        return students;
    }

    @RequestMapping("/custom1")
    private List<User> getCustom1() {
        List<User> list = userRepository.queryByName("u");
        return list;
    }

    @RequestMapping("/custom2")
    private List<User> getCustom2() {
        List<User> list = userRepository.myQuery(new CommonParam(), User.class);
        return list;
    }

    @RequestMapping("/fromRedis")
    private String fromRedis() {
        return userService.getString("NODE_ROLE_USER");
    }

    @RequestMapping("/app/slave")
    private List<App> formSlave() {
        return appService.formSlave();
    }

    @RequestMapping("/app/master")
    private List<App> formMaster() {
        return appService.formMaster();
    }
}

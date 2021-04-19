package com.yuzhi.home.controller;

import com.yuzhi.home.model.User;
import com.yuzhi.home.service.UserService;
import com.yuzhi.home.utils.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户
 * @author soso
 * @date 2019-1-12 11:15:42
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/fidnAll")
    public MessageResult findAll(){
        List<User> users = userService.queryAll();

        return MessageResult.ok(users);
    }
}

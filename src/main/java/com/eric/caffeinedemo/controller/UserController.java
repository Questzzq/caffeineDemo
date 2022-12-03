package com.eric.caffeinedemo.controller;

import com.eric.caffeinedemo.entity.User;
import com.eric.caffeinedemo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Eric Tseng
 * @since 2022-02-19
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping(value = "/getUserById")
    public User getUserById(String id) {
        return userService.getUserById(id);
    }
}

package com.experiments.controller;


import com.experiments.entity.User;
import com.experiments.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/user"))
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public User register( @RequestBody User user ){

        userService.save( user );
        return user;
    }

    @PostMapping("/login")
    public String login( @RequestBody User user ){

        return userService.verify( user );
    }
}

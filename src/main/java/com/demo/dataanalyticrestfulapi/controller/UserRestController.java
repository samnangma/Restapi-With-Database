package com.demo.dataanalyticrestfulapi.controller;

import com.demo.dataanalyticrestfulapi.model.User;
import com.demo.dataanalyticrestfulapi.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class UserRestController {

    private final UserService userService;
    UserRestController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/allusers")
    List<User> getAllUser(){
        return userService.allUsers();
    }
    @GetMapping("/user/{id}")
    public User findUserByID(@PathVariable int id){
        return userService.findUserById(id);

    }

}

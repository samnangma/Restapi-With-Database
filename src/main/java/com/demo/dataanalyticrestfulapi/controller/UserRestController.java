package com.demo.dataanalyticrestfulapi.controller;

import com.demo.dataanalyticrestfulapi.model.User;
import com.demo.dataanalyticrestfulapi.service.UserService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/new-user")
    public String createUser(@RequestBody User user){
        System.out.println("Affect row : "+user);
        try{
            int affectRow = userService.createNewUser(user);
            if(affectRow > 0){
                return "Create user Successfully!";
            } else{
                return "Cannot create a new user";
            }



        } catch (Exception exception){
            return exception.getMessage();
        }
//        return "Successfully";
    }

}

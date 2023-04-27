package com.demo.dataanalyticrestfulapi.controller;

import com.demo.dataanalyticrestfulapi.model.User;
import com.demo.dataanalyticrestfulapi.model.UserAccount;
import com.demo.dataanalyticrestfulapi.model.response.AccountResponse;
import com.demo.dataanalyticrestfulapi.service.UserService;
import com.demo.dataanalyticrestfulapi.utils.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/user")
public class UserRestController {

    private final UserService userService;
    UserRestController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/allusers")
    List<User> getAllUser(){
        return userService.allUsers();
    }
    @GetMapping("/{id}")
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

    @PostMapping("/user-accounts")
    public Response<List<UserAccount>> getAllUserAccounts(){
        try {
            List<UserAccount> data = userService.getAllUserAccounts();
                    return Response.<List<UserAccount>>ok().setPayload(data).setMessage("Successfully retrieved all users accounts");
        } catch (Exception ex){
            return Response.<List<UserAccount>>exception().setMessage("Exception occurs ! Failed to retrieved all users accounts!")
                    .setSuccess(false);

        }
    }

}

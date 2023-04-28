package com.demo.dataanalyticrestfulapi.controller;

import com.demo.dataanalyticrestfulapi.Reposity.UserRepository;
import com.demo.dataanalyticrestfulapi.model.User;
import com.demo.dataanalyticrestfulapi.model.UserAccount;
import com.demo.dataanalyticrestfulapi.model.request.UserRequest;
import com.demo.dataanalyticrestfulapi.model.response.AccountResponse;
import com.demo.dataanalyticrestfulapi.service.UserService;
import com.demo.dataanalyticrestfulapi.utils.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/user")
public class UserRestController {

    private final UserService userService;

    private boolean isUserFounds(int id){
        User user = userService.findUserById(id);
        return user != null;
    }

    private Response<User> userNotFound(int id){
        return Response.<User>notFound().setMessage("Cannot find user with id: "+id).setSuccess(false).setStatus(Response.Status.NOT_FOUND);
    }
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
    public Response<User> createUser(@RequestBody UserRequest request){
        try {
            int userID = userService.createNewUser(request);
            if(userID > 0 ){
                User response = new User().setUsername(request.getUsername()).setAddress(request.getAddress()).setGender(request.getGender()).
                setUserId(userID);

                return Response.<User>createdSuccess().setPayload(response).setMessage("Create User Successfully").setSuccess(true);

            } else {
                return Response.<User>BAD_REQUEST().setMessage("Failed to create User");
            }
        } catch (Exception exception){
            return Response.<User>exception().setMessage("Exception occurs! Failed to create a new user").setSuccess(false);
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

    @PutMapping("/{id}")
    public Response<User> updateUser(@PathVariable int id , @RequestBody User user){
        try {
            if (isUserFounds(id)){
                user.setUserId(id);
                userService.updateUser(user, id);
                return Response.<User>updateSuccess().setPayload(user).setMessage("Update User Successfully :) , Congratulations");
            }else {
                return userNotFound(id);
            }
        }catch (Exception ex){
            return Response.<User>exception().setMessage("Update User Not Success , :( Please try again ").setSuccess(false);
        }
    }

    @DeleteMapping("/{id}")
    public Response<User> removeUser(@PathVariable int id){
        try {
            if (isUserFounds(id)){
                userService.removeUSer(id);
                return Response.<User>deleteSuccess().setMessage("Delete Successfully! , thank you");
            }else {
                return userNotFound(id);
            }
        }catch (Exception ex){
            return Response.<User>exception().setMessage("Delete User Not Success... :( Please try again");
        }
    }
}




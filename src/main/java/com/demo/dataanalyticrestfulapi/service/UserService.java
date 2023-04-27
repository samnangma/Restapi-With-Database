package com.demo.dataanalyticrestfulapi.service;

import com.demo.dataanalyticrestfulapi.model.User;
import com.demo.dataanalyticrestfulapi.model.UserAccount;

import java.util.List;

public interface UserService {
    List <User> allUsers();
    List <User> findUserByName();
    User findUserById(int id);

    int createNewUser(User user);
    int updateUser(User user);
    int removeUSer(int id);

    List<UserAccount> getAllUserAccounts();
}

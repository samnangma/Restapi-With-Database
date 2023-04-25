package com.demo.dataanalyticrestfulapi.service.serviceImpl;

import com.demo.dataanalyticrestfulapi.Reposity.UserRepository;
import com.demo.dataanalyticrestfulapi.model.User;
import com.demo.dataanalyticrestfulapi.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public List<User> allUsers() {
        return userRepository.allUsers();
    }

    @Override
    public List<User> findUserByName() {
        return null;
    }

    @Override
    public User findUserById(int id) {
        return null;
    }

    @Override
    public int createNewUser(User user) {
        return 0;
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    @Override
    public int removeUSer(int id) {
        return 0;
    }
}

package com.example.login_email_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.login_email_service.entity.User;
import com.example.login_email_service.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findByUsernameAndPassword(String username, String password){
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public User saveUpdateUser(User user) {
        return userRepository.save(user);
    }

    public boolean loginStatus(String username, String password) {
        boolean flag = false;
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
    public Optional<User> getUserById(long id){
        return userRepository.findById(id);
    }

}

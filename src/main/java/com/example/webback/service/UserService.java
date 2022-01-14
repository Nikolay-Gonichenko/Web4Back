package com.example.webback.service;

import com.example.webback.entity.User;
import com.example.webback.exception.FailedRegisterUserException;
import com.example.webback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    public void saveUser(User user) throws FailedRegisterUserException {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            throw new FailedRegisterUserException("Username is already used");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    public User findByUsername(String login) {
        return userRepository.findByUsername(login);
    }


    public boolean checkUser(User user){
        User userByUsername = userRepository.findByUsername(user.getUsername());
        if (userByUsername == null) {
            return false;
        }
        return passwordEncoder.matches(user.getPassword(), userByUsername.getPassword());
    }

}

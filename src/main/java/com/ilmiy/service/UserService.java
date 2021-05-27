package com.ilmiy.service;

import com.ilmiy.model.user.User;
import com.ilmiy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

//    public User save(){
//
//    }

    public UserDetails loadByUsernameOrEmail(String s) {
        if (userRepository.existsByUsername(s)) return userRepository.findByUsername(s).get();
        return userRepository.findByEmail(s).get();
    }
}

package com.manufacture.identityservice.service;

import com.manufacture.identityservice.entity.User;
import com.manufacture.identityservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserById(Long id) {

        return userRepository.findById(id);

    }
}

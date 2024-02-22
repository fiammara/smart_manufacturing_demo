package com.manufacture.identityservice.service;

import com.manufacture.identityservice.dto.SignUpRequest;
import com.manufacture.identityservice.entity.Role;
import com.manufacture.identityservice.entity.RoleName;
import com.manufacture.identityservice.entity.User;
import com.manufacture.identityservice.exception.AppException;
import com.manufacture.identityservice.repository.RoleRepository;
import com.manufacture.identityservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;

    public String saveUser(SignUpRequest signUpRequest) {

        User user = new User();
        user.setName(signUpRequest.getName());
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        if (signUpRequest.getRole().equals("expert")) {
            Role userRole = roleRepository.findByName(RoleName.ROLE_EXPERT)
                    .orElseThrow(() -> new AppException("User Role not set."));

            user.setRoles(Collections.singleton(userRole));

        } else {
            Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new AppException("User Role not set."));

            user.setRoles(Collections.singleton(userRole));
        }

        userRepository.save(user);
        return "user added to the database";
    }

    public String generateToken(String username) {

        return jwtService.generateToken(username);

    }
    public void validateToken(String token) {

        jwtService.validateToken(token);
    }
}

package com.manufacture.expertservice.controller;

import com.manufacture.expertservice.exception.AppException;
import com.manufacture.expertservice.model.*;
import com.manufacture.expertservice.payload.ApiResponse;
import com.manufacture.expertservice.payload.JwtAuthenticationResponse;
import com.manufacture.expertservice.payload.LoginRequest;
import com.manufacture.expertservice.payload.SignUpRequest;
import com.manufacture.expertservice.repository.RoleRepository;
import com.manufacture.expertservice.repository.UserExpertRepository;
import com.manufacture.expertservice.repository.UserRepository;
import com.manufacture.expertservice.security.JwtTokenProvider;
import com.manufacture.expertservice.service.UserCompanyService;
import com.manufacture.expertservice.service.UserExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserExpertRepository userexpertRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserExpertService userExpertService;

    @Autowired
    UserCompanyService userCompanyService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(
        //  @Valid
        @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsernameOrEmail(),
                loginRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(
        //  @Valid
        @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
            signUpRequest.getEmail(), signUpRequest.getPassword());

        UserExpert expert = new UserExpert(signUpRequest.getName(), signUpRequest.getUsername(),
            signUpRequest.getEmail());

        UserCompany company = new UserCompany(signUpRequest.getName(), signUpRequest.getUsername(),
            signUpRequest.getEmail());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(signUpRequest.getRole());

        if (signUpRequest.getRole().equals("expert")) {
            Role userRole = roleRepository.findByName(RoleName.ROLE_EXPERT)
                .orElseThrow(() -> new AppException("User Role not set."));

            user.setRoles(Collections.singleton(userRole));


            userExpertService.addExpert(expert);
        } else {
            Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

            user.setRoles(Collections.singleton(userRole));
        }

        User result = userRepository.save(user);
        userCompanyService.addCompany(company);

        URI location = ServletUriComponentsBuilder
            .fromCurrentContextPath().path("/users/{username}")
            .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}

package com.manufacture.identityservice.controller;

import com.manufacture.identityservice.dto.AuthenticationRequest;
import com.manufacture.identityservice.dto.LoginRequest;
import com.manufacture.identityservice.dto.SignUpRequest;
import com.manufacture.identityservice.repository.UserRepository;
import com.manufacture.identityservice.service.AuthService;
import com.manufacture.identityservice.service.JwtAuthenticationResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Log4j2
@RestController
@Validated
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    AuthService authService;
    @Autowired
    UserRepository userRepository;

 /*   @Autowired
    UserExpertRepository userexpertRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserExpertServiceImpl userExpertService;
    @Autowired
    UserCompanyServiceImpl userCompanyService; */

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {

            log.warn("Username is already taken!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {

            log.warn("Email Address already in use!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        authService.saveUser(signUpRequest);

        log.info("User registered successfully, name : {}", signUpRequest.getName());

        return new ResponseEntity<>(signUpRequest.getName(), HttpStatus.CREATED);

      /*  UserExpert expert = new UserExpert(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail());

        UserCompany company = new UserCompany(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail()); */

      /*  if (signUpRequest.getRole().equals("expert")) {
            Role userRole = roleRepository.findByName(RoleName.ROLE_EXPERT)
                    .orElseThrow(() -> new AppException("User Role not set."));

            user.setRoles(Collections.singleton(userRole));
            userExpertService.addExpert(expert);

        } else {
            Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new AppException("User Role not set."));

            user.setRoles(Collections.singleton(userRole));
        } */


        //   userCompanyService.addCompany(company);

      /*  URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri(); */

    }
    @PostMapping("/token")
    public String getToken(@RequestBody AuthenticationRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return authService.generateToken(authRequest.getUsername());
        } else {
            throw new RuntimeException("invalid access");
        }
    }


    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (authentication.isAuthenticated()) {

         //   String jwt = authService.generateToken(authentication);
         //   return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
       return null;
        } else {
            throw new RuntimeException("invalid access");
        }

    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam("token") String token) {
        System.out.println("validating token " + token);
        authService.validateToken(token);
        return ResponseEntity.ok("Token is valid");

    }

}

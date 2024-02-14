package com.manufacture.expertservice.controller;


import com.manufacture.expertservice.exception.ResourceNotFoundException;
import com.manufacture.expertservice.model.User;
import com.manufacture.expertservice.payload.UserIdentityAvailability;
import com.manufacture.expertservice.payload.UserProfile;
import com.manufacture.expertservice.payload.UserSummary;
import com.manufacture.expertservice.repository.UserRepository;
import com.manufacture.expertservice.security.CurrentUser;
import com.manufacture.expertservice.security.UserPrincipal;
import com.manufacture.expertservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/me")
    //  @PreAuthorize("hasRole('USER')")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_EXPERT' ,'ROLE_ADMIN')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));


        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt(), user.getRoles());

        return userProfile;
    }

    /*  @GetMapping("/users/experts")
      public List<User> findAllExperts(){
          return userRepository.findAllExperts();
      } */
    @Autowired
    private UserService userService;

    @GetMapping("/users/experts")
    public List<User> findAllExperts() {
        return userService.findAllExperts();
    }


}

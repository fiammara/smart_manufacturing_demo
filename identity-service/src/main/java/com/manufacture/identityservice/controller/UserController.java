package com.manufacture.identityservice.controller;


import com.manufacture.identityservice.config.CustomUserDetails;
import com.manufacture.identityservice.dto.CurrentUser;
import com.manufacture.identityservice.dto.UserProfile;
import com.manufacture.identityservice.dto.UserResponseDTO;
import com.manufacture.identityservice.dto.UserSummary;
import com.manufacture.identityservice.entity.User;
import com.manufacture.identityservice.exception.ResourceNotFoundException;
import com.manufacture.identityservice.mappers.UserMapper;
import com.manufacture.identityservice.repository.UserRepository;
import com.manufacture.identityservice.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@Log4j2
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    UserMapper userMapper;


    @GetMapping("/user/me")

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_EXPERT' ,'ROLE_ADMIN')")
    public UserSummary getCurrentUser(@CurrentUser CustomUserDetails currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }


    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getRoles());

        return userProfile;
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable(value = "id") String id) {

        log.info("Find user by passing id, where id is :{} ", id);

        Optional<UserResponseDTO> user = userService.getUserById(Long.valueOf(id))
                .flatMap(u -> Optional.ofNullable(userMapper.userDAOToUserDTO(u)));
        if (!user.isPresent()) {
            log.warn("User with id {} is not found.", id);
        } else {
            log.debug("User with id {} is found:", id);
        }
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

}

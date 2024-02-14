package com.manufacture.expertservice.service;

import com.manufacture.expertservice.model.RoleName;
import com.manufacture.expertservice.model.User;
import com.manufacture.expertservice.model.UserExpert;
import com.manufacture.expertservice.repository.RoleRepository;
import com.manufacture.expertservice.repository.UserExpertRepository;
import com.manufacture.expertservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserExpertService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserExpertRepository userexpertRepository;


    public List<User> findAllExperts() {

        return userRepository.findByRoles_(roleRepository.findByName(RoleName.ROLE_EXPERT));
    }

    public void addExpert(UserExpert exp) {

        userexpertRepository.save(exp);

    }

}

package com.manufacture.expertservice.service;

import com.manufacture.expertservice.model.User;
import com.manufacture.expertservice.model.UserExpert;

import java.util.List;

public interface UserExpertService {
    List<User> findAllExperts();

    void addExpert(UserExpert exp);
}

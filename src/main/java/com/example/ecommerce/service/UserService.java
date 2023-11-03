package com.example.ecommerce.service;

import com.example.ecommerce.dto.UserRegisterRequest;
import com.example.ecommerce.model.User;

public interface UserService {
    Integer register(UserRegisterRequest userRegisterRequest);
    User getUserById(Integer userId);
}

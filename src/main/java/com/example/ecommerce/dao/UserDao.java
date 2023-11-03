package com.example.ecommerce.dao;

import com.example.ecommerce.dto.UserRegisterRequest;
import com.example.ecommerce.model.User;

public interface UserDao {
    Integer createUser(UserRegisterRequest userRegisterRequest);
    User getUserById(Integer userId);
}

package com.example.ecommerce.service.impl;

import com.example.ecommerce.dao.UserDao;
import com.example.ecommerce.dto.UserRegisterRequest;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userdao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userdao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userdao.getUserById(userId);
    }
}
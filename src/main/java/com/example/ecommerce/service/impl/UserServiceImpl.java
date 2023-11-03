package com.example.ecommerce.service.impl;

import com.example.ecommerce.dao.UserDao;
import com.example.ecommerce.dto.UserRegisterRequest;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userdao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        //檢查註冊的email是否存在
        User user = userdao.getUserByEmail(userRegisterRequest.getEmail());
        if (user != null){
            log.warn("該email{}已經被註冊",userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        //創建帳號
        return userdao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userdao.getUserById(userId);
    }
}
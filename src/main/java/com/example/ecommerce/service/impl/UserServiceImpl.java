package com.example.ecommerce.service.impl;

import com.example.ecommerce.dao.UserDao;
import com.example.ecommerce.dto.UserLoginRequest;
import com.example.ecommerce.dto.UserRegisterRequest;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
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
        //使用md5生成密碼的雜湊值
        String hashedPassword = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());
        userRegisterRequest.setPassword(hashedPassword);
        //創建帳號
        return userdao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userdao.getUserById(userId);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userdao.getUserByEmail(userLoginRequest.getEmail());
        //檢查user是否存在
        if (user==null){
            log.warn("該Email{}尚未註冊！",userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        //使用md5生成密碼的雜湊值
        String hashedpassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());
        //比較密碼
        if (user.getPassword().equals(hashedpassword)){
            return user;
        }else {
            log.warn("email{}的密碼錯誤",userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
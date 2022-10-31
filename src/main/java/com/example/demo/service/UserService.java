package com.example.demo.service;

import com.example.demo.controller.UserController;
import com.example.demo.dao.RoleDAO;
import com.example.demo.dao.UserDAO;
import com.example.demo.dto.RoleInfo;
import com.example.demo.dto.UserInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final Logger logger = LogManager.getLogger(UserService.class);
    private final UserDAO userDAO;

    @Autowired
    public UserService(@Qualifier("userDAO") UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public String registerUser(UserInfo userInfo) {
        List<UserInfo> list = this.userDAO.getUsers(null);
        if (list.size() == 0) {
            userInfo.setUserRole("ROLE_ADMIN");
        }
        userInfo.setUserPassword(new BCryptPasswordEncoder().encode(userInfo.getUserPassword()));
        try {
            return this.userDAO.insertUser(userInfo) == 0 ? "계정 생성 실패" : null;
        } catch (Exception e) {
            logger.error(e.getMessage() + " " + e.getCause());
            return "계정 생성 실패";
        }
    }

    public List<UserInfo> getUsers(UserInfo userInfo) {
        return this.userDAO.getUsers(userInfo);
    }
}

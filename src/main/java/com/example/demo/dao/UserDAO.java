package com.example.demo.dao;

import com.example.demo.dto.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDAO {
    public int insertUser(UserInfo userInfo);

    public List<UserInfo> getUsers(UserInfo userInfo);
}

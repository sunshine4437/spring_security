package com.example.demo.dao;

import com.example.demo.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//@Mapper
@Mapper
public interface UserDAO {
    public int insertUser(User user);

    public List<User> selectUserList(User user);
}

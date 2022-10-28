package com.example.demo.dao;

import com.example.demo.dto.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleDAO {
    public int insertUserRole(UserRole userRole);

    public List<UserRole> selectUserRoleList(UserRole userRole);
}

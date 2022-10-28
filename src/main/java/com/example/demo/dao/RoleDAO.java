package com.example.demo.dao;

import com.example.demo.dto.Audit;
import com.example.demo.dto.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleDAO {
    public int insertRole(Role role);

    public List<Role> selectRoleList(Role role);
}

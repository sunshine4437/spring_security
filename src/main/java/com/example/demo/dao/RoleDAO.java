package com.example.demo.dao;

import com.example.demo.dto.RoleInfo;
import com.example.demo.dto.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleDAO {

    public List<RoleInfo> getRoles(RoleInfo roleInfo);
}

package com.example.demo.security;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomUserDtailsDAO {
    public CustomUserDetails loadUserByUsername(String username);
}

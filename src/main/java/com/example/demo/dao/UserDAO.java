package com.example.demo.dao;

import com.example.demo.security.CustomUserDetails;
import org.springframework.stereotype.Repository;

//@Mapper
@Repository
public interface UserDAO {
    public CustomUserDetails getUserById(String id);
}

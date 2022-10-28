package com.example.demo.service;

import com.example.demo.dto.Role;
import com.example.demo.dto.User;
import com.example.demo.dto.UserRole;

import java.util.List;

public interface UserService {
    public List<User> getUserList(User user);

    public List<Role> getRoleList(Role role);

    public Role getAdminRole();

    public int addUser(User user, UserRole userRole);
}

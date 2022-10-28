package com.example.demo.service;

import com.example.demo.dao.RoleDAO;
import com.example.demo.dao.UserDAO;
import com.example.demo.dao.UserRoleDAO;
import com.example.demo.dto.Role;
import com.example.demo.dto.User;
import com.example.demo.dto.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final UserRoleDAO userRoleDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO, UserRoleDAO userRoleDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.userRoleDAO = userRoleDAO;
    }

    @Override
    public List<User> getUserList(User user) {
        return this.userDAO.selectUserList(user);
    }

    @Override
    public List<Role> getRoleList(Role role) {
        return this.roleDAO.selectRoleList(role);
    }

    @Override
    public Role getAdminRole() {
        Role role = new Role();
        role.setRole("ROLE_ADMIN");
        try {
            return this.roleDAO.selectRoleList(role).get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int addUser(User user, UserRole userRole) {
        this.userDAO.insertUser(user);
        this.userRoleDAO.insertUserRole(userRole);
        return 1;
    }
}

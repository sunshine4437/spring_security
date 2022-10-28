package com.example.demo.security;

import com.example.demo.dao.UserDAO;
import com.example.demo.dao.UserRoleDAO;
import com.example.demo.dto.Role;
import com.example.demo.dto.User;
import com.example.demo.dto.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDAO userDAO;
    private final UserRoleDAO userRoleDAO;

    @Autowired
    public CustomUserDetailsService(UserDAO userDAO, UserRoleDAO userRoleDAO) {
        this.userDAO = userDAO;
        this.userRoleDAO = userRoleDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User _user = new User();
            _user.setUserId(username);
            _user = userDAO.selectUserList(_user).get(0);
            UserRole _userRole = new UserRole();
            _userRole.setUserId(username);
            _userRole = userRoleDAO.selectUserRoleList(_userRole).get(0);

            return new CustomUserDetails(username, _user.getUserPassword(), _user.getUserName(), _userRole.getRole());
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            return null;
        }
    }
}

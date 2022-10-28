package com.example.demo.controller;

import com.example.demo.dto.Role;
import com.example.demo.dto.User;
import com.example.demo.dto.UserRole;
import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
//@RequestMapping(value = "/")
public class UserController {
    private final Logger logger = LogManager.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(@Qualifier("userServiceImpl") UserServiceImpl userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @GetMapping(value = "/index")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        return "index";
    }

    @GetMapping(value = "/error-test")
    public String error() {
        throw new RuntimeException();
    }

    @GetMapping(value = "/sign-up")
    public String signUp(Model model) {
        if (this.userService.getUserList(null).size() == 0) {
            model.addAttribute("msg", "Register administrator account");
        }
        return "auth/sign-up";
    }

    @GetMapping(value = "/sign-in")
    public String signIn() {

        return "auth/sign-in";
    }

    @PostMapping(value = "/user")
    @ResponseBody
    @Transactional
    public String registerUser(@RequestBody User user) {
        user.setUserPassword(new BCryptPasswordEncoder().encode(user.getUserPassword()));

        if (this.userService.getUserList(null).size() == 0) {
            Role adminRole = this.userService.getAdminRole();
            UserRole userRole = new UserRole(user.getUserId(), adminRole.getRole());
            this.userService.addUser(user, userRole);
        }
        return "";
    }
}

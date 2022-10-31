package com.example.demo.controller;

import com.example.demo.dto.UserInfo;
import com.example.demo.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.parameters.P;
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
import java.util.List;

@Controller
//@RequestMapping(value = "/")
public class UserController {
    private final Logger logger = LogManager.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(@Qualifier("userService") UserService userService) {
        this.userService = userService;
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
        List<UserInfo> list = this.userService.getUsers(null);
        if (list.size() == 0) {
            model.addAttribute("msg", "관리자 계정 등록");
        }
        return "auth/sign-up";
    }

    @GetMapping(value = "/sign-in")
    public String signIn() {

        return "auth/sign-in";
    }

    @PostMapping(value = "/user")
    @ResponseBody
    public String registerUser(@RequestBody UserInfo user) {
        return this.userService.registerUser(user);
    }
}

package com.example.demo.controller;

import com.example.demo.service.AuditService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
//@RequestMapping(value = "/")
public class TestController {
    private final Logger logger = LogManager.getLogger(TestController.class);

    @GetMapping(value = "/index")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        return "index";
    }

    @GetMapping(value = "/error-test")
    public String error() {
        throw new RuntimeException();
    }

    @GetMapping(value = "/sign-up")
    public String signUp() {
        return "auth/sign-up";
    }

    @GetMapping(value = "/sign-in")
    public String signIn() {
        return "auth/sign-in";
    }

}

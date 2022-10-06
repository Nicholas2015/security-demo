package com.example.securitydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Nicholas Sun
 * @date 2022/10/06 19:21
 */
@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home.html";
    }
}

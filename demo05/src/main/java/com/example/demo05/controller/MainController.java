package com.example.demo05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Nicholas Sun
 * @date 2022/10/16 17:35
 */
@Controller
public class MainController {


    @GetMapping("/main")
    public String main() {
        return "main.html";
    }
}

package com.example.demo02.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nicholas Sun
 * @date 2022/10/09 22:48
 */
@RestController
public class HelloController {


    @GetMapping("/hello")
    public String hello() {
        return "Hello!";
    }
}

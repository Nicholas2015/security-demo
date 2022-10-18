package com.example.demo05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.logging.Logger;

/**
 * @author Nicholas Sun
 * @date 2022/10/16 17:35
 */
@Controller
public class MainController {

    private Logger logger = Logger.getLogger(MainController.class.getName());

    @GetMapping("/main")
    public String main() {
        return "main.html";
    }

    @PostMapping("/test")
    @ResponseBody
//    @CrossOrigin(origins = {"http://localhost:8080" })
    public String test() {
        logger.info("Test method called");
        return "HELLO";
    }
}

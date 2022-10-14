package com.example.demo03.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @GetMapping("/email/{mail}")
    public String email(@PathVariable("mail") String mail){
        return "Allowed for email: " + mail;
    }
}

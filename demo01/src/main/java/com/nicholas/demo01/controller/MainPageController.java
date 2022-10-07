package com.nicholas.demo01.controller;

import com.nicholas.demo01.service.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

/**
 * @author Nicholas Sun
 * @date 2022/10/07 22:34
 */
@Controller
public class MainPageController {

    @Resource
    private ProductService productService;

    @GetMapping("/main")
    public String main(Authentication authentication, Model model){
        model.addAttribute("username",authentication.getName());
        model.addAttribute("products",productService.findAll());
        return "main.html";
    }
}

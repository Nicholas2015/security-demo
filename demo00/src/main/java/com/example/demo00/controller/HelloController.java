package com.example.demo00.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Nicholas Sun
 * @date 2022/09/24 21:10
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(Authentication authentication) {
//        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication authentication = context.getAuthentication();
        String username = authentication.getName();
        return String.format("Hello %s",username);
    }

    @GetMapping("/bye")
    @Async
    public void goodbye(){
//        SecurityContextHolder.MODE_THREADLOCAL
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String name = authentication.getName();
        System.out.println(name);
    }

    @GetMapping("/ciao")
    public String ciao() throws Exception {
        Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };
        ExecutorService service = Executors.newCachedThreadPool();
        try {
//            return String.format("Ciao, %s!",service.submit(task).get());
            DelegatingSecurityContextCallable<String> callable = new DelegatingSecurityContextCallable<>(task);
            return String.format("Ciao, %s!",service.submit(callable).get());
        }finally {
            service.shutdown();
        }
    }

    @GetMapping("/hola")
    public String hola() throws Exception {
        Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };
        ExecutorService service = Executors.newCachedThreadPool();
        service = new DelegatingSecurityContextExecutorService(service);
        try {
            return String.format("Hola, %s!",service.submit(task).get());
        }finally {
            service.shutdown();
        }
    }
}

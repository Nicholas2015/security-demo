package com.example.demo00.model;

import lombok.Data;

/**
 * @author Nicholas Sun
 * @date 2022/09/28 22:03
 */
@Data
public class User {

    private int id;

    private String username;

    private String password;

    private String authority;
}

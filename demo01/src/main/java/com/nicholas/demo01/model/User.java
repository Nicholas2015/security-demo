package com.nicholas.demo01.model;

import com.nicholas.demo01.constant.EncryptionAlgorithm;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private EncryptionAlgorithm algorithm;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Authority> authorities;
}

package com.nicholas.demo01.model;

import com.nicholas.demo01.constant.Currency;
import lombok.Data;

import javax.persistence.*;

/**
 * @author Nicholas Sun
 * @date 2022/10/07 22:29
 */
@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private double price;

    @Enumerated(EnumType.STRING)
    private Currency currency;
}

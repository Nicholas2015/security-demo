package com.nicholas.demo01.service.impl;

import com.nicholas.demo01.model.Product;
import com.nicholas.demo01.repo.ProductRepository;
import com.nicholas.demo01.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Nicholas Sun
 * @date 2022/10/07 22:31
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}

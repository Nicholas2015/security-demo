package com.nicholas.demo01.repo;

import com.nicholas.demo01.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
}

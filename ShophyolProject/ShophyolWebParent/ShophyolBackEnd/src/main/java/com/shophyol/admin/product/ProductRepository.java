package com.shophyol.admin.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shophyol.common.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}

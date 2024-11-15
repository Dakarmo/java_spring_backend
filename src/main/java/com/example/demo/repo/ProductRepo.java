package com.example.demo.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

    Page<Product>findByNameContainingIgnoreCase(String productName, Pageable pageable);
}

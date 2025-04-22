package com.example.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.homework.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

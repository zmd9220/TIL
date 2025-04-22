package com.example.homework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.homework.model.Product;
import com.example.homework.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	// insert
	public String insertProduct(Product product) {
		productRepository.save(product);
		return "index";
	}
	
	// select *
	public List<Product> selectAllProduct() {
		return productRepository.findAll();
	}
}

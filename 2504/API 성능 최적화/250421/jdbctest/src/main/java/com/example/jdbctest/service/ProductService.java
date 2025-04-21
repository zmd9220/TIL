package com.example.jdbctest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jdbctest.model.Product;
import com.example.jdbctest.repository.ProductRepository;

@Service
public class ProductService {
	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	// 서비스에서 Repo(Dao) 호출
	public int addProduct(String name, Double price) {
		int cnt = productRepository.insertProduct(name, price);
		return cnt;
	}
	
	// SELECT
	public List<Product> getAllProducts() {
		List<Product> arr = productRepository.getAllProducts();
		return arr;
	}
	
}

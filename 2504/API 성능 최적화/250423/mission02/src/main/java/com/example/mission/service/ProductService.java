package com.example.mission.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mission.model.Product;
import com.example.mission.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}
	
	
	public List<Product> getProductList() {
		
		return productRepository.findAll();
	}
	
	public Product saveProduct(Product product) {
		
		return productRepository.save(product);
	}
	
	public long getCount() {
		return productRepository.count();
	}
}

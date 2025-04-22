package com.example.homework.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.homework.model.Product;
import com.example.homework.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
//	@PostMapping
//	public void insertProduct(@RequestBody Product product) {
//		productService.insertProduct(product);
//	}
	
	@PostMapping
	public void insertProduct(@RequestParam String name, @RequestParam int cnt, @RequestParam String mkDate) {
		productService.insertProduct(new Product(null, name, cnt, mkDate));
	}
	
	@GetMapping
	public List<Product> selectAllProduct(Model model) {
		return productService.selectAllProduct();
	}
}

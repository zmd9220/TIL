package com.example.jdbctest.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.jdbctest.model.Product;
import com.example.jdbctest.service.ProductService;

@Controller
public class ProductController {
	private final ProductService productService;
	
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}


	@GetMapping("/product")
	@ResponseBody // response에서 바로 담아서 보냄
 	public String product(Model model) {
		return "product call";
	}
	
	@GetMapping("/insert")
	public String insert(Model model) {
		
		// 오류 발생시 -1 리턴
		int nCnt = productService.addProduct("컴퓨터", 20.2);
		String result = nCnt>0? "insert success" : "insert fail";
		model.addAttribute("result", result+":"+nCnt);
		return "insert";
	}
	
	@GetMapping("/form")
	public String form(Model model) {
		return "form";
	}
	
	@GetMapping("/formInsert")
	public String formInsert(@RequestParam("name") String name, 
			@RequestParam("price") Double price,
			Model model) {
		int nCnt = productService.addProduct(name, price);
		String result = nCnt>0? "insert success" : "insert fail";
		model.addAttribute("result", result+" : "+nCnt);
		return "formInsert";
	}
	
	@GetMapping("/view")
	public String view(Model model) {
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		return "view";
	}
}

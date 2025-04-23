package com.example.mission.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mission.model.Product;
import com.example.mission.service.ProductService;

@Controller
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@GetMapping
	public String index() {
		return "index";
	}

	@GetMapping("/main")
	public String main() {
		return "main";
	}

	@GetMapping("/insertform")
	public String insertForm() {

		return "insertform";
	}

	@GetMapping("/insertproduct")
	@ResponseBody
	public String insertProduct(
			Model model
			, @RequestParam String productName
			, @RequestParam int productCount
			, @RequestParam String produceDate
			) {

		String result = "";
		Product product = new Product(null, productName, productCount, produceDate);
		long before = productService.getCount();
		productService.saveProduct(product);

		if(productService.getCount() - before > 0) {
			result = "추가 성공";
		} else {
			result = "추가 실패";
		}
//		model.addAttribute("product", product);
//		model.addAttribute("result", result);
		return "추가성공";
	}

	@GetMapping("/list")
	public String getList(Model model) {
		model.addAttribute("products", productService.getProductList());
		return "list";
	}

}

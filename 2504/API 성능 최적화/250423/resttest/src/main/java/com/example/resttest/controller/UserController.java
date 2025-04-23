package com.example.resttest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	@GetMapping
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("/form")
	public String form(Model model) {
		return "form";
	}
	
	@GetMapping("/personview")
	public String personview(Model model) {
		return "personview";
	}
	
	@GetMapping("/insert")
	public String insert(Model model) {
		return "insert";
	}
	
	@GetMapping("/update")
	public String update(Model model) {
		return "update";
	}
	
	@GetMapping("/delete")
	public String delete(Model model) {
		return "delete";
	}
}

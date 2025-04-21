package com.example.jdbctest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.jdbctest.my.MyClass;

@Controller
public class UserController {
//	@Autowired
//	MyClass obj;
	private final MyClass obj;
	
	public UserController(MyClass obj) {
		this.obj = obj;
	}

	@GetMapping("/")
	public String index(Model model) {
//		MyClass obj = new MyClass();
		String rst = obj.hello();
		System.out.println(rst);
		return "index";
	}
}

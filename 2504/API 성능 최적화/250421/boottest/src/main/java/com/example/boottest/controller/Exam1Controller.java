package com.example.boottest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Exam1Controller {
	
	@GetMapping("/fatForm")
	public String fatForm(Model model) {
		return "fatForm";
	}
	
	@GetMapping("/fatFormResult")
	public String fatFormResult(@RequestParam("height") int height,
			@RequestParam("weight") int weight,
			Model model) {
		
		Double normalWeight = (height - 100) * 0.85;
		Double weightSize = weight/normalWeight*100;
		
		model.addAttribute("weightSize", weightSize);
		model.addAttribute("height", height);
		model.addAttribute("weight", weight);
		
		return "fatFormResult";
	}
}

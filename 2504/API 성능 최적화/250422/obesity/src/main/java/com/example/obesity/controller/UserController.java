package com.example.obesity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.obesity.calc.ObesityCalc;

@Controller
public class UserController {
	
	@Autowired
	ObesityCalc ocalc; // 빈 생성시 자동으로 생성 (생성자)
	
	@GetMapping
	public String index (Model model) {
		return "index";
	}
	
	@GetMapping("/form")
	public String form (Model model) {
		return "form"; // 기본 위치 resources/templates
	}
	
	@GetMapping("/formResult")
	public String formResult (@RequestParam("height") double height,
			@RequestParam("weight") double weight,
			Model model) {
		
//		double stdWeight = (height - 100) * 0.85;
//		double obesity = weight / stdWeight * 100; 
//		String result = "";
//		String imgSrc = "";
//		if (obesity<=90) {
//			result = "저체중";
//			imgSrc = "/images/under.png";
//		}
//		else if (obesity <= 110) {
//			result = "정상";
//			imgSrc = "/images/normal.png";
//		}
//		else if (obesity <= 120) {
//			result = "과제중";
//			imgSrc = "/images/obese.png";
//		}
//		else {
//			result = "비만";
//			imgSrc = "/images/over.png";
//		}
		
		ocalc.calcObesity(height, weight);
		
		model.addAttribute("height", height);
		model.addAttribute("weight", weight);
//		model.addAttribute("result", result);
//		model.addAttribute("imgSrc", imgSrc);
		model.addAttribute("result", ocalc.getResult());
		model.addAttribute("imgSrc", ocalc.getImgSrc());
		
		return "formResult";
	}
	
	@GetMapping("/formtest")
	public String formtest (Model model) {
		return "formtest"; // 기본 위치 resources/templates
	}
}

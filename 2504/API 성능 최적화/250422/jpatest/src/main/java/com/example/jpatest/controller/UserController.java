package com.example.jpatest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.jpatest.model.Person;
import com.example.jpatest.service.PersonService;

@Controller
public class UserController {
	
	@Autowired
	PersonService personService;
	
	@GetMapping
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("/form")
	public String form(Model model) {
		return "form";
	}
	
	@GetMapping("/insert")
	public String insert(@RequestParam("name") String name,
			@RequestParam("age") int age, Model model) {
		Long nCnt;
		nCnt = personService.insertPerson(name, age);
		model.addAttribute("result", nCnt+"추가됨");
		return "insertview";
	}
	
	@GetMapping("/select")
	public String select(Model model) {
		model.addAttribute("persons", personService.selectAll());
		return "selectview";
	}
	
	@GetMapping("/selectname")
	public String selectname(Model model) {
		Person person = personService.selectByName("하하");
		model.addAttribute("name", person.getName());
		model.addAttribute("age", person.getAge());
		return "findnameview";
	}
	
	@GetMapping("/selector")
	public String selector(Model model) {
		model.addAttribute("persons", personService.selectByNameOrAge(20, "하하"));
		return "selectview";
	}
	
	@GetMapping("/delete")
	public String delete(Model model) {
		model.addAttribute("result", personService.deletePerson(1));
		return "insertview";
	}
	
	@GetMapping("/update")
	public String update(Model model) {
		model.addAttribute("result", personService.updatePerson("하하", "하하1", 25));
		return "insertview";
	}
}

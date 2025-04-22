package com.example.resttest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.resttest.model.Person;
import com.example.resttest.service.PersonService;

@RestController
@RequestMapping("api/persons") // REST controller 
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	@GetMapping
	public List<Person> getAllPersons(){
		return personService.getAllPersons();
	}
	
	@PostMapping // Post는 요청데이터가 Body에 담아오므로 RequestBody를 사용
	public Person insertPerson(@RequestBody Person person) {
		return personService.savePerson(person);
	}
//	@PostMapping // Post는 요청데이터가 Body에 담아오므로 RequestBody를 사용
//	public Person insertPerson(@RequestParam String name, @RequestParam int age) {
//		return personService.savePerson(new Person(null, name, age));
//	}
}

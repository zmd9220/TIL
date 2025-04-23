package com.example.resttest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/{id}") // {} - 값이 유동적
	public Optional<Person> getPersonById(@PathVariable Long id){ // optional 해당 값이 없을때, null을 자동으로 처리하기 위해
		return personService.getPersonById(id);
	}
	
	@PostMapping // Post는 요청데이터가 Body에 담아오므로 RequestBody를 사용
	public Person insertPerson(@RequestBody Person person) {
		return personService.savePerson(person);
	}
//	@PostMapping // Post는 요청데이터가 Body에 담아오므로 RequestBody를 사용, form으로 받는다면 RequestParam으로 받아야함 (별도 body가 없음)
//	public Person insertPerson(@RequestParam String name, @RequestParam int age) {
//		return personService.savePerson(new Person(null, name, age));
//	}
	
	@PutMapping("/{id}")
	public Person updatePerson(@PathVariable Long id, @RequestBody Person person) {
		person.setId(id);
		return personService.savePerson(person);
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public String deletePerson(@PathVariable Long id) {
		personService.deletePerson(id);
		return "<h3>삭제됨</h3>";
	}
}

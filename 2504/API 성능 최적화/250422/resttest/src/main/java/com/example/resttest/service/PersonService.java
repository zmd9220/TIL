package com.example.resttest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.resttest.model.Person;
import com.example.resttest.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	// select all
	public List<Person> getAllPersons(){
		return personRepository.findAll();
	}
	
	// insert
	public Person savePerson(Person person) {
		return personRepository.save(person);
	}
}

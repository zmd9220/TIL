package com.example.resttest.service;

import java.util.List;
import java.util.Optional;

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
	
	// select by Id
	public Optional<Person> getPersonById(Long id){
		return personRepository.findById(id);
	}
	
	// insert
	public Person savePerson(Person person) {
		return personRepository.save(person);
	}
	
	public void deletePerson(Long id) {
		personRepository.deleteById(id);
	}
}

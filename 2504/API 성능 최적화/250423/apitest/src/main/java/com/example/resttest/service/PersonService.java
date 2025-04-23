package com.example.resttest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.resttest.model.Person;
import com.example.resttest.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Cacheable("persons")
	// select all
	public List<Person> getAllPersons(){
		return personRepository.findAll();
	}
	
	// select by Id
	public Optional<Person> getPersonById(Long id){
		return personRepository.findById(id);
	}
	
	@CacheEvict(value = "persons", allEntries = true)
	// insert
	public Person savePerson(Person person) {
		return personRepository.save(person);
	}
	
	@CacheEvict(value = "persons", allEntries = true)
	public void deletePerson(Long id) {
		personRepository.deleteById(id);
	}
}

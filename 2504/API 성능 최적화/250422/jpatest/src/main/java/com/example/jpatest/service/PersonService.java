package com.example.jpatest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jpatest.model.Person;
import com.example.jpatest.repository.PersonRepository;

@Service
public class PersonService {
	@Autowired
	private PersonRepository personRepository;
	
	// 테이블 row 숫자
	public Long PersonCount() {
		Long nCnt = personRepository.count();
		return nCnt;
	}
	
	// insert
	public Long insertPerson(String name, int age) {
		Long bCnt = personRepository.count();
		// save 함수는 별도의 변경된 갯수 리턴이 없으므로 cnt를 통해 반영 된 갯수 확인해야함 
		personRepository.save(new Person(null, name, age));
		Long aCnt = personRepository.count();
		return aCnt - bCnt;
	}
	
	// select
	public List<Person> selectAll() {
		return personRepository.findAll();
	}
	
	// select by name
	public Person selectByName(String name) {
		return personRepository.findByName(name);
	}
	
	// select by name or age JPQL
	public List<Person> selectByNameOrAge(int age, String name) {
		return personRepository.getByAgeOrName(age, name);
	}
	
	// delete
	public String deletePerson(long id) {
		Person personDelete = personRepository.findById(id).orElse(null); // 해당 id가 있으면 person을, 없으면 null 리턴
		if(personDelete != null) {
			personRepository.delete(personDelete);
			return "삭제됨";
		} else
			return "삭제대상없음";
	}
	
	// update
	public String updatePerson(String oldName, String newName, int newAge) {
		Person personUpdate = personRepository.findByName(oldName);
		personUpdate.setName(newName);
		personUpdate.setAge(newAge);
		personRepository.save(personUpdate);
		return "수정됨";
	}
}
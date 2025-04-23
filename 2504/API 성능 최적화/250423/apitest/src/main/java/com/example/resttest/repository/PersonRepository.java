package com.example.resttest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.resttest.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}

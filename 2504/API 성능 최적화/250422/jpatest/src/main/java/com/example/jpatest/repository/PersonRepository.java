package com.example.jpatest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.jpatest.model.Person;

@Repository
// Person 모델(테이블)을 사용하는 키(id) long 타입을 기반으로 한 Jpa 기본 구현체를 상속받아 신규 인터페이스 생성
public interface PersonRepository extends JpaRepository<Person, Long>{
	// findBy는 꼭 값을 1개만 리턴하므로 PK로 1개만 구분 할수 있도록 해야함
	// findByName (특정 이름을 기준으로 한 find 함수 만들기) 인터페이스 이므로 함수는 자동 생성됨
	// 구현체를 별도로 만들지 않아도 해당 이름을 기반으로 jpa가 자동으로 쿼리 생성. 그러므로 변수명을 만드는게 상당히 중요함 (이름 구조 대로 쿼리를 만들기 때문에)
	// 메서드 이름 기반 쿼리생성 find + By + Name
	// select * from person where name=?
	// findByAgeGreaterThan = select * from person where age>?
	// findByNameAndAge = select * from person where name=? and age = ?
	// findByNameOrAge = select * from person where name=? or age = ?
	// findByNameLike = where name like ?
	// findByAgeBetween(int start, int end) = where age between start and end 
	// findByAgeIn = where age in(?)
	// findAllByName = 
	// findAllByOrderByNameAsc
	Person findByName(String name);
	
	// JPQL을 사용한 문법
	@Query("SELECT p FROM Person p WHERE p.age = :age OR p.name = :name") // SELECT * FROM Person WHERE p.age = ? OR p.name = ?
	List<Person> getByAgeOrName(@Param("age") int age, @Param("name") String name);
}

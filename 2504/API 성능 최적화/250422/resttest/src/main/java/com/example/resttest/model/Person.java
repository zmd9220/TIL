package com.example.resttest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Entity 테이블과 바로 매핑 (없으면 자동 생성)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
	// 행을 구분하는 PK 반드시 필요 (테이블 생성을 위해) 별도 구분자가 없을 경우 row 갯수 마다 자동 증가하는 ID를 키로 쓰면 됨	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private int age;
}

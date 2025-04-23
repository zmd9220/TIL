package com.example.resttest.model;

import java.util.List;

import org.hibernate.annotations.BatchSize;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="orders") // Order 테이블은 중복될 수 있어서 이름 바꿈
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	// 행을 구분하는 PK 반드시 필요 (테이블 생성을 위해) 별도 구분자가 없을 경우 row 갯수 마다 자동 증가하는 ID를 키로 쓰면 됨	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String productName;
	private double price;
	
	@ManyToOne(fetch = FetchType.LAZY) // 여러 주문은 한 사람에게 적용 될 수 있음
	@BatchSize(size = 10)
	@JoinColumn(name = "person_id")
	private Person person;
}

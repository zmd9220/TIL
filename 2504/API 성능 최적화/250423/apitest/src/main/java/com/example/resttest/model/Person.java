package com.example.resttest.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.BatchSize;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
	// 배치 사이즈: 20
	// Hibernate는 내부적으로 다음과 같은 식으로 묶음 그룹을 만들어 둡니다:
	// 1 ~ 20번 -> 첫 번째 IN 쿼리
	// 21 ~ 40번 -> 두 번째 IN 쿼리
	// 41 ~ 60번 -> 세 번째 IN 쿼리 <- 60번 포함됨
	// 61 ~ 80번 -> 네 번째 IN 쿼리
	// 81 ~ 100번 -> 다섯 번째 IN 쿼리	
	@OneToMany(mappedBy = "person", fetch = FetchType.LAZY) // 1대다 관계 사람 한 명에 여러 개 주문이 있을 수 있음, 필요할 때만 주문 목록을 조회해서 성능 최적화 - 지연 로딩
	@BatchSize(size=10) // N+1문제를 줄이기 위해 일괄 조회할 때 한 번에 최대 10개까지 가져옴
	private List<Order> orders = new ArrayList<Order>();
}

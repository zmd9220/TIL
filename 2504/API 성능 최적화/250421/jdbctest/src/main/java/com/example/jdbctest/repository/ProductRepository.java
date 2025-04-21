package com.example.jdbctest.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.jdbctest.model.Product;

// 
@Repository
public class ProductRepository {
	private final JdbcTemplate jdbcTemplate; // 관리객체로 생성

	public ProductRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	// INSERT
	public int insertProduct(String name, Double price) {
		// sql 문 작성
		String sql = "INSERT INTO products(name, price) VALUES(?,?)";
		// jdbc를 통한 반영 결과 값 리턴 (업데이트 갯수)
		int cnt = jdbcTemplate.update(sql, name, price);
		return cnt;
	}
	
	// SELECT
	public List<Product> getAllProducts(){
		String sql = "SELECT * FROM products";
		// lambda 함수로 데이터 결과 row 한 줄씩 매핑해서 집어넣기
		RowMapper<Product> rowMapper = (rs, rowNum) -> new Product(
				rs.getString("name"),
				rs.getDouble("price"));
		
		List<Product> arr = jdbcTemplate.query(sql, rowMapper);
		return arr;
	}
	
}

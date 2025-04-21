package com.example.jdbctest.model;

public class Product {
	private String name;
	public String getName() {
		return name;
	}
	public Product() {
		// TODO Auto-generated constructor stub
	}
	public Product(String name, Double price) {
		super();
		this.name = name;
		this.price = price;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	private Double price;
}

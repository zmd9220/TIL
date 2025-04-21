package com.example.boottest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//ALT+SHIFT+S 생성자 단축키 - 롬복 없을 때
//롬복 쓸때
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Movie {
	Long id;
	String poster;
	String title;
	String date;
	String actor;
	String director;

// 롬복 없이 만들기
//	public Movie(Long id, String poster, String title, String date, String actor, String director) {
//		super();
//		this.id = id;
//		this.poster = poster;
//		this.title = title;
//		this.date = date;
//		this.actor = actor;
//		this.director = director;
//	}
//	public Movie() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//	
//	// getter, setter 생성 (private 변수이므로 함수로 호출)
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//	public String getPoster() {
//		return poster;
//	}
//	public void setPoster(String poster) {
//		this.poster = poster;
//	}
//	public String getTitle() {
//		return title;
//	}
//	public void setTitle(String title) {
//		this.title = title;
//	}
//	public String getDate() {
//		return date;
//	}
//	public void setDate(String date) {
//		this.date = date;
//	}
//	public String getActor() {
//		return actor;
//	}
//	public void setActor(String actor) {
//		this.actor = actor;
//	}
//	public String getDirector() {
//		return director;
//	}
//	public void setDirector(String director) {
//		this.director = director;
//	}
	
}

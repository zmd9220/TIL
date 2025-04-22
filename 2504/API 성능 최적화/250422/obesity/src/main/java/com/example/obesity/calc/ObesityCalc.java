package com.example.obesity.calc;

import org.springframework.stereotype.Component;

@Component
public class ObesityCalc {

	private String result;
	private String imgSrc;
	
	public String getResult() {
		return result;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void calcObesity(double height, double weight) {
		double stdWeight = (height - 100) * 0.85;
		double obesity = weight / stdWeight * 100; 
		if (obesity<=90) {
			result = "저체중";
			imgSrc = "/images/under.png";
		}
		else if (obesity <= 110) {
			result = "정상";
			imgSrc = "/images/normal.png";
		}
		else if (obesity <= 120) {
			result = "과제중";
			imgSrc = "/images/obese.png";
		}
		else {
			result = "비만";
			imgSrc = "/images/over.png";
		}
	}
}

package kr.spring.ch18;

import javax.annotation.Resource; // 스프링에서 만든 클래스가 아닌 자바에서 만든 어노테이션을 사용

public class HomeController {
	
	// 빈의 이름과 프로퍼티명이 일치하면 의존관계 설정
	@Resource
	private Camera camera1;

	public void setCamera1(Camera camera1) {
		this.camera1 = camera1;
	}

	@Override
	public String toString() {
		return "HomeController [camera1=" + camera1 + "]";
	}
	
}

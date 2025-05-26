package kr.spring.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/main/main.do")
	public String viewMain() {
		// 타일스 설정명
		return "main"; // tiles 설정명임. main.jsp가 아님. tiles 설정이 없으면 main.jsp 실행 - main.xml에 설정
	}
	
}

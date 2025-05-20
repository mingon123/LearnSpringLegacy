package kr.spring.mvc01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // == 모델클래스
public class HelloController {
	// 요청 URL과 실행 메서드 연결
	@RequestMapping("/hello.do")
	public ModelAndView hello() {
		ModelAndView mav = new ModelAndView();
		// 뷰 이름 지정
		mav.setViewName("hello"); // 파일명만 작성-확장자X
		// 뷰에서 사용할 데이터 세팅
		mav.addObject("greeting","안녕하세요"); // request 에 저장
		
		return mav; // DispatherServlet에서 실행 및 반환
	}
}

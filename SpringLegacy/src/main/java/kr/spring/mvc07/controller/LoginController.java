package kr.spring.mvc07.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.mvc07.vo.LoginVO;

@Controller
public class LoginController {
	
	// 유효성 체크를 위한 자바빈 초기화
	@ModelAttribute // 이름을 정하지 않으면 loginVO로 저장
	public LoginVO initCommand() {
		return new LoginVO();
	}
	
	@GetMapping("/login/login.do")
	public String form() {
		return "login/form";
	}
	
	@PostMapping("/login/login.do")
	public String submit(@Valid LoginVO loginVO, BindingResult result) {
		
		System.out.println("전송된 데이터 : " + loginVO);
		
		// 유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return form(); // "login/form;" 중 골라서 하면됨 둘다 똑같음
		}		
		
		return "redirect:index.jsp";
	}
}

package kr.spring.mvc07.service;

import kr.spring.mvc07.vo.LoginVO;

public class LoginService {
	public void checkLogin(LoginVO loginVO) throws LoginCheckException{
		// 테스트용으로 아이디와 비밀번호가 일치하면 로그인 성공
		if(!loginVO.getUserId().equals(loginVO.getPassword())) {
			System.out.println("인증 에러 - " + loginVO.getUserId());
			throw new LoginCheckException();
		}
		
		
	}
}

package kr.spring.member.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.AuthCheckException;

@Controller
@RequestMapping("/member")
public class MemberUserController {
	private static final Logger log = LoggerFactory.getLogger(MemberUserController.class);
	
	@Autowired
	private MemberService memberService;
	
	// 자바빈(VO) 초기화
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}
	
	// 회원 등록 폼 호출
	@GetMapping("/registerUser.do")
	public String form() {
				// 타일스 설정명
		return "memberRegister";
	}
	
	// 회원 등록
	@PostMapping("/registerUser.do")
	public String submit(@Valid MemberVO memberVO, BindingResult result, Model model, HttpServletRequest request) { // model로 데이터 셋팅, request로 링크 이동
		
		log.debug("<<회원 등록 - MemberVO>> : " + memberVO);
		
		// 유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return form();
		}
		
		// 회원가입
		memberService.insertMember(memberVO);
		
		model.addAttribute("accessTitle", "회원가입");
		model.addAttribute("accessMsg", "회원가입이 정상적으로 완료되었습니다.");
		model.addAttribute("accessBtn", "홈으로");
		model.addAttribute("accessUrl", request.getContextPath()+"/main/main.do");
		
		return "common/resultView";
	}
	
	// 로그인 폼 호출
	@GetMapping("/login.do")
	public String loginForm() {
		return "memberLogin";
	}
	// 로그인 처리
	@PostMapping("/login.do")
	public String loginSubmit(@Valid MemberVO memberVO, BindingResult result, HttpSession session) {
		log.debug("<<로그인 - MemberVO>> : " + memberVO);
		
		// 유효성 체크 결과 오류가 있으면 폼 id와 passwd 필드만 체크
		if(result.hasFieldErrors("id") || result.hasFieldErrors("passwd")) {
			return loginForm();
		}
		
		// 로그인 체크(id, 비밀번호 일치 여부 체크)
		try {
			MemberVO member = memberService.selectCheckMember(memberVO.getId());
			boolean check = false;
			
			if(member!=null) {
				// 비밀번호 일치 여부 체크
				check = member.isCheckedPasswd(memberVO.getPasswd());
			}
			if(check) {
				// 인증 성공, 로그인 처리
				session.setAttribute("user", member);
				
				log.debug("<<인증 성공>>");
				log.debug("<<id>> : " + member.getId());
				log.debug("<<auth>> : " + member.getAuth());

				return "redirect:/main/main.do";
			}
			
			// 인증 실패
			throw new AuthCheckException();
			
		} catch (AuthCheckException e) {
			// 인증 실패로 로그인 폼 호출
			result.reject("invalidIdOrPassword");
			
			log.debug("<<인증 실패>>");
			
			return loginForm();
		}
	}
	// 로그아웃 처리
	@GetMapping("/logout.do")
	public String logout(HttpSession session) {
		// 로그아웃
		session.invalidate();
		return "redirect:/main/main.do";
	}
	
	// 회원 상세 정보
	@GetMapping("/myPage.do")
	public String getMyPage(HttpSession session, Model model) {
		MemberVO vo = (MemberVO)session.getAttribute("user");
		MemberVO member = memberService.selectMember(vo.getMem_num());
		log.debug("<<회원 상세 정보>> : " + member);
		
		model.addAttribute("member", member);		
		
		return "memberView";
	}
}

package kr.spring.member.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberAjaxController {
	private static final Logger log = LoggerFactory.getLogger(MemberAjaxController.class);
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/confirmId.do")
	@ResponseBody // map,list,자바빈로 만들때 데이터 타입을 맞춰줌
	public Map<String, String> confirmId(@RequestParam String id){ // 데이터가 전송되지 않으면 에러
		log.debug("<<아이디 중복 체크 - id>> : " + id);
		
		Map<String, String> mapAjax = new HashMap<String, String>();
		MemberVO member = memberService.selectCheckMember(id);
		
		if(member!=null) {
			// 아이디 중복
			mapAjax.put("result", "idDuplicated");
		}else {
			if(!Pattern.matches("^[A-Za-z0-9+]{4,12}$", id)) {
				// 패턴 불일치
				mapAjax.put("result", "notMatchPattern");
			}else {
				// 아이디 미중복
				mapAjax.put("result", "idNotFound");
			}
		}
		
		
		return mapAjax;
	}
	
}

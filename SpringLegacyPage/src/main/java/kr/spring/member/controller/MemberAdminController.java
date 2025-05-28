package kr.spring.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
@RequestMapping("/member") // 클래스 차원에서 중간 경로 + 메서드의 경로를 합쳐서 경로 완성
public class MemberAdminController {
	private static final Logger log = LoggerFactory.getLogger(MemberAdminController.class);
	
	@Autowired
	private MemberService memberService;
	
	// 회원 목록
	@GetMapping("/admin_list.do")
	public ModelAndView getList(@RequestParam(defaultValue="1") int pageNum, String keyfield, String keyword) {// String, MAV는 선택
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		// 총 레코드수 또는 검색 레코드수
		int count = memberService.selectRowCount(map);
		
		log.debug("<<회원목록 총레코드수>> : " + count);
		
		// 페이지 처리
		PagingUtil page = new PagingUtil(keyfield, keyword, pageNum, count, 20, 10, "admin_list.do");
		
		List<MemberVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			list = memberService.selectList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin_memberList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		
		return mav;
	}
	
	
	
	
	
	
	
}

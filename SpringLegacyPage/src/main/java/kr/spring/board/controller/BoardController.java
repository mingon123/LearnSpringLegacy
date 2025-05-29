package kr.spring.board.controller;

import java.io.IOException;

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

import kr.spring.board.service.BoardService;
import kr.spring.board.vo.BoardVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.FileUtil;

@Controller
@RequestMapping("/board")
public class BoardController {
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	// 자바빈(VO) 객체 초기화
	@ModelAttribute
	public BoardVO initCommand() {
		return new BoardVO();
	}
	
	// 글 등록 폼 호출
	@GetMapping("/write.do")
	public String form() {
		return "boardWrite";
	}
	
	// 글 등록하기
	@PostMapping("/write.do")
	public String submit(@Valid BoardVO boardVO, BindingResult result, HttpSession session, HttpServletRequest request, Model model)throws IllegalStateException, IOException {
		log.debug("<<게시판 글 저장>> : " + boardVO); 
		
		// 유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return form();
		}
		
		// 회원번호 셋팅
		boardVO.setMem_num(((MemberVO)session.getAttribute("user")).getMem_num()); // 일회성으로 사용하기 위해 변수에 담지 않고 이렇게 사용
		// IP 셋팅
		boardVO.setIp(request.getRemoteAddr());
		// 파일업로드
		boardVO.setFilename(FileUtil.createFile(request, boardVO.getUpload()));
		// 글쓰기
		boardService.InsertBoard(boardVO);
		
		model.addAttribute("message", "글 등록 완료");
		model.addAttribute("url", request.getContextPath()+"/board/list.do");
		
		return "common/resultAlert";
	}
	
	// 게시판 목록
	@GetMapping("/list.do")
	public String getList() {
		
		return "boardList";
	}
	
}

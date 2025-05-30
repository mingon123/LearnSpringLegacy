package kr.spring.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.board.service.BoardService;
import kr.spring.board.vo.BoardVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.FileUtil;

@Controller
@RequestMapping("/board")
public class BoardAjaxController {
	private static final Logger log = LoggerFactory.getLogger(BoardAjaxController.class);
	
	@Autowired
	private BoardService boardService;
	
	// 부모글 업로드 파일 삭제
	@GetMapping("/deleteFile.do")
	@ResponseBody
	public Map<String, String> deleteFile(long board_num, HttpServletRequest request, HttpSession session){
		Map<String, String> mapAjax = new HashMap<String, String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user==null) {
			mapAjax.put("result", "logout");
		}else {
			BoardVO db_board = boardService.selectBoard(board_num);
			// 로그인한 회원번호와 작성자 회원번호 일치 여부 체크
			if(user.getMem_num() != db_board.getMem_num()) {
				// 불일치
				mapAjax.put("result", "wrongAccess");
			}else {
				// 일치
				boardService.deleteFile(board_num);
				FileUtil.removeFile(request, db_board.getFilename());
				mapAjax.put("result", "success");
			}
		}
		
		return mapAjax;
	}
}

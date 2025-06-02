package kr.spring.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.board.service.BoardService;
import kr.spring.board.vo.BoardFavVO;
import kr.spring.board.vo.BoardReplyVO;
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
	@PostMapping("/deleteFile.do")
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
	
	// 부모글 좋아요 읽기
	@GetMapping("/getFav.do")
	@ResponseBody
	public Map<String, Object> getFav(BoardFavVO fav, HttpSession session){
		log.debug("<<게시판 좋아요>> : " + fav);
		
		Map<String, Object> mapAjax = new HashMap<String, Object>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");		
		if(user==null) {
			mapAjax.put("status", "noFav");
		}else {
			// 로그인한 회원번호 셋팅
			fav.setMem_num(user.getMem_num());
			
			BoardFavVO boardFav = boardService.selectFav(fav);
			if(boardFav!=null) {
				mapAjax.put("status", "yesFav");
			}else {
				mapAjax.put("status", "noFav");
			}
		}
		mapAjax.put("count", boardService.selectFavCount(fav.getBoard_num()));
		
		return mapAjax;
	}
	
	// 부모글 좋아요 등록/삭제
	@PostMapping("/writeFav.do")
	@ResponseBody
	public Map<String, Object> writeFav(BoardFavVO fav, HttpSession session){
		log.debug("<<부모글 좋아요 등록/삭제>> : " + fav);
		
		Map<String, Object> mapAjax = new HashMap<String, Object>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user==null) {
			mapAjax.put("result", "logout");
		}else {
			// 로그인한 회원번호 셋팅
			fav.setMem_num(user.getMem_num());
			
			BoardFavVO boardFav = boardService.selectFav(fav);
			if(boardFav!=null) {
				// 이미 등록되어 있으면 삭제
				boardService.deleteFav(fav);
				mapAjax.put("status", "noFav");
			}else {
				// 등록되어져 있지 않으면 등록
				boardService.insertFav(fav);
				mapAjax.put("status", "yesFav");
			}
			mapAjax.put("result", "success");
			mapAjax.put("count", boardService.selectFavCount(fav.getBoard_num()));
		}
		
		return mapAjax;
	}
	
	// 댓글 등록
	@PostMapping("/writeReply.do")
	@ResponseBody
	public Map<String, String> writeReply(BoardReplyVO vo, HttpSession session, HttpServletRequest request){
		log.debug("<<댓글 등록>> : " +vo);
		
		Map<String, String> mapAjax = new HashMap<String, String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user==null) {
			mapAjax.put("result", "logout");
		}else {
			// 회원번호 저장
			vo.setMem_num(user.getMem_num());
			// IP 등록
			vo.setRe_ip(request.getRemoteAddr());
			// 댓글 등록
			boardService.insertReply(vo);
			mapAjax.put("result", "success");
		}
		return mapAjax;
	}
	
	
	
}

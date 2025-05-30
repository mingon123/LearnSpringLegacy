package kr.spring.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.service.BoardService;
import kr.spring.board.vo.BoardVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.FileUtil;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;

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
	public String getList(@RequestParam(defaultValue="1") int pageNum, String keyfield, String keyword, Model model) {
		int count = boardService.selectRowCount(keyfield, keyword);
		log.debug("<<글의 레코드 수>> : " + count);
		
		// 페이지 처리
		PagingUtil page = new PagingUtil(keyfield, keyword, pageNum, count , 20 ,10 ,"list.do");
		List<BoardVO> list = null;
		if(count > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("keyfield", keyfield);
			map.put("keyword", keyword);
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			list = boardService.selectList(map);
		}
		
		model.addAttribute("count", count);
		model.addAttribute("list", list);
		model.addAttribute("page", page.getPage());
		
		return "boardList";
	}
	
	// 글상세
	@GetMapping("/detail.do")
	public String getDetail(long board_num, Model model) {
		log.debug("<<글상세 - board_num>> : " + board_num);
		
		// 해당 글의 조회수 증가
		boardService.updateHit(board_num);
		
		BoardVO board = boardService.selectBoard(board_num);
		board.setTitle(StringUtil.useNoHtml(board.getTitle()));
		// summer note 사용시는 content에 html 태그 사용 허용
		
		model.addAttribute("board", board);
		
		return "boardView";
	}
	// 파일 다운로드
	@GetMapping("/file.do")
	public ModelAndView download(long board_num, HttpServletRequest request) {
		BoardVO board = boardService.selectBoard(board_num);
		
		return new ModelAndView();
	}
}

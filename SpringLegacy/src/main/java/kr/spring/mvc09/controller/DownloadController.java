package kr.spring.mvc09.controller;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DownloadController {
	
	@GetMapping("/file.do")
	public ModelAndView download(HttpSession session) { // 세션을 바로 주입받을 수 있음
		// file.txt의 컨텍스트 경로상의 절대경료 구하기
		String path = session.getServletContext().getRealPath("/WEB-INF/file.txt");
		File downloadFile = new File(path);
								// 뷰이름			속성명		속성값
		return new ModelAndView("download","downloadFile", downloadFile);
	}
}

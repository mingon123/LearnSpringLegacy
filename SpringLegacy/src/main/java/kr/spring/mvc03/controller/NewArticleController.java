package kr.spring.mvc03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.mvc03.service.ArticleService;
import kr.spring.mvc03.vo.NewArticleCommand;

@Controller
public class NewArticleController {
	
	@Autowired // @Autowired가 setter 를 자동으로 만들어주기 때문에 따로 만들지 않아도 됨 + servlet-context.xml에 설정 추가해야함
	private ArticleService articleService;
	
//	@RequestMapping("/article/newArticle.do")
	// Get 요청이 들어올 때 호출
	@GetMapping("/article/newArticle.do")
//	@RequestMapping(value="/article/newAtricle.do",method=RequestMethod.GET) // 위와 같은 코드인데 너무 길어서 위를 쓰면됨
	public String form() {
				// 뷰이름
		return "article/newArticleForm";
	}
	
	/*
	@ModelAttribute 어노테이션을 이용해서 전송된 데이터를 자바빈에 담기
	[기능]
	1. 자바빈(VO) 생성
	2. 전송된 데이터를 자바빈에 저장
	3. View에서 사용할 수 있도록 request에 자바빈(VO)를 저장
	[형식]
	1. @ModelAttribute(속성명) NewArticleCommand command
	   지정한 속성명으로 JSP에서 EL을 통해 자바빈(VO) 호출 가능
	   예) ${속성명.title}
	2. @ModelAttribute를 명시할 때 속성명을 생략할 수 있음
	   속성명을 생략하면 클래스명의 첫 글자를 소문자로 속성명을 자동 생성
	   예) ModelAttribute NewArticleCommand command
	   에) ${newArticleCommand.title}
	3. @ModelAttribute 생략
	   호출 메서드에 인자명만 명시
	   에) NewArticleCommand command 와 같이 인자명만 명시
	   예) ${newArticleCommand.title}
	*/
	
	
	// Post 요청이 들어올 때 호출
	@PostMapping("/article/newArticle.do")
////	@RequestMapping(value="/article/newAtricle.do",method=RequestMethod.POST) // 위와 같은 코드인데 너무 길어서 위를 쓰면됨
//	public String submit(String title, String name, String content, Model model) {
////		System.out.println("title : " + title);
////		System.out.println("name : " + name);
////		System.out.println("content : " + content);
//
//		NewArticleCommand command = new NewArticleCommand();
//		command.setTitle(title);
//		command.setName(name);
//		command.setContent(content);
//		
//		// model에 저장한 데이터는 JSP에서 EL을 이용해서 호출 가능
//						  // 속성명	   속성값
//		model.addAttribute("command", command);
//		
//		return "article/newArticleSubmitted";
//	}
	
	// 위 코드를 아래 한줄로 변경 가능
//	public String submit(@ModelAttribute("command") NewArticleCommand command) {
//	public String submit(@ModelAttribute NewArticleCommand command) { // 속성명을 지움
	public String submit(NewArticleCommand command) { // @ModelAttribute 생략 - 자바빈 생성 + 자바빈에 정보 담김 + request에 저장까지 되었기 때문에 추가로 생성하면 안됨
		
		articleService.writeArticle(command);
		
		return "article/newArticleSubmitted";
	}
	
}

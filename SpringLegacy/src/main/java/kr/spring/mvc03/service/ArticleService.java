package kr.spring.mvc03.service;

import kr.spring.mvc03.vo.NewArticleCommand;

public class ArticleService {
	public void writeArticle(NewArticleCommand command) {
		System.out.println("신규 게시글 등록 : " + command);
	}
}

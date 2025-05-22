package kr.spring.mvc11.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.mvc10.vo.PageRank;

@Controller
public class PageReportController {
	
	@GetMapping("/pageJson.do")
	@ResponseBody // JSP만들지 않고 어노테이션을 사용해 자동으로 만듦 
	public List<PageRank> jsonReport(){
		List<PageRank> pageRanks = new ArrayList<PageRank>();
		pageRanks.add(new PageRank(1, "/file.do"));
		pageRanks.add(new PageRank(2, "/pageRanksExcel.do"));
		pageRanks.add(new PageRank(3, "/pageJson.do"));
		return pageRanks;
	}
}

package kr.spring.mvc08.vo;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

public class SubmitReportVO {
	@NotBlank
	private String subject;
	private MultipartFile reportFile; // 어노테이션으로 유효성검사 불가해서 수작업으로 해야함
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public MultipartFile getReportFile() {
		return reportFile;
	}
	public void setReportFile(MultipartFile reportFile) {
		this.reportFile = reportFile;
	}
	
	@Override
	public String toString() {
		return "SubmitReportVO [subject=" + subject + ", reportFile=" + reportFile + "]";
	}
	
}

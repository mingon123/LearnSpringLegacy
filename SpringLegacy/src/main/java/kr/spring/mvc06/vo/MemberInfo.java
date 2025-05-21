package kr.spring.mvc06.vo;

import javax.validation.constraints.NotBlank;

public class MemberInfo {
	/*
	@NotNull : null만 허용하지 않음
	@NotBlank : null, ""(빈문자열), " "(공백)을 허용하지 않음 - 거의 @NotBlank, 공백 허용하고 싶으면 @NotEmpty
	@NotEmpty : null, ""(빈문자열)을 허용하지 않음
	*/
//	@NotBlank(message = "아이디를 입력하세요") // javax껄로 사용
	@NotBlank
	private String id;
//	@NotBlank(message = "이름을 입력하세요")
	@NotBlank
	private String name;
//	@NotBlank(message = "우편번호를 입력하세요")
	@NotBlank
	private String zipcode;
//	@NotBlank(message = "주소1을 입력하세요")
	@NotBlank
	private String address1;
//	@NotBlank(message = "주소2를 입력하세요")
	@NotBlank
	private String address2;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	@Override
	public String toString() {
		return "MemberInfo [id=" + id + ", name=" + name + ", zipcode=" + zipcode + ", address1=" + address1
				+ ", address2=" + address2 + "]";
	}
	
}

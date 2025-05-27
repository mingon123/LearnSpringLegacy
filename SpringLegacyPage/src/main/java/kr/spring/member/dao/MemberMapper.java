package kr.spring.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.member.vo.MemberVO;

public interface MemberMapper {
	// 회원관리 - 일반회원
	// 회원가입
	@Select("SELECT slpmember_seq.nextval FROM dual")
	public Long selectMem_num();
	@Insert("INSERT INTO slpmember (mem_num,id) VALUES (#{mem_num},#{id})")
	public void insertMember(MemberVO member);
	@Insert("INSERT INTO slpmember_detail (mem_num,name,passwd,phone,email,zipcode,address1,address2) VALUES (#{mem_num},#{name},#{passwd},#{phone},#{email},#{zipcode},#{address1},#{address2})")
	public void insertMember_detail(MemberVO member);
	// 아이디 중복 및 로그인 체크
	@Select("SELECT mem_num,id,auth,passwd,nick_name,photo,email FROM slpmember LEFT OUTER JOIN slpmember_detail USING(mem_num) WHERE id=#{id}")
	public MemberVO selectCheckMember(String id);
	// 회원상세
	@Select("SELECT * FROM slpmember JOIN slpmember_detail USING(mem_num) WHERE mem_num=#{mem_num}")
	public MemberVO selectMember(Long mem_num);
	// 회원 정보 수정
	public void updateMember(MemberVO member);
	// 비밀번호 수정
	public void updatePassword(MemberVO member);
	// 회원탈퇴
	public void deleteMember(Long mem_num);
	public void deleteMember_detail(Long mem_num);
	// 프로필 이미지 업데이트
	public void updateProfile(MemberVO meber);
	
	// 회원관리 - 관리자
	// 회원목록
	public Integer selectRowCount(Map<String, Object> map);
	public List<MemberVO> selectList(Map<String, Object> map);
	
}

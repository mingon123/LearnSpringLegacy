package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.vo.BoardVO;

public interface BoardMapper {
	// 부모글
	public List<BoardVO> selectList(Map<String, Object> map);
	// @Param 어노테이션을 이용한 다중 파라미터 처리
	public Integer selectRowCount(@Param("keyfield") String keyfield, @Param("keyword") String keyword);
	/*
	MyBatis는 null 파라미터 전달 시 JDBC 타입을 모르면 오류 발생
	Java 타입				JDBC 타입(jdbcType)
	int,Integer			INTEGER
	long,LONG			BIGINT
	String				VARCHAR
	boolean,Boolean		BOOLEAN
	Date,LocalDate 등	DATE,TIMESTAMP 등	
	*/
	@Insert("INSERT INTO slpboard (board_num,title,content,filename,ip,mem_num) VALUES (slpboard_seq.nextval,#{title},#{content},#{filename,jdbcType=VARCHAR},#{ip},#{mem_num})")
	public void InsertBoard(BoardVO board);
	@Select("SELECT * FROM slpboard JOIN slpmember USING (mem_num) WHERE board_num=#{board_num}")
	public BoardVO selectBoard(Long board_num);
	@Update("UPDATE slpboard SET hit=hit+1 WHERE board_num=#{board_num}")
	public void updateHit(Long board_num);
	public void updateBoard(BoardVO board);
	@Update("UPDATE slpboard SET filename='' WHERE board_num=#{board_num}")
	public void deleteFile(Long board_num);
	@Delete("DELETE FROM slpboard WHERE board_num=#{board_num}")
	public void deleteBoard(Long board_num);
	
	// 부모글 좋아요
	
	
	// 댓글
	
	
	// 댓글 좋아요
}

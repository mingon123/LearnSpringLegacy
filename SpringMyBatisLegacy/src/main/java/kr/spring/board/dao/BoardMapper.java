package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.spring.board.vo.BoardVO;

public interface BoardMapper {
	public void insertBoard(BoardVO board);
	@Select("SELECT COUNT(*) FROM aboard")
	public Integer selectBoardCount();
	public List<BoardVO> selectBoardList(Map<String, Object> map);
	public BoardVO selectBoard(Long num);
	public void updateBoard(BoardVO board);
	public void deleteBoard(Long num);
	
}

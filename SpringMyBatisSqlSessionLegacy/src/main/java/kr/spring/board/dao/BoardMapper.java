package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import kr.spring.board.vo.BoardVO;

public interface BoardMapper {
	public void insertBoard(BoardVO board);
	public Integer selectBoardCount();
	public List<BoardVO> selectBoardList(Map<String, Object> map);
	public BoardVO selectBoard(Integer num); // 기본은 Integer, int라고 해도 문제 전혀 되지 않음
	public void updateBoard(BoardVO board);
	public void deleteBoard(Integer num);
}

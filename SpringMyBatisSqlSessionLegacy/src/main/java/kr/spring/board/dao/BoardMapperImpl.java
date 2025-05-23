package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.spring.board.vo.BoardVO;

@Repository
public class BoardMapperImpl implements BoardMapper{

	@Override
	public void insertBoard(BoardVO board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer selectBoardCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardVO> selectBoardList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardVO selectBoard(Integer num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBoard(BoardVO board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBoard(Integer num) {
		// TODO Auto-generated method stub
		
	}
	
}

package kr.spring.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.board.vo.BoardVO;

@Service
@Transactional
public class BoardServiceImpl implements BoardService{

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

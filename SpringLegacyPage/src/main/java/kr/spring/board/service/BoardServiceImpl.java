package kr.spring.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.board.dao.BoardMapper;
import kr.spring.board.vo.BoardFavVO;
import kr.spring.board.vo.BoardVO;

@Service
@Transactional
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardVO> selectList(Map<String, Object> map) {
		return boardMapper.selectList(map);
	}

	@Override
	public Integer selectRowCount(String keyfield, String keyword) {
		return boardMapper.selectRowCount(keyfield, keyword);
	}

	@Override
	public void InsertBoard(BoardVO board) {
		boardMapper.InsertBoard(board);
	}

	@Override
	public BoardVO selectBoard(Long board_num) {
		return boardMapper.selectBoard(board_num);
	}

	@Override
	public void updateHit(Long board_num) {
		boardMapper.updateHit(board_num);
	}

	@Override
	public void updateBoard(BoardVO board) {
		boardMapper.updateBoard(board);
	}

	@Override
	public void deleteFile(Long board_num) {
		boardMapper.deleteFile(board_num);
	}
	
	@Override
	public void deleteBoard(Long board_num) {
		boardMapper.deleteBoard(board_num);
	}

	@Override
	public BoardFavVO selectFav(BoardFavVO fav) {
		return boardMapper.selectFav(fav);
	}

	@Override
	public Integer selectFavCount(Long board_num) {
		return boardMapper.selectFavCount(board_num);
	}

	@Override
	public void insertFav(BoardFavVO fav) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFav(BoardFavVO fav) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFavByBoardNum(Long board_num) {
		// TODO Auto-generated method stub
		
	}
}

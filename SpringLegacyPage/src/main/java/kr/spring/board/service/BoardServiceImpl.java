package kr.spring.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.board.dao.BoardMapper;
import kr.spring.board.vo.BoardFavVO;
import kr.spring.board.vo.BoardReFavVO;
import kr.spring.board.vo.BoardReplyVO;
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
		// 좋아요 삭제
		boardMapper.deleteFavByBoardNum(board_num);
		// 댓글의 좋아요 삭제
		boardMapper.deleteReFavByBoardNum(board_num);
		// 댓글 삭제
		boardMapper.deleteReplyByBoardNum(board_num);		
		// 부모글 삭제
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
		boardMapper.insertFav(fav);
	}

	@Override
	public void deleteFav(BoardFavVO fav) {
		boardMapper.deleteFav(fav);		
	}

	@Override
	public List<BoardReplyVO> selectListReply(Map<String, Object> map) {
		return boardMapper.selectListReply(map);
	}

	@Override
	public Integer selectRowCountReply(Map<String, Object> map) {
		return boardMapper.selectRowCountReply(map);
	}

	@Override
	public void insertReply(BoardReplyVO boardReply) {
		boardMapper.insertReply(boardReply);
	}

	@Override
	public void updateReply(BoardReplyVO boardReply) {
		boardMapper.updateReply(boardReply);		
	}

	@Override
	public void deleteReply(Long re_num) {
		// 댓글 좋아요 삭제
		boardMapper.deleteReFavByReNum(re_num);		
		// 댓글 삭제
		boardMapper.deleteReply(re_num);
	}

	@Override
	public BoardReFavVO selectReFav(BoardReFavVO fav) {
		return boardMapper.selectReFav(fav);
	}

	@Override
	public Integer selectReFavCount(Long re_num) {
		return boardMapper.selectReFavCount(re_num);
	}

	@Override
	public void insertReFav(BoardReFavVO fav) {
		boardMapper.insertReFav(fav);
		
	}

	@Override
	public void deleteReFav(BoardReFavVO fav) {
		boardMapper.deleteReFav(fav);
	}
	
}

package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.spring.board.vo.BoardVO;

@Repository
public class BoardMapperImpl implements BoardMapper{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public void insertBoard(BoardVO board) {
		sqlSession.insert("insertBoard", board);		
	}

	@Override
	public Integer selectBoardCount() {
						// mapper.xml에 명시된 SQL 식별 아이디
		return sqlSession.selectOne("selectBoardCount");
	}

	@Override
	public List<BoardVO> selectBoardList(Map<String, Object> map) {
		return sqlSession.selectList("selectBoardList", map);
	}

	@Override
	public BoardVO selectBoard(Integer num) {
		return sqlSession.selectOne("selectBoard",num);
	}

	@Override
	public void updateBoard(BoardVO board) {
		sqlSession.update("updateBoard", board);		
	}

	@Override
	public void deleteBoard(Integer num) {
		sqlSession.delete("deleteBoard", num);		
	}
	
}

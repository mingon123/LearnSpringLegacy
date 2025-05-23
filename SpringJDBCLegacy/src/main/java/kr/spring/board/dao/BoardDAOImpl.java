package kr.spring.board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.spring.board.vo.BoardVO;

@Repository // DAO 역할을 할 때는 @Repository를 붙임
public class BoardDAOImpl implements BoardDAO{

	private static final String INSERT_SQL = "INSERT INTO aboard(num,writer,title,passwd,content,reg_date) VALUES (aboard_seq.nextval,?,?,?,?,SYSDATE)";
	private static final String SELECT_COUNT_SQL = "SELECT COUNT(*) FROM aboard";
	private static final String SELECT_LIST_SQL = "SELECT * FROM (SELECT a.*,rownum rnum FROM (SELECT * FROM aboard ORDER BY reg_date DESC)a) WHERE rnum >= ? and rnum <= ?";
	private static final String SELECT_DETAIL_SQL = "SELECT * FROM aboard WHERE num=?";
	private static final String UPDATE_SQL = "UPDATE aboard SET writer=?,title=?,content=? WHERE num=?";
	private static final String DELETE_SQL = "DELETE FROM aboard WHERE num=?";
	
	
	// 하나의 레코드의 데이터를 자바빈에 매핑
	private RowMapper<BoardVO> rowMapper = new RowMapper<BoardVO>() {
		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			BoardVO board = new BoardVO();
			board.setNum(rs.getInt("num"));
			board.setWriter(rs.getString("writer"));
			board.setTitle(rs.getString("title"));
			board.setPasswd(rs.getString("passwd"));
			board.setContent(rs.getString("content"));
			board.setReg_date(rs.getDate("reg_date"));
			return board;
		}
	};
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void insertBoard(BoardVO board) {
		jdbcTemplate.update(INSERT_SQL,new Object[] {board.getWriter(),board.getTitle(),board.getPasswd(),board.getContent()}); // ?를 배열형식으로 넘김
	}

	@Override
	public int getBoardCount() {
		return jdbcTemplate.queryForObject(SELECT_COUNT_SQL, Integer.class); // 정수를 반환하기 때문에 정수 타입을 반환
	}

	@Override
	public List<BoardVO> getBoardList(int startRow, int endRow) {
		List<BoardVO> list = jdbcTemplate.query(SELECT_LIST_SQL, new Object[] {startRow,endRow}, rowMapper); // object[]{}로 ?에 데이터 바인딩 데이터 넘김
		return list;
	}

	@Override
	public BoardVO getBoard(int num) {
		return jdbcTemplate.queryForObject(SELECT_DETAIL_SQL, new Object[] {num}, rowMapper); // rowMapper안에 VO가 있어서 queryForOjbect가 자동으로 타입을 찾아 반환
	}

	@Override
	public void updateBoard(BoardVO board) {
		jdbcTemplate.update(UPDATE_SQL, new Object[] {board.getWriter(),board.getTitle(),board.getContent(),board.getNum()});
		
	}

	@Override
	public void deleteBoard(int num) {
		jdbcTemplate.update(DELETE_SQL, new Object[] {num});
		
	}
	
}

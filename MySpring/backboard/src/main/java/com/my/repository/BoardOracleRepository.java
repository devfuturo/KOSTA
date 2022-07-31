package com.my.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.dto.Board;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
@Repository
public class BoardOracleRepository implements BoardRepository {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<Board> selectByPage(int currentPage, int cntPerPage) throws FindException {
		SqlSession session =null;
		try {
			session = sqlSessionFactory.openSession();
			Map<String ,Integer> map = new HashMap<>();
			// currentPage	cntPerPage			StartRow	endRow
			//		1			3		인 경우		1			3
			//		2			3					4			6
			//		3			3					7			9

			int endRow = currentPage * cntPerPage; 
			int startRow = endRow - cntPerPage +1 ;			
			map.put("startRow", startRow); // Mapper파일의 startRow가 key
			map.put("endRow", endRow); // Mapper파일의 endRow가 key
			List<Board> list = session.selectList("com.my.mapper.BoardMapper.selectByPage",map); 
			// 행을 1개만 반환 : SelectOne()
			// 행을 여러개 반환 : SelectList()
			// 첫번째 인자 mapper의 namespace.select id
			// 두번째 인자 parameter로 전달할 것 (parameterType)

			if(list.size() == 0) {
				throw new FindException("게시글이 없습니다"); 
			}
			return list;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session !=null) {
				session.close(); // DB와의 연결을 끊겠다가 아닌 connection pool을 돌려주겠다.라는 뜻
			}
		}
	}

	@Override
	public List<Board> selectByWord(String word, int currentPage, int cntPerPage) throws FindException {
		SqlSession session = null;

		try {
			session = sqlSessionFactory.openSession();
			Map<String, Object> map = new HashMap<>();
			int endRow = currentPage * cntPerPage; 
			int startRow = endRow - cntPerPage +1 ;	
			map.put("word", word);
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			List<Board> list =
					session.selectList("com.my.mapper.BoardMapper.selectByWord", map) ;

			if(list.size()==0) {
				throw new FindException("게시글이 없습니다");
			}
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());

		}finally {
			if(session!=null) {
				session.close();
			}
		}
	}

	@Override
	public Board selectByBoardNo(int boardNo) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Board b = session.selectOne("com.my.mapper.BoardMapper.selectByBoardNo", boardNo);
			if(b == null) {
				throw new FindException("게시글이 없습니다");
			}
			return b;
		}catch(Exception e){
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}

	}

	@Override
	public void update(Board board) throws ModifyException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			int rowcnt = session.update("com.my.mapper.BoardMapper.update", board);
			if(rowcnt != 1) {
				throw new ModifyException("수정된 행수 :" + rowcnt);
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new ModifyException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public void delete(int boardNo) throws RemoveException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			deleteReply(session, boardNo);
			deleteBoard(session, boardNo);
		}catch (Exception e) {
			// 트랜잭션 롤백 필요
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}
	private void deleteReply(SqlSession session, int boardNo) throws RemoveException{
		try {
			session.delete("com.my.mapper.BoardMapper.deleteReply", boardNo);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}
	}
	private void deleteBoard(SqlSession session, int boardNo) throws RemoveException{
		try {
			int rowcnt = session.delete("com.my.mapper.BoardMapper.deleteBoard", boardNo);
			if(rowcnt == 0) {
				throw new RemoveException("삭제된 행 수:" + rowcnt);
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}
	}

	@Override
	public void insert(Board board) throws AddException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			session.insert("com.my.mapper.BoardMapper.insert", board);
		}catch(Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		
	}

	@Override
	public int selectCount() throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			return session.selectOne("com.my.mapper.BoardMapper.selectCount");
		} catch(Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			if(session!=null) {
				session.close();
			}
		}
	}

	@Override
	public int selectCount(String word) throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			return session.selectOne("com.my.mapper.BoardMapper.selectCount2", word);
			// word는 BoardMapper의 string 의 value로 전달됨
		} catch(Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			if(session!=null) {
				session.close();
			}
		}
	}
}

package com.owl.spring.board.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.owl.spring.board.domain.Board;
import com.owl.spring.board.domain.PageInfo;

public interface BoardStore {

	/**
	 * 게시글 등록 Store
	 * @param sqlSession
	 * @param board
	 * @return
	 */
	int insertBoard(SqlSession sqlSession, Board board);

	/**
	 * 게시물 수정 Store
	 * @param sqlSession
	 * @param board
	 * @return
	 */
	int updateBoard(SqlSession sqlSession, Board board);

	/**
	 * 게시글 삭제 Store
	 * @param sqlSession
	 * @param board
	 * @return
	 */
	int deleteBoard(SqlSession sqlSession, Board board);

	/**
	 * 전체 게시물 갯수 Store
	 * @param sqlSession
	 * @return
	 */
	int selectListCount(SqlSession sqlSession);

	/**
	 * 게시물 전체 조회 Store
	 * @param sqlSession
	 * @param pInfo
	 * @return
	 */
	List<Board> selectBoardList(SqlSession sqlSession, PageInfo pInfo);

	/**
	 * 게시물 상세 조회 Store
	 * @param sqlSession
	 * @param boardNo
	 * @return
	 */
	Board selectBoardByNo(SqlSession sqlSession, Integer boardNo);

}

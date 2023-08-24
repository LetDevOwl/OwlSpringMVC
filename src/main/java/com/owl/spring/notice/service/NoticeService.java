package com.owl.spring.notice.service;

import java.util.List;
import java.util.Map;

import com.owl.spring.notice.domain.Notice;
import com.owl.spring.notice.domain.PageInfo;

public interface NoticeService {

	/**
	 * 공지사항 등록 Serivce
	 * @param notice
	 * @return
	 */
	int insertNotice(Notice notice);
	
	/**
	 * 공지사항 수정 Serivce
	 * @param notice
	 * @return
	 */
	int updateNotice(Notice notice);

	/**
	 * 공지사항 목록 조회 Service
	 * @return
	 */
	List<Notice> selectNoticeList(PageInfo pInfo);

	/**
	 * 공지사항 전체 갯수 조회 Service
	 * @return
	 */
	int getListCount();

//	/**
//	 * 공지사항 전체검색 조회 Service
//	 * @param searchKeyword
//	 * @param pInfo 
//	 * @return
//	 */
//	List<Notice> searchNoticesAll(String searchKeyword, PageInfo pInfo);
//
//	/**
//	 * 공지사항 제목으로 조회 Service
//	 * @param searchKeyword
//	 * @param pInfo 
//	 * @return
//	 */
//	List<Notice> searchNoticesByTitle(String searchKeyword, PageInfo pInfo);
//
//	/**
//	 * 공지사항 작성자로 조회 Serivce
//	 * @param searchKeyword
//	 * @param pInfo 
//	 * @return
//	 */
//	List<Notice> searchNoticesByWriter(String searchKeyword, PageInfo pInfo);
//
//	/**
//	 * 공지사항 내용으로 조회 Service
//	 * @param searchKeyword
//	 * @param pInfo 
//	 * @return
//	 */
//	List<Notice> searchNoticesByContent(String searchKeyword, PageInfo pInfo);

	/**
	 * 공자사항 조건에 따라 키워드로 검색 Service
	 * @param pInfo 
	 * @param searchCondition
	 * @param searchKeyword
	 * @return
	 */
	List<Notice> searchNoticesByKeyword(PageInfo pInfo, Map<String, String> paramMap);

	/**
	 * 공지사항 검색 게시물 전체 갯수 Service
	 * @param paramMap
	 * @return
	 */
	int getListCount(Map<String, String> paramMap);

	/**
	 * 공지사항 번호로 조회 Service
	 * @param noticeNo
	 * @return
	 */
	Notice selectNoticeByNo(Integer noticeNo);

}

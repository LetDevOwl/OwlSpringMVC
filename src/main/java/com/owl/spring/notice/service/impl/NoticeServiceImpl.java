package com.owl.spring.notice.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.owl.spring.notice.domain.Notice;
import com.owl.spring.notice.domain.PageInfo;
import com.owl.spring.notice.service.NoticeService;
import com.owl.spring.notice.store.NoticeStore;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private SqlSession session;
	@Autowired
	private NoticeStore nStore;

	@Override
	public int insertNotice(Notice notice) {
		int result = nStore.insertNotice(session, notice);
		return result;
	}

	@Override
	public int updateNotice(Notice notice) {
		int result = nStore.updateNotice(session, notice);
		return result;
	}

	@Override
	public List<Notice> selectNoticeList(PageInfo pInfo) {
		List<Notice> nList = nStore.selectNoticeList(session, pInfo);
		return nList;
	}

	@Override
	public int getListCount() {
		int result = nStore.selectListCount(session);
		return result;
	}

//	@Override
//	public List<Notice> searchNoticesAll(String searchKeyword, PageInfo pInfo) {
//		List<Notice> searchList = nStore.searchNoticesAll(session, searchKeyword, pInfo);
//		return searchList;
//	}
//
//	@Override
//	public List<Notice> searchNoticesByTitle(String searchKeyword, PageInfo pInfo) {
//		List<Notice> searchList = nStore.searchNoticesByTitle(session, searchKeyword, pInfo);
//		return searchList;
//	}
//
//	@Override
//	public List<Notice> searchNoticesByWriter(String searchKeyword, PageInfo pInfo) {
//		List<Notice> searchList = nStore.searchNoticesByWriter(session, searchKeyword, pInfo);
//		return searchList;
//	}
//
//	@Override
//	public List<Notice> searchNoticesByContent(String searchKeyword, PageInfo pInfo) {
//		List<Notice> searchList = nStore.searchNoticesByContent(session, searchKeyword, pInfo);
//		return searchList;
//	}

	@Override
	public List<Notice> searchNoticesByKeyword(PageInfo pInfo, Map<String, String> paramMap) {
		List<Notice> searchList = nStore.searchNoticesByKeyword(session, pInfo, paramMap);
		return searchList;
	}

	@Override
	public int getListCount(Map<String, String> paramMap) {
		int result = nStore.selectListCount(session, paramMap);
		return result;
	}

	@Override
	public Notice selectNoticeByNo(Integer noticeNo) {
		Notice noticeOne = nStore.selectNoticeByNo(session, noticeNo);
		return noticeOne;
	}

}

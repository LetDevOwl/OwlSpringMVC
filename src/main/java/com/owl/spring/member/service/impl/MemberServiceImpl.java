package com.owl.spring.member.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.owl.spring.member.domain.Member;
import com.owl.spring.member.service.MemberService;
import com.owl.spring.member.store.MemberStore;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private SqlSession session;
	
	@Autowired
	private MemberStore mStore;

	@Override
	public int insertMember(Member member) {
		int result = mStore.insertMember(session, member);
		return result;
	}

	@Override
	public Member checkMemberLogin(Member member) {
		Member mOne = mStore.checkMemberLogin(session, member);
		return mOne;
	}

	@Override
	public Member showOneById(String memberId) {
		Member member = mStore.showOneById(session, memberId);
		return member;
	}

	@Override
	public int modifyMember(Member member) {
		int result = mStore.modifyMember(session, member);
		return result;
	}

	@Override
	public int deleteMember(String memberId) {
		int result = mStore.deleteMember(session, memberId);
		return result;
	}

}

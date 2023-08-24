package com.owl.spring.member.store.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.owl.spring.member.domain.Member;
import com.owl.spring.member.store.MemberStore;

@Repository
public class MemberStoreLogic implements MemberStore{

	@Override
	public int insertMember(SqlSession session, Member member) {
		int result = session.insert("MemberMapper.insertMember", member);
		return result;
	}

	@Override
	public Member checkMemberLogin(SqlSession session, Member member) {
		Member mOne = session.selectOne("MemberMapper.checkMemberLogin", member);
		return mOne;
	}

	@Override
	public Member showOneById(SqlSession session, String memberId) {
		Member member = session.selectOne("MemberMapper.showOneById", memberId);
		return member;
	}

	@Override
	public int modifyMember(SqlSession session, Member member) {
		int result = session.update("MemberMapper.modifyMember", member);
		return result;
	}

	@Override
	public int deleteMember(SqlSession session, String memberId) {
		int result = session.update("MemberMapper.deleteMember", memberId);
		return result;
	}

}

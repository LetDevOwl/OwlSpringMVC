package com.owl.spring.member.store;

import org.apache.ibatis.session.SqlSession;

import com.owl.spring.member.domain.Member;

public interface MemberStore {

	/**
	 * 회원가입 Store
	 * @param session
	 * @param member
	 * @return
	 */
	int insertMember(SqlSession session, Member member);

	/**
	 * 회원 로그인 Store
	 * @param session
	 * @param member
	 * @return
	 */
	Member checkMemberLogin(SqlSession session, Member member);

	/**
	 * 아이디로 회원조회 Store
	 * @param session
	 * @param memberId
	 * @return
	 */
	Member showOneById(SqlSession session, String memberId);

	/**
	 * 회원정보 수정 Store
	 * @param session
	 * @param member
	 * @return
	 */
	int modifyMember(SqlSession session, Member member);

	/**
	 * 회원탈퇴 Store
	 * @param session
	 * @param memberId
	 * @return
	 */
	int deleteMember(SqlSession session, String memberId);

}

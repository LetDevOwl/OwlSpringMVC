package com.owl.spring.member.service;

import com.owl.spring.member.domain.Member;

public interface MemberService {

	/**
	 * 회원가입 Serivce
	 * @param member
	 * @return
	 */
	int insertMember(Member member);

	/**
	 * 회원 로그인 Service
	 * @param member
	 * @return
	 */
	Member checkMemberLogin(Member member);

	/**
	 * 아이디로 회원 조회 Service
	 * @param memberId 
	 * @return
	 */
	Member showOneById(String memberId);

	/**
	 * 회원정보 수정 Service
	 * @param member
	 * @return
	 */
	int modifyMember(Member member);

	/**
	 * 회원 탈퇴 Servie
	 * @param memberId
	 * @return
	 */
	int deleteMember(String memberId);

}

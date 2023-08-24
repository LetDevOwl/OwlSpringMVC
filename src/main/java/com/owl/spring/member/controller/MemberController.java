package com.owl.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.owl.spring.member.domain.Member;
import com.owl.spring.member.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@RequestMapping(value="/member/register.kh", method=RequestMethod.GET)
	public String showRegisterForm() {
		return "member/register";
	}
	
	@RequestMapping(value="/member/modify.kh", method=RequestMethod.GET)
	public String showModifyForm(
			@RequestParam("memberId") String memberId
			, Model model) {
		try {
			Member member = service.showOneById(memberId);
			if(member != null) {
				model.addAttribute("member", member);
				return "member/modify";
			}else {
				model.addAttribute("msg", "데이터조회에 실패했습니다");
				model.addAttribute("error", "데이터조회 실패");
				model.addAttribute("url", "/index.jsp");
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요.");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/index.jsp");
			return "common/errorPage";
		}
	}

	@RequestMapping(value="/member/mypage.kh", method= {RequestMethod.GET, RequestMethod.POST})
	public String showMyPage(
			//@RequestParam("memberId") String memberId
			HttpSession session
			, Model model) {
		try {
			String memberId = (String)session.getAttribute("memberId");
			Member member = null;
			if(memberId != "" && memberId != null) {
				member = service.showOneById(memberId);
			}
			if(member != null) {
				model.addAttribute("member", member);
				return "member/mypage";
			}else {
				model.addAttribute("msg", "데이터조회에 실패했습니다");
				model.addAttribute("error", "데이터조회 실패");
				model.addAttribute("url", "/index.jsp");
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요.");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/index.jsp");
			return "common/errorPage";
		}
	}

	@RequestMapping(value="/member/register.kh", method=RequestMethod.POST)
		public String registerMember(
	//			@RequestParam("memberId") String memberId
				@ModelAttribute Member member
				, Model model) {
			try {
				// INSERT INTO MEMBER_TBL
				int result = service.insertMember(member);
				if(result > 0) {
					// home.jsp가 로그인할 수 있는 페이지가 되면 됨!!
					return "redirect:/index.jsp";
				}else {
					model.addAttribute("msg", "회원가입이 완료되지 않았습니다.");
					model.addAttribute("error", "회원가입 실패");
					model.addAttribute("url", "/member/register.kh");
					return "common/errorPage";
				}
			} catch (Exception e) {
				model.addAttribute("msg", "관리자에게 문의해주세요.");
				model.addAttribute("error", e.getMessage());
				model.addAttribute("url", "/member/register.kh");
				return "common/errorPage";
			}
		}
	
	@RequestMapping(value="/member/modify.kh", method=RequestMethod.POST)
	public String modifyMember(
			@ModelAttribute Member member
			, Model model) {
		try {
			int result = service.modifyMember(member);
			if(result > 0) {
				return "/member/mypage";
			}else {
				model.addAttribute("msg", "회원정보 수정에 실패했습니다.");
				model.addAttribute("error", "회원정보 수정실패");
				model.addAttribute("url", "/member/mypage.jsp");
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요.");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/member/register.kh");
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value="/member/delete.kh", method=RequestMethod.GET)
	public String outServiceMember(
			@RequestParam("memberId") String memberId
			, HttpSession session
			, Model model) {
		try {
			int result = service.deleteMember(memberId);
			if(result > 0) {
				session.invalidate();
				return "redirect:/index.jsp";
			}else {
				model.addAttribute("msg", "회원탈퇴가 완료되지 않았습니다.");
				model.addAttribute("error", "회원탈퇴 실패");
				model.addAttribute("url", "/member/mypage.kh");
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요.");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/member/mypage.kh");
			return "common/errorPage";
		}
	}

	@RequestMapping(value="/member/login.kh", method=RequestMethod.POST)
	public String memberLoginCheck(
			@ModelAttribute Member member
			, HttpSession session
			,Model model) {
		try {
			// SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ? AND MEMBER_PW = ?
			Member mOne = service.checkMemberLogin(member);
			if(mOne != null) {
				session.setAttribute("memberId", mOne.getMemberId());
				session.setAttribute("memberName", mOne.getMemberName());
				return "redirect:/index.jsp";
			}else {
				model.addAttribute("msg", "로그인이 완료되지 않았습니다.");
				model.addAttribute("error", "로그인 실패");
				model.addAttribute("url", "/index.jsp");
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요.");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/index.jsp");
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value="/member/logout.kh", method=RequestMethod.GET)
	public String memberLogout(HttpSession session, Model model) {
		if(session != null) {
			session.invalidate();
			return "redirect:/index.jsp";
		}else {
			model.addAttribute("msg", "로그아웃을 완료하지 못하였습니다.");
			model.addAttribute("error", "로그아웃 실패");
			model.addAttribute("url", "/index.jsp");
			return "common/errorPage";
		}
	}
}

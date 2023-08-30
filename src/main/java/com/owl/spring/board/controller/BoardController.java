package com.owl.spring.board.controller;

import com.owl.spring.board.domain.Board;
import com.owl.spring.board.domain.PageInfo;
import com.owl.spring.board.domain.Reply;
import com.owl.spring.board.service.BoardService;
import com.owl.spring.board.service.ReplyService;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService bService;
	@Autowired
	private ReplyService rService;
	
	@RequestMapping(value="/board/write.kh", method=RequestMethod.GET)
	public ModelAndView showWriteForm(ModelAndView mv) {
		mv.setViewName("board/write");
//		return "board/write";
		return mv;
	}

	@RequestMapping(value="/board/write.kh", method=RequestMethod.POST)
	public ModelAndView boardRegister(
			ModelAndView mv
			, @ModelAttribute Board board
			, @RequestParam(value="uploadFile", required = false) MultipartFile uploadFile
			, HttpSession session
			, HttpServletRequest request) {
		try {
			String boardWriter = (String)session.getAttribute("memberId");
			if(boardWriter != null && !boardWriter.equals("")) {
				board.setBoardWriter(boardWriter);
				if(uploadFile != null && !uploadFile.getOriginalFilename().equals("")) {
					// 파일정보(이름, 리네임, 경로, 크기) 및 파일저장
					Map<String, Object> bMap = this.saveFile(request, uploadFile);
					board.setBoardFilename((String)bMap.get("fileName"));
					board.setBoardFileRename((String)bMap.get("fileRename"));
					board.setBoardFilepath((String)bMap.get("filePath"));
					board.setBoardFileLength((long)bMap.get("fileLength"));
				}
				int result = bService.insertBoard(board);
				mv.setViewName("redirect:/board/list.kh");
			}else {
				mv.addObject("msg", "로그인 정보가 존재하지 않습니다.");
				mv.addObject("error", "로그인이 필요합니다.");
				mv.addObject("url", "/index.jsp");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
//			model.addAttribute("msg", "등록이 완료되지 않았습니다.");
			mv.addObject("msg", "관리자에게 문의 주세요.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/board/write.kh");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="/board/modify.kh", method=RequestMethod.GET)
	public ModelAndView showModifyForm(ModelAndView mv
			, @RequestParam("boardNo") Integer boardNo) {
		try {
			Board board = bService.selectBoardByNo(boardNo);
			mv.addObject("board", board);
			mv.setViewName("board/modify");
		} catch (Exception e) {
			mv.addObject("msg", "게시글 조회가 완료되지 않았습니다.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/board/write.kh");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="/board/modify.kh", method=RequestMethod.POST)
	public ModelAndView boardModify(ModelAndView mv
			, @ModelAttribute Board board
			, @RequestParam(value="uploadFile", required = false) MultipartFile uploadFile
			, HttpSession session
			, HttpServletRequest request) {
		try {
			String memberId = (String)session.getAttribute("memberId");
			String boardWriter = board.getBoardWriter();
			if(boardWriter != null && boardWriter.equals(memberId)) {
				// 수정이라는 과정은 대체하는 것, 대체하는 방법은 삭제 후 등록
				if(uploadFile != null && !uploadFile.getOriginalFilename().equals("")) {
					String fileRename = board.getBoardFileRename();
					if(fileRename != null) {
						this.deleteFile(fileRename, request);
					}
					Map<String, Object> bFileMap = this.saveFile(request, uploadFile);
					board.setBoardFilename((String)bFileMap.get("fileName"));
					board.setBoardFileRename((String)bFileMap.get("fileRename"));
					board.setBoardFilepath((String)bFileMap.get("filePath"));
					board.setBoardFileLength((long)bFileMap.get("fileLength"));
				}
				int result = bService.updateBoard(board);
				if(result > 0) {
					mv.setViewName("redirect:/board/detail.kh?boardNo="+board.getBoardNo());
				}else {
					mv.addObject("msg", "게시글 수정이 완료되지 않았습니다.");
					mv.addObject("error", "게시글 수정 실패");
					mv.addObject("url", "/board/modify.kh?boardNo="+board.getBoardNo());
					mv.setViewName("common/errorPage");
				}
			}else {
				mv.addObject("msg", "게시글 수정 권한이 없습니다.");
				mv.addObject("error", "게시글 수정 불가");
				mv.addObject("url", "/board/modify.kh?boardNo="+board.getBoardNo());
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", "관리자에게 문의바랍니다.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/board/modify.kh?boardNo="+board.getBoardNo());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="/board/delete.kh", method=RequestMethod.GET)
	public ModelAndView deleteBoard(ModelAndView mv
			, @RequestParam("boardNo") Integer boardNo
			, @RequestParam("boardWriter") String boardWriter
			, HttpSession session) {
		try {
			String memberId = (String)session.getAttribute("memberId");
			Board board = new Board();
			board.setBoardNo(boardNo);
			board.setBoardWriter(boardWriter);
			if(boardWriter != null && boardWriter.equals(memberId)) {
				int result = bService.deleteBoard(board);
				if(result > 0) {
					mv.setViewName("redirect:/board/list.kh");
				}else {
					mv.addObject("msg", "게시글 삭제가 완료되지 않았습니다.");
					mv.addObject("error", "게시글 삭제 실패");
					mv.addObject("url", "/board/list.kh");
					mv.setViewName("common/errorPage");
				}
			}else {
				mv.addObject("msg", "본인이 작성한 글만 삭제할 수 있습니다.");
				mv.addObject("error", "게시글 삭제 불가");
				mv.addObject("url", "/board/list.kh");
				mv.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", "관리자에게 문의 바랍니다.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/board/list.kh");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}

	@RequestMapping(value="/board/detail.kh", method=RequestMethod.GET)
	public ModelAndView showBoardDetail(
			ModelAndView mv
			, @RequestParam("boardNo") Integer boardNo) {
		try {
			Board boardOne = bService.selectBoardByNo(boardNo);
			
			if(boardOne != null) {
				List<Reply> replyList = rService.selectReplyList(boardNo);
				if(replyList.size() > 0) {
					mv.addObject("rList", replyList);
				}
				mv.addObject("board", boardOne);
				mv.setViewName("board/detail");
			}else {
				mv.addObject("msg", "게시글 조회가 완료되지 않았습니다.");
				mv.addObject("error", "게시글 조회 실패");
				mv.addObject("url", "/board/list.kh");
				mv.setViewName("common/errorPage");
			}
			
		} catch (Exception e) {
			mv.addObject("msg", "게시글 조회가 완료되지 않았습니다.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/board/list.kh");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="/board/list.kh", method=RequestMethod.GET)
	public ModelAndView showBoardList(
			@RequestParam(value="page", required = false, defaultValue = "1") Integer currentPage
			, ModelAndView mv) {
		try {
			Integer totalCount = bService.getListCount();
			PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
			List<Board> bList = bService.selectBoardList(pInfo);
			mv.addObject("pInfo", pInfo).addObject("bList", bList).setViewName("board/list");
		} catch (Exception e) {
			mv.addObject("msg", "게시글 등록이 완료되지 않았습니다.");
			mv.addObject("error", e.getMessage());
			mv.addObject("url", "/board/write.kh");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	public PageInfo getPageInfo(Integer currentPage, Integer totalCount) {
		int recordCountPerPage = 10;
		int naviCountPerPage = 10;
		int naviTotalCount;
		naviTotalCount = (int)Math.ceil((double)totalCount/recordCountPerPage);
		int startNavi = ((int)((double)currentPage/naviCountPerPage+0.9)-1)*naviCountPerPage+1;
		int endNavi = startNavi + naviCountPerPage - 1;
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		PageInfo pInfo = new PageInfo(currentPage, totalCount, naviTotalCount, recordCountPerPage, naviCountPerPage, startNavi, endNavi);
		return pInfo;
	}

	public Map<String, Object> saveFile(HttpServletRequest request, MultipartFile uploadFile) throws Exception {
		Map<String, Object> fileMap = new HashMap<String, Object>();
		// resources 경로 구하기
		String root = request.getSession().getServletContext().getRealPath("resources");
		// 파일 저장경로 구하기
		String savePath = root + "\\buploadFiles";
		// 파일 이름 구하기
		String fileName = uploadFile.getOriginalFilename();
		// 파일 확장자 구하기
		String extension = fileName.substring(fileName.lastIndexOf(".")+1);
		// 시간으로 파일 리네임하기
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMDDHHmmss");
		String fileRename = sdf.format(new Date(System.currentTimeMillis())) + "." + extension;
		// 파일 저장 전 폴더 만들기
		File saveFolder = new File(savePath);
		if(!saveFolder.exists()) {
			saveFolder.mkdir();
		}
		// 파일 저장
		File saveFile = new File(savePath + "\\" + fileRename);
		uploadFile.transferTo(saveFile);
		long fileLength = uploadFile.getSize();
		// 파일 정보 리턴
		fileMap.put("fileName", fileName);
		fileMap.put("fileRename", fileRename);
		fileMap.put("filePath", "../resources/buploadFiles" + fileRename);
		fileMap.put("fileLength", fileLength);
		return fileMap;
	}

	private void deleteFile(String fileRename, HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("resources");
		String delPath = root + "\\buploadFiles" + fileRename;
		File delFile = new File(delPath);
		if(delFile.exists()) {
			delFile.delete();
		}
		
	}
}

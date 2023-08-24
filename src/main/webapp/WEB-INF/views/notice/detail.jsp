<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지사항 상세 조회</title>
		<link rel="stylesheet" href="../resources/css/main.css">
	</head>
	<body>
		<h1>공지 상세</h1>
			<ul>
				<li>
					<label>제목</label>
					<span>${noticeOne.noticeSubject }</span>
				</li>
				<li>
					<label>작성자</label>
					<span>${noticeOne.noticeWriter }</span>
				</li>
				<li>
					<label>내용</label>
					<p>${noticeOne.noticeContent }</p>
				</li>
				<li>
					<label>첨부파일</label>
					<!-- String으로 받을 수 없고 변환작업이 필요함 -->
<%-- 				<img alt="첨부파일" src="${noticeOne.noticeFilepath }"> --%>
					<a href="../resources/nuploadFiles/${noticeOne.noticeFileRename }" download>${noticeOne.noticeFilename }</a>
				</li>
			</ul>
			<div>
				<button type="button" onclick="showModifyPage();">수정하기</button>
				<button>삭제하기</button>
				<button type="button" onclick="showNoticePage();">목록으로</button>
			</div>
			<script>
				function showModifyPage() {
					const noticeNo = "${noticeOne.noticeNo }";
					location.href="/notice/modify.kh?noticeNo="+noticeNo;
				}
				function showNoticePage() {
					location.href="/notice/list.kh";
				}
			</script>
	</body>
</html>
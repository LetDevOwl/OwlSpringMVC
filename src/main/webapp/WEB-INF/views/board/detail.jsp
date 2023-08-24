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
					<span>${board.boardTitle }</span>
				</li>
				<li>
					<label>작성자</label>
					<span>${board.boardWriter }</span>
				</li>
				<li>
					<label>내용</label>
					<p>${board.boardContent }</p>
				</li>
				<li>
					<label>첨부파일</label>
					<!-- String으로 받을 수 없고 변환작업이 필요함 -->
<%-- 				<img alt="첨부파일" src="${noticeOne.noticeFilepath }"> --%>
					<a href="../resources/nuploadFiles/${board.boardFileRename }" download>${board.boardFilename }</a>
				</li>
			</ul>
			<div>
				<button type="button" onclick="showModifyPage();">수정하기</button>
				<button>삭제하기</button>
				<button type="button" onclick="showBoardPage();">목록으로</button>
			</div>
			<!-- 댓글 등록 -->
			<form action="/board/addReply.kh" method="post">
				<table width="500" border="1">
					<tr>
						<td>
							<textarea rows="3" cols="55"></textarea>
						</td>
						<td>
							<input type="submit" value="완료">
						</td>
					</tr>
				</table>
			</form>
			<!-- 댓글 목록 -->
			<table width="500" border="1">
				<tr>
					<td>댓글 작성자</td>
					<td>댓글 내용</td>
					<td>댓글 날짜</td>
					<td>
						<a href="#">수정하기</a>
						<a href="#">삭제하기</a>
					</td>
				</tr>
				<tr>
					<td>댓글 작성자</td>
					<td>댓글 내용</td>
					<td>댓글 날짜</td>
					<td>
						<a href="#">수정하기</a>
						<a href="#">삭제하기</a>
					</td>
				</tr>
			</table>
			<script>
				function showModifyPage() {
					const board = "${board.boardNo }";
					location.href="/board/modify.kh?boardNo="+boardNo;
				}
				function showBoardPage() {
					location.href="/board/list.kh";
				}
			</script>
	</body>
</html>
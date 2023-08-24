<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지사항 수정</title>
		<link rel="stylesheet" href="../resources/css/main.css">
	</head>
	<body>
		<h1>공지 수정</h1>
		<form action="/notice/modify.kh" method="post" enctype="multipart/form-data">
		<!-- 수정할 때, 리다이렉트 될 때 사용 -->
		<input type="hidden" name="noticeNo" value="${noticeOne.noticeNo }">
		<!-- 기존 업로드 파일 체크할 때 사용 -->
		<input type="hidden" name="noticeFilename" value="${noticeOne.noticeFilename }">
		<input type="hidden" name="noticeFileRename" value="${noticeOne.noticeFileRename }">
		<input type="hidden" name="noticeFilepath" value="${noticeOne.noticeFilepath }">
		<input type="hidden" name="noticeFilelength" value="${noticeOne.noticeFilelength }">
			<ul>
				<li>
					<label>제목</label>
					<input type="text" name="noticeSubject" value="${noticeOne.noticeSubject }">
				</li>
				<li>
					<label>작성자</label>
					<input type="text" name="noticeWriter" value="${noticeOne.noticeWriter }" readonly>
				</li>
				<li>
					<label>내용</label>
					<textarea rows="4" cols="50" name="noticeContent">${noticeOne.noticeContent }</textarea>
				</li>
				<li>
					<label>첨부파일</label>
					<!-- String으로 받을 수 없고 변환작업이 필요함 -->
					<a href="../resources/nuploadFiles/${noticeOne.noticeFilename }" download>${noticeOne.noticeFilename }</a>
					<input type="file" name="uploadFile">
				</li>
			</ul>
			<div>
				<input type="submit" value="수정완료">
				<button type="button" onclick="showNoticePage();">목록으로</button>
			</div>
		</form>
		<script>
			function showNoticePage() {
				location.href="/notice/list.kh";
			}
		</script>
	</body>
</html>
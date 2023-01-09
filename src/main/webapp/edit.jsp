<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>방명록 수정</title>
<link rel="stylesheet" href="./css/style.css" />
</head>
<body>
	<div class="wrap">
		<div class="wrap_title">
			<strong>방명록 수정</strong>
			<p>축하메세지로 나무를 풍성하게 해주세요!</p>
		</div>
		<div class="write_wrap">
			<form name="frm" method="post" action="update?list_no=${list_no}">
			<div class="bt_wrap">
				<a href="list">취소</a> 
				<a onclick="chkForm(); return false;" class="on">수정</a>
			</div>
				<div class="write">
					<div class="title">
						<dl>
							<dt>제목</dt>
							<dd>
								<input type="text" maxlength="50" name="title" value="${weddingList.title}"
									/>
							</dd>
						</dl>
					</div>
					<div class="info">
						<dl>
							<dt>작성자</dt>
							<dd>
								<input type="text" name="user_id" maxlength="10" value = "${weddingList.user_name}" readonly/>
							</dd>
						</dl>
					</div>				
					<div class="cont">
						<textarea name="content">${weddingList.content}</textarea>
					</div>					
				</div>
			</form>

		</div>
	</div>
	<script type="text/javascript" src="./script.js"></script>
</body>
</html>
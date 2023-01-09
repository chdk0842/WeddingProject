<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Insert title here</title>
<link rel="stylesheet" href="./css/style.css" />
</head>
<body>
	<div class="wrap">
		<div class="wrap_title">
			<strong>방명록 작성</strong>
			<p>축하메세지로 나무를 풍성하게 해주세요!</p>
		</div>
		<div class="write_wrap">
			<form name="frm" method="post" action="insert">
			<!-- input type= "hidden" 데이터만 갖고 있음(사용자 안보임) -->
			<input type= "hidden" name = "user_id" value = "${user.user_id}"/>
			<div class="bt_wrap">
				<a href="list">취소</a> 
				<a onclick="chkForm(); return false;" class="on">등록</a>
			</div>
				<div class="write">
					<div class="title">
						<dl>
							<dt>제목</dt>
							<dd>
								<input type="text" maxlength="50" name="title"
									/>
							</dd>
						</dl>
					</div>
					<div class="info">
						<dl>
							<dt>작성자</dt>
							<dd>
								<input type="text" name="user_name" maxlength="10" value = "${user.user_name}" readonly/>
							</dd>
						</dl>
					</div>				
					<div class="cont">
						<textarea name="content" placeholder="내용 입력"></textarea>
					</div>					
				</div>
			</form>

		</div>
	</div>
	<script type="text/javascript" src="./script.js"></script>
</body>
</html>
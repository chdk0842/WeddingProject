<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<div class="view_wrap">
		<div class="view">
			<form name = "frm" method="post" action="login">
			<div class="bt_wrap">
				<a href="list" >취소</a>
				<a onclick="logForm(); return false;" class="on">등록</a>
			</div>
				<label>성명</label> 
				<input type="text" name="user_name" /><br /> 
				<label>아이디</label>
				<input type="text" name="user_id" /><br />
				<label>패스워드</label>
				<input type="password" name="user_pw" /><br /> 
			</form>

		</div>
	</div>
	<script type="text/javascript" src="./script.js"></script>
</body>
</html>
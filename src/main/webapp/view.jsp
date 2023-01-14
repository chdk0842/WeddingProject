<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>방명록 보기</title>
<link rel="stylesheet" href="./css/style.css" />
</head>
<body>		
		<div class="view_wrap" >
			<div class="view">
				<div class="title">${weddingList.title}</div> <br>
				<div class="name">${weddingList.user_name}</div> <br>
				<div class="cont" style = "white-space:pre-wrap;">${weddingList.content}</div>
			
			<div class="bt_wrap">
			     
				<a href="list" class="on">취소</a>
				<a onclick = "chkDelete(${list_no}, ${weddingList.user_pw}); return false;">삭제</a>
				<a href="edit?list_no=${list_no}">수정</a>
			</div>
		</div>
		
			</div>
			
			<script type="text/javascript" src="./script.js"></script>
</body>
</html>
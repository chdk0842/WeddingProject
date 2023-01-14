<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>방명록 리스트</title>
<link rel="stylesheet" href="./css/style.css" />
</head>
<body>
	<div class="wrap">
		<table class="list">
			<caption>Wedding Message List</caption>
			<thead>
				<tr class="top_tr">
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="wedding" items="${weddingList}" varStatus="status">
					<tr>
						<td>${wedding.list_no}</td>
						<td class="title"><a href="./view?list_no=${wedding.list_no}">${wedding.title}</a></td>
						<td>${wedding.user_id}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="list_page">
			<a href="#" class="bt first">&lt;&lt;</a> <a href="#" class="bt prev">&lt;</a>
			<a href="#" class="num">1</a> <a href="#" class="num">2</a> <a
				href="#" class="num">3</a> <a href="#" class="num">4</a> <a href="#"
				class="num">5</a> <a href="#" class="bt next">&gt;</a> <a href="#"
				class="bt last">&gt;&gt;</a>
		</div>
		<div class= "home_bt">
			<a href="index.jsp" ><img src = "./img/home.png" alt = "logo" width = "40"></a>
			</div>
	</div>
</body>
</html>
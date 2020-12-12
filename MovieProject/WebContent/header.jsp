<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- encoding을 utf-8로 한다 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- charset을 utf-8로 한다 -->
<title>Insert title here</title>
<link rel="stylesheet" href="app.css">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
	<div class="header">
		<%
			String id = (String)session.getAttribute("id");
		if (id == null) {
		%>
			
		<nav class="main_menu">
			<a href="index.jsp">JEGABOX</a>&nbsp; <a
				href="AllMovieList.do?category=01">액션</a>&nbsp; <a
				href="AllMovieList.do?category=02">로맨스</a>&nbsp; <a
				href="AllMovieList.do?category=03">코미디</a>&nbsp; <a
				href="AllMovieList.do?category=04">스릴러</a>&nbsp; <a
				href="AllMovieList.do?category=05">애니메이션</a>&nbsp; <a href="#">내
				예매목록</a>
		</nav>
		<div class = "loginout">
		<a href="/login.jsp">로그인</a> &nbsp;
		<a href="/register.jsp">회원가입</a>		
		</div>
		<%
			} else if(id.equals("admin")){
		%>	
	
		<nav class="main_menu">
			<a href="index.jsp">JEGABOX</a>&nbsp;
			<a href="MyRevList.do">관리자 예매 목록 조회</a>
			<a href="/AddMovie.do">영화 추가하기</a>
		</nav>
		<a href="/Login.do?type=login" class="logout"><b><%=id%></b> 로그아웃</a>
		<%
			} else {
		%>
		<nav class="main_menu">
			<a href="index.jsp">JEGABOX</a>&nbsp; <a
				href="AllMovieList.do?category=01">액션</a>&nbsp; 
				<a href="AllMovieList.do?category=02">로맨스</a>&nbsp;
				<a href="AllMovieList.do?category=03">코미디</a>&nbsp;
				<a href="AllMovieList.do?category=04">스릴러</a>&nbsp; 
				<a href="AllMovieList.do?category=05">애니메이션</a>&nbsp;
				<a href="MyRevList.do?id=<%=id%>">내 예매목록</a>
		</nav>
		<a href="/Login.do?type=login" class="logout"><b><%=id%></b> 로그아웃</a>
		<%
			}
		%>
	</div>
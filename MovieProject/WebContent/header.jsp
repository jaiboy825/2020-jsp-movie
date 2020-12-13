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
</head>
<body>
<!-- 모든 페이지에서 보여줄 헤더이다 -->
	<div class="header">
		<%
			String id = (String)session.getAttribute("id");
		//세션의 id 를 받는다
		if (id == null) {
			//null이라면 기본 헤더를 보여준다 로그인 회원가입 링크를 추가하여 다른 작업을 할 수 있다
		%>
			
		<nav class="main_menu">
			<a href="index.jsp">JEGABOX</a>&nbsp; <a
				href="AllMovieList.do?category=01">액션</a>&nbsp; <a
				href="AllMovieList.do?category=02">로맨스</a>&nbsp; <a
				href="AllMovieList.do?category=03">코미디</a>&nbsp; <a
				href="AllMovieList.do?category=04">스릴러</a>&nbsp; <a
				href="AllMovieList.do?category=05">애니메이션</a>
				<!-- 원하는 카테고리를 보여주기 위한 a 태그들이다 -->
		</nav>
		<div class = "loginout">
		<a href="/login.jsp">로그인</a> &nbsp;
		<a href="/register.jsp">회원가입</a>		
		</div>
		<%
			} else if(id.equals("admin")){
				//세션에 있는 id가 admin 이라면 관리자 헤더를 보여준다
		%>	
	
		<nav class="main_menu">
			<a href="index.jsp">JEGABOX</a>&nbsp;
			<a href="MyRevList.do">관리자 예매 목록 조회</a>
			<!-- 사용자들의 예매 목록을 볼수 있고 취소 할수도 있다 -->
			<a href="/AddMovie.do">영화 추가하기</a>
			<!-- 영화 추가하기 구현 예정 -->
		</nav>
		<a href="/Login.do?type=login" class="logout"><b><%=id%></b> 로그아웃</a>
		<!-- 로그아웃 -->
		<%
			} else {
				// 로그인이 되어있고 admin이 아니라면 일반 유저이기에 로그인 시 사용 가능한 헤더를 보여준다
		%>
		<nav class="main_menu">
			<a href="index.jsp">JEGABOX</a>&nbsp; <a
				href="AllMovieList.do?category=01">액션</a>&nbsp; 
				<a href="AllMovieList.do?category=02">로맨스</a>&nbsp;
				<a href="AllMovieList.do?category=03">코미디</a>&nbsp;
				<a href="AllMovieList.do?category=04">스릴러</a>&nbsp; 
				<a href="AllMovieList.do?category=05">애니메이션</a>&nbsp;
				<a href="MyRevList.do?id=<%=id%>">내 예매목록</a> <!-- 내 예매 목록을 확인할 수 있다 -->
		</nav>
		<a href="/Login.do?type=login" class="logout"><b><%=id%></b> 로그아웃</a><!-- 로그아웃 -->
		<%
			}
		%>
	</div>
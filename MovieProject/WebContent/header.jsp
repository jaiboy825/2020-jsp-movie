<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- encoding을 utf-8로 한다 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- charset을 utf-8로 한다 -->
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<%
			String id = (String) session.getAttribute("id");
		if (id == null) {
		%>
		<nav>
			<a href="index.jsp">메인화면</a>&nbsp; <a
				href="AllMovieList.do?category=01">액션</a>&nbsp; <a
				href="AllMovieList.do?category=02">로맨스</a>&nbsp; <a
				href="AllMovieList.do?category=03">코미디</a>&nbsp; <a
				href="AllMovieList.do?category=04">스릴러</a>&nbsp; <a
				href="AllMovieList.do?category=05">애니메이션</a>&nbsp; <a href="#">내
				예매목록</a>
		</nav>
		<table>
		<a href="/login.jsp">로그인</a>
		<a href="/register.jsp">회원가입</a>
		</table>
		<%
			} else {

		if (id == "admin") {
		%>
		<nav>
			<a href="index.jsp">메인화면</a>&nbsp;
			<a href="/AllTicketList.do">관리자 예매 목록 조회</a>
			<a href="/SelectTicketById.do">관리자 예매 현황 보기</a>
			<a href="/AddMovie.do">영화 추가하기</a>
		</nav>
		
		<%
			} else {
		%>
		<a href="/Login.do?type=login"><%=id%> 로그아웃</a>
		<%
			}
		}
		%>
	</div>
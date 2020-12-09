<%@page import="movie.MovieVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp" />
<div class="container">
	
			<div class="ml_title">영화 목록</div>
		<div class="movieList_container">
		<%
			ArrayList<MovieVO> list = (ArrayList<MovieVO>)request.getAttribute("list");
		for (MovieVO vo : list) {
		%>
			<!-- "/movieInfo.jsp?movieNo=<vo.getMovieNo()%>" -->
			<div class="movie">
			<%
			String id = (String) session.getAttribute("id");
			if (id != null) {
			%>
			<a href="/movieInfo.do?movieNo=<%=vo.getMovieNo()%>"> 
			<img alt="" src="imgs/<%=vo.getImg()%>">
			<div class="movie_title"><b><%=vo.getMovieName()%></b></div>
			</a>
			<%
			}else {
			%>
			<img class="movie_img" alt="" src="imgs/<%=vo.getImg()%>">
			<div class="movie_title"><b><%=vo.getMovieName()%></b></div>
			<%
			}			
			%>
			</div>
			<%
		}
			%>
		</div>
</div>
<jsp:include page="/footer.jsp" />
<%@page import="movie.MovieDAO"%>
<%@page import="movie.MovieVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp" />
<div class="container">
	<!-- 영화 목록들을 보여주기 위한 페이지 -->
	<div class="ml_title">영화 목록</div>
	<div class="movieList_container">
		<%
			ArrayList<MovieVO> list = (ArrayList<MovieVO>) request.getAttribute("list");
		/* list를 받는다  */
		for (MovieVO vo : list) {
			//list를 돌면서
			MovieDAO dao = new MovieDAO();
			String movieNo = vo.getMovieNo()+"";
			String category = dao.getCategory(movieNo);
			//정보들을 받고
		%>
		<div class="movie"> 
		<div class="movieCategory"><%=category %></div> <!-- 카테고리 -->
			<%
				String id = (String) session.getAttribute("id"); //로그인 되어있는지 확인
			if (id != null) { //null이 아니라면 로그인이 된거기에 정보를 보여주기 위해 a 태그로 감싼다
			%>
			<a href="/movieInfo.do?movieNo=<%=vo.getMovieNo()%>"> <img class="movie_img" alt=""
				src="imgs/<%=vo.getImg()%>"> <!-- 사진 -->
				<div class="movie_title">
					<b><%=vo.getMovieName()%></b> <!-- 영화제목  -->
				</div>
			</a>
			<%
				} else { //null 이라면 그냥 목록만 보여준다
			%>
			<img class="movie_img" alt="" src="imgs/<%=vo.getImg()%>"> <!-- 사진 -->
			<div class="movie_title">
				<b><%=vo.getMovieName()%></b> <!-- 영화 제목  -->
			</div>
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
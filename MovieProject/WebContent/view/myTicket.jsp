<%@page import="java.text.SimpleDateFormat"%>
<%@page import="movie.BookVO"%>
<%@page import="movie.MovieDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp" />
<div class="container">
	<!-- 티켓 목록을 보여주기 위한 페이지 -->
	<div class="ml_title">티켓 목록</div>
	<div class="ticket_container">
		<%
			ArrayList<BookVO> list = (ArrayList<BookVO>) request.getAttribute("list");	
		//list 받기
		for (BookVO vo : list) {
			//list를 돈다
			MovieDAO dao = new MovieDAO();
			//dao 부르기
		%>
		<div class="ticket"> <!-- 티켓들을 보여준다 -->
			<img class="movie_img" alt="" src="imgs/<%=vo.getMovieImg()%>"> <!-- 사진 -->
			<div class="ticket_info"> <!-- 정보 -->
				<div> 
				<div class="tmt"><%=vo.getMovieName()%></div> <!-- 제목  -->
				<div><%=vo.getCategory()%></div> <!-- 카테고리 -->
				<div><%=vo.getRuntime()%>분</div> <!-- 시간 -->
				</div>
				<%
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일"); /* 날짜 */
				String date = sdf.format(vo.getRunDay());
				sdf = new SimpleDateFormat("HH:mm"); /* 시간 */
				String startTime = sdf.format(vo.getRunDay());
				%>
				<div>
				<div><%=date%> / <%=startTime%></div> <!-- 날짜와 시작시간 -->
				<div><%=vo.getRoomNo()%>관 / <%=vo.getSeatNo()%>번 자리</div> <!-- 상영관과 자리 -->
				<div>예매자 : <%=vo.getId() %></div> <!-- 예약자  -->
				</div>
				<a href="/delTicket.do?ticketNo=<%=vo.getticketNo()%>" class="delTi">예매 취소</a> <!-- 예매취소 링크 -->				
			</div>
		</div>
		<%
			}
		%>
	</div>
</div>
<jsp:include page="/footer.jsp" />
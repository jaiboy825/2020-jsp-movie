<%@page import="java.text.SimpleDateFormat"%>
<%@page import="movie.BookVO"%>
<%@page import="movie.MovieDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp" />
<div class="container">

	<div class="ml_title">티켓 목록</div>
	<div class="ticket_container">
		<%
			ArrayList<BookVO> list = (ArrayList<BookVO>) request.getAttribute("list");		
		for (BookVO vo : list) {
			MovieDAO dao = new MovieDAO();
		%>
		<!-- "/movieInfo.jsp?movieNo=<vo.getMovieNo()%>" -->
		<div class="ticket">
			<img class="movie_img" alt="" src="imgs/<%=vo.getMovieImg()%>">
			<div class="ticket_info">
				<div> 
				<div class="tmt"><%=vo.getMovieName()%></div>
				<div><%=vo.getCategory()%></div>
				<div><%=vo.getRuntime()%>분</div>
				</div>
				<%
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
				String date = sdf.format(vo.getRunDay());
				sdf = new SimpleDateFormat("HH:mm");
				String startTime = sdf.format(vo.getRunDay());
				%>
				<div>
				<div><%=date%> / <%=startTime%></div>
				<div><%=vo.getRoomNo()%>관 / <%=vo.getSeatNo()%>번 자리</div>
				<div>예매자 : <%=vo.getId() %></div>
				</div>
				<a href="/delTicket.do?ticketNo=<%=vo.getticketNo()%>" class="delTi">예매 취소</a>				
			</div>
		</div>
		<%
			}
		%>
	</div>
</div>
<jsp:include page="/footer.jsp" />
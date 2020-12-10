<%@page import="java.text.SimpleDateFormat"%>
<%@page import="movie.ScheduleVO"%>
<%@page import="movie.MovieDAO"%>
<%@page import="movie.MovieVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp" />
<div class="container">

	<div class="info_container">
		<div class="info_title">영화목록</div>
		<%
			MovieVO vo = (MovieVO) request.getAttribute("vo");
		String ct = (String) request.getAttribute("ct");
		ArrayList<ScheduleVO> scheList = (ArrayList) request.getAttribute("scheList");
		
		MovieDAO dao = new MovieDAO();
		
		%>
		<div class="info">
			<!-- "/movieInfo.jsp?movieNo=<vo.getMovieNo()%>" -->
			<div>
				<img alt="" src="imgs/<%=vo.getImg()%>" width="300" height="200">
			</div>
			<div>
				<div class="movieCategory"><%=ct%></div>
				<div><%=vo.getMovieName()%></div>
				<div>
					상영시간:
					<%=vo.getRuntime()%></div>
				<div>
					정보 :
					<%=vo.getInfo()%></div>
				<div class="schedule_container">
					<div class="title">시간</div>
					<div class="schedule">
						<%
							for (ScheduleVO scheVO : scheList) {
							int seatCnt = dao.getSeatCnt(scheVO.getSchNo());
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							String date = sdf.format(scheVO.getRunDay());
							sdf = new SimpleDateFormat("HH:mm");
							String startTime = sdf.format(scheVO.getRunDay());
						%>
						<div class="runtime">
							<%=startTime%>
							/
							<%=date%>
						</div>
						<a href="/seat.do?schNo=<%=scheVO.getSchNo()%>">
						<div class="seat">
							<div><%= scheVO.getRoomNo() %>관</div>
							<div><%=seatCnt%></div>/20
							
						</div>
						</a>
						 
						<%
							}
						%>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/footer.jsp" />
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
		<%
			MovieVO vo = (MovieVO) request.getAttribute("vo");
		String ct = (String) request.getAttribute("ct");
		ArrayList<ScheduleVO> scheList = (ArrayList) request.getAttribute("scheList");

		MovieDAO dao = new MovieDAO();
		%>
		<div class="backImg">
			<img alt="" src="imgs/<%=vo.getImg()%>">
		</div>
		<div class="info">
			<!-- "/movieInfo.jsp?movieNo=<vo.getMovieNo()%>" -->
			<div class="infos">
				<div class="imovieCategory"><%=ct%></div>
				<div class="movieName"><%=vo.getMovieName()%></div>
				<div class="movieInfos">
					상영시간:
					<%=vo.getRuntime()%>분</div>
				<div class="movieInfos">
					정보 :
					<%=vo.getInfo()%></div>
				<div class="schedule_container">
					<div class="title">관람 시간 선택</div>
					<div class="schedule">
						<form action="/seat.do" method="post">
							<select width="400" name="schNo">
								<%
									for (ScheduleVO scheVO : scheList) {
									int seatCnt = dao.getSeatCnt(scheVO.getSchNo());
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
									String date = sdf.format(scheVO.getRunDay());
									sdf = new SimpleDateFormat("HH:mm");
									String startTime = sdf.format(scheVO.getRunDay());
								%>
								<option value="<%=scheVO.getSchNo()%>"><%=scheVO.getRoomNo()%>관
									(<%=seatCnt%> / 20)
									<%=startTime%> /
									<%=date%>
								</option>

								<%
									}
								%>
							</select>
							<input type="submit" value="빠른 예매" class="rev_button">
						</form>
					</div>
				</div>
			</div>
			<div class="info_img">
				<img alt="" src="imgs/<%=vo.getImg()%>">
			</div>
		</div>
	</div>
</div>
<jsp:include page="/footer.jsp" />
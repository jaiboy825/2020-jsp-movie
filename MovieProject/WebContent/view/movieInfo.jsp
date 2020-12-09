<%@page import="java.text.SimpleDateFormat"%>
<%@page import="movie.ScheduleVO"%>
<%@page import="movie.MovieDAO"%>
<%@page import="movie.MovieVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp" />
<div align="center">

	<table>
		<tr height="60">
			<td align="center" colspan="3"><font size="6" color="gray">영화
					목록</font></td>
		</tr>
		<%
			MovieVO vo = (MovieVO) request.getAttribute("vo");
		String ct = (String) request.getAttribute("ct");
		ArrayList<ScheduleVO> scheList = (ArrayList) request.getAttribute("scheList");
		MovieDAO dao = new MovieDAO();
		%>
		<tr height="220">
			<!-- "/movieInfo.jsp?movieNo=<vo.getMovieNo()%>" -->
			<td width="333" align="center"><img alt=""
				src="imgs/<%=vo.getImg()%>" width="300" height="200"> <br>
				<font><%=ct%></font> <br> <font size="10" color="gray"><b><%=vo.getMovieName()%></b></font>
				<br> <font>상영시간 : <%=vo.getRuntime()%></font> <br> <font>정보
					: <%=vo.getInfo()%></font>
				<div class="schedule_container">
					<div class="title">시간</div>
					<div class="schedule">
						<%
							for (ScheduleVO scheVo : scheList) {

							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							sdf = new SimpleDateFormat("HH:mm");
							String startTime = sdf.format(scheVo.getRunDay());
							String str_date = sdf.format(scheVo.getRunDay());
						%>
						<%=scheVo.getSchNo()%>
						<%
							}
						%>
					</div></td>
		</tr>
	</table>
</div>
<jsp:include page="/footer.jsp" />
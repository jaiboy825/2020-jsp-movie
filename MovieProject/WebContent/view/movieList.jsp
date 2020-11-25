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
			ArrayList<MovieVO> list = (ArrayList<MovieVO>) request.getAttribute("list");
		int j = 0;
		for (MovieVO vo : list) {
			if (j % 3 == 0) {
		%>
		<tr height="220">
			<%
				}
			%>
			<!-- "/movieInfo.jsp?movieNo=<vo.getMovieNo()%>" -->
			<td width="333" align="center"><a
				href="/movieInfo.do?movieNo=<%=vo.getMovieNo()%>"> <img alt=""
					src="imgs/<%=vo.getImg()%>" width="300" height="200">
			</a>
			<p>
					<font size="3" color="gray"><b><%=vo.getMovieName()%></b></font></td>
			<%
				j = j + 1;
			}
			%>
		</tr>
	</table>
</div>
<jsp:include page="/footer.jsp" />
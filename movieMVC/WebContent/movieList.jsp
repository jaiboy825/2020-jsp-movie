<%@page import="movie.MovieVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" />
<div align="center">
	<table>
		<tr height="60">
			<td align="center" colspan="3"><font size="6" color="gray">영화 목록</font></td>
		</tr>
		<%
			ArrayList<MovieVO> list =  (ArrayList<MovieVO>)request.getAttribute("list");
		%>
	</table>
</div>
<jsp:include page="footer.jsp" />
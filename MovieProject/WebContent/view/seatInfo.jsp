<%@page import="movie.MemberVO"%>
<%@page import="movie.TicketVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp" />
<%
	String id = (String) session.getAttribute("id");
	int schNo = (int) request.getAttribute("schNo");
	ArrayList<TicketVO> list = (ArrayList) request.getAttribute("ArrayList");
	
	String ok = (String) request.getAttribute("ticketOk");
	if (ok != null) {
		out.print("<script>alert('" + ok + "'); location.href = '/AllMovieList.do'; </script>");
		
	}
%>

<div class="container sicontainer">
	<div class="si_title">
		<h2>좌석 선택</h2>
	</div>
	<form action="/seat.do" method="post">
	<div class="seatInfo_container">
		<input type="hidden" value="<%=id%>" name="id"> <input
			type="hidden" value="<%=schNo%>" name="schNo">
		<div class="seat_list">
			<%
				boolean seatTictet;
			for (int i = 1; i <= 20; i++) {
				seatTictet = false;
				for (TicketVO vo : list) {
					if (vo.getSeatNo() == i) {
				seatTictet = true;
					}
				}
			%>
			<div class='seat_container <%=seatTictet ? "disable" : ""%>'>
				<input type="checkbox" class="seatOk" id="seat<%=i%>" value="<%=i%>" name="selectList"> 
				<label for='<%=seatTictet ? "" : "seat"+i%>'><%=seatTictet ? "X" : i%></label>
			</div>
			<%
				}
			%>
		</div>
		<div>
			<input type="submit" value="예매하기" id="rev">		
		</div>
	</div>
	</form>
</div>

<jsp:include page="/footer.jsp" />
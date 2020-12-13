<%@page import="movie.MemberVO"%>
<%@page import="movie.TicketVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp" />
<%
	String id = (String) session.getAttribute("id");
	//id 받기
	int schNo = (int) request.getAttribute("schNo");
	//schNo받기
	ArrayList<TicketVO> list = (ArrayList) request.getAttribute("list");
	//list 받기
	String ok = (String) request.getAttribute("ticketOk");
	//예매 완료일때 메시지 받기
	if (ok != null) {
		//ok != null 이라면 alert 창으로 띄우고 /AllMovieList.do 로 보낸다
	out.print("<script>alert('" + ok + "'); location.href = '/AllMovieList.do'; </script>");

}
%>

<div class="container sicontainer">
	<div class="si_title">
		<h2>좌석 선택</h2>
	</div>
	<form action="/seat.do" method="post">
		<div class="seatInfo_container">
			<input type="hidden" value="<%=id%>" name="id">  <!-- 티켓에 정보를 담아야 되기 떄문에 id 숨긴다  -->
			<input type="hidden" value="<%=schNo%>" name="schNo"> <!-- schNo도 담아야되는데 보여주긴 좀 그래서 숨긴다 -->
			<div class="seat_list">
			<!-- 좌석들을 보여줄거 -->
				<%
					boolean seatTictet;
				//예매가 되었는지 확인할 seatTicket
				for (int i = 1; i <= 20; i++) {
					seatTictet = false;
					//일단 false
					for (TicketVO vo : list) {
						if (vo.getSeatNo() == i) {
							//정보가 이미 있다면 true로 둔다
					seatTictet = true;
						}
					}
				%>
				<!-- 예매가 되어있는지 아닌지에 따라 다르게 모여주기 위해서 class를 추가하기 위해서 만든것들 -->
				<div class='seat_container <%=seatTictet ? "disable" : ""%>'>
				
					<!-- checkBox로 예매할 자리를 알아낸다 하지만 그대로 보여주기 별로여서 숨긴다 -->
					<input type="checkbox" class="seatOk" id="seat<%=i%>"
						value="<%=i%>" name="selectList"> 
						<!-- 대신 label로 표시하여 작동은 되게 한다 -->
						<label for='<%=seatTictet ? "" : "seat" + i%>'><%=seatTictet ? "X" : i%></label>
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
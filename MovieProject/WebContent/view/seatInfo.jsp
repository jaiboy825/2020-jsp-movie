<%@page import="movie.MemberVO"%>
<%@page import="movie.TicketVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp"></jsp:include>
<%
	int schNo = 0;
	if (request.getAttribute("schNo") != null) {
		schNo = (int) request.getAttribute("schNo");
	}
	ArrayList<TicketVO> list = new ArrayList();
	if (request.getAttribute("ArrayList") != null) {
		list = (ArrayList) request.getAttribute("ArrayList");
	}
	MemberVO memberVo = new MemberVO();
	if (session.getAttribute("user") != null) {
		memberVo = (MemberVO) session.getAttribute("user");
	}
%>

<div class="seat section">
	<div class="visual">
		<img src="./images/logo.jpg" alt="">
		<div class="text">
			<span class="bold">영화예매 사이트</span>
			<p>홈 > 영화 목록 > 예매 > 좌석 선택</p>
		</div>
	</div>
	<div class="container">
		<div class="section_title">
			<h1>좌석 선택</h1>
			<hr>
			<p>좌석을 클릭해주세요</p>
		</div>
		<form action="Seat.do" method="post">
			<div class="seat_list">
				<%
					for (int i = 1; i <= 20; i++) {
						boolean seat = false;
						for (TicketVO vo : list) {
							if (vo.getSeatNo() == i) {
								seat = true;
							}
						}
				%>
				<div class='item <%=seat ? "disable" : ""%>'>
					<input type="checkbox" class="seat_input" id="check_<%=i%>"
						value="<%=i%>" name="selectList"> <label
						for='<%=seat ? "" : "check_" + i%>'><%=seat ? "&times;" : i%></label>
				</div>
				<%
					}
				%>
			</div>
			<input type="hidden" value="<%=memberVo.getId()%>" name="id">
			<input type="hidden" value="<%=schNo%>" name="schNo">
			<div class="flex_e">
				<button class="btn0 btn1">예약하기</button>
			</div>
		</form>
	</div>
</div>


<jsp:include page="/footer.jsp"></jsp:include>
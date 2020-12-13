<%@page import="java.text.SimpleDateFormat"%>
<%@page import="movie.ScheduleVO"%>
<%@page import="movie.MovieDAO"%>
<%@page import="movie.MovieVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp" />
<!-- 영화 정보를 보여주기 위한 페이지 -->
<div class="container">

	<div class="info_container">
		<%
		MovieVO vo = (MovieVO) request.getAttribute("vo");
		//vo 받기
		String ct = (String) request.getAttribute("ct");
		//카테고리 받기
		ArrayList<ScheduleVO> scheList = (ArrayList) request.getAttribute("scheList");
		//scheList라는 스케줄 리스트를 받는다
		MovieDAO dao = new MovieDAO();
		//dao 부르기
		%>
		<div class="backImg">
			<img alt="" src="imgs/<%=vo.getImg()%>">
			<!-- 영화 사진 -->
		</div>
		<div class="info">
			<div class="infos">
				<div class="imovieCategory"><%=ct%></div> <!-- 카테고리 -->
				<div class="movieName"><%=vo.getMovieName()%></div> <!-- 영화이름 -->
				<div class="movieInfos">
					상영시간:
					<%=vo.getRuntime()%>분</div> <!-- 상영시간 -->
				<div class="movieInfos">
					정보 :
					<%=vo.getInfo()%></div> <!-- 정보 -->
				<div class="schedule_container"> <!-- 스케줄을 선택하기 위한 select   -->
					<div class="title">관람 시간 선택</div>
					<div class="schedule">
						<form action="/seat.do" method="post">
							<select width="400" name="schNo">
								<%
									for (ScheduleVO scheVO : scheList) {
										/* 스케줄 리스트를 하나씩 돌면서 */
									int seatCnt = dao.getSeatCnt(scheVO.getSchNo());
										/* seatCnt 받고  */
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
										/* 날짜 만들고 */
									String date = sdf.format(scheVO.getRunDay());
										/* 시간을 만든다 */
									sdf = new SimpleDateFormat("HH:mm");
									String startTime = sdf.format(scheVO.getRunDay());
								%>
								<!-- 그 정보들을 담는다 -->
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
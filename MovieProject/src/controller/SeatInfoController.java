package controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.MemberVO;
import movie.MovieDAO;
import movie.MovieVO;
import movie.TicketVO;

/**
 */
public class SeatInfoController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 한글 인코딩처리
		req.setCharacterEncoding("utf-8");
		//이 seatInfoController는 빠른 예매 버튼을 눌렀을떄 그 시간대 좌석 현황을 보여주고 선택한 좌석들을 담아서 처리해야 된다
		int schNo = Integer.parseInt(req.getParameter("schNo"));
		//먼저 그 시간대 좌석을 보여주어야 한다 그렇기 떄문에 schNo를 받는다
		MovieDAO dao = new MovieDAO();
		//MovieDAO 부르기
		ArrayList<TicketVO> list = dao.findTicket(schNo);
		//dao 의 findTicket에 받은 schNo를 담아서 보내고 처리된 결과를 list에 담는다
		if (list != null) {
			//list가 null이 아니라면 
			//ArrayList에 list를 담고
			//schNo도 따로 담아서 보낸다
			req.setAttribute("list", list);
			req.setAttribute("schNo", schNo);
		}
		//이번엔 선택한 좌석들을 list에 담아서 보낸것을 받는다
		String[] selectList = req.getParameterValues("selectList");
		//selectList가 null이 아니라면 예매를 한다는 것이고
		if (selectList != null) {
			//id를 받는다 (예매 정보를 저장하기 위해서 )
			String id = req.getParameter("id");
			//selectList의 길이만큼 for문
			for (int i = 0; i < selectList.length; i++) {
				//먼저 처음에 아무 좌석도 선택이 안된 스케줄이라면 db에 정보가 없기 떄문에 
				//비었는지 확인을 해준다 그게 getRoomEmpty이다
				//schNo를 보내서 있는지 없는지를 notEmpty에 받는다
				int notEmpty = dao.getRoomEmpty(schNo);
				//그리고 다음 티켓을 만들기 위해서 db에 있는 Ticket 들중 TicketNo의 제일 큰 번호를 받는다
				int ticketNo = dao.TicketMaxNo();
				//vo에 받은 정보들을 받는다 
				TicketVO vo = new TicketVO(ticketNo, new Date(123), schNo, Integer.parseInt(selectList[i]), id);
				//notEmpty > 0 이라면 
				if (notEmpty > 0) {
					//dao의 insertTicket에 vo를 담아서 처리하고 
					int status = dao.insertTicket(vo);
					//결과를 status에 따라 ticketOk에 담아서 보낸다
					if (status > 0) {
						req.setAttribute("ticketOk", "예매되었습니다.");
					}
				} else {
					// > 0 이 아니라면 없다는 것으로 방도 새로 만들어줘야 하기 떄문에 
					//dao 의 insertTicketAndNewRoom에 vo를 담아서 보내준다
					int status = dao.insertTicketAndNewRoom(vo);
					//똑같이 status에 따라 ticketOk에 담아서 보낸다
					if (status > 0) {
						req.setAttribute("ticketOk", "예매되었습니다.");
					}
				}
			}
		}
		//view폴더의 seatInfo로 보낸다
		req.getRequestDispatcher("/view/seatInfo.jsp").forward(req, resp);
	}
}

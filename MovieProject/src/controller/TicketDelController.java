package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.MovieDAO;

/**
 */
public class TicketDelController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 한글 인코딩처리
		req.setCharacterEncoding("utf-8");
		// 삭제할 ticket의 번호를 받는다
		int ticketNo = Integer.parseInt(req.getParameter("ticketNo"));
		// MovieDAO 부르기
		MovieDAO dao = new MovieDAO();
		// dao의 deleteTicket에 번호를 담아서 보내고 그 결과를 n에 받는다
		int n = dao.deleteTicket(ticketNo);
		if (n > 0) {
			// n > 0 이라면 성공이고 ok에 메시지를 담아서 index.jsp로 보낸다
			req.setAttribute("ok", "예매 취소 완료");
		}
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}

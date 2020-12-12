package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.MovieDAO;

/**
 * Servlet implementation class BookInsertController
 *
 * BookListController를 이용하기 위해서 BookList.do라는 경로로 왔을때 이 컨트롤러를 이용한다
 */
public class TicketDelController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 한글 인코딩처리
		req.setCharacterEncoding("utf-8");
		int ticketNo = Integer.parseInt(req.getParameter("ticketNo"));
		MovieDAO dao = new MovieDAO();
		int n = dao.deleteTicket(ticketNo);
		if(n > 0) {
			req.setAttribute("ok", "예매 취소 완료");
		}
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}

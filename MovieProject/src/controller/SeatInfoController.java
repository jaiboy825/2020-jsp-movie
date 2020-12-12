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
 * Servlet implementation class BookInsertController
 *
 * BookListController를 이용하기 위해서 BookList.do라는 경로로 왔을때 이 컨트롤러를 이용한다
 */
public class SeatInfoController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 한글 인코딩처리
		req.setCharacterEncoding("utf-8");
		int schNo = Integer.parseInt(req.getParameter("schNo"));
		MovieDAO dao = new MovieDAO();
		ArrayList<TicketVO> list = dao.findTicket(schNo);
		if (list != null) {
			req.setAttribute("ArrayList", list);
			req.setAttribute("schNo", schNo);
		}

		String[] selectList = req.getParameterValues("selectList");
		if (selectList != null) {
			String id = req.getParameter("id");
			for (int i = 0; i < selectList.length; i++) {
				int notEmpty = dao.getRoomEmpty(schNo);
				int ticketNo = dao.TicketMaxNo();
				TicketVO vo = new TicketVO(ticketNo, new Date(123), schNo, Integer.parseInt(selectList[i]), id);
				if (notEmpty > 0) {
					int status = dao.insertTicket(vo);
					if (status > 0) {
						req.setAttribute("ticketOk", "예매되었습니다.");
					}
				} else {
					int status = dao.insertTicketAndNewRoom(vo);
					if (status > 0) {
						req.setAttribute("ticketOk", "예매되었습니다.");
					}
				}
			}
		}
		req.getRequestDispatcher("/view/seatInfo.jsp").forward(req, resp);
	}
}

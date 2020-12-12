package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.BookVO;
import movie.MovieDAO;


/**
 * Servlet implementation class BookInsertController
 *
 *BookListController를 이용하기 위해서 BookList.do라는 경로로 왔을때 이 컨트롤러를 이용한다
 */
public class RevListController implements Controller{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 한글 인코딩처리
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		MovieDAO dao = new MovieDAO();
		ArrayList<BookVO> list = null;
		if(id == null) {
			list = dao.getTicketList("all");
		}else {
			list = dao.getTicketList(id);
		}
		
		req.setAttribute("list", list);
		req.getRequestDispatcher("/view/myTicket.jsp").forward(req, resp);
 
	}
}

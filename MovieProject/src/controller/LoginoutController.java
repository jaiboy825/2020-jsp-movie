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

import movie.MovieDAO;
import movie.MovieVO;

/**
 * Servlet implementation class BookInsertController
 *
 * BookListController를 이용하기 위해서 BookList.do라는 경로로 왔을때 이 컨트롤러를 이용한다
 */
public class LoginoutController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 한글 인코딩처리
		req.setCharacterEncoding("utf-8");
		String type = req.getParameter("type");
		MovieDAO dao = new MovieDAO();
		String path;
		if (type == null) {
			String id = req.getParameter("id");
			String password = req.getParameter("password");
			String n = dao.findUser(id, password);
			if (n != "") {
				req.setAttribute("id", n);
				path = "/index.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
			} else {
				req.setAttribute("error", "정보가 없습니다");
				path = "/index.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
			}
		} else {
			req.setAttribute("logout", "로그아웃 되었습니다.");
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		}
	}
}

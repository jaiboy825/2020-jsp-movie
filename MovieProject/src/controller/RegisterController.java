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

/**
 * Servlet implementation class BookInsertController
 *
 * BookListController를 이용하기 위해서 BookList.do라는 경로로 왔을때 이 컨트롤러를 이용한다
 */
public class RegisterController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 한글 인코딩처리
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		String pass = req.getParameter("password");
		String passC = req.getParameter("passwordC");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		Date birth = Date.valueOf(req.getParameter("birth"));
		MovieDAO dao = new MovieDAO();
		MemberVO vo = new MemberVO(id, pass, email, phone, birth);
//			if (n != "") {
//				req.setAttribute("id", n);
//				path = "/index.jsp";
//				req.getRequestDispatcher(path).forward(req, resp);
//			} else {
//				req.setAttribute("error", "정보가 없습니다");
//				path = "/index.jsp";
//				req.getRequestDispatcher(path).forward(req, resp);
//			}
		if(pass.equals(passC)) {
			int n = dao.UserRegister(vo);
			if(n > 0) {
				req.setAttribute("success", "회원 가입 성공");
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
			}else {
				req.setAttribute("error", "회원 가입 실패");
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
			}
		}else {
			req.setAttribute("error", "비밀번호와 비밀번호 확인란의 값이 다릅니다.");
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		}
	}
}

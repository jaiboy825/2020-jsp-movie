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
 * /Login.do라는 경로로 왔을때 이 컨트롤러를 이용한다
 */
public class LoginoutController implements Controller {
	//로그인 로그아웃을 처리하는 컨트롤러이다
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 한글 인코딩처리
		req.setCharacterEncoding("utf-8");
		//로그인인지 아닌지를 확인하는 type 받는다
		String type = req.getParameter("type");
		//dao 부르기
		MovieDAO dao = new MovieDAO();
		//따로 생각해둔게 있는데 구현을 안해서 사실은 필요없는 path
		String path;
		if (type == null) {
			//type이 null이라면 로그인이다
			String id = req.getParameter("id");
			//id 를 받아온다
			String password = req.getParameter("password");
			//password를 받아온다
			String n = dao.findUser(id, password);
			//dao에 있는 findUser에 값을 넣는다
			if (n != "") {
				//n이 ""가 아니라면 정보가 있는것이기에 
				req.setAttribute("id", n);
				//req에 id값을 담아서
				path = "/index.jsp";
				//index.jsp로 보낸다
				req.getRequestDispatcher(path).forward(req, resp);
			} else {
				//n이 ""이라면 일치하는 정보가 없는것이라는 뜻으로
				req.setAttribute("error", "정보가 없습니다");
				//error라는 이름의 메시지를 담아서
				path = "/index.jsp";
				//index.jsp로 보낸다
				req.getRequestDispatcher(path).forward(req, resp);
			}
		} else {
			//type이 있다면 로그아웃으로
			req.setAttribute("logout", "로그아웃 되었습니다.");
			//logout이라는 메시지를 담아서 index.jsp로 보낸다
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		}
	}
}

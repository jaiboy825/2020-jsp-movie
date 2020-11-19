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
 *BookListController를 이용하기 위해서 BookList.do라는 경로로 왔을때 이 컨트롤러를 이용한다
 */
@WebServlet("/AllMovieList.do")
public class MovieAllListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// doProcess에서 처리
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// doProcess에서 처리
		doProcess(req, resp);
	}

	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 한글 인코딩처리
		req.setCharacterEncoding("utf-8");
		String category = req.getParameter("category");
		MovieDAO dao = new MovieDAO();
		ArrayList<MovieVO> list = null;
		if(category == null) {
			list = dao.getMovieList("all");			
		}else {
			list = dao.getMovieList(category);
		}
		
		
		req.setAttribute("list", list);
		//MovieDAO 부르기
		//dao에 있는 getList로 테이블에 있는 값들을 BookVO형식으로 list에 담는다
//		ArrayList<MovieVO> list = dao.getMovieList(category);
		//list라는 항목에 list를 담아서 selectBook.jsp로 보낸다
		//list를 selectBook.jsp로 forward
		req.getRequestDispatcher("/movieList.jsp").forward(req, resp);
		
		//여담으로 방과후로 frontController 방식과 여러 mvc2패턴을 공부하다보니 jsp가 100배는 재미있어진것 같다
		//구조를 잡기에도 쉬운거 같고 집에서 html js를 공부하다보면 jsp에서 사용하는 몇몇의 방식을 사용하고 싶은 생각이 들때가 있어서 신기했다
		//사실은 기능반을 하면서 js가 더 익숙했었는데 이제는 jsp가 좀 더 편해진거같은 느낌도 든다
	}
}

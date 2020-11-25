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
public class MovieAllListController implements Controller{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		req.getRequestDispatcher("/view/movieList.jsp").forward(req, resp);

	}
}

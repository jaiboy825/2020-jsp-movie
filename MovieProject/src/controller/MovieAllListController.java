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
 * /AllMovieList.do라는 경로로 왔을때 이 컨트롤러를 이용한다
 */
public class MovieAllListController implements Controller{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 한글 인코딩처리
		req.setCharacterEncoding("utf-8");
		//헤더에서 장르를 선택하면 그에 맞는 영화 리스트를 보여줘야 하기 떄문에 
		//category를 받는다
		String category = req.getParameter("category");
		//MovieDAO 부르기
		MovieDAO dao = new MovieDAO();
		//ArrayList를 만들고
		ArrayList<MovieVO> list = null;
		//category가 null이라면 모든 종류의 영화를 리스트에 담고
		if(category == null) {
			//all로 보냄
			list = dao.getMovieList("all");			
		}else {
		//아니라면 그 장르에 맞는 영화들을 리스트를 담는다
			list = dao.getMovieList(category);
		}
		
		//그 리스트를 담아서 /view 폴더에 있는 movieList.jsp 로 보낸다
		req.setAttribute("list", list);
		req.getRequestDispatcher("/view/movieList.jsp").forward(req, resp);

	}
}

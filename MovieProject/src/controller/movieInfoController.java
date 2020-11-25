package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.MovieDAO;
import movie.MovieVO;

public class movieInfoController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 한글 인코딩처리
		req.setCharacterEncoding("utf-8");
		String movieNo = req.getParameter("movieNo");
		MovieDAO dao = new MovieDAO();
		MovieVO list = null;
		list = dao.getMovieInfo(movieNo);

		req.setAttribute("list", list);
		req.getRequestDispatcher("/view/movieList.jsp").forward(req, resp);

	}

}

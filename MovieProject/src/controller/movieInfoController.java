package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.MovieDAO;
import movie.MovieVO;
import movie.ScheduleVO;

public class movieInfoController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 한글 인코딩처리
		req.setCharacterEncoding("utf-8");
		String movieNo = req.getParameter("movieNo");
		MovieDAO dao = new MovieDAO();
		MovieVO vo = dao.getMovieInfo(movieNo);
		String category = dao.getCategory(movieNo);
		ArrayList<ScheduleVO> scheList = dao.getSchedule(vo.getMovieNo());
		req.setAttribute("vo", vo);
		req.setAttribute("ct", category);
		req.setAttribute("scheList", scheList);
		req.getRequestDispatcher("/view/movieInfo.jsp").forward(req, resp);
		
	}

}

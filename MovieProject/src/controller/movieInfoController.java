package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.MovieDAO;
import movie.MovieVO;
import movie.ScheduleVO;

/**
 * /movieInfo.do라는 경로로 왔을때 이 컨트롤러를 이용한다
 */
public class movieInfoController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 한글 인코딩처리
		req.setCharacterEncoding("utf-8");
		// 선택한 영화의 정보를 보여주기 위해 그 영화의 movieNo를 받는다
		String movieNo = req.getParameter("movieNo");
		//MovieDAO 부르기
		MovieDAO dao = new MovieDAO();
		//MovieVO를 부르고 vo에다가 dao에 있는 getMovieInfo로 그 영화의 정보들을 받아서 담는다
		MovieVO vo = dao.getMovieInfo(movieNo);
		//영화 category decode 하는거 까먹었어서 따로 만들어서 사용 ㅋㅋ
		String category = dao.getCategory(movieNo);
		//그 영화의 스케줄 리스트를 dao의 getSchedule에 MovieNo로 검색하여 받아서 담는다
		ArrayList<ScheduleVO> scheList = dao.getSchedule(vo.getMovieNo());
		//vo에는 vo를
		req.setAttribute("vo", vo);
		//ct에는 카테고리를 
		req.setAttribute("ct", category);
		//scheList에는 스케줄 리스트를 담는다
		req.setAttribute("scheList", scheList);
		//그리고 /view 폴더에 있는 movieInfo.jsp 로 이동한다
		req.getRequestDispatcher("/view/movieInfo.jsp").forward(req, resp);
		
	}

}

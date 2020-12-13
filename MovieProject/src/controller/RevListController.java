package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.BookVO;
import movie.MovieDAO;


/**
 */
public class RevListController implements Controller{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 한글 인코딩처리
		req.setCharacterEncoding("utf-8");
		//권한에 따라 예약현황을 다르게 보여줘야 하기 떄문에 id 를 받는다
		String id = req.getParameter("id");
		//MovieDAO를 부른다
		MovieDAO dao = new MovieDAO();
		//BookVO를 담을수 있는 list를 만들어준다
		ArrayList<BookVO> list = null;
		if(id == null) {
			//id가 null이라면 admin 계정인것으로 dao의 getTicketList로 "all"이라는 정보를 담아서 보낸다
			list = dao.getTicketList("all");
		}else {
			//null이 아니라면 일반 유저로 그 유저의 정보만 보여주기 위해 getTicketList로 그 id 를 담아서 보낸다
			list = dao.getTicketList(id);
		}
		//받은 list를 "list"에 담아서 /view폴더의 myTicket.jsp로 보낸다
		req.setAttribute("list", list);
		req.getRequestDispatcher("/view/myTicket.jsp").forward(req, resp);
 
	}
}

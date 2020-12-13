package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.MemberVO;
import movie.MovieDAO;

/**
 */
public class RegisterController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 한글 인코딩처리
		req.setCharacterEncoding("utf-8");
		//id 받기
		String id = req.getParameter("id");
		//password 받기
		String pass = req.getParameter("password");
		//passwordC받기 (확인용)
		String passC = req.getParameter("passwordC");
		//email 받기
		String email = req.getParameter("email");
		//phone 받기
		String phone = req.getParameter("phone");
		//birth 받기
		Date birth = Date.valueOf(req.getParameter("birth"));
		// MovieDAO 부르기
		MovieDAO dao = new MovieDAO();
		//vo에 받은 정보들을 담아서 
		MemberVO vo = new MemberVO(id, pass, email, phone, birth);
		//받은 비밀번호와 비밀번호 확인이 같다면
		if(pass.equals(passC)) {
			//dao의 UserRegister로 보낸다
			int n = dao.UserRegister(vo);
			//n > 0 이라면 성공
			if(n > 0) {
				// success 라는 메시지를 담아서 index.jsp 로 보낸다
				req.setAttribute("success", "회원 가입 성공");
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
			}else {
				//아니라면 아이디 중복
				req.setAttribute("error", "아이디가 중복됩니다");
				//error라는 이름의 메시지를 담아서 index.jsp로 보낸다
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
			}
		}else {
			//다르다면 다르다는 내용의 error 메시지를 register.jsp로 보낸다
			req.setAttribute("error", "비밀번호와 비밀번호 확인란의 값이 다릅니다.");
			req.getRequestDispatcher("/register.jsp").forward(req, resp);
		}
	}
}

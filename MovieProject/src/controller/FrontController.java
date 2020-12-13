package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class frontController
 */
@WebServlet(name = "front", urlPatterns = { "*.do" })
//이름은 front .do로 오는 것들을 받는다
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String charset = null;
	HashMap<String, Controller> list = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// url과 sub controller 맵핑
		charset = config.getInitParameter("charset");
		
		//list를 만들어서 ~.do 로 보내지는 것들을 저장하고 그에 맞는 컨트롤러를 연결해준다
		list = new HashMap<String, Controller>();
		list.put("/AllMovieList.do", new MovieAllListController());
		list.put("/Login.do", new LoginoutController());
		list.put("/movieInfo.do", new movieInfoController());
		list.put("/Register.do", new RegisterController());
		list.put("/seat.do", new SeatInfoController());
		list.put("/MyRevList.do", new RevListController());
		list.put("/delTicket.do", new TicketDelController());
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(charset);

		String url = request.getRequestURI(); // ex) /dev/memberSearch.do
		String contextPath = request.getContextPath(); // ex) /dev
		String path = url.substring(contextPath.length()); // ex)

		Controller subController = list.get(path);
		subController.execute(request, response);
	}

}
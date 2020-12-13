package movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieDAO {

	public ArrayList<MovieVO> getMovieList(String category) {
		// 보낼 list를 ArrayList<MovieVO> 형식으로 만든다
		ArrayList<MovieVO> movieList = new ArrayList<MovieVO>();
		// conn 과 pstmt rs 불러온다
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 모든 영화 정보를 받기 위한 sql
			String sql = "SELECT * FROM movie";
			// category 가 all이라면 모든 정보
			if (category.equals("all")) {
				pstmt = conn.prepareStatement(sql);
			} else {
				// 아니라면
				// 특정 category에 맞게 검색
				sql = "SELECT * FROM movie WHERE category = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, category);
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// rs.next라면
				// 모든 정보들을 bean에 담아서 list에 넣는다
				do {
					MovieVO bean = new MovieVO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
							rs.getString(5), rs.getString(6));
					movieList.add(bean);
					bean.toString();
				} while (rs.next());
			} else {
				System.out.println("리스트 가져오기 실패");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}

		// 모든 값을 담은 list를 리턴
		return movieList;
	}

	public String findUser(String id, String password) {
		String n = "";
		// conn 과 pstmt rs 불러온다
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 받은 id와 password에 해당하는 유저가 있는지 검색하는 sql
			String sql = "SELECT * FROM member WHERE id = ? and pw = ?";
			pstmt = conn.prepareStatement(sql);
			// 검색
			pstmt.setString(1, id);
			// id를 1번째
			pstmt.setString(2, password);
			// pw를 2번째
			rs = pstmt.executeQuery();
			// 검색이니까 Query
			if (rs.next()) {
				// 정보를 담는다
				n = rs.getString(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return n;
		// return
	}

	public int UserRegister(MemberVO vo) {
		// conn 과 pstmt rs 불러온다
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int n = 0;
		// 결과를 받을 n
		try {
			conn = DBUtil.getConnection();
			// 받은 절보들을 삽입할 sql
			String sql = "insert into member values(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			// vo에 담겨있는 id, password, email, phone, birth를 각각 pstmt에 담아서 처리
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getPhone());
			pstmt.setDate(5, vo.getBirth());

			n = pstmt.executeUpdate();
			// 결과를 n에 받고 insert니까 Update 이다
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return n;
		// 결과 리턴

	}

	public MovieVO getMovieInfo(String movieNo) {
		// 리턴할 vo 생성
		MovieVO vo = new MovieVO();
		// conn 과 pstmt rs 불러온다
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 선택한 영화의 정보르 보여주기 위한 sql
			String sql = "SELECT * FROM movie WHERE movieNo = ?";
			pstmt = conn.prepareStatement(sql);
			// 받은 영화 번호 사용
			pstmt.setString(1, movieNo);
			// 검색이니까 Query
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo.setMovieNo(rs.getInt(1));
				vo.setMovieName(rs.getString(2));
				vo.setCategory(rs.getInt(3));
				vo.setRuntime(rs.getInt(4));
				vo.setImg(rs.getString(5));
				vo.setInfo(rs.getString(6));
				// 정보들을 vo에 담는다
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return vo;
		// vo 리턴
	}

	public String getCategory(String movieNo) {
		// decode를 다른데서 안쓰고 실수해서 만들어진거
		String temp = null;
		// conn 과 pstmt rs 불러온다
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// category 받기
			String sql = "SELECT DECODE(category, 01, '액션',02, '로맨스', 03, '코미디', 04, '스릴러', 05, '애니메이션') category FROM movie WHERE movieNo = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movieNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				temp = rs.getString(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		// category 보내기
		return temp;
	}

	public ArrayList<ScheduleVO> getSchedule(int movieNo) {
		// 스케줄을 담을 list 생성
		ArrayList<ScheduleVO> list = new ArrayList<ScheduleVO>();
		// conn 과 pstmt rs 불러온다
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// movie와 schedule 테이블을 join해서 정보 받기
			String sql = "SELECT movieName , mt.runtime , mt.movieNo , st.schNo , st.roomNo , runDay FROM movie mt , schedule st WHERE mt.movieNo = st.movieNo AND mt.movieNo = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieNo);
			// 스케줄을 받을 movieNo 사용
			rs = pstmt.executeQuery();
			// 검색이니까 query
			while (rs.next()) {
				// 받은 결과들을 vo에 담아서 만들어둔 list에 담는다
				ScheduleVO vo = new ScheduleVO(rs.getString("movieName"), rs.getInt("schNo"), rs.getInt("movieNo"),
						rs.getTimestamp("runDay"), rs.getInt("runtime"), rs.getInt("roomNo"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		// 결과를 담은 list 리턴
		return list;
	}

	public int getRoomEmpty(int schNo) {
		// 한번도 Room이 생성이 안된애들을 위해서 만든거
		int n = 0;
		// 결과를 받을 n
		// conn 과 pstmt rs 불러온다
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// schNo에 해당하는 room이 있는지 검색
			String sql = "SELECT * FROM room  WHERE schNo = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, schNo);
			rs = pstmt.executeQuery();
			// 검색이니까 query
			if (rs.next()) {
				// 결과 받기
				n = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);

		}
		// 결과 리턴
		return n;
	}

	public int getSeatCnt(int schNo) {
		// 원래 쓰던 sql 쓰니까 이상하게 나와서 만든거 seatCnt만 받기 위한거
		int n = 0;
		// conn 과 pstmt rs 불러온다
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// seatCnt를 검색
			String sql = "SELECT seatcnt FROM schedule st, room ro where st.schNo = ? AND ro.schNo = st.schNo";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, schNo);
			rs = pstmt.executeQuery();
			// 검색이니까 query
			while (rs.next()) {
				// n에 결과 받기
				n = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		// 결과 리턴
		return n;
	}

	public ArrayList<TicketVO> findTicket(int schNo) {
		// 빠른 예매를 했을때 이미 예매된 자리들을 처리하기 위한거
		// 정보들을 받을 list
		ArrayList<TicketVO> list = new ArrayList<TicketVO>();
		// conn 과 pstmt rs 불러온다
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// schNo에 해당하는 정보들을 검색
			String sql = "select * from ticket WHERE schNo = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, schNo);
			rs = pstmt.executeQuery();
			// 검색이니까 query
			while (rs.next()) {
				// vo에 결과들을 담아서 list에 담기
				TicketVO vo = new TicketVO(rs.getInt("ticketNo"), rs.getDate("bookDate"), rs.getInt("schNo"),
						rs.getInt("seatNo"), rs.getString("id"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		// list 리턴
		return list;
	}

	public int insertTicket(TicketVO vo) {
		// 예매 하기 위한 거
		int n = 0;
		// conn 과 pstmt rs 불러온다
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		// sql 여러번 써야되서 미리 생성
		try {
			// 먼저 ticket 테이블에 정보들 입력
			sql = "insert into ticket values(? , SYSDATE , ? , ? , ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getTicketNo());
			pstmt.setInt(2, vo.getSchNo());
			pstmt.setInt(3, vo.getSeatNo());
			pstmt.setString(4, vo.getId());
			n = pstmt.executeUpdate();
			// ---------------------------------------------------------------
			// 그리고 그 관의 seatCnt를 올린다
			sql = "UPDATE room set seatCnt = seatCnt+1 WHERE schNo = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getSchNo());
			n = pstmt.executeUpdate();
			// 둘다 insert update 니까 Update사용
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return n;
		// 결과 리턴
	}

	public int insertTicketAndNewRoom(TicketVO vo) {
		// 방을 새로 생성해줘야 하기 떄문에 만든거
		int n = 0;
		// conn 과 pstmt rs 불러온다
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// sql 여러번 써야되서 미리 생성
		String sql;
		// roomNo를 저장하기 위해서
		int roomNo = 0;
		try {
			// 먼저 예매할 영화의 schNo에 해당하는 roomNo를 받는다
			sql = "select roomNo from schedule where schNo = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getSchNo());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				roomNo = rs.getInt(1);
			}
			// -----------------------------------------------------------
			// rooNo를 사용해서 room에 insert 한다
			sql = "insert into room values(?,?,1)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNo);
			pstmt.setInt(2, vo.getSchNo());
			n = pstmt.executeUpdate();

			// -------------------------------------------------------------
			// 그리고 ticket에 정보를 입력한다
			sql = "insert into ticket values(? , SYSDATE , ? , ? , ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getTicketNo());
			pstmt.setInt(2, vo.getSchNo());
			pstmt.setInt(3, vo.getSeatNo());
			pstmt.setString(4, vo.getId());
			n = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return n;
		// 결과 리턴
	}

	public int TicketMaxNo() {
		// 새 티켓을 위해 제일 큰값 생성
		// conn 과 pstmt rs 불러온다
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// max값 담기 위한 max
		int max = 0;
		try {
			String sql = "SELECT MAX(ticketNo) FROM ticket";
			// ticketNo의 max값 받기
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				max = rs.getInt(1) + 1;
				// 새롭게 하기 위해서 max값의 +1 을 한 값을 저장
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		// max값 리턴
		return max;
	}

	public ArrayList<BookVO> getTicketList(String id) {
		// ticket list를 받기 위한거 내 예매 목록이나 관리자 예매 현황에 사용
		// list 생성
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		// conn 과 pstmt rs 불러온다
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// id에 해당하지않고 모든 정보들 검색
			String sql = "SELECT  mt.movieName, DECODE(category, 01, '액션',02, '로맨스', 03, '코미디', 04, '스릴러', 05, '애니메이션') category, img, mt.runtime, runDay, roomNo, seatNo, tt.id, tt.ticketNo FROM movie mt, ticket tt, schedule st where tt.schNo = st.schNo and st.movieNo = mt.movieNo";
			if (id.equals("all")) {
				// 받은 id가 all이라면 모든정보를
				pstmt = conn.prepareStatement(sql);
			} else {
				// 아니라면 그 id에 해당하는 정보들을 검색
				sql = "SELECT mt.movieName, DECODE(category, 01, '액션',02, '로맨스', 03, '코미디', 04, '스릴러', 05, '애니메이션') category, img, mt.runtime, runDay, roomNo, seatNo, tt.id, tt.ticketNo FROM movie mt, ticket tt, schedule st where tt.schNo = st.schNo and st.movieNo = mt.movieNo and tt.id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// 결과 값들을 vo에 담아서 list에 담는다
				BookVO vo = new BookVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),
						rs.getTimestamp("runDay"), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getInt(9));
				list.add(vo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		// list 리턴
		return list;
	}

	public int deleteTicket(int ticketNo) {
		// 특정 티켓을 삭제하기 위한 거
		int n = 0;
		// conn 과 pstmt rs 불러온다
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql; // sql 여러번 써야댐
		int schNo = 0;
		// schNo 담기 위한거
		int seatCnt = 0;
		// seatCnt 담기 위한거
		try {
			// schNo 얻기
			sql = "select schNo from ticket where ticketNo = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ticketNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				schNo = rs.getInt(1);
			}
			// schNo에 해당하는 Room cnt 줄이기
			sql = "select seatCnt from room where schNo = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, schNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				seatCnt = rs.getInt(1);
			}
			// seatCnt 업데이트
			sql = "UPDATE room set seatCnt = ? WHERE schNo = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seatCnt - 1);
			pstmt.setInt(2, schNo);
			n = pstmt.executeUpdate();
			// Ticket 지우기
			sql = "delete from ticket where ticketNo = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ticketNo);
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt);
		}
		// 결과값 리턴
		return n;
	}

}

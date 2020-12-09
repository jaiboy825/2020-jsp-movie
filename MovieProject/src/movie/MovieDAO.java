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
			String sql = "SELECT * FROM movie";
			if (category.equals("all")) {
				pstmt = conn.prepareStatement(sql);
			} else {
				sql = "SELECT * FROM movie WHERE category = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, category);
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
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
			String sql = "SELECT * FROM member WHERE id = ? and pw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				n = rs.getString(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return n;
	}

	public int UserRegister(MemberVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int n = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into member values(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getPhone());
			pstmt.setDate(5, vo.getBirth());

			n = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);;
		}
		return n;

	}

	public MovieVO getMovieInfo(String movieNo) {
		MovieVO vo = new MovieVO();
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM movie WHERE movieNo = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movieNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo.setMovieNo(rs.getInt(1));
				vo.setMovieName(rs.getString(2));
				vo.setCategory(rs.getInt(3));
				vo.setRuntime(rs.getInt(4));
				vo.setImg(rs.getString(5));
				vo.setInfo(rs.getString(6));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return vo;
	}

	public String getCategory(String movieNo) {
		String temp = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
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
		return temp;
	}

	public ArrayList<ScheduleVO> getSchedule(int movieNo) {
		ArrayList<ScheduleVO> list = new ArrayList<ScheduleVO>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT movieName , mt.runtime , mt.movieNo , st.schNo , st.roomNo , runDay, ro.seatcnt FROM movie mt , schedule st, room ro WHERE mt.movieNo = st.movieNo AND mt.movieNo = ? AND ro.schNo = st.schNo";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ScheduleVO vo =
					new ScheduleVO(
						rs.getString("movieName"), rs.getInt("schNo"),
						rs.getInt("movieNo"), rs.getTimestamp("runDay"),
						rs.getInt("runtime"), rs.getInt("roomNo"),
						rs.getInt("seatCnt"));
				System.out.println(rs.getInt("schNo"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt, rs);;
		}

		return list;
	}
}

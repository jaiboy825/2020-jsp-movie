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
				n = id;
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
		return 0;
		
	}
	public MovieVO getMovieInfo(String movieNo) {
		MovieVO vo = new MovieVO();
		return vo;
	}
}

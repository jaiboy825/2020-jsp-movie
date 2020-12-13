package movie;

import java.sql.*;

public class DBUtil {
	public static Connection getConnection() {
		//연동 후 리턴 
		Connection conn = null;
		try {
			//driver 이름
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//id pw 주소
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "1234");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return conn;
		
	}
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		//상황에 따라서 close를 다르게 쓰기 위해 이것을 rs까지 있는 close 이다
		if(rs != null) {
			//null이 아니라면 close 시키고 다른 close로 나머지를 보낸다
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("rs");
			}
		}
		close(conn, pstmt);
	}
	
	public static void close(Connection conn, PreparedStatement pstmt) {
		//conn 과 pstmt 를 받아서 
		//null이 아니라면 close를 시킨다
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("pstmt");
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("conn");
			}
		}
	}
}

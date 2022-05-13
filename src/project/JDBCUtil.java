package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {
	public static Connection getConnection() {
		Connection conn = null;
		
		// 드라이버 객체 생성
		try {
			DriverManager.registerDriver(new org.h2.Driver());
			
			//DB  연결
			String jdbcUrl = "jdbc:h2:tcp://localhost/~/test";
			conn=DriverManager.getConnection(jdbcUrl,"sa","");
									
		} catch (SQLException e) {
			e.printStackTrace();
		} return conn; 
	}
	
	// DB 연결 종료
	public static void close(PreparedStatement stmt, Connection conn) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
	// DB 종료 오버로딩 (SELECT용)
	public static void close(ResultSet rs,PreparedStatement stmt, Connection conn) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}


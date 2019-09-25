package db;

import java.sql.*;

// 불필요한 코드나 수정될 수 있는 코드들을 담는 메소드
public class DBHelper {
	// Connection에서 수정될 수 있고 반복되는 코드를 담는 메소드
	// return 타입은 Connection을 담고, param 매개변수는 없다.
	public static Connection getConnection() throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/employees", "root", "java1234");
		return conn;
	}
	
	// Dao에서 finally에 rs, stmt, conn을 종료시키는 불필요한 긴 코드를 담는 메소드
	// return 타입은 (void) 없고, param 매개변수로는 rs, stmt, conn을 가진다.
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		// 조건문을 이용하여 매개변수로 받아온 값이 null 값이 아닌 기준으로 각 변수를 종료 시킨다.
		if(rs != null) {
			try {
				rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(rs != null) {
			try {
				rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(rs != null) {
			try {
				rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}

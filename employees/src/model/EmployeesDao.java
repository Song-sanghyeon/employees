package model;

import java.sql.*;

public class EmployeesDao {
	public int selectEmployeesCount() {
		// 쿼리문 작동시켜서 나온 값을 리턴값으로 넘기기위한 변수 만들고 초기화
		int count = 0;
		final String sql = "SELECT COUNT(*) FROM employees";
		// DB연결 변수 만들고 초기화
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		// 예외가 발생할 수 있는 메소드들이 존재하기 때문에 try catch문을 이용 
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/employees","root","java1234");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("COUNT(*)");
				System.out.print(count);
			}
			// try문 안에 또다른 try문을 만들었는데 둘다 e로 만들었는데 가능한 이유
		} catch(Exception e) { // 자바의 변수 생명주기는 {} 블럭이기 떄문이다. 두번째 try문에 있는 e와는 다른것
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace(); // 콘솔창에 예외를 모두 출력 (사용자는 볼 수 없다)
			}
		}
		
		return count;
	}
}

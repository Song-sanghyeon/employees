package model;

import java.sql.*;
import java.util.*;

import vo.Employees;

public class EmployeesDao {
	// emplyees테이블의 데이터의 양이 크기떄문에 limit을 이용하여 데이터를 출력하는 메소드
	// return : List<Employees> param : int Limit <- 직접 limit을 정할 수 있기떄문에
	public List<Employees> selectEmployeesListByLimit(int Limit) {
		// list 호출
		List<Employees> list = new ArrayList<Employees>();
		// 매개변수로 받아온 Limit의 값을 sql문에 대입할 변수에 값 저장
		int limit = Limit;
		// 받아온 Limit의 값이 제대로 되었는지 확인
		System.out.println("Dao / selectEmployeesListByLimit : "+Limit);
		
		// DB연결 변수 만들고 초기화
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// 쿼리문 작동시켜서 나온 값을 list에 저장하기 위한 쿼리문.
		final String sql = "SELECT emp_no, birth_date, first_name, last_name, gender, hire_date FROM employees LIMIT ?";
		
		// 예외가 발생할 수 있는 메소드들이 존재하기 때문에 try catch문을 이용 
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/employees","root","java1234");
			stmt = conn.prepareStatement(sql);
			// 쿼리문 수정, 변경 가능한 값 설정
			stmt.setInt(1, limit);
			rs = stmt.executeQuery();
			
			// 오류가 나는지 안나는지 확인
			System.out.println("Dao / selectEmployeesListByLimit 첫번째 try문 오류 X");
			while(rs.next()) {
				Employees employees = new Employees();
				employees.setEmpNo(rs.getInt("emp_no"));
				employees.setBirthDate(rs.getString("birth_date"));
				employees.setFirstName(rs.getString("first_name"));
				employees.setLastName(rs.getString("last_name"));
				employees.setGender(rs.getString("gender"));
				employees.setHireDate(rs.getString("hire_date"));
				// 값을 리스트에 저장
				list.add(employees);
			}
			// try문 안에 또다른 try문을 만들었는데 둘다 e로 만들었는데 가능한 이유
		} catch(Exception e) { // 자바의 변수 생명주기는 {} 블럭이기 떄문이다. 두번째 try문에 있는 e와는 다른것
			e.printStackTrace();
			// 오류가 나는지 안나는지 확인
			System.out.println("Dao / selectEmployeesListByLimit 첫번째 try문 속 catch 오류 O");
		} finally { // java실행이 종료되지않는이상 반드시 작동하는 finally
			try { // 사용한 값을 담는 변수를 종료해야한다.
				rs.close();
				stmt.close();
				conn.close();
				// 오류가 나는지 안나는지 확인
				System.out.println("Dao / selectEmployeesListByLimit 두번째 try문 오류 X");
			} catch(Exception e) {
				e.printStackTrace(); // 콘솔창에 예외를 모두 출력 (사용자는 볼 수 없다)
				// 오류가 나는지 안나는지 확인
				System.out.println("Dao / selectEmployeesListByLimit 두번째 try문 속 catch 오류 O");
			}
		}
		// return값으로 list를 넘긴다.
		return list;
	}
	
	// index.jsp에서 employees테이블의 데이터 행의 수를 출력하기 위한 메소드
	public int selectEmployeesCount() {
		int count = 0;
		
		return count;
	}
}

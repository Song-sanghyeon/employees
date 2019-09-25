package model;

import java.sql.*;
import java.util.*;
import vo.*;

public class DepartmentsDao {
	// 퇴사자를 제외한 현재 사원의 수만 구하여 각 부서의 사원의 총 합을 구하는 메소드
	// return 값은 List>Map>, param 매개변수는 없다.
	public List<Map<String, Object>> selectDepartmentsCountByDeptNo() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// db 연결
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT d.dept_no, d.dept_name, COUNT(d.dept_no) FROM dept_emp de INNER JOIN departments d ON de.dept_no = d.dept_no WHERE de.to_date = '9999.01.01' GROUP BY d.dept_no ORDER BY COUNT(d.dept_no) desc";
		
		// try catch문을 이용하여 DB연결 하는데에 있어서 오류가 나는지 확인
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/employees","root","java1234");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			// 반복문을 이용하여 테이블의 데이터를 list로 저장
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("deptNo", rs.getString("d.dept_no"));
				map.put("deptName", rs.getString("d.dept_name"));
				map.put("cnt", rs.getString("COUNT(d.dept_no)"));
				list.add(map);
			}
		} catch(Exception e) { // 오류 발생시 발생한 오류를 개발자가 볼 수 있도록 하는 코드
			e.printStackTrace();
		} finally { // 자바가 종료되지않는한 반드시 실행된다. finally에도 오류가 날 수 있기때문에 try catch문을 이용
			try {
				rs.close();
				conn.close();
				stmt.close();
			} catch(Exception e) { // 같은 변수가 가능한 이유는 변수 생명주기가 {} 안이기 떄문 관계 없는 변수이다.
				e.printStackTrace();
			}
		}
		// return값으로 list를 넘긴다.
		return list;
	}
	
	// departments 테이블의 데이터를 리스트화 하여 출력하기 위해 데이터들을 리스트로 저장하는 메소드
	// return 값은 List<Departments>이고 param 값은 departments테이블의 데이터가 소량이기떄문에 없다.
	public List<Departments> selectDepartmentsList() {
		// List를 사용하기위한 호출
		List<Departments> list = new ArrayList<Departments>();
		// db 연결
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT dept_no, dept_name FROM departments";
		
		// try catch문을 이용하여 DB연결 하는데에 있어서 오류가 나는지 확인
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/employees","root","java1234");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			// 반복문을 이용하여 테이블의 데이터를 list로 저장
			while(rs.next()) {
				Departments departments = new Departments();
				departments.setDeptNo(rs.getString("dept_no"));
				departments.setDeptName(rs.getString("dept_name"));
				// list의 값을 저장
				list.add(departments);
			}
		} catch(Exception e) { // 오류 발생시 발생한 오류를 개발자가 볼 수 있도록 하는 코드
			e.printStackTrace();
		} finally { // 자바가 종료되지않는한 반드시 실행된다. finally에도 오류가 날 수 있기때문에 try catch문을 이용
			try {
				rs.close();
				conn.close();
				stmt.close();
			} catch(Exception e) { // 같은 변수가 가능한 이유는 변수 생명주기가 {} 안이기 떄문 관계 없는 변수이다.
				e.printStackTrace();
			}
		}
		// return값으로 list를 넘긴다.
		return list;
	}
	
	// 전체 행의 수를 세기위한 메소드
	public int selectDepartmentsRowCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM departments";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/employees", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("COUNT(*)");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				conn.close();
				stmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
}
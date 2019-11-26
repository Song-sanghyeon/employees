package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DBHelper;
import vo.DeptManager;
import vo.Employees;

public class DeptManagerDao {
	
	// dept_manager 테이블의  사원리스트를 출력하는데 departments와 join하여 dept_name을, employees와 join하여 emp_name까지
	// 같이 출력하는 리스트를 만들 메소드로, 페이징도 같이 할 메소드이다.
	public List<Map<String, Object>> selectDeptManagerList() {
		// param 매개변수로 받아온 값을 확인
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// dept_manager 테이블의 리스트를 출력하는데, 외래키인 dept_no를 이용하여 departments 테이블과 join 시켜 dept_name을
		// dept_manager 테이블의 외래키인 emp_No를 이용하여 employees 테이블과 join 시키고 concat 문자열 합치는 함수를 이용하여 first_name과 last_name을 합쳐서 empName을 출력하는 쿼리문
		String sql = "SELECT (dm.emp_no) 'empNo', (dm.dept_no) 'deptNo', (d.dept_name) 'deptName', CONCAT(e.first_name, ' ', e.last_name) 'empName' "
					+"FROM dept_manager dm INNER JOIN departments d INNER JOIN employees e "
					+"ON dm.emp_no = e.emp_no AND dm.dept_no = d.dept_no WHERE dm.to_date = '9999-01-01' ORDER BY deptNo ASC";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("empNo", rs.getInt("empNo"));
				map.put("deptNo", rs.getString("deptNo"));
				map.put("deptName", rs.getString("deptName"));
				map.put("empName", rs.getString("empName"));
				list.add(map);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	}
	
	// 전체 행의 수를 세기위한 메소드
	public int selectDeptManagerRowCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM dept_manager";
		
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

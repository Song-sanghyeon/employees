package model;

import db.*;

import vo.*;

import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DeptEmpDao {
	// 검색한 사원번호의 해당하는 사원의 부서 변경 현황을 dept_emp 테이블에서 departments테이블과 employees테이블과 조인시켜서 리턴해주는 메소드
	public List<Map<String, Object>> selectDeptEmpSearchList(int empNo){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 받아온 매개변수의 값이 제대로 받아왔는지 확인
		System.out.println("Dao / selectDeptEmpSearchList empNo : "+empNo);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// dept_emp 테이블의 리스트를 출력하는데, 검색을통해 받은 emp_no의 값을 매개변수로 가져 작동하는 쿼리문
		// dept_emp 테이블의 외래키인 emp_No를 이용하여 employees 테이블과 join 시키고 concat 문자열 합치는 함수를 이용하여 first_name과 last_name을 합쳐서 empName을 출력하는 쿼리문
		String sql = "SELECT CONCAT(e.first_name, ' ', e.last_name) AS 'empName', d.dept_name AS 'deptName', de.from_date AS 'fromDate', de.to_date AS 'toDate'"
					+"FROM dept_emp de INNER JOIN departments d INNER JOIN employees e "
					+"ON de.emp_no = e.emp_no AND de.dept_no = d.dept_no WHERE de.emp_no = ?";
		// 예외가 발생할 수 있는 메소드들이 존재하기 때문에 try catch문을 이용 
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, empNo);
			rs = stmt.executeQuery();
			// 오류가 나는지 안나는지 확인
			System.out.println("Dao / selectDeptEmpDao 첫번째 try문 오류 X");
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("empName", rs.getString("empName"));
				map.put("deptName", rs.getString("deptName"));
				map.put("fromDate", rs.getString("fromDate"));
				map.put("toDate", rs.getString("toDate"));
				list.add(map);
			}
			// try문 안에 또다른 try문을 만들었는데 둘다 e로 만들었는데 가능한 이유
		} catch(Exception e) { // 자바의 변수 생명주기는 {} 블럭이기 떄문이다. 두번째 try문에 있는 e와는 다른것
			e.printStackTrace();
			// 오류가 나는지 안나는지 확인
			System.out.println("Dao / selectDeptEmpDao 첫번째 try문 속 catch 오류 O");
		} finally { // java실행이 종료되지않는이상 반드시 작동하는 finally
			DBHelper.close(rs, stmt, conn);
		}
		// return값으로 list를 넘긴다.
		return list;	
	}
	
	// deptEmp 테이블을 리스트로 가져와서 페이징작업을 통해 리스트로 리턴하는 메소드
	public List<Map<String, Object>> selectDeptEmpList(int currentPage, int rowPerPage) {
		// param 매개변수로 받아온 값을 확인
		System.out.println("Dao / selectDeptEmpList currentPage : "+currentPage);
		System.out.println("Dao / selectDeptEmpList rowPerPage : "+rowPerPage);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// dept_emp 테이블의 리스트를 출력하는데, 외래키인 dept_no를 이용하여 departments 테이블과 join 시켜 dept_name을
		// dept_emp 테이블의 외래키인 emp_No를 이용하여 employees 테이블과 join 시키고 concat 문자열 합치는 함수를 이용하여 first_name과 last_name을 합쳐서 empName을 출력하는 쿼리문
		String sql = "SELECT emp_no, dept_no, from_date, to_date FROM dept_emp LIMIT ?, ?";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			int startRow;
			// 매 페이지마다 리스트 행이 변동 되어야 하므로, 매 페이지 시작하는 행을 startRow라는 변수로 만든다.
			startRow = (currentPage-1)*rowPerPage;
			stmt.setInt(1, startRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("empNo", rs.getInt("emp_no"));
				map.put("deptNo", rs.getString("dept_no"));
				map.put("fromDate", rs.getString("from_date"));
				map.put("toDate", rs.getString("to_date"));
				list.add(map);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	}
	
	// 페이징 작업을 하기위해서는 dept_emp 테이블의 데이터 행의 총 합을 알아야 마지막 페이지를 만들 수 잇기때문에 마지막 페이지를 리턴해주는 메소드를 만든다.
	// return 값은 int로 lastPage를 넘기고, param 매개변수로는 마지막페이지를 구하기위한 rowPerPage를 받는다.
	public int selectDeptEmpLastPage(int rowPerPage) {
		int lastPage = 0;
		// selectDeptEmpRowCount라는 메소드에 테이블의 총합을 구하는 메소드가 있기떄문에 값만 받아와서 사용
		DeptEmpDao deptEmpDao = new DeptEmpDao();
		int count = deptEmpDao.selectDeptEmpRowCount();
		// 마지막 페이지는 총 데이터의 행의수를 매 페이지에 출력할 행의 개수를 나눈 나머지가 없다면 딱 맞아 떨어지꺠문에
		// 나눈 몫이돠면 되는데, 나누어 떨어지지않는다면 한페이지를 너 추가시켜서 리스트를 만들어야 한다.
		if(count%rowPerPage == 0) {
			lastPage = count/rowPerPage;
		} else {
			lastPage = (count/rowPerPage)+1;
		}
		
		return lastPage;
	}
	
	
	// dept_emp 테이블의  사원리스트를 출력하는데 departments와 join하여 dept_name을, employees와 join하여 emp_name까지
	// 같이 출력하는 리스트를 만들 메소드로, 페이징도 같이 할 메소드이다.
	// return 값은 list를 출력하기위해 list<Map>이고, param 값은 페이징 작업을 위해 currentPage와 rowPerPage이다.
	public List<Map<String, Object>> selectDeptEmpNowList(int currentPage, int rowPerPage) {
		// param 매개변수로 받아온 값을 확인
		System.out.println("Dao / selectDeptEmpNowList currentPage : "+currentPage);
		System.out.println("Dao / selectDeptEmpNowList rowPerPage : "+rowPerPage);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// dept_emp 테이블의 리스트를 출력하는데, 외래키인 dept_no를 이용하여 departments 테이블과 join 시켜 dept_name을
		// dept_emp 테이블의 외래키인 emp_No를 이용하여 employees 테이블과 join 시키고 concat 문자열 합치는 함수를 이용하여 first_name과 last_name을 합쳐서 empName을 출력하는 쿼리문
		String sql = "SELECT (de.emp_no) 'empNo', (de.dept_no) 'deptNo', (d.dept_name) 'deptName', CONCAT(e.first_name, ' ', e.last_name) 'empName' "
					+"FROM dept_emp de INNER JOIN departments d INNER JOIN employees e "
					+"ON de.emp_no = e.emp_no AND de.dept_no = d.dept_no WHERE de.to_date = '9999-01-01' LIMIT ?,?";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			int startRow;
			// 매 페이지마다 리스트 행이 변동 되어야 하므로, 매 페이지 시작하는 행을 startRow라는 변수로 만든다.
			startRow = (currentPage-1)*rowPerPage;
			stmt.setInt(1, startRow);
			stmt.setInt(2, rowPerPage);
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
	public int selectDeptEmpRowCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM dept_emp";
		
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

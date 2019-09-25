package model;

import java.sql.*;
import java.util.*;

import db.DBHelper;
import vo.Employees;

public class EmployeesDao {
	// employees 테이블의 emp_no의 값을 두개 정해서 두개의 값 사이에 있는 데이터를 보기 위한 메소드
	// return 타입은 List, param 매개변수는 시작값인 begin과 끝값인 end이다.
	public List<Employees> selectEmployeesListBetween(int begin, int end) {
		List<Employees> list = new ArrayList<Employees>();
		// 받아온 매개변수 값인 begin과 end변수의 값이 제대로 받아왔는지 확인
		System.out.println("Dao / selectEmployeesListBetween begin : "+begin);
		System.out.println("Dao / selectEmployeesListBetween end : "+end);
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// 쿼리문 작동시켜서 나온 값을 list에 저장하기 위한 쿼리문.
		final String sql = "SELECT emp_no, birth_date, first_name, last_name, gender, hire_date FROM employees WHERE emp_no BETWEEN ? AND ? ORDER BY emp_no ASC";
		// 예외가 발생할 수 있는 메소드들이 존재하기 때문에 try catch문을 이용 
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, begin);
			stmt.setInt(2, end);
			rs = stmt.executeQuery();
			// 오류가 나는지 안나는지 확인
			System.out.println("Dao / selectEmployeesListBetween 첫번째 try문 오류 X");
			while(rs.next()) {
				Employees employees = new Employees();
				employees.setEmpNo(rs.getInt("emp_no"));
				employees.setBirthDate(rs.getString("birth_date"));
				employees.setFirstName(rs.getString("first_name"));
				employees.setLastName(rs.getString("last_name"));
				employees.setGender(rs.getString("gender"));
				employees.setHireDate(rs.getString("hire_date"));
				list.add(employees);
			}
			// try문 안에 또다른 try문을 만들었는데 둘다 e로 만들었는데 가능한 이유
		} catch(Exception e) { // 자바의 변수 생명주기는 {} 블럭이기 떄문이다. 두번째 try문에 있는 e와는 다른것
			e.printStackTrace();
			// 오류가 나는지 안나는지 확인
			System.out.println("Dao / selectEmployeesListBetween 첫번째 try문 속 catch 오류 O");
		} finally { // java실행이 종료되지않는이상 반드시 작동하는 finally
			DBHelper.close(rs, stmt, conn);
		}
		// return값으로 list를 넘긴다.
		return list;	
	}
	
	
	// employees 테이블의 gender 값의 통계를 보기 위한 메소드
	// return 타입은 List<Map>, param 매개변수는 없다.
	public List<Map<String, Object>> selectEmployeesCountGroupByGender() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// DB연결 변수 만들고 초기화
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// 쿼리문 작동시켜서 나온 값을 list에 저장하기 위한 쿼리문.
		final String sql = "SELECT gender, COUNT(gender) AS cnt FROM employees GROUP BY gender";
		// 예외가 발생할 수 있는 메소드들이 존재하기 때문에 try catch문을 이용 
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			// 오류가 나는지 안나는지 확인
			System.out.println("Dao / selectEmployeesCountGroupByGender 첫번째 try문 오류 X");
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("gender", rs.getString("gender"));
				map.put("cnt", rs.getInt("cnt"));
				list.add(map);
			}
			// try문 안에 또다른 try문을 만들었는데 둘다 e로 만들었는데 가능한 이유
		} catch(Exception e) { // 자바의 변수 생명주기는 {} 블럭이기 떄문이다. 두번째 try문에 있는 e와는 다른것
			e.printStackTrace();
			// 오류가 나는지 안나는지 확인
			System.out.println("Dao / selectEmployeesCountGroupByGender 첫번째 try문 속 catch 오류 O");
		} finally { // java실행이 종료되지않는이상 반드시 작동하는 finally
			DBHelper.close(rs, stmt, conn);
		}
		// return값으로 list를 넘긴다.
		return list;	
	}
	
	// 오름차순인지 내림차순인지 order값을 받아와서 리스트에 값을 저장하는 메소드
	// return : List<Employees> param : String order
	public List<Employees> selectEmployeesListOrderBy(String order) {
		List<Employees> list = new ArrayList<Employees>();
		String sql = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// 오름차순일때와 내림차순일때 쿼리문이 다르기때문에 조건문을 이용하여 쿼리문을 저장한다.
		if(order.equals("asc")) {
			// first_name 이 오름 차순 으로 limit 50
			sql = "SELECT emp_no, birth_date, first_name, last_name, gender, hire_date FROM employees ORDER BY first_name ASC LIMIT 50";
		} else if(order.equals("desc")) {
			// first_name 이 내림 차순 으로 limit 50
			sql = "SELECT emp_no, birth_date, first_name, last_name, gender, hire_date FROM employees ORDER BY first_name DESC LIMIT 50";
		}
		// 예외가 발생할 수 있는 메소드들이 존재하기 때문에 try catch문을 이용 
			try {
				conn = DBHelper.getConnection();
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				
				// 오류가 나는지 안나는지 확인
				System.out.println("Dao / selectEmployeesListOrderBy 첫번째 try문 오류 X");
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
				System.out.println("Dao / selectEmployeesListOrderBy 첫번째 try문 속 catch 오류 O");
			} finally { // java실행이 종료되지않는이상 반드시 작동하는 finally
				DBHelper.close(rs, stmt, conn);
			}
			// return값으로 list를 넘긴다.
			return list;
		}
	
	
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
			conn = DBHelper.getConnection();
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
			DBHelper.close(rs, stmt, conn);
		}
		// return값으로 list를 넘긴다.
		return list;
	}
	
	// 전체 행의 수를 세기위한 메소드
	public int selectEmployeesRowCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM employees";
		
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("COUNT(*)");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		return count;
	}
}

package model;

import java.sql.*;
import java.util.*;

import db.DBHelper;
import vo.Employees;

public class EmployeesDao {
	// 로그인을 하기위해 입력한 값이 employees테이블의 데이터에 일치한다면 session값으로 emp_no를 보내주는 메소드
	// return 값으로는 emp_no를 보내기위한 sessionEmpNo, param 매개변수로는 first_name, last_name, emp_no의 값을 받아와야한다.
	public int login(String firstName, String lastName, int empNo) {
		// loginServlet doPost에서 매개변수를 제대로 받아왔는지 확인
		System.out.println("Dao / login first_name : "+firstName);
		System.out.println("Dao / login last_name : "+lastName);
		System.out.println("Dao / login emp_no : "+empNo);
		
		int sessionEmpNo = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// 입력한 first_name, last_name, emp_no값이 전부 일치한다면, 해당 행의 emp_no를 sessionEmpNo의 값에 저장
		String sql = "SELECT emp_no FROM employees WHERE first_name=? AND last_name=? AND emp_no=?";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setInt(3, empNo);
			rs = stmt.executeQuery();
			if(rs.next()) {
				sessionEmpNo = rs.getInt("emp_no");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		// 쿼리문을 작동시키고 값을 제대로 받아왓는지 확인
		System.out.println("Dao / login sessionEmpNo : "+sessionEmpNo);
		return sessionEmpNo;
	}
	
	// 페이징을 하기위해 마지막 페이지인 lastPage를 구해야하는데, 마지막페이지는 전체 행의 수를 알고있어야 하기때문에 lastPage를 만드는 메소드
	// return 값은 lastPage이기때문에 int 타입의 lastPage이고, param값은 rowPerPage이다. 
	public int selectLastPage(int rowPerPage) {
		int lastPage = 0;
		EmployeesDao employeesDao = new EmployeesDao();
		// 아래에 employees테이블의 전체 데이터 행의 수를 구해놓은 메소드가 존재하기때문에 메소드 호출하여 값만 저장
		int count = employeesDao.selectEmployeesRowCount();
		System.out.println("Dao / selectLastPage count : "+count); // 확인
		// 전체 행의수를 한 페이지에 담을 데이터 양인 rowPerPage를 나누었을때 나머지가 0이라면
		if(count%rowPerPage == 0) {
			// 전체 행의수와 rowPerPage는 딱 떨어지는것이기 때문에 last페이지는 count를 rowPerPage로 나눈 몫이 된다.
			lastPage = count/rowPerPage;
		} else {
			// 그렇지 않고, 나눈 나머지가 존재한다면, 딱 떨어지는것이 아니기떄문에 lastPage가 한 페이지 더 존재하여야 하기떄문에 +1을 해준다.
			lastPage = (count/rowPerPage)+1;
		}
		return lastPage;
	}
	
	// employees 테이블의 사원 리스트를 출력할 메소드인데, limit을 이용하여 한페이지에 10개씩 보기 위한 메소드
	// return 타입은 list, param 매개변수는 현재 페이지인 currentPage, 한 페이지에 정해진 데이터 갯수 rowPerPage를 받는다.
	public List<Employees> selectEmployeesListByPage(int currentPage, int rowPerPage) {
		List<Employees> list = new ArrayList<Employees>();
		// 받아온 매개변수 값인 currentPage와 rowPerPage변수의 값이 제대로 받아왔는지 확인
		System.out.println("Dao / selectEmployeesListByPage currentPage : "+currentPage);
		System.out.println("Dao / selectEmployeesListByPage rowPerPage : "+rowPerPage);
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// 쿼리문 작동시켜서 나온 값을 list에 저장하기 위한 쿼리문.
		final String sql = "SELECT emp_no, birth_date, first_name, last_name, gender, hire_date FROM employees LIMIT ?,?";
		// 예외가 발생할 수 있는 메소드들이 존재하기 때문에 try catch문을 이용 
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			// 페이지의 시작값을 구하기 위한 변수 만들기
			int startRow = 0;
			// 페에지의 시작값은 매 페이지마다 변동 되기때문에 값을 저장해야한다.
			// 현재페이지에서 1을 뺀 뒤 rowPerPage를 곱하면 해당 페이지의 첫번째 열의 주소가 된다.
			startRow = (currentPage-1)*rowPerPage;
			// 확인
			System.out.println("Dao / selectEmployeesListByPage startRow : "+startRow);
			stmt.setInt(1, startRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			// 오류가 나는지 안나는지 확인
			System.out.println("Dao / selectEmployeesListByPage 첫번째 try문 오류 X");
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
			System.out.println("Dao / selectEmployeesListByPage 첫번째 try문 속 catch 오류 O");
		} finally { // java실행이 종료되지않는이상 반드시 작동하는 finally
			DBHelper.close(rs, stmt, conn);
		}
		// return값으로 list를 넘긴다.
		return list;	
	}
	
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

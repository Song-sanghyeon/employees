package model;

import java.util.*;
import java.sql.*;
import db.*;

public class TitlesDao {
	
	// titles 테이블을 리스트로 가져와서 페이징작업을 통해 리스트로 리턴하는 메소드
	public List<Map<String, Object>> selectTitlesList(int currentPage, int rowPerPage) {
		// param 매개변수로 받아온 값을 확인
		System.out.println("Dao / selectTitlesList currentPage : "+currentPage);
		System.out.println("Dao / selectTitlesList rowPerPage : "+rowPerPage);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// titles 테이블의 외래키인 emp_No를 이용하여 employees 테이블과 join 시키고 concat 문자열 합치는 함수를 이용하여 first_name과 last_name을 합쳐서 empName을 출력하는 쿼리문
		String sql = "SELECT CONCAT(e.first_name, ' ', e.last_name) AS empName, t.title AS title, t.from_date AS fromDate, t.to_date AS toDate "
					+"FROM titles t INNER JOIN employees e ON t.emp_no = e.emp_no WHERE t.to_date = '9999-01-01' LIMIT ?, ?";
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
				map.put("empName", rs.getString("empName"));
				map.put("title", rs.getString("title"));
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

	// 페이징 작업을 하기위해서는 titles 테이블의 데이터 행의 총 합을 알아야 마지막 페이지를 만들 수 있기때문에 마지막 페이지를 리턴해주는 메소드를 만든다.
	// return 값은 int로 lastPage를 넘기고, param 매개변수로는 마지막페이지를 구하기위한 rowPerPage를 받는다.
	public int selectTitlesLastPage(int rowPerPage) {
		int lastPage = 0;
		// selectTitlesRowCount라는 메소드에 테이블의 총합을 구하는 메소드가 있기떄문에 값만 받아와서 사용
		TitlesDao titlesDao = new TitlesDao();
		int count = titlesDao.selectTitlesRowCount();
		// 마지막 페이지는 총 데이터의 행의수를 매 페이지에 출력할 행의 개수를 나눈 나머지가 없다면 딱 맞아 떨어지꺠문에
		// 나눈 몫이돠면 되는데, 나누어 떨어지지않는다면 한페이지를 너 추가시켜서 리스트를 만들어야 한다.
		if(count%rowPerPage == 0) {
			lastPage = count/rowPerPage;
		} else {
			lastPage = (count/rowPerPage)+1;
		}
		
		return lastPage;
	}
	
	// 중복된 데이터를 제외하고 한개의 데이터들만 리스트로 저장하기위한 메소드
	// return타입은 List이고 param 매개변수는 존재하지않는다.
	public List<Map<String, Object>> seleteTitlesGroupByList() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT title, COUNT(*) FROM titles WHERE to_date = '9999-01-01' GROUP BY title ORDER BY COUNT(*) ASC";
		
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("title", rs.getString("title"));
				map.put("count", rs.getString("COUNT(*)"));
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
	public int selectTitlesRowCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM titles";
		
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

package model;

import java.util.*;
import java.sql.*;

import db.DBHelper;

public class SalariesDao {
	// salaries 테이블의 데이터의 통계를 구하기위한 메소드
	// return타입은 Map이고, param 매개변수는 없다.
	public Map<String, Long> selectSalariesStatistics() {
		Map<String, Long> map = new HashMap<String, Long>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// count : 전체 행의 수, sum : 더하 값, avg : 평균 값, max : 가장 큰 수, min : 가장 작은 수, std : 평균 편차
		String sql = "SELECT COUNT(salary), SUM(salary), AVG(salary), MAX(salary), MIN(salary), STD(salary) FROM salaries";
		
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				map.put("count", rs.getLong("COUNT(salary)"));
				map.put("sum", rs.getLong("SUM(salary)"));
				map.put("avg", rs.getLong("AVG(salary)"));
				map.put("max", rs.getLong("MAX(salary)"));
				map.put("min", rs.getLong("MIN(salary)"));
				map.put("std", rs.getLong("STD(salary)"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		return map;
	}
	
	// 전체 행의 수를 세기위한 메소드
	public int selectSalariesRowCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM salaries";
		
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

package model;

import java.util.*;
import java.sql.*;
import db.*;

public class TitlesDao {
	// 중복된 데이터를 제외하고 한개의 데이터들만 리스트로 저장하기위한 메소드
	// return타입은 List이고 param 매개변수는 존재하지않는다.
	public List<String> seleteTitlesListDistinct() {
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT DISTINCT title FROM titles";
		
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("title"));
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

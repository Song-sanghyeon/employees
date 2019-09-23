package controller;

import java.util.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.*;
import vo.*;


@WebServlet("/employees/getEmployeesListOrderBy")
public class SelectEmployeesOrderByServlet extends HttpServlet {
	private EmployeesDao employeesDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 오름차순인지 내림차순인지 확인하기 위한 넘겨받는 값 order를 저장할 변수 만들기
		String order = request.getParameter("order");
		
		employeesDao = new EmployeesDao();
		
		// 정렬한 employees 테이블의 데이터를 저장한 리스트를 호출
		List<Employees> list = employeesDao.selectEmployeesListOrderBy(order);
	
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/views/employees/employeesListOrderBy.jsp").forward(request, response);
	}
}

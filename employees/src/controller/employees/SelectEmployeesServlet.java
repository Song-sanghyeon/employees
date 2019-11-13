package controller.employees;

import java.util.*;
import vo.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EmployeesDao;

@WebServlet("/employees/getEmployeesList")
public class SelectEmployeesServlet extends HttpServlet {
	private EmployeesDao employeesDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int limit = 10; // 초기 limit의 값 설정
		// 조건문을 이용하여 선택한 Limit의 값이 null값인지 확인하여 아니라면 limit변수에 저장
		if(request.getParameter("Limit") != null) {
			limit = Integer.parseInt(request.getParameter("Limit"));
		}
		// 제대로 Limit의 값을 limit로 저장되었는지 확인
		System.out.println("Servlet / SelectEmployeesServlet Limit : "+limit);
		
		employeesDao = new EmployeesDao();
		// employeesDao에 존재하는 selectEmployeesListByLimit 메소드에 매개변수로 Limit의 값이 저장되어있는 limit값 저장하고 메소드 실행 뒤 list에 저장
		List<Employees> list = employeesDao.selectEmployeesListByLimit(limit);
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/views/employees/employeesList.jsp").forward(request, response);
	}
}

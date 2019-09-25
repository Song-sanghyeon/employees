package controller;

import vo.*;

import model.*;

import java.util.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/employees/getEmployeesListByPage")
public class GetEmployeesListByPageServlet extends HttpServlet {
	private EmployeesDao employeesDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = 1; // 현재 페이지
		final int rowPerPage = 10; // 한 페이지에 나타낼 데이터 행의 수
		// url에 존재하는 currentPage가 null값이 아닐경우에 변수에 int타입으로 형변환하여 저장
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		// 확인
		System.out.println("currentPage : "+currentPage);
		System.out.println("rowPerPage : "+rowPerPage);
		
		employeesDao = new EmployeesDao();
		List<Employees> list = employeesDao.selectEmployeesListByPage(currentPage, rowPerPage);
		
		// 만들어놓은 변수 rowPerPage를 보내 메소드를 실행하여 lastPage의 값을 받아온다.
		int lastPage = employeesDao.selectLastPage(rowPerPage);
		System.out.println("lastPage : "+lastPage); // 받아온 값 확인
		
		request.setAttribute("list", list);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("rowPerPage", rowPerPage);
		request.setAttribute("lastPage", lastPage);
		
		request.getRequestDispatcher("/WEB-INF/views/employees/employeesListByPage.jsp").forward(request, response);
	}
}

package controller;

import vo.*;

import model.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

// loginServlet은 login이 되어있지않다면, login할 수 있도록 jsp로 보내주는 역할의 doGet과
// login.jsp에서 form에 입력한 login의 값을 받아와서 확인하고 일치하면 index.jsp로 보내주는 doPost로 존재한다.
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private Employees employees;
	private EmployeesDao employeesDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// login.jsp form에서 넘겨받은 값을들 저장하기 위한 변수를 만들고 변수에 값 저장
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		int empNo = Integer.parseInt(request.getParameter("empNo"));
		
		// login.jsp form에서 받아온 firstName과 lastName, empNo가 제대로 받아와졌는지 확인
		System.out.println("loginServlet doPost first_name : "+firstName);
		System.out.println("loginServlet doPost last_name : "+lastName);
		System.out.println("loginServlet doPost emp_no : "+empNo);
		
		// 메소드 호출
		employees = new Employees();
		employeesDao = new EmployeesDao();
		
		// indexServlet에서 로그인이 되었는지 확인 하기 위한 session값을 위한 변수를 만들고 변수에 값 저장
		int sessionEmpNo = employeesDao.login(firstName, lastName, empNo);
		// 메소드가 실행되어서 sessionEmpNo의 값이 제대로 저장되었는지 확인
		System.out.println("loginServlet doPost sessionEmpNo : "+sessionEmpNo);
		
		HttpSession session = request.getSession();
		session.setAttribute("sessionEmpNo", sessionEmpNo);
		//request.getRequestDispatcher("").forward(request, response);
		response.sendRedirect(request.getContextPath()+"/Index");
	}
}
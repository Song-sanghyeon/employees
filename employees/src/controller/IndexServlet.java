package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.*;

@WebServlet({"/", "/index"}) // 메인페이지  이기때문에 /을 요청해도, /index를 요청해도 실행이 되도록 배열로 만듬
public class IndexServlet extends HttpServlet {
	// 캡슐화 (employeesDao는 doGet이아닌 IndexServlet에 있어야 하기 때문
	private EmployeesDao employeesDao;
	private DepartmentsDao departmentsDao;
	private DeptManagerDao deptManagerDao;
	private DeptEmpDao deptEmpDao;
	private TitlesDao titlesDao;
	private SalariesDao salariesDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/index URL 요청");
	
		this.employeesDao = new EmployeesDao();
		this.departmentsDao = new DepartmentsDao();
		this.deptManagerDao = new DeptManagerDao();
		this.deptEmpDao = new DeptEmpDao();
		this.titlesDao = new TitlesDao();
		this.salariesDao = new SalariesDao();
		
		// 메소드 호출하여 나온 count의 값을 변수를 만들어 저장
		int employeesRowCount = employeesDao.selectEmployeesRowCount();
		System.out.println("employeesRowCount : "+employeesRowCount); // 확인

		int departmentsRowCount = departmentsDao.selectDepartmentsRowCount();
		System.out.println("departmentsRowCount : "+departmentsRowCount);

		int deptManagerRowCount = deptManagerDao.selectDeptManagerRowCount();
		System.out.println("deptManagerRowCount : "+deptManagerRowCount);

		int deptEmpRowCount = deptEmpDao.selectDeptEmpRowCount();
		System.out.println("deptEmpRowCount : "+deptEmpRowCount);

		int titlesRowCount = titlesDao.selectTitlesRowCount();
		System.out.println("titlesRowCount : "+titlesRowCount);

		int salariesRowCount = salariesDao.selectSalariesRowCount();
		System.out.println("salariesRowCount : "+salariesRowCount);
		
		request.setAttribute("employeesRowCount", employeesRowCount);
		request.setAttribute("departmentsRowCount", departmentsRowCount);
		request.setAttribute("deptManagerRowCount", deptManagerRowCount);
		request.setAttribute("deptEmpRowCount", deptEmpRowCount);
		request.setAttribute("titlesRowCount", titlesRowCount);
		request.setAttribute("salariesRowCount", salariesRowCount);
		
		// /WEB-INF/views/index.jsp
		// request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
		rd.forward(request, response);
	}
}

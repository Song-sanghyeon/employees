package controller.deptEmp;

import java.util.*;
import model.*;
import vo.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/deptEmp/getDeptEmpSearchList")
public class GetDeptEmpSearchListServlet extends HttpServlet {
	private DeptEmpDao deptEmpDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-----------------------------------------------");
		int empNo = Integer.parseInt(request.getParameter("empNo"));
		System.out.println("empNo : "+empNo);
		
		deptEmpDao = new DeptEmpDao();
		List<Map<String, Object>> list = deptEmpDao.selectDeptEmpSearchList(empNo);
		// 확인
		System.out.println("list : "+list);
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/views/deptEmp/deptEmpSearchList.jsp").forward(request, response);
	}
}
package controller.departments;

import java.util.*;
import model.*;
import vo.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/departments/getDepartmentsList")
public class SelectDepartmentsServlet extends HttpServlet {
	private DepartmentsDao departmentsDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		departmentsDao = new DepartmentsDao();
		List<Departments> list = departmentsDao.selectDepartmentsList();
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/views/departments/departmentsList.jsp").forward(request, response);
	}
}

package controller.deptEmp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/deptEmp/getDeptEmpSearch")
public class GetDeptEmpSearchServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.getRequestDispatcher("/WEB-INF/views/deptEmp/deptEmpSearch.jsp").forward(request, response);
	}
}
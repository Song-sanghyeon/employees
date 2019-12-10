package controller.salaries;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/salaries/getSalariesSearch")
public class GetSalariesSearchServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.getRequestDispatcher("/WEB-INF/views/salaries/salariesSearch.jsp").forward(request, response);
	}
}
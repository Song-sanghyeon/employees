package controller.salaries;

import model.*;

import java.util.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/salaries/getSalariesStatistics")
public class GetSalariesStatisticsServlet extends HttpServlet {
	private SalariesDao salariesDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		salariesDao = new SalariesDao();
		Map<String, Long> map = salariesDao.selectSalariesStatistics();
		
		request.setAttribute("map", map);
		request.getRequestDispatcher("/WEB-INF/views/salaries/salariesStatistics.jsp").forward(request, response);
	}
}

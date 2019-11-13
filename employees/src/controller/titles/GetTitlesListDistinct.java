package controller.titles;

import java.util.*;

import model.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/titles/getTitlesListDistinct")
public class GetTitlesListDistinct extends HttpServlet {
	private TitlesDao titlesDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		titlesDao = new TitlesDao();
		List<String> list = titlesDao.seleteTitlesListDistinct();
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/titles/titlesListDistinct.jsp").forward(request, response);
	}
}

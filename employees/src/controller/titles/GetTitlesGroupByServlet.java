package controller.titles;


import model.*;

import java.util.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/titles/getTitlesGroupBy")
public class GetTitlesGroupByServlet extends HttpServlet {
	private TitlesDao titlesDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Dao 클래스내의 메소드를 실행시켜 값을 저장
		titlesDao = new TitlesDao();
		List<Map<String, Object>> list = titlesDao.seleteTitlesGroupByList();
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/views/titles/titlesGroupBy.jsp").forward(request, response);
	}
}

package controller.titles;

import java.util.*;

import model.*;

import vo.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/titles/getTitlesList")
public class GetTitlesListServlet extends HttpServlet {
	private TitlesDao titlesDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 변수를 만들어 현재 페이지를 기본 1로 저장해주고, url로 받은 currentPage를 만든 변수에 저장
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		// currentPage를 잘 받아왔는지 확인
		System.out.println("servlet currentPage : "+currentPage);
		
		// 한 페이지에 출력할 데이터 행의 수를 10으로 지정하기위한 변수를 만든다.
		final int rowPerPage = 10;
		
		// Dao 클래스내의 메소드를 실행시켜 값을 저장
		titlesDao = new TitlesDao();
		List<Map<String, Object>> list = titlesDao.selectTitlesList(currentPage, rowPerPage);
		int lastPage = titlesDao.selectTitlesLastPage(rowPerPage);
		// lastPage를 제대로 받아왔는지 확인
		System.out.println("servlet lastPage : "+lastPage);
		
		request.setAttribute("list", list);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("rowPerPage", rowPerPage);
		request.setAttribute("lastPage", lastPage);
		
		request.getRequestDispatcher("/WEB-INF/views/titles/titlesList.jsp").forward(request, response);
	}
}
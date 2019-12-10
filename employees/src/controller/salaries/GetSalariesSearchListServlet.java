package controller.salaries;

import java.util.*;
import model.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/salaries/getSalariesSearchList")
public class GetSalariesSearchListServlet extends HttpServlet {
	private SalariesDao salariesDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-----------------------------------------------");
		int empNo = Integer.parseInt(request.getParameter("empNo"));
		System.out.println("empNo : "+empNo);
		
		// 변수를 만들어 현재 페이지를 기본 1로 저장해주고, url로 받은 currentPage를 만든 변수에 저장
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		// currentPage를 잘 받아왔는지 확인
		System.out.println("servlet currentPage : "+currentPage);
		
		// 한 페이지에 출력할 데이터 행의 수를 10으로 지정하기위한 변수를 만든다.
		final int rowPerPage = 10;
		
		salariesDao = new SalariesDao();
		List<Map<String, Object>> list = salariesDao.selectSalariesSearchList(empNo, currentPage, rowPerPage);
		
		// 페이징 작업을 위한 마지막 페이지
		int lastPage = salariesDao.selectSalariesSearchLastPage(empNo, rowPerPage);
		
		// 확인
		System.out.println("list : "+list);

		request.setAttribute("list", list);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("rowPerPage", rowPerPage);
		request.setAttribute("lastPage", lastPage);
		
		request.getRequestDispatcher("/WEB-INF/views/salaries/salariesSearchList.jsp").forward(request, response);
	}
}

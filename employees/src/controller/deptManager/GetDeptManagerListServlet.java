package controller.deptManager;

import vo.*;

import model.*;

import java.util.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deptManager/getDeptManagerList")
public class GetDeptManagerListServlet extends HttpServlet {
	private DeptManagerDao deptManagerDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Dao 클래스내의 메소드를 실행시켜 값을 저장
		deptManagerDao = new DeptManagerDao();
		List<Map<String, Object>> list = deptManagerDao.selectDeptManagerList();
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/views/deptManager/deptManagerList.jsp").forward(request, response);
	}
}

package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// logout은 단순히 session을 초기화하고, session값이 저장되어있는 sessionEmpNo 변수를 초기화 하는 Servlet이다.
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// sessionEmpNo의 값을 받아와서 저장
		int sessionEmpNo = Integer.parseInt(request.getParameter("sessionEmpNo"));
		// 제대로 받아왔는지 확인
		System.out.println("logout sessionEmpNo : "+sessionEmpNo);
	
		HttpSession session = request.getSession();
		// 세션 초기화
		session.invalidate();
		// 세션 값이 저장되어있는 변수 초기화
		sessionEmpNo = 0;
		// 초기화가 끝나면 확인
		System.out.println("logout success sessionEmpNo : "+sessionEmpNo);
		// 로그아웃이 성공하면 다시 login하도록 IndexServlet을 요청
		response.sendRedirect(request.getContextPath()+"/Index");
	}
}

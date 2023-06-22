package com.kh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestPostServlet
 */
@WebServlet("/test2.do")
public class RequestPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		//요청시 전달한 값들은 request의 parameter에 담겨있다.
		//but, POST방식 요청의 경우 값을 뽑기 전에 utf-8방식으로 인코딩 설정을 해야 한다.
		//	   POST방식의 기본 인코딩 설정은 ISO-8859-1이기 때문
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");//"후추", ""(텍스트 상자가 빈 경우 빈 문자열이 넘어감)
		String gender = request.getParameter("gender");//"F", null->라디오버튼 체크 안 한 경우
		int age = Integer.parseInt(request.getParameter("age"));//"20", ""->(값이 없는 경우, NumberFormatException발생)
		
		//체크박스와 같이 복수개의 정보를 받을 때는 배열로 받아야 한다
		String[] food = request.getParameterValues("food");//["한식", "일식"], null(체크 안 한다면 null값 반환)
		
		//응답 페이지 출력
		
		//GET방식에서 다뤄본 내용은 순수 Servlet만 이용한 방식 ==> java코드 내에 html을 작성(가독성 bad 유지보수 bad)
		//JSP(Java Server Page) : html내에 Java 코드를 쓸 수 있는 기술
		
		//응답페이지를 만드는 과정을 jsp에게 위임(떠넘기기)할 예정
		
		//그 응답화면에서(jsp)필요로 하는 데이터들은 request객체에 담아서 전달해줘야 함
		//->request의 attribute라는 영역에 데이터 담아서 전달해주기(키-밸류 세트로)
		//request.setAttribute("키", "밸류");
		request.setAttribute("name", name);
		request.setAttribute("age", age);
		request.setAttribute("gender", gender);
		request.setAttribute("foods", food);
		
		//위임 시 필요한 객체 : RequestDispatcher 객체
		//1) 응답하고자하는 뷰 선택 후 생성
		RequestDispatcher view = request.getRequestDispatcher("views/responsePage.jsp");
		
		//2) 포워딩(위임처리)
		view.forward(request, response);
	}

}

package com.Dynamic.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Dynamic.Implementation.DynamicImplementation;
import com.Dynamic.Interface.DynamicInterface;

@WebServlet("/DoLogin")
public class DoLogin extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public DoLogin() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at :").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		String usname = request.getParameter("username");
		String passss = request.getParameter("password");
		
		System.out.println(usname+""+passss);
		
		DynamicInterface di = new DynamicImplementation();
		int k = di.dolog(usname,passss);
		System.out.println("the value of k is:"+k);
		
		if(k==1) {
			HttpSession session = request.getSession();
			session.setAttribute("dprovider", usname);
			response.sendRedirect("OwnerHome.jsp");
		} else {
			response.sendRedirect("error.jsp");
		}
	}
}

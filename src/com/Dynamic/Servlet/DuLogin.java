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

@WebServlet("/DuLogin")
public class DuLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public DuLogin() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at:").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
		
		System.out.println(uname+"--"+pass);
		
		DynamicInterface di = new DynamicImplementation();
		int k = di.dulog(uname,pass);
		
		System.out.println("The value of k is:"+k);
		
		if(k==1) {
			HttpSession session = request.getSession();
			session.setAttribute("duser", uname);
			response.sendRedirect("UserHome.jsp");
		} else {
			response.sendRedirect("error.jsp");
		}
	}

}

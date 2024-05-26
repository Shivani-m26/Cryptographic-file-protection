package com.Dynamic.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AuditorLogin")
public class AuditorLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public AuditorLogin() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
		
		System.out.println("The Username and Password is:"+uname+"--"+pass);
		
		if(uname.equals("auditor") && pass.equals("auditor")) {
			response.sendRedirect("AuditorHome.jsp");
		} else {
			response.sendRedirect("Error.jsp");
		}
	}

}

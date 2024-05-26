package com.Dynamic.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Dynamic.Bean.UserBean;
import com.Dynamic.Implementation.DynamicImplementation;
import com.Dynamic.Interface.DynamicInterface;

@WebServlet("/DuRegister")
public class DuRegister extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public DuRegister() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at:").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		String name = request.getParameter("name");
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
		String cpass = request.getParameter("conpassword");
		String mail = request.getParameter("email");
		String con = request.getParameter("contact");
		String addr = request.getParameter("address");
		
		System.out.println(name+"--"+uname+"--"+pass+"--"+cpass+"--"+mail+"--"+con+""+addr);
		
		if(pass.equals(cpass)) {
			System.out.println("password and confirm password matched----YES");
			
			UserBean ub = new UserBean();
			ub.setName(name);
			ub.setUsername(uname);
			ub.setPassword(pass);
			ub.setEmail(mail);
			ub.setContact(con);
			ub.setAddress(addr);
			
			DynamicInterface di = new DynamicImplementation();
			int k = di.duregister(ub);
			System.out.println("the value of k is:"+k);
			
			if(k==1) {
				response.sendRedirect("UserLogin.jsp");
			} else {
				response.sendRedirect("error.jsp");
			}
			
			
		}
	}

}

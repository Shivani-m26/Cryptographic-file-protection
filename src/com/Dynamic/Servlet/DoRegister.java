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

@WebServlet("/DoRegister")
public class DoRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public DoRegister() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("served at :").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		String name = request.getParameter("name");
		String uname = request.getParameter("username");
		String mail = request.getParameter("email");
		String pass = request.getParameter("password");
		String rpass = request.getParameter("conpassword");
		String con = request.getParameter("contact");
		String addr = request.getParameter("address");
		
		System.out.println(name+"--"+uname+"--"+mail+"--"+pass+"=="+rpass+"--"+con+"--"+addr);
		
		if(pass.equals(rpass)) {
			System.out.println("User name and password matched----YES");
			UserBean ub = new UserBean();
			ub.setName(name);
			ub.setUsername(uname);
			ub.setEmail(mail);
			ub.setPassword(pass);
			ub.setContact(con);
			ub.setAddress(addr);
			
			DynamicInterface di = new DynamicImplementation();
			int k = di.doregister(ub);
			System.out.println("The k value is:"+k);
			if(k==1) {
				response.sendRedirect("OwnerLogin.jsp");
			} else {
				response.sendRedirect("error.jsp");
			}
			
		}
		
		
	}

}

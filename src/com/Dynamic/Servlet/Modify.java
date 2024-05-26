package com.Dynamic.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.Dynamic.Implementation.DynamicImplementation;
import com.Dynamic.Interface.DynamicInterface;

@WebServlet("/Modify")
public class Modify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public Modify() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("/Served at").append(request.getContextPath());
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		String values = request.getParameter("check");
		System.out.println("The values is"+values);
		
		String filename = request.getParameter("Filename");
		System.out.println("The file name is:" +filename);
		
		String filekey = request.getParameter("filekey");
		System.out.println("The file key is:" +filekey);
		
		DynamicInterface dii = new DynamicImplementation();
		String filekey1 = dii.getpublickey(filename);
		System.out.println("The filekey111"+filekey1);
		
		JOptionPane jpane = new JOptionPane("Enter the secret key");
		JDialog jdialog = jpane.createDialog("Alert");
		jdialog.setAlwaysOnTop(true);
		jdialog.show();
		
		String typingprivatekey  = JOptionPane.showInputDialog("Enter the secret key");
		if(filekey1.equalsIgnoreCase(typingprivatekey)) {
			System.out.println("Matched");
			//PrintWriter out = null;
			response.sendRedirect("ModifyFile.jsp");
	} else {
		System.out.println("Failed");
		JOptionPane.showMessageDialog(null, "Sorry your key is wrong");
		response.sendRedirect("Error.jsp");
		}
	}
}

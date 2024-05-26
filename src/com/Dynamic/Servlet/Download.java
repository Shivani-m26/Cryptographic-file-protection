package com.Dynamic.Servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.Dynamic.Implementation.DynamicImplementation;
import com.Dynamic.Interface.DynamicInterface;

import pcloud.PCloudAPI;
import pcloud.PCloudAPIDebug;


@WebServlet("/Download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public Download() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
			PrintWriter out = null;
			
			try {
				out = response.getWriter();
				String filepath = "C:\\Users\\ST-0002\\workspace2\\Dynamic_Data_Auditing\\WebContent\\LOCAL\\";
				String filename1 = request.getParameter("Filename");
				System.out.println("The file name is:"+filename1);
				
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition", "attachment; filename=\"" +filename1+ "\"");
				java.io.FileInputStream fileInputStream = new java.io.FileInputStream(filepath+filename1);
				
				int i;
				while((i=fileInputStream.read()) != -1) {
					out.write(i);
				}
				fileInputStream.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				File file=new File("E:\\Temp\\Wedding.zip");
	             FileOutputStream ofile=new FileOutputStream(file);
	             PCloudAPI conn=new PCloudAPI(true);
	             Hashtable <String, Object> params=new Hashtable <String, Object> ();
			     params.put("username", "cloudproject18032019@gmail.com");
			     params.put("password", "18032019");
	             params.put("fileids", values.replace('f', ' ').trim());
	             System.out.println("Started");
	             long startTime = System.currentTimeMillis();
	             PCloudAPIDebug.print(conn.sendCommand("getzip", params));
	             
	             conn.readData(ofile);
	             long stopTime = System.currentTimeMillis();
	             long elapsedTime = stopTime - startTime;
	             System.out.println("TIME ::: " + (float)(elapsedTime / 1000) + " sec");
	             System.out.println("end");
	             try {
	     			ZipFile zipFile = new ZipFile("E:\\Temp\\Wedding.zip");
	     			Enumeration<?> enu = zipFile.entries();
	     			while (enu.hasMoreElements()) {
	     				ZipEntry zipEntry = (ZipEntry) enu.nextElement();

	     				String name = zipEntry.getName();
	     				long size = zipEntry.getSize();
	     				long compressedSize = zipEntry.getCompressedSize();
	     				System.out.printf("name: %-20s | size: %6d | compressed size: %6d\n", 
	     						name, size, compressedSize);

	     				File file1 = new File("E:\\Temp\\"+name);
	     				if (name.endsWith("/")) {
	     					file1.mkdirs();
	     					continue;
	     				}

	     				File parent = file1.getParentFile();
	     				if (parent != null) {
	     					parent.mkdirs();
	     				}

	     				InputStream is = zipFile.getInputStream(zipEntry);
	     				FileOutputStream fos = new FileOutputStream(file1);
	     				byte[] bytes = new byte[1024];
	     				int length;
	     				while ((length = is.read(bytes)) >= 0) {
	     					fos.write(bytes, 0, length);
	     				}
	     				is.close();
	     				fos.close();


	     				
	     				
	     			}
	     			zipFile.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Failed");
			JOptionPane.showMessageDialog(null, "Sorry your key is wrong");
			response.sendRedirect("Error.jsp");
		}
		
	}

}

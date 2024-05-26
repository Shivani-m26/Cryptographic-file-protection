package com.Dynamic.Implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.Dynamic.Bean.UploadBean;
import com.Dynamic.Bean.UserBean;
import com.Dynamic.Database.DatabaseConnection;
import com.Dynamic.Interface.DynamicInterface;


public class DynamicImplementation implements DynamicInterface {
	Connection con;
	int k=0;
	
	@Override
	public int doregister(UserBean ub) {
		int result = 0;
		try {
			con = DatabaseConnection.createConnection();
			PreparedStatement ptmt = con.prepareStatement("INSERT INTO doreg VALUES (?,?,?,?,?,?)");
			ptmt.setString(1, ub.getName());
			ptmt.setString(2, ub.getUsername());
			ptmt.setString(3, ub.getPassword());
			ptmt.setString(4, ub.getEmail());
			ptmt.setString(5, ub.getAddress());
			ptmt.setString(6, ub.getContact());
			result = ptmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public int duregister(UserBean ub) {
		int result = 0;
		try {
			con = DatabaseConnection.createConnection();
			PreparedStatement ptmt = con.prepareStatement("INSERT INTO dureg VALUES (?,?,?,?,?,?)");
			ptmt.setString(1, ub.getName());
			ptmt.setString(2, ub.getUsername());
			ptmt.setString(3, ub.getPassword());
			ptmt.setString(4, ub.getEmail());
			ptmt.setString(5, ub.getAddress());
			ptmt.setString(6, ub.getContact());
			result = ptmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public int dulog(String username, String password) {
		con = DatabaseConnection.createConnection();
		System.out.println(con);
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `dureg` where uname='"+username+"' and pass='"+password+"'");
			
			System.out.println(username);
			System.out.println(password);
			
			while(rs.next()) {
				String unamech = rs.getString("uname");
				String passch = rs.getString("pass");
				
				System.out.println(unamech+""+passch);
				if(unamech.equals(username) && passch.equals(password)) {
					k=1;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return k;
	}
	
	@Override
	public int dolog(String username, String password) {
		con = DatabaseConnection.createConnection();
		System.out.println(con);
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `doreg` where uname='"+username+"' and pass='"+password+"'");
			
			System.out.println(username);
			System.out.println(password);
			
			while(rs.next()) {
				String unamech = rs.getString("uname");
				String passch = rs.getString("pass");
				
				System.out.println(unamech+""+passch);
				
				if(unamech.equals(username) && passch.equals(password)) {
					k=1;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return k;
	}
	
	@Override
	public int fileupload(UploadBean bean) {
		int ans=0;
		try {
			con = DatabaseConnection.createConnection();
			PreparedStatement ptmt = con.prepareStatement("INSERT INTO uploadfile VALUES(?,?,?,?,?,?,?,?,?)");
			ptmt.setString(1, bean.getUsername());
			ptmt.setString(2, bean.getFilename());
			ptmt.setString(3, bean.getType());
			ptmt.setString(4, bean.getPath());
			ptmt.setString(5, bean.getContent());
			ptmt.setString(6, bean.getSize());
			ptmt.setString(7, bean.getKey());
			ptmt.setString(8, bean.getEncrypt());
			ptmt.setString(9, bean.getDecrypt());
			
			ans=ptmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ans;
	}
	
	@Override
	public String getpublickey(String filename) {
		int i = 0;
		String publickey = null;
		try {
			con = DatabaseConnection.createConnection();
			PreparedStatement ptmt = con.prepareStatement("SELECT filekey FROM uploadfile where filename = '"+filename+"'");
			ResultSet rs = ptmt.executeQuery();
			
			while(rs.next()) {
				publickey = rs.getString(1);
				System.out.println("The public key is :" +publickey);
			}
			System.out.println("xc"+i);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return publickey;
	}
	
	@Override
	public int modifiedupload(UploadBean bean) {
		int ans=0;
		try {
			con = DatabaseConnection.createConnection();
			PreparedStatement ptmt = con.prepareStatement("INSERT INTO modifieduploadfile VALUES(?,?,?,?,?,?,?,?)");
			ptmt.setString(1, bean.getUsername());
			ptmt.setString(2, bean.getFilename());
			ptmt.setString(3, bean.getType());
			ptmt.setString(4, bean.getPath());
			ptmt.setString(5, bean.getContent());
			ptmt.setString(6, bean.getSize());
			//ptmt.setString(7, bean.getKey());
			ptmt.setString(7, bean.getEncrypt());
			ptmt.setString(8, bean.getDecrypt());
			
			ans=ptmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ans;
	}
}

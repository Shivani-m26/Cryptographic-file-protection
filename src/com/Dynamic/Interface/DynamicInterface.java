package com.Dynamic.Interface;


import com.Dynamic.Bean.UploadBean;
import com.Dynamic.Bean.UserBean;

public interface DynamicInterface {
	public int doregister(UserBean ub);
	public int duregister(UserBean ub);
	public int dulog(String username, String password);
	public int dolog(String username, String password);
	public int fileupload(UploadBean bean);
	public String getpublickey(String filename);
	public int modifiedupload(UploadBean bean);
}

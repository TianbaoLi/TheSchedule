package com.turingmac.schedule;

import java.util.ArrayList;

public class UserInfo {
	private String nickName;
	private String password;
	private String classNumber;
	private ArrayList<ClassInfo> classList;
	ClassDatabaseHelper classDb;

	public UserInfo(String aNickName, String aPassword, String aClassNumber) {
		nickName = aNickName;
		password = aPassword;
		classNumber = aClassNumber;
		classList = new ArrayList<ClassInfo>();
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String aNickName) {
		nickName = aNickName;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String apassword) {
		password = apassword;
	}

	public String getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(String aClassNumber) {
		classNumber = aClassNumber;
	}

	public ArrayList<ClassInfo> getMyClass() {
		return classList;
	}

	public void setMyClass(ArrayList<ClassInfo> aMyClass) {
		classList = aMyClass;
	}

}

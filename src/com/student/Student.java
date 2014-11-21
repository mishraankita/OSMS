package com.student;
public class Student {
	private int userID , phoneNumber , departmentID;
	private String  firstname, lastname, address, email, sessionJoined, enrollProgram, dob;
	public int getUserID() {
		return userID;
	}
	//no test commit in develop
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}
	public String getFirstName() {
		return firstname;
	}
	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}
	public String getLastName() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSessionJoined() {
		return sessionJoined;
	}
	public void setSessionJoined(String sessionJoined) {
		this.sessionJoined = sessionJoined;
	}
	public String getEnrollProgram() {
		return enrollProgram;
	}
	public void setEnrollProgram(String enrollProgram) {
		this.enrollProgram = enrollProgram;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
}

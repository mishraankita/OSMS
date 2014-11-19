package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ManageStudentDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		//String userID = (String) request.getSession().getAttribute("userID");
		String userID = (String) session.getAttribute("userID");	
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + userID);
		//String firstName = request.getParameter("")
		PrintWriter out = response.getWriter();

		try {
			Connection con = DBConnection.getConnection();
			String action = request.getParameter("action");
			PreparedStatement updateData = null;
			
			 if("Edit First name".equals(action)){
				 String firstname = request.getParameter("FirstName");
				 //String updateString = "update studentdetails set FirstName = " + firstname + "where UserID ="+ userID + ";";
				    String updateString =
				            "update studentdetails " +
				            "set FirstName = ? where UserID = ?";
				    updateData = con.prepareStatement(updateString);
				    updateData.setString(1, firstname);
				    updateData.setInt(2, Integer.parseInt(userID));
				 
				 updateData.executeUpdate();
				 //con.commit();
			 }
//			 if("Edit Last name".equals(action)){
//				 String lastname = request.getParameter("LastName");
//				 ResultSet rs = stmt
//							.executeQuery("update studentdetails set LastName = '" + lastname + "where UserID='"+ userID + "'");
//			 }
//			 if("Edit Address".equals(action)){
//				 String address = request.getParameter("address");
//				 ResultSet rs = stmt
//							.executeQuery("update studentdetails set Address = '" + address + "where UserID='"+ userID + "'");	
//			 }
//			 if("Edit PhoneNumber".equals(action)){
//				 String phoneNumber = request.getParameter("phoneNumber");
//				 ResultSet rs = stmt
//							.executeQuery("update studentdetails set PhoneNumber = '" + phoneNumber + "where UserID='"+ userID + "'");	
//			 }
//			 if("Edit Email".equals(action)){
//				 String email = request.getParameter("Email");
//				 ResultSet rs = stmt
//							.executeQuery("update studentdetails set Email = '" + email + "where UserID='"+ userID + "'");
//			 }
//			 if("Edit SessionJoined".equals(action)){
//				 String sessionJoined = request.getParameter("SessionJoined");
//				 ResultSet rs = stmt
//							.executeQuery("update studentdetails set SessionJoined = '" + sessionJoined + "where UserID='"+ userID + "'");
//			 }
//			 if("Edit DepartmentID".equals(action)){
//				 String departmentID = request.getParameter("DepartmentID");
//				 ResultSet rs = stmt
//							.executeQuery("update studentdetails set DepartmentID = '" + departmentID + "where UserID='"+ userID + "'");
//			 }
//			 if("Edit EnrollProgram".equals(action)){
//				 String enrollProgram = request.getParameter("EnrollProgram");
//				 ResultSet rs = stmt
//							.executeQuery("update studentdetails set EnrollProgram = '" + enrollProgram + "where UserID='"+ userID + "'");
//			 }
//			 if("Edit DateOfBirth".equals(action)){
//				 String dateOfBirth = request.getParameter("DateOfBirth");
//				 ResultSet rs = stmt
//							.executeQuery("update studentdetails set DateOfBirth = '" + dateOfBirth + "where UserID='"+ userID + "'");
//			 }
				out.println(" <h2 align=center><a href=./student.jsp>Register a new Student</a><br/></h2>");
				out.println(" <h2 align=center><a href=./adminsuccess.html> Go to Home</a></h2>");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

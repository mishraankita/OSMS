package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EnrollStudentOrDpd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int result1 = 0;
	int result2 = 0;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accountType = request.getParameter("accountType");

		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String password = request.getParameter("password");
		String securityQuestion = request.getParameter("securityQuestion");
		String status = request.getParameter("status");
		String userID = request.getParameter("userID");
		String answer = request.getParameter("answer");
		String address = request.getParameter("address");
		String email = request.getParameter("email");

		response.setContentType("text/html");
		RequestDispatcher rd = null;

		try {
			Connection con = DBConnection.getConnection();

			if (accountType.equalsIgnoreCase("student")) {
				String phoneNumber = request.getParameter("phoneNumber");
				String dob = request.getParameter("dob");
				String sessionJoined = request.getParameter("sessionJoined");
				String departmentID = request.getParameter("departmentID");
				String enrollProgram = request.getParameter("enrollProgram");

				String query1 = "insert into studentdetails(UserID,FirstName,LastName,Address,PhoneNumber,Email,SessionJoined,DepartmentID,EnrollProgram,DateOfBirth) values(?,?,?,?,?,?,?,?,?,?) ";
				PreparedStatement psmt1 = con.prepareStatement(query1);
				psmt1.setString(1, userID);
				psmt1.setString(2, firstname);
				psmt1.setString(3, lastname);
				psmt1.setString(4, address);
				psmt1.setString(5, phoneNumber);
				psmt1.setString(6, email);
				psmt1.setString(7, sessionJoined);
				psmt1.setString(8, departmentID);
				psmt1.setString(9, enrollProgram);
				psmt1.setString(10, dob);

				result1 = psmt1.executeUpdate();
			} else {
				System.out.println("inside the dpd registration");
			String query1 = "insert into employee (UserID,FirstName,LastName,Address,Email) values(?,?,?,?,?) ";
				PreparedStatement psmt1 = con.prepareStatement(query1);
				psmt1.setString(1, userID);
				psmt1.setString(2, firstname);
				psmt1.setString(3, lastname);
				psmt1.setString(4, address);
				psmt1.setString(5, email);
				
				System.out.println("user id" + userID);
				System.out.println("firstname" + firstname);
				System.out.println("lastname" + lastname);
				System.out.println("address" + address);
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!! email" + email);

				result1 = psmt1.executeUpdate();
			}

			String query2 = "insert into login(UserID,Password,SecurityQuestion,Answer,AccountType,Status) values(?,?,?,?,?,?) ";
			PreparedStatement psmt2 = con.prepareStatement(query2);
			psmt2.setString(1, userID);
			psmt2.setString(2, password);
			psmt2.setString(3, securityQuestion);
			psmt2.setString(4, answer);
			psmt2.setString(5, accountType);
			psmt2.setString(6, status);

			result2 = psmt2.executeUpdate();

			if (result1 == 1 && result2 == 1) {
				// request.setAttribute("firstname", firstname);
				request.setAttribute("userID", userID);
				rd = request.getRequestDispatcher("./regsuccess.jsp");
				rd.forward(request, response);
			} else {
				rd = request.getRequestDispatcher("./regfailure.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println(e);
			rd = request.getRequestDispatcher("./regfailure.jsp");
			rd.forward(request, response);
		}

	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("do get ");
		super.doGet(req, resp);
	}

}

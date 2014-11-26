package com.servlet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ForgetpasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userID = request.getParameter("UserID");
		String answer = request.getParameter("Answer");
//		System.out.println("answer provided is " +answer);
//		System.out.println("user provided is " +userID);
		response.setContentType("text/html");

		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("select Answer,Status from login where UserID='"
							+ userID + "'");// and password='"+passWord+"'");
			if (rs.next()) {
				// String dbuserID = rs.getString("UserID");
				// to chk if the userID exists.
				String dbAnswer = rs.getString("Answer");
				int status = rs.getInt("Status");
				// System.out.println("db answer is : " + dbAnswer);
				if (userID == null || answer == null || userID == "") {
					RequestDispatcher rd = request
							.getRequestDispatcher("./failure.html");
					rd.forward(request, response);
				}
				if (answer.equalsIgnoreCase(dbAnswer) && status==1) {
					// TODO save the password to the INBOX AND OPEN THE
					// ACCOUNT
					RequestDispatcher rd = request
							.getRequestDispatcher("./studentsuccess.html");
					rd.forward(request, response);
				} else if(status==0){
					RequestDispatcher rd = request
							.getRequestDispatcher("./accountLocked.html");
					rd.forward(request, response);
				}else{
					RequestDispatcher rd = request
							.getRequestDispatcher("./failure.html");
					rd.forward(request, response);
				}
			}
			 con.close();
		} catch (Exception e) {
			System.out.println(e);
//			RequestDispatcher rd = request.getRequestDispatcher("./failure.html");
//			rd.forward(request, response);
		}

	}

}

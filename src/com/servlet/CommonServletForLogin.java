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

public class CommonServletForLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String UserID = request.getParameter("UserID");
		String passWord = request.getParameter("passWord");
		String AccountType = request.getParameter("AccountType");
		//String action = request.getParameter("SUBMIT");
		
//		System.out.println(UserID);
//		System.out.println(passWord);
//		System.out.println(AccountType);
		response.setContentType("text/html");
		String dbUserID = null;
		String dbPassWord = null;
		String dbAccountType = null;

		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("select UserID,password,AccountType from login where UserID='"
							+ UserID + "'");// and password='"+passWord+"'");
			if (rs.next()) {
				dbUserID = rs.getString("UserID");
				//to chk if the userID exists.
				if (dbUserID == null) {
					RequestDispatcher rd = request
							.getRequestDispatcher("./failure.html");
					rd.forward(request, response);
				}
				dbPassWord = rs.getString("password");
				dbAccountType = rs.getString("AccountType");
//				System.out.println("dbUSerName : " + dbUserID);
//				System.out.println("dbPassWord : " + dbPassWord);
//				System.out.println("dbAccountType : " + dbAccountType);
				//System.out.println("!!!!!!!!" +rs.getString("securityQuestion"));
				if(UserID == null || passWord == null || AccountType == null ){
					RequestDispatcher rd = request.getRequestDispatcher("./failure.html");
					rd.forward(request, response);
				}
				if (UserID != null && UserID != "") {
					RequestDispatcher rd = null;
					if (UserID.equalsIgnoreCase(dbUserID)
							&& passWord.equalsIgnoreCase(dbPassWord)) {
						if (AccountType.equals("student")) {
							rd = request.getRequestDispatcher("./studentsuccess.html");
						}
						if (AccountType.equals("admin")) {
							rd = request.getRequestDispatcher("./adminsuccess.html");
						} if (AccountType.equals("dpd"))
							rd = request.getRequestDispatcher("./dpdsuccess.html");
						}
						rd.forward(request, response);
					}
				} else {
					RequestDispatcher rd = request.getRequestDispatcher("./failure.html");
					rd.forward(request, response);
				}
		
//			} else {
//				System.out.println("else part");
//				RequestDispatcher rd = request.getRequestDispatcher("./failure.html");
//				rd.forward(request, response);
//			}
			 con.close();
		} catch (Exception e) {
			System.out.println(e);
//			RequestDispatcher rd = request.getRequestDispatcher("./failure.html");
//			rd.forward(request, response);
		}

	}

}

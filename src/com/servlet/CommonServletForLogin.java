package com.servlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		Integer dbLoginAttemptCount = 0;

		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("select UserID,password,AccountType,LoginAttemptCount from login where UserID='"
							+ UserID + "'");// and password='"+passWord+"'");
			if (rs.next()) {
				dbUserID = rs.getString("UserID");
				dbLoginAttemptCount = rs.getInt("LoginAttemptCount");

				//to chk if the userID exists.
				if (dbUserID == null) {
					RequestDispatcher rd = request
							.getRequestDispatcher("./failure.html");
					rd.forward(request, response);
				}
				if (dbLoginAttemptCount >=3) {
					updateLoginAttemptCount(++dbLoginAttemptCount, dbUserID);
					RequestDispatcher rd = request
							.getRequestDispatcher("./accountLocked.html");
					rd.forward(request, response);
				}
				dbPassWord = rs.getString("password");
				dbAccountType = rs.getString("AccountType");
				//				System.out.println("dbUSerName : " + dbUserID);
//				System.out.println("dbPassWord : " + dbPassWord);
//				System.out.println("dbAccountType : " + dbAccountType);
				//System.out.println("!!!!!!!!" +rs.getString("securityQuestion"));
//				if(UserID == null || passWord == null || AccountType == null ){
//					RequestDispatcher rd = request.getRequestDispatcher("./failure.html");
//					rd.forward(request, response);
//				}
				if (UserID != null && UserID != "") {
					RequestDispatcher rd = null;
					if (UserID.equalsIgnoreCase(dbUserID)
							&& passWord.equals(dbPassWord) && AccountType.equalsIgnoreCase(dbAccountType)) {
						updateLoginAttemptCount(0, dbUserID);
						if (AccountType.equals("student")) {
							rd = request.getRequestDispatcher("./studentsuccess.html");
							rd.forward(request, response);
						}
						else if (AccountType.equals("admin")) {
							rd = request.getRequestDispatcher("./adminsuccess.html");
							rd.forward(request, response);
						} else if (AccountType.equals("dpd")){
							rd = request.getRequestDispatcher("./dpdsuccess.html");
							rd.forward(request, response);
						}
					}else{
						updateLoginAttemptCount(++dbLoginAttemptCount, dbUserID);
						if(dbLoginAttemptCount==3){
							rd = request.getRequestDispatcher("./accountLocked.html");
							rd.forward(request, response);
						}else{
							rd = request.getRequestDispatcher("./failure.html");
							rd.forward(request, response);
						}
					}
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
			e.printStackTrace();
//			RequestDispatcher rd = request.getRequestDispatcher("./failure.html");
//			rd.forward(request, response);
		}

	}
	
	private void updateLoginAttemptCount(int count, String userID){
		String updateLoginAttemptCountString =
	            "update login " +
	            "set LoginAttemptCount = ? where UserID = ?";
		String updateStatusString =
	            "update login " +
	            "set Status = ? where UserID = ?";
		int status = 1;
	    try {
	    	Connection con = DBConnection.getConnection();
			PreparedStatement updateData = null;
			
			updateData = con.prepareStatement(updateLoginAttemptCountString);
			updateData.setInt(1, count);
		    updateData.setInt(2, Integer.parseInt(userID));
		    updateData.executeUpdate();
		    
		    updateData = con.prepareStatement(updateStatusString);
		    if(count>=3){
		    	status=0;
		    }
		    updateData.setInt(1, status);
		    updateData.setInt(2, Integer.parseInt(userID));
		    updateData.executeUpdate();
		    con.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }

}

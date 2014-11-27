package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CourseChangeStudentServlet
 */
public class CourseChangeStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CourseChangeStudentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = "Close";

		Enumeration<String> enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			// there is only one object, the selected button
			action = enumeration.nextElement();
		}

		String message = "Operation Failed";
		String delimiter = "[$]";
		String[] tokens = action.split(delimiter);
		String query;

		if (tokens[0].equals("Close")) {
			// get out
		}

		if (tokens.length < 2) {
			// set message failed
			return;
		}

		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs;

			if (tokens[0].equals("Add")) {
				rs = stmt
						.executeQuery("select * from courseoffered where CourseID = "
								+ tokens[1]);
				if (rs.next()) {
					String sessionRegisteredIn = rs.getString("sessionOffered");
					String schedule = rs.getString("Schedule");
					query = "INSERT INTO coursetaken VALUES (" + tokens[2]
							+ "," + tokens[1] + "," + null + ",\""
							+ sessionRegisteredIn + "\",\"" + schedule + "\");";
					stmt.executeUpdate(query);
					message = "The course was successfully added";
				}
			}
			if (tokens[0].equals("Drop")) {
				query = "DELETE FROM coursetaken WHERE UserID = " + tokens[2]
						+ " AND CourseID = " + tokens[1] + ";";
				stmt.executeUpdate(query);
				message = "The course was successfully dropped";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "An error ocurred during course registration.";
		}

		RequestDispatcher rd = request
				.getRequestDispatcher("./courseChangeStudent.jsp");
		rd.forward(request, response);
	}
}

package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.student.CourseRegistration;

/**
 * Servlet implementation class CourseRegistrationServlet
 */
public class CourseRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseRegistrationServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//To do
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<String> rowItems = new ArrayList<String>();

		CourseRegistration coursesRegistration = new CourseRegistration();
		// Iterate through courseOffed table and addCourseOffered
		// THIS MUST BE DONE BEFORE LOADING courseTaken
		try {
			HttpSession session = request.getSession();
			String userID = (String) session.getAttribute("userID");
			
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("select DepartmentID from studentdetails where UserID = " + userID);
			int departmentID = 0;
			if(rs.next())
				departmentID = rs.getInt("DepartmentID");
			
			rs = stmt.executeQuery("select * from courseoffered where DepartmentID = " + departmentID);

			while (rs.next()) {
				coursesRegistration.addCourseOffered(rs.getInt("CourseID"),
						rs.getString("CourseName"), rs.getInt("DepartmentID"),
						rs.getString("Schedule"),
						rs.getString("SessionOffered"),
						rs.getInt("PreRequisiteCourseID"),
						rs.getInt("CapacityOfStudent"));
			}

			// Iterate through coursetaken table and addCourseTaken
			// This will use information loaded in courseOffered
			rs = stmt.executeQuery("select * from coursetaken where UserID='"
					+ userID + "'");
			while (rs.next()) {
				coursesRegistration.addCourseTaken(rs.getInt("CourseID"),
						rs.getString("GradesObtained"));
			}

			// Build list/table
			coursesRegistration.buildRegistrationList(rowItems);

			PrintWriter out = response.getWriter();
			out.println("<html><body>"
					+ "<form action=\"./studentsuccess.jsp\" method=POST >"
					+ "<input type=\"submit\"  name=\"submit\" value=\"Return\" /></form>"
					+ "<form action=\"./CourseChangeStudentServlet\" method=POST>"
					+ "<h1 align=center ><font color=blue>Course Registration</font></h1>"
					+ "<br><table border =1 align=center >"
					+ "<tr><th>Selection</th><th>Course Name</th><th>Schedule</th></tr>");

			for (ListIterator<String> iter = rowItems.listIterator(); iter
					.hasNext();) {
				String rowItem = iter.next();
				String delimiter = "[$]";
				String[] tokens = rowItem.split(delimiter);
				if (tokens.length < 4)// error, expecting 4 items: buttonLabel,
										// courseName, courseSchedule, courseID
					continue;
				String activeState = "disabled";
				if (tokens[0].equals("Add") || tokens[0].equals("Drop"))
					activeState = "enabled";

				String name = tokens[0] + "$" + tokens[3] + "$" + userID;
				String row = "<tr><td><input name= \"" + name
						+ "\" type=\"submit\" value="
						+ tokens[0] + " " + activeState + "></td><td>"
						+ tokens[1] + "</td><td>" + tokens[2] + "</td></tr>"; 
				out.println(row);
			}

			out.println("</table border=3 ></form></body></html>");
			out.println("<br/><br/>");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.RequestDispatcher;
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

	String testString = "ADD";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		request.setAttribute("StudentName", "Mary");
//		RequestDispatcher rd = request.getRequestDispatcher("./courseRegistration.jsp");
//		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> rowItems = new ArrayList<String>();

		CourseRegistration coursesRegistration = new CourseRegistration();
		// Iterate through courseOffed table and addCourseOffered
		// THIS MUST BE DONE BEFORE LOADING courseTaken
		// coursesRegistration.addCourseOffered(courseId, courseName,
		// departmentId, professor, labId, courseSchedule, sessionOffered,
		// addDropDeadline, preRequisiteCourseId, classCapacity);
		coursesRegistration.testLoadCourseOfferedDummyData();

		// Iterate through coursetaken table and addCourseTaken
		// This will use information loaded in courseOffered
		// coursesRegistration.addCourseTaken(courseId, gradeObtained);
		coursesRegistration.testLoadCourseTakenDummyData();
		// Build list/table
		coursesRegistration.buildRegistrationList(rowItems);
		
		PrintWriter out = response.getWriter();
	    out.println("<html><body><h1 align=center ><font color=blue>Course Registration</font></h1><br><table border =1 align=center ><tr><th>Selection</th><th>Course Name</th><th>Schedule</th><th>Student ID</th></tr>");	

	    for (ListIterator<String> iter = rowItems.listIterator(); iter
				.hasNext();) {
			String rowItem = iter.next();
			String delimiter = "[$]";
			String[] tokens = rowItem.split(delimiter);
			if(tokens.length < 4)//error, expecting 4 items
				continue;
			String activeState = "disabled";
			if(tokens[0].equals("Add") || tokens[0].equals("Drop"))
				activeState = "enabled";
			
			out.println("<tr><td><input name="+tokens[0]+" "+tokens[3]+" type=\"submit\" value="+tokens[0]+" "+activeState+"></td><td>"+tokens[1]+"</td><td>"+tokens[2]+"</td></tr>");
		}
		
		out.println("</table border=3 ></body></html>");
		out.println("<br/><br/>");
	}

}

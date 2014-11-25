package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

		request.setAttribute("StudentName", "Mary");
//		RequestDispatcher rd = request.getRequestDispatcher("./courseRegistration.jsp");
//		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//RequestDispatcher rd = request.getRequestDispatcher("./courseRegistration.jsp");
		//rd.forward(request, response);

	}

}

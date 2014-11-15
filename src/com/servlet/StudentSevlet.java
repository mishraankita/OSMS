package com.servlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class StudentSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int result =0;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname = request.getParameter("firstname");
		String middlename = request.getParameter("middlename");
		String lastname = request.getParameter("lastname");
		String fname = request.getParameter("fname");
		String dob = request.getParameter("dob");
		String address = request.getParameter("address");
		String Curriculum = request.getParameter("Curriculum");
		String Grade = request.getParameter("Grade");
		
        response.setContentType("text/html");
        String dbUSerName = null;
        String dbPassWord = null;
        String role = null;
        RequestDispatcher rd = null;
   
        try {
//        	Class.forName("com.mysql.jdbc.Driver");
//        	Connection con=  DriverManager.getConnection("jdbc:mysql://localhost:3306/School", "root", "");
        	Connection con= DBConnection.getConnection();
        	String query = "insert into studentinfo(firstname,middlename,lastname,fname,dob,address,Curriculum,Grade) values(?,?,?,?,?,?,?,?) ";
        	PreparedStatement psmt = con.prepareStatement(query);
        	psmt.setString(1, firstname);
        	psmt.setString(2, middlename);
        	psmt.setString(3, lastname);
        	psmt.setString(4, fname);
        	psmt.setString(5, dob);
        	psmt.setString(6, address);
        	psmt.setString(7, Curriculum);
        	psmt.setString(8, Grade);
        	
        	 result = psmt.executeUpdate();
        	 
        	if(result==1){
        		request.setAttribute("firstname", firstname);
        		rd  = request.getRequestDispatcher("./regsuccess.jsp");
    		rd.forward(request, response);
        	}else{
        		rd  = request.getRequestDispatcher("./regfailure.jsp");
        		rd.forward(request, response);
        	}
        		
	   
	        
        } 
        catch(Exception e){
        System.out.println(e);
    	rd  = request.getRequestDispatcher("./regfailure.jsp");
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


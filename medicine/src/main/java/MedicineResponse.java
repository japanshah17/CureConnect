

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MedicineResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String name = request.getParameter("name");
		String aadhar = request.getParameter("aadhar");
		String medicines = request.getParameter("medicines");
		
		boolean match = false;

		try{

			Class.forName("com.mysql.cj.jdbc.Driver");

		Connection con=DriverManager.getConnection ("jdbc:mysql://localhost:3306/medicine","root","");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from medicine_request");
		while(rs.next()){
			
			String requestAadhar = rs.getString("aadhar");			
			if(requestAadhar.equals(aadhar)) {
				match = true;
				break;
			}
		}

		
		if(match == true) {
			PreparedStatement stmt=con.prepareStatement( "insert into medicine_response (name, aadhar, medicines) values (?,?,?)" );
			stmt.setString(1,name);  
			stmt.setString(2,aadhar);  
			stmt.setString(3,medicines);
			int i=stmt.executeUpdate();  
			out.println(i+" records inserted");
			match = false;
		}
		else {
			out.println("Given aadhar not requested for medicines"); 
		}
		
	
	con.close();

		}catch(Exception e)

		{out.println("<p>inside exception"+e.toString()+"</p>");}
		
		
	}

}

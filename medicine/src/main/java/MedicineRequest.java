import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class MedicineRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String name = request.getParameter("name");
		String aadhar = request.getParameter("aadhar");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String address = request.getParameter("address");
		String symptoms = request.getParameter("symptoms");
		
		try{

			Class.forName("com.mysql.cj.jdbc.Driver");

		Connection con=DriverManager.getConnection ("jdbc:mysql://localhost:3306/medicine","root","");

		PreparedStatement stmt=con.prepareStatement( "insert into medicine_request (patientName, aadhar, age, gender, symptoms, address) values (?,?,?,?,?,?)" );
		stmt.setString(1,name);  
		stmt.setString(2,aadhar);  
		stmt.setString(3,age);  
		stmt.setString(4,gender);  
		stmt.setString(5,symptoms);
		stmt.setString(6,address);  
		  
			
		int i=stmt.executeUpdate();  
		out.println(i+" records inserted");  
	con.close();

		}catch(Exception e)

		{out.println("<p>inside exception"+e.toString()+"</p>");}
	}

}

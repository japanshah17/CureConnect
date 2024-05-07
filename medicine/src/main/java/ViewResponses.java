import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class ViewResponses extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		PrintWriter out=response.getWriter();
	
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection ("jdbc:mysql://localhost:3306/medicine","root","");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from medicine_response");
		out.println("<html><head>"
				+ "<link rel=stylesheet href=style.css>"
				+ "</head><body>");
		   out.println("<div class=container>");
		   out.println("<Table border=1 align=center >");
	         out.println("<H2><B><center>Medicine Responses</center></B></H2><BR>");
	         out.println("<TR><TD><b>Name</b></TD><TD><b>Aadhar Number</b></TD><TD><b>Medicines</b></TD></TR>");
		while(rs.next())
		{
			String name = rs.getString("Name");
			String aadhar = rs.getString("aadhar");
		
			String medicines = rs.getString("medicines");
			out.println("<TR><TD>"+name+"</TD><TD>"+aadhar+"</TD><TD>"+medicines+"</TD></TR>");
		}
        out.println("</Table>");
		  
		
		 out.println("</div>");
        out.println("</body></html>");
	con.close();

		}catch(Exception e)

		{out.println("<p>inside exception"+e.toString()+"</p>");}

	}

}

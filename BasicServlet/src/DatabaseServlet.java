import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/SessionExampleServlet")
public class DatabaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String connectionUrl = "jdbc:oracle:thin:@localhost:1521:xe";

    public DatabaseServlet() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
				processRequest(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
				processRequest(request,response); 
	}

protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
	String message = "";
	int nmbrRecords = 0;
	
	try
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
	    Connection conn =
	            DriverManager.getConnection(connectionUrl,"system","password");

       conn.setAutoCommit(false);
       Statement stmt = conn.createStatement();
       ResultSet rs = stmt.executeQuery("select count(*) from testdb.demo_orders");
       
      if (rs.next())
      {
    	  message = "There are " + rs.getString(1) + " records";
    	  nmbrRecords = rs.getInt(1);
    	  
      }else{
    	  message = "There are no records.";
      }
    	  
	} catch (Exception e){
		message = e.toString();
	} 
	
	
	//add the record count to a session
	HttpSession session = request.getSession();
	session.setAttribute("recordcount", nmbrRecords);
	
	 request.setAttribute("message",message);
     getServletContext()
     	.getRequestDispatcher("/output.jsp")
     		.forward(request, response);
}
}

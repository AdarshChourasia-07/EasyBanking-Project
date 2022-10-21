/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easybanking.controller;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hp
 */
public class WelcomeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    public WelcomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    
    Connection con;
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
try{
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@//AdarshChourasia:1521/xe","easybanking","easybanking");
			System.out.println("Database connection established successfully in Customer welcome servlet");
			
		}
		
		catch(Exception e){
			System.err.println(e);
			
		}
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		//String s1=request.getParameter("uname");
		//String s2=request.getParameter("pword");
		HttpSession session=request.getSession();
        String userid=(String)session.getAttribute("uname");
        if(userid==null)
        {
            session.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
		
		HttpSession hs=request.getSession();
		
		String un=(String)hs.getAttribute("uname");
		String pwd=(String)hs.getAttribute("pword");
		
		
		try{
		PreparedStatement pstmt=con.prepareStatement("select * from customer where user_id=? and pword=?");
		
		if( ! un.equals(null) && ! pwd.equals(null))
		{
			pstmt.setString(1, un);
			pstmt.setString(2, pwd);
		}
		
		
		PrintWriter pw=response.getWriter();
		
		
		
		ResultSet rs=pstmt.executeQuery();
	
		rs.next();
		
		
		
		
		Cookie c1=new Cookie("lname",rs.getString(2));
		Cookie c2=new Cookie("AccountNoC",rs.getString(6));
		Cookie c3=new Cookie("bal",rs.getString(8));
		
		response.addCookie(c1);
		response.addCookie(c2);
		response.addCookie(c3);
		
		response.setContentType("text/html");
		pw.println("<body>");
		pw.println("<body background='../img/BackgrounfFor1'>");
		
		pw.println("<strong><a href=aboutus.html>About Us</a></strong> &nbsp;&nbsp;&nbsp;&nbsp;");
		pw.println("<strong><a href=contactus.html>Contact Us</a></strong> &nbsp;&nbsp;&nbsp;&nbsp;");
		pw.println("<strong><a href=careers.html>Careers</a></strong> &nbsp;&nbsp;&nbsp;&nbsp;");
		pw.println("<strong><a href=changecredentials.html >Change User Credentials</a></strong>&nbsp;&nbsp;&nbsp;&nbsp;");
		pw.println("<strong><a href=LogOut>Log out</a></strong> <br />");
		
		
		pw.println("<hr />");
		pw.println("<html> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<h4>Welcome <em>"+rs.getString(2)+"</em></h4>");
		
		
		pw.println("Account Number  : &nbsp;<strong>"+rs.getString(6)+"</strong>");
		pw.println("<br />Your current Balance  : &nbsp;<strong> USD &nbsp; "+rs.getString(8)+"</strong>");
		
		
		
		pw.println("<h3>Menu</h3>");
		pw.println("<ul> <li><a href=FTWithin.html>Funds Transfer With in Bank</a></li>");
		pw.println("<br /> <li> <a href=Ftother.html>Funds Transfer to Other Bank</a></li>");
		pw.println("<br /><li> <a href=complaint.html>Register a Complaint</a></li>");
		pw.println("<br /><li> <a href=ViewComplaint>View Complaints' status</a></li>");
		pw.println("<br /><li> <a href=ViewTransactions>View Transactions</a></li>");
		pw.println("<br /><li> <a href=changecredentials.html >Change User Credentials</a></li>");
		pw.println("<br /><li> <a href=logOut>Log out</a></li></ul>");
		pw.println("</body>");
		}
		catch(Exception e){
			System.err.println(e);
		}
		
	}
    
    
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

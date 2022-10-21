/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easybanking.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
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
public class Complaint extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     public Complaint() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    
    Connection con;
    long complNo;
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		


		super.init(config);
			
try{
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@//AdarshChourasia:1521/xe","easybanking","easybanking");
			System.out.println("Database connection established successfully in registering complaint servlet");
			
			complNo=(long)Math.random();
		}
		
		catch(Exception e){
			System.err.println(e);
			
		}
        }
        
        protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
        String userid=(String)session.getAttribute("uname");
        if(userid==null)
        {
            session.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
		
		String s1=request.getParameter("subject");
		String s2=request.getParameter("desc");
		
		Cookie c[]=request.getCookies();
		
		response.setContentType("text/html");
		PreparedStatement pstmt;
		
		try{
			
			
			complNo++;
	       pstmt=con.prepareStatement("insert into complaint(complaint_no,act_no,subject,description) values(?,?,?,?)");
			
	       System.out.println("complaint No is :"+complNo);
	        pstmt.setLong(1, complNo);
	        pstmt.setString(2, c[2].getValue());
			pstmt.setString(3, s1);
			pstmt.setString(4, s2);
	     
			pstmt.executeUpdate();
			
			PrintWriter pw=response.getWriter();
			
			pw.println("<a href=http://localhost:2020/EasyBanking/WelcomeServlet>Home</a>\t\t");
			
			pw.println("<a href=http://localhost:2020/EasyBanking/LogOut>Log out</a><br /><br />");
			
						
			pw.println("<br />Your complaint is registered successfully and the complaint number is "+ complNo);
                        
                       
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easybanking.controller;

import easybanking.dto.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hp
 */
public class CustomerLogin extends HttpServlet {

    public CustomerLogin() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    ServletContext sc;
    Connection con;
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
		try{
			
			sc=getServletContext();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@//AdarshChourasia:1521/xe","easybanking","easybanking");
			System.out.println("Database connection established successfully in validating customer login credential servlet");
			
		}
		
		catch(Exception e){
			System.err.println(e);
		}
		
	}

    
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 
		try{
			
			String s1=request.getParameter("uname");
			String s2=request.getParameter("pword");
			
			PreparedStatement pstmt=con.prepareStatement("select * from customer where user_id=? and pword=?",ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, s1);
			pstmt.setString(2, s2);
			
			ResultSet rs=pstmt.executeQuery();
			
			PrintWriter pw=response.getWriter();
			
			
			if(rs.next()){
				
				HttpSession hs=request.getSession();
				hs.setAttribute("uname",s1);
				hs.setAttribute("pword", s2);
				
				RequestDispatcher rd=sc.getRequestDispatcher("/WelcomeServlet");
				rd.forward(request, response);
				
				
			}
			else {
				
				pw.println("<html> <h6> Invalid Login credential.Please try again</h6><br /></html>");
				RequestDispatcher rd=sc.getRequestDispatcher("/customerlogin.html");
				rd.include(request, response);
								
			}
			
			
			
					
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

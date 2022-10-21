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
import java.sql.ResultSet;
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
public class UpdateCredentials extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    public UpdateCredentials() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    
    Connection con;
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		

		super.init(config);
		
		
try{
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@//AdarshChourasia:1521/xe","easybanking","easybanking");
			System.out.println("Database connection established successfully in user credentials change servlet");
			
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
		
		HttpSession session=request.getSession();
        String userid=(String)session.getAttribute("uname");
        if(userid==null)
        {
            session.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
                
		String s1=request.getParameter("newuid");
		String s2=request.getParameter("newpword");
		Cookie c[]=request.getCookies();
		response.setContentType("text/html");
		
		int uidlen=s1.length();
		int pwdlen=s2.length();
		
		PrintWriter pw=response.getWriter();
		
		pw.println("<a href=http://localhost:2020/EasyBanking/WelcomeServlet>Home</a>&nbsp; &nbsp;");
		
		pw.println("<a href=http://localhost:2020/EasyBanking/LogOut>Log out</a><br /><br />");
		
		pw.println("<br /><br />");
		
		PreparedStatement pstmt;
		
		try {
		
		if(uidlen==0 && pwdlen==0){
			pw.println("<br /> You have not updated User Id & Password. Login credentials remain same");
		}
		
		else if(uidlen==0 && pwdlen!=0){
			pstmt=con.prepareStatement("update customer set pword=? where act_no=?");
			pstmt.setString(1, s2);
			
			
			pstmt.setString(2, c[2].getValue());
			
			pstmt.executeUpdate();
			
			pw.println("<br /> Your password updated successfully");
		}
		
		else if(uidlen!=0 && pwdlen==0){
			
			pstmt=con.prepareStatement("select user_id from customer");
			ResultSet rs=pstmt.executeQuery();
			boolean userdupl=false;
			while ( rs.next()){
				
				if(s1.equals(rs.getString(1)))
					userdupl=true;
							
			}
			
			
			if(!userdupl)
			{			
			
			pstmt=con.prepareStatement("update customer set user_id=? where actno=?");
			pstmt.setString(1, s1);
			pstmt.setString(2, c[2].getValue());
			
			pstmt.executeUpdate();
			
			pw.println("<br /> Your user id updated successfully");
			
			}
			
			else
			{
			
				pw.println("Given user id is already in use. Please use another user id");
			}
		}
		
		else if(uidlen!=0 && pwdlen!=0) 
		{
			
			pstmt=con.prepareStatement("select user_id from customer");
			ResultSet rs=pstmt.executeQuery();
			boolean userdupl=false;
			while ( rs.next()){
				
				if(s1.equals(rs.getString(1)))
					userdupl=true;
							
			}
			
			
			if(!userdupl) {
			pstmt=con.prepareStatement("update customer set user_id=? where act_no=?");
			pstmt.setString(1, s1);
			pstmt.setString(2, c[2].getValue());
			
			pstmt.executeUpdate();
			
			
			pstmt=con.prepareStatement("update customer set pword=? where act_no=?");
			pstmt.setString(1, s2);
			pstmt.setString(2, c[2].getValue());
			
			pstmt.executeUpdate();
			
			pw.println("<br /> Your User id & Password updated successfully");
			}
			
			else {
				pw.println("Given user id is already in use. Please use another user id");
			}
		
		}//else
		
		}//try
		
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

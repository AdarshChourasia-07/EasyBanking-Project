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
import java.sql.ResultSetMetaData;
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
public class ViewTransactions extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    public ViewTransactions() {
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
			System.out.println("Database connection established successfully");
			
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
		
		PreparedStatement pstmt;
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
                pw.println("<strong><a href=http://localhost:2020/EasyBanking/WelcomeServlet>Home</a></strong>\t\t");
		
		pw.println("<strong><a href=http://localhost:2020/EasyBanking/LogOut>Log out</a></strong><br /><br />");
		
		pw.println("<h4>Details of Transactions :</h4>");
		
		Cookie c[]=request.getCookies();
		
		
		try{
			
			pstmt=con.prepareStatement("select * from transaction where act_no=?",ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, c[2].getValue());
			
			ResultSet rs=pstmt.executeQuery();
			
		
			if(rs.absolute(1))
			{
			
			
						
						
						ResultSetMetaData rm=rs.getMetaData();
						
						int colcnt=rm.getColumnCount();
						
						for(int i=1;i<=colcnt;i++){
							
							pw.print("<strong>"+rm.getColumnName(i).toLowerCase()+",</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
							
						}//for
						
						rs.first();
						do {
							pw.println("<br />");
							
							for(int i=1;i<=colcnt;i++){
								
								pw.print(rs.getString(i)+", &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
								
							} //for
							
										
						} while(rs.next());//while
			
			}//if
			
			else{
				pw.println("There are no Transactions happened in this account");
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

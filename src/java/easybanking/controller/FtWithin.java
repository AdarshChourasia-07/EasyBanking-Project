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
import java.sql.SQLException;
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
public class FtWithin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     public FtWithin() {
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
			System.out.println("Database connection established successfully in Funds transfer With in bank servlet");
			
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
                
		String s1=request.getParameter("actno");
		String s2=request.getParameter("amt");
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		
		pw.println("<a href=http://localhost:2020/EasyBanking/WelcomeServlet>Home</a>\t");
		
		pw.println("<a href=http://localhost:2020/EasyBanking/LogOut>Log out</a><br /><br />");
		
		pw.println("<h3>Funds Transfer to Other Account With in Bank :</h3> ");
		pw.println("Recipient Account number is : "+s1+"<br />");
		pw.println("Balance to be transferred is : "+s2);
		
		Cookie c[]=request.getCookies();
		PreparedStatement pstmt;
		ResultSet rs1;
		
		long tranid=0;
		String remarks="NA",transtatus="NA",trandesc="Funds Transfer With in the Bank",act_no=c[2].getValue();

		try {
			pstmt =con.prepareStatement("select tran_seq.nextval from dual");
			
			rs1=pstmt.executeQuery();
			rs1.next();
			tranid=rs1.getLong(1);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if(Integer.parseInt(c[3].getValue())<Integer.parseInt(s2)){
			
			pw.println("<br /><br /><strong> Funds transfer can not be initiated as available balance is less than the to be transferred amount</strong>");
			remarks="Funds transfer can not be initiated as available balance is less than the to be transferred amount";
			transtatus="fail";
		}
		
		else{
			
			
			
			try {
				
				pstmt=con.prepareStatement("select * from customer where act_no=?");
				
				pstmt.setString(1, s1);
				
				ResultSet rs=pstmt.executeQuery();
				
				if(rs.next()){
				
				int newRecptBal=Integer.parseInt(rs.getString(8));
				newRecptBal=newRecptBal+Integer.parseInt(s2);
				
				pstmt=con.prepareStatement("update customer set balance=? where act_no=?");
				pstmt.setLong(1, newRecptBal);
				pstmt.setString(2, s1);
				pstmt.executeUpdate();
				
				int newBal= Integer.parseInt(c[3].getValue())-Integer.parseInt(s2);
				
				
				pstmt = con.prepareStatement("update customer set balance=? where act_no=?");
							
				pstmt.setLong(1, newBal);
				pstmt.setString(2,c[2].getValue());
				
				pstmt.executeUpdate();
				
				pw.println("<br /> <br />Funds <strong> USD "+s2+" </strong> transferred successfully to the account number <strong>"+s1+"</strong>");
				pw.println("<br /> Transaction Id is : <strong>"+tranid+"</strong>");
				
				pw.println("<br /><br />Available balance in the account is USD <strong>"+newBal+"</strong");
				
				remarks="Funds transferred successfully";
				System.out.println("New balance is updated successfully in database");
				transtatus="pass";
				
			
				}
				
				else {
					
					pw.println("<br /><br /><strong>Recipient account number is incorrect. Please check</strong>");
					remarks="Recipient account number is incorrect";
					transtatus="fail";
				}
				
				
			} 
			
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}//else
		
		try {
		
		pstmt=con.prepareStatement("insert into transaction(tranid,act_no,tran_desc,tran_status,remarks) values(?,?,?,?,?)");
		
		pstmt.setLong(1,tranid);
		pstmt.setString(2, act_no);
		pstmt.setString(3, trandesc);
		pstmt.setString(4, transtatus);
		pstmt.setString(5, remarks);
		
		pstmt.executeUpdate();
		
		System.out.println("Transaction table updated successfully");
		
		}
		catch(Exception e) {
			System.out.println(e);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easybanking.controller;

import easybanking.dao.RegistrationDAO;
import easybanking.dto.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hp
 */
public class RegistrationControllerServlet extends HttpServlet {

    
        public RegistrationControllerServlet() {
            super();
    }
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         RequestDispatcher rd=null;
         Customer cust=new Customer();
        cust.setFname(request.getParameter("fname"));
        cust.setLname(request.getParameter("lname"));
        cust.setBdate(request.getParameter("bdate"));
        cust.setUserid(request.getParameter("userid"));
        cust.setPword(request.getParameter("pword"));
        cust.setActno(request.getParameter("actno"));
        cust.setGender(request.getParameter("gende"));
        cust.setBal(request.getParameter("bal"));
        
        
         try
        {
            boolean result=true;
             result=RegistrationDAO.registerUser(cust);
            request.setAttribute("fname", cust.getFname());
           PrintWriter pw=response.getWriter();
			pw.println("<html> <h4>");
					
			pw.println(cust.getFname().toUpperCase()+"&nbsp;"+cust.getLname().toUpperCase()+"&nbsp; is successfully registered for Online Banking</h4>");
			
            rd=request.getRequestDispatcher("registrationresponse.jsp");
            
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            request.setAttribute("Exception", ex);
            rd=request.getRequestDispatcher("/showexception.jsp");
        }
        finally
        {
            rd.forward(request, response);
        }
        
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
    
   

    
    
    
    

    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      /*  RequestDispatcher rd=null;
         Customer cust=new Customer();
        cust.setFname(request.getParameter("fname"));
        cust.setLname(request.getParameter("lname"));
        cust.setBdate(request.getParameter("bdate"));
        cust.setUserid(request.getParameter("userid"));
        cust.setPword(request.getParameter("pword"));
        cust.setActno(request.getParameter("actno"));
        cust.setGender(request.getParameter("gende"));
        cust.setBal(request.getParameter("bal"));
        
        
         try
        {
            boolean result=true,userfound=false;
           
          
            request.setAttribute("result", result);
            request.setAttribute("username", cust.getFname());
            rd=request.getRequestDispatcher("registrationresponse.jsp");
            
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            request.setAttribute("Exception", ex);
            rd=request.getRequestDispatcher("showexception.jsp");
        }
        finally
        {
            rd.forward(request, response);
        }
        */
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

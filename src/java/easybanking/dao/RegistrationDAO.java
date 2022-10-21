/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easybanking.dao;

import easybanking.dbutil.DBConnection;
import easybanking.dto.Customer;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author hp
 */
public class RegistrationDAO {
    private static PreparedStatement ps,ps1;
     static
   {
    try
    {
        ps=DBConnection.getConnection().prepareStatement("insert into customer values(?,?,?,?,?,?,?,?)");
       
     ps1=DBConnection.getConnection().prepareStatement("Select * from Customer where user_id=?");
        
        
    }
    catch(SQLException ex)
    {
        ex.printStackTrace();
    }
    
   }
     
     
   /*  public static boolean searchUser(String userid) throws SQLException
   {
       ps.setString(1,userid);
       
       return ps1.executeQuery().next();
       
   }*/
     
     public static boolean registerUser(Customer cust) throws SQLException
        {
                        ps.setString(1,cust.getFname());
			ps.setString(2,cust.getLname());
			ps.setString(3,cust.getBdate());
			ps.setString(4,cust.getUserid());
			ps.setString(5,cust.getPword());
			ps.setString(6,cust.getActno());
			ps.setString(7,cust.getGender());
			ps.setString(8,cust.getBal());
			
			return ps.executeUpdate()==1;
        }
    
    
    
    
    
}

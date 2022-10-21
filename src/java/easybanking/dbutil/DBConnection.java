/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easybanking.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hp
 */
public class DBConnection {
     private static Connection conn=null;
    static 
    { 
        try
        {
        
        Class.forName("oracle.jdbc.OracleDriver");
        conn=DriverManager.getConnection("jdbc:oracle:thin:@//AdarshChourasia:1521/xe","easybanking","easybanking");
        System.out.println("Driver Loaded and connection opened!");
        }
        catch(ClassNotFoundException cnf)
         {
                    cnf.printStackTrace();
         }
        catch(SQLException sqlex)
        {
           sqlex.printStackTrace();
        }
    }
    
    public static Connection getConnection()
    {
        return conn;
    }
    public static void closeConnection()
    {
        try
        {
            if(conn!=null)
                conn.close();
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
        }
                
}
}
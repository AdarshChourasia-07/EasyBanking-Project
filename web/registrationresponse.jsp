<%-- 
    Document   : registrationresponse
    Created on : 5 Nov, 2021, 5:17:07 PM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import = "easybanking.dto.Customer" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style type="text/css">
       div
        {
            font-family: "Open Sans", Helvetica, Arial, sans-serif;
            background: url("../img/BackgrounfFor1.png") no-repeat center center fixed;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            background-size: cover;
            -o-background-size: cover;
        }   
    </style>
</head>
<body>
    <div>

    </div>
<button id="myButton">click!</button>

<%
          Customer cust=new Customer();
          out.println("<html> <h4>");			
          out.println( " Successfully registered on EASY BANKING</h4></html>");
          response.sendRedirect("customerlogin.html");
          System.out.println("REGISTRATION RESPONSE WORKED");
%> 
</body>
</html>
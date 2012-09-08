<%-- 
    Document   : header
    Created on : Apr 4, 2012, 4:34:25 PM
    Author     : louise
--%>
<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<%
    int userLogin = 0;
    String currentUser = "";
    System.out.println("session object before ........"+currentUser);
    if ( session != null ) {
    
        currentUser = (String) session.getAttribute("UserName");
        System.out.println("session object after ........"+currentUser);
        if(currentUser!=null){
            userLogin = 1;
               }
        
 
    }

        if(userLogin==0){
            String url = (request.getRequestURL()).toString();
            if(url.indexOf("index.jsp")== -1){
                response.sendRedirect("index.jsp");
                
            }
        }
%>

<header>
    
    <div class="container">
        <h1>
            <a href="index.jsp">Tut-Tutoring</a>
        </h1>
        <nav>
            <ul>
               
                <%                   
                   //Only show these links if the user is signed in
                   // 0 is not a user
                   if(userLogin!=0){                            
                 %>
                <li>
                    <a href="index.jsp" onclick="this.className='current'" >Home</a>
                </li>
                <li>
                    <a href="category.jsp" onclick="this.className='current'" >Category</a>
                </li>
                <li>
                    <a href="gallery.jsp" onclick="this.className='current'" >Gallery</a>
                </li>
                    <li>
                    <a class='inline' href="#edit_details" >Edit Details</a>
                </li>
                
                    <li>
                    <a href="UserServlet?option=4" >Logout</a>
                </li>
                <% }  else { %>
                <li>
                    <a class='inline' href="#inline_content" >Login</a>
                </li>
                <li>
                    <a class='inline' href="#register" >Register</a>
                </li>                
                <% } %>
            </ul>
        </nav>
    </div>
</header>


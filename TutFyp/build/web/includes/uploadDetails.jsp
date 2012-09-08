<%-- 
    Document   : uploadDetails
    Created on : May 24, 2012, 6:49:21 PM
    Author     : louise
--%>

<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Lesson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <% 
 
    Lesson testuploadm = new Lesson();
    String upid = request.getParameter("uploadid");
    System.out.println("upload id is " + upid);
    String str="none selected";
    String uploaddate="";
    if(upid!=null){
    
    int upid_int = Integer.parseInt(upid);
    
    
    
    //testuploadm= (Lesson) session.getAttribute("uploadbyid");
   
     testuploadm.getByUploadID(upid_int);
   
     System.out.println("after id passed to servlet " + testuploadm.getMp3loc());
     str = testuploadm.getMp3loc();
     str = str.substring(0, str.lastIndexOf('.'));
     String uploadDate = testuploadm.getTimestamp();
     uploaddate = uploadDate.split(" ")[0].substring(0, 10);
       }
    
   %>

  <div id="swapupload">
    <ul class="contacts">
        
        <li>
            <strong>Title:</strong><%=str%></li>
        <li>
            <strong>Uploader:</strong><%=testuploadm.getUserID() %></li>
        <li>
            <strong>Date</strong><%=uploaddate %></li>
    </ul>
    <h3>Details:</h3><%=testuploadm.getDescripupload() %>
</div>
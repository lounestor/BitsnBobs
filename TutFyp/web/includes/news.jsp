<%-- 
    Document   : news
    Created on : Apr 18, 2012, 4:28:42 PM
    Author     : louise
--%>

<%@page import="fyp.lou.tut.LessonServlet"%>
<%@page import="java.util.Iterator"%>
<%@page import="entity.Lesson"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
 LessonServlet.loadLessonbyLikes(request, response);   
     int count = 0;       
    //Integer.parseInt(session.getAttribute("type").toString());
    Collection collection = new ArrayList<Lesson>();
    collection = (Collection) session.getAttribute("MostLikedLesson");
    Iterator it = collection.iterator();

   while (it.hasNext()) {

        Lesson lessonList = new Lesson();
        lessonList = (Lesson) it.next();
        // session.getAttribute("prodid");
        count++;
        System.out.println(count);
                                              
                                      
%>

<ul class="news news<%=(count % 4) %>" >
    <li >
        
        <figure>
           
            <strong><%=lessonList.getLikeTotal() %> </strong>likes
        </figure>
        <h3>
            
            <a href="${pageContext.request.contextPath}/TutFyp/includes/login.jsp"><%=lessonList.getTitle() %></a>
            <% 
                String s = lessonList.getDescription(); 
                String words[] = s.split(" ");
                String briefdescription = words[0] + "  " + words[1]+ "  " + words[2]+"  " + words[3]
                        +"  " + words[4]+"  " + words[5]+"  " + words[6]+"  " + words[7]
                        +"  " + words[8]+"  " + words[9]+"  " + words[10]+"  " + words[11]+"  " + words[12]; // first two words


             %>
        </h3><%=briefdescription %>
        <a href="#">...</a>
        
    </li> 
     <%}%> 
</ul>

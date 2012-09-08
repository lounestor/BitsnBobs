<%@page import="java.util.Iterator"%>
<%@page import="entity.User"%>
<%@page import="entity.Lesson"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">

<div id="categorylist">
    <% 

  //Integer.parseInt(session.getAttribute("type").toString());
  Collection col2 = new ArrayList<Lesson>();
  System.out.println("**************size of collection" + col2.size());
  col2 = (Collection) session.getAttribute("lessonsbyCategory");
  Lesson.Category accinter = Lesson.Category.intermediate;    
  col2 = Lesson.getByCategory(accinter);
  Iterator it = col2.iterator();
  while (it.hasNext()) {
      Lesson lessonList = new Lesson();
      lessonList = (Lesson) it.next();
      // session.getAttribute("prodid");  
      System.out.println(lessonList);
   %>
    <ul class ="cat-list">
        <li>
            <figure>
                <img src="images/img2.png" alt="">
            </figure>
            <h3>
                <a href="${pageContext.request.contextPath}/LessonServlet?option=7&lessonid=<%=lessonList.getLessonID() %>"><%=lessonList.getTitle() %></a>
            </h3>
            <% 
                String s = lessonList.getDescription();
                String words[] = s.split(" ");
                for (int i = 0; i < 27; ++i){
                String briefdescription = words[i]; // first two words


             %>
                <%=briefdescription %>
           <%}%><a href="#">...</a>
             <br/>by &raquo;
             <a href="#"><%=lessonList.getUserID() %></a>
        </li> 
    </ul>
    <br/>  <!--keep the distance-->
  <% } if(col2.isEmpty()){%>
  <br/>
  <br/>
  <br/>  
  <p></p>
     <p><font face="Georgia, Arial" color="maroon">Sorry! No Results Found</font></p>
    <%}%>
</div>
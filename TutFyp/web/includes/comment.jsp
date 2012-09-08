<%-- 
    Document   : comment
    Created on : May 22, 2012, 8:30:17 PM
    Author     : louise
--%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Comment"%>
<%@page import="java.util.Collection"%>
<% 
    Collection comments = new ArrayList<Comment>();
    comments = Comment.showOriginalComments(1); 
    
   %>
<div id="commentwrapper">
    
    <% Iterator it = comments.iterator();
    while (it.hasNext()) {
      Comment comm = new Comment();
      comm = (Comment) it.next();
      Collection replies = new ArrayList<Comment>();
      replies = comm.getRepliesToThisComment();
      Iterator itreply = replies.iterator();
      
      // session.getAttribute("prodid");  
    //  System.out.println(comm);
   %>
    
    <div class="maincom" id="comment<%=comm.getCommentID() %>">
        <img src="images/avatar.png" width="70" height="65" border="0" class="avatar"/>
        
        <% 
                        //create a thumbnail of the video from url
                         String sampleDate = comm.getTimestamp();
                         String date = sampleDate.split(" ")[0].substring(0, 10);

         %>
        <div class="text"><%=comm.getText() %>
            <div class="date"><%=date %></div>
        </div>
        
        <a href="#" id="1" class="delete">x</a>
        
        <div class="clear"></div>
       
       <div class="showreply" title="<%=comm.getCommentID() %>" > reply to</div>
         
    </div>  
        
    <div class="commentreplies" id="replies<%=comm.getCommentID() %>">
        <%  while(itreply.hasNext()){
                Comment reply = new Comment();
                reply = (Comment) itreply.next();
        %>            
       
        <div class="comreply" id="reply<%=reply.getCommentID() %>">
            <img src="images/avatar2.png" width="60" height="52" border="0" class="avatar2"/>
            <div class="text"><%=reply.getText() %>
                 <% 
                        //create a thumbnail of the video from url
                         String replyDate = reply.getTimestamp();
                         String replydate = replyDate.split(" ")[0].substring(0, 10);

         %>
            <div class="date"><%=replydate %></div>
        </div>
           
        </div>       
       
     <%}%>
    </div>
        <div class="replybox" id="box<%=comm.getCommentID() %>" >
            <form id="replyto<%=comm.getCommentID() %>" action="/TutFyp/CommentServlet" method="post">
                <fieldset>
                    <div class="field">

                      
                    </div>
                    <div class="field">                                           
                        <textarea name="commenttext" rows="3" cols="40"></textarea>
                    </div>
                    <!-- fix this value to uploadid -->
                     <input type="hidden" name ="commentuploadid" value = "1" />
                    <div>
                        <input type="hidden" name ="commentreplyto" value ="<%=comm.getCommentID() %>" />
                        <input type="hidden" name="option" value=1 />
                        <input type="hidden" name="type" value= "normal" />
                        <div class="submitreply" title ="<%=comm.getCommentID() %>" onclick="document.getElementById('replyto<%=comm.getCommentID() %>').submit()">Send Your Message!</div>
                    </div>
                </fieldset>
            </form> 
        </div>
    
    <% } //end of while comments %>
</div>    
<%-- 
    Document   : login
    Created on : Apr 18, 2012, 4:34:43 PM
    Author     : louise
--%>

<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


    <div style='display:none'>
        <div class="img_modal" id='inline_content' style='padding:10px;'>
            <h2>Login</h2>
            
            <form method="GET" action="/TutFyp/UserServlet" name="loginform" id="userlogin">
            <ul>
                
                <li>
               
                </li>
            
                <li>                    
                    <input style="padding-bottom:5px" value="" id="username" name="user[name]"
                    placeholder="username" size="30" type="text" required>
                </li>
                <li>
                    <input value="" id="password" type="password" name="user[password]" placeholder="password"
                    size="30" type="text" required>
                </li>
                <li class="button">
                    <input type="hidden" name="option" value=1 />
                    <input class="button_save" name="commit" value="Sign In" type="submit">
                   
                </li>
                
            </ul>
                
           </form>
        </div>
     </div>

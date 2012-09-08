<%-- 
    Document   : register
    Created on : Apr 18, 2012, 4:37:47 PM
    Author     : louise
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div style='display:none'>
    <div id='register' class="img_modal" style='padding:10px;'>
        <h2>Register</h2>
        <form method="GET" action="/TutFyp/UserServlet" name="registerform" id="userRegister">
        <ul>
            <li>
                <input value="" id="username" name="user[username]" placeholder="username"
                size="30" type="text" required>
            </li>
            <li>
                <input value="" id="password" type="password" name="user[password]" placeholder="password"
                size="30" type="text" required>
            </li>
            <li>
                <input value="" id="email" name="user[email]" placeholder="email"
                size="30" type="text" required>
            </li>
            
            <li><input id="teacher" name="teacher" type="checkbox" value="teacher" >Teacher</li>
            <input type="hidden" name="option" value="2" />
            <input class="button_save" name="commit" value="Register" type="submit">
        </ul>
        </form> 
    </div>
</div>

<%-- 
    Document   : category
    Created on : Apr 18, 2012, 5:13:02 PM
    Author     : louise
--%>

<%@page import="java.util.Iterator"%>
<%@page import="entity.Lesson"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
    
    <head>
        <title>Category - Category Page | Tut-Tutoring</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/style.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/colorbox.css" />
        <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="js/cufon-yui.js"></script>
        <script type="text/javascript" src="js/Humanst521_BT_400.font.js"></script>
        <script type="text/javascript" src="js/Humanst521_Lt_BT_400.font.js"></script>
        <script type="text/javascript" src="js/cufon-replace.js"></script>
        <script type="text/javascript" src="js/roundabout.js"></script>
        <script type="text/javascript" src="js/roundabout_shapes.js"></script>
        <script type="text/javascript" src="js/gallery_init.js"></script>
        <script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
        <script type="text/javascript" src="js/jquery.colorbox.js"></script>
        <script type="text/javascript" src="js/popupbox.js"></script>
        
        <script>
           $('#beginner_div').live('click', function(){
               $.ajax({
                   url:'${pageContext.request.contextPath}/includes/beginnerlesson.jsp',
                   success:function(data){
                       $('#categorylist').replaceWith(data);
                   }
               });
           }) ;
            
        </script>
        <link rel="icon" type="image/png" href="images/logo.png" />
        <script>
           $('#intermediate_div').live('click', function(){
               $.ajax({
                   url:'${pageContext.request.contextPath}/includes/intermediatelesson.jsp',
                   success:function(data){
                       $('#categorylist').replaceWith(data);
                   }
               });
           }) ;
            
        </script>
        
        <script>
           $('#advanced_div').live('click', function(){
               $.ajax({
                   url:'${pageContext.request.contextPath}/includes/advancedlesson.jsp',
                   success:function(data){
                       $('#categorylist').replaceWith(data);
                   }
               });
           }) ;
            
        </script>

        
        <!--[if lt IE 7]>
            <link rel="stylesheet" href="css/ie/ie6.css" type="text/css" media="all">
        <![endif]-->
        <!--[if lt IE 9]>
            <script type="text/javascript" src="js/html5.js"></script>
            <script type="text/javascript" src="js/IE9.js"></script>
        <![endif]-->
        
    </head>
    
    <body>
        <!-- header -->               
        <%@include file="includes/header.jsp" %> 
        <!-- #gallery -->
        
        <section id="gallery">
            <div class="container">
                <ul id="myRoundabout">
                    <li>
                        <img src="images/demo.png" alt="">
                    </li>
                </ul>
            </div>
        </section>
        <!-- /#gallery -->
        <div class="main-box">
            <div class="container">
                <div class="inside">
                    <div class="wrapper">
                        <!-- aside -->
                        <aside>
                            <h2>Choose
                                <span>Category</span>
                            </h2>
                            <div class="clear"></div>
                            <ul class="container_aside">
                                <li class="menu">
                                    <ul>
                                        <li class="button">
                                            <%// ${pageContext.request.contextPath}/LessonServlet?option=5&category=beginner"%>
                                            <a href="#" class="blue" id="beginner_div" >Beginners <span></span></a>
                                        </li>
                                    </ul>
                                </li>
                                <li class="menu">
                                    <ul>
                                        <li class="button">
                                            <a href="#" class="red" id="intermediate_div" >Intermediate <span></span></a>
                                        </li>
                                    </ul>
                                </li>
                                <li class="menu">
                                    <ul>
                                        <li class="button">
                                            <a href="#" class="green" id="advanced_div">Advanced <span></span></a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </aside>
                        <!-- content -->
                        <section id="content">
                            <article>
                                <h2>Select
                                    <span>Your Lesson</span>
                                </h2>
                                <!-- .cat-list -->
                                <div id="categorylist">
                                    
                                    
                                    <br/>
                                    <br/>
                                    <br/>
                                    <p></p>
                                   <h2>" Please select a category "</h2>
                                </div>
                            </article>
                        </section>
                    </div>
                </div>
            </div>
        </div>
        <!-- This contains the hidden content for inline calls -->
        
        <!-- footer -->
        <%@include file="includes/footer.jsp" %>
        
        <script type="text/javascript">
            Cufon.now();
        </script>
    </body>

</html>

<%-- 
    Document   : index
    Created on : Apr 18, 2012, 4:18:03 PM
    Author     : louise
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
    
    <head>
        <title>Home - Home Page | Tut-Tutoring</title>
        <meta charset="utf-8">
        <link rel="icon" type="image/png" href="images/logo.png" />
        <link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/style.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/colorbox.css" />
        <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="js/cufon-yui.js"></script>
        <script type="text/javascript" src="js/Humanst521_BT_400.font.js"></script>
        <script type="text/javascript" src="js/Humanst521_Lt_BT_400.font.js"></script>
        <script type="text/javascript" src="js/roundabout.js"></script>
        <script type="text/javascript" src="js/roundabout_shapes.js"></script>
        <script type="text/javascript" src="js/gallery_init.js"></script>
        <script type="text/javascript" src="js/cufon-replace.js"></script>
        <script type="text/javascript" src="js/jquery.colorbox.js"></script>
        <script type="text/javascript" src="js/popupbox.js"></script>
            
     
        <!--[if lt IE 7]>
            <link rel="stylesheet" href="css/ie/ie6.css" type="text/css" media="all">
        <![endif]-->
        <!--[if lt IE 9]>
            <script type="text/javascript" src="js/html5.js"></script>
            <script type="text/javascript" src="js/IE9.js"></script>
        <![endif]-->
    </head>
    
    <body>
        
        <!-- login -->                        
        <%@include file="includes/login.jsp" %>
        
        <!-- register -->                
        <%@include file="includes/register.jsp" %>
        
        <!-- header -->        
        <%@include file="includes/header.jsp" %> 
        
        <!-- edit details -->                
        <%@include file="includes/editdetails.jsp" %>
        
        <!-- #gallery -->        
        <%@include file="includes/gallerysection.jsp" %>
        
            
        <div class="main-box">
            <div class="container">
                <div class="inside">
                    <div class="wrapper">
                        <!-- aside -->
                        <aside>
                            <h2>Top
                                <span>Lessons</span>
                            </h2>
                            <!-- .news -->
                            <%@include file="includes/news.jsp" %>
                        
                        <!-- /aside --> 
                        </aside>
                        <!-- content -->
                        <section id="content">
                            <article>
                                <h2>Welcome to
                                    <span>Tut-Tutoring</span>
                                </h2>
                                <p>Tut-tutoring is a platform developed to aid the learning of the tin-whistle.</p>
                                <p>It caters for all skill levels. From those who have never played the instrument before, to thoes who would like
                                    to challenge themselves in a non stressful environment.
                                   point</p>
                                
                                <p>This website template has several features which will encourage the learning
                                    process</p>This website video </p>
                            </article>
                        </section>
                        
                    </div>
                </div>
            </div>
         </div>
     
        <!-- footer -->
        <%@include file="includes/footer.jsp" %>
        
        <script type="text/javascript">
            Cufon.now();
        </script>
    </body>

</html>

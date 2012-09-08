
<%@ page language="java" import="javazoom.upload.*,java.util.*" %>
<%@ page errorPage="ExceptionHandler.jsp" %>
<!--${pageContext.request.contextPath}-->
<jsp:useBean id="upBean" scope="page" class="javazoom.upload.UploadBean" >
<jsp:setProperty name="upBean" property="folderstore" value="C:\Users\louise\Documents\NetBeansProjects\TutFyp\web\uploadedFiles" />
</jsp:useBean>

<%@page import = "entity.Lesson"%>


<%@page errorPage= "ExceptionHandler.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Lesson - Lesson Page | Tut-Tutoring</title>
        <meta charset="utf-8">
        <link rel="icon" type="image/png" href="images/logo.png" />
        <link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/style.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/colorbox.css" />
        <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="js/cufon-yui.js"></script>
        <script type="text/javascript" src="js/Humanst521_BT_400.font.js"></script>
        <script type="text/javascript" src="js/Humanst521_Lt_BT_400.font.js"></script>
        <script type="text/javascript" src="js/cufon-replace.js"></script>
        <script type="text/javascript" src="js/roundabout.js"></script>
        <script type="text/javascript" src="js/roundabout_shapes.js"></script>
        <script type="text/javascript" src="js/gallery_init.js"></script>
        <script type="text/javascript" src="js/loopedslider.min.js"></script>
        <script type="text/javascript" src="js/jquery.colorbox.js"></script>

        <link rel="icon" type="image/png" href="images/logo.png" />
        <link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/style.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/colorbox.css" />
        <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="js/cufon-yui.js"></script>
        <script type="text/javascript" src="js/Humanst521_BT_400.font.js"></script>
        <script type="text/javascript" src="js/Humanst521_Lt_BT_400.font.js"></script>
        <script type="text/javascript" src="js/cufon-replace.js"></script>
        <script type="text/javascript" src="js/roundabout.js"></script>
        <script type="text/javascript" src="js/roundabout_shapes.js"></script>
        <script type="text/javascript" src="js/gallery_init.js"></script>
        <script type="text/javascript" src="js/loopedslider.min.js"></script>
        <script type="text/javascript" src="js/jquery.colorbox.js"></script>
        <!-- Set the notes for the lesson -->
        <script type="text/javascript" src="js/setnotes.js"></script>

        <!-- i was missing this link -->
        <link rel="stylesheet" href="prettyPhoto.css">

        <script src="js/jquery.prettyPhoto.js"></script>
        <script type="text/javascript">
        $(function () {
            // Option set as a global variable
            $('#loopedSlider').loopedSlider({
                container: ".wrap",
                containerClick: false
            });
        });
        </script>
        <script>
        $(document).ready(function () {

            $(".inline").colorbox({
                inline: true,
                width: "50%"
            });
            $(".callbacks").colorbox({
                onOpen: function () {
                    alert('onOpen: colorbox is about to open');
                },
                onLoad: function () {
                    alert('onLoad: colorbox has started to load the targeted content');
                },
                onComplete: function () {
                    alert('onComplete: colorbox has displayed the loaded content');
                },
                onCleanup: function () {
                    alert('onCleanup: colorbox has begun the close process');
                },
                onClosed: function () {
                    alert('onClosed: colorbox has completely closed');
                }
            });

            //Example of preserving a JavaScript event for inline calls.
            $("#click").click(function () {
                $('#click').css({
                    "background-color": "#f00",
                    "color": "#fff",
                    "cursor": "inherit"
                    }).text("Open this window again and this message will still be here.");
                return false;
                });
            });
            </script>
        <!--[if lt IE 7]>
        <link rel="stylesheet" href="css/ie/ie6.css" type="text/css" media="all">
        <![endif]-->
        <!--[if lt IE 9]>
        <script type="text/javascript" src="js/html5.js"></script>
        <script type="text/javascript" src="js/IE9.js"></script>
        <![endif]-->

        <script type="text/javascript">

            var W3CDOM = (document.createElement && document.getElementsByTagName);
            function init() {
                if (!W3CDOM) return;
                    var fakeFileUpload = document.createElement('div');
                    fakeFileUpload.className = 'fakefile';
                    fakeFileUpload.appendChild(document.createElement('input'));
                    var image = document.createElement('img');
                    image.src='images/UploadFile.png';
                    fakeFileUpload.appendChild(image);
                    var x = document.getElementsByTagName('input');
                    for (var i=0;i<x.length;i++) {
                        if (x[i].type != 'file') continue;
                        if (x[i].getAttribute('noscript')) continue;
                        if (x[i].parentNode.className != 'fileinputs') continue;
                        x[i].className = 'file hidden';
                        var clone = fakeFileUpload.cloneNode(true);
                        x[i].parentNode.appendChild(clone);
                        x[i].relatedElement = clone.getElementsByTagName('input')[0];
                        if (x[i].value)
                            x[i].onchange();
                            x[i].onchange = x[i].onmouseout = function () {
                            this.relatedElement.value = this.value;
                        }
                    }
                }
    // -->
         </script>
    </head>

    <body>
    <!-- header -->
    <!-- login -->
    <%@include file="includes/login.jsp" %>

    <!-- register -->
    <%@include file="includes/register.jsp" %>

    <!-- header -->
    <%@include file="includes/header.jsp" %>

    <!-- edit details -->
    <%@include file="includes/editdetails.jsp" %>
    <%
    Lesson lesson =new Lesson();
    //Integer.parseInt(session.getAttribute("type").toString());
    lesson = (Lesson)session.getAttribute("lessonsbyid");
    
    User usertest = new User();
    Integer test = (Integer)session.getAttribute("userid");



    %>
    <!-- #gallery -->
    <section id="gallery">
        <div class="container">
            <div class="intro_left">
                <h2>Title:<span><%=lesson.getTitle() %></span></h2>

                <ul class="lightbox gallery">
                    <li><a href="<%=lesson.getVideoloc() %>" rel="prettyPhoto" title="<%=lesson.getTitle() %>">
                    <%
                    //create a thumbnail of the video from url
                    String sampleUrl = lesson.getVideoloc();
                    String video_id = sampleUrl.split("v=")[1].substring(0, 11);

                    %>
                    <img src="http://img.youtube.com/vi/<%=video_id%>/2.jpg" width="120" height="120" alt="" />
                    </a></li>

                </ul>

                <script type="text/javascript">
                    (function() {

                         //prettyphoto lightbox script
                        $("area[rel^='prettyPhoto']").prettyPhoto();

                        $(".lightbox a[rel^='prettyPhoto']").prettyPhoto({
                            animation_speed:'normal',
                            theme:'light_square',
                            slideshow:3000,
                            autoplay_slideshow: false
                        });

                        //accordion script
                        $('#accordion h3').click(function() {
                            $(this).next().toggle('slow');
                            return false;
                            }).next().hide();

                    })();
                </script>
                    <!-- <img src="images/no_video.png" alt="">-->
                    <p><%=lesson.getDescription() %></p>
                </div>
            </div>
        </section>
        <!-- /#gallery -->
        <div class="main-box">
            <div class="container">
                <div class="inside">
                    <div class="wrapper">
            <!-- aside -->
                        <aside>
                            <h2>Top<span>Uploads</span></h2>
                            <!-- loopedSlider begin -->
                            <div id="loopedSlider">
                                <div class="wrap">
                                    <div class="slides">
                                        <div>
                                            <a href="#"><img src="images/thumb1.png" alt=""></a>
                                        </div>
                                        <div>
                                            <a href="#"><img src="images/thumb1.png" alt=""></a>
                                        </div>
                                        <div>
                                            <a href="#"><img src="images/thumb1.png" alt=""></a>
                                        </div>
                                        <div>
                                            <a href="#"><img src="images/thumb1.png" alt=""></a>
                                        </div>
                                        <div>
                                            <a href="#"><img src="images/thumb1.png" alt=""></a>
                                        </div>
                                    </div>
                                </div>
                                <ul class="nav-controls">
                                    <li>
                                        <a href="#" class="previous">Previous Page</a>
                                    </li>
                                    <li>
                                        <a href="#" class="next">Next Page</a>
                                    </li>
                                </ul>
                            </div>
                    <!-- loopedSlider end -->
                            <br/>
                            <br/>


                            <h2>Upload<span>mp3</span></h2>
                           
                             <%    Lesson lessonupload = new Lesson();

                                if (MultipartFormDataRequest.isMultipartFormData(request))
                                {
                                    // Uses MultipartFormDataRequest to parse the HTTP request.
                                    MultipartFormDataRequest mrequest = new MultipartFormDataRequest(request);
                                    String todo = null;
                                    if (mrequest != null) 
                                        todo = mrequest.getParameter("todo");
                                   
                                    if ( (todo != null) && (todo.equalsIgnoreCase("upload")) )
                                            {
                                                Hashtable files = mrequest.getFiles();
                                                if ( (files != null) && (!files.isEmpty()) )
                                                    {
                                                        UploadFile file = (UploadFile) files.get("uploadfile");
                                                        if (file != null) out.println("<li>Sucessful Upload : "+file.getFileName() + "<BR> Content Type : "+file.getContentType());
                                                            String fileloc = file.getFileName();
                                                            lesson.setMp3Loc(lesson.getLessonID(), test, fileloc);
                                                            //("+file.getFileSize()+" bytes)"
                                                            // Uses the bean now to store specified by jsp:setProperty at the top.
                                                            upBean.store(mrequest, "uploadfile");
                                                      }
                                                 else
                                                    {
                                                        out.println("<li>No uploaded files");
                                                    }
                                           }
                                                else out.println("<BR> todo="+todo);
                                  }
                                 %>


                                <ul>

                                <form method="post" action="lesson.jsp?lessonid=<%=lessonupload.getLessonID()%>" name="upform" enctype="multipart/form-data">
                                    <li>

                                        <!-- <div class="fileinputs">-->
                                        <input value="" type="file" id="upload" name="uploadfile" placeholder="upload"
                                        size="20" type="text" required>
                                        <!-- <div class="fakefile">-->

                                        <!-- <img style="border: none;" src="images/UploadFile.png" alt="upload file">-->
                                        <!-- </div>-->
                                        <!-- </div>-->

                                    </li>
                                    <li>
                                        <input type="hidden" name="todo" value="upload">

                                        <button class="button_save" type="submit" name="Submit" value="Save">
                                            <strong>Save</strong>
                                        </button>
                                        <button class="button_save" type="reset" name="Reset" value="Cancel">
                                            <strong>Cancel</strong>
                                        </button>
                                    </li>
                                </form>
                                </ul>

                            </aside>
                            <!-- content -->
                
                                        <section id="content">
                                <article>
                                    <h2>Try<span>Lesson</span></h2>
                                    <div class="songwrapper">

                                    <%
                                        //dynamically create a table with 6 rows and 16 col
                                        int numVisCols = 16;
                                        int divopened = 0;
                                        String classname = "";
                                        System.out.print("lesson.getSong() is ");
                                        lesson.print();
                                        String gameNotes[] = lesson.getSong().split(",");
                                        System.out.println("*********"+gameNotes.length);
                                        int numRow= 6;
                                        int numCol = gameNotes.length;
                                         for(int j = 0; j<numCol; j++){
                                            if ( j % numVisCols == 0 ) {
                                                
                                                if ( divopened < j ) {
                                                   %> </div> <%
                                                }
                                                divopened = j;
                                                if ( j != 0 ) {
                                                    %><div class="lesson hidden" ><%
                                                } else {
                                                    %><div class="lesson" ><%
                                                }
                                            }
                                            %><ul name ="changeNote" class="note"><%
                                               for(int i=0; i<numRow;i++){
                                                   // build the notes
                                                   //int note = Integer.parseInt(gameNote[3]);
                                                switch ( Integer.parseInt(gameNotes[ j ]))
                                                {
                                                    case 1:
                                                        if ( i < 2 )
                                                            classname = "circle";
                                                        else
                                                            classname = "circle_blank";
                                                            break;
                                                    case 2:
                                                        if ( i < 1 )
                                                            classname = "circle";
                                                        else
                                                            classname = "circle_blank";
                                                            break;
                                                    case 3:
                                                        // No notes
                                                            classname = "circle_blank";
                                                            break;
                                                    case 4:
                                                        if ( i > 0 )
                                                            classname = "circle";
                                                        else
                                                            classname = "circle_blank";
                                                            break;
                                                    case 5:
                                                        classname = "circle";
                                                        break;
                                                    case 6:
                                                        if ( i < 5 )
                                                         classname = "circle";
                                                        else
                                                            classname = "circle_blank";
                                                            break;
                                                    case 7:
                                                        if ( i < 4 )
                                                            classname = "circle";
                                                        else
                                                        classname = "circle_blank";
                                                        break;
                                                    case 8:
                                                        if ( i < 3 )
                                                          classname = "circle";
                                                         else
                                                          classname = "circle_blank";
                                                            break;
                                                    default:
                                                        classname = "blank";
                                                        break;
                                                    }


                                    %>
                                    <li
                                        class="<%=classname%>"></li><%
                                        }

                                        %> </ul><%
                                        
                                    }

                                    %>
                                    </div>
                                </article>
                                <div class="controls_bar">
                                    <div id="editor-controls">
                                        <button class="button_save">
                                            <strong>play</strong>
                                        </button>
                                        <button class="button_save">
                                            <strong>replay</strong>
                                        </button>
                                        <button class="button_save">
                                            <strong>scroll</strong>
                                        </button>
                                    </div>
                                <div id="opionion-controls">
                                    <button class="button_save">
                                        <strong>Completed</strong>
                                    </button>
                                    <% %>
                                   <form method="GET" action="/TutFyp/LessonServlet" name="likeform" >
                                    <span class="tooltip-bottom" title="Like">
                                        <input type="image" src="images/upvote.png" name="image" width="20" height="20">
                                       
                                        <input type="hidden" name="option" value=8 />
                                        <input type="hidden" name="userid" value="<%=test%>" />
                                        <input type="hidden" name="lessonid" value="<%=lesson.getLessonID() %>" />                                   
                                    </span>
                                     </form>
                                   <form method="GET" action="/TutFyp/LessonServlet" name="dislikeform" >
                                    <span class="tooltip-bottom" title="Dislike" >
                                        <input type="image" src="images/downvote.png" name="image" width="20" height="20">
                                       
                                        <input type="hidden" name="option" value=9 />
                                        <input type="hidden" name="userid" value="<%=test%>" />
                                        <input type="hidden" name="lessonid" value="<%=lesson.getLessonID() %>" />   
                                      
                                    </span>
                                   </form>
                                </div>
                            </div>
                        </section>
                      </div>
                    </div>
                </div>
            </div>
            <!-- This contains the hidden content for inline calls -->
            <div style='display:none' >
                <div class="img_modal" id='inline_content' style='padding:10px;' >
                    <h2>Edit Details </h2>
                     <ul>
                         <li>
                             <input style="padding-bottom:5px" value="" id="user_login" name="user[login]"
                             placeholder="username" size="30" type="text" required>
                         </li>
                         <li>
                             <input value="" id="user_email" name="user[email]" placeholder="email"
                             size="30" type="text" required>
                         </li>
                         <li>
                             <input value="" id="user_passowrd" name="user[password]" placeholder="password"
                             size="30" type="text" required>
                         </li>
                         <li class="button">
                            <input type="hidden" name="option" value="edit details" />
                             <input class="button_save" name="commit" value="Save" type="submit">
                        </li>
                    </ul>
                </div>
            </div>
            <!-- footer -->
            <footer>
               <div class="container">
                   <div class="wrapper">
                       <div class="fleft">Copyright - Tut-Tutoring</div>
                       <div class="fright"></div>
                    </div>
                </div>
            </footer>
        <script type="text/javascript">
            Cufon.now();
       </script>
   </body>
</html>



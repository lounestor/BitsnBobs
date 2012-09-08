<%@page import="entity.Lesson"%>
<%@page import="fyp.lou.tut.LessonServlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    
    <head>
        <title>Gallery - Gallery Page | Tut-Tutoring</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/style.css" type="text/css" media="all">
        <link rel="stylesheet" href="css/colorbox.css" />
        <link href="js/skin/pink.flag/jplayer.pink.flag.css" rel="stylesheet"
        type="text/css" />
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
        <script type="text/javascript" src="js/jquery.jplayer.min.js"></script>
        <script type="text/javascript" src="js/jplayer.playlist.min.js"></script>
        
        <script>
            $(document).ready(function () {
                $(".replybox").hide();
                $(".commentreplies").hide();
                // showing replyboxes               

                
                $(".showreply").live('click', function(){
                   // $(".comreply").show('fast'); 
                   // $("#"+this.title).show('fast'); 
                    if ($('#replies'+this.title).is(':hidden')) {
                        $('#replies'+this.title).show();
                        $("#box"+this.title).show('fast');
                    } else {
                    $('#replies'+this.title).hide();
                    $("#box"+this.title).hide();
                    }
                   
                });
               
                
              /*  $("div.submitreply").live('click', function(){
                  //alert( "clickeed submit reply for #replyto"+this.title);
       
                        //inside the form
                        var text = $('#replyto'+this.title+' [name=commenttext]').val();
                        var uploadid = $('#replyto'+this.title+' [name=commentuploadid]').val();
                        var reply = $('#replyto'+this.title+' [name=commentreplyto]').val();
                        if (text == "") { 
                            
                        alert ( "A Comment needs text!");}
                        $.ajax({
                        url:'${pageContext.request.contextPath}/TutFyp/CommentServlet',
                        data:{ commenttext: text, commentuploadid: uploadid, commentreplyto:reply,
                               option: '1',
                               type: 'ajax'},
                        success:function(data){
                            $('#showreply').replaceWith(data);
                        }
                        });
                    
                    return false;
                });  */                 
                   
               

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
        
      
        <script type="text/javascript">
            $(function () {
                // Option set as a global variable
                $('#loopedSlider').loopedSlider({
                    container: ".wrap",
                    containerClick: false
                });
            });
        </script>
         <% LessonServlet.loadAllMp3(request, response); 
           Collection collectionupload = new ArrayList<Lesson>();
           collectionupload = (Collection) session.getAttribute("loadAllMp3Uploads");
        %>
        
        
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
                <div class="inside">
                    <div class='menu_gal'>
                         <%
                            Iterator itmp = collectionupload.iterator();
                             while (itmp.hasNext()) {
                                Lesson lessonmp = new Lesson();
                                lessonmp = (Lesson) itmp.next();
                                // session.getAttribute("prodid");                
                                System.out.println(lessonmp.getMp3loc());
                               // System.out.println(lessonmp.getTimestamp());
                            %>
                        <ul>
                            <!-- dropdown view by lesson -->
                            <li>
                              <!-- $//-->
                                <a href="${pageContext.request.contextPath}/LessonServlet?option=12&uploadid=<%=lessonmp.getUploadID() %>"><%=lessonmp.getMp3loc() %></a>
                                <% 
                                   // int mpuploadbyid=(Integer)session.getAttribute("uploadbyid");
                                   }
                                %>
                            </li>                            
                        </ul>
                    </div>
            <%
                Lesson tuploadmp3 = new Lesson();
                String upidmp3 = request.getParameter("uploadid");
                System.out.println("upload id is " + upidmp3);
                String strmp3="none selected";
               
                if(upidmp3!=null){

                int upidmp3_int = Integer.parseInt(upidmp3);
                //testuploadm= (Lesson) session.getAttribute("uploadbyid");

                 tuploadmp3.getByUploadID(upidmp3_int);

                 System.out.println("after id passed to servlet " + tuploadmp3.getMp3loc());
                 strmp3 = tuploadmp3.getMp3loc();
                
                   }

    %>
        
           <script type="text/javascript">
            //<![CDATA[
            $(document).ready(function () {

                $("#jquery_jplayer_1").jPlayer({
                    ready: function (event) {
                        $(this).jPlayer("setMedia", {                           
                          mp3: "${pageContext.request.contextPath}/uploadedFiles/<%=strmp3%>"
                        //  mp3: "http://localhost:8080${pageContext.request.contextPath}/uploadedFiles/Kalimba.mp3"

                        });
                    }, 
                    swfPath: "js/js/js",
                    supplied: "mp3",
                    wmode: "window"
                });
            });
            //]]>
         <%
                //on timeout redireact to index.jsp
               int timeout = session.getMaxInactiveInterval();
                response.setHeader("Refresh", timeout + "; URL = index.jsp");
         %>   
        </script>
                  <div class="content_list_gal">
                      <ul>
                          <div class="jp-type-playlist">
                           <li>
                              <a href='#'>list of users</a>
                               <ul>
                                   <li>
        
                                    </li>
                                   
                                </ul>
                            </li>
                          </div>
                        </ul>
                   </div>
                   
                    <div class="mp3">
                        <div id="jquery_jplayer_1" class="jp-jplayer"></div>
                        <div id="jp_container_1" class="jp-audio">
                            <div class="jp-type-single">
                                <div class="jp-gui jp-interface">
                                    <ul class="jp-controls">
                                        <li>
                                            <a href="javascript:;" class="jp-play" tabindex="1">play</a>
                                        </li>
                                        <li>
                                            <a href="javascript:;" class="jp-pause" tabindex="1">pause</a>
                                        </li>
                                        <li>
                                            <a href="javascript:;" class="jp-stop" tabindex="1">stop</a>
                                        </li>
                                        <li>
                                            <a href="javascript:;" class="jp-mute" tabindex="1" title="mute">mute</a>
                                        </li>
                                        <li>
                                            <a href="javascript:;" class="jp-unmute" tabindex="1" title="unmute">unmute</a>
                                        </li>
                                        <li>
                                            <a href="javascript:;" class="jp-volume-max" tabindex="1" title="max volume">max volume</a>
                                        </li>
                                    </ul>
                                    <div class="jp-progress">
                                        <div class="jp-seek-bar">
                                            <div class="jp-play-bar"></div>
                                        </div>
                                    </div>
                                    <div class="jp-volume-bar">
                                        <div class="jp-volume-bar-value"></div>
                                    </div>
                                    <div class="jp-current-time"></div>
                                    <div class="jp-duration"></div>
                                    <ul class="jp-toggles">
                                        <li>
                                            <a href="javascript:;" class="jp-repeat" tabindex="1" title="repeat">repeat</a>
                                        </li>
                                        <li>
                                            <a href="javascript:;" class="jp-repeat-off" tabindex="1" title="repeat off">repeat off</a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="jp-title">
                                    <ul>
                                        <li>title</li>
                                    </ul>
                                </div>
                                <div class="jp-no-solution">
                                    <span>Update Required</span>To play the media you will need to either update
                                    your browser to a recent version or update your
                                    <a href="http://get.adobe.com/flashplayer/"
                                    target="_blank">Flash plugin</a>.</div>
                            </div>
                        </div>
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
                           
                            <h2>Song
                                <span>Details</span>
                            </h2>
                            
                                        <%@ include file="includes/uploadDetails.jsp" %>;
                              </aside>
                         
                        <!-- content -->
                        <section id="content">
                           
                            <article>
                                <h2>Leave a
                                    <span>Comment</span>
                                </h2>
                                 
                               <form id="contacts-form" action="/TutFyp/CommentServlet" method="post">
                                    <fieldset>
                                        <div class="field">                                       
                                         
                                        </div>
                                        <div class="field">                                           
                                            <textarea name="commenttext" rows="3" cols="75">  </textarea>
                                        </div>
                                        <!-- fix this value to uploadid -->
                                         <input type="hidden" name ="commentuploadid" value = "1" />
                                        <div>
                                            <input type="hidden" name="option" value=1 />
                                            <input type="hidden" name="type" value= "normal" />
                                            <a href="#" onclick="document.getElementById('contacts-form').submit()">Send Your Message!</a>
                                        </div>
                                    </fieldset>
                                </form> 
                                <%@include file="includes/comment.jsp" %>
                                
                                
                            </article>
                        </section>
                    </div>
                </div>
            </div>
        </div>
        </div>
        <!-- This contains the hidden content for inline calls -->
        <div style='display:none'>
            <div class="img_modal" id='inline_content' style='padding:10px;'>
                <h2>Edit Details</h2>
                <ul>
                    <li>
                        <input style="padding-bottom:5px" value="" id="user_login" name="user[login]"
                        placeholder="username" size="30" type="text" required>
                    </li>
                    <li>
                        <input value="" id="user_login" name="user[login]" placeholder="password"
                        size="30" type="text" required>
                    </li>
                    <li class="button">
                        <input type="hidden" name="option" value="login" />
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

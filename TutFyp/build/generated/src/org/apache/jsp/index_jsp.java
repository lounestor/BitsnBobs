package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import entity.User;
import entity.User;
import fyp.lou.tut.LessonServlet;
import java.util.Iterator;
import entity.Lesson;
import java.util.Collection;
import java.util.ArrayList;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  static {
    _jspx_dependants = new java.util.Vector(7);
    _jspx_dependants.add("/includes/login.jsp");
    _jspx_dependants.add("/includes/register.jsp");
    _jspx_dependants.add("/includes/header.jsp");
    _jspx_dependants.add("/includes/editdetails.jsp");
    _jspx_dependants.add("/includes/gallerysection.jsp");
    _jspx_dependants.add("/includes/news.jsp");
    _jspx_dependants.add("/includes/footer.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("<html lang=\"en\">\n");
      out.write("    \n");
      out.write("    <head>\n");
      out.write("        <title>Home - Home Page | Tut-Tutoring</title>\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <link rel=\"icon\" type=\"image/png\" href=\"images/logo.png\" />\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/reset.css\" type=\"text/css\" media=\"all\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\" media=\"all\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/colorbox.css\" />\n");
      out.write("        <script type=\"text/javascript\" src=\"js/jquery-1.4.2.min.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"js/cufon-yui.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"js/Humanst521_BT_400.font.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"js/Humanst521_Lt_BT_400.font.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"js/roundabout.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"js/roundabout_shapes.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"js/gallery_init.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"js/cufon-replace.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"js/jquery.colorbox.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"js/popupbox.js\"></script>\n");
      out.write("            \n");
      out.write("     \n");
      out.write("        <!--[if lt IE 7]>\n");
      out.write("            <link rel=\"stylesheet\" href=\"css/ie/ie6.css\" type=\"text/css\" media=\"all\">\n");
      out.write("        <![endif]-->\n");
      out.write("        <!--[if lt IE 9]>\n");
      out.write("            <script type=\"text/javascript\" src=\"js/html5.js\"></script>\n");
      out.write("            <script type=\"text/javascript\" src=\"js/IE9.js\"></script>\n");
      out.write("        <![endif]-->\n");
      out.write("    </head>\n");
      out.write("    \n");
      out.write("    <body>\n");
      out.write("        \n");
      out.write("        <!-- login -->                        \n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("\n");
      out.write("    <div style='display:none'>\n");
      out.write("        <div class=\"img_modal\" id='inline_content' style='padding:10px;'>\n");
      out.write("            <h2>Login</h2>\n");
      out.write("            \n");
      out.write("            <form method=\"GET\" action=\"/TutFyp/UserServlet\" name=\"loginform\" id=\"userlogin\">\n");
      out.write("            <ul>\n");
      out.write("                \n");
      out.write("                <li>\n");
      out.write("               \n");
      out.write("                </li>\n");
      out.write("            \n");
      out.write("                <li>                    \n");
      out.write("                    <input style=\"padding-bottom:5px\" value=\"\" id=\"username\" name=\"user[name]\"\n");
      out.write("                    placeholder=\"username\" size=\"30\" type=\"text\" required>\n");
      out.write("                </li>\n");
      out.write("                <li>\n");
      out.write("                    <input value=\"\" id=\"password\" type=\"password\" name=\"user[password]\" placeholder=\"password\"\n");
      out.write("                    size=\"30\" type=\"text\" required>\n");
      out.write("                </li>\n");
      out.write("                <li class=\"button\">\n");
      out.write("                    <input type=\"hidden\" name=\"option\" value=1 />\n");
      out.write("                    <input class=\"button_save\" name=\"commit\" value=\"Sign In\" type=\"submit\">\n");
      out.write("                   \n");
      out.write("                </li>\n");
      out.write("                \n");
      out.write("            </ul>\n");
      out.write("                \n");
      out.write("           </form>\n");
      out.write("        </div>\n");
      out.write("     </div>\n");
      out.write("\n");
      out.write("        \n");
      out.write("        <!-- register -->                \n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<div style='display:none'>\n");
      out.write("    <div id='register' class=\"img_modal\" style='padding:10px;'>\n");
      out.write("        <h2>Register</h2>\n");
      out.write("        <form method=\"GET\" action=\"/TutFyp/UserServlet\" name=\"registerform\" id=\"userRegister\">\n");
      out.write("        <ul>\n");
      out.write("            <li>\n");
      out.write("                <input value=\"\" id=\"username\" name=\"user[username]\" placeholder=\"username\"\n");
      out.write("                size=\"30\" type=\"text\" required>\n");
      out.write("            </li>\n");
      out.write("            <li>\n");
      out.write("                <input value=\"\" id=\"password\" type=\"password\" name=\"user[password]\" placeholder=\"password\"\n");
      out.write("                size=\"30\" type=\"text\" required>\n");
      out.write("            </li>\n");
      out.write("            <li>\n");
      out.write("                <input value=\"\" id=\"email\" name=\"user[email]\" placeholder=\"email\"\n");
      out.write("                size=\"30\" type=\"text\" required>\n");
      out.write("            </li>\n");
      out.write("            \n");
      out.write("            <li><input id=\"teacher\" name=\"teacher\" type=\"checkbox\" value=\"teacher\" >Teacher</li>\n");
      out.write("            <input type=\"hidden\" name=\"option\" value=\"2\" />\n");
      out.write("            <input class=\"button_save\" name=\"commit\" value=\"Register\" type=\"submit\">\n");
      out.write("        </ul>\n");
      out.write("        </form> \n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("        \n");
      out.write("        <!-- header -->        \n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");

    int userLogin = 0;
    String currentUser = "";
    System.out.println("session object before ........"+currentUser);
    if ( session != null ) {
    
        currentUser = (String) session.getAttribute("UserName");
        System.out.println("session object after ........"+currentUser);
        if(currentUser!=null){
            userLogin = 1;
               }
        
 
    }

        if(userLogin==0){
            String url = (request.getRequestURL()).toString();
            if(url.indexOf("index.jsp")== -1){
                response.sendRedirect("index.jsp");
                
            }
        }

      out.write("\n");
      out.write("\n");
      out.write("<header>\n");
      out.write("    \n");
      out.write("    <div class=\"container\">\n");
      out.write("        <h1>\n");
      out.write("            <a href=\"index.jsp\">Tut-Tutoring</a>\n");
      out.write("        </h1>\n");
      out.write("        <nav>\n");
      out.write("            <ul>\n");
      out.write("               \n");
      out.write("                ");
                   
                   //Only show these links if the user is signed in
                   // 0 is not a user
                   if(userLogin!=0){                            
                 
      out.write("\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"index.jsp\" onclick=\"this.className='current'\" >Home</a>\n");
      out.write("                </li>\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"category.jsp\" onclick=\"this.className='current'\" >Category</a>\n");
      out.write("                </li>\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"gallery.jsp\" onclick=\"this.className='current'\" >Gallery</a>\n");
      out.write("                </li>\n");
      out.write("                    <li>\n");
      out.write("                    <a class='inline' href=\"#edit_details\" >Edit Details</a>\n");
      out.write("                </li>\n");
      out.write("                \n");
      out.write("                    <li>\n");
      out.write("                    <a href=\"UserServlet?option=4\" >Logout</a>\n");
      out.write("                </li>\n");
      out.write("                ");
 }  else { 
      out.write("\n");
      out.write("                <li>\n");
      out.write("                    <a class='inline' href=\"#inline_content\" >Login</a>\n");
      out.write("                </li>\n");
      out.write("                <li>\n");
      out.write("                    <a class='inline' href=\"#register\" >Register</a>\n");
      out.write("                </li>                \n");
      out.write("                ");
 } 
      out.write("\n");
      out.write("            </ul>\n");
      out.write("        </nav>\n");
      out.write("    </div>\n");
      out.write("</header>\n");
      out.write("\n");
      out.write(" \n");
      out.write("        \n");
      out.write("        <!-- edit details -->                \n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<div style='display:none'>\n");
      out.write("    <div class=\"img_modal\" id='edit_details' style='padding:10px;'>\n");
      out.write("        <h2>Edit Details</h2>\n");
      out.write("        <ul>\n");
      out.write("            <li>\n");
      out.write("                <input style=\"padding-bottom:5px\" value=\"\" id=\"edit_user\" name=\"user[details]\"\n");
      out.write("                placeholder=\"username\" size=\"30\" type=\"text\" required>\n");
      out.write("            </li>\n");
      out.write("            <li>\n");
      out.write("                <input value=\"\" id=\"edit_user\" name=\"user[details]\" placeholder=\"password\"\n");
      out.write("                size=\"30\" type=\"text\" required >\n");
      out.write("            </li>\n");
      out.write("            <li class=\"button\">\n");
      out.write("                <input type=\"hidden\" name=\"option\" value=\"3\" />\n");
      out.write("                <input class=\"button_save\" name=\"commit\" value=\"Save\" type=\"submit\">\n");
      out.write("            </li>\n");
      out.write("        </ul>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("        \n");
      out.write("        <!-- #gallery -->        \n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<section id=\"gallery\">\n");
      out.write("    <div class=\"container\">\n");
      out.write("        <ul id=\"myRoundabout\">\n");
      out.write("            <li>\n");
      out.write("                <img src=\"images/slide3.png\" alt=\"\">\n");
      out.write("            </li>\n");
      out.write("            <li>\n");
      out.write("                <img src=\"images/slide2.png\" alt=\"\">\n");
      out.write("            </li>\n");
      out.write("\t\t\t<li>\n");
      out.write("                <img src=\"images/slide2.png\" alt=\"\">\n");
      out.write("            </li>\t\t\t\n");
      out.write("            <li>\n");
      out.write("                <img src=\"images/slide1.png\" alt=\"\">\n");
      out.write("            </li>\n");
      out.write("            <li>\n");
      out.write("                <img src=\"images/slide4.png\" alt=\"\">\n");
      out.write("            </li>\n");
      out.write("         </ul>\n");
      out.write("     </div>\n");
      out.write("</section>\n");
      out.write("\n");
      out.write("        \n");
      out.write("            \n");
      out.write("        <div class=\"main-box\">\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <div class=\"inside\">\n");
      out.write("                    <div class=\"wrapper\">\n");
      out.write("                        <!-- aside -->\n");
      out.write("                        <aside>\n");
      out.write("                            <h2>Top\n");
      out.write("                                <span>Lessons</span>\n");
      out.write("                            </h2>\n");
      out.write("                            <!-- .news -->\n");
      out.write("                            ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("\n");

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
                                              
                                      

      out.write("\n");
      out.write("\n");
      out.write("<ul class=\"news news");
      out.print((count % 4) );
      out.write("\" >\n");
      out.write("    <li >\n");
      out.write("        \n");
      out.write("        <figure>\n");
      out.write("           \n");
      out.write("            <strong>");
      out.print(lessonList.getLikeTotal() );
      out.write(" </strong>likes\n");
      out.write("        </figure>\n");
      out.write("        <h3>\n");
      out.write("            \n");
      out.write("            <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/TutFyp/includes/login.jsp\">");
      out.print(lessonList.getTitle() );
      out.write("</a>\n");
      out.write("            ");
 
                String s = lessonList.getDescription(); 
                String words[] = s.split(" ");
                String briefdescription = words[0] + "  " + words[1]+ "  " + words[2]+"  " + words[3]
                        +"  " + words[4]+"  " + words[5]+"  " + words[6]+"  " + words[7]
                        +"  " + words[8]+"  " + words[9]+"  " + words[10]+"  " + words[11]+"  " + words[12]; // first two words


             
      out.write("\n");
      out.write("        </h3>");
      out.print(briefdescription );
      out.write("\n");
      out.write("        <a href=\"#\">...</a>\n");
      out.write("        \n");
      out.write("    </li> \n");
      out.write("     ");
}
      out.write(" \n");
      out.write("</ul>\n");
      out.write("\n");
      out.write("                        \n");
      out.write("                        <!-- /aside --> \n");
      out.write("                        </aside>\n");
      out.write("                        <!-- content -->\n");
      out.write("                        <section id=\"content\">\n");
      out.write("                            <article>\n");
      out.write("                                <h2>Welcome to\n");
      out.write("                                    <span>Tut-Tutoring</span>\n");
      out.write("                                </h2>\n");
      out.write("                                <p>Tut-tutoring is a platform developed to aid the learning of the tin-whistle.</p>\n");
      out.write("                                <p>It caters for all skill levels. From those who have never played the instrument before, to thoes who would like\n");
      out.write("                                    to challenge themselves in a non stressful environment.\n");
      out.write("                                   point</p>\n");
      out.write("                                \n");
      out.write("                                <p>This website template has several features which will encourage the learning\n");
      out.write("                                    process</p>This website video </p>\n");
      out.write("                            </article>\n");
      out.write("                        </section>\n");
      out.write("                        \n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("         </div>\n");
      out.write("     \n");
      out.write("        <!-- footer -->\n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<footer>\n");
      out.write("    <div class=\"container\">\n");
      out.write("        <div class=\"wrapper\">\n");
      out.write("            <div class=\"fleft\">Copyright - Tut-Tutoring</div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</footer>\n");
      out.write("\n");
      out.write("        \n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("            Cufon.now();\n");
      out.write("        </script>\n");
      out.write("    </body>\n");
      out.write("\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fyp.lou.tut;

import entity.Lesson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author louise
 */

public class LessonServlet extends HttpServlet {
    
    private HttpSession session;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("**********IN LESSON SERVLET **********");
        session = request.getSession(false);
        String context = request.getContextPath();
        //String test;
        int Option = Integer.parseInt(request.getParameter("option"));
        System.out.println("The returned a value of: " + request.getParameter("option"));
    
        switch(Option){
            case 0: 
                break;
            case 5:                 
               ViewByCategory(request, response, context);
               break;
            case 6:
                ViewByTeacherCat(request, response, context); 
                break;
            case 7:
                ViewByLessonID(request, response, context); 
                break;
            case 8:
                Like(request, response, context);
                break;
            case 9:
                Dislike(request, response, context);
                break;
            case 10:
                ViewMostPopular(request, response, context);
                break;
            case 11:
                ViewAllMp3(request,response,context);
                break;
            case 12:
                ViewByUploadID(request,response,context);
                break;
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="View by Category"> 
    public void ViewByCategory(HttpServletRequest request,
                             HttpServletResponse response,
                             String context) throws IOException, SQLException
  {
            session = request.getSession(true);
            Collection collection = new ArrayList<Lesson>();
            System.out.println("collection has been created");
            String category = request.getParameter("category");
            Lesson.Category lessoncategory = Lesson.Category.valueOf(category);    
            collection = Lesson.getByCategory(lessoncategory);
            System.out.println("in servlet before redirect");
            System.out.println("collection" + collection.size());
            session.setAttribute("lessonsbyCategory", collection);
            System.out.println("louise");
            
            response.sendRedirect(context + "/category.jsp?category="+lessoncategory);
        
    }        
        //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="ViewByTeacherCat"> 
    public void ViewByTeacherCat(HttpServletRequest request,
                             HttpServletResponse response,
                             String context) throws IOException, SQLException
  {
            session = request.getSession(true);
            Collection collection_teachercat = new ArrayList<Lesson>();
            System.out.println("collection has been created");
            String categoryteacher = request.getParameter("category");
            int userid=Integer.parseInt(request.getParameter(""));            
            Lesson.Category lessoncategory_teacher = Lesson.Category.valueOf(categoryteacher);    
            collection_teachercat = Lesson.getByTeacherAndCat(userid, lessoncategory_teacher);
            session.setAttribute("lessonsbyTeacherCategory", collection_teachercat);
            response.sendRedirect(context + "/category.jsp?userid="+userid+"&category='"+lessoncategory_teacher+"'");
   }        
        //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="View by LessonID"> 
    public void ViewByLessonID(HttpServletRequest request,
                             HttpServletResponse response,
                             String context) throws IOException, SQLException
  {
        int lessonid = Integer.parseInt(request.getParameter("lessonid"));   
        System.out.println("***********before view by lesson: lesson id ="+lessonid);
        Lesson lesson_byid = new Lesson();       
        lesson_byid.getByLessonID(lessonid);
        System.out.println("lesson id is "+ lesson_byid.getLessonID());
        session.setAttribute("lessonsbyid", lesson_byid);
        lesson_byid.print();
        response.sendRedirect(context+ "/lesson.jsp?lessonid="+lessonid);
        
            
   }        
        //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Like"> 
    public void Like(HttpServletRequest request,
                             HttpServletResponse response,
                             String context) throws IOException, SQLException
  {       
         Lesson ds = new Lesson();
         int userid=Integer.parseInt(request.getParameter("userid"));
          System.out.println("value of userid" + userid);
         int lessonid=Integer.parseInt(request.getParameter("lessonid"));
          System.out.println("value of lesson id"+ lessonid);
         System.out.println("like before"+ ds.getLikeTotal());
         Lesson.like(userid, lessonid);
         System.out.println("like after"+ ds.getLikeTotal());
         session.setAttribute("like", ds.getLikeTotal());
         response.sendRedirect(context+ "/lesson.jsp");
  }        
        //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Dislike"> 
    public void Dislike(HttpServletRequest request,
                             HttpServletResponse response,
                             String context) throws IOException, SQLException
  {     
        Lesson ds = new Lesson();
        int userid=Integer.parseInt(request.getParameter("userid"));
         System.out.println("value of userid" + userid);
        int lessonid=Integer.parseInt(request.getParameter("lessonid"));
        System.out.println("value of lesson id"+ lessonid);
        System.out.println("dislike before"+ ds.getLikeTotal());
        Lesson.dislike(userid, lessonid); 
          System.out.println("dislike after"+ ds.getLikeTotal());
        session.setAttribute("dislike", ds.getDislikeTotal());
        response.sendRedirect(context+ "/lesson.jsp");
  }        
        //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="View most popular"> 
    public void ViewMostPopular(HttpServletRequest request,
                             HttpServletResponse response,
                             String context) throws IOException, SQLException
  {
    //  Collection collection_rank = new ArrayList<Lesson>();
     // System.out.println("collection has been created");
     // collection_rank = Lesson.getMostPopular(4);
     // System.out.println("collection rank "+collection_rank);
     // session.setAttribute("MostPopular", collection_rank);
     // response.sendRedirect(context + "/index.jsp");
      
  }        
        //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="View most popular"> 
    
    public void ViewAllMp3(HttpServletRequest request,
                             HttpServletResponse response,
                             String context) throws IOException, SQLException
  {
    //    Lesson da= new Lesson();
    //    Collection collection_mp3 = new ArrayList<Lesson>();
    //    System.out.println("collection has been created");
        //Lesson.Opinion view_rank = Lesson.Opinion.like;
    //    collection_mp3 = Lesson.getAllUploads();
     //   session.setAttribute("ViewAllUploads", collection_mp3);
     //   response.sendRedirect(context + "/gallery.jsp");
   }        
        //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="View by uploadID"> 
    public void ViewByUploadID(HttpServletRequest request,
                             HttpServletResponse response,
                             String context) throws IOException, SQLException
  {
        int uploadid = Integer.parseInt(request.getParameter("uploadid"));   
        System.out.println("***********before view by upload: upload id ="+uploadid);
        Lesson upload_byid = new Lesson();       
        upload_byid.getByUploadID(uploadid);
        System.out.println("upload id is "+ upload_byid.getUploadID());
        session.setAttribute("uploadbyid", upload_byid);
        response.sendRedirect(context+ "/gallery.jsp?uploadid="+uploadid);
        upload_byid.print();
            
   }        
        //</editor-fold>
    public static void loadAllMp3(HttpServletRequest request,
                             HttpServletResponse response) throws SQLException
  {
        HttpSession session = request.getSession();
        //Lesson da= new Lesson();
        Collection collection_uploads = new ArrayList<Lesson>();          
        collection_uploads = Lesson.getAllUploads();
        System.out.println("collection has been created for get uploads size= "+collection_uploads.size());      
        session.setAttribute("loadAllMp3Uploads", collection_uploads);
        
        
        
   }  
     
        public static void loadLessonbyLikes(HttpServletRequest request, 
                HttpServletResponse response) throws SQLException            
        
  {
      HttpSession session = request.getSession();
      Collection collection_lessons = new ArrayList<Lesson>();
      System.out.println("collection has been created");
      collection_lessons = Lesson.getMostPopular(5);
      System.out.println("collection rank "+collection_lessons);
      session.setAttribute("MostLikedLesson", collection_lessons);
      
      
      
  }        
        //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LessonServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LessonServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

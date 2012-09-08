/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.lou.tut;

import entity.Comment;
import entity.Lesson;
import entity.User;
import entity.User.Acctype;
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

/**
 *
 * @author louise
 */
public class TestServlet extends HttpServlet {

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
        
        
        
        
        // ************SENARIO FOR STUDENT USER**********//
        
        
        
        /////////////////////////////////////////////
         //  Login user: DEFAULT as STUDENT        //
         ////////////////////////////////////////////
         User login = new User(); 
         login.setUsername("aaaa");
         login.setPassword("testing1");
         int userLogin = login.userLogin();
         if( userLogin!=0 ){
                System.out.println("login sucessfull username = "+ login.getUsername() +" userid = "+userLogin);
         
         }else
             System.out.println("unable to login id returned 0");
         
         /////////////////////////////////////////////
         //  EDIT USER DETAILS                      //
         ////////////////////////////////////////////
         
         //User.editDetails();
         login.setUsername("testEdit");
         login.setEmail("email@edit.com");
         login.setPassword("passwordupdated");
         if(login.editDetails()){
              System.out.println("sucessfull changed username to = "+ login.getUsername() +
                                " changed user email = "+login.getEmail()+" changed password to "+login.getPassword());
         }else
             System.out.println("failed to edit current users details");
    
         
         /////////////////////////////////////////////
         //         REGISTER ACCOUNT              //
         ////////////////////////////////////////////
         User new_User = new User();
         new_User.setUsername("aaaa");
         new_User.setEmail("testing1@email.com");
         new_User.setPassword("testing1");
         //new_User.setAcctype("student");
         User.Acctype acc = User.Acctype.student;
         new_User.setAcctype(acc);
         
         if (new_User.registerUser()) {
            System.out.println("user has been created");
            int newuser_loggedin = new_User.userLogin();
            
            /////////// LOGIN WITH NEW ACCOUNT////////////
           
            if(newuser_loggedin!=0){
                System.out.println("the newly creater user has logged in user id="+new_User.getUserId());
            }else    
                System.out.println("registerd user did not login sucessfully");
         
        }else { System.out.println("error on inserting user");
        
        }
          
        /////////////////////////////////////////
        //     REGISTER WITH TAKEN NAME       //
        ///////////////////////////////////////
         User new_User_taken = new User();
         new_User_taken.setUsername("aaaa");
         new_User_taken.setEmail("testing1@email.com");
         new_User_taken.setPassword("testing1");
         //new_User.setAcctype("student");
         User.Acctype acc2 = User.Acctype.student;
         new_User.setAcctype(acc2); 
        
         if(new_User_taken.validate()){
                System.out.println("username free");
                 
        
             if (new_User.registerUser()) {
                System.out.println("======user has been created again");
                int newuser_loggedin = new_User.userLogin();

                /////////// LOGIN WITH NEW ACCOUNT////////////

                if(newuser_loggedin!=0){
                    System.out.println("the newly creater user has logged in user id="+new_User.getUserId());
                }else    
                    System.out.println("registerd user did not login sucessfully");
                    //**********end login with new account******//
            }else { 
                 System.out.println("error on inserting user");
            }
      }else{
             System.out.println("username already exsists");
         }
         
         //////////////////////////////////////////////////
         //  Register with new account valid user name  //
         /////////////////////////////////////////////////
         
         User userRegister = new User();
         userRegister.setUsername("lulu");
         userRegister.setEmail("lulu@gmail.com");
         userRegister.setPassword("lulupass");
         User.Acctype accstudent = User.Acctype.student;
         userRegister.setAcctype(accstudent);
            
         if(userRegister.validate()){
                System.out.println("username free");
                 
        
             if (userRegister.registerUser()) {
                System.out.println("======user has been created");
                int userlogin = userRegister.userLogin();

                /////////// LOGIN WITH NEW ACCOUNT////////////

                if(userlogin!=0){
                    System.out.println("the newly creater user has logged in user id="+userRegister.getUserId());
                }else    
                    System.out.println("registerd user did not login sucessfully");
                    //**********end login with new account******//
            }else { 
                 System.out.println("error on inserting user");
            }
      }else{
             System.out.println("username already exsists");
         }
         
         
         //////////////////////////////////////////////
        //     View All lessons in Beginner Category//
       //////////////////////////////////////////////
        System.out.println("User wants to view all lessons in beginner category");
        Lesson lesson = new Lesson();
        Collection collection = new ArrayList<Lesson>();
        System.out.println("collection has been created");
        Lesson.Category accbeginner = Lesson.Category.beginner;    
        collection = Lesson.getByCategory(accbeginner);
        
        System.out.println("lesson is "+ collection);
        Iterator it = collection.iterator();
        while(it.hasNext()){
            System.out.println("in iterator");
            lesson = (Lesson)it.next();
            lesson.print();
         } 
         
      /////////////////////////////////////////////////////////
     //     View All lesson By specific teacher in a category//
     /////////////////////////////////////////////////////////
      System.out.println("view all lesson of specific TEACHER in CATEGORY");
         Lesson teacher_cat = new Lesson();
        Collection collection_teachercat = new ArrayList<Lesson>();
        System.out.println("collection has been created");
        Lesson.Category view_accbeginner = Lesson.Category.beginner;
        collection_teachercat = Lesson.getByTeacherAndCat(1, view_accbeginner);
        
        
        System.out.println("size of collection of lessons byin acc begginer with teacherid = "+teacher_cat.getLessonID()+" size = "+ collection_teachercat);
        Iterator it_teachercat = collection.iterator();
        while(it.hasNext()){
            System.out.println("in iterator");
            lesson = (Lesson)it_teachercat.next();
            lesson.print();
         } 
      
      
         
     /////////////////////////////////////////////////////////
     //     Select a lesson to view                        //
     ////////////////////////////////////////////////////////
        
        System.out.println("select lesson based on id of lesson");
        Lesson lesson_byid = new Lesson();       
        lesson_byid.getByLessonID(1);
        System.out.println("lesson id is "+ lesson_byid.getUserID());
        lesson_byid.print();
         
      ////////////////////////////////////////////////////////////
     //    USER DECIDES TO DISLIKE THE LESSON                  //
     /////////////////////////////////////////////////////////// 
        System.out.println(userRegister.getUsername()+" decides to dislike lesson" +lesson_byid.getLessonID());
        Lesson.dislike(57, 1);
        
     ////////////////////////////////////////////////////////////
     //    CHANGES MIND AND DECIDES to LIKE the LESSON        //
     /////////////////////////////////////////////////////////// 
      System.out.println(userRegister.getUsername()+" changes her mind and likes lesson" +lesson_byid.getLessonID());
      Lesson.like(57, 1); 
      
      /////////////////////////////////////////////////////////
      //  LESSONS ARE RANKED BASED ON MOST POPULAR          //
      ////////////////////////////////////////////////////////
      
      //get most popular
      System.out.println("view lessons based on rank");
      Collection collection_rank = new ArrayList<Lesson>();
      System.out.println("collection has been created");
      //Lesson.Opinion view_rank = Lesson.Opinion.like;
      collection_rank = Lesson.getMostPopular();
      System.out.println("size of collection of lessons byin acc begginer with teacherid = "+teacher_cat.getLessonID()+" size = "+ collection_teachercat);
      Iterator it_rank = collection_rank.iterator();
      while(it_rank.hasNext()){
          System.out.println("in iterator");
          lesson = (Lesson)it_rank.next();
          lesson.print();
       } 
      //view likes
      System.out.println("the amount of likes is = "+lesson.getLikeTotal());
      
      
      //*****************COMMENTS SERVLET ******************************
      
      ////////////////////////////////////////////////////////////////////
      //            COMMENT ON UPLOAD                                  //
      ///////////////////////////////////////////////////////////////////
      Comment comment = new Comment();
      comment.setUploadID(1);
      comment.setText("i like this upload");
      comment.setInReplyTo(0);
      comment.insertThisComment();
      
      ////////////////////////////////////////////////////////////////////
      //            REPLY TO COMMENTOR                                 //
      ///////////////////////////////////////////////////////////////////
      
     
            
       comment.setCommentorID(1);
       comment.setUploadID(1);
       comment.setText("well done you sound professional");
       comment.setInReplyTo(1);
       comment.insertThisComment();
      
      ////////////////////////////////////////////////////////////////////
      //            EDIT/UPDATE COMMENT                                       //
      ///////////////////////////////////////////////////////////////////
      
      ////////////////////////////////////////////////////////////////////
      //            DELETE COMMENT                                     //
      ///////////////////////////////////////////////////////////////////
      
      ////////////////////////////////////////////////////////////////////
      //            DELETE COMMENT inREPLY TO                          //
      ///////////////////////////////////////////////////////////////////
             
    }
    
    


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
            Logger.getLogger(TestServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(TestServlet.class.getName()).log(Level.SEVERE, null, ex);
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.lou.tut;


import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author louise
 */
public class UserServlet extends HttpServlet {
    
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
        System.out.println("**********IN USER SERVLET **********");
        session = request.getSession(false);
        String context = request.getContextPath();
        //String test;
        int Option = Integer.parseInt(request.getParameter("option"));
        System.out.println("The returned a value of: " + request.getParameter("option"));
         
        switch(Option){
            case 0: 
                break;
            case 1:                 
               LoginUser(request, response, context);
               break;
            case 2:
                RegisterUser(request, response, context);
               // LoginUser(request, response, context);
                break;
            case 3:
                EditUserDetails(request, response, context);  
                break;
            case 4:
                Logout(request, response, context);
                break;
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Login"> 
    public void LoginUser(HttpServletRequest request,
                             HttpServletResponse response,
                             String context) throws IOException, SQLException, ServletException
  {
            session = request.getSession(true);
            String username= request.getParameter("user[name]");
            String password= request.getParameter("user[password]");
                        
            System.out.println("uname: " + username + "\npwor: " + password);
            User user_login = new User();                
            user_login.setUsername(username);
            user_login.setPassword(password);
            int userLogin = user_login.userLogin();
                
            if( userLogin!=0 ){
                System.out.println("true "+ user_login.getUsername());                    
                user_login.getAcctype();                
                session.setAttribute("UserName", username);
                session.setAttribute("userid", user_login.getUserId());
                //if account type is 1 i will sent to artist
                System.out.println("true "+ user_login.getUsername());                
               
                response.sendRedirect(context + "/category.jsp");
                
                }else{
                   request.setAttribute("message", "Unknown login, please try again");
                   request.getRequestDispatcher("/index.jsp").forward(request, response);
                  
             }
          }        
        //</editor-fold>
   
    // <editor-fold defaultstate="collapsed" desc="Logout"> 
    public void Logout(HttpServletRequest request,
                             HttpServletResponse response,
                             String context) throws IOException, SQLException
    {
        
        session.setAttribute("UserName", "");
        session.invalidate();
        session = null;
        System.out.println("************Logging out");
        response.sendRedirect(context + "/index.jsp");
    }
      //</editor-fold>
  
    // <editor-fold defaultstate="collapsed" desc="Register"> 
    public void RegisterUser(HttpServletRequest request,
                             HttpServletResponse response,
                             String context) throws IOException, SQLException, ServletException
  {
            session = request.getSession(true);
            String username= request.getParameter("user[username]");
            String password= request.getParameter("user[password]");
            request.getParameter("teacher");
            String email = request.getParameter("user[email]");
            
            System.out.println("uname: " + username + "\npwor: " + password +
                    "\n email: "+email+"\n");
            boolean off= false;
            User new_user = new User();
            new_user.setUsername(username);
           // new_user.setAcctype(accType);
            new_user.setEmail(email);
            new_user.setPassword(password);
                
           if(request.getParameter("teacher") == null){
                 System.out.println("account type is student:");
                 User.Acctype accstudent = User.Acctype.student;
                 new_user.setAcctype(accstudent);
                 
             } else {
                 System.out.println("account type is teacher:");
                 User.Acctype accteacher = User.Acctype.teacher;
                 new_user.setAcctype(accteacher);
             } 
             System.out.println("results on insert customer: " );
            
             if(new_user.validate()){
                System.out.println("username is free");   
                 if(new_user.registerUser()){
                   System.out.println("true "+ new_user.getUsername());                    
                   new_user.getAcctype();                
                   session.setAttribute("UserName", username);
                    //if account type is 1 i will sent to artist
                    System.out.println("true "+ new_user.getUsername());
                    response.sendRedirect(context + "/category.jsp");  
                 }             
                                   
                
             }else{
                System.out.println("username already exsists");
                request.setAttribute("message", "Username already exists, please try again");
                request.getRequestDispatcher("includes/register.jsp").forward(request, response);
            }
           
      }        
     //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Edit Details"> 
    public void EditUserDetails(HttpServletRequest request,
                             HttpServletResponse response,
                             String context) throws IOException, SQLException
  {
            //User.editDetails();
         User currentuser = new User();         
         currentuser.setUsername(request.getParameter("username"));
         currentuser.setEmail(request.getParameter("email"));
         currentuser.setPassword(request.getParameter("password"));
         if(currentuser.editDetails()){
              System.out.println("sucessfull changed username to = "+ currentuser.getUsername() +
                                " changed user email = "+currentuser.getEmail()+" changed password to "+currentuser.getPassword());
              response.sendRedirect(context + "/category.jsp");
         }else
             System.out.println("failed to edit current users details");
             
             response.sendRedirect(context + "/error.jsp");
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
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
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

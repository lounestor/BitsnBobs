/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.lou.tut;

import entity.Comment;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class CommentServlet extends HttpServlet {
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
               insertComment(request, response, context);
               break;
                
            case 2:                 
               deleteComment(request, response, context);
               break;
            
        }
    }
    public void insertComment(HttpServletRequest request,
                             HttpServletResponse response,
                             String context) throws IOException, SQLException, ServletException
  {
            session = request.getSession(true);
            //commentor id == userid
            String commentorid = session.getAttribute("userid").toString();
            //int commid = Inter;
            String uploadid = request.getParameter("commentuploadid");
            String text= request.getParameter("commenttext"); 
            String replyto = request.getParameter("commentreplyto");
            
            Comment comm = new Comment();
            
            comm.setCommentorID(Integer.parseInt(commentorid));
            comm.setUploadID(Integer.parseInt(uploadid));
            comm.setText(text);
            comm.setIsDeleted(true);
            if(replyto!=null){
                comm.setInReplyTo(Integer.parseInt(replyto));
            }
            comm.insertThisComment();
            String type = request.getParameter("type");
            if(type.equalsIgnoreCase("normal")){
                response.sendRedirect(context + "/gallery.jsp");
            }
            
  }
    
     public void deleteComment(HttpServletRequest request,
                             HttpServletResponse response,
                             String context) throws IOException, SQLException, ServletException
  {
            session = request.getSession(true);
            //commentor id == userid
            String commentorid = session.getAttribute("userid").toString();
            //int commid = Inter;
            String uploadid = request.getParameter("commentuploadid");
            String text= request.getParameter("commenttext"); 
            String replyto = request.getParameter("commentreplyto");            
            Comment comm = new Comment();
            
            comm.setCommentorID(Integer.parseInt(commentorid));
            comm.setUploadID(Integer.parseInt(uploadid));
            comm.setText(text);
            if(replyto!=null){
                comm.setInReplyTo(Integer.parseInt(replyto));
            }
            comm.deleteThisComment();
            String type = request.getParameter("type");
            if(type.equalsIgnoreCase("normal")){
                response.sendRedirect(context + "/gallery.jsp");
            }
            
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
            Logger.getLogger(CommentServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CommentServlet.class.getName()).log(Level.SEVERE, null, ex);
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DA;

/**
 *
 * @author louise
 */
import entity.Comment;
import java.sql.*;
import entity.Lesson;
import entity.User;
import entity.Lesson.Category;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
public class CommentDA {
    
    Connection c = null;
   //<editor-fold defaultstate="collapsed" desc="Database connection"> 
    private Connection getConnection() {
    String driver= ("com.mysql.jdbc.Driver");
     
     String url = "jdbc:mysql://localhost:3306/fyptut";
     
    //String url = "jdbc:mysql://172.17.1.48:3306/mm4k00112327";

    try {
            // Load database driver if not already loaded.
            Class.forName(driver);

            // Establish network connection to database.
            c = DriverManager.getConnection(url, "root", "");
            
            //c = DriverManager.getConnection(url, "mm4k00112327", "k00112327");
            return c;

        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error loading driver: " + cnfe);
            return null;
        } catch (SQLException sqle) {
            System.err.println("Error connecting: " + sqle);
            return null;
        }
    }
    //</editor-fold>
    
    
    
    //not sure if this syntax is correct 
    //<editor-fold defaultstate="collapsed" desc="view  teachers lessons based category"> 
    
    //this will get all lesson by teach in a specific category 
    
    // i need a method to view comments based on comment id
    
    //<editor-fold defaultstate="collapsed" desc="view comments based on comment id"> 
    public boolean getCommentByID(Comment comment) {       
                       
        if (c == null) {
            c = getConnection();
        }
      //  Collection collection = new ArrayList<Lesson>();
        
        try {
            Statement statement = c.createStatement();
            String query = "SELECT * FROM comment WHERE commentid ="+comment.getCommentID();
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                comment.setCommentID(rs.getInt(1));
                comment.setUploadID(rs.getInt(2));
                comment.setCommentorID(rs.getInt(3));
                comment.setText(rs.getString(4));
                comment.setTimestamp(rs.getString(5));
                comment.setInReplyTo(rs.getInt(6));
                
                return true;
               // collection.add(lesson);
            }
            System.out.println("");

        } catch (Exception e) {
            System.out.println("error" + e);
        }
        return false;

    }
    //</editor-fold>
    
     //<editor-fold defaultstate="collapsed" desc="insert comments"> 
    public boolean insertComment(Comment comment) {      
       
        return insertComment(comment.getUploadID(), comment.getCommentorID(),comment.getText(), comment.getInReplyTo());

    }
    //</editor-fold>    
     
     //<editor-fold defaultstate="collapsed" desc="insert comment "> 
    public boolean insertComment(int uploadid, int commentorid, String text, int inreplyto) {        
                       
        if (c == null) {
            c = getConnection();
        }
      //  Collection collection = new ArrayList<Lesson>();
        
        try {
            Statement statement = c.createStatement();
            String query = "INSERT INTO comment (uploadid,commentorid,text,inReplyTo) VALUES ("+uploadid+","+commentorid+",'"+text+"',"+inreplyto+")";
            System.out.println(query);
            int rows = statement.executeUpdate(query);
            if (rows>0) { 
                return true;
               
               // collection.add(lesson);
            }else{
                return false;
            }

        } catch (Exception e) {
            System.out.println("error" + e);
        }
        return false;

    }
    
    public boolean updateComment(Comment comment){
        return updateComment(comment.getCommentID(), comment.getText());
    }
     //<editor-fold defaultstate="collapsed" desc="update comment "> 
    public boolean updateComment(int commentid, String text) {        
                       
        if (c == null) {
            c = getConnection();
        }
      //  Collection collection = new ArrayList<Lesson>();
        
        try {
            Statement statement = c.createStatement();
            String query = "UPDATE comment SET text = '"+text+"' WHERE commentid ="+commentid;
            System.out.println(query);
            int rows = statement.executeUpdate(query);
            if (rows>0) { 
                return true;
               
            }else{
                return false;
            }

        } catch (Exception e) {
            System.out.println("error" + e);
        }
        return false;

    }
    
    public boolean deleteComment(Comment comment){
        return deleteComment(comment.getCommentID());
    }
     //<editor-fold defaultstate="collapsed" desc="insert comment "> 
    public boolean deleteComment(int commentid) {        
                       
        if (c == null) {
            c = getConnection();
        }
      //  Collection collection = new ArrayList<Lesson>();
        
        try {
            Statement statement = c.createStatement();
           
            //where it is 1 the comment will be treated as deleted
            String query = "UPDATE comment SET isdeleted = 1 WHERE commentid ="+commentid;;
            System.out.println(query);
            int rows = statement.executeUpdate(query);
            if (rows>0) { 
                return true;
               
            }else{
                return false;
            }

        } catch (Exception e) {
            System.out.println("error" + e);
        }
        return false;

    }
    
    
    //get commment 
    //get a collection of comments
    // get all comments on uploadid
    //public Collection<>
    
    
    //get all replies to comment
    public Collection<Comment> getReplies(int commentid){
        
       if (c == null) {
            c = getConnection();
        }
       
        Collection collection = new ArrayList<Comment>();
       try{
           Statement statement = c.createStatement(); 
           String query ="SELECT * FROM comment WHERE inreplyto ="+commentid+" ORDER BY timestamp ASC";
           System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setCommentID(rs.getInt(1));
                comment.setUploadID(rs.getByte(2));
                comment.setCommentorID(rs.getInt(3));
                comment.setText(rs.getString(4));
                comment.setTimestamp(rs.getString(5));
                comment.setInReplyTo(commentid);
                comment.setIsDeleted(rs.getBoolean(7));
               
                collection.add(comment);
            }
           
       }catch (Exception e) {
            System.out.println("error" + e);
        }
           
           return collection;
        
    }
    
    public Collection<Comment> getOriginalCommentsByUpload(int uploadid ){
       
        return getOriginalCommentsByUpload(uploadid, 10, true);
    }
    
    //get all replies to comment
    public Collection<Comment> getOriginalCommentsByUpload(int uploadid, int limit, boolean newerFirst ){
        
       if (c == null) {
            c = getConnection();
        }
       
        Collection collection = new ArrayList<Comment>();
       try{
           Statement statement = c.createStatement(); 
           String query ="SELECT * FROM comment WHERE uploadid ="+uploadid + " AND inreplyto = 0";
           if ( newerFirst ) {
               query = query + " ORDER BY timestamp ASC";
           } else {
               query = query + " ORDER BY timestamp DESC";
           }
           if(limit>0){
              query = query+ " LIMIT "+limit;
           }
           
           System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setCommentID(rs.getInt(1));
                comment.setUploadID(rs.getByte(2));
                comment.setCommentorID(rs.getInt(3));
                comment.setText(rs.getString(4));
                comment.setTimestamp(rs.getString(5));
                comment.setInReplyTo(0);
                comment.setIsDeleted(rs.getBoolean(7));
                
                collection.add(comment);
            }
           
       }catch (Exception e) {
            System.out.println("error" + e);
        }
           
           return collection;
        
    }
    
    
    
    
    
    
}

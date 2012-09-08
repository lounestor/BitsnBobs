/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DA;

/**
 *
 * @author louise
 */
import java.sql.*;
import entity.Lesson;
import entity.User;
import entity.Lesson.Category;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class LessonDA {
    
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
    public Collection<Lesson> getByTeacher (int userid, Lesson.Category category) {
         return getByTeacherAndCat( userid, category.toString() );
     }
     //will get all lessons by teacher
     public Collection<Lesson> getByTeacher (int userid) {
         return getByTeacherAndCat( userid, "*" );
     }
     private Collection<Lesson> getByTeacherAndCat (int userid, String cat) {
        if (c == null) {
            c = getConnection();
        }
        
        Collection collection = new ArrayList<Lesson>();
        try {
            Statement statement = c.createStatement();
            String query = "SELECT l.* FROM lesson l, user u WHERE ";
            if ( !cat.equals("*" ) ) {
                query = query + "l.category ='"+ cat +"' AND ";
            }
            query = query + "u.userid == "+userid+" AND l.userid == u.userid ORDER BY l.category DESC";
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Lesson lessoncat = new Lesson();               
                lessoncat.setLessonID(rs.getInt(1));
                lessoncat.setUserID(rs.getInt(2));                
                lessoncat.setCategory(Lesson.Category.valueOf(rs.getString(3))); 
                lessoncat.setTitle(rs.getString(4));
                lessoncat.setDescription(rs.getString(5));
                lessoncat.setSong(rs.getString(6));
               // lesson.setPicture((rs.getBlob("picture")));
                lessoncat.setVideoloc(rs.getString((8)));
                lessoncat.setMp3loc(rs.getString((9))); 
                lessoncat.setLikeTotal(rs.getInt((10)));
                lessoncat.setDislikeTotal(rs.getInt((11)));
                collection.add(lessoncat);
                                
            }

        } catch (Exception e) {
        }
        return collection;
    }
     //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="view lesson based category:working"> 
     public Collection<Lesson> getByCategory (Lesson.Category category) {
       
        
        if (c == null) {
            c = getConnection();
        }
        
        Collection collection = new ArrayList<Lesson>();
        try {
            Statement statement = c.createStatement();
            String query = "SELECT * FROM lesson WHERE category ='" + category+"'";
            
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Lesson lessoncat = new Lesson();               
                lessoncat.setLessonID(rs.getInt(1));
                lessoncat.setUserID(rs.getInt(2));                
                lessoncat.setCategory(Lesson.Category.valueOf(rs.getString(3))); 
                lessoncat.setTitle(rs.getString(4));
                lessoncat.setDescription(rs.getString(5));
                lessoncat.setSong(rs.getString(6));
               // lesson.setPicture((rs.getBlob("picture")));
                lessoncat.setVideoloc(rs.getString((8)));
                lessoncat.setMp3loc(rs.getString((9))); 
                lessoncat.setLikeTotal(rs.getInt((10)));
                lessoncat.setDislikeTotal(rs.getInt((11)));
                collection.add(lessoncat);
                System.out.println("processing rs" + lessoncat.getCategory());
                                
            }

        } catch (Exception e) {
        }
        return collection;
    }
      //</editor-fold>
     
     
     //<editor-fold defaultstate="collapsed" desc="rank likes: no parameters - revise"> 
    public Collection<Lesson> getByOpinions(Lesson.Opinion opinion ) {
        return getByOpinions( opinion, 0, false );
    }
    public Collection<Lesson> getByOpinions(Lesson.Opinion opinion, int numResults ) {
        return getByOpinions( opinion, numResults, false );
    }
    public Collection<Lesson> getByOpinions(Lesson.Opinion opinion, int numResults, boolean lowtohigh ) {
                               
        if (c == null) {
            c = getConnection();
        }
        Collection collection = new ArrayList<Lesson>();
        
        try {
            Statement statement = c.createStatement();
            String query = "SELECT * FROM lesson ORDER BY "+opinion.toString()+"total";
            if (lowtohigh) {
                query = query + " ASC";
            } else {
                query = query + " DESC";
            }
            if ( numResults > 0 ){
                query = query + " LIMIT "+numResults;
            }
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                
                Lesson lesson = new Lesson();
               
                lesson.setLessonID(rs.getInt(1));
             
                lesson.setUserID(rs.getInt(2));                
                lesson.setCategory(Lesson.Category.valueOf(rs.getString(3))); 
                lesson.setTitle(rs.getString(4));
                lesson.setDescription(rs.getString(5));
                lesson.setSong(rs.getString(6));
               // lesson.setPicture((rs.getBlob("picture")));
                lesson.setVideoloc(rs.getString((8)));
                lesson.setMp3loc(rs.getString((9)));
                lesson.setLikeTotal(rs.getInt((10)));
                lesson.setDislikeTotal(rs.getInt((11)));
                
                collection.add(lesson);
            }
            System.out.println("size of collection in view lesson by lesson id is " + collection.size());

        } catch (Exception e) {
            System.out.println("error" + e);
        }
        return collection;

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="likes/dislike stump"> 
    public boolean opinion(int lessonid, int userid, Lesson.Opinion opinionType){
       
       if (c == null) {
            c = getConnection();
        }
        try {
            Statement statement = c.createStatement();
            String query = "SELECT * FROM opinion WHERE lessonid="+lessonid+" AND userid="+userid+"";
            
           // String query = "INSERT INTO opinion (lessonid,userid,opiniontype) VALUES ("+lessonid+","+userid+","+opinionType+")";   
            System.out.println(query);
            ResultSet rs= statement.executeQuery(query);
            boolean changedopinion= false; 
            if (rs.next()) {
                
                //if it already exsists and the same leave it
                if(rs.getString(3).equals(opinionType.toString())){
                    return true;
                }else {
                    //changed opion true or false
                    changedopinion= true; 
                }     
            }  // enf of rs.next()
            query = "";
            String lessonquery="";
            if(changedopinion){
                
                query="UPDATE opinion SET opiniontype ='"+opinionType+"' WHERE lessonid = "+lessonid+" AND userid ="+userid+"";
                if(opinionType.equals(Lesson.Opinion.like)){
                    lessonquery ="liketotal = liketotal + 1, disliketotal = disliketotal -1";
                }else{
                    lessonquery ="liketotal = liketotal - 1, disliketotal = disliketotal +1";
                }
                
                lessonquery="UPDATE lesson SET "+lessonquery+" WHERE lessonid ="+lessonid;
             
            //opioion hasn't been changed or is new   
            }else{
                //updating total if new opioion like/dislike
                query = "INSERT INTO opinion (userid,lessonid,opiniontype) VALUES ("+userid+","+lessonid+",'"+opinionType+"')";   
                lessonquery ="UPDATE lesson SET "+opinionType+"total = "+opinionType+"total + 1 WHERE lessonid ="+lessonid+"";
            }
             System.out.println(query);
              System.out.println(lessonquery);
            // queries done
            int rows = statement.executeUpdate(query);
            
            if(rows!=0){
                rows = statement.executeUpdate(lessonquery);
            }
            
            return true;
            
        } catch (Exception e) {
            System.out.println("exception" + e);
            return false;
        }

    }
    //</editor-fold>
    
    
    //upload mp3
    //<editor-fold defaultstate="collapsed" desc="upload mp3"> 
    public boolean uploadFile(int lessonid, int userid, String mp3) throws SQLException{
        
                                       
        if (c == null) {
            c = getConnection();
        }
        try {
            Statement statement = c.createStatement();
            String query = "INSERT INTO upload(lessonid,userid,mp3loc) VALUES "
                    + "(" + lessonid + ","+userid+",'"+mp3+"')";
            int row = statement.executeUpdate(query);
            if(row>0){
                return true;
            }
             else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("exception" + e);
            return false;
        }

    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="view uploads"> 
     public Collection<Lesson> getAllUploads () {
       
        
        if (c == null) {
            c = getConnection();
        }
        Lesson lesson = new Lesson();
        Collection collection = new ArrayList<Lesson>();
        try {
            Statement statement = c.createStatement();
            String query = "SELECT * FROM upload";
            
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Lesson mp3upload = new Lesson();
                mp3upload.setUploadID(rs.getInt(1));
                mp3upload.setUserID(rs.getInt(2));
                mp3upload.setLessonID(rs.getInt(3));
                mp3upload.setMp3loc(rs.getString(5));  
                mp3upload.setTimestamp(rs.getString(6));
                mp3upload.setDescripupload(rs.getString(7));
                System.out.println("the time/date is "+ mp3upload.getTimestamp());
                collection.add(mp3upload);
                System.out.println("processing rs");
                                
            }

        } catch (Exception e) {
        }
        return collection;
    }
      //</editor-fold>
    
   //<editor-fold defaultstate="collapsed" desc="view lesson based on lesson id"> 
    public boolean getByLessonID(Lesson lesson) {
        
                       
        if (c == null) {
            c = getConnection();
        }
      //  Collection collection = new ArrayList<Lesson>();
        
        try {
            Statement statement = c.createStatement();
            String query = "SELECT * FROM lesson WHERE lessonid ="+lesson.getLessonID();
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                
                lesson.setLessonID(rs.getInt(1));
                lesson.setUserID(rs.getInt(2));                
                lesson.setCategory(Lesson.Category.valueOf(rs.getString(3))); 
                lesson.setTitle(rs.getString(4));
                lesson.setDescription(rs.getString(5));
                lesson.setSong(rs.getString(6));
               // lesson.setPicture((rs.getBlob("picture")));
                lesson.setVideoloc(rs.getString((8)));
                lesson.setMp3loc(rs.getString((9)));
                lesson.setLikeTotal(rs.getInt((10)));
                lesson.setDislikeTotal(rs.getInt((11)));
                
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
    
 //<editor-fold defaultstate="collapsed" desc="view by upload id"> 
    public boolean getByUploadID(Lesson mp3upload) {
        
                       
        if (c == null) {
            c = getConnection();
        }
      //  Collection collection = new ArrayList<Lesson>();
        
        try {
            Statement statement = c.createStatement();
            String query = "SELECT * FROM upload WHERE uploadid =" + mp3upload.getUploadID();
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                
                mp3upload.setUploadID(rs.getInt(1));
                mp3upload.setUserID(rs.getInt(2)); 
                mp3upload.setLessonID(rs.getInt(3));
                mp3upload.setMp3loc(rs.getString(5));
                mp3upload.setTimestamp(rs.getString(6));
               
                
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
}


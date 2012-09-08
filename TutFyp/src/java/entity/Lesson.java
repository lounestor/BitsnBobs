/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import DA.LessonDA;
import com.mysql.jdbc.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author louise
 */
public class Lesson {
    
    private int lessonID;
    private int uploadID;
    private int userID;
    private String title;
    private String description;
    private String descripupload;
    private String song;
    private Blob picture;
    private String videoloc;
    private String mp3loc;
    private int likeTotal;
    private int dislikeTotal;
    private String timestamp;
    public enum Category{beginner,intermediate,advanced};
    private Category category;
    public enum Opinion{like,dislike};
    private Opinion opinion;

    public String getTimestamp() {
        return timestamp;
    }

    public String getDescripupload() {
        return descripupload;
    }

    public void setDescripupload(String descripupload) {
        this.descripupload = descripupload;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Opinion getOpinion() {
        return opinion;
    }

    public void setOpinion(Opinion opinion) {
        this.opinion = opinion;
    }
    
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
      
        this.category = category;
    }

    public int getUploadID() {
        return uploadID;
    }

    public void setUploadID(int uploadID) {
        this.uploadID = uploadID;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDislikeTotal() {
        return dislikeTotal;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }
    
    public void setDislikeTotal(int dislikeTotal) {
        this.dislikeTotal = dislikeTotal;
    }

    public int getLessonID() {
        return lessonID;
    }

    public void setLessonID(int lessonID) {
        this.lessonID = lessonID;
    }

    public int getLikeTotal() {
        return likeTotal;
    }

    public void setLikeTotal(int likeTotal) {
        this.likeTotal = likeTotal;
    }

    public String getMp3loc() {
        return mp3loc;
    }
    
    

    public void setMp3loc(String mp3loc) {
        this.mp3loc = mp3loc;
    }

    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoloc() {
        return videoloc;
    }

    public void setVideoloc(String videoloc) {
        this.videoloc = videoloc;
    }
    
    //view all lessons by teacher 
     public static Collection<Lesson> getByTeacher (int userid){
        LessonDA id = new LessonDA();   
        //Lesson.Category acc = Lesson.Category.;
        return id.getByTeacher(userid);          
     }
    
    //view all lessons by  teacher and category
     public static Collection<Lesson> getByTeacherAndCat (int userid, Lesson.Category cat){
        LessonDA id = new LessonDA();   
        //Lesson.Category acc = Lesson.Category.;
        return id.getByTeacher(userid, cat);          
     }
     
       
    //view lesson by id
    public boolean getByLessonID(int lessonid){
        
        LessonDA id = new LessonDA();  
        this.lessonID = lessonid;        
        return id.getByLessonID( this );        
    }
    
    //view lesson by category
    public static Collection<Lesson> getByCategory (Lesson.Category cat){
       
        LessonDA id = new LessonDA();   
        //Lesson.Category acc = Lesson.Category.;
        Collection collection = new ArrayList<Lesson>();
        System.out.println("cat" + cat);
        collection = id.getByCategory(cat);
        System.out.println("size of collection in lesson by category" + collection.size());
        return collection;
    }
    
    //opinion like
    public static boolean like(int lessonid, int userid){
        
        LessonDA likeda = new LessonDA();
        return likeda.opinion(lessonid, userid, Opinion.like);
    }
    
    //opinion dislike
    public static boolean dislike(int lessonid, int userid){        
        LessonDA likeda = new LessonDA();
        return likeda.opinion(lessonid, userid, Opinion.dislike);
    }
            
            
    //view by most popular
    public static Collection<Lesson> getMostPopular (){
        
        LessonDA rank = new LessonDA();
        
        return rank.getByOpinions(Opinion.like);
    }
    
    //view by least popular lesson
    public static Collection<Lesson> getLeastPopular (){
       
        LessonDA rank = new LessonDA();
        return rank.getByOpinions(Opinion.dislike);
    }
    
    public static Collection<Lesson> getMostPopular ( int numResults ){
        
        LessonDA rank = new LessonDA();
        return rank.getByOpinions(Opinion.like, numResults);
    }
    
    //view by least popular lesson
    public static Collection<Lesson> getLeastPopular ( int numResults ){
       
        LessonDA rank = new LessonDA();
        return rank.getByOpinions(Opinion.dislike, numResults );
    }
    
    //set mp3 location
    public boolean setMp3Loc(int lessonid, int userid, String mp3) throws SQLException{        
        LessonDA id = new LessonDA();        
        return id.uploadFile(lessonid, userid, mp3);        
    }
    //view all uploaded mp3s
    public static Collection<Lesson> getAllUploads(){
        LessonDA da= new LessonDA();       
        return da.getAllUploads();
    }
    
    //view by mp3 uploadid
    public boolean getByUploadID(int uploadid){        
        LessonDA id = new LessonDA();  
        this.uploadID = uploadid;        
        return id.getByUploadID( this );        
    }
    
    
    
    
    public void print(){
         System.out.println("lesson id:" +  this.getLessonID());
         System.out.println("get creator user id = " +  this.getUserID());
         System.out.println("The category is = " +  this.getCategory());
         System.out.println("The title is = " +  this.getTitle());
         System.out.println("description of the lesson is = " +  this.getDescription());
         System.out.println("Content of song is = " +  this.getSong());         
         System.out.println("the video url is = " +  this.getVideoloc());
         System.out.println("total likes = " +  this.getLikeTotal());
         System.out.println("the video url is = " +  this.getDislikeTotal());
         System.out.println("the mp3s are = " +  this.getMp3loc());
         System.out.println("date time = " +  this.getTimestamp());
         System.out.println("Upload description = " +  this.getDescripupload());
         
        
     }
    
}

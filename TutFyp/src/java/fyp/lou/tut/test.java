/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.lou.tut;

import entity.Comment;
import entity.Lesson;
import entity.Lesson.Category;
import entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author louise
 */
public class test {
    
    public static void main(String []args) throws SQLException{
        Lesson da= new Lesson();       
        
        Collection collection_rank = new ArrayList<Lesson>();
        System.out.println("collection has been created");
        //Lesson.Opinion view_rank = Lesson.Opinion.like;
        collection_rank = Lesson.getAllUploads();
        
        // System.out.println("size of collection of lessons byin acc begginer with teacherid = "+teacher_cat.getLessonID()+" size = "+ collection_teachercat);
         Iterator it_rank = collection_rank.iterator();
        while(it_rank.hasNext()){
          System.out.println("in iterator");
          da = (Lesson)it_rank.next();
          da.print();
       } 
        
        
        
     //   Lesson.getLeastPopular();
       /* Lesson test = new Lesson();
        int lessonid =1;
        int userid=1;
        String something = "mylocation";
        test.setMp3Loc(lessonid,userid, something);
        System.out.println("testing mp3" +test);
        Lesson lesson = new Lesson();
        System.out.println("view lessons based on rank");
        Collection collection_rank = new ArrayList<Lesson>();
        System.out.println("collection has been created");
        //Lesson.Opinion view_rank = Lesson.Opinion.like;
        collection_rank = Lesson.getMostPopular(2);
        // System.out.println("size of collection of lessons byin acc begginer with teacherid = "+teacher_cat.getLessonID()+" size = "+ collection_teachercat);
         Iterator it_rank = collection_rank.iterator();
        while(it_rank.hasNext()){
          System.out.println("in iterator");
          lesson = (Lesson)it_rank.next();
          lesson.print();
       } */
       /* User new_User = new User();
         new_User.setUsername("bbbb");
         new_User.setEmail("testing2@email.com");
         new_User.setPassword("testing2");
         //User.Acctype acc = User.Acctype.student;
         new_User.setAcctype(User.Acctype.student);
         
         //new_User.setAcctype("student");
          
              
                 
        if (new_User.registerUser()) {
            System.out.println("user has been created");}
        else { System.out.println("error on inserting user");
    
        
        }
        System.out.println("before lesson view");
        Lesson lesson = new Lesson();
        Collection collection = new ArrayList<Lesson>();
        System.out.println("collection has been created");
        Lesson.Category acc2 = Lesson.Category.beginner;    
        collection = Lesson.getByCategory(acc2);
        
        System.out.println("lesson is "+ collection);
        Iterator it = collection.iterator();
        while(it.hasNext()){
            System.out.println("in iterator");
            lesson = (Lesson)it.next();
            lesson.print();
         }
    
    
        System.out.println("view all lesson based on id of lesson");
        Lesson lesson_byid = new Lesson();       
        lesson_byid.getByLessonID(1);
        System.out.println("creator id is "+ lesson_byid.getUserID());
        lesson_byid.print();
        
        
        System.out.println("start liking a lesson");
        Lesson.like(1, 1);
        Lesson.like(1, 1);
        Lesson.like(1, 11);
        Lesson.like(1, 33);
        
        
        System.out.println("start dislike a lesson");
        Lesson.dislike(1, 1);
        Lesson.dislike(1, 1);
        Lesson.dislike(1, 31);
        
        Lesson.like(1, 1);*/
     
    }}
    


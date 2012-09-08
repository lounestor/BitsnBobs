/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import DA.CommentDA;
import java.util.Collection;

/**
 *
 * @author louise
 */
public class Comment {
    
    private int commentID;
    private int uploadID;
    private int commentorID;
    private String text;
    private String timestamp;
    private int inReplyTo = 0;
    private boolean isDeleted = false;

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getCommentorID() {
        return commentorID;
    }

    public void setCommentorID(int commentorID) {
        this.commentorID = commentorID;
    }

    public int getInReplyTo() {
        return inReplyTo;
    }

    public void setInReplyTo(int inReplyTo) {
        this.inReplyTo = inReplyTo;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getUploadID() {
        return uploadID;
    }

    public void setUploadID(int uploadID) {
        this.uploadID = uploadID;
    }
    
   public boolean getCommentByID(int commentid){
        
        CommentDA id = new CommentDA();  
        this.commentID = commentid;        
        return id.getCommentByID( this );        
    }
   
   public boolean insertThisComment(){
      
       CommentDA insert = new CommentDA();
       return insert.insertComment(this);     
     
   }
   
   public boolean updateThisComment(){
      
       CommentDA ds = new CommentDA();
       return ds.updateComment(this);     
     
   }
   public boolean deleteThisComment(){
      
       CommentDA ds = new CommentDA();
       return ds.deleteComment(this);     
     
   }
   
   public static Collection<Comment> showOriginalComments(int uploadid){
       
       CommentDA ds = new CommentDA();             
       return ds.getOriginalCommentsByUpload(uploadid);
   }
   
   public Collection<Comment> getRepliesToThisComment(){       
       CommentDA ds = new CommentDA();             
       return ds.getReplies(this.commentID);
   }
   
   public void print(){
         System.out.println("comment id = " +  this.getCommentID());
         System.out.println("upload id = " +  this.getUploadID());
         System.out.println("commentor id = " +  this.getCommentorID());
         System.out.println("the comment is = " +  this.getText());
         System.out.println("timedstamp is = " +  this.getTimestamp());
         System.out.println("inreplyto = " +  this.getInReplyTo());         
         System.out.println("is deleted = " +  this.getIsDeleted());
         
         
        
     }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import DA.UserDA;
import java.sql.SQLException;

/**
 *
 * @author louise
 */
public class User {
    
    private int userId;
    private String username;
    private String email;
    private String password;
    public enum Acctype{student,teacher};
    private Acctype acctype;
    
    public Acctype getAcctype() {
        return acctype;
    }

    public void setAcctype(Acctype acctype) {
      
        this.acctype = acctype;
    }
   
    boolean valid;
   
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
    
    //user login
    public int userLogin() throws SQLException {
        UserDA userDA = new UserDA();
        int find = userDA.userLogin(this);
        return find;
                
    }
    
    //register new user
    public boolean registerUser()throws SQLException{

        UserDA reg_userDA= new UserDA();
        if (reg_userDA.registerUser(this)) return true;
        else return false;

    }
     //edit details works
     public boolean editDetails(){
         UserDA editDA= new UserDA();
        if (editDA.editDetails(this)) return true;
        else return false;

    }

    public boolean validate() throws SQLException {
        UserDA validateUsernameDA = new UserDA();
        if(validateUsernameDA.validate(this)) return true;
        else return false;
                
    }
}

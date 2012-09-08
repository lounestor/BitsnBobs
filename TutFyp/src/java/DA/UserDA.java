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
import entity.User;
import entity.User.Acctype;
import java.sql.SQLException;

public class UserDA {
    
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
    
    
   //<editor-fold defaultstate="collapsed" desc="User Login returns Id:"> 
    public int userLogin(User login) throws SQLException {
        if (c == null) {
            c = getConnection();
        }
        int userid = 0;
       try {
            
           Statement statement = c.createStatement();
           String query = "SELECT userid,username,password FROM user WHERE username = \"" + login.getUsername() + "\" AND password = \"" + login.getPassword()+"\"";
           System.out.println(query);
           ResultSet rs = statement.executeQuery(query);
           while (rs.next()) {
               // login = this.findByUsername( login.getUsername() );
               System.out.println ( "User ID; " + login.getUserId() );
               login.setUserId(rs.getInt(1));
               userid = login.getUserId();
               
           }
                    
        
        } catch (SQLException e) {
            return userid;
            
            }
        
        return userid;
        
    }
    //</editor-fold> 
    
    
    
   //<editor-fold defaultstate="collapsed" desc="Register New User: only insert if username not taken"> 
    public boolean registerUser(User register) throws SQLException{
        String username = register.getUsername();
        String email = register.getEmail();
        //String acc = register.getAcctype();
        User.Acctype acc = register.getAcctype();
        String password = register.getPassword();
        
                
        if (c == null) {
            c = getConnection();
        }
        try {
            Statement statement = c.createStatement();
            String query = "INSERT INTO user(username,email,password,acctype) VALUES "
                    + "('" + username + "','"+email+"','"+password+"','"+acc+"')";
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
    
    
    //<editor-fold defaultstate="collapsed" desc="Edit User Details: WORKING "> 
    public boolean editDetails (User edit_userDetails) {
         
         String username = edit_userDetails.getUsername();
         String email = edit_userDetails.getEmail();
         String password = edit_userDetails.getPassword();
         
         
       if (c == null) {
            c = getConnection();
        }
        try {
            Statement statement = c.createStatement();
            String query = "UPDATE user SET username = '" +username+ "', email= '" 
                    +email+ "', password = '" +password+ "'";
           
          
           System.out.println(query);
            int rows = statement.executeUpdate(query);
            if (rows > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.println("exception" + e);
            return false;
        }
    }
    //</editor-fold>
    
    
    
    
     //<editor-fold defaultstate="collapsed" desc="validate username not taken:"> 
    public boolean validate(User validate) throws SQLException {
        if (c == null) {
            c = getConnection();
        }
        
       try {
            
           Statement statement = c.createStatement();
           String query = "SELECT username FROM user WHERE username = \"" + validate.getUsername() + "\"";
           System.out.println(query);
           ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                // login = this.findByUsername( login.getUsername() );
               System.out.println (validate.getUsername()+" is already taken" );
               if(validate.getUsername().isEmpty()){
                   return true;
            } else {
                return false;
            }
        } return true;
       
       } catch (Exception e) {
            System.out.println("exception" + e);
            return false;
        }      
    }
    //</editor-fold> 
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sageapp.DAO;
import sageapp.database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sageapp.model.AuthData;
import sageapp.model.LoginModel;
import sageapp.model.RegisterModel;
/**
 *
 * @author sangyakoirala
 */


public class AuthDAO extends MySqlConnection {
    public boolean register(RegisterModel user){
        try{
            PreparedStatement ps =null;
            Connection conn = openConnection();
            
            String sql = "INSERT INTO users(fname, lname, email, password) VALUES(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getFname());
            ps.setString(2, user.getLname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            
            int result = ps.executeUpdate();
            if(result == -1){
                return false;
            }else{
                return true;
            }
            
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

      public AuthData login(LoginModel login){
          try{
              PreparedStatement ps = null;
              Connection conn = openConnection();
              String sql = "SELECT * FROM users WHERE email = ? and password = ?";
              ps = conn.prepareStatement(sql);
              ps.setString(1, login.getEmail());
              ps.setString(2, login.getPassword());
              
              ResultSet result = ps.executeQuery();
              if(result !=null && result.next() != false){
                   // get data here
                   String email = result.getString("email");
                   String password = result.getString("password");
                   String fname = result.getString("fname");
                   String lname = result.getString("lname");
                   AuthData user = new AuthData(
                      fname,lname, email, password
                   );
                   return user;
              }else{
                  return null;
              }
          }catch(Exception exception){
              return null;
          }
      }

//     public LoginResponse login(LoginModel user){
//        try{
//            PreparedStatement ps =null;
//            Connection conn = openConnection();
//            
//            String sql = "SELECT * FROM users where email = ? and password = ?";
//            ps = conn.prepareStatement(sql);
//            ps.setString(1, user.getEmail());
//            ps.setString(2, user.getPassword());
//            
//            ResultSet result = ps.executeQuery();
//            if(result == null){
//                return null;
//            }else{
//                result.next();
//                LoginResponse lr = new LoginResponse(
//                        
//                result.getString("email"),
//                result.getString("password"),
//                result.getString("username")
//
//                );
//                return lr;
//            }
//            
//        }catch(Exception e){
//            System.out.println(e);
//            return null;
//        }
        
//    }
}
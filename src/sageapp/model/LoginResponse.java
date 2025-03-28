
package sageapp.model;

/**
 *
 * @author sangyakoirala
 */
public class LoginResponse {
    private int uid;
    private String email;
    private String password;
    private String fname;
    private String lname;
    public LoginResponse(int uid, String email, String password, String fname,String lname){
        this.uid=uid;
        this.email = email;
        this.password = password;
        this.fname = fname;
        this.lname=lname;
    }
    public Integer getUid(){
        return this.uid;
    }
    
    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
    } 
    
    public String getFname(){
        return this.fname;
    }    
    public String getLname(){
        return this.lname;
    }    
}


package sageapp.model;

/**
 *
 * @author sangyakoirala
 */
public class LoginResponse {
    private String email;
    private String password;
    private String username;
    public LoginResponse(String email, String password, String username){
        this.email = email;
        this.password = password;
        this.username = username;
    }
    
    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
    } 
    
    public String getUsername(){
        return this.username;
    }    
}
